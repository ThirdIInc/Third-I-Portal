package org.paradyne.model.conference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.conference.ConferenceMaster;
import org.paradyne.bean.conference.VenueMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author Tarun Chaturvedi
 * 29-12-2007
 * VenueMasterModel class to write business logic to save, update, delete
 * and view any conference rooms
 */
public class VenueMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(VenueMasterModel.class);
	
	/* method name : addVenue 
	 * purpose     : to add new record
	 * return type : String
	 * parameter   : VenueMaster instance
	 */
	public String addVenue(VenueMaster bean){
		Object [][] addObj = new Object[1][4];
		addObj[0][0] = bean.getVenueName();							//Venue name
		addObj[0][1] = bean.getResPersonCode();						//responsible person
		addObj[0][2] = bean.getBranchCode();						// conference branch
		addObj[0][3] = bean.getIsActive();
		boolean result = checkDuplicateEntry(bean);
		
		if(result){
			String query="SELECT NVL(MAX(VENUE_CODE),0)+1 FROM HRMS_CONF_VENUE";
			Object [][] repData = getSqlModel().getSingleResult(query);
			boolean b= getSqlModel().singleExecute(getQuery(1), addObj);	//execute query to insert data
			
			if(b){
				if(repData!=null&& repData.length>0)
					bean.setVenueCode(String.valueOf(repData[0][0]));
				  return "Record Saved Successfully";
			}	//end of if 
			else{
				  return "Record can not be saved";
			}	//end of else
		}	//end of if
		else{
			return "Duplicate entry found";
		}	//end of else
	}
	
	/* method name : checkDuplicateEntry 
	 * purpose     : to check whether the conference room
	 * 					already present in the table HRMS_CONF_VENUE or not
	 * return type : boolean
	 * parameter   : VenueMaster instance
	 */
	public boolean checkDuplicateEntry(VenueMaster bean){
		boolean result = false;
		
		String query="SELECT * FROM HRMS_CONF_VENUE WHERE UPPER(VENUE_NAME) LIKE '"+bean.getVenueName().trim().toUpperCase()+"'";
		
		Object [][]data = getSqlModel().getSingleResult(query);
		
		if(data != null && data.length >0){
			result = false;
		}	//end of if
		else{
			result = true;
		}	//end of else
		return result;
	}
	
	/* method name : modVenue 
	 * purpose     : to modify existing record 
	 * return type : String
	 * parameter   : VenueMaster instance
	 */
	public String modVenue(VenueMaster bean){
		Object [][] modObj = new Object [1][5];
		
		modObj[0][0] = bean.getVenueName();						//Venue name
		modObj[0][1] = bean.getResPersonCode();						//responsible person
		modObj[0][2] = bean.getBranchCode();						// conference branch
		modObj[0][3] = bean.getIsActive();
		modObj[0][4] = bean.getVenueCode();						//Venue code
		
		boolean result = true;   //checkDuplicateEntry(bean);
		
		String query = "SELECT * FROM HRMS_CONF_VENUE WHERE UPPER(VENUE_NAME) LIKE '"+bean.getVenueName().trim().toUpperCase()+"' "
						+"AND VENUE_CODE != "+bean.getVenueCode();
		
		Object [][]data = getSqlModel().getSingleResult(query);
		
		if(data != null && data.length >0){
			result = false;
		}	//end of if
		else{
			result = true;
		}	//end of e;se
		
		if(result){
			boolean b = getSqlModel().singleExecute(getQuery(2), modObj);//execute query to modify the record
			
			if(b) {
				  return "Record updated successfully.";
			} 	//end of if
			else {
				  return "Record can not be updated.";
			}	//end of else
		}else{
			return "Duplicate entry found.";
		}	//end of else
	}
	
	/* method name : deleteVenue 
	 * purpose     : to delete the selected record
	 * return type : boolean
	 * parameter   : VenueMaster instance
	 */
	public boolean deleteVenue(VenueMaster bean){
		Object[][] delObj = new Object[1][1];
		
		delObj[0][0] = bean.getVenueCode();							//venue code
		
		return getSqlModel().singleExecute(getQuery(3), delObj);	//execute query to delete record
 	}
	
	/* method name : report 
	 * purpose     : to retrieve the report data
	 * return type : boolean
	 * parameter   : VenueMaster instance
	 */
	/*public boolean report(VenueMaster bean){
		Object [][] reportData = getSqlModel().getSingleResult(getQuery(4));
		ArrayList reportDataList = new ArrayList();
		boolean result = false;
		if(reportData.length!=0){
			for(int i=0; i<reportData.length; i++){
				VenueMaster bean1 = new VenueMaster();
				bean1.setVenueCode(String.valueOf(reportData[i][0]));
				bean1.setVenueName(String.valueOf(reportData[i][1]));
				reportDataList.add(bean1);
			}
			bean.setVenueList(reportDataList);
			result = true;
		}
		return result;
	}*/

	/* method name : getReport 
	 * purpose     : to generate crystal report
	 * return type : boolean
	 * parameter   : VenueMaster instance, HttpServletRequest request,
	 *					HttpServletResponse response, ServletContext context,
	 *					HttpSession session
	 */
	public void getReport(VenueMasterModel model, HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			HttpSession session) {
		
		CrystalReport cr = new CrystalReport(); 
		
		String path = "org\\paradyne\\rpt\\admin\\master\\ConferenceRoom.rpt";
		
		cr.createReport(request, response, context,session, path, "");
	}

	/* method name : Data 
	 * purpose     : to retrieve the details of conference room on page loading
	 * return type : void
	 * parameter   : VenueMaster instance, HttpServletRequest request
	 */
	public void  Data(VenueMaster  bean, HttpServletRequest request) {
		
		Object [][] obj = getSqlModel().getSingleResult(getQuery(6));
		
		
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),obj.length,20);	
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
			   bean.setMyPage("1");
		  			
			
			ArrayList<Object> list = new ArrayList<Object>();
			if (obj != null && obj.length > 0) {

			//	for (int i = 0; i < obj.length; i++) {
				 for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
					
					 	VenueMaster  bean1 = new VenueMaster();

						bean1.setVenueCode(String.valueOf(obj[i][0]));
						bean1.setVenueName(String.valueOf(obj[i][1]));
						bean1.setBranchName(String.valueOf(obj[i][3]));
						bean1.setResPersonName(String.valueOf(obj[i][5]));
						bean1.setIsActive(String.valueOf(obj[i][6]));
						
					list.add(bean1);
				}
				
				 bean.setTotalRecords(""+obj.length);
			}
			else{
			
				bean.setListLength("false");
				
			}
			//bean.setEmpList(list);
			bean.setTotalRecords(String.valueOf(list.size()));
		    if(list.size()>0)
		    	bean.setListLength("true");
		    else
		    	bean.setListLength("false");
		
		    bean.setRecordList(list);
		    
		    
		 
	}
	
	/* method name : calforedit 
	 * purpose     : to edit the conference room details
	 * return type : void
	 * parameter   : VenueMaster instance
	 */
	public void calforedit(VenueMaster bean) {
		String query = " SELECT VENUE_CODE, VENUE_NAME,VENUE_BRANCH,CENTER_NAME,VENUE_RES_PERSON,EMP_TOKEN,EMP_FNAME ||' '||EMP_MNAME   ||' '||EMP_LNAME NAME FROM HRMS_CONF_VENUE " 
					 +" LEFT JOIN HRMS_CENTER ON(CENTER_ID= VENUE_BRANCH)"
					 +" LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=VENUE_RES_PERSON)"
					 +"WHERE VENUE_CODE="+bean.getHiddencode();

		Object [][]data=getSqlModel().getSingleResult(query);
		//setter

		bean.setVenueCode(checkNull(String.valueOf(data[0][0])));
		bean.setVenueName(checkNull(String.valueOf(data[0][1])));
		bean.setBranchCode(checkNull(String.valueOf(data[0][2])));
		bean.setBranchName(checkNull(String.valueOf(data[0][3])));
		bean.setResPersonCode(checkNull(String.valueOf(data[0][4])));
		bean.setResPersonToken(checkNull(String.valueOf(data[0][5])));
		bean.setResPersonName(checkNull(String.valueOf(data[0][6])));
		
	}
	
	/* method name : calfordelete 
	 * purpose     : to delete the selected conference room record
	 * return type : boolean
	 * parameter   : VenueMaster instance
	 */
	public boolean calfordelete(VenueMaster bean) {
		Object [][] delete = new Object [1][1];
		delete [0][0] =  bean.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);
		// TODO Auto-generated method stub
	}
	public void getReport(VenueMaster bean, HttpServletRequest request,
			HttpServletResponse response,String[]label) {		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nConference Room\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Conference Room");
		String queryDes = "SELECT ROWNUM, VENUE_NAME,CENTER_NAME,EMP_FNAME ||' '||EMP_MNAME   ||' '||EMP_LNAME NAME FROM HRMS_CONF_VENUE " 
					 +" LEFT JOIN HRMS_CENTER ON(CENTER_ID= VENUE_BRANCH)"
					 +" LEFT JOIN HRMS_EMP_OFFC ON(EMP_ID=VENUE_RES_PERSON) ORDER BY VENUE_NAME ";
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		//Object[][] Data = new Object[data.length][2];
		if (data != null && data.length > 0) {
		//	int j = 1;
			for (int i = 0; i < data.length; i++) {
				data [i][0] =String.valueOf(i+1);
			}
			int cellwidth[] = { 5,35,30,30};
			int alignment[] = { 1,0,0,0};
			rg.addFormatedText(reportName, 6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}
		rg.createReport(response);

	}

	/* method name : deletecheckedRecords 
	 * purpose     : to delete the selected conference room records (more than one record)
	 * return type : boolean
	 * parameter   : VenueMaster instance, String[] code
	 */
	public boolean deletecheckedRecords(VenueMaster bean, String[] code) {
		boolean result=false;
		int count=0;
		if(code !=null)
		{
			for (int i = 0; i < code.length; i++) {
			//logger.info("code======"+code[i]);
				if(!code[i].equals("")){

					Object [][] delete = new Object [1][1];
					delete [0][0] =code[i] ;
					result=getSqlModel().singleExecute(getQuery(3), delete);
					if(!result)
						count++;

				}	//end of if
			}	//end of for loop
		}	//end of if

		if(count!=0)
		{	result=false;
		return result;
		}	//end of if
		else
		{result=true;
		return result; 
		}	//end of else
	}
	
	public static String checkNull(String result) {
		if(result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

}
