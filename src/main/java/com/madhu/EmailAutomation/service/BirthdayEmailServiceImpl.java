package com.madhu.EmailAutomation.service;

import com.madhu.EmailAutomation.entity.EmailTemplate;
import com.madhu.EmailAutomation.entity.User;
import com.madhu.EmailAutomation.repository.EmailTemplateRepository;
import com.madhu.EmailAutomation.repository.UserRepository;
import com.madhu.EmailAutomation.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
//import simple mail message
import org.springframework.mail.SimpleMailMessage;

import java.time.LocalDate;
import java.util.List;

//generate birthday email service implementation class and implement the sendEmail method
@Service
public class BirthdayEmailServiceImpl implements BirthdayEmailService {
    //autowired user repository
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailTemplateRepository emailTemplateRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String sendBirthdayEmail() {
        //get birthday today
        List<User> users = getBirthdayToday();
        //if users list is not empty
        if (!users.isEmpty()) {
            for (User user : users) {
                // Try to find a category-specific template first
                EmailTemplate emailTemplate = emailTemplateRepository.findEmailTemplateByTemplateNameAndCategory("birthday", user.getCategory());
                // If not found, fall back to generic template
                if (emailTemplate == null) {
                    emailTemplate = emailTemplateRepository.findEmailTemplateByTemplateNameAndCategoryIsNull("birthday");
                }
                if (emailTemplate == null) {
                    System.out.println("No email template found for user: " + user.getEmail());
                    continue;
                }
                String message = replacePlaceHolders(emailTemplate.getBody(), user);
                //call the replace place holder method to replace the place holder with user details for subject
                String subject = replacePlaceHolders(emailTemplate.getSubject(), user);

                // print the email message
                System.out.println("Sending email to : " + user.getEmail());
                System.out.println(message);

                // Use EmailUtil to send mail
                EmailUtil.sendMail(javaMailSender, user.getEmail(), subject, message);

                //print the email sent to user email
                System.out.println("Email sent to " + user.getEmail());
            }
            return "Email sent successfully";
        }
        return null;
    }

    //create a method to replace place holder with user details
    private String replacePlaceHolders(String message, User user) {
        //replace the place holder with user name
        message = message.replace("{name}", user.getName());
        //replace the place holder with user email
        message = message.replace("{email}", user.getEmail());
        return message;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getBirthdayToday() {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        return userRepository.findByDobMonthAndDobDay(month, day);
    }

    @Override
    public void addUser(User user) {
        //create user data and add to the database
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAnniversaryToday() {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        return userRepository.findByAnniversaryMonthAndAnniversaryDay(month, day);
    }

    @Override
    public String sendAnniversaryEmail() {
        List<User> users = getAnniversaryToday();
        if (!users.isEmpty()) {
            for (User user : users) {
                // Try to find a category-specific template first
                EmailTemplate emailTemplate = emailTemplateRepository.findEmailTemplateByTemplateNameAndCategory("anniversary", user.getCategory());
                // If not found, fall back to generic template
                if (emailTemplate == null) {
                    emailTemplate = emailTemplateRepository.findEmailTemplateByTemplateNameAndCategoryIsNull("anniversary");
                }
                if (emailTemplate == null) {
                    System.out.println("No anniversary email template found for user: " + user.getEmail());
                    continue;
                }
                String message = replacePlaceHolders(emailTemplate.getBody(), user);
                String subject = replacePlaceHolders(emailTemplate.getSubject(), user);
                System.out.println("Sending anniversary email to : " + user.getEmail());
                System.out.println(message);
                EmailUtil.sendMail(javaMailSender, user.getEmail(), subject, message);
                System.out.println("Anniversary email sent to " + user.getEmail());
            }
            return "Anniversary emails sent successfully";
        }
        return null;
    }
}
