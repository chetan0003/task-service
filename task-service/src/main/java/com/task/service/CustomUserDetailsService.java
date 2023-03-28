package com.task.service;


import com.task.entities.UserDetail;
import com.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author chetan dahule
 * @since  05 April 2020
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail userDetail = userRepository.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(userDetail.getUserName(), userDetail.getPassword(), new ArrayList<>());
    }


}
