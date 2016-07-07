package org.paradyne.model.payroll.incometax;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.incometax.*;
import org.paradyne.lib.*;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.TableDataSet;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

/*
 * author:Pradeep and Venkatesh
 * Date:13-10-2008
 */
public class TaxChallanModel extends ModelBase { 
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	NumberFormat formatter = new DecimalFormat("#0.00");
	/**
	 * following function is called to update the challan tds,challan education cess and challan surcharge and challan total tax for every employee
	 * @param empId
	 * @param challanTds
	 * @param challanEducess
	 * @param challanSur
	 * @param challanTotTax
	 * @param challanCode
	 */
	
	public void updateChallanDtl(String[] empId,String[] challanTds,String[] challanEducess,String[] challanSur,String[] challanTotTax,String[] challanCode){
		Object[][] param = null;
		try{
			param=new Object[empId.length][6];
			
			for(int i=0;i<empId.length;i++){
				param[i][0]=challanTds[i];
				param[i][1]=challanSur[i];
				param[i][2]=challanEducess[i];
				param[i][3]=challanTotTax[i];
			//	param[i][4]=dedDate[i];
			//	param[i][5]=depDate[i];
			//	param[i][6]=pay[i];
				param[i][4]=empId[i];
				param[i][5]=challanCode[i];
			} //end of loop
			}catch(Exception e){
				logger.error("exception  in updateChallanDtl empId for loop",e);
			} //end of catch
			 try {
				String query = "UPDATE HRMS_TAX_CHALLAN_DTL SET CHALLAN_TDS=?,CHALLAN_SURCHARGE=?,CHALLAN_EDU_CESS=?,CHALLAN_TOTAL_TAX=?"
						+ " WHERE EMP_ID=? AND CHALLAN_CODE=?";
				getSqlModel().singleExecute(query, param);
			} catch (Exception e) {
				logger.error("exception  in updateChallanDtl query",e);
			} //end of catch
	} //end of method
	
	/**
	 * following function is used to calculate surcharge amt,tds amt and education cess.
	 * @param tax
	 * @param surchargeTax
	 * @param tdsParameter 
	 * @return
	 */
	public Object[][] getTaxDtls(Double totalTax,Double surchargeTax,Double taxableAmt, Object[][] tdsParameter){
		double surCharge=0;
		double edu_cess=0;
		double tds=0;
		Object[][] param=new Object[1][3];
		try{
		if(tdsParameter==null){
			param[0][0]=0;
			param[0][1]=0;
			param[0][2]=0;
		} //end of if
		else if(tdsParameter.length==0){
			param[0][0]=0;
			param[0][1]=0;
			param[0][2]=0;
		} //end of else if
		else{
			//logger.info("taxableAmt====="+taxableAmt);
			//logger.info("surchargeTax====="+surchargeTax);
			if(taxableAmt>surchargeTax){
				tds = totalTax/(1+(Double.parseDouble(String.valueOf(tdsParameter[0][1]))/100)+
						(Double.parseDouble(String.valueOf(tdsParameter[0][3]))/100));
				//logger.info("tds====="+tds);
				surCharge=tds *(Double.parseDouble(String.valueOf(tdsParameter[0][3]))/100);
				//logger.info("surcharge====="+surCharge);
				edu_cess = totalTax - tds - surCharge;
				//logger.info("edu_cess====="+edu_cess);
			} //end of if
			else{
				tds = totalTax/(1+(Double.parseDouble(String.valueOf(tdsParameter[0][1]))/100));
				//logger.info("totalTax==else==="+totalTax);
				//logger.info("tds==else==="+tds);
				surCharge = 0;
				edu_cess = totalTax - tds;
				//logger.info("edu_cess= else===="+edu_cess);
			} //end of else
		} //end of else
		
		param[0][0]=tds;
		param[0][1]=edu_cess;
		param[0][2]=surCharge;
		}catch (Exception e) {
			logger.error("exception in getTaxDtls method",e);
		} //end of catch
		return param;
	} //end of method

	/**
	 * following function is called to generate the ledger code
	 * @param mon
	 * @param year
	 * @return
	 */
	
	public String getLedgerCode(String mon,String year){
		String ledgCode="";
		Object ledgerData[][] = null;
		try {
			String ledgerQuery = "SELECT LEDGER_CODE FROM HRMS_SALARY_LEDGER "
				+"WHERE LEDGER_MONTH=" + mon + " AND LEDGER_YEAR="+ year + " AND LEDGER_STATUS IN('SAL_START','SAL_FINAL')";
			
			ledgerData = getSqlModel().getSingleResult(ledgerQuery);
		
			if(ledgerData != null && ledgerData.length > 0){
				for (int i = 0; i < ledgerData.length; i++) {
					if (i == ledgerData.length - 1) {
						ledgCode += ledgerData[i][0];
					} else {
						ledgCode += ledgerData[i][0] + ",";
					}
				}
			}
		} catch (Exception e) {
			logger.error("exception in ledgerData query",e);
		} 
		 return ledgCode;
	 } //end of method
		
	/**
	 * Following function is called to calculate the tds debit code,tds edu cess,tds suchatge percentage and the maximum surcharge amount.
	 * @return
	 */
	public Object[][] getTdsDebitCode(){
		Object tdsParameter[][] = null;
		try {
			String tdsParaQuery = "SELECT TDS_DEBIT_CODE,TDS_EDU_CESS,TDS_REBATEMAX_AMOUNT,TDS_SURCHARGE "
					+ " FROM HRMS_TAX_PARAMETER "
					+ " WHERE TDS_CODE= (SELECT MAX(TDS_CODE) FROM HRMS_TAX_PARAMETER)";
			tdsParameter = getSqlModel().getSingleResult(tdsParaQuery);
		} catch (Exception e) {
			logger.error("exception in tdsParameter query",e);
		} //end of catch
		return tdsParameter;
	} //end of method
	
	/**
	 * Following function is called to select the records form hrms_tax_challan table and displays the records.
	 * Following function is called when Go button is clicked.
	 * @param taxChallan
	 */
	
	public void getRecordChanged(TaxChallan taxChallan) {
		String ledgerCode = "";
		Object tdsParameter[][] = null;
		taxChallan.setListFlag("true");
		try {
			ledgerCode = getLedgerCode(taxChallan.getMonth(),taxChallan.getYear());
		} catch (Exception e) {
			logger.error("exception in ledgerCode in getRecord method",e);
		} //end of catch
				if(!(ledgerCode.length() >0)){
					taxChallan.setTax("0.00");
					taxChallan.setSurcharge("0.00");
					taxChallan.setEduCess("0.00");
					taxChallan.setTotalTax("0.00");
					taxChallan.setIntAmt("0.00");
					taxChallan.setOthrAmt("0.00");
					taxChallan.setNoData("true");
				} //end of if
				else{
					try {
						String tdsParaQuery = "SELECT TDS_DEBIT_CODE,TDS_EDU_CESS,TDS_REBATEMAX_AMOUNT,TDS_SURCHARGE "
								+ " FROM HRMS_TAX_PARAMETER "
								+ " WHERE TDS_CODE= (SELECT MAX(TDS_CODE) FROM HRMS_TAX_PARAMETER)";
						tdsParameter = getSqlModel().getSingleResult(tdsParaQuery);
					} catch (Exception e) {
						logger.error("exception in tdsParameter query",e);
					} //end of catch
					if(!(tdsParameter.length >0)){
						taxChallan.setTax("0.00");
						taxChallan.setSurcharge("0.00");
						taxChallan.setEduCess("0.00");
						taxChallan.setTotalTax("0.00");
						taxChallan.setIntAmt("0.00");
						taxChallan.setOthrAmt("0.00");
					} //end of else if
					else if(String.valueOf(tdsParameter[0][0]).equals("") || String.valueOf(tdsParameter[0][0]).equals("null") 
							 || String.valueOf(tdsParameter[0][0]).equals(null) ){
						taxChallan.setTax("0.00");
						taxChallan.setSurcharge("0.00");
						taxChallan.setEduCess("0.00");
						taxChallan.setTotalTax("0.00");
						taxChallan.setIntAmt("0.00");
						taxChallan.setOthrAmt("0.00");
					} //end of else if
					else if(String.valueOf(tdsParameter[0][1]).equals("") || String.valueOf(tdsParameter[0][1]).equals("null") 
							 || String.valueOf(tdsParameter[0][1]).equals(null) ){
						taxChallan.setSurcharge("0.00");
						taxChallan.setEduCess("0.00");
						taxChallan.setIntAmt("0.00");
						taxChallan.setOthrAmt("0.00");
					} //end of else if
					else if(String.valueOf(tdsParameter[0][2]).equals("") || String.valueOf(tdsParameter[0][2]).equals("null") 
							 || String.valueOf(tdsParameter[0][2]).equals(null) ){
						taxChallan.setSurcharge("0.00");
						taxChallan.setEduCess("0.00");
						taxChallan.setIntAmt("0.00");
						taxChallan.setOthrAmt("0.00");
					}//end of else if
					else{	
						try {
							getChaRec(taxChallan, tdsParameter);
						} catch (Exception e) {
							logger.error("exception in getChaRec in  getRecord method",e);
						} //end of catch
					} //end of else
				} //end of else
	} //end of method
	/**
	 * following function is called to add the records.
	 * First it inserts the record in HRMS_TAX_CHALLAN table.Then inserts the record in HRMS_TAX_CHALLAN_DTL table. 
	 * @param taxChallan
	 * @param empId
	 * @param challanTds
	 * @param challanEdCess
	 * @param challanSur
	 * @param challanTotTax
	 * @return
	 */
	public String saveRecord(TaxChallan taxChallan,String empId[],String[] challanTds,String[] challanEdCess,String[] challanSur,String[] challanTotTax){
		Object[][] addObjHdr=new Object[1][19];
		addObjHdr[0][0]  = taxChallan.getDivId();
		addObjHdr[0][1]  = taxChallan.getMonth();
		addObjHdr[0][2]  = taxChallan.getYear();
		addObjHdr[0][3]  = taxChallan.getTax();
		addObjHdr[0][4]  = taxChallan.getSurcharge();
		addObjHdr[0][5]  = taxChallan.getEduCess();
		addObjHdr[0][6]  = taxChallan.getTotalTax();
		logger.info("Total challan tax"+taxChallan.getTotalTax());
		addObjHdr[0][7]  = taxChallan.getChequeNo();
		addObjHdr[0][8]  = taxChallan.getChequeDate();
		addObjHdr[0][9]  = checkNull(taxChallan.getBsrCode());
		addObjHdr[0][10] = taxChallan.getChallanNo();
		addObjHdr[0][11] = taxChallan.getChallanDate();//CHALLAN_DATE= TO_DATE(?,'DD-MM-YYYY'),CHALLAN_NO= ?,CHALLAN_CHQNO= ?,CHALLAN_CHQDATE=TO_DATE(?,'DD-MM-YYYY')
		addObjHdr[0][12] = taxChallan.getAckNo();
		addObjHdr[0][13] = taxChallan.getVchrNo();
		addObjHdr[0][14] = taxChallan.getIntAmt();
		addObjHdr[0][15] = taxChallan.getOthrAmt();
		addObjHdr[0][16] = taxChallan.getBookEntry();
		addObjHdr[0][17] = taxChallan.getOnHold();
		addObjHdr[0][18] = taxChallan.getPaymentDate();
		
		
		
		Object[][] update1=new Object[1][2];
		update1[0][0]= taxChallan.getChequeNo();
		update1[0][1]= taxChallan.getChequeDate();
		String chqQuery="UPDATE HRMS_TAX_CHALLAN SET CHALLAN_CHQNO= ?,CHALLAN_CHQDATE=TO_DATE(?,'DD-MM-YYYY')  WHERE CHALLAN_CODE="+taxChallan.getChallanID();
		
		
		Object[][] update2=new Object[1][2];
		update2[0][0]=taxChallan.getChallanNo();
		update2[0][1]=taxChallan.getChallanDate();
		String chnQuery="UPDATE HRMS_TAX_CHALLAN SET CHALLAN_NO= ?,CHALLAN_DATE=TO_DATE(?,'DD-MM-YYYY')  WHERE CHALLAN_CODE="+taxChallan.getChallanID();
		
		Object[][] addObj=new Object[1][14];
		addObj[0][0]  = taxChallan.getDivId();
		addObj[0][1]  = taxChallan.getMonth();
		addObj[0][2]  = taxChallan.getYear();
		addObj[0][3]  = taxChallan.getTax();
		addObj[0][4]  = taxChallan.getSurcharge();
		addObj[0][5]  = taxChallan.getEduCess();
		addObj[0][6]  = taxChallan.getTotalTax();
		//addObj[0][7]  = taxChallan.getChequeNo();
		//addObj[0][8]  = taxChallan.getChequeDate();
		addObj[0][7]  = checkNull(taxChallan.getBsrCode());
		//addObj[0][10] = taxChallan.getChallanNo();
		//addObj[0][11] = taxChallan.getChallanDate();CHALLAN_DATE= TO_DATE(?,'DD-MM-YYYY'),CHALLAN_NO= ?,CHALLAN_CHQNO= ?,CHALLAN_CHQDATE=TO_DATE(?,'DD-MM-YYYY')
		addObj[0][8] = taxChallan.getAckNo();
		addObj[0][9] = taxChallan.getVchrNo();
		addObj[0][10] = taxChallan.getIntAmt();
		addObj[0][11] = taxChallan.getOthrAmt();
		addObj[0][12] = taxChallan.getBookEntry();
		addObj[0][13] = taxChallan.getPaymentDate();
		
		Object dataCheck[][] = null;
				
		if(taxChallan.getChallanID().trim().equals("") || taxChallan.getChallanID().trim().equals("null") 
				|| taxChallan.getChallanID().trim().equals(null)){
		
			try {
				String check = "SELECT CHALLAN_MONTH,CHALLAN_YEAR,CHALLAN_DIVISION_ID FROM HRMS_TAX_CHALLAN ";
				dataCheck = getSqlModel().getSingleResult(check);
			} catch (Exception e) {
				logger.error("exception in dataCheck query",e);
			} //end of catch
			if(dataCheck != null && dataCheck.length >0){
				try {
					for (int i = 0; i < dataCheck.length; i++) {
						if (String.valueOf(dataCheck[i][0]).trim().equals(
								"" + taxChallan.getMonth() + "")
								&& String.valueOf(dataCheck[i][1]).trim()
										.equals("" + taxChallan.getYear() + "")
								&& String
										.valueOf(dataCheck[i][2])
										.trim()
										.equals("" + taxChallan.getDivId() + "")) {
							return "3";
						}//End if
					}//End of for loop
				} catch (Exception e) {
					logger.error("exception in dataCheck loop",e);
				} //end of catch
			}//End if condition
		}//End if
		
		String query = "INSERT INTO HRMS_TAX_CHALLAN(CHALLAN_CODE,CHALLAN_DIVISION_ID,CHALLAN_MONTH,CHALLAN_YEAR,CHALLAN_TAX,CHALLAN_SURCHARGE, "
		+" CHALLAN_EDUCESS,CHALLAN_TOTALTAX,CHALLAN_CHQNO,CHALLAN_CHQDATE,CHALLAN_BANK,CHALLAN_NO,CHALLAN_DATE,CHALLAN_ACK_NO,CHALLAN_VCHR_NO,CHALLAN_INT_AMT,CHALLAN_OTHR_AMT,CHALLAN_BOOK_ENTRY,CHALLAN_TAX_ONHOLD,CHALLAN_DATE_PAYMENT)  "
		+" VALUES((SELECT NVL(MAX(CHALLAN_CODE),0)+1 FROM HRMS_TAX_CHALLAN),?,?,?,?,?,?,?, "
		+" ?,to_date(?,'dd-mm-yyyy'),?,?,to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,to_date(?,'dd-mm-yyyy')) ";
		
		String updQuery = "UPDATE HRMS_TAX_CHALLAN SET CHALLAN_DIVISION_ID= ?,CHALLAN_MONTH = ?,CHALLAN_YEAR= ?,CHALLAN_TAX= ?,CHALLAN_SURCHARGE= ?, "
			+" CHALLAN_EDUCESS= ?,CHALLAN_TOTALTAX= ?,CHALLAN_BANK= ?, "
			+" CHALLAN_ACK_NO = ?,CHALLAN_VCHR_NO=?,CHALLAN_INT_AMT=?,CHALLAN_OTHR_AMT=?,CHALLAN_BOOK_ENTRY=?,CHALLAN_DATE_PAYMENT=TO_DATE(?,'DD-MM-YYYY')  WHERE CHALLAN_CODE="+taxChallan.getChallanID()+" ";
		
		if(taxChallan.getChallanID()==null || taxChallan.getChallanID().equals("") ||  taxChallan.getChallanID().equals("null") ||  taxChallan.getChallanID().equals(null)){
			 getSqlModel().singleExecute(query,addObjHdr);
			 String challanCode="SELECT MAX(CHALLAN_CODE) FROM HRMS_TAX_CHALLAN";
			 Object chalCode[][] = getSqlModel().getSingleResult(challanCode);
			
			 
			 try {
				for (int i = 0; i < empId.length; i++) {
					Object[][] challanValue = new Object[1][6];
					challanValue[0][0] = chalCode[0][0];//Challan Code
					challanValue[0][1] = empId[i];//Employee Code 
					challanValue[0][2] = challanTds[i];//Tds amount
					challanValue[0][3] = challanSur[i];//Challan Education Cess
					challanValue[0][4] = challanEdCess[i];//Challan Surcharge
					challanValue[0][5] = challanTotTax[i];//Challan Total Tax
					//challanValue[0][6]= dedDate[i];//Date of Deduction
					// challanValue[0][7]=depDate[i];//Deposit date
					// challanValue[0][8]=taxChallan.getChallanDate();
					String addChallan = "INSERT INTO HRMS_TAX_CHALLAN_DTL(CHALLAN_CODE,EMP_ID,CHALLAN_TDS,CHALLAN_SURCHARGE,CHALLAN_EDU_CESS,CHALLAN_TOTAL_TAX)"
							+ " VALUES(?,?,?,?,?,?)";
					getSqlModel().singleExecute(addChallan, challanValue);

				}//End of for loop
			} catch (Exception e) {
				logger.error("exception empId for loop",e);
			} //end of catch
			return "1";
		 } //end of if
		else{
		 try {
			getSqlModel().singleExecute(updQuery, addObj);
		} catch (Exception e) {
			logger.error("exception in addObj",e);
		} //end of catch
		
		//UPDATED BY REEBA
		if(taxChallan.getBookEntry().equals("Y")){
			 try {
				getSqlModel().singleExecute(chqQuery, update1);
			} catch (Exception e) {
				logger.error("exception in update1",e);
			} //end of catch
		 } //end of if 
		 if(taxChallan.getBookEntry().equals("N")){
			 try {
				getSqlModel().singleExecute(chnQuery, update2);
			} catch (Exception e) {
				logger.error("exception in update2",e);
			} //end of catch
		 } //end of if
		 return "2";
		} //end of else
	} //end of method
	
