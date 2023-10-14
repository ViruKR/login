package com.customLogin.controller;


import com.customLogin.dto.UserDTO;
import com.customLogin.model.User;
import com.customLogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller

public class UserController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userDetail",userDetails);
        return "home";
    }
    @GetMapping("/login")
    public String login(Model model, UserDTO userDTO){
        model.addAttribute("user",userDTO);
        return "login";
    }

    @PostMapping("/register")
    public String registersave(@ModelAttribute("user")UserDTO userDTO, Model model){
        User user = userService.findByUsername(userDTO.getUsername());
        if(user !=null){
            model.addAttribute("userexits",user);
            return "register";
        }
        userService.save(userDTO);
        return "redirect:/register?success";
    }

    @GetMapping("/register")
    public String register(Model model, UserDTO userDTO){
        model.addAttribute("user",userDTO);
        return "register";
    }
}
