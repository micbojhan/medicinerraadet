package com.example.temaprojekt1.MedicinNews;

import java.io.Serializable;

public class Author implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6315894007062719367L;
	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
