
package org.paradyne.sql.common;

import org.paradyne.lib.SqlBase;

/**
 * @author tarunc
 * @date Oct 16, 2008
 * @description ReportingHandoverModelSql class serves as Sql for Reporting Hand Over Form to write 
 * 				queries for various actions
 */
public class ReportingHandoverModelSql extends SqlBase {

	/* (non-Javadoc)
	 * @see org.paradyne.lib.SqlBase#getQuery(int)
	 */
	public String getQuery(int id) {
		switch (id) {
		case 1 : return "SELECT REPORTINGHDR_CODE, REPORTINGDTL_CODE "
						+"FROM HRMS_REPORTING_STRUCTUREDTL "
						+"WHERE REPORTINGDTL_EMP_ID = ?"; 
		
		case 2 : return "SELECT REPORTINGHDR_CODE, REPORTINGDTL_CODE "
						+"FROM HRMS_REPORTING_STRUCTUREDTL "
						+"WHERE REPORTINGDTL_ALTER_EMP_ID = ?";
		
		case 3 : return "INSERT INTO HRMS_REPORTING_HANDOVER (REPORTINGHDR_CODE, REPORTINGDTL_CODE, OLD_APPROVER, "
						+"NEW_APPROVER, APPROVER_TYPE, REPORTINGHDR_HANDOVER_DATE) VALUES (?, ?, ?, ?, ?, "
						+"TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'))";
		
		case 4 : return "UPDATE HRMS_REPORTING_STRUCTUREDTL SET REPORTINGDTL_EMP_ID = ? "
						+"WHERE REPORTINGDTL_EMP_ID = ?";
		
		case 5 : return "UPDATE HRMS_REPORTING_STRUCTUREDTL SET REPORTINGDTL_ALTER_EMP_ID = ? "
						+"WHERE REPORTINGDTL_ALTER_EMP_ID = ?";
		
		case 6 : return "SELECT REPORTINGDTL_CODE DTL_CODE, HRMS_REPORTING_STRUCTUREDTL.REPORTINGHDR_CODE HDR_CODE, "
						+"DEPT_NAME, CENTER_NAME, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME, RANK_NAME, "
						+"DECODE(REPORTINGHDR_TYPE, 'Leave','Leave','Requi','Requisition', 'Cash','Cash Voucher', 'Train', 'Training', "
						+"'Tran', 'Transfer', 'Sugg', 'Suggestion', 'Help', 'Help Desk', 'LTC', 'LTC Advance', 'Festi', 'Festival Advance', "
						+"'Other', 'Other Advance', 'Deput', 'Deputation', 'HBA', 'HBA', 'GPF', 'GPF', 'CTF', 'Children Tution Fee', "
						+"'Medi', 'Medical Advance', 'TYD', 'Travel', 'Appra', 'Appraisal', 'Confere', 'Conference', 'Loan', 'Loan', "
						+"'Asset', 'Asset', 'Purchase', 'Purchase')TYPE "
						+"FROM HRMS_REPORTING_STRUCTUREHDR "
						+"INNER JOIN HRMS_REPORTING_STRUCTUREDTL ON (HRMS_REPORTING_STRUCTUREDTL.REPORTINGHDR_CODE = HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_CODE ) "
						+"LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_DEPT_ID) "
						+"LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_BRN_ID) "
						+"LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_DESG_ID) "
						+"LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_EMP_ID) ";
						/*+"WHERE REPORTINGDTL_EMP_ID = 3 "
						+"ORDER BY HRMS_REPORTING_STRUCTUREDTL.REPORTINGHDR_CODE ASC, REPORTINGDTL_CODE ASC";*/
		
		case 7 : return "SELECT HRMS_REPORTING_STRUCTUREDTL.REPORTINGDTL_CODE, HRMS_REPORTING_HANDOVER.REPORTINGHDR_CODE HDR_CODE, DEPT_NAME, CENTER_NAME, "
						+"EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME, RANK_NAME, DECODE(REPORTINGHDR_TYPE, "
						+"'Leave','Leave','Requi','Requisition', 'Cash','Cash Voucher', 'Train', 'Training', "
						+"'Tran', 'Transfer', 'Sugg', 'Suggestion', 'Help', 'Help Desk', 'LTC', 'LTC Advance', "
						+"'Festi', 'Festival Advance', 'Other', 'Other Advance', 'Deput', 'Deputation', 'HBA', 'HBA', "
						+"'GPF', 'GPF', 'CTF', 'Children Tution Fee', 'Medi', 'Medical Advance', 'TYD', 'Travel', "
						+"'Appra', 'Appraisal', 'Confere', 'Conference', 'Loan', 'Loan', 'Asset', 'Asset', "
						+"'Purchase', 'Purchase')TYPE, DECODE(APPROVER_TYPE, 'M', 'Main Approver', 'A', 'Alternate Approver')APP_TYPE "
						+"FROM HRMS_REPORTING_HANDOVER " 
						+"INNER JOIN HRMS_REPORTING_STRUCTUREHDR ON (HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_CODE = HRMS_REPORTING_HANDOVER.REPORTINGHDR_CODE) "
						+"INNER JOIN HRMS_REPORTING_STRUCTUREDTL ON (HRMS_REPORTING_STRUCTUREDTL.REPORTINGDTL_CODE = HRMS_REPORTING_HANDOVER.REPORTINGDTL_CODE "
						+"AND HRMS_REPORTING_STRUCTUREDTL.REPORTINGHDR_CODE = HRMS_REPORTING_HANDOVER.REPORTINGHDR_CODE) "
						+"LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_DEPT_ID) "
						+"LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_BRN_ID) " 
						+"LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_DESG_ID) "
						+"LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_REPORTING_STRUCTUREHDR.REPORTINGHDR_EMP_ID) "
						+"WHERE OLD_APPROVER = ? AND REPORTINGHDR_HANDOVER_DATE = "
						+"(SELECT MAX(TO_DATE(TO_CHAR(REPORTINGHDR_HANDOVER_DATE, 'DD-MM-YYYY'), 'DD-MM-YYYY'))FROM HRMS_REPORTING_HANDOVER)";

		default : return "framework could not found the query";
		}
	}
}
