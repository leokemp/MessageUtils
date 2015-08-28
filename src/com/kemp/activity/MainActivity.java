package com.kemp.activity;

import java.util.ArrayList;
import java.util.List;

import com.kemp.Adapter.BitmapAdapter;
import com.kemp.Adapter.DeviceAdapter;
import com.kemp.Dialog.Dialog_startpage;
import com.kemp.entity.DeviceData;
import com.kemp.activity.R;
import com.kemp.application.MyApplication;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements
		OnPageChangeListener {

	/**
	 * 顶部图片切换
	 */
	private ViewPager viewPager;

	/**
	 * 顶部图片切换指示点组
	 */
	private ImageView[] tips;

	/**
	 * 顶部图片数组
	 */
	private ImageView[] mImageViews;

	/**
	 * 存储图片
	 */
	private int[] imgIdArray;
	/*
	 * 设备列表
	 */
	private ListView mListView;

	/**
	 * 设备存储数组
	 */
	private List<DeviceData> device_datas = new ArrayList<DeviceData>();
	/**
	 * 设备数据
	 */
	private DeviceData device_data = null;
	/**
	 * 设备适配器
	 */
	private DeviceAdapter mDeviceAdapter = null;
	/**
	 * 主界面按钮
	 */
	private ImageButton home_ib;
	/**
	 * 侧滑框菜单
	 */
	private ResideMenu resideMenu;
	private ResideMenuItem itemHome;
	private ResideMenuItem itemProfile;
	private ResideMenuItem itemSettings;
	private ResideMenuItem itemAbout;
	private ResideMenuItem itemHelp;
	/**
	 * 存储全局变量
	 */
	private MyApplication myapp;

	private Animation appAnim;
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myapp = (MyApplication) getApplication();
		appAnim = AnimationUtils.loadAnimation(this, R.anim.startpage_move); 
		isStart_Time();
		Item_init();
		DeviceList_Init();
		View_Ground_Init();
		btn_init();

	}

	/**
	 * 图片浏览测试化
	 */
	private void View_Ground_Init() {
		// TODO Auto-generated method stub
		ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
		viewPager = (ViewPager) findViewById(R.id.viewPager);

		// ����ͼƬ��ԴID
		imgIdArray = new int[] { R.drawable.item01, R.drawable.item02,
				R.drawable.item03, R.drawable.item04, R.drawable.item05 };

		// �������뵽ViewGroup��
		tips = new ImageView[imgIdArray.length];
		for (int i = 0; i < tips.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(20, 20));
			tips[i] = imageView;
			if (i == 0) {
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
			}

			group.addView(imageView);
		}

		// ��ͼƬװ�ص�������
		mImageViews = new ImageView[imgIdArray.length];
		for (int i = 0; i < mImageViews.length; i++) {
			ImageView imageView = new ImageView(this);
			mImageViews[i] = imageView;
			imageView.setBackgroundResource(imgIdArray[i]);
		}

		// ����Adapter
		viewPager.setAdapter(new BitmapAdapter(mImageViews));
		// ���ü�����Ҫ�����õ��ı���
		viewPager.setOnPageChangeListener(this);
		// ����ViewPager��Ĭ����, ����Ϊ���ȵ�100���������ӿ�ʼ�������󻬶�
		viewPager.setCurrentItem((mImageViews.length) * 100);

		// add gesture operation's ignored views
		FrameLayout ignored_view = (FrameLayout) findViewById(R.id.ignored_view);
		resideMenu.addIgnoredView(ignored_view);
	}

	/**
	 * 设备列表初始化
	 */
	private void DeviceList_Init() {
		// TODO Auto-generated method stub
		mListView = (ListView) findViewById(R.id.Device_lv);
		for (int i = 0; i <= 10; i++) {
			device_data = new DeviceData();
			device_data.setAdress("adress" + i);
			device_data.setIcon(getResources().getDrawable(R.drawable.device));
			device_datas.add(device_data);
		}
		mDeviceAdapter = new DeviceAdapter(this, device_datas);
		mListView.setAdapter(mDeviceAdapter);
		mListView.setOnItemClickListener(listener);
	}

	/**
	 * 判断APP启动次数
	 */
	private void isStart_Time() {
		// TODO Auto-generated method stub
		Boolean isFirstIn = false;
		SharedPreferences pref = this.getSharedPreferences("myActivityName", 0);
		// ֵ存储APP启动次数
		isFirstIn = pref.getBoolean("isFirstIn", true);
		if (isFirstIn) {
			DialogShow();
			Editor editor = pref.edit();
			editor.putBoolean("isFirstIn", false);
			editor.commit();
		}
		
		//DialogShow();

	}

	/**
	 * 侧滑菜单初始化
	 */
	private void Item_init() {
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.menu_background1);
		resideMenu.attachToActivity(this);
		resideMenu.setScaleValue(0.6f);
		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

		// create menu items;
		itemHome = new ResideMenuItem(this, R.drawable.icon_home, "我的服务");
		itemProfile = new ResideMenuItem(this, R.drawable.icon_profile, "我的设备");

		itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "设置");

		itemAbout = new ResideMenuItem(this, R.drawable.icon_about, "意见反馈");

		itemHelp = new ResideMenuItem(this, R.drawable.icon_help, "帮助");

		itemHome.setOnClickListener(menu_listener);
		itemProfile.setOnClickListener(menu_listener);
		itemSettings.setOnClickListener(menu_listener);
		itemAbout.setOnClickListener(menu_listener);
		itemHelp.setOnClickListener(menu_listener);

		resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemAbout, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemHelp, ResideMenu.DIRECTION_LEFT);

	}

	private void DialogShow() {
		// TODO Auto-generated method stub
		Dialog_startpage dialog = new Dialog_startpage(this,
				R.style.CustomDialog,
				new Dialog_startpage.OnCustomDialogListener() {

					@Override
					public void back() {
						// TODO Auto-generated method stub

					}
				});
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.CENTER);
		// lp.x = 100;
		lp.y = 130; // ��λ��Y���
		// lp.width = 300; // ���
		// lp.height = 300; // �߶�
		// lp.alpha = 1f; // ͸����
		dialogWindow.setAttributes(lp);
		
		dialog.show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onPageSelected(int arg0) {
		setImageBackground(arg0 % mImageViews.length);
	}

	/**
	 * ����ѡ�е�tip�ı���
	 * 
	 * @param selectItems
	 */
	private void setImageBackground(int selectItems) {
		for (int i = 0; i < tips.length; i++) {
			if (i == selectItems) {
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	OnItemClickListener listener = new OnItemClickListener() {
		public void onItemClick(android.widget.AdapterView<?> arg0,
				android.view.View arg1, int arg2, long arg3) {
			Log.i("TAG", "tag" + arg2);
			if (1 == arg2) {
				Intent i = new Intent(getApplicationContext(),
						ControlActivity.class);
				startActivity(i);
			}
		};
	};

	OnClickListener menu_listener = new OnClickListener() {

		@Override
		public void onClick(View view) {

			Log.i("Click", view.getId() + "");
			// TODO Auto-generated method stub
			// if (view == itemHome){
			// changeFragment(new HomeFragment());
			// }else if (view == itemProfile){
			// changeFragment(new ProfileFragment());
			// }else if (view == itemCalendar){
			// changeFragment(new CalendarFragment());
			// }else if (view == itemSettings){
			// changeFragment(new SettingsFragment());
			// }

			resideMenu.closeMenu();

		}
	};

	private void btn_init() {
		home_ib = (ImageButton) findViewById(R.id.home_ib);
		home_ib.setOnClickListener(home_listener);
	}

	OnClickListener home_listener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);

		}
	};

}
