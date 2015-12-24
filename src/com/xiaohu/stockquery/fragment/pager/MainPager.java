package com.xiaohu.stockquery.fragment.pager;

import android.app.Activity;
import com.xiaohu.stockquery.fragment.base.BasePager;

public class MainPager extends BasePager {


	private MainPagerView mainPagerView;
	public MainPager(Activity activity) {
		super(activity);

	}

	public void initData() {
		mTvTitle.setText("主页");
		if(mainPagerView==null){
			mainPagerView= new MainPagerView(mActivity);
			mainPagerView.initData();
			mFrameLayout.addView(mainPagerView.mRootView);
		}
		
	}

}
