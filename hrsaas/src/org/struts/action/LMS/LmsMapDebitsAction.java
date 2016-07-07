package org.struts.action.LMS;

import org.paradyne.bean.LMS.LmsMapDebits;
import org.paradyne.model.LMS.LmsDesignationModel;
import org.paradyne.model.LMS.LmsMapDebitsModel;
import org.struts.lib.ParaActionSupport;

public class LmsMapDebitsAction extends ParaActionSupport{
	LmsMapDebits mapdebitbean ;

	public LmsMapDebits getMapdebitbean() {
		return mapdebitbean;
	}

	public void setMapdebitbean(LmsMapDebits mapdebitbean) {
		this.mapdebitbean = mapdebitbean;
	}

	@Override
	public void prepare_local() throws Exception {
		mapdebitbean = new LmsMapDebits();
		mapdebitbean.setMenuCode(1144);		
	}

	public Object getModel() {
		return mapdebitbean;
	}
	
	public String input()
	{
		try {
			LmsMapDebitsModel model = new LmsMapDebitsModel();
			model.initiate(context, session);
			model.getDebitList(mapdebitbean, request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return INPUT;
	}
	
	
	
	public String save()
	{
			try {
				LmsMapDebitsModel model = new LmsMapDebitsModel();
				model.initiate(context, session);
				
				boolean result = model.saveRecords(mapdebitbean, request);
				if(result)
					addActionMessage("Record saved Successfully.");
						
				model.getDebitList(mapdebitbean, request);
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			input();
			getNavigationPanel(1);
		return INPUT;
	}
	
	public String reset()
	{
		try {			
			mapdebitbean.setDebitType("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();	
		getNavigationPanel(1);
		return INPUT;
		
	}
	
}
