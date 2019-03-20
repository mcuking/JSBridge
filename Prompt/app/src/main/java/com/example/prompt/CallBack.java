package com.example.prompt;

import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import org.json.JSONObject;

public class CallBack {
    private  String cbName;
    private WebView mWebView;

    public CallBack(WebView webView, String cbName) {
        this.cbName = cbName;
        this.mWebView = webView;
    }

    public void apply(JSONObject jsonObject) {
        if (mWebView!=null) {
            mWebView.evaluateJavascript("javascript:" + cbName + "(" + jsonObject.toString() + ")", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    return;
                }
            });
        }
    }
}
