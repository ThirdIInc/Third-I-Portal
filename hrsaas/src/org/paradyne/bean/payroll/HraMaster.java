package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class HraMaster extends BeanBase implements Serializable  {
	String hraParameterClass = "";
	String hraParameterName = "";
	String hraParameterPercentage = "";
	String hraParameterCode = "";
	ArrayList dispArray=null;
	
	public HraMaster()
	{}

	public String getHraParameterCode() {
		return hraParameterCode;
	}

	public void setHraParameterCode(String hraParameterCode) {
		this.hraParameterCode = hraParameterCode;
	}

	public String getHraParameterName() {
		return hraParameterName;
	}

	public void setHraParameterName(String hraParameterName) {
		this.hraParameterName = hraParameterName;
	}

	public String getHraParameterPercentage() {
		return hraParameterPercentage;
	}

	public void setHraParameterPercentage(String hraParameterPercentage) {
		this.hraParameterPercentage = hraParameterPercentage;
	}

	public String getHraParameterClass() {
		return hraParameterClass;
	}

	public HraMaster(String hraParameterClass, String hraParameterName, String hraParameterPercentage, String hraParameterCode, ArrayList dispArray) {
		super();
		this.hraParameterClass = hraParameterClass;
		this.hraParameterName = hraParameterName;
		this.hraParameterPercentage = hraParameterPercentage;
		this.hraParameterCode = hraParameterCode;
		this.dispArray = dispArray;
	}

	public  void setHraParameterClass(String hraParameterClass) {
		this.hraParameterClass = hraParameterClass;
	}

	

}
