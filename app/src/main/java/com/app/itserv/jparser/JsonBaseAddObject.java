package com.app.itserv.jparser;

/**
 * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * obj : {"id":"bbeeb6885d20e6df015d21331fb00033","content":"ceshi","description":null,"status":null,"baseFullname":"jidi1hao","regionIdCountry":"1","regionIdCity":"3","regionIdProvince":"2","regionIdDistrict":"4","latitudeFlag":null,"openDateOpen":1496246400000,"coveredArea":1000,"displayIndex":null,"attachment1":"","coveredAreaMu":1000,"longtitudeFlag":null,"openDateStop":null,"createBy":null,"createDate":null,"createName":null,"updateBy":null,"updateDate":null,"updateName":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","longitude":null,"regionAddr":"5","usedAreaMu":null,"usedArea":null,"latitude":null,"baseCode":"jidi0001"}
 * msg : 添加成功
 * success : true
 */

/**
 * 新增基地json报文解析类
 *
 * @author haoruigang
 * @Package com.app.itserv.jparser
 * @project yyshed
 * @ClassName: JsonBaseAddObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-6 下午4:40:03
 */

public class JsonBaseAddObject {

    private AttributesBean attributes;
    private ObjBean obj;
    private String msg;
    private boolean success;

    public AttributesBean getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributesBean attributes) {
        this.attributes = attributes;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
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

    public static class AttributesBean {
        /**
         * currUserId : 402883fd5c8fd7be015c8fe0a84e000a currTenantId :
         * 402883fd5c8fd7be015c8fde6fd90003
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
         * id : bbeeb6885d20e6df015d21331fb00033 content : ceshi description :
         * null status : null baseFullname : jidi1hao regionIdCountry : 1
         * regionIdCity : 3 regionIdProvince : 2 regionIdDistrict : 4
         * latitudeFlag : null openDateOpen : 1496246400000 coveredArea : 1000
         * displayIndex : null attachment1 : coveredAreaMu : 1000 longtitudeFlag
         * : null openDateStop : null createBy : null createDate : null
         * createName : null updateBy : null updateDate : null updateName : null
         * tenantId : 402883fd5c8fd7be015c8fde6fd90003 tenantName : 北京平谷大棚基地
         * longitude : null regionAddr : 5 usedAreaMu : null usedArea : null
         * latitude : null baseCode : jidi0001
         */

        private String id;
        private String content;
        private Object description;
        private Object status;
        private String baseFullname;
        private String regionIdCountry;
        private String regionIdCity;
        private String regionIdProvince;
        private String regionIdDistrict;
        private Object latitudeFlag;
        private long openDateOpen;
        private Object coveredArea;
        private Object displayIndex;
        private String attachment1;
        private Object coveredAreaMu;
        private Object longtitudeFlag;
        private Object openDateStop;
        private Object createBy;
        private Object createDate;
        private Object createName;
        private Object updateBy;
        private Object updateDate;
        private Object updateName;
        private String tenantId;
        private String tenantName;
        private Object longitude;
        private String regionAddr;
        private Object usedAreaMu;
        private Object usedArea;
        private Object latitude;
        private String baseCode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getBaseFullname() {
            return baseFullname;
        }

        public void setBaseFullname(String baseFullname) {
            this.baseFullname = baseFullname;
        }

        public String getRegionIdCountry() {
            return regionIdCountry;
        }

        public void setRegionIdCountry(String regionIdCountry) {
            this.regionIdCountry = regionIdCountry;
        }

        public String getRegionIdCity() {
            return regionIdCity;
        }

        public void setRegionIdCity(String regionIdCity) {
            this.regionIdCity = regionIdCity;
        }

        public String getRegionIdProvince() {
            return regionIdProvince;
        }

        public void setRegionIdProvince(String regionIdProvince) {
            this.regionIdProvince = regionIdProvince;
        }

        public String getRegionIdDistrict() {
            return regionIdDistrict;
        }

        public void setRegionIdDistrict(String regionIdDistrict) {
            this.regionIdDistrict = regionIdDistrict;
        }

        public Object getLatitudeFlag() {
            return latitudeFlag;
        }

        public void setLatitudeFlag(Object latitudeFlag) {
            this.latitudeFlag = latitudeFlag;
        }

        public long getOpenDateOpen() {
            return openDateOpen;
        }

        public void setOpenDateOpen(long openDateOpen) {
            this.openDateOpen = openDateOpen;
        }

        public Object getCoveredArea() {
            return coveredArea;
        }

        public void setCoveredArea(int coveredArea) {
            this.coveredArea = coveredArea;
        }

        public Object getDisplayIndex() {
            return displayIndex;
        }

        public void setDisplayIndex(Object displayIndex) {
            this.displayIndex = displayIndex;
        }

        public String getAttachment1() {
            return attachment1;
        }

        public void setAttachment1(String attachment1) {
            this.attachment1 = attachment1;
        }

        public Object getCoveredAreaMu() {
            return coveredAreaMu;
        }

        public void setCoveredAreaMu(int coveredAreaMu) {
            this.coveredAreaMu = coveredAreaMu;
        }

        public Object getLongtitudeFlag() {
            return longtitudeFlag;
        }

        public void setLongtitudeFlag(Object longtitudeFlag) {
            this.longtitudeFlag = longtitudeFlag;
        }

        public Object getOpenDateStop() {
            return openDateStop;
        }

        public void setOpenDateStop(Object openDateStop) {
            this.openDateStop = openDateStop;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public Object getCreateName() {
            return createName;
        }

        public void setCreateName(Object createName) {
            this.createName = createName;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }

        public Object getUpdateName() {
            return updateName;
        }

        public void setUpdateName(Object updateName) {
            this.updateName = updateName;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getTenantName() {
            return tenantName;
        }

        public void setTenantName(String tenantName) {
            this.tenantName = tenantName;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public String getRegionAddr() {
            return regionAddr;
        }

        public void setRegionAddr(String regionAddr) {
            this.regionAddr = regionAddr;
        }

        public Object getUsedAreaMu() {
            return usedAreaMu;
        }

        public void setUsedAreaMu(Object usedAreaMu) {
            this.usedAreaMu = usedAreaMu;
        }

        public Object getUsedArea() {
            return usedArea;
        }

        public void setUsedArea(Object usedArea) {
            this.usedArea = usedArea;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public String getBaseCode() {
            return baseCode;
        }

        public void setBaseCode(String baseCode) {
            this.baseCode = baseCode;
        }
    }
}
