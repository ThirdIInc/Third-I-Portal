package org.paradyne.bean.payroll;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;

import org.paradyne.lib.BeanBase;

public class OtParaMaster extends BeanBase implements Serializable  {
	
	private String otId="";
	private String typeCode="";
	private String typeName="";
	private String normalCalSingle="";
	private String normalCalDouble="";
	private String maximumCap="";
	private String otPolicy="";
	private String minimumFloor="";
	private String holiCalSingle="";
	private String holiCalDouble="";
	private String flag="false";
	private ArrayList otParaArray=null;
	
	
	
	
	
	public OtParaMaster()
	{
		
	}
	
	
	
	public String getTypeCode() {
		return typeCode;
	}



	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}



	public String getTypeName() {
		return typeName;
	}



	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}



	public String getHoliCalDouble() {
		return holiCalDouble;
	}
	public void setHoliCalDouble(String holiCalDouble) {
		this.holiCalDouble = holiCalDouble;
	}
	public String getHoliCalSingle() {
		return holiCalSingle;
	}
	public void setHoliCalSingle(String holiCalSingle) {
		this.holiCalSingle = holiCalSingle;
	}
	public String getMaximumCap() {
		return maximumCap;
	}
	public void setMaximumCap(String maximumCap) {
		this.maximumCap = maximumCap;
	}
	public String getMinimumFloor() {
		return minimumFloor;
	}
	public void setMinimumFloor(String minimumFloor) {
		this.minimumFloor = minimumFloor;
	}
	public String getNormalCalDouble() {
		return normalCalDouble;
	}
	public void setNormalCalDouble(String normalCalDouble) {
		this.normalCalDouble = normalCalDouble;
	}
	public String getNormalCalSingle() {
		return normalCalSingle;
	}
	public void setNormalCalSingle(String normalCalSingle) {
		this.normalCalSingle = normalCalSingle;
	}
	public String getOtPolicy() {
		return otPolicy;
	}
	public void setOtPolicy(String otPolicy) {
		this.otPolicy = otPolicy;
	}


	public String getOtId() {
		return otId;
	}


	public void setOtId(String otId) {
		this.otId = otId;
	}


	public ArrayList getOtParaArray() {
		return otParaArray;
	}


	public void setOtParaArray(ArrayList otParaArray) {
		this.otParaArray = otParaArray;
	}



	public String getFlag() {
		return flag;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
	