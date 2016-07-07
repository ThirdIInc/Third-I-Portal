package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;

import org.paradyne.bean.admin.master.CasteMaster;
/**
 *  
 * @author AA0650
 *
 * to define the business logic for  caste 
 */
public class CasteModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	CasteMaster divMast = null;

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(CasteMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_CAST WHERE UPPER(CAST_NAME) LIKE '"
				+ bean.getCasteName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;

	}

	/* for checking duplicate entry of record during modification */
	public boolean checkDuplicateMod(CasteMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_CAST WHERE UPPER(CAST_NAME) LIKE '"
				+ bean.getCasteName().trim().toUpperCase()
				+ "' AND CAST_ID not in(" + bean.getCasteCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;

	}

	/* for inserting the data */
	public boolean add(CasteMaster bean) {
		/*
		 * String query =" SELECT CAST_NAME FROM HRMS_CAST WHERE CAST_NAME LIKE
		 * '"+bean.getCasteName()+"'"; Object
		 * result[][]=getSqlModel().getSingleResult(query); if(result.length>0) {
		 * return false; }else {
		 */
		if (!checkDuplicate(bean)) {
			String query="SELECT NVL(MAX(CAST_ID),0)+1 FROM  HRMS_CAST";
			Object[][]cast=getSqlModel().getSingleResult(query);
			 bean.setCasteCode(String.valueOf(cast[0][0]));
			Object[][] addObj = new Object[1][3];
			
			addObj[0][0] = bean.getCasteName().trim();
			addObj[0][1] = bean.getCasteCatgCode().trim();
			addObj[0][2] = bean.getIsActive();
			return getSqlModel().singleExecute(getQuery(1), addObj);
		}//end of if
		return false;
	}

	/* for modifing the data */
	public boolean updateCaste(CasteMaster bean) {
		/*
		 * String query =" SELECT CAST_NAME FROM HRMS_CAST WHERE CAST_NAME LIKE
		 * '"+bean.getCasteName()+"'"; Object
		 * result[][]=getSqlModel().getSingleResult(query); if(result.length>1) {
		 * return false; }else {
		 */
		if (!checkDuplicateMod(bean)) {
			Object[][] addObj = new Object[1][4];
			addObj[0][0] = bean.getCasteName().trim();
			addObj[0][1] = bean.getCasteCatgCode().trim();
			addObj[0][2] = bean.getIsActive();
			addObj[0][3] = bean.getCasteCode().trim();
			return getSqlModel().singleExecute(getQuery(2), addObj);
		}//end of if
		else {
			return false;
		}//end of else
	}

	/* for deleting the  single record  */
	public boolean delete(CasteMaster bean) {
		Object[][] addObj = new Object[1][1];
		addObj[0][0] = bean.getCasteCode();
		return getSqlModel().singleExecute(getQuery(3), addObj);
	}

	public void callReport(CasteMaster bean) {

		String query = " SELECT  CAST_ID, CAST_NAME , CATG_NAME , HRMS_CAST.DECODE(IS_ACTIVE,'Y','YES','N','NO','NO')   FROM HRMS_CAST  "
				+ " left JOIN HRMS_CATG ON HRMS_CAST.CAST_CATG=HRMS_CATG.CATG_ID ORDER BY CAST_ID ";

		Object data[][] = getSqlModel().getSingleResult(query);

		ArrayList<Object> casteList = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			CasteMaster bean1 = new CasteMaster();
			//logger.info("VSAliue of id======" + String.valueOf(data[i][0]));
			bean1.setCasteCode(checkNull(String.valueOf(data[i][0])));
			bean1.setCasteName(checkNull(String.valueOf(data[i][1])));
			bean1.setCasteCatgName(checkNull(String.valueOf(data[i][2])));
			bean1.setIsActive(checkNull(String.valueOf(data[i][3])));
			casteList.add(bean1);

		}//end of loop
		bean.setCasteList(casteList);

	}
