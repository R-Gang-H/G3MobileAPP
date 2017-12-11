package com.yycloud.core.beans;

public class AlarmPlanSettingBean {
	 /**
     * _class : com.yunyangdata.greenhouse.strategy.entity.MoAlarmstrategySysEntity
     * _id : 0
     * air_humidity_config : {"h_value_o":80,"h_value_r":90,"h_value_y":70,"l_value_o":20,"l_value_r":10,"l_value_y":30}
     * air_temperature_config : {"h_value_o":35,"h_value_r":40,"h_value_y":30,"l_value_o":10,"l_value_r":5,"l_value_y":15}
     * charge_config : {"l_value_o":20,"l_value_r":10,"l_value_y":40}
     * checkdelay : 1
     * co2_ppm_config : {"h_value_o":1800,"h_value_r":2000,"h_value_y":1500,"l_value_o":300,"l_value_r":100,"l_value_y":500}
     * co_ppm_config : {"h_value_o":2500,"h_value_r":3000,"h_value_y":1500,"l_value_o":600,"l_value_r":300,"l_value_y":1000}
     * ec_config : {"h_value_o":8,"h_value_r":9,"h_value_y":7,"l_value_o":2,"l_value_r":1,"l_value_y":3}
     * lux_config : {"h_value_o":10000,"h_value_r":15000,"h_value_y":8000,"l_value_o":1000,"l_value_r":300,"l_value_y":3000}
     * ph_config : {"h_value_o":8,"h_value_r":9,"h_value_y":7,"l_value_o":2,"l_value_r":1,"l_value_y":3}
     * repeatcount : 1
     * repeatdelay : 10
     * smoke_ppm_config : {"h_value_o":300,"h_value_r":500,"h_value_y":200}
     * soil_humidity_config : {"h_value_o":80,"h_value_r":90,"h_value_y":70,"l_value_o":20,"l_value_r":10,"l_value_y":30}
     * soil_temperature_config : {"h_value_o":35,"h_value_r":40,"h_value_y":30,"l_value_o":10,"l_value_r":5,"l_value_y":15}
     * strategy_id : 0
     * strategy_name : 系统默认报警策略
     * tenantid : 0
     * updatetime : 2017-06-16 14:18:28
     */

    private String _class;
    private String _id;
    private AirHumidityConfigBean air_humidity_config;
    private AirTemperatureConfigBean air_temperature_config;
    private ChargeConfigBean charge_config;
    private Integer checkdelay;
    private Co2PpmConfigBean co2_ppm_config;
    private CoPpmConfigBean co_ppm_config;
    private EcConfigBean ec_config;
    private LuxConfigBean lux_config;
    private PhConfigBean ph_config;
    private Integer repeatcount;
    private Integer repeatdelay;
    private SmokePpmConfigBean smoke_ppm_config;
    private SoilHumidityConfigBean soil_humidity_config;
    private SoilTemperatureConfigBean soil_temperature_config;
    private String strategy_id;
    private String strategy_name;
    private String tenantid;
    private String updatetime;

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public AirHumidityConfigBean getAir_humidity_config() {
        return air_humidity_config;
    }

    public void setAir_humidity_config(AirHumidityConfigBean air_humidity_config) {
        this.air_humidity_config = air_humidity_config;
    }

    public AirTemperatureConfigBean getAir_temperature_config() {
        return air_temperature_config;
    }

    public void setAir_temperature_config(AirTemperatureConfigBean air_temperature_config) {
        this.air_temperature_config = air_temperature_config;
    }

    public ChargeConfigBean getCharge_config() {
        return charge_config;
    }

    public void setCharge_config(ChargeConfigBean charge_config) {
        this.charge_config = charge_config;
    }

    public Integer getCheckdelay() {
        return checkdelay;
    }

    public void setCheckdelay(Integer checkdelay) {
        this.checkdelay = checkdelay;
    }

    public Co2PpmConfigBean getCo2_ppm_config() {
        return co2_ppm_config;
    }

    public void setCo2_ppm_config(Co2PpmConfigBean co2_ppm_config) {
        this.co2_ppm_config = co2_ppm_config;
    }

