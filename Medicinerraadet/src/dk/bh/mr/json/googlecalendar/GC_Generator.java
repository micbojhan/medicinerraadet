package dk.bh.mr.json.googlecalendar;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class GC_Generator implements Serializable{
	@SerializedName("$t")
   	private String $t;
	@SerializedName("version")
   	private String version;
	@SerializedName("uri")
   	private String uri;

 	public String getUri(){
		return this.uri;
	}
	public void setUri(String uri){
		this.uri = uri;
	}
	
 	public String getVersion(){
		return this.version;
	}
	public void setVersion(String version){
		this.version = version;
	}
   	
 	public String get$t(){
		return this.$t;
	}
	public void set$t(String $t){
		this.$t = $t;
	}
}
