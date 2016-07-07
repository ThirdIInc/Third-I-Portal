package org.struts.action.admin.srd;

import java.util.TreeMap;

import org.paradyne.bean.admin.srd.PayHistory;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.model.admin.srd.PayHistoryModel;
import org.paradyne.model.admin.srd.SkillDetailsModel;
import org.struts.lib.ParaActionSupport;

import com.lowagie.text.Image;

/**
 * @author riteshr
 * updated By
 * @author Prajakta.Bhandare
 * @date 07 Jan 2013
 */
public class PayHistoryAction extends ParaActionSupport {

	PayHistory payHistory = null;
	String poolDir = "";
	String fileName = "";
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PayHistoryAction.class);

	public void prepare_local() throws Exception {
		payHistory = new PayHistory();
		payHistory.setMenuCode(155);

	}

	/**
	 * Following method is for general user login who can view only his/her
	 * records.
	 */
	public void prepare_withLoginProfileDetails() throws Exception {
		PayHistoryModel model = new PayHistoryModel();
		payHistory.setEditDetail(false);
		payHistory.setEditFlag(false);
		model.initiate(context, session);
		try{			
			if(payHistory.isGeneralFlag()){
				payHistory.setIsNotGeneralUser("false");
				payHistory.setEmpId(payHistory.getUserEmpId());
			}
			else{
				payHistory.setIsNotGeneralUser("true");
				String str = (String) request.getSession().getAttribute("srdEmpCode");
				if(str!=null && str!=""){	
					payHistory.setEmpId(str);
				}
			}
			model.getPayDetails(payHistory);
		}		
		catch(Exception e){
			e.printStackTrace();
		}
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map = dmu.getGenderXml("Increment");
		payHistory.setAssetmap(map);
		dmu.terminate();
		getProfileImage();
		model.terminate();
	}

	public Object getModel() {

		return payHistory;
	}

	/**
	 * @return the payHistory
	 */
	public PayHistory getPayHistory() {
		return payHistory;
	}

	/**
	 * @param payHistory
	 *            the payHistory to set
	 */
	public void setPayHistory(PayHistory payHistory) {
		this.payHistory = payHistory;
	}

	/**
	 * To save or modify the record.
	 */
	public String save() throws Exception {
		PayHistoryModel model = new PayHistoryModel();
		model.initiate(context, session);
		String result = "";
		if (payHistory.getParacode().equals("")) {
			result = model.savePay(payHistory);

		}// end of if
		else {
			result = model.modPay(payHistory,request);
		}// end of else
		addActionMessage(result);
		payHistory.setEditFlag(false);
		payHistory.setEditDetail(false);
		payHistory.setParacode("");
		model.getPayDetails(payHistory);
		getProfileImage();
		model.terminate();
		payHistory.setPost("");
		payHistory.setPostId("");
		payHistory.setDept("");
		payHistory.setDeptId("");
		payHistory.setCenterName("");
		payHistory.setIncrementDate("");
		payHistory.setDceList("");
		payHistory.setDceDate("");
		payHistory.setPayType("");
		payHistory.setNctc("");
		payHistory.setOctc("");
		payHistory.setPayId("");
		payHistory.setSerialNo("");
		return "success";
	}

	/**
	 * To set the employee details name after search.
	 */
	public String f9action() {

		String query = "  SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),"
			+ "	NVL(CENTER_NAME,' '),NVL(RANK_NAME,' '),EMP_ID FROM HRMS_EMP_OFFC  "
			+ "	INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
			+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK "
			+ "	LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE";
	query += getprofileQuery(payHistory);
	query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";

		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation") };

		String[] headerwidth = { "15", "30", "27", "28" };
		String[] fieldNames = { "tokenNo", "empName",
				"center", "rank", "empId",};
		int[] columnIndex = { 0, 1, 2, 3, 4};
		String submitFlage = "true";
		String submitToMethod = "PayHistory_getRecord.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";

	}

	/**
	 * To display the records of a selected employee below.
	 * 
	 * @return String
	 */
	public String getRecord() throws Exception {
		PayHistoryModel model = new PayHistoryModel();
		model.initiate(context, session);
		model.getPayDetails(payHistory);
		getProfileImage();
		model.terminate();
		return "success";
	}

	/**
	 * To set the record of an employee for edit after clicking respective edit
	 * button.
	 * 
	 * @return String
	 */
	public String edit() {
		try {
			PayHistoryModel model = new PayHistoryModel();
			model.initiate(context, session);
			payHistory.setEditDetail(true);
			payHistory.setEditFlag(true);
			model.getOneRecord(payHistory);
			model.getPayDetails(payHistory);
			getProfileImage();
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "success";

	}
	
	public String add(){
		try {
			PayHistoryModel model = new PayHistoryModel();
			model.initiate(context, session);
			payHistory.setEditDetail(true);
			payHistory.setEditFlag(true);
			model.getPayDetails(payHistory);
			getProfileImage();
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * To delete the record of an employee after clicking the respective delete
	 * button
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		PayHistoryModel model = new PayHistoryModel();
		model.initiate(context, session);
		String result = model.deleteRecord(payHistory);
		addActionMessage(result);
		payHistory.setParacode("");
		payHistory.setEditDetail(false);
		payHistory.setEditFlag(false);
		model.getPayDetails(payHistory);
		getProfileImage();
		model.terminate();
		
		return "success";

	}

	/**
	 * To clear all the form fields
	 * 
	 * @return String
	 */
	public String cancel() {
		try {
			PayHistoryModel model = new PayHistoryModel();
			model.initiate(context, session);
			payHistory.setEditDetail(false);
			payHistory.setEditFlag(false);
			model.getPayDetails(payHistory);
			getProfileImage();
			model.terminate();
		}// end of try
		catch (Exception e) {
			e.printStackTrace();
		}// end of catch
		return "success";
	}
	
	
	/**
	 * METHOD TO GET PROFILE IMAGE AND NAME OF EMPLOYEE
	 **/
	public void getProfileImage(){
		PayHistoryModel model= new PayHistoryModel();
		model.initiate(context, session);
	    model.getImage(payHistory);
		 String photo = payHistory.getUploadFileName();
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

}
