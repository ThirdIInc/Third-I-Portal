/**
 * 
 */
package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.InductionStatusReport;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0517
 *
 */
public class InductionStatusReportAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(InductionScheduleAction.class);
	
	InductionStatusReport inducReport;
	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		inducReport = new InductionStatusReport();
		inducReport.setMenuCode(834);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return inducReport;
	}

	public InductionStatusReport getInducReport() {
		return inducReport;
	}

	public void setInducReport(InductionStatusReport inducReport) {
		this.inducReport = inducReport;
	}

}//end of class
