/**
 * 
 */
package org.paradyne.model.TravelManagement.TravelPlan;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.TravelPlan.TravelApplication;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;

import com.sun.mail.iap.Response;

/**
 * @author aa0417
 *
 */
public class TravelApplicationModel extends ModelBase { 
	/**
	 * @param empId
	 * @param bean
	 */
	public void getEmployeeDetails(String empId,TravelApplication bean)
	{
   try
   {
    String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , "
	 +"	NVL(CENTER_NAME,' '),NVL(DEPT_NAME,' '),NVL(RANK_NAME,' '),EMP_ID ,NVL(CADRE_NAME,' '),EMP_CADRE,NVL(TO_CHAR(EMP_DOB,'DD-MM-YYYY'),' ')  FROM HRMS_EMP_OFFC "
	 +"	LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
	 +"	INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
	 +"	INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
	 +"	INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
	 +" LEFT JOIN HRMS_CADRE ON (HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) " 
	 +" WHERE EMP_ID = "+empId;
		Object[][] empdata = getSqlModel().getSingleResult(query);
		if(empdata!=null && empdata.length>0)
		{
			bean.setEmpToken(String.valueOf(empdata[0][0]));
			bean.setEmpName(String.valueOf(empdata[0][1]));
			bean.setBranchName(String.valueOf(empdata[0][2]));
			bean.setDepartment(String.valueOf(empdata[0][3]));
			bean.setDesignation(String.valueOf(empdata[0][4]));
			bean.setEmpId(empId);
			bean.setEmpGrade(String.valueOf(empdata[0][6]));
			bean.setEmpGradeId(String.valueOf(empdata[0][7])); 
			bean.setEmpDob(String.valueOf(empdata[0][8])); 
		}
   }catch (Exception e) { 
 }
   
   try
   {
	   if(bean.getEmpDob()!=null && bean.getEmpDob().length() >0 )
		  {
			   String ageSql =" SELECT ROUND((TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY')-TO_DATE('"+bean.getEmpDob()+"','DD-MM-YYYY'))/365) AS AGE FROM DUAL";
			   Object [][] ageData = getSqlModel().getSingleResult(ageSql);  
					  if(ageData !=null && ageData.length >0)
					   {
						 try{  
						 bean.setEmpAge(""+ageData[0][0]);
						 System.out.println("bean.getEmpAge()"+bean.getEmpAge());
					           }catch (Exception e) {
						           e.printStackTrace();
					                }
					     } // end of if
		  }
		  
		  String conSql =" SELECT  NVL(ADD_MOBILE,' ') FROM HRMS_EMP_ADDRESS WHERE EMP_ID = "+bean.getEmpId();
			 Object [][] conData = getSqlModel().getSingleResult(conSql);  
			 
			 if(conData !=null && conData.length >0)
			 {
				 try{  
					 bean.setContactNumber(""+conData[0][0]); 
			    }catch (Exception e) {
			    	e.printStackTrace();
			   }
			 } 
			 
   }catch (Exception e) {
	// TODO: handle exception
}
	}  //end of getEmployeeDetails
	
	public void callForSettlementDate(TravelApplication bean)
	{
		 // for settlement days
		String PolSql ="SELECT POLICY_SETTELMENT_DAYS  FROM HRMS_TMS_TRAVEL_POLICY "
			+"	LEFT JOIN HRMS_TMS_POLICY_GRADE ON(HRMS_TMS_POLICY_GRADE.PG_POLICY_ID=HRMS_TMS_TRAVEL_POLICY.POLICY_ID) "
			+"	WHERE PG_EMP_GRADE ="+bean.getEmpGradeId()+"  AND (TO_DATE(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY')< TO_DATE(SYSDATE,'DD-MM-YYYY')) ORDER BY POLICY_EFFECTIVE_DATE DESC ";
 	   Object [][] data = getSqlModel().getSingleResult(PolSql);
 	   if(data!=null && data.length>0)
 	   {   
 		   bean.setSettleNumOfDay(""+data[0][0]);
 		   
 		   System.out.println("callForSettlementDate for PROCESS_MANAGER_EFFECT_DATE "+ bean.getSettleFlag());
 		   String setFlagSql ="SELECT PROCESS_MANAGER_EFFECT_DATE FROM HRMS_TMS_PROCESS_MANAGER " ;
 		   Object [][] settleData = getSqlModel().getSingleResult(setFlagSql);
 		    bean.setSettleFlag(""+settleData[0][0]); 
 	   }
	}
  
	public void callOnLOad(TravelApplication bean)
	{
		 
			 try {
				String today = "";
				Calendar now = Calendar.getInstance();
				int day = now.get(Calendar.DATE);
				int month = now.get(Calendar.MONTH);
				int year = now.get(Calendar.YEAR);
				String day1 = "" + day;
				String month1 = "" + (month + 1);
				if (day1.length() == 1) {
					day1 = "0" + day1;
				}
				if (month1.length() == 1) {
					month1 = "0" + month1;
				}
				today = day1 + "-" + (month1) + "-" + year;
				bean.setAppDate("" + today);
			} catch (Exception e) {

			}
		  
		/*String sql =" SELECT LOCATION_CODE, LOCATION_NAME FROM HRMS_TMS_CITY ORDER BY LOCATION_NAME";
		Object[][] data = getSqlModel().getSingleResult(sql);
		
		ArrayList city = new ArrayList();
		if(data!=null && data.length>0)
		{
			for(int i=0;i<data.length;i++)
			{  TravelApplication bean1 = new TravelApplication(); 
			    bean1.setCityName(""+data[i][1]);
				city.add(bean1);
			}
		 bean.setCityList(city);
		}*/
		 
	}
	
	 public void addTravelList(TravelApplication bean ,HttpServletRequest request)
	 {    
			 

		      // for travel  Details 
    		Object [] jourDate = request.getParameterValues("jourDate");
			Object [] jourTime = request.getParameterValues("jourTime");   
			        
			if(bean.getDelteArrFlag().equals("true"))
			{         ArrayList<Object> modeList = new ArrayList<Object>(); 
			     	   TravelApplication bean1 = new TravelApplication();   
	        			bean1.setJourFromPlace(""); 
	  		        	bean1.setJourToPlace(""); 
	  		        	bean1.setTrModeClass(""); 
	  		        	bean1.setJourDate(""); 
	  		        	bean1.setJourTime("");   
	  		      	 
	  		        	modeList.add(bean1);
	  		        	bean.setTrModeClassList(modeList);
	  			       
			         
			}else
			{ 
				 ArrayList<Object> modeList = new ArrayList<Object>(); 
			        if(jourDate!=null)
			        {  
			        	int j=1;
			        	for(int i=0; i<jourDate.length+1;i++)
			        	{ 
			        		if(i<jourDate.length)
			        		{ 
			        			TravelApplication bean1 = new TravelApplication();   
			        			
			        			    String jourFromPlace=  (String)request.getParameter("jourFromPlace"+j);
				  	        	    String jourFromPlaceId=  (String)request.getParameter("jourFromPlaceId"+j);  
				  	        	    request.setAttribute("jourFromPlace"+j,jourFromPlace);
		        			        request.setAttribute("jourFromPlaceId"+j,jourFromPlaceId);   

			        			    String jourToPlace=  (String)request.getParameter("jourToPlace"+j);
				  	        	    String jourToPlaceId=  (String)request.getParameter("jourToPlaceId"+j);  
				  	        	    request.setAttribute("jourToPlace"+j,jourToPlace);
		        			        request.setAttribute("jourToPlaceId"+j,jourToPlaceId);   
			  		        	
		  		        	    String trModeClass=  (String)request.getParameter("trModeClass"+j);
			  	        	    String trModeClassId=  (String)request.getParameter("trModeClassId"+j);  
			  	        	    request.setAttribute("trModeClass"+j,trModeClass);
	        			        request.setAttribute("trModeClassId"+j,trModeClassId);  
	        			        
			  		        	bean1.setJourDate(checkNull(""+jourDate[i]));  
			  		        	bean1.setJourTime(checkNull(""+jourTime[i]));  
			  		        	modeList.add(bean1); 
			  		        	j++;
			        		}else
			        		{
			        		  if(bean.getTrNoAddRow().equals("true"))
			        			{
			        			TravelApplication bean1 = new TravelApplication();    
			        			bean1.setJourFromPlace("");   
			  		        	bean1.setJourToPlace(""); 
			  		        	bean1.setTrModeClass(""); 
			  		        	bean1.setJourDate(""); 
			  		        	bean1.setJourTime("");  
			  		        	modeList.add(bean1);
			        			 } 
			        		} 
			        	}
			        	bean.setTrModeClassList(modeList);
			        }else
			        {
			        	TravelApplication bean1 = new TravelApplication();   
	        			bean1.setJourFromPlace(""); 
	  		        	bean1.setJourToPlace(""); 
	  		        	bean1.setTrModeClass(""); 
	  		        	bean1.setJourDate(""); 
	  		        	bean1.setJourTime("");      
	  		        	modeList.add(bean1);
	  		        	bean.setTrModeClassList(modeList);
			        } 
			}
	 } // end of addHoltelList
	 
	 
	  public void delTravelMode(TravelApplication bean ,HttpServletRequest request)
	  {   
		 
		    String [] hidHourChkBox =  request.getParameterValues("hidHourChkBox");   
			Object [] jourDate = request.getParameterValues("jourDate");
			Object [] jourTime = request.getParameterValues("jourTime");  
		    ArrayList<Object> modeList = new ArrayList<Object>();  
		    if(jourDate!=null)
		    {
		    	
		    	 int j=1;
				   for(int i=0; i<jourDate.length;i++)
			     	{ 
					   System.out.println("hidHourChkBox "+i+" for travel ========== "+hidHourChkBox[i]);
					   if(!hidHourChkBox[i].equals("Y"))
					      {  
						   TravelApplication bean1 = new TravelApplication();   
		        			
	        			    String jourFromPlace=  (String)request.getParameter("jourFromPlace"+j);
		  	        	    String jourFromPlaceId=  (String)request.getParameter("jourFromPlaceId"+j);  
		  	        	    request.setAttribute("jourFromPlace"+j,jourFromPlace);
       			            request.setAttribute("jourFromPlaceId"+j,jourFromPlaceId);   

	        			    String jourToPlace=  (String)request.getParameter("jourToPlace"+j);
		  	        	    String jourToPlaceId=  (String)request.getParameter("jourToPlaceId"+j);  
		  	        	    request.setAttribute("jourToPlace"+j,jourToPlace);
       			            request.setAttribute("jourToPlaceId"+j,jourToPlaceId);   
	  		        	
	 		        	    String trModeClass=  (String)request.getParameter("trModeClass"+j);
		  	        	    String trModeClassId=  (String)request.getParameter("trModeClassId"+j);  
		  	        	    request.setAttribute("trModeClass"+j,trModeClass);
	   			            request.setAttribute("trModeClassId"+j,trModeClassId);  
	   			        
		  		        	bean1.setJourDate(checkNull(""+jourDate[i]));  
		  		        	bean1.setJourTime(checkNull(""+jourTime[i]));  
		  		        	modeList.add(bean1);  
			        	 } 
					   j++;
					   } 
				  
		   		}  
				bean.setTrModeClassList(modeList);  
			 
	  }
	  
	  public void addLocalCon(TravelApplication bean ,HttpServletRequest request)
		 {    
			   
				Object [] localSource = request.getParameterValues("localSource");  
				Object [] localFromDate = request.getParameterValues("localFromDate");  
	    		Object [] localFromTime = request.getParameterValues("localFromTime");
				Object [] localToDate = request.getParameterValues("localToDate"); 
				Object [] localToTime = request.getParameterValues("localToTime"); 
				
				 ArrayList<Object> localconList = new ArrayList<Object>(); 
				      
				 if(bean.getDelteArrFlag().equals("true"))
					{ 
					  TravelApplication bean1 = new TravelApplication();   //for previus item
	        			bean1.setLocalCity("");   // for hotel Details
	  		        	bean1.setLocalSource(""); 
	  		        	bean1.setLocalFromDate(""); 
	  		        	bean1.setLocalFromTime("");
	  		        	bean1.setLocalToDate("");
	  		        	bean1.setLocalToTime(""); 
	  		        	localconList.add(bean1); 
	  		      	    bean.setLocalConList(localconList);
	  		       
					}
				 else
				 {
					 if(localSource!=null) 
				        {  
						 int j =1;
				        	for(int i=0; i<localSource.length+1;i++)
				        	{ 
				        		 
				        		if(i<localSource.length)
				        		{ 
				        			TravelApplication bean1 = new TravelApplication();
				        			    String localCity=  (String)request.getParameter("localCity"+j);
					  	        	    String localCityId=  (String)request.getParameter("localCityId"+j);  
					  	        	    request.setAttribute("localCity"+j,localCity);
			        			        request.setAttribute("localCityId"+j,localCityId);  
				        		 
				  		        	bean1.setLocalSource(""+localSource[i]);  
				  		        	bean1.setLocalFromDate(checkNull(""+localFromDate[i])); 
				  		        	bean1.setLocalFromTime(checkNull(""+localFromTime[i])); 
				  		        	bean1.setLocalToDate(checkNull(""+localToDate[i]));   
				  		        	bean1.setLocalToTime(checkNull(""+localToTime[i]));    
				  		        	localconList.add(bean1); 
				        		}else 
				        		{  
				        		 if(bean.getLocalNoAddRow().equals("true"))  
				        			{
				        			TravelApplication bean1 = new TravelApplication();   //for previus item
				        			bean1.setLocalCity("");   // for hotel Details
				  		        	bean1.setLocalSource(""); 
				  		        	bean1.setLocalFromDate(""); 
				  		        	bean1.setLocalFromTime("");
				  		        	bean1.setLocalToDate("");
				  		        	bean1.setLocalToTime(""); 
				  		        	localconList.add(bean1); 
				        			}
				        		}
				        		j++;
				        	}
				        	bean.setLocalConList(localconList);
				        }else
				        {
				        	   TravelApplication bean1 = new TravelApplication();   //for previus item
			        			bean1.setLocalCity("");   // for hotel Details
			  		        	bean1.setLocalSource(""); 
			  		        	bean1.setLocalFromDate(""); 
			  		        	bean1.setLocalFromTime("");
			  		        	bean1.setLocalToDate("");
			  		        	bean1.setLocalToTime("");
			  		        	//bean1.setModeMap(modeMap);
			  		        	localconList.add(bean1); 
			  		      	bean.setLocalConList(localconList);
				        } 
				 }
		 } // end of addHoltelList
	 
	  
	  
