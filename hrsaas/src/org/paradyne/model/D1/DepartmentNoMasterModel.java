package org.paradyne.model.D1;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.DepartmentNoMasterBean;
import org.paradyne.bean.admin.master.DeptMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class DepartmentNoMasterModel extends ModelBase {
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}//end of if
		else {
			return result;
		}//end of else
	}
	/* generating the list onload */
	
	public void hasData(DepartmentNoMasterBean dptMaster, HttpServletRequest request) {
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
		ArrayList<DepartmentNoMasterBean> List = new ArrayList<DepartmentNoMasterBean>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
			
			DepartmentNoMasterBean bean1 = new DepartmentNoMasterBean();
			bean1.setDeptID(checkNull(String.valueOf(repData[i][0])));
			bean1.setDeptNumber(checkNull(String.valueOf(repData[i][1])));
			bean1.setDeptName(checkNull(String.valueOf(repData[i][2])));
			bean1.setDeptDesc(checkNull(String.valueOf(repData[i][5])));
			bean1.setIsActive(checkNull(String.valueOf(repData[i][6])));
			
			List.add(bean1);
		}//end of loop

		
		if(List.size()>0 && List!=null){
			dptMaster.setDeptList(List);
		}else{
			dptMaster.setDeptList(null);
		}
		}
	}
	
	/* for inserting record */
	public boolean addDept(DepartmentNoMasterBean bean) {
		boolean result=false;
			
		Object addObj[][] = new Object[1][7];
			try{
			Object [][]data=null;
			String selectdata="SELECT NVL(MAX(DEPT_ID),0)+1 FROM hrms_d1_department ";
			data=getSqlModel().getSingleResult(selectdata);
			
			System.out.println("here last code---"+String.valueOf(data[0][0]));
			System.out.println("here last dfzf---"+bean.getDeptID());
			addObj[0][0]=String.valueOf(data[0][0]);
			
			if (!checkDuplicate(bean)) {
			addObj[0][1] = bean.getDeptNumber().trim();
			
			//addObj[0][1] = bean.getDeptName().trim();
			if(bean.getDeptName()!=null && !bean.getDeptName().equals("")){
				addObj[0][2] = bean.getDeptName().trim();
			}else{
				addObj[0][2] ="";
			}
			addObj[0][3] = bean.getDeptAbbr().trim();
			addObj[0][4] = bean.getDeptDivCode().trim();
			addObj[0][5] = bean.getDeptDesc().trim();
			addObj[0][6] = bean.getIsActive().trim();// added
			bean.setDeptID(String.valueOf(data[0][0]));
			
			String insertData="INSERT INTO hrms_d1_department(DEPT_ID,DEPT_NUMBER, DEPT_NAME, DEPT_ABBRIVATION, DEPT_DIV_CODE, DEPT_DESCRIPTION, DEPT_IS_ACTIVE) "
				+" VALUES(?,?,?,?,?,?,?) ";
			 getSqlModel().singleExecute(insertData, addObj);
			 result =true;
				
			}else{
				 result =false;
			}
			
			
			}catch(Exception e){
				e.printStackTrace();
			}
			return result;
	}

		
	

	public boolean checkDuplicate(DepartmentNoMasterBean bean) {
		boolean result = false;
		String query = "SELECT * FROM hrms_d1_department WHERE UPPER(DEPT_NUMBER) LIKE '"
				+ bean.getDeptNumber().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}
	

	public void getDeptRecord(DepartmentNoMasterBean bean) {
		Object addObj[] = new Object[1];
		addObj[0] = bean.getDeptID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);

		for (int i = 0; i < data.length; i++) {
			bean.setDeptID(checkNull(String.valueOf(data[0][0])));
			bean.setDeptNumber(checkNull(String.valueOf(data[0][1])));
			bean.setDeptName(checkNull(String.valueOf(data[0][2])));
			bean.setDeptAbbr(checkNull(String.valueOf(data[0][3])));
			bean.setDeptDivCode(checkNull(String.valueOf(data[0][4])));
			bean.setDeptDesc(checkNull(String.valueOf(data[0][5])));
			bean.setIsActive(checkNull(String.valueOf(data[0][6])));//added
			bean.setDivName(checkNull(String.valueOf(data[0][7])));//added)
		}// end of loop
	}
	
	/*
	 * for selecting the data from list and setting those data in respective
	 * fields
	 */
	public void calforedit(DepartmentNoMasterBean dptMaster) {

		String query = " SELECT  DEPT_ID,DEPT_NAME FROM HRMS_D1_DEPARTMENT  WHERE  DEPT_ID= "
				+ dptMaster.getHiddencode() + " ORDER BY DEPT_ID ";

		Object[][] data = getSqlModel().getSingleResult(query);
		dptMaster.setDeptID(String.valueOf(data[0][0]));
		dptMaster.setDeptName(String.valueOf(data[0][1]));

	}
	
	/* for modifing the record */
	public boolean modDept(DepartmentNoMasterBean bean) {
		if (!checkDuplicateMod(bean)) {
			Object addObj[][] = new Object[1][7];
			//logger.info("In Modify method");
			addObj[0][0] = bean.getDeptNumber().trim();
			if(bean.getDeptName()!=null && !bean.getDeptName().equals("")){
				addObj[0][1] = bean.getDeptName().trim();
			}else{
				addObj[0][1] ="";
			}
			//addObj[0][1] = bean.getDeptName().trim();
			addObj[0][2] = bean.getDeptAbbr().trim();
			addObj[0][3] = bean.getDeptDivCode().trim();
			addObj[0][4] = bean.getDeptDesc().trim();
			addObj[0][5] = bean.getIsActive().trim();
			addObj[0][6] = bean.getDeptID().trim();
			return getSqlModel().singleExecute(getQuery(2), addObj);
		}// end of if
		else
			return false;
	}
	/* for checking duplicate entry of record during modification */

	public boolean checkDuplicateMod(DepartmentNoMasterBean bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_D1_DEPARTMENT WHERE UPPER(DEPT_NUMBER) LIKE '"
				+ bean.getDeptNumber().trim().toUpperCase()
				+ "' AND DEPT_ID not in(" + bean.getDeptID() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	
	/* for deleting the record after selecting */
	public boolean deleteDept(DepartmentNoMasterBean bean) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getDeptID();
		return getSqlModel().singleExecute(getQuery(3), addObj);
	}
	/* for deleting the one or more records from list */
	public boolean deleteDept(DepartmentNoMasterBean bean, String[] code) {

		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		if (code != null) {

			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {
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
		else{
			result = true;
		}
		return result;

	}

}



