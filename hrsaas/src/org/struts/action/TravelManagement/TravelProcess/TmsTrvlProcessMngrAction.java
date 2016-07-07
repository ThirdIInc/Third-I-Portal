package org.struts.action.TravelManagement.TravelProcess;

import org.paradyne.bean.TravelManagement.TravelProcess.TmsTrvlProcessMngr;
import org.paradyne.model.TravelManagement.TravelProcess.TmsTrvlProcessMngrModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author krishna date: 16-JULY-2009
 * 
 */
public class TmsTrvlProcessMngrAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TmsTrvlProcessMngrAction.class);
	TmsTrvlProcessMngr trvlProMngr;

	public void prepare_local() throws Exception {
		trvlProMngr = new TmsTrvlProcessMngr();
		trvlProMngr.setMenuCode(915);
	}

	public Object getModel() {
		return trvlProMngr;
	}

	/**
	 * @return the trvlProMngr
	 */
	public TmsTrvlProcessMngr getTrvlProMngr() {
		return trvlProMngr;
	}

	/**
	 * @param trvlProMngr
	 *            the trvlProMngr to set
	 */
	public void setTrvlProMngr(TmsTrvlProcessMngr trvlProMngr) {
		this.trvlProMngr = trvlProMngr;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		showSetting();
	}

	public String showSetting() throws Exception {
		logger.info("SHOW SETTINGS---------------");
		TmsTrvlProcessMngrModel model = new TmsTrvlProcessMngrModel();
		model.initiate(context, session);
		model.showSetting(trvlProMngr);
		model.terminate();
		return "success";

	}

	public String save() throws Exception {

		boolean result;
		TmsTrvlProcessMngrModel model = new TmsTrvlProcessMngrModel();
		model.initiate(context, session);
		result = model.saveApplication(trvlProMngr);
		if (result)

			addActionMessage("Setting saved Successully");

		else
			addActionMessage("Setting can't saved Successully");
		model.showSetting(trvlProMngr);
		model.terminate();
		return "success";

	}

}
