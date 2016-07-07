/**
 * 
 */
package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.master.LocationMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author Lakkichand
 * @date 19 APR 2007
 */
/** to define  business logic for location master
 * 
 */
public class LocationMasterModel extends ModelBase {  
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	public String save (LocationMaster bean, String locType){
		String query1="SELECT NVL(MAX(LOCATION_CODE),0)+1 FROM  HRMS_LOCATION";
		Object[][]leave=getSqlModel().getSingleResult(query1);
		 bean.setLocationCode(String.valueOf(leave[0][0]));
		
		String flag = "";
		boolean result = false;
		
		String query = "SELECT UPPER(LOCATION_NAME) FROM HRMS_LOCATION WHERE (LOCATION_NAME='"+bean.getLocationName().trim().toUpperCase()+"' OR LOCATION_NAME='"
						+bean.getLocationName().trim().toLowerCase()+"') AND LOCATION_TYPE='"+locType+"' ";
		
		if(locType.equals("S")){ 
			query += "AND LOCATION_PARENT_CODE = "+bean.getCountryCode();
		}
		
		if(locType.equals("Ci")){// to get the city name
			query += "AND LOCATION_PARENT_CODE = "+bean.getStateCode();
		}
		
		Object [][] data = getSqlModel().getSingleResult(query);
		
		if(data != null && data.length>0){  // to check the duplicate  entry or record
			flag = "duplicate";
		}
		else{
			String locationType 	= bean.getLocationType();// to set the location type
			//logger.info("locationType   "+locationType);
			
			if(locationType.equals("Country")){ // to get the country name
				//logger.info("cccccccccccccc  ");
				
				Object [][] saveData 	= new Object[1][3];
				saveData [0][0]     	= bean.getLocationName();
				saveData [0][1]			= locType;
				saveData [0][2]			= bean.getIsActive();
				
				//saveData [0][2]         =bean.getLocclass();
					
				result = getSqlModel().singleExecute(getQuery(1), saveData);
				 
				if(result)flag = "saved";
				else flag = "error";
			}
			if(locationType.equals("State")){ //  to get the state details for  city
				//logger.info("sssssssssss  ");
					
				Object [][] saveData 	= new Object[1][3];
				saveData [0][0]     	= bean.getLocationName();
				saveData [0][1]			= bean.getCountryCode();
				saveData [0][2]			= locType;
					
				result =  getSqlModel().singleExecute(getQuery(2), saveData);
					
				if(result)flag = "saved";
				else flag = "error";
				
			}
			if(locationType.equals("City")){ //  to get the city details 
				//logger.info("Ciiiiiiiiii  ");
					
				Object [][] saveData 	= new Object[1][4];
				saveData [0][0]     	= bean.getLocationName();
				saveData [0][1]			= bean.getStateCode();
				saveData [0][2]			= locType;
				saveData [0][3]         = bean.getLocclass();
					
				result = getSqlModel().singleExecute(getQuery(3), saveData);
					
				if(result)flag = "saved";
				else flag = "error";
			}
		}
		return flag;
	}
	
	/**
	 *   to modify/update the record
	 * @param bean
	 * @param locationType
	 * @return
	 */
	public String modify(LocationMaster bean, String locationType){
		String flag = "";
		boolean result = false;
		
		Object [][] modData = new Object[1][7];
		String locType      = bean.getLocationType();
		
		String query = "SELECT UPPER(LOCATION_NAME) FROM HRMS_LOCATION WHERE (LOCATION_NAME = '"+bean.getLocationName().trim().toUpperCase()+"' OR LOCATION_NAME = '"
						+bean.getLocationName().trim().toLowerCase()+"') AND LOCATION_TYPE = '"+locationType+"' AND LOCATION_CODE != "+bean.getLocationCode();

		if(locationType.equals("S")){
			query += " AND LOCATION_PARENT_CODE = "+bean.getCountryCode();
		}
		
		if(locationType.equals("Ci")){
			query += " AND LOCATION_PARENT_CODE = "+bean.getStateCode();
		}
		
		//logger.info("query  "+query);
		Object [][] data = getSqlModel().getSingleResult(query);
		
		if(data != null && data.length >0){
			//logger.info("ifffffffffffff");
			flag = "duplicate";
		}
		else{
			//logger.info("elseeeeeeeeeeeeeeee");
			modData [0][0]      = bean.getLocationName();
			
			if(locType.equals("State")){ // to check the state for modify 
				//logger.info("state");
				modData [0][1] 		= bean.getCountryCode();
				modData [0][3]  	= "1";
				//modData [0][4]  	= "";
				logger.info("modData [0][1]   "+modData [0][1]);
			}else if(locType.equals("City")){// to check the city  for modify 
				//logger.info("city");
				modData [0][1] 		= bean.getStateCode();
				modData [0][3]  	= "2";
				modData [0][4]  	= bean.getLocclass();
				//logger.info("modData [0][1]   "+modData [0][1]);
			}else{
				modData [0][3]  	= "0";
				modData [0][4]  	= "";
			}
			//modData [0][2]		= bean.getLocationType();
			modData [0][2]		= locationType;
			modData [0][5]		= bean.getIsActive();
			modData [0][6]  	= bean.getLocationCode();
			//modData [0][4]  	= bean.getLocclass();
			
			result = getSqlModel().singleExecute(getQuery(6), modData);
			
			if(result){
				flag = "updated";
			}
			else{
				flag = "error";
			}
		}
		return flag;
	}
	/**
	 *  to delete the single record after clicking save /search button
	 * @param bean
	 * @return
	 */
	
