package org.paradyne.bean.event;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.paradyne.lib.BeanBase;

public class CeoConfiguration extends BeanBase {
	
	private String empName ="";
	private String empCode ="";
	private String empToken ="";
	private LinkedHashSet  list = null ; 
	private String  srNo ="";
	private String  empCodeItt ="";
	private String  empNameItt ="";
	private String empTokenItt = "";
	private String hiddenDelete ="";
	private String ceoTypeItt ="";
	private String ceoType="";
	private String ceoTypeValueItt ="";
	private String showCeo ="true";
	private String ceoDivName="";
	private String ceoDivCode="";
	
	public String getShowCeo() {
		return showCeo;
	}
	public void setShowCeo(String showCeo) {
		this.showCeo = showCeo;
	}
	public String getCeoTypeValueItt() {
		return ceoTypeValueItt;
	}
	public void setCeoTypeValueItt(String ceoTypeValueItt) {
		this.ceoTypeValueItt = ceoTypeValueItt;
	}
	public String getCeoType() {
		return ceoType;
	}
	public void setCeoType(String ceoType) {
		this.ceoType = ceoType;
	}
	public String getCeoTypeItt() {
		return ceoTypeItt;
	}
	public void setCeoTypeItt(String ceoTypeItt) {
		this.ceoTypeItt = ceoTypeItt;
	}
	public String getHiddenDelete() {
		return hiddenDelete;
	}
	public void setHiddenDelete(String hiddenDelete) {
		this.hiddenDelete = hiddenDelete;
	}
	public String getEmpTokenItt() {
		return empTokenItt;
	}
	public void setEmpTokenItt(String empTokenItt) {
		this.empTokenItt = empTokenItt;
	}
	 
	public LinkedHashSet getList() {
		return list;
	}
	public void setList(LinkedHashSet list) {
		this.list = list;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public String getEmpCodeItt() {
		return empCodeItt;
	}
	public void setEmpCodeItt(String empCodeItt) {
		this.empCodeItt = empCodeItt;
	}
	public String getEmpNameItt() {
		return empNameItt;
	}
	public void setEmpNameItt(String empNameItt) {
		this.empNameItt = empNameItt;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpToken() {
		return empToken;
	}
	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}
	public String getCeoDivName() {
		return ceoDivName;
	}
	public void setCeoDivName(String ceoDivName) {
		this.ceoDivName = ceoDivName;
	}
	public String getCeoDivCode() {
		return ceoDivCode;
	}
	public void setCeoDivCode(String ceoDivCode) {
		this.ceoDivCode = ceoDivCode;
	}

}
