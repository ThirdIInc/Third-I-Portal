package org.struts.action.admin.master;

import org.paradyne.bean.admin.master.TradeMaster;

import org.paradyne.model.admin.master.TradeModel;

/**
 * @author pranali 
 * Date 23-04-07
 */
public class TradeMasterAction extends org.struts.lib.ParaActionSupport {

	TradeMaster tradeMaster;

	public Object getModel() {

		return tradeMaster;
	}

	public org.paradyne.bean.admin.master.TradeMaster getTradeMaster() {
		return tradeMaster;
	}

	public void setTradeMaster(
			org.paradyne.bean.admin.master.TradeMaster tradeMaster) {

		this.tradeMaster = tradeMaster;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	
	public String input() throws Exception {
		tradeMaster.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public String addNew() {
		try {
			getNavigationPanel(2);
			return "tradeData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
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
	
	public void prepare_local() throws Exception {

		tradeMaster = new TradeMaster();
		logger.info("In prepare");
		tradeMaster.setMenuCode(28);

	}

	/**called on load to set the list**/
	public void prepare_withLoginProfileDetails() throws Exception {
		TradeModel model = new TradeModel();
		model.initiate(context, session);
		model.tradeData(tradeMaster, request);
		model.terminate();
	}

	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {

		TradeModel model = new TradeModel();
		model.initiate(context, session);
		model.tradeData(tradeMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;

	}

	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		TradeModel model = new TradeModel();
		model.initiate(context, session);
		model.calforedit(tradeMaster);
		getRecord();

		model.tradeData(tradeMaster, request);
		getNavigationPanel(3);
		tradeMaster.setEnableAll("N");
		model.terminate();
		return "tradeData";
	}

	/*public String calfordelete()
		{
		TradeModel model = new TradeModel();
		model.initiate(context,session);
			boolean result;
			result = model.calfordelete(tradeMaster);
			if(result){
				addActionMessage(getText("Record  deleted Successfully"));
				tradeMaster.setTradeCode("");
				tradeMaster.setTradeName("");
				tradeMaster.setTradeParentName("");
				tradeMaster.setTradeDesc("");
				tradeMaster.setTradeParentCode("");			
			}
			else{
				addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
			}
			
			model.tradeData(tradeMaster,request);
			model.terminate();
			
		return "success";
		}*/
	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		TradeModel model = new TradeModel();

		model.initiate(context, session);
		boolean result = model.deleteTrade(tradeMaster, code);
		if (result) {
			addActionMessage(getText("delMessage"));
			tradeMaster.setTradeCode("");
			tradeMaster.setTradeName("");
			tradeMaster.setTradeAbbr("");
			tradeMaster.setTradeParentName("");
			tradeMaster.setTradeDesc("");
			tradeMaster.setTradeParentCode("");

		}//end of if
		else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
		}//end of else

		model.tradeData(tradeMaster, request);
		getNavigationPanel(1);
		model.terminate();

		return "success";

	}

	/**
	 * To save the record
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
		TradeModel model = new TradeModel();
		model.initiate(context, session);
		boolean result;
		if (tradeMaster.getTradeCode().equals("")) {
			result = model.addTrade(tradeMaster);
			if (result) {
				addActionMessage(getText("addMessage", ""));
				getNavigationPanel(3);
				return "tradeData";
			} //end of if
			else {
				addActionMessage("Duplicate entry found");
				reset();
				getNavigationPanel(1);
				return "success";
			}//end of else
		}//end of nested if
		else {
			result = model.modTrade(tradeMaster);
			if (result) {
				addActionMessage(getText("modMessage", ""));
				getNavigationPanel(3);
				return "tradeData";
			} //end of if
			else {
				addActionMessage("Duplicate entry found");
				reset();
				getNavigationPanel(3);
				return "tradeData";
			}//end of else
		}//end of else
		/*model.tradeData(tradeMaster, request);
		model.terminate();
		tradeMaster.setTradeCode("");
		tradeMaster.setTradeName("");
		tradeMaster.setTradeParentName("");
		/tradeMaster.setTradeDesc("");
		//tradeMaster.setTradeParentCode("");

		return "success";*/
	}

	/**
	 * To reset the fields
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String reset() throws Exception {
		try {
			tradeMaster.setTradeCode("");
			tradeMaster.setTradeName("");
			tradeMaster.setTradeAbbr("");
			tradeMaster.setTradeParentName("");
			tradeMaster.setTradeDesc("");
			tradeMaster.setTradeParentCode("");
			getNavigationPanel(2);
			//return "success";
		} catch (Exception e) {

			logger.error("Error in reset" + e);
		}
		
		return "tradeData";
		//return "success";
	}

	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String delete() throws Exception {

		TradeModel model = new TradeModel();
		model.initiate(context, session);
		boolean result;
		result = model.deleteTrade(tradeMaster);
		if (result) {
			addActionMessage(getText("delMessage", ""));
			tradeMaster.setTradeCode("");
			tradeMaster.setTradeName("");
			tradeMaster.setTradeAbbr("");
			tradeMaster.setTradeParentName("");
			tradeMaster.setTradeDesc("");
			tradeMaster.setTradeParentCode("");

		} //end of if
		else {
			addActionMessage("Record can't be deleted \n as it is associated with some other record");
		}//end of else
		model.tradeData(tradeMaster, request);
		getNavigationPanel(1);
		model.terminate();
		return "success";
	}

	/*public String report()throws Exception 
	{
		logger.info("in report");
		TradeModel model = new TradeModel();
		logger.info("before initiate");
		model.initiate(context,session);
		logger.info("before gettradeReport");
		model.getTradeReport(tradeMaster);
		logger.info("before terminate");
		model.terminate();	
		return "report";    
	}*/

	/**
	 * To generate report
	 * 
	 * @return null
	 * @throws Exception
	 */
	public String report() throws Exception {
		TradeModel model = new TradeModel();
		model.initiate(context, session);
		String []label={"Sr.No",getMessage("tradename"),getMessage("tradeabbr"),getMessage("tradedescription"),"Trade Parent Code"};
		model.getReport(tradeMaster, request, response, context, session,label);
		model.terminate();
		return null;
	}

	/**
	 * To set the fields after search
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String getRecord() throws Exception {

		TradeModel model = new TradeModel();

		model.initiate(context, session);

		model.getTradeRecord(tradeMaster);
		getNavigationPanel(3);
		model.terminate();
		return "tradeData";
	}

	private boolean isInvalid(String value) {
		logger.info("in isinvalid");
		return (value == null || value.length() == 0);
	}

	public String f9action() throws Exception {

		logger.info("in f9 action");

		String query = "  SELECT HRMS_TRADE.TRADE_NAME,HRMS_TRADE.TRADE_ABBR,T1.TRADE_NAME, HRMS_TRADE.TRADE_CODE FROM HRMS_TRADE "
				+ " LEFT JOIN HRMS_TRADE  T1 ON (T1.TRADE_CODE = HRMS_TRADE .TRADE_PARENT_CODE)"
				+ " ORDER BY HRMS_TRADE.TRADE_NAME ";

		String[] headers = {  getMessage("tradename"), getMessage("tradeabbr"), getMessage("tradeparent") };

		String[] headerWidth = {  "50", "25", "25" };

		String[] fieldNames = { 
				"tradeMaster.tradeName", "tradeMaster.tradeAbbr", "tradeMaster.tradeParentName","tradeMaster.tradeCode" };

		int[] columnIndex = { 0, 1, 2, 3 };

		String submitFlag = "true";

		String submitToMethod = "TradeMaster_getRecord.action";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

	public String f9Tradaction() throws Exception {

		String query = " SELECT TRADE_NAME , TRADE_CODE FROM HRMS_TRADE  ORDER BY TRADE_NAME";

		String[] headers = { getMessage("tradeparent") };

		String[] headerWidth = {  "100" };

		String[] fieldNames = {	"tradeMaster.tradeParentName", "tradeMaster.tradeParentCode" };

		int[] columnIndex = { 0, 1 };

		String submitFlag = "false";

		String submitToMethod = " ";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}

}
