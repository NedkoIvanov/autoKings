package com.project.autoKings.service;

import com.project.autoKings.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutoKingsUserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    public AutoKingsUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var userEntity = this.userRepository.findByUsername(username)
                .orElseThrow( ()->new UsernameNotFoundException("User with requested username:" + username + " not found."));

        List<GrantedAuthority> authorities = userEntity
                .getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name()))
                .collect(Collectors.toList());

        return new User(userEntity.getUsername(),userEntity.getPassword(),authorities);
    }
}
