package com.example.dozy.data;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void remove(Task task);

    @Query("SELECT * FROM tasks WHERE completed = 0 ORDER BY id DESC ")
    LiveData<List<Task>> getAllTasks();

    // Obtener solo tareas completadas
    @Query("SELECT * FROM tasks WHERE completed = 1")
    List<Task> getCompletedTasks();

    // Obtener tareas por fecha exacta (para tareas no recurrentes)
    @Query("SELECT * FROM tasks WHERE dateTask = :dateTask")
    List<Task> getTasksByDate(String dateTask);

    // Obtener tareas recurrentes por tipo (ej: lunes a viernes, siempre)
    @Query("SELECT * FROM tasks WHERE isRecurrent = 1 AND repeatType = :repeatType")
    List<Task> getRecurrentTasksByType(String repeatType);

    // Obtener tarea por id
    @Query("SELECT * FROM tasks WHERE id = :id")
    Task getTaskById(int id);
}
