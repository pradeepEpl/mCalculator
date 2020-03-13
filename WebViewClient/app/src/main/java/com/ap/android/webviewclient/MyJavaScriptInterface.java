package com.ap.android.webviewclient;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class MyJavaScriptInterface {
    String TAG = MyJavaScriptInterface.class.getSimpleName();

    private Context ctx;


    public MyJavaScriptInterface(Context ctx) {
        this.ctx = ctx;
    }

    @JavascriptInterface
    public void showHTML(String html) {
        System.out.println(html);
        Log.d(TAG, "Html >> "+ html);
    }
}
