package org.struts.action.vendor;

/**
 * @ author Archana Salunkhe
 * @ purpose : Change Password of Partner
 * @ Date :20-May-2012
 */
import org.paradyne.bean.vendor.PartnerChgPassword;
import org.paradyne.model.vendor.PartnerChgPasswordModel;

public class PartnerChgPasswordAction  extends VendorActionSupport{
	private static final long serialVersionUID = 1L;

	PartnerChgPassword partnerChgPass;
	public void prepare_local() throws Exception {
		
		partnerChgPass = new PartnerChgPassword();
		partnerChgPass.setMenuCode(2308);
	}

	public Object getModel() {
		
		return partnerChgPass;
	}
	
	public String input () throws Exception{
		
		PartnerChgPasswordModel model = new PartnerChgPasswordModel();
		model.initiate(context, session);
		model.getPartnerDetails(partnerChgPass);
		model.terminate();
		return INPUT;
	}

	public String save(){
		PartnerChgPasswordModel model = new PartnerChgPasswordModel();
	    model.initiate(context, session);
		try{
		    System.out.println("In vendor's Password Change");
		    String result = model.save(partnerChgPass);
		    if(result.equals("success")){
		    	addActionMessage("success");
		    }
		    if(result.equals("InvalidOld")){
		    	addActionMessage("InvalidOld");
		    }
		}catch(Exception e){
			e.printStackTrace();			
		}
		    model.terminate();		
		    return SUCCESS;
	}
	
	public String reset(){
		partnerChgPass.setPartnerChgNm("");
		partnerChgPass.setPartnerChgCode("");
		partnerChgPass.setPartnerOldPass("");
		partnerChgPass.setPartnerNewPass("");
		partnerChgPass.setPartnerConfPass("");
		try{
		  return input();
		}catch(Exception e){
			e.printStackTrace();
		}		
		return SUCCESS;
	}
}
