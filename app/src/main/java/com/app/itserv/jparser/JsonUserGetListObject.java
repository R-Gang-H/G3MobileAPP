package com.app.itserv.jparser;

import java.util.List;

/**
 * @project name：yyshed
 * @type name：JsonUserGetListObject
 * @description：用户列表json报文解析类
 * @author：gang
 * @date time：2017-6-9 下午7:10:07
 */
public class JsonUserGetListObject {

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
         * currUserId : 8a8ab0b246dc81120146dc8181950052 userSize : 22
         * currTenantId : 0
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
         * createDate : 1495456412000 updateBy : admin createBy : admin email :
         * createName : 管理员 updateDate : 1495597790000 updateName : 管理员 tenantId
         * : 0 tenantName : 北京云洋数据科技有限公司 officePhone : mobilePhone :
         * signatureFile : null signature : null departid : null status : 1
         * realName : 员工 password : e0bbbc6a18aaa627 currentDepart :
         * {"address":null
         * ,"description":null,"departname":null,"orgCode":null,"tenantId"
         * :null,"tenantName"
         * :null,"tsdeparts":[],"tspdepart":null,"orgType":null
         * ,"fax":null,"departOrder":null,"mobile":null,"id":null} activitiSync
         * : null userName : ccc userKey : 普通用户 browser : null deleteFlag : 0 id
         * : 402837825c30219e015c3025f36d000a
         */

        private long createDate;
        private String updateBy;
        private String createBy;
        private String email;
        private String createName;
        private long updateDate;
        private String updateName;
        private String tenantId;
        private String tenantName;
        private String officePhone;
        private String mobilePhone;
        private Object signatureFile;
        private Object signature;
        private Object departid;
        private int status;
        private String realName;
        private String password;
        private CurrentDepartBean currentDepart;
        private Object activitiSync;
        private String userName;
        private String userKey;
        private Object browser;
        private int deleteFlag;
        private String id;
        public boolean isSelect;

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

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getUpdateName() {
            return updateName;
        }

        public void setUpdateName(String updateName) {
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

        public Object getDepartid() {
            return departid;
        }

        public void setDepartid(Object departid) {
            this.departid = departid;
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

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
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
             * address : null description : null departname : null orgCode :
             * null tenantId : null tenantName : null tsdeparts : [] tspdepart :
             * null orgType : null fax : null departOrder : null mobile : null
             * id : null
             */

            private Object address;
            private Object description;
            private Object departname;
            private Object orgCode;
            private Object tenantId;
            private Object tenantName;
            private Object tspdepart;
            private Object orgType;
            private Object fax;
            private Object departOrder;
            private Object mobile;
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

            public Object getDepartname() {
                return departname;
            }

            public void setDepartname(Object departname) {
                this.departname = departname;
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

            public Object getDepartOrder() {
                return departOrder;
            }

            public void setDepartOrder(Object departOrder) {
                this.departOrder = departOrder;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
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
