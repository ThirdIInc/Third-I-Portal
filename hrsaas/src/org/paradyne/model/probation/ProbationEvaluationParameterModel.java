package org.paradyne.model.probation;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.Asset.AssetSubTypes;
import org.paradyne.bean.probation.ProbationEvaluationParameterBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;


public class ProbationEvaluationParameterModel extends ModelBase {

	public boolean addItem(ProbationEvaluationParameterBean bean) {
		Object[][] addObj = new Object[1][3];

		String query1 = "SELECT NVL(MAX(PROBATION_INCR_CODE),0)+1 FROM  HRMS_PROBATION_EVALUATION_DTL";
		Object[][] code = this.getSqlModel().getSingleResult(query1);

		addObj[0][0] = this.checkNull(String.valueOf(code[0][0]));
		addObj[0][1] = bean.getQuestionAttr().trim();
		addObj[0][2] = bean.getAttributeValue().trim();

		bean.setProbationItemCode(String.valueOf(code[0][0]));

		return this.getSqlModel().singleExecute(this.getQuery(2), addObj);

	}

	public boolean updateItem(ProbationEvaluationParameterBean bean) {

		Object[][] updateObj = new Object[1][3];

		updateObj[0][0] = bean.getQuestionAttr().trim();
		updateObj[0][1] = bean.getAttributeValue().trim();
		updateObj[0][2] = bean.getProbationItemCode().trim();

		return this.getSqlModel().singleExecute(this.getQuery(4), updateObj);

	}

