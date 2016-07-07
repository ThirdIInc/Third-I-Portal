/**
 * 
 */
package org.paradyne.model.openCloseOffice;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.openCloseOffice.OpenCloseOffice;
import org.paradyne.lib.ModelBase;
import org.struts.action.openCloseOffice.OpenCloseOfficeAction;

/**
 * @author Reeba_Joseph
 *
 */
public class OpenCloseOfficeModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(OpenCloseOfficeModel.class);

	public int checkAccess(OpenCloseOffice bean) {
		int value = 0;
		Object[][]accessData = null;
		String chkAccessQuery = "SELECT * FROM HRMS_OPEN_CLOSE_CONFIG WHERE EMP_ID = "+bean.getUserEmpId()+"";
		accessData = getSqlModel().getSingleResult(chkAccessQuery);
		
		if(accessData !=null && accessData.length > 0){
			value = 1;
		} //end of if
		return value;
	} //end of checkAccess method

	public void open(OpenCloseOffice bean,HttpServletRequest request) {
		try {
			
			Object[][]empBranch = null;
			
			String branchQuery = "SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = "+bean.getUserEmpId()+"";
			empBranch = getSqlModel().getSingleResult(branchQuery);
			
			Object[][]chkIfOpen = null;
			String chkIfOpenQuery = "SELECT TO_CHAR(OPEN_DATE_TIME,'DD-MM-YYYY HH24:MI:SS') FROM HRMS_OPEN_CLOSE_OFFICE WHERE " 
				+" TO_CHAR(OPEN_DATE_TIME,'DD-MM-YYYY') = TO_CHAR(SYSDATE,'DD-MM-YYYY') AND BRANCH_ID = "+empBranch[0][0]+"";
			chkIfOpen = getSqlModel().getSingleResult(chkIfOpenQuery);
			
			if(chkIfOpen !=null && chkIfOpen.length > 0){
				bean.setMessage("Office already opened today at "+chkIfOpen[0][0]);
			} //end of if
			else{
				boolean result = false;
				Object[][]openDate = null;
				String openDateQuery = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL";
				openDate = getSqlModel().getSingleResult(openDateQuery);
				
				Object[][] insertData = new Object[1][8];
				
				String str = request.getRemoteAddr();
				String str1 = str.replace(".", "#");
				String[] strSpilt = str1.split("#");
				
				insertData[0][0] = empBranch[0][0];
				insertData[0][1] = bean.getUserEmpId();
				insertData[0][2] = openDate[0][0];
				insertData[0][3] = "";
				insertData[0][4] = strSpilt[0];
				insertData[0][5] = strSpilt[1];
				insertData[0][6] = strSpilt[2];
				insertData[0][7] = strSpilt[3];
				
				String openOfficeQuery = "INSERT INTO HRMS_OPEN_CLOSE_OFFICE (BRANCH_ID, OPEN_EMP_ID, OPEN_DATE_TIME, CLOSE_DATE_TIME, IP_ADDRESS1, IP_ADDRESS2, " 
										+" IP_ADDRESS3, IP_ADDRESS4) VALUES(?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI:SS'),?,?,?,?,?)";
				result = getSqlModel().singleExecute(openOfficeQuery,insertData);
				
				if(result){
					Object[][]openTime = null;
					String openTimeQuery = "SELECT TO_CHAR(OPEN_DATE_TIME,'DD-MM-YYYY HH24:MI:SS') FROM HRMS_OPEN_CLOSE_OFFICE WHERE "
							+" TO_CHAR(OPEN_DATE_TIME,'DD-MM-YYYY') = TO_CHAR(SYSDATE,'DD-MM-YYYY') AND BRANCH_ID = "+empBranch[0][0]+"";
					openTime = getSqlModel().getSingleResult(openTimeQuery);
					
					if(openTime !=null && openTime.length > 0){
						bean.setMessage("Office opened successfully at "+openTime[0][0]);
					} //end of if
					
				} //end of if
				
			} //end of else
		} catch (Exception e) {
			logger.error("exception in open",e);
		} //end of catch
	} //end of open method
	
	
	public void close(OpenCloseOffice bean,HttpServletRequest request) {
		try {
			Object[][]empBranch = null;
			
			String branchQuery = "SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID = "+bean.getUserEmpId()+"";
			empBranch = getSqlModel().getSingleResult(branchQuery);
			
			/*Object[][]chkIfClose = null;
			String chkIfCloseQuery = "SELECT TO_CHAR(CLOSE_DATE_TIME,'DD-MM-YYYY HH24:MI:SS') FROM HRMS_OPEN_CLOSE_OFFICE WHERE " 
				+" TO_CHAR(CLOSE_DATE_TIME,'DD-MM-YYYY') = TO_CHAR(SYSDATE,'DD-MM-YYYY') AND BRANCH_ID = "+empBranch[0][0]+"";
			chkIfClose = getSqlModel().getSingleResult(chkIfCloseQuery);
			
			if(chkIfClose !=null && chkIfClose.length > 0){
				bean.setMessage("Office already closed today at "+chkIfClose[0][0]);
			} //end of if
			else{*/
				
				Object[][]chkIfOpen = null;
				String chkIfOpenQuery = "SELECT TO_CHAR(OPEN_DATE_TIME,'DD-MM-YYYY HH24:MI:SS') FROM HRMS_OPEN_CLOSE_OFFICE WHERE " 
					+" TO_CHAR(OPEN_DATE_TIME,'DD-MM-YYYY') = TO_CHAR(SYSDATE,'DD-MM-YYYY') AND BRANCH_ID = "+empBranch[0][0]+"";
				chkIfOpen = getSqlModel().getSingleResult(chkIfOpenQuery);
				
				if(chkIfOpen !=null && chkIfOpen.length >0){
					boolean result = false;
					Object[][]closeDate = null;
					String closeDateQuery = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI:SS') FROM DUAL";
					closeDate = getSqlModel().getSingleResult(closeDateQuery);
					
					Object[][] updateData = new Object[1][7];
					
					String str = request.getRemoteAddr();
					String str1 = str.replace(".", "#");
					String[] strSpilt = str1.split("#");
					
					updateData[0][0] = closeDate[0][0];
					updateData[0][1] = strSpilt[0];
					updateData[0][2] = strSpilt[1];
					updateData[0][3] = strSpilt[2];
					updateData[0][4] = strSpilt[3];
					updateData[0][5] = bean.getUserEmpId();
					updateData[0][6] = empBranch[0][0];
					
					
					String updateOfficeQuery = "UPDATE HRMS_OPEN_CLOSE_OFFICE SET CLOSE_DATE_TIME = TO_DATE(?,'DD-MM-YYYY HH24:MI:SS') ,CLOSE_IP_ADDRESS1 =?, " 
						+" CLOSE_IP_ADDRESS2=?, CLOSE_IP_ADDRESS3=?, CLOSE_IP_ADDRESS4=?, CLOSE_EMP_ID = ? "
						+" WHERE TO_CHAR(OPEN_DATE_TIME,'DD-MM-YYYY') = TO_CHAR(SYSDATE,'DD-MM-YYYY') AND BRANCH_ID =?";
					
					result = getSqlModel().singleExecute(updateOfficeQuery,updateData);
					
					if(result){
						Object[][]closeTime = null;
						String openTimeQuery = "SELECT TO_CHAR(CLOSE_DATE_TIME,'DD-MM-YYYY HH24:MI:SS') FROM HRMS_OPEN_CLOSE_OFFICE WHERE "
								+" TO_CHAR(CLOSE_DATE_TIME,'DD-MM-YYYY') = TO_CHAR(SYSDATE,'DD-MM-YYYY') AND BRANCH_ID = "+empBranch[0][0]+"";
						closeTime = getSqlModel().getSingleResult(openTimeQuery);
						
						if(closeTime !=null && closeTime.length > 0){
							bean.setMessage("Office closed successfully at "+closeTime[0][0]);
						} //end of if
						
					} //end of if
					
				} //end of if Office opened today
				else{
					bean.setMessage("Office not opened today");
				} //end of else if office not opened
			
		} catch (Exception e) {
			logger.error("exception in open",e);
		} //end of catch
	} //end of open method
	

}
