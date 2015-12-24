package com.xiaohu.stockquery.model;

import java.util.ArrayList;


/**
 * 
 * @author zxh
 * 
 * 请求股票信息的所有数据结果
 * 
 */
public class StockQueryResultInfo {
	public String errMsg;
	public String errNum;
	public retData retData;
	
	public class retData{
		public ArrayList<Stockinfo> stockinfo;
	}
	public class Stockinfo{
		public String closingPrice;
		public String code;
		public String currentPrice;
		public String date;
		public String dealnumber;
		public String growth;
		public String growthPercent;
		public String hPrice;
		public String lPrice;
		public String name;
		public String OpenningPrice;
		public String turnover;
	}
}
