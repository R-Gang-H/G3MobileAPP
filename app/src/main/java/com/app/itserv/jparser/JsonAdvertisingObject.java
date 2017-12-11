package com.app.itserv.jparser;

import java.util.List;

/**
 * attributes : {"currUserId":"8a8ab0b246dc81120146dc8181950052","currTenantId":"0","size":5}
 * obj : [{"id":"40289f9a5c728a6f015c728deabf0009","description":"e","status":"ACTIVE","topicContent":"<p>关于我们：北京云洋数据科技有限公司<\/p><p>愿景：成就国际一流的智慧物联和农业大数据服务商<\/p>","attachment1":null,"pushFlagGwapp":"Y","attachment3":null,"pushFlagMobileapp":"Y","pushFlagCsss":"Y","attachment2":null,"pushFlagCpms":"Y","updateDate":1496754271000,"updateName":"管理员","createBy":"admin","createName":"管理员","updateBy":"admin","createDate":1496570522000,"tenantId":"0","tenantName":"北京云洋【不能删】","topicType":"LEGALSTATEMENT","topicTitle":"e","openurl":"e"},{"id":"40289f9a5c72e7d4015c72ec552b0003","description":"s","status":"ACTIVE","topicContent":"<p>我们是北京云洋数据科技有限公司<\/p>","attachment1":null,"pushFlagGwapp":"Y","attachment3":null,"pushFlagMobileapp":"Y","pushFlagCsss":"Y","attachment2":null,"pushFlagCpms":"Y","updateDate":1496583242000,"updateName":"管理员","createBy":"admin","createName":"管理员","updateBy":"admin","createDate":1496576709000,"tenantId":"0","tenantName":"北京云洋【不能删】","topicType":"USERGUIDE","topicTitle":"2","openurl":""},{"id":"40289f9a5c72f5c9015c72fb9f8d0003","description":"uyuy","status":"ACTIVE","topicContent":"<p>gghgh<\/p>","attachment1":null,"pushFlagGwapp":"Y","attachment3":null,"pushFlagMobileapp":"Y","pushFlagCsss":"Y","attachment2":null,"pushFlagCpms":"Y","updateDate":null,"updateName":"","createBy":"admin","createName":"管理员","updateBy":"","createDate":1496577712000,"tenantId":"0","tenantName":"北京云洋【不能删】","topicType":"USERGUIDE","topicTitle":"hj","openurl":"jjk"},{"id":"40289f9a5c730cfd015c7312798d0005","description":"水电费","status":"ACTIVE","topicContent":"<p>水电费<\/p>","attachment1":null,"pushFlagGwapp":"Y","attachment3":null,"pushFlagMobileapp":"Y","pushFlagCsss":"Y","attachment2":null,"pushFlagCpms":"Y","updateDate":null,"updateName":"","createBy":"admin","createName":"管理员","updateBy":"","createDate":1496579209000,"tenantId":"0","tenantName":"北京云洋【不能删】","topicType":"ADS","topicTitle":"3545","openurl":"是"},{"id":"40289f9a5c730cfd015c7312d4450007","description":"王五","status":"ACTIVE","topicContent":"<p>22<\/p>","attachment1":null,"pushFlagGwapp":"Y","attachment3":null,"pushFlagMobileapp":"N","pushFlagCsss":"Y","attachment2":null,"pushFlagCpms":"Y","updateDate":null,"updateName":"","createBy":"admin","createName":"管理员","updateBy":"","createDate":1496579232000,"tenantId":"0","tenantName":"北京云洋【不能删】","topicType":"WEATHER","topicTitle":"22","openurl":"我"}]
 * success : true
 * msg : OK
 */

/**
 * @project name：yyshed
 * @type name：JsonAdvertisingObject
 * @description：广告列表json报文解析类
 * @author：gang
 * @date time：2017-6-9 上午9:36:20
 */
public class JsonAdvertisingObject {

    private AttributesBean attributes;
    private boolean success;
    private String msg;
    private List<ObjBean> obj;

    public AttributesBean getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributesBean attributes) {
        this.attributes = attributes;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class AttributesBean {
        /**
         * currUserId : 8a8ab0b246dc81120146dc8181950052 currTenantId : 0 size :
         * 5
         */

        private String currUserId;
        private String currTenantId;
        private int size;

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

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    public static class ObjBean {
        /**
         * id : 40289f9a5c728a6f015c728deabf0009 description : e status : ACTIVE
         * topicContent :
         * <p>
         * 关于我们：北京云洋数据科技有限公司
         * </p>
         * <p>
         * 愿景：成就国际一流的智慧物联和农业大数据服务商
         * </p>
         * attachment1 : null pushFlagGwapp : Y attachment3 : null
         * pushFlagMobileapp : Y pushFlagCsss : Y attachment2 : null
         * pushFlagCpms : Y updateDate : 1496754271000 updateName : 管理员 createBy
         * : admin createName : 管理员 updateBy : admin createDate : 1496570522000
         * tenantId : 0 tenantName : 北京云洋【不能删】 topicType : LEGALSTATEMENT
         * topicTitle : e openurl : e
         */

        private String id;
        private String description;
        private String status;
        private String topicContent;
        private String attachment1;
        private String pushFlagGwapp;
        private Object attachment3;
        private String pushFlagMobileapp;
        private String pushFlagCsss;
        private Object attachment2;
        private String pushFlagCpms;
        private long updateDate;
        private String updateName;
        private String createBy;
        private String createName;
        private String updateBy;
        private long createDate;
        private String tenantId;
        private String tenantName;
        private String topicType;
        private String topicTitle;
        private String openurl;

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

        public String getTopicContent() {
            return topicContent;
        }

        public void setTopicContent(String topicContent) {
            this.topicContent = topicContent;
        }

        public String getAttachment1() {
            return attachment1;
        }

        public void setAttachment1(String attachment1) {
            this.attachment1 = attachment1;
        }

        public String getPushFlagGwapp() {
            return pushFlagGwapp;
        }

        public void setPushFlagGwapp(String pushFlagGwapp) {
            this.pushFlagGwapp = pushFlagGwapp;
        }

        public Object getAttachment3() {
            return attachment3;
        }

        public void setAttachment3(Object attachment3) {
            this.attachment3 = attachment3;
        }

        public String getPushFlagMobileapp() {
            return pushFlagMobileapp;
        }

        public void setPushFlagMobileapp(String pushFlagMobileapp) {
            this.pushFlagMobileapp = pushFlagMobileapp;
        }

        public String getPushFlagCsss() {
            return pushFlagCsss;
        }

        public void setPushFlagCsss(String pushFlagCsss) {
            this.pushFlagCsss = pushFlagCsss;
        }

        public Object getAttachment2() {
            return attachment2;
        }

        public void setAttachment2(Object attachment2) {
            this.attachment2 = attachment2;
        }

        public String getPushFlagCpms() {
            return pushFlagCpms;
        }

        public void setPushFlagCpms(String pushFlagCpms) {
            this.pushFlagCpms = pushFlagCpms;
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

        public String getTopicType() {
            return topicType;
        }

        public void setTopicType(String topicType) {
            this.topicType = topicType;
        }

        public String getTopicTitle() {
            return topicTitle;
        }

        public void setTopicTitle(String topicTitle) {
            this.topicTitle = topicTitle;
        }

        public String getOpenurl() {
            return openurl;
        }

        public void setOpenurl(String openurl) {
            this.openurl = openurl;
        }
    }
}