	  public void delLocalConv(TravelApplication bean ,HttpServletRequest request)
	  {   
		   
		    
			Object [] localSource = request.getParameterValues("localSource");  
			Object [] localFromDate = request.getParameterValues("localFromDate");  
    		Object [] localFromTime = request.getParameterValues("localFromTime");
			Object [] localToDate = request.getParameterValues("localToDate"); 
			Object [] localToTime = request.getParameterValues("localToTime"); 
			 String [] hidLocalChkBox =  request.getParameterValues("hidLocalChkBox");  
			
			 ArrayList<Object> localconList = new ArrayList<Object>(); 
			        if(localSource!=null)
			        {  
			        	int j=1;
			        	for(int i=0; i<localSource.length;i++)
			        	{ 
			        		 if(!hidLocalChkBox[i].equals("Y"))
						      {
			        			    TravelApplication bean1 = new TravelApplication();
			        			    String localCity=  (String)request.getParameter("localCity"+j);
				  	        	    String localCityId=  (String)request.getParameter("localCityId"+j);  
				  	        	    request.setAttribute("localCity"+j,localCity);
		        			        request.setAttribute("localCityId"+j,localCityId);  
			        		 
			  		        	bean1.setLocalSource(""+localSource[i]);  
			  		        	bean1.setLocalFromDate(checkNull(""+localFromDate[i])); 
			  		        	bean1.setLocalFromTime(checkNull(""+localFromTime[i])); 
			  		        	bean1.setLocalToDate(checkNull(""+localToDate[i]));   
			  		        	bean1.setLocalToTime(checkNull(""+localToTime[i]));   
			  		        	localconList.add(bean1); 
						      }
			        		 j++;
			        	}
			        	bean.setLocalConList(localconList);
			        }
	  }
	  
	  // for lodging
		 public void addLodgList(TravelApplication bean ,HttpServletRequest request)
		 {     
			 // for hotel Details
				 
			  
	    		Object [] lodgLocation = request.getParameterValues("lodgLocation");
				Object [] lodgFromdate = request.getParameterValues("lodgFromdate");  
				Object [] lodgFromtime = request.getParameterValues("lodgFromtime");  
	    		Object [] lodgTodate = request.getParameterValues("lodgTodate");
				Object [] lodgTotime = request.getParameterValues("lodgTotime"); 
				
				 ArrayList<Object> lodgList = new ArrayList<Object>(); 
				 if(bean.getDelteArrFlag().equals("true"))
					{
					 TravelApplication bean1 = new TravelApplication();   //for previus item
	        			bean1.setHotelType("");   // for hotel Details
	  		        	bean1.setRoomType(""); 
	  		        	bean1.setLodgCity(""); 
	  		        	bean1.setLodgLocation(""); 
	  		        	bean1.setLodgFromdate("");  
	  		        	bean1.setLodgFromtime(""); 
	  		        	bean1.setLodgTodate(""); 
	  		        	bean1.setLodgTotime("");   
	  		        	lodgList.add(bean1);
		        	    bean.setLodgList(lodgList);
		        	    
					}else
					{
				        if(lodgLocation!=null)
				        {  
				        	 int j=1;
				        	for(int i=0; i<lodgLocation.length+1;i++)
				        	{ 
				        		if(i<lodgLocation.length)
				        		{  
				        			TravelApplication bean1 = new TravelApplication();   //for previus item
				        			  // for hotel Details 
				        			
				        			    String lodgCity=  (String)request.getParameter("lodgCity"+j);
					  	        	    String lodgCityId=  (String)request.getParameter("lodgCityId"+j);  
					  	        	    request.setAttribute("lodgCity"+j,lodgCity);
			        			        request.setAttribute("lodgCityId"+j,lodgCityId);   
			        			        
				  		        	bean1.setLodgLocation(checkNull(""+lodgLocation[i])); 
				  		        	bean1.setLodgFromdate(checkNull(""+lodgFromdate[i]));  
				  		        	bean1.setLodgFromtime(checkNull(""+lodgFromtime[i])); 
				  		        	bean1.setLodgTodate(checkNull(""+lodgTodate[i])); 
				  		        	bean1.setLodgTotime(checkNull(""+lodgTotime[i]));  
				  		        	
				  		        	
				  		            String hotelType=  (String)request.getParameter("hotelType"+j);
				  	        	    String hotelTypeId=  (String)request.getParameter("hotelTypeId"+j);  
				  	        	    request.setAttribute("hotelType"+j,hotelType);
		        			        request.setAttribute("hotelTypeId"+j,hotelTypeId);  
		        			        
		        			        String roomType=  (String)request.getParameter("roomType"+j);
				  	        	    String roomTypeId=  (String)request.getParameter("roomTypeId"+j);  
				  	        	    request.setAttribute("roomType"+j,roomType);
		        			        request.setAttribute("roomTypeId"+j,roomTypeId);   
				  		        	lodgList.add(bean1); 
				  		        	j++;
				        		}else   
				        		{
				        		  if(bean.getLodgNoAddRow().equals("true"))
				        			{
				        			   TravelApplication bean1 = new TravelApplication();   //for previus item
					        			bean1.setHotelType("");   // for hotel Details
					  		        	bean1.setRoomType(""); 
					  		        	bean1.setLodgCity(""); 
					  		        	bean1.setLodgLocation(""); 
					  		        	bean1.setLodgFromdate("");  
					  		        	bean1.setLodgFromtime(""); 
					  		        	bean1.setLodgTodate(""); 
					  		        	bean1.setLodgTotime("");   
					  		        	lodgList.add(bean1);
				        			 } 
				        		}
				        	}
				        	bean.setLodgList(lodgList);
				        }else
				        {   System.out.println("for Blank row ======== ");   
				        	TravelApplication bean1 = new TravelApplication();   //for previus item
			        			bean1.setHotelType("");   // for hotel Details
			  		        	bean1.setRoomType(""); 
			  		        	bean1.setLodgCity(""); 
			  		        	bean1.setLodgLocation(""); 
			  		        	bean1.setLodgFromdate("");  
			  		        	bean1.setLodgFromtime(""); 
			  		        	bean1.setLodgTodate(""); 
			  		        	bean1.setLodgTotime("");   
			  		        	lodgList.add(bean1);
		  		        	bean.setLodgList(lodgList);
				        } 
					}
		 } // end of addHoltelList
		 
		 
		 
		 public void delLodg(TravelApplication bean ,HttpServletRequest request)
		 {    
			 
				//           
			      
				 
	    		Object [] lodgLocation = request.getParameterValues("lodgLocation");
				Object [] lodgFromdate = request.getParameterValues("lodgFromdate");  
				Object [] lodgFromtime = request.getParameterValues("lodgFromtime");  
	    		Object [] lodgTodate = request.getParameterValues("lodgTodate");
				Object [] lodgTotime = request.getParameterValues("lodgTotime"); 
				Object [] hidLodgChkBox = request.getParameterValues("hidLodgChkBox"); 
				
				 ArrayList<Object> lodgList = new ArrayList<Object>(); 
				        if(lodgLocation!=null)
				        {  
				        	int j=1;
				        	for(int i=0; i<lodgLocation.length;i++)
				        	{ 
				        		 if(!hidLodgChkBox[i].equals("Y"))
							      {
				        			 TravelApplication bean1 = new TravelApplication();
				        			  String lodgCity=  (String)request.getParameter("lodgCity"+j);
					  	        	  String lodgCityId=  (String)request.getParameter("lodgCityId"+j);  
					  	        	  request.setAttribute("lodgCity"+j,lodgCity);
			        			      request.setAttribute("lodgCityId"+j,lodgCityId);   
			        			        
				  		        	bean1.setLodgLocation(checkNull(""+lodgLocation[i])); 
				  		        	bean1.setLodgFromdate(checkNull(""+lodgFromdate[i]));  
				  		        	bean1.setLodgFromtime(checkNull(""+lodgFromtime[i])); 
				  		        	bean1.setLodgTodate(checkNull(""+lodgTodate[i])); 
				  		        	bean1.setLodgTotime(checkNull(""+lodgTotime[i]));  
				  		        	
				  		        	
				  		            String hotelType=  (String)request.getParameter("hotelType"+j);
				  	        	    String hotelTypeId=  (String)request.getParameter("hotelTypeId"+j);  
				  	        	    request.setAttribute("hotelType"+j,hotelType);
		        			        request.setAttribute("hotelTypeId"+j,hotelTypeId);  
		        			        
		        			        String roomType=  (String)request.getParameter("roomType"+j);
				  	        	    String roomTypeId=  (String)request.getParameter("roomTypeId"+j);  
				  	        	    request.setAttribute("roomType"+j,roomType);
		        			        request.setAttribute("roomTypeId"+j,roomTypeId);   
				  		        	lodgList.add(bean1);    
							      }
				        		 j++;				        		
				        	}
				        	bean.setLodgList(lodgList);
				        } 
		 } // end of addHoltelList
		 
		 //     TRAVEL_APP_IDNUMBER, TRAVEL_APP_COMMENTS, TRAVEL_APP_LEVEL, 
		 //  TRAVEL_APP_APPROVER, TRAVEL_APP_ALTER_APPROVER) 
		 public boolean sendForApprovalSave (TravelApplication bean ,HttpServletRequest request,Object[][] empFlow)
		 {
			
			 Object[][] addobj = new Object[1][25];
			 addobj[0][0]=bean.getEmpId();  
			 addobj[0][1]=bean.getAppDate();  
			 addobj[0][2]="P";  
			 addobj[0][3]=bean.getTravelRequest();  
			 addobj[0][4]=bean.getAppFor();  
			 addobj[0][5]=bean.getGuestName(); 
			 addobj[0][6]=bean.getTravelPurposeId();  
			 addobj[0][7]=bean.getAccommodationReq();  
			 addobj[0][8]=bean.getTrArrg();  
			 addobj[0][9]=bean.getLocalConReq();  
			 addobj[0][10]=bean.getTourLocTypeId();  
			 addobj[0][11]=bean.getTourStartDate();  
			 addobj[0][12]=bean.getTourEndDate();
			 addobj[0][13]=bean.getAdvanceAmt().equals("")?"0":bean.getAdvanceAmt();
			 addobj[0][14]=bean.getPayMode();			 
			 addobj[0][15]=bean.getSettleDate(); 
			 addobj[0][16]=bean.getIdProof();  			 
			 addobj[0][17]=bean.getIdProofNumber();  		 
			 addobj[0][18]=bean.getAppComments();  
			 addobj[0][19]=String.valueOf(empFlow[0][0]);  			 
			 addobj[0][20]=String.valueOf(empFlow[0][3]);
			 addobj[0][21]=bean.getFoodTypeId().equals("")?"0":bean.getFoodTypeId();
			 addobj[0][22]=String.valueOf(empFlow[0][0]);  			 
			 addobj[0][23]=String.valueOf(empFlow[0][3]);
			 addobj[0][24]=bean.getPolicyId().equals("")?"0":bean.getPolicyId();
             boolean flag= getSqlModel().singleExecute(getQuery(1), addobj);  
             
             String sql =" SELECT MAX(TRAVEL_APP_ID)  FROM HRMS_TMS_TRAVEL_APP";
             Object[][] appData= getSqlModel().getSingleResult(sql);
             if(appData != null && appData.length>0)
             {
            	 bean.setTrAppId(""+appData[0][0]);
             }
             saveTravelMode (bean ,request);
             if(bean.getLocalConReq().equals("C"))
             {
             saveLocalCon (bean ,request);
             }
             System.out.println("bean.getAccommodationReq()========= "+bean.getAccommodationReq());
             if(bean.getAccommodationReq().equals("C"))
             {
                savLodging (bean ,request); 
             } 
     			try {
     				Object[][] to_mailIds =new Object[1][1];	
     				Object[][] from_mailIds =new Object[1][1];	
     				
     			 	to_mailIds[0][0]=String.valueOf(empFlow[0][0]);
     				from_mailIds[0][0]=bean.getEmpId(); 
     				MailUtility mail=new MailUtility();
     				mail.initiate(context, session);
     				mail.sendMail(to_mailIds, from_mailIds,"Travel", "", "P"); 
     				System.out.println("mail sent ============= ");
     				mail.terminate();
     				 
     			} catch (Exception e) { 
     				 
     			} 
     	   
             return flag; 
		 }
		 
