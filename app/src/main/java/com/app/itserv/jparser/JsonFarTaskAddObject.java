package com.app.itserv.jparser;

/**
 * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * msg : 添加成功
 * success : true
 * obj : {"id":"402883fd5d548dd0015d548e52550000","content":"11","description":null,"status":"ACTIVE","sysOrgCode":null,"planId":"402883ff5d0b8e44015d0b9385270002","taskName":"111","planDays":"","updateName":null,"updateBy":null,"createName":"程晓梅","createBy":"cxm","createDate":1500362199633,"updateDate":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885c8c679c015c902cc336000a","baseId":"402883ff5d0b8e44015d0b9385270002","attachment1":"","ghouseFullname":"dapeng2hao","baseFullname":"北京昌平区实验基地","sysCompanyCode":null,"planFullname":"种植计划测试","assignStatus":"UNDISTRIBUTED","planTimeEnd":"9","farmingCategory":null,"worktaskCircle":"DAILY","planDateStart":1500307200000,"planDateEnd":1500307200000,"farmingCategoryDefine":"air","planTimeStart":"8"}
 */

/**
 * 农事任务新增json解析类
 *
 * @author haoruigang
 * @Package com.app.itserv.jparser
 * @project yyShed
 * @ClassName: JsonFarTaskAddObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-22 下午4:00:05
 */

public class JsonFarTaskAddObject {

    private AttributesBean attributes;
    private String msg;
    private boolean success;
    private ObjBean obj;

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

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
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
         * id : 402883fd5d548dd0015d548e52550000 content : 11 description : null
         * status : ACTIVE sysOrgCode : null planId :
         * 402883ff5d0b8e44015d0b9385270002 taskName : 111 planDays : updateName
         * : null updateBy : null createName : 程晓梅 createBy : cxm createDate :
         * 1500362199633 updateDate : null tenantId :
         * 402883fd5c8fd7be015c8fde6fd90003 tenantName : 北京平谷大棚基地 ghouseId :
         * bbeeb6885c8c679c015c902cc336000a baseId :
         * 402883ff5d0b8e44015d0b9385270002 attachment1 : ghouseFullname :
         * dapeng2hao baseFullname : 北京昌平区实验基地 sysCompanyCode : null
         * planFullname : 种植计划测试 assignStatus : UNDISTRIBUTED planTimeEnd : 9
         * farmingCategory : null worktaskCircle : DAILY planDateStart :
         * 1500307200000 planDateEnd : 1500307200000 farmingCategoryDefine : air
         * planTimeStart : 8
         */

        private String id;
        private String content;
        private Object description;
        private String status;
        private Object sysOrgCode;
        private String planId;
        private String taskName;
        private String planDays;
        private Object updateName;
        private Object updateBy;
        private String createName;
        private String createBy;
        private long createDate;
        private Object updateDate;
        private String tenantId;
        private String tenantName;
        private String ghouseId;
        private String baseId;
        private String attachment1;
        private String ghouseFullname;
        private String baseFullname;
        private Object sysCompanyCode;
        private String planFullname;
        private String assignStatus;
        private String planTimeEnd;
        private Object farmingCategory;
        private String worktaskCircle;
        private long planDateStart;
        private long planDateEnd;
        private String farmingCategoryDefine;
        private String planTimeStart;

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

        public Object getSysOrgCode() {
            return sysOrgCode;
        }

        public void setSysOrgCode(Object sysOrgCode) {
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

        public String getPlanDays() {
            return planDays;
        }

        public void setPlanDays(String planDays) {
            this.planDays = planDays;
        }

        public Object getUpdateName() {
            return updateName;
        }

        public void setUpdateName(Object updateName) {
            this.updateName = updateName;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
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

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
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

        public String getAttachment1() {
            return attachment1;
        }

        public void setAttachment1(String attachment1) {
            this.attachment1 = attachment1;
        }

        public String getGhouseFullname() {
            return ghouseFullname;
        }

        public void setGhouseFullname(String ghouseFullname) {
            this.ghouseFullname = ghouseFullname;
        }

        public String getBaseFullname() {
            return baseFullname;
        }

        public void setBaseFullname(String baseFullname) {
            this.baseFullname = baseFullname;
        }

        public Object getSysCompanyCode() {
            return sysCompanyCode;
        }

        public void setSysCompanyCode(Object sysCompanyCode) {
            this.sysCompanyCode = sysCompanyCode;
        }

        public String getPlanFullname() {
            return planFullname;
        }

        public void setPlanFullname(String planFullname) {
            this.planFullname = planFullname;
        }

        public String getAssignStatus() {
            return assignStatus;
        }

        public void setAssignStatus(String assignStatus) {
            this.assignStatus = assignStatus;
        }

        public String getPlanTimeEnd() {
            return planTimeEnd;
        }

        public void setPlanTimeEnd(String planTimeEnd) {
            this.planTimeEnd = planTimeEnd;
        }

        public Object getFarmingCategory() {
            return farmingCategory;
        }

        public void setFarmingCategory(Object farmingCategory) {
            this.farmingCategory = farmingCategory;
        }

        public String getWorktaskCircle() {
            return worktaskCircle;
        }

        public void setWorktaskCircle(String worktaskCircle) {
            this.worktaskCircle = worktaskCircle;
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

        public String getFarmingCategoryDefine() {
            return farmingCategoryDefine;
        }

        public void setFarmingCategoryDefine(String farmingCategoryDefine) {
            this.farmingCategoryDefine = farmingCategoryDefine;
        }

        public String getPlanTimeStart() {
            return planTimeStart;
        }

        public void setPlanTimeStart(String planTimeStart) {
            this.planTimeStart = planTimeStart;
        }
    }
}
