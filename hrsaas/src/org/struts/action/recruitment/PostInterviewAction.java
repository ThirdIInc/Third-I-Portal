package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.PostInterview;
import org.struts.lib.ParaActionSupport;

public class PostInterviewAction extends ParaActionSupport {
		
		PostInterview postInt;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return postInt;
	}

	public PostInterview getPostInt() {
		return postInt;
	}

	public void setPostInt(PostInterview postInt) {
		this.postInt = postInt;
	}

}
