package org.paradyne.model.DataMigration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.lib.ModelBase;
import org.struts.action.DataMigration.MigrateExcelData;

import sun.util.logging.resources.logging;

public class PromotionBulkUploadModel extends ModelBase {

	public String checkNull(String result) {
		if(result == null || result.equals("null") || result.equals("")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public void downLoadTemplate(HttpServletResponse response, HttpServletRequest request, String templateName) throws Exception {
		String reportType = "";
		reportType = "Xls";
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(reportType, templateName, "");

		String[] str_colNames = new String[7];
		str_colNames[0] = "Employee Token";
		str_colNames[1] = "Employee Name";
		str_colNames[2] = "Designation";
		str_colNames[3] = "Department";
		str_colNames[4] = "Branch";
		str_colNames[5] = "CTC in Lacs";
		str_colNames[6] = "Date of promotion";
		
		// str_colNames[11]="Country";

		int cellWidth[] = {15, 15, 20, 15, 15, 10,10};
		int cellAlign[] = {0, 0, 0, 0, 0, 0,0};
		Object[][] data = new Object[1][7];
		for(int i = 0; i < data[0].length; i++) {
			data[0][i] = "";
		}
		rg.tableBody(str_colNames, data, cellWidth, cellAlign);
		rg.createReport(response);

	}

	public void uploadTemplate(HttpServletResponse response, HttpServletRequest request, EmpDetailsUpload empDetails) {
		String filePath = empDetails.getDataPath() + empDetails.getUploadFileName();
		MigrateExcelData.getFile(filePath);
		
		Object[][] empToken=null;
		Object[][] empName=null;
		Object[][] design=null;
		Object[][] dept=null;
		Object[][] branch=null;
		Object[][] ctc=null;
		Object[][] promotionDate=null;
		
		HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();
	//	Object[][] EMPDATA = getSqlModel().getSingleResult("select TRIM(EMP_TOKEN),EMP_ID FROM HRMS_EMP_OFFC");
		
		Object[][] EMPDATA = getSqlModel().getSingleResult("SELECT EMP_ID,TRIM(EMP_TOKEN) FROM HRMS_EMP_OFFC");

		try{
			try{
				if(EMPDATA != null && EMPDATA.length > 0) {
					empToken = MigrateExcelData.uploadExcelData(1, EMPDATA, MigrateExcelData.MASTER_TYPE, columnInformation.get(1));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			empName=MigrateExcelData.uploadExcelData(2, null, MigrateExcelData.STRING_TYPE, columnInformation.get(2));
			design= MigrateExcelData.uploadExcelData(3, null, MigrateExcelData.STRING_TYPE, columnInformation.get(3));
			dept = MigrateExcelData.uploadExcelData(4, null, MigrateExcelData.STRING_TYPE, columnInformation.get(4));
			branch = MigrateExcelData.uploadExcelData(5, null, MigrateExcelData.STRING_TYPE, columnInformation.get(5));
			ctc = MigrateExcelData.uploadExcelData(6, null, MigrateExcelData.STRING_TYPE, columnInformation.get(6));
			promotionDate = MigrateExcelData.uploadExcelData(7, null, MigrateExcelData.DATE_TYPE, columnInformation.get(7));
			boolean res = MigrateExcelData.isFileToBeUploaded();
			Object[][] data = null;
			Object[][] deleteData =null;
			
			for(int i = 0; i < empToken.length; i++) {				
				System.out.println("TOKEN VALUES  "+empToken[i][0]);
			}
			
			if(empToken != null && empToken.length > 0) {
				data= new Object[empToken.length][6];
				deleteData= new Object[empToken.length][1];
				for(int i = 0; i < empToken.length; i++) {			
						data[i][0] = String.valueOf(empToken[i][0]).trim();
						deleteData[i][0] =checkNull(String.valueOf(empToken[i][0]).trim());
						
						if(dept!=null && dept.length >0){
							String deptQuery="SELECT DEPT_ID FROM HRMS_DEPT WHERE DEPT_NAME='"+dept[i][0]+"'";
							Object[][] deptObj= getSqlModel().getSingleResult(deptQuery);
							if(deptObj != null && deptObj.length > 0) {
								data[i][1] = checkNull(String.valueOf(deptObj[0][0]).trim());
							}
						}
												
						if(branch!=null && branch.length >0){
							String branchQuery="SELECT CENTER_ID FROM HRMS_CENTER WHERE CENTER_NAME='"+branch[i][0]+"'";
							Object[][] branchObj= getSqlModel().getSingleResult(branchQuery);
							if(branchObj != null && branchObj.length > 0) {
								data[i][2] =checkNull(String.valueOf(branchObj[0][0]).trim());
							}
						}
						
						data[i][3] = checkNull(String.valueOf(ctc[i][0])).trim();
						data[i][4] = checkNull(String.valueOf(promotionDate[i][0])).trim();
						if(design!=null && design.length >0){
							String designQuery="SELECT RANK_ID FROM HRMS_RANK WHERE RANK_NAME='"+design[i][0]+"'";
							Object[][] designObj= getSqlModel().getSingleResult(designQuery);
							if(designObj != null && designObj.length > 0) {
								data[i][5]=checkNull(String.valueOf(designObj[0][0]).trim());
							}
						}
						
					
				}//for loop close
				
			}
			
			if(res) {
						
						String query =" INSERT INTO HRMS_PROMO_HISTORY (PROMO_ID,EMP_ID,PROMO_DEPT,PROMO_BRANCH,PROMO_CTC,PROMO_FROM_DT,PROMO_POST) "
									  +" VALUES((SELECT NVL(MAX(PROMO_ID),0)+1 FROM HRMS_PROMO_HISTORY),?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?)";
						boolean result = getSqlModel().singleExecute(query, data);
						if(result) {
							empDetails.setStatus("Success");
						} else {
							empDetails.setStatus("Fail");
							empDetails.setNote(" Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. Upload the sheet again to transfer the data.");
						}
			}//res if closed
			else {
						empDetails.setStatus("Fail");
						empDetails.setNote(" Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
			}
			empDetails.setUploadName("Promotion");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}//Method closed
}