	/**
	 * following function is called when a record is selected from the search window.
	 * @param taxChallan
	 */
	
	public void getSelectRecord(TaxChallan taxChallan){
		Object taxData[][] = null;
		try{
		try {
			String query = "SELECT TO_CHAR(NVL(CHALLAN_TAX,0),9999999990.99), TO_CHAR(NVL(CHALLAN_SURCHARGE,0),9999999990.99), TO_CHAR(NVL(CHALLAN_EDUCESS,0),9999999990.99), TO_CHAR(NVL(CHALLAN_TOTALTAX,0),9999999990.99), NVL(CHALLAN_CHQNO,' '), NVL(TO_CHAR(CHALLAN_CHQDATE,'DD-MM-YYYY'),' '),"
					+ " CHALLAN_NO,TO_CHAR(CHALLAN_DATE,'DD-MM-YYYY'),BANK_NAME,BANK_BSR_CODE,CHALLAN_ACK_NO,BANK_MICR_CODE,CHALLAN_VCHR_NO,NVL(CHALLAN_INT_AMT,0.00),NVL(CHALLAN_OTHR_AMT,0.00),"
					+ " CHALLAN_BOOK_ENTRY,DECODE(CHALLAN_TAX_ONHOLD,'A','ALL','N','No','Y','Yes'),TO_CHAR(CHALLAN_DATE_PAYMENT,'DD-MM-YYYY'),DECODE(CHALLAN_SALARY_FLAG,'Y','true','N','false'), " 
					+ " DECODE(CHALLAN_SETTLE_FLAG,'Y','true','N','false','false'),DECODE(CHALLAN_ALLOW_FLAG,'Y','true','N','false','false'),DECODE(CHALLAN_ARREARS_FLAG,'Y','true','N','false','false'), " 
					+ " DECODE(CHALLAN_BONUS_FLAG,'Y','true','N','false','false'), DECODE(CHALLAN_LEAVE_FLAG,'Y','true','N','false','false'), DECODE(CHALLAN_OT_FLAG,'Y','true','N','false','false')"
					+ " FROM HRMS_TAX_CHALLAN "
					+ " LEFT JOIN HRMS_BANK ON HRMS_BANK.BANK_BSR_CODE = hrms_tax_challan.CHALLAN_BANK WHERE CHALLAN_CODE="
					+ taxChallan.getChallanID();
			taxData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("eception in taxData query",e);
		} //end of catch
		if(!(taxData.length >0)){
			taxChallan.setTax("");
			taxChallan.setSurcharge("");
			taxChallan.setEduCess("");
			taxChallan.setTotalTax("");
			taxChallan.setChequeNo("");
			taxChallan.setChequeDate("");
			taxChallan.setChallanNo("");
			taxChallan.setChallanDate("");
			taxChallan.setBank("");
			taxChallan.setBsrCode("");
			taxChallan.setAckNo("");
			taxChallan.setVchrNo("");
			taxChallan.setIntAmt("");
			taxChallan.setOthrAmt("");
			//taxChallan.setBookEntry("");
		}else{
			taxChallan.setTax(Utility.twoDecimals(String.valueOf(taxData[0][0])));
			taxChallan.setSurcharge(Utility.twoDecimals(String.valueOf(taxData[0][1])));
			taxChallan.setEduCess(Utility.twoDecimals(String.valueOf(taxData[0][2])));
			taxChallan.setTotalTax(Utility.twoDecimals(String.valueOf(taxData[0][3])));
			taxChallan.setChequeNo(String.valueOf(taxData[0][4]));
			taxChallan.setChequeDate(checkNull(String.valueOf(taxData[0][5])).trim());
			taxChallan.setChallanNo(checkNull(String.valueOf(taxData[0][6])).trim());
			taxChallan.setChallanDate(checkNull(String.valueOf(taxData[0][7])).trim());
			taxChallan.setBank(checkNull(String.valueOf(taxData[0][8])));
			taxChallan.setBsrCode(checkNull(String.valueOf(taxData[0][9])));
			taxChallan.setAckNo(checkNull(String.valueOf(taxData[0][10])));
			taxChallan.setMicr(checkNull(String.valueOf(taxData[0][11])));
			taxChallan.setVchrNo(checkNull(String.valueOf(taxData[0][12])));
			taxChallan.setIntAmt(Utility.twoDecimals(String.valueOf(taxData[0][13])));
			taxChallan.setOthrAmt(Utility.twoDecimals(String.valueOf(taxData[0][14])));
			taxChallan.setBookEntry(String.valueOf(taxData[0][15]));
			taxChallan.setOnHold(String.valueOf(taxData[0][16]));
			taxChallan.setPaymentDate(checkNull(String.valueOf(taxData[0][17])));
			taxChallan.setIncludeSalary(String.valueOf(taxData[0][18]));
			taxChallan.setIncludeSettlement(String.valueOf(taxData[0][19]));
			taxChallan.setIncludeAllowance(String.valueOf(taxData[0][20]));
			taxChallan.setIncludeArrears(String.valueOf(taxData[0][21]));
			taxChallan.setIncludeBonus(String.valueOf(taxData[0][22]));
			taxChallan.setIncludeLeaveEncashment(String.valueOf(taxData[0][23]));
			taxChallan.setIncludeOverTime(String.valueOf(taxData[0][24]));
		}
		}catch(Exception e){
			logger.error("exception in getSelectRecord method",e);
		} 
		Object[][]tdsParameter = null;
		try {
			String tdsParaQuery = "SELECT TDS_DEBIT_CODE,TDS_EDU_CESS,TDS_REBATEMAX_AMOUNT,TDS_SURCHARGE "
					+ " FROM HRMS_TAX_PARAMETER "
					+ " WHERE TDS_CODE= (SELECT MAX(TDS_CODE) FROM HRMS_TAX_PARAMETER)";
			tdsParameter = getSqlModel().getSingleResult(tdsParaQuery);
		} catch (Exception e) {
			logger.error("exception in tdsParameter query",e);
		}
		/**
		 * this values are set...because they are used in java script for calculation
		 * if in case total tax is changed.
		 */
		if(tdsParameter !=null && tdsParameter.length >0){
			taxChallan.setEduCessPercen(String.valueOf(tdsParameter[0][1]));
			taxChallan.setRebateLimit(String.valueOf(tdsParameter[0][2]));
			taxChallan.setSurchargePercen(String.valueOf(tdsParameter[0][3]));
		}
	} //end of method
	
