
package org.paradyne.model.payroll;

import java.util.ArrayList;


import org.paradyne.bean.payroll.BonusParaMaster;
 import org.paradyne.lib.ModelBase;

/**
 * @author Venkatesh
 *
 */
public class BonusParaModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	
	public boolean addBonusPara(BonusParaMaster bean)
	{
		Object addObj[][]=new Object[1][6];
		addObj[0][0]=bean.getBonusType();
		
		addObj[0][1]=bean.getBonDaysDec();
		addObj[0][2]=bean.getBonPrdFrom();
		addObj[0][3]=bean.getBonPrdTo();
		addObj[0][4]=bean.getTypeCode();
		addObj[0][5]=bean.getBonFormula();
		logger.info("Bonus Type"+addObj[0][0]);
		logger.info("Bonus Days Dec"+addObj[0][1]);
		logger.info("Bonus Prd From-"+addObj[0][2]);
		logger.info("Bonus Prd To-"+addObj[0][3]);
		logger.info("Bonus Emp Type-"+addObj[0][4]);
		return getSqlModel().singleExecute(getQuery(1), addObj);
		                                
	}
	public boolean modBonusPara(BonusParaMaster bean) {
		Object addObj[][] =new Object[1][7];
		
		addObj[0][0]=bean.getBonusType();
		addObj[0][1] =bean.getBonDaysDec();
		addObj[0][2] =bean.getBonPrdFrom(); 
		addObj[0][3] =bean.getBonPrdTo();
		addObj[0][4] =bean.getTypeCode();
		addObj[0][5]=bean.getBonFormula();
		addObj[0][6]=bean.getBonusCode();
		return getSqlModel().singleExecute(getQuery(2),addObj);		
	}
	
	public boolean deleteBonusPara(BonusParaMaster bean) {
		Object addObj[][] =new Object[1][1];
		
		addObj[0][0] =bean.getBonusCode();
		return getSqlModel().singleExecute(getQuery(3),addObj);	
		
	}
	public void getBonusParaReport(BonusParaMaster bean)
	{
		Object addObj[]=new Object[1];
		ArrayList<Object> bonusList=new ArrayList();
		
		Object[][] data=getSqlModel().getSingleResult(getQuery(5));
		for(int i=0;i<data.length;i++)
		{
			BonusParaMaster bean1=new BonusParaMaster();
			logger.info("In get Report");
			bean1.setBonusCode(String.valueOf(data[i][0]));
			bean1.setBonusType(String.valueOf(data[i][1]));
			bean1.setBonDaysDec(String.valueOf(data[i][2]));
			bean1.setBonPrdFrom(String.valueOf(data[i][3]));
			bean1.setBonPrdTo(String.valueOf(data[i][4]));
			bean1.setBonEmpType(String.valueOf(data[i][5]));
			bean1.setBonFormula(String.valueOf(data[i][6]));
			bonusList.add(bean1);
		}
		
		bean.setBonusArray(bonusList);
	}
	
	
	public void  getBonusParaRecord(BonusParaMaster bean) {
		Object addObj[] =new Object[1];
		
		addObj[0] =bean.getBonusCode();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4),addObj);
		
		for(int i=0; i<data.length; i++) {
			logger.info("I am get record");
			bean.setBonusCode(String.valueOf(data[i][0]));
			bean.setBonusType(String.valueOf(data[i][1]));
			if(String.valueOf(data[i][2])=="null")
			{
				bean.setBonDaysDec("");
			}
			else
			{
				bean.setBonDaysDec(String.valueOf(data[i][2]));
			}
			
			bean.setBonPrdFrom(String.valueOf(data[i][3]));
			bean.setBonPrdTo(String.valueOf(data[i][4]));
			
			bean.setBonEmpType(String.valueOf(data[i][5]));
			bean.setTypeCode(String.valueOf(data[i][7]));
			bean.setBonFormula(String.valueOf(data[i][6]));
			
		}
		
	}
}
