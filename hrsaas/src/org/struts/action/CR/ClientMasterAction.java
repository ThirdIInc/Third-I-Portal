package org.struts.action.CR;

import org.apache.log4j.Logger;
import org.paradyne.bean.CR.ClientMaster;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.CR.AccountMasterModel;
import org.paradyne.model.CR.ClientMasterModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author vivek.wadhwani
 *
 */
public class ClientMasterAction extends ParaActionSupport
{
  ClientMaster bean = null;

  static Logger logger = Logger.getLogger(ParaActionSupport.class);

  public ClientMaster getBean()
  {
    return this.bean;
  }

  public void setTm(ClientMaster bean) {
    this.bean = bean;
  }

  public Object getModel()
  {
    return this.bean;
  }

 /**
  * The first method called when form is loaded
  */
  public String input()
    throws Exception
  {
	 
	    this.bean.setEnableAll("N");
	    getNavigationPanel(1);
	 
	    return SUCCESS;
  }

  /** Adds the new data  
   * 
   * @return
   */
  public String addNew() {
    try {
    	this.bean.setHiddencode("");
      getNavigationPanel(2);
      return "clientData";
    } catch (Exception e) {
      logger.error("Exception in addNew in action:" + e);
    }return null;
  }


  /** Resets  the data from the screen
 * @return String
 */
public String cancel()
  {
    try {
    	ClientMasterModel model = new ClientMasterModel();
      model.initiate(this.context, this.session);
      String requestID = this.request.getParameter("clientUserNo");
      model.callForBusinessUser(this.bean,this.request);
      
      reset();
      getNavigationPanel(1);
      model.terminate();
      return input();
    } catch (Exception e) {
      logger.error("Exception in cancel in action:" + e);
    }return null;
  }

  public void prepare_local() throws Exception {
    this.bean = new ClientMaster();
    this.bean.setMenuCode(5049);//5049 menu code is set
  }

  public void prepare_withLoginProfileDetails()
    throws Exception
  {
	  ClientMasterModel model = new ClientMasterModel();
	    model.initiate(this.context, this.session);
	   // String requestID = this.request.getParameter("clientUserNo");
	    model.callForBusinessUser(this.bean,this.request);
	    model.terminate();
  }

  /**
 * @return For Pagination
 * @throws Exception
 */
public String callPage()
    throws Exception
  {
	  ClientMasterModel model = new ClientMasterModel();
    model.initiate(this.context, this.session);
    String requestID = this.request.getParameter("clientUserNo");
    model.callForBusinessUser(this.bean,this.request);
    getNavigationPanel(1);
    model.terminate();
    return "success";
  }


  /** Resets  the data from the screen
 * @return String- empty data fields
 * @throws Exception
 */
public String reset()
    throws Exception
  {
    try
    {
      this.bean.setFirstName("");
      this.bean.setLastName("");
      this.bean.setEmailClientAddress("");
      this.bean.setCellNumber("");
      this.bean.setPassword("");
      this.bean.setHiddenCheckBoxFlag("");
      getNavigationPanel(2);
    }
    catch (Exception e) {
      logger.error("Error in reset" + e);
    }
    return "clientData";
  }


  

  /** Displaying the records in list
 * @return String
 * @throws Exception
 */
public String callForBusinessUser()
    throws Exception
  {
    ClientMasterModel model = new ClientMasterModel();
    model.initiate(this.context, this.session);
    String requestID = this.request.getParameter("accountId");
    model.callForBusinessUser(this.bean,this.request);
    this.bean.setEnableAll("Y");
    model.terminate();
    return "businessUserDtl";
  }

 
  
