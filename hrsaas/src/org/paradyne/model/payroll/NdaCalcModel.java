/**
 * 
 */
package org.paradyne.model.payroll;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.paradyne.bean.payroll.NdaCalculateBean;
 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

import java.util.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author MuzaffarS
 * 
 */
public class NdaCalcModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	boolean debug = true;

	NdaCalculateBean bean1;

	public int noOfSunday(int yrs, int mons, int ltday) {
		GregorianCalendar firstdate = getfirstDate(yrs, mons);
		GregorianCalendar seconddate = getlastDate(yrs, mons, ltday);
		int number = countNumberOfSundays(firstdate, seconddate);
		logger.info("the no of count of sundays in  may are" + number);
		return number;
	}

	public int countNumberOfSundays(GregorianCalendar first,
			GregorianCalendar second) {

		int count = 0;
		Calendar currentcalendarday = first;
		Calendar lastcalendarday = second;
		while (!currentcalendarday.equals(lastcalendarday)) {
			if (isOnSunday(currentcalendarday))
				count++;
			currentcalendarday.add(Calendar.DATE, 1);
		}
		return count;

	}

	public boolean isOnSunday(Calendar calendardate) {
		if (debug) {
			if (calendardate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				logger.info("Debug: "
						+ String.valueOf(calendardate.get(Calendar.DATE)) + "-"
						+ String.valueOf(calendardate.get(Calendar.MONTH))
						+ "-" + String.valueOf(calendardate.get(Calendar.YEAR))
						+ " is a SUNDAY.");
			} else {
				logger.info("Debug: "
						+ String.valueOf(calendardate.get(Calendar.DATE)) + "-"
						+ String.valueOf(calendardate.get(Calendar.MONTH))
						+ "-" + String.valueOf(calendardate.get(Calendar.YEAR))
						+ " is not a SUNDAY.");
			}
		}
		return (calendardate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);

	}

	// set date instance for sundays calculation
	public GregorianCalendar getfirstDate(int year, int month) {
		return new GregorianCalendar(year, month, 1);
	}

	public GregorianCalendar getlastDate(int year, int month, int lastday) {
		return new GregorianCalendar(year, month, lastday);
	}

	public void getList(NdaCalculateBean bean) {

		String yr = bean.getYear();

		Date d = new Date();

		String sqlSelect = " SELECT  ATT_CODE,ATT_EMP_ID,ATT_NDA_HRS FROM HRMS_DAILY_ATTENDANCE_"
				+ yr + " ";
		Object[][] data = getSqlModel().getSingleResult(sqlSelect,
				new Object[][] {});

		ArrayList<NdaCalculateBean> atlist = new ArrayList<NdaCalculateBean>();
		logger.info("into model ---------");

		for (int i = 0; i < data.length; i++) {

			NdaCalculateBean bean1 = new NdaCalculateBean();
			bean1.setEmpToken(String.valueOf(data[i][0]));
			
			bean1.setEmpName(String.valueOf(data[i][1]));
			
			bean1.setNdaHrs(String.valueOf(data[i][2]));
			

			atlist.add(bean1);
		}
		bean.setAtt(atlist);

	}

	public void getview(NdaCalculateBean bean, String pbId) {
		try {

			String yr = bean.getYear();
			String tid = bean.getSrtEmp();
			String mon = bean.getMonth();

			ArrayList<NdaCalculateBean> atlist = new ArrayList<NdaCalculateBean>();

			String stat = " SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
					+ " NVL(SUM(HRMS_DAILY_ATTENDANCE_"
					+ yr
					+ ".ATT_NDA_HRS_APPROVED),0),HRMS_EMP_OFFC.EMP_ID "
					+ " from HRMS_DAILY_ATTENDANCE_"
					+ yr
					+ " INNER JOIN HRMS_EMP_OFFC "
					+ " ON(HRMS_EMP_OFFC.EMP_ID=HRMS_DAILY_ATTENDANCE_"
					+ yr
					+ ".ATT_EMP_ID) "
					+ " LEFT JOIN HRMS_SHIFT ON(TRIM(HRMS_SHIFT.SHIFT_ID) = "
					+ " TRIM(HRMS_DAILY_ATTENDANCE_"
					+ yr
					+ ".ATT_SHIFT_ID)) "
					+ " LEFT JOIN HRMS_SALARY_MISC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID) "
					+ " INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_OFFC.EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID)"
					+ " WHERE EMP_PAYBILL='"
					+ pbId
					+ "' AND HRMS_SHIFT.SHIFT_NIGHT_FLAG='Y'AND HRMS_EMP_TYPE.TYPE_ID="
					+ tid
					+ " AND "
					+ "TO_CHAR(HRMS_DAILY_ATTENDANCE_"
					+ yr
					+ ".ATT_DATE,'MM')="
					+ mon
					+ " GROUP BY EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME  ,HRMS_EMP_OFFC.EMP_ID";

			Object[][] data = getSqlModel().getSingleResult(stat,
					new Object[] {});

			//logger.info("the emp id is  " + data[0][3]);
			
			String holiday=" SELECT HOLI_DESC FROM HRMS_HOLIDAY WHERE TO_CHAR(HOLI_DATE,'MM-YYYY')='"+mon+"-"+yr+"'";
			Object[][] countholiday = getSqlModel().getSingleResult(holiday,
					new Object[] {});
			Utility util=new Utility();
			int listofholiday=util.removeSunFromHolidayList(countholiday);
			
			// taking formula depending on  employee type
			
			String formula=" SELECT  NDA_FORMULA FROM HRMS_NDA_PARAMETER WHERE EMP_TYPE="+tid;
			Object[][] formulaValu = getSqlModel().getSingleResult(formula,
					new Object[] {});
			logger.info(formula);
			logger.info(String.valueOf(formulaValu[0][0]));
			logger.info(holiday);
			
			for (int i = 0; i < data.length; i++) {
				NdaCalculateBean bean1 = new NdaCalculateBean();
				bean1.setEmpToken(String.valueOf(data[i][0]));
				bean1.setEmpName(String.valueOf(data[i][1]));
				bean1.setEmpid(String.valueOf(data[i][3]));
				// the no of sundays are calculated as
				int year = Integer.parseInt(yr);
				int month = Integer.parseInt(mon);
				Calendar cal =new Utility().getCalanderDate("01-"+month+"-"+year);
				 cal = Calendar.getInstance();
				//cal.set(year, month, 1);
				
				int lastDay = cal.getActualMaximum(cal.DAY_OF_MONTH);
				logger.info("the last day of the month is" + lastDay);
				if (String.valueOf(data[i][2]) == null) {
					data[i][2] = String.valueOf("0");
				}
				double ndaHrs = (Float.parseFloat(String.valueOf(data[i][2])) * 10) / 60;

				String basic = "select NVL(CREDIT_AMT_PRECOMMISSION,0.0) from HRMS_EMP_credit where EMP_ID="
						+ data[i][3] + " AND CREDIT_CODE IN(1,2,3,5)";
				Object[][] bsamt = getSqlModel().getSingleResult(basic,
						new Object[] {});

				int num = noOfSunday(year, month, lastDay);
				logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@LAST DAY IN MONTH" + lastDay+"NUM OF SUNDAY#################"+num+"LIST OF HOLIDAY"+listofholiday);
				// working hours to be calculated as
				int whrs = (lastDay - num-listofholiday)*8;
				Object[][] Preamt = new Object[4][1];
				if (bsamt.length == 0) {
					Preamt[0][0] = 0;
					Preamt[1][0] = 0;
					Preamt[2][0] = 0;
					Preamt[3][0] = 0;
				} else {
					Preamt[0][0] = bsamt[0][0];
					Preamt[1][0] = bsamt[1][0];
					Preamt[2][0] = bsamt[2][0];
					Preamt[3][0] = bsamt[3][0];
				}
				
				/*double calcNda = ((Float
						.parseFloat(String.valueOf(Preamt[0][0]))
						+ Float.parseFloat(String.valueOf(Preamt[1][0]))
						+ Float.parseFloat(String.valueOf(Preamt[2][0])) + Float
						.parseFloat(String.valueOf(Preamt[3][0])))/ (whrs) )* (ndaHrs)
						;*/
			//	double calcNda =0;
				double calculateNda=0;
				
				try {
					double formulaValue=Utility.expressionEvaluate(new Utility().generateFormula(String.valueOf(data[i][3]), String.valueOf(formulaValu[0][0]), context, "NDA"));	
					logger.info("formaula value -----------------------"+formulaValue);
					logger.info("working hours -----------------------"+whrs);
					logger.info("NDA hours -----------------------"+ndaHrs);
					calculateNda=(formulaValue /whrs)*ndaHrs ;
					logger.info("NDA value ----------------------------------------------------"+calculateNda);
				} catch (Exception e) {
					e.printStackTrace();
				}
					
				bean1.setNdaHrs(String.valueOf(data[i][2]));
				bean1.setNdaAmount(String.valueOf(calculateNda));

				atlist.add(bean1);

			}

			bean.setAtt(atlist);
			bean.setFlag(String.valueOf("true"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Boolean save(NdaCalculateBean bean, String[] empid, String[] amount, String[] hrs) {

		String yr = bean.getYear();
		String mon = bean.getMonth();
		
		for (int i = 0; i < empid.length; i++) {
			String stat = "  SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
				+ " NVL((NDA_AMOUNT),0),NDA_HRS,HRMS_NDA.EMP_ID "
				+ " from HRMS_NDA  "
				+ " INNER JOIN HRMS_EMP_OFFC "
				+ " ON(HRMS_EMP_OFFC.EMP_ID=HRMS_NDA.EMP_ID) "
				+ " INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_OFFC.EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID) "
				+ " WHERE HRMS_NDA.NDA_YEAR="
				+ yr
				+ " AND HRMS_NDA.NDA_MONTH="
				+ mon
				+ " AND  HRMS_NDA.EMP_ID="+empid[i] +
						"GROUP BY "
				+ " EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
				+ " ,HRMS_NDA.EMP_ID,NDA_AMOUNT,NDA_HRS ";
		
		Object[][] data = getSqlModel().getSingleResult(stat,
				new Object[] {});
		if(data.length==0)
		{
			
			String insert=" INSERT INTO HRMS_NDA(EMP_ID,NDA_AMOUNT,NDA_YEAR,NDA_MONTH,NDA_HRS )" +
					" VALUES("+empid[i]+","+amount[i]+","+bean.getYear()+","+bean.getMonth()+","+hrs[i]+")";
			
			debug=getSqlModel().singleExecute(insert);
			logger.info(insert);
		}
		else
		{
			
			String update=" UPDATE  HRMS_NDA SET NDA_AMOUNT="+amount[i]+",NDA_HRS="+hrs[i]+" WHERE  NDA_YEAR="+bean.getYear()+" AND NDA_MONTH="+bean.getMonth()+" AND EMP_ID="+empid[i];
			debug=getSqlModel().singleExecute(update);
			logger.info(update);
		}
			

		}
		return debug;

	}

	public Boolean getrecord(NdaCalculateBean bean, String pbId) {
		String yr = bean.getYear();
		String mon = bean.getMonth();
		
		String tid = bean.getSrtEmp();
	
		
		String stat = "  SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "+  
				 " NVL((NDA_AMOUNT),0),nvl(NDA_HRS,0),HRMS_NDA.EMP_ID  from HRMS_NDA   INNER JOIN HRMS_EMP_OFFC  "+
				 " ON(HRMS_EMP_OFFC.EMP_ID=HRMS_NDA.EMP_ID)  INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_OFFC.EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID) "+
				 " WHERE HRMS_NDA.NDA_YEAR="+yr+" and EMP_PAYBILL='"+pbId+"' AND "+
				 " HRMS_EMP_TYPE.TYPE_ID="+tid+" AND HRMS_NDA.NDA_MONTH="+mon+" GROUP BY  EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' ' "+
				 " ||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME  ,HRMS_NDA.EMP_ID,NDA_AMOUNT,NDA_HRS ";
		
		Object[][] data = getSqlModel().getSingleResult(stat,
				new Object[] {});
		
		ArrayList<NdaCalculateBean> atlist = new ArrayList<NdaCalculateBean>();
		
		for(int i=0;i<data.length;i++)
		{
			NdaCalculateBean bean1 = new NdaCalculateBean();
			bean1.setEmpToken(String.valueOf(data[i][0]));
			bean1.setEmpName(String.valueOf(data[i][1]));
			bean1.setEmpid(String.valueOf(data[i][4]));
			bean1.setNdaAmount(String.valueOf(data[i][2]));
			bean1.setNdaHrs(String.valueOf(data[i][3]));
			
			atlist.add(bean1);
		}
		bean.setAtt(atlist);
		bean.setFlag(String.valueOf("true"));
		if(data.length==0)
		{
			return false;
		}
		return true;
		
	}

	public void getreport(NdaCalculateBean bean, String pbId, HttpServletResponse response) {
		String yr = bean.getYear();
		String mon = bean.getMonth();
		String tid = bean.getSrtEmp();
		String reportType = "Xls";
		String stat = "  SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME , "
				+ " NVL((NDA_AMOUNT),0),nvl(NDA_HRS,0),HRMS_NDA.EMP_ID "
				+ " from HRMS_NDA  "
				+ " INNER JOIN HRMS_EMP_OFFC "
				+ " ON(HRMS_EMP_OFFC.EMP_ID=HRMS_NDA.EMP_ID) "
				+ " INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_OFFC.EMP_TYPE=HRMS_EMP_TYPE.TYPE_ID) "
				+ " WHERE HRMS_NDA.NDA_YEAR="
				+ yr
				+ " AND HRMS_NDA.NDA_MONTH="
				+ mon+" AND EMP_PAYBILL='"+pbId+ " '  "+
				 " AND HRMS_EMP_TYPE.TYPE_ID="+tid
				+ " GROUP BY "
				+ " EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
				+ " ,HRMS_NDA.EMP_ID,NDA_AMOUNT,NDA_HRS ";
		
		Object[][] data = getSqlModel().getSingleResult(stat,
				new Object[] {});
		
		String[] MONTH = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL",
				"AUG", "SEP", "OCT", "NOV", "DEC" };
		
		int[] cellWidth = new int[5];
		int[] alig = new int[]{0,0,0,0,0};
		String [] colNames= new String[5];
		colNames[0]="Sr.no";
		colNames[1]="Token.no";
		colNames[2]="Name";
		colNames[3]="Nda hrs";
		colNames[4]="Amount";
		cellWidth[0]=10;
		cellWidth[1]=15;
		cellWidth[2]=30;
		cellWidth[3]=25;
		cellWidth[4]=25;
		String reportName = " Night Duty Calculation  Details  for " + MONTH[Integer.parseInt(String.valueOf(mon))];
		
		org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
				reportType,reportName );
		rg.genHeader(" ");
		Object [][] data1=new Object[data.length][5];
		if(data.length !=0)
		{

int j=1;
		for(int i=0;i<data.length;i++)
		{
			data1[i][0]=String.valueOf(j);//sr.no
			data1[i][1]=String.valueOf(data[i][0]);//token
			data1[i][2]=String.valueOf(data[i][1]);//name
			data1[i][3]=String.valueOf(data[i][3]);//hrs
			data1[i][4]=String.valueOf(data[i][2]);//amount
			logger.info(data1[i][2]+"*****************************"+data1[i][3]);
			j++;
			
		}
		}
		rg.xlsTableBody(colNames, data1, cellWidth,alig);
		rg.createReport(response);
		
		
		
		
	}

}
