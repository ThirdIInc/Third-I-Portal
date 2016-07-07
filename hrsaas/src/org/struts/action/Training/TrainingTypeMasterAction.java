package org.struts.action.Training;

import org.paradyne.bean.Training.TrainingTypeMaster;
import org.paradyne.model.Training.TrainingTypeMasterModel;
import org.struts.lib.ParaActionSupport;

public class TrainingTypeMasterAction extends ParaActionSupport{

	TrainingTypeMaster trnMaster;
	
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		trnMaster=new TrainingTypeMaster();
		trnMaster.setMenuCode(5043);
	}

	public TrainingTypeMaster getTrnMaster() {
		return trnMaster;
	}

	public void setTrnMaster(TrainingTypeMaster trnMaster) {
		this.trnMaster = trnMaster;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return trnMaster;
	}
	
	public String input() {
		try {
			boolean search=false;
			TrainingTypeMasterModel model = new TrainingTypeMasterModel();
			model.initiate(context, session);
			 search=Boolean.parseBoolean(request.getParameter("search"));
			model.trainingTypeListData(trnMaster, request,search);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(2);
		return "trnTypedata";
	}
		
	/**
	 * To save the record
	 * @return String
	 * @throws Exception
	 */
	public String submitTrnType() throws Exception {
		TrainingTypeMasterModel model = new TrainingTypeMasterModel();
		model.initiate(context, session);
		
		boolean result;

		if(trnMaster.getTrnCode().equals("")) {
			result = model.addTrnType(trnMaster);
			if(result) {
				addActionMessage(getMessage("save"));
				getNavigationPanel(2);
				reset();
				return input();
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();
				getNavigationPanel(2);
				return input();
			}// end of else
		} else {
			result = model.modTrnType(trnMaster);
			System.out.println("------RESULT--------------"+result);
			model.terminate();
			if(result) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(2);
				reset();
				return input();
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();// new added
				getNavigationPanel(2);
				return input();
			}// end of else
		}// end of else
		
		
	}
	
	public String reset() throws Exception {
		try {
			trnMaster.setTrnCode("");
			trnMaster.setTrnName("");
			trnMaster.setTrndes("");
				
			getNavigationPanel(2);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return input();
	}
	
	public String deleteTrn() throws Exception {
		try {
			TrainingTypeMasterModel model = new TrainingTypeMasterModel();
			model.initiate(context, session);
			model.deleteTrn(trnMaster);
			model.terminate();
			getNavigationPanel(2);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return input();
	}
	
	/**
	 * To set the page according to the page numbers
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		TrainingTypeMasterModel model = new TrainingTypeMasterModel();
		model.initiate(context, session);
		model.trainingTypeListData(trnMaster, request,false);
		model.terminate();
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String f9action() throws Exception {

		String query = "SELECT TRAINING_ID,TRAINING_NAME, " +
				"TO_CHAR(HRMS_TRAINING.RECORD_CREATEDON,'DD-MM-YYYY') ," +
				"EMP_FNAME||''||EMP_LNAME FROM  HRMS_TRAINING  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TRAINING.RECORD_CREATEDBY)";

		String[] headers = { getMessage("trnName"),
				getMessage("created"), getMessage("createdBy")};

		String[] headerWidth = { "30", "30", "30" };

		String[] fieldNames = { "trnCode","trnName", "created", "createdBy"};

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "true";

		String submitToMethod = "TrainingTypeMaster_input.action?search=true";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
}
