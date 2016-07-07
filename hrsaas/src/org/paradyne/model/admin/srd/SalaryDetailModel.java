package org.paradyne.model.admin.srd;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.admin.srd.PersonnelDetail;
import org.paradyne.bean.admin.srd.SalaryDetail;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;

public class SalaryDetailModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(SalaryDetailModel.class);
	org.paradyne.bean.admin.srd.SalaryDetail salDetail = null;
	AuditTrail trial=null;

	/**
	 * Method to add Salary details of an employee.
	 * 
	 * @param bean
	 * @param request 
	 * @return boolean
	 */
	public boolean addSalDtl(SalaryDetail bean, HttpServletRequest request) {

		trial = new AuditTrail(bean.getUserEmpId());
		/** AUDIT TRIAL INITIALIZE	**/
		trial.initiate(context, session);
		Object addObj[][] = new Object[1][27];
		addObj[0][0] = checkNull(bean.getEmpID());// Empl Id
		addObj[0][1] = checkNull(bean.getNoGPF());// PF No.
		addObj[0][2] = checkNull(bean.getNoPAN());// Pan no
		addObj[0][3] = checkNull(bean.getRegularACCNO());// Regular/salary //
		// Account no
		addObj[0][4] = checkNull(bean.getMicrReguCode());// Salary Bank
		addObj[0][5] = checkNull(bean.getReimbursementACCNO());
		addObj[0][6] = checkNull(bean.getReimbrBankCode());
		addObj[0][7] = checkNull(bean.getPensionable());
		addObj[0][8] =checkNull(bean.getPayMode());
		addObj[0][9] = checkNull(bean.getEsciNumber());
		addObj[0][10] = checkNull(bean.getGratuityId());
		addObj[0][11] = checkNull(bean.getPensionACCNO());
		addObj[0][12] = checkNull(bean.getPensionBankCode()	);			
		addObj[0][13] = checkNull(bean.getAccountingCategoryCode());
		addObj[0][14] = checkNull(bean.getCostCenterCode());
		addObj[0][15] = checkNull(bean.getSubCostCenterCode());
		addObj[0][16] = getCheckBoxValue(bean.getSEPFflag());
		addObj[0][18] = getCheckBoxValue(bean.getSGPFflag());
		addObj[0][17] = getCheckBoxValue(bean.getSVPFflag());
		addObj[0][19] = getCheckBoxValue(bean.getSPFTflag());
		addObj[0][20] = checkNull((bean.getCustomerRefNo().trim()));
		if(bean.getSOTflag().equals("true")) // Overtime
			addObj[0][21] = "Y";
		else 
			addObj[0][21] = "N";
		
		addObj[0][22] = bean.getPfJoinDate(); // PF Join Date
		
		if (bean.getActualAmtEmpPfFlag().equals("true"))// Calculate Employee PF
			addObj[0][23] = "A";
		if (bean.getQualAmtEmpPfFlag().equals("true"))// Calculate Employee PF
			addObj[0][23] = "Q";
		if (bean.getActualAmtCompPfFlag().equals("true"))// Calculate Company PF
			addObj[0][24] = "A";
		if (bean.getQualAmtCompPfFlag().equals("true"))// Calculate Company PF
			addObj[0][24] = "Q";
		if (bean.getActualAmtPensionPfFlag().equals("true"))// Calculate Pension PF
			addObj[0][25] = "A";
		if (bean.getQualAmtPensionPfFlag().equals("true"))// Calculate Pension PF
			addObj[0][25] = "Q";
		if(bean.getOverridePfCal().equals("true")) // Override System PF Calculation Parameter
			addObj[0][26] = "Y";
		else 
			addObj[0][26] = "N";
		boolean res= getSqlModel().singleExecute(getQuery(1), addObj);
		if(res)
		{
			trial.setParameters("HRMS_SALARY_MISC", new String[] { "EMP_ID" },
					new String[] { bean.getEmpID() }, bean.getEmpID());
			/**	AUDIT TRAIL	EXECUTION **/
			trial.executeADDTrail(request);
		}
		return res;
	}
	/**
	 * Method to modify the Salary details of an employee.
	 * 
	 * @param bean
	 * @return boolean
	 */

