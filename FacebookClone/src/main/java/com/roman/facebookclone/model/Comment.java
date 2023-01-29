package com.roman.facebookclone.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Comment {

    private int comment_id;
    private int post_id;
    private String comment_content;
    private int like;
}
