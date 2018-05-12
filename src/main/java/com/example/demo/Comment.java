package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.joda.time.DateTime;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int commentor_id;
	
	private int postid;
	
	private String text;

	public int getId() {
		return id;
	}

	public int getCommentor_id() {
		return commentor_id;
	}

	public void setCommentor_id(int commentor_id) {
		this.commentor_id = commentor_id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommentor() {
		return commentor_id;
	}

	public void setCommentor(int commentor_id) {
		this.commentor_id = commentor_id;
	}

	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
