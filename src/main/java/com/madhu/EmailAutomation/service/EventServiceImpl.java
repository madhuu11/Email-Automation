package com.madhu.EmailAutomation.service;

import com.madhu.EmailAutomation.entity.Event;
import com.madhu.EmailAutomation.entity.EmailTemplate;
import com.madhu.EmailAutomation.repository.EmailTemplateRepository;
import com.madhu.EmailAutomation.repository.EventRepository;
import com.madhu.EmailAutomation.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(int id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElse(null);
    }

    @Override
    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void updateEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }

    @Override
    public void sendEventEmailsForToday() {
        LocalDate today = LocalDate.now();
        List<Event> events = eventRepository.findByEventDateAndEmailSentFalse(today);
        for (Event event : events) {
            // Find template by template_id and category
            EmailTemplate template = null;
            if (event.getCategory() != null) {
                template = emailTemplateRepository.findEmailTemplateByTemplateNameAndCategory("event", event.getCategory());
            }
            if (template == null) {
                template = emailTemplateRepository.findById(event.getTemplate_id()).orElse(null);
            }
            if (template == null) {
                System.out.println("No email template found for event: " + event.getId());
                continue;
            }
            String subject = template.getSubject();
            String message = template.getBody();
            // Optionally, replace placeholders if you have event/user info
            // Send email (assuming you have a recipient field or logic)
            // For demo, let's assume event has a location as email (replace with actual recipient logic)
            if (event.getLocation() != null && event.getLocation().contains("@")) {
                EmailUtil.sendMail(javaMailSender, event.getLocation(), subject, message);
                event.setEmailSent(true);
                eventRepository.save(event);
                System.out.println("Event email sent to: " + event.getLocation());
            } else {
                System.out.println("No valid recipient for event: " + event.getId());
            }
        }
    }
}
