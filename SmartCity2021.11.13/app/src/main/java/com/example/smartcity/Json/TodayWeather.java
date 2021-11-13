package com.example.smartcity.Json;

public class TodayWeather {

//     
    /**
     * msg : 操作成功
     * code : 200
     * data : {"maxTemperature":"12","uv":"弱","minTemperature":"24","temperature":"21","weather":"晴","humidity":"60","air":"无污染","apparentTemperature":"15","label":"今天","day":1}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * maxTemperature : 12
         * uv : 弱
         * minTemperature : 24
         * temperature : 21
         * weather : 晴
         * humidity : 60
         * air : 无污染
         * apparentTemperature : 15
         * label : 今天
         * day : 1
         */

        private String maxTemperature;
        private String uv;
        private String minTemperature;
        private String temperature;
        private String weather;
        private String humidity;
        private String air;
        private String apparentTemperature;
        private String label;
        private int day;

        public String getMaxTemperature() {
            return maxTemperature;
        }

        public void setMaxTemperature(String maxTemperature) {
            this.maxTemperature = maxTemperature;
        }

        public String getUv() {
            return uv;
        }

        public void setUv(String uv) {
            this.uv = uv;
        }

        public String getMinTemperature() {
            return minTemperature;
        }

        public void setMinTemperature(String minTemperature) {
            this.minTemperature = minTemperature;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getAir() {
            return air;
        }

        public void setAir(String air) {
            this.air = air;
        }

        public String getApparentTemperature() {
            return apparentTemperature;
        }

        public void setApparentTemperature(String apparentTemperature) {
            this.apparentTemperature = apparentTemperature;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }
    }
}
