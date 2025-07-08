package com.example.dozy.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.example.dozy.R;
import com.example.dozy.data.Task;
import com.example.dozy.databinding.BottomsheetLayoutBinding;
import com.example.dozy.ui.interfaces.OnCreatedTaskListener;

import java.util.Objects;

/**
 * Dialogo reutilizable que permite al usuario agregar una nueva tarea.
 * Este dialogo aparece desde la parte inferior de la pantalla con una animacion suave.
 * Muestra un campo de texto para ingresar la tarea y un boton para confirmarla.
 * Cuando el usuario confirma, se crea una instancia de {@link Task} y se pasa al
 * callback {@link OnCreatedTaskListener} para que el controlador externo maneje su almacenamiento.
 * Adicionalmente, el dialogo solicita el foco del campo de texto y muestra el teclado automaticamente.
 * Requiere un {@link Context} y un {@link OnCreatedTaskListener} para funcionar.
 */
public class AddTaskDialog {

    private final Context context;
    private Dialog dialog;
    private final OnCreatedTaskListener onTaskCreatedListener;
    private BottomsheetLayoutBinding binding;

    public AddTaskDialog(Context context, OnCreatedTaskListener listener) {
        this.context = context;
        this.onTaskCreatedListener = listener;
    }

    /**
     * Muestra el dialogo en pantalla.
     * Configura la interfaz de usuario, la animacion y activa el teclado.
     */
    public void show() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding = BottomsheetLayoutBinding.inflate(dialog.getLayoutInflater());
        dialog.setContentView(binding.getRoot());

        Objects.requireNonNull(dialog.getWindow()).setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        setupUI();
        dialog.show();
    }

    /**
     * Configura los eventos de la interfaz de usuario:
     * - Enfoca el campo de texto.
     * - Muestra el teclado tras un retraso corto.
     * - Captura el texto ingresado y lo envia al listener como una nueva tarea.
     */
    private void setupUI() {
        binding.etAddTask.requestFocus();


        new Handler().postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(binding.etAddTask, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 200);

        binding.btnAddTask.setOnClickListener(v -> {
            String text = binding.etAddTask.getText().toString().trim();
            if (!text.isEmpty()) {
                Task newTask = new Task(text, "", "", "", true, false);
                onTaskCreatedListener.onTaskCreated(newTask);
                binding.etAddTask.setText("");
                dialog.dismiss();
            }
        });
    }
}
