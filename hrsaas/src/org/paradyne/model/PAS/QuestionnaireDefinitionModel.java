package org.paradyne.model.PAS;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.QuestionnaireDefinition;
import org.paradyne.lib.ModelBase;

public class QuestionnaireDefinitionModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(QuestionnaireDefinitionModel.class);
	
	/**
	 * @method showQuestionCategory()
	 * @purpose to retrieve Question Category from "PAS_APPR_QUESTIONNAIRE" Table
	 * @param bean to populate all data which retrieve from database
	 */
	
	public void showQuestionList(QuestionnaireDefinition bean, HttpServletRequest request) throws Exception {
		logger.info("In QuestionnaireDefinition.showQuestionCategory() method Model");
		
		try 
		{
			Object [][] objData = getSqlModel().getSingleResult(getQuery(1));
			ArrayList<Object> list = new ArrayList<Object>();
			
			int REC_TOTAL = 0;
			int To_TOT = 20;
			int From_TOT = 0;
			int pg1 = 0;
			int PageNo1 = 1;
			REC_TOTAL = objData.length;
			int no_of_pages = Math.round(REC_TOTAL / 20);
			double row = (double) objData.length / 20.0;
			java.math.BigDecimal value1 = new java.math.BigDecimal(row);
			int rowCount1 = Integer.parseInt(value1.setScale(0,
					java.math.BigDecimal.ROUND_UP).toString());
			
			request.setAttribute("abc", rowCount1);
			if (String.valueOf(bean.getMyPage()).equals("null")
					|| String.valueOf(bean.getMyPage()).equals(null)
					|| String.valueOf(bean.getMyPage()).equals("")) 
			{
				PageNo1 = 1;
				From_TOT = 0;
				To_TOT = 20;
				if (To_TOT > objData.length) 
				{
					To_TOT = objData.length;
				}
				bean.setMyPage("1");
			}
			else 
			{
				pg1 = Integer.parseInt(bean.getMyPage());
				PageNo1 = pg1;

				if (pg1 == 1) {
					From_TOT = 0;
					To_TOT = 20;
				}
				else {
					To_TOT = To_TOT * pg1;
					From_TOT = (To_TOT - 20);
				}
				if (To_TOT > objData.length) {
					To_TOT = objData.length;
				}
			}
			request.setAttribute("xyz", PageNo1);
			
			QuestionnaireDefinition bean1 = null;
			if(objData != null && objData.length != 0)
			{
				for (int i = From_TOT; i < To_TOT; i++) 
				{
					bean1  = new QuestionnaireDefinition(); 
					bean1.setSQuestionCode(String.valueOf(objData[i][0]));			/* Question Code */
					bean1.setSQuestion(String.valueOf(objData[i][1]));				/* Question */
					//bean1.setSQuestionType(String.valueOf(objData[i][2]));			/* Question Type ('O','D')  */
					if ("O".equalsIgnoreCase(String.valueOf(objData[i][2])))
					{
						bean1.setSQuestionType("Objective");
					}
					else if ("D".equalsIgnoreCase(String.valueOf(objData[i][2])))
					{
						bean1.setSQuestionType("Descriptive");
					}
					
					//bean1.setSQuestionStatus(String.valueOf(objData[i][3]));		/* Question Status ('A','D') */
					if ("A".equalsIgnoreCase(String.valueOf(objData[i][3])))
					{
						bean1.setSQuestionStatus("Active");
					}
					else if ("D".equalsIgnoreCase(String.valueOf(objData[i][3])))
					{
						bean1.setSQuestionStatus("De-Active");
					}
					
					//bean1.setSQuestionMandatory(String.valueOf(objData[i][4]));		/* Question Mandatory ('Y','N') */
					if ("Y".equalsIgnoreCase(String.valueOf(objData[i][4])))
					{
						bean1.setSQuestionMandatory("Yes");
					}
					else if ("N".equalsIgnoreCase(String.valueOf(objData[i][4])))
					{
						bean1.setSQuestionMandatory("No");
					}
					
					
					bean1.setSQuestionCategoryCode(String.valueOf(objData[i][5]));	/* Question Category Code */
					bean1.setSQuestionCategoryName(String.valueOf(objData[i][6]));	/* Question Category Name */
					bean1.setSAnwserLimit(String.valueOf(objData[i][7]));			/* Answer Character Limit */
					
					list.add(bean1);
				}
				
				bean.setLstQuestion(list);
				bean.setReadFlag("true");
			} 
		}
		catch (Exception e) 
		{
			logger.error("Error in QuestionnaireDefinition.showQuestionCategory() method Model : " + e.getMessage());
		}
	}
	
	public void getQuestionCategoryList(QuestionnaireDefinition bean )
	{
		Object [][] objData = getSqlModel().getSingleResult(getQuery(6));
		HashMap list = new HashMap ();
		
		for (int i = 0; i < objData.length ; i++) 
		{
			list.put(String.valueOf(objData[i][0]), String.valueOf(objData[i][1]));
		}
		
		/* Sort the list */
		list = (HashMap<Object, Object>)org.paradyne.lib.Utility.sortMapByValue(list, null, true);
		bean.setLstQuestionCategory(list);
	}

	/**
	 * @method saveQuestionCategory()
	 * @purpose to store the Question Category into "PAS_APPR_QUESTIONNAIRE" Table
	 * @param Bean to populate all data which store into database
	 */
	public boolean saveQuestion(QuestionnaireDefinition Bean) 
	{
		boolean result = false;
		
		try 
		{
		
			Object [][] questionCategoryData = new Object [1][7];
			
			/* Get Max for APPR_QUES_CATG_CODE */
			Object [][] objData = getSqlModel().getSingleResult(getQuery(7));
			
			if (objData != null)
			{
				Bean.setSQuestionCode((String.valueOf(objData [0][0]))) ;					/* Get Max Question Code from database */
				
				questionCategoryData [0][0] = Bean.getSQuestionCode();						/* Question Code */
				questionCategoryData [0][1] = Bean.getSQuestion();							/* Question */
				questionCategoryData [0][2] = Bean.getSQuestionType();						/* Question Type */
				questionCategoryData [0][3] = Bean.getSQuestionStatus();					/* Status */
				questionCategoryData [0][4] = Bean.getSQuestionMandatory();					/* Mandatory Question */
				questionCategoryData [0][5] = Bean.getSQuestionCategoryCode();				/* Question Category Code */
				
				if (null !=  Bean.getSQuestionType() && "O".equalsIgnoreCase( Bean.getSQuestionType()))
				{
					questionCategoryData [0][6] = "500";									/* if Object question type the default length = 500 */
				}
				else
				{
					questionCategoryData [0][6] = Bean.getSAnwserLimit();					/* Answer Character Limit */
				}
			}
			
			return result = getSqlModel().singleExecute(getQuery(2), questionCategoryData);
		}
		catch (Exception e) 
		{
			logger.error("Error in QuestionnaireDefinition.saveQuestion() method Model : " + e.getMessage());
		}	
		return result;
	}
	
	/**
	 * @method deleteMultipleQuestion()
	 * @purpose to delete the selected Question Category from "PAS_APPR_QUESTIONNAIRE" Table
	 * @param bean to pop up all the form  field values
	 * @return boolean
	 */
	public boolean deleteMultipleQuestion(String code[]) 
	{
		logger.info("In deleteMultipleQuestion() method Model");
		
		boolean result = false;
		try 
		{
			Object [][] applicationCode = new Object[code.length][1];
			for (int i =0; i < code.length; i++)
			{
				if (!code[i].equals(""))
				{
					applicationCode[i][0] = String.valueOf(code[i]);
				}
			}
			result = getSqlModel().singleExecute(getQuery(4), applicationCode);
		} 
		catch (Exception e) 
		{
			logger.error("Error in deleteMultipleQuestion() method model : " + e.getMessage());
		}
		
		return result;
	}
	
	public void calforedit(QuestionnaireDefinition bean) throws Exception 
	{
		Object [] questionCode = new Object [1];
		questionCode [0] = bean.getHiddencode();
		
		Object [][]data = getSqlModel().getSingleResult(getQuery(5), questionCode);
		
		bean.setSQuestionCode(checkNull(String.valueOf(data [0][0])));
		bean.setSQuestion(checkNull(String.valueOf(data [0][1])));
		bean.setSQuestionType(checkNull(String.valueOf(data[0][2])));
		bean.setSQuestionStatus(checkNull(String.valueOf(data[0][3])));
		bean.setSAnwserLimit(checkNull(String.valueOf(data[0][4])));
		bean.setSQuestionMandatory(checkNull(String.valueOf(data[0][5])));
		bean.setSQuestionCategoryCode(checkNull(String.valueOf(data[0][6])));
		bean.setSQuestionCategoryName(checkNull(String.valueOf(data[0][7])));
	}
	
	
	/**
	 * @method name : updateQuestion()
	 * @purpose		: to update the selected question category in "PAS_APPR_QUESTION_CATGORY" Table
	 * @param 		: bean to pop up all the form field values
	 * @return  	: boolean
	 */
	
	public boolean updateQuestion(QuestionnaireDefinition bean) {
		logger.info("In updateQuestionCategory() method Model");
		
		boolean result = false;
		
		try 
		{
			Object [][] updateApplicationData  = new Object [1][7];
			updateApplicationData [0][0] = bean.getSQuestion();					/* Question */
			updateApplicationData [0][1] = bean.getSQuestionType();				/* Question Type */
			updateApplicationData [0][2] = bean.getSQuestionStatus();			/* Question Status */
			updateApplicationData [0][3] = bean.getSQuestionMandatory();		/* Question Mandatory */
			updateApplicationData [0][4] = bean.getSQuestionCategoryCode();		/* Question Category Code */
			
			//updateApplicationData [0][5] = bean.getSAnwserLimit();			/* Answer Character Limit */
			
			if (null !=  bean.getSQuestionType() && "O".equalsIgnoreCase( bean.getSQuestionType()))
			{
				updateApplicationData [0][5] = "500";							/* if Object question type the default length = 500 */
			}
			else
			{
				updateApplicationData [0][5] =  bean.getSAnwserLimit();			/* Answer Character Limit */
			}
			
			updateApplicationData [0][6] = bean.getSQuestionCode();				/* Question Code */
			
			result = getSqlModel().singleExecute(getQuery(3), updateApplicationData);
			
		} 
		catch (Exception e) {
			logger.error("Error in updateQuestionCategory() method Model : " + e.getMessage());
		}
		
		return result;
	}
	
	public void getSelectedQuestionDetail(QuestionnaireDefinition bean) throws Exception 
	{
		Object[] questionCode = new Object [1];
		questionCode[0] = bean.getSQuestionCode();
		
		Object [][]data = getSqlModel().getSingleResult(getQuery(5), questionCode);
		
		if (data != null)
		{
			bean.setSQuestionCode(checkNull(String.valueOf(data [0][0])));
			bean.setSQuestion(checkNull(String.valueOf(data [0][1])));
			bean.setSQuestionType(checkNull(String.valueOf(data[0][2])));
			bean.setSQuestionStatus(checkNull(String.valueOf(data[0][3])));
			bean.setSAnwserLimit(checkNull(String.valueOf(data[0][4])));
			bean.setSQuestionMandatory(checkNull(String.valueOf(data[0][5])));
			bean.setSQuestionCategoryCode(checkNull(String.valueOf(data[0][6])));
			bean.setSQuestionCategoryName(checkNull(String.valueOf(data[0][7])));
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
	
	
}
