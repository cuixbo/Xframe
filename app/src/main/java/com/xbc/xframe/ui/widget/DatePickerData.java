package com.xbc.xframe.ui.widget;

/**
 * Created by xiaobo.cui on 2017/1/23.
 */

public class DatePickerData {

    public static class YearData {
        public int maxValue;
        public int minValue;
        public int defaultValue;

        public YearData(int maxValue, int minValue, int defaultValue) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.defaultValue = defaultValue;
        }
    }

    public static class MonthData {
        public int maxValue;
        public int minValue;
        public int defaultValue;

        public MonthData(int maxValue, int minValue, int defaultValue) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.defaultValue = defaultValue;
        }
    }

    public static class DayData {
        public int maxValue;
        public int minValue;
        public int defaultValue;

        public DayData(int maxValue, int minValue, int defaultValue) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.defaultValue = defaultValue;
        }
    }

    public static class DateData {
        public int year;
        public int month;
        public int day;
        public int maxDay;

        public DateData(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }
    }

    public DateData getData(int year, int month, int day) {
        DateData dateData = null;
        boolean isLeapYear = isLeapYear(year);
        int monthLevel = getMonthLevel(year,month);
        if(monthLevel==4){
            dateData=new DateData(year, month, day);
            dateData.maxDay=31;
        }else if(monthLevel==3){
            if(day==31){
                dateData=new DateData(year, month, 30);

            }else{
                dateData=new DateData(year, month, day);
            }
            dateData.maxDay=30;
        }else if(monthLevel==2){
            if(day>29){
                dateData=new DateData(year, month, 29);
            }else{
                dateData=new DateData(year, month, day);
            }
            dateData.maxDay=29;
        }else{
            if(day>28){
                dateData=new DateData(year, month, 28);
            }else{
                dateData=new DateData(year, month, day);
            }
            dateData.maxDay=28;
        }
        return dateData;
    }

    private int getMonthLevel(int year, int month) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 4;
        }
        if (month == 2) {
            if (isLeapYear(year)) {
                return 2;
            } else {
                return 1;
            }
        }
        return 3;
    }

    /**
     * 是否闰年
     * @param year
     * @return
     */
    private boolean isLeapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return true;
        }
        return false;
    }

}
