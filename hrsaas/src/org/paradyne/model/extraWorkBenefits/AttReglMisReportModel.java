package org.paradyne.model.extraWorkBenefits;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.extraWorkBenefits.AttReglMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;

import com.itextpdf.text.BaseColor;


/**
 * @author Ayyappa
 * @date 23-03-2010
 */
public class AttReglMisReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	public boolean deleteSavedReport(AttReglMisReport misreport) {
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
	public boolean checkDuplicate(AttReglMisReport misreport) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_MISREPORT_HDR WHERE REPORT_TYPE LIKE 'AttendanceRegl' AND UPPER(REPORT_CRITERIA) LIKE '"
				+ misreport.getSettingName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}	

	public boolean saveReportCriteria(AttReglMisReport misreport) {
		boolean result = false;
		if (!checkDuplicate(misreport)) {
			Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = checkNull(misreport.getSettingName().trim());
			saveObj[0][1] = checkNull(misreport.getReportTitle().trim());
			saveObj[0][2] = "AttendanceRegl";
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
	
	public boolean saveFilters(AttReglMisReport misreport){
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
	
	public boolean saveColumns(AttReglMisReport misreport){
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
		//Attendance Date
		if(misreport.getAttDateFlag().equals("true")){
			count++;
		}				
		//Actual In Time
		if(misreport.getActInFlag().equals("true")){
			count++;
		}
		//Actual Out Time
		if(misreport.getActOutFlag().equals("true")){
			count++;
		}	
		//Status
		if(misreport.getStatusFlag().equals("true")){
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
		//Attendance Date
		if(misreport.getAttDateFlag().equals("true")){
			addObj[int_count][0] = "misreport.attDateFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}				
		//Actual In Time
		if(misreport.getActInFlag().equals("true")){
			addObj[int_count][0] = "misreport.actInFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Actual Out Time
		if(misreport.getActOutFlag().equals("true")){
			addObj[int_count][0] = "misreport.actOutFlag";
			addObj[int_count][1] = "Y";
			int_count++;
		}
		//Status
		if(misreport.getStatusFlag().equals("true")){
			addObj[int_count][0] = "misreport.statusFlag";
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
	
	public boolean saveSortOptions(AttReglMisReport misreport){
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
	
	public boolean saveAdvancedFilters(AttReglMisReport misreport){
		
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
		//Attendance Date
		if (!misreport.getAttDateSelect().equals("")) {
			count++;
		}
		if (!misreport.getAttFromDate().equals("")) {
			count++;
		}
		if (!misreport.getAttToDate().equals("")) {
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
		if (!misreport.getAttDateSelect().equals("")) {
			addObj[int_count][0] = "attDateSelect";
			addObj[int_count][1] = checkNull(misreport.getAttDateSelect().trim());
			int_count++;
		}
		if (!misreport.getAttFromDate().equals("")) {
			addObj[int_count][0] = "misreport.attFromDate";
			addObj[int_count][1] = checkNull(misreport.getAttFromDate().trim());
			int_count++;
		}
		if (!misreport.getAttToDate().equals("")) {
			addObj[int_count][0] = "misreport.attToDate";
			addObj[int_count][1] = checkNull(misreport.getAttToDate().trim());
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

	public void setDetailOnScreen(AttReglMisReport misreport) {
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
					Method modelMethod = AttReglMisReport.class.getDeclaredMethod(
										"set"+initCap(methodStr), String.class);
					logger.info("modelMethod - "+modelMethod);
					modelMethod.invoke(misreport, dtlObj[i][2]);
				}//End of for
			}catch(Exception e){
				logger.error("Exception in setDetailOnScreen - "+e);
			}//End of try-catch
		}//End of if
	}//End of method	
	
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

	public void callViewScreen(AttReglMisReport misreport,
			HttpServletRequest request, String[] labelNames) {
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
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
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

	private String selectQuery(AttReglMisReport misreport, String[] labelNames,
			int count, String[] splitComma, HttpServletRequest request) {
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
					columnMap.put(Integer.parseInt(splitDash[0])," TO_CHAR(SWIPE_REG_APPLICATION_DATE,'DD-MM-YYYY')");
					labelMap.put(7, labelNames[6]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[7].trim())){			//ATTENDANCE DATE
					columnMap.put(Integer.parseInt(splitDash[0])," TO_CHAR(SWIPE_REG_DTL_DATE,'DD-MM-YYYY')");
					labelMap.put(8, labelNames[7]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[8].trim())){			//STATUS 
					columnMap.put(Integer.parseInt(splitDash[0]),
							"NVL(DECODE(SWIPE_REG_DTL_STATUS,'P','Pending','A','Approved','R','Rejected','B','Sent Back'),' ')");
					labelMap.put(9, labelNames[8]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[9].trim())){			//ACTUAL IN TIME
					columnMap.put(Integer.parseInt(splitDash[0])," TO_CHAR(SWIPE_REG_DTL_ACT_INTIME,'HH24:MI')");
					labelMap.put(10, labelNames[9]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[10].trim())){			//ACTUAL OUT TIME
					columnMap.put(Integer.parseInt(splitDash[0])," TO_CHAR(SWIPE_REG_DTL_ACT_OUTTIME,'HH24:MI')");
					labelMap.put(11, labelNames[10]);
					count++;
				}
				else if(splitDash[1].equals(labelNames[11].trim())){			
					columnMap.put(Integer.parseInt(splitDash[0])," NVL(HRMS_SWIPE_REG_HDR.SWIPE_REG_REASON,' ')");
					labelMap.put(12, labelNames[11]);
					count++;
				}				
				else if(splitDash[1].equals(labelNames[12].trim())){			
					columnMap.put(Integer.parseInt(splitDash[0]),"TO_CHAR(HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_FROM_TIME,'HH24:MI')");
					labelMap.put(13, labelNames[12]);
					count++;
				}
				
				else if(splitDash[1].equals(labelNames[13].trim())){	
					columnMap.put(Integer.parseInt(splitDash[0])," TO_CHAR(HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_TO_TIME,'HH24:MI')");
					labelMap.put(14, labelNames[13]);
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
	

	private String conditionQuery(AttReglMisReport bean,
			String[] labelNames) {
		String query="";
		try{
			query ="FROM HRMS_SWIPE_REG_DTL"
				+" LEFT JOIN HRMS_SWIPE_REG_HDR ON (HRMS_SWIPE_REG_HDR.SWIPE_REG_ID = HRMS_SWIPE_REG_DTL.SWIPE_REG_ID) " 
				+" INNER JOIN HRMS_EMP_OFFC ON  (HRMS_EMP_OFFC.EMP_ID = HRMS_SWIPE_REG_HDR.SWIPE_REG_EMP_ID) " 
				+" INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) " 
				+" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) " 
				+" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER) " 
				+" INNER JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_EMP_OFFC.EMP_TYPE)" 
				+" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK) " 
				+" WHERE 1 = 1 ";
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
				if(!bean.getStatus().equals("")){
				query += " AND HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_STATUS = '"+bean.getStatus().trim()+"'";
				}
			}
			//EMPLOYEE
			if(!(bean.getEmpId().equals("")) && !(bean.getEmpId() == null) && !(bean.getEmpId().equals("null")) ){
				query += " AND HRMS_EMP_OFFC.EMP_ID = "+bean.getEmpId();
			}			
			
			//ADVANCE FILTER
			//APPLICATION DATE
			if (!bean.getAppliDateSelect().trim().equals("")) {
				if (bean.getAppliDateSelect().trim().equals("ON")) {
					query += " AND HRMS_SWIPE_REG_HDR.SWIPE_REG_APPLICATION_DATE = TO_DATE('"
							+ bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAppliDateSelect().trim().equals("OB")) {
					query += " AND HRMS_SWIPE_REG_HDR.SWIPE_REG_APPLICATION_DATE <= TO_DATE('"
							+ bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAppliDateSelect().trim().equals("OA")) {
					query += " AND HRMS_SWIPE_REG_HDR.SWIPE_REG_APPLICATION_DATE >= TO_DATE('"
							+ bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAppliDateSelect().trim().equals("BF")) {
					query += " AND HRMS_SWIPE_REG_HDR.SWIPE_REG_APPLICATION_DATE < TO_DATE('"
							+ bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAppliDateSelect().trim().equals("AF")) {
					query += " AND HRMS_SWIPE_REG_HDR.SWIPE_REG_APPLICATION_DATE > TO_DATE('"
							+ bean.getAppliFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAppliDateSelect().trim().equals("BN")) {
					query += " AND HRMS_SWIPE_REG_HDR.SWIPE_REG_APPLICATION_DATE >= TO_DATE('"
							+ bean.getAppliFromDate() + "','DD-MM-YYYY')";
					if(!(bean.getAppliToDate().equals("") || bean.getAppliToDate().equals("DD-MM-YYYY"))){
						query += " AND HRMS_SWIPE_REG_HDR.SWIPE_REG_APPLICATION_DATE <= TO_DATE('"
							+ bean.getAppliToDate() + "','DD-MM-YYYY')";
					}
				}
			}
			//ATTENDANCE DATE
			if (!bean.getAttDateSelect().trim().equals("")) {
				//write code here
				if (bean.getAttDateSelect().trim().equals("ON")) {
					query += " AND HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_DATE = TO_DATE('"
							+ bean.getAttFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAttDateSelect().trim().equals("OB")) {
					query += " AND HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_DATE <= TO_DATE('"
							+ bean.getAttFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAttDateSelect().trim().equals("OA")) {
					query += " AND HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_DATE >= TO_DATE('"
							+ bean.getAttFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAttDateSelect().trim().equals("BF")) {
					query += " AND HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_DATE < TO_DATE('"
							+ bean.getAttFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAttDateSelect().trim().equals("AF")) {
					query += " AND HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_DATE > TO_DATE('"
							+ bean.getAttFromDate() + "','DD-MM-YYYY')";
				}
				if (bean.getAttDateSelect().trim().equals("BN")) {
					query += " AND HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_DATE >= TO_DATE('"
							+ bean.getAttFromDate() + "','DD-MM-YYYY')";
					if(!(bean.getAttToDate().equals("") || bean.getAttToDate().equals("DD-MM-YYYY"))){
						query += " AND HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_DATE <= TO_DATE('"
							+ bean.getAttToDate() + "','DD-MM-YYYY')";
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
			sql = " HRMS_SWIPE_REG_HDR.SWIPE_REG_APPLICATION_DATE";
		}
		//ATTENDANCE DATE
		else if(status.equals(labelNames[7])){
			sql = " HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_DATE";
		}
		//STATUS
		else if(status.equals(labelNames[8])){
			sql = " NVL(HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_STATUS,' ')";
		}
		//ACTUAL IN TIME
		else if(status.equals(labelNames[9])){
			sql = " HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_ACT_INTIME";
		}
		//ACTUAL OUT TIME
		else if(status.equals(labelNames[10])){
			sql = " HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_ACT_OUTTIME";
		}
		
		else if(status.equals(labelNames[12])){
			sql = " HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_FROM_TIME";
		}
		else if(status.equals(labelNames[13])){
			sql = " HRMS_SWIPE_REG_DTL.SWIPE_REG_DTL_TO_TIME";
		}
		else {
			sql = " HRMS_EMP_OFFC.EMP_ID";
		}
		return sql;
	}	
	
	private String getSortOrder(String status) {
		String sql = "";
		if (status.equals("A")) {
			sql = "ASC";
		}
		if (status.equals("D")) {
			sql = "DESC";
		}
		return sql;
	}

	/**
	 * @param bean
	 * @param response
	 * @param labelNames
	 * @param request
	 */
	public void getReport(AttReglMisReport bean, HttpServletResponse response, String[] labelNames,	HttpServletRequest request) {
		try{
			ReportDataSet rds = new ReportDataSet();
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
			logger.info("reportType--------------->" + reportType);
			
			rds.setReportType(reportType);
			
			String reportName = "";
			String fileName="";
			if (!bean.getReportTitle().equals(" ")){
				reportName = bean.getReportTitle();
			}	
			else{
				reportName = "Attendance_Regularization_MIS_Report";
			}
			
			 fileName = reportName+"_"+Utility.getRandomNumber(1000);
			
			
			rds.setFileName(fileName);
			rds.setReportName("Attendance_Regularization_MIS_Report");
			rds.setPageOrientation("landscape");
			rds.setPageSize("A4");
			rds.setShowPageNo(true);
			rds.setUserEmpId(bean.getUserEmpId());
			
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			
			rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			
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
					else if(splitDash[1].equals(labelNames[7].trim())){			//ATTENDANCE DATE
						str_colNames[++str_colNames_array] = labelNames[7];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 1;
					}
					else if(splitDash[1].equals(labelNames[8].trim())){			//STATUS 
						str_colNames[++str_colNames_array] = labelNames[8];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[9].trim())){			//ACTUAL IN TIME
						str_colNames[++str_colNames_array] = labelNames[9];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 1;
					}
					else if(splitDash[1].equals(labelNames[10].trim())){			//ACTUAL OUT TIME
						str_colNames[++str_colNames_array] = labelNames[10];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 1;
					}
					else if(splitDash[1].equals(labelNames[11].trim())){		//Reason
						str_colNames[++str_colNames_array] = labelNames[11];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 1;
					}
					else if(splitDash[1].equals(labelNames[12].trim())){		//Recorded In Time	
						str_colNames[++str_colNames_array] = labelNames[12];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 1;
					}					
					else if(splitDash[1].equals(labelNames[13].trim())){			//Recorded Out Time
						str_colNames[++str_colNames_array] = labelNames[13];
						cellWidth[++cellWidth_array] = 13;
						cellAlign[++cellAlign_array] = 1;
					}
				}//End of for
			}//End of if
			
			logger.info("Export all values   : " + bean.getExportAll());
			logger.info("counter for exporting==========" + bean.getReqStatus());
			if(finalObj != null && finalObj.length > 0){
				if(bean.getReqStatus().trim().equals("R"))
					bean.setExportAll("on");		
				//rg.addFormatedText(reportName, 6, 0, 1, 1);
				
				if (bean.getExportAll().equals("on")) {
					if (bean.getReportType().equals("P")) {
						//rg.tableBody(str_colNames, finalObj, cellWidth, cellAlign);
						
						TableDataSet table1 = new TableDataSet();
						table1.setHeader(str_colNames);
						table1.setHeaderBorderDetail(3);
						table1.setHeaderTable(true);
						table1.setHeaderFontStyle(1);
						table1.setData(finalObj);
						table1.setCellAlignment(cellAlign);
						table1.setCellWidth(cellWidth);
						table1.setBorderDetail(3);
						rg.addTableToDoc(table1);
						
					} else if (bean.getReportType().equals("X")) {
						//rg.xlsTableBody(str_colNames, finalObj, cellWidth, cellAlign);
						
						TableDataSet table2 = new TableDataSet();
						table2.setHeader(str_colNames);
						table2.setHeaderBorderDetail(3);
						table2.setHeaderTable(true);
						table2.setHeaderFontStyle(1);
						table2.setData(finalObj);
						table2.setCellAlignment(cellAlign);
						table2.setCellWidth(cellWidth);
						table2.setBorderDetail(3);
						rg.addTableToDoc(table2);
						
					} else {
						//rg.tableBody(str_colNames, finalObj, cellWidth,	cellAlign);
						
						TableDataSet table3 = new TableDataSet();
						table3.setHeader(str_colNames);
						table3.setHeaderBorderDetail(3);
						table3.setHeaderTable(true);
						table3.setHeaderFontStyle(1);
						table3.setData(finalObj);
						table3.setCellAlignment(cellAlign);
						table3.setCellWidth(cellWidth);
						table3.setBorderDetail(3);
						rg.addTableToDoc(table3);
					}
				}else{
					String[] pageIndex = Utility.doPaging(bean.getMyPage(), finalObj.length, 20);
					
					if (pageIndex == null) {
						pageIndex[0] = "0";
						pageIndex[1] = "20";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					}
					int numOfRec = Integer.parseInt(pageIndex[1]) - Integer.parseInt(pageIndex[0]);
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
						//rg.tableBody(str_colNames, pageObj, cellWidth, cellAlign);
						
						TableDataSet table1 = new TableDataSet();
						table1.setHeader(str_colNames);
						table1.setHeaderBorderDetail(3);
						table1.setHeaderTable(true);
						table1.setHeaderFontStyle(1);
						table1.setData(pageObj);
						table1.setCellAlignment(cellAlign);
						table1.setCellWidth(cellWidth);
						table1.setBorderDetail(3);
						rg.addTableToDoc(table1);
						
					} else if (bean.getReportType().equals("X")) {
						//rg.xlsTableBody(str_colNames, pageObj, cellWidth, cellAlign);
						
						TableDataSet table2 = new TableDataSet();
						table2.setHeader(str_colNames);
						table2.setHeaderBorderDetail(3);
						table2.setHeaderTable(true);
						table2.setHeaderFontStyle(1);
						table2.setData(pageObj);
						table2.setCellAlignment(cellAlign);
						table2.setCellWidth(cellWidth);
						table2.setBorderDetail(3);
						rg.addTableToDoc(table2);						
					} else {
						//rg.tableBody(str_colNames, pageObj, cellWidth, cellAlign);
						
						TableDataSet table3 = new TableDataSet();
						table3.setHeader(str_colNames);
						table3.setHeaderBorderDetail(3);
						table3.setHeaderTable(true);
						table3.setHeaderFontStyle(1);
						table3.setData(pageObj);
						table3.setCellAlignment(cellAlign);
						table3.setCellWidth(cellWidth);
						table3.setBorderDetail(3);
						rg.addTableToDoc(table3);						
					}
				}
			}else{
				TableDataSet noDataTable = new TableDataSet();
				noDataTable.setData(new Object[][] {{"No data to display" }});
				noDataTable.setCellAlignment(new int[]{1});
				noDataTable.setCellWidth(new int[]{100});
				noDataTable.setBorderDetail(0);
				rg.addTableToDoc(noDataTable);
			}
			rg.process();
			rg.createReport(response);
		}catch(Exception e){
			logger.error("Exception in getReport -- "+e);
		}
	}
	public void getReport_OLD(AttReglMisReport bean,
			HttpServletResponse response, String[] labelNames,
			HttpServletRequest request) {
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
				reportName = "Attendance Regularization MIS Report";
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
					else if(splitDash[1].equals(labelNames[7].trim())){			//ATTENDANCE DATE
						str_colNames[++str_colNames_array] = labelNames[7];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 1;
					}
					else if(splitDash[1].equals(labelNames[8].trim())){			//STATUS 
						str_colNames[++str_colNames_array] = labelNames[8];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}
					else if(splitDash[1].equals(labelNames[9].trim())){			//ACTUAL IN TIME
						str_colNames[++str_colNames_array] = labelNames[9];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 1;
					}
					else if(splitDash[1].equals(labelNames[10].trim())){			//ACTUAL OUT TIME
						str_colNames[++str_colNames_array] = labelNames[10];
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
							// logger.info("objData1["+i+"]["+j+"] :
							// "+objData1[i][j]);
							pageObj[z][j] = finalObj[i][j];
							// pageObj[z][0] = String.valueOf(srNo);
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
