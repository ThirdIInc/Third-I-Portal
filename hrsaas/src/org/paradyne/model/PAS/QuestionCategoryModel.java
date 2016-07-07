package org.paradyne.model.PAS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.PAS.AppraisalSchedule;
import org.paradyne.bean.PAS.QuestionCategory;
import org.paradyne.lib.ModelBase;

public class QuestionCategoryModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(QuestionCategoryModel.class);
	
	
	/**
	 * @method saveQuestionCategory()
	 * @purpose to store the Question Category into "PAS_APPR_QUESTION_CATGORY" Table
	 * @param Bean to populate all data which store into database
	 */
	public boolean saveQuestionCategory(QuestionCategory Bean) {
		boolean result = false;
		
		Object [][] questionCategoryData = new Object [1][4];
		
		/* Get Max for APPR_QUES_CATG_CODE */
		Object [][] objData = getSqlModel().getSingleResult(getQuery(6));
		
		if (objData != null)
		{
			Bean.setSQstCategoryCode(String.valueOf(objData [0][0])) ;						/* Get Max Question Category Code from database */
			
			questionCategoryData [0][0] = Bean.getSQstCategoryCode();						/* Question Category Code */
			questionCategoryData [0][1] = Bean.getSQstCategoryName();						/* Category Name */
			questionCategoryData [0][2] = Bean.getSCategoryStatus();						/* Status */
			questionCategoryData [0][3] = Bean.getSCategoryDescription();					/* Description */
		}
		
		result = getSqlModel().singleExecute(getQuery(2), questionCategoryData);
		return result; 
	}
	
	/**
	 * @method showQuestionCategory()
	 * @purpose to retrieve Question Category from "PAS_APPR_QUESTION_CATGORY" Table
	 * @param bean to populate all data which retrieve from database
	 */
	
	public void showQuestionCategory(QuestionCategory bean, HttpServletRequest request) {
		logger.info("In showQuestionCategory() method Model");
		
		
		try 
		{
			Object [][] objData = getSqlModel().getSingleResult(getQuery(1));
			ArrayList<Object> list = new ArrayList<Object>();
			//QuestionCategory bean = null;
			
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
			
			QuestionCategory bean1 = null;
			if(objData != null && objData.length != 0)
			{
				//for (int i = 0; i < objData.length; i++) 
				for (int i = From_TOT; i < To_TOT; i++) 
				{
					bean1  = new QuestionCategory(); 
					bean1.setSQstCategoryCode(String.valueOf(objData[i][0]));			/* Question Category Code */
					bean1.setSQstCategoryName(String.valueOf(objData[i][1]));			/* Category Name */
					bean1.setSCategoryStatus(String.valueOf(objData[i][2]));			/* Status */
					
					if (null != String.valueOf(objData[i][2]) && String.valueOf(objData[i][2]).equalsIgnoreCase("A") )
					{
						bean1.setSCategoryStatusDesc("Active");
					}
					else if (null != String.valueOf(objData[i][2]) && String.valueOf(objData[i][2]).equalsIgnoreCase("D") )
					{
						bean1.setSCategoryStatusDesc("De-Active");
					}
					
					bean1.setSCategoryDescription(String.valueOf(objData[i][3]));		/* Description */
					list.add(bean1);
				}
				
				bean.setLstQuestionCategory(list);
				bean.setReadFlag("true");
			} 
		}
		catch (Exception e) {
			logger.error("Error in showQuestionCategory() method Model : " + e.getMessage());
		}
	}
	
	/**
	 * @method deleteQuestionCategory()
	 * @purpose to delete the selected Question Category from "PAS_APPR_QUESTION_CATGORY" Table
	 * @param bean to pop up all the form  field values
	 * @return boolean
	 */
	public boolean deleteQuestionCategory(String code[]) {
		logger.info("In deleteQuestionCategory() method Model");
		
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
		catch (Exception e) {
			logger.error("Error in deleteQuestionCategory() method model : " + e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * @method name : updateQuestionCategory()
	 * @purpose		: to update the selected question category in "PAS_APPR_QUESTION_CATGORY" Table
	 * @param 		: bean to pop up all the form field values
	 * @return  	: boolean
	 */
	
	public boolean updateQuestionCategory(QuestionCategory bean) {
		logger.info("In updateQuestionCategory() method Model");
		
		boolean result = false;
		
		try 
		{
			Object [][] updateApplicationData  = new Object [1][4];
			updateApplicationData [0][0] = bean.getSQstCategoryName();				/* Category Name */
			updateApplicationData [0][1] = bean.getSCategoryStatus();				/* Status */
			updateApplicationData [0][2] = bean.getSCategoryDescription();			/* Description */
			
			updateApplicationData [0][3] = bean.getSQstCategoryCode();				/* Question Category Code */
			
			result = getSqlModel().singleExecute(getQuery(3), updateApplicationData);
			
		} 
		catch (Exception e) {
			logger.error("Error in updateQuestionCategory() method Model : " + e.getMessage());
		}
		
		return result;
	}
	
	public void calforedit(QuestionCategory bean) throws Exception 
	{
		Object [] questionCategoryCode = new Object [1];
		questionCategoryCode [0] = bean.getHiddencode();
		
		Object [][]data = getSqlModel().getSingleResult(getQuery(5), questionCategoryCode);
		
		bean.setSQstCategoryCode(checkNull(String.valueOf(data [0][0])));
		bean.setSQstCategoryName(checkNull(String.valueOf(data[0][1])));
		bean.setSCategoryStatus(checkNull(String.valueOf(data[0][2])));
		bean.setSCategoryDescription(checkNull(String.valueOf(data[0][3])));
		
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
