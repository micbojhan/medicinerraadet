package com.example.temaprojekt4.GoogleCalendar;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GCal_timesCleaned implements Serializable{
	@SerializedName("value")
   	private String value;

 	public String getValue(){
		return this.value;
	}
	public void setValue(String value){
		this.value = value;
	}
}
