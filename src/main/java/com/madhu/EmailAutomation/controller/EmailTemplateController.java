package com.madhu.EmailAutomation.controller;

//generate rest controller email template controller class with request mapping /email-template
import com.madhu.EmailAutomation.Service.EmailTemplateService;
import com.madhu.EmailAutomation.entity.EmailTemplate;
import com.madhu.EmailAutomation.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/email-template")
public class EmailTemplateController {

    // Autowire email template service
    @Autowired
    private EmailTemplateService emailTemplateService;

    // Generate get mapping /all to get all email template details and return email template details
    @GetMapping("/allTemplateDetails")
    public String getAllEmailTemplateDetails(Model model) {
        // Call get all email template details method from email template service
        List<EmailTemplate> emailTemplateDetails = emailTemplateService.getAllEmailTemplates();
        model.addAttribute("templates", emailTemplateDetails);
        return "allTemplates";
    }

    // create get mapping /addEmailTemplate to add email template details
    @GetMapping("/addEmailTemplate")
    public String showAddTemplateForm(Model model) {
        model.addAttribute("template", new EmailTemplate());
        return "addTemplate";
    }

    // Create post mapping /addEmailTemplate to add email template details
    @PostMapping("/addEmailTemplate")
    public String addTemplate(@ModelAttribute EmailTemplate emailTemplate) {
        // Call add email template method from email template service
        emailTemplateService.addEmailTemplate(emailTemplate);
        return "redirect:/email-template/allTemplateDetails";
    }

    /*
    // Create get mapping /get-by-id to get email template details by id and return email template details
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<EmailTemplate> getEmailTemplateById(@PathVariable int id) {
        // Call get email template by id method from email template service
        EmailTemplate emailTemplate = emailTemplateService.getEmailTemplateById(id);
        // Return response entity with email template details
        return ResponseEntity.ok(emailTemplate);
    }

    // Create put mapping /update to update email template details and return nothing
    @PutMapping("/update")
    public ResponseEntity<Void> updateEmailTemplate(@RequestBody EmailTemplate emailTemplate) {
        // Call update email template method from email template service
        emailTemplateService.updateEmailTemplate(emailTemplate);
        // Return response entity with message
        return ResponseEntity.ok().build();
    }

    // Create delete mapping /delete to delete email template details and return nothing
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmailTemplate(@PathVariable int id) {
        // Call delete email template method from email template service
        emailTemplateService.deleteEmailTemplate(id);
        // Return response entity with message
        return ResponseEntity.ok().build();
    }

    //create get mapping /get-by-name to get email template details by name and return email template details
    @GetMapping("/get-by-name/{templateName}")
    public ResponseEntity<EmailTemplate> getEmailTemplateByName(@PathVariable String templateName) {
        // Call get email template by name method from email template service
        EmailTemplate emailTemplate = emailTemplateService.getEmailTemplateByName(templateName);
        // Return response entity with email template details
        return ResponseEntity.ok(emailTemplate);
    }
     */

}