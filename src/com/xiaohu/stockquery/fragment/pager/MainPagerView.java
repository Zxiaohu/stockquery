package com.xiaohu.stockquery.fragment.pager;

import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xiaohu.stockquery.R;
import com.xiaohu.stockquery.adapter.base.CommonAdapter;
import com.xiaohu.stockquery.adapter.base.ViewHolder;
import com.xiaohu.stockquery.fragment.base.BaseMainPagerView;
import com.xiaohu.stockquery.global.Global;
import com.xiaohu.stockquery.model.StockQueryResultInfo;
import com.xiaohu.stockquery.model.StockQueryResultInfo.Stockinfo;
import com.xiaohu.stockquery.tools.HttpTools;
import com.xiaohu.stockquery.tools.HttpTools.MyHttpListener;
import com.xiaohu.stockquery.tools.LogUtil;
import com.xiaohu.stockquery.tools.SystemTools;

public class MainPagerView extends BaseMainPagerView {

	private ListView lv_stockinfo;//股票信息列表
	private ArrayList<StockQueryResultInfo.Stockinfo> mStockinfos;
	private Button btn_add;//
	public MainPagerView(Activity activity) {
		super(activity);
	}

	public View initViews() {
		mRootView=View.inflate(mActivity, R.layout.fragment_main, null);
		lv_stockinfo=(ListView) mRootView.findViewById(R.id.lv_stockinfo);
		btn_add=(Button) mRootView.findViewById(R.id.btn_add);
		return mRootView;
	}

	public void initData() {
		super.initData();

		btn_add.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				getDataForServer();

			}


		});
	}
	/**
	 * 填充数据
	 * @param stockQueryResultInfo 
	 */
	private void showLvStockInfo(StockQueryResultInfo stockQueryResultInfo) {
		//从网络传来的数据
		mStockinfos=stockQueryResultInfo.retData.stockinfo;
		
		if(mStockinfos!=null){		
			lv_stockinfo.setAdapter(new CommonAdapter<StockQueryResultInfo.Stockinfo>(mActivity,mStockinfos,R.layout.main_pagerview_listadt) {
				public void convert(ViewHolder helper, Stockinfo item) {
					helper.setText(R.id.tv_stock_name, item.name);
					helper.setText(R.id.tv_stock_startp, item.OpenningPrice);
					helper.setText(R.id.tv_stock_minp, item.lPrice);
					helper.setText(R.id.tv_stock_maxp, item.hPrice);
					helper.setText(R.id.tv_stock_currentp, item.currentPrice);
					helper.setText(R.id.tv_stock_date, item.date);
				}
			});
		}
	}

	
	/**
	 * 发送网络请求拿数据
	 */
	private void getDataForServer() {
		RequestParams params=new RequestParams("utf-8");
		HttpTools.sendHttp(Global.httpUrl+"?"+Global.httpArg, mActivity,params,new MyHttpListener() {
			public void finish(String response) {
				//填充listview数据
				showLvStockInfo(parseJsonData(response));
			}
		});
	}

	/**
	 * 解析json数据结果
	 * @param response
	 */
	private StockQueryResultInfo parseJsonData(String response) {
		Gson gson= new Gson();
		return gson.fromJson(response, StockQueryResultInfo.class);
	}
}
