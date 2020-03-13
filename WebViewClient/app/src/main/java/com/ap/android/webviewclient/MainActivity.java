package com.ap.android.webviewclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ap.android.webviewclient.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    String TAG = MainActivity.class.getSimpleName();

    private String link = "https://fwstaging.immdemo.net/web/registration.aspx?clientid=2";
    //"https://fwstaging.immdemo.net/web/registration.aspx?clientid=2";
    ActivityMainBinding binding;
    WebViewClient webViewClient;
    WebChromeClient webChromeClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        webViewClient = new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, final String url) {
                Log.d(TAG, ">> shouldOverrideUrlLoading");
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //super.onPageFinished(view, url);
                Log.d(TAG, "onPageFinished >>");
                view.loadUrl("javascript:HtmlViewer.showHTML" +
                        "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
                //view.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            }

        };
        webChromeClient = new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }

        };
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.setWebViewClient(webViewClient);
        //binding.webview.setWebChromeClient(webChromeClient);
        binding.webview.loadUrl(link);
        binding.webview.addJavascriptInterface(new MyJavaScriptInterface(this), "HtmlViewer");

    }
}




