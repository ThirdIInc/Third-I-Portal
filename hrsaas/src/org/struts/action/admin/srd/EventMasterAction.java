package org.struts.action.admin.srd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import org.paradyne.bean.admin.srd.EventMaster;
import org.paradyne.model.admin.srd.EventMasterModel;
import org.struts.lib.ParaActionSupport;

public class EventMasterAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EventMasterAction.class);

	EventMaster eventmaster;

	public void prepare_local() throws Exception {
		eventmaster = new EventMaster();
		eventmaster.setMenuCode(1158);
	}

	public Object getModel() {
		return eventmaster;
	}

	public EventMaster getEventmaster() {
		return eventmaster;
	}

	public void setEventmaster(EventMaster eventmaster) {
		this.eventmaster = eventmaster;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "\\" + poolName;
			}
			// for getting server path where configuration files are saved.
			String dataPath = getText("data_path") + "\\upload" + poolName
					+ "\\EmployeePortal\\";
			// logger.info("data path " + dataPath);
			eventmaster.setDataPath(dataPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewAttachedImage() throws IOException {
		OutputStream oStream = null;
		response.getOutputStream();
		FileInputStream fsStream = null;
		String fileName = "";
		String mimeType = "";
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "\\" + poolName;
			} // end of if
			fileName = request.getParameter("fileName");
			logger.info("fileName--->>>" + fileName);
			fileName = fileName.replace(".", "#");
			String[] extension = fileName.split("#");
			String ext = extension[extension.length - 1];
			fileName = fileName.replace("#", ".");
			logger.info("extext--->>>" + ext);
			if (ext.equals("pdf")) {
				mimeType = "acrobat";
			} // end of if
			else if (ext.equals("doc")) {
				mimeType = "msword";
			} // end of else if
			else if (ext.equals("xls")) {
				mimeType = "msexcel";
			} // end of else if
			else if (ext.equals("xlsx")) {
				mimeType = "msexcel";
			} // end of else
			else if (ext.equals("jpg")) {
				mimeType = "jpg";
			} // end of else if
			else if (ext.equals("txt")) {
				mimeType = "txt";
			} // end of else if
			else if (ext.equals("gif")) {
				mimeType = "gif";
			} // end of else if
			// if file name is null, open a blank document
			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				} // end of if
			} // end of if

			// for getting server path where configuration files are saved.
			String path = getText("data_path") + "\\upload\\" + poolName
					+ "\\EmployeePortal\\" + fileName;
			oStream = response.getOutputStream();
			if (ext.equals("pdf")) {
				// response.setHeader("Content-type",
				// "application/"+mimeType+"");
			} // end of if
			else {
				response.setHeader("Content-type", "application/" + mimeType
						+ "");
			}

			response.setHeader("Content-disposition", "inline;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			} // end of while

		} catch (FileNotFoundException e) {

			logger.error("-----in file not found catch", e);
			addActionMessage("proof document not found");
		} // end of catch
		catch (Exception e) {
			e.printStackTrace();
		} // end of catch
		finally {
			logger.info("in finally for closing");
			if (fsStream != null) {
				fsStream.close();
			} // end of if
			if (oStream != null) {
				oStream.flush();
				oStream.close();
			} // end of if
		} // end of finally
		// return null;
	}

	public String reset() {
		try {
			eventmaster.setEventModule("");
			eventmaster.setEventName("");
			eventmaster.setEventDesc("");
			eventmaster.setUploadFileName("");
			eventmaster.setUserUploadFileName("");
			eventmaster.setEventFromYear("");
			eventmaster.setEventToYear("");
			eventmaster.setEventDate("");
			eventmaster.setEventLocation("");
			eventmaster.setEventTypeCode("");
			eventmaster.setEventType("");
			eventmaster.setEventDivision("");
			eventmaster.setEventDivCode("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String addMultipleImages() {
		try {
			String[] srNo = request.getParameterValues("srNo"); // serial
			// no.
			String[] image = request.getParameterValues("uploadImageItt");
			String[] imagePath = request
					.getParameterValues("uploadImagePathItt");
			String[] uploadCategoryItt = request
			.getParameterValues("uploadCategoryItt");
			EventMasterModel model = new EventMasterModel();
			model.initiate(context, session);
			model.setImageList(srNo, image, imagePath, eventmaster,uploadCategoryItt);
			eventmaster.setUploadFileName("");
			eventmaster.setUserUploadFileName("");
			model.terminate();
		} catch (Exception e) {
			System.out.println("Exception in addMultipleProof--------" + e);
		}
		return SUCCESS;
	}

	public void displayData() {
		try {
			EventMasterModel model = new EventMasterModel();
			model.initiate(context, session);
			model.displayData(eventmaster);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String save() {
		try {
			EventMasterModel model = new EventMasterModel();
			model.initiate(context, session);
			String[] image = request.getParameterValues("uploadImageItt");
			String[] imagePath = request
					.getParameterValues("uploadImagePathItt");
			String[] uploadCategoryItt = request
			.getParameterValues("uploadCategoryItt");			
			if (eventmaster.getHiddenAutoCode().equals("")) {
				boolean result = model.save(eventmaster, image, imagePath,uploadCategoryItt);
				if (result) {
					addActionMessage("Record saved successfully");
					reset();

				} else {
					addActionMessage("Record can not be saved");
				}
			} else {
				boolean result = model.update(eventmaster, image, imagePath,uploadCategoryItt);
				if (result) {
					addActionMessage("Record updated successfully");
					reset();
				} else {
					addActionMessage("Record can not be updated");
				}
			}

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String setData() {
		try {
			EventMasterModel model = new EventMasterModel();
			model.initiate(context, session);
			reset();
			model.displayData(eventmaster);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * Method to select the Division
	 * @return String
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT  "
			+"  EVENT_NAME , EVENT_YEAR,TO_CHAR(EVENT_DATE,'DD-MM-YYYY'),EVENT_LOCATION , EVENT_CODE FROM HRMS_EVENT_MASTER ";

		query += " ORDER BY EVENT_CODE ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Event Title" ,"Event Year","Event Date" ,"Event Location"};
		String[] headerWidth = { "20" ,"20","20","20","20" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "eventName", "eventFromYear","eventDate","eventLocation","hiddenAutoCode" };
		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2,3,4 };
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
		String submitToMethod = "EventMaster_setData.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	public String deleteRecords(){		
		String[] imagePath = request.getParameterValues("hdeletePath");
		String eventCode = request.getParameter("hiddenAutoCode");
		eventmaster.setHiddenAutoCode(eventCode);
		EventMasterModel model = new EventMasterModel();
		try{			
			model.initiate(context, session);
			model.deleteRecords(eventmaster, imagePath);
			model.displayData(eventmaster);
			model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String f9eventType(){
		
		String query="SELECT EVENT_CODE, EVENT_NAME FROM HRMS_PORTAL_EVENT ORDER BY EVENT_CODE";
		String[] headers = { "Event Type Code" ,"Event Type"};
		String[] headerWidth = { "30" ,"70",};
		String[] fieldNames = { "eventTypeCode", "eventType"};
		int[] columnIndex = { 0, 1 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9EventDiv(){
		String query ="SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION ";
	 	if(eventmaster.getUserProfileDivision() !=null && eventmaster.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+eventmaster.getUserProfileDivision() +")"; 
			query+= " ORDER BY UPPER (DIV_NAME) ";	 
		String header =getMessage("eventDiv");
		String textAreaId =request.getParameter("divName");				
		String hiddenFieldId =request.getParameter("divCode");		
		String submitFlag ="";
		String submitToMethod ="";
		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
}
