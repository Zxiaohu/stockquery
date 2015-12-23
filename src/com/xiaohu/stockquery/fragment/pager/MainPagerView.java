package com.xiaohu.stockquery.fragment.pager;

import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xiaohu.stockquery.R;
import com.xiaohu.stockquery.fragment.base.BaseMainPagerView;

public class MainPagerView extends BaseMainPagerView {

	private ListView lv_stockinfo;
	public MainPagerView(Activity activity) {
		super(activity);
	}
	public View initViews() {
		mRootView=View.inflate(mActivity, R.layout.fragment_main, null);
		lv_stockinfo=(ListView) mRootView.findViewById(R.id.lv_stockinfo);
		return mRootView;
	}
	public void initData() {
		super.initData();
		lv_stockinfo.setAdapter(new ArrayAdapter<String>(mActivity, android.R.layout.simple_expandable_list_item_1,new String []{"一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后","一千年以后"}));
	}
}
