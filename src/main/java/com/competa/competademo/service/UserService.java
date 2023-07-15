package com.competa.competademo.service;

import com.competa.competademo.dto.UserDto;
import com.competa.competademo.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();

}
