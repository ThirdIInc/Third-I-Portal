package org.paradyne.bean.payroll;
import org.paradyne.lib.BeanBase;
/*
 * author:Pradeep
 * Date:20.03.2008
 */
public class PayCoveringLetter extends BeanBase {
	
	
	String divId;
	String divName;
	String month;
	String year;
	String chq;
	String chqDate;
	public PayCoveringLetter(String divId, String divName, String month,
			String year, String chq, String chqDate) {
		super();
		this.divId = divId;
		this.divName = divName;
		this.month = month;
		this.year = year;
		this.chq = chq;
		this.chqDate = chqDate;
	}
	public PayCoveringLetter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PayCoveringLetter(String divId, String divName, String month,
			String year) {
		super();
		this.divId = divId;
		this.divName = divName;
		this.month = month;
		this.year = year;
	}
	
	
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getChq() {
		return chq;
	}
	public void setChq(String chq) {
		this.chq = chq;
	}
	public String getChqDate() {
		return chqDate;
	}
	public void setChqDate(String chqDate) {
		this.chqDate = chqDate;
	}
	
	
	
	
	
	
	
	

}
