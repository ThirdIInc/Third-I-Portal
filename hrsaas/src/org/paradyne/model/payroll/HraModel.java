package org.paradyne.model.payroll;

import org.paradyne.bean.payroll.HraMaster;
 import org.paradyne.lib.ModelBase;

public class HraModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	HraMaster hramaster =null;
	public boolean addHra(HraMaster bean) {
		Object addObj[][] =new Object[1][4];
			addObj[0][0] =bean.getHraParameterClass(); 
			addObj[0][1] =bean.getHraParameterCode();
			addObj[0][2] =bean.getHraParameterName();
			addObj[0][3] =bean.getHraParameterPercentage();
			
		return getSqlModel().singleExecute(getQuery(1),addObj);		
	}
	public boolean modHra(HraMaster bean) {
		Object addObj[][] =new Object[1][4];
		addObj[0][0] =bean.getHraParameterClass(); 
		addObj[0][1] =bean.getHraParameterCode();
		addObj[0][2] =bean.getHraParameterName();
		addObj[0][3] =bean.getHraParameterPercentage();
			
		return getSqlModel().singleExecute(getQuery(2),addObj);		
	}
	public boolean deleteHra(HraMaster bean) {
		Object addObj[][] =new Object[1][1];
		
		addObj[0][0] =bean.getHraParameterCode();
		return getSqlModel().singleExecute(getQuery(3),addObj);	
		
	}
	
	public void  getHraRecord(HraMaster bean) {
		Object addObj[] =new Object[1];
		
		addObj[0] =bean.getHraParameterCode();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4),addObj);
		
		for(int i=0; i<data.length; i++) {
			logger.info("I am 2");
			bean.setHraParameterCode(String.valueOf(data[i][0]));
			bean.setHraParameterName(String.valueOf(data[i][1]));
			bean.setHraParameterPercentage(String.valueOf(data[i][2]));
			bean.setHraParameterClass(String.valueOf(data[i][3]));
		}
		
	}
	
}
