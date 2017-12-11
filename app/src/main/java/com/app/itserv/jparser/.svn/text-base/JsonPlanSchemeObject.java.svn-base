package com.app.itserv.jparser;

import java.util.List;

/**
 * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","yyPlantingplanList":4,"currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * obj : [{"id":"4028832f5cd4552b015cd45750e30002","content":"播种，灌溉","description":"茄子超大个的","status":"CANCELED","baseFullname":"山东云洋基地","createName":"张代浩","updateBy":"scott","createBy":"scott","createDate":1497596292000,"updateName":"张代浩","updateDate":1497596322000,"tenantName":"山东云洋","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","ghouseId":"40289f8a5cae3871015cae8c2a330034 ","gbaseId":"40289f8a5c75bae3015c76f8e3e10003 ","headBy":"40289f8a5c7afe40015c7d8d8b1a0021","sysOrgCode":"A06","headName":"张三","planArea":"4353","planYield":"5464","planStep":"GROWING","planCode":"茄子300","planAreaMu":null,"ghouseFullname":"寿光联通展示大棚","attachment1":null,"sysCompanyCode":"A06","planPickdateStart":1497196800000,"actualPlantdateStart":1496678400000,"actualPickdateEnd":1497888000000,"planPlantdateStart":1496592000000,"actualYield":"563","actualPlantdateEnd":1496678400000,"planFullname":"茄子计划","actualPickdateStart":1497283200000,"cropCategory":"garden_stuff","planPlantdateEnd":1497196800000,"cropCategoryDefine":"茄子","planPickdateEnd":1497283200000},{"id":"4028832f5cd923fa015cd93b4303000f","content":"西红柿计划早种早上市","description":"说的对","status":"ACTIVE","baseFullname":"山东寿光联通合作农业示范基地","createName":"张代浩","updateBy":"","createBy":"scott","createDate":1498293158000,"updateName":"","updateDate":null,"tenantName":"山东云洋","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","ghouseId":"40289fda5c6b4f80015c6d3f4cc80003 ","gbaseId":"40289f8a5ca91cd5015caa9fb2360044 ","headBy":"40289f8a5c7afe40015c7d8d8b1a0021","sysOrgCode":"A06","headName":"张三","planArea":"34534","planYield":"545345","planStep":"SEEDING","planCode":"3333","planAreaMu":null,"ghouseFullname":"寿光联通展示大棚","attachment1":null,"sysCompanyCode":"A06","planPickdateStart":1496678400000,"actualPlantdateStart":null,"actualPickdateEnd":null,"planPlantdateStart":1496678400000,"actualYield":null,"actualPlantdateEnd":null,"planFullname":"西红柿计划","actualPickdateStart":null,"cropCategory":"garden_stuff","planPlantdateEnd":1497283200000,"cropCategoryDefine":"西红柿","planPickdateEnd":1497283200000},{"id":"40289f9a5ca45a7b015ca48392fa0003","content":"播种施肥灌溉大药，每日采摘辣椒","description":"本计划第一次实施","status":"ACTIVE","baseFullname":"山东云洋基地","createName":"张代浩","updateBy":"","createBy":"scott","createDate":1497408705000,"updateName":"","updateDate":null,"tenantName":"山东云洋","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","ghouseId":"40289fda5c6b4f80015c6d3f4cc80003 ","gbaseId":"40289f8a5c75bae3015c76f8e3e10003 ","headBy":"40289f8a5c7afe40015c7d8d8b1a0021","sysOrgCode":"A06","headName":"张三","planArea":"50","planYield":"300","planStep":"PREPARING","planCode":"1","planAreaMu":null,"ghouseFullname":"寿光联通展示大棚","attachment1":null,"sysCompanyCode":"A06","planPickdateStart":1496764800000,"actualPlantdateStart":null,"actualPickdateEnd":null,"planPlantdateStart":1496592000000,"actualYield":null,"actualPlantdateEnd":null,"planFullname":"辣椒计划","actualPickdateStart":null,"cropCategory":"garden_stuff","planPlantdateEnd":1496678400000,"cropCategoryDefine":"辣椒370","planPickdateEnd":1496851200000},{"id":"40289f9a5ca45a7b015ca485c3050005","content":"玫瑰种植，无土栽培","description":"玫瑰贵","status":"CANCELED","baseFullname":"衡水邓庄基地","createName":"张代浩","updateBy":"scott","createBy":"scott","createDate":1497408848000,"updateName":"张代浩","updateDate":1497512061000,"tenantName":"山东云洋","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","ghouseId":"40289fda5c6b4f80015c6d3f4cc80003 ","gbaseId":"40289fda5c44b5c7015c44c074980007 ","headBy":"40289f8a5c7afe40015c7d8d8b1a0021","sysOrgCode":"A06","headName":"张三","planArea":"320","planYield":"400","planStep":"SEEDING","planCode":"2","planAreaMu":null,"ghouseFullname":"寿光联通展示大棚","attachment1":null,"sysCompanyCode":"A06","planPickdateStart":1496332800000,"actualPlantdateStart":null,"actualPickdateEnd":null,"planPlantdateStart":1499443200000,"actualYield":"","actualPlantdateEnd":null,"planFullname":"花卉展览计划","actualPickdateStart":null,"cropCategory":"flowers","planPlantdateEnd":1496246400000,"cropCategoryDefine":"玫瑰230","planPickdateEnd":1496332800000}]
 * msg : OK
 * success : true
 */

