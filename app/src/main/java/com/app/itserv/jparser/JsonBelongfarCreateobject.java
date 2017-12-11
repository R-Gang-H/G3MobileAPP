package com.app.itserv.jparser;

import java.util.List;

/**
 * 农事记录中根据执行任务的ID 获得对应的大棚和农事分类
 *
 * @author yiqiang
 */
public class JsonBelongfarCreateobject {
    /**
     * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
     * obj : {"plantingTaskOpelogList":[{"id":"bbeeb6885d7427a1015d794104440113","status":"ACTIVE","createBy":"cxm","createDate":1500977890000,"updateBy":null,"createName":"程晓梅","updateName":null,"updateDate":null,"tenantName":"北京平谷大棚基地","tenantId":"402883fd5c8fd7be015c8fde6fd90003","ghouseId":"bbeeb6885d7427a1015d790f5e10003d","baseId":"bbeeb6885d7427a1015d78ffd6f70032","planId":"bbeeb6885d7427a1015d79271ff90051","taskId":"bbeeb6885d7427a1015d794104350112","opeDesc":"程晓梅创建农事任务:西红柿播种","taskName":"西红柿播种","ghouseFullname":"北京昌平1号大棚","baseFullname":"北京大兴基地","planFullname":"西红柿种植","worktaskStatus":"CREATE"}],"plantingTask":{"id":"bbeeb6885d7427a1015d794104350112","content":"播种西红柿的种子","description":"","status":"ACTIVE","createBy":"cxm","createDate":1500977890000,"updateBy":"","createName":"程晓梅","updateName":"","updateDate":null,"tenantName":"北京平谷大棚基地","tenantId":"402883fd5c8fd7be015c8fde6fd90003","ghouseId":"bbeeb6885d7427a1015d790f5e10003d","baseId":"bbeeb6885d7427a1015d78ffd6f70032","planId":"bbeeb6885d7427a1015d79271ff90051","sysOrgCode":"A08","taskName":"西红柿播种","planDays":"2, 3, 4","ghouseFullname":"北京昌平1号大棚","attachment1":null,"baseFullname":"北京大兴基地","sysCompanyCode":"A08","planFullname":"西红柿种植","planTimeEnd":"10","farmingCategory":"seed","farmingCategoryDefine":"","worktaskCircle":"WEEKLY","assignStatus":"ALLOCATED","planDateEnd":1503504000000,"planDateStart":1499011200000,"planTimeStart":"5"},"framingRecordList":[{"id":"bbeeb6885d7427a1015d79539219012e","status":"ACTIVE","createBy":"cxm","createDate":1500979106000,"updateBy":"","createName":"程晓梅","updateName":"","updateDate":null,"tenantName":"北京平谷大棚基地","tenantId":"402883fd5c8fd7be015c8fde6fd90003","ghouseId":"bbeeb6885d7427a1015d790f5e10003d","baseId":"bbeeb6885d7427a1015d78ffd6f70032","assId":"bbeeb6885d7427a1015d794104540114","headBy":"402883fd5c8fd7be015c8fe0a84e000a","planId":"bbeeb6885d7427a1015d79271ff90051","headName":"程晓梅","taskName":"西红柿播种","hasProblem":"Y","taskid":"bbeeb6885d7427a1015d794104350112","workDesc":"[2017-07-11 12:00 ~ 2017-07-24 16:00],[cxm]在[北京昌平1号大棚]进行了[播种]工作","ghouseFullname":"北京大兴基地","attachment1":null,"baseFullname":"北京大兴基地","attachment2":null,"planFullname":"北京大兴基地","planTimeEnd":"16","farmingCategory":"seed","farmingCategoryDefine":null,"planDateEnd":1500825600000,"problemDesc":"发现一大问题，不告诉你","planDateStart":1499702400000,"planTimeStart":"12"}],"worktaskList":[{"id":"bbeeb6885d7427a1015d794104540114","description":"我是分配描述","status":"ACTIVE","createBy":"cxm","createDate":1500977890000,"updateBy":"cxm","createName":"程晓梅","updateName":"程晓梅","updateDate":1500978047000,"tenantName":"北京平谷大棚基地","tenantId":"402883fd5c8fd7be015c8fde6fd90003","ghouseId":"bbeeb6885d7427a1015d790f5e10003d","baseId":"bbeeb6885d7427a1015d78ffd6f70032","headBy":"402883fd5c8fd7be015c8fe0a84e000a","planId":"bbeeb6885d7427a1015d79271ff90051","headName":"cxm","sysOrgCode":"A08","taskId":"bbeeb6885d7427a1015d794104350112","taskName":"西红柿播种","ghouseFullname":"北京昌平1号大棚","baseFullname":"北京大兴基地","sysCompanyCode":"A08","planFullname":"西红柿种植","worktaskCloseDesc":"我是领用说明，我已领用","worktaskDoStatus":"IN_EXECUTION","worktaskCloseScore":null},{"id":"bbeeb6885d7427a1015d794104640116","description":"我是分配描述","status":"ACTIVE","createBy":"cxm","createDate":1500977890000,"updateBy":null,"createName":"程晓梅","updateName":null,"updateDate":null,"tenantName":"北京平谷大棚基地","tenantId":"402883fd5c8fd7be015c8fde6fd90003","ghouseId":"bbeeb6885d7427a1015d790f5e10003d","baseId":"bbeeb6885d7427a1015d78ffd6f70032","headBy":"bbeeb6885ca0433b015cb3dfa88400dd","planId":"bbeeb6885d7427a1015d79271ff90051","headName":"王五","sysOrgCode":"A08","taskId":"bbeeb6885d7427a1015d794104350112","taskName":"西红柿播种","ghouseFullname":"北京昌平1号大棚","baseFullname":"北京大兴基地","sysCompanyCode":"A08","planFullname":"西红柿种植","worktaskCloseDesc":null,"worktaskDoStatus":"ASSIGNED","worktaskCloseScore":null}]}
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
         * plantingTaskOpelogList : [{"id":"bbeeb6885d7427a1015d794104440113","status":"ACTIVE","createBy":"cxm","createDate":1500977890000,"updateBy":null,"createName":"程晓梅","updateName":null,"updateDate":null,"tenantName":"北京平谷大棚基地","tenantId":"402883fd5c8fd7be015c8fde6fd90003","ghouseId":"bbeeb6885d7427a1015d790f5e10003d","baseId":"bbeeb6885d7427a1015d78ffd6f70032","planId":"bbeeb6885d7427a1015d79271ff90051","taskId":"bbeeb6885d7427a1015d794104350112","opeDesc":"程晓梅创建农事任务:西红柿播种","taskName":"西红柿播种","ghouseFullname":"北京昌平1号大棚","baseFullname":"北京大兴基地","planFullname":"西红柿种植","worktaskStatus":"CREATE"}]
         * plantingTask : {"id":"bbeeb6885d7427a1015d794104350112","content":"播种西红柿的种子","description":"","status":"ACTIVE","createBy":"cxm","createDate":1500977890000,"updateBy":"","createName":"程晓梅","updateName":"","updateDate":null,"tenantName":"北京平谷大棚基地","tenantId":"402883fd5c8fd7be015c8fde6fd90003","ghouseId":"bbeeb6885d7427a1015d790f5e10003d","baseId":"bbeeb6885d7427a1015d78ffd6f70032","planId":"bbeeb6885d7427a1015d79271ff90051","sysOrgCode":"A08","taskName":"西红柿播种","planDays":"2, 3, 4","ghouseFullname":"北京昌平1号大棚","attachment1":null,"baseFullname":"北京大兴基地","sysCompanyCode":"A08","planFullname":"西红柿种植","planTimeEnd":"10","farmingCategory":"seed","farmingCategoryDefine":"","worktaskCircle":"WEEKLY","assignStatus":"ALLOCATED","planDateEnd":1503504000000,"planDateStart":1499011200000,"planTimeStart":"5"}
         * framingRecordList : [{"id":"bbeeb6885d7427a1015d79539219012e","status":"ACTIVE","createBy":"cxm","createDate":1500979106000,"updateBy":"","createName":"程晓梅","updateName":"","updateDate":null,"tenantName":"北京平谷大棚基地","tenantId":"402883fd5c8fd7be015c8fde6fd90003","ghouseId":"bbeeb6885d7427a1015d790f5e10003d","baseId":"bbeeb6885d7427a1015d78ffd6f70032","assId":"bbeeb6885d7427a1015d794104540114","headBy":"402883fd5c8fd7be015c8fe0a84e000a","planId":"bbeeb6885d7427a1015d79271ff90051","headName":"程晓梅","taskName":"西红柿播种","hasProblem":"Y","taskid":"bbeeb6885d7427a1015d794104350112","workDesc":"[2017-07-11 12:00 ~ 2017-07-24 16:00],[cxm]在[北京昌平1号大棚]进行了[播种]工作","ghouseFullname":"北京大兴基地","attachment1":null,"baseFullname":"北京大兴基地","attachment2":null,"planFullname":"北京大兴基地","planTimeEnd":"16","farmingCategory":"seed","farmingCategoryDefine":null,"planDateEnd":1500825600000,"problemDesc":"发现一大问题，不告诉你","planDateStart":1499702400000,"planTimeStart":"12"}]
         * worktaskList : [{"id":"bbeeb6885d7427a1015d794104540114","description":"我是分配描述","status":"ACTIVE","createBy":"cxm","createDate":1500977890000,"updateBy":"cxm","createName":"程晓梅","updateName":"程晓梅","updateDate":1500978047000,"tenantName":"北京平谷大棚基地","tenantId":"402883fd5c8fd7be015c8fde6fd90003","ghouseId":"bbeeb6885d7427a1015d790f5e10003d","baseId":"bbeeb6885d7427a1015d78ffd6f70032","headBy":"402883fd5c8fd7be015c8fe0a84e000a","planId":"bbeeb6885d7427a1015d79271ff90051","headName":"cxm","sysOrgCode":"A08","taskId":"bbeeb6885d7427a1015d794104350112","taskName":"西红柿播种","ghouseFullname":"北京昌平1号大棚","baseFullname":"北京大兴基地","sysCompanyCode":"A08","planFullname":"西红柿种植","worktaskCloseDesc":"我是领用说明，我已领用","worktaskDoStatus":"IN_EXECUTION","worktaskCloseScore":null},{"id":"bbeeb6885d7427a1015d794104640116","description":"我是分配描述","status":"ACTIVE","createBy":"cxm","createDate":1500977890000,"updateBy":null,"createName":"程晓梅","updateName":null,"updateDate":null,"tenantName":"北京平谷大棚基地","tenantId":"402883fd5c8fd7be015c8fde6fd90003","ghouseId":"bbeeb6885d7427a1015d790f5e10003d","baseId":"bbeeb6885d7427a1015d78ffd6f70032","headBy":"bbeeb6885ca0433b015cb3dfa88400dd","planId":"bbeeb6885d7427a1015d79271ff90051","headName":"王五","sysOrgCode":"A08","taskId":"bbeeb6885d7427a1015d794104350112","taskName":"西红柿播种","ghouseFullname":"北京昌平1号大棚","baseFullname":"北京大兴基地","sysCompanyCode":"A08","planFullname":"西红柿种植","worktaskCloseDesc":null,"worktaskDoStatus":"ASSIGNED","worktaskCloseScore":null}]
         */

