package org.struts.action.recruitment;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.paradyne.bean.Recruitment.InterviewAssessMaster;
import org.paradyne.model.recruitment.InterviewAssessMasterModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Nilesh Dhandare
 */

/**
 * @author aa1380 : Modified on 06th July 2012
 * @modified by @author Prajakta.Bhandare 
 * @date 05 march 2013
 */
public class InterviewAssessMasterAction extends ParaActionSupport {

	/** * logger. */
	private Logger logger = Logger.getLogger(InterviewAssessMasterAction.class);
	/** * intwassMaster. */
	private InterviewAssessMaster intwassMaster;
	/** * messageStr. */
	private String messageStr = "One or more records can't be deleted \n as they are associated with some other records. ";
	/** yestStr. * */
	private String yesStr = "Y";
	/** noStr. * */
	private String noStr = "N";
	/** addNewStr. * */
	private String addNewStr = "addnew";
	/** addnewGroupStr. * */
	private String addnewGroupStr = "addnewGroup";
	/** trueStr. * */
	private String trueStr = "true";
	/** addNewParameterStr. * */
	private String addNewParameterStr = "addNewParameter";
	
	/**
	 * prepare_local.
	 * used to set menucode
	 */
	public void prepare_local() throws Exception {
		this.intwassMaster = new InterviewAssessMaster();
		this.intwassMaster.setMenuCode(1079);
	}

	/**
	 * getModel.
	 * used to return bean instance
	 * @return Object
	 */
	public Object getModel() {
		return this.intwassMaster;
	}

	/**getIntwassMaster.
	 * @return bean
	 */
	public InterviewAssessMaster getIntwassMaster() {
		return this.intwassMaster;
	}

	/**setIntwassMaster.
	 * @param intwassMaster : intwassMaster
	 */
	public void setIntwassMaster(final InterviewAssessMaster intwassMaster) {
		this.intwassMaster = intwassMaster;
	}

