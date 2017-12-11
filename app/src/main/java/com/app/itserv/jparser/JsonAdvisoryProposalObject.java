package com.app.itserv.jparser;

import java.io.Serializable;
import java.util.List;

/**
 * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","userSize":10,"currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * obj : [{"id":"4028832f5cc4f08b015cc4f264740005","description":null,"updateBy":"","updateDate":null,"updateName":"","tenantName":"山东云洋","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","createBy":"scott","createName":"张代浩","createDate":1497952838000,"applyName":null,"applyDate":null,"applyBy":"","sendDate":1497952838000,"sendName":"张代浩","sendBy":"8a8ab0b246dc81120146dc81819d0053","status":"ACTIVE","attachment1":null,"attachment3":null,"attachment2":null,"adviceSendCategory":"NOTIFY","noticeContent":"","noticeTitle":"","adviceReplyStatus":"WAITINGREPLY"},{"id":"4028832f5cc4f08b015cc502ad440007","description":null,"updateBy":"","updateDate":null,"updateName":"","tenantName":"山东云洋","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","createBy":"scott","createName":"张代浩","createDate":1497953905000,"applyName":null,"applyDate":null,"applyBy":"","sendDate":1497953905000,"sendName":"张代浩","sendBy":"8a8ab0b246dc81120146dc81819d0053","status":"ACTIVE","attachment1":null,"attachment3":null,"attachment2":null,"adviceSendCategory":"NOTIFY","noticeContent":"","noticeTitle":"","adviceReplyStatus":"WAITINGREPLY"},{"id":"4028832f5cca2545015cca2a610e0000","description":null,"updateBy":null,"updateDate":null,"updateName":null,"tenantName":"北京云洋数据科技有限公司","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","createBy":null,"createName":null,"createDate":null,"applyName":null,"applyDate":null,"applyBy":null,"sendDate":null,"sendName":null,"sendBy":null,"status":"ACTIVE","attachment1":null,"attachment3":null,"attachment2":null,"adviceSendCategory":"ADVICE","noticeContent":"æ\u0088\u0091æ\u0098¯å\u0086\u0085å®¹","noticeTitle":"æ\u0088\u0091æ\u0098¯æ \u0087é¢\u0098","adviceReplyStatus":"WAITINGREPLY"},{"id":"40289f8a5c7fefe2015c802d96bd0004","description":null,"updateBy":"","updateDate":null,"updateName":"","tenantName":"山东云洋","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","createBy":"scott","createName":"张代浩","createDate":1496799090000,"applyName":null,"applyDate":null,"applyBy":"","sendDate":1496799090000,"sendName":"张代浩","sendBy":"8a8ab0b246dc81120146dc81819d0053","status":"ACTIVE","attachment1":null,"attachment3":null,"attachment2":null,"adviceSendCategory":"NOTIFY","noticeContent":"11","noticeTitle":"11","adviceReplyStatus":"WAITINGREPLY"},{"id":"40289f8a5ca4a097015ca5c9c455000a","description":null,"updateBy":"","updateDate":null,"updateName":"","tenantName":"山东云洋","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","createBy":"scott","createName":"张代浩","createDate":1497430082000,"applyName":null,"applyDate":null,"applyBy":"","sendDate":1497430082000,"sendName":"张代浩","sendBy":"8a8ab0b246dc81120146dc81819d0053","status":"ACTIVE","attachment1":null,"attachment3":null,"attachment2":null,"adviceSendCategory":"ADVICE","noticeContent":"良好的建议","noticeTitle":"11","adviceReplyStatus":"WAITINGREPLY"},{"id":"40289f8a5ca4a097015ca5ca7230000c","description":null,"updateBy":"","updateDate":null,"updateName":"","tenantName":"山东云洋","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","createBy":"scott","createName":"张代浩","createDate":1497430127000,"applyName":null,"applyDate":null,"applyBy":"","sendDate":1497430127000,"sendName":"张代浩","sendBy":"8a8ab0b246dc81120146dc81819d0053","status":"ACTIVE","attachment1":null,"attachment3":null,"attachment2":null,"adviceSendCategory":"CONSULT","noticeContent":"网关如何使用","noticeTitle":"wwwwwww","adviceReplyStatus":"WAITINGREPLY"},{"id":"40289f8a5ca4a097015ca5cadf57000e","description":null,"updateBy":"","updateDate":null,"updateName":"","tenantName":"山东云洋","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","createBy":"scott","createName":"张代浩","createDate":1497430155000,"applyName":null,"applyDate":null,"applyBy":"","sendDate":1497430155000,"sendName":"张代浩","sendBy":"8a8ab0b246dc81120146dc81819d0053","status":"ACTIVE","attachment1":null,"attachment3":null,"attachment2":null,"adviceSendCategory":"CONSULT","noticeContent":"网关如何使用","noticeTitle":"%%%%%%","adviceReplyStatus":"WAITINGREPLY"},{"id":"40289f8a5ca4a097015ca5cbc1a70012","description":null,"updateBy":"","updateDate":null,"updateName":"","tenantName":"山东云洋","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","createBy":"scott","createName":"张代浩","createDate":1497430213000,"applyName":null,"applyDate":null,"applyBy":"","sendDate":1497430213000,"sendName":"张代浩","sendBy":"8a8ab0b246dc81120146dc81819d0053","status":"ACTIVE","attachment1":null,"attachment3":null,"attachment2":null,"adviceSendCategory":"COMPLAIN","noticeContent":"qqqq","noticeTitle":"","adviceReplyStatus":"WAITINGREPLY"},{"id":"40289f8a5ca4a097015ca5d984e30014","description":null,"updateBy":"","updateDate":null,"updateName":"","tenantName":"山东云洋","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","createBy":"scott","createName":"张代浩","createDate":1497431114000,"applyName":null,"applyDate":null,"applyBy":"","sendDate":1497431114000,"sendName":"张代浩","sendBy":"8a8ab0b246dc81120146dc81819d0053","status":"ACTIVE","attachment1":null,"attachment3":null,"attachment2":null,"adviceSendCategory":"NOTIFY","noticeContent":"111","noticeTitle":"网关使用","adviceReplyStatus":"WAITINGREPLY"},{"id":"40289f8a5ca4a097015ca5e2dd440023","description":null,"updateBy":"","updateDate":null,"updateName":"","tenantName":"山东云洋","tenantId":"bbeeb6885c3f560c015c44bee4bf0006","createBy":"scott","createName":"张代浩","createDate":1497431727000,"applyName":null,"applyDate":null,"applyBy":"","sendDate":1497431727000,"sendName":"张代浩","sendBy":"8a8ab0b246dc81120146dc81819d0053","status":"ACTIVE","attachment1":null,"attachment3":null,"attachment2":null,"adviceSendCategory":"NOTIFY","noticeContent":"11","noticeTitle":"11","adviceReplyStatus":"WAITINGREPLY"}]
 * msg : OK
 * success : true
 */