		 public boolean saveTravelMode (TravelApplication bean ,HttpServletRequest request)
		 {  
	    		String [] jourDate = request.getParameterValues("jourDate");
	    		String [] jourTime = request.getParameterValues("jourTime"); 
				boolean trFlag =false;
				String trackEmpId="0";
				if(bean.getApproverPanelFlag().equals("true"))
				{System.out.println("in approver");
					trackEmpId= bean.getUserEmpId();
				}else
				{
					if(bean.isGeneralFlag()==true)
					{
						System.out.println("in genral");
						trackEmpId= bean.getUserEmpId();
					}else
					{
						System.out.println("in not genral");
						trackEmpId= bean.getEmpId();
					}
				}
			if(jourDate !=null)
			{ int j=1;
				for(int i=0; i<jourDate.length;i++)
	        	{ 
					 
					  String trModeClassId = request.getParameter("trModeClassId"+j);  
	  	        	  String jourFromPlaceId=  (String)request.getParameter("jourFromPlaceId"+j);  
	  	        	  String jourToPlaceId=  (String)request.getParameter("jourToPlaceId"+j);   
					 if(trModeClassId==null || trModeClassId.equals("null")|| trModeClassId.equals("")) { trModeClassId="0"; }
					 if(jourFromPlaceId==null || jourFromPlaceId.equals("null") || jourFromPlaceId.equals("")) { jourFromPlaceId="0"; }
					 if(jourToPlaceId==null || jourToPlaceId.equals("null")|| jourToPlaceId.equals("")) { jourToPlaceId="0"; }
				   String trSql ="INSERT INTO HRMS_TMS_APPJOUR_DTL ( APPJOUR_DTL_ID, APPJOUR_DTL_FROMPALCE, APPJOUR_DTL_TOPLACE, "
						 		 +" APPJOUR_DTL_JOURMODE, APPJOUR_DTL_JOURDATE, APPJOUR_DTL_JOURTIME,  APPJOUR_DTL_TRAPPID) "
	                             +" VALUES ((SELECT  NVL(MAX(APPJOUR_DTL_ID),0)+1 FROM HRMS_TMS_APPJOUR_DTL),"+jourFromPlaceId.trim()+" ,"+jourToPlaceId.trim()+" ,"  
	                             +""+trModeClassId.trim()+",TO_DATE('"+jourDate[i].trim()+"','DD-MM-YYYY') ,'"+jourTime[i].trim()+"' ,";
					 if(bean.getTrAppId().equals(""))
					 {
						 trSql+="(SELECT NVL(MAX(TRAVEL_APP_ID),0) FROM HRMS_TMS_TRAVEL_APP) )";
					 }else
					 {
						 trSql+= bean.getTrAppId()+")";
					 }
		         	  trFlag= getSqlModel().singleExecute(trSql);  
		         	//============for track============
		         	try
		         	{
			            String trSqlForTrack ="INSERT INTO HRMS_TMS_APPJOUR_DTL_TRACK ( APPJOUR_DTL_ID, APPJOUR_DTL_FROMPALCE, APPJOUR_DTL_TOPLACE, "
					 		 +" APPJOUR_DTL_JOURMODE, APPJOUR_DTL_JOURDATE, APPJOUR_DTL_JOURTIME, APPJOUR_DTL_MODIFIED_BY, APPJOUR_DTL_TRAPPID) "
		                     +" VALUES ((SELECT  NVL(MAX(APPJOUR_DTL_ID),0)+1 FROM HRMS_TMS_APPJOUR_DTL_TRACK),"+jourFromPlaceId.trim()+" ,"+jourToPlaceId.trim()+" ,"  
		                     +""+trModeClassId.trim()+",TO_DATE('"+jourDate[i].trim()+"','DD-MM-YYYY') ,'"+checkNull(""+jourTime[i])+"' ,"+bean.getEmpId()+",";
						 if(bean.getTrAppId().equals(""))
						 {
							 trSqlForTrack+="(SELECT NVL(MAX(TRAVEL_APP_ID),0) FROM HRMS_TMS_TRAVEL_APP) )";
						 }else
						 {
							 trSqlForTrack+= bean.getTrAppId()+")";
						 }
				     	 getSqlModel().singleExecute(trSqlForTrack); 
		         	}catch (Exception e) {
		         		 
					}
		         	j++; 
	        	}// end of for
				return trFlag ;
			}
			 return true;
		 } // end of save
		 
