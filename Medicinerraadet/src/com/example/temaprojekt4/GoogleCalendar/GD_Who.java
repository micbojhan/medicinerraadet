package com.example.temaprojekt4.GoogleCalendar;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class GD_Who implements Serializable{
	@SerializedName("valueString")
   	private String valueString;
	@SerializedName("rel")
   	private String rel;
	@SerializedName("email")
   	private String email;

 	public String getValueString(){
		return this.valueString;
	}
	public void setValueString(String valueString){
		this.valueString = valueString;
	}
	
 	public String getRel(){
		return this.rel;
	}
	public void setRel(String rel){
		this.rel = rel;
	}
	
 	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email = email;
	}
}
