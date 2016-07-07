package org.paradyne.model.payroll.salary;
import org.paradyne.lib.ModelBase;
import org.paradyne.bean.attendance.MonthAttendance;
import org.paradyne.bean.payroll.PromotionArrears;
import org.paradyne.bean.payroll.salary.PAProcess; 
import com.ibm.icu.util.Calendar;
import org.paradyne.lib.Utility;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Balaji
 * 11-08-2008
 */
/**
 * This class is used for calculate the allowance of employees.
 **/
 
public class PAProcessModel extends ModelBase{
 	 
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	/**
	 * THIS METHOD PROCESS ALL THE EMPLOYEE DATA FROM HRMS_EMP_OFFC
	 */
	
	/**
	 * @param bean
	 * @param request
	 */
	public void callProcess(PAProcess bean,HttpServletRequest request)
	 {
		  if(bean.getHidDisbType().equals("A"))
		  {
			  bean.setApprFlag("true");
		  }else
		  {
			  bean.setApprFlag("false");
		  }
		      String query ="";
		      if(bean.getPaProcessId().equals(""))
				 {    
		    	    query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , EMP_ID  FROM HRMS_EMP_OFFC "  
	                      +" WHERE EMP_STATUS ='S' AND EMP_REGULAR_DATE <= TO_DATE('"+bean.getPaToDate()+"','DD-MM-YYYY')";
		    	   }else
				 {
			    	 query = "SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , EMP_ID ,NVL(ALLW_REMARKS,' ')  "
		                   +" FROM HRMS_EMP_OFFC " 
		                   +" LEFT JOIN HRMS_ALLOWANCE_EMP_DTL ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID AND ALLW_ID = "+bean.getPaProcessId()+" ) "
		                   +" WHERE EMP_STATUS ='S' AND EMP_REGULAR_DATE <= TO_DATE('"+bean.getPaToDate()+"','DD-MM-YYYY')";
			     }
			query = setFilters(bean, query); 
			Object[][] empObj = getSqlModel().getSingleResult(query);
			ArrayList paLIst = new ArrayList();
		  double amt =0;
		  if(empObj != null && empObj.length>0)
			 {
				 for(int i =0 ; i<empObj.length;i++)
				 {
					 Object[][] appRate= getApprisalRate(""+empObj[i][1]);  //GET THE APPR RATE & SCORE
					 String [] entitleAmt = getPIAmount(bean,""+empObj[i][1]);
					 PAProcess bean1 = new PAProcess();
					 bean1.setItEmpName(String.valueOf(empObj[i][0]));
					 bean1.setItRating(String.valueOf(appRate[0][0]));
					 bean1.setItRatingScore(String.valueOf(appRate[0][1]));
					 bean1.setItEntitledAmtActual(entitleAmt[0]);
					 bean1.setItEntitledAmt(entitleAmt[1]);
					 if(bean.getHidDisbType().equals("M"))  // IF DISBURSMENT TYPE MANNULY THEN SCORE IS 100% 
					 {
						 bean1.setItEntitledAmtScore(""+100); 
					 }else
					 {
						 bean1.setItEntitledAmtScore(String.valueOf(appRate[0][1]));  // IF DISBURSMENT TYPE APPR THEN SET THIS SCORE 
					 }
					  amt = Double.parseDouble(bean1.getItEntitledAmt()) * (Double.parseDouble(bean1.getItEntitledAmtScore())/100);
					 bean1.setItPIAmt(""+Math.round(amt)); 
					 if(!bean.getHidDisbType().equals("M"))  // IF DISBURSMENT TYPE MANNULY THEN SCORE IS 100% 
					 {
						 bean1.setItTotalAmt(""+Math.round(amt)); 
					 }
					 bean1.setItEmpId(""+empObj[i][1]);  
					 if(!bean.getPaProcessId().equals(""))
					 {
					   bean1.setItRemark(""+empObj[i][2]); 
					 }
					 paLIst.add(bean1); 
					 amt =0;
					 
					 // for Monthly Details
					 
					    bean1.setMMonth(bean.getMMonth());
				    	bean1.setMMonthDays(bean.getMMonthDays());
				    	bean1.setMYear(bean.getMYear());
				    	bean1.setMAmount(bean.getMAmount()); 
				 }
				 bean.setPaList(paLIst);
			 }
	 }
	/**
	 * THIS METHOD CALCULATE THE APPR RATE & SCORE OF EMPLOYEE
	 */
	  /**
	 * @param EmpId
	 * @return Object type NotRecord
	 */
	public Object[][] getApprisalRate(String EmpId)  
	  {	  
		   Object [][] NotRecord = null;
		  // String aprSql = "SELECT NVL(APPR_SCORE_DESC,' ') ,NVL(APPR_SCORE,0)   FROM HRMS_APPRAISAL WHERE APPR_EMP_ID = "+EmpId;
		   String aprSql = "SELECT NVL(APPR_FINAL_SCORE_VALUE,' ') ,NVL(APPR_FINAL_SCORE,0) FROM PAS_APPR_SCORE "
			   			  +" WHERE APPR_ID =(SELECT MAX(APPR_ID) FROM PAS_APPR_SCORE ) AND EMP_ID = "+EmpId;
			   			//+" WHERE APPR_ID =10 AND EMP_ID = "+EmpId;
		   Object [][] result = getSqlModel().getSingleResult(aprSql); 
		   if(result != null && result.length >0)
		   {
		       return result ;
		   }
		   else
		   {
				   NotRecord = new Object [1][2];
				   NotRecord[0][0] = "DNM";
				   NotRecord[0][1] = "0";
			   return NotRecord ;
		   }
	  }
	/**
	 *  THIS METHOD GET THE PIAMOUNT BY DURATION
	 */  
	
