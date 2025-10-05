package com.madhu.EmailAutomation.service;

import com.madhu.EmailAutomation.entity.EmailTemplate;
import com.madhu.EmailAutomation.entity.User;
import com.madhu.EmailAutomation.repository.EmailTemplateRepository;
import com.madhu.EmailAutomation.repository.UserRepository;
import com.madhu.EmailAutomation.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//generate birthday email service implementation class and implement the sendEmail method
@Service
public class BirthdayEmailServiceImpl implements BirthdayEmailService {
    private static final Logger logger = LoggerFactory.getLogger(BirthdayEmailServiceImpl.class);
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
                    logger.warn("No email template found for user: {}", user.getEmail());
                    continue;
                }
                String message = replacePlaceHolders(emailTemplate.getBody(), user);
                //call the replace place holder method to replace the place holder with user details for subject
                String subject = replacePlaceHolders(emailTemplate.getSubject(), user);

                // print the email message
                logger.info("Sending email to : {}", user.getEmail());
                logger.debug(message);

                // Use EmailUtil to send mail
                EmailUtil.sendMail(javaMailSender, user.getEmail(), subject, message);

                //print the email sent to user email
                logger.info("Email sent to {}", user.getEmail());
            }
            return "Birthday emails sent successfully";
        }
        return "No birthdays today";
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
                EmailTemplate emailTemplate = emailTemplateRepository.findEmailTemplateByTemplateNameAndCategory("anniversary", user.getCategory());
                if (emailTemplate == null) {
                    emailTemplate = emailTemplateRepository.findEmailTemplateByTemplateNameAndCategoryIsNull("anniversary");
                }
                if (emailTemplate == null) {
                    logger.warn("No anniversary email template found for user: {}", user.getEmail());
                    continue;
                }
                String message = replacePlaceHolders(emailTemplate.getBody(), user);
                String subject = replacePlaceHolders(emailTemplate.getSubject(), user);
                logger.info("Sending anniversary email to : {}", user.getEmail());
                logger.debug(message);
                EmailUtil.sendMail(javaMailSender, user.getEmail(), subject, message);
                logger.info("Anniversary email sent to {}", user.getEmail());
            }
            return "Anniversary emails sent successfully";
        }
        return "No anniversaries today";
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }
}
