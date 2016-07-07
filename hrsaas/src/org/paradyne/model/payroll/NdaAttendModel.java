/**
 * 
 */
package org.paradyne.model.payroll;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;



import org.paradyne.bean.payroll.NdaAttend;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author MuzaffarS
 * 
 */
public class NdaAttendModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	boolean debug=true;

	public void getList(NdaAttend bean) {
		String date = bean.getNdaDate();
				
		logger.info(" the nda date is "+date);
		String year= date.substring(6, date.length());
		
		String sqlSelect = " SELECT  ATT_CODE,ATT_EMP_ID,ATT_SHIFT_ID,ATT_NDA_HRS FROM HRMS_DAILY_ATTENDANCE_"+year+" ";
		Object[][] data = getSqlModel().getSingleResult(sqlSelect,
				new Object[][] {});

		ArrayList<NdaAttend> atlist = new ArrayList<NdaAttend>();
		logger.info("into model ---------");

		for (int i = 0; i < data.length; i++) {

			NdaAttend bean1 = new NdaAttend();
			bean1.setEmpToken(String.valueOf(data[i][0]));
			logger.info("into mode data1" + String.valueOf(data[i][0]));
			bean1.setEmpName(String.valueOf(data[i][1]));
			logger.info("into mode data1" + String.valueOf(data[i][1]));
			bean1.setShift(String.valueOf(data[i][2]));
			logger.info("into mode data1" + String.valueOf(data[i][2]));
			bean1.setNdaHrs(String.valueOf(data[i][3]));
			logger.info("into mode data1" + String.valueOf(data[i][3]));
			bean1.setNdaApprove("");

			atlist.add(bean1);
		}
		bean.setIterat(atlist);

	}
	
	public boolean getNdaRecord(NdaAttend bean, String pbId) {
		try{
		logger.info("the value o f  pay bill id is" + pbId);

		ArrayList<NdaAttend> atlist = new ArrayList<NdaAttend>();
		String date = bean.getNdaDate();
		String loginDate=date;
		
		logger.info(" the nda date is "+date);
		String year= date.substring(6, date.length());
		String month= date.substring(3, 5);
		String DD=date.substring(0,2);
		int logoutDate1= Integer.parseInt(String.valueOf(DD))+1;
		String logoutDay=String.valueOf(logoutDate1);
		logger.info(" the nda date is "+logoutDay);
		Calendar cal = Calendar.getInstance();
		cal.setTime(Utility.getDate(01 + "-" +month + "-" + year));
		int daysOfMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		String lastDayOfMonth =daysOfMonth+ "-" +month + "-" + year;
		
		String logoutDate=logoutDay+"-"+month + "-" + year;
		if( date.equalsIgnoreCase(String.valueOf(lastDayOfMonth)))
		{
			logoutDate=String.valueOf("01")+"-"+month + "-" + year;
		}
		
		logger.info(" the year is "+year);
		
		String sel = " SELECT Distinct NVL(HRMS_DAILY_ATTENDANCE_"+year+".ATT_EMP_ID,'0') , SHIFT_NIGHT_FLAG ,NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), "+
		  " NVL(EMP_FNAME,'') ||'  '|| NVL(EMP_MNAME,'')||' '||NVL(EMP_LNAME,'') ,NVL(ATT_SHIFT_ID, ' ' ),NVL(ATT_NDA_HRS_APPROVED,'0') FROM HRMS_DAILY_ATTENDANCE_"+year+" "+
		  " INNER JOIN HRMS_SHIFT ON(TRIM(HRMS_DAILY_ATTENDANCE_"+year+".ATT_SHIFT_ID)=TRIM(HRMS_SHIFT.SHIFT_ID )) "+
		  " INNER JOIN HRMS_EMP_OFFC ON (TRIM(HRMS_EMP_OFFC.EMP_ID) "+
		      " =TRIM(HRMS_DAILY_ATTENDANCE_"+year+".ATT_EMP_ID))	WHERE HRMS_EMP_OFFC.EMP_paybill='"+pbId+"' "+
		      " AND   ATT_DATE=to_date('"+date+"','dd-mm-yyyy') AND HRMS_SHIFT.SHIFT_NIGHT_FLAG ='Y' AND ATT_STATUS!='AA'  ";
	
		Object[][] selec = getSqlModel().getSingleResult(sel, new Object[] {});
		
		if(selec.length==0)
		{
			return false;
		}
		
		for (int i = 0; i < selec.length; i++) {			
			logger.info(" length of year is "+selec.length);
			String send=null;
			
		if(String.valueOf(selec[i][1]).equalsIgnoreCase("Y") )
		 {
			NdaAttend bean1 = new NdaAttend();
			bean1.setEmpToken(String.valueOf(selec[i][2]));
			bean1.setEmpName(String.valueOf(selec[i][3]));
			bean1.setShift(String.valueOf(selec[i][4]));
			bean1.setEmpId(String.valueOf(selec[i][0]));
			bean1.setNdaApprove(String.valueOf(selec[i][5]));
			atlist.add(bean1);
			/*
			logger.info("the flag here is" + selec[i][1]);
		
			NdaAttend bean1 = new NdaAttend();
			
			bean1.setEmpToken(String.valueOf(selec[i][2]));
			bean1.setEmpName(String.valueOf(selec[i][3]));
			bean1.setShift(String.valueOf(selec[i][4]));
			bean1.setEmpId(String.valueOf(selec[i][0]));


			   String login=" select  NVL(HRMS_DAILY_ATTENDANCE_"+year+".ATT_EMP_ID,'0') ,to_char(ATT_LOGIN,'dd-mm-yyyy hh24:mi:ss') "+
			   " from HRMS_DAILY_ATTENDANCE_"+year+"  "+
			   " INNER JOIN HRMS_SHIFT ON(TRIM(HRMS_DAILY_ATTENDANCE_"+year+".ATT_SHIFT_ID)=TRIM(HRMS_SHIFT.SHIFT_ID )) "+
			   " WHERE HRMS_DAILY_ATTENDANCE_"+year+".ATT_DATE=to_date('"+date+"','dd-MM-yyyy') "+
			    " AND HRMS_SHIFT.SHIFT_NIGHT_FLAG ='Y' and ATT_EMP_ID="+ selec[i][0]; 
			   
			  
			   String logout=" select  NVL(HRMS_DAILY_ATTENDANCE_"+year+".ATT_EMP_ID,'0') ,to_char(ATT_LOGOUT,'dd-mm-yyyy hh24:mi:ss') "+
			   " from HRMS_DAILY_ATTENDANCE_"+year+"  "+
			   " INNER JOIN HRMS_SHIFT ON(TRIM(HRMS_DAILY_ATTENDANCE_"+year+".ATT_SHIFT_ID)=TRIM(HRMS_SHIFT.SHIFT_ID )) "+
			   " WHERE HRMS_DAILY_ATTENDANCE_"+year+".ATT_DATE=to_date('"+logoutDate+"','dd-MM-yyyy') "+
			    " AND HRMS_SHIFT.SHIFT_NIGHT_FLAG ='Y' and ATT_EMP_ID="+ selec[i][0]; 
			

				Object[][] date1 = getSqlModel().getSingleResult(login);
				Object[][] date2 = getSqlModel().getSingleResult(logout);
				logger.info("**********************************************"+date1[0][1]+"####################"+date2[0][1]);
						
				String date_1=String.valueOf(date1[0][1]);
				String date_2=String.valueOf(date2[0][1]);
				String dateDiffQuery="select ((to_date('"+date_2+"','dd-mm-yyyy hh24:mi:ss') - "+
						 " to_date('"+date_1+"','dd-mm-yyyy hh24:mi:ss'))*24*3600) hours from dual";
				
				Object[][] hours = getSqlModel().getSingleResult(dateDiffQuery);
				logger.info("HOurs||||||||||||||||||||||||||||||||||||"+String.valueOf(hours[0][0]));
				
				double h=Double.parseDouble(String.valueOf(hours[0][0]));
				int mod = (int)h %3600;
				logger.info("Value of mod "+mod);
				h/=3600;
				int hr=(int)(h);
				int sec = mod % 60;
				int min = mod / 60;
				send=hr+"." +min;
				//int sec=(int)(imod);
				//imod/=60;	
				//int min=(int)(i/60);
				logger.info("Date in hour *********" +hr + "minute is......" + min);
				logger.info("Date in *********"+hr+":" +min+":"+sec);
				bean1.setNdaHrs(String.valueOf(send));
				bean1.setNdaApprove(String.valueOf(send));
				
				String upd = " UPDATE  HRMS_DAILY_ATTENDANCE_"+year+" SET ATT_NDA_HRS = "
				+ String.valueOf(send)
				+ " ,ATT_NDA_HRS_APPROVED="
				+ String.valueOf(send)
				+ " where HRMS_DAILY_ATTENDANCE_"+year+".ATT_EMP_ID="
				+ String.valueOf(selec[i][0]) +" AND ATT_DATE=TO_DATE('"+date+"','DD-MM-YYYY')";

	boolean 	result = getSqlModel().singleExecute(upd,new Object[][]{{}});
	
				//String res=hr+":" +min+":"+sec;	 
			
			//Object[][] data2 = getSqlModel().getSingleResult(query2,
				//	new Object[] {});
			
			//String prces = String.valueOf(data2[0][0]);
			//String day = prces.substring(1, 3);
			
			//logger.info("the value of data 5,8: 6,8:-"
			//		+ (prces.substring(4,5))+"*******"+(prces.substring(6,8))+" the sring oblject is"+prces);
			
			//int min = Integer.parseInt(prces.substring(4,5));
			//logger.info("the value of data process is"
			//		+ Integer.parseInt(prces.substring(4,5)));
			try{
				if(min==0)
				{
					// send = day.concat((".00"));
					bean1.setNdaHrs(String.valueOf(send));
					bean1.setNdaApprove(String.valueOf(send));
					String upd = " UPDATE  HRMS_DAILY_ATTENDANCE_"+year+" SET ATT_NDA_HRS = "
							+ String.valueOf(send)
							+ " ,ATT_NDA_HRS_APPROVED="
							+ String.valueOf(send)
							+ " where HRMS_DAILY_ATTENDANCE_"+year+".ATT_EMP_ID="
							+ String.valueOf(selec[i][0]) +" AND ATT_DATE=TO_DATE('"+date+"','DD-MM-YYYY')";

				boolean 	result = getSqlModel().singleExecute(upd,new Object[][]{{}});
					
				}
				else if (0 > min || min < 15) {
				 //send = day.concat((".25"));
				bean1.setNdaHrs(String.valueOf(send));
				bean1.setNdaApprove(String.valueOf(send));
				String upd = " UPDATE  HRMS_DAILY_ATTENDANCE_"+year+" SET ATT_NDA_HRS = "
						+ String.valueOf(send)
						+ " ,ATT_NDA_HRS_APPROVED="
						+ String.valueOf(send)
						+ " where HRMS_DAILY_ATTENDANCE_"+year+".ATT_EMP_ID="
						+ String.valueOf(selec[i][0]) +" AND ATT_DATE=TO_DATE('"+date+"','DD-MM-YYYY')";

			boolean 	result = getSqlModel().singleExecute(upd,new Object[][]{{}});
			}

			else if (15 >= min || min < 30) {
				logger.info("into ");
				// send = day.concat(".50");
				bean1.setNdaHrs(String.valueOf(send));
				bean1.setNdaApprove(String.valueOf(send));
				String upd = " UPDATE  HRMS_DAILY_ATTENDANCE_"+year+" SET ATT_NDA_HRS = "
						+ String.valueOf(send)
						+ " ,ATT_NDA_HRS_APPROVED="
						+ String.valueOf(send)
						+ "  where HRMS_DAILY_ATTENDANCE_"+year+".ATT_EMP_ID="
						+ String.valueOf(selec[i][0])+" AND ATT_DATE=TO_DATE('"+date+"','DD-MM-YYYY')";

				boolean 	result = getSqlModel().singleExecute(upd,new Object[][]{{}});
			}
			else if (30 >= min || min < 45) {
				// send = day.concat(".75");
				bean1.setNdaHrs(String.valueOf(send));
				bean1.setNdaApprove(String.valueOf(send));
				String upd = " UPDATE  HRMS_DAILY_ATTENDANCE_"+year+" SET ATT_NDA_HRS = "
						+ String.valueOf(send)
						+ " ,ATT_NDA_HRS_APPROVED="
						+ String.valueOf(send)
						+ " where HRMS_DAILY_ATTENDANCE_"+year+".ATT_EMP_ID="
						+ String.valueOf(selec[i][0])+" AND ATT_DATE=TO_DATE('"+date+"','DD-MM-YYYY')";

				boolean 	result = getSqlModel().singleExecute(upd,new Object[][]{{}});
			}

			else if (45 >= min || min < 60) {
				// send = day.concat(".75");
				bean1.setNdaHrs(String.valueOf(send));
				bean1.setNdaApprove(String.valueOf(send));
				String upd = " UPDATE  HRMS_DAILY_ATTENDANCE_"+year+" SET ATT_NDA_HRS = "
						+ String.valueOf(send)
						+ " ,ATT_NDA_HRS_APPROVED="
						+ String.valueOf(send)
						+ " where HRMS_DAILY_ATTENDANCE_"+year+".ATT_EMP_ID="
						+ String.valueOf(selec[i][0])+" AND ATT_DATE=TO_DATE('"+date+"','DD-MM-YYYY')";

				boolean 	result = getSqlModel().singleExecute(upd,new Object[][]{{}});
			}
				

				bean1.setNdaHrs(String.valueOf(send));
				bean1.setNdaApprove(String.valueOf(send));
			}
			catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			boolean tr = atlist.add(bean1);
		*/}		
		}
		bean.setIterat(atlist);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
				
		return true;

	}
																						
	public boolean getNdaSave(NdaAttend bean, String pbId, String [] ndaHrs ,String [] empid,String []emptoken ,String []empname ) 
	{

		Boolean result=false;
		String date = bean.getNdaDate();
		String year= date.substring(6, date.length());
				
		ArrayList<NdaAttend> atlist = new ArrayList<NdaAttend>();
		
		for (int i = 0; i < ndaHrs.length; i++) {
			
			
			logger.info(" employee id is "+empid [i]+" nda hra is"+ndaHrs[i]);
			if(ndaHrs[i]=="" || ndaHrs[i].equalsIgnoreCase("") || ndaHrs[i]==null)
			{
				ndaHrs[i]=String.valueOf(0);
			}
			
			String save = "update HRMS_DAILY_ATTENDANCE_"+year+" set ATT_NDA_HRS_APPROVED="
					+ndaHrs[i] + " where HRMS_DAILY_ATTENDANCE_"+year+".ATT_EMP_ID="
					+empid[i]+" AND ATT_DATE=TO_DATE('"+date+"','DD-MM-YYYY')";
			 	result = getSqlModel().singleExecute(save,new Object[][]{{}});
			logger.info("updated"+result);
			NdaAttend bean1 = new NdaAttend();			
			
			try {
				bean1.setNdaHrs(String.valueOf(ndaHrs[i]));
				bean1.setNdaApprove(String.valueOf(ndaHrs[i]));
				logger.info("EMPID*****************************************"+String.valueOf(empid[i]));
				bean1.setEmpId(String.valueOf(empid[i]));
				//logger.info("EMPTOKEN="+String.valueOf(emptoken[i]));
				//bean1.setEmpToken(String.valueOf(emptoken[i]));
				//bean1.setEmpName(String.valueOf(empname[i]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//an1.setShift(String.valueOf(shift));
			atlist.add(bean1);	
		}
		
		
		
		
		/*String sel = " SELECT Distinct NVL(HRMS_DAILY_ATTENDANCE_2007.ATT_EMP_ID,'0') , SHIFT_NIGHT_FLAG ,NVL(HRMS_EMP_OFFC.EMP_TOKEN,'0000O '), "+
		  " NVL(EMP_FNAME ||'  '|| EMP_MNAME||' '||EMP_LNAME,' ANONYMOUS') ,nvl(EMP_SHIFT, ' ' ),ATT_NDA_HRS_APPROVED,ATT_NDA_HRS FROM HRMS_DAILY_ATTENDANCE_"+year+" "+
		  " INNER JOIN HRMS_SHIFT ON(TRIM(HRMS_DAILY_ATTENDANCE_"+year+".ATT_SHIFT_ID)=TRIM(HRMS_SHIFT.SHIFT_ID )) "+
		  " INNER JOIN HRMS_EMP_OFFC ON (TRIM(HRMS_EMP_OFFC.EMP_ID) "+
		      " =TRIM(HRMS_DAILY_ATTENDANCE_"+year+".ATT_EMP_ID))	WHERE HRMS_EMP_OFFC.EMP_paybill='"+pbId+"' "+
		      " AND   ATT_DATE=to_date('"+date+"','dd-mm-yyyy') AND HRMS_SHIFT.SHIFT_NIGHT_FLAG ='Y' ";
		
		Object[][] selec = getSqlModel().getSingleResult(sel, new Object[] {});
*/
		
	
		return result;
	}

	public void getReport(NdaAttend bean, String pbId, HttpServletResponse response) {
		String date = bean.getNdaDate();
		String loginDate=date;
		String reportType = "Xls";
		
		String year= date.substring(6, date.length());
		String month= date.substring(3, 5);

		String sel = " SELECT Distinct NVL(HRMS_DAILY_ATTENDANCE_"+year+".ATT_EMP_ID,0) , SHIFT_NIGHT_FLAG ,NVL(HRMS_EMP_OFFC.EMP_TOKEN,' '), "+
		  " NVL(EMP_FNAME,'') ||'  '|| NVL(EMP_MNAME,'')||' '||NVL(EMP_LNAME,'') ,NVL(ATT_SHIFT_ID, ' ' ),NVL(ATT_NDA_HRS_APPROVED,'0'),NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,'0') FROM HRMS_DAILY_ATTENDANCE_"+year+" "+
		  " INNER JOIN HRMS_SHIFT ON(TRIM(HRMS_DAILY_ATTENDANCE_"+year+".ATT_SHIFT_ID)=TRIM(HRMS_SHIFT.SHIFT_ID )) "+
		  " INNER JOIN HRMS_EMP_OFFC ON (TRIM(HRMS_EMP_OFFC.EMP_ID) "+
		  " =TRIM(HRMS_DAILY_ATTENDANCE_"+year+".ATT_EMP_ID))" +
		   "LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_DAILY_ATTENDANCE_"+year+".ATT_EMP_ID)	WHERE HRMS_EMP_OFFC.EMP_paybill='"+pbId+"' "+
		      " AND   ATT_DATE=to_date('"+date+"','dd-mm-yyyy') AND HRMS_SHIFT.SHIFT_NIGHT_FLAG ='Y' AND ATT_STATUS!='AA'  ";
	
		Object[][] selec = getSqlModel().getSingleResult(sel, new Object[] {});
		
		
		String[] MONTH = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL",
				"AUG", "SEP", "OCT", "NOV", "DEC" };
		
		int[] cellWidth = new int[6];
		String [] colNames= new String[6];
		colNames[0]="Sr.no";
		colNames[1]="Token.no";
		colNames[2]="Name";
		colNames[3]="Shift";
		colNames[4]="Hours Approved";
		colNames[5]="Account No.";
		
		
		cellWidth[0]=10;
		cellWidth[1]=15;
		cellWidth[2]=30;
		cellWidth[3]=25;
		cellWidth[4]=25;
		cellWidth[5]=25;
		String reportName = " Night Duty Attendance  Details  for " 
			+ MONTH[Integer.parseInt(String.valueOf(month))];
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				reportType,reportName );
		rg.genHeader(" ");
		Object [][] data1=new Object[selec.length][6];
		int j=1;
		
		for (int i = 0; i < selec.length; i++) {			
			
			data1[i][0]=String.valueOf(j);
			data1[i][1]=String.valueOf(selec[i][2]);
			data1[i][2]=String.valueOf(selec[i][3]);
			data1[i][3]=String.valueOf(selec[i][4]);
			data1[i][4]=String.valueOf(selec[i][5]);
			data1[i][5]=String.valueOf(selec[i][6]);
			
			j++;
			
			}
		rg.tableBody(colNames, data1, cellWidth);
		rg.createReport(response);
		
	}

	

}
