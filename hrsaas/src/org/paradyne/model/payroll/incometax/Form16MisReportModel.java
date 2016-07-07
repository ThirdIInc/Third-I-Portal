/**
 * 
 */
package org.paradyne.model.payroll.incometax;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.incometax.Form16MisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.model.payroll.salary.Form12BAModel;


/**
 * @author aa0517
 *
 */
public class Form16MisReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(Form16MisReportModel.class);
	NumberFormat formatter = new DecimalFormat("#0");
	Object[][] taxParameter=null;
	Object[][] invDeducData=null;
	Object[][] cccSection=null;
	
	/**
	 * This method will generate the links to generate the report in bulk 
	 * @param request
	 * @param response
	 * @param bulkForm16
	 * @return
	 */
	public String generateUrlList(HttpServletRequest request,
			HttpServletResponse response, Form16MisReport bulkForm16) {
		String empId = "";
		String empDetailsQuery=getQuery(1,bulkForm16,empId);
		Object finalEmployees[][]=getSqlModel().getSingleResult(empDetailsQuery);
		
		if (finalEmployees!=null && finalEmployees.length>0) {
			request.setAttribute("totalRecords",finalEmployees.length);
			request.setAttribute("recPerPage",bulkForm16.getRecordsPerPage());
			bulkForm16.setRecordFlag(true);
			return "1";
		} //end of if
		else{
			request.setAttribute("totalRecords",0);
			return "No records for selected criteria";
		} //end of else
	} //end of generateReport method
