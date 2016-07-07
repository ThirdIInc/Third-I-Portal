package org.struts.action.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.MyContacts;
import org.paradyne.model.ApplicationStudio.MyContactsModel;
import org.struts.lib.ParaActionSupport;

public class MyContactsAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	MyContacts bean;

	public void prepare_local() throws Exception {
		bean = new MyContacts();
		bean.setMenuCode(2295);
	}

	public Object getModel() {
		return bean;
	}

	public void setBean(MyContacts bean) {
		this.bean = bean;
	}

	public String input() {
		try {
			MyContactsModel model = new MyContactsModel();
			model.initiate(context, session);
			Object[][] dataObj = model.setData(bean);
			request.setAttribute("conData", dataObj);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	public void reset() {
		bean.setHiddenConId("");
		bean.setDeptName("");
		bean.setContactNo("");
		bean.setContactName("");
		bean.setEmailID("");
		bean.setHiddenConId("");
		bean.setContactDivCode("");
		bean.setContactDivision("");

	}

	public String addMyContacts() {
		try {
			MyContactsModel model = new MyContactsModel();
			model.initiate(context, session);
			boolean result = false;
			String contactCode = request.getParameter("contactCode");
			if (contactCode.equals("")) {
				result = model.addMyContacts(bean, contactCode);
				if(result) {
					this.addActionMessage("Contact added successfully");
					reset();
				}
			} else {
				result = model.updateMyContactsData(bean, contactCode);
				if(result) {
					this.addActionMessage("Contact updated successfully");
					reset();
				}
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		return SUCCESS;
	}

	public String delete() {
		try {
			MyContactsModel model = new MyContactsModel();
			model.initiate(context, session);
			boolean result = model.deleteRecord(bean);
			if(result){
				this.addActionMessage("Record deleted successfully");
			}
			model.terminate();
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String f9actionDiv(){
		
		String query ="SELECT DIV_ID, DIV_NAME FROM HRMS_DIVISION ";
	 	if(bean.getUserProfileDivision() !=null && bean.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+bean.getUserProfileDivision() +")"; 
			query+= " ORDER BY UPPER (DIV_NAME) ";	 
		String header =getMessage("division");
		String textAreaId =request.getParameter("divName");				
		String hiddenFieldId =request.getParameter("divCode");		
		String submitFlag ="";
		String submitToMethod ="";		
		setMultiSelectF9(query, header, textAreaId,hiddenFieldId,submitFlag,submitToMethod);
		return "f9multiSelect";
	}
}
