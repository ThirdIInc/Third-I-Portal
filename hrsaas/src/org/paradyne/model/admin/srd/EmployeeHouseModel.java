/**
 * 
 */
package org.paradyne.model.admin.srd;

import java.util.ArrayList;

import org.paradyne.bean.admin.srd.EmpChangeHistory;
import org.paradyne.bean.admin.srd.EmployeeHouse;
 import org.paradyne.lib.ModelBase;

/**
 * @author balajim
 *
 */
public class EmployeeHouseModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	public boolean add(EmployeeHouse bean)
	{
		
		if(bean.getStatus().equals("Y"))
		{
			String query=" UPDATE HRMS_EMP_CREDIT SET CREDIT_AMT = 0 WHERE CREDIT_CODE = 4 AND EMP_ID = "+bean.getEmpID();
			logger.info("query================>>>>>>>>>>>"+query);
			boolean b = getSqlModel().singleExecute(query);	
		}
		Object addObj[][] =new Object[1][4];
		addObj[0][0]=bean.getEmpID();
		addObj[0][1]=bean.getHouseNO();
		addObj[0][2]=bean.getHouseColonyID();	
		addObj[0][3]=bean.getStatus();
		return getSqlModel().singleExecute(getQuery(1),addObj);	
		 
	}
	 
	public boolean update(EmployeeHouse bean)
	{
		if(bean.getStatus().equals("Y"))
		{
			String query=" UPDATE HRMS_EMP_CREDIT SET CREDIT_AMT = 0 WHERE CREDIT_CODE = 4 AND EMP_ID = "+bean.getEmpID();
			logger.info("query11111================>>>>>>>>>>>"+query);
			boolean b = getSqlModel().singleExecute(query);	
		}
		
		
		Object addObj[][] =new Object[1][4]; 
		addObj[0][0]=bean.getHouseNO();
		addObj[0][1] =bean.getHouseColonyID();
		addObj[0][2] =bean.getStatus();
		addObj[0][3] = bean.getHouseID(); 
		 getSqlModel().singleExecute(getQuery(3),addObj);	
		 bean.setFlag("false");
		return true; 
	}
	
	
	
	public boolean display(EmployeeHouse   bean)
	{
		Object addObj[] =new Object[1];
		addObj[0]=bean.getEmpID(); 
		
		String query="  SELECT HOUSE_NUMBER,COLONY_NAME ,CASE WHEN HOUSE_OCCUPANCY_STATUS ='Y' THEN 'Yes' ELSE 'No' END  ,HOUSE_ID FROM HRMS_EMP_HOUSE "
					 +"  INNER JOIN HRMS_HOUSING_COLONY ON(HRMS_EMP_HOUSE.HOUSE_COLONY_ID=HRMS_HOUSING_COLONY.COLONY_ID) "
					 +"  WHERE EMP_ID = "+bean.getEmpID()+"  ORDER BY HOUSE_ID ";
		
		
		Object[][] data = getSqlModel().getSingleResult(query);
		 
		
		ArrayList arry = new ArrayList();
		for(int i=0; i<data.length; i++)
		{	
			 EmployeeHouse bean1= new EmployeeHouse();
			 bean1.setHouseNO(checkNull(String.valueOf(data[i][0])));
			 bean1.setHouseColonyName(checkNull(String.valueOf(data[i][1])));
			 bean1.setStatus(checkNull(String.valueOf(data[i][2])));
			 bean1.setHouseID(checkNull(String.valueOf(data[i][3])));
			 
			 logger.info("value of Date___________________>>>>>"+String.valueOf(data[i][3]));
			arry.add(bean1);
		}
		 
		bean.setTableList(arry);
		
		
	 return true;
	}
	
	public boolean delete(EmployeeHouse bean)
	{
		Object addObj[][] =new Object[1][1];
		addObj[0][0]=bean.getParaId();
		return getSqlModel().singleExecute(getQuery(2),addObj);	
	}
	
	public void edit(EmployeeHouse bean)
	{
		Object addObj[][] =new Object[1][1];
		addObj[0][0]=bean.getParaId();
		logger.info("Value of Para ID=============================>>>>>>>>"+bean.getParaId());
		
		String query= "  SELECT HOUSE_NUMBER,COLONY_NAME , HOUSE_OCCUPANCY_STATUS  ,HOUSE_ID,COLONY_ID FROM HRMS_EMP_HOUSE  "  
		       +"  INNER JOIN HRMS_HOUSING_COLONY ON(HRMS_EMP_HOUSE.HOUSE_COLONY_ID=HRMS_HOUSING_COLONY.COLONY_ID)  "
               +"  WHERE HOUSE_ID = "+bean.getParaId()+"  ORDER BY HOUSE_ID  ";
		Object[][] data = getSqlModel().getSingleResult(query);	
		bean.setHouseNO(checkNull(String.valueOf(data[0][0])));
		bean.setHouseColonyName(checkNull(String.valueOf(data[0][1])));
		bean.setStatus(checkNull(String.valueOf(data[0][2])));
		bean.setHouseID(checkNull(String.valueOf(data[0][3])));
		bean.setHouseColonyID(checkNull(String.valueOf(data[0][4])));
		bean.setFlag("true");
	}
	
	
	 public void getEmployeeDetails(String empId,EmployeeHouse   bean)
	 {
	  String id =empId;
		/*String query =  " SELECT EMP_TOKEN,TO_CHAR(TITLE_NAME||'  '||EMP_FNAME||' '||EMP_MNAME||' '||'  '||EMP_LNAME),EMP_ID  FROM HRMS_EMP_OFFC   "
						+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) where EMP_ID = "+empId;	
			*/
	  
	  
	  String query = "SELECT EMP_TOKEN,TO_CHAR(TITLE_NAME||'  '||EMP_FNAME||' '||EMP_MNAME||' '||'  '||EMP_LNAME),TO_CHAR(EMP_CENTER||'-'||CENTER_NAME),TO_CHAR(RANK_NAME),EMP_ID"							
					+" FROM HRMS_EMP_OFFC "
					+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
					+" INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
					+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) where EMP_ID = "+empId;	
		Object[][] result = getSqlModel().getSingleResult(query);
		bean.setEmployeeToken(checkNull(String.valueOf(result[0][0])));
		bean.setEmpName(checkNull(String.valueOf(result[0][1])));
		bean.setCenter(checkNull(String.valueOf(result[0][2])));
		bean.setRank(checkNull(String.valueOf(result[0][3])));
		bean.setEmpID(checkNull(String.valueOf(result[0][4]))); 

	 }
	 
	 
	
	
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}	 

}
