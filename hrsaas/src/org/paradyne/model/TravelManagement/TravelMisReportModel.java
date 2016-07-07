/**
 * @author Balaji
 *  29-08-2008
 */
package org.paradyne.model.TravelManagement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.lib.ModelBase;
import org.paradyne.bean.TravelManagement.TravelMisReport;
import org.paradyne.lib.report.ReportGenerator;

import org.paradyne.lib.Utility;

public class TravelMisReportModel extends ModelBase{

	/**
	 * This method generates the Travel MIS report
	 * @param bean
	 */
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.paradyne.lib.ModelBase.class);
	public void report(TravelMisReport bean,HttpServletResponse response){
		try {
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String toDay = sdf.format(today);
			
		//report start	
		String rptType=bean.getRptType();
		ReportGenerator rg=new ReportGenerator(rptType,"Travel MIS Report","A4");
		if(rptType.equals("Pdf"))
		{
		rg.addTextBold("Travel MIS Report",0, 1,0); 
		}else
		{
			 Object [][] title = new Object[1][3];
			 title [0][0] = "";
			 title [0][1] = "";
			 title [0][2] = "Travel MIS Report";  
			 
			 int [] cols = {20,20,30};
			 int [] align = {0,0,1};
			rg.tableBodyNoCellBorder(title,cols,align,1); 
		}
		rg.addText("\n\n", 0, 2, 0);
		rg.addTextBold("Date :"+toDay, 0, 2, 0);
		rg.addText("\n\n", 0, 2, 0);   
		
		// filter display
		Object[][] filter = new Object[7][2];
		 filter[0][0] ="";
		 filter[0][1] ="";
		 filter[1][0] ="";
		 filter[1][1] ="";
		 filter[2][0] ="";
		 filter[2][1] ="";
		 filter[3][0] ="";
		 filter[3][1] ="";
		 filter[4][0] ="";
		 filter[4][1] ="";
		 filter[5][0] ="";
		 filter[5][1] ="";
		 filter[6][0] ="";
		 filter[6][1] ="";
		 
		
		  if(!bean.getFrmDate().equals(""))
		  {
			  filter [0][0]= "From Date: "+bean.getFrmDate(); 
			  filter[0][1] = "To Date: "+bean.getToDate();
		  }
		  if(!bean.getTrBranCode().equals(""))
		  {
			  filter [1][0]= "Branch: "+bean.getTrBranch();
			  
		  }
		  if(!bean.getTrDeptCode().equals(""))
		  {
			  filter [1][1]= "Department : "+bean.getTrDept();
			  
		  }
		  
		  if(!bean.getTrDesgCode().equals(""))
		  {
			  filter [2][0]= "Designation : "+bean.getTrDesg();
			  
		  }
		  if(!bean.getTrDivCode().equals(""))
		  {
			  filter [2][1]= " Division : "+bean.getTrDiv(); 
		  }
		  if(!bean.getStatus().equals("1"))
		  {
			   if(bean.getStatus().equals("P"))
			   {
				   filter [3][0]= "Status: "+"Pending"; 
			   }
			   if(bean.getStatus().equals("A"))
			   {
				   filter [3][0]= "Status: "+"Approved";
			   }
			   if(bean.getStatus().equals("R"))
			   {
				   filter [3][0]= "Status: "+"Rejected"; 
			   }
			   
			   if(bean.getStatus().equals("S"))
			   {
				   filter [3][0]= "Status: "+"Scheduled"; 
			   }
			  
		  }
			if(!bean.getEmpId().equals(""))
		 	{
				 filter [4][0] = "Employee Id: "+bean.getEmpToken() +" Employee Name:  "+bean.getEmpName();
		 		//rg.addText("Employee Token: "+bean.getEmpToken() +" Employee Name:  "+bean.getEmpName(), 0, 0, 0);
		 	}
			
			if (!(bean.getCancelStatus().equals("1")) )
			{
				
				 if(bean.getCancelStatus().equals("C"))
				   {
					   filter [5][0]= "Cancel Status: "+"Confirm"; 
				   }
				   if(bean.getCancelStatus().equals("K"))
				   {
					   filter [5][0]= "Cancel Status: "+"Cancel";
				   }
				  
				   if(bean.getCancelStatus().equals("N"))
				   {
					   filter [6][0]= "Cancel Status: "+"Cancel in Process"; 
				   }
 
			} // end of if
			 
			int [] bcellWidth={45,45};
			int [] bcellAlign={0,0};
			if(bean.getRptType().equals("Pdf"))
			{
		 	rg.tableBodyNoBorder(filter,bcellWidth,bcellAlign);
			}else
			{
				 boolean flag =false;
				 try
				 {
				  for (int i =0 ;i<filter.length;i++)
				  {
					  for (int j =0 ;j<2;i++)
					  {
						  if(!(filter [i][j].equals("")))
						  {
							  flag = true;  
						  }
					  }
				  }
				 }catch (Exception e) {
				 logger.info("error in filter ");	 
				} 
				  if(flag)
				  { 
				   rg.tableBody(filter,bcellWidth,bcellAlign);	
				  }
			} 
			// end of else
		
		String colNames[]={"Sr.No.","Employee Name","Application Date","From Place","To Place","Journey Date","Journey Time","Journey Mode","Class","Status","Journey Cost","Other Cost","Journey Cancel Cost","Hotel Name","Hotel Bill","Other Hotel Bill","Hotel Cancel Cost"};
		int cellWidth[]={14,40,20,21,21,29,29,29,21,29,21,20,20,20,20 ,20,20};
		int alignment[]={0,0,0,0,0,1,0,0,0,2,2,2,2,0,2,2,2}; 
		
		String headerQuery ="  SELECT TRAVEL_ID FROM HRMS_TRAVEL "
							+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_TRAVEL.TRAVEL_EMPID) "
							+" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER )  "
							+" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
							+" INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )" 
							+" WHERE 1=1 ";
		
		if (!(bean.getFrmDate().equals(""))
				&& !(bean.getFrmDate() == null)
				&& !bean.getFrmDate().equals("null")) {

			headerQuery += " AND TRAVEL_APPDATE  BETWEEN TO_DATE('"
					+ bean.getFrmDate() + "','DD-MM-YYYY')";
			if (!(bean.getToDate().equals(""))
					&& !(bean.getToDate() == null)
					&& !bean.getToDate().equals("null")) {

				headerQuery += " AND  TO_DATE('" + bean.getToDate()
						+ "','DD-MM-YYYY')";
			} else {
				headerQuery += " AND  TO_DATE(SYSDATE,'DD-MM-YYYY')";
			}
		}

		if (!(bean.getEmpId().equals(""))
				&& !(bean.getEmpId() == null)
				&& !bean.getEmpId().equals("null")) {

			headerQuery += " AND TRAVEL_EMPID ="
					+ bean.getEmpId();
		}

		if (!(bean.getTrBranCode().equals(""))
				&& !(bean.getTrBranCode() == null)
				&& !bean.getTrBranCode().equals("null")) {

			headerQuery += " AND HRMS_EMP_OFFC.EMP_CENTER ="
					+ bean.getTrBranCode();
		}

		if (!(bean.getTrDeptCode().equals(""))
				&& !(bean.getTrDeptCode() == null)
				&& !bean.getTrDeptCode().equals("null")) {

			headerQuery += " AND HRMS_EMP_OFFC.EMP_DEPT ="
					+ bean.getTrDeptCode();
		}

		if (!(bean.getTrDesgCode().equals(""))
				&& !(bean.getTrDesgCode() == null)
				&& !bean.getTrDesgCode().equals("null")) {

			headerQuery += " AND HRMS_EMP_OFFC.EMP_RANK ="
					+ bean.getTrDesgCode();
		}

		if (!(bean.getTrDivCode().equals(""))
				&& !(bean.getTrDivCode() == null)
				&& !bean.getTrDivCode().equals("null")) {

			headerQuery += " AND HRMS_EMP_OFFC.EMP_DIV ="
					+ bean.getTrDivCode();
		}
		if (!(bean.getStatus().equals("1"))
				&& !(bean.getStatus() == null)
				&& !bean.getStatus().equals("null")) {

			
			 if(bean.getStatus().equals("S"))
			   {
				 headerQuery += " AND TRAVEL_SCHEDULE_STATUS ='S'  AND TRAVEL_CONFIRM_STATUS ='D'";						 
			   }else
			   {
				   headerQuery += " AND TRAVEL_STATUS ='"
						+ bean.getStatus()+"'";
			   }
			 
		}
		
		if (!(bean.getCancelStatus().equals("1"))
				&& !(bean.getCancelStatus() == null)
				&& !bean.getCancelStatus().equals("null")) {

			headerQuery += " AND TRAVEL_CONFIRM_STATUS ='"
					+ bean.getCancelStatus()+"'";
		}
		headerQuery += " ORDER BY  TRAVEL_APPDATE DESC ,TRAVEL_ID   ";
		
		 Object appData[][]=getSqlModel().getSingleResult(headerQuery);
		 int rowNum =1;
	 if(appData.length>0 && appData != null)
		{
		 double tJour =0;
		 double tExJour =0;
		 double tCancelJour =0;
		 double tHotel =0;
		 double tExHotel =0;
		 double tCancelHotel=0;
		 System.out.println("appData.length=========== "+appData.length);
		for(int k=0; k <appData.length ;k++)
		{
			System.out.println("appData[k][0] checking=========== "+appData[k][0]);
		String jourQuery="SELECT NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' '),TO_CHAR(TRAVEL_APPDATE,'DD-MM-YYYY'),L1.LOCATION_NAME FROMPLACE ,L2.LOCATION_NAME TOPLACE,NVL(TO_CHAR(TRAVELDTL_SCHD_DATE,'DD-MM-YYYY'),' '),NVL(TRAVELDTL_SCHD_TIME,' '),NVL(HRMS_JOURNEY.JOURNEY_NAME,' '),NVL(HRMS_JOURNEY_CLASS.JOURNEY_CLASS_NAME,' ')," +
		            " CASE WHEN TRAVEL_SCHEDULE_STATUS ='S' THEN DECODE(TRAVEL_CONFIRM_STATUS,'D','Schedule','C','Confirm','N','Cancel In Process','K','Cancel') ELSE  DECODE(TRAVEL_STATUS,'P','Pending','A','Approved','R','Rejected') END ,"+
				    " NVL(TRAVELDTL_SCHD_JOUR_COST,0),NVL(TRAVELDTL_SCHD_JOUR_EXTAMT,0), " +
					" TRAVELDTL_FROM_PLACE,TRAVELDTL_TO_PLACE,TRAVELDTL_SCHD_JOUR_MODE,TRAVELDTL_SCHD_JOUR_CLASS,TRAVEL_ID,TRAVEL_EMPID,NVL(TRAVELDTL_SCHD_CANCEL_AMT,0) FROM HRMS_TRAVEL_DTL"+
					" LEFT JOIN HRMS_TRAVEL ON(HRMS_TRAVEL.TRAVEL_ID = HRMS_TRAVEL_DTL.TRAVELDTL_TRAVEL_ID)"+
					" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_TRAVEL.TRAVEL_EMPID)"+
					" LEFT JOIN HRMS_LOCATION L1 ON (L1.LOCATION_CODE = HRMS_TRAVEL_DTL.TRAVELDTL_FROM_PLACE)"+
					" LEFT JOIN HRMS_LOCATION L2 ON (L2.LOCATION_CODE = HRMS_TRAVEL_DTL.TRAVELDTL_TO_PLACE)"+
					" LEFT JOIN HRMS_JOURNEY ON (HRMS_JOURNEY.JOURNEY_ID = HRMS_TRAVEL_DTL.TRAVELDTL_SCHD_JOUR_MODE)"+
					" LEFT JOIN HRMS_JOURNEY_CLASS ON(HRMS_JOURNEY_CLASS.JOURNEY_CLASS_ID = HRMS_TRAVEL_DTL.TRAVELDTL_SCHD_JOUR_CLASS)"+
				   	" WHERE 1=1 AND HRMS_TRAVEL.TRAVEL_ID = "+appData[k][0]+" ORDER BY  TRAVEL_APPDATE  DESC";  
		Object jourData[][]=getSqlModel().getSingleResult(jourQuery); 
		
		String hotelQuery =" SELECT NVL(TRAVEL_HOTEL_NAME,' '), NVL(TRAVEL_HOTEL_BILL,0), NVL(TRAVEL_HOTEL_OTHER_BILL,0),NVL(TRAVEL_HOTEL_CANCEL_AMT,0) FROM HRMS_TRAVEL_HOTEL  "
                          +" WHERE TRAVEL_HOTEL_TRAVEL_ID ="+appData[k][0]; 
		Object hotelData[][]=getSqlModel().getSingleResult(hotelQuery); 
		
		if(jourData.length>0 && jourData!=null)
		{     int length =0; 
			  if(jourData.length >= hotelData.length) 
				  length =jourData.length; 
			  else 
				  length =hotelData.length;
			  
					    Object [][] data = new Object [length][17];
					   // Object [][] appSum = new Object [1][14];
						for(int i =0 ; i <length;i++)
						{  
							if(i <length)
							 {
							  if(i<jourData.length)
							  {
								  data [i][0] =  rowNum;
								  data [i][1] = jourData[i][0];
								  data [i][2] = jourData[i][1];
								  data [i][3] = jourData[i][2];
								  data [i][4] = jourData[i][3];
								  data [i][5] = jourData[i][4];
								  data [i][6] = jourData[i][5] ;
								  data [i][7] = jourData[i][6];
								  data [i][8] = jourData[i][7];
								  data [i][9] = jourData[i][8];
								  if(String.valueOf(jourData[i][17]).equals("")||String.valueOf(jourData[i][17]).equals("0"))
								  {
								  data [i][10] =  jourData[i][9];
								  data [i][11] = jourData[i][10]; 
							      }else
							      {
							    	  data [i][10] =  "0";
									  data [i][11] = "0";   
							      }
								  data [i][12] = jourData[i][17]; 
								//  jourCost += Math.round(Double.parseDouble(""+jourData[i][8]));
								 // jourExCost += Math.round(Double.parseDouble(""+jourData[i][9]));
								  tCancelJour += Math.round(Double.parseDouble(""+jourData[i][17]));
								  tJour += Math.round(Double.parseDouble(""+data [i][10]));
								  tExJour  += Math.round(Double.parseDouble(""+data [i][11]));
								  }else{
									  data [i][0] = rowNum;
									  data [i][1] = "";
									  data [i][2] = "";
									  data [i][3] = "";
									  data [i][4] = "";
									  data [i][5] = "";
									  data [i][6] = "";
									  data [i][7] = "";
									  data [i][8] = "";
									  data [i][9] = "";
									  data [i][10] = ""; 
									  data [i][11] = ""; 
									  data [i][12] = ""; 
								  } 
							  if(i<hotelData.length)
								  {
								  data [i][13] = hotelData[i][0] ;
								  if(String.valueOf(hotelData[i][3]).equals("")||String.valueOf(hotelData[i][3]).equals("0"))
								  {
								  data [i][14] = hotelData[i][1] ; 
								  data [i][15] = hotelData[i][2] ; 
								  }else
								  {
									  data [i][14] = "0" ; 
									  data [i][15] = "0" ;  
								  }
								  data [i][16] = hotelData[i][3] ; 
								//  hotelCost += Math.round(Double.parseDouble(""+hotelData[i][1]));
								//  hotelExCost += Math.round(Double.parseDouble(""+hotelData[i][2]));
								  tHotel += Math.round(Double.parseDouble(""+data [i][14]));
								  tExHotel += Math.round(Double.parseDouble(""+data [i][15]));
								  tCancelHotel += Math.round(Double.parseDouble(""+hotelData[i][3]));
								  
								  }else{
									  data [i][13] = "" ;
									  data [i][14] = "" ; 
									  data [i][15] = ""; 
									  data [i][16] = ""; 
								  } 
							  rowNum++;
						}/*else
						{
						  	
							  data [i][0] = "TOTAL";
							  data [i][1] = "";
							  data [i][2] = "";
							  data [i][3] = "";
							  data [i][4] = "";
							  data [i][5] = "";
							  data [i][6] = "" ;
							  data [i][7] = "";
							  data [i][8] = "";
							  data [i][9] = jourCost;
							  data [i][10] = jourExCost; 
							  data [i][11] = "" ;
							  data [i][12] = hotelCost ; 
							  data [i][13] = hotelExCost ; 
						 } 	*/
					} 
						
						if(k==0)
						{
					       rg.tableBody(colNames,data, cellWidth, alignment);
						}else
						{
							
							  rg.tableBody(data, cellWidth, alignment); 
						}
			
		}  //end of if 
		
		 }//end of for loop
		  rg.addText("\n".trim(), 0, 0, 0);
		  Object [][] totObj = new Object [1][17];
		  totObj [0][0] = "";
		  totObj [0][1] = "Grand Total";
		  totObj [0][2] = "";
		  totObj [0][3] = "";
		  totObj [0][4] = "";
		  totObj [0][5] = "";
		  totObj [0][6] = "";
		  totObj [0][7] = "";
		  totObj [0][8] = "";
		  totObj [0][9] = "";
		  totObj [0][10] =  tJour;
		  totObj [0][11] = tExJour; 
		  totObj [0][12] = tCancelJour;
		  totObj [0][13] = ""; 
		  totObj [0][14] = tHotel;
		  totObj [0][15] = tExHotel;    
		  totObj [0][16] = tCancelHotel;    
	 	  rg.tableBodyNoCellBorder(totObj, cellWidth, alignment,1); 
		}  // end of if 
		else{
			rg.addText("There are no data to display", 0, 1, 0);
		}
		
		rg.createReport(response);
		
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.info("Exception in Travel Mis Report=="+e);
		}
		
	}  // end of report.
	
}
