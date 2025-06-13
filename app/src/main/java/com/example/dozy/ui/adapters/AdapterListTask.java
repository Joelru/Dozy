package com.example.dozy.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dozy.data.Task;
import com.example.dozy.databinding.ItemTaskBinding;
import com.example.dozy.ui.fragments.CurrentTask;
import com.example.dozy.ui.interfaces.OnTaskListener;

import java.util.ArrayList;
import java.util.List;

public class AdapterListTask extends RecyclerView.Adapter<AdapterListTask.ViewHolder> {
    private List<Task> taskList = new ArrayList<>();
    private OnTaskListener listener = null;
    private CurrentTask context;

    public AdapterListTask(CurrentTask context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater view = LayoutInflater.from(parent.getContext());
        ItemTaskBinding binding = ItemTaskBinding.inflate(view, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(taskList.get(position));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemTaskBinding binding;

        public ViewHolder(@NonNull ItemTaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Task task) {
            if (!binding.contentItem.getText().toString().equals(task.titleTask)) {
                binding.contentItem.setText(task.titleTask);
            }
            binding.checkBoxCompleted.setChecked(task.completed);

        }
    }

    public void submitList(List<Task> tasks) {
        this.taskList = tasks;
        notifyDataSetChanged();
    }


}
