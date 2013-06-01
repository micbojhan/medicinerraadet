package dk.itsmap.nissebanden.medicinerraadet.json;

import java.io.Serializable;

public class SemesterMail implements Serializable{
	private String id;
	private String mailAdr;
	private String nameFirst;
	private String nameLast;
	private String nickName;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMailAdr() {
		return this.mailAdr;
	}

	public void setMailAdr(String mailAdr) {
		this.mailAdr = mailAdr;
	}

	public String getNameFirst() {
		return this.nameFirst;
	}

	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}

	public String getNameLast() {
		return this.nameLast;
	}

	public void setNameLast(String nameLast) {
		this.nameLast = nameLast;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
