package org.struts.action.CR;

import org.paradyne.bean.CR.MRCdataUpload;
import org.struts.lib.ParaActionSupport;

public class MRCdataUploadAction  extends ParaActionSupport{
	MRCdataUpload bean=new MRCdataUpload();
	
	@Override
	public void prepare_local() throws Exception {
		this.bean=new MRCdataUpload();
		bean.setMenuCode(5080);
		
	}

	public Object getModel() {
	
		return this.bean;
	}
	public String input()throws Exception{
		 String D1_URL = getText("D1_PeoplepowerCRM_URL") + "cr/MRCdataUpload_input.action";
		    System.out.println(D1_URL);
		    this.response.sendRedirect(D1_URL);
		return INPUT;
	}
	

}
