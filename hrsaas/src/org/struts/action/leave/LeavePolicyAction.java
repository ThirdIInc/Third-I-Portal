/**
 * 
 */
package org.struts.action.leave;

import java.util.ArrayList;
import org.paradyne.bean.leave.LeavePolicy;
import org.paradyne.model.leave.LeavePolicyModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author lakkichand and Pradeep
 * 
 */
public class LeavePolicyAction extends ParaActionSupport {

	LeavePolicy leavPolicy = null;

	/**
	 * @return the leavPolicy
	 */
	public LeavePolicy getLeavPolicy() {
		return leavPolicy;
	}

	/**
	 * @param leavPolicy
	 *            the leavPolicy to set
	 */
	public void setLeavPolicy(LeavePolicy leavPolicy) {
		this.leavPolicy = leavPolicy;
	}

	public Object getModel() {
		return leavPolicy;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {

		leavPolicy = new LeavePolicy();
		leavPolicy.setMenuCode(61);
	}

	/**
	 * THIS METHOD IS USED FOR RESETTING RECORD
	 * 
	 * @return String
	 */
	public String reset() {
		try {
			leavPolicy.setCarryForword("");
			leavPolicy.setCreditInterval("");
			leavPolicy.setApplicable("");
			leavPolicy.setEmpType("");
			leavPolicy.setEncashable("");
			leavPolicy.setLeaveCode("");
			leavPolicy.setTypeID("");
			leavPolicy.setEntitle("");
			leavPolicy.setLeaveName("");
			leavPolicy.setMaxbalance("");
			leavPolicy.setMinBalance("");
			leavPolicy.setMaxLevsInMonth("");
			leavPolicy.setDivisionCode("");
			leavPolicy.setDivisionName("");
			leavPolicy.setPolicyCode("");
			leavPolicy.setPolicyName("");
			leavPolicy.setLeaveApplicableto("");
			leavPolicy.setLeaveCreditType("");
			leavPolicy.setFromMonth("1");
			leavPolicy.setToMonth("12");
			leavPolicy.setPenaltyStatus("false");
			leavPolicy.setUnplaneFlag("");
			leavPolicy.setCarryForword("");
			
			
			leavPolicy.setUnAuthorizedStatus("");
			leavPolicy.setUnAuthorizedDay("");
			leavPolicy.setAbsentDays("");
			
			leavPolicy.setLeaveBalanceNotAvail("");
			leavPolicy.setLeaveBalEnableStatus("");
			leavPolicy.setF9Flag("false");
		}// end of try
		catch (Exception e) {
			logger.error("Exception in reset------------------" + e);

		}// end of catch
		return "success";
	}// end of reset

	/**
	 * THIS METHOD IS USED FOR SAVING RECORD
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() {

		try {
			LeavePolicyModel model = new LeavePolicyModel();
			model.initiate(context, session);
			boolean result = false;


			String[] applicable = request.getParameterValues("applicable");
			String[] leaveApplicableTo = request
					.getParameterValues("leaveApplicableto");// leave
			// Applicable To
			String[] creInterval = request.getParameterValues("creditInterval");// credit
			// interval
			
			
			
			String[] carForword = request.getParameterValues("carryForword");// carry
			// forward
			String[] maxLevsToBeForward = request
					.getParameterValues("maxLevsToBeForward");
			String[] maxLevsInMonth = request
					.getParameterValues("maxLevsInMonth");// maximum leaves in
			// month
			String[] maxLeaveAccm = request.getParameterValues("maxLeaveAccm");// maximum
			// balance
			String[] minLeaveApply = request
					.getParameterValues("minLeaveApply");// minimum balance
			
			String[] holiInclude = request.getParameterValues("holiDay");// holiday
			// exclude
			String[] weekInclude = request.getParameterValues("weekoff");// weekly
			// off
		
			String[] hiddesncons = request.getParameterValues("hiddesncons");// can
			// not
			// combine
			// with
			String[] conseName = request.getParameterValues("conse");// leave
			// name
			String[] zeroBalance = request.getParameterValues("zeroBal");// zero
			// balance
			// applicable
			String[] leaveCreditType = request
					.getParameterValues("leaveCreditType");// leave Credit Type
			String[] halfLeave = request.getParameterValues("halfLeave");// half
			// day
			// applicable
			String[] paidUnpaidLeavs = request.getParameterValues("levType");// leave
			// type
			String[] proofRequired = request
					.getParameterValues("proofRequired");
			String[] noticePeriod = request
			.getParameterValues("noticePeriod");
			
			/*String[] consePool = 
			request.getParameterValues("consePool");*/
			
			String[] hiddesnconsPool = request
			.getParameterValues("hiddesnconsPool");
			/**
			 * LEAVE ENCASHMENT
			 */
			String[] enableFlag = request.getParameterValues("enableFlag");
			//ENCASH FLAG
			String[] encashmentFormula = request.getParameterValues("encashmentFormula");
			//ENCASHMENT FORMULA
			String[] maxEncashLimit = request.getParameterValues("maxEncashLimit");
			String[] maxEncashSelect = request.getParameterValues("maxEncashSelect");
			String[] maxEncSepration = request.getParameterValues("maxEncSepration");
			/**
			 * UNPLANE / UNAUTHORIZED LEAVE SETTING
			 */
			String[]unPlaneHidCode=request.getParameterValues("unPlaneHidCode");		
			String[]unPlaneLeave=request.getParameterValues("unPlaneLeave");
			String[]forLeaves=request.getParameterValues("forLeaves");
			String[]toLeaves=request.getParameterValues("toLeaves");
			String[]appliedInAdvance=request.getParameterValues("appliedInAdvance");
			String[]penaltyForUnPlane=request.getParameterValues("penaltyForUnPlane");
			
			String []hidLevCode=request.getParameterValues("hiddenLeaveCode");
			String []dop=request.getParameterValues("dop");			
			String[]beforeAfterDay=request.getParameterValues("beforeAfterDay");
			String[]beforeAfterDayPool=request.getParameterValues("beforeAfterDayPool");
		
/*
 * Modified by manish sakpal
 */			
			
			//String[] intervalmonthname =request.getParameterValues("intervalmonthname");//intervalmonthname
			
			String[] carryforwardmonth=request.getParameterValues("carryforwardmonth");//carryforwardmonth
			
			String[] maxLeaveApply = request.getParameterValues("maxLeaveApply");// maximum balance
			
			
			if (leavPolicy.getPolicyCode().equals("")) {
				result = model.saveLeavePolicy(applicable, leaveApplicableTo,
						creInterval, carForword, maxLevsToBeForward,
						maxLevsInMonth, maxLeaveAccm, minLeaveApply,
						holiInclude, weekInclude, hiddesncons, conseName,
						leavPolicy, zeroBalance, leaveCreditType, halfLeave,
						paidUnpaidLeavs, proofRequired,enableFlag,encashmentFormula,maxEncashLimit,maxEncashSelect,maxEncSepration,
						noticePeriod,hiddesnconsPool,carryforwardmonth,maxLeaveApply);
				if(result && hidLevCode !=null && hidLevCode.length>0){
					Object[][]month=new Object[hidLevCode.length][12];
					Object[][]beforeValue=new Object[hidLevCode.length][12];
					Object[][]afterValue=new Object[hidLevCode.length][12];	
					Object[][]beforeValuePool=new Object[hidLevCode.length][12];
					Object[][]afterValuePool=new Object[hidLevCode.length][12];		
					for (int i = 0; i < hidLevCode.length; i++) {
						month[i]=request.getParameterValues("exMonth"+i);
						beforeValue[i]=request.getParameterValues("newMonthBefore"+i);
						afterValue[i]=request.getParameterValues("newMonthAfter"+i);
						beforeValuePool[i]=request.getParameterValues("newMonthBeforePool"+i);
						afterValuePool[i]=request.getParameterValues("newMonthAfterPool"+i);
					}				
					/**
					 * SAVE METHOD FOR LEAVE ENTITLEMENT
					 */
					model.saveLeaveEntitle(leavPolicy,hidLevCode,month,dop,beforeValue,afterValue,beforeAfterDay,
							beforeValuePool,afterValuePool,beforeAfterDayPool);
					/**
					 * METHOD FOR UNPLANE
					 */
					model.saveUnplaneRule(leavPolicy,unPlaneLeave,forLeaves,toLeaves,appliedInAdvance,penaltyForUnPlane,unPlaneHidCode);
				}
				if(leavPolicy.getResetFlag().equals("")){	
					if (result) {
						addActionMessage(getMessage("save"));
					} else {
						addActionMessage("Duplicate entry found");
					}
				}
			} else {
			
				result = model.saveLeavePolicy(applicable, leaveApplicableTo,
						creInterval, carForword, maxLevsToBeForward,
						maxLevsInMonth, maxLeaveAccm, minLeaveApply,
						holiInclude, weekInclude, hiddesncons, conseName,
						leavPolicy, zeroBalance, leaveCreditType, halfLeave,
						paidUnpaidLeavs, proofRequired,enableFlag,encashmentFormula,maxEncashLimit,maxEncashSelect,maxEncSepration,
						noticePeriod,hiddesnconsPool,carryforwardmonth,maxLeaveApply);
				if(result&& hidLevCode !=null && hidLevCode.length>0){
					Object[][]month=new Object[hidLevCode.length][12];
					Object[][]beforeValue=new Object[hidLevCode.length][12];
					Object[][]afterValue=new Object[hidLevCode.length][12];		
					Object[][]beforeValuePool=new Object[hidLevCode.length][12];
					Object[][]afterValuePool=new Object[hidLevCode.length][12];	
					for (int i = 0; i < hidLevCode.length; i++) {
						month[i]=request.getParameterValues("exMonth"+i);
						beforeValue[i]=request.getParameterValues("newMonthBefore"+i);
						afterValue[i]=request.getParameterValues("newMonthAfter"+i);
						beforeValuePool[i]=request.getParameterValues("newMonthBeforePool"+i);
						afterValuePool[i]=request.getParameterValues("newMonthAfterPool"+i);
					}		
					System.out.println("= ##### month.length "+month.length);
					model.saveLeaveEntitle(leavPolicy,hidLevCode,month,dop,beforeValue,afterValue,beforeAfterDay,
							beforeValuePool,afterValuePool,beforeAfterDayPool);
					/**
					 * METHOD FOR UNPLANE
					 */
					model.saveUnplaneRule(leavPolicy,unPlaneLeave,forLeaves,toLeaves,appliedInAdvance,penaltyForUnPlane,unPlaneHidCode);
				}
				
				if(leavPolicy.getResetFlag().equals("")){
						if (result) {
							addActionMessage(getMessage("update"));
						} else {
							addActionMessage("Duplicate entry found");
						}
				}
			}
			model.terminate();
			if(leavPolicy.getResetFlag().equals("")){
			reset();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception in save----------" + e);
		}
		return "success";
		// return getDetails();
	}// end of save

	
	
	
	
	
	/**
	 * MOTHOD FOR SEARCH DISPLAY DATA *
	 * 
	 * @return
	 */

