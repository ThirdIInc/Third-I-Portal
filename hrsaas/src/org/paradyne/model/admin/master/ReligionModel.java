package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.master.ReligionMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author pranali Date 24-04-07
 */
/**
 *  to  define the  business logic for  religion
 */
public class ReligionModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);

	org.paradyne.bean.admin.master.ReligionMaster religionMaster = null;

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(ReligionMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_RELIGION WHERE UPPER(RELIGION_NAME) LIKE '"
				+ bean.getReligionName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* for checking duplicate entry of record during modification */
	public boolean checkDuplicateMod(ReligionMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_RELIGION WHERE UPPER(RELIGION_NAME) LIKE '"
				+ bean.getReligionName().trim().toUpperCase()
				+ "' AND RELIGION_ID not in(" + bean.getReligionID() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* for inserting the data */
	public boolean addReligion(ReligionMaster bean) {
		/*
		 * String query="SELECT UPPER(RELIGION_NAME) FROM HRMS_RELIGION WHERE
		 * (RELIGION_NAME='"+bean.getReligionName().trim().toUpperCase()+"' OR
		 * RELIGION_NAME='"+bean.getReligionName().trim().toLowerCase()+"')";
		 * Object [][]data=getSqlModel().getSingleResult(query);
		 * 
		 * boolean flag=false; if(data.length>0) { flag=false; } else{
		 */
		if (!checkDuplicate(bean)) {
			
			String query="SELECT NVL(MAX(RELIGION_ID),0)+1 FROM  HRMS_RELIGION";
			Object[][]rel=getSqlModel().getSingleResult(query);
			 bean.setReligionID(String.valueOf(rel[0][0]));
			Object[][] addObj = new Object[1][2];
			addObj[0][0] = bean.getReligionName().trim();
			addObj[0][1] = bean.getIsActive();
			return getSqlModel().singleExecute(getQuery(1), addObj);
		}// end of if
		return false;
	}

	/* for modifing the data */
	public boolean modReligion(ReligionMaster bean) {
		if (!checkDuplicateMod(bean)) {
			Object[][] addObj = new Object[1][3];
			addObj[0][0] = bean.getReligionName().trim();
			addObj[0][1] = bean.getIsActive();
			addObj[0][2] = bean.getReligionID();
			return getSqlModel().singleExecute(getQuery(2), addObj);

		}// end of if
		else {
			return false;

		}// end of else
	}

	/* for deleting the record after selecting */
	public boolean deleteReligion(ReligionMaster bean) {
		//logger.info("in deleteReligion");
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getReligionID();
		//logger.info("before singleExecute in delete");
		return getSqlModel().singleExecute(getQuery(3), addObj);

	}

	public void getReligionReport(ReligionMaster regMaster) {

		Object[][] data = getSqlModel().getSingleResult(getQuery(4));

		ArrayList<Object> att = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			ReligionMaster bean1 = new ReligionMaster();

			bean1.setReligionID(checkNull(String.valueOf(data[i][0])));

			bean1.setReligionName(checkNull(String.valueOf(data[i][1])));

			att.add(bean1);

		}// end of loop
		regMaster.setAtt(att);

	}
/**
 *  to check the null value
 * @param result
 * @return
 */
	public String checkNull(String result) {
		if (result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}
/**
 *  to generate the report
 * @param regMaster
 * @param request
 * @param response
 * @param context
 * @param label
 */
	public void getReport(ReligionMaster regMaster, HttpServletRequest request,
			HttpServletResponse response, ServletContext context,String[]label) {
		// TODO Auto-generated method stub

		/*CrystalReport cr = new CrystalReport();
		String path = "org\\paradyne\\rpt\\admin\\master\\relegion.rpt";
		cr.createReport(request, response, context, session, path, "");
*/
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nReligion Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Religion  Master.Pdf");
		String queryDes = "SELECT RELIGION_NAME,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_RELIGION ORDER BY upper(RELIGION_NAME)";
						
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
			int cellwidth[] = { 10, 40, 20 };
			int alignment[] = { 1, 0, 0};
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

	/* generating the list in  onload */
	public void religionData(ReligionMaster regMaster,
			HttpServletRequest request) {

		Object[][] repData = getSqlModel().getSingleResult(getQuery(4));
		if(repData!=null && repData.length>0){
			
			regMaster.setModeLength("true");
			regMaster.setTotalRecords(String.valueOf(repData.length));
		String[] pageIndex = Utility.doPaging(regMaster.getMyPage(), repData.length, 20);	
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
			regMaster.setMyPage("1");
		ArrayList<Object> List = new ArrayList<Object>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
			ReligionMaster bean1 = new ReligionMaster();
			bean1.setReligionID(checkNull(String.valueOf(repData[i][0])));
			bean1.setReligionName(checkNull(String.valueOf(repData[i][1])));
			bean1.setIsActive(checkNull(String.valueOf(repData[i][2])));

			List.add(bean1);
		}// end of loop

		regMaster.setAtt(List);
		}
	}
/**
 *   to modify the data after double click
 * @param regMaster
 */
	public void calforedit(ReligionMaster regMaster) {

		String query = "  SELECT RELIGION_ID,RELIGION_NAME,IS_ACTIVE FROM HRMS_RELIGION   where RELIGION_ID= "
				+ regMaster.getHiddencode() + "    ORDER BY  RELIGION_ID";

		Object[][] data = getSqlModel().getSingleResult(query);
		regMaster.setReligionID(String.valueOf(data[0][0]));
		regMaster.setReligionName(String.valueOf(data[0][1]));
		regMaster.setIsActive(String.valueOf(data[0][2]));

		// TODO Auto-generated method stub

	}

	/* for selecting the record from list by double clicking */
	public boolean calfordelete(ReligionMaster regMaster) {

		Object[][] delete = new Object[1][1];
		delete[0][0] = regMaster.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);

	}

	/*
	 * public boolean deleteReligion(ReligionMaster regMaster, String[] code) {
	 * boolean result=false; boolean flag=false; if(code !=null) { for (int i =
	 * 0; i < code.length; i++) {
	 * 
	 * if(!code[i].equals("")){ logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"+code[i]);
	 * Object [][] delete = new Object [1][1]; delete [0][0] =code[i] ; flag
	 * =getSqlModel().singleExecute(getQuery(3), delete); if(flag) result=true; } } }
	 * return result;
	 *  }
	 */
	/* for deleting one or more records from list */
	public String deletecheckedRecords(ReligionMaster regMaster, String[] code) {
		String result = "";
		int count = 0;

		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					boolean res;
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					res = getSqlModel().singleExecute(getQuery(3), delete);
					if (!res)
						count++;

				}// end of if
			}// end of loop

		}// end of nested if
		if (count != 0) {
			result = "false";
		}// end of if
		else
			result = "true";

		return result;
	}

	/**
	 *  display the record after clicking on search button
	 * @param regMaster
	 */
	public void data(ReligionMaster regMaster) {
		// TODO Auto-generated method stub
		
		try {
			Object[] para = new Object[1];
			para[0] = regMaster.getReligionID();
			String query = " SELECT RELIGION_ID, RELIGION_NAME,  FROM HRMS_RELIGION WHERE RELIGION_ID ="+para[0]+" ORDER BY RELIGION_ID ";

			Object data[][] = getSqlModel().getSingleResult(query,para);

			regMaster.setReligionName(checkNull(String.valueOf(data[0][0])));
			regMaster.setIsActive(checkNull(String.valueOf(data[0][1])));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in data---model");
		}
	}
}
