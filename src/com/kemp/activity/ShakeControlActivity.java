package com.kemp.activity;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;

import com.kemp.application.MyApplication;
import com.kemp.uitls.SegmentedGroup;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ShakeControlActivity extends Activity {

	//返回按钮
	private ImageView back_iv;
	//启动按钮
	private ImageView btn_shake_start;
	//启动文字切换
	private TextView tv_shake_start;
	//静态成员变量
	private MyApplication myapp;
	//单选按钮组
	private SegmentedGroup  segmented_strong;
	//分享按钮
	private ImageButton ib_share;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ShareSDK.initSDK(this);
		setContentView(R.layout.shake_control_page);
		

		back_iv = (ImageView) findViewById(R.id.back_iv);
		back_iv.setOnClickListener(listener);
		btn_shake_start = (ImageView) findViewById(R.id.btn_shake_start);
		btn_shake_start.setOnClickListener(listener);
		tv_shake_start = (TextView) findViewById(R.id.tv_shake_start);
		segmented_strong = (SegmentedGroup) findViewById(R.id.segmented_strong);
		ib_share = (ImageButton) findViewById(R.id.ib_share);
		ib_share.setOnClickListener(listener);
		
		myapp = (MyApplication) getApplication();
		CheckStatus();
	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.btn_shake_start:

				if (myapp.getisShakeIsStart()) {
					myapp.setShakeIsStart(false);
				} else {
					myapp.setShakeIsStart(true);
				}
				CheckStatus();

				break;

			case R.id.back_iv:

				startActivity(new Intent(getApplicationContext(),
						ControlActivity.class));
				break;
				
			case R.id.ib_share:
				//Share();
				//showShare();
			default:
				break;
			}

		}
	};

	@SuppressLint("NewApi")
	private void CheckStatus() {
		if (myapp.getisShakeIsStart()) {
			btn_shake_start.setBackground(getResources().getDrawable(
					R.drawable.start_btn_icon_sl));
			tv_shake_start.setText("正在运行");
			
			 for(int i=0; i<segmented_strong.getChildCount(); i++){  
		            RadioButton r = (RadioButton)segmented_strong.getChildAt(i);  
		            if(r.isChecked()){  
		            	Log.i("ID", r.getText()+"");
		                break;  
		            }  
		        }  
			
			
		} else {
			btn_shake_start.setBackground(getResources().getDrawable(
					R.drawable.start_btn_icon));
			tv_shake_start.setText("启动");
		}
	}
	/*
	 * 分享
	 */
	
	@SuppressLint("SdCardPath")
	private void showShare() {
		 ShareSDK.initSDK(this);
		 OnekeyShare oks = new OnekeyShare();
		 //关闭sso授权
		 oks.disableSSOWhenAuthorize(); 
		 
		// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
		 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
		 // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		 oks.setTitle(getString(R.string.share));
		 // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		 oks.setTitleUrl("http://sharesdk.cn");
		 // text是分享文本，所有平台都需要这个字段
		 oks.setText("我是分享文本");
		 // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		 oks.setImagePath("/sdcard/yao108.png");//确保SDcard下面存在此张图片
		 // url仅在微信（包括好友和朋友圈）中使用
		 oks.setUrl("http://sharesdk.cn");
		 // comment是我对这条分享的评论，仅在人人网和QQ空间使用
		 oks.setComment("我是测试评论文本");
		 // site是分享此内容的网站名称，仅在QQ空间使用
		 oks.setSite(getString(R.string.app_name));
		 // siteUrl是分享此内容的网站地址，仅在QQ空间使用
		 oks.setSiteUrl("http://sharesdk.cn");
		 
		// 启动分享GUI
		 oks.show(this);
		 }
	
	private void Share(){
		ShareSDK.initSDK(this);
		ShareParams wechat = new ShareParams();
		wechat.setTitle("yao健康");	
		wechat.setText("腰健康智能管理套件");
		//wechat.setImagePath("/sdcard/yao108.png");
		wechat.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
		wechat.setUrl("http://www.baidu.com");
		wechat.setShareType(Platform.SHARE_WEBPAGE);

		Platform weixin = ShareSDK.getPlatform(ShakeControlActivity.this, Wechat.NAME);
		//weixin.setPlatformActionListener(WXEntryActivity.this);
		weixin.share(wechat);
	}

}