	public void getProbevalList(ProbationEvaluationParameterBean bean) {

		String query = "SELECT PROBATION_INCR_CODE, QUESTION_ATTRIBUTE, ATTRIBUTE_VALUE from HRMS_PROBATION_EVALUATION_DTL";
		Object[][] data = getSqlModel().getSingleResult(query);

		bean.setProbevalList(null);
		/** DATA IN A LIST IS NULL IN STARTING* */
		List<Object> list = new ArrayList<Object>();

		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {

				ProbationEvaluationParameterBean bean1 = new ProbationEvaluationParameterBean();
				bean1.setEditDataId(checkNull(String.valueOf(data[i][0])));
				bean1.setQuestionAttr(checkNull(String.valueOf(data[i][1])));
				bean1.setAttributeValue(checkNull(String.valueOf(data[i][2])));

				list.add(bean1);
				/** DATA ADDED INTO LIST* */
			}
			bean.setProbevalList(list);

		}
	}

	/**
	 * @param bean -
	 *            Used to set list data.
	 */
	public void getProbationQuestionList(ProbationEvaluationParameterBean bean,
			HttpServletRequest request) {
		Object[][] regData = this.getSqlModel().getSingleResult(getQuery(5));

		if (regData != null && regData.length > 0) {
			bean.setModeLength("true");

			bean.setTotalNoOfRecords(String.valueOf(regData.length));

			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					regData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if ("1".equals(pageIndex[4])) {
				bean.setMyPage("1");
			}
			final List<Object> list1 = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {

				ProbationEvaluationParameterBean bean1 = new ProbationEvaluationParameterBean();
				System.out.println("regData[i][0] +++++" + regData[i][0]);
				bean1.setProbationCode(this.checkNull(String
						.valueOf(regData[i][0])));
				System.out.println("regData[i][1] +++++" + regData[i][1]);
				bean1.setQuestionName(this.checkNull(String
						.valueOf(regData[i][1])));

				list1.add(bean1);
			}

			bean.setProbEvaluationList(list1);
		} else {

			bean.setProbEvaluationList(null);

		}

	}

	/**
	 * @param bean -
	 *            Used to get Application Data.
	 * @return true/false.
	 */
	public boolean probationEvalSave(ProbationEvaluationParameterBean bean, String[] attribute, String[] attribValue) {
		
		if (!this.checkDuplicateQuestion(bean)) {
		
		Object[][] addObj = new Object[1][2];
		boolean result = false;
		String query1 = "SELECT NVL(MAX(PROB_EVAL_CODE),0)+1 FROM  HRMS_PROBATION_EVALUATION";
		Object[][] code = this.getSqlModel().getSingleResult(query1);

		addObj[0][0] = checkNull(String.valueOf(code[0][0]));
		addObj[0][1] = bean.getQuestionName().trim();

		bean.setProbationCode(String.valueOf(code[0][0]));
		
		result = this.getSqlModel().singleExecute(this.getQuery(1), addObj);
		
		

		if (result) {

			Object[][] itemCode = getSqlModel()
					.getSingleResult(
							"SELECT NVL(MAX(PROBATION_INCR_CODE),0)+1 FROM HRMS_PROBATION_EVALUATION_DTL");

			int count = 0;

			count = Integer.parseInt(String.valueOf(itemCode[0][0]));
			Object[][] insertDtlData = null;
        
         	
         	if(attribute != null && attribute.length>0){
         		insertDtlData = new Object[attribute.length][4];
				for (int i = 0; i < attribute.length; i++) {
					insertDtlData[i][0] = count++;
					insertDtlData[i][1] = attribute[i];
					insertDtlData[i][2] = attribValue[i];
					insertDtlData[i][3] = bean.getProbationCode();
				}
				result = getSqlModel().singleExecute(getQuery(2), insertDtlData);
         	}
         	
		
		}
		System.out.println("result ----" +result );
		return result;
		}
		
		else
		{
			return false;
			
		}
	}

	public boolean checkDuplicateQuestion (ProbationEvaluationParameterBean bean)
	{
		boolean result = false;
		String chkQuery = "SELECT * FROM HRMS_PROBATION_EVALUATION  WHERE UPPER(PROB_EVAL_QUES_NAME) LIKE '" + bean.getQuestionName().trim().toUpperCase() + "'";
		final Object[][] data = this.getSqlModel().getSingleResult(chkQuery);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}
	
	
	
	public boolean probationEvalUpdate(ProbationEvaluationParameterBean bean,
			String[] attribute, String[] attribValue) {
		boolean result = false;
		Object[][] updateObj = new Object[1][2];

		updateObj[0][0] = bean.getQuestionName().trim();
		updateObj[0][1] = bean.getProbationCode().trim();
		result = this.getSqlModel().singleExecute(this.getQuery(3), updateObj);
		if (result) {
			getSqlModel().singleExecute(
					"DELETE FROM HRMS_PROBATION_EVALUATION_DTL WHERE PROB_EVAL_ID="
							+ bean.getProbationCode());

			Object[][] insertDtlData=null;
			
			if(attribute!=null && attribute.length>0)
			 insertDtlData = new Object[attribute.length][4];

			Object[][] itemCode = getSqlModel()
					.getSingleResult(
							"SELECT NVL(MAX(PROBATION_INCR_CODE),0)+1 FROM HRMS_PROBATION_EVALUATION_DTL");

			int count = 0;
			if(itemCode!=null && itemCode.length>0)
			count = Integer.parseInt(String.valueOf(itemCode[0][0]));

			for (int i = 0; i < attribute.length; i++) {
				insertDtlData[i][0] = count++;
				insertDtlData[i][1] = attribute[i];
				insertDtlData[i][2] = attribValue[i];
				insertDtlData[i][3] = bean.getProbationCode();

			} // end of for loop
			result = getSqlModel().singleExecute(getQuery(2), insertDtlData);

		}

		return result;
	}

	/**
	 * purpose - Check Null Functionality.
	 * 
	 * @param result
	 *            Contains string data to be checked.
	 * @return String String after checking null
	 */
	public String checkNull(final String result) {
		if (result == null || "null".equals(result)) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * @param bean -
	 *            Used to set application data.
	 * @param editCode -
	 *            code for edit.
	 */
	public void editProbationRecord(ProbationEvaluationParameterBean bean,
			String editCode) {

		String query = "SELECT PROB_EVAL_QUES_NAME "
				+ " FROM HRMS_PROBATION_EVALUATION "
				+ " where PROB_EVAL_CODE =" + bean.getProbationCode();
		Object[][] data = getSqlModel().getSingleResult(query);
		try {
			bean.setQuestionName(checkNull(String.valueOf(data[0][0])));
			showList(bean);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Purpose - Used to Delete Particular Pension Record.
	 * 
	 * @param bean -
	 *            to get code for deletion.
	 * @return true/false.
	 */
	public void deleteProbationRecord_OLD(
			ProbationEvaluationParameterBean bean, String[] attribute,
			String[] valuel) {

		String delQuery = "delete from HRMS_PROBATION_EVALUATION_DTL where  QUESTION_ATTRIBUTE = "
				+ attribute[0];
		getSqlModel().singleExecute(delQuery);
	}

	public void deleteProbationRecord(ProbationEvaluationParameterBean bean,
			String[] attribute, String[] value) {

		try {
			ArrayList<Object> list = new ArrayList();
			if (value != null && value.length > 0) {
				for (int i = 0; i < value.length; i++) {
					ProbationEvaluationParameterBean innerBean = new ProbationEvaluationParameterBean();
					innerBean.setValue(value[i]);
					innerBean.setAttribute(attribute[i]);
					list.add(innerBean);
				}
			}

			list.remove(Integer.parseInt(bean.getHiddenEdit()) - 1);

			bean.setProbevalList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param bean .
	 * @param code -
	 *            unique code for deletion.
	 * @return boolean.
	 */
	public boolean deleteCheckedRecords(ProbationEvaluationParameterBean bean,
			String[] code) {
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {
				if (!"".equals(code[i])) {
					final Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];
					result = this.getSqlModel().singleExecute(this.getQuery(6),
							delete);
					if (!result) {
						count++;
					}

				}
			}
		}
		if (count != 0) {
			result = false;
			return result;
		} else {
			result = true;
			return result;
		}
	}

	public boolean addItem_OLD(String[] attribute, String[] value,
			ProbationEvaluationParameterBean evalBean,
			HttpServletRequest request) {
		boolean isDuplicate = false;
		try {
			List<Object> list = new ArrayList<Object>();
			if (attribute != null && attribute.length > 0) {
				for (int i = 0; i < attribute.length; i++) {
					ProbationEvaluationParameterBean innerBean = new ProbationEvaluationParameterBean();
					innerBean.setAttribute(attribute[i]);
					innerBean.setValue(value[i]);
					list.add(innerBean);

				}
			}
			ProbationEvaluationParameterBean bean = new ProbationEvaluationParameterBean();
			bean.setAttribute(evalBean.getQuestionAttr().trim());
			bean.setValue(evalBean.getAttributeValue().trim());
			list.add(bean);
			evalBean.setProbevalList(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isDuplicate;
	}

	public boolean addItem(String[] attribute, String[] value,
			ProbationEvaluationParameterBean evalBean,
			HttpServletRequest request) {
		boolean isDuplicate = false;
		try {
			List<Object> list = new ArrayList<Object>();

			if (attribute != null && attribute.length > 0) {

				for (int i = 0; i < attribute.length; i++) {

					ProbationEvaluationParameterBean innerBean = new ProbationEvaluationParameterBean();
					innerBean.setAttribute(attribute[i]);
					if (innerBean.getAttribute().equalsIgnoreCase(
							evalBean.getQuestionAttr())) {
						isDuplicate = true;
					}

					innerBean.setValue(value[i]);
					list.add(innerBean);

				}
			}
			if (!isDuplicate) {

				ProbationEvaluationParameterBean bean = new ProbationEvaluationParameterBean();
				bean.setAttribute(evalBean.getQuestionAttr().trim());
				bean.setValue(evalBean.getAttributeValue().trim());
				list.add(bean);

			}

			evalBean.setProbevalList(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDuplicate;
	}

	public boolean updateList(String[] attribute, String[] value, ProbationEvaluationParameterBean evalBean, HttpServletRequest request) {
		boolean isDuplicate = false;
		ArrayList<Object> list = new ArrayList<Object>();
		if (attribute != null && attribute.length > 0) {

			for (int i = 0; i < attribute.length; i++) {
				if (attribute[i].equalsIgnoreCase(evalBean.getQuestionAttr())) {
					isDuplicate = true;
				}
			}

			for (int i = 0; i < attribute.length; i++) {

				if (i == Integer.parseInt(evalBean.getHiddenEdit()) - 1) {

					if (isDuplicate) {
						ProbationEvaluationParameterBean innerBean = new ProbationEvaluationParameterBean();
						innerBean.setAttribute(attribute[i]);
						innerBean.setValue(value[i]);
						
						list.add(innerBean);
					} else {
						ProbationEvaluationParameterBean innerBean = new ProbationEvaluationParameterBean();

						innerBean.setAttribute(evalBean.getQuestionAttr());
						innerBean.setValue(evalBean.getAttributeValue());
						
						list.add(innerBean);
					}

				} else {
					ProbationEvaluationParameterBean innerBean = new ProbationEvaluationParameterBean();
					innerBean.setAttribute(attribute[i]);
					innerBean.setValue(value[i]);
				
					list.add(innerBean);
				}

			}
			evalBean.setProbevalList(list);
		} 
		return isDuplicate;
	
	}

	public void showList(ProbationEvaluationParameterBean evalBean) {

		String listQuery = "SELECT QUESTION_ATTRIBUTE, ATTRIBUTE_VALUE FROM HRMS_PROBATION_EVALUATION_DTL WHERE PROB_EVAL_ID = "
				+ evalBean.getProbationCode();
		Object[][] listObj = getSqlModel().getSingleResult(listQuery);

		ArrayList<Object> list = new ArrayList<Object>();

		for (int i = 0; i < listObj.length; i++) {
			ProbationEvaluationParameterBean bean1 = new ProbationEvaluationParameterBean();
			bean1.setAttribute(String.valueOf(listObj[i][0]));
			bean1.setValue(String.valueOf(listObj[i][1]));
			bean1.setSrNo(String.valueOf(i + 1));
			list.add(bean1);
		}

		evalBean.setProbevalList(list);

	}
	
	public void showList(ProbationEvaluationParameterBean evalBean,
			String[] attribute, String[] value) {

		String listQuery = "SELECT QUESTION_ATTRIBUTE, ATTRIBUTE_VALUE FROM HRMS_PROBATION_EVALUATION_DTL WHERE PROB_EVAL_ID = "
					+ evalBean.getProbationCode();
			Object[][] listObj = getSqlModel().getSingleResult(listQuery);

		ArrayList<Object> list = new ArrayList<Object>();
	
			for (int i = 0; i < listObj.length; i++) {
				ProbationEvaluationParameterBean bean1 = new ProbationEvaluationParameterBean();
				bean1.setAttribute(String.valueOf(listObj[i][0]));
				bean1.setValue(String.valueOf(listObj[i][1]));
			    bean1.setSrNo(String.valueOf(i + 1));
				list.add(bean1);
			
		}
		evalBean.setProbevalList(list);

	}

	/**
	 * @param bean -
	 *            to get code for deletion.
	 * @return true/false.
	 */
	public boolean delete(ProbationEvaluationParameterBean bean) {
		boolean result = false;

		final String deleteId = bean.getProbationCode();

		final String delQuery = "DELETE FROM HRMS_PROBATION_EVALUATION WHERE PROB_EVAL_CODE ="
				+ deleteId;
		result = this.getSqlModel().singleExecute(delQuery);

		if (result) {
			System.out.println("in if matching--------------------");

			final String delDTLQuery = "DELETE FROM HRMS_PROBATION_EVALUATION_DTL WHERE PROB_EVAL_ID ="
					+ deleteId;
			result = this.getSqlModel().singleExecute(delDTLQuery);
			System.out.println("Record deleted===================");

		}

		return result;
	}

	public void setItteratorData(String[] attribute, String[] value,
			ProbationEvaluationParameterBean evalBean) {
		// TODO Auto-generated method stub
		try {

			ArrayList<Object> list = new ArrayList();
			if (value != null && value.length > 0) {
				for (int i = 0; i < value.length; i++) {
					ProbationEvaluationParameterBean innerBean = new ProbationEvaluationParameterBean();
					innerBean.setValue(value[i]);
					innerBean.setAttribute(attribute[i]);
					list.add(innerBean);
				}
			}
			evalBean.setProbevalList(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
