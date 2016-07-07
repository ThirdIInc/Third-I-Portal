
package org.struts.action.TravelManagement.Master;
import org.paradyne.bean.TravelManagement.Master.RoomType;
import org.paradyne.model.TravelManagement.Master.RoomTypeModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author aa0651
 *
 */
public class RoomTypeAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	RoomType rmType;
	@Override
	public void prepare_local() throws Exception {
		rmType = new RoomType();
		rmType.setMenuCode(767);

	}

	public Object getModel() {
		return rmType;
	}

	public RoomType getRmType() {
		return rmType;
	}

	public void setRmType(RoomType rmType) {
		this.rmType = rmType;
	}
	public String input() throws Exception{
		//Default Method with default modeCode(1)	
		System.out.println("in input method----------------");
		getNavigationPanel(1);
		RoomTypeModel model = new RoomTypeModel();	
		model.initiate(context, session);
		rmType.setOnLoadFlag(true);
		callPage();
		model.terminate();
		rmType.setCallpageflag("input");
		return "input";
	}
	
	public String addNew() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(2);
		RoomTypeModel model = new RoomTypeModel();		
		model.initiate(context,session);
		callPage();
		reset();
		rmType.setLoadFlag(true);
		rmType.setFlag(true);	
		//added by debjani
		rmType.setEditableflag(true);
		
		model.terminate();
		rmType.setCallpageflag("success");
		return "success";
		
	}
	
	public String edit() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(4);
		RoomTypeModel model = new RoomTypeModel();
		model.initiate(context, session);
		model.getRoomTypeSearch(rmType);
		callPage();
		
		rmType.setEditFlag(true);
		
		
		rmType.setModFlag(true);
		rmType.setAddFlag(false);
		rmType.setOnLoadFlag(false);
		rmType.setEditableflag(true);
		rmType.setCancelflag(true);
		model.terminate();
		return "success";
		
		
	}
	
	
	
	public String reset() throws Exception{
		rmType.setTypeId("");
		rmType.setTypeName("");
		rmType.setDesciption("");
		rmType.setStatus("");
		rmType.setHotelId("");
		rmType.setHotelName("");
		return SUCCESS;
	}
	
	public String cancelThrd() throws Exception{
		logger.info("Inside Cancel Third");
			getNavigationPanel(3);
			RoomTypeModel model = new RoomTypeModel();	
			model.initiate(context, session);
			
			callPage();
			model.getRoomType(rmType);
			rmType.setSaveFlag(true);
			rmType.setModFlag(false);
			model.terminate();
			return "success";
			
		}
		
		public String cancelSec() throws Exception{
			getNavigationPanel(1);
			RoomTypeModel model = new RoomTypeModel();	
			model.initiate(context, session);
			callPage();
			rmType.setOnLoadFlag(true);
			rmType.setSaveFlag(true);
			model.terminate();
			return "success";
			
		}
		
		/*public String cancelFirst() throws Exception{
			getNavigationPanel(1);
			callPage();
			rmType.setOnLoadFlag(true);
			reset();
			return "success";
		}
		*/
		
		public String cancelFirst() throws Exception {
			logger.info("=========rmType.getCancelFlg()========="
					+ rmType.isCancelflag());
			String str;
			RoomTypeModel model = new RoomTypeModel();	
			model.initiate(context, session);
			if (rmType.isCancelflag()) {
				getNavigationPanel(3);
				System.out.println("inside if method-----");
				model.getRoomType(rmType);
				model.reqData(rmType, request);
				rmType.setOnLoadFlag(true);
				str = "success";
			} else {
				getNavigationPanel(1);
				model.reqData(rmType, request);
				reset();
				rmType.setOnLoadFlag(false);
				str = "input";
			}
			rmType.setCancelflag(false);
			rmType.setCallpageflag("input");
			rmType.setEditableflag(false);
			model.terminate();
			return str;
		}
		
		
		
		
		
		
		
		
		
		
		public String cancelFrth() throws Exception{
			logger.info("Inside Cancel Fourth");
			getNavigationPanel(1);
			RoomTypeModel model = new RoomTypeModel();	
			model.initiate(context, session);
			callPage();
			reset();
			rmType.setOnLoadFlag(true);
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
			RoomTypeModel model = new RoomTypeModel();	
			model.initiate(context, session);
			
			logger.info("+++++++++++rmType.getTypeId()==============="+rmType.getTypeId());
			if(rmType.getTypeId().equals("")){
				logger.info("***********==Inside Save=========");
				boolean result=model.addRoomType(rmType);
				if(result){
				addActionMessage(getMessage("save"));
				callPage();
				rmType.setEditableflag(false);
				rmType.setNoflag(false);
				rmType.setCancelflag(false);
				}else{
				addActionMessage(getMessage("duplicate"));
				getNavigationPanel(2);
				}
			}
			else
			{
				logger.info("***********==Inside Update=========");
				boolean result=model.modRoomType(rmType);
				if(result){
					addActionMessage(getMessage("update"));
					rmType.setCancelflag(false);
					rmType.setNoflag(false);
					rmType.setEditableflag(false);
					callPage();
				}else{
					addActionMessage(getMessage("duplicate"));
					getNavigationPanel(2);
					rmType.setLoadFlag(true);
					rmType.setFlag(true);
				}
			}
			model.reqData(rmType,request);			
			
			rmType.setLoadFlag(false);
			rmType.setAddFlag(true);
			rmType.setSaveFlag(true);		
			
			
			model.terminate();
			
			return "success";
		}
		
		/**
		 * following function is called when update button is clicked in the jsp page  
		 * @return
		 */
		public String update() throws Exception{
			/*Default Method with save modeCode(2)*/
			getNavigationPanel(3);
			RoomTypeModel model = new RoomTypeModel();	
			model.initiate(context, session);
			boolean result=model.modRoomType(rmType);
			if(result){
				addActionMessage(getMessage("update"));
				callPage();
			}else{
				addActionMessage(getMessage("duplicate"));
			}
			model.reqData(rmType,request);		
			//rmType.setModFlag(true);
			//rmType.setSaveFlag(true);
			rmType.setLoadFlag(false);
			rmType.setAddFlag(true);
			rmType.setSaveFlag(true);	
			
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
			RoomTypeModel model = new RoomTypeModel();
			model.initiate(context, session);
					
			boolean result=model.deleteRoomType(rmType);
			
			logger.info("result in Delete---->"+result);
			
			if(result){
				addActionMessage(getMessage("delete"));
				reset();
				callPage();
			}else{
				addActionMessage(getMessage("del.error"));
				callPage();
			}
			rmType.setOnLoadFlag(true);
			model.terminate();
			
			return "input";
		}
		
		
		/**
		 * following function is called to set the field values when a record is selected from the search window
		 * @throws Exception
		 */
		public String  getRecord() throws Exception{
			
			RoomTypeModel model = new RoomTypeModel();
			model.initiate(context, session);
			model.getRoomType(rmType);
			rmType.setSaveFlag(true);
			model.reqData(rmType,request);
			model.terminate();
			return "success";
		}
		
		/*public String callPage1() throws Exception {
			getNavigationPanel(1);
			RoomTypeModel model = new RoomTypeModel();
			model.initiate(context, session);
			rmType.setPageFlag(true);
			//bean.setOnLoadFlag("true");
			model.reqData(rmType,request);
			model.terminate();
			
			
			return "success";
		}*/
		
		//by debjani
		public String callPage1() throws Exception {
			if(rmType.getCallpageflag().equals("success"))
			{
				if(rmType.isEditableflag())
					getNavigationPanel(2);
				else
					getNavigationPanel(3);
			}
			else
			{
				getNavigationPanel(1);
			}
			RoomTypeModel model = new RoomTypeModel();
			model.initiate(context, session);
			rmType.setPageFlag(true);
			//bean.setOnLoadFlag("true");o
			model.reqData(rmType,request);
			model.terminate();
			
			
			return rmType.getCallpageflag();
		}
		
		
		
		
		
		
		
		
		
