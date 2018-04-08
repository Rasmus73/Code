package krabat.D3API;

import java.net.URL;

import krabat.Types.Diablo3APIRequestEnum;

public interface IDownloadTask
{
	// This method runs an AsyncTask and calls supplied Callback object's methods when done
	//public void getRemoteData(URL url, IDownloadListener downloadListener);
	//public void getRemoteData(URL url, Diablo3APIRequestEnum requestEnum, final IDownloadListener downloadListener);
	public void getRemoteData(URL url, final String battletagName, final int battletagCode, final Diablo3APIRequestEnum requestEnum, final IDownloadListener downloadListener);

	// This method just processes input string locally and returns it
	//public String doStuff(String input);			
}
