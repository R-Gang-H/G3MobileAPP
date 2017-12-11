package com.yycloud.core.beans;

import java.util.List;

public class SocketIobeans {

    /**
     * data : {"info":{"config_info":{"cp":"132","ct":"5.0","eth":"15","maxc":"132","minc":"132","mt":"500","ot":"6.0","pd":"1","rt":"0","sl":"40","sth":"8","stm":"0","th":[{"dn":"空气温湿度传感器","icd":"1","sn":"01012C000000031D"},{"dn":"空气温湿度传感器","icd":"1","sn":"01012C0000000354"}],"tl":"500"},"dev_extend_type":"erelay-ventilation","dev_name":"放风机2","dev_type":"erelay2","owner":"YBF002060000000033","password":"","sn":"21012C000000038D","switchs":"12","user":""},"last_value":{"charge":100,"server_time":1491418991094,"status":1},"serverTime":1491418991094,"state":{"charge_state":"normal","online_state":"online"},"strategy":"Failed"}
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
         * info : {"config_info":{"cp":"132","ct":"5.0","eth":"15","maxc":"132","minc":"132","mt":"500","ot":"6.0","pd":"1","rt":"0","sl":"40","sth":"8","stm":"0","th":[{"dn":"空气温湿度传感器","icd":"1","sn":"01012C000000031D"},{"dn":"空气温湿度传感器","icd":"1","sn":"01012C0000000354"}],"tl":"500"},"dev_extend_type":"erelay-ventilation","dev_name":"放风机2","dev_type":"erelay2","owner":"YBF002060000000033","password":"","sn":"21012C000000038D","switchs":"12","user":""}
         * last_value : {"charge":100,"server_time":1491418991094,"status":1}
         * serverTime : 1491418991094
         * state : {"charge_state":"normal","online_state":"online"}
         * strategy : Failed
         */

        private InfoBean info;
        private LastValueBean last_value;
        private long serverTime;
        private StateBean state;
        //  private StrategyBean strategy;

      /*  public StrategyBean getStrategy() {
			return strategy;
		}

		public void setStrategy(StrategyBean strategy) {
			this.strategy = strategy;
		}*/

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



        public static class InfoBean {
            /**
             * config_info : {"cp":"132","ct":"5.0","eth":"15","maxc":"132","minc":"132","mt":"500","ot":"6.0","pd":"1","rt":"0","sl":"40","sth":"8","stm":"0","th":[{"dn":"空气温湿度传感器","icd":"1","sn":"01012C000000031D"},{"dn":"空气温湿度传感器","icd":"1","sn":"01012C0000000354"}],"tl":"500"}
             * dev_extend_type : erelay-ventilation
             * dev_name : 放风机2
             * dev_type : erelay2
             * owner : YBF002060000000033
             * password :
             * sn : 21012C000000038D
             * switchs : 12
             * user :
             */

            private ConfigInfoBean config_info;
            private String dev_extend_type;
            private String dev_name;
            private String dev_type;
            private String owner;
            private String password;
            private String sn;
            private String switchs;
            private String user;
            private String power;
            private String charge_total;
            public String getPower() {
                return power;
            }

            public void setPower(String power) {
                this.power = power;
            }

            public String getCharge_total() {
                return charge_total;
            }

            public void setCharge_total(String charge_total) {
                this.charge_total = charge_total;
            }

            public ConfigInfoBean getConfig_info() {
                return config_info;
            }

            public void setConfig_info(ConfigInfoBean config_info) {
                this.config_info = config_info;
            }

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

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getSwitchs() {
                return switchs;
            }

