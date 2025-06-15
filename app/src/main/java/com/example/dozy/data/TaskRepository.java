package com.example.dozy.data;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;

public class TaskRepository {
    private Dao taskDao;

    public TaskRepository(Dao taskDao) {
        this.taskDao = taskDao;
    }

    public LiveData<List<Task>> getAllTask() {
        return taskDao.getAllTasks();
    }

    public void insert(Task task) {
        Executors.newSingleThreadExecutor().execute(() -> taskDao.insert(task));
    }

}
