/**
 * 
 */
package org.paradyne.bean.common;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author Pradeep Sahoo
 *
 */
public class HrmHome extends BeanBase {
String tot;
String tot1;
String tot2;

String date;
String subject;
String name;
ArrayList list;
ArrayList incrList;
String empName;
String branch;
String dueDate;
String pendingDays;
private String suggId;
private String suggimprove;
private String suggimple;
private String suggestion;
private String suggDate;
private String empdesg;
private String empbranch;
private String emplName;
private String empdept;
private String dueEmpName="";
private String dueEmpbranch="";
private String dueEmpDate="";
private String pendingSince="";

/**
 * @return the suggId
 */
public String getSuggId() {
	return suggId;
}
/**
 * @param suggId the suggId to set
 */
public void setSuggId(String suggId) {
	this.suggId = suggId;
}
public String getEmpName() {
	return empName;
}
public void setEmpName(String empName) {
	this.empName = empName;
}
public String getBranch() {
	return branch;
}
public void setBranch(String branch) {
	this.branch = branch;
}
public String getDueDate() {
	return dueDate;
}
public void setDueDate(String dueDate) {
	this.dueDate = dueDate;
}
public String getPendingDays() {
	return pendingDays;
}
public void setPendingDays(String pendingDays) {
	this.pendingDays = pendingDays;
}
public ArrayList getIncrList() {
	return incrList;
}
public void setIncrList(ArrayList incrList) {
	this.incrList = incrList;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public ArrayList getList() {
	return list;
}
public void setList(ArrayList list) {
	this.list = list;
}
public String getTot() {
	return tot;
}
public void setTot(String tot) {
	this.tot = tot;
}
public String getTot1() {
	return tot1;
}
public void setTot1(String tot1) {
	this.tot1 = tot1;
}
public String getTot2() {
	return tot2;
}
public void setTot2(String tot2) {
	this.tot2 = tot2;
}
/**
 * @return the suggimprove
 */
public String getSuggimprove() {
	return suggimprove;
}
/**
 * @param suggimprove the suggimprove to set
 */
public void setSuggimprove(String suggimprove) {
	this.suggimprove = suggimprove;
}
/**
 * @return the suggimple
 */
public String getSuggimple() {
	return suggimple;
}
/**
 * @param suggimple the suggimple to set
 */
public void setSuggimple(String suggimple) {
	this.suggimple = suggimple;
}
/**
 * @return the suggestion
 */
public String getSuggestion() {
	return suggestion;
}
/**
 * @param suggestion the suggestion to set
 */
public void setSuggestion(String suggestion) {
	this.suggestion = suggestion;
}
/**
 * @return the suggDate
 */
public String getSuggDate() {
	return suggDate;
}
/**
 * @param suggDate the suggDate to set
 */
public void setSuggDate(String suggDate) {
	this.suggDate = suggDate;
}
/**
 * @return the empdesg
 */
public String getEmpdesg() {
	return empdesg;
}
/**
 * @param empdesg the empdesg to set
 */
public void setEmpdesg(String empdesg) {
	this.empdesg = empdesg;
}
/**
 * @return the empbranch
 */
public String getEmpbranch() {
	return empbranch;
}
/**
 * @param empbranch the empbranch to set
 */
public void setEmpbranch(String empbranch) {
	this.empbranch = empbranch;
}
/**
 * @return the emplName
 */
public String getEmplName() {
	return emplName;
}
/**
 * @param emplName the emplName to set
 */
public void setEmplName(String emplName) {
	this.emplName = emplName;
}
/**
 * @return the empdept
 */
public String getEmpdept() {
	return empdept;
}
/**
 * @param empdept the empdept to set
 */
public void setEmpdept(String empdept) {
	this.empdept = empdept;
}
public String getDueEmpName() {
	return dueEmpName;
}
public void setDueEmpName(String dueEmpName) {
	this.dueEmpName = dueEmpName;
}
public String getDueEmpbranch() {
	return dueEmpbranch;
}
public void setDueEmpbranch(String dueEmpbranch) {
	this.dueEmpbranch = dueEmpbranch;
}
public String getDueEmpDate() {
	return dueEmpDate;
}
public void setDueEmpDate(String dueEmpDate) {
	this.dueEmpDate = dueEmpDate;
}
public String getPendingSince() {
	return pendingSince;
}
public void setPendingSince(String pendingSince) {
	this.pendingSince = pendingSince;
}


}