/**
 * This method will return the query String when query id is paased
 * @param id
 * @param bulkForm16
 * @param empId
 * @return
 */
	private String getQuery(int id, Form16MisReport bulkForm16,String empId) {
		String query="";
		int frmYear = bulkForm16.getFrmYear();
		int toYear = bulkForm16.getToYear(); 
		switch(id){
		// query to generate the links based on filters
		case 1: query="SELECT ROWNUM,HRMS_EMP_OFFC.EMP_ID FROM HRMS_EMP_OFFC " 
				+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_EMP_OFFC.EMP_ID= HRMS_SALARY_MISC.EMP_ID)" 
				+ " WHERE EMP_REGULAR_DATE < TO_DATE('01-04-"+toYear+"','DD-MM-YYYY') AND (EMP_LEAVE_DATE >TO_DATE('31-03-"+frmYear+"','DD-MM-YYYY') " 
				+ " OR EMP_LEAVE_DATE IS NULL) " +filter(bulkForm16)
				+ " ORDER BY SAL_PANNO, HRMS_EMP_OFFC.EMP_ID "; 
		break;
		// query to get the employee details
		case 2:	query="SELECT HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||  ' '|| "
				+ " HRMS_EMP_OFFC.EMP_LNAME,' ')  ||'-'||NVL(RANK_NAME,' ')as emp_name,NVL(DIV_NAME||' '||DIV_ADDRESS1||','||DIV_ADDRESS2||' '||   "
				+ " DIV_ADDRESS3||' '||HRMS_LOCATION.LOCATION_NAME||'-'||DIV_PINCODE,' ')as emplo,  nvl(DIV_PANNO,' '),NVL(DIV_TANNO,' '),NVL(HRMS_LOCATION.LOCATION_NAME,' '), "
				+ " NVL(DIV_NAME,' '),TO_CHAR(SYSDATE,'DD-MM-YYYY') "
				+ " ,CASE WHEN EMP_REGULAR_DATE <= TO_DATE('01-04-"+frmYear+"','DD-MM-YYYY')  THEN '01-04-"+frmYear+"' "  
				+ " WHEN EMP_REGULAR_DATE > TO_DATE('01-04-"+frmYear+"','DD-MM-YYYY')  THEN TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') "  
				+ " END AS JOINING,nvl(SAL_PANNO,' '), NVL(DIV_CIT_ADDRESS,' '), NVL(CIT_CITY.LOCATION_NAME,' '), NVL(DIV_CIT_PIN,'')  "
				+ " FROM HRMS_EMP_OFFC "  
				+ " LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "  
				+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "  
				+  " LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV	  "
				+ " LEFT JOIN HRMS_LOCATION ON HRMS_LOCATION.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID " 
				+ " LEFT JOIN HRMS_LOCATION CIT_CITY ON CIT_CITY.LOCATION_CODE = HRMS_DIVISION.DIV_CIT_CITY "
				+ " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) " 
				+ " where HRMS_EMP_OFFC.EMP_ID IN ("+empId+")";
			break;
		// query to get the settlement details	
		case 3: query="SELECT SETTL_ECODE,CASE WHEN SETTL_SEPRDT > TO_DATE('31-03-"+toYear+"','DD-MM-YYYY') THEN '31-03-"+toYear+"' ELSE " 
				+ " TO_CHAR(SETTL_SEPRDT,'DD-MM-YYYY') END "
				+ " FROM HRMS_SETTL_HDR  "
				+ " WHERE SETTL_RESGNO IN(SELECT (RESIGN_CODE) FROM HRMS_RESIGN WHERE RESIGN_EMP IN ("+empId+") AND HRMS_RESIGN.RESIGN_WITHDRAWN != 'Y') " 
				+ " AND SETTL_LOCKFLAG = 'Y' AND   SETTL_SETTLDT BETWEEN TO_DATE('01-04-"+frmYear+"','DD-MM-YYYY') "
				+ " AND TO_DATE('31-03-"+toYear+"','DD-MM-YYYY')";
			break;
		// query to get the tax details	
		case 4: query = "SELECT EMP_ID,NVL(TDS_GROSS_SALARY,0),NVL(TDS_EXCEMPTIONS,0),NVL(TDS_REBATE,0),NVL(TDS_OTH_INCOME,0), " 
				+ " NVL(TDS_DEDUCTIONS,0),NVL(TDS_TAXABLE_INCOME,0),NVL(TDS_TOTAL_TAX,0),NVL(TDS_EDUC_CESS,0),  "
				+ " NVL(TDS_SURCHARGE,0),NVL(TDS_NET_TAX,0),NVL(TDS_TAXPERMONTH,0),NVL(TDS_TAX_PAID,0), "
				+ " NVL(TDS_PROF_TAX,0) FROM HRMS_TDS  "
				+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_TDS.TDS_EMP_ID "  
				+ " WHERE EMP_ID IN ("+empId+") and TDS_FROM_YEAR="+frmYear+" "
				+ " and TDS_TO_YEAR="+toYear+" ";
		break;
		// query to get the perq amount	
		case 5: query = "SELECT TDS_EMP_ID,NVL(TDS_PERQ_AMOUNT,0)FROM HRMS_TDS_PERQ "  
				+ " LEFT JOIN HRMS_PERQUISITE_HEAD ON (HRMS_PERQUISITE_HEAD.PERQ_CODE = HRMS_TDS_PERQ.TDS_PERQ_HEAD) "  
				+ " WHERE TDS_EMP_ID IN ("+empId+") "
				+ " AND TDS_YEAR_FROM="+frmYear+" AND TDS_YEAR_TO="+toYear+"";
		break;
		
		/*case 6: query = "SELECT EMP_ID,HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,NVL(INV_VALID_AMOUNT,0),INV_OTHER_FLAG "  
		+" FROM HRMS_TAX_INVESTMENT  "
		+" LEFT JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE) "  
		+" WHERE INV_CHAPTER = 'OTHER' and INV_FINYEAR_FROM="+frmYear+" "
		+" and INV_FINYEAR_TO="+toYear+" AND EMP_ID IN ("+empId+")";*/
		
		// query to get the investment details for OTHER chapter	
		case 6: query = "SELECT EMP_ID,HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,NVL( CASE WHEN INV_TYPE = 'N' THEN NVL(INV_VALID_AMOUNT,0) "
					+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) > (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
					+ " WHERE TDS_FINANCIALYEAR_FROM = "
					+ frmYear
					+ " AND  TDS_FINANCIALYEAR_TO = "
					+ toYear
					+ ") AND INV_IS_VERIFIED='Y'"
					+ " THEN LEAST(NVL(INV_VERIFIED_AMOUNT,0),NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0)))   "
					+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) < (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
					+ " WHERE TDS_FINANCIALYEAR_FROM = "
					+ frmYear
					+ " AND  TDS_FINANCIALYEAR_TO = "
					+ toYear
					+ ")  "
					+ " THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))) "
					+ "  WHEN (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER " 
					+	"  WHERE TDS_FINANCIALYEAR_FROM = "+frmYear+" AND  TDS_FINANCIALYEAR_TO = "+toYear+") IS NULL THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0)))"
					+ " END,0) AS INVESTMENT_AMOUNT,INV_OTHER_FLAG "  
					+ " FROM HRMS_TAX_INVESTMENT  "
					+ " LEFT JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE) "  
					+ " WHERE INV_CHAPTER = 'OTHER' and INV_FINYEAR_FROM="+frmYear+" "
					+ " and INV_FINYEAR_TO="+toYear+" AND EMP_ID IN ("+empId+")";
		break;
		
		/*case 7: query = "SELECT EMP_ID,HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION,SUM(INV_VALID_AMOUNT ) "  
		+" FROM HRMS_TAX_INVESTMENT   "
		+" LEFT JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE =  HRMS_TAX_INVESTMENT.INV_CODE) "  
		+" WHERE INV_CHAPTER='EXEMPT' AND SUBSTR(INV_SECTION,0,2)=10   and INV_FINYEAR_FROM="+frmYear+" "
		+" and INV_FINYEAR_TO="+toYear+"  and EMP_ID IN ("+empId+") "
		+" GROUP BY EMP_ID,HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION ";*/
		
		// query to get the investment details for Section 10	
		case 7: query = "SELECT EMP_ID,HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION,SUM(NVL( CASE WHEN INV_TYPE = 'N' THEN NVL(INV_VALID_AMOUNT,0) "
				+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) > (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
				+ " WHERE TDS_FINANCIALYEAR_FROM = "
				+ frmYear
				+ " AND  TDS_FINANCIALYEAR_TO = "
				+ toYear
				+ ") AND INV_IS_VERIFIED='Y'"
				+ " THEN CASE WHEN NVL(INV_VERIFIED_AMOUNT,0)>NVL(INV_VALID_AMOUNT,0) THEN NVL(INV_VALID_AMOUNT,0) ELSE NVL(INV_VERIFIED_AMOUNT,0) END   "
				+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) < (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
				+ " WHERE TDS_FINANCIALYEAR_FROM = "
				+ frmYear
				+ " AND  TDS_FINANCIALYEAR_TO = "
				+ toYear
				+ " )  "
				+ " THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))) "
				+ "  WHEN (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER " 
				+ "  WHERE TDS_FINANCIALYEAR_FROM = "+frmYear+" AND  TDS_FINANCIALYEAR_TO = "+toYear+") IS NULL THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0)))END,0)) "  
				+ " FROM HRMS_TAX_INVESTMENT   "
				+ " LEFT JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE =  HRMS_TAX_INVESTMENT.INV_CODE) "  
				+ " WHERE INV_CHAPTER='EXEMPT' AND SUBSTR(INV_SECTION,0,2)=10   and INV_FINYEAR_FROM="+frmYear+" "
				+ " and INV_FINYEAR_TO="+toYear+"  and EMP_ID IN ("+empId+") "
				+ " GROUP BY EMP_ID,HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION ";
		break;
		
		/*case 8: query = "SELECT EMP_ID,INV_CODE,INV_VALID_AMOUNT " 
		+" FROM HRMS_EMP_INVESTMENT  "
		+" WHERE EMP_ID IN ("+empId+") AND INV_FINYEAR_FROM="+frmYear+" AND INV_FINYEAR_TO="+toYear+" " 
		+" AND INV_CODE = "+taxParameter[0][0]+"";*/
		
		// query to get the investment details for HRA exemption	
		case 8: query = "SELECT EMP_ID,HRMS_EMP_INVESTMENT.INV_CODE,sum(NVL( CASE WHEN INV_TYPE = 'N' THEN NVL(INV_VALID_AMOUNT,0) "
				+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) > (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
				+ " WHERE TDS_FINANCIALYEAR_FROM = "
				+ frmYear
				+ " AND  TDS_FINANCIALYEAR_TO = "
				+ toYear
				+ ") AND INV_IS_VERIFIED='Y'"
				+ " THEN CASE WHEN NVL(INV_VERIFIED_AMOUNT,0)>NVL(INV_VALID_AMOUNT,0) THEN NVL(INV_VALID_AMOUNT,0) ELSE NVL(INV_VERIFIED_AMOUNT,0) END   "
				+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) <= (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
				+ " WHERE TDS_FINANCIALYEAR_FROM = "
				+ frmYear
				+ " AND  TDS_FINANCIALYEAR_TO = "
				+ toYear
				+ ")  "
				+ " THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))) "
				+ "  WHEN (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER " 
				+ "  WHERE TDS_FINANCIALYEAR_FROM = "+frmYear+" AND  TDS_FINANCIALYEAR_TO = "+toYear+") IS NULL THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))) END,0)) AS INVESTMENT_AMOUNT8 " 
				+ " FROM HRMS_EMP_INVESTMENT  "
				+ " INNER JOIN HRMS_TAX_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE) "
				+ " WHERE EMP_ID IN ("+empId+") AND INV_FINYEAR_FROM="+frmYear+" AND INV_FINYEAR_TO="+toYear+" " 
				+ " AND INV_CODE = "+taxParameter[0][0]+"  GROUP BY EMP_ID,HRMS_EMP_INVESTMENT.INV_CODE,INV_TYPE,INV_IS_VERIFIED ";
		break;
		
		/*case 9: query = "SELECT EMP_ID,INV_CODE,NVL(INV_VALID_AMOUNT,0) "
		+" FROM HRMS_EMP_INVESTMENT  "
		+" WHERE EMP_ID IN ("+empId+") AND INV_FINYEAR_FROM="+frmYear+" AND INV_FINYEAR_TO="+toYear+" ";*/
		
		// query to get the investment details 	
		case 9: query = "SELECT EMP_ID,HRMS_EMP_INVESTMENT.INV_CODE, sum(NVL( CASE WHEN INV_TYPE = 'N' THEN NVL(INV_VALID_AMOUNT,0) "
				+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) > (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
				+ " WHERE TDS_FINANCIALYEAR_FROM = "
				+ frmYear
				+ " AND  TDS_FINANCIALYEAR_TO = "
				+ toYear
				+ ") AND INV_IS_VERIFIED='Y'"
				+ " THEN CASE WHEN NVL(INV_VERIFIED_AMOUNT,0)>NVL(INV_VALID_AMOUNT,0) THEN NVL(INV_VALID_AMOUNT,0) ELSE NVL(INV_VERIFIED_AMOUNT,0) END   "
				+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) <= (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
				+ " WHERE TDS_FINANCIALYEAR_FROM = "
				+ frmYear
				+ " AND  TDS_FINANCIALYEAR_TO = "
				+ toYear
				+ ")  "
				+ " THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))) "
				+ "  WHEN (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER " 
				+ "  WHERE TDS_FINANCIALYEAR_FROM = "+frmYear+" AND  TDS_FINANCIALYEAR_TO = "+toYear+") IS NULL THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))) END,0)) AS INVESTMENT_AMOUNT  "
				+ " FROM HRMS_EMP_INVESTMENT  "
				+ " INNER JOIN HRMS_TAX_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE) "
				+ " WHERE EMP_ID IN ("+empId+") AND INV_FINYEAR_FROM="+frmYear+" AND INV_FINYEAR_TO="+toYear+" "
				+ " GROUP BY EMP_ID,HRMS_EMP_INVESTMENT.INV_CODE,INV_TYPE,INV_IS_VERIFIED ";
		break;
		
	/*	case 10: query = "SELECT EMP_ID,NVL(INV_VALID_AMOUNT,0),INV_SECTION " 
		+" FROM HRMS_EMP_INVESTMENT   "
		+" LEFT JOIN HRMS_TAX_INVESTMENT ON(HRMS_TAX_INVESTMENT.INV_CODE = HRMS_EMP_INVESTMENT.INV_CODE) "  
		+" WHERE INV_SECTION IN('80E','80G','80D') AND EMP_ID IN ("+empId+") "
		+" AND INV_FINYEAR_FROM = "+frmYear+" AND  INV_FINYEAR_TO = "+toYear+"";*/
		
		// query to get the investment details for section other than '80CCC','80CCCD','80C' and 10
		case 10: query = "SELECT EMP_ID,NVL( CASE WHEN INV_TYPE = 'N' THEN NVL(INV_VALID_AMOUNT,0) "
				+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) > (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
				+ " WHERE TDS_FINANCIALYEAR_FROM = "
				+ frmYear
				+ " AND  TDS_FINANCIALYEAR_TO = "
				+ toYear
				+ ") AND INV_IS_VERIFIED='Y'"
				+ " THEN CASE WHEN NVL(INV_VERIFIED_AMOUNT,0)>NVL(INV_VALID_AMOUNT,0) THEN NVL(INV_VALID_AMOUNT,0) ELSE NVL(INV_VERIFIED_AMOUNT,0) END   "
				+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) < (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
				+ " WHERE TDS_FINANCIALYEAR_FROM = "
				+ frmYear
				+ " AND  TDS_FINANCIALYEAR_TO = "
				+ toYear
				+ ")  "
				+ " THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))) "
				+ "  WHEN (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER " 
				+ "  WHERE TDS_FINANCIALYEAR_FROM = "+frmYear+" AND  TDS_FINANCIALYEAR_TO = "+toYear+") IS NULL THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))) END,0) AS INVESTMENT_AMOUNT,INV_SECTION " 
				+ " FROM HRMS_EMP_INVESTMENT   "
				+ " INNER JOIN HRMS_TAX_INVESTMENT ON(HRMS_TAX_INVESTMENT.INV_CODE = HRMS_EMP_INVESTMENT.INV_CODE) "  
				//+" WHERE INV_SECTION IN('80E','80G','80D') AND EMP_ID IN ("+empId+") "
				+ " WHERE INV_SECTION NOT IN('80CCC','80CCCD','80C') AND SUBSTR(INV_SECTION,0,2)!=10  AND EMP_ID IN ("+empId+") "
				+ " AND INV_FINYEAR_FROM = "+frmYear+" AND  INV_FINYEAR_TO = "+toYear+"";
		break;
		
		/*case 11: query = "SELECT EMP_ID,CHALLAN_TDS,HRMS_TAX_CHALLAN_DTL.CHALLAN_SURCHARGE,CHALLAN_EDU_CESS, "
		+" CHALLAN_TOTAL_TAX,CHALLAN_CHQNO,CHALLAN_BANK,  TO_CHAR(CHALLAN_DATE,'DD-MM-YYYY'),CHALLAN_NO, "
		+" HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE "
		+" FROM HRMS_TAX_CHALLAN_DTL "   
		+" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "  
		//+" LEFT JOIN hrms_bank on hrms_bank.BANK_MICR_CODE = hrms_tax_challan.CHALLAN_BANK   "
		+" WHERE EMP_ID IN ("+empId+") AND CHALLAN_YEAR="+frmYear+" AND CHALLAN_MONTH IN(4,5,6,7,8,9,10,11,12) AND  CHALLAN_TOTAL_TAX >0 " 
		+" ORDER BY  CHALLAN_MONTH";
		break;*/
		
		// query to get the challan details for apr to dec 
		case 11: query = "SELECT EMP_ID, "
			+ " NVL(CHALLAN_TOTAL_TAX,0),CHALLAN_CHQNO,CHALLAN_BANK,  TO_CHAR(CHALLAN_DATE,'DD-MM-YYYY'),CHALLAN_NO, "
			+ " HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE "
			+ " FROM HRMS_TAX_CHALLAN_DTL "   
			+ " INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "  
			//+ " LEFT JOIN hrms_bank on hrms_bank.BANK_MICR_CODE = hrms_tax_challan.CHALLAN_BANK   "
			//+ " WHERE EMP_ID IN ("+empId+") AND CHALLAN_YEAR="+frmYear+" AND CHALLAN_MONTH IN(4,5,6,7,8,9,10,11,12) AND  CHALLAN_TOTAL_TAX >0 " 
			+ " WHERE EMP_ID IN ("+empId+") AND CHALLAN_YEAR="+frmYear+" AND CHALLAN_MONTH IN(4,5,6,7,8,9,10,11,12)  " 
			+ " ORDER BY  CHALLAN_MONTH";
			break;
		
	/*	case 12: query = "SELECT EMP_ID,CHALLAN_TDS,HRMS_TAX_CHALLAN_DTL.CHALLAN_SURCHARGE,CHALLAN_EDU_CESS,CHALLAN_TOTAL_TAX, "
		+" CHALLAN_CHQNO,CHALLAN_BANK,  TO_CHAR(CHALLAN_DATE,'DD-MM-YYYY'),CHALLAN_NO,HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE  "
		+" FROM HRMS_TAX_CHALLAN_DTL    "
		+" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "  
		//+" left join hrms_bank on hrms_bank.BANK_MICR_CODE = hrms_tax_challan.CHALLAN_BANK   "
		+" WHERE EMP_ID IN ("+empId+") AND CHALLAN_YEAR="+toYear+" AND CHALLAN_MONTH IN(1,2,3) AND  CHALLAN_TOTAL_TAX >0 "
		+" ORDER BY  CHALLAN_MONTH";
		break;*/
			
			// query to get the challan details for jan to march
		case 12: query = "SELECT EMP_ID, "
				+ " NVL(CHALLAN_TOTAL_TAX,0),CHALLAN_CHQNO,CHALLAN_BANK,  TO_CHAR(CHALLAN_DATE,'DD-MM-YYYY'),CHALLAN_NO,HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE  "
				+ " FROM HRMS_TAX_CHALLAN_DTL    "
				+ " INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "  
				//+ " left join hrms_bank on hrms_bank.BANK_MICR_CODE = hrms_tax_challan.CHALLAN_BANK   "
				//+ " WHERE EMP_ID IN ("+empId+") AND CHALLAN_YEAR="+toYear+" AND CHALLAN_MONTH IN(1,2,3) AND  CHALLAN_TOTAL_TAX >0 "
				+ " WHERE EMP_ID IN ("+empId+") AND CHALLAN_YEAR="+toYear+" AND CHALLAN_MONTH IN(1,2,3)  "
				+ " ORDER BY  CHALLAN_MONTH";
			break;
		
		// query to get the certificate no
		case 13: query = "SELECT EMPID,CERT_NO FROM HRMS_TDS_CERT "
		+"WHERE EMPID IN ("+empId+") AND FROM_YEAR="+frmYear+" AND TO_YEAR="+toYear+"";
		break;
		
		// query to get the Salary designation
		case 14: query = "SELECT EMP_ID,RANK_NAME,MAX(LEDGER_MONTH) FROM HRMS_SALARY_"+frmYear+" "
			+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_SALARY_"+frmYear+".SAL_EMP_RANK) "
			+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+frmYear+".SAL_LEDGER_CODE) "
			+ " WHERE LEDGER_MONTH >=4 AND EMP_ID IN("+empId+") "
			+ " GROUP BY EMP_ID,RANK_NAME";
		 break;
		
		// query to get the Salary designation
		case 15: query = "SELECT EMP_ID,RANK_NAME,MAX(LEDGER_MONTH) FROM HRMS_SALARY_"+toYear+" "
			+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_SALARY_"+toYear+".SAL_EMP_RANK) "
			+ " INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+toYear+".SAL_LEDGER_CODE) "
			+ " WHERE LEDGER_MONTH <=3 AND EMP_ID IN("+empId+") "
			+ " GROUP BY EMP_ID,RANK_NAME";
		 break;
		
		default:
			break;	
		} //end of switch
		
		return query;
	} //end of getQuery method
	
	/**
	 * This method to be used for adding filter constraints to the Query's.
	 * @param bean
	 * @return
	 */
	public String filter(Form16MisReport bean)
	{
		String query="";
		
		if(!bean.getDivisionId().equals(""))
		{
			query+=" and HRMS_EMP_OFFC.EMP_DIV="+bean.getDivisionId();
		}
		if(!bean.getBranchId().equals(""))
		{
			query+=" and HRMS_EMP_OFFC.EMP_CENTER="+bean.getBranchId();
		}
		if(!bean.getDeptId().equals(""))
		{
			query+=" and HRMS_EMP_OFFC.EMP_DEPT="+bean.getDeptId();
		}
		if(!bean.getEmpTypeId().equals(""))
		{
			query+=" and HRMS_EMP_OFFC.EMP_TYPE="+bean.getEmpTypeId();
		}
		if(!bean.getEmpId().equals(""))
		{
			query+=" and HRMS_EMP_OFFC.EMP_Id="+bean.getEmpId();
		}
		return query;
	} //end of filter method
	/**
	 * This method to be used get the father details.
	 * @param bean
	 * @param taxParameter
	 */
	public void getFatherRecord(Form16MisReport bean, Object[][] taxParameter) {
		
		Object[][] relation = null;
		Object[][] fatherName=null;
		try {
			String query = "SELECT RELATION_CODE,RELATION_NAME FROM HRMS_RELATION "
					+ " where upper(RELATION_NAME)= 'FATHER' "
					+ " ORDER BY RELATION_CODE ";
			relation = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in relation query",e);
		} //end of catch
		
			if(relation.length==0)
			{
				bean.setSignAuthEmpFather("");
			} //end of if
			else{
				if(String.valueOf(taxParameter[0][3]).equals("")||String.valueOf(taxParameter[0][3]).equals("null")
						||String.valueOf(taxParameter[0][3]).equals(null)){
					bean.setSignAuthEmpFather("");
				} //end of if
				else{
					try {
						String sql = "SELECT FML_ID,NVL(FML_FNAME||' '||FML_MNAME||' '||FML_LNAME,' '), "
								+ " FML_RELATION "
								+ " FROM  HRMS_EMP_FML "
								+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_FML.EMP_ID) "
								+ " LEFT JOIN HRMS_RELATION  ON(HRMS_RELATION.RELATION_CODE=HRMS_EMP_FML.FML_RELATION) "
								+ " WHERE HRMS_EMP_OFFC.EMP_ID ="
								+ taxParameter[0][3]
								+ " AND RELATION_CODE="
								+ relation[0][0] + " " + " ORDER BY FML_ID ";
						fatherName = getSqlModel().getSingleResult(sql);
					} catch (Exception e) {
						logger.error("exception in fatherName",e);
					} //end of catch
				} //end of else
				
				if(fatherName==null)
				{
					bean.setSignAuthEmpFather("");
				} //end of if
				else if(fatherName.length==0)
				{
					bean.setSignAuthEmpFather("");
				} //end of else if
				else{
					bean.setSignAuthEmpFather(String.valueOf(fatherName[0][1]));
				} //end of else
			} //end of else
		} //end of getFatherRecord method
	/**
	 * This method is used to generate the report
	 * @param request
	 * @param response
	 * @param bulkForm16
	 */
	public void generateReport(HttpServletRequest request,
			HttpServletResponse response, Form16MisReport bulkForm16) {
		String empIdList = "";
		int frmYear = bulkForm16.getFrmYear();
		int toYear = bulkForm16.getToYear(); 
		Object finalEmployeesList[][] = null;
		if(bulkForm16.isGeneralFlag()){
			finalEmployeesList = new Object[1][2];  
			finalEmployeesList[0][0]= bulkForm16.getUserEmpId();
			finalEmployeesList[0][1]= bulkForm16.getUserEmpId();
		} //end of if
		else{
			String empListQuery=getQuery(1,bulkForm16,empIdList);
			finalEmployeesList=getSqlModel().getSingleResult(empListQuery);
		} //end of else
		
		
		if (finalEmployeesList!=null && finalEmployeesList.length>0) {
			
			int i=0;
			int noOfRecrdsPerPage=0;
			
			if (Integer.parseInt(""+bulkForm16.getRecordsPerPage())!= 0) {	
				noOfRecrdsPerPage=bulkForm16.getRecordsPerPage();
			} //end of if
			
			int startRecord=0,endpage=0;
			if(!bulkForm16.getRangeCode().equals("")){
				 startRecord=Integer.parseInt(bulkForm16.getRangeCode())*noOfRecrdsPerPage;
				 endpage=startRecord+noOfRecrdsPerPage;
			} //end of if
			
			if (endpage>=finalEmployeesList.length) {
				endpage=finalEmployeesList.length;
			} //end of if
			i=startRecord;
			
			if(!bulkForm16.getEmpId().equals(""))
			{
				i=0;
				endpage=1;
			} //end of if
			
			logger.info("i==========="+i);
			logger.info("endpage==========="+endpage);
			try {
				for (int j = i; j < endpage; j++) {
					empIdList += String.valueOf(finalEmployeesList[j][1])+ ",";
				} //end of loop
				empIdList = empIdList.substring(0, empIdList.length() - 1);
			} catch (Exception e) {
				logger.error("exception in endpage loop",e);
			}
			
			logger.info("empId==========="+empIdList);
			
			
			//Form16MisReport employeeBean = null;
			
			if(!empIdList.equals("")){
				//get all the employee info into the bean
				bulkForm16 = loadEmployeeInformation(empIdList,bulkForm16);
				
				try {
					createReport(bulkForm16, empIdList,request, response);
				} catch (Exception e) {
					logger.error("exception in createReport",e);
				} //end of catch
				
			} //end of if
			
		} //end of if
		else{
			ReportDataSet rds = new ReportDataSet();
			rds.setFileName("Form 16");
			rds.setReportName("FORM NO.16");
			rds.setReportType(bulkForm16.getRepType());
			rds.setReportHeaderRequired(false);
			rds.setShowPageNo(false);
			org.paradyne.lib.ireportV2.ReportGenerator rg=new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			org.paradyne.lib.ireportV2.TableDataSet noData = new org.paradyne.lib.ireportV2.TableDataSet();			
			Object[][] noDataObj = new Object[1][1];
			noDataObj[0][0] = "No records available";
			noData.setData(noDataObj);
			noData.setCellAlignment(new int[] { 1 });
			noData.setCellWidth(new int[] { 100 });
			noData.setBorder(false);
			rg.addTableToDoc(noData);
			rg.process();
			rg.createReport(response);
			
		}			
	} //end of generateReport method
	//(50000-(50000/5))*31*3/(365*100)
	/**
	 * create the report
	 */
	private void createReport(Form16MisReport bulkForm16, String empIdList, HttpServletRequest request, HttpServletResponse response) {
		String[] empLength = empIdList.split(",");
		ReportDataSet rds = new ReportDataSet();
		rds.setFileName("Form 16");
		rds.setReportName("FORM NO.16");
		rds.setReportType(bulkForm16.getRepType());
		rds.setReportHeaderRequired(false);
		rds.setShowPageNo(false);
		org.paradyne.lib.ireportV2.ReportGenerator rg=new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
		
		String frmYear = String.valueOf(bulkForm16.getFrmYear());
		String ack1 = "", ack2 = "", ack3 = "", ack4 = "";
		String taxDeductedInChallan1 = "", taxDeductedInChallan2 = "", taxDeductedInChallan3 = "", taxDeductedInChallan4 = "";
		String toYear = String.valueOf(bulkForm16.getToYear());
		String quarterFrm = frmYear.substring(2, 4);
		String quarterTo = toYear.substring(2, 4);
		
		Object[][] ackData=null;
		HashMap acknMap=new HashMap();
		for (int i = 0; i < empLength.length; i++) {
			acknMap.put("6#"+bulkForm16.getFrmYear()+"#"+empLength[i],new String[]{"",""});
			acknMap.put("9#"+bulkForm16.getFrmYear()+"#"+empLength[i],new String[]{"",""});
			acknMap.put("12#"+bulkForm16.getFrmYear()+"#"+empLength[i],new String[]{"",""});
			acknMap.put("3#"+bulkForm16.getToYear()+"#"+empLength[i],new String[]{"",""});
		}
		try {
			/**
			 * to retreive the challan records for acknowledgement no, year and month..
			 */
			String ackNoQuery = "SELECT CASE WHEN CHALLAN_MONTH BETWEEN 4 AND 6 THEN 6 WHEN CHALLAN_MONTH BETWEEN 7 AND 9 THEN 9 "
							+" WHEN CHALLAN_MONTH BETWEEN 10 AND 12 THEN 12 WHEN CHALLAN_MONTH BETWEEN 1 AND 3 THEN 3 end,CHALLAN_YEAR,CHALLAN_ACK_NO,CHALLAN_TOTAL_TAX,EMP_ID FROM HRMS_TAX_CHALLAN"
							+" INNER JOIN HRMS_TAX_CHALLAN_DTL ON (HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE=HRMS_TAX_CHALLAN.CHALLAN_CODE)"
							//+" WHERE EMP_ID IN("+empIdList+") AND CHALLAN_TOTAL_TAX > 0 AND ((CHALLAN_MONTH >3 AND CHALLAN_YEAR="+bulkForm16.getFrmYear()+") "
							+" WHERE EMP_ID IN("+empIdList+") AND ((CHALLAN_MONTH >3 AND CHALLAN_YEAR="+bulkForm16.getFrmYear()+") "
							+ " OR (CHALLAN_MONTH <4 AND CHALLAN_YEAR="+bulkForm16.getToYear()+"))"; 
			ackData = getSqlModel().getSingleResult(ackNoQuery);
		} catch (Exception e) {
			logger.error("error in ackData query   "+e);
		} //end of catch
		/**
		 * set the acknowledgement details
		 */
		if(ackData !=null && ackData.length>0){
			for (int i = 0; i < ackData.length; i++) {
				String []tempArray=null;
				tempArray=(String[])acknMap.get(String.valueOf(ackData[i][0])+"#"+String.valueOf(ackData[i][1])+"#"+String.valueOf(ackData[i][4]));
					try {
						if(tempArray!=null){
						if (!tempArray[0].equals("")) {
							tempArray[0] = String.valueOf(Double
									.parseDouble(tempArray[0])
									+ (Double.parseDouble(String
											.valueOf(ackData[i][3]))));
						}else{
							tempArray[0] = String
											.valueOf(ackData[i][3]);
						}
						if (tempArray[1].equals("")) {
							tempArray[1] = checkNull(String.valueOf(ackData[i][2]));
						}
						acknMap.put(String.valueOf(ackData[i][0])+"#"+String.valueOf(ackData[i][1])+"#"+String.valueOf(ackData[i][4]), tempArray);
						}else{
							tempArray=new String[2];
							tempArray[0] =String
									.valueOf(ackData[i][3]);
							tempArray[1] = String.valueOf(ackData[i][2]);
							acknMap.put(String.valueOf(ackData[i][0])+"#"+String.valueOf(ackData[i][1])+"#"+String.valueOf(ackData[i][4]), tempArray);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				
			}
		}
		try {
			/**
			 * this query retrieves all the Tax Parameter data..
			 */
			String tdsParaQuery = "SELECT TDS_HRAEXEMPT_INVCODE,TDS_EMPPF_INVCODE,TDS_REBATEMAX_AMOUNT,TDS_SIGNING_AUTH,EMP_TOKEN, "
			+" NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '), "
			+" NVL(RANK_NAME,' ') FROM HRMS_TAX_PARAMETER  "
			+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TAX_PARAMETER.TDS_SIGNING_AUTH) " 
			+" LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK  "
			+" WHERE TDS_FINANCIALYEAR_FROM = "+frmYear+" AND TDS_FINANCIALYEAR_TO = "+toYear+"";
			taxParameter = getSqlModel().getSingleResult(tdsParaQuery);
		} catch (Exception e) {
			logger.error("error in taxParameter query   "+e);
		} //end of catch
		
		if(taxParameter !=null && taxParameter.length > 0){
			bulkForm16.setSignAuthEmpId(String.valueOf(taxParameter[0][3]));
			bulkForm16.setSignAuthtoken(String.valueOf(taxParameter[0][4]));
			bulkForm16.setSignAuthName(String.valueOf(taxParameter[0][5]));
			bulkForm16.setSignAuthEmpDesg(String.valueOf(taxParameter[0][6]));
			getFatherRecord(bulkForm16,taxParameter);
		} //end of if
		
		try {
			/**
			 * here in this query all the investments which are under section 80 are retrieved...
			 */
			String invDeductionQuery = "SELECT  INV_CODE,INV_NAME,ROWNUM FROM HRMS_TAX_INVESTMENT "
					+ " WHERE INV_SECTION ='80C' ORDER BY INV_CODE";
			invDeducData = getSqlModel().getSingleResult(invDeductionQuery);
		} catch (Exception e) {
			logger.error("Exception in creating invDeducData--"+e);
		} //end of catch
		
		/**
		 * here in this query all the investments which are under section 80CCC & 80CCCD are retrieved...
		 */
		try {
			String ccdquery = "SELECT  HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION "
					+ " FROM HRMS_TAX_INVESTMENT "
					+ " WHERE INV_SECTION IN('80CCC','80CCCD')";
			cccSection = getSqlModel().getSingleResult(ccdquery);
		} catch (Exception e) {
			logger.error("execption in cccSection----"+e);
		} //end of catch
		if(empLength != null && empLength.length >0){
		for (int i = 0; i < empLength.length; i++) {
			double year = 0,sec10Amt = 0;
			Object[][]headingForm16 = new Object[1][1];
			headingForm16[0][0] = "FORM NO.16";
			int [] cellWidthheadingForm16={100};
			int [] cellAlignheadingForm16={1};
			TableDataSet tableheadingForm16 = new TableDataSet();
			tableheadingForm16.setData(headingForm16);
			tableheadingForm16.setCellWidth(cellWidthheadingForm16);
			tableheadingForm16.setCellAlignment(cellAlignheadingForm16);
			tableheadingForm16.setBorder(false);
			tableheadingForm16.setBodyFontStyle(1);
			rg.addTableToDoc(tableheadingForm16);
			
			Object[][]empCertDetailsMap = null;
			try {
				empCertDetailsMap = (Object[][]) bulkForm16.getEmpCertMap().get(empLength[i]);
			} catch (Exception e) {
				logger.error("exception in empDetail",e);
			} //end of catch
			
			Object[][]headingDataCert = new Object[1][2];
			if(empCertDetailsMap == null){
				headingDataCert[0][1] = "Page 1 of 3";
				headingDataCert[0][0] = "";
			} //end of if
			else if(!(empCertDetailsMap.length >0)){
				headingDataCert[0][1] = "Page 1 of 3";
				headingDataCert[0][0] = "";
			} //end of else if
			else{
				if(String.valueOf(empCertDetailsMap[0][1]).equals("")
						||String.valueOf(empCertDetailsMap[0][1]).equals("null")
						||String.valueOf(empCertDetailsMap[0][1]).equals(null)){
					headingDataCert[0][1] = "Page 1 of 3";
					headingDataCert[0][0] = "";
				} //end of if
				else{
					headingDataCert[0][1] = "Page 1 of 3";
					headingDataCert[0][0] = "Certificate No: "+empCertDetailsMap[0][1];
				} //end of else
			} //end of else
			int [] cellWidthHeaderCert={50,50};
			int [] cellAlignHeaderCert={0,2};
			TableDataSet tableheadingDataCert = new TableDataSet();
			tableheadingDataCert.setData(headingDataCert);
			tableheadingDataCert.setCellWidth(cellWidthHeaderCert);
			tableheadingDataCert.setCellAlignment(cellAlignHeaderCert);
			tableheadingDataCert.setBorder(false);
			rg.addTableToDoc(tableheadingDataCert);  
			
			Object[][]headingData = new Object[3][1];
			headingData[0][0] = "[See rule 31 (1) (a)]";
			headingData[1][0] = "PART A";
			headingData[2][0] = "Certificate under section 203 of the Income Tax Act,1961 for Tax Deducted at Source on salary ";  //\n from income chargeable under the head 'Salaries'";
			int [] cellWidthHeader={100};
			int [] cellAlignHeader={1};
			TableDataSet tableheadingData = new TableDataSet();
			tableheadingData.setData(headingData);
			tableheadingData.setCellWidth(cellWidthHeader);
			tableheadingData.setCellAlignment(cellAlignHeader);
			tableheadingData.setBorder(false);
			tableheadingData.setBlankRowsBelow(1);
			rg.addTableToDoc(tableheadingData);  // print the report heading
			
			//========================Start of employee details=================================//
			Object[][]empDetailsMap = null;
			try {
				empDetailsMap = (Object[][]) bulkForm16.getEmpOfficialDataMap().get(empLength[i]);
			} catch (Exception e) {
				logger.error("exception in empDetail",e);
			} //end of catch
			
			Object[][]salDesigMap = null;
			try {
				// employee Designation
				salDesigMap = (Object[][]) bulkForm16.getSalDesignationMap().get(empLength[i]);
			} catch (Exception e) {
				
				logger.error("exception in empDetail",e);
			} //end of catch
			
			if(salDesigMap!=null && salDesigMap.length >0){
				empDetailsMap[0][1] = String.valueOf(empDetailsMap[0][1]).split("-")[0]+"- "
								+String.valueOf(salDesigMap[0][1]);
			} //end of if
			
			Object[][] empDetails =new Object[2][2];
			empDetails[0][0] = "Name and address of the employer";
			empDetails[1][0] = checkNull(String.valueOf(empDetailsMap[0][2]).toUpperCase());
			empDetails[0][1] = "Name and designation of the employee";
			empDetails[1][1] = checkNull(String.valueOf(empDetailsMap[0][1]).toUpperCase());
			int [] cellWidth7={65,65};
			int [] cellAlign7={0,0};
			
			TableDataSet tableNameAndAdd = new TableDataSet();
			tableNameAndAdd.setData(empDetails);
			tableNameAndAdd.setCellWidth(cellWidth7);
			tableNameAndAdd.setCellAlignment(cellAlign7);
			tableNameAndAdd.setBorderDetail(3);
			rg.addTableToDoc(tableNameAndAdd);
			
			//============================Pan details=====================//
			Object[][] panDetails =new Object[1][3];
			/**
			 * here PAN of the organisation is set
			 */
			if(empDetailsMap == null){
				panDetails[0][0] = "PAN/GIR NO OF EMPLOYER  :";
				panDetails[0][1] = "TAN  : ";
			} //end of if
			else if(!(empDetailsMap.length >0)){
				panDetails[0][0] = "PAN/GIR NO OF EMPLOYER  :";
				panDetails[0][1] = "TAN  : ";
			} //end of else if
			else{
				panDetails[0][0] = "PAN/GIR NO OF EMPLOYER  :  "+checkNull(String.valueOf(empDetailsMap[0][3]))+"";
				panDetails[0][1] = "TAN  :        "+checkNull(String.valueOf(empDetailsMap[0][4]))+"";
			} //end of else
			
			/**
			 * here PAN of the Employee is set..
			 */
			if(empDetailsMap == null){
				panDetails[0][2] = "PAN/GIR NO OF EMPLOYEE  : ";
			} //end of if
			else if(!(empDetailsMap.length >0)){
				panDetails[0][2] = "PAN/GIR NO OF EMPLOYEE  : ";
			} //end of else if
			else{
				panDetails[0][2] = "PAN/GIR NO OF EMPLOYEE  :      "+String.valueOf(empDetailsMap[0][9])+"";
			} //end of else
			int [] cellWidthPan={25,25,50};
			int [] cellAlignPan={0,0,0};
			
			TableDataSet tablePanDetails = new TableDataSet();
			tablePanDetails.setData(panDetails);
			tablePanDetails.setCellWidth(cellWidthPan);
			tablePanDetails.setCellAlignment(cellAlignPan);
			tablePanDetails.setBorderDetail(3);
			rg.addTableToDoc(tablePanDetails);
			
			//=======================Acknowledgement details==================//
			if(ackData == null){
				
			} //end of if
			else if(!(ackData.length >0)){
				
			} //end of else if
			else{
				try {
					for (int j = 0; j < ackData.length; j++) {
						/**
						 * this if condition is to retrieve the ack no for every quarter. It takes the
						 * last month of every quarter.
						 */
						String []tempArray=null;
						logger.info("empLength[i]=="+empLength[i]);
						try {
							tempArray = (String[]) acknMap.get("6#"
									+ bulkForm16.getFrmYear() + "#"
									+ empLength[i]);
							ack1 = tempArray[1];
							taxDeductedInChallan1 = tempArray[0];
							
							//logger.info("taxDeductedInChallan1 "+taxDeductedInChallan1);
							
						} catch (Exception e) {
							ack1 = "";
							taxDeductedInChallan1 = "";
						}
						tempArray=null;
						try {
							tempArray = (String[]) acknMap.get("9#"
									+ bulkForm16.getFrmYear() + "#"
									+ empLength[i]);
							ack2 = tempArray[1];
							taxDeductedInChallan2 = tempArray[0];
						} catch (Exception e) {
							ack2 = "";
							taxDeductedInChallan2 = "";
						}
						
						tempArray=null;
						try {
							tempArray = (String[]) acknMap.get("12#"
									+ bulkForm16.getFrmYear() + "#"
									+ empLength[i]);
							ack3 = tempArray[1];
							taxDeductedInChallan3 = tempArray[0];
						} catch (Exception e) {
							ack3 = "";
							taxDeductedInChallan3 = "";
						}
						tempArray=null;
						try {
							tempArray = (String[]) acknMap.get("3#"
									+ bulkForm16.getToYear() + "#"
									+ empLength[i]);
							ack4 = tempArray[1];
							taxDeductedInChallan4 = tempArray[0];
						} catch (Exception e) {
							ack4 = "";
							taxDeductedInChallan4 = "";
						}					} //end of loop
				} catch (Exception e) {
					logger.error("error in acknowledgement "+e);
				} //end of catch
			} //end of else
			
			Object[][]settleData = (Object[][])bulkForm16.getSettlementDataMap().get(empLength[i]);
			Object[][] quaDetails =new Object[5][4];
			quaDetails[0][0]="Quarter";
			quaDetails[0][1]="Acknowledgement No.";
			quaDetails[0][2]="Amount Of Tax deducted in respect of employee";
			quaDetails[0][3]="Amount Of tax deposited/remitted in respect of the employee";
			String periodFrom=""+empDetailsMap[0][8]+"";
			String periodTo="";
			year = 1+Double.parseDouble(String.valueOf(bulkForm16.getToYear()).substring(2,4));
			if(settleData !=null && settleData.length >0){
				
				periodTo=String.valueOf(settleData[0][1]);
			} //end of if
			else{
				//quaDetails[0][3]="TO\n"+"31-03-"+bulkForm16.getToYear()+"";
				periodTo="31-03-"+bulkForm16.getToYear()+"";
			} //end of else
			
			/*
			 * CIT details start
			 */
			Object[][] citDetails =new Object[1][3];
			citDetails[0][0] = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tCIT(TDS)\n\nAddress : "+checkNull(String.valueOf(empDetailsMap[0][10]).toUpperCase()) +"\n\nCity : " +
								checkNull(String.valueOf(empDetailsMap[0][11]).toUpperCase())+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPin Code :"+checkNull(String.valueOf(empDetailsMap[0][12]).toUpperCase());
			citDetails[0][1] = "Period \n"+"______________________________\n\n"+
			"FROM              : "+periodFrom+"\n\nTO                    : "+periodTo+"\n";
			citDetails[0][2] = "Assessment year\n"+"____________________\n\n\n"+bulkForm16.getToYear()+"-"+Math.round(year)+"";
			int [] cellWidthAck={50,30,20};
			int [] cellAlignAck={0,1,1};
			
			TableDataSet citTable = new TableDataSet();
			citTable.setData(citDetails);
			citTable.setCellWidth(cellWidthAck);
			citTable.setCellAlignment(cellAlignAck);
			citTable.setBorderDetail(3);
			rg.addTableToDoc(citTable);
			/*
			 * CIT details end
			 */
			Object[][] ackDetails =new Object[1][1];
			//ackDetails[0][0] = "Acknowledgement Nos. of all quarterly statements of TDS under sub-section(3) of section 200 as provided by TIN Facilitation Centre or NSDL web-site.";
			ackDetails[0][0] = "Summary of tax deducted at source";
			
			TableDataSet tableAck = new TableDataSet();
			tableAck.setData(ackDetails);
			tableAck.setCellWidth(new int[]{100});
			tableAck.setCellAlignment(new int[]{1});
			tableAck.setBorderDetail(3);
			rg.addTableToDoc(tableAck);
			
			//======================Quarter Ack frm and to======================//
			
			logger.info("assesnment year :-----"+String.valueOf(bulkForm16.getToYear()).substring(2,4));
			year = 1+Double.parseDouble(String.valueOf(bulkForm16.getToYear()).substring(2,4));
			quaDetails[1][0]="Quarter 1";
			quaDetails[1][1]=""+ack1+"";
			if(!taxDeductedInChallan1.equals("")){
				quaDetails[1][2]=formatter.format(Double.parseDouble(taxDeductedInChallan1));
				quaDetails[1][3]=formatter.format(Double.parseDouble(taxDeductedInChallan1));
			}else{
				quaDetails[1][2]="";
				quaDetails[1][3]="";
			}
			//quaDetails[1][4]="";
			//quaDetails[2][0]="July "+quarterFrm+"-Sep "+quarterFrm+"";
			quaDetails[2][0]="Quarter 2";
			quaDetails[2][1]=""+ack2+"";
			if(!taxDeductedInChallan2.equals("")){
				quaDetails[2][2]=formatter.format(Double.parseDouble(taxDeductedInChallan2));
				quaDetails[2][3]=formatter.format(Double.parseDouble(taxDeductedInChallan2));
			}else{
				quaDetails[2][2]="";
				quaDetails[2][3]="";
			}
			//quaDetails[2][4]="";
			quaDetails[3][0]="Quarter 3";
			//quaDetails[3][0]="Oct "+quarterFrm+"-Dec "+quarterFrm+"";
			quaDetails[3][1]=""+ack3+"";
			if(!taxDeductedInChallan3.equals("")){
				quaDetails[3][2]=formatter.format(Double.parseDouble(taxDeductedInChallan3));
				quaDetails[3][3]=formatter.format(Double.parseDouble(taxDeductedInChallan3));
			}else{
				quaDetails[3][2]="";
				quaDetails[3][3]="";
			}
			//quaDetails[3][4]="";
			//quaDetails[4][0]="Jan "+quarterTo+"-Mar "+quarterTo+"";
			quaDetails[4][0]="Quarter 4";
			quaDetails[4][1]=""+ack4+"";
			if(!taxDeductedInChallan4.equals("")){
				quaDetails[4][2]=formatter.format(Double.parseDouble(taxDeductedInChallan4));
				quaDetails[4][3]=formatter.format(Double.parseDouble(taxDeductedInChallan4));
			}else{
				quaDetails[4][2]="";
				quaDetails[4][3]="";
			}
			//quaDetails[4][4]="";
			int [] cellWidthQua={25,25,15,15};
			int [] cellAlignQua={1,1,1,1};
			
			TableDataSet tableQuarter = new TableDataSet();
			tableQuarter.setData(quaDetails);
			tableQuarter.setCellWidth(cellWidthQua);
			tableQuarter.setCellAlignment(cellAlignQua);
			tableQuarter.setBorderDetail(3);
			rg.addTableToDoc(tableQuarter);
			
			//======================start of Details of salary====================//
			Object[][] taxData=null;
			taxData = (Object[][])bulkForm16.getTaxSalaryDataMap().get(empLength[i]);
			
			Object[][]notSaved = new Object[1][1];
			int [] cellWidthnotSaved={100};
			int [] cellAlignnotSaved={1};
			if(taxData == null){
				notSaved[0][0]="TDS Calculation not saved";
				TableDataSet tablenotSaved = new TableDataSet();
				tablenotSaved.setData(notSaved);
				tablenotSaved.setCellWidth(cellWidthnotSaved);
				tablenotSaved.setCellAlignment(cellAlignnotSaved);
				tablenotSaved.setBorder(false);
				rg.addTableToDoc(tablenotSaved);
			} //end of if
			else if(taxData.length == 0){
				notSaved[0][0]="TDS Calculation not saved";
				TableDataSet tablenotSaved = new TableDataSet();
				tablenotSaved.setData(notSaved);
				tablenotSaved.setCellWidth(cellWidthnotSaved);
				tablenotSaved.setCellAlignment(cellAlignnotSaved);
				tablenotSaved.setBorder(false);
				rg.addTableToDoc(tablenotSaved);
			} //end of else if
			else{
				try {
					notSaved = new Object[2][1];
					notSaved[0][0]="PART B (Refer Note 1)";
					notSaved[1][0]="Details of salary paid and any other income and tax deducted";
					TableDataSet tablenotSaved = new TableDataSet();
					tablenotSaved.setData(notSaved);
					tablenotSaved.setCellWidth(cellWidthnotSaved);
					tablenotSaved.setCellAlignment(cellAlignnotSaved);
					tablenotSaved.setBorder(false);
					rg.addTableToDoc(tablenotSaved);
					
					//=============== RS row==========================//
					Object taxTotaRs[][] = new Object[1][4];
					taxTotaRs[0][0] = "";
					taxTotaRs[0][1] = "(Rs.)";
					taxTotaRs[0][2] = "(Rs.)";
					taxTotaRs[0][3] = "(Rs.)";
					int[] cellWidthRs = { 50, 15, 15, 20 };
					int[] cellAlignRs = { 0, 2, 2, 2 };
					
					TableDataSet tableRs = new TableDataSet();
					tableRs.setData(taxTotaRs);
					tableRs.setCellWidth(cellWidthRs);
					tableRs.setCellAlignment(cellAlignRs);
					tableRs.setBorderDetail(3);
					rg.addTableToDoc(tableRs);
				} catch (Exception e) {
					logger.error("exception in tax data",e);
				} //end of catch
			
			
			//=============Gross salary=====================//
			Object [][]perquisiteData = null;
			perquisiteData = (Object[][])bulkForm16.getPerquisiteDataMap().get(empLength[i]);
			double totalPerqAmt=0;
			
			if(perquisiteData == null){
				
			} //end of if
			else if(!(perquisiteData.length >0)){
				
			} //end of else if
			else{
				try {
					/**
					 * this for loop is to add the total Perquisite amount of the employee....
					 */
					for (int j = 0; j < perquisiteData.length; j++) {
						totalPerqAmt += Double.parseDouble(String.valueOf(perquisiteData[j][1]));
					} //end of loop
				} catch (Exception e) {
					logger.error("exception in perquisiteData loop for sum",e);
				} //end of catch
			} //end of else
			
			Object[][] otherIncomeData = null;
			otherIncomeData = (Object[][])bulkForm16.getOtherIncomeDataMap().get(empLength[i]);
			String add="",amt="",total="";
			double totalOtherAmt=0;
			
			if(otherIncomeData == null){
				add = "7. Add:Any other income reported by the employee";
			} //end of if
			else if(otherIncomeData.length == 0){
				add = "7. Add:Any other income reported by the employee";
			} //end of else if
			else{
				add = "7. Add:Any other income reported by the employee";
				try {
					for (int j = 0; j < otherIncomeData.length; j++) {
						add += "\n" + String.valueOf(otherIncomeData[j][2]);
						amt += "\n" + String.valueOf(otherIncomeData[j][3]);
							if(String.valueOf(otherIncomeData[j][4]).equals("D")){ // if other amount is deductive
								totalOtherAmt -= Double.parseDouble(String.valueOf(otherIncomeData[j][3]));
							} //end of if
							else{
								totalOtherAmt += Double.parseDouble(String.valueOf(otherIncomeData[j][3]));
							} //end of else
						total += "\n";
					} //end of loop
				} catch (Exception e) {
					logger.error("Exception in calculating otherIncomeData--"+e);
				} //end of catch
			} //end of else
			
			Object taxTotal[][]= new Object[1][4];
			taxTotal[0][0]="1. Gross salary\n" +
			   "   (a) Salary as per provisions contained in section 17(1)\n" +
			   "   (b) Value of perquisites under section 17(2) as per Form No.12BA,wherever applicable)\n" +
			   "   (c) Profits in lieu of salary under section 17(3)(as per Form No.12BA,wherever applicable)\n" +
			   "   (d) Total";
			if(String.valueOf(taxData[0][1]).trim().equals("") || String.valueOf(taxData[0][1]).trim().equals("null")){
				taxTotal[0][1]="\nNIL\n\n"+0+"\n\nNIL";
				taxTotal[0][2]="\n\n\n\n\n\n\n\nNIL";
			} //end of if
			else{
				taxTotal[0][1]="\n"+formatter.format((Math.round((Double.parseDouble(String.valueOf(taxData[0][1])))) - totalPerqAmt - totalOtherAmt))+"\n\n"+formatter.format(Math.round(totalPerqAmt))+"\n\nNIL";
				taxTotal[0][2]="\n\n\n\n\n\n"+formatter.format(Math.round(Double.parseDouble(String.valueOf(taxData[0][1])) - totalOtherAmt))+"";
			} //end of else
			taxTotal[0][3]="";
			int [] cellWidthtaxTotal={50,15,15,20};
			int [] cellAligntaxTotal={0,2,2,2};
			
			TableDataSet tabletaxTotal = new TableDataSet();
			tabletaxTotal.setData(taxTotal);
			tabletaxTotal.setCellWidth(cellWidthtaxTotal);
			tabletaxTotal.setCellAlignment(cellAligntaxTotal);
			tabletaxTotal.setBorderDetail(3);
			rg.addTableToDoc(tabletaxTotal);
			
			//=========Exempt investment data=====================//
			Object[][] exemptInvData=null;
			Object exemptData[][] = null;
			exemptInvData = (Object[][])bulkForm16.getExemptInvestmentDataMap().get(empLength[i]);
			if(exemptInvData ==null || exemptInvData.length ==0){
				
			}
			else{
				Object[][] empInvHra=null;
				empInvHra = (Object[][])bulkForm16.getInvstmentHraDataMap().get(empLength[i]);
				exemptData=new Object[exemptInvData.length+1][4];
				exemptData[0][0]="2. Less:Allowance to the exempt under section 10";
				exemptData[0][1]="";
				exemptData[0][2]="";
				exemptData[0][3]="";
				
				if(exemptData == null){
					
				} //end of if
				else if(!(exemptData.length >0)){
				
				} //end of else if
				else{
					try {
						for (int j = 0; j < exemptInvData.length; j++) {
							exemptData[j + 1][0] = "" + exemptInvData[j][2] + "";
							
							if (empInvHra != null
									&& empInvHra.length > 0&& String.valueOf(exemptInvData[j][1]).trim()
											.equals(String.valueOf(empInvHra[0][1]).trim())) {
								exemptData[j + 1][1] = "" + empInvHra[0][2] + "";
							} //end of if 
							else {
								if (String.valueOf(exemptInvData[j][4]).trim()
										.equals("")|| String.valueOf(exemptInvData[j][4]).trim().equals("null")) {
									exemptData[j + 1][1] = "0";
								} //end of if
								else {
									exemptData[j + 1][1] = "" + exemptInvData[j][4]+ "";
								} //end of else
							} //end of else
							exemptData[j + 1][2] = "";
							exemptData[j + 1][3] = "";
							sec10Amt = sec10Amt	+ Double.parseDouble(String.valueOf(exemptData[j + 1][1]));
						} //end of loop
					} catch (Exception e) {
						logger.error("error in calculating exemptData   "+e);
					} //end of catch
				} //end of else
			}

			int [] cellWidthExempt={50,15,15,20};
			int [] cellAlignExempt={0,2,2,2};
			TableDataSet tableExemptData = new TableDataSet();
			tableExemptData.setData(exemptData);
			tableExemptData.setCellWidth(cellWidthExempt);
			tableExemptData.setCellAlignment(cellAlignExempt);
			tableExemptData.setBorderDetail(3);
			rg.addTableToDoc(tableExemptData);
			
			//=================Balance===========================//
			Object[][] invDeducAmt=null;
			invDeducAmt = (Object[][])bulkForm16.getInvestmentDeducDataMap().get(empLength[i]);
			
			double deductionTotal=0;
			Object taxTotal2[][] = new Object[5][4];
			/*if(invDeducData!=null && invDeducData.length>0){
				try {
					taxTotal2 = new Object[invDeducData.length + 4 + 8 + 3][5];
				} catch (Exception e) {
					logger.error("exception in creating taxTotal2 object",e);
				} //end of catch
			} //end of if
			else{
				taxTotal2= new Object[3 + 8 + 3][5];
			} //end of else
*/			taxTotal2[0][0]="3. Balance (1-2)";
			taxTotal2[0][1]="";
			taxTotal2[0][2]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal[0][2])) - sec10Amt));
			taxTotal2[0][3]="";
			
			taxTotal2[1][0]="4. Deductions:";
			taxTotal2[1][1]="";
			taxTotal2[1][2]="";
			taxTotal2[1][3]="";
			
			taxTotal2[2][0]="     a)Entertainment allowance";
			taxTotal2[2][1]="NIL";
			taxTotal2[2][2]="";
			taxTotal2[2][3]="";
			
			taxTotal2[3][0]="     b)Tax on Employment";
			taxTotal2[3][1]=""+taxData[0][13];
			taxTotal2[3][2]="";
			taxTotal2[3][3]="";
			 
			taxTotal2[4][0]="5. Aggregate of 4(a) and 4(b)";
			taxTotal2[4][1]="";
			taxTotal2[4][2]=""+taxData[0][13]+"";
			taxTotal2[4][3]="";
			
			Object [][]taxTotal4=new Object [3][4];
			taxTotal4[0][0]="6. INCOME CHARGEABLE UNDER THE HEAD 'SALARIES' (3-5)";
			taxTotal4[0][1]="";
			taxTotal4[0][2]="";
			taxTotal4[0][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal2[0][2])) - Double.parseDouble(String.valueOf(taxTotal2[4][2]))));
			
			taxTotal4[1][0]=add;
			taxTotal4[1][1]=amt;
			taxTotal4[1][2]="";
			taxTotal4[1][3]=(total + formatter.format(totalOtherAmt));
			
			taxTotal4[2][0]="8. GROSS TOTAL INCOME (6+7)";
			taxTotal4[2][1]="";
			taxTotal4[2][2]="";
			taxTotal4[2][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal4[0][3])) + totalOtherAmt));
			
			Object [][]taxTotal5=null;
			if(invDeducData!=null && invDeducData.length>0){
			try {
				taxTotal5 = new Object[invDeducData.length + 3+3][4];
			} catch (Exception e) {
				logger.error("exception in creating taxTotal2 object",e);
			} //end of catch
		} //end of if
		else{
			taxTotal5= new Object[3+4][4];
		} //end of else
			taxTotal5[0][0]="9. DEDUCTIONS UNDER CHAPTER VI-A";
			taxTotal5[0][1]="";
			taxTotal5[0][2]="";
			taxTotal5[0][3]="";
			//taxTotal5[0][4]="";
			
			taxTotal5[1][0]="A) 80C, 80CCC AND 80CCD";
			taxTotal5[1][1]="GROSS AMOUNT";
			taxTotal5[1][2]="QUALIFYING AMOUNT";
			taxTotal5[1][3]="DEDUCTIBLE AMOUNT";
			//taxTotal5[1][4]="";
			
			taxTotal5[2][0]="a) Section 80C";
			taxTotal5[2][1]="(Rs.)";
			taxTotal5[2][2]="(Rs.)";
			taxTotal5[2][3]="(Rs.)";
			//taxTotal5[2][4]="";
			
			if(invDeducData == null){
				
			} //end of if
			else if(invDeducData.length == 0){
				
			} //end of else if
			else{
				try {
					for (int j = 0; j < invDeducData.length; j++) {

						taxTotal5[j + 3][0] = "   " + invDeducData[j][2]
								+ "." + invDeducData[j][1] + "";
						if (invDeducAmt == null) {

						} //end of if 
						else if (invDeducAmt.length == 0) {

						} //end of else if 
						else {
							try{
								for (int k = 0; k < invDeducAmt.length; k++) {
									if (String.valueOf(invDeducData[j][0])
											.trim().equals(String.valueOf(invDeducAmt[k][1]).trim())) {
										taxTotal5[j + 3][1] = Math.round(Double
														.parseDouble(String.valueOf(invDeducAmt[k][2])));
										
									} //end of if
								} //end of loop
							} catch (Exception e) {
								logger.error("Exception in invDeducData--"+e);
							} //end of catch
						} //end of else

						taxTotal5[j + 3][2] = "";
						taxTotal5[j + 3][3] = "";
						//taxTotal5[j + 3][4] = "";
						if (String.valueOf(taxTotal5[j + 3][1]).equals("")
								|| String.valueOf(taxTotal5[j + 3][1]).equals("null")) {taxTotal5[j + 3][1] = 0;
						} //end of if
						deductionTotal = Math.round(deductionTotal
								+ Double.parseDouble(String.valueOf(taxTotal5[j + 3][1])));
					} //end of loop
				} catch (Exception e) {
					logger.error("Exception in invDeducData--"+e);
				} //end of catch
			} //end of else
			
			/**
			 * calculation for CCC & CCCD sections
			 */
			double cccAmt=0,ccdAmt=0,ccfAmt=0;
			try{
				if(cccSection == null){
					
				} //end of if
				else if(cccSection.length == 0){
					
				} //end of else if
				else{
					for (int j = 0; j < cccSection.length; j++) {
						 if(cccSection[j][2].equals("80CCC")){
							 if(invDeducAmt == null){
									
								} //end of if
								else if(invDeducAmt.length == 0){
									
								} //end of else if
								else{
									try{
										for (int j2 = 0; j2 < invDeducAmt.length; j2++) {
											// compare invCode
											if(String.valueOf(cccSection[j][0]).trim().equals(String.valueOf(invDeducAmt[j2][1]).trim())){
												 cccAmt += Math.round(Double.parseDouble(String.valueOf(invDeducAmt[j2][2]))); 
											 } //end of if
										} //end of loop
									} catch (Exception e) {
										logger.error("execption in invDeducAmt loop",e);
									} //end of catch
								} //end of else
						 } //end of if
						 
						 if(cccSection[j][2].equals("80CCD")){
							 if(invDeducAmt == null){
									
								} //end of if 
								else if(invDeducAmt.length == 0){
									
								} //end of else if
								else{
									try{
										for (int j2 = 0; j2 < invDeducAmt.length; j2++) {
											 // compare invCode
											if(String.valueOf(cccSection[j][0]).trim().equals(String.valueOf(invDeducAmt[j2][1]).trim())){
												 ccdAmt += Math.round(Double.parseDouble(String.valueOf(invDeducAmt[j2][2]))); 
											 } //end of if
										} //end of loop
									} catch (Exception e) {
										logger.error("execption in invDeducAmt loop",e);
									} //end of catch
								} //end of else
						 } //end of if
						 
						 /**
						  * 80CCF removed by mangesh 17/11/2011
						  */
						/* if(cccSection[j][2].equals("80CCF")){
							 if(invDeducAmt == null){
									
								} //end of if
								else if(invDeducAmt.length == 0){
									
								} //end of else if
								else{
									try{
										for (int j2 = 0; j2 < invDeducAmt.length; j2++) {
											 if(String.valueOf(cccSection[j][0]).trim().equals(String.valueOf(invDeducAmt[j2][1]).trim())){
												 ccfAmt += Math.round(Double.parseDouble(String.valueOf(invDeducAmt[j2][2]))); 
											 } //end of if
										} //end of loop
									} catch (Exception e) {
										logger.error("execption in invDeducAmt loop",e);
									} //end of catch
								} //end of else
						 } //end of if
*/					} //end of loop
				} //end of else
			} catch (Exception e) {
				logger.error("execption in calculating the sum of ccc & ccd amt----"+e);
			} //end of catch
			deductionTotal = deductionTotal + cccAmt ;
			try {
				if (taxParameter != null && taxParameter.length > 0) {
					if (deductionTotal > Double.parseDouble(String
							.valueOf(taxParameter[0][2]))) {
						deductionTotal = Double.parseDouble(String
								.valueOf(taxParameter[0][2]));
					} //end of if
				} //end of if
			} catch (Exception e) {
				// TODO: handle exception
			}
			//deductionTotal+=ccfAmt;
			int invDeducDataLength=0;
			if(invDeducData != null && invDeducData.length>0){
				invDeducDataLength=invDeducData.length;
			}
			
			taxTotal5[invDeducDataLength + 3][0]="b) Section 80CCC";
			taxTotal5[invDeducDataLength + 3][1]=""+Math.round(cccAmt)+"";
			taxTotal5[invDeducDataLength + 3][2]=""+Math.round(cccAmt)+"";
			taxTotal5[invDeducDataLength + 3][3]=""+Math.round(cccAmt)+"";
			//taxTotal5[invDeducDataLength + 3][4]="";
			
			taxTotal5[invDeducDataLength + 3 + 1][0]="c) Section 80CCD";
			taxTotal5[invDeducDataLength + 3 + 1][1]=""+Math.round(ccdAmt)+"";
			taxTotal5[invDeducDataLength + 3 + 1][2]=""+Math.round(ccdAmt)+"";
			taxTotal5[invDeducDataLength + 3 + 1][3]=""+Math.round(ccdAmt)+"";
			//taxTotal5[invDeducDataLength + 3 + 1][4]="";
			
			/*taxTotal5[invDeducDataLength + 3 + 2][0]="d) Section 80CCF";
			taxTotal5[invDeducDataLength + 3 + 2][1]=""+Math.round(ccfAmt)+"";
			taxTotal5[invDeducDataLength + 3 + 2][2]=""+Math.round(ccfAmt)+"";
			taxTotal5[invDeducDataLength + 3 + 2][3]=""+Math.round(ccfAmt)+"";
			//taxTotal5[invDeducDataLength + 3 + 2][4]="";
*/			
			taxTotal5[invDeducDataLength + 3 + 2][0]="Total [a + b + c]";
			taxTotal5[invDeducDataLength + 3 + 2][1]=""+Math.round(deductionTotal)+"";
			taxTotal5[invDeducDataLength + 3 + 2][2]=""+Math.round(deductionTotal)+"";
			taxTotal5[invDeducDataLength + 3 + 2][3]=""+Math.round(deductionTotal)+"";
			//taxTotal5[invDeducDataLength + 3 + 3][4]="";
			
			int [] cellAlign2={0,2,2,2};
			
			TableDataSet tabletaxTotal2 = new TableDataSet();
			tabletaxTotal2.setData(taxTotal2);
			tabletaxTotal2.setCellWidth(cellWidthtaxTotal);
			tabletaxTotal2.setCellAlignment(cellAlign2);
			tabletaxTotal2.setBorderDetail(3);
			rg.addTableToDoc(tabletaxTotal2);
			
			TableDataSet tabletaxTotal4 = new TableDataSet();
			tabletaxTotal4.setData(taxTotal4);
			tabletaxTotal4.setCellWidth(cellWidthtaxTotal);
			tabletaxTotal4.setCellAlignment(cellAlign2);
			tabletaxTotal4.setBorderDetail(3);
			rg.addTableToDoc(tabletaxTotal4);
			
			rg.addProperty(rg.PAGE_BREAK);
			Object[][]headingDataCert1= new Object[1][2];
			if(empCertDetailsMap == null){
				//headingDataCert1[0][1] = "Page No: 2 of 2";
				headingDataCert1[0][1] = "Page No: 2 of 3";
				headingDataCert1[0][0] = "";
			} //end of if
			else if(!(empCertDetailsMap.length >0)){
				//headingDataCert1[0][1] = "Page No: 2 of 2";
				headingDataCert1[0][1] = "Page No: 2 of 3";
				headingDataCert1[0][0] = "";
			} //end of else if
			else{
				if(String.valueOf(empCertDetailsMap[0][1]).equals("")
						||String.valueOf(empCertDetailsMap[0][1]).equals("null")
						||String.valueOf(empCertDetailsMap[0][1]).equals(null)){
				//	headingDataCert1[0][1] = "Page No: 2 of 2";
						headingDataCert1[0][1] = "Page No: 2 of 3";
					headingDataCert1[0][0] = "";
				} //end of if
				else{
				//	headingDataCert1[0][1] = "Page No: 2 of 2";
						headingDataCert1[0][1] = "Page No: 2 of 3";
					headingDataCert1[0][0] = "Certificate No: "+empCertDetailsMap[0][1];
				} //end of else
			} //end of else
			TableDataSet tableheadingDataCert1 = new TableDataSet();
			tableheadingDataCert1.setData(headingDataCert1);
			tableheadingDataCert1.setCellWidth(cellWidthHeaderCert);
			tableheadingDataCert1.setCellAlignment(cellAlignHeaderCert);
			tableheadingDataCert1.setBorder(false);
			tableheadingDataCert1.setBlankRowsBelow(1);
			rg.addTableToDoc(tableheadingDataCert1);
			TableDataSet tabletaxTotal5 = new TableDataSet();
			tabletaxTotal5.setData(taxTotal5);
			tabletaxTotal5.setCellWidth(cellWidthtaxTotal);
			tabletaxTotal5.setCellAlignment(cellAlign2);
			tabletaxTotal5.setBorderDetail(3);
			rg.addTableToDoc(tabletaxTotal5);
			
			//=================Note table=====================//
			Object noteDetails[][]= new Object[1][4];//new-------------->
		 	noteDetails[0][0] ="Note: 1.aggregate amount deductible under section 80C shall not exceed one lakh rupees. \n         2.aggregate amount deductible under sections, i.e., 80C,80CCC and 80CCD,shall not exceed one lakh rupees";
		 	int [] cellWidthNoteDtls={100};
			int [] cellAlignNoteDtls={0};
			
			
			
			//rg.addProperty(rg.PAGE_BREAK);
		
			//==============taxTotal Other===================//
			Object[][] otherSec = null;
			otherSec = (Object[][])bulkForm16.getOtherSecDataMap().get(empLength[i]);
			double invAmt80D=0,invAmt80E=0,invAmt80G=0,invAmt80DD=0,invAmt80U=0,invAmt80CCF=0;
			/*if((otherSec.length> 0)){
				invAmt80D=0;
				invAmt80E=0;
				invAmt80G=0;
			} //end of if
*/			
			// calculation for sections other than 80CCC & 80CCD
			if(otherSec == null){
				
			} //end of if
			else if(otherSec.length == 0){
				
			} //end of else if
			else{
				try{
					for (int j = 0; j < otherSec.length; j++) {
						 if(String.valueOf(otherSec[j][2]).equals("80D")){
							 invAmt80D += Math.round(Double.parseDouble(String.valueOf(otherSec[j][1])));
						 } //end of if
						 else if(String.valueOf(otherSec[j][2]).equals("80E")){
							 invAmt80E += Math.round(Double.parseDouble(String.valueOf(otherSec[j][1])));
						 } //end of else if
						 else if(String.valueOf(otherSec[j][2]).equals("80G")){
							 invAmt80G += Math.round(Double.parseDouble(String.valueOf(otherSec[j][1])));
						 } //end of else if
						 else if(String.valueOf(otherSec[j][2]).equals("80DD")){
							 invAmt80DD += Math.round(Double.parseDouble(String.valueOf(otherSec[j][1])));
						 } //end of else if
						 else if(String.valueOf(otherSec[j][2]).equals("80U")){
							 invAmt80U += Math.round(Double.parseDouble(String.valueOf(otherSec[j][1])));
						 } //end of else if
						 else if(String.valueOf(otherSec[j][2]).equals("80CCF")){
							 invAmt80CCF += Math.round(Double.parseDouble(String.valueOf(otherSec[j][1])));
						 } //end of else if
						
					} //end of loop
				} catch (Exception e) {
					logger.error("exception in otherSec loop",e);
				} //end of catch for taxData condition..
			} //end of else
			
			Object taxTotalOther[][]= new Object[1][4];//new-------------->
			taxTotalOther[0][0] ="B) Other Sections (for e.g.     80E,80G etc)" +
								 "    \n" +
								 "    a) Section 80CCF \n" +
								 "    b) Section 80D \n" +
								 "    c) Section 80DD \n" +
								 "    d) Section 80G \n" +
								 "    e) Section 80E \n" +
								 "    f) Section 80U";
			taxTotalOther[0][1] ="";
			taxTotalOther[0][2] ="\n"+Math.round(invAmt80CCF)+"\n"+Math.round(invAmt80D)+"\n"+Math.round(invAmt80DD)+"\n"+Math.round(invAmt80G)+"\n"+Math.round(invAmt80E)+"\n"+Math.round(invAmt80U)+"";
			taxTotalOther[0][3] ="\n"+Math.round(invAmt80CCF)+"\n"+Math.round(invAmt80D)+"\n"+Math.round(invAmt80DD)+"\n"+Math.round(invAmt80G)+"\n"+Math.round(invAmt80E)+"\n"+Math.round(invAmt80U)+"";
			//taxTotalOther[0][4] ="";
			int [] cellAlignOther={0,2,2,2};
			TableDataSet tabletaxNote = new TableDataSet();
			tabletaxNote.setData(noteDetails);
			tabletaxNote.setCellWidth(cellWidthtaxTotal);
			tabletaxNote.setCellAlignment(cellAlignOther);
			tabletaxNote.setBorderDetail(3);
			rg.addTableToDoc(tabletaxNote);
			TableDataSet tabletaxTotalOther = new TableDataSet();
			tabletaxTotalOther.setData(taxTotalOther);
			tabletaxTotalOther.setCellWidth(cellWidthtaxTotal);
			tabletaxTotalOther.setCellAlignment(cellAlignOther);
			tabletaxTotalOther.setBorderDetail(3);
			rg.addTableToDoc(tabletaxTotalOther);
			
			//=============Challan Queries===========================//
			 Object[][] challanDataMarDec = null;
			 challanDataMarDec = (Object[][])bulkForm16.getChallanDataAprToDecMap().get(empLength[i]);
			 Object[][] challanDataJanMar = null;
			 challanDataJanMar = (Object[][])bulkForm16.getChallanDataJanToMarMap().get(empLength[i]);
			 double finalTotTaxAmt=0,finalTotTaxAmt1=0,finalAmt=0;
			 int rowCount=0;
			 
			 if(challanDataMarDec!=null && challanDataMarDec.length >0){
					try {
						for (int j = 0; j < challanDataMarDec.length; j++) {
							finalTotTaxAmt += Double.parseDouble(String.valueOf(challanDataMarDec[j][1]));
							rowCount++;
							challanDataMarDec[j][0] = rowCount;
						} //end of loop
					} catch (Exception e) {
						logger.error("exception in challanDataMarDec loop",e);
					} //end of catch
			} //end of if
			 
			 if(challanDataJanMar!=null && challanDataJanMar.length >0){
					try {
						for (int j = 0; j < challanDataJanMar.length; j++) {
							finalTotTaxAmt1 += Double.parseDouble(String.valueOf(challanDataJanMar[j][1]));
							challanDataJanMar[j][0] = rowCount + j + 1;
						} //end of loop
					} catch (Exception e) {
						logger.error("exception in challanDataJanMar loop",e);
					} //end of catch
			} //end of if 
			finalAmt = finalTotTaxAmt + finalTotTaxAmt1;
				
			//=============aggregate table details====================//
			Object taxTotal3[][]= new Object[7][4];//new------------->
			taxTotal3[0][0]="10.Aggregate of deductible amount under Chapter VI-A";
			taxTotal3[0][1]="";
			taxTotal3[0][2]="";
			taxTotal3[0][3]=""+(Math.round(deductionTotal) + Math.round(invAmt80D) + Math.round(invAmt80E) + Math.round(invAmt80CCF) + Math.round(invAmt80DD)+ Math.round(invAmt80U)+
					Math.round(invAmt80G))+"";
			//logger.info("String.valueOf(taxTotal1[1][3])===="+String.valueOf(taxTotal2[7][3]));
			//logger.info("String.valueOf(taxTotal3[0][3])===="+String.valueOf(taxTotal3[0][3]));
			taxTotal3[1][0]="11.TOTAL INCOME (R/o)(8-10)";
			taxTotal3[1][1]="";
			taxTotal3[1][2]="";
			
			double z = Math.round(Double.parseDouble(String.valueOf(taxTotal4[2][3])) - Double.parseDouble(String.valueOf(taxTotal3[0][3])));
			double mod=Math.round(Double.parseDouble(String.valueOf(taxTotal4[2][3])) - Double.parseDouble(String.valueOf(taxTotal3[0][3])))%10;
			
			if(mod >0)
			{
				z=(z-mod)+10;
			} //end of if
			
			if(z<0)
			{
				taxTotal3[1][3]=0;
			} //end of if
			else{
				taxTotal3[1][3]=String.valueOf(Math.round(z));
			} //end of else
			
			taxTotal3[2][0]="12.TAX ON TOTAL INCOME(Net)";
			taxTotal3[2][1]="";
			taxTotal3[2][2]="";
			taxTotal3[2][3]=""+taxData[0][7]+"";
			
			
			taxTotal3[3][0]="13.Education Cess(on tax computed at S.No.12)";
			taxTotal3[3][1]="";
			taxTotal3[3][2]="";
			taxTotal3[3][3]=""+taxData[0][8]+"";
			
			taxTotal3[4][0]="14.TAX PAYABLE (12+13)";
			taxTotal3[4][1]="";
			taxTotal3[4][2]="";
			taxTotal3[4][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal3[2][3])) + Double.parseDouble(String.valueOf(taxTotal3[3][3]))));
			
			taxTotal3[5][0]="15.Less: Relief Under section 89(attach details)";
			taxTotal3[5][1]="";
			taxTotal3[5][2]="";
			taxTotal3[5][3]="NIL";

			taxTotal3[6][0]="16.TAX PAYABLE(14-15)";
			taxTotal3[6][1]="";
			taxTotal3[6][2]="";
			taxTotal3[6][3]=""+String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal3[4][3]))))+"";
			
			/*taxTotal3[8][0]="18.LESS: (a)TAX DEDUCTED AT SOURCE U/S 192(1).\n" +
			"(b) Tax Paid By The Employer On Behalf Of The Employee U/S 192(A) On Perquisites U/S 17(2).";
			taxTotal3[8][1]="";
			taxTotal3[8][2]=""+String.valueOf(Math.round(finalAmt))+"\n\nNIL";
			taxTotal3[8][3]="\n\n\n"+String.valueOf(Math.round(finalAmt))+"";
			     
			taxTotal3[9][0]="19. TAX PAYABLE/REFUNDABLE (17-18)";
			taxTotal3[9][1]="";
			taxTotal3[9][2]="";
			taxTotal3[9][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal3[7][3])) - Double.parseDouble(String.valueOf(taxTotal3[8][3]))));
*/
		    int [] cellWidth3={50,15,15,20};
			int [] cellAlign3={0,2,2,2};
			
			TableDataSet tabletaxTotal3 = new TableDataSet();
			tabletaxTotal3.setData(taxTotal3);
			tabletaxTotal3.setCellWidth(cellWidth3);
			tabletaxTotal3.setCellAlignment(cellAlign3);
			tabletaxTotal3.setBorderDetail(3);
			rg.addTableToDoc(tabletaxTotal3);
			//===========i here by data================//
			 String abc = String.valueOf(formatter.format(finalAmt));
				String []xyz = abc.replace('.', '#').split("#");
				
				int zz=0;
				try {
					for (int j = 0; j < xyz.length; j++) {
					} //end of loop
					zz = Integer.parseInt(xyz[0]);
				} catch (Exception e) {
					logger.error("exception in xyz loop",e);
				} //end of catch
				Object[][]iHerebyHeading = new Object[3][1];
				Object[][]veryHeading = new Object[1][1];
				veryHeading[0][0]="Verification";
				TableDataSet veryHeadingData = new TableDataSet();
				veryHeadingData.setData(veryHeading);
				veryHeadingData.setCellWidth(new int []{100});
				veryHeadingData.setCellAlignment(new int[]{1});
				veryHeadingData.setBorder(false);
				veryHeadingData.setBodyFontStyle(1);
				//veryHeadingData.setBlankRowsAbove(1);
				rg.addTableToDoc(veryHeadingData);
				Object[][]iHerebyData = new Object[1][1];
				iHerebyData[0][0] = "I "+bulkForm16.getSignAuthName()+" son/daughter of "+bulkForm16.getSignAuthEmpFather()+" working in the capacity of" +
						" "+bulkForm16.getSignAuthEmpDesg()+"(designation) do hereby certify that a sum of Rs."+formatter.format(finalAmt)+"/- [Rupees "+new Utility().convert(zz)+" (in words)]" +
						" only has been deducted at source and paid to the credit of the Central Government. I " +
						" further certify that the information given above is true and correct based on the" +
						" book of accounts, documents and other available records.";
				/*iHerebyData[1][0] ="";
				iHerebyData[2][0] ="";*/
				int [] cellWidthIHereBy={100};
				int [] cellAlignIHereBy={0};
				TableDataSet tableiHerebyData = new TableDataSet();
				tableiHerebyData.setData(iHerebyData);
				tableiHerebyData.setCellWidth(cellWidthIHereBy);
				tableiHerebyData.setCellAlignment(cellAlignIHereBy);
				tableiHerebyData.setBorderDetail(3);
				tableiHerebyData.setBlankRowsBelow(1);
				rg.addTableToDoc(tableiHerebyData);
				
				Object[][]placeDateData = new Object[2][3];
				placeDateData[0][0] = "Place : ";
				placeDateData[0][1] = ""+empDetailsMap[0][5];
				placeDateData[0][2] = "";
				placeDateData[1][0] = "Date : ";
				placeDateData[1][1] = ""+bulkForm16.getSignDate();
				placeDateData[1][2] = "Signature of the person responsible for deduction of tax";
				int [] cellWidthPlaceDate={15,25,60};
				int [] cellAlignPlaceDate={0,0,0};
				TableDataSet tableplaceDateData = new TableDataSet();
				tableplaceDateData.setData(placeDateData);
				tableplaceDateData.setCellWidth(cellWidthPlaceDate);
				tableplaceDateData.setCellAlignment(cellAlignPlaceDate);
				tableplaceDateData.setBorderDetail(3);
				//tableplaceDateData.setBlankRowsBelow(1);
				rg.addTableToDoc(tableplaceDateData);
				
				Object[][]signData = new Object[1][2];
				signData[0][0] = "Designation : "+bulkForm16.getSignAuthEmpDesg();
				signData[0][1] = "Full Name : "+bulkForm16.getSignAuthName();
				int [] cellWidthSignData={40,60};
				int [] cellAlignSignData={0,0};
				TableDataSet tablesignData = new TableDataSet();
				tablesignData.setData(signData);
				tablesignData.setCellWidth(cellWidthSignData);
				tablesignData.setCellAlignment(cellAlignSignData);
				tablesignData.setBorderDetail(3);
				tablesignData.setBlankRowsBelow(3);
				rg.addTableToDoc(tablesignData);
				
				Object [][]finalNoteObj=new Object[6][1];
				finalNoteObj[0][0]="Notes :";
				finalNoteObj[1][0]="1. If an assessee is employed under more than one employer during the year, each of the employers shall  issue  Part  A "
						+" of  the  certificate  in  Form No. 16 pertaining to the period for  which  such  assessee  was  employed  with  each  of  the "
						+" employers. Part B may be issued by each of the employers or the last employer at the option of the assessee.";
				finalNoteObj[2][0]="2. Government deductors to enclose Annexure-A if tax is paid without production of an "
						+" income-tax challan and Annexure- B if tax is paid    accompanied by an income-tax challan. ";
				finalNoteObj[3][0]="3. Non-Government deductors to enclose Annexure-B.";
				finalNoteObj[4][0]="4. The  deductor  shall  furnish  the  address  of  the  Commissioner  of  Income-tax  (TDS) "
					+" having jurisdiction as regards TDS statements of the assessee. ";
				finalNoteObj[5][0]="5. This Form shall be applicable only in respect of tax deducted on or after 1st day of April, "+bulkForm16.getFrmYear();
				
				TableDataSet tableFinalNote = new TableDataSet();
				tableFinalNote.setData(finalNoteObj);
				tableFinalNote.setCellWidth(cellWidthNoteDtls);
				tableFinalNote.setCellAlignment(cellAlignNoteDtls);
				tableFinalNote.setBorder(false);
				rg.addTableToDoc(tableFinalNote);
				
				/*Object[][]signData = new Object[2][1];
				signData[0][0] = "____________________________________________";
				signData[1][0] = "Signature of the person responsible for deduction of tax\n Full Name : "+bulkForm16.getSignAuthName()+"\n  Designation : "+bulkForm16.getSignAuthEmpDesg()+"";
				int [] cellWidthSignData={100};
				int [] cellAlignSignData={2};
				TableDataSet tablesignData = new TableDataSet();
				tablesignData.setData(signData);
				tablesignData.setCellWidth(cellWidthSignData);
				tablesignData.setCellAlignment(cellAlignSignData);
				tablesignData.setBorder(false);
				rg.addTableToDoc(tablesignData);*/
			rg.addProperty(rg.PAGE_BREAK);
			//=============Details of tax deducted====================//
			Object[][]headingDataCert2= new Object[1][2];
			if(empCertDetailsMap == null){
				//headingDataCert1[0][1] = "Page No: 2 of 2";
				headingDataCert2[0][1] = "Page 3 of 3";
				headingDataCert2[0][0] = "";
			} //end of if
			else if(!(empCertDetailsMap.length >0)){
				//headingDataCert1[0][1] = "Page No: 2 of 2";
				headingDataCert2[0][1] = "Page 3 of 3";
				headingDataCert2[0][0] = "";
			} //end of else if
			else{
				if(String.valueOf(empCertDetailsMap[0][1]).equals("")
						||String.valueOf(empCertDetailsMap[0][1]).equals("null")
						||String.valueOf(empCertDetailsMap[0][1]).equals(null)){
					headingDataCert2[0][1] = "Page 3 of 3";
					headingDataCert2[0][0] = "";
				} //end of if
				else{
					headingDataCert2[0][1] = "Page 3 of 3";
					headingDataCert2[0][0] = "Certificate No: "+empCertDetailsMap[0][1];
				} //end of else
			} //end of else
			TableDataSet tableheadingDataCert2 = new TableDataSet();
			tableheadingDataCert2.setData(headingDataCert2);
			tableheadingDataCert2.setCellWidth(cellWidthHeaderCert);
			tableheadingDataCert2.setCellAlignment(cellAlignHeaderCert);
			tableheadingDataCert2.setBorder(false);
			//tableheadingDataCert2.setBlankRowsBelow(1);
			rg.addTableToDoc(tableheadingDataCert2);
			Object[][]headingAnnexure = new Object[1][1];
			headingAnnexure[0][0] = "ANNEXURE B";
			TableDataSet tableAnnexure = new TableDataSet();
			tableAnnexure.setData(headingAnnexure);
			tableAnnexure.setCellWidth(cellWidthheadingForm16);
			tableAnnexure.setCellAlignment(cellAlignheadingForm16);
			tableAnnexure.setBorder(false);
			tableAnnexure.setBodyFontStyle(1);
			rg.addTableToDoc(tableAnnexure);
			
			Object detailsOfTaxDeducted[][]= new Object[2][1];//new-------------->
		 	detailsOfTaxDeducted[0][0] ="DETAILS OF TAX DEDUCTED AND DEPOSITED INTO CENTRAL GOVERNMENT ACCOUNT THROUGH CHALLAN";
		 	detailsOfTaxDeducted[1][0] ="(The Employer is to provide transaction-wise details of tax deducted and deposited)";
		 	int [] cellDetailsTaxDeducted={100};
			int [] cellAlignCellDetailsTaxDeducted={1};
			
			TableDataSet tableTaxDeducted = new TableDataSet();
			tableTaxDeducted.setData(detailsOfTaxDeducted);
			tableTaxDeducted.setCellWidth(cellDetailsTaxDeducted);
			tableTaxDeducted.setCellAlignment(cellAlignCellDetailsTaxDeducted);
			tableTaxDeducted.setBorder(false);
			rg.addTableToDoc(tableTaxDeducted);
			
			//=============tax challan header===================//
			Object[][] taxChallan= new Object[1][6];//new------------->
		 	taxChallan[0][0]="Sr No";
		 	/*taxChallan[0][1]="TDS (Rs.)";
			taxChallan[0][2]="Surcharge (Rs.)";
			taxChallan[0][3]="Education Cess (Rs.)";*/
			taxChallan[0][1]="Total Tax Deposited (Rs.)";
			taxChallan[0][2]="Cheque/DD No.";
			taxChallan[0][3]="BSR Code of Bank branch";
			taxChallan[0][4]="Date on Which Tax deposited";
			taxChallan[0][5]="Challan Identification No";
			int [] cellWidthChallan={8,15,15,20,20,20};
			int [] cellAlignChallan={1,1,1,1,1,1};
			 
			TableDataSet tabletaxChallan = new TableDataSet();
			tabletaxChallan.setData(taxChallan);
			tabletaxChallan.setCellWidth(cellWidthChallan);
			tabletaxChallan.setCellAlignment(cellAlignChallan);
			tabletaxChallan.setBorderDetail(3);
			rg.addTableToDoc(tabletaxChallan);
			
			//=====================tax challan details==================//
			if(challanDataMarDec!=null && challanDataMarDec.length >0){
				int [] cellWidthAprToDec={8,15,15,20,20,20};
				int [] cellAlignAprToDec={2,2,1,2,2,2};
				TableDataSet tablechallanDataMarDec = new TableDataSet();
				tablechallanDataMarDec.setData(challanDataMarDec);
				tablechallanDataMarDec.setCellWidth(cellWidthAprToDec);
				tablechallanDataMarDec.setCellAlignment(cellAlignAprToDec);
				tablechallanDataMarDec.setBorderDetail(3);
				//PdfPTable pTablechallanDataMarDec = rg.createTable(tablechallanDataMarDec);
				//rg.addPdfPTableToDoc(pTablechallanDataMarDec);
				rg.addTableToDoc(tablechallanDataMarDec);
			} //end of challanDataMarDec method	
			if(challanDataJanMar!=null && challanDataJanMar.length >0){
				int [] cellWidthJanToMar={8,15,15,20,20,20};
				int [] cellAlignJanToMar={2,2,1,2,2,2};
				TableDataSet tablechallanDataJanMar = new TableDataSet();
				tablechallanDataJanMar.setData(challanDataJanMar);
				tablechallanDataJanMar.setCellWidth(cellWidthJanToMar);
				tablechallanDataJanMar.setCellAlignment(cellAlignJanToMar);
				tablechallanDataJanMar.setBorderDetail(3);
				//PdfPTable pTablechallanDataMarDec = rg.createTable(tablechallanDataJanMar);
				//rg.addPdfPTableToDoc(pTablechallanDataMarDec);
				rg.addTableToDoc(tablechallanDataJanMar);
			} //end of challanDataJanMar method	 
			
			//================tax challan total====================//
			 Object[][] taxChallanTotal= new Object[1][6];
			/* taxChallanTotal[0][0]="";
			 taxChallanTotal[0][1]="";
			 taxChallanTotal[0][2]="";*/
			 taxChallanTotal[0][0]="TOTAL";
			 taxChallanTotal[0][1]=""+Math.round(finalAmt)+"";
			 taxChallanTotal[0][2]="";
			 taxChallanTotal[0][3]="";
			 taxChallanTotal[0][4]="";
			 taxChallanTotal[0][5]="";
			 
			int [] cellWidthTotal={8,15,15,20,20,20};
			int [] cellAlignTotal={2,2,2,2,2,2};
			TableDataSet tabletaxChallanTotal = new TableDataSet();
			tabletaxChallanTotal.setData(taxChallanTotal);
			tabletaxChallanTotal.setCellWidth(cellWidthTotal);
			tabletaxChallanTotal.setCellAlignment(cellAlignTotal);
			tabletaxChallanTotal.setBorderDetail(3);
			rg.addTableToDoc(tabletaxChallanTotal);
			
			
			//======================End of employee details=====================================//
			} //end of else
			rg.addProperty(rg.PAGE_BREAK);
			/*This block refers to Form12BAModel class to add the From12BA report data*/
			
			if (bulkForm16.getForm12Flag().equalsIgnoreCase("true")) {
				try {
					String[] empName = String.valueOf(empDetailsMap[0][1])
							.split("-");
					String[] data = new String[] {
							String.valueOf(empDetailsMap[0][0]),
							String.valueOf(empName[0]),
							String.valueOf(bulkForm16.getFrmYear()),
							String.valueOf(bulkForm16.getToYear()),
							String.valueOf(salDesigMap[0][1]),
							bulkForm16.getDivisionId(),
							String.valueOf(empDetailsMap[0][9]) };
					Form12BAModel form12Model = new Form12BAModel();
					form12Model.initiate(context, session);
					rg = form12Model.getReport(rg, data);
				} catch (Exception e) {
					// TODO: handle exception
				}
				/* END of Form12BAModel block */
				rg.addProperty(rg.PAGE_BREAK);
			}
		} //end of main loop
		}
			rg.process();
			rg.createReport(response);
	} //end of createReport method
