
package dk.itsmap.nissebanden.medicinerraadet.json;

import java.io.Serializable;
import java.util.List;

public class SubjectMails implements Serializable{
   	private List<SubjectMail> mails;

 	public List<SubjectMail> getMails(){
		return this.mails;
	}
	public void setMails(List<SubjectMail> mails){
		this.mails = mails;
	}
}
