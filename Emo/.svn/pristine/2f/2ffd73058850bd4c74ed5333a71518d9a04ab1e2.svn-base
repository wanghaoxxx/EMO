
package com.emolabs.im.utils;

import java.io.File;
import com.emolabs.im.config.EmoApplication;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class UIUtils {

    public static void showShortToast(int pResId) {
        showShortToast(EmoApplication.getInstance().getString(pResId));
    }

    public static void showLongToast(String pMsg) {
        Toast.makeText(EmoApplication.getInstance(), pMsg, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(String pMsg) {
        Toast.makeText(EmoApplication.getInstance(), pMsg, Toast.LENGTH_SHORT).show();
    }

    public static void  openActivity(Context context, Class<?> pClass) {
        openActivity(context, pClass, null);
    }

    public static void openActivity(Context context, Class<?> pClass, Bundle pBundle) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            Intent intent = new Intent(activity, pClass);
            if (pBundle != null) {
                intent.putExtras(pBundle);
            }
            activity.startActivity(intent);
        } else {
            Log.e("wh", "context not a activity instance");
        }
    }

    public static void openActivityForResult(Context context, Class<?> pClass, int pRequestCode) {
        openActivityForResult(context, pClass, pRequestCode, null);
    }

    public static void openActivityForResult(Context context, Class<?> pClass, int pRequestCode,
            Bundle pBundle) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            Intent intent = new Intent(activity, pClass);
            if (pBundle != null)
                intent.putExtras(pBundle);
            activity.startActivityForResult(intent, pRequestCode);
        } else {
            Log.e("wh", "context not a activity instance");
        }
    }
}
