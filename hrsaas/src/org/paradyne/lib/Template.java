/**
 * 
 */
package org.paradyne.lib;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.IntegerValidator;
import org.nfunk.jep.function.Str;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateQuery;

/**
 * @author aa0431
 * 
 */
public class Template extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(Template.class);
	private String templateID;
	private String templateBody;
	private HashMap<Integer, TemplateQuery> templateQuery = new HashMap<Integer, TemplateQuery>();
	private HashMap<Integer, Object> templateQueryType = new HashMap<Integer, Object>();
	private HashMap<Integer, Object> templateQueryName = new HashMap<Integer, Object>();

	public Template(String id) {
		setTemplateID(id);
	}

	public void getTemplateQueries() {
		templateQuery = getTemplateQuery();
		System.out.println("templateQuery.size()....." + templateQuery.size());

		/*
		 * System.out.println("Using Iterator"); Iterator<Map.Entry<Integer,
		 * TemplateQuery>> iterator1 = templateQuery.entrySet().iterator() ;
		 * TemplateQuery q1 = templateQuery.get(getTemplateQuery());
		 * while(iterator1.hasNext()){
		 * 
		 * Entry<Integer, TemplateQuery> tEntry = iterator1.next();
		 * System.out.println(tEntry.getKey() +" :: "+ tEntry.getValue());
		 * System.out.println("--------------->>>"+q1.getQuery(1));
		 * System.out.println("--------------->>>"+q1.getQueryString()); }
		 * return q1.getQueryId();
		 */

		for (int i = 0; i < 5; i++) {
			if (templateQuery.get(i) == null) {
				templateQuery.remove(i);
			} else {
				TemplateQuery q1 = (TemplateQuery) templateQuery.get(i);
				System.out.println("-------------------->>>>>>>"
						+ templateQuery.get(i));
				// --Commented by Jigar to skip code
				// System.out.println("-------------------->>>>>>>"+q1.getQuery(i));
				// System.out.println("-------------------->>>>>>>"+templateQuery.getQuery(i));
				System.out.println("ssssssssss" + q1.getQueryId());
			}
		}
	}

	public TemplateQuery getTemplateQuery(int id) {

		return templateQuery.get(id);
	}

	public TemplateQuery getTemplateQueryType(int id) {

		return templateQuery.get(id);
	}

	public HashMap<Integer, TemplateQuery> getTemplateQuery() {
		// fire query and get list of all queries
		HashMap<Integer, TemplateQuery> templateQueries = new HashMap<Integer, TemplateQuery>();
		HashMap<Integer, Object> templateQueryTypes = new HashMap<Integer, Object>();
		HashMap<Integer, Object> templateQueryNames = new HashMap<Integer, Object>();
		String query = "SELECT  QUERY_ID,QUERY_TYPE,QUERY_STRING,NVL(QUERY_NAME,' ') "
				+ " FROM HRMS_LETTERTEMPLATE_DTL "

				+ " WHERE HRMS_LETTERTEMPLATE_DTL.TEMPLATE_ID = " + templateID;

		// fire a query to identify qury code; 1, 2, 3,4
		Object[][] data = getSqlModel().getSingleResult(query);
		for (int i = 0; i < data.length; i++) {
			int queryNo = Integer.parseInt(String.valueOf(data[i][0]));
			logger.info("queryNo......" + queryNo);
			TemplateQuery templateQuery = new TemplateQuery(queryNo);
			templateQueries.put(queryNo, templateQuery);
			templateQueryTypes.put(queryNo, data[i][1]);
			templateQueryNames.put(queryNo, data[i][3]);
		}
		templateQueryType = templateQueryTypes;
		templateQueryName = templateQueryNames;
		return templateQueries;
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response, String fileName, String empId,
			String empName) {
		execute(request, response, fileName, false, empId, empName);
		return null;
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response, String fileName,
			boolean returnTemplate, String empId, String empName) {

		try {
			templateBody = readCLOBToFileStream();
			/*
			 * while (number of queries) { query 1 -- execute (); }
			 */
		} catch (Exception e) {
			// TODO: handle exception
		}
		// System.out.println("templateQuery.size()....." +
		// templateQuery.size());

		/*
		 * Query added for Variables in the Annexture
		 */
		String varQuery = "SELECT VAR_NAME, VAR_FORMULA, VAR_PRIORITY FROM HRMS_LETTERTEMPLATE_VAR WHERE TEMPLATE_ID="
				+ templateID;
		Object[][] varData = getSqlModel().getSingleResult(varQuery);

		/*
		 * String grossQuery = "SELECT VAR_FORMULA FROM HRMS_LETTERTEMPLATE_VAR
		 * WHERE TEMPLATE_ID=" + templateID; Object[][] grossData =
		 * getSqlModel().getSingleResult(grossQuery);
		 */

		String empQuery = "SELECT EMP_TOKEN,EMP_FNAME,EMP_LNAME,EMP_GRADE,EMP_ROLE,DEPT_NAME,CENTER_NAME,RATING FROM HRMS_EMP_OFFC"
				+ " LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT)"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN APPR_RATING ON(APPR_RATING.EMP_CODE=HRMS_EMP_OFFC.EMP_ID)"
				+ " WHERE EMP_ID = " + empId;
		Object[][] empData = getSqlModel().getSingleResult(empQuery);

		// boolean includesSalary = false;
		Object[][] salaryData = new Object[0][4];
		Vector vect = new Vector();

		for (int i = 0; i < 6; i++) {
			if (templateQuery.get(i) == null) {
				System.out.println("<<<<<<<<<<<<<null1>>>>>>>>>>>");
			} else {

				TemplateQuery templateQueries = templateQuery.get(i);

				String qyeryType = (String) templateQueryType.get(i);
				int templateQueryId = templateQueries.getQueryId();

				System.out.println("qyeryType---------" + qyeryType);
				templateQueries.initiate(context, session);
				Object[][] queryData = null;

				if (qyeryType.equals("S")) {
					try {
						queryData = templateQueries.execute(templateID, "S");
					} catch (Exception e) {
						// TODO: handle exception

						queryData = new Object[0][4];
					}
					if (queryData != null && queryData.length > 0) {
						for (int j = 0; j < queryData.length; j++) {

							for (int j2 = 0; j2 < queryData[0].length; j2++) {

								
								if (empData != null && empData.length > 0) {

									for (int i1 = 0; i1 < empData.length; i1++) {
										for (int i2 = 0; i2 < empData[0].length; i2++)

											templateBody = templateBody
													.replaceAll(
															"&lt;#EMP_CODE#&gt;",
															String
																	.valueOf(empData[i1][i2]));
										templateBody = templateBody.replaceAll(
												"&lt;#EMP_FNAME#&gt;",
												String.valueOf(empData[i1][1]));
										templateBody = templateBody.replaceAll(
												"&lt;#REVISED_GRADE#&gt;",
												String.valueOf(empData[i1][3]));
										templateBody = templateBody.replaceAll(
												"&lt;#DESIGNATION#&gt;",
												String.valueOf(empData[i1][4]));
										templateBody = templateBody.replaceAll(
												"&lt;#MYROLE#&gt;",
												String.valueOf(empData[i1][4]));
										templateBody = templateBody.replaceAll(
												"&lt;#EMP_DEPARTMENT#&gt;",
												String.valueOf(empData[i1][5]));
										templateBody = templateBody.replaceAll(
												"&lt;#EMP_BRANCH#&gt;",
												String.valueOf(empData[i1][6]));
										templateBody = templateBody.replaceAll(
												"&lt;#RATING#&gt;",
												String.valueOf(empData[i1][7]));

									}

								}

								templateBody = templateBody.replaceAll(
										"&lt;#EMP_NAME#&gt;", empName);
								templateBody = templateBody.replaceAll("&lt;#"
										+ templateQueryId + "_ROW" + (j + 1)
										+ "_COL" + (j2 + 1) + "#&gt;", String
										.valueOf(queryData[j][j2]));
								templateBody = templateBody.replaceAll("<#"
										+ templateQueryId + "_ROW" + (j + 1)
										+ "_COL" + (j2 + 1) + "#>", String
										.valueOf(queryData[j][j2]));

								/*
								 * if (grossData != null && grossData.length >
								 * 0) { for (int i1 = 0; i1 < grossData.length;
								 * i1++) { for (int i2 = 0; i2 <
								 * grossData[0].length; i2++){
								 * 
								 * templateBody = templateBody.replaceAll(
								 * "&lt;#MONTH_GROSS#&gt", String
								 * .valueOf(queryData[][j2]));
								 * 
								 * templateBody = templateBody.replaceAll(
								 * "&lt;#ANNUAL_GROSS#&gt", String
								 * .valueOf(queryData[j][j2]));
								 *  }
								 * 
								 *  }
								 *  }
								 */

								if (varData != null && varData.length > 0) {
									for (int k = 0; k < varData.length; k++) {
										varData[k][1] = String
												.valueOf(varData[k][1])
												.replaceAll(
														"&lt;#"
																+ templateQueryId
																+ "_ROW"
																+ (j + 1)
																+ "_COL"
																+ (j2 + 1)
																+ "#&gt;",
														String
																.valueOf(queryData[j][j2]));
										varData[k][1] = String
												.valueOf(varData[k][1])
												.replaceAll(
														"<#" + templateQueryId
																+ "_ROW"
																+ (j + 1)
																+ "_COL"
																+ (j2 + 1)
																+ "#>",
														String
																.valueOf(queryData[j][j2]));
										System.out
												.println("varData[k][1]      Point 1  "
														+ varData[k][1]);
									}
								}

							}
						}

					}

					// salaryData = new Utility().joinArrays(salaryData,
					// queryData,
					// 1,0);
					// includesSalary = true;
				} else if (qyeryType.equals("O")) {
					// ADDED BY SHASHIKANT
					try {
						StringTokenizer tokens = new StringTokenizer(
								templateBody, "$");
						System.out.println("token length = "
								+ tokens.countTokens());
						String[] str_tokens = new String[tokens.countTokens()];
						int counter = 0;
						while (tokens.hasMoreElements()) {
							String token = tokens.nextToken();
							str_tokens[counter++] = token;
						}
						templateBody = "";
						for (int j = 0; j < str_tokens.length; j++) {

							str_tokens[j] = str_tokens[j];
							if (j % 2 != 0) {
								int temQueryId = 0;
								try {
									temQueryId = Integer.parseInt(str_tokens[j]
											.substring(0, 2));
								} catch (Exception e) {
									temQueryId = Integer.parseInt(str_tokens[j]
											.substring(0, 1));
								}

								System.out
										.println("str_tokens[j].substring(0, 1)   :"
												+ str_tokens[j].substring(0, 1));
								TemplateQuery templateTabDataQuery = templateQuery
										.get(temQueryId);
								templateTabDataQuery.initiate(context, session);
								queryData = templateTabDataQuery.execute(
										templateID, "D");
								String query = "SELECT  QUERY_STRING,QUERY_TYPE,QUERY_COLUMN_WIDTH "
										+ " FROM HRMS_LETTERTEMPLATE_DTL "

										+ " WHERE HRMS_LETTERTEMPLATE_DTL.TEMPLATE_ID = "
										+ templateID
										+ " AND HRMS_LETTERTEMPLATE_DTL.QUERY_ID = "
										+ temQueryId;

								Object[][] widthData = getSqlModel()
										.getSingleResult(query);
								String[] width = String
										.valueOf(widthData[0][2]).split(",");
								System.out.println("str_tokens[j]...:  "
										+ str_tokens[j]);
								// str_tokens[j].replaceAll("#&gt;", "$");
								String[] splitedHeaders = str_tokens[j]
										.split("#");

								String replaceWithTable = "<table border='1' bordercolor='#7F7F7F'  style='border-collapse:collapse;' cellspacing='0'  cellpadding='0' width='100%'><tr style='BACKGROUND-COLOR: #c0c0c0' align='center'>";

								/**
								 * For adding the Headers only
								 */
								// int header_counter=0;
								Vector<String> vectHeader = new Vector<String>();
								for (int k = 0; k < splitedHeaders.length; k++) {

									String header = splitedHeaders[k];// .substring(0,indexLast);

									if (k % 2 != 0) {
										// header_counter++;
										String[] splitWidth = header.split("_");
										// String tempHeader =
										// header.substring(0,header.lastIndexOf("_"));
										// width='"+
										// splitWidth[splitWidth.length -
										// 1]+ "'

										replaceWithTable += "<td><strong><font size='2' face='Times New Roman' >"
												+ initCap(header.replaceAll(
														"_", " "))
												+ "</font></strong></td>";
										vectHeader.add(header);
									}

								}
								replaceWithTable += "</tr>";

								/**
								 * For adding the no. of rows
								 */
								String alignStr = "";
								for (int k2 = 0; k2 < queryData.length - 1; k2++) {
									replaceWithTable += "<tr align='center'>";

									for (int k = 0; k < vectHeader.size(); k++) {
										for (int l = 0; l < queryData[0].length; l++) {
											if (vectHeader
													.get(k)
													.equals(
															String
																	.valueOf(queryData[0][l]))) {

												if (l == 0) {
													alignStr = "left";
												} else {
													alignStr = "right";
												}

												String[] splitWidth = vectHeader
														.get(k).split("_");
												// width='"+splitWidth[splitWidth.length-1]+"'
												replaceWithTable += "<td align='"
														+ alignStr
														+ "' width='"
														+ width[l]
														+ "%' ><font size='2' face='Times New Roman' >"
														+ String
																.valueOf(queryData[k2 + 1][l])
														+ "</font></td>";
											}
										}

									} // end of a single Row
									replaceWithTable += "</tr>";

								}
								replaceWithTable += "</table>";
								// ADDED BY LAKKICHAND
								// logger.info("queryData.length..."+queryData.length);
								if (queryData.length > 1) {
									str_tokens[j] = replaceWithTable;
								} else
									str_tokens[j] = replaceWithTable
											+ "<table border='1' bordercolor='#7F7F7F'  style='border-collapse:collapse;' cellspacing='0'  cellpadding='0' width='750'><tr>"
											+ "<td align='center'><font size='2' face='Times New Roman'><strong>NA.</strong></font></td></tr></table>";
							}
							templateBody += str_tokens[j];
						}

					} catch (Exception e) {
						logger.error(e);
						e.printStackTrace();
					}

				} else {

					try {
						queryData = templateQueries.execute(templateID, "");
						// logger.info("String.valueOf(queryData[0][j])"+String.valueOf(queryData[0][1]));

						for (int j = 0; j < queryData[0].length; j++) {
							String whichIsToBeReplace = "&lt;#"
									+ String.valueOf(queryData[0][j]) + "#&gt;";
							logger.info("whichIsToBeReplace...."
									+ whichIsToBeReplace);
							String whichVarToReplace = "&lt;#"
									+ String.valueOf(queryData[0][j]) + "#&gt;";
							String whichVarToReplace_1 = "<#"
									+ String.valueOf(queryData[0][j]) + "#>";

							String replaceBy = "&lt;#NO DATA TO DISPLAY#&gt;";
							try {
								replaceBy = String.valueOf(queryData[1][j]);
							} catch (Exception e) {
								// TODO: handle exception

								logger.info("Query doesnt get Result.....");
							}

							/**
							 * Added for Variables by Lakkichand
							 */

							try {
								if (varData != null && varData.length > 0) {
									for (int k = 0; k < varData.length; k++) {

										varData[k][1] = String.valueOf(
												varData[k][1]).replaceAll(
												whichVarToReplace, replaceBy);
										varData[k][1] = String.valueOf(
												varData[k][1]).replaceAll(
												whichVarToReplace_1, replaceBy);

										System.out
												.println("varData[k][1]      Point 2  "
														+ varData[k][1]);

									}

								}
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
							templateBody = templateBody.replaceAll(
									whichIsToBeReplace, replaceBy);
						}
					} catch (Exception e) {
						logger
								.info("Exeption in Passing Passing Parameters \n Query doesnt get Result");
						// TODO: handle exception
					}

				}

				templateQueries.terminate();
			}
		}

		try {
			for (int k = 0; k < varData.length; k++) {

				try {
					System.out.println("varData[k][1]      Point 3  "
							+ varData[k][1]);

					varData[k][1] = new Utility().expressionEvaluate(String
							.valueOf(varData[k][1]));
					try {
						System.out.println("..replaceBy  Variable.."
								+ varData[k][1]);
						String query = " SELECT TO_CHAR(" + varData[k][1]
								+ ",'99,99,999') FROM DUAL ";
						Object[][] queryResult = getSqlModel().getSingleResult(
								query);
						if (queryResult != null && queryResult.length > 0) {
							varData[k][1] = String.valueOf(queryResult[0][0]);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				} catch (Exception e) {

					// TODO: handle exception
					e.printStackTrace();
					varData[k][1] = "0";
				}
				System.out.println("---------" + String.valueOf(varData[k][0])
						+ "......" + String.valueOf(varData[k][1]));
				String varValue = String.valueOf(varData[k][1]);
				try {
					/*
					 * varValue = String.valueOf(Math.round(Double
					 * .parseDouble(String.valueOf(varData[k][1]))));
					 */
				} catch (Exception e) {
					// TODO: handle exception
					varValue = "";
				}
				templateBody = templateBody.replaceAll("<#"
						+ String.valueOf(varData[k][0]) + "#>", varValue);
				templateBody = templateBody.replaceAll("&lt;#"
						+ String.valueOf(varData[k][0]) + "#&gt;", varValue);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		/*
		 * if (includesSalary) {
		 * System.out.println("salaryData.length..."+salaryData.length); for
		 * (int j = 0; j < salaryData.length; j++) {
		 * 
		 * if (String.valueOf(salaryData[j][1]).equals("M")) {
		 * vect.add(String.valueOf(salaryData[j][0]));
		 * vect.add(String.valueOf(salaryData[j][2])); vect.add("Monthly");
		 * vect.add(String.valueOf(salaryData[j][3])); } } for (int j = 0; j <
		 * salaryData.length; j++) {
		 * if(String.valueOf(salaryData[j][1]).equals("Q")) {
		 * vect.add(String.valueOf(salaryData[j][0]));
		 * vect.add(String.valueOf(salaryData[j][2])); vect.add("Quarterly");
		 * vect.add(String.valueOf(salaryData[j][3])); } } for (int j = 0; j <
		 * salaryData.length; j++) {
		 * if(String.valueOf(salaryData[j][1]).equals("H")) {
		 * vect.add(String.valueOf(salaryData[j][0]));
		 * vect.add(String.valueOf(salaryData[j][2])); vect.add("Half Yearly");
		 * vect.add(String.valueOf(salaryData[j][3])); } } for (int j = 0; j
		 * <salaryData.length; j++) {
		 * if(String.valueOf(salaryData[j][1]).equals("A")) {
		 * vect.add(String.valueOf(salaryData[j][0]));
		 * vect.add(String.valueOf(salaryData[j][2])); vect.add("Annualy");
		 * vect.add(String.valueOf(salaryData[j][3])); } } }
		 */
		/*
		 * String salaryTable = "<table align='center' border='1'
		 * cellspacing='0' cellpadding='0'><tr>" + "<td><strong>Sr. No.</strong></td>" + "<td><strong>Salary
		 * Component</strong></td>" + "<td><strong>Periodicity</strong></td>" + "<td><strong>Amount</strong></td>" + "</tr>";
		 * int counter=0; for (int j = 0; j < vect.size()/4; j++) { //
		 * System.out.println("vect.get(counter++)"+vect.get(counter));
		 * salaryTable+="<tr>" + "<td>"+vect.get(counter++)+"</td>" + "<td>"+vect.get(counter++)+"</td>" + "<td>"+vect.get(counter++)+"</td>" + "<td>"+vect.get(counter++)+"</td>" + "</tr>"; }
		 * salaryTable+="</table>";
		 */

		/*
		 * generateTemplate () ; --- write to response;
		 */
		/**
		 * 
		 * Tempate template = new template(); template.initize() template. id //
		 * pass id getquery(1) passparameters getquery(2) passparameters
		 * template.execute();
		 * 
		 */
		logger.info("i am in rep Gene");
		String type = "Txt";
		String title = "Template letter";

		try {
			if (!returnTemplate) {
				String finaldata = "<html>" + templateBody + "</html>";
				org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
						type, title);

				byte[] buf = finaldata.getBytes();

				response.setContentType("application/msword");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + fileName + ".doc\"");
				response.setHeader("cache-control", "no-cache");
				response.getOutputStream().write(buf);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return templateBody;
	}

	public String getTemplateID() {
		return templateID;
	}

	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}

	public String readCLOBToFileStream() throws IOException, SQLException {

		String sqlText = "SELECT TEMPLATE_BODY FROM  HRMS_LETTERTEMPLATE_HDR WHERE  TEMPLATE_ID ="
				+ getTemplateID();
		Object[][] data = getSqlModel().getSingleResult(sqlText);

		logger.info("STRING +++++++++++++++++++++++++++++MESSAGE+++++++++===="
				+ String.valueOf(data[0][0]));

		return String.valueOf(data[0][0]);
	}

	/**
	 * @author Reeba_Joseph method to execute template without preview
	 * @param request
	 * @param response
	 * @param fileName
	 * @return
	 */

	public String executeWriteFile(HttpServletRequest request,
			HttpServletResponse response, String fileName) {

		try {
			templateBody = readCLOBToFileStream();
			// templateBody = getbody(templateID);
			/*
			 * while (number of queries) { query 1 -- execute (); }
			 */
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("templateQuery.size()....." + templateQuery.size());
		// boolean includesSalary = false;
		Object[][] salaryData = new Object[0][4];
		Vector vect = new Vector();
		for (int i = 0; i < templateQuery.size(); i++) {
			TemplateQuery templateQueries = templateQuery.get(i + 1);

			String qyeryType = (String) templateQueryType.get(i + 1);
			int templateQueryId = templateQueries.getQueryId();
			System.out.println("qyeryType---------" + qyeryType);
			templateQueries.initiate(context, session);
			Object[][] queryData = null;
			if (qyeryType.equals("S")) {
				try {
					queryData = templateQueries.execute(templateID, "S");
				} catch (Exception e) {
					// TODO: handle exception

					queryData = new Object[0][4];
				}
				if (queryData != null && queryData.length > 0) {
					for (int j = 0; j < queryData.length; j++) {

						for (int j2 = 0; j2 < queryData[0].length; j2++) {
							templateBody = templateBody.replaceAll("&lt;#"
									+ templateQueryId + "_ROW" + (j + 1)
									+ "_COL" + (j2 + 1) + "#&gt;", String
									.valueOf(queryData[j][j2]));
							templateBody = templateBody.replaceAll("<#"
									+ templateQueryId + "_ROW" + (j + 1)
									+ "_COL" + (j2 + 1) + "#>", String
									.valueOf(queryData[j][j2]));
						}
					}

				}

				// salaryData = new Utility().joinArrays(salaryData, queryData,
				// 1,0);
				// includesSalary = true;
			} else {

				try {
					queryData = templateQueries.execute(templateID, "");
					// logger.info("String.valueOf(queryData[0][j])"+String.valueOf(queryData[0][1]));

					for (int j = 0; j < queryData[0].length; j++) {
						String whichIsToBeReplace = "&lt;#"
								+ String.valueOf(queryData[0][j]) + "#&gt;";
						logger.info("whichIsToBeReplace...."
								+ whichIsToBeReplace);
						String replaceBy = "&lt;#NO DATA TO DISPLAY#&gt;";
						try {
							replaceBy = String.valueOf(queryData[1][j]);
						} catch (Exception e) {
							// TODO: handle exception

							logger.info("Query doesnt get Result.....");
						}

						templateBody = templateBody.replaceAll(
								whichIsToBeReplace, replaceBy);
					}
				} catch (Exception e) {
					logger
							.info("Exeption in Passing Passing Parameters \n Query doesnt get Result");
					// TODO: handle exception
				}

			}
			templateQueries.terminate();
		}
		return templateBody;
	}

	public static String initCap(String name) {
		String properName = "";

		try {
			StringTokenizer tokens = new StringTokenizer(name);
			int count = 0;
			while (tokens.hasMoreElements()) {
				String token = tokens.nextToken();
				token = token.toLowerCase();
				String init = token.substring(0, 1).toUpperCase();

				token = init + token.substring(1, token.length());
				if (count != 0)
					token = " " + token;
				properName += token;
				count++;

			}
		} catch (Exception e) {
			// TODO: handle exception
			properName = name;
			return properName;
		}
		return properName;
	}

}