        private PlantingTaskBean plantingTask;
        private List<PlantingTaskOpelogListBean> plantingTaskOpelogList;
        private List<FramingRecordListBean> framingRecordList;
        private List<WorktaskListBean> worktaskList;

        public PlantingTaskBean getPlantingTask() {
            return plantingTask;
        }

        public void setPlantingTask(PlantingTaskBean plantingTask) {
            this.plantingTask = plantingTask;
        }

        public List<PlantingTaskOpelogListBean> getPlantingTaskOpelogList() {
            return plantingTaskOpelogList;
        }

        public void setPlantingTaskOpelogList(List<PlantingTaskOpelogListBean> plantingTaskOpelogList) {
            this.plantingTaskOpelogList = plantingTaskOpelogList;
        }

        public List<FramingRecordListBean> getFramingRecordList() {
            return framingRecordList;
        }

        public void setFramingRecordList(List<FramingRecordListBean> framingRecordList) {
            this.framingRecordList = framingRecordList;
        }

        public List<WorktaskListBean> getWorktaskList() {
            return worktaskList;
        }

        public void setWorktaskList(List<WorktaskListBean> worktaskList) {
            this.worktaskList = worktaskList;
        }

        public static class PlantingTaskBean {
            /**
             * id : bbeeb6885d7427a1015d794104350112
             * content : 播种西红柿的种子
             * description :
             * status : ACTIVE
             * createBy : cxm
             * createDate : 1500977890000
             * updateBy :
             * createName : 程晓梅
             * updateName :
             * updateDate : null
             * tenantName : 北京平谷大棚基地
             * tenantId : 402883fd5c8fd7be015c8fde6fd90003
             * ghouseId : bbeeb6885d7427a1015d790f5e10003d
             * baseId : bbeeb6885d7427a1015d78ffd6f70032
             * planId : bbeeb6885d7427a1015d79271ff90051
             * sysOrgCode : A08
             * taskName : 西红柿播种
             * planDays : 2, 3, 4
             * ghouseFullname : 北京昌平1号大棚
             * attachment1 : null
             * baseFullname : 北京大兴基地
             * sysCompanyCode : A08
             * planFullname : 西红柿种植
             * planTimeEnd : 10
             * farmingCategory : seed
             * farmingCategoryDefine :
             * worktaskCircle : WEEKLY
             * assignStatus : ALLOCATED
             * planDateEnd : 1503504000000
             * planDateStart : 1499011200000
             * planTimeStart : 5
             */

