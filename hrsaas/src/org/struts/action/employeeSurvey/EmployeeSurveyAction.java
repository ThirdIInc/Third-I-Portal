package org.struts.action.employeeSurvey;

import java.util.ArrayList;
import java.util.List;
import org.paradyne.bean.employeeSurvey.EmployeeSurvey;
import org.paradyne.lib.Utility;
import org.paradyne.model.employeeSurvey.EmployeeSurveyModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Reeba_Joseph
 *
 * Aug 23, 2010
 */

public class EmployeeSurveyAction extends ParaActionSupport {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EmployeeSurveyAction.class);
	EmployeeSurvey bean;

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	@Override
	public void prepare_local() throws Exception {
		bean = new EmployeeSurvey();
		bean.setMenuCode(1066);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return bean;
	}
	
	/**
	 * @return the bean
	 */
	public EmployeeSurvey getBean() {
		return bean;
	}
	
	/**
	 * @param bean the bean to set
	 */
	public void setBean(EmployeeSurvey bean) {
		this.bean = bean;
	}

	@Override
	public String input() throws Exception {
		try {
			System.out.println("*****************In Input");
			EmployeeSurveyModel model = new EmployeeSurveyModel();
			model.initiate(context, session);
			
			Object[][] surveyData = model.getEmployeeSurveys(bean);
			
			if(surveyData != null && surveyData.length > 0) {
				List<EmployeeSurvey> surveyList = new ArrayList<EmployeeSurvey>(surveyData.length);
				
				for(int i = 0; i < surveyData.length; i++) {
					EmployeeSurvey empSurvey = new EmployeeSurvey();
					
					empSurvey.setSurveyCode(Integer.parseInt(String.valueOf(surveyData[i][0])));
					empSurvey.setSrNo(i + 1);
					empSurvey.setSurveyName(String.valueOf(surveyData[i][1]));
					empSurvey.setSurveyFromDate(String.valueOf(surveyData[i][2]));
					empSurvey.setSurveyToDate(String.valueOf(surveyData[i][3]));
					
					surveyList.add(empSurvey);
				}
				
				String[] pageIndex = Utility.doPaging(bean.getMyPage(), surveyData.length, 20);
				
				if(pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}

				request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
				request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
				
				bean.setSurveyList(surveyList);
				bean.setDataExists(true);
			} else {
				bean.setDataExists(false);
			}			
			
			model.terminate();
		} catch(Exception e) {
			logger.error("Exception in prepare_withLoginProfileDetails: " + e);
		}
		return INPUT;
	}
	
	/**
	 * To set the page according to the page numbers
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		EmployeeSurveyModel model = new EmployeeSurveyModel();
		model.initiate(context, session);
		model.addSurveyList(bean, request);
		model.terminate();
		return INPUT;
	}
	
	public String callSurveyPage() throws Exception {
		try {
			System.out.println("*****************In CallSurveyPage");
			EmployeeSurveyModel model = new EmployeeSurveyModel();
			model.initiate(context, session);
			model.getSurveyQuestions(bean, request);
			
			logger.info("totalQuestions  : "+request.getAttribute("totalQuestions"));
			logger.info("getQnPageNoField  : "+bean.getMyQnPage());
			int totalPages = (Integer)request.getAttribute("totalQuestions");
			int pageNo = Integer.parseInt(String.valueOf(bean.getMyQnPage()));
			if(pageNo==totalPages){
				bean.setFinalize(true);
			}else
				bean.setFinalize(false);
			model.terminate();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String saveAndCallSurveyPage() throws Exception {
		try {
			System.out.println("*****************In saveAndCallSurveyPage");
			EmployeeSurveyModel model = new EmployeeSurveyModel();
			model.initiate(context, session);
			String[] questionCode = request.getParameterValues("questionCode");
			String[] optionCode = request.getParameterValues("optionCode");
			String[] sectionCode = request.getParameterValues("sectionCode");
			String[] questionType = request.getParameterValues("questionType");
			String[] comments = request.getParameterValues("comment");
			String[] optionValues = request.getParameterValues("optionValues");
			String finalizeStatus = request.getParameter("finalizeStatus");
			logger.info("finalizeStatus  : "+finalizeStatus);

			
			model.saveSurveys(bean, questionCode, optionCode, sectionCode,
					questionType, comments, optionValues, finalizeStatus);

			model.getSurveyQuestions(bean, request);
			logger.info("totalQuestions  : "+request.getAttribute("totalQuestions"));
			logger.info("getQnPageNoField  : "+bean.getMyQnPage());
			int totalPages = (Integer)request.getAttribute("totalQuestions");
			int pageNo = Integer.parseInt(String.valueOf(bean.getMyQnPage()));
			if(pageNo==totalPages){
				bean.setFinalize(true);
			}else
				bean.setFinalize(false);
			model.terminate();
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
}