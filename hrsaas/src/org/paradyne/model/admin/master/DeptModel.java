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

import org.paradyne.bean.admin.master.DeptMaster;
import org.paradyne.bean.admin.master.DivisionMaster;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author lakkichand
 * 
 */
public class DeptModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	DeptMaster dptMaster = null;

	/* for checking duplicate entry of record during insertion */

	public boolean checkDuplicate(DeptMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_DEPT WHERE UPPER(DEPT_NAME) LIKE '"
				+ bean.getDeptName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* for checking duplicate entry of record during modification */

	public boolean checkDuplicateMod(DeptMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_DEPT WHERE UPPER(DEPT_NAME) LIKE '"
				+ bean.getDeptName().trim().toUpperCase()
				+ "' AND DEPT_ID not in(" + bean.getDeptID() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* for inserting record */
	public boolean addDept(DeptMaster bean) {
		// Object data1[][]=new Object[1][1];
		// data1=
		if (!checkDuplicate(bean)) {
			String query = "SELECT NVL(MAX(DEPT_ID),0)+1 FROM  HRMS_DEPT";
			Object[][] rel = getSqlModel().getSingleResult(query);
			bean.setDeptID(String.valueOf(rel[0][0]));
			
			Object addObj[][] = new Object[1][7];
			addObj[0][0] = bean.getDeptName().trim();
			addObj[0][1] = bean.getDeptAbbr().trim();
			addObj[0][2] = bean.getDeptDivCode().trim();
			addObj[0][3] = bean.getDeptDesc().trim();
			addObj[0][4] = bean.getDeptLev().trim();
			addObj[0][5] = bean.getDeptParID().trim();
			addObj[0][6] = bean.getIsActive().trim();//added
			System.out.println("########String.valueOf(rel[0][0])##########"+String.valueOf(addObj[0][0]));
			System.out.println("########String.valueOf(rel[0][0])##########"+String.valueOf(addObj[0][1]));
			System.out.println("########String.valueOf(rel[0][0])##########"+String.valueOf(addObj[0][2]));
			System.out.println("########String.valueOf(rel[0][0])##########"+String.valueOf(addObj[0][3]));
			System.out.println("########String.valueOf(rel[0][0])##########"+String.valueOf(addObj[0][4]));
			System.out.println("########String.valueOf(rel[0][0])##########"+String.valueOf(addObj[0][5]));
			System.out.println("########String.valueOf(rel[0][0])##########"+String.valueOf(addObj[0][6]));
			return getSqlModel().singleExecute(getQuery(1), addObj);
		}// end of if

		else {
			return false;
		}// end of else
	}

	/* for modifing the record */
	public boolean modDept(DeptMaster bean) {
		if (!checkDuplicateMod(bean)) {
			Object addObj[][] = new Object[1][8];
			//logger.info("In Modify method");
			addObj[0][0] = bean.getDeptName().trim();
			addObj[0][1] = bean.getDeptAbbr().trim();
			addObj[0][2] = bean.getDeptDivCode().trim();
			addObj[0][3] = bean.getDeptDesc().trim();
			addObj[0][4] = bean.getDeptLev().trim();
			addObj[0][5] = bean.getDeptParID().trim();
			addObj[0][6] = bean.getIsActive().trim();///added
			addObj[0][7] = bean.getDeptID().trim();
			return getSqlModel().singleExecute(getQuery(2), addObj);
		}// end of if
		else
			return false;
	}

	/* for deleting the record after selecting */
	public boolean deleteDept(DeptMaster bean) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getDeptID();
		return getSqlModel().singleExecute(getQuery(3), addObj);
	}

	public void getDeptRecord(DeptMaster bean) {
		Object addObj[] = new Object[1];
		addObj[0] = bean.getDeptID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);

		for (int i = 0; i < data.length; i++) {
			bean.setDeptID(checkNull(String.valueOf(data[0][0])));
			bean.setDeptName(checkNull(String.valueOf(data[0][1])));
			bean.setDeptParName(checkNull(String.valueOf(data[0][2])));
			bean.setDeptAbbr(checkNull(String.valueOf(data[0][3])));
			bean.setDeptDivCode(checkNull(String.valueOf(data[0][4])));
			bean.setDivName(checkNull(String.valueOf(data[0][5])));
			bean.setDeptDesc(checkNull(String.valueOf(data[0][6])));
			bean.setDeptLev(checkNull(String.valueOf(data[0][7])));
			bean.setDeptParID(checkNull(String.valueOf(data[0][8])));
			bean.setIsActive(checkNull(String.valueOf(data[0][9])));//added

		}// end of loop
	}

	public void getDeptReport(DeptMaster bean) {

		Object[][] data = getSqlModel().getSingleResult(getQuery(5));

		ArrayList<Object> dptList = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			DeptMaster bean1 = new DeptMaster();
			bean1.setDeptID(checkNull(String.valueOf(data[i][0])));
			bean1.setDeptName(checkNull(String.valueOf(data[i][1])));
			bean1.setDeptDesc(checkNull(String.valueOf(data[i][2])));
			bean1.setDeptAbbr(checkNull(String.valueOf(data[i][3])));
			bean1.setDeptDivCode(checkNull(String.valueOf(data[i][4])));
			bean1.setDivName(checkNull(String.valueOf(data[i][5])));
			bean1.setDeptParID(checkNull(String.valueOf(data[i][6])));
			bean1.setDeptLev(checkNull(String.valueOf(data[i][7])));
			bean.setIsActive(checkNull(String.valueOf(data[0][8])));//added 
			
			dptList.add(bean1);
		}//end of loop
		bean.setDeptList(dptList);

	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}//end of if
		else {
			return result;
		}//end of else
	}

	/* generating the report */
	public void getReport(DeptMaster dptMaster2, HttpServletRequest request,
			HttpServletResponse response, ServletContext context,String []label) {
		// TODO Auto-generated method stub

		/*CrystalReport cr = new CrystalReport();
		String path = "org\\paradyne\\rpt\\admin\\master\\department.rpt ";
		cr.createReport(request, response, context, session, path, "");*/
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nDepartment Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Department  Master.Pdf");
		String queryDes = "SELECT  DEPT_NAME,DEPT_DESC,DEPT_ABBR,DEPT_DIV_CODE,DEPT_PARENT_CODE,DEPT_LEVEL,DECODE(IS_ACTIVE,'Y','Yes','N','No')"
						 +" FROM HRMS_DEPT "
						 +" LEFT JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID=HRMS_DEPT.DEPT_DIV_CODE)"
						 +" ORDER BY  upper(DEPT_NAME)";// added
						
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][8];//added
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];
				Data[i][4] = data[i][3];
				Data[i][5] = data[i][4];
				Data[i][6] = data[i][5];
				Data[i][7] = data[i][6];	
				
				j++;
			}
			int cellwidth[] = { 15, 40, 40, 30, 25,25, 25,10 };//added
			int alignment[] = { 1, 0, 0, 0, 1, 1, 0, 0};//added
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

	/* generating the list onload */
	public void hasData(DeptMaster dptMaster, HttpServletRequest request) {
		Object[][] repData = getSqlModel().getSingleResult(getQuery(5));
		if(repData!=null  && repData.length>0){
			dptMaster.setModeLength("true");
			dptMaster.setTotalRecords(String.valueOf(repData.length));
		
	String[] pageIndex = Utility.doPaging(dptMaster.getMyPage(), repData.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			//pageIndex[4] = "1";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			dptMaster.setMyPage("1");
		ArrayList<Object> List = new ArrayList<Object>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
			
			DeptMaster bean1 = new DeptMaster();
			bean1.setDeptID(checkNull(String.valueOf(repData[i][0])));
			bean1.setDeptName(checkNull(String.valueOf(repData[i][1])));
			bean1.setDeptDesc(checkNull(String.valueOf(repData[i][2])));
			bean1.setIsActive(checkNull(String.valueOf(repData[i][8])));
			
			List.add(bean1);
		}//end of loop

		dptMaster.setDeptList(List);
		}
	}

	/*
	 * for selecting the data from list and setting those data in respective
	 * fields
	 */
	public void calforedit(DeptMaster dptMaster) {

		String query = " SELECT  DEPT_ID,DEPT_NAME FROM HRMS_DEPT  WHERE  DEPT_ID= "
				+ dptMaster.getHiddencode() + " ORDER BY DEPT_ID ";

		Object[][] data = getSqlModel().getSingleResult(query);
		dptMaster.setDeptID(String.valueOf(data[0][0]));
		dptMaster.setDeptName(String.valueOf(data[0][1]));

	}

	/*
	 * public boolean calfordelete(DeptMaster dptMaster) {
	 * 
	 * Object addObj [][]= new Object [1][1]; addObj
	 * [0][0]=dptMaster.getHiddencode(); return
	 * getSqlModel().singleExecute(getQuery(3),addObj);
	 * 
	 *  }
	 */
	
	
	
	/* for deleting the one or more records from list */
	public boolean deleteDept(DeptMaster dptMaster, String[] code) {

		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		if (code != null) {

			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
					logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->" + code[i]);
					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					flag = getSqlModel().singleExecute(getQuery(3), delete);
					if (!flag) {
						cnt++;
					}// end of if
					// result=true;
				}// end of if
			}// end of loop
		}
		if (cnt > 0) {
			result = false;
		}// end of if
		else
			result = true;
		return result;

	}

}

// TODO Auto-generated method stub

