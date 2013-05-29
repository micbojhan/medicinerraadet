
package com.example.temaprojekt4.GoogleCalendar;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GC_Entry{
	@SerializedName("id")
	private GC_Id id;
	@SerializedName("publisher")
	private GC_Published published;
	@SerializedName("uptated")
	private GC_Updated updated;
	@SerializedName("category")
	private List<GC_Category> category;
	@SerializedName("title")
	private GC_Title title;
	@SerializedName("content")
	private GC_Content content;
	@SerializedName("link")
	private List<GC_Link> link;
	@SerializedName("author")
   	private List<GC_Author> author;
   	
   	@SerializedName("gd$comments")
   	private Gd_comments gd_comments;
   	@SerializedName("gd$eventStatus")
   	private Gd_eventStatus gd_eventStatus;
   	@SerializedName("gd$where")
   	private List<GD_Where> gd_where;
   	@SerializedName("gd$originalEvent")
   	private Gd_originalEvent gd_originalEvent;
   	@SerializedName("gd$who")
   	private List<GD_Who> gd_who;
   	@SerializedName("gd$when")
   	private List<GD_When> gd_when;
   	
   	@SerializedName("gd$transparency")
   	private Gd_transparency gd_transparency;
   	@SerializedName("gCal$anyoneCanAddSelf")
   	private GCal_anyoneCanAddSelf gCal_anyoneCanAddSelf;
   	@SerializedName("gCal$guestsCanInviteOthers")
   	private GCal_guestsCanInviteOthers gCal_guestsCanInviteOthers;
   	@SerializedName("gCal$guestsCanModify")
   	private GCal_guestsCanModify gCal_guestsCanModify;
   	@SerializedName("gCal$guestsCanSeeGuests")
   	private GCal_guestsCanSeeGuests gCal_guestsCanSeeGuests;
   	@SerializedName("gCal$sequence")
   	private GCal_sequence gCal_sequence;
   	@SerializedName("gCal$uid")
   	private GCal_uid gCal_uid;
   	

 	public List<GC_Author> getAuthor(){
		return this.author;
	}
	public void setAuthor(List<GC_Author> author){
		this.author = author;
	}
 	public List<GC_Category> getCategory(){
		return this.category;
	}
	public void setCategory(List<GC_Category> category){
		this.category = category;
	}
 	public GC_Content getContent(){
		return this.content;
	}
	public void setContent(GC_Content content){
		this.content = content;
	}
 	public GCal_anyoneCanAddSelf getGCal_anyoneCanAddSelf(){
		return this.gCal_anyoneCanAddSelf;
	}
	public void setGCal_anyoneCanAddSelf(GCal_anyoneCanAddSelf gCal_anyoneCanAddSelf){
		this.gCal_anyoneCanAddSelf = gCal_anyoneCanAddSelf;
	}
 	public GCal_guestsCanInviteOthers getGCal_guestsCanInviteOthers(){
		return this.gCal_guestsCanInviteOthers;
	}
	public void setGCal_guestsCanInviteOthers(GCal_guestsCanInviteOthers gCal_guestsCanInviteOthers){
		this.gCal_guestsCanInviteOthers = gCal_guestsCanInviteOthers;
	}
 	public GCal_guestsCanModify getGCal$guestsCanModify(){
		return this.gCal_guestsCanModify;
	}
	public void setGCal$guestsCanModify(GCal_guestsCanModify gCal_guestsCanModify){
		this.gCal_guestsCanModify = gCal_guestsCanModify;
	}
 	public GCal_guestsCanSeeGuests getGCal_guestsCanSeeGuests(){
		return this.gCal_guestsCanSeeGuests;
	}
	public void setGCal$guestsCanSeeGuests(GCal_guestsCanSeeGuests gCal_guestsCanSeeGuests){
		this.gCal_guestsCanSeeGuests = gCal_guestsCanSeeGuests;
	}
 	public GCal_sequence getGCal_sequence(){
		return this.gCal_sequence;
	}
	public void setGCal$sequence(GCal_sequence gCal_sequence){
		this.gCal_sequence = gCal_sequence;
	}
 	public GCal_uid getGCal_uid(){
		return this.gCal_uid;
	}
	public void setGCal$uid(GCal_uid gCal_uid){
		this.gCal_uid = gCal_uid;
	}
	
 	public Gd_comments getGd_comments(){
		return this.gd_comments;
	}
	public void setGd_comments(Gd_comments gd_comments){
		this.gd_comments = gd_comments;
	}
	
 	public Gd_eventStatus getGd_eventStatus(){
		return this.gd_eventStatus;
	}
	public void setGd_eventStatus(Gd_eventStatus gd_eventStatus){
		this.gd_eventStatus = gd_eventStatus;
	}
 	public Gd_originalEvent getGd_originalEvent(){
		return this.gd_originalEvent;
	}
	public void setGd_originalEvent(Gd_originalEvent gd_originalEvent){
		this.gd_originalEvent = gd_originalEvent;
	}
 	public Gd_transparency getGd_transparency(){
		return this.gd_transparency;
	}
	public void setGd_transparency(Gd_transparency gd_transparency){
		this.gd_transparency = gd_transparency;
	}
 	public List<GD_When> getGd_when(){
		return this.gd_when;
	}
	public void setGd_when(List<GD_When> gd_when){
		this.gd_when = gd_when;
	}
 	public List<GD_Where> getGd_where(){
		return this.gd_where;
	}
	public void setGd$where(List<GD_Where> gd_where){
		this.gd_where = gd_where;
	}
 	public List<GD_Who> getGd_who(){
		return this.gd_who;
	}
	public void setGd_who(List<GD_Who> gd_who){
		this.gd_who = gd_who;
	}
 	public GC_Id getId(){
		return this.id;
	}
	public void setId(GC_Id id){
		this.id = id;
	}
 	public List<GC_Link> getLink(){
		return this.link;
	}
	public void setLink(List<GC_Link> link){
		this.link = link;
	}
 	public GC_Published getPublished(){
		return this.published;
	}
	public void setPublished(GC_Published published){
		this.published = published;
	}
 	public GC_Title getTitle(){
		return this.title;
	}
	public void setTitle(GC_Title title){
		this.title = title;
	}
 	public GC_Updated getUpdated(){
		return this.updated;
	}
	public void setUpdated(GC_Updated updated){
		this.updated = updated;
	}
}
