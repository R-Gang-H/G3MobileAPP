package com.app.itserv.jparser;

/**
 * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * obj : {"id":"bbeeb6885cec47d3015cf804a8f102d5","description":null,"status":"ACTIVE","attachment1":null,"attachment2":null,"attachment3":null,"noticeTitle":"手机AppV3.0","adviceReplyStatus":"WAITINGREPLY","noticeContent":"手机AppV3.0研发中","adviceSendCategory":"WAITINGREPLY","updateBy":null,"createDate":null,"updateDate":null,"createBy":null,"createName":null,"updateName":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","applyName":null,"applyDate":null,"applyBy":null,"sendDate":null,"sendName":null,"sendBy":null}
 * msg : 添加成功
 * success : true
 */

/**
 * 新增咨询建议json报文解析类
 *
 * @author 作者 E-mail:
 * @version 1.0
 * @date 创建时间：2017-6-30 下午4:57:38
 * @parameter
 * @return
 */
public class JsonAddAdvisoryObject {

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
         * id : bbeeb6885cec47d3015cf804a8f102d5 description : null status :
         * ACTIVE attachment1 : null attachment2 : null attachment3 : null
         * noticeTitle : 手机AppV3.0 adviceReplyStatus : WAITINGREPLY
         * noticeContent : 手机AppV3.0研发中 adviceSendCategory : WAITINGREPLY
         * updateBy : null createDate : null updateDate : null createBy : null
         * createName : null updateName : null tenantId :
         * 402883fd5c8fd7be015c8fde6fd90003 tenantName : 北京平谷大棚基地 applyName :
         * null applyDate : null applyBy : null sendDate : null sendName : null
         * sendBy : null
         */

        private String id;
        private Object description;
        private String status;
        private Object attachment1;
        private Object attachment2;
        private Object attachment3;
        private String noticeTitle;
        private String adviceReplyStatus;
        private String noticeContent;
        private String adviceSendCategory;
        private Object updateBy;
        private Object createDate;
        private Object updateDate;
        private Object createBy;
        private Object createName;
        private Object updateName;
        private String tenantId;
        private String tenantName;
        private Object applyName;
        private Object applyDate;
        private Object applyBy;
        private Object sendDate;
        private Object sendName;
        private Object sendBy;

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

        public String getAdviceReplyStatus() {
            return adviceReplyStatus;
        }

        public void setAdviceReplyStatus(String adviceReplyStatus) {
            this.adviceReplyStatus = adviceReplyStatus;
        }

        public String getNoticeContent() {
            return noticeContent;
        }

        public void setNoticeContent(String noticeContent) {
            this.noticeContent = noticeContent;
        }

        public String getAdviceSendCategory() {
            return adviceSendCategory;
        }

        public void setAdviceSendCategory(String adviceSendCategory) {
            this.adviceSendCategory = adviceSendCategory;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateName() {
            return createName;
        }

        public void setCreateName(Object createName) {
            this.createName = createName;
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

        public Object getApplyName() {
            return applyName;
        }

        public void setApplyName(Object applyName) {
            this.applyName = applyName;
        }

        public Object getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(Object applyDate) {
            this.applyDate = applyDate;
        }

        public Object getApplyBy() {
            return applyBy;
        }

        public void setApplyBy(Object applyBy) {
            this.applyBy = applyBy;
        }

        public Object getSendDate() {
            return sendDate;
        }

        public void setSendDate(Object sendDate) {
            this.sendDate = sendDate;
        }

        public Object getSendName() {
            return sendName;
        }

        public void setSendName(Object sendName) {
            this.sendName = sendName;
        }

        public Object getSendBy() {
            return sendBy;
        }

        public void setSendBy(Object sendBy) {
            this.sendBy = sendBy;
        }
    }

}
