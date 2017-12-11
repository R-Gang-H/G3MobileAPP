package com.app.itserv.jparser;

/**
 * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * obj : {"id":"bbeeb6885d3acf04015d3ec2c0b80029","status":"1","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"402883fd5c8fd7be015c8fe0a84e000a","userName":"cxm","ghouseFullname":"1号大棚（西边）"}
 * msg : OK
 * success : true
 */

/**
 * 大棚员工明细json报文解析类
 *
 * @author haoruigang
 * @Package com.app.itserv.jparser
 * @project yyshed
 * @ClassName: JsonGreHouExaEmployeObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-14 上午10:33:08
 */

public class JsonGreHouExaEmployeObject {

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
         * id : bbeeb6885d3acf04015d3ec2c0b80029 status : 1 updateDate : null
         * updateName : null createBy : cxm createName : 程晓梅 createDate :
         * 1499996537000 updateBy : null tenantId :
         * 402883fd5c8fd7be015c8fde6fd90003 tenantName : 北京平谷大棚基地 ghouseId :
         * bbeeb6885d305d79015d34d77d41002e userId :
         * 402883fd5c8fd7be015c8fe0a84e000a userName : cxm ghouseFullname :
         * 1号大棚（西边）
         */

        private String id;
        private String status;
        private Object updateDate;
        private Object updateName;
        private String createBy;
        private String createName;
        private long createDate;
        private Object updateBy;
        private String tenantId;
        private String tenantName;
        private String ghouseId;
        private String userId;
        private String userName;
        private String ghouseFullname;

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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getGhouseFullname() {
            return ghouseFullname;
        }

        public void setGhouseFullname(String ghouseFullname) {
            this.ghouseFullname = ghouseFullname;
        }
    }
}
