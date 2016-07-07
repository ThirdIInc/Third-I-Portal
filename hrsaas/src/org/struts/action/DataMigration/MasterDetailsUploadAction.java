package org.struts.action.DataMigration;

import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.model.DataMigration.MasterDetailsUploadModel;
import org.struts.lib.ParaActionSupport;

public class MasterDetailsUploadAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MasterDetailsUploadAction.class);
	EmpDetailsUpload empDetails;

	public void downloadTemplate() {
		try {
			String templateName = request.getParameter("templateName");
			String dataPath = getText("data_path");
			String filePath = dataPath + "/DataMigration/Templates/" + templateName;
			MigrateExcelData.openTemplate(request, response, templateName, filePath);
		} catch(Exception e) {
			logger.error("Exception in downloadTemplate in action:" + e);
		}
	}

	public EmpDetailsUpload getEmpDetails() {
		return empDetails;
	}

	public Object getModel() {
		return empDetails;
	}

	public String input() throws Exception {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "/DataMigration/" + poolName + "/";
		empDetails.setDataPath(dataPath);
		return SUCCESS;
	}

	public void prepare_local() throws Exception {
		empDetails = new EmpDetailsUpload();
		empDetails.setMenuCode(1034);
	}

	public String reset() {
		try {
			empDetails.setUploadFileName("");
			empDetails.setUploadName("");
			empDetails.setStatus("Fail");
			empDetails.setNote("");
		} catch(Exception e) {
			logger.error("Exception in reset in action:" + e);
		}
		return SUCCESS;
	}

	public void setEmpDetails(EmpDetailsUpload empDetails) {
		this.empDetails = empDetails;
	}

	public String uploadAssetCategoryDtls() {
		try {
			MasterDetailsUploadModel model = new MasterDetailsUploadModel();
			model.initiate(context, session);
			model.uploadAssetCtgryDtls(response, request, empDetails);
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in uploadAssetCategoryDtls in action:" + e);
		}

		return SUCCESS;
	}

	public String uploadAssetSubTypeDtls() {
		try {
			MasterDetailsUploadModel model = new MasterDetailsUploadModel();
			model.initiate(context, session);
			model.uploadAssetSubTypDtls(response, request, empDetails);
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in uploadAssetSubTypeDtls in action:" + e);
		}

		return SUCCESS;
	}

	public String uploadBranchHoliday() {
		try {
			MasterDetailsUploadModel model = new MasterDetailsUploadModel();
			model.initiate(context, session);
			model.uploadBranchHoliday(empDetails);
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in uploadBranchHoliday in action:" + e);
		}

		return SUCCESS;
	}

	public String uploadBranchMasterDtls() {
		try {
			MasterDetailsUploadModel model = new MasterDetailsUploadModel();
			model.initiate(context, session);
			model.uploadBranchDtls(response, request, empDetails);
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in uploadBranchMasterDtls in action:" + e);
		}

		return SUCCESS;
	}

	public String uploadDeptMasterDtls() {
		try {
			MasterDetailsUploadModel model = new MasterDetailsUploadModel();
			model.initiate(context, session);
			model.uploadDeptDtls(response, request, empDetails);
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in uploadDeptMasterDtls in action:" + e);
		}

		return SUCCESS;
	}

	public String uploadDesgnMasterDtls() {
		try {
			MasterDetailsUploadModel model = new MasterDetailsUploadModel();
			model.initiate(context, session);
			model.uploadDesgnDtls(response, request, empDetails);
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in uploadDesgnMasterDtls in action:" + e);
		}

		return SUCCESS;
	}

	public String uploadHolidayMaster() {
		try {
			MasterDetailsUploadModel model = new MasterDetailsUploadModel();
			model.initiate(context, session);
			model.uploadHolidayMaster(empDetails);
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in uploadHolidayMaster in action:" + e);
		}

		return SUCCESS;
	}

	public void viewUploadedFile() {
		try {
			String uploadFileName = request.getParameter("uploadFileName");
			String dataPath = request.getParameter("dataPath");

			MigrateExcelData.viewUploadedFile(uploadFileName, dataPath, response);
		} catch(Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}
}