package com.example.dozy.ui.interfaces;

import com.example.dozy.data.Task;

public interface OnTaskListener {
    void onTaskClick(Task task);
    void onTaskLongClick(Task task);
}
