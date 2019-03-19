package com.example.javascriptinterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.mWebView);

        // 设置 webViewClient 类
        mWebView.setWebViewClient(new WebViewClient());

        // 设置 webChromeClient 类
        mWebView.setWebChromeClient(new WebChromeClient());

        // 设置支持调用 JS
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.loadUrl("file:///android_asset/index.html");

        JSBridge.register("JSBridge", NativeMethods.class);
        mWebView.addJavascriptInterface(new JSBridge(mWebView), "_jsbridge");
    }


    // 通过拦截 onKeyDown 事件实现网页回退
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
