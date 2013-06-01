package com.medicin.splashdownload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import android.content.Context;
import android.os.Environment;

//import com.example.temaprojekt1.MedicinInfo.MedicinInfo;
import com.example.temaprojekt1.MedicinNews.MedicinNews;
import com.example.temaprojekt4.GoogleCalendar.GC_GoogleCalendar;

import dk.itsmap.nissebanden.medicinerraadet.json.SemesterMails;
import dk.itsmap.nissebanden.medicinerraadet.json.SubjectMail;
import dk.itsmap.nissebanden.medicinerraadet.json.SubjectMails;

public class DownloadedData implements Serializable{

	private Date downloadedTime;
   	private GC_GoogleCalendar googleCalender;
   	private MedicinNews medicinNews;
   	//private MedicinInfo medicinInfo;
   	private SubjectMails subjectMail;
   	private SemesterMails semesterMails;
   	
	
 	public GC_GoogleCalendar getGoogleCalender(){
		return this.googleCalender;
	}
	public void setGoogleCalende(GC_GoogleCalendar googleCalender){
		this.googleCalender = googleCalender;
	}
	
 	public MedicinNews getMedicinNews(){
		return this.medicinNews;
	}
	public void setMedicinNews(MedicinNews medicinNews){
		this.medicinNews = medicinNews;
	}
	/*
 	public MedicinInfo getMedicinInfo(){
		return this.medicinInfo;
	}
	public void setMedicinInfo(MedicinInfo medicinInfo){
		this.medicinInfo = medicinInfo;
	}
	*/
 	public Date getDownloadedDateTime(){
		return this.downloadedTime;
	}
	public void setDownloadedDateTime(Date downloadedTime){
		this.downloadedTime = downloadedTime;
	}
	
	
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
}
