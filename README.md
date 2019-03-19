# mini-jsbridge

a simple jsbridge based on android

本项目以 js 与 android 通信为例，讲解 jsbridge 实现原理，下面提到的方法在iOS（UIWebview 或 WKWebview）均有对应方法。

## 1. native to js
两种 native 调用 js 方法，注意被调用的方法需要在 JS 全局上下文上

##### loadUrl
##### evaluateJavascript

#### 1.1 loadUrl
```java
mWebview.loadUrl("javascript: func()");
```

#### 1.2 evaluateJavascript
```java
mWebview.evaluateJavascript("javascript: func()", new ValueCallback<String>() {
    @Override
    public void onReceiveValue(String value) {
        return;
    }
});
```
两种方法的对比如下表：

| 方法名 | 优点 | 缺点 |
| ------ | ------ | ------ |
| loadUrl | 兼容性好 | 1. 会刷新页面 2. 无法获取js方法执行结果 |
| evaluateJavascript | 1. 性能好 2. 可获取返回值 | 仅在安卓4.4以上可用 |

## 2. js to native

三种 js 调用 native 方法
##### Url Schema
##### 拦截 prompt alert confirm
##### addJavascriptInterface
待续。。。