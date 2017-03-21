package com.example.muslim.bag;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class TimeActivity extends Activity {

	TextView fajr, sunrise, zuhr, asr, maghrib, icha;
	//Pray p;
	GetTimeFromApi getTimeFromApi; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time);
		
		fajr = (TextView) findViewById(R.id.fajr);
		sunrise = (TextView) findViewById(R.id.sunrise);
		zuhr = (TextView) findViewById(R.id.zuhr);
		asr = (TextView) findViewById(R.id.asr);
		maghrib = (TextView) findViewById(R.id.maghrib);
		icha = (TextView) findViewById(R.id.icha);
		
		getTimeFromApi=new GetTimeFromApi(this);
	
		Pray prayday= getTimeFromApi.getP();
		
	
		//String f=extras.getString("fajr");
		 //fajr.setText   ("Fajer:       "+f);
		if (prayday!=null){
        fajr.setText   ("Fajer:       "+prayday.getFajr());
		sunrise.setText("Sunrise:   "+prayday.getSunrise());
		zuhr.setText   ("Zuhr:       "+prayday.getZuhr());
		asr.setText    ("Asr:          "+prayday.getAsr());
		maghrib.setText("Maghrib: "+prayday.getMaghrib());
		icha.setText   ("Icha:        "+prayday.getIcha());
		
		}
		
		}
		
	
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_time, menu);
		return true;
	}

}
