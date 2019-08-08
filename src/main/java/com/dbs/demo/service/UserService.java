package com.dbs.demo.service;

import com.dbs.demo.model.User;
import java.util.List;

public interface UserService {

	User saveUser(User user);

    List<User> listAll();

    User findById(long empId);

    void deleteUser(long empId);
}