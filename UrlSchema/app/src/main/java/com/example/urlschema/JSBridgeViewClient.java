package com.example.urlschema;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class JSBridgeViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        JSBridge.call(view, url);
        return true;
    }
}
