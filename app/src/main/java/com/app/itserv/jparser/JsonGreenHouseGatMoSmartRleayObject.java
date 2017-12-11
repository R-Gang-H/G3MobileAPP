package com.app.itserv.jparser;

import java.util.List;

/**
 * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * obj : [{"id":"bbeeb6885c8c679c015c902cc336000a","content":"\t\t\t\t \r\n\t\t\t\t ","description":"","status":"ACTIVE","updateName":"管理员","createDate":1497067471000,"createBy":"cxm","createName":"程晓梅","updateBy":"admin","updateDate":1499221671000,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","regionAddr":"平谷基地1号","tenantName":"北京平谷大棚基地","usedArea":"1000","longitude":"","usedAreaMu":"1000","latitude":"","areaLength":"","baseId":"","ghouseCode":"100001","areaWidth":"","coveredAreaMu":"1000","regionIdProvince":"北京市","openDateStop":null,"displayIndex":"","openDateOpen":null,"attachment1":null,"baseFullname":"---请选择---","regionIdCountry":"中国","regionIdCity":"市辖区","regionIdDistrict":"平谷区","latitudeFlag":"","coveredArea":"1000","longtitudeFlag":"","ghouseFullname":"平谷大棚基地1号","singleproCount":null,"smartCount":"0"},{"id":"bbeeb6885c8c679c015c902d85bc000c","content":"\t\t\t\t \r\n\t\t\t\t ","description":"","status":"ACTIVE","updateName":"管理员","createDate":1497067521000,"createBy":"cxm","createName":"程晓梅","updateBy":"admin","updateDate":1499221671000,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","regionAddr":"平谷大棚基地2号","tenantName":"北京平谷大棚基地","usedArea":"1000","longitude":"","usedAreaMu":"1000","latitude":"","areaLength":"","baseId":"","ghouseCode":"100002","areaWidth":"","coveredAreaMu":"1000","regionIdProvince":"北京市","openDateStop":null,"displayIndex":"","openDateOpen":null,"attachment1":null,"baseFullname":"---请选择---","regionIdCountry":"中国","regionIdCity":"市辖区","regionIdDistrict":"平谷区","latitudeFlag":"","coveredArea":"1000","longtitudeFlag":"","ghouseFullname":"平谷大棚基地2号","singleproCount":null,"smartCount":"0"}]
 * msg : OK
 * success : true
 */

/**
 * 基地中大棚继电器列表json报文解析类
 *
 * @author jcy
 * @Package com.app.itserv.jparser
 * @project yyshed
 * @ClassName: JsonGreenHouseObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-8 上午11:03:50
 */

public class JsonGreenHouseGatMoSmartRleayObject {


    /**
     * attributes : {"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
     * obj : {"moDoubleControlSize":2,"moOneControlSize":1,"moCameraControlList":[],"moOneControlList":[{"status":null,"directive":null,"sn":"21012C000000037C","deviceType":"智能喷雾"}],"moDoubleControlList":[{"status":null,"directive":null,"sn":"21012C0000000386","deviceType":"放风机"},{"status":null,"directive":null,"sn":"21012C000000038D","deviceType":"放风机"}],"moCameraControlSize":0}
     * success : true
     * msg : OK
     */

    private AttributesBean attributes;
    private ObjBean obj;
    private boolean success;
    private String msg;

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
         * moDoubleControlSize : 2
         * moOneControlSize : 1
         * moCameraControlList : []
         * moOneControlList : [{"status":null,"directive":null,"sn":"21012C000000037C","deviceType":"智能喷雾"}]
         * moDoubleControlList : [{"status":null,"directive":null,"sn":"21012C0000000386","deviceType":"放风机"},{"status":null,"directive":null,"sn":"21012C000000038D","deviceType":"放风机"}]
         * moCameraControlSize : 0
         */

        private int moDoubleControlSize;
        private int moOneControlSize;
        private int moCameraControlSize;
        private List<?> moCameraControlList;
        private List<MoOneControlListBean> moOneControlList;
        private List<MoDoubleControlListBean> moDoubleControlList;

        public int getMoDoubleControlSize() {
            return moDoubleControlSize;
        }

        public void setMoDoubleControlSize(int moDoubleControlSize) {
            this.moDoubleControlSize = moDoubleControlSize;
        }

        public int getMoOneControlSize() {
            return moOneControlSize;
        }

        public void setMoOneControlSize(int moOneControlSize) {
            this.moOneControlSize = moOneControlSize;
        }

        public int getMoCameraControlSize() {
            return moCameraControlSize;
        }

        public void setMoCameraControlSize(int moCameraControlSize) {
            this.moCameraControlSize = moCameraControlSize;
        }

        public List<?> getMoCameraControlList() {
            return moCameraControlList;
        }

        public void setMoCameraControlList(List<?> moCameraControlList) {
            this.moCameraControlList = moCameraControlList;
        }

        public List<MoOneControlListBean> getMoOneControlList() {
            return moOneControlList;
        }

        public void setMoOneControlList(List<MoOneControlListBean> moOneControlList) {
            this.moOneControlList = moOneControlList;
        }

        public List<MoDoubleControlListBean> getMoDoubleControlList() {
            return moDoubleControlList;
        }

        public void setMoDoubleControlList(List<MoDoubleControlListBean> moDoubleControlList) {
            this.moDoubleControlList = moDoubleControlList;
        }

        public static class MoOneControlListBean {
            /**
             * status : null
             * directive : null
             * sn : 21012C000000037C
             * deviceType : 智能喷雾
             */

            private Object status;
            private Object directive;
            private String sn;
            private String deviceType;

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getDirective() {
                return directive;
            }

            public void setDirective(Object directive) {
                this.directive = directive;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(String deviceType) {
                this.deviceType = deviceType;
            }
        }

        public static class MoDoubleControlListBean {
            /**
             * status : null
             * directive : null
             * sn : 21012C0000000386
             * deviceType : 放风机
             */

            private Object status;
            private Object directive;
            private String sn;
            private String deviceType;

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getDirective() {
                return directive;
            }

            public void setDirective(Object directive) {
                this.directive = directive;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(String deviceType) {
                this.deviceType = deviceType;
            }
        }
    }
}
