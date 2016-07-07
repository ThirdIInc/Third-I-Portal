/**
 * 
 */
package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.master.CategoryMaster;
import org.paradyne.bean.admin.master.DisciplineMaster;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author Hemant Nagam
 * 
 */
/**
 *  to define the business logic for category master
 */
public class CategoryModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	CategoryMaster acrMaster = null;

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(CategoryMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM  HRMS_CATG WHERE UPPER(CATG_NAME) LIKE '"
				+ bean.getCatgName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;

	}

	/* for checking duplicate entry of record during modification */
	public boolean checkDuplicateMod(CategoryMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM  HRMS_CATG WHERE UPPER(CATG_NAME) LIKE '"
				+ bean.getCatgName().trim().toUpperCase()
				+ "' AND CATG_ID not in(" + bean.getCatgID() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;

	}

	/* for inserting the data */
	public boolean addCategory(CategoryMaster catgMaster) {
		if (!checkDuplicate(catgMaster)) {
			Object addObj[][] = new Object[1][4];
			String query="SELECT NVL(MAX(CATG_ID),0)+1 FROM HRMS_CATG ";
			Object add[][]=getSqlModel().getSingleResult(query);
			catgMaster.setCatgID(String.valueOf(add[0][0]));
			
			addObj[0][0] = catgMaster.getCatgName().trim();
			addObj[0][1] = catgMaster.getCatgDesc().trim();
			addObj[0][2] = catgMaster.getCatgAge().trim();
			addObj[0][3] = catgMaster.getIsActive();
			return getSqlModel().singleExecute(getQuery(1), addObj);
		}//end of if
		return false;
	}

	/* for modifing the data */
	public boolean modCategory(CategoryMaster catgMaster) {
		if (!checkDuplicateMod(catgMaster)) {
			Object addObj[][] = new Object[1][5];
			addObj[0][0] = catgMaster.getCatgName().trim();
			addObj[0][1] = catgMaster.getCatgDesc().trim();
			addObj[0][2] = catgMaster.getCatgAge().trim();
			addObj[0][3] = catgMaster.getIsActive();
			addObj[0][4] = catgMaster.getCatgID().trim();
			return getSqlModel().singleExecute(getQuery(2), addObj);
		}//end of if
		else {
			return false;
		}//end of else
	}

	/* for deleting the record after selecting */
	public boolean deleteCategory(CategoryMaster catgMaster) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = catgMaster.getCatgID();
		return getSqlModel().singleExecute(getQuery(3), addObj);
	}

	public void getCategoryRecord(CategoryMaster catgMaster) {
		//logger.info("\n\n*****CATEGORY===>");
		Object addObj[] = new Object[1];
		addObj[0] = catgMaster.getCatgID();

		Object data[][] = getSqlModel().getSingleResult(getQuery(4), addObj);

		for (int i = 0; i < data.length; i++) {
			catgMaster.setCatgID(String.valueOf(data[i][0]));
			catgMaster.setCatgName(String.valueOf(data[i][1]));
			catgMaster.setIsActive(String.valueOf(data[i][2]));
			catgMaster.setCatgDesc(checkNull(String.valueOf(data[i][3])));
			catgMaster.setCatgAge(checkNull(String.valueOf(data[i][4])));
		}//end of loop

		//logger.info("CAT NAME--->" + catgMaster.getCatgName());
		//logger.info("CAT Desc--->" + catgMaster.getCatgDesc());
		//logger.info("CAT age--->" + catgMaster.getCatgAge());

		catgMaster.setCatgName(checkNull(catgMaster.getCatgName()));
		//catgMaster.setIsActive(checkNull(catgMaster.getIsActive()));
		catgMaster.setCatgDesc(checkNull(catgMaster.getCatgDesc()));
		catgMaster.setCatgAge(checkNull(catgMaster.getCatgAge()));

	}

	public void getCategoryReport(CategoryMaster catgMaster) {

		Object data[][] = getSqlModel().getSingleResult(getQuery(5));
		ArrayList<Object> catgList = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			CategoryMaster bean1 = new CategoryMaster();
			bean1.setCatgID(checkNull(String.valueOf(data[i][0])));
			bean1.setCatgName(checkNull(String.valueOf(data[i][1])));
			bean1.setCatgDesc(checkNull(String.valueOf(data[i][2])));
			bean1.setCatgAge(checkNull(String.valueOf(data[i][3])));
			catgList.add(bean1);
		}//end of loop
		catgMaster.setCatg(catgList);
	}
