package org.paradyne.sql.D1;

import org.paradyne.lib.SqlBase;

/**
 * @modified By AA1711
 * @purpose : Change Request  
 */

public class BusinessRequirementDocumentModelSql extends SqlBase{
  public String getQuery(int id) {
    switch (id){
    case 1:
    	return " INSERT INTO HRMS_D1_BUSINESS_REQUIREMENT "
      		+ " (BRD_TICKET_NO, PROJECT_NAME, PROJ_START_DATE, PROJ_END_DATE,"
      		+ " BISUNESS_OBJ, PROJECT_CLOSER, PROJ_CONSTRAINTS, PROJ_ASSUMPTIONS, "
      		+ " PROJ_DOC_TYPE, PROJ_UPLOAD_DOC, PROJ_FORWARD_TO, PROJ_FORWARD_EMPID,"
      		+ " PROJ_COMPLETED_BY, PROJ_COMPLETED_DATE, BUSINESS_CODE,PROJ_STATUS, " 
      		+ " PROJ_INITIATOR,PROJ_ALLOCATED_BUDGET, PROJ_APPLICANT_COMMENTS, "
      		+ " PROJ_PRIORITY,  PROJ_TYPE_ID, PROJ_CLASSIFICATION_ID, "
      		+ " PROJ_ANNUAL_FINANCIAL_BENIFIT,BUSINESS_UNIT_ID,RANK) "
      		+ " VALUES(?,?,TO_DATE(?,'dd-mm-yyyy'),TO_DATE(?,'dd-mm-yyyy'),?,?,?,"
      		+ " ?,?,?,?,?,?,TO_DATE(?,'dd-mm-yyyy'),?,?,?,?,?,?,?,?,?,?,?)";
    case 2:
    	return "UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET PROJECT_NAME = ?,"
      		+ " PROJ_START_DATE =TO_DATE(?,'dd-mm-yyyy') , PROJ_END_DATE =TO_DATE(?,'dd-mm-yyyy'), "
      		+ " BISUNESS_OBJ = ?, PROJECT_CLOSER = ?, PROJ_CONSTRAINTS =?, PROJ_ASSUMPTIONS = ?,"
      		+ " PROJ_DOC_TYPE = ?, PROJ_UPLOAD_DOC = ?, PROJ_FORWARD_TO = ?, PROJ_FORWARD_EMPID =?,"
      		+ " PROJ_STATUS = ? , PROJ_ALLOCATED_BUDGET = ?, PROJ_INITIATOR = ?, "
      		+ " PROJ_APPLICANT_COMMENTS = ?, PROJ_PRIORITY = ?,  PROJ_TYPE_ID = ?, "
      		+ " PROJ_CLASSIFICATION_ID = ?, PROJ_ANNUAL_FINANCIAL_BENIFIT = ?, "
      		+ " BUSINESS_UNIT_ID=? ,RANK =? "
      		+ " WHERE BUSINESS_CODE=? ";
    case 3:
    	return "UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET PROJ_FORWARD_TO =?, "
      		+ " PROJ_FORWARD_EMPID =? ,PROJ_CURENT_STAGE =? , PROJ_DOC_TYPE =?, "
      		+ " PROJ_UPLOAD_DOC= ? WHERE  BUSINESS_CODE =?";
    case 4:
    	return "UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET PROJ_FORWARD_EMPID =?, BUSINESS_CODE =? ";
    case 5:
    	return "UPDATE HRMS_D1_BUSINESS_PATH SET BUSINESS_APPROVER_TYPE = ?,"
       		 + " BUSINESS_APPROVER_CODE =? ,BUSINESS_COMMENTS =? ,BUSINESS_STATE =?,"
       		 + " BUSINESS_DOC_TYPE =?, BUSINESS_UPLOAD_FILE =?, BUSINESS_STATUS =? "
       		 + " WHERE BUSINESS_CODE =?";
    case 6:
    	return " UPDATE HRMS_D1_BUSINESS_REQUIREMENT SET HRMS_D1_BUSINESS_REQUIREMENT.BISUNESS_OBJ = ?,"
      		+ " HRMS_D1_BUSINESS_REQUIREMENT.PROJECT_CLOSER = ?,"
      		+ " HRMS_D1_BUSINESS_REQUIREMENT.PROJ_CONSTRAINTS = ?,"
      		+ " HRMS_D1_BUSINESS_REQUIREMENT.PROJ_ASSUMPTIONS = ? "
      		+ " WHERE BUSINESS_CODE = ?";
    }
    return "Framework could not the query number specified";
  }
}