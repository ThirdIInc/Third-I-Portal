/**
 * 
 */
package org.struts.action.voucher;
import org.apache.log4j.Logger;
import org.paradyne.bean.voucher.VoucherUpload;
import org.paradyne.model.voucher.VoucherUploadModel;
import org.struts.action.DataMigration.MigrateExcelData;
import org.struts.lib.ParaActionSupport;

/**
 * @author anantha.lakshmi
 *
 */
public class VoucherUploadAction extends ParaActionSupport {
	VoucherUpload voucherUpload=null;
	
	static Logger logger = Logger.getLogger(VoucherUploadAction.class);
	
	public void prepare_local() throws Exception {
		voucherUpload = new VoucherUpload();
		voucherUpload.setMenuCode(2042);
	}

	public Object getModel() {
		return voucherUpload;
	}
	public String input() throws Exception {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "\\Cash Voucher\\" + poolName + "\\";
		voucherUpload.setDataPath(dataPath);
		
		return SUCCESS;
	}
	public void downloadTemplate() {
		try {
			String templateName = request.getParameter("templateName");
			String dataPath = getText("data_path");
			String filePath = dataPath + "\\DataMigration\\Templates\\" + templateName;
			MigrateExcelData.openTemplate(request, response, templateName, filePath);
		} catch(Exception e) {
			logger.error("Exception in downloadTemplate in action:" + e);
		}
	}
	public String uploadVoucherMasterDetails() throws Exception{
		VoucherUploadModel model =new VoucherUploadModel();
		model.initiate(context, session);
		model.uploadVoucherMasterTemplate(response,request,voucherUpload);
		model.terminate();
		
		return SUCCESS;
	}
	public void viewUploadedFile() {
		try {
			String uploadFileName = request.getParameter("uploadFileName");
			//System.out.println("uploadFileName==>"+uploadFileName);
			String dataPath = request.getParameter("dataPath");
			System.out.println("dataPath==>"+dataPath);
			MigrateExcelData.viewUploadedFile(uploadFileName, dataPath, response);
		} catch(Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}
	public String f9Division() {
		try {
			String query = " SELECT DIV_NAME, DIV_ID FROM HRMS_DIVISION ";
			if(voucherUpload.getUserProfileDivision() != null && voucherUpload.getUserProfileDivision().length() > 0) {
				query += " WHERE DIV_ID IN(" + voucherUpload.getUserProfileDivision() + ") ";
			}
			query += " ORDER BY UPPER(DIV_NAME) ";

			String[] headers = {"Division"};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divisionName", "divisionId"};

			int[] columnIndex = {0, 1};

			String submitFlag = "false";

			String submitToMethod = "";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

			return "f9page";
		} catch(Exception e) {
			logger.error("Exception in f9Division:" + e);
			return "";
		}
	}
	public void vouhcerUploadStmtData() {
		try {
			VoucherUploadModel vm=new VoucherUploadModel();
			vm.initiate(context, session);
			String hidDivId = request.getParameter("divisionId");
			vm.vouherUploadStatementData(voucherUpload,response,hidDivId);
			vm.terminate();
		} catch(Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}
	public void deleteVoucherUploadData() {
		try {
			VoucherUploadModel vm=new VoucherUploadModel();
			vm.initiate(context, session);
			String hidDivId = request.getParameter("divisionId");
			vm.deleteVouherUploadData(voucherUpload,response,hidDivId);
			vm.terminate();
		} catch(Exception e) {
			logger.error("Exception in delete in action:" + e);
		}
	}
	public String reset() {
		try {
			voucherUpload.setUploadFileName("");
			voucherUpload.setUploadName("");
			voucherUpload.setStatus("Fail");
			voucherUpload.setNote("");
			voucherUpload.setDivisionName("");
			voucherUpload.setFrmDate("");
			voucherUpload.setToDate("");
		} catch(Exception e) {
			logger.error("Exception in reset in action:" + e);
		}
		return SUCCESS;
	}
}
