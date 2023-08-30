package com.app.aspireloan.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {
    private String userName;
    private String role;  // can be customer or admin for now.
    private String userId;
    private int age;
    private String email;
    private String phoneNumber;
}
