
package com.example.temaprojekt1.MedicinNews;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class MedicinNews implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("entries")
   	private ArrayList<Entries> entries;
	@SerializedName("icon")
   	private String icon;
	@SerializedName("link")
   	private String link;
	@SerializedName("self")
   	private String self;
	@SerializedName("title")
   	private String title;
	@SerializedName("updated")
   	private String updated;

 	public ArrayList<Entries> getEntries(){
		return this.entries;
	}
	public void setEntries(ArrayList<Entries> entries){
		this.entries = entries;
	}
 	public String getIcon(){
		return this.icon;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
 	public String getLink(){
		return this.link;
	}
	public void setLink(String link){
		this.link = link;
	}
 	public String getSelf(){
		return this.self;
	}
	public void setSelf(String self){
		this.self = self;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public String getUpdated(){
		return this.updated;
	}
	public void setUpdated(String updated){
		this.updated = updated;
	}
}
