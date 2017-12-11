package com.yycloud.core.beans;

public class AlarmIgnoreMSG {
	   /**
     * data : {"sn":"08012A00000002A2","event_id":"5cb2f040-d8cf-4d20-b544-e3c25f638cd3","event_name":"device-online","event_type":null,"event_level":"notice-level-1","event_date":"2017-06-21T12:12:01.293Z","user_name":"test01","smartgate_sn":"YBF002060000000044","smartgate_name":"云洋数据智能网关","device_sn":"08012A00000002A2","event_state":"unread","detail":"烟雾传感器(08012A00000002A2) 上线."}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sn : 08012A00000002A2
         * event_id : 5cb2f040-d8cf-4d20-b544-e3c25f638cd3
         * event_name : device-online
         * event_type : null
         * event_level : notice-level-1
         * event_date : 2017-06-21T12:12:01.293Z
         * user_name : test01
         * smartgate_sn : YBF002060000000044
         * smartgate_name : 云洋数据智能网关
         * device_sn : 08012A00000002A2
         * event_state : unread
         * detail : 烟雾传感器(08012A00000002A2) 上线.
         */

        private String sn;
        private String event_id;
        private String event_name;
        private Object event_type;
        private String event_level;
        private String event_date;
        private String user_name;
        private String smartgate_sn;
        private String smartgate_name;
        private String device_sn;
        private String event_state;
        private String detail;

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getEvent_id() {
            return event_id;
        }

        public void setEvent_id(String event_id) {
            this.event_id = event_id;
        }

        public String getEvent_name() {
            return event_name;
        }

        public void setEvent_name(String event_name) {
            this.event_name = event_name;
        }

        public Object getEvent_type() {
            return event_type;
        }

        public void setEvent_type(Object event_type) {
            this.event_type = event_type;
        }

        public String getEvent_level() {
            return event_level;
        }

        public void setEvent_level(String event_level) {
            this.event_level = event_level;
        }

        public String getEvent_date() {
            return event_date;
        }

        public void setEvent_date(String event_date) {
            this.event_date = event_date;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getSmartgate_sn() {
            return smartgate_sn;
        }

        public void setSmartgate_sn(String smartgate_sn) {
            this.smartgate_sn = smartgate_sn;
        }

        public String getSmartgate_name() {
            return smartgate_name;
        }

        public void setSmartgate_name(String smartgate_name) {
            this.smartgate_name = smartgate_name;
        }

        public String getDevice_sn() {
            return device_sn;
        }

        public void setDevice_sn(String device_sn) {
            this.device_sn = device_sn;
        }

        public String getEvent_state() {
            return event_state;
        }

        public void setEvent_state(String event_state) {
            this.event_state = event_state;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}
