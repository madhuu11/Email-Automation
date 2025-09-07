package com.madhu.EmailAutomation.Service;

import com.madhu.EmailAutomation.entity.EmailTemplate;

import java.util.List;

public interface EmailTemplateService {
    //add method to get email template by name, get template by id, get all templates, add template, update template and delete template
    EmailTemplate getEmailTemplateByName(String templateName);
    EmailTemplate getEmailTemplateById(int id);
    List<EmailTemplate> getAllEmailTemplates();
    void addEmailTemplate(EmailTemplate emailTemplate);
    void updateEmailTemplate(EmailTemplate emailTemplate);
    void deleteEmailTemplate(int id);

}