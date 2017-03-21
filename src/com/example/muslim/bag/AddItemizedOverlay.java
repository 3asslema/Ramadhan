package com.example.muslim.bag;
 
import java.util.ArrayList;
import java.util.Random;

import android.R.string;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
 
public class AddItemizedOverlay extends ItemizedOverlay<OverlayItem> {
 
       private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
 
       private Context context;
 
       public AddItemizedOverlay(Drawable defaultMarker) {
            super(boundCenterBottom(defaultMarker));
       }
 
       public AddItemizedOverlay(Drawable defaultMarker, Context context) {
            this(defaultMarker);
            this.context = context;
       }
 
       @Override
       protected OverlayItem createItem(int i) {
          return mapOverlays.get(i);
       }
 
       @Override
       public int size() {
          return mapOverlays.size();
       }
 
       @Override
       protected boolean onTap(int index) {
	  
    	   String non;
		
	   
    	   
    	   
    	   OverlayItem item = mapOverlays.get(index);
    	   non=item.getTitle();
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
           int randInt = new Random(5).nextInt(2) + 1;
           dialog.setTitle("Mosquee: "+non);
           dialog.setMessage("Temps d'attentes:\nFajer: 30"
           +"mm \nDhuher: 45"
        		   +" mm\nAsr: 15"
          +" mm\nMaghrib: 5"
        		   +" mm\nIcha: 20"
           +" mm");
    	    
           dialog.show();
           return true;
       }
       public void addOverlay(OverlayItem overlay) {
          mapOverlays.add(overlay);
           this.populate();
       }
       
      
    }