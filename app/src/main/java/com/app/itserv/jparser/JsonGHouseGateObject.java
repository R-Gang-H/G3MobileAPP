package com.app.itserv.jparser;

import java.io.Serializable;
import java.util.List;

/**
 * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","smartgateSize":3,"currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * obj : [{"id":"58816e8484163b5c5c7c0a23","owner":"cxm","version":"0.0","devName":null,"dev_name":"云洋数据智能网关","sn":"YBF00132000000043E","ghouseid":"402883fd5dbbcfcd015dbbf4a1430023","contact_name":"yunyangdata","sim_linenumber":"","harvest_time":"20160601","harvest_weight":"3000","dev_location":"中国北京市朝阳区京藏高速辅路","contact_number":"18001228446","ghouseName":"苹果种植基地","tnantName":"北京平谷大棚","tnantid":"402883fd5c8fd7be015c8fde6fd90003","plant_name":"大豆","area":"20","sim_imsi":"","sim_devid":"357942050123456","sim_imei":"","lat":"40.00847","sim_name":"无SIM卡","plant_trme":null,"geogroup":"e6aa03be-4dbb-4e4d-bec2-2330d014a6fa","lng":"116.37732","expectation":"3500","devLocation":null},{"id":"58d9cb893d9cfb697ee3e03b","owner":"cxm","version":"0.0","devName":null,"dev_name":"云洋数据智能网关","sn":"YBF0011500000001A6","ghouseid":"402883fd5dbbcfcd015dbbf4a1430023","contact_name":"yunyangdata","sim_linenumber":"","harvest_time":"20160601","harvest_weight":"3000","dev_location":"中国北京市朝阳区大屯路108号","contact_number":"18001228446","ghouseName":"苹果种植基地","tnantName":"北京平谷大棚","tnantid":"402883fd5c8fd7be015c8fde6fd90003","plant_name":"大豆","area":"20","sim_imsi":"460068132035713","sim_devid":"357942050123530","sim_imei":"","lat":"40.008347","sim_name":"运营商中国联通","plant_trme":null,"geogroup":"e6aa03be-4dbb-4e4d-bec2-2330d014a6fa","lng":"116.37732","expectation":"3500","devLocation":null},{"id":"58e1ea97baafd03018abb612","owner":"cxm","version":"0.0","devName":null,"dev_name":"云洋数据智能网关","sn":"YBF002060000000033","ghouseid":"402883fd5dbbcfcd015dbbf293db0021","contact_name":"yunyangdata","sim_linenumber":"","harvest_time":"20160601","harvest_weight":"3000","dev_location":"中国山东省潍坊市寿光市正泰路","contact_number":"18001228446","ghouseName":"桃子种植基地","tnantName":"北京平谷大棚","tnantid":"402883fd5c8fd7be015c8fde6fd90003","plant_name":"大豆","area":"20","sim_imsi":"","sim_devid":"","sim_imei":"","lat":"36.8939","sim_name":"无SIM卡","plant_trme":null,"geogroup":"2e6a06c6-3e37-41f1-b3dc-0c888a53d153","lng":"118.78227","expectation":"3500","devLocation":null}]
 * success : true
 * msg : OK
 */

/**
 * 大棚网关json解析类
 *
 * @author haoruigang
 * @Package com.app.itserv.jparser
 * @project yyShed
 * @ClassName: JsonGHouseGateObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-8-11 上午10:41:28
 */

public class JsonGHouseGateObject {


    private AttributesBean attributes;
    private boolean success;
    private String msg;
    private List<ObjBean> obj;

