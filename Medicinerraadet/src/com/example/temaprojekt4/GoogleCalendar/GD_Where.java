package com.example.temaprojekt4.GoogleCalendar;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GD_Where{
	@SerializedName("valueString")
   	private String valueString;

 	public String getValueString(){
		return this.valueString;
	}
	public void setValueString(String valueString){
		this.valueString = valueString;
	}
}
