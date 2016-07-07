package org.paradyne.model.ApplicationStudio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.ApplicationStudio.EmailTemplate;
import org.paradyne.lib.ModelBase;

public class EmailTemplateModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmailTemplateModel.class);

	public EmailTemplate addItem(EmailTemplate emailTemplate, String[] srNo,
			String[] queryName, String[] queryType, int check,
			String[] noqueryparameter, String[] queryparameter) {
		try {
			ArrayList<EmailTemplate> tableList = new ArrayList<EmailTemplate>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					EmailTemplate bean = new EmailTemplate();
					bean.setSrNo(String.valueOf(i + 1));
					bean.setQueryName(queryName[i]);
					bean.setQtype(queryType[i]);
					if (queryType[i].equals("F")) {
						bean.setQtype1("From Mail Id");
					} else if (queryType[i].equals("T")) {
						bean.setQtype1("To Mail Id");
					} else if (queryType[i].equals("D")) {
						bean.setQtype1("Table Data");
					} else {
						bean.setQtype1("Others");
					}
					bean.setIttrnoqueryparameter(checkNull(noqueryparameter[i])
							.trim());
					bean.setIttrqueryparameter(checkNull(queryparameter[i])
							.trim());
					tableList.add(bean);
				}
			}
			if (check == 1) {
				EmailTemplate bean = new EmailTemplate();
				bean.setSrNo(String.valueOf(tableList.size() + 1));
				bean.setQueryName(emailTemplate.getQName());
				bean.setQtype(emailTemplate.getQuerytype());
				if (emailTemplate.getQuerytype().equals("F")) {
					bean.setQtype1("From Mail Id");
				} else if (emailTemplate.getQuerytype().equals("T")) {
					bean.setQtype1("To Mail Id");
				} else if (emailTemplate.getQuerytype().equals("D")) {
					bean.setQtype1("Table Data");
				} else {
					bean.setQtype1("Others");
				}
				bean.setIttrnoqueryparameter(checkNull(
						emailTemplate.getNoqueryparameter()).trim());
				bean.setIttrqueryparameter(checkNull(
						emailTemplate.getQueryparameter()).trim());
				tableList.add(bean);
			} else if (check == 0) {
				tableList
						.remove(Integer.parseInt(emailTemplate.getSubcode()) - 1);
			}
			if (tableList.size() != 0) {
				emailTemplate.setTableLength("1");
			} else {
				emailTemplate.setTableLength("0");
			}
			emailTemplate.setList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailTemplate;
	}

	/**
	 * method to check the duplicate entry of record during insertion
	 * 
	 * @param letterTemplate
	 * @return boolean
	 */
	public boolean checkDuplicate(EmailTemplate letterTemplate) {
		boolean result = false;
		try {
			String query = "SELECT * FROM HRMS_EMAILTEMPLATE_HDR WHERE UPPER(TEMPLATE_NAME) LIKE '"
					+ letterTemplate.getTempName().trim().toUpperCase() + "'";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}// end of if
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	/**
	 * method to check duplicate entry of record during modification 
	 * @param letterTemplate
	 * @return boolean
	 */
	public boolean checkDuplicateMod(EmailTemplate letterTemplate) {
		boolean result = false;
		try {
			String query = "SELECT * FROM HRMS_EMAILTEMPLATE_HDR WHERE UPPER(TEMPLATE_NAME) LIKE '"
					+ letterTemplate.getTempName().trim().toUpperCase()
					+ "' AND TEMPLATE_ID NOT IN("
					+ letterTemplate.getTempCode() + ")";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}// end of if
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	/**
	 * THIS METHOD IS USED FOR CHECKING NULL VALUES
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}// end of else
	}// end of checkNull

	public String createTempList(EmailTemplate emailTemplate, String[] srNo,
			String[] queryName, String[] queryType, String[] field) {
		/* To add paramaters to the template. Setting the labels from the query */
		/*
		 * String listQuery = emailTemplate.getQName(); logger.info("Queries
		 * ----:" + listQuery); StringTokenizer st = new
		 * StringTokenizer(listQuery);
		 */

		ArrayList<Object> tempList = new ArrayList<Object>();
		ArrayList<Object> fromList = new ArrayList<Object>();
		ArrayList<Object> toList = new ArrayList<Object>();
		ArrayList<Object> tableList = new ArrayList<Object>();

		String listQuery[] = new String[queryName.length];
		StringTokenizer st = null;
		for (int k = 0; k < queryType.length; k++) {
			EmailTemplate tableBean = new EmailTemplate();
			listQuery[k] = queryName[k];
			st = new StringTokenizer(listQuery[k]);

			// get how many tokens inside st object
			String[] splitedQuery = new String[st.countTokens()];

			// iterate st object to get more tokens from it
			int count = 0;
			while (st.hasMoreElements()) {
				splitedQuery[count] = st.nextElement().toString();
				System.out.println("token = " + splitedQuery[count]);
				count++;
			}
			ArrayList list = new ArrayList();
			for (int i = 0; i < splitedQuery.length; i++) {
				if (splitedQuery[i].toUpperCase().equals("AS")) {
					list.add(splitedQuery[i + 1]);
				}
			}
			String[] fields = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				fields[i] = (String) list.get(i);
			}
			HashMap hashTable = new HashMap();
			HashMap hashTableSel = new HashMap();
			for (int i = 0; i < fields.length; i++) {
				EmailTemplate bean = new EmailTemplate();
				bean.setField(String.valueOf(fields[i]));
				if (queryType[k].equals("F")) {
					fromList.add(bean);
				} else if (queryType[k].equals("T")) {
					toList.add(bean);
				} else if (queryType[k].equals("D")) {
					logger.info("TABLE DATA  :" + fields[i]);
					hashTable.put(String.valueOf(fields[i]), String
							.valueOf(fields[i]));
				}
				else {
					tempList.add(bean);
				}
			}
			hashTable = (HashMap<Object, Object>) org.paradyne.lib.Utility
					.sortMapByValue(hashTable, null, true);
			if (queryType[k].equals("D")) {
				System.out.println("----------------   " + (k + 1));
				tableBean.setSrNoOther(String.valueOf((k + 1)));
				tableBean.setHashmapTable(hashTable);
				tableBean.setHashmapTableSel(hashTableSel);
				tableList.add(tableBean);
			}
			emailTemplate.setFromiterate(fromList);
			emailTemplate.setToiterate(toList);
			emailTemplate.setIterate(tempList);
		}
		emailTemplate.setIttTable(tableList);
		return "Query Added";
	}

	public void createTempListForDelete(EmailTemplate template, String[] srNo,
			String[] queryName, String[] field) {
		try {
			ArrayList<Object> tempList = new ArrayList<Object>();
			if (queryName != null) {
				for (int i = 0; i < queryName.length; i++) {
					if (i == Integer.parseInt(template.getHiddenEdit()) - 1) {
					} else {
						StringTokenizer st = new StringTokenizer(queryName[i]);
						// get how many tokens inside st object
						String[] splitedQuery = new String[st.countTokens()];
						// iterate st object to get more tokens from it
						int count = 0;
						while (st.hasMoreElements()) {
							splitedQuery[count] = st.nextElement().toString();
							count++;
						}
						ArrayList list = new ArrayList();
						for (int j = 0; j < splitedQuery.length; j++) {
							if (splitedQuery[j].toUpperCase().equals("AS")) {
								list.add(splitedQuery[j + 1]);
							}
						}
						String[] fields = new String[list.size()];
						for (int k = 0; k < list.size(); k++) {
							fields[k] = (String) list.get(k);

						}
						for (int m = 0; m < fields.length; m++) {
							EmailTemplate bean = new EmailTemplate();
							bean.setField(String.valueOf(fields[m]));
							tempList.add(bean);
						}
					}
				}
			}
			template.setIterate(tempList);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public String createTempListForEdit(EmailTemplate template, String[] srNo,
			String[] queryName, String[] field) {
		try {
			ArrayList<Object> tempList = new ArrayList<Object>();
			if (queryName != null) {
				for (int i = 0; i < queryName.length; i++) {
					if (i == Integer.parseInt(template.getHiddenEdit()) - 1) {
					} else {
						StringTokenizer st = new StringTokenizer(queryName[i]);

						// get how many tokens inside st object
						String[] splitedQuery = new String[st.countTokens()];

						// iterate st object to get more tokens from it
						int count = 0;
						while (st.hasMoreElements()) {
							splitedQuery[count] = st.nextElement().toString();
							count++;
						}
						ArrayList list = new ArrayList();
						for (int j = 0; j < splitedQuery.length; j++) {
							if (splitedQuery[j].toUpperCase().equals("AS")) {
								list.add(splitedQuery[j + 1]);
							}
						}
						String[] fields = new String[list.size()];
						for (int k = 0; k < list.size(); k++) {
							fields[k] = (String) list.get(k);

						}

						for (int m = 0; m < fields.length; m++) {
							EmailTemplate bean = new EmailTemplate();
							bean.setField(String.valueOf(fields[m]));
							tempList.add(bean);
						}
					}
				}
			}
			String listQuery = template.getQName();
			StringTokenizer st = new StringTokenizer(listQuery);

			// get how many tokens inside st object
			String[] splitedQuery = new String[st.countTokens()];

			// iterate st object to get more tokens from it
			int count = 0;
			while (st.hasMoreElements()) {
				splitedQuery[count] = st.nextElement().toString();
				count++;
			}
			ArrayList list = new ArrayList();
			for (int i = 0; i < splitedQuery.length; i++) {
				if (splitedQuery[i].toUpperCase().equals("AS")) {
					list.add(splitedQuery[i + 1]);
				}
			}
			String[] fields = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				System.out.println("list..." + list.get(i));
				fields[i] = (String) list.get(i);

			}
			for (int i = 0; i < fields.length; i++) {
				EmailTemplate bean = new EmailTemplate();
				bean.setField(String.valueOf(fields[i]));
				tempList.add(bean);
			}
			template.setIterate(tempList);
		} catch (Exception e) {
			logger.error(e);
		}
		return "Query Added";
	}

	public String createTempListForEdit1(EmailTemplate emailTemplate,
			String[] srNo, String[] queryName, String[] field) {
		/* To add paramaters to the template. Setting the labels from the query */

		String listQuery = emailTemplate.getQName();
		StringTokenizer st = new StringTokenizer(listQuery);

		// get how many tokens inside st object
		String[] splitedQuery = new String[st.countTokens()];

		// iterate st object to get more tokens from it
		int count = 0;
		while (st.hasMoreElements()) {
			splitedQuery[count] = st.nextElement().toString();
			count++;
		}
		ArrayList list = new ArrayList();
		for (int i = 0; i < splitedQuery.length; i++) {
			if (splitedQuery[i].toUpperCase().equals("AS")) {
				list.add(splitedQuery[i + 1]);
			}
		}
		String[] fields = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			System.out.println("list..." + list.get(i));
			fields[i] = (String) list.get(i);

		}

		ArrayList<Object> tempList = new ArrayList<Object>();

		if (field != null) {
			for (int i = 0; i < field.length; i++) {
				EmailTemplate bean = new EmailTemplate();
				bean.setField(String.valueOf(field[i]));
				tempList.add(bean);
			}
		}
		emailTemplate.setIterate(tempList);
		return "Query Added";
	}

	public boolean delDtl(EmailTemplate emailTemplate, String[] code,
			String[] queryName) {
		try {
			ArrayList<EmailTemplate> list = new ArrayList<EmailTemplate>();
			if (queryName != null) {
				for (int i = 0; i < queryName.length; i++) {
					EmailTemplate bean = new EmailTemplate();
					if ((String.valueOf(code[i]).equals(""))) {
						bean.setQueryName(queryName[i]);
						list.add(bean);
					}
				}
			}
			emailTemplate.setList(list);
		} catch (Exception e) {
			logger.error(e);
		}
		return true;
	}

	public boolean deleteQuery(String[] srNo, String[] queryName,
			EmailTemplate template, String[] noqueryparm, String[] queryparm) {
		try {
			ArrayList <EmailTemplate> tableList = new ArrayList <EmailTemplate>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					EmailTemplate bean = new EmailTemplate();
					bean.setSrNo(String.valueOf(i + 1));
					bean.setQueryName(queryName[i]);
					bean.setIttrnoqueryparameter(checkNull(noqueryparm[i]));
					bean.setIttrqueryparameter(checkNull(queryparm[i]));
					tableList.add(bean);
				}// end of for
				tableList
						.remove(Integer.parseInt(template.getHiddenEdit()) - 1);
			}
			template.setList(tableList);
		} catch (Exception e) {
			logger.error(e);
		}
		return true;
	}

	public EmailTemplate moditem(EmailTemplate emailTemplate, String[] srNo,
			String[] queryName, String[] queryType, int check,
			String[] noqueryparameter, String[] queryparameter) {
		ArrayList <EmailTemplate> tableList = new ArrayList <EmailTemplate>();

		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				EmailTemplate bean = new EmailTemplate();

				if (i == Integer.parseInt(emailTemplate.getHiddenEdit()) - 1) {
					bean.setSrNo(String.valueOf(i + 1));
					bean.setQueryName(emailTemplate.getQName());
					bean.setQtype(emailTemplate.getQuerytype());
					if (emailTemplate.getQuerytype().equals("F")) {
						bean.setQtype1("From Mail Id");
					} else if (emailTemplate.getQuerytype().equals("T")) {
						bean.setQtype1("To Mail Id");
					} else if (emailTemplate.getQuerytype().equals("D")) {
						bean.setQtype1("Table Data");
					} else {
						bean.setQtype1("Others");
					}
					bean.setIttrnoqueryparameter(checkNull(
							emailTemplate.getNoqueryparameter()).trim());
					bean.setIttrqueryparameter(checkNull(
							emailTemplate.getQueryparameter()).trim());
				} else {
					bean.setSrNo(String.valueOf(i + 1));
					bean.setQueryName(queryName[i]);
					bean.setQtype(queryType[i]);
					if (queryType[i].equals("F")) {
						bean.setQtype1("From Mail Id");
					} else if (queryType[i].equals("T")) {
						bean.setQtype1("To Mail Id");
					} else if (queryType[i].equals("D")) {
						bean.setQtype1("Table Data");
					} else {
						bean.setQtype1("Others");
					}
					// if(noqueryparameter!=null && noqueryparameter.length>0)
					bean.setIttrnoqueryparameter(checkNull(noqueryparameter[i])
							.trim());
					// if(queryparameter!=null && queryparameter.length>0)
					bean.setIttrqueryparameter(checkNull(queryparameter[i])
							.trim());
				}
				tableList.add(bean);
			}
		}

		if (tableList.size() != 0) {
			emailTemplate.setTableLength("1");
		} else {
			emailTemplate.setTableLength("0");
		}
		emailTemplate.setList(tableList);
		return emailTemplate;
	}

	public String readCLOBToFileStream(EmailTemplate tempBean)
			throws IOException, SQLException {
		String sqlText = "SELECT TEMPLATE_BODY FROM  HRMS_EMAILTEMPLATE_HDR WHERE  TEMPLATE_ID ="
				+ tempBean.getTempCode();
		Object[][] data = getSqlModel().getSingleResult(sqlText);
		return String.valueOf(data[0][0]);
	}

	public void restoreDefault(EmailTemplate template) {
		boolean result = false;
		String hdrQuery = " UPDATE HRMS_EMAILTEMPLATE_HDR SET TEMPLATE_BODY = TEMPLATE_DEFAULT_BODY, TEMPLATE_FROM_MAIL = TEMPLATE_DEFAULT_FROM_MAIL,"
				+ " TEMPLATE_TO_MAIL = TEMPLATE_DEFAULT_TO_MAIL, TEMPLATE_SUBJECT = TEMPLATE_DEFAULT_SUBJECT "
				+ " WHERE TEMPLATE_ID = " + template.getTempCode();
		result = getSqlModel().singleExecute(hdrQuery);
	}

	public boolean save(EmailTemplate emailTemplate, String[] srNo,
			String[] queryName, String[] queryType, String[] noqueryparameter,
			String[] queryparameter) {
		boolean result = false;
		try {
			boolean res = checkDuplicate(emailTemplate);
			if (!checkDuplicate(emailTemplate)) {
				Object[][] insertdata = new Object[1][10];
				insertdata[0][0] = emailTemplate.getTempName();
				insertdata[0][1] = "";
				insertdata[0][2] = emailTemplate.getHtmlcode();
				insertdata[0][3] = "";
				insertdata[0][4] = "";
				insertdata[0][5] = "";
				insertdata[0][6] = emailTemplate.getFromMailId();
				insertdata[0][7] = emailTemplate.getToMailId();
				insertdata[0][8] = emailTemplate.getSubject();
				insertdata[0][9] = emailTemplate.getModuleCode();
				logger.info("getModuleCode........!!!"
						+ emailTemplate.getModuleCode());

				String hdrQuery = " INSERT INTO HRMS_EMAILTEMPLATE_HDR(TEMPLATE_ID, TEMPLATE_DATE, TEMPLATE_NAME,TEMPLATE_BODY, TEMPLATE_DEFAULT_BODY,TEMPLATE_FROM_MAIL,TEMPLATE_TO_MAIL,TEMPLATE_SUBJECT,"
						+ " TEMPLATE_DEFAULT_FROM_MAIL, TEMPLATE_DEFAULT_TO_MAIL, TEMPLATE_DEFAULT_SUBJECT,TEMPLATE_EVENT_CODE) "
						+ " VALUES((SELECT NVL(MAX(TEMPLATE_ID),0) + 1 FROM HRMS_EMAILTEMPLATE_HDR), SYSDATE, ?,?, ?, ?, ?,?, ?,?,?,?)";
				result = getSqlModel().singleExecute(hdrQuery, insertdata);

				if (result) {
					String query = " SELECT MAX(TEMPLATE_ID) FROM HRMS_EMAILTEMPLATE_HDR ";
					Object maxcode[][] = getSqlModel().getSingleResult(query);

					String dtlQuery = " INSERT INTO HRMS_EMAILTEMPLATE_DTL(QUERY_ID, TEMPLATE_ID, QUERY_STRING, QUERY_TYPE,QUERY_PARAM_NO, QUERY_PARAMETERS) "
							+ " VALUES (?, "
							+ String.valueOf(maxcode[0][0])
							+ ", ?, ?,?,?)";
					Object[][] insData = null;
					insData = new Object[queryName.length][5];
					if (queryName != null && queryName.length > 0) {
						for (int i = 0; i < queryName.length; i++) {
							insData[i][0] = i + 1;
							insData[i][1] = String.valueOf(queryName[i]);
							insData[i][2] = String.valueOf(queryType[i]);

							insData[i][3] = checkNull(String
									.valueOf(noqueryparameter[i]));

							insData[i][4] = checkNull(String
									.valueOf(queryparameter[i]));

						}
						result = getSqlModel().singleExecute(dtlQuery, insData);
					}
				}
			} else {
				result = false;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	/**
	 * method to set the jsp fields with the data by setting the bean variables
	 * 
	 * @param field
	 * @param queryName
	 * @param srNo
	 * @param tempBean
	 */
	public void setTemplateData(HttpServletRequest request, EmailTemplate template, String type) {
		try {
			String queryHDR = "";
			if (type.equals("D")) {
				queryHDR = " SELECT HRMS_EMAILTEMPLATE_HDR.TEMPLATE_ID, TEMPLATE_NAME, TEMPLATE_DEFAULT_BODY, TEMPLATE_DEFAULT_FROM_MAIL,"
						+ " TEMPLATE_DEFAULT_TO_MAIL, TEMPLATE_DEFAULT_SUBJECT,HRMS_MODULE.MODULE_NAME,TEMPLATE_EVENT_CODE FROM HRMS_EMAILTEMPLATE_HDR "
						+ "left join HRMS_MODULE on (HRMS_MODULE.MODULE_CODE=HRMS_EMAILTEMPLATE_HDR.TEMPLATE_EVENT_CODE)"
						+ " WHERE HRMS_EMAILTEMPLATE_HDR.TEMPLATE_ID = "
						+ template.getTempCode();
			} else if (type.equals("C")) {
				queryHDR = " SELECT HRMS_EMAILTEMPLATE_HDR.TEMPLATE_ID, TEMPLATE_NAME, NVL(TEMPLATE_BODY, TEMPLATE_DEFAULT_BODY),"
						+ " NVL(TEMPLATE_FROM_MAIL, TEMPLATE_DEFAULT_FROM_MAIL), NVL(TEMPLATE_TO_MAIL, TEMPLATE_DEFAULT_TO_MAIL), "
						+ " NVL(TEMPLATE_SUBJECT,TEMPLATE_DEFAULT_SUBJECT)"
						+ ", HRMS_MODULE.MODULE_NAME,TEMPLATE_EVENT_CODE "
						+ "FROM HRMS_EMAILTEMPLATE_HDR "
						+ " left join HRMS_MODULE on (HRMS_MODULE.MODULE_CODE=HRMS_EMAILTEMPLATE_HDR.TEMPLATE_EVENT_CODE)"
						+ " WHERE HRMS_EMAILTEMPLATE_HDR.TEMPLATE_ID = "
						+ template.getTempCode();
			}
			Object[][] dataHDR = getSqlModel().getSingleResult(queryHDR);

			 request.setAttribute("defaultBodyText", String.valueOf(dataHDR[0][2]));
			 template.setHtmlcode(String.valueOf(dataHDR[0][2]));
			
			String queryDTL = " SELECT QUERY_STRING,QUERY_TYPE,nvl(QUERY_PARAM_NO,''), nvl(QUERY_PARAMETERS,'') FROM HRMS_EMAILTEMPLATE_DTL "
					+ " WHERE HRMS_EMAILTEMPLATE_DTL.TEMPLATE_ID = "
					+ template.getTempCode() + " ORDER BY QUERY_ID ";
			if (dataHDR != null && dataHDR.length > 0) {
				template.setTempName(checkNull(String.valueOf(dataHDR[0][1])));
				template.setHtmlcode(checkNull(String.valueOf(dataHDR[0][2])));
				template
						.setFromMailId(checkNull(String.valueOf(dataHDR[0][3])));
				template.setToMailId(checkNull(String.valueOf(dataHDR[0][4])));
				template.setSubject(checkNull(String.valueOf(dataHDR[0][5])));

				template
						.setModuleName(checkNull(String.valueOf(dataHDR[0][6])));
				template
						.setModuleCode(checkNull(String.valueOf(dataHDR[0][7])));
			}
			Object[][] dataDTL = getSqlModel().getSingleResult(queryDTL);

			ArrayList <EmailTemplate> list = new ArrayList <EmailTemplate>();
			if (dataDTL != null && dataDTL.length > 0) {
				for (int i = 0; i < dataDTL.length; i++) {
					EmailTemplate bean = new EmailTemplate();
					bean.setQueryName(checkNull(String.valueOf(dataDTL[i][0])));
					bean.setSrNo(String.valueOf(i + 1));
					bean.setQtype(checkNull(String.valueOf(dataDTL[i][1])));
					if (String.valueOf(dataDTL[i][1]).equals("F")) {
						bean.setQtype1("From Mail Id");
					} else if (String.valueOf(dataDTL[i][1]).equals("T")) {
						bean.setQtype1("To Mail Id");
					} else if (String.valueOf(dataDTL[i][1]).equals("D")) {
						bean.setQtype1("Table Data");
					}

					else {
						bean.setQtype1("Others");
					}
					bean.setIttrnoqueryparameter(checkNull(String
							.valueOf(dataDTL[i][2])));
					bean.setIttrqueryparameter(checkNull(String
							.valueOf(dataDTL[i][3])));
					list.add(bean);
				}
				template.setList(list);
			}
			if (list.size() == 0) {
				template.setTableLength("0");
			} else {
				template.setTableLength("1");
			}

			ArrayList<Object> tempList = new ArrayList<Object>();
			ArrayList<Object> fromList = new ArrayList<Object>();
			ArrayList<Object> toList = new ArrayList<Object>();
			ArrayList<Object> tableList = new ArrayList<Object>();

			StringTokenizer st = null;
			ArrayList<EmailTemplate> itlist = new ArrayList <EmailTemplate>();
			if (dataDTL != null && dataDTL.length > 0) {
				for (int i = 0; i < dataDTL.length; i++) {
					EmailTemplate bean = new EmailTemplate();
					bean.setQueryName(checkNull(String.valueOf(dataDTL[i][0])));
					bean.setQtype(checkNull(String.valueOf(dataDTL[i][1])));
					if (String.valueOf(dataDTL[i][1]).equals("F")) {
						bean.setQtype1("From Mail Id");
					} else if (String.valueOf(dataDTL[i][1]).equals("T")) {
						bean.setQtype1("To Mail Id");
					} else if (String.valueOf(dataDTL[i][1]).equals("D")) {
						bean.setQtype1("Table Data");
					} else {
						bean.setQtype1("Others");
					}
					bean.setSrNo(String.valueOf(i + 1));
					bean.setIttrnoqueryparameter(checkNull(String
							.valueOf(dataDTL[i][2])));
					bean.setIttrqueryparameter(checkNull(String
							.valueOf(dataDTL[i][3])));
					itlist.add(bean);
				}
				template.setList(itlist);
			}
			if (list.size() == 0) {
				template.setTableLength("0");
			} else {
				template.setTableLength("1");
			}

			ArrayList<Object> iterList = new ArrayList<Object>();
			StringTokenizer st1 = null;
			if (dataDTL != null && dataDTL.length > 0) {
				for (int i = 0; i < dataDTL.length; i++) {
					st1 = new StringTokenizer(String.valueOf(dataDTL[i][0]));

					// get how many tokens inside st object
					String[] splitedQuery = new String[st1.countTokens()];

					// iterate st object to get more tokens from it
					int count = 0;
					while (st1.hasMoreElements()) {
						splitedQuery[count] = st1.nextElement().toString();
						count++;
					}
					ArrayList list1 = new ArrayList();
					for (int j = 0; j < splitedQuery.length; j++) {
						if (splitedQuery[j].toUpperCase().equals("AS")) {
							list1.add(splitedQuery[j + 1]);
						}
					}
					String[] fields = new String[list1.size()];

					for (int k = 0; k < list1.size(); k++) {
						fields[k] = (String) list1.get(k);

					}
					HashMap hashTable = new HashMap();
					HashMap hashTableSel = new HashMap();

					EmailTemplate tableBean = new EmailTemplate();
					for (int k = 0; k < fields.length; k++) {
						EmailTemplate bean = new EmailTemplate();
						bean.setField(String.valueOf(fields[k]));
						if (dataDTL[i][1].equals("F")) {
							fromList.add(bean);
						} else if (dataDTL[i][1].equals("T")) {
							toList.add(bean);
						} else if (dataDTL[i][1].equals("D")) {
							logger.info("TABLE DATA  :" + fields[k]);
							hashTable.put(String.valueOf(fields[k]), String
									.valueOf(fields[k]));
						} else {
							tempList.add(bean);
						}
					}

					hashTable = (HashMap<Object, Object>) org.paradyne.lib.Utility
							.sortMapByValue(hashTable, null, true);
					if (dataDTL[i][1].equals("D")) {
						tableBean.setSrNoOther("" + (i + 1));
						tableBean.setHashmapTable(hashTable);
						tableBean.setHashmapTableSel(hashTableSel);
						tableList.add(tableBean);
					}
				}
			}
			template.setFromiterate(fromList);
			template.setToiterate(toList);
			template.setIterate(tempList);
			template.setIttTable(tableList);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public boolean update(EmailTemplate template) {
		boolean result = false;
		try {
			if (!(checkDuplicateMod(template))) {
				// create a file and save the content in that file
				Object[][] updateData = new Object[1][5];
				updateData[0][0] = template.getHtmlcode();
				updateData[0][1] = template.getFromMailId();
				updateData[0][2] = template.getToMailId();
				updateData[0][3] = template.getSubject();
				updateData[0][4] = template.getTempCode();
				String hdrQuery = " UPDATE HRMS_EMAILTEMPLATE_HDR SET TEMPLATE_DATE = SYSDATE, TEMPLATE_BODY = ?, "
						+ " TEMPLATE_FROM_MAIL = ?, TEMPLATE_TO_MAIL = ?, TEMPLATE_SUBJECT = ? WHERE TEMPLATE_ID = ? ";
				result = getSqlModel().singleExecute(hdrQuery, updateData);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	public boolean update(EmailTemplate template, String[] srNo,
			String[] queryName, String[] queryType, String[] noqueryparameter,
			String[] queryparameter) {
		boolean result = false;
		try {
			if (!(checkDuplicateMod(template))) {
				// create a file and save the content in that file
				Object[][] updateData = new Object[1][7];
				updateData[0][0] = template.getTempName();
				updateData[0][1] = template.getHtmlcode();
				updateData[0][2] = template.getFromMailId();
				updateData[0][3] = template.getToMailId();
				updateData[0][4] = template.getSubject();

				updateData[0][5] = template.getModuleCode();

				updateData[0][6] = template.getTempCode();

				logger.info("getModuleCode........!!!"
						+ template.getModuleCode());

				String hdrQuery = " UPDATE HRMS_EMAILTEMPLATE_HDR SET TEMPLATE_DATE=SYSDATE, TEMPLATE_NAME = ?, TEMPLATE_DEFAULT_BODY = ?,"
						+ " TEMPLATE_DEFAULT_FROM_MAIL = ?, TEMPLATE_DEFAULT_TO_MAIL = ?, TEMPLATE_DEFAULT_SUBJECT = ? ,TEMPLATE_EVENT_CODE=? "
						+ " WHERE TEMPLATE_ID =? ";
				result = getSqlModel().singleExecute(hdrQuery, updateData);

				if (result) {
					String deleteQuery = " DELETE FROM HRMS_EMAILTEMPLATE_DTL WHERE TEMPLATE_ID = "
							+ template.getTempCode();

					String dtlQuery = " INSERT INTO HRMS_EMAILTEMPLATE_DTL(QUERY_ID, TEMPLATE_ID, QUERY_STRING, QUERY_TYPE,QUERY_PARAM_NO, QUERY_PARAMETERS) "
							+ " VALUES (?, "
							+ template.getTempCode()
							+ ", ?, ?,?,?)";
					result = getSqlModel().singleExecute(deleteQuery);

					Object[][] insData = new Object[queryName.length][5];
					for (int i = 0; i < queryName.length; i++) {
						insData[i][0] = String.valueOf(srNo[i]);
						insData[i][1] = String.valueOf(queryName[i]);
						insData[i][2] = String.valueOf(queryType[i]);

						insData[i][3] = checkNull(String
								.valueOf(noqueryparameter[i]));

						insData[i][4] = checkNull(String
								.valueOf(queryparameter[i]));

					}
					result = getSqlModel().singleExecute(dtlQuery, insData);
				}
			} else {
				result = false;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	public Object[][] setTemplateDefaultBody(EmailTemplate emailBean) {
		Object[][] tempObj = null;
		return tempObj;
	}

	public Object[][] setBodyText(String tempCode) {		
		String defaultBodyTxt= " SELECT TEMPLATE_DEFAULT_BODY "
							   + " FROM HRMS_EMAILTEMPLATE_HDR WHERE TEMPLATE_ID ="+tempCode;
		Object[][] defaultTxtObj = getSqlModel().getSingleResult(defaultBodyTxt);
		return defaultTxtObj;
	}
}