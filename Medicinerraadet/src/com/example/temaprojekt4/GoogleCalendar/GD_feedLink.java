package com.example.temaprojekt4.GoogleCalendar;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GD_feedLink implements Serializable{
	@SerializedName("href")
   	private String href;
   	
 	public String getHref(){
		return this.href;
	}
	public void setHref(String href){
		this.href = href;
	}

}
