# BUBV - Be Ur Best Version (Habit Tracker)

## Goal
A web application to help users build and track habits consistently.

## Features
1. Add a new habit.
2. Mark that habit as done for the day.
3. View all habits(complete vs incomplete).
4. Delete a habit.
5. Display weekly stat for each habit.

## Future Enhancements
- Categories for habits (Health, Work, Learning, etc.)
- Monthly stats and streaks
- User authentication & login
- Notifications / reminders

## API Endpoints (Phase 1)
- **POST** `/api/habits` → Create a habit
- **GET** `/api/habits` → View all habits
- **POST** `/api/habits/{id}/complete` → Mark as done for today
- **DELETE** `/api/habits/{id}` → Delete a habit
- **GET** `/api/habits/{id}/stats/weekly` → Weekly stats

## Tech Stack
- **Backend:** Spring Boot
- **Database:** MySql
- **Frontend:** Javascript
- **Version Control:** Git, Github