	/**
	 * following function is called when Go button is clicked to show the employee list with their tax,education cess and surcharge.
	 * Education cess and surcharge are calculated according to the formula defined in the function getTaxDtls().
	 * This function selects the record from HRMS_SAL_DEBITS_2008(Year entered) table.
	 * @param taxChallan
	 * @param tdsParameter 
	 */
	public void getChaRec(TaxChallan taxChallan, Object[][] tdsParameter){
		try{
			double surcharge=0.00,totaltds=0.00;
			double educationCess=0.00;
			double totTds=0.00;
			double totalTax=0.00;
			Object data[][] = null;
			String ledgerCode = "";
			
			/**
			 * this values are set...because they are used in java script for calculation
			 * if in case total tax is changed.
			 */
			taxChallan.setEduCessPercen(String.valueOf(tdsParameter[0][1]));
			taxChallan.setRebateLimit(String.valueOf(tdsParameter[0][2]));
			taxChallan.setSurchargePercen(String.valueOf(tdsParameter[0][3]));
			try {
				ledgerCode = getLedgerCode(taxChallan.getMonth(), taxChallan.getYear());
			} catch (Exception e) {
				logger.error("exception in ledgerCode in getChaRec method",e);
			} //end of catch
			
			ArrayList<Object> chList = new ArrayList<Object>();
			
			if(ledgerCode!=null && ledgerCode.length() >0){
				int fromYear=0;
				int toYear=0;
					if(Integer.parseInt(taxChallan.getMonth())>=4 && Integer.parseInt(taxChallan.getMonth())<=12) {
					
						fromYear =Integer.parseInt(taxChallan.getYear());
						toYear=fromYear+1;
					 } //end of if
					else if(Integer.parseInt(taxChallan.getMonth())>=1 && Integer.parseInt(taxChallan.getMonth())<=3) {
					     fromYear =Integer.parseInt(taxChallan.getYear())-1;
					     toYear=Integer.parseInt(taxChallan.getYear());
					} //end of else if	
			
			try{
				String salaryQuery="SELECT DISTINCT HRMS_SAL_DEBITS_"+taxChallan.getYear()+".EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, TO_CHAR(SUM(NVL(SAL_AMOUNT,0)),9999999990.99),"
				+" SAL_DEBIT_CODE FROM HRMS_SAL_DEBITS_"+taxChallan.getYear()
				+" INNER JOIN HRMS_EMP_OFFC ON HRMS_SAL_DEBITS_"+taxChallan.getYear()+".EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+" INNER JOIN HRMS_SALARY_"+taxChallan.getYear()+"  ON (HRMS_SALARY_"+taxChallan.getYear()+".EMP_ID = HRMS_SAL_DEBITS_"+taxChallan.getYear()+".EMP_ID and HRMS_SALARY_"+taxChallan.getYear()+".SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"+taxChallan.getYear()+".SAL_LEDGER_CODE)"
				+" WHERE HRMS_EMP_OFFC.EMP_DIV="+taxChallan.getDivId()+" AND SAL_DEBIT_CODE="+String.valueOf(tdsParameter[0][0])+" AND SAL_LEDGER_CODE IN("+ledgerCode+") ";
				
				if(taxChallan.getOnHold().equals("Y")){
					salaryQuery += " AND SAL_ONHOLD='Y' ORDER BY HRMS_SAL_DEBITS_"+taxChallan.getYear()+".EMP_ID";
				} else if(taxChallan.getOnHold().equals("N")){
					salaryQuery += " AND SAL_ONHOLD='N' ORDER BY HRMS_SAL_DEBITS_"+taxChallan.getYear()+".EMP_ID";
				} else {
					salaryQuery += " ORDER BY UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME)";
				}
				if(taxChallan.getIncludeSalary().equals("true")){
					data = getSqlModel().getSingleResult(salaryQuery);
				}
				
			}catch(Exception e){
				logger.error("exception in HRMS_SAL_DEBITS_ query ",e);
			}
			
			if(taxChallan.getIncludeArrears().equals("true")){
				Object[][]arrearDebitData = getDebitArrearsAmt(taxChallan.getMonth(),taxChallan.getYear(),taxChallan.getDivId(), String.valueOf(tdsParameter[0][0]));
				
				if(arrearDebitData != null && arrearDebitData.length > 0){
					data = Utility.consolidateArrearsObject(data, arrearDebitData, 0, new int[] {3}, 5);
				}
			} 
			
			if(taxChallan.getIncludeSettlement().equals("true")){
				Object[][]settlementData = getSettlementAmt(taxChallan.getMonth(),taxChallan.getYear(),taxChallan.getDivId(),String.valueOf(tdsParameter[0][0]));
				
				if(settlementData != null && settlementData.length > 0){
					data = Utility.consolidateArrearsObject(data, settlementData, 0, new int[] {3}, 5);
				}
			} 
			
			if(taxChallan.getIncludeBonus().equals("true")){
				Object[][] bonusData = getBonusAmt(taxChallan.getMonth(),taxChallan.getYear(),taxChallan.getDivId());
				
				if(bonusData != null && bonusData.length > 0){
					data = Utility.consolidateArrearsObject(data, bonusData, 0, new int[] {3}, 5);
				}
			} 
			
			if(taxChallan.getIncludeOverTime().equals("true")){
				Object[][] overTimeData = getOverTimeAmt(taxChallan.getMonth(),taxChallan.getYear(),taxChallan.getDivId());
				if(overTimeData != null && overTimeData.length > 0){
					data = Utility.consolidateArrearsObject(data, overTimeData, 0, new int[] {3}, 5);
				}
			} 
			
			if(taxChallan.getIncludeLeaveEncashment().equals("true")){
				Object[][] leaveEncashmentData = getLeaveEncashmentAmt(taxChallan.getMonth(),taxChallan.getYear(),taxChallan.getDivId());
				if(leaveEncashmentData != null && leaveEncashmentData.length > 0){
					data = Utility.consolidateArrearsObject(data, leaveEncashmentData, 0, new int[] {3}, 5);
				}
			} 
			
			if(data.length >0 && data!=null){
				/*
				 * Following loop is used to set the employee id,name,challan tds,challan surcharge,education cess to every employee
				 * those who belong to the selected division
				 */
				
				String empIncomeQuery = "SELECT TO_CHAR(NVL(TDS_TAXABLE_INCOME,0),9999999990.99),TDS_EMP_ID FROM HRMS_TDS "
										+" WHERE  TDS_FROM_YEAR="+fromYear+" AND TDS_TO_YEAR="+toYear+" ORDER BY TDS_EMP_ID";
				Object empTaxIncome[][] = getSqlModel().getSingleResult(empIncomeQuery);
				
				try{	
					for(int i=0;i<data.length;i++){
						TaxChallan bean=new TaxChallan(); 
						double amount=0.00;
						Object[][] challanTax = null;
						double chkAmount=0.00;
						//String sqlQuery="SELECT NVL(TDS_TAXABLE_INCOME,0.00) FROM HRMS_TDS WHERE TDS_EMP_ID="+data[i][0]+" AND TDS_FROM_YEAR="+fromYear+" AND TDS_TO_YEAR="+toYear;
						//Object amt[][] = getSqlModel().getSingleResult(sqlQuery);
						if(empTaxIncome!=null && empTaxIncome.length!=0){
							for (int j = 0; j < empTaxIncome.length; j++) {
								 if(String.valueOf(empTaxIncome[j][1]).equals(String.valueOf(data[i][0]))){
									 amount=Double.parseDouble(String.valueOf(empTaxIncome[j][0]));
								 } //end of if
							} //end of loop
						} //end of if
						else{
							amount=0.00;
						} //end of else
						
						logger.info("amount is"+amount);
						 try {
							challanTax = getTaxDtls(Double.parseDouble(String.valueOf(data[i][3])), Double
									.parseDouble(String.valueOf(tdsParameter[0][2])),amount, tdsParameter);
						} catch (Exception e) {
							logger.error("exception in challanTax",e);
						} //end of catch
						logger.info("data[i][2]  emp name--->"+data[i][2]); 
						double tottds = Double.parseDouble(String.valueOf(challanTax[0][0]));
					//	totaltds += tottds;
						logger.info("totaltds-------->"+totaltds);
						double sur=Double.parseDouble(String.valueOf(challanTax[0][2]));
						//surcharge+=sur;
						//logger.info("surcharge----------in loop  2---"+surcharge);
						double cess=Double.parseDouble(String.valueOf(challanTax[0][1]));
						//educationCess+=cess;
						//logger.info("educationCess----------in loop  3---"+educationCess);
						
					    totTds+=Double.parseDouble(String.valueOf(data[i][3]));
					    
					   // logger.info("totTds----------down  4---"+totTds);
						bean.setEmpId(String.valueOf(data[i][0]));
						bean.setEmpToken(String.valueOf(data[i][1]));
						bean.setEmpName(String.valueOf(data[i][2]));
						bean.setEmpTaxIncome(String.valueOf(amount));//this is used in java script
	
						chkAmount = Double.parseDouble(formatter.format((tottds))) + Double.parseDouble(formatter.format((sur))) + Double.parseDouble(formatter.format((cess)));
						
						//logger.info("value of chkAmount==11====>"+chkAmount);
						
						chkAmount = Double.parseDouble(String.valueOf(data[i][3])) - chkAmount;
						
						//logger.info("value of data[i][3]======>"+data[i][3]);
						
						//logger.info("value of chkAmount==22====>"+chkAmount);
						
						bean.setChallanTax(formatter.format(tottds + chkAmount));
						//logger.info("TDS-----------"+bean.getChallanTax());
						totaltds+= Double.parseDouble(String.valueOf(bean.getChallanTax()));
						bean.setChallanSurcharge(Utility.twoDecimals(Math.round(sur)));
						//logger.info("ChallanSurcharge-----------"+bean.getChallanSurcharge());
						surcharge+= Double.parseDouble(String.valueOf(bean.getChallanSurcharge()));
						
						bean.setChallanEduCess(Utility.twoDecimals(Math.round(cess)));
						logger.info("ChallanEduCess-----------"+bean.getChallanEduCess());
						educationCess+= Double.parseDouble(String.valueOf(bean.getChallanEduCess()));
						
						bean.setChallanTotTax(Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(data[i][3])))));
						totalTax+= Double.parseDouble(String.valueOf(bean.getChallanTotTax()));
						logger.info("ChallanTotTax-----------"+bean.getChallanTotTax());
						chList.add(bean);
					} //end of loop
				}catch(Exception e){
					logger.error("exception in HRMS_SAL_DEBITS object loop",e);
				} //end of catch
			
			} //end of data if
			else{
				taxChallan.setCountFlag("true");
				taxChallan.setNoData("true");
			} //end of else
			taxChallan.setChallanList(chList);
			} //end of if
			else{
				taxChallan.setNoData("true");
				//taxChallan.setSaveFlag("true");
				taxChallan.setCountFlag("true");
			} //end of else
			
			/*
			 * Following code sets the total sum of the surcharge,education cess and total tax amount.
			 */
			taxChallan.setTax(Utility.twoDecimals(totaltds));
			taxChallan.setSurcharge(Utility.twoDecimals(surcharge));
			taxChallan.setEduCess(Utility.twoDecimals(educationCess));
			taxChallan.setTotalTax(Utility.twoDecimals(totalTax));
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("exception in getChaRec",e);
		} //end of catch
	}//end of method
	/**
	 * This method will calculate the debit arrears amount under the head TDS
	 * @param month
	 * @param year
	 * @param divisionId
	 * @param tdsDebitCode
	 * @return
	 */
	private Object[][] getDebitArrearsAmt(String month, String year, String divisionId, String tdsDebitCode) {
		Object[][]data = null;
		try {
			String query = "SELECT ARREARS_EMP_ID, NVL(EMP_TOKEN,' '), NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' '), TO_CHAR(SUM(NVL(ARREARS_AMT,0)),9999999990.99), ARREARS_DEBIT_CODE "   
				+ " FROM HRMS_ARREARS_DEBIT_"+year
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ARREARS_DEBIT_"+year+".ARREARS_EMP_ID) "    
				+ " INNER JOIN HRMS_DEBIT_HEAD ON (HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ARREARS_DEBIT_"+year+".ARREARS_DEBIT_CODE) "  
				+ " WHERE  ARREARS_CODE IN (SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_PAID_MONTH =" +month
				+ " AND ARREARS_PAID_YEAR = "+year+" AND ARREARS_DIVISION = "+divisionId+") AND ARREARS_DEBIT_CODE ="+tdsDebitCode
				+ " GROUP BY ARREARS_DEBIT_CODE, ARREARS_EMP_ID, EMP_TOKEN, EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME "
				+ " ORDER BY ARREARS_EMP_ID, NVL(EMP_TOKEN,' '), NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' ')";
			
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in getDebitArrearsAmt query",e);
		} //end of catch
		return data;
	} //end of getDebitArrearsAmt method
	/**
	 * This method will calculate the debit settlement amount under the head TDS
	 * @param month
	 * @param year
	 * @param divId
	 * @param tdsDebitCode
	 * @return
	 */
	private Object[][] getSettlementAmt(String month, String year,String divId, String tdsDebitCode) {
		Object[][]data = null;
		try {
			String query = "SELECT SETTL_ECODE, NVL(EMP_TOKEN,' '), NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' '), TO_CHAR(SUM(NVL(SETTL_AMT,0)),9999999990.99), SETTL_DEBIT_CODE FROM HRMS_SETTL_DEBITS "    
			+" INNER JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_DEBITS.SETTL_CODE) "
			+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SETTL_HDR.SETTL_ECODE) "
			+" INNER JOIN HRMS_RESIGN ON (HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO) " 
			+" WHERE (SETTL_MTH_TYPE IN ('OH','CO')) AND RESIGN_WITHDRAWN = 'N' AND EMP_DIV ="+divId+" "
			+" AND TO_CHAR(SETTL_SEPRDT,'MM')="+month+" AND TO_CHAR(SETTL_SEPRDT,'YYYY')="+year+" "
			+" AND SETTL_DEBIT_CODE="+tdsDebitCode+"   "
			+" GROUP BY SETTL_DEBIT_CODE,SETTL_ECODE "  
			+" ORDER BY SETTL_ECODE, NVL(EMP_TOKEN,' '), NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' ')";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in settlement query",e);
		} //end of catch	
		return data;
	} //end of getSettlementAmt method

	/**
	 * This method will calculate the TDS amount deducted in Allowance
	 * @param month
	 * @param year
	 * @param divId
	 * @return
	 */
	private Object[][] getAllowanceAmt(String month, String year, String divId) {
		Object[][]data = null;
		try {
			String query = "SELECT TO_CHAR(NVL(SUM(ALLW_TAX_AMT),0),9999999990.99),HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID " 
			+" FROM HRMS_ALLOWANCE_HDR   "
			+" INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON (HRMS_ALLOWANCE_EMP_DTL.ALLW_ID = HRMS_ALLOWANCE_HDR.ALLW_ID) "
			+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID) "
			+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ALLOWANCE_HDR.ALLW_CREDIT_HEAD) "  
			+" AND TO_CHAR(ALLW_PROCESS_DATE,'MM')="+month+"  "
			+" AND TO_CHAR(ALLW_PROCESS_DATE,'YYYY')="+year+" " 
			+" AND EMP_DIV = "+divId+" "
			+" GROUP BY HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID";
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in allowance query",e);
		} //end of catch	
		return data;
	} //end of getAllowanceAmt method
	
	/**
	 * This method will calculate the TDS amount deducted in Bonus
	 * @param month
	 * @param year
	 * @param divId
	 * @return
	 */
	private Object[][] getBonusAmt(String month, String year, String divId) {
		Object[][] bonusData = null;
		try {
			String query = " SELECT HRMS_BONUS_EMP.EMP_ID, NVL(EMP_TOKEN,' '), NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' '), TO_CHAR(SUM(NVL(HRMS_BONUS_EMP.BONUS_TAX_AMT,0)),9999999990.99) BONUS_AMT, 0 " 
			+ " FROM HRMS_BONUS_EMP "
			+ " INNER JOIN HRMS_BONUS_HDR ON (HRMS_BONUS_HDR.BONUS_CODE = HRMS_BONUS_EMP.BONUS_CODE)"
			+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_BONUS_EMP.EMP_ID)"
			+ " WHERE HRMS_BONUS_HDR.DIV_CODE="+divId+" AND HRMS_BONUS_HDR.PAY_MONTH="+month+" AND HRMS_BONUS_HDR.PAY_YEAR = "+year
			+ " AND HRMS_BONUS_HDR.PAY_IN_SAL='N' AND  HRMS_BONUS_HDR.DEDUCT_TAX='Y'"
			+ " GROUP BY HRMS_BONUS_EMP.EMP_ID, NVL(EMP_TOKEN,' '), NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' ')";
			
			bonusData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bonusData;
	}
	/**
	 * This method will calculate the TDS amount deducted in Leave Encashment
	 * @param month
	 * @param year
	 * @param divId
	 * @return
	 */
	private Object[][] getLeaveEncashmentAmt(String month, String year, String divId) {
		Object[][] leaveEncashmentData = null;
		try {
			String query = " SELECT HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID, NVL(EMP_TOKEN,' '), NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' '), "
				+ " TO_CHAR(SUM(NVL(ENCASHMENT_TDS_AMOUNT,0)),9999999990.99), 0"
				+ " FROM HRMS_ENCASHMENT_PROCESS_DTL "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID) "
				+ " INNER JOIN HRMS_ENCASHMENT_PROCESS_HDR ON (HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_CODE = HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_PROCESS_CODE)"
				+ " WHERE HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DIVISION ="+divId+" AND HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_INCLUDE_SAL_MONTH = "+month
				+ " AND HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_INCLUDE_SAL_YEAR= "+year+" AND HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_INCLUDE_SAL_FLAG = 'N'"
				+ " GROUP BY HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID, NVL(EMP_TOKEN,' '), NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' ')";
			leaveEncashmentData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leaveEncashmentData;
	}

	/**
	 * This method will calculate the TDS amount deducted in OT
	 * @param month
	 * @param year
	 * @param divId
	 * @return
	 */
	private Object[][] getOverTimeAmt(String month, String year, String divId) {
		Object[][] overTimeData = null;
		try {
			String query = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return overTimeData;
	}
	
	/**
	 * Following function is called when a record is selected from the search window.
	 * This function displays the records from HRMS_TAX_CHALLAN_DTL table for every employee those belongs to the selected division 
	 * along with the Employee id,name,tax(tds),surcharge,education cess and the total tax.   
	 * @param taxChallan
	 */
	public void getChallanRecords(TaxChallan taxChallan){
		Object data[][] = null;
	
		try {
			String challanQuery = "SELECT HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE,HRMS_TAX_CHALLAN_DTL.EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_TAX_CHALLAN_DTL.CHALLAN_TDS,"
					+ " NVL(HRMS_TAX_CHALLAN_DTL.CHALLAN_SURCHARGE,0),NVL(HRMS_TAX_CHALLAN_DTL.CHALLAN_EDU_CESS,0),NVL(HRMS_TAX_CHALLAN_DTL.CHALLAN_TOTAL_TAX,0) FROM"
					+ " HRMS_TAX_CHALLAN_DTL INNER JOIN HRMS_TAX_CHALLAN ON(HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE=HRMS_TAX_CHALLAN.CHALLAN_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_TAX_CHALLAN_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " WHERE HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE="
					+ taxChallan.getChallanID()
					+ " ORDER BY UPPER(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME)";
			data = getSqlModel().getSingleResult(challanQuery);
		} catch (Exception e) {
			logger.error("exception in challanQuery in getChallanRecords method",e);
		} //end of catch
		
		if(data.length> 0 && data!=null){
		taxChallan.setListFlag("true");
		ArrayList<Object> chList = new ArrayList<Object>();
		try{
		for(int i=0;i<data.length;i++){
			TaxChallan bean=new TaxChallan(); 
			bean.setChallanCode(String.valueOf(data[i][0]));//Challan Code
			bean.setEmpId(String.valueOf(data[i][1]));//Employee Id
			bean.setEmpToken(String.valueOf(data[i][2]));//Token No.
			bean.setEmpName(String.valueOf(data[i][3]));//Emp Name
			bean.setChallanTax(Utility.twoDecimals(String.valueOf(data[i][4])));//Tds
			//bean.setChallanSurcharge(String.valueOf(data[i][5]));
			bean.setChallanSurcharge(Utility.twoDecimals(String.valueOf(data[i][5])));//Surcharge
			bean.setChallanEduCess(Utility.twoDecimals(String.valueOf(data[i][6])));//Education cess
			bean.setChallanTotTax(Utility.twoDecimals(String.valueOf(data[i][7])));	//Total Tax
		//	bean.setChallanTotTax(String.valueOf(data[i][7]));
		//	bean.setPayDate(String.valueOf(data[i][8]));
			
		//	if(String.valueOf(data[i][9]).equals("null") || String.valueOf(data[i][9]).equals("")){
		//		bean.setDeductDate("");
		//	}else{
		//	bean.setDeductDate(String.valueOf(data[i][9]).trim());
		//	}
			
		//	if(String.valueOf(data[i][10]).equals("null") || String.valueOf(data[i][10]).equals("")){
		//		bean.setDepDate("");
		//	}else{
		//	bean.setDepDate(String.valueOf(data[i][10]).trim());
		//	}
			chList.add(bean);
		}
		}catch (Exception e) {
			logger.error("exception in data loop",e);
		} //end of catch
		
		    taxChallan.setChallanList(chList);
		    
		} //end of if
	
	} //end of method
	
	public String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "";
		} //end of if
		else{
			return result;
		} //end o else
	} //end of method
	
	/** This method sets the ReportDataSet
	 * @param pfChallan
	 * @param request
	 * @param response
	 * @param reportPath - attachment path
	 * @param logoPath - path of the logo to be used
	 */
	public void generateReport(TaxChallan bean, HttpServletRequest request, HttpServletResponse response, String reportPath) {
		try {
			
			ReportDataSet rds = new ReportDataSet();
			String type = bean.getReportType();
			rds.setReportType(type);
			String fileName = "T.D.S.-TCS TAX CHALLAN"+Utility.getRandomNumber(1000);
			String reportPathName=reportPath+fileName+"."+type;
			rds.setFileName(fileName);
			rds.setPageSize("A4");
			rds.setReportHeaderRequired(false);
			rds.setShowPageNo(true);
			org.paradyne.lib.ireportV2.ReportGenerator rg=null;
			/* Added by Prashant*/
			if(reportPath.equals("")){
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session, context, request);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+type);
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, reportPath ,session, context, request);
				request.setAttribute("reportPath", reportPath+fileName+"."+type);
				request.setAttribute("fileName", fileName+"."+type);
				request.setAttribute("action", "TaxChallan_input.action");
			}
			rg = getReport(rg, bean);
			rg.process();
			if(reportPath.equals("")){
				rg.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Changing report using the new report library & revised tax challan format
	 * 
	 */
	public org.paradyne.lib.ireportV2.ReportGenerator getReport(org.paradyne.lib.ireportV2.ReportGenerator rg, TaxChallan bean){
		try {
			int toYear=Integer.parseInt(bean.getYear().substring(2))+1;
			String toYearStr ="";
			if(toYear < 10){
				toYearStr = "0"+toYear;
			}else {
				toYearStr =""+toYear;
			}
			String assesmentYear = bean.getYear()+ "-"+ toYearStr;
			TableDataSet subtitleName = new TableDataSet();
			Object obj[][] = new Object[1][3];
			
			obj[0][0] = "* Important : Please see notes \noverleaf before filling up the challan";
			obj[0][1] = "T.D.S./TCS TAX CHALLAN";
			obj[0][2] = "Single Copy (to be sent to the ZAO)";
			subtitleName.setData(obj);
			subtitleName.setCellAlignment(new int[] { 0, 1, 0 });
			subtitleName.setCellWidth(new int[] { 30, 40, 30 });
			subtitleName.setBorderDetail(3);
			subtitleName.setHeaderTable(true);
			rg.addTableToDoc(subtitleName);
			
			TableDataSet heading1 = new TableDataSet();
			heading1.setData(new Object[][] { {
							"CHALLAN NO./ \nITNS \n281\n\n"+bean.getChallanNo(),
							"Tax Applicable (Tick One)*\nTAX DEDUCTED/COLLECTED AT SOURCE FROM\n\n(0020) COMPANY  [__] \t\t\t\t(0021) NON-COMPANY [__] \n\n DEDUCTEES \t\t\t\t\t\t\t DEDUCTEES",
							"Assesment Year \n\n"+assesmentYear }});
			heading1.setCellAlignment(new int[] { 1, 1, 1 });
			heading1.setCellWidth(new int[] { 20, 60, 20 });
			heading1.setBorderDetail(3);
			rg.addTableToDoc(heading1);
			
			Object divAddress[][]=getSqlModel().getSingleResult("SELECT HRMS_DIVISION.DIV_NAME, NVL(HRMS_DIVISION.DIV_ADDRESS1,' '), NVL(HRMS_DIVISION.DIV_ADDRESS2,' '), NVL(HRMS_DIVISION.DIV_ADDRESS3,' '), NVL(HRMS_DIVISION.DIV_TANNO,' '), "
					+" HRMS_DIVISION.DIV_TELEPHONE, HRMS_DIVISION.DIV_PINCODE FROM HRMS_DIVISION WHERE HRMS_DIVISION.DIV_ID="+bean.getDivId());
			
			Object [][] empDataObj = new Object[4][1];
    		empDataObj[0][0] = "Tax Deduction Account No. (T.A.N.)\n"+divAddress[0][4];;
    		empDataObj[1][0] = "Full Name\n"+divAddress[0][0];
    		empDataObj[2][0] = "Complete Address with City & State\n"+divAddress[0][1]+"\n"+""+divAddress[0][2]+"\n"+""+divAddress[0][3];
    		empDataObj[3][0] = "Tel. No. : "+checkNull(""+divAddress[0][5])+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
    				"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Pin : "+checkNull(""+divAddress[0][6]);
    		
    		
    		TableDataSet empData = new TableDataSet();
			empData.setData(empDataObj);
			empData.setCellAlignment(new int[] { 0 });
			empData.setCellWidth(new int[] { 100 });
			empData.setBorderDetail(3);
			rg.addTableToDoc(empData);
    		
    		TableDataSet table1 = new TableDataSet();
    		table1.setData(new Object[][] { { "Type of Payment", "Code * " } });
    		table1.setCellAlignment(new int[] { 0, 2});
    		table1.setCellWidth(new int[] { 50, 50 });
    		table1.setBorderDetail(3);
			
			
			TableDataSet table2 = new TableDataSet();
			table2.setData(new Object[][] { {"(Tick One)\t\t\t\t\t\t\t\t\t\t\t" +
					"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(Please see overleaf)" +
					"\n\n\n \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTDS/TCS Payable by Taxpayer\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t (200)" +
					"\n\n\n \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t TDS/TCS Regular Assessment (Raised by I.T. Deptt.)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(400)\n"}});
			table2.setCellAlignment(new int[] {0});
			table2.setCellWidth(new int[] { 100 });
			table2.setBorderDetail(3);
			
			HashMap<String ,Object> map2 = rg.joinTableDataSet(table1, table2, false, 100);
			
			TableDataSet table3 = new TableDataSet();
			table3.setData(new Object[][] { { "DETAILS OF PAYMENTS","Amount (in Rs. Only)" }});
			table3.setCellAlignment(new int[] { 0, 0});
			table3.setCellWidth(new int[] { 50, 50});
			table3.setBorderDetail(3);
			
			HashMap<String ,Object> map3 = rg.joinTableDataSet(map2, table3, false, 100);
			
			TableDataSet table4 = new TableDataSet();
			table4.setData(new Object[][] { {"Income Tax",bean.getTax()},
					{"Surcharge",bean.getSurcharge()},
					{"Education Cess",bean.getEduCess()},
					{"Interest",bean.getIntAmt()},
					{"Penalty",bean.getOthrAmt()},
					{"Total",bean.getTotalTax()},
					{"Total (in words)",""}});
			table4.setCellAlignment(new int[] { 0, 0});
			table4.setCellWidth(new int[] { 30, 70});
			table4.setBorderDetail(3);
			
			HashMap<String ,Object> map4 = rg.joinTableDataSet(map3, table4, false, 100);
			
			String numberString [] = convert(Integer.parseInt(bean.getTotalTax().trim().replace(".00",""))).split(",");
			
			Object totalInWord [][]= new Object [2][6];
			totalInWord [0][0] = "CRORE";
			totalInWord [0][1] = "LACS";
			totalInWord [0][2] = "THOUSAND";
			totalInWord [0][3] = "HUNDRED";
			totalInWord [0][4] = "TENS";
			totalInWord [0][5] = "UNITS";
			
			for (int i = 0; i < numberString.length; i++) {
				if(numberString[i].equalsIgnoreCase("zero")){
					totalInWord [1][i] = "Nil";
				} else { 
					totalInWord [1][i] = numberString[i];
				}
			}
			
			TableDataSet table5 = new TableDataSet();
			table5.setData(totalInWord);
			table5.setCellAlignment(new int[] { 1, 1, 1, 1, 1, 1});
			table5.setCellWidth(new int[] { 15, 15, 15, 15, 15, 15});
			table5.setBorderDetail(3);
			
			HashMap<String ,Object> map5 = rg.joinTableDataSet(map4, table5, false, 100);
			
			TableDataSet table6 = new TableDataSet();
			table6.setData(new Object[][] { {"Paid in Cash/Debit to A/c /Cheque No."+bean.getChequeNo(), "", "Dated", bean.getChequeDate() }});
			table6.setCellAlignment(new int[] { 1, 1, 1, 1});
			table6.setCellWidth(new int[] { 50, 15, 20, 15});
			table6.setBorderDetail(3);
			
			HashMap<String ,Object> map6 = rg.joinTableDataSet(map5, table6, false, 100);
			
			TableDataSet table7 = new TableDataSet();
			table7.setData(new Object[][] { {"Drawn on", bean.getBank()}});
			table7.setCellAlignment(new int[] { 0, 0});
			table7.setCellWidth(new int[] { 50,50});
			table7.setBorderDetail(3);
			
			HashMap<String ,Object> map7 = rg.joinTableDataSet(map6, table7, false, 100);
			
			TableDataSet table8 = new TableDataSet();
			table8.setData(new Object[][] { {"(Name of the Bank and Branch)"}});
			table8.setCellAlignment(new int[] { 2});
			table8.setCellWidth(new int[] { 100 });
			table8.setBorderDetail(3);
			
			HashMap<String ,Object> map8 = rg.joinTableDataSet(map7, table8, false, 100);
			
			TableDataSet table9 = new TableDataSet();
			table9.setData(new Object[][] { {"Date", ""}});
			table9.setCellAlignment(new int[] { 0, 0});
			table9.setCellWidth(new int[] { 60, 40});
			table9.setBorderDetail(3);
			
			HashMap<String ,Object> map9 = rg.joinTableDataSet(map8, table9, false, 100);
			
			TableDataSet table10 = new TableDataSet();
			table10.setData(new Object[][] { {"Signature of person making payment"}});
			table10.setCellAlignment(new int[] { 2 });
			table10.setCellWidth(new int[] { 100});
			table10.setBorderDetail(3);
			
			HashMap<String ,Object> map10 = rg.joinTableDataSet(map9, table10, false, 100);
			
			TableDataSet table11 = new TableDataSet();
			table11.setData(new Object[][] { {"Taxpayers Counterfoil (To be filled up by taxpayer)"}});
			table11.setCellAlignment(new int[] { 1 });
			table11.setCellWidth(new int[] { 100});
			table11.setBodyFontDetails(Font.FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(0, 0, 0));
			table11.setBorderDetail(3);
			
			HashMap<String ,Object> map11 = rg.joinTableDataSet(map10, table11, false, 100);
			
			TableDataSet table12 = new TableDataSet();
			table12.setData(new Object[][] { {"TAN "+divAddress[0][4]+"\n\nReceived from "+divAddress[0][0]}});
			table12.setCellAlignment(new int[] { 0 });
			table12.setCellWidth(new int[] { 100});
			table12.setBorderDetail(3);
			
			HashMap<String ,Object> map12 = rg.joinTableDataSet(map11, table12, false, 100);
			
			TableDataSet table13 = new TableDataSet();
			table13.setData(new Object[][] { {"(Name)"}});
			table13.setCellAlignment(new int[] { 1 });
			table13.setCellWidth(new int[] { 100 });
			table13.setBorderDetail(3);
			
			HashMap<String ,Object> map13 = rg.joinTableDataSet(map12, table13, false, 100);
			
			TableDataSet table14 = new TableDataSet();
			table14.setData(new Object[][] { {"Cash/ Debit to A/c /Cheque No. :"+bean.getChequeNo()+"For Rs."+bean.getTotalTax().replace(".00", "")+"/-"}});
			table14.setCellAlignment(new int[] { 1 });
			table14.setCellWidth(new int[] { 100 });
			table14.setBorderDetail(3);
			
			HashMap<String ,Object> map14 = rg.joinTableDataSet(map13, table14, false, 100);
			
			TableDataSet table15 = new TableDataSet();
			table15.setData(new Object[][] { {"Rs. (in words)", Utility.convert(Integer.parseInt(bean.getTotalTax().trim().replace(".00", "")))}, {"Drawn on", bean.getBank()}});
			table15.setCellAlignment(new int[] { 0, 1 });
			table15.setCellWidth(new int[] { 30, 70 });
			table15.setBorderDetail(3);
			
			HashMap<String ,Object> map15 = rg.joinTableDataSet(map14, table15, false, 100);
			
			TableDataSet table16 = new TableDataSet();
			table16.setData(new Object[][] { {"(Name of the Bank and Branch)"}, 
					{"Company/Non-Company Deductees"}, 
					{"on account of Tax Deducted at Source (TDS)/Tax Collected at Source (TCS) from____(Fill up Code)"},
					{"(Strike out whichever is not applicable)"}});
			table16.setCellAlignment(new int[] { 1 });
			table16.setCellWidth(new int[] { 100 });
			table16.setBorderDetail(3);
			
			HashMap<String ,Object> map16 = rg.joinTableDataSet(map15, table16, false, 100);
			
			TableDataSet table17 = new TableDataSet();
			table17.setData(new Object[][] { {"\nfor the Assessment Year : "+assesmentYear}});
			table17.setCellAlignment(new int[] { 0});
			table17.setCellWidth(new int[] { 100 });
			table17.setBorderDetail(3);
			
			HashMap<String ,Object> map17 = rg.joinTableDataSet(map16, table17, false, 100);
			
			TableDataSet table18 = new TableDataSet();
			table18.setData(new Object[][] { {"\n\n\n\n\n\t\t\t\t\t\t\t\tFOR USE IN RECEIVING BANK \n\n\n\n\n\n\n\t\t\t\t\t\t\t\t\tDebit to A/c/Cheque credited on\n\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDD-MM-YY\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSPACE FOR BANK SEAL\n\n\n\n\n\n\n\n\n\n\nRs."}});
			table18.setCellAlignment(new int[] { 0 });
			table18.setCellWidth(new int[] {100});
			table18.setBorderDetail(3);

			TableDataSet table19 = new TableDataSet();
			table19.setData(new Object[][] { {"\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSPACE FOR BANK SEAL\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nRs."}});
			table19.setCellAlignment(new int[] { 0 });
			table19.setCellWidth(new int[] {100});
			table19.setBorderDetail(3);
			//rg.addTableToDoc(table11);
			
			HashMap<String ,Object> map18 = rg.joinTableDataSet(table18, table19, false, 100);
			
			HashMap<String ,Object> map19 = rg.joinTableDataSet(map17, map18, true, 70);
			
			rg.addTableToDoc(map19);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}
	
	
	public String convert(int number){
		 String numberString ="";
		 int div =10000000 ;
		 for (int i = 0; i < 6; i++) {
			try {
				int remain = number/(div);
				numberString += Utility.convert(remain)+",";
				if(remain !=0 && div !=0)
				number = number % div;
				if(i<2)
					div = div/100;
					else 
						div = div/10;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 numberString = numberString.replace("only", "");
		 System.out.println("numberString last==" + numberString);
		 return numberString;
	 }
/**
 * This method is used to display the challan list on starting page 
 * @param taxChallan
 * @param request
 */
	public void getChallanOnloadList(TaxChallan taxChallan,HttpServletRequest request) {
		Object[][]listData = null;
		ArrayList<Object> list = new ArrayList();
		try {
			String query = "SELECT   CHALLAN_CODE ,DECODE(CHALLAN_MONTH ,1,'JANUARY',2,'FEBRUARY',3,'MARCH',4,'APRIL',5, "
					+ " 'MAY',6,'JUNE',7,'JULY',8,'AUGUST',  9,'SEPTEMBER',10,'OCTOBER',11,'NOVEMBER',12,'DECEMBER'), "
					+ " CHALLAN_YEAR, NVL(DIV_NAME,' '), DIV_ID, CHALLAN_MONTH, ROWNUM, TO_CHAR(NVL(CHALLAN_TOTALTAX,0),9999999990.99) FROM HRMS_TAX_CHALLAN  "
					+ " INNER JOIN HRMS_DIVISION ON HRMS_DIVISION.DIV_ID = HRMS_TAX_CHALLAN.CHALLAN_DIVISION_ID "
					+ " ORDER BY CHALLAN_YEAR DESC";
			listData = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.error("exception in listData query",e);
		} //end of catch
		
		if(listData !=null && listData.length >0){
			
				String[] pageIndex = Utility.doPaging(taxChallan.getMyPage(), listData.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				} //end of if
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));

				if (pageIndex[4].equals("1"))
					taxChallan.setMyPage("1");
			
			//int count=0;
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				TaxChallan bean = new TaxChallan();
				bean.setChallanListCode(String.valueOf(listData[i][0]));
				bean.setChallanListMonth(String.valueOf(listData[i][1]));
				bean.setChallanListYear(String.valueOf(listData[i][2]));
				bean.setChallanListDivName(String.valueOf(listData[i][3]));
				bean.setChallanListDivId(String.valueOf(listData[i][4]));
				bean.setChallanListMonthId(String.valueOf(listData[i][5]));
				bean.setListRowNum(String.valueOf(listData[i][6]));
				bean.setListTotalTax(String.valueOf(listData[i][7]));
				list.add(bean);
				//count++;
			} //end of loop
			taxChallan.setTotalListRecords(String.valueOf(listData.length));
			taxChallan.setIteratorlist(list);
			//request.setAttribute("addQuestionTotalPages",(list.size() % scriptPageNo == 0)?(list.size()/scriptPageNo):(list.size()/scriptPageNo)+1);
		}else{
			taxChallan.setListNoData("true");
		} //end of else
		
	} //end of getChallanOnloadList method
/**
 * This method is used to display the challan details
 * @param taxChallan
 */
	public void getRecord(TaxChallan taxChallan) {
		String ledgerCode = "";
		Object tdsParameter[][] = null;
		taxChallan.setListFlag("true");
		taxChallan.setMonthName(getMonth(Integer.parseInt(taxChallan.getMonth())));
		if(taxChallan.getOnHold().equals("A")){
			taxChallan.setOnHold("All");
		} else if(taxChallan.getOnHold().equals("Y")){
			taxChallan.setOnHold("Yes");
		} else{
			taxChallan.setOnHold("No");
		}
		Object[][]data = null;
		try {
			String tdsParaQuery = "SELECT TDS_DEBIT_CODE,TDS_EDU_CESS,TDS_SURCHARGE_LIMIT_AMT,TDS_SURCHARGE "
					+ " FROM HRMS_TAX_PARAMETER "
					+ " WHERE TDS_CODE= (SELECT MAX(TDS_CODE) FROM HRMS_TAX_PARAMETER)";
			tdsParameter = getSqlModel().getSingleResult(tdsParaQuery);
		} catch (Exception e) {
			logger.error("exception in tdsParameter query",e);
		} //end of catch
		
		if(tdsParameter !=null && tdsParameter.length >0){
			if(taxChallan.getIncludeSalary().equals("true")){
				try {
					ledgerCode = getLedgerCode(taxChallan.getMonth(),taxChallan.getYear());
				} catch (Exception e) {
					logger.error("exception in ledgerCode in getRecord method",e);
				} //end of catch
			} //end of includeSalary if
			
			String challanQuery = getUnionQuery(taxChallan,tdsParameter,ledgerCode);
			try {
				data = getSqlModel().getSingleResult(challanQuery);
			} catch (Exception e) {
				logger.error("exception in union challan query",e);
			} //end of catch
			//logger.info("challanQuery========"+challanQuery);
			
			
			if(data!=null && data.length >0){
				getChaRecordNew(taxChallan, tdsParameter,data);
			}else{
				saveChallanHdrIfNoEmpFound(taxChallan);
				setValuesIfNoData(taxChallan);
			} //end of data else
			
		} else{
			setValuesIfNoData(taxChallan);
		} //end of tdsParameter else if null
		
	} //end of getRecordChanged method
/**
 * This method saves the challan HDR record if no data found for selected conditions
 * @param taxChallan
 */
	private void saveChallanHdrIfNoEmpFound(TaxChallan taxChallan) {
		
		String challanCode="SELECT NVL(MAX(CHALLAN_CODE),0)+1 FROM HRMS_TAX_CHALLAN";
		Object chalCode[][] = getSqlModel().getSingleResult(challanCode);
		
		Object[][] addObjHdr=new Object[1][17];
		addObjHdr[0][0]  = chalCode[0][0];
		addObjHdr[0][1]  = taxChallan.getDivId();
		addObjHdr[0][2]  = taxChallan.getMonth();
		addObjHdr[0][3]  = taxChallan.getYear();
		addObjHdr[0][4]  = taxChallan.getTax();
		addObjHdr[0][5]  = taxChallan.getSurcharge();
		addObjHdr[0][6]  = taxChallan.getEduCess();
		addObjHdr[0][7]  = taxChallan.getTotalTax();
		addObjHdr[0][8] = 0;//taxChallan.getIntAmt();
		addObjHdr[0][9] = 0;//taxChallan.getOthrAmt();
		addObjHdr[0][10] = taxChallan.getOnHold();
		logger.info("taxChallan.getOnHold()=="+taxChallan.getOnHold());
		if(taxChallan.getOnHold().equals("All")){
			addObjHdr[0][10] = "A";
		} //end of if
		else if(taxChallan.getOnHold().equals("Yes")){
			addObjHdr[0][10]="Y";
		} //end of else if
		else if(taxChallan.getOnHold().equals("No")){
			addObjHdr[0][10]="N";
		} //end of else if
		
		if(taxChallan.getIncludeSalary().equals("true")){
			addObjHdr[0][11] = "Y";
		} else {
			addObjHdr[0][11] = "N";
		}
		
		if(taxChallan.getIncludeSettlement().equals("true")){
			addObjHdr[0][12] = "Y";
		} else {
			addObjHdr[0][12] = "N";
		}
		
		/*if(taxChallan.getIncludeAllowance().equals("true")){
			addObjHdr[0][13] = "Y";
		} else {
			addObjHdr[0][13] = "N"; 
		}*/
	
		if(taxChallan.getIncludeArrears().equals("true")) {
			addObjHdr[0][13] = "Y";
		} else {
			addObjHdr[0][13] = "N";
		}
		if(taxChallan.getIncludeBonus().equals("true")) {
			addObjHdr[0][14] = "Y";
		} else {
			addObjHdr[0][14] = "N";
		}
		if(taxChallan.getIncludeLeaveEncashment().equals("true")) {
			addObjHdr[0][15] = "Y";
		} else {
			addObjHdr[0][15] = "N";
		}
		if(taxChallan.getIncludeOverTime().equals("true")) {
			addObjHdr[0][16] = "Y";
		} else {
			addObjHdr[0][16] = "N";
		}
		
		String insertHdrQuery = "INSERT INTO HRMS_TAX_CHALLAN(CHALLAN_CODE, CHALLAN_DIVISION_ID, CHALLAN_MONTH, CHALLAN_YEAR, CHALLAN_TAX, CHALLAN_SURCHARGE, "
			+" CHALLAN_EDUCESS, CHALLAN_TOTALTAX, CHALLAN_INT_AMT, CHALLAN_OTHR_AMT, CHALLAN_TAX_ONHOLD," +
			 " CHALLAN_SALARY_FLAG, CHALLAN_SETTLE_FLAG, CHALLAN_ARREARS_FLAG, CHALLAN_BONUS_FLAG, CHALLAN_LEAVE_FLAG, CHALLAN_OT_FLAG)  "
			+" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		getSqlModel().singleExecute(insertHdrQuery, addObjHdr);
		
		taxChallan.setChallanID(String.valueOf(chalCode[0][0]));
		
		fetchIncludeCheckSelections(taxChallan, String.valueOf(chalCode[0][0]));
		
	} //end of saveChallanHdrIfNoEmpFound method
/**
 * This method will calculate the split for TDS,edu cess & surcharge amt 
 * @param taxChallan
 * @param tdsParameter
 * @param data
 */
	private void getChaRecordNew(TaxChallan taxChallan,Object[][] tdsParameter, Object[][] data) {
		double surcharge=0.00,totaltds=0.00;
		double educationCess=0.00;
		double totTds=0.00,totalTax=0.00;
		
		/**
		 * this values are set...because they are used in java script for calculation
		 * if in case total tax is changed.
		 */
		taxChallan.setEduCessPercen(String.valueOf(tdsParameter[0][1]));
		taxChallan.setRebateLimit(String.valueOf(tdsParameter[0][2]));
		taxChallan.setSurchargePercen(String.valueOf(tdsParameter[0][3]));
		
		int fromYear=0;
		int toYear=0;
		if(Integer.parseInt(taxChallan.getMonth())>=4 && Integer.parseInt(taxChallan.getMonth())<=12) {
			fromYear =Integer.parseInt(taxChallan.getYear());
			toYear=fromYear+1;
		 } //end of if
		else if(Integer.parseInt(taxChallan.getMonth())>=1 && Integer.parseInt(taxChallan.getMonth())<=3) {
		     fromYear =Integer.parseInt(taxChallan.getYear())-1;
		     toYear=Integer.parseInt(taxChallan.getYear());
		} //end of else if	
		
		ArrayList<Object> challanList = new ArrayList<Object>();
		
		/*
		 * Following loop is used to set the employee id,name,challan tds,challan surcharge,education cess to every employee
		 * those who belong to the selected division
		 */
		Object empTaxIncome[][] = null;
		try {
			String empIncomeQuery = "SELECT TO_CHAR(NVL(TDS_TAXABLE_INCOME,0),9999999990.99),TDS_EMP_ID FROM HRMS_TDS "
									+" WHERE  TDS_FROM_YEAR="+fromYear+" AND TDS_TO_YEAR="+toYear+" ORDER BY TDS_EMP_ID";
			empTaxIncome = getSqlModel().getSingleResult(empIncomeQuery);
		} catch (Exception e) {
			logger.error("exception in empIncomeQuery",e);
		} //end of catch
		
		String challanCodeQuery ="SELECT NVL(MAX(CHALLAN_CODE),0)+1 FROM HRMS_TAX_CHALLAN";
		Object chalCodeObj[][] = getSqlModel().getSingleResult(challanCodeQuery);
		
		String challanCode = String.valueOf(chalCodeObj[0][0]);
		
		try{	
			for(int i=0;i<data.length;i++){
				TaxChallan bean=new TaxChallan(); 
				double amount=0.00;
				Object[][] challanTax = null;
				double chkAmount=0.00;

				if(empTaxIncome!=null && empTaxIncome.length!=0){
					for (int j = 0; j < empTaxIncome.length; j++) {
						 if(String.valueOf(empTaxIncome[j][1]).equals(String.valueOf(data[i][0]))){
							 amount=Double.parseDouble(String.valueOf(empTaxIncome[j][0]));
						 } //end of if
					} //end of loop
				} //end of if
				else{
					amount=0.00;
				} //end of else
				
				logger.info("amount is"+amount);
				 try {
					challanTax = getTaxDtls(Double.parseDouble(String.valueOf(data[i][3])), Double
							.parseDouble(String.valueOf(tdsParameter[0][2])),amount, tdsParameter);
				} catch (Exception e) {
					logger.error("exception in challanTax",e);
				} //end of catch
				logger.info("data[i][2]  emp name--->"+data[i][2]); 
				double tottds = Double.parseDouble(String.valueOf(challanTax[0][0]));
			//	totaltds += tottds;
				logger.info("totaltds-------->"+totaltds);
				double sur=Double.parseDouble(String.valueOf(challanTax[0][2]));
				//surcharge+=sur;
				//logger.info("surcharge----------in loop  2---"+surcharge);
				double cess=Double.parseDouble(String.valueOf(challanTax[0][1]));
				//educationCess+=cess;
				//logger.info("educationCess----------in loop  3---"+educationCess);
				
			    totTds+=Double.parseDouble(String.valueOf(data[i][3]));
			    
			   // logger.info("totTds----------down  4---"+totTds);
			    Object[][] data1=new Object[1][6];
			    
			    data1[0][0]=chalCodeObj[0][0];
			    data1[0][1]=data[i][0];
			    
				bean.setEmpId(String.valueOf(data[i][0]));
				bean.setEmpToken(String.valueOf(data[i][1]));
				bean.setEmpName(String.valueOf(data[i][2]));
				bean.setEmpTaxIncome(String.valueOf(amount));//this is used in java script

				chkAmount = Math.round(tottds) + Math.round(sur) + Math.round(cess);
				
				logger.info("value of chkAmount==11====>"+chkAmount);
				
				chkAmount = Double.parseDouble(String.valueOf(data[i][3])) - chkAmount;
				
				logger.info("value of data[i][3]======>"+data[i][3]);
				
				logger.info("value of chkAmount==22====>"+chkAmount);
				
				bean.setChallanTax(Utility.twoDecimals(Math.round(tottds) + chkAmount));
				
				data1[0][2]=bean.getChallanTax();
				
				logger.info("TDS-----------"+bean.getChallanTax());
				totaltds+= Double.parseDouble(String.valueOf(bean.getChallanTax()));
				bean.setChallanSurcharge(Utility.twoDecimals(Math.round(sur)));
				logger.info("ChallanSurcharge-----------"+bean.getChallanSurcharge());
				 
				data1[0][3]=bean.getChallanSurcharge();
				
				surcharge+= Double.parseDouble(String.valueOf(bean.getChallanSurcharge()));
				
				bean.setChallanEduCess(Utility.twoDecimals(Math.round(cess)));
				logger.info("ChallanEduCess-----------"+bean.getChallanEduCess());
				educationCess+= Double.parseDouble(String.valueOf(bean.getChallanEduCess()));
				
				data1[0][4]=bean.getChallanEduCess();
				
				bean.setChallanTotTax(Utility.twoDecimals(Math.round(Double.parseDouble(String.valueOf(data[i][3])))));
				totalTax+= Double.parseDouble(String.valueOf(bean.getChallanTotTax()));
				logger.info("ChallanTotTax-----------"+bean.getChallanTotTax());
				
				data1[0][5]=bean.getChallanTotTax();
				
				challanList.add(data1);
			} //end of loop
			//taxChallan.setChallanList(challanList);
			
			/*
			 * Following code sets the total sum of the surcharge,education cess and total tax amount.
			 */
			taxChallan.setTax(Utility.twoDecimals(totaltds));
			taxChallan.setSurcharge(Utility.twoDecimals(surcharge));
			taxChallan.setEduCess(Utility.twoDecimals(educationCess));
			taxChallan.setTotalTax(Utility.twoDecimals(totalTax));
			
			try {
				saveAndNext(taxChallan, challanList, challanCode);
			} catch (Exception e) {
				logger.error("exception in saveAndNext method",e);
			} //end of catch
			
		}catch(Exception e){
			logger.error("exception in HRMS_SAL_DEBITS object loop",e);
		} //end of catch
	} //end of getChaRecordNew method
/**
 * This method saves the records in HRMS_TAX_CHALLAN & HRMS_TAX_CHALLAN_DTL
 * @param taxChallan
 * @param challanList
 * @param chalCode
 */
	private void saveAndNext(TaxChallan taxChallan, ArrayList challanList, String chalCode) {
		
		Object[][] addObjHdr=new Object[1][17];
		addObjHdr[0][0]  = chalCode;
		addObjHdr[0][1]  = taxChallan.getDivId();
		addObjHdr[0][2]  = taxChallan.getMonth();
		addObjHdr[0][3]  = taxChallan.getYear();
		addObjHdr[0][4]  = taxChallan.getTax();
		addObjHdr[0][5]  = taxChallan.getSurcharge();
		addObjHdr[0][6]  = taxChallan.getEduCess();
		addObjHdr[0][7]  = taxChallan.getTotalTax();
		addObjHdr[0][8] = 0;//taxChallan.getIntAmt();
		addObjHdr[0][9] = 0;//taxChallan.getOthrAmt();
		addObjHdr[0][10] = taxChallan.getOnHold();
		logger.info("taxChallan.getOnHold()=="+taxChallan.getOnHold());
		if(taxChallan.getOnHold().equals("All")){
			addObjHdr[0][10] = "A";
		} //end of if
		else if(taxChallan.getOnHold().equals("Yes")){
			addObjHdr[0][10]="Y";
		} //end of else if
		else if(taxChallan.getOnHold().equals("No")){
			addObjHdr[0][10]="N";
		} //end of else if
		
		if(taxChallan.getIncludeSalary().equals("true"))
			addObjHdr[0][11] = "Y";
		else
			addObjHdr[0][11] = "N";
		
		if(taxChallan.getIncludeSettlement().equals("true"))
			addObjHdr[0][12] = "Y";
		else
			addObjHdr[0][12] = "N";
		
		/*if(taxChallan.getIncludeAllowance().equals("true"))
			addObjHdr[0][13] = "Y";
		else
			addObjHdr[0][13] = "N";*/
	
		if(taxChallan.getIncludeArrears().equals("true"))
			addObjHdr[0][13] = "Y";
		else
			addObjHdr[0][13] = "N";
		
		if(taxChallan.getIncludeBonus().equals("true")) {
			addObjHdr[0][14] = "Y";
		} else {
			addObjHdr[0][14] = "N";
		}
		if(taxChallan.getIncludeLeaveEncashment().equals("true")) {
			addObjHdr[0][15] = "Y";
		} else {
			addObjHdr[0][15] = "N";
		}
		if(taxChallan.getIncludeOverTime().equals("true")) {
			addObjHdr[0][16] = "Y";
		} else {
			addObjHdr[0][16] = "N";
		}
		
	
		String insertHdrQuery = "INSERT INTO HRMS_TAX_CHALLAN(CHALLAN_CODE,CHALLAN_DIVISION_ID,CHALLAN_MONTH,CHALLAN_YEAR,CHALLAN_TAX,CHALLAN_SURCHARGE, "
			+" CHALLAN_EDUCESS,CHALLAN_TOTALTAX,CHALLAN_INT_AMT,CHALLAN_OTHR_AMT,CHALLAN_TAX_ONHOLD," +
			 " CHALLAN_SALARY_FLAG,CHALLAN_SETTLE_FLAG,CHALLAN_ARREARS_FLAG, CHALLAN_BONUS_FLAG, CHALLAN_LEAVE_FLAG, CHALLAN_OT_FLAG)  "
			+" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		
		if(taxChallan.getChallanID()==null || taxChallan.getChallanID().equals("") ||  
				taxChallan.getChallanID().equals("null") ||  taxChallan.getChallanID().equals(null)){
			
			Object[][]dtlData = new Object[challanList.size()][5];
			
		
			 for (int i = 0; i < challanList.size(); i++) {
				 dtlData[i]=((Object[][])challanList.get(i))[0];
			} //end of loop
			 
			/* for (int i = 0; i < dtlData.length; i++) {
				 logger.info("======================================");
				for (int j = 0; j < dtlData[0].length; j++) {
					logger.info("finalObject====="+dtlData[i][j]);
				}
			}*/
			 boolean hdrFlag = false;
			 try {
				hdrFlag =getSqlModel().singleExecute(insertHdrQuery, addObjHdr);
			} catch (Exception e) {
				logger.info("exception in insertHdrQuery",e);
			} //end of catch 
			
			String addChallan = "INSERT INTO HRMS_TAX_CHALLAN_DTL(CHALLAN_CODE,EMP_ID," +
			 		"CHALLAN_TDS,CHALLAN_SURCHARGE,CHALLAN_EDU_CESS,CHALLAN_TOTAL_TAX)"
					+ " VALUES(?,?,?,?,?,?)";
			if(hdrFlag){
				getSqlModel().singleExecute(addChallan, dtlData);
			} //end of if
			
			taxChallan.setChallanID(chalCode);
			
		} //end of insert if
		fetchIncludeCheckSelections(taxChallan, chalCode);
	} //end of saveAndNext method

	private void setValuesIfNoData(TaxChallan taxChallan) {
		taxChallan.setTax("0.00");
		taxChallan.setSurcharge("0.00");
		taxChallan.setEduCess("0.00");
		taxChallan.setTotalTax("0.00");
		taxChallan.setIntAmt("0.00");
		taxChallan.setOthrAmt("0.00");
		taxChallan.setNoData("true");
	} //end of setValuesIfNoData method
/**
 * This method returns the query to retrieve the TAX deduction details deducted in various income
 * as selected on the form
 * @param taxChallan
 * @param tdsParameter
 * @param ledgerCode
 * @return
 */
	private String getUnionQuery(TaxChallan taxChallan,Object[][] tdsParameter, String ledgerCode) {
		
		String salaryQuery = "";
		String arrearstQuery = "";
		String settlementQuery = "";
		//String allowanceQuery = "";
		String challanQuery = "";
		String bonusQuery = "";
		String leaveEncashmentQuery = "";
		String OverTimeQuery = "";
		//-------------------------Start of query---------------------------------------
		try{
		challanQuery = "SELECT EMPID, ETOKEN, ENAME, TO_CHAR(SUM(AMT),9999999990.99) FROM ( ";
		
			if(ledgerCode != null && ledgerCode.length() > 0){
				/**
				 * Query for Salary
				 */
				salaryQuery="SELECT DISTINCT HRMS_SAL_DEBITS_"+taxChallan.getYear()+".EMP_ID AS EMPID,EMP_TOKEN AS ETOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS ENAME "
				+" ,TO_CHAR(SUM(NVL(SAL_AMOUNT,0)),9999999990.99)  AS AMT  "
				+" FROM HRMS_SAL_DEBITS_"+taxChallan.getYear()+"  "
				+" INNER JOIN HRMS_EMP_OFFC ON HRMS_SAL_DEBITS_"+taxChallan.getYear()+".EMP_ID = HRMS_EMP_OFFC.EMP_ID "  
				+" INNER JOIN HRMS_SALARY_"+taxChallan.getYear()+"  ON (HRMS_SALARY_"+taxChallan.getYear()+".EMP_ID = HRMS_SAL_DEBITS_"+taxChallan.getYear()+".EMP_ID " 
				+" and HRMS_SALARY_"+taxChallan.getYear()+".SAL_LEDGER_CODE = HRMS_SAL_DEBITS_"+taxChallan.getYear()+".SAL_LEDGER_CODE) "
				+" WHERE HRMS_SALARY_"+taxChallan.getYear()+".SAL_DIVISION="+taxChallan.getDivId()+" AND SAL_DEBIT_CODE="+String.valueOf(tdsParameter[0][0])+" AND SAL_LEDGER_CODE IN("+ledgerCode+") " +
				 " AND HRMS_SAL_DEBITS_"+taxChallan.getYear()+".EMP_ID NOT IN(SELECT EMP_ID FROM HRMS_TAX_CHALLAN_DTL "
				+" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE ) "
				+" WHERE CHALLAN_MONTH = "+taxChallan.getMonth()+" AND CHALLAN_YEAR="+taxChallan.getYear()+" AND CHALLAN_DIVISION_ID="+taxChallan.getDivId()+")";
				logger.info("taxChallan.getOnHold()======"+taxChallan.getOnHold());
				if(taxChallan.getOnHold().equals("Yes")){
					salaryQuery += " AND SAL_ONHOLD='Y' ";
				} //end of if
				else if(taxChallan.getOnHold().equals("No")){
					salaryQuery += " AND SAL_ONHOLD='N' ";
				} //end of else if
				salaryQuery+=" GROUP BY HRMS_SAL_DEBITS_"+taxChallan.getYear()+".EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ";
				
			} //end of ledgerCode if
			
			challanQuery +=salaryQuery;
			if(taxChallan.getIncludeArrears().equals("true")){
				if(taxChallan.getIncludeSalary().equals("true")){
					arrearstQuery +=" UNION ";
				} //end of if
			
			/**
			 * Query for Arrears 
			 */
			arrearstQuery +=" SELECT ARREARS_EMP_ID AS EMPID,EMP_TOKEN AS ETOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS ENAME, "
				+" TO_CHAR(SUM(NVL(ARREARS_AMT,0)),9999999990.99) AS AMT  "
				+" FROM HRMS_ARREARS_DEBIT_"+taxChallan.getYear()+" "
				+" INNER JOIN HRMS_DEBIT_HEAD ON HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_ARREARS_DEBIT_"+taxChallan.getYear()+".ARREARS_DEBIT_CODE " 
				+" INNER JOIN HRMS_EMP_OFFC ON HRMS_ARREARS_DEBIT_"+taxChallan.getYear()+".ARREARS_EMP_ID = HRMS_EMP_OFFC.EMP_ID "
				+" WHERE  ARREARS_CODE IN (SELECT ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE ARREARS_PAID_MONTH = "+taxChallan.getMonth()+" "   
				+" AND ARREARS_PAID_YEAR = "+taxChallan.getYear()+" AND ARREARS_DIVISION = "+taxChallan.getDivId()+") AND ARREARS_DEBIT_CODE = "+String.valueOf(tdsParameter[0][0])+" " +
				 " AND ARREARS_EMP_ID NOT IN (SELECT EMP_ID FROM HRMS_TAX_CHALLAN_DTL "
				+" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE AND CHALLAN_ARREARS_FLAG='Y') "
				+" WHERE CHALLAN_MONTH = "+taxChallan.getMonth()+" AND CHALLAN_YEAR="+taxChallan.getYear()+" AND CHALLAN_DIVISION_ID="+taxChallan.getDivId()+") "
				+" GROUP BY ARREARS_EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ";
			
		} //end of includeArrears if
			
			challanQuery +=arrearstQuery;
			if(taxChallan.getIncludeSettlement().equals("true")){
				if(taxChallan.getIncludeSalary().equals("true")){
					settlementQuery +=" UNION ";
				} //end of if
				settlementQuery += " SELECT SETTL_ECODE AS EMPID,EMP_TOKEN AS ETOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS ENAME, "
				+" TO_CHAR(SUM(NVL(SETTL_AMT,0)),9999999990.99)+TO_CHAR(nvl((SETTL_TAX_AMT),0),9999999990.99) AS AMT FROM HRMS_SETTL_DEBITS "
				+" INNER JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_DEBITS.SETTL_CODE) "
				+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SETTL_HDR.SETTL_ECODE) "
				+" INNER JOIN HRMS_RESIGN ON (HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO) " 
				+" WHERE (SETTL_MTH_TYPE IN ('OH','CO')) AND RESIGN_WITHDRAWN = 'N' AND EMP_DIV = "+taxChallan.getDivId()+" "
				+" AND TO_CHAR(SETTL_SETTLDT,'MM')="+taxChallan.getMonth()+" AND TO_CHAR(SETTL_SETTLDT,'YYYY')="+taxChallan.getYear()+" "
				+" AND SETTL_DEBIT_CODE="+String.valueOf(tdsParameter[0][0])+" " +
				 " AND SETTL_ECODE NOT IN (SELECT EMP_ID FROM HRMS_TAX_CHALLAN_DTL "
				+" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "
				+" WHERE CHALLAN_MONTH = "+taxChallan.getMonth()+" AND CHALLAN_YEAR="+taxChallan.getYear()+" AND CHALLAN_DIVISION_ID="+taxChallan.getDivId()+") "   
				+" GROUP BY SETTL_ECODE,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,SETTL_TAX_AMT ";
				
			} //end of includeSettlement if
			
			challanQuery +=settlementQuery;
			
			/*if(taxChallan.getIncludeAllowance().equals("true")){
				taxChallan.setHidIncludeAllow("true");
				if(taxChallan.getIncludeSalary().equals("true") || taxChallan.getIncludeSettlement().equals("true")){
					allowanceQuery +=" UNION ";
				} //end of if
				allowanceQuery += " SELECT HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID AS EMPID,EMP_TOKEN AS ETOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS ENAME, "
				+" NVL(SUM(ALLW_TAX_AMT),0) AS AMT "
				+" FROM HRMS_ALLOWANCE_HDR "
				+" INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON (HRMS_ALLOWANCE_EMP_DTL.ALLW_ID = HRMS_ALLOWANCE_HDR.ALLW_ID) "
				+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID) "
				+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ALLOWANCE_HDR.ALLW_CREDIT_HEAD) "
				+" AND TO_CHAR(ALLW_PROCESS_DATE,'MM')="+taxChallan.getMonth()+"  "
				+" AND TO_CHAR(ALLW_PROCESS_DATE,'YYYY')="+taxChallan.getYear()+" " 
				+" AND HRMS_ALLOWANCE_HDR.ALLW_DIVISION = "+taxChallan.getDivId()+" " +
				 " AND HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID NOT IN (SELECT EMP_ID FROM HRMS_TAX_CHALLAN_DTL "
				+" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "
				+" WHERE CHALLAN_MONTH = "+taxChallan.getMonth()+" AND CHALLAN_YEAR="+taxChallan.getYear()+" AND CHALLAN_DIVISION_ID="+taxChallan.getDivId()+") "
				+" GROUP BY HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ";
				
			} //end of includeAllowance if
			
			challanQuery +=allowanceQuery;*/
			if(taxChallan.getIncludeBonus().equals("true")){
				if(taxChallan.getIncludeSalary().equals("true")){
					bonusQuery +=" UNION ";
				}
				
				bonusQuery += " SELECT HRMS_BONUS_EMP.EMP_ID AS EMPID, NVL(EMP_TOKEN,' ') AS ETOKEN, NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' ') AS ENAME, TO_CHAR(SUM(NVL(HRMS_BONUS_EMP.BONUS_TAX_AMT,0)),9999999990.99) AS AMT" 
					+ " FROM HRMS_BONUS_EMP "
					+ " INNER JOIN HRMS_BONUS_HDR ON (HRMS_BONUS_HDR.BONUS_CODE = HRMS_BONUS_EMP.BONUS_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_BONUS_EMP.EMP_ID)"
					+ " WHERE HRMS_BONUS_HDR.DIV_CODE="+taxChallan.getDivId()+" AND HRMS_BONUS_HDR.PAY_MONTH="+taxChallan.getMonth()+" AND HRMS_BONUS_HDR.PAY_YEAR = "+taxChallan.getYear()
					+ " AND HRMS_BONUS_HDR.PAY_IN_SAL='N' AND  HRMS_BONUS_HDR.DEDUCT_TAX='Y'"
					+ " GROUP BY HRMS_BONUS_EMP.EMP_ID, NVL(EMP_TOKEN,' '), NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' ')";
				
			} 
			challanQuery +=bonusQuery;
			
			if(taxChallan.getIncludeLeaveEncashment().equals("true")){
				if(taxChallan.getIncludeSalary().equals("true")){
					leaveEncashmentQuery +=" UNION ";
				}
				leaveEncashmentQuery += " SELECT HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID AS EMPID, NVL(EMP_TOKEN,' ') AS ETOKEN, NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' ') AS ENAME, TO_CHAR(SUM(NVL(ENCASHMENT_TDS_AMOUNT,0)),9999999990.99) AS AMT"
					+ " FROM HRMS_ENCASHMENT_PROCESS_DTL "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID) "
					+ " INNER JOIN HRMS_ENCASHMENT_PROCESS_HDR ON (HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_CODE = HRMS_ENCASHMENT_PROCESS_DTL.ENCASHMENT_PROCESS_CODE)"
					+ " WHERE HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_PROCESS_DIVISION ="+taxChallan.getDivId()+" AND HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_INCLUDE_SAL_MONTH = "+taxChallan.getMonth()
					+ " AND HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_INCLUDE_SAL_YEAR= "+taxChallan.getYear()+" AND HRMS_ENCASHMENT_PROCESS_HDR.ENCASHMENT_INCLUDE_SAL_FLAG = 'N'"
					+ " GROUP BY HRMS_ENCASHMENT_PROCESS_DTL.EMP_ID, NVL(EMP_TOKEN,' '), NVL(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,' ')";

			} 
			challanQuery +=leaveEncashmentQuery;
			
			if(taxChallan.getIncludeOverTime().equals("true")){
				if(taxChallan.getIncludeSalary().equals("true")){
					OverTimeQuery +=" UNION ";
				}
				OverTimeQuery += "";
			} 
			challanQuery +=OverTimeQuery;
			
			challanQuery +=" ) GROUP BY EMPID, ETOKEN, ENAME";
		}catch(Exception e){
			logger.error("exception in union challan query",e);
		} //end of catch
		return challanQuery;
	} //end of getUnionQuery method
	/**
	 * This method get the selected income details 
	 * @param taxChallan
	 * @param challanCode
	 */
	public void fetchIncludeCheckSelections(TaxChallan taxChallan, String challanCode){
		String query = "SELECT DECODE(CHALLAN_SALARY_FLAG,'Y','true','N','false'),  DECODE(CHALLAN_SETTLE_FLAG,'Y','true','N','false','false'), "
			+ " DECODE(CHALLAN_ARREARS_FLAG,'Y','true','N','false','false'), DECODE(CHALLAN_BONUS_FLAG,'Y','true','N','false','false'), "
			+ " DECODE(CHALLAN_LEAVE_FLAG,'Y','true','N','false','false'),  DECODE(CHALLAN_OT_FLAG,'Y','true','N','false','false')"
			+ " FROM HRMS_TAX_CHALLAN  WHERE CHALLAN_CODE = "+challanCode;
		
		Object [][] chkSelectionData = getSqlModel().getSingleResult(query);
		
		taxChallan.setIncludeSalary(String.valueOf(chkSelectionData[0][0]));
		taxChallan.setIncludeSettlement(String.valueOf(chkSelectionData[0][1]));
		taxChallan.setIncludeArrears(String.valueOf(chkSelectionData[0][2]));
		taxChallan.setIncludeBonus(String.valueOf(chkSelectionData[0][3]));
		taxChallan.setIncludeLeaveEncashment(String.valueOf(chkSelectionData[0][4]));
		taxChallan.setIncludeOverTime(String.valueOf(chkSelectionData[0][5]));
		
	}
	/**
	 * This method updates the challan details on 2nd page
	 * @param taxChallan
	 * @return
	 */
	public String saveOnNextPage(TaxChallan taxChallan) {
		
		taxChallan.setMonthName(getMonth(Integer.parseInt(taxChallan.getMonth())));
		Object[][] update1=new Object[1][2];
		update1[0][0]= taxChallan.getChequeNo();
		update1[0][1]= taxChallan.getChequeDate();
		String chqQuery="UPDATE HRMS_TAX_CHALLAN SET CHALLAN_CHQNO= ?,CHALLAN_CHQDATE=TO_DATE(?,'DD-MM-YYYY')  WHERE CHALLAN_CODE="+taxChallan.getChallanID();
		
		Object[][] update2=new Object[1][2];
		update2[0][0]=taxChallan.getChallanNo();
		update2[0][1]=taxChallan.getChallanDate();
		String chnQuery="UPDATE HRMS_TAX_CHALLAN SET CHALLAN_NO= ?,CHALLAN_DATE=TO_DATE(?,'DD-MM-YYYY')  WHERE CHALLAN_CODE="+taxChallan.getChallanID();
		
		//UPDATED BY REEBA
		if(taxChallan.getBookEntry().equals("Y")){
			 try {
				getSqlModel().singleExecute(chqQuery, update1);
			} catch (Exception e) {
				logger.error("exception in update1",e);
			} //end of catch
		 } //end of if 
		 if(taxChallan.getBookEntry().equals("N")){
			 try {
				getSqlModel().singleExecute(chnQuery, update2);
			} catch (Exception e) {
				logger.error("exception in update2",e);
			} //end of catch
		 } //end of if
		 
		 Object[][] addObj=new Object[1][7];
			addObj[0][0]  = checkNull(taxChallan.getBsrCode());
			addObj[0][1] = taxChallan.getAckNo();
			addObj[0][2] = taxChallan.getVchrNo();
			addObj[0][3] = taxChallan.getIntAmt();
			addObj[0][4] = taxChallan.getOthrAmt();
			addObj[0][5] = taxChallan.getBookEntry();
			addObj[0][6] = taxChallan.getPaymentDate();
		 
		 String updQuery = "UPDATE HRMS_TAX_CHALLAN SET  CHALLAN_BANK= ?, "
				+" CHALLAN_ACK_NO = ?,CHALLAN_VCHR_NO=?,CHALLAN_INT_AMT=?,CHALLAN_OTHR_AMT=?," +
				 " CHALLAN_BOOK_ENTRY=?,CHALLAN_DATE_PAYMENT=TO_DATE(?,'DD-MM-YYYY')  " +
				 " WHERE CHALLAN_CODE="+taxChallan.getChallanID()+" ";
		 	try {
				getSqlModel().singleExecute(updQuery, addObj);
			} catch (Exception e) {
				logger.error("exception in addObj",e);
			} //end of catch
			fetchIncludeCheckSelections(taxChallan, taxChallan.getChallanID());
			return "1";
	} //end of saveOnNextPage method
