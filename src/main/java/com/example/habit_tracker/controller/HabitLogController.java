package com.example.habit_tracker.controller;

import com.example.habit_tracker.model.HabitLog;
import com.example.habit_tracker.service.HabitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("habit-logs")
public class HabitLogController {

    @Autowired
    private HabitLogService habitLogService;

    @PostMapping("/{id}")
    public HabitLog markHabitsDone(
            @PathVariable Long id,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Boolean completed) {

        LocalDate logDate = (date == null) ? LocalDate.now() : LocalDate.parse(date);
        boolean isCompleted = (completed == null) ? true : completed; // default true
        return habitLogService.markHabitDone(id, logDate, isCompleted);
    }

    @GetMapping("/{id}/logs")
    public List<HabitLog> getLogsByHabit(@PathVariable Long id){
        return habitLogService.getLogsByHabit(id);
    }

    @GetMapping("/{id}/weekly")
    public List<HabitLog> getWeeklyStats(@PathVariable Long id){
        return habitLogService.getWeeklyStats(id);
    }
}
