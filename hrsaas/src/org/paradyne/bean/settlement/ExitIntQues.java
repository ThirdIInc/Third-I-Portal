package org.paradyne.bean.settlement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.paradyne.lib.BeanBase;

/**
 * @author Rita
 * 
 */
public class ExitIntQues extends BeanBase {

	private String code;
	private String branch;

	private String designation;
	private String grade;
	private String joinDate;
	private String leaveDate;
	private String resignDate;
	private String sepDate;
	private String empId;
	private String empName;
	private String question;
	private String rating;
	private String questionCode;
	private String questionValue;
	private String exIntcode;
	private String resignId;
	private String resignCode;
	private String exitCode;
	private String ratingCode;
	private String empToken;
	private String quedetails = "0";
	private String QuesDtlflag = "false";
	private String flag = "false";
	private String isInterviewed;
	TreeMap tmap;

	ArrayList tableList = null;

	private String comment;

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}

	public String getQuestionValue() {
		return questionValue;
	}

	public void setQuestionValue(String questionValue) {
		this.questionValue = questionValue;
	}

	public String getExIntcode() {
		return exIntcode;
	}

	public void setExIntcode(String exIntcode) {
		this.exIntcode = exIntcode;
	}

	/**
	 * @return the list
	 * 
	 */
	public ArrayList getTableList() {
		return tableList;
	}

	/**
	 * @param list
	 * 
	 */
	public void setTableList(ArrayList tableList) {
		this.tableList = tableList;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the treeMap
	 * 
	 */
	public TreeMap getTmap() {
		return tmap;
	}

	/**
	 * @param treeMap
	 * 
	 */
	public void setTmap(TreeMap tmap) {
		this.tmap = tmap;
	}

	/**
	 * @return the resignId
	 */
	public String getResignId() {
		return resignId;
	}

	/**
	 * @param resignId
	 *            the resignId to set
	 */
	public void setResignId(String resignId) {
		this.resignId = resignId;
	}

	public String getExitCode() {
		return exitCode;
	}

	public void setExitCode(String exitCode) {
		this.exitCode = exitCode;
	}

	public String getRatingCode() {
		return ratingCode;
	}

	public void setRatingCode(String ratingCode) {
		this.ratingCode = ratingCode;
	}

	public String getEmpToken() {
		return empToken;
	}

	public void setEmpToken(String empToken) {
		this.empToken = empToken;
	}

	public String getQuedetails() {
		return quedetails;
	}

	public void setQuedetails(String quedetails) {
		this.quedetails = quedetails;
	}

	public String getResignCode() {
		return resignCode;
	}

	public void setResignCode(String resignCode) {
		this.resignCode = resignCode;
	}

	public String getQuesDtlflag() {
		return QuesDtlflag;
	}

	public void setQuesDtlflag(String quesDtlflag) {
		QuesDtlflag = quesDtlflag;
	}

	public String getResignDate() {
		return resignDate;
	}

	public void setResignDate(String resignDate) {
		this.resignDate = resignDate;
	}

	public String getSepDate() {
		return sepDate;
	}

	public void setSepDate(String sepDate) {
		this.sepDate = sepDate;
	}

	public String getIsInterviewed() {
		return isInterviewed;
	}

	public void setIsInterviewed(String isInterviewed) {
		this.isInterviewed = isInterviewed;
	}

}
