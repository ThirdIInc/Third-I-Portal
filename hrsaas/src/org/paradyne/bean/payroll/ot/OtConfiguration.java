package org.paradyne.bean.payroll.ot;

import java.util.ArrayList;
import java.util.List;

import org.paradyne.lib.BeanBase;

/**Created on 21 Feb 2012.
 * @author aa1385
 */
public class OtConfiguration extends BeanBase {
	
	/** * divisionID */
	private String divisionID = "";
	/**	 * divisionName	 */
	private String divisionName = "";
	/**	 * actualHoursWorkedShiftHoursFlag	 */
	private String actualHoursWorkedShiftHoursFlag = "";
	/**	 * actualOutTimeShiftOutTimeFlag	 */
	private String actualOutTimeShiftOutTimeFlag = "";
	/**	 * otHoursRoundOffOptions	 */
	private String otHoursRoundOffOptions = "";
	/**	 * calOtHourlyRateFormula	 */
	private String calOtHourlyRateFormula = "";
	/**	 * calWeeklyOtHourlyRateFormula	 */
	private String calWeeklyOtHourlyRateFormula = "";
	/**	 * otConfigID	 */
	private String otConfigID = "";
	/**	 * hiddenOtConfigId	 */
	private String hiddenOtConfigId = "";
	
	/**	 * myPage	 */
	private String myPage = null;
	/**	 * modeLength	 */
	private String modeLength="false";
	/**	 * totalRecords	 */
	private String totalRecords="";
	  /**	 * iteratorlist	 */
	ArrayList iteratorlist;
	  private String divisionNameItr = "";
	  
	  /**	 * calDoubleOtHourlyRateFormula	 */
	private String calDoubleOtHourlyRateFormula = "";
	
	private String doubleOtFlag = "";
	
	public String getDoubleOtFlag() {
		return doubleOtFlag;
	}
	public void setDoubleOtFlag(String doubleOtFlag) {
		this.doubleOtFlag = doubleOtFlag;
	}
	public String getCalDoubleOtHourlyRateFormula() {
		return calDoubleOtHourlyRateFormula;
	}
	public void setCalDoubleOtHourlyRateFormula(String calDoubleOtHourlyRateFormula) {
		this.calDoubleOtHourlyRateFormula = calDoubleOtHourlyRateFormula;
	}
	/**getDivisionNameItr.
	 * @return divisionNameItr
	 */
	public String getDivisionNameItr() {
		return divisionNameItr;
	}
	/** setDivisionNameItr.
	 * @param divisionNameItr
	 */
	public void setDivisionNameItr(String divisionNameItr) {
		this.divisionNameItr = divisionNameItr;
	}
	/**getMyPage
	 * @return myPage
	 */
	public String getMyPage() {
		return myPage;
	}
	/** setMyPage.
	 * @param myPage
	 */
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	/** getModeLength.
	 * @return modeLength
	 */
	public String getModeLength() {
		return modeLength;
	}
	/** setModeLength.
	 * @param modeLength
	 */
	public void setModeLength(String modeLength) {
		this.modeLength = modeLength;
	}
	/** getTotalRecords
	 * @return totalRecords.
	 */
	public String getTotalRecords() {
		return totalRecords;
	}
	/** setTotalRecords.
	 * @param totalRecords
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	/** getIteratorlist.
	 * @return iteratorlist
	 */
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	/** getHiddenOtConfigId.
	 * @return hiddenOtConfigId
	 */
	public String getHiddenOtConfigId() {
		return hiddenOtConfigId;
	}
	/** setHiddenOtConfigId.
	 * @param hiddenOtConfigId
	 */
	public void setHiddenOtConfigId(String hiddenOtConfigId) {
		this.hiddenOtConfigId = hiddenOtConfigId;
	}
	/** getDivisionID.
	 * @return divisionID
	 */
	public String getDivisionID() {
		return divisionID;
	}
	/**setDivisionID.
	 * @param divisionID
	 */
	public void setDivisionID(String divisionID) {
		this.divisionID = divisionID;
	}
	/** getDivisionName.
	 * @return divisionName
	 */
	public String getDivisionName() {
		return divisionName;
	}
	/** setDivisionName.
	 * @param divisionName
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	/**getActualHoursWorkedShiftHoursFlag.
	 * @return actualHoursWorkedShiftHoursFlag
	 */
	public String getActualHoursWorkedShiftHoursFlag() {
		return actualHoursWorkedShiftHoursFlag;
	}
	/**setActualHoursWorkedShiftHoursFlag.
	 * @param actualHoursWorkedShiftHoursFlag
	 */
	public void setActualHoursWorkedShiftHoursFlag(
			String actualHoursWorkedShiftHoursFlag) {
		this.actualHoursWorkedShiftHoursFlag = actualHoursWorkedShiftHoursFlag;
	}
	/**getActualOutTimeShiftOutTimeFlag.
	 * @return actualOutTimeShiftOutTimeFlag
	 */
	public String getActualOutTimeShiftOutTimeFlag() {
		return actualOutTimeShiftOutTimeFlag;
	}
	/**setActualOutTimeShiftOutTimeFlag.
	 * @param actualOutTimeShiftOutTimeFlag
	 */
	public void setActualOutTimeShiftOutTimeFlag(
			String actualOutTimeShiftOutTimeFlag) {
		this.actualOutTimeShiftOutTimeFlag = actualOutTimeShiftOutTimeFlag;
	}
	/**getOtHoursRoundOffOptions.
	 * @return otHoursRoundOffOptions
	 */
	public String getOtHoursRoundOffOptions() {
		return otHoursRoundOffOptions;
	}
	/**setOtHoursRoundOffOptions.
	 * @param otHoursRoundOffOptions
	 */
	public void setOtHoursRoundOffOptions(String otHoursRoundOffOptions) {
		this.otHoursRoundOffOptions = otHoursRoundOffOptions;
	}
	/**getCalOtHourlyRateFormula.
	 * @return calOtHourlyRateFormula
	 */
	public String getCalOtHourlyRateFormula() {
		return calOtHourlyRateFormula;
	}
	/**setCalOtHourlyRateFormula.
	 * @param calOtHourlyRateFormula
	 */
	public void setCalOtHourlyRateFormula(String calOtHourlyRateFormula) {
		this.calOtHourlyRateFormula = calOtHourlyRateFormula;
	}
	/**getCalWeeklyOtHourlyRateFormula.
	 * @return calWeeklyOtHourlyRateFormula
	 */
	public String getCalWeeklyOtHourlyRateFormula() {
		return calWeeklyOtHourlyRateFormula;
	}
	/**setCalWeeklyOtHourlyRateFormula.
	 * @param calWeeklyOtHourlyRateFormula
	 */
	public void setCalWeeklyOtHourlyRateFormula(String calWeeklyOtHourlyRateFormula) {
		this.calWeeklyOtHourlyRateFormula = calWeeklyOtHourlyRateFormula;
	}
	public String getOtConfigID() {
		return otConfigID;
	}
	public void setOtConfigID(String otConfigID) {
		this.otConfigID = otConfigID;
	}
	
	

}
