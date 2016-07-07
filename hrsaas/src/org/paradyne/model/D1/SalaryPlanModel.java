package org.paradyne.model.D1;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.ImprintTypeBean;
import org.paradyne.bean.D1.SalaryPlanBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class SalaryPlanModel extends ModelBase {

	/** Generating the list on Onload */
	public void initialData(SalaryPlanBean salPlanBean,
			HttpServletRequest request) {

		

		Object[][] regData = getSqlModel().getSingleResult(getQuery(3));

		if (regData != null && regData.length > 0) {
			salPlanBean.setModeLength("true");

			salPlanBean
					.setTotalNoOfRecords(String.valueOf(regData.length));

			String[] pageIndex = Utility.doPaging(salPlanBean.getMyPage(),
					regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";

			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				salPlanBean.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				SalaryPlanBean bean = new SalaryPlanBean();
				bean.setSalCode(checkNull(String.valueOf(regData[i][0])));
				bean.setZipCode(checkNull(String.valueOf(regData[i][1])));

				List.add(bean);
			}// end of loop
			System.out.println("in main page---" + List.size());
			salPlanBean.setSalaryPlanList(List);
		}

		else {

			salPlanBean.setSalaryPlanList(null);

		}
	}

	//Check Null Function//	

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}
	
	/**For inserting record into DB 
	 * @param pinCodes */
	public boolean insertData(SalaryPlanBean salPlanBean) {
		
		if (!checkDuplicateAdd(salPlanBean)) {
		
		Object addObj[][] = new Object[1][3];
	
		String query1 = "SELECT NVL(MAX(ZIP_ID),0)+1 FROM  HRMS_D1_HIRE_ZIP";
		Object[][] salId = getSqlModel().getSingleResult(query1);
		
		addObj[0][0] = checkNull(String.valueOf(salId[0][0]));
		addObj[0][1] = salPlanBean.getZipCode();
		addObj[0][2] = salPlanBean.getSalaryPlan().trim();
		
		/*for (int i = 0; i < addObj.length; i++) {
			for (int j = 0; j < addObj[i].length; j++) {
				System.out.println("insertObj[" + i + "][" + j + "]  " + addObj[i][j]);
			}
		}*/

		salPlanBean.setSalCode(String.valueOf(salId[0][0]));
		
		return getSqlModel().singleExecute(getQuery(1), addObj);
	}

		else{
			
		return false;
		}		
		}
	
	/* for checking duplicate entry of record during insertion*/

	public boolean checkDuplicateAdd(SalaryPlanBean bean) {
		boolean result = false;
		String query = "SELECT ZIP_CODE FROM HRMS_D1_HIRE_ZIP WHERE UPPER(ZIP_CODE) LIKE '"
				+ bean.getZipCode().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}
		
	/**  Modifing the record */
	public boolean updateData(SalaryPlanBean bean) {

		
		Object updateObj[][] = new Object[1][3];

		updateObj[0][0] =bean.getZipCode();
		updateObj[0][1] = bean.getSalaryPlan().trim();
		updateObj[0][2] = bean.getSalCode().trim();
		return getSqlModel().singleExecute(getQuery(2), updateObj);

	
	}
	
	/* for checking duplicate entry of record during updation*/
	public boolean checkDuplicateUpdate(SalaryPlanBean bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_D1_HIRE_ZIP WHERE UPPER(ZIP_CODE) LIKE '"
				+ bean.getZipCode().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	} 
		
	/**
	 * for selecting the data from list and setting those data in respective
	 * fields
	 */
	public void editSalaryplan(SalaryPlanBean salPlanBean,
			HttpServletRequest request) {

		try {
			System.out
					.println("salPlanBean.getSalCode() here we get in calForEdit is-------------"
							+ salPlanBean.getSalCode());
			String query = " SELECT ZIP_CODE,SALARY_PLAN FROM HRMS_D1_HIRE_ZIP WHERE  ZIP_ID= "
					+ salPlanBean.getSalCode();
			Object[][] data = getSqlModel().getSingleResult(query);
			salPlanBean.setZipCode(String.valueOf(data[0][0]));
			salPlanBean.setSalaryPlan(String.valueOf(data[0][1]));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public boolean deleteCheckedRecords(SalaryPlanBean  bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(4), delete);
					if (!result)
						count++;

				}// end of if
			}// end of for loop
		}// end of if
		if (count != 0) {
			result = false;
			return result;
		}// end of if
		else {
			result = true;
			return result;
		}// end of else		
	}

	public boolean delete(SalaryPlanBean salPlanBean,	HttpServletRequest request) {
		boolean result = false;
		System.out
				.println("salPlanBean.getSalCode() in delete method call ======"
						+ salPlanBean.getSalCode());
		String deleteId = salPlanBean.getSalCode();

		String delQuery = "DELETE FROM HRMS_D1_HIRE_ZIP WHERE ZIP_ID="
				+ deleteId;
		result = getSqlModel().singleExecute(delQuery);
		return result;
	}
	
	
	
	
	
	
}
