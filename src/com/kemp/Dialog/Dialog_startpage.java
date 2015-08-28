package com.kemp.Dialog;

import com.kemp.activity.R;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Dialog_startpage extends Dialog {

	
	private Animation appAnim;
	
	private ImageView iv_start_page;

	public Dialog_startpage(Context context, int theme, 
			OnCustomDialogListener customDialogListener) {
		super(context, theme);
		appAnim = AnimationUtils.loadAnimation(context, R.anim.startpage_move); 
		this.customDialogListener = customDialogListener;
	}

	public interface OnCustomDialogListener {

		public void back();
	}

	private OnCustomDialogListener customDialogListener;
	
	private Button btn_cancel;


	
	public float getDensity(Context context) {
		Resources res = context.getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		return dm.density;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_startpage);
		iv_start_page = (ImageView) findViewById(R.id.iv_start_page);
		
		btn_cancel = (Button) findViewById(R.id.btn_cancel);
		btn_cancel.setOnClickListener(clickListener);
		iv_start_page.startAnimation(appAnim);
		
		
	}
	
	private View.OnClickListener clickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			customDialogListener.back();
			Dialog_startpage.this.dismiss();
			
		}
	};

}
