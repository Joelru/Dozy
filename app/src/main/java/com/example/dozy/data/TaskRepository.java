package com.example.dozy.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private Dao taskDao;

    public TaskRepository(Dao taskDao) {
        this.taskDao = taskDao;
    }

    public LiveData<List<Task>> getAllTask() {
        return taskDao.getAllTasks();
    }

}
