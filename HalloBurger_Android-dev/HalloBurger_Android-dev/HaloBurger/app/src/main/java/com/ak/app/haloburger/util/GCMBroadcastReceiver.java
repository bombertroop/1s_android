package com.ak.app.haloburger.util;

import android.content.Context;

public class GCMBroadcastReceiver extends
		com.google.android.gcm.GCMBroadcastReceiver {

	@Override
	protected String getGCMIntentServiceClassName(Context context) {

		return "com.ak.app.haloburger.activity.GCMIntentService";
	}
}