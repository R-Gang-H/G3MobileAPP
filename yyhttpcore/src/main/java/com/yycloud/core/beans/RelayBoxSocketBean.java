package com.yycloud.core.beans;

import java.util.List;

public class RelayBoxSocketBean {
	 /**
     * data : {"info":{"dev_extend_type":"relaybox","dev_name":"电气柜","dev_type":"relaybox","owner":"YBF002020000000057","password":"","remote":1,"sn":"250205000000000B","user":""},"last_value":{"charge":100,"pool_list":[{"dev_extend_type":"排风机","dev_name":"排风机（南）","dev_type":"DOUBLE","pool_index":1,"status":1},{"dev_extend_type":"内保温电机","dev_name":"内保温电机（北）","dev_type":"DOUBLE","pool_index":2,"status":2},{"dev_extend_type":"水帘水泵","dev_name":"水帘水泵（北）","dev_type":"SINGLE","pool_index":3,"status":2},{"dev_extend_type":"微喷电机","dev_name":"微喷电机（北）","dev_type":"SINGLE","pool_index":4,"status":2},{"dev_extend_type":"循环风机","dev_name":"循环风机（南）","dev_type":"SINGLE","pool_index":5,"status":2},{"dev_extend_type":"外遮阳电机","dev_name":"外遮阳电机（北）","dev_type":"DOUBLE","pool_index":6,"status":2},{"dev_extend_type":"内遮阳电机","dev_name":"内遮阳电机（北）","dev_type":"DOUBLE","pool_index":7,"status":2},{"dev_extend_type":"卷膜电机","dev_name":"卷膜电机（北）","dev_type":"SINGLE","pool_index":8,"status":1},{"dev_extend_type":"东西走廊循环风机","dev_name":"东西走廊循环风机","dev_type":"SINGLE","pool_index":9,"status":2},{"dev_extend_type":"东西走廊内遮阳电机","dev_name":"东西走廊内遮阳电机","dev_type":"DOUBLE","pool_index":10,"status":2},{"dev_extend_type":"照明","dev_name":"照明（北）","dev_type":"SINGLE","pool_index":11,"status":2},{"dev_extend_type":"循环风机","dev_name":"循环风机（南2）","dev_type":"SINGLE","pool_index":12,"status":2},{"dev_extend_type":"水帘水泵","dev_name":"水帘水泵（北2）","dev_type":"SINGLE","pool_index":13,"status":2},{"dev_extend_type":"微喷电机","dev_name":"微喷电机（北2）","dev_type":"SINGLE","pool_index":14,"status":2},{"dev_extend_type":"内保温电机","dev_name":"内保温电机（北2）","dev_type":"DOUBLE","pool_index":15,"status":2},{"dev_extend_type":"外遮阳电机","dev_name":"外遮阳电机（北2）","dev_type":"DOUBLE","pool_index":16,"status":2},{"dev_extend_type":"内遮阳电机","dev_name":"内遮阳电机（北2）","dev_type":"DOUBLE","pool_index":17,"status":2},{"dev_extend_type":"卷膜电机","dev_name":"卷膜电机（北2）","dev_type":"DOUBLE","pool_index":18,"status":2},{"dev_extend_type":"东西走廊循环风机","dev_name":"东西走廊循环风机2","dev_type":"SINGLE","pool_index":19,"status":2},{"dev_extend_type":"东西走廊内遮阳电机","dev_name":"东西走廊内遮阳电机2","dev_type":"DOUBLE","pool_index":20,"status":2}],"server_time":1490570417726},"serverTime":1490570417726,"state":{"charge_state":"normal","online_state":"online"},"strategy":"Failed"}
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
         * info : {"dev_extend_type":"relaybox","dev_name":"电气柜","dev_type":"relaybox","owner":"YBF002020000000057","password":"","remote":1,"sn":"250205000000000B","user":""}
         * last_value : {"charge":100,"pool_list":[{"dev_extend_type":"排风机","dev_name":"排风机（南）","dev_type":"DOUBLE","pool_index":1,"status":1},{"dev_extend_type":"内保温电机","dev_name":"内保温电机（北）","dev_type":"DOUBLE","pool_index":2,"status":2},{"dev_extend_type":"水帘水泵","dev_name":"水帘水泵（北）","dev_type":"SINGLE","pool_index":3,"status":2},{"dev_extend_type":"微喷电机","dev_name":"微喷电机（北）","dev_type":"SINGLE","pool_index":4,"status":2},{"dev_extend_type":"循环风机","dev_name":"循环风机（南）","dev_type":"SINGLE","pool_index":5,"status":2},{"dev_extend_type":"外遮阳电机","dev_name":"外遮阳电机（北）","dev_type":"DOUBLE","pool_index":6,"status":2},{"dev_extend_type":"内遮阳电机","dev_name":"内遮阳电机（北）","dev_type":"DOUBLE","pool_index":7,"status":2},{"dev_extend_type":"卷膜电机","dev_name":"卷膜电机（北）","dev_type":"SINGLE","pool_index":8,"status":1},{"dev_extend_type":"东西走廊循环风机","dev_name":"东西走廊循环风机","dev_type":"SINGLE","pool_index":9,"status":2},{"dev_extend_type":"东西走廊内遮阳电机","dev_name":"东西走廊内遮阳电机","dev_type":"DOUBLE","pool_index":10,"status":2},{"dev_extend_type":"照明","dev_name":"照明（北）","dev_type":"SINGLE","pool_index":11,"status":2},{"dev_extend_type":"循环风机","dev_name":"循环风机（南2）","dev_type":"SINGLE","pool_index":12,"status":2},{"dev_extend_type":"水帘水泵","dev_name":"水帘水泵（北2）","dev_type":"SINGLE","pool_index":13,"status":2},{"dev_extend_type":"微喷电机","dev_name":"微喷电机（北2）","dev_type":"SINGLE","pool_index":14,"status":2},{"dev_extend_type":"内保温电机","dev_name":"内保温电机（北2）","dev_type":"DOUBLE","pool_index":15,"status":2},{"dev_extend_type":"外遮阳电机","dev_name":"外遮阳电机（北2）","dev_type":"DOUBLE","pool_index":16,"status":2},{"dev_extend_type":"内遮阳电机","dev_name":"内遮阳电机（北2）","dev_type":"DOUBLE","pool_index":17,"status":2},{"dev_extend_type":"卷膜电机","dev_name":"卷膜电机（北2）","dev_type":"DOUBLE","pool_index":18,"status":2},{"dev_extend_type":"东西走廊循环风机","dev_name":"东西走廊循环风机2","dev_type":"SINGLE","pool_index":19,"status":2},{"dev_extend_type":"东西走廊内遮阳电机","dev_name":"东西走廊内遮阳电机2","dev_type":"DOUBLE","pool_index":20,"status":2}],"server_time":1490570417726}
         * serverTime : 1490570417726
         * state : {"charge_state":"normal","online_state":"online"}
         * strategy : Failed
         */