	public String delete (LocationMaster bean){
		boolean result = false;
		String flag = "";
		
		String query = "SELECT * FROM HRMS_LOCATION WHERE LOCATION_PARENT_CODE = "+bean.getLocationCode();
		
		Object [][] isDataExist = getSqlModel().getSingleResult(query);
		
		if(isDataExist != null && isDataExist.length >0){
			flag = "referenced";
		}
		else{
			result = getSqlModel().singleExecute(getQuery(4), new Object [][]{{bean.getLocationCode()}});
			
			if(result)flag = "deleted";
			else flag = "error";
		}
		return flag;
		/*Object [][] delete = new Object [1][1];
		delete [0][0] = bean.getLocationCode();
		return getSqlModel().singleExecute(getQuery(4), delete);*/
	}
	
	public boolean report(LocationMaster bean){
		//logger.info("in side report model");
		Object [][] reportData = getSqlModel().getSingleResult(getQuery(7));
		//logger.info("result.length   "+reportData.length);
		ArrayList reportDataList = new ArrayList();
		boolean result = false;
		if(reportData.length!=0){
			for(int i=0;i<reportData.length;i++){
				LocationMaster bean1 = new LocationMaster();
				bean1.setLocationCode(checkNull(String.valueOf(reportData[i][0])));
				bean1.setLocationName(checkNull(String.valueOf(reportData[i][1])));
				bean1.setLocationType(checkNull(String.valueOf(reportData[i][2])));
				reportDataList.add(bean1);
			}
			bean.setLocationList(reportDataList);
			result = true;
		}
		return result;
	}
	/**
	 *  to display the  record after clicking on search button
	 * @param bean
	 */
	public void showRecord(LocationMaster bean){
		//logger.info("in side model showRecord=============");
		//logger.info("location name    "+bean.getLocationName());
		Object [] parentCode = new Object [1];
		parentCode [0]	     = bean.getParentCode();	
		//logger.info("Parent Code    "+bean.getParentCode());
		String locationType = bean.getLocationType();
		//logger.info("Location Type   "+bean.getLocationType());
		if(locationType.equals("State")){ // to get the  State 
			Object [][] result = getSqlModel().getSingleResult(getQuery(5), parentCode);
			if(!(result.length==0)){
			bean.setCountryCode(checkNull(String.valueOf(result[0][0])));
			bean.setCountryName(checkNull(String.valueOf(result[0][1])));
			}
		}
		
		if(locationType.equals("City")){ // to get the city
			Object [][] result = getSqlModel().getSingleResult(getQuery(5), parentCode);
			//logger.info("length  =="+result.length);
			if(!(result.length==0)){
			bean.setStateCode(checkNull(String.valueOf(result[0][0])));
			bean.setStateName(checkNull(String.valueOf(result[0][1])));
			
			
			Object [] countryCode  = new Object [1];
			countryCode [0]        = result[0][2];
			
			Object [][] result1 = getSqlModel().getSingleResult(getQuery(5), countryCode);
			bean.setCountryCode(checkNull(String.valueOf(result1[0][0])));
			bean.setCountryName(checkNull(String.valueOf(result1[0][1])));
			}
		}
	}
	/**
	 *  to check the  null value
	 * @param result
	 * @return
	 */
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
/**
 *  to generate the report
 * @param locationMaster
 * @param request
 * @param response
 * @param context
 * @param label
 */
	public void getReport(LocationMaster locationMaster,
			HttpServletRequest request, HttpServletResponse response,
			ServletContext context,String []label) {
		// TODO Auto-generated method stub

		/*CrystalReport cr=new CrystalReport();
		String path="org\\paradyne\\rpt\\admin\\master\\location.rpt ";
		cr.createReport(request, response, context,session, path, "");*/
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nLocation Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Location  Master.Pdf");
		String queryDes = "SELECT  LOCATION_NAME, DECODE(LOCATION_TYPE, 'C','Country','S','State','Ci','City'), DECODE(IS_ACTIVE,'Y','YES','N','NO','NO') "
						+" FROM HRMS_LOCATION "
						+" ORDER BY upper(LOCATION_NAME)";
						
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][4];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];
				
				j++;
			}
			int cellwidth[] = { 15, 40, 30, 15 };
			int alignment[] = { 1, 0, 0, 0 };
			rg.addFormatedText(reportName, 6, 0, 1, 0);
			rg.addText("\n",0,0,0);
			rg.addTextBold("Date :"+toDay, 0, 2, 0);
			rg.addText("\n",0,0,0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);
		
	}
