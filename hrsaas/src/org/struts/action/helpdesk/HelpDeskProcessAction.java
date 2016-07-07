package org.struts.action.helpdesk;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.TreeMap;

import org.paradyne.bean.helpdesk.HelpDeskProcess;
import org.paradyne.model.Asset.AssetEmployeeModel;
import org.paradyne.model.helpdesk.HelpDeskProcessModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author AA0554
 * 
 */
public class HelpDeskProcessAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(HelpDeskProcessAction.class);
	HelpDeskProcess helpDeskProcess;

	@Override
	public void prepare_local() throws Exception {
		helpDeskProcess = new HelpDeskProcess();
		helpDeskProcess.setMenuCode(1178);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return helpDeskProcess;
	}

	public HelpDeskProcess getHelpDeskProcess() {
		return helpDeskProcess;
	}

	public void setHelpDeskProcess(HelpDeskProcess helpDeskProcess) {
		this.helpDeskProcess = helpDeskProcess;
	}

	public String retrieveDetails() {
		String requestId = request.getParameter("reqAppCode");
		String reqStatus = request.getParameter("reqStatus");
		
		System.out.println("requestId"+requestId);
		System.out.println("reqStatus"+reqStatus);
		
		System.out.println("retrieveDetails===");
		helpDeskProcess.setRequestId(requestId);
		helpDeskProcess.setStatus(reqStatus);
		HelpDeskProcessModel model = new HelpDeskProcessModel();
		model.initiate(context, session);
		
		if(requestId!=null){
			if (reqStatus.equals("Resolved")|| helpDeskProcess.getEscalatedFlag().equals("true"))
				getNavigationPanel(3);
			else {
				getNavigationPanel(2);
			}
		}
		
		model.getRequestDetails(helpDeskProcess);
		model.setSlaDetails(helpDeskProcess);
		TreeMap<String,String> map = model.getStatusMap();
			/*new TreeMap();
		String sql = " SELECT  STATUS_ABBREV,STATUS_CATAGORY_NAME  FROM  HELPDESK_SLA_STATUS_CATAGORY  ";
		// + " where ASSET_CATEGORY_CODE= "+chk;
		Object[][] data = model.getSqlModel().getSingleResult(sql);
		map.put("","Select");
		for (int j = 0; j < data.length; j++) {

			map.put(String.valueOf(data[j][0]).trim(), String.valueOf(data[j][1]));

		}// end inner for
*/		helpDeskProcess.setStatusMap(map);
		model.setActivityLogDetails(helpDeskProcess);

		model.terminate();
		return SUCCESS;
	}

	public String getList() {
		String listStatus = request.getParameter("listStatus");
		if (listStatus.equals("PD")) {
			return input();
		} else {
			return getResolvedList();
		}
	}

	public void viewFile() throws IOException {
		OutputStream oStream = null;
		response.getOutputStream();
		FileInputStream fsStream = null;
		String fileName = "";
		String mimeType = "";
		try {
			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			} // end of if
			fileName = request.getParameter("fileName");
			logger.info("fileName--->>>" + fileName);
			fileName = fileName.replace(".", "#");
			String[] extension = fileName.split("#");
			String ext = extension[extension.length - 1];
			fileName = fileName.replace("#", ".");
			logger.info("extext--->>>" + ext);
			if (ext.equals("pdf")) {
				mimeType = "acrobat";
			} // end of if
			else if (ext.equals("doc")) {
				mimeType = "msword";
			} // end of else if
			else if (ext.equals("xls")) {
				mimeType = "msexcel";
			} // end of else if
			else if (ext.equals("xlsx")) {
				mimeType = "msexcel";
			} // end of else
			else if (ext.equals("jpg")) {
				mimeType = "jpg";
			} // end of else if
			else if (ext.equals("txt")) {
				mimeType = "txt";
			} // end of else if
			else if (ext.equals("gif")) {
				mimeType = "gif";
			} // end of else if
			// if file name is null, open a blank document
			if (fileName == null) {
				if (fileName.length() <= 0) {
					fileName = "blank.doc";
				} // end of if
			} // end of if

			// for getting server path where configuration files are saved.

			String path = getText("data_path") + "/HelpDesk/" + poolName
					+ "/" + fileName;
			logger.info("path===" + path);
			oStream = response.getOutputStream();
			if (ext.equals("pdf")) {
				// response.setHeader("Content-type",
				// "application/"+mimeType+"");
			} // end of if
			else {
				response.setHeader("Content-type", "application/" + mimeType
						+ "");
			}
			response.setHeader("Content-disposition", "inline;filename=\""
					+ fileName + "\"");

			int iChar;
			fsStream = new FileInputStream(path);

			while ((iChar = fsStream.read()) != -1) {
				oStream.write(iChar);
			} // end of while

		} catch (FileNotFoundException e) {

			logger.error("-----in file not found catch", e);
			addActionMessage("proof document not found");
		} // end of catch
		catch (Exception e) {
			e.printStackTrace();
		} // end of catch
		finally {
			logger.info("in finally for closing");
			if (fsStream != null) {
				fsStream.close();
			} // end of if
			if (oStream != null) {
				oStream.flush();
				oStream.close();
			} // end of if
		} // end of finally
		// return null;
	}

	public String input() {
		
		String source = request.getParameter("src");
		helpDeskProcess.setSrc(source);
		boolean isFilter = false;
		HelpDeskProcessModel model = new HelpDeskProcessModel();
		model.initiate(context, session);

		model.terminate();
		String listStatus = request.getParameter("listStatus");
		
		String type="";
		if(!(listStatus==null))
		{
			if(listStatus.equals("1"))
			{
				 type= searchByFilter();
				 
				 System.out.println("listStatus==null return-------->"+type);
			}
		}

		if (listStatus == null) {
			model.getPendingList(helpDeskProcess, request, isFilter);
			System.out.println("input===");
		} else if (listStatus.equals("PD")) {
			model.getPendingList(helpDeskProcess, request, isFilter);
			System.out.println("input===");
		} else if (listStatus.equals("RD")) {

			model.getResolvedList(helpDeskProcess, request, isFilter);
			System.out.println("input===");
			model.terminate();
		} else {
			type= retrieveDetails();
			System.out.println("listStatus return"+type);
		}
		TreeMap<String,String> map = model.getStatusMap();
		helpDeskProcess.setStatusMap(map);
		return INPUT;
	}

	public String getResolvedList() {
		boolean isFilter = false;
		try {
			HelpDeskProcessModel model = new HelpDeskProcessModel();
			model.initiate(context, session);
			model.getResolvedList(helpDeskProcess, request, isFilter);
			System.out.println("input===");
			TreeMap<String,String> map = model.getStatusMap();
			helpDeskProcess.setStatusMap(map);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return INPUT;
	}

	public String f9AssignTo() throws Exception {

		String query1 = "SELECT DEPT_CODE FROM HELPDESK_DEPT WHERE DEPT_HEAD="+helpDeskProcess.getUserEmpId();
		HelpDeskProcessModel model=new HelpDeskProcessModel();
		model.initiate(context, session);
		Object[][] result = model.getSqlModel().getSingleResult(query1);
		String query="";
		if (result != null && result.length > 0) {
			
			 query = "SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, TECH_CODE "
				+ " FROM HELPDESK_TECHNICIANS "
				+ " INNER JOIN HRMS_EMP_OFFC ON EMP_ID=TECH_CODE "
				+ " WHERE MANAGER_CODE IN( SELECT DISTINCT MANAGER_CODE FROM HELPDESK_MGRREPORTING_HDR WHERE DEPT_CODE="+String.valueOf(result[0][0])+")"
				+ " UNION ALL "
				+ " SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, EMP_ID "
				+ " FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_ID IN("
				+"SELECT DISTINCT MANAGER_CODE FROM HELPDESK_MGRREPORTING_HDR WHERE DEPT_CODE="+String.valueOf(result[0][0])+")"+"OR EMP_ID="+ helpDeskProcess.getUserEmpId();
		}else{
			 query = "SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, TECH_CODE "
				+ " FROM HELPDESK_TECHNICIANS "
				+ " INNER JOIN HRMS_EMP_OFFC ON EMP_ID=TECH_CODE "
				+ " WHERE MANAGER_CODE="+helpDeskProcess.getUserEmpId()
				+ " UNION ALL "
				+ " SELECT EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME NAME, EMP_ID "
				+ " FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_ID="+helpDeskProcess.getUserEmpId();
		}
		String[] headers = { getMessage("employee.id"), getMessage("employee") };

		String[] headerWidth = { "20", "80" };

		String[] fieldNames = { "assignToId", "assignToName", "assignToCode" };
		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		model.terminate();
	
		return "f9page";
	}

	public String showForAssignment() {
		HelpDeskProcessModel model = new HelpDeskProcessModel();
		model.initiate(context, session);
		model.showForAssignment(helpDeskProcess);
		System.out.println("assetScreen===");
		model.terminate();
		return "assetScreen";
	}

	/*
	 * select Inventory for assignment method: f9actionInventory();
	 * 
	 */
	public String f9actionInventory() throws Exception {
		String invCode = "";

		/*
		 * filter the Inventory code from selection which is already present in
		 * the list
		 * 
		 * 
		 */
		try {
			for (int i = 1; i <= Integer.parseInt(helpDeskProcess
					.getTableLength()); i++)
				invCode += "'" + request.getParameter("inventoryCode" + i)
						+ "',";
			invCode = (invCode.substring(0, invCode.length() - 1)).replaceAll(
					"''", "' '");
		} catch (Exception e) {
			invCode = "' '";
		}
		AssetEmployeeModel model = new AssetEmployeeModel();
		model.initiate(context, session); /*
											 * get the warehouse code for which
											 * logged in person is responsible
											 * method: getWarehouseCodes
											 */
		logger.info("invcode in the list ===" + invCode);
		logger.info("asset category code in f9actionInventory==="
				+ helpDeskProcess.getHiddenCategoryCode());
		logger.info("asset SubType code in f9actionInventory==="
				+ helpDeskProcess.getHiddenSubTypeCode());

		String query = "SELECT HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE,HRMS_ASSET_MASTER_DTL.ASSET_INVENTORY_CODE,HRMS_ASSET_MASTER_DTL.ASSET_AVAILABLE FROM HRMS_ASSET_MASTER_DTL  "
				+ " INNER JOIN HRMS_ASSET_MASTER ON (HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE)"
				+ " WHERE ASSET_ASSISGN_FLAG ='U' AND ASSET_CATEGORY_CODE ="
				+ helpDeskProcess.getHiddenCategoryCode()
				+ " AND HRMS_ASSET_MASTER_DTL.ASSET_INVENTORY_CODE NOT IN ("
				+ invCode
				+ ") AND ASSET_WAREHOUSE_CODE ="
				+ helpDeskProcess.getEmpWarehouseCode()
				+ " AND ASSET_SUBTYPE_CODE ="
				+ helpDeskProcess.getHiddenSubTypeCode();

		String[] headers = { getMessage("acode"),
				getMessage("asset.Inventary"), getMessage("asset.QtyAvailable") };

		String[] headerWidth = { "20", "60", "20" };

		String[] fieldNames = { "assetMasterCode" + helpDeskProcess.getRowId(),
				"inventoryCode" + helpDeskProcess.getRowId(),
				"quantityAvailable" + helpDeskProcess.getRowId() };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String processRequest() {
		
		String source = request.getParameter("src");
		helpDeskProcess.setSrc(source);
		System.out.println("source------------------>>>>>>>>>>>>>>"+source);
		HelpDeskProcessModel model = new HelpDeskProcessModel();
		model.initiate(context, session);
		boolean result = model.processRequest(helpDeskProcess);
		if (result) {
			addActionMessage("Request processed successfully.");
		}
		if (helpDeskProcess.getSrc().equals("mymessages")) {
			return "mymessages";
		} else if (helpDeskProcess.getSrc().equals("myservices")) {
			return "serviceJsp";
		} else {
			return input();
		}
	}

	public String searchByFilter() {
		boolean isFilter = true;
		try {
			logger.info("############# IN SEARCH BY FILTER ###############");
			String src= request.getParameter("src");
			helpDeskProcess.setSrc(src);
			HelpDeskProcessModel model = new HelpDeskProcessModel();
			model.initiate(context, session);
			
				if (helpDeskProcess.getFilterByStatus().equals("O")
						|| helpDeskProcess.getFilterByStatus().equals("I")) {
					model.getPendingList(helpDeskProcess, request, isFilter);
				} else if (helpDeskProcess.getFilterByStatus().equals("R")) {
					model.getResolvedList(helpDeskProcess, request, isFilter);
				} 
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	
	}
	public String f9department(){
		try {
			String query = " SELECT NVL(HELPDESK_DEPT.DEPT_NAME,' '), HELPDESK_DEPT.DEPT_CODE FROM HELPDESK_DEPT "
				+ " WHERE  HELPDESK_DEPT.IS_ACTIVE='Y' "
				+ " ORDER BY  HELPDESK_DEPT.DEPT_CODE";
			String[] headers = { getMessage("department") };
			String[] headerWidth = { "100" };
			String[] fieldNames = { "filterByDept", "filterByDeptId" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String f9requestId() {
		try {
			String query = " SELECT REQUEST_TOKEN, REQUEST_ID FROM HELPDESK_REQUEST_HDR ORDER BY REQUEST_ID ";
				
			String[] headers = { "Request Id" };
			String[] headerWidth = { "100" };
			String[] fieldNames = { "filterByReqToken", "filterById"  };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	public String f9category() {
		try {
		String query = " SELECT NVL(REQUEST_TYPE_NAME,' '), REQUEST_TYPE_ID FROM HELPDESK_REQUEST_TYPE ORDER BY REQUEST_TYPE_ID";
		String[] headers = { "Request Type"};
		String[] headerwidth = { "100" };

		String fieldNames[] = { "filterByCat", "filterByCatId" };

		int[] columnIndex = { 0, 1 };

		String submitFlage = "false";

		String submitToMethod = "";
		setF9Window(query, headers, headerwidth, fieldNames, columnIndex,
				submitFlage, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	public String f9employee(){
		try {
			String query = "SELECT NVL(EMP_TOKEN,' '), EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME NAME,"
					+ " HRMS_EMP_OFFC.EMP_ID "
					+ " FROM HRMS_EMP_OFFC  "
					+ " WHERE EMP_STATUS ='S' ORDER BY EMP_ID";
			String[] headers = { getMessage("employee.id"),
					getMessage("employee") };
			String[] headerWidth = { "20", "80" };
			String[] fieldNames = { "filterByEmpToken", "filterByEmp",
					"filterByEmpId" };
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

	/**
	 *@Nilesh Dhandare.
	 * Method : genReport().
	 * Purpose : To generate Report.
	 * @return String.
	 */
	public String genReport()
	{
		System.out.println("inside genReport------");
		
		HelpDeskProcessModel model;
		try {
			model = new HelpDeskProcessModel();
			model.initiate(context, session);
			String appCode = helpDeskProcess.getRequestId();
			System.out.println("appCode --------------- "+ appCode);
			model.genReport(helpDeskProcess, request, response,appCode);
			model.terminate();
		} catch (RuntimeException e) {
		
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
}