/**
 * This method will display the employee list with TDS & education cess split
 * @param taxChallan
 * @param request
 */
	public void viewEmployees(TaxChallan taxChallan, HttpServletRequest request) {
		request.setAttribute("highlightRec", -1);
		Object data[][] = null;
		try {
			String challanQuery = "SELECT HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE,HRMS_TAX_CHALLAN_DTL.EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_TAX_CHALLAN_DTL.CHALLAN_TDS,"
					+ " TO_CHAR(NVL(HRMS_TAX_CHALLAN_DTL.CHALLAN_SURCHARGE,0), 9999999990.99),TO_CHAR(NVL(HRMS_TAX_CHALLAN_DTL.CHALLAN_EDU_CESS,0), 9999999990.99), TO_CHAR(NVL(HRMS_TAX_CHALLAN_DTL.CHALLAN_TOTAL_TAX,0), 9999999990.99) FROM"
					+ " HRMS_TAX_CHALLAN_DTL INNER JOIN HRMS_TAX_CHALLAN ON(HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE=HRMS_TAX_CHALLAN.CHALLAN_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_TAX_CHALLAN_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " WHERE HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE="
					+ taxChallan.getChallanID()
					+ " ORDER BY EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME";
			data = getSqlModel().getSingleResult(challanQuery);
		} catch (Exception e) {
			logger.error("exception in challanQuery in getChallanRecords method",e);
		} //end of catch
		
		if(data!=null && data.length> 0){
			
			String[] pageIndex = Utility.doPaging(taxChallan.getMyPage(), data.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			} //end of if
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));

			if (pageIndex[4].equals("1"))
				taxChallan.setMyPage("1");
			
		taxChallan.setListFlag("true");
		ArrayList<Object> chList = new ArrayList<Object>();
		try{
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
				TaxChallan bean=new TaxChallan(); 
				bean.setChallanCode(String.valueOf(data[i][0]));//Challan Code
				bean.setEmpId(String.valueOf(data[i][1]));//Employee Id
				bean.setEmpToken(String.valueOf(data[i][2]));//Token No.
				bean.setEmpName(String.valueOf(data[i][3]));//Emp Name
				bean.setChallanTax(Utility.twoDecimals(String.valueOf(data[i][4])));//Tds
				bean.setChallanSurcharge(Utility.twoDecimals(String.valueOf(data[i][5])));//Surcharge
				bean.setChallanEduCess(Utility.twoDecimals(String.valueOf(data[i][6])));//Education cess
				bean.setChallanTotTax(Utility.twoDecimals(String.valueOf(data[i][7])));	//Total Tax
			
				chList.add(bean);
			} //end of loop
		}catch (Exception e) {
			logger.error("exception in data loop",e);
		} //end of catch
		    taxChallan.setChallanList(chList);
		} //end of if
		else{
			taxChallan.setNoData("true");
		} //end of else
		
		int fromYear=0;
		int toYear=0;
		if(Integer.parseInt(taxChallan.getMonth())>=4 && Integer.parseInt(taxChallan.getMonth())<=12) {
			fromYear =Integer.parseInt(taxChallan.getYear());
			toYear=fromYear+1;
		 } //end of if
		else if(Integer.parseInt(taxChallan.getMonth())>=1 && Integer.parseInt(taxChallan.getMonth())<=3) {
		     fromYear =Integer.parseInt(taxChallan.getYear())-1;
		     toYear=Integer.parseInt(taxChallan.getYear());
		} //end of else if	
		
		String empIncomeQuery = "SELECT TO_CHAR(NVL(TDS_TAXABLE_INCOME,0), 9999999990.99),TDS_EMP_ID FROM HRMS_TDS " 
		+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_TDS.TDS_EMP_ID) "
		+" WHERE  TDS_FROM_YEAR="+fromYear+" AND TDS_TO_YEAR="+toYear+" AND EMP_DIV = "+taxChallan.getDivId()+" "
		+" ORDER BY TDS_EMP_ID";
		Object empTaxIncome[][] = getSqlModel().getSingleResult(empIncomeQuery);
		 try {
				if(empTaxIncome != null && empTaxIncome.length != 0){
					  ArrayList<Object> valuesList = new ArrayList<Object>();
					  for (int i = 0; i < empTaxIncome.length; i++) {
							  TaxChallan bean = new TaxChallan();
							  bean.setHidListEmpId(String.valueOf(empTaxIncome[i][1]));
							  bean.setHidListTdsIncome(String.valueOf(empTaxIncome[i][0]));
							  valuesList.add(bean);
					}
					taxChallan.setValuesList(valuesList);
				  }
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		
		taxChallan.setEnterTotalTax("0");
		taxChallan.setAddedTds("0");
		taxChallan.setAddedSurcharge("0");
		taxChallan.setAddedEduCess("0");
		taxChallan.setAddEmpId("");
		taxChallan.setAddEmpToken("");
		taxChallan.setAddEmpName("");
		taxChallan.setF9AddEmpFlag("false");
	} //end of viewEmployees method
