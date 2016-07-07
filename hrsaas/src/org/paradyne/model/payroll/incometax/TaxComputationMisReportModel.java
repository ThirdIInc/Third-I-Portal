/**
 * 
 */
package org.paradyne.model.payroll.incometax;

import java.awt.Color;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.incometax.TaxComputationMisReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 * @author aa0517
 *
 */
public class TaxComputationMisReportModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TaxComputationMisReportModel.class);
	
	Object[][] taxParameter=null;
	Object[][] invDeducData=null;
	Object[][] cccSection=null;
	
	public String generateUrlList(HttpServletRequest request,
			HttpServletResponse response, TaxComputationMisReport taxComputation) {
		String empId = "";
		String empDetailsQuery=getQuery(1,taxComputation,empId);
		Object finalEmployees[][]=getSqlModel().getSingleResult(empDetailsQuery);
		
		if (finalEmployees!=null && finalEmployees.length>0) {
			request.setAttribute("totalRecords",finalEmployees.length);
			request.setAttribute("recPerPage",taxComputation.getRecordsPerPage());
			taxComputation.setRecordFlag(true);
			return "1";
		} //end of if
		else{
			request.setAttribute("totalRecords",0);
			return "No records for selected criteria";
		} //end of else
	} //end of generateReport method

	private String getQuery(int id, TaxComputationMisReport taxComputation,String empId) {
		String query="";
		int frmYear = taxComputation.getFrmYear();
		int toYear = taxComputation.getToYear(); 
		switch(id){
		
		case 1: query="SELECT ROWNUM,EMP_ID FROM HRMS_EMP_OFFC WHERE 1=1 " +
				" "+filter(taxComputation);
		break;
		
		case 2:	query="SELECT HRMS_EMP_OFFC.EMP_ID,NVL(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||  ' '|| "
		+" HRMS_EMP_OFFC.EMP_LNAME,' ')  ||''||NVL(RANK_NAME,' ')as emp_name,NVL(DIV_NAME||''||DIV_ADDRESS1||','||DIV_ADDRESS2||''||   "
		+" DIV_ADDRESS3||''||LOCATION_NAME||'-'||DIV_PINCODE,' ')as emplo,  nvl(DIV_PANNO,' '),NVL(DIV_TANNO,' '),NVL(LOCATION_NAME,' '), "
		+" NVL(DIV_NAME,' '),TO_CHAR(SYSDATE,'DD-MM-YYYY') "
		+" ,CASE WHEN EMP_REGULAR_DATE <= TO_DATE('01-04-"+frmYear+"','DD-MM-YYYY')  THEN '01-04-"+frmYear+"' "  
		+" WHEN EMP_REGULAR_DATE > TO_DATE('01-04-"+frmYear+"','DD-MM-YYYY')  THEN TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') "  
		+" END AS JOINING,nvl(SAL_PANNO,' '),EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||EMP_LNAME  "
		+" FROM HRMS_EMP_OFFC "  
		+" LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "  
		+" LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE "  
		+" LEFT JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV	  "
		+" LEFT JOIN HRMS_LOCATION ON HRMS_LOCATION.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID " +
		 " LEFT JOIN HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) " 
		+" where HRMS_EMP_OFFC.EMP_ID IN ("+empId+")";
		break;
			
		case 3: query="SELECT SETTL_ECODE,CASE WHEN SETTL_SEPRDT > TO_DATE('31-03-"+toYear+"','DD-MM-YYYY') THEN TO_CHAR(TO_DATE('31-03-"+toYear+"','DD-MM-YYYY'),'DD-MM-YYYY') ELSE " 
		+" TO_CHAR(TO_DATE(SETTL_SEPRDT,'DD-MM-YYYY'),'DD-MM-YYYY')end  "
		+" FROM HRMS_SETTL_HDR  "
		+" WHERE SETTL_RESGNO IN(SELECT MAX(RESIGN_CODE) FROM HRMS_RESIGN WHERE RESIGN_EMP IN ("+empId+")) " 
		+" AND SETTL_LOCKFLAG = 'Y' AND   SETTL_SETTLDT BETWEEN TO_DATE('01-04-"+frmYear+"','DD-MM-YYYY') "
		+" AND TO_DATE('31-03-"+toYear+"','DD-MM-YYYY')";
		break;
		
		case 4: query = "SELECT EMP_ID,NVL(TDS_GROSS_SALARY,0),NVL(TDS_EXCEMPTIONS,0),NVL(TDS_REBATE,0),NVL(TDS_OTH_INCOME,0), " 
		+" NVL(TDS_DEDUCTIONS,0),NVL(TDS_TAXABLE_INCOME,0),NVL(TDS_TOTAL_TAX,0),NVL(TDS_EDUC_CESS,0),  "
		+" NVL(TDS_SURCHARGE,0),NVL(TDS_NET_TAX,0),NVL(TDS_TAXPERMONTH,0),NVL(TDS_TAX_PAID,0), "
		+" NVL(TDS_PROF_TAX,0),CASE WHEN EMP_REGULAR_DATE <= TO_DATE('01-04-"+frmYear+"','DD-MM-YYYY') " 
		+" THEN '01-04-"+frmYear+"' "
		+" WHEN EMP_REGULAR_DATE > TO_DATE('01-04-"+frmYear+"','DD-MM-YYYY') " 
		+" THEN TO_CHAR(EMP_REGULAR_DATE,'DD-MM-YYYY') "
		+" END AS JOINING FROM HRMS_TDS  "
		+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = HRMS_TDS.TDS_EMP_ID "  
		+" WHERE EMP_ID IN ("+empId+") and TDS_FROM_YEAR="+frmYear+" "
		+" and TDS_TO_YEAR="+toYear+" ";
		break;
		
		case 5: query = "SELECT TDS_EMP_ID,NVL(TDS_PERQ_AMOUNT,0)FROM HRMS_TDS_PERQ "  
		+" LEFT JOIN HRMS_PERQUISITE_HEAD ON (HRMS_PERQUISITE_HEAD.PERQ_CODE = HRMS_TDS_PERQ.TDS_PERQ_HEAD) "  
		+" WHERE TDS_EMP_ID IN ("+empId+") "
		+" AND TDS_YEAR_FROM="+frmYear+" AND TDS_YEAR_TO="+toYear+"";
		break;
		
		case 6: query = "SELECT EMP_ID,HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,NVL( CASE WHEN INV_TYPE = 'N' THEN sum(NVL(INV_VALID_AMOUNT,0)) "
			+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) > (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
			+ " WHERE TDS_FINANCIALYEAR_FROM = "
			+ frmYear
			+ " AND  TDS_FINANCIALYEAR_TO = "
			+ toYear
			+ ") AND INV_IS_VERIFIED='Y'"
			+ " THEN sum(LEAST(NVL(INV_VERIFIED_AMOUNT,0),NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))))   "
			+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) <= (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
			+ " WHERE TDS_FINANCIALYEAR_FROM = "
			+ frmYear
			+ " AND  TDS_FINANCIALYEAR_TO = "
			+ toYear
			+ ")  "
			+ " THEN sum(LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0)))) "
			+ "  WHEN (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER " 
			+"  WHERE TDS_FINANCIALYEAR_FROM = "+frmYear+" AND  TDS_FINANCIALYEAR_TO = "+toYear+") IS NULL THEN sum(LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))))" +
			  " END,0) AS INVESTMENT_AMOUNT,INV_OTHER_FLAG "  
		+" FROM HRMS_TAX_INVESTMENT  "
		+" LEFT JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE) "  
		+" WHERE INV_CHAPTER = 'OTHER' and INV_FINYEAR_FROM="+frmYear+" "
		+" and INV_FINYEAR_TO="+toYear+" AND EMP_ID IN ("+empId+") GROUP BY EMP_ID,HRMS_TAX_INVESTMENT.INV_CODE,INV_TYPE,INV_OTHER_FLAG,INV_NAME,INV_IS_VERIFIED";
		break;
		
		/*case 7: query = "SELECT EMP_ID,HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION,SUM(INV_VALID_AMOUNT ) "  
		+" FROM HRMS_TAX_INVESTMENT   "
		+" LEFT JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE =  HRMS_TAX_INVESTMENT.INV_CODE) "  
		+" WHERE INV_CHAPTER='EXEMPT' AND SUBSTR(INV_SECTION,0,2)=10   and INV_FINYEAR_FROM="+frmYear+" "
		+" and INV_FINYEAR_TO="+toYear+"  and EMP_ID IN ("+empId+") "
		+" GROUP BY EMP_ID,HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION ";*/
		case 7: query = "SELECT EMP_ID,HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION,NVL( CASE WHEN INV_TYPE = 'N' THEN SUM(NVL(INV_VALID_AMOUNT,0)) "
			+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) > (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
			+ " WHERE TDS_FINANCIALYEAR_FROM = "
			+ frmYear
			+ " AND  TDS_FINANCIALYEAR_TO = "
			+ toYear
			+ ") AND INV_IS_VERIFIED='Y'"
			+ " THEN sum(CASE WHEN NVL(INV_VERIFIED_AMOUNT,0)>NVL(INV_VALID_AMOUNT,0) THEN NVL(INV_VALID_AMOUNT,0) ELSE NVL(INV_VERIFIED_AMOUNT,0) END)   "
			+ " WHEN (SELECT TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM DUAL) <= (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER "
			+ " WHERE TDS_FINANCIALYEAR_FROM = "
			+ frmYear
			+ " AND  TDS_FINANCIALYEAR_TO = "
			+ toYear
			+ ")  "
			+ " THEN sum(LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0)))) "
			+ "  WHEN (SELECT TO_DATE(TO_CHAR(TDS_INV_VERIFICATION_DATE,'DD-MM-YYYY'),'DD-MM-YYYY') FROM HRMS_TAX_PARAMETER " 
			+"  WHERE TDS_FINANCIALYEAR_FROM = "+frmYear+" AND  TDS_FINANCIALYEAR_TO = "+toYear+") IS NULL THEN sum(LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0))))" +
			  " END,0) as INVESTMENT_AMOUNT "  
		+" FROM HRMS_TAX_INVESTMENT   "
		+" LEFT JOIN HRMS_EMP_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE =  HRMS_TAX_INVESTMENT.INV_CODE) "  
		+" WHERE INV_CHAPTER='EXEMPT' AND SUBSTR(INV_SECTION,0,2)=10   and INV_FINYEAR_FROM="+frmYear+" "
		+" and INV_FINYEAR_TO="+toYear+"  and EMP_ID IN ("+empId+") "
		+" GROUP BY EMP_ID,HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION,INV_TYPE,INV_IS_VERIFIED ";
		break;
		
		/*case 8: query = "SELECT EMP_ID,INV_CODE,INV_VALID_AMOUNT " 
		+" FROM HRMS_EMP_INVESTMENT  "
		+" WHERE EMP_ID IN ("+empId+") AND INV_FINYEAR_FROM="+frmYear+" AND INV_FINYEAR_TO="+toYear+" " 
		+" AND INV_CODE = "+taxParameter[0][0]+"";*/
		
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
			+"  WHERE TDS_FINANCIALYEAR_FROM = "+frmYear+" AND  TDS_FINANCIALYEAR_TO = "+toYear+") IS NULL THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0)))" +
			  " END,0)) AS INVESTMENT_AMOUNT8 " 
		+" FROM HRMS_EMP_INVESTMENT  "
		+ " INNER JOIN HRMS_TAX_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE) "
		+" WHERE EMP_ID IN ("+empId+") AND INV_FINYEAR_FROM="+frmYear+" AND INV_FINYEAR_TO="+toYear+" " 
		+" AND INV_CODE = "+taxParameter[0][0]+"  GROUP BY EMP_ID,HRMS_EMP_INVESTMENT.INV_CODE,INV_TYPE,INV_IS_VERIFIED ";
		break;
		
		/*case 9: query = "SELECT EMP_ID,INV_CODE,NVL(INV_VALID_AMOUNT,0) "
		+" FROM HRMS_EMP_INVESTMENT  "
		+" WHERE EMP_ID IN ("+empId+") AND INV_FINYEAR_FROM="+frmYear+" AND INV_FINYEAR_TO="+toYear+" ";*/
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
			+"  WHERE TDS_FINANCIALYEAR_FROM = "+frmYear+" AND  TDS_FINANCIALYEAR_TO = "+toYear+") IS NULL THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0)))" +
			  " END,0)) AS INVESTMENT_AMOUNT  "
		+" FROM HRMS_EMP_INVESTMENT  "
		+ " INNER JOIN HRMS_TAX_INVESTMENT ON (HRMS_EMP_INVESTMENT.INV_CODE = HRMS_TAX_INVESTMENT.INV_CODE) "
		+" WHERE EMP_ID IN ("+empId+") AND INV_FINYEAR_FROM="+frmYear+" AND INV_FINYEAR_TO="+toYear+" "
		+" GROUP BY EMP_ID,HRMS_EMP_INVESTMENT.INV_CODE,INV_TYPE,INV_IS_VERIFIED ";
		break;
		
	/*	case 10: query = "SELECT EMP_ID,NVL(INV_VALID_AMOUNT,0),INV_SECTION " 
		+" FROM HRMS_EMP_INVESTMENT   "
		+" LEFT JOIN HRMS_TAX_INVESTMENT ON(HRMS_TAX_INVESTMENT.INV_CODE = HRMS_EMP_INVESTMENT.INV_CODE) "  
		+" WHERE INV_SECTION IN('80E','80G','80D') AND EMP_ID IN ("+empId+") "
		+" AND INV_FINYEAR_FROM = "+frmYear+" AND  INV_FINYEAR_TO = "+toYear+"";*/
		case 10: query = "SELECT EMP_ID,sum(NVL( CASE WHEN INV_TYPE = 'N' THEN NVL(INV_VALID_AMOUNT,0) "
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
			+"  WHERE TDS_FINANCIALYEAR_FROM = "+frmYear+" AND  TDS_FINANCIALYEAR_TO = "+toYear+") IS NULL THEN LEAST(NVL(INV_VALID_AMOUNT,0),NVL(INV_SAL_AMOUNT,NVL(INV_VALID_AMOUNT,0)))" +
			  " END,0)) AS INVESTMENT_AMOUNT,INV_SECTION " 
		+" FROM HRMS_EMP_INVESTMENT   "
		+" LEFT JOIN HRMS_TAX_INVESTMENT ON(HRMS_TAX_INVESTMENT.INV_CODE = HRMS_EMP_INVESTMENT.INV_CODE) "  
		//+" WHERE INV_SECTION IN('80E','80G','80D') AND EMP_ID IN ("+empId+") "
		+" WHERE INV_SECTION NOT IN('80CCC','80CCCD','80C') AND SUBSTR(INV_SECTION,0,2)!=10  AND EMP_ID IN ("+empId+") "
		+" AND INV_FINYEAR_FROM = "+frmYear+" AND  INV_FINYEAR_TO = "+toYear+""
		+" GROUP BY INV_TYPE,EMP_ID,INV_SECTION,INV_IS_VERIFIED";
		break;
		
		
		case 11: query = "SELECT EMP_ID,CHALLAN_TDS,HRMS_TAX_CHALLAN_DTL.CHALLAN_SURCHARGE,CHALLAN_EDU_CESS, "
		+" CHALLAN_TOTAL_TAX,CHALLAN_CHQNO,BANK_BSR_CODE,  TO_CHAR(CHALLAN_DATE,'DD-MM-YYYY'),CHALLAN_NO, "
		+" HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE   "
		+" FROM HRMS_TAX_CHALLAN_DTL "   
		+" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "  
		+" LEFT JOIN HRMS_BANK ON HRMS_BANK.BANK_MICR_CODE = hrms_tax_challan.CHALLAN_BANK   "
		+" WHERE EMP_ID IN ("+empId+") AND CHALLAN_YEAR="+taxComputation.getFrmYear()+" AND CHALLAN_MONTH IN(4,5,6,7,8,9,10,11,12) " 
		+" ORDER BY  CHALLAN_MONTH";
		break;
		
		case 12: query = "SELECT EMP_ID,CHALLAN_TDS,HRMS_TAX_CHALLAN_DTL.CHALLAN_SURCHARGE,CHALLAN_EDU_CESS,CHALLAN_TOTAL_TAX, "
		+" CHALLAN_CHQNO,BANK_BSR_CODE,  TO_CHAR(CHALLAN_DATE,'DD-MM-YYYY'),CHALLAN_NO,HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE  "
		+" FROM HRMS_TAX_CHALLAN_DTL    "
		+" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "  
		+" LEFT JOIN HRMS_BANK ON HRMS_BANK.BANK_MICR_CODE = HRMS_TAX_CHALLAN.CHALLAN_BANK   "
		+" WHERE EMP_ID IN ("+empId+") AND CHALLAN_YEAR="+taxComputation.getToYear()+" AND CHALLAN_MONTH IN(1,2,3) "
		+" ORDER BY  CHALLAN_MONTH";
		break;
		
		default:
			break;	
		} //end of switch
		
		return query;
	} //end of getQuery method
	
	/*
	 * This method to be used for adding filter constraints to the Query's.
	 */
	public String filter(TaxComputationMisReport bean)
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
		if(!bean.getPaybillId().equals(""))
		{
			query+=" AND HRMS_EMP_OFFC.EMP_PAYBILL="+bean.getPaybillId();
		}
		if(!bean.getReportingToId().equals(""))
		{
			query+=" AND HRMS_EMP_OFFC.EMP_REPORTING_OFFICER="+bean.getReportingToId();
		}
		return query;
	} //end of filter method
	
	public void generateReport(HttpServletRequest request,
			HttpServletResponse response, TaxComputationMisReport taxComputation) {
		String empIdList = "";
		int frmYear = taxComputation.getFrmYear();
		int toYear = taxComputation.getToYear(); 
		Object finalEmployeesList[][] = null;
		if(taxComputation.isGeneralFlag()){
			finalEmployeesList = new Object[1][2];  
			finalEmployeesList[0][0]= taxComputation.getUserEmpId();
			finalEmployeesList[0][1]= taxComputation.getUserEmpId();
		} //end of if
		else{
			String empListQuery=getQuery(1,taxComputation,empIdList);
			finalEmployeesList=getSqlModel().getSingleResult(empListQuery);
		} //end of else
		
		
		if (finalEmployeesList!=null && finalEmployeesList.length>0) {
			
			int i=0;
			int noOfRecrdsPerPage=0;
			
			if (Integer.parseInt(""+taxComputation.getRecordsPerPage())!= 0) {	
				noOfRecrdsPerPage=taxComputation.getRecordsPerPage();
			} //end of if
			
			int startRecord=0,endpage=0;
			if(!taxComputation.getRangeCode().equals("")){
				 startRecord=Integer.parseInt(taxComputation.getRangeCode())*noOfRecrdsPerPage;
				 endpage=startRecord+noOfRecrdsPerPage;
			} //end of if
			
			if (endpage>=finalEmployeesList.length) {
				endpage=finalEmployeesList.length;
			} //end of if
			i=startRecord;
			
			if(!taxComputation.getEmpId().equals(""))
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
				taxComputation = loadEmployeeInformation(empIdList,taxComputation);
				
				try {
					createReport(taxComputation, empIdList,response);
				} catch (Exception e) {
					logger.error("exception in createReport",e);
				} //end of catch
				
			} //end of if
			
		} //end of if
	} //end of generateReport method

	private void createReport(TaxComputationMisReport taxComputation, String empIdList, HttpServletResponse response) {
		String[] empLength = empIdList.split(",");
		ReportDataSet rds = new ReportDataSet();
		rds.setFileName("Statement of Tax Calculation");
		rds.setReportName("PROVISIONAL TAX COMPUTATION");
		rds.setReportType("Pdf");
		org.paradyne.lib.ireport.ReportGenerator rg=new org.paradyne.lib.ireport.ReportGenerator(rds);
		
		String frmYear = String.valueOf(taxComputation.getFrmYear());
		String ack1 = "", ack2 = "", ack3 = "", ack4 = "";
		String toYear = String.valueOf(taxComputation.getToYear());
		String quarterFrm = frmYear.substring(2, 4);
		String quarterTo = toYear.substring(2, 4);
		
		Object[][] ackData=null;
		try {
			/**
			 * to retireive the challan records for acknowledgement no, year and month..
			 */
			String ackNoQuery = "SELECT CHALLAN_MONTH,CHALLAN_YEAR,CHALLAN_ACK_NO FROM HRMS_TAX_CHALLAN";
			ackData = getSqlModel().getSingleResult(ackNoQuery);
		} catch (Exception e) {
			logger.error("error in ackData query   "+e);
		} //end of catch
		
		
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
			taxComputation.setSignAuthEmpId(String.valueOf(taxParameter[0][3]));
			taxComputation.setSignAuthtoken(String.valueOf(taxParameter[0][4]));
			taxComputation.setSignAuthName(String.valueOf(taxParameter[0][5]));
			taxComputation.setSignAuthEmpDesg(String.valueOf(taxParameter[0][6]));
		} //end of if
		
		try {
			/**
			 * here in this query all the investments which are under section 80 are retrieved...
			 */
			String invDeductionQuery = "SELECT  INV_CODE,INV_NAME,ROWNUM FROM HRMS_TAX_INVESTMENT "
					+ " WHERE INV_SECTION ='80C' AND INV_IS_INCLUDE_IN_80C_LIMIT='Y' ORDER BY INV_CODE";
			invDeducData = getSqlModel().getSingleResult(invDeductionQuery);
		} catch (Exception e) {
			logger.error("Exception in creating invDeducData--"+e);
		} //end of catch
		
		try {
			String ccdquery = "SELECT  HRMS_TAX_INVESTMENT.INV_CODE,INV_NAME,INV_SECTION "
					+ " FROM HRMS_TAX_INVESTMENT "
					+ " WHERE INV_SECTION IN('80CCC','80CCCD')";
			cccSection = getSqlModel().getSingleResult(ccdquery);
		} catch (Exception e) {
			logger.error("execption in cccSection----"+e);
		} //end of catch
		
		String divDetailsQury = "SELECT DIV_NAME,nvl(DIV_ADDRESS1,'')||' '||nvl(DIV_ADDRESS2,'')||' '||nvl(DIV_ADDRESS3,'')"
			+ "   FROM HRMS_DIVISION where DIV_ID="
			+ taxComputation.getDivisionId();
		Object[][] companyData = getSqlModel().getSingleResult(divDetailsQury);
		
		for (int i = 0; i < empLength.length; i++) {
			double year = 0,sec10Amt = 0;
			/*Object[][]headingForm16 = new Object[1][1];
			headingForm16[0][0] = "FORM NO.16";
			int [] cellWidthheadingForm16={100};
			int [] cellAlignheadingForm16={1};
			TableDataSet tableheadingForm16 = new TableDataSet();
			tableheadingForm16.setData(headingForm16);
			tableheadingForm16.setCellWidth(cellWidthheadingForm16);
			tableheadingForm16.setCellAlignment(cellAlignheadingForm16);
			tableheadingForm16.setBorder(false);
			tableheadingForm16.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0));
			rg.addTableToDoc(tableheadingForm16);*/
			
			Object[][]headingData = new Object[2][1];
			headingData[0][0] = companyData[0][0];
			headingData[1][0] = companyData[0][1];
			int [] cellWidthHeader={100};
			int [] cellAlignHeader={1};
			TableDataSet tableheadingData = new TableDataSet();
			tableheadingData.setData(headingData);
			tableheadingData.setCellWidth(cellWidthHeader);
			tableheadingData.setCellAlignment(cellAlignHeader);
			tableheadingData.setBorder(false);
			tableheadingData.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
			tableheadingData.setBlankRowsBelow(1);
			rg.addTableToDoc(tableheadingData);
			
			//========================Start of employee details=================================//
			Object[][]empDetailsMap = null;
			try {
				empDetailsMap = (Object[][]) taxComputation.getEmpOfficialDataMap().get(empLength[i]);
			} catch (Exception e) {
				logger.error("exception in empDetail",e);
			} //end of catch
			
			Object[][] taxData=null;
			taxData = (Object[][])taxComputation.getTaxSalaryDataMap().get(empLength[i]);
			
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
					Object[][]settleData = (Object[][])taxComputation.getSettlementDataMap().get(empLength[i]);
					String periodTo = "";
					if(settleData !=null && settleData.length > 0){
						periodTo = String.valueOf(settleData[0][1]);
					} //end of if
					else{
						periodTo = "31-03-"+taxComputation.getToYear()+"";
					}
					Object[][]partAHeading = new Object[3][2];
					partAHeading[0][0] = "";
					partAHeading[0][1] = "Provisional Statement Of Tax Calculation of "+checkNull(String.valueOf(empDetailsMap[0][10]).toUpperCase());
					partAHeading[1][0] = "Part A";
					partAHeading[1][1] = "Financial Year "+taxComputation.getFrmYear()+"-"+taxComputation.getToYear()+"";
					partAHeading[2][0] = "";
					partAHeading[2][1] = "Period From - "+taxData[0][14]+" To - "+periodTo+"";
					int[] cellWidthPartA = {8, 92};
					int[] cellAlignPartA = {0,1};
					TableDataSet tablePartA = new TableDataSet();
					tablePartA.setData(partAHeading);
					tablePartA.setCellWidth(cellWidthPartA);
					tablePartA.setCellAlignment(cellAlignPartA);
					tablePartA.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
					tablePartA.setBorder(false);
					rg.addTableToDoc(tablePartA);
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
					tableRs.setBorder(true);
					rg.addTableToDoc(tableRs);
				} catch (Exception e) {
					logger.error("exception in tax data",e);
				} //end of catch
			
			
			//=============Gross salary=====================//
			Object [][]perquisiteData = null;
			perquisiteData = (Object[][])taxComputation.getPerquisiteDataMap().get(empLength[i]);
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
			otherIncomeData = (Object[][])taxComputation.getOtherIncomeDataMap().get(empLength[i]);
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
							if(String.valueOf(otherIncomeData[j][4]).equals("D")){
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
				taxTotal[0][1]="\n"+String.valueOf(Math.round((Double.parseDouble(String.valueOf(taxData[0][1])))) - totalPerqAmt - totalOtherAmt)+"\n\n"+Math.round(totalPerqAmt)+"\n\nNIL";
				taxTotal[0][2]="\n\n\n\n\n\n"+String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxData[0][1])) - totalOtherAmt))+"";
			} //end of else
			taxTotal[0][3]="";
			int [] cellWidthtaxTotal={50,15,15,20};
			int [] cellAligntaxTotal={0,2,2,2};
			
			TableDataSet tabletaxTotal = new TableDataSet();
			tabletaxTotal.setData(taxTotal);
			tabletaxTotal.setCellWidth(cellWidthtaxTotal);
			tabletaxTotal.setCellAlignment(cellAligntaxTotal);
			tabletaxTotal.setBorder(true);
			rg.addTableToDoc(tabletaxTotal);
			
			//=========Exempt investment data=====================//
			Object[][] exemptInvData=null;
			exemptInvData = (Object[][])taxComputation.getExemptInvestmentDataMap().get(empLength[i]);
			Object exemptData[][] = null;
			if(exemptInvData ==null || exemptInvData.length ==0){
				
			}
			else{
				Object[][] empInvHra=null;
				empInvHra = (Object[][])taxComputation.getInvstmentHraDataMap().get(empLength[i]);
				
				exemptData= new Object[exemptInvData.length+1][4];
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
			tableExemptData.setBorder(true);
			rg.addTableToDoc(tableExemptData);
			
			//=================Balance===========================//
			Object[][] invDeducAmt=null;
			invDeducAmt = (Object[][])taxComputation.getInvestmentDeducDataMap().get(empLength[i]);
			
			double deductionTotal=0;
			Object taxTotal2[][] =	null;
			if(invDeducData!=null && invDeducData.length>0){
				try {
					taxTotal2 = new Object[invDeducData.length + 4 + 8 + 3][5];
				} catch (Exception e) {
					logger.error("exception in creating taxTotal2 object",e);
				} //end of catch
			} //end of if
			else{
				taxTotal2= new Object[3 + 8 + 3][5];
			} //end of else
			taxTotal2[0][0]="3. Balance (1-2)";
			taxTotal2[0][1]="";
			taxTotal2[0][2]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal[0][2])) - sec10Amt));
			taxTotal2[0][3]="";
			
			taxTotal2[1][0]="4. Deductions:";
			taxTotal2[1][1]="";
			taxTotal2[1][2]="";
			taxTotal2[1][3]="";
			
			taxTotal2[2][0]="     a)Entertainment allowance      NIL";
			taxTotal2[2][1]="";
			taxTotal2[2][2]="";
			taxTotal2[2][3]="";
			
			taxTotal2[3][0]="     b)Tax on Employment             "+taxData[0][13]+"";
			taxTotal2[3][1]="";
			taxTotal2[3][2]="";
			taxTotal2[3][3]="";
			
			taxTotal2[4][0]="5. Aggregate of 4(a) and 4(b)";
			taxTotal2[4][1]=""+taxData[0][13]+"";
			taxTotal2[4][2]="";
			taxTotal2[4][3]="";
			
			taxTotal2[5][0]="6. INCOME CHARGEABLE UNDER THE HEAD 'SALARIES' (3-5)";
			taxTotal2[5][1]="";
			taxTotal2[5][2]="";
			taxTotal2[5][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal2[0][2])) - Double.parseDouble(String.valueOf(taxTotal2[4][1]))));
			
			taxTotal2[6][0]=add;
			taxTotal2[6][1]=amt;
			taxTotal2[6][2]="";
			taxTotal2[6][3]=total + totalOtherAmt;
			
			taxTotal2[7][0]="8. GROSS TOTAL INCOME (6+7)";
			taxTotal2[7][1]="";
			taxTotal2[7][2]="";
			taxTotal2[7][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal2[5][3])) + totalOtherAmt));
			
			taxTotal2[8][0]="9. DEDUCTIONS UNDER CHAPTER VI-A";
			taxTotal2[8][1]="";
			taxTotal2[8][2]="";
			taxTotal2[8][3]="";
			taxTotal2[8][4]="";
			
			taxTotal2[9][0]="A) 80C, 80CCC AND 80CCD";
			taxTotal2[9][1]="GROSS AMOUNT";
			taxTotal2[9][2]="QUALIFYING AMOUNT";
			taxTotal2[9][3]="DEDUCTIBLE AMOUNT";
			taxTotal2[9][4]="";
			
			taxTotal2[10][0]="a) Section 80C";
			taxTotal2[10][1]="(Rs.)";
			taxTotal2[10][2]="(Rs.)";
			taxTotal2[10][3]="(Rs.)";
			taxTotal2[10][4]="";
			
			if(invDeducData == null){
				
			} //end of if
			else if(invDeducData.length == 0){
				
			} //end of else if
			else{
				try {
					for (int j = 0; j < invDeducData.length; j++) {

						taxTotal2[j + 3 + 8][0] = "   " + (j+1)
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
										taxTotal2[j + 3 + 8][1] = Math.round(Double
														.parseDouble(String.valueOf(invDeducAmt[k][2])));
										
									} //end of if
								} //end of loop
							} catch (Exception e) {
								logger.error("Exception in invDeducData--"+e);
							} //end of catch
						} //end of else

						taxTotal2[j + 3 + 8][2] = "";
						taxTotal2[j + 3 + 8][3] = "";
						taxTotal2[j + 3 + 8][4] = "";
						if (String.valueOf(taxTotal2[j + 3 + 8][1]).equals("")
								|| String.valueOf(taxTotal2[j + 3 + 8][1]).equals("null")) {taxTotal2[j + 3 + 8][1] = 0;
						} //end of if
						deductionTotal = Math.round(deductionTotal
								+ Double.parseDouble(String.valueOf(taxTotal2[j + 3 + 8][1])));
					} //end of loop
				} catch (Exception e) {
					logger.error("Exception in invDeducData--"+e);
				} //end of catch
			} //end of else
			double deductibleAmt=0;
			try {
				if (taxParameter != null && taxParameter.length > 0) {
					if (deductionTotal > Double.parseDouble(String
							.valueOf(taxParameter[0][2]))) {
						deductibleAmt = Double.parseDouble(String
								.valueOf(taxParameter[0][2]));
						
					} //end of if
					else{
						deductibleAmt=deductionTotal;
					}
				} //end of if
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			taxTotal2[invDeducData.length + 3 + 8][0]="Total [1 to "+invDeducData.length+"]";
			taxTotal2[invDeducData.length + 3 + 8][1]=""+Math.round(deductionTotal)+"";
			taxTotal2[invDeducData.length + 3 + 8][2]=""+Math.round(deductibleAmt)+"";
			taxTotal2[invDeducData.length + 3 + 8][3]=""+Math.round(deductibleAmt)+"";
			taxTotal2[invDeducData.length + 3 + 8][4]="";
			
			double cccAmt=0,ccdAmt=0;
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
						  * CCF commented by mangesh
						  * 
						  */
						 /*if(cccSection[j][2].equals("80CCF")){
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
			
			taxTotal2[invDeducData.length + 3 + 8 + 1][0]="b) Section 80CCC";
			taxTotal2[invDeducData.length + 3 + 8 + 1][1]=""+Math.round(cccAmt)+"";
			taxTotal2[invDeducData.length + 3 + 8 + 1][2]=""+Math.round(cccAmt)+"";
			taxTotal2[invDeducData.length + 3 + 8 + 1][3]=""+Math.round(cccAmt)+"";
			taxTotal2[invDeducData.length + 3 + 8 + 1][4]="";
			
			taxTotal2[invDeducData.length + 3 + 8 + 2][0]="c) Section 80CCD";
			taxTotal2[invDeducData.length + 3 + 8 + 2][1]=""+Math.round(ccdAmt)+"";
			taxTotal2[invDeducData.length + 3 + 8 + 2][2]=""+Math.round(ccdAmt)+"";
			taxTotal2[invDeducData.length + 3 + 8 + 2][3]=""+Math.round(ccdAmt)+"";
			taxTotal2[invDeducData.length + 3 + 8 + 2][4]="";
			
			/*taxTotal2[invDeducData.length + 3 + 8 + 3][0]="d) Section 80CCF";
			taxTotal2[invDeducData.length + 3 + 8 + 3][1]=""+Math.round(ccfAmt)+"";
			taxTotal2[invDeducData.length + 3 + 8 + 3][2]=""+Math.round(ccfAmt)+"";
			taxTotal2[invDeducData.length + 3 + 8 + 3][3]=""+Math.round(ccfAmt)+"";
			taxTotal2[invDeducData.length + 3 + 8 + 3][4]="";*/
			
			int [] cellWidth2={35,15,15,15,20};
			int [] cellAlign2={0,2,2,2,2};
			
			TableDataSet tabletaxTotal2 = new TableDataSet();
			tabletaxTotal2.setData(taxTotal2);
			tabletaxTotal2.setCellWidth(cellWidth2);
			tabletaxTotal2.setCellAlignment(cellAlign2);
			tabletaxTotal2.setBorder(true);
			rg.addTableToDoc(tabletaxTotal2);
			
			//=================Note table=====================//
			Object noteDetails[][]= new Object[2][1];//new-------------->
		 	noteDetails[0][0] ="Note: 1.aggregate amount deductible under section 80C shall not exceed one lakh rupees";
		 	noteDetails[1][0] ="2.aggregate amount deductible under sections, i.e., 80C,80CCC and 80CCD,shall not exceed one lakh rupees";
		 	int [] cellWidthNoteDtls={100};
			int [] cellAlignNoteDtls={0};
			
			TableDataSet tablenoteDetails = new TableDataSet();
			tablenoteDetails.setData(noteDetails);
			tablenoteDetails.setCellWidth(cellWidthNoteDtls);
			tablenoteDetails.setCellAlignment(cellAlignNoteDtls);
			tablenoteDetails.setBorder(false);
			rg.addTableToDoc(tablenoteDetails);
			
			//==============taxTotal Other===================//
			Object[][] otherSec = null;
			otherSec = (Object[][])taxComputation.getOtherSecDataMap().get(empLength[i]);
			double invAmt80D=0,invAmt80E=0,invAmt80G=0,invAmt80DD=0,invAmt80U=0,invAmt80CCF=0;
			/*if((otherSec.length> 0)){
				invAmt80D=0;
				invAmt80E=0;
				invAmt80G=0;
			} //end of if
*/			if(otherSec == null){
				
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
			
//logger.info("invAmt80E=="+invAmt80E);
			Object taxTotalOther[][]= new Object[1][5];//new-------------->
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
			taxTotalOther[0][4] ="";
			int [] cellWidthOther={35,15,15,15,20};
			int [] cellAlignOther={0,2,2,2,2};
			TableDataSet tabletaxTotalOther = new TableDataSet();
			tabletaxTotalOther.setData(taxTotalOther);
			tabletaxTotalOther.setCellWidth(cellWidthOther);
			tabletaxTotalOther.setCellAlignment(cellAlignOther);
			tabletaxTotalOther.setBorder(true);
			rg.addTableToDoc(tabletaxTotalOther);
			
			//=============Challan Queries===========================//
			 Object[][] challanDataMarDec = null;
			 challanDataMarDec = (Object[][])taxComputation.getChallanDataAprToDecMap().get(empLength[i]);
			 Object[][] challanDataJanMar = null;
			 challanDataJanMar = (Object[][])taxComputation.getChallanDataJanToMarMap().get(empLength[i]);
			 double finalTotTaxAmt=0,finalTotTaxAmt1=0,finalAmt=0;
			 int rowCount=0;
			 
			 if(challanDataMarDec!=null && challanDataMarDec.length >0){
					try {
						for (int j = 0; j < challanDataMarDec.length; j++) {
							finalTotTaxAmt += Double.parseDouble(String.valueOf(challanDataMarDec[j][4]));
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
							finalTotTaxAmt1 += Double.parseDouble(String.valueOf(challanDataJanMar[j][4]));
							challanDataJanMar[j][0] = rowCount + j + 1;
						} //end of loop
					} catch (Exception e) {
						logger.error("exception in challanDataJanMar loop",e);
					} //end of catch
			} //end of if 
			finalAmt = finalTotTaxAmt + finalTotTaxAmt1;
				
			//=============aggregate table details====================//
			Object taxTotal3[][]= new Object[10][4];//new------------->
			taxTotal3[0][0]="10.Aggregate of deductible amount under Chapter VI-A";
			taxTotal3[0][1]="";
			taxTotal3[0][2]="";
			taxTotal3[0][3]=""+(Math.round(deductibleAmt) + Math.round(invAmt80D) + Math.round(invAmt80E) + Math.round(invAmt80DD) + Math.round(invAmt80U)+ Math.round(invAmt80CCF) +
					Math.round(invAmt80G) + Math.round(cccAmt) + Math.round(ccdAmt))+"";
			//logger.info("String.valueOf(taxTotal1[1][3])===="+String.valueOf(taxTotal2[7][3]));
			//logger.info("String.valueOf(taxTotal3[0][3])===="+String.valueOf(taxTotal3[0][3]));
			taxTotal3[1][0]="11.TOTAL INCOME (R/o)(8-10)";
			taxTotal3[1][1]="";
			taxTotal3[1][2]="";
			
			double z = Math.round(Double.parseDouble(String.valueOf(taxTotal2[7][3])) - Double.parseDouble(String.valueOf(taxTotal3[0][3])));
			double mod=Math.round(Double.parseDouble(String.valueOf(taxTotal2[7][3])) - Double.parseDouble(String.valueOf(taxTotal3[0][3])))%10;
			
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
			
			taxTotal3[3][0]="13.Surcharge(on tax computed at S.No.12)";
			taxTotal3[3][1]="";
			taxTotal3[3][2]="";
			taxTotal3[3][3]=""+taxData[0][9]+"";
			
			taxTotal3[4][0]="14.Education Cess(on tax at S.No.12 and Surcharge at S.No.13)";
			taxTotal3[4][1]="";
			taxTotal3[4][2]="";
			taxTotal3[4][3]=""+taxData[0][8]+"";
			
			taxTotal3[5][0]="15.TAX PAYABLE (12+13+14)";
			taxTotal3[5][1]="";
			taxTotal3[5][2]="";
			taxTotal3[5][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal3[2][3])) + Double.parseDouble(String.valueOf(taxTotal3[3][3])) + Double.parseDouble(String.valueOf(taxTotal3[4][3]))));
			
			taxTotal3[6][0]="16.Relief Under section 89(attach details)";
			taxTotal3[6][1]="";
			taxTotal3[6][2]="";
			taxTotal3[6][3]="NIL";

			taxTotal3[7][0]="17.TAX PAYABLE(15-16)";
			taxTotal3[7][1]="";
			taxTotal3[7][2]="";
			taxTotal3[7][3]=""+String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal3[5][3]))))+"";
			
			taxTotal3[8][0]="18.LESS: (a)TAX DEDUCTED AT SOURCE U/S 192(1).\n" +
			"(b) Tax Paid By The Employer On Behalf Of The Employee U/S 192(A) On Perquisites U/S 17(2).";
			taxTotal3[8][1]="";
			taxTotal3[8][2]=""+String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxData[0][12]))))+"\n\nNIL";
			taxTotal3[8][3]="\n\n\n"+String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxData[0][12]))))+"";
			
			taxTotal3[9][0]="19. TAX PAYABLE/REFUNDABLE (17-18)";
			taxTotal3[9][1]="";
			taxTotal3[9][2]="";
			taxTotal3[9][3]=String.valueOf(Math.round(Double.parseDouble(String.valueOf(taxTotal3[7][3])) - Double.parseDouble(String.valueOf(taxTotal3[8][3]))));

		    int [] cellWidth3={50,15,15,20};
			int [] cellAlign3={0,2,2,2};
			
			TableDataSet tabletaxTotal3 = new TableDataSet();
			tabletaxTotal3.setData(taxTotal3);
			tabletaxTotal3.setCellWidth(cellWidth3);
			tabletaxTotal3.setCellAlignment(cellAlign3);
			tabletaxTotal3.setBorder(true);
			rg.addTableToDoc(tabletaxTotal3);
			
			/*
			 * PART B Hidded
			 * 
			 */
			//=============Details of tax deducted====================//
			/*Object detailsOfTaxDeducted[][]= new Object[2][1];//new-------------->
		 	detailsOfTaxDeducted[0][0] ="Part B";
		 	detailsOfTaxDeducted[1][0] ="Statment Of Actual Tax Deducted";
		 	int [] cellDetailsTaxDeducted={100};
			int [] cellAlignCellDetailsTaxDeducted={0};
			
			TableDataSet tableTaxDeducted = new TableDataSet();
			tableTaxDeducted.setData(detailsOfTaxDeducted);
			tableTaxDeducted.setCellWidth(cellDetailsTaxDeducted);
			tableTaxDeducted.setCellAlignment(cellAlignCellDetailsTaxDeducted);
			tableTaxDeducted.setBorder(false);
			tableTaxDeducted.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD, new Color(0,0,0));
			rg.addTableToDoc(tableTaxDeducted);
			
			//=============tax challan header===================//
			Object[][] taxChallan= new Object[1][9];//new------------->
		 	taxChallan[0][0]="Sr No";
		 	taxChallan[0][1]="TDS (Rs.)";
			taxChallan[0][2]="Surcharge (Rs.)";
			taxChallan[0][3]="Education Cess (Rs.)";
			taxChallan[0][4]="Total Tax Deposited (Rs.)";
			taxChallan[0][5]="Cheque/DD No.";
			taxChallan[0][6]="BSR Code of Bank branch";
			taxChallan[0][7]="Date on Which Tax deposited";
			taxChallan[0][8]="Challan Identification No";
			int [] cellWidthChallan={5,10,15,15,15,15,20,20,20};
			int [] cellAlignChallan={1,1,1,1,1,1,1,1,1};
			 
			TableDataSet tabletaxChallan = new TableDataSet();
			tabletaxChallan.setData(taxChallan);
			tabletaxChallan.setCellWidth(cellWidthChallan);
			tabletaxChallan.setCellAlignment(cellAlignChallan);
			tabletaxChallan.setBorder(true);
			rg.addTableToDoc(tabletaxChallan);
			
			//=====================tax challan details==================//
			if(challanDataMarDec!=null && challanDataMarDec.length >0){
				int [] cellWidthAprToDec={5,10,15,15,15,15,20,20,20};
				int [] cellAlignAprToDec={2,2,2,2,2,1,2,2,2};
				TableDataSet tablechallanDataMarDec = new TableDataSet();
				tablechallanDataMarDec.setData(challanDataMarDec);
				tablechallanDataMarDec.setCellWidth(cellWidthAprToDec);
				tablechallanDataMarDec.setCellAlignment(cellAlignAprToDec);
				tablechallanDataMarDec.setBorder(true);
				//PdfPTable pTablechallanDataMarDec = rg.createTable(tablechallanDataMarDec);
				//rg.addPdfPTableToDoc(pTablechallanDataMarDec);
				rg.addTableToDoc(tablechallanDataMarDec);
			} //end of challanDataMarDec method	
			if(challanDataJanMar!=null && challanDataJanMar.length >0){
				int [] cellWidthJanToMar={5,10,15,15,15,15,20,20,20};
				int [] cellAlignJanToMar={2,2,2,2,2,1,2,2,2};
				TableDataSet tablechallanDataJanMar = new TableDataSet();
				tablechallanDataJanMar.setData(challanDataJanMar);
				tablechallanDataJanMar.setCellWidth(cellWidthJanToMar);
				tablechallanDataJanMar.setCellAlignment(cellAlignJanToMar);
				tablechallanDataJanMar.setBorder(true);
				//PdfPTable pTablechallanDataMarDec = rg.createTable(tablechallanDataJanMar);
				//rg.addPdfPTableToDoc(pTablechallanDataMarDec);
				rg.addTableToDoc(tablechallanDataJanMar);
			} //end of challanDataJanMar method	 
			
			//================tax challan total====================//
			 Object[][] taxChallanTotal= new Object[1][9];
			 taxChallanTotal[0][0]="";
			 taxChallanTotal[0][1]="";
			 taxChallanTotal[0][2]="";
			 taxChallanTotal[0][3]="TOTAL";
			 taxChallanTotal[0][4]=""+Math.round(finalAmt)+"";
			 taxChallanTotal[0][5]="";
			 taxChallanTotal[0][6]="";
			 taxChallanTotal[0][7]="";
			 taxChallanTotal[0][8]="";
			 
			int [] cellWidthTotal={5,10,15,15,15,15,20,20,20};
			int [] cellAlignTotal={2,2,2,2,2,2,2,2,2};
			TableDataSet tabletaxChallanTotal = new TableDataSet();
			tabletaxChallanTotal.setData(taxChallanTotal);
			tabletaxChallanTotal.setCellWidth(cellWidthTotal);
			tabletaxChallanTotal.setCellAlignment(cellAlignTotal);
			tabletaxChallanTotal.setBorder(true);
			rg.addTableToDoc(tabletaxChallanTotal);*/
			
			//===========i here by data================//
			/* String abc = String.valueOf(finalAmt);
				String []xyz = abc.replace('.', '#').split("#");
				
				int zz=0;
				try {
					for (int j = 0; j < xyz.length; j++) {
					} //end of loop
					zz = Integer.parseInt(xyz[0]);
				} catch (Exception e) {
					logger.error("exception in xyz loop",e);
				} //end of catch
				
				Object[][]iHerebyData = new Object[3][1];
				iHerebyData[0][0] = "I "+taxComputation.getSignAuthName()+" son/daughter of "+taxComputation.getSignAuthEmpFather()+" working in the capacity of" +
						" "+taxComputation.getSignAuthEmpDesg()+"(designation) do hereby certify that a sum of Rs."+finalAmt+"/- [Rupees "+new Utility().convert(zz)+" (in words)]" +
						" only has been deducted at source and paid to the credit of the Central Government. I " +
						" further certify that the information given above is true and correct based on the" +
						" book of accounts, documents and other available records.";
				iHerebyData[1][0] ="";
				iHerebyData[2][0] ="";
				int [] cellWidthIHereBy={100};
				int [] cellAlignIHereBy={0};
				TableDataSet tableiHerebyData = new TableDataSet();
				tableiHerebyData.setData(iHerebyData);
				tableiHerebyData.setCellWidth(cellWidthIHereBy);
				tableiHerebyData.setCellAlignment(cellAlignIHereBy);
				tableiHerebyData.setBorder(false);
				tableiHerebyData.setBlankRowsAbove(1);
				rg.addTableToDoc(tableiHerebyData);
				
				Object[][]placeDateData = new Object[2][1];
				placeDateData[0][0] = "Place : "+empDetailsMap[0][5]+"";
				placeDateData[1][0] = "Date : "+empDetailsMap[0][7]+"";
				int [] cellWidthPlaceDate={100};
				int [] cellAlignPlaceDate={0};
				TableDataSet tableplaceDateData = new TableDataSet();
				tableplaceDateData.setData(placeDateData);
				tableplaceDateData.setCellWidth(cellWidthPlaceDate);
				tableplaceDateData.setCellAlignment(cellAlignPlaceDate);
				tableplaceDateData.setBorder(false);
				tableplaceDateData.setBlankRowsBelow(1);
				rg.addTableToDoc(tableplaceDateData);
				
				Object[][]signData = new Object[2][1];
				signData[0][0] = "____________________________________________";
				signData[1][0] = "Signature of the person responsible for deduction of tax\n Full Name  "+taxComputation.getSignAuthName()+"\n  Designation  "+taxComputation.getSignAuthEmpDesg()+"";
				int [] cellWidthSignData={100};
				int [] cellAlignSignData={2};
				TableDataSet tablesignData = new TableDataSet();
				tablesignData.setData(signData);
				tablesignData.setCellWidth(cellWidthSignData);
				tablesignData.setCellAlignment(cellAlignSignData);
				tablesignData.setBorder(false);
				rg.addTableToDoc(tablesignData);*/
			//======================End of employee details=====================================//
			} //end of else
			rg.addProperty(rg.PAGE_BREAK);
			
		} //end of main loop
			rg.process();
			rg.createReport(response);
	} //end of createReport method

	private TaxComputationMisReport loadEmployeeInformation(String empIdList,TaxComputationMisReport employeeBean) {
		//TaxComputationMisReport employeeBean = new TaxComputationMisReport();
		employeeBean.setEmpOfficialDataMap(getEmpOffcialData(empIdList,employeeBean));
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
		return employeeBean;
	} //end of loadEmployeeInformation method

	private HashMap getChallanDataJanToMar(String empIdList,TaxComputationMisReport taxComputation) {
		Object challanJanToMarData[][] = null;
		try {
			String challanJanToMarQuery = getQuery(12, taxComputation, empIdList);
			challanJanToMarData = getSqlModel().getSingleResult(challanJanToMarQuery);
		} catch (Exception e) {
			logger.error("exception in perquisite data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "11";
		HashMap mapData = convertObjectToHashMap(challanJanToMarData, empLength,objectLength);
		return mapData;
	} //end of getChallanDataJanToMar method

	private HashMap getChallanDataAprToDec(String empIdList,TaxComputationMisReport taxComputation) {
		Object challanAprToDecData[][] = null;
		try {
			String challanAprToDecQuery = getQuery(11, taxComputation, empIdList);
			challanAprToDecData = getSqlModel().getSingleResult(challanAprToDecQuery);
		} catch (Exception e) {
			logger.error("exception in perquisite data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "11";
		HashMap mapData = convertObjectToHashMap(challanAprToDecData, empLength,objectLength);
		return mapData;
	} //end of getChallanDataAprToDec method

	private HashMap getOtherSectionData(String empIdList,TaxComputationMisReport taxComputation) {
		Object otherSec[][] = null;
		try {
			String otherSecQuery = getQuery(10, taxComputation, empIdList);
			otherSec = getSqlModel().getSingleResult(otherSecQuery);
		} catch (Exception e) {
			logger.error("exception in perquisite data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "3";
		HashMap mapData = convertObjectToHashMap(otherSec, empLength,objectLength);
		return mapData;
	} //end of getOtherSectionData method

	private HashMap getInvestmentDeducData(String empIdList,TaxComputationMisReport taxComputation) {
		Object invDeducAmt[][] = null;
		try {
			String invDeducAmtQuery = getQuery(9, taxComputation, empIdList);
			invDeducAmt = getSqlModel().getSingleResult(invDeducAmtQuery);
		} catch (Exception e) {
			logger.error("exception in perquisite data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "3";
		HashMap mapData = convertObjectToHashMap(invDeducAmt, empLength,objectLength);
		return mapData;
	} //end of getInvestmentDeducData method

	private HashMap getEmpInvetmentHraData(String empIdList,TaxComputationMisReport taxComputation) {
		Object empInvHraData[][] = null;
		if (taxParameter != null && taxParameter.length > 0) {
			try {
				String exemptInvQuery = getQuery(8, taxComputation, empIdList);
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
	 * @param taxComputation
	 * @return
	 */
	private HashMap getExemptInvestmentData(String empIdList,TaxComputationMisReport taxComputation) {
		Object exemptInvData[][] = null;
		try {
			String exemptInvQuery = getQuery(7, taxComputation, empIdList);
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
	private HashMap getOtherIncomeData(String empIdList,TaxComputationMisReport taxComputation) {
		Object otherIncomeData[][] = null;
		try {
			String otherIncomeQuery = getQuery(6, taxComputation, empIdList);
			otherIncomeData = getSqlModel().getSingleResult(otherIncomeQuery);
		} catch (Exception e) {
			logger.error("exception in perquisite data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "5";
		HashMap mapData = convertObjectToHashMap(otherIncomeData, empLength,objectLength);
		return mapData;
	} //end of getOtherIncomeData method

	private HashMap getPerquisiteData(String empIdList,TaxComputationMisReport taxComputation) {
		Object perqData[][] = null;
		try {
			String perquisiteQuery = getQuery(5, taxComputation, empIdList);
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
	private HashMap getTaxSalaryData(String empIdList,TaxComputationMisReport taxComputation) {
		Object taxData[][] = null;
		try {
			String taxDataQuery = getQuery(4, taxComputation, empIdList);
			taxData = getSqlModel().getSingleResult(taxDataQuery);
		} catch (Exception e) {
			logger.error("exception in tax data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "15";
		HashMap mapData = convertObjectToHashMap(taxData, empLength,objectLength);
		return mapData;
	} //end of getTaxSalaryData method

	/**
	 * this method is used to set the settlement data.
	 * @param empIdList
	 * @param employeeBean
	 * @return
	 */
	private HashMap getSettlementData(String empIdList,TaxComputationMisReport taxComputation) {
		Object settleData[][] = null;
		try {
			String settleQuery = getQuery(3, taxComputation, empIdList);
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
	 * @param taxComputation
	 * @return
	 */
	private HashMap getEmpOffcialData(String empIdList, TaxComputationMisReport taxComputation) {
		Object employeesOffData[][] = null;
		
		try {
			String empOffcQuery = getQuery(2, taxComputation, empIdList);
			employeesOffData = getSqlModel().getSingleResult(empOffcQuery);
		} catch (Exception e) {
			logger.error("exception in empOfficial data in map",e);
		} //end of catch
		String[] empLength = empIdList.split(",");
		String objectLength = "11";
		HashMap mapData = convertObjectToHashMap(employeesOffData, empLength,objectLength);
		
		return mapData;
	} //end of getEmpOffcialData method

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
	
	public String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "";
		}
		else{
			return result;
		}
	}

	public void setDetailsForGeneralUser(TaxComputationMisReport taxComputation) {
		Object[][]userData = null;
		try {
			String query = "SELECT EMP_ID,EMP_TOKEN, EMP_FNAME||' '||EMP_LNAME, HRMS_EMP_OFFC.EMP_DIV, NVL(HRMS_DIVISION.DIV_NAME,' ') FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV)"
				+ " WHERE EMP_ID = "+ taxComputation.getUserEmpId() + "";
			userData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in userData",e);
		} //end of catch
		if(userData !=null && userData.length > 0){
			taxComputation.setEmpId(String.valueOf(userData[0][0]));
			taxComputation.setEmpToken(String.valueOf(userData[0][1]));
			taxComputation.setEmpName(String.valueOf(userData[0][2]));
			taxComputation.setDivisionId(String.valueOf(userData[0][3]));
			taxComputation.setDivisionName(String.valueOf(userData[0][4]));
		} //end of if
	} //end of setDetailsForGeneralUser method

}
