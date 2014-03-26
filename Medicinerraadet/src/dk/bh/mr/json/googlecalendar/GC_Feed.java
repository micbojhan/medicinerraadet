
package dk.bh.mr.json.googlecalendar;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GC_Feed implements Serializable{
	@SerializedName("xmlns")
   	private String xmlns;
	@SerializedName("xmlns$gCal")
   	private String xmlns_gCal;
	@SerializedName("xmlns$gd")
   	private String xmlns_gd;
	@SerializedName("xmlns$openSearch")
   	private String xmlns_openSearch;
   	
	@SerializedName("id")
   	private GC_Id id;
	@SerializedName("updated")
   	private GC_Updated updated;
	@SerializedName("category")
   	private List<GC_Category> category;
   	@SerializedName("title")
   	private GC_Title title;
   	@SerializedName("subtitle")
   	private GC_Subtitle subtitle;
   	@SerializedName("link")
   	private List<GC_Link> link;
   	@SerializedName("author")
   	private List<GC_Author> author;
   	@SerializedName("generator")
   	private GC_Generator generator;
   	
   	@SerializedName("openSearch$totalResults")
   	private GC_OpenSearch_totalResults openSearch_totalResults;
   	@SerializedName("openSearch$startIndex")
   	private GC_OpenSearch_startIndex openSearch_startIndex;
   	@SerializedName("openSearch$itemsPerPage")
   	private GC_OpenSearch_itemsPerPage openSearch_itemsPerPage;
   	
   	@SerializedName("gCal$timezone")
   	private GCal_timezone gCal_timezone;
   	@SerializedName("gCal$timesCleaned")
   	private GCal_timesCleaned gCal_timesCleaned;
   	@SerializedName("entry")
   	private List<GC_Entry> entry;



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
 	public List<GC_Entry> getEntry(){
		return this.entry;
	}
	public void setEntry(List<GC_Entry> entry){
		this.entry = entry;
	}
 	public GCal_timesCleaned getGCal_timesCleaned(){
		return this.gCal_timesCleaned;
	}
	public void setGCal_timesCleaned(GCal_timesCleaned gCal_timesCleaned){
		this.gCal_timesCleaned = gCal_timesCleaned;
	}
 	public GCal_timezone getGCal_timezone(){
		return this.gCal_timezone;
	}
	public void setGCal_timezone(GCal_timezone gCal_timezone){
		this.gCal_timezone = gCal_timezone;
	}
 	public GC_Generator getGenerator(){
		return this.generator;
	}
	public void setGenerator(GC_Generator generator){
		this.generator = generator;
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
 	public GC_OpenSearch_itemsPerPage getOpenSearch_itemsPerPage(){
		return this.openSearch_itemsPerPage;
	}
	public void setOpenSearch_itemsPerPage(GC_OpenSearch_itemsPerPage openSearch_itemsPerPage){
		this.openSearch_itemsPerPage = openSearch_itemsPerPage;
	}
 	public GC_OpenSearch_startIndex getOpenSearch_startIndex(){
		return this.openSearch_startIndex;
	}
	public void setOpenSearch$startIndex(GC_OpenSearch_startIndex openSearch_startIndex){
		this.openSearch_startIndex = openSearch_startIndex;
	}
 	public GC_OpenSearch_totalResults getOpenSearch_totalResults(){
		return this.openSearch_totalResults;
	}
	public void setOpenSearch$totalResults(GC_OpenSearch_totalResults openSearch_totalResults){
		this.openSearch_totalResults = openSearch_totalResults;
	}
 	public GC_Subtitle getSubtitle(){
		return this.subtitle;
	}
	public void setSubtitle(GC_Subtitle subtitle){
		this.subtitle = subtitle;
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
 	public String getXmlns(){
		return this.xmlns;
	}
	public void setXmlns(String xmlns){
		this.xmlns = xmlns;
	}
 	public String getXmlns_gCal(){
		return this.xmlns_gCal;
	}
	public void setXmlns_gCal(String xmlns_gCal){
		this.xmlns_gCal = xmlns_gCal;
	}
 	public String getXmlns_gd(){
		return this.xmlns_gd;
	}
	public void setXmlns_gd(String xmlns_gd){
		this.xmlns_gd = xmlns_gd;
	}
 	public String getXmlns_openSearch(){
		return this.xmlns_openSearch;
	}
	public void setXmlns_openSearch(String xmlns_openSearch){
		this.xmlns_openSearch = xmlns_openSearch;
	}
}
