package org.paradyne.model.admin.master;


import java.util.ArrayList;

import org.paradyne.bean.admin.master.CreditMaster;

 import org.paradyne.lib.ModelBase;
/**
 * @author pranali 
 * Date 25-04-07
 */
public class CreditModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 

	
org.paradyne.bean.admin.master.CreditMaster creditMaster=null;
	

	public boolean addCredit(CreditMaster bean)
	{
		Object addObj[][] =new Object[1][2];
		addObj[0][0] =bean.getCreditName().trim(); 
		addObj[0][1] =bean.getCreditAbbr().trim();
		return getSqlModel().singleExecute(getQuery(1),addObj);	
	}
	
	
	
	public boolean modCredit(CreditMaster bean)
	{
		Object addObj[][] =new Object[1][3];
		addObj[0][0] =bean.getCreditName().trim(); 
		addObj[0][1] =bean.getCreditAbbr().trim();
		addObj[0][2] =bean.getCreditCode().trim();
		return getSqlModel().singleExecute(getQuery(2),addObj);		
	}
	
	
	
	public boolean deleteCredit(CreditMaster bean)
	{
		Object addObj[][] =new Object[1][1];
		addObj[0][0] =bean.getCreditCode();
		return getSqlModel().singleExecute(getQuery(3),addObj);	
	}
	
	
	
	public void  getCreditReport(CreditMaster creditMaster)
	{
		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		ArrayList<Object> att = new ArrayList<Object>();
		for(int i=0; i<data.length; i++)
		{	
			CreditMaster bean1= new CreditMaster();
			bean1.setCreditCode(String.valueOf(data[i][0]));
			bean1.setCreditName(String.valueOf(data[i][1]));
			bean1.setCreditAbbr(String.valueOf(data[i][2]));
			att.add(bean1);	
		}
		creditMaster.setAtt(att); 
	}
	
}
