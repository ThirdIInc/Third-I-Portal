package org.paradyne.model.payroll.salary;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.payroll.salary.PayBillwiseBankReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
public class PayBillwiseBankReportModel extends ModelBase {
	NumberFormat formatter = new DecimalFormat("#0.00");
	public void report(HttpServletRequest request, HttpServletResponse response, PayBillwiseBankReport bean) {
		String title ="BANKING SUMMARY FOR MONTH OF "+Utility.month(Integer.parseInt(bean.getMonth())).toUpperCase()+"-"+bean.getYear();
		ReportDataSet rds = new ReportDataSet();
		rds.setFileName("PaybillWiseBankSummaryReport");
		rds.setReportName("Salary MIS Report");
		rds.setReportType("Pdf");
		org.paradyne.lib.ireport.ReportGenerator rg=new org.paradyne.lib.ireport.ReportGenerator(rds);
		/*TableDataSet titleName = new TableDataSet();
		Object[][] nameObj = new Object[1][3];
		nameObj[0][0] = "";
		nameObj[0][1] = "Arrears are not Processed.";
		nameObj[0][2] = "";
		titleName.setData(nameObj);
		titleName.setCellAlignment(new int[] { 1, 1, 1 });
		titleName.setCellWidth(new int[] { 20, 70, 10 });
		titleName.setBodyFontDetails(Font.HELVETICA, 8,
				Font.BOLD, new Color(0, 0, 0));
		titleName.setBorder(false);
		//PdfPTable nameTable0 = rg.createTable(titleName);
		//rg.addPdfPTableToDoc(nameTable0);
		rg.addTableToDoc(titleName);*/
		

		
		String divisionName = "", divisionAddress = "";
		String divDetailsQury="";

		divDetailsQury = "SELECT DIV_NAME,NVL(DIV_ABBR,''),nvl(DIV_ADDRESS1,''),nvl(DIV_ADDRESS2,''),nvl(DIV_ADDRESS3,''),"
				+ " NVL(DIV_TELEPHONE,''),NVL(DIV_WEBSITE,''),NVL(DIV_SIGN_LOGO,' ')  FROM HRMS_DIVISION where DIV_ID="
				+ bean.getDivisionId();
	
		Object divisionDtl[][] = getSqlModel().getSingleResult(
				divDetailsQury);

		if (divisionDtl != null && divisionDtl.length > 0) {
			divisionName = String.valueOf(divisionDtl[0][0]);
			divisionAddress = String.valueOf(checkNull(""+ divisionDtl[0][2]))+ "\n"
					+ String.valueOf(checkNull("" + divisionDtl[0][3]))
					+ String.valueOf(checkNull("" + divisionDtl[0][4]));
		}
		
		Object[][] nameObj = new Object[1][1];
		boolean isLogo = false;
		try {
			//String divLogoQuery="select DIV_ID,NVL(DIV_LOGO,'') from HRMS_DIVISION WHERE DIV_ID="+bean.getDivisionId();
			Object logoObj[][]=null;
			String cmpnyLogoQuery = "select COMPANY_CODE,NVL(COMPANY_LOGO,' ') from HRMS_COMPANY";
			logoObj = getSqlModel().getSingleResult(cmpnyLogoQuery);				
			
			if (logoObj != null && logoObj.length > 0) {
				String filename = "";
				if (!String.valueOf(logoObj[0][1]).equals(" ")) {
					filename = String.valueOf(logoObj[0][1]);
					String filePath = context.getRealPath("/")
							+ "pages/Logo/"
							+ session.getAttribute("session_pool") + "/"
							+ filename;
					nameObj[0][0] = Image.getInstance(filePath);
					isLogo = true;

				} else
					nameObj[0][0] = "";

			} else {
				nameObj[0][0] = "";
			}
			
		} catch (Exception eee) {
			nameObj[0][0] = " ";
		}
	
		
		
		TableDataSet logoData = new TableDataSet();
		logoData.setData(nameObj);
		logoData.setCellAlignment(new int[] { 0 });
		logoData.setCellWidth(new int[] { 100 });
		logoData.setBorder(false);
		Object[][] titleObj = new Object[1][1];
		
		if(!bean.getBranchName().equals("")){
			divisionName+="\n"+bean.getBranchName();
		}
		if(!bean.getPaybillName().equals("")){
			divisionName+="\n"+bean.getPaybillName();
		}
		titleObj[0][0] = "" + divisionName;

		TableDataSet titleName = new TableDataSet();
		titleName.setData(titleObj);
		titleName.setCellAlignment(new int[] { 1 });
		titleName.setCellWidth(new int[] { 100 });
		titleName.setBodyFontDetails(Font.HELVETICA, 12, Font.BOLD,
				new Color(0, 0, 0));
		titleName.setBorder(false);

		Object[][] subtitleObj = new Object[1][1];
		subtitleObj[0][0] = "" ;

		TableDataSet subtitleName = new TableDataSet();
		subtitleName.setData(subtitleObj);
		subtitleName.setCellAlignment(new int[] { 1 });
		subtitleName.setCellWidth(new int[] { 100 });
		subtitleName.setBodyFontDetails(Font.HELVETICA, 8, Font.BOLD,
				new Color(0, 0, 0));
		subtitleName.setBorder(false);

		Object[][] subtitleObj2 = new Object[1][1];
		subtitleObj2[0][0] = "" + title + " " ;

		TableDataSet subtitleName2 = new TableDataSet();
		subtitleName2.setData(subtitleObj2);
		subtitleName2.setCellAlignment(new int[] { 1 });
		subtitleName2.setCellWidth(new int[] { 100 });
		subtitleName2.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD,new Color(0, 0, 0));
		subtitleName2.setBorder(false);

