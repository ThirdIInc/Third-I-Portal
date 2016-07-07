/**
 * Balaji
 * 15-08-2008
**/

package org.paradyne.model.TravelManagement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.TravelManagement.TravelScheduleReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;



public class TravelScheduleReportModel extends ModelBase {
	
			
	/**
	 * THIS METHOD GENERATES THE SCHEDULED TRAVLE REPORT
	 * @param bean
	 * @param response
	 */
	public void report(TravelScheduleReport bean, HttpServletResponse response){
		
		//String rptType=bean.getRptType();
		ReportGenerator rg=new ReportGenerator("Pdf","Travel Schedule Report","A4");
		rg.addFormatedText("Travel Schedule Report\n", 2, 0, 1, 0);

		//rg.addText(msg, borderStyle, align, margin)
		
		
		//SHOW TRAVEL RECORDS WHICH HAVE BEEN SCHEDULED WITH STATUS='S'
		String applicantSql=" SELECT TRAVEL_ID,TO_CHAR(TRAVEL_APPDATE,'DD-MM-YYYY'),EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
						+" CENTER_NAME AS BRANCH,DEPT_NAME AS DEPT,RANK_NAME AS DESG FROM HRMS_TRAVEL"
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_TRAVEL.TRAVEL_EMPID )"
						+" LEFT  JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
						+" LEFT  JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT)"
						+" LEFT  JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
			            +" WHERE TRAVEL_SCHEDULE_STATUS='S' AND TRAVEL_ID = "+bean.getTravelReportId()  ;
		//System.out.println("\n\n\nEMP ID----"+bean.getEmpId()+"----");
/*		if(!bean.getEmpId().trim().equals("")){//IF EMPLOYEE SELECTED
			applicantSql+=" AND TRAVEL_EMPID="+bean.getEmpId();
		}if(!bean.getAppFromDate().trim().equals("")){//IF APPLICATION DATE RANGE SELECTED
			applicantSql+=" AND TRAVEL_APPDATE BETWEEN TO_DATE('"+bean.getAppFromDate()+"','DD-MM-YYYY') AND TO_DATE('"+bean.getAppToDate()+"','DD-MM-YYYY')";
		}if(!bean.getBranchId().trim().equals("")){
			applicantSql+=" AND EMP_CENTER="+bean.getBranchId();
		}if(!bean.getDeptId().trim().equals("")){
			applicantSql+=" AND EMP_DEPT="+bean.getDeptId();
		}if(!bean.getDesgId().trim().equals("")){
			applicantSql+=" AND EMP_RANK="+bean.getDesgId();
		}if(!bean.getDivisionId().trim().equals("")){
			applicantSql+=" AND EMP_DIV="+bean.getDivisionId();
		}*/
		
		Object applicantData[][]=getSqlModel().getSingleResult(applicantSql);
		
