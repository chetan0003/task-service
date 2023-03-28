package com.task.service.impl;

import com.task.entities.UserDetail;
import com.task.model.Response;
import com.task.model.User;
import com.task.repository.UserRepository;
import com.task.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    @Override
    public Response createUser(User user) {
        UserDetail userDetail = userRepository.findByUserName(user.getUserName());
        if(Objects.nonNull(userDetail))
            return Response.builder().statusCode(HttpStatus.FORBIDDEN.value()).message("user name already exist").build();
        UserDetail userEntity = UserDetail.builder().userName(user.getUserName()).firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getEmail()).password(user.getPassword()).build();
        log.info("User Request " +userEntity.getUserName()+ " " + userEntity.getFirstName());
        UserDetail user1 = userRepository.save(userEntity);
        return Response.builder().statusCode(HttpStatus.CREATED.value()).data(user1).build();
    }

    @Override
    public Response getUserById(Integer id) {
        Optional<UserDetail> user = userRepository.findById(id);
        UserDetail user1 = null;
        if(user.isPresent())
            user1 = user.get();
        return Response.builder().statusCode(HttpStatus.OK.value()).data(user1).build();
    }

    @Override
    public Response getUserByUserName(String userName) {
        UserDetail userDetail = userRepository.findByUserName(userName);
        return Response.builder().statusCode(HttpStatus.OK.value()).data(userDetail).build();
    }

    @Override
    public UserDetail findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
