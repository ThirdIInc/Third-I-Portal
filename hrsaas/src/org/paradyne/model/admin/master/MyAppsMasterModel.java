package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.master.MyAppsMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author Prajakta.Bhandare
 * @date 11 Feb 2013
 */
public class MyAppsMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);
	MyAppsMaster myApps = null;

	/** Method to get link records
	 * @param myApps
	 * @param request
	 */
	public void getData(MyAppsMaster myApps, HttpServletRequest request) {
		Object[][] linkData = getSqlModel().getSingleResult(getQuery(1));
		if (linkData != null && linkData.length > 0) {// if data
			myApps.setModeLength("true"); // set the length of data in the
			// list
			myApps.setTotalRecords(String.valueOf(linkData.length));// display
			// the total
			// record in
			// the list

			String[] pageIndex = Utility.doPaging(myApps.getMyPage(),
					linkData.length, 20);// to display the page number
			if (pageIndex == null) {//if pageIndex null
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}//end if pageIndex null
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2]))); // to set the total number of page
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));// to set the page number
			if (pageIndex[4].equals("1"))
				myApps.setMyPage("1");
			ArrayList<MyAppsMaster> List = new ArrayList<MyAppsMaster>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {//for loop to retrieve data
				MyAppsMaster bean1 = new MyAppsMaster();
				bean1.setLinkId(checkNull(String.valueOf(linkData[i][0])));//link Id
				bean1.setLinkName(checkNull(String.valueOf(linkData[i][1])));//link Name 

				bean1.setLinkUrl(checkNull(String.valueOf(linkData[i][2]))); //link Url

				bean1.setIsActive(checkNull(String.valueOf(linkData[i][3]))); // Is Active
				List.add(bean1);
			}// end for loop to retrieve data
			myApps.setLinkList(List);// to display the division list in the  page
		}//end if data							
	}

	/** Method to get particular record ID and Name for edit
	 * @param myApps
	 */
	public void callEdit(MyAppsMaster myApps) {
		try {
			Object hiddenobj[] = new Object[1];
			hiddenobj[0] = myApps.getHiddenCode();
			Object[][] linkData = getSqlModel().getSingleResult(getQuery(2),
					hiddenobj);
			if (linkData != null && linkData.length > 0) {//if data
				myApps.setLinkId(checkNull(String.valueOf(linkData[0][0])));
				myApps.setLinkName(checkNull(String.valueOf(linkData[0][1])));
			}//end if data
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	/** Method to retrieve particular link record for editing
	 * @param myApps
	 */
	public void editRecord(MyAppsMaster myApps) {
		try {
			String code = "";
			String getName = "";
			Object[] obj = new Object[1];
			obj[0] = myApps.getLinkId();
			myApps.setHiddenCode(String.valueOf(myApps.getLinkId()));
			Object[][] data = getSqlModel().getSingleResult(getQuery(3), obj);
			if (data != null && data.length > 0) {//if data
				myApps.setLinkId(checkNull(String.valueOf(data[0][0])));//Link Id
				myApps.setLinkName(checkNull(String.valueOf(data[0][1])));//Link Name
				myApps.setLinkUrl(checkNull(String.valueOf(data[0][2])));//Link Url
				myApps.setLinkImage(checkNull(String.valueOf(data[0][3])));//Link Image
				myApps.setLinkDivCode(checkNull(String.valueOf(data[0][4])));//Link Division code
				code = String.valueOf(data[0][4]);//link division codes
				//Query to get division code and name
				String divQuery = "SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID IN("
						+ String.valueOf(data[0][4]) + ")";
				Object[][] divObj = getSqlModel().getSingleResult(divQuery);
				getName = Utility.getNameByKey(divObj, code);
				myApps.setLinkDiv(getName);//Link Division	
				myApps.setLinkSeq(checkNull(String.valueOf(data[0][5])));//LinkSequence
				myApps.setIsActive(checkNull(String.valueOf(data[0][6])));//Is Active
			}//end if data
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	/** Method to add new link record
	 * @return boolean
	 */
	public boolean addLink(MyAppsMaster bean) {
		if (!checkDuplicate(bean)) {//if not duplicate
			String query="SELECT NVL(MAX(LINK_ID),0)+1 FROM HRMS_PORTAL_APPS";
			Object[][] rel = getSqlModel().getSingleResult(query);
			bean.setLinkId(String.valueOf(rel[0][0]));//link id
			bean.setHiddenCode(String.valueOf(rel[0][0]));//hidden code
			Object[][] addObj = new Object[1][6];
			addObj[0][0]= bean.getLinkName().trim();//link name
			addObj[0][1]= bean.getLinkUrl().trim();//link url
			addObj[0][2]= bean.getLinkImage().trim();//link image
			addObj[0][3]= bean.getLinkSeq().trim();//link sequence
			addObj[0][4]= bean.getLinkDivCode().trim();//link division code
			addObj[0][5]= bean.getIsActive().trim();//is active
			return getSqlModel().singleExecute(getQuery(4), addObj);
		}//end if not duplicate
		else{
			return false;
		}//end of else
	}

	/** Method to update existing link record
	 * @return boolean
	 */
	public boolean modLink(MyAppsMaster bean) {
		if (!checkDuplicateMod(bean)) {//if not duplicate
			Object[][] addObj = new Object[1][7];
			addObj[0][0]= bean.getLinkName().trim();//link name
			addObj[0][1]= bean.getLinkUrl().trim();//link url
			addObj[0][2]= bean.getLinkImage().trim();//link image
			addObj[0][3]= bean.getLinkSeq().trim();//link sequence
			addObj[0][4]= bean.getLinkDivCode().trim();//link division code
			addObj[0][5]= bean.getIsActive().trim();//is active
			addObj[0][6]= bean.getLinkId().trim();//Link Id
			return getSqlModel().singleExecute(getQuery(5), addObj);	
			
		}//end if not duplicate
		return false;
	}
	
	/** Method to delete multiple records
	 * @return boolean
	 */
	public boolean deleteLink(MyAppsMaster bean, String[] code){
		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		if (code != null) {// to check the division code which not null
			for (int i = 0; i < code.length; i++) {//for loop to delete 
				if (!code[i].equals("")) {// to check the division code which not null
					Object[][] delete = new Object[1][1];// to delete the multiple record
					delete[0][0] = code[i];
					flag = getSqlModel().singleExecute(getQuery(6), delete);
					if (!flag) {//if not flag
						cnt++;
					}//end of if not flag
					// result=true;
				}// end of nested if
			}// end of for loop to delete
		}// end of if
		if (cnt > 0) {
			result = false;
		}// end of if
		else
			result = true;
		return result;
	}
	
	/** Method to delete single record
	 * @return boolean
	 */
	public boolean delete(MyAppsMaster bean){
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = bean.getLinkId();
		// to delete the Link record from data base
		return getSqlModel().singleExecute(getQuery(6), delObj);
	}
	/** Method to check duplicate link names while adding link record
	 * @param bean
	 * @return boolean
	 */
	public boolean checkDuplicate(MyAppsMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_PORTAL_APPS WHERE UPPER(LINK_NAME) LIKE '"
				+ bean.getLinkName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {//if data
			result = true;
		}// end of if
		return result;

	}

	/** Method to check duplicate entries while updating record
	 * @param bean
	 * @return
	 */
	public boolean checkDuplicateMod(MyAppsMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_PORTAL_APPS WHERE UPPER(LINK_NAME) LIKE '"
				+ bean.getLinkName().trim().toUpperCase()
				+ "' AND LINK_ID not in(" + bean.getLinkId() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {//if data
			result = true;
		}// end of if
		return result;

	}
	
	/** Method to generate report
	 * @param divMast
	 * @param request
	 * @param response
	 * @param context
	 * @param label
	 */
	public void getReport(MyAppsMaster myApps, HttpServletRequest request,
		HttpServletResponse response, ServletContext context, String[] label) {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nMy Apps Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",reportName,"");
		rg.setFName("My Apps Master");
		String queryDes = "SELECT LINK_NAME, LINK_URL, LINK_IMAGE,LINK_FOR_DIVISION," 
							+" LINK_SEQUENCE ,IS_ACTIVE FROM HRMS_PORTAL_APPS ";
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][7];
		if (data != null && data.length > 0) {//if data
			int j = 1;
			for (int i = 0; i < data.length; i++) {//for loop to retrieve data
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];
				String code="";
				String getName="";
				Object[][] divObj=null;
				code = String.valueOf(data[i][3]);
				String divQuery = "SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID IN("
					+ String.valueOf(data[i][3]) + ")";
				divObj = getSqlModel().getSingleResult(divQuery);
				getName = Utility.getNameByKey(divObj, code);
				Data[i][4] = getName;
				Data[i][5] = data[i][4];
				Data[i][6] = data[i][5];
				j++;
			}//end of for loop
			int cellwidth[] = {5,20,25,15,25,5,5};
			int alignment[] = { 1, 0, 0, 0, 0, 1, 1};
			rg.addFormatedText(reportName,6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} //end of if data
		else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}//END OF ELSE

		rg.createReport(response);

	}

	/**
	 * to cheking null value
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
}
