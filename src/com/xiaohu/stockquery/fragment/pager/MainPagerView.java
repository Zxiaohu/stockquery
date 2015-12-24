package com.xiaohu.stockquery.fragment.pager;

import java.util.ArrayList;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xiaohu.stockquery.R;
import com.xiaohu.stockquery.adapter.base.CommonAdapter;
import com.xiaohu.stockquery.adapter.base.ViewHolder;
import com.xiaohu.stockquery.fragment.base.BaseMainPagerView;
import com.xiaohu.stockquery.global.Global;
import com.xiaohu.stockquery.model.JuHeListData;
import com.xiaohu.stockquery.model.JuHeListData.StockInfo;
import com.xiaohu.stockquery.model.StockQueryResultInfo;
import com.xiaohu.stockquery.model.StockQueryResultInfo.Stockinfo;
import com.xiaohu.stockquery.tools.HttpTools;
import com.xiaohu.stockquery.tools.HttpTools.MyHttpListener;
import com.xiaohu.stockquery.tools.LogUtil;
import com.xiaohu.stockquery.tools.SystemTools;

public class MainPagerView extends BaseMainPagerView {

	private ListView lv_stockinfo;//股票信息列表
	private ArrayList<JuHeListData.StockInfo> mStockinfos;//聚合数据返回的数据
	private Button btn_add;//
	private EditText et_stock_input;
	private JuHeListData juHeListData;
	private TextView tv_itemsinfo;
	public MainPagerView(Activity activity) {
		super(activity);
	}

	public View initViews() {
		mRootView=View.inflate(mActivity, R.layout.fragment_main, null);
		lv_stockinfo=(ListView) mRootView.findViewById(R.id.lv_stockinfo);
		btn_add=(Button) mRootView.findViewById(R.id.btn_add);
		et_stock_input=(EditText) mRootView.findViewById(R.id.et_stock_input);
		tv_itemsinfo=(TextView) mRootView.findViewById(R.id.tv_items);
		return mRootView;
	}

	public void initData() {
		super.initData();
		btn_add.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				//获取输入框的数据
				String page=et_stock_input.getText().toString().trim();

				//验证数据
				if(page.equals("")){
					SystemTools.showToastInfo(mActivity, "请填入完整信息", 2);
				}else{
					
					//getDataForServer(stockID);
					getDataForJuHeServer(Integer.parseInt(page),2);
					//getDataByBaiDuApi();
				}
			}

		});
	}
	/**
	 * 填充数据
	 * @param stockQueryResultInfo 
	 */
	private void showLvStockInfo(JuHeListData juHeListData) {
		//从网络传来的数据
		mStockinfos=juHeListData.result.data;

		if(mStockinfos!=null){		
			lv_stockinfo.setAdapter(new CommonAdapter<JuHeListData.StockInfo>(mActivity,mStockinfos,R.layout.main_pagerview_listadt) {
				public void convert(ViewHolder helper, StockInfo item) {
					helper.setText(R.id.tv_stock_name, item.name);
					helper.setText(R.id.tv_stock_startp, item.open);
					helper.setText(R.id.tv_stock_minp, item.low);
					helper.setText(R.id.tv_stock_maxp, item.high);
					helper.setText(R.id.tv_stock_currentp, item.trade);
					helper.setText(R.id.tv_stock_date, item.ticktime);
				}
			});
		}
	}


	/**
	 * 百度的数据
	 * 发送网络请求拿数据
	 * @param stockID 
	 */
	private void getDataForServer(String stockID) {
		RequestParams params=new RequestParams("utf-8");
		params.addHeader("apikey","36e638ddd7bc55805a9d081953204472");
		HttpTools.sendHttp(Global.httpUrl+Global.httpArg, mActivity,params,new MyHttpListener() {
			public void finish(String response) {
				//填充listview数据
				//showLvStockInfo(parseJsonData(response));
			}
		});
	}

	/**
	 * 聚合数据
	 * 发送网络请求拿数据
	 * @param stockID 
	 */
	private void getDataForJuHeServer(int page,int type) {


		HttpTools.sendHttp(Global.httpJuhe+"?key="+Global.httpJuheKey+"&page="+page+"&type="+type, 
				mActivity,null,new MyHttpListener() {
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
	private JuHeListData parseJsonData(String response) {
		Gson gson= new Gson();
		juHeListData = gson.fromJson(response, JuHeListData.class);



		tv_itemsinfo.setText("总条目:"+juHeListData.result.totalCount+"---当前页---:"+juHeListData.result.page);


		return juHeListData;
	}


	/**
	 * 百度api自带的
	 */
	private void getDataByBaiDuApi(){
		Parameters para = new Parameters();
		para.put("city", "beijing");

		ApiStoreSDK.execute("http://apis.baidu.com/heweather/weather/free", 
				ApiStoreSDK.GET,
				para, 
				new ApiCallBack() {
			@Override
			public void onSuccess(int status, String responseString) {
				Log.i("sdkdemo", "onSuccess");
				et_stock_input.setText(responseString);
			}
			public void onComplete() {
				Log.i("sdkdemo", "onComplete");
			}
			public void onError(int status, String responseString, Exception e) {
				Log.i("sdkdemo", "onError, status: " + status);
				Log.i("sdkdemo", "errMsg: " + (e == null ? "" : e.getMessage()));
				et_stock_input.setText(e.getMessage());
			}  

		});
	}
}
