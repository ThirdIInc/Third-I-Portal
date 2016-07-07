/**
 * 
 */
package org.paradyne.model.openCloseOffice;

import java.util.ArrayList;

import org.paradyne.bean.openCloseOffice.OpenCloseOfficeConfig;
import org.paradyne.lib.ModelBase;
import org.struts.action.common.LoginAction;

/**
 * @author Reeba_Joseph
 *
 */
public class OpenCloseOfficeConfigModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(LoginAction.class);
	
	public int add(OpenCloseOfficeConfig bean) {
		int value = 0;
		boolean insert = false;
		try {
			
			String insertQuery = "INSERT INTO HRMS_OPEN_CLOSE_CONFIG (BRANCH_ID, EMP_ID) " +
					"VALUES ("+bean.getBranchId()+","+bean.getEmpCode()+")";
			insert = getSqlModel().singleExecute(insertQuery);
			
			if(insert){
				value = 1;
				loadList(bean);
			} //ende of if
		} catch (Exception e) {
			logger.error("exception in add",e);
		} //end of catch
		return value;
	}

	public void loadList(OpenCloseOfficeConfig bean) {
		Object[][]loadData = null;
		try {
			String loadQuery = "SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_EMP_OFFC.EMP_ID FROM HRMS_OPEN_CLOSE_CONFIG "
							+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_OPEN_CLOSE_CONFIG.EMP_ID) "
							+" WHERE BRANCH_ID = "+bean.getBranchId()+"";
			loadData = getSqlModel().getSingleResult(loadQuery);
			
			if(loadData !=null && loadData.length > 0){
				ArrayList<Object> empList = new ArrayList<Object>();
				
				for (int i = 0; i < loadData.length; i++) {
					OpenCloseOfficeConfig beanList = new OpenCloseOfficeConfig();
					
					beanList.setEmpTokenList(String.valueOf(loadData[i][0]));
					beanList.setEnameList(String.valueOf(loadData[i][1]));
					beanList.setEmpIdList(String.valueOf(loadData[i][2]));
					empList.add(beanList);
				} //end of loop
				bean.setEmpList(empList);
				bean.setListNotnull(true);
			} //end of if
			else{
				bean.setListNotnull(false);
			} //end of else
		} catch (Exception e) {
			logger.error("exception in loadList",e);
		} //end of catch
		
		
	}
	
	public boolean delete(OpenCloseOfficeConfig bean) {
		boolean result = false;
		try {
			String deleteQuery = "DELETE FROM HRMS_OPEN_CLOSE_CONFIG "
							+" WHERE BRANCH_ID ="+bean.getBranchId()+" AND EMP_ID = "+bean.getDelEmpCode()+"";
			result = getSqlModel().singleExecute(deleteQuery);
			if(result){
				loadList(bean);
			}
			return result;
		} catch (Exception e) {
			logger.error("exception in delete",e);
			return false;
		} //end of catch
	} //end of delete method

}
