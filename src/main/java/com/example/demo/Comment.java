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
	
	private int post_id;
	
	private String text;
	
	private DateTime commentTime;

	public int getId() {
		return id;
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

	public int getPost() {
		return post_id;
	}

	public void setPost(int post_id) {
		this.post_id = post_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public DateTime getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(DateTime commentTime) {
		this.commentTime = commentTime;
	}
	
	
}
