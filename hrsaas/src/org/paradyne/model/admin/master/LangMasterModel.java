package org.paradyne.model.admin.master;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.admin.master.*;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.Utility;

/*
 * Anantha lakshmi
 *  to define the business logic for lang
 */
public class LangMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);
	LangMaster am = null;
	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(LangMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_LANGUAGE WHERE UPPER(LANGUAGE_NAME) LIKE '"
				+ bean.getLangType().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/* for checking duplicate entry of record during modification */

	public boolean checkDuplicateMod(LangMaster bean) {
		boolean result = false;
		System.out.println("LANG TYPE IS"+ bean.getLangType().trim().toUpperCase());
		System.out.println("LANG CODE IS"+ bean.getLangCode());
		System.out.println();
		String query = "SELECT * FROM HRMS_LANGUAGE WHERE UPPER(LANGUAGE_NAME) LIKE '"
				+ bean.getLangType().trim().toUpperCase()+ "'" 
				+ " AND LANGUAGE_CODE NOT IN(" + bean.getLangCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}

	/* for inserting the record */
	public boolean addLang(LangMaster bean) {
		if (!checkDuplicate(bean)) {
			Object[][] saveObj = new Object[1][3];
			saveObj[0][0] = bean.getLangType().trim();
			saveObj[0][1] = bean.getCountryType().trim();
			saveObj[0][2]=bean.getIsActive();
			return getSqlModel().singleExecute(getQuery(1), saveObj);
		}
		else {
			return false;
		}
	}

	/* for modifying the record */
	public boolean modLang(LangMaster bean) {
		if (!checkDuplicateMod(bean)) {
			Object modObj[][] = new Object[1][4];
			modObj[0][0] = bean.getLangType().trim();
			modObj[0][1] = bean.getCountryType().trim();
			modObj[0][2] = bean.getIsActive();
			modObj[0][3] = bean.getLangCode();
			return getSqlModel().singleExecute(getQuery(2), modObj);
		} else{
			return false;
		}
	}

	/* for deleting record after selecting */
	public boolean deleteLang(LangMaster bean) {
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = bean.getLangCode();
		return getSqlModel().singleExecute(getQuery(3), delObj);
	}

	/*public void getReport(LangMaster bean) {

		Object data[][] = getSqlModel().getSingleResult(getQuery(4));
		ArrayList<Object> awardList = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			LangMaster bean1 = new LangMaster();
			bean1.setLangCode(checkNull(String.valueOf(data[i][0])));
			bean1.setLangType(checkNull(String.valueOf(data[i][1])));
			bean1.setCountryType(checkNull(String.valueOf(data[i][2])));
			awardList.add(bean1);
		}
		bean.setAwardList(awardList);
	}*/

	/* generating the report */
	
	/* generating the list in onload */
	public void langData(LangMaster am, HttpServletRequest request) {
		Object[][] repData = getSqlModel().getSingleResult(getQuery(4));
		if(repData!=null && repData.length>0){
			am.setModeLength("true");
			am.setTotalRecords(String.valueOf(repData.length));
		String[] pageIndex = Utility.doPaging(am.getMyPage(), repData.length, 20);	
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
			am.setMyPage("1");
		ArrayList<Object> List = new ArrayList<Object>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 		
			LangMaster bean1 = new LangMaster();
			bean1.setLangCode(checkNull(String.valueOf(repData[i][0])));
			bean1.setLangType(checkNull(String.valueOf(repData[i][1])));
			bean1.setCountryType(checkNull(String.valueOf(repData[i][2])));
			bean1.setHiddenIsactive(checkNull(String.valueOf(repData[i][3])));
			List.add(bean1);
		}
		am.setAwardList(List);
		}
	}

	/* for selecting the record from list by double clicking */
	public void calforedit(LangMaster am) {
		String query = "  SELECT  LANGUAGE_CODE, LANGUAGE_NAME, COUNTRY_NAME, LANGUAGE_IS_ACTIVE FROM HRMS_LANGUAGE "
				+ "  where  LANGUAGE_CODE = "
				+ am.getHiddencode()
				+ "   ORDER BY LANGUAGE_CODE ";

		Object[][] data = getSqlModel().getSingleResult(query);
		am.setLangCode(String.valueOf(data[0][0]));
		am.setLangType(String.valueOf(data[0][1]));
		am.setCountryType(String.valueOf(data[0][2]));
		am.setIsActive(String.valueOf(data[0][3]));

	}
/**
 *  to delete the single record after clecking on save/ searching button
 * @param am
 * @return
 */
	public boolean calfordelete(LangMaster am) {
		Object[][] delete = new Object[1][1];
		delete[0][0] = am.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);

	}

	/* for deleting one or more records from list */
	public boolean deleteEmptype(LangMaster am, String[] code) {
		boolean result = false;
		boolean flag = false;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!code[i].equals("")) {
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					flag = getSqlModel().singleExecute(getQuery(3), delete);
					if (flag)
						result = true;
				}
			}
		}
		return result;
	}
/**
 *  to display the data  after clicking on search button
 * @param am2
 * @return
 */
	public boolean getLangdata(LangMaster am2) {
		try {
			Object[] para = new Object[1];
			para[0] = am2.getLangCode();
			String query=" SELECT  LANGUAGE_CODE, LANGUAGE_NAME, COUNTRY_NAME,DECODE(LANGUAGE_IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_LANGUAGE  "
				+ "  where  LANGUAGE_CODE = ? "
				+ "   ORDER BY LANGUAGE_CODE ";
			Object[][] data = getSqlModel().getSingleResult(query, para);
			am2.setLangType(checkNull(String.valueOf(data[0][1])));	
			am2.setCountryType(checkNull(String.valueOf(data[0][2])));
			am2.setHiddenIsactive(checkNull(String.valueOf(data[0][3])));
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in data---model");
			return false;
		}
		
	}
	public void getLangDetails(LangMaster am2) {
		try {
			Object[] para = new Object[1];
			para[0] = am2.getLangType();
			System.out.println("LANG CODE"+am2.getLangType());
			String query=" SELECT  LANGUAGE_CODE, LANGUAGE_NAME, COUNTRY_NAME,DECODE(LANGUAGE_IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_LANGUAGE  "
				+ "  where  LANGUAGE_NAME= ? "
				+ "   ORDER BY LANGUAGE_CODE ";
			Object[][] data = getSqlModel().getSingleResult(query, para);
			am2.setLangCode(checkNull(String.valueOf(data[0][0])));
			am2.setHiddencode(checkNull(String.valueOf(data[0][0])));
			am2.setLangType(checkNull(String.valueOf(data[0][1])));	
			am2.setCountryType(checkNull(String.valueOf(data[0][2])));
			am2.setHiddenIsactive(checkNull(String.valueOf(data[0][3])));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in data---model");
		}
		
	}
	public void getReport(LangMaster am, HttpServletRequest request,HttpServletResponse response, ServletContext context,
			HttpSession session,String []label) {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\n Language Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",reportName);
		rg.setFName("Language  Master");
		String queryDes = "SELECT  LANGUAGE_NAME, COUNTRY_NAME, DECODE(LANGUAGE_IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_LANGUAGE ORDER BY upper(LANGUAGE_NAME) " ;				
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
			int cellwidth[] = {25,25,25,25 };
			int alignment[] = {1,1,1,1};
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
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

}
