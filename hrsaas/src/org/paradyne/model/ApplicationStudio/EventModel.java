package org.paradyne.model.ApplicationStudio;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.lib.ModelBase;
import org.paradyne.bean.ApplicationStudio.*;

public class EventModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	EventMaster cadMaster = null;

	/* this is for data on loading page */
	public void eventData(EventMaster eventMaster, HttpServletRequest request) {
		String query = " SELECT EVENT_CODE, EVENT_NAME, EVENT_EMAIL"
						+ " FROM HRMS_PORTAL_EVENT ORDER BY EVENT_CODE";
		Object data[][] = getSqlModel().getSingleResult(query);
		ArrayList<EventMaster> list = new ArrayList<EventMaster>();
		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				eventMaster.setModeLength("true");				
				EventMaster bean1 = new EventMaster();
				bean1.setHiddenAutoCode(String.valueOf(data[i][0]));					
				bean1.setEvent(String.valueOf(data[i][1]));
				bean1.setMailId(String.valueOf(data[i][2]));				
				list.add(bean1);
			}
			eventMaster.setEventList(list);
		}

	}

	/* for inserting record */
	public boolean addEventData(EventMaster bean) {
		boolean result;
		
		String insertQuery="INSERT INTO HRMS_PORTAL_EVENT"
						  + " (EVENT_CODE,EVENT_NAME,EVENT_EMAIL) VALUES "
						  + " ((SELECT NVL(MAX(EVENT_CODE),0)+1 FROM HRMS_PORTAL_EVENT),'"+
						   bean.getEvent()+"','"+ bean.getMailId()+"')";
		result = getSqlModel().singleExecute(insertQuery);
		return result;
	}

	public boolean deleteEvent(EventMaster bean, String[] code) {
		boolean result = false;
		// Object delObj[][] = new Object[1][1];
		// delObj[0][0] = eventMaster.getEvent();
		System.out.println("====EVENT=======" + bean.getEvent());
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				String query = "DELETE FROM HRMS_PORTAL_EVENT WHERE EVENT_CODE="
						+ code[i];
				result = getSqlModel().singleExecute(query);
			}
		}

		return result;
	}

	public void editEventData(EventMaster eventMaster, String hiddenCode) {

		String query = " SELECT EVENT_CODE,EVENT_NAME,EVENT_EMAIL  "
					  + " FROM HRMS_PORTAL_EVENT  WHERE EVENT_CODE="+ hiddenCode;
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null) {
			eventMaster.setEventCode(String.valueOf(data[0][0]));
			eventMaster.setEvent(String.valueOf(data[0][1]));
			eventMaster.setMailId(String.valueOf(data[0][2]));
		
		}
	}

	public boolean modifyEvent(EventMaster eventMaster) {
		boolean result;
		String query = " UPDATE HRMS_PORTAL_EVENT SET EVENT_CODE=?, EVENT_NAME=?,"
					  + " EVENT_EMAIL=? WHERE EVENT_CODE="
				+ eventMaster.getEventCode();

		Object modObj[][] = new Object[1][3];
		modObj[0][0]= eventMaster.getEventCode();
		modObj[0][1] = eventMaster.getEvent().trim();
		modObj[0][2] = eventMaster.getMailId();
		result = getSqlModel().singleExecute(query, modObj);
		return result;

	}

	public boolean delete(EventMaster eventMaster) {
		boolean result = false;

		try {
			String deleteQuery = "  DELETE FROM HRMS_PORTAL_EVENT "
					+ " WHERE EVENT_CODE=" + eventMaster.getEventCode();
			result = getSqlModel().singleExecute(deleteQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
