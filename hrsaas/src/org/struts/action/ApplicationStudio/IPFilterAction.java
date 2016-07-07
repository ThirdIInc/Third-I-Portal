package org.struts.action.ApplicationStudio;

import java.util.ArrayList;
import java.util.HashMap;
import org.paradyne.bean.ApplicationStudio.IPFilter;
import org.paradyne.model.ApplicationStudio.IPFilterModel;
import org.struts.lib.ParaActionSupport;

public class IPFilterAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(IPFilterAction.class);	
	
	IPFilter ipFilter;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		ipFilter =new IPFilter();
		ipFilter.setMenuCode(737);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return ipFilter;
	}

	public IPFilter getIpFilter() {
		return ipFilter;
	}

	public void setIpFilter(IPFilter ipFilter) {
		this.ipFilter = ipFilter;
	}
	
	/*@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		//getNavigationPanel(1);
	//	ipFilter.setOnLoadFlag(true);
		return INPUT;
	}*/
	
	public String cancelFirst() throws Exception{
		logger.info("in 1st cancel-------");
		//getNavigationPanel(1);
		
		ipFilter.setOnLoadFlag(true);
		reset();
		return SUCCESS;
	}
	
	public String cancelFrth() throws Exception{
		logger.info("Inside Cancel Fourth");
		//getNavigationPanel(1);
		ipFilter.setOnLoadFlag(true);
		reset();
		return "success";
		
	}
	
	
	public void prepare_withLoginProfileDetails() throws Exception {
		IPFilterModel model = new IPFilterModel();
		model.initiate(context, session);
		 show();
		model.terminate();
	}
	
	public String show()
	{

		try {
			//getNavigationPanel(2);
			//ipFilter.setOnLoadFlag(false);
			IPFilterModel model = new IPFilterModel();
			model.initiate(context, session);
			String poolDir = (String) session.getAttribute("session_pool");
			String fileName = getText("data_path") + "\\datafiles\\" + poolDir;
			model.getRecord(fileName, ipFilter, request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in show---------------"+e);
		}
		//ipFilter.setLoadFlag(true);
		return SUCCESS;
	}
	
	public String save()
	{
		try {
			//getNavigationPanel(1);
			IPFilterModel model = new IPFilterModel();
			model.initiate(context, session);
			String poolDir = (String) session.getAttribute("session_pool");
			String fileName = getText("data_path") + "\\datafiles\\" + poolDir
					+ "\\xml\\IPFilter\\ipfilter.xml";
			String fileName1 = getText("data_path") + "\\datafiles\\" + poolDir
					+ "\\xml\\IPFilter\\organisation.xml";
			String brnFile = getText("data_path") + "\\datafiles\\" + poolDir
					+ "\\xml\\IPFilter\\branch.xml";
		boolean result=model.save(ipFilter, fileName, request, fileName1, brnFile);
		if(result)
		{
			addActionMessage(getMessage("save"));
			show();
			//reset();
		}
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in save---------------"+e);
			e.printStackTrace();
		}
		//ipFilter.setOnLoadFlag(true);
		return SUCCESS;
	}
	
	
	public String reset()
	{
		 
		ipFilter.setIpFilterFlag("");
		ipFilter.setOrgFrom("");
		ipFilter.setOrgTo("");
		ipFilter.setBranchCode("");
		ipFilter.setBranchName("");
		ipFilter.setBranchFrom("");
		ipFilter.setBranchTo("");
		ipFilter.setEmpId("");
		ipFilter.setEmpName("");
		
		return SUCCESS;
	}
	
	public String addOrgIpRange()
	{
		
		try {
			//getNavigationPanel(2);
			String[] srNo = request.getParameterValues("srNo");
			String[] fromIpAdd = request.getParameterValues("fromIpAdd");
			String[] toIpAdd = request.getParameterValues("toIpAdd");
			
			IPFilterModel model = new IPFilterModel();
			model.initiate(context, session);
			
			String message = model.addIpRanges(ipFilter, srNo, fromIpAdd, toIpAdd, request);
			if(!message.equals("")) {
				addActionMessage(message);
			}
			
			ipFilter.setOrgFrom("");
			ipFilter.setOrgTo("");
			
			setExceptionData();
			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in addOrgIpRange---------------"+e);
		}
		return SUCCESS;
	}
	
	public String addBranchIpRange()
	{
		IPFilterModel model;
		try {
			String[] srNoBr = request.getParameterValues("srNoBr");
			String[] branchfromIpAdd = request.getParameterValues("branchfromIpAdd");
			String[] branchtoIpAdd = request.getParameterValues("branchtoIpAdd");
			String[] brnCode = request.getParameterValues("brnCode");
			String[] brnName = request.getParameterValues("brnName");
			String[] confChk = request.getParameterValues("pmChkFlag");
			
			model = new IPFilterModel();
			model.initiate(context, session);
			String message = model.addBranchIpRanges(ipFilter, srNoBr, branchfromIpAdd, branchtoIpAdd, brnCode, brnName, request);
			if(!message.equals("")) {
				addActionMessage(message);
			}
			
			ipFilter.setBranchTo("");
			ipFilter.setBranchFrom("");
			ipFilter.setBranchCode("");
			ipFilter.setBranchName("");
			
			setExceptionData();
			model.terminate();
		} catch (Exception e) {
			logger.info("Exception in addBranchIpRange---------------"+e);
		}
		return SUCCESS;
	}
	
	public String addException()
	{
		try {
			//getNavigationPanel(2);
			String[] srNoEmp = request.getParameterValues("srNoEmp");
			String[] eId = request.getParameterValues("eId");
			String[] eName = request.getParameterValues("eName");
			String[] eToken = request.getParameterValues("eToken");
			IPFilterModel model = new IPFilterModel();
			model.initiate(context, session);
			model.addException(ipFilter, srNoEmp, eId, eName, eToken, request);
			ipFilter.setEmpName("");
			ipFilter.setEmpId("");
			ipFilter.setEmpToken("");
			if(ipFilter.getBranchWiseCheck().equals("true"))
			{
				setBrnData();
			}
			if(ipFilter.getOrgWiseCheck().equals("true"))
			{
				setOrgData();
			}
		model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in addException---------------"+e);
		}
		return SUCCESS;
	}
	
	public String deleteOrgIpAdd()
	{
		try {
			//getNavigationPanel(2);
			String[] srNo = request.getParameterValues("srNo");
			String[] fromIpAdd = request.getParameterValues("fromIpAdd");
			String[] toIpAdd = request.getParameterValues("toIpAdd");
			String[] confChk = request.getParameterValues("leaveChk");
			IPFilterModel model = new IPFilterModel();
			model.initiate(context, session);
			model.deleteOrgIp(ipFilter, srNo, fromIpAdd, toIpAdd, confChk,
					request);
			setExceptionData();
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in deleteOrgIpAdd---------------"+e);
		}
		return SUCCESS;
	}
	
	public String deleteBrnIpAdd()
	{
		try {
			//getNavigationPanel(2);
			String[] srNo = request.getParameterValues("srNoBr");
			String[] fromIpAdd = request.getParameterValues("branchfromIpAdd");
			String[] toIpAdd = request.getParameterValues("branchtoIpAdd");
			String[] brnCode = request.getParameterValues("brnCode");
			String[] brnName = request.getParameterValues("brnName");
			String[] confChk = request.getParameterValues("leaveChkBrn");
			IPFilterModel model = new IPFilterModel();
			model.initiate(context, session);
			model.deleteBrnIp(ipFilter, srNo, fromIpAdd, toIpAdd, brnCode,
					brnName, confChk, request);
			setExceptionData();
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in deleteBrnIpAdd---------------"+e);
		}
		return SUCCESS;
	}
	
	public String deleteException()
	{
		try {
			//getNavigationPanel(2);
			String[] srNoEmp = request.getParameterValues("srNoEmp");
			String[] eId = request.getParameterValues("eId");
			String[] eName = request.getParameterValues("eName");
			String[] eToken = request.getParameterValues("eToken");
			String[] confChk = request.getParameterValues("leaveChkEmp");
			
			IPFilterModel model = new IPFilterModel();
			model.initiate(context, session);
			model.delException(ipFilter, srNoEmp, eId, eName, eToken, confChk,
					request);
			setOrgData();
			setBrnData();
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception in deleteException---------------"+e);
		}
		return SUCCESS;
	} 
	
	public void setBrnData()
		{
			try {
				 
				String[] srNoBr = request.getParameterValues("srNoBr");
				String[] branchfromIpAdd = request
						.getParameterValues("branchfromIpAdd");
				String[] branchtoIpAdd = request
						.getParameterValues("branchtoIpAdd");
				String[] brnCode = request.getParameterValues("brnCode");
				String[] brnName = request.getParameterValues("brnName");
				HashMap mapdata = new HashMap();
				ArrayList<Object> list = new ArrayList<Object>();
				if (srNoBr != null) {
					for (int i = 0; i < srNoBr.length; i++) {
						IPFilter bean = new IPFilter();
						bean.setBranchfromIpAdd(branchfromIpAdd[i]);
						bean.setBranchtoIpAdd(branchtoIpAdd[i]);
						bean.setBrnCode(brnCode[i]);
						bean.setBrnName(brnName[i]);
						list.add(bean);
						mapdata.put("" + i, "Q");
					}
				}
				request.setAttribute("dataBranch", mapdata);
				ipFilter.setBranchList(list);
			} catch (Exception e) {
				// TODO: handle exception
				logger.info("Exception in setBrnData---------------"+e);
			}
		}
		
	public void setOrgData()
		{
			try {
				 
				String[] srNo = request.getParameterValues("srNo");
				String[] fromIpAdd = request.getParameterValues("fromIpAdd");
				String[] toIpAdd = request.getParameterValues("toIpAdd");
				HashMap mapdata = new HashMap();
				ArrayList<Object> list = new ArrayList<Object>();
				if (srNo != null) {

					for (int i = 0; i < srNo.length; i++) {
						IPFilter bean = new IPFilter();
						bean.setFromIpAdd(fromIpAdd[i]);
						bean.setToIpAdd(toIpAdd[i]);
						list.add(bean);
						mapdata.put("" + i, "G");
					}
				}
				request.setAttribute("data", mapdata);
				ipFilter.setList(list);
			} catch (Exception e) {
				// TODO: handle exception
				logger.info("Exception in setOrgData---------------"+e);
			}
		}
		
	public void setExceptionData() {
			try {
				String[] srNoEmp = request.getParameterValues("srNoEmp");
				String[] empId = request.getParameterValues("eId");
				String[] empName = request.getParameterValues("eName");
				String[] empToken = request.getParameterValues("eToken");
				HashMap mapdata = new HashMap();
				ArrayList<Object> list = new ArrayList<Object>();
				if (srNoEmp != null) {
					for (int i = 0; i < srNoEmp.length; i++) {
						IPFilter bean = new IPFilter();
						bean.setEId(empId[i]);
						bean.setEName(empName[i]);
						bean.setEToken(empToken[i]);
						list.add(bean);
						mapdata.put("" + i, "B");
					}
				}
				request.setAttribute("dataEmp", mapdata);
				ipFilter.setEmpList(list);
			} catch (Exception e) {
				// TODO: handle exception
				logger.info("Exception in setExceptionData---------------"+e);
			}
		}
		
	/**
	 * Popup window contains list of all branches 
	**/
	/**
	 * @return String f9page
	**/
	public String f9BranchName()
	{
		try
		{
			String query = " SELECT CENTER_NAME, CENTER_ID FROM HRMS_CENTER ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"branchName", "branchCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		} //end of try-catch block
	} //end of f9Branch
	
	public String f9EmployeeName()
	{
		String[] eId = request.getParameterValues("eId");
		String str="0";
		if(eId!=null)
		{
			for (int i = 0; i < eId.length; i++) {
				str+=","+eId[i];
			}	
		}
		System.out.println("value of str-----------------"+str);
		
		try
		{
			String query =  " SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
				+ "	HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID "
				+ "	FROM HRMS_EMP_OFFC ";
			
			query += getprofileQuery(ipFilter);
			 query +=" AND EMP_STATUS='S' AND EMP_ID NOT IN("+str+")";
			query += "	ORDER BY HRMS_EMP_OFFC.EMP_ID";
			
				

			String[] headers = {getMessage("employee.id"),getMessage("employee")};

			String[] headerWidth = {"20","80"};

			String[] fieldNames = {"empToken", "empName","empId"};

			int[] columnIndex = {0, 1,2};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return "";
		} //end of try-catch block
	} //end of f9Branch
}