package org.struts.action.Poll;

import org.paradyne.bean.Poll.PollView;
import org.struts.lib.ParaActionSupport;

public class PollViewAction extends ParaActionSupport {
	PollView view;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		view=new PollView();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return view;
	}

}
