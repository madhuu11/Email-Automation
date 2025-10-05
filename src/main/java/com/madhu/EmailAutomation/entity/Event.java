package com.madhu.EmailAutomation.entity;

import com.madhu.EmailAutomation.util.Category;
import com.madhu.EmailAutomation.util.TemplateName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String location;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;
    @Enumerated(EnumType.STRING)
    private TemplateName templateName;
    @Enumerated(EnumType.STRING)
    private Category category;
    private boolean emailSent;

    public Event() {
        this.templateName = TemplateName.EVENT;
    }
}