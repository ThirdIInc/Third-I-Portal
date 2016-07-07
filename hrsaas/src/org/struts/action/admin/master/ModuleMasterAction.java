package org.struts.action.admin.master;
/*
 * @author:saipavan voleti.
 * Dt:13/8/2009
 */

import org.paradyne.bean.admin.master.ModuleMaster;
import org.paradyne.model.admin.master.ModuleMasterModel;
import org.struts.lib.ParaActionSupport;


public class ModuleMasterAction extends ParaActionSupport {



	ModuleMaster bean;
		static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModuleMasterAction.class);
		public void prepare_local() throws Exception {
			
			bean=new ModuleMaster();
			bean.setMenuCode(929);
			// TODO Auto-generated method stub

		}

		public Object getModel() {
			// TODO Auto-generated method stub
			return bean;
		}

		public ModuleMaster getBean() {
			return bean;
		}

		public void setBean(ModuleMaster bean) {
			this.bean = bean;
		}
		/* (non-Javadoc)
		 * @see org.struts.lib.ParaActionSupport#prepare_withLoginProfileDetails()
		 * setting the all the records in a list at the time of loading the form.
		 */
		@Override
		public void prepare_withLoginProfileDetails() throws Exception {
			ModuleMasterModel model=new ModuleMasterModel();
			model.initiate(context,session);
		    model.Data(bean, request);			
		    model.setModulenames(bean);
			model.terminate();
			
			
		}
		
		/**
		 * @return String after adding/modifying the record.
		 */
		public String save() {
			ModuleMasterModel model=new ModuleMasterModel();
			model.initiate(context, session);
			boolean result=false;
			
			if(!bean.getModuleCode().equals(""))
			{
				result=model.modrecord(bean);
				if(result)
				{
				addActionMessage("Record modified Sucessfully.");
				}else
					//addActionMessage("Record cannot be modified.\n Module Name,Module Type should be unique.");
				addActionMessage("Module Type already exist ,please enter different Module Type");
				
			}else
			{
				result=model.addrecord(bean);
				if(result)
				addActionMessage("Record saved successfully.");
				else
				//addActionMessage("Record cannot be added.\n Module Name,Module Type should be unique.");
					addActionMessage("Module Type already exist ,please enter different Module Type");
				
			}
			
			model.Data(bean, request);
			model.terminate();
		    return reset();
		}

		 /**
		   * @return String after clearing the all the fields.
		   * @throws Exception
		   */
		public String reset() {
			bean.setModuleCode("");
			bean.setModuleName("");
		  //	bean.setStatus("");
			bean.setDescription("");
		   bean.setHiddencode("");
		   bean.setHidAuthflag("");
		   bean.setModuleType("");	
			return "success";
		}
		 /**
		   * @return String after deleting the selected record.
		   * @throws Exception
		   */
	  public String delete() {
		    ModuleMasterModel model=new ModuleMasterModel();
			model.initiate(context, session);
			boolean result=model.deleterecord(bean);
			if(result)
			addActionMessage("Record deleted successfully.");
			else
			//addActionMessage("This record is referenced in other resources.So cannot delete.!");
				addActionMessage("Module Title cannot be delete as Mail Event Master Defined for it.");
				model.Data(bean, request);
		    reset();
			model.terminate();
		    return "success";
		}
	  /**
	   * @return String after setting the record details.
	   * @throws Exception
	   */
	  public String calforedit() throws Exception{
		 
		    ModuleMasterModel model=new ModuleMasterModel();
			model.initiate(context, session);			
			model.callForEdit(bean);			
			model.Data(bean,request);			
			model.terminate();
		  return "success";
	  }
	  /**
	   * @return String after setting the records for appropriate page no.
	   * @throws Exception
	   */
	  public String callPage() throws Exception {
		  ModuleMasterModel model=new ModuleMasterModel();
			model.initiate(context, session);
			
			model.Data(bean, request);
			model.terminate();
		  
		  return "success";
		  
	  }
	  /**
	   * @return String after deleting the checked records.
	   * @throws Exception
	   */
	  public String deleteCheckedRecords()throws Exception {
			String code[]=request.getParameterValues("hdeleteCode");
			
			ModuleMasterModel model=new ModuleMasterModel();
			
			model.initiate(context,session);
			boolean result =model.deleteCheckedRecords(bean,code);
			
				if(result) {
					addActionMessage(getText("delMessage",""));
				} else {
					addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
				}
			bean.setMyPage("1");
			bean.setShow("1");
			model.Data(bean,request);
			model.terminate();
			
			return reset();

		}

	  
	  /**
	 * @return f9page after setting f9 action fields.
	 * @throws Exception
	 */
	public String f9action() throws Exception 
		{
		String query = "SELECT MODULE_CODE,MODULE_NAME,MODULE_TYPE	FROM HRMS_MODULE ORDER BY MODULE_CODE ";		
		
		String[] headers={"Module Code",getMessage("conf1"),getMessage("conf4")};
		String[] headerWidth={"15","35","35"};
		String[] fieldNames={"moduleCode","moduleName","moduleType"};
		int[] columnIndex={0,1,2};
		String submitFlag = "true";
		
		String submitToMethod="ModuleMaster_details.action";
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
		}

	  public String details() {
		  ModuleMasterModel model=new ModuleMasterModel();
		  model.initiate(context,session);
		  model.details(bean);
		  model.terminate();
		 return "success";
		
	}
	  
	  
	  
	}