	  /**
	 * @param bean
	 * @param EmpId
	 * @return String type returnValue
	 */
	public String [] getPIAmount(PAProcess bean,String EmpId)  
	  {	  
		  String [] returnValue = new String[2]; 
		   Object [][] NotRecord = null;
		   String PISql = " SELECT NVL(CREDIT_AMT,0) FROM HRMS_EMP_CREDIT WHERE CREDIT_CODE = "+bean.getComponentId()+"  AND EMP_ID ="+EmpId ;
		   Object [][] result = getSqlModel().getSingleResult(PISql); 
		   if(result != null && result.length >0)
		   {
			   logger.info("crdit Amount ========== >"+result[0][0]);
			   //return ""+Math.round(Double.parseDouble(""+result[0][0])*mulVal) ;
			   returnValue[0] = ""+Math.round(Double.parseDouble(String.valueOf(result[0][0])));
			   returnValue[1] = ""+Math.round(calPIAmt(bean,EmpId,Double.parseDouble(String.valueOf(result[0][0]))));
			   return returnValue;
		   }
		   else
		   { 
			   returnValue[0] = "0";
			   returnValue[1] = "0";
			   return returnValue;
		   }
	  }
	/**
	 *  THIS METHOD CALCULATE THE PIAMOUNT BY DURATION
	 */  
	
	  /**
	 * @param bean
	 * @param EmpId
	 * @return double type totalAmt
	 */
	  public double calPIAmt(PAProcess bean,String EmpId,double piAmt)
	  {
		  String fromDate = bean.getPaFromDate();
		   String toDate = bean.getPaToDate();
		   logger.info("Joining Dat >>>>>>>>>>>>>>>>>>>Call To Pi ");
		  String empQuery =" SELECT TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') FROM HRMS_EMP_OFFC "
                            +" WHERE EMP_ID ="+EmpId +" AND EMP_REGULAR_DATE <= TO_DATE('"+bean.getPaToDate()+"','DD-MM-YYYY')";
		   Object [][] empJoinData = getSqlModel().getSingleResult(empQuery); 
		  if(empJoinData !=null && empJoinData.length >0)
		  {
		   logger.info("Joining Dateeeeeee >>>>>>>>>>>>>>>>>>>   "+empJoinData[0][0]);
		   String empJoingDate = String.valueOf(empJoinData[0][0]); 
		   int chkDate = checkDate(bean.getPaFromDate(), empJoingDate); 
			  if(chkDate==-1)
			 {
				 fromDate =empJoingDate;
				 logger.info("In Joining Date========= "+fromDate);
			 }  
		  } 
		  
		 logger.info("fromDate========= "+fromDate);
		 logger.info("toDate========= "+toDate);
		     
		   String[] fromDateSplit = fromDate.split("-");
		   String[] toDateSplit =   toDate.split("-");
		   
		   double numMon =0;
		   String fromDay =fromDateSplit[0];
		   String frmMonth= fromDateSplit[1];
		   String frmYear =fromDateSplit[2];
		   String toDay =  toDateSplit[0]; 
		   String toMonth =toDateSplit[1]; 
		   String toYear = toDateSplit[2];   
		   
		    // FromMonthday  
		    Calendar cal = Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.SUNDAY);
			cal.setTime(Utility.getDate("01-" + frmMonth + "-" + frmYear));
			int fromMonthDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			
			// toMonthday 
		    Calendar cale = Calendar.getInstance();
		    cale.setFirstDayOfWeek(Calendar.SUNDAY);
			cale.setTime(Utility.getDate("01-" + toMonth + "-" + toYear));
			int toMonthDays = cale.getActualMaximum(Calendar.DAY_OF_MONTH); 
			 double mulVal =0.0;
			 double monthlyAmt =0.0;   
			    if(bean.getPeriod().equals("Monthly"))
			    {
			    	monthlyAmt = piAmt;
			    }
			    if(bean.getPeriod().equals("Quarterly"))
			    { 
			    	monthlyAmt = piAmt/3 ;
			    }
			    if(bean.getPeriod().equals("Half Yearly"))
			    { 
			    	monthlyAmt = piAmt/6 ;
			    }
			    if(bean.getPeriod().equals("Annually"))
			    {  
			    	monthlyAmt = piAmt/12 ; 
			    }
			    logger.info("Period ===========  "+bean.getPeriod());
			    logger.info("piAmt**** ======== "+piAmt);
			    logger.info("monthlyAmt ======== "+monthlyAmt);
			    Calendar calLoop = Calendar.getInstance();
			    calLoop.setFirstDayOfWeek(Calendar.SUNDAY); 
			    int calLoopMonthDay =0;
			    //variable for montly Allowence of Emp
			    
