/**
 * 
 */
package org.paradyne.model.admin.master;

import java.util.ArrayList;


import org.paradyne.bean.admin.master.DebitHeadMaster;
 import org.paradyne.lib.ModelBase;

/**
 * @author lakkichand
 * @date 25 APR 2007
 */
public class DebitHeadModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	public boolean addDebit (DebitHeadMaster bean){
		Object addObj [][]=new Object[1][2];
		addObj [0][0]=bean.getDebitName().trim();
		addObj [0][1]=bean.getDebitAbbr().trim();
		return getSqlModel().singleExecute(getQuery(1), addObj);
	}
	
	public boolean modDebit (DebitHeadMaster bean){
		Object addObj [][]=new Object[1][3];
		addObj [0][0]=bean.getDebitName().trim();
		addObj [0][1]=bean.getDebitAbbr().trim();
		addObj [0][2]=bean.getDebitCode().trim();
		return getSqlModel().singleExecute(getQuery(2), addObj);
	}
	
	public boolean deleteDebit(DebitHeadMaster bean){
		Object addObj [][]=new Object[1][1];
		addObj [0][0]=bean.getDebitCode();
		return getSqlModel().singleExecute(getQuery(3), addObj);
	}
	
	public void getDebitRecord(DebitHeadMaster bean){
		Object addObj []= new Object [1];
		addObj[0]=bean.getDebitCode();
		Object [][] data=getSqlModel().getSingleResult(getQuery(4), addObj);
		
		for(int i=0;i<data.length;i++){
			bean.setDebitCode(String.valueOf(data [i][0]));
			bean.setDebitName(String.valueOf(data [i][1]));
			bean.setDebitAbbr(String.valueOf(data [i][2]));
		}
		
	}
	public void getDebitReport(DebitHeadMaster bean){
		
		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		
		ArrayList<Object> debList = new ArrayList<Object>();
		
		for(int i=0; i<data.length; i++){
			DebitHeadMaster bean1 =new DebitHeadMaster();
			bean1.setDebitCode(String.valueOf(data [i][0]));
			bean1.setDebitName(String.valueOf(data [i][1]));
			bean1.setDebitAbbr(String.valueOf(data [i][2]));
			debList.add(bean1);
		}
		bean.setDebitList(debList);
	}
		

}
