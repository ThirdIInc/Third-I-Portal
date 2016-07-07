package org.struts.action.voucher;

import org.paradyne.bean.voucher.VoucherMaster;
import org.paradyne.model.voucher.VoucherMasterModel;
import org.struts.lib.ParaActionSupport;

public class VoucherMasterAction extends ParaActionSupport {
	VoucherMaster voucher;

	
	public Object getModel() {
		
		return voucher;
	}

	public VoucherMaster getVoucher() {
		return voucher;
	}

	public void setVoucher(VoucherMaster voucher) {
		this.voucher = voucher;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	public void prepare_local() throws Exception {		
		voucher=new VoucherMaster();
		voucher.setMenuCode(388);
		voucher.setEnableAll("N");
	}
	
	public void prepare_withLoginProfileDetails() throws Exception {
		VoucherMasterModel model = new VoucherMasterModel();	
				model.initiate(context,session);
				model.Data(voucher,request);
				model.terminate();
		}
	
	
	public String input() throws Exception {
		voucher.setEnableAll("N");
		getNavigationPanel(1);
		return SUCCESS;
	}
	
	public String reset()throws Exception {
		logger.info("In reset1");
			voucher.setVoucherCode("");
			voucher.setVoucherHead("");
			voucher.setHiddencode("");
			voucher.setHdeleteCode("");
			logger.info("In reset2");
			getNavigationPanel(2);
			return "Data";
		}
		
	
	public String save()
	{
		VoucherMasterModel model = new VoucherMasterModel();
		model.initiate(context,session);
		/*if(voucher.getVoucherCode().equals(""))
		{
			 
			boolean flag =model.addVoucherPara(voucher);
			if(flag==true)
			{
				addActionMessage("Record Saved Sucessfully");
			}else{
				addActionMessage("voucher Head Already Existed.");
			}
		}else
		{
			
			logger.info("INSIDE THE ELSE");
		
			boolean flag =model.modVoucherPara(voucher);
			
			if(flag==true)
			{
				
				addActionMessage("Record modified Sucessfully");
			}
			else{
			
				addActionMessage("voucher Head Already Existed..");
			}
			
		}*/
		String str="";
		
		if(voucher.getVoucherCode().equals(""))
		{		
				//  write the action function  like this ....!  model.function(passbean as argument);
				
			 str =model.addVoucher(voucher);
			 getNavigationPanel(3);
		}
		else
		{
		
			 str = model.modVoucher(voucher);			
			 getNavigationPanel(3);
		}
		model.Data(voucher,request);
		model.terminate();
		addActionMessage(getText(str));
		//voucher.setVoucherCode("");
		//voucher.setVoucherHead("");
		model.terminate();
		//getNavigationPanel(1);
		return "Data";
	}
	
	public String delete() throws Exception
	{
	logger.info("in delete");
	VoucherMasterModel model = new VoucherMasterModel();
	
	model.initiate(context,session);
	boolean result ;
	logger.info("Before deletequalification");
	result = model.deleteVoucher(voucher);
	logger.info("After delete");
	if(result)
	{
		addActionMessage("Record Deleted Successfully.");
		reset();
			
	}
	else
	{
		addActionMessage("This record is referenced in other resources.So cannot delete.!");
		reset();
	}
	model.Data(voucher,request);
	model.terminate();
	getNavigationPanel(1);
	return "success";
	}


	/*public String report() throws Exception 
	{
		VoucherMasterModel model = new VoucherMasterModel();
		logger.info("In report");
		model.initiate(context,session);
		model.getVoucherParaReport(voucher);
		model.terminate();
		return "report";
		
	}*/
	
	public String report()throws Exception 
	{
		VoucherMasterModel model = new VoucherMasterModel();
	 model.initiate(context,session);			
	 String[] headers = {"Sr.No",getMessage("voucher.head")};
	 model.getReport(voucher,request,response,headers );
	// model.getReport(model,request,response,context,session);
	 model.terminate();	
	 return null;    
	}
	

	public String VoucherParaRecord()throws Exception
	{	
		logger.info("I am Bonus ParaRecord");
		VoucherMasterModel model = new VoucherMasterModel();
		model.initiate(context,session);
		model.getVoucherRecord(voucher);
		logger.info("I am Voucher ParaRecord1");
		
		model.terminate();
		logger.info("I am Voucher ParaRecord2");
		
		return "success";
	}
		
	public String f9action() throws Exception 
	{
	
	logger.info("in f9 action");
	
	String query = "SELECT VCH_CODE,VCH_NAME FROM HRMS_VCH_HD order by VCH_CODE ";		
	
	logger.info("in f9 action1");
	String[] headers={"Voucher Code", getMessage("voucher.head")};
	
	String[] headerWidth={"20", "80"};
	logger.info("in f9 action2");
	
	String[] fieldNames={"voucherCode","voucherHead"};
	
	
	int[] columnIndex={0,1};
	
	String submitFlag = "true";
	logger.info("in f9 action4");
	
	String submitToMethod="VoucherMaster_data.action";
	logger.info("in f9 action5");
	
	
	setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
	logger.info("in f9 action6");
	return "f9page";
	}

	
	
		/*public String callPage() throws Exception {
			
			VoucherMasterModel model = new VoucherMasterModel();
				model.initiate(context,session);
				model.Data(voucher,request);
				model.terminate();
			 return SUCCESS;
			
			}
		
		public String calforedit() throws Exception{
			VoucherMasterModel model = new VoucherMasterModel();
			model.initiate(context,session);
			model.callForEdit(voucher);
			model.Data(voucher,request);
			model.terminate();
			return "success";
		}
		*/
	
	
	public String deleteCheckedRecords()throws Exception {
		String code[]=request.getParameterValues("hdeleteCode");
		
		VoucherMasterModel model = new VoucherMasterModel();
		
		model.initiate(context,session);
		boolean result =model.deleteCheckedRecords(voucher,code);
		
			if(result) {
				addActionMessage(getText("delMessage",""));
			} else {
				addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
			}
		

		
		model.Data(voucher,request);
		voucher.setVoucherCode("");
		voucher.setVoucherHead("");
		voucher.setHiddencode("");
		voucher.setHdeleteCode("");		
		model.terminate();
		getNavigationPanel(1);		
		return SUCCESS;

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

	public String addNew() {
		try {
			getNavigationPanel(2);
			return "Data";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	public String calforedit() throws Exception {
		VoucherMasterModel model = new VoucherMasterModel();
		model.initiate(context, session);
		model.callForEdit(voucher);		
		getNavigationPanel(3);
		voucher.setEnableAll("N");
		model.terminate();
		return "Data";
	}

	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		VoucherMasterModel model = new VoucherMasterModel();
		model.initiate(context, session);	
		model.Data(voucher,request);
		getNavigationPanel(1);
		model.terminate();
		return SUCCESS;
	}
	
	/**
	 * To set the fields after search
	 * @return String
	 * @throws Exception
	 */
	public String data() throws Exception {
		VoucherMasterModel model = new VoucherMasterModel();
		model.initiate(context, session);		
		getNavigationPanel(3);
		model.terminate();
		return "Data";
	}

	
	
	
	/**
	 * To delete any record
	 * @return String
	 * @throws Exception
	 */
	/*public String delete() throws Exception {
		VoucherMasterModel model = new VoucherMasterModel();
		model.initiate(context, session);
		
		boolean result=model.delete(voucher);
		model.Data(voucher,request);
		model.terminate();
		

		if(result) {
			addActionMessage(getMessage("delete"));

		} else {
			addActionMessage(getMessage("del.error"));
		}// end of else
		
		
		getNavigationPanel(1);
	
		return "success";
		
	}*/

	/**
	 * To delete one or more records from the list
	 * @return String
	 * @throws Exception
	 */
	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");

		VoucherMasterModel model = new VoucherMasterModel();

		model.initiate(context, session);
		boolean result =false;
			result=model.deleteCheckedRecords(voucher,code);
		
		if(result) {
			addActionMessage(getMessage("delete"));
		//	reset();
		} else {
			addActionMessage(getMessage("multiple.del.error"));
		}// end of else

		model.Data(voucher,request);
		model.terminate();
		getNavigationPanel(1);
		
		
		return "success";
	}


}