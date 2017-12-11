package com.app.itserv.jparser;

import java.io.Serializable;
import java.util.List;

/**
 * 每日任务json 报文解析类
 *
 * @author haoruigang
 * @Package com.app.itserv.jparser
 * @project Workspace
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017/9/5 16:19
 */
public class JsonTodayTaskDetailObject {


    /**
     * attributes : null
     * obj : {"yyWorktask":{"id":"bbeeb6885e59fede015e5a03fdc90006","description":"分配 yg1 ","status":"ACTIVE","createDate":1504748764000,"createBy":"cxm","updateBy":null,"createName":"程晓梅","updateDate":null,"updateName":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚","ghouseId":"402883fd5dbbcfcd015dbbf293db0021","baseId":"402883fd5dbbcfcd015dbbe618e60018","sysOrgCode":"A08","taskName":"桃子种植计划","headName":"yg1","planId":"402883fd5dbbcfcd015dbc1782630069","headBy":"402883fd5dbb8a9c015dbbc69aa40093","taskId":"bbeeb6885e59fede015e5a03c1f90002","baseFullname":"平谷大棚一号基地","ghouseFullname":"桃子种植基地","sysCompanyCode":"A08","worktaskCloseScore":null,"worktaskCloseDesc":null,"worktaskDoStatus":"ASSIGNED","planFullname":"重阳红种植"},"yyPlantingTaskOpelogList":[{"id":"bbeeb6885e59fede015e5a03fde80007","status":"ACTIVE","createDate":1504748764000,"createBy":"cxm","updateBy":null,"createName":"程晓梅","updateDate":null,"updateName":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚","ghouseId":"402883fd5dbbcfcd015dbbf293db0021","baseId":"402883fd5dbbcfcd015dbbe618e60018","taskName":"桃子种植计划","headName":"yg1","assId":"bbeeb6885e59fede015e5a03fdc90006","planId":"402883fd5dbbcfcd015dbc1782630069","headBy":"402883fd5dbb8a9c015dbbc69aa40093","taskId":"bbeeb6885e59fede015e5a03c1f90002","opeDesc":"程晓梅创建农事任务:桃子种植计划给yg1","baseFullname":"平谷大棚一号基地","ghouseFullname":"桃子种植基地","planFullname":"重阳红种植","worktaskStatus":"ASSIGN"}],"YyPlantingTask":{"id":"bbeeb6885e59fede015e5a03c1f90002","content":"桃子种植计划 每日灌溉 ","description":"","status":"ACTIVE","createDate":1504748749000,"createBy":"cxm","updateBy":"cxm","createName":"程晓梅","updateDate":1504748764000,"updateName":"程晓梅","tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚","ghouseId":"402883fd5dbbcfcd015dbbf293db0021","baseId":"402883fd5dbbcfcd015dbbe618e60018","sysOrgCode":"A08","taskName":"桃子种植计划","planId":"402883fd5dbbcfcd015dbc1782630069","planDays":null,"farmingCategory":"irrigate","planTimeStart":"8","farmingCategoryDefine":"","planDateStart":1504195200000,"planDateEnd":1506700800000,"assignStatus":"ALLOCATED","worktaskCircle":"DAILY","planTimeEnd":"9","baseFullname":"平谷大棚一号基地","attachment1":null,"ghouseFullname":"桃子种植基地","sysCompanyCode":"A08","planFullname":"重阳红种植"},"framingRecordList":[{"id":"bbeeb6885e59fede015e5a0745620075","status":"ACTIVE","createDate":1504748979000,"createBy":"yg1","updateBy":"","createName":"员工1","updateDate":null,"updateName":"","tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚","ghouseId":"402883fd5dbbcfcd015dbbf293db0021","baseId":"402883fd5dbbcfcd015dbbe618e60018","taskName":"桃子种植计划","headName":"yg1","assId":"bbeeb6885e59fede015e5a03fdc90006","planId":"402883fd5dbbcfcd015dbc1782630069","headBy":"402883fd5dbb8a9c015dbbc69aa40093","hasProblem":"N","workDesc":"[2017-09-07 6:00 ~ 2017-09-07 8:00],[yg1]在[桃子种植基地]进行了[灌溉]工作","taskid":"bbeeb6885e59fede015e5a03c1f90002","farmingCategory":"irrigate","planTimeStart":"6","farmingCategoryDefine":null,"planDateStart":1504713600000,"planDateEnd":1504713600000,"problemDesc":"","planTimeEnd":"8","baseFullname":"平谷大棚一号基地","attachment1":null,"ghouseFullname":"桃子种植基地","attachment2":null,"planFullname":"重阳红种植","taskdayId":"bbeeb6885e59fede015e5a03fe56000e"},{"id":"bbeeb6885e59fede015e5a077e830077","status":"ACTIVE","createDate":1504748994000,"createBy":"yg1","updateBy":"","createName":"员工1","updateDate":null,"updateName":"","tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚","ghouseId":"402883fd5dbbcfcd015dbbf293db0021","baseId":"402883fd5dbbcfcd015dbbe618e60018","taskName":"桃子种植计划","headName":"yg1","assId":"bbeeb6885e59fede015e5a03fdc90006","planId":"402883fd5dbbcfcd015dbc1782630069","headBy":"402883fd5dbb8a9c015dbbc69aa40093","hasProblem":"N","workDesc":"[2017-09-07 17:00 ~ 2017-09-07 18:00],[yg1]在[桃子种植基地]进行了[灌溉]工作","taskid":"bbeeb6885e59fede015e5a03c1f90002","farmingCategory":"irrigate","planTimeStart":"17","farmingCategoryDefine":null,"planDateStart":1504713600000,"planDateEnd":1504713600000,"problemDesc":"","planTimeEnd":"18","baseFullname":"平谷大棚一号基地","attachment1":null,"ghouseFullname":"桃子种植基地","attachment2":null,"planFullname":"重阳红种植","taskdayId":"bbeeb6885e59fede015e5a03fe56000e"}],"yyWorktaskDay":{"id":"bbeeb6885e59fede015e5a03fe56000e","description":"分配 yg1 ","status":"ACTIVE","createDate":1504748764000,"createBy":"cxm","updateBy":"yg1","createName":"程晓梅","updateDate":1504752979000,"updateName":"员工1","tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚","ghouseId":"402883fd5dbbcfcd015dbbf293db0021","baseId":"402883fd5dbbcfcd015dbbe618e60018","taskName":"桃子种植计划","headName":"yg1","assId":"bbeeb6885e59fede015e5a03fdc90006","planId":"402883fd5dbbcfcd015dbc1782630069","headBy":"402883fd5dbb8a9c015dbbc69aa40093","taskId":"bbeeb6885e59fede015e5a03c1f90002","worktaskDate":1504713600000,"baseFullname":"平谷大棚一号基地","ghouseFullname":"桃子种植基地","worktaskCloseScore":null,"worktaskCloseDesc":"gg","worktaskDoStatus":"IN_EXECUTION","planFullname":"重阳红种植"}}
     * msg : OK
     * success : true
     */

