package com.app.itserv.jparser;

import java.util.List;

/**
 * attributes : {"yyPlantingTaskList":1,"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * obj : [{"id":"bbeeb6885d1b6246015d1cedbec4000b","content":"11","description":"11","status":"ACTIVE","createDate":1499428929000,"updateBy":"scott","updateDate":1499839783000,"createBy":"scott","createName":"张代浩","updateName":"张代浩","tenantName":"山东云洋","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","ghouseId":"40289fda5c6b4f80015c6d3f4cc80003 ","baseId":"40289f8a5c75bae3015c76f8e3e10003 ","sysOrgCode":"A06","planId":"4028832f5cd4552b015cd45750e30002 ","taskName":"日志跟踪","planDays":null,"baseFullname":"山东云洋基地","attachment1":null,"ghouseFullname":"寿光联通展示大棚","planFullname":"茄子计划","sysCompanyCode":"A06","planDateStart":1499097600000,"planDateEnd":1499270400000,"planTimeEnd":"4","farmingCategoryDefine":null,"farmingCategory":"seed","planTimeStart":"3","assignStatus":"ASSIGN","worktaskCircle":"ONCE"}]
 * msg : OK
 * success : true
 */

/**
 * 我的农事任务列表json解析类
 *
 * @author haoruigang
 * @Package com.app.itserv.jparser
 * @project yyShed
 * @ClassName: JsonMyFarTaskObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-21 上午9:37:42
 */
public class JsonMyFarTaskObject {

    private AttributesBean attributes;
    private String msg;
    private boolean success;
    private List<ObjBean> obj;

    public static class AttributesBean {
        /**
         * yyPlantingTaskList : 1 currUserId : 402883fd5c8fd7be015c8fe0a84e000a
         * currTenantId : 402883fd5c8fd7be015c8fde6fd90003
         */

        private int yyPlantingTaskList;
        private String currUserId;
        private String currTenantId;

        public int getYyPlantingTaskList() {
            return yyPlantingTaskList;
        }

        public void setYyPlantingTaskList(int yyPlantingTaskList) {
            this.yyPlantingTaskList = yyPlantingTaskList;
        }

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

    public static class ObjBean {
        /**
         * id : bbeeb6885d1b6246015d1cedbec4000b content : 11 description : 11
         * status : ACTIVE createDate : 1499428929000 updateBy : scott
         * updateDate : 1499839783000 createBy : scott createName : 张代浩
         * updateName : 张代浩 tenantName : 山东云洋 tenantId :
         * bbeeb6885c3f560c015c44bee4bf0006 ghouseId :
         * 40289fda5c6b4f80015c6d3f4cc80003 baseId :
         * 40289f8a5c75bae3015c76f8e3e10003 sysOrgCode : A06 planId :
         * 4028832f5cd4552b015cd45750e30002 taskName : 日志跟踪 planDays : null
         * baseFullname : 山东云洋基地 attachment1 : null ghouseFullname : 寿光联通展示大棚
         * planFullname : 茄子计划 sysCompanyCode : A06 planDateStart :
         * 1499097600000 planDateEnd : 1499270400000 planTimeEnd : 4
         * farmingCategoryDefine : null farmingCategory : seed planTimeStart : 3
         * assignStatus : ASSIGN worktaskCircle : ONCE
         */

        private String id;
        private String content;
        private String description;
        private String status;
        private long createDate;
        private String updateBy;
        private long updateDate;
        private String createBy;
        private String createName;
        private String updateName;
        private String tenantName;
        private String tenantId;
        private String ghouseId;
        private String baseId;
        private String sysOrgCode;
        private String planId;
        private String taskName;
        private Object planDays;
        private String baseFullname;
        private Object attachment1;
        private String ghouseFullname;
        private String planFullname;
        private String sysCompanyCode;
        private long planDateStart;
        private long planDateEnd;
        private String planTimeEnd;
        private Object farmingCategoryDefine;
        private String farmingCategory;
        private String planTimeStart;
        private String assignStatus;
        private String worktaskCircle;

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

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
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

        public String getUpdateName() {
            return updateName;
        }

        public void setUpdateName(String updateName) {
            this.updateName = updateName;
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

        public String getBaseId() {
            return baseId;
        }

        public void setBaseId(String baseId) {
            this.baseId = baseId;
        }

        public String getSysOrgCode() {
            return sysOrgCode;
        }

        public void setSysOrgCode(String sysOrgCode) {
            this.sysOrgCode = sysOrgCode;
        }

        public String getPlanId() {
            return planId;
        }

        public void setPlanId(String planId) {
            this.planId = planId;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public Object getPlanDays() {
            return planDays;
        }

        public void setPlanDays(Object planDays) {
            this.planDays = planDays;
        }

        public String getBaseFullname() {
            return baseFullname;
        }

        public void setBaseFullname(String baseFullname) {
            this.baseFullname = baseFullname;
        }

        public Object getAttachment1() {
            return attachment1;
        }

        public void setAttachment1(Object attachment1) {
            this.attachment1 = attachment1;
        }

        public String getGhouseFullname() {
            return ghouseFullname;
        }

        public void setGhouseFullname(String ghouseFullname) {
            this.ghouseFullname = ghouseFullname;
        }

        public String getPlanFullname() {
            return planFullname;
        }

        public void setPlanFullname(String planFullname) {
            this.planFullname = planFullname;
        }

        public String getSysCompanyCode() {
            return sysCompanyCode;
        }

        public void setSysCompanyCode(String sysCompanyCode) {
            this.sysCompanyCode = sysCompanyCode;
        }

        public long getPlanDateStart() {
            return planDateStart;
        }

        public void setPlanDateStart(long planDateStart) {
            this.planDateStart = planDateStart;
        }

        public long getPlanDateEnd() {
            return planDateEnd;
        }

        public void setPlanDateEnd(long planDateEnd) {
            this.planDateEnd = planDateEnd;
        }

        public String getPlanTimeEnd() {
            return planTimeEnd;
        }

        public void setPlanTimeEnd(String planTimeEnd) {
            this.planTimeEnd = planTimeEnd;
        }

        public Object getFarmingCategoryDefine() {
            return farmingCategoryDefine;
        }

        public void setFarmingCategoryDefine(Object farmingCategoryDefine) {
            this.farmingCategoryDefine = farmingCategoryDefine;
        }

        public String getFarmingCategory() {
            return farmingCategory;
        }

        public void setFarmingCategory(String farmingCategory) {
            this.farmingCategory = farmingCategory;
        }

        public String getPlanTimeStart() {
            return planTimeStart;
        }

        public void setPlanTimeStart(String planTimeStart) {
            this.planTimeStart = planTimeStart;
        }

        public String getAssignStatus() {
            return assignStatus;
        }

        public void setAssignStatus(String assignStatus) {
            this.assignStatus = assignStatus;
        }

        public String getWorktaskCircle() {
            return worktaskCircle;
        }

        public void setWorktaskCircle(String worktaskCircle) {
            this.worktaskCircle = worktaskCircle;
        }
    }
}
