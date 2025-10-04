package com.madhu.EmailAutomation.controller;

import com.madhu.EmailAutomation.service.BirthdayEmailService;
import com.madhu.EmailAutomation.entity.User;
import com.madhu.EmailAutomation.util.Category;
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
    private BirthdayEmailService birthdayEmailService;

    // Generate get mapping /send
    @GetMapping("/send")
    public ResponseEntity<String> sendBirthdayEmail() {
        // Call send birthday email method from birthday email service
        birthdayEmailService.sendBirthdayEmail();
        // Return response entity with message
        return ResponseEntity.ok("Birthday Email Sent Successfully");
    }

    //generate get mapping /all  to get all user details and return user details
    @GetMapping("/allUserDetails")
    public String getAllUserDetails(Model model) {
        // Call get all user details method from birthday email service
        List<User> users = birthdayEmailService.getAllUsers();
        model.addAttribute("users", users);
        return "allUsers";
    }

    //create get mapping /add-user to get add-user form
    @GetMapping("/add-user")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("categories", Category.values());
        return "addUser";
    }

    //create post mapping /add-user to add user details
    @PostMapping("/add-user")
    public String addUser(@ModelAttribute User user) {
        // Call add user method from birthday email service
        birthdayEmailService.addUser(user);
        return "redirect:/birthday-email/allUserDetails";
    }

    //create get mapping /user/birthdayDay to get user details by birthday day and return user details
    @GetMapping("/user/birthdayToday")
    public String getUserByBirthdayDay(Model model) {
        // Call get user by birthday day method from birthday email service
        List<User> birthdayUsers = birthdayEmailService.getBirthdayToday();
        model.addAttribute("users", birthdayUsers);
        model.addAttribute("today", LocalDate.now());
        return "todayBirthday";
    }

    // Get users whose anniversary is today
    @GetMapping("/user/anniversaryToday")
    public String getUserByAnniversaryDay(Model model) {
        List<User> anniversaryUsers = birthdayEmailService.getAnniversaryToday();
        model.addAttribute("users", anniversaryUsers);
        model.addAttribute("today", LocalDate.now());
        return "todayAnniversary";
    }

    // Combined today's celebrations (birthdays and anniversaries)
    @GetMapping("/todayCelebrations")
    public String getTodayCelebrations(Model model) {
        List<User> birthdayUsers = birthdayEmailService.getBirthdayToday();
        List<User> anniversaryUsers = birthdayEmailService.getAnniversaryToday();
        model.addAttribute("birthdayUsers", birthdayUsers);
        model.addAttribute("anniversaryUsers", anniversaryUsers);
        model.addAttribute("today", LocalDate.now());
        return "todayCelebrations";
    }

    // Add delete mapping to delete user by id
    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable int id) {
        birthdayEmailService.deleteUserById(id);
        return "redirect:/birthday-email/allUserDetails";
    }

    // Manual trigger for anniversary emails
    @GetMapping("/sendAnniversary")
    public ResponseEntity<String> sendAnniversaryEmail() {
        birthdayEmailService.sendAnniversaryEmail();
        return ResponseEntity.ok("Anniversary Email Sent Successfully");
    }

}
