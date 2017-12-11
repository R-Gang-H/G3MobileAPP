package com.app.itserv.jparser;

import java.util.List;

/**
 * attributes : {"greenhouseSize":2,"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * obj : [{"id":"bbeeb6885c8c679c015c902cc336000a","content":"\t\t\t\t \r\n\t\t\t\t ","description":"","status":"ACTIVE","smartCount":"0","updateBy":"admin","createBy":"cxm","createDate":1497067471000,"updateName":"管理员","updateDate":1499221671000,"createName":"程晓梅","tenantName":"北京平谷大棚基地","tenantId":"402883fd5c8fd7be015c8fde6fd90003","regionAddr":"平谷基地1号","latitude":"","longitude":"","usedArea":"1000","usedAreaMu":"1000","ghouseCode":"100001","areaLength":"","baseId":"","areaWidth":"","regionIdCity":"市辖区","openDateOpen":null,"coveredArea":"1000","longtitudeFlag":"","openDateStop":null,"ghouseFullname":"平谷大棚基地1号","attachment1":null,"displayIndex":"","coveredAreaMu":"1000","baseFullname":"---请选择---","regionIdProvince":"北京市","regionIdCountry":"中国","regionIdDistrict":"平谷区","latitudeFlag":"","singleproCount":null},{"id":"bbeeb6885c8c679c015c902d85bc000c","content":"\t\t\t\t \r\n\t\t\t\t ","description":"","status":"ACTIVE","smartCount":"0","updateBy":"admin","createBy":"cxm","createDate":1497067521000,"updateName":"管理员","updateDate":1499221671000,"createName":"程晓梅","tenantName":"北京平谷大棚基地","tenantId":"402883fd5c8fd7be015c8fde6fd90003","regionAddr":"平谷大棚基地2号","latitude":"","longitude":"","usedArea":"1000","usedAreaMu":"1000","ghouseCode":"100002","areaLength":"","baseId":"","areaWidth":"","regionIdCity":"市辖区","openDateOpen":null,"coveredArea":"1000","longtitudeFlag":"","openDateStop":null,"ghouseFullname":"平谷大棚基地2号","attachment1":null,"displayIndex":"","coveredAreaMu":"1000","baseFullname":"---请选择---","regionIdProvince":"北京市","regionIdCountry":"中国","regionIdDistrict":"平谷区","latitudeFlag":"","singleproCount":null}]
 * msg : OK
 * success : true
 */

/**
 * 大棚环境平均值折线json报文解析类
 *
 * @author jcy
 * @Package com.app.itserv.jparser
 * @project yyshed
 * @ClassName: JsonGreenhouseManagerObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-10 下午4:25:34
 */
public class JsonGreenhouseAcerageCodeObject {

    /**
     * msg : OK obj :
     * [{"air_humidity":"0","air_temperature":"0","co2_ppm":"0","device_type"
     * :"smartgate"
     * ,"id":"58b9309093ca27ac0e86a895","lux":"0","scope":"scope1","smoke"
     * :"0","sn"
     * :"YBF0011500000001A6","soil_humidity":"0","soil_temperature":"0",
     * "update_date"
     * :1488531600001},{"air_humidity":"0","air_temperature":"0","co2_ppm"
     * :"0","device_type"
     * :"smartgate","id":"58b9354093ca27ac0e86a8a1","lux":"0","scope"
     * :"scope1","smoke"
     * :"0","sn":"YBF0011500000001A6","soil_humidity":"0","soil_temperature"
     * :"0",
     * "update_date":1488532800003},{"air_humidity":"0","air_temperature":"0"
     * ,"co2_ppm"
     * :"0","device_type":"smartgate","id":"58b939f093ca27ac0e86a8a7","lux"
     * :"0","scope"
     * :"scope1","smoke":"0","sn":"YBF0011500000001A6","soil_humidity"
     * :"0","soil_temperature"
     * :"0","update_date":1488534000002},{"air_humidity":"0"
     * ,"air_temperature":"0"
     * ,"co2_ppm":"0","device_type":"smartgate","id":"58b93ea093ca27ac0e86a8ad"
     * ,"lux":"0","scope":"scope1","smoke":"0","sn":"YBF0011500000001A6",
     * "soil_humidity"
     * :"0","soil_temperature":"0","update_date":1488535200003},{"air_humidity"
     * :"0","air_temperature":"0","co2_ppm":"0","device_type":"smartgate","id":
     * "58b9435093ca27ac0e86a8b9"
     * ,"lux":"0","scope":"scope1","smoke":"0","sn":"YBF0011500000001A6"
     * ,"soil_humidity":"0","soil_temperature":"0","update_date":1488536400003}]
     * success : true
     */

    private String msg;
    private boolean success;
    private List<ObjBean> obj;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * air_humidity : 0 air_temperature : 0 co2_ppm : 0 device_type :
         * smartgate id : 58b9309093ca27ac0e86a895 lux : 0 scope : scope1 smoke
         * : 0 sn : YBF0011500000001A6 soil_humidity : 0 soil_temperature : 0
         * update_date : 1488531600001
         */

        private String air_humidity;
        private String air_temperature;
        private String co2_ppm;
        private String device_type;
        private String id;
        private String lux;
        private String scope;
        private String smoke;
        private String sn;
        private String currentData;
        private String deviceType;
        private String updateDate;
        private String message;
        private String humidity;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        private String temperature;
        private String soil_humidity;
        private String soil_temperature;
        private long update_date;

        public String getCurrentData() {
            return currentData;
        }

        public void setCurrentData(String currentData) {
            this.currentData = currentData;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getAir_humidity() {
            return air_humidity;
        }

        public void setAir_humidity(String air_humidity) {
            this.air_humidity = air_humidity;
        }

        public String getAir_temperature() {
            return air_temperature;
        }

        public void setAir_temperature(String air_temperature) {
            this.air_temperature = air_temperature;
        }

        public String getCo2_ppm() {
            return co2_ppm;
        }

        public void setCo2_ppm(String co2_ppm) {
            this.co2_ppm = co2_ppm;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLux() {
            return lux;
        }

        public void setLux(String lux) {
            this.lux = lux;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getSmoke() {
            return smoke;
        }

        public void setSmoke(String smoke) {
            this.smoke = smoke;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getSoil_humidity() {
            return soil_humidity;
        }

        public void setSoil_humidity(String soil_humidity) {
            this.soil_humidity = soil_humidity;
        }

        public String getSoil_temperature() {
            return soil_temperature;
        }

        public void setSoil_temperature(String soil_temperature) {
            this.soil_temperature = soil_temperature;
        }

        public long getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(long update_date) {
            this.update_date = update_date;
        }

        @Override
        public String toString() {
            return "ObjBean [air_humidity=" + air_humidity
                    + ", air_temperature=" + air_temperature + ", co2_ppm="
                    + co2_ppm + ", device_type=" + device_type + ", id=" + id
                    + ", lux=" + lux + ", scope=" + scope + ", smoke=" + smoke
                    + ", sn=" + sn + ", currentData=" + currentData
                    + ", deviceType=" + deviceType + ", updateDate="
                    + updateDate + ", message=" + message + ", humidity="
                    + humidity + ", temperature=" + temperature
                    + ", soil_humidity=" + soil_humidity
                    + ", soil_temperature=" + soil_temperature
                    + ", update_date=" + update_date + "]";
        }

    }

}
