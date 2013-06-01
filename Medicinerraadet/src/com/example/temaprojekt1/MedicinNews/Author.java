
package com.example.temaprojekt1.MedicinNews;

import java.io.Serializable;
import java.util.List;

public class Author implements Serializable{
   	private String name;

 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
}