  /**
	 * To delete any record
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		ClientMasterModel model = new ClientMasterModel();
		model.initiate(context, session);
		
		boolean result = false;
		
		
		if(!this.bean.getHiddencode().equals("")) {
			result = model.delete(this.bean);
		}
		model.callForBusinessUser(this.bean, request);
		
		model.terminate();
		
		if(result) {
			
			addActionMessage(getMessage("delete"));
			this.bean.setFirstName("");
			this.bean.setLastName("");
			this.bean.setCellNumber("");
			this.bean.setEmailClientAddress("");
			this.bean.setPassword("");
			this.bean.setIsClientActive("");
			
			
			
		} else {
			addActionMessage(getMessage("del.error"));
		}// end of else
		getNavigationPanel(1);
		return "success";
	}
  
  
  /** Add client user's
 * @return
 */
public String addClientUserLstDtl() {
    ClientMasterModel model = new ClientMasterModel();
    model.initiate(this.context, this.session);

    String email = this.bean.getEmailClientAddress().trim();
    if (this.bean.getHiddencode().equals(""))
    {
      boolean result = model.saveClientUserLstDtl(this.bean, this.request);
      String userId;
      if (result) {
        addActionMessage(getMessage("save"));
        getNavigationPanel(3);
        model.callForBusinessUser(this.bean,this.request);
        
        return "clientData";
      }
      else
      {
        addActionMessage("E-mail ID is already present. Please select new E-mail ID.");
        
        try {
			reset();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return "success";
      }
    }
    else
    {
    	//update method
      System.out.println("UPDATEEEEEE");
      boolean result = model.modClientUserLstDtl(this.bean, this.request);
      model.terminate();
      if(result){
    	  addActionMessage("Records Updated successfully.");
    	 
    	  getNavigationPanel(3);
    	  return "clientData";
      }else{
    	  addActionMessage("Error while updating record");
    	  try {
			reset();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			getNavigationPanel(1);
			return "success";
      }
      
     
      
    }

 }

 

  /** Delete multiple records
 * @return
 * @throws Exception
 */
public String deleteClientUserLstDtl()
    throws Exception
  {
    try
    {
    	
      String code[] = request.getParameterValues("hdeleteCode");
      ClientMasterModel model = new ClientMasterModel();
      model.initiate(this.context, this.session);
     // String clientUserID = this.request.getParameter("ittClientUserNo");
      boolean result = model.deleteClientUserLstDtls(this.bean, code);
      String requestID = this.bean.getClientUserNo();
      model.callForBusinessUser(this.bean,this.request);
      this.bean.setParacode("");
      this.bean.setFirstName("");
      this.bean.setLastName("");
      this.bean.setEmailClientAddress("");
      this.bean.setCellNumber("");
      this.bean.setClientUserNo("");
      this.bean.setIsClientActive("true");
      this.bean.setPassword("");
      
      model.terminate();
      if (result) {
        addActionMessage(getMessage("delete"));
        reset();
      } else {
        addActionMessage(getMessage("no result"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    getNavigationPanel(1);
	return "success";
  }

 

 
  
  /**
	 * To generate report
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {
		ClientMasterModel model = new ClientMasterModel();
		model.initiate(context, session);
		String[]label={"Sr.No",getMessage("first.name"),getMessage("last.name"),getMessage("user.id"),getMessage("cell.number"),getMessage("Is.Active")};
		model.getReport(this.bean, request, response, context, session,label);
		
		model.terminate();
		return null;
	}
  
  
	/** Search function
	 * @return
	 * @throws Exception
	 */
	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT  FIRST_NAME, LAST_NAME, IS_ACTIVE, CRUSER_CODE" +
				" FROM CR_CLIENT_USERS "+
		" WHERE IS_ACTIVE='Y' ORDER BY CRUSER_CODE ";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("first.name"), getMessage("last.name"),getMessage("is.active")};

		String[] headerWidth = { "25", "25","25"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "bean_firstName","bean_lastName","bean_isClientActive","bean_clientUserNo"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1,2,3};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "ClientMaster_clientRecord.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}
	
	

  /** Edit ClientUserListDetail
 * @return String- result name "clientData"
 * @throws Exception
 */
public String editClientUserLstDtl()
    throws Exception
  {
    logger.info("inside edit");
    ClientMasterModel model = new ClientMasterModel();
    model.initiate(this.context, this.session);
   // String clientUserID = this.bean.getParacode();

    model.editClientUserLstDtl(this.bean);
    
    //clientRecord();
    
   // model.callForBusinessUser(this.bean,this.request);
    
    
    getNavigationPanel(3);
    this.bean.setEnableAll("N");
    model.terminate();
    return "clientData";
  }

  
  
	/** Get client records for f9 action
	 * @return Records 
	 * @throws Exception
	 */
	public String clientRecord() throws Exception {
		ClientMasterModel model = new ClientMasterModel();
		model.initiate(context, session);
		model.getClientRecord(this.bean);
		getNavigationPanel(3);
		model.terminate();
		return "clientData";
	}
}