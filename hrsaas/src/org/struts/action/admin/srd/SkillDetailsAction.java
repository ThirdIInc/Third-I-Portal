package org.struts.action.admin.srd;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.admin.srd.SkillDetails;
import org.paradyne.model.admin.srd.SkillDetailsModel;
import com.lowagie.text.Image;

/**
 * @author deepak.pimparkar
 */

/** Updated By
 * @author Prajakta.Bhandare
 * @date 03 Jan 2013
 */
public class SkillDetailsAction extends ParaActionSupport {

	private static final long serialVersionUID = 1L;
	private SkillDetails skillDetail;
	
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	public void prepare_local() throws Exception {
		skillDetail = new SkillDetails();
		skillDetail.setMenuCode(2241);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return skillDetail;
	}
	

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
	 */
	public void prepare_withLoginProfileDetails() throws Exception{
		SkillDetailsModel model = new SkillDetailsModel();
		model.initiate(context, session);
		skillDetail.setEditDetail(false);
		skillDetail.setEditFlag(false);
		skillDetail.setNoData("false");
		try{			
			if(skillDetail.isGeneralFlag()){
				skillDetail.setIsNotGeneralUser("false");
				skillDetail.setEmpId(skillDetail.getUserEmpId());
			}
			else{
				skillDetail.setIsNotGeneralUser("true");
				String str = (String) request.getSession().getAttribute("srdEmpCode");
				if(str==null || str.equals("") || str.equals("null")){	
				str=skillDetail.getUserEmpId();
				}
				skillDetail.setEmpId(str);
			}
			model.getRecord(skillDetail, request);
		}		
		catch(Exception e){
			e.printStackTrace();
		}
		getProfileImage();
		model.terminate();
	}
	
	/**
	 * METHOD TO GET PROFILE IMAGE AND NAME OF EMPLOYEE
	 **/
	public void getProfileImage(){
		SkillDetailsModel model= new SkillDetailsModel();
		model.initiate(context, session);
	    model.getImage(skillDetail);
		 String photo = skillDetail.getUploadFileName();
		String str = (String) session.getAttribute("session_pool");
		//leaveBal.setFlag("false");
		String img = model.getServletContext().getRealPath("//")+ "//pages//images//" + str + "//employee//" + photo;// +".jpg";
		try {
			Image image = Image.getInstance(img);
		}// end of try
		catch (Exception e) {
			photo = "NoImage.jpg";
		}
		request.setAttribute("profilePhoto", photo);
	}

	/** Method to add Skills of Employee 
	 * @return String
	 */
	public String add() throws Exception{
		try {
			skillDetail.setEditDetail(true);
			skillDetail.setEditFlag(true);
			SkillDetailsModel model = new SkillDetailsModel();
			model.initiate(context, session);
			model.getRecord(skillDetail, request);
			getProfileImage();
			model.terminate();
		} catch (RuntimeException e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/** Method to edit skill detail of employee
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception{
		try {
			SkillDetailsModel model = new SkillDetailsModel();
			model.initiate(context, session);
			model.getEditRecord(skillDetail, request);
			skillDetail.setEditDetail(true);
			skillDetail.setEditFlag(true);
			model.getRecord(skillDetail, request);
			getProfileImage();
			model.terminate();
		} catch (RuntimeException e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/** Method to select the employee. 
	 @return String
	  @throws Exception*/
	
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = "  SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
				+ "	NVL(CENTER_NAME,' '),NVL(RANK_NAME,' '),EMP_ID FROM HRMS_EMP_OFFC  "
				+ "	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE";
		query += getprofileQuery(skillDetail);
		query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
		
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation"),
				};
		String[] headerWidth = { "20", "30", "30", "20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "empToken", "empName",
				"branchName", "designationName","empId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4};

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
		String submitToMethod = "SkillDetails_skillRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	/** Method to cancel Skill Detail Record
	  @return String
	  @throws Exception
	 */
	public String cancel() throws Exception {
		try {
			SkillDetailsModel model = new SkillDetailsModel();
			model.initiate(context, session);
			model.getRecord(skillDetail, request);
			skillDetail.setEditDetail(false);
			skillDetail.setEditFlag(false);
			getProfileImage();
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/** Method to select skill of employee
	 * @return String
	 * @throws Exception
	 */
	public String f9actionSkill() throws Exception {

		String query = "SELECT SKILL_ID,SKILL_NAME FROM  HRMS_SKILL WHERE SKILL_STATUS = 'A' ORDER BY SKILL_ID";
		String[] headers = { "SkillCode", getMessage("skill") };
		String[] headerWidth = { "20","80" };
		String[] fieldNames = { "skillValue","skillName"};
		int[] columnIndex = { 0,1 };		
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	/** Mrthodto save Skill Detail of Employee
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception{
		SkillDetailsModel model = new SkillDetailsModel();
		model.initiate(context, session);
		boolean result;
		String str = null;
		if (skillDetail.getParacode().equals("")) {
			result = model.addSkillDetails(skillDetail,request);
			str = getMessage("save");
			skillDetail.setNoData("false");
		}// end of if
		else {
			result = model.updateSkillDetails(skillDetail,request);
			str = getMessage("update");
		}// end of else
		
		if (result) {
			addActionMessage(str);
		}// end of if
		else {
			addActionMessage(getMessage("save.error"));
		}// end of else
		skillDetail.setEditFlag(false);
		skillDetail.setEditDetail(false);
		model.getRecord(skillDetail, request);
		reset();
		skillDetail.setParacode("");
		getProfileImage();
	    model.terminate();
		return SUCCESS;
		
	}
	
	/** Method to reset fields
	 * @return STRING
	 */
	public String reset(){
		skillDetail.setSkilltype("");
		skillDetail.setSkillName("");
		skillDetail.setSkillValue("");
		skillDetail.setDuration("");
		skillDetail.setDurationType("");
		skillDetail.setOtherSkill("");
		return SUCCESS;
		
	}
	 
	/** Method to delete skill detail of Employee
	 * @return String
	 * @throws Exception
	 */
	public String deleteSkillDetails() throws Exception{
		try {
			boolean str;
			SkillDetailsModel model = new SkillDetailsModel();
			model.initiate(context, session);
			str= model.delete(skillDetail,request);
			if(str){
				addActionMessage(getMessage("delete"));
			}else{
				addActionMessage(getMessage("delete.error"));
			}
			skillDetail.setEditDetail(false);
			skillDetail.setEditFlag(false);
			model.getRecord(skillDetail, request);
			skillDetail.setParacode("");
			getProfileImage();
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
	
	/** Method to get Skill Details of Employee
	 * @return String
	 */
	public String skillRecord(){
		try {
			SkillDetailsModel model = new SkillDetailsModel();
			model.initiate(context, session);
			model.getRecord(skillDetail, request);
			getProfileImage();
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
}
