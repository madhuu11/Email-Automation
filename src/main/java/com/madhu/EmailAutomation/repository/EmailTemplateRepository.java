package com.madhu.EmailAutomation.repository;

//generate email template repository interface which extends JPA repository
import com.madhu.EmailAutomation.entity.EmailTemplate;
import com.madhu.EmailAutomation.util.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Integer> {
    EmailTemplate findEmailTemplateByTemplateName(String birthday);
    EmailTemplate findEmailTemplateByTemplateNameAndCategory(String templateName, Category category);
    EmailTemplate findEmailTemplateByTemplateNameAndCategoryIsNull(String templateName);
}

