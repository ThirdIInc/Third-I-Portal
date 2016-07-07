package org.struts.action.TravelManagement.Master;

import org.paradyne.bean.TravelManagement.Master.TravelGrade;
import org.paradyne.model.TravelManagement.Master.TravelGradeModel;



			/**
			 * 
			 */
	public class TravelGradeAction  extends org.struts.lib.ParaActionSupport {

				// private static final long serialVersionUID = 7047317819789938757L;


				/**
				 * @return 
				 */
				

				static org.apache.log4j.Logger logger = org.apache.log4j.Logger
						.getLogger(org.struts.lib.ParaActionSupport.class);
				TravelGrade bean;
				public void prepare_local() throws Exception {
					// TODO Auto-generated method stub
					
					bean = new TravelGrade();
					bean.setMenuCode(769);

				}
				@Override
				public void prepare_withLoginProfileDetails() throws Exception {
					//callPage();
				}

				public Object getModel() {
					// TODO Auto-generated method stub
					return bean;
				}
				public TravelGrade getBean() {
					return bean;
				}
				public void setBean(TravelGrade bean) {
					this.bean = bean;
				}
				
				public String input() throws Exception{
					//Default Method with default modeCode(1)		
					getNavigationPanel(1);
					TravelGradeModel model=new TravelGradeModel();
					model.initiate(context, session);
					bean.setOnLoadFlag(true);
					callPage();
					model.terminate();
					return INPUT;
				}
				
				public String addNew() throws Exception{
					/*Default Method with save modeCode(2)*/
					getNavigationPanel(2);
					TravelGradeModel model=new TravelGradeModel();
					model.initiate(context,session);
					callPage();
					reset();
					bean.setLoadFlag(true);
					bean.setFlag(true);
					//System.out.println("val of flag"+bean.getFlag());
					model.terminate();
					return "success";
					
				}
				
				public String edit() throws Exception{
					/*Default Method with save modeCode(2)*/
					getNavigationPanel(2);
					TravelGradeModel model=new TravelGradeModel();
					model.initiate(context, session);
					model.getTravelGrade(bean);
					//model.getRecords(bean, request);
					callPage();
					logger.info("Inside Edit----->");
					//logger.info("bean.getEditFlag()---->"+bean.getEditFlag());
					
					bean.setEditFlag(true);
					
					//logger.info("bean.getEditFlag() After Setting---->"+bean.getEditFlag());
					bean.setModFlag(true);
					bean.setAddFlag(false);
					bean.setOnLoadFlag(false);
					model.terminate();
					return "success";
					
					
				}
				
			public String cancelThrd() throws Exception{
				logger.info("Inside Cancel Third");
					getNavigationPanel(3);
					TravelGradeModel model=new TravelGradeModel();
					model.initiate(context, session);
					
					callPage();
					model.getTravelGradeEdt(bean);
					bean.setSaveFlag(true);
					bean.setModFlag(false);
					model.terminate();
					return "success";
					
				}
				
				public String cancelSec() throws Exception{
					getNavigationPanel(1);
					TravelGradeModel model=new TravelGradeModel();
					model.initiate(context, session);
					callPage();
					bean.setOnLoadFlag(true);
					bean.setSaveFlag(true);
					model.terminate();
					reset();
					return "success";
					
					
				}
				
				public String cancelFirst() throws Exception{
					getNavigationPanel(1);
					callPage();
					bean.setOnLoadFlag(true);
					logger.info("-----Reset in Cancel First------");
					reset();
					return "success";
				}
				
				public String cancelFrth() throws Exception{
					logger.info("Inside Cancel Fourth");
					getNavigationPanel(1);
					TravelGradeModel model=new TravelGradeModel();
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
					getNavigationPanel(3);
					 boolean result;
					 TravelGradeModel model=new TravelGradeModel();
					model.initiate(context, session);
					
					 {
						 if(bean.getGradeCode().equals("")){
					 result=model.addTravelGrade(bean);
					 if(result){
						addActionMessage(getMessage("save"));
						callPage();
					}
					 else{
						 addActionMessage(getMessage("duplicate"));
						callPage();
					}
						 }
						 else{
							 result=model.modTravelGrade(bean);
							 if(result){
								 addActionMessage(getMessage("update"));
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
				public String update() throws Exception{
					/*Default Method with save modeCode(2)*/
					getNavigationPanel(2);
					TravelGradeModel model=new TravelGradeModel();
					model.initiate(context, session);
					boolean result=model.modTravelGrade(bean);
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
				}
				
				/**
				 * following function is called when delete button is clicked in the jsp page
				 * @return
				 */
				public String delete() throws Exception{
					/*Default Method with save modeCode(2)*/
					getNavigationPanel(1);
					TravelGradeModel model=new TravelGradeModel();
					model.initiate(context, session);
							
					boolean result=model.deleteTravelGrade(bean);
					
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
					
					return "success";
				}
				
				/**
				 * following function is called to reset the fields.
				 * @return
				 */
				public String reset(){
					
					bean.setGradeCode("");
					bean.setGradeName("");
					bean.setDescription("");
					bean.setStatus("A");
					
					
					
					return "success";
				}
				
				/**
				 * following function is called to set the field values when a record is selected from the search window
				 * @throws Exception
				 */
				public String  getRecord() throws Exception{
					
					TravelGradeModel model=new TravelGradeModel();
					model.initiate(context, session);
					model.getTravelGrade(bean);
					bean.setSaveFlag(true);
					model.getRecords(bean,request);
					model.terminate();
					return "success";
				}
				
		public String report() throws Exception{
					
			TravelGradeModel model=new TravelGradeModel();
					model.initiate(context, session);
					String [] label={"Sr.No",getMessage("trv.grade"),getMessage("desc"),getMessage("status")};
					model.getReport(bean, request, response, context,label);
					bean.setSaveFlag(true);
					model.getRecords(bean,request);
					model.terminate();
					return null;
				}
				public String callPage1() throws Exception {
					getNavigationPanel(1);
					TravelGradeModel model=new TravelGradeModel();
					model.initiate(context, session);
					bean.setPageFlag(true);
					//bean.setOnLoadFlag("true");
					model.getRecords(bean,request);
					model.terminate();
					
					
					return "success";
				}
				public String callPage2() throws Exception {
					getNavigationPanel(1);
					TravelGradeModel model=new TravelGradeModel();
					model.initiate(context, session);
					model.getRecords(bean,request);
					bean.setPageFlag(true);
					model.terminate();
					
					
					return "success";
				}
				
				
				/**
				 * following function is called to display all the records when the link is clicked
				 * @return
				 * @throws Exception
				 */
				public String callPage() throws Exception {
					TravelGradeModel model=new TravelGradeModel();
					model.initiate(context, session);
					model.getRecords(bean,request);
					model.terminate();
					
					
					return "success";
				}
				
				/**
				 * following function is called when 
				 * @return
				 * @throws Exception
				 */
				public String calforedit() throws Exception {
					getNavigationPanel(2);
					logger.info("Calforedit inside indus ");
					TravelGradeModel model=new TravelGradeModel();
					model.initiate(context,session);
					model.getTravelGradeOnDoubleClick(bean);
					model.getTravelGrade(bean);
					model.getRecords(bean,request);
					bean.setDblFlag(true);
					bean.setModFlag(false);
					//bean.setFlag("true");
					model.terminate();
				
					return "success";
					
				
				}
				
				
				public String delete1() throws Exception{
					/*Default Method with save modeCode(2)*/
					getNavigationPanel(1);
					boolean result;
					TravelGradeModel model = new TravelGradeModel();
					model.initiate(context,session);
			        callPage2();
					String[] code=request.getParameterValues("hdeleteCode");
					result=model.delChkdRec(bean,code);
					if(result){
						prepare_withLoginProfileDetails();
						addActionMessage(getText("delMessage",""));
						model.getRecords(bean, request);
						reset();
						
					}
					else
						addActionMessage("multiple.del.error");
					
					return "success";
				}
				
				/**
				 * 	 
				 *  The Following Method is used to display Search Window to get Record to modify 
				 */

				
				public String f9Action() throws Exception {
					
					String query = " SELECT NVL(TRAVEL_GRADE_NAME,' '),DECODE(TRAVEL_GRADE_STATUS,'A','Active','D','Deactive'),TRAVEL_GRADE_ID  FROM HRMS_TMS_TRAVEL_GRADE  ORDER BY TRAVEL_GRADE_NAME";
					
					String []headers ={getMessage("trv.grade"),getMessage("status")};
					String []headerwidth={"20","20"};
					
					
					String fieldNames[]={"gradeName","Status","gradeCode",};
									
						int []columnIndex={0,1,2};
					
						String submitFlage ="true";
						
						String submitToMethod ="TravelGrade_details.action";
					
						setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
						
						return "f9page";
				}
				public String details() {
					getNavigationPanel(3);
					TravelGradeModel model = new TravelGradeModel();
					model.initiate(context, session);
					model.getTravelGradeRec(bean);
					bean.setSaveFlag(true);
					model.getRecords(bean,request);
					model.terminate();
					return "success";
				}

			}








