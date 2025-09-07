package com.madhu.EmailAutomation.Service;

//generate email template service implementation class which implements email template service interface
import com.madhu.EmailAutomation.entity.EmailTemplate;
import com.madhu.EmailAutomation.repository.EmailTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {
    //autowired email template repository
    @Autowired
    EmailTemplateRepository emailTemplateRepository;

    //implement the getEmailTemplateByName method
    @Override
    public EmailTemplate getEmailTemplateByName(String templateName) {
        return emailTemplateRepository.findEmailTemplateByTemplateName(templateName);
    }

    //implement the getEmailTemplateById method
    @Override
    public EmailTemplate getEmailTemplateById(int id) {
        return emailTemplateRepository.findById(id).orElse(null);
    }

    //implement the getAllEmailTemplates method
    @Override
    public List<EmailTemplate> getAllEmailTemplates() {
        return emailTemplateRepository.findAll();
    }

    //implement the addEmailTemplate method
    @Override
    public void addEmailTemplate(EmailTemplate emailTemplate) {
        emailTemplateRepository.save(emailTemplate);
    }

    //implement the updateEmailTemplate method
    @Override
    public void updateEmailTemplate(EmailTemplate emailTemplate) {
        emailTemplateRepository.save(emailTemplate);
    }

    //implement the deleteEmailTemplate method
    @Override
    public void deleteEmailTemplate(int id) {
        emailTemplateRepository.deleteById(id);
    }
}
