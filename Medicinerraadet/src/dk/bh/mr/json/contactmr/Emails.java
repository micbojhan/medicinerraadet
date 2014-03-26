
package dk.bh.mr.json.contactmr;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Emails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1176793024327771445L;
	@SerializedName("id")
   	private String id;
	@SerializedName("mailAdrs")
   	private List<String> mailAdrs;
	@SerializedName("name")
   	private String name;
	@SerializedName("nameFirst")
   	private String nameFirst;
	@SerializedName("nameLast")
   	private String nameLast;
	@SerializedName("nickName")
   	private String nickName;
	@SerializedName("subemails")
   	private List<Emails> subemails;
	
    @Override
    public String toString() {          
        return  nickName;
    }

 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public List<String> getMailAdrs(){
		return this.mailAdrs;
	}
	public void setMailAdrs(List<String> mailAdrs){
		this.mailAdrs = mailAdrs;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getNameFirst(){
		return this.nameFirst;
	}
	public void setNameFirst(String nameFirst){
		this.nameFirst = nameFirst;
	}
 	public String getNameLast(){
		return this.nameLast;
	}
	public void setNameLast(String nameLast){
		this.nameLast = nameLast;
	}
 	public String getNickName(){
		return this.nickName;
	}
	public void setNickName(String nickName){
		this.nickName = nickName;
	}
 	public List<Emails> getSubemails(){
		return this.subemails;
	}
	public void setSubemails(List<Emails> subemails){
		this.subemails = subemails;
	}
}
