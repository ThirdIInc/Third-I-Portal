/**
 * @author Anantha Lakshmi
 * VoucherUploadModel class to write the business logic to view voucher upload  templates and generate the voucher data template
 * regarding the voucher upload
 */

package org.paradyne.model.voucher;


import java.util.HashMap;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.voucher.VoucherUpload;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.action.DataMigration.MigrateExcelData;

public class VoucherUploadModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(VoucherUploadModel.class);
	public void uploadVoucherMasterTemplate(HttpServletResponse response,HttpServletRequest request, VoucherUpload voucherUpload) {
		// TODO Auto-generated method stub
		String filePath=voucherUpload.getDataPath()+""+voucherUpload.getUploadFileName();
		voucherUpload.setUploadName("VoucherMaster");
		//to create  object of the file 
		MigrateExcelData.getFile(filePath);
		
		/**
		 * Get column numbers with mandatory information
		 */
		HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();
		
		Object[][] EmpIdMaster = getSqlModel().getSingleResult(" SELECT EMP_ID,TRIM(EMP_TOKEN) FROM HRMS_EMP_OFFC");
		Object[][] voucherHeaderMaster = getSqlModel().getSingleResult(" SELECT VCH_CODE,VCH_NAME FROM HRMS_VCH_HD ");

		Object[][] empIdObj=null;
		Object[][] empNameObj=null;
		Object[][] vouhcerHeaderObj=null;
		Object[][] vouherAmtObj=null;
		Object[][] vouherDtObj=null;
		
		if(EmpIdMaster != null && EmpIdMaster.length > 0) {
			empIdObj=MigrateExcelData.uploadExcelData(1, EmpIdMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(1));
		}
		
		empNameObj= MigrateExcelData.uploadExcelData(2, null, MigrateExcelData.STRING_TYPE, columnInformation.get(2));
		
		if(voucherHeaderMaster != null && voucherHeaderMaster.length > 0) {
			vouhcerHeaderObj=MigrateExcelData.uploadExcelData(3, voucherHeaderMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(3));
		}
		
		vouherAmtObj  = MigrateExcelData.uploadExcelData(4, null, MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(4));
		
		vouherDtObj = MigrateExcelData.uploadExcelData(5, null, MigrateExcelData.DATE_TYPE, columnInformation.get(5));
		
		boolean res = MigrateExcelData.isFileToBeUploaded();
		
		Object [][]insertObj=new Object[empIdObj.length][4];
		for (int i = 0; i < empIdObj.length; i++) {
			insertObj[i][0]=empIdObj[i][0];
			insertObj[i][1]=vouhcerHeaderObj[i][0];
			insertObj[i][2]=vouherDtObj[i][0];
			insertObj[i][3]=vouherAmtObj[i][0];
			
		} // end of for loop
		if(res) {

			String voucherInsertQuery ="INSERT INTO HRMS_VOUCHER_UPLOAD (EMP_ID,VOUCHER_HEAD,VOUCHER_DATE,VOUCHER_AMOUNT,VOUCHER_UPLOAD_DATE )" 
				+" VALUES ( ?,?,TO_DATE(?,'MM-DD-YYYY'),?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'))";
			//System.out.println("assetHdrInsertQuery--------"+voucherInsertQuery);
			boolean result = getSqlModel().singleExecute(voucherInsertQuery, insertObj);
			if(result) {
				voucherUpload.setStatus("Success");
			} else {
				voucherUpload.setStatus("Fail");
				voucherUpload.setNote(" Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. Upload the sheet again to transfer the data.");
			}
		} else {
			voucherUpload.setStatus("Fail");
			voucherUpload.setNote(" Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
		}
		
	} //end uploadVoucherMasterTemplate
	
	public void vouherUploadStatementData(VoucherUpload voucherUpload,HttpServletResponse response,String hidDivId){
			String reportName = "Voucher Statement";
			//String type="Xls";
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator("Xls",reportName);
	        
			rg.addTextBold("Voucher Statement For "+voucherUpload.getDivisionName(), 0, 1, 0, 12);
			String query="SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME|| ' ' ||EMP_LNAME," +
					" NVL(BANK_NAME,' '),NVL(SAL_REIMBMENT,' '),VCH_NAME,VOUCHER_AMOUNT from HRMS_VOUCHER_UPLOAD"
			+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_VOUCHER_UPLOAD.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
			+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)"
			+" LEFT JOIN HRMS_BANK ON(HRMS_SALARY_MISC.SAL_REIMBANK=HRMS_BANK.BANK_MICR_CODE)"
			+" LEFT JOIN HRMS_VCH_HD ON(HRMS_VCH_HD.VCH_CODE = HRMS_VOUCHER_UPLOAD.VOUCHER_HEAD)"
			+" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)"
			+" WHERE 1=1 ";      
			
			if (!(voucherUpload.getFrmDate().equals(""))&& !(voucherUpload.getFrmDate() == null)&& !voucherUpload.getFrmDate().equals("null")) {
					String da="";
					if(!voucherUpload.getToDate().equals("")){
						da+=voucherUpload.getToDate();
						query+= " AND VOUCHER_DATE BETWEEN TO_DATE('" + voucherUpload.getFrmDate()
						+ "','DD-MM-YYYY')AND TO_DATE('" + da + "','DD-MM-YYYY')";
					}  // end of if
					else 
					{
						da+=voucherUpload.getToDay();
						query+= " AND VOUCHER_DATE >= TO_DATE('" + voucherUpload.getFrmDate()
						+ "','DD-MM-YYYY')";
					}  // end of else 
			}else 
			if (!(voucherUpload.getToDate().equals(""))	&& !(voucherUpload.getToDate() == null)&& !voucherUpload.getToDate().equals("null")) {
					String da="";
					
						da+=voucherUpload.getToDate();
						query+= " AND VOUCHER_DATE <= TO_DATE('" + voucherUpload.getToDate()
						+ "','DD-MM-YYYY')";
					 
			}
			if (!(voucherUpload.getDivisionId().equals(""))
					&& !(voucherUpload.getDivisionId() == null)
					&& !voucherUpload.getDivisionId().equals("null")) {
					query +=" AND DIV_ID='"+hidDivId+"'";
			}
			
			Object voucherUploadData[][]=getSqlModel().getSingleResult(query);
			Object finalData[][]=null;
			int[] attCellWidth = { 25,45,45,45,30,30,30};
			int []attAlign={1,1,1,1,1,1,1};
			String []attCol={"Employee Id","Employee Name","Reimbursement Bank","Reimbursement AccNo","Voucher Name","Voucher Amount","Account,Credit,Amount,Narration"};
			if(voucherUploadData.length>0){
				finalData=new Object[voucherUploadData.length][voucherUploadData[0].length+1];							
				double total=0.0;				
				for(int i=0;i<finalData.length;i++){
					finalData[i][0]=voucherUploadData[i][0];
					finalData[i][1]=voucherUploadData[i][1];
					finalData[i][2]=voucherUploadData[i][2];
					finalData[i][3]=voucherUploadData[i][3];
					finalData[i][4]=voucherUploadData[i][4];
					finalData[i][5]=voucherUploadData[i][5];
					if(voucherUploadData[i][3].equals("")|| voucherUploadData[i][3].equals(" "))
						finalData[i][6]="C,"+voucherUploadData[i][5]+","+voucherUploadData[i][1];
					else
						finalData[i][6]=voucherUploadData[i][3]+",C,"+voucherUploadData[i][5]+","+voucherUploadData[i][1];
					//logger.info("Fi Data Is +++++++++++++"+String.valueOf(finalData[i][0]));
					try {
						total += Double.parseDouble(String
								.valueOf(finalData[i][5]));
					} catch (Exception e) {
						// TODO: handle exception
					}	
					
				}
				/*finalData[finalData.length-1][0]="";
				finalData[finalData.length-1][1]="";
				finalData[finalData.length-1][2]="";
				finalData[finalData.length-1][3]="";
				finalData[finalData.length-1][4]="";
				finalData[finalData.length-1][5]=""; //+total;
*/			}else{
				finalData=voucherUploadData;
			}
			rg.setFName("Voucher Statement");
			rg.tableBody(attCol, finalData, attCellWidth,attAlign);
			if(finalData==null ||finalData.length==0){
				rg.addText("There is No Record To Display", 0, 1, 0);
			}else{
				rg.addFormatedText("\n",0, 0, 1, 0);
			}
			rg.createReport(response);	
		
	}
	public boolean deleteVouherUploadData(VoucherUpload voucherUpload,HttpServletResponse response,String hidDivId){
			boolean result=false;
			String query="DELETE FROM HRMS_VOUCHER_UPLOAD"
			+" WHERE EMP_ID IN (SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_DIV="+voucherUpload.getDivisionId()+") ";      
			
			if (!(voucherUpload.getFrmDate().equals(""))&& !(voucherUpload.getFrmDate() == null)&& !voucherUpload.getFrmDate().equals("null")) {
					String da="";
					if(!voucherUpload.getToDate().equals("")){
						da+=voucherUpload.getToDate();
						query+= " AND VOUCHER_DATE BETWEEN TO_DATE('" + voucherUpload.getFrmDate()
						+ "','DD-MM-YYYY') AND TO_DATE('" + da + "','DD-MM-YYYY')";
					}  // end of if
					else 
					{
						da+=voucherUpload.getToDay();
						query+= " AND VOUCHER_DATE >= TO_DATE('" + voucherUpload.getFrmDate()
						+ "','DD-MM-YYYY'))";
					}  // end of else 
			}else if (!(voucherUpload.getToDate().equals(""))	&& !(voucherUpload.getToDate() == null)&& !voucherUpload.getToDate().equals("null")) {
					String da="";
					da+=voucherUpload.getToDate();
					query+= " AND VOUCHER_DATE <= TO_DATE('" + voucherUpload.getToDate()+ "','DD-MM-YYYY'))";
			}
			
			result = getSqlModel().singleExecute(query);
			return result;
	
	}
	private String convertEmpListToString(Object[][] empList) {
		String empId = "";
		try {
			for (int i = 0; i < empList.length; i++) {
				empId += String.valueOf(empList[i][0]) + ",";// this ledger
			}
			empId = empId.substring(0, empId.length() - 1);
		} catch (Exception e) {}
		return empId;
	} 
}