package com.example.dozy.ui.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dozy.R;
import com.example.dozy.data.Task;
import com.example.dozy.data.TaskDatabase;
import com.example.dozy.databinding.FragmentAddTaskBinding;
import com.example.dozy.ui.MainActivity;
import com.example.dozy.utils.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTask#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTask extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String date = "00/00/00";
    private FragmentAddTaskBinding binding;


    public AddTask() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddTask.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTask newInstance(String param1, String param2) {
        AddTask fragment = new AddTask();
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
        binding = FragmentAddTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupButtons();
        initDB();
//        validations();

    }

    private boolean validations() {
        if (Utils.validateText(binding.titleTask) && Utils.validateText(binding.descriptionTask)) {
            return true;
        }
        return false;
    }

    private void initDB() {
        RoomDatabase.Builder<TaskDatabase> tasks = Room.databaseBuilder(getContext(),
                TaskDatabase.class,
                "tasks");
        tasks.allowMainThreadQueries().build();
    }

    private void setupButtons() {
        binding.calendarImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar();
            }
        });
        binding.saveTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void showCalendar() {

        final Calendar calendar = Calendar.getInstance();
        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(

                binding.calendarImgBtn.getContext(),
                (view, year, monthOfYear, dayOfMonth) -> {
                    date = dayOfMonth + "/" + monthOfYear + "/" + year;
                },
                anio, mes, dia
        );
        datePickerDialog.show();
    }

}