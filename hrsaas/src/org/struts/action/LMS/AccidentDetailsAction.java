/**
 * @author Manish Sakpal
 * created on 11th Feb. 2011
 */

package org.struts.action.LMS;

import org.paradyne.bean.LMS.AccidentDetails;
import org.paradyne.model.LMS.AccidentDetailsModel;
import org.struts.lib.ParaActionSupport;

public class AccidentDetailsAction extends ParaActionSupport {
	AccidentDetails accBean;
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(AccidentDetailsAction.class);
	
	public void prepare_local() throws Exception {
		accBean = new AccidentDetails();
		accBean.setMenuCode(1156);
	}

	public Object getModel() {		
		return accBean;
	}

	public AccidentDetails getAccBean() {
		return accBean;
	}

	public void setAccBean(AccidentDetails accBean) {
		this.accBean = accBean;
	}
	
	public String input()
	{
		try {
			AccidentDetailsModel model = new AccidentDetailsModel();
			model.initiate(context, session);
			model.getInitialList(accBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String back() throws Exception {
		input();
		getNavigationPanel(1);
		return INPUT;
	}
	
	public String calforedit() throws Exception 
	{
		try {
			String hiddenAccidentListID = request.getParameter("id");
			AccidentDetailsModel model = new AccidentDetailsModel();			
			model.initiate(context, session);
			model.calforedit(accBean, hiddenAccidentListID);		
			model.terminate();
			} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		accBean.setEnableAll("N");
		getNavigationPanel(3);
		return SUCCESS;
	}
	
	public String addNew()
	{ 
		accBean.setAccidentID("");
		accBean.setCasualTypeRadio("");
		accBean.setStatusRadio("");
		accBean.setLegalHeirsRadio("");
		accBean.setAccidentPlace("");
		accBean.setEnableAll("Y");
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	
	public String save() {
		try 
		{
			AccidentDetailsModel model = new AccidentDetailsModel();
			model.initiate(context, session);
			boolean result;
			// Victim parameters
			String[] victimIDItr = request.getParameterValues("victimIDItr");			
			String[] victimNameItr = request.getParameterValues("victimNameItr");
			String[] victimTokenItr = request.getParameterValues("victimTokenItr");
			String[] injuryDetailsItr = request.getParameterValues("injuryDetailsItr");
			String[] bodyPartsAffectedItr = request.getParameterValues("bodyPartsAffectedItr");
			String[] dateOfDeathItr = request.getParameterValues("dateOfDeathItr");
			String[] timeOfDeathItr = request.getParameterValues("timeOfDeathItr");
			String[] statusItr = request.getParameterValues("statusItr");
			String[] legalHiersEmployedItr = request.getParameterValues("legalHiersEmployedItr");
			String[] nameOfHiersItr = request.getParameterValues("nameOfHiersItr");
			String[] relationShipItr = request.getParameterValues("relationShipItr");
			String[] insuranceNumberItr = request.getParameterValues("insuranceNumberItr");			
			String[] meansOfHospitalizationItr = request.getParameterValues("meansOfHospitalizationItr");
			String[] workedPerformedItr = request.getParameterValues("workedPerformedItr");
			String[] protectiveMeasuresItr = request.getParameterValues("protectiveMeasuresItr");
			String[] amtOfCompensationItr = request.getParameterValues("amtOfCompensationItr");
			
			// Witness parameters
			String[] witnessID = request.getParameterValues("witnessID");
			String[] witnessName = request.getParameterValues("witnessName");
			String[] witnessAddress = request.getParameterValues("witnessAddress");
			String[] witnessOccupation = request.getParameterValues("witnessOccupation");
			
			if (accBean.getAccidentID().equals("")) 
			{
				result = model.insertRecords(accBean, request,victimIDItr,victimNameItr,victimTokenItr,
						injuryDetailsItr,bodyPartsAffectedItr,dateOfDeathItr,timeOfDeathItr,statusItr,legalHiersEmployedItr,
						nameOfHiersItr, relationShipItr,insuranceNumberItr,meansOfHospitalizationItr,workedPerformedItr,
						protectiveMeasuresItr,amtOfCompensationItr,witnessID,witnessName,witnessAddress,witnessOccupation);
				if (result) 
				{
					addActionMessage(getMessage("save"));
				} 
				else 
				{
					addActionMessage("Error occur during saving the records.");
				}				
			}
			else 
			{
				result = model.updateRecords(accBean, request,victimIDItr,victimNameItr,victimTokenItr,
						injuryDetailsItr,bodyPartsAffectedItr,dateOfDeathItr,timeOfDeathItr,statusItr,legalHiersEmployedItr,
						nameOfHiersItr, relationShipItr,insuranceNumberItr,meansOfHospitalizationItr,workedPerformedItr,
						protectiveMeasuresItr,amtOfCompensationItr,witnessID,witnessName,witnessAddress,witnessOccupation);
				if (result) 
				{
					addActionMessage(getMessage("update"));
				} 
				else 
				{
					addActionMessage("Error occur during updating the records.");
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
		input();
		getNavigationPanel(1);		
		return INPUT;
	} 	
	
	public String f9searchRecords() throws Exception {
		try {
			String searchquery = "SELECT DECODE(ACC_CASUALITY_TYPE,'A','Accident','N','Nearmiss'),"
								+" DECODE(ACCIDENT_TYPE,'F','Fatal','N','Non-Fatal'),ACCIDENT_PLACE,ACCIDENT_CODE FROM HRMS_ACCIDENT_HDR";
			String[] headers = { "Casual Type", "Accident Type", "Accident Place" };
			String[] headerWidth = { "30", "30", "40"};
			String[] fieldNames = { "casualityType", "typeOfAccident",  "accidentPlace", "accidentID" };
			int[] columnIndex = { 0, 1, 2, 3 };
			String submitFlag = "true";
			String submitToMethod = "AccidentDetails_setAccidentRecord.action";
			setF9Window(searchquery, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String setAccidentRecord()
	{
		try {
			AccidentDetailsModel model = new AccidentDetailsModel();
			model.initiate(context, session);
			model.setAccidentRecord(accBean);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		accBean.setEnableAll("N");
		getNavigationPanel(3);
		return SUCCESS;
	}
	
	
	public String f9Victim()
	{
		try {
			String searchquery = "SELECT EMP_TOKEN,EMP_FNAME ||' '|| EMP_MNAME ||' '|| EMP_LNAME,EMP_ID  FROM HRMS_EMP_OFFC"
								+" ORDER BY EMP_ID";
			String[] headers = { "Victim ID", "Victim Name" };
			String[] headerWidth = { "30", "70" };
			String[] fieldNames = { "victimToken", "victimName", "victimID"  };
			int[] columnIndex = { 0, 1, 2 };
			String submitFlag = "false";
			String submitToMethod = "";
			setF9Window(searchquery, headers, headerWidth, fieldNames,
					columnIndex, submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}
	
	public String addVictimDetails()
	{
		try {
			AccidentDetailsModel model = new AccidentDetailsModel();
			model.initiate(context, session);
			String[] serialNum = request.getParameterValues("serialNum");
			String[] victimIDItr = request.getParameterValues("victimIDItr");			
			String[] victimNameItr = request.getParameterValues("victimNameItr");
			String[] victimTokenItr = request.getParameterValues("victimTokenItr");
			String[] injuryDetailsItr = request.getParameterValues("injuryDetailsItr");
			String[] bodyPartsAffectedItr = request.getParameterValues("bodyPartsAffectedItr");
			String[] dateOfDeathItr = request.getParameterValues("dateOfDeathItr");
			String[] timeOfDeathItr = request.getParameterValues("timeOfDeathItr");
			String[] statusItr = request.getParameterValues("statusItr");
			String[] legalHiersEmployedItr = request.getParameterValues("legalHiersEmployedItr");
			String[] nameOfHiersItr = request.getParameterValues("nameOfHiersItr");
			String[] relationShipItr = request.getParameterValues("relationShipItr");
			String[] insuranceNumberItr = request.getParameterValues("insuranceNumberItr");
			String[] meansOfHospitalizationItr = request.getParameterValues("meansOfHospitalizationItr");
			String[] workedPerformedItr = request.getParameterValues("workedPerformedItr");
			String[] protectiveMeasuresItr = request.getParameterValues("protectiveMeasuresItr");
			String[] amtOfCompensationItr = request.getParameterValues("amtOfCompensationItr");
			
			
			// Witness parameters
			String[] witnessID = request.getParameterValues("witnessID");
			String[] witnessName = request.getParameterValues("witnessName");
			String[] witnessAddress = request.getParameterValues("witnessAddress");
			String[] witnessOccupation = request.getParameterValues("witnessOccupation");
			
			model.addVictimDetails(accBean, request,serialNum,victimIDItr,victimNameItr,victimTokenItr,
					injuryDetailsItr,bodyPartsAffectedItr,dateOfDeathItr,timeOfDeathItr,statusItr,legalHiersEmployedItr,
					nameOfHiersItr, relationShipItr,insuranceNumberItr,meansOfHospitalizationItr,workedPerformedItr,
					protectiveMeasuresItr,amtOfCompensationItr,witnessID,witnessName,witnessAddress,witnessOccupation);
			
			accBean.setVictimID("");
			accBean.setVictimToken("");
			accBean.setVictimName("");
			accBean.setStatus("");
			accBean.setInjuryDetails("");
			accBean.setBodyPartsAffected("");
			accBean.setDateOfDeath("");
			accBean.setTimeOfDeath("");
			accBean.setInsuranceNumber("");
			accBean.setAmtOfCompensation("");
			accBean.setLegalHiersEmployed("");
			accBean.setNameOfHiers("");
			accBean.setRelationShip("");
			accBean.setMeansOfHospitalization("");
			accBean.setWorkedPerformed("");
			accBean.setProtectiveMeasures("");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		accBean.setEnableAll("Y");
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String deleteVictimRecordFromDatabase()
	{
		try {
			String victimDeleteCode = request.getParameter("victimDeleteCode");
			AccidentDetailsModel model = new AccidentDetailsModel();
			model.initiate(context, session);
			boolean result = model.deleteVictimRecordFromDatabase(accBean,victimDeleteCode);
			if (result) {
				addActionMessage("Record deleted successfully");
			} else {
				addActionMessage("Record can't be deleted ");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setAccidentRecord();
		accBean.setEnableAll("Y");
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	public String deleteVictimFromJsp() {
		try {
			AccidentDetailsModel model = new AccidentDetailsModel();
			model.initiate(context, session);
			
			String[] serialNum = request.getParameterValues("serialNum");
			String[] victimIDItr = request.getParameterValues("victimIDItr");			
			String[] victimNameItr = request.getParameterValues("victimNameItr");
			String[] victimTokenItr = request.getParameterValues("victimTokenItr");
			String[] injuryDetailsItr = request.getParameterValues("injuryDetailsItr");
			String[] bodyPartsAffectedItr = request.getParameterValues("bodyPartsAffectedItr");
			String[] dateOfDeathItr = request.getParameterValues("dateOfDeathItr");
			String[] timeOfDeathItr = request.getParameterValues("timeOfDeathItr");
			String[] statusItr = request.getParameterValues("statusItr");
			String[] legalHiersEmployedItr = request.getParameterValues("legalHiersEmployedItr");
			String[] nameOfHiersItr = request.getParameterValues("nameOfHiersItr");
			String[] relationShipItr = request.getParameterValues("relationShipItr");
			String[] insuranceNumberItr = request.getParameterValues("insuranceNumberItr");
			String[] meansOfHospitalizationItr = request.getParameterValues("meansOfHospitalizationItr");
			String[] workedPerformedItr = request.getParameterValues("workedPerformedItr");
			String[] protectiveMeasuresItr = request.getParameterValues("protectiveMeasuresItr");
			String[] amtOfCompensationItr = request.getParameterValues("amtOfCompensationItr");
			
			boolean result = model.removeVictimRecord(accBean, request,serialNum,victimIDItr,victimNameItr,victimTokenItr,
					injuryDetailsItr,bodyPartsAffectedItr,dateOfDeathItr,timeOfDeathItr,statusItr,legalHiersEmployedItr,
					nameOfHiersItr, relationShipItr,insuranceNumberItr,meansOfHospitalizationItr,workedPerformedItr,
					protectiveMeasuresItr,amtOfCompensationItr);
			
			if (result) {
				addActionMessage("Record deleted successfully");
			} else {
				addActionMessage("Record can't be deleted ");
			}
			
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		accBean.setEnableAll("Y");
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	
	public String deleteWitnessRecord()
	{
		try {
			String witnessCode = request.getParameter("witnessCode");
			AccidentDetailsModel model = new AccidentDetailsModel();
			model.initiate(context, session);
			boolean result = model.deleteWitnessRecordFromDatabase(accBean,witnessCode);
			if (result) {
				addActionMessage("Record deleted successfully");
			} else {
				addActionMessage("Record can't be deleted ");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setAccidentRecord();
		accBean.setEnableAll("Y");
		getNavigationPanel(2);
		return SUCCESS;
	}
	
	
	public String deleteAccidentRecord()
	{
		try {
			AccidentDetailsModel model = new AccidentDetailsModel();
			model.initiate(context, session);
			boolean result = model.deleteAccidentRecord(accBean);
			if (result) {
				addActionMessage("Record deleted successfully");
			} else {
				addActionMessage("Record can't be deleted ");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		getNavigationPanel(1);
		return INPUT;
	}
	
	
	public String deleteAccidentRecordFromListPage()
	{
		try {
			String deleteAccidentID = request.getParameter("deleteAccidentID");
			AccidentDetailsModel model = new AccidentDetailsModel();
			model.initiate(context, session);
			boolean result = model.deleteAccidentRecordFromList(accBean,deleteAccidentID);
			if (result) {
				addActionMessage("Record deleted successfully");
			} else {
				addActionMessage("Record can't be deleted ");
			}
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input();
		getNavigationPanel(1);
		return INPUT;
	}
	
	
	public String edit() throws Exception {
		try {
			getNavigationPanel(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setAccidentRecord();
		accBean.setEnableAll("Y");
		getNavigationPanel(2);
		return SUCCESS;
	}
	
}
