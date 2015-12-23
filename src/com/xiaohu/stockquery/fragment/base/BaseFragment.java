package com.xiaohu.stockquery.fragment.base;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public abstract class BaseFragment extends Fragment {

	public View view;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = initView(inflater,container,savedInstanceState);

		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	abstract public View initView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState);
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData(savedInstanceState);
	}
	abstract public void initData(Bundle savedInstanceState);

}