		 // returns the city id
		 public String CityId(String cityName)
		 {
			 String citySql ="SELECT LOCATION_CODE FROM HRMS_LOCATION WHERE LOCATION_NAME='"+cityName+"'" ;
			  Object[][] cityData =getSqlModel().getSingleResult(citySql);
			  if(cityData!=null && cityData.length>0)
			  {
				  return ""+cityData[0][0];  
			  }else
			  {
			  String addCity="INSERT INTO HRMS_LOCATION (LOCATION_CODE, LOCATION_NAME) "
					  		+" VALUES ((SELECT NVL(MAX(LOCATION_CODE),0)+1 FROM HRMS_LOCATION) ,'"+cityName+"')"; 
			  getSqlModel().singleExecute(addCity); 
			  String getCitySql ="SELECT MAX(LOCATION_CODE) FROM HRMS_LOCATION " ;
			  Object[][] cityIdData =getSqlModel().getSingleResult(getCitySql);
				  return ""+cityIdData[0][0];  
			  } 
		 }
		 
		 
		 public boolean saveLocalCon (TravelApplication bean ,HttpServletRequest request)
		 {
			  
				String [] localSource = request.getParameterValues("localSource");  
				String [] localFromDate = request.getParameterValues("localFromDate");  
				String [] localFromTime = request.getParameterValues("localFromTime");
				String [] localToDate = request.getParameterValues("localToDate"); 
				String [] localToTime = request.getParameterValues("localToTime"); 
				boolean locFlag=false;
				String trackEmpId="0";
				if(bean.getApprflag().equals("true"))
				{
					trackEmpId= bean.getUserEmpId();
				}else
				{
					if(bean.isGeneralFlag()==true)
					{
						trackEmpId= bean.getUserEmpId();
					}else
					{
						trackEmpId= bean.getEmpId();
					}
				}
				
			  if(localSource !=null)
			  { int j=1;
		         	for(int i=0; i<localSource.length;i++)
		        	{ 
		         		 String localCityId=  (String)request.getParameter("localCityId"+j);  
		         		if(localCityId==null || localCityId.equals("null") || localCityId.equals("")) { localCityId="0"; }
		     		// String city=CityId(""+localCity[i]);
		             String localCon ="INSERT INTO HRMS_TMS_APPLOCAL_DTL (APPLOCAL_DTL_ID, APPLOCAL_DTL_CITY, " 
			 				 +"APPLOCAL_DTL_SOURCE,  APPLOCAL_DTL_FROM_DATE, APPLOCAL_DTL_FROM_TIME," 
			 				 +" APPLOCAL_DTL_TO_DATE, APPLOCAL_DTL_TO_TIME, APPLOCAL_DTL_TRAPPID) "
						     +" VALUES ((SELECT  NVL(MAX(APPLOCAL_DTL_ID),0)+1 FROM HRMS_TMS_APPLOCAL_DTL ),"+localCityId+" ,'" +
						     ""+localSource[i].trim()+"',TO_DATE('"+localFromDate[i].trim()+"','DD-MM-YYYY') ,'"+localFromTime[i].trim()+"' ," +
						     "TO_DATE('"+localToDate[i].trim()+"','DD-MM-YYYY'),'"+localToTime[i].trim()+"' ,";
			       
				       if(bean.getTrAppId().equals(""))
						 {
				    	   localCon+="(SELECT NVL(MAX(TRAVEL_APP_ID),0) FROM HRMS_TMS_TRAVEL_APP) )";
						 }else
						 {
							 localCon+= bean.getTrAppId()+")";
						 }
			      	  locFlag = getSqlModel().singleExecute(localCon);
			      	
			      	//================ for track==============
			      	try
			      	{
				      	 String localConTrack ="INSERT INTO HRMS_TMS_APPLOCAL_DTL_TRACK (APPLOCAL_DTL_ID, APPLOCAL_DTL_CITY, " 
			 				 +"APPLOCAL_DTL_SOURCE,  APPLOCAL_DTL_FROM_DATE, APPLOCAL_DTL_FROM_TIME," 
			 				 +" APPLOCAL_DTL_TO_DATE, APPLOCAL_DTL_TO_TIME,APPLOCAL_DTL_MODIFIED_BY, APPLOCAL_DTL_TRAPPID) "
						     +" VALUES ((SELECT  NVL(MAX(APPLOCAL_DTL_ID),0)+1 FROM HRMS_TMS_APPLOCAL_DTL_TRACK ),"+localCityId.trim()+" ,'" +
						     ""+localSource[i].trim()+"',TO_DATE('"+localFromDate[i].trim()+"','DD-MM-YYYY') ,'"+localFromTime[i].trim()+"' ," +
						     "TO_DATE('"+localToDate[i].trim()+"','DD-MM-YYYY'),'"+localToTime[i].trim()+"' ,"+bean.getUserEmpId()+",";
			       
				       if(bean.getTrAppId().equals(""))
						 {
				    	   localConTrack+="(SELECT NVL(MAX(TRAVEL_APP_ID),0) FROM HRMS_TMS_TRAVEL_APP) )";
						 }else
						 {
							 localConTrack+= bean.getTrAppId()+")";
						 }
			                  getSqlModel().singleExecute(localConTrack);
			      	}catch (Exception e) {
						return true;
					} 
			      	j++;
	        	}
		      return locFlag;   	
			}else
			{
				return true;
			}
			 
		 }
		 
		 
		 public boolean savLodging (TravelApplication bean ,HttpServletRequest request)
		 {
			  System.out.println("in savLodging========= ");
			      // for hotel Details 
			   String trackEmpId="0";
				if(bean.getApprflag().equals("true"))
				{
					trackEmpId= bean.getUserEmpId();
				}else
				{
					if(bean.isGeneralFlag()==true)
					{
						trackEmpId= bean.getUserEmpId();
					}else
					{
						trackEmpId= bean.getEmpId();
					}
				}
				 
	    		String [] lodgLocation = request.getParameterValues("lodgLocation");
	    		String [] lodgFromdate = request.getParameterValues("lodgFromdate");  
	    		String [] lodgFromtime = request.getParameterValues("lodgFromtime");  
	    		String [] lodgTodate = request.getParameterValues("lodgTodate");
	    		String [] lodgTotime = request.getParameterValues("lodgTotime");  
				boolean lodgingFlag = false;
				if(lodgLocation!=null)
				{
					int j =1;
		        	for(int i=0; i<lodgLocation.length;i++)
		        	{ 
		        		String hotelTypeId = request.getParameter("hotelTypeId"+j);
		        		String roomTypeId = request.getParameter("roomTypeId"+j);
		        		 String lodgCityId=  (String)request.getParameter("lodgCityId"+j);
		        		if(hotelTypeId==null || hotelTypeId.equals("null")|| hotelTypeId.equals("")) { hotelTypeId="0"; }
		        		if(roomTypeId==null || roomTypeId.equals("null")|| roomTypeId.equals("")) { roomTypeId="0"; }
		        		if(lodgCityId==null || lodgCityId.equals("null")|| lodgCityId.equals("")) { lodgCityId="0"; }
		        		System.out.println("lodgCityId=========== "+lodgCityId);
		        		
		        // String city=CityId(""+lodgCity[i]); 
		         String lodgSql ="INSERT INTO HRMS_TMS_APPLODG_DTL ( APPLODG_DTL_ID, APPLODG_DTL_HOTEL, APPLODG_DTL_ROOM, "
							+" APPLODG_DTL_CITY, APPLODG_DTL_LOCATION, APPLODG_DTL_FROMDATE,  APPLODG_DTL_FROMTIME, "
							+" APPLODG_DTL_TODATE, APPLODG_DTL_TOTIME, APPLODG_DTL_TRAPPID)  "
							+" VALUES ((SELECT  NVL(MAX(APPLODG_DTL_ID),0)+1 FROM HRMS_TMS_APPLODG_DTL),"+hotelTypeId.trim()+"," 
							+""+roomTypeId.trim()+","+lodgCityId.trim()+" ,'"+lodgLocation[i].trim()+"',TO_DATE('"+lodgFromdate[i].trim()+"','DD-MM-YYYY') ,'" 
							+""+lodgFromtime[i].trim()+"',TO_DATE('"+lodgTodate[i].trim()+"','DD-MM-YYYY') ,'"+lodgTotime[i].trim()+"',";
				        if(bean.getTrAppId().equals(""))
						 {
				        	lodgSql+="(SELECT NVL(MAX(TRAVEL_APP_ID),0) FROM HRMS_TMS_TRAVEL_APP) )";
						 }else
						 {
							 lodgSql+= bean.getTrAppId()+")";
						 }
		               lodgingFlag = getSqlModel().singleExecute(lodgSql);
		              
		              // ===========for track==============
		              try
		              {
		              String lodgSqlTrack ="INSERT INTO HRMS_TMS_APPLODG_DTL_TRACK ( APPLODG_DTL_ID, APPLODG_DTL_HOTEL, APPLODG_DTL_ROOM, "
							+" APPLODG_DTL_CITY, APPLODG_DTL_LOCATION, APPLODG_DTL_FROMDATE,  APPLODG_DTL_FROMTIME, "
							+" APPLODG_DTL_TODATE, APPLODG_DTL_TOTIME,APPLOCAL_DTL_MODIFIED_BY, APPLODG_DTL_TRAPPID)  "
							+" VALUES ((SELECT  NVL(MAX(APPLODG_DTL_ID),0)+1 FROM HRMS_TMS_APPLODG_DTL_TRACK),"+hotelTypeId.trim()+"," 
							+""+roomTypeId.trim()+","+lodgCityId.trim()+" ,'"+lodgLocation[i].trim()+"',TO_DATE('"+lodgFromdate[i].trim()+"','DD-MM-YYYY') ,'" 
							+""+lodgFromtime[i].trim()+"',TO_DATE('"+lodgTodate[i].trim()+"','DD-MM-YYYY') ,'"+lodgTotime[i].trim()+"',"+bean.getUserEmpId()+",";
				        if(bean.getTrAppId().equals(""))
						 {
				        	lodgSqlTrack+="(SELECT NVL(MAX(TRAVEL_APP_ID),0) FROM HRMS_TMS_TRAVEL_APP) )";
						 }else
						 {
							 lodgSqlTrack+= bean.getTrAppId()+")";
						 }
		                getSqlModel().singleExecute(lodgSqlTrack); 
		              }catch (Exception e) {
						return true;
					 }
		              j++;
		        	 }
		        	 return lodgingFlag; 
				}else
				{
					return true;
				} 
		 }
			 
		
	 public void callAppDtl(TravelApplication bean ,HttpServletRequest request)
		 {
		 
		 bean.setTrNoAddRow("false");
		 bean.setLocalNoAddRow("false");
		 bean.setLodgNoAddRow("false");
		 
		String appSql ="SELECT NVL(TRAVEL_APP_APPFOR,' '), NVL(TRAVEL_APP_GUEST,''), NVL(PURPOSE_NAME,' '),TRAVEL_APP_PURPOSE, TRAVEL_APP_ACCOMMODATION, "
		+" TRAVEL_APP_TRAVEL_ARRG, TRAVEL_APP_LOCAL_ALLOW,NVL(LOCATION_TYPE_NAME,' '),TRAVEL_APP_LOCATION_TYPE, TO_CHAR(TRAVEL_APP_START_DATE,'DD-MM-YYYY'), "
		+" TO_CHAR(TRAVEL_APP_END_DATE,'DD-MM-YYYY'), TRAVEL_APP_ADVANCE, TRAVEL_APP_PAYMODE, TO_CHAR(TRAVEL_APP_SETTLE_DATE,'DD-MM-YYYY'),  "
		+" TRAVEL_APP_IDPROOF,NVL(TRAVEL_APP_IDNUMBER,' '),NVL(TRAVEL_APP_COMMENTS,' ') ,TRAVEL_APP_EMPID ," ;
		
		  if(bean.getTrAppCanStatus().equals("true"))
	         {
				 appSql +=" TRAVEL_APP_STATUS ,DECODE(TRAVEL_APP_STATUS,'N','New','P','Pending','A','Approved','R','Rejected'),";
	         }else
	         {
	        	 appSql +=" TRAVEL_CANCEL_STATUS ,DECODE(TRAVEL_CANCEL_STATUS, 'P','Pending','A','Approved','R','Rejected'),";
	          }  
		 appSql +=" DECODE (TRAVEL_APP_IDPROOF,'V','Voter Identity Card ','P','Passport','I',' PAN Card','D','Driving Licence ','G','Photo identity cards issued by Central/State Government'), "  
	    +" DECODE (TRAVEL_APP_PAYMODE,'C','Cash','Q','Cheque','S','Salary','T','Transfer') , " 
	    +" NVL(TRAVEL_APP_FOOD_TYPE,0),NVL(FOOD_TYPE_NAME,' '),NVL(TRAVEL_APP_REQUEST,' '),TRAVEL_APP_POLICY_ID ,TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY') , ";
	    
	    if(bean.getTrAppCanStatus().equals("true"))
        {
	    	appSql +=" DECODE(TRAVEL_APP_STATUS, 'N','New','P','Pending','A','Approved','R','Rejected')";
        }else
        {
        	appSql +=" DECODE(TRAVEL_CANCEL_STATUS,'P','Pending','A','Approved','R','Rejected')";
         } 
	    
	    appSql += "  FROM HRMS_TMS_TRAVEL_APP "
		+" LEFT JOIN HRMS_TMS_PURPOSE ON (HRMS_TMS_PURPOSE.PURPOSE_ID =TRAVEL_APP_PURPOSE) "
		+" LEFT JOIN HRMS_TMS_LOCATION_TYPE ON (HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID =TRAVEL_APP_LOCATION_TYPE) "
		+" LEFT JOIN HRMS_TMS_FOOD_TYPE ON (HRMS_TMS_FOOD_TYPE.FOOD_TYPE_ID =TRAVEL_APP_FOOD_TYPE) "
		+" WHERE TRAVEL_APP_ID="+bean.getTrAppId();  
		 Object[][] appData =getSqlModel().getSingleResult(appSql);
		 if(appData!=null && appData.length>0)
			 if(appData!=null && appData.length>0)
			 { 
				 
				 if((""+appData[0][0]).equals("G"))
				 {
					 bean.setGuestAppFlag("true");
					 bean.setHidAppForRadio("Y");
				 }else
				 {
					 bean.setGuestAppFlag("false");
					 bean.setHidAppForRadio("N");
				 }
				bean.setAppFor(""+appData[0][0]); 
				bean.setGuestName(checkNull(""+appData[0][1])); 
				bean.setTravelPurpose(""+appData[0][2]); 
				bean.setTravelPurposeId(""+appData[0][3]); 
				 if((""+appData[0][4]).equals("C"))
				 {
					 bean.setAccomFlag("true");
					 bean.setHidAccommodationReqFlag("Y");
				 }else
				 {
					 bean.setAccomFlag("false"); 
					 bean.setHidAccommodationReqFlag("N");
				 }
				bean.setAccommodationReq(""+appData[0][4]); 
				 if((""+appData[0][5]).equals("C"))
				 {
					 bean.setTrArFlag("true");
					 bean.setHidtrArrgFlag("Y");
				 }else
				 {
					 bean.setTrArFlag("false");
					 bean.setHidtrArrgFlag("N");
				 }
				bean.setTrArrg(""+appData[0][5]);
				 if((""+appData[0][6]).equals("C"))
				 {
					 bean.setHidLocalConReq("Y");
					 bean.setLocalFlag("true");
				 }else
				 {
					 bean.setHidLocalConReq("N");
					 bean.setLocalFlag("false");
				 }
				bean.setLocalConReq(""+appData[0][6]);
				bean.setTourLocType(""+appData[0][7]);
				bean.setTourLocTypeId(""+appData[0][8]);
				bean.setTourStartDate(""+appData[0][9]);
				bean.setTourEndDate(""+appData[0][10]);
				bean.setAdvanceAmt(checkNull(""+appData[0][11]));
				bean.setPayMode(""+appData[0][12]);
				bean.setSettleDate(""+appData[0][13]);
				bean.setIdProof(""+appData[0][14]);
				bean.setIdProofNumber(checkNull(""+appData[0][15]));
				bean.setAppComments(checkNull(""+appData[0][16]));
				bean.setEmpId(""+appData[0][17]);
				bean.setAppStatus(""+appData[0][18]);
				bean.setDisAppStatus(checkNull(""+appData[0][19]));
				bean.setDisIdProof(checkNull(""+appData[0][20]));
				bean.setDisPayMode(checkNull(""+appData[0][21])); 
				bean.setFoodTypeId(checkNull(""+appData[0][22])); 
				bean.setFoodType(checkNull(""+appData[0][23])); 
				bean.setTravelRequest(checkNull(""+appData[0][24])); 
				bean.setPolicyId(checkNull(""+appData[0][25]));  
				bean.setAppDate(checkNull(""+appData[0][26]));  
				bean.setHidStatus(checkNull(""+appData[0][27]));  
				System.out.println("food type Id=========  "+""+appData[0][22]);
				System.out.println("food type "+""+appData[0][23]);
				if((""+appData[0][18]).equals("A") || (""+appData[0][18]).equals("R") )
				{
					bean.setTrackFlag("true");
				}
			 }
		 System.out.println("appData[0][18]============ "+appData[0][18]);
		 if(bean.getHidStatus().equals("Approved")|| bean.getHidStatus().equals("Rejected")){
			 dispApprCommets(bean);
		 } 
		 // for travel  
		 
	String travelSql =" SELECT NVL(C1.LOCATION_NAME,' '),NVL(C2.LOCATION_NAME,' '),NVL(APPJOUR_DTL_JOURMODE,0), NVL(TO_CHAR(APPJOUR_DTL_JOURDATE,'DD-MM-YYYY'),' '), "
					+" NVL(APPJOUR_DTL_JOURTIME,' '),NVL(APPJOUR_DTL_JOURMODE,0),JOURNEY_NAME ||'-'|| JOURNEY_CLASS_NAME ," 
					+" NVL(APPJOUR_DTL_FROMPALCE,0),NVL(APPJOUR_DTL_TOPLACE,0) FROM HRMS_TMS_APPJOUR_DTL "
					+" LEFT JOIN HRMS_LOCATION C1 ON(C1.LOCATION_CODE = APPJOUR_DTL_FROMPALCE) "
					+" LEFT JOIN HRMS_LOCATION C2 ON(C2.LOCATION_CODE = APPJOUR_DTL_TOPLACE) "
					+" LEFT JOIN HRMS_TMS_JOURNEY ON (APPJOUR_DTL_JOURMODE = JOURNEY_ID)  "
					+" WHERE APPJOUR_DTL_TRAPPID ="+bean.getTrAppId()+" ORDER BY APPJOUR_DTL_ID ";
	 Object[][] trData =getSqlModel().getSingleResult(travelSql);
	
	 ArrayList<Object> modeList = new ArrayList<Object>(); 
     if(trData!=null && trData.length>0)
     {  
    	 int j =1;
     	for(int i=0; i<trData.length;i++)
     	{ 
     		 
     			TravelApplication bean1 = new TravelApplication();   //for previus item
     			    
     			 request.setAttribute("jourFromPlace"+j,""+trData[i][0]);
     			 request.setAttribute("jourToPlace"+j,""+trData[i][1]); 
     			 request.setAttribute("jourFromPlaceId"+j,""+trData[i][7]);
    			 request.setAttribute("jourToPlaceId"+j,""+trData[i][8]); 
		        
		        	bean1.setTrModeClass(""+trData[i][2]);
		        	bean1.setJourDate((""+trData[i][3]).trim());
		        	bean1.setJourTime((""+trData[i][4]).trim());  
 			        request.setAttribute("trModeClassId"+j,""+trData[i][5]); 
 			        request.setAttribute("trModeClass"+j,""+trData[i][6]); 
		        	modeList.add(bean1); 
		        	j++;
     	}
     	bean.setTrModeClassList(modeList);
     }
     // for local Convaence
     String LocalCon ="SELECT NVL(LOCATION_NAME,' ') , NVL(APPLOCAL_DTL_SOURCE,' '),NVL(TO_CHAR(APPLOCAL_DTL_FROM_DATE,'DD-MM-YYYY'),' '),  "
				+"	NVL(APPLOCAL_DTL_FROM_TIME,' '), NVL(TO_CHAR(APPLOCAL_DTL_TO_DATE,'DD-MM-YYYY'),' '), "
				+"	NVL(APPLOCAL_DTL_TO_TIME,' '),APPLOCAL_DTL_CITY  FROM HRMS_TMS_APPLOCAL_DTL "
				+"	LEFT JOIN HRMS_LOCATION   ON(HRMS_LOCATION.LOCATION_CODE = APPLOCAL_DTL_CITY) "
				+"	WHERE  APPLOCAL_DTL_TRAPPID ="+bean.getTrAppId()+" ORDER BY APPLOCAL_DTL_ID ";
     Object[][] loaclConData =getSqlModel().getSingleResult(LocalCon);
     
	 ArrayList<Object> localconList = new ArrayList<Object>(); 
     if(loaclConData!=null && loaclConData.length>0)
     {  int j =1;
     	for(int i=0; i<loaclConData.length;i++)
     	{  	TravelApplication bean1 = new TravelApplication();   //for previus item
     	    request.setAttribute("localCity"+j,""+loaclConData[i][0]);
	        request.setAttribute("localCityId"+j,""+loaclConData[i][6]);   
     			   // for hotel Details
		        	bean1.setLocalSource((""+loaclConData[i][1]).trim()); 
		        	bean1.setLocalFromDate((""+loaclConData[i][2]).trim());
		        	bean1.setLocalFromTime((""+loaclConData[i][3]).trim());
		        	bean1.setLocalToDate((""+loaclConData[i][4]).trim()); 
		        	bean1.setLocalToTime((""+loaclConData[i][5]).trim());  
		        	localconList.add(bean1); 
		        	j++;
     	}
     	bean.setLocalConList(localconList);
     }else
     {
    	 addLocalCon(bean ,request);
     }
     
     // for lodging 
					
     String lodgSql="SELECT  APPLODG_DTL_HOTEL,NVL(HOTEL_TYPE_NAME,' '), NVL(APPLODG_DTL_ROOM,0),NVL(ROOM_TYPE_NAME,' '), NVL(LOCATION_NAME,' '),NVL(APPLODG_DTL_LOCATION,' ')," 
     		+" NVL(TO_CHAR(APPLODG_DTL_FROMDATE,'DD-MM-YYYY'),' '),NVL(APPLODG_DTL_FROMTIME,' '), " 
     		+" NVL(TO_CHAR(APPLODG_DTL_TODATE,'DD-MM-YYYY'),' '), NVL(APPLODG_DTL_TOTIME,' ') ,NVL(APPLODG_DTL_CITY,0) FROM HRMS_TMS_APPLODG_DTL "
			+" LEFT JOIN HRMS_LOCATION  ON(HRMS_LOCATION.LOCATION_CODE = APPLODG_DTL_CITY) "
			+" LEFT JOIN HRMS_TMS_HOTEL_TYPE  ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID = APPLODG_DTL_HOTEL) "
            +" LEFT JOIN HRMS_TMS_ROOM_TYPE  ON(HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID = APPLODG_DTL_ROOM) "
			+" WHERE APPLODG_DTL_TRAPPID ="+bean.getTrAppId()+" ORDER BY APPLODG_DTL_ID";
    Object[][] lodgData =getSqlModel().getSingleResult(lodgSql);
    
	 ArrayList<Object> lodgList = new ArrayList<Object>(); 
     if(lodgData!=null && lodgData.length>0)
     { System.out.println("Lodging data in ifffffffffffff ");
    	 int j= 1;
	     	for(int i=0; i<lodgData.length;i++)
	     	{   
			   TravelApplication bean1 = new TravelApplication();   //for previus item  
			    request.setAttribute("hotelTypeId"+j,""+lodgData[i][0]);   
	     	    request.setAttribute("hotelType"+j,""+lodgData[i][1]); 
	     	    request.setAttribute("roomTypeId"+j,""+lodgData[i][2]);   
	     	    request.setAttribute("roomType"+j,""+lodgData[i][3]);  
	     	    request.setAttribute("lodgCity"+j,""+lodgData[i][4]);
		        request.setAttribute("lodgCityId"+j,""+lodgData[i][10]);   
		        
	        	 
	        	bean1.setLodgLocation((""+lodgData[i][5]).trim()) ;
	        	bean1.setLodgFromdate((""+lodgData[i][6]).trim()) ; 
	        	bean1.setLodgFromtime((""+lodgData[i][7]).trim()) ; 
	        	bean1.setLodgTodate((""+lodgData[i][8]).trim()) ;
	        	bean1.setLodgTotime((""+lodgData[i][9]).trim()) ; 
	        	lodgList.add(bean1); 
	        	j++;
	     	}
	     	bean.setLodgList(lodgList);
     	} else
	     {
     		 
     		addLodgList(bean ,request);
	     }
     
     radioFlagMethod(bean,request);
		 
 } //END OF METHOD
		
	 public boolean callDelete(TravelApplication bean)
	 { 
		 Object[][] delObj = new Object[1][1];
		 delObj [0][0] =bean.getTrAppId();       
		  boolean flag; 
		  flag= getSqlModel().singleExecute(getQuery(2),delObj);
		  if(flag)
		  {
			  flag= getSqlModel().singleExecute(getQuery(3),delObj);
		  }
		  if(flag)
		  {
			  flag= getSqlModel().singleExecute(getQuery(4),delObj);
		  }
		  if(flag)
		  {
			  flag= getSqlModel().singleExecute(getQuery(5),delObj);
		  }
		  
		  try
          {   Object[][] delObjTrack = new Object[1][2];
			  delObjTrack [0][0] =bean.getTrAppId(); 
			  delObjTrack [0][1] =bean.getUserEmpId(); 
			getSqlModel().singleExecute(getQuery(7),delObjTrack);  
			getSqlModel().singleExecute(getQuery(8),delObjTrack); 
			getSqlModel().singleExecute(getQuery(9),delObjTrack);
		  }catch (Exception e) {
			  return flag;
		}
		 return flag;
	 }
	 
