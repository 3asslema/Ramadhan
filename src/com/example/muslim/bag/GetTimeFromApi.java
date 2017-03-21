package com.example.muslim.bag;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

@SuppressLint("SimpleDateFormat")
public class GetTimeFromApi {
	private static Pray p=null ;
	
	public Pray getP() {
		return p;
	}

	public void setP(Pray p) {
		this.p = p;
	}

	public GetTimeFromApi(Context context) {
		 final Intent i;
		
		try {
			
				
			GPSTracker gps = new GPSTracker(context);
			 
          
			Log.i("p","pp");
         
         
         Calendar cal = Calendar.getInstance();
         cal.add(Calendar.DATE, 1);
         SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
         String date = format1.format(cal.getTime());
         Log.i("pazsazsa",date);
         final int year=Integer.parseInt(date.substring(0,4));
         final int month=Integer.parseInt(date.substring(5,7));
         final int day=Integer.parseInt(date.substring(8,10));
        // Log.i("pazsazsa",gps.getLocation()+"");
         
         Log.i("pazsazsa",day+"");

         
			final URL url = new URL("http://api.xhanch.com/islamic-get-prayer-time.php?lng=10.493481&lat=33.348419&yy="+year+"&mm="+month+"&gmt=1");
			
			Log.i("pazsazsa",url+"");
			
			Thread thread=new Thread() {

		        @Override
		        public void start() {
		            // do something in the actual (old) thread
		        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = null;
					try {
						db = dbf.newDocumentBuilder();
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Document doc = null;
					try {
						doc = db.parse(new InputSource(url.openStream()));
					} catch (SAXException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					doc.getDocumentElement().normalize();
					
					
						
						 
						p = new Pray();
						int d=day;
						p.setDay(day);
						p.setYear(year);
						p.setMonth(month);

						NodeList nodeList = doc.getElementsByTagName("d-" + d);
						Element element = (Element) nodeList.item(0);
						

						NodeList fajrList = element.getElementsByTagName("fajr");
						Element fajrElement = (Element) fajrList.item(0);
						fajrList = fajrElement.getChildNodes();
						p.setFajr(((Node) fajrList.item(0)).getNodeValue());
						
			              // sending data to new activity
			           

						NodeList sunriseList = element.getElementsByTagName("sunrise");
						Element sunriseElement = (Element) sunriseList.item(0);
						sunriseList = sunriseElement.getChildNodes();
						p.setSunrise(((Node) sunriseList.item(0)).getNodeValue());

						NodeList zuhrList = element.getElementsByTagName("zuhr");
						Element zuhrElement = (Element) zuhrList.item(0);
						zuhrList = zuhrElement.getChildNodes();
						p.setZuhr(((Node) zuhrList.item(0)).getNodeValue());

						NodeList asrList = element.getElementsByTagName("asr");
						Element asrElement = (Element) asrList.item(0);
						asrList = asrElement.getChildNodes();
						p.setAsr(((Node) asrList.item(0)).getNodeValue());

						NodeList maghribList = element.getElementsByTagName("maghrib");
						Element maghribElement = (Element) maghribList.item(0);
						maghribList = maghribElement.getChildNodes();
						p.setMaghrib(((Node) maghribList.item(0)).getNodeValue());

						NodeList ichaList = element.getElementsByTagName("isha");
						Element ichaElement = (Element) ichaList.item(0);
						ichaList = ichaElement.getChildNodes();
						p.setIcha(((Node) ichaList.item(0)).getNodeValue());
						Log.i("not",p.getAsr());
		        }

		        @Override
		        public void run() {
		        	
						  
				           
		            // do something in a new thread if 'called' by super.start()
		        }
		    };
			
			thread.start();

					} catch (Exception e) {
			System.out.println("XML Pasing Excpetion = " + e);
		}
	}

}
