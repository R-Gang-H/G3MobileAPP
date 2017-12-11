package com.app.itserv.manager;

/**
 * Created by Administrator on 2017/10/9.
 * 数据字典查询类
 */

public class DataWordQuery {

    private static DataWordQuery instance;

    public DataWordQuery() {
        super();
    }

    public static DataWordQuery getInstance() {
        if (instance == null) {
            instance = new DataWordQuery();
        }
        return instance;
    }

    /**
     * 执行频率
     *
     * @param execuCycle
     * @return
     */
    public String getexecuCycle(String execuCycle) {
        String execuText = "";
        if (execuCycle.equals("ONCE")) {//一次性
            execuText = "一次性";
        } else if (execuCycle.equals("DAILY")) {
            execuText = "每日";
        } else if (execuCycle.equals("WEEKLY")) {
            execuText = "每周";
        } else if (execuCycle.equals("MONTHLY")) {
            execuText = "每月";
        }
        return execuText;
    }

    /**
     * 农事分类
     *
     * @param farType
     * @return
     */
    public String getfarType(String farType) {
        String farClass = "";
        if (farType.equals("seed")) {
            farClass = "播种";
        } else if (farType.equals("irrigate")) {
            farClass = "灌溉";
        } else if (farType.equals("spray_insecticide")) {
            farClass = "打药";
        } else if (farType.equals("pluck")) {
            farClass = "采摘";
        } else if (farType.equals("other")) {
            farClass = "其他";
        } else if (farType.equals("air")) {
            farClass = "放风";
        } else if (farType.equals("weeding")) {
            farClass = "除草";
        } else if (farType.equals("truck_loading")) {
            farClass = "装车";
        }
        return farClass;
    }

    /**
     * 分配状态
     *
     * @param assignStatus
     * @return
     */
    public String getassignStatus(String assignStatus) {
        String allocated = "";
        if (assignStatus.equals("UNDISTRIBUTED")) {
            allocated = "未分配";
        } else if (assignStatus.equals("ALLOCATED")) {
            allocated = "已分配";
        } else if (assignStatus.equals("CANCELED")) {
            allocated = "已取消";
        } else if (assignStatus.equals("CLOSED")) {
            allocated = "已关闭";
        }
        return allocated;
    }

    /**
     * 任务状态
     *
     * @param tastExecuStatus
     * @return
     */
    public String gettastExecuStatus(String tastExecuStatus) {
        String taskExecStatus = "";
        if (tastExecuStatus.equals("ASSIGNED")) {
            taskExecStatus = "已分配";
        } else if (taskExecStatus.equals("IN_EXECUTION")) {
            taskExecStatus = "执行中";
        } else if (taskExecStatus.equals("CLOSED")) {
            taskExecStatus = "已关闭";
        } else if (taskExecStatus.equals("CANCELED")) {
            taskExecStatus = "已取消";
        }
        return taskExecStatus;
    }

    /**
     * 操作类型
     *
     * @param operaAction
     * @return
     */
    public String getoperaType(String operaAction) {
        String operaType = null;
        if (operaAction.equals("CREATE")) {
            operaType = "创建";
        } else if (operaAction.equals("EDIT")) {
            operaType = "编辑";
        } else if (operaAction.equals("ASSIGN")) {
            operaType = "分配";
        } else if (operaAction.equals("ACCEPT")) {
            operaType = "领用";
        } else if (operaAction.equals("CLOSE")) {
            operaType = "关闭";
        } else if (operaAction.equals("CANCEL")) {
            operaType = "取消";
        }
        return operaType;
    }

}
