package org.paradyne.model.DataMigration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.DataMigration.OfficalPartialUpload;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.lib.ModelBase;
import org.struts.action.DataMigration.MigrateExcelData;

/**
 * 
 * @author aa0491 Vishwambhar Deshpande
 *
 */
public class OfficialPartialUploadModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OfficialPartialUploadModel.class);
	
	private final int EMPLOYEE_COUNT = 100;
	
	public boolean downloadTemplate(OfficalPartialUpload bean,
			HttpServletRequest request, HttpServletResponse response,
			String poolName, String dataPath, String application, String rangeValue)

	{
		try {
 
			Object [][] empObj = getEmployee(bean);

			if (empObj != null && empObj.length > 0) {
				int count = 2;
				if (bean.getFirstName().equals("true"))
					count++;
				if (bean.getMiddleName().equals("true"))
					count++;
				if (bean.getLastName().equals("true"))
					count++;
				if (bean.getDivision().equals("true"))
					count++;
				if (bean.getBranch().equals("true"))
					count++;
				if (bean.getDepartment().equals("true"))
					count++;
				if (bean.getDesignation().equals("true"))
					count++;
				if (bean.getEmployeeTitle().equals("true"))
					count++;
				if (bean.getEmployeeType().equals("true"))
					count++;
				if (bean.getReportingTo().equals("true"))
					count++;
				if (bean.getShift().equals("true"))
					count++;
				if (bean.getPaybill().equals("true"))
					count++;
				if (bean.getGender().equals("true"))
					count++;
				if (bean.getBirthDate().equals("true"))
					count++;
				if (bean.getGrade().equals("true"))
					count++;
				if (bean.getJoiningDate().equals("true"))
					count++;
				if (bean.getLeaving().equals("true"))
					count++;
				if (bean.getStatus().equals("true"))
					count++;
				if (bean.getGroupJoinDateCheck().equals("true"))
					count++;
				if(bean.getDateofconfirm().equals("true"))
					count++;
				if(bean.getReportingto().equals("true"))
					count++;
				if(bean.getTrade().equals("true"))
					count++;
				if(bean.getRole().equals("true"))
					count++;
					
				int recordNo = 0;
				int startRecord = 0, recordsInCurrentPage = 0;
				int totalRecords = empObj.length;

				if(!rangeValue.equals("")) {
					startRecord = Integer.parseInt(rangeValue) * EMPLOYEE_COUNT;
					recordsInCurrentPage = startRecord + EMPLOYEE_COUNT;
				}
				
				if (recordsInCurrentPage >= totalRecords) {
					recordsInCurrentPage = totalRecords;
				}
				recordNo = startRecord;
				int rowsCount = 0;
				int cnt = 0;
				rowsCount = (recordsInCurrentPage - recordNo);
				Object[][] downloadData = new Object[rowsCount][count];
				for (; recordNo < recordsInCurrentPage; recordNo++) {
					
					downloadData[cnt][0] = String.valueOf(empObj[recordNo][0]); // EMP_TOKEN
					downloadData[cnt][1] = String.valueOf(empObj[recordNo][1]); // EMP_NAME
					cnt++;
				}
				application += "_" + (startRecord + 1) + " to " + recordsInCurrentPage;
				
				System.out.println("application    "+application);
				
				String[] headerNames = new String[count];
				headerNames[0] = "empId";
				headerNames[1] = "empName";
				int headerCount = 2;
				if (bean.getFirstName().equals("true")) {
					headerNames[headerCount] = "firstName";
					headerCount++;
				}
				if (bean.getMiddleName().equals("true")) {
					headerNames[headerCount] = "middleName";
					headerCount++;
				}
				if (bean.getLastName().equals("true")) {
					headerNames[headerCount] = "lastName";
					headerCount++;
				}
				if (bean.getDivision().equals("true")) {
					headerNames[headerCount] = "division";
					headerCount++;
				}
				if (bean.getBranch().equals("true")) {
					headerNames[headerCount] = "branch";
					headerCount++;
				}
				if (bean.getDepartment().equals("true")) {
					headerNames[headerCount] = "department";
					headerCount++;
				}
				if (bean.getDesignation().equals("true")) {
					headerNames[headerCount] = "designation";
					headerCount++;
				}
				if (bean.getEmployeeTitle().equals("true")) {
					headerNames[headerCount] = "employeeTitle";
					headerCount++;
				}
				if (bean.getEmployeeType().equals("true")) {
					headerNames[headerCount] = "employeetype";
					headerCount++;
				}
				if (bean.getReportingTo().equals("true")) {
					headerNames[headerCount] = "reportingto";
					headerCount++;
				}
				if (bean.getShift().equals("true")) {
					headerNames[headerCount] = "shift";
					headerCount++;
				}
				if (bean.getPaybill().equals("true")) {
					headerNames[headerCount] = "paybill";
					headerCount++;
				}
				if (bean.getGender().equals("true")) {
					headerNames[headerCount] = "gender";
					headerCount++;
				}
				if (bean.getBirthDate().equals("true")) {
					headerNames[headerCount] = "birthDate";
					headerCount++;
				}
				if (bean.getGrade().equals("true")) {
					headerNames[headerCount] = "grade";
					headerCount++;
				}
				if (bean.getJoiningDate().equals("true")) {
					headerNames[headerCount] = "joiningDate";
					headerCount++;
				}
				if (bean.getLeaving().equals("true")) {
					headerNames[headerCount] = "leaving";
					headerCount++;
				}
				if (bean.getStatus().equals("true")) {
					headerNames[headerCount] = "status";
					headerCount++;
				}
				
				if (bean.getGroupJoinDateCheck().equals("true")) {
					headerNames[headerCount] = "groupJoinDateCheck";
					headerCount++;
				}
				if (bean.getDateofconfirm().equals("true")) {
					headerNames[headerCount] = "dateofconfirm";
					headerCount++;
				}
				if (bean.getReportingto().equals("true")) {
					headerNames[headerCount] = "reportingto";
					headerCount++;
				}

				if(bean.getTrade().equals("true")){
					headerNames[headerCount] ="trade";
					headerCount++;
				}
				
				if(bean.getRole().equals("true")){
					headerNames[headerCount]="role";
					headerCount++;
				}
				
				logger.info("count ----------" + count);
				MigrateExcelData.downloadTemplateWithData(request, response,
						dataPath, headerNames, downloadData, application, poolName);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
			return true ;
	}

 

	public void uploadOfficialDataTemplate(String path,
			OfficalPartialUpload bean) {
		
		try {
			MigrateExcelData.getFile(path);
			Object[][] columnNames = MigrateExcelData.getColumnInfo();
			bean.setFileUploaded(true);
			
			if(columnNames != null && columnNames.length > 0)
			{
				
				Object[][] empIdObj = null;
				Object[][] firstNameObj = null;
				Object[][] middleNameObj = null;
				Object[][] lastNameObj = null;
				Object[][] divisionObj = null;
				Object[][] branchObj = null;
				Object[][] departmentObj = null;
				Object[][] designationObj= null;
				Object[][] employeeTitleObj= null;
				Object[][] employeeTypeObj= null;
				//Object[][] reportingToObj=null;
				Object[][] shiftObj= null;
				Object[][] paybillObj=null;
				Object[][] genderObj= null;
				Object[][] birthDateObj=null;
				Object[][] gradeObj= null;
				Object[][] joiningDateObj=null;
				Object[][] leavingObj= null;
				Object[][] statusObj=null;
				
				Object[][] groupJoinDateObj=null;
				Object[][] dateofconfirm=null;
				Object[][] reportingto=null;
				Object[][] tradeObj=null;
				Object[][] roleObj=null;
				
				
		 	
				Object[][] divMasterObj = getSqlModel().getSingleResult(" SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ORDER BY DIV_ID ");
				
				Object[][] branchMasterObj = getSqlModel().getSingleResult(" SELECT CENTER_ID,CENTER_NAME  FROM HRMS_CENTER ORDER BY CENTER_ID ");
				
				Object[][] departmentMasterObj = getSqlModel().getSingleResult(" SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT ORDER BY DEPT_ID "); 
				
				
				Object[][] designationMasterObj = getSqlModel().getSingleResult(" SELECT RANK_ID,RANK_NAME FROM HRMS_RANK ORDER BY RANK_ID "); 
				
				Object[][] empTitleMasterObj = getSqlModel().getSingleResult(" SELECT  TITLE_CODE, TITLE_NAME FROM HRMS_TITLE ORDER BY TITLE_CODE "); 
				
				Object[][] empTypeMasterObj = getSqlModel().getSingleResult(" SELECT TYPE_ID, TYPE_NAME FROM HRMS_EMP_TYPE ORDER BY TYPE_ID ");
				
				Object[][] reportingToMasterObj = getSqlModel().getSingleResult(" SELECT EMP_ID,EMP_TOKEN FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S' ORDER BY EMP_ID ");
				
				Object[][] shiftMasterObj = getSqlModel().getSingleResult(" SELECT SHIFT_ID, SHIFT_NAME FROM HRMS_SHIFT ORDER BY SHIFT_ID ");
				
				Object[][] paybillMasterObj = getSqlModel().getSingleResult(" SELECT PAYBILL_ID, PAYBILL_GROUP FROM HRMS_PAYBILL ORDER BY PAYBILL_ID ");
				
				Object[][] gradeMasterObj = getSqlModel().getSingleResult(" SELECT CADRE_ID,CADRE_NAME FROM HRMS_CADRE ORDER BY CADRE_ID ");
				
				Object[][] tradeMasterObj = getSqlModel().getSingleResult("SELECT TRADE_CODE, TRADE_NAME FROM HRMS_TRADE ORDER BY TRADE_CODE");
			 	
				Object [][]genderMasterObj=null;
			 	
				try {
					DataModificatonUtil dmu = new DataModificatonUtil();
					dmu.initiate(context, session);
					TreeMap genderMap = dmu.getGenderXml("gender");
					dmu.terminate();
					if (genderMap != null && genderMap.size() > 0) {
						int l = 0;
						genderMasterObj = new Object[genderMap.size()][2];
						for (Iterator k = genderMap.keySet().iterator(); k
								.hasNext();) {
							genderMasterObj[l][0] = k.next();
							genderMasterObj[l][1] = genderMap.get(String
									.valueOf(genderMasterObj[l][0]));
							l++;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
					
				String officialDataQuery = " UPDATE HRMS_EMP_OFFC SET ";
				
				String whereQuery =" WHERE EMP_ID = ? ";
				
				/**
				 * Get column numbers with mandatory information
				 */
				HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();
				
				int count= 0 ;
				
				for (int i = 0; i < columnNames.length; i++) {
					int colNo = Integer.parseInt(String.valueOf(columnNames[i][0]));
					String colName = String.valueOf(columnNames[i][1]);
					
					System.out.println("colNo     "+colNo);
					
					System.out.println("colName     "+colName);
					
					if(colName.equals("Employee ID")) {
						Object [][] empIdMaster = getSqlModel().getSingleResult(" SELECT EMP_ID,EMP_TOKEN FROM HRMS_EMP_OFFC WHERE EMP_STATUS ='S' ");
						if(empIdMaster != null && empIdMaster.length > 0)
							empIdObj = MigrateExcelData.uploadExcelData(colNo, empIdMaster, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						continue;
					}
				 
					if(colName.equals("Employee Name")) {
						Object[][] empName=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
					}
					
					if(colName.equals("First Name")) {
						firstNameObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_FNAME = ? ,";
						count++;
						continue;
					}
					
					if(colName.equals("Middle Name")) {
						middleNameObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_MNAME = ? ,";
						count++;
						continue;
					}
					
					if(colName.equals("Last Name")) {
						lastNameObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_LNAME = ? ,";
						count++;
						continue;
					}
					 
					if(colName.equals("Division")) {
						//if(divMasterObj != null && divMasterObj.length > 0)
							divisionObj=MigrateExcelData.uploadExcelData(colNo, divMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_DIV = ? ,";
						count++;
						continue;
					}
					
					if(colName.equals("Branch")) {
						//if(branchMasterObj != null && branchMasterObj.length > 0)
							branchObj=MigrateExcelData.uploadExcelData(colNo, branchMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_CENTER = ? ,";
						count++;
						continue;
					}
					
					if(colName.equals("Department")) {
						//if(departmentMasterObj != null && departmentMasterObj.length > 0)
							departmentObj=MigrateExcelData.uploadExcelData(colNo, departmentMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_DEPT = ? ,";
						count++;
						continue;
					}
					
					if(colName.equals("Designation")) {
						//if(designationMasterObj != null && designationMasterObj.length > 0)
							designationObj=MigrateExcelData.uploadExcelData(colNo, designationMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_RANK = ? ,";
						count++;
						continue;
					}
					
					if(colName.equals("Employee Title")) {
						//if(empTitleMasterObj != null && empTitleMasterObj.length > 0)
							employeeTitleObj=MigrateExcelData.uploadExcelData(colNo, empTitleMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_TITLE_CODE = ? ,";
						count++;
						continue;
					}
					
					if(colName.equals("Employee Type")) {
						//if(empTypeMasterObj != null && empTypeMasterObj.length > 0)
							employeeTypeObj=MigrateExcelData.uploadExcelData(colNo, empTypeMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_TYPE = ? ,";
						count++;
						continue;
					}
					
					
					
					if(colName.equals("Shift")) {
						//if(shiftMasterObj != null && shiftMasterObj.length > 0)
							shiftObj=MigrateExcelData.uploadExcelData(colNo, shiftMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_SHIFT = ? ,";
						count++;
						continue;
					}
					
					if(colName.equals("Pay Bill")) {
						//if(paybillMasterObj != null && paybillMasterObj.length > 0)
							paybillObj=MigrateExcelData.uploadExcelData(colNo, paybillMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_PAYBILL = ? ,";
						count++;
						continue;
					}
					
					if(colName.equals("Gender")) {
						if(genderMasterObj != null && genderMasterObj.length > 0)
							genderObj=MigrateExcelData.uploadExcelData(colNo, genderMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
							officialDataQuery+=" EMP_GENDER = ? ,";
						count++;
						continue;
					}
				 	
					
					if(colName.equals("Birth Date")) {
						birthDateObj = MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.DATE_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_DOB = TO_DATE(?,'MM/DD/YYYY'),";
						count++;
						continue;
					}
					
					if(colName.equals("Grade")) {
						//if(gradeMasterObj != null && gradeMasterObj.length > 0)
							gradeObj = MigrateExcelData.uploadExcelData(colNo, gradeMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_CADRE = ? ,";
						count++;
						continue;
					}
					
					if(colName.equals("Joining Date")) {
						joiningDateObj = MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.DATE_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_REGULAR_DATE = TO_DATE(?,'MM/DD/YYYY'),";
						count++;
						continue;
					}
					
					if(colName.equals("Leaving Date")) {
						leavingObj = MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.DATE_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_LEAVE_DATE = TO_DATE(?,'MM/DD/YYYY'),";
						count++;
						continue;
					}
					if(colName.equals("Status")) {
						statusObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
				 		officialDataQuery+=" EMP_STATUS = ? ,";
						count++;
						continue;
					}
					
					if(colName.equals("Group Joining Date")) {
						groupJoinDateObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.DATE_TYPE, columnInformation.get(colNo));
				 		officialDataQuery+=" EMP_GROUP_JOIN_DATE = TO_DATE(?,'MM/DD/YYYY'),";
						count++;
						continue;
					}
					if(colName.equals("Date Of Confirm")) {
						dateofconfirm=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.DATE_TYPE, columnInformation.get(colNo));
				 		officialDataQuery+=" EMP_CONFIRM_DATE = TO_DATE(?,'MM/DD/YYYY'),";
						count++;
						continue;
					}
					if(colName.equals("Reporting To")) {
					
					System.out.println("in    reportingToMasterObj.length    "+reportingToMasterObj.length);
					
					//if(reportingToMasterObj != null && reportingToMasterObj.length > 0)
						reportingto=MigrateExcelData.uploadExcelData(colNo, reportingToMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
					
					officialDataQuery+=" EMP_REPORTING_OFFICER = ? ,";
					count++;
					continue;
				}
					
				if(colName.equals("Trade")) {
						//if(tradeMasterObj != null && tradeMasterObj.length > 0)
							tradeObj = MigrateExcelData.uploadExcelData(colNo, tradeMasterObj, MigrateExcelData.MASTER_TYPE, columnInformation.get(colNo));
						officialDataQuery+=" EMP_TRADE = ? ,";
						count++;
						continue;
					}
					
				if(colName.equals("Role")) {
					roleObj=MigrateExcelData.uploadExcelData(colNo, null, MigrateExcelData.STRING_TYPE, columnInformation.get(colNo));
					officialDataQuery+=" EMP_ROLE = ? ,";
					count++;
					continue;
				  }
				}
				
				officialDataQuery = officialDataQuery.substring(0, officialDataQuery.length() - 1);
				officialDataQuery+=whereQuery;
				logger.info("officialDataQuery_______________    "+officialDataQuery);
				boolean res = MigrateExcelData.isFileToBeUploaded();
				logger.info("value of res--------------------------"+res);
				if(res)
				{
					if(empIdObj != null && empIdObj.length > 0)
					{
						boolean officialUpdateResult= false;
						if(count > 0)
						{
							Object [][] updateOfficialObj = new Object[empIdObj.length][count+1];
							for (int k = 0; k < empIdObj.length; k++) {
								int temp = 0;
								
								if(firstNameObj != null && firstNameObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(firstNameObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								
								if(middleNameObj != null && middleNameObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(middleNameObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								
								if(lastNameObj != null && lastNameObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(lastNameObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								if(divisionObj != null && divisionObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(divisionObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								if(branchObj != null && branchObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(branchObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								if(departmentObj != null && departmentObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(departmentObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								
								if(designationObj != null && designationObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(designationObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								
								if(employeeTitleObj != null && employeeTitleObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(employeeTitleObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								
								if(employeeTypeObj != null && employeeTypeObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(employeeTypeObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								
							/*	if(reportingToObj != null && reportingToObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(reportingToObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}*/
								
								if(shiftObj != null && shiftObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(shiftObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								if(paybillObj != null && paybillObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(paybillObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								if(genderObj != null && genderObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(genderObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								if(birthDateObj != null && birthDateObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(birthDateObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								if(gradeObj != null && gradeObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(gradeObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								
								if(joiningDateObj != null && joiningDateObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(joiningDateObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								
								if(leavingObj != null && leavingObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(leavingObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								
								if(statusObj != null && statusObj.length > 0){
									try {
										
										if(String.valueOf(statusObj[k][0]).equalsIgnoreCase("Service"))
										{
											statusObj[k][0]= "S";
										}
										if(String.valueOf(statusObj[k][0]).equalsIgnoreCase("Retired"))
										{
											statusObj[k][0]= "R";
										}
										if(String.valueOf(statusObj[k][0]).equalsIgnoreCase("Resigned"))
										{
											statusObj[k][0]= "N";
										}
										if(String.valueOf(statusObj[k][0]).equalsIgnoreCase("Terminated"))
										{
											statusObj[k][0]= "E";
										}
										
										updateOfficialObj[k][temp] =String.valueOf(statusObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
																
								if(groupJoinDateObj != null && groupJoinDateObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(groupJoinDateObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								if(dateofconfirm != null && dateofconfirm.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(dateofconfirm[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								
								if(reportingto != null && reportingto.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(reportingto[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
																								
								if(tradeObj != null && tradeObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(tradeObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}
								
								if(roleObj != null && roleObj.length > 0){
									try {
										updateOfficialObj[k][temp] =String.valueOf(roleObj[k][0]);
									}catch (Exception e) {
										updateOfficialObj[k][temp] ="";
									}	
									temp++;
								}								
						 	
								updateOfficialObj[k][temp] =String.valueOf(empIdObj[k][0]);
							}
						/* for (int i = 0; i < updateOfficialObj.length; i++) {
								for (int j = 0; j < updateOfficialObj[0].length; j++) {
								
									logger.info("updateOfficialObj[i][j]                        "+updateOfficialObj[i][j]);
								}
							} */
							officialUpdateResult = getSqlModel().singleExecute(officialDataQuery,updateOfficialObj);
							logger.info("officialUpdateResult-----------------"+officialUpdateResult);
						}
						
						if(officialUpdateResult)
							bean.setStatus("Success");
						else{
							if(officialUpdateResult && count == 0) {
								bean.setStatus("Success");
							}else if(officialUpdateResult && count == 0) {
								bean.setStatus("Success");
							}else {
								bean.setStatus("Fail");
								bean.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. Upload the sheet again to transfer the data");
							}
						}
					}
				}
				
				else {
					bean.setStatus("Fail");
					bean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
				}
			}
			
		} catch (Exception e) {
			logger.error("Error in OfficialPartialUploadModel -- uploadOfficialDataTemplate "+e);
			bean.setStatus("Fail");
			bean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
			e.printStackTrace();
		}
		
	}
 
	public boolean generateUrlList(HttpServletRequest request,
			HttpServletResponse response, OfficalPartialUpload bean) {
		try{
			Object [][] resultObj = getEmployee(bean);
				if (resultObj != null && resultObj.length >0) {
					request.setAttribute("totalRecords",resultObj.length);
					request.setAttribute("recPerPage",EMPLOYEE_COUNT);
					bean.setDataExists(true);
					return true;
				}else{
					request.setAttribute("totalRecords",0);
					return false;
				}
			}
			catch (Exception e) {
				logger.error("Error in generateUrlList == "+e);
				return false;
		}
	}
	
	public Object [][] getEmployee(OfficalPartialUpload bean){
		
		Object[][] empObj = null;
		try {
			String empQuery =" SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) FROM HRMS_EMP_OFFC "
					+" WHERE EMP_STATUS ='S'AND EMP_TOKEN IS NOT NULL ";

			if(!bean.getDivCode().equals(""))
				empQuery+=" and HRMS_EMP_OFFC.EMP_DIV="+bean.getDivCode();
			if(!bean.getBranchCode().equals(""))
				empQuery+=" and HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchCode();
			if(!bean.getDeptCode().equals(""))
				empQuery+=" and HRMS_EMP_OFFC.EMP_DEPT="+bean.getDeptCode();
			if(!bean.getDesgCode().equals(""))
				empQuery+=" and HRMS_EMP_OFFC.EMP_RANK="+bean.getDesgCode();
			if(!bean.getEmpTypeCode().equals(""))
				empQuery+=" and HRMS_EMP_OFFC.EMP_TYPE="+bean.getEmpTypeCode();
			if(!bean.getGradeCode().equals(""))
				empQuery+=" and HRMS_EMP_OFFC.EMP_GRADE="+bean.getGradeCode();
			
			empQuery+=" ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME)";

			empObj = getSqlModel().getSingleResult(empQuery);
			
		} catch (Exception e) {
			logger.error("Error in getEmployee == "+e);
		}
		
		return empObj;
}

}
