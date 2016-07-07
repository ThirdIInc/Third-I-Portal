/**
 * 
 */
package org.paradyne.model.helpdesk;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.helpdesk.HelpDeskReqType;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa0623
 *
 */
public class HelpDeskReqTypeModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(HelpDeskReqTypeModel.class);
	
	public void getRecords(HelpDeskReqType reqType, HttpServletRequest request) {
		try {
			int length=0;	
			String query = " SELECT REQUEST_TYPE_ID, NVL(REQUEST_TYPE_NAME,' '), NVL(HELPDESK_DEPT.DEPT_NAME,' '), REQUEST_TYPE_DEPT, "
				+ " DECODE (HELPDESK_REQUEST_TYPE.IS_ACTIVE,'Y','Yes','N','No','No')"
				+ " FROM HELPDESK_REQUEST_TYPE "
				+ " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_TYPE.REQUEST_TYPE_DEPT) "
				+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HELPDESK_DEPT.DEPT_CODE) "
				+ " ORDER BY HRMS_DEPT.DEPT_ID";

			Object[][] data = getSqlModel().getSingleResult(query);
			if(data!=null && data.length>0){
				reqType.setModeLength("true");
			reqType.setTotalRecords(String.valueOf(data.length));  //   to  display the total number of record in  the list 
			
			String[] pageIndex = Utility.doPaging(reqType.getMyPage(),data.length, 20);	
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
				reqType.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 	
			HelpDeskReqType reqType1 = new HelpDeskReqType();

				reqType1.setReqTypeCodeItr(String.valueOf(data[i][0]));   
				reqType1.setReqTypeItr(String.valueOf(data[i][1]).trim());   
				reqType1.setReqTypeDeptItr(String.valueOf(data[i][2]).trim());       
				reqType1.setReqTypeDeptIdItr(String.valueOf(data[i][3]));
				reqType1.setIsActiveItr(String.valueOf(data[i][4]));
				List.add(reqType1);
			}
			
			reqType.setReqTypeList(List);
			length=data.length;
			reqType.setTotalRecords(String.valueOf(length));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void getRequestTypes(HelpDeskReqType reqType) {
		try {

			
			String query = " SELECT REQUEST_TYPE_ID, NVL(REQUEST_TYPE_NAME,' '), NVL(HELPDESK_DEPT.DEPT_NAME,' '), REQUEST_TYPE_DEPT, HELPDESK_REQUEST_TYPE.IS_ACTIVE "
				+ " FROM HELPDESK_REQUEST_TYPE "
				+ " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_TYPE.REQUEST_TYPE_DEPT) "
				+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HELPDESK_DEPT.DEPT_CODE) "
				+ " WHERE REQUEST_TYPE_ID="+reqType.getReqTypeCode();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {
				reqType.setReqTypeCode(String.valueOf(data[0][0]));   
				reqType.setReqType(String.valueOf(data[0][1]).trim());   
				reqType.setReqTypeDept(String.valueOf(data[0][2]).trim());       
				reqType.setReqTypeDeptId(String.valueOf(data[0][3]));
				/*if (String.valueOf(data[0][4]).equals("Y")) {
					reqType.setIsManagerApproved("true");
				}*/
				if (String.valueOf(data[0][4]).equals("Y")) {
					reqType.setIsActive("true");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void getReqTypeOnDblClick(HelpDeskReqType reqType) {
		try {

			String query = " SELECT REQUEST_TYPE_ID, NVL(REQUEST_TYPE_NAME,' '), NVL(HELPDESK_DEPT.DEPT_NAME,' '), REQUEST_TYPE_DEPT, HELPDESK_REQUEST_TYPE.IS_ACTIVE"
				+ " FROM HELPDESK_REQUEST_TYPE "
				+ " LEFT JOIN HELPDESK_DEPT ON (HELPDESK_DEPT.DEPT_CODE = HELPDESK_REQUEST_TYPE.REQUEST_TYPE_DEPT) "
				+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HELPDESK_DEPT.DEPT_CODE) "
				+ " WHERE REQUEST_TYPE_ID="+reqType.getHiddencode();

			Object[][] data = getSqlModel().getSingleResult(query);

			if (data != null && data.length > 0) {

				reqType.setReqTypeCode(String.valueOf(data[0][0]));   
				reqType.setReqType(String.valueOf(data[0][1]).trim());   
				reqType.setReqTypeDept(String.valueOf(data[0][2]).trim());       
				reqType.setReqTypeDeptId(String.valueOf(data[0][3]));
				/*if (String.valueOf(data[0][4]).equals("Y")) {
					reqType.setIsManagerApproved("true");
				}*/
				if (String.valueOf(data[0][4]).equals("Y")) {
					reqType.setIsActive("true");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public boolean delChkdRec(HelpDeskReqType reqType, String[] code) {
		int count = 0;
		boolean result = false;
		for (int i = 0; i < code.length; i++) {
			if (!code[i].equals("")) {

				Object[][] delete = new Object[1][1];
				delete[0][0] = code[i];
				String query= " DELETE FROM HELPDESK_REQUEST_TYPE WHERE REQUEST_TYPE_ID=?";
				result = getSqlModel().singleExecute(query, delete);
				if (!result) {
					count++;
				}
			}
		}

		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

	public boolean deleteReqType(HelpDeskReqType reqType) {
		Object del[][] = new Object[1][1];
		// to delete the single record after clicking on saving or searching button
		del[0][0] = reqType.getReqTypeCode();
		String query= " DELETE FROM HELPDESK_REQUEST_TYPE WHERE REQUEST_TYPE_ID=?";
		return getSqlModel().singleExecute(query, del);
	}

	public String addRequestTypes(HelpDeskReqType reqType) {
		Object[][] add = new Object[1][3];
		String flag = "";
		boolean result = false;
		try {
			add[0][0] = reqType.getReqType().trim();  // designation name 
			add[0][1] = reqType.getReqTypeDeptId();  // designation abbreviation
			/*if( reqType.getIsManagerApproved().equals("true")){
				add[0][2] = "Y"; //is manager approved
			}else{
				add[0][2] = "N";
			}*/
			if( reqType.getIsActive().equals("true")){
				add[0][2] = "Y"; //is active
			}else{
				add[0][2] = "N";
			}

			if (!checkDuplicate(reqType)) {
				String query = " INSERT INTO HELPDESK_REQUEST_TYPE (REQUEST_TYPE_ID, REQUEST_TYPE_NAME, REQUEST_TYPE_DEPT, IS_ACTIVE) " 
					+"VALUES((SELECT NVL(MAX(REQUEST_TYPE_ID),0)+1 FROM HELPDESK_REQUEST_TYPE ),?,?,?)";
				result = getSqlModel().singleExecute(query, add);
				if (result) {

					String maxQuery = " SELECT MAX(REQUEST_TYPE_ID) FROM HELPDESK_REQUEST_TYPE";
					// to get the  designation/rank id  from table
					Object[][] data = getSqlModel().getSingleResult(maxQuery);
					reqType.setReqTypeCode(String.valueOf(data[0][0]));
				
					flag = "saved";
				} else {
					flag = "error";
				}
			} else {
				flag = "duplicate";
			}
		} catch (Exception e) {
			logger.error("Exception was raised-->");
		}
		return flag;
	}

	private boolean checkDuplicate(HelpDeskReqType reqType) {
		boolean result = false;
		String query = "SELECT * FROM HELPDESK_REQUEST_TYPE WHERE UPPER(REQUEST_TYPE_NAME) LIKE '"
				+ reqType.getReqType().trim().toUpperCase() + "'";

		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}

	public String modRequestTypes(HelpDeskReqType reqType) {
		// to get the data for  update the record
		Object[][] data = new Object[1][4];
		String editFlag = "";
		boolean result = false;
		try {
			data[0][0] = reqType.getReqType().trim();   
			data[0][1] = reqType.getReqTypeDeptId(); 
			/*if( reqType.getIsManagerApproved().equals("true")){
				data[0][2] = "Y"; //is manager approved
			}else{
				data[0][2] = "N";
			}*/
			if( reqType.getIsActive().equals("true")){
				data[0][2] = "Y"; //is active
			}else{
				data[0][2] = "N";
			}
			data[0][3] = reqType.getReqTypeCode();    
			if (!checkDuplicateMod(reqType)) {
				// to get the data  for modifying  the record
				String query = "UPDATE HELPDESK_REQUEST_TYPE SET REQUEST_TYPE_NAME=?,REQUEST_TYPE_DEPT=?, IS_ACTIVE=? WHERE REQUEST_TYPE_ID=?";
				result = getSqlModel().singleExecute(query, data);
				if (result) {
					/*reqType.getReqTypeCode(checkNull(String.valueOf(
							data[0][4]).trim())); */                     
					editFlag = "modified";
				} else {
					editFlag = "error";
				}
			} else {
				editFlag = "duplicate";
			}
		} catch (Exception e) {
			logger.error("Exception was rised--->");
		}
		return editFlag;
	}

	private boolean checkDuplicateMod(HelpDeskReqType reqType) {
		boolean result = false;
		Object[][] data = null;
		Object[] value = new Object[1];
		try {

			value[0] = reqType.getReqType().trim().toUpperCase();
		} catch (Exception e) {
			
		}
		try {
			String query = "SELECT * FROM HELPDESK_REQUEST_TYPE WHERE UPPER(REQUEST_TYPE_NAME) LIKE '"
					+ reqType.getReqType().trim().toUpperCase()
					+ "' AND REQUEST_TYPE_ID not in(" + reqType.getReqTypeCode() + ")";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			
		}
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}

}
