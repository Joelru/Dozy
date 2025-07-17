package com.example.dozy.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarUtils {
    public interface OnDateSelectedListener {
        void OnDateSelected(String date);
    }

    public static void showCalendar(Context context, OnDateSelectedListener listener) {
        final Calendar calendar = Calendar.getInstance();

        int yearActual = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                (DatePicker view, int year, int month, int dayOfMonth) -> {
                    String dateSelected = String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, month + 1, year);
                    listener.OnDateSelected(dateSelected);
                }, yearActual, mes, dia);

        datePickerDialog.show();
    }

    public static String getFormattedCurrentDate() {
        Locale locale = new Locale("es", "ES");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d 'de' MMMM", locale);
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    public static String getDateToday() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(new Date());
    }

    public static String getDateToSave(String date) {

        return (date != null && !date.isEmpty() ? date : getDateToday());
    }


}
