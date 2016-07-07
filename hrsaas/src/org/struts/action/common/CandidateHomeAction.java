package org.struts.action.common;

import org.paradyne.bean.common.CandidateHomeBean;
import org.paradyne.model.recruitment.CandidateLoginModel;
import org.struts.lib.DyneActionSupport;

/**
 * @author AA0517
 *
 */
public class CandidateHomeAction extends DyneActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.DyneActionSupport.class);
	
	CandidateHomeBean CandidateHomeBeanInstance =null; 
	public void prepare_local() throws Exception {
		CandidateHomeBeanInstance =new CandidateHomeBean();
	}

	public Object getModel() {
		return CandidateHomeBeanInstance;
	}
	
	public String input(){
		return SUCCESS;
	}
	
	public String create(){
		try {
			logger.info("inside create of Candidate Home Action. ");
			CandidateLoginModel model = new CandidateLoginModel();
			model.initiate(context, session);
			model.createMainMenu(request);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public CandidateHomeBean getCandidateHomeBeanInstance() {
		return CandidateHomeBeanInstance;
	}

	public void setCandidateHomeBeanInstance(
			CandidateHomeBean candidateHomeBeanInstance) {
		CandidateHomeBeanInstance = candidateHomeBeanInstance;
	}

}
