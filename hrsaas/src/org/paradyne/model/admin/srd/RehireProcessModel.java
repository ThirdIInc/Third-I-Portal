/**
 * 
 */
package org.paradyne.model.admin.srd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.admin.srd.RehireProcess;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.common.ApplCodeTemplateModel;

/**
 * @author AA0517
 *
 */
public class RehireProcessModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(RehireProcessModel.class);
	public void getEmploeeList(RehireProcess bean,HttpServletRequest request) {
		String empQuery ="SELECT EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME AS NAME,TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY'), " 
						+" TO_CHAR(EMP_LEAVE_DATE,'DD-MM-YYYY'),TYPE_NAME "
						+" FROM HRMS_EMP_OFFC " +
						 " LEFT JOIN HRMS_EMP_TYPE ON (HRMS_EMP_TYPE.TYPE_ID = HRMS_EMP_OFFC.EMP_TYPE)"
						+" WHERE EMP_LEAVE_DATE IS NOT NULL AND ( 1=0 " ;
		boolean isFilterSelected =false;
		if(!bean.getApplDivisionCode().equals("")){
			empQuery += " OR EMP_DIV IN("+bean.getApplDivisionCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplBranchCode().equals("")){
			empQuery += " OR EMP_CENTER IN("+bean.getApplBranchCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplDeptCode().equals("")){
			empQuery += " OR EMP_DEPT IN("+bean.getApplDeptCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplDesgCode().equals("")){
			empQuery += " OR EMP_RANK IN("+bean.getApplDesgCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplEmpCode().equals("")){
			empQuery += " OR HRMS_EMP_OFFC.EMP_ID IN("+bean.getApplEmpCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplEmpTypeCode().equals("")){
			empQuery += " OR HRMS_EMP_OFFC.EMP_TYPE IN("+bean.getApplEmpTypeCode()+")";
			isFilterSelected =true;
		}
		if(!isFilterSelected){
			empQuery +="OR 1=1";
		}
		empQuery +=") ORDER BY UPPER(NAME) ";
		Object [][]empData = null;
		try {
			empData = getSqlModel().getSingleResult(empQuery);
		} catch (Exception e) {
			logger.error("exception in empData query",e);
		} //end of catch
		ArrayList tableList = new ArrayList();
		if(empData != null && empData.length !=0){
			for (int i = 0; i < empData.length; i++) {
				RehireProcess bean1 = new RehireProcess();
				bean1.setListEmpId(String.valueOf(empData[i][0]));
				bean1.setListEmpToken(String.valueOf(empData[i][1]));
				bean1.setListEmpName(String.valueOf(empData[i][2]));
				bean1.setListDoj(String.valueOf(empData[i][3]));
				bean1.setListDol(String.valueOf(empData[i][4]));
				bean1.setListEmpType(String.valueOf(empData[i][5]));
				tableList.add(bean1);
			} //end of loop
			bean.setEmployeeList(tableList);
		} //end of if
		else{
			bean.setNoData("true");
		} //end of else
	} //end of getEmploeeList method
	public void getReocordToProcess(RehireProcess bean, String empIds) {
		Object[][]data = null;
		try {
			String query = "SELECT EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_LNAME,TO_CHAR(SYSDATE,'DD-MM-YYYY') "
							+" FROM HRMS_EMP_OFFC "
							+" WHERE EMP_ID IN ("+empIds+")";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in data query",e);
		} //end of catch
		ArrayList tableList = new ArrayList();
		
		if(data !=null && data.length >0){
			ApplCodeTemplateModel tempModel = new ApplCodeTemplateModel();
			tempModel.initiate(context, session);
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			String newToken ="";
			for (int i = 0; i < data.length; i++) {
				RehireProcess bean1 = new RehireProcess();
				bean1.setProcessEmpId(String.valueOf(data[i][0]));
				bean1.setOldEmpToken(String.valueOf(data[i][1]));
				bean1.setProcessEmpName(String.valueOf(data[i][2]));
				bean1.setProcessDoj(String.valueOf(data[i][3]));
				bean1.setUpdOrInsert("insert");
				newToken = tempModel.generateApplicationCode("", "EmpId", String.valueOf(data[i][0]), sysDate);
				logger.info(" new token for "+String.valueOf(data[i][1])+" =="+newToken);
				bean1.setNewEmpToken(newToken);
				bean1.setNewEmpTokenHidden(newToken);
				tableList.add(bean1);
			} //end of loop
			bean.setProcessList(tableList);
		} //end of if
	} //end of getReocordToProcess method
	public String saveProcess(RehireProcess rehireProcess, String[] empIds,
			String[] newToken, String[] doj, String[] updOrInsertFlag, String[] oldToken, String[] name,String[] newTokenHidden) {
		String value = "";
		try {
			Object[][]updateBatch = new Object[empIds.length][2];
			//Object[][]insertBatch = new Object[empIds.length][4];
			String newTokenExist = "";
			int count = 0;
			for (int i = 0; i < empIds.length; i++) {
				//System.out.println("updOrInsertFlag["+i+"]"+updOrInsertFlag[i]);
				if(updOrInsertFlag[i].equals("insert")){
					newTokenExist += "'"+newToken[count].trim()+"'"+",";
					count++;
				} //end of if
			} //end of if
			if(count >0){
				newTokenExist = newTokenExist.substring(0,newTokenExist.length()-1);
				logger.info("newTokenExist=====>"+newTokenExist);
			} //end of if
			Object[][]chkTokenExist = null;
			try {
				String tokenQuery = "SELECT EMP_ID,Emp_token FROM HRMS_EMP_OFFC WHERE EMP_TOKEN IN ("
						+ newTokenExist + ")";
				chkTokenExist = getSqlModel().getSingleResult(tokenQuery);
			} catch (Exception e) {
				logger.error("exception in chk token query",e);
			} //end of catch
			if(chkTokenExist !=null && chkTokenExist.length >0){
				ArrayList tableList = new ArrayList();
				int insertTemp=0;
				for (int i = 0; i < empIds.length; i++) {
					RehireProcess bean1 = new RehireProcess();
					bean1.setProcessEmpId(String.valueOf(empIds[i]));
					bean1.setOldEmpToken(String.valueOf(oldToken[i]));
					bean1.setProcessEmpName(String.valueOf(name[i]));
					if(updOrInsertFlag[i].equals("insert"))
							{
						bean1.setNewEmpToken(String.valueOf(newToken[insertTemp].trim()));
						insertTemp++;
							}
					else
						bean1.setNewEmpToken(String.valueOf("Field is Disabled"));	
					
					bean1.setNewEmpTokenHidden(String.valueOf(newTokenHidden[i]));
					bean1.setProcessDoj(String.valueOf(doj[i]));
					bean1.setUpdOrInsert(String.valueOf(updOrInsertFlag[i]));
					for (int j = 0; j < chkTokenExist.length; j++) {
						if(String.valueOf(chkTokenExist[j][0]).equals(empIds[i])){
							bean1.setColour("#FF8383");
							break;
						} //end of if
						else{
							bean1.setColour("#FFFFFF");
						} //end of else
					} //end of if
					tableList.add(bean1);
				} //end of loop
				rehireProcess.setProcessList(tableList);
				value = "1";
			} //end of if
			else{
				int cnt=0;
				for (int i = 0; i < empIds.length; i++) {
					if(updOrInsertFlag[i].equals("update")){
						updateBatch[i][0] = doj[i];
						updateBatch[i][1] = empIds[i];
					} //end of if
					else{
						Object[][]newEmpId = null;
						String query = "SELECT NVL(MAX(EMP_ID),0)+1 FROM HRMS_EMP_OFFC";
						newEmpId = getSqlModel().getSingleResult(query);
						boolean offcFlag = insertOfficalData(empIds[i],newToken[cnt],doj[i],String.valueOf(newEmpId[0][0]));
						if(offcFlag){
							insertAddressDtl(empIds[i],String.valueOf(newEmpId[0][0]));
							insertPersonalDtl(empIds[i],String.valueOf(newEmpId[0][0]));
							insertFamilyDtl(empIds[i],String.valueOf(newEmpId[0][0]));
							insertQualificationDtl(empIds[i],String.valueOf(newEmpId[0][0]));
							insertSalaryDtl(empIds[i],String.valueOf(newEmpId[0][0]));
						} //end of if
						cnt++;
					} //end of else
				} //end of loop
				String updateBatchQuery = "UPDATE HRMS_EMP_OFFC SET EMP_REGULAR_DATE = TO_DATE(?,'DD-MM-YYYY'),EMP_STATUS = 'S', " 
				+" EMP_LEAVE_DATE = to_date('','dd-mm-yyyy')  "
				+" WHERE EMP_ID = ?";
				getSqlModel().singleExecute(updateBatchQuery,updateBatch);
				value = "2";
			} //end of else
		} catch (Exception e) {
			logger.error("exception in saveProcess method",e);
			e.printStackTrace();
		} //end of catch
		return value;
	} //end of saveProcess method
	
	private void insertSalaryDtl(String empId, String newEmpId) {
		try {
			String query = "INSERT INTO HRMS_SALARY_MISC (SAL_ID, EMP_ID, SAL_GPFNO, SAL_CGHSNO, SAL_PANNO, SAL_MICR_REGULAR, " 
			+" SAL_ACCNO_REGULAR, SAL_PAYSCALE, SAL_MICR_PENSION, SAL_ACCNO_PENSION, SAL_MICR_PENSION_SAVINGS,  "
			+" SAL_ACCNO_PENSION_SAVINGS, SAL_PENSIONABLE, SAL_UNIT_CODE, SAL_PAY_MODE, SAL_PAYBILL_NO_NOTUSED, "
			+" SAL_DCMAF_MEMBERSHIP, SAL_ESCINUMBER, SAL_REIMBMENT, SAL_REIMBANK, SAL_EPF_FLAG, SAL_VPF_FLAG, SAL_GPF_FLAG, " 
			+" SAL_PFTRUST_FLAG, SAL_GRATUITY_ACCNO, SAL_PENSION_BANK, ACCOUNT_CATEGORY_ID,  "
			+" COST_CENTER_ID, SAL_PENSION_ACCNO, SUB_COST_CENTER_ID, SAL_LWF_FLAG) "
			+" (SELECT (SELECT NVL(MAX(SAL_ID),0)+1 FROM HRMS_SALARY_MISC),"+newEmpId+",SAL_GPFNO, SAL_CGHSNO, SAL_PANNO, SAL_MICR_REGULAR, " 
			+" SAL_ACCNO_REGULAR, SAL_PAYSCALE, SAL_MICR_PENSION, SAL_ACCNO_PENSION, SAL_MICR_PENSION_SAVINGS,  "
			+" SAL_ACCNO_PENSION_SAVINGS, SAL_PENSIONABLE, SAL_UNIT_CODE, SAL_PAY_MODE, SAL_PAYBILL_NO_NOTUSED,  "
			+" SAL_DCMAF_MEMBERSHIP, SAL_ESCINUMBER, SAL_REIMBMENT, SAL_REIMBANK, SAL_EPF_FLAG, SAL_VPF_FLAG, SAL_GPF_FLAG, " 
			+" SAL_PFTRUST_FLAG, SAL_GRATUITY_ACCNO, SAL_PENSION_BANK, ACCOUNT_CATEGORY_ID,  "
			+" COST_CENTER_ID, SAL_PENSION_ACCNO, SUB_COST_CENTER_ID, SAL_LWF_FLAG FROM HRMS_SALARY_MISC WHERE EMP_ID = "+empId+")";
			getSqlModel().singleExecute(query);
		} catch (Exception e) {
			logger.error("esception in salary insert query",e);
		} //end of catch
	} //end of insertFamilyDtl method
	private void insertQualificationDtl(String empId, String newEmpId) {
		try {
			String query = "INSERT INTO HRMS_EMP_QUA (QUA_ID, EMP_ID, QUA_MAST_CODE, QUA_INST, QUA_UNIV, QUA_YEAR, " 
			+" QUA_PER, QUA_ISTECH, QUA_ATTEMPT, QUA_GRD) "
			+" (SELECT (SELECT NVL(MAX(QUA_ID),0)+1 FROM HRMS_EMP_QUA),"+newEmpId+",QUA_MAST_CODE, QUA_INST, QUA_UNIV, QUA_YEAR, " 
			+" QUA_PER, QUA_ISTECH, QUA_ATTEMPT, QUA_GRD FROM HRMS_EMP_QUA WHERE EMP_ID = "+empId+") ";
			getSqlModel().singleExecute(query);
		} catch (Exception e) {
			logger.error("esception in quali insert query",e);
		} //end of catch
	} //end of insertFamilyDtl method
	private void insertFamilyDtl(String empId, String newEmpId) {
		try {
			String query = "INSERT INTO HRMS_EMP_FML (FML_ID, EMP_ID, FML_DOB, FML_GENDER, FML_RELATION, FML_PROFESSION, FML_IDMARK, " 
			+" FML_ADDRESS, FML_PH, FML_EMAIL, FML_MARITAL_STATUS, FML_SPOUSE_BLDGRP, FML_SPOUSE_PHOTO, "
			+" FML_FNAME, FML_MNAME, FML_LNAME, FML_NATIONALITY, FML_ISALIVE, FML_ISDEPENDENT, FML_OTHERINFO) "
			+" (SELECT (SELECT NVL(MAX(FML_ID),0)+1 FROM HRMS_EMP_FML),"+newEmpId+",FML_DOB, FML_GENDER, FML_RELATION, FML_PROFESSION, FML_IDMARK, " 
			+" FML_ADDRESS, FML_PH, FML_EMAIL, FML_MARITAL_STATUS, FML_SPOUSE_BLDGRP, FML_SPOUSE_PHOTO, "
			+" FML_FNAME, FML_MNAME, FML_LNAME, FML_NATIONALITY, FML_ISALIVE, FML_ISDEPENDENT, FML_OTHERINFO "
			+" FROM HRMS_EMP_FML WHERE EMP_ID = "+empId+")";
			getSqlModel().singleExecute(query);
		} catch (Exception e) {
			logger.error("esception in family insert query",e);
		} //end of catch
	} //end of insertFamilyDtl method
	private void insertPersonalDtl(String empId, String newEmpId) {
		try {
			String query = "INSERT INTO HRMS_EMP_PERS (EMP_ID, EMP_CASTE_CATG, EMP_CASTE, EMP_SUBCAST, EMP_HEIGHT, EMP_WEIGHT, " 
			+" EMP_IDMARK, EMP_BLDGP, EMP_HANDI_DESC, EMP_HOBBY, EMP_PHOTO, EMP_MARITAL_STATUS, EMP_MARRG_DATE, EMP_HOMETOWN,  "
			+" EMP_NEAREST_HOMETOWN_RLYSTN, EMP_HANDI_PERCENTAGE, EMP_NATIONALITY, EMP_SHOE_SIZE, EMP_OVERALL_SIZE,  "
			+" EMP_RELIGION, EMP_ICARD_NO, EMP_ICARD_STATUS, EMP_LOCAL_RLYSTN, EMP_ICARD_ISSUE_PLACE, EMP_RECRUITMENT_MEDIUM, " 
			+" EMP_SERVICE_NO, EMP_ICARD_ISSUE_DATE, EMP_SPCIAL_SKILL, EMP_PASSPORT, EMP_ISHANDICAP, EMP_PASSPORT_EXPDATE, "
			+" EMP_AILMENTS, EMP_ALLERGIES, EMP_DISEASES, EMP_IS_CRIMINAL_REC, EMP_CRIMINAL_REC_DESC) "
			+" (SELECT "+newEmpId+", EMP_CASTE_CATG, EMP_CASTE, EMP_SUBCAST, EMP_HEIGHT, EMP_WEIGHT, EMP_IDMARK, EMP_BLDGP, " 
			+" EMP_HANDI_DESC, EMP_HOBBY, EMP_PHOTO, EMP_MARITAL_STATUS, EMP_MARRG_DATE, EMP_HOMETOWN, EMP_NEAREST_HOMETOWN_RLYSTN, " 
			+" EMP_HANDI_PERCENTAGE, EMP_NATIONALITY, EMP_SHOE_SIZE, EMP_OVERALL_SIZE, EMP_RELIGION, EMP_ICARD_NO, EMP_ICARD_STATUS,  "
			+" EMP_LOCAL_RLYSTN, EMP_ICARD_ISSUE_PLACE, EMP_RECRUITMENT_MEDIUM, EMP_SERVICE_NO, EMP_ICARD_ISSUE_DATE,  "
			+" EMP_SPCIAL_SKILL, EMP_PASSPORT, EMP_ISHANDICAP, EMP_PASSPORT_EXPDATE, EMP_AILMENTS, "
			+" EMP_ALLERGIES, EMP_DISEASES, EMP_IS_CRIMINAL_REC, EMP_CRIMINAL_REC_DESC FROM HRMS_EMP_PERS WHERE EMP_ID = "+empId+")";
			getSqlModel().singleExecute(query);
		} catch (Exception e) {
			logger.error("esception in personal insert query",e);
		} //end of catch
	} //end of insertPersonalDtl method
	private void insertAddressDtl(String empId, String newEmpId) {
		try {
			String query = "INSERT INTO HRMS_EMP_ADDRESS (EMP_ID, ADD_TYPE, ADD_1, ADD_2, ADD_3, ADD_CITY, ADD_STATE, " 
			+" ADD_CNTRY, ADD_PH1, ADD_PH2, ADD_FAX, ADD_MOBILE, ADD_EMAIL, ADD_PINCODE) "
			+" (SELECT "+newEmpId+",ADD_TYPE, ADD_1, ADD_2, ADD_3, ADD_CITY, ADD_STATE,  "
			+" ADD_CNTRY, ADD_PH1, ADD_PH2, ADD_FAX, ADD_MOBILE, ADD_EMAIL, ADD_PINCODE FROM HRMS_EMP_ADDRESS WHERE EMP_ID = "+empId+")";
			getSqlModel().singleExecute(query);
		} catch (Exception e) {
			logger.error("esception in address insert query",e);
		} //end of catch
	} //end of insertAddressDtl method
	private boolean insertOfficalData(String empId, String newToken, String doj, String newEmpId) {
		boolean flag = false;
		try {
			/*String insQuery = "INSERT INTO HRMS_EMP_OFFC (EMP_ID,EMP_TOKEN,EMP_REGULAR_DATE, EMP_FNAME, " 
				+" EMP_MNAME, EMP_LNAME, EMP_CENTER, EMP_CADRE, EMP_GROUP, EMP_TRADE,  "
				+" EMP_GENDER, EMP_DOB, EMP_SHIFT, EMP_STATUS, EMP_REPORTING_OFFICER, EMP_TYPE, " 
				+" EMP_DESG, EMP_RANK, EMP_DISCIPLINE, EMP_GRADE, EMP_TITLE_CODE,  "
				+" EMP_LEAVE_DATE, EMP_PAYBILL,EMP_SENIORITY_DATE, "
				+" EMP_INCR_DATE, EMP_PHOTO, EMP_DEPT, EMP_DIV, "
				+" EMP_SAL_GRADE, EMP_IND_STATUS, "
				+" EMP_CONFIRM_DATE) "
				+" (SELECT "+newEmpId+",'"+newToken+"', "
				+" TO_DATE('"+doj+"','DD-MM-YYYY'),EMP_FNAME, EMP_MNAME, EMP_LNAME, EMP_CENTER, EMP_CADRE, EMP_GROUP, EMP_TRADE, " 
				+" EMP_GENDER, EMP_DOB, EMP_SHIFT, 'S', EMP_REPORTING_OFFICER, EMP_TYPE, "
				+" EMP_DESG,EMP_RANK, EMP_DISCIPLINE, EMP_GRADE, EMP_TITLE_CODE, "
				+" EMP_LEAVE_DATE, EMP_PAYBILL,EMP_SENIORITY_DATE, "
				+" EMP_INCR_DATE, EMP_PHOTO, EMP_DEPT, EMP_DIV, "
				+" EMP_SAL_GRADE, EMP_IND_STATUS, "
				+" EMP_CONFIRM_DATE FROM HRMS_EMP_OFFC WHERE EMP_ID ="+empId+")";
			flag = getSqlModel().singleExecute(insQuery);*/
			
			String insQuery = "INSERT INTO HRMS_EMP_OFFC (EMP_ID,EMP_TOKEN,EMP_REGULAR_DATE, EMP_FNAME, " 
				+" EMP_MNAME, EMP_LNAME, EMP_CENTER, EMP_CADRE, EMP_GROUP, EMP_TRADE,  "
				+" EMP_GENDER, EMP_DOB, EMP_SHIFT, EMP_STATUS, EMP_REPORTING_OFFICER, EMP_TYPE, " 
				+" EMP_DESG, EMP_RANK, EMP_DISCIPLINE, EMP_TITLE_CODE,  "
				+"  EMP_PAYBILL,EMP_SENIORITY_DATE, "
				+" EMP_INCR_DATE, EMP_PHOTO, EMP_DEPT, EMP_DIV, "
				+" EMP_SAL_GRADE, EMP_IND_STATUS, "
				+" EMP_CONFIRM_DATE) "
				+" (SELECT "+newEmpId+",'"+newToken+"', "
				+" TO_DATE('"+doj+"','DD-MM-YYYY'),EMP_FNAME, EMP_MNAME, EMP_LNAME, EMP_CENTER, EMP_CADRE, EMP_GROUP, EMP_TRADE, " 
				+" EMP_GENDER, EMP_DOB, EMP_SHIFT, 'S', EMP_REPORTING_OFFICER, EMP_TYPE, "
				+" EMP_DESG,EMP_RANK, EMP_DISCIPLINE, EMP_TITLE_CODE, "
				+"  EMP_PAYBILL,EMP_SENIORITY_DATE, "
				+" EMP_INCR_DATE, EMP_PHOTO, EMP_DEPT, EMP_DIV, "
				+" EMP_SAL_GRADE, EMP_IND_STATUS, "
				+" EMP_CONFIRM_DATE FROM HRMS_EMP_OFFC WHERE EMP_ID ="+empId+")";
			flag = getSqlModel().singleExecute(insQuery);
			if(flag)
			{
				String updateQuery="UPDATE HRMS_EMP_OFFC SET EMP_LEAVE_DATE = to_date('','dd-mm-yyyy') WHERE EMP_ID = "+empId;
				flag=getSqlModel().singleExecute(updateQuery);
			}
		} catch (Exception e) {
			logger.error("esception in insert query",e);
		} //end of catch
		return flag;
	} //end of insertOfficalData method
	
} //end of class
