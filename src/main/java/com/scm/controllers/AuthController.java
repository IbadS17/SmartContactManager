package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.repsitories.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam("token") String token, HttpSession httpSession) {
        User user = userRepo.findByEmailToken(token).orElse(null);

        if (user != null) {
            if (user.getEmailToken().equals(token)) {
                user.setEmailVerified(true);
                user.setEnabled(true);
                userRepo.save(user);
                httpSession.setAttribute("message", Message.builder().type(MessageType.green).content("Email verified successfully. You can now login.").build());
                return "success_page";
                
            }
            httpSession.setAttribute("message", Message.builder().type(MessageType.red).content("Email not verified . Something went wrong!.").build());
            return "error_page";
        }
        httpSession.setAttribute("message", Message.builder().type(MessageType.red).content("Email not verified . Something went wrong!.").build());
        return "error_page";

    }

}
