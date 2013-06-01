package com.example.temaprojekt4.GoogleCalendar;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GC_Subtitle implements Serializable{
	@SerializedName("$t")
   	private String $t;
	@SerializedName("type")
   	private String type;
   	
   	
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}

 	public String get$t(){
		return this.$t;
	}
	public void set$t(String $t){
		this.$t = $t;
	}
}
