package org.struts.action.payroll.incometax;

import org.paradyne.bean.payroll.incometax.*;
import org.paradyne.model.payroll.incometax.*;
import org.struts.lib.*;

/**
 * @author Varunk
 * modified by ganesh 11/10/2011
 */
public class TaxSlabAction extends ParaActionSupport {

	TaxSlab taxSlab;

	public TaxSlab getTaxSlab() {
		return taxSlab;
	}

	public void setTaxSlab(TaxSlab taxSlab) {
		this.taxSlab = taxSlab;
	}

	public Object getModel() {
		return taxSlab;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TaxSlabAction.class);

	public void prepare_local() throws Exception {
		taxSlab = new TaxSlab();
		taxSlab.setMenuCode(279);
	}

	public String input() throws Exception {
		TaxSlabModel model = new TaxSlabModel();
		model.initiate(context, session);
		model.getTaxSlabDataList(taxSlab, request);
		model.terminate();
		taxSlab.setEnableAll("Y");
		getNavigationPanel(1);
		return INPUT;

	}

	/**
	 * This method is used to save and update the record in the HRMS_TAX_SLAB
	 * table.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {

		TaxSlabModel model = new TaxSlabModel();
		model.initiate(context, session);
		boolean result;
		//boolean chkRange = false;
		/**
		 * this method checks the range of slabs to be inserted with the
		 * previous slab which are already added. If the range already exists it
		 * displays the alert message.
		 */
		//chkRange = model.chkRange(taxSlab);
		/**
		 * If the range is correct.
		 */
		
		/*final boolean checkFlag = model.checkFromYearExit(this.taxSlab.getFromYear());
		if (checkFlag) {
			
			taxSlab.setTaxId("");
			taxSlab.setTaxSlabWomenListId("");
			taxSlab.setTaxSlabSeniorListId("");
			taxSlab.setFromYear("");
			taxSlab.setToYear("");
			this.addActionMessage("Given Financial From Year and Financial To Year range already exists");
			return reset();
		}*/
		
		
		/*if (chkRange) {*/
			if ("".equals(this.taxSlab.getTaxId())) {
				result = model.addTaxSlab(taxSlab,request);
				if (result) {
					this.addActionMessage("Record Saved Successfully.");
					
					
					//taxSlab.setTdsSlabTableFlag(true);
				//	taxSlab.setReportFlag(true);
					
					
					
					//reset();
				} else {
					this.addActionMessage("Given Financial From Year and Financial To Year range already exists");
				//	getNavigationPanel(1);
				//	return "showData";
					//this.reset();
				}	
				
				/**
				 * this method is used to insert the record.
				 */
				
			}// end of if
			else {
				
				/**
				 * this method is used to update the record
				 */
				result = model.updateTaxSlab(taxSlab,request);
				model.terminate();
				if (result) {
					this.addActionMessage("Record Updated Successfully.");
					/*taxSlab.setReportFlag(true);
					return view();*/
					
					
				} else {
					this.addActionMessage("Error occured while Saving");
					//getNavigationPanel(1);
					//return "showData";
					//this.reset();
				}	
			}// end of else