		int totalJourAmt=0;
		int totalHotelAmt=0;
		
		
		if(applicantData!=null && applicantData.length>0){//IF TRAVEL DATA AVAILABLE BASED ON FILTERS		
			
			for(int i=0;i<applicantData.length;i++){
			
								String empData="Employee Id:"+applicantData[i][2]+" Employee Name:"+applicantData[i][3]+"Branch :"+applicantData[i][4]+" \nDepartment :"+applicantData[i][5]+" Designation :"+applicantData[i][6]
								               +" Application Date: "+applicantData[i][1];  
								//rg.addFormatedText(empData, 0, 0, 0, 0);//EMPLOYEE WISE DETAILS
								
								Object data[][]=new Object[2][6];
								int width1[]={2,5,2,5,2,5};
								int allignment1[]={0,0,0,0,0,0};
								
								data[0][0]="Employee Id:";
								data[0][1]=applicantData[i][2];
								data[0][2]="Employee Name:";
								data[0][3]=applicantData[i][3];
								data[0][4]="Branch :";
								data[0][5]=applicantData[i][4];
								data[1][0]="Department :";
								data[1][1]=applicantData[i][5];
								data[1][2]="Designation :";
								data[1][3]=applicantData[i][6];
								data[1][4]="Application Date: ";
								data[1][5]=applicantData[i][1];
								
								rg.tableBodyNoBorder(data, width1, allignment1);	 						
								
								//SHOW TRAVEL RECORDS WHICH HAVE BEEN SCHEDULED WITH STATUS='S'
								String journeySql="SELECT ROWNUM,L1.LOCATION_NAME ||'-'|| L2.LOCATION_NAME PLACE,HRMS_JOURNEY.JOURNEY_NAME AS TRAVEL_MODE,"
												   +" JOURNEY_CLASS_NAME AS J_CLASS,TRAVELDTL_SCHD_MODEREF_NUMBER AS REF_NO,"
												   +" TRAVELDTL_SCHD_TICKET_NUMBER AS TIECKET_NO,TO_CHAR(TRAVELDTL_SCHD_DATE,'DD-MM-YYYY')AS SDATE,TRAVELDTL_SCHD_TIME,"
												   +" NVL(TRAVELDTL_SCHD_JOUR_COST,0),NVL(TRAVELDTL_SCHD_JOUR_EXTAMT,0),TRAVEL_ID,TRAVEL_EMPID FROM HRMS_TRAVEL_DTL"
												   +" INNER JOIN HRMS_TRAVEL ON(HRMS_TRAVEL.TRAVEL_ID = HRMS_TRAVEL_DTL.TRAVELDTL_TRAVEL_ID)"
												   +" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_TRAVEL.TRAVEL_EMPID)"
												   +" LEFT JOIN HRMS_LOCATION L1 ON (L1.LOCATION_CODE = HRMS_TRAVEL_DTL.TRAVELDTL_FROM_PLACE)"
												   +" LEFT JOIN HRMS_LOCATION L2 ON (L2.LOCATION_CODE = HRMS_TRAVEL_DTL.TRAVELDTL_TO_PLACE)"
												   +" LEFT JOIN HRMS_JOURNEY ON (HRMS_JOURNEY.JOURNEY_ID = HRMS_TRAVEL_DTL.TRAVELDTL_SCHD_JOUR_MODE)"
												   +" LEFT JOIN HRMS_JOURNEY_CLASS ON(HRMS_JOURNEY_CLASS.JOURNEY_CLASS_ID = HRMS_TRAVEL_DTL.TRAVELDTL_SCHD_JOUR_CLASS)"
												   +" WHERE TRAVELDTL_TRAVEL_ID="+applicantData[i][0];												   
								
							/*	if(!bean.getEmpId().trim().equals("")){//IF EMPLOYEE SELECTED
									journeySql+=" AND EMP_ID="+bean.getEmpId();
								}if(!bean.getJourneyFromDate().trim().equals("")){//IF JOURNEY DATE RANGE SELECTED
									journeySql+=" AND TRAVELDTL_JOURNEY_DATE BETWEEN TO_DATE('"+bean.getJourneyFromDate()+"','DD-MM-YYYY') AND TO_DATE('"+bean.getJourneyToDate()+"','DD-MM-YYYY')";
								}*/
								journeySql+=" ORDER BY TRAVELDTL_ID";
								Object journeyData[][]=getSqlModel().getSingleResult(journeySql);
								
								if(journeyData!=null && journeyData.length>0){//IF JOURNEY DETAILS OF THE APPLICANT FOR A APPLICATION EXISTS
										
										Object finalJourneydata[][]=new Object[journeyData.length+1][journeyData[0].length];
									 	
										String colNames[]={"S.No","From Place - To Place","Journey Mode","Class","Bus/Plane/Rail No","Ticket No","Journey Date","Journey Time","Journey Amount","Other Amt","Total Amount"};
										int width[]={5,20,5,10,5,5,10,10,10,10,10};
										int allignment[]={0,0,0,0,0,0,1,2,2,2,2};
										int jAmt=0,othrAmt=0;
										
										for(int index=0;index<journeyData.length;index++){
											
												finalJourneydata[index][0]=journeyData[index][0];
												finalJourneydata[index][1]=journeyData[index][1];
												finalJourneydata[index][2]=journeyData[index][2];
												finalJourneydata[index][3]=journeyData[index][3];
												finalJourneydata[index][4]=journeyData[index][4];
												finalJourneydata[index][5]=journeyData[index][5];
												finalJourneydata[index][6]=journeyData[index][6];
												finalJourneydata[index][7]=journeyData[index][7];
												finalJourneydata[index][8]=journeyData[index][8];//JOUR AMT
												finalJourneydata[index][9]=journeyData[index][9];//OTHR AMT
												finalJourneydata[index][10]=(Integer.parseInt(""+journeyData[index][8])+Integer.parseInt(""+journeyData[index][9]));
												
												
												jAmt+=Integer.parseInt(""+journeyData[index][8]);												
												othrAmt+=Integer.parseInt(""+journeyData[index][9]);
												totalJourAmt+=Integer.parseInt(""+journeyData[index][8])+Integer.parseInt(""+journeyData[index][9]);
												
										}	//end of loop
										
										////ROW FOR GRAND TOTAL	
										finalJourneydata[finalJourneydata.length-1][0]="TOTAL";
										finalJourneydata[finalJourneydata.length-1][1]="";
										finalJourneydata[finalJourneydata.length-1][2]="";
										finalJourneydata[finalJourneydata.length-1][3]="";
										finalJourneydata[finalJourneydata.length-1][4]="";
										finalJourneydata[finalJourneydata.length-1][5]="";
										finalJourneydata[finalJourneydata.length-1][6]="";
										finalJourneydata[finalJourneydata.length-1][7]="";
										finalJourneydata[finalJourneydata.length-1][8]=jAmt;
										finalJourneydata[finalJourneydata.length-1][9]=othrAmt;
										finalJourneydata[finalJourneydata.length-1][10]=totalJourAmt;
																					
										rg.addFormatedText("Journey Details", 2, 0, 0,0);
										rg.tableBody(colNames, finalJourneydata, width,allignment);
																			
								}  // end of if
								
								
								//STAY DETAILS OF THE APPLICANT FOR AN APPLICATION
								String staySql=" SELECT ROWNUM,TRAVEL_HOTEL_NAME,TRAVEL_HOTEL_ADDRESS,TO_CHAR(TRAVEL_HOTEL_CHKIN_DATE,'DD-MM-YYYY'),"
											  +" TRAVEL_HOTEL_CHKIN_TIME,TO_CHAR(TRAVEL_HOTEL_CHKOUT_DATE,'DD-MM-YYYY'),TRAVEL_HOTEL_CHKOUT_TIME,TRAVEL_HOTEL_BILL,"
											  +" TRAVEL_HOTEL_OTHER_BILL,'' FROM HRMS_TRAVEL_HOTEL"
											  +" LEFT JOIN HRMS_TRAVEL ON(HRMS_TRAVEL_HOTEL.TRAVEL_HOTEL_TRAVEL_ID = HRMS_TRAVEL.TRAVEL_ID )"
											  +" WHERE TRAVEL_ID="+applicantData[i][0];
								Object stayData[][]=getSqlModel().getSingleResult(staySql);
								
								if(stayData!=null && stayData.length>0){
								
										Object finalStayData[][]=new Object[stayData.length+1][stayData[0].length];
										String colNames[]={"S.No","Hotel","Address","Booking From Date","Booking From Time","Booking To Date","Booking To Time","Bill Amount","Other Amount","Total Amount"};
										int width[]={5,20,20,5,5,5,5,5,5,5};
										int allignment[]={0,0,0,1,1,1,1,2,2,2};
										int sAmt=0,othrAmt=0;

										for(int index=0;index<stayData.length;index++){
											
											finalStayData[index][0]=stayData[index][0];
											finalStayData[index][1]=stayData[index][1];
											finalStayData[index][2]=stayData[index][2];
											finalStayData[index][3]=stayData[index][3];
											finalStayData[index][4]=stayData[index][4];
											finalStayData[index][5]=stayData[index][5];
											finalStayData[index][6]=stayData[index][6];
											finalStayData[index][7]=stayData[index][7];//BILL AMT
											finalStayData[index][8]=stayData[index][8];//OTHR AMT
											finalStayData[index][9]=(Integer.parseInt(""+stayData[index][7])+Integer.parseInt(""+stayData[index][8]));
											
											sAmt+=Integer.parseInt(""+stayData[index][7]);
											othrAmt+=Integer.parseInt(""+stayData[index][8]);
											totalHotelAmt+=Integer.parseInt(""+stayData[index][7])+Integer.parseInt(""+stayData[index][8]);

										} // end of for loop
										
										////ROW FOR GRAND TOTAL
										finalStayData[finalStayData.length-1][0]="TOTAL";
										finalStayData[finalStayData.length-1][1]="";
										finalStayData[finalStayData.length-1][2]="";
										finalStayData[finalStayData.length-1][3]="";
										finalStayData[finalStayData.length-1][4]="";
										finalStayData[finalStayData.length-1][5]="";
										finalStayData[finalStayData.length-1][6]="";
										finalStayData[finalStayData.length-1][7]=sAmt;
										finalStayData[finalStayData.length-1][8]=othrAmt;
										finalStayData[finalStayData.length-1][9]=totalHotelAmt;
										
										rg.addFormatedText("Hotel Details", 2, 0, 0,0);
										rg.tableBody(colNames, finalStayData, width,allignment);								
									
								}   // end of if
					 	 
			}	//applicant for ends 		
			
			int widthTotalAmt[]={93,7};
			int allignmentTotalAmt[]={2,2};
			
			int totalAmt = 0;
			totalAmt = totalJourAmt + totalHotelAmt;
			Object [][] totAmt = new Object [1][2]; 
			totAmt[0][0] = "TOTAL (JOURNEY + HOTEL) ";
			totAmt[0][1] = ""+totalAmt;
			rg.addFormatedText("\n", 0, 0, 0,0);
			rg.tableBody(totAmt, widthTotalAmt,allignmentTotalAmt);	
		
		}//If applicant data available
		else{
			rg.addText("\n\n\n\n\n\t\t\t***Travel schedule data not available***", 0, 1,0);
		}
		rg.createReport(response);
	}  // end of report
	
	
}
