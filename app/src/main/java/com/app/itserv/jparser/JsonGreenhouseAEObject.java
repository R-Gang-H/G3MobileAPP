package com.app.itserv.jparser;

/**
 * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * obj : {"id":"bbeeb6885d214f13015d2c2db9f90052","content":"ceshi","description":null,"status":"1","smartCount":null,"updateBy":null,"createBy":null,"createDate":null,"updateName":null,"updateDate":null,"createName":null,"tenantName":"北京平谷大棚基地","tenantId":"402883fd5c8fd7be015c8fde6fd90003","regionAddr":"5","latitude":"116.395954","longitude":"39.52676","usedArea":"1000","usedAreaMu":null,"ghouseCode":"dapeng1001","areaLength":"100","baseId":null,"areaWidth":"100","regionIdCity":"3","openDateOpen":1496332800000,"coveredArea":"1000","longtitudeFlag":null,"openDateStop":1527782400000,"ghouseFullname":"dapeng1hao","attachment1":null,"displayIndex":null,"coveredAreaMu":null,"baseFullname":null,"regionIdProvince":"2","regionIdCountry":"1","regionIdDistrict":"4","latitudeFlag":null,"singleproCount":null}
 * msg : 添加成功
 * success : true
 */

/**
 * @author 作者 E-mail:
 * @version 1.0
 * @date 创建时间：2017-7-10 下午8:20:49
 * @parameter
 * @return
 */

public class JsonGreenhouseAEObject {

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
         * id : bbeeb6885d214f13015d2c2db9f90052 content : ceshi description :
         * null status : 1 smartCount : null updateBy : null createBy : null
         * createDate : null updateName : null updateDate : null createName :
         * null tenantName : 北京平谷大棚基地 tenantId :
         * 402883fd5c8fd7be015c8fde6fd90003 regionAddr : 5 latitude : 116.395954
         * longitude : 39.52676 usedArea : 1000 usedAreaMu : null ghouseCode :
         * dapeng1001 areaLength : 100 baseId : null areaWidth : 100
         * regionIdCity : 3 openDateOpen : 1496332800000 coveredArea : 1000
         * longtitudeFlag : null openDateStop : 1527782400000 ghouseFullname :
         * dapeng1hao attachment1 : null displayIndex : null coveredAreaMu :
         * null baseFullname : null regionIdProvince : 2 regionIdCountry : 1
         * regionIdDistrict : 4 latitudeFlag : null singleproCount : null
         */

        private String id;
        private String content;
        private Object description;
        private String status;
        private Object smartCount;
        private Object updateBy;
        private Object createBy;
        private Object createDate;
        private Object updateName;
        private Object updateDate;
        private Object createName;
        private String tenantName;
        private String tenantId;
        private String regionAddr;
        private String latitude;
        private String longitude;
        private String usedArea;
        private Object usedAreaMu;
        private String ghouseCode;
        private String areaLength;
        private Object baseId;
        private String areaWidth;
        private String regionIdCity;
        private long openDateOpen;
        private String coveredArea;
        private Object longtitudeFlag;
        private long openDateStop;
        private String ghouseFullname;
        private Object attachment1;
        private Object displayIndex;
        private Object coveredAreaMu;
        private Object baseFullname;
        private String regionIdProvince;
        private String regionIdCountry;
        private String regionIdDistrict;
        private Object latitudeFlag;
        private Object singleproCount;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getSmartCount() {
            return smartCount;
        }

        public void setSmartCount(Object smartCount) {
            this.smartCount = smartCount;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
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

        public Object getUpdateName() {
            return updateName;
        }

        public void setUpdateName(Object updateName) {
            this.updateName = updateName;
        }

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }

        public Object getCreateName() {
            return createName;
        }

        public void setCreateName(Object createName) {
            this.createName = createName;
        }

        public String getTenantName() {
            return tenantName;
        }

        public void setTenantName(String tenantName) {
            this.tenantName = tenantName;
        }

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }

        public String getRegionAddr() {
            return regionAddr;
        }

        public void setRegionAddr(String regionAddr) {
            this.regionAddr = regionAddr;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getUsedArea() {
            return usedArea;
        }

        public void setUsedArea(String usedArea) {
            this.usedArea = usedArea;
        }

        public Object getUsedAreaMu() {
            return usedAreaMu;
        }

        public void setUsedAreaMu(Object usedAreaMu) {
            this.usedAreaMu = usedAreaMu;
        }

        public String getGhouseCode() {
            return ghouseCode;
        }

        public void setGhouseCode(String ghouseCode) {
            this.ghouseCode = ghouseCode;
        }

        public String getAreaLength() {
            return areaLength;
        }

        public void setAreaLength(String areaLength) {
            this.areaLength = areaLength;
        }

        public Object getBaseId() {
            return baseId;
        }

        public void setBaseId(Object baseId) {
            this.baseId = baseId;
        }

        public String getAreaWidth() {
            return areaWidth;
        }

        public void setAreaWidth(String areaWidth) {
            this.areaWidth = areaWidth;
        }

        public String getRegionIdCity() {
            return regionIdCity;
        }

        public void setRegionIdCity(String regionIdCity) {
            this.regionIdCity = regionIdCity;
        }

        public long getOpenDateOpen() {
            return openDateOpen;
        }

        public void setOpenDateOpen(long openDateOpen) {
            this.openDateOpen = openDateOpen;
        }

        public String getCoveredArea() {
            return coveredArea;
        }

        public void setCoveredArea(String coveredArea) {
            this.coveredArea = coveredArea;
        }

        public Object getLongtitudeFlag() {
            return longtitudeFlag;
        }

        public void setLongtitudeFlag(Object longtitudeFlag) {
            this.longtitudeFlag = longtitudeFlag;
        }

        public long getOpenDateStop() {
            return openDateStop;
        }

        public void setOpenDateStop(long openDateStop) {
            this.openDateStop = openDateStop;
        }

        public String getGhouseFullname() {
            return ghouseFullname;
        }

        public void setGhouseFullname(String ghouseFullname) {
            this.ghouseFullname = ghouseFullname;
        }

        public Object getAttachment1() {
            return attachment1;
        }

        public void setAttachment1(Object attachment1) {
            this.attachment1 = attachment1;
        }

        public Object getDisplayIndex() {
            return displayIndex;
        }

        public void setDisplayIndex(Object displayIndex) {
            this.displayIndex = displayIndex;
        }

        public Object getCoveredAreaMu() {
            return coveredAreaMu;
        }

        public void setCoveredAreaMu(Object coveredAreaMu) {
            this.coveredAreaMu = coveredAreaMu;
        }

        public Object getBaseFullname() {
            return baseFullname;
        }

        public void setBaseFullname(Object baseFullname) {
            this.baseFullname = baseFullname;
        }

        public String getRegionIdProvince() {
            return regionIdProvince;
        }

        public void setRegionIdProvince(String regionIdProvince) {
            this.regionIdProvince = regionIdProvince;
        }

        public String getRegionIdCountry() {
            return regionIdCountry;
        }

        public void setRegionIdCountry(String regionIdCountry) {
            this.regionIdCountry = regionIdCountry;
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

        public Object getSingleproCount() {
            return singleproCount;
        }

        public void setSingleproCount(Object singleproCount) {
            this.singleproCount = singleproCount;
        }
    }
}
