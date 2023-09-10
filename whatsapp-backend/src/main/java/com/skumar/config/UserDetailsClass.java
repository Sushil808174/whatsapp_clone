package com.skumar.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skumar.entity.Users;
import com.skumar.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsClass implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<Users> user = userRepository.findByEmail(username);
        if (user.isPresent()) {

            Users users = user.get();

            List<GrantedAuthority> authorities = new ArrayList<>();

            SimpleGrantedAuthority sga = new SimpleGrantedAuthority(users.getRole());
            authorities.add(sga);

            return new User(users.getEmail(), users.getPassword(), authorities);


        } else
            throw new BadCredentialsException("User Details not found with this username: " + username);

    }
}
