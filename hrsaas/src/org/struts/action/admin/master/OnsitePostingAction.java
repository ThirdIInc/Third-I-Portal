package org.struts.action.admin.master;


import org.paradyne.bean.admin.master.OnsitePosting;
import org.paradyne.model.admin.master.CenterModel;
import org.paradyne.model.admin.master.CheckListModel;
import org.paradyne.model.admin.master.DivisionModel;
import org.paradyne.model.admin.master.OnsitePostingModel;
import org.struts.lib.ParaActionSupport;

/**
 * 
 * @author Dilip Kumar
 * Date:20/09/09
 */
public class OnsitePostingAction extends ParaActionSupport {
	OnsitePosting onsiteposting;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		onsiteposting = new OnsitePosting();
		onsiteposting.setMenuCode(941);
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return onsiteposting;
	}

	public OnsitePosting getOnsiteposting() {
		return onsiteposting;
	}

	public void setOnsiteposting(OnsitePosting onsiteposting) {
		this.onsiteposting = onsiteposting;
	}

	public static org.apache.log4j.Logger getLogger() {
		return logger;
	}

	public static void setLogger(org.apache.log4j.Logger logger) {
		OnsitePostingAction.logger = logger;
	}
	
	public String input() throws Exception {
		onsiteposting.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "postingData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		OnsitePostingModel model= new OnsitePostingModel();
		 model.initiate(context, session);
		
		model.iteratorListData(onsiteposting, request);
		
		model.terminate();
	}
	public String cancel() {
		try {
			reset();
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	public String save() throws Exception{
		OnsitePostingModel model= new OnsitePostingModel();
		 model.initiate(context, session);
		
			String str="";
			boolean result;
			{	
				if(onsiteposting.getOnSiteID().equals("")) {
					
					result = model.saveRecord(onsiteposting);
					if(result) {
						
						addActionMessage(getMessage("save"));
						getNavigationPanel(3);
						str="postingData";
					} else {
						addActionMessage(getMessage("duplicate"));
						reset();
						getNavigationPanel(1);
						str= "success";
					}// end of else
				}
				else {
					result = model.updateRecord(onsiteposting);
					
					if(result) {
						addActionMessage(getMessage("update"));
						getNavigationPanel(3);
						str= "postingData";
					} else {
						addActionMessage(getMessage("duplicate"));
						reset();// new added
						getNavigationPanel(1);
						str= "success";
					}// end of else
			   }
			}
			model.terminate();	
			return str;
	}
	public String reset() throws Exception {
		try {
			onsiteposting.setSiteType("");
			onsiteposting.setSiteCode("");
			onsiteposting.setSiteAbbreviation("");
			onsiteposting.setSiteName("");
			onsiteposting.setSiteLocation("");
			onsiteposting.setSiteAddress3("");
			onsiteposting.setSiteAddress1("");
			onsiteposting.setSiteAddress2("");
			onsiteposting.setPhone("");
			onsiteposting.setEmailId("");
			onsiteposting.setOnSiteID("");
			onsiteposting.setIsActive("");
			getNavigationPanel(2);
		} catch(Exception e) {
			logger.error("Error in reset" + e);
		}
		return "postingData";
	}

	
	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		OnsitePostingModel model= new OnsitePostingModel();
		model.initiate(context, session);
		model.iteratorListData(onsiteposting, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	
	
	
	public String delete() throws Exception {
		OnsitePostingModel model= new OnsitePostingModel();
		model.initiate(context, session);
		
		boolean result = model.deletePosting(onsiteposting);
		model.iteratorListData(onsiteposting, request);
		
		model.terminate();

		if(result) {
			addActionMessage(getMessage("delete"));

		} else {
			addActionMessage(getMessage("del.error"));
		}// end of else
		getNavigationPanel(1);
		onsiteposting.setSiteType("");
		onsiteposting.setSiteCode("");
		onsiteposting.setSiteAbbreviation("");
		onsiteposting.setSiteName("");
		onsiteposting.setSiteLocation("");
		onsiteposting.setSiteAddress3("");
		onsiteposting.setSiteAddress1("");
		onsiteposting.setSiteAddress2("");
		onsiteposting.setPhone("");
		onsiteposting.setEmailId("");
		onsiteposting.setOnSiteID("");
		return "success";
	}
	/**
	 * following function is called to get the Pdf report for list of  Onsite Posting Master  records
	 * 
	 */
	public String report(){
		getNavigationPanel(3);
		OnsitePostingModel model=new OnsitePostingModel();
		model.initiate(context,session);
		String[]label={"Sr.No",getMessage("site.type"),getMessage("site.code"),getMessage("site.abbriviation"),getMessage("site.name")};
		model.generateReport(onsiteposting,response,label);
		model.terminate();
		return null;
	}

	
	
	
	public String delete1() throws Exception {
		
		String code[] = request.getParameterValues("hdeleteCode");
		OnsitePostingModel model= new OnsitePostingModel();

		model.initiate(context, session);
		boolean result = model.deleteSiteType(onsiteposting, code);
		
		if(result) {
			addActionMessage(getMessage("delete"));
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else

		model.iteratorListData(onsiteposting, request);
		getNavigationPanel(1);
		model.terminate();
		
		return "success";
	}
	
	/**
	 * To edit any record in the list by double clicking on it
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		OnsitePostingModel model= new OnsitePostingModel();
		model.initiate(context, session);
		onsiteposting.setOnSiteID(onsiteposting.getHiddencode());
		model.calforedit(onsiteposting);
		sitedata();
		
		model.iteratorListData(onsiteposting, request);
		getNavigationPanel(3);
		onsiteposting.setEnableAll("N");
		model.terminate();
		return "postingData";
	}
	
	/**
	 * To set the fields after search
	 * @return String
	 * @throws Exception
	 */
	public String sitedata() throws Exception {
		OnsitePostingModel model= new OnsitePostingModel();
		model.initiate(context, session);
		
		boolean b = model.sitedata(onsiteposting);
		getNavigationPanel(3);
		model.terminate();
		return "postingData";
	}

	public String f9action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
	String query = " SELECT DECODE(ONSITE_POSTING_TYPE,'C','Client','V','Vender','P','Partner'), ONSITE_POSTING_CODE," 
					+" ONSITE_POSTING_ABBR, ONSITE_POSTING_NAME,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO'),ONSITE_POSTING_ID,LOCATION_NAME," +
					" ONSITE_POSTING_ADDRESS_1, ONSITE_POSTING_ADDRESS_2, ONSITE_POSTING_ADDRESS_3, " +
					" ONSITE_POSTING_PHONE, ONSITE_POSTING_EMAIL, ONSITE_POSTING_TYPE FROM HRMS_ONSITE_POSTING " +
					" LEFT JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_ONSITE_POSTING.ONSITE_POSTING_LOCATION) order by ONSITE_POSTING_ID";


		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("site.type"), getMessage("site.code"), getMessage("site.abbriviation"), getMessage("site.name"), getMessage("is.active")};

		String[] headerWidth = { "20", "20","20", "20", "20"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = {"hiddenSiteType", "siteCode","siteAbbreviation","siteName","isActive", "onSiteID","siteType"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1,2,3,4,5,6};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "OnsitePosting_sitedata.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9location() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
		 */
		String query = " SELECT   LOCATION_NAME,LOCATION_CODE FROM HRMS_LOCATION where LOCATION_LEVEL_CODE=2 ORDER BY LOCATION_NAME";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("site.location")};

		String[] headerWidth = { "100"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
		 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
		 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
		 */
		String[] fieldNames = { "siteLocation","loccationId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
		 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
		 */
		int[] columnIndex = {0, 1};

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 */
		String submitFlag = "false";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
		 * <NAME OF ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED
		 */
		
		onsiteposting.setMasterMenuCode(20);
		
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

		return "f9page";
	}


}

