package com.app.itserv.jparser;


/**
 * 农事种植计划详情json报文解析类
 *
 * @author changyiqiang
 * @Package com.app.itserv.jparser
 * @project yyshed
 * @ClassName: JsonPlanSchemeParticularsObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-13 上午11:35:24
 */


public class JsonPlanSchemeParticularsObject {
    /**
     * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
     * obj : {"id":"4028832f5cd4552b015cd45750e30002","content":"播种，灌溉","description":"茄子超大个的","status":"CANCELED","updateDate":1499767733000,"updateName":"张代浩","createBy":"scott","createName":"张代浩","createDate":1497596292000,"updateBy":"scott","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","tenantName":"山东云洋","gbaseId":"40289f8a5c75bae3015c76f8e3e10003 ","ghouseId":"bbeeb6885ca042eb015cc4cddd180013 ","sysOrgCode":"A06","headBy":"40289f8a5c7afe40015c7d8d8b1a0021","planStep":"GROWING","planYield":"5464","planCode":"茄子300","planArea":"4353","headName":"张三","planAreaMu":null,"attachment1":null,"baseFullname":"山东云洋基地","ghouseFullname":"北京昌平区大棚","sysCompanyCode":"A06","actualPlantdateStart":1496678400000,"planPlantdateStart":1496592000000,"planFullname":"茄子计划","planPlantdateEnd":1497196800000,"planPickdateStart":1500912000000,"planPickdateEnd":1501171200000,"actualYield":"563","actualPlantdateEnd":1496678400000,"actualPickdateStart":1497283200000,"cropCategoryDefine":"茄子","cropCategory":"garden_stuff","actualPickdateEnd":1497888000000}
     * msg : OK
     * success : true
     */

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
         * id : 4028832f5cd4552b015cd45750e30002
         * content : 播种，灌溉
         * description : 茄子超大个的
         * status : CANCELED
         * updateDate : 1499767733000
         * updateName : 张代浩
         * createBy : scott
         * createName : 张代浩
         * createDate : 1497596292000
         * updateBy : scott
         * tenantId : bbeeb6885c3f560c015c44bee4bf0006
         * tenantName : 山东云洋
         * gbaseId : 40289f8a5c75bae3015c76f8e3e10003
         * ghouseId : bbeeb6885ca042eb015cc4cddd180013
         * sysOrgCode : A06
         * headBy : 40289f8a5c7afe40015c7d8d8b1a0021
         * planStep : GROWING
         * planYield : 5464
         * planCode : 茄子300
         * planArea : 4353
         * headName : 张三
         * planAreaMu : null
         * attachment1 : null
         * baseFullname : 山东云洋基地
         * ghouseFullname : 北京昌平区大棚
         * sysCompanyCode : A06
         * actualPlantdateStart : 1496678400000
         * planPlantdateStart : 1496592000000
         * planFullname : 茄子计划
         * planPlantdateEnd : 1497196800000
         * planPickdateStart : 1500912000000
         * planPickdateEnd : 1501171200000
         * actualYield : 563
         * actualPlantdateEnd : 1496678400000
         * actualPickdateStart : 1497283200000
         * cropCategoryDefine : 茄子
         * cropCategory : garden_stuff
         * actualPickdateEnd : 1497888000000
         */

        private String id;
        private String content;
        private String description;
        private String status;
        private long updateDate;
        private String updateName;
        private String createBy;
        private String createName;
        private long createDate;
        private String updateBy;
        private String tenantId;
        private String tenantName;
        private String gbaseId;
        private String ghouseId;
        private String sysOrgCode;
        private String headBy;
        private String planStep;
        private String planYield;
        private String planCode;
        private String planArea;
        private String headName;
        private Object planAreaMu;
        private Object attachment1;
        private String baseFullname;
        private String ghouseFullname;
        private String sysCompanyCode;
        private long actualPlantdateStart;
        private long planPlantdateStart;
        private String planFullname;
        private long planPlantdateEnd;
        private long planPickdateStart;
        private long planPickdateEnd;
        private String actualYield;
        private long actualPlantdateEnd;
        private long actualPickdateStart;
        private String cropCategoryDefine;
        private String cropCategory;
        private long actualPickdateEnd;

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

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public String getUpdateName() {
            return updateName;
        }

