package com.customLogin.service;


import com.customLogin.dto.UserDTO;
import com.customLogin.model.User;

public interface UserService {

    User findByUsername(String username);

    User save(UserDTO userDTO);
}
