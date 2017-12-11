package hsl.p2pipcam.manager.listener;

import android.graphics.Bitmap;

import java.util.List;

import hsl.p2pipcam.manager.Device;

public interface DeviceStatusListener {
	void receiveDeviceStatus(long userid, int status);
	void receiveDeviceImage(long userid, Bitmap bitmap);
	void loadListData(List<Device> data);
	void receiveDeviceZoneArming(long userid, int zone_arming);
}
