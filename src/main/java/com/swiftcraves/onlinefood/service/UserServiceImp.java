package com.swiftcraves.onlinefood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftcraves.onlinefood.config.JwtProvider;
import com.swiftcraves.onlinefood.model.User;
import com.swiftcraves.onlinefood.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {
        

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private JwtProvider jwtProvider;

        @Override
        public User findUserByJwtToken(String jwt) throws Exception {
            String email=jwtProvider.getEmailFromJwtToken(jwt);
            User user=findUserByEmail(email);
            return user;
        }

        @Override
        public User findUserByEmail(String email) throws Exception {
            User user=userRepository.findByEmail(email);

            if(user==null){
                throw new Exception("User not found.");
            }
            return user;
        }
}
