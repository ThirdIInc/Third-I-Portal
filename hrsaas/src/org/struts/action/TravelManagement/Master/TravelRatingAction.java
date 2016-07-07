package org.struts.action.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.TravelRatingBean;
import org.paradyne.model.TravelManagement.Master.TravelRatingModel;

import org.struts.lib.ParaActionSupport;

public class TravelRatingAction extends ParaActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(TravelRatingAction.class);

	
	TravelRatingBean trBean;
	
	public TravelRatingBean getTrBean() {
		return trBean;
	}

	public void setTrBean(TravelRatingBean trBean) {
		this.trBean = trBean;
	}

	public void prepare_local() throws Exception {
		trBean = new TravelRatingBean();
		trBean.setMenuCode(1143);
	}

	public Object getModel() {
		return trBean;
	}

	/**This input function is get called for displaying Onload List*/
	public String input() throws Exception {

		TravelRatingModel model = new TravelRatingModel();
		model.initiate(context, session);
		
		model.intData(trBean, request);
		
		model.terminate();
		getNavigationPanel(1);
		return "input";
	}
	
	
	
	public String addNew() {
		try {
			
			getNavigationPanel(2);
			return "addnew";
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	
	public void prepare_withLoginProfileDetails() throws Exception {
		TravelRatingModel  model = new TravelRatingModel ();
		model.initiate(context, session);
		model.intData(trBean, request);
		
		model.terminate();
	
	}
	
	
	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {

		TravelRatingModel model = new TravelRatingModel();
		model.initiate(context, session);
		model.intData(trBean, request);
		getNavigationPanel(1);
		model.terminate();
		
		return "input";
	}
	
/**This method is called on save button for saving records*/
	
	public String save() throws Exception {
		try {
			TravelRatingModel model = new TravelRatingModel();
			model.initiate(context, session);
			boolean result;
			if (trBean.getHiddencode().equals("") && !trBean.getHiddencode().equals("null")) {
				result = model.addData(trBean);
				if (result) {
					addActionMessage(getMessage("save"));
				} else {
					addActionMessage(getMessage("duplicate"));
					getNavigationPanel(2);
					reset();// new added
				}
			} else {
				result = model.update(trBean);

				if (result) {
					addActionMessage(getMessage("update"));
					
					 

				} else {
					addActionMessage(getMessage("duplicate"));
					reset();// new added

					 
				}

			}
			
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		
		getNavigationPanel(3);
		trBean.setEnableAll("N");
		return "addnew";

	}
	
/**This method is called on reset button*/
	
	public String reset() throws Exception {
		try {
			trBean.setRatingParameter("");
			trBean.setRatingType("");
			trBean.setHiddencode("");			
			getNavigationPanel(2);
		} catch (Exception e) {
			logger.error("Error in reset" + e);
		}
		return "addnew";
	}
	
	
	public String setRecord() throws Exception {
		try{
			TravelRatingModel model = new TravelRatingModel();
			model.initiate(context, session);
			model.terminate();
			model.calforedit(trBean);
			getNavigationPanel(3);
			
		}catch(Exception e){
			logger.error("Exception - " +e);
		}
		
		getNavigationPanel(3);
		trBean.setEnableAll("N");
		return "addnew";
	}
	
	
	/*This function called when edit button clicked on jsp after record get saved*/ 
	public String edit() throws Exception {
		try {
			TravelRatingModel model = new TravelRatingModel();
			model.initiate(context, session);
			model.calforedit(trBean);
			model.terminate();
			getNavigationPanel(2);
			//cityGrade.setEnableAll("N");
		} catch (Exception e) {
			logger.error("Error in edit" + e);
		}
		return "addnew";
	}
	
	/**
	 * To edit any record in the list by double clicking on it
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		try {
			TravelRatingModel model = new TravelRatingModel();
			model.initiate(context, session);
			model.calforedit(trBean);
			getNavigationPanel(3);
			trBean.setEnableAll("N");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addnew";
	}
	
	/**This method is called on back button*/
	public String cancel() {
		try {
			
			getNavigationPanel(1);
			
			reset();
			
			prepare_withLoginProfileDetails();
			getNavigationPanel(1);
			
			return "input";
		}
		catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	
	/**following function is called when delete button is clicked in the jsp page*/
	
	public String deleteChkRecords() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		TravelRatingModel model = new TravelRatingModel();

		model.initiate(context, session);
		boolean result = model.deleteCheckedRecords(trBean, code);

		if (result) {
			addActionMessage(getText("delMessage", ""));
			reset();
		} else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}

		model.intData(trBean, request);
		getNavigationPanel(1);
		model.terminate();

		return "input";

	}
	
	/**
	 * To delete any record
	 * @return String
	 * @throws Exception
	 */
	public String delete() throws Exception {
		TravelRatingModel model = new TravelRatingModel();
		model.initiate(context, session);
		boolean result = model.delRecord(trBean);
		model.intData(trBean, request);
		model.terminate();

		if(result) {
			addActionMessage(getMessage("delete"));
			
		} else {
			addActionMessage(getMessage("no result"));
		}// end of else
		getNavigationPanel(1);
		trBean.setRatingParameter("");
		trBean.setRatingType("");
		trBean.setHiddencode("");
		return "input";
	}

	
	
	/**This f9action is for Search pop up window */
	public String f9action() throws Exception {
	
		String query = "  SELECT RATING_NAME, DECODE(RATING_TYPE,'H','HOTEL','D','DESK'), RATING_ID FROM TMS_RATING_PARAM ORDER BY RATING_NAME";
		String[] headers = { getMessage("ratingparameter"),getMessage("ratingtype")};
		String[] headerWidth = { "50", "50" };
		String[] fieldNames = { "ratingParameter", "ratingType","hiddencode" };
		int[] columnIndex = { 0, 1, 2};
		String submitFlag = "true";
		String submitToMethod = "TravelRating_setRecord.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
		
	}
	

}
