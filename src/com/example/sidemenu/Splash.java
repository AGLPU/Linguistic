package com.example.sidemenu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

public class Splash extends Activity {
	public static int SPLASH_TIME_OUT=1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		// Get the app's shared preferences
	    SharedPreferences app_preferences = 
	        PreferenceManager.getDefaultSharedPreferences(this);

	   
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent i=new Intent(Splash.this,MainActivity.class);
				startActivity(i);
				finish();
				
				
			}
		}, SPLASH_TIME_OUT);
	}


}
