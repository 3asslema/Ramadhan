package com.example.muslim.bag;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;


public class MainActivity extends Activity implements OnClickListener,LocationListener {
	ImageView time, mosquee, koran;

	Location location;
	double longitude=10.495117,latitude=33.350265;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		time = (ImageView) findViewById(R.id.Time);
		time.setOnClickListener(this);
		
		mosquee = (ImageView) findViewById(R.id.NearestMosque);
		mosquee.setOnClickListener(this);
		
		koran = (ImageView) findViewById(R.id.Koran);
		koran.setOnClickListener(this);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "Settings");
		return true;
	}

	@Override
		public void onClick(View arg0) {
		switch (arg0.getId()) {
	
		case R.id.Time:
			Intent intentTime = new Intent(MainActivity.this,TimeActivity.class);
			startActivity(intentTime);
			break;
		case R.id.NearestMosque:
		
	
	Intent intent=new Intent(MainActivity.this,MosqueeActivity.class);
			intent.putExtra("latitude",latitude);
			intent.putExtra("longitude",longitude);
			startActivity(intent);
			break;
		case R.id.Koran:
			Intent intentKoran = new Intent(MainActivity.this, ListKoran.class);
			startActivity(intentKoran);
			break;
		}
		}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

	Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
		return true;
	}

	@Override
	public void onLocationChanged(Location arg0) {
		longitude = location.getLongitude();
		latitude = location.getLatitude();
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}
	
	 }
