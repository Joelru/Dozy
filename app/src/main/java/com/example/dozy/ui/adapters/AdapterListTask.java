package com.example.dozy.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dozy.data.Task;
import com.example.dozy.databinding.ItemTaskBinding;
import com.example.dozy.model.TaskViewModel;
import com.example.dozy.ui.fragments.CurrentTask;
import com.example.dozy.ui.interfaces.OnTaskListener;
import com.example.dozy.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AdapterListTask extends RecyclerView.Adapter<AdapterListTask.ViewHolder> {
    private List<Task> taskList = new ArrayList<>();
    private TaskViewModel viewModel;
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
            viewModel = new ViewModelProvider(context).get(TaskViewModel.class);
        }

        void bind(Task task) {
            if (!binding.contentItem.getText().toString().equals(task.titleTask)) {
                binding.contentItem.setText(task.titleTask);
            }
            binding.layoutCompletedTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    task.completed = true;
                    Utils.modifyStyle(view.getContext(), binding.contentItem, binding.layoutCompletedTask, binding.checkButtomCompleted, task.completed);
                    viewModel.update(task);

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        taskList.remove(position);
                        notifyItemRemoved(position);
                    }

                }
            });


        }
    }

    public void submitList(List<Task> tasks) {
        this.taskList = tasks;
        notifyDataSetChanged();
    }

    public Task getItemAt(int position) {
        return taskList.get(position);
    }

}
