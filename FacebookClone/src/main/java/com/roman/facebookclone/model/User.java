package com.roman.facebookclone.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class User {

    private int id;
    private String firstname;
    private String surname;
    private String email;
    private String password;
    private String dob;
    private String gender;

}
