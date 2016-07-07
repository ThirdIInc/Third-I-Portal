package org.paradyne.model.CR;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.CR.ClientMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.StringEncrypter;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author vivek.wadhwani
 * 
 */
public class ClientMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	ClientMaster tm = null;

	/**
	 *  to check null value
	 *  
	 * @param result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} //end of if
		else {
			return result;
		}//end of else
	}

	/** for checking duplicate entry of record during insertion 
	 * @param bean
	 * @return
	 */
	public boolean checkEmailDuplicate(ClientMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM CR_CLIENT_USERS WHERE UPPER(EMAIL_ID) LIKE '"
				+ bean.getEmailClientAddress().trim().toUpperCase() + "'";

		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {

		
		
		
		String internalUserQuery = "SELECT * FROM HRMS_EMP_ADDRESS WHERE UPPER(ADD_EMAIL) LIKE '"
			+ bean.getEmailClientAddress().trim().toUpperCase() + "'";
	Object[][] internalData = getSqlModel().getSingleResult(internalUserQuery);
		if ((data != null && data.length > 0) || (internalData != null && internalData.length > 0)) {

			result = true;
		}// end of if
		}
		return result;

	}

	/** for deleting the record
	 * @param bean
	 * @return
	 */
	public boolean delete(ClientMaster bean) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getHiddencode(); // to get the  clientUserNo  for deleting the particular record(one record)

		boolean result = getSqlModel().singleExecute(getQuery(4),
				addObj);

		return result;
	}

	/** For displaying the list of records from the database
	 * @param bean
	 * @param request
	 */
	public void callForBusinessUser(ClientMaster bean,
			HttpServletRequest request) {

		String clientUserQuery = "SELECT CRUSER_CODE, FIRST_NAME,LAST_NAME,EMAIL_ID, CASE WHEN IS_ACTIVE='Y' THEN 'Yes' ELSE 'No' END FROM CR_CLIENT_USERS ORDER  BY CRUSER_CODE";
		int length = 0;
		Object[][] data = getSqlModel().getSingleResult(
				clientUserQuery);

		if (data != null && data.length > 0) {
			bean.setModeLength("true");
			bean.setTotalRecords(String.valueOf(data.length)); //   to  display the total number of record in  the list 

			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					data.length, 20);
			if (pageIndex == null) {//checking page index
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}//end of inner if
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPageInProcess("1");
			int k = 0;
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {//the list of records to display
				ClientMaster bean1 = new ClientMaster();
				bean1
						.setIttClientUserNo(checkNull((String
								.valueOf(data[i][0])))); //Client user no from iterator
				bean1.setIttFirstName(checkNull((String.valueOf(data[i][1])))); //First Name from iterator
				bean1.setIttLastName(checkNull((String.valueOf(data[i][2])))); //Last Name from iterator
				bean1.setIttEmailId(checkNull((String.valueOf(data[i][3])))); //Email Id from iterator
				bean1.setIttIsActive(checkNull((String.valueOf(data[i][4])))); //IsActive from iterator

				List.add(bean1); //Adding bean to list
				k++;
			}//end of for

			bean.setClientUserList(List);
			length = data.length;
			bean.setTotalRecords(String.valueOf(length));
		}//end of outer if

	}

	/** displaying records after search f9action
	 * @param bean
	 */
	public void getClientRecord(ClientMaster bean) {
		Object addObj[] = new Object[1];
		addObj[0] = bean.getClientUserNo(); //get the ClientUserNo
		System.out.println("hidden " + bean.getHiddencode());
		Object data[][] = getSqlModel().getSingleResult(getQuery(3),
				addObj);
		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				bean.setHiddencode(checkNull(String.valueOf(data[0][0]))); //Hidden Code
				bean.setFirstName(checkNull(String.valueOf(data[0][1]))); //First Name
				bean.setLastName(checkNull(String.valueOf(data[0][2]))); //Last Name
				bean.setEmailClientAddress(checkNull(String.valueOf(data[0][3]))); //Email ID
				bean.setPassword(checkNull(String.valueOf(data[0][4]))); // Password 
				bean.setCellNumber(checkNull(String.valueOf(data[0][5])));// Cell Number
				bean.setIsClientActive(checkNull(String.valueOf(data[0][6]))); // IsActive

			}//end of for
		}//end of if
	}

	/** to save data in database 
	 * @param bean
	 * @param request
	 * @return
	 */
	public boolean saveClientUserLstDtl(ClientMaster bean,
			HttpServletRequest request) {
		boolean result = false;

		try {

			if (!checkEmailDuplicate(bean)) {
				//if email is not duplicate then the following code executes
				Object[][] addObj = new Object[1][6];
				addObj[0][0] = bean.getFirstName().trim();
				; // Get FirstName
				addObj[0][1] = bean.getLastName().trim();
				; //Get LastName
				addObj[0][2] = bean.getEmailClientAddress().trim();
				; //Get Email ID
				addObj[0][3] = bean.getCellNumber().trim();
				; //Get Cell Number

				if (bean.getIsClientActive().equals("true")) {//is checkbox selected for Is Active field
					addObj[0][4] = "Y";
				}//end of if 
				else {
					addObj[0][4] = "N";
				}//end of else

				String encryptPwd;

				encryptPwd = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean
						.getPassword().trim());

				addObj[0][5] = encryptPwd;
				result = getSqlModel().singleExecute(getQuery(1),
						addObj);
				if (result) {
					//to get the new clientUserNo to be set as hiddencode
					String autoCodeQuery = " SELECT NVL(MAX(CRUSER_CODE),0) FROM CR_CLIENT_USERS ";
					Object[][] data = getSqlModel().getSingleResult(
							autoCodeQuery);
					if (data != null && data.length > 0) {
						bean.setHiddencode(String.valueOf(data[0][0]));
					}//end  inner if
				}//end outer if

			} // end of main if
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	/** Deleting user record
	 * @param bean
	 * @param request
	 * @param clientUserID
	 * @return
	 */
	public boolean delClientUserLstDtl(ClientMaster bean,
			HttpServletRequest request, String clientUserID) {
		String deleteQuery = "";
		try {
			deleteQuery = "DELETE FROM CR_CLIENT_USERS  WHERE CRUSER_CODE="
					+ clientUserID;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return getSqlModel().singleExecute(deleteQuery);
	}

	/** Deleting mutiple records
	 * @param bean
	 * @param code
	 * @return
	 */
	public boolean deleteClientUserLstDtls(ClientMaster bean, String[] code) {
		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		if (code != null) {
			//if a checkbox is selected
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					//logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"+code[i]);	
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];

					flag = getSqlModel().singleExecute(getQuery(2),
							delete);
					if (!flag) {
						cnt++;
					}//end of if
					//result=true;
				}//end of nested if
			}//end of loop
		}//end of nested if
		if (cnt > 0) {
			result = false;
		}//end of if
		else
			result = true;
		return result;

	}

	/** Method: Edit client users from the list on double click
	 * @param bean
	 */
	public void editClientUserLstDtl(ClientMaster bean) {
		try {

			String query = "SELECT FIRST_NAME, LAST_NAME, EMAIL_ID, "
					+ "PHONE_NO, PASSWORD, IS_ACTIVE  FROM CR_CLIENT_USERS "
					+ " WHERE  CRUSER_CODE= " + bean.getHiddencode();
			Object[][] data = getSqlModel().getSingleResult(query);
			//bean.setParacode(checkNull((String.valueOf(data[0][0]))));
			bean.setClientUserNo(bean.getHiddencode());

			bean.setFirstName(checkNull((String.valueOf(data[0][0])))); //First Name
			bean.setLastName(checkNull((String.valueOf(data[0][1])))); //Last Name
			bean.setEmailClientAddress(checkNull((String.valueOf(data[0][2])))); //Email id
			bean.setCellNumber(checkNull((String.valueOf(data[0][3])))); //Cell Number
			bean.setPassword(new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).decrypt(String
					.valueOf(data[0][4]))); //Password

			if (String.valueOf(data[0][5]).equals("Y")) { //IsActive
				//check box selected has value "Y"
				bean.setIttIsActive("S");
				bean.setIsClientActive("true");
			}//end if 

			else {
				bean.setIttIsActive("L");
				bean.setIsClientActive("false");
			}//end of else
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** Update the existing record
	 * @param bean
	 * @param request
	 * @return boolean
	 */
	public boolean modClientUserLstDtl(ClientMaster bean,
			HttpServletRequest request) {
		boolean result = false;

		try {
			Object updateObj[][] = new Object[1][7];

			updateObj[0][0] = bean.getHiddencode(); //HiddenCode
			updateObj[0][1] = bean.getFirstName(); //First Name
			updateObj[0][2] = bean.getLastName(); //Last Name
			updateObj[0][3] = bean.getEmailClientAddress(); //Email Id
			updateObj[0][4] = bean.getCellNumber(); //Cell Number
			String encryptPwd;
			try {
				encryptPwd = new StringEncrypter(
						StringEncrypter.DESEDE_ENCRYPTION_SCHEME).encrypt(bean
						.getPassword().trim());
				updateObj[0][5] = encryptPwd;
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (bean.getIsClientActive().equals("true")) {
				updateObj[0][6] = "Y";
			} else {
				updateObj[0][6] = "N";
			}

			String insertQuery = "UPDATE CR_CLIENT_USERS SET "
					+ " CRUSER_CODE = ?, FIRST_NAME = ? , LAST_NAME = ?  , EMAIL_ID  = ? , PHONE_NO = ? , PASSWORD = ? , IS_ACTIVE = ? "
					+ " WHERE CRUSER_CODE = " + bean.getHiddencode();

			result = getSqlModel().singleExecute(insertQuery,
					updateObj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**This is used to get Report.
	 * @param bean : ClientMaster
	 * @param response
	 * @param request
	 * @param reportPath
	 */
	public void getReport(ClientMaster cntrMaster2, HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			HttpSession session, String[] label) {
		// TODO Auto-generated method stub

		/*CrystalReport cr=new CrystalReport();
		String path="org\\paradyne\\rpt\\admin\\master\\center.rpt ";
		cr.createReport(request, response, context,session, path, "");*/
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);

		String reportName = "\n\nClient User Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Client User Master");
		String query = "SELECT FIRST_NAME, LAST_NAME, EMAIL_ID,"
				+ " PHONE_NO,  CASE WHEN IS_ACTIVE='Y' THEN 'Yes' ELSE 'No' END "
				+ " FROM CR_CLIENT_USERS "
				+ " ORDER  BY CRUSER_CODE";
		Object[][] data = getSqlModel().getSingleResult(query);
		Object[][] Data = new Object[data.length][6];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j; // For getting Sr No.
				Data[i][1] = data[i][0]; //FirstName
				Data[i][2] = data[i][1]; //LastName
				Data[i][3] = data[i][2]; //Email-id
				Data[i][4] = data[i][3]; //mobile -number
				Data[i][5] = data[i][4]; //active

				j++;
			}// end of for
			int cellwidth[] = { 15, 30, 25, 40, 25, 25 };
			int alignment[] = { 1, 0, 0, 0, 0, 0 };
			rg.addFormatedText(reportName, 6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		}// end of if
		else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}//end of else

		rg.createReport(response);

	}
}
