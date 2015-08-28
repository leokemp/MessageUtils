package com.kemp.activity;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;

import com.kemp.Dialog.Dialog_Share;
import com.kemp.Dialog.Dialog_Wait;
import com.kemp.application.MyApplication;
import com.kemp.uitls.SegmentedGroup;
import com.kemp.uitls.VibratorUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class CycleControlActivity extends Activity implements
		PlatformActionListener {

	// 启动按钮
	private ImageView btn_cycle_start;
	// 启动文字切换
	private TextView tv_cycle_start;

	private MyApplication myapp;
	// 返回按钮
	private ImageView back_iv;
	// 分享按钮
	private ImageButton ib_shape;
    //组
	private SegmentedGroup segmented_strong;
	//组
	private SegmentedGroup segmented_time;

	private RadioButton low_strong;
	private RadioButton height_strong;
	private RadioButton low_time;
	private RadioButton height_time;
	
	private TextView tv_time;
	private TextView tv_strong;
	//状态
	private static final int MSG_TOAST = 1;
	private static final int MSG_ACTION_CCALLBACK = 2;
	private static final int MSG_CANCEL_NOTIFY = 3;

	private MyHandler UIHandler;
    //等待对话框
	private Dialog_Wait mDialogtest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ShareSDK.initSDK(this);
		setContentView(R.layout.cycle_control_page);

		segmented_strong = (SegmentedGroup) findViewById(R.id.segmented_strong);
		segmented_time = (SegmentedGroup) findViewById(R.id.segmented_time);
		low_strong = (RadioButton) findViewById(R.id.low_strong);
		height_strong = (RadioButton) findViewById(R.id.height_strong);
		low_time = (RadioButton) findViewById(R.id.low_time);
		height_time = (RadioButton) findViewById(R.id.height_time);
		tv_time = (TextView) findViewById(R.id.tv_time);
		tv_strong = (TextView) findViewById(R.id.tv_strong);

		btn_cycle_start = (ImageView) findViewById(R.id.btn_cycle_start);
		btn_cycle_start.setOnClickListener(listener);
		tv_cycle_start = (TextView) findViewById(R.id.tv_cycle_start);
		back_iv = (ImageView) findViewById(R.id.back_iv);
		back_iv.setOnClickListener(listener);
		ib_shape = (ImageButton) findViewById(R.id.ib_shape);
		ib_shape.setOnClickListener(listener);
		UIHandler = new MyHandler();
		myapp = (MyApplication) getApplication();

		CheckStatus();

	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.btn_cycle_start:

				if (myapp.getisCycleIsStart()) {

					myapp.setCycleIsStart(false);
				} else {
					myapp.setCycleIsStart(true);
				}

				VibratorUtil.Vibrate(CycleControlActivity.this, 100); // ��100ms
				CheckStatus();

				break;

			case R.id.back_iv:

				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));

				break;

			case R.id.ib_shape:
				// 显示进度框
				// showShare();
				Show_SharePage();

				break;

			default:
				break;
			}

		}

		/**
		 * 分享页面
		 */
		private void Show_SharePage() {
			// TODO Auto-generated method stub
			Dialog_Share selectDialog = new Dialog_Share(
					CycleControlActivity.this, R.style.dialog_wait,
					new Dialog_Share.OnCustomDialogListener() {

						@Override
						public void back(int item) {
							// TODO Auto-generated method stub
							Log.i("Item", item + "");
							if (item == 1) {
								Share();
								mDialogtest = new Dialog_Wait(
										CycleControlActivity.this,
										R.style.loading_dialog);
								mDialogtest.show();
							}
						}
					});
			// 创建Dialog并设置样式主题
			selectDialog.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
			selectDialog.show();
			Window win = selectDialog.getWindow();
			win.getDecorView().setPadding(0, 0, 0, 0);
			win.setGravity(Gravity.BOTTOM);
			WindowManager.LayoutParams lp = win.getAttributes();
			lp.width = WindowManager.LayoutParams.FILL_PARENT;
			lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
			win.setAttributes(lp);

		}
	};

	/**
	 *  判断设备状态
	 */
	@SuppressLint("ResourceAsColor")
	private void CheckStatus() {
		if (myapp.getisCycleIsStart()) {

			tv_cycle_start.setText("正在运行");
			btn_cycle_start.setBackground(getResources().getDrawable(
					R.drawable.start_btn_icon_sl));
			Update_view(false, Color.argb(0xff, 0xcc, 0xcc, 0xcc));
		} else {

			tv_cycle_start.setText("开始");
			btn_cycle_start.setBackground(getResources().getDrawable(
					R.drawable.start_btn_icon));
			Update_view(true, Color.argb(0xff, 0x33, 0xb5, 0xe5));
		}
	}

	/**
	 * 更新界面为不可触碰
	 * @param isClickable 
	 * @param tintColor 颜色
	 */
	private void Update_view(boolean isClickable, int tintColor) {
		height_strong.setClickable(isClickable);
		height_time.setClickable(isClickable);
		low_strong.setClickable(isClickable);
		low_time.setClickable(isClickable);
		segmented_strong.setTintColor(tintColor);
		segmented_time.setTintColor(tintColor);
		tv_time.setTextColor(tintColor);
		tv_strong.setTextColor(tintColor);
	}

	/**
	 * 一键分享 测试使用
	 */
	@SuppressLint("SdCardPath")
	private void showShare() {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle(getString(R.string.share));
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://www.baidu.com");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("yao健康智能管理套件" + "http://www.baidu.com");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		oks.setImagePath("/sdcard/yao108.png");// 确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://www.baidu.com");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://www.baidu.com");

		// 启动分享GUI
		oks.show(this);
	}

	/**
	 * 微信分享
	 */
	private void Share() {
		ShareSDK.initSDK(this);
		ShareParams wechat = new ShareParams();
		wechat.setTitle("yao健康");
		wechat.setText("腰健康智能管理套件");
		wechat.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
		wechat.setUrl("http://www.baidu.com");
		wechat.setShareType(Platform.SHARE_WEBPAGE);

		Platform weixin = ShareSDK.getPlatform(CycleControlActivity.this,
				Wechat.NAME);
		weixin.setPlatformActionListener(CycleControlActivity.this);
		weixin.share(wechat);
	}

	@Override
	public void onCancel(Platform platform, int action) {
		// 取消
		Log.i("onCancel1", "onCancel");
		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 3;
		msg.arg2 = action;
		msg.obj = platform;
		UIHandler.sendMessage(msg);

	}

	@Override
	public void onComplete(Platform platform, int action,
			HashMap<String, Object> arg2) {
		// 成功
		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 1;
		msg.arg2 = action;
		msg.obj = platform;
		UIHandler.sendMessage(msg);

	}

	@Override
	public void onError(Platform platform, int action, Throwable t) {
		// 失敗
		// 打印错误信息,print the error msg
		t.printStackTrace();
		// 错误监听,handle the error msg
		Message msg = new Message();
		msg.what = MSG_ACTION_CCALLBACK;
		msg.arg1 = 2;
		msg.arg2 = action;
		msg.obj = t;
		UIHandler.sendMessage(msg);

	}

	class MyHandler extends Handler {
		public MyHandler() {
		}

		// 子类必须重写此方法,接受数据
		@Override
		public void handleMessage(Message msg) {
			switch (msg.arg1) {
			case 1: {
				// 成功
				mDialogtest.cancel();
				Log.i("onComplete", "onComplete");
				Toast.makeText(CycleControlActivity.this, "分享成功", 10000).show();
				System.out.println("分享回调成功------------");
			}
				break;
			case 2: {
				// 失败
				mDialogtest.cancel();
				Toast.makeText(CycleControlActivity.this, "分享失败", 10000).show();
			}
				break;
			case 3: {
				// 取消
				mDialogtest.cancel();
				Toast.makeText(CycleControlActivity.this, "分享取消", 10000).show();
			}
				break;
			}

		}
	}

}
