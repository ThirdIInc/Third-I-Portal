package org.paradyne.model.payroll;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.DaAcquintanceRoll;

 import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;



	public class DaAcquintanceRollModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
		
		public String  report(DaAcquintanceRoll daAcquintanceRoll ,HttpServletResponse response)
		{
			String reportType = "Xls";
			String availEffDate="false";
			try
			{
				
				Object[] selObj = new Object[3];
				selObj[0] = daAcquintanceRoll.getPayBillNo();
				selObj[1] = daAcquintanceRoll.getTypeCode();
				selObj[2] = daAcquintanceRoll.getDaDate();
				logger.info("vvvvvvvvvvvvv"+daAcquintanceRoll.getPayBillNo());
				logger.info("vvvvvvvvvvvvv"+daAcquintanceRoll.getTypeCode());
				logger.info("vvvvvvvvvvvvv"+daAcquintanceRoll.getDaDate());
				String daArrearSelect = "SELECT  HRMS_EMP_OFFC.EMP_TOKEN , HRMS_EMP_OFFC.EMP_FNAME ||' '|| HRMS_EMP_OFFC.EMP_MNAME ||' '|| HRMS_EMP_OFFC.EMP_LNAME AS EMP_NAME, "
					+ " DA_MONTH,DA_YEAR,DA_DRAWN,DA_DUE,DA_DIFF FROM HRMS_DA_ARREAR_HDR "
					+ " INNER JOIN HRMS_DA_ARREAR_DTL ON (HRMS_DA_ARREAR_DTL.DA_CODE=HRMS_DA_ARREAR_HDR.DA_CODE) "
					+ " LEFT JOIN HRMS_EMP_OFFC ON (HRMS_DA_ARREAR_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					+ " WHERE DA_PAYBILL_ID=? AND DA_EMP_TYPE=? AND TO_CHAR(DA_EFFECT_DATE,'DD-MM-YYYY')=? "
					+ " ORDER BY HRMS_DA_ARREAR_DTL.EMP_ID";

				Object daArrearData[][] = getSqlModel().getSingleResult(
					daArrearSelect, selObj);
				Object[][] daData=new Object[daArrearData.length][7];
				for (int i = 0; i < daArrearData.length; i++) {
					daData[i][0]=daArrearData[i][0];
					daData[i][1]=daArrearData[i][1];
					daData[i][2]=Utility.month(Integer.parseInt(String
							.valueOf(daArrearData[i][2])));
					daData[i][3]=daArrearData[i][3];
					daData[i][4]=daArrearData[i][4];
					daData[i][5]=daArrearData[i][5];
					daData[i][6]=daArrearData[i][6];
					
				}
				
				
			/*
			 * String dadate=daAcquintanceRoll.getDaDate(); Date date = new
			 * Date(); SimpleDateFormat formater = new
			 * SimpleDateFormat("dd-MM-yyyy"); String sysDate =
			 * formater.format(date); String
			 * month=String.valueOf((Integer.parseInt(sysDate.substring(2,
			 * 4))-1));
			 * 
			 * daAcquintanceRoll.setSysDate(sysDate); String daSelect="SELECT
			 * DA_CODE, TO_CHAR(DA_EFFECTIVE_DATE,'DD-MM-YYYY') , DA_RATE FROM
			 * HRMS_DA_PARAMETER WHERE
			 * DA_EFFECTIVE_DATE=TO_DATE('"+dadate+"','DD-MM-YYYY') ";
			 * 
			 * Object da[][]= getSqlModel().getSingleResult(daSelect);
			 * 
			 * daAcquintanceRoll.setDaCode(String.valueOf(da[0][0]));
			 * daAcquintanceRoll.setEffectiveDate(String.valueOf(da[0][1]));
			 * daAcquintanceRoll.setDaRate(String.valueOf(da[0][2]));
			 * availEffDate="true"; try { String daArrearSelect="SELECT
			 * HRMS_EMP_OFFC.EMP_TOKEN , HRMS_EMP_OFFC.EMP_FNAME ||' '||
			 * HRMS_EMP_OFFC.EMP_MNAME ||' '|| HRMS_EMP_OFFC.EMP_LNAME AS
			 * EMP_NAME ,DA_PAID ,DA_DUE ,to_char(DA_EFFECT_DATE,'dd-mm-yyyy')
			 * ,TO_CHAR(DA_PROCESS_DATE,'DD-MM-YYYY') FROM HRMS_DA_ARREAR join
			 * HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID=HRMS_DA_ARREAR.EMP_ID JOIN
			 * HRMS_SALARY_MISC ON
			 * HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID"; String where=" ";
			 * try { if(daAcquintanceRoll.getPayBillNo()!=null &&
			 * daAcquintanceRoll.getPayBillNo().length()>0)
			 * 
			 * where=" AND SAL_PAYBILL_NO="+daAcquintanceRoll.getPayBillNo();
			 * 
			 * //if(daArrear.getTypeCode()!=null &&
			 * daArrear.getTypeCode().length()>0)
			 *  // where=" AND HRMS_EMP_OFFC.emp_type="+daArrear.getTypeCode();
			 * 
			 * daArrearSelect=daArrearSelect+where;
			 */
			
			//Object daArrearData[][]= getSqlModel().getSingleResult(daArrearSelect);
			
			
			
			
			//String reportType=new String(daAcquintanceRoll.getRptType());	
			logger.info(daAcquintanceRoll.getRptType()+"-------------------");
			String reportName="DA Acquintance Roll";		
			
			// int companyCode = Integer.parseInt(String.valueOf(data[1]));
			// int code=Integer.parseInt(String.valueOf(data[2]));
			
			String[] colNames={"TOKEN NO ","EMPLOYEE NAME","DA MONTH","DA YEAR","PAID DA","DUE DA","DA DIFF"};

			int [] cellWidth={10,30,15,10,15,15,15};

	        int cellAlign[]={0,0,0,0,0,0,0};
			// 0--left align, 1--align center, 2--right align,3-- justified
			// align

			//int [] alignment={1,1,0,1,1,1};

			
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					 reportType, reportName);
			rg.genHeader("");

			String text1[] = { "DA Aquintance Report" };
			int style1[] = { 2 };
			 rg.addFormatedText(text1,style1,0,1,0);

			rg.xlsTableBody(colNames, daData, cellWidth, cellAlign);
			rg.createReport(response);
			String str="DAAquintanceReport";
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			 
			return null;
		}
		
		public String bankStatement(DaAcquintanceRoll daAcquintanceRoll ,HttpServletResponse response){
			
			String reportType = "Xls";
			String availEffDate="false";
			
			try {
				
				Object[] selObj = new Object[3];
				selObj[0] = daAcquintanceRoll.getPayBillNo();
				selObj[1] = daAcquintanceRoll.getTypeCode();
				selObj[2] = daAcquintanceRoll.getDaDate();
				String bankQuery="SELECT  HRMS_EMP_OFFC.EMP_TOKEN , HRMS_EMP_OFFC.EMP_FNAME ||' '|| HRMS_EMP_OFFC.EMP_MNAME ||' '|| HRMS_EMP_OFFC.EMP_LNAME AS EMP_NAME, " 
					+" NVL(HRMS_SALARY_MISC.SAL_ACCNO_REGULAR,' '), SUM(DA_DIFF)	 "
					+" FROM HRMS_DA_ARREAR_HDR   "
					+" INNER JOIN HRMS_DA_ARREAR_DTL ON (HRMS_DA_ARREAR_DTL.DA_CODE=HRMS_DA_ARREAR_HDR.DA_CODE) " 
					+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_DA_ARREAR_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					+" LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					
					+" WHERE DA_PAYBILL_ID=? AND "
					+" DA_EMP_TYPE=? AND TO_CHAR(DA_EFFECT_DATE,'DD-MM-YYYY')=? "
					+" GROUP BY HRMS_EMP_OFFC.EMP_TOKEN , HRMS_EMP_OFFC.EMP_FNAME ||' '|| HRMS_EMP_OFFC.EMP_MNAME ||' '|| HRMS_EMP_OFFC.EMP_LNAME ,"
					+" HRMS_SALARY_MISC.SAL_ACCNO_REGULAR";
				
				
				Object daArrearData[][] = getSqlModel().getSingleResult(
						bankQuery, selObj);
				logger.info(daAcquintanceRoll.getRptType()+"-------------------");
				String reportName="DA Acquintance Roll";		
				
				// int companyCode = Integer.parseInt(String.valueOf(data[1]));
				// int code=Integer.parseInt(String.valueOf(data[2]));
				
				String[] colNames={"TOKEN NO ","EMPLOYEE NAME","ACCOUNT NO","DA TO PAY"};

				int [] cellWidth={10,30,15,15};

		        int cellAlign[]={0,0,0,0,0};
				// 0--left align, 1--align center, 2--right align,3-- justified
				// align

				//int [] alignment={1,1,0,1,1,1};

				
				org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
						 reportType, reportName);
				rg.genHeader("");

				String text1[] = { "DA Bank Statement Report" };
				int style1[] = { 2 };
				 rg.addFormatedText(text1,style1,0,1,0);

				rg.xlsTableBody(colNames, daArrearData, cellWidth, cellAlign);
				rg.createReport(response);
				String str="DABankStatementReport";
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		 
	}
