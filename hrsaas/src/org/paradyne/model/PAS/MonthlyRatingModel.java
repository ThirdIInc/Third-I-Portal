package org.paradyne.model.PAS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.bean.PAS.MonthlyRating;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.struts.action.DataMigration.MigrateExcelData;

/**
 * @author REEBA_JOSEPH
 * 22 OCTOBER 2010
 *
 */

public class MonthlyRatingModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MonthlyRatingModel.class);
	
	/**
	 * Display list of employees reporting to logged user
	 * @param monthlyRating
	 * @param request
	 */
	public void viewEmployeeList(MonthlyRating monthlyRating,
			HttpServletRequest request) {
		//QUERY TO RETRIEVE ALL EMPLOYEES REPORTING TO THE USER LOGGED IN
		String empQuery = "SELECT EMP_ID, EMP_TOKEN, EMP_FNAME||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME, ' ') "
			+ " FROM HRMS_EMP_OFFC "
			+ " WHERE EMP_REPORTING_OFFICER = "+monthlyRating.getUserEmpId()
			+ " AND EMP_STATUS = 'S' ORDER BY UPPER(EMP_FNAME||' '||NVL(EMP_LNAME, ' '))";
		Object[][] empObj = getSqlModel().getSingleResult(empQuery);
		
		//QUERY TO RETRIEVE SAVED RATINGS OF THE EMPLOYEE
		String savedRatings = " SELECT RATING_EMP_ID, RATING FROM HRMS_EMP_MONTHLY_RATING " 
			+ " WHERE RATING_MONTH="+monthlyRating.getMonth()+" AND RATING_YEAR="+monthlyRating.getYear()
			+ " AND RATING_GIVEN_BY="+monthlyRating.getUserEmpId();
		Object[][] savedRatingObj = getSqlModel().getSingleResult(savedRatings);
		
		if(empObj != null && empObj.length > 0){
			monthlyRating.setRecordsAvailable(true);
			ArrayList list = new ArrayList();
			for (int i = 0; i < empObj.length; i++) {
				MonthlyRating bean = new MonthlyRating();
				bean.setEmpId(String.valueOf(empObj[i][0]));
				bean.setEmpToken(String.valueOf(empObj[i][1]));
				bean.setEmpName(String.valueOf(empObj[i][2]));
				if(savedRatingObj != null && savedRatingObj.length > 0){
					for (int j = 0; j < savedRatingObj.length; j++) {
						//COMPARE EMP ID IN HRMS_EMP_MONTHLY_RATING WITH EMP ID IN HRMS_EMP_OFFC
						if(String.valueOf(empObj[i][0]).equals(String.valueOf(savedRatingObj[j][0]))){
							//IF EMP IDS MATCH SET THE CORRESPONDING RATING
							bean.setRating(Utility.checkNull(String.valueOf(savedRatingObj[j][1])));
						}
					}
				}else
					bean.setRating("");
				list.add(bean);
			}
			monthlyRating.setEmpList(list);
		}else{
			monthlyRating.setRecordsAvailable(false);
		}
		
	}// end of method viewEmployeeList

	/**
	 * Save entered ratings of the employees
	 * @param monthlyRating
	 * @param request
	 * @return
	 */
	public boolean saveRatings(MonthlyRating monthlyRating, HttpServletRequest request) {
		boolean result = false;
		try {
			String empId[] = request.getParameterValues("empId");
			String rating[] = request.getParameterValues("rating");
			
			if (empId != null && empId.length > 0) {
				//DELETE ENTRIES
				String deleteQuery = "DELETE FROM HRMS_EMP_MONTHLY_RATING WHERE RATING_MONTH="
						+ monthlyRating.getMonth()
						+ " AND RATING_YEAR = "
						+ monthlyRating.getYear()
						+ " AND RATING_GIVEN_BY = "+monthlyRating.getUserEmpId();
				getSqlModel().singleExecute(deleteQuery);

				//ADD DATA TO VECTOR AS OBJECT SIZE IS DYNAMIC
				Vector insertVector = new Vector();
				for (int i = 0; i < empId.length; i++) {
					if (!rating[i].trim().equals("")) {
						//ADD EMP ID & RATING TO VECTOR IF RATING IS NOT BLANK
						insertVector.add(empId[i]);
						insertVector.add(rating[i]);
					}
				}
				//CREATE OBJECT OF VECTOR SIZE
				Object[][] insertObj = new Object[insertVector.size() / 2][2];
				int counter = 0;
				for (int i = 0; i < insertVector.size() / 2; i++) {
					//ASSIGN VALUES FROM VECTOR TO OBJECT
					insertObj[i][0] = insertVector.get(counter++);
					insertObj[i][1] = insertVector.get(counter++);
				}
				//INSERT QUERY
				String insertQuery = " INSERT INTO HRMS_EMP_MONTHLY_RATING (RATING_EMP_ID, RATING_MONTH, RATING_YEAR, RATING_GIVEN_BY, "
						+ " RATING_DATE, RATING) VALUES (?, "
						+ monthlyRating.getMonth()
						+ ", "
						+ monthlyRating.getYear()
						+ ", "
						+ monthlyRating.getUserEmpId() + ", SYSDATE, ?) ";
				result = getSqlModel().singleExecute(insertQuery, insertObj);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//end of method saveRatings
	
	public void uploadMonthlyRating(MonthlyRating bean) {
		String filePath = bean.getDataPath() + bean.getUploadFileName();
		MigrateExcelData.getFile(filePath);
		
		/**
		 * Get column numbers with mandatory information
		 */
		HashMap<Integer, Boolean> columnInformation = MigrateExcelData.isColumnsMandatory();
		Object[][] empOffcData = getSqlModel().getSingleResult("SELECT EMP_ID,TRIM(EMP_TOKEN) FROM HRMS_EMP_OFFC");
		Object[][] empIdObj = MigrateExcelData.uploadExcelData(1, empOffcData, MigrateExcelData.MASTER_TYPE, columnInformation.get(1));
		Object[][] empNameObj = MigrateExcelData.uploadExcelData(2, null, MigrateExcelData.STRING_TYPE, columnInformation.get(2));
		Object[][] monthObj = MigrateExcelData.uploadExcelData(3, null, MigrateExcelData.NUMBER_INTEGER_TYPE, columnInformation.get(3));
		Object[][] yearObj = MigrateExcelData.uploadExcelData(4, null, MigrateExcelData.NUMBER_INTEGER_TYPE, columnInformation.get(4));
		Object[][] ratingObj = MigrateExcelData.uploadExcelData(5, null, MigrateExcelData.NUMBER_DOUBLE_TYPE, columnInformation.get(5));
		Object[][] managerIdObj = MigrateExcelData.uploadExcelData(6, empOffcData, MigrateExcelData.MASTER_TYPE, columnInformation.get(6));
		Object[][] managerNameObj = MigrateExcelData.uploadExcelData(1, null, MigrateExcelData.STRING_TYPE, columnInformation.get(7));
		
		boolean res = MigrateExcelData.isFileToBeUploaded();

		if(res) {
			Object[][] insertRatingObj = new Object[empIdObj.length][5];
			Object[][] deleteRatingObj = new Object[empIdObj.length][3];

			for(int i = 0; i < insertRatingObj.length; i++) {
				insertRatingObj[i][0] = empIdObj[i][0];
				insertRatingObj[i][1] = monthObj[i][0];
				insertRatingObj[i][2] = yearObj[i][0];
				insertRatingObj[i][3] = ratingObj[i][0];
				insertRatingObj[i][4] = managerIdObj[i][0];
				deleteRatingObj[i][0] = empIdObj[i][0];
				deleteRatingObj[i][1] = monthObj[i][0];
				deleteRatingObj[i][2] = yearObj[i][0];
			}
			String deleteRatingQuery = "DELETE HRMS_EMP_MONTHLY_RATING WHERE RATING_EMP_ID =? AND RATING_MONTH=? AND RATING_YEAR=?";
			String insertRatingQuery = "INSERT INTO HRMS_EMP_MONTHLY_RATING ( RATING_EMP_ID, RATING_MONTH, RATING_YEAR, RATING,"
					+" RATING_GIVEN_BY ,RATING_DATE ) VALUES ( ?, ?, ?, ?, ?, SYSDATE)";
			Vector dataVector = new Vector();
			Object sqlQuery[] = new Object[]{deleteRatingQuery,insertRatingQuery};
			
			dataVector.add(deleteRatingObj);
			dataVector.add(insertRatingObj);

			boolean insertResult = getSqlModel().multiExecute(sqlQuery, dataVector);

			if(insertResult) {
				bean.setStatus("Success");
			} else {
				bean.setStatus("Fail");
				bean.setNote("Duplicate records found. Please verify the data in the sheet and data in the system to remove the duplicate records. "
					+ "Upload the sheet again to transfer the data.");
			}
		} else {
			bean.setStatus("Fail");
			bean.setNote("Please download the attached sheet to verify and resolve the integrity issues. Upload the sheet again to transfer the records.");
		}
		bean.setUploadName("monthRating");
	}
	

}