/**
 * 农事种植计划json报文解析类
 *
 * @author haoruigang
 * @Package com.app.itserv.jparser
 * @project yyshed
 * @ClassName: JsonPlanSchemeObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-5 上午11:35:24
 */
public class JsonPlanSchemeObject {

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
         * currUserId : 402883fd5c8fd7be015c8fe0a84e000a yyPlantingplanList : 4
         * currTenantId : 402883fd5c8fd7be015c8fde6fd90003
         */

        private String currUserId;
        private int yyPlantingplanList;
        private String currTenantId;

        public String getCurrUserId() {
            return currUserId;
        }

        public void setCurrUserId(String currUserId) {
            this.currUserId = currUserId;
        }

        public int getYyPlantingplanList() {
            return yyPlantingplanList;
        }

        public void setYyPlantingplanList(int yyPlantingplanList) {
            this.yyPlantingplanList = yyPlantingplanList;
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
         * id : 4028832f5cd4552b015cd45750e30002 content : 播种，灌溉 description :
         * 茄子超大个的 status : CANCELED baseFullname : 山东云洋基地 createName : 张代浩
         * updateBy : scott createBy : scott createDate : 1497596292000
         * updateName : 张代浩 updateDate : 1497596322000 tenantName : 山东云洋
         * tenantId : bbeeb6885c3f560c015c44bee4bf0006 ghouseId :
         * 40289f8a5cae3871015cae8c2a330034 gbaseId :
         * 40289f8a5c75bae3015c76f8e3e10003 headBy :
         * 40289f8a5c7afe40015c7d8d8b1a0021 sysOrgCode : A06 headName : 张三
         * planArea : 4353 planYield : 5464 planStep : GROWING planCode : 茄子300
         * planAreaMu : null ghouseFullname : 寿光联通展示大棚 attachment1 : null
         * sysCompanyCode : A06 planPickdateStart : 1497196800000
         * actualPlantdateStart : 1496678400000 actualPickdateEnd :
         * 1497888000000 planPlantdateStart : 1496592000000 actualYield : 563
         * actualPlantdateEnd : 1496678400000 planFullname : 茄子计划
         * actualPickdateStart : 1497283200000 cropCategory : garden_stuff
         * planPlantdateEnd : 1497196800000 cropCategoryDefine : 茄子
         * planPickdateEnd : 1497283200000
         */

        private String id;
        private String content;
        private String description;
        private String status;
        private String baseFullname;
        private String createName;
        private String updateBy;
        private String createBy;
        private long createDate;
        private String updateName;
        private long updateDate;
        private String tenantName;
        private String tenantId;
        private String ghouseId;
        private String gbaseId;
        private String headBy;
        private String sysOrgCode;
        private String headName;
        private String planArea;
        private String planYield;
        private String planStep;
        private String planCode;
        private Object planAreaMu;
        private String ghouseFullname;
        private Object attachment1;
        private String sysCompanyCode;
        private long planPickdateStart;
        private long actualPlantdateStart;
        private long actualPickdateEnd;
        private long planPlantdateStart;
        private String actualYield;
        private long actualPlantdateEnd;
        private String planFullname;
        private long actualPickdateStart;
        private String cropCategory;
        private long planPlantdateEnd;
        private String cropCategoryDefine;
        private long planPickdateEnd;

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

        public String getBaseFullname() {
            return baseFullname;
        }

        public void setBaseFullname(String baseFullname) {
            this.baseFullname = baseFullname;
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

        public String getUpdateName() {
            return updateName;
        }

        public void setUpdateName(String updateName) {
            this.updateName = updateName;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
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

        public String getGhouseId() {
            return ghouseId;
        }

        public void setGhouseId(String ghouseId) {
            this.ghouseId = ghouseId;
        }

        public String getGbaseId() {
            return gbaseId;
        }

        public void setGbaseId(String gbaseId) {
            this.gbaseId = gbaseId;
        }

        public String getHeadBy() {
            return headBy;
        }

        public void setHeadBy(String headBy) {
            this.headBy = headBy;
        }

        public String getSysOrgCode() {
            return sysOrgCode;
        }

        public void setSysOrgCode(String sysOrgCode) {
            this.sysOrgCode = sysOrgCode;
        }

        public String getHeadName() {
            return headName;
        }

        public void setHeadName(String headName) {
            this.headName = headName;
        }

        public String getPlanArea() {
            return planArea;
        }

        public void setPlanArea(String planArea) {
            this.planArea = planArea;
        }

        public String getPlanYield() {
            return planYield;
        }

        public void setPlanYield(String planYield) {
            this.planYield = planYield;
        }

        public String getPlanStep() {
            return planStep;
        }

        public void setPlanStep(String planStep) {
            this.planStep = planStep;
        }

        public String getPlanCode() {
            return planCode;
        }

        public void setPlanCode(String planCode) {
            this.planCode = planCode;
        }

        public Object getPlanAreaMu() {
            return planAreaMu;
        }

        public void setPlanAreaMu(Object planAreaMu) {
            this.planAreaMu = planAreaMu;
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

        public String getSysCompanyCode() {
            return sysCompanyCode;
        }

        public void setSysCompanyCode(String sysCompanyCode) {
            this.sysCompanyCode = sysCompanyCode;
        }

        public long getPlanPickdateStart() {
            return planPickdateStart;
        }

        public void setPlanPickdateStart(long planPickdateStart) {
            this.planPickdateStart = planPickdateStart;
        }

        public long getActualPlantdateStart() {
            return actualPlantdateStart;
        }

        public void setActualPlantdateStart(long actualPlantdateStart) {
            this.actualPlantdateStart = actualPlantdateStart;
        }

        public long getActualPickdateEnd() {
            return actualPickdateEnd;
        }

        public void setActualPickdateEnd(long actualPickdateEnd) {
            this.actualPickdateEnd = actualPickdateEnd;
        }

        public long getPlanPlantdateStart() {
            return planPlantdateStart;
        }

        public void setPlanPlantdateStart(long planPlantdateStart) {
            this.planPlantdateStart = planPlantdateStart;
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

        public String getPlanFullname() {
            return planFullname;
        }

        public void setPlanFullname(String planFullname) {
            this.planFullname = planFullname;
        }

        public long getActualPickdateStart() {
            return actualPickdateStart;
        }

        public void setActualPickdateStart(long actualPickdateStart) {
            this.actualPickdateStart = actualPickdateStart;
        }

        public String getCropCategory() {
            return cropCategory;
        }

        public void setCropCategory(String cropCategory) {
            this.cropCategory = cropCategory;
        }

        public long getPlanPlantdateEnd() {
            return planPlantdateEnd;
        }

        public void setPlanPlantdateEnd(long planPlantdateEnd) {
            this.planPlantdateEnd = planPlantdateEnd;
        }

        public String getCropCategoryDefine() {
            return cropCategoryDefine;
        }

        public void setCropCategoryDefine(String cropCategoryDefine) {
            this.cropCategoryDefine = cropCategoryDefine;
        }

        public long getPlanPickdateEnd() {
            return planPickdateEnd;
        }

        public void setPlanPickdateEnd(long planPickdateEnd) {
            this.planPickdateEnd = planPickdateEnd;
        }
    }
}
