package com.app.itserv.jparser;

import java.util.List;

/**
 * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","userSize":2,"currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * msg : OK
 * success : true
 * obj : [{"updateDate":1497257230000,"updateName":"程晓梅","updateBy":"cxm","tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","createName":"管理员","createDate":1497062484000,"email":"","createBy":"admin","officePhone":"","mobilePhone":"","signatureFile":null,"signature":null,"status":1,"realName":"程晓梅","departid":null,"userKey":"平谷大棚负责人","deleteFlag":0,"browser":null,"password":"542322e32b64f7a0","currentDepart":{"address":null,"description":null,"orgCode":null,"tenantId":null,"tenantName":null,"mobile":null,"tsdeparts":[],"orgType":null,"tspdepart":null,"fax":null,"departname":null,"departOrder":null,"id":null},"activitiSync":null,"userName":"cxm","id":"402883fd5c8fd7be015c8fe0a84e000a"},{"updateDate":1497256654000,"updateName":"程晓梅","updateBy":"cxm","tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","createName":"程晓梅","createDate":1497254959000,"email":"","createBy":"cxm","officePhone":"","mobilePhone":"","signatureFile":null,"signature":null,"status":1,"realName":"zhangsan11","departid":null,"userKey":null,"deleteFlag":0,"browser":null,"password":"7e0d5072ccc45286820b07d055078382","currentDepart":{"address":null,"description":null,"orgCode":null,"tenantId":null,"tenantName":null,"mobile":null,"tsdeparts":[],"orgType":null,"tspdepart":null,"fax":null,"departname":null,"departOrder":null,"id":null},"activitiSync":null,"userName":"zhangsan","id":"402883fd5c9b5615015c9b59981d0004"}]
 */

/**
 * @project name：yyshed
 * @type name：JsonUserRoleOrgObject
 * @description：员工管理---角色管理---查看用户
 * @author：gang
 * @date time：2017-6-13 上午11:50:34
 */
public class JsonUserRoleOrgObject {

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
         * currUserId : 402883fd5c8fd7be015c8fe0a84e000a userSize : 2
         * currTenantId : 402883fd5c8fd7be015c8fde6fd90003
         */

        private String currUserId;
        private int userSize;
        private String currTenantId;

        public String getCurrUserId() {
            return currUserId;
        }

        public void setCurrUserId(String currUserId) {
            this.currUserId = currUserId;
        }

        public int getUserSize() {
            return userSize;
        }

        public void setUserSize(int userSize) {
            this.userSize = userSize;
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
         * updateDate : 1497257230000 updateName : 程晓梅 updateBy : cxm tenantId :
         * 402883fd5c8fd7be015c8fde6fd90003 tenantName : 北京平谷大棚基地 createName :
         * 管理员 createDate : 1497062484000 email : createBy : admin officePhone :
         * mobilePhone : signatureFile : null signature : null status : 1
         * realName : 程晓梅 departid : null userKey : 平谷大棚负责人 deleteFlag : 0
         * browser : null password : 542322e32b64f7a0 currentDepart :
         * {"address":
         * null,"description":null,"orgCode":null,"tenantId":null,"tenantName"
         * :null
         * ,"mobile":null,"tsdeparts":[],"orgType":null,"tspdepart":null,"fax"
         * :null,"departname":null,"departOrder":null,"id":null} activitiSync :
         * null userName : cxm id : 402883fd5c8fd7be015c8fe0a84e000a
         */

        private long updateDate;
        private String updateName;
        private String updateBy;
        private String tenantId;
        private String tenantName;
        private String createName;
        private long createDate;
        private String email;
        private String createBy;
        private String officePhone;
        private String mobilePhone;
        private Object signatureFile;
        private Object signature;
        private int status;
        private String realName;
        private Object departid;
        private String userKey;
        private int deleteFlag;
        private Object browser;
        private String password;
        private CurrentDepartBean currentDepart;
        private Object activitiSync;
        private String userName;
        private String id;

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

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getOfficePhone() {
            return officePhone;
        }

        public void setOfficePhone(String officePhone) {
            this.officePhone = officePhone;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
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

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public Object getBrowser() {
            return browser;
        }

        public void setBrowser(Object browser) {
            this.browser = browser;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class CurrentDepartBean {
            /**
             * address : null description : null orgCode : null tenantId : null
             * tenantName : null mobile : null tsdeparts : [] orgType : null
             * tspdepart : null fax : null departname : null departOrder : null
             * id : null
             */

            private Object address;
            private Object description;
            private Object orgCode;
            private Object tenantId;
            private Object tenantName;
            private Object mobile;
            private Object orgType;
            private Object tspdepart;
            private Object fax;
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

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
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
