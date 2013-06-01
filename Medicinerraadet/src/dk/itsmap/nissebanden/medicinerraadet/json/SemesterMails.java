
package dk.itsmap.nissebanden.medicinerraadet.json;

import java.io.Serializable;
import java.util.List;

public class SemesterMails implements Serializable{
   	private List mails;

 	public List getMails(){
		return this.mails;
	}
	public void setMails(List mails){
		this.mails = mails;
	}
}
