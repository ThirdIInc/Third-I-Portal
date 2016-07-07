/**
 * 
 */
package org.paradyne.model.recruitment;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.bean.Recruitment.QuestionnaireMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author mangeshj
 * 
 */
public class QuestionnaireMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public boolean addQuest(QuestionnaireMaster quest, String[] attribute,String[] value) {
		System.out.println("---------------1addQuest-----jhjhj88888888888888888888888888888888-----");
		String Query1 = "SELECT UPPER(QUES_NAME) FROM HRMS_QUES WHERE (QUES_NAME='"
				+ quest.getQuesName().trim().toUpperCase()
				+ "' OR QUES_NAME='"
				+ quest.getQuesName().trim().toLowerCase()
				+ "')"
				+ "and QUES_TYPE='" + quest.getQuesType() + "'";
		Object[][] Recordlength = getSqlModel().getSingleResult(Query1);
		if (Recordlength.length > 0) {
			return false;

		}

		String query = " SELECT NVL(MAX(QUES_CODE),0)+1 FROM HRMS_QUES ";

		Object[][] code = getSqlModel().getSingleResult(query);
		System.out.println("---------quest.getQuesName()--------------"+quest.getQuesName());
		Object obj[][] = new Object[1][3];
		obj[0][0] = code[0][0];
		obj[0][1] = quest.getQuesName();
		obj[0][2] = quest.getQuesType();

		boolean result = getSqlModel().singleExecute(getQuery(1), obj);
		if (result) {
			quest.setQuesCode(String.valueOf(code[0][0]));
			result = addOption(quest, attribute, value);
		}