/**
 * This method updates one single employee record in HRMS_TAX_CHALLAN_DTL & HRMS_TAX_CHALLAN
 * @param taxChallan
 * @param request
 * @return
 */
	public String addEmpSave(TaxChallan taxChallan, HttpServletRequest request) {
		String value = "";
		try {
			String updateChallanHdr = "UPDATE HRMS_TAX_CHALLAN SET CHALLAN_TAX= "+taxChallan.getTax()+", " 
				+" CHALLAN_SURCHARGE="+taxChallan.getSurcharge()+", CHALLAN_EDUCESS="+taxChallan.getEduCess()+", "
				+" CHALLAN_TOTALTAX="+taxChallan.getTotalTax()+" WHERE CHALLAN_CODE ="+taxChallan.getChallanID()+" ";
			getSqlModel().singleExecute(updateChallanHdr);
		} catch (Exception e) {
			logger.error("exception in updateChallanHdr",e);
		} //end of catch
		
		if(taxChallan.getF9AddEmpFlag().equals("false")){
			try {
				String updateEmpDtl ="UPDATE HRMS_TAX_CHALLAN_DTL SET CHALLAN_TOTAL_TAX ="+taxChallan.getEnterTotalTax()+" , " +
				 " CHALLAN_TDS ="+taxChallan.getAddedTds()+" , " 
				+" CHALLAN_SURCHARGE ="+taxChallan.getAddedSurcharge()+" , CHALLAN_EDU_CESS ="+taxChallan.getAddedEduCess()+" "
				+" WHERE CHALLAN_CODE ="+taxChallan.getChallanID()+" AND EMP_ID ="+taxChallan.getAddEmpId()+" "; 
				getSqlModel().singleExecute(updateEmpDtl);
			} catch (Exception e) {
				logger.error("exception in updateEmpDtl",e);
			} //end of catch	
			value= "2";
		} //end of if
		else{
			Object[][]addEmpObj = new Object[1][6];
			addEmpObj[0][0] = taxChallan.getChallanID();
			addEmpObj[0][1] = taxChallan.getAddEmpId();
			addEmpObj[0][2] = taxChallan.getEnterTotalTax();
			addEmpObj[0][3] = taxChallan.getAddedTds();
			addEmpObj[0][4] = taxChallan.getAddedSurcharge();
			addEmpObj[0][5] = taxChallan.getAddedEduCess();
			
			String insertEmpDtl = "INSERT INTO HRMS_TAX_CHALLAN_DTL (CHALLAN_CODE, EMP_ID, CHALLAN_TOTAL_TAX, " 
				+" CHALLAN_TDS, CHALLAN_SURCHARGE, CHALLAN_EDU_CESS) "
				+" VALUES(?,?,?,?,?,?)";
			try {
				getSqlModel().singleExecute(insertEmpDtl, addEmpObj);
			} catch (Exception e) {
				logger.error("exception in insertEmpDtl",e);
			} //end of catch
			value= "1";
		} //end of else
		
		viewEmployees(taxChallan,request);
		
		return value;
	} //end of addEmpSave method
