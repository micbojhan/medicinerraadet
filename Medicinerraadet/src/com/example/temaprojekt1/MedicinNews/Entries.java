package com.example.temaprojekt1.MedicinNews;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Entries implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 803508010625047536L;
	@SerializedName("alternate")
	private String alternate;
	@SerializedName("author")
	private Author author;
	// @SerializedName("categories")
	// private ArrayList categories;
	@SerializedName("comments")
	private String comments;
	@SerializedName("content")
	private String content;
	@SerializedName("id")
	private String id;
	@SerializedName("likes")
	private String likes;
	@SerializedName("objects")
	private String objects;
	@SerializedName("published")
	private String published;
	@SerializedName("target")
	private String target;
	@SerializedName("title")
	private String title;
	@SerializedName("updated")
	private String updated;
	@SerializedName("verb")
	private String verb;

	public String getAlternate() {
		return this.alternate;
	}

	public void setAlternate(String alternate) {
		this.alternate = alternate;
	}

	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	/*
	 * public ArrayList getCategories(){ return this.categories; } public void
	 * setCategories(ArrayList categories){ this.categories = categories; }
	 */
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLikes() {
		return this.likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getObjects() {
		return this.objects;
	}

	public void setObjects(String objects) {
		this.objects = objects;
	}

	public String getPublished() {
		return this.published;
	}

	public void setPublished(String published) {
		this.published = published;
	}

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUpdated() {
		return this.updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getVerb() {
		return this.verb;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}
}
