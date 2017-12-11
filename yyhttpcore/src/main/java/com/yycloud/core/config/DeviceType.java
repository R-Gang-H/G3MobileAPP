package com.yycloud.core.config;

public class DeviceType {
    public static String smartgate = "smartgate"; // smartgate:
    public static String humidity_temperature = "humidity-temperature";
    public static String co = "co";
    public static String co2 = "co2";
    public static String soil_th = "soil-th";
    public static String smoke = "smoke";
    public static String illumination = "illumination";
    public static String erelay = "erelay"; // normal relay
    public static String erelay2 = "erelay2"; // rolling-machine
    public static String camera = "camera"; // camera for taking photos
    public static String cameraip = "cameraip";
    public static String relaybox = "relaybox";//电气柜electric-counter
    public static String erelay_switch = "erelay-switch"; // normal relay开关

    public static String no_extend_type = "";
    public static String erelay2_shutter = "erelay2-shutter";
    public static String erelay_exhaust = "erelay-exhaust";
    public static String erelay_water_valve = "erelay-water-valve";
    public static String erelay_ventilation = "erelay-ventilation";
    public static String erelay_lamp = "erelay-lamp";

    // 以下文字同 res/values/strings中
    public final static String DEVICE_EXTENDTYPE_SHUTTER = "卷帘机"; // 卷帘机
    public final static String DEVICE_EXTENDTYPE_WATER_VALVE = "水阀"; // 水阀
    public final static String DEVICE_EXTENDTYPE_VENTILATION = "通风"; // 通风
    public final static String DEVICE_EXTENDTYPE_LAMP = "灯"; // 灯

    /**
     * get the extend-type by the extend-type-name
     *
     * @param name
     * @return
     */
    public static String getTypeByName(String name) {
        if (name.equals(DEVICE_EXTENDTYPE_SHUTTER)) {
            return erelay2_shutter;
        } else if (name.equals(DEVICE_EXTENDTYPE_WATER_VALVE)) {
            return erelay_water_valve;
        } else if (name.equals(DEVICE_EXTENDTYPE_VENTILATION)) {
            return erelay_ventilation;
        } else if (name.equals(DEVICE_EXTENDTYPE_LAMP)) {
            return erelay_lamp;
        } else {
            return "";
        }
    }

    /**
     * get the default name of some controller by the extend-type
     *
     * @param name
     * @return
     */
    public static String getDefaultNameByExtendType(String name) {
        if (name.equals(erelay2_shutter)) {
            return "卷帘机控制器";
        } else if (name.equals(erelay_water_valve)) {
            return "水阀控制器";
        } else if (name.equals(erelay_ventilation)) {
            return "放风机控制器";
        } else if (name.equals(erelay_lamp)) {
            return "灯光控制器";
        } else if (name.equals("智能排风机")) {
            return "智能排风机";
        } else if (name.equals("无线电气柜")) {
            return "无线电气柜";
        } else {
            return "未知控制器";
        }
    }

}
