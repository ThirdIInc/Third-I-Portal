/**
 * 
 */
package org.paradyne.bean.exam;

import org.paradyne.lib.BeanBase;

/**
 * @author diptip
 *
 */
public class CandidateScheduleReport extends BeanBase {

	/**
	 * 
	 */
	private String  fromDate="";
	private String  toDate="";
	private String paperCode="";
	private String paperName="";
	
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
}
