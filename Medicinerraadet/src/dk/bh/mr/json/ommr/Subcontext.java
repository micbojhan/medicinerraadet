
package dk.bh.mr.json.ommr;

import java.util.List;

public class Subcontext{
   	private List context;
   	private boolean expand;
   	private Number id;
   	private List subcontext;
   	private String subject;

 	public List getContext(){
		return this.context;
	}
	public void setContext(List context){
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
 	public List getSubcontext(){
		return this.subcontext;
	}
	public void setSubcontext(List subcontext){
		this.subcontext = subcontext;
	}
 	public String getSubject(){
		return this.subject;
	}
	public void setSubject(String subject){
		this.subject = subject;
	}
}
