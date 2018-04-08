package com.example.com.krabat.simpleapp;

import java.util.List;

import krabat.D3API.IDownloadListener;
import krabat.D3API.ServiceProxy;
import krabat.Types.CareerProfile;
import krabat.Types.Diablo3APIError;
import krabat.Types.Diablo3APIRequestEnum;
import krabat.Types.HeroProfile;
import krabat.Types.IDiablo3APIType;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HeroListActivity extends ListActivity implements IDownloadListener
{
	public final static String BATTLETAGNAME_MESSAGE = "com.example.com.krabt.simpleapp.BATTLETAGNAME_MESSAGE";
	public final static String BATTLETAGCODE_MESSAGE = "com.example.com.krabt.simpleapp.BATTLETAGCODE_MESSAGE";
	public final static String HEROID_MESSAGE = "com.example.com.krabt.simpleapp.HEROID_MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_hero_list);
		
		ListView lv = getListView();
        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) 
	        {
        	    Intent intent = new Intent(getApplicationContext(), HeroActivity.class);
        	    
        	    HeroProfile hero = (HeroProfile)parent.getAdapter().getItem(position);
        	    
        	    // Send info to heroactivity
        	    intent.putExtra(BATTLETAGNAME_MESSAGE, hero.getBattletagName());
        	    intent.putExtra(BATTLETAGCODE_MESSAGE, hero.getBattletagCode());
        	    intent.putExtra(HEROID_MESSAGE, hero.getId());
        	    
        	    startActivity(intent);
	        }
	    });
        
/*        
        setOnItemClickListener(new OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
               
        	  /*
              // selected item 
              String product = ((TextView) view).getText().toString();
               
              // Launching new Activity on selecting single List Item
              Intent i = new Intent(getApplicationContext(), SingleListItem.class);
              // sending data to new activity
              i.putExtra("product", product);
              startActivity(i);
             */
//          }
//        }

		ServiceProxy serviceProxy = new ServiceProxy("http://eu.battle.net");
		serviceProxy.GetCareerProfile("krabat", 2146, this);
	}

	@Override
	public void onDownloadCompleted(Diablo3APIRequestEnum requestEnum, IDiablo3APIType diablo3APIType)
	{	
		if(diablo3APIType instanceof Diablo3APIError)
		{
			Diablo3APIError error = (Diablo3APIError)diablo3APIType;
		}
		else if(diablo3APIType instanceof CareerProfile)
		{
			CareerProfile careerProfile = (CareerProfile)diablo3APIType;
			List<HeroProfile> heroes = careerProfile.getHeroes();		
			
			ArrayAdapter<HeroProfile> adapter = new ArrayAdapter<HeroProfile>(HeroListActivity.this, android.R.layout.simple_list_item_1, heroes);
			this.setListAdapter(adapter);
		}
	}
}