/**
 *  to generate the report  for caste
 * @param casteMaster
 * @param request
 * @param response
 * @param context
 * @param session
 * @param label
 */
	public void getReport(CasteMaster casteMaster, HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			HttpSession session,String []label) {
		/*CrystalReport cr = new CrystalReport();
		String path = "org\\paradyne\\rpt\\admin\\master\\caste.rpt";
		cr.createReport(request, response, context, session, path, "");*/
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		
		String reportName = "\n\nCaste Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Caste  Master.Pdf");
		String queryDes = "SELECT   CAST_NAME , CATG_NAME ,DECODE(HRMS_CAST.IS_ACTIVE,'Y','YES','N','NO','NO')  FROM HRMS_CAST  " 
               +" left JOIN HRMS_CATG ON HRMS_CAST.CAST_CATG=HRMS_CATG.CATG_ID ORDER BY upper(CAST_NAME)";
						
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
			int cellwidth[] = { 15, 30, 30, 15 };
			int alignment[] = { 1, 0, 0, 0};
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
		}//end of if
		else {
			return result;
		}//end of else
	}

	/* generating the list in onload */

	public void castData(CasteMaster casteMaster, HttpServletRequest request) {
		Object[][] repData = getSqlModel().getSingleResult(getQuery(7));
		
		if(repData!=null && repData.length>0){
			casteMaster.setModeLength("true");
			casteMaster.setTotalRecords(String.valueOf(repData.length));
	
		String[] pageIndex = Utility.doPaging(casteMaster.getMyPage(), repData.length, 20);	
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
			casteMaster.setMyPage("1");
		ArrayList<Object> List = new ArrayList<Object>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
			
			CasteMaster bean1 = new CasteMaster();
			bean1.setCasteCode(checkNull(String.valueOf(repData[i][0])));
			bean1.setCasteName(checkNull(String.valueOf(repData[i][1])));
			bean1.setCasteCatgName(checkNull(String.valueOf(repData[i][2])));
			bean1.setCasteCatgCode(checkNull(String.valueOf(repData[i][3])));
			bean1.setIsActive(checkNull(String.valueOf(repData[i][4])));

			List.add(bean1);
		}//end of loop

		casteMaster.setCasteList(List);
		}
	}

	/* for selecting the record from list by double clicking */
	public void calforedit(CasteMaster casteMaster) {
		
		System.out.println("casteMaster.getHiddencode()  -- "+casteMaster.getHiddencode() );
		
		String query = " SELECT CAST_ID, CAST_NAME, CATG_NAME, CAST_CATG,DECODE(HRMS_CAST.IS_ACTIVE,'Y','YES','N','NO') FROM HRMS_CAST "
				+ " left JOIN HRMS_CATG ON HRMS_CAST.CAST_CATG=HRMS_CATG.CATG_ID"
				+ "  WHERE  CAST_ID = "+casteMaster.getHiddencode() ;

		Object[][] casteData = getSqlModel().getSingleResult(query);
		
		if(casteData!= null && casteData.length > 0) {
			casteMaster.setCasteCode(String.valueOf(casteData[0][0]));
			casteMaster.setCasteName(String.valueOf(casteData[0][1]));
			casteMaster.setCasteCatgName(String.valueOf(casteData[0][2]));
			casteMaster.setCasteCatgCode(String.valueOf(casteData[0][3]));
			casteMaster.setIsActive(String.valueOf(casteData[0][4]));
			
		} else {
			logger.info("NO  RECORDS FOUND");
		}
		
	/*	System.out.println("String.valueOf(casteData[0][0]) -- "+String.valueOf(casteData[0][0]));
		System.out.println("String.valueOf(casteData[0][1]) -- "+String.valueOf(casteData[0][1]));
		System.out.println("String.valueOf(casteData[0][2]) -- "+String.valueOf(casteData[0][2]));
		System.out.println("String.valueOf(casteData[0][3]) -- "+String.valueOf(casteData[0][3]));
		System.out.println("String.valueOf(casteData[0][4]) -- "+String.valueOf(casteData[0][4])); */

	}

	/* for deleting  single records from list */
	public boolean calfordelete(CasteMaster casteMaster) {
		Object[][] delete = new Object[1][1];
		delete[0][0] = casteMaster.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);
	}

	/*
	 * public boolean deleteCast(CasteMaster casteMaster, String[] code) {
	 * boolean result=false; boolean flag=false; if(code !=null) { for (int i =
	 * 0; i < code.length; i++) {
	 * 
	 * if(!code[i].equals("")){ logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"+code[i]);
	 * Object [][] delete = new Object [1][1]; delete [0][0] =code[i] ; flag
	 * =getSqlModel().singleExecute(getQuery(3), delete); if(flag) result=true; } } }
	 * return result; }
	 */

	/* for deleting one or more records from list */
	public String deletecheckedRecords(CasteMaster casteMaster, String[] code) {
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

				}//end of if
			}//end of loop

		}//end of nested if
		if (count != 0) {
			result = "false";
		}//end of if
		else
			result = "true";

		return result;
	}
/**
 *  to display  the record after clcking on  search button
 * @param casteMaster
 * @return
 */
	public boolean data(CasteMaster casteMaster) {
		// TODO Auto-generated method stub
		try {
			Object[] para = new Object[1];
			para[0] = casteMaster.getCasteCode();

			//logger.info("before query...." + para[0]);
			
			String query = " SELECT  CAST_ID, CAST_NAME , CATG_NAME ,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO')   FROM HRMS_CAST  "
				+ " left JOIN HRMS_CATG ON HRMS_CAST.CAST_CATG=HRMS_CATG.CATG_ID " +
			    "where  CAST_ID = "+ para[0]+" ORDER BY CAST_ID ";
			
			Object[][] data = getSqlModel().getSingleResult(query, para);
			casteMaster.setCasteName(checkNull(String.valueOf(data[0][1])));
			casteMaster.setCasteCatgName(checkNull(String.valueOf(data[0][2])));
			casteMaster.setIsActive(checkNull(String.valueOf(data[0][3])));
			

			return false;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in data---model");
			return false;
		}
	}
}