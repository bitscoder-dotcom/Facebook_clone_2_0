package com.roman.facebookclone.dao;

import com.roman.facebookclone.dto.UserDto;
import com.roman.facebookclone.model.User;
import lombok.NonNull;

public interface UserDao {

    User saveUser(UserDto userDto);

    User findUserByEmailAndPassword(@NonNull String email, @NonNull String password);

}
