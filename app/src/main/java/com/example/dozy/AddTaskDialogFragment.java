package com.example.dozy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddTaskDialogFragment extends DialogFragment {

    private EditText editTextTask;

    private List<Calendar> selectedDates = new ArrayList<>();

    public interface OnTaskAddedListener {
        void onTaskAdded(String taskName, List<Calendar> dates);
    }

    private OnTaskAddedListener listener;

    public void setOnTaskAddedListener(OnTaskAddedListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_task, container, false);

        editTextTask = view.findViewById(R.id.editTextTask);


        Button btnSave = view.findViewById(R.id.btnSave);
        Button btnCancel = view.findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(v -> {
            String task = editTextTask.getText().toString();


            if (listener != null) {
                listener.onTaskAdded(task, selectedDates);
            }
            dismiss();
        });

        btnCancel.setOnClickListener(v -> dismiss());

        return view;
    }
}
