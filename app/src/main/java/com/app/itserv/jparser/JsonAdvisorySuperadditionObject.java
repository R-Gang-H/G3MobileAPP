package com.app.itserv.jparser;

public class JsonAdvisorySuperadditionObject {

    /**
     * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
     * obj : {"id":"bbeeb6885d305d2c015d34d78204006b","description":null,"status":"","updateName":"管理员","createBy":"cxm","createName":"程晓梅","updateDate":1499847581390,"updateBy":"admin","createDate":1499830125000,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","applyDate":1499847581390,"applyBy":"8a8ab0b246dc81120146dc8181950052","sendBy":"402883fd5c8fd7be015c8fe0a84e000a","sendDate":1499830125000,"sendName":"程晓梅","applyName":"管理员","attachment1":null,"attachment2":null,"attachment3":null,"noticeTitle":"网关怎么安装？","adviceSendCategory":"NOTIFY","noticeContent":"我是新用户，第一次使用你们的网关，请问怎么使用？","adviceReplyStatus":"REPLYED"}
     * msg : 回复成功
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
         * id : bbeeb6885d305d2c015d34d78204006b
         * description : null
         * status :
         * updateName : 管理员
         * createBy : cxm
         * createName : 程晓梅
         * updateDate : 1499847581390
         * updateBy : admin
         * createDate : 1499830125000
         * tenantId : 402883fd5c8fd7be015c8fde6fd90003
         * tenantName : 北京平谷大棚基地
         * applyDate : 1499847581390
         * applyBy : 8a8ab0b246dc81120146dc8181950052
         * sendBy : 402883fd5c8fd7be015c8fe0a84e000a
         * sendDate : 1499830125000
         * sendName : 程晓梅
         * applyName : 管理员
         * attachment1 : null
         * attachment2 : null
         * attachment3 : null
         * noticeTitle : 网关怎么安装？
         * adviceSendCategory : NOTIFY
         * noticeContent : 我是新用户，第一次使用你们的网关，请问怎么使用？
         * adviceReplyStatus : REPLYED
         */

        private String id;
        private Object description;
        private String status;
        private String updateName;
        private String createBy;
        private String createName;
        private long updateDate;
        private String updateBy;
        private long createDate;
        private String tenantId;
        private String tenantName;
        private long applyDate;
        private String applyBy;
        private String sendBy;
        private long sendDate;
        private String sendName;
        private String applyName;
        private Object attachment1;
        private Object attachment2;
        private Object attachment3;
        private String noticeTitle;
        private String adviceSendCategory;
        private String noticeContent;
        private String adviceReplyStatus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
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

        public long getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(long applyDate) {
            this.applyDate = applyDate;
        }

        public String getApplyBy() {
            return applyBy;
        }

        public void setApplyBy(String applyBy) {
            this.applyBy = applyBy;
        }

        public String getSendBy() {
            return sendBy;
        }

        public void setSendBy(String sendBy) {
            this.sendBy = sendBy;
        }

        public long getSendDate() {
            return sendDate;
        }

        public void setSendDate(long sendDate) {
            this.sendDate = sendDate;
        }

        public String getSendName() {
            return sendName;
        }

        public void setSendName(String sendName) {
            this.sendName = sendName;
        }

        public String getApplyName() {
            return applyName;
        }

        public void setApplyName(String applyName) {
            this.applyName = applyName;
        }

        public Object getAttachment1() {
            return attachment1;
        }

        public void setAttachment1(Object attachment1) {
            this.attachment1 = attachment1;
        }

        public Object getAttachment2() {
            return attachment2;
        }

        public void setAttachment2(Object attachment2) {
            this.attachment2 = attachment2;
        }

        public Object getAttachment3() {
            return attachment3;
        }

        public void setAttachment3(Object attachment3) {
            this.attachment3 = attachment3;
        }

        public String getNoticeTitle() {
            return noticeTitle;
        }

        public void setNoticeTitle(String noticeTitle) {
            this.noticeTitle = noticeTitle;
        }

        public String getAdviceSendCategory() {
            return adviceSendCategory;
        }

        public void setAdviceSendCategory(String adviceSendCategory) {
            this.adviceSendCategory = adviceSendCategory;
        }

        public String getNoticeContent() {
            return noticeContent;
        }

        public void setNoticeContent(String noticeContent) {
            this.noticeContent = noticeContent;
        }

        public String getAdviceReplyStatus() {
            return adviceReplyStatus;
        }

        public void setAdviceReplyStatus(String adviceReplyStatus) {
            this.adviceReplyStatus = adviceReplyStatus;
        }
    }

}
