/**
 * 
 */
package org.paradyne.model.settings;

import java.util.ArrayList;

import org.paradyne.bean.settings.SuggestionAppr;
import org.paradyne.lib.ModelBase;

/**
 * @author ritac
 *
 */
public class SuggestionApprModel extends ModelBase {

	public boolean setData(SuggestionAppr bean,String stat) {
		try {
		
		Object[]flag=new Object[1];
		flag[0]=bean.getSuggStatus();
		System.out.println("hswdh"+bean.getSuggStatus());
		String Query="";
		
		 Query=" SELECT SUGGESTION_CODE,SUGGESTION_SUBJECT,SUGGESTION_FLAG FROM HRMS_SUGGESTION WHERE SUGGESTION_FLAG='"+flag[0]+"' ORDER BY SUGGESTION_CODE ";
		
		Object [][]data=getSqlModel().getSingleResult(Query);
		ArrayList<Object> list =new ArrayList<Object>();
		if(data.length>0)
		{
			for (int j = 0; j < data.length; j++) {
				
			
			SuggestionAppr bean1=new SuggestionAppr();
			bean1.setSuggestionCode(String.valueOf(data[j][0]));
			bean1.setSuggestionSub(String.valueOf(data[j][1]));
			bean1.setCheckStatus(String.valueOf(data[j][2]));
			System.out.println("status==="+bean1.getCheckStatus());
			bean1.setSrNo(String.valueOf(j+1));
			
			list.add(bean1);
			}
			bean.setSuggDetail("1");
			bean.setList(list);
		}
		
		return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public boolean saveData(SuggestionAppr suggAppr, 
			Object[] suggCode, Object[] checkStatus) {
		try {
		
		Object [][] changeStatus = new Object[checkStatus.length][2];
		boolean result = false;
		for (int i=0;i<checkStatus.length;i++){
			if(!checkStatus[i].equals('P'))
			{
			changeStatus[i][0] = checkStatus[i];
			System.out.println("changeStatus   "+ changeStatus[i][0]);
			changeStatus[i][1] = suggCode[i];
			System.out.println("changeStatus  vo no "+ changeStatus[i][1]);
		String query="UPDATE HRMS_SUGGESTION SET SUGGESTION_FLAG=? WHERE SUGGESTION_CODE=?";
	
		String stat = suggAppr.getStatus();
		System.out.println("status is  "+stat);
		
		result = getSqlModel().singleExecute(query,changeStatus);
		
	
		Object[]sugcode=new Object[1];
		sugcode[0]=suggCode[i];
		System.out.println("vchno is ==="+sugcode[0]);
		
		setData(suggAppr, stat);
			}
			System.out.println("changeStatus is ==="+changeStatus[i][0]);
		}
		//bean.setList(list);
		return result;
		// TODO Auto-generated method stub
		
	}
		
	 catch (Exception e) {
		// TODO: handle exception
		 return false;
	}
	}
}
		
	


