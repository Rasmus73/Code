package krabat.Types;

public class HeroSkill
{
	public enum SkillType
	{
		unknown,
		passive, 
		active
	}		
	
	private SkillType skillType;
	private String name;
	private String description;
	private SkillRune skillRune;
		
	public SkillRune getSkillRune()
	{
		return skillRune;
	}
	public void setSkillRune(SkillRune skillRune)
	{
		this.skillRune = skillRune;
	}
	
	public SkillType getSkillType()
	{
		return skillType;
	}
	public void setSkillType(SkillType skillType)
	{
		this.skillType = skillType;
	}
	public void setSkillType(String skillType)
	{
		if(skillType == "active")
		{
			this.skillType = SkillType.active;
		}
		else if(skillType == "passive")
		{
			this.skillType = SkillType.passive;
		}
		else
		{
			this.skillType = SkillType.unknown;
		}
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
}
/*
"skill":{
    "slug":"spirit-barrage",
    "name":"Spirit Barrage",
    "icon":"witchdoctor_spiritbarrage",
    "level":17,
    "categorySlug":"decay",
    "tooltipUrl":"skill/witch-doctor/spirit-barrage",
    "description":"Cost: 100 Mana\r\n\r\nBombard an enemy with 4 spirit bolts deal a total of 425% weapon damage as Cold.",
    "simpleDescription":"Cost: 100 Mana\r\n\r\nUnleash a spirit blast that can travel long distances and over some obstacles.",
    "skillCalcId":"g"
 },
*/