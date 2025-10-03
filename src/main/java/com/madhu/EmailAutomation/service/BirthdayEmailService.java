package com.madhu.EmailAutomation.service;

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
    // add method to delete user by id
    void deleteUserById(int id);
    // Add method to get users whose anniversary is today
    List<User> getAnniversaryToday();
    // Add method to send anniversary emails
    String sendAnniversaryEmail();

}
