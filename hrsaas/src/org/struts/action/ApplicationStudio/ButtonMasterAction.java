package org.struts.action.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.ButtonMaster;
import org.paradyne.model.ApplicationStudio.ButtonMasterModel;
import org.struts.lib.ParaActionSupport;

public class ButtonMasterAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	ButtonMaster buttonMaster;

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		buttonMaster = new ButtonMaster();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return buttonMaster;
	}

	public ButtonMaster getButtonMaster() {
		return buttonMaster;
	}

	public void setButtonMaster(ButtonMaster buttonMaster) {
		this.buttonMaster = buttonMaster;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		ButtonMasterModel model = new ButtonMasterModel();
		model.initiate(context, session);
		getButtonList();
		model.terminate();
	}

	public String getButtonList() {
		try {
			ButtonMasterModel model = new ButtonMasterModel();
			model.initiate(context, session);
			String poolDir = (String) session.getAttribute("session_pool");
			System.out.println("poolDir" + poolDir);
			String fileName = getText("data_path");
			Object[][] btnList = null;
			model.getButtonMaster(fileName, buttonMaster, request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in getButtonList-----------------" + e);
		}
		return SUCCESS;
	}

	public String addList() {

		try {
			ButtonMasterModel model = new ButtonMasterModel();
			model.initiate(context, session);
			String[] buttonCode = request.getParameterValues("buttonCode");
			String[] buttonName = request.getParameterValues("buttonName");
			String[] buttonOrder = request.getParameterValues("buttonOrder");
			String[] buttonFlag = request.getParameterValues("buttonFlag");
			String[] enabledisable = request
					.getParameterValues("enabledisable");

			if (buttonMaster.getHiddenEdit().equals(""))
			// adds queries into the list
			{
				model.addList(buttonMaster, buttonCode, buttonName,
						buttonOrder, buttonFlag, enabledisable, 1);
			} else {

				System.out
						.println("in my method---------------------------------------");
				model.modList(buttonMaster, buttonCode, buttonName,
						buttonOrder, buttonFlag, enabledisable, 1);
			}
			clear();
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in addList-----------------" + e);
		}
		return SUCCESS;
	}

	public String save() {
		try {
			ButtonMasterModel model = new ButtonMasterModel();
			model.initiate(context, session);
			String poolDir = (String) session.getAttribute("session_pool");
			String fileName1 = getText("data_path");
			String fileName = getText("data_path")
					+ "\\NavigationPanel\\buttons.xml";
			model.save(buttonMaster, fileName, request);
			addActionMessage("Record saved successfully");
			model.getButtonMaster(fileName1, buttonMaster, request);
			model.terminate();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in save-----------------" + e);
		}
		return SUCCESS;
	}

	public String clear() {
		buttonMaster.setBtnCode("");
		buttonMaster.setBtnName("");
		buttonMaster.setBtnFlag("");
		buttonMaster.setEnableField("");
		buttonMaster.setBtnOrder("");
		buttonMaster.setHiddenEdit("");
		return SUCCESS;
	}

	public String editData() {
		try {
			ButtonMasterModel model = new ButtonMasterModel();
			model.initiate(context, session);
			String[] buttonCode = request.getParameterValues("buttonCode");
			String[] buttonName = request.getParameterValues("buttonName");
			String[] buttonOrder = request.getParameterValues("buttonOrder");
			String[] buttonFlag = request.getParameterValues("buttonFlag");
			String[] enabledisable = request
					.getParameterValues("enabledisable");
			int rowEdited = 0;
			rowEdited = Integer.parseInt(buttonMaster.getHiddenEdit()) - 1;
			String editBtnName = buttonName[rowEdited];
			String editBtnOrder = buttonOrder[rowEdited];
			String editFlag = buttonFlag[rowEdited];
			String editFieldEnable = enabledisable[rowEdited];
			if (editFlag.equals("Insert")) {
				buttonMaster.setBtnFlag("I");
			} else if (editFlag.equals("Delete")) {
				buttonMaster.setBtnFlag("D");
			} else if (editFlag.equals("Update")) {
				buttonMaster.setBtnFlag("U");
			} else if (editFlag.equals("View")) {
				buttonMaster.setBtnFlag("V");
			}
			if (editFieldEnable.equals("Yes")) {
				buttonMaster.setEnableField("Y");
			} else if (editFieldEnable.equals("No")) {
				buttonMaster.setEnableField("N");
			}
			buttonMaster.setBtnName(editBtnName);
			buttonMaster.setBtnOrder(editBtnOrder);
			model.modList(buttonMaster, buttonCode, buttonName, buttonOrder,
					buttonFlag, enabledisable, 1);
			model.terminate();

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in editData-----------------" + e);
		}
		return SUCCESS;
	}

	public String deleteData() {
		try {
			ButtonMasterModel model = new ButtonMasterModel();
			model.initiate(context, session);
			String[] buttonCode = request.getParameterValues("buttonCode");
			String[] buttonName = request.getParameterValues("buttonName");
			String[] buttonFlag = request.getParameterValues("buttonFlag");
			String[] enabledisable = request.getParameterValues("enabledisable");
			String[] buttonOrder = request.getParameterValues("buttonOrder");
			boolean result = model.deleteData(buttonMaster, buttonCode,
					buttonName, buttonFlag, enabledisable, buttonOrder);

			if (result) {
				addActionMessage("Record deleted successfully");
			}

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Exception in deleteData-----------------------" + e);
		}
		return SUCCESS;
	}
}
