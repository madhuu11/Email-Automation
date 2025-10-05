package com.madhu.EmailAutomation.repository;

import com.madhu.EmailAutomation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE MONTH(u.dateOfBirth) = :month AND DAY(u.dateOfBirth) = :day")
    List<User> findByDobMonthAndDobDay(@Param("month") int month, @Param("day") int day);

    @Query("SELECT u FROM User u WHERE MONTH(u.anniversary) = :month AND DAY(u.anniversary) = :day")
    List<User> findByAnniversaryMonthAndAnniversaryDay(@Param("month") int month, @Param("day") int day);

    List<User> findByCategory(com.madhu.EmailAutomation.util.Category category);
}