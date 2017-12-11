package com.app.itserv.jparser;

/**
 * msg : get userguide success
 * obj : {"createBy":"admin","createDate":1496576709000,"createName":"管理员","description":"s","id":"40289f9a5c72e7d4015c72ec552b0003","openurl":"","pushFlagCpms":"Y","pushFlagCsss":"Y","pushFlagGwapp":"Y","pushFlagMobileapp":"Y","status":"ACTIVE","tenantId":"0","tenantName":"北京云洋【不能删】","topicContent":"<p>我们是北京云洋数据科技有限公司<\/p>","topicTitle":"2","topicType":"USERGUIDE","updateBy":"admin","updateDate":1496583242000,"updateName":"管理员"}
 * success : true
 */

/**
 * @project name：yyshed
 * @type name：JsonProtocolObject
 * @description：用户协议json报文解析类
 * @author：gang
 * @date time：2017-6-5 上午10:12:43
 */
public class JsonProtocolObject {

    private String msg;
    private ObjBean obj;
    private boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class ObjBean {
        /**
         * createBy : admin createDate : 1496576709000 createName : 管理员
         * description : s id : 40289f9a5c72e7d4015c72ec552b0003 openurl :
         * pushFlagCpms : Y pushFlagCsss : Y pushFlagGwapp : Y pushFlagMobileapp
         * : Y status : ACTIVE tenantId : 0 tenantName : 北京云洋【不能删】 topicContent
         * :
         * <p>
         * 我们是北京云洋数据科技有限公司
         * </p>
         * topicTitle : 2 topicType : USERGUIDE updateBy : admin updateDate :
         * 1496583242000 updateName : 管理员
         */

        private String createBy;
        private long createDate;
        private String createName;
        private String description;
        private String id;
        private String openurl;
        private String pushFlagCpms;
        private String pushFlagCsss;
        private String pushFlagGwapp;
        private String pushFlagMobileapp;
        private String status;
        private String tenantId;
        private String tenantName;
        private String topicContent;
        private String topicTitle;
        private String topicType;
        private String updateBy;
        private long updateDate;
        private String updateName;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOpenurl() {
            return openurl;
        }

        public void setOpenurl(String openurl) {
            this.openurl = openurl;
        }

        public String getPushFlagCpms() {
            return pushFlagCpms;
        }

        public void setPushFlagCpms(String pushFlagCpms) {
            this.pushFlagCpms = pushFlagCpms;
        }

        public String getPushFlagCsss() {
            return pushFlagCsss;
        }

        public void setPushFlagCsss(String pushFlagCsss) {
            this.pushFlagCsss = pushFlagCsss;
        }

        public String getPushFlagGwapp() {
            return pushFlagGwapp;
        }

        public void setPushFlagGwapp(String pushFlagGwapp) {
            this.pushFlagGwapp = pushFlagGwapp;
        }

        public String getPushFlagMobileapp() {
            return pushFlagMobileapp;
        }

        public void setPushFlagMobileapp(String pushFlagMobileapp) {
            this.pushFlagMobileapp = pushFlagMobileapp;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getTopicContent() {
            return topicContent;
        }

        public void setTopicContent(String topicContent) {
            this.topicContent = topicContent;
        }

        public String getTopicTitle() {
            return topicTitle;
        }

        public void setTopicTitle(String topicTitle) {
            this.topicTitle = topicTitle;
        }

        public String getTopicType() {
            return topicType;
        }

        public void setTopicType(String topicType) {
            this.topicType = topicType;
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

        public String getUpdateName() {
            return updateName;
        }

        public void setUpdateName(String updateName) {
            this.updateName = updateName;
        }
    }
}
