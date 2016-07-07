package org.paradyne.model.payroll.incometax;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.payroll.incometax.EmployeePreviousIncome;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class EmployeePreviousIncomeModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(EmployeePreviousIncomeModel.class);
	public void displayList(EmployeePreviousIncome employeePreviousIncome, HttpServletRequest request) {

		String listQuery = "SELECT PRE_INCOME_ID, NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' '), "
			+ " PRE_FROM_YEAR, PRE_TO_YEAR, PRE_EMP_ID "
			+ " FROM HRMS_PRE_EMPLOYER_INCOME "
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PRE_EMPLOYER_INCOME.PRE_EMP_ID) ";
		
		if (employeePreviousIncome.isGeneralFlag()) {
			listQuery += "WHERE  PRE_EMP_ID = "+employeePreviousIncome.getUserEmpId();
		}
			
		listQuery += " ORDER BY PRE_INCOME_ID ";
		Object[][] obj = getSqlModel().getSingleResult(listQuery);

		String[] pageIndex = Utility.doPaging(employeePreviousIncome.getMyPage(), obj.length, 20);
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

		if (pageIndex[4].equals("1"))
			employeePreviousIncome.setMyPage("1");

		ArrayList<Object> list = new ArrayList<Object>();
		if (obj != null && obj.length > 0) {
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				EmployeePreviousIncome bean = new EmployeePreviousIncome();

				bean.setPrevIncCodeItr(String.valueOf(obj[i][0]));
				bean.setEmployeeNameItr(String.valueOf(obj[i][1]));
				bean.setFromYearItr(String.valueOf(obj[i][2]));
				bean.setToYearItr((String.valueOf(obj[i][3])));
				list.add(bean);
			}

			employeePreviousIncome.setTotalRecords("" + obj.length);
		} else {
			employeePreviousIncome.setListLength("false");
		}

		if (list.size() > 0)
			employeePreviousIncome.setListLength("true");
		else
			employeePreviousIncome.setListLength("false");

		employeePreviousIncome.setIteratorlist(list);

	}
	
	public void setDetails(EmployeePreviousIncome employeePreviousIncome, String type) {
		/*String query = " SELECT PRE_INCOME_ID, NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' '), " 
			+ " HRMS_EMP_OFFC.EMP_TOKEN , HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME, PRE_FROM_YEAR, PRE_TO_YEAR, "
			+ " PRE_EMP_ID,PRE_BASIC_AMT, PRE_CONV_AMT, PRE_DA_AMT, PRE_HRA_AMT, PRE_OTHER_INCOME_AMT, "
			+ " PRE_PF_AMT, PRE_PT_AMT, PRE_TAX_PAID_AMT, PRE_NET_PAY_AMT "
			+ " FROM HRMS_PRE_EMPLOYER_INCOME "
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PRE_EMPLOYER_INCOME.PRE_EMP_ID) "
			+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
			+ " LEFT JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) ";*/
		String query = " SELECT PRE_INCOME_ID, NVL(EMP_FNAME,' ')||' '||NVL(EMP_MNAME,' ')||' '||NVL(EMP_LNAME,' '), "
				+ "HRMS_EMP_OFFC.EMP_TOKEN , HRMS_CENTER.CENTER_NAME, HRMS_RANK.RANK_NAME, PRE_FROM_YEAR, PRE_TO_YEAR, "
				+ "PRE_EMP_ID, "
				+ " NVL(PRE_PF_AMT,0), NVL(PRE_PT_AMT,0), NVL(PRE_TAX_PAID_AMT,0), NVL(PRE_TAXABLE_INCOME,0),NVL(SAL_PANNO,'') "
				+ " FROM HRMS_PRE_EMPLOYER_INCOME "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_PRE_EMPLOYER_INCOME.PRE_EMP_ID) "
				+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
				+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
				+ " LEFT JOIN HRMS_CENTER ON (HRMS_EMP_OFFC.EMP_CENTER = HRMS_CENTER.CENTER_ID) ";
		if(type.trim().equals("SEARCH"))
			query += " WHERE PRE_INCOME_ID ="+employeePreviousIncome.getPrevIncCode();
		else if(type.trim().equals("EDIT"))
			query += " WHERE PRE_INCOME_ID ="+employeePreviousIncome.getHiddencode();
		Object[][] data = getSqlModel().getSingleResult(query);
		/*employeePreviousIncome.setPrevIncCode(String.valueOf(data[0][0]));
		employeePreviousIncome.setEmpName(String.valueOf(data[0][1]));
		employeePreviousIncome.setEmpToken(String.valueOf(data[0][2]));
		employeePreviousIncome.setEmpCenter(String.valueOf(data[0][3]));
		employeePreviousIncome.setEmpRank(String.valueOf(data[0][4]));
		employeePreviousIncome.setFromYear(String.valueOf(data[0][5]));
		employeePreviousIncome.setToYear(String.valueOf(data[0][6]));
		employeePreviousIncome.setEmpID(String.valueOf(data[0][7]));
		employeePreviousIncome.setBasicAmount(String.valueOf(data[0][8]));
		employeePreviousIncome.setCaAmount(String.valueOf(data[0][9]));
		employeePreviousIncome.setDaAmount(String.valueOf(data[0][10]));
		employeePreviousIncome.setHraAmount(String.valueOf(data[0][11]));
		employeePreviousIncome.setOtherAmount(String.valueOf(data[0][12]));
		employeePreviousIncome.setPfAmount(String.valueOf(data[0][13]));
		employeePreviousIncome.setPtAmount(String.valueOf(data[0][14]));
		employeePreviousIncome.setTaxAmount(String.valueOf(data[0][15]));
		employeePreviousIncome.setNetAmount(String.valueOf(data[0][16]));*/
		employeePreviousIncome.setPrevIncCode(String.valueOf(data[0][0]));
		employeePreviousIncome.setEmpName(String.valueOf(data[0][1]));
		employeePreviousIncome.setEmpToken(String.valueOf(data[0][2]));
		employeePreviousIncome.setEmpCenter(String.valueOf(data[0][3]));
		employeePreviousIncome.setEmpRank(String.valueOf(data[0][4]));
		employeePreviousIncome.setFromYear(String.valueOf(data[0][5]));
		employeePreviousIncome.setToYear(String.valueOf(data[0][6]));
		employeePreviousIncome.setEmpID(String.valueOf(data[0][7]));		
		employeePreviousIncome.setPfAmount(String.valueOf(data[0][8]));
		employeePreviousIncome.setPtAmount(String.valueOf(data[0][9]));
		employeePreviousIncome.setTaxAmount(String.valueOf(data[0][10]));
		employeePreviousIncome.setNetAmount(String.valueOf(data[0][11]));
		employeePreviousIncome.setPanNo(checkNull(String.valueOf(data[0][12])));
		
	}
	
	public boolean deletecheckedRecords(
			EmployeePreviousIncome employeePreviousIncome, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					Object[][] deleteObj = new Object[1][1];
					deleteObj[0][0] = code[i];
					String deleteQuery = "DELETE HRMS_PRE_EMPLOYER_INCOME WHERE  PRE_INCOME_ID=?";
					result = getSqlModel().singleExecute(deleteQuery, deleteObj);
					if (!result)
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
	}

	public boolean savePreviousIncome(
			EmployeePreviousIncome employeePreviousIncome) {
		boolean result = false;
		
		String sqlQuery="SELECT * FROM HRMS_PRE_EMPLOYER_INCOME WHERE PRE_FROM_YEAR ="+employeePreviousIncome.getFromYear()+" AND PRE_EMP_ID = "+employeePreviousIncome.getEmpID();
		Object[][] duplicatObj = getSqlModel().getSingleResult(sqlQuery);
		if(duplicatObj!=null && duplicatObj.length>0)
		{
			return result;
		}
		else
		{
			String maxCodeQuery = "SELECT NVL(MAX(PRE_INCOME_ID),0)+1 FROM HRMS_PRE_EMPLOYER_INCOME";
			Object[][] maxCode = getSqlModel().getSingleResult(maxCodeQuery);
			Object[][] saveObj = new Object[1][8];
			saveObj[0][0] 	= String.valueOf(maxCode[0][0]);
			saveObj[0][1] 	= employeePreviousIncome.getEmpID();
			saveObj[0][2] 	= employeePreviousIncome.getFromYear();
			saveObj[0][3] 	= employeePreviousIncome.getToYear();
			saveObj[0][4] 	= employeePreviousIncome.getNetAmount();
			saveObj[0][5] 	= employeePreviousIncome.getTaxAmount();
			/*saveObj[0][4] 	= employeePreviousIncome.getBasicAmount();
			saveObj[0][5] 	= employeePreviousIncome.getCaAmount();
			saveObj[0][6] 	= employeePreviousIncome.getDaAmount();
			saveObj[0][7] 	= employeePreviousIncome.getHraAmount();
			saveObj[0][8] 	= employeePreviousIncome.getOtherAmount();*/
			saveObj[0][6] 	= employeePreviousIncome.getPfAmount();
			saveObj[0][7] 	= employeePreviousIncome.getPtAmount();
			
			
			/*String insertQuery = " INSERT INTO HRMS_PRE_EMPLOYER_INCOME (PRE_INCOME_ID, PRE_EMP_ID, PRE_FROM_YEAR, PRE_TO_YEAR, PRE_BASIC_AMT, "
					+ " PRE_CONV_AMT, PRE_DA_AMT, PRE_HRA_AMT, PRE_OTHER_INCOME_AMT, PRE_PF_AMT, PRE_PT_AMT, PRE_TAX_PAID_AMT, PRE_NET_PAY_AMT) "
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";*/
			String insertQuery = " INSERT INTO HRMS_PRE_EMPLOYER_INCOME (PRE_INCOME_ID, PRE_EMP_ID, PRE_FROM_YEAR, PRE_TO_YEAR, "
				+ " PRE_TAXABLE_INCOME,PRE_TAX_PAID_AMT, PRE_PF_AMT, PRE_PT_AMT) "
				+ " VALUES(?,?,?,?,?,?,?,?)";
			result = getSqlModel().singleExecute(insertQuery, saveObj);
		}
		
		
		return result;
	}

	public boolean updatePreviousIncome(
			EmployeePreviousIncome employeePreviousIncome) {
		boolean result = false;
		Object[][] updateObj = new Object[1][8];
		updateObj[0][0] 	= employeePreviousIncome.getEmpID();
		updateObj[0][1] 	= employeePreviousIncome.getFromYear();
		updateObj[0][2] 	= employeePreviousIncome.getToYear();
		updateObj[0][3] 	= employeePreviousIncome.getNetAmount();
		updateObj[0][4] 	= employeePreviousIncome.getTaxAmount();
		/*updateObj[0][3] 	= employeePreviousIncome.getBasicAmount();
		updateObj[0][4] 	= employeePreviousIncome.getCaAmount();
		updateObj[0][5] 	= employeePreviousIncome.getDaAmount();
		updateObj[0][6] 	= employeePreviousIncome.getHraAmount();
		updateObj[0][7] 	= employeePreviousIncome.getOtherAmount();*/
		updateObj[0][5] 	= employeePreviousIncome.getPfAmount();
		updateObj[0][6] 	= employeePreviousIncome.getPtAmount();
		
		
		updateObj[0][7] 	= employeePreviousIncome.getPrevIncCode();
		/*String updateQuery = "UPDATE HRMS_PRE_EMPLOYER_INCOME SET PRE_EMP_ID = ?, PRE_FROM_YEAR = ?, PRE_TO_YEAR = ?, PRE_BASIC_AMT = ?, "
				+ " PRE_CONV_AMT = ?, PRE_DA_AMT = ?, PRE_HRA_AMT = ?, PRE_OTHER_INCOME_AMT = ?, PRE_PF_AMT = ?, PRE_PT_AMT = ?, "
				+ " PRE_TAX_PAID_AMT = ?, PRE_NET_PAY_AMT = ?  WHERE PRE_INCOME_ID = ?";*/
		
		String updateQuery = "UPDATE HRMS_PRE_EMPLOYER_INCOME SET PRE_EMP_ID = ?, PRE_FROM_YEAR = ?, PRE_TO_YEAR = ?, "
			+ " PRE_TAXABLE_INCOME = ?,PRE_TAX_PAID_AMT = ?, PRE_PF_AMT = ?, PRE_PT_AMT = ? "
			+ "   WHERE PRE_INCOME_ID = ?";
		result = getSqlModel().singleExecute(updateQuery, updateObj);
		return result;
	}

	public boolean delete(EmployeePreviousIncome employeePreviousIncome) {
		Object deleteObj[][]=null;

		try {
			deleteObj = new Object[1][1];
			deleteObj[0][0] = employeePreviousIncome.getPrevIncCode();
		} catch (Exception e) {
			logger.error("Error in delete method : "+e);
		}
		String deleteQuery = "DELETE HRMS_PRE_EMPLOYER_INCOME WHERE  PRE_INCOME_ID=?";
		return getSqlModel().singleExecute(deleteQuery, deleteObj);
	}

	public EmployeePreviousIncome getEmployeeDetails(String userEmpId,
			EmployeePreviousIncome employeePreviousIncome) {
		Object[] beanObj = null;
		try {
			beanObj = new Object[1];
			beanObj[0] = userEmpId;
			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ "	HRMS_CENTER.CENTER_NAME,HRMS_RANK.RANK_NAME,HRMS_EMP_OFFC.EMP_ID,EMP_TYPE,HRMS_EMP_OFFC.EMP_GENDER,NVL(SAL_PANNO,'') "
				+ "	FROM HRMS_EMP_OFFC "
				+"	LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
				+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ " WHERE HRMS_EMP_OFFC.EMP_ID=?";
			Object[][] data = getSqlModel().getSingleResult(query,
					beanObj);
			for (int i = 0; i < data.length; i++) {
				employeePreviousIncome.setEmpToken(String.valueOf(data[i][0]));// employee
				// token
				employeePreviousIncome.setEmpName(String
						.valueOf(data[i][1]));// employee
				// name
				employeePreviousIncome
						.setEmpCenter(String.valueOf(data[i][2]));// branch
				employeePreviousIncome.setEmpRank(String
						.valueOf(data[i][3]));// rank
				employeePreviousIncome.setEmpID(String
						.valueOf(data[i][4]));// employee			
				// id
				employeePreviousIncome.setPanNo(checkNull(String
						.valueOf(data[i][7])));
			}// end of for loop
		} catch (Exception e) {
			logger.error("Exception in getEmployeeDetails------------" + e);
		}
		return employeePreviousIncome;
	}
	
	/*
	 * method name : checkNull purpose : check the string is null or not return
	 * type : String parameter : String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}

}
