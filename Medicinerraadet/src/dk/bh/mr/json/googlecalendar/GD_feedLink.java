package dk.bh.mr.json.googlecalendar;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class GD_feedLink implements Serializable{
	@SerializedName("href")
   	private String href;
   	
 	public String getHref(){
		return this.href;
	}
	public void setHref(String href){
		this.href = href;
	}

}
