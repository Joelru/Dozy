package com.example.dozy.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.dozy.R;
import com.example.dozy.databinding.ActivityMainBinding;
import com.example.dozy.model.TaskViewModel;
import com.example.dozy.utils.AddTaskDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        listeners();
    }

    private void listeners() {
        binding.fab.setOnClickListener(view -> showBottomDialog());

    }

    /**
     * Muestra un dialogo inferior para agregar una tarea.
     * Oculta temporalmente la barra inferior mientras el dialogo esta activo y la vuelve a mostrar con un pequeño retraso al cerrarse.
     */
    private void showBottomDialog() {
        binding.bottomAppBar.setVisibility(View.GONE);
        TaskViewModel viewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        AddTaskDialog addTaskDialog = new AddTaskDialog(this, task -> {
            viewModel.insert(task); // Inserta la tarea cuando se crea
            new Handler().postDelayed(() -> binding.bottomAppBar.setVisibility(View.VISIBLE), 150);
        });

        addTaskDialog.show();
    }
}