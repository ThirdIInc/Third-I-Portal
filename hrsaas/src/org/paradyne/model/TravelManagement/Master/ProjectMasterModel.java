package org.paradyne.model.TravelManagement.Master;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.ApplicationStudio.LetterTemplate;
import org.paradyne.bean.TravelManagement.Master.ProjectMasterBean;
import org.paradyne.bean.TravelManagement.Master.TravelModeListMasterBean;
import org.paradyne.bean.employeeSurvey.EmployeeSurveyMaster;
import org.paradyne.lib.ModelBase;

public class ProjectMasterModel extends ModelBase {

	
 
	
	public boolean save(ProjectMasterBean bean) {
		
		boolean result=false;
		
		String pName=bean.getProjectName();
		String pOwnerId=bean.getEmpId();
		String pDescription=bean.getProjectDescription();
		
		String selQuery=" SELECT * FROM TMS_TRAVEL_PROJECT WHERE UPPER(PROJECT_NAME) LIKE '"
					+ bean.getProjectName().trim().toUpperCase() + "'";
		Object [][]selData=getSqlModel().getSingleResult(selQuery);
		if(selData.length==0){
		
			
			Object insObj[][]=new Object[1][3];
		
		insObj[0][0]= pName;
		System.out.println(""+pName);
		insObj[0][1]=pOwnerId;
		insObj[0][2]=pDescription;
		System.out.println(""+pDescription);

		String insQuery="INSERT INTO TMS_TRAVEL_PROJECT (PROJECT_ID, PROJECT_NAME, PROJECT_OWNER, PROJECT_DESC) VALUES((SELECT NVL(MAX(PROJECT_ID),0)+1 FROM  TMS_TRAVEL_PROJECT) ,?,?,?)";
		result=getSqlModel().singleExecute(insQuery,insObj);
		
		String Query="SELECT MAX(PROJECT_ID) FROM  TMS_TRAVEL_PROJECT";
		Object [][]data=getSqlModel().getSingleResult(Query);
		bean.setProjectId(String.valueOf(data[0][0]));
		}
		return result;
	

	}

	public void getList(ProjectMasterBean bean, HttpServletRequest request) {
		Object[][] selData = null;
		
		try {
			String selQuery="SELECT  PROJECT_NAME, EMP_FNAME|| ' ' ||EMP_MNAME|| ' ' ||EMP_LNAME, PROJECT_DESC,PROJECT_ID FROM TMS_TRAVEL_PROJECT LEFT JOIN HRMS_EMP_OFFC ON(TMS_TRAVEL_PROJECT.PROJECT_OWNER=HRMS_EMP_OFFC.EMP_ID) ORDER BY PROJECT_ID ";
		 selData=getSqlModel().getSingleResult(selQuery);

			//System.out.println("=========== After Query ===========");
			selData = getSqlModel().getSingleResult(selQuery);
		} catch (Exception e) {
			
		}
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(bean
				.getMyPage(), selData.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		try {
			System.out.println("pageIndex  of 2============    "
					+ Integer.parseInt(String.valueOf(pageIndex[2])));
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");
		if (selData == null) {

		}
		
		ArrayList<Object> list = new ArrayList<Object>();
		if (selData != null && selData.length > 0) {
			
			for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
			.parseInt(String.valueOf(pageIndex[1])); i++){
				ProjectMasterBean subBean = new ProjectMasterBean();
				subBean.setIttProjectName(String.valueOf(selData[i][0]));
				subBean.setIttProjectOwner(checkNull(String.valueOf(selData[i][1])));
				subBean.setIttProjectDescription(checkNull(String.valueOf(selData[i][2])));
				subBean.setIttSrN0(String.valueOf(i+1));
				subBean.setIttProjectCode(String.valueOf(selData[i][3]));
				
				list.add(subBean);
			}
		}
		bean.setProjectMasterItt(list);
	}

	public boolean deleteCheck(ProjectMasterBean bean, String[] code, String[] empId2, HttpServletRequest request) {
		
	
	boolean flag =false;
		
	//System.out.println(".......................................");
	for (int j = 0; j < code.length; j++) {	
		if(code[j].equals("Y")){
	
		String delQuery="DELETE FROM TMS_TRAVEL_PROJECT WHERE PROJECT_ID="+empId2[j]+"";
		flag = getSqlModel().singleExecute(delQuery);
		
		}
	
		
	
	}
	getList(bean,request);
	
	return flag;

}

	public boolean delete(ProjectMasterBean bean, HttpServletRequest request) {
		
		boolean result=false;
		
		String projectId=bean.getProjectId();
		System.out.println("............"+projectId);
		String delQuery="DELETE FROM TMS_TRAVEL_PROJECT WHERE PROJECT_ID="+projectId;
		result= getSqlModel().singleExecute(delQuery);
		getList(bean,request);
		return result;
	}

	
		
		

	public void dblClickItt(ProjectMasterBean bean) {
	 getDetails(bean);
		
	}

	public void getDetails(ProjectMasterBean bean) {
     try {
		String projectId = bean.getProjectId();
		String selQuery = "SELECT PROJECT_ID,PROJECT_NAME,NVL(PROJECT_OWNER,0), EMP_FNAME|| ' ' ||EMP_MNAME|| ' ' ||EMP_LNAME, NVL(PROJECT_DESC,'') FROM TMS_TRAVEL_PROJECT LEFT JOIN HRMS_EMP_OFFC ON(TMS_TRAVEL_PROJECT.PROJECT_OWNER=HRMS_EMP_OFFC.EMP_ID) WHERE PROJECT_ID= "
				+ projectId;
		Object[][] data = getSqlModel().getSingleResult(selQuery);
		bean.setProjectId(String.valueOf(data[0][0]));
		bean.setProjectName(String.valueOf(data[0][1]));
		bean.setEmpId(String.valueOf(data[0][2]));
		bean.setProjectOwner(checkNull(String.valueOf(data[0][3])));
		bean.setProjectDescription(checkNull(String.valueOf(data[0][4])));
	} catch (Exception e) {
		// TODO: handle exception
	}
	}

	public boolean update(ProjectMasterBean bean) {
		boolean result=false;
		try {
			String projectId = bean.getProjectId();
			String pName = bean.getProjectName();
			String pOwnerId = bean.getEmpId();
			String pDescription = bean.getProjectDescription();
			System.out.println(".............." + pOwnerId);
			System.out.println(".............." + pName);
			System.out.println(".............." + pDescription);
			String upQuery = "UPDATE TMS_TRAVEL_PROJECT SET  PROJECT_NAME= '"
					+ pName + "'";
			if (!pOwnerId.equals("0")) {
				upQuery += ", PROJECT_OWNER= " + pOwnerId;
			}
			if (!pDescription.equals("")) {
				upQuery += ", PROJECT_DESC='" + pDescription + "'";
			}
			upQuery += " WHERE PROJECT_ID= " + projectId;
			result = getSqlModel().singleExecute(upQuery);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}
	
}
	
	

