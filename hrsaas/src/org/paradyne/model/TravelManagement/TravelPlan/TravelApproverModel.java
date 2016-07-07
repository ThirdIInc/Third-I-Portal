
/**
 * @author balajim
 * 12-08-2008
 */
package org.paradyne.model.TravelManagement.TravelPlan;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;  

import org.paradyne.bean.TravelManagement.TravelPlan.TravelApplication;
import org.paradyne.bean.TravelManagement.TravelPlan.TravelApprover;  
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;

public class TravelApproverModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	/*
	 *  This method is used for show the status wise data.
	 */
	 /**
	 * @param bean
	 * @param status
	 */
	public void callStatus(TravelApprover bean , String status,HttpServletRequest request)
	{ 
		String query = "  SELECT TRAVEL_APP_REQUEST ,EMP_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS EMPNAME,  TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY'), ";
			  if(bean.getTrAppCanStatus().equals("true"))
		         {
				 query += " TRAVEL_APP_STATUS ," ;
		         }else
		         {
		        query += " TRAVEL_CANCEL_STATUS," ; 
		         } 
			  query +=" TRAVEL_APP_LEVEL, TRAVEL_APP_ID ,EMP_CADRE,TRAVEL_APP_POLICY_ID  FROM HRMS_TMS_TRAVEL_APP  "
			   +"	LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID)  "
			   +"	LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)  "
			   +"	LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)	"
			   +"	WHERE " ;
		         if(bean.getTrAppCanStatus().equals("true"))
		         {
				 query += " TRAVEL_APP_STATUS  ='"+status+"' AND ( TRAVEL_APP_APPROVER ="+bean.getUserEmpId() +"  OR  TRAVEL_APP_ALTER_APPROVER = "+bean.getUserEmpId() +") ORDER BY TRAVEL_APP_APPDATE  DESC " ;
		         }else
		         {
		        query += "TRAVEL_CANCEL_STATUS  ='"+status+"'  AND ( TRAVEL_CANCEL_APPROVER ="+bean.getUserEmpId() +"  OR  TRAVEL_CANCEL_ALTER_APPROVER = "+bean.getUserEmpId() +") ORDER BY TRAVEL_APP_APPDATE  DESC " ; 
		         }
		         
		
		Object [][] result = getSqlModel().getSingleResult(query );
		
		doPaging(bean, result.length, new Object[][]{}, request);
		int fromTotRec = Integer.parseInt(bean.getFromTotRec());
		int toTotRec = Integer.parseInt(bean.getToTotRec());
		
		ArrayList travelList = new ArrayList();
		
		for(int i =fromTotRec;i<toTotRec;i++)
		{
			TravelApprover bean1 = new TravelApprover(); 
			
			bean1.setTrRequestName(String.valueOf(result[i][0]));
			bean1.setTravelEmpId(String.valueOf(result[i][1]));
			bean1.setEmpName(String.valueOf(result[i][2]));
			bean1.setAppDate(String.valueOf(result[i][3]));
			bean1.setCheckStatus(String.valueOf(result[i][4]));
			bean1.setDispSrNo(""+(i+1));
			if(status.equals("A"))
				bean1.setStatusNew("Approved");
			if(status.equals("R"))
			bean1.setStatusNew("Rejected");
			bean1.setLevel(String.valueOf(result[i][5]));
			bean1.setTravelAppId(String.valueOf(result[i][6])); 
			String pathQuery="  SELECT APP_PATH_APPROVER_REMARK FROM HRMS_TMS_APP_PATH WHERE APP_PATH_APPROVER_CODE="+bean.getUserEmpId()
			+" AND APP_PATH_APP_ID ="+String.valueOf((result[i][6])); 
			Object [][] data=getSqlModel().getSingleResult(pathQuery);
			if(data.length==0 || String.valueOf(data[0][0]).equals("null") || data==null){				 
				bean1.setRemark("");
			}else {
				 bean1.setRemark(String.valueOf(data[0][0]));
			} 
			
			
			// for not applicable travel Mode
			String [] alertTr= getNotAppTravelMode(""+result[i][6],""+result[i][8]);
			bean1.setAlertTrMode(alertTr[0]);
			bean1.setAlertTrFlag(alertTr[1]); 
			
			String [] alertHotel= getNotAppHotelType(""+result[i][6],""+result[i][8]);
			bean1.setAlertHotelType(alertHotel[0]);
			bean1.setAlertHotelFlag(alertHotel[1]);  
			travelList.add(bean1); 
		} //end of for loop
			 bean.setTravelList(travelList);
			 if(travelList.size()==0)
			 {
				  bean.setListLength("0");
				  bean.setNoData("true"); 
			 }else
			 {
				 bean.setListLength("1"); 
				 bean.setNoData("false"); 
			 }
			 bean.setStatus(status); 
			 
				if(!status.equals("P")){
					System.out.println("status checking In Model  "+status);
					bean.setApprflag("true");
				}  
			 
			 
	} //end of callStatus
	
	public String [] getNotAppTravelMode(String trAppId,String policyId)
	{ 
		System.out.println("trAppId======== "+trAppId);
		System.out.println("policyId======== "+policyId);
	String [] notAppTr = new String[2]; 
	String sql ="SELECT NVL(JOURNEY_NAME,' '),NVL(JOURNEY_CLASS_NAME,' ') FROM HRMS_TMS_APPJOUR_DTL "
		+"	LEFT JOIN HRMS_TMS_JOURNEY ON(JOURNEY_ID =APPJOUR_DTL_JOURMODE ) "
		+"	WHERE APPJOUR_DTL_TRAPPID ="+trAppId+" AND "
		+"	APPJOUR_DTL_JOURMODE NOT IN (SELECT  PTM_TRAVEL_MODE_ID  FROM HRMS_TMS_POLICY_TRAVEL_MODE " 
		+"	WHERE PTM_POLICY_ID ="+policyId+" AND PTM_STATUS ='Y')";
	Object [][] AppData = getSqlModel().getSingleResult(sql );
	
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
		 notAppTr[0]=alertMsg;
		 notAppTr[1]="true"; 
 
	   }else	   {
		     notAppTr[0]="";
			 notAppTr[1]="";    
	   }
		return notAppTr;
	}
	
	
	public String [] getNotAppHotelType(String trAppId,String policyId)
	{ 
		System.out.println("trAppId===getNotAppHotelType===== "+trAppId);
		System.out.println("policyId====getNotAppHotelType==== "+policyId);
	String [] notAppTr = new String[2];  
	
  String sql ="  SELECT HOTEL_TYPE_NAME,ROOM_TYPE_NAME  FROM HRMS_TMS_APPLODG_DTL  "
			+"	LEFT JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=APPLODG_DTL_HOTEL ) "
			+"  LEFT JOIN HRMS_TMS_ROOM_TYPE ON(HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID= APPLODG_DTL_ROOM)  "
			+" WHERE APPLODG_DTL_TRAPPID ="+trAppId+" AND "  
			+" (APPLODG_DTL_HOTEL NOT IN (SELECT  PHT_HOTEL_TYPE_ID  FROM HRMS_TMS_POLICY_HOTEL_TYPE WHERE PHT_POLICY_ID ="+policyId+" AND PHT_STATUS ='Y')"  
			+" OR (APPLODG_DTL_ROOM NOT IN (SELECT  PHT_ROOM_ID FROM HRMS_TMS_POLICY_HOTEL_TYPE WHERE PHT_POLICY_ID ="+policyId+" AND PHT_STATUS ='Y') )) ";
	
	Object [][] AppData = getSqlModel().getSingleResult(sql );
	
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
		 notAppTr[0]=alertMsg;
		 notAppTr[1]="true"; 
 
	   }else	   {
		     notAppTr[0]="";
			 notAppTr[1]="";    
	   }
		return notAppTr;
	}
	
	/*
	 *  This method is used for forward the application to respective person.
	 */
	/**
	 * @param bean
	 * @param status
	 * @param travelAppId
	 * @param comments
	 * @param travelEmpId
	 */
	public void forward (TravelApprover bean,String status, String  travelAppId ,String comments ,String travelEmpId)
	{
		 
		Object [][] addObj = new Object[1][4];
		
		addObj[0][0] = travelAppId;
		addObj[0][1]=bean.getUserEmpId();
		addObj[0][2]=comments;
		addObj[0][3]=status;
		
		if(String.valueOf(status).equals("R")){
			
			Object[][]reject=new Object[1][2];
			reject[0][0]=String.valueOf(status);
			reject[0][1]=String.valueOf(travelAppId);
			
			  if(bean.getTrAppCanStatus().equals("true"))
		         {
					getSqlModel().singleExecute(getQuery(3), reject);
		         }else
		         {
		        	 getSqlModel().singleExecute(getQuery(5), reject); 
		         }
			  
		
			
			logger.info("To mail===Reject==11111== "+travelEmpId);
			logger.info("From mail==  Reject ==1111=== "+bean.getUserEmpId());
				
			
		}  //end of if
		// for mail working
		/*try {
			Object[][] to_mailIds =new Object[1][1];	
			Object[][] from_mailIds =new Object[1][1];	
			 
		 	to_mailIds[0][0]= travelEmpId;
			from_mailIds[0][0]=bean.getUserEmpId();
			MailUtility mail=new MailUtility();
			mail.initiate(context, session);
			mail.sendMail(to_mailIds, from_mailIds,"Travel", "", status); 
			mail.terminate();
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			 
		}  */
		
		  if(bean.getTrAppCanStatus().equals("true"))
	         {
			  getSqlModel().singleExecute(getQuery(1), addObj); 
	         }else
	         {
	        	 getSqlModel().singleExecute(getQuery(4), addObj); 
	         }
		
	} //end of forward
	
	/*
	 *  This method is used for change the status of application.
	 */
	/**
	 * @param bean
	 * @param empFlow
	 * @param travelAppId
	 * @param travelEmpId
	 * @return boolean
	 */
	public boolean changeApplStatus(TravelApprover bean,Object [][]empFlow,String travelAppId,String travelEmpId){
		boolean result=false;
		 logger.info("In empFlow=========="+empFlow.length);
		if(empFlow !=null && empFlow.length!=0){ 
		Object [][]updateApprover=new Object[1][4];
		updateApprover[0][0]=empFlow[0][2];
		updateApprover[0][1]=empFlow[0][0]; 
		updateApprover[0][2]=empFlow[0][3]; 
		updateApprover[0][3]=travelAppId; 
		 if(bean.getTrAppCanStatus().equals("true"))
         {
			 result= getSqlModel().singleExecute(getQuery(2), updateApprover);
         }else
         {
        	 result= getSqlModel().singleExecute(getQuery(6), updateApprover);
         }
		 
		
		// send mail for second approver 
		 try {
				Object[][] to_mailIds =new Object[1][1];	
				Object[][] from_mailIds =new Object[1][1];	
				 
			 	to_mailIds[0][0]= empFlow[0][0];
				from_mailIds[0][0]=travelEmpId;
				MailUtility mail=new MailUtility();
				mail.initiate(context, session);
				mail.sendMail(to_mailIds, from_mailIds,"Travel", "", "P"); 
				mail.terminate();
				 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
				 
			}   
				}
		else{
			            logger.info("inside first else");
						Object[][]statusChanged=new Object[1][2];
						statusChanged[0][0]="A";
						statusChanged[0][1]=travelAppId;
						
						 if(bean.getTrAppCanStatus().equals("true"))
				         {
							 result= getSqlModel().singleExecute(getQuery(3), statusChanged);
				         }else
				         {
				        	 result= getSqlModel().singleExecute(getQuery(5), statusChanged);
				         } 
						
						// FOR MAIL PURPOSE 
				try {
					 if(result)
						{
							String SqlSch = " SELECT TRAVEL_ADMIN_EMP_ID FROM HRMS_TRAVEL_ADMIN  WHERE TRAVEL_ADMIN_BRANCH_CODE ="
	                                        +" (SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID ="+travelEmpId+" )"; 
					 		Object[][] data = getSqlModel().getSingleResult(SqlSch);
					 		
					 		String approver =" SELECT TRAVEL_APPROVER FROM  HRMS_TRAVEL WHERE  TRAVEL_ID = "+travelAppId;
					 		Object[][] approverData = getSqlModel().getSingleResult(approver);
					 		if(data != null && data.length > 0)
					 		{
								Object[][] from_mailIds =new Object[1][1];	
								from_mailIds[0][0]=approverData[0][0];
								
								Object[][] to_mailIds =new Object[1][1];	 
								for(int k =0;k <data.length ;k++)
								{ 
								 	to_mailIds[0][0]= data[k][0]; 
								 	logger.info("to_mailIds[0][0]   >>>>>>>> "+to_mailIds[0][0]);
									MailUtility mail=new MailUtility();
									mail.initiate(context, session);
									mail.sendMail(to_mailIds, from_mailIds,"Travel", travelEmpId, "S"); 
									mail.terminate();
								}
								
					 		}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error(e.getMessage());
						 
					}   
		        }
		
		//collect(bean, bean.getStatus());
		return result;
		
} // end of changeApplStatus
	/*
	 *  This method is used for show the approver and its details.
	 */
	/**
	 * @param bean
	 */
	public void setApprover(TravelApprover bean) {
		String query = "  SELECT EMP_TOKEN,(EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '), "
						+"  TO_CHAR(APPR_DATE,'DD-MM-YYYY'),TRAVEL_REMARK    FROM HRMS_TRAVEL_PATH  "
						+"  LEFT JOIN HRMS_EMP_OFFC ON (HRMS_TRAVEL_PATH.APPROVER_CODE= HRMS_EMP_OFFC.EMP_ID) "
						+" LEFT JOIN HRMS_TITLE ON(HRMS_EMP_OFFC.EMP_TITLE_CODE=HRMS_TITLE.TITLE_CODE)"
				       + "  WHERE TRAVEL_ID = " + bean.getTravelAppId() +" ORDER BY APPR_DATE";
		Object apprData[][] = getSqlModel().getSingleResult(query);
		ArrayList<Object> apprList = new ArrayList<Object>();
		 
		
		try { } catch (Exception e) {
			 logger.error(e.getMessage());
		}
	} // end of setApprover
	/*
	 *  This method is used for show the journey details.
	 */
	/**
	 * @param bean
	 * @param travelAppId
	 */
	public void callDtl(TravelApprover bean,String travelAppId)
	 {
		String sql ="  SELECT  L1.LOCATION_NAME,L2.LOCATION_NAME, JOURNEY_NAME,JOURNEY_CLASS_NAME, "
				    +"  TO_CHAR(TRAVELDTL_JOURNEY_DATE,'DD-MM-YYYY'),NVL(TRAVELDTL_JOURNEY_TIME ,' '), TRAVELDTL_FROM_PLACE, "
					+"  TRAVELDTL_TO_PLACE ,TRAVELDTL_JOURNEY_MODE, TRAVELDTL_JOURNEY_CLASS  FROM HRMS_TRAVEL_DTL  "
					+"  INNER JOIN HRMS_LOCATION L1  ON(HRMS_TRAVEL_DTL.TRAVELDTL_FROM_PLACE = L1.LOCATION_CODE) "
					+"  INNER JOIN HRMS_LOCATION L2  ON(HRMS_TRAVEL_DTL.TRAVELDTL_TO_PLACE = L2.LOCATION_CODE) "
					+"  LEFT JOIN HRMS_TMS_JOURNEY ON(HRMS_TRAVEL_DTL.TRAVELDTL_JOURNEY_MODE =HRMS_TMS_JOURNEY.JOURNEY_ID) "
					+"  INNER JOIN HRMS_TMS_JOURNEY_CLASS ON(HRMS_TRAVEL_DTL.TRAVELDTL_JOURNEY_CLASS = HRMS_TMS_JOURNEY_CLASS.JOURNEY_CLASS_ID) "
					+"  WHERE TRAVELDTL_TRAVEL_ID ="+travelAppId;
		     Object [][] data = getSqlModel().getSingleResult(sql);
		
		    ArrayList<Object> jList = new ArrayList<Object>();   
		    if(data.length >0 && data != null)
		    {
       	   for(int i=0; i<data.length;i++)
       	      {
       		            TravelApprover bean1 = new TravelApprover();    //for previus item
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
	  		        	jList.add(bean1);
       	      } 
       	     bean.setTravelDtlList(jList);   
		    } // end of if
		    
		    
		    
	 }  // end of callDtl
	/*
	 *  This method is used for show the applicant details.
	 */
	/**
	 * @param bean
	 * @param travelAppId
	 */
	public void setApplication(TravelApprover bean ,String travelAppId)
	{
	   try
	   { 
		   bean.setTravelAppId(travelAppId); 
			String sql = "  SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , 	"
				 +" DEPT_NAME,RANK_NAME,TO_CHAR(TRAVEL_APPDATE,'DD-MM-YYYY') ,TRAVEL_STATUS ,TRAVEL_EMPID,  " 
				 +" NVL(TRAVEL_APPLICANT_COMMENT,' ')  ,CADRE_NAME,EMP_CADRE , CENTER_NAME , " 
				 +" DECODE ( TRAVEL_ID_PROOF ,'V','Voter Identity Card ','P','Passport','I',' PAN Card','D','Driving Licence ', "
				 +"	'G','Photo identity cards issued by Central/State Government' ) ,nvl(TRAVEL_PROOF_NO,' ') "
				 +" FROM HRMS_TRAVEL  "
				 +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_TRAVEL.TRAVEL_EMPID ) "				
				 +" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )  "
				 +" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
				 +" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) " 
				 +" LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
				 +"  WHERE TRAVEL_ID = "+travelAppId;
			
			Object [][] result = getSqlModel().getSingleResult(sql);
			if(result.length>0 && result!=null)
			{
			bean.setEmpToken(String.valueOf(result[0][0]));
			bean.setEName(String.valueOf(result[0][1]));
			bean.setDept(String.valueOf(result[0][2]));
			bean.setDesg(String.valueOf(result[0][3]));
			bean.setADate(String.valueOf(result[0][4]));
			bean.setStatus(String.valueOf(result[0][5]));
			bean.setEmpId(String.valueOf(result[0][6]));
			bean.setAppComment(String.valueOf(result[0][7])); 
			bean.setGradeName(String.valueOf(result[0][8]));
			bean.setGradeCode(String.valueOf(result[0][9]));
			bean.setBrName(String.valueOf(result[0][10])); 
			bean.setIdProof(String.valueOf(result[0][11]));
			bean.setIdNumber(String.valueOf(result[0][12]));
			
			}  // end of if
	   }catch (Exception e) {
		logger.error(e.getMessage());
	} 
		 
	}  // end of setApplication
	
	/**
	 * Method for display the applicable journey mode
	 * @param bean
	 */
	 public void generateExpense(TravelApprover bean)
		{ 
		 //== for display age,contact number
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
		  
		 //==============
		 
		 String modeSql =" SELECT DISTINCT JOURNEY_NAME,JOURNEY_CLASS_NAME ,TRAVEL_GRADE_OTHER_TRAVEL_COST"
			    +" , TRAVEL_GRADE_HOTEL_COST , TRAVEL_GRADE_OTHER_HOTEL_COST , TRAVEL_GRADE_POCKET_COST, TRAVEL_GRADE_FOOD_COST "
		 		+" FROM HRMS_TRAVEL_GRADE_DTL "
				+" LEFT JOIN HRMS_TMS_JOURNEY ON(HRMS_TMS_JOURNEY.JOURNEY_ID= HRMS_TRAVEL_GRADE_DTL.TRAVEL_GRADE_DTL_JOURNEY_ID) "
				+" LEFT JOIN HRMS_TMS_JOURNEY_CLASS ON(HRMS_TMS_JOURNEY_CLASS.JOURNEY_CLASS_ID= HRMS_TRAVEL_GRADE_DTL.TRAVEL_GRADE_DTL_JCLASS_ID)  "
				+" LEFT JOIN HRMS_TRAVEL_GRADE_HDR ON (HRMS_TRAVEL_GRADE_DTL.TRAVEL_GRADE_HDR_ID =  HRMS_TRAVEL_GRADE_HDR.TRAVEL_GRADE_HDR_ID) "
				+" WHERE  TRAVEL_GRADE_CODE ="+bean.getGradeCode()+" AND TRAVEL_GRADE_DTL_CLASS_FLAG='Y'";
		   Object [][] modeData= getSqlModel().getSingleResult(modeSql);    
		   
		  /*  if(modeData!=null && modeData.length>0)
		    {  bean.setAppDispFlag("false");
		    	String dupMode ="";
		    	for(int k=0;k <modeData.length;k++)
		    	 {   TravelApprover bean1 = new TravelApprover();
		    	     if(!dupMode.equals(""+modeData[k][0]))
		    	     {
		    		    bean1.setAppModeName(""+modeData[k][0]);
		    		    bean1.setAppSerial(""+count);
		    		    count++;
		    	     }else
		    	     {
		    	    	   bean1.setAppModeName(" ");
			    		    bean1.setAppSerial(" ");
		    	     }
		    		 bean1.setAppClassName(""+modeData[k][1]);
		    		 modeList.add(bean1);
		    		 dupMode=""+modeData[k][0];
		    	 }   
		    	 bean.setApplicantModeList(modeList);
		    }*/
		   
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
		 	bean.setTrOtherExp("Travel Miscellaneous Expenses :- "+modeData[0][2]);
			bean.setLodgAllowPerDay("Lodging allowance per Day :- "+modeData[0][3]);
			bean.setLodgOtherAllow("Lodging Miscellaneous allowance per day :- "+modeData[0][4]);
			bean.setOutPocketAllow("Out of pocket allowance per day :- "+modeData[0][5]);
			bean.setFoodAllow("Food allowance per day :-"+modeData[0][6]);
			bean.setCssClassTYpe("border2");
		    } else
		    {
		    	bean.setAppDispFlag("true");
		    }
	}
		 
	
	public void doPaging(TravelApprover bean, int empLength, Object[][] attnObj, HttpServletRequest request)
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
			
			/*String pagingSql = " SELECT CONF_RECORDS_PER_PAGE FROM HRMS_SALARY_CONF ";
			Object[][] pagingObj = getSqlModel().getSingleResult(pagingSql);*/
			int totalRec =20;// Integer.parseInt(String.valueOf(pagingObj[0][0]));
			
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
	
	public int getLevelNo(TravelApprover bean)
	{
		String levelSql="";
		if(bean.getTrAppCanStatus().equals("true"))
		{
			levelSql  = "SELECT TRAVEL_APP_LEVEL  FROM HRMS_TMS_TRAVEL_APP WHERE TRAVEL_APP_ID ="+bean.getTrAppId();
		}else
		{
			levelSql  = "SELECT TRAVEL_CANCEL_LEVEL  FROM HRMS_TMS_TRAVEL_APP WHERE TRAVEL_APP_ID ="+bean.getTrAppId();
		}
	 Object [][] data =getSqlModel().getSingleResult(levelSql);
		 if(data != null && data.length >0)
		 {
			return Integer.parseInt(""+data[0][0]);
		 }else
		 {
			 return 0;
		 } 
	}
	
	 public boolean callRequistionStatus(TravelApprover bean)
	 {   
		    String reqSql ="SELECT PROCESS_MANAGER_APL_APPR_REQ  FROM HRMS_TMS_PROCESS_MANAGER " ;
 		    Object [][] settleData = getSqlModel().getSingleResult(reqSql);
 		    
 		    if((""+settleData[0][0]).equals("Y"))
 		    {
 		    	return true;
 		    }else
 		    {
 		    	return false;
 		    }
	 }

}
