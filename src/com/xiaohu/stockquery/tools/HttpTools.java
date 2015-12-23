package com.xiaohu.stockquery.tools;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class HttpTools {
	public static String RUL="http://hq.sinajs.cn/";
	public static void sendHttp(String url,final Context context,final MyHttpListener mHttpListener){
		HttpUtils httpUtils= new HttpUtils(6*3000);
		httpUtils.send(HttpMethod.POST,url,new RequestCallBack<String>() {
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(context, "请求失败", 3000).show();
			}
			public void onSuccess(ResponseInfo<String> arg0) {
				String response=arg0.result.toString();
				if(TextUtils.isEmpty(response)){
					Toast.makeText(context, "请求的数据有误", 3000).show();
				}else{
					mHttpListener.finish(response);
					
				}
			}
		});
	}
	public interface MyHttpListener{
		void finish(String response);
	}
}
