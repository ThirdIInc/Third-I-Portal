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

public class IncrementBulkUploadModel extends ModelBase {

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

		String[] str_colNames = new String[6];
		str_colNames[0] = "Employee Token";
		str_colNames[1] = "Employee Name";
		str_colNames[2] = "Increment Type";
		str_colNames[3] = "Old CTC";
		str_colNames[4] = "New CTC";
		str_colNames[5] = "Date of Increment ";
		
		// str_colNames[11]="Country";

		int cellWidth[] = {15, 15, 20, 15, 15, 20};
		int cellAlign[] = {0, 0, 0, 0, 0, 0};
		Object[][] data = new Object[1][6];
		for(int i = 0; i < data[0].length; i++) {
			data[0][i] = "";
		}
		rg.tableBody(str_colNames, data, cellWidth, cellAlign);
		rg.createReport(response);

	}

	public void uploadTemplate(HttpServletResponse response, HttpServletRequest request, EmpDetailsUpload empDetails) {
		String filePath = empDetails.getDataPath() + empDetails.getUploadFileName();
		MigrateExcelData.getFile(filePath);
		
		Object[][] empId=null;
		Object[][] incrementType=null;
		HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();
		Object[][] EMPDATA = getSqlModel().getSingleResult("SELECT EMP_ID,TRIM(EMP_TOKEN) FROM HRMS_EMP_OFFC");
		Object[][] incrType = getSqlModel().getSingleResult("SELECT MOD_NAME FROM HRMS_DATA_MODIFICATION WHERE MOD_TYPE ='incrementType'");
		try{
			if(EMPDATA != null && EMPDATA.length > 0) {
				 empId = MigrateExcelData.uploadExcelData(1, EMPDATA, MigrateExcelData.MASTER_TYPE, columnInformation.get(1));
			}
			if(EMPDATA != null && EMPDATA.length > 0) {
				Object[][] empName = MigrateExcelData.uploadExcelData(2, EMPDATA, MigrateExcelData.STRING_TYPE, columnInformation.get(2));
			}
			if(incrType != null && incrType.length > 0) {
				incrementType= MigrateExcelData.uploadExcelData(3, incrType, MigrateExcelData.STRING_TYPE, columnInformation.get(3));
			}
			Object[][] oldCTC = MigrateExcelData.uploadExcelData(4, null, MigrateExcelData.STRING_TYPE, columnInformation.get(4));
			Object[][] newCTC = MigrateExcelData.uploadExcelData(5, null, MigrateExcelData.STRING_TYPE, columnInformation.get(5));
			Object[][] dateOfIncr = MigrateExcelData.uploadExcelData(6, null, MigrateExcelData.DATE_TYPE, columnInformation.get(6));
			
			boolean res = MigrateExcelData.isFileToBeUploaded();
			
			Object[][] data =null;
			Object[][] deleteData =null;
			if(empId != null && empId.length > 0) {
				data= new Object[empId.length][5];
				deleteData= new Object[empId.length][2];
				for(int i = 0; i < empId.length; i++) {
						data[i][0] = String.valueOf(empId[i][0]).trim();
						deleteData[i][0] =checkNull(String.valueOf(empId[i][0]).trim());
					   
						if(incrementType!=null && incrementType.length >0){
							String incrModValueQuery="SELECT MOD_VALUE FROM HRMS_DATA_MODIFICATION WHERE MOD_NAME='"+incrementType[i][0]+"'";
							Object[][] modValueObj= getSqlModel().getSingleResult(incrModValueQuery);
							if(modValueObj != null && modValueObj.length > 0) {
								data[i][1] = String.valueOf(modValueObj[0][0]).trim();
								deleteData[i][1] = String.valueOf(modValueObj[0][0]).trim();
							}
						}
											
						data[i][2] = String.valueOf(oldCTC[i][0]).trim();
						data[i][3] = checkNull(String.valueOf(newCTC[i][0])).trim();
						data[i][4] = checkNull(String.valueOf(dateOfIncr[i][0])).trim();
					
				}
			}
				if(res) {
						String query = " INSERT INTO HRMS_EMP_INCR (INCR_CODE,EMP_ID,INCR_PAY_TYPE,INCR_OLD_CTC,INCR_NEW_CTC,INCR_DATE)" 
								      +" VALUES((SELECT NVL(MAX(INCR_CODE),0)+1 FROM HRMS_EMP_INCR),?,?,?,?,TO_DATE(?,'MM-DD-YYYY'))";
						boolean result = getSqlModel().singleExecute(query, data);
						System.out.println("=========== RESULT =====  " + result);
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
				empDetails.setUploadName("Increment");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}//Method closed
}