package com.example.dozy.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.example.dozy.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class AddTaskDialog {

    private final Context context;
    private Dialog dialog = null;
    private final DialogInterface.OnDismissListener onDismissListener;

    public AddTaskDialog(Context context, DialogInterface.OnDismissListener onDismissListener) {
        this.context = context;
        this.onDismissListener = onDismissListener;
    }

    public void show() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheet_layout);
        Objects.requireNonNull(dialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        dialog.setOnDismissListener(onDismissListener);
        dialog.show();


        TextInputEditText editText = dialog.findViewById(R.id.etAddTask);
        if (editText != null) {
            editText.requestFocus();
            new Handler().postDelayed(() -> {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                }
            }, 200);

        }

    }
}