    public CoPpmConfigBean getCo_ppm_config() {
        return co_ppm_config;
    }

    public void setCo_ppm_config(CoPpmConfigBean co_ppm_config) {
        this.co_ppm_config = co_ppm_config;
    }

    public EcConfigBean getEc_config() {
        return ec_config;
    }

    public void setEc_config(EcConfigBean ec_config) {
        this.ec_config = ec_config;
    }

    public LuxConfigBean getLux_config() {
        return lux_config;
    }

    public void setLux_config(LuxConfigBean lux_config) {
        this.lux_config = lux_config;
    }

    public PhConfigBean getPh_config() {
        return ph_config;
    }

    public void setPh_config(PhConfigBean ph_config) {
        this.ph_config = ph_config;
    }

    public Integer getRepeatcount() {
        return repeatcount;
    }

    public void setRepeatcount(Integer repeatcount) {
        this.repeatcount = repeatcount;
    }

    public Integer getRepeatdelay() {
        return repeatdelay;
    }

    public void setRepeatdelay(Integer repeatdelay) {
        this.repeatdelay = repeatdelay;
    }

    public SmokePpmConfigBean getSmoke_ppm_config() {
        return smoke_ppm_config;
    }

    public void setSmoke_ppm_config(SmokePpmConfigBean smoke_ppm_config) {
        this.smoke_ppm_config = smoke_ppm_config;
    }

    public SoilHumidityConfigBean getSoil_humidity_config() {
        return soil_humidity_config;
    }

    public void setSoil_humidity_config(SoilHumidityConfigBean soil_humidity_config) {
        this.soil_humidity_config = soil_humidity_config;
    }

    public SoilTemperatureConfigBean getSoil_temperature_config() {
        return soil_temperature_config;
    }

