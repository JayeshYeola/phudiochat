package com.example.demo;

import java.sql.Blob;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String fbid;
	
	private String name;
	
	private String description;
	
	private String profilepic;
	
	private String friend_ids;
	
	@Column(unique=true)
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFbid() {
		return fbid;
	}

	public void setFbid(String fbid) {
		this.fbid = fbid;
	}

	public String[] getFriend_ids() {
		String[] arr = friend_ids.split(",");
		String[] numArr = new String[arr.length];
		for(int i = 0; i < numArr.length; i++){
		    numArr[i] = arr[i];
		}
		return numArr;
	}

	public void setFriend_ids(String[] friend_ids) {
        String models = String.join(",", friend_ids);
		this.friend_ids = models;
	}

}
