package org.paradyne.model.eGov.reports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.lib.Utility;
import org.paradyne.bean.D1.BusinessUnitBean;
import org.paradyne.bean.D1.NewHireRehire;
import org.paradyne.bean.eGov.reports.LeaveCreditConfigeGovBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author pradeep
 * @modified by Reeba
 * @modified by Dilip
 * 
 */

/**
 *  To define the business logic for Division
 */
public class LeaveCreditConfigeGovModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	LeaveCreditConfigeGovBean divMast = null;

	/* inserting record */
	public boolean addDiv(LeaveCreditConfigeGovBean bean) {
		
		String query = "SELECT NVL(MAX(LEAVE_CONF_ID),0)+1 FROM  HRMS_LEAVE_CREDIT_CONFIG";
		Object[][] rel = getSqlModel().getSingleResult(query);
		bean.setLeaveConfigId(String.valueOf(rel[0][0]));

		Object[][] addObj = new Object[1][2];
		addObj[0][0] = bean.getLeaveId().trim(); // leave Id
		addObj[0][1] = bean.getDebitCode().trim(); // debit Code

		String insertQuery = "INSERT INTO HRMS_LEAVE_CREDIT_CONFIG"
				+ "(LEAVE_CONF_ID, LEAVE_CODE, CREDIT_CODE)"
				+ " VALUES((SELECT NVL(MAX(LEAVE_CONF_ID),0)+1 FROM HRMS_LEAVE_CREDIT_CONFIG),?,?)";

		for (int i = 0; i < addObj.length; i++) {
			for (int j = 0; j < addObj[i].length; j++) {
				logger.info("insertObj[" + i + "][" + j + "]  " + addObj[i][j]);
			}
		}
		return getSqlModel().singleExecute(insertQuery, addObj);

	
	}

	
	public boolean insertRecord(LeaveCreditConfigeGovBean bean) {

			 Object [][] addObj  = new Object[1][3];

			final String query1 = "SELECT NVL(MAX(LEAVE_CONF_ID),0)+1 FROM  HRMS_LEAVE_CREDIT_CONFIG";
			final Object[][] leaveValue = getSqlModel().getSingleResult(query1);

			addObj[0][0] = checkNull(String.valueOf(leaveValue[0][0]));
			addObj[0][1] = bean.getLeaveId().trim();
			addObj[0][2] = bean.getDebitCode().trim();
			

			bean.setLeaveConfigId(String.valueOf(leaveValue[0][0]));

			String insertQuery = "INSERT INTO HRMS_LEAVE_CREDIT_CONFIG (LEAVE_CONF_ID, LEAVE_CODE, CREDIT_CODE) VALUES(?,?,?)";

			return this.getSqlModel().singleExecute(insertQuery, addObj); 

	}
	
	
	/**
	 * purpose - Modifying record.
	 * @param bean - get appln data.
	 * @return true/false..
	 */
	public boolean updateRecord(LeaveCreditConfigeGovBean bean) {
			 Object [][] updateObj = new Object[1][3];

			updateObj[0][0] = bean.getLeaveId().trim();
			updateObj[0][1] = bean.getDebitCode().trim();
			updateObj[0][2] = bean.getLeaveConfigId().trim();
		
			return getSqlModel().singleExecute(getQuery(2), updateObj);
		 	
		
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * to cheking null value
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

	/**
	 * to get the details data after clickin on the serch button
	 * 
	 * @param divMast
	 */

	public void data(LeaveCreditConfigeGovBean bean) {
		// TODO Auto-generated method stub
		try {
			Object[] para = new Object[1];
			para[0] = bean.getLeaveConfigId();
			String dataQuery = "SELECT HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CONF_ID, HRMS_LEAVE.LEAVE_NAME, CREDIT_CODE,LEAVE_ID "
					+ "     FROM HRMS_LEAVE_CREDIT_CONFIG"
					+ "   LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CODE)"
					+ "     WHERE LEAVE_CONF_ID =" + String.valueOf(para[0]);
			Object[][] data = getSqlModel().getSingleResult(dataQuery);
			bean.setLeaveConfigId(checkNull(String.valueOf(data[0][0])));
			bean.setLeaveName(checkNull(String.valueOf(data[0][1])));

			String query = "select CREDIT_CODE, CREDIT_ABBR from HRMS_CREDIT_HEAD ";
			Object[][] obj = getSqlModel().getSingleResult(query);

			String debitAbbr = "";

			//	bean.setDebitCode(checkNull(String.valueOf(data[0][2])));

			String[] abbr = String.valueOf(data[0][2]).split(",");
			for (int j = 0; j < abbr.length; j++) {
				for (int j2 = 0; j2 < obj.length; j2++) {
					if (String.valueOf(abbr[j]).equals(
							String.valueOf(obj[j2][0]))) {
						System.out.println("String.valueOf(obj[j2][1])="
								+ String.valueOf(obj[j2][1]));
						debitAbbr += String.valueOf(obj[j2][1]) + ",";
						System.out.println("debitAbbr=" + debitAbbr);

					}

				}

			}
			debitAbbr = debitAbbr.substring(0, debitAbbr.length() - 1);
			bean.setDebitName(debitAbbr); // division
			bean.setDebitCode(String.valueOf(data[0][2]));
			bean.setLeaveId(String.valueOf(data[0][3]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * to generate the list in onload
	 * 
	 */
	public void leaveCreditData(LeaveCreditConfigeGovBean bean, HttpServletRequest request) {
		// to get the data from division master

		Object[][] repData = null;
		final List<LeaveCreditConfigeGovBean> saveList = new ArrayList<LeaveCreditConfigeGovBean>();

		String selQuery = " SELECT HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CONF_ID, HRMS_LEAVE.LEAVE_NAME, CREDIT_CODE "
				+ "      FROM HRMS_LEAVE_CREDIT_CONFIG "
				+ "      LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CODE) "
				+ "ORDER BY HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CONF_ID DESC ";

		repData = getSqlModel().getSingleResult(selQuery);

		if (repData != null && repData.length > 0) {
			bean.setModeLength("true"); // set the length of data in the
			// list
			bean.setTotalRecords(String.valueOf(repData.length));// display

			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					repData.length, 20);// to display the page number
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
System.out.println("pageIndex[2]=========== " + pageIndex[2]);
			String query = "select CREDIT_CODE,CREDIT_ABBR from HRMS_CREDIT_HEAD ";
			Object[][] obj = getSqlModel().getSingleResult(query);
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2]))); // to set the total number of page
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));// to set the page number
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");
			//ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				LeaveCreditConfigeGovBean bean1 = new LeaveCreditConfigeGovBean();
				bean1
						.setLeaveConfigId(checkNull(String
								.valueOf(repData[i][0]))); // division
				String debitAbbr = ""; // id
				bean1.setLeaveName(checkNull(String.valueOf(repData[i][1]))); // division
				String[] abbr = String.valueOf(repData[i][2]).split(",");
				for (int j = 0; j < abbr.length; j++) {
					for (int j2 = 0; j2 < obj.length; j2++) {
						if (String.valueOf(abbr[j]).equals(
								String.valueOf(obj[j2][0]))) {
							System.out.println("String.valueOf(obj[j2][1])="
									+ String.valueOf(obj[j2][1]));
							debitAbbr += String.valueOf(obj[j2][1]) + ",";
							System.out.println("debitAbbr=" + debitAbbr);

						}

					}

				}
				debitAbbr = debitAbbr.substring(0, debitAbbr.length() - 1);
				bean1.setDebitName(debitAbbr); // division
				saveList.add(bean1);
			}// end of loop
			bean.setLeaveConfigList(saveList);// to display the division list in the  page

		}
	}

	

	/**
	 * Added by Nilesh
	 */

	/**
	 * @param bean - to get code for deletion. 
	 * @return true/false.
	 */
	public boolean delete(LeaveCreditConfigeGovBean bean) {
		boolean result = false;

		final String deleteId = bean.getLeaveConfigId();

		final String delQuery = "DELETE FROM HRMS_LEAVE_CREDIT_CONFIG WHERE LEAVE_CONF_ID ="+ deleteId;
		result = this.getSqlModel().singleExecute(delQuery);
		return result;
	}

	/**
	 * purpose - Edit respective record.
	 * @param businessUnitBean bean instance.
	 */
	public void editBusinessData(LeaveCreditConfigeGovBean bean) {

		try {

			final String query = " SELECT HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CONF_ID, HRMS_LEAVE.LEAVE_NAME, HRMS_CREDIT_HEAD.CREDIT_CODE,HRMS_CREDIT_HEAD.CREDIT_ABBR"
					+ " FROM HRMS_LEAVE_CREDIT_CONFIG "
					+ " LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CODE) "
					+ " LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CONF_ID) "
					+ "  WHERE LEAVE_CONF_ID= " + bean.getLeaveConfigId();

			final Object[][] data = this.getSqlModel().getSingleResult(query);

			bean.setLeaveConfigId(String.valueOf(data[0][0]));
			bean.setLeaveName(String.valueOf(data[0][1]));
			bean.setDebitCode(String.valueOf(data[0][2]));
			bean.setDebitName(String.valueOf(data[0][3]));

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * @param bean .
	 * @param code - unique code for deletion.
	 * @return boolean.
	 */
	public boolean deleteCheckedRecords(LeaveCreditConfigeGovBean bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!"".equals(code[i])) {
					final Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = this.getSqlModel().singleExecute(this.getQuery(1), delete);
					if (!result) {
						count++;
					}

				}
			}
		}
		if (count != 0) {
			result = false;
			return result;
		}  else {
			result = true;
			return result;
		}
	}
	
	
	/**
	 * purpose - display credit abbrivation record.
	 * @param businessUnitBean bean instance.
	 */
	public void display( LeaveCreditConfigeGovBean bean) {

		try {
			
			String selQuery = " SELECT HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CONF_ID, HRMS_LEAVE.LEAVE_NAME, CREDIT_CODE , LEAVE_CODE "
				+ "      FROM HRMS_LEAVE_CREDIT_CONFIG "
				+ "      LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CODE) "
				+ "ORDER BY HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CONF_ID DESC ";
			
			final Object[][] reqData = this.getSqlModel().getSingleResult(selQuery);
			
			final String query =  "select CREDIT_CODE, CREDIT_ABBR from HRMS_CREDIT_HEAD ";

			final Object[][] data = this.getSqlModel().getSingleResult(query);
			bean.setLeaveConfigId(String.valueOf(reqData[0][0]));
			bean.setLeaveName(String.valueOf(reqData[0][1]));
			
			String debitAbbr = ""; // id
			String[] abbr = String.valueOf(reqData[0][2]).split(",");
			for (int j = 0; j < abbr.length; j++) {
				for (int j2 = 0; j2 < data.length; j2++) {
					if (String.valueOf(abbr[j]).equals(
							String.valueOf(data[j2][0]))) {
						System.out.println("String.valueOf(obj[j2][1])="
								+ String.valueOf(data[j2][1]));
						debitAbbr += String.valueOf(data[j2][1]) + ",";
						System.out.println("debitAbbr=" + debitAbbr);

					}

				}

			}
			debitAbbr = debitAbbr.substring(0, debitAbbr.length() - 1);
			bean.setDebitName(debitAbbr); // division
			bean.setDebitCode(String.valueOf(reqData[0][2]));
			bean.setLeaveId(String.valueOf(reqData[0][3]));
		//	bean.setDebitCode(String.valueOf(data[0][0]));
		//	bean.setDebitName(String.valueOf(data[0][1]));
		} catch (final Exception e) {
			e.printStackTrace();
		}

	}
	
	
}