        private InfoBean info;
        private LastValueBean last_value;
        private long serverTime;
        private StateBean state;
        private String strategy;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public LastValueBean getLast_value() {
            return last_value;
        }

        public void setLast_value(LastValueBean last_value) {
            this.last_value = last_value;
        }

        public long getServerTime() {
            return serverTime;
        }

        public void setServerTime(long serverTime) {
            this.serverTime = serverTime;
        }

        public StateBean getState() {
            return state;
        }

        public void setState(StateBean state) {
            this.state = state;
        }

        public String getStrategy() {
            return strategy;
        }

        public void setStrategy(String strategy) {
            this.strategy = strategy;
        }

        public static class InfoBean {
            /**
             * dev_extend_type : relaybox
             * dev_name : 电气柜
             * dev_type : relaybox
             * owner : YBF002020000000057
             * password : 
             * remote : 1
             * sn : 250205000000000B
             * user : 
             */

            private String dev_extend_type;
            private String dev_name;
            private String dev_type;
            private String owner;
            private String password;
            private int remote;
            private String sn;
            private String user;

            public String getDev_extend_type() {
                return dev_extend_type;
            }

            public void setDev_extend_type(String dev_extend_type) {
                this.dev_extend_type = dev_extend_type;
            }

            public String getDev_name() {
                return dev_name;
            }

            public void setDev_name(String dev_name) {
                this.dev_name = dev_name;
            }

            public String getDev_type() {
                return dev_type;
            }

            public void setDev_type(String dev_type) {
                this.dev_type = dev_type;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public int getRemote() {
                return remote;
            }

            public void setRemote(int remote) {
                this.remote = remote;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }
        }

