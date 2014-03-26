package dk.bh.mr.json.googlecalendar;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class GC_Content implements Serializable{
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
