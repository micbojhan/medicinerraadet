package dk.itsmap.nissebanden.medicinerraadet.json;

import java.io.Serializable;
import java.util.List;

public class SemesterMails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -83405593511149979L;
	private List<SemesterMail> mails;

	public List<SemesterMail> getMails() {
		return this.mails;
	}

	public void setMails(List<SemesterMail> mails) {
		this.mails = mails;
	}
}
