package com.madhu.EmailAutomation.service;

import com.madhu.EmailAutomation.entity.EmailTemplate;
import com.madhu.EmailAutomation.entity.User;
import com.madhu.EmailAutomation.repository.EmailTemplateRepository;
import com.madhu.EmailAutomation.repository.UserRepository;
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
    public String sendEmail() {
        //get birthday today
        List<User> users = getBirthdayToday();
        //if users list is not empty
        if (!users.isEmpty()) {
            //iterate through the list of users
            //get the email template by name from the repository and store it on email template
            EmailTemplate emailTemplate = emailTemplateRepository.findEmailTemplateByTemplateName("birthday");
            for (User user : users) {
                //call the replace place holder method to replace the place holder with user details
                String message = replacePlaceHolders(emailTemplate.getBody(), user);
                //call the replace place holder method to replace the place holder with user details for subject
                String subject = replacePlaceHolders(emailTemplate.getSubject(), user);

                // print the email message
                System.out.println("Sending email to : " + user.getEmail());
                System.out.println(message);

                //sends email
                sendMail(user, subject, message);

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

    //generate send mail method to send email to user using simpleMailMessage
    private void sendMail(User user, String subject, String message) {
        //create a simple mail message object
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //set the to, subject and message
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        //send the email
        javaMailSender.send(mailMessage);
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
}
