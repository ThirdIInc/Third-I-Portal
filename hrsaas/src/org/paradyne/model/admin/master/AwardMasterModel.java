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
 * Pradeep Kumar Sahoo
 * Date:28.06.2007
 */
/**
 *  to define the business logic for award
 */
public class AwardMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	AwardMaster am = null;

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(AwardMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_AWARD_MASTER WHERE UPPER(AWARD_TYPE) LIKE '"
				+ bean.getAwardType().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/* for checking duplicate entry of record during modification */

	public boolean checkDuplicateMod(AwardMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_AWARD_MASTER WHERE UPPER(AWARD_TYPE) LIKE '"
				+ bean.getAwardType().trim().toUpperCase()
				+ "' AND AWARD_CODE not in(" + bean.getAwardCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;

	}

	/* for inserting the record */
	public boolean addAward(AwardMaster bean) {
		if (!checkDuplicate(bean)) {
			//logger.info("in addRecr()");
			String query="SELECT NVL(MAX(AWARD_CODE),0)+1 FROM HRMS_AWARD_MASTER";
			  Object data[][] = getSqlModel().getSingleResult(query);
			 bean.setAwardCode(String.valueOf(data[0][0]));			 
			Object[][] saveObj = new Object[1][2];
			saveObj[0][0] = bean.getAwardType().trim();
			saveObj[0][1]=bean.getIsActive();
			return getSqlModel().singleExecute(getQuery(1), saveObj);
		}
		else {
			return false;
		}
		}

	/* for modifying the record */
	public boolean modAward(AwardMaster bean) {
		if (!checkDuplicateMod(bean)) {
			Object modObj[][] = new Object[1][3];
			modObj[0][0] = bean.getAwardType().trim();
			modObj[0][1] = bean.getIsActive();
			modObj[0][2] = bean.getAwardCode();
			return getSqlModel().singleExecute(getQuery(2), modObj);
		} else
			return false;

	}

	/* for deleting record after selecting */
	public boolean deleteAward(AwardMaster bean) {
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = bean.getAwardCode();
		return getSqlModel().singleExecute(getQuery(3), delObj);
	}

	public void getReport(AwardMaster bean) {

		Object data[][] = getSqlModel().getSingleResult(getQuery(4));
		ArrayList<Object> awardList = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			AwardMaster bean1 = new AwardMaster();
			bean1.setAwardCode(checkNull(String.valueOf(data[i][0])));
			bean1.setAwardType(checkNull(String.valueOf(data[i][1])));
			awardList.add(bean1);
		}
		bean.setAwardList(awardList);
	}

	/* generating the report */
	public void getReport(AwardMaster am, HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			HttpSession session,String []label) {
		/*CrystalReport cr = new CrystalReport();
		String path = "org\\paradyne\\rpt\\admin\\master\\award.rpt";
		cr.createReport(request, response, context, session, path, "");*/
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nAward Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Award  Master.Pdf");
		String queryDes = "SELECT  AWARD_TYPE,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_AWARD_MASTER ORDER BY upper(AWARD_TYPE) ";
						
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
			int cellwidth[] = { 15, 40,20 };
			int alignment[] = { 1, 0,0};
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
 *  to check the null value
 * @param result
 * @return
 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/* generating the list in onload */
	public void awardData(AwardMaster am, HttpServletRequest request) {
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
			AwardMaster bean1 = new AwardMaster();
			bean1.setAwardCode(checkNull(String.valueOf(repData[i][0])));
			bean1.setAwardType(checkNull(String.valueOf(repData[i][1])));
			bean1.setIsActive(checkNull(String.valueOf(repData[i][2])));
			List.add(bean1);
		}
		am.setAwardList(List);
		}
	}

	/* for selecting the record from list by double clicking */
	public void calforedit(AwardMaster am) {
		String query = "  SELECT  AWARD_CODE,AWARD_TYPE,IS_ACTIVE FROM HRMS_AWARD_MASTER  "

				+ "  where  AWARD_CODE = "
				+ am.getHiddencode()
				+ "   ORDER BY AWARD_CODE ";

		Object[][] data = getSqlModel().getSingleResult(query);
		am.setAwardCode(String.valueOf(data[0][0]));
		am.setAwardType(String.valueOf(data[0][1]));
		am.setIsActive(String.valueOf(data[0][2]));

	}
/**
 *  to delete the single record after clecking on save/ searching button
 * @param am
 * @return
 */
	public boolean calfordelete(AwardMaster am) {
		Object[][] delete = new Object[1][1];
		delete[0][0] = am.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);

	}

	/* for deleting one or more records from list */
	public boolean deleteEmptype(AwardMaster am, String[] code) {
		boolean result = false;
		boolean flag = false;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					//logger .info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->" + code[i]);
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
	public boolean data(AwardMaster am2) {
		// TODO Auto-generated method stub
		
		try {
			Object[] para = new Object[1];
			para[0] = am2.getAwardCode();

			String query=" SELECT  AWARD_CODE,AWARD_TYPE,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_AWARD_MASTER  "
				+ "  where  AWARD_CODE = "
				+ am.getHiddencode()
				+ "   ORDER BY AWARD_CODE ";
			Object[][] data = getSqlModel().getSingleResult(query, para);
			am2.setAwardType(checkNull(String.valueOf(data[0][0])));	
			am2.setIsActive(checkNull(String.valueOf(data[0][1])));
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in data---model");
			return false;
		}
		
	}

}