	public String getPolicyDetails() {
		LeavePolicyModel model = new LeavePolicyModel();
		model.initiate(context, session);
		try {
			logger.info("Penalty status   "+leavPolicy.getHiddenPenelty());
			leavPolicy.setPenaltyStatus(leavPolicy.getHiddenPenelty());
			//GET DATA FOR LEAVE POLICY
			model.setHeaderData(leavPolicy);		
			String[][] result = model.getPolicyDetails(leavPolicy);		
			String[][]regEmpData=model.getLeaveEntRegEmp(leavPolicy);	
			String[][]newEmpData=model.getLeaveEntNewEmp(leavPolicy);
			String[][]empDataPool=model.getLeaveEmpPoolLeave(leavPolicy);
			String resultLeave=model.setUnauthorizedLeave(leavPolicy);
			request.setAttribute("regEmpData",regEmpData );
			request.setAttribute("newEmpData",newEmpData );
			request.setAttribute("empDataPool",empDataPool );
			leavPolicy.setAddRuleField("");
			leavPolicy.setUnplaneFlag("");
			model.addUnplaneRule(leavPolicy);
			if (result != null && result.length > 0) {
				request.setAttribute("data", result);
				leavPolicy.setFlag("true");
				leavPolicy.setF9Flag("true");
			}// end of if
			else {
				addActionMessage("There is no data to display");
			}// end of else
		} // end of try
		catch (Exception e) {
			logger.error("Exception in getDetails------------------"  );
			e.printStackTrace();
		}// end of catch
		model.terminate();
		return "success";
	}// end of getDetails

