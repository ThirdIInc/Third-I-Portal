 package org.struts.action.recruitment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.paradyne.bean.Recruitment.AdvertiseReport;
import org.paradyne.model.recruitment.AdvertiseReportModel;
import org.struts.lib.ParaActionSupport;

public class AdvertiseVacReportAction extends ParaActionSupport {

	AdvertiseReport bean;
		@Override
		public void prepare_local() throws Exception {
			// TODO Auto-generated method stub
			bean=new AdvertiseReport();
			bean.setMenuCode(922);
			
		}

		public Object getModel() {
			// TODO Auto-generated method stub
			return bean;
		}
		
		
		public AdvertiseReport getbean() {
			return bean;
		}

		public void setbean(AdvertiseReport bean) {
			this.bean = bean;
		}

		/**
		 * @author AA0877
		 * Added for displaying open vacancies report 
		 * @return String
		 */
		public String report() { 
			AdvertiseReportModel model=new AdvertiseReportModel();
			model.initiate(context, session);
 
			String[] colnames = {getMessage("serial.no"),getMessage("reqs.code"),getMessage("position"),getMessage("numOfVacAdvLabel"),getMessage("modeLabel"),getMessage("agencuNameChkLabel"),
				getMessage("advertiseDateChkLabel"),getMessage("onlineAdvCostLabel"),getMessage("onilineRespAdvLabel")};
		  	model.getReport(request,response ,bean,colnames);
			model.terminate();
			return null;
		}
		 
		
		public String save(){
			AdvertiseReportModel model=new AdvertiseReportModel();
			model.initiate(context, session);
			boolean chekDup=false;
			boolean result=false;
			if(bean.getSearchSetting().equals("B") || (! bean.getSettingName().trim().equals(bean.getSettingNameDup().trim()))){ 
				 Object[][] data  =model.checkDuplicate(bean);
				if(data!= null && data.length>0){ 
					chekDup =true;
					addActionMessage("Setting Name already exist."); 
					bean.setSearchSetting("B"); 
					//reset();
					return "success"; 
				}else{
					chekDup =false;
				} 
				 result = model.callSave(bean);  
				if(result)
				{
					addActionMessage(getMessage("save"));
				}else{
					addActionMessage(getMessage("save.error"));
				}
			}else{
				
				 result = model.callUpdate(bean);  
				if(result)
				{
					addActionMessage(getMessage("update"));
				}else{
					addActionMessage(getMessage("update.error"));
				}
				
			}
			 Object[][] repData  = model.checkDuplicate(bean);
				if(repData!= null && repData.length>0){
					bean.setSearchSetting(String.valueOf(repData[0][1])); 
				}
			model.terminate(); 
			if(!chekDup){
			viewSavedRecord();
			}
			return "success"; 
		}
		
		
		/**
		 * Display the report on screen
		 * @return
		 */
		public String viewOnScreen(){
			AdvertiseReportModel model=new AdvertiseReportModel();
			model.initiate(context, session);
			model.callViewScreen(bean,request);  
			model.terminate();
			return "viewOnScreen"; 
		}
		
		public String callBack(){
			AdvertiseReportModel model=new AdvertiseReportModel();
			model.initiate(context, session);
			model.callBack(bean);
			model.callPreviousRecord(bean); 
			model.terminate();
			return "success"; 
		}
		
		public String input(){
			AdvertiseReportModel model=new AdvertiseReportModel();
			model.initiate(context, session);
			model.input(bean);
			model.callPreviousRecord(bean);
			model.terminate();
			return "success"; 
		}
		/**
		 * @author AA0877
		 * Added for clearing form 
		 * @return String
		 */
		public String reset(){
			bean.setFrmDate("");
			bean.setToDate(""); 
			bean.setReqname("");
			bean.setReqCode("");
			bean.setReportType("");
			bean.setReportType("1");   
			bean.setReqname1(""); 
			bean.setSelectedReq("");
			bean.setPosition("");
			bean.setPositionId("");
			bean.setDateFilter("1");  
			bean.setMyPage("");
			bean.setShow("");
			bean.setDataLength("");
			bean.setSelectedReqName("");
			bean.setSelectedReqFlag("");
			bean.setEditReqFlag("false"); 
			bean.setSettingName("");  
			
		    bean.setNumOfVacAdvCom("");  
 		    bean.setNumOfVacAdvTxt("");  
 		    bean.setOnlineRespAdvCom("");  
 		    bean.setOnlineRespAdvTxt("");  
 		    bean.setCostAdvCom("");  
 		    bean.setCostAdvTxt("");   
			bean.setSortBy("");
			bean.setThenBy1("");
			bean.setThenBy2(""); 
			bean.setReqStatus("V");  
			bean.setHidReportView("checked");
			bean.setHidReportRadio("");   
			bean.setSortByAsc("checked");
			bean.setSortByDsc("");  
			bean.setThenByOrder1Asc("checked");
			bean.setThenByOrder1Dsc("");  
			bean.setThenByOrder2Asc("checked");
			bean.setThenByOrder2Dsc("");   
			bean.setSearchSetting("B");
			bean.setModeOfAdvertise("1"); 
			bean.setRadioStatus("");
			bean.setRadioReq("");
			bean.setRadioPosition("");  
			
			AdvertiseReportModel model=new AdvertiseReportModel();
			model.initiate(context, session);
			model.input(bean);
			model.callPreviousRecord(bean);
			model.terminate();
			return "success";
		}
		
		public String displayReq(){ 
			AdvertiseReportModel model=new AdvertiseReportModel();
			model.initiate(context, session);
			model.displayReq(bean);
			model.terminate();
			return "viewReq";
		}
		
		public String viewSavedRecord(){
			AdvertiseReportModel model=new AdvertiseReportModel();
			model.initiate(context, session);
			model.callFilterSavedData(bean); 
			model.callSortSavedData(bean); 
			model.callColumnSavedData(bean);
			model.callAdvanceSavedData(bean);
			model.callSavedReqData(bean); 
			model.callPreviousRecord(bean);
			model.terminate();
			return "success"; 
		}
		
		/**
		 * @author AA0877
		 * Added For getting all Requisitions from Db
		 * @return String
		 */
		public String f9actionReqName()
		{
			 
			String query = " SELECT REQS_NAME,RANK_NAME,TO_CHAR(REQS_DATE ,'DD-MM-YYYY'), "
				+" DECODE(REQS_APPROVAL_STATUS,'P','Pending','R','Rejected','A'  ,'Approved','H','On Hold','B','New Requisition','Q','Quick Requisition'), "
				+" HRMS_REC_ADVT_HDR.REQS_CODE FROM HRMS_REC_ADVT_HDR  "
				+" INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_ADVT_HDR.REQS_CODE ) " 
				+" INNER JOIN  HRMS_RANK ON (RANK_ID =REQS_POSITION) "
				+" WHERE REQS_APPROVAL_STATUS IN ('A','Q') ORDER BY REQS_DATE DESC ";
				 
				String [] headers = {getMessage("reqs.code"),getMessage("position"),getMessage("reqs.date"),"Status"}; 
				 
				String [] headerWidth = {"35","30","15", "20"}; 
				 
				String [] fieldNames = {"reqname","common","common","common","reqCode"};
				 
				int [] columnIndex = {0, 1,2,3,4}; 
				
				String submitFlag = "false"; 
				 
				String submitToMethod = "";
				 
				setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
				
				return "f9page";
		
		}
		
		 
 
 
		
		
		public String f9Position()
		{  
				String query = " SELECT RANK_NAME , RANK_ID FROM HRMS_RANK ORDER BY UPPER(RANK_NAME)";
				 
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
