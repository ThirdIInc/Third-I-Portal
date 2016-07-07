package org.paradyne.model.conference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.conference.ConferenceMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author Tarun Chaturvedi
 * 20-12-2007
 * ConferenceMasterModel class to write business logic to save, update, delete
 * and view any conference accessories
 */
public class ConferenceMasterModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ConferenceMasterModel.class);

	/* method name : addConference 
	 * purpose     : to add new record
	 * return type : boolean
	 * parameter   : ConferenceMaster instance
	 */
	public boolean addConference(ConferenceMaster bean) {
		Object addObj [][] = new Object[1][2];
		
		addObj[0][0] = bean.getAccessoryName();						//Accessory name
		//addObj[0][1] = bean.getResPersonCode();						//Responsible person code
		addObj[0][1] = bean.getIsActive();		
	
		String query="SELECT NVL(MAX(ACCESSORY_CODE),0)+1 FROM HRMS_CONF_ACCESORIES";
		
		
		
		boolean result = checkDuplicateEntry(bean);
		
		if(result){
			Object [][] repData = getSqlModel().getSingleResult(query);
			result =  getSqlModel().singleExecute(getQuery(1), addObj);	//execute query to insert form data
			if(repData!=null&& repData.length>0)
				bean.setAccessCode(String.valueOf(repData[0][0]));
			
			return result;
			
		}	//end of if
		else{
			return false;
		}	//end of else
	}
	
	/* method name : checkDuplicateEntry 
	 * purpose     : to check whether the accessory name 
	 * 					already present in the table HRMS_CONF_ACCESORIES or not
	 * return type : boolean
	 * parameter   : ConferenceMaster instance
	 */
	public boolean checkDuplicateEntry(ConferenceMaster bean){
		boolean result = false;
		
		String query = "SELECT * FROM HRMS_CONF_ACCESORIES WHERE UPPER(ACCESSORY_NAME) LIKE '"+bean.getAccessoryName().trim().toUpperCase()+"'";
		
		Object [][]data = getSqlModel().getSingleResult(query);
		
		if(data != null && data.length >0){
			result = false;
		}	//end of if
		else{
			result = true;
		}	//end of else
		return result;
	}
	
	/* method name : modConference 
	 * purpose     : to modify the existing record
	 * return type : boolean
	 * parameter   : ConferenceMaster instance
	 */
	public boolean modConference (ConferenceMaster bean){
		Object modObj [][] = new Object[1][3];
		
		modObj [0][0] = bean.getAccessoryName();						//Accessory name
		//modObj [0][1] = bean.getResPersonCode();						//Responsible person code
		modObj [0][1] = bean.getIsActive();
		modObj [0][2] = bean.getAccessCode();							//Accessory code
			
		logger.info("modObj [0][1]--------   "+modObj [0][1]);
		
		boolean result = true;   //checkDuplicateEntry(bean);
		
		String query = "SELECT * FROM HRMS_CONF_ACCESORIES WHERE UPPER(ACCESSORY_NAME) LIKE '"+bean.getAccessoryName().trim().toUpperCase()+"' "
					   +"AND ACCESSORY_CODE != "+bean.getAccessCode();
		
		Object [][]data = getSqlModel().getSingleResult(query);
		
		if(data != null && data.length >0){
			result = false;
		}	//end of if
		else{
			result = true;
		}	//end of else
		
		if(result){
			return getSqlModel().singleExecute(getQuery(2), modObj);		//execute query to modify the form data
		}	//end od if
		else{
			return false;
		}	//end of else
	}
	
	/* method name : deleteConference 
	 * purpose     : to delete the selected record
	 * return type : boolean
	 * parameter   : ConferenceMaster instance
	 */
	public boolean deleteConference (ConferenceMaster bean){
		Object delObj [][] = new Object [1][1];
		
		delObj [0][0] = bean.getAccessCode();						//Accessory code
		
		return getSqlModel().singleExecute(getQuery(3), delObj);	//execute query to delete record
	}
	
	/* method name : report 
	 * purpose     : to create the report 
	 * return type : boolean
	 * parameter   : ConferenceMaster instance
	 */
	public boolean report(ConferenceMaster bean){
		Object [][] reportData = getSqlModel().getSingleResult(getQuery(4));
		
		ArrayList reportDataList = new ArrayList();
		boolean result = false;
		
		if(reportData.length!=0){
			for(int i=0; i<reportData.length; i++){
				ConferenceMaster bean1 = new ConferenceMaster();
				bean1.setAccessCode(String.valueOf(reportData[i][0]));
				bean1.setAccessoryName(String.valueOf(reportData[i][1]));
				//bean1.setResPersonName(String.valueOf(reportData[i][2]));
				reportDataList.add(bean1);
			}	//end of for loop
			
			bean.setConfList(reportDataList);
			result = true;
			
		}	//end of if
		return result;
	}
	
	/* method name : getReport 
	 * purpose     : to create crystal report 
	 * return type : void
	 * parameter   : ConferenceMaster instance, HttpServletRequest request, HttpServletResponse response,
	 *					ServletContext context
	 */
	public void getReport(ConferenceMaster confMaster, HttpServletRequest request, HttpServletResponse response,
			ServletContext context) {
		// TODO Auto-generated method stub
		CrystalReport cr = new CrystalReport();
		
		String path = "org\\paradyne\\rpt\\admin\\master\\ConeferenceAccessories.rpt ";
		
		cr.createReport(request, response, context,session, path, "");
	}

	/* method name : Data 
	 * purpose     : to retrieve the details of accessories on page loading
	 * return type : void
	 * parameter   : ConferenceMaster instance, HttpServletRequest request
	 */
	public void  Data(ConferenceMaster bean, HttpServletRequest request) {
		
			Object [][] obj = getSqlModel().getSingleResult(getQuery(4));
		
		
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
					
					 ConferenceMaster   bean1 = new ConferenceMaster();
					 	bean1.setAccessCode(String.valueOf(obj[i][0]));
						bean1.setAccessoryName(String.valueOf(obj[i][1]));
						bean1.setIsActive(String.valueOf(obj[i][2]));

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
	 * purpose     : to edit the accessory details
	 * return type : void
	 * parameter   : ConferenceMaster instance
	 */
	public void calforedit(ConferenceMaster bean) {
		logger.info("before query");

		String query =  "SELECT ACCESSORY_CODE, ACCESSORY_NAME  FROM HRMS_CONF_ACCESORIES "
						+" WHERE ACCESSORY_CODE ="+bean.getHiddencode();

		logger.info("After query"+bean.getHiddencode());

		Object [][]data=getSqlModel().getSingleResult(query);
		bean.setAccessCode(String.valueOf(data[0][0]));
		bean.setAccessoryName(String.valueOf(data[0][1]));
		/*bean.setResPersonName(String.valueOf(data[0][2]));
		bean.setResPersonToken(String.valueOf(data[0][3]));*/
	}
	
	/* method name : calfordelete 
	 * purpose     : to delete the selected accessory record
	 * return type : boolean
	 * parameter   : ConferenceMaster instance
	 */
	public boolean calfordelete(ConferenceMaster bean) {
		Object [][] delete = new Object [1][1];

		delete [0][0] =  bean.getHiddencode();

		return getSqlModel().singleExecute(getQuery(5), delete);
	}
	
	/* method name : deletecheckedRecords 
	 * purpose     : to delete the selected accessory records (more than one record)
	 * return type : boolean
	 * parameter   : ConferenceMaster instance, String[] code
	 */
	public boolean deletecheckedRecords(ConferenceMaster bean, String[] code) {
		boolean result = false;
		int count = 0;

		if(code != null){
			for (int i = 0; i < code.length; i++) {
				if(!code[i].equals("")){

					Object [][] delete = new Object [1][1];
					delete [0][0] =code[i] ;
					result=getSqlModel().singleExecute(getQuery(5), delete);
					if(!result)
						count++;
				}	//end of if
			}	//end of for loop
		}	//end of if

		if(count!=0){
			result=false;
			return result;
		}	//end of if
		else{
			result = true;
			return result; }
	}	//end of else
	
	public void getReport(ConferenceMaster bean, HttpServletRequest request,
			HttpServletResponse response,String[]label) {		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nConference Accessories\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Conference Accessories");
		String queryDes = "SELECT ROWNUM, ACCESSORY_NAME FROM HRMS_CONF_ACCESORIES "
		+"	ORDER BY ACCESSORY_CODE ";
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][2];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][1];
				j++;
			}
			int cellwidth[] = { 5,40};
			int alignment[] = { 1,0};
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
	
	
}
