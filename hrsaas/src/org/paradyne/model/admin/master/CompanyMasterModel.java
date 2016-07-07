/**
 * 
 */
package org.paradyne.model.admin.master;

import org.paradyne.bean.admin.master.CompanyMaster;
import org.paradyne.lib.ModelBase;

/**
 * @author balajim
 *
 */
public class CompanyMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	
	public boolean save(CompanyMaster bean)
	{
		
		 Object[][] addObj=null;
		 try {
			addObj = new Object[1][10];
			addObj[0][0] = bean.getCompName().trim();
			addObj[0][1] = bean.getAddress().trim();
			addObj[0][2] = bean.getCityId();
			addObj[0][3] = bean.getPincode().trim();
			addObj[0][4] = bean.getTelephone().trim();
			addObj[0][5] = bean.getWebsite().trim();
			addObj[0][6] = bean.getTanNo().trim();
			addObj[0][7] = bean.getBankName().trim();
			addObj[0][8] = bean.getUploadFileName().trim();
			addObj[0][9] = bean.getDisplayName().trim();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getSqlModel().singleExecute(getQuery(1), addObj);
		
		  
	}
	
 	public boolean update(CompanyMaster bean)
	{
		
		  Object[][] addObj = new Object[1][11];
		 try {
			addObj[0][0] = bean.getCompName().trim();
			addObj[0][1] = bean.getAddress().trim();
			addObj[0][2] = bean.getCityId();
			addObj[0][3] = bean.getPincode().trim();
			addObj[0][4] = bean.getTelephone().trim();
			addObj[0][5] = bean.getWebsite().trim();
			addObj[0][6] = bean.getTanNo();
			addObj[0][7] = bean.getBankName().trim();
			addObj[0][8] = bean.getUploadFileName().trim();
			addObj[0][9] = bean.getDisplayName().trim();
			addObj[0][10] = bean.getCompCode();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getSqlModel().singleExecute(getQuery(2), addObj);
		
		  
	} 
 	
 	
 	public boolean delete(CompanyMaster bean)
 	{
 		Object[][] addObj=null;
 		 try {
			addObj = new Object[1][1];
			addObj[0][0] = bean.getCompCode();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getSqlModel().singleExecute(getQuery(3), addObj);
 	}

	public void data(CompanyMaster bean) {
		try{
		Object[]para=new Object[1]; 
		para[0]=bean.getCompCode();
		//logger.info("before sai...."+para[0]);
		Object[][] addObj =  getSqlModel().getSingleResult(getQuery(4));
		//logger.info("After sai....");
		bean.setAddress(checkNull(String.valueOf(addObj[0][0])));
		bean.setCityName(checkNull(String.valueOf(addObj[0][1])));
		bean.setPincode(checkNull(String.valueOf(addObj[0][2])));
		bean.setTelephone(checkNull(String.valueOf(addObj[0][3])));
		bean.setWebsite(checkNull(String.valueOf(addObj[0][4])));
		bean.setTanNo(checkNull(String.valueOf(addObj[0][5])));
		bean.setBankName(checkNull(String.valueOf(addObj[0][6])));
		bean.setUploadFileName(checkNull(String.valueOf(addObj[0][7])));
		bean.setCompCode(String.valueOf(addObj[0][8]));
		bean.setCompName(checkNull(String.valueOf(addObj[0][9])));
		bean.setCityId(String.valueOf(addObj[0][10]));
		bean.setDisplayName(checkNull(String.valueOf(addObj[0][11])));
		 
		}catch(Exception e)
		{
			e.printStackTrace();
		 
		}
	}
	public String checkNull(String result) {
		/*
		 * method to check the null value
		 * 
		 */
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

}
