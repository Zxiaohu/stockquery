package com.xiaohu.stockquery.fragment.base;

import android.app.Activity;
import android.view.View;

public abstract class BaseMainPagerView {
	
	public Activity mActivity;
	public View mRootView;//根view

	public BaseMainPagerView(Activity activity) {
		mActivity = activity;
		mRootView = initViews();
	}

	/**
	 *初始化view
	 */
	public abstract View initViews();

	/**
	 * 初始化数据
	 */
	public void initData() {

	}
}
