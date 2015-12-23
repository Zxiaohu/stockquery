package com.xiaohu.stockquery;


import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.xiaohu.stockquery.fragment.base.BasePager;
import com.xiaohu.stockquery.fragment.pager.MainPager;
import com.xiaohu.stockquery.fragment.pager.MorePager;
import com.xiaohu.stockquery.view.MyViewPager;
import android.os.Bundle;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener{

	@ViewInject(R.id.vp_main)
	private MyViewPager vp_main;

	@ViewInject(R.id.tv_main)
	private TextView tv_mian;

	@ViewInject(R.id.tv_more)
	private TextView tv_more;

	private int mMainTextColor;
	private int mBackColor;
	private List<BasePager> mPagers = new ArrayList<BasePager>();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		//初始化资源信息
		initResources();       
		//初始化点击事件
		initEvents(tv_mian,tv_more);
		//初始化数据
		initData();
		
		//viewpager相关操作
		setViewPager();
		
	}
	
	private void setViewPager() {
		//设置适配器
		vp_main.setAdapter(MyPageAdapter);
		//设置监听事件
		vp_main.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int arg0) {
				mPagers.get(arg0).initData();//初始化数据
			}
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		mPagers.get(0).initData();//默认初始化数据
	}

	private void initData() {
		BasePager mbasePager= new MainPager(this);
		mPagers.add(mbasePager);
		BasePager basePager= new MorePager(this);
		mPagers.add(basePager);
	}

	PagerAdapter MyPageAdapter = new PagerAdapter() {
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		public int getCount() {
			return mPagers.size();
		}
		public Object instantiateItem(ViewGroup container, int position) {
			View view=mPagers.get(position).mRootView;
			container.addView(view);
			return view;
		};
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		};
	} ;
	private void initEvents(View ...views) {	
		for (int i = 0; i < views.length; i++) {
			if(views[i] instanceof TextView){
				views[i].setOnClickListener(this);
			}
		}
	}
	private void initResources() {
		Resources resources = getResources();
		mMainTextColor=resources.getColor(R.color.blue);
		mBackColor=resources.getColor(R.color.normal);
	}

	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.tv_main:
			setColor(new View[]{tv_mian,tv_more}, new int[]{mBackColor,mBackColor});
			setColor(tv_mian, mMainTextColor);
			
			vp_main.setCurrentItem(0,false);
			
			break;
		case R.id.tv_more:
			setColor(new View[]{tv_mian,tv_more}, new int[]{mBackColor,mBackColor});
			setColor(tv_more, mMainTextColor);

			vp_main.setCurrentItem(1,false);
			
			break;
		default:
			break;
		}
	}
	
	//设置颜色的事件
	private void setColor(View []views,int []colors){
		for (int i = 0; i < colors.length; i++) {
			if(views[i] instanceof TextView){
				((TextView)views[i]).setTextColor(colors[i]);
			}
		}
	}
	
	private void setColor(View view,int color){
		if(view instanceof TextView){
			((TextView)view).setTextColor(color);
		}
	}
}
