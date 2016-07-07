package org.paradyne.model.LMS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.LMS.LmsMapDebits;
import org.paradyne.lib.ModelBase;

public class LmsMapDebitsModel extends ModelBase{

	public void getDebitList(LmsMapDebits mapdebitbean,
			HttpServletRequest request) {
		try {
					String query = "SELECT DEBIT_CODE,DEBIT_NAME,LMS_DEBIT_TYPE FROM HRMS_DEBIT_HEAD ";							
					Object[][] queryObj = getSqlModel().getSingleResult(query);			
					if(queryObj !=null && queryObj.length>0)
					{
						ArrayList list = new ArrayList();
						for (int i = 0; i < queryObj.length; i++) {
							LmsMapDebits bean = new LmsMapDebits();
							bean.setDebitID(checkNull(String.valueOf(queryObj[i][0])));					
							bean.setDebitName(checkNull(String.valueOf(queryObj[i][1])));
							bean.setDebitType(checkNull(String.valueOf(queryObj[i][2])));
							list.add(bean);
						}
						mapdebitbean.setMapDebitList(list);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
	
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} 
		else {
			return result;
		} 
	}


	public boolean saveRecords(LmsMapDebits mapdebitbean,
			HttpServletRequest request) {
		boolean result = false ; 
		try {			
			String debitID[] = request.getParameterValues("debitID");
			String debitType[] = request.getParameterValues("debitType");  
				Object addObj[][] =  new Object[debitID.length][2];
				for (int i = 0; i < debitID.length; i++) {					
						
						addObj[i][0] = checkNull(String.valueOf(debitType[i]));
						addObj[i][1] = checkNull(String.valueOf(debitID[i]));
					}
				String updateQuery = "UPDATE HRMS_DEBIT_HEAD SET LMS_DEBIT_TYPE=? WHERE DEBIT_CODE=?";
				result = getSqlModel().singleExecute(updateQuery, addObj);
				
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result ;
	}

}
