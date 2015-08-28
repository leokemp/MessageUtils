package com.kemp.activity;

import com.kemp.activity.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class StartPage extends Activity {
	
	private ImageView logo;
	
	private TextView logo_tv;
	
	private Animation appAnim;
	
	private Animation tv_Anim;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.startpage);
	logo = (ImageView) findViewById(R.id.logo_iv);  
	logo_tv = (TextView) findViewById(R.id.logo_tv);
	appAnim = AnimationUtils.loadAnimation(this, R.anim.set);  
	tv_Anim = AnimationUtils.loadAnimation(this, R.anim.fistpage_enter);
	
	
	appAnim.setAnimationListener(new AnimationListener() {  
	  
	    @Override  
	    public void onAnimationStart(Animation animation) {  
	        // TODO Auto-generated method stub  
	  
	    }  
	  
	    @Override  
	    public void onAnimationRepeat(Animation animation) {  
	        // TODO Auto-generated method stub  
	  
	    }  
	  
	    @Override  
	    public void onAnimationEnd(Animation animation) {  
	        // TODO Auto-generated method stub  
	        Intent i = new Intent(getApplicationContext(),  
	                MainActivity.class);  
	        startActivity(i);  
	        int version = Integer.valueOf(android.os.Build.VERSION.SDK);
            if(version  >= 5) {     
               overridePendingTransition(R.anim.activity_enter, R.anim.activity_exit); 
              //overridePendingTransition(android.R.anim.decelerate_interpolator,android.R.anim.decelerate_interpolator);    
                 //overridePendingTransition(android.R.anim.overshoot_interpolator,android.R.anim.linear_interpolator);  
            }    
            StartPage.this.finish();
	    }  
	});  
	logo.startAnimation(appAnim); 
	logo_tv.startAnimation(tv_Anim)
;	
}
	
}
