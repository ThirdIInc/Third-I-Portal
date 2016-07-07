package org.paradyne.bean.vas;

import org.paradyne.lib.BeanBase;

public class VASHome extends BeanBase {

	private String clientCode="";
	private String serviceCode="";

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
}
