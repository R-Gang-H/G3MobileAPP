package com.app.itserv.jparser;

import java.util.List;

/**
 * attributes : {"yyGreenhouseDocList":0,"YyGreenhouseUserEntityList":0,"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","smartgateSize":0,"currTenantId":"402883fd5c8fd7be015c8fde6fd90003","singleproSize":0}
 * obj : {"yyGreenhouseDocList":[],"greenhouse":{"id":"bbeeb6885d2f42dd015d2fa5cb670036","content":"ceshi","description":null,"status":"1","updateName":"程晓梅","createBy":null,"createName":null,"updateDate":1499755375000,"updateBy":"cxm","createDate":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","regionAddr":"5","longitude":"39.52676","latitude":"116.395954","usedAreaMu":null,"usedArea":"1000","ghouseCode":"dapeng1001","areaLength":"100","areaWidth":"100","baseId":null,"smartCount":"0","regionIdProvince":"2","latitudeFlag":null,"regionIdDistrict":"4","coveredArea":"1000","coveredAreaMu":null,"baseFullname":null,"regionIdCountry":"1","longtitudeFlag":null,"openDateOpen":1496332800000,"regionIdCity":"3","ghouseFullname":"dapeng1hao","openDateStop":1527782400000,"attachment1":null,"displayIndex":null,"singleproCount":"0"},"YyGreenhouseUserEntityList":[],"singleproList":[],"smartgateList":[]}
 * msg : OK
 * success : true
 */

/**
 * 大棚查看json报文解析类
 *
 * @author haoruigang
 * @Package com.app.itserv.jparser
 * @project yyshed
 * @ClassName: JsonGreenHouseViewObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-12 下午2:13:37
 */
public class JsonGreenHouseViewObject {

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
         * yyGreenhouseDocList : 0 YyGreenhouseUserEntityList : 0 currUserId :
         * 402883fd5c8fd7be015c8fe0a84e000a smartgateSize : 0 currTenantId :
         * 402883fd5c8fd7be015c8fde6fd90003 singleproSize : 0
         */

        private int yyGreenhouseDocList;
        private int YyGreenhouseUserEntityList;
        private String currUserId;
        private int smartgateSize;
        private String currTenantId;
        private int singleproSize;

        public int getYyGreenhouseDocList() {
            return yyGreenhouseDocList;
        }

        public void setYyGreenhouseDocList(int yyGreenhouseDocList) {
            this.yyGreenhouseDocList = yyGreenhouseDocList;
        }

        public int getYyGreenhouseUserEntityList() {
            return YyGreenhouseUserEntityList;
        }

        public void setYyGreenhouseUserEntityList(int YyGreenhouseUserEntityList) {
            this.YyGreenhouseUserEntityList = YyGreenhouseUserEntityList;
        }

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

        public int getSingleproSize() {
            return singleproSize;
        }

