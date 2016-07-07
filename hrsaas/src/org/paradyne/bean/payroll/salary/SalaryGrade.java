/**
 * 
 */
package org.paradyne.bean.payroll.salary;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author varunk
 * @modified ayyappa
 *
 */
public class SalaryGrade extends BeanBase {
	
	private String gradeCode="";
	private String gradeName="";
	private String creditCode="";
	private String salAmt="";
	private String creditName="";
	
	private String myPage ;
	private String hiddencode;
	private String show;
	
	private String totalRecords;
	private String listLength;
	
	ArrayList salGradeArray=null;
	
	ArrayList iteratorlist;
	
	ArrayList creditlist;
	
	private String hdeleteCode;
	
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getSalAmt() {
		return salAmt;
	}
	public void setSalAmt(String salAmt) {
		this.salAmt = salAmt;
	}
	public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public String getListLength() {
		return listLength;
	}
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}
	public ArrayList getSalGradeArray() {
		return salGradeArray;
	}
	public void setSalGradeArray(ArrayList salGradeArray) {
		this.salGradeArray = salGradeArray;
	}
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	public String getHdeleteCode() {
		return hdeleteCode;
	}
	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
	public ArrayList getCreditlist() {
		return creditlist;
	}
	public void setCreditlist(ArrayList creditlist) {
		this.creditlist = creditlist;
	}
	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

}
