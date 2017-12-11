package com.app.itserv.jparser;

import java.util.List;

/**
 * attributes : {"greenhouseUsercSize":19,"currUserId":"402883fd5c8fd7be015c8fe0a84e000a","currTenantId":"402883fd5c8fd7be015c8fde6fd90003"}
 * obj : [{"id":"bbeeb6885d3acf04015d3ec2c0b80029","status":"1","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"402883fd5c8fd7be015c8fe0a84e000a","userName":"cxm","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c0d7002b","status":"1","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"402883fd5c9b5615015c9b59981d0004","userName":"zhangsan","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c0f6002d","status":"0","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015caa8f17620028","userName":"lisiMrs","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c116002f","status":"1","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cb0c7a6d70093","userName":"King","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c1350031","status":"0","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cb0e0021100a5","userName":"King1","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c1540033","status":"0","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cb3ddc1a300ca","userName":"张三","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c1730035","status":"0","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cb3de746900cf","userName":"李四","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c1830037","status":"0","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cb3dfa88400dd","userName":"王五","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c1a20039","status":"0","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cb3f0164500f1","userName":"王小六","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c1c1003b","status":"0","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cb3f7c6f20109","userName":"Kk","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c1e1003d","status":"1","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cb4add12601b6","userName":"张三3","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c200003f","status":"1","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cb4b6dc8401d7","userName":"King4","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c21f0041","status":"1","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cb4bbf98001e5","userName":"King7","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c23e0043","status":"1","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cb4be82dc01f3","userName":"King10","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c25e0045","status":"1","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cb4c5e7280206","userName":"21","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c27d0047","status":"1","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cb4c899b80215","userName":"25","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c29c0049","status":"1","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cb4e28e940224","userName":"4","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c2bb004b","status":"1","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cb505f31c027c","userName":"King瀚","ghouseFullname":"1号大棚（西边）"},{"id":"bbeeb6885d3acf04015d3ec2c2db004d","status":"1","updateDate":null,"updateName":null,"createBy":"cxm","createName":"程晓梅","createDate":1499996537000,"updateBy":null,"tenantId":"402883fd5c8fd7be015c8fde6fd90003","tenantName":"北京平谷大棚基地","ghouseId":"bbeeb6885d305d79015d34d77d41002e","userId":"bbeeb6885ca0433b015cbfd5c1bc032b","userName":"King云","ghouseFullname":"1号大棚（西边）"}]
 * msg : OK
 * success : true
 */

/**
 * 大棚员工管理列表json报文解析类
 *
 * @author haoruigang
 * @Package com.app.itserv.jparser
 * @project yyshed
 * @ClassName: JsonGreHouEmpObject
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-7-13 下午5:19:32
 */
public class JsonGreHouEmpObject {

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
         * greenhouseUsercSize : 19 currUserId :
         * 402883fd5c8fd7be015c8fe0a84e000a currTenantId :
         * 402883fd5c8fd7be015c8fde6fd90003
         */

        private int greenhouseUsercSize;
        private String currUserId;
        private String currTenantId;

        public int getGreenhouseUsercSize() {
            return greenhouseUsercSize;
        }

        public void setGreenhouseUsercSize(int greenhouseUsercSize) {
            this.greenhouseUsercSize = greenhouseUsercSize;
        }

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
