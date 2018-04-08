package krabat.D3API;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;

import android.util.Log;

import krabat.Types.Diablo3APIRequestEnum;

public class ServiceProxy
{ 	
	//private static final String NEWLINE = System.getProperty("line.separator").toString();
	private static final String PATH = "api/d3/profile";
	
    private String host;

	public ServiceProxy(String host)
    {
        this.host = host;
    }	
	
	private void AsyncServiceCall(String address, String battletagName, int battletagCode, Diablo3APIRequestEnum requestEnum, IDownloadListener downloadListener) throws IOException
	{		
		Log.d("", "");
				
		URL url = new URL(address);
	    DownloadTask dl = new DownloadTask();
	    dl.getRemoteData(url, battletagName, battletagCode, requestEnum, downloadListener);
		
	    return;
	}

	public void GetCareerProfile(String battletagName, int battletagCode, IDownloadListener downloadListener)
	{
		//From http://blizzard.github.io/d3-api-docs/ :
		//battletag-name ::= <regional battletag allowed characters>
		//battletag-code ::= <integer>
		//url ::= <host> "/api/d3/profile/" <battletag-name> "-" <battletag-code> "/"
		String address = String.format(Locale.US, "%s/%s/%s-%d/", host, PATH, battletagName, battletagCode);		

		try
		{
			AsyncServiceCall(address, battletagName, battletagCode, Diablo3APIRequestEnum.CareerProfile, downloadListener);
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}
	
	public void GetHeroProfile(String battletagName, int battletagCode, int heroId, IDownloadListener downloadListener)
	{
		//From http://blizzard.github.io/d3-api-docs/ :
		//battletag-name ::= <regional battletag allowed characters>
		//battletag-code ::= <integer>
		//hero-id ::= <integer>
		//url ::= <host> "/api/d3/profile/" <battletag-name> "-" <battletag-code> "/hero/" <hero-id>
		String address = String.format(Locale.US, "%s/%s/%s-%d/hero/%d", host, PATH, battletagName, battletagCode, heroId);

		try
		{
			AsyncServiceCall(address, battletagName, battletagCode, Diablo3APIRequestEnum.HeroProfile, downloadListener);
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}
	
}