			/**
			 * if result is true then the respective fields is kept blank
			 */
			/*if (result1) {
				taxSlab.setFrmAmount("");
				taxSlab.setToAmount("");
				taxSlab.setTaxPercentage("");
				taxSlab.setIsFromEdit("");
				addActionMessage(getText("addMessage", ""));
				return view();

			}// end of if
			else {
				addActionMessage(" TaxSlab cannot be added");
			}// end of else
*/		/*}// end of if
		else {
			addActionMessage("Given From Amount and To Amount range already exists");
		}// end of else
*/		
		/**
		 * the view method is called to retrieve the saved record.
		 */
			model.getRecord(taxSlab, true);
			getNavigationPanel(3);
			return "showData";
	}// end of save method

	/**
	 * This method is used to view the report of the slabs for the respective
	 * years entered This report displays the record of all the Slab
	 * types(men,women and senior)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String report() throws Exception {
		TaxSlabModel model = new TaxSlabModel();
		model.initiate(context, session);
		/**
		 * this method is used to retrieve the record and to display in the
		 * report
		 */
		boolean result = model.getReport(taxSlab, response);
		/**
		 * if the result is false the message is displayed
		 */
		if (!result) {
			addActionMessage("No Data Available");
		}// end of if
		else {
			addActionMessage("Problem in Report Generation");
		}// end of else
		model.terminate();

		return null;
	}// end of report method

	/**
	 * this method is usedd to get the saved slab record
	 * 
	 * @return
	 * @throws Exception
	 */
	public String taxSlabRecord() throws Exception {
		TaxSlabModel model = new TaxSlabModel();
		model.initiate(context, session);
		model.getRecord(taxSlab,false);
		model.terminate();
		getNavigationPanel(3);
		return "showData";
	}// end of tax_slabRecord

	/**
	 * this method is used to delete the slabs.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		TaxSlabModel model = new TaxSlabModel();
		model.initiate(context, session);
		boolean result = model.delTaxSlabRecord(taxSlab);

		if (result) {
			addActionMessage(getMessage("delete"));
			result = model.getRecord(taxSlab,false);
		}// end of if
		if (result == false) {

			taxSlab.setFrmAmount("0");
		}// end of if
		model.terminate();
		getNavigationPanel(3);
		return "showData";
	}// end of delete method

	/**
	 * This method is used to view the records for the respective year entered
	 * and the slab type selected.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		TaxSlabModel model = new TaxSlabModel();
		model.initiate(context, session);
		boolean result = model.getRecord(taxSlab,false);
		if (result == false) {
			addActionMessage("No Records to view for the financial year entered. Please enter slab rates and Save");
			taxSlab.setFrmAmount("0");
		}// end of if
		taxSlab.setChkEdit("true");
		taxSlab.setTdsSlabTableFlag(true);
		model.terminate();

		getNavigationPanel(2);
		return "showData";
	}// end of view method

	/**
	 * This method is used to edit the records.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		TaxSlabModel model = new TaxSlabModel();
		model.initiate(context, session);
		model.getEditRecord(taxSlab);
		taxSlab.setIsFromEdit("true");
		model.getRecord(taxSlab,false);
		model.terminate();
		return taxSlabRecord();
	}// end of edit method

	/**
	 * this method sets all the field value to blank
	 * 
	 * @return
	 * @throws Exception
	 */
	public String reset() {
		
		taxSlab.setFromYear("");
		taxSlab.setToYear("");
		
			taxSlab.setFrmAmount("");
			taxSlab.setToAmount("");
			taxSlab.setTaxPercentage("");
			taxSlab.setFrmAmountWomen("");
			taxSlab.setToAmountWomen("");
			taxSlab.setTaxPercentageWomen("");
			taxSlab.setFrmAmountSenior("");
			taxSlab.setToAmountSenior("");
			taxSlab.setTaxPercentageSenior("");
			
			taxSlab.setTaxId("");
			taxSlab.setTaxSlabWomenListId("");
			taxSlab.setTaxSlabSeniorListId("");
			taxSlab.setParaID("");
			taxSlab.setIsFromEdit("");
			taxSlab.setChkEdit("");
			taxSlab.setType("");
		
		getNavigationPanel(2);
		return "showData";
	}// end of reset method

	/**
	 * this method only resets the respective fields
	 * 
	 * @return
	 * @throws Exception
	 */
	public String reset1() throws Exception {
		try {
			taxSlab.setFrmAmount("");
			taxSlab.setToAmount("");
			taxSlab.setTaxPercentage("");
		} catch (Exception e) {
			logger.error("this method resets the fields");
		}
		return "success";
	}// end of reset1 method

	/**
	 * This Function is used for searching Records.
	 * @return f9page
	 */
	public String f9taxslab(){

		try {
			//String query = " select distinct TAX_FROM_YEAR,TAX_TO_YEAR,decode(TAX_EMP_TYPE,'M','Men','F','Women','S','Senior'),TAX_EMP_TYPE from HRMS_TAX_SLAB order by TAX_FROM_YEAR desc";
			String query = " SELECT DISTINCT HRMS_TAX_SLAB.TAX_FROM_YEAR, HRMS_TAX_SLAB.TAX_TO_YEAR FROM HRMS_TAX_SLAB ORDER BY HRMS_TAX_SLAB.TAX_FROM_YEAR DESC";
			String[] headers = { getMessage("taxation.FinYrFrm"),
					getMessage("taxation.FinYrTo") };
			String[] headerWidth = { "50", "50" };
			String[] fieldNames = { "taxSlab.fromYear", "taxSlab.toYear" };
			int[] columnIndex = { 0, 1 };
			String submitFlag = "true";
			String submitToMethod = "TaxSlab_f9actiondetails.action";
			setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "f9page";
	}

	/**
	 * f9actiondetails.: When click on perticuler Record.
	 * @return showData
	 * @throws Exception
	 */
	public String f9actiondetails() throws Exception {

		TaxSlabModel model = new TaxSlabModel();
		model.initiate(context, session);
		model.initiate(context, session);

		String fromYrCode = taxSlab.getFromYear();
		String toYrCode = taxSlab.getToYear();
		String empTypeCode = "2";
		
		taxSlab.setFromYear(fromYrCode);
		taxSlab.setToYear(toYrCode);
		taxSlab.setType(empTypeCode);
		//taxSlab.setTdsSlabTableFlag(true);
		//taxSlab.setReportFlag(true);
		
		model.getRecord(taxSlab,true);
		taxSlab.setChkEdit("true");

		model.terminate();
		getNavigationPanel(3);
		return "showData";
	}

	/**
	 * This Function is used to add new application. 
	 * @return reset
	 */
	public String addNew() {
		try {
			taxSlab.setFromYear("");
			taxSlab.setToYear("");
			taxSlab.setTaxId("");
			taxSlab.setTaxSlabWomenListId("");
			taxSlab.setTaxSlabSeniorListId("");
			getNavigationPanel(4);
			
		} catch (Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	 return "showData";
	}

	/**
	 * This function is used to come list page.
	 * @return : input()
	 * @throws Exception
	 */
	public String back() throws Exception {
		//reset();
		
		getNavigationPanel(1);
	//	input();
		return input();
	}

	/**
	 * This function is used for Edit perticuler Record.
	 * @return showData
	 */
	public String callForListEdit() throws Exception {

		try {
			TaxSlabModel model = new TaxSlabModel();
			model.initiate(context, session);
			String fromYrCode = request.getParameter("fromYearCode");
			String toYrCode = request.getParameter("toYearCode");
			String empTypeCode = request.getParameter("empTypeCode");
			taxSlab.setFromYear(fromYrCode);
			taxSlab.setToYear(toYrCode);
			taxSlab.setType(empTypeCode);
			//taxSlab.setTdsSlabTableFlag(true);
		//	taxSlab.setReportFlag(true);
			
			
			model.getRecord(taxSlab, true);
			
			getNavigationPanel(3);
			taxSlab.setEnableAll("N");
			
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "showData";
	}

	public String deleteMultipleRecordsFromTaxSlabList() throws Exception {

		logger.info("################## IN MULTIPLE DELETE ##############");
		boolean result = false;
		TaxSlabModel model = new TaxSlabModel();
		model.initiate(context, session);
		String[] fromYearCode = request
				.getParameterValues("hdeleteCodeFromYear");
		String[] toYearCode = request.getParameterValues("hdeleteCodeToYear");
		String[] empTypeCode = request.getParameterValues("hdeleteCodeEmpType");
System.out.println("fromYearCode========"+fromYearCode);
		result = model.deleteCheckedRecords(taxSlab, fromYearCode, toYearCode);
		if (result)
			addActionMessage(getText("delMessage", ""));
		else
			//addActionMessage(getMessage("del.error"));
			addActionMessage(getText("delMessage", ""));
		model.terminate();
		getNavigationPanel(1);
		return input();
	}
	
	
	
	public String deleteSelectedRecordsFromTaxSlabList() throws Exception {

		logger.info("################## IN MULTIPLE DELETE ##############");
		boolean result = false;
		TaxSlabModel model = new TaxSlabModel();
		model.initiate(context, session);
		String fromYearCode = taxSlab.getFromYear();
		String toYearCode = taxSlab.getToYear();
		
		result = model.deleteSelectedRecords(taxSlab, fromYearCode, toYearCode);
		if (result)
			addActionMessage(getText("delMessage", ""));
		else
			//addActionMessage(getMessage("del.error"));
			addActionMessage(getText("delMessage", ""));
		model.terminate();
		getNavigationPanel(1);
		return input();
	}

}
