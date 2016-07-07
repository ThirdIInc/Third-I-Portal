/**
 * 
 */
package org.paradyne.bean.admin.srd;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author balajim
 *
 */
public class EmployeeHouse extends BeanBase {
private String empID;
private String employeeToken;
private String empName;
private String center;
private String rank;
private String houseNO;
private String houseColonyID;
private String  houseColonyName;
private String status;
private String houseID;
private String paraId;
private String flag="false";
 

ArrayList<Object> tableList = null;


public ArrayList<Object> getTableList() {
	return tableList;
}
public void setTableList(ArrayList<Object> tableList) {
	this.tableList = tableList;
}
public String getCenter() {
	return center;
}
public void setCenter(String center) {
	this.center = center;
}
public String getEmpID() {
	return empID;
}
public void setEmpID(String empID) {
	this.empID = empID;
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
 
public String getHouseNO() {
	return houseNO;
}
public void setHouseNO(String houseNO) {
	this.houseNO = houseNO;
}
public String getRank() {
	return rank;
}
public void setRank(String rank) {
	this.rank = rank;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getHouseColonyID() {
	return houseColonyID;
}
public void setHouseColonyID(String houseColonyID) {
	this.houseColonyID = houseColonyID;
}
public String getHouseColonyName() {
	return houseColonyName;
}
public void setHouseColonyName(String houseColonyName) {
	this.houseColonyName = houseColonyName;
}
public String getHouseID() {
	return houseID;
}
public void setHouseID(String houseID) {
	this.houseID = houseID;
}
public String getParaId() {
	return paraId;
}
public void setParaId(String paraId) {
	this.paraId = paraId;
}
public String getFlag() {
	return flag;
}
public void setFlag(String flag) {
	this.flag = flag;
}
 
}
