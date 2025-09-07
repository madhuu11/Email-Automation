package com.madhu.EmailAutomation.controller;

import com.madhu.EmailAutomation.Service.BirthdayEmailService;
import com.madhu.EmailAutomation.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//generate rest controller birthday email controller class with request mapping /birthday-email
@Controller
@RequestMapping("/birthday-email")
public class BirthdayEmailController {

    // Autowire birthday email service
    @Autowired
    private BirthdayEmailService BirthdayEmailService;

    @GetMapping
    public String home() {
        return "index";
    }

    // Generate get mapping /send
    @GetMapping("/send")
    public ResponseEntity<String> sendBirthdayEmail() {
        // Call send birthday email method from birthday email service
        BirthdayEmailService.sendEmail();
        // Return response entity with message
        return ResponseEntity.ok("Birthday Email Sent Successfully");
    }

    //generate get mapping /all  to get all user details and return user details
    @GetMapping("/allUserDetails")
    public String getAllUserDetails(Model model) {
        // Call get all user details method from birthday email service
        List<User> users = BirthdayEmailService.getAllUsers();
        model.addAttribute("users", users);
        return "allUsers";
    }

    //create get mapping /add-user to get add-user form
    @GetMapping("/add-user")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    //create post mapping /add-user to add user details
    @PostMapping("/add-user")
    public String addUser(@ModelAttribute User user) {
        // Call add user method from birthday email service
        BirthdayEmailService.addUser(user);
        return "redirect:/birthday-email/allUserDetails";
    }

    //create get mapping /user/birthdayDay to get user details by birthday day and return user details
    @GetMapping("/user/birthdayToday")
    public String getUserByBirthdayDay(Model model) {
        // Call get user by birthday day method from birthday email service
        List<User> birthdayUsers = BirthdayEmailService.getBirthdayToday();
        model.addAttribute("users", birthdayUsers);
        model.addAttribute("today", LocalDate.now());
        return "todayBirthday";
    }

}
