/**
 * Balaji
 * 22-08-2008
**/ 
package org.paradyne.model.TravelManagement;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.TravelManagement.TravelConfirmation;
import org.paradyne.bean.TravelManagement.TravelSchedule;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;

public class TravelConfirmationModel extends ModelBase {
	/*
	 *  This method is used for show the scheduled travel list
	 */
	/**
	 * @param travelConf
	 */
	public void generateListForConfirm(TravelConfirmation travelConf,String status,HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		if(status.equals("D"))
			travelConf.setPend("true");
		else if(status.equals("C"))
		{
			travelConf.setConfirm("true");
			travelConf.setPend("false");
		}
		else if(status.equals("N"))
		{
			travelConf.setCancel("true");
			travelConf.setPend("false");
		}
		
		String query ="SELECT TRAVEL_EMPID,EMP_TOKEN, 	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,NVL(TO_CHAR(TRAVEL_APPDATE,'DD-MM-YYYY'),''),TRAVEL_ID " 
					+"  FROM HRMS_TRAVEL "
					+" INNER JOIN  HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TRAVEL.TRAVEL_EMPID) "
				    +" WHERE TRAVEL_SCHEDULE_STATUS='S'" ;
					if(status.equals("N"))
					{
						query += " AND TRAVEL_CONFIRM_STATUS IN('N','K') AND TRAVEL_EMPID ="+travelConf.getUserEmpId() ;
					}else
					{
						query += " AND TRAVEL_CONFIRM_STATUS='"+status+"' AND TRAVEL_EMPID ="+travelConf.getUserEmpId() ;
						
					}
					query += "ORDER BY TRAVEL_APPDATE DESC ";
					//
		Object[][] userData = getSqlModel().getSingleResult(query);
		
		doPaging(travelConf, userData.length, new Object[][]{}, request);
		int fromTotRec = Integer.parseInt(travelConf.getFromTotRec());
		int toTotRec = Integer.parseInt(travelConf.getToTotRec());
		
		ArrayList<Object> arr =new ArrayList<Object>();		
		
		if(userData!=null)
		{
			for (int i = fromTotRec; i < toTotRec; i++) {
				
				TravelConfirmation travelCon =new TravelConfirmation();
				travelCon.setEmpId(checkNull(String.valueOf(userData[i][0])));
				travelCon.setTokenNo(checkNull(String.valueOf(userData[i][1])));
				travelCon.setEmpName(checkNull(String.valueOf(userData[i][2])));
				travelCon.setTravelDate(checkNull(String.valueOf(userData[i][3])));
				travelCon.setTravelID(checkNull(String.valueOf(userData[i][4])));
				if(status.equals("D"))
					travelCon.setItPend("true");
				arr.add(travelCon);		
			}
			travelConf.setTravelConfList(arr);
		} // end of if 
		 if(arr.size()==0)
		 {  
			 travelConf.setNoData("true"); 
		 }else
		 { 
			 travelConf.setNoData("false"); 
		 }
		
	}  // end of generateListForConfirm
	
	
	public void getAdminCancelList(TravelConfirmation travelConf,String cancelStatus,String confStatus,HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("travelConf.getConfStatus()>>>>>>>>.."+travelConf.getCancelStatus());
		 
	     if(confStatus.equals("N"))
	     {   
	    	 travelConf.setCStatus("true");
	     }
		String query ="SELECT TRAVEL_EMPID,EMP_TOKEN, 	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,NVL(TO_CHAR(TRAVEL_APPDATE,'DD-MM-YYYY'),''),TRAVEL_ID " 
					+"  FROM HRMS_TRAVEL "
					+" INNER JOIN  HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TRAVEL.TRAVEL_EMPID) "
				    +" WHERE TRAVEL_CONFIRM_STATUS='"+confStatus+"' AND TRAVEL_CANCEL_ADMIN_STATUS ='"+cancelStatus+"'"
					+" AND  HRMS_EMP_OFFC.EMP_CENTER IN( SELECT TRAVEL_ADMIN_BRANCH_CODE  FROM HRMS_TRAVEL_ADMIN WHERE TRAVEL_ADMIN_EMP_ID ="+travelConf.getUserEmpId()+")"
					+" ORDER BY TRAVEL_APPDATE DESC ";
					  
		Object[][] userData = getSqlModel().getSingleResult(query);
		
		ArrayList<Object> arr =new ArrayList<Object>();	
		
		doPaging(travelConf, userData.length, new Object[][]{}, request);
		int fromTotRec = Integer.parseInt(travelConf.getFromTotRec());
		int toTotRec = Integer.parseInt(travelConf.getToTotRec());
		
		if(userData!=null)
		{
			for (int i = fromTotRec; i < toTotRec; i++) {
				
				TravelConfirmation travelCon =new TravelConfirmation();
				travelCon.setEmpId(checkNull(String.valueOf(userData[i][0])));
				travelCon.setTokenNo(checkNull(String.valueOf(userData[i][1])));
				travelCon.setEmpName(checkNull(String.valueOf(userData[i][2])));
				travelCon.setTravelDate(checkNull(String.valueOf(userData[i][3])));
				travelCon.setTravelID(checkNull(String.valueOf(userData[i][4])));
				arr.add(travelCon);		
			}
			travelConf.setTravelConfList(arr);
		} // end of if 
	//}
		 if(arr.size()==0)
		 {  
			 travelConf.setCancelNoData("true"); 
		 }else
		 { 
			 travelConf.setCancelNoData("false"); 
		 }
		
	} 
	
	
	
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	} // end of checkNull
	/*
	 *  This method is used for save the status.
	 */
	/**
	 * @param travelConf
	 * @param travelId
	 */
	public boolean save(TravelConfirmation travelConf, String travelId) {
		
			String updateQuery ="UPDATE HRMS_TRAVEL SET TRAVEL_CONFIRM_STATUS='C' WHERE TRAVEL_ID="+travelId;  
			boolean result =  getSqlModel().singleExecute(updateQuery);
			// send mail for second approver 
			if(result)
			{
			 try {
				  String schSql =" SELECT TRAVEL_ADMIN_EMP_ID  FROM HRMS_TRAVEL_ADMIN WHERE  TRAVEL_ADMIN_BRANCH_CODE =" +
				  		  " (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID  = "+travelConf.getUserEmpId()+")"; 
				    Object[][] schData = getSqlModel().getSingleResult(schSql);
					Object[][] to_mailIds =new Object[1][1];	
					Object[][] from_mailIds =new Object[1][1];	  
					from_mailIds[0][0]= travelConf.getUserEmpId();  
					
					if(schData!= null && schData.length >0)
					{
						for(int k =0; k<schData.length ;k++)
						{
							to_mailIds[0][0]= schData[k][0];
							System.out.println("to_mailIds[0][0] >> "+to_mailIds[0][0]);
							MailUtility mail=new MailUtility();
							mail.initiate(context, session);
							mail.sendMail(to_mailIds, from_mailIds,"Travel", "", "C"); 
							mail.terminate();
						}
					 }
					 
				} catch (Exception e) {
					// TODO Auto-generated catch block  
				}  
			}
				return result;
	}  
	
	public boolean travelCancel(TravelConfirmation travelConf, String travelId) {
		
		String updateQuery ="UPDATE HRMS_TRAVEL SET TRAVEL_CONFIRM_STATUS='N' WHERE TRAVEL_ID="+travelId;
		
		boolean result =  getSqlModel().singleExecute(updateQuery);
		
		// send mail for second approver 
		if(result)
		{
		 try {
			  String schSql =" SELECT TRAVEL_ADMIN_EMP_ID  FROM HRMS_TRAVEL_ADMIN WHERE  TRAVEL_ADMIN_BRANCH_CODE =" +
			  		  " (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID  = "+travelConf.getUserEmpId()+")"; 
			    Object[][] schData = getSqlModel().getSingleResult(schSql);
				Object[][] to_mailIds =new Object[1][1];	
				Object[][] from_mailIds =new Object[1][1];	
				 
			 	to_mailIds[0][0]= schData[0][0];
				from_mailIds[0][0]= travelConf.getUserEmpId();
				MailUtility mail=new MailUtility();
				mail.initiate(context, session);
				mail.sendMail(to_mailIds, from_mailIds,"Travel", "", "N"); 
				mail.terminate();
				 
			} catch (Exception e) {
				// TODO Auto-generated catch block  
			}  
		}
			return result;
      }  	
	      // end of save
	
	public void doPaging(TravelConfirmation bean, int empLength, Object[][] attnObj, HttpServletRequest request)
	{
		try
		{
			/*
			 * totalRec -: No. of records per page
			 * fromTotRec -: Starting No. of record to show on a current page
			 * toTotRec -: Last No. of record to show on a current page
			 * pageNo -: Current page to show
			 * totalPage -: Total No. of Pages
			*/
			
			String pagingSql = " SELECT CONF_RECORDS_PER_PAGE FROM HRMS_SALARY_CONF ";
			Object[][] pagingObj = getSqlModel().getSingleResult(pagingSql);
			int totalRec = Integer.parseInt(String.valueOf(pagingObj[0][0]));
			
			int pageNo = 1;
			int fromTotRec = 0;
			int toTotRec = totalRec;
			int searchCount = 0;
			int totalPage = 0;
			String empExists = "false";

			java.math.BigDecimal bigDecRow1 = new java.math.BigDecimal((double)empLength / totalRec);
			totalPage = Integer.parseInt(bigDecRow1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
			
			if(String.valueOf(bean.getHdPage()).equals("null")||String.valueOf(bean.getHdPage()).equals(null)||String.valueOf(bean.getHdPage()).equals(""))
			{
				pageNo = 1;
				fromTotRec = 0;
				toTotRec = totalRec;

				if(toTotRec > empLength)
				{
					toTotRec = empLength;
				}
				bean.setHdPage("1");
			}
			else
			{   	pageNo = Integer.parseInt(bean.getHdPage());
						  
					if(pageNo == 1)
					{
						fromTotRec = 0;
						toTotRec = totalRec;
					}
					else
					{
						toTotRec = toTotRec * pageNo;
						fromTotRec = toTotRec - totalRec;
					}
					if(toTotRec > empLength)
					{
						toTotRec = empLength;
					}
								
			}
			
			bean.setFromTotRec(String.valueOf(fromTotRec));
			bean.setToTotRec(String.valueOf(toTotRec));
			
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("fromTotRec", fromTotRec);
			request.setAttribute("toTotRec", toTotRec);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
