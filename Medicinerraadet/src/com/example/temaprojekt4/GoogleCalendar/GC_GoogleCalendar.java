
package com.example.temaprojekt4.GoogleCalendar;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GC_GoogleCalendar implements Serializable{
	@SerializedName("encoding")
   	private String encoding;
	@SerializedName("feed")
   	private GC_Feed feed;
	@SerializedName("version")
   	private String version;

 	public String getEncoding(){
		return this.encoding;
	}
	public void setEncoding(String encoding){
		this.encoding = encoding;
	}
 	public GC_Feed getFeed(){
		return this.feed;
	}
	public void setFeed(GC_Feed feed){
		this.feed = feed;
	}
 	public String getVersion(){
		return this.version;
	}
	public void setVersion(String version){
		this.version = version;
	}
}