            public void setSwitchs(String switchs) {
                this.switchs = switchs;
            }

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public static class ConfigInfoBean {
                /**
                 * cp : 132
                 * ct : 5.0
                 * eth : 15
                 * maxc : 132
                 * minc : 132
                 * mt : 500
                 * ot : 6.0
                 * pd : 1
                 * rt : 0
                 * sl : 40
                 * sth : 8
                 * stm : 0
                 * th : [{"dn":"空气温湿度传感器","icd":"1","sn":"01012C000000031D"},{"dn":"空气温湿度传感器","icd":"1","sn":"01012C0000000354"}]
                 * tl : 500
                 */

                private String cp;
                private String ct;
                private String eth;
                private String maxc;
                private String minc;
                private String mt;
                private String ot;
                private String pd;
                private String rt;
                private String sl;
                private String sth;
                private String stm;
                private String tl;
                private List<ThBean> th;
                private String vcc_min; // 最小值 add by jcy 20170726
                private String vcc_max; // 最大值 add by jcy 20170726
                private String vcc_current; // 当前值 add by jcy 20170726
                private String pt; // 百分比
                private String percent; // 百分比 add by jcy 20170726
                private String version;
                private String result; // 校准 add by jcy 20170726
                private boolean istemcontrol; // true是 false不是 时间控制
                public String getVersion() {
                    return version;
                }

                public void setVersion(String version) {
                    this.version = version;
                }

                public String getResult() {
                    return result;
                }

                public void setResult(String result) {
                    this.result = result;
                }

                public boolean isIstemcontrol() {
                    return istemcontrol;
                }

                public void setIstemcontrol(boolean istemcontrol) {
                    this.istemcontrol = istemcontrol;
                }

                public String getPt() {
                    return pt;
                }

                public void setPt(String pt) {
                    this.pt = pt;
                }

                public String getPercent() {
                    return percent;
                }

                public void setPercent(String percent) {
                    this.percent = percent;
                }

                public String getVcc_current() {
                    return vcc_current;
                }

                public void setVcc_current(String vcc_current) {
                    this.vcc_current = vcc_current;
                }

                public String getVcc_max() {
                    return vcc_max;
                }

                public void setVcc_max(String vcc_max) {
                    this.vcc_max = vcc_max;
                }

                public String getVcc_min() {
                    return vcc_min;
                }

                public void setVcc_min(String vcc_min) {
                    this.vcc_min = vcc_min;
                }

                public String getCp() {
                    return cp;
                }

                public void setCp(String cp) {
                    this.cp = cp;
                }

                public String getCt() {
                    return ct;
                }

                public void setCt(String ct) {
                    this.ct = ct;
                }

                public String getEth() {
                    return eth;
                }

                public void setEth(String eth) {
                    this.eth = eth;
                }

                public String getMaxc() {
                    return maxc;
                }

                public void setMaxc(String maxc) {
                    this.maxc = maxc;
                }

                public String getMinc() {
                    return minc;
                }

                public void setMinc(String minc) {
                    this.minc = minc;
                }

                public String getMt() {
                    return mt;
                }

                public void setMt(String mt) {
                    this.mt = mt;
                }

                public String getOt() {
                    return ot;
                }

                public void setOt(String ot) {
                    this.ot = ot;
                }

                public String getPd() {
                    return pd;
                }

                public void setPd(String pd) {
                    this.pd = pd;
                }

                public String getRt() {
                    return rt;
                }

                public void setRt(String rt) {
                    this.rt = rt;
                }

                public String getSl() {
                    return sl;
                }

                public void setSl(String sl) {
                    this.sl = sl;
                }

                public String getSth() {
                    return sth;
                }

                public void setSth(String sth) {
                    this.sth = sth;
                }

                public String getStm() {
                    return stm;
                }

                public void setStm(String stm) {
                    this.stm = stm;
                }

                public String getTl() {
                    return tl;
                }

                public void setTl(String tl) {
                    this.tl = tl;
                }

                public List<ThBean> getTh() {
                    return th;
                }

                public void setTh(List<ThBean> th) {
                    this.th = th;
                }

                public static class ThBean {
                    /**
                     * dn : 空气温湿度传感器
                     * icd : 1
                     * sn : 01012C000000031D
                     */

                    private String dn;
                    private String icd;
                    private String sn;

                    public String getDn() {
                        return dn;
                    }

                    public void setDn(String dn) {
                        this.dn = dn;
                    }

                    public String getIcd() {
                        return icd;
                    }

                    public void setIcd(String icd) {
                        this.icd = icd;
                    }

                    public String getSn() {
                        return sn;
                    }

                    public void setSn(String sn) {
                        this.sn = sn;
                    }
                }
            }
        }

        public static class LastValueBean {
            @Override
            public String toString() {
                return "LastValueBean{" + "status=" + status + ", server_time="
                        + server_time + ", charge=" + charge + '}';
            }

