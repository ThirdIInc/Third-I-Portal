package org.struts.action.vendor;


/** @ Author : Archana Salunkhe
 * @ purpose : Developed to define Vendor Reporting Structure 
 * @ Date : 03-May-2012
 */
import org.paradyne.bean.vendor.VendorReportStructure;
import org.paradyne.model.vendor.VendorReportStructureModel;
import org.struts.lib.ParaActionSupport;

public class VendorReportStructureAction  extends ParaActionSupport{
	private static final long serialVersionUID = 1L;
	
	VendorReportStructure vendorReport;
	
	public void prepare_local() throws Exception {
		vendorReport = new VendorReportStructure();
		vendorReport.setMenuCode(2307);	
	}

	public Object getModel() {		
		return vendorReport;
	}

	public VendorReportStructure getVendorReport() {
		return vendorReport;
	}

	public void setVendorReport(VendorReportStructure vendorReport) {
		this.vendorReport = vendorReport;
	}

	public String input() throws Exception{
		VendorReportStructureModel model = new VendorReportStructureModel();
		model.initiate(context, session);
		model.getPartnerReportList(vendorReport,request);
		model.terminate();
		return SUCCESS;
	}
	
	public String addNew(){
		try {
			VendorReportStructureModel model = new VendorReportStructureModel();
			model.initiate(context, session);			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "reportStructure";
	}
	
	/** To reset records 
	 * @ return String
	 */
	public String reset(){
		try{
			vendorReport.setReportingID("");
			vendorReport.setPartnerName("");
			vendorReport.setPartnerCode("");
			vendorReport.setApproverCode("");
			vendorReport.setApproverName("");
			vendorReport.setApproverType("");
			vendorReport.setPartnerCode("");
			vendorReport.setLevel("");
			vendorReport.setApproverToken("");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "reportStructure";
	}
	

	/**To edit any record in the list by double clicking on it
	 * @ return String
	 */
	public String callForEdit() throws Exception {		
		try {
			VendorReportStructureModel model = new VendorReportStructureModel();
			model.initiate(context, session);
			model.callForEdit(vendorReport);
			model.terminate();
		} catch (Exception e) {
		e.printStackTrace();
		}
		return "reportStructure";
	}
	
	public String f9partner() throws Exception {
		
		String query = "SELECT PARTNER_LOGIN_CODE, PARTNER_NAME FROM VENDOR_INFO ORDER BY PARTNER_LOGIN_CODE";
		String[] headers = {getMessage("partnerID"), getMessage("partnerName")};
		String[] headerWidth = { "50","50"};
		String[] fieldNames = { "partnerCode","partnerName"};
		int[] columnIndex = {0, 1};
		String submitFlag = "false";
		String submitToMethod = "";
		vendorReport.setMasterMenuCode(2303);
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
	
	/**To Save record 
	 * @ return String
	 */
	public String save(){
		
		boolean result = false;
		try {			
			VendorReportStructureModel model = new VendorReportStructureModel();
			model.initiate(context, session);
			if (vendorReport.getReportingID().equals("")) { //Insert new record 
				result = model.save(request,vendorReport);
				if (result) {					
						addActionMessage("Application Saved Successfully.");
						prepare_withLoginProfileDetails();
						input();
						return input();
				}
				else {
					addActionMessage(getMessage("save.error"));
				}
			}
			else {
				result = model.update(vendorReport);
				if (result) {
					addActionMessage("Record Updated Successfully.");
				}
				else
					addActionMessage("Record cannot Updated");
			}
				
			
			model.terminate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "reportStructure";
	}
	
	public String f9action() throws Exception {
		
		String query = "SELECT EMP_TOKEN, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
				+ "  EMP_ID FROM HRMS_EMP_OFFC";
		String[] headers = {getMessage("approverToken"), getMessage("approverNm")};
		String[] headerWidth = { "15", "35" };
		String[] fieldNames = { "approverToken", "approverName","approverCode" };
		int[] columnIndex = { 0, 1, 2 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
}
