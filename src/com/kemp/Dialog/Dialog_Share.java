package com.kemp.Dialog;

import com.kemp.Dialog.Dialog_startpage.OnCustomDialogListener;
import com.kemp.activity.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class Dialog_Share extends Dialog {

	private Button btn_WeChat_Share;
	private Button btn_WeiBo_Share;
	private Button btn_Back;

	private ImageView iv_WeChat_Share;
	private ImageView iv_WeiBo_Share;
	private ImageView iv_Back;

	private int item_id;

	private OnCustomDialogListener customDialogListener;

	public Dialog_Share(Context context, int theme,
			OnCustomDialogListener customDialogListener) {
		super(context, theme);
		this.customDialogListener = customDialogListener;
		// TODO Auto-generated constructor stub
	}

	public Dialog_Share(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}

	public Dialog_Share(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public interface OnCustomDialogListener {
		public void back(int item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_share);
		btn_WeChat_Share = (Button) findViewById(R.id.btn_WeChat_Share);
		btn_WeiBo_Share = (Button) findViewById(R.id.btn_WeiBo_Share);
		btn_Back = (Button) findViewById(R.id.btn_Back);
		iv_WeChat_Share = (ImageView) findViewById(R.id.iv_WeChat_Share);
		iv_WeiBo_Share = (ImageView) findViewById(R.id.iv_WeiBo_Share);
		iv_Back = (ImageView) findViewById(R.id.iv_Back);

		btn_WeChat_Share.setOnClickListener(clickListener);
		btn_WeiBo_Share.setOnClickListener(clickListener);
		btn_Back.setOnClickListener(clickListener);
		iv_WeChat_Share.setOnClickListener(clickListener);
		iv_WeiBo_Share.setOnClickListener(clickListener);
		iv_Back.setOnClickListener(clickListener);
		Window mWindow = getWindow();
		mWindow.setWindowAnimations(R.style.dialog_animation);

	}

	private View.OnClickListener clickListener = new View.OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub

			switch (view.getId()) {
			case R.id.btn_WeChat_Share:
				item_id = 1;
				break;
			case R.id.iv_WeChat_Share:
				item_id = 1;
				break;
			case R.id.btn_WeiBo_Share:
				item_id = 2;
				break;
			case R.id.iv_WeiBo_Share:
				item_id = 2;
				break;
			default:
				item_id = 3;
				break;
			}
			customDialogListener.back(item_id);
			Dialog_Share.this.dismiss();

		}
	};

}