		HashMap<String, Object> mapOne = rg.joinTableDataSet(titleName,subtitleName, false, 0);

		HashMap<String, Object> mapTwo = rg.joinTableDataSet(mapOne,subtitleName2, false, 0);
		HashMap<String, Object> mapThree = null;
		if (isLogo)
			mapThree = rg.joinTableDataSet(logoData, mapTwo, true, 30);
		else
			mapThree = rg.joinTableDataSet(logoData, mapTwo, true, 5);

		rg.addTableToDoc(mapThree);
		String year=bean.getYear();
		String month=bean.getMonth();
		String divisionId=bean.getDivisionId();
		
		String paybillQuery="SELECT DISTINCT HRMS_PAYBILL.PAYBILL_ID,NVL(PAYBILL_GROUP,' ')  "
			+"	FROM HRMS_SALARY_"+year+"  "
			+"	INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_"+year+".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
			+"	INNER JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID=HRMS_EMP_OFFC.EMP_PAYBILL)"
			+"	INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+year+".SAL_LEDGER_CODE) "
			+"	WHERE  LEDGER_MONTH="+month+" AND LEDGER_year="+year+" AND SAL_NET_SALARY >0 AND LEDGER_DIVISION="+divisionId+"  ";

		if(!bean.getPaybillId().equals("")){
			paybillQuery+= " AND HRMS_EMP_OFFC.EMP_PAYBILL= "+bean.getPaybillId();
		}
		
		
		
		
				
		int payBillCount=0;
		int bankCount=0;
	/*	String paybillQUery="SELECT PAYBILL_ID,NVL(PAYBILL_GROUP,' ') FROM HRMS_PAYBILL " ;
		if(!bean.getPaybillId().equals("")){
			paybillQUery+= " WHERE PAYBILL_ID= "+bean.getPaybillId();
		}
		paybillQUery+=" ORDER BY PAYBILL_ID";*/
		Object[][]paybillObj=getSqlModel().getSingleResult(paybillQuery);
		if(paybillObj!=null && paybillObj.length>0){
			payBillCount=paybillObj.length;
		}
		
		String bankQuery="SELECT  DISTINCT BANK_MICR_CODE,BANK_NAME,BRANCH_NAME FROM HRMS_BANK  INNER JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.SAL_MICR_REGULAR = HRMS_BANK.BANK_MICR_CODE)  ORDER BY BANK_MICR_CODE";
		Object[][]bankObj=getSqlModel().getSingleResult(bankQuery);
		if(bankObj!=null && bankObj.length>0){
			bankCount=bankObj.length;
		}
		Object[][]obj= null;
		int[] width=null;
		int[] alignment=null;
		if(payBillCount>0 &&bankCount>0 ){
			obj=new Object[payBillCount+2][bankCount+3];
			width=new int [bankCount+3];
			alignment=new int[bankCount+3];
		}
	
		String query="SELECT SUM(NVL(SAL_NET_SALARY,0)),HRMS_PAYBILL.PAYBILL_ID||'#'||HRMS_BANK.BANK_MICR_CODE "
					+"	FROM HRMS_SALARY_"+year+"  "
					+"	INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_"+year+".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
					+"	INNER JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID=HRMS_EMP_OFFC.EMP_PAYBILL)"
					+"	INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+year+".SAL_LEDGER_CODE) "
					+"	INNER join HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
					+"	INNER join HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
					+"	WHERE  LEDGER_MONTH="+month+" AND LEDGER_year="+year+" AND SAL_NET_SALARY >0 AND LEDGER_DIVISION="+divisionId+"  ";
					
		if(!bean.getBranchId().equals("")){
			query+= " AND HRMS_EMP_OFFC.EMP_CENTER= "+bean.getBranchId();
		}
		if(!bean.getPaybillId().equals("")){
			query+= " AND HRMS_EMP_OFFC.EMP_PAYBILL= "+bean.getPaybillId();
		}
		
		query+= "	  GROUP BY HRMS_PAYBILL.PAYBILL_ID||'#'||HRMS_BANK.BANK_MICR_CODE ";
		Object[][]	salObj=getSqlModel().getSingleResult(query);
		HashMap<String, String>map=new HashMap<String, String>();
		if(salObj!=null && salObj.length>0){
			for (int i = 0; i < salObj.length; i++) {
				map.put(String.valueOf(salObj[i][1]), String.valueOf(salObj[i][0]));
			}
		}

	/**
	 * FOR CASH AMOUNT
	 */
	String cashSumquery="SELECT COUNT(*),SUM(NVL(SAL_NET_SALARY,0)),HRMS_PAYBILL.PAYBILL_ID "
		+"	FROM HRMS_SALARY_"+year+"  "
		+"	INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_"+year+".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
		+"	INNER JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID=HRMS_EMP_OFFC.EMP_PAYBILL)"
		+"	INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+year+".SAL_LEDGER_CODE) "
		+"	INNER join HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "		
		+"	WHERE  LEDGER_MONTH="+month+" AND SAL_PAY_MODE='C' AND  LEDGER_year="+year+" AND SAL_NET_SALARY >0 AND LEDGER_DIVISION="+divisionId+"  ";
	if(!bean.getBranchId().equals("")){
		cashSumquery+= " AND HRMS_EMP_OFFC.EMP_CENTER= "+bean.getBranchId();
	}
	if(!bean.getPaybillId().equals("")){
		cashSumquery+= " AND HRMS_EMP_OFFC.EMP_PAYBILL= "+bean.getPaybillId();
	}
	cashSumquery+= "	  GROUP BY HRMS_PAYBILL.PAYBILL_ID";
	Object[][]	cashSumObj=getSqlModel().getSingleResult(cashSumquery);
	HashMap<String, Object[][]>cashSummap=new HashMap<String, Object[][]>();
	if(cashSumObj!=null && cashSumObj.length>0){
		for (int i = 0; i < cashSumObj.length; i++) {
			Object[][]data=new Object[1][2];
			data[0][0]=String.valueOf(cashSumObj[i][0]);
			data[0][1]=String.valueOf(cashSumObj[i][1]);
			cashSummap.put(String.valueOf(cashSumObj[i][2]), data);
		}
	}
		
		
		/**
		 * EMPLOYEE COUNT
		 */
		String empCountquery="SELECT COUNT(*),HRMS_PAYBILL.PAYBILL_ID||'#'||HRMS_BANK.BANK_MICR_CODE "
			+"	FROM HRMS_SALARY_"+year+"  "
			+"	INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_"+year+".EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
			+"	INNER JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID=HRMS_EMP_OFFC.EMP_PAYBILL)"
			+"	INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+year+".SAL_LEDGER_CODE) "
			+"	INNER join HRMS_SALARY_MISC ON (HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID) "
			+"	INNER join HRMS_BANK ON (HRMS_BANK.BANK_MICR_CODE = HRMS_SALARY_MISC.SAL_MICR_REGULAR) "
			+"	WHERE  LEDGER_MONTH="+month+" AND LEDGER_year="+year+"AND SAL_NET_SALARY >0 AND LEDGER_DIVISION="+divisionId+" ";
			if(!bean.getBranchId().equals("")){
				empCountquery+= " AND HRMS_EMP_OFFC.EMP_CENTER= "+bean.getBranchId();
			}
			if(!bean.getPaybillId().equals("")){
				empCountquery+= " AND HRMS_EMP_OFFC.EMP_PAYBILL= "+bean.getPaybillId();
			}
			
			empCountquery+= " 	  GROUP BY HRMS_PAYBILL.PAYBILL_ID||'#'||HRMS_BANK.BANK_MICR_CODE ";
		Object[][]	empCOuntObj=getSqlModel().getSingleResult(empCountquery);
		HashMap<String, String>empCountmap=new HashMap<String, String>();
		if(empCOuntObj!=null && empCOuntObj.length>0){
			for (int i = 0; i < empCOuntObj.length; i++) {
				empCountmap.put(String.valueOf(empCOuntObj[i][1]), String.valueOf(empCOuntObj[i][0]));
			}
		}
		for (int i = 0; i < payBillCount+1; i++) {
			int bank=2;
			int sumEmpCount=0;
			double sumEmpValue=0.0;
			
			
			for (int k = 0; k < bankCount; k++) {
				if(i==0){
					obj[i][0]="PAYBILL NAMES";	
					obj[i][1]="CASH";
					obj[i][bank]=bankObj[k][1];
					obj[i][bankCount+2]="Total";
					width[0]=20;
					width[1]=20;
					width[bank]=20;
					width[bankCount+2]=20;
					alignment[0]=0;
					alignment[1]=2;
					alignment[bank]=2;
					alignment[bankCount+2]=2;
					bank++;
				}
				else{
					String key=String.valueOf(paybillObj[i-1][0])+"#"+String.valueOf(bankObj[k][0]);
					obj[i][0]=String.valueOf(paybillObj[i-1][1]);	
					obj[i][1]="0\n0";	//CASH
					
					int cashempcount=0;
					double cashsumcount=0;
					if(cashSummap!=null && cashSummap.size()>0){
						Object[][]cashobj=cashSummap.get(String.valueOf(paybillObj[i-1][0]));
						if(cashobj!=null && cashobj.length>0){
							obj[i][1]=cashobj[0][0]+"\n"+cashobj[0][1];
							 cashempcount=Integer.parseInt(String.valueOf(cashobj[0][0]));
							 cashsumcount=Double.parseDouble(String.valueOf(cashobj[0][1]));
						}
					}
					
					//////////////
					
					
					obj[i][bank]="0";
					String empData="0";
					if(empCountmap!=null && empCountmap.size()>0){
						String value=empCountmap.get(key);
						if(value!=null && value.length()>0){
							empData=value;
							obj[i][bank]=value;
							sumEmpCount+=Integer.parseInt(empData);
						}
					}
					String empValue="0";
					if(map!=null && map.size()>0){
						String value=map.get(key);
						if(value!=null && value.length()>0){
							empValue=value;
							sumEmpValue+=Double.parseDouble(empValue);
						}
					}
					obj[i][bank]=empData+"\n"+formatter.format(Double.parseDouble(empValue));
					
					String horizaTotal=String.valueOf(obj[payBillCount+1][bank]);
					if(horizaTotal.contains("\n")){
						String str[]=horizaTotal.split("\n");
						int empCountValue=Integer.parseInt(str[0]);
						double empSumValue=Double.parseDouble(str[1]);
						
						obj[payBillCount+1][bank]=(Integer.parseInt(empData)+empCountValue)+"\n"+(formatter.format(Double.parseDouble(empValue)+empSumValue));	
						
					}else{
						obj[payBillCount+1][bank]=empData+"\n"+formatter.format(Double.parseDouble(empValue));
					}
					
					//obj[payBillCount][bank]="Total";
					width[0]=20;
					width[1]=20;
					width[bank]=20;
					alignment[0]=0;
					alignment[1]=2;
					alignment[bank]=2;
					bank++;
				}
				
				
			}
			
			/**
			 * SET TOTAL
			 */
			if(i==0){
			
			obj[payBillCount+1][0]="Total";	
			width[bankCount+1]=20;
			alignment[bankCount+1]=2;			
			}
			else{
				obj[i][bankCount+2]=sumEmpCount+"\n"+formatter.format(sumEmpValue);
				String horizaTotal=String.valueOf(obj[payBillCount+1][bankCount+2]);
				if(horizaTotal.contains("\n")){
					String str[]=horizaTotal.split("\n");
					int empCountValue=Integer.parseInt(str[0]);
					double empSumValue=Double.parseDouble(str[1]);
					
					obj[payBillCount+1][bankCount+2]=((sumEmpCount)+empCountValue)+"\n"+(formatter.format(sumEmpValue+empSumValue));	
					
				}else{
					obj[payBillCount+1][bankCount+2]=sumEmpCount+"\n"+formatter.format(sumEmpValue);
				}
			}
		}
		
		/**
		 * SET TOTAL CASH
		 * obj=new Object[payBillCount+2][bankCount+3];
		 */
		int cashempcount=0;
		double cashsumcount=0.0;
		
		
		for (int j = 1; j < obj.length-1; j++) {
			
			/**
			 * TOTAL CASH SUM
			 */
			int EMPCOUNT=0;
			double AMOUNTSUM=0.0;
			
			String strValue=String.valueOf(obj[j][1]);
			if(strValue.contains("\n")){
				String str[]=strValue.split("\n");
				cashempcount+=Integer.parseInt(str[0]);
				cashsumcount+=Double.parseDouble(str[1]);
			}			
			/*int count=1;
			
			for (int k = 0; k < bankCount; k++) {
				String strValue1=String.valueOf(obj[j][count]);
				if(strValue1.contains("\n")){
					String str[]=strValue1.split("\n");
					EMPCOUNT+=Integer.parseInt(str[0]);
					AMOUNTSUM+=Double.parseDouble(str[1]);
				}
				count++;
			}
			obj[payBillCount+1][bankCount+2]=((EMPCOUNT))+"\n"+((formatter.format(AMOUNTSUM)));*/
			
		}
		
		obj[payBillCount+1][1]=((cashempcount))+"\n"+((formatter.format(cashsumcount)));	
		
		
		//obj[payBillCount+1][bankCount+2]
		
		TableDataSet totalTable = new TableDataSet();
		totalTable.setData(obj);
		totalTable.setCellAlignment(alignment);
		totalTable.setCellWidth(width);
		totalTable.setBorder(true);
		totalTable.setBlankRowsAbove(1);
		rg.addTableToDoc(totalTable);
		
		
		/**
		 * SET SIGNATURE
		 */
	
		Object[][] signObj = new Object[1][5];
		signObj[0][0] = "__________\n\nCLERK";
		signObj[0][1] = "_______________________\n\nDY. CHIEF ACCOUNTANTS OFFICER";
		signObj[0][2] = "____________________\n\nCHIEF ACCOUNTANTS OFFICER";
		signObj[0][3] = "_________________________\n\nMUNICIPAL CHIEF AUDITOR";
		signObj[0][4] = "_____________________\n\nDY. COMMISSIONER(HQ)";
		TableDataSet signObjData = new TableDataSet();
		signObjData.setData(signObj);
		signObjData.setCellAlignment(new int[] { 0,0,0,0,0 });
		signObjData.setCellWidth(new int[] { 10,20,20,20,20 });	
		signObjData.setBodyFontDetails(Font.HELVETICA, 7,
				Font.NORMAL, new Color(0, 0, 0));
		//signObjData.setBorder(true);
		signObjData.setBlankRowsAbove(2);
		rg.addTableToDoc(signObjData);
		
		rg.process();
		rg.createReport(response);
	}

	
	public String checkNull(String result) {		
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
}