		return result;
	}

	public boolean modQuest(QuestionnaireMaster quest, String[] attribute,String[] value) {
		System.out.println("---------------modQuest----------");
		boolean result1 = false;
		boolean result2 = false;
		Object obj[][] = new Object[1][3];
		obj[0][0] = quest.getQuesName();
		obj[0][1] = quest.getQuesType();
		obj[0][2] = quest.getQuesCode();
		boolean result = getSqlModel().singleExecute(getQuery(9), obj);
		if (result) {
			String queryDel = "DELETE FROM HRMS_QUESDTL WHERE QUES_CODE= "
					+ quest.getQuesCode();
			result1 = getSqlModel().singleExecute(queryDel);
			if (result1)
				result2 = addOption(quest, attribute, value);
		}
		return result;
	}

	public boolean addOption(QuestionnaireMaster quest, String[] attribute,	String[] value) {
		System.out.println("---------------addOption----------");
		boolean result = false;
		Object addObj[][] = new Object[1][4];
		if (attribute != null) {
			for (int i = 0; i < attribute.length; i++) {
				addObj[0][0] = quest.getQuesCode();
				addObj[0][1] = attribute[i];
				addObj[0][2] = value[i];
				addObj[0][3] = String.valueOf(i + 1);

				result = getSqlModel().singleExecute(getQuery(2), addObj);
			}
		}
		return result;
	}

	public boolean deleteQuest(QuestionnaireMaster quest) {
		System.out.println("---------------deleteQuest----------");
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = quest.getQuesCode();
		boolean res = getSqlModel().singleExecute(getQuery(3), delObj);
		if (res)
			return getSqlModel().singleExecute(getQuery(4), delObj);
		else
			return false;
	}

	public boolean addDetails(QuestionnaireMaster quest) {
		String query1 = " SELECT NVL(MAX(QUESDTL_CODE),0)+1 FROM HRMS_QUESDTL WHERE QUES_CODE="
				+ quest.getQuesCode();
		Object[][] data1 = getSqlModel().getSingleResult(query1);

		Object add[][] = new Object[1][4];
		add[0][0] = quest.getQuesCode();
		add[0][1] = quest.getAttribute();
		add[0][2] = quest.getValue();
		add[0][3] = data1[0][0];
		return getSqlModel().singleExecute(getQuery(2), add);
	}

	public boolean updateDetails(QuestionnaireMaster quest) {
		System.out.println("---------------updateDetails----------");
		Object[][] data1 = new Object[1][4];
		data1[0][0] = quest.getAttribute();
		data1[0][1] = quest.getValue();
		data1[0][2] = quest.getQuesCode();
		data1[0][3] = quest.getSubcode();
		return getSqlModel().singleExecute(getQuery(7), data1);
	}
	public void calforedit(QuestionnaireMaster quest) {
		System.out.println("---------------calforedit----------");
		String query = "SELECT  QUES_CODE ,QUES_NAME , QUES_TYPE FROM HRMS_QUES  where QUES_CODE="+ quest.getHiddenCode();
		Object[][] data = getSqlModel().getSingleResult(query);
		quest.setQuesCode(checkNull(String.valueOf(data[0][0])));
		quest.setQuesName(checkNull(String.valueOf(data[0][1])));
		quest.setQuesType(checkNull(String.valueOf(data[0][2])));
		showRecord(quest);

	}
	public void showRecord(QuestionnaireMaster quest) {
		System.out.println("---------------showRecord----quest.getQuesCode()------"+quest.getQuesCode());
		String query = "SELECT QUESDTL_CODE,QUESDTL_NAME ,QUESDTL_VALUE  FROM HRMS_QUESDTL WHERE QUES_CODE = "
				+ quest.getQuesCode() + " ORDER BY QUESDTL_CODE";

		Object data[][] = getSqlModel().getSingleResult(query);

		ArrayList<Object> list = new ArrayList<Object>();
		for (int i = 0; i < data.length; i++) {
			QuestionnaireMaster bean1 = new QuestionnaireMaster();
			bean1.setSubCode(String.valueOf(data[i][0]));
			bean1.setAttribute(String.valueOf(data[i][1]));
			bean1.setValue(String.valueOf(data[i][2]));
			bean1.setSrNo(String.valueOf(i + 1));

			list.add(bean1);

		}
		quest.setList(list);
		if (list.size() == 0) {
			quest.setTableLength("0");
		} else
			quest.setTableLength("1");

	}

	/*
	 * public void delDtl(QuestionnaireMaster quest){ Object obj[]=new
	 * Object[2]; obj[0]=quest.getQuesCode(); obj[1]=quest.getSubcode();
	 * System.out.println("Obj[1]=="+obj[1]);
	 * getSqlModel().getSingleResult(getQuery(8), obj); }
	 */

	public void delDtl(QuestionnaireMaster quest, String[] code, String[] attr,
			String[] val) {
		System.out.println("---------------delDtl----------");
		try {
			ArrayList<Object> list = new ArrayList<Object>();

			if (attr != null) {
				for (int i = 0; i < attr.length; i++) {
					QuestionnaireMaster bean = new QuestionnaireMaster();
					if ((String.valueOf(code[i]).equals(""))) {
						bean.setAttribute(attr[i]);
						bean.setValue(val[i]);
						list.add(bean);
					}

				}
			}
			quest.setList(list);
		} catch (Exception e) {
			e.printStackTrace();

			// TODO: handle exception
		}

	}

	public void addItem(QuestionnaireMaster quest, String[] srNo,
			String[] attribute, String[] value, int check) {
		System.out.println("---------------addItem----------");
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				QuestionnaireMaster bean = new QuestionnaireMaster();
				bean.setSrNo(String.valueOf(i + 1));
				bean.setAttribute(attribute[i]);
				bean.setValue(value[i]);
				tableList.add(bean);
			}
		}
		if (check == 1) {
			quest.setSrNo(String.valueOf(tableList.size() + 1));
			quest.setAttribute(quest.getQuesAttr());
			quest.setValue(quest.getQuesValue());
			tableList.add(quest);
		} else if (check == 0) {
			tableList.remove(Integer.parseInt(quest.getSubcode()) - 1);
		}
		if (tableList.size() != 0)
			quest.setTableLength("1");
		else
			quest.setTableLength("0");
		quest.setList(tableList);
	}

	public void getDuplicate(QuestionnaireMaster quest, String[] srNo,
			String[] attribute, String[] value, int check) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {

			for (int i = 0; i < srNo.length; i++) {
				QuestionnaireMaster bean = new QuestionnaireMaster();
				bean.setSrNo(String.valueOf(i + 1));
				bean.setAttribute(attribute[i]);
				bean.setValue(value[i]);
				tableList.add(bean);
			}
			quest.setList(tableList);
		}

	}

	public void getRecord(QuestionnaireMaster bean) {
		System.out.println("======getRecord============");
		Object obj[] = new Object[2];
		obj[1] = bean.getSubcode();
		obj[0] = bean.getQuesCode();
		Object[][] data = getSqlModel().getSingleResult(getQuery(5), obj);

		bean.setAttribute(String.valueOf(data[0][0]));
		bean.setValue(String.valueOf(data[0][1]));
	}

	public void moditem(QuestionnaireMaster quest, String[] srNo,
			String[] attribute, String[] value, int check) {
		System.out.println("======moditem============");
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {

			for (int i = 0; i < srNo.length; i++) {
				QuestionnaireMaster bean = new QuestionnaireMaster();
				if (i == Integer.parseInt(quest.getHiddenEdit()) - 1) {
					bean.setSrNo(String.valueOf(i + 1));
					bean.setAttribute(quest.getQuesAttr());
					bean.setValue(quest.getQuesValue());

				} else {
					bean.setSrNo(String.valueOf(i + 1));
					bean.setAttribute(attribute[i]);
					bean.setValue(value[i]);
				}
				tableList.add(bean);
			}
		}/*
		 * if(check==1){ quest.setSrNo(String.valueOf(tableList.size()+1));
		 * quest.setAttribute(quest.getQuesAttr());
		 * quest.setValue(quest.getQuesValue()); tableList.add(quest); }else
		 * if(check==0){
		 * tableList.remove(Integer.parseInt(quest.getSubcode())-1); }
		 */
		if (tableList.size() != 0)
			quest.setTableLength("1");
		else
			quest.setTableLength("0");
		quest.setList(tableList);

	}

	public void questionData(QuestionnaireMaster quest,
			HttpServletRequest request) {
		Object[][] repData = getSqlModel().getSingleResult(getQuery(10));
		if (repData != null && repData.length > 0) {
			quest.setModeLength("true");
			quest.setTotalRecords(String.valueOf(repData.length));

			String[] pageIndex = Utility.doPaging(quest.getMyPage(),
					repData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				quest.setMyPage("1");
			ArrayList<Object> List = new ArrayList<Object>();
			for (int i = Integer.parseInt(pageIndex[0]); i < Integer
					.parseInt(pageIndex[1]); i++) {
				QuestionnaireMaster bean1 = new QuestionnaireMaster();
				bean1.setQuesCode(checkNull(String.valueOf(repData[i][0])));
				bean1.setQuesName(checkNull(String.valueOf(repData[i][1])));
				bean1.setQuesType(checkNull(String.valueOf(repData[i][2])));

				List.add(bean1);
			}

			quest.setQuestionlist(List);
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean checkeddeletequestion(QuestionnaireMaster quest,
			String[] code) {
		System.out.println("&&&---checkeddeletequestion--&&");
		boolean result = false;
		int count = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				if (!code[i].equals("")) {

					Object[][] delete = new Object[1][1];
					delete[0][0] = code[i];

					boolean res = getSqlModel().singleExecute(getQuery(3),
							delete);
					if (res) {
						result = getSqlModel().singleExecute(getQuery(4),
								delete);
					}
					if (!result)
						count++;
				}
			}
		}
		if (count != 0) {
			result = false;

		} else {
			result = true;
		}
		return result;
	}

	public void getReport(QuestionnaireMaster quest,
			HttpServletRequest request, HttpServletResponse response,
			ServletContext context, HttpSession session, String[] label) {
		// TODO Auto-generated method stub
		
		String reportName = "Questionnaire Master";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Questionnaire  Master.Pdf");
		String queryDes =" SELECT subStr(QUES_NAME,1,50) , DECODE(QUES_TYPE,'T','Training','F','Training Feedback','E','Exit Interview','C','Candidate Evaluation','I','Induction Feedback') FROM HRMS_QUES " 
		      +" ORDER BY upper(QUES_NAME )";
						
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][3];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				
				j++;
			}
			int cellwidth[] = { 15, 60, 25 };
			int alignment[] = { 1, 0, 0};
			rg.addTextBold("Questionnaire Master", 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);

	}
}
