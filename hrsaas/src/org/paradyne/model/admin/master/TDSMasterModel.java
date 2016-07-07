package org.paradyne.model.admin.master;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.admin.master.TDSMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Rectangle;
import com.lowagie.text.Font;
/*
 * author:Pradeep Kumar Sahoo
 * Date:20-05-2008
 */
public class TDSMasterModel extends ModelBase {
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(TDSMasterModel.class);
	
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	/**
	 * following function is called to add a new record in the action class.
	 * @param bean
	 * @return
	 */
	public int addTds(TDSMaster bean){
		boolean result=false;
		
		String checkQuery="SELECT TDS_CODE ,TDS_FINANCIALYEAR_FROM, TDS_FINANCIALYEAR_TO FROM HRMS_TAX_PARAMETER" +
				" WHERE TDS_FINANCIALYEAR_FROM=? AND  TDS_FINANCIALYEAR_TO =?";
		Object[] paraObj = null;
				
		try{
		
			paraObj = new Object[2];
			paraObj[0] = bean.getFromYear();
			paraObj[1] = bean.getToYear();
			
			Object results[][]=getSqlModel().getSingleResult(checkQuery, paraObj);
			if(results!=null && results.length>0)
				return 2;
			
			Object[][] add=new Object[1][61];
			add[0][0]=bean.getTdsDate();
			add[0][1]=bean.getTdsDebitCode();
			add[0][2]=bean.getEduCess();
			add[0][3]=bean.getSurcharge();
			add[0][4]=bean.getCitizenAge();
			add[0][5]=bean.getCitizenSurLimit();
			add[0][6]=bean.getSalAmt();
			add[0][7]=bean.getSalAmt2();
			add[0][8]=bean.getSalAmt3();
			add[0][9]=bean.getSalAmt4();
			add[0][10]=bean.getSalAmt5();
			add[0][11]=bean.getSalAmt6();
			add[0][12]=bean.getHraCode();
			add[0][13]=bean.getBasicCode();
			add[0][14]=bean.getDaCode();
			add[0][15]=bean.getHraExCode();
			add[0][16]=bean.getRebateLimit();
			add[0][17]=bean.getProvFundCode();
			//add[0][18]=bean.getSignAuthId();
			
			 if(bean.getSignAuthId()!="")
				 add[0][18]=bean.getSignAuthId();
			       else
			    	   add[0][18]="";
			
			add[0][19]=bean.getHraPaidMetro();
			add[0][20]=bean.getInvVerificationDate();
			
			//add[0][21]=bean.getReimbursebillDate();
			add[0][21]=bean.getFromYear();
			add[0][22]=bean.getToYear();			
			add[0][23]=bean.getLeaveEncashamt();
			add[0][24]=bean.getLeaveEncashFormula();
			add[0][25]=bean.getLeaveEncashMonths();
			//add[0][27]=bean.getAvgleaveEncashNo();
			add[0][26]=bean.getLeaveEncInvcode();			
			add[0][27]=bean.getGratuityAmount().trim(); 
			add[0][28]=bean.getTaxCode().trim(); 
			add[0][29]=bean.getCreditCode().trim();
			add[0][30]=bean.getLtaAmount().trim(); 
			add[0][31]=bean.getLtaTaxCode().trim(); 
			add[0][32]=bean.getLtaCreditCode().trim(); 
			add[0][33]=bean.getTraAllowanceLimit().trim();
			add[0][34]=bean.getTraAllowTaxCode().trim(); 
			add[0][35]=bean.getVehicleCapLessthan1600().trim(); 
			add[0][36]=bean.getVehicleCapGreaterthan1600().trim(); 
			add[0][37]=bean.getDriverUsedAddExemption().trim(); 
			add[0][38]=bean.getVehicalAllowTaxCode().trim(); 
			add[0][39]=bean.getDonationsTaxCode().trim(); 
			add[0][40]=bean.getInvDeclarationCuttOffDate(); 
			add[0][41]=bean.getMonthInvestmentDecPeriodFromDate(); 
			add[0][42]=bean.getMonthInvestmentDecPeriodToDate(); 
			add[0][43]=bean.getHraExUnpaidCode();
			add[0][44]=bean.getMapPerquisiteHeadCode();
			add[0][45]=bean.getAccomPerqCentOwnedHigher();
			add[0][46]=bean.getAccomPerqCentOwnedLess();
			add[0][47]=bean.getAccomPerqCentRentedHigher();
			add[0][48]=bean.getAccomPerqCentRentedLess();
			add[0][49]=bean.getTransAllowanceLimitPermDisabi();
			add[0][50]=bean.getTraAllowCreditHeadCode();
			add[0][51]=bean.getPermanentDisabilityDedCode();
			add[0][52]=bean.getGovLoanRate();
			add[0][53]=bean.getPerqHeadCompanyLoanCode();
			add[0][54]=bean.getMinLoanAmt();
			add[0][56]=bean.getDonationFormPercent();
			add[0][57]=bean.getDonationApplInvCode();
			
			if(bean.getLockFlag().equals("true")) {
				add[0][55]="Y";
			} else {
				add[0][55]="N";
			}
			/**For Tax Rebate Fields- TaxRebateEnable, TaxRebateAmount And TaxRebateIncomeLImit*/
			if(bean.getTaxRebateEnable().equals("true")){
				add[0][58]="Y";
				add[0][59]= bean.getTaxRebateAmount();
				add[0][60]= bean.getTaxIncomeLimit();
			}else{
				add[0][58]="N";
				add[0][59]= "";
				add[0][60]= "";
			}
			
			result= getSqlModel().singleExecute(getQuery(1),add);
			
			if(result) {
					/**
					 * Following code calculates the tax
					 * and updates tax process
					 */
					try {
						CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
						taxmodel.initiate(context, session);
						logger.info("I m calling tax calculation method");
						Object empList[][] = getSqlModel().getSingleResult("SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S'");
						/*Calendar cal = Calendar.getInstance();
						cal.setTime(new Date());
						int fromYear = Integer.parseInt(String.valueOf(cal.get(Calendar.YEAR)));
						int month = Integer.parseInt(String.valueOf(cal.get(Calendar.MONTH)));
						if(month <= 3)
							fromYear--;*/
						if(empList != null && empList.length > 0)
							//taxmodel.calculateTax(empList,bean.getFromYear(),bean.getToYear());
						taxmodel.terminate();
					} catch (Exception e) {
						logger.error("Exception in addTds() of TDSMaster  while calling calculateTax : "+e);
						e.printStackTrace();
					}
					return 1;
				}
				else
					return 2;
		}catch(Exception e){
			e.printStackTrace();
			return 3;
		}
		//return result;
			
	}//TDS_HRAEXEMPT_COND1MET
	
