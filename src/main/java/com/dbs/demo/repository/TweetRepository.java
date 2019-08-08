package com.dbs.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.demo.model.User;

@Repository
public interface TweetRepository extends JpaRepository<User, Long> {

}
