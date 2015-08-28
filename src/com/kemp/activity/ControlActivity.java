package com.kemp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ControlActivity extends Activity {
	
	private ImageButton mImageButton_back;
	
	private ImageView mImageView_cycle;
	
	private ImageView mImageView_shake;
	
	private Window mWindow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_page);
		mImageButton_back = (ImageButton) findViewById(R.id.back_iv);
		mImageButton_back.setOnClickListener(listener);
		mImageView_cycle = (ImageView) findViewById(R.id.cycle_iv);
		mImageView_shake = (ImageView) findViewById(R.id.shake_iv);
		mImageView_cycle.setOnClickListener(listener);
		mImageView_shake.setOnClickListener(listener);
		//mWindow = getWindow();
		//mWindow.setWindowAnimations(R.anim.page_in);
			
		
	}
	
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View view) {
			Intent i;
			// TODO Auto-generated method stub
			switch (view.getId()) {
			case R.id.back_iv:
				i = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(i);
				break;
				
			case R.id.cycle_iv:
			    i = new Intent(getApplicationContext(),CycleControlActivity.class);
				startActivity(i);
				break;
				
			case R.id.shake_iv:		
			    i = new Intent(getApplicationContext(),ShakeControlActivity.class);
				startActivity(i);
				break;

			default:
				break;
			}
		}
	};

}
