package com.ak.app.haloburger.util;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.ak.app.haloburger.activity.R;

import java.text.DateFormatSymbols;
import java.util.Calendar;

/**
 * Created by el on 25/05/18.
 */

public class CustomDatePicker {

    private Integer dobYear, dobMonth, dobDate;

    private Calendar calendar;
    private int year, month, day, selectedDay, selectedMonth, selectedYear;
    private DatePicker datePicker;
    private Context context;



    public CustomDatePicker(Context context){
        this.context = context;
        dobYear = 0;
        dobMonth = 0;
        dobDate = 0;
        calendar = Calendar.getInstance();

        year = 2002;// calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        selectedDay = day;
        selectedMonth = month;
        selectedYear = year;
    }

    public void showDatePickerDialog(final TextView textView){


        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.customdialog);

        datePicker = (DatePicker) dialog.findViewById(R.id.date_picker);

        if (selectedDay != day || selectedMonth != month
                || selectedYear != year)
            datePicker.init(selectedYear, selectedMonth, selectedDay,
                    null);
        else
            datePicker.init(year, month, day, null);

        Button dialogButton = (Button) dialog
                .findViewById(R.id.dialogButtonOK);

        Button clearButton = (Button) dialog
                .findViewById(R.id.dialogButtonClear);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.clearFocus();
                dobYear = datePicker.getYear();
                dobMonth = datePicker.getMonth() + 1;
                dobDate = datePicker.getDayOfMonth();

                Calendar currentDate = Calendar.getInstance();
                currentDate.set(Calendar.YEAR, 2002);

                Integer d = currentDate.get(Calendar.DATE);
                Integer m = currentDate.get(Calendar.MONTH) + 1;
                Integer y = currentDate.get(Calendar.YEAR);

                Calendar validDate = Calendar.getInstance();
                validDate.set(dobYear, dobMonth - 1, dobDate);

                StringBuilder sb = new StringBuilder();
                if (dobYear < 2002) {

                    selectedYear = dobYear;
                    selectedMonth = dobMonth - 1;
                    selectedDay = dobDate;

                    sb.append(getMonth(dobMonth)).append(" ")
                            .append(dobDate.toString()).append(", ")
                            .append(dobYear.toString());

                } else {
                    selectedYear = y;
                    selectedMonth = dobMonth - 1;
                    selectedDay = dobDate;
                    sb.append(getMonth(dobMonth)).append(" ")
                            .append(dobDate.toString()).append(", ")
                            .append(y.toString());
                }

                String dobStr = sb.toString();
                textView.setText(dobStr);

                dialog.dismiss();

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dobYear = 0;
                dobMonth = 0;
                dobDate = 0;

                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                selectedDay = day;
                selectedMonth = month;
                selectedYear = year;

                textView.setText("");

                dialog.dismiss();

            }
        });

        dialog.show();
//        return dateResult;
    }

    public Integer getDobYear() {
        return dobYear;
    }

    public void setDobYear(Integer dobYear) {
        this.dobYear = dobYear;
    }

    public Integer getDobMonth() {
        return dobMonth;
    }

    public void setDobMonth(Integer dobMonth) {
        this.dobMonth = dobMonth;
    }

    public Integer getDobDate() {
        return dobDate;
    }

    public void setDobDate(Integer dobDate) {
        this.dobDate = dobDate;
    }

    private String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month - 1];
    }
}
