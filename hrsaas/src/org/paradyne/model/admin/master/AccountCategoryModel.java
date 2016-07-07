package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.admin.master.AccountCategoryMaster;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * 
 * @author Dilip Kumar
 * Date:20/09/09
 */
public class AccountCategoryModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	/**
	 * to Save the record
	 * 
	 * @param accountCategory
	 * @return
	 */
	public boolean addListData(AccountCategoryMaster accountCategory) {
		// TODO Auto-generated method stub
		if (!checkDuplicate(accountCategory)) {

			String query = "SELECT NVL(MAX(ACCOUNT_CATEGORY_ID),0)+1 FROM  HRMS_ACCOUNTING_CATEGORY";
			Object[][] acct = getSqlModel().getSingleResult(query);
			accountCategory.setAccountID(String.valueOf(acct[0][0]));

			Object[][] data = new Object[1][3];
			data[0][0] = accountCategory.getAccountAbbreviation().trim();
			data[0][1] = accountCategory.getAccountName().trim();
			data[0][2] =accountCategory.getIsActive();
			String query1 = "INSERT INTO HRMS_ACCOUNTING_CATEGORY (ACCOUNT_CATEGORY_ID, ACCOUNT_CATEGORY_ABBR, ACCOUNT_CATEGORY_NAME,IS_ACTIVE)"
					+ "  values((SELECT NVL(MAX(ACCOUNT_CATEGORY_ID),0)+1 FROM  HRMS_ACCOUNTING_CATEGORY),?,?,?)";
			return getSqlModel().singleExecute(query1, data);
		} else {

			return false;
		}
	}

	/*
	 * to Modify the record
	 * 
	 */
	public boolean modListData(AccountCategoryMaster accountCategory) {
		// TODO Auto-generated method stub

		if (!checkDuplicateMod(accountCategory)) {
			Object modData[][] = new Object[1][4];
			modData[0][0] = accountCategory.getAccountName().trim();
			modData[0][1] = accountCategory.getAccountAbbreviation().trim();
			modData[0][2] = accountCategory.getIsActive();
			modData[0][3] = accountCategory.getAccountID().trim();
			String Updatequery = "UPDATE HRMS_ACCOUNTING_CATEGORY SET ACCOUNT_CATEGORY_NAME=?,ACCOUNT_CATEGORY_ABBR=?,IS_ACTIVE=? WHERE ACCOUNT_CATEGORY_ID=?";
			return getSqlModel().singleExecute(Updatequery, modData);
		}

		else {
			return false;
		}
	}

	/**
	 * To Delete the record in the List
	 * 
	 * @param accountCategory
	 * @return
	 */
	public boolean deleteListData(AccountCategoryMaster accountCategory) {
		// TODO Auto-generated method stub
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = accountCategory.getAccountID();
		return getSqlModel().singleExecute(getQuery(3), delObj);
	}

	/**
	 * To Delete multiple record in the list
	 * 
	 * @param accountCategory
	 * @param code
	 * @return
	 */
	public boolean deleteAccount(AccountCategoryMaster accountCategory,
			String[] code) {
		// TODO Auto-generated method stub
		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					logger
							.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"
									+ code[i]);
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					flag = getSqlModel().singleExecute(getQuery(3), delete);
					if (!flag) {
						cnt++;
					}
					// result=true;
				}// end of nested if
			}// end of loop
		}// end of if
		if (cnt > 0) {
			result = false;
		}// end of if
		else
			result = true;
		return result;

	}
	/**
	 *  to modify the record in double click in the list
	 * @param accountCategory
	 */

	public void calforedit(AccountCategoryMaster accountCategory) {
		// TODO Auto-generated method stub

		String query = "SELECT  ACCOUNT_CATEGORY_NAME,ACCOUNT_CATEGORY_ABBR ,ACCOUNT_CATEGORY_ID,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') from HRMS_ACCOUNTING_CATEGORY"
				+ " WHERE ACCOUNT_CATEGORY_ID="
				+ accountCategory.getAccountID()
				+ " ORDER BY ACCOUNT_CATEGORY_ID ";

		Object[][] data = getSqlModel().getSingleResult(query);
		accountCategory.setAccountName(String.valueOf(data[0][0]));
		accountCategory.setAccountAbbreviation(String.valueOf(data[0][1]));
		accountCategory.setAccountID(String.valueOf(data[0][2]));
		accountCategory.setIsActive(String.valueOf(data[0][3]));

	}

	/**
	 * to check the Duplicate value or data
	 * 
	 */
	public boolean checkDuplicate(AccountCategoryMaster accountCategory) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_ACCOUNTING_CATEGORY WHERE UPPER(ACCOUNT_CATEGORY_NAME) LIKE '"
				+ accountCategory.getAccountName().trim().toUpperCase() + "' OR UPPER(ACCOUNT_CATEGORY_ABBR) LIKE '"
				+ accountCategory.getAccountAbbreviation().trim().toUpperCase() + "'";
		Object obj[][] = getSqlModel().getSingleResult(query);
		if (obj != null && obj.length > 0) {
			return true;
		}
		return result;
	}

	/**
	 * to check the Modified Duplicate value or data
	 * 
	 */
	public boolean checkDuplicateMod(AccountCategoryMaster accountCategory) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_ACCOUNTING_CATEGORY WHERE UPPER(ACCOUNT_CATEGORY_NAME) LIKE '"
				+ accountCategory.getAccountName().trim().toUpperCase()
				+ "' AND UPPER(ACCOUNT_CATEGORY_ABBR) LIKE '"
				+ accountCategory.getAccountAbbreviation().trim().toUpperCase() + "' AND ACCOUNT_CATEGORY_ID not in ("
				+ accountCategory.getAccountID() + ")";
		Object obj[][] = getSqlModel().getSingleResult(query);
		if (obj != null && obj.length > 0) {
			return true;
		}
		return result;
	}

	/**
	 * To Display the Record list in onload
	 * 
	 * @param accountCategory
	 * @param request
	 */
	public void listData(AccountCategoryMaster accountCategory,
			HttpServletRequest request) {
		// TODO Auto-generated method stub

		Object[][] repData = getSqlModel().getSingleResult(getQuery(5));
		ArrayList<Object> List = new ArrayList<Object>();
		if (repData != null && repData.length > 0) {
			accountCategory.setAccountLength("true");
			accountCategory.setTotalRecords(String.valueOf(repData.length));

			String[] pageIndex = Utility.doPaging(accountCategory.getMyPage(),
					repData.length, 20);
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
				accountCategory.setMyPage("1");
			
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				AccountCategoryMaster bean1 = new AccountCategoryMaster();
				bean1.setAccountID(checkNull(String.valueOf(repData[i][0])));
				bean1.setAccountName(checkNull(String.valueOf(repData[i][1])));
				bean1.setAccountAbbreviation(checkNull(String.valueOf(repData[i][2])));
				bean1.setIsActive(checkNull(String.valueOf(repData[i][3])));

				List.add(bean1);
			}// end of loop

			accountCategory.setAccountList(List);

		}
		else{
			accountCategory.setAccountLength("false");
			accountCategory.setTotalRecords("0");
			accountCategory.setAccountList(List);

		}

	}

	/**
	 * To check null value
	 * 
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}
/** to set the value  after click on search button
 * 
 * @param accountCategory
 * @return
 */
	public boolean data(AccountCategoryMaster accountCategory) {
		// TODO Auto-generated method stub

		try {
			Object[] para = new Object[1];
			para[0] = accountCategory.getAccountID();

			String query = "SELECT  ACCOUNT_CATEGORY_NAME ,ACCOUNT_CATEGORY_ABBR, DECODE(IS_ACTIVE,'Y','YES','N','NO','NO')" +
					"  from HRMS_ACCOUNTING_CATEGORY"
					+ " WHERE  ACCOUNT_CATEGORY_ID =" + para[0] + "";

			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				accountCategory.setAccountName(String.valueOf(data[0][0]));
				accountCategory.setAccountAbbreviation(String.valueOf(data[0][1]));
				accountCategory.setIsActive(String.valueOf(data[0][2]));

			}
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in data---model");
			return false;
		}

	}
/**
 * to generate the report
 * @param accountCategory
 * @param response
 * @param label
 */
	public void generateReport(AccountCategoryMaster accountCategory,
			HttpServletResponse response, String[] label) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		
		String reportName = "\n\nAccount Category  Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Onsite Posting Master.Pdf");
		String queryDes = " SELECT  ACCOUNT_CATEGORY_NAME,ACCOUNT_CATEGORY_ABBR,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_ACCOUNTING_CATEGORY   ORDER BY UPPER(ACCOUNT_CATEGORY_NAME)";
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][4];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];

				j++;
			}
			int cellwidth[] = { 10, 20, 20, 20 };
			int alignment[] = { 1, 0, 0, 0 };
			rg.addFormatedText(reportName, 6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);

	}
}
