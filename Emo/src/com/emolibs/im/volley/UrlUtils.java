
package com.emolibs.im.volley;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.emolabs.im.config.AppConfig;
import com.emolibs.im.volley.ParamsList.Params;

import org.json.JSONObject;

public class UrlUtils {

    public static String encodeUrl(String sub_url) {
        return encodeUrl(sub_url, null);
    }

    public static String encodeUrl(String sub_url, ParamsList params) {
        StringBuilder result = new StringBuilder(AppConfig.ServiceUrl.baseUrl);
        result.append(sub_url);
        if (params != null) {
            result.append('?').append(encodeParams(params));
        }
        Log.i("url", result.toString());
        return result.toString();
    }

    public static String encodeGetUrl(String sub_url, ParamsList params) {
        StringBuilder result = new StringBuilder(sub_url);
        if (params != null) {
            result.append('?').append(encodeParams(params));
        }
        return result.toString();
    }
    
    public static String obtainJsonBody(ParamsList params){
        String body="";
        try {
            JSONObject o=new JSONObject();
            for(Params param:params){
                o.put(param.name,URLEncoder.encode(param.value, "UTF-8"));
            }
            body=o.toString();
        } catch (Exception e) {
        }
        return body;
    }

    private static String encodeParams(ParamsList params) {
        StringBuilder sb = new StringBuilder();
        int _i = 0;
        for (Params param : params) {
            if (_i > 0)
                sb.append("&");
            sb.append(param.name).append("=");
            try {
                sb.append(URLEncoder.encode(param.value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            _i++;
        }
        return sb.toString();
    }
}
