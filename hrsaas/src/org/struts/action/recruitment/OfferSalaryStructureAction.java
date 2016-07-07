/**
 * 
 */
package org.struts.action.recruitment;

import org.paradyne.bean.Recruitment.OfferDetails;
import org.paradyne.model.recruitment.OfferDetailsModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0517
 *
 */
public class OfferSalaryStructureAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OfferDetailsModel.class);
	OfferDetails offerDetails = null;
	public OfferDetails getOfferDetails() {
		return offerDetails;
	}

	public void setOfferDetails(OfferDetails offerDetails) {
		this.offerDetails = offerDetails;
	}

	public void prepare_local() throws Exception {
		offerDetails = new OfferDetails();
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return offerDetails;
	}
	
	/*public String f9salgradeAction() throws Exception {

		String sql = " SELECT SALGRADE_CODE,NVL(SALGRADE_TYPE ,' ') FROM HRMS_SALGRADE_HDR ORDER BY SALGRADE_CODE ";

		String[] headers = { "Salary Grade Code", "Salary Grade Name" };
		String[] headersWidth = { "20", " 40" };

		String[] fieldName = { "salgrdId", "salgrdName" };
		String submitFlag = "true";

		int[] columnIndex = { 0, 1 };
		String submitToMethod = "OfferSalaryStructure_gradeDetail.action";
		setF9Window(sql, headers, headersWidth, fieldName, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String gradeDetail() {
		OfferDetailsModel model = new OfferDetailsModel();

		Object addObj[] = new Object[1];
		model.initiate(context, session);
		String id = offerDetails.getSalgrdId();
		
		model.showGrade(offerDetails, id, request);
		logger.info("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return "success";
	}
*/
	
	/*public String saveSalaryStruc()throws Exception{
		OfferDetailsModel model = new OfferDetailsModel();
		model.initiate(context, session);
		model.saveSalaryStruc(offerDetails);
		model.terminate();
		
		return SUCCESS;
	}
*/

}
