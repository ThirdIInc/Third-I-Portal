/**
 * 
 */
package org.struts.action.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.FoodType;
import org.paradyne.model.TravelManagement.Master.FoodTypeModel;
import org.paradyne.model.TravelManagement.Master.HotelTypeModel;

import org.struts.lib.ParaActionSupport;

/**
 * @author aa0651
 *
 */
public class FoodTypeAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	FoodType food;

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		food = new FoodType();
		food.setMenuCode(776);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return food;
	}

	public FoodType getFood() {
		return food;
	}

	public void setFood(FoodType food) {
		this.food = food;
	}
	
	public String input() throws Exception{
		//Default Method with default modeCode(1)		
		getNavigationPanel(1);
		FoodTypeModel model = new FoodTypeModel();	
		model.initiate(context, session);
		food.setOnLoadFlag(true);
		callPage();
		model.terminate();
		food.setCallpageflag("input");
		return "input";
	}
	
	public String addNew() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		FoodTypeModel model = new FoodTypeModel();		
		model.initiate(context,session);
		callPage();
		reset();
		food.setLoadFlag(true);
		food.setFlag(true);	
		food.setEditableflag(true);
		model.terminate();
		food.setCallpageflag("success");
		return "success";
		
	}
	
	public String edit() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		FoodTypeModel model = new FoodTypeModel();
		model.initiate(context, session);
		model.getFoodTypeSearch(food);
		callPage();
		
		food.setEditFlag(true);
		
		
		food.setModFlag(true);
		food.setAddFlag(false);
		food.setOnLoadFlag(false);
		//added 
		food.setEditableflag(true);
		food.setCancelflag(true);
		model.terminate();
		return "success";
		
		
	}
	
	
	
	public String reset() throws Exception{
		food.setTypeId("");
		food.setTypeName("");
		food.setDescription("");
		food.setStatus("");
		return SUCCESS;
	}
	
	public String cancelThrd() throws Exception{
		logger.info("Inside Cancel Third");
			getNavigationPanel(3);
			FoodTypeModel model = new FoodTypeModel();	
			model.initiate(context, session);
			
			callPage();
			model.getFoodType(food);
			food.setSaveFlag(true);
			food.setModFlag(false);
			model.terminate();
			return "success";
			
		}
		
		public String cancelSec() throws Exception{
			getNavigationPanel(1);
			FoodTypeModel model = new FoodTypeModel();	
			model.initiate(context, session);
			callPage();
			food.setOnLoadFlag(true);
			food.setSaveFlag(true);
			model.terminate();
			return "success";
			
		}
		
		/*public String cancelFirst() throws Exception{
			getNavigationPanel(1);
			callPage();
			food.setOnLoadFlag(true);
			reset();
			return "success";
		}*/
		//added by debjani
		public String cancelFirst() throws Exception{
		logger.info("=========bean.getCancelFlg()========="
				+ food.isCancelflag());
		String str;
		FoodTypeModel model = new FoodTypeModel();	
		model.initiate(context, session);
		if (food.isCancelflag()) {
			getNavigationPanel(3);
			System.out.println("inside if method-----");
			model.getFoodType(food);
			model.reqData(food, request);
			food.setOnLoadFlag(true);
			str = "success";
		} else {
			getNavigationPanel(1);
			model.reqData(food, request);
			reset();
			food.setOnLoadFlag(false);
			str = "input";
		}
		food.setCancelflag(false);
		food.setCallpageflag("input");
		food.setEditableflag(false);
		model.terminate();
		return str;
		}
		public String cancelFrth() throws Exception{
			logger.info("Inside Cancel Fourth");
			getNavigationPanel(1);
			FoodTypeModel model = new FoodTypeModel();	
			model.initiate(context, session);
			callPage();
			reset();
			food.setOnLoadFlag(true);
			model.terminate();
			return "success";
			
		}
		
		
		/**
		 * following function is called when add new record is clicked in the jsp page
		 * @return
		 */
		public String save() throws Exception{
			//Default Method with Edit modeCode(3)
			getNavigationPanel(3);
			FoodTypeModel model = new FoodTypeModel();	
			model.initiate(context, session);
			
			logger.info("+++++++++++food.getTypeId()==============="+food.getTypeId());
			if(food.getTypeId().equals("")){
				logger.info("***********==Inside Save=========");
				boolean result=model.addFoodType(food);
				if(result){
				addActionMessage(getMessage("save"));
				food.setCancelflag(false);
				food.setEditableflag(false);
				food.setNoflag(false);
				callPage();
				}else{
				addActionMessage(getMessage("duplicate"));
				getNavigationPanel(2);
				}
			}
			else
			{
				logger.info("***********==Inside Update=========");
				boolean result=model.modFoodType(food);
				if(result){
					addActionMessage(getMessage("update"));
					food.setCancelflag(false);
					food.setEditableflag(false);
					food.setNoflag(false);
					callPage();
				}else{
					addActionMessage(getMessage("duplicate"));
					getNavigationPanel(2);
					food.setLoadFlag(true);
					food.setFlag(true);
				}
			}
			model.reqData(food,request);			
			
			food.setLoadFlag(false);
			food.setAddFlag(true);
			food.setSaveFlag(true);		
			
			
			model.terminate();
			
			return "success";
		}		
		
		/**
		 * following function is called when delete button is clicked in the jsp page
		 * @return
		 */
		public String delete() throws Exception{
			/*Default Method with save modeCode(2)*/
			getNavigationPanel(1);
			FoodTypeModel model = new FoodTypeModel();
			model.initiate(context, session);
			
			boolean result=model.deleteFoodType(food);
			
			logger.info("result in Delete---->"+result);
			
			if(result){
				addActionMessage(getMessage("delete"));
				reset();
				callPage();
			}else{
				addActionMessage(getMessage("del.error"));
				callPage();
			}
			food.setOnLoadFlag(true);
			model.terminate();
			
			return "input";
		}
		
		
		/**
		 * following function is called to set the field values when a record is selected from the search window
		 * @throws Exception
		 */
		public String  getRecord() throws Exception{
			
			FoodTypeModel model = new FoodTypeModel();
			model.initiate(context, session);
			model.getFoodType(food);
			food.setSaveFlag(true);
			model.reqData(food,request);
			model.terminate();
			return "success";
		}
		
	/*	public String callPage1() throws Exception {
			getNavigationPanel(1);
			FoodTypeModel model = new FoodTypeModel();
			model.initiate(context, session);
			food.setPageFlag(true);
			//bean.setOnLoadFlag("true");
			model.reqData(food,request);
			model.terminate();
			
			
			return "success";
		}*/
		
		
		//added by debjani
		public String callPage1() throws Exception {
			if(food.getCallpageflag().equals("success"))
			{
				if(food.isEditableflag())
					getNavigationPanel(2);
				else
					getNavigationPanel(3);
			}
			else
			{
				getNavigationPanel(1);
			}
			FoodTypeModel model = new FoodTypeModel();
			model.initiate(context, session);
			food.setPageFlag(true);
			//bean.setOnLoadFlag("true");o
			model.reqData(food,request);
			model.terminate();
			
			
			return food.getCallpageflag();
		}
		
		
	/*	public String callPage2() throws Exception {
			getNavigationPanel(1);
			FoodTypeModel model = new FoodTypeModel();
			model.initiate(context, session);
			model.reqData(food,request);
			food.setPageFlag(true);
			model.terminate();
			
			
			return "success";
		}*/
		//by debajani
		public String callPage2() throws Exception {
			
			if(food.getCallpageflag().equals("success"))
			{
				if(food.isEditableflag())
					getNavigationPanel(2);
				else
					getNavigationPanel(3);
			}
			else
			{
				getNavigationPanel(1);
			}
			FoodTypeModel model = new FoodTypeModel();
			model.initiate(context, session);
			model.reqData(food,request);
			model.terminate();
			System.out.println("food.getCallpageflag()---------"+food.getCallpageflag());
			return food.getCallpageflag();
		}
		
		
		/**
		 * following function is called to display all the records when the link is clicked
		 * @return
		 * @throws Exception
		 */
		public String callPage() throws Exception {
			FoodTypeModel model = new FoodTypeModel();	
			model.initiate(context, session);
			model.reqData(food,request);
			model.terminate();
			
			
			return food.getCallpageflag();
		}
		
		/**
		 * following function is called when 
		 * @return
		 * @throws Exception
		 */
		public String calforedit() throws Exception {
			getNavigationPanel(2);			
			FoodTypeModel model = new FoodTypeModel();	
			model.initiate(context,session);
			model.getFoodTypeOnDoubleClick(food);
			//model.getFoodType(food);
			model.reqData(food,request);
			food.setDblFlag(true);
			food.setModFlag(false);
			food.setEditableflag(true);
			//bean.setFlag("true");
			model.terminate();
			
			return "success";
			
		
		}
		
		public String delete1() throws Exception{
			/*Default Method with save modeCode(2)*/
			getNavigationPanel(1);
			boolean result;
			FoodTypeModel model = new FoodTypeModel();
			model.initiate(context,session);
			callPage2();
			String[] code=request.getParameterValues("hdeleteCode");
			result=model.delChkdRec(food,code);
			if(result){
				prepare_withLoginProfileDetails();
				//reset();
				addActionMessage(getMessage("delete"));
				getNavigationPanel(1);
			}
			else
			{
				addActionMessage(getMessage("multiple.del.error"));
				getNavigationPanel(1);
			}
			return "input";
		}
	
	/** called on load to set the list* */
	public void prepare_withLoginProfileDetails() throws Exception {
		FoodTypeModel model = new FoodTypeModel();		
		model.initiate(context, session);
		model.reqData(food, request);
		model.terminate();
	}
	public String report(){
		getNavigationPanel(5);
		FoodTypeModel model = new FoodTypeModel();
		model.initiate(context,session);
		String[]label={"Sr.No",getMessage("food.type"),getMessage("food.desc"),getMessage("food.sts")};
		model.generateReport(food,response,label);
		model.terminate();
		return null;
	}
	
	public String cancel() throws Exception{
		FoodTypeModel model = new FoodTypeModel();
		model.initiate(context, session);
		model.terminate();
		return SUCCESS;
	}
	
	public String f9Action() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		//String query = "SELECT  * FROM HRMS_TMS_ROOM_TYPE	 ORDER BY  ROOM_TYPE_ID";
		String query="SELECT  FOOD_TYPE_NAME,"
			+ " CASE WHEN FOOD_TYPE_STATUS='A' THEN 'Active' ELSE 'Deactive' END ,FOOD_TYPE_ID"
			+ " FROM HRMS_TMS_FOOD_TYPE	 ORDER BY  FOOD_TYPE_NAME,FOOD_TYPE_STATUS";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("food.type"),getMessage("food.sts")};

		String[] headerWidth = { "15", "10"};

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		
		String[] fieldNames = {  "typeName","status","typeId"};

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "FoodType_details.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String details() {
		getNavigationPanel(3);
		FoodTypeModel model = new FoodTypeModel();
		model.initiate(context,session);
		model.getDesc(food);
		model.getFoodType(food);
		food.setSaveFlag(true);
		food.setNoflag(false);
		food.setCancelflag(false);
		model.reqData(food,request);
		model.terminate();
		return "success";
	}
	/**
	 * To set the fields after search
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String search() {
		getNavigationPanel(3);
		return SUCCESS;
	}

}
