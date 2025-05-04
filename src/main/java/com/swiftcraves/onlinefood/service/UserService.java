package com.swiftcraves.onlinefood.service;



import com.swiftcraves.onlinefood.model.User;


public interface UserService {

        public User findUserByJwtToken(String jwt) throws Exception;

        public User findUserByEmail(String email) throws Exception;

}