    private Object attributes;
    private ObjBean obj;
    private String msg;
    private boolean success;

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
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

    public static class ObjBean {
        /**
         * yyWorktask : {"id":"bbeeb6885e59fede015e5a03fdc90006","description":"分配 yg1 ","status":"ACTIVE","createDate":1504748764000,"createBy":"cxm","updateBy":null,"createName":"程晓梅","updateDate":null,"updateName":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚","ghouseId":"402883fd5dbbcfcd015dbbf293db0021","baseId":"402883fd5dbbcfcd015dbbe618e60018","sysOrgCode":"A08","taskName":"桃子种植计划","headName":"yg1","planId":"402883fd5dbbcfcd015dbc1782630069","headBy":"402883fd5dbb8a9c015dbbc69aa40093","taskId":"bbeeb6885e59fede015e5a03c1f90002","baseFullname":"平谷大棚一号基地","ghouseFullname":"桃子种植基地","sysCompanyCode":"A08","worktaskCloseScore":null,"worktaskCloseDesc":null,"worktaskDoStatus":"ASSIGNED","planFullname":"重阳红种植"}
         * yyPlantingTaskOpelogList : [{"id":"bbeeb6885e59fede015e5a03fde80007","status":"ACTIVE","createDate":1504748764000,"createBy":"cxm","updateBy":null,"createName":"程晓梅","updateDate":null,"updateName":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚","ghouseId":"402883fd5dbbcfcd015dbbf293db0021","baseId":"402883fd5dbbcfcd015dbbe618e60018","taskName":"桃子种植计划","headName":"yg1","assId":"bbeeb6885e59fede015e5a03fdc90006","planId":"402883fd5dbbcfcd015dbc1782630069","headBy":"402883fd5dbb8a9c015dbbc69aa40093","taskId":"bbeeb6885e59fede015e5a03c1f90002","opeDesc":"程晓梅创建农事任务:桃子种植计划给yg1","baseFullname":"平谷大棚一号基地","ghouseFullname":"桃子种植基地","planFullname":"重阳红种植","worktaskStatus":"ASSIGN"}]
         * YyPlantingTask : {"id":"bbeeb6885e59fede015e5a03c1f90002","content":"桃子种植计划 每日灌溉 ","description":"","status":"ACTIVE","createDate":1504748749000,"createBy":"cxm","updateBy":"cxm","createName":"程晓梅","updateDate":1504748764000,"updateName":"程晓梅","tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚","ghouseId":"402883fd5dbbcfcd015dbbf293db0021","baseId":"402883fd5dbbcfcd015dbbe618e60018","sysOrgCode":"A08","taskName":"桃子种植计划","planId":"402883fd5dbbcfcd015dbc1782630069","planDays":null,"farmingCategory":"irrigate","planTimeStart":"8","farmingCategoryDefine":"","planDateStart":1504195200000,"planDateEnd":1506700800000,"assignStatus":"ALLOCATED","worktaskCircle":"DAILY","planTimeEnd":"9","baseFullname":"平谷大棚一号基地","attachment1":null,"ghouseFullname":"桃子种植基地","sysCompanyCode":"A08","planFullname":"重阳红种植"}
         * framingRecordList : [{"id":"bbeeb6885e59fede015e5a0745620075","status":"ACTIVE","createDate":1504748979000,"createBy":"yg1","updateBy":"","createName":"员工1","updateDate":null,"updateName":"","tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚","ghouseId":"402883fd5dbbcfcd015dbbf293db0021","baseId":"402883fd5dbbcfcd015dbbe618e60018","taskName":"桃子种植计划","headName":"yg1","assId":"bbeeb6885e59fede015e5a03fdc90006","planId":"402883fd5dbbcfcd015dbc1782630069","headBy":"402883fd5dbb8a9c015dbbc69aa40093","hasProblem":"N","workDesc":"[2017-09-07 6:00 ~ 2017-09-07 8:00],[yg1]在[桃子种植基地]进行了[灌溉]工作","taskid":"bbeeb6885e59fede015e5a03c1f90002","farmingCategory":"irrigate","planTimeStart":"6","farmingCategoryDefine":null,"planDateStart":1504713600000,"planDateEnd":1504713600000,"problemDesc":"","planTimeEnd":"8","baseFullname":"平谷大棚一号基地","attachment1":null,"ghouseFullname":"桃子种植基地","attachment2":null,"planFullname":"重阳红种植","taskdayId":"bbeeb6885e59fede015e5a03fe56000e"},{"id":"bbeeb6885e59fede015e5a077e830077","status":"ACTIVE","createDate":1504748994000,"createBy":"yg1","updateBy":"","createName":"员工1","updateDate":null,"updateName":"","tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚","ghouseId":"402883fd5dbbcfcd015dbbf293db0021","baseId":"402883fd5dbbcfcd015dbbe618e60018","taskName":"桃子种植计划","headName":"yg1","assId":"bbeeb6885e59fede015e5a03fdc90006","planId":"402883fd5dbbcfcd015dbc1782630069","headBy":"402883fd5dbb8a9c015dbbc69aa40093","hasProblem":"N","workDesc":"[2017-09-07 17:00 ~ 2017-09-07 18:00],[yg1]在[桃子种植基地]进行了[灌溉]工作","taskid":"bbeeb6885e59fede015e5a03c1f90002","farmingCategory":"irrigate","planTimeStart":"17","farmingCategoryDefine":null,"planDateStart":1504713600000,"planDateEnd":1504713600000,"problemDesc":"","planTimeEnd":"18","baseFullname":"平谷大棚一号基地","attachment1":null,"ghouseFullname":"桃子种植基地","attachment2":null,"planFullname":"重阳红种植","taskdayId":"bbeeb6885e59fede015e5a03fe56000e"}]
         * yyWorktaskDay : {"id":"bbeeb6885e59fede015e5a03fe56000e","description":"分配 yg1 ","status":"ACTIVE","createDate":1504748764000,"createBy":"cxm","updateBy":"yg1","createName":"程晓梅","updateDate":1504752979000,"updateName":"员工1","tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚","ghouseId":"402883fd5dbbcfcd015dbbf293db0021","baseId":"402883fd5dbbcfcd015dbbe618e60018","taskName":"桃子种植计划","headName":"yg1","assId":"bbeeb6885e59fede015e5a03fdc90006","planId":"402883fd5dbbcfcd015dbc1782630069","headBy":"402883fd5dbb8a9c015dbbc69aa40093","taskId":"bbeeb6885e59fede015e5a03c1f90002","worktaskDate":1504713600000,"baseFullname":"平谷大棚一号基地","ghouseFullname":"桃子种植基地","worktaskCloseScore":null,"worktaskCloseDesc":"gg","worktaskDoStatus":"IN_EXECUTION","planFullname":"重阳红种植"}
         */

