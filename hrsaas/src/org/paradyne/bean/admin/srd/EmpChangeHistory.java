/**
 * 
 */
package org.paradyne.bean.admin.srd;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author balajim
 *
 */
public class EmpChangeHistory extends BeanBase {

	 private String employeeToken;
	 private String empName;
	 private String type;
	 private String oldValue;
	 private String newValue;
	 private String authNO;
	 private String authDate;
	 private String empID;
	 private String flag;
	 private String flagList;
	 private String  cityID;
	 private String cityName;
	 private String center;
	 private String rank;
	 private String flagType="false";
	 private String flagGen="false";
	 
	 
	 ArrayList<Object> tableList ;
	 
	 
	public String getAuthDate() {
		return authDate;
	}
	public void setAuthDate(String authDate) {
		this.authDate = authDate;
	}
	public String getAuthNO() {
		return authNO;
	}
	public void setAuthNO(String authNO) {
		this.authNO = authNO;
	}
	public String getEmployeeToken() {
		return employeeToken;
	}
	public void setEmployeeToken(String employeeToken) {
		this.employeeToken = employeeToken;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public ArrayList<Object> getTableList() {
		return tableList;
	}
	public void setTableList(ArrayList<Object> tableList) {
		this.tableList = tableList;
	}
	public String getFlagList() {
		return flagList;
	}
	public void setFlagList(String flagList) {
		this.flagList = flagList;
	}
	public String getCityID() {
		return cityID;
	}
	public void setCityID(String cityID) {
		this.cityID = cityID;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getFlagType() {
		return flagType;
	}
	public void setFlagType(String flagType) {
		this.flagType = flagType;
	}
	public String getFlagGen() {
		return flagGen;
	}
	public void setFlagGen(String flagGen) {
		this.flagGen = flagGen;
	}
}
