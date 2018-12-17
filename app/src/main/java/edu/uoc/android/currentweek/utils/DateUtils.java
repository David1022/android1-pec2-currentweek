package edu.uoc.android.currentweek.utils;

import java.util.Calendar;

public class DateUtils {

    private final Calendar calendar;

    public DateUtils(Calendar calendar) {
        this.calendar = calendar;
    }

    /**
     * Return true if weekNumber param is the current week number. If not, return false.
     */
    public boolean isTheCurrentWeekNumber(int weekNumber) {
        return getCurrentWeekNumber() == weekNumber;
    }

    private int getCurrentWeekNumber() {
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
}
