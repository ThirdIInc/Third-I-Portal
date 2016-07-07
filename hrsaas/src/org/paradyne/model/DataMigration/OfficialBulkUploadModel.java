package org.paradyne.model.DataMigration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;
import org.struts.action.DataMigration.MigrateExcelData;

public class OfficialBulkUploadModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.paradyne.model.DataMigration.OfficialBulkUploadModel.class);
	
	public void downloadOfficialTemplate(EmpDetailsUpload bean,HttpServletRequest request,HttpServletResponse response){
		
		org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator("Xls","officialDetails","A4");
		String [] colNames={"Employee Code","Title","First Name","Middle Name","Last Name","Division","Branch","Department","Designation","Shift","Employee Type","PayBill",
							"Status","Gender","Date of Birth","Grade","Date Of Joining","Date of Leaveing","Trade","Group Joining Date","Role"};
		
		int [] align={0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,0,1,0};
		int [] colwidth={10,10,15,15,15,15,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
		//rg.addText("", 1, 1, 1);
		Object tempObj[][]=new Object[1][21] ;
		for (int i = 0; i < tempObj[0].length; i++) {
			tempObj[0][i]="";
		}
		rg.tableBody(colNames, tempObj, colwidth,align);
		rg.createReport(response);
	}
	
	public void uploadTemplate(HttpServletResponse response,
			HttpServletRequest request, EmpDetailsUpload empDetails) {

		String filePath = empDetails.getDataPath()+"/"+ empDetails.getUploadFileName();
		
		//to create  object of the file 
		MigrateExcelData.getFile(filePath);
		/**
		 * Get column numbers with mandatory information
		 */
		HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();
		
		Object[][] empId=MigrateExcelData.uploadExcelData(1, null, MigrateExcelData.STRING_TYPE, columnInformation.get(1));
		
		Object titleMasterObj[][]=getSqlModel().getSingleResult("SELECT TITLE_CODE,TITLE_NAME FROM HRMS_TITLE");
		Object[][] titleObj=MigrateExcelData.uploadExcelData(2, titleMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(2));
		
		Object[][] fNameObj=MigrateExcelData.uploadExcelData(3, null, MigrateExcelData.STRING_TYPE, columnInformation.get(3));
		Object[][] mNameObj=MigrateExcelData.uploadExcelData(4, null, MigrateExcelData.STRING_TYPE, columnInformation.get(4));
		Object[][] lNameObj=MigrateExcelData.uploadExcelData(5, null, MigrateExcelData.STRING_TYPE, columnInformation.get(5));
		
		Object divMasterObj[][]=getSqlModel().getSingleResult("SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION");
		Object[][] divObj=MigrateExcelData.uploadExcelData(6, divMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(6));
		
		Object branchMasterObj[][]=getSqlModel().getSingleResult("SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER");
		Object[][] branchObj=MigrateExcelData.uploadExcelData(7, branchMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(7));

		Object deptMasterObj[][]=getSqlModel().getSingleResult("SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT");
		Object[][] deptObj=MigrateExcelData.uploadExcelData(8, deptMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(8));
		
		Object desgMasterObj[][]=getSqlModel().getSingleResult("SELECT RANK_ID,RANK_NAME FROM HRMS_RANK");
		Object[][] desgObj=MigrateExcelData.uploadExcelData(9, desgMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(9));
		
		Object shiftMasterObj[][]=getSqlModel().getSingleResult("SELECT SHIFT_ID,SHIFT_NAME FROM HRMS_SHIFT");
		Object[][] shiftObj=MigrateExcelData.uploadExcelData(10, shiftMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(10));
		
		Object eTypeMasterObj[][]=getSqlModel().getSingleResult("SELECT TYPE_ID,TYPE_NAME FROM HRMS_EMP_TYPE");
		Object[][] empTypeObj=MigrateExcelData.uploadExcelData(11, eTypeMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(11));
		
		Object payBillMasterObj[][]=getSqlModel().getSingleResult("SELECT PAYBILL_ID,PAYBILL_GROUP FROM HRMS_PAYBILL");
		Object[][] payBillObj=MigrateExcelData.uploadExcelData(12, payBillMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(12));
		
		Object [][]statusMasterObj=new Object[4][2];
		statusMasterObj[0][0]="S";
		statusMasterObj[0][1]="Service";
		
		statusMasterObj[1][0]="R";
		statusMasterObj[1][1]="Retired";
		
		statusMasterObj[2][0]="N";
		statusMasterObj[2][1]="Resigned";
		
		statusMasterObj[3][0]="E";
		statusMasterObj[3][1]="Terminated";
		Object[][] statusObj=MigrateExcelData.uploadExcelData(13, statusMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(13));
		
		Object [][]genderMasterObj=null;
		Object[][] genderObj=null;
		
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap genderMap = dmu.getGenderXml("gender");
		dmu.terminate();
		if(genderMap != null && genderMap.size() > 0){
			int l=0;
			genderMasterObj = new Object[genderMap.size()][2];
			for (Iterator k = genderMap.keySet().iterator() ; k.hasNext();) {
				genderMasterObj[l][0] = k.next();
				genderMasterObj[l][1] = genderMap.get(String.valueOf(genderMasterObj[l][0]));
				l++;
			}
		}
		
		if(genderMasterObj != null && genderMasterObj.length > 0)
		genderObj=MigrateExcelData.uploadExcelData(14, genderMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(14));
		
		Object[][] dobObj=MigrateExcelData.uploadExcelData(15, null, MigrateExcelData.DATE_TYPE, columnInformation.get(15));
		
		Object gradeMasterObj[][]=getSqlModel().getSingleResult("SELECT CADRE_ID,CADRE_NAME FROM HRMS_CADRE");
		Object[][] gradeObj=MigrateExcelData.uploadExcelData(16, gradeMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(16));
		
		Object[][] dojObj=MigrateExcelData.uploadExcelData(17, null, MigrateExcelData.DATE_TYPE, columnInformation.get(17));
		
		Object[][] dateOfLeavingObj=MigrateExcelData.uploadExcelData(18, null, MigrateExcelData.DATE_TYPE, columnInformation.get(18));
								
		Object tradeMasterObj[][]=getSqlModel().getSingleResult("SELECT TRADE_CODE, TRADE_NAME FROM HRMS_TRADE");
		Object[][] tradeObj= MigrateExcelData.uploadExcelData(19, tradeMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(19));
		
		Object[][] groupJoinDate = MigrateExcelData.uploadExcelData(20, null, MigrateExcelData.DATE_TYPE, columnInformation.get(20));

		Object[][] roleObj = MigrateExcelData.uploadExcelData(21, null, MigrateExcelData.STRING_TYPE, columnInformation.get(21));
		
		boolean res = MigrateExcelData.isFileToBeUploaded();
		
		if(res){
			Object [][]empOffcDetailsObj =new Object[empId.length][22];
			Object [][]maxEmpId=getSqlModel().getSingleResult("(SELECT NVL(MAX(EMP_ID),0)+1 FROM HRMS_EMP_OFFC)");
			int emp_id =Integer.parseInt(String.valueOf(maxEmpId[0][0]));
			
			for (int i = 0; i < empOffcDetailsObj.length; i++) {
				empOffcDetailsObj[i][0]=empId[i][0];
				empOffcDetailsObj[i][21]=String.valueOf(emp_id++);
			
				try{
					empOffcDetailsObj[i][1]=titleObj[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][1]="";
				}
				try{
					empOffcDetailsObj[i][2]=fNameObj[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][2]="";
				}
				try{
					empOffcDetailsObj[i][3]=mNameObj[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][3]="";
				}
				try{
					empOffcDetailsObj[i][4]=lNameObj[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][4]="";
				}
				try{
					empOffcDetailsObj[i][5]=divObj[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][5]="";
				}
				try{
					empOffcDetailsObj[i][6]=branchObj[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][6]="";
				}
				try{
					empOffcDetailsObj[i][7]=deptObj[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][7]="";
				}
				try{
					empOffcDetailsObj[i][8]=desgObj[i][0];	
				}catch (Exception e) {
					empOffcDetailsObj[i][8]="";
				}
				try{
					empOffcDetailsObj[i][9]=shiftObj[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][9]="";
				}
				try{
					empOffcDetailsObj[i][10]=empTypeObj[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][10]="";
				}
				try{
					empOffcDetailsObj[i][11]=payBillObj[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][11]="";
				}
				try{
					empOffcDetailsObj[i][12]=statusObj[i][0];
					logger.info("statusObj=="+String.valueOf(statusObj[i][0])+"=");
				}catch (Exception e) {
					empOffcDetailsObj[i][12]="";
				}
				try{
					empOffcDetailsObj[i][13]=genderObj[i][0];
					logger.info("gender=="+String.valueOf(genderObj[i][0])+"=");
				}catch (Exception e) {
					empOffcDetailsObj[i][13]="";
				}
				try{
					empOffcDetailsObj[i][14]=dobObj[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][14]="";
				}
				try{
					empOffcDetailsObj[i][15]=gradeObj[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][15]="";
				}
				try{
					empOffcDetailsObj[i][16]=dojObj[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][16]="";
				}
				try{
					empOffcDetailsObj[i][17]=dateOfLeavingObj[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][17]="";
				}									
				
				try{
					empOffcDetailsObj[i][18]=tradeObj[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][18]="";
				}
				
				try{
					empOffcDetailsObj[i][19]=groupJoinDate[i][0];
				}catch (Exception e) {
					empOffcDetailsObj[i][19]="";
				}
				
				try{
					empOffcDetailsObj[i][20]=roleObj[i][0];
				}catch(Exception e){
					empOffcDetailsObj[i][20]="";
				}
				
			}
						
			String offcQuery="INSERT INTO HRMS_EMP_OFFC (EMP_TOKEN,EMP_TITLE_CODE,EMP_FNAME, EMP_MNAME, EMP_LNAME,EMP_DIV,EMP_CENTER,EMP_DEPT,EMP_RANK,EMP_SHIFT," 
				+" EMP_TYPE,EMP_PAYBILL,EMP_STATUS,EMP_GENDER,EMP_DOB,EMP_CADRE,EMP_REGULAR_DATE,EMP_LEAVE_DATE,EMP_TRADE,EMP_GROUP_JOIN_DATE, EMP_ROLE,EMP_ID) VALUES" 
				+" (?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'MM/DD/YYYY'),?,TO_DATE(?,'MM/DD/YYYY'),TO_DATE(?,'MM/DD/YYYY'),?,TO_DATE(?,'MM/DD/YYYY'),?,?)";
			
			boolean result = getSqlModel().singleExecute(offcQuery, empOffcDetailsObj);
			
			if(result) {
				empDetails.setStatus("Success");
			} else {
				empDetails.setStatus("Fail");
				empDetails.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. " +
						"Upload the sheet again to transfer the data.");
			}
		}else{
			empDetails.setStatus("Fail");
			empDetails.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
		}
		
		empDetails.setUploadName("Official");
		
		
	}
}
