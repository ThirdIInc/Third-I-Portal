package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.AccountCategoryMaster;
import org.paradyne.model.admin.master.AccountCategoryModel;
import org.paradyne.model.admin.master.DivisionModel;
import org.paradyne.model.admin.master.OnsitePostingModel;

import org.struts.lib.ParaActionSupport;
/**
 * 
 * @author aa0650( Dilip Kumar)
 * date:21/09/2009
 *
 */
public class AccountCategoryMasterAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AccountCategoryMasterAction.class);
	AccountCategoryMaster accountCategory;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		accountCategory=new AccountCategoryMaster();
		accountCategory.setMenuCode(942);
		
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return accountCategory;
	}

	public String input() throws Exception {
		accountCategory.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	/**to display the save mode( next mode)
	 * 
	 */ 
		public String addNew() {
			try {
				getNavigationPanel(2);
				return "accountData";
			} catch(Exception e) {
				logger.error("Exception in addNew in action:" + e);
				return null;
			}
		}
		
		/**
		 *  to cancel the all the  mode 
		 *   
		 * @return
		 */
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
		/**
		 * called on load to set the list
		 */
		public void prepare_withLoginProfileDetails() throws Exception {
			AccountCategoryModel model = new AccountCategoryModel();
			model.initiate(context, session);

			model.listData(accountCategory, request);
			
			model.terminate();
		}
		
		/**
		 * To save the record
		 * @return String
		 * @throws Exception
		 */
		public String save() throws Exception {
			AccountCategoryModel model = new AccountCategoryModel();
			model.initiate(context, session);
			
			boolean result;

			if(accountCategory.getAccountID().equals("")) {
				result = model.addListData(accountCategory);
				if(result) {
					addActionMessage(getMessage("save"));
					getNavigationPanel(3);
					return "accountData";
				} else {
					addActionMessage(getMessage("duplicate"));
					reset();
					getNavigationPanel(1);
					return "success";
				}// end of else
			} else {
				result = model.modListData(accountCategory);
				model.terminate();
				if(result) {
					addActionMessage(getMessage("update"));
					getNavigationPanel(3);
					return "accountData";
				} else {
					addActionMessage(getMessage("duplicate"));
					reset();// new added
					getNavigationPanel(1);
					return "success";
				}// end of else
			}// end of else
			
			
		}

		/**
		 * To reset the fields
		 * @return String
		 * @throws Exception
		 */
		public String reset() throws Exception {
			try {
				accountCategory.setAccountID("");
				accountCategory.setAccountName("");
				accountCategory.setAccountAbbreviation("");
				getNavigationPanel(2);
			} catch(Exception e) {
				logger.error("Error in reset" + e);
			}
			return "accountData";
		}

		 
		/**
		 * To delete any record
		 * @return String
		 * @throws Exception
		 */
		public String delete() throws Exception {
			AccountCategoryModel model = new AccountCategoryModel();
			model.initiate(context, session);
			
			boolean result = model.deleteListData(accountCategory);
			model.listData(accountCategory, request);
			
			model.terminate();

			if(result) {
				addActionMessage(getMessage("delete"));

			} else {
				addActionMessage(getMessage("del.error"));
			}// end of else
			getNavigationPanel(1);
			accountCategory.setAccountID("");
			accountCategory.setAccountName("");
			accountCategory.setAccountAbbreviation("");
			accountCategory.setIsActive("");
			return "success";
		}

		/**
		 * To delete one or more records from the list
		 * @return String
		 * @throws Exception
		 */
		public String delete1() throws Exception {
			String code[] = request.getParameterValues("hdeleteCode");

			AccountCategoryModel model = new AccountCategoryModel();

			model.initiate(context, session);
			boolean result = model.deleteAccount(accountCategory, code);
			
			if(result) {
				addActionMessage(getMessage("delete"));
				reset();
			} else {
				addActionMessage(getMessage("multiple.del.error"));
			}// end of else

			model.listData(accountCategory, request);
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
			AccountCategoryModel model = new AccountCategoryModel();

			model.initiate(context, session);
			accountCategory.setAccountID(accountCategory.getHiddencode());
			model.calforedit(accountCategory);
			data();
			
			model.listData(accountCategory, request);
			getNavigationPanel(3);
			accountCategory.setEnableAll("N");
			model.terminate();
			return "accountData";
		}

		/**
		 * To set the page according to the page numbers
		 * @return String
		 * @throws Exception
		 */
		public String callPage() throws Exception {
			AccountCategoryModel model = new AccountCategoryModel();

			model.initiate(context, session);
			
			model.listData(accountCategory, request);
			getNavigationPanel(1);
			model.terminate();
			return SUCCESS;
		}

		

		public AccountCategoryMaster getAccountCategory() {
			return accountCategory;
		}

		public void setAccountCategory(AccountCategoryMaster accountCategory) {
			this.accountCategory = accountCategory;
		}

		/**
		 * following function is called to get the Pdf report for list of  Onsite Posting Master  records
		 * 
		 */
		public String report(){
			getNavigationPanel(3);
			AccountCategoryModel model= new AccountCategoryModel();
			model.initiate(context,session);
			String[]label={"Sr.No",getMessage("account.name"),getMessage("account.abbriviation"),getMessage("is.active")};
			model.generateReport(accountCategory,response,label);
			model.terminate();
			return null;
		}
		
		/**
		 * To set the fields after search
		 * @return String
		 * @throws Exception
		 */
		public String data() throws Exception {
			AccountCategoryModel model= new AccountCategoryModel();
			model.initiate(context, session);
			
			boolean b = model.data(accountCategory);
			getNavigationPanel(3);
			model.terminate();
			return "accountData";
		}

		public String f9action() throws Exception {
			/**
			 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED OUTPUT
			 */
		String query = " 	SELECT  ACCOUNT_CATEGORY_NAME,ACCOUNT_CATEGORY_ABBR,DECODE(IS_ACTIVE,'Y','YES','N','NO','NO'),ACCOUNT_CATEGORY_ID from HRMS_ACCOUNTING_CATEGORY";


			/**
			 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
			 */
			String[] headers = { getMessage("account.name"), getMessage("account.abbriviation"), getMessage("is.active")};

			String[] headerWidth = { "50", "30", "20"};

			/**
			 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT
			 * FLAG IS 'false' -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN INDEX. NOTE: LENGHT OF COLUMN
			 * INDEX MUST BE SAME AS THE LENGTH OF FIELDNAMES
			 */
			String[] fieldNames = {"accountName","accountAbbreviation","isActive", "accountID"};

			/**
			 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES
			 * NEED TO BE SHOWN IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3} NOTE: COLUMN NUMBERS STARTS WITH 0
			 */
			int[] columnIndex = {0,1,2,3};

			/**
			 * WHEN SET TO 'true' WILL SUBMIT THE FORM
			 */
			String submitFlag = "true";

			/**
			 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION:
			 * <NAME OF ACTION>_<METHOD TO CALL>.action
			 */
			String submitToMethod = "AccountCategoryMaster_data.action";

			/**
			 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
			 */
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		}

		
		
		
}
