package org.struts.action.LMS;

import java.util.Iterator;
import org.paradyne.bean.LMS.LmsDesignation;
import org.paradyne.model.LMS.LmsDesignationModel;
import org.struts.lib.ParaActionSupport;

public class LmsDesignationAction extends ParaActionSupport {

	
	LmsDesignation lmsdesigbean;
	public LmsDesignation getLmsdesigbean() {
		return lmsdesigbean;
	}

	public void setLmsdesigbean(LmsDesignation lmsdesigbean) {
		this.lmsdesigbean = lmsdesigbean;
	}

	public void prepare_local() throws Exception {		
		lmsdesigbean = new LmsDesignation();
		lmsdesigbean.setMenuCode(1136);
	}

	public Object getModel() {		
		return lmsdesigbean;
	}
	
	
	public String input()
	{
		try {
			LmsDesignationModel model = new LmsDesignationModel();
			model.initiate(context, session);
			model.getInitialList(lmsdesigbean, request,"Y");	
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		lmsdesigbean.setShowList(true);
		return INPUT;
	}
	
	public String save()
	{
		try {
			 
			LmsDesignationModel model = new LmsDesignationModel();
			model.initiate(context, session);
			boolean result = model.saveRecords(lmsdesigbean, request);
			if(result)
			{
				addActionMessage("Record saved Successfully.");
			}else{
				addActionMessage("Records can not be save.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		lmsdesigbean.setAbbrevitaionCodeCheck(lmsdesigbean.getAbbrevitaionCodeCheck()) ;
		lmsdesigbean.setHiddenDesignationAbbreviation(lmsdesigbean.getHiddenDesignationAbbreviation());
		processFunction();	
		return INPUT;
	}
	
	public String reset()
	{
		try {			
			lmsdesigbean.setConfChk("");
			lmsdesigbean.setHiddenDesignationTypeCode("");
			lmsdesigbean.setConfDelChk("");
			lmsdesigbean.setHiddenMappedDesignationTypeCode("");
			lmsdesigbean.setAbbrevitaionCodeCheck(lmsdesigbean.getAbbrevitaionCodeCheck()) ;
			lmsdesigbean.setHiddenDesignationAbbreviation(lmsdesigbean.getHiddenDesignationAbbreviation());
		} catch (Exception e) {
			e.printStackTrace();
		}
		processFunction();		
		return INPUT;
		
	}
	
	public String processFunction()
	{
		try {			
			String designationListTypeID = request.getParameter("designationListTypeID");
			String designationAbbreviation = request.getParameter("designationAbbreviation");
			
			LmsDesignationModel model = new LmsDesignationModel();
			try {
				if(designationListTypeID==null)
				{
					lmsdesigbean.setAbbrevitaionCodeCheck(lmsdesigbean.getAbbrevitaionCodeCheck()) ;
					lmsdesigbean.setHiddenDesignationAbbreviation(lmsdesigbean.getHiddenDesignationAbbreviation());
				}
				else{					
					lmsdesigbean.setAbbrevitaionCodeCheck(Integer
							.parseInt(designationListTypeID));
					lmsdesigbean.setHiddenDesignationAbbreviation(designationAbbreviation);
				}
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			model.initiate(context, session);
			model.processList(lmsdesigbean, request,"Y",lmsdesigbean.getAbbrevitaionCodeCheck(),lmsdesigbean.getHiddenDesignationAbbreviation());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		lmsdesigbean.setShowList(false);
		lmsdesigbean.setShowDetails(true);		
		return INPUT;
	}
	
	public String returntoListFun()
	{
		try {
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}	
	
	public String delete()
	{
		try {
			 
			LmsDesignationModel model = new LmsDesignationModel();
			model.initiate(context, session);
			boolean delresult = model.deleteRecords(lmsdesigbean, request);
			if(delresult)
			{
				addActionMessage("Record deleted Successfully.");
			}else{
				addActionMessage("Due to some exception selected records can not be delete.");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		lmsdesigbean.setAbbrevitaionCodeCheck(lmsdesigbean.getAbbrevitaionCodeCheck()) ;
		lmsdesigbean.setHiddenDesignationAbbreviation(lmsdesigbean.getHiddenDesignationAbbreviation());
		processFunction();	
		return INPUT;
		
	}
}
