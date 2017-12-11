package com.app.itserv.jparser;

import java.util.List;

public class JsonFarmingRecordObject {
    /**
     * attributes : {"framingRecordTaskListSize":4,"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
     * obj : [{"id":"bbeeb6885d721994015d7267a2500082","status":"ACTIVE","createDate":1500862980000,"updateDate":null,"updateName":"","createBy":"cxm","createName":"程晓梅","updateBy":"","tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d721994015d725709510010","baseId":"bbeeb6885d721994015d7255092d000d","planId":"bbeeb6885d721994015d725fe3990019","taskName":"平谷玉米灌溉 4","headBy":"402883fd5c8fd7be015c8fe0a84e000a","taskid":"bbeeb6885d721994015d7264aa5f005d","headName":"cxm","workDesc":"[2017-07-01 7:00 ~ 2017-07-01 8:00],[cxm]在[北京平谷大棚]进行了[灌溉]工作","hasProblem":"N","assId":"bbeeb6885d721994015d7265855d006f","planFullname":"北京平谷玉米种植","farmingCategory":"irrigate","problemDesc":"","planDateEnd":1498838400000,"farmingCategoryDefine":null,"planDateStart":1498838400000,"planTimeStart":"7","planTimeEnd":"8","baseFullname":"北京平谷基地","attachment1":null,"ghouseFullname":"北京平谷大棚","attachment2":null},{"id":"bbeeb6885d721994015d7267d6800084","status":"ACTIVE","createDate":1500862994000,"updateDate":null,"updateName":"","createBy":"cxm","createName":"程晓梅","updateBy":"","tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d721994015d725709510010","baseId":"bbeeb6885d721994015d7255092d000d","planId":"bbeeb6885d721994015d725fe3990019","taskName":"平谷玉米灌溉 4","headBy":"402883fd5c8fd7be015c8fe0a84e000a","taskid":"bbeeb6885d721994015d7264aa5f005d","headName":"cxm","workDesc":"[2017-07-02 8:00 ~ 2017-07-02 9:00],[cxm]在[北京平谷大棚]进行了[灌溉]工作","hasProblem":"N","assId":"bbeeb6885d721994015d7265855d006f","planFullname":"北京平谷玉米种植","farmingCategory":"irrigate","problemDesc":"","planDateEnd":1498924800000,"farmingCategoryDefine":null,"planDateStart":1498924800000,"planTimeStart":"8","planTimeEnd":"9","baseFullname":"北京平谷基地","attachment1":null,"ghouseFullname":"北京平谷大棚","attachment2":null},{"id":"bbeeb6885d721994015d726884b10086","status":"ACTIVE","createDate":1500863038000,"updateDate":null,"updateName":"","createBy":"cxm","createName":"程晓梅","updateBy":"","tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d721994015d725709510010","baseId":"bbeeb6885d721994015d7255092d000d","planId":"bbeeb6885d721994015d725fe3990019","taskName":"平谷玉米灌溉 4","headBy":"402883fd5c8fd7be015c8fe0a84e000a","taskid":"bbeeb6885d721994015d7264aa5f005d","headName":"程晓梅","workDesc":"[2017-07-03 7:00 ~ 2017-07-03 8:00],[cxm]在[北京平谷大棚]进行了[灌溉]工作","hasProblem":"Y","assId":"bbeeb6885d721994015d7265855d006f","planFullname":"北京平谷基地","farmingCategory":"irrigate","problemDesc":"水阀关闭不紧","planDateEnd":1499011200000,"farmingCategoryDefine":null,"planDateStart":1499011200000,"planTimeStart":"7","planTimeEnd":"8","baseFullname":"北京平谷基地","attachment1":null,"ghouseFullname":"北京平谷基地","attachment2":null},{"id":"bbeeb6885d721994015d727f2c080090","status":"ACTIVE","createDate":1500864523000,"updateDate":null,"updateName":"","createBy":"cxm","createName":"程晓梅","updateBy":"","tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d721994015d725709510010","baseId":"bbeeb6885d721994015d7255092d000d","planId":"bbeeb6885d721994015d725fe3990019","taskName":"平谷玉米灌溉 4","headBy":"402883fd5c8fd7be015c8fe0a84e000a","taskid":"bbeeb6885d721994015d7264aa5f005d","headName":"程晓梅","workDesc":"我是文字描述","hasProblem":"Y","assId":"bbeeb6885d721994015d7265855d006f","planFullname":"北京平谷基地","farmingCategory":"irrigate","problemDesc":"我是问题描述","planDateEnd":1501084800000,"farmingCategoryDefine":null,"planDateStart":1499702400000,"planTimeStart":"14","planTimeEnd":"15","baseFullname":"北京平谷基地","attachment1":null,"ghouseFullname":"北京平谷基地","attachment2":null}]
     * msg : OK
     * success : true
     */

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
         * framingRecordTaskListSize : 4
         * currUserId : 402883fd5c8fd7be015c8fe0a84e000a
         * currTenantId : 402883fd5c8fd7be015c8fde6fd90003
         */

