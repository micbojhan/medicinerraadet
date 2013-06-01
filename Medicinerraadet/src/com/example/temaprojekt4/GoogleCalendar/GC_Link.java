package com.example.temaprojekt4.GoogleCalendar;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GC_Link implements Serializable{
	@SerializedName("rel")
   	private String rel;
	@SerializedName("type")
   	private String type;
	@SerializedName("href")
   	private String href;

 	public String getRel(){
		return this.rel;
	}
	public void setRel(String rel){
		this.rel = rel;
	}
	
 	public String getHref(){
		return this.href;
	}
	public void setHref(String href){
		this.href = href;
	}
	
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
}
