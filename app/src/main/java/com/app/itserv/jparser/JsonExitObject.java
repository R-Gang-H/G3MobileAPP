package com.app.itserv.jparser;

/**
 * attributes : null
 * obj : null
 * success : true
 * msg : OK
 */

/**
 * @project name：yyshed
 * @type name：JsonExitObject
 * @description：登出App Json报文解析类
 * @author：gang
 * @date time：2017-6-9 下午12:35:24
 */
public class JsonExitObject {

    private Object attributes;
    private Object obj;
    private boolean success;
    private String msg;

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
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
}
