/*******************************************************************************
 * Copyright (C) 2015-2018 Andreas Redmer <andreasredmer@mailchuck.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.gitlab.ardash.appleflinger.android;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gitlab.ardash.appleflinger.ActionResolver;
import com.gitlab.ardash.appleflinger.AppleflingerGame;
import com.gitlab.ardash.appleflinger.global.GameManager;

public class AndroidLauncher extends AndroidApplication implements ActionResolver {

	protected View gameView;
	private static final String TAG = "MyActivity";

	public AndroidLauncher() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (GameManager.DEBUG)
			throw new RuntimeException("APP IS IN DEBUG MODE =========================");
		if (GameManager.DEBUGZOOM)
			throw new RuntimeException("APP IS IN DEBUG MODE =========================");
		if (GameManager.SANDBOX)
			throw new RuntimeException("APP IS IN DEBUG MODE =========================");
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;

		// Do the stuff that initialize() would do for you
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

		RelativeLayout layout = new RelativeLayout(this);
		@SuppressWarnings("static-access")
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(params);

		View localGameView = createGameView(config);
		layout.addView(localGameView);

		setContentView(layout);

		keepScreenOn(true);
		// initialize(new AppleflingerGame(this), config);
		// System.out.println("nothing");
	}

	@Override
	public void restartMySelf() {
		Intent mStartActivity = new Intent(this.getContext(), AndroidLauncher.class);
		int mPendingIntentId = 123456;
		PendingIntent mPendingIntent = PendingIntent.getActivity(this.getContext(), mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager mgr = (AlarmManager)this.getContext().getSystemService(Context.ALARM_SERVICE);
		mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
		System.exit(0);
	}

	@Override
	public void keepScreenOn(final boolean on) {
		try{
	       runOnUiThread(new Runnable() { 
	            @Override 
	            public void run() { 
	                try {
						gameView.setKeepScreenOn(on);
					} catch (Throwable t) {
						Log.e(TAG, "could not set KEEP_SCREEN_ON flag to a view", t);
					} 
	            } 
	       });
		}
		catch (Throwable t)
		{
			Log.e(TAG, "could not run Runnable on Ui thread ", t);
		}
	}

	private View createGameView(AndroidApplicationConfiguration cfg) {
		gameView = initializeForView(new AppleflingerGame(this), cfg);
		@SuppressWarnings("static-access")
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		// params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
		// RelativeLayout.TRUE);
		// params.addRule(RelativeLayout.CENTER_HORIZONTAL,
		// RelativeLayout.TRUE);
		// params.addRule(RelativeLayout.BELOW, adView.getId());
		// params.topMargin = -150;
		gameView.setLayoutParams(params);
		return gameView;
	}


	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@SuppressLint("DefaultLocale")
	@Override
	public boolean twPostRecommendation() {
		// Create intent using ACTION_VIEW and a normal Twitter url:
		String tweetUrl = String.format("https://twitter.com/intent/tweet?text=%s&url=%s", ARH.urlEncode(recommendationText),
				ARH.urlEncode(marketUrl));
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));

		// Narrow down to official Twitter app, if available:
		List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
		for (ResolveInfo info : matches) {
			if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
				intent.setPackage(info.activityInfo.packageName);
			}
		}

		startActivity(intent);
		return true;
	}

	@SuppressLint("DefaultLocale")
	@Override
	public boolean fbPostRecommendation() {
		// Create intent using ACTION_VIEW and a normal Twitter url:
		String fbUrl = String.format("https://www.facebook.com/sharer/sharer.php?u=%s", ARH.urlEncode(marketUrl));
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(fbUrl));

		// Narrow down to official Twitter app, if available:
		List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
		for (ResolveInfo info : matches) {
			if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook")) {
				intent.setPackage(info.activityInfo.packageName);
			}
		}

		startActivity(intent);
		return true;
	}

	@SuppressLint("DefaultLocale")
	@Override
	public boolean gpPostRecommendation() {
		// Create intent using ACTION_VIEW and a normal G+ url:
		String fbUrl = String.format("https://plus.google.com/share?url=%s", ARH.urlEncode(marketUrl));
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(fbUrl));

		// Narrow down to official G+ app, if available:
		List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
		for (ResolveInfo info : matches) {
			if (info.activityInfo.packageName.toLowerCase().startsWith("com.google")) {
				intent.setPackage(info.activityInfo.packageName);
			}
		}

		startActivity(intent);
		return true;
	}

	@SuppressLint("DefaultLocale")
	@Override
	public boolean piPostRecommendation() {
		// Create intent using ACTION_VIEW and a normal G+ url:
		String img = "https://lh3.googleusercontent.com/Id1_TxwfC7PmIzLf-LOjc1UR2MOuM1GiMLkIfG0o3LPoR337D7d1cSHvQzZSB6N1-A=w300";
		String piUrl = String.format("https://pinterest.com/pin/create/button/?url=%s&media=%s&description=%s", ARH.urlEncode(marketUrl),
				ARH.urlEncode(img), ARH.urlEncode(recommendationText));
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(piUrl));

		// Narrow down to official G+ app, if available:
		List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
		for (ResolveInfo info : matches) {
			if (info.activityInfo.packageName.toLowerCase().startsWith("com.pinterest")) {
				intent.setPackage(info.activityInfo.packageName);
			}
		}

		startActivity(intent);
		return true;
	}

}
