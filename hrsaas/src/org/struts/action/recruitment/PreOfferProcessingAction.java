package org.struts.action.recruitment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.paradyne.bean.Recruitment.PreOfferProcessing;
import org.paradyne.model.recruitment.PreOfferProcessingModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author ganesh
 * date : 10/11/2010
 *  
 */

public class PreOfferProcessingAction extends ParaActionSupport {
	
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(PreOfferProcessingAction.class);


	PreOfferProcessing preoffer;

	@Override
	public void prepare_local() throws Exception {
		preoffer = new PreOfferProcessing();
		 preoffer.setMenuCode(786);

	}

	public Object getModel() {
		
		return preoffer;
	}

	public PreOfferProcessing getBgcheck() {
		return preoffer;
	}

	public void setBgcheck(PreOfferProcessing preoffer) {
		this.preoffer = preoffer;
	}

	/*
	 * Pop up window Background check Records. 
	 *  @return String f9page
	 */
	public String f9search() {
		try {
			//
			String query = " SELECT CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME as name,"
						+" HRMS_REC_REQS_HDR.REQS_NAME,PRE_CODE,PRE_REQS_CODE,PRE_CAND_CODE,PRE_CHECK_LIST," +
							//	"PRE_CHECK_TYPE," +
								" HRMS_REC_PARTNER.REC_PARTNERNAME,PRE_AGENCYCODE "
						+" 	FROM HRMS_REC_PRECHECK "
						+" 	INNER JOIN HRMS_REC_CAND_DATABANK ON (HRMS_REC_PRECHECK.PRE_CAND_CODE=HRMS_REC_CAND_DATABANK.CAND_CODE)"
						+" 	INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_PRECHECK.PRE_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
						+" 	left JOIN HRMS_REC_PARTNER ON (HRMS_REC_PARTNER.REC_CODE=HRMS_REC_PRECHECK.PRE_AGENCYCODE)" +
						 " ORDER BY PRE_CODE  ";
			//String[] headers = {"Candidate Name","Requisiton Code"};
			String[] headers = {getMessage("cand.name"),getMessage("reqs.code")};
			String[] headerWidth = {"50","40" };                                                               //,"bgCheckType"
			String[] fieldNames = {"candidateName","reqName","bgcheckCode","reqCode","candidateCode","checkListType","outsourceAgencyName","outsourceAgencyCode" };
			
			//int[] columnIndex = {0,1,2,3,4,5,6,7,8};
			int[] columnIndex = {0,1,2,3,4,5,6,7};
			String submitFlag = "true";
			String submitToMethod = "PreOfferProcessing_f9searchdtl.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {

			return "";
		} //end of try-catch block
	}

	
	
	public String f9searchdtl() {
		getNavigationPanel(3);
		PreOfferProcessingModel bgmodel = new PreOfferProcessingModel();
		bgmodel.initiate(context, session);
		if (String.valueOf(preoffer.getCheckListType()) != "") {
		request.setAttribute("listname",String.valueOf(preoffer.getCheckListType()));
		}
		bgmodel.f9searchdtl(preoffer);
		preoffer.setButtonpanelcode("1");
		bgmodel.terminate();
		
		return "PageView";
			
	}
	/**Added by Pradeep on Date:17-06-2009 
	 * following function is called to set the offer status of the candidate when 
	 * the corresponding candidate is selected from the candidate pop up window. 
	 * @return
	 */
	public String getOffStatus(){
		getNavigationPanel(2);
		PreOfferProcessingModel model = new PreOfferProcessingModel();
		model.initiate(context,session);
		model.showOffer(preoffer);
		model.terminate();
		return "success1";
	}
	
	
	/*
	 * Pop up window for Candidate List 
	 *  @return String f9page
	 */  
	public String f9Candidate() {
		try {
			String query = "SELECT CAND_FIRST_NAME ||' '||CAND_MID_NAME||' '||CAND_LAST_NAME,CAND_CODE from HRMS_REC_CAND_DATABANK order by CAND_FIRST_NAME ";
			/*String[] headers = { "Candidate Code","Candidate Name" };
			String[] headerWidth = { "20","80" };
			String[] fieldNames = { "candidateCode", "candidateName" };
		
			int[] columnIndex = { 0, 1 };*/
			//String[] headers = { "Candidate Name" };
			String[] headers = {getMessage("cand.name")};
			String[] headerWidth = {"80" };
			String[] fieldNames = {  "candidateName","candidateCode" };
		    int[] columnIndex = { 0, 1 };
			String submitFlag = "true";
			String submitToMethod = "PreOfferProcessing_getOffStatus.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {

			return "";
		} //end of try-catch block
	}
	/*
	 * Pop up window for Requisition 
	 *  @return String f9page
	 */

