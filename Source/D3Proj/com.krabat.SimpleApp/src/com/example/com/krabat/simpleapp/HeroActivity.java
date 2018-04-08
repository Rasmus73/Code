package com.example.com.krabat.simpleapp;

import java.util.List;

import krabat.D3API.IDownloadListener;
import krabat.D3API.ServiceProxy;
import krabat.Types.CareerProfile;
import krabat.Types.Diablo3APIError;
import krabat.Types.Diablo3APIRequestEnum;
import krabat.Types.HeroProfile;
import krabat.Types.HeroProfile.Gender;
import krabat.Types.IDiablo3APIType;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class HeroActivity extends Activity implements IDownloadListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hero);
		
		Intent intent = getIntent();
		String battletagName = intent.getStringExtra(HeroListActivity.BATTLETAGNAME_MESSAGE);
		int battletagCode = intent.getIntExtra(HeroListActivity.BATTLETAGCODE_MESSAGE, -1);
		int heroId = intent.getIntExtra(HeroListActivity.HEROID_MESSAGE, -1);
		
		if(heroId != -1)
		{		
			ServiceProxy serviceProxy = new ServiceProxy("http://eu.battle.net");
			serviceProxy.GetHeroProfile(battletagName, battletagCode, heroId, this);
		}
	}

	@Override
	public void onDownloadCompleted(Diablo3APIRequestEnum requestEnum, IDiablo3APIType diablo3APIType)
	{
		if(diablo3APIType instanceof HeroProfile)
		{
			HeroProfile heroProfile = (HeroProfile)diablo3APIType;
			Gender gender = heroProfile.getGender();
			String name = heroProfile.getName();
			
			Context context = getApplicationContext();
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, name, duration);
			toast.show();
			
			
//			ArrayAdapter<HeroProfile> adapter = new ArrayAdapter<HeroProfile>(HeroListActivity.this, android.R.layout.simple_list_item_1, heroes);
//			this.setListAdapter(adapter);
		}
		else if(diablo3APIType instanceof Diablo3APIError)
		{
			Diablo3APIError error = (Diablo3APIError)diablo3APIType;
		}
		else
		{
			//TODO: throw some error. 
		}

		
	
	}
}
