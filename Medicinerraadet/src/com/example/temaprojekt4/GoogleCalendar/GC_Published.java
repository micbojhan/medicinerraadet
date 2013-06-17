package com.example.temaprojekt4.GoogleCalendar;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class GC_Published implements Serializable{
	@SerializedName("$t")
   	private String $t;

 	public String get$t(){
		return this.$t;
	}
	public void set$t(String $t){
		this.$t = $t;
	}
}
