package org.paradyne.model.PAS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.ApprFormGeneralInfo;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;

public class ApprFormGeneralInfoModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ApprFormGeneralInfoModel.class);
	
	public void getPhases(ApprFormGeneralInfo bean){
		try{
			Object [][] appraisalCode = null;
			Object [][] phaseData = null;
			
			if(bean.getDetailFLag().equals("false")){
				
				appraisalCode = new Object[1][2];
				appraisalCode [0][0] = bean.getEmpId();
				appraisalCode [0][1] = bean.getApprId();
				
				String empId = bean.getEmpId();
				String apprId = bean.getApprId();
				
				phaseData = getSqlModel().getSingleResult(getQuery(1), new Object[]{empId,apprId});
				
			}else{
							
				appraisalCode = new Object[1][2];
				appraisalCode [0][0] = bean.getApprId();
				appraisalCode [0][1] = bean.getPhaseCode();
				
				String appprId = bean.getApprId();
				String phaseCode = bean.getPhaseCode();
				//logger.info("bean.getApprId()--------"+bean.getApprId());
				//logger.info("bean.getPhaseCode()--------"+bean.getPhaseCode());
				
				phaseData = getSqlModel().getSingleResult(getQuery(6), new Object[]{appprId,phaseCode});
				
			}
				
			//logger.info("phaseData.length"+phaseData.length);
			if (phaseData != null && phaseData.length != 0) {
				bean.setPhaseCode(checkNull(String.valueOf(phaseData[0][0])));
				bean.setPhaseName(checkNull(String.valueOf(phaseData[0][1])));
				bean.setPhaseStartDate(checkNull(String.valueOf(phaseData[0][2])));
				bean.setPhaseEndDate(checkNull(String.valueOf(phaseData[0][3])));
				bean.setPhaseLockFlag(checkNull(String.valueOf(phaseData[0][4])));
				bean.setPhaseOrder(checkNull(String.valueOf(phaseData[0][5])));
				
				if(bean.getDetailFLag().equals("false")){
					
					if(String.valueOf(phaseData[0][5]).equals("Y"))
						bean.setPhaseForwardFlag("true");
					else
						bean.setPhaseForwardFlag("false");
					
					if(String.valueOf(phaseData[0][6]).equals("Y"))
						bean.setQuesWtDisplayFlag("true");
					else
						bean.setQuesWtDisplayFlag("false");
							
				}else{
					
					if(String.valueOf(phaseData[0][5]).equals("Y"))
						bean.setQuesWtDisplayFlag("true");
					else
						bean.setQuesWtDisplayFlag("false");
				}
				
				//logger.info("bean.getPhaseForwardFlag----------------"+bean.getPhaseForwardFlag());
				
				}
			//logger.info("phaseData.length"+bean.getPhaseName());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void getEmployeeDetails(ApprFormGeneralInfo bean){
		try{
			
			
			Object [] empCode = new Object[2];
					
			//logger.info("getEmployeeDetails"+bean.getDetailFLag());
			
			//if(bean.getDetailFLag().equals("false")){
				
				empCode [0] = bean.getApprId();
				empCode [1] = bean.getEmpId();
		//	}
		//	else{
				
		//		empCode [0] = bean.getApprId();
		//		empCode [1] = bean.getEmpId();
				
		//	}
			
			//logger.info("appraisal id---------"+String.valueOf(empCode [0]));
			//logger.info("employee id---------"+String.valueOf(empCode [1]));
			
			Object [][] empData = getSqlModel().getSingleResult(getQuery(2), empCode);
		
			//logger.info("empData.length"+empData.length);
			if (empData != null && empData.length != 0) {
			bean.setEmpId(checkNull(String.valueOf(empData[0][0])));
			bean.setEmpCode(checkNull(String.valueOf(empData[0][1])));
			bean.setEmpName(checkNull(String.valueOf(empData[0][2])));
			bean.setEmpBrnName(checkNull(String.valueOf(empData[0][3])));
			bean.setEmpDeptName(checkNull(String.valueOf(empData[0][4])));
			bean.setEmpDesgName(checkNull(String.valueOf(empData[0][5])));
			bean.setEmpReportingTo(checkNull(String.valueOf(empData[0][6])));
			bean.setEmpDoj(checkNull(String.valueOf(empData[0][7])));
			bean.setApprDate(checkNull(String.valueOf(empData[0][8])));
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void getAppraiserDetails(ApprFormGeneralInfo bean){
		try{
			Object [] empCode = new Object[2];
			
		//	if(bean.getDetailFLag().equals("false"))
				empCode [0] = bean.getEmpId();
		//	else
			//	empCode [0] = bean.getEmpId();
		//	
			
				empCode [1] = bean.getApprId();
			
			
						
			Object [][] appraiserGroup =  getSqlModel().getSingleResult(getQuery(3),empCode);
			
			Object [] empGroupCode = new Object[2];
			Object [][] appraiserData = null;
			empGroupCode [0] = bean.getApprId();
			if (appraiserGroup != null && appraiserGroup.length != 0) {
					empGroupCode [1] = appraiserGroup[0][0];
					 appraiserData = getSqlModel().getSingleResult(getQuery(4), empGroupCode);
			}
			
			ArrayList<Object> list=new ArrayList<Object>();
			if (appraiserData != null && appraiserData.length != 0) {
				
				for (int i = 0; i < appraiserData.length; i++) {
					ApprFormGeneralInfo bean1 = new ApprFormGeneralInfo();
					if(i==0){
						bean1.setAppraiserName(bean.getEmpName());
						bean1.setAppraiserDesgName(bean.getEmpDesgName());
					}else{
						bean1.setAppraiserName(checkNull(String.valueOf(appraiserData[i][1])));
						bean1.setAppraiserDesgName(checkNull(String.valueOf(appraiserData[i][2])));
					}
										
					bean1.setAppraiserPhaseName(checkNull(String.valueOf(appraiserData[i][3])));
					bean1.setAppraiserLevel(checkNull(String.valueOf(appraiserData[i][4])));
					list.add(bean1);

				}
			} // end of if
			
			bean.setAppraiserList(list)	;
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	public void getNextPhaseVisibility(ApprFormGeneralInfo bean){
		
		Object [] apprCode = new Object[2];
		
		//if(bean.getDetailFLag().equals("false"))
			apprCode [0] = bean.getEmpId();
		//else
		//	apprCode [0] = bean.getEmpId();
		
		
		apprCode [1] = bean.getApprId();
		
		
		Object [][] visibilityData = getSqlModel().getSingleResult(getQuery(5), apprCode);
					
		if (visibilityData != null && visibilityData.length != 0) {
			
			bean.setTemplateCode(checkNull(String.valueOf(visibilityData[0][0])));
			bean.setInstrVisibilty(checkNull(String.valueOf(visibilityData[0][1])));
		
		}
	}
	
	public void getAppraisalData(ApprFormGeneralInfo bean){
		
		try{
				/*String apprCode= request.getParameter("happrId");
				String empCode= request.getParameter("hempId");
				String phaseCode= request.getParameter("hphaseCode");
				
				logger.info("apprcode---------"+phaseCode);
				bean.setApprId(apprCode);
				bean.setEmpId(empCode);
				bean.setPhaseCode(phaseCode);*/
				
				Object [][]apprData = getSqlModel().getSingleResult(getQuery(7), new Object[]{bean.getApprId()});
			
				if (apprData != null && apprData.length != 0) {
					
					bean.setApprCode(checkNull(String.valueOf(apprData[0][0])));
					bean.setApprStartDate(checkNull(String.valueOf(apprData[0][1])));
					bean.setApprEndDate(checkNull(String.valueOf(apprData[0][2])));
					bean.setApprValidTillDate(checkNull(String.valueOf(apprData[0][3])));
				}
			
				//logger.info("apprcode---------"+String.valueOf(bean.getApprId()));
				//logger.info("empCode---------"+String.valueOf(bean.getEmpId()));
				//logger.info("phaseCode---------"+String.valueOf(bean.getPhaseCode()));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean sendBackAppraisal(String apprCode,String templateCode,String phaseCode,String empCode,String appraiserCode ){
		try{
			boolean result = false;
			Object[][] to_mailIds =new Object[1][1];	
			Object[][] from_mailIds =new Object[1][1];	
			
			Object [] []paramObj = new Object[0][3];
			
			paramObj[0][0] = apprCode;
			paramObj[0][1] = templateCode;
			paramObj[0][3] = empCode;
			
					 getSqlModel().singleExecute(getQuery(11), paramObj);
			result = getSqlModel().singleExecute(getQuery(12), paramObj);
			
			if(result){
				
				try {
					to_mailIds[0][0]=String.valueOf(empCode);
					from_mailIds[0][0]=appraiserCode;

					MailUtility mail=new MailUtility();
					mail.initiate(context, session);
					//logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
					//logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
					mail.sendMail(to_mailIds, from_mailIds,"Appraisal", "", "SB");

					mail.terminate();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return result;
		
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	public void getRating(ApprFormGeneralInfo bean){
		
		Object [][]apprData = getSqlModel().getSingleResult(getQuery(9), new Object[]{bean.getApprId()});
		
		if (apprData != null && apprData.length > 0) {
			bean.setRatingDefined("true");
		}else
			bean.setRatingDefined("false");
		
	}
	
	public void getApprStart(ApprFormGeneralInfo bean){
		
		Object [][]apprData = getSqlModel().getSingleResult(getQuery(10), new Object[]{bean.getUserEmpId(),bean.getApprId()});
		
		//logger.info("in getApprStart model------------");
		if (apprData != null && apprData.length > 0) {
			
			bean.setApprCode(checkNull(String.valueOf(apprData[0][0])));
			bean.setApprStartDate(checkNull(String.valueOf(apprData[0][1])));
			bean.setApprEndDate(checkNull(String.valueOf(apprData[0][2])));
			bean.setApprId(checkNull(String.valueOf(apprData[0][3])));
			bean.setApprValidTillDate(checkNull(String.valueOf(apprData[0][4])));
		}
		
		
	}
	/* 
	 * method name : checkNull
	 * purpose     : check the string is null or not
	 * return type : String
	 * parameter   : String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}  // end of if
		else {
			return result;
		} // end of else
	}

}
