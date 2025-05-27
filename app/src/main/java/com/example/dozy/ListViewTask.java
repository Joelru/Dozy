package com.example.dozy;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListViewTask#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewTask extends Fragment {

    private List<Long> selectedTimestamps = new ArrayList<>();
    private TextView txtSelectedDates;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view_task, container, false);

        Button btnAddDate = view.findViewById(R.id.btnTest);


        btnAddDate.setOnClickListener(v -> showDialogAddTask());

        return view;
    }

    private void showDialogAddTask() {

        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View vista = inflater.inflate(R.layout.dialog_add_task, null);

        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setView(vista)
                .create();

        dialog.show();
    }

}
