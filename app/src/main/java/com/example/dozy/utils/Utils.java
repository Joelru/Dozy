package com.example.dozy.utils;

import android.widget.EditText;

public class Utils {
    public static boolean validateText(EditText campo) {
        String text = campo.getText().toString().trim();
        if (text.isEmpty()) {
            campo.setError("Campo obligatorio");
            return false;
        }
        return true;
    }

}
