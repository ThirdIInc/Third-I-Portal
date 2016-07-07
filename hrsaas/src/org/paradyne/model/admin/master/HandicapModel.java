package org.paradyne.model.admin.master;

import java.util.ArrayList;

import org.paradyne.bean.admin.master.HandicapMaster;
 import org.paradyne.lib.ModelBase;

/**
 * @author pranali 
 * Date 24-04-07
 */
public class HandicapModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 

	

	org.paradyne.bean.admin.master.HandicapMaster handicapMaster=null;
	
	public boolean addHandicap(HandicapMaster bean)
	{
		
		Object addObj[][] =new Object[1][1];
		addObj[0][0] =bean.getCatagoryName(); 
		return getSqlModel().singleExecute(getQuery(1),addObj);	
		
	}
	
	public boolean modHandicap(HandicapMaster bean)
	{
		
		Object addObj[][] =new Object[1][2];
				
		addObj[0][0] =bean.getCatagoryName(); 
					
		addObj[0][1] =bean.getCatagoryID();
		
		return getSqlModel().singleExecute(getQuery(2),addObj);		
	}
	
	public boolean deleteHandicap(HandicapMaster bean)  
	{
		logger.info("in deletehandicap");
		Object addObj[][] =new Object[1][1];
		addObj[0][0] =bean.getCatagoryID();
		logger.info("addObj[0][0] "+addObj[0][0]);
		return getSqlModel().singleExecute(getQuery(3),addObj);	
		
	}
	
	
	public void  getHandicapReport(HandicapMaster handicapMaster)
	{
		logger.info("in handicapreport");
		Object[][] data = getSqlModel().getSingleResult(getQuery(4));
	
		ArrayList<Object> att = new ArrayList<Object>();
		logger.info("length "+data.length);
		for(int i=0; i<data.length; i++)
		{	
			HandicapMaster bean1= new HandicapMaster();
			
			bean1.setCatagoryID(String.valueOf(data[i][0]));
			logger.info("1st arg "+String.valueOf(data[i][0]));
			bean1.setCatagoryName(String.valueOf(data[i][1]));
			logger.info("2st arg "+String.valueOf(data[i][1]));
			att.add(bean1);
			
		}
		handicapMaster.setAtt(att); 
		
		
	}
	
	

}
