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
 * @author haoruigang
 * @Package com.app.itserv.jparser
 * @project yyshed
 * @ClassName: JsonGreenHouseObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-8 上午11:03:50
 */

public class JsonGreenHouseObject {

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
         * id : bbeeb6885c8c679c015c902cc336000a content :
         * <p>
         * description : status : ACTIVE updateName : 管理员 createDate :
         * 1497067471000 createBy : cxm createName : 程晓梅 updateBy : admin
         * updateDate : 1499221671000 tenantId :
         * 402883fd5c8fd7be015c8fde6fd90003 regionAddr : 平谷基地1号 tenantName :
         * 北京平谷大棚基地 usedArea : 1000 longitude : usedAreaMu : 1000 latitude :
         * areaLength : baseId : ghouseCode : 100001 areaWidth : coveredAreaMu :
         * 1000 regionIdProvince : 北京市 openDateStop : null displayIndex :
         * openDateOpen : null attachment1 : null baseFullname : ---请选择---
         * regionIdCountry : 中国 regionIdCity : 市辖区 regionIdDistrict : 平谷区
         * latitudeFlag : coveredArea : 1000 longtitudeFlag : ghouseFullname :
         * 平谷大棚基地1号 singleproCount : null smartCount : 0
         */

        private String id;
        private String content;
        private String description;
        private String status;
        private String updateName;
        private long createDate;
        private String createBy;
        private String createName;
        private String updateBy;
        private long updateDate;
        private String tenantId;
        private String regionAddr;
        private String tenantName;
        private String usedArea;
        private String longitude;
        private String usedAreaMu;
        private String latitude;
        private String areaLength;
        private String baseId;
        private String ghouseCode;
        private String areaWidth;
        private String coveredAreaMu;
        private String regionIdProvince;
        private Object openDateStop;
        private int displayIndex;
        private Object openDateOpen;
        private Object attachment1;
        private String baseFullname;
        private String regionIdCountry;
        private String regionIdCity;
        private String regionIdDistrict;
        private String latitudeFlag;
        private String coveredArea;
        private String longtitudeFlag;
        private String ghouseFullname;
        private Object singleproCount;
        private String smartCount;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUpdateName() {
            return updateName;
        }

        public void setUpdateName(String updateName) {
            this.updateName = updateName;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
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

        public String getTenantName() {
            return tenantName;
        }

        public void setTenantName(String tenantName) {
            this.tenantName = tenantName;
        }

        public String getUsedArea() {
            return usedArea;
        }

        public void setUsedArea(String usedArea) {
            this.usedArea = usedArea;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getUsedAreaMu() {
            return usedAreaMu;
        }

        public void setUsedAreaMu(String usedAreaMu) {
            this.usedAreaMu = usedAreaMu;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getAreaLength() {
            return areaLength;
        }

        public void setAreaLength(String areaLength) {
            this.areaLength = areaLength;
        }

        public String getBaseId() {
            return baseId;
        }

        public void setBaseId(String baseId) {
            this.baseId = baseId;
        }

        public String getGhouseCode() {
            return ghouseCode;
        }

        public void setGhouseCode(String ghouseCode) {
            this.ghouseCode = ghouseCode;
        }

        public String getAreaWidth() {
            return areaWidth;
        }

        public void setAreaWidth(String areaWidth) {
            this.areaWidth = areaWidth;
        }

        public String getCoveredAreaMu() {
            return coveredAreaMu;
        }

        public void setCoveredAreaMu(String coveredAreaMu) {
            this.coveredAreaMu = coveredAreaMu;
        }

        public String getRegionIdProvince() {
            return regionIdProvince;
        }

        public void setRegionIdProvince(String regionIdProvince) {
            this.regionIdProvince = regionIdProvince;
        }

        public Object getOpenDateStop() {
            return openDateStop;
        }

        public void setOpenDateStop(Object openDateStop) {
            this.openDateStop = openDateStop;
        }

        public int getDisplayIndex() {
            return displayIndex;
        }

        public void setDisplayIndex(int displayIndex) {
            this.displayIndex = displayIndex;
        }

        public Object getOpenDateOpen() {
            return openDateOpen;
        }

        public void setOpenDateOpen(Object openDateOpen) {
            this.openDateOpen = openDateOpen;
        }

        public Object getAttachment1() {
            return attachment1;
        }

        public void setAttachment1(Object attachment1) {
            this.attachment1 = attachment1;
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

        public String getRegionIdDistrict() {
            return regionIdDistrict;
        }

        public void setRegionIdDistrict(String regionIdDistrict) {
            this.regionIdDistrict = regionIdDistrict;
        }

        public String getLatitudeFlag() {
            return latitudeFlag;
        }

        public void setLatitudeFlag(String latitudeFlag) {
            this.latitudeFlag = latitudeFlag;
        }

        public String getCoveredArea() {
            return coveredArea;
        }

        public void setCoveredArea(String coveredArea) {
            this.coveredArea = coveredArea;
        }

        public String getLongtitudeFlag() {
            return longtitudeFlag;
        }

        public void setLongtitudeFlag(String longtitudeFlag) {
            this.longtitudeFlag = longtitudeFlag;
        }

        public String getGhouseFullname() {
            return ghouseFullname;
        }

        public void setGhouseFullname(String ghouseFullname) {
            this.ghouseFullname = ghouseFullname;
        }

        public Object getSingleproCount() {
            return singleproCount;
        }

        public void setSingleproCount(Object singleproCount) {
            this.singleproCount = singleproCount;
        }

        public String getSmartCount() {
            return smartCount;
        }

        public void setSmartCount(String smartCount) {
            this.smartCount = smartCount;
        }
    }
}