/**
 *  to check  the value
 * @param result
 * @return
 */
	public String checkNull(String result) {
		//logger.info("\n\n\n\n****HEMANT");
		if (result == null || result.equals("null") || result.equals(" ")) {
			return "";
		}//end of if
		else {
			return result;
		}//end of else
	}
/**
 *  to generate the report
 * @param catgMaster
 * @param request
 * @param response
 * @param context
 * @param label
 */
	public void getReport(CategoryMaster catgMaster,
			HttpServletRequest request, HttpServletResponse response,
			ServletContext context,String[]label) {
		// TODO Auto-generated method stub
		/*CrystalReport cr = new CrystalReport();
		String path = "org\\paradyne\\rpt\\admin\\master\\category.rpt ";
		cr.createReport(request, response, context, session, path, "");*/
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nCategory  Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Category  Master.Pdf");
		String queryDes = " SELECT CATG_NAME,CATG_DESC,CATG_AGE,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_CATG  ORDER BY UPPER(CATG_NAME)";
						
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][5];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];
				Data[i][4] = data[i][3];
			
				j++;
			}
			int cellwidth[] = { 15, 40, 40, 30, 20};
			int alignment[] = { 1, 0, 0, 0, 0};
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

	/* generating the list in onload */
	public void categoryData(CategoryMaster catgMaster,
			HttpServletRequest request) {
		Object[][] repData = getSqlModel().getSingleResult(getQuery(6));
		if(repData!=null && repData.length>0){
			catgMaster.setModeLength("true");
			catgMaster.setTotalRecords(String.valueOf(repData.length));	
		String[] pageIndex = Utility.doPaging(catgMaster.getMyPage(), repData.length, 20);	
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
			catgMaster.setMyPage("1");
		ArrayList<Object> List = new ArrayList<Object>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
			CategoryMaster bean1 = new CategoryMaster();
			bean1.setCatgID(checkNull(String.valueOf(repData[i][0])));
			bean1.setCatgName(checkNull(String.valueOf(repData[i][1])));
			bean1.setCatgDesc(checkNull(String.valueOf(repData[i][2])));
			bean1.setIsActive(checkNull(String.valueOf(repData[i][3])));
			List.add(bean1);
		}//end of loop

		catgMaster.setCatg(List);
		}
	}

	/* for selecting the record from list by double clicking */

	public void calforedit(CategoryMaster catgMaster) {
		String query = " SELECT  CATG_ID,NVL(CATG_NAME,' '),NVL(CATG_DESC,' '),NVL(CATG_AGE,' '),IS_ACTIVE FROM HRMS_CATG  WHERE CATG_ID="
				+ catgMaster.getHiddencode();

		Object[][] data = getSqlModel().getSingleResult(query);
		catgMaster.setCatgID(String.valueOf(data[0][0]));
		catgMaster.setCatgName(String.valueOf(data[0][1]));
		catgMaster.setCatgDesc(String.valueOf(data[0][2]));
		catgMaster.setCatgAge(String.valueOf(data[0][3]));
		catgMaster.setIsActive(String.valueOf(data[0][4]));

	}
/**
 *  to delete the single record  after clicking on save/search 
 * @param catgMaster
 * @return
 */
	public boolean calfordelete(CategoryMaster catgMaster) {
		Object[][] delete = new Object[1][1];
		delete[0][0] = catgMaster.getHiddencode();
		//logger.info("shashikant---------------------" + catgMaster.getHiddencode());
		return getSqlModel().singleExecute(getQuery(3), delete);

	}

	/*
	 * public boolean deleteCat(CategoryMaster catgMaster, String[] code) {
	 * 
	 * 
	 * boolean result=false; boolean flag=false; if(code !=null) { for (int i =
	 * 0; i < code.length; i++) {
	 * 
	 * if(!code[i].equals("")){ logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"+code[i]);
	 * Object [][] delete = new Object [1][1]; delete [0][0] =code[i] ; flag
	 * =getSqlModel().singleExecute(getQuery(3), delete); if(flag) result=true; } } }
	 * return result; }
	 */

	/* for deleting one or more records from list */
	public String deletecheckedRecords(CategoryMaster catgMaster, String[] code) {
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

}
