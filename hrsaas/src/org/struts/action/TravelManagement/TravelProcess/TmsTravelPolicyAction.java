package org.struts.action.TravelManagement.TravelProcess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.paradyne.bean.TravelManagement.TravelProcess.TmsTravelPolicy;
import org.paradyne.model.TravelManagement.TravelProcess.TmsTravelPolicyModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0563
 *
 */
public class TmsTravelPolicyAction extends ParaActionSupport {

	private static final long serialVersionUID = 1L;
	TmsTravelPolicy tp;

	public void prepare_local() throws Exception {
		tp = new TmsTravelPolicy();
		tp.setMenuCode(771);
	}

	public Object getModel() {
		return tp;
	}

	/**
	 * @return the tp
	 */
	public TmsTravelPolicy getTp() {
		return tp;
	}

	/**
	 * @param tp the tp to set
	 */
	public void setTp(TmsTravelPolicy tp) {
		this.tp = tp;
	}

	//calling the list appear below in onload  method 
	public String input() throws Exception {
		try {
			TmsTravelPolicyModel model = new TmsTravelPolicyModel();
			model.initiate(context, session);
			model.callListPage(tp, request);			
			model.terminate();
			getNavigationPanel(1);
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return INPUT;
	}

	public String callPage() throws Exception {

		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		model.initiate(context, session);
		model.callListPage(tp, request);
		getNavigationPanel(1);
		model.terminate();
		return INPUT;

	}

	//
	public String addNew() {
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		model.initiate(context, session);
		model.getPolicyCurrency(tp);
		model.terminate();
		tp.setEditFlag("false");
		getNavigationPanel(8);
		reset();
		return "success";

	}

	public String save() {
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		String type = request.getParameter("saveType");
		getNavigationPanel(8);
		model.initiate(context, session);
		/*if(tp.getPolicyId().equals(""))
		{*/
		if (type.equals("success")) {
			String overWrite = request.getParameter("overWrite");
			if (tp.getPolicyId().equals("")) {
				if (tp.getStatus().equals("A")) {
					//if (tp.getActiveflag().equals("true")) {
						if (model.save(tp, request))
							addActionMessage("Record saved successfully.");
						else {
							addActionMessage("Record cannot be saved.");

						}
					/*} else {
						addActionMessage("policy is not completely defined.you can't keep Active");
						tp.setStatus("D");

					}*/
				} else {
					if (model.save(tp, request))
						addActionMessage("Record saved successfully.");
					else {
						addActionMessage("Duplicate entry found.");
					}
				}
			} else {
				/*if (tp.getStatus().equals("A")
						&& (tp.getActiveflag().equals("false"))) {
					addActionMessage("Policy is not completely defined.you can't keep "
							+ getMessage("status") + " Active");
					tp.setStatus("D");
				} else {*/
					boolean flag = false;
					/*if (tp.getStatus().equals("A")
							&& (tp.getActiveflag().equals("true"))
							&& (tp.getCheckActive().equals("D"))) {
						model.changeStatus(tp, overWrite);
						boolean confirm = model.chkstatus(tp);
						if (!confirm) {
							addActionMessage("No grade is available for this policy.you cannot keep "
									+ getMessage("status") + " Active");
							tp.setStatus("D");
							flag = true;
						}

					}*/
					/*if (tp.getStatus().equals("D")
							&& (tp.getActiveflag().equals("true"))
							&& (tp.getCheckActive().equals("A"))) {
						String query = "select CADRE_NAME,POLICY_GRAD_ID  from  TMS_POLICY_GRADE_DTL "
								+ " INNER JOIN HRMS_CADRE ON(TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID=HRMS_CADRE.CADRE_ID) "
								+ " where TMS_POLICY_GRADE_DTL.policy_id ="
								+ tp.getPolicyId();
						Object obj[][] = model.getSqlModel().getSingleResult(
								query);
						String gradeStr = "";
						if (obj != null && obj.length > 0) {
							for (int i = 0; i < obj.length; i++) {
								gradeStr += obj[i][0] + ",";
							}
							gradeStr = gradeStr.substring(0,
									gradeStr.length() - 1);
							addActionMessage("you cannot keep  status deactive as "
									+ gradeStr
									+ " grade(s) are not defined in any other policy");
							tp.setStatus("A");
						} else {*/
						
					/*	if (model.update(tp, request))
								addActionMessage("Record updated successfully.1");
							else {
								System.out.println("Duplicate entry while updating the existing record.");
								addActionMessage("Duplicate entry found.");
							}*/
					
						/*}//inne

					} else {*/
						if (!flag) {
							if (model.update(tp, request))
								addActionMessage("Record updated successfully.");
							else {
								System.out.println("If not flag =========> Duplicate entry.");
								addActionMessage("Duplicate entry found.");
							}
						}
					//} //inner else
				//} //outer else 1
			} //outer else
		}

		//}
		if (type.equals("gradeview")) {
			String overWrite = request.getParameter("overWrite");
			String gradeList = request.getParameter("gradeList");
			System.out.println("overWrite-----------------" + overWrite);
			// String policyid=request.getParameter("policyId");
			//System.out.println("policyId-------------"+policyid);
			String[] empGrade = (String[]) request
					.getParameterValues("empGrade");
			String[] empGradeId = (String[]) request
					.getParameterValues("empGradeId");
			String[] gradeStatus = (String[]) request
					.getParameterValues("hidGradeStatus");

			int cnt = 0;
			for (int i = 0; i < empGradeId.length; i++) {
				if (gradeStatus[i].equals("Y")) {
					cnt++;
				}

			}

			String empid[] = new String[cnt];
			String[] empStatusGradeId = new String[cnt];

			int count = 0;
			for (int i = 0; i < empGradeId.length; i++) {
				if (gradeStatus[i].equals("Y")) {
					empStatusGradeId[count] = gradeStatus[i];
					//System.out.println("empStatusGradeId---------"+empStatusGradeId[i]);
					empid[count] = empGradeId[i];
					//System.out.println("empStatusGradeId---------"+empid[i]);
					count++;
				}
				/* else
				 {
					 empStatusGradeId[i]=gradeStatus[i];
				 }*/
				/*String gradeSql ="  INSERT INTO HRMS_TMS_POLICY_GRADE ( PG_ID, PG_POLICY_ID,PG_EMP_GRADE, PG_STATUS)  "
					          +" VALUES (( SELECT NVL(MAX(PG_ID),0)+1 FROM HRMS_TMS_POLICY_GRADE),"+bean.getPolicyId()+" ,"+empStatusGradeId[i]+" ,'"+status+"')";
				flag= getSqlModel().singleExecute(gradeSql); 
				j++;*/

			}
			boolean avalFlag = model.checkAvailableGrade(tp);
			if (avalFlag) {

				if (model.saveGrade(tp, empid, empStatusGradeId, overWrite,
						gradeList)) {
					addActionMessage("Record updated successfully.");

					model.callGradeList1(tp);
					getNavigationPanel(9);

				} else {
					addActionMessage("No Employee Grade selected");
					model.callGradeList(tp);
					getNavigationPanel(9);
				}
			}

			else {
				if (model.saveGrade(tp, empid, empStatusGradeId, overWrite,
						gradeList)) {
					addActionMessage("Record saved successfully.");

					model.callGradeList1(tp);
					getNavigationPanel(9);

				} else {
					addActionMessage("No Employee Grade selected");
					model.callGradeList(tp);
					getNavigationPanel(9);
				}
			}
		}
		model.getPolicyCurrency(tp);
		return type;
	}

	/* current workspace save method
	public String save()
	{ 
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
	    String type=request.getParameter("saveType");
	 
	    System.out.println("type is------------"+type);
		 getNavigationPanel(8);
		
		//System.out.println("policyId---------------------"+request.getParameter("policyId"));
		System.out.println("tp.getPolicyId()-------------"+tp.getPolicyId());
		model.initiate(context, session);
		
		 
			if(type.equals("success"))
			{
				String overWrite=request.getParameter("overWrite");
			if(tp.getPolicyId().equals(""))
			{
				 if(tp.getStatus().equals("A"))
				 {
				if(tp.getActiveflag().equals("true"))
				{
				if(model.save(tp,request))
					addActionMessage("Record saved successfully."); 
				 else 
				 {
					addActionMessage("Record cannot be saved.");
					
				 }
				}
				else{addActionMessage("policy is not completely defined.you can't keep Active");
				tp.setStatus("D"); 
				}
			}
				 else{
					 if(model.save(tp,request))
							addActionMessage("Record saved successfully."); 
						 else 
						 {
							 addActionMessage("Duplicate entry found.");
							
						 }
				 } 
			}
			
			else
			{
				if(tp.getStatus().equals("A")&&(tp.getActiveflag().equals("false")))
					addActionMessage("Policy is not completely defined.you can't keep "+getMessage("status")+" Active");
				
				else {
					
					if(model.update(tp,request))
						addActionMessage("Record updated successfully."); 
					else 
					{
						addActionMessage("Duplicate entry found.");
					}
				}
			}
	}	
			
		//}
		if(type.equals("gradeview"))
			{
				 String overWrite=request.getParameter("overWrite");
				 System.out.println("overWrite-----------------"+overWrite);
				// String policyid=request.getParameter("policyId");
				 //System.out.println("policyId-------------"+policyid);
				 String [] empGrade =(String[])request.getParameterValues("empGrade");
				 String [] empGradeId =(String[])request.getParameterValues("empGradeId");
				 String [] gradeStatus =(String[])request.getParameterValues("hidGradeStatus");  
				
				
			int cnt=0;
				 for(int i=0;i<empGradeId.length;i++)
				 {   
					 if(gradeStatus[i].equals("Y"))
					 {
						cnt++;
					 }			
				   
				 }
				
				
				 String empid[]=new String[cnt];
				 String[] empStatusGradeId= new String[cnt];
				
				 int count=0;
				for(int i=0;i<empGradeId.length;i++)
				 {    
					 if(gradeStatus[i].equals("Y"))
					 {
						 empStatusGradeId[count]= gradeStatus[i];
						 //System.out.println("empStatusGradeId---------"+empStatusGradeId[i]);
						 empid[count]=empGradeId[i];
						 //System.out.println("empStatusGradeId---------"+empid[i]);
						 count++;
					 }
					
				   
				 }
				
				 
			
				
				
				if(model.saveGrade(tp,empid,empStatusGradeId,overWrite))
				{
				addActionMessage("Record saved successfully.");
				
				model.callGradeList1(tp);
				 getNavigationPanel(9);
				
				
				}
				else 
				{
					addActionMessage("No Employee Grade selected");
					model.callGradeList(tp);
					 getNavigationPanel(9);
				}
			}
		
		return type;
	}
	
	
	
	 */
	//clearing the fields 
	public void reset() {
		tp.setPolicyId("");
		tp.setPolicyName("");
		tp.setPolicyAbbr("");
		tp.setPolicyAdvanceAllowed("");
		tp.setMaxDaysTravelClaim("");
		tp.setEffectDate("");
		tp.setDesc("");
		tp.setStatus("S");
		tp.setPayModeForAdvanceClaimCheque("");
		tp.setPayModeForAdvanceClaimCash("");
		tp.setPayModeForAdvanceClaimTransfer("");
		tp.setPayModeForAdvanceClaimInSalary("");
		tp.setMinNoOfAdvanceDaysToApplyForTravel("");
		tp.setTravelTypeSelf("");
		tp.setTravelTypeTeam("");
		tp.setTravelTypeGuest("");
	}

	/* public String firstNext()
	 {
		 getNavigationPanel(8);
		 TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		 if(tp.getPolicyId().equals(""))
			{   boolean flag=model.save(tp,request);
				if(flag)
				{
					addActionMessage("Record saved successfully."); 
					getNavigationPanel(9);
					
				}
				else {
					addActionMessage(getMessage("duplicate"));
					getNavigationPanel(8);
				}
				
			}
				else
				{
					 boolean flag=model.update(tp,request);
						if(flag)
						{
							addActionMessage("Record updated successfully."); 
							model.callGradeList(tp);
							getNavigationPanel(9);
						}
						else {
							addActionMessage(getMessage("duplicate"));
							getNavigationPanel(8);
						}
				}
				
				 
			
		 
		 return "gradeview";
	 }
	 */

	/*
	 * for saving data aswell going to next page from policy form to grade form
	 * return String
	 */
	public String firstSaveandNext() {
		//getNavigationPanel(9);
		String overWrite = request.getParameter("overWrite");
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		model.initiate(context, session);

		if (tp.getPolicyId().equals("")) {
			/*if (tp.getStatus().equals("A")
					&& (tp.getActiveflag().equals("false"))) {

				addActionMessage("Policy is not completely defined.you can't keep "
						+ getMessage("status") + " Active");
				tp.setStatus("D");
				getNavigationPanel(8);
				return SUCCESS;
			} else {*/
				boolean flag = model.save(tp, request);
				System.out.println("flag-------------------" + flag);

				if (flag) {
					//addActionMessage("Record saved successfully."); 
					getNavigationPanel(9);
					model.callGradeList(tp);

				} else {
					addActionMessage("Record cannot be saved.");
					getNavigationPanel(8);
					return SUCCESS;
				}
				//just foe testing 
				tp.setGradeStatus("true");
				tp.setCancelFlag("true");
			//}
		} else {
			/*if (tp.getStatus().equals("A")
					&& (tp.getActiveflag().equals("false"))) {
				addActionMessage("Policy is not completely defined.you can't keep "
						+ getMessage("status") + " Active");

				getNavigationPanel(8);
				tp.setStatus("D");
				return SUCCESS;
			}

			else {*/

				boolean statusflag = false;
				/*if (tp.getStatus().equals("A")
						&& (tp.getActiveflag().equals("true"))
						&& (tp.getCheckActive().equals("D"))) {
					model.changeStatus(tp, overWrite);
					boolean confirm = model.chkstatus(tp);

					if (!confirm) {
						addActionMessage("No grade is available for this policy.you cannot keep "
								+ getMessage("status") + " Active");
						getNavigationPanel(8);
						tp.setStatus("D");
						statusflag = true;
						return SUCCESS;

					}
				}*/
				if (!statusflag) {
					boolean flag = model.update(tp, request);

					if (flag) {
						//addActionMessage("Record updated successfully."); 
						String query = "Select POLICY_ID,POLICY_GRAD_ID from TMS_POLICY_GRADE_DTL where POLICY_ID ="
								+ tp.getPolicyId();
						Object obj[][] = model.getSqlModel().getSingleResult(
								query);
						//method for retriving saved  grade list when go to next page from policy form
						System.out.println("obj.length for grade ------------"
								+ obj.length);
						if (obj.length > 0) {
							model.callNextGradeList(tp);
						} else {
							model.callGradeList(tp);
						}
						getNavigationPanel(9);
						tp.setCancelFlag("true");
					} else {
						addActionMessage("Record cannot be saved.");
						getNavigationPanel(8);
					}
					tp.setEditFlag("true");
				}
			//}
		}
		tp.setEditFlag("true");
		//tp.setActiveflag("true");
		//tp.setActiveflag1("true");		
		model.terminate();

		return "gradeview";
	}

	/*
	 * f9method for policy
	 */
	public String f9action() {
		try {
			String query = " SELECT POLICY_NAME,POLICY_ABBR, TO_CHAR(POLICY_EFFECTIVE_DATE,'DD-MM-YYYY'),DECODE(POLICY_STATUS,'A','Active','D','Deactive'),"
					+ "  POLICY_ID  FROM TMS_TRAVEL_POLICY  "
					+ " ORDER BY POLICY_EFFECTIVE_DATE ";

			String[] headers = { getMessage("policy.name"),
					getMessage("abbreviation"), getMessage("effective.date"),
					getMessage("searchStatus") };

			String[] headerWidth = { "40", "20", "15", "15" };

			String[] fieldNames = { "policyName", "policyAbbr", "effectDate",
					"status", "policyId" };

			int[] columnIndex = { 0, 1, 2, 3, 4 };

			String submitFlag = "true";

			String submitToMethod = "TravelPolicy_setField.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return null;
		}
	} // end of f9Journey

	/*
	 * in f9window setting the other fields 
	 */
	public String setField() {
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		getNavigationPanel(5);
		model.initiate(context, session);
		//model.setFieldDetails(tp);
		
		model.setFieldDetails1(tp);
		
		model.terminate();
		tp.setEnableAll("N");
		//tp.setEditFlag("true");
		/*if(trplc.getEditFlag().equals("true"))
		{ getNavigationPanel(5);
		}else{
		getNavigationPanel(2);
		}*/
		return "success";
	}

	/*
	 * for editing mode  for grade and policy 
	 */
	public String edit()  {
		String type = request.getParameter("saveType");
		try {
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		if (type.equals("success")) {
			getNavigationPanel(8);
			model.initiate(context, session);
			model.setFieldDetails1(tp);
			String query = "SELECT POLICY_ID FROM TMS_POLICY_EXPENSE_DTL Where POLICY_ID=" + tp.getPolicyId();
			Object obj[][] = model.getSqlModel().getSingleResult(query);
			if (obj.length > 0) {
				tp.setActiveflag("true");
			} else {
				tp.setActiveflag("false");
			}

			model.terminate();
			tp.setEditFlag("false");
		}
		
		if (type.equals("gradeview")) {
			getNavigationPanel(9);
			model.initiate(context, session);
			model.callNextGradeList(tp);
			tp.setEditFlag("true");
			model.terminate();
			//tp.setEditFlag("false");
		}

		if (type.equals("details")) {
			getNavigationPanel(10);
			model.initiate(context, session);
			String[][] menuItems = model.getTravelModeItems(tp);
			request.setAttribute("menuItems", menuItems);
			String[][] menuLodgeItems = model.getLodgeModeItems(tp);
			request.setAttribute("menuLodgeItems", menuLodgeItems);

			String query = " Select POLICY_ID from TMS_POLICY_EXPENSE_DTL where POLICY_ID=" + tp.getPolicyId();
			Object obj[][] = model.getSqlModel().getSingleResult(query);
			if (obj.length > 0)  {
				model.callExpenseList1(tp);
				model.callrestPart(tp);
				String[][] menuItems1 = model.getTravelModeItems(tp);
				String[][] menuFlags = model.getTravelModeFlagItems(tp, menuItems1);
				request.setAttribute("menuItems", menuItems1);
				request.setAttribute("menuFlags", menuFlags);
				String[][] menuLodgeItems1 = model.getLodgeModeItems(tp);
				String[][] lodgeMenuFlags = model.getLodgeModeFlagItems(tp, menuLodgeItems1);
				request.setAttribute("menuLodgeItems", menuLodgeItems1);
				request.setAttribute("lodgeMenuFlags", lodgeMenuFlags);
				tp.setModeEntitle("true");
				tp.setLodgemode("true");
			} else  {
				model.callExpenseList(tp);
				model.callrestPart(tp);
			}

			//model.setFieldDetails(tp);
			tp.setEditFlag("true");
			tp.setNextflag("true");
			tp.setEditFlag("false");
			model.terminate();
		}		
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return type;
}

	public void beforeSave() {
		try {
			String policyId = request.getParameter("policyId");
			String status = request.getParameter("status");
			String gradeList = request.getParameter("gradeList");
			TmsTravelPolicyModel model = new TmsTravelPolicyModel();
			model.initiate(context, session);
			String responseTxt = "";
			//	responseTxt = model.checkGradesForPolicy(policyId);
			responseTxt = model.checkGradesForPolicy(policyId, tp, status,
					gradeList);
			if (responseTxt == null && responseTxt.equals(null))
				responseTxt = "";
			//for (int i = 0; i < responseTxt.length; i++) {
			//System.out.println(" Response Grades : "+responseTxt[i][0]);
			System.out.println(" Response Gnames : " + responseTxt);
			//}
			try {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println(responseTxt);
			} // try end
			catch (Exception e) {
				e.printStackTrace();
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//for going to prevous page i.e to policy form
	public String saveandprevious() {		
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		String str = "";
		model.initiate(context, session);
		//for save
		String overWrite = request.getParameter("overWrite");
		String gradeList = request.getParameter("gradeList");
		//String policyid=request.getParameter("policyId");

		//System.out.println("policyId-------------"+policyid);
		String[] empGrade = (String[]) request.getParameterValues("empGrade");
		String[] empGradeId = (String[]) request
				.getParameterValues("empGradeId");
		String[] gradeStatus = (String[]) request
				.getParameterValues("hidGradeStatus");

		int cnt = 0;
		for (int i = 0; i < empGradeId.length; i++) {
			if (gradeStatus[i].equals("Y")) {
				cnt++;
			}

		}

		String empid[] = new String[cnt];
		String[] empStatusGradeId = new String[cnt];

		int count = 0;
		for (int i = 0; i < empGradeId.length; i++) {
			if (gradeStatus[i].equals("Y")) {
				empStatusGradeId[count] = gradeStatus[i];
				//System.out.println("empStatusGradeId---------"+empStatusGradeId[i]);
				empid[count] = empGradeId[i];
				//System.out.println("empStatusGradeId---------"+empid[i]);
				count++;
			}
			/* else
			 {
				 empStatusGradeId[i]=gradeStatus[i];
			 }*/
			/*String gradeSql ="  INSERT INTO HRMS_TMS_POLICY_GRADE ( PG_ID, PG_POLICY_ID,PG_EMP_GRADE, PG_STATUS)  "
				          +" VALUES (( SELECT NVL(MAX(PG_ID),0)+1 FROM HRMS_TMS_POLICY_GRADE),"+bean.getPolicyId()+" ,"+empStatusGradeId[i]+" ,'"+status+"')";
			flag= getSqlModel().singleExecute(gradeSql); 
			j++;*/

		}

		if (model.saveGrade(tp, empid, empStatusGradeId, overWrite, gradeList)) {
			//if(model.saveGrade(tp,empid,empStatusGradeId,overWrite))
			//{
			//addActionMessage("Record saved successfully.");
			//model.callGradeList1(tp);
			getNavigationPanel(8);
			model.setFieldDetails1(tp);
			str = "success";
			tp.setEditFlag("false");
			String query = "SELECT POLICY_ID FROM TMS_POLICY_EXPENSE_DTL Where POLICY_ID="
					+ tp.getPolicyId();
			Object obj[][] = model.getSqlModel().getSingleResult(query);

			if (obj != null && obj.length > 0) {
				tp.setActiveflag("true");

			} else {
				tp.setActiveflag("false");
			}

		} else {
			addActionMessage(getMessage("save.error"));
			model.callGradeList(tp);
			getNavigationPanel(9);
			str = "gradeview";
			tp.setEditFlag("true");
		}

		//for previous page

		model.terminate();
		return str;
	}

	//for save and next(final) page
	public String secondSaveAndNext() {

		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		model.initiate(context, session);
		String str = "";
		//method for save the grade
		//String overWrite=request.getParameter("overWrite");		
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String dataPath = getText("data_path") + "/upload" + poolName
					+ "/Travel/";
			// logger.info("data path " + dataPath);
			tp.setDataPath(dataPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String overWrite = request.getParameter("overWrite");
		String policyid = request.getParameter("policyId");
		String status = request.getParameter("status");
		String gradeList = request.getParameter("gradeList");

		// String overWrite=request.getParameter("overWrite");
		System.out.println("overWrite-----------------------" + overWrite);
		// String policyid=request.getParameter("policyId");
		System.out.println("policyId-------------" + policyid);
		String[] empGrade = (String[]) request.getParameterValues("empGrade");
		String[] empGradeId = (String[]) request
				.getParameterValues("empGradeId");
		String[] gradeStatus = (String[]) request
				.getParameterValues("hidGradeStatus");

		for (int i = 0; i < gradeStatus.length; i++) {
			System.out.println();

		}

		int cnt = 0;
		for (int i = 0; i < empGradeId.length; i++) {
			if (gradeStatus[i].equals("Y")) {
				cnt++;
			}

		}		
		setPolicyCurrency(policyid);
		String empid[] = new String[cnt];
		String[] empStatusGradeId = new String[cnt];

		int count = 0;
		for (int i = 0; i < empGradeId.length; i++) {
			if (gradeStatus[i].equals("Y")) {
				empStatusGradeId[count] = gradeStatus[i];
				//System.out.println("empStatusGradeId---------"+empStatusGradeId[i]);
				empid[count] = empGradeId[i];
				System.out.println("empStatusGradeId---------" + empid[count]);
				count++;
			}
			/*else
			{
			 empStatusGradeId[i]=gradeStatus[i];
			}
			String gradeSql ="  INSERT INTO HRMS_TMS_POLICY_GRADE ( PG_ID, PG_POLICY_ID,PG_EMP_GRADE, PG_STATUS)  "
				          +" VALUES (( SELECT NVL(MAX(PG_ID),0)+1 FROM HRMS_TMS_POLICY_GRADE),"+bean.getPolicyId()+" ,"+empStatusGradeId[i]+" ,'"+status+"')";
			flag= getSqlModel().singleExecute(gradeSql); 
			j++;*/

		}

		if (model.saveGrade(tp, empid, empStatusGradeId, overWrite, gradeList)) {
			//if(model.saveGrade(tp,empid,empStatusGradeId,overWrite))
			//{
			//addActionMessage("Record saved successfully.");
			String[][] menuItems = model.getTravelModeItems(tp);
			//String[][] menuFlags = model.getTravelModeFlagItems(tp,menuItems);
			request.setAttribute("menuItems", menuItems);
			//request.setAttribute("menuFlags", menuFlags);
			//for lodge 
			String[][] menuLodgeItems = model.getLodgeModeItems(tp);
			//String[][] lodgeMenuFlags = model.getLodgeModeFlagItems(tp,menuLodgeItems);
			request.setAttribute("menuLodgeItems", menuLodgeItems);
			//request.setAttribute("lodgeMenuFlags", lodgeMenuFlags);

			String query = " Select POLICY_ID from TMS_POLICY_EXPENSE_DTL where POLICY_ID="
					+ tp.getPolicyId();
			Object obj[][] = model.getSqlModel().getSingleResult(query);
			if (obj.length > 0) {
				model.callExpenseList1(tp);
				model.callrestPart(tp);
				String[][] menuItems1 = model.getTravelModeItems(tp);
				String[][] menuFlags = model.getTravelModeFlagItems(tp,
						menuItems);
				request.setAttribute("menuItems", menuItems1);
				request.setAttribute("menuFlags", menuFlags);

				//for lodge 
				String[][] menuLodgeItems1 = model.getLodgeModeItems(tp);
				String[][] lodgeMenuFlags = model.getLodgeModeFlagItems(tp,
						menuLodgeItems);
				request.setAttribute("menuLodgeItems", menuLodgeItems1);
				request.setAttribute("lodgeMenuFlags", lodgeMenuFlags);
				tp.setModeEntitle("true");
				tp.setLodgemode("true");

			} else {
				model.callExpenseList(tp);

			}

			tp.setCancelFlag("true");
			getNavigationPanel(10);
			str = "details";

		} else {
			addActionMessage("No Employee Grade selected");
			model.callGradeList(tp);
			getNavigationPanel(9);
			str = "gradeview";

		}

		//getNavigationPanel(10);
		model.terminate();
		return str;
	}

	//F9 for travel mode
	public String f9ExpenseCatTravel() {
		try {
			String query = " SELECT  EXP_CATEGORY_NAME, DECODE(EXP_CATEGORY_UNIT,'D','Per Day','T','Per Tour','K','Per Kilometer'), EXP_CATEGORY_ID "
					+ " FROM HRMS_TMS_EXP_CATEGORY WHERE EXP_CATEGORY_STATUS  ='A' ORDER BY EXP_CATEGORY_NAME  ";

			String[] headers = { getMessage("expense.category.name"),
					getMessage("expense.category.unit") };

			String[] headerWidth = { "80", "20" };

			String[] fieldNames = { "expCateTravel", "expCateTravelUnit",
					"expCateTravelId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return null;
		}
	} //

	//f9method for  lodging entitle

	public String f9ExpHotel() {
		try {
			String query = " SELECT  EXP_CATEGORY_NAME, DECODE(EXP_CATEGORY_UNIT,'D','Per Day','T','Per Tour','K','Per Kilometer'), EXP_CATEGORY_ID "
					+ " FROM HRMS_TMS_EXP_CATEGORY WHERE EXP_CATEGORY_STATUS  ='A' ORDER BY EXP_CATEGORY_NAME  ";

			String[] headers = { getMessage("expense.category1"),
					getMessage("expense.unit1") };

			String[] headerWidth = { "80", "20" };

			String[] fieldNames = { "expCateHotel", "expCateHotelUnit",
					"expCateHotelId" };

			int[] columnIndex = { 0, 1, 2 };

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {
			return null;
		}
	} //

	//for heirarchi(in tree manner)
	public String getItems() {
		getNavigationPanel(10);
		System.out.println("inside method");
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		model.initiate(context, session);
		String[][] menuItems = model.getTravelModeItems(tp);
		//String[][] menuFlags = model.getTravelModeFlagItems(tp,menuItems);
		model.callExpenseList(tp);
		request.setAttribute("menuItems", menuItems);
		//request.setAttribute("menuFlags", menuFlags);
		//menuRelease.setIsTotal("true");
		model.terminate();

		return "details";
	}

	//tree for lodge mode
	public String getLodgeItems() {
		getNavigationPanel(10);
		System.out.println("inside method");
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		model.initiate(context, session);
		String[][] menuLodgeItems = model.getLodgeModeItems(tp);
		model.callExpenseList(tp);
		request.setAttribute("menuLodgeItems", menuLodgeItems);

		//menuRelease.setIsTotal("true");
		model.terminate();

		return "details";
	}

	//tree for lodge mode
	public String getLodgeItemsReset() {
		getNavigationPanel(10);
		System.out.println("inside method");
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		model.initiate(context, session);
		request.setAttribute("menuLodgeItems", null);
		model.callExpenseList(tp);
		//menuRelease.setIsTotal("true");

		model.terminate();
		return "details";
	}

	//only for view purpose go to next page i.e to grade page from non edit mode

	public String firstNext() {
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		model.initiate(context, session);
		String query = "Select POLICY_ID,POLICY_GRAD_ID from TMS_POLICY_GRADE_DTL where POLICY_ID ="
				+ tp.getPolicyId();
		Object obj[][] = model.getSqlModel().getSingleResult(query);
		//method for retriving saved  grade list when go to next page from policy form
		System.out.println("obj.length------------" + obj.length);
		if (obj.length > 0) {
			model.callNextGradeList(tp);

		}

		else {
			model.callGradeList(tp);
		}
		tp.setEditFlag("false");
		getNavigationPanel(11);
		model.terminate();
		return "gradeview";
	}

	//for go to previous page from non editable mode in grade form
	public String previousGrade() {
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		model.initiate(context, session);
		String policyid = request.getParameter("policyId");
		// model.goPreviousPage(tp);
		//model.setFieldDetails(tp);
		model.setFieldDetails1(tp);
		getNavigationPanel(5);
		tp.setEditFlag("true");
		model.terminate();
		return "success";
	}

	public String previousSecond() {
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		model.initiate(context, session);
		String policyid = request.getParameter("policyId");
		model.callNextGradeList(tp);
		getNavigationPanel(13);

		tp.setEditFlag("false");
		model.terminate();
		return "gradeview";
	}

	// save method for  the travel details 
	/*public String saveDetails()
	 {
	 getNavigationPanel(10);
	 System.out.println("enter to save details method");
	 TmsTravelPolicyModel model = new TmsTravelPolicyModel();
	
	 model.initiate(context, session);
	 // String [] exCategory =(String[])request.getParameterValues("exCategory");
	 String [] exCategoryId =(String[])request.getParameterValues("exCategoryId");
	 //String [] expCatUnit =(String[])request.getParameterValues("expCatUnit");  
	 String [] expValue =(String[])request.getParameterValues("expValue");  
	 String [] expCatUnitCode =(String[])request.getParameterValues("expCatUnitCode");  
	 String [] hidActualAtt =(String[])request.getParameterValues("hidActualAtt");  
	 String policyid=tp.getPolicyId();
	
	
	
	 System.out.println("tp.getPolicyId()----------------------"+tp.getPolicyId());
	
	 //for checkj box
	 String insertFlg[] = null;
	 String insertFlg1[] = null;
	
	 try {
	 insertFlg = (String[]) request.getParameterValues("insertChk");
	 insertFlg1=(String[]) request.getParameterValues("insertChk1");
	 for (int i = 1; i < insertFlg.length; i++) 
	
	 {
	 System.out.println("insertFlg------ "+insertFlg[i]);
	 }
	 for (int i = 1; i < insertFlg1.length; i++) 
	
	 {
	 System.out.println("insertFlg1------ "+insertFlg1[i]);
	 }
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 try {
	 String classId = null;
	 classId = insertFlg[1];
	 // String inPara1 = "";
	 for (int i = 1; i < insertFlg1.length; i++) 
	 inPara1+=insertFlg1[i]+",";
	 if(!inPara1.equals(""))
	 inPara1=inPara1.substring(0,inPara1.length()-1);
	
	 String query1="SELECT TO_CHAR(ROOM_TYPE_ID), ROOM_LEVEL "
	 +" FROM HRMS_TMS_ROOM_TYPE "
	 +"  WHERE  ROOM_LEVEL =  (select min(ROOM_LEVEL) from  HRMS_TMS_ROOM_TYPE  where ROOM_TYPE_ID IN ("+inPara1+"))" ; 
	

	 Object [][] checkObj1 = model.getSqlModel().getSingleResult(query1);
	 String roomId = null;
	 roomId = insertFlg1[1];
	 boolean flag = model.saveTravelDetails(tp, policyid, exCategoryId,
	 expValue, expCatUnitCode, hidActualAtt, classId, roomId);
	
	
	 if (flag) {
	 addActionMessage("record saved successfully");
	 model.callExpenseList1(tp);
	 tp.setActiveflag("true");
	 } else {
	 addActionMessage("record cannot be saved");

	 }
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 try {
	 String[][] menuItems = model.getTravelModeItems(tp);
	
	 String[][] menuFlags = model.getTravelModeFlagItems(tp, menuItems);
	 request.setAttribute("menuItems", menuItems);
	 request.setAttribute("menuFlags", menuFlags);
	 //for lodge 
	 String[][] menuLodgeItems = model.getLodgeModeItems(tp);
	 String[][] lodgeMenuFlags = model.getLodgeModeFlagItems(tp,
	 menuLodgeItems);
	 request.setAttribute("menuLodgeItems", menuLodgeItems);
	 request.setAttribute("lodgeMenuFlags", lodgeMenuFlags);
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 //model.callExpenseRetriveList(tp);
	 model.terminate();
	 return "details";
	 }
	 */
	public String saveDetails() {
		getNavigationPanel(10);
		System.out.println("enter to save details method");
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();

		model.initiate(context, session);
		// String [] exCategory =(String[])request.getParameterValues("exCategory");
		String[] exCategoryId = (String[]) request
				.getParameterValues("exCategoryId");
		//String [] expCatUnit =(String[])request.getParameterValues("expCatUnit");  

		//String[] expValue = (String[]) request.getParameterValues("expValue");
		String[] expValuewithBill = (String[]) request
				.getParameterValues("expValuewithBill");
		String[] expValuewithoutBill = (String[]) request
				.getParameterValues("expValuewithoutBill");

		String[] expCatUnitCode = (String[]) request
				.getParameterValues("expCatUnitCode");
		String[] hidActualAtt = (String[]) request
				.getParameterValues("hidActualAtt");
		String[] proof = (String[]) request.getParameterValues("proofId");
		System.out.println("proof id is-------------" + proof[0]);

		String[] citygrade = (String[]) request.getParameterValues("cityId");
		String policyid = tp.getPolicyId();
		//for checkj box
		String insertFlg[] = null;
		String insertFlg1[] = null;

		try {
			insertFlg = (String[]) request.getParameterValues("insertChk");
			insertFlg1 = (String[]) request.getParameterValues("insertChk1");

			for (int i = 1; i < insertFlg1.length; i++)

			{
				System.out.println("insertFlg1------ " + insertFlg1[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String classId = "";
		try {

			if (insertFlg != null) {
				try {
					classId = insertFlg[1];
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// String inPara1 = "";
			/*for (int i = 1; i < insertFlg1.length; i++) 
				inPara1+=insertFlg1[i]+",";
			if(!inPara1.equals(""))
				inPara1=inPara1.substring(0,inPara1.length()-1);
			
			String query1="SELECT TO_CHAR(ROOM_TYPE_ID), ROOM_LEVEL "
							+" FROM HRMS_TMS_ROOM_TYPE "
							+"  WHERE  ROOM_LEVEL =  (select min(ROOM_LEVEL) from  HRMS_TMS_ROOM_TYPE  where ROOM_TYPE_ID IN ("+inPara1+"))" ; 
							 

			Object [][] checkObj1 = model.getSqlModel().getSingleResult(query1);*/
			String roomId = "";
			if (insertFlg1 != null) {
				try {
					roomId = insertFlg1[1];
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			boolean flag = false;
			if (insertFlg != null && insertFlg1 != null) {

				boolean chkDetail = model.chkDtlAvailavle(tp);
				if (chkDetail) {
					flag = model.saveTravelDetails(tp, policyid, exCategoryId,
							expValuewithBill, expValuewithoutBill,
							expCatUnitCode, hidActualAtt, classId, roomId,
							proof, citygrade);

					if (flag) {
						addActionMessage("Record updated successfully.");
						model.callExpenseList1(tp);
						tp.setActiveflag("true");
					} else {
						addActionMessage("Record cannot be saved.");
						model.callExpenseList(tp);
					}

				} else {
					flag = model.saveTravelDetails(tp, policyid, exCategoryId,
							expValuewithBill, expValuewithoutBill,
							expCatUnitCode, hidActualAtt, classId, roomId,
							proof, citygrade);

					if (flag) {
						addActionMessage("Record saved successfully.");
						model.callExpenseList1(tp);
						tp.setActiveflag("true");
					} else {
						addActionMessage("Record cannot be saved.");
						model.callExpenseList(tp);
					}

				}

			}

			else {

				addActionMessage("Please select atleast one child checkBox");
				//model.callExpenseList(tp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String[][] menuItems = model.getTravelModeItems(tp);

			String[][] menuFlags = model.getTravelModeFlagItems(tp, menuItems);
			request.setAttribute("menuItems", menuItems);
			request.setAttribute("menuFlags", menuFlags);
			//for lodge 
			String[][] menuLodgeItems = model.getLodgeModeItems(tp);
			String[][] lodgeMenuFlags = model.getLodgeModeFlagItems(tp,
					menuLodgeItems);
			request.setAttribute("menuLodgeItems", menuLodgeItems);
			request.setAttribute("lodgeMenuFlags", lodgeMenuFlags);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//model.callExpenseRetriveList(tp);
		model.terminate();
		System.out.println("tp.getPolicyId()---------"+tp.getPolicyId());
		setPolicyCurrency(tp.getPolicyId());
		return "details";
	}

	//for detail page from grade page in next button
	public String secondNext() {
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		model.initiate(context, session);
		String query = "Select POLICY_ID FROM TMS_POLICY_EXPENSE_DTL where POLICY_ID ="
				+ tp.getPolicyId();
		Object obj[][] = model.getSqlModel().getSingleResult(query);
		setPolicyCurrency(tp.getPolicyId());
		//method for retriving saved  grade list when go to next page from policy form
		if (obj.length > 0) {
			model.callExpenseList1(tp);
			model.callrestPart(tp);
			String[][] menuItems = model.getTravelModeItems(tp);
			String[][] menuFlags = model.getTravelModeFlagItems(tp, menuItems);
			request.setAttribute("menuItems", menuItems);
			request.setAttribute("menuFlags", menuFlags);
			tp.setModeEntitle("true");

			//for lodge 
			String[][] menuLodgeItems = model.getLodgeModeItems(tp);
			String[][] lodgeMenuFlags = model.getLodgeModeFlagItems(tp,
					menuLodgeItems);
			request.setAttribute("menuLodgeItems", menuLodgeItems);
			request.setAttribute("lodgeMenuFlags", lodgeMenuFlags);
			tp.setLodgemode("true");

		}

		else {
			System.out.println("in else-----------------");			
			model.callExpenseList(tp);
		}
		tp.setEditFlag("false");
		tp.setNextflag("false");
		getNavigationPanel(12);
		model.terminate();
		return "details";
	}

	//call for edit for modifing from first page
	public String callEdit() 
	{
		try
		{
			TmsTravelPolicyModel model = new TmsTravelPolicyModel();
			getNavigationPanel(5);
			model.initiate(context, session);
			model.setFieldDetails1(tp);
			String query = "SELECT POLICY_ID FROM TMS_POLICY_EXPENSE_DTL Where POLICY_ID="
					+ tp.getPolicyId();
			
			Object obj[][] = model.getSqlModel().getSingleResult(query);
			if (obj.length > 0) 
			{
				tp.setActiveflag("true");

			} 
			else
				tp.setActiveflag("false");
			model.terminate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception Occured ==============> "+e);
		}
		
		tp.setEnableAll("N");
		return "success";
	}

	//for cancel the page 
	public String cancel() throws Exception {
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		String type = request.getParameter("saveType");
		String str = null;
		model.initiate(context, session);
		if (type.equals("policy")) {
			getNavigationPanel(1);
			System.out.println("inside if method-----");
			model.callListPage(tp, request);
			str = "input";
		}
		if (type.equals("grade")) {
			if (tp.getCancelFlag().equals("true")) {
				getNavigationPanel(1);
				//model.setFieldDetails1(tp);
				model.callListPage(tp, request);
				tp.setEditFlag("false");
				str = "input";
			} else {
				System.out.println("else of grade");
				getNavigationPanel(1);
				tp.setEditFlag("true");
				model.callListPage(tp, request);
				str = "input";
			}
		}
		if (type.equals("details")) {
			if (tp.getCancelFlag().equals("true")) {
				getNavigationPanel(1);
				str = "input";
				tp.setEditFlag("true");
				//model.callNextGradeList(tp);
				model.callListPage(tp, request);

			} else {
				System.out.println("else of details");
				getNavigationPanel(1);
				model.callListPage(tp, request);
				tp.setEditFlag("true");
				str = "input";
			}

			//getNavigationPanel(9);
			//model.callGradeList(tp);
			//str = "details";
		}

		model.terminate();
		return str;
	}

	//for deleting  page
	public String delete() throws Exception {
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		model.initiate(context, session);
		boolean flag = model.callDelete(tp);
		if (flag) {
			addActionMessage("Record deleted successfully.");
			reset();
		} else {
			addActionMessage("Record cannot be deleted \n as they are associated with some other records");
		}
		model.terminate();
		input();
		model.terminate();
		//getNavigationPanel(1);
		return INPUT;
	}

	public String saveAndPrevioudDetails() throws Exception {
		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		model.initiate(context, session);
		// String [] exCategory =(String[])request.getParameterValues("exCategory");
		String[] exCategoryId = (String[]) request
				.getParameterValues("exCategoryId");
		//String [] expCatUnit =(String[])request.getParameterValues("expCatUnit");  
		//String[] expValue = (String[]) request.getParameterValues("expValue");

		String[] expValuewithBill = (String[]) request
				.getParameterValues("expValuewithBill");
		String[] expValuewithoutBill = (String[]) request
				.getParameterValues("expValuewithoutBill");

		String[] expCatUnitCode = (String[]) request
				.getParameterValues("expCatUnitCode");
		String[] hidActualAtt = (String[]) request
				.getParameterValues("hidActualAtt");
		String policyid = tp.getPolicyId();
		String[] proof = (String[]) request.getParameterValues("proofId");

		String[] citygrade = (String[]) request.getParameterValues("cityId");
		System.out.println("tp.getPolicyId()----------------------"
				+ tp.getPolicyId());

		//for checkj box
		String insertFlg[] = null;
		String insertFlg1[] = null;

		try {
			insertFlg = (String[]) request.getParameterValues("insertChk");
			insertFlg1 = (String[]) request.getParameterValues("insertChk1");

		} catch (Exception e) {
			e.printStackTrace();
		}
		String inPara = "";
		String classId = "";
		try {

			if (insertFlg != null) {
				try {
					classId = insertFlg[1];
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			String roomId = "";
			roomId = insertFlg1[1];
			if (insertFlg1 != null) {
				try {
					roomId = insertFlg1[1];
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			boolean flag = model.saveTravelDetails(tp, policyid, exCategoryId,
					expValuewithBill, expValuewithoutBill, expCatUnitCode,
					hidActualAtt, classId, roomId, proof, citygrade);

			if (flag) {
				//addActionMessage("record saved successfully");
			} else {
				addActionMessage("record cannot be saved");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//model.callExpenseList(tp);
		String[][] menuItems = model.getTravelModeItems(tp);
		String[][] menuFlags = model.getTravelModeFlagItems(tp, menuItems);
		request.setAttribute("menuItems", menuItems);
		request.setAttribute("menuFlags", menuFlags);

		//for lodge 
		String[][] menuLodgeItems = model.getLodgeModeItems(tp);
		String[][] lodgeMenuFlags = model.getLodgeModeFlagItems(tp,
				menuLodgeItems);
		request.setAttribute("menuLodgeItems", menuLodgeItems);
		request.setAttribute("lodgeMenuFlags", lodgeMenuFlags);

		model.callNextGradeList(tp);
		tp.setEditFlag("true");
		getNavigationPanel(9);
		//model.callExpenseRetriveList(tp);
		model.terminate();
		return "gradeview";
	}

	//for edit policy guidlines to open editer
	public String View() {
		return "guidline";
	}

	public void beforeSaveInFirstPage() {
		String policyId = request.getParameter("policyId");
		String status = request.getParameter("status");
		String gradeList = request.getParameter("gradeList");

		System.out.println("checkActive-------------" + tp.getStatus());
		System.out.println(" I m in action policy id : " + policyId);
		System.out.println("gradeList>>>>>>>>>>." + gradeList);

		TmsTravelPolicyModel model = new TmsTravelPolicyModel();
		model.initiate(context, session);
		String responseTxt = "";
		responseTxt = model.checkGradesForPolicyInFirstPage(policyId, tp,
				status);

		//ADD NEW GRADES FOR THE CURRENT POLICY
		if (responseTxt == null && responseTxt.equals(null))
			responseTxt = "";
		//for (int i = 0; i < responseTxt.length; i++) {
		//System.out.println(" Response Grades : "+responseTxt[i][0]);
		System.out.println(" Response Gnames : " + responseTxt);
		//}

		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(responseTxt);
		} // try end
		catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
	}
		
	public void viewAttachedProof() throws Exception {
		OutputStream oStream = null;
		response.getOutputStream();
		FileInputStream fsStream = null;
		String fileName = "";
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			fileName = request.getParameter("fileName");
			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				}
			}
			// for getting server path where configuration files are saved.
			String path = getText("data_path") + "/upload/" + poolName
					+ "/Travel/" + fileName;
			oStream = response.getOutputStream();

			response.setHeader("Content-disposition", "attachment;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			addActionMessage("File not found");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		
			if (fsStream != null) {
				fsStream.close();
			}
			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}
		// return null;
	}
	
	public void setPolicyCurrency(String policyCode){
		try {
			TmsTravelPolicyModel model = new TmsTravelPolicyModel();
			model.initiate(context, session);
			String queryCur = "SELECT POLICY_TRAVEL_CURRENCY FROM TMS_TRAVEL_POLICY WHERE POLICY_ID="
					+ policyCode;
			Object[][] currencyObj = model.getSqlModel().getSingleResult(
					queryCur);
			if (currencyObj != null && currencyObj.length > 0) {
				System.out.println("String.valueOf(currencyObj[0][0])----"
						+ String.valueOf(currencyObj[0][0]));
				tp.setTravelCurrency(String.valueOf(currencyObj[0][0]));
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
