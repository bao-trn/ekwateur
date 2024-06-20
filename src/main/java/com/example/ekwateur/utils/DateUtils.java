package com.example.ekwateur.utils;

import java.time.LocalDate;
import java.time.YearMonth;

public class DateUtils {

    public static int hoursInCurrentMonths() {

        //ensures the correct number of days in a month no matter the year

        LocalDate date = LocalDate.now();
        int currentYear = date.getYear();
        int currentMonth = date.getMonthValue();
        YearMonth yearMonth = YearMonth.of(currentYear, currentMonth);
        int daysInMonth = yearMonth.lengthOfMonth();

        return daysInMonth * 24;

    }
}