	 // update
	 public boolean sendForApprovalUpdate (TravelApplication bean ,HttpServletRequest request,Object[][] empFlow)
	 { 
		        
		 bean.setGuestAppFlag("true");
		 
		 
		  boolean flag; 
		     Object[][] addobj = new Object[1][26];
			 addobj[0][0]=bean.getEmpId();  
			 addobj[0][1]=bean.getAppDate();  
			 addobj[0][2]="P";  
			 addobj[0][3]=bean.getTravelRequest();  
			 addobj[0][4]=bean.getHidAppForRadio().equals("Y")?"G":"S";  
			 addobj[0][5]=bean.getGuestName(); 
			 addobj[0][6]=bean.getTravelPurposeId();  
			 addobj[0][7]=bean.getHidAccommodationReqFlag().equals("Y")?"C":"S";
			 addobj[0][8]=bean.getHidtrArrgFlag().equals("Y")?"C":"S";  
			 addobj[0][9]=bean.getHidLocalConReq().equals("Y")?"C":"S";   
			 addobj[0][10]=bean.getTourLocTypeId();  
			 addobj[0][11]=bean.getTourStartDate();  
			 addobj[0][12]=bean.getTourEndDate();
			 addobj[0][13]=bean.getAdvanceAmt().equals("")?"0":bean.getAdvanceAmt();
			 addobj[0][14]=bean.getPayMode();			 
			 addobj[0][15]=bean.getSettleDate(); 
			 addobj[0][16]=bean.getIdProof();  			 
			 addobj[0][17]=bean.getIdProofNumber();  		 
			 addobj[0][18]=bean.getAppComments();   
			 addobj[0][19]=String.valueOf(empFlow[0][0]);  			 
			 addobj[0][20]=String.valueOf(empFlow[0][3]);
			 addobj[0][21]=bean.getFoodTypeId().equals("")?"0":bean.getFoodTypeId();
			 addobj[0][22]=String.valueOf(empFlow[0][0]);  			 
			 addobj[0][23]=String.valueOf(empFlow[0][3]);
			 addobj[0][24]=bean.getPolicyId().equals("")?"0":bean.getPolicyId();
			 addobj[0][25]=bean.getTrAppId();
			 System.out.println("bean.getTrAppId()======= "+bean.getTrAppId());
 
	 
	     flag= getSqlModel().singleExecute(getQuery(6),addobj); 
		  Object[][] delObj = new Object[1][1];
		  delObj [0][0] =bean.getTrAppId(); 
		  
		  Object[][] delObjTrack = new Object[1][2];
		  delObjTrack [0][0] =bean.getTrAppId(); 
		  delObjTrack [0][1] =bean.getUserEmpId();
		  try
		  {
			  if(flag)
			  {
			   getSqlModel().singleExecute(getQuery(2),delObj); 
			   getSqlModel().singleExecute(getQuery(7),delObjTrack); 
			   saveTravelMode (bean ,request);
			  }
			  if(flag)
			  {
				  flag= getSqlModel().singleExecute(getQuery(3),delObj);
				  getSqlModel().singleExecute(getQuery(8),delObjTrack);
			  }
			  if(flag)
			  {
				  getSqlModel().singleExecute(getQuery(4),delObj);
				  getSqlModel().singleExecute(getQuery(9),delObjTrack);
			  } 
			  
			  if(flag)
			  {
				 if(bean.getHidLocalConReq().equals("Y"))
				 {
					 saveLocalCon (bean ,request); 
				 }
				 if(bean.getHidAccommodationReqFlag().equals("Y"))  
				 {
					 savLodging (bean ,request); 
				 }
			  }
			  
			 
			 /*  if(bean.getLocalConReq().equals("C"))
	           {     saveLocalCon (bean ,request);
	           }
	           if(bean.getAccommodationReq().equals("C"))
	           {   savLodging (bean ,request); 
	           }*/
		 }catch (Exception e) {
			// TODO: handle exception
		} 
			try {
				Object[][] to_mailIds =new Object[1][1];	
				Object[][] from_mailIds =new Object[1][1];	
				
			 	to_mailIds[0][0]=String.valueOf(empFlow[0][0]);
				from_mailIds[0][0]=bean.getEmpId(); 
				MailUtility mail=new MailUtility();
				mail.initiate(context, session);
				mail.sendMail(to_mailIds, from_mailIds,"Travel", "", "P"); 
				System.out.println("mail sent ============= ");
				mail.terminate();
				 
			} catch (Exception e) { 
				  
			} 
			
           return flag;
	 }
	 
	 
	 // call travel Policy
	 
	public boolean callTravelPolicy(TravelApplication bean ,HttpServletRequest request ,HttpServletResponse response)
	 {
		bean.setTravelPolInvalidFlag("true");
		Object [][] data =null;
		if(bean.getTrAppId().equals("") || bean.getTrAppId().equals("null"))
			{
				if (!(bean.getEmpGradeId().equals("") && bean.getEmpGradeId().equals("null")))
				{ 
					 
				String PolSql ="SELECT POLICY_ID  FROM HRMS_TMS_TRAVEL_POLICY "
						+"	LEFT JOIN HRMS_TMS_POLICY_GRADE ON(HRMS_TMS_POLICY_GRADE.PG_POLICY_ID=HRMS_TMS_TRAVEL_POLICY.POLICY_ID) "
						+"	WHERE PG_EMP_GRADE ="+bean.getEmpGradeId()+" AND POLICY_STATUS ='A' AND PG_STATUS ='Y'  " ;
				
				   System.out.println("callTravelPolicy for PROCESS_MANAGER_EFFECT_DATE "+ bean.getSettleFlag());
						
				   String setFlagSql ="SELECT PROCESS_MANAGER_EFFECT_DATE FROM HRMS_TMS_PROCESS_MANAGER " ;
		 		   Object [][] settleData = getSqlModel().getSingleResult(setFlagSql);
				 		   
		 		   if((""+settleData[0][0]).equals("F")){
		 			   
		 			  PolSql+=" AND (POLICY_EFFECTIVE_DATE<= TO_DATE('"+bean.getTourStartDate()+"','DD-MM-YYYY')) ORDER BY POLICY_EFFECTIVE_DATE DESC ";
		 		     }
		 		   
		 		  if((""+settleData[0][0]).equals("T")){
					   
		 			  PolSql+=" AND (POLICY_EFFECTIVE_DATE<= TO_DATE('"+bean.getTourEndDate()+"','DD-MM-YYYY')) ORDER BY POLICY_EFFECTIVE_DATE DESC ";
		 		     }
		 		  
		 		 if((""+settleData[0][0]).equals("A")){
					   
					  PolSql+=" AND (POLICY_EFFECTIVE_DATE<= TO_DATE('"+bean.getAppDate()+"','DD-MM-YYYY')) ORDER BY POLICY_EFFECTIVE_DATE DESC ";
				     }
				 		 
		 		data = getSqlModel().getSingleResult(PolSql);
			  }
			}else
		{
			String PolSql ="SELECT TRAVEL_APP_POLICY_ID  FROM HRMS_TMS_TRAVEL_APP WHERE TRAVEL_APP_ID ="+bean.getTrAppId(); 
			data = getSqlModel().getSingleResult(PolSql);
		}
	 	 
	 	 
	 	 if(data!=null && data.length>0)
	 	 { 		 
 
	 		 bean.setPolicyId(""+data[0][0]);
	 		 System.out.println(" bean.getPolicyId()========== "+ bean.getPolicyId());
	 	// for Expense Category
			String expSql =" SELECT  NVL(EXP_CATEGORY_NAME,' '),  DECODE(PE_EXP_CAT_UNIT,'D','Per Day','T','Per Travel') , "
						+"	PE_EXP_CAT_VALUE   FROM HRMS_TMS_POLICY_EXPENSE "
						+"	LEFT JOIN HRMS_TMS_EXP_CATEGORY ON(EXP_CATEGORY_ID=PE_EXP_CAT_ID) "
						+"	WHERE PE_POLICY_ID ="+data[0][0]+" ORDER BY PE_ID "; 
			Object[][] expData = getSqlModel().getSingleResult(expSql);
			if(expData != null && expData.length>0)
			{
			ArrayList expnseList = new ArrayList();
				for(int i=0;i<expData.length;i++)
				{   
					TravelApplication bean1 = new TravelApplication();
					bean1.setExCategory(""+expData[i][0]);
					bean1.setExpCatUnit(""+expData[i][1]); 
					bean1.setExpValue(""+expData[i][2]);   
					expnseList.add(bean1);
				} 
				bean.setExpList(expnseList); 
			}
			
// for travel Mode
			
		String trModeSql ="  SELECT  NVL(JOURNEY_NAME,' '),NVL(JOURNEY_CLASS_NAME,' '),PTM_TRAVEL_MODE_ID "
			+"   FROM HRMS_TMS_POLICY_TRAVEL_MODE "
			+"	LEFT JOIN HRMS_TMS_JOURNEY ON(JOURNEY_ID = PTM_TRAVEL_MODE_ID) " 
			+"	WHERE PTM_POLICY_ID ="+data[0][0]+" AND PTM_STATUS ='Y' ORDER BY PTM_ID ";
		System.out.println("six journey sql========== ");
            Object[][] trModeData = getSqlModel().getSingleResult(trModeSql); 
            String trModeId ="";
            String dupTrMode ="";
			if(trModeData != null && trModeData.length>0)
			{
			 
				 ArrayList trModeList = new ArrayList();
					for(int i=0;i<trModeData.length;i++)
					{ 
						TravelApplication bean1 = new TravelApplication();
						if(!dupTrMode.equals(""+trModeData[i][0]))
						{
						bean1.setTravelMode(""+trModeData[i][0]);
						}
						dupTrMode=""+trModeData[i][0];
						bean1.setTravelClass(""+trModeData[i][1]);  
						trModeList.add(bean1); 
						if(i<trModeData.length-1)
						{
						   trModeId+="*"+trModeData[i][2]+"*,";
						}else
						{
							trModeId+="*"+trModeData[i][2]+"*";
						}
					}  
					System.out.println("valid Travel Mode=========== "+trModeId);
					bean.setValidTrMode(trModeId);
					bean.setTravelModeList(trModeList);  
					
			} 
			// FOR HOTEL TYPE
			
			 String hotelTypeSql =" SELECT NVL(HOTEL_TYPE_NAME,' '),NVL(ROOM_TYPE_NAME,' ') ,NVL(PHT_HOTEL_TYPE_ID,0),NVL(PHT_ROOM_ID,0) FROM HRMS_TMS_POLICY_HOTEL_TYPE "
								 +"	LEFT JOIN HRMS_TMS_HOTEL_TYPE ON(HOTEL_TYPE_ID=PHT_HOTEL_TYPE_ID) "
								+"	LEFT JOIN HRMS_TMS_ROOM_TYPE ON(ROOM_TYPE_ID=PHT_ROOM_ID) " 
								+"	WHERE PHT_POLICY_ID ="+data[0][0]+" AND PHT_STATUS ='Y'	ORDER BY PHT_ID ";
					Object[][] hotelData = getSqlModel().getSingleResult(hotelTypeSql); 
					String validHotelType ="";
					String validHotelRoomType ="";
					String hotelIdForRoom="";
					String validRoomType ="";
					String dupHotelType ="";
 
					if(hotelData != null && hotelData.length>0)
					{ 
						 
						 ArrayList hotelList = new ArrayList();
							for(int j=0;j<hotelData.length;j++)
							{   	TravelApplication bean1 = new TravelApplication();
							       if(!dupHotelType.equals(""+hotelData[j][0])){
									bean1.setHotelTypePol(""+hotelData[j][0]);
							       }
							       dupHotelType = ""+hotelData[j][0] ;
									bean1.setRoomTypePol(""+hotelData[j][1]);  
									hotelList.add(bean1);  
									if(j<(hotelData.length-1))
									{
								 	validHotelType=validHotelType+"*"+hotelData[j][2]+"*H*"+""+hotelData[j][3]+"*R,"; 
									}else
									{
										validHotelType=validHotelType+"*"+hotelData[j][2]+"*H*"+""+hotelData[j][3]+"*R"; 
									}
                                
	                        	System.out.println("validHotelType==in loop========= "+validHotelType);
						   }
							System.out.println("validHotelType=========== "+validHotelType);
							bean.setValidHotelType(validHotelType); 
							bean.setHotelTypeList(hotelList);  
					} 
					
					 //FOR GUID LINES
					 String polGuidSql="	SELECT NVL(POLICY_GUIDLINES,' ') FROM HRMS_TMS_TRAVEL_POLICY WHERE POLICY_ID ="+data[0][0];
					 Object[][] guidData = getSqlModel().getSingleResult(polGuidSql);
					 if(guidData!=null && guidData.length >0 )
					 {
						 bean.setGuidLines(""+guidData[0][0]);
					 }
					 
			// for travel only 
				 
						String [] jourDate =request.getParameterValues("jourDate");
						 int x =1; 
						 String chkFound="true"; 
						 if(jourDate!=null)
						 {
							 for(int i =0;i< jourDate.length;i++)
							 {
								 String trModeClassId =(String)request.getParameter("trModeClassId"+x); 
									if(trModeClassId!=null && trModeClassId.length()>0)
									 {	 
									     String trModeClassForChk ="*"+trModeClassId+"*"; 
										 if(trModeId.indexOf(trModeClassForChk)==-1)
										 { 
											 chkFound="false";
											 break; 
										 } 
										 x++;
									 } 
					        }	
						 }
						 System.out.println("chkFound====== "+chkFound);
						 if(chkFound.equals("false"))
						 {
							 return false;
						 }
						 
						// for Hotel And Room only 
						 
							String [] lodgFromdate =request.getParameterValues("lodgFromdate");
							 int z =1;
							  String chkHotelRoomFound="true";
							  if(lodgFromdate!=null && lodgFromdate.length>0)
							  {
								 for(int i =0;i< lodgFromdate.length;i++)
								 {
									 String hotelTypeId =(String)request.getParameter("hotelTypeId"+z); 
									 String roomTypeId =(String)request.getParameter("roomTypeId"+z); 
										if(hotelTypeId!=null && hotelTypeId.length()>0)
										 {	 
												 String hotelRoomChk = "*"+hotelTypeId+"*H*"+roomTypeId+"*R";
												 if(validHotelType.indexOf(hotelRoomChk)==-1)
												 { 
													 chkHotelRoomFound="false";
													 break; 
												 } 
												z++;
										 }
									
								   }	  
							  }
							  System.out.println("chkHotelRoomFound====== "+chkHotelRoomFound);
							 if(chkHotelRoomFound.equals("false"))
							 {
								 return false;
							 } 
	 	    } else
	 	    {
	 	    	 bean.setTravelPolicyViewFlag("false");
	 	    	 try{  
	 	    	 PrintWriter out = response.getWriter(); 
	 	    	 out.println(" <html> <body><script> alert('Policy is not defined for Grade "+bean.getEmpGrade()+" ,Please contact HR'); window.close();  </script> </body> </html>");
	 	    	  }catch (Exception e) {
					// TODO: handle exception
				} 
	 	    	 
	 	    }
		 return true;		 
 
	 }
		/**
		 * @param result
		 * @return String 
		 */ 
	