	public String f9Requistion() {
		try {
			String query = " select REQS_NAME,RANK_NAME,"
					+" HRMS_DIVISION.DIV_NAME,hrms_center.CENTER_NAME,HRMS_DEPT.DEPT_NAME,REQS_CODE"	
					+" from HRMS_REC_REQS_HDR "
					+" inner join HRMS_DIVISION ON (HRMS_DIVISION.DIV_ID=HRMS_REC_REQS_HDR.REQS_DIVISION)"
					+" inner join hrms_center ON (hrms_center.CENTER_ID=HRMS_REC_REQS_HDR.REQS_BRANCH)"
					+" inner join HRMS_DEPT ON (HRMS_DEPT.DEPT_ID=HRMS_REC_REQS_HDR.REQS_DEPT)"
					+" inner join HRMS_RANK ON (HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
					+" inner join HRMS_REC_VACPUB_HDR on (HRMS_REC_VACPUB_HDR.PUB_REQS_CODE=HRMS_REC_REQS_HDR.REQS_CODE)"
					//+" WHERE REQS_APPROVAL_STATUS IN ('A','Q')"
					+" order by REQS_CODE asc";
		//	String[] headers = { "Requisition Code","Position","Division","Branch","Department" };
			String[] headers = {getMessage("reqs.code"),getMessage("position"),getMessage("division"),getMessage("branch"),getMessage("department")};
			String[] headerWidth = { "20","20","20","20","20" };
			String[] fieldNames = {"reqName","position","division","branch","department","reqCode"};
			int[] columnIndex = { 0,1,2,3,4,5 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {

			return "";
		} //end of try-catch block
	}
	/*
	 * Pop up window for Out source Agency 
	 *  @return String f9page
	 */
	public String f9outsource() {
		try {
			String query = " SELECT REC_PARTNERNAME,DECODE(REC_TYPE,'C ','Consultant','AA','Advertising Agency','O ','Outsourcing Agency'),REC_CODE FROM HRMS_REC_PARTNER ORDER BY REC_CODE ";
			String[] headers = { "Outsource Agency Name","Partner Type" };
			String[] headerWidth = { "40","40" };
			String[] fieldNames = {"outsourceAgencyName","partnertype","outsourceAgencyCode"};
			int[] columnIndex = { 0,1,2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);

			return "f9page";
		} catch (Exception e) {

			return "";
		} //end of try-catch block
	}
	
	
	/*
	 * method for setting check list details.
	 */
	public String showCheckList() {
		
		try {
			getNavigationPanel(2);
			PreOfferProcessingModel bgmodel = new PreOfferProcessingModel();
			bgmodel.initiate(context, session);
			if (String.valueOf(preoffer.getCheckListType()) != "") {
				
				preoffer.setHiddenstatus(preoffer.getCheckListType());
				request.setAttribute("listname",String.valueOf(preoffer.getCheckListType()));
				
			bgmodel.showCheckList(preoffer);
			}
			bgmodel.terminate();
		} catch (Exception e) {
			
		}
		return "success1";
	}

	/*
	 *  @ saving back ground check application details.
	 */
	
	public String save() {
			
		PreOfferProcessingModel bgmodel = new PreOfferProcessingModel();
		bgmodel.initiate(context, session);
		preoffer.setChecklistTable(true);
		boolean res=false;
		  
		Object [] ckcode=request.getParameterValues("checkListitemcode");
		Object [] Response=request.getParameterValues("checkListresponce");
		Object [] comments=request.getParameterValues("checkListComments");
		
		Object [] dtlproof=request.getParameterValues("uploadLocFileName");
        
		 if (String.valueOf(preoffer.getCheckListType()) != "") {
				request.setAttribute("listname",String.valueOf(preoffer.getCheckListType()));
			}
		if(preoffer.getBgcheckCode().equals(""))
		{
			res=bgmodel.save(preoffer,ckcode,Response,comments,dtlproof );  
			
			if(res)
			{ 
			addActionMessage("Record saved successfully."); 
			getNavigationPanel(3);
			preoffer.setButtonpanelcode("2");
			}
			else
			{ 
			addActionMessage("Record can not saved."); 
			getNavigationPanel(2);
			preoffer.setButtonpanelcode("1");
			}
			
		}
		else
		{	
			res=bgmodel.update(preoffer,ckcode,Response,comments,dtlproof ); 
		
		   if(res)
			{
			addActionMessage("Record updated successfully."); 
			getNavigationPanel(3);
			preoffer.setButtonpanelcode("2");
			}
		  else
		  {	
		  addActionMessage("Record can not updated.");
		  getNavigationPanel(2);
		   // preoffer.setButtonpanelcode("1");
		  }
		
		}
		
		bgmodel.f9searchdtl(preoffer);
		bgmodel.terminate();
		return "PageView";
	}
	
	/*
	 *  @ setting conducted back ground check application details.
	 */
	
	public String conducted() {
		
		getNavigationPanel(2);
		preoffer.setButtonpanelcode("1");
		PreOfferProcessingModel bgmodel = new PreOfferProcessingModel();
		bgmodel.initiate(context, session);
		System.out.println("preoffer.getChkreqcode() value"+preoffer.getChkreqcode());
		System.out.println("preoffer.getChkcandidatecode() value"+preoffer.getChkcandidatecode());
		if(preoffer.getChkreqcode()!="")
			if(preoffer.getChkcandidatecode()!="")
				bgmodel.conducted(preoffer);
		
		bgmodel.terminate();
		return "success1";
	}
	
	
	
	
	public String callstatus(){  //retrieving application details 
		try {
			getNavigationPanel(1);
			PreOfferProcessingModel bgmodel = new PreOfferProcessingModel();
			bgmodel.initiate(context, session);
			//String status = request.getParameter("bgStatus");
			String status = request.getParameter("status");
			if (status.equals("C")) {
				preoffer.setBgStatus("C");
				status = "C";
				preoffer.setConduct("true");

			} else {
				preoffer.setBgStatus("P");
				status = "P";
				preoffer.setConduct("false");
			}
			preoffer.setButtonpanelcode("1");
			request.setAttribute("stat", status);
			bgmodel.setBackgroundList(preoffer, status,request);
			bgmodel.terminate();
		} catch (Exception e) {
			
		}
		return "success";
	}
	
	
	
	/*
	 *  @ setting pending back ground check application details.
	 */
	public String input() throws Exception{
		/*Default Method with default modeCode(1)*/		
		getNavigationPanel(1);
		PreOfferProcessingModel bgmodel = new PreOfferProcessingModel();
		bgmodel.initiate(context,session);
		
		 preoffer.setButtonpanelcode("1");
		bgmodel.setBackgroundList(preoffer,"P", request);
		
		bgmodel.terminate();
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
				poolName = "/" + poolName;
			}
			fileName = request.getParameter("fileName");
			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				}
			}
			// for getting server path where configuration files are saved.
			String dataPath = getText("data_path") + "/upload" + poolName
			+ "/Travel/"+ fileName;
			oStream = response.getOutputStream();

