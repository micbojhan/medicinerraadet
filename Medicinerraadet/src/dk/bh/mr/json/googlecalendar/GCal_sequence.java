package dk.bh.mr.json.googlecalendar;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class GCal_sequence implements Serializable{
	@SerializedName("value")
   	private String value;

 	public String getValue(){
		return this.value;
	}
	public void setValue(String value){
		this.value = value;
	}
}
