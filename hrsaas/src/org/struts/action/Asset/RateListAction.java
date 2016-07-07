/**
 * 
 */
package org.struts.action.Asset;

import org.paradyne.bean.Asset.RateList;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.Asset.RateListModel;
import org.paradyne.model.Asset.VendorMasterModel;
import org.paradyne.model.admin.master.QualificationModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author mangeshj
 *
 */
public class RateListAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	RateList ratelist;
	public void prepare_local() throws Exception {
		ratelist=new RateList();
		ratelist.setMenuCode(650);

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return ratelist;
	}

	public RateList getRatelist() {
		return ratelist;
	}
	
	public String input() throws Exception{
		RateListModel model=new RateListModel();
		model.initiate(context, session);
		model.showList(ratelist, request);
		model.terminate();
		getNavigationPanel(1);

		
		return INPUT;
	}
	
	public String addNew() {
		try {
			getNavigationPanel(2);
			ratelist.setVendorName("");
			ratelist.setVendorCode("");
			ratelist.setCityName("");
			return SUCCESS;
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	public String cancel() {
		try {
			reset();
			return input();
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	
	
	public String edit() {
		try {
			getNavigationPanel(2);
			RateListModel model=new RateListModel();
			model.initiate(context, session);
			model.editData(ratelist);
			model.terminate();
		 
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
		return SUCCESS;
	}

	/*
	 * method name : delete1 
	 * purpose : to delete the record selected by check on the Check Box 
	 * return type : String 
	 * parameter : none
	 */

	public String delete1() throws Exception {
		String code[] = request.getParameterValues("hdeleteCode");
		RateListModel model=new RateListModel();
		model.initiate(context, session);
		boolean result = model.deletecheckedRecords(ratelist, code);
		if (result) {
			addActionMessage(getText("delMessage", ""));
		}// end of if
		else {
			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
		}// end of else

		model.showList(ratelist, request);
		model.terminate();
		reset();
		
		return INPUT;

	}
	

	
public String f9action() throws Exception {
		
		String query = " SELECT DISTINCT RATELIST_VENDOR, VENDOR_NAME ,LOCATION_NAME ,RATELIST_CODE FROM HRMS_ASSET_RATELIST "
			+" INNER JOIN HRMS_VENDOR ON (HRMS_VENDOR.VENDOR_CODE=HRMS_ASSET_RATELIST.RATELIST_VENDOR and HRMS_VENDOR.VENDOR_TYPE='S')"
			+" INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=HRMS_VENDOR.VENDOR_CITY) "
			+" ORDER BY RATELIST_VENDOR ";
		
		String[] headers={getMessage("venCode"), getMessage("venName"),getMessage("city")};
		
		String[] headerWidth={"20", "50", "30"};
		
		
		String[] fieldNames={"vendorCode","vendorName","cityName","rateCode"};
		
		int[] columnIndex={0, 1, 2, 3};
		
		
		String submitFlag = "true";
		
		
		String submitToMethod="RateList_showRecord.action";
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
public String f9actionVendor() throws Exception {
		
		String query = " SELECT VENDOR_CODE, VENDOR_NAME,LOCATION_NAME FROM HRMS_VENDOR " 
			+" INNER JOIN HRMS_LOCATION ON (HRMS_LOCATION.LOCATION_CODE=HRMS_VENDOR.VENDOR_CITY) "	
			+" WHERE VENDOR_TYPE='S' AND VENDOR_ISACTIVE='Y' ORDER BY VENDOR_CODE ";
		
		String[] headers={getMessage("venCode"), getMessage("venName"),getMessage("city")};
		
		
		String[] headerWidth={"20", "50", "30"};
		
		
		String[] fieldNames={"vendorCode","vendorName","cityName"};
		
		int[] columnIndex={0, 1 , 2};
		
		
		String submitFlag = "true";
		
		
		String submitToMethod="RateList_showRecordForVendor.action";
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
public String f9actionSubType() throws Exception {
	
	
		String query = " SELECT ASSET_SUBTYPE_CODE, NVL(ASSET_SUBTYPE_NAME,''),UNIT_NAME FROM HRMS_ASSET_SUBTYPE " 
						+" INNER JOIN HRMS_UNIT_MASTER ON (HRMS_UNIT_MASTER.UNIT_CODE=HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_UNIT)"
						+" WHERE ASSET_SUBTYPE_ISACTIVE = 'Y' ORDER BY ASSET_SUBTYPE_CODE ";		
		
		
		String[] headers={getMessage("code"),getMessage("assets")};
		
		String[] headerWidth={"20", "80"};
		
		String[] fieldNames={"subTypeCode","subTypeName","unit"};
		
		
		int[] columnIndex={0, 1, 2};
		
		
		String submitFlag = "true";
		
		
		String submitToMethod="RateList_showList.action";
		
		
		setF9Window(query,headers,headerWidth,fieldNames,columnIndex,submitFlag,submitToMethod);
		
		return "f9page";
	}
	public void setRatelist(RateList ratelist) {
		this.ratelist = ratelist;
	}
	public String showRecord(){
		RateListModel model=new RateListModel();
		model.initiate(context, session);
		model.showRecord(ratelist);
		getNavigationPanel(3);
		ratelist.setEnableAll("N");
		model.terminate();
		return SUCCESS;
	}
	public String showRecordForVendor(){
		try {
			RateListModel model = new RateListModel();
			model.initiate(context, session);
			model.showRecordForVendor(ratelist);
			getNavigationPanel(2);
			ratelist.setEnableAll("Y");
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	public String addItem(){
		try {
			RateListModel model = new RateListModel();
			model.initiate(context, session);
			boolean result = false;
			String[] itemCode = request.getParameterValues("itemCodeIterator");
			String[] itemName = request.getParameterValues("itemIterator");
			String[] price = request.getParameterValues("priceIterator");
			String[] unit = request.getParameterValues("unitIterator");
			if (ratelist.getParaId().equals("")) {
				result = model.addItem(ratelist, itemCode, itemName, price,
						unit);
			} else {
				result = model.editItem(ratelist, itemCode, itemName, price,
						unit);
			}
			//model.showRecord(ratelist);
			if (!result) {
				addActionMessage("Item is already added in the list !");
				model.showList(ratelist, itemCode, itemName, price, unit);
			} else

				getNavigationPanel(2);
			resetnew();
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	public String showList(){
		try {
			String[] itemCode = request.getParameterValues("itemCodeIterator");
			String[] itemName = request.getParameterValues("itemIterator");
			String[] price = request.getParameterValues("priceIterator");
			String[] unit = request.getParameterValues("unitIterator");
			RateListModel model = new RateListModel();
			model.initiate(context, session);
			model.showList(ratelist, itemCode, itemName, price, unit);
			model.terminate();
			getNavigationPanel(2);
			ratelist.setEnableAll("Y");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	public String removeItem(){
		try {
			getNavigationPanel(2);
			RateListModel model = new RateListModel();
			model.initiate(context, session);
			String[] itemCode = request.getParameterValues("itemCodeIterator");
			String[] itemName = request.getParameterValues("itemIterator");
			String[] price = request.getParameterValues("priceIterator");
			String[] unit = request.getParameterValues("unitIterator");
			model.removeItem(ratelist, itemCode, itemName, price, unit);
			ratelist.setParaId("");
			model.terminate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}
	public String delete(){
		RateListModel model=new RateListModel();
		model.initiate(context, session);
		
		if(model.delete(ratelist)){
			addActionMessage(getMessage("delete"));
			resetVendor();
		}else{
			addActionMessage("This record is referenced in other resources.So can't delete.");
		}
		model.showList(ratelist, request);
		getNavigationPanel(1);
		model.terminate();
		return INPUT;
	}
	public String save(){
		boolean result=false;
		RateListModel model=new RateListModel();
		model.initiate(context, session);
		String []itemCode=request.getParameterValues("itemCodeIterator");
		String []price=  request.getParameterValues("priceIterator");
		if(ratelist.getRateCode().equals("")){
			result=model.saveItem(ratelist,itemCode,price);
			if(result){
				addActionMessage(getMessage("save"));
				//resetVendor();
			}else{
				addActionMessage("Record can't be saved.");
				//showList();
			}
		}else{
			result=model.updateItem(ratelist,itemCode,price);
			if(result){
				addActionMessage(getMessage("update"));
				//resetVendor();
			}else{
				addActionMessage("Record can't be updated.");
				//showList();
			}
		}
		
		 model.showRecord(ratelist);
		getNavigationPanel(3);
		model.terminate();
		return SUCCESS;
	}
	public String report(){
		RateListModel model=new RateListModel();
		model.initiate(context, session);
		model.getReport(request,response,ratelist);
		model.terminate();
		return null;
	}
	public String resetnew(){
		//ratelist.setVendorCode("");
		//ratelist.setVendorName("");
		//ratelist.setCityName("");
		ratelist.setSubTypeCode("");
		ratelist.setSubTypeName("");
		ratelist.setPrice("");
		ratelist.setParaId("");
		ratelist.setUnit("Unit");
		//ratelist.setRateCode("");
		getNavigationPanel(2);
		
		return SUCCESS;
	}
	public String reset(){
		ratelist.setVendorCode("");
		ratelist.setVendorName("");
		ratelist.setCityName("");
		ratelist.setSubTypeCode("");
		ratelist.setSubTypeName("");
		ratelist.setPrice("");
		ratelist.setParaId("");
		ratelist.setUnit("Unit");
		ratelist.setRateCode("");
		getNavigationPanel(2);
		
		return SUCCESS;
	}
	/*
	 * method name : callPage
	 * purpose : to displays the records in the form
	 * return type : String 
	 * parameter : none
	 */
	public String callPage() throws Exception {
		RateListModel model=new RateListModel();
		model.initiate(context, session);
		model.showList(ratelist, request);
		model.terminate();
		getNavigationPanel(1);

		
		return INPUT;

	}
	public String resetVendor(){
		ratelist.setSubTypeCode("");
		ratelist.setSubTypeName("");
		ratelist.setPrice("");
		ratelist.setVendorCode("");
		ratelist.setVendorName("");
		ratelist.setCityName("");
		ratelist.setRateCode("");
		ratelist.setParaId("");
		ratelist.setUnit("Unit");
		return SUCCESS;
	}

}
