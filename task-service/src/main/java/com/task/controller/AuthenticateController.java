package com.task.controller;

import com.task.entities.UserDetail;
import com.task.model.Response;
import com.task.model.User;
import com.task.service.UserService;
import com.task.util.CustomPasswordEncoder;
import com.task.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/authenticate")
public class AuthenticateController {

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder bcpe;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping
    public @ResponseBody Response register(@RequestBody User user) throws AuthenticationException {
        UserDetail userVO2 = userService.findByUserName(user.getUserName());
        if(userVO2 == null){
            return Response.builder().statusCode(401).message("User is not authorize,please check user name and password").build();
        }
        userVO2.setPassword(customPasswordEncoder.encode(userVO2.getPassword()));
        if (bcpe.matches(user.getPassword(), userVO2.getPassword())) {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUserName(),
                            user.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(authentication);
            user.setPassword(null);
            user.setToken(token);
            user.setId(userVO2.getId());
            return Response.builder().statusCode(200).data(user).build();
        } else {
            return Response.builder().statusCode(401).message("User is not authorize,please check user name and password").build();
        }
    }
}

