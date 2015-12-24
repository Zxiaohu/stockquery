package com.xiaohu.stockquery.tools;
import android.util.Log;

/**
 * 日志类
 * @author zxh
 *
 */
public class LogUtil {
	public static final int V=1;
	public static final int D=2;
	public static final int I=3;
	public static final int W=4;
	public static final int E=5;
	public static final int NOTING=6;
	public static final int LEVEL=V;
	
	public static void  v(String tag,String msg){
		if(LEVEL<=V){
			Log.v(tag, msg);
		}
	}
	public static void  d(String tag,String msg){
		if(LEVEL<=D){
			Log.d(tag, msg);
		}
	}
	public static void  i(String tag,String msg){
		if(LEVEL<=I){
			Log.i(tag, msg);
		}
	}
	public static void  w(String tag,String msg){
		if(LEVEL<=W){
			Log.w(tag, msg);
		}
	}
	public static void  e(String tag,String msg){
		if(LEVEL<=E){
			Log.e(tag, msg);
		}
	}
}
