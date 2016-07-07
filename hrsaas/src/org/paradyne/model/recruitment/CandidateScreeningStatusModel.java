package org.paradyne.model.recruitment;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.bean.Recruitment.CandidateScreeningStatus;
import org.struts.lib.ParaActionSupport;

import javax.servlet.http.*;

import java.util.*;
/**
 * 
 * @author Pradeep Kumar Sahoo
 * Date:13-01-2009
 *
 */

public class CandidateScreeningStatusModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CandidateScreeningStatusModel.class);
  
	
	/*
	 * following function is called to generate the list of records to be displayed when any button is clicked. 
	 */	
	public void getCandidateRecords(CandidateScreeningStatus bean,String status,HttpServletRequest request){
		
		if(status.equals("O") || status.equals("R")){
			bean.setFlag("true");
		}else if(status.equals("T")){
			bean.setTestFlag("true");
		}else if(status.equals("I")){
			bean.setIntFlag("true");
		}else if(status.equals("B")){
			bean.setTestIntFlag("true");
		}
		String query="SELECT RESUME_REQS_CODE,NVL(REQS_NAME,' '),NVL(RANK_NAME,' '),CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME"
					+" ,NVL(HRMS_REC_CAND_DATABANK.CAND_EXP_YEAR,0),CAND_CURR_CTC,CASE WHEN CAND_GENDER='M' THEN 'Male' WHEN CAND_GENDER='F' THEN 'Female' WHEN CAND_GENDER='O' THEN 'Other' ELSE ' ' END,"	
					+" EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,NVL(CAND_RESUME,' '),"
					+" CASE WHEN RESUME_STATUS='O' THEN 'Open' WHEN RESUME_STATUS='R' THEN 'Rejected' WHEN RESUME_STATUS='I' THEN 'Short List For Interview' "
					+" WHEN RESUME_STATUS='T' THEN 'Short List For Test' WHEN RESUME_STATUS='B' THEN 'Short List For Test/Interview' ELSE ' ' END,NVL(RESUME_COMMENTS,' '),NVL(HRMS_REC_CAND_DATABANK.CAND_EXP_MONTH,0),REQS_CODE,RESUME_CAND_CODE, "
					+" REQS_HIRING_MANAGER,RESUME_REC_EMPID,RESUME_CODE FROM HRMS_REC_RESUME_BANK"
					+" INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_RESUME_BANK.RESUME_REQS_CODE)"	
					+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
					+" INNER JOIN HRMS_REC_CAND_DATABANK ON(HRMS_REC_CAND_DATABANK.CAND_CODE=HRMS_REC_RESUME_BANK.RESUME_CAND_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)"
					+" WHERE RESUME_STATUS="+"'"+status+"'  AND REQS_STATUS !='C' AND RESUME_SHEDULE_STATUS='N'  AND RESUME_REC_EMPID = "+bean.getUserEmpId();
		
		Object[][] data = getSqlModel().getSingleResult(query);
		if(data!=null && data.length>0)
		{
			bean.setModeLength("true");	
		}else
		{
			bean.setModeLength("false");	
		}
		ArrayList<Object> reqList=new ArrayList<Object>();
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),data.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			bean.setMyPage("1");
		
		if(data!=null && data.length>0){
			
			for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
				CandidateScreeningStatus candScrSts=new CandidateScreeningStatus();
				candScrSts.setRequisitionCode(String.valueOf(data[i][0]));//Requisition Code
				candScrSts.setReqCode(String.valueOf(data[i][1]));//Requisition Code
				candScrSts.setPosition(String.valueOf(data[i][2]));//Position
				candScrSts.setCandidateName(String.valueOf(data[i][3]));//Candidate Name
				String exp="";
				int year=Integer.parseInt(String.valueOf(data[i][4]));
				int month=Integer.parseInt(String.valueOf(data[i][11]));
				if(year >1 && month >1){
					exp+=year+" Years "+ month+" Months";
				}else if(year >1 && !(month >1)){
					exp+=year+" Years "+ month +" Month";
					
				}else if(!(year >1) && month >1){
					exp+=year+" Year "+ month +" Months";
				}else{
					exp+=year+" Year "+ month +" Month";
				}
				
				candScrSts.setExp(String.valueOf(exp));//Exp
				if(String.valueOf(data[i][5]).equals("") || String.valueOf(data[i][5]).equals("null")){
					candScrSts.setCtc(String.valueOf(""));
				}else{
				    candScrSts.setCtc(String.valueOf(data[i][5]));//Ctc
				}
				candScrSts.setGender(String.valueOf(data[i][6]));//Gender
				candScrSts.setRecruiter(String.valueOf(data[i][7]));//Recruiter Name
				candScrSts.setResume(String.valueOf(data[i][8]));//Resume Name
				candScrSts.setResumeStatus(String.valueOf(data[i][9]));//Status of resume
				candScrSts.setComment(String.valueOf(data[i][10]));//Comment
				//candScrSts.setRequisitionCode(String.valueOf(data[i][11]));
				candScrSts.setCandidateCode(String.valueOf(data[i][13]));
				candScrSts.setHiringMgrCode(String.valueOf(data[i][14]));
				candScrSts.setRecruiterCode(String.valueOf(data[i][15]));
				candScrSts.setResCode(String.valueOf(data[i][16]));
				if(status.equals("O") || status.equals("R")){
					candScrSts.setOpenRejflag("true");
				}else{
					candScrSts.setOpenRejflag("false");
				}
				reqList.add(candScrSts);
			}//End of for loop
			
			bean.setList(reqList);
			bean.setTotalRecords(String.valueOf(data.length));
		}else{
			bean.setData("true");
			bean.setTotalRecords(String.valueOf(data.length));
		}
		
		if(!status.equals(bean.getHiddenStatusPage()))
		{
			bean.setHiddenStatusPage(status);
			bean.setMyPage("1");
			bean.setHiddenPageStatusFlag(true); 
		}
		
	}

	public void getRecord(CandidateScreeningStatus bean, String status,HttpServletRequest request,String reqsLabel,String postionLabel, String candidatNameLabel, String candExpLabel,String hiringLabel,String genderLabel)		
	 {
		
		String conCat ="";
		
		if(status.equals("O") || status.equals("R")){
			bean.setFlag("true");
		}else if(status.equals("T")){
			bean.setTestFlag("true");
		}else if(status.equals("I")){
			bean.setIntFlag("true");
		}else if(status.equals("B")){
			bean.setTestIntFlag("true");
		}
		String query="SELECT RESUME_REQS_CODE,NVL(REQS_NAME,' '),NVL(RANK_NAME,' '),CAND_FIRST_NAME||' '||CAND_MID_NAME||' '||CAND_LAST_NAME"
					+" ,NVL(HRMS_REC_CAND_DATABANK.CAND_EXP_YEAR,0),CAND_CURR_CTC,CASE WHEN CAND_GENDER='M' THEN 'Male' WHEN CAND_GENDER='F' THEN 'Female' WHEN CAND_GENDER='O' THEN 'Other' ELSE ' ' END,"	
					+" EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,NVL(CAND_RESUME,' '),"
					+" CASE WHEN RESUME_STATUS='O' THEN 'Open' WHEN RESUME_STATUS='R' THEN 'Rejected' WHEN RESUME_STATUS='I' THEN 'Short List For Interview' "
					+" WHEN RESUME_STATUS='T' THEN 'Short List For Test' WHEN RESUME_STATUS='B' THEN 'Short List For Test/Interview' ELSE ' ' END,NVL(RESUME_COMMENTS,' '),NVL(HRMS_REC_CAND_DATABANK.CAND_EXP_MONTH,0),REQS_CODE,RESUME_CAND_CODE, "
					+" REQS_HIRING_MANAGER,RESUME_REC_EMPID,RESUME_CODE FROM HRMS_REC_RESUME_BANK"
					+" INNER JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE=HRMS_REC_RESUME_BANK.RESUME_REQS_CODE)"	
					+" INNER JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_REC_REQS_HDR.REQS_POSITION)"
					+" INNER JOIN HRMS_REC_CAND_DATABANK ON(HRMS_REC_CAND_DATABANK.CAND_CODE=HRMS_REC_RESUME_BANK.RESUME_CAND_CODE)"
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_REC_REQS_HDR.REQS_HIRING_MANAGER)"
					+" WHERE RESUME_STATUS="+"'"+status+"' AND RESUME_SHEDULE_STATUS='N'  AND REQS_STATUS !='C' AND RESUME_REC_EMPID = "+bean.getUserEmpId();
		
		 if(bean.getAppliedFilterFlag().equals("true"))
		 {
			if(!bean.getRequisitionId().equals("")) {
				query += " AND  RESUME_REQS_CODE ="+bean.getRequisitionId();
				conCat+= reqsLabel+" : "+bean.getRequisitionName()+",";
			}
			
			if (!bean.getPositionId().equals("")) {
				query = query + " AND REQS_POSITION="+bean.getPositionId();
				conCat+=postionLabel+" : "+bean.getPositionName()+",";
			}
			if (!bean.getCandCode1().equals("")) {
				query +=  " AND CAND_CODE="+bean.getCandCode1();
				conCat+=candidatNameLabel+" : "+bean.getCandidateName1()+",";
			}
			boolean chkFlag= false;
			if(!bean.getYear1().equals("")){  
				query += " AND CAND_EXP_YEAR="+bean.getYear1();
				chkFlag =true;
				if(!bean.getMonth().equals("")){
					conCat+=candExpLabel+" : "+bean.getYear1()+" Year "+bean.getMonth()+" Month ,";
				}else{
					conCat+=candExpLabel+" : "+bean.getYear1()+" Year ,";
				}
			}
			if(!bean.getMonth().equals("")) {			    	   
				query += " AND CAND_EXP_MONTH="+bean.getMonth();
				if(chkFlag==false)
				{
					conCat+=candExpLabel+" : "+bean.getMonth()+" Month ,";
				}
			 } 
			
			if(!bean.getHrManagerId().equals("")){
				query +=  " AND REQS_HIRING_MANAGER="+bean.getHrManagerId();
				conCat+=hiringLabel+" : "+bean.getManagerName()+",";
			}
			
			if (!bean.getCandGender().equals("")) {
				query +=  " AND CAND_GENDER='"+bean.getCandGender()+"'";
	             if(bean.getCandGender().equals("M"))
	             {
	            	 conCat+=genderLabel+" : Male ,";
	             } if(bean.getCandGender().equals("F")){
	            	 conCat+=genderLabel+" : Female ,"; 
	             }if(bean.getCandGender().equals("O")){
	            	 conCat+=genderLabel+" : Others ,"; 
	             }
			}
	  } // end of if for checking the filter is applied or not

		try
		{ 
			 String [] filterArr = conCat.split(",");
			 request.setAttribute("filterArr", filterArr);  // for setting the appilied filter on jsp
		}catch (Exception e) {
			// TODO: handle exception
		}
		Object[][] data = getSqlModel().getSingleResult(query);
		if(data!=null && data.length>0)	{
			bean.setModeLength("true");	
		}else{
			bean.setModeLength("false");	
		}
		
		if(!status.equals(bean.getHiddenStatusPage()))
		{
			bean.setHiddenStatusPage(status);
			bean.setMyPage("1"); 
			bean.setHiddenPageStatusFlag(true);  
		}
		System.out.println("bean.getMyPage()============= "+bean.getMyPage());
		ArrayList<Object> reqList=new ArrayList<Object>();
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),data.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			bean.setMyPage("1");
		
		if(data!=null && data.length>0){
			
			for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){
				CandidateScreeningStatus candScrSts=new CandidateScreeningStatus();
				candScrSts.setRequisitionCode(String.valueOf(data[i][0]));//Requisition Code
				candScrSts.setReqCode(String.valueOf(data[i][1]));//Requisition Code
				candScrSts.setPosition(String.valueOf(data[i][2]));//Position
				candScrSts.setCandidateName(String.valueOf(data[i][3]));//Candidate Name
				String exp="";
				int year=Integer.parseInt(String.valueOf(data[i][4]));
				int month=Integer.parseInt(String.valueOf(data[i][11]));
				if(year >1 && month >1){
					exp+=year+" Years "+ month+" Months";
				}else if(year >1 && !(month >1)){
					exp+=year+" Years "+ month +" Month";
					
				}else if(!(year >1) && month >1){
					exp+=year+" Year "+ month +" Months";
				}else{
					exp+=year+" Year "+ month +" Month";
				}
				
				candScrSts.setExp(String.valueOf(exp));//Exp
				if(String.valueOf(data[i][5]).equals("") || String.valueOf(data[i][5]).equals("null")){
					candScrSts.setCtc(String.valueOf(""));
				}else{
				    candScrSts.setCtc(String.valueOf(data[i][5]));//Ctc
				}
				candScrSts.setGender(String.valueOf(data[i][6]));//Gender
				candScrSts.setRecruiter(String.valueOf(data[i][7]));//Recruiter Name
				candScrSts.setResume(String.valueOf(data[i][8]));//Resume Name
				candScrSts.setResumeStatus(String.valueOf(data[i][9]));//Status of resume
				candScrSts.setComment(String.valueOf(data[i][10]));//Comment
				//candScrSts.setRequisitionCode(String.valueOf(data[i][11]));
				candScrSts.setCandidateCode(String.valueOf(data[i][13]));
				candScrSts.setHiringMgrCode(String.valueOf(data[i][14]));
				candScrSts.setRecruiterCode(String.valueOf(data[i][15]));
				candScrSts.setResCode(String.valueOf(data[i][16]));
				if(status.equals("O") || status.equals("R")){
					candScrSts.setOpenRejflag("true");
				}else{
					candScrSts.setOpenRejflag("false");
				}
				reqList.add(candScrSts);
			}//End of for loop
			
			bean.setList(reqList);
			bean.setTotalRecords(String.valueOf(data.length));
			
		}else{
			bean.setData("true");
			bean.setTotalRecords(String.valueOf(data.length));
			
		} 
	}	
	
	public String getMessage(String key,HttpServletRequest request) {
		String obj = "";
		try {
			HashMap hMap = (HashMap)context.getAttribute("common" + session.getAttribute("session_pool"));
			obj = (String)hMap.get(key);
			if(obj == null || obj.equals(null)) {
				hMap = (HashMap)request.getAttribute("formLabels");
				obj = (String)hMap.get(key);
			}
		} catch(Exception e) {
			return "Message Not Found";
		}
		if(obj == null)
			return "Message Not Found";
		return obj;
	}
	 

}
