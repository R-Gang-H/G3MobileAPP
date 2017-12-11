package com.app.commons;

/**
 * EventBus send to MainActivity
 *
 * @author ted
 */
public class ToUIEvent {

    public static final int NEW_UNREAD_EVENT = 1;        //有新的未读事件
    public static final int SET_EVENT_READ = 2;                //将未读事件状态设为已读，服务器返回设置成功
    public static final int UPDATE_BASEMANAGER = 3; //刷新基地列表
    public static final int UPDATE_EXECUTASKLIST = 4; //刷新执行任务列表
    public static final int UPDATE_MONITORING_INTERFACE =5; //刷新监控列表
    private int what;
    private Object obj;

    public ToUIEvent(int what, Object obj) {
        this.what = what;
        this.obj = obj;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}
