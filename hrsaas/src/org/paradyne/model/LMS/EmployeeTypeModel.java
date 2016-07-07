package org.paradyne.model.LMS;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.LMS.EmployeeTypeBean;
import org.paradyne.bean.TravelManagement.Master.CityGrade;

import org.paradyne.lib.ModelBase;

public class EmployeeTypeModel extends ModelBase {

	/** Generating the list Onload */
	public void emptypeData(EmployeeTypeBean emptypeBean,
			HttpServletRequest request,String isSaved) {
		String query = "SELECT TYPE_ID,TYPE_NAME FROM HRMS_EMP_TYPE ";							
		Object[][] queryObj = getSqlModel().getSingleResult(query);	
		
		ArrayList<EmployeeTypeBean> List = new ArrayList<EmployeeTypeBean>();
		if (queryObj.length > 0) {
			for (int i = 0; i < queryObj.length; i++) {

				EmployeeTypeBean bean = new EmployeeTypeBean();
				bean.setEmpCode(checkNull(String.valueOf(queryObj[i][0])));
				bean.setEmptype(checkNull(String.valueOf(queryObj[i][1])));
					
				setEmploymentType(bean,isSaved);
				List.add(bean);
			}// end of loop
			emptypeBean.setEmploymentList(List);
		}

	}

	/**Checknull Function**/
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/**For inserting record into DB */
	public boolean insertData(EmployeeTypeBean bean, HttpServletRequest request) {

		boolean result = false;
		try {
			String[] typeId = request.getParameterValues("empCode");
			String[] typeofemp = request.getParameterValues("typeofEmployment");
			Object addObj[][] = new Object[typeofemp.length][2];
			for (int i = 0; i < typeofemp.length; i++) {
				
				addObj[i][0] = String.valueOf(typeofemp[i]);
				addObj[i][1] = String.valueOf(typeId[i]);
				
			}
			String updateQuery = "UPDATE HRMS_EMP_TYPE SET LMS_EMP_TYPE=? WHERE TYPE_ID=?";
			result = getSqlModel().singleExecute(updateQuery, addObj);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
return result;
	} // end 

	
	/**Method call for displaying dropdown list elements in onload jsp**/	
	
	public void setEmploymentType(EmployeeTypeBean bean,String isSaved) {
		LinkedHashMap<String, String>  tmap = new  LinkedHashMap<String, String>();
		String emptypeQuery = "SELECT LMS_EMP_TYPE,TYPE_OF_EMPLOYMENT FROM LMS_TYPE_OF_EMPLOYMENT ORDER BY TYPE_OF_EMPLOYMENT_ID";
		Object[][] emptypeObj = getSqlModel().getSingleResult(emptypeQuery);
		if (emptypeObj != null && emptypeObj.length > 0) {
			for (int i = 0; i < emptypeObj.length; i++) {
				tmap.put(String.valueOf(emptypeObj[i][0]), String
						.valueOf(emptypeObj[i][1]));
			}
		}
		bean.setMap(tmap);

		if(isSaved.equals("Y")){
			String empQuery = "SELECT LMS_EMP_TYPE FROM HRMS_EMP_TYPE WHERE TYPE_ID ="+bean.getEmpCode();
			Object[][] desigObj = getSqlModel().getSingleResult(empQuery);
			if (desigObj != null && desigObj.length > 0) {
				bean.setTypeofEmployment(String.valueOf(desigObj[0][0]));
			}
		}
		
		
	}

}
