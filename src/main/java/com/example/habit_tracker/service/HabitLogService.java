package com.example.habit_tracker.service;

import com.example.habit_tracker.model.Habit;
import com.example.habit_tracker.model.HabitLog;
import com.example.habit_tracker.repository.HabitLogRepository;
import com.example.habit_tracker.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HabitLogService {
    @Autowired
    private HabitLogRepository habitLogRepository;

    @Autowired
    private HabitRepository habitRepository;

    public HabitLog markHabitDone(Long habitId, LocalDate date, boolean isCompleted){
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("habit not found"));

        List<HabitLog> logs = habitLogRepository.findAll();
        for(HabitLog log : logs){
            if(log.getHabit().getId().equals(habitId) && log.getDate().equals(date)){
                log.setIsCompleted(isCompleted);
                return habitLogRepository.save(log);
            }
        }

        HabitLog newLog = new HabitLog(date, isCompleted, habit);
        return habitLogRepository.save(newLog);
    }



    public List<HabitLog> getLogsByHabit(Long habitId){
        Habit habit=habitRepository.findById(habitId).orElseThrow(() -> new RuntimeException("Habit not found"));
        return habitLogRepository.findAll().stream()
                .filter(log -> log.getHabit().getId().equals(habitId)).toList();
    }

    public List<HabitLog> getWeeklyStats(Long habitId){
        Habit habit=habitRepository.findById(habitId).orElseThrow(() -> new RuntimeException("Habit not found"));
        LocalDate today=LocalDate.now();
        LocalDate weekAgo=LocalDate.now().minusDays(6);

        List<HabitLog> logs=habitLogRepository.findAll().stream()
                .filter(log -> log.getHabit().getId().equals(habitId)
                        && !log.getDate().isBefore(weekAgo)
                        && !log.getDate().isAfter(today)
                ).toList();
        Map<LocalDate,HabitLog> logmap=logs.stream().collect(Collectors.toMap(HabitLog::getDate, log -> log));
        List<HabitLog> res=new ArrayList<>();
        for(int i=0;i<7;i++){
            LocalDate date=weekAgo.plusDays(1);
            if(logmap.containsKey(date)){
                res.add(logmap.get(date));
            }
            else{
                HabitLog missing=new HabitLog(date,false,habit);
                res.add(missing);
            }
        }
        return res;
    }
}
