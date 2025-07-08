package com.example.dozy.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String titleTask;
    public String contentDescription;
    public String dateTask;
    public String repeatType;
    public boolean isRecurrent;
    public boolean completed;

    public String getTitleTask() {
        return titleTask;
    }

    public void setTitleTask(String titleTask) {
        this.titleTask = titleTask;
    }

    public Task(String titleTask, String contentDescription, String dateTask, String repeatType, boolean isRecurrent, boolean completed) {
        this.titleTask = titleTask;
        this.contentDescription = contentDescription;
        this.dateTask = dateTask;
        this.repeatType = repeatType;
        this.isRecurrent = isRecurrent;
        this.completed = completed;
    }

    public Task() {
    }
}
