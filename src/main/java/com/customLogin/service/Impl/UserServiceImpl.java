package com.customLogin.service.Impl;

import com.customLogin.dto.UserDTO;
import com.customLogin.model.User;
import com.customLogin.repository.UserRepository;
import com.customLogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserDTO userDTO) {
        User user=new User(userDTO.getUsername(),passwordEncoder.encode( userDTO.getPassword()), userDTO.getFullname() );
        return userRepository.save(user);
    }
}
