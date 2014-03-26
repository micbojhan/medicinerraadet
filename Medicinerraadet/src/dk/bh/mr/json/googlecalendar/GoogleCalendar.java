
package dk.bh.mr.json.googlecalendar;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class GoogleCalendar implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6528221674515891799L;
	@SerializedName("encoding")
   	private String encoding;
	@SerializedName("feed")
   	private GC_Feed feed;
	@SerializedName("version")
   	private String version;

 	public String getEncoding(){
		return this.encoding;
	}
	public void setEncoding(String encoding){
		this.encoding = encoding;
	}
 	public GC_Feed getFeed(){
		return this.feed;
	}
	public void setFeed(GC_Feed feed){
		this.feed = feed;
	}
 	public String getVersion(){
		return this.version;
	}
	public void setVersion(String version){
		this.version = version;
	}
}
