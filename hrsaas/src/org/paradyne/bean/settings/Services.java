/**
 * 
 */
package org.paradyne.bean.settings;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author prakashs
 *
 */
public class Services extends BeanBase {
	private String serviceCode;
	private String subheadName;
	private String serviceName;
	private String serviceLink;
	private String serviceLinkCode;
	private String serviceLinkcode;
	private String srNo;
	private String chkfield ="";
	private ArrayList list=getList();
	public String getServiceLinkCode() {
		return serviceLinkCode;
	}
	public void setServiceLinkCode(String serviceLinkCode) {
		this.serviceLinkCode = serviceLinkCode;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	/**
	 * @return the serviceCode
	 */
	public String getServiceCode() {
		return serviceCode;
	}
	/**
	 * @param serviceCode the serviceCode to set
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	/**
	 * @return the subheadName
	 */
	public String getSubheadName() {
		return subheadName;
	}
	/**
	 * @param subheadName the subheadName to set
	 */
	public void setSubheadName(String subheadName) {
		this.subheadName = subheadName;
	}
	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * @return the serviceLink
	 */
	public String getServiceLink() {
		return serviceLink;
	}
	/**
	 * @param serviceLink the serviceLink to set
	 */
	public void setServiceLink(String serviceLink) {
		this.serviceLink = serviceLink;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getServiceLinkcode() {
		return serviceLinkcode;
	}
	public void setServiceLinkcode(String serviceLinkcode) {
		this.serviceLinkcode = serviceLinkcode;
	}
	public String getChkfield() {
		return chkfield;
	}
	public void setChkfield(String chkfield) {
		this.chkfield = chkfield;
	}

}
