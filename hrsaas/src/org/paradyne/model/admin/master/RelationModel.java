package org.paradyne.model.admin.master;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.bean.admin.master.RelationMaster;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  to define the business logic for relation
 * @author AA0650(Dilip)
 *
 */
public class RelationModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	org.paradyne.bean.admin.master.RelationMaster relMaster = null;

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(RelationMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_RELATION WHERE UPPER(RELATION_NAME) LIKE '"
				+ bean.getRelationName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;

	}

	/* for checking duplicate entry of record during modification */
	public boolean checkDuplicateMod(RelationMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_RELATION WHERE UPPER(RELATION_NAME) LIKE '"
				+ bean.getRelationName().trim().toUpperCase()
				+ "' AND RELATION_CODE not in(" + bean.getRelationCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;

	}

	/* for inserting the data */
	public boolean addRelation(RelationMaster bean) {
		/*
		 * String query="SELECT UPPER(RELATION_NAME) FROM HRMS_RELATION WHERE
		 * (RELATION_NAME='"+bean.getRelationName().trim().toUpperCase()+"' OR
		 * RELATION_NAME='"+bean.getRelationName().trim().toLowerCase()+"')";
		 * Object [][]data=getSqlModel().getSingleResult(query);
		 * 
		 * boolean flag=false; if(data.length>0) { flag=false; } else{
		 */
		if (!checkDuplicate(bean)) {
			
			String query="SELECT NVL(MAX(RELATION_CODE),0)+1 FROM  HRMS_RELATION";
			Object[][]rel=getSqlModel().getSingleResult(query);
			 bean.setRelationCode(String.valueOf(rel[0][0]));
			Object addObj[][] = new Object[1][2];
			addObj[0][0] = bean.getRelationName().trim();
			addObj[0][1] = bean.getIsActive();
			return getSqlModel().singleExecute(getQuery(1), addObj);
		}//end of if
		return false;
	}

	/* for modifing the data */
	public boolean modRelation(RelationMaster bean) {
		if (!checkDuplicateMod(bean)) {
			Object addObj[][] = new Object[1][3];
			addObj[0][0] = bean.getRelationName().trim();
			addObj[0][1] = bean.getIsActive();
			addObj[0][2] = bean.getRelationCode();
			return getSqlModel().singleExecute(getQuery(2), addObj);
		}//end of if
		else {
			return false;
		}//end of else
	}

	/* for deleting the record after selecting */
	public boolean deleteRelation(RelationMaster bean) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getRelationCode();
		return getSqlModel().singleExecute(getQuery(3), addObj);

	}

	public void getRelationRecord(RelationMaster bean) {
		Object addObj[] = new Object[1];

		addObj[0] = bean.getRelationCode();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);

		for (int i = 0; i < data.length; i++) {
			//logger.info("I am 2");
			bean.setRelationCode(String.valueOf(data[i][0]));
			bean.setRelationName(String.valueOf(data[i][1]));
			bean.setIsActive(String.valueOf(data[i][2]));

		}//end of loop
	}

	public void getRelationReport(RelationMaster bean) {

		Object[][] data = getSqlModel().getSingleResult(getQuery(5));

		ArrayList<Object> relList = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			RelationMaster bean1 = new RelationMaster();
			bean1.setRelationCode(String.valueOf(data[i][0]));
			bean1.setRelationName(String.valueOf(data[i][1]));
			bean1.setIsActive(String.valueOf(data[i][2]));

			relList.add(bean1);
		}//end of loop
		bean.setRelationList(relList);

	}
