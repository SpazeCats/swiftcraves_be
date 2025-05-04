package com.swiftcraves.onlinefood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swiftcraves.onlinefood.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String username);
}