        public void setUpdateName(String updateName) {
            this.updateName = updateName;
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

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
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

        public String getGbaseId() {
            return gbaseId;
        }

        public void setGbaseId(String gbaseId) {
            this.gbaseId = gbaseId;
        }

        public String getGhouseId() {
            return ghouseId;
        }

        public void setGhouseId(String ghouseId) {
            this.ghouseId = ghouseId;
        }

        public String getSysOrgCode() {
            return sysOrgCode;
        }

        public void setSysOrgCode(String sysOrgCode) {
            this.sysOrgCode = sysOrgCode;
        }

        public String getHeadBy() {
            return headBy;
        }

        public void setHeadBy(String headBy) {
            this.headBy = headBy;
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

        public String getPlanCode() {
            return planCode;
        }

        public void setPlanCode(String planCode) {
            this.planCode = planCode;
        }

        public String getPlanArea() {
            return planArea;
        }

        public void setPlanArea(String planArea) {
            this.planArea = planArea;
        }

        public String getHeadName() {
            return headName;
        }

        public void setHeadName(String headName) {
            this.headName = headName;
        }

        public Object getPlanAreaMu() {
            return planAreaMu;
        }

        public void setPlanAreaMu(Object planAreaMu) {
            this.planAreaMu = planAreaMu;
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

        public String getGhouseFullname() {
            return ghouseFullname;
        }

        public void setGhouseFullname(String ghouseFullname) {
            this.ghouseFullname = ghouseFullname;
        }

        public String getSysCompanyCode() {
            return sysCompanyCode;
        }

        public void setSysCompanyCode(String sysCompanyCode) {
            this.sysCompanyCode = sysCompanyCode;
        }

        public long getActualPlantdateStart() {
            return actualPlantdateStart;
        }

        public void setActualPlantdateStart(long actualPlantdateStart) {
            this.actualPlantdateStart = actualPlantdateStart;
        }

        public long getPlanPlantdateStart() {
            return planPlantdateStart;
        }

        public void setPlanPlantdateStart(long planPlantdateStart) {
            this.planPlantdateStart = planPlantdateStart;
        }

        public String getPlanFullname() {
            return planFullname;
        }

        public void setPlanFullname(String planFullname) {
            this.planFullname = planFullname;
        }

        public long getPlanPlantdateEnd() {
            return planPlantdateEnd;
        }

        public void setPlanPlantdateEnd(long planPlantdateEnd) {
            this.planPlantdateEnd = planPlantdateEnd;
        }

        public long getPlanPickdateStart() {
            return planPickdateStart;
        }

        public void setPlanPickdateStart(long planPickdateStart) {
            this.planPickdateStart = planPickdateStart;
        }

        public long getPlanPickdateEnd() {
            return planPickdateEnd;
        }

        public void setPlanPickdateEnd(long planPickdateEnd) {
            this.planPickdateEnd = planPickdateEnd;
        }

        public String getActualYield() {
            return actualYield;
        }

        public void setActualYield(String actualYield) {
            this.actualYield = actualYield;
        }

        public long getActualPlantdateEnd() {
            return actualPlantdateEnd;
        }

        public void setActualPlantdateEnd(long actualPlantdateEnd) {
            this.actualPlantdateEnd = actualPlantdateEnd;
        }

        public long getActualPickdateStart() {
            return actualPickdateStart;
        }

        public void setActualPickdateStart(long actualPickdateStart) {
            this.actualPickdateStart = actualPickdateStart;
        }

        public String getCropCategoryDefine() {
            return cropCategoryDefine;
        }

        public void setCropCategoryDefine(String cropCategoryDefine) {
            this.cropCategoryDefine = cropCategoryDefine;
        }

        public String getCropCategory() {
            return cropCategory;
        }

        public void setCropCategory(String cropCategory) {
            this.cropCategory = cropCategory;
        }

        public long getActualPickdateEnd() {
            return actualPickdateEnd;
        }

        public void setActualPickdateEnd(long actualPickdateEnd) {
            this.actualPickdateEnd = actualPickdateEnd;
        }
    }

}
