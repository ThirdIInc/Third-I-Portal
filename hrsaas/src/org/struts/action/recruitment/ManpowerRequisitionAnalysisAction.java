package org.struts.action.recruitment;
/**
 * Author:Pradeep Kumar Sahoo
 * Date:31-07-2009
 */
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.ManpwrReqsnAnalysis;
import org.paradyne.model.recruitment.CandidateStatusModel;
import org.paradyne.model.recruitment.ManpowerRequisitionAnalysisModel;
import org.struts.lib.ParaActionSupport;
public class ManpowerRequisitionAnalysisAction  extends ParaActionSupport{
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ManpowerRequisitionAnalysisAction.class);
	ManpwrReqsnAnalysis bean=null;
	
	public void prepare_local() throws Exception {
		bean =  new ManpwrReqsnAnalysis(); 
		bean.setMenuCode(921);

	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public ManpwrReqsnAnalysis getBean() {
		return bean;
	}

	public void setBean(ManpwrReqsnAnalysis bean) {
		this.bean = bean;
	}
	
	public String input(){
	
		if(!(bean.getMraRepCode().equals("") || bean.getMraRepCode().equals("null"))){
			ManpowerRequisitionAnalysisModel  model = new ManpowerRequisitionAnalysisModel();
			model.initiate(context, session);
			
			//String quer = "SELECT MPRREP_CODE,MPRREP_USER_NAME FROM HRMS_REC_MPRREP_FILTERS WHERE MPRREP_CODE="+bean.getMraRepCode();
			//  Object[][] iterator = model.getSqlModel().getSingleResult(quer);
			//		HashMap mp = new HashMap();
			//		for (int i = 0; i < iterator.length; i++) {
			//			mp.put(String.valueOf(iterator[i][0]),String.valueOf(iterator[i][1]));
				
			//		}
			//		bean.setHashMap(mp);
			//model.getFilterDetails(bean);
			//model.getSortingDetails(bean);
			//model.getAdvFilter(bean);
			//model.getColumnDef(bean,request);
			//model.getReqsnDetails(bean);
			model.terminate();
			reset();
			getFilterDetails();
		}
	
		return "success";
	}
	
	public void prepare_withLoginProfileDetails(){
		ManpowerRequisitionAnalysisModel  model = new ManpowerRequisitionAnalysisModel();
		model.initiate(context, session);
		model.callSavedLIst(bean);
		model.terminate();
}

/**
 * following function is called to display the filter options,sorting options,advance filter options
 * and column definitions etc. from the database when any value is selected from the saved setting names
 * drop down list for the corresponding user.   
 * @return
 */
public String getFilterDetails(){
			ManpowerRequisitionAnalysisModel  model = new ManpowerRequisitionAnalysisModel();
			model.initiate(context,session);
			model.getFilterDetails(bean);
			model.callSavedLIst(bean);
			//model.getUserSetting(bean);
			model.getSortingDetails(bean);
			model.getAdvFilter(bean);
			model.getColumnDef(bean,request);
			model.getReqsnDetails(bean);
			model.terminate();
     return "success";	
}
	
/**
 * following function is called to save the setting details into the database.
 * @return
 */
	public String saveSettings(){
		try{
			ManpowerRequisitionAnalysisModel  model = new ManpowerRequisitionAnalysisModel();
			model.initiate(context, session);
			
			if(bean.getMraRepCode().equals("") || bean.getMraRepCode().equals("null")){
				Object[][] obj=model.chkUser(bean);
					if(obj!=null && obj.length>0){
						addActionMessage("Setting name already exists.");
					}else{
					
					boolean result=model.saveSettings(bean);
				
					if(result){ 
						model.callSavedLIst(bean);
						Object[][] orderObj=model.chkUser(bean);
						bean.setSearchSetting(String.valueOf(orderObj[0][1]));
						getFilterDetails();
				    	addActionMessage("Record saved successfully.");
					}
					}	
			}else{
				String str=(String)bean.getHashMap().get(bean.getSearchSetting());
				    if(str.equalsIgnoreCase(bean.getSettingName())){
				    	model.updateSettings(bean);
				    	model.callSavedLIst(bean);
				    	Object[][] orderObj=model.chkUser(bean);
						bean.setSearchSetting(String.valueOf(orderObj[0][1]));
				       	addActionMessage("Record updated successfully.");
				    	
				    }else{
				    	Object[][] obj=model.chkUser(bean);
						if(obj!=null && obj.length>0){
							Object[][] orderObj=model.chkUser(bean);
							bean.setSearchSetting(String.valueOf(orderObj[0][1]));
							addActionMessage("Setting name already exists.");
							
						}else{
						
						boolean result=model.saveSettings(bean);
					
						if(result){
							model.callSavedLIst(bean);
							Object[][] orderObj=model.chkUser(bean);
							bean.setSearchSetting(String.valueOf(orderObj[0][1]));
							addActionMessage("Record saved successfully.");
							
						   }
					
						}
						
				     }
				    getFilterDetails();
				}
			model.terminate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
		
	}
	
	/**
	 * following function is used to reset the fields.
	 * following function is called when Reset button is clicked. 
	 * @return
	 */
	
	public String reset(){
		bean.setShowReqsnFlag("false");
		bean.setExportAllData("Y");
		bean.setReqsnFlg("false");
		bean.setPositionFlg("false");
		bean.setReqsnCode("");
		bean.setFirstAsc("");
		bean.setSecDesc("");
		bean.setThirdAsc("");
		bean.setReqsnDate("");
		bean.setReqsnStatus("");
		bean.setReqsnName("");
		bean.setSelectedReqName("");
		bean.setSelectedReq("");
		bean.setRepType("");
		bean.setPositionId("");
		bean.setPositionName("");
		bean.setFrmDate("");
		bean.setTDate("");
		bean.setAdvVacVal("");
		bean.setAdvOverDueVal("");
		bean.setAdvVacOpt("");
		bean.setAdvOverDueOpt("");
		bean.setSettingName("");
		bean.setMraRepCode("");
		bean.setMyPage("");
		bean.setScrnReqCode("");
		bean.setReportFlag("false");
		bean.setCheckFlag("false");
		bean.setRadio1("false");
		bean.setRadio2("false");
		bean.setRadio3("false");
		bean.setRadioFlag1("false");
		bean.setRadioFlag2("false");
		bean.setRadioFlag3("false");
		bean.setRadioFlag("false");
		bean.setHidReqByDate("Y");
		bean.setHidReqsn("Y");
		bean.setHidReqsnDate("Y");
		bean.setHidNoOfVac("Y");
		bean.setHidVacStatus("Y");
		bean.setHidOverDue("Y");
		bean.setFirstSort("");
		bean.setFirstDesc("");
		bean.setSecAsc("");
		bean.setSecSortBy("");
		bean.setThirdDesc("");
		bean.setThirdSort("");
		bean.setSearchSetting("");
		bean.setHidOpenVac("Y");
		bean.setDivId("");
		bean.setHidFirstAsc("A");
		bean.setHidFirstDesc("");
		bean.setHidSecAsc("A");
		bean.setHidSecDesc("");
		bean.setHidThirdAsc("A");
		bean.setHidThirdDesc("");
		bean.setHidPosition("Y");
		bean.setHidClosedDate("Y");
		bean.setHidTotalCtc("Y");
		
		bean.setHdrHrngMngrFlag("false");
		bean.setHdrRecruitNameFlag("false");
		bean.setHdrApprvrNameFlag("false");
		bean.setHidHiringMngr("Y");
		bean.setHidApprvrName("Y");
		bean.setHidRecruitName("Y");
		bean.setItrHrngMngrFlag("false");
		bean.setItrApprvrNameFlag("false");
		bean.setItrRecruitNameFlag("false");
	
		bean.setHidReqsStatus("Y");
		bean.setHidApprvStatus("Y");
		bean.setHdrApprvStatFlag("false");
		bean.setHdrReqsStatFlag("false");
		bean.setHdrPosFlag("false");
		bean.setItrReqsStatFlag("false");
		bean.setIrtApprvStatus("");
		bean.setItrApprvStatFlag("false");
		bean.setItrPosFlag("false");
		bean.setItrReqsStatus("");
		bean.setAdvApprvStat("1");
		bean.setNoDataReq("false");
		return SUCCESS;
	}
	
	/**
	 * following function is called to display the requisition details when select requisition or edit requisition 
	 * button is clicked.
	 * @return
	 */
	public String displayReq(){ 
		ManpowerRequisitionAnalysisModel  model = new ManpowerRequisitionAnalysisModel();
		model.initiate(context, session);
		model.displayReq(bean);
		model.terminate();
		return "viewReqAnalysis";
	}
	
/**
 * following function is called when Report Type radio button 
 * is checked and Generate Report button is clicked.
 * @return
 */	
	public String generateReport(){
		
		ManpowerRequisitionAnalysisModel  model = new ManpowerRequisitionAnalysisModel();
		model.initiate(context, session);
		model.getManpwrAnalysisReport(bean,response,getMessage("serial.no"),getMessage("reqs.code"),getMessage("reqs.date"),getMessage("position"),
				
				getMessage("apprvStatus"),getMessage("reqsStatus"),getMessage("totCtc"),getMessage("noofvac"),getMessage("reqdbydate"),getMessage("clsdDt"),getMessage("overdue"),getMessage("hiringMngr"),getMessage("apprvrName"),getMessage("recruitName"));
		model.terminate();
		return null;
	}
	
/**
 * following function is called to generate the data page wise when view on screen is checked.
 * @return
 */	
	public String showPagewise(){
		try{
			
			ManpowerRequisitionAnalysisModel  model = new ManpowerRequisitionAnalysisModel();
			model.initiate(context, session);
			model.getDisplayInJsp(bean,request);
			model.terminate();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "pageWise";
	}

	/**
	 * following function is called to generate the report when 
	 * Export to Pdf,Export to Xls or Export to Doc button is clicked.
	 *@return
	 */
 public String showReport(){
		ManpowerRequisitionAnalysisModel  model = new ManpowerRequisitionAnalysisModel();
		model.initiate(context, session);
		String report=request.getParameter("reportType");
		bean.setRepType(report);
model.getManpwrAnalysisReport(bean,response,getMessage("serial.no"),getMessage("reqs.code"),getMessage("reqs.date"),getMessage("position"),
				
				getMessage("apprvStatus"),getMessage("reqsStatus"),getMessage("totCtc"),getMessage("noofvac"),getMessage("reqdbydate"),getMessage("clsdDt"),getMessage("overdue"),getMessage("hiringMngr"),getMessage("apprvrName"),getMessage("recruitName"));
		model.terminate();
	 return null;
 }
	
	/**
	 * following function is called to display the requisition details when the requisition search window is clicked.
	 * @return
	 */
	public String f9Requisition()
	{
			String query = "SELECT REQS_NAME,TO_CHAR(REQS_DATE,'DD-MM-YYYY'),REQS_CODE FROM HRMS_REC_REQS_HDR WHERE REQS_APPROVAL_STATUS NOT IN('B','P','H','R') ORDER BY REQS_DATE DESC";
			String [] headers = {getMessage("reqs.code"),getMessage("reqs.date")};
			String [] headerWidth = {"75","25"};
			String [] fieldNames = {"reqsnName","viewReqsDate", "reqsnCode"};
			int [] columnIndex = {0, 1,2};
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			return "f9page";
	
	}
		
/**
 * following function is called to display the Position details
 * when Position search window is clicked
 * @return
 * @throws Exception
 */	
	public String f9Position()throws Exception {

		String query = 	" SELECT DISTINCT RANK_NAME,RANK_ID FROM HRMS_RANK "+
						" ORDER BY RANK_ID "; 

					
		String []headers ={getMessage("position")};
		String []headerwidth={"40"};
		String []fieldNames={"positionName","positionId"};
		int []columnIndex={0,1};
		String submitFlage ="false";
		String submitToMethod = "";
		setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
		return "f9page";
	}

}
