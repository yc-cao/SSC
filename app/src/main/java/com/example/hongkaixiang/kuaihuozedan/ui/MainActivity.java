package com.example.hongkaixiang.kuaihuozedan.ui;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hongkaixiang.kuaihuozedan.R;
import com.example.hongkaixiang.kuaihuozedan.utils.StatusBarUtil;

public class MainActivity extends AppCompatActivity {
    private WebView mWebMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setStatusBarColor(this,Color.parseColor("#EC2829"));
        initView();
    }

    private void initView() {
        mWebMain = (WebView) findViewById(R.id.web_main);
        // 清缓存和记录，缓存引起的白屏
        mWebMain.clearCache(true);
        mWebMain.clearHistory();
        WebSettings settings = mWebMain.getSettings();

        // 缓存白屏
        String appCachePath = getApplicationContext().getCacheDir()
                .getAbsolutePath() + "/webcache";
        // 设置 Application Caches 缓存目录
        settings.setAppCachePath(appCachePath);
        settings.setDatabasePath(appCachePath);

        settings.setAppCacheEnabled(false);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false);//解决图片不显示
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setAllowFileAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);

        //扩大比例的缩放
        settings.setUseWideViewPort(true);

        settings.setSavePassword(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setMediaPlaybackRequiresUserGesture(false);

        mWebMain.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    view.loadUrl(url);
                } else if (url.startsWith("javascript:")) {
                    view.loadUrl(url);
                } else {
                    //其余的链接走浏览器
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(url));
                        startActivity(intent);
                    } catch (Exception e) {
                    }
                }
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });

        mWebMain.loadUrl("http://wap.qblhl.com");
    }
}
