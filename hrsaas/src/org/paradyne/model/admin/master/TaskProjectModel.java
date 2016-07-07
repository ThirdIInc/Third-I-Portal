package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.master.TaskProject;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * 
 * @author Krishna
 * Date:07-10-2008
 *
 */
/**
 *  to define the  business logic for task project
 */
public class TaskProjectModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TaskProjectModel.class);

	/* method name : add 
	 * purpose     : to add the record
	 * return type : boolean
	 * parameter   : bean
	 */
	public boolean add(TaskProject bean) {
		if (!checkDuplicate(bean)) {
			 
			Object addObj[][] = new Object[1][2];
			
			String query="SELECT NVL(MAX(PROJECT_CODE),0)+ 1 FROM HRMS_TASK_PROJECT ";
            Object data[][] = getSqlModel().getSingleResult(query);
			 bean.setProCode(String.valueOf(data[0][0]));
			
			addObj[0][0] = checkNull(String.valueOf(bean.getProjName().trim()));
			addObj[0][1] = checkNull(String.valueOf(bean.getIsActive()));
			
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
	public boolean mod(TaskProject bean) {
		if (!checkDuplicateMod(bean)) {
			Object modObj[][] = new Object[1][3];

			modObj[0][0] = checkNull(String.valueOf(bean.getProjName().trim()));
			modObj[0][1] = checkNull(String.valueOf(bean.getIsActive()));
			modObj[0][2] = checkNull(String.valueOf(bean.getProCode().trim()));

			return getSqlModel().singleExecute(getQuery(2), modObj);
		}//end of if
		else {
			return false;
		}//end of else
	}
	public boolean addStakeHolder(TaskProject bean)
	{
		boolean result=false;
		String delQuery="DELETE TASK_STAKEHOLDER_CONFG WHERE TASK_PROJECT_ID="+bean.getProCode();
		result=getSqlModel().singleExecute(delQuery);
		if(result)
		{
			if(bean.getIttrempempCode()!=null && bean.getIttrempempCode().length()>0)
			{
				String stakeHoldId[]=bean.getIttrempempCode().split(",");
				if(stakeHoldId!=null && stakeHoldId.length>0)
				{
					String sqlQuery="INSERT INTO TASK_STAKEHOLDER_CONFG(TASK_PROJECT_ID, TASK_STAKEHOLDER_ID) VALUES(?,?)";
					Object dataObj[][]=new Object[stakeHoldId.length][2];
					for(int i=0;i<stakeHoldId.length;i++)
					{
						System.out.println("stakeHoldId["+i+"] :: "+stakeHoldId[i]);
						dataObj[i][0]=checkNull(String.valueOf(bean.getProCode()));
						dataObj[i][1]=checkNull(String.valueOf(stakeHoldId[i]));
					}
					result=getSqlModel().singleExecute(sqlQuery, dataObj);
				}
			}
			
		}
		return result;
	}
	public boolean saveStatus(TaskProject bean)
	{
		boolean result=false;
		String delQuery="DELETE TASK_PROJECT_STATUS WHERE TASK_PROJECT_ID="+bean.getProCode();
		result=getSqlModel().singleExecute(delQuery);
		if(result)
		{
			if(bean.getIttrProjStatus()!=null && bean.getIttrProjStatus().length()>0)
			{
				String projStatus[]=bean.getIttrProjStatus().split(",");
				if(projStatus!=null && projStatus.length>0)
				{
					Object[][] data = getSqlModel().getSingleResult("(SELECT NVL(MAX(TASK_STATUS_ID),0)+1 FROM TASK_PROJECT_STATUS)");
					int statusId=Integer.parseInt(String.valueOf(data[0][0]));
					String sqlQuery="INSERT INTO TASK_PROJECT_STATUS(TASK_PROJECT_ID, TASK_STATUS_ID, TASK_STATUS) VALUES(?,?,?)";
					Object dataObj[][]=new Object[projStatus.length][3];
					for(int i=0;i<projStatus.length;i++)
					{
						dataObj[i][0]=checkNull(String.valueOf(bean.getProCode()));
						dataObj[i][1]=checkNull(String.valueOf(statusId++));
						dataObj[i][2]=checkNull(String.valueOf(projStatus[i].trim()));
					}
					result=getSqlModel().singleExecute(sqlQuery, dataObj);
				}
			}
			
		}
		return result;
	}
	public void setStockHolderList(TaskProject bean){
		String sqlQuery="SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME) AS NAME,EMP_ID FROM TASK_STAKEHOLDER_CONFG "+ 
						"INNER JOIN HRMS_EMP_OFFC ON (TASK_STAKEHOLDER_CONFG.TASK_STAKEHOLDER_ID=HRMS_EMP_OFFC.EMP_ID)"+
						"WHERE TASK_STAKEHOLDER_CONFG.TASK_PROJECT_ID="+bean.getHiddencode();
		Object[][] data = getSqlModel().getSingleResult(sqlQuery);
		ArrayList<Object> tableList = new ArrayList<Object>();
		if(data!=null && data.length>0){
			for(int i=0;i<data.length;i++){
				TaskProject taskbean=new TaskProject();
				taskbean.setIttrempToken(checkNull(String.valueOf(data[i][0])));
				taskbean.setIttrempName(checkNull(String.valueOf(data[i][1])));
				taskbean.setIttrempempCode(checkNull(String.valueOf(data[i][2])));
				tableList.add(taskbean);
			}
			
			
		}
		bean.setStakeholderlist(tableList);
	}
	public void setStatusList(TaskProject bean){
		String sqlQuery="SELECT TASK_STATUS,TASK_STATUS_ID FROM TASK_PROJECT_STATUS WHERE TASK_PROJECT_ID="+bean.getHiddencode();
						Object[][] data = getSqlModel().getSingleResult(sqlQuery);
						ArrayList<Object> tableList = new ArrayList<Object>();
						if(data!=null && data.length>0){
						for(int i=0;i<data.length;i++){
						TaskProject taskbean=new TaskProject();
						taskbean.setIttrProjStatus(checkNull(String.valueOf(data[i][0])));						
						tableList.add(taskbean);
						}
						
						
						}
			bean.setStatuslist(tableList);
		
	}

	/* method name : checkDuplicate 
	 * purpose     : for checking duplicate entry of record during insertion
	 * return type : boolean
	 * parameter   : bean
	 */

	public boolean checkDuplicate(TaskProject bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_TASK_PROJECT WHERE UPPER(PROJECT_NAME) LIKE '"
				+ bean.getProjName().trim().toUpperCase() + "'";
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

	public boolean checkDuplicateMod(TaskProject bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_TASK_PROJECT WHERE UPPER(PROJECT_NAME) LIKE '"
				+ bean.getProjName().trim().toUpperCase()
				+ "' AND PROJECT_CODE not in(" + bean.getProCode() + ")";
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
	public boolean delete(TaskProject bean) {
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = String.valueOf(bean.getProCode());
		return getSqlModel().singleExecute(getQuery(3), delObj);
	}

	/* method name : delete 
	 * purpose     : to display the data in the form while loading
	 * return type : void
	 * parameter   : bean,request
	 */

	public void Data(TaskProject bean, HttpServletRequest request) {
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
			TaskProject bean1 = new TaskProject();
			bean1.setProCode(checkNull(String.valueOf(repData[i][0])));
			bean1.setProjName(checkNull(String.valueOf(repData[i][1])));
			bean1.setIsActive(checkNull(String.valueOf(repData[i][2])));
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
	public void calforedit(TaskProject bean) {
		String query = "SELECT PROJECT_CODE,NVL(PROJECT_NAME,' '),IS_ACTIVE FROM HRMS_TASK_PROJECT WHERE PROJECT_CODE="
				+ bean.getHiddencode();
		Object[][] data = getSqlModel().getSingleResult(query);
		bean.setProCode(checkNull(String.valueOf(data[0][0])));
		bean.setProjName(checkNull(String.valueOf(data[0][1])));
		bean.setIsActive(checkNull(String.valueOf(data[0][2])));
	}

	/* method name : calfordelete 
	 * purpose     : to delete the record
	 * return type : boolean
	 * parameter   : bean
	 */

	public boolean calfordelete(TaskProject bean) {
		Object[][] delete = new Object[1][1];
		delete[0][0] = bean.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);

	}

	/* method name : deletecheckedRecords 
	 * purpose     : to delete the checked records
	 * return type : boolean
	 * parameter   : bean,code
	 */

	public boolean deletecheckedRecords(TaskProject bean, String[] code) {
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

	public void report(TaskProject bean, HttpServletRequest request,
			HttpServletResponse response) {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String s = "\n\nTask Project Report\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				s);
		String query = " SELECT PROJECT_CODE,NVL(PROJECT_NAME,''), DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_TASK_PROJECT ORDER BY PROJECT_CODE";
		// IMS_STATION_CODE,
		Object result[][] = getSqlModel().getSingleResult(query);
		rg.addFormatedText(s, 6, 0, 1, 0);
		if (result.length > 0)	{
			Object[][] tabledata = new Object[result.length][4];
			for (int i = 0; i < result.length; i++) {
				tabledata[i][0] = String.valueOf(i + 1);
				tabledata[i][1] = checkNull(String.valueOf(result[i][0]));
				tabledata[i][2] = checkNull(String.valueOf(result[i][1]));
				tabledata[i][3] = checkNull(String.valueOf(result[i][2]));
			}//end of for loop
			int[] bcellWidth = { 20, 20, 40, 20 };
			int[] bcellAlign = { 0, 0, 0, 0 };
			String appCol[] = { "Sr no", " Project Code ", "Project Name", "Is Active" };			
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
/**
 *  to display the record after click on search button
 * @param taskProject
 * @return
 */
	public boolean data(TaskProject taskProject) {
		// TODO Auto-generated method stub
		
		try {
			Object[] para = new Object[1];
			para[0] = taskProject.getProCode();
			String query = " SELECT NVL(PROJECT_NAME,''),DECODE(IS_ACTIVE,'Y','YES','N','NO','NO'),PROJECT_CODE FROM HRMS_TASK_PROJECT  where PROJECT_CODE="+para[0]+" ORDER BY PROJECT_CODE";
			Object[][] data = getSqlModel().getSingleResult(query, para);
			taskProject.setProjName(checkNull(String.valueOf(data[0][0])));
			taskProject.setIsActive(checkNull(String.valueOf(data[0][1])));
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in data---model");
			return false;
		}
			
	}
	public void addItem(TaskProject taskProject,String empCode[],String empToken[],String empName[])
	{
		ArrayList<Object> tableList = new ArrayList<Object>();
		try {
			if (empCode != null && empCode.length > 0) // check whether list is
			// empty or not
			{
				for(int i=0;i<empCode.length;i++)
				{
					TaskProject bean1=new TaskProject();
					bean1.setIttrempempCode(empCode[i]);
					bean1.setIttrempToken(empToken[i]);
					bean1.setIttrempName(empName[i]);
					tableList.add(bean1);
					taskProject.setStakeholderlist(tableList);
				}
				if(taskProject.getEmpCode()!=null && taskProject.getEmpCode().length()>0)
				{
				taskProject.setIttrempempCode(taskProject.getEmpCode());
				taskProject.setIttrempToken(taskProject.getEmpToken());
				taskProject.setIttrempName(taskProject.getEmpName());
				tableList.add(taskProject);
				}
				
			}else
			{
				if(taskProject.getEmpCode()!=null && taskProject.getEmpCode().length()>0)
				{
					taskProject.setIttrempempCode(taskProject.getEmpCode());
					taskProject.setIttrempToken(taskProject.getEmpToken());
					taskProject.setIttrempName(taskProject.getEmpName());
					tableList.add(taskProject);
				}
				
				
				taskProject.setStakeholderlist(tableList);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void removeItem(TaskProject taskProject,String empCode[],String empToken[],String empName[])
	{
		ArrayList<Object> tableList = new ArrayList<Object>();
		try {
			
				for(int i=0;i<empCode.length;i++)
				{
					if ((i+1  != Integer.parseInt(taskProject.getParaId()))) {
						TaskProject bean1=new TaskProject();
						bean1.setIttrempempCode(empCode[i]);
						bean1.setIttrempToken(empToken[i]);
						bean1.setIttrempName(empName[i]);
						tableList.add(bean1);
						
					}
					
				}
				
				taskProject.setStakeholderlist(tableList);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

public void report(TaskProject taskProject, HttpServletRequest request,
		HttpServletResponse response, ServletContext context, String[] label) {
	
		// TODO Auto-generated method stub
	
	Date today = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String toDay = sdf.format(today);
	String s = "\n\nTask Project Report\n\n";
	org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
			s);
	String query = " SELECT PROJECT_CODE,NVL(PROJECT_NAME,''), DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') FROM HRMS_TASK_PROJECT ORDER BY PROJECT_CODE";
	// IMS_STATION_CODE,
	Object result[][] = getSqlModel().getSingleResult(query);
	rg.addFormatedText(s, 6, 0, 1, 0);
	if (result.length > 0)	{
		Object[][] tabledata = new Object[result.length][4];
		for (int i = 0; i < result.length; i++) {
			tabledata[i][0] = String.valueOf(i + 1);
			tabledata[i][1] = checkNull(String.valueOf(result[i][0]));
			tabledata[i][2] = checkNull(String.valueOf(result[i][1]));
			tabledata[i][3] = checkNull(String.valueOf(result[i][2]));
		}//end of for loop
		int[] bcellWidth = { 20, 20, 40, 20 };
		int[] bcellAlign = { 0, 0, 0, 0 };
		String appCol[] = { "Sr no", " Project Code ", "Project Name" };			
		rg.addTextBold("Date :" + toDay, 0, 2, 0);
		rg.addText("\n", 0, 0, 0);
		rg.tableBody(appCol, tabledata, bcellWidth, bcellAlign);
	}//end of if
	rg.createReport(response);
	
}

public void addprojStatus(TaskProject taskProject, String[] ittrprojStatus) {
	ArrayList<Object> tableList = new ArrayList<Object>();
	try {
		if (ittrprojStatus != null && ittrprojStatus.length > 0) // check whether list is
		// empty or not
		{
			for(int i=0;i<ittrprojStatus.length;i++)
			{
				TaskProject bean1=new TaskProject();
				bean1.setIttrProjStatus(ittrprojStatus[i]);
				
				tableList.add(bean1);
				taskProject.setStatuslist(tableList);
			}
			if(taskProject.getProjStatus()!=null && taskProject.getProjStatus().length()>0)
			{
				taskProject.setIttrProjStatus(taskProject.getProjStatus());			
				tableList.add(taskProject);
			}
		}else
		{
			if(taskProject.getProjStatus()!=null && taskProject.getProjStatus().length()>0)
			{
				taskProject.setIttrProjStatus(taskProject.getProjStatus());	
				tableList.add(taskProject);
			}
						
			taskProject.setStatuslist(tableList);
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	
}

public void removeStatus(TaskProject taskProject, String[] ittrprojStatus) {
	ArrayList<Object> tableList = new ArrayList<Object>();
	try {
		
			for(int i=0;i<ittrprojStatus.length;i++)
			{
				if ((i+1  != Integer.parseInt(taskProject.getParaId()))) {
					TaskProject bean1=new TaskProject();
					bean1.setIttrProjStatus(ittrprojStatus[i]);
					tableList.add(bean1);
					
				}
				
			}
			
			taskProject.setStatuslist(tableList);
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
}

}
