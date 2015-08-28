package com.kemp.Dialog;

import com.kemp.activity.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Dialog_Wait extends Dialog {

	//һ��layout����loading_dialog.xml  һ��������תloading_animation.xml  һ��Dialog��ʽ styles.xml 
	//һ����תͼƬ�뱳��ͼƬ
	
	private Context mContext;
	
	public Dialog_Wait(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}
	
	public Dialog_Wait(Context context, int theme) {
		super(context, theme);
		this.mContext = context;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_wait);
		LinearLayout layout = (LinearLayout) findViewById(R.id.dialog_view);// ���ز���  
        // main.xml�е�ImageView  
        ImageView spaceshipImage = (ImageView) findViewById(R.id.img);  
        TextView tipTextView = (TextView) findViewById(R.id.tipTextView);// ��ʾ����  
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(  
        		mContext, R.anim.loading_animation);  
        // ʹ��ImageView��ʾ����  
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);  
        //tipTextView.setText("请稍等");// ���ü�����Ϣ  
		
	}




}
