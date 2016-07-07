package org.paradyne.model.admin.srd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.srd.DisciplinaryMisReport;
import org.paradyne.bean.admin.srd.FamilyMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.BaseColor;

public class DisciplinaryMisReportModel extends ModelBase{
	
	public void getDisMISReport(DisciplinaryMisReport dmr, HttpServletRequest request, HttpServletResponse response, String reportPath)
	{
		org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
		String type= dmr.getReport();
		rds.setReportType(type);
		String fileName = "DisciplinaryMISReportDetails"+ Utility.getRandomNumber(1000);
		rds.setFileName(fileName);
		rds.setReportName("Disciplinary MIS Report");
		rds.setTotalColumns(13);
		rds.setShowPageNo(true);
		rds.setPageOrientation("landscape");
		rds.setUserEmpId(dmr.getUserEmpId());

		//Report generator starts here
		org.paradyne.lib.ireportV2.ReportGenerator rg= null;
		
		//Attachment Path Definition
		if(reportPath.equals(""))
		{
			rg =  new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);		
		}
		else
		{
			rg= new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath, session, context, request);
			request.setAttribute("reportPath", reportPath+fileName+"."+type);			
			request.setAttribute("action","/srd/DisciplinaryMisReport_input.action");
			request.setAttribute("fileName",fileName+"."+type );
			//Initial Page Action
		}
		
		/*Setting Filter Details */
		
		String filter ="";
		
		if(!dmr.getDivision().equals("")){
			filter +="\nDivision: "+dmr.getDivision();
		}
		
		if(!dmr.getDeptName().equals("")){
			filter+="\n\nDepartment: "+dmr.getDeptName();
		}
		
		if(!dmr.getCenterName().equals("")){
			filter+="\n\nBranch: "+dmr.getCenterName();
		}
		
		if(!dmr.getDesgName().equals("")){
			filter+="\n\nDesignation: " +dmr.getDesgName();
		}
		
		if(!dmr.getEmpName().equals("")){
			filter+="\n\nEmployee Name: " +dmr.getEmpName();
		}
		
		TableDataSet filterData = new TableDataSet();
		filterData.setData(new Object[][]{{filter}});
		filterData.setCellAlignment(new int[]{0});
		filterData.setCellWidth(new int[]{100});
		filterData.setCellColSpan(new int[]{13});
		filterData.setBodyFontStyle(1);
		filterData.setCellNoWrap(new boolean[]{false});
		rg.addTableToDoc(filterData);
		
		String filterClause="";
		
		if (!dmr.getDivision().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_DIV IN("
					+ dmr.getDivCode()+")";
			System.out.println("+dmr.getDivCode() ="
					+ dmr.getDivCode());
		}
		
		if (!dmr.getDeptName().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_DEPT IN("
					+ dmr.getDeptCode()+")";
			System.out.println("+dmr.getDeptId() =" + dmr.getDeptCode());
		}
		
		if (!dmr.getCenterName().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_CENTER IN("
					+ dmr.getCenterId()+")";
			System.out.println("+dmr.getBranchCode() ="
					+ dmr.getCenterId());
		}
		
		if (!dmr.getDesgName().equals("")) {
			filterClause += " AND HRMS_EMP_OFFC.EMP_RANK IN("
					+ dmr.getDesgCode()+")";
			System.out.println("+dmr.getDesgCode() ="
					+ dmr.getDesgCode());
		}
		
		if (!dmr.getEmpName().equals("")) {
			System.out.println(dmr.getEmpid());
			filterClause += " AND HRMS_EMP_OFFC.EMP_ID IN(" + dmr.getEmpid()+")";
			System.out.println("+dmr.getEmpCode() =" + dmr.getEmpid());
		}
		
		if (!(dmr.getStatus().equals("-1")))
		 {
			filterClause += " AND HRMS_EMP_OFFC.EMP_STATUS='"+ dmr.getStatus() + "'";
		 }
	
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME,"
						+" HRMS_DIVISION.DIV_NAME, HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_NAME,HRMS_DEPT.DEPT_NAME, HRMS_PUNISH.PUNISH_AUTH, NVL(TO_CHAR(HRMS_PUNISH.PUNISH_WEF,'DD-MM-YYYY'),' '),"
						+" TO_CHAR(HRMS_PUNISH.PUNISH_TODATE,'DD-MM-YYYY'), HRMS_PUNISH.PUNISH_PERIOD, HRMS_PUNISH.OFFENCE_DESCRIPTION, DECODE(HRMS_PUNISH.PUNISH_SUSP_STATUS,'A','Active','I','In Active'),"
						+" DECODE(HRMS_EMP_OFFC.EMP_STATUS,'S','Service','R','Retire','N','Resigned','E','Terminated')"
						+" FROM HRMS_PUNISH INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PUNISH.EMP_ID)"
						+" LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV"
						+" LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK"
						+" LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER"
						+" LEFT JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT WHERE 1<2 ";
		
		query += filterClause;
		query += "ORDER BY HRMS_EMP_OFFC.EMP_ID";
		
		//Defining table Structure and Data
		org.paradyne.lib.ireportV2.TableDataSet tdstable = new org.paradyne.lib.ireportV2.TableDataSet();
		Object [][] queryData = getSqlModel().getSingleResult(query);
		
		if(queryData == null || queryData.length == 0){
	           
			TableDataSet noData = new TableDataSet();
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";
			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			noData.setBorder(false);
			rg.addTableToDoc(noData);
	   }
		else{
			System.out.println("In getDisciplinaryMisReport()QeryData Length: "+queryData.length);
			tdstable.setBlankRowsAbove(1);
			tdstable.setHeader(new String[]{"Emp ID"," Name", "Division", "Designation"," Branch","Department"," Authority", "EffectiveFromDate ", "EffectiveToDate"," Period","Description", " Punishment Status","Status"});
			tdstable.setHeaderTable(true);
			tdstable.setHeaderBorderDetail(3);
			//tdstable.setHeaderBorderColor(new BaseColor(255,0,0));
			tdstable.setCellAlignment(new int[]{0,0,0,0,0,0,0,1,1,2,0,0,0});
			tdstable.setCellWidth(new int[] {6,7,7,7,7,7,7,9,7,7,15,8,6});
			tdstable.setData(queryData);
			tdstable.setBorderDetail(3);
			//tdstable.setBorder(true);
			//tdstable.setBlankRowsBelow(1);
			rg.addTableToDoc(tdstable);
		}
		int totalEmployee = queryData.length;
		TableDataSet totalEmp = new TableDataSet();
		totalEmp.setData(new Object[][] { { "Total Employees : "
				+ totalEmployee } });
		totalEmp.setCellAlignment(new int[] { 0 });
		totalEmp.setCellWidth(new int[] { 100 });
		totalEmp.setBorderDetail(0);
		totalEmp.setBodyBGColor(new BaseColor(200, 200, 200));
		totalEmp.setBodyFontStyle(1);
		totalEmp.setBlankRowsAbove(1);
		rg.addTableToDoc(totalEmp);
		rg.process();
		
		if(reportPath.equals(""))	
		{
			rg.createReport(response);
		}
		else
		{
			rg.saveReport(response);
		}
	}
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull
	
	private String convertEmpListToString(Object[][] empList) {
		String empId = "";
		try {
			for (int i = 0; i < empList.length; i++) {
				empId += String.valueOf(empList[i][0]) + ",";// this ledger
				// code contains
				// the code for Apr To Dec
			} // end of loop
			empId = empId.substring(0, empId.length() - 1);
		} catch (Exception e) {
		} // end of catch
		// all the ledgerCodes for salary process from April to December are
		// substring together.
		return empId;
	} // end of getEmpList method

}
