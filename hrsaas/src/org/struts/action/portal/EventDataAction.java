 package org.struts.action.portal;

import java.io.PrintWriter;
import org.paradyne.bean.portal.EventDataBean;
import org.paradyne.model.portal.EventDataModel;
import org.struts.lib.ParaActionSupport;

public class EventDataAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	EventDataBean bean =null ;
	public void prepare_local() throws Exception {
		bean =new EventDataBean();
	}

	public Object getModel() {
		return bean;
	}

	public String getGlofestData(String check) {		
		EventDataModel model = null;
		try {
			model = new EventDataModel();
			model.initiate(context, session);
			String categoryCode = request.getParameter("categoryCode");
			String eventCode = request.getParameter("eventCode");
			String year = request.getParameter("yearValue");			
			if(check==null){				
				check="0";
			}
			request.setAttribute("selectedTab", check);		
			model.getGlofestData(request, categoryCode,eventCode,year,bean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	
	public String getRewardData() {		
		EventDataModel model = null;
		try {
			model = new EventDataModel();
			model.initiate(context, session);
			String categoryCode = request.getParameter("categoryCode");
			String eventCode = request.getParameter("eventCode");
			String year = request.getParameter("yearValue");			
			String awardType =request.getParameter("awardType");			
			String check =request.getParameter("check");			
			String checkAward =request.getParameter("checkAward");
			if(check==null){				
				check="0";
			}			
			if(checkAward==null){				
				checkAward="0";
			}
			request.setAttribute("selectedTab", check);
			request.setAttribute("selectedStrAward", checkAward);			
			model.getRewardData(request,bean, categoryCode,eventCode,year,awardType,check);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "rewardPage";
	}
	
	
	public String getHelpDeskSection(){
		return "helpdeskpage";
	}
	
	public String getHelpDeskApproval(){
		return "helpdeskApprovalpage";
	}
	
	public String getHelpDeskConsole(){	 
		return "helpdeskConsolepage";
	}
	
	
	
	public String homePageGlofest() {		
		try {
			String check =request.getParameter("check");
			System.out.println("check val in glo  "+check);
			getGlofestData(check);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "rightglofest";
	}
	
	
	public String homePageCorporateEvent() {		
		try {
			String check =request.getParameter("check");
			System.out.println("check val in glo  "+check);
			getGlofestData(check);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "homePageCorporateEvent";
	}
	
	
	public String callJspPage() {		
		try {
			/*String check =request.getParameter("check");
			System.out.println("check val in glo  "+check);
			getGlofestData(check);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "aboutglogest";
	}
	
	public String saveFeedbackDetails() {
		EventDataModel model = null;
		try {
			model = new EventDataModel();
			model.initiate(context, session);			
			String categoryCode = request.getParameter("categoryCode");
			String title = request.getParameter("title");
			String desc = request.getParameter("desc");			
			boolean result = model.saveFeedbackDetails(categoryCode,title,desc,bean.getUserEmpId());
			System.out.println("result        "+result);
			String message="";
			if(result){
				message="Thank you for your feedback.";
			}
			else{
				message="feedback not submited.";
			}			
			// set message in a response
			response.setContentType("text/html");
			PrintWriter printWriter = response.getWriter();
			printWriter.print(message);
			printWriter.flush();			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public EventDataBean getBean() {
		return bean;
	}

	public void setBean(EventDataBean bean) {
		this.bean = bean;
	}
}
