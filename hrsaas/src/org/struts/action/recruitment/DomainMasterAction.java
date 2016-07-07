package org.struts.action.recruitment;
import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.Recruitment.DomainMaster;
import org.paradyne.model.recruitment.DomainMasterModel;

/**
 * Date:05-01-2009
 * @author Pradeep
 *  modified by Prasad 
 */

public class DomainMasterAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger
	(org.struts.action.recruitment.DomainMasterAction.class); 
	
	DomainMaster bean;
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		
		bean = new DomainMaster();
		bean.setMenuCode(741);

	}
	@Override
	public void prepare_withLoginProfileDetails() throws Exception {
		//callPage();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return bean;
	}
	public DomainMaster getBean() {
		return bean;
	}
	public void setBean(DomainMaster bean) {
		this.bean = bean;
	}
	
	/**
	 * following function is called when Add New button is clicked.
	 * @return
	 */
	public String addNew(){
		getNavigationPanel(2);
		DomainMasterModel model=new DomainMasterModel();
		model.initiate(context, session);
		model.getRecords(bean, request);
		bean.setCancelFlg("false");
		model.terminate();
		return SUCCESS;
	}
	
	public String input() throws Exception{
		getNavigationPanel(1);
		DomainMasterModel model=new DomainMasterModel();
		model.initiate(context, session);
		bean.setDomainStatus("");
		bean.setFlagShow("true");
		callPage();
		model.terminate();
		return "view";
	}
	
	public String add() throws Exception{
		DomainMasterModel model=new DomainMasterModel();
		model.initiate(context,session);
		callPage();
		bean.setFlag("true");
		
		//System.out.println("val of flag"+bean.getFlag());
		model.terminate();
		return "success";
		
	}
	
	public String edit() throws Exception{
		getNavigationPanel(2);
		DomainMasterModel model=new DomainMasterModel();
		model.initiate(context, session);
		model.getDom(bean);
		model.getRecords(bean, request);
		logger.info("******Inside Edit After Call Page and Before Setting EditFlag*******");
		bean.setCancelFlg("true");
		
		model.terminate();
		return "success";
	}
	public String cancelSecond() throws Exception{
		getNavigationPanel(1);
		DomainMasterModel model=new DomainMasterModel();
		model.initiate(context, session);
		model.getRecords(bean, request);
		bean.setFlagShow("true");
		reset();
		model.terminate();
		return "view";
	}
	
	public String cancelFirst() throws Exception{
		int i;
		String str;
		DomainMasterModel model=new DomainMasterModel();
		model.initiate(context, session);
		if(bean.getCancelFlg().equals("true")){
			bean.setFlagView("true");
			model.getDom(bean);
			model.getRecords(bean, request);
			i=3;
			str="view";
		}else{
				
			i=1;
			model.getRecords(bean, request);
			bean.setFlagShow("true");
			reset();
			str="view";
		}
		
	/*	if(callingStage.equals("first")){
			callPage();
			bean.setOnLoadFlag("true");
		}else if(callingStage.equals("second")){
			callPage();
			bean.setOnLoadFlag("true");
		}else if(callingStage.equals("third")){
			model.getRecords(bean, request);
			//bean.setOnLoadFlag("true");
			bean.setSaveFlag("true");
			bean.setUpFlag("true");
			//bean.setFlag("true");
		
			
			model.getDomRec(bean);
		}else if(callingStage.equals("fourth")){
			callPage();
			bean.setOnLoadFlag("true");
		}*/
		getNavigationPanel(i);
		model.terminate();
		return str;
	}
	

	
	/**
	 * following function is called when add new record is clicked in the jsp page
	 * @return
	 */
	public String save() throws Exception{
	int i;
	String str;
		String result="";
		
		DomainMasterModel model=new DomainMasterModel();
		model.initiate(context, session);
		
		if(bean.getDomainCode().equals("")){
			result=model.addDom(bean);
			
			if(result.equals("saved")){
				bean.setFlagView("true");
				addActionMessage(getText("Record saved successfully!"));
				
				i=3;
				str="view";
				
			}
			else if(result.equals("duplicate")) 
			{
				addActionMessage(getText("Duplicate entry found!"));
				bean.setCancelFlg("false");
				i=2;
				str="success";
			
			}
			else
			{
				addActionMessage("Racord can not be saved!");
				bean.setCancelFlg("false");
				i=2;
				str="success";
			}
		}
		else{
			result=model.modDom(bean);
			
			if(result.equals("modified")){
				bean.setFlagView("true");
				addActionMessage(getText("Record updated successfully!"));
				
				i=3;
				str="view";
			}
			else if(result.equals("duplicate"))
			{
				addActionMessage("Duplicate entry found!");
				bean.setCancelFlg("false");
				i=2;
				str="success";
			}
			else
			{
				addActionMessage(getText("Record can not be updated!"));
				bean.setCancelFlg("false");
				i=2;
				str="success";
			}
		}
		
		
		model.getRecords(bean, request);	
		model.terminate();
		getNavigationPanel(i);
		return str;
	}
	
	/**
	 * following function is called when update button is clicked in the jsp page  
	 * @return
	 */
	public String update() throws Exception{
		String result="";
		DomainMasterModel model=new DomainMasterModel();
		model.initiate(context, session);
		result=model.modDom(bean);
		
		if(result.equals("modified")){
			addActionMessage(getText("Record updated successfully!"));
			
			bean.setSaveFlag("true");
		}
		else if(result.equals("duplicate"))
		{
			addActionMessage("Duplicate entry found!");
			bean.setFlag("true");
		}
		else
		{
			addActionMessage(getText("Record can not be updated!"));
			bean.setFlag("true");
		}
		model.getRecords(bean, request);
		//bean.setFlag("true");
		//bean.setOnLoadFlag("false");
		bean.setUpFlag("true");
		model.terminate();
		
		return "success";
	}
	
	/**
	 * following function is called when delete button is clicked in the jsp page
	 * @return
	 */
	public String delete() throws Exception{
		getNavigationPanel(1);
		DomainMasterModel model=new DomainMasterModel();
		model.initiate(context, session);
		boolean result=model.deleteDom(bean);
		if(result){
			addActionMessage(getText("Record deleted successfully!"));
			callPage();
		}else{
			addActionMessage(getText("This record is referenced in other resources.\no can't be deleted!"));
		}
		
		bean.setFlagShow("true");
		
		model.terminate();
		
		return "view";
	}
	
	/**
	 * following function is called to reset the fields.
	 * @return
	 */
	public String reset(){
		DomainMasterModel model=new DomainMasterModel();
		
		model.initiate(context,session);
		bean.setDomainCode("");
		bean.setDomainName("");
		bean.setDomAbbr("");
		bean.setDomDesc("");
		bean.setDomainStatus("");
		bean.setFlagView("false");
		model.terminate();
		return "success";
	}
	
	/**
	 * following function is called to set the field values when a record is selected from the search window
	 * @throws Exception
	 */
	public String  getRecord() throws Exception{
		getNavigationPanel(3);
		DomainMasterModel model=new DomainMasterModel();
		model.initiate(context, session);
		model.getDom(bean);
	/*	bean.setSaveFlag("true");
		bean.setUpFlag("true");*/
		bean.setFlagView("true");
		model.getRecords(bean,request);
		model.terminate();
		return "view";
	}
	
	
	
	
	public String callPage1() throws Exception {
		DomainMasterModel model=new DomainMasterModel();
		model.initiate(context, session);
		bean.setPageFlag("true");
		//bean.setOnLoadFlag("true");
		model.getRecords(bean,request);
		model.terminate();
		reset();	
		return "success";
	}
	public String callPage2() throws Exception {
		DomainMasterModel model=new DomainMasterModel();
		model.initiate(context, session);
		model.getRecords(bean,request);
		bean.setPageFlag("true");
		model.terminate();
		reset();
		return "success";
	}
	
	
	/**
	 * following function is called to display all the records when the link is clicked
	 * @return
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		getNavigationPanel(1);
		DomainMasterModel model=new DomainMasterModel();
		model.initiate(context, session);
		model.getRecords(bean,request);
		bean.setFlagShow("true");
		model.terminate();
		reset();
		return "view";
	}
	
	/**
	 * following function is called when 
	 * @return
	 * @throws Exception
	 */
	public String calforedit() throws Exception {
		
		getNavigationPanel(3);
		DomainMasterModel model=new DomainMasterModel();
		model.initiate(context,session);
		model.getDomOnDoubleClick(bean);
		model.getRecords(bean,request);
		bean.setCancelFlg("false");
		bean.setFlagView("true");
		model.terminate();
		
		return "view";
		
	
	}
	
	
	public String delete1() throws Exception{
		/*Default Method with save modeCode(2)*/
		getNavigationPanel(1);
		
		DomainMasterModel model = new DomainMasterModel();
		model.initiate(context,session);
		String[] code=request.getParameterValues("hdeleteCode");
		boolean result=model.delChkdRec(bean,code);
		
		if(result){
			addActionMessage(getText("delMessage",""));
		}
		else{
			addActionMessage("One or more records can not be deleted \n as they are associated with some other records");
		}
		bean.setOnLoadFlag("true");
		model.getRecords(bean, request);
		bean.setFlagShow("true");
		
		model.terminate();
		reset();
		return "view";
	}
	
	
