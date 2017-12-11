package com.app.itserv.jparser;

import java.util.List;

/**
 * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003","greenhouseDocSize":1}
 * obj : [{"id":"bbeeb6885d108584015d16b2af1f0018","content":"1","description":"1","status":"INACTIVE","updateName":"管理员","createBy":"cxm","createName":"程晓梅","updateDate":1499324738000,"updateBy":"admin","createDate":1499324395000,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","docName":"1","ghouseId":"bbeeb6885c8c679c015c902d85bc000c","fileType":null,"attachment1":"upload/files/201707061459573I1tzIXP.doc","displayIndex":1}]
 * msg : OK
 * success : true
 */

/**
 * 大棚图片json报文解析线程
 *
 * @author haoruigang
 * @Package com.app.itserv.jparser
 * @project yyshed
 * @ClassName: JsonGreenhouseImgObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-12 下午4:10:38
 */
public class JsonGreenhouseImgObject {

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
         * currUserId : 402883fd5c8fd7be015c8fe0a84e000a currTenantId :
         * 402883fd5c8fd7be015c8fde6fd90003 greenhouseDocSize : 1
         */

        private String currUserId;
        private String currTenantId;
        private int greenhouseDocSize;

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

        public int getGreenhouseDocSize() {
            return greenhouseDocSize;
        }

        public void setGreenhouseDocSize(int greenhouseDocSize) {
            this.greenhouseDocSize = greenhouseDocSize;
        }
    }

    public static class ObjBean {
        /**
         * id : bbeeb6885d108584015d16b2af1f0018 content : 1 description : 1
         * status : INACTIVE updateName : 管理员 createBy : cxm createName : 程晓梅
         * updateDate : 1499324738000 updateBy : admin createDate :
         * 1499324395000 tenantId : 402883fd5c8fd7be015c8fde6fd90003 tenantName
         * : 北京平谷大棚基地 docName : 1 ghouseId : bbeeb6885c8c679c015c902d85bc000c
         * fileType : null attachment1 : upload/files/201707061459573I1tzIXP.doc
         * displayIndex : 1
         */

        private String id;
        private String content;
        private String description;
        private String status;
        private String updateName;
        private String createBy;
        private String createName;
        private long updateDate;
        private String updateBy;
        private long createDate;
        private String tenantId;
        private String tenantName;
        private String docName;
        private String ghouseId;
        private Object fileType;
        private String attachment1;
        private int displayIndex;

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

        public String getDocName() {
            return docName;
        }

        public void setDocName(String docName) {
            this.docName = docName;
        }

        public String getGhouseId() {
            return ghouseId;
        }

        public void setGhouseId(String ghouseId) {
            this.ghouseId = ghouseId;
        }

        public Object getFileType() {
            return fileType;
        }

        public void setFileType(Object fileType) {
            this.fileType = fileType;
        }

        public String getAttachment1() {
            return attachment1;
        }

        public void setAttachment1(String attachment1) {
            this.attachment1 = attachment1;
        }

        public int getDisplayIndex() {
            return displayIndex;
        }

        public void setDisplayIndex(int displayIndex) {
            this.displayIndex = displayIndex;
        }
    }
}
