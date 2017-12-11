package com.app.itserv.jparser;

import java.util.List;

/**
 * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * obj : [{"id":"bbeeb6885c8c679c015c902cc336000a","content":"\t\t\t\t \r\n\t\t\t\t ","description":"","status":"ACTIVE","updateName":"管理员","createDate":1497067471000,"createBy":"cxm","createName":"程晓梅","updateBy":"admin","updateDate":1499221671000,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","regionAddr":"平谷基地1号","tenantName":"北京平谷大棚基地","usedArea":"1000","longitude":"","usedAreaMu":"1000","latitude":"","areaLength":"","baseId":"","ghouseCode":"100001","areaWidth":"","coveredAreaMu":"1000","regionIdProvince":"北京市","openDateStop":null,"displayIndex":"","openDateOpen":null,"attachment1":null,"baseFullname":"---请选择---","regionIdCountry":"中国","regionIdCity":"市辖区","regionIdDistrict":"平谷区","latitudeFlag":"","coveredArea":"1000","longtitudeFlag":"","ghouseFullname":"平谷大棚基地1号","singleproCount":null,"smartCount":"0"},{"id":"bbeeb6885c8c679c015c902d85bc000c","content":"\t\t\t\t \r\n\t\t\t\t ","description":"","status":"ACTIVE","updateName":"管理员","createDate":1497067521000,"createBy":"cxm","createName":"程晓梅","updateBy":"admin","updateDate":1499221671000,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","regionAddr":"平谷大棚基地2号","tenantName":"北京平谷大棚基地","usedArea":"1000","longitude":"","usedAreaMu":"1000","latitude":"","areaLength":"","baseId":"","ghouseCode":"100002","areaWidth":"","coveredAreaMu":"1000","regionIdProvince":"北京市","openDateStop":null,"displayIndex":"","openDateOpen":null,"attachment1":null,"baseFullname":"---请选择---","regionIdCountry":"中国","regionIdCity":"市辖区","regionIdDistrict":"平谷区","latitudeFlag":"","coveredArea":"1000","longtitudeFlag":"","ghouseFullname":"平谷大棚基地2号","singleproCount":null,"smartCount":"0"}]
 * msg : OK
 * success : true
 */
/**
 * 基地中大棚列json报文解析类
 *
 * @author jcy
 * @Package com.app.itserv.jparser
 * @project yyshed
 * @ClassName: JsonGreenHouseObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017年9月13日17:17:02
 * @version
 *
 */

public class JsonGreenHouseGatMoSmartObject {

    /**
     * attributes : {"currTenantId":"402883fd5c8fd7be015c8fde6fd90003","currUserId":"402883fd5c8fd7be015c8fe0a84e000a"}
     * msg : OK
     * obj : [{"area":"20","contact_name":"yunyangdata","contact_number":"18001228446","dev_location":"中国北京市朝阳区京藏高速辅路","dev_name":"云洋数据智能网关","expectation":"3500","geogroup":"e6aa03be-4dbb-4e4d-bec2-2330d014a6fa","ghouseName":"苹果种植基地","ghouseid":"402883fd5dbbcfcd015dbbf4a1430023","harvest_time":"20160601","harvest_weight":"3000","id":"58816e8484163b5c5c7c0a23","lat":"40.00847","lng":"116.37732","owner":"cxm","plant_name":"大豆","sim_devid":"357942050123456","sim_imei":"","sim_imsi":"","sim_linenumber":"","sim_name":"无SIM卡","sn":"YBF00132000000043E","tnantName":"北京平谷大棚","tnantid":"402883fd5c8fd7be015c8fde6fd90003","version":"0.0"},{"area":"20","contact_name":"yunyangdata","contact_number":"18001228446","dev_location":"中国北京市朝阳区京藏高速辅路","dev_name":"云洋数据智能网关057","expectation":"3500","geogroup":"e6aa03be-4dbb-4e4d-bec2-2330d014a6fa","ghouseName":"桃子种植基地","ghouseid":"402883fd5dbbcfcd015dbbf293db0021","harvest_time":"20160601","harvest_weight":"3000","id":"58d2769870a8d05c36c18ff9","lat":"40.00862","lng":"116.37759","owner":"cxm","plant_name":"大豆","sim_devid":"357942050123381","sim_imei":"","sim_imsi":"","sim_linenumber":"","sim_name":"SIM卡信息:无SIM卡","sn":"YBF002020000000057","tnantName":"北京平谷大棚","tnantid":"402883fd5c8fd7be015c8fde6fd90003","version":"0.0"},{"area":"20","contact_name":"yunyangdata","contact_number":"18001228446","dev_location":"中国北京市朝阳区大屯路108号","dev_name":"云洋数据智能网关","expectation":"3500","geogroup":"e6aa03be-4dbb-4e4d-bec2-2330d014a6fa","ghouseName":"苹果种植基地","ghouseid":"402883fd5dbbcfcd015dbbf4a1430023","harvest_time":"20160601","harvest_weight":"3000","id":"58d9cb893d9cfb697ee3e03b","lat":"40.008347","lng":"116.37732","owner":"cxm","plant_name":"大豆","sim_devid":"357942050123530","sim_imei":"","sim_imsi":"460068132035713","sim_linenumber":"","sim_name":"运营商中国联通","sn":"YBF0011500000001A6","tnantName":"北京平谷大棚","tnantid":"402883fd5c8fd7be015c8fde6fd90003","version":"0.0"}]
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
         * currTenantId : 402883fd5c8fd7be015c8fde6fd90003
         * currUserId : 402883fd5c8fd7be015c8fe0a84e000a
         */

