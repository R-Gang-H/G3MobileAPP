package com.yycloud.core.beans;

public class AlarmIgnoreBean {
	private String sn;
	private boolean checked;
	//"checked": "false"

	public void setSn(String sn){
	this.sn = sn;
	}
	public String getSn(){
	return this.sn;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return "AlarmIgnoreBean [sn=" + sn + ", checked=" + checked + "]";
	}

}
