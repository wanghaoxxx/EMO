package com.emolabs.im.activity;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.emolabs.im.R;
import com.emolabs.im.config.AppConfig;
import com.emolabs.im.net.EmoHttpClient;
import com.emolabs.im.net.EmoRequest;
import com.emolabs.im.net.EmoStringRequest;
import com.emolabs.im.net.IEmoHttpListener;
import com.emolabs.im.userinfo.UserBasicInfo;
import com.emolabs.im.userinfo.UserInfoManager;
import com.google.gson.Gson;

public class SplashActivity extends Activity implements IEmoHttpListener {
	private static final String TAG = "SplashActivity";
	SharedPreferences sp;
	EmoHttpClient http = EmoHttpClient.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		boolean bIsRegistered = sp.getBoolean("isRegistered", false);
		boolean bIsLogined = sp.getBoolean("isLogined", false);

		bIsRegistered = true;// 测试用
		if (bIsRegistered) {
			if (bIsLogined) {
				autoLogin();
			} else {
				startActivity(new Intent(this, LoginActivity.class));
			}
		} else {
			startActivity(new Intent(this, RegisterActivity.class));
		}
		finish();
	}

	// 自动登录
	private void autoLogin() {
		String strToken = sp.getString("X-Kata-Token", null);
		if(!TextUtils.isEmpty(strToken)){
			HashMap<String, String> autoLoginHeaders = new HashMap<String, String>();
			autoLoginHeaders.put("X-Kata-Token", strToken);
			EmoRequest request = new EmoStringRequest(AppConfig.ServiceUrl.strAutoLogin, autoLoginHeaders, null, this);
			http.doPost(request);
		}else{
			startActivity(new Intent(this, LoginActivity.class));
		}
	}

	@Override
	public void onSuccess(String tag,String requestUrl, String requestResult) {
		if (TextUtils.equals(requestUrl, AppConfig.ServiceUrl.strAutoLogin) && tag == null) {
			JSONObject json = null;
			int errno = -1;
			try {
				json = new JSONObject(requestResult);
				errno = json.getInt("errno");
			} catch (JSONException e) {
				e.printStackTrace();
				
				Toast.makeText(this, "登录失败", 0).show();
			}

			Log.i(TAG, "自动登录... errno:" + errno);
			Log.i(TAG, "自动登录 requestResult:" + requestResult);

			if (errno == 0) {
				try {
					//保存数据 
					String uid = json.getJSONObject("data").getJSONObject("user_basic").getString("id");
					Editor editor = sp.edit();
					editor.putString("uid", uid);
					editor.putBoolean("isLogined", true);
					editor.commit();
					
					//保存用户个人信息
					Gson gson = new Gson();
					UserBasicInfo user = gson.fromJson(json.getJSONObject("data").getJSONObject("user_basic").toString(), UserBasicInfo.class);
					UserInfoManager.getInstance().setUserBasicInfo(user);
					
					startActivity(new Intent(this, HomeActivity.class));
					finish();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Toast.makeText(this, "登录失败", 0).show();
				Editor editor = sp.edit();
				editor.putString("X-Kata-Token", null);
				editor.commit();
				startActivity(new Intent(this, LoginActivity.class));
				finish();
			}
		}
	}
	@Override
	public void onFailed(String tag,String requestUrl, int errorNo, String requestResult) {
		if (TextUtils.equals(requestUrl, AppConfig.ServiceUrl.strAutoLogin) && tag == null) {
			Log.i(TAG, "requestUrl=" + requestUrl + ":result=" + requestResult);
			Toast.makeText(this, "访问出错", 0).show();
			finish();
		}
	}
}
