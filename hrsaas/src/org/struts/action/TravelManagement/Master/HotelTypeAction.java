package org.struts.action.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.HotelType;
import org.paradyne.model.TravelManagement.Master.HotelTypeModel;
import org.paradyne.model.TravelManagement.Master.RoomTypeModel;
import org.paradyne.model.TravelManagement.Master.TravelPurposeModel;


		/**
		 * Author Dilip 
		 */
	public class HotelTypeAction extends org.struts.lib.ParaActionSupport {

			// private static final long serialVersionUID = 7047317819789938757L;
		static org.apache.log4j.Logger logger = org.apache.log4j.Logger
		.getLogger(org.struts.lib.ParaActionSupport.class);
		
		HotelType bean;
		public void prepare_local() throws Exception {
			// TODO Auto-generated method stub
			
			bean = new HotelType();
			bean.setMenuCode(768);

		}
		@Override
		public void prepare_withLoginProfileDetails() throws Exception {
			HotelTypeModel model=new HotelTypeModel();
			model.initiate(context, session);
			model.getRecords(bean, request);
			model.terminate();
			//callPage();
		}

		public Object getModel() {
			// TODO Auto-generated method stub
			return bean;
		}
		public HotelType getBean() {
			return bean;
		}
		public void setBean(HotelType bean) {
			this.bean = bean;
		}
		
		public String input() throws Exception{
			//Default Method with default modeCode(1)		
			getNavigationPanel(1);
			HotelTypeModel model=new HotelTypeModel();
			model.initiate(context, session);
			bean.setOnLoadFlag(true);
			model.getRecords(bean, request);
			callPage();
			model.terminate();
			bean.setCallpageflag("view");
			return "view";
		}
		
		public String addNew() throws Exception{
			/*Default Method with save modeCode(2)*/
			getNavigationPanel(2);
			HotelTypeModel model=new HotelTypeModel();
			model.initiate(context,session);
			callPage();
			reset();
			bean.setLoadFlag(true);
			bean.setFlag(true);
			bean.setEditableflag(true);
			//System.out.println("val of flag"+bean.getFlag());
			model.terminate();
			bean.setCallpageflag("success");
			return "success";
			
		}
		
		public String edit() throws Exception{
			/*Default Method with save modeCode(2)*/
			getNavigationPanel(2);
			HotelTypeModel model=new HotelTypeModel();
			model.initiate(context, session);
			model.getHotelType(bean);
			//model.getRecords(bean, request);
			callPage();
			logger.info("Inside Edit----->");
			//logger.info("bean.getEditFlag()---->"+bean.getEditFlag());
			
			bean.setEditFlag(true);
			
			//logger.info("bean.getEditFlag() After Setting---->"+bean.getEditFlag());
			bean.setModFlag(true);
			bean.setAddFlag(false);
			bean.setOnLoadFlag(false);
			bean.setEditableflag(true);
			bean.setCancelflag(true);
			model.terminate();
			return "success";
			
			
		}
		
	public String cancelThrd() throws Exception{
		logger.info("Inside Cancel Third");
			getNavigationPanel(3);
			HotelTypeModel model=new HotelTypeModel();
			model.initiate(context, session);
			
			callPage();
			model.getHotelTypeEdt(bean);
			bean.setSaveFlag(true);
			bean.setModFlag(false);
			model.terminate();
			return "success";
			
		}
		
		public String cancelSec() throws Exception{
			getNavigationPanel(1);
			HotelTypeModel model=new HotelTypeModel();
			model.initiate(context, session);
			callPage();
			bean.setOnLoadFlag(true);
			bean.setSaveFlag(true);
			model.terminate();
			reset();
			return "success";
			
			
		}
		
		/*public String cancelFirst() throws Exception{
			getNavigationPanel(1);
			callPage();
			bean.setOnLoadFlag(true);
			logger.info("-----Reset in Cancel First------");
			reset();
			return "view";
		}*/
		
		
		
		
		//debjani
		
		public String cancelFirst() throws Exception {
			logger.info("=========bean.getCancelFlg()========="
					+ bean.isCancelflag());
			String str;
			HotelTypeModel model=new HotelTypeModel();
			model.initiate(context, session);
			if (bean.isCancelflag()) {
				getNavigationPanel(3);
				System.out.println("inside if method-----");
				model.getHotelTypeRec(bean);
				model.getRecords(bean, request);
				bean.setOnLoadFlag(true);
				str = "success";
			} else {
				getNavigationPanel(1);
				model.getRecords(bean, request);
				reset();
				bean.setOnLoadFlag(false);
				str = "view";
			}
			bean.setCancelflag(false);
			bean.setCallpageflag("view");
			bean.setEditableflag(false);
			model.terminate();
			return str;
		}
		public String cancelFrth() throws Exception{
			logger.info("Inside Cancel Fourth");
			getNavigationPanel(1);
			HotelTypeModel model=new HotelTypeModel();
			model.initiate(context, session);
			callPage();
			reset();
			bean.setOnLoadFlag(true);
			model.terminate();
			return "success";
			
		}
		
		/**
		 * following function is called when add new record is clicked in the jsp page
		 * @return
		 */
		public String save() throws Exception {
			//Default Method with Edit modeCode(3)
			System.out.println("hello-----------------");
			getNavigationPanel(3);
			 boolean result;
			 HotelTypeModel model=new HotelTypeModel();
			model.initiate(context, session);
			
			 {
				 if(bean.getHotelCode().equals("")){
			 result=model.addHotelType(bean);
			 if(result){
				 addActionMessage(getMessage("save"));
				 bean.setCancelflag(false);
				 bean.setEditableflag(false);
				 bean.setNoflag(false);
				callPage();
			}
			 else{
				 addActionMessage(getMessage("duplicate"));
				 getNavigationPanel(2);
			}
				 }
				 else{
					 result=model.modHotelType(bean);
					 if(result){
						 addActionMessage(getMessage("update"));
						 bean.setCancelflag(false);
						 bean.setNoflag(false);
						 bean.setEditableflag(false);
						callPage();
					}
					 else{
						
						 addActionMessage(getMessage("duplicate"));
						getNavigationPanel(2);
						bean.setLoadFlag(true);
						bean.setFlag(true);
						callPage();
					}
					 
				 }
			 
			model.getRecords(bean,request);
			
			//logger.info("value of save flag---->"+bean.getSaveFlag());
			bean.setLoadFlag(false);
			bean.setAddFlag(true);
			bean.setSaveFlag(true);
			
			//logger.info("value of Save Flag After Setting--->"+bean.getSaveFlag());
			
			
			
			}
			 model.terminate();
			 return "success";
		
			}
			
		

		
		

		
		/**
		 * following function is called when update button is clicked in the jsp page  
		 * @return
		 */
		/*public String update() throws Exception{
			Default Method with save modeCode(2)
			getNavigationPanel(2);
			HotelTypeModel model=new HotelTypeModel();
			model.initiate(context, session);
			boolean result=model.modHotelType(bean);
			if(result){
				addActionMessage(getMessage("update"));
				
				callPage();
			}else{
				addActionMessage(getMessage("duplicate"));
				callPage();
			}
			bean.setModFlag(true);
			bean.setSaveFlag(true);
			model.terminate();
			
			return "success";
		}*/
		
		/**
		 * following function is called when delete button is clicked in the jsp page
		 * @return
		 */
		public String delete() throws Exception{
			/*Default Method with save modeCode(2)*/
			getNavigationPanel(1);
			HotelTypeModel model=new HotelTypeModel();
			model.initiate(context, session);
					
			boolean result=model.deleteHotelType(bean);
			
			logger.info("result in Delete---->"+result);
			
			if(result){
				addActionMessage(getMessage("delete"));
				reset();
				callPage();
			}else{
				addActionMessage(getMessage("del.error"));
				//callPage();
			}
			bean.setOnLoadFlag(true);
			model.terminate();
			
			return "view";
		}
		
		/**
		 * following function is called to reset the fields.
		 * @return
		 */
		public String reset(){
			
			bean.setHotelCode("");
			bean.setHotelName("");
			bean.setDescription("");
			
			bean.setStatus("");
			
			
			
			return "success";
		}
		
		/**
		 * following function is called to set the field values when a record is selected from the search window
		 * @throws Exception
		 */
		public String  getRecord() throws Exception{
			
			HotelTypeModel model=new HotelTypeModel();
			model.initiate(context, session);
			model.getHotelType(bean);
			bean.setSaveFlag(true);
			model.getRecords(bean,request);
			model.terminate();
			return "success";
		}
		
public String report() throws Exception{
			
	HotelTypeModel model=new HotelTypeModel();
			model.initiate(context, session);
			String []label={"Sr.No",getMessage("hotel.name"),getMessage("desc"),getMessage("status")};
			model.getReport(bean, request, response, context,label);
			bean.setSaveFlag(true);
			model.getRecords(bean,request);
			model.terminate();
			return null;
		}
		public String callPage1() throws Exception {
			if(bean.getCallpageflag().equals("success"))
			{
				if(bean.isEditableflag())
					getNavigationPanel(2);
				else
					getNavigationPanel(3);
			}
			else
			{
				getNavigationPanel(1);
			}
			HotelTypeModel model=new HotelTypeModel();
			model.initiate(context, session);
			bean.setPageFlag(true);
			//bean.setOnLoadFlag("true");o
			model.getRecords(bean,request);
			model.terminate();
			
			
			return bean.getCallpageflag();
		}
		public String callPage2() throws Exception {
			if(bean.getCallpageflag().equals("success"))
			{
				if(bean.isEditableflag())
					getNavigationPanel(2);
				else
					getNavigationPanel(3);
			}
			else
			{
				getNavigationPanel(1);
			}
			HotelTypeModel model=new HotelTypeModel();
			model.initiate(context, session);
			model.getRecords(bean,request);
			model.terminate();
			return bean.getCallpageflag();
		}
		
		
		/**
		 * following function is called to display all the records when the link is clicked
		 * @return
		 * @throws Exception
		 */
		public String callPage() throws Exception {
			HotelTypeModel model=new HotelTypeModel();
			model.initiate(context, session);
			model.getRecords(bean,request);
			model.terminate();
			
			
			return bean.getCallpageflag();
		}
		
		/**
		 * following function is called when 
		 * @return
		 * @throws Exception
		 */
		public String calforedit() throws Exception {
			getNavigationPanel(2);
			logger.info("Calforedit inside indus ");
            HotelTypeModel model=new HotelTypeModel();
			model.initiate(context,session);
			model.getHotelTypeOnDoubleClick(bean);
			model.getHotelType(bean);
			model.getRecords(bean,request);
			bean.setDblFlag(true);
			bean.setModFlag(false);
			
			bean.setEditableflag(true);
			//bean.setFlag("true");
			model.terminate();
		
			return "success";
			
		
		}
		
		
		public String delete1() throws Exception{
			/*Default Method with save modeCode(2)*/
			getNavigationPanel(1);
			boolean result;
			HotelTypeModel model = new HotelTypeModel();
			model.initiate(context,session);
			callPage2();
			String[] code=request.getParameterValues("hdeleteCode");
			result=model.delChkdRec(bean,code);
			if(result){
				prepare_withLoginProfileDetails();
				addActionMessage(getText("delMessage",""));
				model.getRecords(bean, request);
				getNavigationPanel(1);
				
				//reset();
			}
			else
			{
				addActionMessage(getMessage("multiple.del.error"));
				getNavigationPanel(1);
			}
			return "view";
		}
		
		/**
		 * 	 
		 *  The Following Method is used to display Search Window to get Record to modify 
		 */

		
		public String f9Action() throws Exception {
			
			String query = " SELECT NVL(HOTEL_TYPE_NAME,' '),DECODE(HOTEL_TYPE_STATUS,'A','Active','D','Deactive'),HOTEL_TYPE_ID   FROM HRMS_TMS_HOTEL_TYPE ORDER BY HOTEL_LEVEL  ";
				
				String []headers ={getMessage("hotel.name"),getMessage("status")};
				String []headerwidth={"20","20","20"};
				
				
				String fieldNames[]={"hotelName","Status","hotelCode"};
			
				int []columnIndex={0,1,2};
			
				String submitFlage ="true";
				
				String submitToMethod ="HotelType_details.action";
			
				setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
				
				return "f9page";
		}
		public String details() {
			getNavigationPanel(3);
			HotelTypeModel model = new HotelTypeModel();
			model.initiate(context, session);
			model.getHotelTypeRec(bean);
			bean.setSaveFlag(true);
			 bean.setNoflag(false);
			 bean.setCancelflag(false);
			model.getRecords(bean,request);
			model.terminate();
			return "success";
		}
		
		
		public String upData() throws Exception{
			/*Default Method with save modeCode(2)*/
			getNavigationPanel(1);
			boolean result;
			HotelTypeModel model = new HotelTypeModel();
			model.initiate(context,session);
			callPage2(); 
		     model.upData(bean); 
				prepare_withLoginProfileDetails();
				reset();  
			return "success";
		}
		
		public String upDataView() throws Exception{
			/*Default Method with save modeCode(2)*/
			getNavigationPanel(1);
			boolean result;
			HotelTypeModel model = new HotelTypeModel();
			model.initiate(context,session);
			callPage2(); 
		     model.upData(bean); 
				prepare_withLoginProfileDetails();
				reset();  
			return "view";
		}

		
		
		
		
		}





