/**
 * 
 */
package org.paradyne.bean.admin.srd;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0554
 *
 */
public class EmpCountSummary extends BeanBase {

	private String divName;
	private String divCode;
	private String horizontalPara;
	private String verticalPara;
	private String asOnDate;
	private String reportType;
	
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getDivCode() {
		return divCode;
	}
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}
	
	public String getHorizontalPara() {
		return horizontalPara;
	}
	public void setHorizontalPara(String horizontalPara) {
		this.horizontalPara = horizontalPara;
	}
	public String getVerticalPara() {
		return verticalPara;
	}
	public void setVerticalPara(String verticalPara) {
		this.verticalPara = verticalPara;
	}
	public String getAsOnDate() {
		return asOnDate;
	}
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	} 
	
}
