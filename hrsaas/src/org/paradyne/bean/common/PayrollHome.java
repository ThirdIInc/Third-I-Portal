package org.paradyne.bean.common;
import java.util.*;

import org.paradyne.lib.BeanBase;
/*
 * author:Pradeep Kumar Sahoo
 * Date:18.01.2008
 */

public class PayrollHome extends BeanBase {
	
	String description;
	String dueDate;
	String pending;
	String name;
	String pendingSince;
	String subject;
	String date;
	
	ArrayList list;
	ArrayList list1;
	ArrayList list2;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getPending() {
		return pending;
	}
	public void setPending(String pending) {
		this.pending = pending;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPendingSince() {
		return pendingSince;
	}
	public void setPendingSince(String pendingSince) {
		this.pendingSince = pendingSince;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public ArrayList getList1() {
		return list1;
	}
	public void setList1(ArrayList list1) {
		this.list1 = list1;
	}
	public ArrayList getList2() {
		return list2;
	}
	public void setList2(ArrayList list2) {
		this.list2 = list2;
	}
	
	
	
	

}
