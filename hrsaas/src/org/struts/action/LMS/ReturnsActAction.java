package org.struts.action.LMS;

import java.util.LinkedHashMap;
import org.paradyne.bean.LMS.ActsChildLabour;
import org.paradyne.bean.LMS.ActsMaternityBenefits;
import org.paradyne.bean.LMS.ActsPaymentOfBonus;
import org.paradyne.bean.LMS.EqualRemuneration;
import org.paradyne.bean.LMS.FactoryAnnualReturn;
import org.paradyne.bean.LMS.FactoryIndustrialRelations;
import org.paradyne.bean.LMS.FactoryInspections;
import org.paradyne.bean.LMS.FactoryOtherDetails;
import org.paradyne.bean.LMS.FactorySafetyHealth;
import org.paradyne.bean.LMS.FactoryWagesBenefits;
import org.paradyne.bean.LMS.FactoryWelfareFacilities;
import org.paradyne.bean.LMS.GeneralInfo;
import org.paradyne.bean.LMS.GratuityRules;
import org.paradyne.bean.LMS.MinimumWages;
import org.paradyne.bean.LMS.RentAllowance;
import org.paradyne.bean.LMS.ReturnActBean;
import org.paradyne.bean.LMS.WorkForceInfo;
import org.paradyne.model.LMS.MinimumWagesActModel;
import org.paradyne.model.LMS.ReturnsActModel;
import org.struts.lib.ParaActionSupport;

