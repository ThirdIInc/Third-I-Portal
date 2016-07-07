package org.paradyne.model.payroll.salary;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Recruitment.OpenVacReport;
import org.paradyne.bean.payroll.TaxInv;
import org.paradyne.bean.payroll.salary.ReimbursementConfiguration;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.struts.action.payroll.salary.ReimbursementConfigurationAction;

public class ReimbursementConfigurationModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ReimbursementConfigurationModel.class);

	public void getAccountantsList(ReimbursementConfiguration reimburseBean,
			HttpServletRequest request) {
		
		try {
			String accountantsQuery = " SELECT  EMP_FNAME||' '||EMP_LNAME, DIV_NAME, ACCOUNTANT_EMP_ID, ACCOUNTANT_DIV_ID "
					+ " FROM HRMS_CASH_CONFIG "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_CASH_CONFIG.ACCOUNTANT_EMP_ID) "
					+ " INNER JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_CASH_CONFIG.ACCOUNTANT_DIV_ID) ";
			
			Object data[][] = getSqlModel().getSingleResult(accountantsQuery);
			String[] pageIndex = Utility.doPaging(reimburseBean.getMyPage(),
					data.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1")) {
				reimburseBean.setMyPage("1");
			}
			ArrayList<Object> accountantList = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				ReimbursementConfiguration bean1 = new ReimbursementConfiguration();
				bean1.setAccountantEmpNameItt(String.valueOf(data[i][0]));//Accountant Emp Name
				bean1.setAccountantDivNameItt(String.valueOf(data[i][1]));//Division name
				bean1.setAccountantEmpIdItt(String.valueOf(data[i][2]));//Accountant Emp Id
				bean1.setAccountantDivIdItt(String.valueOf(data[i][3]));//Division Id
				accountantList.add(bean1);
			}
			reimburseBean.setAccountantListArray(accountantList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void callForEdit(ReimbursementConfiguration reimburseBean,
			String empCode, String divId) {
		try {
			Object addObj[] =new Object[2];
			addObj[0] = empCode;
			addObj[1] = divId;
			Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);
			
			reimburseBean.setAccountantEmpName(String.valueOf(data[0][1]));
			reimburseBean.setAccountantDivName(String.valueOf(data[0][2]));
			reimburseBean.setAccountantEmpId(String.valueOf(data[0][3]));
			reimburseBean.setAccountantDivId(String.valueOf(data[0][4]));
			reimburseBean.setHiddenEmpId(String.valueOf(data[0][3]));
			reimburseBean.setHiddenDivId(String.valueOf(data[0][4]));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean addAccountDivision(ReimbursementConfiguration reimburseBean) {
		boolean result = false;
		try {
			
			Object addAccountantObj[][] = new Object[1][2];
			addAccountantObj[0][0] = reimburseBean.getAccountantEmpId();//Id 
			addAccountantObj[0][1] = reimburseBean.getAccountantDivId();//Division id 
			
			result = getSqlModel().singleExecute(getQuery(1), addAccountantObj);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public void getAccountantDtl(ReimbursementConfiguration reimburseBean,
			HttpServletRequest request) {
		
		try {
			Object accountantIdObj[] = new Object[2];
			accountantIdObj[0] = reimburseBean.getHiddenEmpId();
			accountantIdObj[1] = reimburseBean.getHiddenDivId();
			logger.info("############ DIV ID ########"+String.valueOf(accountantIdObj[1]));
			Object[][] data = getSqlModel().getSingleResult(getQuery(4), accountantIdObj);
			reimburseBean.setAccountantEmpToken(String.valueOf(data[0][0]));
			reimburseBean.setAccountantEmpName(String.valueOf(data[0][1]));
			reimburseBean.setAccountantDivName(String.valueOf(data[0][2]));
			reimburseBean.setAccountantEmpId(String.valueOf(data[0][3]));
			reimburseBean.setAccountantDivId(String.valueOf(data[0][4]));
			reimburseBean.setHiddenEmpId(String.valueOf(data[0][3]));
			reimburseBean.setHiddenDivId(String.valueOf(data[0][4]));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public boolean modAccountantDetails(ReimbursementConfiguration reimburseBean) {
		
		boolean result = false;
		try {
			
			Object addAccountantObj[][] = new Object[1][3];
			addAccountantObj[0][0] = reimburseBean.getAccountantDivId();//Division id
			addAccountantObj[0][1] = reimburseBean.getHiddenEmpId();//Emp Id 
			addAccountantObj[0][2] = reimburseBean.getHiddenDivId();//Div Id 
			
			result = getSqlModel().singleExecute(getQuery(2), addAccountantObj);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
		
	}

	public boolean deleteAccountantDivision(
			ReimbursementConfiguration reimburseBean) {
		boolean result = false;
		try {
			Object addAccountantObj[][] = new Object[1][2];
			addAccountantObj[0][0] = reimburseBean.getHiddenEmpId();//Emp Id 
			addAccountantObj[0][1] = reimburseBean.getHiddenDivId();//Div Id
			result = getSqlModel().singleExecute(getQuery(3), addAccountantObj);
		} catch (Exception e) {
			// TODO: handle exception
		} 
		return result;
	}

	public boolean deleteCheckedRecords(
			ReimbursementConfiguration reimburseBean, String[] accntId, String[] divisionId) {
		int count=0;
		boolean result=false;
		
		
		try {
			for (int i = 0; i < accntId.length; i++) {
				if (!(accntId[i].equals("") && divisionId[i].equals(""))) {

					Object[][] delete = new Object[1][2];
					delete[0][0] = accntId[i];
					delete[0][1] = divisionId[i];
					logger.info("************** accnt id ************"+String.valueOf(delete[0][0]));
					logger.info("************** div id ************"+String.valueOf(delete[0][1]));
					result = getSqlModel().singleExecute(getQuery(3), delete);
					if (!result) {
						count++;
					}
				}
			}
			if (count != 0) {
				result = false;
				return result;
			} else {
				result = true;
				return result;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
