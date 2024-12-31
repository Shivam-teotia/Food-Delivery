package org.shivam.fooddelivery.service;

import org.shivam.fooddelivery.Model.User;
import org.shivam.fooddelivery.Model.UserRole;
import org.shivam.fooddelivery.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userRepository.findByEmail(username);

        if(user==null) {
            throw new UsernameNotFoundException("user not found with email  - "+username);
        }
        UserRole role=user.getRole();

        if(role==null) role=UserRole.ROLE_CUSTOMER;

        System.out.println("role  ---- "+role);

        List<GrantedAuthority> authorities=new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role.toString()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),user.getPassword(),authorities);
    }

}