public class ReturnsActAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReturnsActAction.class);
	
	ReturnActBean retactBean;
	MinimumWages minimumWages;
	//ADDED BY MANISH
	GratuityRules gratuityBean;
	RentAllowance rentAllowanceBean;
	EqualRemuneration equalRemunerationBean;
	//ADDED BY GANESH
	ActsPaymentOfBonus actsPaymentOfBonus; 
	ActsChildLabour actsChildLabour;
	ActsMaternityBenefits actsMaternityBenefits;
	GeneralInfo generalInfo;
	WorkForceInfo workForceInfo;
	//ADDED BY PRASHANT
	FactoryInspections factoryInspectionsBean;
	FactorySafetyHealth factorySafetyHealthBean;
	FactoryWelfareFacilities factoryWelfareFacilitiesBean;
	FactoryIndustrialRelations factoryIndustrialRelationsBean;
	FactoryWagesBenefits factoryWagesBenefitsBean;
	FactoryAnnualReturn factoryAnnualReturnBean;
	FactoryOtherDetails factoryOtherDetailsBean;

	public void prepare_local() throws Exception {
		retactBean = new ReturnActBean();
		retactBean.setMenuCode(1141);
	}

	public Object getModel() {
		return retactBean;
	}

	public ReturnActBean getRetactBean() {
		return retactBean;
	}

	public void setRetactBean(ReturnActBean retactBean) {
		this.retactBean = retactBean;
	}

	//===============METHODS ADDED BY REEBA BEGINS=============//
	
	public void setGeneralInfoBlock(){
		MinimumWagesActModel model = new MinimumWagesActModel();
		model.initiate(context, session);
		String orgId ="1";
		generalInfo = model.getLabourGeneralInfo(orgId);
		model.terminate();
	}
	
	public void setWorkforceInfoBlock(){
		MinimumWagesActModel model = new MinimumWagesActModel();
		model.initiate(context, session);
		String orgId ="1";
		workForceInfo = model.getLabourWorkforceInfo(orgId);
		model.terminate();
	}
	
	/** This input function is get called for displaying Onload List */
	public String callReturnList() {
		String status = request.getParameter("status");
		if(status==null){
			status="P";
		}
		retactBean.setOrgFlag(true);
		ReturnsActModel model = new ReturnsActModel();
		model.initiate(context, session);
		model.setReturns(retactBean, status);
		model.terminate();
		//getNavigationPanel(1);
		return INPUT;
	}

	public String getAnnualReturns() throws Exception {
		String status = request.getParameter("status");
		System.out.println("status    " + status);
		ReturnsActModel model = new ReturnsActModel();
		model.initiate(context, session);
		if (status.equals("C")) {
			retactBean.setListType("completed");
		} else if (status.equals("P")) {
			model.setReturns(retactBean, status);
		}
		model.terminate();
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String generateReturn() throws Exception {
		ReturnsActModel model = new ReturnsActModel();
		model.initiate(context, session);
		//String returnID = request.getParameter("returnID");
		String frequency = retactBean.getFrequency();
		//model.generateReturnID(retactBean, returnID);
		LinkedHashMap<String, String> actMap = model.setReturnActs(frequency);
		retactBean.setActMap(actMap);
		model.terminate();
		String pageToShow = "";
		if (frequency.equals("A"))
			pageToShow = retactBean.getPageToShow();
		else
			pageToShow = "monthlyActs";
		getNavigationPanel(1);
		try {
			setGeneralInfoBlock();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		try {
			setWorkforceInfoBlock();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return pageToShow;
	}
	
	public String f9division() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
		if(retactBean.getUserProfileDivision() !=null && retactBean.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+retactBean.getUserProfileDivision() +")"; 
			query+= " ORDER BY  UPPER(DIV_NAME) ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division") };

		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "orgName", "orgId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ReturnAct_callReturnList.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/**
	 * METHOD TO SET GRATUITY ACT
	 */
	public void setMinimumWagesAct(){
		
		MinimumWagesActModel model = new MinimumWagesActModel();
		model.initiate(context, session);
		String orgId =retactBean.getOrgId();
		logger.info("Org ID......"+orgId);
		String month = "1";
		String year = "2011";
		//	minwageBean.setMonth(month);
		//minwageBean.setYear(year);
		minimumWages = model.getLabourWageRecords(orgId, month, year, request);
		model.terminate();
	}
	
	//===============METHODS ADDED BY REEBA ENDS=============//

	public String next() {
		try {
			ReturnsActModel model = new ReturnsActModel();
			model.initiate(context, session);
			String frequency = retactBean.getFrequency();
			LinkedHashMap<String, String> actMap = model.setReturnActs(frequency);
			retactBean.setActMap(actMap);
			model.terminate();
			if(frequency.equals("A")){
				setGratuityAct();
				setHRA();
				setAnualFactorySafety_Health();
				setWelfFareFacility();
				setMaternityBenefits();
			}else if(frequency.equals("M")){
				setMinimumWagesAct();
			}
			String pageToShow = retactBean.getPageToShow();
			if(pageToShow.equals("annualfactory")||pageToShow.equals("minimumWages")){
				getNavigationPanel(3);
			}else{
				getNavigationPanel(2);
			}
			return pageToShow;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String cancel() {
		try {
			ReturnsActModel model = new ReturnsActModel();
			model.initiate(context, session);
			String frequency = retactBean.getFrequency();
			LinkedHashMap<String, String> actMap = model.setReturnActs(frequency);
			retactBean.setActMap(actMap);
			model.terminate();
			if(frequency.equals("A")){
				setGratuityAct();
				setHRA();
				setAnualFactorySafety_Health();
				setWelfFareFacility();
				setMaternityBenefits();
			}else if(frequency.equals("M")){
				setMinimumWagesAct();
			}
			try {
				setGeneralInfoBlock();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			try {
				setWorkforceInfoBlock();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			String previousPage = retactBean.getPreviousPage();
			if(previousPage.equals("input")){
				
				callReturnList();
			}
			getNavigationPanel(1);
			return previousPage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * METHODS ADDED BY SHASHIKANT :START
	 */
	
	/**
	 * METHOD TO SET GRATUITY ACT
	 */
	public void setGratuityAct(){
		
		ReturnsActModel model = new ReturnsActModel();
		model.initiate(context, session);
		String orgId =retactBean.getOrgId();
		logger.info("Org ID......"+orgId);
		gratuityBean = model.setGratuityAct(orgId,"01-01-2010","31-12-2010");
		model.terminate();
	}
	/**
	 * METHOD TO SET HRA ACT
	 */
	public void setHRA(){
		ReturnsActModel model = new ReturnsActModel();
		model.initiate(context, session);
		String orgId =retactBean.getOrgId();
		logger.info("Org ID......"+orgId);
		rentAllowanceBean = model.setHRA(orgId,"01-01-2010","31-08-2010");
		model.terminate();
		//setHRADisputes();
	}
	/**
	 * METHOD TO SET setHRADisputes	
	 * 
	 *  */
	public void setHRADisputes(){
		ReturnsActModel model = new ReturnsActModel();
		model.initiate(context, session);
		String orgId =retactBean.getOrgId();
		logger.info("Org ID......"+orgId);
		rentAllowanceBean = model.setHRADisputes(orgId,"01-01-2010","31-12-2011",rentAllowanceBean);
		model.terminate();
	}
	/**
	 * METHOD TO SET ANUAL FACTORY RETURN & SAFETY & HEAKTH
	 * 
	 *  */
	public void setAnualFactorySafety_Health(){
		ReturnsActModel model = new ReturnsActModel();
		model.initiate(context, session);
		String orgId =retactBean.getOrgId();
		logger.info("Org ID......"+orgId);
		factorySafetyHealthBean = model.setAnualFactorySafety_Health(orgId,"01-01-2010","31-12-2011",rentAllowanceBean);
		model.terminate();
	}
	/**
	 * METHOD TO SET WELF FARE FACILITY
	 */
	public void setWelfFareFacility(){
		ReturnsActModel model = new ReturnsActModel();
		model.initiate(context, session);
		String orgId =retactBean.getOrgId();
		logger.info("Org ID......"+orgId);
		factoryWelfareFacilitiesBean = model.setWelfFareFacility(factoryWelfareFacilitiesBean);
		model.terminate();
	}
	/**
	 * METHOD TO SET MATERNITY
	 */
	public void setMaternityBenefits(){
		ReturnsActModel model = new ReturnsActModel();
		model.initiate(context, session);
		String orgId =retactBean.getOrgId();
		logger.info("Org ID......"+orgId);
		actsMaternityBenefits = model.setMaternityBenefits(orgId,"","");
		model.terminate();
	}
	
	/*
	 * METHODS ADDED BY SHASHIKANT: END
	 */
	

	//===============METHODS ADDED BY MANISH BEGINS=============//
	// Gratuity insert and update records Begin
	public String gratuitySaveAndNextRecords() {
		try {
			ReturnsActModel model = new ReturnsActModel();
			model.initiate(context, session);
			boolean result;

			if (gratuityBean.getGratuityReturnID().equals("")) {
				result = model.gratuityInsertData(gratuityBean);
				if (result) {
					addActionMessage(getMessage("save"));
				} else {
					addActionMessage("Error occur during saving the gratuity records.");
				}
			} else {
				result = model.gratuityUpdateData(gratuityBean);
				if (result) {
					addActionMessage(getMessage("update"));
				} else {
					addActionMessage("Error occur during updating the gratuity records.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		String pageToShow = retactBean.getPageToShow();
		getNavigationPanel(2);
		return pageToShow;
	} // Gratuity insert and update records End

	// HRA insert and update records Begin
	public String rentAllowanceSaveAndNextRecords() {
		try {
			ReturnsActModel model = new ReturnsActModel();
			model.initiate(context, session);
			boolean result;

			if (rentAllowanceBean.getReturnID().equals("")) {
				result = model.hraInsertData(rentAllowanceBean);
				if (result) {
					addActionMessage(getMessage("save"));
				} else {
					addActionMessage("Error occur during saving the HRA records.");
				}
			} else {
				result = model.hraUpdateData(rentAllowanceBean);
				if (result) {
					addActionMessage(getMessage("update"));
				} else {
					addActionMessage("Error occur during updating the HRA records.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		String pageToShow = retactBean.getPageToShow();
		getNavigationPanel(2);
		return pageToShow;
	}

	// HRA insert and update records End

	// Equal-Rmuneration insert and update records Begin
	public String equalRemunerationSaveAndNextRecords() {
		ReturnsActModel model = new ReturnsActModel();
		model.initiate(context, session);
		boolean result;
		try {
			if (equalRemunerationBean.getEqualRemunerationReturnID().equals("")) {
				result = model
						.equalRemunerationInsertRecords(equalRemunerationBean);
				if (result) {
					addActionMessage(getMessage("save"));
				} else {
					addActionMessage("Error occur in saving Equal Emuneration Records");
				}
			} else {
				result = model
						.equalRemunerationUpdateRecords(equalRemunerationBean);
				if (result) {
					addActionMessage(getMessage("update"));
				} else {
					addActionMessage("Error occur in updating Equal Emuneration Records.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String pageToShow = retactBean.getPageToShow();
		getNavigationPanel(2);
		return pageToShow;
	}

	// Equal-Rmuneration insert and update records End
	//===============METHODS ADDED BY MANISH ENDS=============//
	
	//===============METHODS ADDED BY GANESH BEGINS=============//
	
	/** This input function is get called for Bonus */
	public String bonusPaymentSaveAndNextRecord() {
		try {
			ReturnsActModel model = new ReturnsActModel();
			model.initiate(context, session);
			boolean result;
			if (actsPaymentOfBonus.getReturnID().equals("")) {
				result = model.getInsertBonusRecords(actsPaymentOfBonus);

				if (result) {
					addActionMessage("Record Save Successfully.");
				} else {
					addActionMessage("Record not Updated Successfully");
				}
			} else {
				result = model.getUpdateBonusRecords(actsPaymentOfBonus);

				if (result) {
					addActionMessage("Record Updated Successfully.");
				} else {
					addActionMessage("Record not Updated Successfully");
				}
			}

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
			// return null;
		}
		getNavigationPanel(2);
		String pageToShow = retactBean.getPageToShow();

		return pageToShow;
	}

	/** This input function is get called for Child Labour */
	public String childLabourSaveAndNextRecord() {
		try {
			ReturnsActModel model = new ReturnsActModel();
			model.initiate(context, session);
			boolean result;
			if (actsChildLabour.getReturnID().equals("")) {
				result = model.getInsertChildLabourRecords(actsChildLabour);

				if (result) {
					addActionMessage("Record Save Successfully.");
				} else {
					addActionMessage("Record not Updated Successfully");
				}
			} else {
				result = model.getUpdateChildLabourRecords(actsChildLabour);

				if (result) {
					addActionMessage("Record Updated Successfully.");
				} else {
					addActionMessage("Record not Updated Successfully");
				}
			}

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
			// return null;
		}
		getNavigationPanel(2);
		String pageToShow = retactBean.getPageToShow();

		return pageToShow;
	}

	/** This input function is get called for Maternity Benefits */
	public String maternityBenefitsSaveAndNextRecord() {
		try {
			ReturnsActModel model = new ReturnsActModel();
			model.initiate(context, session);
			boolean result;
			if (actsMaternityBenefits.getReturnID().equals(
					"")) {
				result = model
						.getInsertMaternityBenefitsRecords(actsMaternityBenefits);

				if (result) {
					addActionMessage("Record Save Successfully.");
				} else {
					addActionMessage("Record not Updated Successfully");
				}
			} else {
				result = model
						.getUpdateMaternityBenefitsRecords(actsMaternityBenefits);

				if (result) {
					addActionMessage("Record Updated Successfully.");
				} else {
					addActionMessage("Record not Updated Successfully");
				}
			}

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
			// return null;
		}
		getNavigationPanel(2);
		String pageToShow = retactBean.getPageToShow();

		return pageToShow;
	}

	/** This function is get called for General Information */
	public String generalInfoSaveAndNextRecord() {
		try {
			ReturnsActModel model = new ReturnsActModel();
			model.initiate(context, session);
			boolean result;
			if (generalInfo.getReturnID().equals("")) {
				result = model.getInsertGeneralInfoRecords(generalInfo,
						workForceInfo);

				if (result) {
					addActionMessage("Record Save Successfully.");
				} else {
					addActionMessage("Record not Updated Successfully.");
				}
			} else {
				result = model.getUpdateGeneralInfoRecords(generalInfo,
						workForceInfo);

				if (result) {
					addActionMessage("Record Updated Successfully.");
				} else {
					addActionMessage("Record not Updated Successfully");
				}
			}

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
			// return null;
		}
		getNavigationPanel(2);
		String pageToShow = retactBean.getPageToShow();

		return pageToShow;
	}
	
	
	// ===============METHODS ADDED BY GANESH ENDS=============//

	/**
	 * @return the gratuityBean
	 */
	public GratuityRules getGratuityBean() {
		return gratuityBean;
	}

	/**
	 * @param gratuityBean
	 *            the gratuityBean to set
	 */
	public void setGratuityBean(GratuityRules gratuityBean) {
		this.gratuityBean = gratuityBean;
	}

	/**
	 * @return the rentAllowanceBean
	 */
	public RentAllowance getRentAllowanceBean() {
		return rentAllowanceBean;
	}

	/**
	 * @param rentAllowanceBean
	 *            the rentAllowanceBean to set
	 */
	public void setRentAllowanceBean(RentAllowance rentAllowanceBean) {
		this.rentAllowanceBean = rentAllowanceBean;
	}

	/**
	 * @return the equalRemunerationBean
	 */
	public EqualRemuneration getEqualRemunerationBean() {
		return equalRemunerationBean;
	}

	/**
	 * @param equalRemunerationBean
	 *            the equalRemunerationBean to set
	 */
	public void setEqualRemunerationBean(EqualRemuneration equalRemunerationBean) {
		this.equalRemunerationBean = equalRemunerationBean;
	}

	/**
	 * @return the actsPaymentOfBonus
	 */
	public ActsPaymentOfBonus getActsPaymentOfBonus() {
		return actsPaymentOfBonus;
	}

	/**
	 * @param actsPaymentOfBonus the actsPaymentOfBonus to set
	 */
	public void setActsPaymentOfBonus(ActsPaymentOfBonus actsPaymentOfBonus) {
		this.actsPaymentOfBonus = actsPaymentOfBonus;
	}

	/**
	 * @return the actsChildLabour
	 */
	public ActsChildLabour getActsChildLabour() {
		return actsChildLabour;
	}

	/**
	 * @param actsChildLabour the actsChildLabour to set
	 */
	public void setActsChildLabour(ActsChildLabour actsChildLabour) {
		this.actsChildLabour = actsChildLabour;
	}

	/**
	 * @return the actsMaternityBenefits
	 */
	public ActsMaternityBenefits getActsMaternityBenefits() {
		return actsMaternityBenefits;
	}

	/**
	 * @param actsMaternityBenefits the actsMaternityBenefits to set
	 */
	public void setActsMaternityBenefits(ActsMaternityBenefits actsMaternityBenefits) {
		this.actsMaternityBenefits = actsMaternityBenefits;
	}

	/**
	 * @return the generalInfo
	 */
	public GeneralInfo getGeneralInfo() {
		return generalInfo;
	}

	/**
	 * @param generalInfo the generalInfo to set
	 */
	public void setGeneralInfo(GeneralInfo generalInfo) {
		this.generalInfo = generalInfo;
	}

	/**
	 * @return the workForceInfo
	 */
	public WorkForceInfo getWorkForceInfo() {
		return workForceInfo;
	}

	/**
	 * @param workForceInfo the workForceInfo to set
	 */
	public void setWorkForceInfo(WorkForceInfo workForceInfo) {
		this.workForceInfo = workForceInfo;
	}

	/**
	 * @return the factoryInspectionsBean
	 */
	public FactoryInspections getFactoryInspectionsBean() {
		return factoryInspectionsBean;
	}

	/**
	 * @param factoryInspectionsBean the factoryInspectionsBean to set
	 */
	public void setFactoryInspectionsBean(FactoryInspections factoryInspectionsBean) {
		this.factoryInspectionsBean = factoryInspectionsBean;
	}

	/**
	 * @return the factorySafetyHealthBean
	 */
	public FactorySafetyHealth getFactorySafetyHealthBean() {
		return factorySafetyHealthBean;
	}

	/**
	 * @param factorySafetyHealthBean the factorySafetyHealthBean to set
	 */
	public void setFactorySafetyHealthBean(
			FactorySafetyHealth factorySafetyHealthBean) {
		this.factorySafetyHealthBean = factorySafetyHealthBean;
	}

	/**
	 * @return the factoryWelfareFacilitiesBean
	 */
	public FactoryWelfareFacilities getFactoryWelfareFacilitiesBean() {
		return factoryWelfareFacilitiesBean;
	}

	/**
	 * @param factoryWelfareFacilitiesBean the factoryWelfareFacilitiesBean to set
	 */
	public void setFactoryWelfareFacilitiesBean(
			FactoryWelfareFacilities factoryWelfareFacilitiesBean) {
		this.factoryWelfareFacilitiesBean = factoryWelfareFacilitiesBean;
	}

	/**
	 * @return the factoryIndustrialRelationsBean
	 */
	public FactoryIndustrialRelations getFactoryIndustrialRelationsBean() {
		return factoryIndustrialRelationsBean;
	}

	/**
	 * @param factoryIndustrialRelationsBean the factoryIndustrialRelationsBean to set
	 */
	public void setFactoryIndustrialRelationsBean(
			FactoryIndustrialRelations factoryIndustrialRelationsBean) {
		this.factoryIndustrialRelationsBean = factoryIndustrialRelationsBean;
	}

	/**
	 * @return the factoryWagesBenefitsBean
	 */
	public FactoryWagesBenefits getFactoryWagesBenefitsBean() {
		return factoryWagesBenefitsBean;
	}

	/**
	 * @param factoryWagesBenefitsBean the factoryWagesBenefitsBean to set
	 */
	public void setFactoryWagesBenefitsBean(
			FactoryWagesBenefits factoryWagesBenefitsBean) {
		this.factoryWagesBenefitsBean = factoryWagesBenefitsBean;
	}

	/**
	 * @return the factoryAnnualReturnBean
	 */
	public FactoryAnnualReturn getFactoryAnnualReturnBean() {
		return factoryAnnualReturnBean;
	}

	/**
	 * @param factoryAnnualReturnBean the factoryAnnualReturnBean to set
	 */
	public void setFactoryAnnualReturnBean(
			FactoryAnnualReturn factoryAnnualReturnBean) {
		this.factoryAnnualReturnBean = factoryAnnualReturnBean;
	}

	/**
	 * @return the factoryOtherDetailsBean
	 */
	public FactoryOtherDetails getFactoryOtherDetailsBean() {
		return factoryOtherDetailsBean;
	}

	/**
	 * @param factoryOtherDetailsBean the factoryOtherDetailsBean to set
	 */
	public void setFactoryOtherDetailsBean(
			FactoryOtherDetails factoryOtherDetailsBean) {
		this.factoryOtherDetailsBean = factoryOtherDetailsBean;
	}

	public MinimumWages getMinimumWages() {
		return minimumWages;
	}

	public void setMinimumWages(MinimumWages minimumWages) {
		this.minimumWages = minimumWages;
	}

}
