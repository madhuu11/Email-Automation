package com.madhu.EmailAutomation.service;

import com.madhu.EmailAutomation.entity.Event;
import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event getEventById(int id);
    void addEvent(Event event);
    void updateEvent(Event event);
    void deleteEvent(int id);
    void sendEventEmailsForToday();
}
