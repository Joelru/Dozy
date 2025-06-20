package com.example.dozy.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dozy.R;
import com.example.dozy.data.Task;
import com.example.dozy.databinding.FragmentAddTaskBinding;
import com.example.dozy.databinding.FragmentCurrentTaskBinding;
import com.example.dozy.model.TaskViewModel;
import com.example.dozy.ui.adapters.AdapterListTask;
import com.example.dozy.ui.interfaces.OnTaskListener;
import com.example.dozy.utils.SlideInLeftAnimator;
import com.example.dozy.utils.SwipeToActionCallback;
import com.example.dozy.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentTask#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentTask extends Fragment implements OnTaskListener {
    private AdapterListTask adapter;
    private FragmentCurrentTaskBinding binding;
    private TaskViewModel taskViewModel;
    private Task task = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CurrentTask() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment currentTask.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentTask newInstance(String param1, String param2) {
        CurrentTask fragment = new CurrentTask();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCurrentTaskBinding.inflate(inflater, container, false);
        initRecycler();
        initCallBacks();
        initViews();
        return binding.getRoot();
    }

    private void initViews() {
        binding.tvDate.setText(Utils.getFormattedCurrentDate());
    }

    private void initCallBacks() {
        SwipeToActionCallback callback = new SwipeToActionCallback(requireContext()) {
            @Override
            public void onDeleteConfirmed(int pos) {
                Task task = adapter.getItemAt(pos);
                taskViewModel.remove(task);
            }

            @Override
            public void onCompleteConfirmed(int pos) {
                Task task = adapter.getItemAt(pos);
                task.completed = true;
                taskViewModel.update(task);
            }
        };

        new ItemTouchHelper(callback).attachToRecyclerView(binding.rvTaskList);
    }

    private void initRecycler() {
        adapter = new AdapterListTask(this);
        binding.rvTaskList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvTaskList.setAdapter(adapter);
        binding.rvTaskList.setItemAnimator(new SlideInLeftAnimator());

        taskViewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);

        taskViewModel.getAllTask().observe(getViewLifecycleOwner(), tasks -> adapter.submitList(tasks));
    }

    public void onTaskClick(Task task) {

    }

    @Override
    public void onTaskLongClick(Task task) {

    }
}