/**
 * Modified by Lakkichand
 * Date :29-032008
 */
package org.struts.action.common;
import org.struts.lib.ParaActionSupport;

import org.paradyne.bean.common.AnalyticHome;
import org.paradyne.model.common.*;
public class AnalyticHomeAction extends ParaActionSupport   {
	
	
	AnalyticHome ah;

	public AnalyticHome getAh() {
		return ah;
	}

	public void setAh(AnalyticHome ah) {
		this.ah = ah;
	}
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		
		ah=new AnalyticHome();
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return ah;
	}
	
	
	public String input()throws Exception{
		
		AnalyticHomeModel model=new AnalyticHomeModel();
		model.initiate(context,session);
		String[][] result=model.getDashlets(ah);
		model.terminate();
		request.setAttribute("result", result);
		
		return INPUT;
	}
	
	public String recruit(){
		
		AnalyticHomeModel model=new AnalyticHomeModel();
		model.initiate(context,session);
		model.getRecruitData(ah);
		model.terminate();
		return "rec";
	}
	
	public String hrm(){
		AnalyticHomeModel model=new AnalyticHomeModel();
		model.initiate(context,session);
		model.getHrmData(ah);
		model.terminate();
		return "hrm";
	}
	
	public String payroll(){
		AnalyticHomeModel model=new AnalyticHomeModel();
		model.initiate(context,session);
		model.getPayrollData(ah);
		model.terminate();
		return "payroll";
	}

	
	public String leave(){
		
		AnalyticHomeModel model=new AnalyticHomeModel();
		model.initiate(context,session);
		model.getLeaveDetails(ah);
		model.terminate();
		
		
		return "leave";
	}
	public String attendance(){
		AnalyticHomeModel model=new AnalyticHomeModel();
		model.initiate(context,session);
		model.getAttendanceDetails(ah);
		model.terminate();
		return "attendance";
	}
	
	public String pms(){
		AnalyticHomeModel model=new AnalyticHomeModel();
		model.initiate(context,session);
		model.getApprData(ah);
		model.terminate();
		
		
		return "pms";
	}
	
	public String training(){
		AnalyticHomeModel model=new AnalyticHomeModel();
		model.initiate(context,session);
		model.getTrainingData(ah);
		model.terminate();
		
		return "training";
	}
	
	public String tax(){
		AnalyticHomeModel model=new AnalyticHomeModel();
		model.initiate(context,session);
		model.getTaxData(ah);
		model.terminate();
		return "tax";
	}
	
	public String admin(){
		AnalyticHomeModel model=new AnalyticHomeModel();
		model.initiate(context,session);
		model.getAdminData(ah);
		model.terminate();
		return "admin";
	}
	
	public String getLinks()throws Exception{
		String menuCode="";
		try {
			menuCode = (String) request.getParameter("menuCode");
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("llllllllllllllllllllll"+menuCode);
		AnalyticHomeModel model=new AnalyticHomeModel();
		model.initiate(context,session);
		String contextPath=request.getContextPath();
		
		String[][] links=model.getIndividualLinks(ah,menuCode,contextPath);
		model.terminate();
		request.setAttribute("links", links);
		
		return "admin";
	}
}
