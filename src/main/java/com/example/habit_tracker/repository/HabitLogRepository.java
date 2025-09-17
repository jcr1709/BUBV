package com.example.habit_tracker.repository;

import com.example.habit_tracker.model.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
    HabitLog findTopByHabitIdOrderByDateDesc(Long habitId);

}