        public static class LastValueBean {
            /**
             * charge : 100
             * pool_list : [{"dev_extend_type":"排风机","dev_name":"排风机（南）","dev_type":"DOUBLE","pool_index":1,"status":1},{"dev_extend_type":"内保温电机","dev_name":"内保温电机（北）","dev_type":"DOUBLE","pool_index":2,"status":2},{"dev_extend_type":"水帘水泵","dev_name":"水帘水泵（北）","dev_type":"SINGLE","pool_index":3,"status":2},{"dev_extend_type":"微喷电机","dev_name":"微喷电机（北）","dev_type":"SINGLE","pool_index":4,"status":2},{"dev_extend_type":"循环风机","dev_name":"循环风机（南）","dev_type":"SINGLE","pool_index":5,"status":2},{"dev_extend_type":"外遮阳电机","dev_name":"外遮阳电机（北）","dev_type":"DOUBLE","pool_index":6,"status":2},{"dev_extend_type":"内遮阳电机","dev_name":"内遮阳电机（北）","dev_type":"DOUBLE","pool_index":7,"status":2},{"dev_extend_type":"卷膜电机","dev_name":"卷膜电机（北）","dev_type":"SINGLE","pool_index":8,"status":1},{"dev_extend_type":"东西走廊循环风机","dev_name":"东西走廊循环风机","dev_type":"SINGLE","pool_index":9,"status":2},{"dev_extend_type":"东西走廊内遮阳电机","dev_name":"东西走廊内遮阳电机","dev_type":"DOUBLE","pool_index":10,"status":2},{"dev_extend_type":"照明","dev_name":"照明（北）","dev_type":"SINGLE","pool_index":11,"status":2},{"dev_extend_type":"循环风机","dev_name":"循环风机（南2）","dev_type":"SINGLE","pool_index":12,"status":2},{"dev_extend_type":"水帘水泵","dev_name":"水帘水泵（北2）","dev_type":"SINGLE","pool_index":13,"status":2},{"dev_extend_type":"微喷电机","dev_name":"微喷电机（北2）","dev_type":"SINGLE","pool_index":14,"status":2},{"dev_extend_type":"内保温电机","dev_name":"内保温电机（北2）","dev_type":"DOUBLE","pool_index":15,"status":2},{"dev_extend_type":"外遮阳电机","dev_name":"外遮阳电机（北2）","dev_type":"DOUBLE","pool_index":16,"status":2},{"dev_extend_type":"内遮阳电机","dev_name":"内遮阳电机（北2）","dev_type":"DOUBLE","pool_index":17,"status":2},{"dev_extend_type":"卷膜电机","dev_name":"卷膜电机（北2）","dev_type":"DOUBLE","pool_index":18,"status":2},{"dev_extend_type":"东西走廊循环风机","dev_name":"东西走廊循环风机2","dev_type":"SINGLE","pool_index":19,"status":2},{"dev_extend_type":"东西走廊内遮阳电机","dev_name":"东西走廊内遮阳电机2","dev_type":"DOUBLE","pool_index":20,"status":2}]
             * server_time : 1490570417726
             */

            private int charge;
            private long server_time;
            private List<PoolListBean> pool_list;

            public int getCharge() {
                return charge;
            }

            public void setCharge(int charge) {
                this.charge = charge;
            }

            public long getServer_time() {
                return server_time;
            }

            public void setServer_time(long server_time) {
                this.server_time = server_time;
            }

            public List<PoolListBean> getPool_list() {
                return pool_list;
            }

            public void setPool_list(List<PoolListBean> pool_list) {
                this.pool_list = pool_list;
            }

            public static class PoolListBean {
                /**
                 * dev_extend_type : 排风机
                 * dev_name : 排风机（南）
                 * dev_type : DOUBLE
                 * pool_index : 1
                 * status : 1
                 */

                private String dev_extend_type;
                private String dev_name;
                private String dev_type;
                private int pool_index;
                private int status;

                public String getDev_extend_type() {
                    return dev_extend_type;
                }

                public void setDev_extend_type(String dev_extend_type) {
                    this.dev_extend_type = dev_extend_type;
                }

                public String getDev_name() {
                    return dev_name;
                }

                public void setDev_name(String dev_name) {
                    this.dev_name = dev_name;
                }

                public String getDev_type() {
                    return dev_type;
                }

                public void setDev_type(String dev_type) {
                    this.dev_type = dev_type;
                }

                public int getPool_index() {
                    return pool_index;
                }

                public void setPool_index(int pool_index) {
                    this.pool_index = pool_index;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }
        }

        public static class StateBean {
            /**
             * charge_state : normal
             * online_state : online
             */

            private String charge_state;
            private String online_state;

            public String getCharge_state() {
                return charge_state;
            }

            public void setCharge_state(String charge_state) {
                this.charge_state = charge_state;
            }

            public String getOnline_state() {
                return online_state;
            }

            public void setOnline_state(String online_state) {
                this.online_state = online_state;
            }
        }
    }

}
