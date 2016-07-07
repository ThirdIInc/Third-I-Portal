package org.struts.action.D1;

import org.paradyne.bean.D1.ReferralSourceBean;
import org.paradyne.model.D1.EthinicityModel;
import org.paradyne.model.D1.ReferralSourceModel;
import org.struts.lib.ParaActionSupport;

public class ReferralSourceAction extends ParaActionSupport {

	ReferralSourceBean refBean;

	public void prepare_local() throws Exception {
		refBean = new ReferralSourceBean();
		refBean.setMenuCode(2043);
	}

	public Object getModel() {

		return refBean;
	}

	public ReferralSourceBean getRefBean() {
		return refBean;
	}

	public void setRefBean(ReferralSourceBean refBean) {
		this.refBean = refBean;
	}

	public String input() {
		System.out.println("-------inside input method-----");
		ReferralSourceModel model = new ReferralSourceModel();
		model.initiate(context, session);
		model.initialData(refBean, request);
		model.terminate();
		getNavigationPanel(1);

		return INPUT;
	}

	public String callPage() throws Exception {

		ReferralSourceModel model = new ReferralSourceModel();
		model.initiate(context, session);
		input();
		getNavigationPanel(1);
		model.terminate();

		return "input";
	}

	public String addNew() {
		ReferralSourceModel model = new ReferralSourceModel();
		model.initiate(context, session);
		reset();
		getNavigationPanel(2);
		return "success";
	}

	/*Reset Function*/
	public String reset() {
		refBean.setRefSource("");
		refBean.setRefCode("");
		getNavigationPanel(2);
		return "success";

	}

	/* Method call for Saving Data*/
	public String save() throws Exception {
		try {

			System.out.println("in save call-------------------------");
			ReferralSourceModel model = new ReferralSourceModel();
			model.initiate(context, session);
			boolean result;
			System.out.println("businessCode here ------- "
					+ refBean.getRefCode());

			if (refBean.getRefCode().equals("")) {
				result = model.insert(refBean);
				System.out.println("result here we get is --------------->"
						+ result);
				if (result) {

					addActionMessage(getMessage("save"));

				} else {
					addActionMessage(getMessage("duplicate"));
					reset();
					// new added
				}
			} else {

				result = model.update(refBean);

				if (result) {
					addActionMessage(getMessage("update"));

				} else {
					addActionMessage(getMessage("duplicate"));
					reset();

				}

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(3);
		refBean.setEnableAll("N");
		return "success";

	}

	public String cancel() {
		input();
		getNavigationPanel(1);
		refBean.setEnableAll("Y");
		return "input";
	}

	public String delete() throws Exception {
		ReferralSourceModel model = new ReferralSourceModel();
		model.initiate(context, session);
		System.out.println("delete method ------------------------");
		boolean result;
		result = model.delete(refBean, request);
		model.initialData(refBean, request);
		model.terminate();

		if (result) {
			addActionMessage("Record Deleted successfully.");

		}

		getNavigationPanel(1);

		return "input";
	}

	/**following function is called when delete button is clicked in the jsp page*/

	public String deleteChkRecords() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		ReferralSourceModel model = new ReferralSourceModel();

		model.initiate(context, session);
		boolean result = model.deleteCheckedRecords(refBean, code);

		if (result) {
			addActionMessage(getText("delMessage", ""));
			reset();
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}

		model.initialData(refBean, request);
		getNavigationPanel(1);
		model.terminate();

		return "input";
	}

	public String editBusinessData() {
		try {
			ReferralSourceModel model = new ReferralSourceModel();
			model.initiate(context, session);
			model.editBusinessData(refBean);
			getNavigationPanel(3);
			refBean.setEnableAll("N");
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();

		}

		return "success";
	}

	/** This f9action is for Search pop up window */
	public String f9referralAction() throws Exception {

		String query = "SELECT  REFERRAL_SOURCE_NAME,REFERRAL_ID  FROM HRMS_D1_REFERRAL ";

		String[] headers = { getMessage("ref_source") };

		String[] headerWidth = { "40" };

		String[] fieldNames = { "refSource", "refCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "true";

		String submitToMethod = "ReferralSource_setRecord.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	/*Search Button Functionality*/

	public String setRecord() throws Exception {
		try {
			ReferralSourceModel model = new ReferralSourceModel();
			model.initiate(context, session);
			model.terminate();
			getNavigationPanel(3);

		} catch (Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(3);
		refBean.setEnableAll("N");
		return "success";
	}

}
