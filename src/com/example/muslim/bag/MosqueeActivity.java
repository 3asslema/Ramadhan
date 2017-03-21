
package com.example.muslim.bag;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MosqueeActivity extends MapActivity  {
    public static final String TAG = "GoogleMapsActivity";
    private MapView mapView;
    private LocationManager locationManager;
    double lat,log;
    Geocoder geocoder;
    Location location;
    GeoPoint geoPt=null;
    private MapView map;
	ArrayList<Mosque>listMosque;
	MapController mapControl;

  
    LocationListener locationListener;
    CountDownTimer locationtimer;
    MapController mapController;
    MapOverlay mapOverlay = new MapOverlay();

    @Override
    protected void onCreate(Bundle icicle) {
    	
    	
        super.onCreate(icicle);
        setContentView(R.layout.activity_mosquee);
        initComponents();
        mapView.setBuiltInZoomControls(true);
       
        mapController = mapView.getController();
        mapController.setZoom(16);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        
        creerTous();
        
        
        mapView.setOnTouchListener(new  OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
       
    				
					creerTous();
  
            
    				return false;
    			}
    		});
        locationListener = new LocationListener() {
            @Override
            public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            }

            @Override
            public void onProviderEnabled(String arg0) {
            }

            @Override
            public void onProviderDisabled(String arg0) {
            }

            @Override
            public void onLocationChanged(Location l) {
                location = l;
                locationManager.removeUpdates(this);
                if (l.getLatitude() == 0 || l.getLongitude() == 0) {
                } else {
                    double lat = l.getLatitude();
                    double lng = l.getLongitude();
                    Toast.makeText(MosqueeActivity.this,"Location Are" + lat + ":" + lng,Toast.LENGTH_SHORT).show();
                }
            }
        };
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 1000, 10f, locationListener);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 1000, 10f, locationListener);
        locationtimer = new CountDownTimer(30000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (location != null)
                    locationtimer.cancel();
            }

            @Override
            public void onFinish() {
                if (location == null) {
                }
            }
        };
        locationtimer.start();
    }

    public MapView getMapView() {
        return this.mapView;
    }

    private void initComponents() {
        mapView = (MapView) findViewById(R.id.mapView);
    }

    protected boolean isRouteDisplayed1() {
        return false;
    }

    class MapOverlay extends Overlay {
        private GeoPoint pointToDraw;

        public void setPointToDraw(GeoPoint point) {
            pointToDraw = point;
        }

        public GeoPoint getPointToDraw() {
            return pointToDraw;
        }

        @Override
        public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
                long when) {
            super.draw(canvas, mapView, shadow);

            Point screenPts = new Point();
            mapView.getProjection().toPixels(pointToDraw, screenPts);

            Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.indice);
            canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 24, null);
            return true;
        }
        
        
    }
    public void creerTous(){
    	if (locationManager == null) 
        Toast.makeText(MosqueeActivity.this,"GPS non Activer!!!", Toast.LENGTH_SHORT).show();
     
   
    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
   if (location == null){
	   
	   
	   
	   
       map=(MapView)findViewById(R.id.mapView);
       map.setBuiltInZoomControls(true);
       mapControl=map.getController();
      lat=this.getIntent().getExtras().getDouble("latitude");
      log=this.getIntent().getExtras().getDouble("longitude");

       URL url; 
       DecimalFormat df = new DecimalFormat("#.######");
			try {   url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location="+lat+","+log+"&radius=2000&types=mosque&sensor=true&key=AIzaSyCbM7hr1THqc0oF1cS1nnKsDojSpiC7N-U");
				
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					
					Document doc = db.parse(new InputSource(url.openStream()));
				
					doc.getDocumentElement().normalize();
					NodeList nodeList = doc.getElementsByTagName("result");
					
					listMosque=new ArrayList<Mosque>();
					for (int i = 0; i < nodeList.getLength(); i++) {
						Mosque m=new Mosque();
				        Element e = (Element) nodeList.item(i);
				        Node node = nodeList.item(i);
				      
				        Element fstElmnt = (Element) node;
				        
				        //Noeud Nom
				        NodeList nameList = fstElmnt.getElementsByTagName("name");
				        Element nameElement = (Element) nameList.item(0);
				        nameList = nameElement.getChildNodes();
				        m.setNom(((Node) nameList.item(0)).getNodeValue());
				        
				        //Noeud Rue
				        NodeList rueList = fstElmnt.getElementsByTagName("vicinity");
				        Element rueElement = (Element) rueList.item(0);
				        rueList = rueElement.getChildNodes();
				        m.setRue(((Node) rueList.item(0)).getNodeValue());
				        
				      //Noeud Latitude
				        NodeList latList = fstElmnt.getElementsByTagName("lat");
				        Element latElement = (Element) latList.item(0);
				        latList = latElement.getChildNodes();
				       m.setLat( Double.parseDouble(((Node) latList.item(0)).getNodeValue()));
				        
				      //Noeud Longitude
				        NodeList lngList = fstElmnt.getElementsByTagName("lng");
				        Element lngElement = (Element) lngList.item(0);
				        lngList = lngElement.getChildNodes();
				        m.setLog(Double.parseDouble(((Node) lngList.item(0)).getNodeValue()));
				        listMosque.add(m);
				        Log.i("Nbr=",""+i);
						}
					
		        for(int i=0;i<listMosque.size();i++){ 
		        	geoPt=new GeoPoint((int)(Double.parseDouble(df.format( listMosque.get(i).getLat()))*1E6),(int)(Double.parseDouble(df.format( listMosque.get(i).getLog()))*1E6));
		        	
		        	List<Overlay> mapOverlays = map.getOverlays();
		        	Drawable drawable = MosqueeActivity.this.getResources().getDrawable(R.drawable.mosque);
			        AddItemizedOverlay itemizedOverlay =new AddItemizedOverlay(drawable, MosqueeActivity.this);
			       
			        OverlayItem overlayitem = new OverlayItem(geoPt,listMosque.get(i).getNom(),listMosque.get(i).getRue());
			       
			        itemizedOverlay.addOverlay(overlayitem);
				    mapOverlays.add(itemizedOverlay);
				    mapView.setSatellite(true);
 
		        } 
				
		        mapControl.animateTo(geoPt);
			   mapControl.setZoom(15);
				map.invalidate();
				Log.i("coordonnees: ",""+geoPt);	
			}
					catch(Exception ex){}
		       
		      
		        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
					
		        	  @Override 
		  		    public void onLocationChanged(Location location) {    
		  		     Date today = new Date();  
		  		     Timestamp currentTimeStamp = new Timestamp(today.getTime());  
		  		        if (location != null) {
		  		           
		  		        	int l=(int)(location.getLatitude()*1E6);
		  		        	int lg=(int)(location.getLongitude()*1E6);
		  		        	//Toast.makeText(MosqueeActivity.this,"on location "+l+"\n"+lg, 2000).show();
		  		        	reload(lat,log);
		  		        	
		       
		  		        		
		  		        } 
		  		    }

					@Override
					public void onProviderDisabled(String provider) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onProviderEnabled(String provider) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {
						// TODO Auto-generated method stub
						
					} 
		  		   		  		   
				});   
       }
		        
		    
		}
		
			@Override
			protected boolean isRouteDisplayed() {
				// TODO Auto-generated method stub
				return false;
			}

			public void reload(double lat,double log) {
					
				    Intent intent = getIntent();
		        	intent.putExtra("latitude",lat);
					intent.putExtra("longitude",log);
				    overridePendingTransition(0, 0);
				    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				    finish();

				    overridePendingTransition(0, 0);
				    startActivity(intent);
				    Log.i("lat: ",""+geoPt.getLatitudeE6());
				    Log.i("log: ",""+geoPt.getLatitudeE6());
   

       
   
   if (location != null)
    {
      lat = location.getLatitude();
      log = location.getLongitude();
        //lat=33.348419;
       //log=10.493481;
       Toast.makeText(MosqueeActivity.this,"Location Are" + lat + ":" + log, Toast.LENGTH_SHORT).show();
        URL url; 
        DecimalFormat df = new DecimalFormat("#.######");           
        GeoPoint point = new GeoPoint((int) (lat * 1E6), (int) (log * 1E6));
        mapController.animateTo(point, new Message());
        mapOverlay.setPointToDraw(point);
        List<Overlay> listOfOverlays = mapView.getOverlays();
        listOfOverlays.clear();
        listOfOverlays.add(mapOverlay);
               
        //https://maps.googleapis.com/maps/api/place/search/xml?location="+lat+","+log+"&radius=500&types=mosque&&sensor=false&key=AIzaSyCbM7hr1THqc0oF1cS1nnKsDojSpiC7N-U
        
        try {   
        url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/xml?location="+lat+","+log+"&radius=2000&types=mosque&sensor=true&key=AIzaSyCJi-l_8kF-sWmIK7MspYFbHwGJO55PCS8");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		Document doc = db.parse(new InputSource(url.openStream()));
	
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName("result");
		listMosque=new ArrayList<Mosque>();
		
		
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Mosque m=new Mosque();
	        Element e = (Element) nodeList.item(i);
	        Node node = nodeList.item(i);
	      
	        Element fstElmnt = (Element) node;
	        
	        //Noeud Nom
	        NodeList nameList = fstElmnt.getElementsByTagName("name");
	        
	        Element nameElement = (Element) nameList.item(0);
	        nameList = nameElement.getChildNodes();
	        m.setNom(((Node) nameList.item(0)).getNodeValue());
	        Log.i("name",m.getNom());
	        //Noeud Rue
	        NodeList rueList = fstElmnt.getElementsByTagName("vicinity");
	        Element rueElement = (Element) rueList.item(0);
	        rueList = rueElement.getChildNodes();
	        m.setRue(((Node) rueList.item(0)).getNodeValue());
	        
	      //Noeud Latitude
	        NodeList latList = fstElmnt.getElementsByTagName("lat");
	        Element latElement = (Element) latList.item(0);
	        latList = latElement.getChildNodes();
	       m.setLat( Double.parseDouble(((Node) latList.item(0)).getNodeValue()));
	        
	      //Noeud Longitude
	        NodeList lngList = fstElmnt.getElementsByTagName("lng");
	        Element lngElement = (Element) lngList.item(0);
	        lngList = lngElement.getChildNodes();
	        m.setLog(Double.parseDouble(((Node) lngList.item(0)).getNodeValue()));
	        listMosque.add(m);
	        
	
	        
	        
			}
		Log.i("Nbr=",""+listMosque.size());
		for(int i=0;i<listMosque.size();i++){ 
        	geoPt=new GeoPoint((int)(Double.parseDouble(df.format( listMosque.get(i).getLat()))*1E6),(int)(Double.parseDouble(df.format( listMosque.get(i).getLog()))*1E6));
        	
        	List<Overlay> mapOverlays = map.getOverlays();
        	
        	Drawable drawable = MosqueeActivity.this.getResources().getDrawable(R.drawable.mosque);
	        AddItemizedOverlay itemizedOverlay =new AddItemizedOverlay(drawable, MosqueeActivity.this);
	       
	        OverlayItem overlayitem = new OverlayItem(geoPt,listMosque.get(i).getNom(),listMosque.get(i).getRue());
	       
	        itemizedOverlay.addOverlay(overlayitem);
		    mapOverlays.add(itemizedOverlay);
		    Log.i("Nbr=",""+i);
		    
		    

        } 

    for(int i=0;i<listMosque.size();i++){ 
    	geoPt=new GeoPoint((int)(Double.parseDouble(df.format( listMosque.get(i).getLat()))*1E6),(int)(Double.parseDouble(df.format( listMosque.get(i).getLog()))*1E6));
    	
       	Drawable drawable = MosqueeActivity.this.getResources().getDrawable(R.drawable.mosque);
       	Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.mosque);
       	AddItemizedOverlay itemizedOverlay =new AddItemizedOverlay(drawable, MosqueeActivity.this);
       
        OverlayItem overlayitem = new OverlayItem(geoPt,listMosque.get(i).getNom(),listMosque.get(i).getRue());
        mapController.animateTo(geoPt);
        itemizedOverlay.addOverlay(overlayitem);
        listOfOverlays.add(itemizedOverlay);
        Log.i("lat: ",""+geoPt.getLatitudeE6()); 
	    Log.i("log: ",""+geoPt.getLongitudeE6());
        mapOverlay.setPointToDraw(geoPt);
        
        
 } 
}
		catch(Exception ex){
			
			} }
   }}