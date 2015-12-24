package com.xiaohu.stockquery.model;

import java.util.ArrayList;

public class JuHeListData {
	public String error_code;
	public String reason;
	public result result;
	
	public class result{
		public ArrayList<StockInfo> data;
		public String num;
		public String page;
		public String totalCount;
	} 
	
	public class StockInfo{
		public String trade;
		public String name;
		public String open;
		public String low;
		public String high;
		public String ticktime;
	}
}
