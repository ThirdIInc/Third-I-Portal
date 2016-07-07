/**
 * 
 */
package org.struts.action.D1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.paradyne.bean.D1.CDRomRequestBean;
import org.paradyne.bean.D1.TravelApplicationFormBean;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.CDRomApprovalModel;
import org.paradyne.model.D1.CDRomRequestModel;
import org.paradyne.model.D1.DepartmentandLocationChangeModel;
import org.paradyne.model.D1.TravelApplicationAppModel;
import org.paradyne.model.D1.TravelApplicationFormModel;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.struts.action.DataMigration.EmpDetailsUploadAction;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa1439
 *
 */
public class TravelApplicationFormAction extends ParaActionSupport{
	static Logger logger = Logger.getLogger(EmpDetailsUploadAction.class);
	TravelApplicationFormBean bean;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean = new TravelApplicationFormBean();
		bean.setMenuCode(2025);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	/**
	 * @return the bean
	 */
	public TravelApplicationFormBean getBean() {
		return bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(TravelApplicationFormBean bean) {
		this.bean = bean;
	}
	/** This method is called on back button */
	public String back() {
		try {
			TravelApplicationFormModel model = new TravelApplicationFormModel();
			model.initiate(context, session);
			String listType = model.checkNull(bean.getListType());
			 if ("".equals(listType)) {
	        	 input();
	         } else if (listType.equals("approved")){
	        	 getApprovedList();
	         }  else if (listType.equals("rejected")){
	        	 getRejectedList();
	         } else {
	        	input();
	         }
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}
	
	public String input() {
		try {
			TravelApplicationFormModel model = new TravelApplicationFormModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser) {
				model.getPendingList(bean, request, userId);
			}
			bean.setListType("pending");
			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return "input";
	}
	
	public String addNew() {
		try {
			TravelApplicationFormModel model=new TravelApplicationFormModel();
			model.initiate(context, session);
			reset();
			model.getEmployeeDetails(bean.getUserEmpId(),bean);
						
			
			//Second Approver
			int level=1;
			System.out.println("level---"+level);
			String userId = bean.getUserEmpId();
			String employeeCode = bean.getEmployeeCode();
			System.out.println("employeeCode--"+employeeCode);
			String nextApprover = userId;
			Object[][] empFlow = generateEmpFlow(employeeCode, "D1", level+1);

			if(empFlow != null && empFlow.length > 0) {
				nextApprover = String.valueOf(empFlow[0][0]);
				setApproverName(nextApprover);
				bean.setSecondAppFlag(true);
			}
			//For Tracking Number
		
			/* String autoCodeQuery = " SELECT NVL(MAX(TRAVEL_ID),0)+1 FROM HRMS_D1_TRAVEL_REQUEST  ";
			 Object data[][] = model.getSqlModel()
				.getSingleResult(autoCodeQuery);
			 
			
				if (data != null && data.length > 0) {
					///bean.setRequestID(String.valueOf(data[0][0]));
				}
			
			 String requestId =String.valueOf(data[0][0]);
			 System.out.println("requestId $$$$$$$$$$" + requestId);
			 getTrackingNo(requestId);*/
			 
			 //End Tracking Number
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method." + e);
		}

		//getNavigationPanel(2);
		getNavigationPanel(6);
		return SUCCESS;
	}
	
	private String getTrackingNo(String requestId) {
		System.out.println("requestId==========" + requestId);
		String trackingNo = "";

		if(requestId.length() < 4) {
			int remChars = 4 - requestId.length();

			for(int i = 0; i < remChars; i++) {
				requestId = "0" + requestId;
			}
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		
		String strDay = day < 10 ? "0" + day : String.valueOf(day);
		String strMonth = month < 10 ? "0" + month : String.valueOf(month);
		String strYear = String.valueOf(year);

		trackingNo = "AUTHTRVL-" + strYear + strMonth + strDay + "-" + requestId;
		bean.setAuthorizedToken(trackingNo);
System.out.println("trackingNo====" + trackingNo);
		return trackingNo;
	}

	/**
	 * For Selecting Employee
	 * 
	 * @return String
	 */
	public String f9Employee() {
		/*String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,To_char(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) , " 
			+" HRMS_D1_DEPARTMENT.DEPT_NUMBER,HRMS_RANK.RANK_NAME,EMP_ID, EMP_REPORTING_OFFICER "  
			+" FROM HRMS_EMP_OFFC "
			//+" Left join HRMS_D1_DEPARTMENT on(HRMS_D1_DEPARTMENT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT_NO) "
			+" Left join HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) " ;*/
		
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN ,To_char(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) , " 
			+"  HRMS_DEPT.DEPT_NAME,HRMS_RANK.RANK_NAME,EMP_ID, EMP_REPORTING_OFFICER " 
			+"  FROM HRMS_EMP_OFFC "
			+"  Left join HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK) "
			+" Left join HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=HRMS_EMP_OFFC.EMP_DEPT) ";
		
		query += getprofileQuery(bean);
		query += " AND EMP_STATUS='S'";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		
		//String[] headers = {"Employee Token",getMessage("employee"), getMessage("dept.no"),"Designation"};

		String[] headers = {"Employee ID",getMessage("employee")};
		String[] headerWidth = {"10", "30"};

		String[] fieldNames = {"employeeToken", "employeeName","deptCode","designation","employeeCode","firstApproverCode"};

		int[] columnIndex = {0, 1, 2,3,4,5};

		String submitFlag = "true";

		//String submitToMethod = "CDRomRequest_getEmployeeDetailsAction.action";
		String submitToMethod = "TravelApplication_getManagerAction.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}
	
	public String getManagerAction() {
		logger.info("in getEmployeeDetailsAction");

		TravelApplicationFormModel model = new TravelApplicationFormModel();
		model.initiate(context, session); // initialize the LoanApplicationModel class
	
		model.getManagerName(bean);
		
		//Second Approver
		int level=1;
		System.out.println("level---"+level);
		String userId = bean.getUserEmpId();
		String employeeCode = bean.getEmployeeCode();
		System.out.println("employeeCode--"+employeeCode);
		String nextApprover = userId;
		Object[][] empFlow = generateEmpFlow(employeeCode, "D1", level+1);

		if(empFlow != null && empFlow.length > 0) {
			nextApprover = String.valueOf(empFlow[0][0]);
			setApproverName(nextApprover);
			bean.setSecondAppFlag(true);
		}
		model.terminate(); // terminate the model class
		
		
		getNavigationPanel(2);
		return "success";
	}
	
	//Second Default Manager from reporting structure
	public void setApproverName(String nextApprover) {
		TravelApplicationFormModel model = new TravelApplicationFormModel();
		try {
			System.out.println("in setApp method-----");
			model.initiate(context, session);
			
				String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id, EMP_TOKEN from hrms_emp_offc "
						+ " where emp_id=" + nextApprover;
				Object data[][] = model.getSqlModel().getSingleResult(query);

				if (data != null && data.length > 0) {
					bean.setSecondApproverName(String.valueOf(data[0][0]));
					bean.setSecondApproverCode(String.valueOf(data[0][1]));
					bean.setSecondApproverToken(String.valueOf(data[0][2]));
					
					if (! (bean.getSecondApproverCode().equals(model.checkNull(bean.getApproverCode())))) {
						bean.setApproverCode(model.checkNull(String.valueOf(data[0][33])));
						bean.setApproverToken(model.checkNull(String.valueOf(data[0][34])));
						bean.setApproverName(model.checkNull(String.valueOf(data[0][35])));
					} else {
						bean.setApproverCode("");
						bean.setApproverToken("");
						bean.setApproverName("");
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.terminate();
	}
	
	/**
	 * For Selecting Approver
	 * 
	 * @return String
	 */
	public String f9Approver() {
		String str = "0";
		
		if( request.getParameter("firstApproverCode")!=null && !request.getParameter("firstApproverCode").equals("") && !request.getParameter("firstApproverCode").equals("null")) {
			str = request.getParameter("firstApproverCode");
		}else{
			str="0";
		}

		
		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	EMP_ID  FROM HRMS_EMP_OFFC ";

		//query += getprofileQuery(bean);
		if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		}
		else
		{
			query += " WHERE 1=1 ";
		}

		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN("+ bean.getEmployeeCode()+ ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = {getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {"approverToken", "approverName", "approverCode"};

		int[] columnIndex = {0, 1, 2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}
	
	//Draft 
	public String save() {
		TravelApplicationFormModel model = new TravelApplicationFormModel();

		//String status = request.getParameter("status");
		String status = bean.getStatus();
		System.out.println("status update------" + status);
		
		model.initiate(context, session);
		boolean result;
		if(bean.getTravelID().equals("")) {
			result = model.save(bean);
			
			if(result) {
				addActionMessage(getMessage("save"));

			} else {
				addActionMessage("duplicate record found");
			}
		} else {
		
			result = model.update(bean);
			if(result) {
				addActionMessage(getText("modMessage", ""));
			}// end of if
			else {
				addActionMessage("duplicate record found");
			}// end of else
		}
		// model.getList(bean, request);
		model.terminate();
		try {
			input();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method." + e);
		}

		getNavigationPanel(1);
		bean.setEnableAll("Y");

		return INPUT;
	}
	
	//Send for Approval
	
	public void prepare_withLoginProfileDetails() throws Exception {
		TravelApplicationFormModel model = new TravelApplicationFormModel();
		model.initiate(context, session);
		System.out.println("here janisha travel id--"+bean.getTravelID());
		//model.view(bean, request, bean.getTravelID());
		// input();
		model.terminate();
	}
	public String sendForApproval() {
		TravelApplicationFormModel model = new TravelApplicationFormModel();

		//String status = request.getParameter("status");
		String status = bean.getStatus();
		System.out.println("status update------" + status);
		
		model.initiate(context, session);
		String employeeCode = bean.getUserEmpId();
		boolean result;
		if(bean.getTravelID().equals("")) {
			result = model.sendForApproval(bean);
			
			if(result) {
				addActionMessage("Application sent for approver." + " \nTracking Number" +bean.getAuthorizedToken());
				
			} else {
				addActionMessage("Application can not be sent for approver.");
			}
		} else {
		
			result = model.updateSendForApproval(bean);
			if(result) {
				addActionMessage("Application sent for approver."+ " \nTracking Number" +bean.getAuthorizedToken());
				
			}// end of if
			else {
				addActionMessage("Application can not be sent for approver.");
			}// end of else
		}
		model.terminate();
		
			sendMail(employeeCode);
		
		
		try {
			input();
		} catch(Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(1);
		//bean.setEnableAll("Y");
	
		return INPUT;
	}
//For Approval 
	
	private void populateApproverComments(Object[][] apprCommentListObj) {
		List<Object> approverCommentList = new ArrayList<Object>(apprCommentListObj.length);

		for(int i = 0; i < apprCommentListObj.length; i++) {
			TravelApplicationFormBean data = new TravelApplicationFormBean();
			data.setApprName(String.valueOf(apprCommentListObj[i][0]));
			if(String.valueOf(apprCommentListObj[i][1])!=null && !String.valueOf(apprCommentListObj[i][1]).equals("null")){
				data.setApprComments(String.valueOf(apprCommentListObj[i][1]));
			}else{
				data.setApprComments("");
			}
			data.setApprDate(String.valueOf(apprCommentListObj[i][2]));
			data.setApprStatus(String.valueOf(apprCommentListObj[i][3]));
			approverCommentList.add(data);
		}

		bean.setApproverCommentList(approverCommentList);
	}
	
	
	/**
	 * For Selecting Employee draft details
	 * 
	 * @return String
	 */
	/*public void setApproverName() {
		CDRomRequestModel model = new CDRomRequestModel();
		try {

			// String claimId = request.getParameter("claimId");
			model.initiate(context, session);
			Object[][] empFlow = generateEmpFlow(bean.getUserEmpId(), "D1", 1);
			if(empFlow != null && empFlow.length > 0) {
				String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id,EMP_TOKEN from hrms_emp_offc " + " where emp_id="
					+ bean.getUserEmpId();
				Object data[][] = model.getSqlModel().getSingleResult(query);

				if(data != null && data.length > 0) {
					bean.setFirstApproverName(String.valueOf(data[0][0]));
					bean.setFirstApproverCode(String.valueOf(data[0][1]));
					bean.setFirstApproverToken(String.valueOf(data[0][2]));
				}
			} else {

			}
		} catch(Exception e) {
			// TODO: handle exception
		}
		model.terminate();
	}*/
	
	
	public void setApproverName() {
		TravelApplicationFormModel model = new TravelApplicationFormModel();
		try {

			// String claimId = request.getParameter("claimId");
			model.initiate(context, session);
			Object[][] empFlow = generateEmpFlow(bean.getUserEmpId(), "D1", 1);
			
				String query = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id,EMP_TOKEN,EMP_REPORTING_OFFICER from hrms_emp_offc " + " where emp_id="
					+ bean.getEmployeeCode();
				Object data[][] = model.getSqlModel().getSingleResult(query);
				
				if(data != null && data.length > 0) {
					
					String queryDefault = " select EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,emp_id,EMP_TOKEN,EMP_REPORTING_OFFICER from hrms_emp_offc " + " where emp_id="
					+ String.valueOf(data[0][3]);
					Object defaultObj [][] = model.getSqlModel().getSingleResult(queryDefault);
					if(defaultObj != null && defaultObj.length > 0) {
						bean.setFirstApproverName(String.valueOf(defaultObj[0][0]));
						bean.setFirstApproverCode(String.valueOf(defaultObj[0][1]));
						bean.setFirstApproverToken(String.valueOf(defaultObj[0][2]));
						
						if (! (bean.getFirstApproverCode().equals(model.checkNull(bean.getApproverCode())))) {
							bean.setApproverCode(model.checkNull(String.valueOf(data[0][33])));
							bean.setApproverToken(model.checkNull(String.valueOf(data[0][34])));
							bean.setApproverName(model.checkNull(String.valueOf(data[0][35])));
						} else {
							bean.setApproverCode("");
							bean.setApproverToken("");
							bean.setApproverName("");
						}
					}
				}
			
		} catch(Exception e) {
			// TODO: handle exception
		}
		model.terminate();
	}
	
	public String callView() {
		TravelApplicationFormModel model = new TravelApplicationFormModel();
		model.initiate(context, session);
		System.out.println("in call view Function");
		String travelId = request.getParameter("travelID");
		String status = request.getParameter("sendBack");
		getNavigationPanel(2);
		System.out.println("outside if send back---"+status);
		
		try {
			if (status.equals("sendBack") || status.equals("draft")) {
				System.out.println("inside if send back---" + status);
				getNavigationPanel(2);
				bean.setDraftFlag(true);
				bean.setEnableAll("Y");
				bean.setApplnStatus("D");
			} else {
				bean.setWorldTravelFlag(true);
				//bean.setForApproval(true);
				bean.setForwardManager(true);
				bean.setEnableAll("N");
				getNavigationPanel(4);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		/*
		 * FOR SUPER USER
		 */
		String applCode=request.getParameter("applCode");
		if(applCode !=null &&applCode.length()>0){
			travelId=applCode;
		}

		Object[][] apprCommentListObj = model.getApproverCommentList(travelId);
		populateApproverComments(apprCommentListObj);
		
		model.view(bean, request, travelId);
		setApproverName();
		int level=1;
		String userId = bean.getUserEmpId();
		String employeeCode = bean.getEmployeeCode();
		String nextApprover = userId;
		Object[][] empFlow = generateEmpFlow(employeeCode, "D1", level+1);

		if(empFlow != null && empFlow.length > 0) {
			nextApprover = String.valueOf(empFlow[0][0]);
			setApproverName(nextApprover);
			bean.setSecondAppFlag(true);
		}
		
		model.terminate();
		
		if(applCode !=null &&applCode.length()>0){
			//getNavigationPanel(0);
			//bean.setEnableAll("N");
			System.out.println("bean.getApplnStatus()===" + bean.getApplnStatus());
			if(bean.getApplnStatus().equals("D") || bean.getApplnStatus().equals("A")|| bean.getApplnStatus().equals("B")|| bean.getApplnStatus().equals("R"))
			{
				getNavigationPanel(0);
				bean.setEnableAll("N");
			}else
				{
				//bean.setWorldTravelFlag(true);
				//bean.setForApproval(true);
				bean.setForwardManager(true);
				getNavigationPanel(7);
				bean.setEnableAll("N");
					
					
				}
			
		}

	   /*  if(!status.equals("D") ||!status.equals("B")) {
	    	 TravelApplicationFormModel apprModel = new TravelApplicationFormModel();
			apprModel.initiate(context, session);

			if(status.equals("B")) {
				bean.setReadOnlyDetails(false);
			} else {
				bean.setReadOnlyDetails(true);
			}
			if(status.equals("F") ||status.equals("A")|| status.equals("P") || status.equals("B") || status.equals("R")) { 
				bean.setWorldTravelFlag(false);
			}
			
			apprModel.terminate();
		}*/
		
		
		
		return SUCCESS;
	}
	
	/**
	 * To delete record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		TravelApplicationFormModel model = new TravelApplicationFormModel();
		model.initiate(context, session);
		boolean result;
		result = model.delete(bean, request);
		if (result) {
			addActionMessage("Record Deleted successfully.");
		}else{
			addActionMessage("Record can not be deleted.");
		}

		model.terminate();
		try {
			input();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Exception occurred in delete method." + e);
		}

		getNavigationPanel(1);

		return INPUT;
	}
	
	/** This method is called on reset button */
	public String reset() {
		//Employee Information
		bean.setTravelID("");
		bean.setHiddenCode("");
		bean.setEmployeeCode("");
		bean.setEmployeeToken("");
		bean.setEmployeeName("");
	
		//Approver Details
		bean.setFirstApproverCode("");
		bean.setFirstApproverToken("");
		bean.setFirstApproverName("");
		bean.setSecondApproverCode("");
		bean.setSecondApproverToken("");
		bean.setSecondApproverName("");
		bean.setApproverCode("");
		bean.setApproverToken("");
		bean.setApproverName("");
		
		//Form  feilds
		bean.setEmpTravel("");
		bean.setDeptCode("");
		bean.setDateOfAtr("");
		bean.setFromDate("");
		bean.setToDate("");
		bean.setDesignation("");
		bean.setCustomer("");
		bean.setManagmentTraining("");
		bean.setOther("");
		bean.setTraining("");
		bean.setAcquisition("");
		bean.setComments("");
		bean.setAir("");
		bean.setCar("");
		bean.setHotel("");
		bean.setLowCost("");
		bean.setConnections("");
		bean.setAtr("");
		bean.setTimes("");
		bean.setNotBooked("");
		bean.setCarrierPreference("");
		bean.setOtherChk("");
		bean.setNonRefundable("");
		
		bean.setAirExp("");
		bean.setCarExp("");
		bean.setHotelExp("");
		bean.setMealExp("");
		bean.setOtherExp("");
		bean.setTotalAirExp("");
		bean.setTotalCarExp("");
		bean.setTotalHotelExp("");
		bean.setTotalOtherExp("");
		bean.setTotalMealExp("");
		bean.setTotalValue("");
		getNavigationPanel(2);
		return "success";

	}
	
	public String getApprovedList() throws Exception {
		try {
			TravelApplicationFormModel model = new TravelApplicationFormModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser) {
				model.getApprovedList(bean, request, userId);
			}
			 bean.setListType("approved");
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in getApprovedList------" + e);
		}
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String getRejectedList() throws Exception {
		try {
			TravelApplicationFormModel model = new TravelApplicationFormModel();
			model.initiate(context, session);
			String userId = bean.getUserEmpId();
			boolean isCurrentUser = model.isCurrentUser(userId);
			if(isCurrentUser) {
				model.getRejectedList(bean, request, userId);
			}
			bean.setListType("rejected");
			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return INPUT;
	}
	
	//Start For Approval
	
	public String getDefaultManagerName() throws Exception {
		TravelApplicationFormModel model = new TravelApplicationFormModel();
		model.initiate(context, session);
		model.getDefaultManagerName(bean);
		// addDetRecord();
		getNavigationPanel(2);
		model.terminate();
		return SUCCESS;
	}
	public String viewDetails() {
		try {
			String travelId = request.getParameter("travelId");
			bean.setForApproval(true);
			bean.setTravelID(travelId);
			final String applCode = request.getParameter("applCode");
			TravelApplicationFormModel model = new TravelApplicationFormModel();
			model.initiate(context, session);
			
			/*
			 * FOR SUPER USER
			 */
			
			if(applCode !=null &&applCode.length()>0){
				travelId=applCode;
			}
			
			Object[][] persDataObj = model.getTravelDetails(travelId);

			Object[][] apprCommentListObj = model.getApproverCommentList(travelId);
			populateApproverComments(apprCommentListObj);

			
			
			TravelApplicationFormModel travelRequestModel = new TravelApplicationFormModel();
			travelRequestModel.initiate(context, session);
			travelRequestModel.view(bean, request, travelId);
			travelRequestModel.terminate();
			//setApproverName();
			getDefaultManagerName();

			String status = String.valueOf(persDataObj[0][2]);
			boolean readOnlyDetails = false;
			
		
			
			/*if(status.equals("P") || status.equals("C")) {
				getNavigationPanel(5);
			} else if(status.equals("F")) {
				String userId = bean.getUserEmpId();
				boolean isUserAHRApprover = model.isUserAHRApprover(userId);

				if(isUserAHRApprover) {
					getNavigationPanel(5);
				} else {
					readOnlyDetails = true;
					getNavigationPanel(2);
				}
			} else {
				readOnlyDetails = true;
				//getNavigationPanel(2);
				getNavigationPanel(4);
			}*/
			
			if(status.equals("P") || status.equals("C") || status.equals("F")) {
				getNavigationPanel(5);
				
				bean.setWorldTravelFlag(true);
				bean.setForApproval(true);
				bean.setForwardManager(true);
			}else{
				getNavigationPanel(4);
				bean.setWorldTravelFlag(false);
				bean.setForApproval(false);
				bean.setForwardManager(false);
			}
			
			if(bean.getFlag().equals("AA")||bean.getFlag().equals("RR")){
				getNavigationPanel(4);
				bean.setWorldTravelFlag(false);
				bean.setForApproval(false);
				bean.setForwardManager(false);
			}
			if(bean.getListType().equals("approved"))
			{
				getNavigationPanel(4);
				bean.setWorldTravelFlag(false);
				bean.setForApproval(false);
				bean.setForwardManager(false);
			}
			bean.setEnableAll("N");
			//bean.setReadOnlyDetails(readOnlyDetails);
			model.terminate();
			
			if(applCode !=null &&applCode.length()>0){
			
				if(status.equals("D")|| status.equals("A")|| status.equals("B")|| status.equals("R"))
				{
					bean.setEnableAll("N");
					getNavigationPanel(8);
				}else{
					bean.setWorldTravelFlag(false);
					bean.setForApproval(true);
				bean.setForwardManager(false);
						getNavigationPanel(7);
						bean.setEnableAll("N");
					}
			}
			
			return "view";
		} catch(Exception e) {
			logger.error(e);
			return "";
		}
	}
	
	public String approve() {
		String travelId = bean.getTravelID();
		String approverComments = bean.getApproverComments();
		String userId = bean.getUserEmpId();
		String employeeCode = bean.getEmployeeCode();
		String status = bean.getApplnStatus();
		int level = Integer.parseInt(bean.getLevel());

		TravelApplicationAppModel model = new TravelApplicationAppModel();
		model.initiate(context, session);

		String nextApprover = userId;

		Object[][] empFlow = generateEmpFlow(employeeCode, "D1", (level + 1));

		if(empFlow != null && empFlow.length > 0) {
			nextApprover = String.valueOf(empFlow[0][0]);
			level += 1;
		}else{
			
		}
		//String emailId=bean.getEmailWorldTravel().trim();
		//System.out.println("Here email id---"+emailId);
		String emailId="";
		status = model.approve(travelId, approverComments, userId, status, nextApprover, level,emailId);
		
		model.terminate();
		
		System.out.println("Status here janisha --"+status);
		
		//Start Mail
		sendApprovalMail(travelId, userId, employeeCode, status);
		String email="";
		sendFinalMail(travelId, userId, employeeCode, status,email);
		//End Mail;
		
		//Finace/group mail
		if(status.equals("F")){
			sendfinaceGroupMail(travelId, userId, employeeCode, status,email);
		}
		//end 
		if(status.equals("F")){
			sendApprovalMailToInititor(travelId, userId, employeeCode, status);
		}
		
		bean.setForApproval(true);
		//bean.setApplnStatus(status);
		bean.setTravelID("");
		
		addActionMessage("Application approved successfully.");
		String str="Application approved successfully.";
		 request.setAttribute("messageStr", str);
		getNavigationPanel(5);
		
		//return SUCCESS;approve
		
		return "approve";
	}
	
	
	public String forward() {
		String travelId = bean.getTravelID();
		String approverComments = bean.getApproverComments();
		String userId = bean.getUserEmpId();
		String employeeCode = bean.getEmployeeCode();
		String status = bean.getApplnStatus();
		int level = Integer.parseInt(bean.getLevel());
		String nextApprover="";
		TravelApplicationAppModel model = new TravelApplicationAppModel();
		model.initiate(context, session);
		nextApprover=bean.getForwardApproverCode().trim();
		status = model.forward(travelId, approverComments, userId, status,nextApprover);
		//addActionMessage("Application forwarded successfully.");
		
		model.terminate();
		forwardMail(travelId, userId, employeeCode, status);
		sendForwardMailToInititor(travelId, userId, employeeCode, status);
		bean.setForApproval(true);
		//bean.setApplnStatus(status);
		bean.setTravelID("");
		String str="Application forwarded successfully.";
		request.setAttribute("messageStr", str);
		addActionMessage("Application forwarded successfully."); 
		getNavigationPanel(5);
		//return SUCCESS;approve
		return "approve";
	}
	
	//Mail forward
	private void forwardMail(String travelId, String userId, String employeeCode, String status) {

		try {
			
			System.out.println("travelId----"+travelId);
			System.out.println("forward APPROVAL MAIL----"+bean.getForwardApproverCode());
			
			try {
				TravelApplicationFormModel model = new TravelApplicationFormModel();
				// MAIL FROM APPROVER TO MANAGER
				model.initiate(context, session);
				

				/*String query = " SELECT NVL(APP_SETTINGS_EMP_ID,0) AS MANAGER_ID , EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS MANAGER_NAME "
					+ " FROM HRMS_D1_APPROVAL_SETTINGS "
					+ " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID)"
					+ " WHERE APP_SETTINGS_TYPE = 'H'  ORDER BY APP_SETTINGS_EMP_ID";*/
				
				String query = " SELECT TRAVEL_ID AS MAIN_ID , "
					+ " TO_CHAR(OFFC.EMP_FNAME || ' ' || OFFC.EMP_MNAME || ' ' || OFFC.EMP_LNAME) AS FORWARD_NAME "
					+ " FROM HRMS_D1_TRAVEL_DATA_PATH PATH "
					+ " INNER JOIN HRMS_EMP_OFFC OFFC ON (OFFC.EMP_ID = PATH.TRAVEL_APPROVER) "
					+ " WHERE TRAVEL_ID ="+ travelId + " ORDER BY TRAVEL_DATE DESC " ;
				
				
				Object data[][] = model.getSqlModel().getSingleResult(query);
				System.out.println("manager to---"+bean.getForwardApproverCode());
				EmailTemplateBody templateApp = new EmailTemplateBody();
				templateApp.initiate(context, session);
				templateApp.setEmailTemplate("D1-AUTHORIZED TRAVEL REQUEST APPLICATION DETAILS FORWARDED FROM  APPROVER TO MANAGER");
				templateApp.getTemplateQueries();
				EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
				templateQueryApp1.setParameter(1, userId);
				
				EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
				templateQueryApp2.setParameter(1, bean.getForwardApproverCode());
				EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
				templateQueryApp3.setParameter(1, travelId);
				EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
				templateQueryApp4.setParameter(1, travelId);
				
				EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
				templateQueryApp5.setParameter(1, travelId);
				
				EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
				templateQueryApp6.setParameter(1,bean.getForwardApproverCode());
				
				EmailTemplateQuery templateQueryApp7 = templateApp.getTemplateQuery(7);
				templateQueryApp7.setParameter(1,travelId);
				

				String[] empData = null;
				System.out.println("here"+data[0][0]);
				System.out.println("here"+data.length);
				if(data != null && data.length > 0) {
					empData = new String[data.length];
					for(int i = 1; i < empData.length; i++) {
						empData[i] = String.valueOf(data[i][0]);
					}
				}
				//System.out.println("empData = " + empData.length);
				
				
				
				templateApp.configMailAlert();
				
				templateApp.sendApplicationMailToKeepInfo(empData);

				templateApp.sendApplicationMail();
				templateApp.clearParameters();
				templateApp.terminate();

				model.terminate();

			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			logger.error(e);
		}

	}
	//end mail forward
	
	public String reject() {
		String travelId = bean.getTravelID();
		String approverComments = bean.getApproverComments();
		String employeeCode = bean.getEmployeeCode();
		String userId = bean.getUserEmpId();
		String status = bean.getApplnStatus();

		TravelApplicationAppModel model = new TravelApplicationAppModel();
		model.initiate(context, session);
		model.reject(travelId, approverComments, userId);

		Object[][] apprCommentListObj = model.getApproverCommentList(travelId);
		populateApproverComments(apprCommentListObj);

		model.terminate();

		//addActionMessage("Application rejected successfully.");
		
		//Mail Integration
	    sendRejectSenBackMailToApplicant(travelId, userId, employeeCode, status);
	    sendApprovalMailToInititor(travelId, userId, employeeCode, status);
	    //End Mail Integration
		bean.setForApproval(true);
		String str="Application rejected successfully.";
		 request.setAttribute("messageStr", str);
		getNavigationPanel(5);
		//return SUCCESS;
		return "approve";
	}
	
	public String sendBack() {
		String travelId = bean.getTravelID();
		String approverComments = bean.getApproverComments();
		String employeeCode = bean.getEmployeeCode();
		String userId = bean.getUserEmpId();

		TravelApplicationAppModel model = new TravelApplicationAppModel();
		model.initiate(context, session);

		Object[][] empFlow = generateEmpFlow(employeeCode, "D1", 1);
		String nextApprover = userId;
		
		if(empFlow != null && empFlow.length > 0) {
			nextApprover = String.valueOf(empFlow[0][0]);
		}
		String status = model.sendBack(travelId, approverComments, userId, nextApprover);

		Object[][] apprCommentListObj = model.getApproverCommentList(travelId);
		populateApproverComments(apprCommentListObj);

		bean.setApplnStatus(status);
		bean.setReadOnlyDetails(true);
		bean.setApproverComments("");
		model.terminate();

		//addActionMessage("Application send back successfully.");
		//Mail Integration 
		sendRejectSenBackMailToApplicant(travelId, userId, employeeCode, status);
		sendApprovalMailToInititor(travelId, userId, employeeCode, status);
		//End Mail Integration
		
		String str="Application send back successfully.";
		 request.setAttribute("messageStr", str);
		getNavigationPanel(2);

		//return SUCCESS;
		return "approve";
	}
	//End For Approval
	//Start Mail Integration
	private void sendApprovalMail(String travelId, String userId, String employeeCode, String status) {

		try {
			System.out.println("SEND APPROVAL MAIL");
			// if(status.equals("approved")){

			// MAIL FROM APPROVER TO APPLICANT

			try {
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template.setEmailTemplate("D1-AUTHORIZED TRAVEL REQUEST APPLICATION  FROM  APPROVER TO APPLICANT");
				template.getTemplateQueries();
				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, userId);
				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1, employeeCode);
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, travelId);
				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, travelId);
				
				EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, travelId);
				
				template.configMailAlert();
				// template.sendProcessManagerAlert(approver, module, msgType,
				// applnID, level);
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
				
			} catch(Exception e) {
				e.printStackTrace();
			}

			
		
		} catch(Exception e) {
			logger.error(e);
		}

	}
	
	private void sendApprovalMailToApplicantFinal(String travelId, String userId, String employeeCode, String status) {
		try {
			CDRomRequestModel model = new CDRomRequestModel();
			// MAIL FROM APPROVER TO MANAGER
			model.initiate(context, session);
			String query = " SELECT NVL(APP_SETTINGS_EMP_ID,0) AS MANAGER_ID , EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS MANAGER_NAME "
				+ " FROM HRMS_D1_APPROVAL_SETTINGS "
				+ " INNER JOIN HRMS_EMP_OFFC on(HRMS_EMP_OFFC.EMP_ID = HRMS_D1_APPROVAL_SETTINGS.APP_SETTINGS_EMP_ID)"
				+ " WHERE APP_SETTINGS_TYPE = 'H'  ORDER BY APP_SETTINGS_EMP_ID";
			Object data[][] = model.getSqlModel().getSingleResult(query);
			EmailTemplateBody templateApp = new EmailTemplateBody();
			templateApp.initiate(context, session);
			templateApp.setEmailTemplate("D1-AUTHORIZED TRAVEL REQUEST APPLICATION DETAILS APPROVE/REJECTED/SENDBACK FROM  HR");
			templateApp.getTemplateQueries();
			EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
			templateQueryApp1.setParameter(1, userId);
			EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
			templateQueryApp2.setParameter(1, employeeCode);
			EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
			templateQueryApp3.setParameter(1, travelId);
			EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
			templateQueryApp4.setParameter(1, travelId);
			
			EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
			templateQueryApp5.setParameter(1, travelId);
			
			EmailTemplateQuery templateQueryApp6 = templateApp.getTemplateQuery(6);
			templateQueryApp6.setParameter(1, String.valueOf(data[0][0]));

			templateApp.configMailAlert();

			templateApp.sendApplicationMail();
			templateApp.clearParameters();
			templateApp.terminate();

			model.terminate();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	private void sendRejectSenBackMailToApplicant(String travelId, String userId, String employeeCode, String status) {
		try {
			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);
			template.setEmailTemplate("D1-AUTHORIZED TRAVEL REQUEST APPLICATION DETAILS REJECTED/SENDBACK FROM  APPROVER");
			template.getTemplateQueries();
			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			// EMAIL
			templateQuery1.setParameter(1, userId);
			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			// EMAIL
			templateQuery2.setParameter(1, employeeCode);
			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, travelId);
			
			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, travelId);
			
			
			EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
			templateQuery5.setParameter(1, travelId);
			template.configMailAlert();
			// template.sendProcessManagerAlert(approver, module, msgType,
			// applnID, level);
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();
			template.getTemplateQueries();
			template.clearParameters();
			template.terminate();

		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public void sendMail(String employeeCode) {

		try {
			System.out.println("applicationID in mail---"+ bean.getTravelID());
			String applicationID = bean.getTravelID();
			System.out.println("applicationID in mail---"+applicationID);
			//String approverID = String.valueOf(employeeCode[0][1]);

			// Mail From Applicant to Approver

			EmailTemplateBody template = new EmailTemplateBody();
			template.initiate(context, session);

			template.setEmailTemplate("D1-AUTHORIZED TRAVEL REQUEST APPLICATION DETAILS TO APPROVER");

			template.getTemplateQueries();

			EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM
			// EMAIL
			templateQuery1.setParameter(1, employeeCode);
			

			EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO
			// EMAIL
			System.out.println("bean.getApproverCode()---"+bean.getApproverCode());

			System.out.println("bean.getApproverCode()---"+bean.getFirstApproverCode());
			String approverCode = "0";
			if(!bean.getApproverCode().equals("")) {
				approverCode = bean.getApproverCode();
			} else {
				approverCode = bean.getFirstApproverCode();
			}
			System.out.println("approverCode--"+approverCode);
			templateQuery2.setParameter(1, approverCode);

			EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
			templateQuery3.setParameter(1, applicationID);

			EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
			templateQuery4.setParameter(1, applicationID);

			template.configMailAlert();
			// template.sendProcessManagerAlert(approver, module, msgType,
			// applnID, level);
			template.sendApplicationMail();
			template.clearParameters();
			template.terminate();

			// Mail From Applicant to Approver End

		} catch(Exception e) {
			logger.error(e);
		}

	}

	
	
	private void sendFinalMail(String travelId, String userId, String employeeCode, String status,String emailAddr) {

		try {
			System.out.println("SEND fixcvxcvxvdfdfnal MAIL");
			// if(status.equals("approved")){
			System.out.println("SEND final MAIL final email---");
			// MAIL FROM APPROVER TO APPLICANT
			try {
				TravelApplicationFormModel model = new TravelApplicationFormModel();
				// MAIL FROM APPROVER TO MANAGER
				model.initiate(context, session);
			
				String emailQuery="select APP_EMAIL_ID from HRMS_D1_APPROVAL_SETTINGS where APP_SETTINGS_TYPE='W' AND APP_EMAIL_ID IS NOT NULL";
				Object[][] emailQueryObj =model.getSqlModel().getSingleResult(emailQuery);
				String value="";
				if(emailQueryObj!=null && emailQueryObj.length>0){
					emailAddr=String.valueOf(emailQueryObj[0][0]);
				}
				
				System.out.println("email---"+emailAddr);
				EmailTemplateBody templateApp = new EmailTemplateBody();
				templateApp.initiate(context, session);
				templateApp.setEmailTemplate("D1-AUTHORIZED TRAVEL REQUEST APPLICATION DETAILS");
				templateApp.getTemplateQueries();
				EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
				templateQueryApp1.setParameter(1, userId);
				EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
				templateQueryApp2.setParameter(1,emailAddr.trim());
				EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
				templateQueryApp3.setParameter(1, travelId);
				EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
				templateQueryApp4.setParameter(1, travelId);
				
				EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
				templateQueryApp5.setParameter(1, travelId);
				
				//Added on 1 APril 2011
				// Add approval link -pass parameters to the link
				String level = "1";
				String[] link_param = new String[3];
				String[] link_label = new String[3];
				String applicationType = "AuthorizedTravelForm";
				link_param[0] = applicationType + "#" + travelId + "#"
						 + "A" + "#" + "..." + "#"
						+ emailAddr.trim() + "#" + "#" + userId + "#" + employeeCode + "#";
				link_param[1]  = applicationType + "#" + travelId + "#"
				 + "R" + "#" + "..." + "#"
					+ emailAddr.trim() + "#" + "#" + userId + "#" + employeeCode + "#";
				link_param[2]   = applicationType + "#" + travelId + "#"
				 + "B" + "#" + "..." + "#"
					+ emailAddr.trim() + "#" + "#" + userId + "#" + employeeCode + "#";
				link_label[0] = "Approve";
				link_label[1] = "Reject";
				link_label[2] = "Send Back";
				//End added on 1 April 2011
				
				//Keep inform
				String[] empData = new String[emailQueryObj.length];
				if (emailQueryObj != null && emailQueryObj.length > 1) {
					empData = new String[emailQueryObj.length];
					for (int i = 1; i < empData.length; i++) {
						empData[i] = String.valueOf(emailQueryObj[i][0]);
					}
				}
				//end keep inform
				templateApp.configMailAlert();
				templateApp.addOnlineLink(request, link_param, link_label);
				//templateApp.sendApplicationMailToKeepInfo(empData);
				String[] attdata = null;
				//templateApp.sendMailToCCEmailIdsWithAttachment(empData, attdata);
				if(emailQueryObj!=null &&emailQueryObj.length>0){
				templateApp.sendApplicationMailToGroups(emailQueryObj);
				}
				
				//templateApp.sendApplicationMail();
				templateApp.clearParameters();
				templateApp.terminate();

				model.terminate();

			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			logger.error(e);
		}

	}
	//End Mail Integration
	
	//For Forward 
	/**
	 * For Selecting Approver
	 * 
	 * @return String
	 */
	public String f9ForwardApprover() {
		String str = "0";

		/*if(!bean.getFirstApproverCode().equals("")) {
			str = bean.getFirstApproverCode();
		}*/

		String query = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME , " + "	EMP_ID  FROM HRMS_EMP_OFFC ";

		//query += getprofileQuery(bean);
		if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
			query += " WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getUserProfileDivision() + ")";
		}
		else
		{
			query += " WHERE 1=1 ";
		}
		query += " AND EMP_STATUS='S' AND EMP_ID NOT IN(" + bean.getEmployeeCode() + ","+ bean.getApproverCode() + ")";
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = {getMessage("employee.id"), getMessage("employee")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {"forwardApproverToken", "forwardApproverName", "forwardApproverCode"};

		int[] columnIndex = {0, 1, 2};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}
	
	//end Forward
	private void sendApprovalMailToInititor(String travelId, String userId, String employeeCode, String status) {

		try {
			System.out.println("SEND Initiotor MAIL---"+travelId);
			// if(status.equals("approved")){

			// MAIL FROM APPROVER TO APPLICANT


			//Initiator
			
			try {
				
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				TravelApplicationFormModel model=new TravelApplicationFormModel();
				model.initiate(context, session);
				String finInititor="Select Trim(TRAVEL_INITIATOR) from  HRMS_D1_TRAVEL_REQUEST where TRAVEL_ID="+travelId;
				//Object finInititorObj[][]=model.getSqlModel().getSingleResult(finInititor);
				Object[][] finInititorObj =model.getSqlModel().getSingleResult(finInititor);
				String inititorId="";
				if(finInititorObj!=null && finInititorObj.length>0){
					inititorId=String.valueOf(finInititorObj[0][0]).trim();
				}
				template.setEmailTemplate("D1-AUTHORIZED TRAVEL REQUEST APPLICATION  FROM  APPROVER TO INITIATOR");
				template.getTemplateQueries();
				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, userId);
				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1,inititorId.trim());
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, travelId);
				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, travelId);
				
				EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, travelId);
				
				template.configMailAlert();
				// template.sendProcessManagerAlert(approver, module, msgType,
				// applnID, level);
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			//End Inititor
		
		} catch(Exception e) {
			logger.error(e);
		}

	}
	
	//Added on 1 April 2011
	private void sendfinaceGroupMail(String travelId, String userId, String employeeCode, String status,String emailAddr) {

		try {
			System.out.println("SEND finace group MAIL");
			// if(status.equals("approved")){
			System.out.println("SEND final MAIL final email---"+emailAddr);
			// MAIL FROM APPROVER TO APPLICANT
			try {
				TravelApplicationFormModel model = new TravelApplicationFormModel();
				// MAIL FROM APPROVER TO MANAGER
				model.initiate(context, session);
			
				String emailQuery="SELECT APP_EMAIL_ID  FROM HRMS_D1_APPROVAL_SETTINGS  WHERE APP_SETTINGS_TYPE = 'R' AND APP_EMAIL_ID IS NOT NULL ORDER BY APP_SETTINGS_ID ";
				Object[][] emailQueryObj =model.getSqlModel().getSingleResult(emailQuery);
				/*String valueFinace="";
				if(emailQueryObj!=null && emailQueryObj.length>0){
					valueFinace=String.valueOf(emailQueryObj[0][0]);
				}*/
				
				System.out.println("email---"+emailAddr);
				EmailTemplateBody templateApp = new EmailTemplateBody();
				templateApp.initiate(context, session);
				templateApp.setEmailTemplate("D1-AUTHORIZED TRAVEL REQUEST APPLICATION DETAILS");
				templateApp.getTemplateQueries();
				EmailTemplateQuery templateQueryApp1 = templateApp.getTemplateQuery(1); // FROM EMAIL
				templateQueryApp1.setParameter(1, userId);
				EmailTemplateQuery templateQueryApp2 = templateApp.getTemplateQuery(2); // TO EMAIL
				templateQueryApp2.setParameter(1,"1");
				EmailTemplateQuery templateQueryApp3 = templateApp.getTemplateQuery(3);
				templateQueryApp3.setParameter(1, travelId);
				EmailTemplateQuery templateQueryApp4 = templateApp.getTemplateQuery(4);
				templateQueryApp4.setParameter(1, travelId);
				
				EmailTemplateQuery templateQueryApp5 = templateApp.getTemplateQuery(5);
				templateQueryApp5.setParameter(1, travelId);
				
				templateApp.configMailAlert();
				
				//Keep inform
				//String[] empData = null;
				/*String[] empData = new String[emailQueryObj.length];
				if (emailQueryObj != null && emailQueryObj.length > 0) {
					empData = new String[emailQueryObj.length];
					for (int i = 0; i < empData.length; i++) {
						empData[i] = String.valueOf(emailQueryObj[i][1]);
					}
				}*/
				//end keep inform
				
				
				//templateApp.sendApplicationMailToKeepInfo(empData);
				
				if(emailQueryObj !=null && emailQueryObj.length>0){
				templateApp.sendApplicationMailToGroups(emailQueryObj);
				}
				//templateApp.sendApplicationMail();
				templateApp.clearParameters();
				templateApp.terminate();

				model.terminate();

			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			logger.error(e);
		}

	}
//Forward to Inititor
	private void sendForwardMailToInititor(String travelId, String userId, String employeeCode, String status) {

		try {
			System.out.println("SEND Initiotor MAIL---"+travelId);
			// if(status.equals("approved")){

			// MAIL FROM APPROVER TO APPLICANT


			//Initiator
			
			try {
				
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				TravelApplicationFormModel model=new TravelApplicationFormModel();
				model.initiate(context, session);
				String finInititor="Select Trim(TRAVEL_INITIATOR) from  HRMS_D1_TRAVEL_REQUEST where TRAVEL_ID="+travelId;
				//Object finInititorObj[][]=model.getSqlModel().getSingleResult(finInititor);
				Object[][] finInititorObj =model.getSqlModel().getSingleResult(finInititor);
				String inititorId="";
				if(finInititorObj!=null && finInititorObj.length>0){
					inititorId=String.valueOf(finInititorObj[0][0]).trim();
				}
				template.setEmailTemplate("D1-AUTHORIZED TRAVEL REQUEST FORWARDED APPLICATION  FROM  APPROVER TO INITIATOR");
				template.getTemplateQueries();
				EmailTemplateQuery templateQuery1 = template.getTemplateQuery(1); // FROM EMAIL
				templateQuery1.setParameter(1, userId);
				EmailTemplateQuery templateQuery2 = template.getTemplateQuery(2); // TO EMAIL
				templateQuery2.setParameter(1,inititorId.trim());
				EmailTemplateQuery templateQuery3 = template.getTemplateQuery(3);
				templateQuery3.setParameter(1, travelId);
				EmailTemplateQuery templateQuery4 = template.getTemplateQuery(4);
				templateQuery4.setParameter(1, travelId);
				
				EmailTemplateQuery templateQuery5 = template.getTemplateQuery(5);
				templateQuery5.setParameter(1, travelId);
				
				template.configMailAlert();
				// template.sendProcessManagerAlert(approver, module, msgType,
				// applnID, level);
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			//End Inititor
		
		} catch(Exception e) {
			logger.error(e);
		}

	}
	
	/*
	 * For Selecting Approver
	 * 
	 * @return String
	 */
	public String f9deptNumber() {
		

		 String query = "SELECT DEPT_ID, DEPT_NAME FROM HRMS_DEPT  ";

         if (bean.getUserProfileDivision()!=null && bean.getUserProfileDivision().length() > 0) {
				query += " WHERE DEPT_DIV_CODE IN ("
						+ bean.getUserProfileDivision() + ")";
			}
         query += " ORDER BY DEPT_ID DESC";

		String[] headers = { getMessage("dept.id"),getMessage("deptName")};

		String[] headerWidth = {"20", "80"};

		String[] fieldNames = {  "deptCode","deptName"};

		int[] columnIndex = { 0,1};

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";

	}
	
}
