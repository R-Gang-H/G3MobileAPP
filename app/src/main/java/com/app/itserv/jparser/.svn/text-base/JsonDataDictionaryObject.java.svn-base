package com.app.itserv.jparser;

import java.io.Serializable;
import java.util.List;

/**
 * 数据字典 bean
 *
 * @author yiqiang
 */
public class JsonDataDictionaryObject {
    /**
     * attributes : {"typeListSize":8} obj :
     * [{"tstypes":null,"createName":"管理员",
     * "createDate":1497347217000,"typecode":
     * "seed","typename":"播种","tstype":null
     * ,"tstypegroup":null,"id":"bbeeb6885ca0433b015ca0d9598f0002"
     * },{"tstypes":null
     * ,"createName":"管理员","createDate":1497347240000,"typecode"
     * :"irrigate","typename":"灌溉","tstype":null,"tstypegroup":null,"id":
     * "bbeeb6885ca0433b015ca0d9b0e60004"
     * },{"tstypes":null,"createName":"管理员","createDate"
     * :1497347280000,"typecode"
     * :"spray_insecticide","typename":"打药","tstype":null
     * ,"tstypegroup":null,"id"
     * :"bbeeb6885ca0433b015ca0da4e3f0006"},{"tstypes":null
     * ,"createName":"管理员","createDate"
     * :1497347330000,"typecode":"pluck","typename"
     * :"采摘","tstype":null,"tstypegroup"
     * :null,"id":"bbeeb6885ca0433b015ca0db10560008"
     * },{"tstypes":null,"createName"
     * :"管理员","createDate":1497347349000,"typecode"
     * :"other","typename":"其它","tstype"
     * :null,"tstypegroup":null,"id":"bbeeb6885ca0433b015ca0db5b0b000a"
     * },{"tstypes"
     * :null,"createName":"管理员","createDate":1497405856000,"typecode"
     * :"air","typename":"放风","tstype":null,"tstypegroup":null,"id":
     * "bbeeb6885ca0433b015ca4581a8a0019"
     * },{"tstypes":null,"createName":"管理员","createDate"
     * :1497405900000,"typecode"
     * :"weeding","typename":"除草","tstype":null,"tstypegroup"
     * :null,"id":"bbeeb6885ca0433b015ca458c6c7001b"
     * },{"tstypes":null,"createName"
     * :"管理员","createDate":1497405958000,"typecode"
     * :"truck_loading","typename":"装车","tstype":null,"tstypegroup":null,"id":
     * "bbeeb6885ca0433b015ca459aabe001d"}] msg : 操作成功 success : true
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
         * typeListSize : 8
         */

        private int typeListSize;

        public int getTypeListSize() {
            return typeListSize;
        }

        public void setTypeListSize(int typeListSize) {
            this.typeListSize = typeListSize;
        }
    }

    public static class ObjBean implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        /**
         * tstypes : null createName : 管理员 createDate : 1497347217000 typecode :
         * seed typename : 播种 tstype : null tstypegroup : null id :
         * bbeeb6885ca0433b015ca0d9598f0002
         */

        private Object tstypes;
        private String createName;
        private long createDate;
        private String typecode;
        private String typename;
        private Object tstype;
        private Object tstypegroup;
        private String id;

        public Object getTstypes() {
            return tstypes;
        }

        public void setTstypes(Object tstypes) {
            this.tstypes = tstypes;
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

        public String getTypecode() {
            return typecode;
        }

        public void setTypecode(String typecode) {
            this.typecode = typecode;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public Object getTstype() {
            return tstype;
        }

        public void setTstype(Object tstype) {
            this.tstype = tstype;
        }

        public Object getTstypegroup() {
            return tstypegroup;
        }

        public void setTstypegroup(Object tstypegroup) {
            this.tstypegroup = tstypegroup;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
