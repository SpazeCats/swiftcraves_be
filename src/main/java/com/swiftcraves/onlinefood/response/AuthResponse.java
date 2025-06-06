package com.swiftcraves.onlinefood.response;

import com.swiftcraves.onlinefood.model.USER_ROLE;

import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
