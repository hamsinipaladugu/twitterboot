package com.dbs.demo.model;

import java.util.List;

import javax.persistence.Entity;

//@Entity
public class Follower {
	
	private List<User>followers;

	public void addFollower(User user) {
        //this.followers.add(user);
        //user.setUser(this);
    }

}
