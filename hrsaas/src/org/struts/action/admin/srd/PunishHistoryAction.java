package org.struts.action.admin.srd;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.ArrayList;
import org.paradyne.bean.admin.srd.PunishHistory;
import org.paradyne.model.admin.srd.PunishHistoryModel;
import org.struts.lib.ParaActionSupport;

import com.lowagie.text.Image;
/**
 * modified by Priyanka. Kumbhar.
 * 
 */
public class PunishHistoryAction extends ParaActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PunishHistory punishHistory;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PunishHistoryAction.class);
	public void prepare_local() throws Exception {
		punishHistory = new PunishHistory();
		punishHistory.setMenuCode(203);

	}

	/**
	 * following method is for general user login who can view only his/her
	 * records.
	 */

   public void prepare_withLoginProfileDetails() throws Exception {
		PunishHistoryModel model = new PunishHistoryModel();
		model.initiate(context, session);
		try {
		if (punishHistory.isGeneralFlag()) {			
			punishHistory.setIsNotGeneralUser("false");
			punishHistory.setEmpId(punishHistory.getUserEmpId());
		}// end of if
		else {
			punishHistory.setIsNotGeneralUser("true");
			String offcEmp = (String) request.getSession().getAttribute("srdEmpCode");
			if(!(offcEmp == null || offcEmp.equals("") || offcEmp.equals("null"))){
				punishHistory.setEmpId(offcEmp);
			}
			else{
				punishHistory.setEmpId(punishHistory.getUserEmpId());
			}
		}// end of if
		model.getPunishDetails(punishHistory);		
		 getProfileImage();
		 punishHistory.setEditableFlag("false");
		model.terminate();
	} catch (Exception e) {
		e.printStackTrace();
	}
   }

	public String addNewRecord() throws Exception {
		PunishHistoryModel model = new PunishHistoryModel();
		model.initiate(context, session);
		model.getPunishDetails(punishHistory);
		punishHistory.setEditableFlag("true");	
		getProfileImage();		 
		 model.terminate();
		return SUCCESS;
	}
	
	public Object getModel() {

		return punishHistory;
	}
	
	/**
	 * following function is called when delete button is clicked in the list for the corresponding row.
	 * @return
	 */
	
	public String delPunish(){
		PunishHistoryModel model = new PunishHistoryModel();
		model.initiate(context,session);
		boolean result=model.delPunishment(punishHistory);
		if(result)
		{
			addActionMessage(getText("delMessage",
					"Record Deleted Successfully"));
			punishHistory.setDispAction("");
			punishHistory.setDispActionId("");
			punishHistory.setAuthority("");
			punishHistory.setLetterDate("");
			punishHistory.setLetterNo("");
			punishHistory.setCeoDate("");
			punishHistory.setCeoOrder("");
			punishHistory.setPeriod("");
			punishHistory.setWef("");
			punishHistory.setDescription("");
			punishHistory.setSerialNo("");
			punishHistory.setUploadFileName("");
			punishHistory.setUploadFileNameTxt("");
			punishHistory.setEffFromDate("");
			punishHistory.setEffToDate("");
		}
		else
		{
			addActionMessage("Record Cannot Be Deleted");
		}
		punishHistory.setParacode("");
		model.getPunishDetails(punishHistory);
		getProfileImage();
		model.terminate();		
		return "success";
	}
	
	
	

	/**
	 * @return the punishHistory
	 */
	public PunishHistory getPunishHistory() {
		return punishHistory;
	}

	/**
	 * @param punishHistory the punishHistory to set
	 */
	public void setPunishHistory(PunishHistory punishHistory) {
		this.punishHistory = punishHistory;
	}

	/**
	 * Method to save or modify the punishment details of an employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		PunishHistoryModel model = new PunishHistoryModel();
		model.initiate(context, session);
		String[] doc=request.getParameterValues("uploadFil");
		String result = "";
		if(punishHistory.getParacode().equals("")){
			System.out.println("-----------------------add---------------------------"+punishHistory.getParacode());
			result = model.savePunish(punishHistory,doc);
				
		}else{
			System.out.println("--------------------------------update---------------------------");
			result = model.modPunish(punishHistory,doc,request);
			
		}
	    punishHistory.setEditableFlag("false");
    	model.getPunishDetails(punishHistory);
    	punishHistory.setParacode("");
    	getProfileImage();
		model.terminate();

		addActionMessage(getText(result));
		punishHistory.setParacode("");
		punishHistory.setDispAction("");
		punishHistory.setDispActionId("");
		punishHistory.setAuthority("");
		punishHistory.setEffFromDate("");
		punishHistory.setEffToDate("");
		punishHistory.setLetterNo("");
		punishHistory.setCeoDate("");
		punishHistory.setCeoOrder("");
		punishHistory.setPeriod("");
		punishHistory.setWef("");
		punishHistory.setPunishId("");
		punishHistory.setDescription("");
		punishHistory.setSerialNo("");
		punishHistory.setUploadFileName("");
		punishHistory.setUploadFileNameTxt("");
		punishHistory.setItrFlag(true);
		return "success";
	}

	/**
	 * Method to set the employee information after search .
	 * 
	 * @return String
	 */
	public String f9actionEmployeeId() {
		String query = " SELECT EMP_TOKEN,(EMP_FNAME||'  '|| EMP_MNAME|| '  ' ||EMP_LNAME),CENTER_NAME,RANK_NAME,EMP_ID"
				+"  FROM HRMS_EMP_OFFC "
				+ " INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
				+ " INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK ";
		query += getprofileQuery(punishHistory);
		query += " ORDER BY EMP_ID  ";
		punishHistory.setDeleteFlag(true);//A
		String[] headers = { getMessage("employee.id"), getMessage("employee"),
				getMessage("branch"), getMessage("designation") };

		String[] headerwidth = { "12", "30", "27", "28" };
		String[] fieldNames = { "punishHistory.tokenNo",
				"punishHistory.empName", "punishHistory.center",
				"punishHistory.rank", "empId"
				 };
		int[] columnIndex = { 0, 1, 2, 3, 4 };
		String submitFlage = "true";
		String submitToMethod = "PunishHistory_getRecord.action";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";

	}

	/**
	 * Method set the the Disciplinary action Name
	 * 
	 * @return String
	 */
	public String f9actionDispAction() {
		String query = "SELECT PUNISH_NAME,PUNISH_ID FROM HRMS_PUNISHMENT ORDER BY PUNISH_ID ";
		String[] headers = { getMessage("punishment") };
		String[] headerwidth = {"30" };
		String[] fieldNames = {"punishHistory.dispAction","punishHistory.dispActionId" };
		int[] columnIndex = { 0, 1 };
		String submitFlage = "false";
		String submitToMethod = "";
		punishHistory.setMasterMenuCode(255);
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);

		return "f9page";

	}

	/**
	 * Method to get the punishment details of an selected employee.
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getRecord() throws Exception {
		punishHistory.setItrFlag(true);
		PunishHistoryModel model = new PunishHistoryModel();
		model.initiate(context, session);
		model.getPunishDetails(punishHistory);
		getProfileImage();
		
		model.terminate();
		return "success";
	}

	/**
	 * Method to set particular record of an employee after clicking the respective
	 * edit button.
	 * 
	 * @return String
	 */
	public String edit() {
		try {
			PunishHistoryModel model = new PunishHistoryModel();
			model.initiate(context, session);
			model.getOneRecord(punishHistory);
			model.getPunishDetails(punishHistory);
			punishHistory.setEditFlag("false");
			punishHistory.setEditableFlag("true");
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
		return "success";

	}

	/**
	 * Method to delete particular record of an employee after clicking the respective
	 * delete button.
	 * 
	 * @return String
	 */

	/**
	 * Method to clear all the form fields
	 * 
	 * @return String
	 */
	public String reset() {
		try {
			punishHistory.setEmpId("");
			punishHistory.setEmpName("");
			punishHistory.setTokenNo("");
			punishHistory.setDispAction("");
			punishHistory.setDispActionId("");
			punishHistory.setAuthority("");
			punishHistory.setEffFromDate("");
			punishHistory.setLetterNo("");
			punishHistory.setCeoDate("");
			punishHistory.setPunishId("");
			punishHistory.setCeoOrder("");
			punishHistory.setPeriod("");
			punishHistory.setWef("");
			punishHistory.setRank("");
			punishHistory.setCenter("");
			punishHistory.setPunishList(null);
			punishHistory.setParacode("");
			punishHistory.setDescription("");
			punishHistory.setSerialNo("");
			punishHistory.setUploadFileName("");
			punishHistory.setUploadFileNameTxt("");
			punishHistory.setPunishStatus("A");
			punishHistory.setItrFlag(false);
			punishHistory.setEffToDate("");
			punishHistory.setDeleteFlag(true);//A
		} catch (Exception e) {
			logger.error(e);

		}
		return "success";
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
				poolName = "\\" + poolName;
			}
			fileName = request.getParameter("fileName");

			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				}
			}
			String path = getText("data_path") + "\\TMS\\" + poolName
					+ "\\Tickets\\" + fileName;

			oStream = response.getOutputStream();
			response.setHeader("Content-disposition", "attachment;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("-----in file not found catch", e);
			addActionMessage("File not found");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.info("in finally for closing");
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
	

	public String setPic() throws Exception{
		addNewRecord();
		PunishHistoryModel model = new PunishHistoryModel();
		model.initiate(context, session);
		Object addObj[] = new Object[1];
		String pid = punishHistory.getParacode();
		String var= "SELECT  PUNISH_DOCUMENT from HRMS_PUNISH where PUNISH_ID = "+pid;
		 
		Object[][] data = model.getSqlModel().getSingleResult(var);
		ArrayList<Object> list1 = new ArrayList<Object>();

		if (data.length > 0 && data!=null) {
			for (int i = 0; i < data.length; i++) {
	
		String result=String.valueOf(data[i][0]);
		String[] abc=result.split(",");
		String value="";
		for (int j = 0; j < abc.length; j++) {	
			PunishHistory bean = new PunishHistory();
			value=abc[j];
			bean.setUploadFil(value);
			System.out.println("abc["+ j+"]----------------->"+abc[j]);
			list1.add(bean);
		}
		}
			punishHistory.setKeepInformedList(list1);	
		}
	return SUCCESS;
	}
	
	
	public String cancelFunc(){
		try {
			PunishHistoryModel model = new PunishHistoryModel();
			model.initiate(context, session);
			model.getPunishDetails(punishHistory);
			punishHistory.setEditableFlag("false");
			getProfileImage();
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	public void getProfileImage(){
		PunishHistoryModel model = new PunishHistoryModel();
		model.initiate(context, session);
		 model.getImage(punishHistory);
		 String photo = punishHistory.getProfileUploadFileName();
		String str = (String) session.getAttribute("session_pool");
		punishHistory.setFlag("false");
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
