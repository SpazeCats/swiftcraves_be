package com.swiftcraves.onlinefood.request;

import lombok.Data;

@Data
public class LoginRequest {
    
    private String email;
    private String password;
}
