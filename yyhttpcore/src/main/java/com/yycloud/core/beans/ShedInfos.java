package com.yycloud.core.beans;

import java.util.List;

public class ShedInfos {
	/*
    * components : [{"charge":"100","charge_state":"normal","dev_alias":"","dev_extend_type":"relaybox","dev_name":"电气柜2","dev_type":"relaybox","online_state":"online","owner":"YBF0011500000001A6","password":"","pool_list":[{"dev_extend_type":"排风机","dev_name":"排风机（南）","dev_type":"SINGLE","pool_index":1,"status":2},{"dev_extend_type":"循环风机","dev_name":"循环风机（南）","dev_type":"SINGLE","pool_index":2,"status":2},{"dev_extend_type":"水帘水泵","dev_name":"水帘水泵（北）","dev_type":"SINGLE","pool_index":3,"status":2},{"dev_extend_type":"微喷电机","dev_name":"微喷电机（北）","dev_type":"SINGLE","pool_index":4,"status":2},{"dev_extend_type":"内保温电机","dev_name":"内保温电机（北）","dev_type":"DOUBLE","pool_index":5,"status":2},{"dev_extend_type":"外遮阳电机","dev_name":"外遮阳电机（北）","dev_type":"DOUBLE","pool_index":6,"status":2},{"dev_extend_type":"内遮阳电机","dev_name":"内遮阳电机（北）","dev_type":"DOUBLE","pool_index":7,"status":2},{"dev_extend_type":"卷膜电机","dev_name":"卷膜电机（北）","dev_type":"DOUBLE","pool_index":8,"status":2},{"dev_extend_type":"东西走廊循环风机","dev_name":"东西走廊循环风机","dev_type":"SINGLE","pool_index":9,"status":2},{"dev_extend_type":"东西走廊内遮阳电机","dev_name":"东西走廊内遮阳电机","dev_type":"DOUBLE","pool_index":10,"status":2},{"dev_extend_type":"照明","dev_name":"照明（北）","dev_type":"SINGLE","pool_index":11,"status":2}],"remote":"0","server_time":"1487719755257","sn":"2502050000000003","status":"0","switchs":"11","user":""},{"air_humidity":"0","air_temperature":"0","charge":"100","charge_state":"normal","dev_alias":"undefined","dev_extend_type":"","dev_name":"未知设备","dev_type":"humidity-temperature","humidity_state":"normal","online_state":"offline","owner":"YBF0011500000001A6","password":"","server_time":"1487719755186","sn":"0000000000000149","temperature_state":"normal","user":""},{"charge":"100","charge_state":"normal","dev_alias":"","dev_extend_type":"erelay-ventilation","dev_name":"放风机","dev_type":"erelay2","online_state":"online","owner":"YBF0011500000001A6","password":"","pool_list":"[]","remote":"0","server_time":"1487719755205","sn":"21012C0000000391","status":"0","switchs":"11","user":""}]
    * smartgate : {"area":"20","contact_name":"yunyangdata","contact_number":"18001228446","dev_alias":"undefined","dev_location":"中国北京市朝阳区京藏高速辅路","dev_name":"云洋数据智能网关","expectation":"3500","harvest_time":"20160601","harvest_weight":"3000","lat":"40.002457","lng":"116.37078","online_state":"online","plant_name":"大豆","plant_time":"20151101","server_time":"1487719755181","sn":"YBF0011500000001A6","utc_offset":"28800","utc_timestamp":"1487748554171","version":"0.0"}
    */

   private SmartgateBean smartgate;
   private List<ComponentsBean> components;

   public SmartgateBean getSmartgate() {
       return smartgate;
   }

   public void setSmartgate(SmartgateBean smartgate) {
       this.smartgate = smartgate;
   }

   public List<ComponentsBean> getComponents() {
       return components;
   }

   public void setComponents(List<ComponentsBean> components) {
       this.components = components;
   }

   public static class SmartgateBean {
       /**
        * area : 20
        * contact_name : yunyangdata
        * contact_number : 18001228446
        * dev_alias : undefined
        * dev_location : 中国北京市朝阳区京藏高速辅路
        * dev_name : 云洋数据智能网关
        * expectation : 3500
        * harvest_time : 20160601
        * harvest_weight : 3000
        * lat : 40.002457
        * lng : 116.37078
        * online_state : online
        * plant_name : 大豆
        * plant_time : 20151101
        * server_time : 1487719755181
        * sn : YBF0011500000001A6
        * utc_offset : 28800
        * utc_timestamp : 1487748554171
        * version : 0.0
        */

       private String area;
       private String contact_name;
       private String contact_number;
       private String dev_alias;
       private String dev_location;
       private String dev_name;
       private String expectation;
       private String harvest_time;
       private String harvest_weight;
       private String lat;
       private String lng;
       private String online_state;
       private String plant_name;
       private String plant_time;
       private String server_time;
       private String sn;
       private String utc_offset;
       private String utc_timestamp;
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

       public String getDev_alias() {
           return dev_alias;
       }

