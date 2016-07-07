package org.paradyne.model.payroll;

import java.util.ArrayList;

import org.paradyne.bean.payroll.AllowancePay;
import org.paradyne.lib.ModelBase;

public class AllowancePayModel extends ModelBase {

	public boolean addToList(AllowancePay allowancePay) {
		// TODO Auto-generated method stub
		boolean falg = false;
		try {

			Object insertObj[][] = new Object[1][7];

			insertObj[0][0] = allowancePay.getEmployeeCode().trim();
			insertObj[0][1] = allowancePay.getCreditCode().trim();
			insertObj[0][2] = allowancePay.getMonth().trim();
			insertObj[0][3] = allowancePay.getYear().trim();
			insertObj[0][4] = allowancePay.getCreditAmount().trim();
			insertObj[0][5] = allowancePay.getRemarks().trim();
			insertObj[0][6] = allowancePay.getProcessingDate().trim();

			String insertQuery = "	INSERT INTO HRMS_ALLOWANCE ( EMP_ID, CREDIT_CODE, ALLOWANCE_MONTH, ALLOWANCE_YEAR, CREDIT_AMOUNT, "
					+ " ALLOWANCE_REMARKS, ALLOWANCE_DATE ,ALLOWANCE_CODE ) VALUES ( ?, ?, ?, ?, ?, ?, TO_DATE(?,'DD-MM-YYYY'),( SELECT NVL(MAX(ALLOWANCE_CODE),0)+1 FROM HRMS_ALLOWANCE)) ";

			falg = getSqlModel().singleExecute(insertQuery, insertObj);

			if (falg) {
				displayIttValues(allowancePay,allowancePay.getMonth().trim(),allowancePay.getYear().trim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return falg;
	}

	public boolean displayIttValues(AllowancePay allowancePay,String month,String year) {
		
		boolean result = false ;
		try {
			String selectQuery = " SELECT HRMS_ALLOWANCE.EMP_ID,NVL(emp_token,''),EMP_FNAME||'  '||EMP_MNAME||' '||EMP_LNAME,HRMS_ALLOWANCE.CREDIT_CODE,HRMS_CREDIT_HEAD.CREDIT_NAME, "
					+ " CREDIT_AMOUNT,ALLOWANCE_REMARKS ,ALLOWANCE_CODE ,ALLOWANCE_MONTH,ALLOWANCE_YEAR "
					+ "  FROM HRMS_ALLOWANCE "
					+ "  INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =HRMS_ALLOWANCE.EMP_ID) "
					+ " 	INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE =HRMS_ALLOWANCE.CREDIT_CODE ) "
					+" WHERE ALLOWANCE_MONTH="+month+" AND ALLOWANCE_YEAR="+year
					+" ORDER BY HRMS_ALLOWANCE.EMP_ID ";


			Object data[][] = getSqlModel().getSingleResult(selectQuery);

			ArrayList list = new ArrayList();
			if (data != null && data.length > 0) {
				for (int i = 0; i < data.length; i++) {
					AllowancePay bean = new AllowancePay();
					bean.setEmployeeId(checkNull(String.valueOf(data[i][0])));
					bean.setEmpToken(checkNull(String.valueOf(data[i][1])));
					bean.setEmpName(checkNull(String.valueOf(data[i][2])));
					bean
							.setCreditCodeItt(checkNull(String
									.valueOf(data[i][3])));
					bean
							.setCreditNameItt(checkNull(String
									.valueOf(data[i][4])));
					bean.setCreditAmountItt(checkNull(String
							.valueOf(data[i][5])));
					bean.setRemarksItt(checkNull(String.valueOf(data[i][6])));

					bean
							.setAllowanceCode(checkNull(String
									.valueOf(data[i][7])));
					
					

					bean
							.setMonthItt(checkNull(String
									.valueOf(data[i][8])));

					

					bean
							.setYearItt(checkNull(String
									.valueOf(data[i][9])));


					list.add(bean);
					
					result =true;
					allowancePay.setShowFlag("true");
				}

				allowancePay.setList(list);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public boolean delete(AllowancePay allowancePay) {
		// TODO Auto-generated method stub

		boolean result = false;
		try {
			String deleteQuery = " DELETE FROM HRMS_ALLOWANCE WHERE ALLOWANCE_CODE="
					+ allowancePay.getHiddenEdit().trim();

			result = getSqlModel().singleExecute(deleteQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	 
}
