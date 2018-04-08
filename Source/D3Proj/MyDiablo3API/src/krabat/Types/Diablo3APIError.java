package krabat.Types;

public final class Diablo3APIError extends IDiablo3APIType 
{
	private String errorMessage;
	public String getErrorMessage()
	{
		return errorMessage;
	}

	public Diablo3APIError(String errorMessage)
	{
		super("Not defined", -1);
		
		this.errorMessage = errorMessage;
	}

	public Diablo3APIError(String battletagName, int battletagCode, String errorMessage)
	{
		super(battletagName, battletagCode);
		
		this.errorMessage = errorMessage; 
	}	
}