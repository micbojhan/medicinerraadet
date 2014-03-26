
package dk.bh.mr.json.ommr;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Ommr implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 289622832174918134L;
	@SerializedName("context")
   	private List<String> context;
	@SerializedName("expand")
   	private boolean expand;
	@SerializedName("id")
   	private Number id;
	@SerializedName("subcontext")
   	private List<Ommr> subcontext;
	@SerializedName("subject")
   	private String subject;

 	public List<String> getContext(){
		return this.context;
	}
	public void setContext(List<String> context){
		this.context = context;
	}
 	public boolean getExpand(){
		return this.expand;
	}
	public void setExpand(boolean expand){
		this.expand = expand;
	}
 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public List<Ommr> getSubcontext(){
		return this.subcontext;
	}
	public void setSubcontext(List<Ommr> subcontext){
		this.subcontext = subcontext;
	}
 	public String getSubject(){
		return this.subject;
	}
	public void setSubject(String subject){
		this.subject = subject;
	}
}
