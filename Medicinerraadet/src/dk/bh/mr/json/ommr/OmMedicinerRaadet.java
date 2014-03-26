
package dk.bh.mr.json.ommr;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class OmMedicinerRaadet  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8487305119155860463L;
	@SerializedName("ommr")
   	private List<Ommr> ommr;

 	public List<Ommr> getOmmr(){
		return this.ommr;
	}
	public void setOmmr(List<Ommr> ommr){
		this.ommr = ommr;
	}
}