        private YyWorktaskBean yyWorktask;
        private YyPlantingTaskBean YyPlantingTask;
        private YyWorktaskDayBean yyWorktaskDay;
        private List<YyPlantingTaskOpelogListBean> yyPlantingTaskOpelogList;
        private List<FramingRecordListBean> framingRecordList;

        public YyWorktaskBean getYyWorktask() {
            return yyWorktask;
        }

        public void setYyWorktask(YyWorktaskBean yyWorktask) {
            this.yyWorktask = yyWorktask;
        }

        public YyPlantingTaskBean getYyPlantingTask() {
            return YyPlantingTask;
        }

        public void setYyPlantingTask(YyPlantingTaskBean YyPlantingTask) {
            this.YyPlantingTask = YyPlantingTask;
        }

        public YyWorktaskDayBean getYyWorktaskDay() {
            return yyWorktaskDay;
        }

        public void setYyWorktaskDay(YyWorktaskDayBean yyWorktaskDay) {
            this.yyWorktaskDay = yyWorktaskDay;
        }

        public List<YyPlantingTaskOpelogListBean> getYyPlantingTaskOpelogList() {
            return yyPlantingTaskOpelogList;
        }

        public void setYyPlantingTaskOpelogList(List<YyPlantingTaskOpelogListBean> yyPlantingTaskOpelogList) {
            this.yyPlantingTaskOpelogList = yyPlantingTaskOpelogList;
        }

