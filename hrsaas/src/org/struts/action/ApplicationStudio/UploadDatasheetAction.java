package org.struts.action.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.UploadDatasheet;
import org.paradyne.model.ApplicationStudio.UploadDatasheetModel;
import org.struts.lib.ParaActionSupport;

public class UploadDatasheetAction extends ParaActionSupport {
	UploadDatasheet uploadDatasheet;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		uploadDatasheet=new UploadDatasheet();
		uploadDatasheet.setMenuCode(837);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return uploadDatasheet;
	}

	public UploadDatasheet getUploadDatasheet() {
		return uploadDatasheet;
	}

	public void setUploadDatasheet(UploadDatasheet uploadDatasheet) {
		this.uploadDatasheet = uploadDatasheet;
	}

	
	
	public String generate()throws Exception {
           
		UploadDatasheetModel model = new UploadDatasheetModel();
		model.initiate(context,session);
		
		boolean result;
		try
		{
			result=model.generate(uploadDatasheet, session);
			if(result)
			{
					addActionMessage("Record generated Successfully !");
			}//if ends
			else
			{
				addActionMessage("Record can not be generated. !");
				
			}//else ends
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		model.terminate();
		
		return "success";
	}
}