            private String id;
            private String content;
            private String description;
            private String status;
            private String createBy;
            private long createDate;
            private String updateBy;
            private String createName;
            private String updateName;
            private Object updateDate;
            private String tenantName;
            private String tenantId;
            private String ghouseId;
            private String baseId;
            private String planId;
            private String sysOrgCode;
            private String taskName;
            private String planDays;
            private String ghouseFullname;
            private Object attachment1;
            private String baseFullname;
            private String sysCompanyCode;
            private String planFullname;
            private String planTimeEnd;
            private String farmingCategory;
            private String farmingCategoryDefine;
            private String worktaskCircle;
            private String assignStatus;
            private long planDateEnd;
            private long planDateStart;
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

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
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

            public String getPlanId() {
                return planId;
            }

            public void setPlanId(String planId) {
                this.planId = planId;
            }

            public String getSysOrgCode() {
                return sysOrgCode;
            }

            public void setSysOrgCode(String sysOrgCode) {
                this.sysOrgCode = sysOrgCode;
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

            public String getSysCompanyCode() {
                return sysCompanyCode;
            }

            public void setSysCompanyCode(String sysCompanyCode) {
                this.sysCompanyCode = sysCompanyCode;
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

            public String getFarmingCategoryDefine() {
                return farmingCategoryDefine;
            }

            public void setFarmingCategoryDefine(String farmingCategoryDefine) {
                this.farmingCategoryDefine = farmingCategoryDefine;
            }

            public String getWorktaskCircle() {
                return worktaskCircle;
            }

            public void setWorktaskCircle(String worktaskCircle) {
                this.worktaskCircle = worktaskCircle;
            }

            public String getAssignStatus() {
                return assignStatus;
            }

            public void setAssignStatus(String assignStatus) {
                this.assignStatus = assignStatus;
            }

            public long getPlanDateEnd() {
                return planDateEnd;
            }

            public void setPlanDateEnd(long planDateEnd) {
                this.planDateEnd = planDateEnd;
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

        public static class PlantingTaskOpelogListBean {
            /**
             * id : bbeeb6885d7427a1015d794104440113
             * status : ACTIVE
             * createBy : cxm
             * createDate : 1500977890000
             * updateBy : null
             * createName : 程晓梅
             * updateName : null
             * updateDate : null
             * tenantName : 北京平谷大棚基地
             * tenantId : 402883fd5c8fd7be015c8fde6fd90003
             * ghouseId : bbeeb6885d7427a1015d790f5e10003d
             * baseId : bbeeb6885d7427a1015d78ffd6f70032
             * planId : bbeeb6885d7427a1015d79271ff90051
             * taskId : bbeeb6885d7427a1015d794104350112
             * opeDesc : 程晓梅创建农事任务:西红柿播种
             * taskName : 西红柿播种
             * ghouseFullname : 北京昌平1号大棚
             * baseFullname : 北京大兴基地
             * planFullname : 西红柿种植
             * worktaskStatus : CREATE
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
            private String planId;
            private String taskId;
            private String opeDesc;
            private String taskName;
            private String ghouseFullname;
            private String baseFullname;
            private String planFullname;
            private String worktaskStatus;

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

            public String getPlanId() {
                return planId;
            }

            public void setPlanId(String planId) {
                this.planId = planId;
            }

            public String getTaskId() {
                return taskId;
            }

            public void setTaskId(String taskId) {
                this.taskId = taskId;
            }

            public String getOpeDesc() {
                return opeDesc;
            }

            public void setOpeDesc(String opeDesc) {
                this.opeDesc = opeDesc;
            }

            public String getTaskName() {
                return taskName;
            }

            public void setTaskName(String taskName) {
                this.taskName = taskName;
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

            public String getPlanFullname() {
                return planFullname;
            }

            public void setPlanFullname(String planFullname) {
                this.planFullname = planFullname;
            }

            public String getWorktaskStatus() {
                return worktaskStatus;
            }

            public void setWorktaskStatus(String worktaskStatus) {
                this.worktaskStatus = worktaskStatus;
            }
        }

        public static class FramingRecordListBean {
            /**
             * id : bbeeb6885d7427a1015d79539219012e
             * status : ACTIVE
             * createBy : cxm
             * createDate : 1500979106000
             * updateBy :
             * createName : 程晓梅
             * updateName :
             * updateDate : null
             * tenantName : 北京平谷大棚基地
             * tenantId : 402883fd5c8fd7be015c8fde6fd90003
             * ghouseId : bbeeb6885d7427a1015d790f5e10003d
             * baseId : bbeeb6885d7427a1015d78ffd6f70032
             * assId : bbeeb6885d7427a1015d794104540114
             * headBy : 402883fd5c8fd7be015c8fe0a84e000a
             * planId : bbeeb6885d7427a1015d79271ff90051
             * headName : 程晓梅
             * taskName : 西红柿播种
             * hasProblem : Y
             * taskid : bbeeb6885d7427a1015d794104350112
             * workDesc : [2017-07-11 12:00 ~ 2017-07-24 16:00],[cxm]在[北京昌平1号大棚]进行了[播种]工作
             * ghouseFullname : 北京大兴基地
             * attachment1 : null
             * baseFullname : 北京大兴基地
             * attachment2 : null
             * planFullname : 北京大兴基地
             * planTimeEnd : 16
             * farmingCategory : seed
             * farmingCategoryDefine : null
             * planDateEnd : 1500825600000
             * problemDesc : 发现一大问题，不告诉你
             * planDateStart : 1499702400000
             * planTimeStart : 12
             */

            private String id;
            private String status;
            private String createBy;
            private long createDate;
            private String updateBy;
            private String createName;
            private String updateName;
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

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
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

        public static class WorktaskListBean {
            /**
             * id : bbeeb6885d7427a1015d794104540114
             * description : 我是分配描述
             * status : ACTIVE
             * createBy : cxm
             * createDate : 1500977890000
             * updateBy : cxm
             * createName : 程晓梅
             * updateName : 程晓梅
             * updateDate : 1500978047000
             * tenantName : 北京平谷大棚基地
             * tenantId : 402883fd5c8fd7be015c8fde6fd90003
             * ghouseId : bbeeb6885d7427a1015d790f5e10003d
             * baseId : bbeeb6885d7427a1015d78ffd6f70032
             * headBy : 402883fd5c8fd7be015c8fe0a84e000a
             * planId : bbeeb6885d7427a1015d79271ff90051
             * headName : cxm
             * sysOrgCode : A08
             * taskId : bbeeb6885d7427a1015d794104350112
             * taskName : 西红柿播种
             * ghouseFullname : 北京昌平1号大棚
             * baseFullname : 北京大兴基地
             * sysCompanyCode : A08
             * planFullname : 西红柿种植
             * worktaskCloseDesc : 我是领用说明，我已领用
             * worktaskDoStatus : IN_EXECUTION
             * worktaskCloseScore : null
             */

            private String id;
            private String description;
            private String status;
            private String createBy;
            private long createDate;
            private String updateBy;
            private String createName;
            private String updateName;
            private long updateDate;
            private String tenantName;
            private String tenantId;
            private String ghouseId;
            private String baseId;
            private String headBy;
            private String planId;
            private String headName;
            private String sysOrgCode;
            private String taskId;
            private String taskName;
            private String ghouseFullname;
            private String baseFullname;
            private String sysCompanyCode;
            private String planFullname;
            private String worktaskCloseDesc;
            private String worktaskDoStatus;
            private Object worktaskCloseScore;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
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

            public String getBaseId() {
                return baseId;
            }

            public void setBaseId(String baseId) {
                this.baseId = baseId;
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

            public String getSysOrgCode() {
                return sysOrgCode;
            }

            public void setSysOrgCode(String sysOrgCode) {
                this.sysOrgCode = sysOrgCode;
            }

            public String getTaskId() {
                return taskId;
            }

            public void setTaskId(String taskId) {
                this.taskId = taskId;
            }

            public String getTaskName() {
                return taskName;
            }

            public void setTaskName(String taskName) {
                this.taskName = taskName;
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

            public String getSysCompanyCode() {
                return sysCompanyCode;
            }

            public void setSysCompanyCode(String sysCompanyCode) {
                this.sysCompanyCode = sysCompanyCode;
            }

            public String getPlanFullname() {
                return planFullname;
            }

            public void setPlanFullname(String planFullname) {
                this.planFullname = planFullname;
            }

            public String getWorktaskCloseDesc() {
                return worktaskCloseDesc;
            }

            public void setWorktaskCloseDesc(String worktaskCloseDesc) {
                this.worktaskCloseDesc = worktaskCloseDesc;
            }

            public String getWorktaskDoStatus() {
                return worktaskDoStatus;
            }

            public void setWorktaskDoStatus(String worktaskDoStatus) {
                this.worktaskDoStatus = worktaskDoStatus;
            }

            public Object getWorktaskCloseScore() {
                return worktaskCloseScore;
            }

            public void setWorktaskCloseScore(Object worktaskCloseScore) {
                this.worktaskCloseScore = worktaskCloseScore;
            }
        }
    }
}
