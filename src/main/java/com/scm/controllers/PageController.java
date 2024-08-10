package com.scm.controllers;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.repsitories.UserRepo;
// import com.scm.services.EmailService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {
    Random random= new Random(1000);

    // @Autowired
    // private EmailService emailService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");
        // sending data to view
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("youtubeChannel", "Learn Code With Durgesh");
        model.addAttribute("githubRepo", "https://github.com/learncodewithdurgesh/");
        return "home";
    }

    // about route

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading");
        return "about";
    }

    // services

    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("services page loading");
        return "services";
    }

    // contact page

    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    // this is showing login page
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    // registration page
    @GetMapping("/signup")
    public String register(Model model) {

        UserForm userForm = new UserForm();
        // default data bhi daal sakte hai
        // userForm.setName("Durgesh");
        // userForm.setAbout("This is about : Write something about yourself");
        model.addAttribute("userForm", userForm);

        return "signup";
    }

    // processing register

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
            HttpSession session) {
        System.out.println("Processing registration");
        // fetch form data
        // UserForm
        System.out.println(userForm);

        

        // save to database

        // userservice

        // UserForm--> User
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic(
        // "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        

        User savedUser = userService.saveUser(user);

        System.out.println("user saved :"+ savedUser);

        // message = "Registration Successful"

        // add the message:

        Message message = Message.builder().content("Registration Successful, Make sure to verify email before login").type(MessageType.green).build();

        session.setAttribute("message", message);

        // redirect to login page
        return "redirect:/signup";

        
    }

    @RequestMapping(value = "/forgot_password")
    public String emailForm(){
        return "forgot_password";
    }

    // @RequestMapping(value = "/send_otp")
    // public String sendOTP(@RequestParam("email") String email, HttpSession session){
    //     System.out.println("Email: "+ email);

    //     // Generating OTP
    //     int otp = random.nextInt(999999);
    //     Message message = Message.builder().content("OTP Sent Successfully").type(MessageType.green).build();
    //     session.setAttribute("message", message);

    //     System.out.println("OTP: "+otp);

    //     String subject = "SCM - OTP to change your Password";
    //     String messages = "<h1> OTP : "+otp+" </h1>";
        
    //     String to =email;

    //     boolean flag = this.emailService.sendEmail(subject, messages, to);

    //     if (flag) {
    //         session.setAttribute("myOtp",otp);
    //         session.setAttribute("email", email);
    //         return "verify_otp";
    //     }else{
    //         Message error_message = Message.builder().content("Check your email address").type(MessageType.red).build();
    //         session.setAttribute("message", error_message);
    //         return "forgot_password";
    //     }

    // }
    // Verify otp
    @PostMapping(value = "verify-otp")
    public String verifyOtp(@RequestParam("otp") int otp, HttpSession session){
        int myOtp = (int) session.getAttribute("myOtp");
        String email = (String) session.getAttribute("email");

        if (myOtp == otp) {

            Optional<User> user = this .userRepo.findByEmail(email);
            if (!user.isPresent()) {
                Message otp_message = Message.builder().content("User with this email address does not exist, please register!").type(MessageType.red).build();
                session.setAttribute("message", otp_message);
                return "forgot_password";
            }
            else{

            }
            return "password_change_form";
        }
        else{
            Message otp_message = Message.builder().content("You have entered wrong OTP!").type(MessageType.red).build();
            session.setAttribute("message", otp_message);
            return "verify_otp";
        }

    }

    // Change Password
    @PostMapping(value = "/change-password")
    public String changePassword(@RequestParam("newPassword") String newPassword, HttpSession session){
        String email = (String) session.getAttribute("email");
        Optional<User> userOpt = this.userRepo.findByEmail(email);
        User user = userOpt.get();
        user.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
        this.userRepo.save(user);
        Message change_pass_message = Message.builder().content("Password changed successfully! Please login with new password").type(MessageType.blue).build();
        session.setAttribute("message", change_pass_message);
        return "redirect:/login?change=password changed successfully";
    }
}
