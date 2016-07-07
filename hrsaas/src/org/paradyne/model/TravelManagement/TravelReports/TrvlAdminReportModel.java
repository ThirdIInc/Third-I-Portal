package org.paradyne.model.TravelManagement.TravelReports;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.LinkedMap;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlAccntReport;
import org.paradyne.bean.TravelManagement.TravelReports.TrvlAdminReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
/**
 * @author ayyappa
 * @date 20-04-2010
 */
public class TrvlAdminReportModel extends ModelBase {

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
	
	public LinkedMap getFinancialYears() {
		LinkedMap hMap = new LinkedMap();
		Calendar c = Calendar.getInstance();
		for(int i=c.get(Calendar.YEAR);i>=2001;i--) {
			hMap.put(String.valueOf(i), String.valueOf(i));
		}
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
	
	public boolean deleteSavedReport(TrvlAdminReport adminRepBean) {
		// TODO Auto-generated method stub
		boolean result = false;
		String deleteDetail = "DELETE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID="
				+ adminRepBean.getReportId();
		String deleteHeader = "DELETE FROM HRMS_MISREPORT_HDR WHERE REPORT_ID="
				+ adminRepBean.getReportId();
		result = getSqlModel().singleExecute(deleteDetail);
		if (result)
			result = getSqlModel().singleExecute(deleteHeader);
		return result;
	}
	
	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(TrvlAdminReport adminRepBean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_MISREPORT_HDR WHERE REPORT_TYPE LIKE 'TravelAdmin' AND UPPER(REPORT_CRITERIA) LIKE '"
				+ adminRepBean.getSettingName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;
	}	
	
	public boolean saveReportCriteria(TrvlAdminReport adminRepBean) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (!checkDuplicate(adminRepBean)) {
			Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = checkNull(adminRepBean.getSettingName().trim());
			saveObj[0][1] = checkNull(adminRepBean.getReportTitle().trim());
			saveObj[0][2] = "TravelAdmin";
			String insertHeader = "INSERT INTO HRMS_MISREPORT_HDR (REPORT_ID, REPORT_CRITERIA, REPORT_TITLE, REPORT_TYPE)"
					+ " VALUES ((SELECT NVL(MAX(REPORT_ID),0)+1 FROM HRMS_MISREPORT_HDR), ?, ?, ?)";
			result = getSqlModel().singleExecute(insertHeader, saveObj);
			if (result) {
				String codeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
				Object[][] codeObj = getSqlModel().getSingleResult(codeQuery);
				adminRepBean.setReportId(String.valueOf(codeObj[0][0]));
				if (saveFilters(adminRepBean) && saveColumns(adminRepBean)
						&& saveSortOptions(adminRepBean)
						&& saveAdvancedFilters(adminRepBean)) {
					result = true;
				} else
					result = false;
			}
		}else {
			result = false;
		}
		return result;
	}
	
