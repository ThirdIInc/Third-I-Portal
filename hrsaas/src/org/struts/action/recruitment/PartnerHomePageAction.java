package org.struts.action.recruitment;   
import org.paradyne.bean.Recruitment.PartnerHomePage;
import org.paradyne.model.recruitment.PartnerLoginModel;

/**
 * @author lakkichand
 * modified by: Reeba_Joseph
 * @Date 12-DEC-2008
 */
public class PartnerHomePageAction extends PartnerActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PartnerHomePageAction.class);
	
	PartnerHomePage partnerHomeBeanInstance =null; 
	public void prepare_local() throws Exception {
		partnerHomeBeanInstance =new PartnerHomePage();
	}

	public Object getModel() {
		return partnerHomeBeanInstance;
	}
	
	public String input(){
		return SUCCESS;
	}
	
	public String create(){
		try {
			String newMenuCode = request.getParameter("menuCode");
			PartnerLoginModel model = new PartnerLoginModel();
			model.initiate(context, session);
			model.createMainMenu(request, newMenuCode);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	public PartnerHomePage getPartnerHomeBeanInstance() {
		return partnerHomeBeanInstance;
	}

	public void setPartnerHomeBeanInstance(PartnerHomePage partnerHomeBeanInstance) {
		this.partnerHomeBeanInstance = partnerHomeBeanInstance;
	}

}
