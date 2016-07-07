package org.struts.action.LMS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import org.paradyne.bean.LMS.TradeUnionWorkCouncilBean;
import org.paradyne.model.LMS.TradeUnionWorkCouncilModel;

import org.struts.lib.ParaActionSupport;

public class TradeUnionWorkCouncilAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TradeUnionWorkCouncilAction.class);
	
	TradeUnionWorkCouncilBean councilBean; 
	
	
	public TradeUnionWorkCouncilBean getCouncilBean() {
		return councilBean;
	}

	public void setCouncilBean(TradeUnionWorkCouncilBean councilBean) {
		this.councilBean = councilBean;
	}

	public void prepare_local() throws Exception {
		
		councilBean = new TradeUnionWorkCouncilBean();
		councilBean.setMenuCode(1150);
		getNavigationPanel(1);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return councilBean;
	}

	public static org.apache.log4j.Logger getLogger() {
		return logger;
	}

	public static void setLogger(org.apache.log4j.Logger logger) {
		ColonyMasterAction.logger = logger;
	}

	

	
	public String input() throws Exception {

		try {
			
			TradeUnionWorkCouncilModel councilModel = new TradeUnionWorkCouncilModel(); 
			councilModel.initiate(context, session);
			councilModel.getList(councilBean, request);
			councilModel.terminate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		getNavigationPanel(1);
		//councilBean.setEnableAll("Y");
		return INPUT;
	}

	public String addNew() {
		try 
		{		
			reset();			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method."+e);
		}
		
		getNavigationPanel(2);
		return SUCCESS;
	}

	/*public String back() {

		TradeUnionWorkCouncilModel councilModel = new TradeUnionWorkCouncilModel(); 
		councilModel.initiate(context, session);
		councilModel.getList(councilBean, request);
		councilModel.terminate();

		getNavigationPanel(1);

		return INPUT;
	}*/
	
	public String back() throws Exception {
		input();
		getNavigationPanel(1);
		councilBean.setEnableAll("Y");
		return INPUT;
	}

	public String addLocalCon()
	{
	   
		councilBean.setBargainAggreement("true");
	   
		TradeUnionWorkCouncilModel councilModel = new TradeUnionWorkCouncilModel(); 
		councilModel.initiate(context, session);  
		
    
		//councilModel.addLocalCon(councilBean ,request);
    	
		councilModel.terminate();  
		
			getNavigationPanel(2);
			return INPUT;
		
	}

	public String save() {
		TradeUnionWorkCouncilModel councilModel = new TradeUnionWorkCouncilModel(); 
		councilModel.initiate(context, session);
		boolean result;
		if (councilBean.getTradeUnionWorkCouncilID().equals("")) {
			result = councilModel.save(councilBean,request);

			if (result) {
				addActionMessage(getMessage("save"));

			} else {
				addActionMessage("duplicate record found");
			}
		} else {
			result = councilModel.update(councilBean,request);
			if (result) {
				addActionMessage(getText("modMessage", ""));
			}// end of if
			else {
				addActionMessage("duplicate record found");
			}// end of else
		}
		councilModel.getList(councilBean, request);
		councilModel.terminate();
		try 
		{		
			input();		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("Exception occurred in Addnew method."+e);
		}
		
		getNavigationPanel(1);
		councilBean.setEnableAll("Y");
		
		return INPUT;
	}

	
/*
		public String delete() {
		boolean result;
		HouseMasterModel model = new HouseMasterModel();
		model.initiate(context, session);
		result = model.delete(bean, request);
		if (result) {
			addActionMessage("Record Deleted successfully.");

		}

		model.terminate();

		getNavigationPanel(1);

		return INPUT;
	}*/

		public String callPage() throws Exception {
			try {
				input();
			} catch (Exception e) {
				// TODO: handle exception
			}
			return INPUT;
		}

	public String edit() {

		try {
			getNavigationPanel(2);
		} catch (Exception e) {
			logger.error("Error in edit" + e);
		}
		return SUCCESS;
	}
	
	public void viewAttachedProof() throws Exception {
		OutputStream oStream = null;
		response.getOutputStream();
		FileInputStream fsStream = null;
		String fileName = "";
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			fileName = request.getParameter("fileName");
			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				}
			}
			// for getting server path where configuration files are saved.
			String path = getText("data_path") + "/TMS/" + poolName
					+ "/Tickets/" + fileName;
			oStream = response.getOutputStream();

			// response.setHeader("Content-type", "application/msword");
			response.setHeader("Content-disposition", "attachment;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("-----in file not found catch", e);
			addActionMessage("File not found");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.info("in finally for closing");
			if (fsStream != null) {
				fsStream.close();
			}
			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}

		// return null;
	}
	
	public String reset() throws Exception {
		getNavigationPanel(2);
		councilBean.setTradeUnionWorkCouncilID("");
		councilBean.setCommitteeName("");
		councilBean.setCommitteeType("");
		councilBean.setLeaderName("");
		councilBean.setBargainingAgreement("");
		councilBean.setProcedureGrievance("");		
		
		return SUCCESS;
	}
	/**
	 * To delete any record
	 */
	public String delete() throws Exception {
		try {
			TradeUnionWorkCouncilModel councilModel = new TradeUnionWorkCouncilModel(); 
			councilModel.initiate(context, session);
			
			String tradeID = request.getParameter("tradeUnionWorkCouncilID");
			
			boolean result = councilModel.delRecord(councilBean,tradeID);
			councilModel.getList(councilBean, request);
			councilModel.terminate();

			if (result) {
				addActionMessage(getMessage("delete"));

			} else {
				addActionMessage(getMessage("no result"));
			}// end of else

			councilBean.setCommitteeName("");
			councilBean.setCommitteeType("");
			councilBean.setLeaderName("");
			councilBean.setBargainingAgreement("");
			councilBean.setProcedureGrievance("");		
			

		} catch (Exception e) {
			System.out.println("Error ========> " + e);
		}
		getNavigationPanel(1);
	//	cbean.setEnableAll("Y");
		return INPUT;
	}
	
	/**
	 * following function is called when delete button is clicked in the jsp
	 * page
	 */

	public String deleteChkRecords() throws Exception {

		try {
			String code[] = request.getParameterValues("hiddenCode");
			TradeUnionWorkCouncilModel councilModel = new TradeUnionWorkCouncilModel(); 
			councilModel.initiate(context, session);
			boolean result = councilModel.deleteCheckedRecords(councilBean, code);
			if (result) {
				addActionMessage(getText("delMessage", ""));
				reset();
			} else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}
			councilModel.getList(councilBean, request);
			getNavigationPanel(1);
			councilModel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured : "+e);
		}
		return "input";

	}
	
	
	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 */
	public String calforedit() throws Exception 
	{
		try {
			TradeUnionWorkCouncilModel councilModel = new TradeUnionWorkCouncilModel(); 
			String custID = request.getParameter("tradeUnionWorkCouncilID");
			System.out.println("custID = " + custID);
			councilModel.initiate(context, session);
			councilModel.calforedit(councilBean, custID,request);		
			councilModel.terminate();
			getNavigationPanel(3);
			} 
		catch (Exception e) 
		{
			System.out.println("Error Occured : " + e);
		}
		//cbean.setEnableAll("N");		
		return SUCCESS;
	}//End of calforedit()
}
