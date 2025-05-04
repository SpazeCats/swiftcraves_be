package com.swiftcraves.onlinefood.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swiftcraves.onlinefood.model.USER_ROLE;
import com.swiftcraves.onlinefood.model.User;
import com.swiftcraves.onlinefood.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from repository by email
        User user = userRepository.findByEmail(username);

        if (user == null) {
            // If no user found, throw an exception
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        // Get the role of the user, default to ROLE_CUSTOMER if the role is not set
        USER_ROLE role = user.getRole();
        if (role == null) {
            role = USER_ROLE.ROLE_CUSTOMER;
        }

        // Print the role for debugging
        System.out.println("Role ---- " + role);

        // Create a list of authorities (roles)
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));

        // Return a new UserDetails object with the user's email, password, and authorities
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorities);
    }
}
