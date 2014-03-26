package dk.bh.mr.json.googlecalendar;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class GD_Where implements Serializable{
	@SerializedName("valueString")
   	private String valueString;

 	public String getValueString(){
		return this.valueString;
	}
	public void setValueString(String valueString){
		this.valueString = valueString;
	}
}