/**
 * 投诉建议json报文解析类
 *
 * @author haoruigang
 * @Package com.app.itserv.jparser
 * @project yyshed
 * @ClassName: JsonAdvisoryProposalObject
 * @date 2017-6-24 下午4:05:06
 */
public class JsonAdvisoryProposalObject {

    /**
     * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","userAdviceSize":3,"currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
     * obj : [{"id":"bbeeb6885d305d2c015d308048cf003c","description":null,"status":"","updateName":"张代浩","createBy":"scott","createName":"张代浩","updateDate":1499757868000,"updateBy":"scott","createDate":1499757299000,"tenantId":"bbeeb6885c3f560c015c44bee4bf0006","tenantName":"山东云洋","applyDate":1499757868000,"applyBy":"8a8ab0b246dc81120146dc81819d0053","sendBy":"8a8ab0b246dc81120146dc81819d0053","sendDate":1499757299000,"sendName":"张代浩","applyName":"张代浩","attachment1":null,"attachment2":null,"attachment3":null,"noticeTitle":"水阀坏了","adviceSendCategory":"NOTIFY","noticeContent":"我的水阀坏了","adviceReplyStatus":"REPLYED"},{"id":"bbeeb6885d305d2c015d30831f5c0042","description":null,"status":"","updateName":"张代浩","createBy":"scott","createName":"张代浩","updateDate":1499757933000,"updateBy":"scott","createDate":1499757485000,"tenantId":"bbeeb6885c3f560c015c44bee4bf0006","tenantName":"山东云洋","applyDate":1499757933000,"applyBy":"8a8ab0b246dc81120146dc81819d0053","sendBy":"8a8ab0b246dc81120146dc81819d0053","sendDate":1499757485000,"sendName":"张代浩","applyName":"张代浩","attachment1":null,"attachment2":null,"attachment3":null,"noticeTitle":"网关问题","adviceSendCategory":"COMPLAIN","noticeContent":"网关的Bug太对","adviceReplyStatus":"REPLYED"},{"id":"bbeeb6885d305d2c015d308aa5d9004f","description":null,"status":"","updateName":"张代浩","createBy":"scott","createName":"张代浩","updateDate":1499758088000,"updateBy":"scott","createDate":1499757979000,"tenantId":"bbeeb6885c3f560c015c44bee4bf0006","tenantName":"山东云洋","applyDate":1499758088000,"applyBy":"8a8ab0b246dc81120146dc81819d0053","sendBy":"8a8ab0b246dc81120146dc81819d0053","sendDate":1499757979000,"sendName":"张代浩","applyName":"张代浩","attachment1":null,"attachment2":null,"attachment3":null,"noticeTitle":"传感器配对","adviceSendCategory":"CONSULT","noticeContent":"请问传感器怎么配对","adviceReplyStatus":"REPLYED"}]
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
         * currUserId : 402883fd5c8fd7be015c8fe0a84e000a
         * userAdviceSize : 3
         * currTenantId : 402883fd5c8fd7be015c8fde6fd90003
         */

        private String currUserId;
        private int userAdviceSize;
        private String currTenantId;

        public String getCurrUserId() {
            return currUserId;
        }

        public void setCurrUserId(String currUserId) {
            this.currUserId = currUserId;
        }

        public int getUserAdviceSize() {
            return userAdviceSize;
        }

        public void setUserAdviceSize(int userAdviceSize) {
            this.userAdviceSize = userAdviceSize;
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
         * id : bbeeb6885d305d2c015d308048cf003c
         * description : null
         * status :
         * updateName : 张代浩
         * createBy : scott
         * createName : 张代浩
         * updateDate : 1499757868000
         * updateBy : scott
         * createDate : 1499757299000
         * tenantId : bbeeb6885c3f560c015c44bee4bf0006
         * tenantName : 山东云洋
         * applyDate : 1499757868000
         * applyBy : 8a8ab0b246dc81120146dc81819d0053
         * sendBy : 8a8ab0b246dc81120146dc81819d0053
         * sendDate : 1499757299000
         * sendName : 张代浩
         * applyName : 张代浩
         * attachment1 : null
         * attachment2 : null
         * attachment3 : null
         * noticeTitle : 水阀坏了
         * adviceSendCategory : NOTIFY
         * noticeContent : 我的水阀坏了
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
