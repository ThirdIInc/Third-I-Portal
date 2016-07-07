package org.struts.action.D1;

import org.paradyne.bean.D1.WorkersCompInjury;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.D1.WorkersCompInjuryApprModel;
import org.paradyne.model.D1.WorkersCompInjuryModel;
import org.paradyne.model.leave.RegularizationModel;
import org.struts.lib.ParaActionSupport;

public class WorkersCompInjuryApprAction extends ParaActionSupport {


	WorkersCompInjury bean;
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		bean=new WorkersCompInjury();
		bean.setMenuCode(1180);
	}

	public WorkersCompInjury getBean() {
		return bean;
	}

	public void setBean(WorkersCompInjury bean) {
		this.bean = bean;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}

	public String input() throws Exception{
		getNavigationPanel(1);
		WorkersCompInjuryApprModel model =new WorkersCompInjuryApprModel();
		model.initiate(context, session);
		boolean flag=model.input(bean,request);		
		model.terminate();
		return INPUT;
	}
	public String addApplication() throws Exception{
		getNavigationPanel(1);		
		return SUCCESS;
	}
	public String editApplication() throws Exception{
		getNavigationPanel(1);
		WorkersCompInjuryModel model =new WorkersCompInjuryModel();
		model.initiate(context, session);
		System.out.println("########################");
		boolean flag=model.editApplication(bean);		
		model.setApproverComments(bean);
		model.terminate();		
		if(bean.getFlag().equals("RR")||bean.getFlag().equals("AA")){
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
			bean.setEnableAll("N");
		}
		return SUCCESS;
	}
	/**
	 * METHOD TO SAVE
	 */
	
	
	public String sendBack() throws Exception{
		getNavigationPanel(1);
		WorkersCompInjuryApprModel model =new WorkersCompInjuryApprModel();
		model.initiate(context, session);		
		String code=bean.getWorkersCode();
		String comments=bean.getComments();
		String approverCode=bean.getUserEmpId();
		String flag=model.check(request,bean.getEmpId(),code,"B",comments,approverCode);
		addActionMessage("Application send back successfully");
		//boolean flag=model.check("A",code,approverCode,comments);
		if(flag.equals("")){
			
			/*try {
				sendMailMethod(
						"Workers comp Injury/Illness Mail to employee regarding  approval",
						bean.getEmpId(), bean.getUserEmpId(), bean
								.getWorkersCode(), null, null);
			} catch (Exception e) {
				// TODO: handle exception
			}*/
		}
		
		model.terminate();
		return input();
	}
	public String approve() throws Exception{
		getNavigationPanel(1);
		WorkersCompInjuryApprModel model =new WorkersCompInjuryApprModel();
		model.initiate(context, session);	
		String code=bean.getWorkersCode();
		String comments=bean.getComments();
		String approverCode=bean.getUserEmpId();
		
		String flag=model.check(request,bean.getEmpId(),code,"A",comments,approverCode);
		//boolean flag=model.check("A",code,approverCode,comments);
		addActionMessage("Application approved successfully");
		if(!flag.equals("")){
			
			/*try {
				sendMailMethod(
						"Workers comp Injury/Illness Mail to employee regarding  approval",
						bean.getEmpId(), bean.getUserEmpId(), bean
								.getWorkersCode(), null, null);
			} catch (Exception e) {
				// TODO: handle exception
			}*/
		}
		
		model.terminate();
		return input();
	}
	
	public String reject() throws Exception{
		getNavigationPanel(1);
		WorkersCompInjuryApprModel model =new WorkersCompInjuryApprModel();
		model.initiate(context, session);		
		String code=bean.getWorkersCode();
		String comments=bean.getComments();
		String approverCode=bean.getUserEmpId();
		String flag=model.check(request,bean.getEmpId(),code,"R",comments,approverCode);
		addActionMessage("Application rejected successfully");		
		//boolean flag=model.check("A",code,approverCode,comments);
		if(!flag.equals("")){
				
		}
		
		model.terminate();
		return input();
	}
	
}
