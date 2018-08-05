package com.bdparcel.bdparcel;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BdparcelActivity extends AppCompatActivity {
WebView webView;
   final String url = "https://www.bdparcel.com";
    TextView per;
    Activity activity;
    ProgressBar progressBar;
    ImageView iv;
    RelativeLayout rl;
    final static public String PREFS_NAME = "PREFS_NAME";
    final static private String PREF_KEY_SHORTCUT_ADDED = "PREF_KEY_SHORTCUT_ADDED";
    private boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        boolean shortCutWasAlreadyAdded = sharedPreferences.getBoolean(PREF_KEY_SHORTCUT_ADDED, false);
        if(shortCutWasAlreadyAdded){

        }
        else {
            final Intent shortcutIntent = new Intent(this, BdparcelActivity.class);

            final Intent intent = new Intent();
            intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
// Sets the custom shortcut's title
            intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
// Set the custom shortcut icon
            intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(this, R.drawable.logo));
// add the shortcut
            intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(PREF_KEY_SHORTCUT_ADDED,true);
            editor.commit();
            editor.apply();
        }

        webView = (WebView) findViewById(R.id.wb);
        progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        progressBar.setScaleY(2.1f);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.setWebViewClient(new ourViewClient());
        webView.loadUrl(url);

        webView.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                progressBar.setProgress(newProgress);


                if(newProgress == 100){

                    progressBar.setVisibility(View.GONE);
                }
                else
                {
                   progressBar.setVisibility(View.VISIBLE);
                }
            }


        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.setWebViewClient(new ourViewClient());
        webView.loadUrl(url);

        webView.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

              progressBar.setProgress(newProgress);

                if(newProgress == 100){
                    progressBar.setVisibility(View.GONE);

                }
                else
                {
                    progressBar.setVisibility(View.VISIBLE);

                }
            }


        });
    }


}
