
package dk.bh.mr.json.contactmr;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Contact implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1054162009744529527L;
	@SerializedName("context")
   	private List<String> context;
	@SerializedName("emails")
   	private List<Emails> emails;

 	public List<String> getContext(){
		return this.context;
	}
	public void setContext(List<String> context){
		this.context = context;
	}
 	public List<Emails> getEmails(){
		return this.emails;
	}
	public void setEmails(List<Emails> emails){
		this.emails = emails;
	}
}
