package org.paradyne.model.LMS;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.LMS.LmsDesignation;
import org.paradyne.lib.ModelBase;

public class LmsDesignationModel extends ModelBase {

	public void getInitialList(LmsDesignation lmsdesigbean,
			HttpServletRequest request, String isSaved) {
		try {

			String designationListQuery = "SELECT EMPLOYEE_ROLE_ID, EMPLOYEE_ROLE, ROLE_ABBR FROM LMS_EMPLOYEE_ROLE ORDER BY EMPLOYEE_ROLE_ID";
			Object[][] designationListObj = getSqlModel().getSingleResult(
					designationListQuery);
			if (designationListObj != null && designationListObj.length > 0) {

				ArrayList innerList = new ArrayList();
				for (int i = 0; i < designationListObj.length; i++) {
					LmsDesignation innerbean = new LmsDesignation();
					innerbean.setDesignationListTypeID(checkNull(String
							.valueOf(designationListObj[i][0])));
					innerbean.setDesignationListTypeName(checkNull(String
							.valueOf(designationListObj[i][1])));
					innerbean.setDesignationAbbreviation(checkNull(String.valueOf(designationListObj[i][2])));
					innerList.add(innerbean);
				}
				lmsdesigbean.setDesignationTypeItterator(innerList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void processList(LmsDesignation lmsdesigbean,
			HttpServletRequest request, String isSaved,
			int designationListTypeID, String designationAbbreviation) {
		try {			
			//(Start) Only for setting header name
			String headerNamequery = "SELECT EMPLOYEE_ROLE FROM LMS_EMPLOYEE_ROLE WHERE EMPLOYEE_ROLE_ID= "+designationListTypeID;
			Object[][] headerNameObj = getSqlModel().getSingleResult(headerNamequery);
			lmsdesigbean.setDesignationListTypeName(checkNull(String.valueOf(headerNameObj[0][0])));
			//(End) Only for setting header name
			
			String query = " SELECT RANK_ID,RANK_NAME FROM HRMS_RANK "
					+ " INNER JOIN LMS_EMPLOYEE_ROLE ON(LMS_EMPLOYEE_ROLE.ROLE_ABBR=HRMS_RANK.LMS_DESIGNATION AND LMS_EMPLOYEE_ROLE.ROLE_ABBR='"+designationAbbreviation+"') ";

			Object[][] queryObj = getSqlModel().getSingleResult(query);

			if (queryObj != null && queryObj.length > 0) {
				lmsdesigbean.setMapListLength("true");
				ArrayList list = new ArrayList();
				for (int i = 0; i < queryObj.length; i++) {
					LmsDesignation bean = new LmsDesignation();
					bean.setDesignationID(checkNull(String
							.valueOf(queryObj[i][0])));
					bean.setDesignation(checkNull(String
							.valueOf(queryObj[i][1])));				
					list.add(bean);
				}
				lmsdesigbean.setMappedDesignationlist(list);
			}
			
			
			//Query for pending designation list
			String pendingQuery = " SELECT RANK_ID,RANK_NAME FROM HRMS_RANK "
							+" WHERE LMS_DESIGNATION IS NULL ";
		Object[][] pendingQueryObj = getSqlModel().getSingleResult(pendingQuery);

		if (pendingQueryObj != null && pendingQueryObj.length > 0) {
			lmsdesigbean.setListLength("true");
			ArrayList list = new ArrayList();
			for (int i = 0; i < pendingQueryObj.length; i++) {
				LmsDesignation bean = new LmsDesignation();
				bean.setPendingDesignationID(checkNull(String
						.valueOf(pendingQueryObj[i][0])));
				bean.setPendingDesignation(checkNull(String
						.valueOf(pendingQueryObj[i][1])));				
				list.add(bean);
			}
			lmsdesigbean.setPendingDesignationlist(list);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}


	public boolean saveRecords(LmsDesignation lmsdesigbean,
			HttpServletRequest request) {
		boolean result = false;
		try {
			String confChk[] = request.getParameterValues("confChk");
			String hiddenDesignationTypeCode[] = request.getParameterValues("hiddenDesignationTypeCode");			
			Object addObj[][] = new Object[hiddenDesignationTypeCode.length][2];
			for (int i = 0; i < hiddenDesignationTypeCode.length; i++) {
				addObj[i][0] = lmsdesigbean.getHiddenDesignationAbbreviation();
				addObj[i][1] = checkNull(String.valueOf(hiddenDesignationTypeCode[i]));				
			}
			String updateQuery = "UPDATE HRMS_RANK SET LMS_DESIGNATION=? WHERE RANK_ID=?";
			result = getSqlModel().singleExecute(updateQuery, addObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteRecords(LmsDesignation lmsdesigbean,
			HttpServletRequest request) {
		boolean delResult = false;
		try {
			String hiddenMappedDesignationTypeCode[] = request.getParameterValues("hiddenMappedDesignationTypeCode");
			Object delObj[][] = new Object[hiddenMappedDesignationTypeCode.length][2];
			for (int i = 0; i < hiddenMappedDesignationTypeCode.length; i++) {
				delObj[i][0] = "";
				delObj[i][1] = checkNull(String.valueOf(hiddenMappedDesignationTypeCode[i]));				
			}
			String delQuery = "UPDATE HRMS_RANK SET LMS_DESIGNATION=? WHERE RANK_ID=?";
			delResult = getSqlModel().singleExecute(delQuery,delObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return delResult;
	}

}