            /**
             * status : 2 server_time : 1489945230023 charge : 100
             */
            private String air_temperature;
            private String air_humidity;
            // 土壤
            private String soil_temperature;
            private String soil_humidity;
            // 光照
            private String lux;

            // 烟雾
            private String smoke;
            private int status;
            private long server_time;
            private int charge;

            private String co2_ppm;

            public String getAir_temperature() {
                return air_temperature;
            }

            public void setAir_temperature(String air_temperature) {
                this.air_temperature = air_temperature;
            }

            public String getAir_humidity() {
                return air_humidity;
            }

            public void setAir_humidity(String air_humidity) {
                this.air_humidity = air_humidity;
            }

            public String getSoil_temperature() {
                return soil_temperature;
            }

            public void setSoil_temperature(String soil_temperature) {
                this.soil_temperature = soil_temperature;
            }

            public String getSoil_humidity() {
                return soil_humidity;
            }

            public void setSoil_humidity(String soil_humidity) {
                this.soil_humidity = soil_humidity;
            }

            public String getLux() {
                return lux;
            }

            public void setLux(String lux) {
                this.lux = lux;
            }

            public String getCo2_ppm() {
                return co2_ppm;
            }

            public void setCo2_ppm(String co2_ppm) {
                this.co2_ppm = co2_ppm;
            }

            public String getSmoke() {
                return smoke;
            }

            public void setSmoke(String smoke) {
                this.smoke = smoke;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public long getServer_time() {
                return server_time;
            }

            public void setServer_time(long server_time) {
                this.server_time = server_time;
            }

            public int getCharge() {
                return charge;
            }

            public void setCharge(int charge) {
                this.charge = charge;
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
        public static class StrategyBean {
            /**
             * air_t_enable_h_1 : true
             * air_t_enable_h_2 : true
             * air_t_enable_h_3 : false
             * air_t_h_value_1 : 20
             * air_t_h_value_2 : 25
             * air_t_h_value_3 :
             * strategy_id : 2a27e857-c817-475b-996e-215d1fc5fbab
             * strategy_name : 办公室测试033高温
             * username : test01
             */

            private String air_t_enable_h_1;
            private String air_t_enable_h_2;
            private String air_t_enable_h_3;
            private String air_t_h_value_1;
            private String air_t_h_value_2;
            private String air_t_h_value_3;
            private String strategy_id;
            private String strategy_name;
            private String username;

            public String getAir_t_enable_h_1() {
                return air_t_enable_h_1;
            }

            public void setAir_t_enable_h_1(String air_t_enable_h_1) {
                this.air_t_enable_h_1 = air_t_enable_h_1;
            }

            public String getAir_t_enable_h_2() {
                return air_t_enable_h_2;
            }

            public void setAir_t_enable_h_2(String air_t_enable_h_2) {
                this.air_t_enable_h_2 = air_t_enable_h_2;
            }

            public String getAir_t_enable_h_3() {
                return air_t_enable_h_3;
            }

            public void setAir_t_enable_h_3(String air_t_enable_h_3) {
                this.air_t_enable_h_3 = air_t_enable_h_3;
            }

            public String getAir_t_h_value_1() {
                return air_t_h_value_1;
            }

            public void setAir_t_h_value_1(String air_t_h_value_1) {
                this.air_t_h_value_1 = air_t_h_value_1;
            }

            public String getAir_t_h_value_2() {
                return air_t_h_value_2;
            }

            public void setAir_t_h_value_2(String air_t_h_value_2) {
                this.air_t_h_value_2 = air_t_h_value_2;
            }

            public String getAir_t_h_value_3() {
                return air_t_h_value_3;
            }

            public void setAir_t_h_value_3(String air_t_h_value_3) {
                this.air_t_h_value_3 = air_t_h_value_3;
            }

            public String getStrategy_id() {
                return strategy_id;
            }

            public void setStrategy_id(String strategy_id) {
                this.strategy_id = strategy_id;
            }

            public String getStrategy_name() {
                return strategy_name;
            }

            public void setStrategy_name(String strategy_name) {
                this.strategy_name = strategy_name;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }

}
