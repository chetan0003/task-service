package com.task.controller;

import com.task.model.Response;
import com.task.model.User;
import com.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @PostMapping("/create")
    public ResponseEntity<Response> postUser(@RequestBody User user) {
        Response response = this.userService.createUser(user);
        if(response!=null&&response.getStatusCode().equals(401))
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        else
            return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> getUserById(@PathVariable Integer id){
        return new ResponseEntity<>(this.userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/getUser/{userName}")
    public ResponseEntity<Response> getUserByUserName(@PathVariable String userName) {
        return new ResponseEntity<>(this.userService.getUserByUserName(userName), HttpStatus.OK);
    }
}
