package org.struts.action.PAS;

import java.util.Calendar;
import org.paradyne.bean.PAS.MonthlyRating;
import org.paradyne.model.DataMigration.AssetMasterUploadModel;
import org.paradyne.model.PAS.MonthlyRatingModel;
import org.struts.action.DataMigration.MigrateExcelData;
import org.struts.lib.ParaActionSupport;

/**
 * @author REEBA_JOSEPH
 * 22 OCTOBER 2010
 *
 */
public class MonthlyRatingAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(MonthlyRatingAction.class);
	
	MonthlyRating monthlyRating = null;
	
	public MonthlyRating getMonthlyRating() {
		return monthlyRating;
	}

	public void setMonthlyRating(MonthlyRating monthlyRating) {
		this.monthlyRating = monthlyRating;
	}

	@Override
	public void prepare_local() throws Exception {
		monthlyRating = new MonthlyRating();
		monthlyRating.setMenuCode(1098);

	}

	public Object getModel() {
		return monthlyRating;
	}
	
	/**
	 * Method to view employees reporting to the logged user
	 * @return String
	 */
	public String viewEmployees() {
		monthlyRating.setViewEmp("true");
		MonthlyRatingModel model = new MonthlyRatingModel();
		model.initiate(context, session);
		model.viewEmployeeList(monthlyRating, request);
		model.terminate();
		return SUCCESS;
	}// end of method viewEmployees
	
	/**
	 * Reset the form fields
	 * @return String
	 */
	public String reset() {
		monthlyRating.setMonth("");
		monthlyRating.setYear("");
		monthlyRating.setEmpList(null);
		monthlyRating.setViewEmp("false");
		monthlyRating.setUploadFileName("");
		monthlyRating.setUploadName("");
		return SUCCESS;
	}// end of reset
	
	/**
	 * Save employees with no blank ratings
	 * @return String
	 * @throws Exception
	 */
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
	public String save() throws Exception {
		MonthlyRatingModel model = new MonthlyRatingModel();
		model.initiate(context, session);
		boolean result = model.saveRatings(monthlyRating, request);
		if(result)
			addActionMessage(getMessage("save"));
		else
			addActionMessage(getMessage("save.error"));
		model.terminate();
		reset();
		return SUCCESS;
	}//end of save
	public String input() throws Exception {
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		String dataPath = getText("data_path") + "/DataMigration/" + poolName + "/";
		monthlyRating.setDataPath(dataPath);
		
		return SUCCESS;
	}
	public String uploadEmployeeRating() throws Exception{
		MonthlyRatingModel model =new MonthlyRatingModel();
		model.initiate(context, session);
		model.uploadMonthlyRating(monthlyRating);
		model.terminate();
		
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
