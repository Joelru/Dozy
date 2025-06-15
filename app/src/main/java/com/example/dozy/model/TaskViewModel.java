package com.example.dozy.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dozy.data.Task;
import com.example.dozy.data.TaskDatabase;
import com.example.dozy.data.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository repository;
    private LiveData<List<Task>> allTask;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        TaskDatabase db = TaskDatabase.getInstance(application);
        repository = new TaskRepository(db.taskDao());
        allTask = repository.getAllTask();
    }

    public LiveData<List<Task>> getAllTask() {
        return allTask;
    }

    public void insert(Task task) {
        repository.insert(task);
    }

}
