package com.example.habit_tracker.dto;

public class HabitDTO {
    private Long id;
    private String habitName;
    private boolean completed;

    public HabitDTO(Long id, String habitName, boolean completed) {
        this.id = id;
        this.habitName = habitName;
        this.completed = completed;
    }
    public Long getId() {
        return id;
    }
    public String getHabitName() {
        return habitName;
    }
    public boolean isCompleted() {
        return completed;
    }
}
