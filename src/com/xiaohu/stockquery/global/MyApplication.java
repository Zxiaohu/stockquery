package com.xiaohu.stockquery.global;

import com.baidu.apistore.sdk.ApiStoreSDK;
import com.xiaohu.stockquery.R;

import android.app.Application;
public class MyApplication extends Application {
	
	public void onCreate() {
		ApiStoreSDK.init(this, getResources().getString(R.string.baidu_apikey));
		super.onCreate();
	}
}
