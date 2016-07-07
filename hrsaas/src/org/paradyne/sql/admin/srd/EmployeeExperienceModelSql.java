package org.paradyne.sql.admin.srd;

import org.paradyne.lib.SqlBase;
/**
 * @author Prajakta.Bhandare
 * @date 23 Jan 2013
 */
public class EmployeeExperienceModelSql extends SqlBase {

	public String getQuery(int id) {
		switch (id) {

		case 1:
			//Insert Query of work Experience
			return 	"INSERT INTO HRMS_EMP_EXP(EXP_ID,EMP_ID,EXP_EMPLOYER,EXP_JOBTITLE,EXP_JOBDESC,EXP_JOINING_DATE," 
					+" EXP_RELIEVING_DATE,EXP_LASTSAL,EXP_SCALE_OF_PAY,EMP_PF_NUMBER)"
					+" VALUES (?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?,?,?)";
		case 2:
			//Update Query of Work Experience
			return 	"UPDATE HRMS_EMP_EXP SET EXP_EMPLOYER = ?,EXP_JOBTITLE =? ,EXP_JOBDESC =? ," 
					+" EXP_JOINING_DATE =TO_DATE(?,'DD-MM-YYYY'),EXP_RELIEVING_DATE =TO_DATE(?,'DD-MM-YYYY')," 
					+" EXP_LASTSAL = ?,EXP_SCALE_OF_PAY=?, EMP_PF_NUMBER=? WHERE EXP_ID =? AND EMP_ID=? ";

		case 3:
			//Select Query of Work Experience
			return 	"SELECT HRMS_EMP_EXP.EXP_ID,EXP_EMPLOYER,EXP_JOBTITLE,EXP_JOBDESC,"
					+" TO_CHAR(EXP_JOINING_DATE,'DD-MM-YYYY'),TO_CHAR(EXP_RELIEVING_DATE,'DD-MM-YYYY'),"
					+" EXP_LASTSAL,EXP_SCALE_OF_PAY,HRMS_EMP_EXP.EMP_PF_NUMBER"
					+" FROM HRMS_EMP_EXP"
					+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_EXP.EMP_ID)" 
					+" WHERE HRMS_EMP_EXP.EMP_ID =?";

		case 4:
			//Select Query for single record from work experience
			return "SELECT HRMS_EMP_EXP.EXP_ID,EXP_EMPLOYER,EXP_JOBTITLE,EXP_JOBDESC,"
					+" TO_CHAR(EXP_JOINING_DATE,'DD-MM-YYYY'),TO_CHAR(EXP_RELIEVING_DATE,'DD-MM-YYYY'),"
					+" EXP_LASTSAL,EXP_SCALE_OF_PAY,HRMS_EMP_EXP.EMP_PF_NUMBER"
					+" FROM HRMS_EMP_EXP"
					+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_EXP.EMP_ID)" 
					+" WHERE HRMS_EMP_EXP.EMP_ID =?AND HRMS_EMP_EXP.EXP_ID =?";
		case 5:
			//Select query of Project Details
			return "SELECT NVL(PROJECT_NAME,''), NVL(PROJECT_DESCRIPTION,''), NVL(PROJECT_DURATION,'')," 
					+" NVL(PROJECT_TEAMSIZE,''), NVL(PROJECT_ROLE,''), NVL(PROJECT_CODE,''), NVL(PROJECT_EXP_ID,'')" 
					+" FROM HRMS_EMP_EXP_PROJDTLS where PROJECT_EXP_ID=?";
			
		case 6:
			//Insert Query for project details
			return "INSERT INTO HRMS_EMP_EXP_PROJDTLS(PROJECT_CODE,PROJECT_EXP_ID,PROJECT_NAME,PROJECT_DURATION,"
					+" PROJECT_TEAMSIZE,PROJECT_ROLE,PROJECT_DESCRIPTION) VALUES((SELECT NVL(MAX(PROJECT_CODE)+1,0)"
					+" FROM HRMS_EMP_EXP_PROJDTLS),?,?,?,?,?,?)";


		case 7: 
			//Update query for project details
			return "UPDATE HRMS_EMP_EXP_PROJDTLS SET PROJECT_NAME = ?,PROJECT_DURATION =? ,PROJECT_TEAMSIZE =?,"
					+" PROJECT_ROLE =?,PROJECT_DESCRIPTION = ? WHERE PROJECT_EXP_ID =? AND PROJECT_CODE =?";
		
			
		case 8:
			// Delete query for project details
			return "DELETE FROM HRMS_EMP_EXP_PROJDTLS WHERE PROJECT_EXP_ID =? AND PROJECT_CODE=? ";
			
		
		case 9:
			//Delete query for work experience
			return "DELETE FROM HRMS_EMP_EXP WHERE EXP_ID = ?";
			
		case 10:
			//select Query for single record from Project Details
			return "SELECT NVL(PROJECT_NAME,''), NVL(PROJECT_DESCRIPTION,''), NVL(PROJECT_DURATION,''),NVL(PROJECT_TEAMSIZE,''),"
					+" NVL(PROJECT_ROLE,''), NVL(PROJECT_CODE,''), NVL(PROJECT_EXP_ID,'') "
					+" FROM HRMS_EMP_EXP_PROJDTLS WHERE PROJECT_CODE=? AND PROJECT_EXP_ID=? ";
		
		case 11:
			//Delete query to delete all project details for particular experience
			return "DELETE FROM HRMS_EMP_EXP_PROJDTLS WHERE PROJECT_EXP_ID =?";
		default:
			
			return "Query not found";
		}
	}
}
