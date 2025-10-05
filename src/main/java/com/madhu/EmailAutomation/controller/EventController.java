package com.madhu.EmailAutomation.controller;

import com.madhu.EmailAutomation.entity.Event;
import com.madhu.EmailAutomation.repository.EmailTemplateRepository;
import com.madhu.EmailAutomation.service.EventService;
import com.madhu.EmailAutomation.util.Category;
import com.madhu.EmailAutomation.util.TemplateName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

    @GetMapping
    public String getAllEvents(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "allEvents";
    }

    @GetMapping("/add")
    public String showAddEventForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("categories", Category.values());
        model.addAttribute("templateNames", TemplateName.values());
        model.addAttribute("formAction", "/events/add");
        model.addAttribute("submitLabel", "Add Event");
        model.addAttribute("formTitle", "Add Event");
        return "eventForm";
    }

    @PostMapping("/add")
    public String addEvent(@ModelAttribute Event event) {
        eventService.addEvent(event);
        return "redirect:/events";
    }

    @GetMapping("/edit/{id}")
    public String showEditEventForm(@PathVariable int id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        model.addAttribute("categories", Category.values());
        model.addAttribute("templateNames", TemplateName.values());
        model.addAttribute("formAction", "/events/edit/" + id);
        model.addAttribute("submitLabel", "Update Event");
        model.addAttribute("formTitle", "Edit Event");
        return "eventForm";
    }

    @PostMapping("/edit/{id}")
    public String updateEvent(@PathVariable int id, @ModelAttribute Event event) {
        event.setId(id);
        eventService.updateEvent(event);
        return "redirect:/events";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable int id) {
        eventService.deleteEvent(id);
        return "redirect:/events";
    }

    @GetMapping("/sendMail/{id}")
    public String sendMailForEvent(@PathVariable int id) {
        eventService.sendMailForEvent(id);
        return "redirect:/events";
    }
}
