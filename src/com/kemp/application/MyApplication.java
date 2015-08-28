package com.kemp.application;

import android.app.Application;

public class MyApplication extends Application {

	private boolean CycleIsStart = false;

	private boolean ShakeIsStart = false;

	public boolean getisCycleIsStart() {
		return CycleIsStart;
	}

	public void setCycleIsStart(boolean cycleIsStart) {
		CycleIsStart = cycleIsStart;
	}

	public boolean getisShakeIsStart() {
		return ShakeIsStart;
	}

	public void setShakeIsStart(boolean shakeIsStart) {
		ShakeIsStart = shakeIsStart;
	}

}
