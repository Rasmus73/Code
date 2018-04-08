package krabat.Types;

public class SkillRune
{
	private String name;
	private String description;
	
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
"rune":{
    "slug":"spirit-barrage-b",
    "type":"b",
    "name":"Well of Souls",
    "level":32,
    "description":"An additional 3 spirits seek out other enemies and deal 65% weapon damage as Cold.",
    "simpleDescription":"Additional spirits seek out and damage nearby enemies.",
    "tooltipParams":"rune/spirit-barrage/b",
    "skillCalcId":"Z",
    "order":1
 }
*/