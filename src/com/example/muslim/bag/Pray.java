package com.example.muslim.bag;

import java.io.Serializable;

public class Pray implements Serializable{

	String fajr,sunrise,zuhr,asr,maghrib,icha;
	int day,month,year;
	String lat,log;
 
 
	public Pray(){}


	public Pray(String fajr, String sunrise, String zuhr, String asr,
			String maghrib, String icha, int day, int month, int year,
			String lat, String log) {
		super();
		this.fajr = fajr;
		this.sunrise = sunrise;
		this.zuhr = zuhr;
		this.asr = asr;
		this.maghrib = maghrib;
		this.icha = icha;
		this.day = day;
		this.month = month;
		this.year = year;
		this.lat = lat;
		this.log = log;
	}


	public String getFajr() {
		return fajr;
	}


	public void setFajr(String fajr) {
		this.fajr = fajr;
	}


	public String getSunrise() {
		return sunrise;
	}


	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}


	public String getZuhr() {
		return zuhr;
	}


	public void setZuhr(String zuhr) {
		this.zuhr = zuhr;
	}


	public String getAsr() {
		return asr;
	}


	public void setAsr(String asr) {
		this.asr = asr;
	}


	public String getMaghrib() {
		return maghrib;
	}


	public void setMaghrib(String maghrib) {
		this.maghrib = maghrib;
	}


	public String getIcha() {
		return icha;
	}


	public void setIcha(String icha) {
		this.icha = icha;
	}


	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getLat() {
		return lat;
	}


	public void setLat(String lat) {
		this.lat = lat;
	}


	public String getLog() {
		return log;
	}


	public void setLog(String log) {
		this.log = log;
	}
	
}
