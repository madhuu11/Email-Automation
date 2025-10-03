package com.madhu.EmailAutomation.controller;

//generate rest controller email template controller class with request mapping /email-template
import com.madhu.EmailAutomation.service.EmailTemplateService;
import com.madhu.EmailAutomation.entity.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute("categories", com.madhu.EmailAutomation.util.Category.values());
        return "addTemplate";
    }

    // Create post mapping /addEmailTemplate to add email template details
    @PostMapping("/addEmailTemplate")
    public String addTemplate(@ModelAttribute EmailTemplate emailTemplate) {
        // Call add email template method from email template service
        emailTemplateService.addEmailTemplate(emailTemplate);
        return "redirect:/email-template/allTemplateDetails";
    }

    // Add delete mapping to delete email template by id
    @GetMapping("/delete-template/{id}")
    public String deleteTemplate(@PathVariable int id) {
        emailTemplateService.deleteEmailTemplate(id);
        return "redirect:/email-template/allTemplateDetails";
    }

}