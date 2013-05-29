
package com.example.temaprojekt4.GoogleCalendar;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GC_Author{
	@SerializedName("email")
   	private GC_Email email;
	@SerializedName("name")
   	private GC_Name name;

 	public GC_Email getEmail(){
		return this.email;
	}
	public void setEmail(GC_Email email){
		this.email = email;
	}
 	public GC_Name getName(){
		return this.name;
	}
	public void setName(GC_Name name){
		this.name = name;
	}
}
