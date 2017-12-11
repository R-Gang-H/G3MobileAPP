package com.app.itserv.jparser;

import java.util.List;

/**
 * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * msg : 激活成功
 * success : true
 * obj : {"officePhone":null,"mobilePhone":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","updateName":"程晓梅","email":null,"createBy":"admin","createName":"管理员","createDate":1497062484000,"updateBy":"cxm","updateDate":1497257230000,"signatureFile":null,"signature":null,"password":"542322e32b64f7a0","status":1,"realName":"程晓梅","departid":null,"userKey":"平谷大棚负责人","activitiSync":null,"browser":null,"deleteFlag":0,"userName":"cxm","currentDepart":{"address":null,"description":null,"tsdeparts":[],"orgType":null,"tspdepart":null,"orgCode":null,"tenantId":null,"tenantName":null,"fax":null,"departname":null,"mobile":null,"departOrder":null,"id":null},"id":"402883fd5c8fd7be015c8fe0a84e000a"}
 */

/**
 * @project name：yyshed
 * @type name：JsonUserCancellActivatObject
 * @description：员工---用户管理-注销、激活json 报文解析类
 * @author：gang
 * @date time：2017-6-15 下午5:35:06
 */
public class JsonUserCancellActivatObject {

    private AttributesBean attributes;
    private String msg;
    private boolean success;
    private ObjBean obj;

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

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
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
         * officePhone : null mobilePhone : null tenantId :
         * 402883fd5c8fd7be015c8fde6fd90003 tenantName : 北京平谷大棚基地 updateName :
         * 程晓梅 email : null createBy : admin createName : 管理员 createDate :
         * 1497062484000 updateBy : cxm updateDate : 1497257230000 signatureFile
         * : null signature : null password : 542322e32b64f7a0 status : 1
         * realName : 程晓梅 departid : null userKey : 平谷大棚负责人 activitiSync : null
         * browser : null deleteFlag : 0 userName : cxm currentDepart :
         * {"address"
         * :null,"description":null,"tsdeparts":[],"orgType":null,"tspdepart"
         * :null,"orgCode":null,"tenantId":null,"tenantName":null,"fax":null,
         * "departname":null,"mobile":null,"departOrder":null,"id":null} id :
         * 402883fd5c8fd7be015c8fe0a84e000a
         */

        private Object officePhone;
        private Object mobilePhone;
        private String tenantId;
        private String tenantName;
        private String updateName;
        private Object email;
        private String createBy;
        private String createName;
        private long createDate;
        private String updateBy;
        private long updateDate;
        private Object signatureFile;
        private Object signature;
        private String password;
        private int status;
        private String realName;
        private Object departid;
        private String userKey;
        private Object activitiSync;
        private Object browser;
        private int deleteFlag;
        private String userName;
        private CurrentDepartBean currentDepart;
        private String id;

        public Object getOfficePhone() {
            return officePhone;
        }

        public void setOfficePhone(Object officePhone) {
            this.officePhone = officePhone;
        }

        public Object getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(Object mobilePhone) {
            this.mobilePhone = mobilePhone;
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

        public String getUpdateName() {
            return updateName;
        }

        public void setUpdateName(String updateName) {
            this.updateName = updateName;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
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

        public Object getSignatureFile() {
            return signatureFile;
        }

        public void setSignatureFile(Object signatureFile) {
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public Object getDepartid() {
            return departid;
        }

        public void setDepartid(Object departid) {
            this.departid = departid;
        }

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public CurrentDepartBean getCurrentDepart() {
            return currentDepart;
        }

        public void setCurrentDepart(CurrentDepartBean currentDepart) {
            this.currentDepart = currentDepart;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class CurrentDepartBean {
            /**
             * address : null description : null tsdeparts : [] orgType : null
             * tspdepart : null orgCode : null tenantId : null tenantName : null
             * fax : null departname : null mobile : null departOrder : null id
             * : null
             */

            private Object address;
            private Object description;
            private Object orgType;
            private Object tspdepart;
            private Object orgCode;
            private Object tenantId;
            private Object tenantName;
            private Object fax;
            private Object departname;
            private Object mobile;
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

            public Object getOrgType() {
                return orgType;
            }

            public void setOrgType(Object orgType) {
                this.orgType = orgType;
            }

            public Object getTspdepart() {
                return tspdepart;
            }

            public void setTspdepart(Object tspdepart) {
                this.tspdepart = tspdepart;
            }

            public Object getOrgCode() {
                return orgCode;
            }

            public void setOrgCode(Object orgCode) {
                this.orgCode = orgCode;
            }

            public Object getTenantId() {
                return tenantId;
            }

            public void setTenantId(Object tenantId) {
                this.tenantId = tenantId;
            }

            public Object getTenantName() {
                return tenantName;
            }

            public void setTenantName(Object tenantName) {
                this.tenantName = tenantName;
            }

            public Object getFax() {
                return fax;
            }

            public void setFax(Object fax) {
                this.fax = fax;
            }

            public Object getDepartname() {
                return departname;
            }

            public void setDepartname(Object departname) {
                this.departname = departname;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
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