        public List<FramingRecordListBean> getFramingRecordList() {
            return framingRecordList;
        }

        public void setFramingRecordList(List<FramingRecordListBean> framingRecordList) {
            this.framingRecordList = framingRecordList;
        }

        public static class YyWorktaskBean {
            /**
             * id : bbeeb6885e59fede015e5a03fdc90006
             * description : 分配 yg1
             * status : ACTIVE
             * createDate : 1504748764000
             * createBy : cxm
             * updateBy : null
             * createName : 程晓梅
             * updateDate : null
             * updateName : null
             * tenantId : 402883fd5c8fd7be015c8fde6fd90003
             * tenantName : 北京平谷大棚
             * ghouseId : 402883fd5dbbcfcd015dbbf293db0021
             * baseId : 402883fd5dbbcfcd015dbbe618e60018
             * sysOrgCode : A08
             * taskName : 桃子种植计划
             * headName : yg1
             * planId : 402883fd5dbbcfcd015dbc1782630069
             * headBy : 402883fd5dbb8a9c015dbbc69aa40093
             * taskId : bbeeb6885e59fede015e5a03c1f90002
             * baseFullname : 平谷大棚一号基地
             * ghouseFullname : 桃子种植基地
             * sysCompanyCode : A08
             * worktaskCloseScore : null
             * worktaskCloseDesc : null
             * worktaskDoStatus : ASSIGNED
             * planFullname : 重阳红种植
             */

