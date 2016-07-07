package org.struts.action.admin.srd;

import org.paradyne.bean.admin.srd.PromotionHistory;
import org.paradyne.model.admin.srd.PromotionHistoryModel;
import org.struts.lib.ParaActionSupport;

import com.lowagie.text.Image;

/**
 * @author Prajakta.Bhandare
 * @date 09 Jan 2013
 */
public class PromotionHistoryAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	PromotionHistory promotionHistory = null;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PromotionHistoryAction.class);

	/**
	 * @return the promotionHistory
	 */
	public PromotionHistory getPromotionHistory() {
		return promotionHistory;
	}

	/**
	 * @param promotionHistory
	 *            the promotionHistory to set
	 */
	public void setPromotionHistory(PromotionHistory promotionHistory) {
		this.promotionHistory = promotionHistory;
	}

	public void prepare_local() throws Exception {
		promotionHistory = new PromotionHistory();
		promotionHistory.setMenuCode(142);
	}

	/**
	 * following method is for general user login who can view only his/her
	 * records.
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		PromotionHistoryModel model = new PromotionHistoryModel();
		model.initiate(context, session);
		promotionHistory.setEditDetail(false);
		promotionHistory.setEditFlag(false);
		if (promotionHistory.isGeneralFlag()) {
			promotionHistory.setIsNotGeneralUser("false");
			promotionHistory.setEmpId(promotionHistory.getUserEmpId());
		}// end of if
		else {
			promotionHistory.setIsNotGeneralUser("true");
			String offcEmp = (String) request.getSession().getAttribute(
					"srdEmpCode");
			if (offcEmp == null || offcEmp.equals("") || offcEmp.equals("null")) {
				offcEmp=promotionHistory.getUserEmpId();
			}
			promotionHistory.setEmpId(offcEmp);
		}// end of else
		model.getPromotionDetails(promotionHistory);
		getProfileImage();
		model.terminate();
	}

	public Object getModel() {

		return promotionHistory;
	}

	/**
	 * for saving and modifying the records.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		PromotionHistoryModel model = new PromotionHistoryModel();
		model.initiate(context, session);
		String result = "";
		if (promotionHistory.getParacode().equals("")) {
			result = model.savePromotion(promotionHistory);
		}// end of if
		else{
			result = model.modPromotion(promotionHistory, request);
		}// end of else
		model.getPromotionDetails(promotionHistory);
		promotionHistory.setEditDetail(false);
		promotionHistory.setEditFlag(false);
		getProfileImage();
		model.terminate();
		addActionMessage(result);
		promotionHistory.setPost("");
		promotionHistory.setPostId("");
		promotionHistory.setDept("");
		promotionHistory.setDeptId("");
		promotionHistory.setCenterName("");
		promotionHistory.setCenterNo("");
		promotionHistory.setCtc("");
		promotionHistory.setPromotionDate("");
		promotionHistory.setPromoId("");
		promotionHistory.setParacode("");

		return "success";
	}

	/**
	 * following method returns the employee name after search.
	 * 
	 * @return String
	 */
	public String f9actionEmployeeId() {

		String query = " SELECT EMP_TOKEN,(EMP_FNAME||'  '|| EMP_MNAME|| '  ' ||EMP_LNAME),CENTER_NAME,RANK_NAME,EMP_ID, "
				+ "  NVL(TITLE_NAME,' ')||' ' ||NVL(HRMS_EMP_OFFC.EMP_FNAME,' ' )|| ' ' || NVL(HRMS_EMP_OFFC.EMP_MNAME,' ') || ' ' || NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')	FROM HRMS_EMP_OFFC "
				+ " INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ " INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
				+ " LEFT JOIN HRMS_TITLE ON (HRMS_EMP_OFFC.EMP_TITLE_CODE  =HRMS_TITLE.TITLE_CODE)  ";

		query += getprofileQuery(promotionHistory);
		query += " ORDER BY EMP_ID  ";

		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation") };

		String[] headerwidth = { "12", "30", "27", "28" };
		String[] fieldNames = { "tokenNo", "empName", "center", "rank",
				"empId", "empName" };
		int[] columnIndex = { 0, 1, 2, 3, 4, 5 };
		String submitFlage = "true";
		String submitToMethod = "PromotionHistory_getRecord.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";

	}

	/**
	 * To set the Post id and Post name after search .
	 * 
	 * @return String
	 */
	public String f9actionPost() {
		String query = "SELECT RANK_ID,RANK_NAME FROM HRMS_RANK WHERE IS_ACTIVE='Y' ORDER BY RANK_NAME ";
		String[] headers = { getMessage("designation.code"),
				getMessage("designation") };
		String[] headerwidth = { "8", "20" };
		String[] fieldNames = { "postId", "post" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		//promotionHistory.setMasterMenuCode(26);
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";

	}

	/**
	 * To set the center,Rank and all the records of a selected employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getRecord() throws Exception {
		PromotionHistoryModel model = new PromotionHistoryModel();
		model.initiate(context, session);
		model.getPromotionDetails(promotionHistory);
		promotionHistory.setEditDetail(false);
		promotionHistory.setEditFlag(false);
		getProfileImage();
		model.terminate();
		return "success";
	}

	/**
	 * To set the particular record of an employee 
	 * 
	 * @return String
	 */
	public String edit() {
		PromotionHistoryModel model = new PromotionHistoryModel();
		model.initiate(context, session);
		promotionHistory.setEditDetail(true);
		promotionHistory.setEditFlag(true);
		model.getOneRecord(promotionHistory);
		model.getPromotionDetails(promotionHistory);
		getProfileImage();
		model.terminate();
		return "success";

	}

	/** To add record of employee 
	 * 
	 * @return String
	 */
	public String add() {
		PromotionHistoryModel model = new PromotionHistoryModel();
		model.initiate(context, session);
		promotionHistory.setEditDetail(true);
		promotionHistory.setEditFlag(true);
		model.getPromotionDetails(promotionHistory);
		getProfileImage();
		model.terminate();
		return "success";

	}

	/** Method to cancel the particular selected record 
	 * @return String 
	 */
	public String cancel() {
		PromotionHistoryModel model = new PromotionHistoryModel();
		model.initiate(context, session);
		promotionHistory.setEditDetail(false);
		promotionHistory.setEditFlag(false);
		model.getPromotionDetails(promotionHistory);
		getProfileImage();
		model.terminate();
		return "success";

	}

	/**
	 * To delete particular record of an employee after clicking respective
	 * Delete button.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		PromotionHistoryModel model = new PromotionHistoryModel();
		model.initiate(context, session);
		String result = model.deleteRecord(promotionHistory);
		model.getPromotionDetails(promotionHistory);
		addActionMessage(result);
		promotionHistory.setParacode("");
		promotionHistory.setEditDetail(false);
		promotionHistory.setEditFlag(false);
		getProfileImage();
		model.terminate();
		return "success";

	}

	/**
	 * To select department Id and department Name
	 * 
	 * @return String
	 */
	public String f9actionDept() {

		String query = "SELECT DEPT_ID,DEPT_NAME FROM HRMS_DEPT WHERE IS_ACTIVE='Y' ORDER BY DEPT_ID";

		String[] headers = { getMessage("department.code"),
				getMessage("department") };
		String[] headerwidth = { "8", "20" };
		String[] fieldNames = { "deptId", "dept" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		//promotionHistory.setMasterMenuCode(23);
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";

	}

	/**
	 * To select center Id and center Name
	 * 
	 * @return String
	 */
	public String f9actionCenterNo() {

		String query = "  SELECT CENTER_ID,CENTER_NAME FROM HRMS_CENTER WHERE IS_ACTIVE='Y' ORDER BY CENTER_ID ";

		String[] headers = { getMessage("branch.code"), getMessage("branch") };
		String[] headerwidth = { "8", "20" };
		String[] fieldNames = { "centerNo", "centerName" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		//promotionHistory.setMasterMenuCode(31);
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";

	}

	/**
	 * METHOD TO GET PROFILE IMAGE AND NAME OF EMPLOYEE
	 **/
	public void getProfileImage() {
		PromotionHistoryModel model = new PromotionHistoryModel();
		model.initiate(context, session);
		model.getImage(promotionHistory);
		String photo = promotionHistory.getUploadFileName();
		String str = (String) session.getAttribute("session_pool");
		//leaveBal.setFlag("false");
		String img = model.getServletContext().getRealPath("//")
				+ "//pages//images//" + str + "//employee//" + photo;// +".jpg";
		try {
			Image image = Image.getInstance(img);
		}// end of try
		catch (Exception e) {
			photo = "NoImage.jpg";
		}
		request.setAttribute("profilePhoto", photo);
	}
}
