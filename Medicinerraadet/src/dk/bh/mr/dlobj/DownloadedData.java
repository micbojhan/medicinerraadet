package dk.bh.mr.dlobj;

import java.io.Serializable;
import java.util.Date;


//import com.example.temaprojekt1.MedicinInfo.MedicinInfo;

import dk.bh.mr.json.contactmr.ContactMedicinerRaadet;
import dk.bh.mr.json.facebookfeed.FacebookFeed;
import dk.bh.mr.json.googlecalendar.GoogleCalendar;
import dk.bh.mr.json.ommr.OmMedicinerRaadet;


@SuppressWarnings("serial")
public class DownloadedData implements Serializable{

	private Date downloadedTime;
   	private GoogleCalendar googleCalender;
   	private FacebookFeed facebookFeedMR;
   	private OmMedicinerRaadet omMR;
   	private ContactMedicinerRaadet contactMR;
   	//private SubjectMails subjectMail;
   	//private SemesterMails semesterMails;
   	
 	public ContactMedicinerRaadet getContactMedicinerRaadet(){
		return this.contactMR;
	}
	public void setContactMedicinerRaadet(ContactMedicinerRaadet contactMR){
		this.contactMR = contactMR;
	}
	
 	public GoogleCalendar getGoogleCalender(){
		return this.googleCalender;
	}
	public void setGoogleCalende(GoogleCalendar googleCalender){
		this.googleCalender = googleCalender;
	}
	
 	public FacebookFeed getMedicinNews(){
		return this.facebookFeedMR;
	}
	public void setMedicinNews(FacebookFeed medicinNews){
		this.facebookFeedMR = medicinNews;
	}
	
 	public OmMedicinerRaadet getMedicinInfo(){
		return this.omMR;
	}
	public void setMedicinInfo(OmMedicinerRaadet medicinInfo){
		this.omMR = medicinInfo;
	}
	
 	public Date getDownloadedDateTime(){
		return this.downloadedTime;
	}
	public void setDownloadedDateTime(Date downloadedTime){
		this.downloadedTime = downloadedTime;
	}
	
	/*
 	public SubjectMails getDownloadedSubjectMails(){
		return this.subjectMail;
	}
	public void setDownloadedSubjectMails(SubjectMails subjectMail){
		this.subjectMail = subjectMail;
	}
	
	
 	public SemesterMails getDownloadedSemesterMails(){
		return this.semesterMails;
	}
	public void setDownloadedSemesterMails(SemesterMails semesterMails){
		this.semesterMails = semesterMails;
	}
	*/
}
