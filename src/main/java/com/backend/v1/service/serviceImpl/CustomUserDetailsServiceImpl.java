package com.backend.v1.service.serviceImpl;

import com.backend.v1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var user = userService.getUserByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Email is not registered"));

        return User.withUsername(user.getEmail()).password(user.getPassword()).roles(user.getRole().getName().split("_")[1]).build();
    }

}
