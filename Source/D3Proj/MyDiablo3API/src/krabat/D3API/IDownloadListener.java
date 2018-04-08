package krabat.D3API;

import krabat.Types.Diablo3APIRequestEnum;
import krabat.Types.IDiablo3APIType;

public interface IDownloadListener 
{
	public void onDownloadCompleted(Diablo3APIRequestEnum requestEnum, IDiablo3APIType diablo3APIType);
}