    public void setSoil_temperature_config(SoilTemperatureConfigBean soil_temperature_config) {
        this.soil_temperature_config = soil_temperature_config;
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

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    
    public static class AirHumidityConfigBean {
        /**
         * h_value_o : 80
         * h_value_r : 90
         * h_value_y : 70
         * l_value_o : 20
         * l_value_r : 10
         * l_value_y : 30
         */

        private Integer h_value_o;
        private Integer h_value_r;
        private Integer h_value_y;
        private Integer l_value_o;
        private Integer l_value_r;
        private Integer l_value_y;

        public Integer getH_value_o() {
            return h_value_o;
        }

        public void setH_value_o(Integer h_value_o) {
            this.h_value_o = h_value_o;
        }

        public Integer getH_value_r() {
            return h_value_r;
        }

        public void setH_value_r(Integer h_value_r) {
            this.h_value_r = h_value_r;
        }

        public Integer getH_value_y() {
            return h_value_y;
        }

        public void setH_value_y(Integer h_value_y) {
            this.h_value_y = h_value_y;
        }

        public Integer getL_value_o() {
            return l_value_o;
        }

        public void setL_value_o(Integer l_value_o) {
            this.l_value_o = l_value_o;
        }

        public Integer getL_value_r() {
            return l_value_r;
        }

        public void setL_value_r(Integer l_value_r) {
            this.l_value_r = l_value_r;
        }

        public Integer getL_value_y() {
            return l_value_y;
        }

        public void setL_value_y(Integer l_value_y) {
            this.l_value_y = l_value_y;
        }
    }

    public static class AirTemperatureConfigBean {
        /**
         * h_value_o : 35
         * h_value_r : 40
         * h_value_y : 30
         * l_value_o : 10
         * l_value_r : 5
         * l_value_y : 15
         */

        private Integer h_value_o;
        private Integer h_value_r;
        private Integer h_value_y;
        private Integer l_value_o;
        private Integer l_value_r;
        private Integer l_value_y;

        public Integer getH_value_o() {
            return h_value_o;
        }

        public void setH_value_o(Integer h_value_o) {
            this.h_value_o = h_value_o;
        }

        public Integer getH_value_r() {
            return h_value_r;
        }

        public void setH_value_r(Integer h_value_r) {
            this.h_value_r = h_value_r;
        }

        public Integer getH_value_y() {
            return h_value_y;
        }

        public void setH_value_y(Integer h_value_y) {
            this.h_value_y = h_value_y;
        }

        public Integer getL_value_o() {
            return l_value_o;
        }

        public void setL_value_o(Integer l_value_o) {
            this.l_value_o = l_value_o;
        }

        public Integer getL_value_r() {
            return l_value_r;
        }

        public void setL_value_r(Integer l_value_r) {
            this.l_value_r = l_value_r;
        }

        public Integer getL_value_y() {
            return l_value_y;
        }

        public void setL_value_y(Integer l_value_y) {
            this.l_value_y = l_value_y;
        }
    }

    public static class ChargeConfigBean {
        /**
         * l_value_o : 20
         * l_value_r : 10
         * l_value_y : 40
         */

        private Integer l_value_o;
        private Integer l_value_r;
        @Override
		public String toString() {
			return "ChargeConfigBean [l_value_o=" + l_value_o + ", l_value_r="
					+ l_value_r + ", l_value_y=" + l_value_y + "]";
		}

		private Integer l_value_y;

        public Integer getL_value_o() {
            return l_value_o;
        }

        public void setL_value_o(Integer l_value_o) {
            this.l_value_o = l_value_o;
        }

        public Integer getL_value_r() {
            return l_value_r;
        }

        public void setL_value_r(Integer l_value_r) {
            this.l_value_r = l_value_r;
        }

        public Integer getL_value_y() {
            return l_value_y;
        }

        public void setL_value_y(Integer l_value_y) {
            this.l_value_y = l_value_y;
        }
    }

    public static class Co2PpmConfigBean {
        /**
         * h_value_o : 1800
         * h_value_r : 2000
         * h_value_y : 1500
         * l_value_o : 300
         * l_value_r : 100
         * l_value_y : 500
         */

        private Integer h_value_o;
        private Integer h_value_r;
        private Integer h_value_y;
        private Integer l_value_o;
        private Integer l_value_r;
        private Integer l_value_y;

        public Integer getH_value_o() {
            return h_value_o;
        }

        public void setH_value_o(Integer h_value_o) {
            this.h_value_o = h_value_o;
        }

        public Integer getH_value_r() {
            return h_value_r;
        }

        public void setH_value_r(Integer h_value_r) {
            this.h_value_r = h_value_r;
        }

        public Integer getH_value_y() {
            return h_value_y;
        }

        public void setH_value_y(Integer h_value_y) {
            this.h_value_y = h_value_y;
        }

        public Integer getL_value_o() {
            return l_value_o;
        }

        public void setL_value_o(Integer l_value_o) {
            this.l_value_o = l_value_o;
        }

        public Integer getL_value_r() {
            return l_value_r;
        }

        public void setL_value_r(Integer l_value_r) {
            this.l_value_r = l_value_r;
        }

        public Integer getL_value_y() {
            return l_value_y;
        }

        public void setL_value_y(Integer l_value_y) {
            this.l_value_y = l_value_y;
        }
    }

    public static class CoPpmConfigBean {
        /**
         * h_value_o : 2500
         * h_value_r : 3000
         * h_value_y : 1500
         * l_value_o : 600
         * l_value_r : 300
         * l_value_y : 1000
         */

        private Integer h_value_o;
        private Integer h_value_r;
        private Integer h_value_y;
        private Integer l_value_o;
        private Integer l_value_r;
        private Integer l_value_y;

        public Integer getH_value_o() {
            return h_value_o;
        }

        public void setH_value_o(Integer h_value_o) {
            this.h_value_o = h_value_o;
        }

        public Integer getH_value_r() {
            return h_value_r;
        }

        public void setH_value_r(Integer h_value_r) {
            this.h_value_r = h_value_r;
        }

        public Integer getH_value_y() {
            return h_value_y;
        }

        public void setH_value_y(Integer h_value_y) {
            this.h_value_y = h_value_y;
        }

        public Integer getL_value_o() {
            return l_value_o;
        }

        public void setL_value_o(Integer l_value_o) {
            this.l_value_o = l_value_o;
        }

        public Integer getL_value_r() {
            return l_value_r;
        }

        public void setL_value_r(Integer l_value_r) {
            this.l_value_r = l_value_r;
        }

        public Integer getL_value_y() {
            return l_value_y;
        }

        public void setL_value_y(Integer l_value_y) {
            this.l_value_y = l_value_y;
        }
    }

    public static class EcConfigBean {
        /**
         * h_value_o : 8
         * h_value_r : 9
         * h_value_y : 7
         * l_value_o : 2
         * l_value_r : 1
         * l_value_y : 3
         */

        private Integer h_value_o;
        private Integer h_value_r;
        private Integer h_value_y;
        private Integer l_value_o;
        private Integer l_value_r;
        private Integer l_value_y;

        public Integer getH_value_o() {
            return h_value_o;
        }

        public void setH_value_o(Integer h_value_o) {
            this.h_value_o = h_value_o;
        }

        public Integer getH_value_r() {
            return h_value_r;
        }

        public void setH_value_r(Integer h_value_r) {
            this.h_value_r = h_value_r;
        }

        public Integer getH_value_y() {
            return h_value_y;
        }

        public void setH_value_y(Integer h_value_y) {
            this.h_value_y = h_value_y;
        }

        public Integer getL_value_o() {
            return l_value_o;
        }

        public void setL_value_o(Integer l_value_o) {
            this.l_value_o = l_value_o;
        }

        public Integer getL_value_r() {
            return l_value_r;
        }

        public void setL_value_r(Integer l_value_r) {
            this.l_value_r = l_value_r;
        }

        public Integer getL_value_y() {
            return l_value_y;
        }

        public void setL_value_y(Integer l_value_y) {
            this.l_value_y = l_value_y;
        }
    }

    public static class LuxConfigBean {
        /**
         * h_value_o : 10000
         * h_value_r : 15000
         * h_value_y : 8000
         * l_value_o : 1000
         * l_value_r : 300
         * l_value_y : 3000
         */

        private Integer h_value_o;
        private Integer h_value_r;
        private Integer h_value_y;
        private Integer l_value_o;
        private Integer l_value_r;
        private Integer l_value_y;

        public Integer getH_value_o() {
            return h_value_o;
        }

        public void setH_value_o(Integer h_value_o) {
            this.h_value_o = h_value_o;
        }

        public Integer getH_value_r() {
            return h_value_r;
        }

        public void setH_value_r(Integer h_value_r) {
            this.h_value_r = h_value_r;
        }

        public Integer getH_value_y() {
            return h_value_y;
        }

        public void setH_value_y(Integer h_value_y) {
            this.h_value_y = h_value_y;
        }

        public Integer getL_value_o() {
            return l_value_o;
        }

        public void setL_value_o(Integer l_value_o) {
            this.l_value_o = l_value_o;
        }

        public Integer getL_value_r() {
            return l_value_r;
        }

        public void setL_value_r(Integer l_value_r) {
            this.l_value_r = l_value_r;
        }

        public Integer getL_value_y() {
            return l_value_y;
        }

        public void setL_value_y(Integer l_value_y) {
            this.l_value_y = l_value_y;
        }
    }

    public static class PhConfigBean {
        /**
         * h_value_o : 8
         * h_value_r : 9
         * h_value_y : 7
         * l_value_o : 2
         * l_value_r : 1
         * l_value_y : 3
         */

        private Integer h_value_o;
        private Integer h_value_r;
        private Integer h_value_y;
        private Integer l_value_o;
        private Integer l_value_r;
        private Integer l_value_y;

        public Integer getH_value_o() {
            return h_value_o;
        }

        public void setH_value_o(Integer h_value_o) {
            this.h_value_o = h_value_o;
        }

        public Integer getH_value_r() {
            return h_value_r;
        }

        public void setH_value_r(Integer h_value_r) {
            this.h_value_r = h_value_r;
        }

        public Integer getH_value_y() {
            return h_value_y;
        }

        public void setH_value_y(Integer h_value_y) {
            this.h_value_y = h_value_y;
        }

        public Integer getL_value_o() {
            return l_value_o;
        }

        public void setL_value_o(Integer l_value_o) {
            this.l_value_o = l_value_o;
        }

        public Integer getL_value_r() {
            return l_value_r;
        }

        public void setL_value_r(Integer l_value_r) {
            this.l_value_r = l_value_r;
        }

        public Integer getL_value_y() {
            return l_value_y;
        }

        public void setL_value_y(Integer l_value_y) {
            this.l_value_y = l_value_y;
        }
    }

    public static class SmokePpmConfigBean {
        /**
         * h_value_o : 300
         * h_value_r : 500
         * h_value_y : 200
         */

        private Integer h_value_o;
        private Integer h_value_r;
        private Integer h_value_y;

        public Integer getH_value_o() {
            return h_value_o;
        }

        public void setH_value_o(Integer h_value_o) {
            this.h_value_o = h_value_o;
        }

        public Integer getH_value_r() {
            return h_value_r;
        }

        public void setH_value_r(Integer h_value_r) {
            this.h_value_r = h_value_r;
        }

        public Integer getH_value_y() {
            return h_value_y;
        }

        public void setH_value_y(Integer h_value_y) {
            this.h_value_y = h_value_y;
        }
    }

    public static class SoilHumidityConfigBean {
        /**
         * h_value_o : 80
         * h_value_r : 90
         * h_value_y : 70
         * l_value_o : 20
         * l_value_r : 10
         * l_value_y : 30
         */

        private Integer h_value_o;
        private Integer h_value_r;
        private Integer h_value_y;
        private Integer l_value_o;
        private Integer l_value_r;
        private Integer l_value_y;

        public Integer getH_value_o() {
            return h_value_o;
        }

        public void setH_value_o(Integer h_value_o) {
            this.h_value_o = h_value_o;
        }

        public Integer getH_value_r() {
            return h_value_r;
        }

        public void setH_value_r(Integer h_value_r) {
            this.h_value_r = h_value_r;
        }

        public Integer getH_value_y() {
            return h_value_y;
        }

        public void setH_value_y(Integer h_value_y) {
            this.h_value_y = h_value_y;
        }

        public Integer getL_value_o() {
            return l_value_o;
        }

        public void setL_value_o(Integer l_value_o) {
            this.l_value_o = l_value_o;
        }

        public Integer getL_value_r() {
            return l_value_r;
        }

        public void setL_value_r(Integer l_value_r) {
            this.l_value_r = l_value_r;
        }

        public Integer getL_value_y() {
            return l_value_y;
        }

        public void setL_value_y(Integer l_value_y) {
            this.l_value_y = l_value_y;
        }
    }

    public static class SoilTemperatureConfigBean {
        /**
         * h_value_o : 35
         * h_value_r : 40
         * h_value_y : 30
         * l_value_o : 10
         * l_value_r : 5
         * l_value_y : 15
         */

        private Integer h_value_o;
        private Integer h_value_r;
        private Integer h_value_y;
        private Integer l_value_o;
        private Integer l_value_r;
        private Integer l_value_y;

        public Integer getH_value_o() {
            return h_value_o;
        }

        public void setH_value_o(Integer h_value_o) {
            this.h_value_o = h_value_o;
        }

        public Integer getH_value_r() {
            return h_value_r;
        }

        public void setH_value_r(Integer h_value_r) {
            this.h_value_r = h_value_r;
        }

        public Integer getH_value_y() {
            return h_value_y;
        }

        public void setH_value_y(Integer h_value_y) {
            this.h_value_y = h_value_y;
        }

        public Integer getL_value_o() {
            return l_value_o;
        }

        public void setL_value_o(Integer l_value_o) {
            this.l_value_o = l_value_o;
        }

        public Integer getL_value_r() {
            return l_value_r;
        }

        public void setL_value_r(Integer l_value_r) {
            this.l_value_r = l_value_r;
        }

        public Integer getL_value_y() {
            return l_value_y;
        }

        public void setL_value_y(Integer l_value_y) {
            this.l_value_y = l_value_y;
        }
    }
    
}