/**
 * This method deletes the single employee record
 * @param taxChallan
 * @param request
 * @return
 */
	public String deleteEmp(TaxChallan taxChallan, HttpServletRequest request) {
		String empId = request.getParameter("delEmpId");
		String challanId = request.getParameter("challanId");
		
		try {
			String updateChallanHdr = "UPDATE HRMS_TAX_CHALLAN SET CHALLAN_TAX= "+taxChallan.getTax()+", " 
				+" CHALLAN_SURCHARGE="+taxChallan.getSurcharge()+", CHALLAN_EDUCESS="+taxChallan.getEduCess()+", "
				+" CHALLAN_TOTALTAX="+taxChallan.getTotalTax()+" WHERE CHALLAN_CODE ="+taxChallan.getChallanID()+" ";
			getSqlModel().singleExecute(updateChallanHdr);
		} catch (Exception e) {
			logger.error("exception in updateChallanHdr",e);
		} //end of catch
		
		try {
			String delEmpDtl = "DELETE FROM HRMS_TAX_CHALLAN_DTL WHERE CHALLAN_CODE="+challanId+" " +
							   " AND EMP_ID="+empId+"";
			getSqlModel().singleExecute(delEmpDtl);
		} catch (Exception e) {
			logger.error("exception in deleteEmp",e);
		} //end of catch
		viewEmployees(taxChallan,request);
		return "1";
	} //end of deleteEmp method
