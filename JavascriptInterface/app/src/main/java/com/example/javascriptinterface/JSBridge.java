package com.example.javascriptinterface;

import android.net.Uri;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class JSBridge {
    private WebView mWebView;

    public JSBridge(WebView webView) {
        this.mWebView = webView;
    }


    private  static Map<String, HashMap<String, Method>> exposeMethods = new HashMap<>();

    public static void register(String exposeName, Class<?> classz) {
        if (!exposeMethods.containsKey(exposeName)) {
            exposeMethods.put(exposeName, getAllMethod(classz));
        }
    }

    private static HashMap<String, Method> getAllMethod(Class injectedCls) {
        HashMap<String, Method> methodHashMap = new HashMap<>();

        Method[] methods = injectedCls.getDeclaredMethods();

        for (Method method: methods) {
            if(method.getModifiers()!=(Modifier.PUBLIC | Modifier.STATIC) || method.getName()==null) {
                continue;
            }
            Class[] parameters = method.getParameterTypes();
            if (parameters!=null && parameters.length==3) {
                if (parameters[0] == WebView.class && parameters[1] == JSONObject.class && parameters[2] == CallBack.class) {
                    methodHashMap.put(method.getName(), method);
                }
            }
        }

        return methodHashMap;
    }

    @JavascriptInterface
    public String call(String methodName, String args) {
            try {
                JSONObject _args = new JSONObject(args);
                JSONObject arg = new JSONObject(_args.getString("data"));
                String cbName = _args.getString("cbName");


                if (exposeMethods.containsKey("JSBridge")) {
                    HashMap<String, Method> methodHashMap = exposeMethods.get("JSBridge");

                    if (methodHashMap!=null && methodHashMap.size()!=0 && methodHashMap.containsKey(methodName)) {
                        Method method = methodHashMap.get(methodName);

                        if (method!=null) {
                            method.invoke(null, mWebView, arg, new CallBack(mWebView, cbName));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        return null;
    }
}