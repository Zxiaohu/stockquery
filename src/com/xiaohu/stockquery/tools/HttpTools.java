package com.xiaohu.stockquery.tools;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class HttpTools {

	
	public static void sendHttp(String url,final Context context,final RequestParams params,final MyHttpListener mHttpListener){
		HttpUtils httpUtils= new HttpUtils(6*3000);
		final ProgressDialog progressDialog= new ProgressDialog(context);
		progressDialog.setTitle("正在请求数据");
		params.addHeader("apikey","36e638ddd7bc55805a9d081953204472");
		httpUtils.send(HttpMethod.GET,url,params,new RequestCallBack<String>() {
			public void onFailure(HttpException arg0, String arg1) {
				progressDialog.dismiss();
				SystemTools.showToastInfo(context, "无法连接服务器", 2);
			}
			public void onSuccess(ResponseInfo<String> arg0) {
				progressDialog.dismiss();
				String response=arg0.result.toString();
				if(TextUtils.isEmpty(response)){
					SystemTools.showToastInfo(context, "查询到的数据有误", 2);
				}else{
					mHttpListener.finish(response);
				}
			}
			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				super.onLoading(total, current, isUploading);
				progressDialog.show();
			}
			public void onStart() {
				super.onStart();
				progressDialog.show();
			}
		});
	}
	public interface MyHttpListener{
		void finish(String response);
	}
}
