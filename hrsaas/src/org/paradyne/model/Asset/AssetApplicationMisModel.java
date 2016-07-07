/**
 * @author Ananthalakshmi
 * 
 */
package org.paradyne.model.Asset;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Asset.AssestApplicationMis;

import org.paradyne.lib.ModelBase;


public class AssetApplicationMisModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);

	public AssetApplicationMisModel() {
		// TODO Auto-generated constructor stub
	}

	public void getEmployeeDetails(String empId, AssestApplicationMis bean) {
		try {
			Object[] beanObj = new Object[1];
			beanObj[0] = empId;// employee id
			String query = " SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN, "
					+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
					+ "	HRMS_RANK.RANK_NAME,TO_CHAR(CENTER_ID||'-'||CENTER_NAME),HRMS_DIVISION.DIV_ID,HRMS_DIVISION.DIV_NAME "
					+ "	FROM HRMS_EMP_OFFC "
					+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
					+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
					+ "  INNER JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV "
					+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "
					+ "  WHERE HRMS_EMP_OFFC.EMP_ID = ?";
			Object[][] values = getSqlModel().getSingleResult(query, beanObj);

			if (values != null && values.length > 0) {
				bean.setEmpId(checkNull(String.valueOf(values[0][0])));// employee id
				bean.setToken(checkNull(String.valueOf(values[0][1])));// employee
				// token
				bean.setEmpName(checkNull(String.valueOf(values[0][2])));// employee
				// name
				bean.setRank(checkNull(String.valueOf(values[0][3])));// designation
				bean.setCenter(checkNull(String.valueOf(values[0][4])));// branch
				bean.setDivCode(checkNull(String.valueOf(values[0][5])));// division
				// code
				bean.setDivsion(checkNull(String.valueOf(values[0][6])));// division
				// name
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}// end of getEmployeeDetails

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

	/**
	 * THIS METHOD IS USED OFR GENERATING REPORT
	 * 
	 * @param leaveAppMis
	 * @param response
	 * @return String
	 */
	public String getReport(AssestApplicationMis assetAppMis,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String reportName = "Asset Application Mis Report";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(assetAppMis.getReportType(), reportName);
			rg.addTextBold("Asset Application MIS", 0, 1, 0);
			rg.addTextBold("\n", 0, 1, 0);

			Object[][] nameData = new Object[3][4];
			nameData[0][0] = "Division :";
			nameData[0][1] = "";// name
			nameData[0][2] = "Department :";
			nameData[0][3] = "";

			nameData[1][0] = "Branch :";
			nameData[1][1] = "";
			nameData[1][2] = "Designation :";
			nameData[1][3] = "";

			

			nameData[2][0] = "Status :";
			nameData[2][1] = "";
			nameData[2][2] = "";
			nameData[2][3] = "";

			int[] width_1 = { 15, 25, 15, 25 };
			int[] align_1 = { 0, 0, 0, 0 };

			if (!(assetAppMis.getDivsion().equals(""))) {
				nameData[0][1] = assetAppMis.getDivsion();
			}// end of if
			if (!(assetAppMis.getDeptName().equals(""))) {
				nameData[0][3] = assetAppMis.getDeptName();
			}// end of if
			if (!(assetAppMis.getCenterNo().equals(""))) {
				nameData[1][1] = assetAppMis.getCenterName();
			}// end of if
			if (!(assetAppMis.getDesgName().equals(""))) {
				nameData[1][3] = assetAppMis.getDesgName();
			}// end of if
		
			if (!(assetAppMis.getStatus().equals(""))) {
				
				if (assetAppMis.getStatus().equals("P")) {
					nameData[2][1] = "Pending";
				}// end of if
				
				if (assetAppMis.getStatus().equals("A")) {
					nameData[2][1] = "Approved";
				}// end of if
				if (assetAppMis.getStatus().equals("R")) {
					nameData[2][1] = "Rejected";
				}// end of if
				if (assetAppMis.getStatus().equals("S")) {
					nameData[2][1] = "Assigned";
				}// end of if
			}// end of if

			String sql = " SELECT distinct ASSET_APPL_NO AS APPLICATION_NO,HRMS_EMP_OFFC.EMP_TOKEN," +
						 " NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') AS EMP_NAME " + 
						 " ,TO_CHAR(ASSET_APPL_DATE,'dd-MM-YYYY')" + 
						 " ,NVL(DECODE(ASSET_STATUS,'P','PENDING','R','REJECTED','A','APPROVED','S','ASSIGNED'),'')" +
						 " ,NVL(E1.EMP_FNAME,' ')||' '||NVL(E1.EMP_MNAME,' ')||' '||NVL(E1.EMP_LNAME,' ') as approver_name " +
						 " FROM HRMS_ASSET_APPLICATION " + 
						 " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_ASSET_APPLICATION.ASSET_EMP_ID) " +
						 " lEFT JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID=HRMS_ASSET_APPLICATION.ASSET_APPL_APPROVER) " +
						 " WHERE HRMS_EMP_OFFC.EMP_DIV=" + assetAppMis.getDivCode();
			if (assetAppMis.getEmpId() != null
					&& assetAppMis.getEmpId().length() > 0) {
				sql += " AND ASSET_EMP_ID=" + assetAppMis.getEmpId()
						+ " ";
			}// end of if

			if (assetAppMis.getCenterNo() != null
					&& assetAppMis.getCenterNo().length() > 0) {
				sql += " AND HRMS_EMP_OFFC.EMP_CENTER="
						+ assetAppMis.getCenterNo() + " ";
			}// end of if

			if (assetAppMis.getDeptCode() != null
					&& assetAppMis.getDeptCode().length() > 0) {
				sql += " AND HRMS_EMP_OFFC.EMP_DEPT="
						+ assetAppMis.getDeptCode();
			}// end of if
			if (assetAppMis.getDesgCode() != null
					&& assetAppMis.getDesgCode().length() > 0) {
				sql += " AND HRMS_EMP_OFFC.EMP_RANK="
						+ assetAppMis.getDesgCode();
			}// end of if
			if (assetAppMis.getStatus() != null
					&& assetAppMis.getStatus().length() > 0) {
				sql += " AND HRMS_ASSET_APPLICATION .ASSET_STATUS='"
						+ assetAppMis.getStatus() + "'";
			}// end of if

			if (assetAppMis.getEmpStatus() != null
					&& !assetAppMis.getEmpStatus().equals("")) {
				sql += " AND HRMS_EMP_OFFC.EMP_STATUS='"
						+ assetAppMis.getEmpStatus() + "'";
			}// end of if

		

			Object leaveData[][] = getSqlModel().getSingleResult(sql);
			Object[][] finalLeaveData = null;
			String[] colNames = { "Application No", "Employee Id", "Employee Name",	"Application Date", "Status", "Approver Name" };
			int[] cellWidth = { 10, 15, 20, 15, 15, 15 };
			int[] alignment = { 0, 0, 0, 0, 1, 0};
			if (leaveData != null && leaveData.length > 0) {
				finalLeaveData = new Object[leaveData.length][10];
				for (int i = 0; i < leaveData.length; i++) {
					finalLeaveData[i][0] = leaveData[i][0];
					finalLeaveData[i][1] = leaveData[i][1];
					finalLeaveData[i][2] = leaveData[i][2];
					finalLeaveData[i][3] = leaveData[i][3];
					finalLeaveData[i][4] = leaveData[i][4];
					finalLeaveData[i][5] = leaveData[i][5];
				}

			}

			if (finalLeaveData != null && finalLeaveData.length > 0) {
				if (assetAppMis.getReportType().equals("Xls")) {
					String[] name_xls = { "", "Asset Application MIS Report","", "" };
					rg.xlsTableBody(name_xls, nameData, width_1, align_1);
					rg.addText("\n", 0, 0, 0);
					rg.xlsTableBody(colNames, finalLeaveData, cellWidth,
							alignment);
				} else if (assetAppMis.getReportType().equals("Pdf")) {
					rg.addText("\n", 0, 0, 0);
					rg.tableBodyNoBorder(nameData, width_1, align_1);
					rg.addText("\n", 0, 0, 0);
					rg.tableBody(colNames, finalLeaveData, cellWidth,alignment);
				} else if (assetAppMis.getReportType().equals("Txt")) {
					rg.tableBodyNoBorder(nameData, width_1, align_1);
					rg.tableBody(colNames, finalLeaveData, cellWidth,alignment);
				}
			}// end of if
			else {
				rg.addTextBold("There is no data to display.", 0, 1, 0);
			}// end of else

			rg.createReport(response);

		}// end of try
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception------------------" + e);
			e.printStackTrace();

		}// end of catch
		return "true";
	}

}// end of class
