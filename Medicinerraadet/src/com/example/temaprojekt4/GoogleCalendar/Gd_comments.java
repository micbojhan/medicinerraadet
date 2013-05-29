package com.example.temaprojekt4.GoogleCalendar;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Gd_comments{
	@SerializedName("gd$feedLink")
   	private GD_feedLink gd_feedLink;
   	
 	public GD_feedLink getGD_feedLink(){
		return this.gd_feedLink;
	}
	public void setGD_feedLink(GD_feedLink gd_feedLink){
		this.gd_feedLink = gd_feedLink;
	}

}
