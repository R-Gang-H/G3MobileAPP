package com.app.itserv.jparser;

/**
 * 农事填报成功 后返回的Json
 *
 * @author yiqiang
 */
public class JsonFarmingRecordCreateObject {

    /**
     * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
     * obj : {"id":"bbeeb6885d81a7bc015d884446280019","status":"ACTIVE","createBy":"cxm","createDate":1501229762088,"updateBy":null,"createName":"程晓梅","updateName":null,"updateDate":null,"tenantName":"北京云洋【不能删】","tenantId":"0","ghouseId":"bbeeb6885d721994015d725709510010","baseId":"bbeeb6885d721994015d7255092d000d","assId":"bbeeb6885d721994015d7265857c0071","headBy":"402883fd5c8fd7be015c8fe0a84e000a","planId":"bbeeb6885d721994015d725fe3990019","headName":"程晓梅","taskName":"平谷玉米灌溉 4","hasProblem":"Y","taskid":"bbeeb6885d721994015d7264aa5f005d","workDesc":"11","ghouseFullname":"北京平谷基地","attachment1":null,"baseFullname":"北京平谷基地","attachment2":null,"planFullname":"北京平谷基地","planTimeEnd":"9","farmingCategory":"irrigate","farmingCategoryDefine":null,"planDateEnd":1500307200000,"problemDesc":"11","planDateStart":1500307200000,"planTimeStart":"8"}
     * msg : 添加成功
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
         * id : bbeeb6885d81a7bc015d884446280019
         * status : ACTIVE
         * createBy : cxm
         * createDate : 1501229762088
         * updateBy : null
         * createName : 程晓梅
         * updateName : null
         * updateDate : null
         * tenantName : 北京云洋【不能删】
         * tenantId : 0
         * ghouseId : bbeeb6885d721994015d725709510010
         * baseId : bbeeb6885d721994015d7255092d000d
         * assId : bbeeb6885d721994015d7265857c0071
         * headBy : 402883fd5c8fd7be015c8fe0a84e000a
         * planId : bbeeb6885d721994015d725fe3990019
         * headName : 程晓梅
         * taskName : 平谷玉米灌溉 4
         * hasProblem : Y
         * taskid : bbeeb6885d721994015d7264aa5f005d
         * workDesc : 11
         * ghouseFullname : 北京平谷基地
         * attachment1 : null
         * baseFullname : 北京平谷基地
         * attachment2 : null
         * planFullname : 北京平谷基地
         * planTimeEnd : 9
         * farmingCategory : irrigate
         * farmingCategoryDefine : null
         * planDateEnd : 1500307200000
         * problemDesc : 11
         * planDateStart : 1500307200000
         * planTimeStart : 8
         */

        private String id;
        private String status;
        private String createBy;
        private long createDate;
        private Object updateBy;
        private String createName;
        private Object updateName;
        private Object updateDate;
        private String tenantName;
        private String tenantId;
        private String ghouseId;
        private String baseId;
        private String assId;
        private String headBy;
        private String planId;
        private String headName;
        private String taskName;
        private String hasProblem;
        private String taskid;
        private String workDesc;
        private String ghouseFullname;
        private Object attachment1;
        private String baseFullname;
        private Object attachment2;
        private String planFullname;
        private String planTimeEnd;
        private String farmingCategory;
        private Object farmingCategoryDefine;
        private long planDateEnd;
        private String problemDesc;
        private long planDateStart;
        private String planTimeStart;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getAssId() {
            return assId;
        }

        public void setAssId(String assId) {
            this.assId = assId;
        }

        public String getHeadBy() {
            return headBy;
        }

        public void setHeadBy(String headBy) {
            this.headBy = headBy;
        }

        public String getPlanId() {
            return planId;
        }

        public void setPlanId(String planId) {
            this.planId = planId;
        }

        public String getHeadName() {
            return headName;
        }

        public void setHeadName(String headName) {
            this.headName = headName;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getHasProblem() {
            return hasProblem;
        }

        public void setHasProblem(String hasProblem) {
            this.hasProblem = hasProblem;
        }

        public String getTaskid() {
            return taskid;
        }

        public void setTaskid(String taskid) {
            this.taskid = taskid;
        }

        public String getWorkDesc() {
            return workDesc;
        }

        public void setWorkDesc(String workDesc) {
            this.workDesc = workDesc;
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

        public String getBaseFullname() {
            return baseFullname;
        }

        public void setBaseFullname(String baseFullname) {
            this.baseFullname = baseFullname;
        }

        public Object getAttachment2() {
            return attachment2;
        }

        public void setAttachment2(Object attachment2) {
            this.attachment2 = attachment2;
        }

        public String getPlanFullname() {
            return planFullname;
        }

        public void setPlanFullname(String planFullname) {
            this.planFullname = planFullname;
        }

        public String getPlanTimeEnd() {
            return planTimeEnd;
        }

        public void setPlanTimeEnd(String planTimeEnd) {
            this.planTimeEnd = planTimeEnd;
        }

        public String getFarmingCategory() {
            return farmingCategory;
        }

        public void setFarmingCategory(String farmingCategory) {
            this.farmingCategory = farmingCategory;
        }

        public Object getFarmingCategoryDefine() {
            return farmingCategoryDefine;
        }

        public void setFarmingCategoryDefine(Object farmingCategoryDefine) {
            this.farmingCategoryDefine = farmingCategoryDefine;
        }

        public long getPlanDateEnd() {
            return planDateEnd;
        }

        public void setPlanDateEnd(long planDateEnd) {
            this.planDateEnd = planDateEnd;
        }

        public String getProblemDesc() {
            return problemDesc;
        }

        public void setProblemDesc(String problemDesc) {
            this.problemDesc = problemDesc;
        }

        public long getPlanDateStart() {
            return planDateStart;
        }

        public void setPlanDateStart(long planDateStart) {
            this.planDateStart = planDateStart;
        }

        public String getPlanTimeStart() {
            return planTimeStart;
        }

        public void setPlanTimeStart(String planTimeStart) {
            this.planTimeStart = planTimeStart;
        }
    }

}
