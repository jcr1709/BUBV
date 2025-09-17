package com.example.habit_tracker.service;

import com.example.habit_tracker.dto.HabitDTO;
import com.example.habit_tracker.model.Habit;
import com.example.habit_tracker.model.HabitLog;
import com.example.habit_tracker.repository.HabitLogRepository;
import com.example.habit_tracker.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private HabitLogRepository habitLogRepository; // ✅ Add this

    public Habit saveHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public List<HabitDTO> getAllHabits() {
        List<Habit> habits = habitRepository.findAll();
        List<HabitDTO> habitDTOs = new ArrayList<>();

        for (Habit habit : habits) {
            HabitLog latestLog = habitLogRepository.findTopByHabitIdOrderByDateDesc(habit.getId());
            boolean completed = latestLog != null && latestLog.getIsCompleted();
            habitDTOs.add(new HabitDTO(habit.getId(), habit.getHabitName(), completed));
        }

        return habitDTOs;
    }

    public Optional<Habit> getHabitById(Long id) {
        return habitRepository.findById(id);
    }

    public void deleteHabit(Long id) {
        habitRepository.deleteById(id);
    }
}
