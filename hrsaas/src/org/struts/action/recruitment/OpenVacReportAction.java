 package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.OpenVacReport;
import org.paradyne.model.recruitment.OpenVacReportModel;
import org.struts.lib.ParaActionSupport;

public class OpenVacReportAction extends ParaActionSupport {

	OpenVacReport openvac;
		public void prepare_local() throws Exception {
			openvac=new OpenVacReport();
			openvac.setMenuCode(829);
		}

		public Object getModel() {
			return openvac;
		}
		
		public OpenVacReport getOpenvac() {
			return openvac;
		}

		public void setOpenvac(OpenVacReport openvac) {
			this.openvac = openvac;
		}

		/**
		 * @author AA0877
		 * Added for displaying open vacancies report 
		 * @return String
		 *  
		 */
		public String report() { 
			try {
				OpenVacReportModel model = new OpenVacReportModel();
				model.initiate(context, session);
				String[] colnames = { getMessage("serial.no"),
						getMessage("reqs.code"), getMessage("position"),
						getMessage("reqs.date"), getMessage("hiring.mgr"),
						getMessage("rec.name"),
						getMessage("RequiredDateLabel"),
						getMessage("TotalvacancyLabel"),
						getMessage("vacLabelAssigned"),
						getMessage("OpenvacancyLabel"),
						getMessage("ClosedvacancyLabel"),
						getMessage("ClosedDateLabel"),
						getMessage("overDueDayLabel"),
						getMessage("approvar.name") };
				model.getReport(request, response, openvac, colnames);
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		 
		
		
		/**
		 * Display the report on screen
		 * @return
		 */
		public String save(){
			try {
				OpenVacReportModel model = new OpenVacReportModel();
				model.initiate(context, session);
				boolean chekDup = false;
				boolean result = false;
				if (openvac.getSearchSetting().equals("B")
						|| (!openvac.getSettingName().trim().equals(
								openvac.getSettingNameDup().trim()))) {
					Object[][] data = model.checkDuplicate(openvac);
					if (data != null && data.length > 0) {
						chekDup = true;
						addActionMessage("Setting Name already exist.");
						openvac.setSearchSetting("B");
						return "success";
					} else {
						chekDup = false;
					}
					result = model.callSave(openvac);
					if (result) {
						addActionMessage(getMessage("save"));
					} else {
						addActionMessage(getMessage("save.error"));
					}
				} else {
					result = model.callUpdate(openvac);
					if (result) {
						addActionMessage(getMessage("update"));
					} else {
						addActionMessage(getMessage("update.error"));
					}

				}
				Object[][] repData = model.checkDuplicate(openvac);
				if (repData != null && repData.length > 0) {
					openvac.setSearchSetting(String.valueOf(repData[0][1]));
				}
				model.terminate();
				if (!chekDup) {
					viewSavedRecord();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "success"; 
		}
		
		
		/**
		 * Display the report on screen
		 * @return
		 */
		public String viewOnScreen(){
			try {
				OpenVacReportModel model = new OpenVacReportModel();
				model.initiate(context, session);
				model.callViewScreen(openvac, request);
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "viewOnScreen"; 
		}
		
		public String callBack(){
			try {
				OpenVacReportModel model = new OpenVacReportModel();
				model.initiate(context, session);
				model.callBack(openvac);
				model.callPreviousRecord(openvac);
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "success"; 
		}
		
		public String input(){
			try {
				OpenVacReportModel model = new OpenVacReportModel();
				model.initiate(context, session);
				model.input(openvac);
				model.callPreviousRecord(openvac);
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "success"; 
		}
		/**
		 * @author AA0877
		 * Added for clearing form 
		 * @return String
		 */
		public String reset(){
			try {
				openvac.setFrmDate("");
				openvac.setToDate("");
				openvac.setRecruiter("");
				openvac.setRecruiterName("");
				openvac.setRecruiterCode("");
				openvac.setReqname("");
				openvac.setReqCode("");
				openvac.setReportType("");
				openvac.setReportType("1");
				openvac.setReqname1("");
				openvac.setHiringManagerId("");
				openvac.setHiringManagerName("");
				openvac.setSelectedReq("");
				openvac.setPosition("");
				openvac.setPositionId("");
				openvac.setDateFilter("1");
				openvac.setMyPage("");
				openvac.setShow("");
				openvac.setDataLength("");
				openvac.setSelectedReqName("");
				openvac.setSelectedReqFlag("");
				openvac.setEditReqFlag("false");
				openvac.setSettingName("");
				openvac.setTotalVacAdvCom("");
				openvac.setAsgnVacAdvCom("");
				openvac.setOpenVacAdvCom("");
				openvac.setCloseVacAdvCom("");
				openvac.setTotalVacAdvTxt("");
				openvac.setAsgnVacAdvTxt("");
				openvac.setOpenVacAdvTxt("");
				openvac.setCloseVacAdvTxt("");
				openvac.setRequirdeByFromDateTxt("");
				openvac.setRequirdeByToDateTxt("");
				openvac.setSortBy("");
				openvac.setThenBy1("");
				openvac.setThenBy2("");
				openvac.setReqStatus("V");
				openvac.setRequirdeByFromDateTxt("");
				openvac.setRequirdeByToDateTxt("");
				openvac.setHidReportView("checked");
				openvac.setHidReportRadio("");
				openvac.setSortByAsc("checked");
				openvac.setSortByDsc("");
				openvac.setThenByOrder1Asc("checked");
				openvac.setThenByOrder1Dsc("");
				openvac.setThenByOrder2Asc("checked");
				openvac.setThenByOrder2Dsc("");
				openvac.setRequirdeByDateCom("1");
				openvac.setSearchSetting("B");
				openvac.setRadioStatus("");
				openvac.setRadioReq("");
				openvac.setRadioRecr("");
				openvac.setRadioHirMng("");
				openvac.setRadioPosition("");
				OpenVacReportModel model = new OpenVacReportModel();
				model.initiate(context, session);
				model.input(openvac);
				model.callPreviousRecord(openvac);
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "success";
		}
		
		public String displayReq(){ 
			try {
				OpenVacReportModel model = new OpenVacReportModel();
				model.initiate(context, session);
				model.displayReq(openvac);
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "viewReq";
		}
		
		public String viewSavedRecord(){
			try {
				OpenVacReportModel model = new OpenVacReportModel();
				model.initiate(context, session);
				model.callFilterSavedData(openvac);
				model.callSortSavedData(openvac);
				model.callColumnSavedData(openvac);
				model.callAdvanceSavedData(openvac);
				model.callSavedReqData(openvac);
				model.callPreviousRecord(openvac);
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "success"; 
		}
		
		/**
		 * @author AA0877
		 * Added For getting all Requisitions from Db
		 * @return String
		 */
		public String f9actionReqName()
		{
			 
			String query = "  SELECT DISTINCT REQS_NAME AS REQCODE,RANK_NAME  AS REQPOSITION ,TO_CHAR(REQS_DATE,'DD-MM-YYYY') AS REQDATE ," 
					 +" DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A' ,'Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'),REQS_CODE " 
					 +" FROM HRMS_REC_VACPUB_RECDTL" 
					 +" INNER JOIN  HRMS_REC_VACPUB_HDR ON(HRMS_REC_VACPUB_HDR.PUB_CODE = HRMS_REC_VACPUB_RECDTL.PUB_CODE)" 
					 +" INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE= HRMS_REC_VACPUB_HDR.PUB_REQS_CODE)" 
					 +" INNER JOIN HRMS_REC_REQS_VACDTL ON(HRMS_REC_REQS_VACDTL.VACAN_DTL_CODE = HRMS_REC_VACPUB_HDR.PUB_VACAN_DTLCODE)" 
				 	  +"INNER JOIN HRMS_RANK  ON(HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION) " 
					 +" WHERE REQS_APPROVAL_STATUS IN('A','Q') ORDER BY UPPER(REQS_NAME)";
				 
				String [] headers = {getMessage("reqs.code"),getMessage("position"),getMessage("reqs.date"),"Status"}; 
				 
				String [] headerWidth = {"35","35","15", "15"}; 
				 
				String [] fieldNames = {"reqname","common","common","common","reqCode"};
				 
				int [] columnIndex = {0,1,2,3,4}; 
				
				String submitFlag = "false"; 
				 
				String submitToMethod = "";
				 
				setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
				
				return "f9page";
		
		}
		
		/**
		 * @author AA0877
		 * Added for getting all recruiter names from db
		 * @return String
		 */
		public String f9actionRecruiter()
		{  
				String query = "SELECT EMP_TOKEN,(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) as name, EMP_ID FROM HRMS_EMP_OFFC ORDER BY upper(name)";
				 
				String [] headers = {getMessage("recruiterCode"), getMessage("rec.name")};
				 
				String [] headerWidth = {"50", "50"};
				 
				String [] fieldNames = {"common", "recruiterName","recruiterCode"};
				 
				int [] columnIndex = {0, 1,2};
				 
				String submitFlag = "false";
				 
				String submitToMethod = ""; 
				 
				setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
				
				return "f9page";
		
		}
		
		public String f9HiringManger()
		{  
				String query = "SELECT EMP_TOKEN,(EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) as name, EMP_ID FROM HRMS_EMP_OFFC ORDER BY upper(name)";
				 
				String [] headers = {getMessage("hrmgCode"), getMessage("hiring.mgr")};
				 
				String [] headerWidth = {"50", "50"};
				 
				String [] fieldNames = {"common", "hiringManagerName","hiringManagerId"};
				 
				int [] columnIndex = {0, 1,2};
				 
				String submitFlag = "false";
				 
				String submitToMethod = ""; 
				 
				setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
				
				return "f9page";
		
		}
		
		
		public String f9Position()
		{  
				String query = " SELECT RANK_NAME , RANK_ID FROM HRMS_RANK  ORDER BY UPPER(RANK_NAME)";
				 
				String [] headers = {getMessage("position")};
				 
				String [] headerWidth = {"100"};
				 
				String [] fieldNames = {"position","positionId"};
				 
				int [] columnIndex = {0, 1};
				 
				String submitFlag = "false";
				 
				String submitToMethod = ""; 
				 
				setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
				
				return "f9page";
		
		}
		
		
}
