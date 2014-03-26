
package dk.bh.mr.json.contactmr;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ContactMedicinerRaadet implements Serializable{
	@SerializedName("contact")
   	private Contact contact;

 	public Contact getContact(){
		return this.contact;
	}
	public void setContact(Contact contact){
		this.contact = contact;
	}
}
