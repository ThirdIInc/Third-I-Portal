package org.struts.action.Poll;

import org.paradyne.bean.Poll.PollQuestion;
import org.struts.lib.ParaActionSupport;

public class PollQuestionAction extends ParaActionSupport {
	PollQuestion pollQues;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		pollQues=new PollQuestion();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return pollQues;
	}

}
