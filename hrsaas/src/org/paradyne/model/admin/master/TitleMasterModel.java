package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.admin.master.*;
import org.paradyne.lib.ModelBase; /*
import org.paradyne.lib.Utility;
 * Pradeep Kumar Sahoo
 * Date:26.06.2007
 */
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.Utility;


public class TitleMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	TitleMaster tm = null;

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(TitleMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_TITLE WHERE UPPER(TITLE_NAME) LIKE '"
				+ bean.getTitleName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;

	}

	/* for checking duplicate entry of record during modification */
	public boolean checkDuplicateMod(TitleMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_TITLE WHERE UPPER(TITLE_NAME) LIKE '"
				+ bean.getTitleName().trim().toUpperCase()
				+ "' AND TITLE_CODE not in(" + bean.getTitleCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data.length==0) {
			result = true;
		}//end of if
		return result;

	}

	/* for inserting the data */
	public boolean addTitle(TitleMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_TITLE WHERE UPPER(TITLE_NAME) LIKE '"
				+ bean.getTitleName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data.length ==0) {
			
			Object[][] saveObj = new Object[1][2];
			saveObj[0][0] = bean.getTitleName();
			saveObj[0][1] = bean.getIsActive();
			System.out.println("####### TITLE NAME ###########"+bean.getTitleName());
			System.out.println("#######IS ACTIVE ###########"+bean.getIsActive());
			
			result=getSqlModel().singleExecute(getQuery(1), saveObj);
			
			String selQuery="SELECT MAX(TITLE_CODE) FROM HRMS_TITLE";
			
		Object [][]datasel=getSqlModel().getSingleResult(selQuery);
		bean.setTitleCode(String.valueOf(datasel[0][0]));
		}//end of if
		else {
			return false;

		}//end of else
		return result;
	}
	

	/* for modifing the data */
	public boolean modTitle(TitleMaster bean) {
		if (checkDuplicateMod(bean)) {
			Object modObj[][] = new Object[1][3];
			modObj[0][0] = bean.getTitleName().trim();
			modObj[0][1] = bean.getIsActive();
			modObj[0][2] = bean.getTitleCode();
			
			return getSqlModel().singleExecute(getQuery(2), modObj);
		}//end of if
		else
			return false;

	}

	/* for deleting the record after selecting */
	public boolean deleteTitle(TitleMaster bean) {
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = bean.getTitleCode();
		return getSqlModel().singleExecute(getQuery(3), delObj);
	}

	public void callReport(TitleMaster bean) {
		String query = " SELECT  TITLE_CODE,TITLE_NAME,DECODE(IS_ACTIVE,'Y','YES','N','NO') FROM HRMS_TITLE ORDER BY TITLE_CODE ";

		Object data[][] = getSqlModel().getSingleResult(query);
		ArrayList<Object> titleList = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			TitleMaster bean1 = new TitleMaster();
			bean1.setTitleCode(checkNull(String.valueOf(data[i][0])));
			bean1.setTitleName(checkNull(String.valueOf(data[i][1])));
			bean1.setIsActive(checkNull(String.valueOf(data[i][2])));
			titleList.add(bean1);

		}//end of loop
		bean.setTitleList(titleList);

	}
/**
 *  to generate the report
 * @param tm
 * @param request
 * @param response
 * @param context
 * @param session
 * @param label
 */
	public void getReport(TitleMaster tm, HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			HttpSession session , String []label) {
		/*CrystalReport cr = new CrystalReport();
		String path = "org\\paradyne\\rpt\\admin\\master\\title.rpt";
		cr.createReport(request, response, context, session, path, "");*/
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nTitle Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Title  Master.Pdf");
		String queryDes = "SELECT  TITLE_NAME,DECODE(IS_ACTIVE,'Y','YES','N','NO') FROM HRMS_TITLE ORDER BY upper(TITLE_NAME)";
						
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][3];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				j++;
			}
			int cellwidth[] = { 10, 40, 50 };
			int alignment[] = { 1, 0, 0 };
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

	/* generating the list in  onload */
	public void hasData1(TitleMaster tm, HttpServletRequest request) {

		Object[][] repData = getSqlModel().getSingleResult(getQuery(4));
		ArrayList<Object> List = new ArrayList<Object>();
		if(repData!=null && repData.length>0){
			tm.setModeLength("true");
			tm.setTotalRecords(String.valueOf(repData.length));
		
		String[] pageIndex = Utility.doPaging(tm.getMyPage(), repData.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			tm.setMyPage("1");
		
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
			
			TitleMaster bean1 = new TitleMaster();
			bean1.setTitleCode(checkNull(String.valueOf(repData[i][0])));
			bean1.setTitleName(checkNull(String.valueOf(repData[i][1])));
			bean1.setIsActive(checkNull(String.valueOf(repData[i][2])));
			List.add(bean1);
		}//end of loop
		tm.setTitleList(List);
		}
		else{
			tm.setModeLength("false");
			tm.setTotalRecords("0");
			tm.setTitleList(List);
		}
	}

	/* for selecting the record from list */
	public void calforedit(TitleMaster tm) {

		String query = " SELECT  TITLE_CODE,TITLE_NAME, IS_ACTIVE FROM HRMS_TITLE  where TITLE_CODE= "
				+ tm.getHiddencode() + "   ORDER BY TITLE_CODE";
		Object[][] data = getSqlModel().getSingleResult(query);
		tm.setTitleCode(String.valueOf(data[0][0]));
		tm.setTitleName(String.valueOf(data[0][1]));
		tm.setIsActive(String.valueOf(data[0][2]));
	}

	/**
	 *  to delete  the  one record 
	 * @param tm
	 * @return
	 */
	public boolean calfordelete(TitleMaster tm) {

		Object[][] delete = new Object[1][1];
		delete[0][0] = tm.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);
		// TODO Auto-generated method stub

	}

	/* for deleting the multiple record from list by double clicking */
	public boolean deleteTitle(TitleMaster tm, String[] code) {

		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					//logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->" + code[i]);
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					flag = getSqlModel().singleExecute(getQuery(3), delete);
					if (!flag) {
						cnt++;
					}//end of if
					//result=true;
				}//end of if
			}//end of loop
		}//end of  nested if
		if (cnt > 0) {
			result = false;
		}//end of if
		else
			result = true;
		return result;

	}
/**
 *  to get the record after click on search button
 * @param tm2
 * @param request
 */
	public void data(TitleMaster tm2, HttpServletRequest request) {
		// TODO Auto-generated method stub
		try{
		Object[] para = new Object[1];
		para[0] = tm2.getTitleCode();
		String query = " SELECT  TITLE_NAME FROM HRMS_TITLE WHERE TITLE_CODE="+para[0]+" ORDER BY TITLE_CODE "; 
		Object data[][] = getSqlModel().getSingleResult(query,para);
		TitleMaster bean1 = new TitleMaster();
		bean1.setTitleName(checkNull(String.valueOf(data[0][0])));// isActive field is added by Abhijit Samanta
		bean1.setIsActive(checkNull(String.valueOf(data[0][1])));
			
		}//end of loop
		
		catch(Exception e){
			e.printStackTrace();
	   }
	}
}


