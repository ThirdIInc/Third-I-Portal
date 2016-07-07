package org.paradyne.model.payroll.pension;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.BusinessUnitBean;
import org.paradyne.bean.PAS.QuestionCategory;
import org.paradyne.bean.payroll.pension.FormulaBuilderPension;
import org.paradyne.bean.payroll.pension.PensionConfiguration;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.PAS.QuestionCategoryModel;

public class PensionConfigurationModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PensionConfigurationModel.class);
	
	/**
	 * @method showQuestionCategory()
	 * @purpose to retrieve Question Category from "PAS_APPR_QUESTION_CATGORY" Table
	 * @param bean to populate all data which retrieve from database
	 */
	
	/*public void showPensionConfigurationList(PensionConfiguration bean, HttpServletRequest request) {
		logger.info("In showPensionConfigurationList() method Model");
		
		
		try 
		{
			Object [][] objData = getSqlModel().getSingleResult(getQuery(1));
			ArrayList<Object> list = new ArrayList<Object>();
			
			int REC_TOTAL = 0;
			int To_TOT = 20;
			int From_TOT = 0;
			int pg1 = 0;
			int PageNo1 = 1;// ----------
			REC_TOTAL = objData.length;
			int no_of_pages = Math.round(REC_TOTAL / 20);
			double row = (double) objData.length / 20.0;
			java.math.BigDecimal value1 = new java.math.BigDecimal(row);
			int rowCount1 = Integer.parseInt(value1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());
			
			request.setAttribute("abc", rowCount1);
			if (String.valueOf(bean.getMyPage()).equals("null")
					|| String.valueOf(bean.getMyPage()).equals(null)
					|| String.valueOf(bean.getMyPage()).equals("")) {
				PageNo1 = 1;
				From_TOT = 0;
				To_TOT = 20;
				if (To_TOT > objData.length) {
					To_TOT = objData.length;
				}//end of if
				bean.setMyPage("1");
			}//end of if

			else {
				pg1 = Integer.parseInt(bean.getMyPage());
				PageNo1 = pg1;

				if (pg1 == 1) {
					From_TOT = 0;
					To_TOT = 20;
				}//end of if
				else {
					To_TOT = To_TOT * pg1;
					From_TOT = (To_TOT - 20);
				}//end of else
				if (To_TOT > objData.length) {
					To_TOT = objData.length;
				}//end of if
			}//end of else
			request.setAttribute("xyz", PageNo1);
			
			PensionConfiguration bean1 = null;
			if(objData != null && objData.length != 0)
			{
				for (int i = From_TOT; i < To_TOT; i++) 
				{
					bean1  = new PensionConfiguration(); 
					
					bean1.setSSrNo("" + i+1);
					bean1.setSPensionCode(checkNull(String.valueOf(objData[i][0])));
					bean1.setSPensionTypeCode(checkNull(String.valueOf(objData[i][1])));
					bean1.setSPensionMinService(checkNull(String.valueOf(objData[i][2])));
					bean1.setSPensionMaxService(checkNull(String.valueOf(objData[i][3])));
					bean1.setSPensionFormula(checkNull(String.valueOf(objData[i][4])));
					bean1.setSPensionCompFormula(checkNull(String.valueOf(objData[i][5])));
					bean1.setSPensionMinAmt(checkNull(String.valueOf(objData[i][6])));

					list.add(bean1);
				}
				
				bean.setLstPensionConfiguration(list);
				bean.setReadFlag("true");
			} 
		}
		catch (Exception e) {
			logger.error("Error in PensionConfigurationModel method Model : " + e.getMessage());
		}
	}*/
	
	public void showPensionConfigurationList(PensionConfiguration bean, HttpServletRequest request) {
		logger.info("In showPensionConfigurationList() method Model"+bean.getSPensionTypeCode());
		request.setAttribute("tabName", getTabName(Integer.parseInt(bean.getSPensionTypeCode())));
		try 
		{
			Object []pensionType= new Object[1];
			
			pensionType[0]= bean.getSPensionTypeCode();
			Object [][] objData = getSqlModel().getSingleResult(getQuery(1),pensionType);
			
			
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),objData.length, 20);	
			if(pageIndex==null){
				pageIndex[0] = "0";
				pageIndex[1] ="20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if(pageIndex[4].equals("1"))
				bean.setMyPage("1");
			ArrayList list= new ArrayList();
			PensionConfiguration bean1 = null;
			if(objData != null && objData.length != 0)
			{
				for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {
					bean1  = new PensionConfiguration(); 
					
					bean1.setSSrNo("" + i+1);
					bean1.setSPensionCode(checkNull(String.valueOf(objData[i][0])));
					bean1.setSPensionTypeCode(checkNull(String.valueOf(objData[i][1])));
					bean1.setSPensionMinService(checkNull(String.valueOf(objData[i][2])));
					bean1.setSPensionMaxService(checkNull(String.valueOf(objData[i][3])));
					bean1.setSPensionFormula(checkNull(String.valueOf(objData[i][4])));
					bean1.setSPensionCompFormula(checkNull(String.valueOf(objData[i][5])));
					bean1.setSPensionMinAmt(checkNull(String.valueOf(objData[i][6])));

					list.add(bean1);
				}
				
				bean.setLstPensionConfiguration(list);
			}else{
			}
		}
		catch (Exception e) {
			logger.error("Error in PensionConfigurationModel method Model : " + e.getMessage());
		}
	}
	
	/**
	 * @method saveQuestionCategory()
	 * @purpose to store the Pension Configuration into "HRMS_PENSION_CONF" Table
	 * @param Bean to populate all data which store into database
	 */
	public boolean savePensionConfiguration(PensionConfiguration Bean,String pensionType) {
		boolean result = false;
		
		Object [][] pensionConfigData = null;
		
		logger.info("pensionType in update"+pensionType);
		/* Get Max for APPR_QUES_CATG_CODE */
		Object [][] objData = getSqlModel().getSingleResult(getQuery(2));				/* Retrieve the max 'PENS_CODE' */
		
		if (objData != null)
		{
			Bean.setSPensionCode(String.valueOf(objData [0][0]));						/* Set Max PensionCode */
			
			
			if (pensionType.equals("1")) {
				
				pensionConfigData = new Object [1][10];
				pensionConfigData[0][0] = Bean.getSPensionCode();
				pensionConfigData[0][1] = "1"; 					/* Pension Type Code */
				pensionConfigData[0][2] = Bean.getSPensionEffFrm1();					/* Pension Effective from */
				pensionConfigData[0][3] = Bean.getSPensionMinService1(); 				/* Minimum Service */
				pensionConfigData[0][4] = Bean.getSPensionMaxService1(); 				/* Maximum Service */
				pensionConfigData[0][5] = Bean.getSPensionMinAmt1(); 					/* Minimum Amount */
				pensionConfigData[0][6] = "1"; 											/* Employee Status '1#Retirement, 2#Voluntary, 3#Compulsory, 4#Death' */

				pensionConfigData[0][7] = Bean.getSPensionEmolFormula1(); 				/* Emoluments Formula (EF) */
				pensionConfigData[0][8] = Bean.getSPensionEmolMonths1(); 				/* Months for calculating Average Emoluments (MAE)  */
				pensionConfigData[0][9] = Bean.getSPensionFormula1(); 					/* Pension Formula */
				
				result = getSqlModel().singleExecute(getQuery(3), pensionConfigData);
				
			} else if (pensionType.equals("2")) {
				
				pensionConfigData = new Object [1][11];
				pensionConfigData[0][0] = Bean.getSPensionCode();
				pensionConfigData[0][1] = "2"; 					/* Pension Type Code */
				pensionConfigData[0][2] = Bean.getSPensionEffFrm2(); 					/* Pension Effective from */
				pensionConfigData[0][3] = Bean.getSPensionMinService2(); 				/* Minimum Service */
				pensionConfigData[0][4] = Bean.getSPensionMaxService2(); 				/* Maximum Service */
				pensionConfigData[0][5] = Bean.getSPensionMinAmt2();					/* Minimum Amount */
				pensionConfigData[0][6] = "2"; 											/* Employee Status '1#Retirement, 2#Voluntary, 3#Compulsory, 4#Death' */

				pensionConfigData[0][7] = Bean.getSPensionEmolFormula2(); 				/* Emoluments Formula (EF) */
				pensionConfigData[0][8] = Bean.getSPensionEmolMonths2(); 				/* Months for calculating Average Emoluments (MAE)  */
				pensionConfigData[0][9] = Bean.getSPensionFormula2(); 					/* Pension Formula */
				pensionConfigData[0][10]= Bean.getSPensionVolWeiyears2();				/* Weight age years to be added in qualifying Service */
				
				result = getSqlModel().singleExecute(getQuery(4), pensionConfigData);
			} else if (pensionType.equals("3")) {
				
				pensionConfigData = new Object [1][10];
				pensionConfigData[0][0] = Bean.getSPensionCode();
				pensionConfigData[0][1] = "3"; 					/* Pension Type Code */
				pensionConfigData[0][2] = Bean.getSPensionEffFrm3(); 					/* Pension Effective from */
				pensionConfigData[0][3] = Bean.getSPensionMinService3();				/* Maximum Service */
				pensionConfigData[0][4] = Bean.getSPensionMaxService3(); 				/* Minimum Service */
				pensionConfigData[0][5] = Bean.getSPensionMinAmt3(); 					/* Minimum Amount */
				pensionConfigData[0][6] = "3"; 											/* Employee Status '1#Retirement, 2#Voluntary, 3#Compulsory, 4#Death' */

				pensionConfigData[0][7] = Bean.getSPensionEmolFormula3(); 				/* Emoluments Formula (EF) */
				pensionConfigData[0][8] = Bean.getSPensionEmolMonths3(); 				/* Months for calculating Average Emoluments (MAE)  */
				pensionConfigData[0][9] = Bean.getSPensionFormula3(); 					/* Pension Formula */
				
				result = getSqlModel().singleExecute(getQuery(3), pensionConfigData);
				
			} else if (pensionType.equals("4")) {
				
				pensionConfigData = new Object [1][11];
				pensionConfigData[0][0] = Bean.getSPensionCode();
				pensionConfigData[0][1] = "4"; 					/* Pension Type Code */
				pensionConfigData[0][2] = Bean.getSPensionEffFrm4(); 					/* Pension Effective from */
				pensionConfigData[0][3] = Bean.getSPensionMinService4(); 				/* Minimum Service */
				pensionConfigData[0][4] = Bean.getSPensionMaxService4(); 				/* Maximum Service */
				pensionConfigData[0][5] = Bean.getSPensionMinAmt4(); 					/* Minimum Amount */
				pensionConfigData[0][6] = "4"; 											/* Employee Status '1#Retirement, 2#Voluntary, 3#Compulsory, 4#Death' */

				pensionConfigData[0][7] = Bean.getSPensionEmolFormula4(); 				/* Emoluments Formula (EF) */
				pensionConfigData[0][8] = Bean.getSPensionEmolMonths4(); 				/* Months for calculating Average Emoluments (MAE)  */
				pensionConfigData[0][9] = Bean.getSPensionFormula4(); 					/* Pension Formula (If Compensation paid) */
				pensionConfigData[0][10] = Bean.getSPensionCompFormula4();				/* Pension Formula (If Compensation not paid) */
				
				result = getSqlModel().singleExecute(getQuery(8), pensionConfigData);
			
			} else if (pensionType.equals("5")) {
				pensionConfigData = new Object [1][6];
				pensionConfigData[0][0] = Bean.getSPensionAmtCreditCode1();
				pensionConfigData[0][1] = Bean.getSPensionAmtCreditValue1();
				
				pensionConfigData[0][2] = Bean.getSPensionAmtCreditCode2();
				pensionConfigData[0][3] = Bean.getSPensionAmtCreditValue2();
				
				pensionConfigData[0][4] = Bean.getSPensionAmtCreditCode3();
				pensionConfigData[0][5] = Bean.getSPensionAmtCreditValue3();
				
				/* Delete already exist record */
				result = getSqlModel().singleExecute(getQuery(10));
				
				/* Insert new record */
				result = getSqlModel().singleExecute(getQuery(11), pensionConfigData);
			}
		}
		
		//result = getSqlModel().singleExecute(getQuery(3), pensionConfigData);
		return result; 
	}
	
	public void calforedit(PensionConfiguration bean) throws Exception {
		Object [] inputData = new Object [1];
		inputData [0] = bean.getSPensionCode();
		
		Object [][]outputData = getSqlModel().getSingleResult(getQuery(5), inputData);
		
		if (outputData != null && outputData.length > 0) {
			
			if (String.valueOf(outputData [0][1]).equals("1")) {
				bean.setSPensionCode(checkNull(String.valueOf(outputData [0][0])));
				bean.setSPensionTypeCode(checkNull(String.valueOf(outputData [0][1])));
				bean.setSPensionEffFrm1(checkNull(String.valueOf(outputData [0][2])));
				bean.setSPensionMinService1(checkNull(String.valueOf(outputData [0][3])));
				bean.setSPensionMaxService1(checkNull(String.valueOf(outputData [0][4])));
				bean.setSPensionMinAmt1(checkNull(String.valueOf(outputData [0][5])));
				bean.setSPensionEmpStatus1(checkNull(String.valueOf(outputData [0][6])));
				bean.setSPensionEmolFormula1(checkNull(String.valueOf(outputData [0][7])));
				bean.setSPensionEmolMonths1(checkNull(String.valueOf(outputData [0][8])));
				bean.setSPensionFormula1(checkNull(String.valueOf(outputData [0][9])));
				bean.setSPensionVolWeiyears1(checkNull(String.valueOf(outputData [0][10])));
				bean.setSPensionCompFormula1(checkNull(String.valueOf(outputData [0][11])));
				
			} else if (String.valueOf(outputData [0][1]).equals("2")) {
				bean.setSPensionCode(checkNull(String.valueOf(outputData [0][0])));
				bean.setSPensionTypeCode(checkNull(String.valueOf(outputData [0][1])));
				bean.setSPensionEffFrm2(checkNull(String.valueOf(outputData [0][2])));
				bean.setSPensionMinService2(checkNull(String.valueOf(outputData [0][3])));
				bean.setSPensionMaxService2(checkNull(String.valueOf(outputData [0][4])));
				bean.setSPensionMinAmt2(checkNull(String.valueOf(outputData [0][5])));
				bean.setSPensionEmpStatus2(checkNull(String.valueOf(outputData [0][6])));
				bean.setSPensionEmolFormula2(checkNull(String.valueOf(outputData [0][7])));
				bean.setSPensionEmolMonths2(checkNull(String.valueOf(outputData [0][8])));
				bean.setSPensionFormula2(checkNull(String.valueOf(outputData [0][9])));
				bean.setSPensionVolWeiyears2(checkNull(String.valueOf(outputData [0][10])));
				bean.setSPensionCompFormula2(checkNull(String.valueOf(outputData [0][11])));
				
			} else if (String.valueOf(outputData [0][1]).equals("3")) {
				bean.setSPensionCode(checkNull(String.valueOf(outputData [0][0])));
				bean.setSPensionTypeCode(checkNull(String.valueOf(outputData [0][1])));
				bean.setSPensionEffFrm3(checkNull(String.valueOf(outputData [0][2])));
				bean.setSPensionMinService3(checkNull(String.valueOf(outputData [0][3])));
				bean.setSPensionMaxService3(checkNull(String.valueOf(outputData [0][4])));
				bean.setSPensionMinAmt3(checkNull(String.valueOf(outputData [0][5])));
				bean.setSPensionEmpStatus3(checkNull(String.valueOf(outputData [0][6])));
				bean.setSPensionEmolFormula3(checkNull(String.valueOf(outputData [0][7])));
				bean.setSPensionEmolMonths3(checkNull(String.valueOf(outputData [0][8])));
				bean.setSPensionFormula3(checkNull(String.valueOf(outputData [0][9])));
				bean.setSPensionVolWeiyears3(checkNull(String.valueOf(outputData [0][10])));
				bean.setSPensionCompFormula3(checkNull(String.valueOf(outputData [0][11])));
				
			} else if (String.valueOf(outputData [0][1]).equals("4")) {
				bean.setSPensionCode(checkNull(String.valueOf(outputData [0][0])));
				bean.setSPensionTypeCode(checkNull(String.valueOf(outputData [0][1])));
				bean.setSPensionEffFrm4(checkNull(String.valueOf(outputData [0][2])));
				bean.setSPensionMinService4(checkNull(String.valueOf(outputData [0][3])));
				bean.setSPensionMaxService4(checkNull(String.valueOf(outputData [0][4])));
				bean.setSPensionMinAmt4(checkNull(String.valueOf(outputData [0][5])));
				bean.setSPensionEmpStatus4(checkNull(String.valueOf(outputData [0][6])));
				bean.setSPensionEmolFormula4(checkNull(String.valueOf(outputData [0][7])));
				bean.setSPensionEmolMonths4(checkNull(String.valueOf(outputData [0][8])));
				bean.setSPensionFormula4(checkNull(String.valueOf(outputData [0][9])));
				bean.setSPensionVolWeiyears4(checkNull(String.valueOf(outputData [0][10])));
				bean.setSPensionCompFormula4(checkNull(String.valueOf(outputData [0][11])));
			}
		}
	}
	
	/** 
	 * @method name : checkNull()
	 * @purpose     : Check the String is null or not
	 * @parameter 	: String
	 * @return type : String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) 
		{
			return "";
		}  
		else 
		{
			return result;
		} 
	}
	
	/**
	 * @method name : updateQuestionCategory()
	 * @purpose		: to update the selected question category in "PAS_APPR_QUESTION_CATGORY" Table
	 * @param 		: bean to pop up all the form field values
	 * @return  	: boolean
	 */
	
	public boolean updatePensionConfiguration(PensionConfiguration bean,String pensionType) {
		boolean result = false;
		
		logger.info("pensionType in update"+pensionType);
		try 
		{
			Object [][] updateData  = new Object [1][12];
			
			if (pensionType.equals("1")) {
				
				updateData[0][0] = "1";
				updateData[0][1] = bean.getSPensionEffFrm1();
				updateData[0][2] = bean.getSPensionMinService1();
				updateData[0][3] = bean.getSPensionMaxService1();
				updateData[0][4] = bean.getSPensionMinAmt1();
				updateData[0][5] = bean.getSPensionEmpStatus1();
				updateData[0][6] = bean.getSPensionEmolFormula1();
				updateData[0][7] = bean.getSPensionEmolMonths1();
				updateData[0][8] = bean.getSPensionFormula1();
				updateData[0][9] = bean.getSPensionVolWeiyears1();
				updateData[0][10] = bean.getSPensionCompFormula1();

				updateData[0][11] = bean.getSPensionCode();
				
			} else if (pensionType.equals("2")) {
				
				updateData[0][0] = "2";
				updateData[0][1] = bean.getSPensionEffFrm2();
				updateData[0][2] = bean.getSPensionMinService2();
				updateData[0][3] = bean.getSPensionMaxService2();
				updateData[0][4] = bean.getSPensionMinAmt2();
				updateData[0][5] = bean.getSPensionEmpStatus2();
				updateData[0][6] = bean.getSPensionEmolFormula2();
				updateData[0][7] = bean.getSPensionEmolMonths2();
				updateData[0][8] = bean.getSPensionFormula2();
				updateData[0][9] = bean.getSPensionVolWeiyears2();
				updateData[0][10] = bean.getSPensionCompFormula2();
				
				updateData[0][11] = bean.getSPensionCode();

			} else if (pensionType.equals("3")) {
				
				updateData[0][0] = "3";
				updateData[0][1] = bean.getSPensionEffFrm3();
				updateData[0][2] = bean.getSPensionMinService3();
				updateData[0][3] = bean.getSPensionMaxService3();
				updateData[0][4] = bean.getSPensionMinAmt3();
				updateData[0][5] = bean.getSPensionEmpStatus3();
				updateData[0][6] = bean.getSPensionEmolFormula3();
				updateData[0][7] = bean.getSPensionEmolMonths3();
				updateData[0][8] = bean.getSPensionFormula3();
				updateData[0][9] = bean.getSPensionVolWeiyears3();
				updateData[0][10] = bean.getSPensionCompFormula3();
				
				updateData[0][11] = bean.getSPensionCode();

			} else if (pensionType.equals("4")) {
				
				updateData[0][0] = "4";
				updateData[0][1] = bean.getSPensionEffFrm4();
				updateData[0][2] = bean.getSPensionMinService4();
				updateData[0][3] = bean.getSPensionMaxService4();
				updateData[0][4] = bean.getSPensionMinAmt4();
				updateData[0][5] = bean.getSPensionEmpStatus4();
				updateData[0][6] = bean.getSPensionEmolFormula4();
				updateData[0][7] = bean.getSPensionEmolMonths4();
				updateData[0][8] = bean.getSPensionFormula4();
				updateData[0][9] = bean.getSPensionVolWeiyears4();
				updateData[0][10] = bean.getSPensionCompFormula4();
				
				updateData[0][11] = bean.getSPensionCode();
				
			}
 			
			result = getSqlModel().singleExecute(getQuery(6), updateData);
			
		} 
		catch (Exception e) {
			logger.error("Error in method Model : " + e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * @method deleteQuestionCategory()
	 * @purpose to delete the selected Question Category from "PAS_APPR_QUESTION_CATGORY" Table
	 * @param bean to pop up all the form  field values
	 * @return boolean
	 */
	public boolean deletePensionConfiguration(String code[]) {
		boolean result = false;
		try 
		{
			Object [][] aPensionCode = new Object[code.length][1];
			for (int i =0; i < code.length; i++)
			{
				if (!code[i].equals(""))
				{
					aPensionCode[i][0] = String.valueOf(code[i]);
				}
			}
			result = getSqlModel().singleExecute(getQuery(7), aPensionCode);
		} 
		catch (Exception e) {
			logger.error("Error in deletePensionConfiguration() method model : " + e.getMessage());
		}
		
		return result;
	}
	
	public void getPensionConfigurationDetails (PensionConfiguration bean)
	{
		try {
			
			Object [] inputData = new Object [1];
			inputData [0] = bean.getSPensionCode();
			
			Object [][]outputData = getSqlModel().getSingleResult(getQuery(5), inputData);
			
			if (outputData != null && outputData.length > 0) {
				
				if (String.valueOf(outputData [0][1]).equals("1")) {
					bean.setSPensionCode(checkNull(String.valueOf(outputData [0][0])));
					bean.setSPensionTypeCode(checkNull(String.valueOf(outputData [0][1])));
					bean.setSPensionEffFrm1(checkNull(String.valueOf(outputData [0][2])));
					bean.setSPensionMinService1(checkNull(String.valueOf(outputData [0][3])));
					bean.setSPensionMaxService1(checkNull(String.valueOf(outputData [0][4])));
					bean.setSPensionMinAmt1(checkNull(String.valueOf(outputData [0][5])));
					bean.setSPensionEmpStatus1(checkNull(String.valueOf(outputData [0][6])));
					bean.setSPensionEmolFormula1(checkNull(String.valueOf(outputData [0][7])));
					bean.setSPensionEmolMonths1(checkNull(String.valueOf(outputData [0][8])));
					bean.setSPensionFormula1(checkNull(String.valueOf(outputData [0][9])));
					
				} else if (String.valueOf(outputData [0][1]).equals("2")) {
					bean.setSPensionCode(checkNull(String.valueOf(outputData [0][0])));
					bean.setSPensionTypeCode(checkNull(String.valueOf(outputData [0][1])));
					bean.setSPensionEffFrm2(checkNull(String.valueOf(outputData [0][2])));
					bean.setSPensionMinService2(checkNull(String.valueOf(outputData [0][3])));
					bean.setSPensionMaxService2(checkNull(String.valueOf(outputData [0][4])));
					bean.setSPensionMinAmt2(checkNull(String.valueOf(outputData [0][5])));
					bean.setSPensionEmpStatus2(checkNull(String.valueOf(outputData [0][6])));
					bean.setSPensionEmolFormula2(checkNull(String.valueOf(outputData [0][7])));
					bean.setSPensionEmolMonths2(checkNull(String.valueOf(outputData [0][8])));
					bean.setSPensionFormula2(checkNull(String.valueOf(outputData [0][9])));
					bean.setSPensionVolWeiyears2(checkNull(String.valueOf(outputData [0][10])));
					
				} else if (String.valueOf(outputData [0][1]).equals("3")) {
					bean.setSPensionCode(checkNull(String.valueOf(outputData [0][0])));
					bean.setSPensionTypeCode(checkNull(String.valueOf(outputData [0][1])));
					bean.setSPensionEffFrm3(checkNull(String.valueOf(outputData [0][2])));
					bean.setSPensionMinService3(checkNull(String.valueOf(outputData [0][3])));
					bean.setSPensionMaxService3(checkNull(String.valueOf(outputData [0][4])));
					bean.setSPensionMinAmt3(checkNull(String.valueOf(outputData [0][5])));
					bean.setSPensionEmpStatus3(checkNull(String.valueOf(outputData [0][6])));
					bean.setSPensionEmolFormula3(checkNull(String.valueOf(outputData [0][7])));
					bean.setSPensionEmolMonths3(checkNull(String.valueOf(outputData [0][8])));
					bean.setSPensionFormula3(checkNull(String.valueOf(outputData [0][9])));
					
				} else if (String.valueOf(outputData [0][1]).equals("4")) {
					bean.setSPensionCode(checkNull(String.valueOf(outputData [0][0])));
					bean.setSPensionTypeCode(checkNull(String.valueOf(outputData [0][1])));
					bean.setSPensionEffFrm4(checkNull(String.valueOf(outputData [0][2])));
					bean.setSPensionMinService4(checkNull(String.valueOf(outputData [0][3])));
					bean.setSPensionMaxService4(checkNull(String.valueOf(outputData [0][4])));
					bean.setSPensionMinAmt4(checkNull(String.valueOf(outputData [0][5])));
					bean.setSPensionEmpStatus4(checkNull(String.valueOf(outputData [0][6])));
					bean.setSPensionEmolFormula4(checkNull(String.valueOf(outputData [0][7])));
					bean.setSPensionEmolMonths4(checkNull(String.valueOf(outputData [0][8])));
					bean.setSPensionFormula4(checkNull(String.valueOf(outputData [0][9])));
					
				}
			}
			
		} catch (Exception e) {
			logger.error("Error in getPensionConfigurationDetails() : " + e.getMessage());
		}
	}
	
	public void getPensionConfigurationDetailsThroughSearch(PensionConfiguration bean)
	{
		try {
			
			Object [] inputData = new Object [1];
			inputData [0] = bean.getSPensionCode();
			
			Object [][]outputData = getSqlModel().getSingleResult(getQuery(5), inputData);
			
			if (outputData != null && outputData.length > 0) {
				
					bean.setSPensionCode(checkNull(String.valueOf(outputData [0][0])));
					bean.setSPensionTypeCode(checkNull(String.valueOf(outputData [0][1])));
					bean.setSPensionEffFrm(checkNull(String.valueOf(outputData [0][2])));
					bean.setSPensionMinService(checkNull(String.valueOf(outputData [0][3])));
					bean.setSPensionMaxService(checkNull(String.valueOf(outputData [0][4])));
					bean.setSPensionMinAmt(checkNull(String.valueOf(outputData [0][5])));
					bean.setSPensionEmpStatus(checkNull(String.valueOf(outputData [0][6])));
					bean.setSPensionEmolFormula(checkNull(String.valueOf(outputData [0][7])));
					bean.setSPensionEmolMonths(checkNull(String.valueOf(outputData [0][8])));
					bean.setSPensionFormula(checkNull(String.valueOf(outputData [0][9])));
					bean.setSPensionVolWeiyears(checkNull(String.valueOf(outputData [0][10])));
					bean.setSPensionCompFormula(checkNull(String.valueOf(outputData [0][11])));
					bean.setSPensionAvgEmol("EF / MAE");
			}
			
		} catch (Exception e) {
			logger.error("Error in getPensionConfigurationDetails() : " + e.getMessage());
		}
	}
	
	public String getTabName(int PensionType){
		switch (PensionType) {
		case 1:return "Superannuation Pension ";
		
		case 2:return "Voluntary Pension";
		
		case 3:return "Comuplsory Retirement Pension";
		
		case 4:return "Death Pension";
		
		case 5:return "Pension Amount Split";
		
		default:return "Superannuation Pension";
			
		}
	}
	
	public void getPensionSplitAmpunt(PensionConfiguration bean,HttpServletRequest request) {
		try {
			Object [][]outputData = getSqlModel().getSingleResult(getQuery(9));
			
			if (outputData != null && outputData.length > 0) {
				bean.setSPensionAmtCreditCode1(String.valueOf(outputData[0][0]));
				bean.setSPensionAmtCreditValue1(String.valueOf(outputData[0][1]));
				bean.setSPensionAmtCreditHead1(String.valueOf(outputData[0][2]));
				
				bean.setSPensionAmtCreditCode2(String.valueOf(outputData[0][3]));
				bean.setSPensionAmtCreditValue2(String.valueOf(outputData[0][4]));
				bean.setSPensionAmtCreditHead2(String.valueOf(outputData[0][5]));
				
				bean.setSPensionAmtCreditCode3(String.valueOf(outputData[0][6]));
				bean.setSPensionAmtCreditValue3(String.valueOf(outputData[0][7]));
				bean.setSPensionAmtCreditHead3(String.valueOf(outputData[0][8]));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute("tabName", getTabName(Integer.parseInt(bean.getSPensionTypeCode())));
	}
	
	
	public boolean addPension(PensionConfiguration bean) {
		Object [][] addObj  = new Object[1][4];

		 String query1 = "SELECT NVL(MAX(CREDIT_CONF_CODE),0)+1 FROM  HRMS_PENSION_CREDIT_CONF";
		 Object[][] code = this.getSqlModel().getSingleResult(query1);

		addObj[0][0] = this.checkNull(String.valueOf(code[0][0]));
		addObj[0][1] = bean.getCreditHeadCode().trim();
		addObj[0][2] = bean.getCreditFormula().trim();
		addObj[0][3] = bean.getPensionValue().trim();
		bean.setParaId(String.valueOf(code[0][0]));

		return this.getSqlModel().singleExecute(this.getQuery(12), addObj); 
		
	}
	
	public boolean updatePension(PensionConfiguration bean) {
		
		 Object [][] updateObj = new Object[1][4];

		updateObj[0][0] = bean.getCreditHeadCode().trim();
		updateObj[0][1] = bean.getCreditFormula().trim();
		updateObj[0][2] = bean.getPensionValue().trim();
		updateObj[0][3] = bean.getParaId().trim();
		return this.getSqlModel().singleExecute(this.getQuery(13), updateObj);

	}
	

	public void getPensionDetailsList(PensionConfiguration bean) {
		
		String query = "select CREDIT_CONF_CODE,HRMS_CREDIT_HEAD.CREDIT_CODE,HRMS_CREDIT_HEAD.CREDIT_NAME,DECODE(HRMS_PENSION_CREDIT_CONF.CREDIT_TYPE, 'F','Fixed','P','Pension') AS TYPE,CREDIT_VALUES " + " from HRMS_PENSION_CREDIT_CONF " 
		+ " LEFT JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PENSION_CREDIT_CONF.CREDIT_CODE) ";
		Object[][] data = getSqlModel().getSingleResult(query);

		bean.setCreditDetailsList(null);
		/** DATA IN A LIST IS NULL IN STARTING* */
		List<PensionConfiguration> list = new ArrayList<PensionConfiguration>();
		
		if (data !=null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {

				PensionConfiguration bean1 = new PensionConfiguration();
				bean1.setEditDataId(checkNull(String.valueOf(data[i][0])));
				bean1.setCreditHeadCode(checkNull(String.valueOf(data[i][1])));
				bean1.setCreditHead(checkNull(String.valueOf(data[i][2])));
				bean1.setCreditFormula(checkNull(String.valueOf(data[i][3])));
				bean1.setPensionValue(checkNull(String.valueOf(data[i][4])));
				list.add(bean1);
				/** DATA ADDED INTO LIST* */
			}
			bean.setCreditDetailsList(list);

		}
	}

	/**
	 * @param bean 
	 * @param editCode
	 */
	public void editPensionRecord(PensionConfiguration bean, String editCode) {
System.out.println("editCode here ----------------"+ editCode);
		try {
			String query = " SELECT CREDIT_CONF_CODE,HRMS_CREDIT_HEAD.CREDIT_CODE,HRMS_CREDIT_HEAD.CREDIT_NAME,HRMS_PENSION_CREDIT_CONF.CREDIT_TYPE AS TYPE,CREDIT_VALUES "  
			+ " from HRMS_PENSION_CREDIT_CONF "
			+ " LEFT JOIN HRMS_CREDIT_HEAD ON(HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_PENSION_CREDIT_CONF.CREDIT_CODE) "
			+  " where CREDIT_CONF_CODE = " + editCode;

			Object[][] data = getSqlModel().getSingleResult(query);
			if(data !=null && data.length > 0 ) { 
				for (int i = 0; i < data.length; i++) {
				
				bean.setEditDataId(checkNull(String.valueOf(data[0][0])));
				bean.setCreditHeadCode(checkNull(String.valueOf(data[0][1])));
				bean.setCreditHead(checkNull(String.valueOf(data[0][2])));
				System.out.println("data[0][3] ===="+ data[0][3]);
				bean.setCreditFormula(checkNull(String.valueOf(data[i][3])));
				bean.setPensionValue(checkNull(String.valueOf(data[0][4])));
				
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Purpose - Used to Delete Perticular Pension Record.
	 * @param bean - to get code for deletion. 
	 * @return true/false.
	 */
	public boolean deletePensionRecord(PensionConfiguration bean ,String deleteCode) {
		boolean result = false;

		final String delQuery = "DELETE FROM HRMS_PENSION_CREDIT_CONF WHERE CREDIT_CONF_CODE =" + deleteCode;
		result = this.getSqlModel().singleExecute(delQuery);
		return result;
	}

	
/*	public void addFormula(Object[] srNo, Object[] creditheadName, Object[] formula, Object[] value, PensionConfiguration bean1) {
		System.out.println("111111111111");
		System.out.println(srNo);
		System.out.println("creditheadName"+creditheadName);
		System.out.println("formula"+ formula);
		System.out.println("value" + value);
		
		List<PensionConfiguration> tableList = new ArrayList<PensionConfiguration>();
		
		for (int i = 0; i < srNo.length; i++) {
		PensionConfiguration bean = new PensionConfiguration();
		bean.setSrNo(String.valueOf(i + 1));
		bean.setCreditHeadItt(String.valueOf(creditheadName[i]));
		bean.setCreditFormulaItt(String.valueOf(formula[i]));
		bean.setValueItt(String.valueOf(value[i]));
		
		if (formula[i].equals("Fixed")) {
			bean.setValueItt("Fixed");
		}
		if (formula[i].equals("Pension")) {
			bean.setValueItt("Pension");
		}
		
		tableList.add(bean);
		
		} 	
		bean1.setCreditDetailsList(tableList);
	
	}*/
	
}
