package org.paradyne.model.TravelManagement.TravelReports;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.LinkedMap;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlAccntReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
/**
 * @author ayyappa
 * @date 13-04-2010
 */
public class TrvlAccntReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);
	
	public LinkedMap getTravelTypes() {
		// TODO Auto-generated method stub
		LinkedMap hMap = new LinkedMap();
		String typeQry = " SELECT LOCATION_TYPE_ID,LOCATION_TYPE_NAME FROM HRMS_TMS_LOCATION_TYPE "
				+ " WHERE LOCATION_TYPE_STATUS ='A'"
				+ " ORDER BY LOCATION_TYPE_ID";
		Object[][] typeObj = getSqlModel().getSingleResult(typeQry);

		if (typeObj != null && typeObj.length > 0)
			for (int i = 0; i < typeObj.length; i++)
				hMap.put(String.valueOf(typeObj[i][0]), String.valueOf(typeObj[i][1]));
		return hMap;
	}

	public LinkedMap getTravelPurposes() {
		// TODO Auto-generated method stub
		LinkedMap hMap = new LinkedMap();
		String purQry = " SELECT PURPOSE_ID,PURPOSE_NAME FROM HRMS_TMS_PURPOSE "
				 + " WHERE PURPOSE_STATUS ='A' " 
				 + " ORDER BY PURPOSE_ID";
		Object[][] purObj = getSqlModel().getSingleResult(purQry);

		if (purObj != null && purObj.length > 0)
			for (int i = 0; i < purObj.length; i++)
				hMap.put(String.valueOf(purObj[i][0]), String.valueOf(purObj[i][1]));
		return hMap;
	}

	public static String initCap(String name) {
		String properName = "";

		try {
			properName = name.substring(0, 1).toUpperCase()
					+ name.substring(1, name.length());
		} catch (Exception e) {
			return properName;
		}
		return properName;
	}	
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	public boolean deleteSavedReport(TrvlAccntReport accntRepBean) {
		// TODO Auto-generated method stub
		boolean result = false;
		String deleteDetail = "DELETE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID="
				+ accntRepBean.getReportId();
		String deleteHeader = "DELETE FROM HRMS_MISREPORT_HDR WHERE REPORT_ID="
				+ accntRepBean.getReportId();
		result = getSqlModel().singleExecute(deleteDetail);
		if (result)
			result = getSqlModel().singleExecute(deleteHeader);
		return result;
	}
	
	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(TrvlAccntReport accntRepBean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_MISREPORT_HDR WHERE REPORT_TYPE LIKE 'TravelAccountant' AND UPPER(REPORT_CRITERIA) LIKE '"
				+ accntRepBean.getSettingName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;
	}	
	
	public boolean saveReportCriteria(TrvlAccntReport accntRepBean) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (!checkDuplicate(accntRepBean)) {
			Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = checkNull(accntRepBean.getSettingName().trim());
			saveObj[0][1] = checkNull(accntRepBean.getReportTitle().trim());
			saveObj[0][2] = "TravelAccountant";
			String insertHeader = "INSERT INTO HRMS_MISREPORT_HDR (REPORT_ID, REPORT_CRITERIA, REPORT_TITLE, REPORT_TYPE)"
					+ " VALUES ((SELECT NVL(MAX(REPORT_ID),0)+1 FROM HRMS_MISREPORT_HDR), ?, ?, ?)";
			result = getSqlModel().singleExecute(insertHeader, saveObj);
			if (result) {
				String codeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
				Object[][] codeObj = getSqlModel().getSingleResult(codeQuery);
				accntRepBean.setReportId(String.valueOf(codeObj[0][0]));
				if (saveFilters(accntRepBean) && saveColumns(accntRepBean)
						&& saveSortOptions(accntRepBean)
						&& saveAdvancedFilters(accntRepBean)) {
					result = true;
				} else
					result = false;
			}
		}else {
			result = false;
		}
		return result;
	}
	
	public boolean saveFilters(TrvlAccntReport accntRepBean) {
		
		boolean result = false;
		int count = 0;
		//Status
		if(!accntRepBean.getStatus().equals("")){
			count++;
		}
		//Type of Expense
		if(!accntRepBean.getTypeExp().equals("")){
			count++;
		}
		//Grade
		if(!accntRepBean.getGradeId().equals("")){
			count++;
			count++;
		}
		//Branch
		if(!accntRepBean.getBranchId().equals("")){
			count++;
			count++;
		}
		//Department
		if(!accntRepBean.getDeptId().equals("")){
			count++;
			count++;
		}
		//Advance Amount
		if(!accntRepBean.getAdvAmtSelect().equals("")){
			count++;
		}
		if(!accntRepBean.getAdvAmtFrom().equals("")){
			count++;
		}
		if(!accntRepBean.getAdvAmtTo().equals("")){
			count++;
		}
		//Advance Claim Settlement
		if(!accntRepBean.getClaimsetSelect().equals("")){
			count++;
		}
		if(!accntRepBean.getClaimsetFrom().equals("")){
			count++;
		}
		if(!accntRepBean.getClaimsetTo().equals("")){
			count++;
		}
		//From Date
		if(!accntRepBean.getFromDateSelect().equals("")){
			count++;
		}
		if(!accntRepBean.getFromDateFromDate().equals("")){
			count++;
		}
		if(!accntRepBean.getFromDateToDate().equals("")){
			count++;
		}
		//To Date
		if(!accntRepBean.getToDateSelect().equals("")){
			count++;
		}
		if(!accntRepBean.getToDateFromDate().equals("")){
			count++;
		}
		if(!accntRepBean.getToDateToDate().equals("")){
			count++;
		}
		//Application For
		if(!accntRepBean.getAppliFor().equals("")){
			count++;
		}
		//Travel - Check box
		if(accntRepBean.getTravelCheck().equals("true")){
			count++;	//check box
		}
		//TravelSelf - Radio button
		if(!accntRepBean.getHidTravelSelf().equals("")){
			count++;
		}
		//TravelComp - Radio button
		if(!accntRepBean.getHidTravelComp().equals("")){
			count++;
		}
		//Accommodation - Check box
		if(accntRepBean.getAccomCheck().equals("true")){
			count++;
		}
		//AccomSelf - Radio button
		if(!accntRepBean.getHidAccomSelf().equals("")){
			count++;
		}
		//AccomComp - Radio button
		if(!accntRepBean.getHidAccomComp().equals("")){
			count++;
		}
		//Local Conveyance - Check box
		if(accntRepBean.getLocalCheck().equals("true")){
			count++;
		}
		//LocalSelf - Radio button
		if(!accntRepBean.getHidLocalSelf().equals("")){
			count++;
		}
		//LocalComp - Radio button
		if(!accntRepBean.getHidLocalComp().equals("")){
			count++;
		}
		
		logger.info("Count for saveFilters : "+count);
		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if(!accntRepBean.getStatus().equals("")){
			addObj[int_count][0] = "accntRepBean.status";
			addObj[int_count][1] = checkNull(accntRepBean.getStatus().trim());
			int_count++;
		}
		//Type of Expense
		if(!accntRepBean.getTypeExp().equals("")){
			addObj[int_count][0] = "accntRepBean.typeExp";
			addObj[int_count][1] = checkNull(accntRepBean.getTypeExp().trim());
			int_count++;
		}
		//Grade
		if(!accntRepBean.getGradeId().equals("")){
			addObj[int_count][0] = "accntRepBean.gradeId";
			addObj[int_count][1] = checkNull(accntRepBean.getGradeId().trim());
			int_count++;
			addObj[int_count][0] = "accntRepBean.gradeName";
			addObj[int_count][1] = checkNull(accntRepBean.getGradeName().trim());
			int_count++;
		}
		//Branch
		if(!accntRepBean.getBranchId().equals("")){
			addObj[int_count][0] = "accntRepBean.branchId";
			addObj[int_count][1] = checkNull(accntRepBean.getBranchId().trim());
			int_count++;
			addObj[int_count][0] = "accntRepBean.branchName";
			addObj[int_count][1] = checkNull(accntRepBean.getBranchName().trim());
			int_count++;
		}
		//Department
		if(!accntRepBean.getDeptId().equals("")){
			addObj[int_count][0] = "accntRepBean.deptId";
			addObj[int_count][1] = checkNull(accntRepBean.getDeptId().trim());
			int_count++;
			addObj[int_count][0] = "accntRepBean.deptName";
			addObj[int_count][1] = checkNull(accntRepBean.getDeptName().trim());
			int_count++;
		}
		//Advance Amount
		if(!accntRepBean.getAdvAmtSelect().equals("")){
			addObj[int_count][0] = "advAmtSelect";
			addObj[int_count][1] = checkNull(accntRepBean.getAdvAmtSelect().trim());
			int_count++;
		}
		if(!accntRepBean.getAdvAmtFrom().equals("")){
			addObj[int_count][0] = "accntRepBean.advAmtFrom";
			addObj[int_count][1] = checkNull(accntRepBean.getAdvAmtFrom().trim());
			int_count++;
		}
		if(!accntRepBean.getAdvAmtTo().equals("")){
			addObj[int_count][0] = "accntRepBean.advAmtTo";
			addObj[int_count][1] = checkNull(accntRepBean.getAdvAmtTo().trim());
			int_count++;
		}
		//Advance Claim Settlement
		if(!accntRepBean.getClaimsetSelect().equals("")){
			addObj[int_count][0] = "claimsetSelect";
			addObj[int_count][1] = checkNull(accntRepBean.getClaimsetSelect().trim());
			int_count++;
		}
		if(!accntRepBean.getClaimsetFrom().equals("")){
			addObj[int_count][0] = "accntRepBean.claimsetFrom";
			addObj[int_count][1] = checkNull(accntRepBean.getClaimsetFrom().trim());
			int_count++;
		}
		if(!accntRepBean.getClaimsetTo().equals("")){
			addObj[int_count][0] = "accntRepBean.claimsetTo";
			addObj[int_count][1] = checkNull(accntRepBean.getClaimsetTo().trim());
			int_count++;
		}
		//From Date
		if(!accntRepBean.getFromDateSelect().equals("")){
			addObj[int_count][0] = "fromDateSelect";
			addObj[int_count][1] = checkNull(accntRepBean.getFromDateSelect().trim());
			int_count++;
		}
		if(!accntRepBean.getFromDateFromDate().equals("")){
			addObj[int_count][0] = "accntRepBean.fromDateFromDate";
			addObj[int_count][1] = checkNull(accntRepBean.getFromDateFromDate().trim());
			int_count++;
		}
		if(!accntRepBean.getFromDateToDate().equals("")){
			addObj[int_count][0] = "accntRepBean.fromDateToDate";
			addObj[int_count][1] = checkNull(accntRepBean.getFromDateToDate().trim());
			int_count++;
		}
		//To Date
		if(!accntRepBean.getToDateSelect().equals("")){
			addObj[int_count][0] = "toDateSelect";
			addObj[int_count][1] = checkNull(accntRepBean.getToDateSelect().trim());
			int_count++;
		}
		if(!accntRepBean.getToDateFromDate().equals("")){
			addObj[int_count][0] = "accntRepBean.toDateToDate";
			addObj[int_count][1] = checkNull(accntRepBean.getToDateFromDate().trim());
			int_count++;
		}
		if(!accntRepBean.getToDateToDate().equals("")){
			addObj[int_count][0] = "accntRepBean.toDateToDate";
			addObj[int_count][1] = checkNull(accntRepBean.getToDateToDate().trim());
			int_count++;
		}
		//Application For
		if(!accntRepBean.getAppliFor().equals("")){
			addObj[int_count][0] = "accntRepBean.appliFor";
			addObj[int_count][1] = checkNull(accntRepBean.getAppliFor().trim());
			int_count++;
		}
		//Travel - Check box
		if(accntRepBean.getTravelCheck().equals("true")){
			addObj[int_count][0] = "accntRepBean.travelCheck";
			addObj[int_count][1] = checkNull(accntRepBean.getTravelCheck().trim());
			int_count++;
		}
		//TravelSelf - Radio button
		if(!accntRepBean.getHidTravelSelf().equals("")){
			addObj[int_count][0] = "hidTravelSelf";
			addObj[int_count][1] = checkNull(accntRepBean.getHidTravelSelf().trim());
			int_count++;
		}
		//TravelComp - Radio button
		if(!accntRepBean.getHidTravelComp().equals("")){
			addObj[int_count][0] = "hidTravelComp";
			addObj[int_count][1] = checkNull(accntRepBean.getHidTravelComp().trim());
			int_count++;
		}
		//Accommodation - Check box
		if(accntRepBean.getAccomCheck().equals("true")){
			addObj[int_count][0] = "accntRepBean.accomCheck";
			addObj[int_count][1] = checkNull(accntRepBean.getAccomCheck().trim());
			int_count++;
		}
		//AccomSelf - Radio button
		if(!accntRepBean.getHidAccomSelf().equals("")){
			addObj[int_count][0] = "hidAccomSelf";
			addObj[int_count][1] = checkNull(accntRepBean.getHidAccomSelf().trim());
			int_count++;
		}
		//AccomComp - Radio button
		if(!accntRepBean.getHidAccomComp().equals("")){
			addObj[int_count][0] = "hidAccomComp";
			addObj[int_count][1] = checkNull(accntRepBean.getHidAccomComp().trim());
			int_count++;
		}
		//Local Conveyance - Check box
		if(accntRepBean.getLocalCheck().equals("true")){
			addObj[int_count][0] = "accntRepBean.localCheck";
			addObj[int_count][1] = checkNull(accntRepBean.getLocalCheck().trim());
			int_count++;
		}
		//LocalSelf - Radio button
		if(!accntRepBean.getHidLocalSelf().equals("")){
			addObj[int_count][0] = "hidLocalSelf";
			addObj[int_count][1] = checkNull(accntRepBean.getHidLocalSelf().trim());
			int_count++;
		}
		//LocalComp - Radio button
		if(!accntRepBean.getHidLocalComp().equals("")){
			addObj[int_count][0] = "hidLocalComp";
			addObj[int_count][1] = checkNull(accntRepBean.getHidLocalComp().trim());
			int_count++;
		}
		
		logger.info("int_count     : " + int_count);
		if(int_count==0){
			return true;
		} else {

			String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);

			Object[][] addFilters = new Object[int_count][3];
			for (int i = 0; i < addFilters.length; i++) {
				addFilters[i][0] = maxCode[0][0];
				addFilters[i][1] = addObj[i][0];
				logger.info("addFilters[" + i + "][1]  : " + addFilters[i][1]);
				addFilters[i][2] = addObj[i][1];
				logger.info("addFilters[" + i + "][2]  : " + addFilters[i][2]);
			}
			String insertFilters = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
					+ " VALUES (?, ?, ?) ";
			result = getSqlModel().singleExecute(insertFilters, addFilters);
			return result;
		}				
	} //End of saveFilters method
	
	public boolean saveColumns(TrvlAccntReport accntRepBean){
		boolean result = false;
		int count = 0;
		
		//Applicant Name
		if(accntRepBean.getAppliNameFlag().equals("true")){
			count++;
		}
		//Grade
		if(accntRepBean.getGradeFlag().equals("true")){
			count++;
		}
		//Branch
		if(accntRepBean.getBranchFlag().equals("true")){
			count++;
		}
		//Employee Id
		if(accntRepBean.getEmpIdFlag().equals("true")){
			count++;
		}
		//Travel Start Date
		if(accntRepBean.getStartDateFlag().equals("true")){
			count++;
		}
		//Travel End Date
		if(accntRepBean.getEndDateFlag().equals("true")){
			count++;
		}
		//Status
		if(accntRepBean.getStatusFlag().equals("true")){
			count++;
		}
		//Travel Purpose
		if(accntRepBean.getPurposeFlag().equals("true")){
			count++;
		}
		//Travel Type
		if(accntRepBean.getTypeFlag().equals("true")){
			count++;
		}
		//Advance Amount 
		if(accntRepBean.getAdvAmtFlag().equals("true")){
			count++;
		}
		
		
		logger.info("Count for saveColumns : "+count);
		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		//Applicant Name
		if(accntRepBean.getAppliNameFlag().equals("true")){
			addObj[int_count][0] = "accntRepBean.appliNameFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Grade
		if(accntRepBean.getGradeFlag().equals("true")){
			addObj[int_count][0] = "accntRepBean.gradeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Branch
		if(accntRepBean.getBranchFlag().equals("true")){
			addObj[int_count][0] = "accntRepBean.branchFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Employee Id
		if(accntRepBean.getEmpIdFlag().equals("true")){
			addObj[int_count][0] = "accntRepBean.empIdFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Travel Start Date
		if(accntRepBean.getStartDateFlag().equals("true")){
			addObj[int_count][0] = "accntRepBean.startDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Travel End Date
		if(accntRepBean.getEndDateFlag().equals("true")){
			addObj[int_count][0] = "accntRepBean.endDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Status
		if(accntRepBean.getStatusFlag().equals("true")){
			addObj[int_count][0] = "accntRepBean.statusFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Travel Purpose
		if(accntRepBean.getPurposeFlag().equals("true")){
			addObj[int_count][0] = "accntRepBean.purposeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Travel Type
		if(accntRepBean.getTypeFlag().equals("true")){
			addObj[int_count][0] = "accntRepBean.typeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Advance Amount
		if(accntRepBean.getAdvAmtFlag().equals("true")){
			addObj[int_count][0] = "accntRepBean.advAmtFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		logger.info("int_count     : " + int_count);
		if(int_count==0){
			return true;
		} else {
			String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);

			Object[][] addColumns = new Object[int_count][3];
			for (int i = 0; i < addColumns.length; i++) {
				addColumns[i][0] = maxCode[0][0];
				addColumns[i][1] = addObj[i][0];
				logger.info("addColumns[" + i + "][1]  : " + addColumns[i][1]);
				addColumns[i][2] = addObj[i][1];
				logger.info("addColumns[" + i + "][2]  : " + addColumns[i][2]);
			}
			String insertColumns = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
					+ " VALUES (?, ?, ?) ";
			result = getSqlModel().singleExecute(insertColumns, addColumns);
			return result;
		}		
	}
	public boolean saveSortOptions(TrvlAccntReport accntRepBean){
		boolean result = false;
		int count = 0;
		
		if(!accntRepBean.getSortBy().equals("1")) {
			count++;
		}
		if(!accntRepBean.getHiddenSortBy().equals("")){
			count++;
		}
		if(!accntRepBean.getSortByAsc().equals("")) {
			count++;
		}
		if(!accntRepBean.getSortByDsc().equals("")) {
			count++;
		}
		if(!accntRepBean.getSortByOrder().equals("")) {
			count++;
		}
		if(!accntRepBean.getThenBy1().equals("1")) {
			count++;
		}
		if(!accntRepBean.getHiddenThenBy1().equals("")){
			count++;
		}
		if(!accntRepBean.getThenByOrder1Asc().equals("")) {
			count++;
		}
		if(!accntRepBean.getThenByOrder1Dsc().equals("")) {
			count++;
		}
		if(!accntRepBean.getThenByOrder1().equals("")) {
			count++;
		}
		if(!accntRepBean.getThenBy2().equals("1")) {
			count++;
		}
		if(!accntRepBean.getHiddenThenBy2().equals("")){
			count++;
		}
		if(!accntRepBean.getThenByOrder2Asc().equals("")) {
			count++;
		}
		if(!accntRepBean.getThenByOrder2Dsc().equals("")) {
			count++;
		}
		if(!accntRepBean.getThenByOrder2().equals("")) {
			count++;
		}
		if(!accntRepBean.getHiddenColumns().equals("")){
			count++;
		}

		logger.info("Count for Save sort options : " + count);	
		

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if(!accntRepBean.getSortBy().equals("1")) {
			addObj[int_count][0] = "sortBy";
			addObj[int_count][1] = checkNull(accntRepBean.getSortBy().trim());
			int_count++;
		}
		if(!accntRepBean.getHiddenSortBy().equals("")) {
			addObj[int_count][0] = "hiddenSortBy";
			addObj[int_count][1] = checkNull(accntRepBean.getHiddenSortBy().trim());
			int_count++;
		}
		logger.info("getSortByAsc...."+accntRepBean.getSortByAsc());
		if(!accntRepBean.getSortByAsc().equals("")) {
			addObj[int_count][0] = "sortByAsc";
			addObj[int_count][1] = "Y";// checkNull(accntRepBean.getSortByAsc().trim());
			int_count++;
		}
		logger.info("getSortByDsc...."+accntRepBean.getSortByDsc());
		if(!accntRepBean.getSortByDsc().equals("")) {
			addObj[int_count][0] = "sortByDsc";
			addObj[int_count][1] = "Y";// checkNull(accntRepBean.getSortByDsc().trim());
			int_count++;
		}
		if(!accntRepBean.getSortByOrder().equals("")) {
			addObj[int_count][0] = "sortByOrder";
			addObj[int_count][1] = checkNull(accntRepBean.getSortByOrder().trim());
			int_count++;
		}
		if(!accntRepBean.getThenBy1().equals("1")) {
			addObj[int_count][0] = "thenBy1";
			addObj[int_count][1] = checkNull(accntRepBean.getThenBy1().trim());
			int_count++;
		}
		if(!accntRepBean.getHiddenThenBy1().equals("")) {
			addObj[int_count][0] = "hiddenThenBy1";
			addObj[int_count][1] = checkNull(accntRepBean.getHiddenThenBy1().trim());
			int_count++;
		}
		if(!accntRepBean.getThenByOrder1Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Asc";
			addObj[int_count][1] = "Y";// checkNull(accntRepBean.getThenByOrder1Asc().trim());
			int_count++;
		}
		if(!accntRepBean.getThenByOrder1Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Dsc";
			addObj[int_count][1] = "Y";// checkNull(accntRepBean.getThenByOrder1Dsc().trim());
			int_count++;
		}
		if(!accntRepBean.getThenByOrder1().equals("")) {
			addObj[int_count][0] = "thenByOrder1";
			addObj[int_count][1] = checkNull(accntRepBean.getThenByOrder1().trim());
			int_count++;
		}
		if(!accntRepBean.getThenBy2().equals("1")) {
			addObj[int_count][0] = "thenBy2";
			addObj[int_count][1] = checkNull(accntRepBean.getThenBy2().trim());
			int_count++;
		}
		if(!accntRepBean.getHiddenThenBy2().equals("")) {
			addObj[int_count][0] = "hiddenThenBy2";
			addObj[int_count][1] = checkNull(accntRepBean.getHiddenThenBy2().trim());
			int_count++;
		}
		if(!accntRepBean.getThenByOrder2Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Asc";
			addObj[int_count][1] = "Y";// checkNull(accntRepBean.getThenByOrder2Asc().trim());
			int_count++;
		}
		if(!accntRepBean.getThenByOrder2Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Dsc";
			addObj[int_count][1] = "Y";// checkNull(accntRepBean.getThenByOrder2Dsc().trim());
			int_count++;
		}
		if(!accntRepBean.getThenByOrder2().equals("")) {
			addObj[int_count][0] = "thenByOrder2";
			addObj[int_count][1] = checkNull(accntRepBean.getThenByOrder2().trim());
			int_count++;
		}
		if(!accntRepBean.getHiddenColumns().equals("")){
			addObj[int_count][0] = "hiddenColumns";
			addObj[int_count][1] = checkNull(accntRepBean.getHiddenColumns().trim());
			int_count++;
		}

		logger.info("int_count     : " + int_count);
		if(int_count==0){
			return true;
		} else {
			String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);

			Object[][] addSortOptions = new Object[int_count][3];
			for (int i = 0; i < addSortOptions.length; i++) {
				addSortOptions[i][0] = maxCode[0][0];
				addSortOptions[i][1] = addObj[i][0];
				logger.info("addSortOptions[" + i + "][1]  : "
						+ addSortOptions[i][1]);
				addSortOptions[i][2] = addObj[i][1];
				logger.info("addSortOptions[" + i + "][2]  : "
						+ addSortOptions[i][2]);
			}
			String insertSortOptions = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
					+ " VALUES (?, ?, ?) ";
			result = getSqlModel().singleExecute(insertSortOptions,
					addSortOptions);

			return result;
		}		
	}
	public boolean saveAdvancedFilters(TrvlAccntReport accntRepBean){
		boolean result = false;
		int count = 0;
		
		//Travel Type
		if(!accntRepBean.getTrvlType().equals("")){
			count++;
		}
		//Travel Purpose
		if(!accntRepBean.getTrvlPurpose().equals("")){
			count++;
		}
		//Travel Start Date
		if(!accntRepBean.getStartDateSelect().equals("")){
			count++;
		}
		if(!accntRepBean.getStartFromDate().equals("")){
			count++;
		}
		if(!accntRepBean.getStartToDate().equals("")){
			count++;
		}
		//Travel End Date
		if(!accntRepBean.getEndDateSelect().equals("")){
			count++;
		}
		if(!accntRepBean.getEndFromDate().equals("")){
			count++;
		}
		if(!accntRepBean.getEndToDate().equals("")){
			count++;
		}
		logger.info("Count for Save advance filters : " + count);
		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		//Travel Type
		if(!accntRepBean.getTrvlType().equals("")){
			addObj[int_count][0] = "trvlType";
			addObj[int_count][1] = checkNull(accntRepBean.getTrvlType().trim());
			int_count++;
		}
		//Travel Purpose
		if(!accntRepBean.getTrvlPurpose().equals("")){
			addObj[int_count][0] = "trvlPurpose";
			addObj[int_count][1] = checkNull(accntRepBean.getTrvlPurpose().trim());
			int_count++;
		}
		//Travel Start Date
		if(!accntRepBean.getStartDateSelect().equals("")){
			addObj[int_count][0] = "startDateSelect";
			addObj[int_count][1] = checkNull(accntRepBean.getStartDateSelect().trim());
			int_count++;
		}
		if(!accntRepBean.getStartFromDate().equals("")){
			addObj[int_count][0] = "accntRepBean.startFromDate";
			addObj[int_count][1] = checkNull(accntRepBean.getStartFromDate().trim());
			int_count++;
		}
		if(!accntRepBean.getStartToDate().equals("")){
			addObj[int_count][0] = "accntRepBean.startToDate";
			addObj[int_count][1] = checkNull(accntRepBean.getStartToDate().trim());
			int_count++;
		}
		//Travel End Date
		if(!accntRepBean.getEndDateSelect().equals("")){
			addObj[int_count][0] = "endDateSelect";
			addObj[int_count][1] = checkNull(accntRepBean.getEndDateSelect().trim());
			int_count++;
		}
		if(!accntRepBean.getEndFromDate().equals("")){
			addObj[int_count][0] = "accntRepBean.endFromDate";
			addObj[int_count][1] = checkNull(accntRepBean.getEndFromDate().trim());
			int_count++;
		}
		if(!accntRepBean.getEndToDate().equals("")){
			addObj[int_count][0] = "accntRepBean.endToDate";
			addObj[int_count][1] = checkNull(accntRepBean.getEndToDate().trim());
			int_count++;
		}
		
		logger.info("int_count     : " + int_count);
		if(int_count==0){
			return true;
		} else {
			String maxCodeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);

			Object[][] addAdvanceFilters = new Object[int_count][3];
			for (int i = 0; i < addAdvanceFilters.length; i++) {
				addAdvanceFilters[i][0] = maxCode[0][0];
				addAdvanceFilters[i][1] = addObj[i][0];
				logger.info("addAdvanceFilters[" + i + "][1]  : "
						+ addAdvanceFilters[i][1]);
				addAdvanceFilters[i][2] = addObj[i][1];
				logger.info("addAdvanceFilters[" + i + "][2]  : "
						+ addAdvanceFilters[i][2]);
			}//End of for
			String insertAdvFilters = "INSERT INTO HRMS_MISREPORT_DTL (REPORT_ID, FIELD_NAME, FIELD_VALUE) "
					+ " VALUES (?, ?, ?) ";
			result = getSqlModel().singleExecute(insertAdvFilters,
					addAdvanceFilters);
			return result;
		}		
	}

	public void setDetailOnScreen(TrvlAccntReport accntRepBean) {
		// TODO Auto-generated method stub
		accntRepBean.setHidTravelComp("");
		accntRepBean.setHidAccomComp("");
		accntRepBean.setHidLocalComp("");
		String dtlQuery = "SELECT REPORT_ID, FIELD_NAME, FIELD_VALUE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID ="
			+ accntRepBean.getReportId();
		Object[][] dtlObj = getSqlModel().getSingleResult(dtlQuery);
		
		if(dtlObj!= null && dtlObj.length > 0){
			try {
				for(int i=0; i< dtlObj.length; i++){
					String methodStr = "";
					logger.info("dtlObj[" + i + "][1] "+ String.valueOf(dtlObj[i][1]));
					String fieldName = String.valueOf(dtlObj[i][1]).replace(".", "-");
					logger.info("fieldName " + fieldName);
					String fieldNames[] = fieldName.split("-");
					if(fieldNames.length>1)
						methodStr = fieldNames[1];
					else
						methodStr = fieldNames[0];
					logger.info("methodStr - "+methodStr);	                       
					if(String.valueOf(dtlObj[i][2]).trim().equals("Y")){
						dtlObj[i][2] = "true";
					}
					if(String.valueOf(dtlObj[i][2]).trim().equals("true") &&
							(	String.valueOf(dtlObj[i][1]).trim().equals("sortByAsc")
							||	String.valueOf(dtlObj[i][1]).trim().equals("sortByDsc")
							||	String.valueOf(dtlObj[i][1]).trim().equals("thenByOrder1Asc")
							||	String.valueOf(dtlObj[i][1]).trim().equals("thenByOrder1Dsc")
							||	String.valueOf(dtlObj[i][1]).trim().equals("thenByOrder2Asc")
							||	String.valueOf(dtlObj[i][1]).trim().equals("thenByOrder2Dsc")
							)	){
						dtlObj[i][2] = "checked";
					}
					Method modelMethod = TrvlAccntReport.class.getDeclaredMethod(
										"set"+initCap(methodStr), String.class);
					logger.info("modelMethod - "+modelMethod);
					modelMethod.invoke(accntRepBean, dtlObj[i][2]);
				}//End of for
			}catch(Exception e){
				logger.error("Exception in setDetailOnScreen - "+e);
			}//End of try-catch
		}//End of if
	}

	public void callViewScreen(TrvlAccntReport accntRepBean,
			HttpServletRequest request, String[] labelNames) {
		// TODO Auto-generated method stub
		try{
			logger.info("report columns -------- "+accntRepBean.getHiddenColumns());
			String multiSelectValues = accntRepBean.getHiddenColumns();
			String splitComma[] = null;
			if(!multiSelectValues.equals("")){
				String lastComma = multiSelectValues.substring(0, multiSelectValues.length()-1);
				splitComma = lastComma.split(",");
			}//End of if
			int count = 0;
			// Select Query with Count 
			Object[][] selectQueryObj = selectQuery(accntRepBean,labelNames,count,splitComma,request);
			String advEmpSelectQuery = (String) selectQueryObj[0][0];
			String advGuestSelectQuery = (String) selectQueryObj[0][1];
			String expSelectQuery = (String) selectQueryObj[0][2];
			//String expGuestSelectQuery = (String) selectQueryObj[0][3];
			String labels = (String) selectQueryObj[0][3];
			count = Integer.parseInt((String) selectQueryObj[0][4]);
			logger.info("counter after select query==========" + count);
			logger.info("labels after select query==========" + labels);
			request.setAttribute("labelValues", labels);
			// CONDITION QUERY
			Object[][] condQueryObj = conditionQuery(accntRepBean, labelNames); 
			String advEmpConditionQuery = (String) condQueryObj[0][0];
			String advGuestConditionQuery = (String) condQueryObj[0][1];
			String expConditionQuery = (String) condQueryObj[0][2];
			//String expGuestConditionQuery = (String) condQueryObj[0][3];
			
			String orderByStmt = (String) condQueryObj[0][3];
			//CREATING QUERIES
			String advEmpQuery = advEmpSelectQuery + advEmpConditionQuery;
			String advGuestQuery = advGuestSelectQuery + advGuestConditionQuery;
			String expQuery = expSelectQuery + expConditionQuery;
			//String expGuestQuery = expGuestSelectQuery + expGuestConditionQuery;
			
			//FINAL QUERY
			String finalQuery = "";
			//Check TYPE OF EXPENSE
			if(!(accntRepBean.getTypeExp().equals(""))) {
				if(accntRepBean.getTypeExp().equals("A")) {
					finalQuery = advEmpQuery + " UNION " + advGuestQuery;
				}
				if(accntRepBean.getTypeExp().equals("C")) {
					finalQuery = expQuery;
				}
			}
/*			else{
				//union of 4 queries
				finalQuery = advEmpQuery  + " UNION " +
							advGuestQuery + " UNION " + 
							expEmpQuery  + " UNION " + 
							expGuestQuery;
			}*/
			//Adding Order By stmt to finalQuery
			finalQuery += orderByStmt;
			logger.info("finalQuery -- ----------------------->\n"+finalQuery);
			Object finalObj[][] = null;
			finalObj = getSqlModel().getSingleResult(finalQuery);
			logger.info("finalObj.length - "+finalObj.length);
			
			if(finalObj != null && finalObj.length > 0){
				String[] pageIndex = Utility.doPaging(accntRepBean.getMyPage(),
						finalObj.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					accntRepBean.setMyPage("1");
				
				int numOfRec = Integer.parseInt(pageIndex[1]) - Integer.parseInt(pageIndex[0]);
				int columnLength = finalObj[0].length;
				Object[][] pageObj = new Object[numOfRec][columnLength];
				int z = 0;
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					for (int j = 0; j < columnLength; j++) {
						pageObj[z][j] = checkNull(String.valueOf(finalObj[i][j]));
					}
					z++;
				}
				
				request.setAttribute("labelValues", labels);
				request.setAttribute("finalObj", pageObj);
				accntRepBean.setDataLength(String.valueOf(finalObj.length));
				accntRepBean.setNoData("false");
			} else {
				request.setAttribute("totalPage", new Integer(0));
				request.setAttribute("PageNo", new Integer(0));
				accntRepBean.setNoData("true");
				request.setAttribute("labelValues", labels);
			}
		}catch(Exception e){
			request.setAttribute("totalPage", new Integer(0));
			request.setAttribute("PageNo", new Integer(0));
			accntRepBean.setNoData("true");
			logger.error("Exception in callViewScreen - "+e);
		}
	}

	public Object[][] selectQuery(TrvlAccntReport accntRepBean,
			String[] labelNames, int count, String[] splitComma,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String labels = "Travel Id,";
		String advEmpQuery = "SELECT DISTINCT APPL_TRVL_ID  AS TRAVELID";
		String advGuestQuery = "SELECT DISTINCT APPL_TRVL_ID  AS TRAVELID";
		String expQuery = "SELECT DISTINCT EXP_TRVL_ID  AS TRAVELID";
	//	String expGuestQuery = "SELECT DISTINCT APPL_TRVL_ID  AS TRAVELID";
		if(splitComma!=null){
			// new HASHMAP FOR ORDERING
			LinkedHashMap<Integer, String> labelMap = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> advEmpMap = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> advGuestMap = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> expMap = new LinkedHashMap<Integer, String>();
			//LinkedHashMap<Integer, String> expGuestMap = new LinkedHashMap<Integer, String>();
			
			for(int i=0;i<splitComma.length;i++) {
				String splitDash[] = splitComma[i].split("-");
				logger.info("Key....." + splitDash[0]);
				logger.info("Value....." + splitDash[1]);
				
				// DONT APPEND QUERY
				// PUT IN HASHMAP (ORDER NO,FIELD)
				if(splitDash[1].trim().equals(labelNames[0].trim())) {		//APPLICANTNAME
					advEmpMap.put(Integer.parseInt(splitDash[0]),
							"nvl(HRMS_EMP_OFFC.EMP_FNAME,'')||' '||nvl(HRMS_EMP_OFFC.EMP_MNAME,'')||"+
							"nvl(HRMS_EMP_OFFC.EMP_LNAME,'') as APPLICANTNAME");
					advGuestMap.put(Integer.parseInt(splitDash[0]),
							"nvl(HRMS_EMP_OFFC.EMP_FNAME,'')||' '||nvl(HRMS_EMP_OFFC.EMP_MNAME,'')||"+
							"nvl(HRMS_EMP_OFFC.EMP_LNAME,'') as APPLICANTNAME");
					expMap.put(Integer.parseInt(splitDash[0]),
							"nvl(HRMS_EMP_OFFC.EMP_FNAME,'')||' '||nvl(HRMS_EMP_OFFC.EMP_MNAME,'')||"+
							"nvl(HRMS_EMP_OFFC.EMP_LNAME,'') as APPLICANTNAME");
				/*	expGuestMap.put(Integer.parseInt(splitDash[0]),
							"nvl(HRMS_EMP_OFFC.EMP_FNAME,'')||' '||nvl(HRMS_EMP_OFFC.EMP_MNAME,'')||"+
							"nvl(HRMS_EMP_OFFC.EMP_LNAME,'') as APPLICANTNAME");	*/
					labelMap.put(1, labelNames[0]);
					count++;
				}
				else if(splitDash[1].trim().equals(labelNames[1].trim())) {		//GRADE
					advEmpMap.put(Integer.parseInt(splitDash[0]),"nvl(CADRE_NAME,'') as GRADE");
					advGuestMap.put(Integer.parseInt(splitDash[0]),"nvl(CADRE_NAME,'') as GRADE");
					expMap.put(Integer.parseInt(splitDash[0]),"nvl(CADRE_NAME,'') as GRADE");
					//expGuestMap.put(Integer.parseInt(splitDash[0]),"CADRE_NAME as GRADE");
					labelMap.put(2, labelNames[1]);
					count++;
				}
				else if(splitDash[1].trim().equals(labelNames[2].trim())) {		//BRANCH
					advEmpMap.put(Integer.parseInt(splitDash[0]),"nvl(CENTER_NAME,'') AS BRANCH");
					advGuestMap.put(Integer.parseInt(splitDash[0]),"nvl(CENTER_NAME,'') AS BRANCH");
					expMap.put(Integer.parseInt(splitDash[0]),"nvl(CENTER_NAME,'') AS BRANCH");
					//expGuestMap.put(Integer.parseInt(splitDash[0]),"CENTER_NAME AS BRANCH");
					labelMap.put(3, labelNames[2]);
					count++;
				}
				else if(splitDash[1].trim().equals(labelNames[3].trim())) {		//EMPLOYEE ID
					advEmpMap.put(Integer.parseInt(splitDash[0]),"nvl(HRMS_EMP_OFFC.EMP_TOKEN,'') AS TOKEN");
					advGuestMap.put(Integer.parseInt(splitDash[0]),"nvl(HRMS_EMP_OFFC.EMP_TOKEN,'') AS TOKEN");
					expMap.put(Integer.parseInt(splitDash[0]),"nvl(HRMS_EMP_OFFC.EMP_TOKEN,'') AS TOKEN");
					//expGuestMap.put(Integer.parseInt(splitDash[0]),"HRMS_EMP_OFFC.EMP_TOKEN AS TOKEN");
					labelMap.put(4, labelNames[3]);
					count++;
				}
				else if(splitDash[1].trim().equals(labelNames[4].trim())) {		//TRAVEL START DATE
					advEmpMap.put(Integer.parseInt(splitDash[0]),
							"nvl(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),' ') AS STARTDATE");
					advGuestMap.put(Integer.parseInt(splitDash[0]),
							"nvl(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),' ') AS STARTDATE");
					expMap.put(Integer.parseInt(splitDash[0]),
							"nvl(TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),' ') AS STARTDATE");
					/*expGuestMap.put(Integer.parseInt(splitDash[0]),
							"TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS STARTDATE");	*/
					labelMap.put(5, labelNames[4]);
					count++;
				}
				else if(splitDash[1].trim().equals(labelNames[5].trim())) {		//TRAVEL END DATE
					advEmpMap.put(Integer.parseInt(splitDash[0]),
							"nvl(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),' ') AS ENDDATE");
					advGuestMap.put(Integer.parseInt(splitDash[0]),
							"nvl(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),' ') AS ENDDATE");
					expMap.put(Integer.parseInt(splitDash[0]),
							"nvl(TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),' ') AS ENDDATE");
					/*expGuestMap.put(Integer.parseInt(splitDash[0]),
							"TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') AS ENDDATE");*/
					labelMap.put(6, labelNames[5]);
					count++;
				}
				else if(splitDash[1].trim().equals(labelNames[6].trim())) {		//STATUS
					advEmpMap.put(Integer.parseInt(splitDash[0]),
							"DECODE(ADV_DISB_STATUS,'C','Closed','P','Partially Paid','Pending') AS STATUS");
					advGuestMap.put(Integer.parseInt(splitDash[0]),
							"DECODE(ADV_DISB_STATUS,'C','Closed','P','Partially Paid','Pending') AS STATUS");
					expMap.put(Integer.parseInt(splitDash[0]),
							"DECODE(EXP_DISB_STATUS,'C','Closed','P','Partially Paid','X','Over Payment','Pending') AS STATUS");
					/*expGuestMap.put(Integer.parseInt(splitDash[0]),
							"DECODE(EXP_DISB_STATUS,'C','Closed','P','Partially Closed','X','Over Payment','Pending') AS STATUS");*/
					labelMap.put(7, labelNames[6]);
					count++;
				}
				else if(splitDash[1].trim().equals(labelNames[7].trim())) {		//TRAVEL PURPOSE
					advEmpMap.put(Integer.parseInt(splitDash[0]),"NVL(PURPOSE_NAME,' ') AS PURPOSE");
					advGuestMap.put(Integer.parseInt(splitDash[0]),"NVL(PURPOSE_NAME,' ') AS PURPOSE");
					expMap.put(Integer.parseInt(splitDash[0]),"NVL(PURPOSE_NAME,' ') AS PURPOSE");
					//expGuestMap.put(Integer.parseInt(splitDash[0]),"NVL(PURPOSE_NAME,' ') AS PURPOSE");
					labelMap.put(8, labelNames[7]);
					count++;
				}
				else if(splitDash[1].trim().equals(labelNames[8].trim())) {		//TRAVEL TYPE
					advEmpMap.put(Integer.parseInt(splitDash[0]),"NVL(LOCATION_TYPE_NAME,' ') AS TRAVELTYPE");
					advGuestMap.put(Integer.parseInt(splitDash[0]),"NVL(LOCATION_TYPE_NAME,' ') AS TRAVELTYPE");
					expMap.put(Integer.parseInt(splitDash[0]),"NVL(LOCATION_TYPE_NAME,' ') AS TRAVELTYPE");
					//expGuestMap.put(Integer.parseInt(splitDash[0]),"NVL(LOCATION_TYPE_NAME,' ') AS TRAVELTYPE");
					labelMap.put(9, labelNames[8]);
					count++;
				}
				else if(splitDash[1].trim().equals(labelNames[9].trim())) {		//ADVANCE AMOUNT
					advEmpMap.put(Integer.parseInt(splitDash[0]),"NVL(APPL_EMP_ADVANCE_AMT,'0') AS ADV_AMT");
					advGuestMap.put(Integer.parseInt(splitDash[0]),"NVL(GUEST_ADVANCE_AMT,'0') AS ADV_AMT");
					expMap.put(Integer.parseInt(splitDash[0]),"NVL(EXP_APPRVD_AMT,'0') AS ADV_AMT");
					//expGuestMap.put(Integer.parseInt(splitDash[0]),"NVL(TMS_EXP_DISBURSEMENT.EXP_DISB_ADVANCE_AMT,'0') AS ADV_AMT");
					labelMap.put(10, labelNames[9]);
					count++;
				}
			}
			
			Iterator<Integer> advEmpIterator = advEmpMap.keySet().iterator();
			while (advEmpIterator.hasNext()) {
				String mapAdvEmpValues = (String) advEmpMap.get(advEmpIterator.next());
				logger.info("mapEmpValues : " + mapAdvEmpValues);
				advEmpQuery += "," + mapAdvEmpValues;
			}
			
			
			Iterator<Integer> advGuestIterator = advGuestMap.keySet().iterator();
			while (advGuestIterator.hasNext()) {
				String mapAdvGuestValues = (String) advGuestMap.get(advGuestIterator.next());
				logger.info("mapEmpValues : " + mapAdvGuestValues);
				advGuestQuery += "," + mapAdvGuestValues;
			}

			Iterator<Integer> expIterator = expMap.keySet().iterator();
			while (expIterator.hasNext()) {
				String mapExpValues = (String) expMap.get(expIterator.next());
				logger.info("mapEmpValues : " + mapExpValues);
				expQuery += "," + mapExpValues;
			}
			/*
			Iterator<Integer> expGuestIterator = expGuestMap.keySet().iterator();
			while (expGuestIterator.hasNext()) {
				String mapExpGuestValues = (String) expGuestMap.get(expGuestIterator.next());
				logger.info("mapEmpValues : " + mapExpGuestValues);
				expGuestQuery += "," + mapExpGuestValues;
			}
			*/
			Iterator<Integer> labelIter = labelMap.keySet().iterator();
			String labelValues = "";
			while (labelIter.hasNext()) {
				labelValues = (String) labelMap.get(labelIter.next());
				logger.info("labelValues : " + labelValues);
				labels += labelValues + ",";
			}
		}//End of if
		
		logger.info("labels -- "+labels);
		advEmpQuery += " ,HRMS_EMP_OFFC.EMP_ID ";
		advGuestQuery += " ,HRMS_EMP_OFFC.EMP_ID ";
		expQuery += " ,HRMS_EMP_OFFC.EMP_ID ";
		//expGuestQuery += " ,HRMS_EMP_OFFC.EMP_ID ";

		Object[][] str = new Object[1][5];
		str[0][0] = advEmpQuery;
		str[0][1] = advGuestQuery;
		str[0][2] = expQuery;
		//str[0][3] = expGuestQuery;
		str[0][3] = labels;
		str[0][4] = "" + count;
		return str;
	}

	public Object[][] conditionQuery(TrvlAccntReport accntRepBean,
			String[] labelNames) {
		
		String str = "0";
		Object[][] branchData = null;
		String allBrnchQuery = "SELECT AUTH_ALL_BRNCH FROM TMS_MANG_AUTH_HDR WHERE AUTH_ACCOUNTENT="
				+ accntRepBean.getUserEmpId()
				+ " AND AUTH_STATUS='A' ORDER BY AUTH_ALL_BRNCH DESC";
		Object[][] allBrnch = getSqlModel().getSingleResult(allBrnchQuery);
		
		if (allBrnch != null && allBrnch.length > 0)
		{

			if (allBrnch[0][0].equals("N")) {
				String branchQuery = "SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR WHERE  AUTH_ACCOUNTENT="
						+ accntRepBean.getUserEmpId() + "AND AUTH_STATUS='A'";
				branchData = getSqlModel().getSingleResult(branchQuery);
				if (branchData != null && branchData.length > 0) {
					for (int i = 0; i < branchData.length; i++) {
						if (i == 0) {
							str = "" + branchData[i][0];
						} else {
							str += "," + branchData[i][0];
						}

					}// end of for
				}

			}// end of if
			else if (allBrnch[0][0].equals("Y")) {
				String branchQuery = "SELECT AUTH_BRNCH_ID FROM TMS_MANG_AUTH_HDR WHERE  AUTH_ALL_BRNCH='N'"
						+ " AND AUTH_STATUS='A' AND AUTH_ACCOUNTENT!="
						+ accntRepBean.getUserEmpId();
				branchData = getSqlModel().getSingleResult(branchQuery);
				if (branchData != null && branchData.length > 0) {
					for (int i = 0; i < branchData.length; i++) {
						if (i == 0) {
							str = "" + branchData[i][0];
						} else {
							str += "," + branchData[i][0];
						}

					}// end of for

				}// end of inner if
			}// end of else if
		
		}
		
		
		try{
			String advEmpQuery = " FROM TMS_SCH_DESK "
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_SCH_DESK.DESK_APPL_ID) "
				+ " INNER JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_ID=TMS_SCH_DESK.DESK_APPL_ID AND TMS_APP_EMPDTL.APPL_CODE=TMS_SCH_DESK.DESK_APPL_CODE) "
				+ " LEFT JOIN TMS_ADV_DISBURSEMENT ON(TMS_ADV_DISBURSEMENT.TRVL_APPID=TMS_SCH_DESK.DESK_APPL_ID AND TMS_ADV_DISBURSEMENT.TRVL_APPCODE=TMS_SCH_DESK.DESK_APPL_CODE ) "  
				+ " INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_APP_EMPDTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_APP_EMPDTL.APPL_CODE) " 
				+ " INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE) "  
				+ " INNER JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE) "  
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE) "  
				+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "  
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "  
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) "
				+ " WHERE 1=1 ";			
			if (allBrnch[0][0].equals("N")) {
				advEmpQuery += " AND CENTER_ID IN (" + str + ")";
			} else if (allBrnch[0][0].equals("Y")) {
				advEmpQuery += " AND CENTER_ID NOT IN (" + str + ")";
			}
			
			String advGuestQuery = " FROM TMS_SCH_DESK "
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_SCH_DESK.DESK_APPL_ID) " 
				+ " LEFT JOIN TMS_APP_GUEST_DTL ON(TMS_APP_GUEST_DTL.APPL_ID=TMS_SCH_DESK.DESK_APPL_ID AND TMS_APP_GUEST_DTL.APPL_CODE=TMS_SCH_DESK.DESK_APPL_CODE) "
				+ " left JOIN TMS_ADV_DISBURSEMENT ON(TMS_ADV_DISBURSEMENT.TRVL_APPID=TMS_SCH_DESK.DESK_APPL_ID AND TMS_ADV_DISBURSEMENT.TRVL_APPCODE=TMS_SCH_DESK.DESK_APPL_CODE ) "  
				+ " INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE) "  
				+ " INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE) "  
				+ " INNER JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE) "  
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR) "  
				+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "   
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "  
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) "
				+ " WHERE 1=1 ";
			
			if (allBrnch[0][0].equals("N")) {
				advGuestQuery += " AND CENTER_ID IN (" + str + ")";
			} else if (allBrnch[0][0].equals("Y")) {
				advGuestQuery += " AND CENTER_ID NOT IN (" + str + ")";
			}	
			
			String expQuery = " FROM TMS_CLAIM_APPL "
							+ " LEFT JOIN TMS_EXP_DISBURSEMENT on(TMS_EXP_DISBURSEMENT.EXP_DISB_EXP_ID=TMS_CLAIM_APPL.EXP_APPID " ;
				if(accntRepBean.getTypeExp().equals("C") && accntRepBean.getStatus().equals("B") ) {
					expQuery += "AND EXP_DISB_STATUS not in ('P','C','X') ";
				}
				expQuery += " ) " 
						+ " LEFT JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_EXP_DISBURSEMENT.EXP_DISB_ID) "  
						+ " LEFT JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_CLAIM_APPL.EXP_TRVL_APPCODE) "  
						+ " LEFT JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE) "  
						+ " LEFT JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE) "  
						+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_CLAIM_APPL.EXP_APP_EMPID) "  
						+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "   
						+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "  
						+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)  "
						+ " WHERE 1=1 ";
			
			if (allBrnch[0][0].equals("N")) {
				expQuery += " AND CENTER_ID IN (" + str + ")";
			} else if (allBrnch[0][0].equals("Y")) {
				expQuery += " AND CENTER_ID NOT IN (" + str + ")";
			}
			

			/*
			String expGuestQuery = " FROM TMS_EXP_DISBURSEMENT "
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_EXP_DISBURSEMENT.EXP_DISB_ID) "
				+ " LEFT JOIN TMS_CLAIM_APPL ON(TMS_CLAIM_APPL.EXP_APPID=TMS_EXP_DISBURSEMENT.EXP_DISB_ID) "
				+ " LEFT JOIN TMS_APP_GUEST_DTL ON(TMS_APP_GUEST_DTL.APPL_ID=TMS_CLAIM_APPL.EXP_TRVL_APPID AND TMS_APP_GUEST_DTL.APPL_CODE=TMS_CLAIM_APPL.EXP_TRVL_APPCODE) "
				+ " INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE) "
				+ " INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE) "
				+ " INNER JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE) "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR) "
				+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)  "
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) " 
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) "
				+ " WHERE 1=1 AND TMS_CLAIM_APPL.EXP_APP_STATUS = 'A' ";
			
			if (allBrnch[0][0].equals("N")) {
				expGuestQuery += " AND CENTER_ID IN (" + str + ")";
			} else if (allBrnch[0][0].equals("Y")) {
				expGuestQuery += " AND CENTER_ID NOT IN (" + str + ")";
			}
			*/
			if(!(accntRepBean.getTypeExp().equals(""))) {
				if(accntRepBean.getTypeExp().equals("A")) {
					if(!(accntRepBean.getStatus().equals(""))) {
						if(accntRepBean.getStatus().equals("B")) {
							//Pending
							advEmpQuery += "AND ((DESK_STATUS = 'A' or DESK_STATUS = 'S')  AND " 
										+ " TMS_APP_EMPDTL.APPL_TRVL_ID  NOT LIKE '%TRVL_'||TMS_ADV_DISBURSEMENT.TRVL_APPID||'_'||TMS_ADV_DISBURSEMENT.TRVL_APPCODE "
										+ " AND TMS_APP_EMPDTL.APPL_EMP_ADVANCE_AMT >0 )"
										+ " AND TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY')<= TO_CHAR(SYSDATE,'DD-MM-YYYY')";
							advGuestQuery += " AND ((DESK_STATUS = 'A' or DESK_STATUS = 'S')  AND " 
										+ " TMS_APP_GUEST_DTL.APPL_TRVL_ID  NOT LIKE '%TRVL_'||TMS_ADV_DISBURSEMENT.TRVL_APPID||'_'||TMS_ADV_DISBURSEMENT.TRVL_APPCODE " 
										+ " AND TMS_APP_GUEST_DTL.GUEST_ADVANCE_AMT >0) "
										+ " AND TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY')<= TO_CHAR(SYSDATE,'DD-MM-YYYY') ";
						}
						else {
							//Partially Paid,Closed
							advEmpQuery += "AND TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS = '"+accntRepBean.getStatus()+"'";
							advGuestQuery += "AND TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS = '"+accntRepBean.getStatus()+"'";
						}
					}
					else{
						//General
						advEmpQuery += " AND ( ((DESK_STATUS = 'A' or DESK_STATUS = 'S')  AND " 
										+ " TMS_APP_EMPDTL.APPL_TRVL_ID  NOT LIKE '%TRVL_'||TMS_ADV_DISBURSEMENT.TRVL_APPID||'_'||TMS_ADV_DISBURSEMENT.TRVL_APPCODE "
										+ " AND TMS_APP_EMPDTL.APPL_EMP_ADVANCE_AMT >0 ) "
										+ " OR TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS = 'P' OR TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS = 'C' ) "
										+ " AND TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY')<= TO_CHAR(SYSDATE,'DD-MM-YYYY')";
						advGuestQuery += " AND ( ((DESK_STATUS = 'A' or DESK_STATUS = 'S')  AND "
										+ " TMS_APP_GUEST_DTL.APPL_TRVL_ID NOT LIKE '%TRVL_'||TMS_ADV_DISBURSEMENT.TRVL_APPID||'_'||TMS_ADV_DISBURSEMENT.TRVL_APPCODE  AND  TMS_APP_GUEST_DTL.GUEST_ADVANCE_AMT >0 ) "  
										+ " OR TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS = 'P' OR TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS = 'C' )  "
										+ " AND TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY')<= TO_CHAR(SYSDATE,'DD-MM-YYYY') ";
					}
				}//End of Advance
				else if(accntRepBean.getTypeExp().equals("C")){
					if(!(accntRepBean.getStatus().equals(""))) {
						if(accntRepBean.getStatus().equals("B")) {
							//Pending
							expQuery += " AND (EXP_APP_STATUS ='A')";
						}
						else {
							//Partially Paid,Closed,Over Payment
							expQuery += " AND TMS_EXP_DISBURSEMENT.EXP_DISB_STATUS='"+accntRepBean.getStatus().trim()+"'";
						}
					}
					else{
						//General
						expQuery += " AND (EXP_APP_STATUS ='A' OR TMS_EXP_DISBURSEMENT.EXP_DISB_STATUS='P' OR "
									+ " TMS_EXP_DISBURSEMENT.EXP_DISB_STATUS='X' OR TMS_EXP_DISBURSEMENT.EXP_DISB_STATUS='C') ";
					}
				}//End of Claim
			}
/*			//STATUS
			if(!(accntRepBean.getStatus().equals(""))) {
				advEmpQuery += " AND TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS = '"+accntRepBean.getStatus()+"'";
				advGuestQuery += " AND TMS_ADV_DISBURSEMENT.ADV_DISB_STATUS = '"+accntRepBean.getStatus()+"'";
				expQuery += " AND TMS_EXP_DISBURSEMENT.EXP_DISB_STATUS = '"+accntRepBean.getStatus()+"'";
				//expGuestQuery += " AND TMS_EXP_DISBURSEMENT.EXP_DISB_STATUS = '"+accntRepBean.getStatus()+"'";
			}*/
			//GRADE
			if(!(accntRepBean.getGradeId().equals("")) && !(accntRepBean.getGradeId() == null)
					&& !(accntRepBean.getGradeId().equals("null")) ) {
				advEmpQuery += " AND HRMS_EMP_OFFC.EMP_CADRE = "+accntRepBean.getGradeId();
				advGuestQuery += " AND HRMS_EMP_OFFC.EMP_CADRE = "+accntRepBean.getGradeId();
				expQuery += " AND HRMS_EMP_OFFC.EMP_CADRE = "+accntRepBean.getGradeId();
				//expGuestQuery += " AND HRMS_EMP_OFFC.EMP_CADRE = "+accntRepBean.getGradeId();
			}
			//BRANCH
			if(!(accntRepBean.getBranchId().equals("")) && !(accntRepBean.getBranchId() == null) 
					&& !(accntRepBean.getBranchId().equals("null")) ){
				advEmpQuery += " AND HRMS_EMP_OFFC.EMP_CENTER = "+accntRepBean.getBranchId();
				advGuestQuery += " AND HRMS_EMP_OFFC.EMP_CENTER = "+accntRepBean.getBranchId();
				expQuery += " AND HRMS_EMP_OFFC.EMP_CENTER = "+accntRepBean.getBranchId();
				//expGuestQuery += " AND HRMS_EMP_OFFC.EMP_CENTER = "+accntRepBean.getBranchId();
			}
			//DEPARTMENT
			if(!(accntRepBean.getDeptId().equals("")) && !(accntRepBean.getDeptId() == null)
					&& !(accntRepBean.getDeptId().equals("null")) ) {
				advEmpQuery += " AND HRMS_EMP_OFFC.EMP_DEPT = "+accntRepBean.getDeptId();
				advGuestQuery += " AND HRMS_EMP_OFFC.EMP_DEPT = "+accntRepBean.getDeptId();
				expQuery += " AND HRMS_EMP_OFFC.EMP_DEPT = "+accntRepBean.getDeptId();
				//expGuestQuery += " AND HRMS_EMP_OFFC.EMP_DEPT = "+accntRepBean.getDeptId();
			}
			//ADVANCE AMOUNT
			if(!(accntRepBean.getAdvAmtSelect().equals(""))){
				if(!(accntRepBean.getAdvAmtSelect().equals("BN"))){
					advEmpQuery += " AND TMS_ADV_DISBURSEMENT.ADV_DISB_ADVANCE_AMT "
									+getAdvanceFilter(accntRepBean.getAdvAmtSelect())
									+accntRepBean.getAdvAmtFrom();
					advGuestQuery += " AND TMS_ADV_DISBURSEMENT.ADV_DISB_ADVANCE_AMT "
									+getAdvanceFilter(accntRepBean.getAdvAmtSelect())
									+accntRepBean.getAdvAmtFrom();
					expQuery += " AND TMS_EXP_DISBURSEMENT.EXP_DISB_ADVANCE_AMT "
									+getAdvanceFilter(accntRepBean.getAdvAmtSelect())
									+accntRepBean.getAdvAmtFrom();
					/*expGuestQuery += " AND TMS_EXP_DISBURSEMENT.EXP_DISB_ADVANCE_AMT "
									+getAdvanceFilter(accntRepBean.getAdvAmtSelect())
									+accntRepBean.getAdvAmtFrom();*/
				}
				if(accntRepBean.getAdvAmtSelect().equals("BN")){
					advEmpQuery += " AND TMS_ADV_DISBURSEMENT.ADV_DISB_ADVANCE_AMT "
									+ ">= "+accntRepBean.getAdvAmtFrom()
								+ " AND TMS_ADV_DISBURSEMENT.ADV_DISB_ADVANCE_AMT "
									+ "<= "+accntRepBean.getAdvAmtTo();
					advGuestQuery += " AND TMS_ADV_DISBURSEMENT.ADV_DISB_ADVANCE_AMT "
									+ ">= "+accntRepBean.getAdvAmtFrom()
								+ " AND TMS_ADV_DISBURSEMENT.ADV_DISB_ADVANCE_AMT "
									+ "<= "+accntRepBean.getAdvAmtTo();
					expQuery += " AND TMS_EXP_DISBURSEMENT.EXP_DISB_ADVANCE_AMT "
									+ ">= "+accntRepBean.getAdvAmtFrom()
								+ " AND TMS_EXP_DISBURSEMENT.EXP_DISB_ADVANCE_AMT "
									+ "<= "+accntRepBean.getAdvAmtTo();
					/*expGuestQuery += " AND TMS_EXP_DISBURSEMENT.EXP_DISB_ADVANCE_AMT "
									+ ">= "+accntRepBean.getAdvAmtFrom()
								+ " AND TMS_EXP_DISBURSEMENT.EXP_DISB_ADVANCE_AMT "
									+ "<= "+accntRepBean.getAdvAmtTo();*/
				}
			}
			//ADVANCE CLAIM SETTLEMENT
			if(!(accntRepBean.getClaimsetSelect().equals(""))) {
				if(!(accntRepBean.getClaimsetSelect().equals("BN"))){
					advEmpQuery += " AND TMS_ADV_DISBURSEMENT.ADV_DISB_ADVANCE_AMT - TMS_ADV_DISBURSEMENT.ADV_DISB_BALANCE_AMT "
										+getAdvanceFilter(accntRepBean.getClaimsetSelect())
										+accntRepBean.getClaimsetFrom();
					advGuestQuery += " AND TMS_ADV_DISBURSEMENT.ADV_DISB_ADVANCE_AMT - TMS_ADV_DISBURSEMENT.ADV_DISB_BALANCE_AMT "
										+getAdvanceFilter(accntRepBean.getClaimsetSelect())
										+accntRepBean.getClaimsetFrom();
					expQuery += " AND TMS_EXP_DISBURSEMENT.EXP_DISB_ADVANCE_AMT - TMS_EXP_DISBURSEMENT.EXP_DISB_BALANCE_AMT "
										+getAdvanceFilter(accntRepBean.getClaimsetSelect())
										+accntRepBean.getClaimsetFrom();
					/*expGuestQuery += " AND TMS_EXP_DISBURSEMENT.EXP_DISB_ADVANCE_AMT - TMS_EXP_DISBURSEMENT.EXP_DISB_BALANCE_AMT "
										+getAdvanceFilter(accntRepBean.getClaimsetSelect())
										+accntRepBean.getClaimsetFrom();*/
				}
				if(accntRepBean.getClaimsetSelect().equals("BN")){
					advEmpQuery += " AND TMS_ADV_DISBURSEMENT.ADV_DISB_ADVANCE_AMT - TMS_ADV_DISBURSEMENT.ADV_DISB_BALANCE_AMT "
										+ ">= "+accntRepBean.getClaimsetFrom()
								+ " AND TMS_ADV_DISBURSEMENT.ADV_DISB_ADVANCE_AMT - TMS_ADV_DISBURSEMENT.ADV_DISB_BALANCE_AMT "
										+ "<= "+accntRepBean.getClaimsetTo();
					advGuestQuery += " AND TMS_ADV_DISBURSEMENT.ADV_DISB_ADVANCE_AMT - TMS_ADV_DISBURSEMENT.ADV_DISB_BALANCE_AMT "
										+ ">= "+accntRepBean.getClaimsetFrom()
								+ " AND TMS_ADV_DISBURSEMENT.ADV_DISB_ADVANCE_AMT - TMS_ADV_DISBURSEMENT.ADV_DISB_BALANCE_AMT "
										+ "<= "+accntRepBean.getClaimsetTo();
					expQuery += " AND TMS_EXP_DISBURSEMENT.EXP_DISB_ADVANCE_AMT - TMS_EXP_DISBURSEMENT.EXP_DISB_BALANCE_AMT "
										+ ">= "+accntRepBean.getClaimsetFrom()
								+ " AND TMS_EXP_DISBURSEMENT.EXP_DISB_ADVANCE_AMT - TMS_EXP_DISBURSEMENT.EXP_DISB_BALANCE_AMT "
										+ "<= "+accntRepBean.getClaimsetTo();
					/*expGuestQuery += " AND TMS_EXP_DISBURSEMENT.EXP_DISB_ADVANCE_AMT - TMS_EXP_DISBURSEMENT.EXP_DISB_BALANCE_AMT "
										+ ">= "+accntRepBean.getClaimsetFrom()
								+ " AND TMS_EXP_DISBURSEMENT.EXP_DISB_ADVANCE_AMT - TMS_EXP_DISBURSEMENT.EXP_DISB_BALANCE_AMT "
										+ "<= "+accntRepBean.getClaimsetTo();*/
				}
			}
			//APPLICATION FOR
			if(!(accntRepBean.getAppliFor().equals(""))){
				advEmpQuery += " AND TMS_APPLICATION.APPL_FOR_FLAG = '"+accntRepBean.getAppliFor()+"'";
				advGuestQuery += " AND TMS_APPLICATION.APPL_FOR_FLAG = '"+accntRepBean.getAppliFor()+"'";
				expQuery += " AND TMS_APPLICATION.APPL_FOR_FLAG = '"+accntRepBean.getAppliFor()+"'";
				//expGuestQuery += " AND TMS_APPLICATION.APPL_FOR_FLAG = '"+accntRepBean.getAppliFor()+"'";
			}
			//TRAVEL CHECK
			if(accntRepBean.getTravelCheck().equals("true")) {
				String tmp = "";
				if(accntRepBean.getHidTravelSelf().equals("checked"))
					tmp = "S";
				else
					tmp = "C";
				advEmpQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ARR_DONE = '"+tmp+"'";
				advGuestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ARR_DONE = '"+tmp+"'";
				expQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ARR_DONE = '"+tmp+"'";
				//expGuestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ARR_DONE = '"+tmp+"'";
			}
			//ACCOMMODATION CHECK
			if(accntRepBean.getAccomCheck().equals("true")) {
				String tmp = "";
				if(accntRepBean.getHidAccomSelf().equals("checked"))
					tmp = "S";
				else
					tmp = "C";
				advEmpQuery += " AND TMS_APP_TOUR_DTL.TOUR_ACCOM_ARR_DONE = '"+tmp+"'";
				advGuestQuery += " AND TMS_APP_TOUR_DTL.TOUR_ACCOM_ARR_DONE = '"+tmp+"'";
				expQuery += " AND TMS_APP_TOUR_DTL.TOUR_ACCOM_ARR_DONE = '"+tmp+"'";
				//expGuestQuery += " AND TMS_APP_TOUR_DTL.TOUR_ACCOM_ARR_DONE = '"+tmp+"'";
			}
			//LOCAL CONVEYANCE CHECK
			if(accntRepBean.getLocalCheck().equals("true")) {
				String tmp = "";
				if(accntRepBean.getHidLocalSelf().equals("checked"))
					tmp = "S";
				else
					tmp = "C";
				advEmpQuery += " AND TMS_APP_TOUR_DTL.TOUR_CONV_ARR_DONE = '"+tmp+"'";
				advGuestQuery += " AND TMS_APP_TOUR_DTL.TOUR_CONV_ARR_DONE = '"+tmp+"'";
				expQuery += " AND TMS_APP_TOUR_DTL.TOUR_CONV_ARR_DONE = '"+tmp+"'";
				//expGuestQuery += " AND TMS_APP_TOUR_DTL.TOUR_CONV_ARR_DONE = '"+tmp+"'";
			}
			
			//TRAVEL TYPE
			if(!(accntRepBean.getTrvlType().equals("")) && !(accntRepBean.getTrvlType() == null) 
					&& !(accntRepBean.getTrvlType().equals("null"))) {
				advEmpQuery += " AND HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID = "+accntRepBean.getTrvlType();
				advGuestQuery += " AND HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID = "+accntRepBean.getTrvlType();
				expQuery += " AND HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID = "+accntRepBean.getTrvlType();
				//expGuestQuery += " AND HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID = "+accntRepBean.getTrvlType();
			}
			//TRAVEL PURPOSE
			if(!(accntRepBean.getTrvlPurpose().equals("")) && !(accntRepBean.getTrvlPurpose() == null)
					&& !(accntRepBean.getTrvlPurpose().equals("null"))) {
				advEmpQuery += " AND HRMS_TMS_PURPOSE.PURPOSE_ID = "+accntRepBean.getTrvlPurpose();
				advGuestQuery += " AND HRMS_TMS_PURPOSE.PURPOSE_ID = "+accntRepBean.getTrvlPurpose();
				expQuery += " AND HRMS_TMS_PURPOSE.PURPOSE_ID = "+accntRepBean.getTrvlPurpose();
				//expGuestQuery += " AND HRMS_TMS_PURPOSE.PURPOSE_ID = "+accntRepBean.getTrvlPurpose();
			}
			//TRAVEL START DATE
			if(!(accntRepBean.getStartDateSelect().equals(""))) {
				if(!accntRepBean.getStartDateSelect().equals("BN")){
					advEmpQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT "
										+getAdvanceFilter(accntRepBean.getStartDateSelect())
										+" TO_DATE('"+accntRepBean.getStartFromDate()+"','DD-MM-YYYY')";
					advGuestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT "
										+getAdvanceFilter(accntRepBean.getStartDateSelect())
										+" TO_DATE('"+accntRepBean.getStartFromDate()+"','DD-MM-YYYY')";
					expQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT "
										+getAdvanceFilter(accntRepBean.getStartDateSelect())
										+" TO_DATE('"+accntRepBean.getStartFromDate()+"','DD-MM-YYYY')";
					/*expGuestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT "
										+getAdvanceFilter(accntRepBean.getStartDateSelect())
										+" TO_DATE('"+accntRepBean.getStartFromDate()+"','DD-MM-YYYY')";*/
				}
				if(accntRepBean.getStartDateSelect().equals("BN")) {
					advEmpQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT "
										+" >= TO_DATE('"+accntRepBean.getStartFromDate()+"','DD-MM-YYYY')"
								+ " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT "
										+" <= TO_DATE('"+accntRepBean.getStartToDate()+"','DD-MM-YYYY')";
					advGuestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT "
										+" >= TO_DATE('"+accntRepBean.getStartFromDate()+"','DD-MM-YYYY')"
								+ " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT "
										+" <= TO_DATE('"+accntRepBean.getStartToDate()+"','DD-MM-YYYY')";
					expQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT "
										+" >= TO_DATE('"+accntRepBean.getStartFromDate()+"','DD-MM-YYYY')"
								+ " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT "
										+" <= TO_DATE('"+accntRepBean.getStartToDate()+"','DD-MM-YYYY')";
					/*expGuestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT "
										+" >= TO_DATE('"+accntRepBean.getStartFromDate()+"','DD-MM-YYYY')"
								+ " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_STARTDT "
										+" <= TO_DATE('"+accntRepBean.getStartToDate()+"','DD-MM-YYYY')";*/
				}
			}
			//TRAVEL END DATE
			if(!(accntRepBean.getEndDateSelect().equals(""))) {
				if(!accntRepBean.getEndDateSelect().equals("BN")){
					advEmpQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT "
										+getAdvanceFilter(accntRepBean.getEndDateSelect())
										+" TO_DATE('"+accntRepBean.getEndFromDate()+"','DD-MM-YYYY')";
					advGuestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT "
										+getAdvanceFilter(accntRepBean.getEndDateSelect())
										+" TO_DATE('"+accntRepBean.getEndFromDate()+"','DD-MM-YYYY')";
					expQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT "
										+getAdvanceFilter(accntRepBean.getEndDateSelect())
										+" TO_DATE('"+accntRepBean.getEndFromDate()+"','DD-MM-YYYY')";
					/*expGuestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT "
										+getAdvanceFilter(accntRepBean.getEndDateSelect())
										+" TO_DATE('"+accntRepBean.getEndFromDate()+"','DD-MM-YYYY')";*/
				}
				if(accntRepBean.getEndDateSelect().equals("BN")) {
					advEmpQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT "
										+" >= TO_DATE('"+accntRepBean.getEndFromDate()+"','DD-MM-YYYY')"
								+ " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT "
										+" <= TO_DATE('"+accntRepBean.getEndToDate()+"','DD-MM-YYYY')";
					advGuestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT "
										+" >= TO_DATE('"+accntRepBean.getEndFromDate()+"','DD-MM-YYYY')"
								+ " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT "
										+" <= TO_DATE('"+accntRepBean.getEndToDate()+"','DD-MM-YYYY')";
					expQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT "
										+" >= TO_DATE('"+accntRepBean.getEndFromDate()+"','DD-MM-YYYY')"
								+ " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT "
										+" <= TO_DATE('"+accntRepBean.getEndToDate()+"','DD-MM-YYYY')";
/*					expGuestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT "
										+" >= TO_DATE('"+accntRepBean.getEndFromDate()+"','DD-MM-YYYY')"
								+ " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ENDDT "
										+" <= TO_DATE('"+accntRepBean.getEndToDate()+"','DD-MM-YYYY')";*/
				}
			}
			//Sorting code
			/*
			 * Here tmp is temporary string to carry order by stmt,
			 * at last we add this part to all 4 queries
			 */
			String tmp = "";
			if(!accntRepBean.getSortBy().equals("1") || !accntRepBean.getThenBy1().equals("1") || 
					!accntRepBean.getThenBy2().equals("1")){
				tmp += " ORDER BY ";
			}
			if (!accntRepBean.getSortBy().equals("1")) {
				tmp += getSortVal(accntRepBean.getSortBy(), labelNames) + " "
						+ getSortOrder(accntRepBean.getSortByOrder());
				if (!accntRepBean.getThenBy1().equals("1")
						|| !accntRepBean.getThenBy2().equals("1")) {
					tmp += " , ";
				}
			}

			if (!accntRepBean.getThenBy1().equals("1")) {
				tmp += getSortVal(accntRepBean.getThenBy1(), labelNames) + " "
						+ getSortOrder(accntRepBean.getThenByOrder1());
				logger.info("bean.getThenByOrder1() -- > "+accntRepBean.getThenByOrder1());
				if (!accntRepBean.getThenBy2().equals("1")) {
					tmp += " , ";
				}
			}

			if (!accntRepBean.getThenBy2().equals("1")) {
				tmp += getSortVal(accntRepBean.getThenBy2(), labelNames) + " "
						+ getSortOrder(accntRepBean.getThenByOrder2());
			}
			//advEmpQuery += tmp;
			//expEmpQuery += tmp;
			//advGuestQuery += tmp;
			//expGuestQuery += tmp;
			
			Object[][] condQueryString = new Object[1][4];
			condQueryString[0][0] = advEmpQuery;
			condQueryString[0][1] = advGuestQuery;
			condQueryString[0][2] = expQuery;
			//condQueryString[0][3] = expGuestQuery;
			condQueryString[0][3] = tmp; //Order by part
			return condQueryString;
		}catch(Exception e){
			logger.error("Exception in Condition Query -- "+e);
			return null;
		}
	}
	
	public String getAdvanceFilter(String Status) {
		String sql = "";
		if (Status.equals("IE") || Status.equals("ON")) {
			sql = "=";
		}
		if (Status.equals("LE") || Status.equals("OB")) {
			sql = "<=";
		}

		if (Status.equals("GE") || Status.equals("OA")) {
			sql = ">=";
		}
		if (Status.equals("LT") || Status.equals("BF")) {
			sql = "<";
		}
		if (Status.equals("GT") || Status.equals("AF")) {
			sql = ">";
		}
		return sql;
	}

	public String getSortVal(String Status, String[] labelNames) {
		logger.info("Sort Status............" + Status);
		String sql = "";
		if (Status.equals(labelNames[0]))
			sql = " APPLICANTNAME";
		else if (Status.equals(labelNames[1]))
			sql = " GRADE ";
		else if (Status.equals(labelNames[2]))
			sql = " BRANCH ";
		else if (Status.equals(labelNames[3]))
			sql = " TOKEN ";
		else if (Status.equals(labelNames[4]))
			sql = " STARTDATE ";
		else if (Status.equals(labelNames[5]))
			sql = " ENDDATE ";
		else if (Status.equals(labelNames[6]))
			sql = " STATUS ";
		else if (Status.equals(labelNames[7]))
			sql = " PURPOSE ";
		else if (Status.equals(labelNames[8]))
			sql = " TRAVELTYPE ";
		else if (Status.equals(labelNames[9]))
			sql = " ADV_AMT ";
		else if (Status.equals("Travel Id"))
			sql = " TRAVELID ";
		logger.info("sql -- "+sql);
		return sql;
	}

	public String getSortOrder(String Status) {
		String sql = "";
		if (Status.equals("A")) {
			sql = "ASC";
		}
		if (Status.equals("D")) {
			sql = "DESC";
		}
		return sql;
	}
	
	public void getReport(TrvlAccntReport bean,
			HttpServletResponse response, String[] labelNames,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
			String reportType = "";
			if (bean.getReportType().equals("P")) {
				reportType = "Pdf";
			}
			if (bean.getReportType().equals("X")) {
				reportType = "Xls";
			}
			if (bean.getReportType().equals("T")) {
				reportType = "Txt";
			}
			logger.info("reportType--------------->" + reportType + "<-------");
			String reportName = "";
			if (!bean.getReportTitle().equals(""))
				reportName = bean.getReportTitle();
			else
				reportName = "Report for Accountant";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					reportType, reportName,"");
			rg.addText("\n", 0, 0, 0);
			try{
			logger.info("report columns -------- "+bean.getHiddenColumns());
			String multiSelectValues = bean.getHiddenColumns();
			String splitComma[] = null;
			if(!multiSelectValues.equals("")){
				String lastComma = multiSelectValues.substring(0, multiSelectValues.length()-1);
				splitComma = lastComma.split(",");
			}//End of if
			int count = 0;
			// Select Query with Count 
			Object[][] selectQueryObj = selectQuery(bean,labelNames,count,splitComma,request);
			String advEmpSelectQuery = (String) selectQueryObj[0][0];
			String advGuestSelectQuery = (String) selectQueryObj[0][1];
			String expSelectQuery = (String) selectQueryObj[0][2];
			//String expGuestSelectQuery = (String) selectQueryObj[0][3];
			String labels = (String) selectQueryObj[0][3];
			count = Integer.parseInt((String) selectQueryObj[0][4]);
			logger.info("counter after select query==========" + count);
			logger.info("labels after select query==========" + labels);
			
			// CONDITION QUERY
			Object[][] condQueryObj = conditionQuery(bean, labelNames); 
			String advEmpConditionQuery = (String) condQueryObj[0][0];
			String advGuestConditionQuery = (String) condQueryObj[0][1];
			String expConditionQuery = (String) condQueryObj[0][2];
			//String expGuestConditionQuery = (String) condQueryObj[0][3];
			String orderByStmt = (String) condQueryObj[0][3];
			//CREATING QUERIES
			String advEmpQuery = advEmpSelectQuery + advEmpConditionQuery;
			String advGuestQuery = advGuestSelectQuery + advGuestConditionQuery;
			String expQuery = expSelectQuery + expConditionQuery;
			//String expGuestQuery = expGuestSelectQuery + expGuestConditionQuery;
			
			//FINAL QUERY
			String finalQuery = "";
			//Check TYPE OF EXPENSE
			if(!(bean.getTypeExp().equals(""))) {
				if(bean.getTypeExp().equals("A")) {
					finalQuery = advEmpQuery + " UNION " + advGuestQuery;
				}
				if(bean.getTypeExp().equals("C")) {
					finalQuery = expQuery;
				}
			}
			/*else{
				//union of 4 queries
				finalQuery = advEmpQuery  + " UNION " +
							advGuestQuery + " UNION " + 
							expEmpQuery  + " UNION " + 
							expGuestQuery;
			}*/
			finalQuery +=orderByStmt;
			logger.info("\finalQuery -- ----------------------->\n"+finalQuery);
			Object finalObj[][] = null;
			finalObj = getSqlModel().getSingleResult(finalQuery);
			logger.info("finalObj.length - "+finalObj.length);
			
			// CODING FOR HEADERS, WIDTH AND ALIGNMENTS
			String[] str_colNames = new String[count + 1];
			str_colNames[0] = "Travel Id";
			int str_colNames_array = 0;
			int[] cellWidth = new int[count + 1];
			cellWidth[0] = 10;
			int cellWidth_array = 0;
			int[] cellAlign = new int[count + 1];
			cellAlign[0] = 0;
			int cellAlign_array = 0;
			
			if (splitComma != null && splitComma.length > 0) {
				for (int i = 0; i < splitComma.length; i++) {
					String splitDash[] = splitComma[i].split("-");
					logger.info("Key....." + splitDash[0]);
					logger.info("Value....." + splitDash[1]);
					if(splitDash[1].equals(labelNames[0].trim())){				//APPLICANTNAME
						str_colNames[++str_colNames_array] = labelNames[0];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[1].trim())){			//GRADE
						str_colNames[++str_colNames_array] = labelNames[1];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[2].trim())){			//BRANCH
						str_colNames[++str_colNames_array] = labelNames[2];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[3].trim())){			//EMPLOYEE ID
						str_colNames[++str_colNames_array] = labelNames[3];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[4].trim())){			//TRAVEL START DATE
						str_colNames[++str_colNames_array] = labelNames[4];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 1;
					}
					else if(splitDash[1].equals(labelNames[5].trim())){			//TRAVEL END DATE
						str_colNames[++str_colNames_array] = labelNames[5];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 1;
					}
					else if(splitDash[1].equals(labelNames[6].trim())){			//STATUS
						str_colNames[++str_colNames_array] = labelNames[6];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[7].trim())){			//TRAVEL PURPOSE
						str_colNames[++str_colNames_array] = labelNames[7];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[8].trim())){			//TRAVEL TYPE
						str_colNames[++str_colNames_array] = labelNames[8];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[9].trim())){			//ADVANCE AMOUNT
						str_colNames[++str_colNames_array] = labelNames[9];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}					
				}//End of for
			}//End of if
			
			logger.info("Export all values   : " + bean.getExportAll());
			logger.info("counter for exporting==========" + bean.getReqStatus());
			if(finalObj != null && finalObj.length > 0){
				if(bean.getReqStatus().trim().equals("R"))
					bean.setExportAll("on");	
				if (bean.getExportAll().equals("on")) {
					if (bean.getReportType().equals("P")) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText("\n", 0, 0, 0);
						rg.tableBody(str_colNames, finalObj, cellWidth,
								cellAlign);
					} else if (bean.getReportType().equals("X")) {
						//rg.setFName(reportName + ".xls");
						rg.setFName(reportName);
						rg.addText(reportName, 0, 1, 0);
						rg.addText("\n", 0, 0, 0);
						rg.xlsTableBody(str_colNames, finalObj, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + ".doc");
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(str_colNames, finalObj, cellWidth,
								cellAlign);
					}
				}else{

					String[] pageIndex = Utility.doPaging(bean.getMyPage(),
							finalObj.length, 20);
					if (pageIndex == null) {
						pageIndex[0] = "0";
						pageIndex[1] = "20";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					}
					int numOfRec = Integer.parseInt(pageIndex[1])
							- Integer.parseInt(pageIndex[0]);
					int columnLength = count + 1;
					logger.info("columnLength   : " + columnLength);
					Object[][] pageObj = new Object[numOfRec][columnLength];
					int z = 0;
					int srNo = 1;
					for (int i = Integer.parseInt(pageIndex[0]); i < Integer
							.parseInt(pageIndex[1]); i++) {
						for (int j = 0; j < columnLength; j++) {
							pageObj[z][j] = finalObj[i][j];
						}
						z++;
						srNo++;
					}
					if (bean.getReportType().equals("P")) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText("\n", 0, 0, 0);
						rg.tableBody(str_colNames, pageObj, cellWidth,
								cellAlign);
					} else if (bean.getReportType().equals("X")) {
						rg.setFName(reportName + ".xls");
						rg.addText(reportName, 0, 1, 0);
						rg.addText("\n", 0, 0, 0);
						rg.xlsTableBody(str_colNames, pageObj, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + ".doc");
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(str_colNames, pageObj, cellWidth,
								cellAlign);
					}
				}
			}else{
				rg.addFormatedText("There is no data to display.", 0, 1, 1, 0);
			}
			rg.createReport(response);
		}catch(Exception e){
			rg.addFormatedText("There is no data to display.", 0, 1, 1, 0);
			rg.createReport(response);
			logger.error("Exception in getReport - "+e);
		}
	}
}