	public void radioFlagMethod(TravelApplication bean,HttpServletRequest request)
	{
		
		 if(bean.getHidAppForRadio().equals("Y"))
		 {
			 bean.setGuestAppFlag("true"); 
		 }else
		 {
			 bean.setGuestAppFlag("false"); 
		 } 
	 
	   if(bean.getHidAccommodationReqFlag().equals("Y"))  
		 {
			 bean.setAccomFlag("true"); 
			 String dataLodg="input";
			 request.setAttribute("dataLodg", dataLodg);
			 
		 }else
		 {
			 String dataLodg="none";
			 request.setAttribute("dataLodg", dataLodg);
			 bean.setAccomFlag("false"); 
		 }  
		 
		 if(bean.getHidtrArrgFlag().equals("Y"))
		 {
			 bean.setTrArFlag("true");
		 }else
		 {
			 bean.setTrArFlag("false");
		 } 
		 if(bean.getHidLocalConReq().equals("Y"))
		 {
			 bean.setLocalFlag("true");
			 String dataLocal="input";
			 request.setAttribute("dataLocal", dataLocal);
		 }else
		 {
			 bean.setLocalFlag("false");
			 String dataLocal="none";
			 request.setAttribute("dataLocal", dataLocal);
		 }
		 
		 
	}
	 
