package com.example.javascriptinterface;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONObject;

public class NativeMethods {
    public static void showToast(WebView view, JSONObject arg, CallBack callBack) {
        String message = arg.optString("msg");

        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

        if (callBack !=null) {
            try {
                JSONObject result = new JSONObject();
                result.put("msg", "js 调用 native 成功！");
                callBack.apply(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