			//response.setHeader("Content-type", "application/msword");
			response.setHeader("Content-disposition", "attachment;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(dataPath);

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
	
	
	/*
	 *  @ new back ground check application.
	 */
	public String addNew() throws Exception{
		
		getNavigationPanel(2);
		PreOfferProcessingModel bgmodel = new PreOfferProcessingModel();
		bgmodel.initiate(context,session);
		
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String dataPath = getText("data_path") + "/upload" + poolName
					+ "/Travel/";
			// logger.info("data path " + dataPath);
			preoffer.setDataPath(dataPath);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		reset();
		preoffer.setButtonpanelcode("1");
		bgmodel.terminate();
		return "success1";
		
	}
	
	/*
	 *  @ delete back ground check application.
	 */
	public String delete() throws Exception {
		
		PreOfferProcessingModel model = new PreOfferProcessingModel();
		model.initiate(context, session);
		
		boolean result;
		result = model.deletepreoffer(preoffer);
		if (result) {
			getNavigationPanel(2);
			addActionMessage("Record deleted successfully !");
			
		}//end of if
		else {
			getNavigationPanel(3);
			addActionMessage("Record can't be deleted as \n it is associated with some other record");
		}//end of else
		model.terminate();
		return "success1";
	}
	
	
	
	/*
	 *  @ editing back ground check application.
	 */
	
	public String edit() throws Exception{
		
		getNavigationPanel(2);
		PreOfferProcessingModel bgmodel = new PreOfferProcessingModel();
		bgmodel.initiate(context, session);
		preoffer.setButtonpanelcode("3");
		 if (String.valueOf(preoffer.getCheckListType()) != "") {
				request.setAttribute("listname",String.valueOf(preoffer.getCheckListType()));
			}
		
		bgmodel.bgCheckRecord(preoffer);
		bgmodel.terminate();
		return "success1";
		
		
	}
	
	
public String cancelThrd() throws Exception{
	System.out.println("u r at cancelThrd  333");
	getNavigationPanel(3);
	PreOfferProcessingModel bgmodel = new PreOfferProcessingModel();
	bgmodel.initiate(context, session);
	
	 if (String.valueOf(preoffer.getCheckListType()) != "") {
			request.setAttribute("listname",String.valueOf(preoffer.getCheckListType()));
		}
	bgmodel.bgCheckRecord(preoffer);
	preoffer.setButtonpanelcode("1");
    bgmodel.terminate();
		return "PageView";
		
	}
public String cancelFirst() throws Exception{
	getNavigationPanel(1);
	System.out.println("u r at cancelFirst  111");
	PreOfferProcessingModel bgmodel = new PreOfferProcessingModel();
	bgmodel.initiate(context, session);
	reset();
	 bgmodel.setBackgroundList(preoffer,"P",request);
	 bgmodel.terminate();
	return "success";
	
}
	public String cancelSec() throws Exception{
		getNavigationPanel(2);
		System.out.println("u r at cancelSec  222");
		PreOfferProcessingModel bgmodel = new PreOfferProcessingModel();
		bgmodel.initiate(context, session);
		 if (String.valueOf(preoffer.getCheckListType()) != "") {
				request.setAttribute("listname",String.valueOf(preoffer.getCheckListType()));
			}
		bgmodel.bgCheckRecord(preoffer);
		preoffer.setButtonpanelcode("1");
		bgmodel.terminate();
		
		return "success1";
		
	}
	
	
	
	public String cancelFrth() throws Exception{
		getNavigationPanel(1);
		System.out.println("u r at cancelFrth  444");
		PreOfferProcessingModel bgmodel = new PreOfferProcessingModel();
		bgmodel.initiate(context, session);
	    bgmodel.setBackgroundList(preoffer,"P",request);
		bgmodel.terminate();
		return "success";
		
	}
	
	public String callPage() throws Exception {
		getNavigationPanel(1);
		PreOfferProcessingModel model = new PreOfferProcessingModel();
		model.initiate(context, session);
		String status =preoffer.getBgStatus();
			//request.getParameter("status");
		
		 System.out.println("callPage callPage.........!!"+status);
		
		if (status.equals("C")) {
			preoffer.setBgStatus("C");
			status = "C";
			preoffer.setConduct("true");

		} else {
			preoffer.setBgStatus("P");
			status = "P";
			preoffer.setConduct("false");
		}
		
		request.setAttribute("stat", status);
		System.out.println("callPage callPage.........!!"+status);
		model.setBackgroundList(preoffer, status,request);
		model.terminate();
		//reset();
		
		return "success";
	}
	
	
	
	
	
	
	public void reset() {
		PreOfferProcessingModel bgmodel = new PreOfferProcessingModel();
		bgmodel.initiate(context, session);
		
		
		
		preoffer.setBgcheckCode("");
		preoffer.setCandidateName("");
		preoffer.setCandidateCode(""); 
		preoffer.setOfferStatus("");
		preoffer.setReqCode(""); 
		preoffer.setReqName(""); 	
		preoffer.setDivision(""); 
		preoffer.setBranch(""); 
		preoffer.setDepartment(""); 
		preoffer.setPosition("");
		preoffer.setDupbgCheckType(""); 
		preoffer.setOutsourceAgencyName(""); 
		preoffer.setOutsourceAgencyCode("");
		preoffer.setCheckListType(""); 
		preoffer.setDupcheckListType(""); 
		preoffer.setCheckListCode("");
		preoffer.setOverallComments(""); 
		//preoffer.setChecklistTable("");
		preoffer.setCheckListresponce("");
		preoffer.setDupcheckListresponce("");
		preoffer.setCheckListComments("");
		preoffer.setCheckListitemcode("");
		preoffer.setCheckListName("");
		preoffer.setLcandidate("");
		preoffer.setLcancode("");
		preoffer.setLoffercode("");
		preoffer.setLreqcode("");
		preoffer.setLreqName("");
		preoffer.setLposition("");
		preoffer.setLofferstatus("");
		preoffer.setLchecklistType("");
		preoffer.setBgStatus("");
		preoffer.setConduct("");
		preoffer.setChkLength("");
		preoffer.setBgpendinglistLength(""); 
		preoffer.setBgconductlistLength ("");
		preoffer.setChkreqcode(""); 
		preoffer.setChkoffercode(""); 
		preoffer.setChkcandidatecode("");
		
		bgmodel.terminate();
		
		
		
		
	}
	
	/*
	 *  @ showing candidate CV.
	 */
	public void viewCV()throws Exception{
		OutputStream oStream = null;response.getOutputStream();
		FileInputStream fsStream =null;
		String fileName="";
		try {
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null )) {
				poolName = "/" + poolName;
			}
			fileName= request.getParameter("fileName");
			if(fileName==null){
				if(fileName.length()<=0){
					fileName = "blank.doc";
				}
			}
			//for getting server path where configuration files are saved.
			String path = getText("data_path") + "/datafiles/" + poolName+ "/resume/"+fileName;
			oStream = response.getOutputStream();
			
			response.setHeader("Content-type", "application/msword"); 
			response.setHeader("Content-disposition", "inline;filename=\"" + fileName + "\"");
		
			int iChar;
			fsStream = new FileInputStream(path);
			
			while ((iChar = fsStream.read()) != -1)
			{
				oStream.write(iChar);
			}
			
		}
		catch (FileNotFoundException e) {
			
		
			addActionMessage("Resume not found");
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		finally{
			
			if(fsStream!=null){
			fsStream.close();  
			}
			if(oStream!=null){
			oStream.flush();
			oStream.close();
			}
		}
		
		
	}

	
	
}
