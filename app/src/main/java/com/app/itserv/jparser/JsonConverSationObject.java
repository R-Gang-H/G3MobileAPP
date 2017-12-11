package com.app.itserv.jparser;

import java.util.List;

/**
 * @project name：yyshed
 * @type name：JsonConverSationObject
 * @description：账户明细json报文解析类
 * @author：gang
 * @date time：2017-6-4 下午4:56:18
 * @version
 */

/**
 * attributes : null
 * obj : {"mobilePhone":"18888888888","officePhone":"01088888888","email":"qqqq8888@163.com","createDate":1469003175000,"createName":null,"updateBy":"admin","updateDate":1495855329000,"updateName":"管理员","createBy":null,"tenantName":"北京云洋【不能删】","tenantId":"0","signatureFile":"images/renfang/qm/licf.gif","signature":null,"password":"","userName":"admin","status":1,"userKey":"北京云洋管理员","realName":"管理员","departid":"8a8ab0b246dc81120146dc8180a20016","currentDepart":{"address":null,"description":null,"tspdepart":null,"tsdeparts":[],"orgType":null,"fax":null,"orgCode":null,"tenantName":null,"tenantId":null,"mobile":null,"departname":null,"departOrder":null,"id":null},"activitiSync":null,"browser":null,"deleteFlag":0,"id":"8a8ab0b246dc81120146dc8181950052"}
 * msg : OK
 * success : true
 */

public class JsonConverSationObject {

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
         * mobilePhone : 18888888888 officePhone : 01088888888 email :
         * qqqq8888@163.com createDate : 1469003175000 createName : null
         * updateBy : admin updateDate : 1495855329000 updateName : 管理员 createBy
         * : null tenantName : 北京云洋【不能删】 tenantId : 0 signatureFile :
         * images/renfang/qm/licf.gif signature : null password : userName :
         * admin status : 1 userKey : 北京云洋管理员 realName : 管理员 departid :
         * 8a8ab0b246dc81120146dc8180a20016 currentDepart :
         * {"address":null,"description"
         * :null,"tspdepart":null,"tsdeparts":[],"orgType"
         * :null,"fax":null,"orgCode"
         * :null,"tenantName":null,"tenantId":null,"mobile"
         * :null,"departname":null,"departOrder":null,"id":null} activitiSync :
         * null browser : null deleteFlag : 0 id :
         * 8a8ab0b246dc81120146dc8181950052
         */

        private String mobilePhone;
        private String officePhone;
        private String email;
        private long createDate;
        private Object createName;
        private String updateBy;
        private long updateDate;
        private String updateName;
        private Object createBy;
        private String tenantName;
        private String tenantId;
        private String signatureFile;
        private Object signature;
        private String password;
        private String userName;
        private int status;
        private String userKey;
        private String realName;
        private String departid;
        private CurrentDepartBean currentDepart;
        private Object activitiSync;
        private Object browser;
        private int deleteFlag;
        private String id;

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getOfficePhone() {
            return officePhone;
        }

        public void setOfficePhone(String officePhone) {
            this.officePhone = officePhone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public Object getCreateName() {
            return createName;
        }

        public void setCreateName(Object createName) {
            this.createName = createName;
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

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
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

        public String getSignatureFile() {
            return signatureFile;
        }

        public void setSignatureFile(String signatureFile) {
            this.signatureFile = signatureFile;
        }

        public Object getSignature() {
            return signature;
        }

        public void setSignature(Object signature) {
            this.signature = signature;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getDepartid() {
            return departid;
        }

        public void setDepartid(String departid) {
            this.departid = departid;
        }

        public CurrentDepartBean getCurrentDepart() {
            return currentDepart;
        }

        public void setCurrentDepart(CurrentDepartBean currentDepart) {
            this.currentDepart = currentDepart;
        }

        public Object getActivitiSync() {
            return activitiSync;
        }

        public void setActivitiSync(Object activitiSync) {
            this.activitiSync = activitiSync;
        }

        public Object getBrowser() {
            return browser;
        }

        public void setBrowser(Object browser) {
            this.browser = browser;
        }

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class CurrentDepartBean {
            /**
             * address : null description : null tspdepart : null tsdeparts : []
             * orgType : null fax : null orgCode : null tenantName : null
             * tenantId : null mobile : null departname : null departOrder :
             * null id : null
             */

            private Object address;
            private Object description;
            private Object tspdepart;
            private Object orgType;
            private Object fax;
            private Object orgCode;
            private Object tenantName;
            private Object tenantId;
            private Object mobile;
            private Object departname;
            private Object departOrder;
            private Object id;
            private List<?> tsdeparts;

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public Object getTspdepart() {
                return tspdepart;
            }

            public void setTspdepart(Object tspdepart) {
                this.tspdepart = tspdepart;
            }

            public Object getOrgType() {
                return orgType;
            }

            public void setOrgType(Object orgType) {
                this.orgType = orgType;
            }

            public Object getFax() {
                return fax;
            }

            public void setFax(Object fax) {
                this.fax = fax;
            }

            public Object getOrgCode() {
                return orgCode;
            }

            public void setOrgCode(Object orgCode) {
                this.orgCode = orgCode;
            }

            public Object getTenantName() {
                return tenantName;
            }

            public void setTenantName(Object tenantName) {
                this.tenantName = tenantName;
            }

            public Object getTenantId() {
                return tenantId;
            }

            public void setTenantId(Object tenantId) {
                this.tenantId = tenantId;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public Object getDepartname() {
                return departname;
            }

            public void setDepartname(Object departname) {
                this.departname = departname;
            }

            public Object getDepartOrder() {
                return departOrder;
            }

            public void setDepartOrder(Object departOrder) {
                this.departOrder = departOrder;
            }

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public List<?> getTsdeparts() {
                return tsdeparts;
            }

            public void setTsdeparts(List<?> tsdeparts) {
                this.tsdeparts = tsdeparts;
            }
        }
    }

}
