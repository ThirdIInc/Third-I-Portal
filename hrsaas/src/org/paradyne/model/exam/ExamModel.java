package org.paradyne.model.exam;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.driver.OracleResultSet;
import oracle.sql.CLOB;

import org.paradyne.bean.exam.ExamMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author Prasad
 * 
 */

public class ExamModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.action.exam.ExamMasterAction.class);

	

	public void displaySubjectCategory(ExamMaster bean,
			HttpServletRequest request) {
		int n;
		try {
			Object[] SNo = request.getParameterValues("SrNo");
			Object[] catName = request
					.getParameterValues("subjectCategoryNameItr");
			ArrayList<Object> localList = new ArrayList<Object>();
			int i = 0;
			if (SNo != null && SNo.length > 0) {
				for (i = 0; i < SNo.length; i++) {
					ExamMaster catbean = new ExamMaster();
					catbean.setSrNo(String.valueOf(i + 1));
					catbean.setSubjectCategoryNameItr(String
							.valueOf(catName[i]));
					localList.add(catbean);
				}
			}
			bean.setCategoryList(localList);
			bean.setCategoryListlength(String.valueOf(localList.size()));
			n = localList.size();
			logger.info("locallist size---> " + n);
			if (n == 0) {
				bean.setCatList(false);
			} else {
				bean.setCatList(true);
			}
		} catch (Exception e) {
		}
	}

	public String addCategory(ExamMaster bean, HttpServletRequest request) {
		String result="";
		Object catdtl[][] = new Object[1][3];
		boolean flag;
		
		if (!(checkDuplicateSection(bean))) {
			catdtl[0][0] = String.valueOf(bean.getSubjectCode());
			catdtl[0][1] = String.valueOf(bean.getCategoryName());
			catdtl[0][2] = String.valueOf(bean.getCategoryStatus());
			flag = getSqlModel().singleExecute(getQuery(5), catdtl);
			if (flag) {
				result = "success";
			} else {
				result = "error";
			}
		} else {
			result = "duplicate";
		}
		return result;
	}
		
	public String updateCategory(ExamMaster bean, HttpServletRequest request) {
		String result="";
		Object catdtl[][] = new Object[1][3];
		boolean flag;
		if (!(checkDuplicateSectionMod(bean))) {
			catdtl[0][0] = String.valueOf(bean.getCategoryName());
			catdtl[0][1] = String.valueOf(bean.getCategoryStatus());
			catdtl[0][2] = String.valueOf(bean.getHEditCatCode());
			flag = getSqlModel().singleExecute(getQuery(7), catdtl);
			if (flag) {
				result = "success";
			} else {
				result = "error";
			}
		} else {
			result = "duplicate";
		}
		return result;
	}
	
	public String updateCategoryStatus(HttpServletRequest request) {
		String result="";
		Object catdtl[][] = new Object[1][2];
		boolean flag;		
		catdtl[0][0] = String.valueOf(request.getParameter("categoryStatus"));
		catdtl[0][1] = String.valueOf(request.getParameter("categoryCode"));		
		flag = getSqlModel().singleExecute(getQuery(7), catdtl);
		if(flag){
			result = "success";
		} else {
			result = "error";
		}
		return result;
	}
	
	public String deleteCategory(ExamMaster bean, HttpServletRequest request, String[] categoryCode) {
		String result="";
		boolean flag = false;
		
		try {
			for (int i = 0; i < categoryCode.length; i++) {
				if (!categoryCode[i].equals("")) {
					
					String selectQues = "SELECT QUES_CODE FROM HRMS_REC_QUESBANK WHERE QUES_CAT_CODE="
							+ categoryCode[i];
					Object[][] data1 = getSqlModel().getSingleResult(selectQues);
					if (data1 != null && data1.length > 0) {
						for (int k = 0; k < data1.length; k++) {
							String deleteQues = "DELETE FROM HRMS_REC_QUESOPTION WHERE QUES_CODE = "
									+ String.valueOf(data1[k][0]);
							flag = getSqlModel().singleExecute(deleteQues);
						}
					}
					
					String deleteQueQuery = "DELETE FROM HRMS_REC_QUESBANK WHERE QUES_CAT_CODE= " + categoryCode[i];
					flag = getSqlModel().singleExecute(deleteQueQuery);
					
					String deletecatQuery = "DELETE FROM HRMS_REC_CATEGORY WHERE CAT_SUB_CODE ="
							+ bean.getSubjectCode()
							+ " AND CAT_CODE = "
							+ categoryCode[i];
					flag = getSqlModel().singleExecute(deletecatQuery);
				}
			}
			if(flag){
				result = "success";
			} else {
				result = "error";
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			result = "error";
		}
		return result;
	}
	
	
	/**
	 * following function is called to add a new record
	 * 
	 * @param bean
	 * @param request
	 * @return
	 */

	public String addSubject(ExamMaster bean, HttpServletRequest request) {
		boolean result = false;
		String saveFlag = "";
		int n = 0;
		Object[][] add = new Object[1][5];
		try {
			add[0][0] = bean.getSubjectName().trim();
			add[0][1] = bean.getSubjectAbbr().trim();
			add[0][2] = bean.getSubjectDesc().replaceAll("\n", "");
			add[0][3] = bean.getSubjectStatus();
			if(bean.getEnableSection().equals("Y")){
				add[0][4] = "Y";
			} else {
				add[0][4] = "N";
			}
			
			if (!(checkDuplicate(bean))) {
				result = getSqlModel().singleExecute(getQuery(1), add);
				if (result) {
					String query = " SELECT MAX(SUBJECT_CODE) FROM HRMS_REC_SUBJECT";
					Object[][] data = getSqlModel().getSingleResult(query);

					if (bean.getCategoryName() != null && !bean.getCategoryName().trim().equals("")) {
						Object catdtl[][] = new Object[1][3];
						boolean flag;
						catdtl[0][0] = String.valueOf(data[0][0]);
						catdtl[0][1] = String.valueOf(bean.getCategoryName());
						catdtl[0][2] = String.valueOf(bean.getCategoryStatus());
						flag = getSqlModel().singleExecute(getQuery(5), catdtl);
					}
					String query1 = " SELECT SUBJECT_NAME,SUBJECT_ABBR,SUBJECT_DESC,SUBJECT_CODE,CASE WHEN SUBJECT_STATUS='A' THEN 'Active' ELSE 'Deactive' END FROM "
							+ " HRMS_REC_SUBJECT WHERE SUBJECT_CODE="
							+ String.valueOf(data[0][0]);
					Object[][] Data = getSqlModel().getSingleResult(query1);
					bean.setSubjectName(checkNull(String.valueOf(Data[0][0])));
					bean.setSubjectAbbr(checkNull(String.valueOf(Data[0][1])));
					bean.setSubjectDesc(checkNull(String.valueOf(Data[0][2])));
					bean.setSubjectCode(checkNull(String.valueOf(Data[0][3])));
					bean.setViewStatus(checkNull(String.valueOf(Data[0][4])));
					String Catdtl = "SELECT CAT_NAME,CAT_CODE FROM HRMS_REC_CATEGORY WHERE CAT_SUB_CODE="
							+ String.valueOf(data[0][0]) + " ORDER BY CAT_CODE";
					Object[][] CatdtlData = getSqlModel().getSingleResult(Catdtl);
					ArrayList <Object> localList = new ArrayList<Object>();
					if (CatdtlData != null && CatdtlData.length > 0) {
						for (int i = 0; i < CatdtlData.length; i++) {
							ExamMaster catbean = new ExamMaster();
							catbean.setSrNo(String.valueOf(i + 1));
							catbean.setSubjectCategoryNameItr(String
									.valueOf(CatdtlData[i][0]));
							catbean.setCategoryCodeItr(String
									.valueOf(CatdtlData[i][1]));
							localList.add(catbean);
						}
						bean.setCategoryList(localList);
					}
					bean.setCategoryListlength(String.valueOf(localList
									.size()));
					n = localList.size();
					logger.info("locallist size------->" + localList.size());
					if (n == 0) {
						bean.setCatList(false);
					} else {
						bean.setCatList(true);
					}
					saveFlag = "saved";
				} else {
					saveFlag = "error";
				}
			} else {
				saveFlag = "duplicate";
				displaySubjectCategory(bean, request);

			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return saveFlag;
	}

	/**
	 * following function is called to update the record.
	 * 
	 * @param bean
	 * @param request
	 * @return
	 */

	public String modSubject(ExamMaster bean, HttpServletRequest request) {
		Object mod[][] = new Object[1][6];
		String editFlag = "";
		boolean result = false;
		try {
			mod[0][0] = bean.getSubjectName().trim();
			mod[0][1] = bean.getSubjectAbbr().trim();
			mod[0][2] = bean.getSubjectDesc().replaceAll("\n", "");
			mod[0][3] = bean.getSubjectStatus();
			if(bean.getEnableSection().equals("Y")){
				mod[0][4] = "Y";
			} else {
				mod[0][4] = "N";
			}
			mod[0][5] = bean.getSubjectCode();
			if (!checkDuplicateMod(bean)) {
				result = getSqlModel().singleExecute(getQuery(2), mod);
				editFlag = "modified";
				/*if (result) {

					String Deletecat = "Delete from HRMS_REC_CATEGORY where CAT_SUB_CODE="
							+ bean.getSubjectCode();
					boolean flag1 = getSqlModel().singleExecute(Deletecat);
					boolean flag;
					Object[] sNo = request.getParameterValues("SrNo");
					Object[] catName = request
							.getParameterValues("subjectCategoryNameItr");
					Object[] catCode = request
							.getParameterValues("categoryCodeItr");
					if (flag1) {

						if (sNo != null && sNo.length > 0) {
							Object catdtl[][] = new Object[1][2];

							for (int i = 0; i < sNo.length; i++) {

								catdtl[0][0] = bean.getSubjectCode();
								catdtl[0][1] = String.valueOf(catName[i]);
								flag = getSqlModel().singleExecute(getQuery(5),
										catdtl);
							}
						}
					} else {

						if (sNo != null && sNo.length > 0) {
							Object[][] catMod = new Object[1][3];
							Object[][] catAdd = new Object[1][2];
							for (int i = 0; i < sNo.length; i++) {
								if (String.valueOf(catCode[i]).equals("")
										|| String.valueOf(catCode[i]).equals(
												"null")
										|| String.valueOf(catCode[i]).equals(
												" ")) {

									catAdd[0][0] = bean.getSubjectCode();
									catAdd[0][1] = String.valueOf(catName[i]);
									flag = getSqlModel().singleExecute(
											getQuery(5), catAdd);
								} else {

									catMod[0][0] = String.valueOf(catName[i]);
									catMod[0][1] = String.valueOf(catCode[i]);
									catMod[0][1] = bean.getSubjectCode();
									flag = getSqlModel().singleExecute(
											getQuery(6), catMod);
								}
							}
						}
					}
				
					String query1 = " SELECT SUBJECT_NAME,SUBJECT_ABBR,SUBJECT_DESC,SUBJECT_CODE,CASE WHEN SUBJECT_STATUS='A' THEN 'Active' ELSE 'Deactive' END  FROM HRMS_REC_SUBJECT"
							+ " WHERE SUBJECT_CODE=" + bean.getSubjectCode();
					Object[][] Data = getSqlModel().getSingleResult(query1);
					bean.setSubjectName(checkNull(String.valueOf(Data[0][0])));
					bean.setSubjectAbbr(checkNull(String.valueOf(Data[0][1])));
					bean.setSubjectDesc(checkNull(String.valueOf(Data[0][2])));
					bean.setSubjectCode(checkNull(String.valueOf(Data[0][3])));
					bean.setViewStatus(checkNull(String.valueOf(Data[0][4])));
					String Catdtl = "select CAT_NAME,CAT_CODE from HRMS_REC_CATEGORY where CAT_SUB_CODE="
							+ String.valueOf(Data[0][3]) + " ORDER BY CAT_CODE";
					Object[][] CatdtlData = getSqlModel().getSingleResult(
							Catdtl);
					ArrayList<Object> localList = new ArrayList<Object>();
					if (CatdtlData != null && CatdtlData.length > 0) {
						for (int i = 0; i < CatdtlData.length; i++) {
							ExamMaster catbean = new ExamMaster();
							catbean.setSrNo(String.valueOf(i + 1));
							catbean.setSubjectCategoryNameItr(String
									.valueOf(CatdtlData[i][0]));
							catbean.setCategoryCodeItr(String
									.valueOf(CatdtlData[i][1]));
							localList.add(catbean);
						}
						bean.setCategoryList(localList);
					}
					bean
							.setCategoryListlength(String.valueOf(localList
									.size()));
					n = localList.size();
					if (n == 0) {
						bean.setCatList(false);
					} else {
						bean.setCatList(true);
					}
					editFlag = "modified";
				} else {
					editFlag = "error";
				}*/
				
				Object[] catCode = request.getParameterValues("categoryCode");
				Object[] catOrder = request.getParameterValues("categoryOrder");
				if (catCode != null && catCode.length > 0 && catOrder != null && catOrder.length > 0) {
					Object[][] catMod = null;
					for (int i = 0; i < catCode.length; i++) {
						catMod = new Object[1][2];
						catMod[0][0] = catOrder[i];
						catMod[0][1] = catCode[i];
						
						String query = "UPDATE HRMS_REC_CATEGORY SET CAT_ORDER=? WHERE CAT_CODE=?";
						
						getSqlModel().singleExecute(query, catMod);
						
					}
					
				}
				
			} else {
				editFlag = "duplicate";

				displaySubjectCategory(bean, request);
			}

		} catch (Exception e) {
			editFlag = "error";
		}
		return editFlag;
	}

	/**
	 * following function is called to display the category details for each
	 * subject.
	 * 
	 * @param bean
	 */
	public void categoryList(ExamMaster bean, HttpServletRequest request) {
		int n = 0;
		String Catdtl = "SELECT CAT_NAME,CAT_CODE, CAT_STATUS, CAT_ORDER FROM HRMS_REC_CATEGORY"
				+ " WHERE CAT_SUB_CODE="
				+ bean.getSubjectCode() 
				+ " ORDER BY CAT_ORDER";
		Object[][] CatdtlData = getSqlModel().getSingleResult(Catdtl);
		ArrayList<Object> localList = new ArrayList<Object>();
		if (CatdtlData != null && CatdtlData.length > 0) {
			for (int i = 0; i < CatdtlData.length; i++) {
				ExamMaster catbean = new ExamMaster();
				catbean.setSrNo(String.valueOf(i + 1));
				catbean.setCategoryName(String
						.valueOf(CatdtlData[i][0]));
				catbean.setCategoryCode(String.valueOf(CatdtlData[i][1]));
				catbean.setCatStatus(String.valueOf(CatdtlData[i][2]));
				catbean.setCategoryOrder(String.valueOf(CatdtlData[i][3]));
				if(catbean.getCategoryName().length() > 20) {
					catbean.setSectionNmLen(catbean.getCategoryName().substring(0, 20) + "....");
				} else {
					catbean.setSectionNmLen(catbean.getCategoryName());
				}
				catbean.setDblFlag(true);
				localList.add(catbean);
			}
			bean.setCategoryList(localList);
		}
		bean.setCategoryListlength(String.valueOf(localList.size()));
		n = localList.size();
		request.setAttribute("categoryListSize", n);
		logger.info("locallist size------->" + localList.size());
		if (n == 0) {
			bean.setCatList(false);
		} else {
			bean.setCatList(true);
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	/**
	 * following function is called when a record is selected from search window
	 * to set the records.
	 * 
	 * @param bean
	 */

	public void getSubjectRec(ExamMaster bean, HttpServletRequest request) {
		try {
			String query = " SELECT SUBJECT_NAME, SUBJECT_ABBR, SUBJECT_DESC, SUBJECT_CODE,"
					+ " SUBJECT_STATUS, IS_SECTION"
					+ " FROM HRMS_REC_SUBJECT  "					
					+ " WHERE SUBJECT_CODE=" + bean.getSubjectCode();
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				bean.setSubjectName(checkNull(String.valueOf(data[0][0]).trim()));
				bean.setSubjectAbbr(checkNull(String.valueOf(data[0][1]).trim()));
				bean.setSubjectDesc(checkNull(String.valueOf(data[0][2])));
				bean.setSubjectCode(checkNull(String.valueOf(data[0][3])));
				bean.setStatus(checkNull(String.valueOf(data[0][4])));
				if(checkNull(String.valueOf(data[0][5])).equalsIgnoreCase("Y")){
					bean.setEnableSection("true");
				}else{
					if(bean.getEnableSection().equals("true"))
					{
						bean.setEnableSection("true");
					}
					else{
					bean.setEnableSection("false");
					}
				}
			}
			categoryList(bean, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSubjectOnDoubleClick(ExamMaster bean, HttpServletRequest request) {

		try {
			String query = " SELECT NVL(SUBJECT_NAME,' '), NVL(SUBJECT_ABBR,' '), NVL(SUBJECT_DESC,' '),"
					+ " SUBJECT_CODE, CASE WHEN SUBJECT_STATUS='A' THEN 'A' ELSE 'D' END,"
					+ " IS_SECTION"
					+ " FROM HRMS_REC_SUBJECT"
					+ "  WHERE SUBJECT_CODE="
					+ bean.getSubjectCode();
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				bean.setSubjectName(checkNull(String.valueOf(data[0][0]).trim()));
				bean.setSubjectAbbr(checkNull(String.valueOf(data[0][1]).trim()));
				bean.setSubjectDesc(checkNull(String.valueOf(data[0][2]).trim()));
				bean.setSubjectCode(checkNull(String.valueOf(data[0][3])));
				bean.setSubjectStatus(checkNull(String.valueOf(data[0][4])));
				if(checkNull(String.valueOf(data[0][5])).equalsIgnoreCase("Y")){
					bean.setEnableSection("true");
				}else{
					bean.setEnableSection("false");
				}
			}
			categoryList(bean, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setContent(ExamMaster bean, HttpServletRequest request, String from) {
		try {
			String query = "";
			if (from.equals("catedit")) {
				query = " SELECT NVL(HRMS_REC_CATEGORY.CAT_NAME,' '), HRMS_REC_CATEGORY.CAT_CODE,"
					+ " HRMS_REC_CATEGORY.CAT_CONTENT, NVL(HRMS_REC_SUBJECT.SUBJECT_NAME,' '),"
					+ " HRMS_REC_SUBJECT.SUBJECT_CODE "
					+ " FROM HRMS_REC_CATEGORY "
					+ " LEFT JOIN HRMS_REC_SUBJECT ON HRMS_REC_SUBJECT.SUBJECT_CODE = HRMS_REC_CATEGORY.CAT_SUB_CODE"					
					+ " WHERE HRMS_REC_CATEGORY.CAT_CODE = "
					+ bean.getCategoryCode();
				Object[][] data = getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					bean.setCategoryName(checkNull(String.valueOf(data[0][0]).trim()));
					bean.setCategoryCode(checkNull(String.valueOf(data[0][1])));
					//bean.setContent(checkNull(String.valueOf(data[0][2])));
					bean.setSubjectName(checkNull(String.valueOf(data[0][3]).trim()));
					bean.setSubjectCode(checkNull(String.valueOf(data[0][4])));					
				}
				if(!bean.getContentId().equals("")){
					query = " SELECT NVL(CONTENT_TYPE,' '), NVL(CONTENT_TITLE,' '),"
						+ " NVL(CONTENT_URL,' '), CONTENT_TEXT"
						+ " FROM HRMS_WBT_CONTENT WHERE CONTENT_ID = "
						+ bean.getContentId();
					data = getSqlModel().getSingleResult(query);
					if (data != null && data.length > 0) {
						bean.setContentType(checkNull(String.valueOf(data[0][0]).trim()));
						bean.setContentTitle(checkNull(String.valueOf(data[0][1])));
						if(!bean.getContentType().equalsIgnoreCase("text")){
							bean.setContentUrl(checkNull(String.valueOf(data[0][2])));
						} else {
							bean.setContentUrl("");
						}
						if(bean.getContentType().equalsIgnoreCase("text")){
							bean.setContent(checkNull(String.valueOf(data[0][3])));
						} else {
							bean.setContent("");
						}
					}
				}
				
				getContentList(bean, request, "category", bean.getCategoryCode());
				
			} else {
				query = " SELECT NVL(SUBJECT_NAME,' '), SUBJECT_CODE,  SUBJECT_CONTENT "
					+ " FROM HRMS_REC_SUBJECT "
					+ " WHERE SUBJECT_CODE = "
					+ bean.getSubjectCode();
				Object[][] data = getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					bean.setSubjectName(checkNull(String.valueOf(data[0][0]).trim()));
					bean.setSubjectCode(checkNull(String.valueOf(data[0][1])));
					//bean.setContent(checkNull(String.valueOf(data[0][2])));
					bean.setCategoryCode("");
				}
				if(!bean.getContentId().equals("")){
					query = " SELECT NVL(CONTENT_TYPE,' '), NVL(CONTENT_TITLE,' '),"
						+ " NVL(CONTENT_URL,' '), CONTENT_TEXT"
						+ " FROM HRMS_WBT_CONTENT WHERE CONTENT_ID = "
						+ bean.getContentId();
					
					data = getSqlModel().getSingleResult(query);
					if (data != null && data.length > 0) {
						bean.setContentType(checkNull(String.valueOf(data[0][0]).trim()));
						bean.setContentTitle(checkNull(String.valueOf(data[0][1])));
						if(!bean.getContentType().equalsIgnoreCase("text")){
							bean.setContentUrl(checkNull(String.valueOf(data[0][2])));
						} else {
							bean.setContentUrl("");
						}
						if(bean.getContentType().equalsIgnoreCase("text")){
							bean.setContent(checkNull(String.valueOf(data[0][3])));
						} else {
							bean.setContent("");
						}
					}
				}
				
				getContentList(bean, request, "subject", bean.getSubjectCode());
			}
			
			request.setAttribute("labelName", "Content");
			request.setAttribute("from", from);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean deleteContent(ExamMaster bean, HttpServletRequest request, String from) {
		boolean result = false;
		try {
			String query = "";
			query = " DELETE FROM HRMS_WBT_CONTENT WHERE CONTENT_ID = "
				+ bean.getContentId();
			result = getSqlModel().singleExecute(query);

			if (from.equals("catedit")) {
				query = " SELECT NVL(HRMS_REC_CATEGORY.CAT_NAME,' '), HRMS_REC_CATEGORY.CAT_CODE,"
						+ " HRMS_REC_CATEGORY.CAT_CONTENT, NVL(HRMS_REC_SUBJECT.SUBJECT_NAME,' '),"
						+ " HRMS_REC_SUBJECT.SUBJECT_CODE "
						+ " FROM HRMS_REC_CATEGORY "
						+ " LEFT JOIN HRMS_REC_SUBJECT ON HRMS_REC_SUBJECT.SUBJECT_CODE = HRMS_REC_CATEGORY.CAT_SUB_CODE"
						+ " WHERE HRMS_REC_CATEGORY.CAT_CODE = "
						+ bean.getCategoryCode();
				Object[][] data = getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					bean.setCategoryName(checkNull(String.valueOf(data[0][0]).trim()));
					bean.setCategoryCode(checkNull(String.valueOf(data[0][1])));
					// bean.setContent(checkNull(String.valueOf(data[0][2])));
					bean.setSubjectName(checkNull(String.valueOf(data[0][3]).trim()));
					bean.setSubjectCode(checkNull(String.valueOf(data[0][4])));
				}

				getContentList(bean, request, "category", bean.getCategoryCode());

			} else {
				query = " SELECT NVL(SUBJECT_NAME,' '), SUBJECT_CODE,  SUBJECT_CONTENT "
						+ " FROM HRMS_REC_SUBJECT "
						+ " WHERE SUBJECT_CODE = "
						+ bean.getSubjectCode();
				Object[][] data = getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					bean.setSubjectName(checkNull(String.valueOf(data[0][0]).trim()));
					bean.setSubjectCode(checkNull(String.valueOf(data[0][1])));
					// bean.setContent(checkNull(String.valueOf(data[0][2])));
					bean.setCategoryCode("");
				}

				getContentList(bean, request, "subject", bean.getSubjectCode());
			}

			bean.setContentId("");
			bean.setContentOrder("");
			bean.setContentTitle("");
			bean.setContentType("");
			bean.setContentUrl("");
			bean.setContent("");
			
			request.setAttribute("labelName", "Content");
			request.setAttribute("from", from);

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public boolean contentOrderUp(ExamMaster bean, HttpServletRequest request, String from) {
		boolean result = false;
		try {
			String query = "";
			int previousOrder = 0;
			String content_sec_type = "";
			String content_sec_id = "";
			String previousOrderQuery = "SELECT NVL(HRMS_WBT_CONTENT.CONTENT_ORDER,0),"
					+ " HRMS_WBT_CONTENT.CONTENT_SEC_TYPE, HRMS_WBT_CONTENT.CONTENT_SEC_ID	"
					+ " FROM HRMS_WBT_CONTENT "
					+ " WHERE HRMS_WBT_CONTENT.CONTENT_ID = "
					+ bean.getContentId();
			Object[][] previousOrderData = getSqlModel().getSingleResult(previousOrderQuery);
			if (previousOrderData != null && previousOrderData.length > 0) {
				previousOrder = Integer.parseInt(String.valueOf(previousOrderData[0][0]));
				content_sec_type = String.valueOf(previousOrderData[0][1]);
				content_sec_id = String.valueOf(previousOrderData[0][2]);
			}
			int newOrder = previousOrder + 1; 
			
			int swapContentId;
			query = "SELECT HRMS_WBT_CONTENT.CONTENT_ID	"
					+ " FROM HRMS_WBT_CONTENT "
					+ " WHERE  HRMS_WBT_CONTENT.CONTENT_SEC_TYPE = '"+ content_sec_type + "'"
					+ " AND HRMS_WBT_CONTENT.CONTENT_SEC_ID = " + content_sec_id
					+ " AND HRMS_WBT_CONTENT.CONTENT_ORDER = " + newOrder;			
			
			Object[][] swapContentIdData = getSqlModel().getSingleResult(query);
			if (swapContentIdData != null && swapContentIdData.length > 0) {
				swapContentId = Integer.parseInt(String.valueOf(swapContentIdData[0][0]));
				
				query = "UPDATE HRMS_WBT_CONTENT SET HRMS_WBT_CONTENT.CONTENT_ORDER = " + previousOrder 
					+ " WHERE HRMS_WBT_CONTENT.CONTENT_ID = " + swapContentId;
				result = getSqlModel().singleExecute(query);

			}
			
			query = "UPDATE HRMS_WBT_CONTENT SET HRMS_WBT_CONTENT.CONTENT_ORDER = " + newOrder 
				+ " WHERE HRMS_WBT_CONTENT.CONTENT_ID = " + bean.getContentId();
			result = getSqlModel().singleExecute(query);

		
			
			if (from.equals("catedit")) {
				query = " SELECT NVL(HRMS_REC_CATEGORY.CAT_NAME,' '), HRMS_REC_CATEGORY.CAT_CODE,"
					+ " HRMS_REC_CATEGORY.CAT_CONTENT, NVL(HRMS_REC_SUBJECT.SUBJECT_NAME,' '),"
					+ " HRMS_REC_SUBJECT.SUBJECT_CODE "
					+ " FROM HRMS_REC_CATEGORY "
					+ " LEFT JOIN HRMS_REC_SUBJECT ON HRMS_REC_SUBJECT.SUBJECT_CODE = HRMS_REC_CATEGORY.CAT_SUB_CODE"
					+ " WHERE HRMS_REC_CATEGORY.CAT_CODE = "
					+ bean.getCategoryCode();
				Object[][] data = getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					bean.setCategoryName(checkNull(String.valueOf(data[0][0]).trim()));
					bean.setCategoryCode(checkNull(String.valueOf(data[0][1])));
					// bean.setContent(checkNull(String.valueOf(data[0][2])));
					bean.setSubjectName(checkNull(String.valueOf(data[0][3]).trim()));
					bean.setSubjectCode(checkNull(String.valueOf(data[0][4])));
				}
				
				getContentList(bean, request, "category", bean.getCategoryCode());
				
			} else {
				query = " SELECT NVL(SUBJECT_NAME,' '), SUBJECT_CODE,  SUBJECT_CONTENT "
					+ " FROM HRMS_REC_SUBJECT "
					+ " WHERE SUBJECT_CODE = "
					+ bean.getSubjectCode();
				Object[][] data = getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					bean.setSubjectName(checkNull(String.valueOf(data[0][0]).trim()));
					bean.setSubjectCode(checkNull(String.valueOf(data[0][1])));
					// bean.setContent(checkNull(String.valueOf(data[0][2])));
					bean.setCategoryCode("");
				}
				
				getContentList(bean, request, "subject", bean.getSubjectCode());
			}
			
			bean.setContentId("");
			bean.setContentOrder("");
			bean.setContentTitle("");
			bean.setContentType("");
			bean.setContentUrl("");
			bean.setContent("");
			
			request.setAttribute("labelName", "Content");
			request.setAttribute("from", from);
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public boolean contentOrderDown(ExamMaster bean, HttpServletRequest request, String from) {
		boolean result = false;
		try {
			String query = "";
			int previousOrder = 0;
			String content_sec_type = "";
			String content_sec_id = "";
			String previousOrderQuery = "SELECT NVL(HRMS_WBT_CONTENT.CONTENT_ORDER,0),"
				+ " HRMS_WBT_CONTENT.CONTENT_SEC_TYPE, HRMS_WBT_CONTENT.CONTENT_SEC_ID	"
				+ " FROM HRMS_WBT_CONTENT "
				+ " WHERE HRMS_WBT_CONTENT.CONTENT_ID = "
				+ bean.getContentId();
			Object[][] previousOrderData = getSqlModel().getSingleResult(previousOrderQuery);
			if (previousOrderData != null && previousOrderData.length > 0) {
				previousOrder = Integer.parseInt(String.valueOf(previousOrderData[0][0]));
				content_sec_type = String.valueOf(previousOrderData[0][1]);
				content_sec_id = String.valueOf(previousOrderData[0][2]);
			}
			int newOrder = previousOrder - 1; 
			
			int swapContentId;
			query = "SELECT HRMS_WBT_CONTENT.CONTENT_ID	"
				+ " FROM HRMS_WBT_CONTENT "
				+ " WHERE  HRMS_WBT_CONTENT.CONTENT_SEC_TYPE = '"+ content_sec_type + "'"
				+ " AND HRMS_WBT_CONTENT.CONTENT_SEC_ID = " + content_sec_id
				+ " AND HRMS_WBT_CONTENT.CONTENT_ORDER = " + newOrder;			
			
			Object[][] swapContentIdData = getSqlModel().getSingleResult(query);
			if (swapContentIdData != null && swapContentIdData.length > 0) {
				swapContentId = Integer.parseInt(String.valueOf(swapContentIdData[0][0]));
				
				query = "UPDATE HRMS_WBT_CONTENT SET HRMS_WBT_CONTENT.CONTENT_ORDER = " + previousOrder 
					+ " WHERE HRMS_WBT_CONTENT.CONTENT_ID = " + swapContentId;
				result = getSqlModel().singleExecute(query);
				
			}
			
			query = "UPDATE HRMS_WBT_CONTENT SET HRMS_WBT_CONTENT.CONTENT_ORDER = " + newOrder 
				+ " WHERE HRMS_WBT_CONTENT.CONTENT_ID = " + bean.getContentId();
			result = getSqlModel().singleExecute(query);
		
		
			if (from.equals("catedit")) {
				query = " SELECT NVL(HRMS_REC_CATEGORY.CAT_NAME,' '), HRMS_REC_CATEGORY.CAT_CODE,"
					+ " HRMS_REC_CATEGORY.CAT_CONTENT, NVL(HRMS_REC_SUBJECT.SUBJECT_NAME,' '),"
					+ " HRMS_REC_SUBJECT.SUBJECT_CODE "
					+ " FROM HRMS_REC_CATEGORY "
					+ " LEFT JOIN HRMS_REC_SUBJECT ON HRMS_REC_SUBJECT.SUBJECT_CODE = HRMS_REC_CATEGORY.CAT_SUB_CODE"
					+ " WHERE HRMS_REC_CATEGORY.CAT_CODE = "
					+ bean.getCategoryCode();
				Object[][] data = getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					bean.setCategoryName(checkNull(String.valueOf(data[0][0]).trim()));
					bean.setCategoryCode(checkNull(String.valueOf(data[0][1])));
					// bean.setContent(checkNull(String.valueOf(data[0][2])));
					bean.setSubjectName(checkNull(String.valueOf(data[0][3]).trim()));
					bean.setSubjectCode(checkNull(String.valueOf(data[0][4])));
				}
				
				getContentList(bean, request, "category", bean.getCategoryCode());
				
			} else {
				query = " SELECT NVL(SUBJECT_NAME,' '), SUBJECT_CODE,  SUBJECT_CONTENT "
					+ " FROM HRMS_REC_SUBJECT "
					+ " WHERE SUBJECT_CODE = "
					+ bean.getSubjectCode();
				Object[][] data = getSqlModel().getSingleResult(query);
				if (data != null && data.length > 0) {
					bean.setSubjectName(checkNull(String.valueOf(data[0][0]).trim()));
					bean.setSubjectCode(checkNull(String.valueOf(data[0][1])));
					// bean.setContent(checkNull(String.valueOf(data[0][2])));
					bean.setCategoryCode("");
				}
				
				getContentList(bean, request, "subject", bean.getSubjectCode());
			}
			
			bean.setContentId("");
			bean.setContentOrder("");
			bean.setContentTitle("");
			bean.setContentType("");
			bean.setContentUrl("");
			bean.setContent("");
			
			request.setAttribute("labelName", "Content");
			request.setAttribute("from", from);
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	public void getContentList(ExamMaster bean, HttpServletRequest request, String sectionType, String sectionId) throws Exception {

		try {
			String query = " SELECT CONTENT_ID, NVL(CONTENT_TYPE,' '), NVL(CONTENT_TITLE,' '),"
					+ " NVL(CONTENT_URL,' ')"
					+ " FROM HRMS_WBT_CONTENT WHERE CONTENT_SEC_TYPE='"+sectionType+"' "
					+ " AND CONTENT_SEC_ID='"+sectionId+"' ORDER BY CONTENT_ORDER DESC";
			Object[][] data = getSqlModel().getSingleResult(query);

			ArrayList<Object> obj = new ArrayList<Object>();
			String[] pageIndex = Utility.doPaging(bean.getMyPage(), data.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("abc", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("xyz", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");

			if (data != null && data.length > 0) {
				bean.setDataLength(String.valueOf(data.length));
				request.setAttribute("contentCount", data.length);
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					ExamMaster bean1 = new ExamMaster();
					bean1.setContentId(String.valueOf(data[i][0]).trim());
					bean1.setContentType(String.valueOf(data[i][1]).trim().toUpperCase());
					bean1.setContentTitle(String.valueOf(data[i][2]));
					
					if(bean1.getContentType().equalsIgnoreCase("text")){
						bean1.setContentUrl("");
					} else {
						bean1.setContentUrl(checkNull(String.valueOf(data[i][3])));
					}
					
					obj.add(bean1);
				}
				bean.setContentList(obj);
			} else {
				bean.setDataLength("0");
				request.setAttribute("contentCount", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	public void setInstruction(ExamMaster bean, HttpServletRequest request, String from) {
		try {
			String query = " SELECT NVL(SUBJECT_NAME,' '), SUBJECT_CODE, SUBJECT_INSTRUCTION "
					+ " FROM HRMS_REC_SUBJECT "
					+ " WHERE SUBJECT_CODE = "
					+ bean.getSubjectCode();
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				bean.setSubjectName(checkNull(String.valueOf(data[0][0]).trim()));
				bean.setSubjectCode(checkNull(String.valueOf(data[0][1])));			
				bean.setContent(checkNull(String.valueOf(data[0][2])));
				bean.setCategoryCode("");
			}
			request.setAttribute("labelName", "Instructions");
			request.setAttribute("from", from);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public boolean updateContent(ExamMaster bean, HttpServletRequest request, String from) {
		boolean result = false;
		String query = "";
		try {			
			Object mod[][] = new Object[1][1];
			//mod[0][0] = bean.getContent();
			if(from.equals("catedit")) {
				/*mod[0][0] = bean.getCategoryCode();
				query = " UPDATE HRMS_REC_CATEGORY SET CAT_CONTENT = EMPTY_CLOB() WHERE CAT_CODE = ? ";
				result = getSqlModel().singleExecute(query, mod);
				if (result) {
					try {
						String sqlText = "SELECT CAT_CONTENT FROM HRMS_REC_CATEGORY WHERE  CAT_CODE = "
								+ bean.getCategoryCode() + " FOR UPDATE ";
						SqlModel model = getSqlModel();
						Connection conn = model.connection();
						Statement stmt = conn.createStatement();
						ResultSet rset = stmt.executeQuery(sqlText);
						rset.next();
						CLOB xmlDoent = ((OracleResultSet) rset)
								.getCLOB("CAT_CONTENT");
						xmlDoent.setString(1, bean.getContent());

						conn.commit();
						rset.close();
						stmt.close();
						model.freeConnection(conn);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}*/
				
				if (bean.getContentId().equals("")) {
					String sql = "INSERT INTO HRMS_WBT_CONTENT (CONTENT_ID, CONTENT_TYPE, CONTENT_SEC_TYPE, "
							+ "CONTENT_SEC_ID, CONTENT_TITLE, CONTENT_URL, CONTENT_ORDER)"
							+ " VALUES((SELECT NVL(MAX(CONTENT_ID),0)+1 FROM HRMS_WBT_CONTENT ),'"
							+ bean.getContentType()
							+ "','category',"
							+ bean.getCategoryCode()
							+ ",'" + bean.getContentTitle()
							+ "','" + bean.getContentUrl() + "',"
							+ " (SELECT NVL(MAX(HRMS_WBT_CONTENT.CONTENT_ORDER),0)+1 "
							+ " FROM HRMS_WBT_CONTENT "
							+ " WHERE HRMS_WBT_CONTENT.CONTENT_SEC_TYPE='category'"
							+ " AND  HRMS_WBT_CONTENT.CONTENT_SEC_ID = "
							+ bean.getCategoryCode() +") )";
					result = getSqlModel().singleExecute(sql);
					
					sql="SELECT NVL(MAX(CONTENT_ID),0) FROM HRMS_WBT_CONTENT"; 
					Object contentId[][] = getSqlModel().getSingleResult(sql);
					
					
					query = " UPDATE HRMS_WBT_CONTENT SET CONTENT_TEXT = EMPTY_CLOB()"
							+ " WHERE CONTENT_ID = " + contentId[0][0];
					result = getSqlModel().singleExecute(query);

					if (result) {
						try {
							String sqlText = "SELECT CONTENT_TEXT FROM HRMS_WBT_CONTENT WHERE  CONTENT_ID = "
									+ contentId[0][0] + " FOR UPDATE ";
							SqlModel model = getSqlModel();
							Connection conn = model.connection();
							Statement stmt = conn.createStatement();
							ResultSet rset = stmt.executeQuery(sqlText);
							rset.next();
							CLOB xmlDoent = ((OracleResultSet) rset).getCLOB("CONTENT_TEXT");
							xmlDoent.setString(1, bean.getContent());
							conn.commit();
							rset.close();
							stmt.close();
							model.freeConnection(conn);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					
					bean.setContentId("");
					bean.setContentOrder("");
					bean.setContentTitle("");
					bean.setContentType("");
					bean.setContentUrl("");
					bean.setContent("");
				} else {

					String sql = "UPDATE HRMS_WBT_CONTENT SET CONTENT_TYPE='"
							+ bean.getContentType() + "', CONTENT_TITLE='"
							+ bean.getContentTitle() + "', CONTENT_URL='"
							+ bean.getContentUrl() + "' WHERE CONTENT_ID='"
							+ bean.getContentId()+"'";
					result = getSqlModel().singleExecute(sql);

					query = " UPDATE HRMS_WBT_CONTENT SET CONTENT_TEXT = EMPTY_CLOB()"
							+ " WHERE CONTENT_ID = " + bean.getContentId();
					result = getSqlModel().singleExecute(query);

					if (result) {
						try {
							String sqlText = "SELECT CONTENT_TEXT FROM HRMS_WBT_CONTENT WHERE  CONTENT_ID = "
									+ bean.getContentId() + " FOR UPDATE ";
							SqlModel model = getSqlModel();
							Connection conn = model.connection();
							Statement stmt = conn.createStatement();
							ResultSet rset = stmt.executeQuery(sqlText);
							rset.next();
							CLOB xmlDoent = ((OracleResultSet) rset).getCLOB("CONTENT_TEXT");
							xmlDoent.setString(1, bean.getContent());

							conn.commit();
							rset.close();
							stmt.close();
							model.freeConnection(conn);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					bean.setContentId("");
					bean.setContentOrder("");
					bean.setContentTitle("");
					bean.setContentType("");
					bean.setContentUrl("");
					bean.setContent("");
					
				}
				
			} else {
				mod[0][0] = bean.getSubjectCode();
				/*query = " UPDATE HRMS_REC_SUBJECT SET SUBJECT_CONTENT = EMPTY_CLOB() WHERE SUBJECT_CODE = ? ";
				result = getSqlModel().singleExecute(query, mod);
				if (result) {
					try {
						String sqlText = "SELECT SUBJECT_CONTENT  FROM HRMS_REC_SUBJECT WHERE  SUBJECT_CODE = "
								+ bean.getSubjectCode() + " FOR UPDATE ";
						SqlModel model = getSqlModel();
						Connection conn = model.connection();
						Statement stmt = conn.createStatement();
						ResultSet rset = stmt.executeQuery(sqlText);
						rset.next();
						CLOB xmlDoent = ((OracleResultSet) rset)
								.getCLOB("SUBJECT_CONTENT");
						xmlDoent.setString(1, bean.getContent());

						conn.commit();
						rset.close();
						stmt.close();
						model.freeConnection(conn);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}*/
				
				if (bean.getContentId().equals("")) {
					String sql = "INSERT INTO HRMS_WBT_CONTENT (CONTENT_ID, CONTENT_TYPE, CONTENT_SEC_TYPE, "
							+ "CONTENT_SEC_ID, CONTENT_TITLE, CONTENT_URL, CONTENT_ORDER )"
							+ " VALUES((SELECT NVL(MAX(CONTENT_ID),0)+1 FROM HRMS_WBT_CONTENT ),'"
							+ bean.getContentType()
							+ "','subject','"
							+ bean.getSubjectCode()
							+ "','" + bean.getContentTitle()
							+ "','" + bean.getContentUrl() + "',"
							+ " (SELECT NVL(MAX(HRMS_WBT_CONTENT.CONTENT_ORDER),0)+1 "
							+ " FROM HRMS_WBT_CONTENT "
							+ " WHERE HRMS_WBT_CONTENT.CONTENT_SEC_TYPE='subject'"
							+ " AND  HRMS_WBT_CONTENT.CONTENT_SEC_ID = "
							+ bean.getSubjectCode() +") )";
					
					result = getSqlModel().singleExecute(sql);
					
					sql="SELECT NVL(MAX(CONTENT_ID),0) FROM HRMS_WBT_CONTENT"; 
					Object contentId[][] = getSqlModel().getSingleResult(sql);
					
					
					query = " UPDATE HRMS_WBT_CONTENT SET CONTENT_TEXT = EMPTY_CLOB()"
							+ " WHERE CONTENT_ID = " + contentId[0][0];
					result = getSqlModel().singleExecute(query);


					if (result) {
						try {
							String sqlText = "SELECT CONTENT_TEXT FROM HRMS_WBT_CONTENT WHERE  CONTENT_ID = "
									+ contentId[0][0] + " FOR UPDATE ";
							SqlModel model = getSqlModel();
							Connection conn = model.connection();
							Statement stmt = conn.createStatement();
							ResultSet rset = stmt.executeQuery(sqlText);
							rset.next();
							CLOB xmlDoent = ((OracleResultSet) rset).getCLOB("CONTENT_TEXT");
							xmlDoent.setString(1, bean.getContent());
							conn.commit();
							rset.close();
							stmt.close();
							model.freeConnection(conn);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					bean.setContentId("");
					bean.setContentOrder("");
					bean.setContentTitle("");
					bean.setContentType("");
					bean.setContentUrl("");
					bean.setContent("");
				} else {

					String sql = "UPDATE HRMS_WBT_CONTENT SET CONTENT_TYPE='"
							+ bean.getContentType() + "', CONTENT_TITLE='"
							+ bean.getContentTitle() + "', CONTENT_URL='"
							+ bean.getContentUrl() + "' WHERE CONTENT_ID='"
							+ bean.getContentId()+"'";
					result = getSqlModel().singleExecute(sql);

					query = " UPDATE HRMS_WBT_CONTENT SET CONTENT_TEXT = EMPTY_CLOB()"
							+ " WHERE CONTENT_ID = " + bean.getContentId();
					result = getSqlModel().singleExecute(query);

					if (result) {
						try {
							String sqlText = "SELECT CONTENT_TEXT FROM HRMS_WBT_CONTENT WHERE  CONTENT_ID = "
									+ bean.getContentId() + " FOR UPDATE ";
							SqlModel model = getSqlModel();
							Connection conn = model.connection();
							Statement stmt = conn.createStatement();
							ResultSet rset = stmt.executeQuery(sqlText);
							rset.next();
							CLOB xmlDoent = ((OracleResultSet) rset).getCLOB("CONTENT_TEXT");
							xmlDoent.setString(1, bean.getContent());

							conn.commit();
							rset.close();
							stmt.close();
							model.freeConnection(conn);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					bean.setContentId("");
					bean.setContentOrder("");
					bean.setContentTitle("");
					bean.setContentType("");
					bean.setContentUrl("");
					bean.setContent("");
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateInstruction(ExamMaster bean, HttpServletRequest request) {
		boolean result = false;
		try {			
			Object mod[][] = new Object[1][1];
			mod[0][0] = bean.getSubjectCode();
			
			String query = " UPDATE HRMS_REC_SUBJECT SET SUBJECT_INSTRUCTION = EMPTY_CLOB() WHERE SUBJECT_CODE = ? ";
			result = getSqlModel().singleExecute(query, mod);			
			if (result) {
				try {
					String sqlText = "SELECT SUBJECT_INSTRUCTION  FROM HRMS_REC_SUBJECT WHERE  SUBJECT_CODE = "
							+ bean.getSubjectCode() + " FOR UPDATE ";
					SqlModel model = getSqlModel();
					Connection conn = model.connection();
					Statement stmt = conn.createStatement();
					ResultSet rset = stmt.executeQuery(sqlText);
					rset.next();
					CLOB xmlDoent = ((OracleResultSet) rset)
							.getCLOB("SUBJECT_INSTRUCTION");
					xmlDoent.setString(1, bean.getContent());

					conn.commit();
					rset.close();
					stmt.close();
					model.freeConnection(conn);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void getRecords(ExamMaster bean, HttpServletRequest request) {
		try {
			String query = " SELECT NVL(SUBJECT_NAME,' '),	NVL(SUBJECT_ABBR,' '), SUBJECT_CODE,"
					+ " CASE WHEN SUBJECT_STATUS='A' THEN 'Yes' ELSE 'No' END"
					+ " FROM HRMS_REC_SUBJECT";
			Object[][] data = getSqlModel().getSingleResult(query);

			ArrayList<Object> obj = new ArrayList<Object>();
			String[] pageIndex = Utility.doPaging(bean.getMyPage(), data.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}

			request.setAttribute("abc", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("xyz", Integer.parseInt(String.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");

			if (data != null && data.length > 0) {
				bean.setDataLength(String.valueOf(data.length));
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					ExamMaster bean1 = new ExamMaster();
					bean1.setSubjectName(String.valueOf(data[i][0]).trim());
					bean1.setSubjectAbbr(String.valueOf(data[i][1]).trim());
					bean1.setSubjectCode(String.valueOf(data[i][2]));
					bean1.setSubjectStatus(checkNull(String.valueOf(data[i][3])));	
					if(bean1.getSubjectName().length() > 20) {
						bean1.setModuleNmLen(bean1.getSubjectName().substring(0, 20) + "....");
					} else {
						bean1.setModuleNmLen(bean1.getSubjectName());
					}
					
					obj.add(bean1);
				}
				bean.setSubjectList(obj);
			} else {
				bean.setDataLength("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void getRecordsofCat(ExamMaster bean,HttpServletRequest request){
	 * try{ String query = " SELECT NVL(CAT_NAME,' '),CAT_SUB_CODE,CAT_CODE FROM
	 * HRMS_REC_CATEGORY" +" ORDER BY CAT_CODE"; Object[][] data =
	 * getSqlModel().getSingleResult(query); ArrayList obj=new ArrayList();
	 * for(int i=0;i<data.length;i++){ ExamMaster bean1 = new ExamMaster();
	 * bean1.setSubjectCategoryName(String.valueOf(data[i][0]).trim());
	 * bean1.setSubjectCategoryCode(String.valueOf(data[i][1]).trim());
	 * bean1.setCategoryCode(String.valueOf(data[i][2])); obj.add(bean1); }
	 * bean.setCategoryList(obj); }catch(Exception e){ e.printStackTrace(); } }
	 */
	public boolean delChkdRec(ExamMaster bean, String[] code) {
		int count = 0;
		boolean result = false;
		
		
		for (int i = 0; i < code.length; i++) {
			if (!code[i].equals("")) {
				/*Object[][] delete = new Object[1][1];
				delete[0][0] = code[i];
				delete[0][1] = catCode[i];*/
				String deletecatQuery = "DELETE FROM HRMS_REC_CATEGORY WHERE CAT_SUB_CODE="
						+ String.valueOf(code[i]);
						/*+ " AND CAT_CODE="
						+ String.valueOf(catCode[i]);*/
				result = getSqlModel().singleExecute(deletecatQuery);
				// result=getSqlModel().singleExecute(getQuery(3),delete);
				
				String deleteSubQuery = "DELETE FROM HRMS_REC_SUBJECT WHERE SUBJECT_CODE="
						+ String.valueOf(code[i]);
				getSqlModel().singleExecute(deleteSubQuery);
				
				if (!result) {
					count++;
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

	/**
	 * following function is called to delete a record
	 * 
	 * @param bean
	 * @return
	 */
	public boolean deleteSubject(ExamMaster bean) {
		boolean result = false;
		Object del[][] = new Object[1][1];
		del[0][0] = bean.getSubjectCode();
		String deleteQue = "DELETE FROM HRMS_REC_QUESBANK WHERE QUES_SUB_CODE= " + bean.getSubjectCode();
		String deletecat = "DELETE FROM HRMS_REC_CATEGORY WHERE CAT_SUB_CODE=" + bean.getSubjectCode();
		try {
			String selectQue = "SELECT CAT_CODE FROM HRMS_REC_CATEGORY WHERE CAT_SUB_CODE="
					+ bean.getSubjectCode();
			Object[][] data = getSqlModel().getSingleResult(selectQue);
			if (data != null && data.length > 0) {
				for (int j = 0; j < data.length; j++) {
					String selectQues = "SELECT QUES_CODE FROM HRMS_REC_QUESBANK WHERE QUES_CAT_CODE="
							+ String.valueOf(data[j][0]);
					Object[][] data1 = getSqlModel().getSingleResult(selectQues);
					if (data1 != null && data1.length > 0) {
						for (int k = 0; k < data1.length; k++) {
							String deleteQues = "DELETE FROM HRMS_REC_QUESOPTION WHERE QUES_CODE = "
									+ String.valueOf(data1[k][0]);
							result = getSqlModel().singleExecute(deleteQues);
						}
					}
					String deleteQues = "DELETE FROM HRMS_REC_QUESBANK WHERE QUES_CAT_CODE = "
							+ String.valueOf(data[j][0]);
					result = getSqlModel().singleExecute(deleteQues);
				}
			}

			String selectQues = "SELECT QUES_CODE FROM HRMS_REC_QUESBANK WHERE QUES_SUB_CODE="
				+ bean.getSubjectCode();
			Object[][] data1 = getSqlModel().getSingleResult(selectQues);
			if (data1 != null && data1.length > 0) {
				for (int k = 0; k < data1.length; k++) {
					String deleteQues = "DELETE FROM HRMS_REC_QUESOPTION WHERE QUES_CODE = "
							+ String.valueOf(data1[k][0]);
					result = getSqlModel().singleExecute(deleteQues);
				}
			}
			
			result = getSqlModel().singleExecute(deleteQue);
			result = getSqlModel().singleExecute(deletecat);

			result = getSqlModel().singleExecute(getQuery(3), del);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean deleteSubjectCategory(ExamMaster bean, Object[] catName,
			Object[] no, Object[] code, Object[] catCode) {
		int n = 0;
		logger.info("I am in Model--->");
		logger.info("length of hidcode in model---->" + code.length);
		ArrayList<Object> tableList = new ArrayList<Object>();
		logger.info("no.length---->" + no.length);
		if (no != null && no.length > 0) {

			for (int j = 0; j < no.length; j++) {
				if (j + 1 == Integer.parseInt(bean.getParaId())) {
					String.valueOf("cat code in if" + catCode[j]);
				}
			}

			int j = 1;
			logger.info("hggggggggggggg" + bean.getParaId());
			for (int i = 0; i < no.length; i++) {
				ExamMaster bean1 = new ExamMaster();

				if ((i + 1 != Integer.parseInt(bean.getParaId()))) {

					bean1.setSrNo(String.valueOf(j));
					bean1.setSubjectCategoryNameItr(String.valueOf(catName[i]));
					bean1.setCategoryCodeItr(String.valueOf(catCode[i]));
					bean1.setFlag(true);
					tableList.add(bean1);
					j++;
					logger.info("I am in If of model----> ");
					// bean1.setCategoryCodeItr(String.valueOf(catCode[i]));
				}

			}
		}
		bean.setCategoryList(tableList);
		logger.info("table list size----->" + tableList.size());
		n = tableList.size();
		logger.info("locallist size------->" + tableList.size());
		if (n == 0) {
			bean.setCatList(false);
		} else {
			bean.setCatList(true);
		}
		return true;
	}

	/* for checking duplicate entry of records during insertion. */
	public boolean checkDuplicate(ExamMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_REC_SUBJECT WHERE UPPER(SUBJECT_NAME) LIKE '"
				+ bean.getSubjectName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}

	/* for checking duplicate entry of records during modification. */
	public boolean checkDuplicateMod(ExamMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_REC_SUBJECT WHERE UPPER(SUBJECT_NAME) LIKE '"
				+ bean.getSubjectName().trim().toUpperCase()
				+ "' AND SUBJECT_CODE NOT IN(" + bean.getSubjectCode() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}
	
	
	/* for checking duplicate entry of records during insertion. */
	public boolean checkDuplicateSection(ExamMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_REC_CATEGORY WHERE UPPER(CAT_NAME) LIKE  '"
				+ bean.getCategoryName().trim().toUpperCase() + "' AND CAT_SUB_CODE= "+bean.getSubjectCode();
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}

	/* for checking duplicate entry of records during modification. */
	public boolean checkDuplicateSectionMod(ExamMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_REC_CATEGORY WHERE UPPER(CAT_NAME) LIKE '"
				+ bean.getCategoryName().trim().toUpperCase()
				+ "' AND CAT_CODE NOT IN(" + bean.getHEditCatCode() + ") AND CAT_SUB_CODE= "+bean.getSubjectCode();
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * Following function is called to get the report of the Pdf format for list
	 * of Subject Records
	 */
	public void generateReport(ExamMaster examMaster,
			HttpServletResponse response, String[] label) {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "Subject Master";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Subject Master.Pdf");
		String queryDes = "SELECT SUBJECT_NAME,SUBJECT_ABBR  FROM HRMS_REC_SUBJECT ORDER BY SUBJECT_CODE";
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
			int cellwidth[] = { 5, 20, 20 };
			int alignment[] = { 1, 0, 0 };
			rg.addTextBold("Subject Master", 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}
		rg.createReport(response);
	}
}