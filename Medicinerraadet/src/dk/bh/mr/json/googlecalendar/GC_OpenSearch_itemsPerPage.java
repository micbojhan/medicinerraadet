package dk.bh.mr.json.googlecalendar;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class GC_OpenSearch_itemsPerPage implements Serializable{
	@SerializedName("$t")
   	private String $t;

 	public String get$t(){
		return this.$t;
	}
	public void set$t(String $t){
		this.$t = $t;
	}
}
