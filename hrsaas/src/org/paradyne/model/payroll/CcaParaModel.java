
package org.paradyne.model.payroll;

import java.util.ArrayList;


import org.paradyne.bean.payroll.CcaParaMaster;
 import org.paradyne.lib.ModelBase;

/**
 * @author Venkatesh
 *
 */
public class CcaParaModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	
	public boolean addCcaPara(CcaParaMaster bean) {
		Object addObj[][] =new Object[1][3];
			
			
			addObj[0][0] =bean.getEquiSalFrom(); 
			addObj[0][1] =bean.getEquiSalTo();
			addObj[0][2] =bean.getCcaAmt();
			
		return getSqlModel().singleExecute(getQuery(1),addObj);
	}
	
	public boolean modCcaPara(CcaParaMaster bean) {
		Object addObj[][] =new Object[1][4];
		   
		addObj[0][0] =bean.getEquiSalFrom();
		addObj[0][1] =bean.getEquiSalTo(); 
		addObj[0][2] =bean.getCcaAmt();
		addObj[0][3] =bean.getCcaCode();
		return getSqlModel().singleExecute(getQuery(2),addObj);		
	}
	
	public boolean deleteCcaPara(CcaParaMaster bean) {
		Object addObj[][] =new Object[1][1];
		
		addObj[0][0] =bean.getCcaCode();
		return getSqlModel().singleExecute(getQuery(3),addObj);	
		
	}
	public void getCcaParaReport(CcaParaMaster bean)
	{
		Object addObj[] =new Object[1];
		ArrayList<Object> dispList=new ArrayList();
		
		
		Object[][] data = getSqlModel().getSingleResult(getQuery(5));
		for(int i=0; i<data.length; i++) {
			CcaParaMaster bean1=new CcaParaMaster();
			logger.info("I am 2");
			bean1.setCcaCode(String.valueOf(data[i][0]));
			bean1.setEquiSalFrom(String.valueOf(data[i][1]));
			bean1.setEquiSalTo(String.valueOf(data[i][2]));
			bean1.setCcaAmt(String.valueOf(data[i][3]));
			
			dispList.add(bean1);
		}
		bean.setCcaArray(dispList);
		
	
	}
	public void  getCcaParaRecord(CcaParaMaster bean) {
		Object addObj[] =new Object[1];
		
		addObj[0] =bean.getCcaCode();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4),addObj);
		
		for(int i=0; i<data.length; i++) {
			logger.info("I am 2");
			bean.setCcaCode(String.valueOf(data[i][0]));
			bean.setEquiSalFrom(String.valueOf(data[i][1]));
			bean.setEquiSalTo(String.valueOf(data[i][2]));
			bean.setCcaAmt(String.valueOf(data[i][3]));
			
						
		}
		
	}
}
