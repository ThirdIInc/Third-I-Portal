/*Author Anantha lakshmi*/
package org.struts.action.DataMigration;
import org.apache.log4j.Logger;
import org.paradyne.bean.DataMigration.CandidateDetailsUpload;
import org.paradyne.model.DataMigration.CandidateDetailsBulkUpload;

import org.struts.lib.ParaActionSupport;

public class CandidateDetailsUploadAction extends ParaActionSupport {
	static Logger logger = Logger.getLogger(CandidateDetailsUploadAction.class);
	CandidateDetailsUpload cndDetails;

	public void downloadTemplate() {
		try {
			String templateName = request.getParameter("templateName");
			String dataPath = getText("data_path");
			String filePath = dataPath + "/DataMigration/Templates/"+ templateName;
			MigrateExcelData.openTemplate(request, response, templateName,filePath);
		} catch (Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}

	public CandidateDetailsUpload getEmpDetails() {
		return cndDetails;
	}

	public Object getModel() {
		return cndDetails;
	}

	@Override
	public String input() throws Exception {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "/DataMigration/" + poolName
				+ "/";
		cndDetails.setDataPath(dataPath);

		return SUCCESS;
	}

	public void prepare_local() throws Exception {
		cndDetails = new CandidateDetailsUpload();
		cndDetails.setMenuCode(2117);
	}

	public void setEmpDetails(CandidateDetailsUpload empDetails) {
		this.cndDetails = empDetails;
	}

	public String uploadCandidateDetails() throws Exception {
		CandidateDetailsBulkUpload model = new CandidateDetailsBulkUpload();
		model.initiate(context, session);
		model.uploadTemplate(response, request,cndDetails);
		model.terminate();
		return SUCCESS;
	}

	
	public void viewUploadedFile() {
		try {
			String uploadFileName = request.getParameter("uploadFileName");
			String dataPath = request.getParameter("dataPath");
			MigrateExcelData.viewUploadedFile(uploadFileName, dataPath,response);
		} catch (Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}


	public String reset() {
		try {
			cndDetails.setUploadFileName("");
			cndDetails.setUploadName("");
			cndDetails.setStatus("Fail");
			cndDetails.setNote("");
		} catch (Exception e) {
			logger.error("Exception in reset in action:" + e);
		}
		return SUCCESS;
	}
}