/*		public String callPage2() throws Exception {
			getNavigationPanel(1);
			RoomTypeModel model = new RoomTypeModel();
			model.initiate(context, session);
			model.reqData(rmType,request);
			rmType.setPageFlag(true);
			model.terminate();
			
			
			return "success";
		}*/
		//by debjani
		
		public String callPage2() throws Exception {
			if(rmType.getCallpageflag().equals("success"))
			{
				if(rmType.isEditableflag())
					getNavigationPanel(2);
				else
					getNavigationPanel(3);
			}
			else
			{
				getNavigationPanel(1);
			}
			RoomTypeModel model = new RoomTypeModel();
			model.initiate(context, session);
			model.reqData(rmType,request);
			model.terminate();
			return rmType.getCallpageflag();
		}
		
		/**
		 * following function is called to display all the records when the link is clicked
		 * @return
		 * @throws Exception
		 */
		public String callPage() throws Exception {
			RoomTypeModel model = new RoomTypeModel();	
			model.initiate(context, session);
			model.reqData(rmType,request);
			model.terminate();
			
			
			return rmType.getCallpageflag();
		}
		
		/**
		 * following function is called when 
		 * @return
		 * @throws Exception
		 */
		public String calforedit() throws Exception {
			getNavigationPanel(2);			
			RoomTypeModel model = new RoomTypeModel();	
			model.initiate(context,session);
			model.getRoomTypeOnDoubleClick(rmType);
			//model.getRoomType(rmType);
			model.reqData(rmType,request);
			rmType.setDblFlag(true);
			rmType.setModFlag(false);
			rmType.setEditableflag(true);
			//bean.setFlag("true");
			model.terminate();
			
			return "success";
			
		
		}
		
		public String delete1() throws Exception{
			/*Default Method with save modeCode(2)*/
			getNavigationPanel(1);
			boolean result;
			RoomTypeModel model = new RoomTypeModel();
			model.initiate(context,session);
			callPage2();
			String[] code=request.getParameterValues("hdeleteCode");
			result=model.delChkdRec(rmType,code);
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
		System.out.println("in profile method----------------");
		RoomTypeModel model = new RoomTypeModel();		
		model.initiate(context, session);
		model.reqData(rmType, request);
		model.terminate();
	}
	public String report(){
		getNavigationPanel(5);
		RoomTypeModel model = new RoomTypeModel();
		model.initiate(context,session);
		String[]label={"Sr.No","Hotel Type",getMessage("room.type"),getMessage("room.desc"),getMessage("room.sts")};
		model.generateReport(rmType,response,label);
		model.terminate();
		return null;
	}
	
	public String cancel() throws Exception{
		RoomTypeModel model = new RoomTypeModel();
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
		String query = "SELECT HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_NAME, ROOM_TYPE_NAME,"
				+ " CASE WHEN ROOM_TYPE_STATUS='A' THEN 'Active' ELSE 'Deactive' END,ROOM_TYPE_ID,ROOM_HOTEL_TYPE "
				+ " FROM HRMS_TMS_ROOM_TYPE	"
				+ " LEFT JOIN HRMS_TMS_HOTEL_TYPE  ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=HRMS_TMS_ROOM_TYPE.ROOM_HOTEL_TYPE)"
				+ " ORDER BY  ROOM_LEVEL";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { getMessage("hotel.name"),getMessage("room.type"), getMessage("room.sts") };

		String[] headerWidth = { "20","20", "15" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "hotelName","typeName", "status", "typeId", "hotelId" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4 };

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
		String submitToMethod = "RoomType_details.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	
	public String f9Hotel() throws Exception {
		
		String query="SELECT  HOTEL_TYPE_NAME,HOTEL_TYPE_ID "
			+ " FROM HRMS_TMS_HOTEL_TYPE WHERE HOTEL_TYPE_STATUS ='A'"
			+ " ORDER BY  HOTEL_LEVEL";
		
		String[] headers = {"Hotel Name" };
		
		String[] headerWidth = {  "20"};		

		String[] fieldNames = { "hotelName","hotelId", };
		
		int[] columnIndex = { 0, 1};

		String submitFlag = "false";
		
		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		
		return "f9page";
	}
	
	public String details() {
		getNavigationPanel(3);
		RoomTypeModel model = new RoomTypeModel();
		model.initiate(context,session);
		model.getDesc(rmType);
		model.getRoomType(rmType);
		rmType.setSaveFlag(true);
		rmType.setSaveFlag(true);
		rmType.setNoflag(false);
		rmType.setCancelflag(false);
		model.reqData(rmType,request);
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
	
	
	
	public String upData() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(1);
		boolean result;
		RoomTypeModel model = new RoomTypeModel();	
		model.initiate(context,session);
		callPage2(); 
		String results=model.upData(rmType); 
		if(!results.equals("")){
			addActionMessage(results);
		}
			prepare_withLoginProfileDetails();
			reset();  
		return "success";
	}
	
	public String upDataView() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(1);
		logger.info("Inside action updata");
		boolean result;
		RoomTypeModel model = new RoomTypeModel();	
		model.initiate(context,session);
		callPage2(); 
		String results=model.upData(rmType); 
		if(!results.equals("")){
			addActionMessage(results);
		}
			prepare_withLoginProfileDetails();
			reset();  
		return "input";
	}
	
	/*public String TravelRecord() throws Exception {
		logger.info("I am in model");
		RoomTypeModel model = new RoomTypeModel();
		model.initiate(context, session);
		model.getTypeRecord(rmType);
		model.terminate();
		return "success";
	}*/

	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 *//*
	public String callPage() throws Exception {

		RoomTypeModel model = new RoomTypeModel();		

		model.initiate(context, session);
		model.reqData(rmType, request);
		model.terminate();
		return SUCCESS;

	}*/
	
	/*public String save() throws Exception {

		
		RoomTypeModel model = new RoomTypeModel();		
		model.initiate(context, session);		
		boolean result;

		if (rmType.getTypeId().equals("")) {
			
			result = model.addType(rmType);
			if (result) {
				addActionMessage("Record saved successfully");
				
			}// end of if
			else {
				addActionMessage("Duplicate entry found");
			}// end of else
		}// end of nested if
		else {
			
			result = model.modType(rmType);
			
			if (result) {
				addActionMessage("Record modified successfully");
				
			} else {
				addActionMessage("Duplicate entry found");
			}
		}// end of else
		
		// model.getTravelRecord(trvPurpose);
		model.reqData(rmType, request);
		model.terminate();
		
		getNavigationPanel(2);
		return reset();

	}*/
	/**
	 * To delete any record
	 * 
	 * @return String
	 * @throws Exception
	 *//*
	public String delete() throws Exception {
		logger.info(" into delete");

		logger.info(" into delete<--------------------------------------->");
		RoomTypeModel model = new RoomTypeModel();
		logger.info("bean model");
		model.initiate(context, session);
		boolean result = model.deleteType(rmType);
		if (result) {
			reset();

		}// end of if
		if (result) {
			addActionMessage(getText("delMessage", ""));
		} // end of if
		else {
			addActionMessage("Record can't be deleted \n as it is associated with some other record");
		}// end of else
		model.reqData(rmType, request);
		model.terminate();
		getNavigationPanel(1);
		return "success";

	}*/
	/**
	 * To delete one or more records from the list
	 * 
	 * @return String
	 * @throws Exception
	 *//*
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		RoomTypeModel model = new RoomTypeModel();

		model.initiate(context, session);
		boolean result = model.deleteType(rmType, code);
		if (result) {
			addActionMessage("Record deleted successfully");

			rmType.setTypeId("");
			rmType.setTypeName("");
			rmType.setTrvHighCode("");

		}
		// end of if
		else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other record(s) .");
		}// end of else

		model.reqData(rmType, request);
		model.terminate();
		getNavigationPanel(1);
		return "success";

	}*/
	/**
	 * To edit any record in the list by double clicking on it
	 * 
	 * @return String
	 * @throws Exception
	 *//*

	public String calforedit() throws Exception {
		logger.info(" ------------------- ");
		RoomTypeModel model = new RoomTypeModel();
		model.initiate(context, session);
		model.calforedit(rmType);
		// RankRecord();

		model.reqData(rmType, request);		
		model.terminate();
		getNavigationPanel(1);
		return "success";
	}*/
	

}
