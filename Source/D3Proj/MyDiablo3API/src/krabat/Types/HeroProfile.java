package krabat.Types;

import java.util.List;
import java.util.Locale;

public final class HeroProfile extends IDiablo3APIType
{
	public HeroProfile()
	{
		super("Not defined", -1);
	}

	public HeroProfile(String battletagName, int battletagCode)
	{
		super(battletagName, battletagCode);
	}
	
	public enum Gender
	{
		Male,
		Female
	}
	
	private int id;
	private String name;
	private String heroClass;
	private int level;	
	private Gender gender;
	private boolean hardcore;
	
	private List<HeroSkill> heroSkills;

	public List<HeroSkill> getHeroSkills()
	{
		return heroSkills;
	}
	public void setHeroSkills(List<HeroSkill> heroSkills)
	{
		this.heroSkills = heroSkills;
	}

	public boolean isHardcore()
	{
		return hardcore;
	}
	public void setHardcore(boolean hardcore)
	{
		this.hardcore = hardcore;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return this.id;
	}	
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;	
	}
	
	public void setHeroClass(String heroClass)
	{
		this.heroClass = heroClass;
	}
	public String getHeroClass()
	{
		return this.heroClass;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	public int getLevel()
	{
		return this.level;
	}
		
	public void setGender(int gender)
	{
		switch(gender)
		{
			case 0:
				this.gender = Gender.Male;
				break;			
			case 1:
				this.gender = Gender.Female;
				break;
		}
	}
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}	
	public Gender getGender()
	{
		return this.gender;
	}
	
	@Override
	public String toString()
	{
		Locale locale = new Locale("da", "dk");
		String str = String.format(locale, "Id: %d - Name: %s - Class: %s", this.id, this.name, this.heroClass);
		return str;
	}
}