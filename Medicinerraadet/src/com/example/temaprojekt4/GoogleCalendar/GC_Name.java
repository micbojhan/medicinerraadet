
package com.example.temaprojekt4.GoogleCalendar;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GC_Name implements Serializable{
	@SerializedName("$t")
   	private String $t;

 	public String get$t(){
		return this.$t;
	}
	public void set$t(String $t){
		this.$t = $t;
	}
}