	public void employeeInfo(TravelApplication bean)
	{
 String sql =" SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TRAVEL_APP_REQUEST ,TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY'), " 
		+"  TRAVEL_APP_STATUS , CENTER_NAME,DEPT_NAME,RANK_NAME," 
		+"  TRAVEL_APP_EMPID ,NVL(CADRE_NAME,' '),EMP_CADRE,TO_CHAR(EMP_DOB,'DD-MM-YYYY'),EMP_TOKEN   FROM HRMS_TMS_TRAVEL_APP  "
		+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID= HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID) " 
		+" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
		+" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) " 
		+" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "  
		+" LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE) "
		+" WHERE TRAVEL_APP_ID ="+bean.getTrAppId();
   Object[][] data = getSqlModel().getSingleResult(sql);
		if(data !=null && data.length>0)
		{
			bean.setEmpName(""+data[0][0]);	
			bean.setTravelRequest(""+data[0][1]);	
			bean.setAppDate(""+data[0][2]);	
			bean.setAppStatus(""+data[0][3]);	
			bean.setBranchName(""+data[0][4]);	 
			bean.setDepartment(""+data[0][5]);	
			bean.setDesignation(""+data[0][6]);	
			bean.setEmpId(""+data[0][7]);	 
			bean.setEmpGrade(""+data[0][8]);	
			bean.setEmpGradeId(""+data[0][9]);	
			bean.setEmpDob(""+data[0][10]);
			bean.setEmpToken(""+data[0][11]);
		}  
	}
	
	public void trackForTravelMode(TravelApplication bean)
	{   
		 bean.setTrackTrModeFlag("true");
		 bean.setTrackLocalFlag("false");
		 bean.setTrackLodgFlag("false");
		 
 String sql =" SELECT C1.LOCATION_NAME,C2.LOCATION_NAME,JOURNEY_NAME||'-'||JOURNEY_CLASS_NAME, TO_CHAR(APPJOUR_DTL_JOURDATE,'DD-MM-YYYY'),  "
			+" APPJOUR_DTL_JOURTIME  FROM HRMS_TMS_APPJOUR_DTL_TRACK   "
			+" LEFT JOIN HRMS_LOCATION C1 ON(C1.LOCATION_CODE = APPJOUR_DTL_FROMPALCE)  "
			+" LEFT JOIN HRMS_LOCATION C2 ON(C2.LOCATION_CODE = APPJOUR_DTL_TOPLACE)  "
			+"  LEFT JOIN HRMS_TMS_JOURNEY  ON(JOURNEY_ID = APPJOUR_DTL_JOURMODE) "
		    +" WHERE APPJOUR_DTL_TRAPPID="+bean.getTrAppId()+" AND APPJOUR_DTL_MODIFIED_BY ="+bean.getUserEmpId();
			 Object[][] trData =getSqlModel().getSingleResult(sql);
				
			 ArrayList<Object> modeList = new ArrayList<Object>(); 
			 if(trData!=null && trData.length>0)
			 {  
			 	for(int i=0; i<trData.length;i++)
			 	{ 
			 		 
			 			TravelApplication bean1 = new TravelApplication();   //for previus item
			 			    bean1.setJourFromPlace(""+trData[i][0]);   // for hotel Details
				        	bean1.setJourToPlace(""+trData[i][1]);
				        	bean1.setTrModeClass(""+trData[i][2]);
				        	bean1.setJourDate(""+trData[i][3]);
				        	bean1.setJourTime(checkNull(""+trData[i][4]));   
				        	
				        	modeList.add(bean1); 
			 	}
			 	bean.setTrModeClassList(modeList);
			 } 
	}
	
	
	public void trackForLocal(TravelApplication bean)
	{   
		 bean.setTrackTrModeFlag("false");
		 bean.setTrackLocalFlag("true");
		 bean.setTrackLodgFlag("false"); 
		 
		 String LocalCon ="SELECT LOCATION_NAME , APPLOCAL_DTL_SOURCE,TO_CHAR(APPLOCAL_DTL_FROM_DATE,'DD-MM-YYYY'), "
					+" APPLOCAL_DTL_FROM_TIME, TO_CHAR(APPLOCAL_DTL_TO_DATE,'DD-MM-YYYY'), "
					+" APPLOCAL_DTL_TO_TIME  FROM HRMS_TMS_APPLOCAL_DTL_TRACK  "
					+" LEFT JOIN HRMS_LOCATION   ON(HRMS_LOCATION.LOCATION_CODE = APPLOCAL_DTL_CITY) "
				   +"  WHERE  APPLOCAL_DTL_TRAPPID ="+bean.getTrAppId()+" AND  APPLOCAL_DTL_MODIFIED_BY ="+bean.getUserEmpId();
      Object[][] loaclConData =getSqlModel().getSingleResult(LocalCon);
  
		 ArrayList<Object> localconList = new ArrayList<Object>(); 
		  if(loaclConData!=null && loaclConData.length>0)
		  {  
		  	for(int i=0; i<loaclConData.length;i++)
		  	{  	TravelApplication bean1 = new TravelApplication();   //for previus item
		  			    bean1.setLocalCity(checkNull(""+loaclConData[i][0]));   // for hotel Details
				        	bean1.setLocalSource(checkNull(""+loaclConData[i][1])); 
				        	bean1.setLocalFromDate(checkNull(""+loaclConData[i][2]));
				        	bean1.setLocalFromTime(checkNull(""+loaclConData[i][3]));
				        	bean1.setLocalToDate(checkNull(""+loaclConData[i][4])); 
				        	bean1.setLocalToTime(checkNull(""+loaclConData[i][5]));  
				        	localconList.add(bean1); 
		  	}
		  	bean.setLocalConList(localconList);
		  } 
	} 
	
	public void trackForLodg(TravelApplication bean)
	{   
		 bean.setTrackTrModeFlag("false");
		 bean.setTrackLocalFlag("false");
		 bean.setTrackLodgFlag("true"); 
		 
  String lodgSql="  SELECT  HOTEL_TYPE_NAME, ROOM_TYPE_NAME, LOCATION_NAME,APPLODG_DTL_LOCATION,"
				+" TO_CHAR(APPLODG_DTL_FROMDATE,'DD-MM-YYYY'),APPLODG_DTL_FROMTIME, "
				+" TO_CHAR(APPLODG_DTL_TODATE,'DD-MM-YYYY'), APPLODG_DTL_TOTIME FROM HRMS_TMS_APPLODG_DTL_TRACK "
				+" LEFT JOIN HRMS_LOCATION  ON(HRMS_LOCATION.LOCATION_CODE = APPLODG_DTL_CITY) "
				+" LEFT JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID = APPLODG_DTL_HOTEL) "
			    +" LEFT JOIN HRMS_TMS_ROOM_TYPE ON(HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID= APPLODG_DTL_ROOM) "
				+" WHERE APPLODG_DTL_TRAPPID ="+bean.getTrAppId()+"  AND APPLOCAL_DTL_MODIFIED_BY ="+bean.getUserEmpId();
		 Object[][] lodgData =getSqlModel().getSingleResult(lodgSql);
		    
		 ArrayList<Object> lodgList = new ArrayList<Object>(); 
	     if(lodgData!=null)
	     {  
	     	for(int i=0; i<lodgData.length;i++)
	     	{   
			   TravelApplication bean1 = new TravelApplication();   //for previus item
			    bean1.setHotelType(""+lodgData[i][0]) ;  // for hotel Details
	        	bean1.setRoomType(""+lodgData[i][1]) ; 
	        	bean1.setLodgCity(""+lodgData[i][2]) ; 
	        	bean1.setLodgLocation(checkNull(""+lodgData[i][3])) ;
	        	bean1.setLodgFromdate(""+lodgData[i][4]) ; 
	        	bean1.setLodgFromtime(checkNull(""+lodgData[i][5])) ; 
	        	bean1.setLodgTodate(""+lodgData[i][6]) ;
	        	bean1.setLodgTotime(checkNull(""+lodgData[i][7])) ; 
	        	lodgList.add(bean1); 
	     	}
	     	bean.setLodgList(lodgList);
	     }
	} 
	
	public void getRadioFlagFun(TravelApplication bean)
	{
		  if(bean.getAppFor().equals("G"))
			 {
				 bean.setGuestAppFlag("true");
				 bean.setHidAppForRadio("Y");
			 }else
			 {
				 bean.setGuestAppFlag("false");
				 bean.setHidAppForRadio("N");
			 } 
			 if(bean.getAccommodationReq().equals("C"))
			 {
				 bean.setAccomFlag("true");
				 bean.setHidAccommodationReqFlag("Y");
			 }else
			 {
				 bean.setAccomFlag("false"); 
				 bean.setHidAccommodationReqFlag("N");
			 } 
			 if(bean.getTrArrg().equals("C"))
			 {
				 bean.setTrArFlag("true");
				 bean.setHidtrArrgFlag("Y");
			 }else
			 {
				 bean.setTrArFlag("false");
				 bean.setHidtrArrgFlag("N");
			 } 
			 if(bean.getLocalConReq().equals("C"))
			 {   bean.setHidLocalConReq("Y");
				 bean.setLocalFlag("true");
			 }else
			 {
				 bean.setHidLocalConReq("N");
				 bean.setLocalFlag("false");
			 }
	}
	
		public String checkNull(String result) {
			 
			if (result == null || result.equals("null")) {
				return "";
			} else {
				return result;
			}
		} // end of checkNull
		
		public void getAllIterator(TravelApplication bean)
		{
			ArrayList<Object> modeList = new ArrayList<Object>(); 
			TravelApplication bean1 = new TravelApplication();   
			bean1.setJourFromPlace(""); 
        	bean1.setJourToPlace(""); 
        	bean1.setTrModeClass(""); 
        	bean1.setJourDate(""); 
        	bean1.setJourTime("");      
        	modeList.add(bean1);
        	bean.setTrModeClassList(modeList);
        	
        	 ArrayList<Object> localconList = new ArrayList<Object>(); 
        	TravelApplication bean2 = new TravelApplication();   //for previus item
			bean1.setLocalCity("");   // for hotel Details
			bean2.setLocalSource(""); 
			bean2.setLocalFromDate(""); 
			bean2.setLocalFromTime("");
			bean2.setLocalToDate("");
			bean2.setLocalToTime(""); 
	        localconList.add(bean1); 
	    	bean.setLocalConList(localconList);
	    	
	        ArrayList<Object> lodgList = new ArrayList<Object>();
	        
	        TravelApplication bean3 = new TravelApplication();   //for previus item
	        bean3.setHotelType("");   // for hotel Details
	        bean3.setRoomType(""); 
	        bean3.setLodgCity(""); 
	        bean3.setLodgLocation(""); 
	        bean3.setLodgFromdate("");  
	        bean3.setLodgFromtime(""); 
        	bean1.setLodgTodate(""); 
        	bean1.setLodgTotime("");   
        	lodgList.add(bean1);
        	bean.setLodgList(lodgList);
        	
		}
		
		 public boolean save (TravelApplication bean ,HttpServletRequest request)
		 {
			 System.out.println("bean.getLocalConReq(); ========="+bean.getLocalConReq() );
			 System.out.println("bean.getAccommodationReq(); ========="+bean.getAccommodationReq() );
			 System.out.println("bean.getAppFor(); ========="+bean.getAppFor() );
			 System.out.println("bean.getTrArrg(); ========="+bean.getTrArrg() );
			 
			
					 Object[][] addobj = new Object[1][21];
					 addobj[0][0]=bean.getEmpId();  
					 addobj[0][1]=bean.getAppDate();  
					 addobj[0][2]="N";  
					 addobj[0][3]=bean.getTravelRequest();  
					 addobj[0][4]=bean.getAppFor();  
					 addobj[0][5]=bean.getGuestName(); 
					 addobj[0][6]=bean.getTravelPurposeId();  
					 addobj[0][7]=bean.getAccommodationReq();  
					 addobj[0][8]=bean.getTrArrg();  
					 addobj[0][9]=bean.getLocalConReq();  
					 addobj[0][10]=bean.getTourLocTypeId();  
					 addobj[0][11]=bean.getTourStartDate();  
					 addobj[0][12]=bean.getTourEndDate();
					 addobj[0][13]=bean.getAdvanceAmt().equals("")?"0":bean.getAdvanceAmt();
					 addobj[0][14]=bean.getPayMode();			 
					 addobj[0][15]=bean.getSettleDate(); 
					 addobj[0][16]=bean.getIdProof();  			 
					 addobj[0][17]=bean.getIdProofNumber();  		 
					 addobj[0][18]=bean.getAppComments();   
					 addobj[0][19]=bean.getFoodTypeId().equals("")?"0":bean.getFoodTypeId();
					 addobj[0][20]=bean.getPolicyId().equals("")?"0":bean.getPolicyId();
		             boolean flag= getSqlModel().singleExecute(getQuery(11), addobj);  
		             
		             String sql =" SELECT MAX(TRAVEL_APP_ID)  FROM HRMS_TMS_TRAVEL_APP";
		             Object[][] appData= getSqlModel().getSingleResult(sql);
		             if(appData != null && appData.length>0)
		             {
		            	 bean.setTrAppId(""+appData[0][0]);
		             }
		             saveTravelMode (bean ,request);
		             if(bean.getLocalConReq().equals("C"))
		             {
		             saveLocalCon (bean ,request);
		             }
		             System.out.println("bean.getAccommodationReq()========= "+bean.getAccommodationReq());
		             if(bean.getAccommodationReq().equals("C"))
		             {
		                savLodging (bean ,request); 
		             }
		             return flag; 
		 } // end of save 
		 
		 
		 public boolean update (TravelApplication bean ,HttpServletRequest request)
		 { 
			        
			  boolean flag; 
			     Object[][] addobj = new Object[1][22];
				 addobj[0][0]=bean.getEmpId();  
				 addobj[0][1]=bean.getAppDate();  
				 addobj[0][2]="N";  
				 addobj[0][3]=bean.getTravelRequest();  
				 addobj[0][4]=bean.getAppFor();  
				 addobj[0][5]=bean.getGuestName(); 
				 addobj[0][6]=bean.getTravelPurposeId();  
				 addobj[0][7]=bean.getAccommodationReq();  
				 addobj[0][8]=bean.getTrArrg();  
				 addobj[0][9]=bean.getLocalConReq();  
				 addobj[0][10]=bean.getTourLocTypeId();  
				 addobj[0][11]=bean.getTourStartDate();  
				 addobj[0][12]=bean.getTourEndDate();
				 addobj[0][13]=bean.getAdvanceAmt().equals("")?"0":bean.getAdvanceAmt();
				 addobj[0][14]=bean.getPayMode();			 
				 addobj[0][15]=bean.getSettleDate(); 
				 addobj[0][16]=bean.getIdProof();  			 
				 addobj[0][17]=bean.getIdProofNumber();  		 
				 addobj[0][18]=bean.getAppComments();    
				 addobj[0][19]=bean.getFoodTypeId().equals("")?"0":bean.getFoodTypeId();
				 addobj[0][20]=bean.getPolicyId().equals("")?"0":bean.getPolicyId();
				 addobj[0][21]=bean.getTrAppId();
				 System.out.println("bean.getTrAppId()======= "+bean.getTrAppId());
	 
		 
		     flag= getSqlModel().singleExecute(getQuery(12),addobj); 
			  Object[][] delObj = new Object[1][1];
			  delObj [0][0] =bean.getTrAppId(); 
			  
			  Object[][] delObjTrack = new Object[1][2];
			  delObjTrack [0][0] =bean.getTrAppId(); 
			  delObjTrack [0][1] =bean.getUserEmpId();
			  try
			  {
				  if(flag)
				  {
				   getSqlModel().singleExecute(getQuery(2),delObj); 
				   getSqlModel().singleExecute(getQuery(7),delObjTrack); 
				   saveTravelMode (bean ,request);
				  }
				  if(flag)
				  {
					  flag= getSqlModel().singleExecute(getQuery(3),delObj);
					  getSqlModel().singleExecute(getQuery(8),delObjTrack);
				  }
				  if(flag)
				  {
					  getSqlModel().singleExecute(getQuery(4),delObj);
					  getSqlModel().singleExecute(getQuery(9),delObjTrack);
				  } 
				  
				  
				 
				   if(bean.getLocalConReq().equals("C"))
		           {     saveLocalCon (bean ,request);
		           }
		           if(bean.getAccommodationReq().equals("C"))
		           {   savLodging (bean ,request); 
		           }
			 }catch (Exception e) {
				// TODO: handle exception
			}
	           return flag;
		 }
		
		//=============================FOR APPROVER UPDATE==========================
		
		 public boolean updateApprover (TravelApplication bean ,HttpServletRequest request)
		 { 
			        
			  boolean flag; 
			     Object[][] addobj = new Object[1][20];
				 addobj[0][0]=bean.getEmpId();  
				 addobj[0][1]=bean.getAppDate();  
				 addobj[0][2]="P";  
				 addobj[0][3]=bean.getTravelRequest();  
				 addobj[0][4]=bean.getAppFor();  
				 addobj[0][5]=bean.getGuestName(); 
				 addobj[0][6]=bean.getTravelPurposeId();  
				 addobj[0][7]=bean.getAccommodationReq();  
				 addobj[0][8]=bean.getTrArrg();  
				 addobj[0][9]=bean.getLocalConReq();  
				 addobj[0][10]=bean.getTourLocTypeId();  
				 addobj[0][11]=bean.getTourStartDate();  
				 addobj[0][12]=bean.getTourEndDate();
				 addobj[0][13]=bean.getAdvanceAmt().equals("")?"0":bean.getAdvanceAmt();
				 addobj[0][14]=bean.getPayMode();			 
				 addobj[0][15]=bean.getSettleDate(); 
				 addobj[0][16]=bean.getIdProof();  			 
				 addobj[0][17]=bean.getIdProofNumber();   
				 addobj[0][18]= bean.getFoodTypeId().equals("")?"0":bean.getFoodTypeId();
				 addobj[0][19]=bean.getTrAppId();
				 
				 System.out.println("bean.getTrAppId()======= "+bean.getTrAppId());
	 
		 
		     flag= getSqlModel().singleExecute(getQuery(10),addobj); 
			  Object[][] delObj = new Object[1][1];
			  delObj [0][0] =bean.getTrAppId(); 
			  
			  Object[][] delObjTrack = new Object[1][2];
			  delObjTrack [0][0] =bean.getTrAppId(); 
			  delObjTrack [0][1] =bean.getUserEmpId();
			  if(flag)
			  {
				  System.out.println("in first"+flag);
			   getSqlModel().singleExecute(getQuery(2),delObj); 
			  }
			  if(flag)
			  {
				  System.out.println("in 22222222"+flag);
				  flag= getSqlModel().singleExecute(getQuery(3),delObj);
			  }
			  if(flag)
			  {
				  System.out.println("in 333333333 "+flag);
				   getSqlModel().singleExecute(getQuery(4),delObj);
			  } 
			  
			  try
			  {
				  System.out.println("in 44444444 "+flag);
				  getSqlModel().singleExecute(getQuery(7),delObjTrack); 
				
			  }catch (Exception e) {
				// TODO: handle exception
			}
			  saveTravelMode (bean ,request);
	        //  saveLocalCon (bean ,request);
	        //  savLodging (bean ,request); 
			   if(bean.getLocalConReq().equals("C"))
	           {  System.out.println("in 555555555 "+flag);
				   getSqlModel().singleExecute(getQuery(8),delObjTrack);
				   saveLocalCon (bean ,request);
	           }
	           if(bean.getAccommodationReq().equals("C"))
	           {   
	        	   getSqlModel().singleExecute(getQuery(9),delObjTrack);
	        	   savLodging (bean ,request); 
	           }
	           return flag;
		 }
		
		 public void dispApprCommets(TravelApplication bean)
		 {
			 String comSql ="SELECT (EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '), "
						+" NVL(TO_CHAR(APP_PATH_APPROVER_DATE,'DD-MM-YYYY'),' '),NVL(APP_PATH_APPROVER_REMARK,' ')    FROM  HRMS_TMS_APP_PATH   "
						+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_TMS_APP_PATH.APP_PATH_APPROVER_CODE= HRMS_EMP_OFFC.EMP_ID)  "
						+" WHERE APP_PATH_APP_ID="+bean.getTrAppId()+" ORDER BY APP_PATH_APPROVER_DATE"; 
			 Object [][] data =getSqlModel().getSingleResult(comSql);
			 if(data!=null && data.length >0)
			 {  
				  ArrayList apprList = new ArrayList();
				  for(int i =0 ;i<data.length;i++)
				  {
					 TravelApplication bean1 = new TravelApplication(); 
					 bean1.setApprEmpName(""+data[i][0]);
					 bean1.setApprDate(""+data[i][1]);
					 bean1.setApprComments(""+data[i][2]);
					 apprList.add(bean1);
				  }
				  bean.setApprList(apprList);
				  bean.setCommentFlag("true");
			 }else
			 {
				 bean.setCommentFlag("false"); 
			 }
			 
		 }
		 
		 public void dispApprCommetsCancel(TravelApplication bean)
		 {
			 String comSql ="SELECT (EMP_FNAME ||' '||EMP_MNAME ||' '|| EMP_LNAME ||' '), "
						+" NVL(TO_CHAR(APP_PATH_CAN_APPROVER_DATE,'DD-MM-YYYY'),' '),NVL(APP_PATH_CAN_APPROVER_REMARK,' ')    FROM  HRMS_TMS_CANCEL_APP_PATH   "
						+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_TMS_CANCEL_APP_PATH.APP_PATH_CAN_APPROVER_CODE = HRMS_EMP_OFFC.EMP_ID)  "
						+" WHERE APP_PATH_CAN_APP_ID="+bean.getTrAppId()+" ORDER BY APP_PATH_CAN_APPROVER_DATE"; 
			 Object [][] data =getSqlModel().getSingleResult(comSql);
			 if(data!=null && data.length >0)
			 {  
				  ArrayList apprList = new ArrayList();
				  for(int i =0 ;i<data.length;i++)
				  {
					 TravelApplication bean1 = new TravelApplication(); 
					 bean1.setApprEmpName(""+data[i][0]);
					 bean1.setApprDate(""+data[i][1]);
					 bean1.setApprComments(""+data[i][2]);
					 apprList.add(bean1);
				  }
				  bean.setApprList(apprList);
				  bean.setCommentFlag("true");
			 }else
			 {
				 bean.setCommentFlag("false"); 
			 }
			 
		 }
		 
		 
		 public void callStatus(TravelApplication bean , String status,HttpServletRequest request)
			{ 
				
			 if(status.equals("N"))
				  bean.setReqNew("true"); 
			  if(status.equals("P"))
				  bean.setPen("true");
				else if(status.equals("A"))
					bean.setApp("true");
				else if(status.equals("R"))
					bean.setRej("true");
				if(!status.equals("P")){
					System.out.println("status checking   "+status);
					bean.setApprflag("true");
				} 
				String query = "  SELECT TRAVEL_APP_REQUEST ,EMP_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS EMPNAME,  TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY'), "
				       +"   DECODE(TRAVEL_APP_STATUS,'N','New','P','Pending','A','Approved','R','Rejected'),TRAVEL_APP_STATUS, TRAVEL_APP_ID  FROM HRMS_TMS_TRAVEL_APP  "
					   +"	LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_TRAVEL_APP.TRAVEL_APP_EMPID)  "
					   +"	WHERE TRAVEL_APP_STATUS  ='"+status+"' " ;
					   		 if(bean.isGeneralFlag()==true)
					   		 {
					   			query+=" AND EMP_ID ="+bean.getUserEmpId(); 
					   		 } 
					   		query+=" ORDER BY TRAVEL_APP_APPDATE DESC"; 
				
				Object [][] result = getSqlModel().getSingleResult(query );
				
				doPaging(bean, result.length, new Object[][]{}, request);
				int fromTotRec = Integer.parseInt(bean.getFromTotRec());
				int toTotRec = Integer.parseInt(bean.getToTotRec());
				
				ArrayList statusList = new ArrayList();
			 
				for(int i =fromTotRec ;i<toTotRec;i++)
				{
					TravelApplication bean1 = new TravelApplication();   
					bean1.setItRequestName(String.valueOf(result[i][0]));
					bean1.setItEmpId(String.valueOf(result[i][1]));
					bean1.setItEmpName(String.valueOf(result[i][2]));
					bean1.setItAppDate(String.valueOf(result[i][3]));
					bean1.setItStatus(String.valueOf(result[i][4]));
					bean1.setItHidStatus(String.valueOf(result[i][5]));
					bean1.setItTrAppId(String.valueOf(result[i][6])); 
					bean1.setSerialNo(""+(i+1));
					statusList.add(bean1);
				}
					bean.setStatusList(statusList); 
					 if(statusList.size()==0)
					 {
						 
						  bean.setNoData("true"); 
					 }else
					 { 
						 bean.setNoData("false"); 
					 }
			} //end of callStatus
		 
		 
		 public void doPaging(TravelApplication bean, int empLength, Object[][] attnObj, HttpServletRequest request)
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
		 
		 public void callForPolicyDate(TravelApplication bean)
		 {
			  System.out.println("in presd for PROCESS_MANAGER_EFFECT_DATE "+ bean.getSettleFlag());
			  String setFlagSql ="SELECT PROCESS_MANAGER_EFFECT_DATE FROM HRMS_TMS_PROCESS_MANAGER " ;
	 		   Object [][] settleData = getSqlModel().getSingleResult(setFlagSql);
	 		    bean.setSettleFlag(""+settleData[0][0]); 
		 }
		 
		 public boolean callRequistionStatus(TravelApplication bean)
		 {   
			    String reqSql ="SELECT PROCESS_MANAGER_APL_REQ  FROM HRMS_TMS_PROCESS_MANAGER " ;
	 		    Object [][] settleData = getSqlModel().getSingleResult(reqSql);
	 		    if(settleData!= null && settleData.length>0){
	 		    
	 		    if((""+settleData[0][0]).equals("Y"))
	 		    {
	 		    	return true;
	 		    }else
	 		    {
	 		    	return false;
	 		    }
	 		   }else
	 		   {
	 			  return true;
	 		   }
		 }
		 
		 
		 public void callCancellationAppDtl(TravelApplication bean ,HttpServletRequest request)
		 {
		 
		 bean.setTrNoAddRow("false");
		 bean.setLocalNoAddRow("false");
		 bean.setLodgNoAddRow("false");
		 
		String appSql ="SELECT NVL(TRAVEL_APP_APPFOR,' '), NVL(TRAVEL_APP_GUEST,''), NVL(PURPOSE_NAME,' '),TRAVEL_APP_PURPOSE, TRAVEL_APP_ACCOMMODATION, "
		+" TRAVEL_APP_TRAVEL_ARRG, TRAVEL_APP_LOCAL_ALLOW,NVL(LOCATION_TYPE_NAME,' '),TRAVEL_APP_LOCATION_TYPE, TO_CHAR(TRAVEL_APP_START_DATE,'DD-MM-YYYY'), "
		+" TO_CHAR(TRAVEL_APP_END_DATE,'DD-MM-YYYY'), SCH_APPR_ADVANCE_SAN, TRAVEL_APP_PAYMODE, TO_CHAR(TRAVEL_APP_SETTLE_DATE,'DD-MM-YYYY'),  "
		+" TRAVEL_APP_IDPROOF,NVL(TRAVEL_APP_IDNUMBER,' '),NVL(TRAVEL_APP_COMMENTS,' ') ,TRAVEL_APP_EMPID ," ;
		
		  if(bean.getTrAppCanStatus().equals("true"))
	         {
				 appSql +=" TRAVEL_APP_STATUS ,DECODE(TRAVEL_APP_STATUS,'N','New','P','Pending','A','Approved','R','Rejected'),";
	         }else
	         {
	        	 appSql +=" TRAVEL_CANCEL_STATUS ,DECODE(TRAVEL_CANCEL_STATUS, 'P','Pending','A','Approved','R','Rejected'),";
	          }  
		 appSql +=" DECODE (TRAVEL_APP_IDPROOF,'V','Voter Identity Card ','P','Passport','I',' PAN Card','D','Driving Licence ','G','Photo identity cards issued by Central/State Government'), "  
	    +" DECODE (TRAVEL_APP_PAYMODE,'C','Cash','Q','Cheque','S','Salary','T','Transfer') , " 
	    +" NVL(TRAVEL_APP_FOOD_TYPE,0),NVL(FOOD_TYPE_NAME,' '),NVL(TRAVEL_APP_REQUEST,' '),TRAVEL_APP_POLICY_ID ,TO_CHAR(TRAVEL_APP_APPDATE,'DD-MM-YYYY') , ";
	    
	    if(bean.getTrAppCanStatus().equals("true"))
        {
	    	appSql +=" DECODE(TRAVEL_APP_STATUS, 'N','New','P','Pending','A','Approved','R','Rejected')";
        }else
        {
        	appSql +=" DECODE(TRAVEL_CANCEL_STATUS,'P','Pending','A','Approved','R','Rejected')";
         } 
	    
	    appSql += "  FROM HRMS_TMS_TRAVEL_APP "
		+" LEFT JOIN HRMS_TMS_PURPOSE ON (HRMS_TMS_PURPOSE.PURPOSE_ID =TRAVEL_APP_PURPOSE) "
		+" LEFT JOIN HRMS_TMS_LOCATION_TYPE ON (HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID =TRAVEL_APP_LOCATION_TYPE) "
		+" LEFT JOIN HRMS_TMS_FOOD_TYPE ON (HRMS_TMS_FOOD_TYPE.FOOD_TYPE_ID =TRAVEL_APP_FOOD_TYPE) "
		+" INNER JOIN  HRMS_TMS_SCH_DTL ON (HRMS_TMS_SCH_DTL.SCH_DTL_TRAVEL_APP_ID =HRMS_TMS_TRAVEL_APP.TRAVEL_APP_ID) "
		+" WHERE TRAVEL_APP_ID="+bean.getTrAppId();  
		 Object[][] appData =getSqlModel().getSingleResult(appSql);
		 if(appData!=null && appData.length>0)
			 if(appData!=null && appData.length>0)
			 { 
				 
				 if((""+appData[0][0]).equals("G"))
				 {
					 bean.setGuestAppFlag("true");
					 bean.setHidAppForRadio("Y");
				 }else
				 {
					 bean.setGuestAppFlag("false");
					 bean.setHidAppForRadio("N");
				 }
				bean.setAppFor(""+appData[0][0]); 
				bean.setGuestName(checkNull(""+appData[0][1])); 
				bean.setTravelPurpose(""+appData[0][2]); 
				bean.setTravelPurposeId(""+appData[0][3]); 
				 if((""+appData[0][4]).equals("C"))
				 {
					 bean.setAccomFlag("true");
					 bean.setHidAccommodationReqFlag("Y");
				 }else
				 {
					 bean.setAccomFlag("false"); 
					 bean.setHidAccommodationReqFlag("N");
				 }
				bean.setAccommodationReq(""+appData[0][4]); 
				 if((""+appData[0][5]).equals("C"))
				 {
					 bean.setTrArFlag("true");
					 bean.setHidtrArrgFlag("Y");
				 }else
				 {
					 bean.setTrArFlag("false");
					 bean.setHidtrArrgFlag("N");
				 }
				bean.setTrArrg(""+appData[0][5]);
				 if((""+appData[0][6]).equals("C"))
				 {
					 bean.setHidLocalConReq("Y");
					 bean.setLocalFlag("true");
				 }else
				 {
					 bean.setHidLocalConReq("N");
					 bean.setLocalFlag("false");
				 }
				bean.setLocalConReq(""+appData[0][6]);
				bean.setTourLocType(""+appData[0][7]);
				bean.setTourLocTypeId(""+appData[0][8]);
				bean.setTourStartDate(""+appData[0][9]);
				bean.setTourEndDate(""+appData[0][10]);
				bean.setAdvanceAmt(checkNull(""+appData[0][11]));
				bean.setPayMode(""+appData[0][12]);
				bean.setSettleDate(""+appData[0][13]);
				bean.setIdProof(""+appData[0][14]);
				bean.setIdProofNumber(checkNull(""+appData[0][15]));
				bean.setAppComments(checkNull(""+appData[0][16]));
				bean.setEmpId(""+appData[0][17]);
				bean.setAppStatus(""+appData[0][18]);
				bean.setDisAppStatus(checkNull(""+appData[0][19]));
				bean.setDisIdProof(checkNull(""+appData[0][20]));
				bean.setDisPayMode(checkNull(""+appData[0][21])); 
				bean.setFoodTypeId(checkNull(""+appData[0][22])); 
				bean.setFoodType(checkNull(""+appData[0][23])); 
				bean.setTravelRequest(checkNull(""+appData[0][24])); 
				bean.setPolicyId(checkNull(""+appData[0][25]));  
				bean.setAppDate(checkNull(""+appData[0][26]));  
				bean.setHidStatus(checkNull(""+appData[0][27]));  
				System.out.println("food type Id=========  "+""+appData[0][22]);
				System.out.println("food type "+""+appData[0][23]);
			 }
		 System.out.println("bean.getHidStatus()============ "+bean.getHidStatus());
		 if(bean.getHidStatus().equals("Approved")|| bean.getHidStatus().equals("Rejected")){
			 dispApprCommets(bean);
		 } 
		 // for travel  
		 
	String travelSql =" SELECT NVL(C1.LOCATION_NAME,' '),NVL(C2.LOCATION_NAME,' '),NVL(APPJOUR_SCH_JOURMODE,0), NVL(TO_CHAR(APPJOUR_SCH_TRAVEL_DATE,'DD-MM-YYYY'),' '), "
					+" NVL(APPJOUR_SCH_TRAVEL_TIME,' '),NVL(APPJOUR_SCH_JOURMODE,0),JOURNEY_NAME ||'-'|| JOURNEY_CLASS_NAME ," 
					+" NVL(APPJOUR_DTL_FROMPALCE,0),NVL(APPJOUR_DTL_TOPLACE,0) FROM HRMS_TMS_APPJOUR_DTL "
					+" LEFT JOIN HRMS_LOCATION C1 ON(C1.LOCATION_CODE = APPJOUR_DTL_FROMPALCE) "
					+" LEFT JOIN HRMS_LOCATION C2 ON(C2.LOCATION_CODE = APPJOUR_DTL_TOPLACE) "
					+" LEFT JOIN HRMS_TMS_JOURNEY ON (APPJOUR_SCH_JOURMODE = JOURNEY_ID)  "
					+" WHERE APPJOUR_DTL_TRAPPID ="+bean.getTrAppId()+" ORDER BY APPJOUR_DTL_ID ";
	 Object[][] trData =getSqlModel().getSingleResult(travelSql);
	
	 ArrayList<Object> modeList = new ArrayList<Object>(); 
     if(trData!=null && trData.length>0)
     {  
    	 int j =1;
     	for(int i=0; i<trData.length;i++)
     	{ 
     		 
     			TravelApplication bean1 = new TravelApplication();   //for previus item
     			    
     			 request.setAttribute("jourFromPlace"+j,""+trData[i][0]);
     			 request.setAttribute("jourToPlace"+j,""+trData[i][1]); 
     			 request.setAttribute("jourFromPlaceId"+j,""+trData[i][7]);
    			 request.setAttribute("jourToPlaceId"+j,""+trData[i][8]); 
		        
		        	bean1.setTrModeClass(""+trData[i][2]);
		        	bean1.setJourDate((""+trData[i][3]).trim());
		        	bean1.setJourTime((""+trData[i][4]).trim());  
 			        request.setAttribute("trModeClassId"+j,""+trData[i][5]); 
 			        request.setAttribute("trModeClass"+j,""+trData[i][6]); 
		        	modeList.add(bean1); 
		        	j++;
     	}
     	bean.setTrModeClassList(modeList);
     }
     // for local Convaence
     String LocalCon ="SELECT NVL(LOCATION_NAME,' ') , NVL(APPLOCAL_SCH_SOURCE,' '),NVL(TO_CHAR(APPLOCAL_DTL_FROM_DATE,'DD-MM-YYYY'),' '),  "
				+"	NVL(APPLOCAL_DTL_FROM_TIME,' '), NVL(TO_CHAR(APPLOCAL_DTL_TO_DATE,'DD-MM-YYYY'),' '), "
				+"	NVL(APPLOCAL_DTL_TO_TIME,' '),APPLOCAL_SCH_CITY  FROM HRMS_TMS_APPLOCAL_DTL "
				+"	LEFT JOIN HRMS_LOCATION   ON(HRMS_LOCATION.LOCATION_CODE = APPLOCAL_SCH_CITY) "
				+"	WHERE  APPLOCAL_DTL_TRAPPID ="+bean.getTrAppId()+" ORDER BY APPLOCAL_DTL_ID ";
     Object[][] loaclConData =getSqlModel().getSingleResult(LocalCon);
     
	 ArrayList<Object> localconList = new ArrayList<Object>(); 
     if(loaclConData!=null && loaclConData.length>0)
     {  int j =1;
     	for(int i=0; i<loaclConData.length;i++)
     	{  	TravelApplication bean1 = new TravelApplication();   //for previus item
     	    request.setAttribute("localCity"+j,""+loaclConData[i][0]);
	        request.setAttribute("localCityId"+j,""+loaclConData[i][6]);   
     			   // for hotel Details
		        	bean1.setLocalSource((""+loaclConData[i][1]).trim()); 
		        	bean1.setLocalFromDate((""+loaclConData[i][2]).trim());
		        	bean1.setLocalFromTime((""+loaclConData[i][3]).trim());
		        	bean1.setLocalToDate((""+loaclConData[i][4]).trim()); 
		        	bean1.setLocalToTime((""+loaclConData[i][5]).trim());  
		        	localconList.add(bean1); 
		        	j++;
     	}
     	bean.setLocalConList(localconList);
     }else
     {
    	 addLocalCon(bean ,request);
     }
     
     // for lodging 
					
     String lodgSql="SELECT  APPLODG_SCH_HOTEL,NVL(HOTEL_TYPE_NAME,' '), NVL(APPLODG_SCH_ROOM,0),NVL(ROOM_TYPE_NAME,' '), NVL(LOCATION_NAME,' '),NVL(APPLODG_DTL_LOCATION,' ')," 
     		+" NVL(TO_CHAR(APPLODG_SCH_FROMDATE,'DD-MM-YYYY'),' '),NVL(APPLODG_SCH_FROMTIME,' '), " 
     		+" NVL(TO_CHAR(APPLODG_DTL_TODATE,'DD-MM-YYYY'),' '), NVL(APPLODG_DTL_TOTIME,' ') ,NVL(APPLODG_DTL_CITY,0) FROM HRMS_TMS_APPLODG_DTL "
			+" LEFT JOIN HRMS_LOCATION  ON(HRMS_LOCATION.LOCATION_CODE = APPLODG_SCH_CITYID) "
			+" LEFT JOIN HRMS_TMS_HOTEL_TYPE  ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID = APPLODG_SCH_HOTEL) "
            +" LEFT JOIN HRMS_TMS_ROOM_TYPE  ON(HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID = APPLODG_SCH_ROOM) "
			+" WHERE APPLODG_DTL_TRAPPID ="+bean.getTrAppId()+" ORDER BY APPLODG_DTL_ID";
    Object[][] lodgData =getSqlModel().getSingleResult(lodgSql);
    
	 ArrayList<Object> lodgList = new ArrayList<Object>(); 
     if(lodgData!=null && lodgData.length>0)
     { System.out.println("Lodging data in ifffffffffffff ");
    	 int j= 1;
	     	for(int i=0; i<lodgData.length;i++)
	     	{   
			   TravelApplication bean1 = new TravelApplication();   //for previus item  
			    request.setAttribute("hotelTypeId"+j,""+lodgData[i][0]);   
	     	    request.setAttribute("hotelType"+j,""+lodgData[i][1]); 
	     	    request.setAttribute("roomTypeId"+j,""+lodgData[i][2]);   
	     	    request.setAttribute("roomType"+j,""+lodgData[i][3]);  
	     	    request.setAttribute("lodgCity"+j,""+lodgData[i][4]);
		        request.setAttribute("lodgCityId"+j,""+lodgData[i][10]);   
		        
	        	 
	        	bean1.setLodgLocation((""+lodgData[i][5]).trim()) ;
	        	bean1.setLodgFromdate((""+lodgData[i][6]).trim()) ; 
	        	bean1.setLodgFromtime((""+lodgData[i][7]).trim()) ; 
	        	bean1.setLodgTodate((""+lodgData[i][8]).trim()) ;
	        	bean1.setLodgTotime((""+lodgData[i][9]).trim()) ; 
	        	lodgList.add(bean1); 
	        	j++;
	     	}
	     	bean.setLodgList(lodgList);
     	} else
	     {
     		 
     		addLodgList(bean ,request);
	     }
     
     radioFlagMethod(bean,request);
		 
 } //END OF METHOD
		
	
}