        private String currTenantId;
        private String currUserId;

        public String getCurrTenantId() {
            return currTenantId;
        }

        public void setCurrTenantId(String currTenantId) {
            this.currTenantId = currTenantId;
        }

        public String getCurrUserId() {
            return currUserId;
        }

        public void setCurrUserId(String currUserId) {
            this.currUserId = currUserId;
        }
    }

    public static class ObjBean {
        /**
         * area : 20
         * contact_name : yunyangdata
         * contact_number : 18001228446
         * dev_location : 中国北京市朝阳区京藏高速辅路
         * dev_name : 云洋数据智能网关
         * expectation : 3500
         * geogroup : e6aa03be-4dbb-4e4d-bec2-2330d014a6fa
         * ghouseName : 苹果种植基地
         * ghouseid : 402883fd5dbbcfcd015dbbf4a1430023
         * harvest_time : 20160601
         * harvest_weight : 3000
         * id : 58816e8484163b5c5c7c0a23
         * lat : 40.00847
         * lng : 116.37732
         * owner : cxm
         * plant_name : 大豆
         * sim_devid : 357942050123456
         * sim_imei :
         * sim_imsi :
         * sim_linenumber :
         * sim_name : 无SIM卡
         * sn : YBF00132000000043E
         * tnantName : 北京平谷大棚
         * tnantid : 402883fd5c8fd7be015c8fde6fd90003
         * version : 0.0
         */

        private String area;
        private String contact_name;
        private String contact_number;
        private String dev_location;
        private String dev_name;
        private String expectation;
        private String geogroup;
        private String ghouseName;
        private String ghouseid;
        private String harvest_time;
        private String harvest_weight;
        private String id;
        private String lat;
        private String lng;
        private String owner;
        private String plant_name;
        private String sim_devid;
        private String sim_imei;
        private String sim_imsi;
        private String sim_linenumber;
        private String sim_name;
        private String sn;
        private String tnantName;
        private String tnantid;
        private String version;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getContact_name() {
            return contact_name;
        }

        public void setContact_name(String contact_name) {
            this.contact_name = contact_name;
        }

        public String getContact_number() {
            return contact_number;
        }

        public void setContact_number(String contact_number) {
            this.contact_number = contact_number;
        }

        public String getDev_location() {
            return dev_location;
        }

        public void setDev_location(String dev_location) {
            this.dev_location = dev_location;
        }

        public String getDev_name() {
            return dev_name;
        }

        public void setDev_name(String dev_name) {
            this.dev_name = dev_name;
        }

        public String getExpectation() {
            return expectation;
        }

        public void setExpectation(String expectation) {
            this.expectation = expectation;
        }

        public String getGeogroup() {
            return geogroup;
        }

        public void setGeogroup(String geogroup) {
            this.geogroup = geogroup;
        }

        public String getGhouseName() {
            return ghouseName;
        }

        public void setGhouseName(String ghouseName) {
            this.ghouseName = ghouseName;
        }

        public String getGhouseid() {
            return ghouseid;
        }

        public void setGhouseid(String ghouseid) {
            this.ghouseid = ghouseid;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getPlant_name() {
            return plant_name;
        }

        public void setPlant_name(String plant_name) {
            this.plant_name = plant_name;
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

        public String getSim_imsi() {
            return sim_imsi;
        }

        public void setSim_imsi(String sim_imsi) {
            this.sim_imsi = sim_imsi;
        }

        public String getSim_linenumber() {
            return sim_linenumber;
        }

        public void setSim_linenumber(String sim_linenumber) {
            this.sim_linenumber = sim_linenumber;
        }

        public String getSim_name() {
            return sim_name;
        }

        public void setSim_name(String sim_name) {
            this.sim_name = sim_name;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
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

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
