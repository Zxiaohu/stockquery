package com.xiaohu.stockquery.fragment.pager;

import android.app.Activity;
import android.util.Log;

import com.xiaohu.stockquery.fragment.base.BasePager;

public class MorePager extends BasePager {
	public MorePager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}
	
	public void initData() {
		mTvTitle.setText("����");
		Log.i("PagerDataTest","�������ݳ�ʼ��");
	}

}
