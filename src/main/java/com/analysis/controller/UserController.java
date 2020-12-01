package com.analysis.controller;

import com.analysis.pojo.User;
import com.analysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("UserService")
    private UserService userService;

    @RequestMapping("/login")
    public String queryAllDevice(Model model) {
        String usename = "xxx";
        String pass = "345";

        List<User>  users = userService.queryUser();

        for (int i=0 ; i< users.size() ;i++){
            User u = users.get(i);
            if(u.getUser_name().equals(usename) && u.getPassword().equals(pass)){
                System.out.println(u.getUser_name());
                model.addAttribute("user", u);
            }
//            System.out.println(u.getUser_name() == usename);
        }

//        System.out.println(users.get(0).getPassword());

//        model.addAttribute("users", userService.queryUser());
        return "user-login";
    }

}
