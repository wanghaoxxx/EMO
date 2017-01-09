package com.emolabs.im.activity;


import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends Activity implements IEmoHttpListener{
	private static final String TAG = "LoginActivity";
	EmoHttpClient http = EmoHttpClient.getInstance();
	EditText etPhoneNumer;
	EditText etPassword;
	Button	 btnLogin;
	String strPhoneNumber;
	String strPassword;
	SharedPreferences sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}
	private void initView() {
		etPhoneNumer = (EditText) findViewById(R.id.et_login_phone_number);
		etPassword = (EditText) findViewById(R.id.et_login_password);
		btnLogin = (Button) findViewById(R.id.btn_login_login);

		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				strPhoneNumber = etPhoneNumer.getText().toString();
				strPassword = etPassword.getText().toString();
				if(!TextUtils.isEmpty(strPhoneNumber) && !TextUtils.isEmpty(strPassword)){
					login();
				}else{
					Toast.makeText(LoginActivity.this, "用户名或密码不正确", 0).show();
				}
			}
		});
	}

	private void login() {
		JSONObject json = new JSONObject();
		try {
			json.put("ph", strPhoneNumber);
			json.put("pw",strPassword);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		EmoRequest request=new EmoStringRequest(AppConfig.ServiceUrl.strLogin, json.toString(),this);
		http.doPost(request);  
	}

	/*
	 * 网络请求成功
	 * */
	@Override
	public void onSuccess(String tag,String requestUrl, String requestResult) {
		if(TextUtils.equals(requestUrl, AppConfig.ServiceUrl.strLogin) && tag == null){
			try {
				Log.i(TAG,"登录 result:" + requestResult);
				Log.i(TAG, "登录 requestResult:" + requestResult);
				JSONObject json = new JSONObject(requestResult);
				int errNo = json.getInt("errno");
				if(errNo == 0){//登录成功
					Toast.makeText(this, "登录成功", 1).show();
					
					//保存信息
					String token = json.getJSONObject("data").getString("token");
					String uid = json.getJSONObject("data").getJSONObject("user_basic").getString("id");
					Editor editor = sp.edit();
					editor.putString("X-Kata-Token", token);
					editor.putString("uid", uid);
					editor.putBoolean("isLogined", true);
					editor.putString("phoneNumber", strPhoneNumber);
					editor.commit();
					
					//保存用户个人信息
					Gson gson = new Gson();
					UserBasicInfo user = gson.fromJson(json.getJSONObject("data").getJSONObject("user_basic").toString(), UserBasicInfo.class);
					UserInfoManager.getInstance().setUserBasicInfo(user);
					
					startActivity(new Intent(this, HomeActivity.class));
					finish();
				}else{//登录失败
					if(errNo == -203){
						Toast.makeText(this, "用户名或者密码错误", 0).show();
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
				Log.i(TAG,"JSONException ... ");
			}
		}
	}

	/*
	 * 没有网络的情况下,网络访问请求失败
	 * */
	@Override
	public void onFailed(String tag,String requestUrl, int errorNo, String requestResult) {
		if(TextUtils.equals(requestUrl, AppConfig.ServiceUrl.strLogin) && tag == null)
		Toast.makeText(this, "网络访问请求失败aa", 0).show();
	}
	
}

















