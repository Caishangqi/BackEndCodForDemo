package com.jt.controller;

import com.jt.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//   ___      ___  ___      ___   ______         _______      _______       ____
//  |"  \    /"  ||"  \    /"  | /" _  "\       /"     "\    /"     "\     /  " \
//   \   \  //   | \   \  //  / (: ( \___)     (__/\    :)  (__/\    :)   /__|| |
//   /\\  \/.    |  \\  \/. ./   \/ \              / ___/       / ___/       |: |
//  |: \.        |   \.    //    //  \ _          // \___      // \___      _\  |
//  |.  \    /:  |    \\   /    (:   _) \        (:  /  "\    (:  /  "\    /" \_|\
//  |___|\__/|___|     \__/      \_______)        \_______)    \_______)  (_______)
//
@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    public void addUser() {
        userService.addUser();
    }
}
