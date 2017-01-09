
package com.emolabs.im.activity;

import java.io.File;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.emolabs.im.R;
import com.emolabs.im.adapter.ListFeedAdapter;
import com.emolabs.im.config.AppConfig;
import com.emolabs.im.config.EmoApplication;
import com.emolabs.im.struct.Feed;
import com.emolabs.im.struct.FeedDataItem;
import com.emolabs.im.userinfo.UserInfoManager;
import com.emolabs.im.utils.UIUtils;
import com.emolibs.im.volley.GsonRequest;
import com.emolibs.im.volley.EmoVolley;
import com.emolibs.im.volley.ParamsList;
import com.emolibs.im.volley.ParamsList.Params;
import com.manuelpeinado.quickreturnheader.QuickReturnHeaderHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @category 动态主页
 * @author zhanghongjun 
 * @date 2015.4.29 PM 14:41
 */
public class HomeActivity extends Activity implements View.OnClickListener{
    
    ImageView ivListBackground;
    ListView lvListFeed;
    ArrayList<FeedDataItem> feedItemList=new ArrayList<FeedDataItem>();
    String last_fid;
    boolean hasMore;
    boolean isloading;

    // 头部浮层
    RelativeLayout rlFloatLayout;
    ImageView ivFloatHeader;
    ImageView ivFloatEmo;
    ImageView ivFloatNewNotify;
    ImageView ivFloatAddFriend;

    // 底部浮层
    ImageView ivBottomHome;
    ImageView ivBottomTakephoto;
    ImageView ivBottomDiscover;
    ListFeedAdapter listFeedAdapter;
    private String token;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        token = sp.getString("X-Kata-Token", null);
        uid = sp.getString("uid", null);
        initView();
        getFeedList(AppConfig.INIT);
    }
    

    private void initView() {
        // 头部浮层
        rlFloatLayout = (RelativeLayout) findViewById(R.id.rl_home_float_title);
        ivFloatHeader = (ImageView) findViewById(R.id.iv_home_float_title_header);
        ivFloatEmo = (ImageView) findViewById(R.id.iv_home_float_emo);
        ivFloatNewNotify = (ImageView) findViewById(R.id.iv_home_new_notify_msg);
        ivFloatAddFriend = (ImageView) findViewById(R.id.iv_home_float_add_friend);
        // 底部浮层
        ivBottomHome = (ImageView) findViewById(R.id.iv_home_bottom_home);
        ivBottomTakephoto = (ImageView) findViewById(R.id.iv_home_bottom_takephoto);
        ivBottomDiscover = (ImageView) findViewById(R.id.iv_home_bottom_discover);

        ivListBackground = (ImageView) findViewById(R.id.iv_home_background);
        lvListFeed = (ListView) findViewById(R.id.lv_home_list_feed);
        // 设置头部浮层监听
        ivFloatHeader.setOnClickListener(this);
        ivFloatEmo.setOnClickListener(this);
        ivFloatNewNotify.setOnClickListener(this);
        ivFloatAddFriend.setOnClickListener(this);
        // 设置底部浮层监听
        ivBottomHome.setOnClickListener(this);
        ivBottomTakephoto.setOnClickListener(this);
        ivBottomDiscover.setOnClickListener(this);
        
        String headerUrl = UserInfoManager.getInstance().getUserBasicInfo().head;
        ImageLoader.getInstance().displayImage(headerUrl, ivFloatHeader,
                EmoApplication.options_circle);
        int titleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 54f,
                getResources()
                        .getDisplayMetrics());
        new QuickReturnHeaderHelper(rlFloatLayout, lvListFeed, titleHeight).setup();
        lvListFeed.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        getFeedList(AppConfig.MORE);
                    }
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                    int totalItemCount) {
            }
        });
        listFeedAdapter=new ListFeedAdapter(this,feedItemList);
        lvListFeed.setAdapter(listFeedAdapter);
    }

    /**
     * 获取动态列表
     * @param state
     */
    private void getFeedList(final int state) {
        if(isloading)
            return;
        isloading=true;
        if(state==AppConfig.INIT){
            last_fid="0";
        }else if (state==AppConfig.REFRESH) {
            last_fid="0";
        }else if (state==AppConfig.MORE) {
            if(!hasMore) //已无数据，返回
                return;
        }
        ParamsList params = new ParamsList();
        params.add(new Params("uid", uid));
        params.add(new Params("last_fid", last_fid));
        final Response.Listener<Feed> listener = new Response.Listener<Feed>() {
            @Override
            public void onResponse(Feed response) {
                if(response!=null&&response.errno==0){ //正常返回
                    handleSuccess(state,response);
                }else if(response!=null&&response.errno==-1){  //错误
                    UIUtils.showShortToast(response.usermsg);
                }else {
                    handleError(state);
                }
            }
        };
        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {  //错误
                handleError(state);
            }
        };
        GsonRequest<Feed> request = new GsonRequest<Feed>(Method.POST,
                AppConfig.ServiceUrl.strGetFeedList, params, Feed.class, listener, errorListener);
        request.setTag(this);
        EmoVolley.getRequestQueue().add(request);
    }
    
    /**
     * 返回成功
     * @param state
     * @param data
     */
    private void handleSuccess(int state,Feed data){
        switch (state) {
            case AppConfig.INIT:
            case AppConfig.REFRESH:
                feedItemList.clear();
            case AppConfig.MORE:
                last_fid=data.data.last_fid;  //当前最后一条的id
                hasMore=data.data.has_more;  //是否还有数据
                feedItemList.addAll(data.data.feed_list);
                listFeedAdapter.notifyDataSetChanged();
                break;
        }
        isloading=false;
    }
    
    /**
     * 返回失败
     * @param state
     */
    private void handleError(int state){
        if(state==AppConfig.INIT){
            UIUtils.showShortToast("休息一下，木有网络啦");
        }else if (state==AppConfig.REFRESH) {
            
        }else if (state==AppConfig.MORE) {
            UIUtils.showShortToast("休息一下，木有网络啦");
        }
        isloading=false;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            String storage = Environment.getExternalStorageDirectory().getAbsolutePath();
            Intent intent = new Intent();
            intent.putExtra("filename", EmoApplication.appDataDir + "temp.jpg");
            intent.setClass(this, ShowTakePhotoActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.iv_home_float_title_header:// 头部头像
            Bundle bundle=new Bundle();
            bundle.putString("uid",uid);
            bundle.putString("fid","0");
            bundle.putString("X-Kata-Token", token);
            UIUtils.openActivity(this, PersonalFeedActivity.class, bundle);
            break;
        case R.id.iv_home_new_notify_msg:// 头部新的通知
        case R.id.iv_home_float_emo:// 头部Emo
            UIUtils.openActivity(this,MessageActivity.class);
            break;
        case R.id.iv_home_float_add_friend:// 头部添加好友
            UIUtils.openActivity(this,AddFriendActivity.class);
            break;
        case R.id.iv_home_bottom_home:// 底部首页
            Log.i("home_click", "底部首页");
            break;
        case R.id.iv_home_bottom_takephoto:// 底部拍照
            Log.i("home_click", "底部拍照");
            Uri imageUri = Uri.fromFile(new File(EmoApplication.appDataDir,"temp.jpg"));
            Intent intentTakePhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intentTakePhoto.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
            startActivityForResult(intentTakePhoto, 0);    
            break;
        case R.id.iv_home_bottom_discover:// 底部发现
            UIUtils.openActivity(this,DiscoverActivity.class);
            break;
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EmoVolley.getRequestQueue().cancelAll(this);
    }
}
