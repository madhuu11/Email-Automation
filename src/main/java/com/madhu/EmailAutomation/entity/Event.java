package com.madhu.EmailAutomation.entity;

import com.madhu.EmailAutomation.util.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String location;
    private LocalDate eventDate;
    private int template_id;
    @Enumerated(EnumType.STRING)
    private Category category;
    private boolean emailSent;

}