/**
 * 
 */
package org.paradyne.model.attendance;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.attendance.DailyMusterReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import java.util.Iterator;

/**
 * @author Reeba_Joseph
 * @modified By AA1711 : To Generate Attendance Report For Selected Month
 *
 */
public class DailyMusterReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DailyMusterReportModel.class);

	/**Method Name: getReport()
	 * @purpose: To Generate Report
	 * @param rg
	 * @param musterReport
	 * @return reportGenerator
	 */
	private org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, DailyMusterReport musterReport) {
		try {
			/**This query returns Attendance Record of Employee's 
			 * in Selected Month And Division*/
			String empQuery = " SELECT DISTINCT ATT_EMP_ID, NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' '), "
					+ " COUNT(CASE WHEN ATT_STATUS_ONE='WO' THEN ATT_EMP_ID ELSE NULL END) WO_DAYS, "
					+ " COUNT(CASE WHEN ATT_STATUS_ONE !='AB' THEN ATT_EMP_ID ELSE NULL END) WORK_DAYS, "
					+ " HRMS_EMP_OFFC.EMP_TOKEN,"
					+ " COUNT(CASE WHEN ATT_REG_STATUS_TWO='LV' THEN ATT_EMP_ID ELSE NULL END) LEAVE_DAYS,"
					+ " COUNT(CASE WHEN ATT_STATUS_ONE='HO' THEN ATT_EMP_ID ELSE NULL END) HOLIDAYS"
					+ " FROM HRMS_DAILY_ATTENDANCE_"
					+ musterReport.getYear()
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_"
					+ musterReport.getYear()
					+ ".ATT_EMP_ID) "
					+ " WHERE TO_CHAR(ATT_DATE,'YYYY-MM') = '"
					+ musterReport.getYear()
					+ "-"
					+ musterReport.getMonth()
					+ "'"
					+ " AND HRMS_EMP_OFFC.EMP_DIV IN ( "
					+ musterReport.getDivCode() + " )";					
			empQuery += getFilters(musterReport);
			empQuery += " GROUP BY ATT_EMP_ID,HRMS_EMP_OFFC.EMP_TOKEN, NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' ') "
					+ " ORDER BY ATT_EMP_ID";
			Object[][] empObj = getSqlModel().getSingleResult(empQuery);
			/**It Calculate Total Days in Selected Months */
			String lastDayQuery = "SELECT TO_CHAR(LAST_DAY(TO_DATE('"
					+ musterReport.getYear() + "-" + musterReport.getMonth()
					+ "','YYYY-MM')),'DD') FROM DUAL";
			Object[][] monthLastDay = getSqlModel().getSingleResult(
					lastDayQuery);
			String empList = convertEmpListToString(empObj);
			String month = musterReport.getMonth();
			String year = musterReport.getYear();
			HashMap<String, String> map = addToHashMap(empList, month, year);
			/* Setting filter details */
			String filters = "Period: "
					+ Utility.month(Integer.parseInt(month)) + " - " + year
					+ "";
			if (!musterReport.getDivName().equals("")) {
				filters += "\n\nDivision: " + musterReport.getDivName();
			}
			if (!musterReport.getBrnName().equals("")) {
				filters += "\n\nBranch: " + musterReport.getBrnName();
			}

			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filters } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			// /filterData.setCellColSpan(new int[]{5});
			filterData.setBorder(false);
			// filterData.setBlankRowsBelow(1);
			rg.addTableToDoc(filterData);
			if (empObj != null && empObj.length > 0) {
				String[] str_colNames = null;
				int[] cellWidth = null;
				int[] cellAlign = null;
				Object[][] finalObj = null;

				if (monthLastDay != null && monthLastDay.length > 0) {
					finalObj = new Object[empObj.length][Integer
							.parseInt(String.valueOf(monthLastDay[0][0])) + 7];
				} // end of if
				/**It is write to give Date wise Attendance Record 
				 * From HRMS_DAILY_ATTENDANCE_YEAR
				 * of All employees which satisfies that Filters */
				String attDateQuery = "SELECT  ATT_EMP_ID||'#'||To_number(TO_CHAR(ATT_DATE,'DD')),NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' '), "
								+ " CASE  "
								+ " WHEN ATT_STATUS_ONE='WO' THEN 'WO'"
								+ " WHEN ATT_REG_STATUS_TWO='LV' THEN 'LV'  "
								+ " WHEN ATT_REG_STATUS_TWO='TR' THEN 'TR' "
								+ " WHEN ATT_REG_STATUS_TWO='RG' THEN 'REG' "
								+ " WHEN ATT_REG_STATUS_TWO='HL' THEN 'HDL'"
								+ " WHEN ATT_REG_STATUS_TWO='EW' THEN 'EW'  "
								+ " WHEN (ATT_STATUS_ONE='PR' and ATT_STATUS_TWO='IN') THEN 'PR' "
								+ " WHEN ATT_STATUS_ONE='PR' then DECODE(ATT_STATUS_TWO,'HD','HD','DL','DL','LC','LC','EL','EL','PR','PR','AB','AB') "
								+ " ELSE DECODE(ATT_STATUS_ONE,'AB','AB','WO','WO','HO','HO') "
								+ " END ATT_STATUS_ONE , TO_CHAR(ATT_DATE,'DD')"
								+ " FROM HRMS_DAILY_ATTENDANCE_"
								+ musterReport.getYear()
								+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_DAILY_ATTENDANCE_"+musterReport.getYear()+".ATT_EMP_ID)"
								+ " WHERE TO_CHAR(ATT_DATE,'YYYY-MM') = '"
								+ musterReport.getYear()
								+ "-"
								+ musterReport.getMonth()
								+ "'"
								+ " AND HRMS_EMP_OFFC.EMP_DIV IN ("
								+ musterReport.getDivCode()	+ ") ";
			attDateQuery+= getFilters(musterReport);	
			attDateQuery+= " ORDER BY ATT_EMP_ID, ATT_DATE";
				/**This query returns MAP as Output*/
				Map attMap = getSqlModel().getSingleResultMap(attDateQuery, 0,
						0);
				HashMap<String, Object> attendanceDataMap = new HashMap<String, Object>();
				int monthDays = Integer.parseInt(String
						.valueOf(monthLastDay[0][0]));

				int int_count = 2;
				str_colNames = new String[monthDays + 7];
				str_colNames[0] = "Sr No";
				str_colNames[1] = "Employee Code";
				str_colNames[2] = "Employee Name";
				str_colNames[3] = "Work Days";
				str_colNames[4] = "Weekly Off";				
				str_colNames[5] = "Leave Days";
				str_colNames[6] = "Holidays";
				int str_colNames_array = 6;

				cellWidth = new int[monthDays + 7];
				cellWidth[0] = 10;
				cellWidth[1] = 25;
				cellWidth[2] = 30;
				int cellWidth_array = 2;

				cellAlign = new int[monthDays + 7];
				cellAlign[0] = 0;
				cellAlign[1] = 0;
				cellAlign[2] = 0;
				int cellAlign_array = 2;
				str_colNames[++str_colNames_array] = String
						.valueOf(monthDays);
				cellWidth[++cellWidth_array] = 15;
				cellAlign[++cellAlign_array] = 0;
				
				/**To define Column Names as month Dates */
				for (int f = 1; f <= monthDays; f++) {
					str_colNames[f + 6] = "" + f;
					cellWidth[++cellWidth_array] = 15;
					cellAlign[++cellAlign_array] = 0;
				}
				/**Assign values to First Four columns*/
				for (int k = 0; k < empObj.length; k++) {
					finalObj[k][0] = k + 1; // Sr NO
					finalObj[k][1] = String.valueOf(empObj[k][4]);// Emp code
					finalObj[k][2] = String.valueOf(empObj[k][1]);// Emp Name
					finalObj[k][3] = String.valueOf(empObj[k][3]);// Work Days
					finalObj[k][4] = String.valueOf(empObj[k][2]);// Weekly Off Count					
					finalObj[k][5] = String.valueOf(empObj[k][5]);// Leave Days
					finalObj[k][6] = String.valueOf(empObj[k][6]); //HoliDays

					/** Assign Attendance Status 
					 * in Respective Attendance Date Column's */
					for (int l = 1; l <= monthDays; l++) {	
						try {
							Set set = attMap.entrySet();
							Iterator it = (Iterator) set.iterator();
							while (it.hasNext()) {
								Map.Entry me = (Map.Entry) it.next();								
								Object[] objAttendance = (Object[]) me.getValue();

								try {
									if (me.getKey().equals(
											empObj[k][0] + "#" + l)) {
										finalObj[k][l + 6] = String.valueOf(objAttendance[2]);										
									}
								} catch (Exception e) {
									finalObj[k][l + 6] = "NA";
								}								
							}

						} catch (Exception e) {
							finalObj[k][l + 6] = "NA";
							e.printStackTrace();
						}
					}
				}
				int[] cellWidthDateHeader = { 100 };
				int[] cellAlignDateHeader = { 0 };

				Object[][] summaryData = new Object[1][1];
				summaryData[0][0] = "Daily Muster Report :";

				TableDataSet tableheadingDateData = new TableDataSet();
				tableheadingDateData.setData(summaryData);
				tableheadingDateData.setCellWidth(cellWidthDateHeader);
				tableheadingDateData.setCellAlignment(cellAlignDateHeader);
				tableheadingDateData.setBodyFontStyle(1);
				tableheadingDateData.setCellColSpan(new int[] { 16 });
				tableheadingDateData.setBorderDetail(0);
				tableheadingDateData.setBlankRowsAbove(1);
				rg.addTableToDoc(tableheadingDateData);

				TableDataSet tdstable = new TableDataSet();
				tdstable.setHeader(str_colNames);
				tdstable.setHeaderBorderDetail(3);
				// tdstable.setCellNoWrap(bcellwrap);
				tdstable.setData(finalObj);
				tdstable.setCellAlignment(cellAlign);
				tdstable.setCellWidth(cellWidth);
				tdstable.setBorderDetail(3);
				tdstable.setHeaderTable(true);
				rg.addTableToDoc(tdstable);
			} else {
				// rg.addFormatedText("There is no data to display.", 0, 1, 1,
				// 0);
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "Daily Muster Report : No records available";
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 0 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBlankRowsAbove(1);
				noData.setBorder(false);
				rg.addTableToDoc(noData);

			}
			// /rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
	
	/**Method Name: getFilters()
	 * @purpose : To Filter
	 * @param musterReport
	 * @return String
	 */
	private String getFilters(DailyMusterReport musterReport) {
		String query = "";
		logger.info("Brn code : "+musterReport.getBrnCode());
		if (!musterReport.getBrnCode().trim().equals("")
				&& musterReport.getBrnCode() != null
				&& !musterReport.getBrnCode().trim().equals("null")){
			query += " AND HRMS_EMP_OFFC.EMP_CENTER IN ( "+musterReport.getBrnCode()+" )";
		}
		return query;
	}

	/**Method Name: convertEmpListToString()
	 * @purpose:  This method returns the string of empIds with comma seperated between
	 * them..
	 * @param empList
	 * @return empIds
	 */
	private String convertEmpListToString(Object[][] empList) {
		String empId = "";
		
		if (empList != null && empList.length > 0) {
		try {
			for (int i = 0; i < empList.length; i++) {
				empId += String.valueOf(empList[i][0]) + ",";
			} // end of loop
		} catch (Exception e) {
			logger.error("exception in empList loop", e);
		} // end of catch
		empId = empId.substring(0, empId.length() - 1);
		}
		return empId;
	} // end of getEmpList method
	
	/**Method Name: addToHashMap()
	 * @purpose: It returns Leave Type with respective Leave Balance
	 * @param empList
	 * @param month
	 * @param year
	 * @return HashMap
	 */
	private HashMap<String, String> addToHashMap(String empList, String month, String year) {
		HashMap<String, String> dataMap = new HashMap<String, String>();
		String leaveMapQuery = "SELECT EMP_ID, LEAVE_CODE, SUM(LEAVE_DAYS) FROM HRMS_LEAVE_DTLHISTORY "
			+ " WHERE EMP_ID IN ("+empList+") AND LEAVE_DTL_STATUS = 'A' AND LEAVE_MONTH = '"+month+"' AND LEAVE_YEAR = '"+year+"' " 
			+ " GROUP BY LEAVE_CODE, EMP_ID";
		Object[][] totalDataObject = getSqlModel().getSingleResult(leaveMapQuery);
		
		if (totalDataObject == null) {

		} // end of if
		else if (totalDataObject.length == 0) {

		} // end of else if
		else {
			try{
				for (int j = 0; j < totalDataObject.length; j++) {
					String empId = "";
					String leaveId = "";
					empId = String.valueOf(totalDataObject[j][0]);
					leaveId = String.valueOf(totalDataObject[j][1]);
					dataMap.put(empId+"-"+leaveId, String.valueOf(totalDataObject[j][2]));
				} //end of totalDataObject loop
			}catch(Exception e){
				logger.error("exception in addToHashMap",e);
			} //end of catch
		} //end of else
		return dataMap;
	} // end of convertObjectToHashMap method

	
	/**Method Name: getReport()
	 * @purpose:  To generate Report
	 * @param bean
	 * @param response
	 * @param request
	 * @param reportPath
	 */
	public void getReport(DailyMusterReport bean,
			HttpServletResponse response, HttpServletRequest request,
			 String reportPath) {

		try {
			ReportDataSet rds = new ReportDataSet();
			Date date = new Date();
			
			String type = bean.getReportType();
			System.out.println("type==="+type);
			rds.setReportType(type);
			String fileName = "Daily_Muster_Report_"+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setReportName("Daily Muster Report");
			
			rds.setPageSize("TABLOID");
			rds.setPageOrientation("landscape");
			rds.setUserEmpId(bean.getUserEmpId());
			/*rds.setMarginBottom(25);
			rds.setMarginLeft(25);
			rds.setMarginRight(25);*/
			rds.setShowPageNo(true);
			rds.setTotalColumns(15);
			//rds.setMarginTop(25);
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;			
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context,request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context,request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName + "." + type);
				request.setAttribute("action", "/attendance/DailyMusterReport_input.action");
			}
			rg = getReport(rg, bean);
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
