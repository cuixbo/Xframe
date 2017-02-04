package com.xbc.xframe.ui.widget;

/**
 * Created by xiaobo.cui on 2017/1/23.
 */

public class TimePickerData {

    public static class HourData {
        public int maxValue;
        public int minValue;
        public int defaultValue;

        public HourData(int maxValue, int minValue, int defaultValue) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.defaultValue = defaultValue;
        }
    }

    public static class MinData {
        public int maxValue;
        public int minValue;
        public int defaultValue;

        public MinData(int maxValue, int minValue, int defaultValue) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.defaultValue = defaultValue;
        }
    }

    public static class SecData {
        public int maxValue;
        public int minValue;
        public int defaultValue;

        public SecData(int maxValue, int minValue, int defaultValue) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.defaultValue = defaultValue;
        }
    }

    public static class TimeData {
        public int hour;
        public int min;
        public int sec;


        public TimeData(int hour, int min) {
            this.hour = hour;
            this.min = min;
        }

        public TimeData(int hour, int min, int sec) {
            this.hour = hour;
            this.min = min;
            this.sec = sec;
        }
    }

}
