package edu.osu.bucketlistmatch;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SplashScreenActivity extends Activity {
	
	protected int _splashTime = 5000;
	private Thread splashThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		final SplashScreenActivity sPlashScreen = this;
			 
			        // thread for displaying the SplashScreen
			        splashThread = new Thread() {
			            @Override
			            public void run() {
			                try {                  
			                    synchronized(this){
			 
			                        wait(_splashTime);
			                    }
			 
			                } catch(InterruptedException e) {}
			                finally {
			                    finish();
			 
			                    //start a new activity
			                    Intent i = new Intent();
			                    i.setClass(sPlashScreen, LoginActivity.class);
			                    startActivity(i);
			 
			                    stop();
			                }
			            }
			        };
			 
			        splashThread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

}