	public boolean saveFilters(TrvlAdminReport adminRepBean) {
		boolean result = false;
		int count = 0;
		
		//Financial Year
		if(!adminRepBean.getFinanYear().equals("")){
			count++;
		}
		//Grade
		if(!adminRepBean.getGradeId().equals("")){
			count++;	//code
			count++;	//name
		}
		//Travel Type
		if(!adminRepBean.getTrvlType().equals("")){
			count++;
		}
		//Application For
		if(!adminRepBean.getAppliFor().equals("")){
			count++;
		}
		//Travel - Check box
		if(adminRepBean.getTravelCheck().equals("true")){
			count++;	//check box
		}
		//TravelSelf - Radio button
		if(!adminRepBean.getHidTravelSelf().equals("")){
			count++;
		}
		//TravelComp - Radio button
		if(!adminRepBean.getHidTravelComp().equals("")){
			count++;
		}
		//Accommodation - Check box
		if(adminRepBean.getAccomCheck().equals("true")){
			count++;
		}
		//AccomSelf - Radio button
		if(!adminRepBean.getHidAccomSelf().equals("")){
			count++;
		}
		//AccomComp - Radio button
		if(!adminRepBean.getHidAccomComp().equals("")){
			count++;
		}
		//Local Conveyance - Check box
		if(adminRepBean.getLocalCheck().equals("true")){
			count++;
		}
		//LocalSelf - Radio button
		if(!adminRepBean.getHidLocalSelf().equals("")){
			count++;
		}
		//LocalComp - Radio button
		if(!adminRepBean.getHidLocalComp().equals("")){
			count++;
		}
		
		logger.info("Count for saveFilters : "+count);
		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		//Financial Year
		if(!adminRepBean.getFinanYear().equals("")){
			addObj[int_count][0] = "finanYear";
			addObj[int_count][1] = checkNull(adminRepBean.getFinanYear().trim());
			int_count++;
		}
		//Grade
		if(!adminRepBean.getGradeId().equals("")){
			//code
			addObj[int_count][0] = "adminRepBean.gradeId"; 
			addObj[int_count][1] = checkNull(adminRepBean.getGradeId().trim());
			int_count++; 
			//name
			addObj[int_count][0] = "adminRepBean.gradeName"; 
			addObj[int_count][1] = checkNull(adminRepBean.getGradeName().trim());
			int_count++; 			
		}
		//Travel Type
		if(!adminRepBean.getTrvlType().equals("")){
			addObj[int_count][0] = "trvlType";
			addObj[int_count][1] = checkNull(adminRepBean.getTrvlType().trim());
			int_count++;
		}
		//Application For
		if(!adminRepBean.getAppliFor().equals("")){
			addObj[int_count][0] = "adminRepBean.appliFor";
			addObj[int_count][1] = checkNull(adminRepBean.getAppliFor().trim());
			int_count++;
		}
		//Travel - Check box
		if(adminRepBean.getTravelCheck().equals("true")){
			addObj[int_count][0] = "adminRepBean.travelCheck";
			addObj[int_count][1] = checkNull(adminRepBean.getTravelCheck().trim());
			int_count++;
		}
		//TravelSelf - Radio button
		if(!adminRepBean.getHidTravelSelf().equals("")){
			addObj[int_count][0] = "hidTravelSelf";
			addObj[int_count][1] = checkNull(adminRepBean.getHidTravelSelf().trim());
			int_count++;
		}
		//TravelComp - Radio button
		if(!adminRepBean.getHidTravelComp().equals("")){
			addObj[int_count][0] = "hidTravelComp";
			addObj[int_count][1] = checkNull(adminRepBean.getHidTravelComp().trim());
			int_count++;
		}
		//Accommodation - Check box
		if(adminRepBean.getAccomCheck().equals("true")){
			addObj[int_count][0] = "adminRepBean.accomCheck";
			addObj[int_count][1] = checkNull(adminRepBean.getAccomCheck().trim());
			int_count++;
		}
		//AccomSelf - Radio button
		if(!adminRepBean.getHidAccomSelf().equals("")){
			addObj[int_count][0] = "hidAccomSelf";
			addObj[int_count][1] = checkNull(adminRepBean.getHidAccomSelf().trim());
			int_count++;
		}
		//AccomComp - Radio button
		if(!adminRepBean.getHidAccomComp().equals("")){
			addObj[int_count][0] = "hidAccomComp";
			addObj[int_count][1] = checkNull(adminRepBean.getHidAccomComp().trim());
			int_count++;
		}
		//Local Conveyance - Check box
		if(adminRepBean.getLocalCheck().equals("true")){
			addObj[int_count][0] = "adminRepBean.localCheck";
			addObj[int_count][1] = checkNull(adminRepBean.getLocalCheck().trim());
			int_count++;
		}
		//LocalSelf - Radio button
		if(!adminRepBean.getHidLocalSelf().equals("")){
			addObj[int_count][0] = "hidLocalSelf";
			addObj[int_count][1] = checkNull(adminRepBean.getHidLocalSelf().trim());
			int_count++;
		}
		//LocalComp - Radio button
		if(!adminRepBean.getHidLocalComp().equals("")){
			addObj[int_count][0] = "hidLocalComp";
			addObj[int_count][1] = checkNull(adminRepBean.getHidLocalComp().trim());
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
	}//End of saveFilters method
	
	public boolean saveColumns(TrvlAdminReport adminRepBean){
		boolean result = false;
		int count = 0;
		
		//Employee Id
		if(adminRepBean.getEmpIdFlag().equals("true")){
			count++;
		}
		//Applicant Name
		if(adminRepBean.getAppliNameFlag().equals("true")){
			count++;
		}
		//Grade
		if(adminRepBean.getGradeFlag().equals("true")){
			count++;
		}
		//Application Date
		if(adminRepBean.getAppliDateFlag().equals("true")){
			count++;
		}
		//Travel Id
/*		if(adminRepBean.getTravelIdFlag().equals("true")){
			count++;
		} */
		//Travel Start Date
		if(adminRepBean.getStartDateFlag().equals("true")){
			count++;
		}
		//Travel End Date
		if(adminRepBean.getEndDateFlag().equals("true")){
			count++;
		}
		//Travel Purpose
		if(adminRepBean.getPurposeFlag().equals("true")){
			count++;
		}
		//Travel Type
		if(adminRepBean.getTypeFlag().equals("true")){
			count++;
		}
		//Approved Amount
		if(adminRepBean.getApprvdAmtFlag().equals("true")){
			count++;
		}
		//Travel Cost
		if(adminRepBean.getTravelCostFlag().equals("true")) {
			count++;
		}
		//Accommodation Cost
		if(adminRepBean.getAccomCostFlag().equals("true")) {
			count++;
		}
		//Local Conveyance Cost
		if(adminRepBean.getLocalCostFlag().equals("true")) {
			count++;
		}
		logger.info("Count for saveColumns : "+count);
		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		//Employee Id
		if(adminRepBean.getEmpIdFlag().equals("true")){
			addObj[int_count][0] = "adminRepBean.empIdFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Applicant Name
		if(adminRepBean.getAppliNameFlag().equals("true")){
			addObj[int_count][0] = "adminRepBean.appliNameFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Grade
		if(adminRepBean.getGradeFlag().equals("true")){
			addObj[int_count][0] = "adminRepBean.gradeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Application Date
		if(adminRepBean.getAppliDateFlag().equals("true")){
			addObj[int_count][0] = "adminRepBean.appliDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Travel Id
	/*
		if(adminRepBean.getTravelIdFlag().equals("true")){
			addObj[int_count][0] = "adminRepBean.travelIdFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
	*/
		//Travel Start Date
		if(adminRepBean.getStartDateFlag().equals("true")){
			addObj[int_count][0] = "adminRepBean.startDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Travel End Date
		if(adminRepBean.getEndDateFlag().equals("true")){
			addObj[int_count][0] = "adminRepBean.endDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Travel Purpose
		if(adminRepBean.getPurposeFlag().equals("true")){
			addObj[int_count][0] = "adminRepBean.purposeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Travel Type
		if(adminRepBean.getTypeFlag().equals("true")){
			addObj[int_count][0] = "adminRepBean.typeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Approved Amount
		if(adminRepBean.getApprvdAmtFlag().equals("true")){
			addObj[int_count][0] = "adminRepBean.apprvdAmtFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Travel Cost
		if(adminRepBean.getTravelCostFlag().equals("true")) {
			addObj[int_count][0] = "adminRepBean.travelCostFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Accommodation Cost
		if(adminRepBean.getAccomCostFlag().equals("true")) {
			addObj[int_count][0] = "adminRepBean.accomCostFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Local Conveyance Cost
		if(adminRepBean.getLocalCostFlag().equals("true")) {
			addObj[int_count][0] = "adminRepBean.localCostFlag";
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
	}//End of saveColumns method
	
	public boolean saveSortOptions(TrvlAdminReport adminRepBean){
		boolean result = false;
		int count = 0;
		
		if(!adminRepBean.getSortBy().equals("1")) {
			count++;
		}
		if(!adminRepBean.getHiddenSortBy().equals("")){
			count++;
		}
		if(!adminRepBean.getSortByAsc().equals("")) {
			count++;
		}
		if(!adminRepBean.getSortByDsc().equals("")) {
			count++;
		}
		if(!adminRepBean.getSortByOrder().equals("")) {
			count++;
		}
		if(!adminRepBean.getThenBy1().equals("1")) {
			count++;
		}
		if(!adminRepBean.getHiddenThenBy1().equals("")){
			count++;
		}
		if(!adminRepBean.getThenByOrder1Asc().equals("")) {
			count++;
		}
		if(!adminRepBean.getThenByOrder1Dsc().equals("")) {
			count++;
		}
		if(!adminRepBean.getThenByOrder1().equals("")) {
			count++;
		}
		if(!adminRepBean.getThenBy2().equals("1")) {
			count++;
		}
		if(!adminRepBean.getHiddenThenBy2().equals("")){
			count++;
		}
		if(!adminRepBean.getThenByOrder2Asc().equals("")) {
			count++;
		}
		if(!adminRepBean.getThenByOrder2Dsc().equals("")) {
			count++;
		}
		if(!adminRepBean.getThenByOrder2().equals("")) {
			count++;
		}
		if(!adminRepBean.getHiddenColumns().equals("")){
			count++;
		}

		logger.info("Count for Save sort options : " + count);	
		

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if(!adminRepBean.getSortBy().equals("1")) {
			addObj[int_count][0] = "sortBy";
			addObj[int_count][1] = checkNull(adminRepBean.getSortBy().trim());
			int_count++;
		}
		if(!adminRepBean.getHiddenSortBy().equals("")) {
			addObj[int_count][0] = "hiddenSortBy";
			addObj[int_count][1] = checkNull(adminRepBean.getHiddenSortBy().trim());
			int_count++;
		}
		logger.info("getSortByAsc...."+adminRepBean.getSortByAsc());
		if(!adminRepBean.getSortByAsc().equals("")) {
			addObj[int_count][0] = "sortByAsc";
			addObj[int_count][1] = "Y";// checkNull(accntRepBean.getSortByAsc().trim());
			int_count++;
		}
		logger.info("getSortByDsc...."+adminRepBean.getSortByDsc());
		if(!adminRepBean.getSortByDsc().equals("")) {
			addObj[int_count][0] = "sortByDsc";
			addObj[int_count][1] = "Y";// checkNull(accntRepBean.getSortByDsc().trim());
			int_count++;
		}
		if(!adminRepBean.getSortByOrder().equals("")) {
			addObj[int_count][0] = "sortByOrder";
			addObj[int_count][1] = checkNull(adminRepBean.getSortByOrder().trim());
			int_count++;
		}
		if(!adminRepBean.getThenBy1().equals("1")) {
			addObj[int_count][0] = "thenBy1";
			addObj[int_count][1] = checkNull(adminRepBean.getThenBy1().trim());
			int_count++;
		}
		if(!adminRepBean.getHiddenThenBy1().equals("")) {
			addObj[int_count][0] = "hiddenThenBy1";
			addObj[int_count][1] = checkNull(adminRepBean.getHiddenThenBy1().trim());
			int_count++;
		}
		if(!adminRepBean.getThenByOrder1Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Asc";
			addObj[int_count][1] = "Y";// checkNull(accntRepBean.getThenByOrder1Asc().trim());
			int_count++;
		}
		if(!adminRepBean.getThenByOrder1Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Dsc";
			addObj[int_count][1] = "Y";// checkNull(accntRepBean.getThenByOrder1Dsc().trim());
			int_count++;
		}
		if(!adminRepBean.getThenByOrder1().equals("")) {
			addObj[int_count][0] = "thenByOrder1";
			addObj[int_count][1] = checkNull(adminRepBean.getThenByOrder1().trim());
			int_count++;
		}
		if(!adminRepBean.getThenBy2().equals("1")) {
			addObj[int_count][0] = "thenBy2";
			addObj[int_count][1] = checkNull(adminRepBean.getThenBy2().trim());
			int_count++;
		}
		if(!adminRepBean.getHiddenThenBy2().equals("")) {
			addObj[int_count][0] = "hiddenThenBy2";
			addObj[int_count][1] = checkNull(adminRepBean.getHiddenThenBy2().trim());
			int_count++;
		}
		if(!adminRepBean.getThenByOrder2Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Asc";
			addObj[int_count][1] = "Y";// checkNull(accntRepBean.getThenByOrder2Asc().trim());
			int_count++;
		}
		if(!adminRepBean.getThenByOrder2Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Dsc";
			addObj[int_count][1] = "Y";// checkNull(accntRepBean.getThenByOrder2Dsc().trim());
			int_count++;
		}
		if(!adminRepBean.getThenByOrder2().equals("")) {
			addObj[int_count][0] = "thenByOrder2";
			addObj[int_count][1] = checkNull(adminRepBean.getThenByOrder2().trim());
			int_count++;
		}
		if(!adminRepBean.getHiddenColumns().equals("")){
			addObj[int_count][0] = "hiddenColumns";
			addObj[int_count][1] = checkNull(adminRepBean.getHiddenColumns().trim());
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
	}//End of saveSortOptions method
	
	public boolean saveAdvancedFilters(TrvlAdminReport adminRepBean){
		boolean result = false;
		int count = 0;

		//Travel Purpose
		if(!adminRepBean.getTrvlPurpose().equals("")){
			count++;
		}
		//Branch
		if(!adminRepBean.getBranchId().equals("")){
			count++;
			count++;
		}
		//Department
		if(!adminRepBean.getDeptId().equals("")){
			count++;
			count++;
		}
		//Employee
		if(!adminRepBean.getEmpId().equals("")){
			count++;
			count++;
			count++;
		}
		logger.info("Count for Save advance filters : " + count);
		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		
		//Travel Purpose
		if(!adminRepBean.getTrvlPurpose().equals("")){
			addObj[int_count][0] = "trvlPurpose";
			addObj[int_count][1] = checkNull(adminRepBean.getTrvlPurpose().trim());
			int_count++;
		}
		//Branch
		if(!adminRepBean.getBranchId().equals("")){
			addObj[int_count][0] = "adminRepBean.branchId";
			addObj[int_count][1] = checkNull(adminRepBean.getBranchId().trim());
			int_count++;
			addObj[int_count][0] = "adminRepBean.branchName";
			addObj[int_count][1] = checkNull(adminRepBean.getBranchName().trim());
			int_count++;
		}
		//Department
		if(!adminRepBean.getDeptId().equals("")){
			addObj[int_count][0] = "adminRepBean.deptId";
			addObj[int_count][1] = checkNull(adminRepBean.getDeptId().trim());
			int_count++;
			addObj[int_count][0] = "adminRepBean.deptName";
			addObj[int_count][1] = checkNull(adminRepBean.getDeptName().trim());
			int_count++;
		}
		//Employee
		if(!adminRepBean.getEmpId().equals("")){
			addObj[int_count][0] = "adminRepBean.empId";
			addObj[int_count][1] = checkNull(adminRepBean.getEmpId().trim());
			int_count++;
			addObj[int_count][0] = "adminRepBean.empToken";
			addObj[int_count][1] = checkNull(adminRepBean.getEmpToken().trim());
			int_count++;
			addObj[int_count][0] = "adminRepBean.empName";
			addObj[int_count][1] = checkNull(adminRepBean.getEmpName().trim());
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
	}//End of saveAdvacedFilters method
	public void setDetailOnScreen(TrvlAdminReport adminRepBean) {
		// TODO Auto-generated method stub
		adminRepBean.setHidTravelComp("");
		adminRepBean.setHidAccomComp("");
		adminRepBean.setHidLocalComp("");
		String dtlQuery = "SELECT REPORT_ID, FIELD_NAME, FIELD_VALUE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID ="
			+ adminRepBean.getReportId();
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
					Method modelMethod = TrvlAdminReport.class.getDeclaredMethod(
										"set"+initCap(methodStr), String.class);
					logger.info("modelMethod - "+modelMethod);
					modelMethod.invoke(adminRepBean, dtlObj[i][2]);
				}//End of for
			}catch(Exception e){
				logger.error("Exception in setDetailOnScreen - "+e);
			}//End of try-catch
		}//End of if
	}

	public void callViewScreen(TrvlAdminReport adminRepBean,
			HttpServletRequest request, String[] labelNames) {
		// TODO Auto-generated method stub
		try{
			logger.info("report columns -------- "+adminRepBean.getHiddenColumns());
			String multiSelectValues = adminRepBean.getHiddenColumns();
			String splitComma[] = null;
			if(!multiSelectValues.equals("")){
				String lastComma = multiSelectValues.substring(0, multiSelectValues.length()-1);
				splitComma = lastComma.split(",");
			}//End of if
			int count = 0;
			// Select Query with Count 
			Object[][] selectQueryObj = selectQuery(adminRepBean,labelNames,count,splitComma,request);
			String empSelectQuery = (String) selectQueryObj[0][0];
			String guestSelectQuery = (String) selectQueryObj[0][1];
			String labels = (String) selectQueryObj[0][2];
			count = Integer.parseInt((String) selectQueryObj[0][3]);
			logger.info("counter after select query==========" + count);
			logger.info("labels after select query==========" + labels);
			
			// CONDITION QUERY
			Object[][] condQueryObj = conditionQuery(adminRepBean, labelNames); 
			String empConditionQuery = (String) condQueryObj[0][0];
			String guestConditionQuery = (String) condQueryObj[0][1];
			
			String orderByStmt = (String) condQueryObj[0][2];
			//CREATING QUERIES
			String empQuery = empSelectQuery + empConditionQuery;
			String guestQuery = guestSelectQuery + guestConditionQuery;
			
			//FINAL QUERY
			String finalQuery = empQuery + " UNION " + guestQuery + orderByStmt;
			logger.info("finalQuery -- ----------------------->\n"+finalQuery);
			Object finalObj[][] = null;
			finalObj = getSqlModel().getSingleResult(finalQuery);
			logger.info("finalObj.length - "+finalObj.length);
			
			if(finalObj != null && finalObj.length > 0){
				String[] pageIndex = Utility.doPaging(adminRepBean.getMyPage(),
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
					adminRepBean.setMyPage("1");
				
				int numOfRec = Integer.parseInt(pageIndex[1]) - Integer.parseInt(pageIndex[0]);
				int columnLength = finalObj[0].length;
				Object[][] pageObj = new Object[numOfRec][columnLength];
				int z = 0;
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					for (int j = 0; j < columnLength; j++) {
						pageObj[z][j] = finalObj[i][j];
					}
					z++;
				}
				
				request.setAttribute("labelValues", labels);
				request.setAttribute("finalObj", pageObj);
				adminRepBean.setDataLength(String.valueOf(finalObj.length));
				adminRepBean.setNoData("false");
			} else {
				request.setAttribute("totalPage", new Integer(0));
				request.setAttribute("PageNo", new Integer(0));
				adminRepBean.setNoData("true");
				request.setAttribute("labelValues", labels);
			}
		}catch(Exception e){
			logger.error("Exception in callViewScreen -- "+e);
		}
	}

	private Object[][] conditionQuery(TrvlAdminReport adminRepBean,
			String[] labelNames) {
		// TODO Auto-generated method stub
		try{
			String empQuery = " FROM TMS_APP_EMPDTL "
				+ " LEFT JOIN TMS_CLAIM_APPL ON(TMS_CLAIM_APPL.EXP_TRVL_APPID=TMS_APP_EMPDTL.APPL_ID AND TMS_CLAIM_APPL.EXP_TRVL_APPCODE=TMS_APP_EMPDTL.APPL_CODE) "
				+ " LEFT JOIN TMS_EXP_DISBURSEMENT ON(TMS_EXP_DISBURSEMENT.EXP_DISB_EXP_ID=TMS_CLAIM_APPL.EXP_APPID AND TMS_EXP_DISBURSEMENT.EXP_DISB_STATUS='C') "
				+ " LEFT JOIN TMS_MONITORING ON(TMS_MONITORING.APPL_ID=TMS_APP_EMPDTL.APPL_ID AND TMS_MONITORING.APPL_CODE=TMS_APP_EMPDTL.APPL_CODE) "
				+ " LEFT JOIN TMS_SUGG_TRAVELLING ON(TMS_SUGG_TRAVELLING.MONITOR_ID=TMS_MONITORING.TMS_MONITOR_ID AND TMS_SUGG_TRAVELLING.TVLNG_SEL_FLAG='Y' AND TMS_MONITORING.MTR_TVL_AVA_STATUS='FI') "
				+ " LEFT JOIN TMS_SUGG_ACCOM ON(TMS_SUGG_ACCOM.MONITOR_ID =TMS_MONITORING.TMS_MONITOR_ID AND TMS_SUGG_ACCOM.ACCOM_SEL_FLAG='Y' AND TMS_MONITORING.MTR_ACC_AVSTATUS= 'FI') "
				+ " LEFT JOIN TMS_SUGG_LOC_CONV ON(TMS_SUGG_LOC_CONV.MONITOR_ID =TMS_MONITORING.TMS_MONITOR_ID AND TMS_SUGG_LOC_CONV.LOCCONV_SEL_FLAG='Y' AND TMS_MONITORING.MTR_LOCAL_AVSTATUS='FI') " 
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APP_EMPDTL.APPL_ID=TMS_APPLICATION.APPL_ID) "
				+ " INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_APP_EMPDTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_APP_EMPDTL.APPL_CODE) " 
				+ " INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE) "
				+ " INNER JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE) "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APP_EMPDTL.APPL_EMP_CODE) "
				+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) "
				+ " WHERE  1=1 AND TMS_MONITOR_ID IS NOT NULL ";
			String guestQuery = " FROM TMS_APP_GUEST_DTL "
				+ " LEFT JOIN TMS_CLAIM_APPL ON(TMS_CLAIM_APPL.EXP_TRVL_APPID=TMS_APP_GUEST_DTL.APPL_ID AND TMS_CLAIM_APPL.EXP_TRVL_APPCODE=TMS_APP_GUEST_DTL.APPL_CODE) "
				+ " LEFT JOIN TMS_EXP_DISBURSEMENT ON(TMS_EXP_DISBURSEMENT.EXP_DISB_EXP_ID=TMS_CLAIM_APPL.EXP_APPID AND TMS_EXP_DISBURSEMENT.EXP_DISB_STATUS='C') "
				+ " LEFT JOIN TMS_MONITORING ON(TMS_MONITORING.APPL_ID=TMS_APP_GUEST_DTL.APPL_ID AND TMS_MONITORING.APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE) "
				+ " LEFT JOIN TMS_SUGG_TRAVELLING ON(TMS_SUGG_TRAVELLING.MONITOR_ID=TMS_MONITORING.TMS_MONITOR_ID AND TMS_SUGG_TRAVELLING.TVLNG_SEL_FLAG='Y' AND TMS_MONITORING.MTR_TVL_AVA_STATUS='FI') "
				+ " LEFT JOIN TMS_SUGG_ACCOM ON(TMS_SUGG_ACCOM.MONITOR_ID =TMS_MONITORING.TMS_MONITOR_ID AND TMS_SUGG_ACCOM.ACCOM_SEL_FLAG='Y' AND TMS_MONITORING.MTR_ACC_AVSTATUS= 'FI') "
				+ " LEFT JOIN TMS_SUGG_LOC_CONV ON(TMS_SUGG_LOC_CONV.MONITOR_ID =TMS_MONITORING.TMS_MONITOR_ID AND TMS_SUGG_LOC_CONV.LOCCONV_SEL_FLAG='Y' AND TMS_MONITORING.MTR_LOCAL_AVSTATUS='FI') " 
				+ " INNER JOIN TMS_APPLICATION ON(TMS_APP_GUEST_DTL.APPL_ID=TMS_APPLICATION.APPL_ID) "
				+ " INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID=TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE=TMS_APP_GUEST_DTL.APPL_CODE) " 
				+ " INNER JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE) "
				+ " INNER JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE) "
				+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR) "
				+ " INNER JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) " 
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) "
				+ " WHERE 1=1 AND TMS_MONITOR_ID IS NOT NULL ";
			
			//FINANCIAL YEAR
			if(!(adminRepBean.getFinanYear().equals("")) && !(adminRepBean.getFinanYear() == null) 
					&& !(adminRepBean.getFinanYear().equals("null"))){
				empQuery += " AND TO_CHAR(TMS_APPLICATION.APPL_DATE,'YYYY') = '"+adminRepBean.getFinanYear().trim()+"'";
				guestQuery += " AND TO_CHAR(TMS_APPLICATION.APPL_DATE,'YYYY') = '"+adminRepBean.getFinanYear().trim()+"'";
			}
			//GRADE
			if(!(adminRepBean.getGradeId().equals("")) && !(adminRepBean.getGradeId() == null)
					&& !(adminRepBean.getGradeId().equals("null")) ) {
				empQuery += " AND HRMS_EMP_OFFC.EMP_CADRE = "+adminRepBean.getGradeId();
				guestQuery += " AND HRMS_EMP_OFFC.EMP_CADRE = "+adminRepBean.getGradeId();
			}
			//TRAVEL TYPE
			if(!(adminRepBean.getTrvlType().equals("")) && !(adminRepBean.getTrvlType() == null) 
					&& !(adminRepBean.getTrvlType().equals("null"))) {
				empQuery += " AND HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID = "+adminRepBean.getTrvlType();
				guestQuery += " AND HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID = "+adminRepBean.getTrvlType();
			}
			//APPLICATION FOR
			if(!(adminRepBean.getAppliFor().equals(""))){
				empQuery += " AND TMS_APPLICATION.APPL_FOR_FLAG = '"+adminRepBean.getAppliFor()+"'";
				guestQuery += " AND TMS_APPLICATION.APPL_FOR_FLAG = '"+adminRepBean.getAppliFor()+"'";
			}
			//TRAVEL CHECK BOX
			if(adminRepBean.getTravelCheck().equals("true")) {
				String tmp = "";
				if(adminRepBean.getHidTravelSelf().equals("checked"))
					tmp = "S";
				else
					tmp = "C";
				empQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ARR_DONE = '"+tmp+"'";
				guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_TRAVEL_ARR_DONE = '"+tmp+"'";
			}
			//ACCOMMODATION CHECK BOX
			if(adminRepBean.getAccomCheck().equals("true")) {
				String tmp = "";
				if(adminRepBean.getHidAccomSelf().equals("checked"))
					tmp = "S";
				else
					tmp = "C";
				empQuery += " AND TMS_APP_TOUR_DTL.TOUR_ACCOM_ARR_DONE = '"+tmp+"'";
				guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_ACCOM_ARR_DONE = '"+tmp+"'";
			}
			//LOCAL CONVEYANCE CHECK BOX
			if(adminRepBean.getLocalCheck().equals("true")) {
				String tmp = "";
				if(adminRepBean.getHidLocalSelf().equals("checked"))
					tmp = "S";
				else
					tmp = "C";
				empQuery += " AND TMS_APP_TOUR_DTL.TOUR_CONV_ARR_DONE = '"+tmp+"'";
				guestQuery += " AND TMS_APP_TOUR_DTL.TOUR_CONV_ARR_DONE = '"+tmp+"'";
			}
			//TRAVEL PURPOSE
			if(!(adminRepBean.getTrvlPurpose().equals("")) && !(adminRepBean.getTrvlPurpose() == null)
					&& !(adminRepBean.getTrvlPurpose().equals("null"))) {
				empQuery += " AND HRMS_TMS_PURPOSE.PURPOSE_ID = "+adminRepBean.getTrvlPurpose();
				guestQuery += " AND HRMS_TMS_PURPOSE.PURPOSE_ID = "+adminRepBean.getTrvlPurpose();
			}
			//BRANCH
			if(!(adminRepBean.getBranchId().equals("")) && !(adminRepBean.getBranchId() == null) 
					&& !(adminRepBean.getBranchId().equals("null")) ){
				empQuery += " AND HRMS_EMP_OFFC.EMP_CENTER = "+adminRepBean.getBranchId();
				guestQuery += " AND HRMS_EMP_OFFC.EMP_CENTER = "+adminRepBean.getBranchId();
			}
			//DEPARTMENT
			if(!(adminRepBean.getDeptId().equals("")) && !(adminRepBean.getDeptId() == null)
					&& !(adminRepBean.getDeptId().equals("null")) ) {
				empQuery += " AND HRMS_EMP_OFFC.EMP_DEPT = "+adminRepBean.getDeptId();
				guestQuery += " AND HRMS_EMP_OFFC.EMP_DEPT = "+adminRepBean.getDeptId();
			}
			//EMPLOYEE
			if(!(adminRepBean.getEmpId().equals("")) && !(adminRepBean.getEmpId() == null)
					&& !(adminRepBean.getEmpId().equals("null")) ) {
				empQuery += " AND TMS_APP_EMPDTL.APPL_EMP_CODE = "+adminRepBean.getEmpId();
				guestQuery += " AND TMS_APPLICATION.APPL_INITIATOR = "+adminRepBean.getEmpId();
			}
			
			//Add group by statement to each query otherwise it is not possible to get 
			//travel cost, accom cost and local convy cost.
			
			empQuery += "group by TMS_MONITOR_ID,TVLNG_SEL_FLAG,ACCOM_SEL_FLAG,LOCCONV_SEL_FLAG, "
					+ " NVL(HRMS_EMP_OFFC.EMP_TOKEN,''), "
					+ " nvl(HRMS_EMP_OFFC.EMP_FNAME,'')||' '||nvl(HRMS_EMP_OFFC.EMP_MNAME,'')||nvl(HRMS_EMP_OFFC.EMP_LNAME,''), "
					+ " NVL(CADRE_NAME,''),TO_CHAR(APPL_DATE,'DD-MM-YYYY'),NVL(APPL_TRVL_ID,''),TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'), "
					+ " TO_CHAR(TOUR_TRAVEL_ENDDT,'DD_MM_YYYY'),NVL(PURPOSE_NAME,' '),NVL(LOCATION_TYPE_NAME,' '),NVL(EXP_APPRVD_AMT,0), "
					+ " EMP_ID ";
			guestQuery += "group by TMS_MONITOR_ID,TVLNG_SEL_FLAG,ACCOM_SEL_FLAG,LOCCONV_SEL_FLAG, "
				+ " NVL(HRMS_EMP_OFFC.EMP_TOKEN,''), "
				+ " nvl(HRMS_EMP_OFFC.EMP_FNAME,'')||' '||nvl(HRMS_EMP_OFFC.EMP_MNAME,'')||nvl(HRMS_EMP_OFFC.EMP_LNAME,''), "
				+ " NVL(CADRE_NAME,''),TO_CHAR(APPL_DATE,'DD-MM-YYYY'),NVL(APPL_TRVL_ID,''),TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'), "
				+ " TO_CHAR(TOUR_TRAVEL_ENDDT,'DD_MM_YYYY'),NVL(PURPOSE_NAME,' '),NVL(LOCATION_TYPE_NAME,' '),NVL(EXP_APPRVD_AMT,0), "
				+ " EMP_ID ";
			//Sorting code
			/*
			 * Here tmp is temporary string to carry order by stmt,
			 * at last we add this part after two queries
			 */
			String tmp = "";
			if(!adminRepBean.getSortBy().equals("1") || !adminRepBean.getThenBy1().equals("1") || 
					!adminRepBean.getThenBy2().equals("1")){
				tmp += " ORDER BY ";
			}
			if (!adminRepBean.getSortBy().equals("1")) {
				tmp += getSortVal(adminRepBean.getSortBy(), labelNames) + " "
						+ getSortOrder(adminRepBean.getSortByOrder());
				if (!adminRepBean.getThenBy1().equals("1")
						|| !adminRepBean.getThenBy2().equals("1")) {
					tmp += " , ";
				}
			}

			if (!adminRepBean.getThenBy1().equals("1")) {
				tmp += getSortVal(adminRepBean.getThenBy1(), labelNames) + " "
						+ getSortOrder(adminRepBean.getThenByOrder1());
				logger.info("bean.getThenByOrder1() -- > "+adminRepBean.getThenByOrder1());
				if (!adminRepBean.getThenBy2().equals("1")) {
					tmp += " , ";
				}
			}

			if (!adminRepBean.getThenBy2().equals("1")) {
				tmp += getSortVal(adminRepBean.getThenBy2(), labelNames) + " "
						+ getSortOrder(adminRepBean.getThenByOrder2());
			}
			
			Object[][] condQueryString = new Object[1][3];
			condQueryString[0][0] = empQuery;
			condQueryString[0][1] = guestQuery;
			condQueryString[0][2] = tmp; //Order by part
			return condQueryString;
		}catch(Exception e){
			logger.error("Exception in conditionQuery - "+e);
			return null;
		}
	}

	public String getSortVal(String Status, String[] labelNames) {
		logger.info("Sort Status............" + Status);
		String sql = "";
		if (Status.equals(labelNames[0]))
			sql = " TOKEN ";
		else if (Status.equals(labelNames[1]))
			sql = " EMP_NAME ";
		else if (Status.equals(labelNames[2]))
			sql = " GRADE ";
		else if (Status.equals(labelNames[3]))
			sql = " APPLI_DATE ";
		else if (Status.equals(labelNames[4]))
			sql = " START_DATE ";
		else if (Status.equals(labelNames[5]))
			sql = " END_DATE ";
		else if (Status.equals(labelNames[6]))
			sql = " PURPOSE ";
		else if (Status.equals(labelNames[7]))
			sql = " TRAVEL_TYPE ";
		else if (Status.equals(labelNames[8]))
			sql = " APRVD_AMT ";
		else if (Status.equals(labelNames[9]))
			sql = " TRAVEL_COST ";
		else if (Status.equals(labelNames[10]))
			sql = " ACCOM_COST ";
		else if (Status.equals(labelNames[11]))
			sql = " LOCAL_COST ";
		else if (Status.equals("Travel Id"))
			sql = " TRAVEL_ID ";
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
	
	private Object[][] selectQuery(TrvlAdminReport adminRepBean,
			String[] labelNames, int count, String[] splitComma,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String labels = "Travel Id,";
		String empQuery = "SELECT DISTINCT NVL(APPL_TRVL_ID,'')  AS TRAVEL_ID";
		String guestQuery = "SELECT DISTINCT NVL(APPL_TRVL_ID,'')  AS TRAVEL_ID";
		//String empQuery = "SELECT DISTINCT NVL(HRMS_EMP_OFFC.EMP_TOKEN,'') AS TOKEN";
		//String guestQuery = "SELECT DISTINCT NVL(HRMS_EMP_OFFC.EMP_TOKEN,'') AS TOKEN";
		if(splitComma!=null){
			// new HASHMAP FOR ORDERING
			LinkedHashMap<Integer, String> labelMap = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> empMap = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> guestMap = new LinkedHashMap<Integer, String>();
			for(int i=0;i<splitComma.length;i++) {
				String splitDash[] = splitComma[i].split("-");
				logger.info("Key....." + splitDash[0]);
				logger.info("Value....." + splitDash[1]);
				// DONT APPEND QUERY
				// PUT IN HASHMAP (ORDER NO,FIELD)
				if(splitDash[1].trim().equals(labelNames[0].trim())) {	//EMPLOYEE ID
					empMap.put(Integer.parseInt(splitDash[0]), "NVL(HRMS_EMP_OFFC.EMP_TOKEN,'') AS TOKEN");
					guestMap.put(Integer.parseInt(splitDash[0]), "NVL(HRMS_EMP_OFFC.EMP_TOKEN,'') AS TOKEN");
					labelMap.put(1, labelNames[0]);
					count++;
				}
				else if(splitDash[1].trim().equals(labelNames[1].trim())) {			//EMPLOYEE NAME
					empMap.put(Integer.parseInt(splitDash[0]),
							"nvl(HRMS_EMP_OFFC.EMP_FNAME,'')||' '||nvl(HRMS_EMP_OFFC.EMP_MNAME,'')||"+
							"nvl(HRMS_EMP_OFFC.EMP_LNAME,'') as EMP_NAME");
					guestMap.put(Integer.parseInt(splitDash[0]),
							"nvl(HRMS_EMP_OFFC.EMP_FNAME,'')||' '||nvl(HRMS_EMP_OFFC.EMP_MNAME,'')||"+
							"nvl(HRMS_EMP_OFFC.EMP_LNAME,'') as EMP_NAME");
					labelMap.put(2, labelNames[1]);
					count++;
				}else if(splitDash[1].trim().equals(labelNames[2].trim())) {	//GRADE
					empMap.put(Integer.parseInt(splitDash[0]), "NVL(CADRE_NAME,'') as GRADE");
					guestMap.put(Integer.parseInt(splitDash[0]), "NVL(CADRE_NAME,'') as GRADE");
					labelMap.put(3, labelNames[2]);
					count++;
				}else if(splitDash[1].trim().equals(labelNames[3].trim())) {	//APPLICATION DATE
					empMap.put(Integer.parseInt(splitDash[0]),
							"TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APPLI_DATE");
					guestMap.put(Integer.parseInt(splitDash[0]),
							"TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APPLI_DATE");
					labelMap.put(4, labelNames[3]);
					count++;
				}else if(splitDash[1].trim().equals(labelNames[4].trim())) {	//TRAVEL START DATE
					empMap.put(Integer.parseInt(splitDash[0]),
							"TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS START_DATE");
					guestMap.put(Integer.parseInt(splitDash[0]),
							"TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS START_DATE");
					labelMap.put(5, labelNames[4]);
					count++;
				}else if(splitDash[1].trim().equals(labelNames[5].trim())) {	//TRAVEL END DATE
					empMap.put(Integer.parseInt(splitDash[0]),
							"TO_CHAR(TOUR_TRAVEL_ENDDT,'DD_MM_YYYY') AS END_DATE");
					guestMap.put(Integer.parseInt(splitDash[0]),
							"TO_CHAR(TOUR_TRAVEL_ENDDT,'DD_MM_YYYY') AS END_DATE");
					labelMap.put(6, labelNames[5]);
					count++;
				}else if(splitDash[1].trim().equals(labelNames[6].trim())) {	//TRAVEL PURPOSE
					empMap.put(Integer.parseInt(splitDash[0]), "NVL(PURPOSE_NAME,' ') AS PURPOSE");
					guestMap.put(Integer.parseInt(splitDash[0]), "NVL(PURPOSE_NAME,' ') AS PURPOSE");
					labelMap.put(7, labelNames[6]);
					count++;
				}else if(splitDash[1].trim().equals(labelNames[7].trim())) {	//TRAVEL TYPE
					empMap.put(Integer.parseInt(splitDash[0]), "NVL(LOCATION_TYPE_NAME,' ') AS TRAVEL_TYPE");
					guestMap.put(Integer.parseInt(splitDash[0]), "NVL(LOCATION_TYPE_NAME,' ') AS TRAVEL_TYPE");
					labelMap.put(8, labelNames[7]);
					count++;
				}else if(splitDash[1].trim().equals(labelNames[8].trim())) {	//APPROVED AMOUNT
					empMap.put(Integer.parseInt(splitDash[0]), "NVL(EXP_APPRVD_AMT,0) AS APRVD_AMT");
					guestMap.put(Integer.parseInt(splitDash[0]), "NVL(EXP_APPRVD_AMT,0) AS APRVD_AMT");
					labelMap.put(9, labelNames[8]);
					count++;
				}else if(splitDash[1].trim().equals(labelNames[9].trim())) {	//TRAVEL COST
					empMap.put(Integer.parseInt(splitDash[0]),
							"nvl((select SUM(TVLNG_COST) from TMS_SUGG_TRAVELLING " +
							"where TMS_SUGG_TRAVELLING.TVLNG_SEL_FLAG='Y' and " +
							"TMS_SUGG_TRAVELLING.MONITOR_ID = TMS_MONITOR_ID " +
							"group by TMS_SUGG_TRAVELLING.MONITOR_ID),0) as TRAVEL_COST");
					guestMap.put(Integer.parseInt(splitDash[0]),
							"nvl((select SUM(TVLNG_COST) from TMS_SUGG_TRAVELLING " +
							"where TMS_SUGG_TRAVELLING.TVLNG_SEL_FLAG='Y' and " +
							"TMS_SUGG_TRAVELLING.MONITOR_ID = TMS_MONITOR_ID " +
							"group by TMS_SUGG_TRAVELLING.MONITOR_ID),0) as TRAVEL_COST");
					labelMap.put(10, labelNames[9]);
					count++;
				}else if(splitDash[1].trim().equals(labelNames[10].trim())) {	//ACCOMMODATION COST
					empMap.put(Integer.parseInt(splitDash[0]),
							"nvl((select SUM(ACCOM_BOOKING_AMT) from TMS_SUGG_ACCOM " +
							"where TMS_SUGG_ACCOM.ACCOM_SEL_FLAG='Y' and " +
							"TMS_SUGG_ACCOM.MONITOR_ID = TMS_MONITOR_ID " +
							"group by TMS_SUGG_ACCOM.MONITOR_ID),0) as ACCOM_COST");
					guestMap.put(Integer.parseInt(splitDash[0]),
							"nvl((select SUM(ACCOM_BOOKING_AMT) from TMS_SUGG_ACCOM " +
							"where TMS_SUGG_ACCOM.ACCOM_SEL_FLAG='Y' and " +
							"TMS_SUGG_ACCOM.MONITOR_ID = TMS_MONITOR_ID " +
							"group by TMS_SUGG_ACCOM.MONITOR_ID),0) as ACCOM_COST");
					labelMap.put(11, labelNames[10]);
					count++;
				}else if(splitDash[1].trim().equals(labelNames[11].trim())) {	//LOCAL CONVEYANCE COST
					empMap.put(Integer.parseInt(splitDash[0]),
							"nvl((select SUM(LOCCONV_TARIFFCOST) from TMS_SUGG_LOC_CONV " +
							"where TMS_SUGG_LOC_CONV.LOCCONV_SEL_FLAG='Y' and " +
							"TMS_SUGG_LOC_CONV.MONITOR_ID = TMS_MONITOR_ID " +
							"group by TMS_SUGG_LOC_CONV.MONITOR_ID),0) as LOCAL_COST");
					guestMap.put(Integer.parseInt(splitDash[0]),
							"nvl((select SUM(LOCCONV_TARIFFCOST) from TMS_SUGG_LOC_CONV " +
							"where TMS_SUGG_LOC_CONV.LOCCONV_SEL_FLAG='Y' and " +
							"TMS_SUGG_LOC_CONV.MONITOR_ID = TMS_MONITOR_ID " +
							"group by TMS_SUGG_LOC_CONV.MONITOR_ID),0) as LOCAL_COST");
					labelMap.put(12, labelNames[11]);
					count++;
				}
			}//End of for
			
			Iterator<Integer> empIterator = empMap.keySet().iterator();
			while (empIterator.hasNext()) {
				String mapAdvGuestValues = (String) empMap.get(empIterator.next());
				logger.info("mapEmpValues : " + mapAdvGuestValues);
				empQuery += "," + mapAdvGuestValues;
			}
			
			Iterator<Integer> guestIterator = guestMap.keySet().iterator();
			while (guestIterator.hasNext()) {
				String mapExpGuestValues = (String) guestMap.get(guestIterator.next());
				logger.info("mapEmpValues : " + mapExpGuestValues);
				guestQuery += "," + mapExpGuestValues;
			}
			
			Iterator<Integer> labelIter = labelMap.keySet().iterator();
			String labelValues = "";
			while (labelIter.hasNext()) {
				labelValues = (String) labelMap.get(labelIter.next());
				logger.info("labelValues : " + labelValues);
				labels += labelValues + ",";
			}
		}//End of if
		
		logger.info("labels -- "+labels);
		empQuery += " ,HRMS_EMP_OFFC.EMP_ID ";
		guestQuery += " ,HRMS_EMP_OFFC.EMP_ID ";

		Object[][] str = new Object[1][4];
		str[0][0] = empQuery;
		str[0][1] = guestQuery;
		str[0][2] = labels;
		str[0][3] = "" + count;
		return str;
	}//End of selectQuery method

	public void getReport(TrvlAdminReport bean,
			HttpServletResponse response, String[] labelNames,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		try{
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
				reportName = "Report for Admin";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					reportType, reportName,"");
			rg.addText("\n", 0, 0, 0);
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
			String empSelectQuery = (String) selectQueryObj[0][0];
			String guestSelectQuery = (String) selectQueryObj[0][1];
			String labels = (String) selectQueryObj[0][2];
			count = Integer.parseInt((String) selectQueryObj[0][3]);
			logger.info("counter after select query==========" + count);
			logger.info("labels after select query==========" + labels);
			
			// CONDITION QUERY
			Object[][] condQueryObj = conditionQuery(bean, labelNames); 
			String empConditionQuery = (String) condQueryObj[0][0];
			String guestConditionQuery = (String) condQueryObj[0][1];
			
			String orderByStmt = (String) condQueryObj[0][2];
			//CREATING QUERIES
			String empQuery = empSelectQuery + empConditionQuery;
			String guestQuery = guestSelectQuery + guestConditionQuery;
			
			//FINAL QUERY
			String finalQuery = empQuery + " UNION " + guestQuery + orderByStmt;
			logger.info("finalQuery -- ----------------------->\n"+finalQuery);
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
					if(splitDash[1].equals(labelNames[0].trim())){			//EMPLOYEE ID
						str_colNames[++str_colNames_array] = labelNames[0];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[1].trim())){				//EMPLOYEE NAME
						str_colNames[++str_colNames_array] = labelNames[1];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[2].trim())){			//GRADE
						str_colNames[++str_colNames_array] = labelNames[2];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[3].trim())){			//APPLICATION DATE
						str_colNames[++str_colNames_array] = labelNames[3];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 1;
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
					else if(splitDash[1].equals(labelNames[6].trim())){			//TRAVEL PURPOSE
						str_colNames[++str_colNames_array] = labelNames[6];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[7].trim())){			//TRAVEL TYPE
						str_colNames[++str_colNames_array] = labelNames[7];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[8].trim())){			//APPROVED AMOUNT
						str_colNames[++str_colNames_array] = labelNames[8];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[9].trim())){			//TRAVEL COST
						str_colNames[++str_colNames_array] = labelNames[9];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[10].trim())){			//ACCOMMODATION COST
						str_colNames[++str_colNames_array] = labelNames[10];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[11].trim())){			//LOCAL CONVEYANCE COST
						str_colNames[++str_colNames_array] = labelNames[11];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
				}//End of for
			}//End of splitComma if
			
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
			logger.error("Exception in getReport -- "+e);
		}
	}//End of getReport method	
}
