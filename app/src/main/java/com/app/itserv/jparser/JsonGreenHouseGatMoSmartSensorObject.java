package com.app.itserv.jparser;

import java.util.List;

/**
 * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * obj : [{"id":"bbeeb6885c8c679c015c902cc336000a","content":"\t\t\t\t \r\n\t\t\t\t ","description":"","status":"ACTIVE","updateName":"管理员","createDate":1497067471000,"createBy":"cxm","createName":"程晓梅","updateBy":"admin","updateDate":1499221671000,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","regionAddr":"平谷基地1号","tenantName":"北京平谷大棚基地","usedArea":"1000","longitude":"","usedAreaMu":"1000","latitude":"","areaLength":"","baseId":"","ghouseCode":"100001","areaWidth":"","coveredAreaMu":"1000","regionIdProvince":"北京市","openDateStop":null,"displayIndex":"","openDateOpen":null,"attachment1":null,"baseFullname":"---请选择---","regionIdCountry":"中国","regionIdCity":"市辖区","regionIdDistrict":"平谷区","latitudeFlag":"","coveredArea":"1000","longtitudeFlag":"","ghouseFullname":"平谷大棚基地1号","singleproCount":null,"smartCount":"0"},{"id":"bbeeb6885c8c679c015c902d85bc000c","content":"\t\t\t\t \r\n\t\t\t\t ","description":"","status":"ACTIVE","updateName":"管理员","createDate":1497067521000,"createBy":"cxm","createName":"程晓梅","updateBy":"admin","updateDate":1499221671000,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","regionAddr":"平谷大棚基地2号","tenantName":"北京平谷大棚基地","usedArea":"1000","longitude":"","usedAreaMu":"1000","latitude":"","areaLength":"","baseId":"","ghouseCode":"100002","areaWidth":"","coveredAreaMu":"1000","regionIdProvince":"北京市","openDateStop":null,"displayIndex":"","openDateOpen":null,"attachment1":null,"baseFullname":"---请选择---","regionIdCountry":"中国","regionIdCity":"市辖区","regionIdDistrict":"平谷区","latitudeFlag":"","coveredArea":"1000","longtitudeFlag":"","ghouseFullname":"平谷大棚基地2号","singleproCount":null,"smartCount":"0"}]
 * msg : OK
 * success : true
 */

/**
 * 基地中大棚传感器列表json报文解析类
 *
 * @author jcy
 * @Package com.app.itserv.jparser
 * @project yyshed
 * @ClassName: JsonGreenHouseGatMoSmartSensorObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-8 上午11:03:50
 */

public class JsonGreenHouseGatMoSmartSensorObject {


    /**
     * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
     * obj : [{"message":null,"status":null,"temperature":"23.3","currentData":null,"deviceName":"土壤温湿度传感器","updateDate":"2017-03-03 19:43:46","humidity":"0","sn":"04012A000000029C","deviceType":"土壤温湿度传感器"},{"message":null,"status":null,"temperature":"0","currentData":null,"deviceName":"土壤温湿度传感器","updateDate":"2017-03-03 19:43:05","humidity":"0","sn":"04012A000000027B","deviceType":"土壤温湿度传感器"},{"message":null,"status":null,"temperature":null,"currentData":"0","deviceName":"未知设备","updateDate":"2017-04-17 19:38:33","humidity":null,"sn":"060207000000000F","deviceType":"二氧化碳传感器"},{"message":null,"status":null,"temperature":null,"currentData":"1","deviceName":"二氧化碳传感器","updateDate":"2017-03-28 23:08:44","humidity":null,"sn":"0000000F06020700","deviceType":"二氧化碳传感器"},{"message":null,"status":null,"temperature":"19.6","currentData":null,"deviceName":"空气温湿度传感器","updateDate":"2017-04-10 01:19:52","humidity":"35.6","sn":"01012C000000031D","deviceType":"空气温湿度传感器"},{"message":null,"status":null,"temperature":null,"currentData":"0.5","deviceName":"烟雾传感器","updateDate":"2017-06-24 16:29:53","humidity":null,"sn":"08012A00000002A2","deviceType":"烟雾传感器"},{"message":null,"status":null,"temperature":"26.3","currentData":null,"deviceName":"空气温湿度传感器","updateDate":"2017-06-30 22:19:21","humidity":"64.6","sn":"0102070000000063","deviceType":"空气温湿度传感器"}]
     * msg : OK
     * success : true
     */

    private AttributesBean attributes;
    private String msg;
    private boolean success;
    private List<ObjBean> obj;

    public AttributesBean getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributesBean attributes) {
        this.attributes = attributes;
    }

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

    public static class AttributesBean {
        /**
         * currUserId : 402883fd5c8fd7be015c8fe0a84e000a
         * currTenantId : 402883fd5c8fd7be015c8fde6fd90003
         */

        private String currUserId;
        private String currTenantId;

        public String getCurrUserId() {
            return currUserId;
        }

        public void setCurrUserId(String currUserId) {
            this.currUserId = currUserId;
        }

        public String getCurrTenantId() {
            return currTenantId;
        }

        public void setCurrTenantId(String currTenantId) {
            this.currTenantId = currTenantId;
        }
    }

    public static class ObjBean {
        /**
         * message : null
         * status : null
         * temperature : 23.3
         * currentData : null
         * deviceName : 土壤温湿度传感器
         * updateDate : 2017-03-03 19:43:46
         * humidity : 0
         * sn : 04012A000000029C
         * deviceType : 土壤温湿度传感器
         */

        private String message;
        private String status;
        private String temperature;
        private String currentData;
        private String deviceName;
        private String updateDate;
        private String humidity;
        private String sn;
        private String deviceType;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getCurrentData() {
            return currentData;
        }

        public void setCurrentData(String currentData) {
            this.currentData = currentData;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }
    }
}
