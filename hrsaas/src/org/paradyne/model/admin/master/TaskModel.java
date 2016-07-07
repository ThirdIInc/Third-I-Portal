
  package org.paradyne.model.admin.master;

 import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.master.TaskMaster;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;




import org.paradyne.bean.admin.master.TaskMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

/**
 *  
 * @author AA0650
 * to define the business logic for task master
 */
public class TaskModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TaskModel.class);

	/* method name : add 
	 * purpose     : to add the record
	 * return type : boolean
	 * parameter   : bean
	 */
	public boolean add(TaskMaster bean) {
		if (!checkDuplicate(bean)) {
			String query="SELECT NVL(MAX(TYPE_CODE),0)+1 FROM HRMS_TASK_TYPE";
			  Object data[][] = getSqlModel().getSingleResult(query);
			 bean.setTypeCode(String.valueOf(data[0][0]));
			Object addObj[][] = new Object[1][1];
			addObj[0][0] = checkNull(String.valueOf(bean.getTypeName().trim()));
			return getSqlModel().singleExecute(getQuery(1), addObj);
		}//end of if
		else {
			return false;
		}//end of else
	}

	/* method name : mod 
	 * purpose     : to modify the record
	 * return type : boolean
	 * parameter   : bean
	 */
	public boolean mod(TaskMaster bean) {
		if (!checkDuplicateMod(bean)) {
			Object modObj[][] = new Object[1][2];

			modObj[0][0] = checkNull(String.valueOf(bean.getTypeName().trim()));
			modObj[0][1] = checkNull(String.valueOf(bean.getTypeCode().trim()));

			return getSqlModel().singleExecute(getQuery(2), modObj);
		}//end of if
		else {
			return false;
		}//end of else
	}

	/* method name : checkDuplicate 
	 * purpose     : for checking duplicate entry of record during insertion
	 * return type : boolean
	 * parameter   : bean
	 */

	public boolean checkDuplicate(TaskMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_TASK_TYPE WHERE UPPER(TYPE_NAME) LIKE '"
				+ bean.getTypeName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;
	}

	/* method name : checkDuplicateMod 
	 * purpose     : for checking duplicate entry of record during modification 
	 * return type : boolean
	 * parameter   : bean
	 */

	public boolean checkDuplicateMod(TaskMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_TASK_TYPE WHERE UPPER(TYPE_NAME) LIKE '"
				+ bean.getTypeName().trim().toUpperCase()
				+ "' AND TYPE_CODE not in(" + bean.getTypeCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}//end of if
		return result;

	}

	/* method name : delete 
	 * purpose     : to delete the record 
	 * return type : boolean
	 * parameter   : bean
	 */
	public boolean delete(TaskMaster bean) {
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = String.valueOf(bean.getTypeCode());
		return getSqlModel().singleExecute(getQuery(3), delObj);
	}

	/* method name : delete 
	 * purpose     : to display the data in the form while loading
	 * return type : void
	 * parameter   : bean,request
	 */

	public void Data(TaskMaster bean, HttpServletRequest request) {
		Object[][] repData = getSqlModel().getSingleResult(getQuery(4));
		if(repData!=null && repData.length>0){
			bean.setModeLength("true");
			bean.setTotalRecords(String.valueOf(repData.length));		
		String[] pageIndex = Utility.doPaging(bean.getMyPage(), repData.length, 20);	
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
			bean.setMyPage("1");
		ArrayList<Object> List = new ArrayList<Object>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {
			TaskMaster bean1 = new TaskMaster();
			bean1.setTypeCode(checkNull(String.valueOf(repData[i][0])));
			bean1.setTypeName(checkNull(String.valueOf(repData[i][1])));
			List.add(bean1);
		}//end of for loop
		bean.setIteratorlist(List);
		}
	}

	/* method name : calforedit 
	 * purpose     : to edit the record
	 * return type : void
	 * parameter   : bean
	 */
	public void calforedit(TaskMaster bean) {
		String query = "SELECT TYPE_CODE,NVL(TYPE_NAME,' ') FROM HRMS_TASK_TYPE WHERE TYPE_CODE="
				+ bean.getHiddencode();
		Object[][] data = getSqlModel().getSingleResult(query);
		bean.setTypeCode(checkNull(String.valueOf(data[0][0])));
		bean.setTypeName(checkNull(String.valueOf(data[0][1])));
	}

	/* method name : calfordelete 
	 * purpose     : to delete the record
	 * return type : boolean
	 * parameter   : bean
	 */

	public boolean calfordelete(TaskMaster bean) {
		Object[][] delete = new Object[1][1];
		delete[0][0] = bean.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);

	}

	/* method name : deletecheckedRecords ( multiple record)
	 * purpose     : to delete the checked records
	 * return type : boolean
	 * parameter   : bean,code
	 */

	public boolean deletecheckedRecords(TaskMaster bean, String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = getSqlModel().singleExecute(getQuery(3), delete);
					if (!result)
						count++;

				}//end of if
			}//end of for loop
		}//end of if
		if (count != 0) {
			result = false;
			return result;
		}//end of if 
		else {
			result = true;
			return result;
		}//end of else
	}

	/* method name : report 
	 * purpose     : to generate the report for the selected application
	 * return type : void
	 * parameter   : bean,request,response
	 */

	public void report(TaskMaster bean, HttpServletRequest request,
			HttpServletResponse response) {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String s = "\n\nTask Master Report\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				s);
		String query = " SELECT TYPE_CODE,NVL(TYPE_NAME,'') FROM HRMS_TASK_TYPE ORDER BY TYPE_CODE";
		// IMS_STATION_CODE,
		Object result[][] = getSqlModel().getSingleResult(query);
		rg.addFormatedText(s, 6, 0, 1, 0);
		if (result.length > 0)

		{

			Object[][] tabledata = new Object[result.length][3];

			for (int i = 0; i < result.length; i++) {
				tabledata[i][0] = String.valueOf(i + 1);
				tabledata[i][1] = checkNull(String.valueOf(result[i][0]));
				tabledata[i][2] = checkNull(String.valueOf(result[i][1]));

			}//end of for loop
			int[] bcellWidth = { 20, 20, 40 };
			int[] bcellAlign = { 0, 0, 0 };
			String appCol[] = { "Sr no", " Type Code ", "Type Name" };
			
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(appCol, tabledata, bcellWidth, bcellAlign);
		}//end of if
		rg.createReport(response);
	}

	/* method name : checkNull 
	 * purpose     : to check the null values and to make them as blank.
	 * return type : String
	 * parameter   : result
	 */

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean data(TaskMaster taskMaster) {
		// TODO Auto-generated method stub
		
		try {
			Object[] para = new Object[1];
			para[0] = taskMaster.getTypeCode();

			String query = "SELECT NVL(TYPE_NAME,' '),TYPE_CODE FROM HRMS_TASK_TYPE WHERE TYPE_CODE="+para[0] ;
		   Object[][] data = getSqlModel().getSingleResult(query,para);
			
			taskMaster.setTypeName(checkNull(String.valueOf(data[0][0])));
		

			return false;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in data---model");
			return false;
		}
		
	}

}