	/**
	 * THIS METHOD IS USED FOR GETTING DETAILS ABOUT LEAVE POLICY ACCORDING TO
	 * EMPLOYEE TYPE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getDetails() {
		LeavePolicyModel model = new LeavePolicyModel();
		model.initiate(context, session);
		try {
			String[][] result = model.getTableDetails(leavPolicy);			
			if (result != null && result.length > 0) {
				request.setAttribute("data", result);
				/**
				 * get object & added i wor for date of processing
				 */
			String[][]regEmpData=new String[result.length+1][result[0].length];
			for (int i = 0; i < regEmpData.length; i++) {
				for (int j = 0; j < result[0].length; j++) {
					if(i<result.length){
						regEmpData[i][j]=result[i][j];
					}	
					else{
						regEmpData[i][j]="";
					}
				}
				
			}
				
				request.setAttribute("regEmpData", regEmpData);	
				request.setAttribute("newEmpData",result );		
				request.setAttribute("empDataPool",result );		
				leavPolicy.setFlag("true");
				leavPolicy.setUnplaneFlag("");
				/**
				 * FOR UNPLANE LEAVE
				 */
				leavPolicy.setUnAuthorizedLeaveStatus("false");
				leavPolicy.setLateMarksLeave("");
				leavPolicy.setLateMarksLeaveCode("");
				leavPolicy.setAddRuleField("");
				model.addUnplaneRule(leavPolicy);
			}// end of if
			else {
				addActionMessage("There is no data to display");
			}// end of else
		} // end of try
		catch (Exception e) {
			logger.error("Exception in getDetails------------------" + e);
		}// end of catch
		model.terminate();
		return "success";
	}// end of getDetails

	
	public String addUnplaneRule() throws Exception{
		LeavePolicyModel model = new LeavePolicyModel();
		model.initiate(context, session);		
		leavPolicy.setResetFlag("reset");
		save();
		leavPolicy.setResetFlag("");
		String[][] result = model.getPolicyDetails(leavPolicy);		
		String[][]regEmpData=model.getLeaveEntRegEmp(leavPolicy);	
		String[][]newEmpData=model.getLeaveEntNewEmp(leavPolicy);	
		
		request.setAttribute("regEmpData",regEmpData );
		request.setAttribute("newEmpData",newEmpData );
		
		String[][]empDataPool=model.getLeaveEmpPoolLeave(leavPolicy);
		request.setAttribute("empDataPool",empDataPool );
		if (result != null && result.length > 0) {
			request.setAttribute("data", result);
			leavPolicy.setFlag("true");
		}// end of if
		leavPolicy.setAddRuleField("addRule");
		model.addUnplaneRule(leavPolicy);
		leavPolicy.setAddRuleField("");
		model.terminate();		
		return SUCCESS;
	}
	
	public String removeUnplaneRule() throws Exception{
		LeavePolicyModel model = new LeavePolicyModel();
		model.initiate(context, session);		
		
		String[]remCode=request.getParameterValues("unPlaneLeave");
	
		leavPolicy.setResetFlag("reset");
		save();
		leavPolicy.setResetFlag("");
		String[][] result = model.getPolicyDetails(leavPolicy);		
		String[][]regEmpData=model.getLeaveEntRegEmp(leavPolicy);	
		String[][]newEmpData=model.getLeaveEntNewEmp(leavPolicy);	
		request.setAttribute("regEmpData",regEmpData );
		request.setAttribute("newEmpData",newEmpData );
		String[][]empDataPool=model.getLeaveEmpPoolLeave(leavPolicy);
		request.setAttribute("empDataPool",empDataPool );
		if (result != null && result.length > 0) {
			request.setAttribute("data", result);
			leavPolicy.setFlag("true");
		}// end of if
		leavPolicy.setAddRuleField("");
		String policyCode=leavPolicy.getPolicyCode();
		String []hideenY=request.getParameterValues("hideenY");
		String[]unPlaneHidCode=request.getParameterValues("unPlaneHidCode");		
		
		model.removeUnplaneRule(policyCode,remCode,unPlaneHidCode,hideenY);
		
		
		
		model.addUnplaneRule(leavPolicy);
		leavPolicy.setAddRuleField("");
		model.terminate();		
		return SUCCESS;
	}
	
	
	/**
	 * THIS METHOD IS USED FOR DELETING LEAVE POLICY
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		try {
			LeavePolicyModel model = new LeavePolicyModel();
			model.initiate(context, session);
			boolean result = model.deletePolicy(leavPolicy);
			if (result) {
				addActionMessage(getMessage("delete"));
				
			}// end of if
			else {
				addActionMessage(getMessage("del.error"));
			}// end of else
			model.terminate();
			reset();
			leavPolicy.setPolicyCode("");
			leavPolicy.setPolicyName("");
			leavPolicy.setDivisionCode("");
			leavPolicy.setDivisionName("");
			leavPolicy.setFromMonth("1");
			leavPolicy.setToMonth("12");
			leavPolicy.setFlag("");
			leavPolicy.setPenaltyStatus("false");
		} catch (Exception e) {
			logger.info("Exception in delete---------" + e);
		}
		return SUCCESS;
	}// end of delete

	/**
	 * THIS METHOD IS USED FOR LEAVE CONSECUTIVE(LEAVE CAN NOT COMBINE WITH)
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String leaveConsecutive() throws Exception {

		try {
			LeavePolicyModel model = new LeavePolicyModel();
			String hCode = request.getParameter("hCode");
			leavPolicy.setHLeaveCode(hCode);
			model.initiate(context, session);
			String query = " SELECT HRMS_LEAVE.LEAVE_ID,LEAVE_ABBR FROM HRMS_LEAVE ORDER BY HRMS_LEAVE.LEAVE_ID";
			Object obj[][] = model.getSqlModel().getSingleResult(query);
			ArrayList<Object> arr = new ArrayList<Object>();
			for (int i = 0; i < obj.length; i++) {
				// if(!hCode.equals(String.valueOf(obj[i][0]))){
				LeavePolicy levPol = new LeavePolicy();
				levPol.setLevid(String.valueOf(obj[i][0]));// leave code
				levPol.setLevname(String.valueOf(obj[i][1]));// leave name
				arr.add(levPol);
				// }

			}// end of for
			leavPolicy.setArr(arr);
			String str = request.getParameter("id");
			String str1 = request.getParameter("id1");
			leavPolicy.setFrmId(str);
			leavPolicy.setHiddenfrmId(str1);
			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in leaveConsecutive------- " + e);
		}
		return "f9calc";

	}// end of leaveConsecutive

	/**
	 * THIS METHOD IS USED FOR SELECTING EMPLOYEE TYPE
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */
		String query = " SELECT  LEAVE_POLICY_CODE, LEAVE_POLICY_NAME,DIV_NAME,DIV_CODE ,LEAVE_MGTYEAR_MONTH_START, LEAVE_MGTYEAR_MONTH_END,DECODE(LEAVE_UNPLAN_ISENABLED,'Y','true','false')"
				+ " FROM  HRMS_LEAVE_POLICY_HDR "
				+ " LEFT JOIN  HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_LEAVE_POLICY_HDR.DIV_CODE) ";
		
		if(leavPolicy.getUserProfileDivision() !=null && leavPolicy.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+leavPolicy.getUserProfileDivision() +")"; 
			query+= " ORDER BY LEAVE_POLICY_CODE ";
			 
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */

		String[] headers = { getMessage("policycode"),
				getMessage("policyname"), getMessage("division") };

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "20", "40", "40" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "policyCode", "policyName", "divisionName",
				"divisionCode" ,"fromMonth","toMonth","hiddenPenelty"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3,4,5,6 };

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
		String submitToMethod = "LeavePolicy_getPolicyDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}// end of f9action

	/**
	 * Method to select the Division
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String f9divisionaction() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";
				 
		if(leavPolicy.getUserProfileDivision() !=null && leavPolicy.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+leavPolicy.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("division.code"),
				getMessage("division") };

		String[] headerWidth = { "30", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "divisionCode", "divisionName" };

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
		String submitToMethod = "LeavePolicy_getDetails.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	
	/**
	 * method name : leaveCombination purpose : to display all the available
	 * leave types in new window return type : String parameter : none
	 */
	
	public String leaveCombination() {
		leavPolicy.setLeaveCodeHidNext(leavPolicy.getLeaveCodeHid());
		leavPolicy.setLeaveAbbrHidNext(leavPolicy.getLeaveAbbrHid());
		LeavePolicyModel model = new LeavePolicyModel();
		model.initiate(context, session);
		model.leaveCombination(leavPolicy);
		model.terminate();
		return "leaveList";
	}
	
	/**
	 * method name : setLeavePriority purpose :to set the leave priorities
	 * return type : String parameter : none
	 */
	public String setLeavePriority() {
		/**
		 * getting all the form fields values which are necessary to set the
		 * leave priority
		 */
		String srNo = leavPolicy.getSrNo();
		String buttonType = request.getParameter("type");
		String[] leaveCodev = request.getParameterValues("leaveCodev");
		String[] leaveNamev = request.getParameterValues("leaveNamev");
		String[] leaveAbbrv = request.getParameterValues("leaveAbbrv");

		LeavePolicyModel model = new LeavePolicyModel();
		model.initiate(context, session);
		model.setLeavePriority(leavPolicy, srNo, buttonType, leaveCodev,
				leaveNamev, leaveAbbrv);
		model.terminate(); // terminate the AttendanceSettingsModel class
		return "leaveList";
	}
	
	
	
	public String getLeaveIntervalMonth()
	{
		
		try {
			 
			String hCode = request.getParameter("hCode");
			System.out.println("hCode         "+hCode);
			leavPolicy.setHLeaveCode(hCode);			
			 
			String str = request.getParameter("id");
			String str1 = request.getParameter("id1");		
			
			System.out.println("str    "+str);
			
			System.out.println("str1   "+str1);
			leavPolicy.setFrmId(str);
			leavPolicy.setHiddenfrmId(str1);
			 
			 
		} catch (Exception e) {
			logger.info("Exception in leaveConsecutive------- " + e);
		}
		
		
		
		
		return "leaveintervalmonth";
	}
	
	
	
	public String resetIntervalMonth()
	{
		leavPolicy.setCreditInterval("");
		return "success";
	}
}// end of class
