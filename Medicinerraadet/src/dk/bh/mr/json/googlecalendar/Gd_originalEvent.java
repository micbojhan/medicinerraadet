package dk.bh.mr.json.googlecalendar;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class Gd_originalEvent implements Serializable{
	@SerializedName("id")
   	private String id;
	@SerializedName("href")
   	private String href;
	@SerializedName("gd$when")
   	private GD_When gd$when;

 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	
 	public String getHref(){
		return this.href;
	}
	public void setHref(String href){
		this.href = href;
	}
	
 	public GD_When getGD_When(){
		return this.gd$when;
	}
	public void setGD_When(GD_When gd$when){
		this.gd$when = gd$when;
	}
}
