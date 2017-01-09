
package com.emolibs.im.volley;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.emolabs.im.config.EmoApplication;
import com.emolabs.im.utils.WHLog;
import com.emolibs.im.volley.ParamsList.Params;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonRequest<T> extends Request<T> {

    private final Gson mGson;

    private final Class<T> mClazz;

    private final Listener<T> mListener;

    private ParamsList params;

    private String body;

    public GsonRequest(int method, String url, Class<T> clazz, Listener<T> listener,
            ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mClazz = clazz;
        this.mListener = listener;
        mGson = new Gson();
    }

    public GsonRequest(int method, String url, Class<T> clazz, Listener<T> listener,
            ErrorListener errorListener, ParamsList params) {
        super(method, url, errorListener);
        this.mClazz = clazz;
        this.mListener = listener;
        mGson = new Gson();
        this.params = params;
    }

    public GsonRequest(int method, String url,ParamsList params, Class<T> clazz, Listener<T> listener,
            ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mClazz = clazz;
        this.mListener = listener;
        this.body = UrlUtils.obtainJsonBody(params);
        mGson = new Gson();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        HashMap<String, String> paramsMap = new HashMap<String, String>();
        try {
            if (params != null) {
                for (Params param : params) {
                    paramsMap.put(param.name, URLEncoder.encode(param.value, "UTF-8"));
                }
            }
        } catch (Exception e) {
        }
        return paramsMap;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (!TextUtils.isEmpty(body)) {
            try {
                return body.getBytes("UTF-8");
            } catch (Exception e) {
                WHLog.e("不支持的encoding ", e);
            }
        }
        return super.getBody();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        SharedPreferences sp = EmoApplication.getInstance().getSharedPreferences("config",
                Context.MODE_PRIVATE);
        String token = sp.getString("X-Kata-Token", "");
        Map<String, String> headers = new HashMap<String, String>();
        if (!TextUtils.isEmpty(token)) {
            headers.put("X-Kata-Token",token);
        }
        return headers;
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data,"UTF-8");
            return Response.success(mGson.fromJson(json, mClazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

}
