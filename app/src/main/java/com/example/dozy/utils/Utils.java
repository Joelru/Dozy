package com.example.dozy.utils;

import android.content.Context;
import android.graphics.Paint;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.dozy.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    public static String getFormattedCurrentDate() {
        Locale locale = new Locale("es", "ES");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d 'de' MMMM", locale);
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
}
