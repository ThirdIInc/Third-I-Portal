package org.struts.action.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.MyDownloadsBean;
import org.paradyne.model.ApplicationStudio.MyDownloadsModel;
import org.struts.lib.ParaActionSupport;

public class MyDownloadsAction extends ParaActionSupport {

	private static final long serialVersionUID = 1L;
	MyDownloadsBean bean;
	String poolDir = "";
	String fileName = "";
	
	public void prepare_local() throws Exception {
		bean=new MyDownloadsBean();
		bean.setMenuCode(2296);
	}

	public Object getModel() {
		return bean;
	}
	
	public String reset() {
		try {
			bean.setCategoryName("");
			bean.setSubCategoryName("");
			bean.setUploadDocument("");
			bean.setLinkName("");
			bean.setLink("");
			bean.setHiddenCode("");
			bean.setCheckFlag("false");
			bean.setCheckActive("false");
			bean.setDownloadDivCode("");
			bean.setDownloadDivName("");
			bean.setCategoryNameDropdown("");
			bean.setSubCategoryNameDropDown("");
			input();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";

	}
	
	public String saveDownloads() {
		try {
			MyDownloadsModel model = new MyDownloadsModel();
			model.initiate(context, session);
			int value = model.saveDownloads(bean,"save");
			reset();
			bean.setCheckFlag("true");
			
			if (value == 0){
				addActionMessage("Duplicate Entry Found. Record cant be Added!");
			}else if (value == 1){
				addActionMessage("Record Saved Successfully");reset();
			}else{
				addActionMessage("Record Modified Successfully");
			}
			bean.setCheckActive("");
			model.listDetails(bean);
			model.terminate();
			reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "download";
	}
	public String showDownloads() {
		try {
			MyDownloadsModel model = new MyDownloadsModel();
			model.initiate(context, session);
			model.saveDownloads(bean, "show");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "download";
	}

	public String f9applDiv(){		
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
	
	public String edit() {
		try {
			String editCode = request.getParameter("editCode");
			bean.setHiddenCode(editCode);
			MyDownloadsModel model = new MyDownloadsModel();
			model.initiate(context, session);
			model.edit(bean, editCode);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "download";
	}
	
	public String delete() {
		MyDownloadsModel model = new MyDownloadsModel();
		model.initiate(context, session);
		
		boolean result = model.delete(bean);
		if (result) {
			addActionMessage("Record Deleted Successfully!");
			bean.setCategoryName("");
			bean.setSubCategoryName("");
			bean.setUploadDocument("");
			bean.setLinkName("");
			bean.setLink("");
			bean.setHiddenCode("");
			bean.setCategoryNameDropdown("");
			bean.setSubCategoryNameDropDown("");
		}
		else {
			addActionMessage("Record Can't be Deleted!");
		}
		input();
		model.terminate();
		return "download";
	}
	public String input() {
		try {
			MyDownloadsModel model = new MyDownloadsModel();
			model.initiate(context, session);		
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "\\" + poolName;
			}
			String fileDataPath = getText("data_path") + "\\upload" + poolName + "\\myDownload\\";
			System.out.println("data path : " + fileDataPath);
			bean.setDataPath(fileDataPath);
			model.listDetails(bean);
			model.setCategoryName(bean);
			model.setSubCategoryName(bean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	public MyDownloadsBean getBean() {
		return bean;
	}

	public void setBean(MyDownloadsBean bean) {
		this.bean = bean;
	}

	public String getPoolDir() {
		return poolDir;
	}

	public void setPoolDir(String poolDir) {
		this.poolDir = poolDir;
	}
}
