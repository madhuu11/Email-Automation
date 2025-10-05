package com.madhu.EmailAutomation.service;

import com.madhu.EmailAutomation.entity.Event;
import com.madhu.EmailAutomation.entity.EmailTemplate;
import com.madhu.EmailAutomation.repository.EmailTemplateRepository;
import com.madhu.EmailAutomation.repository.EventRepository;
import com.madhu.EmailAutomation.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private com.madhu.EmailAutomation.repository.UserRepository userRepository;

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
            logger.info("Processing event: id={}, templateName={}, category={}", event.getId(), event.getTemplateName(), event.getCategory());
            if (event.getTemplateName() == null) {
                logger.warn("Event {} has no templateName set. Skipping.", event.getId());
                continue;
            }
            EmailTemplate template = emailTemplateRepository.findEmailTemplateByTemplateName(event.getTemplateName().name());
            if (template == null) {
                logger.warn("No email template found for event: {} (templateName={}, category={})", event.getId(), event.getTemplateName(), event.getCategory());
                continue;
            }
            String subject = template.getSubject();
            String message = template.getBody();
            boolean sent = false;
            if (event.getCategory() != null) {
                List<com.madhu.EmailAutomation.entity.User> users = userRepository.findByCategory(event.getCategory());
                for (com.madhu.EmailAutomation.entity.User user : users) {
                    if (user.getEmail() != null && user.getEmail().contains("@")) {
                        EmailUtil.sendMail(javaMailSender, user.getEmail(), subject, message);
                        logger.info("Event email sent to: {} for event {}", user.getEmail(), event.getId());
                        sent = true;
                    }
                }
            }
            if (sent) {
                event.setEmailSent(true);
                eventRepository.save(event);
            } else {
                logger.warn("No valid user emails found for event: {}", event.getId());
            }
        }
    }

    @Override
    public void sendMailForEvent(int id) {
        Event event = getEventById(id);
        if (event == null) return;
        EmailTemplate template = emailTemplateRepository.findEmailTemplateByTemplateName(event.getTemplateName().name());
        if (template != null) {
            String subject = template.getSubject();
            String message = template.getBody();
            boolean sent = false;
            if (event.getCategory() != null) {
                List<com.madhu.EmailAutomation.entity.User> users = userRepository.findByCategory(event.getCategory());
                for (com.madhu.EmailAutomation.entity.User user : users) {
                    if (user.getEmail() != null && user.getEmail().contains("@")) {
                        try {
                            EmailUtil.sendMail(javaMailSender, user.getEmail(), subject, message);
                            logger.info("Event email sent to: {} for event {}", user.getEmail(), event.getId());
                            sent = true;
                        } catch (Exception e) {
                            logger.error("Failed to send email to: {} for event {}. Exception: {}", user.getEmail(), event.getId(), e.getMessage(), e);
                        }
                    } else {
                        logger.warn("User {} has invalid email: {}", user.getId(), user.getEmail());
                    }
                }
            }
            if (sent) {
                event.setEmailSent(true);
                eventRepository.save(event);
            } else {
                logger.warn("No valid user emails found or all email sends failed for event: {}", event.getId());
            }
        } else {
            logger.warn("No email template found for event: {}", id);
        }
    }
}
