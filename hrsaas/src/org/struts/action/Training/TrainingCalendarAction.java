/**
 * 
 */
package org.struts.action.Training;

import java.util.TreeMap;

import org.paradyne.bean.Training.TrainingCalendarBean;
import org.paradyne.bean.admin.master.CenterMaster;
import org.paradyne.lib.DataModificatonUtil;
import org.paradyne.model.Training.TrainingCalendarModel;
import org.paradyne.model.admin.master.CenterModel;
import org.paradyne.model.payroll.PayrollZoneMasterModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author ankita.wankhade
 *
 */
public class TrainingCalendarAction extends ParaActionSupport {

TrainingCalendarBean trainingCalendar;
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	
	public void prepare_local() throws Exception {
		trainingCalendar = new TrainingCalendarBean();
		trainingCalendar.setMenuCode(5060);
	}
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "calanderData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	public TrainingCalendarBean getTrainingCalendar() {
		return trainingCalendar;
	}
	public void setTrainingCalendar(TrainingCalendarBean trainingCalendar) {
		this.trainingCalendar = trainingCalendar;
	}
	public Object getModel() {
		return trainingCalendar;
	}
	public String input() throws Exception {
		trainingCalendar.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		TrainingCalendarModel model = new TrainingCalendarModel();
		model.initiate(context, session);

		model.getTableData(trainingCalendar, request);
		
		model.terminate();
		
		DataModificatonUtil dmu = new DataModificatonUtil();
		dmu.initiate(context, session);
		TreeMap map = dmu.getGenderXml("zone");
		trainingCalendar.setMap(map);
		dmu.terminate();
	}

	
	/**
	 * To edit any record in the list by double clicking on it
	 * @return String
	 * @throws Exception
	 */
	
	
}
