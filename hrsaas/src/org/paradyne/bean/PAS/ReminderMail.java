/**
 * 
 */
package org.paradyne.bean.PAS;

import java.util.LinkedHashMap;

import org.paradyne.lib.BeanBase;

/**
 * @author AA0554
 *
 */
public class ReminderMail extends BeanBase {
		private String apprCode;
		private LinkedHashMap phaseList = null;
		private String phaseType="";
		private String apprName;
		private String startDate;
		private String endDate;
		
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		public String getApprName() {
			return apprName;
		}
		public void setApprName(String apprName) {
			this.apprName = apprName;
		}
		public String getPhaseType() {
			return phaseType;
		}
		public void setPhaseType(String phaseType) {
			this.phaseType = phaseType;
		}
		public String getApprCode() {
			return apprCode;
		}
		public void setApprCode(String apprCode) {
			this.apprCode = apprCode;
		}
		public LinkedHashMap getPhaseList() {
			return phaseList;
		}
		public void setPhaseList(LinkedHashMap phaseList) {
			this.phaseList = phaseList;
		}
		
}
