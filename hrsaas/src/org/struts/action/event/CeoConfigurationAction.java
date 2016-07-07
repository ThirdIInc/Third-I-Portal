package org.struts.action.event;

import org.paradyne.bean.event.CeoConfiguration;
import org.paradyne.model.event.CeoConfigurationModel;
import org.struts.lib.ParaActionSupport;

public class CeoConfigurationAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	CeoConfiguration bean;

	public void prepare_local() throws Exception {
		bean = new CeoConfiguration();
		bean.setMenuCode(1165);
	}

	public Object getModel() {
		return bean;
	}

	public CeoConfiguration getBean() {
		return bean;
	}

	public void setBean(CeoConfiguration bean) {
		this.bean = bean;
	}

	public void prepare_withLoginProfileDetails() {
		try {
			CeoConfigurationModel model = new CeoConfigurationModel();
			model.initiate(context, session);
			boolean flag = false;
			flag =model.displayList(bean);
			System.out.println("flag================"+flag);
			if(flag)
			{
				 bean.setShowCeo("false");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String addEmployee() {

		try {
			CeoConfigurationModel model = new CeoConfigurationModel();
			model.initiate(context, session);
			boolean res =model.addEmployee(bean);			
			if(res){
				addActionMessage("Record saved successfully");
				model.displayList(bean);
			}
			else{
				addActionMessage("Record can not be saved ");
				model.displayList(bean);
			}
			bean.setEmpCode("");
			bean.setEmpName("");
			bean.setEmpToken("");
			bean.setCeoType("");
			bean.setCeoDivCode("");
			bean.setCeoDivName("");
			model.terminate();
		} catch (Exception e) {
			System.out.println("Exception in addEmployee--------" + e);
		}
		return SUCCESS;
	}

	public String reset() {
		try {
			bean.setEmpCode("");
			bean.setEmpName("");
			bean.setEmpToken("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String delete() {
		try {			 
			CeoConfigurationModel model = new CeoConfigurationModel();
			model.initiate(context, session);
			boolean res =model.delete( bean);
			if(res){
				addActionMessage("Record deleted successfully");
				model.displayList(bean);
			}
			else{
				addActionMessage("Record can not be deleted ");
				model.displayList(bean);
			}
			model.terminate();
			// bean.setShowCeo("true");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String f9employee() throws Exception {
		try {
			String[] empCode = request.getParameterValues("empCodeItt");
			String code = "0"; //bean.getInitId();
			if (empCode != null) {
				for (int i = 0; i < empCode.length; i++) {// loop x
					if (i == empCode.length) {
						code += empCode[i];
					} else {
						code += "," + empCode[i];
					}
				}// end loop x
			}// end if

			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN ,  HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME "
					+ "	,EMP_ID " + " FROM HRMS_EMP_OFFC    ";
			// query += getprofileQuery(trvlApp);
			query += " WHERE 1=1 AND EMP_STATUS='S'";
			if (bean.getUserProfileDivision() != null
					&& bean.getUserProfileDivision().length() > 0)
				query += "AND EMP_DIV IN (" + bean.getUserProfileDivision()
						+ ")";
			if (!code.equals("")) {
				query += " AND EMP_ID NOT IN(" + code + ")";
			}
			query += "ORDER BY EMP_ID ";
			String[] headers = { "Employee Id", "Employee Name" };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "empToken", "empName", "empCode" };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
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
