package com.example.temaprojekt4.GoogleCalendar;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GD_When{
	@SerializedName("endTime")
   	private String endTime;
	@SerializedName("startTime")
   	private String startTime;

 	public String getEndTime(){
		return this.endTime;
	}
	public void setEndTime(String endTime){
		this.endTime = endTime;
	}
	
 	public String getStartTime(){
		return this.startTime;
	}
	public void setStartTime(String startTime){
		this.startTime = startTime;
	}
}
