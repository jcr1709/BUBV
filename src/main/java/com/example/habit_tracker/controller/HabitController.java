package com.example.habit_tracker.controller;


import com.example.habit_tracker.dto.HabitDTO;
import com.example.habit_tracker.model.Habit;
import com.example.habit_tracker.service.HabitLogService;
import com.example.habit_tracker.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habits")
public class HabitController {

    @Autowired
    private HabitService habitService;

    @PostMapping
    public Habit saveNewHabit(@RequestBody Habit habit){
        return habitService.saveHabit(habit);
    }

    @GetMapping
    public List<HabitDTO> getAllHabits(){
        return habitService.getAllHabits();
    }

    @GetMapping("/{id}")
    public Optional<Habit> getHabitById(@PathVariable Long id){
        return habitService.getHabitById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteHabit(@PathVariable Long id){
        habitService.deleteHabit(id);
        return "Deleted";
    }
}
