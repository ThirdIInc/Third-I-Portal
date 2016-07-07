package org.paradyne.model.payroll.pension;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.payroll.pension.ReleaseOnholdPension;
import org.paradyne.lib.ModelBase;

/**
 * @author REEBA_JOSEPH
 * 25 OCTOBER 2010
 *
 */

public class ReleaseOnholdPensionModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReleaseOnholdPensionModel.class);

	/**
	 * DISPLAY LIST OF PENSIONS UNDER ONHOLD
	 * @param onholdPension
	 * @param request
	 */
	public void viewOnholdEmployees(ReleaseOnholdPension onholdPension,
			HttpServletRequest request) {
		String empQuery = " SELECT PENS_EMP_ID, EMP_TOKEN, EMP_FNAME||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME, ' '), LEDGER_CODE "
			+ " FROM HRMS_PENSION_"+onholdPension.getYear()+" "
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PENSION_"+onholdPension.getYear()+".PENS_EMP_ID) "
			+ " INNER JOIN HRMS_PENSION_LEDGER ON (HRMS_PENSION_LEDGER.LEDGER_CODE = HRMS_PENSION_"+onholdPension.getYear()+".PENS_LEDGER_CODE) "
			+ " WHERE LEDGER_MONTH = "+onholdPension.getMonth()+" AND LEDGER_YEAR  = "+onholdPension.getYear()+" AND PENS_ONHOLD = 'Y' AND "
			+ " PENS_RELEASE_ONHOLD = 'N'";
		if(!onholdPension.getPensionempId().equals("")){
			empQuery +=" AND HRMS_EMP_OFFC.EMP_ID ="+onholdPension.getPensionempId();
		}
		Object[][] empObj = getSqlModel().getSingleResult(empQuery);
		if(empObj !=null && empObj.length > 0){
			onholdPension.setRecordsAvailable(true);
			ArrayList empList = new ArrayList();
			for (int i = 0; i < empObj.length; i++) {
				ReleaseOnholdPension bean = new ReleaseOnholdPension();
				bean.setEmpId(String.valueOf(empObj[i][0]));
				bean.setEmpToken(String.valueOf(empObj[i][1]));
				bean.setEmpName(String.valueOf(empObj[i][2]));
				bean.setLedgerCode(String.valueOf(empObj[i][3]));
				empList.add(bean);
			}
			onholdPension.setEmpList(empList);
		}else{
			onholdPension.setRecordsAvailable(false);
		}
		
	}//END OF METHOD VIEWONHOLDEMPLOYEES

	/**
	 * SAVE RELEASED OMHOLD PENSIONS
	 * @param onholdPension
	 * @param request
	 * @return
	 */
	public boolean updatePension(ReleaseOnholdPension onholdPension,
			HttpServletRequest request) {
		boolean result = false;
		try {
			String empId[] = request.getParameterValues("empId");
			//String month[] = request.getParameterValues("paidMonth");
			//String year[] = request.getParameterValues("paidYear");
			String releaseOnhold[] = request.getParameterValues("hiddenCode");
			String ledgerCode[] = request.getParameterValues("ledgerCode");
			
			if (empId != null && empId.length > 0) {
				//ADD DATA TO VECTOR AS OBJECT SIZE IS DYNAMIC
				Vector updateVector = new Vector();
				for (int i = 0; i < empId.length; i++) {
					if (releaseOnhold[i].trim().equals("Y")) {
						//ADD RELEASE ONHOLD FLAG, MONTH, YEAR, LEDGER CODE & EMP ID
						//TO VECTOR IF RELEASE ONHOLD FLAG IS Y
						updateVector.add(releaseOnhold[i]);
						updateVector.add(onholdPension.getPaidOnholdMonth());
						updateVector.add(onholdPension.getPaidOnholdYear());
						updateVector.add(ledgerCode[i]);
						updateVector.add(empId[i]);
					}
				}
				//CREATE OBJECT OF VECTOR SIZE
				Object[][] updateObj = new Object[updateVector.size() / 5][5];
				int counter = 0;
				for (int i = 0; i < updateVector.size() / 5; i++) {
					//ASSIGN VALUES FROM VECTOR TO OBJECT
					updateObj[i][0] = updateVector.get(counter++);//RELEASE ONHOLD
					updateObj[i][1] = updateVector.get(counter++);//PAID MONTH
					updateObj[i][2] = updateVector.get(counter++);//PAID YEAR
					updateObj[i][3] = updateVector.get(counter++);//LEDGER CODE
					updateObj[i][4] = updateVector.get(counter++);//EMP ID
				}
				String updateQuery = " UPDATE HRMS_PENSION_"+onholdPension.getYear()+" SET PENS_RELEASE_ONHOLD=?, PENS_PAID_IN_MONTH=?, "
					+ "PENS_PAID_IN_YEAR=? WHERE PENS_LEDGER_CODE=? AND PENS_EMP_ID=? ";
				result = getSqlModel().singleExecute(updateQuery,updateObj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}// end of try-catch block
		return result;
	}//END OF SAVE METHOD
}