/**
 *  to generate the report
 * @param relMaster
 * @param request
 * @param response
 * @param context
 * @param session
 * @param label
 */
	public void getReport(RelationMaster relMaster, HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			HttpSession session,String [] label) {
		/*CrystalReport cr = new CrystalReport();
		String path = "org\\paradyne\\rpt\\admin\\master\\relation.rpt";
		cr.createReport(request, response, context, session, path, "");*/
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nRelation  Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Relation  Master.Pdf");
		String queryDes = "SELECT RELATION_NAME , DECODE(IS_ACTIVE,'Y','YES','N','NO') FROM HRMS_RELATION ORDER BY upper(RELATION_NAME)";
						
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
			int cellwidth[] = { 15, 40, 40 };
			int alignment[] = { 1, 0, 0};
			rg.addFormatedText(reportName, 6, 0, 1, 0);
			rg.addText("\n",0,0,0);
			rg.addTextBold("Date :"+toDay, 0, 2, 0);
			rg.addText("\n",0,0,0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}
		rg.createReport(response);
	}

	/* generating the list onload */
	public void show1(RelationMaster relMaster, HttpServletRequest request) {

		Object data[][] = getSqlModel().getSingleResult(getQuery(5));
	
		if(data!=null && data.length>0){
			relMaster.setModeLength("true");
			relMaster.setTotalRecords(String.valueOf(data.length));
		
		String[] pageIndex = Utility.doPaging(relMaster.getMyPage(), data.length, 20);	
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
			relMaster.setMyPage("1");
		ArrayList<Object> List = new ArrayList<Object>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
			
			RelationMaster bean1 = new RelationMaster();
			bean1.setRelationCode(checkNull(String.valueOf(data[i][0])));
			bean1.setRelationName(checkNull(String.valueOf(data[i][1])));
			bean1.setIsActive(checkNull(String.valueOf(data[i][2])));

			List.add(bean1);
		}//end of loop
		relMaster.setRelationList(List);
		}
	}

	// TODO Auto-generated method stub
/**
 *  to check null value
 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}//end of if
		else {
			return result;
		}//end of else
	}

	/* for selecting the record from list on  double click */
	public void calforedit(RelationMaster relMaster) {
		// TODO Auto-generated method stub
		/*String query = "  SELECT RELATION_CODE,RELATION_NAME,DECODE(IS_ACTIVE,'Y','YES','N','NO') FROM HRMS_RELATION  where RELATION_CODE= "
				+ relMaster.getHiddencode() + "    ORDER BY RELATION_CODE"; */
		
		String query = "  SELECT RELATION_CODE,RELATION_NAME,IS_ACTIVE FROM HRMS_RELATION  where RELATION_CODE= "
			+ relMaster.getHiddencode() + "    ORDER BY RELATION_CODE";

		Object[][] data = getSqlModel().getSingleResult(query);
		relMaster.setRelationCode(String.valueOf(data[0][0]));
		relMaster.setRelationName(String.valueOf(data[0][1]));
		relMaster.setIsActive(String.valueOf(data[0][2]));

	}
	/**
	 *  to delete the single record
	 * @param relMaster
	 * @return
	 */

	public boolean calfordelete(RelationMaster relMaster) {

		Object[][] delete = new Object[1][1];
		delete[0][0] = relMaster.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);
		// TODO Auto-generated method stub

	}

	/*
	 * public boolean deleteRelation1(RelationMaster relMaster2, String[] code) {
	 * boolean result=false; boolean flag=false; if(code !=null) { for (int i =
	 * 0; i < code.length; i++) {
	 * 
	 * if(!code[i].equals("")){ logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"+code[i]);
	 * Object [][] delete = new Object [1][1]; delete [0][0] =code[i] ; flag
	 * =getSqlModel().singleExecute(getQuery(3), delete); if(flag) result=true; } } }
	 * return result; }
	 */
	/* for delecting one or more records from list */
	
/*	
	public boolean deleteEmptype(EmpTypeMaster typMaster, String[] code) {

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
					}//end of if
					//result=true;
				}//end of if
			}//end of loop
		}//end of nested if
		if (cnt > 0) {
			result = false;
		}//end of if
		else
			result = true;
		return result;

	}*/
	
	/**
	 *  to delete the multiple record form the list
	 */
	public boolean deletecheckedRecords(RelationMaster relMaster2, String[] code) {
		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					//logger .info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->" + code[i]);
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					flag = getSqlModel().singleExecute(getQuery(3), delete);
					if (!flag) {
						cnt++;
					}//end of if
					//result=true;
				}//end of if
			}//end of loop
		}//end of nested if
		if (cnt > 0) {
			result = false;
		}//end of if
		else
			result = true;
		return result;

	}

}