package com.xiaohu.stockquery.fragment.base;

import android.app.Activity;
import android.view.View;

public abstract class BaseMainPagerView {
	
	public Activity mActivity;

	public View mRootView;// �����ֶ���

	public BaseMainPagerView(Activity activity) {
		mActivity = activity;
		mRootView = initViews();
	}

	/**
	 * ��ʼ������
	 */
	public abstract View initViews();

	/**
	 * ��ʼ������
	 */
	public void initData() {

	}
}
