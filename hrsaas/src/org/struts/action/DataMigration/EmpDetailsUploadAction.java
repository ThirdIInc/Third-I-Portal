/* Prakash Shetkar April 20, 2010 */

package org.struts.action.DataMigration;

import org.apache.log4j.Logger;
import org.paradyne.bean.DataMigration.EmpDetailsUpload;
import org.paradyne.model.DataMigration.AddressDetailsBulkUpload;
import org.paradyne.model.DataMigration.EmpCreditBulkUploadModel;
import org.paradyne.model.DataMigration.EmpQualBulkUploadModel;
import org.paradyne.model.DataMigration.FamilyBulkUploadModel;
import org.paradyne.model.DataMigration.LeaveBalanceBulkUploadModel;
import org.paradyne.model.DataMigration.OfficialBulkUploadModel;
import org.paradyne.model.DataMigration.PersonalBulkUploadModel;
import org.paradyne.model.DataMigration.ExperienceBulkUploadModel;
import org.paradyne.model.DataMigration.HouseRentBulkUploadModel;
import org.paradyne.model.DataMigration.PostingDetailsBulkUploadModel;
import org.paradyne.model.DataMigration.IncrementBulkUploadModel;
import org.paradyne.model.DataMigration.PromotionBulkUploadModel;
import org.struts.lib.ParaActionSupport;

public class EmpDetailsUploadAction extends ParaActionSupport {
	static Logger logger = Logger.getLogger(EmpDetailsUploadAction.class);
	EmpDetailsUpload empDetails;

	public void downloadTemplate() {
		try {
			String templateName = request.getParameter("templateName");
			String dataPath = getText("data_path");
			String filePath = dataPath + "/DataMigration/Templates/"
					+ templateName;
			MigrateExcelData.openTemplate(request, response, templateName,
					filePath);
		} catch (Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}

	public EmpDetailsUpload getEmpDetails() {
		return empDetails;
	}

	public Object getModel() {
		return empDetails;
	}

	@Override
	public String input() throws Exception {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "/DataMigration/" + poolName
				+ "/";
		empDetails.setDataPath(dataPath);

		return SUCCESS;
	}

	public void prepare_local() throws Exception {
		empDetails = new EmpDetailsUpload();
		empDetails.setMenuCode(1032);
	}

	public void setEmpDetails(EmpDetailsUpload empDetails) {
		this.empDetails = empDetails;
	}

	public String uploadAddress() throws Exception {
		AddressDetailsBulkUpload model = new AddressDetailsBulkUpload();
		model.initiate(context, session);
		model.uploadTemplate(response, request, empDetails);
		model.terminate();

		return SUCCESS;
	}

	public String uploadEmpCredit() throws Exception {
		EmpCreditBulkUploadModel model = new EmpCreditBulkUploadModel();
		model.initiate(context, session);
		model.uploadTemplate(response, request, empDetails);
		model.terminate();

		return SUCCESS;
	}

	/**
	 * @author Reeba_Joseph
	 * @return
	 * @throws Exception
	 */
	public String uploadEmpDebit() throws Exception {
		EmpCreditBulkUploadModel model = new EmpCreditBulkUploadModel();
		model.initiate(context, session);
		model.uploadDebitTemplate(response, request, empDetails);
		model.terminate();

		return SUCCESS;
	}

	public String uploadLeaveDtl() {
		try {
			LeaveBalanceBulkUploadModel model = new LeaveBalanceBulkUploadModel();
			model.initiate(context, session);
			model.uploadLeaveDtlTemplate(response, request, empDetails);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in uploadLeaveDtl in action:" + e);
		}
		return SUCCESS;
	}

	
	
	public String uploadHouseRentDtl() {
		try {
			HouseRentBulkUploadModel model = new HouseRentBulkUploadModel();
			model.initiate(context, session);
			model.uploadHouseRentDtlTemplate(response, request, empDetails);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in uploadExperienceDtl in action:" + e);
		}
		return SUCCESS;
	}

	public String uploadExperienceDtl() {
		try {
			ExperienceBulkUploadModel model = new ExperienceBulkUploadModel();
			model.initiate(context, session);
			model.uploadExpDtlTemplate(response, request, empDetails);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in uploadExperienceDtl in action:" + e);
		}
		return SUCCESS;
	}

	public String uploadOfficialDetails() {
		OfficialBulkUploadModel model = new OfficialBulkUploadModel();
		model.initiate(context, session);
		model.uploadTemplate(response, request, empDetails);
		model.terminate();
		return SUCCESS;
	}

	public String uploadPersonal() {
		PersonalBulkUploadModel model = new PersonalBulkUploadModel();
		model.initiate(context, session);
		model.uploaddPersonalTemplate(request, response, empDetails);
		model.terminate();
		return SUCCESS;
	}

	public String uploadFamily() {
		FamilyBulkUploadModel model = new FamilyBulkUploadModel();
		model.initiate(context, session);
		model.uploaddFamilyTemplate(request, response, empDetails);
		model.terminate();
		return SUCCESS;
	}

	public void viewUploadedFile() {
		try {
			String uploadFileName = request.getParameter("uploadFileName");
			String dataPath = request.getParameter("dataPath");

			MigrateExcelData.viewUploadedFile(uploadFileName, dataPath,
					response);
		} catch (Exception e) {
			logger.error("Exception in viewUploadedFile in action:" + e);
		}
	}

	public String uploadEmpQualification() {
		EmpQualBulkUploadModel model = new EmpQualBulkUploadModel();
		model.initiate(context, session);
		model.uploadEmpQualification(empDetails);
		model.terminate();
		return SUCCESS;
	}

	public String uploadPostDetails() {
		try {
			System.out
					.println("in action's uploadPostDetails method############################## ");
			PostingDetailsBulkUploadModel model = new PostingDetailsBulkUploadModel();
			model.initiate(context, session);
			model.uploadPostDetails(request, response, empDetails);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String reset() {
		try {
			empDetails.setUploadFileName("");
			empDetails.setUploadName("");
			empDetails.setStatus("Fail");
			empDetails.setNote("");
		} catch (Exception e) {
			logger.error("Exception in reset in action:" + e);
		}
		return SUCCESS;
	}
	
	//Update By Anantha Lakshmi
	public String uploadIncrementHistoryDetails() {
		try {
			IncrementBulkUploadModel model = new IncrementBulkUploadModel();
			model.initiate(context, session);
			model.uploadTemplate(response, request, empDetails);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in uploadINCR in action:" + e);
		}
		return SUCCESS;
	}

	public String uploadPromotionHistoryDetails() {
		try {
			PromotionBulkUploadModel model = new PromotionBulkUploadModel();
			model.initiate(context, session);
			model.uploadTemplate(response, request, empDetails);
			model.terminate();
		} catch (Exception e) {
			logger.error("Exception in uploadINCR in action:" + e);
		}
		return SUCCESS;
	}
}