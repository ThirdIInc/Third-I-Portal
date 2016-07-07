package org.paradyne.model.D1;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.WorkersCompInjury;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.leave.RegularizationModel;

public class WorkersCompInjuryApprModel extends ModelBase {

	
	
	
	/*public String check1(HttpServletRequest request,String empCode,String workerCode,
			String status, String Comments,String approverCode) {
		
		
		return "";
	}*/
	
	public String check(HttpServletRequest request,String empCode,String workerCode,
			String status, String Comments,String approverCode) {
		boolean flag=false;
		String initiatorName="";
		String finalStatus=status;
		Object[][]data=new Object[1][3];		
		String query="SELECT WORKERS_RETURN_TO_WORK,WORKERS_REPORTING_TO,WORKERS_STATUS,COMPLETED_BY FROM HRMS_D1_WRKRS_COMP_INJURY WHERE WORKERS_ID="+workerCode+" ";// AND WORKERS_REPORTING_TO=2";
		Object[][]aaa=getSqlModel().getSingleResult(query);		
		if(aaa !=null && aaa.length>0){
			 initiatorName=String.valueOf(aaa[0][3]);
			if(String.valueOf(aaa[0][2]).equals("A")||String.valueOf(aaa[0][2]).equals("Z")||String.valueOf(aaa[0][2]).equals("R")||String.valueOf(aaa[0][2]).equals("B")){
				return "Application has already been processed.";
			}
		}
		
		
		if(status.equals("A")){
			status="Z";		
		if(aaa !=null && aaa.length>0){
			if(String.valueOf(aaa[0][0]).equals("Y")){
				status="A";
			}
			if(String.valueOf(aaa[0][1]).equals("2")){
				status="A";
			}			
		}
		}
		data[0][0]=status;
		data[0][1]="";
		data[0][2]=workerCode;
		
		String qq="UPDATE HRMS_D1_WRKRS_COMP_INJURY SET WORKERS_STATUS=?,COMMENTS=? WHERE WORKERS_ID=?";
		flag= getSqlModel().singleExecute(qq,data);
		if(flag){
			//INSERT INTO PATH
			String qqq="INSERT INTO HRMS_D1_WORKER_INJURY_PATH(WORKERS_ID,WORKERS_APPROVER,WORKERS_COMMENTS,WORKERS_DATE,WORKERS_PATH_ID,WORKERS_STATUS) "
						+"	VALUES(?,?,?,SYSDATE,(SELECT NVL(MAX(WORKERS_PATH_ID),0)+1 FROM HRMS_D1_WORKER_INJURY_PATH),?)";
			Object[][]obj=new Object[1][4];
			obj[0][0]=workerCode;
			obj[0][1]=approverCode;
			obj[0][2]=Comments;
			obj[0][3]=status;
			flag= getSqlModel().singleExecute(qqq,obj);
		}
		if(flag){
			String HRGroupQuery="SELECT APP_EMAIL_ID FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('H')  AND APP_EMAIL_ID IS NOT NULL  ";
			Object[][]HRGroupEmail=getSqlModel().getSingleResult(HRGroupQuery);
			
			
			String aa="Application approved successfully";
			if(finalStatus.equals("A")){
				 aa="Application approved successfully";
			}
			if(finalStatus.equals("R")){
				 aa= "Application rejected successfully";
			}
			if(finalStatus.equals("B")){
				 aa= "Application sendback successfully";
			}	
			try {
				sendMailMethod(
						"Workers comp Injury/Illness Mail to employee regarding  approval",
						empCode, approverCode, workerCode, null, null,request,initiatorName,HRGroupEmail);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return aa;
		}
		else{
			return "";
		}
		
	}

	public boolean input(WorkersCompInjury bean, HttpServletRequest request) {
		
		/**
		 * DRAFT APPLICATION
		 */
		
		String query="SELECT NVL(TRACKING_NO,' '), EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(WORKERS_APPLICATION_DATE,'DD-MM-YYYY') "
					+"	,HRMS_D1_WRKRS_COMP_INJURY.WORKERS_EMP_ID,WORKERS_ID FROM HRMS_D1_WRKRS_COMP_INJURY "
					+"	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_D1_WRKRS_COMP_INJURY.WORKERS_EMP_ID) ";
		
		String draft=query+" WHERE WORKERS_STATUS='P' ORDER BY WORKERS_ID DESC ";
		
		boolean checkAuth=checkReporting(bean);
		
		/**
		 * IN PENDING
		 */
		Object[][]data=getSqlModel().getSingleResult(draft);
		
		String[]pageIndex=setData(bean,request,data);
		
		
		if(data !=null && data.length>0 &&checkAuth){
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
			.parseInt(String.valueOf(pageIndex[1])); i++) {
				WorkersCompInjury bean1=new WorkersCompInjury();
				bean1.setIttEmployeeToken(String.valueOf(data[i][0]));
				bean1.setIttEmployee(String.valueOf(data[i][1]));
				bean1.setIttApplicationDate(String.valueOf(data[i][2]));
				bean1.setIttEmployeeId(String.valueOf(data[i][3]));
				bean1.setIttWorkersCode(String.valueOf(data[i][4]));
				list.add(bean1);
			}
			bean.setListDraft(list);
		}
		
	
		/**
		 * APPROVED
		 */
		String approve=query+" WHERE WORKERS_STATUS='A' ORDER BY WORKERS_ID DESC ";
		
		if(bean.getFlag().equals("AA") &&checkAuth){
			Object[][]objApprove=getSqlModel().getSingleResult(approve);
			String[]pageIndexAppr=setData(bean,request,objApprove);
			if(objApprove !=null && objApprove.length>0){
				ArrayList list =new ArrayList();
				for (int i = Integer.parseInt(String.valueOf(pageIndexAppr[0])); i < Integer
				.parseInt(String.valueOf(pageIndexAppr[1])); i++) {
					
					WorkersCompInjury bean1=new WorkersCompInjury();
					bean1.setIttEmployeeToken(String.valueOf(objApprove[i][0]));
					bean1.setIttEmployee(String.valueOf(objApprove[i][1]));
					bean1.setIttApplicationDate(String.valueOf(objApprove[i][2]));
					bean1.setIttEmployeeId(String.valueOf(objApprove[i][3]));
					bean1.setIttWorkersCode(String.valueOf(objApprove[i][4]));
					list.add(bean1);
				}
				bean.setListApprove(list);
			}
		}
		
		
		
		//listReject
		String reject=query+" WHERE WORKERS_STATUS='R' ORDER BY WORKERS_ID DESC";
		
		if(bean.getFlag().equals("RR") &&checkAuth){
			Object[][]objReject=getSqlModel().getSingleResult(reject);
			String[]pageIndexR=setData(bean,request,objReject);
		if(objReject !=null && objReject.length>0){
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexR[0])); i < Integer
			.parseInt(String.valueOf(pageIndexR[1])); i++) {
				WorkersCompInjury bean1=new WorkersCompInjury();
				bean1.setIttEmployeeToken(String.valueOf(objReject[i][0]));
				bean1.setIttEmployee(String.valueOf(objReject[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objReject[i][2]));
				bean1.setIttEmployeeId(String.valueOf(objReject[i][3]));
				bean1.setIttWorkersCode(String.valueOf(objReject[i][4]));
				list.add(bean1);
			}
			bean.setListReject(list);
		}
	}	
		return false;
	}

	public String[] setData(WorkersCompInjury bean, HttpServletRequest request,Object[][]data){
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(bean
				.getMyPage(), data.length, 10);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "10";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		try {
			System.out.println("pageIndex  of 2============    "
					+ Integer.parseInt(String.valueOf(pageIndex[2])));
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");
		return pageIndex;
	}
	public boolean checkReporting(WorkersCompInjury bean) {
		boolean rr=false;
	String query="SELECT APP_SETTINGS_EMP_ID,APP_SETTINGS_TYPE FROM HRMS_D1_APPROVAL_SETTINGS WHERE  APP_SETTINGS_TYPE IN('H')  AND APP_SETTINGS_EMP_ID= "+bean.getUserEmpId();
		Object[][]data=getSqlModel().getSingleResult(query);
		if(data !=null && data.length>0){
			rr=true;
		}
		else{
			rr=false;
		}

	return rr;
}
	
	public String sendMailMethod(String tempName,String empCode,String approverCode,String applicationCode
			,String[] link_param,String[] link_label, 
			HttpServletRequest request,String initiatorName,Object[][]HRGroupEmail) throws Exception{
			Object[][]eventData = null;
			Object[][]templateData=null;
			RegularizationModel model = new RegularizationModel();
			model.initiate(context, session);
		
		String templateQuery = "SELECT TEMPLATE_NAME "
				+" FROM HRMS_EMAILTEMPLATE_HDR  "
				+" WHERE HRMS_EMAILTEMPLATE_HDR.TEMPLATE_NAME = '"+tempName+"'";
				templateData = model.getSqlModel().getSingleResult(templateQuery);
			//if(templateData !=null && templateData.length>0){
				String templateName=tempName.trim();
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template.setEmailTemplate(templateName);
				
				template.getTemplateQueries();
				EmailTemplateQuery templateQuery1 = template
						.getTemplateQuery(1); // FROM EMAIL
				if(!empCode.equals("")){
				templateQuery1.setParameter(1, approverCode);
				}
				

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2); // TO EMAIL				
					templateQuery2.setParameter(1, empCode);
				
				// Subject + Body
				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				// templateQuery3.setParameter(1, applnDate);
				templateQuery3.setParameter(1, applicationCode);

				EmailTemplateQuery templateQuery4 = template
						.getTemplateQuery(4);
				templateQuery4.setParameter(1, applicationCode);

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(5);
				templateQuery5.setParameter(1, approverCode);	
				
				EmailTemplateQuery templateQuery6 = template
				.getTemplateQuery(6);
		templateQuery6.setParameter(1, applicationCode);
		
		EmailTemplateQuery templateQuery7 = template
		.getTemplateQuery(7);
			templateQuery7.setParameter(1, applicationCode);
			
			
			EmailTemplateQuery templateQuery8 = template
			.getTemplateQuery(8);
				templateQuery8.setParameter(1, initiatorName);
		
				template.configMailAlert();
				
				if(!approverCode.equals("")&& !initiatorName.equals(empCode) ){								
					template.sendApplicationMailToKeepInfo(initiatorName);
				}
				if(HRGroupEmail!=null && HRGroupEmail.length>0){
					template.sendApplicationMailToGroups(HRGroupEmail);
				}
				if(link_param !=null && link_param.length>0){
				template.addOnlineLink(request, link_param, link_label);
				}
				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();	
			//}
			
		return "";
	}
}