public String f9Domain() throws Exception {
		
		
		String query = " SELECT FUNC_DOMAIN_NAME,FUNC_DOMAIN_ABBR,DECODE(FUNC_DOMAIN_STATUS,'A','Active','D','Deactive'),FUNC_DOMAIN_CODE FROM HRMS_FUNC_DOMAIN_MASTER  ORDER BY FUNC_DOMAIN_CODE";
		
		String []headers ={getMessage("dom.name"),getMessage("dom.abbr"),getMessage("dom.stat")};
		String []headerwidth={"20","20","20"};
			
			
		String fieldNames[]={"domainName","domAbbr","domainStatus","domainCode"};
		
			int []columnIndex={0,1,2,3};
		
			String submitFlage ="true";
			
			String submitToMethod ="DomainMaster_getRecord.action";
		
			setF9Window(query,headers,headerwidth,fieldNames,columnIndex,submitFlage,submitToMethod);
			
			return "f9page";
	}
/**
 * following function is called to get the Pdf report for list of Domain records
 * 
 */
public String report(){
	getNavigationPanel(3);
	DomainMasterModel model = new DomainMasterModel();	
	model.initiate(context,session);
	String[]label={"Sr.No",getMessage("dom.name"),getMessage("dom.abbr"),getMessage("dom.stat")};
	model.generateReport(bean,response,label);
	model.terminate();
	return null;
}

}
