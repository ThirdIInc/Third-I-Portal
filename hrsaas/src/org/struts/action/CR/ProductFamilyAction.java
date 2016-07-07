package org.struts.action.CR;

import org.struts.lib.ParaActionSupport;

public class ProductFamilyAction extends ParaActionSupport{

	public String input()
    throws Exception
  {
	 System.out.println("22 Datareconcile Action is called----------------------------------");
	    String D1_URL = getText("D1_PeoplepowerCRM_URL") + "cr/ProductFamily_input.action";
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
