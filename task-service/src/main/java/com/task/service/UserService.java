package com.task.service;

import com.task.entities.UserDetail;
import com.task.model.Response;
import com.task.model.User;

public interface UserService {

    Response createUser(User user);

    Response getUserById(Integer id);
    Response getUserByUserName(String userName);

    UserDetail findByUserName(String userName);
}