        public void setSingleproSize(int singleproSize) {
            this.singleproSize = singleproSize;
        }
    }

    public static class ObjBean {
        /**
         * yyGreenhouseDocList : [] greenhouse :
         * {"id":"bbeeb6885d2f42dd015d2fa5cb670036"
         * ,"content":"ceshi","description"
         * :null,"status":"1","updateName":"程晓梅",
         * "createBy":null,"createName":null
         * ,"updateDate":1499755375000,"updateBy"
         * :"cxm","createDate":null,"tenantId"
         * :"402883fd5c8fd7be015c8fde6fd90003"
         * ,"tenantName":"北京平谷大棚基地","regionAddr"
         * :"5","longitude":"39.52676","latitude"
         * :"116.395954","usedAreaMu":null,
         * "usedArea":"1000","ghouseCode":"dapeng1001"
         * ,"areaLength":"100","areaWidth"
         * :"100","baseId":null,"smartCount":"0","regionIdProvince"
         * :"2","latitudeFlag"
         * :null,"regionIdDistrict":"4","coveredArea":"1000","coveredAreaMu"
         * :null
         * ,"baseFullname":null,"regionIdCountry":"1","longtitudeFlag":null,
         * "openDateOpen"
         * :1496332800000,"regionIdCity":"3","ghouseFullname":"dapeng1hao"
         * ,"openDateStop":1527782400000,"attachment1":null,"displayIndex":null,
         * "singleproCount":"0"} YyGreenhouseUserEntityList : [] singleproList :
         * [] smartgateList : []
         */

        private GreenhouseBean greenhouse;
        private List<?> yyGreenhouseDocList;
        private List<?> YyGreenhouseUserEntityList;
        private List<?> singleproList;
        private List<?> smartgateList;
        private List<YyPlantingplanEntityListBean> yyPlantingplanEntityList;

        public GreenhouseBean getGreenhouse() {
            return greenhouse;
        }

        public void setGreenhouse(GreenhouseBean greenhouse) {
            this.greenhouse = greenhouse;
        }

        public List<YyPlantingplanEntityListBean> getYyPlantingplanEntityList() {
            return yyPlantingplanEntityList;
        }

        public void setYyPlantingplanEntityList(
                List<YyPlantingplanEntityListBean> yyPlantingplanEntityList) {
            this.yyPlantingplanEntityList = yyPlantingplanEntityList;
        }

        public List<?> getYyGreenhouseDocList() {
            return yyGreenhouseDocList;
        }

        public void setYyGreenhouseDocList(List<?> yyGreenhouseDocList) {
            this.yyGreenhouseDocList = yyGreenhouseDocList;
        }

        public List<?> getYyGreenhouseUserEntityList() {
            return YyGreenhouseUserEntityList;
        }

        public void setYyGreenhouseUserEntityList(
                List<?> YyGreenhouseUserEntityList) {
            this.YyGreenhouseUserEntityList = YyGreenhouseUserEntityList;
        }

        public List<?> getSingleproList() {
            return singleproList;
        }

        public void setSingleproList(List<?> singleproList) {
            this.singleproList = singleproList;
        }

        public List<?> getSmartgateList() {
            return smartgateList;
        }

        public void setSmartgateList(List<?> smartgateList) {
            this.smartgateList = smartgateList;
        }

        public static class GreenhouseBean {
            /**
             * id : bbeeb6885d2f42dd015d2fa5cb670036 content : ceshi description
             * : null status : 1 updateName : 程晓梅 createBy : null createName :
             * null updateDate : 1499755375000 updateBy : cxm createDate : null
             * tenantId : 402883fd5c8fd7be015c8fde6fd90003 tenantName : 北京平谷大棚基地
             * regionAddr : 5 longitude : 39.52676 latitude : 116.395954
             * usedAreaMu : null usedArea : 1000 ghouseCode : dapeng1001
             * areaLength : 100 areaWidth : 100 baseId : null smartCount : 0
             * regionIdProvince : 2 latitudeFlag : null regionIdDistrict : 4
             * coveredArea : 1000 coveredAreaMu : null baseFullname : null
             * regionIdCountry : 1 longtitudeFlag : null openDateOpen :
             * 1496332800000 regionIdCity : 3 ghouseFullname : dapeng1hao
             * openDateStop : 1527782400000 attachment1 : null displayIndex :
             * null singleproCount : 0
             */

            private String id;
            private String content;
            private Object description;
            private String status;
            private String updateName;
            private Object createBy;
            private Object createName;
            private long updateDate;
            private String updateBy;
            private Object createDate;
            private String tenantId;
            private String tenantName;
            private String regionAddr;
            private String longitude;
            private String latitude;
            private Object usedAreaMu;
            private String usedArea;
            private String ghouseCode;
            private String areaLength;
            private String areaWidth;
            private Object baseId;
            private String smartCount;
            private String regionIdProvince;
            private Object latitudeFlag;
            private String regionIdDistrict;
            private String coveredArea;
            private Object coveredAreaMu;
            private Object baseFullname;
            private String regionIdCountry;
            private Object longtitudeFlag;
            private long openDateOpen;
            private String regionIdCity;
            private String ghouseFullname;
            private long openDateStop;
            private Object attachment1;
            private Object displayIndex;
            private String singleproCount;

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

            public String getUpdateName() {
                return updateName;
            }

            public void setUpdateName(String updateName) {
                this.updateName = updateName;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public Object getCreateName() {
                return createName;
            }

            public void setCreateName(Object createName) {
                this.createName = createName;
            }

            public long getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(long updateDate) {
                this.updateDate = updateDate;
            }

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
            }

            public Object getCreateDate() {
                return createDate;
            }

            public void setCreateDate(Object createDate) {
                this.createDate = createDate;
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

            public String getRegionAddr() {
                return regionAddr;
            }

            public void setRegionAddr(String regionAddr) {
                this.regionAddr = regionAddr;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public Object getUsedAreaMu() {
                return usedAreaMu;
            }

            public void setUsedAreaMu(Object usedAreaMu) {
                this.usedAreaMu = usedAreaMu;
            }

            public String getUsedArea() {
                return usedArea;
            }

            public void setUsedArea(String usedArea) {
                this.usedArea = usedArea;
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

            public String getAreaWidth() {
                return areaWidth;
            }

            public void setAreaWidth(String areaWidth) {
                this.areaWidth = areaWidth;
            }

            public Object getBaseId() {
                return baseId;
            }

            public void setBaseId(Object baseId) {
                this.baseId = baseId;
            }

            public String getSmartCount() {
                return smartCount;
            }

            public void setSmartCount(String smartCount) {
                this.smartCount = smartCount;
            }

            public String getRegionIdProvince() {
                return regionIdProvince;
            }

            public void setRegionIdProvince(String regionIdProvince) {
                this.regionIdProvince = regionIdProvince;
            }

            public Object getLatitudeFlag() {
                return latitudeFlag;
            }

            public void setLatitudeFlag(Object latitudeFlag) {
                this.latitudeFlag = latitudeFlag;
            }

            public String getRegionIdDistrict() {
                return regionIdDistrict;
            }

            public void setRegionIdDistrict(String regionIdDistrict) {
                this.regionIdDistrict = regionIdDistrict;
            }

            public String getCoveredArea() {
                return coveredArea;
            }

            public void setCoveredArea(String coveredArea) {
                this.coveredArea = coveredArea;
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

            public String getRegionIdCountry() {
                return regionIdCountry;
            }

            public void setRegionIdCountry(String regionIdCountry) {
                this.regionIdCountry = regionIdCountry;
            }

            public Object getLongtitudeFlag() {
                return longtitudeFlag;
            }

            public void setLongtitudeFlag(Object longtitudeFlag) {
                this.longtitudeFlag = longtitudeFlag;
            }

            public long getOpenDateOpen() {
                return openDateOpen;
            }

            public void setOpenDateOpen(long openDateOpen) {
                this.openDateOpen = openDateOpen;
            }

            public String getRegionIdCity() {
                return regionIdCity;
            }

            public void setRegionIdCity(String regionIdCity) {
                this.regionIdCity = regionIdCity;
            }

            public String getGhouseFullname() {
                return ghouseFullname;
            }

            public void setGhouseFullname(String ghouseFullname) {
                this.ghouseFullname = ghouseFullname;
            }

            public long getOpenDateStop() {
                return openDateStop;
            }

            public void setOpenDateStop(long openDateStop) {
                this.openDateStop = openDateStop;
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

            public String getSingleproCount() {
                return singleproCount;
            }

            public void setSingleproCount(String singleproCount) {
                this.singleproCount = singleproCount;
            }
        }

        public static class YyPlantingplanEntityListBean {
            /**
             * actualPickdateEnd : 1502726400000 actualPickdateStart :
             * 1501516800000 actualPlantdateEnd : 1263484800000
             * actualPlantdateStart : 1262275200000 actualYield : 1000
             * baseFullname : 平谷大棚一号基地 content : 重阳红种植 一次性种植 createBy : cxm
             * createDate : 1502099243000 createName : 程晓梅 cropCategoryDefine :
             * 桃子 description : gbaseId : 402883fd5dbbcfcd015dbbe618e60018
             * ghouseFullname : 桃子种植基地 ghouseId :
             * 402883fd5dbbcfcd015dbbf293db0021 headBy :
             * 402883fd5dbb8a9c015dbbc47c76008b headName : zz1 id :
             * 402883fd5dbbcfcd015dbc1782630069 planArea : 100 planAreaMu :
             * planCode : 100001 planFullname : 重阳红种植 planPickdateEnd :
             * 1376496000000 planPickdateStart : 1375286400000 planPlantdateEnd
             * : 1263484800000 planPlantdateStart : 1262275200000 planStep :
             * HARVESTED planYield : 1000 status : ACTIVE sysCompanyCode : A08
             * sysOrgCode : A08 tenantId : 402883fd5c8fd7be015c8fde6fd90003
             * tenantName : 北京平谷大棚 updateBy : updateName :
             */

            private long actualPickdateEnd;
            private long actualPickdateStart;
            private long actualPlantdateEnd;
            private long actualPlantdateStart;
            private String actualYield;
            private String baseFullname;
            private String content;
            private String createBy;
            private long createDate;
            private String createName;
            private String cropCategoryDefine;
            private String description;
            private String gbaseId;
            private String ghouseFullname;
            private String ghouseId;
            private String headBy;
            private String headName;
            private String id;
            private String planArea;
            private String planAreaMu;
            private String planCode;
            private String planFullname;
            private long planPickdateEnd;
            private long planPickdateStart;
            private long planPlantdateEnd;
            private long planPlantdateStart;
            private String planStep;
            private String planYield;
            private String status;
            private String sysCompanyCode;
            private String sysOrgCode;
            private String tenantId;
            private String tenantName;
            private String updateBy;
            private String updateName;

            public long getActualPickdateEnd() {
                return actualPickdateEnd;
            }

            public void setActualPickdateEnd(long actualPickdateEnd) {
                this.actualPickdateEnd = actualPickdateEnd;
            }

            public long getActualPickdateStart() {
                return actualPickdateStart;
            }

            public void setActualPickdateStart(long actualPickdateStart) {
                this.actualPickdateStart = actualPickdateStart;
            }

            public long getActualPlantdateEnd() {
                return actualPlantdateEnd;
            }

            public void setActualPlantdateEnd(long actualPlantdateEnd) {
                this.actualPlantdateEnd = actualPlantdateEnd;
            }

            public long getActualPlantdateStart() {
                return actualPlantdateStart;
            }

            public void setActualPlantdateStart(long actualPlantdateStart) {
                this.actualPlantdateStart = actualPlantdateStart;
            }

            public String getActualYield() {
                return actualYield;
            }

            public void setActualYield(String actualYield) {
                this.actualYield = actualYield;
            }

            public String getBaseFullname() {
                return baseFullname;
            }

            public void setBaseFullname(String baseFullname) {
                this.baseFullname = baseFullname;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public String getCreateName() {
                return createName;
            }

            public void setCreateName(String createName) {
                this.createName = createName;
            }

            public String getCropCategoryDefine() {
                return cropCategoryDefine;
            }

            public void setCropCategoryDefine(String cropCategoryDefine) {
                this.cropCategoryDefine = cropCategoryDefine;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getGbaseId() {
                return gbaseId;
            }

            public void setGbaseId(String gbaseId) {
                this.gbaseId = gbaseId;
            }

            public String getGhouseFullname() {
                return ghouseFullname;
            }

            public void setGhouseFullname(String ghouseFullname) {
                this.ghouseFullname = ghouseFullname;
            }

            public String getGhouseId() {
                return ghouseId;
            }

            public void setGhouseId(String ghouseId) {
                this.ghouseId = ghouseId;
            }

            public String getHeadBy() {
                return headBy;
            }

            public void setHeadBy(String headBy) {
                this.headBy = headBy;
            }

            public String getHeadName() {
                return headName;
            }

            public void setHeadName(String headName) {
                this.headName = headName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPlanArea() {
                return planArea;
            }

            public void setPlanArea(String planArea) {
                this.planArea = planArea;
            }

            public String getPlanAreaMu() {
                return planAreaMu;
            }

            public void setPlanAreaMu(String planAreaMu) {
                this.planAreaMu = planAreaMu;
            }

            public String getPlanCode() {
                return planCode;
            }

            public void setPlanCode(String planCode) {
                this.planCode = planCode;
            }

            public String getPlanFullname() {
                return planFullname;
            }

            public void setPlanFullname(String planFullname) {
                this.planFullname = planFullname;
            }

            public long getPlanPickdateEnd() {
                return planPickdateEnd;
            }

            public void setPlanPickdateEnd(long planPickdateEnd) {
                this.planPickdateEnd = planPickdateEnd;
            }

            public long getPlanPickdateStart() {
                return planPickdateStart;
            }

            public void setPlanPickdateStart(long planPickdateStart) {
                this.planPickdateStart = planPickdateStart;
            }

            public long getPlanPlantdateEnd() {
                return planPlantdateEnd;
            }

            public void setPlanPlantdateEnd(long planPlantdateEnd) {
                this.planPlantdateEnd = planPlantdateEnd;
            }

            public long getPlanPlantdateStart() {
                return planPlantdateStart;
            }

            public void setPlanPlantdateStart(long planPlantdateStart) {
                this.planPlantdateStart = planPlantdateStart;
            }

            public String getPlanStep() {
                return planStep;
            }

            public void setPlanStep(String planStep) {
                this.planStep = planStep;
            }

            public String getPlanYield() {
                return planYield;
            }

            public void setPlanYield(String planYield) {
                this.planYield = planYield;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getSysCompanyCode() {
                return sysCompanyCode;
            }

            public void setSysCompanyCode(String sysCompanyCode) {
                this.sysCompanyCode = sysCompanyCode;
            }

            public String getSysOrgCode() {
                return sysOrgCode;
            }

            public void setSysOrgCode(String sysOrgCode) {
                this.sysOrgCode = sysOrgCode;
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

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
            }

            public String getUpdateName() {
                return updateName;
            }

            public void setUpdateName(String updateName) {
                this.updateName = updateName;
            }
        }
    }
}
