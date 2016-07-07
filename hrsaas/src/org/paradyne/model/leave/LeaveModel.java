package org.paradyne.model.leave;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;
 import org.paradyne.lib.ModelBase;
import org.paradyne.bean.attendance.TestBean;
import org.paradyne.bean.leave.LeaveMaster;

public class LeaveModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 

	public void prePopulate(LeaveMaster bean) {
		/*
		 * PREPARE THE PRE-POPULATED ATTRIBUTES HERS
		 */
		ArrayList<Object> list = new ArrayList<Object>();
		TestBean testbean = new TestBean();
		testbean.setId("1");
		testbean.setName("Sachin");
		list.add(testbean);
		list.add(testbean);
		bean.setTestList(list);
	}

	public boolean addLeave(LeaveMaster bean) {
		Object addObj[][] = new Object[1][2];
		addObj[0][0] = bean.getLeavCode();
		addObj[0][1] = bean.getLeavName();
		// addObj[0][2] =bean.getLeavDesc();
		return getSqlModel().singleExecute(getQuery(2), addObj);
	}

	public boolean modLeave(LeaveMaster bean) {
		return true;
	}

	public boolean deleteLeave(LeaveMaster bean) {
		return true;
	}

	public LeaveMaster getLeaveRecord(String ID) {
		return new LeaveMaster();
	}
}