/**
 *  to display the record in onload
 * @param locationMaster
 * @param request
 */
	public void employeeSearch(LocationMaster locationMaster, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Object [][] repData = getSqlModel().getSingleResult(getQuery(7));
	if(repData!=null && repData.length>0){
		locationMaster.setModeLength("true");
		locationMaster.setTotalRecorde(String.valueOf(repData.length));
		
		String[] pageIndex = Utility.doPaging(locationMaster.getMyPage(), repData.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			locationMaster.setMyPage("1");
		ArrayList<Object> List = new ArrayList<Object>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
			
				LocationMaster bean1 = new LocationMaster();
				bean1.setLocationCode(String.valueOf(repData[i][0]));
				bean1.setLocationName(String.valueOf(repData[i][1]));
				bean1.setLocationType(String.valueOf(repData[i][2]));
				bean1.setIsActive(String.valueOf(repData[i][3]));
				
				List.add(bean1);
			}
			locationMaster.setTypeList(List);
		
	}
	}
/**
 *  to modify the data 
 * @param locationMaster
 */
	public void calforedit(LocationMaster locationMaster) {
		
		// TODO Auto-generated method stub
		String query = " SELECT  LOCATION_CODE, LOCATION_NAME, DECODE(LOCATION_TYPE,'C','Country','S','State','Ci','City'), LOCATION_PARENT_CODE,LOCATION_CLASS, IS_ACTIVE "
			+ " FROM HRMS_LOCATION where LOCATION_CODE= "+locationMaster.getHiddencode();
			
		
		Object [][]data=getSqlModel().getSingleResult(query);
		locationMaster.setLocationCode(String.valueOf(data[0][0]));
		locationMaster.setLocationName(String.valueOf(data[0][1]));
		locationMaster.setLocationType(String.valueOf(data[0][2]));
		locationMaster.setParentCode(String.valueOf(data[0][3]));
		locationMaster.setLocclass(String.valueOf(data[0][4]));
		locationMaster.setIsActive(String.valueOf(data[0][5]));
	}
/**
 *  to delete the single record
 * @param locationMaster
 * @return
 */
	public boolean calfordelete(LocationMaster locationMaster){
		Object [][] delete = new Object [1][1];
		delete [0][0] = locationMaster.getHiddencode();
		return getSqlModel().singleExecute(getQuery(4), delete);
	}
/**
 *  to delete the multiple record in the list 
 * @param bean
 * @param code
 * @return
 */
	public boolean deleteLocation(LocationMaster bean, String[] code) {
		boolean result = false;
		String flag   = "";
		
		if(code != null){
			for (int i = 0; i < code.length; i++) {
				if(!code[i].equals("")){
					//logger.info(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"+code[i]);
					
					String query = "SELECT * FROM HRMS_LOCATION WHERE LOCATION_PARENT_CODE = "+code[i];
					
					Object [][] isDataExist = getSqlModel().getSingleResult(query);
					
					if(isDataExist != null && isDataExist.length >0){
						flag = "referenced";
						result = false;
					}
					else{
						result	= getSqlModel().singleExecute(getQuery(4), new Object [][] {{code[i]}});
						
						if(flag.equals("referenced"))result = false;
					}
				}
			}
		}
		return result;
	}
}
