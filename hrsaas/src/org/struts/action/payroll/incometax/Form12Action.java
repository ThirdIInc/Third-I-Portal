package org.struts.action.payroll.incometax;
import org.paradyne.bean.payroll.incometax.Form12;
import org.paradyne.model.payroll.incometax.Form12Model;
import org.struts.lib.ParaActionSupport;
/*
 * @Saipavan voleti
 * Date:14/10/2008
 */

public class Form12Action extends ParaActionSupport {


	Form12 formbean;
	public void prepare_local() throws Exception {
		formbean=new Form12();
		formbean.setMenuCode(713);
		
		//formbean.setMenuCode();
		

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return formbean;
	}

	public Form12 getFormbean() {
		return formbean;
	}

	public void setFormbean(Form12 formbean) {
		this.formbean = formbean;
	}

	public void report() {
		try {
			Form12Model model = new Form12Model();
			model.initiate(context, session);
			String logoPath = getText("data_path")+"/logos/eGov/PFSmall.bmp";
			model.report(formbean, request, response, logoPath, "");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String f9actionDiv() throws Exception {
		
		String query = "SELECT DIV_ID,DIV_NAME  FROM HRMS_DIVISION ";
		
		if(formbean.getUserProfileDivision() !=null && formbean.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+formbean.getUserProfileDivision() +")"; 
			query+= " ORDER BY  DIV_ID ";
			
			
		String[] headers = {getMessage("division.code") ,getMessage("division")};
		String[] headerWidth = {"10", "90" };
		String[] fieldNames = {"divisioncode" ,"divisionName"};
		int[] columnIndex = {0,1};
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,submitFlag, submitToMethod);
		
		return "f9page";
	}	
	
	public final String reportMail(){
		try {
			Form12Model model = new Form12Model();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			String logoPath = getText("data_path")+"/logos/eGov/PFSmall.bmp";
			String reportPath =  getText("data_path") + "/Report/Payroll/" + poolName + "/";
			model.report(formbean, request, response, logoPath, reportPath);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

}
