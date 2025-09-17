document.addEventListener("DOMContentLoaded", () => {
    const newHabit = document.getElementById("newHabit");

    // Handle adding a new habit
    document.getElementById("submit-btn").addEventListener("click", (e) => {
        e.preventDefault();
        if (newHabit.value.trim() !== "") {
            addHabit(newHabit.value.trim());
            newHabit.value = "";
        }
    });

    // Load habits on page load
    loadHabits();
});

// Add a new habit
async function addHabit(habitName) {
    await fetch("http://localhost:8080/habits", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            habitName: habitName,
            createdDate: new Date().toISOString().split("T")[0],
            completed: false
        })
    });
    loadHabits();
}

// Load all habits
async function loadHabits() {
    const habitList = document.getElementById("habitsList");
    habitList.innerHTML = "";

    const response = await fetch("http://localhost:8080/habits", { method: "GET" });
    const habits = await response.json();

    for (let habit of habits) {
        const nh = document.createElement("li");

        // Habit name
        const span = document.createElement("span");
        span.textContent = habit.habitName + " ";
        nh.appendChild(span);

        // Delete button
        const nb = document.createElement("button");
        nb.textContent = "delete";
        nb.addEventListener("click", () => deleteHabit(habit.id));
        nh.appendChild(nb);

        // Completed checkbox
        const ch = document.createElement("input");
        ch.type = "checkbox";

        // ✅ Edge compatibility fix
        const isChecked = !!habit.completed;
        ch.checked = isChecked;
        ch.defaultChecked = isChecked;

        ch.addEventListener("change", () => markHabitDone(habit.id, ch.checked));
        nh.appendChild(ch);

        habitList.appendChild(nh);
    }
}

// Delete a habit
async function deleteHabit(id) {
    await fetch(`http://localhost:8080/habits/${id}`, { method: "DELETE" });
    loadHabits();
}

// Mark habit done (toggle)
async function markHabitDone(id, completed) {
    await fetch(`http://localhost:8080/habit-logs/${id}?completed=${completed}`, {
        method: "POST"
    });
    loadHabits();
}