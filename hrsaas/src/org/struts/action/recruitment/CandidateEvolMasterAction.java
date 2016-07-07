package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.CandidateEvolMaster;
import org.struts.lib.ParaActionSupport;

public class CandidateEvolMasterAction extends ParaActionSupport {

	CandidateEvolMaster candidate;
	
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return candidate;
	}

}
