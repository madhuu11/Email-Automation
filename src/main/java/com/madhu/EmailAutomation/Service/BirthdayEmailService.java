package com.madhu.EmailAutomation.Service;

import com.madhu.EmailAutomation.entity.User;

import java.util.List;

//generate birthday email service interface with sendEmail method
public interface BirthdayEmailService {
    String sendEmail();
    //add method to get all users and returns list of users
    List<User> getAllUsers();
    //add method to get User birthday today and returns list of users
    List<User> getBirthdayToday();
    //add method to add user
    void addUser(User user);

}