       public void setDev_alias(String dev_alias) {
           this.dev_alias = dev_alias;
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

       public String getOnline_state() {
           return online_state;
       }

       public void setOnline_state(String online_state) {
           this.online_state = online_state;
       }

       public String getPlant_name() {
           return plant_name;
       }

       public void setPlant_name(String plant_name) {
           this.plant_name = plant_name;
       }

       public String getPlant_time() {
           return plant_time;
       }

       public void setPlant_time(String plant_time) {
           this.plant_time = plant_time;
       }

       public String getServer_time() {
           return server_time;
       }

       public void setServer_time(String server_time) {
           this.server_time = server_time;
       }

       public String getSn() {
           return sn;
       }

       public void setSn(String sn) {
           this.sn = sn;
       }

       public String getUtc_offset() {
           return utc_offset;
       }

       public void setUtc_offset(String utc_offset) {
           this.utc_offset = utc_offset;
       }

       public String getUtc_timestamp() {
           return utc_timestamp;
       }

       public void setUtc_timestamp(String utc_timestamp) {
           this.utc_timestamp = utc_timestamp;
       }

       public String getVersion() {
           return version;
       }

       public void setVersion(String version) {
           this.version = version;
       }
   }

   public static class ComponentsBean {
       /**
        * charge : 100
        * charge_state : normal
        * dev_alias :
        * dev_extend_type : relaybox
        * dev_name : 电气柜2
        * dev_type : relaybox
        * online_state : online
        * owner : YBF0011500000001A6
        * password :
        * pool_list : [{"dev_extend_type":"排风机","dev_name":"排风机（南）","dev_type":"SINGLE","pool_index":1,"status":2},{"dev_extend_type":"循环风机","dev_name":"循环风机（南）","dev_type":"SINGLE","pool_index":2,"status":2},{"dev_extend_type":"水帘水泵","dev_name":"水帘水泵（北）","dev_type":"SINGLE","pool_index":3,"status":2},{"dev_extend_type":"微喷电机","dev_name":"微喷电机（北）","dev_type":"SINGLE","pool_index":4,"status":2},{"dev_extend_type":"内保温电机","dev_name":"内保温电机（北）","dev_type":"DOUBLE","pool_index":5,"status":2},{"dev_extend_type":"外遮阳电机","dev_name":"外遮阳电机（北）","dev_type":"DOUBLE","pool_index":6,"status":2},{"dev_extend_type":"内遮阳电机","dev_name":"内遮阳电机（北）","dev_type":"DOUBLE","pool_index":7,"status":2},{"dev_extend_type":"卷膜电机","dev_name":"卷膜电机（北）","dev_type":"DOUBLE","pool_index":8,"status":2},{"dev_extend_type":"东西走廊循环风机","dev_name":"东西走廊循环风机","dev_type":"SINGLE","pool_index":9,"status":2},{"dev_extend_type":"东西走廊内遮阳电机","dev_name":"东西走廊内遮阳电机","dev_type":"DOUBLE","pool_index":10,"status":2},{"dev_extend_type":"照明","dev_name":"照明（北）","dev_type":"SINGLE","pool_index":11,"status":2}]
        * remote : 0
        * server_time : 1487719755257
        * sn : 2502050000000003
        * status : 0
        * switchs : 11
        * user :
        * air_humidity : 0
        * air_temperature : 0
        * humidity_state : normal
        * temperature_state : normal
        */

       private String charge;
       private String charge_state;
       private String dev_alias;
       private String dev_extend_type;
       private String dev_name;
       private String dev_type;
       private String online_state;
       private String owner;
       private String password;
       private String remote;
       private String server_time;
       private String sn;
       private String status;
       private String switchs;
       private String user;
       private String air_humidity;
       private String air_temperature;
       private String humidity_state;
       private String temperature_state;
       private List<PoolListBean> pool_list;

       public String getCharge() {
           return charge;
       }

       public void setCharge(String charge) {
           this.charge = charge;
       }

       public String getCharge_state() {
           return charge_state;
       }

       public void setCharge_state(String charge_state) {
           this.charge_state = charge_state;
       }

       public String getDev_alias() {
           return dev_alias;
       }

       public void setDev_alias(String dev_alias) {
           this.dev_alias = dev_alias;
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

       public String getOnline_state() {
           return online_state;
       }

       public void setOnline_state(String online_state) {
           this.online_state = online_state;
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

       public String getRemote() {
           return remote;
       }

       public void setRemote(String remote) {
           this.remote = remote;
       }

       public String getServer_time() {
           return server_time;
       }

       public void setServer_time(String server_time) {
           this.server_time = server_time;
       }

       public String getSn() {
           return sn;
       }

       public void setSn(String sn) {
           this.sn = sn;
       }

       public String getStatus() {
           return status;
       }

       public void setStatus(String status) {
           this.status = status;
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

       public String getHumidity_state() {
           return humidity_state;
       }

       public void setHumidity_state(String humidity_state) {
           this.humidity_state = humidity_state;
       }

       public String getTemperature_state() {
           return temperature_state;
       }

       public void setTemperature_state(String temperature_state) {
           this.temperature_state = temperature_state;
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
            * dev_type : SINGLE
            * pool_index : 1
            * status : 2
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
}
