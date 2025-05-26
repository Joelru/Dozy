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
 * Use the {@link AddTask#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddTask extends Fragment {

    private List<Long> selectedTimestamps = new ArrayList<>();

    private FloatingActionButton btnAddDate;
    private TextView txtSelectedDates;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        btnAddDate = view.findViewById(R.id.floating_action_button);

        btnAddDate.setOnClickListener(v -> mostrarDialogoConVistaPersonalizada());

        return view;
    }

    private void mostrarDialogoConVistaPersonalizada() {
        // Inflar el layout del diálogo
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View vista = inflater.inflate(R.layout.dialog_add_task, null);

        // Referencias a vistas internas del diálogo


        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setView(vista)
                .create();

        dialog.show();
    }

}
