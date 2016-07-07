/**
 * Balaji
 * 08-08-2008
**/
package org.paradyne.model.TravelManagement;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.TravelManagement.TravelApplication;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator; 
 
  
public class TravelApplicationModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	/*
	 *  This method is used for save the Travel details.
	 */
	/**
	 * @param bean
	 * @param request
	 * @param empFlow
	 * @return boolean
	 */
	public boolean save(TravelApplication bean,HttpServletRequest request,Object[][] empFlow)
	{ 
		Object [] fromPlaceId = request.getParameterValues("itFromPlaceId");
		Object [] toPlaceId = request.getParameterValues("itToPlaceId");
		Object [] journeyClassId = request.getParameterValues("itJourneyClassId");
		Object [] journeyId = request.getParameterValues("itJourneyId");
		Object [] journeyDate = request.getParameterValues("itJourneyDate");
		Object [] journeyTime = request.getParameterValues("itJourneyTime"); 
		Object [] JourneyModeRefNumber = request.getParameterValues("itJourneyModeRefNumber"); 
		
		Object[][] addObj = new Object[1][7];
		addObj [0][0] = bean.getEmpId() ;
		addObj [0][1] = bean.getAppDate() ;		
		addObj [0][2] =String.valueOf(empFlow[0][0]);
		addObj [0][3] =bean.getApplicantComment();
		addObj [0][4] =String.valueOf(empFlow[0][3]); 
		addObj [0][5] =bean.getIdProof();
		addObj [0][6] =bean.getIdNumber();
	    boolean result = getSqlModel().singleExecute(getQuery(1), addObj);
	    
	    for(int i=0; i<fromPlaceId.length;i++)
    	{       	Object addItem[][] = new Object[1][10];
  					addItem[0][0] =String.valueOf(fromPlaceId[i]);
  					addItem[0][1] =String.valueOf(toPlaceId[i]);
  					addItem[0][2] =String.valueOf(journeyId[i]);
  					addItem[0][3] =String.valueOf(journeyClassId[i]);  				 
  			        addItem[0][4] =String.valueOf(journeyDate[i]);
  					addItem[0][5] =String.valueOf(journeyTime[i]);
  					addItem[0][6] =String.valueOf(journeyId[i]);
  					addItem[0][7] =String.valueOf(journeyClassId[i]);  	
  					addItem[0][8] =String.valueOf(JourneyModeRefNumber[i]);
  					addItem[0][9] =String.valueOf(JourneyModeRefNumber[i]);
  					result =  getSqlModel().singleExecute(getQuery(2),addItem);	 
    	}   
	    
	    if (result) {
			try {
				Object[][] to_mailIds =new Object[1][1];	
				Object[][] from_mailIds =new Object[1][1];	
				logger.info("Approver ID"+String.valueOf(empFlow[0][0]));
			 	to_mailIds[0][0]=String.valueOf(empFlow[0][0]);
				from_mailIds[0][0]=bean.getEmpId(); 
				MailUtility mail=new MailUtility();
				mail.initiate(context, session);
				mail.sendMail(to_mailIds, from_mailIds,"Travel", "", "P"); 
				mail.terminate();
				return result;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//logger.error(e.getMessage());
				 
				return true;
			} 
	    } // end of if
	    
		return result;
	} // end of save
	
	/*
	 *  This method is used for update the Travel details.
	 */
	/**
	 * @param bean
	 * @param request
	 * @param empFlow
	 * @return bpplean
	 */
	public boolean update(TravelApplication bean,HttpServletRequest request,Object[][] empFlow)
	{ 
		boolean flag=false;
		Object [] fromPlaceId = request.getParameterValues("itFromPlaceId");
		Object [] toPlaceId = request.getParameterValues("itToPlaceId");
		Object [] journeyClassId = request.getParameterValues("itJourneyClassId");
		Object [] journeyId = request.getParameterValues("itJourneyId");
		Object [] journeyDate = request.getParameterValues("itJourneyDate");
		Object [] journeyTime = request.getParameterValues("itJourneyTime");  
		Object [] JourneyModeRefNumber = request.getParameterValues("itJourneyModeRefNumber"); 
		
		Object[][] addObj = new Object[1][7];
		addObj [0][0] = bean.getEmpId() ; 
		addObj [0][1] =String.valueOf(empFlow[0][0]);
		addObj [0][2] =bean.getApplicantComment();
		addObj [0][3] =String.valueOf(empFlow[0][3]); 
		addObj [0][4] =bean.getIdProof();
		addObj [0][5] =bean.getIdNumber(); 
		addObj [0][6] = bean.getTravelAppId();
		
	    boolean result = getSqlModel().singleExecute(getQuery(3), addObj);
	    
	    if(fromPlaceId!=null)
	  	{
	  	   String query = " DELETE FROM  HRMS_TRAVEL_DTL WHERE TRAVELDTL_TRAVEL_ID = " +bean.getTravelAppId();	  	
	       flag = getSqlModel().singleExecute(query);
	  	}
	    if(flag)
	    {
		    for(int i=0; i<fromPlaceId.length;i++)
	    	{       	Object addItem[][] = new Object[1][11];
	    	            addItem[0][0] =bean.getTravelAppId();
	  					addItem[0][1] =String.valueOf(fromPlaceId[i]);
	  					addItem[0][2] =String.valueOf(toPlaceId[i]);
	  					addItem[0][3] =String.valueOf(journeyId[i]);
	  					addItem[0][4] =String.valueOf(journeyClassId[i]);  				 
	  			        addItem[0][5] =String.valueOf(journeyDate[i]);
	  					addItem[0][6] =String.valueOf(journeyTime[i]);
	  					addItem[0][7] =String.valueOf(journeyId[i]);
	  					addItem[0][8] =String.valueOf(journeyClassId[i]);  
	  					addItem[0][9] =String.valueOf(JourneyModeRefNumber[i]);  
	  					addItem[0][10] =String.valueOf(JourneyModeRefNumber[i]);  
	  					result =  getSqlModel().singleExecute(getQuery(4),addItem);	 
	    	}   
	    }   // end of if
		return result;
	}   // end of update
	
	/*
	 *  This method is used for delete the travel application.
	 */
	/**
	 * @param bean
	 * @return boolean
	 */
	public boolean deleteApp(TravelApplication bean)
	{
		  String Sqlmail = " SELECT TRAVEL_APPROVER FROM HRMS_TRAVEL WHERE TRAVEL_ID = " +bean.getTravelAppId();	  	
	      Object[][] appData = getSqlModel().getSingleResult(Sqlmail);
	       
	    	   String query = " DELETE FROM  HRMS_TRAVEL_DTL WHERE TRAVELDTL_TRAVEL_ID = " +bean.getTravelAppId();	  	
	    	   boolean  result = getSqlModel().singleExecute(query);
	      
	   if(result)
       {
	       String Sql = " DELETE FROM  HRMS_TRAVEL WHERE TRAVEL_ID = " +bean.getTravelAppId();	  	
	        result = getSqlModel().singleExecute(Sql);
        }
	   if(result)
	   { 
	      if(appData!= null && appData.length > 0)
	      {
	    	  System.out.println("in mail bean.getEmpId() "+bean.getEmpId());
	    	  try {
					Object[][] to_mailIds =new Object[1][1];	
					Object[][] from_mailIds =new Object[1][1];	 
				 	to_mailIds[0][0]=String.valueOf(appData[0][0]);
					from_mailIds[0][0]=bean.getEmpId(); 
					MailUtility mail=new MailUtility();
					mail.initiate(context, session);
					mail.sendMail(to_mailIds, from_mailIds,"Travel", "", "D"); 
					mail.terminate();
					return result;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//logger.error(e.getMessage());
					 
					return true;
				}  
	      }
	        
	   }
		return result;
	}   // end of deleteApp
	/*
	 *  This method is used for add the travel details in arraylist
	 */
	 /**
	 * @param bean
	 * @param request
	 */
	public void addIterator(TravelApplication bean,HttpServletRequest request)
	 {
		 
			Object [] fromPlaceName = request.getParameterValues("itFromPlaceName");
			Object [] toPlaceName = request.getParameterValues("itToPlaceName");
			Object [] journeyName = request.getParameterValues("itJourneyName");
			Object [] journeyClassName = request.getParameterValues("itJourneyClassName");
			Object [] fromPlaceId = request.getParameterValues("itFromPlaceId");
			Object [] toPlaceId = request.getParameterValues("itToPlaceId");
			Object [] journeyClassId = request.getParameterValues("itJourneyClassId");
			Object [] journeyId = request.getParameterValues("itJourneyId");
			Object [] journeyDate = request.getParameterValues("itJourneyDate");
			Object [] journeyTime = request.getParameterValues("itJourneyTime"); 
			Object [] JourneyModeRefNumber = request.getParameterValues("itJourneyModeRefNumber"); 
			 
			ArrayList<Object> jList = new ArrayList<Object>(); 
			        if(fromPlaceName!=null)
			        {  
			        	for(int i=0; i<fromPlaceName.length+1;i++)
			        	{ 
			        		if(i<fromPlaceName.length)
			        		{ 
			        			TravelApplication bean1 = new TravelApplication();   //for previus item
			  		        	bean1.setItFromPlaceName(""+fromPlaceName[i]);
			  		        	bean1.setItFromPlaceId(""+fromPlaceId[i]);
			  		        	bean1.setItToPlaceName(""+toPlaceName[i]);
			  		        	bean1.setItToPlaceId(""+toPlaceId[i]);
			  		        	bean1.setItJourneyName(""+journeyName[i]);
			  		        	bean1.setItJourneyId(""+journeyId[i]);
			  		        	bean1.setItJourneyClassName(""+journeyClassName[i]);
			  		        	bean1.setItJourneyClassId(""+journeyClassId[i]);
			  		        	bean1.setItJourneyDate(""+journeyDate[i]);
			  		        	bean1.setItJourneyTime(""+journeyTime[i]);
			  		        	bean1.setItJourneyModeRefNumber(""+JourneyModeRefNumber[i]);
			  		          	jList.add(bean1);
			  		        	bean.setJourneyList(jList); 
			        		}else
			        		{
			        		    TravelApplication bean1 = new TravelApplication();   //Last time added in list
			  		        	bean1.setItFromPlaceName(bean.getFromPlace());
			  		        	bean1.setItFromPlaceId(bean.getFromPlaceId());
			  		        	bean1.setItToPlaceName(bean.getToPlace());
			  		        	bean1.setItToPlaceId(bean.getToPlaceId());
			  		        	bean1.setItJourneyName(bean.getJourneyName());
			  		        	bean1.setItJourneyId(bean.getJourneyId());
			  		        	bean1.setItJourneyClassName(bean.getJourneyClassName());
			  		        	bean1.setItJourneyClassId(bean.getJourneyClassId());
			  		        	bean1.setItJourneyDate(bean.getJourneyDate());
			  		        	bean1.setItJourneyTime(bean.getJourneyTime());
			  		        	bean1.setItJourneyModeRefNumber(""+bean.getJourneyModeRefNumber());
			  		        	 jList.add(bean1);
			  		        	bean.setJourneyList(jList); 
			        		}
			        	}
			        	
			        }else
			        {
			            TravelApplication bean1 = new TravelApplication();   //first time added in list
			        	bean1.setItFromPlaceName(bean.getFromPlace());
			        	bean1.setItFromPlaceId(bean.getFromPlaceId());
			        	bean1.setItToPlaceName(bean.getToPlace());
			        	bean1.setItToPlaceId(bean.getToPlaceId());
			        	bean1.setItJourneyName(bean.getJourneyName());
			        	bean1.setItJourneyId(bean.getJourneyId());
			        	bean1.setItJourneyClassName(bean.getJourneyClassName());
			        	bean1.setItJourneyClassId(bean.getJourneyClassId());
			        	bean1.setItJourneyDate(bean.getJourneyDate());
			        	bean1.setItJourneyTime(bean.getJourneyTime());
			        	bean1.setItJourneyModeRefNumber(bean.getJourneyModeRefNumber()); 
			        	jList.add(bean1);
			        	bean.setJourneyList(jList); 
			        }   // end of else
	 } // end of addIterator
	
	/*
	 *  This method is used for update the travel details in arraylist
	 */
	 /**
	 * @param bean
	 * @param request
	 */
	 public void editIterator(TravelApplication bean,HttpServletRequest request)
	 {
		 
			Object [] fromPlaceName = request.getParameterValues("itFromPlaceName");
			Object [] toPlaceName = request.getParameterValues("itToPlaceName");
			Object [] journeyName = request.getParameterValues("itJourneyName");
			Object [] journeyClassName = request.getParameterValues("itJourneyClassName");
			Object [] fromPlaceId = request.getParameterValues("itFromPlaceId");
			Object [] toPlaceId = request.getParameterValues("itToPlaceId");
			Object [] journeyClassId = request.getParameterValues("itJourneyClassId");
			Object [] journeyId = request.getParameterValues("itJourneyId");
			Object [] journeyDate = request.getParameterValues("itJourneyDate");
			Object [] journeyTime = request.getParameterValues("itJourneyTime"); 
			Object [] JourneyModeRefNumber = request.getParameterValues("itJourneyModeRefNumber"); 
			ArrayList<Object> jList = new ArrayList<Object>(); 
			        if(fromPlaceName!=null)
			        {
						logger.info("fromPlaceName llllll============ "+fromPlaceName.length);
			        	for(int i=0; i<fromPlaceName.length;i++)
			        	{
				        		 TravelApplication bean1 = new TravelApplication(); 
				        		if(i==Integer.parseInt(bean.getCheckEdit()))
				        		{
				        			      //Edit the Item
					  		        	bean1.setItFromPlaceName(bean.getFromPlace());
					  		        	bean1.setItFromPlaceId(bean.getFromPlaceId());
					  		        	bean1.setItToPlaceName(bean.getToPlace());
					  		        	bean1.setItToPlaceId(bean.getToPlaceId());
					  		        	bean1.setItJourneyName(bean.getJourneyName());
					  		        	bean1.setItJourneyId(bean.getJourneyId());
					  		        	bean1.setItJourneyClassName(bean.getJourneyClassName());
					  		        	bean1.setItJourneyClassId(bean.getJourneyClassId());
					  		        	bean1.setItJourneyDate(bean.getJourneyDate());
					  		        	bean1.setItJourneyTime(bean.getJourneyTime());
					  		        	bean1.setItJourneyModeRefNumber(bean.getJourneyModeRefNumber());					  		        	
					  		        	jList.add(bean1);
					  		        	bean.setJourneyList(jList); 
				        		}
				        		else
				        		{     //for previus item
				  		        	bean1.setItFromPlaceName(""+fromPlaceName[i]);
				  		        	bean1.setItFromPlaceId(""+fromPlaceId[i]);
				  		        	bean1.setItToPlaceName(""+toPlaceName[i]);
				  		        	bean1.setItToPlaceId(""+toPlaceId[i]);
				  		        	bean1.setItJourneyName(""+journeyName[i]);
				  		        	bean1.setItJourneyId(""+journeyId[i]);
				  		        	bean1.setItJourneyClassName(""+journeyClassName[i]);
				  		        	bean1.setItJourneyClassId(""+journeyClassId[i]);
				  		        	bean1.setItJourneyDate(""+journeyDate[i]);
				  		        	bean1.setItJourneyTime(""+journeyTime[i]);
				  		        	bean1.setItJourneyModeRefNumber(""+JourneyModeRefNumber[i]);
				  		        	jList.add(bean1);
				  		        	bean.setJourneyList(jList); 
				        		} 
			        	} 
			        }  
	     } // end of editIterator
	 
		/*
		 *  This method is used for delete the travel details from arraylist
		 */
		 /**
		 * @param bean
		 * @param request
		 */
	 public void delItem(TravelApplication bean,HttpServletRequest request)
	 {
		 
		 logger.info("INSIDE DELITEM.....");
		 Object [] fromPlaceName = request.getParameterValues("itFromPlaceName");
			Object [] toPlaceName = request.getParameterValues("itToPlaceName");
			Object [] journeyName = request.getParameterValues("itJourneyName");
			Object [] journeyClassName = request.getParameterValues("itJourneyClassName");
			Object [] fromPlaceId = request.getParameterValues("itFromPlaceId");
			Object [] toPlaceId = request.getParameterValues("itToPlaceId");
			Object [] journeyClassId = request.getParameterValues("itJourneyClassId");
			Object [] journeyId = request.getParameterValues("itJourneyId");
			Object [] journeyDate = request.getParameterValues("itJourneyDate");
			Object [] journeyTime = request.getParameterValues("itJourneyTime"); 
			String serialNo = request.getParameter("serialNo");
			Object [] JourneyModeRefNumber = request.getParameterValues("itJourneyModeRefNumber"); 
			ArrayList<Object> jList = new ArrayList<Object>(); 
			        if(fromPlaceName!=null)
			        {
						logger.info("fromPlaceName llllll============BALAJI "+fromPlaceName.length);
			        	for(int i=0; i<fromPlaceName.length;i++)
			        	{
			        		
			        				logger.info("INSIDE DELITEM....."+i);
			        		
				        		    TravelApplication bean1 = new TravelApplication();    //for previus item
				  		        	bean1.setItFromPlaceName(""+fromPlaceName[i]);
				  		        	bean1.setItFromPlaceId(""+fromPlaceId[i]);
				  		        	bean1.setItToPlaceName(""+toPlaceName[i]);
				  		        	bean1.setItToPlaceId(""+toPlaceId[i]);
				  		        	bean1.setItJourneyName(""+journeyName[i]);
				  		        	bean1.setItJourneyId(""+journeyId[i]);
				  		        	bean1.setItJourneyClassName(""+journeyClassName[i]);
				  		        	bean1.setItJourneyClassId(""+journeyClassId[i]);
				  		        	bean1.setItJourneyDate(""+journeyDate[i]);
				  		        	bean1.setItJourneyTime(""+journeyTime[i]);  
				  		        	bean1.setItJourneyModeRefNumber(""+JourneyModeRefNumber[i]);
				  		        	jList.add(bean1);
			        	} 
			        	logger.info("serialNo===== by getPara=>>>>>>>>>  "+serialNo);
			        	logger.info("serialNo===== by getPara=>>>>>>>>>  "+bean.getSerialNo());
			        	jList.remove(Integer.parseInt(bean.getSerialNo()));
			        	
			        	logger.info("ARRAY LIST SIZE: "+jList.size());
	  		        	bean.setJourneyList(jList);  
			        	
			        }  // end of if
	 } // end of delItem
	 
		/*
		 *  This method is used for collect the data from travel details  
		 */
		 /**
		 * @param bean
		 
		 */
	 public void callDtl(TravelApplication bean)
	 {
		 try
		 {
		 String sqlQuery =" SELECT  nvl(TRAVEL_APPLICANT_COMMENT,' '),TRAVEL_ID_PROOF, nvl(TRAVEL_PROOF_NO,' ') FROM HRMS_TRAVEL WHERE  TRAVEL_ID ="+bean.getTravelAppId();;
		 Object [][] cData = getSqlModel().getSingleResult(sqlQuery);
		 if(cData.length >0 && cData != null)
		 {
			 bean.setApplicantComment(""+cData[0][0]); 
			 bean.setIdProof(""+cData[0][1]); 
			 bean.setIdNumber(""+cData[0][2]); 
		 }
		 }catch (Exception e) {
			// TODO: handle exception
		}
		 
		 
		 bean.setStatus(bean.getHidStatus());
		 if(bean.getTrHidSchStatus().equals("S"))
		 {
			 String sqlStatus =" SELECT TRAVEL_CONFIRM_STATUS FROM HRMS_TRAVEL WHERE TRAVEL_ID ="+bean.getTravelAppId();
			 Object [][] statusData = getSqlModel().getSingleResult(sqlStatus);
			 if(statusData.length >0 && statusData != null)
			 {
				 bean.setStatus(""+statusData[0][0]); 
			 }
		 }  // end of outer loop.
		 
		String sql ="  SELECT  L1.LOCATION_NAME,L2.LOCATION_NAME, JOURNEY_NAME,JOURNEY_CLASS_NAME, "
				    +"  TO_CHAR(TRAVELDTL_JOURNEY_DATE,'DD-MM-YYYY'), NVL(TRAVELDTL_JOURNEY_TIME,' '), TRAVELDTL_FROM_PLACE, "
					+"  TRAVELDTL_TO_PLACE ,TRAVELDTL_JOURNEY_MODE, TRAVELDTL_JOURNEY_CLASS,NVL(TRAVELDTL_JOURNEY_MODEREF_NUM,' ') FROM HRMS_TRAVEL_DTL  "
					+"  INNER JOIN HRMS_LOCATION L1  ON(HRMS_TRAVEL_DTL.TRAVELDTL_FROM_PLACE = L1.LOCATION_CODE) "
					+"  INNER JOIN HRMS_LOCATION L2  ON(HRMS_TRAVEL_DTL.TRAVELDTL_TO_PLACE = L2.LOCATION_CODE) "
					+"  LEFT JOIN HRMS_JOURNEY ON(HRMS_TRAVEL_DTL.TRAVELDTL_JOURNEY_MODE =HRMS_JOURNEY.JOURNEY_ID) "
					+"  INNER JOIN HRMS_JOURNEY_CLASS ON(HRMS_TRAVEL_DTL.TRAVELDTL_JOURNEY_CLASS = HRMS_JOURNEY_CLASS.JOURNEY_CLASS_ID) "
					+"  WHERE TRAVELDTL_TRAVEL_ID ="+bean.getTravelAppId()+"ORDER BY TRAVELDTL_TRAVEL_ID ";
		     Object [][] data = getSqlModel().getSingleResult(sql);
		
		    ArrayList<Object> jList = new ArrayList<Object>();   
		    if(data.length >0 && data != null)
		    {
        	   for(int i=0; i<data.length;i++)
        	      {
	        		    TravelApplication bean1 = new TravelApplication();    //for previus item
	  		        	bean1.setItFromPlaceName(""+data[i][0]);
	  		        	bean1.setItToPlaceName(""+data[i][1]);
	  		        	bean1.setItJourneyName(""+data[i][2]);
	  		        	bean1.setItJourneyClassName(""+data[i][3]);
	  		        	bean1.setItJourneyDate(""+data[i][4]);
	  		        	bean1.setItJourneyTime(""+data[i][5]); 
	  		        	bean1.setItFromPlaceId(""+data[i][6]); 
	  		        	bean1.setItToPlaceId(""+data[i][7]); 
	  		        	bean1.setItJourneyId(""+data[i][8]); 
	  		        	bean1.setItJourneyClassId(""+data[i][9]);
	  		        	bean1.setItJourneyModeRefNumber(""+data[i][10]); 
	  		        	jList.add(bean1);
        	      } 
        	     bean.setJourneyList(jList);   
		    } // end of if
	 }  // end of callDtl
	     /*
		 *  This method is used for collect the data for the employee.
		 */
		/**
		 * @param empId
		 * @param bean
		 */
		public void getEmployeeDetails(String empId,TravelApplication bean)
		{
       try
       {
	    String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
		 +"	CENTER_NAME,DEPT_NAME,RANK_NAME,EMP_ID ,CADRE_NAME,EMP_CADRE  FROM HRMS_EMP_OFFC "
		 +"	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
		 +"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
		 +"	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
		 +"	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
		 +" LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) " 
		 +" WHERE EMP_ID = "+empId;
			Object[][] empdata = getSqlModel().getSingleResult(query);
			bean.setEmpToken(String.valueOf(empdata[0][0]));
			bean.setEmpName(String.valueOf(empdata[0][1]));
			bean.setBranchName(String.valueOf(empdata[0][2]));
			bean.setDept(String.valueOf(empdata[0][3]));
			bean.setDesg(String.valueOf(empdata[0][4]));
			bean.setEmpId(empId);
			bean.setGradeName(String.valueOf(empdata[0][6]));
			bean.setGradeCode(String.valueOf(empdata[0][7]));
			bean.setContactNo(String.valueOf(empdata[0][8]));
			bean.setEmpId(empId);
       }catch (Exception e) {
		logger.info("in exception");
	}
		}  //end of getEmployeeDetails
	 
	 
	  //This method is used for checking the status
		/**
		   * @param bean
		 */
		public boolean checkForward(TravelApplication bean)
		{ 
			  String sql =" SELECT TRAVEL_STATUS FROM HRMS_TRAVEL WHERE  TRAVEL_STATUS = 'P' AND TRAVEL_LEVEL = 1 AND TRAVEL_ID =" +bean.getTravelAppId();
			   Object [][] result  = getSqlModel().getSingleResult(sql);
				if(result.length>0)
				{
					return true;
				}else
				   return false;
		} //end of checkForward
		
	 
		 /*
		 *  This method is used for generate the report.
		 */
		/**
		 * @param request
		 * @param response
		 * @param bean
		 */
		public void getReport(HttpServletRequest request, HttpServletResponse response, TravelApplication bean) {
			
			String sqlQuery ="SELECT CASE WHEN TRAVEL_SCHEDULE_STATUS ='S' THEN DECODE(TRAVEL_CONFIRM_STATUS,'D','Schedule','C','Confirm','N','Cancel In Process','K','Cancel') ELSE  DECODE(TRAVEL_STATUS,'P','Pending','A','Approved','R','Rejected') END FROM HRMS_TRAVEL WHERE TRAVEL_ID =" +bean.getTravelAppId();
			   Object [][] statusRes  = getSqlModel().getSingleResult(sqlQuery);
			   
			String s = "\n Travel Application Report \n\n";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",	s,"A3");
			 
			
			String query  = "  SELECT ROWNUM, L1.LOCATION_NAME,L2.LOCATION_NAME, JOURNEY_NAME,JOURNEY_CLASS_NAME, NVL(TRAVELDTL_JOURNEY_MODEREF_NUM,' '),"
				    +"  TO_CHAR(TRAVELDTL_JOURNEY_DATE,'DD-MM-YYYY'), TRAVELDTL_JOURNEY_TIME, TRAVELDTL_FROM_PLACE, "
					+"  TRAVELDTL_TO_PLACE ,TRAVELDTL_JOURNEY_MODE, TRAVELDTL_JOURNEY_CLASS  FROM HRMS_TRAVEL_DTL  "
					+"  INNER JOIN HRMS_LOCATION L1  ON(HRMS_TRAVEL_DTL.TRAVELDTL_FROM_PLACE = L1.LOCATION_CODE) "
					+"  INNER JOIN HRMS_LOCATION L2  ON(HRMS_TRAVEL_DTL.TRAVELDTL_TO_PLACE = L2.LOCATION_CODE) "
					+"  LEFT JOIN HRMS_JOURNEY ON(HRMS_TRAVEL_DTL.TRAVELDTL_JOURNEY_MODE =HRMS_JOURNEY.JOURNEY_ID) "
					+"  INNER JOIN HRMS_JOURNEY_CLASS ON(HRMS_TRAVEL_DTL.TRAVELDTL_JOURNEY_CLASS = HRMS_JOURNEY_CLASS.JOURNEY_CLASS_ID) "
					+"  WHERE TRAVELDTL_TRAVEL_ID ="+bean.getTravelAppId();
	            Object [][] result = getSqlModel().getSingleResult(query);
	            
	            String sqlSchedule = "SELECT ROWNUM, L1.LOCATION_NAME,L2.LOCATION_NAME, NVL(JOURNEY_NAME ,' ')AS JSNAME,NVL(JOURNEY_CLASS_NAME,' ') , "
					+"	TO_CHAR(TRAVELDTL_SCHD_DATE,'DD-MM-YYYY'), NVL(TRAVELDTL_SCHD_TIME,' '), NVL(TRAVELDTL_SCHD_MODEREF_NUMBER,' '), "
					+"	NVL( TRAVELDTL_SCHD_TICKET_NUMBER,' '), NVL(TRAVELDTL_SCHD_JOUR_COMMENT,' ') FROM HRMS_TRAVEL_DTL  "
					+"	LEFT JOIN HRMS_LOCATION L1  ON(HRMS_TRAVEL_DTL.TRAVELDTL_FROM_PLACE = L1.LOCATION_CODE) "	
					+"	LEFT JOIN HRMS_LOCATION L2  ON(HRMS_TRAVEL_DTL.TRAVELDTL_TO_PLACE = L2.LOCATION_CODE) " 
					+"	LEFT JOIN HRMS_JOURNEY  ON(HRMS_TRAVEL_DTL.TRAVELDTL_SCHD_JOUR_MODE =HRMS_JOURNEY.JOURNEY_ID) "
					+"	LEFT JOIN HRMS_JOURNEY_CLASS   ON(HRMS_TRAVEL_DTL.TRAVELDTL_SCHD_JOUR_CLASS = HRMS_JOURNEY_CLASS .JOURNEY_CLASS_ID) "
	        		+"  WHERE TRAVELDTL_TRAVEL_ID ="+bean.getTravelAppId(); 
	              Object [][] schdata = getSqlModel().getSingleResult(sqlSchedule);
	              
	              String sqlHotel =" SELECT ROWNUM, NVL(TRAVEL_HOTEL_NAME,' '), NVL(TRAVEL_HOTEL_ADDRESS,' '), TO_CHAR(TRAVEL_HOTEL_CHKIN_DATE,'DD-MM-YYYY'), "
								+"	NVL(TRAVEL_HOTEL_CHKIN_TIME,' '),  TO_CHAR(TRAVEL_HOTEL_CHKOUT_DATE,'DD-MM-YYYY'),NVL(TRAVEL_HOTEL_CHKOUT_TIME,' '), "
								+"	NVL(TRAVEL_HOTEL_COMMENT,' ') FROM HRMS_TRAVEL_HOTEL "
								+"	WHERE TRAVEL_HOTEL_TRAVEL_ID ="+bean.getTravelAppId(); 
	              Object [][] hotelData = getSqlModel().getSingleResult(sqlHotel);
		 
			 

		 	String approver = " SELECT EMP_TOKEN,(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' ') AS EMP ," 
		 			       +" TO_CHAR(APPR_DATE,'DD-MM-YYYY'),TRAVEL_REMARK  AS REMARK,DECODE(TRAVEL_STATUS,'P','Pending','A'," 
		 			       +"'Approved','R','Rejected')  FROM HRMS_TRAVEL_PATH   "
		 			       +" INNER JOIN HRMS_EMP_OFFC ON (HRMS_TRAVEL_PATH.APPROVER_CODE= HRMS_EMP_OFFC.EMP_ID)   "
		 			       +"  LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)	 WHERE TRAVEL_ID = "+bean.getTravelAppId()    
		 			       +"  UNION  "
		 			       +"  SELECT EMP_TOKEN,(HRMS_TITLE.TITLE_NAME||' '||EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '),'',cast(''AS NVARCHAR2(100)) a,  "
		 			       +"  DECODE(TRAVEL_STATUS,'P','Pending','A','Approved','R','Rejected') FROM  HRMS_TRAVEL  "  
		 			       +"  INNER JOIN HRMS_EMP_OFFC ON (HRMS_TRAVEL.TRAVEL_APPROVER= HRMS_EMP_OFFC.EMP_ID)  "
		 			       +"  INNER JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE) "
		 			       +"  WHERE TRAVEL_STATUS ='P' AND TRAVEL_ID = "+bean.getTravelAppId();
		 	

			Object[][] approverData = getSqlModel().getSingleResult(approver);
	          int j =1;
			Object[][] approvalTable = new Object[approverData.length][7];
			for (int i = 0; i < approverData.length; i++) {
				approvalTable[i][0] = String.valueOf(j++);
				approvalTable[i][1] = checkNull(String.valueOf(approverData[i][0]));
				approvalTable[i][2] = checkNull(String.valueOf(approverData[i][1]));
				approvalTable[i][3] = checkNull(String.valueOf(approverData[i][2]));
				approvalTable[i][4] = checkNull(String.valueOf(approverData[i][3]));
				approvalTable[i][5] = checkNull(String.valueOf(approverData[i][4]));
				approvalTable[i][6] = "";
			} 

			//Object tab[][] = getSqlModel().getSingleResult(sql);

			Object data[][] = new Object[4][4];		 

			data[0][0] = "Employee Id.";
			data[0][1] = ": " + bean.getEmpToken();
			data[0][2] = "Employee Name ";
			data[0][3] = ": " + bean.getEmpName();

			data[1][0] = " Branch ";
			data[1][1] = ": " + bean.getBranchName() ;
			data[1][2] = " Department ";
			data[1][3] = ": " +  bean.getDept();  

			data[2][0] = " Designation ";
			data[2][1] = ": " +bean.getDesg();
			data[2][2] = " Application Date ";
			data[2][3] = ": " + bean.getAppDate()  ;

			data[3][0] = " Status ";
			data[3][1] = ": " +String.valueOf(statusRes[0][0]);
			data[3][2] = " ";
			data[3][3] = " " ;

			int[] bcellWidth = { 6, 15, 7, 66 };
			int[] bcellAlign = { 0, 0, 0, 0 };
			rg.addFormatedText(s, 6, 0, 1, 0);

			rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
	 

			rg.addFormatedText("Travel Details : \n ", 6, 0, 0, 0);		 
			String colnames[] = { "Sr.No", "From Place ", "To Place", "Journey Mode","Journey Class","Bus/Train/Air Number" ,"Journey Date","Journey Time"};
			int cellwidth[] = { 5, 25, 25, 15, 15,20,15,10};
			int alignment[] = { 1, 0, 0, 0, 0 ,0,0,0 };
			rg.tableBody(colnames, result, cellwidth, alignment); 
			rg.addFormatedText("", 1, 0, 2, 3); 
			
			rg.addFormatedText("Travel Schedule Details : \n ", 6, 0, 0, 0);		 
			String colnamesSch[] = { "Sr.No", "From Place ", "To Place", "Journey Mode","Journey Class" ,"Journey Date","Journey Time","Bus/Train/Air Number","Ticket Number","Comment"};
			int cellwidthSch[] = { 5, 25, 25, 15, 15,15,15,20,15,45};
			int alignmentSch[] = { 1, 0, 0, 0, 0 , 0, 0,0,0,0 };
			rg.tableBody(colnamesSch, schdata, cellwidthSch, alignmentSch);
			rg.addFormatedText("", 1, 0, 2, 3);  
			
			rg.addFormatedText("Hotel Details : \n ", 6, 0, 0, 0);		 
			String colnamesHotel[] = { "Sr.No", "Hotel Name", "Hotel Address", "Booking From Date","From Time" ,"To Date","To Time","Comments"};
			int cellwidthHotel[] = { 5, 25, 25, 15, 15,15,10,40};
			int alignmenHotel[] = { 1, 0, 0, 0, 0 , 0, 0 ,0}; 
			rg.tableBody(colnamesHotel, hotelData, cellwidthHotel, alignmenHotel);
			
			String appCol[] = { "Sr. No", "Approver Id", "Approver Name", "Date",
					"Remarks", "Status", "Signature" };
			int appCell[] = { 5, 10, 25, 10, 30, 10, 12 };
			int apprAlign[] = { 1, 1, 0, 1, 0, 0, 0 }; 
			
			
			 
			rg.addFormatedText("", 1, 0, 2, 3);
			 
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("Approver Details :", 6, 0, 0, 0);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);

	       	rg.tableBody(appCol, approvalTable, appCell, apprAlign);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("", 1, 0, 2, 3);
			rg.addFormatedText("__________________", 1, 0, 0, 3);
			rg.addFormatedText("", 1, 0, 0, 3);
			rg.addFormatedText("Employee Signature", 1, 0, 0, 3);

			 
			rg.createReport(response);

		}
		
		/**
		 * @param result
		 * @return String
		 */
		public String checkNull(String result) {
			 
			if (result == null || result.equals("null")) {
				return "";
			} else {
				return result;
			}
		} // end of checkNull
	
		 /*
		 *  This method is used for show the employee information for schedule
		 */
		
		/**
		 * @param bean
		 */
		public void travelScheduleEmpInfo(TravelApplication bean)
		{
			
			String query = " SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " 
			     +" TO_CHAR(TRAVEL_APPDATE,'DD-MM-YYYY'),CASE WHEN TRAVEL_SCHEDULE_STATUS ='S' THEN DECODE(TRAVEL_CONFIRM_STATUS,'D','Schedule','C','Confirm','N','Cancel In Process','K','Cancel') ELSE  DECODE(TRAVEL_STATUS,'P','Pending','A','Approved','R','Rejected') END , "	
				 +" CENTER_NAME,DEPT_NAME,RANK_NAME,EMP_ID,CASE WHEN TRAVEL_SCHEDULE_STATUS ='S' THEN TRAVEL_CONFIRM_STATUS ELSE TRAVEL_STATUS END,TRAVEL_ID,NVL(TRAVEL_APPLICANT_COMMENT,' ') , " 
				 + " CADRE_NAME,EMP_CADRE ,  " 
				 +" DECODE ( TRAVEL_ID_PROOF ,'V','Voter Identity Card ','P','Passport','I',' PAN Card','D','Driving Licence ', 'G','Photo identity cards issued by Central/State Government' ) "
				 +"  ,nvl(TRAVEL_PROOF_NO,' ')  FROM HRMS_TRAVEL  "  
				 +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_TRAVEL.TRAVEL_EMPID ) " 
				 +" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) " 
				 +" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) " 
				 +" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) " 
				 +" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
				 +" LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
				 +"  WHERE TRAVEL_ID ="+bean.getTravelAppId();
			
			  Object [][] result = getSqlModel().getSingleResult(query);
			  bean.setEmpToken(String.valueOf(result[0][0]));
			  bean.setEmpName(String.valueOf(result[0][1]));
			  bean.setAppDate(String.valueOf(result[0][2]));
			  bean.setStatus(String.valueOf(result[0][3])); 
			  bean.setBranchName(String.valueOf(result[0][4]));
			  bean.setDept(String.valueOf(result[0][5]));
			  bean.setDesg(String.valueOf(result[0][6]));
			  bean.setEmpId(String.valueOf(result[0][7]));
			  bean.setStatus(String.valueOf(result[0][8]));
			  bean.setTravelAppId(String.valueOf(result[0][9])); 
			  bean.setApplicantComment(String.valueOf(result[0][10])); 
			  bean.setGradeName(String.valueOf(result[0][11])); 
			  bean.setGradeCode(String.valueOf(result[0][12])); 
			  bean.setIdProof(String.valueOf(result[0][13])); 
			  bean.setIdNumber(String.valueOf(result[0][14])); 
		}   // end of travelScheduleEmpInfo
		
		/* public void callDtlSchedule(TravelApplication bean)
		 {
String sql =" SELECT  L1.LOCATION_NAME,L2.LOCATION_NAME, J1.JOURNEY_NAME,JC1.JOURNEY_CLASS_NAME,TO_CHAR(TRAVELDTL_JOURNEY_DATE,'DD-MM-YYYY'), " 
		+"	NVL(TRAVELDTL_JOURNEY_TIME,' '), TRAVELDTL_FROM_PLACE,   TRAVELDTL_TO_PLACE ,TRAVELDTL_JOURNEY_MODE,TRAVELDTL_JOURNEY_CLASS , "
		+"	NVL(J2.JOURNEY_NAME ,' ')AS JSNAME,NVL(JC2.JOURNEY_CLASS_NAME,' ') AS JSCLASS,TRAVELDTL_SCHD_JOUR_MODE,TRAVELDTL_SCHD_JOUR_CLASS, "
		+"	TO_CHAR(TRAVELDTL_SCHD_DATE,'DD-MM-YYYY'), NVL(TRAVELDTL_SCHD_TIME,' '), NVL(TRAVELDTL_SCHD_MODEREF_NUMBER,' '),NVL( TRAVELDTL_SCHD_TICKET_NUMBER,' '), "
		+"	NVL(TRAVELDTL_SCHD_JOUR_COST,0) FROM HRMS_TRAVEL_DTL  "
		+"	LEFT JOIN HRMS_LOCATION L1  ON(HRMS_TRAVEL_DTL.TRAVELDTL_FROM_PLACE = L1.LOCATION_CODE) "
		+"	LEFT JOIN HRMS_LOCATION L2  ON(HRMS_TRAVEL_DTL.TRAVELDTL_TO_PLACE = L2.LOCATION_CODE) "
		+"	LEFT JOIN HRMS_JOURNEY J1 ON(HRMS_TRAVEL_DTL.TRAVELDTL_JOURNEY_MODE =J1.JOURNEY_ID) "
		+"	LEFT JOIN HRMS_JOURNEY_CLASS JC1 ON(HRMS_TRAVEL_DTL.TRAVELDTL_JOURNEY_CLASS = JC1.JOURNEY_CLASS_ID) "
		+"	LEFT JOIN HRMS_JOURNEY J2 ON(HRMS_TRAVEL_DTL.TRAVELDTL_SCHD_JOUR_MODE =J2.JOURNEY_ID) "
		+"	LEFT JOIN HRMS_JOURNEY_CLASS JC2 ON(HRMS_TRAVEL_DTL.TRAVELDTL_SCHD_JOUR_CLASS = JC2.JOURNEY_CLASS_ID) "
		+"  WHERE TRAVELDTL_TRAVEL_ID ="+bean.getTravelAppId(); 
      Object [][] data = getSqlModel().getSingleResult(sql);
			
			    ArrayList<Object> jList = new ArrayList<Object>();   
			    if(data.length >0 && data != null)
			    {   int j =1;
	        	   for(int i=0; i<data.length;i++)
	        	      {
		        		    TravelApplication bean1 = new TravelApplication();    //for previus item
		        		    bean1.setItFromPlaceName(""+data[i][0]);
		  		        	bean1.setItToPlaceName(""+data[i][1]);
		  		        	bean1.setItJourneyName(""+data[i][2]);
		  		        	bean1.setItJourneyClassName(""+data[i][3]);
		  		        	bean1.setItJourneyDate(""+data[i][4]);
		  		        	bean1.setItJourneyTime(""+data[i][5]); 
		  		        	bean1.setItFromPlaceId(""+data[i][6]); 
		  		        	bean1.setItToPlaceId(""+data[i][7]); 
		  		        	bean1.setItJourneyId(""+data[i][8]); 
		  		        	bean1.setItJourneyClassId(""+data[i][9]); 
		  		        	 if(bean.getStatusSch().equals("S"))            // for schedule status
		        			 {      bean1.setSchViewFlag("true"); 
				  		        	bean1.setSchViewJourneyMode(""+data[i][10]); 
				  		        	bean1.setSchViewJourneyClass(""+data[i][11]);   
				  		        	bean1.setSchJourneyDate(""+data[i][14]); 
				  		        	bean1.setSchJourneyTime(""+data[i][15]); 
				  		        	bean1.setSchModeNumber(""+data[i][16]); 
				  		        	bean1.setSchTicketNumber(""+data[i][17]); 
				  		        	bean1.setSchJourneyCost(""+data[i][18]); 
		        			 }
		  		        
		  		        	
		  		        	
		  		        	jList.add(bean1);
	        	      } 
	        	     bean.setSchJourneyList(jList);   
			    }
		 }*/
		 /*
		 *  This method is used for collect the data for the cancel
		 */
		
		 /**
		 * @param bean
		 * @param request
		 */
		public void callDtlForCancel(TravelApplication bean,HttpServletRequest request)
		 {
String sql =" SELECT  L1.LOCATION_NAME,L2.LOCATION_NAME, J1.JOURNEY_NAME,JC1.JOURNEY_CLASS_NAME,TO_CHAR(TRAVELDTL_JOURNEY_DATE,'DD-MM-YYYY'), " 
		+"	NVL(TRAVELDTL_JOURNEY_TIME,' '), NVL(TRAVELDTL_FROM_PLACE,0),   NVL(TRAVELDTL_TO_PLACE ,0),NVL(TRAVELDTL_JOURNEY_MODE,0),NVL(TRAVELDTL_JOURNEY_CLASS,0) , "
		+"	NVL(J2.JOURNEY_NAME ,' ')AS JSNAME,NVL(JC2.JOURNEY_CLASS_NAME,' ') AS JSCLASS,NVL(TRAVELDTL_SCHD_JOUR_MODE,0),NVL(TRAVELDTL_SCHD_JOUR_CLASS,0), "
		+"	TO_CHAR(TRAVELDTL_SCHD_DATE,'DD-MM-YYYY'), NVL(TRAVELDTL_SCHD_TIME,''), NVL(TRAVELDTL_SCHD_MODEREF_NUMBER,' '),NVL( TRAVELDTL_SCHD_TICKET_NUMBER,' '), "
		+"	NVL(TRAVELDTL_SCHD_JOUR_COST,0),NVL(TRAVELDTL_SCHD_JOUR_EXTAMT,0), NVL(TRAVELDTL_SCHD_JOUR_COMMENT,' '),TRAVELDTL_ID,NVL(TRAVELDTL_SCHD_CANCEL_AMT,0)"  
		+"  FROM HRMS_TRAVEL_DTL  "
		+"	LEFT JOIN HRMS_LOCATION L1  ON(HRMS_TRAVEL_DTL.TRAVELDTL_FROM_PLACE = L1.LOCATION_CODE) "
		+"	LEFT JOIN HRMS_LOCATION L2  ON(HRMS_TRAVEL_DTL.TRAVELDTL_TO_PLACE = L2.LOCATION_CODE) "
		+"	LEFT JOIN HRMS_JOURNEY J1 ON(HRMS_TRAVEL_DTL.TRAVELDTL_JOURNEY_MODE =J1.JOURNEY_ID) "
		+"	LEFT JOIN HRMS_JOURNEY_CLASS JC1 ON(HRMS_TRAVEL_DTL.TRAVELDTL_JOURNEY_CLASS = JC1.JOURNEY_CLASS_ID) "
		+"	LEFT JOIN HRMS_JOURNEY J2 ON(HRMS_TRAVEL_DTL.TRAVELDTL_SCHD_JOUR_MODE =J2.JOURNEY_ID) "
		+"	LEFT JOIN HRMS_JOURNEY_CLASS JC2 ON(HRMS_TRAVEL_DTL.TRAVELDTL_SCHD_JOUR_CLASS = JC2.JOURNEY_CLASS_ID) "
		+"  WHERE TRAVELDTL_TRAVEL_ID ="+bean.getTravelAppId() +"	ORDER BY TRAVELDTL_ID "; 
        Object [][] data = getSqlModel().getSingleResult(sql);
			
			    ArrayList<Object> jList = new ArrayList<Object>();   
			    double totalJcost =0;
			    double totalJCancel =0;
			    if(data.length >0 && data != null)     
			    {   int j =1;
	        	   for(int i=0; i<data.length;i++)
	        	      {
		        		    TravelApplication bean1 = new TravelApplication();    //for previus item
		        		    bean1.setItFromPlaceName(String.valueOf(data[i][0]).trim());
		  		        	bean1.setItToPlaceName(String.valueOf(data[i][1]).trim());
		  		        	bean1.setItJourneyName(String.valueOf(data[i][2]).trim());
		  		        	bean1.setItJourneyClassName(String.valueOf(data[i][3]).trim());
		  		        	bean1.setItJourneyDate(checkNull(String.valueOf(data[i][4])));
		  		        	bean1.setItJourneyTime(checkNull(String.valueOf(data[i][5]).trim())); 
		  		        	// if(bean.getStatusSch().equals("S"))            // for schedule status
		        			// {    //  
		  		        	        bean1.setSchViewFlag("true");
		  		        	      request.setAttribute("schJourneyMode"+j, String.valueOf(data[i][10]).trim());
		        			        request.setAttribute("schJourneyClass"+j, String.valueOf(data[i][11]).trim());
		        			        request.setAttribute("schJourneyModeId"+j, String.valueOf(data[i][12]).trim());
		        			        request.setAttribute("schJourneyClassId"+j, String.valueOf(data[i][13]).trim()); 
				  		        	bean1.setSchJourneyDate(checkNull(""+data[i][14])); 
				  		        	bean1.setSchJourneyTime(checkNull(String.valueOf(data[i][15]).trim())); 
				  		        	bean1.setSchModeNumber(String.valueOf(data[i][16]).trim()); 
				  		        	bean1.setSchTicketNumber(String.valueOf(data[i][17]).trim()); 
				  		        	bean1.setSchJourneyCost(String.valueOf(data[i][18]).trim()); 
				  		        	bean1.setSchExtraAmt(String.valueOf(data[i][19]).trim()); 
				  		        	bean1.setSchJourneyComment(String.valueOf(data[i][20]).trim());  
				  		        	bean1.setJourneyDtlId(String.valueOf(data[i][21]).trim());
				  		        	bean1.setCancelJourneyAmt(String.valueOf(data[i][22]).trim());
				  		          totalJcost +=(Double.parseDouble(""+data[i][18])+Double.parseDouble(""+data[i][19]));
				  		        totalJCancel += Double.parseDouble(""+data[i][22]);
				  		    	// }  
		  		        	jList.add(bean1);
		  		        	j++;
	        	      } 
	        	     bean.setSchJourneyList(jList);    
	        	     
	        	     String sqlHotel ="  SELECT NVL(TRAVEL_HOTEL_BILL,0),NVL(TRAVEL_HOTEL_OTHER_BILL,0) FROM HRMS_TRAVEL_HOTEL "
								+" WHERE TRAVEL_HOTEL_TRAVEL_ID = "+bean.getTravelAppId(); 
	        	     Object [][] result = getSqlModel().getSingleResult(sqlHotel);
	        	     double hotelTotalBill =0; 
	        	     if(result.length >0 && result!= null)
	        	     {
	        	    	  for(int h =0; h<result.length;h++)
		        	     {
		        	    	hotelTotalBill =hotelTotalBill+ (Double.parseDouble(""+result[h][0])+Double.parseDouble(""+result[h][1]));
		        	     } 
	        	    	 bean.setHotelTotalBill(""+Math.round(hotelTotalBill));
	        	     }
	        	     
	        	        double totalTravelCost =0;
        			     totalTravelCost = totalJcost+ hotelTotalBill;
	        	    	 bean.setTotalJCost(""+Math.round(totalJcost)); 
	        	    	 bean.setTotalTravelCost(""+Math.round(totalTravelCost));
	        	    	 bean.setTotalJCancel(""+totalJCancel);
	        	    	 logger.info("totalJCancel========="+totalJCancel);
										        	     
			    }
		 }   // end of callDtlForCancel
		 /*
		 *  This method is used for collect the data for the schedule
		 */
		 /**
		 * @param bean
		 * @param request
		 */
		public void callDtlSchedule(TravelApplication bean,HttpServletRequest request)
		 {
			try
			{
				// for check the travel Master
 String costSql ="  SELECT TRAVEL_GRADE_OTHER_TRAVEL_COST ,TRAVEL_GRADE_HOTEL_COST, TRAVEL_GRADE_OTHER_HOTEL_COST FROM HRMS_TRAVEL_GRADE_HDR "
				+"	WHERE  TRAVEL_GRADE_CODE ="+bean.getGradeCode();
		Object [][] costData = getSqlModel().getSingleResult(costSql);  
				
				
String sql =" SELECT  L1.LOCATION_NAME,L2.LOCATION_NAME, J1.JOURNEY_NAME,JC1.JOURNEY_CLASS_NAME,TO_CHAR(TRAVELDTL_JOURNEY_DATE,'DD-MM-YYYY'), " 
		+"	NVL(TRAVELDTL_JOURNEY_TIME,' '), NVL(TRAVELDTL_FROM_PLACE,0),   NVL(TRAVELDTL_TO_PLACE ,0),NVL(TRAVELDTL_JOURNEY_MODE,0),NVL(TRAVELDTL_JOURNEY_CLASS,0) , "
		+"	NVL(J2.JOURNEY_NAME ,' ')AS JSNAME,NVL(JC2.JOURNEY_CLASS_NAME,' ') AS JSCLASS,NVL(TRAVELDTL_SCHD_JOUR_MODE,0),NVL(TRAVELDTL_SCHD_JOUR_CLASS,0), "
		+"	TO_CHAR(TRAVELDTL_SCHD_DATE,'DD-MM-YYYY'), NVL(TRAVELDTL_SCHD_TIME,''), NVL(TRAVELDTL_SCHD_MODEREF_NUMBER,' '),NVL( TRAVELDTL_SCHD_TICKET_NUMBER,' '), "
		+"	NVL(TRAVELDTL_SCHD_JOUR_COST,0),NVL(TRAVELDTL_SCHD_JOUR_EXTAMT,0), NVL(TRAVELDTL_SCHD_JOUR_COMMENT,' ')"  
		+" FROM HRMS_TRAVEL_DTL  "
		+"	LEFT JOIN HRMS_LOCATION L1  ON(HRMS_TRAVEL_DTL.TRAVELDTL_FROM_PLACE = L1.LOCATION_CODE) "
		+"	LEFT JOIN HRMS_LOCATION L2  ON(HRMS_TRAVEL_DTL.TRAVELDTL_TO_PLACE = L2.LOCATION_CODE) "
		+"	LEFT JOIN HRMS_JOURNEY J1 ON(HRMS_TRAVEL_DTL.TRAVELDTL_JOURNEY_MODE =J1.JOURNEY_ID) "
		+"	LEFT JOIN HRMS_JOURNEY_CLASS JC1 ON(HRMS_TRAVEL_DTL.TRAVELDTL_JOURNEY_CLASS = JC1.JOURNEY_CLASS_ID) "
		+"	LEFT JOIN HRMS_JOURNEY J2 ON(HRMS_TRAVEL_DTL.TRAVELDTL_SCHD_JOUR_MODE =J2.JOURNEY_ID) "
		+"	LEFT JOIN HRMS_JOURNEY_CLASS JC2 ON(HRMS_TRAVEL_DTL.TRAVELDTL_SCHD_JOUR_CLASS = JC2.JOURNEY_CLASS_ID) "
		+"  WHERE TRAVELDTL_TRAVEL_ID ="+bean.getTravelAppId() +"	ORDER BY TRAVELDTL_ID "; 
        Object [][] data = getSqlModel().getSingleResult(sql);
			
			    ArrayList<Object> jList = new ArrayList<Object>();   
			    double totalJcost =0;
			   
			    if(data.length >0 && data != null)     
			    {   int j =1;
	        	   for(int i=0; i<data.length;i++)
	        	      {
		        		    TravelApplication bean1 = new TravelApplication();    //for previus item
		        		    bean1.setItFromPlaceName(String.valueOf(data[i][0]).trim());
		  		        	bean1.setItToPlaceName(String.valueOf(data[i][1]).trim());
		  		        	bean1.setItJourneyName(String.valueOf(data[i][2]).trim());
		  		        	bean1.setItJourneyClassName(String.valueOf(data[i][3]).trim());
		  		        	bean1.setItJourneyDate(checkNull(String.valueOf(data[i][4])));
		  		        	bean1.setItJourneyTime(checkNull(String.valueOf(data[i][5]).trim())); 
		  		        	bean1.setItFromPlaceId(String.valueOf(data[i][6]).trim()); 
		  		        	bean1.setItToPlaceId(String.valueOf(data[i][7]).trim()); 
		  		        	bean1.setItJourneyId(String.valueOf(data[i][8]).trim()); 
		  		        	bean1.setItJourneyClassId(String.valueOf(data[i][9]).trim()); 
		  		        	// if(bean.getStatusSch().equals("S"))            // for schedule status
		        			// {    //  
		  		        	        bean1.setSchViewFlag("true");  
		        			        request.setAttribute("schJourneyMode"+j, String.valueOf(data[i][10]).trim());
		        			        request.setAttribute("schJourneyClass"+j, String.valueOf(data[i][11]).trim());
		        			        request.setAttribute("schJourneyModeId"+j, String.valueOf(data[i][12]).trim());
		        			        request.setAttribute("schJourneyClassId"+j, String.valueOf(data[i][13]).trim()); 
				  		        	bean1.setSchJourneyDate(checkNull(""+data[i][14])); 
				  		        	bean1.setSchJourneyTime(checkNull(String.valueOf(data[i][15]).trim())); 
				  		        	bean1.setSchModeNumber(String.valueOf(data[i][16]).trim()); 
				  		        	bean1.setSchTicketNumber(String.valueOf(data[i][17]).trim()); 
				  		        	bean1.setSchJourneyCost(String.valueOf(data[i][18]).trim()); 
				  		        	bean1.setSchExtraAmt(String.valueOf(data[i][19]).trim()); 
				  		        	bean1.setSchJourneyComment(String.valueOf(data[i][20]).trim());  
				  		          totalJcost +=(Double.parseDouble(""+data[i][18])+Double.parseDouble(""+data[i][19]));
				  		    	// } 
				  		          
				  		        try {
			  		        	if(costData!=null && costData.length>0)
			  					   {
			  						bean1.setAlertTrOtherCost(""+costData[0][0]);
			  						bean1.setAlertHotelBillCost(""+costData[0][1]);
			  						bean1.setAlertHotelOtherCost(""+costData[0][2]);  
			  					   }
								} catch (Exception e) {
									// TODO: handle exception
								}
								
		  		        	jList.add(bean1);
		  		        	j++;
		  		        	
		  		        	
		  		        	
		  		        	
	        	      }  // end of for loop
	        	     bean.setSchJourneyList(jList);    
	        	     
	        	     String sqlHotel ="  SELECT NVL(TRAVEL_HOTEL_BILL,0),NVL(TRAVEL_HOTEL_OTHER_BILL,0) FROM HRMS_TRAVEL_HOTEL "
								+" WHERE TRAVEL_HOTEL_TRAVEL_ID = "+bean.getTravelAppId(); 
	        	     Object [][] result = getSqlModel().getSingleResult(sqlHotel);
	        	     double hotelTotalBill =0; 
	        	     if(result.length >0 && result!= null)
	        	     {
	        	    	  for(int h =0; h<result.length;h++)
		        	     {
		        	    	hotelTotalBill =hotelTotalBill+ (Double.parseDouble(""+result[h][0])+Double.parseDouble(""+result[h][1]));
		        	     } 
	        	    	 bean.setHotelTotalBill(""+Math.round(hotelTotalBill));
	        	     }
	        	     
	        	        double totalTravelCost =0;
        			     totalTravelCost = totalJcost+ hotelTotalBill;
	        	    	 bean.setTotalJCost(""+Math.round(totalJcost)); 
	        	    	 bean.setTotalTravelCost(""+Math.round(totalTravelCost));
        			 
										        	     
			    } //end of if
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			try
			{
				String noAppSql ="   SELECT JOURNEY_NAME,JOURNEY_CLASS_NAME, TRAVELDTL_JOURNEY_MODE, TRAVELDTL_JOURNEY_CLASS FROM HRMS_TRAVEL_DTL  "
					+"  LEFT JOIN HRMS_TRAVEL ON (HRMS_TRAVEL.TRAVEL_ID =HRMS_TRAVEL_DTL.TRAVELDTL_TRAVEL_ID) "
					+"  LEFT JOIN HRMS_JOURNEY ON(HRMS_JOURNEY.JOURNEY_ID= HRMS_TRAVEL_DTL.TRAVELDTL_JOURNEY_MODE) "  
					+"  LEFT JOIN HRMS_JOURNEY_CLASS ON(HRMS_JOURNEY_CLASS.JOURNEY_CLASS_ID= HRMS_TRAVEL_DTL.TRAVELDTL_JOURNEY_CLASS) "  
					+"  WHERE TRAVEL_ID= "+bean.getTravelAppId()+" AND TRAVELDTL_JOURNEY_CLASS NOT IN( "
					+"  SELECT  TRAVEL_GRADE_DTL_JCLASS_ID FROM HRMS_TRAVEL_GRADE_DTL " 
					+"  LEFT JOIN HRMS_JOURNEY ON(HRMS_JOURNEY.JOURNEY_ID= HRMS_TRAVEL_GRADE_DTL.TRAVEL_GRADE_DTL_JOURNEY_ID) "  
					+"  LEFT JOIN HRMS_JOURNEY_CLASS ON(HRMS_JOURNEY_CLASS.JOURNEY_CLASS_ID= HRMS_TRAVEL_GRADE_DTL.TRAVEL_GRADE_DTL_JCLASS_ID) "  
					+" LEFT JOIN HRMS_TRAVEL_GRADE_HDR ON (HRMS_TRAVEL_GRADE_DTL.TRAVEL_GRADE_HDR_ID =    HRMS_TRAVEL_GRADE_HDR.TRAVEL_GRADE_HDR_ID) " 
					+" WHERE  TRAVEL_GRADE_CODE = "+bean.getGradeCode()+"  AND TRAVEL_GRADE_DTL_CLASS_FLAG='Y'  "
					+"  ) ORDER BY JOURNEY_NAME";
				
	Object [][] AppData = getSqlModel().getSingleResult(noAppSql);  
	
	 if(AppData!=null && AppData.length>0)
	   {
		  String alertJname ="";
		  String alertMsg ="";
		  int count = 1;
		 for (int z =0; z< AppData.length ;z++)
		 {
			 if(!alertJname.equals(""+AppData[z][0]))
			 {
				 if(count!=1)
				 {
					 alertMsg +="\n"; 
				 }
				 alertMsg += ""+count+". "+AppData[z][0]+" - ";
				 count++;  
			 }else
			 {
				 if(z< AppData.length-1)
				 {
					 alertMsg +=", "; 
				 } 
			 }
			 alertMsg += AppData[z][1];
			 
			 alertJname = ""+AppData[z][0];
		 }
		 bean.setAlertSchMode(alertMsg);
		 bean.setAlertSchFlag("true");
		 logger.info("alertMsg =========  "+alertMsg);
	   } 	
 
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			
		 }  //end of callDtlSchedule
		 /*
		 *  This method is used for collect the data for the H
		 */
		 /**
		 * @param bean		 
		 */
		 public void getHoteldetail(TravelApplication bean)
		 {
			 String sqlHotel ="  SELECT  NVL(TRAVEL_HOTEL_NAME,' '), NVL(TRAVEL_HOTEL_ADDRESS,' '), TO_CHAR(TRAVEL_HOTEL_CHKIN_DATE,'DD-MM-YYYY'), NVL(TRAVEL_HOTEL_CHKIN_TIME,''), "
					+" TO_CHAR(TRAVEL_HOTEL_CHKOUT_DATE,'DD-MM-YYYY'),  NVL(TRAVEL_HOTEL_CHKOUT_TIME,''), NVL(TRAVEL_HOTEL_BILL,0)," 
					+" NVL(TRAVEL_HOTEL_OTHER_BILL,0), NVL(TRAVEL_HOTEL_COMMENT,' ') FROM HRMS_TRAVEL_HOTEL  "
					+" WHERE TRAVEL_HOTEL_TRAVEL_ID = "+bean.getTravelAppId() +"ORDER BY TRAVEL_HOTEL_ID"; 
	     Object [][] result = getSqlModel().getSingleResult(sqlHotel);
	     double hotelTotalBill =0;
	     
	     if(result.length >0 && result!= null)
	     {
	    	
	    	 ArrayList<Object> hotelList = new ArrayList<Object>(); 
	    	 for(int h =0; h<result.length;h++)
 	     {
 	    	  TravelApplication bean1 = new TravelApplication();   //Last time added in list
     		    bean1.setHotelName(""+result[h][0]);   // for hotel Details
		        	bean1.setHotelAddress(""+result[h][1]); 
		        	bean1.setChkInDate(checkNull(""+result[h][2])); 
		        	bean1.setChkInTime(checkNull(""+result[h][3])); 
		        	bean1.setChkOutDate(checkNull(""+result[h][4])); 
		        	bean1.setChkOutTime(checkNull(""+result[h][5]));
		        	bean1.setHotelBill(""+result[h][6]); 
		        	bean1.setExtraHotelBill(""+result[h][7]); 
  		        	bean1.setHotelComment(checkNull(""+result[h][8])); 
		        	hotelList.add(bean1);
		        	hotelTotalBill =hotelTotalBill+( Double.parseDouble(""+result[h][6])+ Double.parseDouble(""+result[h][7]));
 	     } //end of for loop
	    	 bean.setHotelList(hotelList);
	    	 bean.setHotelTotalBill(""+Math.round(hotelTotalBill));
	     }else
	     {
	    	 ArrayList<Object> hotelList = new ArrayList<Object>(); 
	     
 	    	  TravelApplication bean1 = new TravelApplication();   //Last time added in list
     		        bean1.setHotelName("");   // for hotel Details
		        	bean1.setHotelAddress(""); 
		        	bean1.setChkInDate(""); 
		        	bean1.setChkInTime(""); 
		        	bean1.setChkOutDate(""); 
		        	bean1.setChkOutTime("");
		        	bean1.setHotelBill("0"); 
		        	bean1.setExtraHotelBill("0"); 
  		        	bean1.setHotelComment(""); 
		        	hotelList.add(bean1);
		        	hotelTotalBill = 0;
	    	 bean.setHotelList(hotelList);
	    	 bean.setHotelTotalBill("0"); 
	     }
		 } //end of getHoteldetail
		 
		    /*
			 *  This method is used for cancel the hotel schedule.
			 */
			 /**
			 * @param bean		 
			 */
		 public void getHoteldetailForCancel(TravelApplication bean)
		 {
			 String sqlHotel ="  SELECT  NVL(TRAVEL_HOTEL_NAME,' '), NVL(TRAVEL_HOTEL_ADDRESS,' '), TO_CHAR(TRAVEL_HOTEL_CHKIN_DATE,'DD-MM-YYYY'), NVL(TRAVEL_HOTEL_CHKIN_TIME,''), "
					+" TO_CHAR(TRAVEL_HOTEL_CHKOUT_DATE,'DD-MM-YYYY'),  NVL(TRAVEL_HOTEL_CHKOUT_TIME,''), NVL(TRAVEL_HOTEL_BILL,0)," 
					+" NVL(TRAVEL_HOTEL_OTHER_BILL,0), NVL(TRAVEL_HOTEL_COMMENT,' '),TRAVEL_HOTEL_ID, NVL(TRAVEL_HOTEL_CANCEL_AMT,0) FROM HRMS_TRAVEL_HOTEL  "
					+" WHERE TRAVEL_HOTEL_TRAVEL_ID = "+bean.getTravelAppId() +"ORDER BY TRAVEL_HOTEL_ID"; 
	     Object [][] result = getSqlModel().getSingleResult(sqlHotel);
	     double hotelTotalBill =0;
	     double hotelTotalCancel =0;
	     if(result.length >0 && result!= null)
	     {
	    	
	    	 ArrayList<Object> hotelList = new ArrayList<Object>(); 
	    	 for(int h =0; h<result.length;h++)
 	     {
 	    	  TravelApplication bean1 = new TravelApplication();   //Last time added in list
     		    bean1.setHotelName(""+result[h][0]);   // for hotel Details
		        	bean1.setHotelAddress(""+result[h][1]); 
		        	bean1.setChkInDate(checkNull(""+result[h][2])); 
		        	bean1.setChkInTime(checkNull(""+result[h][3])); 
		        	bean1.setChkOutDate(checkNull(""+result[h][4])); 
		        	bean1.setChkOutTime(checkNull(""+result[h][5]));
		        	bean1.setHotelBill(""+result[h][6]); 
		        	bean1.setExtraHotelBill(""+result[h][7]); 
  		        	bean1.setHotelComment(checkNull(""+result[h][8]));
  		        	bean1.setHotelDtlId(checkNull(""+result[h][9]));
  		        	bean1.setHotelCancelAmt(checkNull(""+result[h][10]));
		        	hotelList.add(bean1);
		        	hotelTotalBill =hotelTotalBill+( Double.parseDouble(""+result[h][6])+ Double.parseDouble(""+result[h][7]));
		        	hotelTotalCancel += ( Double.parseDouble(""+result[h][10]));
 	     }
	    	 bean.setHotelList(hotelList);
	    	 bean.setHotelTotalBill(""+Math.round(hotelTotalBill));
	    	 bean.setHotelTotalCanceled(""+Math.round(hotelTotalCancel));
	     }else
	     {
	    	 
		     bean.setHotelTotalCanceled(""+Math.round(hotelTotalCancel));
	    	 bean.setHotelTotalBill("0"); 
	     }
		 }    // end of getHoteldetail
		 
		     /*
			 *  This method is used for save the cancel details.
			 */
			 /**
			 * @param bean
			 * @param request
			 */
		 public boolean  travelCancelSave(TravelApplication bean,HttpServletRequest request)
		 { 
			 boolean flag =false; 
			 	    
			    Object [] journeyDtlId = request.getParameterValues("journeyDtlId");
				Object [] journeyCancelAmt = request.getParameterValues("cancelJourneyAmt");				
				Object [] hotelDtlId = request.getParameterValues("hotelDtlId");
				Object [] hotelCancelAmt = request.getParameterValues("hotelCancelAmt");
				
				if(journeyDtlId !=null && journeyDtlId.length !=0){
				Object [][]updateJourneyCancel=new Object [journeyDtlId.length][2];
				for (int i = 0; i < journeyDtlId.length; i++) {
					updateJourneyCancel [i][0]= journeyCancelAmt[i];
					updateJourneyCancel [i][1]= journeyDtlId[i];
					
				}
				flag=getSqlModel().singleExecute(getQuery(8), updateJourneyCancel);
				}
				if(flag)
				if(hotelDtlId !=null && hotelDtlId.length !=0){
					Object [][]updateHotelCancel=new Object [hotelDtlId.length][2];
					for (int i = 0; i < hotelDtlId.length; i++) {
						updateHotelCancel [i][0]= hotelCancelAmt[i];
						updateHotelCancel [i][1]= hotelDtlId[i];
					}
					flag=getSqlModel().singleExecute(getQuery(9), updateHotelCancel);
					}
			
				if(flag){
					String updateQuery ="UPDATE HRMS_TRAVEL SET TRAVEL_CANCEL_ADMIN_STATUS='E' , TRAVEL_CONFIRM_STATUS ='K' WHERE TRAVEL_ID="+bean.getTravelAppId();
					getSqlModel().singleExecute(updateQuery);
				}
			 return flag;
			 
		 }  // end of travelCancelSave
		    /*
			 *  This method is used for save the schedule details.
			 */
			 /**
			 * @param bean
			 * @param request
			 */
		 public boolean  travelScheduleSave(TravelApplication bean,HttpServletRequest request)
		 {  boolean flag =false; 
			 try
			 {
			    
			    Object [] fromPlaceId = request.getParameterValues("itFromPlaceId");
				Object [] toPlaceId = request.getParameterValues("itToPlaceId");
				Object [] journeyClassId = request.getParameterValues("itJourneyClassId");
				Object [] journeyId = request.getParameterValues("itJourneyId");
				Object [] journeyDate = request.getParameterValues("itJourneyDate");
				Object [] journeyTime = request.getParameterValues("itJourneyTime");  
				
				Object [] schModeNumber =  request.getParameterValues("schModeNumber");  
				Object [] schTicketNumber = request.getParameterValues("schTicketNumber");  
				Object [] schJourneyDate = request.getParameterValues("schJourneyDate");  
	    		Object [] schJourneyTime = request.getParameterValues("schJourneyTime");
				Object [] schJourneyCost = request.getParameterValues("schJourneyCost"); 
				Object [] schExtraAmt = request.getParameterValues("schExtraAmt"); 
				Object [] schJourneyComment = request.getParameterValues("schJourneyComment"); 
				String [] schJourModeId = new String [fromPlaceId.length];
				String [] schJourClassId = new String [fromPlaceId.length]; 
				
				Object [] hotelName =  request.getParameterValues("hotelName");  // for hotel Details
				Object [] hotelAddress = request.getParameterValues("hotelAddress");  
				Object [] chkInDate = request.getParameterValues("chkInDate");  
	    		Object [] chkInTime = request.getParameterValues("chkInTime");
				Object [] chkOutDate = request.getParameterValues("chkOutDate"); 
				Object [] chkOutTime = request.getParameterValues("chkOutTime"); 
				Object [] hotelBill = request.getParameterValues("hotelBill"); 
				Object [] extraHotelBill = request.getParameterValues("extraHotelBill"); 
				Object [] hotelComment = request.getParameterValues("hotelComment"); 
				
				Object [][] para =new Object[1][1];
				para[0][0]=bean.getTravelAppId();
				if(bean.getScheduleFinalFlag().equals("final"))
				{
				boolean flagUpdate =  getSqlModel().singleExecute(getQuery(5),para);
				   
				   
				   
				   if(flagUpdate)
			 		{
					   String emSql = " SELECT TRAVEL_EMPID FROM HRMS_TRAVEL WHERE TRAVEL_ID ="+bean.getTravelAppId();
					     Object [][] empData=  getSqlModel().getSingleResult(emSql);
					    Object[][] to_mailIds =new Object[1][1];	
						Object[][] from_mailIds =new Object[1][1];	
						 
					 	to_mailIds[0][0]= empData[0][0];
						from_mailIds[0][0]=bean.getUserEmpId(); 
						
						MailUtility mail=new MailUtility();
						mail.initiate(context, session);
						mail.sendMail(to_mailIds, from_mailIds,"Travel", "", "SC"); 
						mail.terminate();
			 		}
				}
				
				int j =1;
				for(int i= 0; i<fromPlaceId.length;i++)
				{   
					String jrModeId =(String)request.getParameter("schJourneyModeId"+j);
					String jrClassId =(String) request.getParameter("schJourneyClassId"+j);
					
					schJourModeId[i]=jrModeId;
					schJourClassId[i] =jrClassId;
					logger.info("schJourModeId=========  "+schJourModeId[i]);
					j++;
				} 
			    if(fromPlaceId!=null)
			  	{
			  	   String query = " DELETE FROM  HRMS_TRAVEL_DTL WHERE TRAVELDTL_TRAVEL_ID = " +bean.getTravelAppId();	
			  	   logger.info("query  "+query);
			       flag = getSqlModel().singleExecute(query);
			       logger.info("In Delete "+flag);
			  	} 
			     if(flag)
			     {
			    	 logger.info("bean.getTravelAppId() ========= .......  "+bean.getTravelAppId());
				    for(int i=0; i<fromPlaceId.length;i++)
			    	{    
				    	 logger.info("In Loop "+i);
				    	        Object addItem[][] = new Object[1][16];
				    	        addItem[0][0] =bean.getTravelAppId();
			  					addItem[0][1] =String.valueOf(fromPlaceId[i]);
			  					addItem[0][2] =String.valueOf(toPlaceId[i]);
			  					addItem[0][3] =String.valueOf(journeyId[i]);
			  					addItem[0][4] =String.valueOf(journeyClassId[i]);  				 
			  			        addItem[0][5] =String.valueOf(checkNull(""+journeyDate[i])).trim();;
			  					addItem[0][6] =String.valueOf(checkNull(""+journeyTime[i]));  
			  					addItem[0][7] =String.valueOf(schJourModeId[i]); //SCHEDULE MODE NUMBER
			  					addItem[0][8] =String.valueOf(schJourClassId[i]);
			  					addItem[0][9] =String.valueOf(checkNull(""+schJourneyDate[i])).trim();
			  					addItem[0][10] =String.valueOf(checkNull(""+schJourneyTime[i]));  				 
			  			        addItem[0][11] =String.valueOf(schModeNumber[i]);
			  					addItem[0][12] =String.valueOf(schTicketNumber[i]);
			  					  String cost ="0";
			  					  String extra ="0";
					    	     if(String.valueOf(schJourneyCost[i]).equals("")||String.valueOf(schJourneyCost[i]).equals("null"))
					    	     {	 cost ="0"; }
					    	      else 	{ cost = ""+Integer.parseInt(String.valueOf(schJourneyCost[i])); };  
			  					addItem[0][13] =cost ;
			  					
			  					 if(String.valueOf(schExtraAmt[i]).equals("")||String.valueOf(schExtraAmt[i]).equals("null"))
					    	     {	 extra ="0"; }
					    	      else 	{ extra = ""+Integer.parseInt(String.valueOf(schExtraAmt[i])); }; 
			  					addItem[0][14] =extra; 
			  					addItem[0][15] =String.valueOf(schJourneyComment[i]);    
			  			flag= getSqlModel().singleExecute(getQuery(6),addItem);	 
			    	}   
			  }
 
			     logger.info("Flag >>>>>>>>> >>>>>>>>>> "+flag);
			     if(flag)
			     {
 
			    	 if(hotelName !=null)
					  	{
			    		 logger.info("In Hotel");
					  	   String query = " DELETE FROM  HRMS_TRAVEL_HOTEL WHERE TRAVEL_HOTEL_TRAVEL_ID  = " +bean.getTravelAppId();	
					  	   flag = getSqlModel().singleExecute(query); 
					  	 if(flag)
					     {
						for(int k =0;k<hotelName.length;k++)
						{
			    	     Object hotelPara[][] = new Object[1][10];
			    	     hotelPara[0][0] =bean.getTravelAppId();
			    	     hotelPara[0][1] = String.valueOf(hotelName[k]); 
			    	     hotelPara[0][2] = String.valueOf(hotelAddress[k]); 
			    	     hotelPara[0][3] = String.valueOf(checkNull(""+chkInDate[k])).trim();; 
			    	     hotelPara[0][4] = String.valueOf(checkNull(""+chkInTime[k])); 
			    	     hotelPara[0][5] = String.valueOf(checkNull(""+chkOutDate[k])).trim();; 
			    	     hotelPara[0][6] = String.valueOf(checkNull(""+chkOutTime[k])); 
			    	     String Bill ="0";
			    	     String exBill ="0";
			    	     if(String.valueOf(hotelBill[k]).equals("")||String.valueOf(hotelBill[k]).equals("null"))
			    	     {
			    	    	 Bill ="0";
			    	     }else
			    	     {
			    	    	 Bill = ""+Integer.parseInt(String.valueOf(hotelBill[k]));
			    	     }
			    	     hotelPara[0][7] =Bill ;  
			    	     if(String.valueOf(extraHotelBill[k]).equals("")||String.valueOf(extraHotelBill[k]).equals("null"))
			    	     {
			    	    	 exBill ="0";
			    	     }else
			    	     {
			    	    	 exBill = ""+Integer.parseInt(String.valueOf(extraHotelBill[k]));
			    	     }
			    	     hotelPara[0][8] =exBill ;  
			    	     hotelPara[0][9] =String.valueOf(hotelComment[k]);  
			    	     
			    	     flag= getSqlModel().singleExecute(getQuery(7),hotelPara);	 
			    	     
						}
					     }
						}
			    	 
			     }    // end of if
			     
			 }catch (Exception e) {
				 logger.error(e.getMessage());
				 return false;
			}
			 return flag;
			 
		 } // end of travelScheduleSave   
		 
		    /*
			 *  This method is used for add the hotel details.
			 */
			 /**
			 * @param bean
			 * @param request
			 */
		 
		 public void addHoltelList(TravelApplication bean ,HttpServletRequest request)
		 {   
			    Object [] hotelName =  request.getParameterValues("hotelName");  // for hotel Details
				Object [] hotelAddress = request.getParameterValues("hotelAddress");  
				Object [] chkInDate = request.getParameterValues("chkInDate");  
	    		Object [] chkInTime = request.getParameterValues("chkInTime");
				Object [] chkOutDate = request.getParameterValues("chkOutDate"); 
				Object [] chkOutTime = request.getParameterValues("chkOutTime"); 
				Object [] hotelBill = request.getParameterValues("hotelBill");  
				Object [] extraHotelBill = request.getParameterValues("extraHotelBill");  
				Object [] hotelComment = request.getParameterValues("hotelComment");  
				
				
				ArrayList<Object> hotelList = new ArrayList<Object>(); 
				        if(hotelName!=null)
				        {  
				        	for(int i=0; i<hotelName.length+1;i++)
				        	{ 
				        		if(i<hotelName.length)
				        		{ 
				        			TravelApplication bean1 = new TravelApplication();   //for previus item
				        			bean1.setHotelName(""+hotelName[i]);   // for hotel Details
				  		        	bean1.setHotelAddress(""+hotelAddress[i]); 
				  		        	bean1.setChkInDate(checkNull(""+chkInDate[i])); 
				  		        	bean1.setChkInTime(checkNull(""+chkInTime[i])); 
				  		        	bean1.setChkOutDate(checkNull(""+chkOutDate[i])); 
				  		        	bean1.setChkOutTime(checkNull(""+chkOutTime[i]));
				  		        	bean1.setHotelBill(""+hotelBill[i]);  
				  		        	bean1.setExtraHotelBill(""+extraHotelBill[i]);
				  		        	bean1.setHotelComment(checkNull(""+hotelComment[i]));
				  		        	
				  		        	hotelList.add(bean1);
				  		        	bean.setHotelList(hotelList);
				        		}else
				        		{
				        		    TravelApplication bean1 = new TravelApplication();   //Last time added in list
				        		    bean1.setHotelName("");   // for hotel Details
				  		        	bean1.setHotelAddress(""); 
				  		        	bean1.setChkInDate(""); 
				  		        	bean1.setChkInTime(""); 
				  		        	bean1.setChkOutDate(""); 
				  		        	bean1.setChkOutTime("");
				  		        	bean1.setHotelBill(""); 
				  		        	bean1.setExtraHotelBill("");
				  		        	bean1.setHotelComment("");
				  		        	hotelList.add(bean1);
				  		        	bean.setHotelList(hotelList);
				        		}
				        	}
				        	
				        }else
				        {
				            TravelApplication bean1 = new TravelApplication();   //first time added in list
				        	bean1.setHotelName("");   // for hotel Details
		  		        	bean1.setHotelAddress(""); 
		  		        	bean1.setChkInDate(""); 
		  		        	bean1.setChkInTime(""); 
		  		        	bean1.setChkOutDate(""); 
		  		        	bean1.setChkOutTime("");
		  		        	bean1.setHotelBill("");  
		  		        	hotelList.add(bean1);
		  		        	bean.setHotelList(hotelList);
				        } 
		 } // end of addHoltelList
		    /*
			 *  This method is used for show the hotel details.
			 */
			 /**
			 * @param bean
			 * @param request
			 */
		 public void callDtlForHotel(TravelApplication bean,HttpServletRequest request)
		 {
			    Object [] fromPlaceId = request.getParameterValues("itFromPlaceId");
				Object [] toPlaceId = request.getParameterValues("itToPlaceId");   
			 
			    Object [] itFromPlaceName = request.getParameterValues("itFromPlaceName");
				Object [] itToPlaceName = request.getParameterValues("itToPlaceName"); 
				Object [] itJourneyName = request.getParameterValues("itJourneyName");
				Object [] itJourneyClassName = request.getParameterValues("itJourneyClassName");
				
				Object [] journeyClassId = request.getParameterValues("itJourneyClassId");
				Object [] journeyId = request.getParameterValues("itJourneyId");
				Object [] journeyDate = request.getParameterValues("itJourneyDate");
				Object [] journeyTime = request.getParameterValues("itJourneyTime");  
				
				Object [] schModeNumber =  request.getParameterValues("schModeNumber");  
				Object [] schTicketNumber = request.getParameterValues("schTicketNumber");  
				Object [] schJourneyDate = request.getParameterValues("schJourneyDate");  
	    		Object [] schJourneyTime = request.getParameterValues("schJourneyTime");
				Object [] schJourneyCost = request.getParameterValues("schJourneyCost"); 
				Object [] schExtraAmt = request.getParameterValues("schExtraAmt"); 
				Object [] schJourneyComment = request.getParameterValues("schJourneyComment"); 
				String [] schJourModeId = new String [fromPlaceId.length];
				String [] schJourClassId = new String [fromPlaceId.length];  
				  ArrayList<Object> jList = new ArrayList<Object>(); 
				 if(fromPlaceId.length >0 && fromPlaceId != null)
				    {   int j =1;
		        	   for(int i=0; i<fromPlaceId.length;i++)
		        	      {
			        		    TravelApplication bean1 = new TravelApplication();    //for previus item
			        		    bean1.setItFromPlaceName(""+itFromPlaceName[i]);
			  		        	bean1.setItToPlaceName(""+itToPlaceName[i]);
			  		        	bean1.setItJourneyName(""+itJourneyName[i]);
			  		        	bean1.setItJourneyClassName(""+itJourneyClassName[i]); 
			  		        	bean1.setItJourneyDate(checkNull(""+journeyDate[i]));
			  		        	bean1.setItJourneyTime(checkNull(""+journeyTime[i])); 
			  		        	bean1.setItFromPlaceId(""+fromPlaceId[i]); 
			  		        	bean1.setItToPlaceId(""+toPlaceId[i]); 
			  		        	bean1.setItJourneyId(""+journeyId[i]); 
			  		        	bean1.setItJourneyClassId(""+journeyClassId[i]);  
			  		        	String jmode=  (String)request.getParameter("schJourneyMode"+j);
			  		        	logger.info("jmode=============="+jmode);
			  		        	String jmodeId=  (String)request.getParameter("schJourneyModeId"+j);
			  		        	String jClass=  (String)request.getParameter("schJourneyClass"+j);
			  		        	String jClassId=  (String)request.getParameter("schJourneyClassId"+j); 
			  		        	        request.setAttribute("schJourneyMode"+j,jmode);
			        			        request.setAttribute("schJourneyClass"+j,jClass);
			        			        request.setAttribute("schJourneyModeId"+j,jmodeId);
			        			        request.setAttribute("schJourneyClassId"+j,jClassId); 
					  		        	bean1.setSchJourneyDate(checkNull(""+schJourneyDate[i])); 
					  		        	bean1.setSchJourneyTime(checkNull(""+schJourneyTime[i])); 
					  		        	bean1.setSchModeNumber(""+schModeNumber[i]); 
					  		        	bean1.setSchTicketNumber(""+schTicketNumber[i]); 
					  		        	bean1.setSchJourneyCost(""+schJourneyCost[i]); 
					  		        	bean1.setSchExtraAmt(""+schExtraAmt[i]); 
					  		        	bean1.setSchJourneyComment(""+schJourneyComment[i]);  
					  		       //   totalJcost +=(Double.parseDouble(""+data[i][18])+Double.parseDouble(""+data[i][19]));
					  		        	 bean1.setSchViewFlag("true");  
			  		        	jList.add(bean1);
			  		        	j++;
		        	      } 
		        	     bean.setSchJourneyList(jList);    
			 }
 
		 }  // end of callDtlForHotel
		 
		  
		 public void generateExpense(TravelApplication bean)
			{
			 
			  // for age display 
			  String dobSql =" SELECT TO_CHAR(EMP_DOB,'DD-MM-YYYY') FROM HRMS_EMP_OFFC WHERE  EMP_ID = "+bean.getEmpId();
			  Object [][] dobData = getSqlModel().getSingleResult(dobSql);
			  
			  if(dobData!=null && dobData.length >0 )
			  {
				   String ageSql =" SELECT ROUND((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')-TO_DATE('"+dobData[0][0]+"','DD-MM-YYYY'))/365) AS AGE FROM DUAL";
				   Object [][] ageData = getSqlModel().getSingleResult(ageSql);  
				  if(ageData !=null && ageData.length >0)
				 {
					 try{  
					 bean.setEmpAge(""+ageData[0][0]);
					 System.out.println("bean.getEmpAge()"+bean.getEmpAge());
				    }catch (Exception e) {
					e.printStackTrace();
				   }
				 } 
			  }
			 String conSql =" SELECT  NVL(ADD_MOBILE,' ') FROM HRMS_EMP_ADDRESS WHERE EMP_ID = "+bean.getEmpId();
			 Object [][] conData = getSqlModel().getSingleResult(conSql);  
			 
			 if(conData !=null && conData.length >0)
			 {
				 try{  
					 bean.setContactNo(""+conData[0][0]); 
			    }catch (Exception e) {
			    	e.printStackTrace();
			   }
			 } 
			  
			   String sqlTravel =" SELECT TRAVEL_GRADE_DTL_JOURNEY_ID, TRAVEL_GRADE_DTL_JCLASS_ID ,TRAVEL_GRADE_HOTEL_COST, " 
			   	  +" TRAVEL_GRADE_OTHER_HOTEL_COST, TRAVEL_GRADE_OTHER_TRAVEL_COST FROM  HRMS_TRAVEL_GRADE_DTL "
				  +" LEFT JOIN HRMS_TRAVEL_GRADE_HDR ON (HRMS_TRAVEL_GRADE_DTL.TRAVEL_GRADE_HDR_ID =  HRMS_TRAVEL_GRADE_HDR.TRAVEL_GRADE_HDR_ID) "
			      +" WHERE  TRAVEL_GRADE_CODE ="+bean.getGradeCode()+" AND TRAVEL_GRADE_DTL_CLASS_FLAG='Y'"; 
			    Object [][] dataTravel = getSqlModel().getSingleResult(sqlTravel);  
			    if(dataTravel!=null && dataTravel.length>0)
			    {   bean.setAppDispFlag("false");
			    	bean.setHotelPerDayCost(""+dataTravel[0][2]);
			    	bean.setHotelOtherCost(""+dataTravel[0][3]);
			    	bean.setTravelOtherCost(""+dataTravel[0][4]);
			     String classId="";
			     String jourId="";
			     for(int i =0;i<dataTravel.length;i++)
			     {
			    	if(i<dataTravel.length-1)
			    	{
			    		    jourId+="*"+dataTravel[i][0]+"*,";
			    			classId+="*"+dataTravel[i][1]+"*,";
			    	}else
			    	{
			    		jourId+="*"+dataTravel[i][0]+"*";
			    		classId+="*"+dataTravel[i][1]+"*";
			    	}
			     }
			     bean.setGradeClassId(classId);
			     bean.setGradeJourneyId(jourId);
			    } 
			    
			  String modeSql =" SELECT DISTINCT JOURNEY_NAME,JOURNEY_CLASS_NAME ,TRAVEL_GRADE_OTHER_TRAVEL_COST  " 
				   +" , TRAVEL_GRADE_HOTEL_COST , TRAVEL_GRADE_OTHER_HOTEL_COST , TRAVEL_GRADE_POCKET_COST, TRAVEL_GRADE_FOOD_COST "
			  		+" FROM HRMS_TRAVEL_GRADE_DTL "
					+" LEFT JOIN HRMS_JOURNEY ON(HRMS_JOURNEY.JOURNEY_ID= HRMS_TRAVEL_GRADE_DTL.TRAVEL_GRADE_DTL_JOURNEY_ID) "
					+" LEFT JOIN HRMS_JOURNEY_CLASS ON(HRMS_JOURNEY_CLASS.JOURNEY_CLASS_ID= HRMS_TRAVEL_GRADE_DTL.TRAVEL_GRADE_DTL_JCLASS_ID)  "
					+" LEFT JOIN HRMS_TRAVEL_GRADE_HDR ON (HRMS_TRAVEL_GRADE_DTL.TRAVEL_GRADE_HDR_ID =  HRMS_TRAVEL_GRADE_HDR.TRAVEL_GRADE_HDR_ID) "
					+" WHERE  TRAVEL_GRADE_CODE ="+bean.getGradeCode()+"AND TRAVEL_GRADE_DTL_CLASS_FLAG='Y'";
			   Object [][] modeData= getSqlModel().getSingleResult(modeSql);   
			   
			    if(modeData!=null && modeData.length>0)
			    {
			 	  String alertJname ="";
			 	  String alertMsg ="";
			 	  int count = 1;
			 	 for (int z =0; z< modeData.length ;z++)
			 	 {
			 		 if(!alertJname.equals(""+modeData[z][0]))
			 		 {
			 			 if(count!=1)
			 			 {
			 				 alertMsg +="   "; 
			 			 }
			 			 alertMsg += "  "+count+". "+modeData[z][0]+" :- ";
			 			 count++;  
			 		 }else
			 		 {
			 			 if(z< modeData.length-1)
			 			 {
			 				 alertMsg +=", "; 
			 			 } 
			 		 }
			 		 alertMsg += modeData[z][1];
			 		 
			 		 alertJname = ""+modeData[z][0];
			 	 }
			 	bean.setAppModeName(alertMsg);  
			 	bean.setTrOtherExp("Travel Miscellaneous  Expenses :- "+modeData[0][2]);
				bean.setLodgAllowPerDay("Lodging allowance per Day :- "+modeData[0][3]);
				bean.setLodgOtherAllow("Lodging Miscellaneous allowance per day :- "+modeData[0][4]);
				bean.setOutPocketAllow("Out of pocket allowance per day :- "+modeData[0][5]);
				bean.setFoodAllow("Food allowance per day :- "+modeData[0][6]);
				bean.setCssClassTYpe("border2");
			    } 
				 else
			    {
			    	bean.setAppDispFlag("true");
			    }
		}
			
}  // end of class