            private String id;
            private String description;
            private String status;
            private long createDate;
            private String createBy;
            private Object updateBy;
            private String createName;
            private Object updateDate;
            private Object updateName;
            private String tenantId;
            private String tenantName;
            private String ghouseId;
            private String baseId;
            private String sysOrgCode;
            private String taskName;
            private String headName;
            private String planId;
            private String headBy;
            private String taskId;
            private String baseFullname;
            private String ghouseFullname;
            private String sysCompanyCode;
            private Object worktaskCloseScore;
            private Object worktaskCloseDesc;
            private String worktaskDoStatus;
            private String planFullname;

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

            public Object getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(Object updateDate) {
                this.updateDate = updateDate;
            }

            public Object getUpdateName() {
                return updateName;
            }

            public void setUpdateName(Object updateName) {
                this.updateName = updateName;
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

            public String getHeadName() {
                return headName;
            }

            public void setHeadName(String headName) {
                this.headName = headName;
            }

            public String getPlanId() {
                return planId;
            }

            public void setPlanId(String planId) {
                this.planId = planId;
            }

            public String getHeadBy() {
                return headBy;
            }

            public void setHeadBy(String headBy) {
                this.headBy = headBy;
            }

            public String getTaskId() {
                return taskId;
            }

            public void setTaskId(String taskId) {
                this.taskId = taskId;
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

            public Object getWorktaskCloseScore() {
                return worktaskCloseScore;
            }

            public void setWorktaskCloseScore(Object worktaskCloseScore) {
                this.worktaskCloseScore = worktaskCloseScore;
            }

            public Object getWorktaskCloseDesc() {
                return worktaskCloseDesc;
            }

            public void setWorktaskCloseDesc(Object worktaskCloseDesc) {
                this.worktaskCloseDesc = worktaskCloseDesc;
            }

            public String getWorktaskDoStatus() {
                return worktaskDoStatus;
            }

            public void setWorktaskDoStatus(String worktaskDoStatus) {
                this.worktaskDoStatus = worktaskDoStatus;
            }

            public String getPlanFullname() {
                return planFullname;
            }

            public void setPlanFullname(String planFullname) {
                this.planFullname = planFullname;
            }
        }

        public static class YyPlantingTaskBean implements Serializable {
            /**
             * id : bbeeb6885e59fede015e5a03c1f90002
             * content : 桃子种植计划 每日灌溉
             * description :
             * status : ACTIVE
             * createDate : 1504748749000
             * createBy : cxm
             * updateBy : cxm
             * createName : 程晓梅
             * updateDate : 1504748764000
             * updateName : 程晓梅
             * tenantId : 402883fd5c8fd7be015c8fde6fd90003
             * tenantName : 北京平谷大棚
             * ghouseId : 402883fd5dbbcfcd015dbbf293db0021
             * baseId : 402883fd5dbbcfcd015dbbe618e60018
             * sysOrgCode : A08
             * taskName : 桃子种植计划
             * planId : 402883fd5dbbcfcd015dbc1782630069
             * planDays : null
             * farmingCategory : irrigate
             * planTimeStart : 8
             * farmingCategoryDefine :
             * planDateStart : 1504195200000
             * planDateEnd : 1506700800000
             * assignStatus : ALLOCATED
             * worktaskCircle : DAILY
             * planTimeEnd : 9
             * baseFullname : 平谷大棚一号基地
             * attachment1 : null
             * ghouseFullname : 桃子种植基地
             * sysCompanyCode : A08
             * planFullname : 重阳红种植
             */

            private String id;
            private String content;
            private String description;
            private String status;
            private long createDate;
            private String createBy;
            private String updateBy;
            private String createName;
            private long updateDate;
            private String updateName;
            private String tenantId;
            private String tenantName;
            private String ghouseId;
            private String baseId;
            private String sysOrgCode;
            private String taskName;
            private String planId;
            private Object planDays;
            private String farmingCategory;
            private String planTimeStart;
            private String farmingCategoryDefine;
            private long planDateStart;
            private long planDateEnd;
            private String assignStatus;
            private String worktaskCircle;
            private String planTimeEnd;
            private String baseFullname;
            private Object attachment1;
            private String ghouseFullname;
            private String sysCompanyCode;
            private String planFullname;

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

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
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

            public String getPlanId() {
                return planId;
            }

            public void setPlanId(String planId) {
                this.planId = planId;
            }

            public Object getPlanDays() {
                return planDays;
            }

            public void setPlanDays(Object planDays) {
                this.planDays = planDays;
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

            public String getFarmingCategoryDefine() {
                return farmingCategoryDefine;
            }

            public void setFarmingCategoryDefine(String farmingCategoryDefine) {
                this.farmingCategoryDefine = farmingCategoryDefine;
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
        }

        public static class YyWorktaskDayBean implements Serializable {
            /**
             * id : bbeeb6885e59fede015e5a03fe56000e
             * description : 分配 yg1
             * status : ACTIVE
             * createDate : 1504748764000
             * createBy : cxm
             * updateBy : yg1
             * createName : 程晓梅
             * updateDate : 1504752979000
             * updateName : 员工1
             * tenantId : 402883fd5c8fd7be015c8fde6fd90003
             * tenantName : 北京平谷大棚
             * ghouseId : 402883fd5dbbcfcd015dbbf293db0021
             * baseId : 402883fd5dbbcfcd015dbbe618e60018
             * taskName : 桃子种植计划
             * headName : yg1
             * assId : bbeeb6885e59fede015e5a03fdc90006
             * planId : 402883fd5dbbcfcd015dbc1782630069
             * headBy : 402883fd5dbb8a9c015dbbc69aa40093
             * taskId : bbeeb6885e59fede015e5a03c1f90002
             * worktaskDate : 1504713600000
             * baseFullname : 平谷大棚一号基地
             * ghouseFullname : 桃子种植基地
             * worktaskCloseScore : null
             * worktaskCloseDesc : gg
             * worktaskDoStatus : IN_EXECUTION
             * planFullname : 重阳红种植
             */

            private String id;
            private String description;
            private String status;
            private long createDate;
            private String createBy;
            private String updateBy;
            private String createName;
            private long updateDate;
            private String updateName;
            private String tenantId;
            private String tenantName;
            private String ghouseId;
            private String baseId;
            private String taskName;
            private String headName;
            private String assId;
            private String planId;
            private String headBy;
            private String taskId;
            private long worktaskDate;
            private String baseFullname;
            private String ghouseFullname;
            private Object worktaskCloseScore;
            private String worktaskCloseDesc;
            private String worktaskDoStatus;
            private String planFullname;

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

            public String getTaskName() {
                return taskName;
            }

            public void setTaskName(String taskName) {
                this.taskName = taskName;
            }

            public String getHeadName() {
                return headName;
            }

            public void setHeadName(String headName) {
                this.headName = headName;
            }

            public String getAssId() {
                return assId;
            }

            public void setAssId(String assId) {
                this.assId = assId;
            }

            public String getPlanId() {
                return planId;
            }

            public void setPlanId(String planId) {
                this.planId = planId;
            }

            public String getHeadBy() {
                return headBy;
            }

            public void setHeadBy(String headBy) {
                this.headBy = headBy;
            }

            public String getTaskId() {
                return taskId;
            }

            public void setTaskId(String taskId) {
                this.taskId = taskId;
            }

            public long getWorktaskDate() {
                return worktaskDate;
            }

            public void setWorktaskDate(long worktaskDate) {
                this.worktaskDate = worktaskDate;
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

            public Object getWorktaskCloseScore() {
                return worktaskCloseScore;
            }

            public void setWorktaskCloseScore(Object worktaskCloseScore) {
                this.worktaskCloseScore = worktaskCloseScore;
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

            public String getPlanFullname() {
                return planFullname;
            }

            public void setPlanFullname(String planFullname) {
                this.planFullname = planFullname;
            }
        }

        public static class YyPlantingTaskOpelogListBean {
            /**
             * id : bbeeb6885e59fede015e5a03fde80007
             * status : ACTIVE
             * createDate : 1504748764000
             * createBy : cxm
             * updateBy : null
             * createName : 程晓梅
             * updateDate : null
             * updateName : null
             * tenantId : 402883fd5c8fd7be015c8fde6fd90003
             * tenantName : 北京平谷大棚
             * ghouseId : 402883fd5dbbcfcd015dbbf293db0021
             * baseId : 402883fd5dbbcfcd015dbbe618e60018
             * taskName : 桃子种植计划
             * headName : yg1
             * assId : bbeeb6885e59fede015e5a03fdc90006
             * planId : 402883fd5dbbcfcd015dbc1782630069
             * headBy : 402883fd5dbb8a9c015dbbc69aa40093
             * taskId : bbeeb6885e59fede015e5a03c1f90002
             * opeDesc : 程晓梅创建农事任务:桃子种植计划给yg1
             * baseFullname : 平谷大棚一号基地
             * ghouseFullname : 桃子种植基地
             * planFullname : 重阳红种植
             * worktaskStatus : ASSIGN
             */

            private String id;
            private String status;
            private long createDate;
            private String createBy;
            private Object updateBy;
            private String createName;
            private Object updateDate;
            private Object updateName;
            private String tenantId;
            private String tenantName;
            private String ghouseId;
            private String baseId;
            private String taskName;
            private String headName;
            private String assId;
            private String planId;
            private String headBy;
            private String taskId;
            private String opeDesc;
            private String baseFullname;
            private String ghouseFullname;
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

            public Object getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(Object updateDate) {
                this.updateDate = updateDate;
            }

            public Object getUpdateName() {
                return updateName;
            }

            public void setUpdateName(Object updateName) {
                this.updateName = updateName;
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

            public String getTaskName() {
                return taskName;
            }

            public void setTaskName(String taskName) {
                this.taskName = taskName;
            }

            public String getHeadName() {
                return headName;
            }

            public void setHeadName(String headName) {
                this.headName = headName;
            }

            public String getAssId() {
                return assId;
            }

            public void setAssId(String assId) {
                this.assId = assId;
            }

            public String getPlanId() {
                return planId;
            }

            public void setPlanId(String planId) {
                this.planId = planId;
            }

            public String getHeadBy() {
                return headBy;
            }

            public void setHeadBy(String headBy) {
                this.headBy = headBy;
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
             * id : bbeeb6885e59fede015e5a0745620075
             * status : ACTIVE
             * createDate : 1504748979000
             * createBy : yg1
             * updateBy :
             * createName : 员工1
             * updateDate : null
             * updateName :
             * tenantId : 402883fd5c8fd7be015c8fde6fd90003
             * tenantName : 北京平谷大棚
             * ghouseId : 402883fd5dbbcfcd015dbbf293db0021
             * baseId : 402883fd5dbbcfcd015dbbe618e60018
             * taskName : 桃子种植计划
             * headName : yg1
             * assId : bbeeb6885e59fede015e5a03fdc90006
             * planId : 402883fd5dbbcfcd015dbc1782630069
             * headBy : 402883fd5dbb8a9c015dbbc69aa40093
             * hasProblem : N
             * workDesc : [2017-09-07 6:00 ~ 2017-09-07 8:00],[yg1]在[桃子种植基地]进行了[灌溉]工作
             * taskid : bbeeb6885e59fede015e5a03c1f90002
             * farmingCategory : irrigate
             * planTimeStart : 6
             * farmingCategoryDefine : null
             * planDateStart : 1504713600000
             * planDateEnd : 1504713600000
             * problemDesc :
             * planTimeEnd : 8
             * baseFullname : 平谷大棚一号基地
             * attachment1 : null
             * ghouseFullname : 桃子种植基地
             * attachment2 : null
             * planFullname : 重阳红种植
             * taskdayId : bbeeb6885e59fede015e5a03fe56000e
             */

            private String id;
            private String status;
            private long createDate;
            private String createBy;
            private String updateBy;
            private String createName;
            private Object updateDate;
            private String updateName;
            private String tenantId;
            private String tenantName;
            private String ghouseId;
            private String baseId;
            private String taskName;
            private String headName;
            private String assId;
            private String planId;
            private String headBy;
            private String hasProblem;
            private String workDesc;
            private String taskid;
            private String farmingCategory;
            private String planTimeStart;
            private Object farmingCategoryDefine;
            private long planDateStart;
            private long planDateEnd;
            private String problemDesc;
            private String planTimeEnd;
            private String baseFullname;
            private Object attachment1;
            private String ghouseFullname;
            private Object attachment2;
            private String planFullname;
            private String taskdayId;

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

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
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

            public String getTaskName() {
                return taskName;
            }

            public void setTaskName(String taskName) {
                this.taskName = taskName;
            }

            public String getHeadName() {
                return headName;
            }

            public void setHeadName(String headName) {
                this.headName = headName;
            }

            public String getAssId() {
                return assId;
            }

            public void setAssId(String assId) {
                this.assId = assId;
            }

            public String getPlanId() {
                return planId;
            }

            public void setPlanId(String planId) {
                this.planId = planId;
            }

            public String getHeadBy() {
                return headBy;
            }

            public void setHeadBy(String headBy) {
                this.headBy = headBy;
            }

            public String getHasProblem() {
                return hasProblem;
            }

            public void setHasProblem(String hasProblem) {
                this.hasProblem = hasProblem;
            }

            public String getWorkDesc() {
                return workDesc;
            }

            public void setWorkDesc(String workDesc) {
                this.workDesc = workDesc;
            }

            public String getTaskid() {
                return taskid;
            }

            public void setTaskid(String taskid) {
                this.taskid = taskid;
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

            public String getPlanFullname() {
                return planFullname;
            }

            public void setPlanFullname(String planFullname) {
                this.planFullname = planFullname;
            }

            public String getTaskdayId() {
                return taskdayId;
            }

            public void setTaskdayId(String taskdayId) {
                this.taskdayId = taskdayId;
            }
        }
    }
}
