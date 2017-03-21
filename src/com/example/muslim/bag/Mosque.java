package com.example.muslim.bag;

public class Mosque {
	String nom;
	String rue;
	double lat,log;
	Mosque()
	{
		nom="";
		rue="";
		lat=0;
		log=0;
	}
	Mosque(String n,String r,double l,double lg)
	{
		nom=n;
		rue=r;
		lat=l;
		log=lg;
		
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLog() {
		return log;
	}
	public void setLog(double log) {
		this.log = log;
	}}
