package krabat.D3API;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import krabat.Types.CareerProfile;
import krabat.Types.Diablo3APIError;
import krabat.Types.HeroProfile;;

public class Converter
{
	public static Diablo3APIError GetError(Exception ex)
	{		
		Diablo3APIError diablo3APIError = new Diablo3APIError(ex.getMessage());
		
		return diablo3APIError;
	}	

	public static Diablo3APIError GetError(String battletagName, int battletagCode, Exception ex)
	{		
		Diablo3APIError diablo3APIError = new Diablo3APIError(battletagName, battletagCode, ex.getMessage());
		
		return diablo3APIError;
	}	
	
	// TODO : implement remaining properties.
	public static CareerProfile GetCareerProfileFromJSON(String battletagName, int battletagCode, String json)
	{
		try
		{
			List<HeroProfile> heroes = new ArrayList<HeroProfile>();
			
			JSONObject root = new JSONObject(json);
			JSONArray jsonHeroes = root.getJSONArray("heroes");

			for(int ix = 0; ix < jsonHeroes.length(); ix++)
			{
				HeroProfile hero = new HeroProfile(battletagName, battletagCode);
				JSONObject jsonHero = jsonHeroes.getJSONObject(ix);
				
				hero.setId(jsonHero.getInt("id"));
				hero.setName(jsonHero.getString("name"));
				hero.setHeroClass(jsonHero.getString("class"));
				hero.setLevel(jsonHero.getInt("level"));
				hero.setGender(jsonHero.getInt("gender"));
				
				heroes.add(hero);
			}
			
			int jsonParagonLevel = root.getInt("paragonLevel");
			int jsonParagonLevelHardcore = root.getInt("paragonLevelHardcore");
			String jsonBattleTag = root.getString("battleTag");
						
			CareerProfile careerProfile = new CareerProfile(battletagName, battletagCode, heroes, jsonParagonLevel, jsonParagonLevelHardcore, jsonBattleTag);
			return careerProfile;			
		}
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	// TODO : implement remaining properties.
	public static HeroProfile GetHeroProfileFromJSON(String json)
	{
		try
		{
			JSONObject root = new JSONObject(json);
					
			HeroProfile hero = new HeroProfile();
			hero.setId(root.getInt("id"));
			hero.setName(root.getString("name"));
			hero.setHeroClass(root.getString("class"));
			hero.setLevel(root.getInt("level"));
			hero.setGender(root.getInt("gender"));
			
			return hero;
		}
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}