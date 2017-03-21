package com.example.muslim.bag;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;

public class ListKoran extends ListActivity {
@Override
public void onCreate(Bundle savedInstanceState) {
		
super.onCreate(savedInstanceState);
    	  
        String[]koran_souar=getResources().getStringArray(R.array.koran_souar);
        
        // Binding resources Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_list_koran, R.id.label,koran_souar));
        ListView lv = getListView();
        
        // listening to single list item on click
          lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
 
              // selected item
              String soura = ((TextView) view).getText().toString();
                 // Launching new Activity on selecting single List Item
             Intent i = new Intent(getApplicationContext(),Listen_koran.class);
              // sending data to new activity
             Log.i("position",position+"");
             	i.putExtra("pos",position+"");
             	i.putExtra("soura", soura);
             
              startActivity(i);
 
          }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.activity_list_koran, menu);
        return true;
    }
   
}  