        private int framingRecordTaskListSize;
        private String currUserId;
        private String currTenantId;

        public int getFramingRecordTaskListSize() {
            return framingRecordTaskListSize;
        }

        public void setFramingRecordTaskListSize(int framingRecordTaskListSize) {
            this.framingRecordTaskListSize = framingRecordTaskListSize;
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

    public static class ObjBean {
        /**
         * id : bbeeb6885d721994015d7267a2500082
         * status : ACTIVE
         * createDate : 1500862980000
         * updateDate : null
         * updateName :
         * createBy : cxm
         * createName : 程晓梅
         * updateBy :
         * tenantId : 402883fd5c8fd7be015c8fde6fd90003
         * tenantName : 北京平谷大棚基地
         * ghouseId : bbeeb6885d721994015d725709510010
         * baseId : bbeeb6885d721994015d7255092d000d
         * planId : bbeeb6885d721994015d725fe3990019
         * taskName : 平谷玉米灌溉 4
         * headBy : 402883fd5c8fd7be015c8fe0a84e000a
         * taskid : bbeeb6885d721994015d7264aa5f005d
         * headName : cxm
         * workDesc : [2017-07-01 7:00 ~ 2017-07-01 8:00],[cxm]在[北京平谷大棚]进行了[灌溉]工作
         * hasProblem : N
         * assId : bbeeb6885d721994015d7265855d006f
         * planFullname : 北京平谷玉米种植
         * farmingCategory : irrigate
         * problemDesc :
         * planDateEnd : 1498838400000
         * farmingCategoryDefine : null
         * planDateStart : 1498838400000
         * planTimeStart : 7
         * planTimeEnd : 8
         * baseFullname : 北京平谷基地
         * attachment1 : null
         * ghouseFullname : 北京平谷大棚
         * attachment2 : null
         */

        private String id;
        private String status;
        private long createDate;
        private Object updateDate;
        private String updateName;
        private String createBy;
        private String createName;
        private String updateBy;
        private String tenantId;
        private String tenantName;
        private String ghouseId;
        private String baseId;
        private String planId;
        private String taskName;
        private String headBy;
        private String taskid;
        private String headName;
        private String workDesc;
        private String hasProblem;
        private String assId;
        private String planFullname;
        private String farmingCategory;
        private String problemDesc;
        private long planDateEnd;
        private Object farmingCategoryDefine;
        private long planDateStart;
        private String planTimeStart;
        private String planTimeEnd;
        private String baseFullname;
        private Object attachment1;
        private String ghouseFullname;
        private Object attachment2;


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

        public String getHeadBy() {
            return headBy;
        }

        public void setHeadBy(String headBy) {
            this.headBy = headBy;
        }

        public String getTaskid() {
            return taskid;
        }

        public void setTaskid(String taskid) {
            this.taskid = taskid;
        }

        public String getHeadName() {
            return headName;
        }

        public void setHeadName(String headName) {
            this.headName = headName;
        }

        public String getWorkDesc() {
            return workDesc;
        }

        public void setWorkDesc(String workDesc) {
            this.workDesc = workDesc;
        }

        public String getHasProblem() {
            return hasProblem;
        }

        public void setHasProblem(String hasProblem) {
            this.hasProblem = hasProblem;
        }

        public String getAssId() {
            return assId;
        }

        public void setAssId(String assId) {
            this.assId = assId;
        }

        public String getPlanFullname() {
            return planFullname;
        }

        public void setPlanFullname(String planFullname) {
            this.planFullname = planFullname;
        }

        public String getFarmingCategory() {
            return farmingCategory;
        }

        public void setFarmingCategory(String farmingCategory) {
            this.farmingCategory = farmingCategory;
        }

        public String getProblemDesc() {
            return problemDesc;
        }

        public void setProblemDesc(String problemDesc) {
            this.problemDesc = problemDesc;
        }

        public long getPlanDateEnd() {
            return planDateEnd;
        }

        public void setPlanDateEnd(long planDateEnd) {
            this.planDateEnd = planDateEnd;
        }

        public Object getFarmingCategoryDefine() {
            return farmingCategoryDefine;
        }

        public void setFarmingCategoryDefine(Object farmingCategoryDefine) {
            this.farmingCategoryDefine = farmingCategoryDefine;
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

        public String getPlanTimeEnd() {
            return planTimeEnd;
        }

        public void setPlanTimeEnd(String planTimeEnd) {
            this.planTimeEnd = planTimeEnd;
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

        public Object getAttachment2() {
            return attachment2;
        }

        public void setAttachment2(Object attachment2) {
            this.attachment2 = attachment2;
        }
    }

}
