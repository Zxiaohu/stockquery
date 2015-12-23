package com.xiaohu.stockquery.fragment.base;

import com.xiaohu.stockquery.R;
import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public abstract class BasePager {
	public Activity mActivity;
	public View mRootView;
	public TextView mTvTitle;
	public FrameLayout mFrameLayout;
	public BasePager(Activity activity){
		this.mActivity=activity;
		initView();
	}
	
	private void initView(){
		mRootView=View.inflate(mActivity, R.layout.basepager, null);
		mTvTitle = (TextView) mRootView.findViewById(R.id.textView1);
		mFrameLayout = (FrameLayout) mRootView.findViewById(R.id.fl_content);
	}
	abstract public void initData();
	
	protected void setContentView(View view){
		
		mFrameLayout.addView(view);
	
	}
}
