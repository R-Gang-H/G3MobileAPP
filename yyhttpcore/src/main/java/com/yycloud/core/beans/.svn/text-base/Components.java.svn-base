package com.yycloud.core.beans;

import com.yycloud.core.config.Constants;

import java.io.Serializable;
import java.util.List;

public class Components implements Serializable {

	private static final long serialVersionUID = 1L;
	// private Online online;
	// private String temperature;
	// private String humidity;
	// private String component_token;
	// private String component_id;
	// private String vendor;
	// private String update_at;
	// "remote":"0",
	private String charge;

	private String charge_state;
	private String dev_alias;
	private String dev_extend_type;
	private String dev_name;
	private String dev_type;
	private String online_state;
	private String owner;
	private String password;
	private String power;
	private String charge_total;
	private String remote;
	private String server_time;
	private String update_time;
	private String sn;
	private ConfigInfoBean config_info;
	private String status;
	private String switchs;
	private String user;

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getCharge_total() {
		return charge_total;
	}

	public void setCharge_total(String charge_total) {
		this.charge_total = charge_total;
	}


	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}


	@Override
	public String toString() {
		return "Components [charge=" + charge + ", charge_state="
				+ charge_state + ", dev_alias=" + dev_alias
				+ ", dev_extend_type=" + dev_extend_type + ", dev_name="
				+ dev_name + ", dev_type=" + dev_type + ", online_state="
				+ online_state + ", owner=" + owner + ", password=" + password
				+ ", remote=" + remote + ", server_time=" + server_time
				+ ", sn=" + sn + ", status=" + status + ", switchs=" + switchs
				+ ", user=" + user + ", air_temperature=" + air_temperature
				+ ", air_humidity=" + air_humidity + ", soil_temperature="
				+ soil_temperature + ", soil_humidity=" + soil_humidity
				+ ", lux=" + lux + ", co2_ppm=" + co2_ppm + ", smoke=" + smoke
				+ ", pool_list=" + pool_list + "]";
	}

	// 空气温湿度
	private String air_temperature;
	private String air_humidity;
	// 土壤
	private String soil_temperature;
	private String soil_humidity;
	// 光照
	private String lux;

	private String co2_ppm;

	// 烟雾
	private String smoke;

	public String getCo2_ppm() {
		return co2_ppm;
	}

	public void setCo2_ppm(String co2_ppm) {
		this.co2_ppm = co2_ppm;
	}

	public ConfigInfoBean getConfig_info() {
		return config_info;
	}

	public void setConfig_info(ConfigInfoBean config_info) {
		this.config_info = config_info;
	}

	public static class ConfigInfoBean implements Serializable{
		/**
		 * cp : 1117 ct : 4.0 eth : 7 etm : 41 maxc : 1117 minc : 1117 mt : 80
		 * ot : 6.0 pd : 1 rt : 0 sl : 10 sth : 3 stm : 0 th :
		 * [{"dn":"空气温湿度传感器1"
		 * ,"icd":"1","sn":"01012C000000031D"},{"dn":"空气温湿度传感器2"
		 * ,"icd":"1","sn":"01012C0000000354"}] tl : 80
		 *
		 * "eth1": "", "eth2": "", "eth3": "", "eth4": "", "eth5": "", "etm1":
		 * "", "etm2": "", "etm3": "", "etm4": "", "etm5": "", "isauto": "",
		 * "slh": "", "sth1": "", "sth2": "", "sth3": "", "sth4": "", "sth5":
		 * "", "stm1": "", "stm2": "", "stm3": "", "stm4": "", "stm5": "","tlh":
		 * ""
		 */
		private String cp;
		private String ct;
		private String eth;
		private String etm;
		private String maxc;
		private String minc;
		private String mt;
		private String ot;
		private String pd;
		private String rt;
		private String sl;
		private String sth;
		private String stm;
		private String tl;
		private List<ThBean> th;
		private String eth1;
		private String eth2;
		private String eth3;
		private String eth4;
		private String eth5;
		private String etm1;
		private String etm2;
		private String etm3;
		private String etm4;
		private String etm5;
		private String isauto;
		private String slh;
		private String sth1;
		private String sth2;
		private String sth3;
		private String sth4;
		private String sth5;
		private String stm1;
		private String stm2;
		private String stm3;
		private String stm4;
		private String stm5;
		private String tlh;
		private String version;
		private String vcc_min; // 最小值 add by jcy 20170726
		private String vcc_max; // 最大值 add by jcy 20170726
		private String vcc_current; // 当前值 add by jcy 20170726
		private String pt; // 百分比
		private String percent; // 百分比 add by jcy 20170726
		private String result; // 校准 add by jcy 20170726
		private boolean istemcontrol; // true是 false不是 时间控制

		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}

		public String getPt() {
			return pt;
		}

		public void setPt(String pt) {
			this.pt = pt;
		}

		public String getPercent() {
			return percent;
		}

		public void setPercent(String percent) {
			this.percent = percent;
		}

		public String getVcc_current() {
			return vcc_current;
		}

		public void setVcc_current(String vcc_current) {
			this.vcc_current = vcc_current;
		}

		public String getVcc_max() {
			return vcc_max;
		}

		public void setVcc_max(String vcc_max) {
			this.vcc_max = vcc_max;
		}

		public String getVcc_min() {
			return vcc_min;
		}

		public void setVcc_min(String vcc_min) {
			this.vcc_min = vcc_min;
		}


		public boolean isIstemcontrol() {
			return istemcontrol;
		}

		public void setIstemcontrol(boolean istemcontrol) {
			this.istemcontrol = istemcontrol;
		}


		public String getEth1() {
			return eth1;
		}

		public void setEth1(String eth1) {
			this.eth1 = eth1;
		}

		public String getEth2() {
			return eth2;
		}

		public void setEth2(String eth2) {
			this.eth2 = eth2;
		}

		public String getEth3() {
			return eth3;
		}

		public void setEth3(String eth3) {
			this.eth3 = eth3;
		}

		public String getEth4() {
			return eth4;
		}

		public void setEth4(String eth4) {
			this.eth4 = eth4;
		}

		public String getEth5() {
			return eth5;
		}

		public void setEth5(String eth5) {
			this.eth5 = eth5;
		}

		public String getEtm1() {
			return etm1;
		}

		public void setEtm1(String etm1) {
			this.etm1 = etm1;
		}

		public String getEtm2() {
			return etm2;
		}

		public void setEtm2(String etm2) {
			this.etm2 = etm2;
		}

		public String getEtm3() {
			return etm3;
		}

		public void setEtm3(String etm3) {
			this.etm3 = etm3;
		}

		public String getEtm4() {
			return etm4;
		}

		public void setEtm4(String etm4) {
			this.etm4 = etm4;
		}

		public String getEtm5() {
			return etm5;
		}

		public void setEtm5(String etm5) {
			this.etm5 = etm5;
		}

		public String getIsauto() {
			return isauto;
		}

		public void setIsauto(String isauto) {
			this.isauto = isauto;
		}

		public String getSlh() {
			return slh;
		}

		public void setSlh(String slh) {
			this.slh = slh;
		}

		public String getSth1() {
			return sth1;
		}

		public void setSth1(String sth1) {
			this.sth1 = sth1;
		}

		public String getSth2() {
			return sth2;
		}

		public void setSth2(String sth2) {
			this.sth2 = sth2;
		}

		public String getSth3() {
			return sth3;
		}

		public void setSth3(String sth3) {
			this.sth3 = sth3;
		}

		public String getSth4() {
			return sth4;
		}

		public void setSth4(String sth4) {
			this.sth4 = sth4;
		}

		public String getSth5() {
			return sth5;
		}

		public void setSth5(String sth5) {
			this.sth5 = sth5;
		}

		public String getStm1() {
			return stm1;
		}

		public void setStm1(String stm1) {
			this.stm1 = stm1;
		}

		public String getStm2() {
			return stm2;
		}

		public void setStm2(String stm2) {
			this.stm2 = stm2;
		}

		public String getStm3() {
			return stm3;
		}

		public void setStm3(String stm3) {
			this.stm3 = stm3;
		}

		public String getStm4() {
			return stm4;
		}

		public void setStm4(String stm4) {
			this.stm4 = stm4;
		}

		public String getStm5() {
			return stm5;
		}

		public void setStm5(String stm5) {
			this.stm5 = stm5;
		}

		public String getTlh() {
			return tlh;
		}

		public void setTlh(String tlh) {
			this.tlh = tlh;
		}

		public String getCp() {
			return cp;
		}

		public void setCp(String cp) {
			this.cp = cp;
		}

		public String getCt() {
			return ct;
		}

		public void setCt(String ct) {
			this.ct = ct;
		}

		public String getEth() {
			return eth;
		}

		public void setEth(String eth) {
			this.eth = eth;
		}

		public String getEtm() {
			return etm;
		}

		public void setEtm(String etm) {
			this.etm = etm;
		}

		public String getMaxc() {
			return maxc;
		}

		public void setMaxc(String maxc) {
			this.maxc = maxc;
		}

		public String getMinc() {
			return minc;
		}

		public void setMinc(String minc) {
			this.minc = minc;
		}

		public String getMt() {
			return mt;
		}

		public void setMt(String mt) {
			this.mt = mt;
		}

		public String getOt() {
			return ot;
		}

		public void setOt(String ot) {
			this.ot = ot;
		}

		public String getPd() {
			return pd;
		}

		public void setPd(String pd) {
			this.pd = pd;
		}

		public String getRt() {
			return rt;
		}

		public void setRt(String rt) {
			this.rt = rt;
		}

		public String getSl() {
			return sl;
		}

		public void setSl(String sl) {
			this.sl = sl;
		}

		public String getSth() {
			return sth;
		}

		public void setSth(String sth) {
			this.sth = sth;
		}

		public String getStm() {
			return stm;
		}

		public void setStm(String stm) {
			this.stm = stm;
		}

		public String getTl() {
			return tl;
		}

		public void setTl(String tl) {
			this.tl = tl;
		}

		public List<ThBean> getTh() {
			return th;
		}

		public void setTh(List<ThBean> th) {
			this.th = th;
		}

		public static class ThBean  implements Serializable{
			/**
			 * dn : 空气温湿度传感器1 icd : 1 sn : 01012C000000031D
			 */

			private String dn;
			private String icd;
			private String sn;

			public String getDn() {
				return dn;
			}

			public void setDn(String dn) {
				this.dn = dn;
			}

			public String getIcd() {
				return icd;
			}

			public void setIcd(String icd) {
				this.icd = icd;
			}

			public String getSn() {
				return sn;
			}

			public void setSn(String sn) {
				this.sn = sn;
			}
		}
	}

	private List<Pool_list> pool_list;

	public String getRemote() {
		return remote;
	}

	public void setRemote(String remote) {
		this.remote = remote;
	}

	public String getServer_time() {
		return server_time;
	}

	public void setServer_time(String server_time) {
		this.server_time = server_time;
	}

	public List<Pool_list> getPool_list() {
		return pool_list;
	}

	public void setPool_list(List<Pool_list> pool_list) {
		this.pool_list = pool_list;
	}

	public String getSwitchs() {
		return switchs;
	}

	public void setSwitchs(String switchs) {
		this.switchs = switchs;
	}

	public String getAir_temperature() {
		return air_temperature;
	}

	public void setAir_temperature(String air_temperature) {
		this.air_temperature = air_temperature;
	}

	public String getAir_humidity() {
		return air_humidity;
	}

	public void setAir_humidity(String air_humidity) {
		this.air_humidity = air_humidity;
	}

	public String getSoil_temperature() {
		return soil_temperature;
	}

	public void setSoil_temperature(String soil_temperature) {
		this.soil_temperature = soil_temperature;
	}

	public String getSoil_humidity() {
		return soil_humidity;
	}

	public void setSoil_humidity(String soil_humidity) {
		this.soil_humidity = soil_humidity;
	}

	public String getLux() {
		return lux;
	}

	public void setLux(String lux) {
		this.lux = lux;
	}

	public String getSmoke() {
		return smoke;
	}

	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}

	/**
	 * @return the dev_alias
	 */
	public String getDev_alias() {
		return dev_alias;
	}

	/**
	 * @param dev_alias
	 *            the dev_alias to set
	 */
	public void setDev_alias(String dev_alias) {
		this.dev_alias = dev_alias;
	}

	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * @param sn
	 *            the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the dev_name
	 */
	public String getDev_name() {
		return dev_name;
	}

	/**
	 * @param dev_name
	 *            the dev_name to set
	 */
	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}

	/**
	 * @return the dev_type
	 */
	public String getDev_type() {
		return dev_type;
	}

	/**
	 * @param dev_type
	 *            the dev_type to set
	 */
	public void setDev_type(String dev_type) {
		this.dev_type = dev_type;
	}

	/**
	 * @return the charge_state
	 */
	public String getCharge_state() {
		return charge_state;
	}

	/**
	 * @param charge_state
	 *            the charge_state to set
	 */
	public void setCharge_state(String charge_state) {
		this.charge_state = charge_state;
	}

	/**
	 * @return the online_state
	 */
	public String getOnline_state() {
		if (null == online_state || "".equals(online_state))
			online_state = Constants.STATUS_OFFLINE;
		return online_state;
	}

	/**
	 * @param online_state
	 *            the online_state to set
	 */
	public void setOnline_state(String online_state) {
		if (null == online_state || "".equals(online_state))
			online_state = Constants.STATUS_OFFLINE;
		this.online_state = online_state;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * public String getUpdate_at() { return update_at; }
	 *
	 * public void setUpdate_at(String update_at) { this.update_at = update_at;
	 * }
	 */

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getDev_extend_type() {
		return dev_extend_type;
	}

	public void setDev_extend_type(String dev_extend_type) {
		this.dev_extend_type = dev_extend_type;
	}

	public class Pool_list implements Serializable {
		private String dev_extend_type;

		private String dev_name;

		private String dev_type;

		private int pool_index;

		private int status;

		public void setDev_extend_type(String dev_extend_type) {
			this.dev_extend_type = dev_extend_type;
		}

		public String getDev_extend_type() {
			return this.dev_extend_type;
		}

		public void setDev_name(String dev_name) {
			this.dev_name = dev_name;
		}

		public String getDev_name() {
			return this.dev_name;
		}

		public void setDev_type(String dev_type) {
			this.dev_type = dev_type;
		}

		public String getDev_type() {
			return this.dev_type;
		}

		public void setPool_index(int pool_index) {
			this.pool_index = pool_index;
		}

		public int getPool_index() {
			return this.pool_index;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public int getStatus() {
			return this.status;
		}

	}
}
