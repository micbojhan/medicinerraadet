
package com.example.temaprojekt4.GoogleCalendar;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GC_Category implements Serializable{
	@SerializedName("scheme")
   	private String scheme;
	@SerializedName("term")
   	private String term;

 	public String getScheme(){
		return this.scheme;
	}
	public void setScheme(String scheme){
		this.scheme = scheme;
	}
 	public String getTerm(){
		return this.term;
	}
	public void setTerm(String term){
		this.term = term;
	}
}
