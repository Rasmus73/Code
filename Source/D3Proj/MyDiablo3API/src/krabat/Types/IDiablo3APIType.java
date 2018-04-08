package krabat.Types;

public abstract class IDiablo3APIType 
{
	private String battletagName = "";
	private int battletagCode = -1;
	
	//public IDiablo3APIType() {}

	public IDiablo3APIType(String battletagName, int battletagCode)
	{
		this.battletagName = battletagName;
		this.battletagCode = battletagCode;
	}
	
	//public Diablo3APIRequestEnum D3APIType = null;
	public String getBattletagName()
	{
		return this.battletagName;
	}
	
	public int getBattletagCode()
	{
		return this.battletagCode;
	}
	
		
}