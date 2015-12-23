package com.xiaohu.stockquery.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.xiaohu.stockquery.R;
import com.xiaohu.stockquery.fragment.base.BaseFragment;

public class MainFragment extends BaseFragment {

	public View initView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_main,container, false);
		ViewUtils.inject(this, view);
		return view;
	}

	public void initData(Bundle savedInstanceState) {
		
	}
}
