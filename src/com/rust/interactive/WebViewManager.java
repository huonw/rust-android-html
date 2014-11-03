package com.rust.interactive;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebViewManager {
	public static String website = "http://192.168.0.121:8000";

	private WebView webview;
	
	private boolean started;
	public boolean isStarted(){
		return started;
	}

	public WebViewManager(WebView contentView){
		webview = contentView;
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setBuiltInZoomControls(true);
		webview.setWebViewClient(new WebViewClient()
	    {
			// stops the app from loading the URL in a separate browser
	        @Override
	        public boolean shouldOverrideUrlLoading(WebView  view, String  url)
	        {
	            return false;
	        }
	    });
		started = false;
	}

	public void start(){
		started = true;
		webview.loadUrl(website);
	}
	
	public void stop(){
		if (!started)
			return;
		webview.loadUrl("about:blank");
		started = false;
	}

	public void registerButtonPress(Button me){
		if (started){
			// stop running
			this.stop();
			me.setText("Start...");
		} else {
			// start running
			this.start();
			me.setText("Stop...");
		}
	}
}
