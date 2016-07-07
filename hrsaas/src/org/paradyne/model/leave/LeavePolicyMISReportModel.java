package org.paradyne.model.leave;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.leave.LeavePolicyMIS;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class LeavePolicyMISReportModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);

	/**
	 * THIS METHOD IS USED FOR GETTING EMPLOYEE INFORMATION
	 * 
	 * @param empId-emplolyee
	 *            id
	 * @param bean
	 */
	public void getEmployeeDetails(String empId, LeavePolicyMIS bean) {
		Object[] beanObj = new Object[1];
		beanObj[0] = empId;// employee id
		
		String query = " SELECT HRMS_EMP_OFFC.EMP_ID, HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	HRMS_DIVISION.DIV_ID,HRMS_DIVISION.DIV_NAME "
				+ "	FROM HRMS_EMP_OFFC "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
				+ " INNER JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID = ?";

		Object[][] values = getSqlModel().getSingleResult(query, beanObj);
		bean.setEmployeeCode(checkNull(String.valueOf(values[0][0])));// employee id
		bean.setTokenNo(checkNull(String.valueOf(values[0][1])));// employee
		// token
		bean.setEmployeeName(checkNull(String.valueOf(values[0][2])));// employee
		// name
		bean.setDivisionCode(checkNull(String.valueOf(values[0][3])));// division code
		 
		bean.setDivisionName(checkNull(String.valueOf(values[0][4])));// division
		// name
		

	}// end of getEmployeeDetails


	public void report(LeavePolicyMIS leavePolicyMIS, HttpServletRequest request, HttpServletResponse response, String path) {
		try {
			// TODO Auto-generated method stub
			String divAbbrQuery = "SELECT HRMS_DIVISION.DIV_NAME, NVL(HRMS_DIVISION.DIV_ABBR,' ')"
					+ "  FROM HRMS_DIVISION  WHERE HRMS_DIVISION.DIV_ID=?";
			
			Object[] parameterObj = null;
			parameterObj = new Object[1];
			parameterObj[0] = leavePolicyMIS.getDivisionCode();  
			
			Object[][] queryData = getSqlModel().getSingleResult(divAbbrQuery, parameterObj);
			
			String reportType = "" + leavePolicyMIS.getReportType();
			String reportName = "Leave Policy MIS Report";		

			String name = "Leave Policy MIS Report for ";
			
			if(reportType.equals("") || reportType == null || reportType.equals("null")){
				reportType = "pdf";
			}		
			
			ReportDataSet  rds = new ReportDataSet();
			String fileName = "Leave_Policy_MIS_" + queryData[0][1] + "_" + Utility.getRandomNumber(1000);
			String reportPathName=fileName+"."+reportType;
			rds.setFileName(fileName);
			rds.setReportName(reportName);
			rds.setReportType(reportType);
			rds.setUserEmpId(leavePolicyMIS.getUserEmpId());
			rds.setReportHeaderRequired(true);
			rds.setTotalColumns(8);
			ReportGenerator rg = null;
			
			if(path.equals("")){
				rg = new ReportGenerator(rds,session,context, request);
				new ReportGenerator(rds,session,context);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+path+fileName+"."+reportType);
				rg = new ReportGenerator(rds,path,session,context, request);
				request.setAttribute("reportPath", path+fileName+"."+reportType);
				request.setAttribute("fileName", fileName + "."	+ reportType);
				request.setAttribute("action", "LeavePolicyMIS_input.action?path="+reportPathName);
			}		
						
			String res = "";
			//int month = Integer.parseInt(entitle.getMonth());// entitle month
			//int year = Integer.parseInt(entitle.getYear());// entitle year
			String insertQuery = "INSERT INTO HRMS_LEAVE_PROCESS (LEAVE_MONTH,LEAVE_YEAR,LEAVE_ENTITLEDATE,DIV_CODE) VALUES (?,?,TO_DATE(?,'DD-MM-YYYY'),?)";
			String selTypeQuery = " SELECT TYPE_ID FROM HRMS_EMP_TYPE ORDER BY TYPE_ID";
			String empIdsQuery = " SELECT HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC WHERE HRMS_EMP_OFFC.EMP_STATUS='S'  ";
			/*Object[][] nameData = new Object[3][4];
			nameData[0][0] = "Division :";
			nameData[0][1] = "";//name
			nameData[0][2] = "Department :";
			nameData[0][3] = "";
			nameData[1][0] = "Branch :";
			nameData[1][1] = "";
			nameData[1][2] = "Employee Type :";
			nameData[1][3] = "";
			nameData[2][0] = "Designation :";
			nameData[2][1] = "";
			nameData[2][2] = "";
			nameData[2][3] = "";
			*/
			String filterObject = "";
			
			int[] width_1 = { 15, 25, 15, 25 };
			int[] align_1 = { 0, 0, 0, 0 };
			//DIV
			if (!leavePolicyMIS.getDivisionCode().equals("")) {
				empIdsQuery += " 	AND HRMS_EMP_OFFC.EMP_DIV= "
						+ leavePolicyMIS.getDivisionCode();

				name += " 	Division ->" + leavePolicyMIS.getDivisionName();

				//nameData[0][1] = leavePolicyMIS.getDivisionName();
				filterObject += "Division : " + leavePolicyMIS.getDivisionName();
			}
			//DEPARTMENT
			if (!leavePolicyMIS.getDeptCode().equals("")) {
				empIdsQuery += " AND HRMS_EMP_OFFC.EMP_DEPT= " + leavePolicyMIS.getDeptCode();
				name += " 	Department ->" + leavePolicyMIS.getDeptName();
				//nameData[0][3] = leavePolicyMIS.getDeptName();
				filterObject += "\n\nDepartment : " + leavePolicyMIS.getDeptName();
			}
			//BRANCH
			if (!leavePolicyMIS.getBranchCode().equals("")) {
				empIdsQuery += " AND HRMS_EMP_OFFC.EMP_CENTER= "
						+ leavePolicyMIS.getBranchCode();
				name += " 	Branch ->" + leavePolicyMIS.getBranchName();
				//nameData[1][1] = leavePolicyMIS.getBranchName();
				filterObject += "\n\nBranch : " + leavePolicyMIS.getBranchName();
			}
			//EMPLOYEE TYPE
			if (!leavePolicyMIS.getEmpTypeCode().equals("")) {
				empIdsQuery += " AND HRMS_EMP_OFFC.EMP_TYPE= "
						+ leavePolicyMIS.getEmpTypeCode();
				//nameData[1][3] = leavePolicyMIS.getEmpTypeName();
				name += " 	Employee Type ->" + leavePolicyMIS.getEmpTypeName();
				filterObject += "\n\nEmployee Type : " + leavePolicyMIS.getEmpTypeName();
			}
			//DESIGNATION
			if (!leavePolicyMIS.getDesgCode().equals("")) {
				empIdsQuery += " AND HRMS_EMP_OFFC.EMP_RANK= " + leavePolicyMIS.getDesgCode();
				name += "  Designation  ->" + leavePolicyMIS.getDesgName();

				//nameData[2][1] = leavePolicyMIS.getDesgName();
				filterObject += "\n\nDesignation : " + leavePolicyMIS.getDesgName();
			}
			empIdsQuery += " ORDER BY HRMS_EMP_OFFC.EMP_ID";
			Object empIdsObj[][] = getSqlModel().getSingleResult(empIdsQuery);
			Object insertTempObj[][] = new Object[empIdsObj.length][2];
			for (int i = 0; i < empIdsObj.length; i++) {

				insertTempObj[i][0] = empIdsObj[i][0];
				insertTempObj[i][1] = "";
			}
			//HRMS_LEAVE_ENTITLE_TEMP
			//String delTempQuery =" DELETE FROM HRMS_LEAVE_ENTITLE_TEMP ";
			String delTempQuery = " DELETE FROM HRMS_LEAVE_POLICY_TEMP ";
			getSqlModel().singleExecute(delTempQuery);
			String insertTempQuery = " INSERT INTO HRMS_LEAVE_POLICY_TEMP (EMP_ID, LEAVE_POLICY_CODE) VALUES(?,?)";
			getSqlModel().singleExecute(insertTempQuery, insertTempObj);
			String leaveAvail = "  SELECT HRMS_LEAVE_POLICY.LEAVE_CODE,NVL(LEAVE_ENTITLEMENT,0),NVL(LEAVE_MAX_BALANCE,0), LEAVE_CREDIT_INTERVAL,LEAVE_CARRY_FORWARD "
					+ " FROM HRMS_LEAVE_POLICY  "
					+ " WHERE HRMS_LEAVE_POLICY.LEAVE_POLICY_CODE = ? AND LEAVE_APPLICABLE='Y' AND LEAVE_CREDIT_INTERVAL IS NOT NULL "
					+ " ORDER BY HRMS_LEAVE_POLICY.LEAVE_CODE  ";
			/*
			 * Update hrms_leve_balamce set leave_closing_bnalance =
			 * leave_closing_bnalance+ ? where levcode = ? and empcode in (select *
			 * from hrms_emp_offc where emp in service and emptype = ? and doj < ?);
			 * 
			 * //Max update update hrms_leve_balamce set closing_balance = ? where
			 * leavecode = ? and closing_balance > ?
			 * 
			 */
			String selectQuery = " SELECT HRMS_LEAVE_POLICY_SETTING.EMP_DIVISION, HRMS_LEAVE_POLICY_SETTING.EMP_DEPT, " 
					+ " HRMS_LEAVE_POLICY_SETTING.EMP_CENTER, HRMS_LEAVE_POLICY_SETTING.EMP_RANK, " 
					+ " HRMS_LEAVE_POLICY_SETTING.EMP_TYPE, HRMS_LEAVE_POLICY_SETTING.EMP_ID, HRMS_LEAVE_POLICY_SETTING.LEAVE_POLICY_CODE, " 
					+ " HRMS_LEAVE_POLICY_SETTING.POLICY_SETTING_CODE "
					+ " FROM HRMS_LEAVE_POLICY_SETTING  " 
					+ " ORDER BY HRMS_LEAVE_POLICY_SETTING.EMP_ID DESC , "
					+ " (CASE WHEN HRMS_LEAVE_POLICY_SETTING.EMP_DIVISION IS NULL THEN 1 ELSE 0 END )+ "
					+ " (CASE WHEN HRMS_LEAVE_POLICY_SETTING.EMP_DEPT IS NULL THEN 1 ELSE 0 END ) +  "
					+ " (CASE WHEN HRMS_LEAVE_POLICY_SETTING.EMP_CENTER  IS NULL THEN 1 ELSE 0 END )+  "
					+ " (CASE WHEN HRMS_LEAVE_POLICY_SETTING.EMP_RANK IS NULL THEN 1 ELSE 0 END )+  "
					+ " (CASE WHEN HRMS_LEAVE_POLICY_SETTING.EMP_TYPE IS NULL THEN 1 ELSE 0 END) DESC ";
			Object selObj[][] = getSqlModel().getSingleResult(selectQuery);
			String query = "";
			Object[][] policyCodeObj = null;
			for (int i = 0; i < selObj.length; i++) {
				policyCodeObj = new Object[selObj.length][1];
				policyCodeObj[i][0] = selObj[i][6];

				query = "	UPDATE HRMS_LEAVE_POLICY_TEMP SET LEAVE_POLICY_CODE="
						+ policyCodeObj[i][0]
						+ " WHERE EMP_ID IN(SELECT EMP_ID FROM HRMS_EMP_OFFC  WHERE  EMP_STATUS='S' AND ";

				if (!(String.valueOf(selObj[i][5]).equals(""))
						&& !(String.valueOf(selObj[i][5]) == null)
						&& !String.valueOf(selObj[i][5]).equals("null")) {
					// if emp id not null
					query += " EMP_ID =" + String.valueOf(selObj[i][5])
							+ " AND ";
				}// end if emp id not null
				else {// emp id is null
					if (!(String.valueOf(selObj[i][4]).equals(""))
							&& !(String.valueOf(selObj[i][4]) == null)
							&& !String.valueOf(selObj[i][4]).equals("null")) {
						// if emp type not null
						query += " EMP_TYPE =" + String.valueOf(selObj[i][4])
								+ " AND ";
					}// end if
					if (!(String.valueOf(selObj[i][1]).equals(""))
							&& !(String.valueOf(selObj[i][1]) == null)
							&& !String.valueOf(selObj[i][1]).equals("null")) {
						// if dept not null
						query += " EMP_DEPT =" + String.valueOf(selObj[i][1])
								+ " AND ";
					}// end if
					if (!(String.valueOf(selObj[i][2]).equals(""))
							&& !(String.valueOf(selObj[i][2]) == null)
							&& !String.valueOf(selObj[i][2]).equals("null")) {
						// if branch not null
						query += " EMP_CENTER =" + String.valueOf(selObj[i][2])
								+ " AND ";
					}// end if
					if (!(String.valueOf(selObj[i][3]).equals(""))
							&& !(String.valueOf(selObj[i][3]) == null)
							&& !String.valueOf(selObj[i][3]).equals("null")) {
						// if desg not null
						query += " EMP_RANK =" + String.valueOf(selObj[i][3])
								+ " AND ";
					}// end if
				}// end else
				query += " EMP_DIV =" + String.valueOf(selObj[i][0]) + " )";

				getSqlModel().singleExecute(query);

			}
			String selQuery = "SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '),NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')   ,NVL(HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_NAME,'Not Defined') FROM HRMS_LEAVE_POLICY_TEMP "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LEAVE_POLICY_TEMP.EMP_ID)"
					+ " LEFT JOIN HRMS_LEAVE_POLICY_HDR ON(HRMS_LEAVE_POLICY_HDR.LEAVE_POLICY_CODE=HRMS_LEAVE_POLICY_TEMP.LEAVE_POLICY_CODE)";
					
			if(!leavePolicyMIS.getEmployeeCode().equals("")){
				selQuery+= " WHERE HRMS_LEAVE_POLICY_TEMP.EMP_ID="+leavePolicyMIS.getEmployeeCode();
			}
			
			Object[][] leavePoliceData = getSqlModel()
					.getSingleResult(selQuery);
			String delQuery = " DELETE FROM HRMS_LEAVE_POLICY_TEMP ";
			getSqlModel().singleExecute(delQuery);
			String[] str_colNames = { "Employee Id", "Employee Name", "Policy Name" };
			int[] cellWidth = { 15, 40, 30 };
			int[] cellAlign = { 0, 0, 0 };
			String[] ss = { name };
			int[] cc = { 40 };
			int[] aa = { 0 };
			Object[][] data_1 = new Object[1][1];
			data_1[0][0] = "";
			/*if (leavePolicyMIS.getReportType().equals("Xls")) {
				String[] name_xls = { "", "Leave Policy MIS Report", "", "" };
				rg.xlsTableBody(name_xls, nameData, width_1, align_1);
				rg.addText("\n", 0, 0, 0);
				rg.xlsTableBody(str_colNames, leavePoliceData, cellWidth,
						cellAlign);
			} else if (leavePolicyMIS.getReportType().equals("Pdf")) {
				rg.addFormatedText("Leave Policy MIS Report", 2, 0, 1, 0);
				rg.addText("\n", 0, 0, 0);
				//rg.addText("Leave Policy MIS Report", 2, 1, 0);
				rg.tableBodyNoBorder(nameData, width_1, align_1);
				rg.addText("\n", 0, 0, 0);
				rg.tableBody(str_colNames, leavePoliceData, cellWidth,
						cellAlign);
			} else if (leavePolicyMIS.getReportType().equals("Txt")) {
				//rg.addFormatedText(name, 2, 0, 2, 1);
				rg.addFormatedText("Leave Policy MIS Report", 2, 0, 1, 0);
				//rg.addText("\n", 0, 0, 0);
				//rg.addText("Leave Policy MIS Report", 2, 1, 0);
				rg.tableBodyNoBorder(nameData, width_1, align_1);
				//rg.addText("\n", 0, 0, 0);
				rg.tableBody(str_colNames, leavePoliceData, cellWidth,
						cellAlign);
			}*/
			
			
			//rg.xlsTableBody(name_xls, nameData, width_1, align_1);
			TableDataSet tdsObj1 = new TableDataSet();
			tdsObj1.setData(new Object[][] { { filterObject } });
			tdsObj1.setHeaderTable(false);
			tdsObj1.setCellAlignment(new int[] {0});
			tdsObj1.setCellWidth(new int[] {100});
			tdsObj1.setBlankRowsAbove(1);
			tdsObj1.setBlankRowsBelow(1);
			rg.addTableToDoc(tdsObj1);
			
			//rg.xlsTableBody(str_colNames, leavePoliceData, cellWidth, cellAlign);
			if (leavePoliceData != null && leavePoliceData.length > 0) {
				tdsObj1 = new TableDataSet();
				tdsObj1.setData(leavePoliceData);
				tdsObj1.setHeaderTable(true);
				tdsObj1.setHeader(str_colNames);
				tdsObj1.setHeaderBorderDetail(3);
				tdsObj1.setCellAlignment(cellAlign);
				tdsObj1.setCellWidth(cellWidth);
				tdsObj1.setBorder(true);
				tdsObj1.setBorderDetail(3);
				rg.addTableToDoc(tdsObj1);
			} else {
				tdsObj1 = new TableDataSet();
				tdsObj1.setData(new Object[][] { { "No records to display" } });
				tdsObj1.setHeaderTable(false);
				tdsObj1.setCellAlignment(new int[] {1});
				tdsObj1.setCellWidth(new int[] {100});
				tdsObj1.setCellColSpan(new int[] {8});
				tdsObj1.setBodyFontStyle(1);
				tdsObj1.setBlankRowsAbove(1);
				tdsObj1.setBlankRowsBelow(1);
				rg.addTableToDoc(tdsObj1);
			}
			rg.process();			
			if(path.equals("")){
				rg.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in report----------------------------"+e);
		}
	}

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

}