	/**
	 * following function is called in the action class to delete the selected records from the list.
	 * @param bean
	 * @param code
	 * @return
	 */
	public boolean deleteCheckedRecords(TDSMaster bean,String[] code){
		int count=0;
		boolean result=false;
		for(int i=0;i<code.length;i++){
			if(!code[i].equals("")){
				
				Object [][] delete = new Object [1][1];
				delete [0][0] =code[i] ;
				result=getSqlModel().singleExecute(getQuery(3), delete);
				if(!result){
					count++;
				}
				}
	  	}
		
		
		if(count!=0){
			result=false;
			return result;
		}else {
			result=true;
			return result;
		}
		
		
		
	}
	/**
	 * following function is called in the action class to select all the records from HRMS_TAX_PARAMETER table.
	 * @param bean
	 * @param request
	 */
	public void getTdsListDtls(TDSMaster bean,HttpServletRequest request){
		try{
		
		
		String query="SELECT TDS_CODE,TDS_SURCHARGE,nvl(TDS_SURCHARGE_LIMIT_AMT,'') ,TDS_EDU_CESS,"
			+" NVL(TDS_HRAEXEMPT_PAIDCOND1,' '),NVL(TDS_HRAEXEMPT_PAIDCOND2,' '),NVL(TDS_HRAEXEMPT_PAIDCOND3,' '),NVL(TDS_HRAEXEMPT_UNPAIDCOND1,' '),"
			+" NVL(TDS_HRAEXEMPT_UNPAIDCOND2,' '),NVL(TDS_HRAEXEMPT_UNPAIDCOND3,' '),TDS_FINANCIALYEAR_FROM, TDS_FINANCIALYEAR_TO,NVL(TDS_HRAEXEMPT_COND1MET,' ') "
			+" FROM  HRMS_TAX_PARAMETER ORDER BY TDS_CODE";
	Object repData[][] = getSqlModel().getSingleResult(query);
	
	
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
	 int pg1=0;
	int PageNo1=1;//----------
	REC_TOTAL = repData.length;
	int no_of_pages=Math.round(REC_TOTAL/20);
	//PageNo = Integer.parseInt(bean.getMyPage());//-----------
	double row = (double)repData.length/20.0;
   
      java.math.BigDecimal value1 = new java.math.BigDecimal(row);
     
      int rowCount1 =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
    
	
	
	request.setAttribute("abc", rowCount1);
	
	//PageNo
	if(String.valueOf(bean.getMyPage()).equals("null")||String.valueOf(bean.getMyPage()).equals(null)||String.valueOf(bean.getMyPage()).equals(""))
	{
		PageNo1=1;
		From_TOT=0;
		  To_TOT=20;

		  if(To_TOT >repData.length){
			  To_TOT=repData.length;
		  }
		
			bean.setMyPage("1");
	}
	
	
	else{
			
		  pg1=	Integer.parseInt(bean.getMyPage());
		  PageNo1=pg1;
		  
		  if(pg1 ==1){
			 From_TOT=0;
			 To_TOT=20;
		  }
		  else{
			//  From_TOTAL=To_TOTAL+1;
				 To_TOT=To_TOT*pg1;
				 From_TOT=(To_TOT-20);
		  }
		  if(To_TOT >repData.length){
			  To_TOT=repData.length;
		  }
	  }
	request.setAttribute("xyz", PageNo1);
	  ArrayList<Object> list=new ArrayList<Object>();
	  for(int i=From_TOT;i<To_TOT;i++){
              
		  
		  	TDSMaster tds=new TDSMaster();
			tds.setTdsCode(String.valueOf(repData[i][0]));
			tds.setSurcharge(String.valueOf(repData[i][1]));
			tds.setCitizenSurLimit(String.valueOf(repData[i][2]));
			tds.setEduCess(String.valueOf(repData[i][3]));
			tds.setSalAmt(String.valueOf(repData[i][4]));
			tds.setSalAmt2(String.valueOf(repData[i][5]));
			tds.setSalAmt3(String.valueOf(repData[i][6]));
			tds.setSalAmt4(String.valueOf(repData[i][7]));
			tds.setSalAmt5(String.valueOf(repData[i][8]));
			tds.setSalAmt6(String.valueOf(repData[i][9]));
			tds.setFromYear(checkNull(String.valueOf(repData[i][10])));
			tds.setToYear(checkNull(String.valueOf(repData[i][11])));
			tds.setHraPaidMetro(checkNull(String.valueOf(repData[i][12])));
			list.add(tds);
			
	  }
	
		bean.setTdsList(list);
		
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	
}	
/**
 * following function is called to modify the selected record.
 * @param bean
 * @return
 */	
	public int modTds(TDSMaster bean){
		
		String checkQuery="SELECT HRMS_TAX_PARAMETER.TDS_CODE ,HRMS_TAX_PARAMETER.TDS_FINANCIALYEAR_FROM, HRMS_TAX_PARAMETER.TDS_FINANCIALYEAR_TO " 
			+ " FROM HRMS_TAX_PARAMETER " 
			+ " WHERE TDS_FINANCIALYEAR_FROM=? AND  TDS_FINANCIALYEAR_TO =?  AND TDS_CODE !=?";

		Object[] paraObj = null;
	
		boolean result=false;
		try{
			paraObj = new Object[3];
			paraObj[0] = bean.getFromYear();
			paraObj[1] = bean.getToYear();
			paraObj[2] = bean.getTdsCode();
			
			Object results[][]=getSqlModel().getSingleResult(checkQuery,paraObj);
			if(results!=null && results.length>0)
				return 2;
			
			 Object[][] mod=new Object[1][63];
		       mod[0][0]=bean.getTdsDate();
		       mod[0][1]=bean.getTdsDebitCode();
		       mod[0][2]=bean.getEduCess();
		       mod[0][3]=bean.getSurcharge();
		       mod[0][4]=bean.getCitizenAge();
		       mod[0][5]=bean.getCitizenSurLimit();
		       mod[0][6]=bean.getSalAmt();
		       mod[0][7]=bean.getSalAmt2();
		       mod[0][8]=bean.getSalAmt3();
		       mod[0][9]=bean.getSalAmt4();
		       mod[0][10]=bean.getSalAmt5();
		       mod[0][11]=bean.getSalAmt6();
		       mod[0][12]=bean.getHraCode();
		       mod[0][13]=bean.getBasicCode();
		      if(bean.getDaCode().equals("null") || bean.getDaCode().equals("") || bean.getDaCode().equals(" ") || bean.getDaCode().equals(null)){
		    	   mod[0][14]="";
		       }else{
		       mod[0][14]=bean.getDaCode();
		       }
		       mod[0][15]=bean.getHraExCode();
		       mod[0][16]=bean.getRebateLimit();
		       mod[0][17]=bean.getProvFundCode();
		       if(bean.getSignAuthId()!="")
		       mod[0][18]=bean.getSignAuthId();
		       else
		    	   mod[0][18]="";
		       mod[0][19]=bean.getHraPaidMetro();
		       logger.info(">>>>"+bean.getHraPaidMetro());
		       
		       
		       mod[0][20]=bean.getInvVerificationDate();
		       mod[0][21]=bean.getReimbursebillDate();
		       
		       
		       mod[0][22]=bean.getFromYear();
		       mod[0][23]=bean.getToYear();
		       
		       mod[0][24]=bean.getLeaveEncashamt();
		       mod[0][25]=bean.getLeaveEncashFormula();
		       mod[0][26]=bean.getLeaveEncashMonths();
		       
		       //mod[0][27]=bean.getAvgleaveEncashNo();
		       mod[0][27]=bean.getLeaveEncInvcode();
		    
		       mod[0][28]=bean.getGratuityAmount().trim(); 
		       mod[0][29]=bean.getTaxCode().trim(); 
		       mod[0][30]=bean.getCreditCode().trim(); 
		       

		       mod[0][31]=bean.getLtaAmount().trim(); 
		       mod[0][32]=bean.getLtaTaxCode().trim(); 
		       mod[0][33]=bean.getLtaCreditCode().trim(); 
		       
		       mod[0][34]=bean.getTraAllowanceLimit().trim();
		       mod[0][35]=bean.getTraAllowTaxCode().trim(); 
		       mod[0][36]=bean.getVehicleCapLessthan1600().trim(); 
		       mod[0][37]=bean.getVehicleCapGreaterthan1600().trim(); 
		       mod[0][38]=bean.getDriverUsedAddExemption().trim(); 
		       mod[0][39]=bean.getVehicalAllowTaxCode().trim(); 
		       mod[0][40]=bean.getDonationsTaxCode().trim(); 
		       
		       mod[0][41]=bean.getInvDeclarationCuttOffDate(); 
		       mod[0][42]=bean.getMonthInvestmentDecPeriodFromDate(); 
		       mod[0][43]=bean.getMonthInvestmentDecPeriodToDate(); 
		       
		       mod[0][44]=bean.getHraExUnpaidCode();
		       mod[0][45]=bean.getMapPerquisiteHeadCode();
		       mod[0][46]=bean.getAccomPerqCentOwnedHigher();
		       mod[0][47]=bean.getAccomPerqCentOwnedLess();
		       mod[0][48]=bean.getAccomPerqCentRentedHigher();
		       mod[0][49]=bean.getAccomPerqCentRentedLess();
		       mod[0][50]=bean.getTransAllowanceLimitPermDisabi();
		       mod[0][51]=bean.getTraAllowCreditHeadCode();
		       mod[0][52]=bean.getPermanentDisabilityDedCode();
		       mod[0][53]=bean.getGovLoanRate();
		       mod[0][54]=bean.getPerqHeadCompanyLoanCode();
		       mod[0][55]=bean.getMinLoanAmt();
		       if (bean.getLockFlag().equals("true")) {
		    	   mod[0][56] = "Y";
		       } else {
		    	   mod[0][56] = "N";
		       }
		       mod[0][57]=bean.getDonationFormPercent();
		       mod[0][58]=bean.getDonationApplInvCode();
		       /**For Tax Rebate Fields- TaxRebateEnable, TaxRebateAmount And TaxRebateIncomeLImit*/
		       if(bean.getTaxRebateEnable().equals("true")){
		    	   mod[0][59]="Y";
		    	   mod[0][60]=checkNull(bean.getTaxRebateAmount());
			       mod[0][61]=checkNull(bean.getTaxIncomeLimit());
		       }else{
		    	   mod[0][59]="N";
		    	   mod[0][60]= "";
			       mod[0][61]="";
		       }		       
		       mod[0][62]=bean.getTdsCode();		       
		       logger.info(">>>>tds code"+bean.getTdsCode());				
		       result= getSqlModel().singleExecute(getQuery(2), mod);		     
		       if(result){
		    	   /**
					 * Following code calculates the tax
					 * and updates tax process
					 */
					try {
						CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
						taxmodel.initiate(context, session);
						logger.info("I m calling tax calculation method");
						Object empList[][] = getSqlModel().getSingleResult("SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S'");
						/*Calendar cal = Calendar.getInstance();
						cal.setTime(new Date());
						int fromYear = Integer.parseInt(String.valueOf(cal.get(Calendar.YEAR)));
						int month = Integer.parseInt(String.valueOf(cal.get(Calendar.MONTH)));
						if(month <= 3)
							fromYear--;*/
						if(empList != null && empList.length > 0)
							//taxmodel.calculateTax(empList,bean.getFromYear(),bean.getToYear());
						taxmodel.terminate();
					} catch (Exception e) {
						logger.error("Exception in updateTds() of TDSMaster  while calling calculateTax : "+e);
						e.printStackTrace();
					}
					return 1;
		       }
				else
					return 2;		       
		}catch(Exception e){
			e.printStackTrace();
			return 3;
		}
	// return result;
	}
	/**
	 * following function is called to delete a record.
	 * @param bean
	 * @return
	 */
	public boolean delTds(TDSMaster bean){
		Object[][] del=new Object[1][1];
			  del[0][0]=bean.getTdsCode();
		return getSqlModel().singleExecute(getQuery(3),del);
	}
	
	/**
	 * following function is called in the action class to display tds details when a record is selected from the pop up window 
	 * @param bean
	 */
	public void getTdsDet(TDSMaster bean){
		try{
		Object[] obj=new Object[1];
		obj[0]=bean.getTdsCode();
		System.out.println("obj[0]=" + obj[0]);
		Object[][] param = getSqlModel().getSingleResult(getQuery(4),obj);
		bean.setCitizenAge(String.valueOf(param[0][0]));
		bean.setCitizenSurLimit(String.valueOf(param[0][1]));
		bean.setSalAmt(String.valueOf(param[0][2]));
		bean.setSalAmt2(String.valueOf(param[0][3]));
		bean.setSalAmt3(String.valueOf(param[0][4]));
		bean.setSalAmt4(String.valueOf(param[0][5]));
		bean.setSalAmt5(String.valueOf(param[0][6]));
		bean.setSalAmt6(String.valueOf(param[0][7]));
		bean.setHraCode(String.valueOf(param[0][8]));
		bean.setHraName(String.valueOf(param[0][9]));
		bean.setBasicCode(String.valueOf(param[0][10]));
		bean.setBasicName(String.valueOf(param[0][11]));
		bean.setDaCode(String.valueOf(param[0][12]));
		bean.setDaName(String.valueOf(param[0][13]));
		bean.setHraExCode(String.valueOf(param[0][14]));
		bean.setHraExName(String.valueOf(param[0][15]));
		if(String.valueOf(param[0][16]).equals("null") || String.valueOf(param[0][16]).equals("")){
			bean.setRebateLimit("");
		}else {
		bean.setRebateLimit(String.valueOf(param[0][16]));
		}
		bean.setProvFundCode(String.valueOf(param[0][17]));
		bean.setProvFundName(String.valueOf(param[0][18]));
		bean.setSignAuthId(checkNull(String.valueOf(param[0][19])));
		bean.setSignAuthName(checkNull(String.valueOf(param[0][20])));
		bean.setToken(String.valueOf(param[0][21]));
		bean.setHraPaidMetro(String.valueOf(param[0][22]));
		bean.setInvVerificationDate(checkNull(String.valueOf(param[0][23])));
		bean.setReimbursebillDate(checkNull(String.valueOf(param[0][24])));
		
		bean.setFromYear(checkNull(String.valueOf(param[0][25])));
		bean.setToYear(checkNull(String.valueOf(param[0][26])));
		
		bean.setLeaveEncashamt(checkNull(String.valueOf(param[0][27])));
		bean.setLeaveEncashFormula(checkNull(String.valueOf(param[0][28])));
		bean.setLeaveEncashMonths(checkNull(String.valueOf(param[0][29])));
		
		
		//bean.setAvgleaveEncashNo(checkNull(String.valueOf(param[0][30])));
		bean.setLeaveEncInvcode(checkNull(String.valueOf(param[0][30])));
		bean.setLeaveEncInvName(checkNull(String.valueOf(param[0][31])));
		
		bean.setGratuityAmount(checkNull(String.valueOf(param[0][32])));
		bean.setTaxCode(checkNull(String.valueOf(param[0][33])));
		bean.setCreditCode(checkNull(String.valueOf(param[0][34])));
		
		bean.setCreditType(checkNull(String.valueOf(param[0][35])));
		bean.setExemptSectionNo(checkNull(String.valueOf(param[0][36])));
		
		
		bean.setLtaAmount(checkNull(String.valueOf(param[0][37])));
		bean.setLtaTaxCode(checkNull(String.valueOf(param[0][38])));
		bean.setLtaCreditCode(checkNull(String.valueOf(param[0][39])));
		
		bean.setLtaExemptSectionNo(checkNull(String.valueOf(param[0][40])));
		bean.setLtaCreditType(checkNull(String.valueOf(param[0][41])));
		
		
//Added By Ganesh
		
		bean.setTraAllowanceLimit(checkNull(String.valueOf(param[0][42])));
		bean.setTraAllowTaxCode(checkNull(String.valueOf(param[0][43])));
		bean.setTraAllowExemptSectionNo(checkNull(String.valueOf(param[0][44])));
		bean.setVehicleCapLessthan1600(checkNull(String.valueOf(param[0][45])));
		bean.setVehicleCapGreaterthan1600(checkNull(String.valueOf(param[0][46])));
		bean.setDriverUsedAddExemption(checkNull(String.valueOf(param[0][47])));
		bean.setVehicalAllowTaxCode(checkNull(String.valueOf(param[0][48])));
		bean.setVehicalAllowExemptSectionNo(checkNull(String.valueOf(param[0][49])));
		bean.setDonationsTaxCode(checkNull(String.valueOf(param[0][50])));
		bean.setDonationsExemptSectionNo(checkNull(String.valueOf(param[0][51])));
		
		bean.setInvDeclarationCuttOffDate(checkNull(String.valueOf(param[0][52])));
		bean.setMonthInvestmentDecPeriodFromDate(checkNull(String.valueOf(param[0][53])));
		bean.setMonthInvestmentDecPeriodToDate(checkNull(String.valueOf(param[0][54])));
		bean.setMinLoanAmt(checkNull(String.valueOf(param[0][55])));
		 
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
public String checkNull(String result) {
		
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	/**
	 * following function is called to display the tds details when a record is double clicked from the list. 
	 * @param bean
	 */
	public void getTdsOnDblClk(TDSMaster bean){
		try{
		Object[] obj=new Object[1];
		obj[0]=bean.getHiddenCode();
		System.out.println("obj[0]===" +obj[0]);
		Object[][] param = getSqlModel().getSingleResult(getQuery(5),obj);
		bean.setTdsCode(String.valueOf(param[0][24]));
		bean.setTdsDate(String.valueOf(param[0][19]));
		bean.setTdsDebitCode(String.valueOf(param[0][20]));
		bean.setTdsDebit(String.valueOf(param[0][21]));
		bean.setSurcharge(String.valueOf(param[0][23]));
		bean.setEduCess(String.valueOf(param[0][22]));
		bean.setCitizenAge(String.valueOf(param[0][0]));
		bean.setCitizenSurLimit(String.valueOf(param[0][1]));
		bean.setSalAmt(String.valueOf(param[0][2]));
		bean.setSalAmt2(String.valueOf(param[0][3]));
		bean.setSalAmt3(String.valueOf(param[0][4]));
		bean.setSalAmt4(String.valueOf(param[0][5]));
		bean.setSalAmt5(String.valueOf(param[0][6]));
		bean.setSalAmt6(String.valueOf(param[0][7]));
		bean.setHraCode(String.valueOf(param[0][8]));
		bean.setHraName(String.valueOf(param[0][9]));
		bean.setBasicCode(String.valueOf(param[0][10]));
		bean.setBasicName(String.valueOf(param[0][11]));
		bean.setDaCode(String.valueOf(param[0][12]));
		bean.setDaName(String.valueOf(param[0][13]));
		bean.setHraExCode(String.valueOf(param[0][14]));
		bean.setHraExName(String.valueOf(param[0][15]));
		if(String.valueOf(param[0][16]).equals("null") || String.valueOf(param[0][16]).equals("")){
			bean.setRebateLimit("");
		}else {
		bean.setRebateLimit(String.valueOf(param[0][16]));
		}
		bean.setProvFundCode(String.valueOf(param[0][17]));
		bean.setProvFundName(String.valueOf(param[0][18]));
		bean.setSignAuthId(checkNull(String.valueOf(param[0][25])));
		bean.setSignAuthName(checkNull(String.valueOf(param[0][26])));
		bean.setToken(String.valueOf(param[0][27]));
		bean.setHraPaidMetro(String.valueOf(param[0][28]));
		
		
		bean.setInvVerificationDate(checkNull(String.valueOf(param[0][29])));
		bean.setReimbursebillDate(checkNull(String.valueOf(param[0][30])));
		
		bean.setFromYear(checkNull(String.valueOf(param[0][31])));
		bean.setToYear(checkNull(String.valueOf(param[0][32])));
	
		
		bean.setLeaveEncashamt(checkNull(String.valueOf(param[0][33])));
		bean.setLeaveEncashFormula(checkNull(String.valueOf(param[0][34])));
		bean.setLeaveEncashMonths(checkNull(String.valueOf(param[0][35])));
		
		//bean.setAvgleaveEncashNo(checkNull(String.valueOf(param[0][36])));
		bean.setLeaveEncInvcode(checkNull(String.valueOf(param[0][36])));
		bean.setLeaveEncInvName(checkNull(String.valueOf(param[0][37])));
		
		
		bean.setGratuityAmount(checkNull(String.valueOf(param[0][38])));
		bean.setTaxCode(checkNull(String.valueOf(param[0][39])));
		bean.setCreditCode(checkNull(String.valueOf(param[0][40])));
		
		bean.setCreditType(checkNull(String.valueOf(param[0][41])));
		bean.setExemptSectionNo(checkNull(String.valueOf(param[0][42])));
		

		bean.setLtaAmount(checkNull(String.valueOf(param[0][43])));
		bean.setLtaTaxCode(checkNull(String.valueOf(param[0][44])));
		bean.setLtaCreditCode(checkNull(String.valueOf(param[0][45])));
		
		bean.setLtaExemptSectionNo(checkNull(String.valueOf(param[0][46])));
		bean.setLtaCreditType(checkNull(String.valueOf(param[0][47])));
		
		//Added By Ganesh
		
		bean.setTraAllowanceLimit(checkNull(String.valueOf(param[0][48])));
		bean.setTraAllowTaxCode(checkNull(String.valueOf(param[0][49])));
		bean.setTraAllowExemptSectionNo(checkNull(String.valueOf(param[0][50])));
		bean.setVehicleCapLessthan1600(checkNull(String.valueOf(param[0][51])));
		bean.setVehicleCapGreaterthan1600(checkNull(String.valueOf(param[0][52])));
		bean.setDriverUsedAddExemption(checkNull(String.valueOf(param[0][53])));
		bean.setVehicalAllowTaxCode(checkNull(String.valueOf(param[0][54])));
		bean.setVehicalAllowExemptSectionNo(checkNull(String.valueOf(param[0][55])));
		bean.setDonationsTaxCode(checkNull(String.valueOf(param[0][56])));
		bean.setDonationsExemptSectionNo(checkNull(String.valueOf(param[0][57])));
		bean.setInvDeclarationCuttOffDate(checkNull(String.valueOf(param[0][58])));
		bean.setMonthInvestmentDecPeriodFromDate(checkNull(String.valueOf(param[0][59])));
		bean.setMonthInvestmentDecPeriodToDate(checkNull(String.valueOf(param[0][60])));
		
		//Added By Vijay
		bean.setHraExUnpaidCode(checkNull(String.valueOf(param[0][61])));
		bean.setHraExUnpaidName(checkNull(String.valueOf(param[0][62])));
		bean.setMapPerquisiteHeadCode(checkNull(String.valueOf(param[0][63])));
		bean.setMapPerquisiteHeadName(checkNull(String.valueOf(param[0][64])));
		bean.setAccomPerqCentOwnedHigher(checkNull(String.valueOf(param[0][65])));
		bean.setAccomPerqCentOwnedLess(checkNull(String.valueOf(param[0][66])));
		bean.setAccomPerqCentRentedHigher(checkNull(String.valueOf(param[0][67])));
		bean.setAccomPerqCentRentedLess(checkNull(String.valueOf(param[0][68])));
		bean.setTransAllowanceLimitPermDisabi(checkNull(String.valueOf(param[0][69])));
		bean.setTraAllowCreditHeadCode(checkNull(String.valueOf(param[0][70])));
		bean.setTraAllowCreditHeadName(checkNull(String.valueOf(param[0][71])));
		bean.setPermanentDisabilityDedCode(checkNull(String.valueOf(param[0][72])));
		bean.setPermanentDisabilityDedName(checkNull(String.valueOf(param[0][73])));
		bean.setGovLoanRate(checkNull(String.valueOf(param[0][74])));
		bean.setPerqHeadCompanyLoanCode(checkNull(String.valueOf(param[0][75])));
		bean.setPerqHeadCompanyLoan(checkNull(String.valueOf(param[0][76])));
		bean.setMinLoanAmt(checkNull(String.valueOf(param[0][77])));
		bean.setDonationFormPercent(checkNull(String.valueOf(param[0][79])));
		if(checkNull(String.valueOf(param[0][78])).equals("Y")) {
			bean.setLockFlag("true");
		} else {
			bean.setLockFlag("false");
		}
		/**For Tax Rebate Fields- TaxRebateEnable, TaxRebateAmount And TaxRebateIncomeLImit*/
		if(checkNull(String.valueOf(param[0][80])).equals("Y")) {
			bean.setTaxRebateEnable("true");
		} else {
			bean.setTaxRebateEnable("false");
		}		
		bean.setTaxRebateAmount(checkNull(String.valueOf(param[0][81])));
		bean.setTaxIncomeLimit(checkNull(String.valueOf(param[0][82])));
		Object[] obj1=new Object[2];
		obj1[0]=bean.getHiddenCode();
		obj1[1]=bean.getHiddenCode();
		Object[][] multiInvObj = getSqlModel().getSingleResult(getQuery(6),obj1);
		String donationApplInv=checkNull(String.valueOf(multiInvObj[0][0]));
		if(!donationApplInv.equals("")){
			bean.setDonationApplInv(checkNull(donationApplInv.substring(0,donationApplInv.length()-1)));
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	
	}

	public void Data(TDSMaster bean, HttpServletRequest request) {
		try {
			//System.out.println("fgdfgfgdg");
			Object[][] draftListData = null;
			ArrayList tdsList = new ArrayList();
			
			
			// For drafted application Begins
			String query = " SELECT NVL(TDS_FINANCIALYEAR_FROM,''),NVL(TDS_FINANCIALYEAR_TO,''),TO_CHAR(TDS_EFF_DATE,'DD-MM-YYYY'),NVL(TDS_SURCHARGE,''),NVL(TDS_EDU_CESS,''),"
				+ " NVL(DEBIT_NAME,' '),TDS_CODE,TDS_DEBIT_CODE FROM  HRMS_TAX_PARAMETER INNER JOIN HRMS_DEBIT_HEAD ON "
				+ " (HRMS_TAX_PARAMETER.TDS_DEBIT_CODE=HRMS_DEBIT_HEAD.DEBIT_CODE) ORDER BY TDS_FINANCIALYEAR_FROM DESC";
			
			
			draftListData = getSqlModel().getSingleResult(query);
			
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					draftListData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalDraftPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("draftPageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");
			
			if (draftListData != null && draftListData.length > 0) {
				bean.setDraftVoucherListLength(true);
				System.out.println("draftListData.length : "+draftListData.length);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
				.parseInt(pageIndex[1]); i++) {
					System.out.println("Fo loop : "+i);
					TDSMaster beanItt = new TDSMaster();
					
					beanItt.setFromYear(checkNull(String
							.valueOf(draftListData[i][0])));
					beanItt.setToYear(checkNull(String
							.valueOf(draftListData[i][1])));
					beanItt.setTdsDate(checkNull(String
							.valueOf(draftListData[i][2])));
					beanItt.setSurcharge(checkNull(String
							.valueOf(draftListData[i][3])));
					beanItt.setEduCess(checkNull(String
							.valueOf(draftListData[i][4])));
					beanItt.setTdsDebit(checkNull(String
							.valueOf(draftListData[i][5])));
					beanItt.setItttdsCode(checkNull(String
							.valueOf(draftListData[i][6])));
					beanItt.setTdsDebitCode(checkNull(String
							.valueOf(draftListData[i][7])));
					
					tdsList.add(beanItt);
				}
				bean.setTdsIteratorList(tdsList);
			}
			// For drafted application Ends
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}

	public void generateReport(TDSMaster bean, HttpServletResponse response, HttpServletRequest request,
			String[] label, String reportPath) {
		try {

			final String frmYear = bean.getFromYear(); //from year
			final String toYear = bean.getToYear(); //to year
			
			
			String reportType = bean.getReportType();

			logger.info("reportType--------------->" + reportType + "<-------");

			String title = " TDS Parameter Report For  " + frmYear
			+ " - " + toYear;
			ReportDataSet rds = new ReportDataSet();
			String fileName = "TDS_Parameter";
			rds.setFileName(fileName);
			rds.setReportName(title);
			rds.setReportType(reportType);
			rds.setPageSize("A4");
			rds.setUserEmpId(bean.getUserEmpId());
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;
			if(reportPath.equals("")){
				rg = new ReportGenerator(rds,session,context, request);
				new ReportGenerator(rds,session,context);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+reportPath+fileName+"."+reportType);
				rg = new ReportGenerator(rds,reportPath,session,context, request);
				request.setAttribute("reportPath", reportPath+fileName+"."+reportType);
				request.setAttribute("fileName", fileName + "."	+ reportType);
				request.setAttribute("action", "/payroll/TDSMaster_input.action?path="+reportPath);
			}
			
			/*
			// For Report heading
			TableDataSet repTitle = new TableDataSet();
			Object[][] repTitleObj = new Object[1][1];
			repTitleObj[0][0] = title;
			repTitle.setData(repTitleObj);
			repTitle.setCellAlignment(new int[] { 1 });
			repTitle.setCellWidth(new int[] { 100 });
			repTitle.setCellColSpan(new int[] {4});
			repTitle.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.BOLD,
					new BaseColor(0, 0, 0));
			repTitle.setBorder(false);
			repTitle.setBlankRowsBelow(1);
			rg.addTableToDoc(repTitle);

			
			java.util.Date d = new java.util.Date();
			logger.info("Date : - " + d);
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"dd-MM-yyyy");
			String strDate = sdf.format(d);

			Object[][] dateData = new Object[1][1];
			dateData[0][0] = "Date:  " + strDate;
			int[] cellWidthDateHeader = { 100 };
			int[] cellAlignDateHeader = { 2 };
			TableDataSet tableheadingDateData = new TableDataSet();
			tableheadingDateData.setData(dateData);
			tableheadingDateData.setCellWidth(cellWidthDateHeader);
			tableheadingDateData.setCellAlignment(cellAlignDateHeader);
			tableheadingDateData.setBodyFontDetails(FontFamily.HELVETICA, 10,
					Font.NORMAL, new BaseColor(0, 0, 0));
			tableheadingDateData.setBorder(false);
			// tableheadingDateData.setHeaderTable(true);
			tableheadingDateData.setBlankRowsBelow(1);
			rg.addTableToDoc(tableheadingDateData);
			*/
			
			TableDataSet subtitleName = new TableDataSet();
			String filterObj = "";
			filterObj = "Period  : " + bean.getFromYear() + " - " + bean.getToYear();
			
			subtitleName.setData(new Object[][] { { filterObj } });
			subtitleName.setCellAlignment(new int[] { 0 });
			subtitleName.setCellWidth(new int[] { 100 });
			//subtitleName.setBodyFontStyle(1);
			subtitleName.setBorder(false);
			subtitleName.setHeaderTable(false);
			subtitleName.setBlankRowsBelow(1);
			//rg.createHeader(subtitleName);
			rg.addTableToDoc(subtitleName);
			
			TableDataSet tdsParameters = new TableDataSet();
			Object[][] tdsParametersTitleObj = new Object[1][1];
			tdsParametersTitleObj[0][0] = "TDS Parameters";
			tdsParameters.setData(tdsParametersTitleObj);
			tdsParameters.setCellAlignment(new int[] { 0 });
			tdsParameters.setCellWidth(new int[] { 100 });
			tdsParameters.setBodyFontStyle(1);
			tdsParameters.setBorder(false);
			//tdsParameters.setBlankRowsBelow(1);
			rg.addTableToDoc(tdsParameters);
			
			Vector blackLineVector=new Vector();
			blackLineVector.add(new BaseColor(0, 0, 0));
			blackLineVector.add(Rectangle.TOP);
			
			Vector redLineVector1=new Vector();
			redLineVector1.add(new BaseColor(255, 0, 0));
			redLineVector1.add(Rectangle.TOP);
			
			rg.addLine(blackLineVector);
			
			Object[][] tdsParametersDataObj=new Object[7][4];
			
			tdsParametersDataObj[0][0]="TDS Effective Date:";
			tdsParametersDataObj[0][1]= "";
			if(bean.getTdsDate()!=null && bean.getTdsDate().length() > 0)
				tdsParametersDataObj[0][1]= bean.getTdsDate();
			
			tdsParametersDataObj[0][2]="Surcharge on Tax %:";
			tdsParametersDataObj[0][3]=""; 
			if(bean.getSurcharge()!=null && bean.getSurcharge().length() > 0)
				tdsParametersDataObj[0][3]= formatter.format(Double.parseDouble(String.valueOf(bean.getSurcharge()))); 
			
			tdsParametersDataObj[1][0]="Education Cess %:";  
			tdsParametersDataObj[1][1]="";
			if(bean.getEduCess()!=null && bean.getEduCess().length() > 0)
				tdsParametersDataObj[1][1]= formatter.format(Double.parseDouble(String.valueOf(bean.getEduCess())));
			
			tdsParametersDataObj[1][2]="Surcharge Applicability:";
			tdsParametersDataObj[1][3]="";
			if(bean.getCitizenSurLimit()!=null && bean.getCitizenSurLimit().length() > 0)
				tdsParametersDataObj[1][3]= formatter.format(Double.parseDouble(String.valueOf(bean.getCitizenSurLimit())));
			
			tdsParametersDataObj[2][0]="Senior Citizen Age Limit:";
			tdsParametersDataObj[2][1]="";
			if(bean.getCitizenAge()!=null && bean.getCitizenAge().length() > 0)
				tdsParametersDataObj[2][1]=bean.getCitizenAge();
			
			tdsParametersDataObj[2][2]="TDS Debit Type:";                    
			tdsParametersDataObj[2][3]="";
			if(bean.getTdsDebit()!=null && bean.getTdsDebit().length() > 0)
				tdsParametersDataObj[2][3]= bean.getTdsDebit();
	
			tdsParametersDataObj[3][0]="Basic Credit Head:";
			tdsParametersDataObj[3][1]="";
			if(bean.getBasicName()!=null && bean.getBasicName().length() > 0)
				tdsParametersDataObj[3][1]=bean.getBasicName();
			
			tdsParametersDataObj[3][2]="DA Credit Head:";                 
			tdsParametersDataObj[3][3]="";
			if(bean.getDaName()!=null && bean.getDaName().length() > 0)
				tdsParametersDataObj[3][3]=bean.getDaName();
			
	
			tdsParametersDataObj[4][0]="Investment Verification Date:";
			tdsParametersDataObj[4][1]="";
			if(bean.getInvVerificationDate()!=null && bean.getInvVerificationDate().length() > 0)
				tdsParametersDataObj[4][1]=bean.getInvVerificationDate();
			
			tdsParametersDataObj[4][2]="Investment Declaration Cut-Off Date:";                  
			tdsParametersDataObj[4][3]="";
			if(bean.getInvDeclarationCuttOffDate()!=null && bean.getInvDeclarationCuttOffDate().length() > 0)
				tdsParametersDataObj[4][3]=bean.getInvDeclarationCuttOffDate();			
			
			tdsParametersDataObj[5][0]="Monthly Investment Declaration Period :";
			tdsParametersDataObj[5][1]="";
			if(bean.getInvVerificationDate()!=null && bean.getInvVerificationDate().length() > 0)
				tdsParametersDataObj[5][1]=bean.getMonthInvestmentDecPeriodFromDate() + " - " + bean.getMonthInvestmentDecPeriodToDate();
			
			tdsParametersDataObj[5][2]="Section 80C Investment limit:";                  
			tdsParametersDataObj[5][3]="";
			if(bean.getRebateLimit()!=null && bean.getRebateLimit().length() > 0)
				tdsParametersDataObj[5][3]= formatter.format(Double.parseDouble(String.valueOf(bean.getRebateLimit())));		
			
			tdsParametersDataObj[6][0]="Employee Provident Fund Exempted under Section:";                  
			tdsParametersDataObj[6][1]="";
			if(bean.getProvFundName()!=null && bean.getProvFundName().length() > 0)
				tdsParametersDataObj[6][1]=bean.getProvFundName();		
			
			tdsParametersDataObj[6][2]="";                  
			tdsParametersDataObj[6][3]="";
			
			int [] width={25,25,25,25};
			int [] align={0,0,0,0};
			
			TableDataSet tdsParametersData = new TableDataSet();
			tdsParametersData.setData(tdsParametersDataObj);
			tdsParametersData.setCellAlignment(align);
			tdsParametersData.setCellWidth(width);
			//tdsParametersData.setBodyFontStyle(1);
			tdsParametersData.setBorder(true);
			tdsParametersData.setBorderDetail(3);
			tdsParametersData.setBlankRowsBelow(1);
			rg.addTableToDoc(tdsParametersData);
						
			//rg.addLine(redLineVector1);
			
			TableDataSet exempParameters = new TableDataSet();
			Object[][] exempParametersTitleObj = new Object[1][1];
			exempParametersTitleObj[0][0] = "Exemptions Parameters";
			exempParameters.setData(exempParametersTitleObj);
			exempParameters.setCellAlignment(new int[] { 0 });
			exempParameters.setCellWidth(new int[] { 100 });
			exempParameters.setBodyFontStyle(1);
			exempParameters.setBorder(false);
			//exempParameters.setBlankRowsBelow(1);
			exempParameters.setBlankRowsAbove(1);
			rg.addTableToDoc(exempParameters);
			
			rg.addLine(blackLineVector);
			 
			TableDataSet hraExempTds = new TableDataSet();
			Object[][] hraExempTitleObj = new Object[1][1];
			hraExempTitleObj[0][0] = "HRA Exceptions";
			hraExempTds.setData(hraExempTitleObj);
			hraExempTds.setCellAlignment(new int[] { 0 });
			hraExempTds.setCellWidth(new int[] { 100 });
			hraExempTds.setBodyFontStyle(1);
			hraExempTds.setBorder(false);
			//hraExempTds.setBlankRowsBelow(1);
			rg.addTableToDoc(hraExempTds);
			
			//rg.addLine(blackLineVector);
			
			
			Object[][] hraCreditObj=new Object[1][2];
			hraCreditObj[0][0]="HRA Credit Head";                  
			hraCreditObj[0][1]="";
			if(bean.getHraName()!=null && bean.getHraName().length() > 0)
				hraCreditObj[0][1]=bean.getHraName();	
			int [] hraCreditWidth={25,75};
			int [] hraCreditAlign={0,0};
			
			TableDataSet hraCreditData = new TableDataSet();
			hraCreditData.setData(hraCreditObj);
			hraCreditData.setCellAlignment(hraCreditAlign);
			hraCreditData.setCellWidth(hraCreditWidth);
			hraCreditData.setBorder(true);
			hraCreditData.setBorderDetail(3);
			hraCreditData.setBlankRowsBelow(1);
			rg.addTableToDoc(hraCreditData);
			
			Object[][] hraCondObj =new Object[1][2];
			hraCondObj[0][0] = "HRA Paid Conditions";
			hraCondObj[0][1] = "HRA Unpaid Conditions";
			TableDataSet hraCondTitleData = new TableDataSet();
			hraCondTitleData.setData(hraCondObj);
			hraCondTitleData.setCellAlignment(new int[] {0,0});
			hraCondTitleData.setCellWidth(new int[] {50,50});
			hraCondTitleData.setCellColSpan(new int[] {2,2});
			hraCondTitleData.setBorder(true);
			hraCondTitleData.setBorderDetail(3);
			hraCondTitleData.setBodyFontStyle(1);
			rg.addTableToDoc(hraCondTitleData);
			
			
			String query = "SELECT HRMS_CREDIT_HEAD.CREDIT_NAME,'C'||HRMS_CREDIT_HEAD.CREDIT_CODE  FROM HRMS_CREDIT_HEAD ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
			Object[][] creditCodeObj = getSqlModel().getSingleResult(query);
			HashMap creditCodeMap = new HashMap(100);
			for (int j = 0; j < creditCodeObj.length; j++) {
				creditCodeMap.put(String.valueOf(creditCodeObj[j][1]), String.valueOf(creditCodeObj[j][0]));
			}
			
			
			Object[][] hraExempObj=new Object[5][4];
			hraExempObj[0][0]="Exemption under Section:";
			hraExempObj[0][1]= "";			
			if(bean.getHraExName()!=null && bean.getHraExName().length() > 0)
				hraExempObj[0][1]=bean.getHraExName();		
			
			hraExempObj[0][2]="Exemption under Section:";
			hraExempObj[0][3]=""; 
			if(bean.getHraExUnpaidName()!=null && bean.getHraExUnpaidName().length() > 0)
				hraExempObj[0][1]=bean.getHraExUnpaidName();		
			
			
			hraExempObj[1][0]="HRA Paid Condition 1(Non Metro):";
			hraExempObj[1][1]= "";
			if(bean.getSalAmt()!=null && bean.getSalAmt().length() > 0){
				hraExempObj[1][1] = bean.getSalAmt();
				String strMain = bean.getSalAmt();
				StringTokenizer stk = new StringTokenizer(bean.getSalAmt(),"#");
				while (stk.hasMoreElements()) {
					String key = stk.nextToken();
					if(creditCodeMap.containsKey(key)){
						strMain = strMain.replace("#"+ key +"#", creditCodeMap.get(key).toString() + " ");
					}
				}	
				hraExempObj[1][1] = strMain;
			}
			
			hraExempObj[1][2]="HRA Unpaid Condition 1:";
			hraExempObj[1][3]=""; 
			if(bean.getSalAmt4()!=null && bean.getSalAmt4().length() > 0){
				hraExempObj[1][3]=bean.getSalAmt4();
				String strMain = bean.getSalAmt4();
				StringTokenizer stk = new StringTokenizer(bean.getSalAmt4(),"#");
				while (stk.hasMoreElements()) {
					String key = stk.nextToken();
					if(creditCodeMap.containsKey(key)){
						strMain = strMain.replace("#"+ key +"#", creditCodeMap.get(key).toString() + " ");
					}
				}	
				hraExempObj[1][3] = strMain;
			}			
			
			hraExempObj[2][0]="HRA Paid Condition 2:";  
			hraExempObj[2][1]="";
			if(bean.getSalAmt2()!=null && bean.getSalAmt2().length() > 0){
				hraExempObj[2][1] = bean.getSalAmt2();
				String strMain = bean.getSalAmt2();
				StringTokenizer stk = new StringTokenizer(bean.getSalAmt2(),"#");
				while (stk.hasMoreElements()) {
					String key = stk.nextToken();
					if(creditCodeMap.containsKey(key)){
						strMain = strMain.replace("#"+ key +"#", creditCodeMap.get(key).toString() + " ");
					}
				}	
				hraExempObj[2][1] = strMain;
			}
			
			hraExempObj[2][2]="HRA Unpaid Condition 2:";
			hraExempObj[2][3]="";
			if(bean.getSalAmt5()!=null && bean.getSalAmt5().length() > 0){
				hraExempObj[2][3]=bean.getSalAmt5() + " % of Income";
				String strMain = bean.getSalAmt5()+ " % of Income";
				StringTokenizer stk = new StringTokenizer(bean.getSalAmt5(),"#");
				while (stk.hasMoreElements()) {
					String key = stk.nextToken();
					if(creditCodeMap.containsKey(key)){
						strMain = strMain.replace("#"+ key +"#", creditCodeMap.get(key).toString() + " ");
					}
				}	
				hraExempObj[2][3] = strMain;
			}
			
			
			hraExempObj[3][0]="HRA Paid Condition 3:";
			hraExempObj[3][1]="";
			if(bean.getSalAmt3()!=null && bean.getSalAmt3().length() > 0){
				hraExempObj[3][1] = "Rent " + bean.getSalAmt3();
				String strMain = "Rent " + bean.getSalAmt3();
				StringTokenizer stk = new StringTokenizer(bean.getSalAmt3(),"#");
				while (stk.hasMoreElements()) {
					String key = stk.nextToken();
					if(creditCodeMap.containsKey(key)){
						strMain = strMain.replace("#"+ key +"#", creditCodeMap.get(key).toString() + " ");
					}
				}	
				hraExempObj[3][1] = strMain;
			}
			
			hraExempObj[3][2]="HRA Unpaid Condition 3:";                    
			hraExempObj[3][3]="";
			if(bean.getSalAmt6()!=null && bean.getSalAmt6().length() > 0){
				hraExempObj[3][3]="Rent " + bean.getSalAmt6();
				String strMain = "Rent " + bean.getSalAmt6();
				StringTokenizer stk = new StringTokenizer(bean.getSalAmt6(),"#");
				while (stk.hasMoreElements()) {
					String key = stk.nextToken();
					if(creditCodeMap.containsKey(key)){
						strMain = strMain.replace("#"+ key +"#", creditCodeMap.get(key).toString() + " ");
					}
				}	
				hraExempObj[3][3] = strMain;
			}
			
			hraExempObj[4][0]="HRA Paid Condition 1(Metro):";
			hraExempObj[4][1]="";
			if(bean.getHraPaidMetro() !=null && bean.getHraPaidMetro().length() > 0){
				hraExempObj[4][1] = bean.getHraPaidMetro();
				String strMain = bean.getHraPaidMetro();
				StringTokenizer stk = new StringTokenizer(bean.getHraPaidMetro(),"#");
				while (stk.hasMoreElements()) {
					String key = stk.nextToken();
					if(creditCodeMap.containsKey(key)){
						strMain = strMain.replace("#"+ key +"#", creditCodeMap.get(key).toString() + " ");
					}
				}	
				hraExempObj[4][1] = strMain;
			}
		      
			hraExempObj[4][2]="";                 
			hraExempObj[4][3]="";
	
			int [] hraWidth={25,25,25,25};
			int [] hraAlign={0,0,0,0};
			       
			TableDataSet hraExempTdsData = new TableDataSet();
			hraExempTdsData.setData(hraExempObj);
			hraExempTdsData.setCellAlignment(hraAlign);
			hraExempTdsData.setCellWidth(hraWidth);
			//hraExempTdsData.setBodyFontStyle(1);
			hraExempTdsData.setBorder(false);
			hraExempTdsData.setBorderDetail(3);
			hraExempTdsData.setBlankRowsBelow(1);
			rg.addTableToDoc(hraExempTdsData);
			
			TableDataSet accParamTds = new TableDataSet();
			Object[][] accParamTdsObj = new Object[1][1];
			accParamTdsObj[0][0] = "Accommodation Parameters:";
			accParamTds.setData(accParamTdsObj);
			accParamTds.setCellAlignment(new int[] { 0 });
			accParamTds.setCellWidth(new int[] { 100 });
			accParamTds.setCellColSpan(new int[] {4});
			accParamTds.setBodyFontStyle(1);
			accParamTds.setBorder(false);
			//accParamTds.setBlankRowsBelow(1);
			rg.addTableToDoc(accParamTds);
			
			Object[][] accParamObj=new Object[7][2];
			accParamObj[0][0]="Map Perquisite Head :"; 			
			accParamObj[0][1]= "";
			if (bean.getMapPerquisiteHeadName() != null && bean.getMapPerquisiteHeadName().length() > 0)
				accParamObj[0][1] = bean.getMapPerquisiteHeadName();
			
			accParamObj[1][0]="Accommodation Perk, If Company owned house:";  
			accParamObj[1][1]="";
			accParamObj[2][0]="If Population of City <= 25 Lakh"; 			
			accParamObj[2][1]= "(% of Salary Income)";			
			if (bean.getAccomPerqCentOwnedLess() != null && bean.getAccomPerqCentOwnedLess().length() > 0)
				accParamObj[2][1] = formatter.format(Double.parseDouble(String.valueOf(bean.getAccomPerqCentOwnedLess()))) + 
						" " + "(% of Salary Income)";
			
			accParamObj[3][0]="If Population of City > 25 Lakh";  
			accParamObj[3][1]= "(% of Salary Income)";			
			if (bean.getAccomPerqCentOwnedHigher() != null && bean.getAccomPerqCentOwnedHigher().length() > 0)
				accParamObj[3][1] = formatter.format(Double.parseDouble(String.valueOf(bean.getAccomPerqCentOwnedHigher()))) + 
						" " +  "(% of Salary Income)";
			
			
			
			accParamObj[4][0]="Accommodation Perks, If Company pays the rent:"; 			
			accParamObj[4][1]= "";
			accParamObj[5][0]="If Population of City <= 25 Lakh";  
			accParamObj[5][1]= "(% of Salary Income)";
			if (bean.getAccomPerqCentRentedLess() != null && bean.getAccomPerqCentRentedLess().length() > 0)
				accParamObj[5][1] = formatter.format(Double.parseDouble(String.valueOf(bean.getAccomPerqCentRentedLess()))) + 
						" " + "(% of Salary Income)";
			
			accParamObj[6][0]="If Population of City > 25 Lakh"; 			
			accParamObj[6][1]= "(% of Salary Income)";
			if (bean.getAccomPerqCentRentedHigher() != null && bean.getAccomPerqCentRentedHigher().length() > 0)
				accParamObj[6][1] = formatter.format(Double.parseDouble(String.valueOf(bean.getAccomPerqCentRentedHigher()))) + 
						" " + "(% of Salary Income)";
			
			
			int [] accParamWidth={30,70};
			int [] accParamAlign={0,0};
			
			TableDataSet accParamTdsData = new TableDataSet();
			accParamTdsData.setData(accParamObj);
			accParamTdsData.setCellAlignment(accParamAlign);
			accParamTdsData.setCellWidth(accParamWidth);
			//accParamTdsData.setBodyFontStyle(1);
			accParamTdsData.setBorder(true);
			accParamTdsData.setBorderDetail(3);
			accParamTdsData.setBlankRowsBelow(1);
			rg.addTableToDoc(accParamTdsData);
			
			TableDataSet leaveEncashExempTds = new TableDataSet();
			Object[][] leaveEncashExempTitleObj = new Object[1][1];
			leaveEncashExempTitleObj[0][0] = "Leave Encashment - Least of following is exempted.";
			leaveEncashExempTds.setData(leaveEncashExempTitleObj);
			leaveEncashExempTds.setCellAlignment(new int[] { 0 });
			leaveEncashExempTds.setCellWidth(new int[] { 100 });
			leaveEncashExempTds.setCellColSpan(new int[] {4});
			leaveEncashExempTds.setBodyFontStyle(1);
			leaveEncashExempTds.setBorder(false);
			//leaveEncashExempTds.setBlankRowsBelow(1);
			rg.addTableToDoc(leaveEncashExempTds);
			rg.addLine(blackLineVector);
			
			Object[][] leaveEncashExempObj=new Object[4][2];
			leaveEncashExempObj[0][0]="Condition 1:";
			leaveEncashExempObj[0][1]= "Encash Amount Limit:   ";
			if(bean.getLeaveEncashamt() !=null && bean.getLeaveEncashamt().length() > 0)
				leaveEncashExempObj[0][1]= "Encash Amount Limit:   " + formatter.format(Double.parseDouble(String.valueOf(bean.getLeaveEncashamt())));
			
			leaveEncashExempObj[1][0]="Condition 2:";  
			leaveEncashExempObj[1][1]="Amount of Leave Encashment Actually Received";
			leaveEncashExempObj[2][0]="Condition 3:";
			leaveEncashExempObj[2][1]="No of Leaves * "+ bean.getLeaveEncashMonths() +" Month Average Salary with Components formula "+ bean.getLeaveEncashFormula();
			
			if(bean.getLeaveEncashFormula() !=null && bean.getLeaveEncashFormula().length() > 0){
				String strMain = bean.getLeaveEncashFormula();
				StringTokenizer stk = new StringTokenizer(bean.getLeaveEncashFormula(),"#");
				while (stk.hasMoreElements()) {
					String key = stk.nextToken();
					if(creditCodeMap.containsKey(key)){
						strMain = strMain.replace("#"+ key +"#", creditCodeMap.get(key).toString() + " ");
					}
				}	
				leaveEncashExempObj[2][1] = "No of Leaves * "+ bean.getLeaveEncashMonths() +" Month Average Salary with Components formula " + strMain;
			}
			
			leaveEncashExempObj[3][0]="Map Taxable Leave Encashment Amount To:";
			leaveEncashExempObj[3][1]="";
			if(bean.getLeaveEncInvName() !=null && bean.getLeaveEncInvName().length() > 0)
				leaveEncashExempObj[3][1]= bean.getLeaveEncInvName();			
					
			int [] leaveWidth={30,70};
			int [] leaveAlign={0,0};		      
			         
			TableDataSet leaveExempTdsData = new TableDataSet();
			leaveExempTdsData.setData(leaveEncashExempObj);
			leaveExempTdsData.setCellAlignment(leaveAlign);
			leaveExempTdsData.setCellWidth(leaveWidth);
			//leaveExempTdsData.setBodyFontStyle(1);
			leaveExempTdsData.setBorder(true);
			leaveExempTdsData.setBorderDetail(3);
			leaveExempTdsData.setBlankRowsBelow(1);
			rg.addTableToDoc(leaveExempTdsData);
			
			TableDataSet gratuityExempTds = new TableDataSet();
			Object[][] gratuityExempTitleObj = new Object[1][1];
			gratuityExempTitleObj[0][0] = "Gratuity Exemption";
			gratuityExempTds.setData(gratuityExempTitleObj);
			gratuityExempTds.setCellAlignment(new int[] { 0 });
			gratuityExempTds.setCellWidth(new int[] { 100 });
			gratuityExempTds.setBodyFontStyle(1);
			gratuityExempTds.setBorder(false);
			//gratuityExempTds.setBlankRowsBelow(1);
			rg.addTableToDoc(gratuityExempTds);
			rg.addLine(blackLineVector);
			
			Object[][] gratuityExempObj=new Object[3][2];
			gratuityExempObj[0][0]="Gratuity Amount Limit:"; 			
			gratuityExempObj[0][1]= "";
			gratuityExempObj[1][0]="Exempted Under Section:";  
			gratuityExempObj[1][1]="";
			gratuityExempObj[2][0]="Gratuity Credit Head:";
			gratuityExempObj[2][1]="";
			
			int [] gratuityWidth={30,70};
			int [] gratuityAlign={0,0};
		      
			if(bean.getGratuityAmount() != null && bean.getGratuityAmount().length() > 0)
				gratuityExempObj[0][1]= formatter.format(Double.parseDouble(String.valueOf(bean.getGratuityAmount())));
			if(bean.getExemptSectionNo() !=null && bean.getExemptSectionNo().length() > 0)
				gratuityExempObj[1][1]= bean.getExemptSectionNo();
			if(bean.getCreditType() !=null && bean.getCreditType().length() > 0)
				gratuityExempObj[2][1]= bean.getCreditType();
			
			TableDataSet gratuityExempTdsData = new TableDataSet();
			gratuityExempTdsData.setData(gratuityExempObj);
			gratuityExempTdsData.setCellAlignment(gratuityAlign);
			gratuityExempTdsData.setCellWidth(gratuityWidth);
			//gratuityExempTdsData.setBodyFontStyle(1);
			gratuityExempTdsData.setBorder(true);
			gratuityExempTdsData.setBorderDetail(3);
			gratuityExempTdsData.setBlankRowsBelow(1);
			rg.addTableToDoc(gratuityExempTdsData);
			
			
			TableDataSet ltaExempTds = new TableDataSet();
			Object[][] ltaExempTitleObj = new Object[1][1];
			ltaExempTitleObj[0][0] = "LTA Exemption";
			ltaExempTds.setData(ltaExempTitleObj);
			ltaExempTds.setCellAlignment(new int[] { 0 });
			ltaExempTds.setCellWidth(new int[] { 100 });
			ltaExempTds.setBodyFontStyle(1);
			ltaExempTds.setBorder(false);
			//ltaExempTds.setBlankRowsBelow(1);
			rg.addTableToDoc(ltaExempTds);
			rg.addLine(blackLineVector);
			
			Object[][] ltaExempObj=new Object[3][2];
			ltaExempObj[0][0]="LTA Amount Limit:"; 			
			ltaExempObj[0][1]= "";
			ltaExempObj[1][0]="LTA Exempted Under Section:";  
			ltaExempObj[1][1]="";
			ltaExempObj[2][0]="LTA Credit Head:";
			ltaExempObj[2][1]="";
			
			int [] ltaWidth={30,70};
			int [] ltaAlign={0,0};
		      
			if(bean.getLtaAmount() !=null && bean.getLtaAmount().length() > 0)
				ltaExempObj[0][1]= formatter.format(Double.parseDouble(String.valueOf(bean.getLtaAmount())));
			if(bean.getLtaExemptSectionNo() !=null && bean.getLtaExemptSectionNo().length() > 0)
				ltaExempObj[1][1]= bean.getLtaExemptSectionNo();
			if(bean.getLtaCreditType() !=null && bean.getLtaCreditType().length() > 0)
				ltaExempObj[2][1]= bean.getLtaCreditType();
			
			TableDataSet ltaExempTdsData = new TableDataSet();
			ltaExempTdsData.setData(ltaExempObj);
			ltaExempTdsData.setCellAlignment(ltaAlign);
			ltaExempTdsData.setCellWidth(ltaWidth);
			//ltaExempTdsData.setBodyFontStyle(1);
			ltaExempTdsData.setBorder(true);
			ltaExempTdsData.setBorderDetail(3);
			ltaExempTdsData.setBlankRowsBelow(1);
			rg.addTableToDoc(ltaExempTdsData);
			
			TableDataSet transAllowanceExempTds = new TableDataSet();
			Object[][] transAllowanceTitleObj = new Object[1][1];
			transAllowanceTitleObj[0][0] = "Transport Allowance Exemption";
			transAllowanceExempTds.setData(transAllowanceTitleObj);
			transAllowanceExempTds.setCellAlignment(new int[] { 0 });
			transAllowanceExempTds.setCellWidth(new int[] { 100 });
			transAllowanceExempTds.setBodyFontStyle(1);
			transAllowanceExempTds.setBorder(false);
			//transAllowanceExempTds.setBlankRowsBelow(1);
			rg.addTableToDoc(transAllowanceExempTds);
			rg.addLine(blackLineVector);
			
			Object[][] transAllowanceExempObj=new Object[4][2];
			transAllowanceExempObj[0][0]="Transport Allowance Monthly Limit:"; 			
			transAllowanceExempObj[0][1]= "";
			if(bean.getTraAllowanceLimit() !=null && bean.getTraAllowanceLimit().length() > 0)
				transAllowanceExempObj[0][1]= formatter.format(Double.parseDouble(String.valueOf(bean.getTraAllowanceLimit())));
			
			transAllowanceExempObj[1][0]="Transport Allowance Monthly Limit Incase of Permanent Disability:";  
			transAllowanceExempObj[1][1]="";
			if(bean.getTransAllowanceLimitPermDisabi() !=null && bean.getTransAllowanceLimitPermDisabi().length() > 0)
				transAllowanceExempObj[1][1]= formatter.format(Double.parseDouble(String.valueOf(bean.getTransAllowanceLimitPermDisabi())));
			
			transAllowanceExempObj[2][0]="Transport Allowance Credit Head:";  
			transAllowanceExempObj[2][1]="";
			if(bean.getTraAllowCreditHeadName() !=null && bean.getTraAllowCreditHeadName().length() > 0)
				transAllowanceExempObj[2][1]= bean.getTraAllowCreditHeadName();			
			
			transAllowanceExempObj[3][0]="Transport Allowance Exempted under Section:";  
			transAllowanceExempObj[3][1]="";
			if(bean.getTraAllowExemptSectionNo() !=null && bean.getTraAllowExemptSectionNo().length() > 0)
				transAllowanceExempObj[3][1]= bean.getTraAllowExemptSectionNo();
			
			int [] transAllowanceWidth={30,70};
			int [] transAllowanceAlign={0,0};
			
			TableDataSet transAllowanceExempTdsData = new TableDataSet();
			transAllowanceExempTdsData.setData(transAllowanceExempObj);
			transAllowanceExempTdsData.setCellAlignment(transAllowanceAlign);
			transAllowanceExempTdsData.setCellWidth(transAllowanceWidth);
			//transAllowanceExempTdsData.setBodyFontStyle(1);
			transAllowanceExempTdsData.setBorder(true);
			transAllowanceExempTdsData.setBorderDetail(3);
			transAllowanceExempTdsData.setBlankRowsBelow(1);
			rg.addTableToDoc(transAllowanceExempTdsData);
									
			
			TableDataSet vehicleMaintenanceExempTds = new TableDataSet();
			Object[][] vehicleMaintenanceTitleObj = new Object[1][1];
			vehicleMaintenanceTitleObj[0][0] = "Vehicle Maintenance Exemption";
			vehicleMaintenanceExempTds.setData(vehicleMaintenanceTitleObj);
			vehicleMaintenanceExempTds.setCellAlignment(new int[] { 0 });
			vehicleMaintenanceExempTds.setCellWidth(new int[] { 100 });
			vehicleMaintenanceExempTds.setBodyFontStyle(1);
			vehicleMaintenanceExempTds.setBorder(false);
			//vehicleMaintenanceExempTds.setBlankRowsBelow(1);
			rg.addTableToDoc(vehicleMaintenanceExempTds);
			rg.addLine(blackLineVector);
			
			Object[][] vehicleMaintenanceExempObj=new Object[4][2];
			vehicleMaintenanceExempObj[0][0]="Condition 1:"; 			
			vehicleMaintenanceExempObj[0][1]= "Vehicle Capacity > 1600cc Vehicle Maintenance Monthly Exemption Amount: ";
			if(bean.getVehicleCapGreaterthan1600() != null && bean.getVehicleCapGreaterthan1600().length() > 0)
				vehicleMaintenanceExempObj[0][1] = vehicleMaintenanceExempObj[0][1] + formatter.format(Double.parseDouble(String.valueOf(bean.getVehicleCapGreaterthan1600())));
			
			vehicleMaintenanceExempObj[1][0]="Condition 2:";  
			vehicleMaintenanceExempObj[1][1]="Vehicle Capacity <= 1600cc Vehicle Maintenance Monthly Exemption Amount: ";
			if(bean.getVehicleCapLessthan1600() != null && bean.getVehicleCapLessthan1600().length() > 0)
				vehicleMaintenanceExempObj[1][1] = vehicleMaintenanceExempObj[1][1] + formatter.format(Double.parseDouble(String.valueOf(bean.getVehicleCapLessthan1600())));
						
			vehicleMaintenanceExempObj[2][0]="Additional exemption, If Driver used:";
			vehicleMaintenanceExempObj[2][1]="";
			if(bean.getDriverUsedAddExemption() != null && bean.getDriverUsedAddExemption().length() > 0)
				vehicleMaintenanceExempObj[2][1] = formatter.format(Double.parseDouble(String.valueOf(bean.getDriverUsedAddExemption())));
			
			vehicleMaintenanceExempObj[3][0]="Vehicle Maintenance Exempted under Section:";
			vehicleMaintenanceExempObj[3][1]="";
			if(bean.getVehicalAllowExemptSectionNo() != null && bean.getVehicalAllowExemptSectionNo().length() > 0)
				vehicleMaintenanceExempObj[3][1] = bean.getVehicalAllowExemptSectionNo();
			
			int [] vehicleMaintenanceWidth={30,70};
			int [] vehicleMaintenanceAlign={0,0};
				
			TableDataSet vehicleMaintenanceExempTdsData = new TableDataSet();
			vehicleMaintenanceExempTdsData.setData(vehicleMaintenanceExempObj);
			vehicleMaintenanceExempTdsData.setCellAlignment(vehicleMaintenanceAlign);
			vehicleMaintenanceExempTdsData.setCellWidth(vehicleMaintenanceWidth);
			//vehicleMaintenanceExempTdsData.setBodyFontStyle(1);
			vehicleMaintenanceExempTdsData.setBorder(true);
			vehicleMaintenanceExempTdsData.setBorderDetail(3);
			vehicleMaintenanceExempTdsData.setBlankRowsBelow(1);
			rg.addTableToDoc(vehicleMaintenanceExempTdsData);
			
			TableDataSet donationExempTds = new TableDataSet();
			Object[][] donationTitleObj = new Object[1][1];
			donationTitleObj[0][0] = "Donation to Charities exemption";
			donationExempTds.setData(donationTitleObj);
			donationExempTds.setCellAlignment(new int[] { 0 });
			donationExempTds.setCellWidth(new int[] { 100 });
			donationExempTds.setBodyFontStyle(1);
			donationExempTds.setBorder(false);
			//donationExempTds.setBlankRowsBelow(1);
			rg.addTableToDoc(donationExempTds);
			rg.addLine(blackLineVector);
			
			Object[][] donationExempObj=new Object[1][2];
			donationExempObj[0][0]="Donations Exempted under Section: "; 			
			donationExempObj[0][1]= "";
			
			int [] donationWidth={30,70};
			int [] donationAlign={0,0};
		      
			if(bean.getDonationsExemptSectionNo() !=null && bean.getDonationsExemptSectionNo().length() > 0)
				donationExempObj[0][1]= donationExempObj[0][1] + bean.getDonationsExemptSectionNo();
				
			TableDataSet donationExempTdsData = new TableDataSet();
			donationExempTdsData.setData(donationExempObj);
			donationExempTdsData.setCellAlignment(donationAlign);
			donationExempTdsData.setCellWidth(donationWidth);
			//donationExempTdsData.setBodyFontStyle(1);
			donationExempTdsData.setBorder(true);
			donationExempTdsData.setBorderDetail(3);
			donationExempTdsData.setBlankRowsBelow(1);
			rg.addTableToDoc(donationExempTdsData);
			/*
			TableDataSet permaDisDedTds = new TableDataSet();
			Object[][] permaDisDedTitleObj = new Object[1][1];
			permaDisDedTitleObj[0][0] = "Permanent Disability Deduction";
			permaDisDedTds.setData(permaDisDedTitleObj);
			permaDisDedTds.setCellAlignment(new int[] { 0 });
			permaDisDedTds.setCellWidth(new int[] { 100 });
			permaDisDedTds.setBodyFontStyle(1);
			permaDisDedTds.setBorder(false);
			//permaDisDedTds.setBlankRowsBelow(1);
			rg.addTableToDoc(permaDisDedTds);
			rg.addLine(blackLineVector);
			
			Object[][] permaDisDedObj=new Object[1][2];
			permaDisDedObj[0][0]="Permanent Disability Deduction under Section: "; 			
			permaDisDedObj[0][1]= "";
			
			int [] permaDisDedWidth={30,70};
			int [] permaDisDedAlign={0,0};
		      
			if(bean.getPermanentDisabilityDedName() !=null && bean.getPermanentDisabilityDedName().length() > 0)
				permaDisDedObj[0][1]= permaDisDedObj[0][1] + bean.getPermanentDisabilityDedName();
				
			TableDataSet permaDisDedTdsData = new TableDataSet();
			permaDisDedTdsData.setData(permaDisDedObj);
			permaDisDedTdsData.setCellAlignment(permaDisDedAlign);
			permaDisDedTdsData.setCellWidth(permaDisDedWidth);
			//permaDisDedTdsData.setBodyFontStyle(1);
			permaDisDedTdsData.setBorder(true);
			permaDisDedTdsData.setBorderDetail(3);
			permaDisDedTdsData.setBlankRowsBelow(1);
			rg.addTableToDoc(permaDisDedTdsData);*/
			
			TableDataSet loanInteRateTds = new TableDataSet();
			Object[][] loanInteRateTitleObj = new Object[1][1];
			loanInteRateTitleObj[0][0] = "Loan Interest Rates";
			loanInteRateTds.setData(loanInteRateTitleObj);
			loanInteRateTds.setCellAlignment(new int[] { 0 });
			loanInteRateTds.setCellWidth(new int[] { 100 });
			loanInteRateTds.setBodyFontStyle(1);
			loanInteRateTds.setBorder(false);
			//loanInteRateTds.setBlankRowsBelow(1);
			rg.addTableToDoc(loanInteRateTds);
			rg.addLine(blackLineVector);
			
			Object[][] loanInteRateObj=new Object[3][2];
			loanInteRateObj[0][0]="Government's Standard Loan Interest Rate: "; 			
			loanInteRateObj[0][1]= "";
			if(bean.getGovLoanRate() !=null && bean.getGovLoanRate().length() > 0)
				loanInteRateObj[0][1]= formatter.format(Double.parseDouble(String.valueOf(bean.getGovLoanRate())));
			
			loanInteRateObj[1][0]="Minimum Loan Amount :"; 			
			loanInteRateObj[1][1]= "";
			if(bean.getMinLoanAmt() !=null && bean.getMinLoanAmt().length() > 0)
				loanInteRateObj[1][1]= bean.getMinLoanAmt();
						
			loanInteRateObj[2][0]="Map Perquisite Head for Company Loan :"; 			
			loanInteRateObj[2][1]= "";
			if(bean.getPerqHeadCompanyLoan() !=null && bean.getPerqHeadCompanyLoan().length() > 0)
				loanInteRateObj[2][1]= bean.getPerqHeadCompanyLoan();
			
			
			int [] loanInteRateWidth={30,70};
			int [] loanInteRateAlign={0,0};
		      
				
			TableDataSet loanInteRateTdsData = new TableDataSet();
			loanInteRateTdsData.setData(loanInteRateObj);
			loanInteRateTdsData.setCellAlignment(loanInteRateAlign);
			loanInteRateTdsData.setCellWidth(loanInteRateWidth);
			//loanInteRateTdsData.setBodyFontStyle(1);
			loanInteRateTdsData.setBorder(true);
			loanInteRateTdsData.setBorderDetail(3);
			loanInteRateTdsData.setBlankRowsBelow(1);
			rg.addTableToDoc(loanInteRateTdsData);
			
			//rg.addLine(redLineVector1);
			
			rg.addProperty(rg.PAGE_BREAK);

			String query1 = "SELECT DISTINCT HRMS_TAX_SLAB.TAX_PERCENT FROM HRMS_TAX_SLAB " +
					"WHERE HRMS_TAX_SLAB.TAX_FROM_YEAR=? AND HRMS_TAX_SLAB.TAX_TO_YEAR=?";
			
			Object[] paraObj = null;
			
			paraObj = new Object[2];
			paraObj[0] = bean.getFromYear();
			paraObj[1] = bean.getToYear();
			
			Object[][] distPerc = getSqlModel().getSingleResult(query1, paraObj);
			
			String query2 = "SELECT HRMS_TAX_SLAB.TAX_FROM_AMOUNT||'-'||HRMS_TAX_SLAB.TAX_TO_AMT,HRMS_TAX_SLAB.TAX_PERCENT||'-'||HRMS_TAX_SLAB.TAX_EMP_TYPE"  
				+ " FROM HRMS_TAX_SLAB WHERE HRMS_TAX_SLAB.TAX_FROM_YEAR=?" 
				+ " AND HRMS_TAX_SLAB.TAX_TO_YEAR=?" 
				+ " ORDER BY HRMS_TAX_SLAB.TAX_EMP_TYPE,HRMS_TAX_SLAB.TAX_PERCENT";
			
			paraObj = new Object[2];
			paraObj[0] = bean.getFromYear();
			paraObj[1] = bean.getToYear();

			Object[][] distPerc1 = getSqlModel().getSingleResult(query2, paraObj);
			HashMap map = new HashMap(100);
			for (int j = 0; j < distPerc1.length; j++) {
				map.put(String.valueOf(distPerc1[j][1]), String.valueOf(distPerc1[j][0]));
			}
			
			TableDataSet taxSlabsTds = new TableDataSet();
			Object[][] taxSlabsObj = new Object[1][1];
			taxSlabsObj[0][0] = "TDS Slabs";
			taxSlabsTds.setData(taxSlabsObj);
			taxSlabsTds.setCellAlignment(new int[] { 0 });
			taxSlabsTds.setCellWidth(new int[] { 100 });
			taxSlabsTds.setBodyFontStyle(1);
			taxSlabsTds.setBorder(false);
			//taxSlabsTds.setBlankRowsBelow(1);
			taxSlabsTds.setBlankRowsAbove(1);
			rg.addTableToDoc(taxSlabsTds);
						
			rg.addLine(blackLineVector);
			
			String[] colNames = { "%", "Men", "Women", "Senior Citizen" };
			int[] tdsSlabsWidth = { 10, 30, 30, 30 };		
			int[] tdsSlabsAlign = { 2, 2, 2, 2 };
		
			Object[][] tdsSlabsObj=new Object[distPerc.length][4];
			for (int j = 0; j < distPerc.length; j++) {
				tdsSlabsObj[j][0] = distPerc[j][0];
				if(map.get(distPerc[j][0]+"-M") == null )
					tdsSlabsObj[j][1] = "NA";
				else
					tdsSlabsObj[j][1] = map.get(distPerc[j][0]+"-M");
				
				if(map.get(distPerc[j][0]+"-F") == null )
					tdsSlabsObj[j][2] = "NA";
				else
					tdsSlabsObj[j][2] = map.get(distPerc[j][0]+"-F");
				
				if(map.get(distPerc[j][0]+"-S") == null )
					tdsSlabsObj[j][3] = "NA";
				else
					tdsSlabsObj[j][3] = map.get(distPerc[j][0]+"-S");
				
			}
			
			TableDataSet tdsSlabsTdsData = new TableDataSet();
			tdsSlabsTdsData.setHeader(colNames);
			tdsSlabsTdsData.setHeaderBGColor(new BaseColor(210,210,210));
			tdsSlabsTdsData.setHeaderBorderDetail(3);			
			tdsSlabsTdsData.setHeaderFontStyle(Font.BOLD);
			tdsSlabsTdsData.setData(tdsSlabsObj);
			tdsSlabsTdsData.setCellAlignment(tdsSlabsAlign);
			tdsSlabsTdsData.setCellWidth(tdsSlabsWidth);
			//tdsSlabsTdsData.setBodyFontStyle(1);
			tdsSlabsTdsData.setBorder(true);
			tdsSlabsTdsData.setBorderDetail(3);
			tdsSlabsTdsData.setBlankRowsBelow(1);
			rg.addTableToDoc(tdsSlabsTdsData);
			
			//rg.addLine(redLineVector1);
			
			TableDataSet taxConfigTitleTds = new TableDataSet();
			Object[][] taxConfigTitleObj = new Object[1][1];
			taxConfigTitleObj[0][0] = "Tax Configuration";
			taxConfigTitleTds.setData(taxConfigTitleObj);
			taxConfigTitleTds.setCellAlignment(new int[] { 0 });
			taxConfigTitleTds.setCellWidth(new int[] { 100 });
			taxConfigTitleTds.setBodyFontStyle(1);
			taxConfigTitleTds.setBorder(false);
			taxConfigTitleTds.setBlankRowsAbove(1);
			//taxConfigTitleTds.setBlankRowsBelow(1);
			rg.addTableToDoc(taxConfigTitleTds);
			
			rg.addLine(blackLineVector);
			
			TableDataSet exempTitleTds = new TableDataSet();
			Object[][] exempTitleObj = new Object[1][1];
			exempTitleObj[0][0] = "Exemption under Section 10 & 17";
			exempTitleTds.setData(exempTitleObj);
			exempTitleTds.setCellAlignment(new int[] { 0 });
			exempTitleTds.setCellWidth(new int[] { 100 });
			exempTitleTds.setBodyFontStyle(1);
			exempTitleTds.setBorder(false);
			//exempTitleTds.setBlankRowsBelow(1);
			rg.addTableToDoc(exempTitleTds);
			rg.addLine(blackLineVector);
			
			int length=0;
			query1 = "SELECT HRMS_TAX_INVESTMENT.INV_NAME, HRMS_TAX_INVESTMENT.INV_SECTION FROM HRMS_TAX_INVESTMENT WHERE FROM_YEAR=?"
				+ " AND TO_YEAR=? and INV_GROUP='E'";
			
			paraObj = new Object[2];
			paraObj[0] = bean.getFromYear();
			paraObj[1] = bean.getToYear();
			
			Object[][] taxConfigExemp = getSqlModel().getSingleResult(query1,paraObj);
			
			if(taxConfigExemp.length%2==0){
				length = taxConfigExemp.length / 2;
			}else{
				length = (taxConfigExemp.length / 2) + 1;
			}
			
			Object[][] taxConfigExempObj = new Object[length][2];
			for(int j=0,i=0; j<taxConfigExemp.length; j++,i++){
				taxConfigExempObj[i][0] = taxConfigExemp[j][0] + "(" + taxConfigExemp[j][1] + ")";
				if(j+1 < taxConfigExemp.length){
					taxConfigExempObj[i][1] = taxConfigExemp[j+1][0] + "(" + taxConfigExemp[j+1][1] + ")";
					j++;
				}
			}
			
			TableDataSet taxConfigExempData = new TableDataSet();
			taxConfigExempData.setData(taxConfigExempObj);
			taxConfigExempData.setCellAlignment(new int[] {0,0});
			taxConfigExempData.setCellWidth(new int[] {50,50});
			//taxConfigExempData.setBodyFontStyle(1);
			taxConfigExempData.setBorder(true);
			taxConfigExempData.setBorderDetail(3);
			taxConfigExempData.setBlankRowsBelow(1);
			rg.addTableToDoc(taxConfigExempData);
			
			
			TableDataSet otherIncomeTitleTds = new TableDataSet();
			Object[][] otherIncomeTitleObj = new Object[1][1];
			otherIncomeTitleObj[0][0] = "Other income";
			otherIncomeTitleTds.setData(otherIncomeTitleObj);
			otherIncomeTitleTds.setCellAlignment(new int[] { 0 });
			otherIncomeTitleTds.setCellWidth(new int[] { 100 });
			otherIncomeTitleTds.setBodyFontStyle(1);
			otherIncomeTitleTds.setBorder(false);
			//otherIncomeTitleTds.setBlankRowsBelow(1);
			rg.addTableToDoc(otherIncomeTitleTds);
			rg.addLine(blackLineVector);
			
			query1 = "SELECT HRMS_TAX_INVESTMENT.INV_NAME, HRMS_TAX_INVESTMENT.INV_SECTION FROM HRMS_TAX_INVESTMENT WHERE FROM_YEAR=?"
				+ " AND TO_YEAR=? and INV_GROUP='O'";
			
			paraObj = new Object[2];
			paraObj[0] = bean.getFromYear();
			paraObj[1] = bean.getToYear();
			
			Object[][] taxConfigOther = getSqlModel().getSingleResult(query1,paraObj);
			
			if(taxConfigOther.length%2==0){
				length = taxConfigOther.length / 2;
			}else{
				length = (taxConfigOther.length / 2) + 1;
			}
			
			Object[][] taxConfigOtherObj = new Object[length][2];
			for(int j=0,i=0; j<taxConfigOther.length; j++,i++){
				taxConfigOtherObj[i][0] = taxConfigOther[j][0] + "(" + taxConfigOther[j][1] + ")";
				if(j+1 < taxConfigOther.length){
					taxConfigOtherObj[i][1] = taxConfigOther[j+1][0] + "(" + taxConfigOther[j+1][1] + ")";
					j++;
				}
			}
			
			TableDataSet taxConfigOtherData = new TableDataSet();
			taxConfigOtherData.setData(taxConfigOtherObj);
			taxConfigOtherData.setCellAlignment(new int[] {0,0});
			taxConfigOtherData.setCellWidth(new int[] {50,50});
			//taxConfigOtherData.setBodyFontStyle(1);
			taxConfigOtherData.setBorder(true);
			taxConfigOtherData.setBorderDetail(3);
			taxConfigOtherData.setBlankRowsBelow(1);
			rg.addTableToDoc(taxConfigOtherData);
			
			TableDataSet deductionTitleTds = new TableDataSet();
			Object[][] deductionTitleObj = new Object[1][1];
			deductionTitleObj[0][0] = "Deductions under Chapter VI-A";
			deductionTitleTds.setData(deductionTitleObj);
			deductionTitleTds.setCellAlignment(new int[] { 0 });
			deductionTitleTds.setCellWidth(new int[] { 100 });
			deductionTitleTds.setBodyFontStyle(1);
			deductionTitleTds.setBorder(false);
			//deductionTitleTds.setBlankRowsBelow(1);
			rg.addTableToDoc(deductionTitleTds);
			rg.addLine(blackLineVector);
			
			query1 = "SELECT HRMS_TAX_INVESTMENT.INV_NAME, HRMS_TAX_INVESTMENT.INV_SECTION FROM HRMS_TAX_INVESTMENT WHERE FROM_YEAR=?"
				+ " AND TO_YEAR=? and INV_GROUP='D'";
			
			paraObj = new Object[2];
			paraObj[0] = bean.getFromYear();
			paraObj[1] = bean.getToYear();
			
			Object[][] taxConfigDedu = getSqlModel().getSingleResult(query1, paraObj);
			
			if(taxConfigDedu.length%2==0){
				length = taxConfigDedu.length / 2;
			}else{
				length = (taxConfigDedu.length / 2) + 1;
			}
			
			Object[][] taxConfigDeduObj = new Object[length][2];
			for(int j=0,i=0; j<taxConfigDedu.length; j++,i++){
				taxConfigDeduObj[i][0] = taxConfigDedu[j][0] + "(" + taxConfigDedu[j][1] + ")";
				if(j+1 < taxConfigDedu.length){
					taxConfigDeduObj[i][1] = taxConfigDedu[j+1][0] + "(" + taxConfigDedu[j+1][1] + ")";
					j++;
				}
			}
			
			TableDataSet taxConfigDeduData = new TableDataSet();
			taxConfigDeduData.setData(taxConfigDeduObj);
			taxConfigDeduData.setCellAlignment(new int[] {0,0});
			taxConfigDeduData.setCellWidth(new int[] {50,50});
//			taxConfigDeduData.setBodyFontStyle(1);
			taxConfigDeduData.setBorder(true);
			taxConfigDeduData.setBorderDetail(3);
			taxConfigDeduData.setBlankRowsBelow(1);
			rg.addTableToDoc(taxConfigDeduData);
			
			TableDataSet deduction80cTitleTds = new TableDataSet();
			Object[][] deduction80cTitleObj = new Object[1][1];
			deduction80cTitleObj[0][0] = "Deductions under Chapter VI (sec 80C)";
			deduction80cTitleTds.setData(deduction80cTitleObj);
			deduction80cTitleTds.setCellAlignment(new int[] { 0 });
			deduction80cTitleTds.setCellWidth(new int[] { 100 });
			deduction80cTitleTds.setBodyFontStyle(1);
			deduction80cTitleTds.setBorder(false);
			//deduction80cTitleTds.setBlankRowsBelow(1);
			rg.addTableToDoc(deduction80cTitleTds);
			rg.addLine(blackLineVector);
			
			query1 = "SELECT HRMS_TAX_INVESTMENT.INV_NAME, HRMS_TAX_INVESTMENT.INV_SECTION FROM HRMS_TAX_INVESTMENT WHERE FROM_YEAR=?"
				+ " AND TO_YEAR=? and INV_GROUP='S'";
			
			paraObj = new Object[2];
			paraObj[0] = bean.getFromYear();
			paraObj[1] = bean.getToYear();
			
			Object[][] taxConfigDedu80 = getSqlModel().getSingleResult(query1,paraObj);
			
			if(taxConfigDedu80.length%2==0){
				length = taxConfigDedu80.length / 2;
			}else{
				length = (taxConfigDedu80.length / 2) + 1;
			}
			
			Object[][] taxConfigDedu80Obj = new Object[length][2];
			for(int j=0,i=0; j<taxConfigDedu80.length;j++,i++ ){
				taxConfigDedu80Obj[i][0] = taxConfigDedu80[j][0] + "(" + taxConfigDedu80[j][1] + ")";
				if(j+1 < taxConfigDedu80.length){
					taxConfigDedu80Obj[i][1] = taxConfigDedu80[j+1][0] + "(" + taxConfigDedu80[j+1][1] + ")";
					j++;
				}
				
			}
			
			TableDataSet taxConfigDedu80Data = new TableDataSet();
			taxConfigDedu80Data.setData(taxConfigDedu80Obj);
			taxConfigDedu80Data.setCellAlignment(new int[] {0,0});
			taxConfigDedu80Data.setCellWidth(new int[] {50,50});
//			taxConfigDedu80Data.setBodyFontStyle(1);
			taxConfigDedu80Data.setBorder(true);
			taxConfigDedu80Data.setBorderDetail(3);
			taxConfigDedu80Data.setBlankRowsBelow(1);
			rg.addTableToDoc(taxConfigDedu80Data);
			
			//rg.addLine(redLineVector1);
			
			/*
			Object[][] genrateByObj = new Object[1][1];
			Date todaysDate = new java.util.Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd, MMM yyyy HH:mm");
			String formattedDate = formatter.format(todaysDate);

			genrateByObj[0][0] = "Generated By Administrator on " + formattedDate; 
			TableDataSet genrateByTds = new TableDataSet();
			genrateByTds.setData(genrateByObj);
			genrateByTds.setCellAlignment(new int[] {0});
			genrateByTds.setCellWidth(new int[] {100});
			genrateByTds.setCellColSpan(new int[] {4});
			genrateByTds.setBodyFontDetails(FontFamily.HELVETICA, 10, Font.NORMAL, new BaseColor(0, 0, 0));
			genrateByTds.setBorder(false);
			genrateByTds.setBlankRowsAbove(1);
			rg.addTableToDoc(genrateByTds);
			*/			
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
}