/**
 * This method will set all the employee information required to generate the Form 16
 * @param empIdList
 * @param employeeBean
 * @return
 */
	private Form16MisReport loadEmployeeInformation(String empIdList,Form16MisReport employeeBean) {
		//Form16MisReport employeeBean = new Form16MisReport();
		employeeBean.setEmpOfficialDataMap(getEmpOffcialData(empIdList,employeeBean));
		employeeBean.setEmpCertMap(getEmpCertData(empIdList,employeeBean));
		employeeBean.setSettlementDataMap(getSettlementData(empIdList,employeeBean));
		employeeBean.setTaxSalaryDataMap(getTaxSalaryData(empIdList,employeeBean));
		employeeBean.setPerquisiteDataMap(getPerquisiteData(empIdList,employeeBean));
		employeeBean.setOtherIncomeDataMap(getOtherIncomeData(empIdList,employeeBean));
		employeeBean.setExemptInvestmentDataMap(getExemptInvestmentData(empIdList,employeeBean));
		employeeBean.setInvstmentHraDataMap(getEmpInvetmentHraData(empIdList,employeeBean));
		employeeBean.setInvestmentDeducDataMap(getInvestmentDeducData(empIdList,employeeBean));
		employeeBean.setOtherSecDataMap(getOtherSectionData(empIdList,employeeBean));
		employeeBean.setChallanDataAprToDecMap(getChallanDataAprToDec(empIdList,employeeBean));
		employeeBean.setChallanDataJanToMarMap(getChallanDataJanToMar(empIdList,employeeBean));
		employeeBean.setSalDesignationMap(getSalDesigData(empIdList,employeeBean));
		return employeeBean;
	} //end of loadEmployeeInformation method
	
	private HashMap getSalDesigData(String empIdList,Form16MisReport bulkForm16) {
		Object salAprToDecData[][] = null;
		try {
			String salAprToDecQuery = getQuery(14, bulkForm16, empIdList);
			salAprToDecData = getSqlModel().getSingleResult(salAprToDecQuery);
		} catch (Exception e) {
			logger.error("exception in perquisite data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "3";
		HashMap mapData = convertObjectToHashMap(salAprToDecData, empLength,objectLength);
		
		Object salJanToMarData[][] = null;
		try {
			String salJanToMarQuery = getQuery(15, bulkForm16, empIdList);
			salJanToMarData = getSqlModel().getSingleResult(salJanToMarQuery);
		} catch (Exception e) {
			logger.error("exception in perquisite data in map",e);
		} //end of catch
		mapData = convertObjectToHashMapForSalDesg(salJanToMarData, empLength,objectLength,mapData);
		return mapData;
	} //end of getSalDesigData method
/**
 * get the challan details Map for Jan to March month
 * @param empIdList
 * @param bulkForm16
 * @return
 */
	private HashMap getChallanDataJanToMar(String empIdList,Form16MisReport bulkForm16) {
		Object challanJanToMarData[][] = null;
		try {
			String challanJanToMarQuery = getQuery(12, bulkForm16, empIdList);
			challanJanToMarData = getSqlModel().getSingleResult(challanJanToMarQuery);
		} catch (Exception e) {
			logger.error("exception in perquisite data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "11";
		HashMap mapData = convertObjectToHashMap(challanJanToMarData, empLength,objectLength);
		return mapData;
	} //end of getChallanDataJanToMar method
	
	/**
	 * get the challan details Apr for Dec to March month
	 * @param empIdList
	 * @param bulkForm16
	 * @return
	 */
	private HashMap getChallanDataAprToDec(String empIdList,Form16MisReport bulkForm16) {
		Object challanAprToDecData[][] = null;
		try {
			String challanAprToDecQuery = getQuery(11, bulkForm16, empIdList);
			challanAprToDecData = getSqlModel().getSingleResult(challanAprToDecQuery);
		} catch (Exception e) {
			logger.error("exception in perquisite data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "11";
		HashMap mapData = convertObjectToHashMap(challanAprToDecData, empLength,objectLength);
		return mapData;
	} //end of getChallanDataAprToDec method

	/**
	 * get the other section details Map
	 * @param empIdList
	 * @param bulkForm16
	 * @return
	 */
	private HashMap getOtherSectionData(String empIdList,Form16MisReport bulkForm16) {
		Object otherSec[][] = null;
		try {
			String otherSecQuery = getQuery(10, bulkForm16, empIdList);
			otherSec = getSqlModel().getSingleResult(otherSecQuery);
		} catch (Exception e) {
			logger.error("exception in perquisite data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "3";
		HashMap mapData = convertObjectToHashMap(otherSec, empLength,objectLength);
		return mapData;
	} //end of getOtherSectionData method

	/**
	 * get the investment details
	 * @param empIdList
	 * @param bulkForm16
	 * @return
	 */
	private HashMap getInvestmentDeducData(String empIdList,Form16MisReport bulkForm16) {
		Object invDeducAmt[][] = null;
		try {
			String invDeducAmtQuery = getQuery(9, bulkForm16, empIdList);
			invDeducAmt = getSqlModel().getSingleResult(invDeducAmtQuery);
		} catch (Exception e) {
			logger.error("exception in perquisite data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "3";
		HashMap mapData = convertObjectToHashMap(invDeducAmt, empLength,objectLength);
		return mapData;
	} //end of getInvestmentDeducData method

	/**
	 * get the investment HRA details Map 
	 * @param empIdList
	 * @param bulkForm16
	 * @return
	 */
	private HashMap getEmpInvetmentHraData(String empIdList,Form16MisReport bulkForm16) {
		Object empInvHraData[][] = null;
		if (taxParameter != null && taxParameter.length > 0) {
			try {
				String exemptInvQuery = getQuery(8, bulkForm16, empIdList);
				empInvHraData = getSqlModel().getSingleResult(exemptInvQuery);
			} catch (Exception e) {
				logger.error("exception in empInvHraData in map",e);
			} //end of catch
		} //end of if
		String[] empLength = empIdList.split(",");
		String objectLength = "3";
		HashMap mapData = convertObjectToHashMap(empInvHraData, empLength,objectLength);
		return mapData;
	} //end of getEmpInvetmentHraData method

	/**
	 * this is to retrieve for investment Chapter "Exempt"
	 * @param empIdList
	 * @param bulkForm16
	 * @return
	 */
	private HashMap getExemptInvestmentData(String empIdList,Form16MisReport bulkForm16) {
		Object exemptInvData[][] = null;
		try {
			String exemptInvQuery = getQuery(7, bulkForm16, empIdList);
			exemptInvData = getSqlModel().getSingleResult(exemptInvQuery);
		} catch (Exception e) {
			logger.error("exception in exemptInvQuery data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "5";
		HashMap mapData = convertObjectToHashMap(exemptInvData, empLength,objectLength);
		return mapData;
	} //end of getExemptInvestmentData method

	/**
	 * this is to retrieve for investment Chapter "Other"
	 * @param empIdList
	 * @param employeeBean
	 * @return
	 */
	private HashMap getOtherIncomeData(String empIdList,Form16MisReport bulkForm16) {
		Object otherIncomeData[][] = null;
		try {
			String otherIncomeQuery = getQuery(6, bulkForm16, empIdList);
			otherIncomeData = getSqlModel().getSingleResult(otherIncomeQuery);
		} catch (Exception e) {
			logger.error("exception in perquisite data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "5";
		HashMap mapData = convertObjectToHashMap(otherIncomeData, empLength,objectLength);
		return mapData;
	} //end of getOtherIncomeData method

	/**
	 * get the perqs details Map
	 * @param empIdList
	 * @param bulkForm16
	 * @return
	 */
	private HashMap getPerquisiteData(String empIdList,Form16MisReport bulkForm16) {
		Object perqData[][] = null;
		try {
			String perquisiteQuery = getQuery(5, bulkForm16, empIdList);
			perqData = getSqlModel().getSingleResult(perquisiteQuery);
		} catch (Exception e) {
			logger.error("exception in perquisite data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "2";
		HashMap mapData = convertObjectToHashMap(perqData, empLength,objectLength);
		return mapData;
	} //end of getPerquisiteData method

	/**
	 * the records from Tds calculation is retrieved..
	 * @param empIdList
	 * @param employeeBean
	 * @return
	 */
	private HashMap getTaxSalaryData(String empIdList,Form16MisReport bulkForm16) {
		Object taxData[][] = null;
		try {
			String taxDataQuery = getQuery(4, bulkForm16, empIdList);
			taxData = getSqlModel().getSingleResult(taxDataQuery);
		} catch (Exception e) {
			logger.error("exception in tax data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "14";
		HashMap mapData = convertObjectToHashMap(taxData, empLength,objectLength);
		return mapData;
	} //end of getTaxSalaryData method

	/**
	 * this method is used to set the settlement data.
	 * @param empIdList
	 * @param employeeBean
	 * @return
	 */
	private HashMap getSettlementData(String empIdList,Form16MisReport bulkForm16) {
		Object settleData[][] = null;
		try {
			String settleQuery = getQuery(3, bulkForm16, empIdList);
			settleData = getSqlModel().getSingleResult(settleQuery);
		} catch (Exception e) {
			logger.error("exception in settlement data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "2";
		HashMap mapData = convertObjectToHashMap(settleData, empLength,objectLength);
		return mapData;
	} //end of getSettlementData method

	/**
	 * This method is used to set the employee details.
	 * @param empIdList
	 * @param bulkForm16
	 * @return
	 */
	private HashMap getEmpOffcialData(String empIdList, Form16MisReport bulkForm16) {
		Object employeesOffData[][] = null;
		
		try {
			String empOffcQuery = getQuery(2, bulkForm16, empIdList);
			employeesOffData = getSqlModel().getSingleResult(empOffcQuery);
		} catch (Exception e) {
			logger.error("exception in empOfficial data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "10";
		HashMap mapData = convertObjectToHashMap(employeesOffData, empLength,objectLength);
		
		return mapData;
	} //end of getEmpOffcialData method
	
	/**
	 * This method is used to set the employee Certificate Details.
	 * @param empIdList
	 * @param bulkForm16
	 * @return
	 */
	private HashMap getEmpCertData(String empIdList, Form16MisReport bulkForm16) {
		Object employeesCertData[][] = null;
		
		try {
			String empCertQuery = getQuery(13, bulkForm16, empIdList);
			employeesCertData = getSqlModel().getSingleResult(empCertQuery);
		} catch (Exception e) {
			logger.error("exception in empOfficial data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "2";
		HashMap mapData = convertObjectToHashMap(employeesCertData, empLength,objectLength);
		
		return mapData;
	} //end of getEmpOffcialData method
/**
 * This method is used toi convert the Object to Hashmap
 * @param objectData
 * @param empLength
 * @param objectLength
 * @return
 */
	private HashMap convertObjectToHashMap(Object[][] objectData,String[] empLength, String objectLength) {
		HashMap dataMap = new HashMap();
		if (objectData == null) {

		} // end of if
		else if (objectData.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try{
			for (int i = 0; i < empLength.length; i++) {
				String empId = "";
				empId = String.valueOf(empLength[i]);
				for (int j = 0; j < objectData.length; j++) {
					if(String.valueOf(objectData[j][0]).equals(empLength[i])){
						v.add(objectData[j]);
					} //end of if
				} //end of totalDataObject loop
				Object[][]newData = new Object[v.size()][Integer.parseInt(objectLength)];
				for (int k = 0; k < newData.length; k++) {
					newData[k] = (Object[])v.get(k);
				} //end of loop
				dataMap.put(empId, newData);
				v = new Vector();
			} //end of empList loop
			}catch(Exception e){
				logger.error("exception in convertAllowanceObjectToHashMap",e);
			} //end of catch
			////logger.info("dataMap.get(429)======"+dataMap.get("429"));
		} //end of else
		return dataMap;
	} //end of convertObjectToHashMap method
	
	/**
	 * This method is used only for salary designations.Here hashmap is passed in the method
	 * @param objectData
	 * @param empLength
	 * @param objectLength
	 * @param mapData
	 * @return
	 */
	private HashMap convertObjectToHashMapForSalDesg(Object[][] objectData,String[] empLength, String objectLength, HashMap mapData) {
		
		if (objectData == null) {

		} // end of if
		else if (objectData.length == 0) {

		} // end of else if
		else {
			Vector v = new Vector();
			try{
			for (int i = 0; i < empLength.length; i++) {
				String empId = "";
				empId = String.valueOf(empLength[i]);
				for (int j = 0; j < objectData.length; j++) {
					if(String.valueOf(objectData[j][0]).equals(empLength[i])){
						v.add(objectData[j]);
					} //end of if
				} //end of totalDataObject loop
				Object[][]newData = new Object[v.size()][Integer.parseInt(objectLength)];
				for (int k = 0; k < newData.length; k++) {
					newData[k] = (Object[])v.get(k);
				} //end of loop
				mapData.put(empId, newData);
				v = new Vector();
			} //end of empList loop
			}catch(Exception e){
				logger.error("exception in convertAllowanceObjectToHashMap",e);
			} //end of catch
			////logger.info("dataMap.get(429)======"+dataMap.get("429"));
		} //end of else
		return mapData;
	} //end of convertObjectToHashMap method
	
	public String checkNull(String result){
		if(result ==null ||result.equalsIgnoreCase("null")){
			return "";
		}
		else{
			return result;
		}
	}
/**
 * This method is used for setting the employee details in case of general user
 * @param bulkForm16
 */
	public void setDetailsForGeneralUser(Form16MisReport bulkForm16) {
		Object[][]userData = null;
		try {
			String query = "SELECT EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_LNAME FROM HRMS_EMP_OFFC WHERE EMP_ID = "
					+ bulkForm16.getUserEmpId() + "";
			userData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in userData",e);
		} //end of catch
		if(userData !=null && userData.length > 0){
			bulkForm16.setEmpId(String.valueOf(userData[0][0]));
			bulkForm16.setEmpToken(String.valueOf(userData[0][1]));
			bulkForm16.setEmpName(String.valueOf(userData[0][2]));
		} //end of if
	} //end of setDetailsForGeneralUser method
} //end of class
