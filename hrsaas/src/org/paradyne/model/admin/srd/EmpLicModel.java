package org.paradyne.model.admin.srd;
import javax.servlet.http.*;
import java.util.ArrayList;
 import org.paradyne.lib.ModelBase;
import org.paradyne.bean.admin.srd.EmpLic;
import org.paradyne.bean.admin.srd.EmployeeExperience;


/*
 * author:Pradeep Ku Sahoo
 * Date:-09-08-2007
 */


public class EmpLicModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	org.paradyne.bean.admin.srd.EmpLic el = null;
	
	
	/**
	 * following function is called when the Add Policy button is clicked in the jsp page  
	 * @param bean
	 * @param policyName
	 * @param policyN
	 * @param insAMt
	 * @param premAmt
	 * @param stDt
	 * @param endDt
	 * @param taxEx
	 * @param debitEx
	 * @param debitExCode
	 * @param debitCode
	 * @param debitName
	 * @param licId
	 */
	public void addPolicy(EmpLic bean,String[] policyName,String[] policyN,String[] insAMt,String[] premAmt,String[] stDt,String[] endDt,String[] taxEx,String[] debitEx,
			String[] debitExCode,String[] debitCode,String[] debitName,String[] licId){
		bean.setFlag("true");
		ArrayList<Object> list=new ArrayList<Object>();
		
		if(policyName!=null){
			for(int i=0;i<policyName.length;i++){
								
				EmpLic bean1=new EmpLic();
				bean1.setHLicName(policyName[i]);
				bean1.setHPolicyNo(policyN[i]);
				bean1.setHInsAMt(insAMt[i]);
				bean1.setHPremAMt(premAmt[i]);
				bean1.setHStartDate(stDt[i]);
				bean1.setHEndDate(endDt[i]);
				bean1.setHTaxEx(taxEx[i]);
				bean1.setHDebitExempt(debitEx[i]);
				bean1.setHDebitExemptCode(debitExCode[i]);
				bean1.setHDebitCode(debitCode[i]);
				bean1.setHDebitName(debitName[i]);
				if(licId!=null && licId.length>0){
					bean1.setLicId(licId[i]);
				}
				list.add(bean1);
							
			}
			
			
		}
			   EmpLic empLic=new EmpLic();
			   empLic.setHLicName(bean.getLicName());
			   empLic.setHPolicyNo(bean.getLicPolicyNo());
			   empLic.setHInsAMt(bean.getInsuranceAmt());
			   empLic.setHPremAMt(bean.getLicPremiumAmt());
			   empLic.setHStartDate(bean.getLicStDate());
			   empLic.setHEndDate(bean.getLicEndDt());
			    if(bean.getTaxExempt().equals("Y")){
					empLic.setHTaxEx("Yes");
				}else if(bean.getTaxExempt().equals("N")){
					empLic.setHTaxEx("No");
				}else{
					empLic.setHTaxEx("");
			    }
			
			   empLic.setHDebitExempt(bean.getInvName());
			   empLic.setHDebitExemptCode(bean.getInvCode());
			   empLic.setHDebitCode(bean.getDebitCode());
			   empLic.setHDebitName(bean.getDebitName());
			   list.add(empLic);
			
			
			
			
		
		bean.setLicList(list);
		
		
		
	}
	
	
	public void modPolicy(EmpLic bean,String[] policyName,String[] policyN,String[] insAMt,String[] premAmt,String[] stDt,String[] endDt,String[] taxEx,String[] debitEx,
			String[] debitExCode,String[] debitCode,String[] debitName,String[] licId){
		bean.setFlag("true");
		ArrayList<Object> list=new ArrayList<Object>();
		
		
			for(int i=0;i<policyName.length;i++){
				EmpLic bean1=new EmpLic();
			if((i+1 != Integer.parseInt(bean.getParaId())))	{			
			
				bean1.setHLicName(policyName[i]);
				bean1.setHPolicyNo(policyN[i]);
				bean1.setHInsAMt(insAMt[i]);
				bean1.setHPremAMt(premAmt[i]);
				bean1.setHStartDate(stDt[i]);
				bean1.setHEndDate(endDt[i]);
				bean1.setHTaxEx(taxEx[i]);
				bean1.setHDebitExempt(debitEx[i]);
				bean1.setHDebitExemptCode(debitExCode[i]);
				bean1.setHDebitCode(debitCode[i]);
				bean1.setHDebitName(debitName[i]);
				if(licId!=null && licId.length>0){
					bean1.setLicId(licId[i]);
				}
				list.add(bean1);
							
			}else{
				EmpLic empLic=new EmpLic();
				   empLic.setHLicName(bean.getLicName());
				   empLic.setHPolicyNo(bean.getLicPolicyNo());
				   empLic.setHInsAMt(bean.getInsuranceAmt());
				   empLic.setHPremAMt(bean.getLicPremiumAmt());
				   empLic.setHStartDate(bean.getLicStDate());
				   empLic.setHEndDate(bean.getLicEndDt());
				   if(bean.getTaxExempt().equals("Y")){
						empLic.setHTaxEx("Yes");
					}else if(bean.getTaxExempt().equals("N")){
						empLic.setHTaxEx("No");
					}else{
						empLic.setHTaxEx("");
				    }
				 //  empLic.setHTaxEx(bean.getTaxExempt());
				   empLic.setHDebitExempt(bean.getInvName());
				   empLic.setHDebitExemptCode(bean.getInvCode());
				   empLic.setHDebitCode(bean.getDebitCode());
				   empLic.setHDebitName(bean.getDebitName());
				   list.add(empLic);
				
			 }
		  }
			
		
		
		
		bean.setLicList(list);
		
		
		
	}
		
	/**
	 * following function is called when the edit button is clicked.
	 * This function removes the current row from the list.
	 * @param bean
	 * @param policyName
	 * @param policyN
	 * @param insAMt
	 * @param premAmt
	 * @param stDt
	 * @param endDt
	 * @param taxEx
	 * @param debitEx
	 * @param debitExCode
	 * @param debitCode
	 * @param debitName
	 * @param licId
	 */
	public void delPolicy(EmpLic bean,String[] policyName,String[] policyN,String[] insAMt,String[] premAmt,String[] stDt,String[] endDt,String[] taxEx,String[] debitEx,
			String[] debitExCode,String[] debitCode,String[] debitName,String[] licId){
		bean.setFlag("true");
		ArrayList<Object> list=new ArrayList<Object>();
		for(int i=0;i<policyName.length;i++){
			EmpLic bean1=new EmpLic();
			if((i+1 != Integer.parseInt(bean.getParaId()))){
			
				bean1.setHLicName(policyName[i]);
				bean1.setHPolicyNo(policyN[i]);
				bean1.setHInsAMt(insAMt[i]);
				bean1.setHPremAMt(premAmt[i]);
				bean1.setHStartDate(stDt[i]);
				bean1.setHEndDate(endDt[i]);
				bean1.setHTaxEx(taxEx[i]);
				bean1.setHDebitExempt(debitEx[i]);
				bean1.setHDebitExemptCode(debitExCode[i]);
				bean1.setHDebitCode(debitCode[i]);
				bean1.setHDebitName(debitName[i]);
				if(licId!=null && licId.length>0){
					bean1.setLicId(licId[i]);
				}
				list.add(bean1);
				
				
			 }	
			
			bean.setLicList(list);
		 }
		
	}
	
		public String addLic(EmpLic bean,HttpServletRequest request){
			logger.info(">>>>>>");
			String str="";
			String[] licN=request.getParameterValues("hLicName");
			String[] policyNo=request.getParameterValues("hPolicyNo");
			String[] insAmt=request.getParameterValues("hInsAMt");
			String[] premAmt=request.getParameterValues("hPremAMt");
			String[] startDate=request.getParameterValues("hStartDate");
			String[] endDate=request.getParameterValues("hEndDate");
			String[] taxEx=request.getParameterValues("hTaxEx");
			String[] debitExmpt=request.getParameterValues("hDebitExempt");
			String[] debitCode=request.getParameterValues("hDebitCode");
			String[] debitName=request.getParameterValues("hDebitName");
			String[] debitExmptCode=request.getParameterValues("hDebitExemptCode");
			if(licN!=null && licN.length>0){
					if(chkEmpId(bean)){
					Object[][] delObj=new Object[1][1];
					delObj[0][0]=bean.getEmpId();
					getSqlModel().singleExecute(getQuery(3),delObj);
					
					for(int i=0;i<licN.length;i++){
					Object[][] addObj=new Object[1][10];
					addObj[0][0]=licN[i];
					addObj[0][1]=policyNo[i];
					addObj[0][2]=insAmt[i];
					addObj[0][3]=premAmt[i];
					addObj[0][4]=startDate[i];
					addObj[0][5]=endDate[i];
					
					if(taxEx[i].equals("Yes")){
						addObj[0][6]=String.valueOf("Y");
					}else if(taxEx[i].equals("No")){
						addObj[0][6]=String.valueOf("N");
					}else{
						addObj[0][6]=String.valueOf("");
					}
					   addObj[0][7]=debitExmptCode[i];
					   
					if(debitCode[i].equals("") || debitCode[i].equals("null")){
					   addObj[0][8]=String.valueOf("");
					}else{
					 addObj[0][8]=debitCode[i];
					}
					addObj[0][9]=bean.getEmpId();
					
					getSqlModel().singleExecute(getQuery(1),addObj);
				
				    }
					str="mod";
				}else{
					
					
					for(int i=0;i<licN.length;i++){
					Object[][] addObj=new Object[1][10];
					addObj[0][0]=licN[i];
					addObj[0][1]=policyNo[i];
					addObj[0][2]=insAmt[i];
					addObj[0][3]=premAmt[i];
					addObj[0][4]=startDate[i];
					addObj[0][5]=endDate[i];
					if(taxEx[i].equals("Yes")){
						addObj[0][6]=String.valueOf("Y");
					}else if(taxEx[i].equals("No")){
						addObj[0][6]=String.valueOf("N");
					}else{
						addObj[0][6]=String.valueOf("");
					}
					addObj[0][7]=debitExmptCode[i];
					addObj[0][8]=debitCode[i];
					addObj[0][9]=bean.getEmpId();
					getSqlModel().singleExecute(getQuery(1),addObj);
					
					}
				
				  str="add";  
					
				}
				
			/*	if(chkEmpId(bean)){
					
					getSqlModel().singleExecute(getQuery(1),addObj);
					str="mod";
				}else{
					getSqlModel().singleExecute(getQuery(1),addObj);
					str="add";
				}*/
				
				
			}
		/*	Object addObj[][]=new Object[1][11];
			
			addObj[0][0]=bean.getEmpId();
			logger.info("********1:" +String.valueOf( bean.getEmpId()));
			addObj[0][1]=bean.getLicName();
			addObj[0][2]=bean.getLicPolicyNo();
			addObj[0][3]=bean.getInsuranceAmt();
			addObj[0][4]=bean.getLicPremiumAmt();
			addObj[0][5]=bean.getLicStDate();
			addObj[0][6]=bean.getLicEndDt();
			addObj[0][7]=bean.getTaxExempt();
			addObj[0][8]=bean.getPaidInSal();
			addObj[0][9]=bean.getLicActive();
			addObj[0][10]=bean.getDebitCode();*/
			//return getSqlModel().singleExecute(getQuery(1),addObj);
		
			
		return str;	
		}
		

		public void empDebit(EmpLic bean){
			Object debObj[][]=new Object[1][1];
			 
			debObj[0][0]=bean.getEmpId();
			logger.info("********1:" +String.valueOf( bean.getEmpId()));
		
			String query1 ="select LIC_PAID_IN_DEBIT_CODE,sum(LIC_MONTHLY_PREMIUM) from hrms_lic where emp_id="+bean.getEmpId()+" and LIC_PAID_IN_SALARY='Y' "+ 
				  "	and LIC_ISACTIVE='Y' group by LIC_PAID_IN_DEBIT_CODE";
 
			Object[][] debit = getSqlModel().getSingleResult(query1);
			 
		
			
			for(int i=0;i<debit.length;i++){
				String query2= "select debit_amt from hrms_emp_debit where debit_code="+String.valueOf(debit[i][0])+" and emp_id="+bean.getEmpId()+"";
				Object[][] debAmt = getSqlModel().getSingleResult(query2);
				logger.info("addApplication:-----111111111111111111111111-111-------------"+debAmt.length);
				int  amount = Integer.parseInt(String.valueOf(debAmt[0][0]));
				//String debit1=String.valueOf(debit[0][0]);
				logger.info("addApplication:-----111111111111111111111111-111-------------"+amount);
				int premium = Integer.parseInt(String.valueOf(debit[i][1]));
				logger.info("addApplication:-----22222222222222222222222222221-111-------------"+premium);
				if(debAmt.length!=0)
				{
					int abc = amount+premium;
					logger.info("addApplication:-----333333333333333333333333333333333321-111-------------"+abc);
				String query3= "update HRMS_EMP_DEBIT set DEBIT_AMT="+abc+" where debit_code="+String.valueOf(debit[i][0])+" and emp_id="+bean.getEmpId()+"  ";
				boolean xyz = getSqlModel().singleExecute(query3);
				logger.info(query3);
				logger.info("addApplication:-----4444444444444444444444444444444444-111-------------"+xyz);
				}
				/*else {
					String query4= "Insert into HRMS_EMP_DEBIT (DEBIT_CODE,EMP_ID,DEBIT_AMT) values ("+String.valueOf(debit[i][0])+","+bean.getEmpId()+","+premium+")";
					boolean def = getSqlModel().singleExecute(query4);
					logger.info("addApplication:-----4444444444444444444444444444444444-111-------------"+def);
				}*/
				
			 
			
		}
			
	
		}	
		
		 
		
		
		public String modLic(EmpLic bean){
			Object modObj[][]=new Object[1][12];
			
			modObj[0][0]=bean.getEmpId();
			modObj[0][1]=bean.getLicName();
			modObj[0][2]=bean.getLicPolicyNo();
			modObj[0][3]=bean.getInsuranceAmt();
			modObj[0][4]=bean.getLicPremiumAmt();
			modObj[0][5]=bean.getLicStDate();
			modObj[0][6]=bean.getLicEndDt();
			modObj[0][7]=bean.getTaxExempt();
			modObj[0][8]=bean.getPaidInSal();
			modObj[0][9]=bean.getLicActive();
			modObj[0][10]=bean.getDebitCode();
			modObj[0][11]=bean.getLicId();			
			
		//	return getSqlModel().singleExecute(getQuery(2),modObj);
			boolean result = getSqlModel().singleExecute(getQuery(2), modObj);
			if(result){
				return "Record updated Successfully";
			}else{
				return "Record cannot be updated ";
			}
		
			
		}
		
		public String checkNull(String result){
			if(result ==null ||result.equals("null")){
				return "";
			}
			else{
				return result;
			}
		}
		
		public void getLicRecord(EmpLic bean){
			try{
		
			Object [] addObj = new Object [1];
			addObj[0]=bean.getEmpId();
			Object[][] data = getSqlModel().getSingleResult(getQuery(4),addObj);
			
			ArrayList<Object> list=new ArrayList<Object>();
		if(data!=null && data.length>0){	
			bean.setFlag("true");
			
			for(int i=0;i<data.length;i++){
				EmpLic bean1=new EmpLic();
				bean1.setLicId(checkNull(String.valueOf(data[i][0])));
				bean1.setHLicName(checkNull(String.valueOf(data[i][1])));
				bean1.setHPolicyNo(checkNull(String.valueOf(data[i][2])));
				bean1.setHInsAMt(checkNull(String.valueOf(data[i][3])));
				bean1.setHPremAMt(checkNull(String.valueOf(data[i][4])));
				bean1.setHStartDate(checkNull(String.valueOf(data[i][5])));
				bean1.setHEndDate(checkNull(String.valueOf(data[i][6])));
				bean1.setHTaxEx(checkNull(String.valueOf(data[i][7])));
				bean1.setHDebitExemptCode(checkNull(String.valueOf(data[i][8])));
				bean1.setHDebitExempt(checkNull(String.valueOf(data[i][9])));
				bean1.setHDebitName(checkNull(String.valueOf(data[i][10])));
				bean1.setHDebitCode(checkNull(String.valueOf(data[i][11])));
				bean1.setStatusIt(checkNull(String.valueOf(data[i][12])));
				list.add(bean1);
			/*	bean1.setEmpId(checkNull(String.valueOf(data[i][1])));
				bean1.setEmpToken(checkNull(String.valueOf(data[i][2])));
				bean1.setEmpName(checkNull(String.valueOf(data[i][3])));
				bean1.setEmpCent(checkNull(String.valueOf(data[i][4])));
				bean1.setEmpRank(checkNull(String.valueOf(data[i][5])));
				bean1.setLicName(checkNull(String.valueOf(data[i][6])));
				bean1.setLicPolicyNo(checkNull(String.valueOf(data[i][7])));
				bean1.setInsuranceAmt(checkNull(String.valueOf(data[i][8])));
				bean1.setLicPremiumAmt(checkNull(String.valueOf(data[i][9])));
				bean1.setLicStDate(checkNull(String.valueOf(data[i][10])));
				bean1.setLicEndDt(checkNull(String.valueOf(data[i][11])));
				bean1.setTaxExempt(checkNull(String.valueOf(data[i][12])));
				bean1.setPaidInSal(checkNull(String.valueOf(data[i][13])));
				bean1.setLicActive(checkNull(String.valueOf(data[i][14])));
				bean1.setDebitName(checkNull(String.valueOf(data[i][15])));
				linscList.add(bean1);
				logger.info("********3:"+linscList.listIterator());
				*/
			}
			bean.setLicList(list);
	    	}	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
		
public  void getRecord(EmpLic bean){
			
			Object addObj[]=new Object[2];
			addObj[0]=bean.getParaId();
			addObj[1]=bean.getEmpId();
			logger.info("Value of paraid"+addObj[0] );
			logger.info("Value of empid"+addObj[1] );
			Object[][] data = getSqlModel().getSingleResult(getQuery(5),addObj);
			
			
				bean.setLicId(checkNull(String.valueOf(data[0][0])));
				logger.info("********2:"+String.valueOf(data[0][1]));
				bean.setEmpId(checkNull(String.valueOf(data[0][1])));
				bean.setEmpToken(checkNull(String.valueOf(data[0][2])));
				bean.setEmpName(checkNull(String.valueOf(data[0][3])));
				bean.setEmpCent(checkNull(String.valueOf(data[0][4])));
				bean.setEmpRank(checkNull(String.valueOf(data[0][5])));
				bean.setLicName(checkNull(String.valueOf(data[0][6])));
				bean.setLicPolicyNo(checkNull(String.valueOf(data[0][7])));
				bean.setInsuranceAmt(checkNull(String.valueOf(data[0][8])));
				bean.setLicPremiumAmt(checkNull(String.valueOf(data[0][9])));
				bean.setLicStDate(checkNull(String.valueOf(data[0][10])));
				bean.setLicEndDt(checkNull(String.valueOf(data[0][11])));
				bean.setTaxExempt(checkNull(String.valueOf(data[0][12])));
				bean.setPaidInSal(checkNull(String.valueOf(data[0][13])));
				
				bean.setDebitName(checkNull(String.valueOf(data[0][14])));
				
				bean.setLicActive(checkNull(String.valueOf(data[0][15])));
				bean.setDebitCode(checkNull(String.valueOf(data[0][16])));
			
		}


  public boolean delLicRecord(EmpLic bean){
	Object delObj[][]=new Object[1][1];
	
	delObj[0][0]=bean.getLicIdHead();
	return getSqlModel().singleExecute(getQuery(3),delObj);
	
}
  
  public void  getEmployeeDetails(String empId, EmpLic el)
	{
	Object[] beanObj = new Object[1];
	beanObj[0] =empId ;

   


	String query =" SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN, "
		+ "	(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) "
		+ "	,(HRMS_CENTER.CENTER_NAME),HRMS_RANK.RANK_NAME  "
		+ "	FROM HRMS_EMP_OFFC "
		+ " LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE "
		+ "	LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK " 
		+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
		+"  WHERE HRMS_EMP_OFFC.EMP_ID = ?";
	Object[][] values = getSqlModel().getSingleResult(query, beanObj);
	logger.info("addApplication:-------------------"+values.length);
	logger.info("addApplication:-------------------"+String.valueOf(beanObj[0]));

	el.setEmpId(checkNull(String.valueOf(values[0][0])));
	el.setEmpToken(checkNull(String.valueOf(values[0][1])));
	el.setEmpName(checkNull(String.valueOf(values[0][2])));
	el.setEmpCent(checkNull(String.valueOf(values[0][3])));
	el.setEmpRank(checkNull(String.valueOf(values[0][4])));


}
	
	
	public void  getEmpLic(String empId, EmpLic bean)
	{
			try{
		
			Object [] addObj = new Object [1];
			addObj[0]=empId;
			Object[][] data = getSqlModel().getSingleResult(getQuery(4),addObj);
			
			ArrayList<Object> list=new ArrayList<Object>();
		if(data!=null && data.length>0){	
			bean.setFlag("true");
			
			for(int i=0;i<data.length;i++){
				EmpLic bean1=new EmpLic();
				bean1.setLicId(checkNull(String.valueOf(data[i][0])));
				bean1.setHLicName(checkNull(String.valueOf(data[i][1])));
				bean1.setHPolicyNo(checkNull(String.valueOf(data[i][2])));
				bean1.setHInsAMt(checkNull(String.valueOf(data[i][3])));
				bean1.setHPremAMt(checkNull(String.valueOf(data[i][4])));
				bean1.setHStartDate(checkNull(String.valueOf(data[i][5])));
				bean1.setHEndDate(checkNull(String.valueOf(data[i][6])));
				bean1.setHTaxEx(checkNull(String.valueOf(data[i][7])));
				bean1.setHDebitExemptCode(checkNull(String.valueOf(data[i][8])));
				bean1.setHDebitExempt(checkNull(String.valueOf(data[i][9])));
				bean1.setHDebitName(checkNull(String.valueOf(data[i][10])));
				bean1.setHDebitCode(checkNull(String.valueOf(data[i][11])));
				bean1.setStatusIt(checkNull(String.valueOf(data[i][12])));
				list.add(bean1);
			
			}
			bean.setLicList(list);
	    	}	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public org.paradyne.bean.admin.srd.EmpLic getEl() {
		return el;
	}

	public void setEl(org.paradyne.bean.admin.srd.EmpLic el) {
		this.el = el;
	}

	public boolean insertPolicyDet(EmpLic bean){
		boolean result= false;
		Object[][] addObj=new Object[1][11];
		addObj[0][0]=bean.getLicName();
		addObj[0][1]=bean.getLicPolicyNo();
		addObj[0][2]=bean.getInsuranceAmt();
		addObj[0][3]=bean.getLicPremiumAmt();
		addObj[0][4]=bean.getLicStDate();
		addObj[0][5]=bean.getLicEndDt();
		addObj[0][6]=bean.getTaxExempt();
		addObj[0][7]=bean.getInvCode();
		addObj[0][8]=bean.getDebitCode();
		addObj[0][9]=bean.getEmpId();
		addObj[0][10]=bean.getLicStatus();
		
		result = getSqlModel().singleExecute(getQuery(1),addObj);
		return result;
	}
	public boolean updatePolicyDet(EmpLic bean){
		boolean result= false;
		Object[][] addObj=new Object[1][12];
		
		addObj[0][0]=bean.getLicName();
		addObj[0][1]=bean.getLicPolicyNo();
		addObj[0][2]=bean.getInsuranceAmt();
		addObj[0][3]=bean.getLicPremiumAmt();
		addObj[0][4]=bean.getLicStDate();
		addObj[0][5]=bean.getLicEndDt();
		addObj[0][6]=bean.getTaxExempt();
		addObj[0][7]=bean.getInvCode();
		addObj[0][8]=bean.getDebitCode();
		addObj[0][9]=bean.getEmpId();
		addObj[0][10]=bean.getLicStatus();
		addObj[0][11]=bean.getLicIdHead();
		
		result = getSqlModel().singleExecute(getQuery(8),addObj);
		return result;
	}
	public boolean chkEmpId(EmpLic bean){
		String query="SELECT EMP_ID FROM HRMS_LIC WHERE EMP_ID="+bean.getEmpId();
		
		 Object [][]data=getSqlModel().getSingleResult(query);
		
		 if(data!=null && data.length>0){
			return true;
		 }else
		 {
			return false;  
		 }
		
	   }
	
		
}

	

	
	
	
	


