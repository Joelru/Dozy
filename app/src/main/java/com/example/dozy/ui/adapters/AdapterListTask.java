package com.example.dozy.ui.adapters;

import android.os.Handler;
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
import com.example.dozy.ui.interfaces.OnCreatedTaskListener;
import com.example.dozy.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AdapterListTask extends RecyclerView.Adapter<AdapterListTask.ViewHolder> {
    private List<Task> taskList = new ArrayList<>();
    private TaskViewModel viewModel;
    private OnCreatedTaskListener listener = null;
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
            boolean isDone = task.completed;
            Utils.modifyStyle(
                    itemView.getContext(),
                    binding.contentItem,
                    binding.layoutCompletedTask,
                    binding.checkButtomCompleted,
                    isDone
            );
            binding.checkButtomCompleted.setChecked(isDone);

            binding.contentItem.setText(task.titleTask);
            binding.layoutCompletedTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    task.completed = true;
                    viewModel.update(task);

                    Utils.modifyStyle(
                            view.getContext(),
                            binding.contentItem,
                            binding.layoutCompletedTask,
                            binding.checkButtomCompleted,
                            true
                    );
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int pos = getAdapterPosition();
                            if (pos != RecyclerView.NO_POSITION) {
                                taskList.remove(pos);
                                notifyItemRemoved(pos);
                            }
                        }
                    }, 2000);


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