/**
 * This method searches the employee record in the dispalyed list highlighted with different co
 * @param taxChallan
 * @param request
 */
	public void searchEmployee(TaxChallan taxChallan, HttpServletRequest request) {
		Object data[][] = null;
		try {
			String challanQuery = "SELECT HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE,HRMS_TAX_CHALLAN_DTL.EMP_ID,EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,HRMS_TAX_CHALLAN_DTL.CHALLAN_TDS,"
					+ " TO_CHAR(NVL(HRMS_TAX_CHALLAN_DTL.CHALLAN_SURCHARGE,0), 9999999990.99),TO_CHAR(NVL(HRMS_TAX_CHALLAN_DTL.CHALLAN_EDU_CESS,0), 9999999990.99),TO_CHAR(NVL(HRMS_TAX_CHALLAN_DTL.CHALLAN_TOTAL_TAX,0), 9999999990.99) " 
					+ " FROM HRMS_TAX_CHALLAN_DTL "
					+ " INNER JOIN HRMS_TAX_CHALLAN ON(HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE=HRMS_TAX_CHALLAN.CHALLAN_CODE)"
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_TAX_CHALLAN_DTL.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
					+ " WHERE HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE="
					+ taxChallan.getChallanID()
					+ " ORDER BY EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME";
			data = getSqlModel().getSingleResult(challanQuery);
		} catch (Exception e) {
			logger.error("exception in challanQuery in getChallanRecords method",e);
		} //end of catch
		
		if(data.length> 0 && data!=null){
			int rownum = 0;
			for (int i = 0; i < data.length; i++) {
				if(String.valueOf(data[i][1]).equals(taxChallan.getSearchForEmpId())){
					rownum = i;
				} //end of if
			} //end of loop
			logger.info("rownum======"+rownum);
			int recPerPage = 20;
			String[] pageIndex = Utility.doPaging(taxChallan.getMyPage(), data.length, recPerPage);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			} //end of if
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			

			if (pageIndex[4].equals("1"))
				taxChallan.setMyPage("1");
			
			taxChallan.setListFlag("true");
			ArrayList<Object> chList = new ArrayList<Object>();
			
			int startRecNo = 0;
			int endRecNo = 0;
			
			int pageDiv = rownum/recPerPage;
			
			startRecNo = pageDiv * recPerPage;
			endRecNo = startRecNo + recPerPage - 1;
			
			if(data.length <= endRecNo){
				endRecNo = data.length-1;
			}
			
			logger.info("startRecNo======"+startRecNo);
			logger.info("endRecNo======"+endRecNo);
			int pageRemainder = (rownum+1)%recPerPage;
			logger.info("pageRemainder======"+pageRemainder);
			
			int highlightRec = 0;
			
			
			if(pageRemainder > 0){
				pageDiv = pageDiv+1;
				highlightRec = pageRemainder - 1;
			}
			else{
				pageDiv = pageDiv+1;
				highlightRec = recPerPage - 1;
			}
			request.setAttribute("highlightRec", highlightRec);
			logger.info("highlightRec======"+highlightRec);
			request.setAttribute("pageNo", pageDiv);
			taxChallan.setMyPage(String.valueOf(pageDiv));
			
			try{
				for (int i = startRecNo; i <= endRecNo; i++) {
					TaxChallan bean=new TaxChallan(); 
					bean.setChallanCode(String.valueOf(data[i][0]));//Challan Code
					bean.setEmpId(String.valueOf(data[i][1]));//Employee Id
					bean.setEmpToken(String.valueOf(data[i][2]));//Token No.
					bean.setEmpName(String.valueOf(data[i][3]));//Emp Name
					bean.setChallanTax(Utility.twoDecimals(String.valueOf(data[i][4])));//Tds
					bean.setChallanSurcharge(Utility.twoDecimals(String.valueOf(data[i][5])));//Surcharge
					bean.setChallanEduCess(Utility.twoDecimals(String.valueOf(data[i][6])));//Education cess
					bean.setChallanTotTax(Utility.twoDecimals(String.valueOf(data[i][7])));	//Total Tax
				
					chList.add(bean);
				} //end of loop
			}catch (Exception e) {
				logger.error("exception in data loop",e);
			} //end of catch
			    taxChallan.setChallanList(chList);
		} //end of if
		
		int fromYear=0;
		int toYear=0;
		if(Integer.parseInt(taxChallan.getMonth())>=4 && Integer.parseInt(taxChallan.getMonth())<=12) {
			fromYear =Integer.parseInt(taxChallan.getYear());
			toYear=fromYear+1;
		 } //end of if
		else if(Integer.parseInt(taxChallan.getMonth())>=1 && Integer.parseInt(taxChallan.getMonth())<=3) {
		     fromYear =Integer.parseInt(taxChallan.getYear())-1;
		     toYear=Integer.parseInt(taxChallan.getYear());
		} //end of else if	
		
		String empIncomeQuery = "SELECT TO_CHAR(NVL(TDS_TAXABLE_INCOME,0), 9999999990.99),TDS_EMP_ID FROM HRMS_TDS " 
			+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_TDS.TDS_EMP_ID) "
			+" WHERE  TDS_FROM_YEAR="+fromYear+" AND TDS_TO_YEAR="+toYear+" AND EMP_DIV = 22 "
			+" ORDER BY TDS_EMP_ID";
			Object empTaxIncome[][] = getSqlModel().getSingleResult(empIncomeQuery);
			 try {
					if(empTaxIncome != null && empTaxIncome.length != 0){
						  ArrayList<Object> valuesList = new ArrayList<Object>();
						  for (int i = 0; i < empTaxIncome.length; i++) {
								  TaxChallan bean = new TaxChallan();
								  bean.setHidListEmpId(String.valueOf(empTaxIncome[i][1]));
								  bean.setHidListTdsIncome(String.valueOf(empTaxIncome[i][0]));
								  valuesList.add(bean);
						}
						taxChallan.setValuesList(valuesList);
					  }
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			
			taxChallan.setEnterTotalTax("0");
			taxChallan.setAddedTds("0");
			taxChallan.setAddedSurcharge("0");
			taxChallan.setAddedEduCess("0");
			taxChallan.setAddEmpId("");
			taxChallan.setAddEmpToken("");
			taxChallan.setAddEmpName("");
			taxChallan.setF9AddEmpFlag("false");
		
	} //end of searchEmployee method

	public String deleteChallan(TaxChallan taxChallan, HttpServletRequest request) {
		
		String challanId = request.getParameter("challanId");
		
		String deleteChallanHdr = "DELETE FROM HRMS_TAX_CHALLAN WHERE CHALLAN_CODE = "+challanId+"";
		getSqlModel().singleExecute(deleteChallanHdr);
		String deleteChallanDtl = "DELETE FROM HRMS_TAX_CHALLAN_DTL WHERE CHALLAN_CODE ="+challanId+"";
		getSqlModel().singleExecute(deleteChallanDtl);
		return "1";
	} //end of deleteChallan method
	
	public String getMonth(int month) {
		String str = "";
		switch (month) {
		case 1:
			str = "January";
			break;
		case 2:
			str = "February";
			break;
		case 3:
			str = "March";
			break;
		case 4:
			str = "April";
			break;
		case 5:
			str = "May";
			break;
		case 6:
			str = "June";
			break;
		case 7:
			str = "July";
			break;
		case 8:
			str = "August";
			break;
		case 9:
			str = "September";
			break;
		case 10:
			str = "October";
			break;
		case 11:
			str = "November";
			break;
		case 12:
			str = "December";
		}
		return str;
	}
	
} //end of class