			    String mEmpId ="";
			    String mMonth ="";
			    String mMonthDays ="";
			    String mYear ="";
			    String mAmount =""; 
			    double  totalAmt=0;
			    
			  if(Integer.parseInt(frmMonth)==Integer.parseInt(toMonth) && Integer.parseInt(frmYear) == Integer.parseInt(toYear))
			   {   
			    	totalAmt= ((Double.parseDouble(toDay) -Double.parseDouble(fromDay))+1)*(monthlyAmt/toMonthDays); 
			    	    bean.setMMonth(toMonth);
				    	bean.setMMonthDays(""+((Double.parseDouble(toDay) -Double.parseDouble(fromDay))+1));
				    	bean.setMYear(toYear);
				    	bean.setMAmount(""+totalAmt);   
				    	logger.info("I am in same month");
			  }else{
			    
			     if(Integer.parseInt(frmYear)==Integer.parseInt(toYear))
			      {
			    	 numMon = Integer.parseInt(toMonth)-Integer.parseInt(frmMonth);
			    	 logger.info("I am in same Year");
			    	 for(int i =Integer.parseInt(frmMonth);i <=Integer.parseInt(toMonth);i++)
			    	  {
			    		    calLoop.setTime(Utility.getDate("01-" + i+ "-" + Integer.parseInt(frmYear)));
						     calLoopMonthDay = calLoop.getActualMaximum(Calendar.DAY_OF_MONTH); 
						     if(i==Integer.parseInt(toMonth))
						     {
						    	 mMonth+=i;
						    	 mMonthDays+=Double.parseDouble(toDay);
						    	 mYear+=frmYear;
						    	 mAmount+=Double.parseDouble(toDay) *(monthlyAmt/toMonthDays);
						     }else
						     {
						    	 if(i ==Integer.parseInt(frmMonth))
						    	 {
						    		 mMonth+=i+",";
								     mMonthDays+=((fromMonthDays - Double.parseDouble(fromDay)+1))+","; // monthdays - fromdate days
								     mYear+=frmYear+",";
								     mAmount+=Math.round(Double.parseDouble(""+((fromMonthDays - Double.parseDouble(fromDay))+1)*(monthlyAmt/fromMonthDays)))+",";
						    		 
						    	 }else
						    	 {
							     mMonth+=i+",";
							     mMonthDays+=calLoopMonthDay+","; 
							     mYear+=frmYear+",";
							     mAmount+=Math.round(Double.parseDouble(""+monthlyAmt))+",";
						    	 }
						     }
			    	}
			    	
			    	logger.info("monthlymMonth======= for "+mMonth);
			    	logger.info("monthlymMonthDays======= for "+mMonthDays);
			    	logger.info("monthlymYear======= for "+mYear);
			    	logger.info("monthlymAmount======= for "+mAmount);
			    	
			    }else
			    {
			    	logger.info("I am in Different Year");
			    	 int frmIntYear =Integer.parseInt(frmYear);
			    	 for (int i = frmIntYear ;i <=Integer.parseInt(toYear);i++)
					 {
						 for (int j =Integer.parseInt(frmMonth) ;j<=12;j++)
						 {
							   calLoop.setTime(Utility.getDate("01-" + j + "-" + i));
							   logger.info("01-" + j + "-" + i);
							   
								  calLoopMonthDay = calLoop.getActualMaximum(Calendar.DAY_OF_MONTH);   
								     logger.info("MONTH DAYS: "+calLoopMonthDay);

								  
								  ////////////////////////////////////////////////////////
								  if(j==Integer.parseInt(toMonth)&&  Integer.parseInt(toYear)==i )
								     {
								    	 mMonth+=j;
								    	 mMonthDays+=Double.parseDouble(toDay);
								    	 mYear+=frmYear;
								    	 mAmount+=Double.parseDouble(toDay) *(monthlyAmt/toMonthDays);
								     }else
								     {
								    	 if(j ==Integer.parseInt(frmMonth)&& frmIntYear==i)
								    	 {
								    		 mMonth+=j+",";
										     mMonthDays+=((fromMonthDays - Double.parseDouble(fromDay))+1)+","; 
										     mYear+=frmYear+",";
										     mAmount+=Math.round(Double.parseDouble(""+((fromMonthDays - Double.parseDouble(fromDay))+1)*(monthlyAmt/fromMonthDays)))+",";
								    		 
								    	 }else
								    	 {
									     mMonth+=j+",";
									     mMonthDays+=calLoopMonthDay+","; 
									     mYear+=frmYear+",";
									     mAmount+=Math.round(Double.parseDouble(""+monthlyAmt))+",";
								    	 }
								     }
								  //////////////////////////
								  
								  if(j==12)
								  {
									 int yearPlus = 1 + frmIntYear;
									 frmYear =""+yearPlus; 
								  }
								  
							if(Integer.parseInt(toMonth)==j && Integer.parseInt(toYear)==i ) 
							{
								break;
							}
							numMon++;
						 }  
						 frmMonth="1";
						 System.out.println("frmIntYear "+mYear);
						 
					  }
			    	 
			    }
			    bean.setMMonth(mMonth);
		    	bean.setMMonthDays(mMonthDays);
		    	bean.setMYear(mYear);
		    	bean.setMAmount(""+mAmount);  
			 
		    Double remainingFromDays = ((fromMonthDays - Double.parseDouble(fromDay))+1)*(monthlyAmt/fromMonthDays);
		    Double remainingToDays = Double.parseDouble(toDay) *(monthlyAmt/toMonthDays);
		    logger.info("remainingFromDays============Amt  "+remainingFromDays);
		    logger.info("remainingToDays============Amt  "+remainingToDays);
		       totalAmt = remainingFromDays+((numMon-1) * monthlyAmt)+remainingToDays;
			 } //else of same month
			  
		    	
		   return totalAmt;
	  }
	  
	    /**
		 *  THIS METHOD COMPARE THE TWO DATE 
		 */  
		
		  /**
		 * @param bean
		 * @param EmpId
		 * @return int type result
		 */
	 
	  public static int checkDate(String date1, String date2) {
	  		try{

	  			logger.info(" I Am in Joining Date");
	  			logger.info(" I Am in From  Date"+date1);
	  			logger.info(" I Am in Joining "+date2);
	  			int result = 0;

			    String DATE_FORMAT = "dd-MM-yyyy";

			    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);

			    java.util.Date dt1=sdf.parse(date1);

				Calendar c1=Calendar.getInstance();
				c1.setTime(dt1);

			    java.util.Date dt2=sdf.parse(date2);

				Calendar c2=Calendar.getInstance();
					c2.setTime(dt2);


			    System.out.print(""+sdf.format(c1.getTime()));
			    System.out.print("\ndate2:"+sdf.format(c2.getTime()));


			    if (c1.before(c2)) {
			       System.out.print("\n "+sdf.format(c1.getTime())+" is before "+sdf.format(c2.getTime()));
			       result = -1;
			    }
			    if (c1.after(c2)) {
			       System.out.print("\n "+sdf.format(c1.getTime())+" is after "+sdf.format(c2.getTime()));
			       result = 1;
			    }
			    if (c1.equals(c2)) {
			       System.out.print("\n "+sdf.format(c1.getTime())+" same as "+sdf.format(c2.getTime()));
			       result = 0;
			    }

			    logger.info(" Result ======= "+result);
			    return result;
			 }catch(Exception ex){
				logger.info("Error in checkDate function:"+ex);
			}
			return 9999;

		}
	  
	  /**
	  *  THIS METHOD CALCULATE THE MONTHLY ALLOWANCE
	 *  
	 /**
	 * @param EmpNo
	 * @param month
	 * @param year
	 * @param amount
	 * @param days
	 * returns boolean type result
	 */
	public  boolean getMonthWiseAllowanceData(String EmpNo,String month,String year,String amount,String days){			
		
			
					
					String empMonth[]=month.split(",");
					String empYear[]=year.split(",");
					String empAmount[]=amount.split(",");
					String empDays[]=days.split(",");
					boolean result=false;
					for(int j=0;j<empMonth.length;j++){
					
							String sql="INSERT INTO HRMS_ALLOWANCE_MTH_DTL(ALLW_ID, ALLW_EMP_ID, "
									  +" ALLW_MONTH, ALLW_YEAR, ALLW_AMOUNT, ALLW_DAYS) VALUES("
									  +" (SELECT MAX(ALLW_ID)  FROM HRMS_ALLOWANCE_HDR),"+EmpNo
									  +","+empMonth[j]+","+empYear[j]+","+empAmount[j]+","+empDays[j]+")";
							logger.info("QUERY : "+sql);		 
						    
							result= getSqlModel().singleExecute(sql);
					
					}
				
			 return result;
			
			
		}
	  
	  /**
	  *  THIS METHOD SAVE THE PROCESS
	  */  
	
	  /**
	 * @param bean
	 * @param request
	 * @return boolean type result
	 */
	 public boolean savePAProcess(PAProcess bean ,HttpServletRequest request)
	 {
		  boolean result = false;
		 
		 String []  EmpNo = (String[]) request.getParameterValues("itEmpId");
		 String []  piAmt =  (String[])request.getParameterValues("itEntitledAmt");		 
		 String []  piScore = (String[]) request.getParameterValues("itEntitledAmtScore");
		 String []  piFinalAmt =(String[]) request.getParameterValues("itPIAmt");
		 String []  remark = (String[]) request.getParameterValues("itRemark");
		 String []  actualEntitle= (String[]) request.getParameterValues("itEntitledAmtActual"); 
		 String []  itTaxAmt= (String[]) request.getParameterValues("itTaxAmt");
		 String []  itTotalAmt= (String[]) request.getParameterValues("itTotalAmt");
		 
		 String month[]=request.getParameterValues("mMonth");
		 String year[]=request.getParameterValues("mYear");
		 String amount[]=request.getParameterValues("mAmount");
		 String days[]=request.getParameterValues("mMonthDays"); 
		
		 
		  Object[][] para = new Object[1][16]; 
		  para[0][0] = bean.getPaFromDate();
		  para[0][1] = bean.getPaToDate() ; 
		  para[0][2] = bean.getComponentId(); 
		  para[0][3] = bean.getProcessDate();
		  para[0][4] = bean.getHidDisbType();
		  para[0][5] = bean.getDivisionId().equals("") ? "" :bean.getDivisionId() ;
		  para[0][6] = bean.getDeptId().equals("") ? "0" :bean.getDeptId() ;
		  para[0][7] = bean.getBranchId().equals("") ? "0" : bean.getBranchId() ;
		  para[0][8] = bean.getEmpTypeId().equals("") ? "0" :  bean.getEmpTypeId();
		  para[0][9] = bean.getPayBillId().equals("") ? "0" : bean.getPayBillId() ;
		  para[0][10] = bean.getDesgnId() .equals("") ? "0" : bean.getDesgnId() ; //Designation
		  para[0][11] = bean.getEmpId().equals("") ? "0" : bean.getEmpId();  //EmpId for Single Employee
		  para[0][12] = bean.getSalaryFlag().equals("true") ? "Y" : "N" ;             //salary Flag
		  para[0][13] = bean.getSalFrmMonth().equals("") ? "0" :bean.getSalFrmMonth();  //Adjust Able Month in Salary
		  para[0][14] = bean.getSalFrmYear().equals("") ? "0" :bean.getSalFrmYear() ;//Adjust Able Year in Salary
		  para[0][15] = bean.getPayMode(); 
		  result = getSqlModel().singleExecute(getQuery(1), para);  
		  
		  
		 
		  
		  if(result)
		  {
			   if(EmpNo!=null && EmpNo.length > 0)
			   {
				  for( int i = 0; i <EmpNo.length ;i++)
				  {
				  logger.info("i value======  "+i);
				  String dtlQuery = " INSERT INTO HRMS_ALLOWANCE_EMP_DTL(ALLW_ID, ALLW_EMP_ID, ALLW_PERCENT, ALLW_AMOUNT_ENTITLE, ALLW_AMOUNT_FINAL, ALLW_REMARKS,ALLW_ACTUAL_ENTITLE,ALLW_TAX_AMT, ALLW_TOTAL_AMT)"
					                +" VALUES((SELECT NVL(MAX(ALLW_ID),0) FROM HRMS_ALLOWANCE_HDR),"+EmpNo[i]+","+piScore[i]+"," +
					                ""+piAmt[i]+","+piFinalAmt[i]+",'"+remark[i]+"',"+actualEntitle[i]+","+itTaxAmt[i]+","+itTotalAmt[i]+")";
				  result = getSqlModel().singleExecute(dtlQuery);
				  if(result)
				  {  					   
					  boolean result1=getMonthWiseAllowanceData(EmpNo[i],month[i],year[i],amount[i],days[i]);
				  }
				  }
			   }
		  }
	 
		 return result;
	 }
	  /**
	  *  THIS METHOD UPDATE THE PROCESS
	  */  
	
	  /**
	  * @param bean
	  * @param request
	  * @return boolean type result
	  */
	 
	 public boolean updatePAProcess(PAProcess bean ,HttpServletRequest request)
	 {
		  boolean result = false;
		 
		 String []  EmpID = (String[]) request.getParameterValues("itEmpId");
		 String []  piAmt =  (String[])request.getParameterValues("itEntitledAmt");		 
		 String []  piScore = (String[]) request.getParameterValues("itEntitledAmtScore");
		 String []  piFinalAmt =(String[]) request.getParameterValues("itPIAmt");
		 String []  remark = (String[]) request.getParameterValues("itRemark"); 
		 String []  actualEntitle= (String[]) request.getParameterValues("itEntitledAmtActual");
		 String []  itTaxAmt= (String[]) request.getParameterValues("itTaxAmt");
		 String []  itTotalAmt= (String[]) request.getParameterValues("itTotalAmt");
		 
		 String month[]=request.getParameterValues("mMonth");
		 String year[]=request.getParameterValues("mYear");
		 String amount[]=request.getParameterValues("mAmount");
		 String days[]=request.getParameterValues("mMonthDays"); 
		 
		  
		  Object[][] para = new Object[1][16]; 
		  para[0][0] = bean.getPaFromDate();
		  para[0][1] = bean.getPaToDate() ;  
		  para[0][2] = bean.getComponentId(); 
		  para[0][3] = bean.getHidDisbType();
		  para[0][4] = bean.getDivisionId().trim().equals("")||bean.getDivisionId().trim().equals("null") ? "0" :bean.getDivisionId() ;
		  para[0][5] =bean.getDeptId().trim().equals("")|| bean.getDeptId().trim().equals("null") ? "0" :bean.getDeptId() ;
		  para[0][6] = bean.getBranchId().trim().equals("")|| bean.getBranchId().trim().equals("null") ? "0" : bean.getBranchId() ;
		  para[0][7] =  bean.getEmpTypeId().trim().equals("")||bean.getEmpTypeId().trim().equals("null") ? "0" :  bean.getEmpTypeId();
		  para[0][8] =  bean.getPayBillId().trim().equals("")|| bean.getPayBillId().trim().equals("null") ? "0" : bean.getPayBillId() ;
		  para[0][9] = bean.getDesgnId().trim() .equals("")||bean.getDesgnId().trim() .equals("null") ? "0" : bean.getDesgnId() ; //Designation
		  para[0][10] = bean.getEmpId().trim().equals("")||bean.getEmpId().trim().equals("null") ? "0" : bean.getEmpId();   //EmpId for Single Employee
		  para[0][11] = bean.getSalaryFlag().equals("true") ? "Y" : "N";     //bean.getSalaryFlag()                 //salary Flag
		  para[0][12] = bean.getSalFrmMonth().equals("") ? "0":bean.getSalFrmMonth();  //Adjust Able Month in Salary
		  para[0][13] = bean.getSalFrmYear().equals("") ? "0":bean.getSalFrmYear();  //Adjust Able Year in Salary
		  para[0][14] = bean.getPayMode();
		  para[0][15] = bean.getPaProcessId();
		 
		  result = getSqlModel().singleExecute(getQuery(2),para); 
		    
		  
		  String delQuery = "DELETE FROM HRMS_ALLOWANCE_EMP_DTL WHERE ALLW_ID ="+bean.getPaProcessId() ;
	      result = getSqlModel().singleExecute(delQuery);
	      boolean monthResult=false;
	      
	      //CHECK FOR BLANK MONTHLY DATA
	      String tempMonth[]=month[0].split(",");
	      
	      if(tempMonth!=null && tempMonth.length>0 && !tempMonth[0].equals("")){
	    	  String sql = "DELETE FROM HRMS_ALLOWANCE_MTH_DTL WHERE ALLW_ID ="+bean.getPaProcessId() ;
	    	  monthResult = getSqlModel().singleExecute(sql);
	      }
	      
	      
		  // insert the details Record
		  if(result)
		  {
		  if(EmpID != null && EmpID.length >0)
		   {
			   
			  for( int i = 0; i <EmpID.length ;i++)
			  {
			  
			  String dtlQuery = " INSERT INTO HRMS_ALLOWANCE_EMP_DTL(ALLW_ID, ALLW_EMP_ID, ALLW_PERCENT, ALLW_AMOUNT_ENTITLE, ALLW_AMOUNT_FINAL, ALLW_REMARKS,ALLW_ACTUAL_ENTITLE ,ALLW_TAX_AMT, ALLW_TOTAL_AMT)"
				                +" VALUES("+bean.getPaProcessId()+","+EmpID[i]+","+piScore[i]+"," +
				                ""+piAmt[i]+","+piFinalAmt[i]+",'"+remark[i]+"',"+actualEntitle[i]+","+itTaxAmt[i]+","+itTotalAmt[i]+")";
			  result = getSqlModel().singleExecute(dtlQuery);
			  if(result && monthResult)
			  {				   
				  getUpdatedMonthWiseAllowanceData(EmpID[i],month[i],year[i],amount[i],days[i],bean.getPaProcessId());
				   
			   }
			  }
		   }
		  }
	 
		 return result;
	 }
	 
	  /**
	  *  THIS METHOD UPDATE THE MONTHWISE DATA
	  */  
	
	  /**
	  * @param bean
	  * @param boolean
	  * @return boolean type result
	  */
		public  boolean getUpdatedMonthWiseAllowanceData(String EmpNo,String month,String year,String amount,String days,String processId){		 
			
			String empMonth[]=month.split(",");
			String empYear[]=year.split(",");
			String empAmount[]=amount.split(",");
			String empDays[]=days.split(",");
			boolean result=false;
			if(empMonth.length>0)
			{
			 
			  for(int j=0;j<empMonth.length;j++){
			
					String sql="INSERT INTO HRMS_ALLOWANCE_MTH_DTL(ALLW_ID, ALLW_EMP_ID, "
							  +" ALLW_MONTH, ALLW_YEAR, ALLW_AMOUNT, ALLW_DAYS) VALUES("
							  +processId+","+EmpNo
							  +","+empMonth[j]+","+empYear[j]+","+empAmount[j]+","+empDays[j]+")";
					logger.info("QUERY : "+sql);		 
				    
					result= getSqlModel().singleExecute(sql); 
			}
			}
		
	 return result;
	
	
}
		 /**
		  *  THIS METHOD DELETE THE PROCESS
		  */  
		
		  /**
		  * @param bean		  
		  * @return boolean type result
		  */ 
	 public boolean delete(PAProcess bean)
	 {    
		 boolean result =false;
		 String delDtl= "DELETE FROM HRMS_ALLOWANCE_EMP_DTL WHERE ALLW_ID ="+bean.getPaProcessId();
		  result = getSqlModel().singleExecute(delDtl); 
		  if(result)
		  {
			  String sql = "DELETE FROM HRMS_ALLOWANCE_MTH_DTL WHERE ALLW_ID ="+bean.getPaProcessId() ;
			   result = getSqlModel().singleExecute(sql); 
			  if(result)
			  {
				  String delHDR= "DELETE FROM HRMS_ALLOWANCE_HDR WHERE ALLW_ID ="+bean.getPaProcessId();
				  result = getSqlModel().singleExecute(delHDR);   
			  }
		  }
		  return result;
	 }
	 
	 /**
	  *  THIS METHOD COLLECT THE PROCESS DETAILS DATA
	  */  
	
	  /**
	  * @param bean		  
	  * @return boolean type result
	  */ 
	 
	public void callF9Details(PAProcess bean,HttpServletRequest request)
	 {
		  String sql ="SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, ALLW_EMP_ID, ALLW_PERCENT, ALLW_AMOUNT_ENTITLE, "
				  +" ALLW_AMOUNT_FINAL, NVL(ALLW_REMARKS,' ') ,NVL(ALLW_ACTUAL_ENTITLE,0),NVL(ALLW_TAX_AMT,0),NVL(ALLW_TOTAL_AMT,0) FROM HRMS_ALLOWANCE_EMP_DTL "
				  +" LEFT JOIN  HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID= HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID)  "
				  +" WHERE HRMS_ALLOWANCE_EMP_DTL.ALLW_ID ="+bean.getPaProcessId() 
		          +" ORDER BY EMP_FNAME,HRMS_EMP_OFFC.EMP_ID" ;
		    Object[][] empObj = getSqlModel().getSingleResult(sql); 
		    ArrayList paLIst = new ArrayList();
		    for(int i =0 ; i<empObj.length;i++)
			 {  
		    	 Object[][] appRate= getApprisalRate(""+empObj[i][1]); 
				 PAProcess bean1 = new PAProcess();
				 bean1.setItEmpName(String.valueOf(empObj[i][0]));
				 bean1.setItEmpId(""+empObj[i][1]); 
				 bean1.setItRating(String.valueOf(appRate[0][0]));
				 bean1.setItRatingScore(String.valueOf(appRate[0][1])); 
				 bean1.setItEntitledAmtScore(String.valueOf(empObj[i][2])); 
				 bean1.setItEntitledAmt(String.valueOf(empObj[i][3]));
				 bean1.setItPIAmt(String.valueOf(empObj[i][4])); 
				 bean1.setItRemark(String.valueOf(empObj[i][5])); 
				 bean1.setItEntitledAmtActual(String.valueOf(empObj[i][6])); 
				 bean1.setItTaxAmt(String.valueOf(empObj[i][7])); 
				 bean1.setItTotalAmt(String.valueOf(empObj[i][8])); 
				 paLIst.add(bean1);  
			 }
			 bean.setPaList(paLIst);
			 bean.setProcessStatus("done");
			 logger.info("bean.getSalaryFlag() Before "+bean.getProcessStatus());
			 
			 if(bean.getHiddenChk().equals("Y"))
			 {
				 bean.setSalaryFlag("true");
			 }else
			 {
				 bean.setSalaryFlag("false");
			 }
			 
			 if(bean.getFinaliseFlag().equals("U")||bean.getFinaliseFlag().equals("")||bean.getFinaliseFlag().equals("null")||bean.getFinaliseFlag()==null){
				 
				 bean.setFlagButton("Lock");
			 }
			 if(bean.getFinaliseFlag().equals("L")){ 
				 bean.setFlagButton("Unlock");
			 }
			 
	 }
	
	 /**
	  *  THIS METHOD LOCK THE PROCESS
	  */  
	
	  /**
	  * @param bean		
	  * @param request	
	  * @param response	 
	  */ 
	
	public void callLock(PAProcess bean,HttpServletRequest request,HttpServletResponse response)
	{
		 String res="";
		String sql ="UPDATE HRMS_ALLOWANCE_HDR SET ALLW_FINALIZE_FLAG ='L' WHERE ALLW_ID="+bean.getPaProcessId();//+bean.getPaProcessId();
	 	boolean result = getSqlModel().singleExecute(sql);
	 	if(result)
			{
				res = "Periodic Allowance Process Locked Successfully";
				/**
				 * Following code calculates the tax
				 * and updates tax process
				 */
				try {
					CommonTaxCalculationModel model = new CommonTaxCalculationModel();
					logger.info("I m calling tax calculation method");
					model.initiate(context, session);
					String dateQry = " SELECT  TO_CHAR(ALLW_PROCESS_DATE,'MM'),TO_CHAR(ALLW_PROCESS_DATE,'YYYY')  AS PRO_DATE FROM HRMS_ALLOWANCE_HDR "
								   + " WHERE ALLW_ID = "+bean.getPaProcessId();
					Object dateObj[][] = getSqlModel().getSingleResult(dateQry);
					
					String allEmpQry = " SELECT DISTINCT ALLW_EMP_ID FROM HRMS_ALLOWANCE_EMP_DTL "
						 + " WHERE ALLW_ID = "+bean.getPaProcessId();
					Object empList[][] = getSqlModel().getSingleResult(allEmpQry);
					logger.info("Process Date : "+bean.getProcessDate());
					int fromYear = Integer.parseInt(String.valueOf(dateObj[0][1]));
					int month = Integer.parseInt(String.valueOf(dateObj[0][0]));
					logger.info("Month : "+month+" Year : "+fromYear);
					if(month <=3)
						fromYear--;
					if(empList != null && empList.length > 0)
					model.calculateTax(empList,String.valueOf(fromYear),String.valueOf(fromYear+1));
					model.terminate();
				} catch (Exception e) {
					logger.error("Exception in callLock() in PAProcessModel while calling calculateTax : "+e);

			}
			}else
			{
				res = "Periodic Allowance Process Can't Lock ";
			}
			  
    		   try
    			{
    				response.setContentType("text/html");
    				PrintWriter out = response.getWriter();
    				out.print(res);
    				out.flush();
    			}
    			catch (Exception e)
    			{
    				e.printStackTrace();
    			}		
	}
	
	 /**
	  *  THIS METHOD UNLOCK THE PROCESS
	  */  
	
	  /**
	  * @param bean		
	  * @param request	
	  * @param response	 
	  */ 
	
	public void callUnLock(PAProcess bean,HttpServletRequest request,HttpServletResponse response)
	{
		 String res="";
			String sql ="UPDATE HRMS_ALLOWANCE_HDR SET ALLW_FINALIZE_FLAG ='U' WHERE ALLW_ID="+bean.getPaProcessId();//+bean.getPaProcessId();
		 	boolean result = getSqlModel().singleExecute(sql);
		 	if(result)
				{
					res = "Periodic Allowance Process UnLocked Successfully";
				}else
				{
					res = "Periodic Allowance Process Can't UnLock ";
				}
				  
	    		   try
	    			{
	    				response.setContentType("text/html");
	    				PrintWriter out = response.getWriter();
	    				out.print(res);
	    				out.flush();
	    			}
	    			catch (Exception e)
	    			{
	    				e.printStackTrace();
	    			}		
	}
	 
	 /**
	  *  THIS METHOD SET THE FILTER FOR PROCESS
	  */   
	
	 /**
	 * @param bean
	 * @param sqlQuery
	 * @return String type sqlQuery
	 */
	public String setFilters(PAProcess bean, String sqlQuery) // Set filters while calculating arrears
		{
			try
			{
				String typeCode = bean.getEmpTypeId().trim();
				String payBillNo = bean.getPayBillId().trim();
				String brnCode = bean.getBranchId().trim();
				String deptCode = bean.getDeptId().trim();
				String divCode = bean.getDivisionId().trim();
				String empID = bean.getEmpId().trim();
				logger.info("payBillId>>>>>>>>>>> "+bean.getPayBillId());
				
				if(!divCode.equals("") && !divCode.equals("null")&& !divCode.equals("0") && divCode != null )
				{
					sqlQuery += " AND EMP_DIV = "+divCode;
				//	bean.setEmpChkFlag("true");
				}
				if(!typeCode.equals("") && !typeCode.equals("null") && !typeCode.equals("0") && typeCode != null)
				{
					sqlQuery += " AND EMP_TYPE = "+typeCode;
					//bean.setEmpChkFlag("true");
				}
				if(!payBillNo.equals("") && !payBillNo.equals("null") && !payBillNo.equals("0") && payBillNo != null )
				{
					sqlQuery += " AND EMP_PAYBILL = "+payBillNo;
					//bean.setEmpChkFlag("true");
				}
				if(!brnCode.equals("") && !brnCode.equals("null")  && !brnCode.equals("0") && brnCode != null )
				{
					sqlQuery += " AND EMP_CENTER = "+brnCode;
					//bean.setEmpChkFlag("true");
				}
				if(!deptCode.equals("") && !deptCode.equals("null") && !deptCode.equals("0") && deptCode != null)
				{
					sqlQuery += " AND EMP_DEPT = "+deptCode;
					//bean.setEmpChkFlag("true");
				}
				
				if(!empID.equals("") && !empID.equals("null")&& !empID.equals("0") && empID != null )
				{
					sqlQuery += " AND EMP_ID = "+empID;
					//bean.setEmpChkFlag("true");
				}
				sqlQuery += " ORDER BY EMP_FNAME,EMP_ID ";
				return sqlQuery;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return "";
			}
		}
	 /**
	  *  THIS METHOD HANDLE THE NULL STRING
	  */   
	
	 /**
	 * @param result
	 * @return String type result
	 */ 
	
	 public String checkNull(String result) {
		 
			if (result == null || result.equals("null")) {
				return "";
			} else {
				return result;
			}
		}
	
}
