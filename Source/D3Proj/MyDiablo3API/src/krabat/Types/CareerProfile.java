package krabat.Types;

import java.util.ArrayList;
import java.util.List;

public final class CareerProfile extends IDiablo3APIType
{
	private int paragonLevel;
	private int paragonLevelHardcore;
	private String battleTag;
	
	private List<HeroProfile> heroes = new ArrayList<HeroProfile>();
	
	/*
	public CareerProfile(String battletagName, int battletagCode) {
		super(battletagName, battletagCode);
		// TODO Auto-generated constructor stub
	}
	*/

	public CareerProfile(String battletagName, int battletagCode, List<HeroProfile> heroes)
	{
		super(battletagName, battletagCode);
		
		this.heroes = heroes;
		this.paragonLevel = -1;
		this.paragonLevelHardcore = -1;
		this.battleTag = null;
	}	
	
	/*
	public CareerProfile(List<HeroProfile> heroes, int paragonLevel)
	{
		this.heroes = heroes;
		this.paragonLevel = paragonLevel;
		this.paragonLevelHardcore = -1;
		this.battleTag = null;
	}	
	public CareerProfile(List<HeroProfile> heroes, int paragonLevel, int paragonLevelHardcore)
	{
		this.heroes = heroes;
		this.paragonLevel = paragonLevel;
		this.paragonLevelHardcore = paragonLevelHardcore;
		this.battleTag = null;
	}
	*/
	public CareerProfile(String battletagName, int battletagCode, List<HeroProfile> heroes, int paragonLevel, int paragonLevelHardcore, String battleTag)
	{
		super(battletagName, battletagCode);
		
		this.heroes = heroes;
		this.paragonLevel = paragonLevel;
		this.paragonLevelHardcore = paragonLevelHardcore;
		this.battleTag = battleTag;
	}
	
	public List<HeroProfile> getHeroes()
	{
		return heroes;
	}
	
	public int getParagonLevel()
	{
		return this.paragonLevel;
	}

	public int getParagonLevelHardcore()
	{
		return this.paragonLevelHardcore;
	}
	
	public String getBattleTag()
	{
		return battleTag;
	}
}