    public AttributesBean getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributesBean attributes) {
        this.attributes = attributes;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
         * smartgateSize : 3
         * currTenantId : 402883fd5c8fd7be015c8fde6fd90003
         */

        private String currUserId;
        private int smartgateSize;
        private String currTenantId;

        public String getCurrUserId() {
            return currUserId;
        }

        public void setCurrUserId(String currUserId) {
            this.currUserId = currUserId;
        }

        public int getSmartgateSize() {
            return smartgateSize;
        }

        public void setSmartgateSize(int smartgateSize) {
            this.smartgateSize = smartgateSize;
        }

        public String getCurrTenantId() {
            return currTenantId;
        }

        public void setCurrTenantId(String currTenantId) {
            this.currTenantId = currTenantId;
        }
    }

    public static class ObjBean implements Serializable {
        /**
         * id : 58816e8484163b5c5c7c0a23
         * owner : cxm
         * version : 0.0
         * devName : null
         * dev_name : 云洋数据智能网关
         * sn : YBF00132000000043E
         * ghouseid : 402883fd5dbbcfcd015dbbf4a1430023
         * contact_name : yunyangdata
         * sim_linenumber :
         * harvest_time : 20160601
         * harvest_weight : 3000
         * dev_location : 中国北京市朝阳区京藏高速辅路
         * contact_number : 18001228446
         * ghouseName : 苹果种植基地
         * tnantName : 北京平谷大棚
         * tnantid : 402883fd5c8fd7be015c8fde6fd90003
         * plant_name : 大豆
         * area : 20
         * sim_imsi :
         * sim_devid : 357942050123456
         * sim_imei :
         * lat : 40.00847
         * sim_name : 无SIM卡
         * plant_trme : null
         * geogroup : e6aa03be-4dbb-4e4d-bec2-2330d014a6fa
         * lng : 116.37732
         * expectation : 3500
         * devLocation : null
         */

        private String id;//主键
        private String owner;//所属用户
        private String version;//版本号
        private Object devName;//用户列表显示（暂不用）
        private String dev_name;//网关名称
        private String sn;//SN号
        private String ghouseid;//大棚id
        private String contact_name;
        private String sim_linenumber;
        private String harvest_time;
        private String harvest_weight;
        private String dev_location;
        private String contact_number;
        private String ghouseName;
        private String tnantName;
        private String tnantid;
        private String plant_name;
        private String area;
        private String sim_imsi;
        private String sim_devid;
        private String sim_imei;
        private String lat;
        private String sim_name;
        private Object plant_trme;
        private String geogroup;
        private String lng;
        private String expectation;
        private Object devLocation;//（暂不用）

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public Object getDevName() {
            return devName;
        }

        public void setDevName(Object devName) {
            this.devName = devName;
        }

        public String getDev_name() {
            return dev_name;
        }

        public void setDev_name(String dev_name) {
            this.dev_name = dev_name;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getGhouseid() {
            return ghouseid;
        }

        public void setGhouseid(String ghouseid) {
            this.ghouseid = ghouseid;
        }

        public String getContact_name() {
            return contact_name;
        }

        public void setContact_name(String contact_name) {
            this.contact_name = contact_name;
        }

        public String getSim_linenumber() {
            return sim_linenumber;
        }

        public void setSim_linenumber(String sim_linenumber) {
            this.sim_linenumber = sim_linenumber;
        }

        public String getHarvest_time() {
            return harvest_time;
        }

        public void setHarvest_time(String harvest_time) {
            this.harvest_time = harvest_time;
        }

        public String getHarvest_weight() {
            return harvest_weight;
        }

        public void setHarvest_weight(String harvest_weight) {
            this.harvest_weight = harvest_weight;
        }

        public String getDev_location() {
            return dev_location;
        }

        public void setDev_location(String dev_location) {
            this.dev_location = dev_location;
        }

        public String getContact_number() {
            return contact_number;
        }

        public void setContact_number(String contact_number) {
            this.contact_number = contact_number;
        }

        public String getGhouseName() {
            return ghouseName;
        }

        public void setGhouseName(String ghouseName) {
            this.ghouseName = ghouseName;
        }

        public String getTnantName() {
            return tnantName;
        }

        public void setTnantName(String tnantName) {
            this.tnantName = tnantName;
        }

        public String getTnantid() {
            return tnantid;
        }

        public void setTnantid(String tnantid) {
            this.tnantid = tnantid;
        }

        public String getPlant_name() {
            return plant_name;
        }

        public void setPlant_name(String plant_name) {
            this.plant_name = plant_name;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getSim_imsi() {
            return sim_imsi;
        }

        public void setSim_imsi(String sim_imsi) {
            this.sim_imsi = sim_imsi;
        }

        public String getSim_devid() {
            return sim_devid;
        }

        public void setSim_devid(String sim_devid) {
            this.sim_devid = sim_devid;
        }

        public String getSim_imei() {
            return sim_imei;
        }

        public void setSim_imei(String sim_imei) {
            this.sim_imei = sim_imei;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getSim_name() {
            return sim_name;
        }

        public void setSim_name(String sim_name) {
            this.sim_name = sim_name;
        }

        public Object getPlant_trme() {
            return plant_trme;
        }

        public void setPlant_trme(Object plant_trme) {
            this.plant_trme = plant_trme;
        }

        public String getGeogroup() {
            return geogroup;
        }

        public void setGeogroup(String geogroup) {
            this.geogroup = geogroup;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getExpectation() {
            return expectation;
        }

        public void setExpectation(String expectation) {
            this.expectation = expectation;
        }

        public Object getDevLocation() {
            return devLocation;
        }

        public void setDevLocation(Object devLocation) {
            this.devLocation = devLocation;
        }
    }
}
