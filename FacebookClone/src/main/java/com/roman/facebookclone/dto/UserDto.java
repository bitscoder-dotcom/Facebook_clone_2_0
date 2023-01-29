package com.roman.facebookclone.dto;

import lombok.Data;

@Data
public class UserDto {

    private final String firstname;
    private final String surname;
    private final String email;
    private final String password;
    private final String dob;
    private final String gender;
}
