package com.itserv.app.bean;

/**
 *  * @Description:授权网关属性
 *  * @author:axin
 *  * @time:2016-11-25 下午4:05:05
 */
public class SmartLimit {
    private String smartname;
    private String smartsn;
    private String smartVisitorTag;

    public SmartLimit() {
        super();
    }

    public String getSmartname() {
        return smartname;
    }

    public void setSmartname(String smartname) {
        this.smartname = smartname;
    }

    public String getSmartsn() {
        return smartsn;
    }

    public void setSmartsn(String smartsn) {
        this.smartsn = smartsn;
    }

    public String getSmartVisitorTag() {
        return smartVisitorTag;
    }

    public void setSmartVisitorTag(String smartVisitorTag) {
        this.smartVisitorTag = smartVisitorTag;
    }

}
