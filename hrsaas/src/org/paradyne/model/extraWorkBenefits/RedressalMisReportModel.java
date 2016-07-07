package org.paradyne.model.extraWorkBenefits;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.extraWorkBenefits.RedressalMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author Ayyappa
 * @date 01-04-2010
 */

public class RedressalMisReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
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
	
	public boolean deleteSavedReport(RedressalMisReport misreport) {
		// TODO Auto-generated method stub
		boolean result = false;
		String deleteDetail = "DELETE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID="
				+ misreport.getReportId();
		String deleteHeader = "DELETE FROM HRMS_MISREPORT_HDR WHERE REPORT_ID="
				+ misreport.getReportId();
		result = getSqlModel().singleExecute(deleteDetail);
		if (result)
			result = getSqlModel().singleExecute(deleteHeader);
		
		return result;
	}
	
	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(RedressalMisReport misreport) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_MISREPORT_HDR WHERE REPORT_TYPE LIKE 'Redressal' AND UPPER(REPORT_CRITERIA) LIKE '"
				+ misreport.getSettingName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}	

	public boolean saveReportCriteria(RedressalMisReport misreport) {
		// TODO Auto-generated method stub
		boolean result = false;
		if (!checkDuplicate(misreport)) {
			Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = checkNull(misreport.getSettingName().trim());
			saveObj[0][1] = checkNull(misreport.getReportTitle().trim());
			saveObj[0][2] = "Redressal";
			String insertHeader = "INSERT INTO HRMS_MISREPORT_HDR (REPORT_ID, REPORT_CRITERIA, REPORT_TITLE, REPORT_TYPE)"
					+ " VALUES ((SELECT NVL(MAX(REPORT_ID),0)+1 FROM HRMS_MISREPORT_HDR), ?, ?, ?)";
			result = getSqlModel().singleExecute(insertHeader, saveObj);
			if (result) {
				String codeQuery = "SELECT MAX(REPORT_ID) FROM HRMS_MISREPORT_HDR";
				Object[][] codeObj = getSqlModel().getSingleResult(codeQuery);
				misreport.setReportId(String.valueOf(codeObj[0][0]));
				if (saveFilters(misreport) && saveColumns(misreport)
						&& saveSortOptions(misreport)
						&& saveAdvancedFilters(misreport)) {
					result = true;
				} else
					result = false;
			}
		}else
			result = false;
		return result;
	}


	public boolean saveFilters(RedressalMisReport misreport){
		boolean result = false;
		int count = 0;
		//Division
		if(!misreport.getDivId().equals("")){
			count++; //code
			count++; //name
		}
		//Department
		if(!misreport.getDeptId().equals("")){
			count++; //code
			count++; //name
		}
		//Branch
		if(!misreport.getBranchId().equals("")){
			count++; //code
			count++; //name
		}
		//Employee Type
		if(!misreport.getEmpTypeId().equals("")){
			count++; //code
			count++; //name
		}
		//Designation
		if(!misreport.getDesigId().equals("")){
			count++; //code
			count++; //name
		}
		//Status
		if(!misreport.getStatus().equals("1")){
			count++; //name
		}
		//Employee
		if(!misreport.getEmpId().equals("")){
			count++; //code
			count++; //token
			count++; //name
		}
		logger.info("Count for Save filters : " + count);
		
		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		
		//Division
		if(!misreport.getDivId().equals("")){
			//code
			addObj[int_count][0] = "misreport.divId"; 
			addObj[int_count][1] = checkNull(misreport.getDivId().trim());
			int_count++; 
			//name
			addObj[int_count][0] = "misreport.divName"; 
			addObj[int_count][1] = checkNull(misreport.getDivName().trim());
			int_count++; 			
		}
		//Department
		if(!misreport.getDeptId().equals("")){
			//code
			addObj[int_count][0] = "misreport.deptId"; 
			addObj[int_count][1] = checkNull(misreport.getDeptId().trim());
			int_count++; 
			//name
			addObj[int_count][0] = "misreport.deptName"; 
			addObj[int_count][1] = checkNull(misreport.getDeptName().trim());
			int_count++; 			
		}
		//Branch
		if(!misreport.getBranchId().equals("")){
			//code
			addObj[int_count][0] = "misreport.branchId"; 
			addObj[int_count][1] = checkNull(misreport.getBranchId().trim());
			int_count++; 
			//name
			addObj[int_count][0] = "misreport.branchName"; 
			addObj[int_count][1] = checkNull(misreport.getBranchName().trim());
			int_count++; 			
		}
		//Employee Type
		if(!misreport.getEmpTypeId().equals("")){
			//code
			addObj[int_count][0] = "misreport.empTypeId"; 
			addObj[int_count][1] = checkNull(misreport.getEmpTypeId().trim());
			int_count++; 
			//name
			addObj[int_count][0] = "misreport.empTypeName"; 
			addObj[int_count][1] = checkNull(misreport.getEmpTypeName().trim());
			int_count++; 			
		}
		//Designation
		if(!misreport.getDesigId().equals("")){
			//code
			addObj[int_count][0] = "misreport.desigId"; 
			addObj[int_count][1] = checkNull(misreport.getDesigId().trim());
			int_count++; 
			//name
			addObj[int_count][0] = "misreport.desigName"; 
			addObj[int_count][1] = checkNull(misreport.getDesigName().trim());
			int_count++; 			
		}
		//Status
		if(!misreport.getStatus().equals("1")){
			//status
			addObj[int_count][0] = "misreport.status"; 
			addObj[int_count][1] = checkNull(misreport.getStatus().trim());
			int_count++; 

		}
		//Employee
		if(!misreport.getEmpId().equals("")){
			//code
			addObj[int_count][0] = "misreport.empId"; 
			addObj[int_count][1] = checkNull(misreport.getEmpId().trim());
			int_count++; 			
			//token
			addObj[int_count][0] = "misreport.empToken"; 
			addObj[int_count][1] = checkNull(misreport.getEmpToken().trim());
			int_count++; 
			//name
			addObj[int_count][0] = "misreport.empName"; 
			addObj[int_count][1] = checkNull(misreport.getEmpName().trim());
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
	}
	
	public boolean saveColumns(RedressalMisReport misreport){
		boolean result = false;
		int count = 0;
		//Name
		if(misreport.getEmpNameFlag().equals("true")){
			count++;
		}
		//Division
		if(misreport.getDivFlag().equals("true")){
			count++;
		}
		//Department
		if(misreport.getDeptFlag().equals("true")){
			count++;
		}
		//Branch
		if(misreport.getBranchFlag().equals("true")){
			count++;
		}		
		
		//Designation
		if(misreport.getDesigFlag().equals("true")){
			count++;
		}
		//Employee Type
		if(misreport.getEmpTypeFlag().equals("true")){
			count++;
		}
		//Application Date
		if(misreport.getAppliDateFlag().equals("true")){
			count++;
		}
		//Status
		if(misreport.getStatusFlag().equals("true")){
			count++;
		}				
		//Leave From Date
		if(misreport.getLeaveFromDateFlag().equals("true")){
			count++;
		}				
		//Leave To Date
		if(misreport.getLeaveToDateFlag().equals("true")){
			count++;
		}
		//Leave Type
		if(misreport.getLeaveTypeFlag().equals("true")){
			count++;
		}	
		//Leave Days
		if(misreport.getLeaveDaysFlag().equals("true")){
			count++;
		}			
		//Redressal Days
		if(misreport.getRedDaysFlag().equals("true")){
			count++;
		}			
		logger.info("Count for Save columns : " + count);
		Object[][] addObj = new Object[count][2];
		int int_count = 0;

		//Name
		if(misreport.getEmpNameFlag().equals("true")){
			addObj[int_count][0] = "misreport.empNameFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Division
		if(misreport.getDivFlag().equals("true")){
			addObj[int_count][0] = "misreport.divFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Department
		if(misreport.getDeptFlag().equals("true")){
			addObj[int_count][0] = "misreport.deptFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Branch
		if(misreport.getBranchFlag().equals("true")){
			addObj[int_count][0] = "misreport.branchFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}		
		
		//Designation
		if(misreport.getDesigFlag().equals("true")){
			addObj[int_count][0] = "misreport.desigFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Employee Type
		if(misreport.getEmpTypeFlag().equals("true")){
			addObj[int_count][0] = "misreport.empTypeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Application Date
		if(misreport.getAppliDateFlag().equals("true")){
			addObj[int_count][0] = "misreport.appliDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Status
		if(misreport.getStatusFlag().equals("true")){
			addObj[int_count][0] = "misreport.statusFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}		
		//Leave From Date
		if(misreport.getLeaveFromDateFlag().equals("true")){
			addObj[int_count][0] = "misreport.leaveFromDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}				
		//Leave To Date
		if(misreport.getLeaveToDateFlag().equals("true")){
			addObj[int_count][0] = "misreport.leaveToDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Leave Type
		if(misreport.getLeaveTypeFlag().equals("true")){
			addObj[int_count][0] = "misreport.leaveTypeFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Leave Days
		if(misreport.getLeaveDaysFlag().equals("true")){
			addObj[int_count][0] = "misreport.leaveDaysFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Redressal Days
		if(misreport.getRedDaysFlag().equals("true")){
			addObj[int_count][0] = "misreport.redDaysFlag";
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
	
	public boolean saveSortOptions(RedressalMisReport misreport){
		boolean result = false;
		int count = 0;
		
		if (!misreport.getSortBy().equals("1")) {
			count++;
		}
		if(!misreport.getHiddenSortBy().equals("")){
			count++;
		}
		if (!misreport.getSortByAsc().equals("")) {
			count++;
		}
		if (!misreport.getSortByDsc().equals("")) {
			count++;
		}
		if (!misreport.getSortByOrder().equals("")) {
			count++;
		}
		if (!misreport.getThenBy1().equals("1")) {
			count++;
		}
		if(!misreport.getHiddenThenBy1().equals("")){
			count++;
		}
		if (!misreport.getThenByOrder1Asc().equals("")) {
			count++;
		}
		if (!misreport.getThenByOrder1Dsc().equals("")) {
			count++;
		}
		if (!misreport.getThenByOrder1().equals("")) {
			count++;
		}
		if (!misreport.getThenBy2().equals("1")) {
			count++;
		}
		if(!misreport.getHiddenThenBy2().equals("")){
			count++;
		}
		if (!misreport.getThenByOrder2Asc().equals("")) {
			count++;
		}
		if (!misreport.getThenByOrder2Dsc().equals("")) {
			count++;
		}
		if (!misreport.getThenByOrder2().equals("")) {
			count++;
		}
		if(!misreport.getHiddenColumns().equals("")){
			count++;
		}

		logger.info("Count for Save sort options : " + count);	
		

		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (!misreport.getSortBy().equals("1")) {
			addObj[int_count][0] = "sortBy";
			addObj[int_count][1] = checkNull(misreport.getSortBy().trim());
			int_count++;
		}
		if (!misreport.getHiddenSortBy().equals("")) {
			addObj[int_count][0] = "hiddenSortBy";
			addObj[int_count][1] = checkNull(misreport.getHiddenSortBy().trim());
			int_count++;
		}
		logger.info("getSortByAsc...."+misreport.getSortByAsc());
		if (!misreport.getSortByAsc().equals("")) {
			addObj[int_count][0] = "sortByAsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getSortByAsc().trim());
			int_count++;
		}
		logger.info("getSortByDsc...."+misreport.getSortByDsc());
		if (!misreport.getSortByDsc().equals("")) {
			addObj[int_count][0] = "sortByDsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getSortByDsc().trim());
			int_count++;
		}
		if (!misreport.getSortByOrder().equals("")) {
			addObj[int_count][0] = "sortByOrder";
			addObj[int_count][1] = checkNull(misreport.getSortByOrder().trim());
			int_count++;
		}
		if (!misreport.getThenBy1().equals("1")) {
			addObj[int_count][0] = "thenBy1";
			addObj[int_count][1] = checkNull(misreport.getThenBy1().trim());
			int_count++;
		}
		if (!misreport.getHiddenThenBy1().equals("")) {
			addObj[int_count][0] = "hiddenThenBy1";
			addObj[int_count][1] = checkNull(misreport.getHiddenThenBy1().trim());
			int_count++;
		}
		if (!misreport.getThenByOrder1Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Asc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder1Asc().trim());
			int_count++;
		}
		if (!misreport.getThenByOrder1Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder1Dsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder1Dsc().trim());
			int_count++;
		}
		if (!misreport.getThenByOrder1().equals("")) {
			addObj[int_count][0] = "thenByOrder1";
			addObj[int_count][1] = checkNull(misreport.getThenByOrder1().trim());
			int_count++;
		}
		if (!misreport.getThenBy2().equals("1")) {
			addObj[int_count][0] = "thenBy2";
			addObj[int_count][1] = checkNull(misreport.getThenBy2().trim());
			int_count++;
		}
		if (!misreport.getHiddenThenBy2().equals("")) {
			addObj[int_count][0] = "hiddenThenBy2";
			addObj[int_count][1] = checkNull(misreport.getHiddenThenBy2().trim());
			int_count++;
		}
		if (!misreport.getThenByOrder2Asc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Asc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder2Asc().trim());
			int_count++;
		}
		if (!misreport.getThenByOrder2Dsc().equals("")) {
			addObj[int_count][0] = "thenByOrder2Dsc";
			addObj[int_count][1] = "Y";// checkNull(misReport.getThenByOrder2Dsc().trim());
			int_count++;
		}
		if (!misreport.getThenByOrder2().equals("")) {
			addObj[int_count][0] = "thenByOrder2";
			addObj[int_count][1] = checkNull(misreport.getThenByOrder2().trim());
			int_count++;
		}
		if(!misreport.getHiddenColumns().equals("")){
			addObj[int_count][0] = "hiddenColumns";
			addObj[int_count][1] = checkNull(misreport.getHiddenColumns().trim());
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
	
	public boolean saveAdvancedFilters(RedressalMisReport misreport){
		
		boolean result = false;
		int count = 0;
		//Application Date
		if (!misreport.getAppliDateSelect().equals("")) {
			count++;
		}
		if (!misreport.getAppliFromDate().equals("")) {
			count++;
		}
		if (!misreport.getAppliToDate().equals("")) {
			count++;
		}
		//Leave From Date - leave1
		if (!misreport.getLeave1DateSelect().equals("")) {
			count++;
		}
		if (!misreport.getLeave1FromDate().equals("")) {
			count++;
		}
		if (!misreport.getLeave1ToDate().equals("")) {
			count++;
		}	
		//Leave To Date - leave2
		if (!misreport.getLeave2DateSelect().equals("")) {
			count++;
		}
		if (!misreport.getLeave2FromDate().equals("")) {
			count++;
		}
		if (!misreport.getLeave2ToDate().equals("")) {
			count++;
		}			
		logger.info("Count for Save advance filters : " + count);
		Object[][] addObj = new Object[count][2];
		int int_count = 0;
		if (!misreport.getAppliDateSelect().equals("")) {
			addObj[int_count][0] = "appliDateSelect";
			addObj[int_count][1] = checkNull(misreport.getAppliDateSelect().trim());
			int_count++;
		}
		if (!misreport.getAppliFromDate().equals("")) {
			addObj[int_count][0] = "misreport.appliFromDate";
			addObj[int_count][1] = checkNull(misreport.getAppliFromDate().trim());
			int_count++;
		}
		if (!misreport.getAppliToDate().equals("")) {
			addObj[int_count][0] = "misreport.appliToDate";
			addObj[int_count][1] = checkNull(misreport.getAppliToDate().trim());
			int_count++;
		}
		if (!misreport.getLeave1DateSelect().equals("")) {
			addObj[int_count][0] = "leave1DateSelect";
			addObj[int_count][1] = checkNull(misreport.getLeave1DateSelect().trim());
			int_count++;
		}
		if (!misreport.getLeave1FromDate().equals("")) {
			addObj[int_count][0] = "misreport.leave1FromDate";
			addObj[int_count][1] = checkNull(misreport.getLeave1FromDate().trim());
			int_count++;
		}
		if (!misreport.getLeave1ToDate().equals("")) {
			addObj[int_count][0] = "misreport.leave1ToDate";
			addObj[int_count][1] = checkNull(misreport.getLeave1ToDate().trim());
			int_count++;
		}		
		if (!misreport.getLeave2DateSelect().equals("")) {
			addObj[int_count][0] = "leave2DateSelect";
			addObj[int_count][1] = checkNull(misreport.getLeave2DateSelect().trim());
			int_count++;
		}
		if (!misreport.getLeave2FromDate().equals("")) {
			addObj[int_count][0] = "misreport.leave2FromDate";
			addObj[int_count][1] = checkNull(misreport.getLeave2FromDate().trim());
			int_count++;
		}
		if (!misreport.getLeave2ToDate().equals("")) {
			addObj[int_count][0] = "misreport.leave2ToDate";
			addObj[int_count][1] = checkNull(misreport.getLeave2ToDate().trim());
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
	
	public void setDetailOnScreen(RedressalMisReport misreport) {
		// TODO Auto-generated method stub
		String dtlQuery = "SELECT REPORT_ID, FIELD_NAME, FIELD_VALUE FROM HRMS_MISREPORT_DTL WHERE REPORT_ID ="
			+ misreport.getReportId();
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
					Method modelMethod = RedressalMisReport.class.getDeclaredMethod(
										"set"+initCap(methodStr), String.class);
					logger.info("modelMethod - "+modelMethod);
					modelMethod.invoke(misreport, dtlObj[i][2]);
				}//End of for
			}catch(Exception e){
				logger.error("Exception in setDetailOnScreen - "+e);
			}//End of try-catch
		}//End of if
	}//End of method	
	
	public void callViewScreen(RedressalMisReport misreport,
			HttpServletRequest request, String[] labelNames) {
		// TODO Auto-generated method stub
		try{
			//Report Columns
			logger.info("report columns -------- "+misreport.getHiddenColumns());
			String multiSelectValues = misreport.getHiddenColumns();
			String splitComma[] = null;
			if(!multiSelectValues.equals("")){
				String lastComma = multiSelectValues.substring(0, multiSelectValues.length()-1);
				splitComma = lastComma.split(",");
			}//End of if
			int count = 0;
			//Creating Query with Count (count appended after #)
			String queryWithCount = selectQuery(misreport,labelNames,count,splitComma,request);
			
			String splitQuery[] = queryWithCount.split("#");
			String query = splitQuery[0];
			count = Integer.parseInt(splitQuery[1]);
			String labels = splitQuery[2];

			logger.info("counter after select query==========" + count);
			logger.info("labels after select query==========" + labels);
			
			// QUERY APPENDED WITH CONDITIONS
			query += conditionQuery(misreport, labelNames); 
			
			logger.info("\nquery -- ----------------------->\n"+query);
			Object finalObj[][] = null;
			finalObj = getSqlModel().getSingleResult(query);
			logger.info("finalObj.length - "+finalObj.length);
			if(finalObj != null && finalObj.length > 0){
				String[] pageIndex = Utility.doPaging(misreport.getMyPage(),
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
					misreport.setMyPage("1");
				
				int numOfRec = Integer.parseInt(pageIndex[1]) - Integer.parseInt(pageIndex[0]);
				int columnLength = finalObj[0].length;
				Object[][] pageObj = new Object[numOfRec][columnLength];
				int z = 0;
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					for (int j = 0; j < columnLength; j++) {
						pageObj[z][j] = finalObj[i][j];
					}
					z++;
				}
				
				request.setAttribute("labelValues", labels);
				request.setAttribute("finalObj", pageObj);
				misreport.setDataLength(String.valueOf(finalObj.length));
				misreport.setNoData("false");
			} else {
				request.setAttribute("totalPage", new Integer(0));
				request.setAttribute("PageNo", new Integer(0));
				misreport.setNoData("true");
				request.setAttribute("labelValues", labels);
			}
		}catch(Exception e){
			
		}
	}

	private String selectQuery(RedressalMisReport misreport, String[] labelNames,
			int count, String[] splitComma, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String labels = "Employee Code,";
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN ";
		if(splitComma!=null){
			// new HASHMAP FOR ORDERING
			LinkedHashMap<Integer, String> columnMap = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> labelMap = new LinkedHashMap<Integer, String>();
			
			for(int i=0;i<splitComma.length;i++){
				String splitDash[] = splitComma[i].split("-");
				logger.info("Key....." + splitDash[0]);
				logger.info("Value....." + splitDash[1]);

				// DONT APPEND QUERY
				// PUT IN HASHMAP (ORDER NO,FIELD)
				if(splitDash[1].equals(labelNames[0].trim())){				//NAME
					columnMap.put(Integer.parseInt(splitDash[0]), "NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||"
												+"NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||"
												+ "NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')");
					labelMap.put(1, labelNames[0]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[1].trim())){			//DIVISION
					columnMap.put(Integer.parseInt(splitDash[0])," NVL(DIV_NAME,' ')");
					labelMap.put(2, labelNames[1]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[2].trim())){			//DEPARTMENT
					columnMap.put(Integer.parseInt(splitDash[0])," NVL(DEPT_NAME,' ')");
					labelMap.put(3, labelNames[2]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[3].trim())){			//BRANCH
					columnMap.put(Integer.parseInt(splitDash[0])," NVL(CENTER_NAME,' ')");
					labelMap.put(4, labelNames[3]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[4].trim())){			//DESIGNATION
					columnMap.put(Integer.parseInt(splitDash[0])," NVL(RANK_NAME,' ')");
					labelMap.put(5, labelNames[4]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[5].trim())){			//EMPLOYEE TYPE
					columnMap.put(Integer.parseInt(splitDash[0])," NVL(TYPE_NAME,' ')");
					labelMap.put(6, labelNames[5]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[6].trim())){			//APPLICATION DATE
					columnMap.put(Integer.parseInt(splitDash[0])," TO_CHAR(REDRESSAL_APPLICATION_DATE,'DD-MM-YYYY')");
					labelMap.put(7, labelNames[6]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[7].trim())){			//STATUS
					columnMap.put(Integer.parseInt(splitDash[0]),
							" NVL(DECODE(REDRESSAL_DTL_STATUS,'P','Pending','A','Approved','R','Rejected','B','Sent Back'),' ')");
					labelMap.put(8, labelNames[7]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[8].trim())){			//LEAVE FROM DATE
					columnMap.put(Integer.parseInt(splitDash[0])," TO_CHAR(REDRESSAL_DTL_FROM_DATE,'DD-MM-YYYY')");
					labelMap.put(9, labelNames[8]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[9].trim())){		//LEAVE TO DATE
					columnMap.put(Integer.parseInt(splitDash[0])," TO_CHAR(REDRESSAL_DTL_TO_DATE,'DD-MM-YYYY')");
					labelMap.put(10, labelNames[9]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[10].trim())){		//LEAVE TYPE
					columnMap.put(Integer.parseInt(splitDash[0])," NVL(LEAVE_NAME,' ')");
					labelMap.put(11, labelNames[10]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[11].trim())){		//LEAVE DAYS
					columnMap.put(Integer.parseInt(splitDash[0])," nvl(REDRESSAL_DTL_PENALTY_ADJ_DAYS,'0')+nvl(REDRESSAL_DTL_PENALTY_DAYS,'0') LEAVEDAYS");
					labelMap.put(12, labelNames[11]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[12].trim())){		//REDRESSAL DAYS
					columnMap.put(Integer.parseInt(splitDash[0])," NVL(REDRESSAL_DTL_REDRESSED_DAYS,'0')");
					labelMap.put(13, labelNames[12]);
					count++;
				}
			}//End of for
			
			Iterator<Integer> iterator = columnMap.keySet().iterator();
			while(iterator.hasNext()){
				String mapValues = (String) columnMap.get(iterator.next());
				logger.info("query parts : " + mapValues);
				query += ","+mapValues; 
			}
			
			Iterator<Integer> labelIter = labelMap.keySet().iterator();
			while(labelIter.hasNext()){
				String labValues = (String) labelMap.get(labelIter.next());
				logger.info("labelValues : "+labValues);
				labels += labValues + ",";
			}
		}//End of if
		logger.info("labels -- "+labels);
		query += " ,HRMS_EMP_OFFC.EMP_ID " + "#" + count + "#" + labels;
		return query;
	}	

	private String conditionQuery(RedressalMisReport bean,
			String[] labelNames) {
		// TODO Auto-generated method stub
		String query="";
		try{
			query ="FROM HRMS_REDRESSAL_DTL "
				+ " LEFT JOIN HRMS_REDRESSAL_HDR ON (HRMS_REDRESSAL_HDR.REDRESSAL_ID = HRMS_REDRESSAL_DTL.REDRESSAL_ID)  "
				+ " INNER JOIN HRMS_EMP_OFFC ON  (HRMS_EMP_OFFC.EMP_ID = HRMS_REDRESSAL_HDR.REDRESSAL_EMP_ID) "
				+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)  "
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "  
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) "  
				+ " INNER JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_EMP_OFFC.EMP_TYPE) "
				+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)  "
				+ " LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_REDRESSAL_DTL.REDRESSAL_DTL_LEAVE_CODE) "
				+ " WHERE 1 = 1 ";
			//DIVISION
			if(!(bean.getDivId().equals("")) && !(bean.getDivId() == null) && !(bean.getDivId().equals("null"))){
				query += " AND HRMS_EMP_OFFC.EMP_DIV = "+bean.getDivId();
			}
			//DEPARTMENT
			if(!(bean.getDeptId().equals("")) && !(bean.getDeptId() == null) && !(bean.getDeptId().equals("null")) ){
				query += "  AND HRMS_EMP_OFFC.EMP_DEPT = "+bean.getDeptId();
			}
			//BRANCH
			if(!(bean.getBranchId().equals("")) && !(bean.getBranchId() == null) && !(bean.getBranchId().equals("null")) ){
				query += " AND HRMS_EMP_OFFC.EMP_CENTER = "+bean.getBranchId();
			}
			//EMPLOYEE TYPE
			if(!(bean.getEmpTypeId().equals("")) && !(bean.getEmpTypeId() == null) && !(bean.getEmpTypeId().equals("null")) ){
				query += " AND HRMS_EMP_OFFC.EMP_TYPE = "+bean.getEmpTypeId();
			}
			//DESIGNATION
			if(!(bean.getDesigId().equals("")) && !(bean.getDesigId() == null) && !(bean.getDesigId().equals("null")) ){
				query += " AND HRMS_EMP_OFFC.EMP_RANK = "+bean.getDesigId();
			}
			//STATUS
			if(!(bean.getStatus().equals("1")) && !(bean.getStatus() == null) && !(bean.getStatus().equals("null")) ){
				query += " AND HRMS_REDRESSAL_DTL.REDRESSAL_DTL_STATUS = '"+bean.getStatus().trim()+"'";
			}
			//EMPLOYEE
			if(!(bean.getEmpId().equals("")) && !(bean.getEmpId() == null) && !(bean.getEmpId().equals("null")) ){
				query += " AND HRMS_EMP_OFFC.EMP_ID = "+bean.getEmpId();
			}			
			
			//ADVANCE FILTER
			//APPLICATION DATE
			if (!bean.getAppliDateSelect().trim().equals("")) {
				if (bean.getAppliDateSelect().trim().equals("ON")) {
					query += " AND HRMS_REDRESSAL_HDR.REDRESSAL_APPLICATION_DATE = TO_DATE('"
							+ bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAppliDateSelect().trim().equals("OB")) {
					query += " AND HRMS_REDRESSAL_HDR.REDRESSAL_APPLICATION_DATE <= TO_DATE('"
							+ bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAppliDateSelect().trim().equals("OA")) {
					query += " AND HRMS_REDRESSAL_HDR.REDRESSAL_APPLICATION_DATE >= TO_DATE('"
							+ bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAppliDateSelect().trim().equals("BF")) {
					query += " AND HRMS_REDRESSAL_HDR.REDRESSAL_APPLICATION_DATE < TO_DATE('"
							+ bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAppliDateSelect().trim().equals("AF")) {
					query += " AND HRMS_REDRESSAL_HDR.REDRESSAL_APPLICATION_DATE > TO_DATE('"
							+ bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAppliDateSelect().trim().equals("BN")) {
					query += " AND HRMS_REDRESSAL_HDR.REDRESSAL_APPLICATION_DATE >= TO_DATE('"
							+ bean.getAppliFromDate() + "','DD-MM-YYYY')";
					if(!(bean.getAppliToDate().equals("") || bean.getAppliToDate().equals("DD-MM-YYYY"))){
						query += " AND HRMS_REDRESSAL_HDR.REDRESSAL_APPLICATION_DATE <= TO_DATE('"
							+ bean.getAppliToDate() + "','DD-MM-YYYY')";
					}
				}
			}
			//LEAVE FROM DATE
			if (!bean.getLeave1DateSelect().trim().equals("")) {
				//write code here
				if (bean.getLeave1DateSelect().trim().equals("ON")) {
					query += " AND HRMS_REDRESSAL_DTL.REDRESSAL_DTL_FROM_DATE = TO_DATE('"
							+ bean.getLeave1FromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getLeave1DateSelect().trim().equals("OB")) {
					query += " AND HRMS_REDRESSAL_DTL.REDRESSAL_DTL_FROM_DATE <= TO_DATE('"
							+ bean.getLeave1FromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getLeave1DateSelect().trim().equals("OA")) {
					query += " AND HRMS_REDRESSAL_DTL.REDRESSAL_DTL_FROM_DATE >= TO_DATE('"
							+ bean.getLeave1FromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getLeave1DateSelect().trim().equals("BF")) {
					query += " AND HRMS_REDRESSAL_DTL.REDRESSAL_DTL_FROM_DATE < TO_DATE('"
							+ bean.getLeave1FromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getLeave1DateSelect().trim().equals("AF")) {
					query += " AND HRMS_REDRESSAL_DTL.REDRESSAL_DTL_FROM_DATE > TO_DATE('"
							+ bean.getLeave1FromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getLeave1DateSelect().trim().equals("BN")) {
					query += " AND HRMS_REDRESSAL_DTL.REDRESSAL_DTL_FROM_DATE >= TO_DATE('"
							+ bean.getLeave1FromDate() + "','DD-MM-YYYY')";
					if(!(bean.getLeave1ToDate().equals("") || bean.getLeave1ToDate().equals("DD-MM-YYYY"))){
						query += " AND HRMS_REDRESSAL_DTL.REDRESSAL_DTL_FROM_DATE <= TO_DATE('"
							+ bean.getLeave1ToDate() + "','DD-MM-YYYY')";
					}
				}
			
			}

			//LEAVE TO DATE
			if (!bean.getLeave2DateSelect().trim().equals("")) {
				//write code here
				if (bean.getLeave2DateSelect().trim().equals("ON")) {
					query += " AND HRMS_REDRESSAL_DTL.REDRESSAL_DTL_TO_DATE = TO_DATE('"
							+ bean.getLeave2FromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getLeave2DateSelect().trim().equals("OB")) {
					query += " AND HRMS_REDRESSAL_DTL.REDRESSAL_DTL_TO_DATE <= TO_DATE('"
							+ bean.getLeave2FromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getLeave2DateSelect().trim().equals("OA")) {
					query += " AND HRMS_REDRESSAL_DTL.REDRESSAL_DTL_TO_DATE >= TO_DATE('"
							+ bean.getLeave2FromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getLeave2DateSelect().trim().equals("BF")) {
					query += " AND HRMS_REDRESSAL_DTL.REDRESSAL_DTL_TO_DATE < TO_DATE('"
							+ bean.getLeave2FromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getLeave2DateSelect().trim().equals("AF")) {
					query += " AND HRMS_REDRESSAL_DTL.REDRESSAL_DTL_TO_DATE > TO_DATE('"
							+ bean.getLeave2FromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getLeave2DateSelect().trim().equals("BN")) {
					query += " AND HRMS_REDRESSAL_DTL.REDRESSAL_DTL_TO_DATE >= TO_DATE('"
							+ bean.getLeave2FromDate() + "','DD-MM-YYYY')";
					if(!(bean.getLeave2ToDate().equals("") || bean.getLeave2ToDate().equals("DD-MM-YYYY"))){
						query += " AND HRMS_REDRESSAL_DTL.REDRESSAL_DTL_TO_DATE <= TO_DATE('"
							+ bean.getLeave2ToDate() + "','DD-MM-YYYY')";
					}
				}
			
			}			
			
			//Sorting code
			if(!bean.getSortBy().equals("1") || !bean.getThenBy1().equals("1") || 
					!bean.getThenBy2().equals("1")){
				query += " ORDER BY ";
			}
			if (!bean.getSortBy().equals("1")) {
				query += getSortVal(bean.getSortBy(), labelNames) + " "
						+ getSortOrder(bean.getSortByOrder());
				if (!bean.getThenBy1().equals("1")
						|| !bean.getThenBy2().equals("1")) {
					query += " , ";
				}
			}

			if (!bean.getThenBy1().equals("1")) {
				query += getSortVal(bean.getThenBy1(), labelNames) + " "
						+ getSortOrder(bean.getThenByOrder1());
				logger.info("bean.getThenByOrder1() -- > "+bean.getThenByOrder1());
				if (!bean.getThenBy2().equals("1")) {
					query += " , ";
				}
			}

			if (!bean.getThenBy2().equals("1")) {
				query += getSortVal(bean.getThenBy2(), labelNames) + " "
						+ getSortOrder(bean.getThenByOrder2());
			}
		}catch(Exception e){
			logger.error("Exception in condition Query"+e);
		}
		//logger.info("condition query -- "+query);
		return query;
	}	

	private String getSortVal(String status, String[] labelNames) {
		// TODO Auto-generated method stub
		logger.info("Status ---------- "+status);
		String sql = "";
		//NAME
		if(status.equals(labelNames[0])){
			sql = " UPPER(NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||"
					+ "NVL(HRMS_EMP_OFFC.EMP_LNAME,' '))";
		}
		//DIVISION
		else if(status.equals(labelNames[1])){
			sql = " UPPER(NVL(DIV_NAME,' '))";
		}
		//DEPARTMENT
		else if(status.equals(labelNames[2])){
			sql = " UPPER(NVL(DEPT_NAME,' '))";
		}
		//BRANCH
		else if(status.equals(labelNames[3])){
			sql = " UPPER(NVL(CENTER_NAME,' '))";
		}
		//DESIGNATION
		else if(status.equals(labelNames[4])){
			sql = " UPPER(NVL(RANK_NAME,' '))";
		}
		//EMPLOYEE TYPE
		else if(status.equals(labelNames[5])){
			sql = " UPPER(NVL(HRMS_EMP_TYPE.TYPE_NAME,' '))";
		}
		//APPLICATION DATE
		else if(status.equals(labelNames[6])){
			sql = " HRMS_LATE_REG_HDR.LATE_REG_APPLICATION_DATE";
		}
		//STATUS
		else if(status.equals(labelNames[7])){
			sql = " NVL(HRMS_REDRESSAL_DTL.REDRESSAL_DTL_STATUS,' ')";
		}
		//LEAVE FROM DATE
		else if(status.equals(labelNames[8])){
			sql = " HRMS_REDRESSAL_DTL.REDRESSAL_DTL_FROM_DATE";
		}
		//LEAVE TO DATE
		else if(status.equals(labelNames[9])){
			sql = " HRMS_REDRESSAL_DTL.REDRESSAL_DTL_TO_DATE";
		}
		//LEAVE TYPE 
		else if(status.equals(labelNames[10])){
			sql = " HRMS_LEAVE.LEAVE_NAME";
		}
		//LEAVE DAYS 
		else if(status.equals(labelNames[11])){
			sql = " LEAVEDAYS";
		}
		//REDRESSAL DAYS
		else if(status.equals(labelNames[12])){
			sql = " HRMS_REDRESSAL_DTL.REDRESSAL_DTL_REDRESSED_DAYS";
		}
		else {
			sql = " HRMS_EMP_OFFC.EMP_ID";
		}
		return sql;
	}	
	
	private String getSortOrder(String status) {
		// TODO Auto-generated method stub
		String sql = "";
		if (status.equals("A")) {
			sql = "ASC";
		}
		if (status.equals("D")) {
			sql = "DESC";
		}
		return sql;
	}

	public void getReport(RedressalMisReport bean,
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
				reportName = "Redressal Regularization MIS Report";
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
			//Creating Query with Count (count appended after #)
			String queryWithCount = selectQuery(bean,labelNames,count,splitComma,request);
			
			String splitQuery[] = queryWithCount.split("#");
			String query = splitQuery[0];
			count = Integer.parseInt(splitQuery[1]);
			String labels = splitQuery[2];

			logger.info("counter after select query==========" + count);
			logger.info("labels after select query==========" + labels);
			
			// QUERY APPENDED WITH CONDITIONS
			query += conditionQuery(bean, labelNames); 
			
			logger.info("\nquery -- ----------------------->\n"+query);
			Object finalObj[][] = null;
			finalObj = getSqlModel().getSingleResult(query);	
			// CODING FOR HEADERS, WIDTH AND ALIGNMENTS
			String[] str_colNames = new String[count + 1];
			str_colNames[0] = "Employee Code";
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
					if(splitDash[1].equals(labelNames[0].trim())){				//NAME
						str_colNames[++str_colNames_array] = labelNames[0];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[1].trim())){			//DIVISION
						str_colNames[++str_colNames_array] = labelNames[1];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[2].trim())){			//DEPARTMENT
						str_colNames[++str_colNames_array] = labelNames[2];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[3].trim())){			//BRANCH
						str_colNames[++str_colNames_array] = labelNames[3];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[4].trim())){			//DESIGNATION
						str_colNames[++str_colNames_array] = labelNames[4];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[5].trim())){			//EMPLOYEE TYPE
						str_colNames[++str_colNames_array] = labelNames[5];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[6].trim())){			//APPLICATION DATE
						str_colNames[++str_colNames_array] = labelNames[6];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 1;
					}
					else if(splitDash[1].equals(labelNames[7].trim())){			//STATUS
						str_colNames[++str_colNames_array] = labelNames[7];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[8].trim())){			//LEAVE FROM DATE
						str_colNames[++str_colNames_array] = labelNames[8];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 1;
					}
					else if(splitDash[1].equals(labelNames[9].trim())){		//LEAVE TO DATE
						str_colNames[++str_colNames_array] = labelNames[9];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 1;
					}
					else if(splitDash[1].equals(labelNames[10].trim())){		//LEAVE TYPE
						str_colNames[++str_colNames_array] = labelNames[10];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[11].trim())){		//LEAVE DAYS
						str_colNames[++str_colNames_array] = labelNames[11];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 1;
					}
					else if(splitDash[1].equals(labelNames[12].trim())){		//REDRESSAL DAYS
						str_colNames[++str_colNames_array] = labelNames[12];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 1;
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
			logger.error("Exception in getReport -- "+e);
		}
	}	
	
}
