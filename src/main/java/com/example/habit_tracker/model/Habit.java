package com.example.habit_tracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "habit")
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String habitName;
    private LocalDate createdDate;
    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HabitLog> logs = new ArrayList<>();


    // Default constructor (needed by Hibernate)
    public Habit() {}

    // Constructor without ID (for creating new habits)
    public Habit(String habitName, LocalDate createdDate) {
        this.habitName = habitName;
        this.createdDate = createdDate;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getHabitName() { return habitName; }
    public void setHabitName(String habitName) { this.habitName = habitName; }

    public LocalDate getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }

}
