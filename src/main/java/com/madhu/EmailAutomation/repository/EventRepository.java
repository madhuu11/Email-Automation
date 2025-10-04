package com.madhu.EmailAutomation.repository;

import com.madhu.EmailAutomation.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByEventDateBefore(LocalDate date);
    List<Event> findByEventDate(LocalDate date);
    List<Event> findByEventDateAfter(LocalDate date);
    List<Event> findByEventDateAndEmailSentFalse(LocalDate date);
}