	/**
	 * input.
	 * Used to set initial group list
	 * @return String
	 */
	public String input() {
		try {
			final InterviewAssessMasterModel model = new InterviewAssessMasterModel();
			model.initiate(context, session);
			model.showInitialGroupList(this.intwassMaster, request);
			//model.intData(this.intwassMaster, request);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.resetGroup();
		this.getNavigationPanel(1);
		return INPUT;
	}

	
	/**Method : addNewGroup.
	 * Purpose : This method is used to add new group
	 * @return String
	 */
	public String addNewGroup() {
		try {
			this.resetGroup();
			this.getNavigationPanel(2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.addnewGroupStr;
	}


	/**@modified by @author Prajakta.Bhandare 
	 * To reset the parameters fields.
	 * @return String
	 */
	public String resetParameterData() {
		try {
			String groupCode = request.getParameter("groupCode");
			this.intwassMaster.setGroupCode(groupCode);
			final InterviewAssessMasterModel model = new InterviewAssessMasterModel();
			model.initiate(context, session);
			model.setGroupDetailsForParameter(intwassMaster, groupCode);
			this.intwassMaster.setParaName("");
			this.intwassMaster.setParaDes("");
			this.intwassMaster.setIsActive("");
			this.intwassMaster.setParaCode("");
			this.intwassMaster.setParaIsActive("");
			final String[] parameterNameItr = request.getParameterValues("parameterNameItr");
			final String[] parameterDescriptionItr = request.getParameterValues("parameterDescriptionItr");
			final String[] parameterIsActiveItr = request.getParameterValues("parameterIsActiveItr");
			final List<InterviewAssessMaster> paramList = new ArrayList<InterviewAssessMaster>();
			if(parameterNameItr.length > 0){//id data
				for (int i = 0; i < parameterNameItr.length; i++) {//i for loop
					final InterviewAssessMaster innerBean = new InterviewAssessMaster();
					innerBean.setParameterNameItr(String.valueOf(parameterNameItr[i]));
					innerBean.setParameterDescriptionItr(String.valueOf(parameterDescriptionItr[i]));
					innerBean.setParameterIsActiveItr("Yes".equals(String.valueOf(parameterIsActiveItr[i])) ? "Yes" : "No" );
					paramList.add(innerBean);
				}//end of for loop
			}//end if data
			
			if (paramList.size() > 0) {//if size greater
				intwassMaster.setDataPresent(true);
			}//end if size greater
			intwassMaster.setParameterList(paramList);
			model.terminate();
			
		} catch (final Exception e) {
			this.logger.error("Error in reset" + e);
		}
		this.getNavigationPanel(2);
		this.intwassMaster.setEnableAll("Y");
		return this.addNewStr;
	}

	
	/**This method is called on save button for saving records*/
	
	/**Method : saveGroup.
	 * Purpose : This method is used to save group data
	 * @return String
	 * @throws Exception : exception
	 */
	public String saveGroup() throws Exception {
		try {
			final InterviewAssessMasterModel model = new InterviewAssessMasterModel();
			model.initiate(context, session);
			boolean result;

			if ("".equals(this.intwassMaster.getGroupCode().trim())) {
				result = model.addGroupData(this.intwassMaster);
				if (result) {
					this.addActionMessage(this.getMessage("save"));
				} else {
					this.addActionMessage("Unable to save this group");
					this.getNavigationPanel(2);
					this.resetGroup();
				}
			} else {
				result = model.updateGroupData(this.intwassMaster);
				if (result) {
					this.addActionMessage(this.getMessage("update"));
				} else {
					this.addActionMessage("Unable to update this group");
					this.resetGroup();
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(3);
		this.intwassMaster.setEnableAll(this.noStr);
		return this.addnewGroupStr;
	}
	
	/**Method : editGroup.
	 * Purpose : Used to edit group data
	 * @return String
	 */
	public String editGroup() {
		this.getNavigationPanel(2);
		this.intwassMaster.setEnableAll(this.yesStr);
		return this.addnewGroupStr;
	}
	
	/**Method : deleteGroup.
	 * Purpose : Used to delete group data
	 * @return String
	 */
	public String deleteGroup() {
		final InterviewAssessMasterModel model = new InterviewAssessMasterModel();
		model.initiate(context, session);
		final boolean result = model.deleteGroupData(this.intwassMaster);
		if (result) {
			this.addActionMessage("Record deleted successfully");
		} else {
			this.addActionMessage(this.messageStr);
		}
		this.input();
		model.terminate();
		return INPUT;
	}
	
	
	/**Method : resetGroup.
	 * Purpose : Used to reset group data
	 * @return String
	 */
	public String resetGroup() {
		this.intwassMaster.setGroupCode("");
		this.intwassMaster.setGroupName("");
		this.intwassMaster.setGroupDescription("");
		this.intwassMaster.setGroupAbbreviation("");
		this.getNavigationPanel(2);
		this.intwassMaster.setEnableAll("Y");
		return this.addnewGroupStr;
	}
	
	
	/**Method : f9Groupaction.
	 * To set the fields after search
	 * @return String
	 * @throws Exception : Exception
	 */

	public String f9Groupaction() throws Exception {
		final String query = " SELECT HRMS_REC_INTERVIEW_GROUP.GROUP_NAME, HRMS_REC_INTERVIEW_GROUP.GROUP_DESCRIPTION, " + 
							 " DECODE(HRMS_REC_INTERVIEW_GROUP.GROUP_IS_ACTIVE,'Y','Active','N','InActive'), HRMS_REC_INTERVIEW_GROUP.GROUP_ID " + 
							 " FROM HRMS_REC_INTERVIEW_GROUP " +  
							 " ORDER BY HRMS_REC_INTERVIEW_GROUP.GROUP_ID";
		final String[] headers = {this.getMessage("groupNameLabel"), this.getMessage("descriptionLabel")};
		final String[] headerWidth = {"30", "60", "10"};
		final String[] fieldNames = {"groupName", "groupDescription", "isGroupActive", "groupCode"};
		final int[] columnIndex = {0, 1, 2, 3 };
		final String submitFlag = this.trueStr;
		final String submitToMethod = "InterviewAssessMasterAction_setGroupRecord.action";
		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	
	/**Method : setGroupRecord.
	 * Purpose : This method is used to set group data
	 * @return String
	 * @throws Exception : exception
	 */
	public String setGroupRecord() throws Exception {
		try {
			final InterviewAssessMasterModel model = new InterviewAssessMasterModel();
			model.initiate(context, session);
			model.getGroupRecord(this.intwassMaster);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(3);
		this.intwassMaster.setEnableAll(this.noStr);
		return this.addnewGroupStr;
	}
	
	
	/**
	 * Method : deleteChkGroupRecords.
	 * purpose - deleting multiple records.
	 * @return String.
	 */
	public String deleteChkGroupRecords()  {
		final String [] code = request.getParameterValues("hdeleteCode");
		final InterviewAssessMasterModel model = new InterviewAssessMasterModel();
		model.initiate(context, session);
		final boolean result = model.deleteCheckedGroupRecords(this.intwassMaster, code);
		if (result) {
			this.addActionMessage(this.getText("delMessage", ""));
		} else {
			this.addActionMessage(this.messageStr);
		}
		this.input();
		this.getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}
	
	/**
	 * Method : backToGroupList.
	 * Purpose : Used to return back to group page
	 * @return String
	 */
	public String backToGroupList() {
		this.input();
		return INPUT;
	}
	
	/**
	 * manageParameters.
	 * Used to add new group
	 * @return String
	 */
	public String manageParameters() {
		try {
			String groupCode = request.getParameter("groupCode");
			this.intwassMaster.setGroupCode(groupCode);
			final InterviewAssessMasterModel model = new InterviewAssessMasterModel();
			model.initiate(context, session);
			model.setGroupDetailsForParameter(this.intwassMaster, groupCode);
			model.setSavedParameters(this.intwassMaster, groupCode,request);
			model.terminate();
			this.getNavigationPanel(2);
			this.intwassMaster.setEnableAll("Y");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return this.addNewParameterStr;
	}
	
	/**
	 * Method : addParameterToList.
	 * purpose - Add parameters to list.
	 * @return String.
	 */
	public String addParameterToList() {
		try {
			final String parameterName = this.intwassMaster.getParaName();
			final String parameterDescription = this.intwassMaster.getParaDes();
			final String parameterIsActive = this.intwassMaster.getParaIsActive();
			
			final String[] parameterNameItr = request.getParameterValues("parameterNameItr");
			final String[] parameterDescriptionItr = request.getParameterValues("parameterDescriptionItr");
			final String[] parameterIsActiveItr = request.getParameterValues("parameterIsActiveItr");
			
			final InterviewAssessMasterModel model = new InterviewAssessMasterModel();
			model.initiate(context, session);
			model.addParameterToList(this.intwassMaster, parameterName, parameterDescription, parameterIsActive, parameterNameItr, parameterDescriptionItr, parameterIsActiveItr,request);
			model.setGroupDetailsForParameter(this.intwassMaster, this.intwassMaster.getGroupCode());
			model.terminate();
			this.addActionMessage("Parameter added successfully");
		} catch (final Exception e) {
			this.addActionMessage("Unable to add this parameter");
			e.printStackTrace();
		}
		
		this.getNavigationPanel(2);
		this.intwassMaster.setEnableAll("Y");
		this.intwassMaster.setParaName("");
		this.intwassMaster.setParaDes("");
		this.intwassMaster.setIsActive("");
		this.intwassMaster.setParaCode("");
		this.intwassMaster.setParaIsActive("");
		return this.addNewParameterStr;
	}
	
	
	/**
	 * Method : saveParameters.
	 * Purpose : This method is used to save parameters
	 * @return String
	 */
	public String saveParameters() {
		try {
			final String[] parameterNameItr = request.getParameterValues("parameterNameItr");
			final String[] parameterDescriptionItr = request.getParameterValues("parameterDescriptionItr");
			final String[] parameterIsActiveItr = request.getParameterValues("parameterIsActiveItr");
			
			final InterviewAssessMasterModel model = new InterviewAssessMasterModel();
			model.initiate(context, session);
			boolean result = model.saveParameters(this.intwassMaster, this.intwassMaster.getGroupCode(), parameterNameItr, parameterDescriptionItr, parameterIsActiveItr);
			model.setSavedParameters(this.intwassMaster, this.intwassMaster.getGroupCode(),request);
			model.setGroupDetailsForParameter(this.intwassMaster, this.intwassMaster.getGroupCode());
			if (result) {
				addActionMessage("Parameters added successfully");
			}
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		resetParameterData();
		this.getNavigationPanel(2);
		this.intwassMaster.setEnableAll("Y");
		return "addNewParameter";
	}
	
	
	/** Method to add new Record
	 * @return String
	 */
	public String addNew(){
		try {
			final InterviewAssessMasterModel model = new InterviewAssessMasterModel();
			model.initiate(context, session);
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	return "addnew"	;
	}
	
	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		final InterviewAssessMasterModel model = new InterviewAssessMasterModel();
		model.initiate(context, session);
		model.showInitialGroupList(this.intwassMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}
	
	/**@author Prajakta.Bhandare
	 * Method : setRecord.
	 * Purpose : This method is used to set parameter data for editing
	 * @return String
	 * @throws Exception : exception
	 */
	public String setRecord() throws Exception {
		try {
			final String[] parameterNameItr = request.getParameterValues("parameterNameItr");
			final String[] parameterDescriptionItr = request.getParameterValues("parameterDescriptionItr");
			final String[] parameterIsActiveItr = request.getParameterValues("parameterIsActiveItr");
			String groupCode = request.getParameter("groupCode");
			this.intwassMaster.setGroupCode(groupCode);
			final InterviewAssessMasterModel model = new InterviewAssessMasterModel();
			model.initiate(context, session);
			model.setGroupDetailsForParameter(this.intwassMaster, groupCode);
			model.getRecord(this.intwassMaster, parameterNameItr, parameterDescriptionItr, parameterIsActiveItr,request);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		this.getNavigationPanel(2);
		this.intwassMaster.setEnableAll(this.yesStr);
		return this.addNewParameterStr;
	}
}
