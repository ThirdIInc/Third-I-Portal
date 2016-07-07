package org.paradyne.model.Promotion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.promotion.PromotionMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.Utility;
	
public class PromotionMISReportModel extends ModelBase{
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.paradyne.lib.ModelBase.class);
	
	// METHOD FOR GENERATING MIS REPORT FROM PROMOTION HEADER N PROMOTION SALARY TABLE
	public boolean misReport(PromotionMisReport promotion, HttpServletResponse response) {

		try {
			String reportName = "PROMOTION MIS REPORT";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(promotion.getReportType(),	reportName, "A4");
		
			rg.addFormatedText(reportName, 6, 0, 1, 100);
			
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String toDay = sdf.format(today);
			rg.addText("Date:  "+toDay, 0, 2, 0);
			
			if(!(promotion.getEmpCode().equals(""))
					&& !(promotion.getEmpCode() == null)
					&& !promotion.getEmpCode().equals("null"))
		 	{
				
		 		rg.addText("Employee Name:  "+promotion.getEmpName(), 0, 0, 0);
		 	}
			if(!(promotion.getFrmDate().equals(""))
					&& !(promotion.getFrmDate() == null)
					&& !promotion.getFrmDate().equals("null"))
		  {
			    rg.addText("From Date:  "+promotion.getFrmDate() , 0, 0, 0);
		  }
			if(!(promotion.getToDate().equals(""))
					&& !(promotion.getToDate() == null)
					&& !promotion.getToDate().equals("null"))
		  {
			    rg.addText("To Date:  "+promotion.getToDate(), 0, 0, 0);
		  }
			  if(!(promotion.getPrBranCode().equals(""))
						&& !(promotion.getPrBranCode() == null)
						&& !promotion.getPrBranCode().equals("null"))
			  {
				
				  rg.addText("Branch:  "+promotion.getProBranch(), 0, 0, 0);
			  }
			  if(!(promotion.getPrDeptCode().equals(""))
						&& !(promotion.getPrDeptCode() == null)
						&& !promotion.getPrDeptCode().equals("null"))
			  {
				 
				  rg.addText("Department:  "+promotion.getProDept(), 0, 0, 0);
				  
			  }
			  if(!(promotion.getPrDesgCode().equals(""))
						&& !(promotion.getPrDesgCode() == null)
						&& !promotion.getPrDesgCode().equals("null"))
			  {
					  rg.addText("Designation :  "+promotion.getProDesg(), 0, 0, 0);
				  
			  }
			  if(!(promotion.getPrDivCode().equals(""))
						&& !(promotion.getPrDivCode() == null)
						&& !promotion.getPrDivCode().equals("null"))
			  {
				 
				  rg.addText("Division:  "+promotion.getProDiv(), 0, 0, 0);
			  }
			  if (!(promotion.getPrGrdCode().equals(""))
						&& !(promotion.getPrGrdCode() == null)
						&& !promotion.getPrGrdCode().equals("null")) {
					
					 rg.addText("Grade:  "+promotion.getProGrade(), 0, 0, 0);
				}
		
			String query = "SELECT ROW_NUMBER() OVER (ORDER BY PCODE),EMTOKEN,ENAME,DOJ,PDATE,EFFDATE,PROCENTER,PRODEPT,PRORANK,PROCADRE,PRODIV,PROREP,STRENGTH,WEAKNESS,RATING,OLD_CTC,'0',GR0SSAMT,'0', "
				+" PERC_HIKE,PROLE,FORMGRADE,PROMFLAG,ECODE,P_CODE "
				+" from( ";
			
			query += " SELECT  DISTINCT PROM_CODE PCODE ,E0.EMP_TOKEN EMTOKEN ,E0.EMP_FNAME||' '||E0.EMP_MNAME||' '||E0.EMP_LNAME ENAME, TO_CHAR(DATE_JOINING,'DD-MM-YYYY') DOJ ,TO_CHAR(PROM_DATE,'DD-MM-YYYY') PDATE,TO_CHAR(EFFECT_DATE,'DD-MM-YYYY') EFFDATE,NVL(C2.CENTER_NAME,' ') PROCENTER,NVL(DP2.DEPT_NAME,' ') PRODEPT, NVL(R2.RANK_NAME,' ') PRORANK, NVL(CD2.CADRE_NAME,' ') PROCADRE,DV2.DIV_NAME PRODIV , E1.EMP_FNAME||' '||E1.EMP_MNAME||' '||E1.EMP_LNAME PROREP ,  NVL(STRENGTH,' ') STRENGTH ,NVL(WEAKNESS,' ') WEAKNESS ,NVL(RATING,' ') RATING, OLD_CTC,'0',NVL(PROM_GR0SSAMT,'0') GR0SSAMT ,'0',"
				+" (NVL(PROM_GR0SSAMT,'0')-NVL(OLD_CTC,0))*100/(CASE WHEN OLD_CTC>0 THEN OLD_CTC ELSE 1 END) "
				+" AS PERC_HIKE,PRO_ROLE PROLE , DECODE(PROCESS_FLAG,'F',NVL(FORMULA_NAME,' '),'G',NVL(SALGRADE_TYPE,' '),' ') FORMGRADE,"
				+" DECODE(PROM_PROMFLAG,'P','YES','NO') PROMFLAG,EMP_CODE ECODE , HRMS_PROMOTION.PROM_CODE P_CODE  "
				+" FROM HRMS_PROMOTION LEFT JOIN HRMS_EMP_OFFC E0 ON(E0.EMP_ID = HRMS_PROMOTION.EMP_CODE)" 
				+" LEFT JOIN HRMS_EMP_OFFC rep ON(rep.EMP_REPORTING_OFFICER = HRMS_PROMOTION.REPORTING_TO)" 
				+" LEFT JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID= HRMS_PROMOTION.PRO_REPORT_TO) "
				+" LEFT JOIN HRMS_CENTER C2 ON(C2.CENTER_ID=HRMS_PROMOTION.PRO_BRANCH) "
				+" LEFT JOIN HRMS_RANK R2 ON(R2.RANK_ID=HRMS_PROMOTION.PRO_DESG) "
				+" LEFT JOIN HRMS_CADRE CD2 ON(CD2.CADRE_ID=HRMS_PROMOTION.PRO_GRADE)" 
				+" LEFT JOIN HRMS_DEPT DP2 ON(DP2.DEPT_ID=HRMS_PROMOTION.PRO_DEPT) "
				+" LEFT JOIN HRMS_DIVISION DV2 ON(DV2.DIV_ID = HRMS_PROMOTION.PRO_DIV)" 
				+" LEFT JOIN HRMS_FORMULABUILDER_HDR  ON(PROM_FORMULA = FORMULA_ID) "
				+" LEFT JOIN HRMS_SALGRADE_HDR ON(PROM_SALGRADE= SALGRADE_CODE) WHERE 1=1 ";
			
			query += queryFilter(promotion);
			
			query +=")";

			Object[][] data = getSqlModel().getSingleResult(query);

				 Object[][] filter = new Object[5][2];
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
			
			  if(!(promotion.getFrmDate().equals(""))
						&& !(promotion.getFrmDate() == null)
						&& !promotion.getFrmDate().equals("null"))
			  {
				  logger.info("frommmmmmmmmmmmmmmm======"+promotion.getFrmDate());
				  filter [0][0]= "From Date: "+promotion.getFrmDate(); 
				  filter[0][1] = "To Date: "+promotion.getToDate();
			  }
			  if(!(promotion.getPrBranCode().equals(""))
						&& !(promotion.getPrBranCode() == null)
						&& !promotion.getPrBranCode().equals("null"))
			  {
				  filter [1][0]= "Branch: "+promotion.getProBranch();
				  
			  }
			  if(!(promotion.getPrDeptCode().equals(""))
						&& !(promotion.getPrDeptCode() == null)
						&& !promotion.getPrDeptCode().equals("null"))
			  {
				  filter [1][1]= "Department : "+promotion.getProDept();
				  
			  }
			  
			  if(!(promotion.getPrDesgCode().equals(""))
						&& !(promotion.getPrDesgCode() == null)
						&& !promotion.getPrDesgCode().equals("null"))
			  {
				  filter [2][0]= "Designation : "+promotion.getProDesg();
				  
			  }
			  if(!(promotion.getPrDivCode().equals(""))
						&& !(promotion.getPrDivCode() == null)
						&& !promotion.getPrDivCode().equals("null"))
			  {
				  filter [2][1]= " Division : "+promotion.getProDiv(); 
			  }
				if (!(promotion.getPrGrdCode().equals(""))
						&& !(promotion.getPrGrdCode() == null)
						&& !promotion.getPrGrdCode().equals("null")) {
					 filter [3][0]= " Grade : "+promotion.getProGrade(); 
				}
		
				if(!(promotion.getEmpCode().equals(""))
						&& !(promotion.getEmpCode() == null)
						&& !promotion.getEmpCode().equals("null"))
			 	{
					 filter [4][0] = "Employee Token: "+promotion.getEmpToken() +" Employee Name:  "+promotion.getEmpName();
			 	}
			   
				int [] bcellWidth={45,45};
				int [] bcellAlign={0,0};
			
				String monthGrossQuery="SELECT ROUND(SUM( CASE WHEN CREDIT_PERIODICITY='M' THEN OLD_AMT ELSE 0 END )),ROUND(SUM(CASE  WHEN CREDIT_PERIODICITY='M' THEN NEW_AMT ELSE 0 END)), EMP_CODE||'#'||HRMS_PROMOTION.PROM_CODE ,"
					+" ROUND(SUM(CASE WHEN CREDIT_PERIODICITY='M' THEN (NEW_AMT * 12) WHEN CREDIT_PERIODICITY='A'  "
					+" THEN NEW_AMT WHEN CREDIT_PERIODICITY='Q' THEN NEW_AMT*4 WHEN CREDIT_PERIODICITY='H' THEN NEW_AMT*2 END)) AS NEWCTC "
					+" FROM HRMS_PROMOTION_SALARY"
					+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_PROMOTION_SALARY.SAL_CODE=HRMS_CREDIT_HEAD.CREDIT_CODE) "
					+" INNER JOIN HRMS_PROMOTION ON(HRMS_PROMOTION.PROM_CODE=HRMS_PROMOTION_SALARY.PROM_CODE)"
					+" WHERE CREDIT_PAYFLAG='Y'  AND CREDIT_IS_CTC_COMPONENT='Y' ";
					monthGrossQuery+= queryFilter(promotion);
					monthGrossQuery+=" GROUP BY EMP_CODE,HRMS_PROMOTION.PROM_CODE";
				
				
					
			Object [][] empMonthGrossObj=getSqlModel().getSingleResult(monthGrossQuery);
			
			HashMap<String, String> currentMonthGrossMap=new HashMap<String, String>();
			HashMap<String, String> proposedMonthGrossMap=new HashMap<String, String>();
			HashMap<String, String> proposedCTCMap=new HashMap<String, String>();
			
			if(empMonthGrossObj !=null && empMonthGrossObj.length>0){
				for (int i = 0; i < empMonthGrossObj.length; i++) {
					currentMonthGrossMap.put(String.valueOf(empMonthGrossObj[i][2]), String.valueOf(empMonthGrossObj[i][0]));
					proposedMonthGrossMap.put(String.valueOf(empMonthGrossObj[i][2]), String.valueOf(empMonthGrossObj[i][1]));
					proposedCTCMap.put(String.valueOf(empMonthGrossObj[i][2]), String.valueOf(empMonthGrossObj[i][3]));
				}
			}
			
			String creditQuery = " SELECT DISTINCT SAL_CODE, NVL(CREDIT_NAME,' ') AS CREDITS FROM HRMS_PROMOTION_SALARY "
					+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PROMOTION_SALARY.SAL_CODE)"
					+ " INNER JOIN HRMS_PROMOTION ON (HRMS_PROMOTION.PROM_CODE = HRMS_PROMOTION_SALARY.PROM_CODE) "
					+ " LEFT JOIN HRMS_CENTER C2 ON(C2.CENTER_ID=HRMS_PROMOTION.PRO_BRANCH) "
					+ " LEFT JOIN HRMS_RANK R2 ON(R2.RANK_ID=HRMS_PROMOTION.PRO_DESG)" 
					+ " LEFT JOIN HRMS_CADRE CD2 ON(CD2.CADRE_ID=HRMS_PROMOTION.PRO_GRADE)" 
					+ " LEFT JOIN HRMS_DEPT DP2 ON(DP2.DEPT_ID=HRMS_PROMOTION.PRO_DEPT)" 
					+ " LEFT JOIN HRMS_DIVISION DV2 ON(DV2.DIV_ID = HRMS_PROMOTION.PRO_DIV) "		
					+ " WHERE 1=1 ";
			
			creditQuery += queryFilter(promotion);
			
			Object[][] creditDataObj = getSqlModel().getSingleResult(creditQuery);
			
			/*CREDIT AMOUNTS TO FETCHED IN THE QUERY BELOW*/
			
			HashMap creditAmtMap=new HashMap();
			
			String creditAmtQuery = " SELECT PROM_CODE ||'#'||SAL_CODE, NVL(NEW_AMT,0) FROM HRMS_PROMOTION_SALARY "
				+ " INNER JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PROMOTION_SALARY.SAL_CODE)"
				+ " INNER JOIN HRMS_PROMOTION ON (HRMS_PROMOTION.PROM_CODE = HRMS_PROMOTION_SALARY.PROM_CODE) "
				+ " LEFT JOIN HRMS_CENTER C2 ON(C2.CENTER_ID=HRMS_PROMOTION.PRO_BRANCH) "
				+ " LEFT JOIN HRMS_RANK R2 ON(R2.RANK_ID=HRMS_PROMOTION.PRO_DESG)" 
				+ " LEFT JOIN HRMS_CADRE CD2 ON(CD2.CADRE_ID=HRMS_PROMOTION.PRO_GRADE)" 
				+ " LEFT JOIN HRMS_DEPT DP2 ON(DP2.DEPT_ID=HRMS_PROMOTION.PRO_DEPT)" 
				+ " LEFT JOIN HRMS_DIVISION DV2 ON(DV2.DIV_ID = HRMS_PROMOTION.PRO_DIV) "		
				+ " WHERE 1=1 ";
		
			creditAmtQuery += queryFilter(promotion);
			
			creditAmtMap = getSqlModel().getSingleResultMap(creditAmtQuery, 0, 2);
			
			logger.info("########### creditDataObj.length() ###########"+creditDataObj.length);
			int credits = 0;
			
			if(creditDataObj !=null && creditDataObj.length >0){
				credits = creditDataObj.length;
			}
			
			String[] colNames = new String[23+credits]; 
			
			colNames[0] = "Sr. No.";
			colNames[1] = "Employee ID"; 
			colNames[2] = "Employee Name";
			colNames[3] = "Date Of Joining"; 
			colNames[4] = "Promotion Date";
			colNames[5] = "Effective Date"; 
			colNames[6] = "Proposed Branch"; 
			colNames[7] = "Proposed Department"; 
			colNames[8] = "Proposed Designation"; 
			colNames[9] = "Proposed Grade";
			colNames[10] = "Proposed Division"; 
			colNames[11] = "Reporting To";
			colNames[12] = "Strength";
			colNames[13] = "Weakness"; 
			colNames[14] = "Rating";
			colNames[15] = "Current CTC";
			colNames[16] = "Current Monthly Gross";
			colNames[17] = "Proposed CTC";
			colNames[18] = "Proposed Monthly Gross";
			colNames[19] = "% Increment in CTC";
			colNames[20] = "Proposed Role";
			colNames[21] = "Proposed Salary Grade/Formula";
			colNames[22] = "Is Promoted";
			
			if(creditDataObj !=null && creditDataObj.length >0){
				for (int i = 0; i < creditDataObj.length; i++) {
					colNames[23+i] = String.valueOf(creditDataObj[i][1]);
				}
			}
			
			int[] cellWidth = new int[23+credits];  
			
			int[] cellAln = new int[23+credits]; 
			
			/* SETTING WIDTH & ALIGNMENT*/
			
			for (int j = 0; j < cellAln.length; j++) {
				cellWidth[j] = 10;
				cellAln[j] = 0; 
			}
			
			Object[][] finalDataObj = new Object[data.length][23+credits];
			
			
			if (data != null && data.length != 0) {
				for (int i = 0; i < data.length; i++) {
					for (int j = 0; j < colNames.length; j++) {
						if(j< 23){
							/* Adding values to finalDataObj upto 23 columns form data Object*/
							finalDataObj[i][j] = String.valueOf(data[i][j]);
							
							if(j>15 && j< 20){
								try {
									finalDataObj[i][16] = currentMonthGrossMap.get(String
											.valueOf(data[i][23])
											+ "#" + String.valueOf(data[i][24]));
								} catch (Exception e) {
									finalDataObj[i][16] ="0";
								}
								
								try {
									finalDataObj[i][17] = proposedCTCMap.get(String
											.valueOf(data[i][23])
											+ "#" + String.valueOf(data[i][24]));
								} catch (Exception e) {
									finalDataObj[i][17] ="0";
								}
								try {
									finalDataObj[i][18] = proposedMonthGrossMap.get(String
											.valueOf(data[i][23])
											+ "#" + String.valueOf(data[i][24]));
								} catch (Exception e) {
									finalDataObj[i][18] ="0";
								}
								
								finalDataObj[i][19]=Utility.twoDecimals(String.valueOf(data[i][19]));
							}
						}else{
							try {
								/*Adding credits to finalDataObj*/
								Object[][] amountObj = (Object[][])creditAmtMap.get(String.valueOf(data[i][24])
										+ "#"+ String.valueOf(creditDataObj[j-23][0]));
								
								if(amountObj!=null && amountObj.length >0){
									finalDataObj[i][j] = String.valueOf(amountObj[0][1]);
								}else{
									finalDataObj[i][j]="0";
								}
							} catch (Exception e) {
								finalDataObj[i][j]="0";
								e.printStackTrace();
							}
						}
					}//end of for loop
				}
				rg.addText("\n", 0, 0, 0);
				
				rg.tableBody(colNames, finalDataObj, cellWidth, cellAln);

			}
			else{
				rg.addText("\n", 0, 0, 0);
				rg.addText("\n", 0, 0, 0);
				rg.addText("\n", 0, 0, 0);
				rg.addFormatedText("No Record Found For Selected Criteria", 6, 0, 1, 100);
			}

			rg.createReport(response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public String queryFilter(PromotionMisReport promotion){
		
		String query = "";
		
		if (!(promotion.getFrmDate().equals("")) && promotion.getToDate().equals("") ) {
			
			query += " AND HRMS_PROMOTION.EFFECT_DATE>=TO_DATE('"+promotion.getFrmDate() + "','DD-MM-YYYY')";
			
		}
		if (!(promotion.getToDate().equals("")) && promotion.getFrmDate().equals("")) {
			
			query += " AND HRMS_PROMOTION.EFFECT_DATE<=TO_DATE('"+promotion.getToDate() + "','DD-MM-YYYY')";
			
		}
		if (!(promotion.getFrmDate().equals("")) && !(promotion.getToDate().equals(""))) {
			
			query += " AND HRMS_PROMOTION.EFFECT_DATE BETWEEN TO_DATE('"+promotion.getFrmDate()+"','DD-MM-YYYY')  AND TO_DATE('"+promotion.getToDate()+"','DD-MM-YYYY')";

			
		}
		if (!(promotion.getEmpCode().equals(""))
				&& !(promotion.getEmpCode() == null)
				&& !promotion.getEmpCode().equals("null")) {

			query += " AND HRMS_PROMOTION.EMP_CODE ="
					+ promotion.getEmpCode();
		}

		if (!(promotion.getPrBranCode().equals(""))
				&& !(promotion.getPrBranCode() == null)
				&& !promotion.getPrBranCode().equals("null")) {

			query += " AND HRMS_PROMOTION.PRO_BRANCH ="
					+ promotion.getPrBranCode();
		}

		if (!(promotion.getPrDeptCode().equals(""))
				&& !(promotion.getPrDeptCode() == null)
				&& !promotion.getPrDeptCode().equals("null")) {

			query += " AND HRMS_PROMOTION.PRO_DEPT ="
					+ promotion.getPrDeptCode();
		}

		if (!(promotion.getPrDesgCode().equals(""))
				&& !(promotion.getPrDesgCode() == null)
				&& !promotion.getPrDesgCode().equals("null")) {

			query += " AND HRMS_PROMOTION.PRO_DESG ="
					+ promotion.getPrDesgCode();
		}

		if (!(promotion.getPrDivCode().equals(""))
				&& !(promotion.getPrDivCode() == null)
				&& !promotion.getPrDivCode().equals("null")) {

			query += " AND HRMS_PROMOTION.PRO_DIV ="
					+ promotion.getPrDivCode();
		}

		if (!(promotion.getPrGrdCode().equals(""))
				&& !(promotion.getPrGrdCode() == null)
				&& !promotion.getPrGrdCode().equals("null")) {

			query += " AND HRMS_PROMOTION.PRO_GRADE ="
					+ promotion.getPrGrdCode();
		}
		return query;
	}
}
	