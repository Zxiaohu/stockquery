package com.xiaohu.stockquery.fragment.pager;



import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xiaohu.stockquery.R;
import com.xiaohu.stockquery.fragment.base.BaseMainPagerView;


import com.xiaohu.stockquery.tools.LogUtil;
import com.xiaohu.stockquery.tools.SystemTools;

public class MorePagerView extends BaseMainPagerView {

	@ViewInject(R.id.web_view)
	private WebView webView;
	@ViewInject(R.id.et_stock_input)
	private EditText et_content;
	@ViewInject(R.id.btn_add)
	private Button btn_add;
	
	private Handler mHandler= new Handler();
	
	public MorePagerView(Activity activity) {
		super(activity);
	}

	public View initViews() {
		
		mRootView=View.inflate(mActivity, R.layout.morepager_view, null);
		ViewUtils.inject(this, mRootView);
		return mRootView;
	}

	public void initData() {
		super.initData();
		
		
		WebSettings webSettings = webView.getSettings();
		
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);

      
	    final class DemoJavaScriptInterface {

	        DemoJavaScriptInterface() {
	        }

	        /**
	         * This is not called on the UI thread. Post a runnable to invoke
	         * loadUrl on the UI thread.
	         */
	        @JavascriptInterface
	        public void clickOnAndroid() {
	            mHandler.post(new Runnable() {
	                public void run() {
	                    webView.loadUrl("javascript:wave()");//调用js里的方法
	                    SystemTools.showToastInfo(mActivity, "这是用js调用的Toast", 1);
	                }
	            });

	        }
	    }

	    /**
	     * Provides a hook for calling "alert" from javascript. Useful for
	     * debugging your javascript.
	     */
	    final class MyWebChromeClient extends WebChromeClient {
	    
	    	
	    	@Override
	    	public boolean onJsAlert(WebView view, String url, String message,
	    			JsResult result) {
	    		LogUtil.i("MyWebChromeClient",message);
	    		
	    		String [] str=message.split(",");
	    		et_content.setText("用户名:"+str[0]+"----密码:"+str[1]);
	    		
	    		
	    		
	    		
	    		result.confirm();
	    		return true;
	    	}
	    }
	    webView.setWebChromeClient(new MyWebChromeClient());//用来接收从js传来的值

        webView.addJavascriptInterface(new DemoJavaScriptInterface(), "demo");//在里面定义js响应的方法


        webView.loadUrl("file:///android_asset/demo.html");

	}

}