public boolean modSalDtl(SalaryDetail bean,HttpServletRequest request) {

		boolean result=false;
		
		try {
			Object addObj[][] = new Object[1][34];
			logger.info("===USER LOGIN CODE====" + bean.getUserEmpId());
			trial = new AuditTrail(bean.getUserEmpId());
			/** AUDIT TRIAL INITIALIZE	**/
			trial.initiate(context, session);
			trial.setParameters("HRMS_SALARY_MISC", new String[] { "EMP_ID" },
					new String[] { bean.getEmpID() }, bean.getEmpID());
			trial.beginTrail();
			addObj[0][0] = bean.getPayScaleCode();
			addObj[0][1] = bean.getNoGPF().trim();
			addObj[0][2] = bean.getNoPAN().trim();
			addObj[0][3] = bean.getNoCGHS().trim();
			addObj[0][4] = bean.getMicrReguCode();
			addObj[0][5] = bean.getPensionMicrcode();
			addObj[0][6] = bean.getRegularACCNO().trim();
			addObj[0][7] = bean.getPensionACCNO().trim();
			addObj[0][8] = bean.getSavingMICRcode();
			addObj[0][9] = bean.getSavingACCNO().trim();
			addObj[0][10] = bean.getPensionable().trim();
			addObj[0][11] = bean.getPayMode().trim();
			addObj[0][12] = bean.getNoDCMAF().trim();
			addObj[0][13] = bean.getEsciNumber().trim();
			addObj[0][14] = bean.getReimbursementACCNO().trim();
			addObj[0][15] = bean.getReimbrBankCode().trim();
			addObj[0][16] = checkNull(bean.getGratuityId());
			addObj[0][17] = checkNull(bean.getPensionACCNO());
			addObj[0][18] = checkNull(bean.getPensionBankCode()	);			
			addObj[0][19] = checkNull(bean.getAccountingCategoryCode());
			addObj[0][20] = checkNull(bean.getCostCenterCode());
			addObj[0][21] = checkNull(bean.getSubCostCenterCode());
			addObj[0][22] = getCheckBoxValue(bean.getSEPFflag());
			addObj[0][24] = getCheckBoxValue(bean.getSGPFflag());
			addObj[0][23] = getCheckBoxValue(bean.getSVPFflag());
			addObj[0][25] = getCheckBoxValue(bean.getSPFTflag());
			addObj[0][26] = checkNull((bean.getCustomerRefNo().trim()));
			if(bean.getSOTflag().equals("true")) // Overtime
				addObj[0][27] = "Y";
			else 
				addObj[0][27] = "N";
			
			addObj[0][28] = bean.getPfJoinDate(); // PF Join Date
			
			
			if (bean.getActualAmtEmpPfFlag().equals("true"))// Calculate Employee PF
				addObj[0][29] = "A";
			
			if (bean.getQualAmtEmpPfFlag().equals("true"))
				addObj[0][29] = "Q";
			
			if (bean.getActualAmtCompPfFlag().equals("true"))// Calculate Company PF
				addObj[0][30] = "A";
			
			if (bean.getQualAmtCompPfFlag().equals("true"))
				addObj[0][30] = "Q";
			
			if (bean.getActualAmtPensionPfFlag().equals("true"))// Calculate Pension PF
				addObj[0][31] = "A";
			
			if (bean.getQualAmtPensionPfFlag().equals("true"))
				addObj[0][31] = "Q";
			
			if(bean.getOverridePfCal().equals("true")) // Override System PF Calculation Parameter
				addObj[0][32] = "Y";
			else 
				addObj[0][32] = "N";
			
			
			addObj[0][33] = bean.getEmpID();
			result = getSqlModel().singleExecute(getQuery(2), addObj);
			/**	AUDIT TRAIL	EXECUTION **/
			trial.executeMODTrail(request);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in modSalDtl---------------"+e);
		}
		return result;
	}

	private String getCheckBoxValue(String sFieldValue) {
		String sResult = "";
		try {
			if ((sFieldValue != null) && (sFieldValue.equals("true")))
				sResult = "Y";
			else
				sResult = "N";
			
		} catch (Exception e) {
			sResult = "N";
		}
		return sResult;
	}
	
	private String getCheckBoxValue1(String sFieldValue) {
		String sResult = "";
		try {
			if ((sFieldValue != null) && (sFieldValue.equals("Y")))
				sResult = "true";
			else
				sResult = "false";
			
		} catch (Exception e) {
			sResult = "false";
		}
		return sResult;
	}
	
	
	/**
	 * Method to delete the Salary details of an employee.
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean delSalDtl(SalaryDetail bean,HttpServletRequest request) {
		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getEmpID();
		
		trial = new AuditTrail(bean.getUserEmpId());
		/** AUDIT TRIAL INITIALIZE	**/
		trial.initiate(context, session);
		trial.setParameters("HRMS_SALARY_MISC", new String[] { "EMP_ID" },
				new String[] {bean.getEmpID() }, bean.getEmpID());
		
		
		/**	AUDIT TRAIL	EXECUTION **/
		trial.executeDELTrail(request);
		return getSqlModel().singleExecute(getQuery(4), addObj);
	}

	/**
	 * Method to view the Salary details of an employee.
	 * 
	 * @param salDetail
	 * @return Object
	 */
	public SalaryDetail getEditRecord(SalaryDetail salDetail) {
		Object addObj[] = new Object[1];
		//addObj[0] = salDetail.getEmpID();
		addObj[0] = salDetail.getEmpID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(3), addObj);
		if(data!=null && data.length > 0)
		{
			if (String.valueOf(data[0][0]).equals("null")) {
				salDetail.setPayScaleCode("");
			}// end of if
			else {
				salDetail.setPayScaleCode(String.valueOf(data[0][0]));
			}// end of else

			salDetail.setPayScaleName(String.valueOf(data[0][1]).trim());
			salDetail.setNoGPF(String.valueOf(data[0][2]).trim());
			salDetail.setNoPAN(String.valueOf(data[0][3]).trim());
			salDetail.setNoCGHS(String.valueOf(data[0][4]).trim());
			if (String.valueOf(data[0][5]).equals("0")) {

				salDetail.setMicrReguCode("");
			}// end of 0f
			else {
				salDetail.setMicrReguCode(String.valueOf(data[0][5]).trim());
			}// end of else
			if (String.valueOf(data[0][6]).equals("0")) {
				salDetail.setPensionMicrcode("");
			}// end of 0f
			else {
				salDetail.setPensionMicrcode(String.valueOf(data[0][6]));
			}// end of else
			salDetail.setRegularACCNO(String.valueOf(data[0][7]).trim());
			salDetail.setPensionACCNO(String.valueOf(data[0][8]));
			if (String.valueOf(data[0][9]).equals("0")) {
				salDetail.setSavingMICRcode("");
			}// end of 0f
			else {
				salDetail.setSavingMICRcode(String.valueOf(data[0][9]));
			}// end of else
			salDetail.setSavingACCNO(String.valueOf(data[0][10]));
			salDetail.setPensionable(String.valueOf(data[0][11]));
			salDetail.setPayMode(String.valueOf(data[0][12]));
			salDetail.setNoDCMAF(String.valueOf(data[0][13]).trim());
			/*1**/salDetail.setRegularMICR(String.valueOf(data[0][14]).trim());
			if (String.valueOf(data[0][15]).equals("null")) {
				salDetail.setEsciNumber("");
			}// end of 0f
			else
				salDetail.setEsciNumber(String.valueOf(data[0][15]).trim());

			salDetail.setReimbursementACCNO(checkNull(String.valueOf(
					data[0][16]).trim()));
			salDetail.setReimbrBankCode(checkNull(String.valueOf(data[0][17])
					.trim()));
			salDetail.setReimbrBank(checkNull(String.valueOf(data[0][18])
					.trim()));

			salDetail.setGratuityId(checkNull(String.valueOf(data[0][19]).trim()));
			salDetail.setPensionACCNO(checkNull(String.valueOf(data[0][20]).trim()));
			salDetail.setPensionBankCode(checkNull(String.valueOf(data[0][21]).trim()));
			salDetail.setPensionBank(checkNull(String.valueOf(data[0][22]).trim()));
			salDetail.setAccountingCategoryCode(checkNull(String.valueOf(data[0][23]).trim()));
			salDetail.setAccountingCategory(checkNull(String.valueOf(data[0][24]).trim()));
			salDetail.setCostCenterCode(checkNull(String.valueOf(data[0][25]).trim()));
			salDetail.setCostCenter(checkNull(String.valueOf(data[0][26]).trim()));
			if(!salDetail.getCostCenterCode().equals("")){
				String query1=" SELECT HRMS_SALARY_MISC.SUB_COST_CENTER_ID,SUB_COST_CENTER_NAME FROM HRMS_SALARY_MISC "
							  + " INNER JOIN HRMS_SUB_COST_CENTER ON  (HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID ) "
							  + " WHERE EMP_ID="+salDetail.getEmpID()
							  + " AND HRMS_SUB_COST_CENTER.COST_CENTER_ID="+salDetail.getCostCenterCode();
				Object[][]subData=getSqlModel().getSingleResult(query1);
				if(subData !=null && subData.length>0){
					salDetail.setSubCostCenterCode(checkNull(String.valueOf(subData[0][0]).trim()));
					salDetail.setSubCostCenter(checkNull(String.valueOf(subData[0][1]).trim()));
				}
			}else{
				salDetail.setSubCostCenter("");
				salDetail.setSubCostCenterCode("");
			}
			salDetail.setSEPFflag(getCheckBoxValue1(String.valueOf(data[0][29]).trim()));			
			salDetail.setSVPFflag(getCheckBoxValue1(String.valueOf(data[0][30]).trim()));
			salDetail.setSGPFflag(getCheckBoxValue1(String.valueOf(data[0][31]).trim()));
			salDetail.setSPFTflag(getCheckBoxValue1(String.valueOf(data[0][32]).trim()));
			
		
			
			salDetail.setCustomerRefNo(checkNull(String.valueOf(data[0][33]).trim()));
			
			if (String.valueOf(data[0][34]).equals("Y")) {
				salDetail.setSOTflag("true");
			} else {
				salDetail.setSOTflag("false");
			}
			
			salDetail.setPfJoinDate(checkNull(String.valueOf(data[0][35])));
			
			if (String.valueOf(data[0][36]).equals("A")) {
				salDetail.setActualAmtEmpPfFlag("true");
			} else{
				salDetail.setActualAmtEmpPfFlag("false");
			}
				
			if (String.valueOf(data[0][36]).equals("Q")){
				salDetail.setQualAmtEmpPfFlag("true");
			} else{
				salDetail.setQualAmtEmpPfFlag("false");
			}
			
			if (String.valueOf(data[0][37]).equals("A")) {
				salDetail.setActualAmtCompPfFlag("true");
			} else {
				salDetail.setActualAmtCompPfFlag("false");
			}
				
			if (String.valueOf(data[0][37]).equals("Q")){
				salDetail.setQualAmtCompPfFlag("true");
			} else {
				salDetail.setQualAmtCompPfFlag("false");
			}
			if (String.valueOf(data[0][38]).equals("A")) {
				salDetail.setActualAmtPensionPfFlag("true");
			} else {
				salDetail.setActualAmtPensionPfFlag("false");
			}
				
			if (String.valueOf(data[0][38]).equals("Q")){
				salDetail.setQualAmtPensionPfFlag("true");
			} else {
				salDetail.setQualAmtPensionPfFlag("false");
			}
			
			if (String.valueOf(data[0][39]).equals("Y")) {
				salDetail.setOverridePfCal("true");
			} else {
				salDetail.setOverridePfCal("false");
			}
			
			salDetail.setGrade(checkNull(String.valueOf(data[0][40]).trim()));
			salDetail.setJoiningDate(checkNull(String.valueOf(data[0][41]).trim()));
			salDetail.setDeptName(checkNull(String.valueOf(data[0][42]).trim()));		
		
	}
		return salDetail;
	}

	/**
	 * Method to view the Salary details of an employee.
	 * 
	 * @param salDetail
	 * @return Object
	 */
	public SalaryDetail getRecord(SalaryDetail salDetail) {
		Object addObj[] = new Object[1];
		//addObj[0] = salDetail.getEmpID();
		addObj[0] = salDetail.getEmpID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(3), addObj);
		if(data!=null && data.length >0){//if data
			if (String.valueOf(data[0][0]).equals("null")) {
				salDetail.setPayScaleCode("");
			}// end of if
			else {
				salDetail.setPayScaleCode(String.valueOf(data[0][0]));
			}// end of else

			salDetail.setPayScaleName(String.valueOf(data[0][1]).trim());
			salDetail.setNoGPF(String.valueOf(data[0][2]).trim());
			salDetail.setNoPAN(String.valueOf(data[0][3]).trim());
			salDetail.setNoCGHS(String.valueOf(data[0][4]).trim());
			if (String.valueOf(data[0][5]).equals("0")) {

				salDetail.setMicrReguCode("");
			}// end of 0f
			else {
				salDetail.setMicrReguCode(String.valueOf(data[0][5]).trim());
			}// end of else
			if (String.valueOf(data[0][6]).equals("0")) {
				salDetail.setPensionMicrcode("");
			}// end of 0f
			else {
				salDetail.setPensionMicrcode(String.valueOf(data[0][6]));
			}// end of else
			salDetail.setRegularACCNO(String.valueOf(data[0][7]).trim());
			salDetail.setPensionACCNO(String.valueOf(data[0][8]));
			if (String.valueOf(data[0][9]).equals("0")) {
				salDetail.setSavingMICRcode("");
			}// end of 0f
			else {
				salDetail.setSavingMICRcode(String.valueOf(data[0][9]));
			}// end of else
			salDetail.setSavingACCNO(String.valueOf(data[0][10]));
			if(String.valueOf(data[0][11]).equals("Y")){
				salDetail.setPensionable("Yes");
			}else if(String.valueOf(data[0][11]).equals("N")){
				salDetail.setPensionable("No");
			}else{
				salDetail.setPensionable("");
			}
			if(String.valueOf(data[0][12]).equals("C")){
				salDetail.setPayMode("Cash");
			}else if(String.valueOf(data[0][12]).equals("H")){
				salDetail.setPayMode("Cheque");
			}else if(String.valueOf(data[0][12]).equals("T")){
				salDetail.setPayMode("Transfer");
			}else{
				salDetail.setPayMode("");
			}
			salDetail.setNoDCMAF(String.valueOf(data[0][13]).trim());
			/*1**/salDetail.setRegularMICR(String.valueOf(data[0][14]).trim());
			if (String.valueOf(data[0][15]).equals("null")) {
				salDetail.setEsciNumber("");
			}// end of 0f
			else
				salDetail.setEsciNumber(String.valueOf(data[0][15]).trim());

			salDetail.setReimbursementACCNO(checkNull(String.valueOf(
					data[0][16]).trim()));
			salDetail.setReimbrBankCode(checkNull(String.valueOf(data[0][17])
					.trim()));
			salDetail.setReimbrBank(checkNull(String.valueOf(data[0][18])
					.trim()));

			salDetail.setGratuityId(checkNull(String.valueOf(data[0][19]).trim()));
			salDetail.setPensionACCNO(checkNull(String.valueOf(data[0][20]).trim()));
			salDetail.setPensionBankCode(checkNull(String.valueOf(data[0][21]).trim()));
			salDetail.setPensionBank(checkNull(String.valueOf(data[0][22]).trim()));
			salDetail.setAccountingCategoryCode(checkNull(String.valueOf(data[0][23]).trim()));
			salDetail.setAccountingCategory(checkNull(String.valueOf(data[0][24]).trim()));
			salDetail.setCostCenterCode(checkNull(String.valueOf(data[0][25]).trim()));
			salDetail.setCostCenter(checkNull(String.valueOf(data[0][26]).trim()));
			if(!salDetail.getCostCenterCode().equals("")){
				String query1=" SELECT HRMS_SALARY_MISC.SUB_COST_CENTER_ID,SUB_COST_CENTER_NAME FROM HRMS_SALARY_MISC "
							  + " INNER JOIN HRMS_SUB_COST_CENTER ON  (HRMS_SUB_COST_CENTER.SUB_COST_CENTER_ID=HRMS_SALARY_MISC.SUB_COST_CENTER_ID ) "
							  + " WHERE EMP_ID="+salDetail.getEmpID()
							  + " AND HRMS_SUB_COST_CENTER.COST_CENTER_ID="+salDetail.getCostCenterCode();
				Object[][]subData=getSqlModel().getSingleResult(query1);
				if(subData !=null && subData.length>0){
					salDetail.setSubCostCenterCode(checkNull(String.valueOf(subData[0][0]).trim()));
					salDetail.setSubCostCenter(checkNull(String.valueOf(subData[0][1]).trim()));
				}
			}else{
				salDetail.setSubCostCenter("");
				salDetail.setSubCostCenterCode("");
			}
			salDetail.setSEPFflag(getCheckBoxValue1(String.valueOf(data[0][29]).trim()));			
			salDetail.setSVPFflag(getCheckBoxValue1(String.valueOf(data[0][30]).trim()));
			salDetail.setSGPFflag(getCheckBoxValue1(String.valueOf(data[0][31]).trim()));
			salDetail.setSPFTflag(getCheckBoxValue1(String.valueOf(data[0][32]).trim()));
			
		
			
			salDetail.setCustomerRefNo(checkNull(String.valueOf(data[0][33]).trim()));
			
			if (String.valueOf(data[0][34]).equals("Y")) {
				salDetail.setSOTflag("true");
			} else {
				salDetail.setSOTflag("false");
			}
			
			salDetail.setPfJoinDate(checkNull(String.valueOf(data[0][35])));
			
			if (String.valueOf(data[0][36]).equals("A")) {
				salDetail.setActualAmtEmpPfFlag("true");
			} else{
				salDetail.setActualAmtEmpPfFlag("false");
			}
				
			if (String.valueOf(data[0][36]).equals("Q")){
				salDetail.setQualAmtEmpPfFlag("true");
			} else{
				salDetail.setQualAmtEmpPfFlag("false");
			}
			
			if (String.valueOf(data[0][37]).equals("A")) {
				salDetail.setActualAmtCompPfFlag("true");
			} else {
				salDetail.setActualAmtCompPfFlag("false");
			}
				
			if (String.valueOf(data[0][37]).equals("Q")){
				salDetail.setQualAmtCompPfFlag("true");
			} else {
				salDetail.setQualAmtCompPfFlag("false");
			}
			if (String.valueOf(data[0][38]).equals("A")) {
				salDetail.setActualAmtPensionPfFlag("true");
			} else {
				salDetail.setActualAmtPensionPfFlag("false");
			}
				
			if (String.valueOf(data[0][38]).equals("Q")){
				salDetail.setQualAmtPensionPfFlag("true");
			} else {
				salDetail.setQualAmtPensionPfFlag("false");
			}
			
			if (String.valueOf(data[0][39]).equals("Y")) {
				salDetail.setOverridePfCal("true");
			} else {
				salDetail.setOverridePfCal("false");
			}
			
			salDetail.setGrade(checkNull(String.valueOf(data[0][40]).trim()));
			salDetail.setJoiningDate(checkNull(String.valueOf(data[0][41]).trim()));
			salDetail.setDeptName(checkNull(String.valueOf(data[0][42]).trim()));		
		}//end if data
		return salDetail;
	}


	/**
	 * Method to check the data is saved or not.
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean checkID(SalaryDetail bean) {
		boolean flag;
		Object addObj[] = new Object[1];
		addObj[0] = bean.getEmpID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(5), addObj);
		if (data.length == 0) {
			flag = false;
		}// end of if
		else {
			flag = true;
		}// end of else
		return flag;
	}

	/**
	 * Method to replace the null with a space.
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}
	
	public void getPfApplTypes(SalaryDetail bean,HttpServletRequest request){
		
		Object pfFlags[][]=getSqlModel().getSingleResult("SELECT NVL(CONF_EPF,'N'), NVL(CONF_GPF,'N'), NVL(CONF_VPF,'N'), NVL(CONF_PFTRUST,'N') FROM HRMS_SALARY_CONF");
		
		bean.setOEPFflag(getCheckBoxValue1(String.valueOf(pfFlags[0][0])));
		bean.setOGPFflag(getCheckBoxValue1(String.valueOf(pfFlags[0][1])));
		bean.setOVPFflag(getCheckBoxValue1(String.valueOf(pfFlags[0][2])));
		bean.setOPFTflag(getCheckBoxValue1(String.valueOf(pfFlags[0][3])));
		
	}
	
public void getImage(SalaryDetail salDetail) {
		
		try {
			String query="select EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="+salDetail.getEmpID();
			
			Object[][] myPics = getSqlModel().getSingleResult(query);
				
			salDetail.setUploadFileName( String.valueOf(myPics[0][0]));	
			salDetail.setProfileEmpName( String.valueOf(myPics[0][1])+" "+String.valueOf(myPics[0][2])+" "+String.valueOf(myPics[0][3]));
			
			System.out.println("persDetail.setUploadFileName( String.valueOf(myPics[0][0]))----"+salDetail.getUploadFileName());
		
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}