package com.example.dozy.utils;

import android.content.Context;
import android.graphics.Paint;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.dozy.R;

public class Utils {
    public static boolean validateText(EditText campo) {
        String text = campo.getText().toString().trim();
        if (text.isEmpty()) {
            campo.setError("Campo obligatorio");
            return false;
        }
        return true;
    }

    public static void modifyStyle(Context context, TextView textView, ConstraintLayout itemView, RadioButton button, Boolean isCompleted) {
        if (isCompleted) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.black_transparent));
            button.setChecked(true);
        } else {
            textView.setPaintFlags(textView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
            button.setChecked(false);
        }
    }
}
