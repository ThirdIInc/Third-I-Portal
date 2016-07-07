package org.struts.action.CR;

import org.struts.lib.ParaActionSupport;

public class ReasonMasterAction extends ParaActionSupport{

	
	
	public String input()
    throws Exception
  {
    //this.bean.setTableFlagCheckedCount(false);
   // this.bean.setFilters(false);
   // this.bean.setFiltersDefault(false);
   // this.bean.setDataReconcileFlag(false);
   //// this.bean.setSourceId("");
   // this.bean.setSourceName("");
    System.out.println("22 Datareconcile Action is called----------------------------------");
    String D1_URL = getText("D1_PeoplepowerCRM_URL") + "cr/ReasonMaster_input.action";
    System.out.println(D1_URL);
    this.response.sendRedirect(D1_URL);
    return "input";
  }

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
