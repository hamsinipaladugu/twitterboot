package com.dbs.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	@NotBlank(message = "Name cannot be null")
	private String name;

	@Column(name = "password")
	@NotBlank(message = "password cannot be null")
	private String password;

	@NotBlank(message = "Email Address cannot be null")
	private String emailAddress;

	@Column(name = "city")
	@NotBlank(message = "city cannot be null")
	private String city;

	@Column(name = "street")
	@NotBlank(message = "street cannot be null")
	private String street;

	@Column(name = "state")
	@NotBlank(message = "state cannot be null")
	private String state;

	@Column(name = "zip")
	@NotBlank(message = "zip cannot be null")
	private String zip;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Tweet> tweetSet = new HashSet<>();
	
	public void addTweetSet(Tweet tweet) {
        this.tweetSet.add(tweet);
        tweet.setUser(this);
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}
