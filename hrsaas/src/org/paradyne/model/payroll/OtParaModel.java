
package org.paradyne.model.payroll;

import java.util.ArrayList;


import org.paradyne.bean.payroll.OtParaMaster;
 import org.paradyne.lib.ModelBase;

/**
 * @author Venkatesh
 *
 */
public class OtParaModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	
	public boolean addOtPara(OtParaMaster bean) {
		Object addObj[][] =new Object[1][5];
			
			addObj[0][0] =bean.getTypeCode();
			addObj[0][1] =bean.getNormalCalSingle(); 
			addObj[0][2] =bean.getNormalCalDouble(); 
			addObj[0][3] =bean.getHoliCalSingle();
			addObj[0][4] =bean.getHoliCalDouble();
						
			
			logger.info("into model");
		return getSqlModel().singleExecute(getQuery(1),addObj);
	}
	
	public boolean modOtPara(OtParaMaster bean) {
		Object addObj[][] =new Object[1][5]; 
		addObj[0][0] =bean.getNormalCalSingle(); 
		addObj[0][1] =bean.getNormalCalDouble(); 
		addObj[0][2] =bean.getHoliCalSingle();
		addObj[0][3] =bean.getHoliCalDouble();
		addObj[0][4] =bean.getTypeCode();
	 
		
		return getSqlModel().singleExecute(getQuery(2),addObj);		
	}
	
	public boolean deleteOtPara(OtParaMaster bean) {
		Object addObj[][] =new Object[1][1];
		
		addObj[0][0] =bean.getTypeCode();
		return getSqlModel().singleExecute(getQuery(3),addObj);	
		
	}
	public void getOtParaReport(OtParaMaster bean)
	{
		Object addObj[] =new Object[1];
		ArrayList<Object> dispList=new ArrayList();
		
		
		Object[][] data = getSqlModel().getSingleResult(getQuery(6));
		for(int i=0; i<data.length; i++) {
			OtParaMaster bean1=new OtParaMaster();
			 
			bean1.setTypeName(checkNull(String.valueOf(data[i][0])));
			bean1.setNormalCalSingle(checkNull(String.valueOf(data[i][1]))); 
			bean1.setNormalCalDouble(checkNull(String.valueOf(data[i][2]))); 
			bean1.setHoliCalSingle(checkNull(String.valueOf(data[i][3])));
			bean1.setHoliCalDouble(checkNull(String.valueOf(data[i][4])));
			dispList.add(bean1); 
		}
		bean.setOtParaArray(dispList);
		
	
	}
	public void  getOtParaRecord(OtParaMaster bean) {
		Object addObj[] =new Object[1];
		
		addObj[0] =bean.getOtId();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4),addObj);
		
		for(int i=0; i<data.length; i++) {
			logger.info("I am getrcord");
			bean.setOtId(String.valueOf(data[i][0]));
			bean.setTypeCode(String.valueOf(data[i][1]));
			bean.setNormalCalSingle(String.valueOf(data[i][2])); 
			bean.setNormalCalDouble(String.valueOf(data[i][3]));
			bean.setMaximumCap(String.valueOf(data[i][4]));
			bean.setOtPolicy(String.valueOf(data[i][5]));
			logger.info("OtPolicu value -" +bean.getOtPolicy());
			bean.setMinimumFloor(String.valueOf(data[i][6]));
			bean.setHoliCalSingle(String.valueOf(data[i][7]));
			bean.setHoliCalDouble(String.valueOf(data[i][8]));
				
		}
		
	}
	
	
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}	
}
