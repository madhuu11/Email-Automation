package com.madhu.EmailAutomation.entity;
//define entity class for email template with id, templateName, subject, body and with required annotations
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplate {
    //make the id field as primary key using generated value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String templateName;
    private String subject;
    private String body;

}
