package com.roman.facebookclone.dao;

import com.roman.facebookclone.model.Post;
import lombok.NonNull;

public interface PostDao {

    Post postContent(@NonNull String post_content);

}
