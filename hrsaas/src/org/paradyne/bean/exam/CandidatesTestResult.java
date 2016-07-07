/**
 * 
 */
package org.paradyne.bean.exam;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

/**
 * @author diptip
 *
 */
public class CandidatesTestResult extends BeanBase {

	/**
	 * 
	 */
	private String  fromDate="";
	private String  toDate="";
	private String  paperCode="";
	private String  paperName="";
	private String  ucutoff="";
	private String  lcutoff="";
	private ArrayList list;
	private String  candidateCode="";
	private String  candidateName="";
	private String  testDate="";
	private String  testTime="";
	private String  totScore="";
	private String  result="";
	
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getPaperCode() {
		return paperCode;
	}
	public void setPaperCode(String paperCode) {
		this.paperCode = paperCode;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public String getUcutoff() {
		return ucutoff;
	}
	public void setUcutoff(String ucutoff) {
		this.ucutoff = ucutoff;
	}
	public String getLcutoff() {
		return lcutoff;
	}
	public void setLcutoff(String lcutoff) {
		this.lcutoff = lcutoff;
	}
	
	public ArrayList getList() {
		return list;
	}
	public void setList(ArrayList list) {
		this.list = list;
	}
	public String getCandidateCode() {
		return candidateCode;
	}
	public void setCandidateCode(String candidateCode) {
		this.candidateCode = candidateCode;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
	public String getTestTime() {
		return testTime;
	}
	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}
	public String getTotScore() {
		return totScore;
	}
	public void setTotScore(String totScore) {
		this.totScore = totScore;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

}
