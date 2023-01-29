package com.roman.facebookclone.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Post {


    private int post_id;
    private  String post_content;
    private String firstname;
    private String surname;
    private LocalDate time;
    private int likes;
}
