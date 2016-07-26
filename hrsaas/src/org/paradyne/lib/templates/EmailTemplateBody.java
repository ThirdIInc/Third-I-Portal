package org.paradyne.lib.templates;

import java.sql.Connection;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.admin.master.TemplateMaster;
import org.paradyne.lib.MailModel;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.PPEncrypter;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.admin.srd.SendMailModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;

/**
 * @author Reeba_Joseph
 * @date 01-April-2009
 * @modifiedBy Lakkichand_Golegaonkar
 * @modifiedBy Vishwambhar_Deshpande
 */

/*
 * Note:- Please find note as OnlineApproval For Live link = "http://" +
 * clientName + ".peoplepower.com" should be For Test link = "http://" +
 * request.getServerName() + ":" should be
 */
public class EmailTemplateBody extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmailTemplateBody.class);
	
	
	private String templateID;
	private String templateBody;
	private String fromMailId;
	private String fromEmpId;
	private String toMailIds;
	private String subject;
	private String subj_body;
	private HashMap<Integer, EmailTemplateQuery> templateQuery = new HashMap<Integer, EmailTemplateQuery>();
	private HashMap<Integer, Object> templateQueryType = new HashMap<Integer, Object>();
	String newToMailIds[] = null;
	String[] newFromMailId = new String[1];

	public void clearParameters() {
		try {
			templateID = null;
			templateBody = null;
			fromMailId = null;
			toMailIds = null;
			subject = null;
			subj_body = null;
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * To send a mail and alert, replace all the values obtained through queries
	 * from application.
	 */
	public void configMailAlert() {
		try {
			// Retrieving from database
			
			String emailIds = " SELECT NVL(TEMPLATE_FROM_MAIL, TEMPLATE_DEFAULT_FROM_MAIL), NVL(TEMPLATE_TO_MAIL, TEMPLATE_DEFAULT_TO_MAIL), "
					+ " NVL(TEMPLATE_SUBJECT, TEMPLATE_DEFAULT_SUBJECT), NVL(TEMPLATE_BODY, TEMPLATE_DEFAULT_BODY) "
					+ " FROM HRMS_EMAILTEMPLATE_HDR WHERE TEMPLATE_ID = "
					+ templateID;
			Object[][] data = getSqlModel().getSingleResult(emailIds);
			fromMailId = String.valueOf(data[0][0]);
			toMailIds = String.valueOf(data[0][1]);
			subject = String.valueOf(data[0][2]);
			templateBody = String.valueOf(data[0][3]);
			subj_body = subject + "####" + templateBody;// Concatenation passing to
			// id as array
			Object[][] queryData = null;
			for (int i = 0; i < templateQuery.size(); i++) {// main loop X
				EmailTemplateQuery templateQueries = (EmailTemplateQuery) templateQuery
						.get(i + 1);
				String queryType = (String) templateQueryType.get(i + 1);
				templateQueries.initiate(context, session);
				if (queryType.equals("F")) {// if queryType is F
					try {
						// execute the query for 'F'
						queryData = templateQueries.execute(templateID, "F");
						fromEmpId = templateQueries.getEmployeeId();
					} catch (Exception e) {
						logger.error(e);
					}

					String toBeReplaced = "<#"
							+ String.valueOf(queryData[0][0]) + "#>";
					String replaceWith = "<#NO DATA TO DISPLAY#>";

					try {
						replaceWith = String.valueOf(queryData[1][0]);
					} catch (Exception e) {
						logger.error(e);
					}
					System.out.println("Before..  fromMailId  "+fromMailId);
					System.out.println("toBeReplaced.. " + toBeReplaced
							+ " replaceWith.. " + replaceWith);
					fromMailId = fromMailId.replaceAll(toBeReplaced,
							replaceWith);
					System.out.println("After..  fromMailId  "+fromMailId);
					newFromMailId[0] = fromMailId;
				} else if (queryType.equals("T")) {// if queryType is T
					try {
						queryData = templateQueries.execute(templateID, "T");
					} catch (Exception e) {
						logger.error(e);
					}

					for (int j = 0; j < queryData[0].length; j++) {// loop x
						// for no. of to mail ids query found
						String toBeReplaced = "<#"
								+ String.valueOf(queryData[0][j]) + "#>";

						String replaceWith = "<#NO DATA TO DISPLAY#>";
						try {
							replaceWith = String.valueOf(queryData[1][j]);
						} catch (Exception e) {
							logger.error(e);
						}// end of catch block

						toMailIds = toMailIds.replaceAll(toBeReplaced,
								replaceWith);
					}// end loop x

					// to mail Id's saved in database as a;b;c;d :Hence split them
					newToMailIds = toMailIds.split(";");
				} else if (queryType.equals("D")) {
					// ADDED BY SHASHIKANT
					try {
						StringTokenizer tokens = new StringTokenizer(subj_body,
								"$");
						System.out.println("token length = "
								+ tokens.countTokens());
						String[] str_tokens = new String[tokens.countTokens()];
						int counter = 0;
						while (tokens.hasMoreElements()) {
							String token = tokens.nextToken();
							str_tokens[counter++] = token;
						}
						subj_body = "";
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
								EmailTemplateQuery templateTabDataQuery = (EmailTemplateQuery) templateQuery
										.get(temQueryId);
								templateTabDataQuery.initiate(context, session);
								queryData = templateTabDataQuery.execute(
										templateID, "D");

								System.out.println("str_tokens[j]...:  "
										+ str_tokens[j]);
								// str_tokens[j].replaceAll("#&gt;", "$");
								String[] splitedHeaders = str_tokens[j]
										.split("#");

								String replaceWithTable = "<table border='1' bordercolor='#7F7F7F'  style='border-collapse:collapse;' cellspacing='0'  cellpadding='0' width='750'><tr style='BACKGROUND-COLOR: #c0c0c0' align='center'>";

								/**
								 * For adding the Headers only
								 */
								// int header_counter=0;
								Vector vectHeader = new Vector();
								for (int k = 0; k < splitedHeaders.length; k++) {

									String header = splitedHeaders[k];// .substring(0,indexLast);

									if (k % 2 != 0) {
										// header_counter++;
										String[] splitWidth = header.split("_");
										// String tempHeader =
										// header.substring(0,header.lastIndexOf("_"));
										// width='"+ splitWidth[splitWidth.length -
										// 1]+ "'

										replaceWithTable += "<td><strong><font size='2' face='Arial' >"
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
								if(queryData!=null && queryData.length>0){
								for (int k2 = 0; k2 < queryData.length - 1; k2++) {
									replaceWithTable += "<tr align='center'>";
									for (int k = 0; k < vectHeader.size(); k++) {
										for (int l = 0; l < queryData[0].length; l++) {
											if (vectHeader
													.get(k)
													.equals(
															String
																	.valueOf(queryData[0][l]))) {
												String[] splitWidth = ((String) vectHeader
														.get(k)).split("_");
												// width='"+splitWidth[splitWidth.length-1]+"'
												replaceWithTable += "<td><font size='2' face='Arial' >"
														+ String
																.valueOf(queryData[k2 + 1][l])
														+ "</font></td>";
											}
										}

									} // end of a single Row
									replaceWithTable += "</tr>";

								}
								}
								replaceWithTable += "</table>";
								// ADDED BY LAKKICHAND
								// logger.info("queryData.length..."+queryData.length);
								
								if (queryData!=null && queryData.length > 1) {
									str_tokens[j] = replaceWithTable;
								} else
									str_tokens[j] = replaceWithTable
											+ "<table border='1' bordercolor='#7F7F7F'  style='border-collapse:collapse;' cellspacing='0'  cellpadding='0' width='750'><tr>"
											+ "<td align='center'><font size='2' face='Arial'><strong>NA.</strong></font></td></tr></table>";
							}
							subj_body += str_tokens[j];
						}

					} catch (Exception e) {
						logger.error(e);
						e.printStackTrace();
					}

				}

				else {
					// for QueryType = Other
					// First, subject and body is concatenated together. Then it is
					// replaced and split
					try {
						queryData = templateQueries.execute(templateID, "");

						for (int j = 0; j < queryData[0].length; j++) {// loop x
							// for no. of queries found
							String toBeReplaced = "&lt;#"
									+ String.valueOf(queryData[0][j]) + "#&gt;";
							String toBeReplaced_if_sub = "<#"
									+ String.valueOf(queryData[0][j]) + "#>";

							String replaceWith = "&lt;#NO DATA TO DISPLAY#&gt;";
							try {
								replaceWith = String.valueOf(queryData[1][j]);
							} catch (Exception e) {
								logger.error(e);
								e.printStackTrace();
							}// end catch block

							subj_body = subj_body.replaceAll(toBeReplaced,
									replaceWith);// Replaced
							subj_body = subj_body.replaceAll(
									toBeReplaced_if_sub, replaceWith);
						}
					} catch (Exception e) {
						logger.error(e);
					}
				}// end else

				templateQueries.terminate();
			}// end main for X
			
			 // passing from id as array
			
			System.out.println("newFromMailId[0] ...       :" + newFromMailId[0]);
			System.out.println("subj_body       :" + subj_body);
		} catch (Exception e) {
			System.out.println("Exception::::");
		e.printStackTrace();
		}
	}// end of execute() method

	/**
	 * To send a mail and alert, replace all the values obtained through queries
	 * from application.
	 */
	public void configMailAlert(Connection dbConn) {
		try {
			// Retrieving from database
			String emailIds = " SELECT NVL(TEMPLATE_FROM_MAIL, TEMPLATE_DEFAULT_FROM_MAIL), NVL(TEMPLATE_TO_MAIL, TEMPLATE_DEFAULT_TO_MAIL), "
					+ " NVL(TEMPLATE_SUBJECT, TEMPLATE_DEFAULT_SUBJECT), NVL(TEMPLATE_BODY, TEMPLATE_DEFAULT_BODY) "
					+ " FROM HRMS_EMAILTEMPLATE_HDR WHERE TEMPLATE_ID = "
					+ templateID;
			Object[][] data = getSqlModel().getSingleResult(emailIds, dbConn);
			fromMailId = String.valueOf(data[0][0]);
			toMailIds = String.valueOf(data[0][1]);
			subject = String.valueOf(data[0][2]);
			templateBody = String.valueOf(data[0][3]);
			subj_body = subject + "####" + templateBody;// Concatenation passing
			// to
			// id as array
			Object[][] queryData = null;
			for (int i = 0; i < templateQuery.size(); i++) {// main loop X
				EmailTemplateQuery templateQueries = (EmailTemplateQuery) templateQuery
						.get(i + 1);
				String queryType = (String) templateQueryType.get(i + 1);
				// templateQueries.initiate(context, session);
				if (queryType.equals("F")) {// if queryType is F
					try {
						// execute the query for 'F'
						queryData = templateQueries.execute(templateID, "F",
								dbConn);
						fromEmpId = templateQueries.getEmployeeId();
					} catch (Exception e) {
						logger.error(e);
					}

					String toBeReplaced = "<#"
							+ String.valueOf(queryData[0][0]) + "#>";
					String replaceWith = "<#NO DATA TO DISPLAY#>";

					try {
						replaceWith = String.valueOf(queryData[1][0]);
					} catch (Exception e) {
						logger.error(e);
					}

					fromMailId = fromMailId.replaceAll(toBeReplaced,
							replaceWith);
				} else if (queryType.equals("T")) {// if queryType is T
					try {
						queryData = templateQueries.execute(templateID, "T",
								dbConn);
					} catch (Exception e) {
						logger.error(e);
					}

					for (int j = 0; j < queryData[0].length; j++) {// loop x
						// for no. of to mail ids query found
						String toBeReplaced = "<#"
								+ String.valueOf(queryData[0][j]) + "#>";

						String replaceWith = "<#NO DATA TO DISPLAY#>";
						try {
							replaceWith = String.valueOf(queryData[1][j]);
						} catch (Exception e) {
							logger.error(e);
						}// end of catch block

						toMailIds = toMailIds.replaceAll(toBeReplaced,
								replaceWith);
					}// end loop x

					// to mail Id's saved in database as a;b;c;d :Hence split
					// them
					newToMailIds = toMailIds.split(";");
				} else if (queryType.equals("D")) {
					// ADDED BY SHASHIKANT
					try {
						StringTokenizer tokens = new StringTokenizer(subj_body,
								"$");
						System.out.println("token length = "
								+ tokens.countTokens());
						String[] str_tokens = new String[tokens.countTokens()];
						int counter = 0;
						while (tokens.hasMoreElements()) {
							String token = tokens.nextToken();
							str_tokens[counter++] = token;
						}
						subj_body = "";
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
								EmailTemplateQuery templateTabDataQuery = (EmailTemplateQuery) templateQuery
										.get(temQueryId);
								// templateTabDataQuery.initiate(context,
								// session);
								queryData = templateTabDataQuery.execute(
										templateID, "D", dbConn);

								System.out.println("str_tokens[j]...:  "
										+ str_tokens[j]);
								// str_tokens[j].replaceAll("#&gt;", "$");
								String[] splitedHeaders = str_tokens[j]
										.split("#");

								String replaceWithTable = "<table border='1' bordercolor='#7F7F7F'  style='border-collapse:collapse;' cellspacing='0'  cellpadding='0' width='750'><tr style='BACKGROUND-COLOR: #c0c0c0' align='center'>";

								/**
								 * For adding the Headers only
								 */
								// int header_counter=0;
								Vector vectHeader = new Vector();
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

										replaceWithTable += "<td><strong><font size='2' face='Arial' >"
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

								for (int k2 = 0; k2 < queryData.length - 1; k2++) {
									replaceWithTable += "<tr align='center'>";
									for (int k = 0; k < vectHeader.size(); k++) {
										for (int l = 0; l < queryData[0].length; l++) {
											if (vectHeader
													.get(k)
													.equals(
															String
																	.valueOf(queryData[0][l]))) {
												String[] splitWidth = ((String) vectHeader
														.get(k)).split("_");
												// width='"+splitWidth[splitWidth.length-1]+"'
												replaceWithTable += "<td><font size='2' face='Arial' >"
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
											+ "<td align='center'><font size='2' face='Arial'><strong>NA.</strong></font></td></tr></table>";
							}
							subj_body += str_tokens[j];
						}

					} catch (Exception e) {
						logger.error(e);
						e.printStackTrace();
					}

				}

				else {
					// for QueryType = Other
					// First, subject and body is concatenated together. Then it
					// is
					// replaced and split
					try {
						queryData = templateQueries.execute(templateID, "",
								dbConn);

						for (int j = 0; j < queryData[0].length; j++) {// loop
							// x
							// for no. of queries found
							String toBeReplaced = "&lt;#"
									+ String.valueOf(queryData[0][j]) + "#&gt;";
							String toBeReplaced_if_sub = "<#"
									+ String.valueOf(queryData[0][j]) + "#>";

							String replaceWith = "&lt;#NO DATA TO DISPLAY#&gt;";
							try {
								replaceWith = String.valueOf(queryData[1][j]);
							} catch (Exception e) {
								logger.error(e);
								e.printStackTrace();
							}// end catch block

							subj_body = subj_body.replaceAll(toBeReplaced,
									replaceWith);// Replaced
							subj_body = subj_body.replaceAll(
									toBeReplaced_if_sub, replaceWith);
						}
					} catch (Exception e) {
						logger.error(e);
					}
				}// end else

				templateQueries.terminate();
			}// end main for X
			newFromMailId[0] = fromMailId; // passing from id as array
			System.out.println("subj_body       :" + subj_body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// end of execute() method

	public void getTemplateQueries() {
		templateQuery = getTemplateQuery();
		for (int i = 0; i < templateQuery.size(); i++) {// loop x
			EmailTemplateQuery q1 = (EmailTemplateQuery) templateQuery
					.get(i + 1);
		}// end of loop x
	}

	public int getTemplateQueries(Connection dbconn) {
		templateQuery = getTemplateQuery(dbconn);
		for (int i = 0; i < templateQuery.size(); i++) {// loop x
			EmailTemplateQuery q1 = (EmailTemplateQuery) templateQuery
					.get(i + 1);
		}// end of loop x

		return templateQuery.size();
	}

	/**
	 * Make a collection of Queries
	 */
	public HashMap<Integer, EmailTemplateQuery> getTemplateQuery() {
		// fire query and get list of all queries
		HashMap<Integer, EmailTemplateQuery> templateQueries = new HashMap<Integer, EmailTemplateQuery>();
		HashMap<Integer, Object> templateQueryTypes = new HashMap<Integer, Object>();

		String query = " SELECT QUERY_ID, QUERY_TYPE FROM HRMS_EMAILTEMPLATE_DTL "
				+ " WHERE HRMS_EMAILTEMPLATE_DTL.TEMPLATE_ID = " + templateID;
		// fire a query to identify query code; 1, 2, 3,4
		Object[][] data = getSqlModel().getSingleResult(query);

		for (int i = 0; i < data.length; i++) {// loop x with total data
			int queryNo = Integer.parseInt(String.valueOf(data[i][0]));
			EmailTemplateQuery templateQuery = new EmailTemplateQuery(queryNo);
			templateQueries.put(queryNo, templateQuery);
			templateQueryTypes.put(queryNo, String.valueOf(data[i][1]));
		}// end loop x

		templateQueryType = templateQueryTypes;
		return templateQueries;
	}// end of getTemplateQuery() method

	/**
	 * Make a collection of Queries
	 */
	public HashMap<Integer, EmailTemplateQuery> getTemplateQuery(
			Connection dbconn) {
		// fire query and get list of all queries
		HashMap<Integer, EmailTemplateQuery> templateQueries = new HashMap<Integer, EmailTemplateQuery>();
		HashMap<Integer, Object> templateQueryTypes = new HashMap<Integer, Object>();

		String query = " SELECT QUERY_ID, QUERY_TYPE FROM HRMS_EMAILTEMPLATE_DTL "
				+ " WHERE HRMS_EMAILTEMPLATE_DTL.TEMPLATE_ID = " + templateID;
		// fire a query to identify query code; 1, 2, 3,4
		Object[][] data = getSqlModel().getSingleResult(query, dbconn);

		for (int i = 0; i < data.length; i++) {// loop x with total data
			int queryNo = Integer.parseInt(String.valueOf(data[i][0]));
			EmailTemplateQuery templateQuery = new EmailTemplateQuery(queryNo);
			templateQueries.put(queryNo, templateQuery);
			templateQueryTypes.put(queryNo, String.valueOf(data[i][1]));
		}// end loop x

		templateQueryType = templateQueryTypes;
		return templateQueries;
	}// end of getTemplateQuery() method

	public EmailTemplateQuery getTemplateQuery(int id) {
		return (EmailTemplateQuery) templateQuery.get(id);
	}

	public EmailTemplateQuery getTemplateQueryType(int id) {
		return (EmailTemplateQuery) templateQuery.get(id);
	}

	public void sendApplicationMail() {
		try {
			sendApplicationMail(false, null, null, null, null);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void sendApplicationMail(Connection dbconn) {
		try {
			sendApplicationMail(false, null, null, null, null, dbconn);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void sendApplicationMail(boolean isCalendar, String fromDate,
			String toDate, String calMessage, String calSubject,
			Connection dbconn) {
		try {
			logger.info("subj_body     " + subj_body);
			final String[] subBodySpilt = subj_body.split("####");
			logger
					.info("subject%%%%%%%%%%%%%%%%%%%     "
							+ subBodySpilt.length);
			SendMailModel model = new SendMailModel();
			// model.initiate(context, session);
			final String message = model.getMassMessage(String
					.valueOf(subBodySpilt[1]), dbconn);
			// model.terminate();
			// call Mail Utility for sending email
			final MailUtility mailModel = new MailUtility();
			// mailModel.initiate(context, session);
			logger.info("fromEmpId         " + fromEmpId);

			for (int i = 0; i < newToMailIds.length; i++) {

				System.out.println("newToMailIds---------------------------"
						+ newToMailIds[i]);
			}

			if (!isCalendar) {
				mailModel.sendMail(newToMailIds, newFromMailId,
						subBodySpilt[0], message, "", fromEmpId, true, dbconn);
			} else {
				mailModel.sendMail(newToMailIds, newFromMailId,
						subBodySpilt[0], message, null, fromEmpId, true,
						isCalendar, fromDate, toDate, calMessage, calSubject,
						dbconn);
			}
			// mailModel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void sendApplicationMail(boolean isCalendar, String fromDate,
			String toDate, String calMessage, String calSubject) {
		try {
			logger.info("subj_body     " + subj_body);
			final String[] subBodySpilt = subj_body.split("####");
			logger
					.info("subject%%%%%%%%%%%%%%%%%%%     "
							+ subBodySpilt.length);
			SendMailModel model = new SendMailModel();
			model.initiate(context, session);
			final String message = model.getMassMessage(String
					.valueOf(subBodySpilt[1]));
			model.terminate();
			// call Mail Utility for sending email
			final MailUtility mailModel = new MailUtility();
			mailModel.initiate(context, session);
			logger.info("fromEmpId         " + fromEmpId);
			if (!isCalendar) {
				mailModel.sendMail(newToMailIds, newFromMailId,
						subBodySpilt[0], message, "", fromEmpId, true);
			} else {
				mailModel.sendMail(newToMailIds, newFromMailId,
						subBodySpilt[0], message, null, fromEmpId, true,
						isCalendar, fromDate, toDate, calMessage, calSubject);
			}
			mailModel.terminate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	/**
	 * THIS METHOD IS USED FOR SENDING MAIL WITH ATTACHMENT
	 * 
	 * @param attachFile
	 */

	public void sendApplMailWithAttachment(String[] attachFile) {
		try {
			logger.info("subj_body     " + subj_body);
			String[] subBodySpilt = subj_body.split("####");
			logger
					.info("subject%%%%%%%%%%%%%%%%%%%     "
							+ subBodySpilt.length);
			SendMailModel model = new SendMailModel();
			model.initiate(context, session);
			String message = model.getMassMessage(String
					.valueOf(subBodySpilt[1]));
			model.terminate();
			// call Mail Utility for sending email

			final MailUtility mailModel1 = new MailUtility();
			mailModel1.initiate(context, session);

			try {
				mailModel1.sendMail(newToMailIds, newFromMailId,
						subBodySpilt[0], message, attachFile, fromEmpId, true);
			} catch (Exception e) {
				// TODO: handle exception
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	/**
	 * METHOD TO SEND MAIL WITH ARRAY[EMAIL ID] PASS THE OBJECT OF EMAIL IDS
	 * 
	 * @param keepInfo
	 */

	public void sendApplicationMailToGroups(Object[][] emailID) {
		sendApplicationMailToGroups(emailID, null);
	}

	public void sendApplicationMailToGroups(Object[][] emailID,
			String[] attachfiles) {
		try {

			logger.info("subj_body   :  " + subj_body);
			String[] subBodySpilt = subj_body.split("####");
			SendMailModel model = new SendMailModel();
			model.initiate(context, session);
			String message = model.getMassMessage(String
					.valueOf(subBodySpilt[1]));
			if (emailID != null && emailID.length > 0) {
				String[] informTo = new String[emailID.length];
				for (int i = 0; i < informTo.length; i++) {
					informTo[i] = String.valueOf(emailID[i][0]);
					System.out.println("TO EMAIL ID :::  "
							+ String.valueOf(emailID[i][0]));
				}
				final MailUtility mailModel = new MailUtility();
				mailModel.initiate(context, session);
				logger.info("fromEmpId         " + fromEmpId);
				try {
					if (attachfiles != null && attachfiles.length > 0) {
						mailModel.sendMail(informTo, newFromMailId,
								subBodySpilt[0], message, attachfiles,
								fromEmpId, true);
					} else {
						mailModel.sendMail(informTo, newFromMailId,
								subBodySpilt[0], message, "", fromEmpId, true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				mailModel.terminate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	/**
	 * This method is used for old process manager alert
	 * @param empID :employee id
	 * @param module :module
	 * @param msgType :message type
	 * @param applnID :application id
	 * @param alertLevel :alert level.
	 */

	public void sendProcessManagerAlert(String empID, String module,
			String msgType, String applnID, String alertLevel) {

		try {
			String[] subBodySpilt = subj_body.split("####");
			ProcessManagerAlertsModel processModel = new ProcessManagerAlertsModel();
			processModel.initiate(context, session);
			processModel.saveAlert(subBodySpilt[0], empID, subBodySpilt[1],
					module, msgType, applnID, alertLevel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is created by Vishwambhar for mypage
	 * 
	 * @param managerId :
	 *            manager id
	 * @param module
	 *            :module name
	 * @param msgType
	 *            :message type A/I Action/Information action for
	 *            manager/alternate approver and information for employee and
	 *            keep informed to manager
	 * @param applnID
	 *            :application id
	 * @param alertLevel
	 *            :alert level
	 * @param link_param
	 *            :link parameters
	 * @param link
	 *            :link name
	 * @param status
	 *            :status actual status like pending.
	 * @param applicantId
	 *            :employee id
	 * @param alternateApproverId
	 *            :alternate approver id.
	 * @param ccId
	 *            :keep informed to ids
	 * @param empId
	 *            :employee id.
	 */

	public void sendProcessManagerAlert(String managerId, String module,
			String msgType, String applnID, String alertLevel,
			String link_param, String link, String status, String applicantId,
			String alternateApproverId, String ccId, String empId,String fromId) {

		try {
			// subBodySpilt[1] Old Code
			String[] subBodySpilt = subj_body.split("####");

			MypageProcessManagerAlertsModel processModel = new MypageProcessManagerAlertsModel();
			processModel.initiate(context, session);
			processModel.saveAlert(subBodySpilt[0], managerId, "", module,
					msgType, applnID, alertLevel, link_param, link, status,
					applicantId, alternateApproverId, ccId, empId,fromId);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public void sendProcessManagerAlert(String managerId, String module,
			String msgType, String applnID, String alertLevel,
			String link_param, String link, String status, String applicantId,
			String alternateApproverId, String ccId, String empId,
			Connection dbconn) {

		try {
			// subBodySpilt[1] Old Code
			String[] subBodySpilt = subj_body.split("####");

			MypageProcessManagerAlertsModel processModel = new MypageProcessManagerAlertsModel();
			//processModel.initiate(context, session);
			processModel.saveAlert(subBodySpilt[0], managerId, subBodySpilt[1],
					module, msgType, applnID, alertLevel, link_param, link,
					status, applicantId, alternateApproverId, ccId, empId,
					dbconn);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * Added By Vishwambhar for mypage draft entry into process manager alert
	 * table.
	 * 
	 * @param empID :
	 *            empCode
	 * @param module :
	 *            module name
	 * @param msgType :
	 *            mesage type A/I Action for manager info foe employee and cc
	 *            manager
	 * @param applnID :
	 *            application id
	 * @param alertLevel :
	 *            alert level
	 * @param link_param :
	 *            link parameters
	 * @param link
	 *            :link name
	 * @param message :
	 *            message
	 * @param status :
	 *            status actual status like pending
	 * @param applicantID
	 *            :applicant id
	 */
	public void sendProcessManagerAlertDraft(String empID, String module,
			String msgType, String applnID, String alertLevel,
			String link_param, String link, String message, String status,
			String applicantID,String fromId) {

		try {

			MypageProcessManagerAlertsModel processModel = new MypageProcessManagerAlertsModel();
			processModel.initiate(context, session);
			processModel.saveAlertForDraft(message, empID, "", module, msgType,
					applnID, alertLevel, link_param, link, status, applicantID,fromId,"","",true);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	/*This method is send a information email to Applicant, Approver and Keep Informed to  */
	
	public void sendProcessManagerAlertWithdraw(String empID, String module,
			String msgType, String applnID, String alertLevel,
			String link_param, String link, String message, String status,
			String applicantID,String fromId,String keepInformedTo,String managerId) {

		try {

			MypageProcessManagerAlertsModel processModel = new MypageProcessManagerAlertsModel();
			processModel.initiate(context, session);
			processModel.saveAlertForDraft(message, empID, "", module, msgType,
					applnID, alertLevel, link_param, link, status, applicantID,fromId,keepInformedTo,managerId,false);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	

	/**
	 * @param templateName
	 *            to set the templateID
	 */
	public void setEmailTemplate(String templateName) {
		try {
			String templateIDSql = " SELECT TEMPLATE_ID FROM HRMS_EMAILTEMPLATE_HDR WHERE TEMPLATE_NAME LIKE '"
					+ templateName + "'";
			Object[][] templateIDQbj = getSqlModel().getSingleResult(
					templateIDSql);
			this.templateID = String.valueOf(templateIDQbj[0][0]);
		} catch (Exception e) {
			logger.info(e);
		}
	}

	/**
	 * @param templateName
	 *            to set the templateID
	 */
	public void setEmailTemplate(String templateName, Connection dbconn) {
		try {
			String templateIDSql = " SELECT TEMPLATE_ID FROM HRMS_EMAILTEMPLATE_HDR WHERE TEMPLATE_NAME LIKE '"
					+ templateName + "'";
			Object[][] templateIDQbj = getSqlModel().getSingleResult(
					templateIDSql, dbconn);
			this.templateID = String.valueOf(templateIDQbj[0][0]);
		} catch (Exception e) {
			logger.info(e);
		}
	}

	// ADDED BY LAKKICHAND
	public void addApprovalLink(HttpServletRequest request,
			String approval_link_param, String reject_link_param) {
		String poolName = (String) session.getAttribute("session_pool");
		String appr_encripted_param = "";
		String rej_encripted_param = "";
		// String appendText="";

		String appr_completeParam = approval_link_param;
		String rej_completeParam = reject_link_param;
		ResourceBundle boundle = ResourceBundle
				.getBundle("org.paradyne.lib.ConnectionModel");
		String clientName = "www";
		try {
			clientName = boundle.getString(poolName);
		} catch (Exception e) {
			e.printStackTrace();
			clientName = "www";
		}
		System.out.println("rej_completeParam    :" + rej_completeParam);
		try {
			poolName = PPEncrypter.encrypt(poolName);

			appr_encripted_param = PPEncrypter.encrypt(appr_completeParam);
			rej_encripted_param = PPEncrypter.encrypt(rej_completeParam);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		String port_80 = String.valueOf(request.getServerPort());
		String link = "";
		// if(port_80.equals("80")){
		link = "http://" + clientName + ".peoplepower.com"
				+ request.getContextPath()
				+ "/AppStudio/OnlineApprRejc_approveRejectApp.action?pool="
				+ poolName + "&parameter=";
		// }
		/*
		 * else if(port_80.equals("443")){ link = "https://" + clientName +
		 * ".peoplepower.com" + request.getContextPath() +
		 * "/AppStudio/OnlineApprRejc_approveRejectApp.action?pool=" + poolName +
		 * "&parameter="; } else{ link = "http://" + request.getServerName() +
		 * ":" + request.getServerPort() + request.getContextPath() +
		 * "/AppStudio/OnlineApprRejc_approveRejectApp.action?pool=" + poolName +
		 * "&parameter="; }
		 */
		// OnlineApproval
		// for live
		// for testing
		/**/
		String approvalLink = link + appr_encripted_param;
		String rejectLink = link + rej_encripted_param;

		System.out.println("approvalLink     :" + approvalLink);
		String instantApprovalText = "----------------------------------------------------------------------------------------------------<br>"
				+ " <font size='2' face='Arial' ><strong>Instant approval processing:</strong><br><br>"

				+ " For instant approval process please click <b><a href='"
				+ approvalLink
				+ "'>Approve</a><b> | <b><a href='"
				+ rejectLink
				+ "'>Reject</a><b>.<br><br>"
				+ " ------------------------------------------------------------------------------------------------------<br><br>"
				+ " To edit  application please click <a href='http://"
				+ clientName
				+ ".the3i.com'>"
				+ clientName
				+ ".the3i.com</a></font>";
		subj_body += instantApprovalText;

	}

	// ADDED BY VISHWAMBHAR
	public void addOnlineLinkForALert(String poolName, String[] link_param,
			String[] link_label) {
		// String poolName ="pool_fort";// (String)
		// request.getSession().getAttribute("session_pool");
		String appr_encripted_param = "";
		String rej_encripted_param = "";
		// String appendText="";

		/*
		 * String appr_completeParam = approval_link_param; String
		 * rej_completeParam = reject_link_param;
		 */
		ResourceBundle boundle = ResourceBundle
				.getBundle("org.paradyne.lib.ConnectionModel");
		String clientName = "www";
		try {
			clientName = boundle.getString(poolName);
		} catch (Exception e) {
			e.printStackTrace();
			clientName = "www";
		}
		// System.out.println("rej_completeParam :" + rej_completeParam);
		String links = "";
		try {
			poolName = PPEncrypter.encrypt(poolName);

			// For Live
			String link = "http://"
					+ clientName
					+ ".peoplepower.com"
					+ "/hrsaas/AppStudio/OnlineApprRejc_approveRejectApp.action?pool="
					+ poolName + "&parameter=";

			// FOR LOCAL TESTING
			/*String link = "http://" + "localhost" + ":" + 9090 + "/pims"
					+ "/AppStudio/OnlineApprRejc_approveRejectApp.action?pool="
					+ poolName + "&parameter=";*/

			for (int i = 0; i < link_label.length; i++) {
				String encripted_param = PPEncrypter.encrypt(link_param[i]);
				links += "<a href='" + link + encripted_param + "'>"
						+ link_label[i] + "</a> | ";
			}
			links = links.substring(0, links.length() - 2);

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

		String instantApprovalText = "----------------------------------------------------------------------------------------------------<br>"
				+ " <font size='2' face='Arial' ><strong>Instant approval processing:</strong><br><br>"

				+ " For instant approval process please click <b>"
				+ links
				+ ".<b><br><br>"
				+ " ------------------------------------------------------------------------------------------------------<br><br>"
				+ " To edit  application please click <a href='http://"
				+ clientName
				+ ".the3i.com'>"
				+ clientName
				+ ".the3i.com</a></font>";
		subj_body += instantApprovalText;

	}

	// ADDED BY LAKKICHAND
	public void addOnlineLink(HttpServletRequest request, String[] link_param,
			String[] link_label) {
		String poolName = (String) session.getAttribute("session_pool");
		String appr_encripted_param = "";
		String rej_encripted_param = "";
		// String appendText="";

		/*
		 * String appr_completeParam = approval_link_param; String
		 * rej_completeParam = reject_link_param;
		 */
		ResourceBundle boundle = ResourceBundle
				.getBundle("org.paradyne.lib.ConnectionModel");
		String clientName = "www";
		try {
			clientName = boundle.getString(poolName);
		} catch (Exception e) {
			e.printStackTrace();
			clientName = "www";
		}
		// System.out.println("rej_completeParam :" + rej_completeParam);
		String links = "";
		try {
			poolName = PPEncrypter.encrypt(poolName);

			// For Live file updation 

			
			/*  String link = "http://" + clientName + ".peoplepower.com" +
			  request.getContextPath() +
			  "/AppStudio/OnlineApprRejc_approveRejectApp.action?pool=" +
			  poolName + "&parameter=";*/
			 

			// FOR LOCAL TESTING
			 String link = "http://" + request.getServerName() + ":"
					+ request.getServerPort() + request.getContextPath()
					+ "/AppStudio/OnlineApprRejc_approveRejectApp.action?pool="
					+ poolName + "&parameter="; 

			for (int i = 0; i < link_label.length; i++) {
				String encripted_param = PPEncrypter.encrypt(link_param[i]);
				links += "<a href='" + link + encripted_param + "'>"
						+ link_label[i] + "</a> | ";
			}
			links = links.substring(0, links.length() - 2);

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

		String instantApprovalText = "----------------------------------------------------------------------------------------------------<br>"
				+ " <font size='2' face='Arial' ><strong>Instant approval processing:</strong><br><br>"

				+ " For instant approval process please click <b>"
				+ links
				+ ".<b><br><br>"
				+ " ------------------------------------------------------------------------------------------------------<br><br>"
				+ " To edit  application please click <a href='http://"
				+ clientName
				+ ".the3i.com'>"
				+ clientName
				+ ".the3i.com</a></font>";
		subj_body += instantApprovalText;

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

	public String[][] getDefaultMailIds() {
		String fromQuery = " SELECT SYSMAIL_EMAIL_ID, SYSMAIL_EMAIL_PASS FROM HRMS_SETTINGS_SYSMAILID ORDER BY SYSMAIL_CODE ";
		Object fromEmp[][] = getSqlModel().getSingleResult(fromQuery);
		String[][] mailIds = new String[fromEmp.length][2];
		for (int i = 0; i < fromEmp.length; i++) {
			mailIds[i][0] = String.valueOf(fromEmp[i][0]);
			mailIds[i][1] = String.valueOf(fromEmp[i][1]);
		}
		return mailIds;
	}

	public void sendApplicationMailToKeepInfo(String[] keepInfo) {
		try {
			logger.info("subj_body   ==================  " + subj_body);
			String[] subBodySpilt = subj_body.split("####");
			SendMailModel model = new SendMailModel();
			model.initiate(context, session);
			String message = model.getMassMessage(String
					.valueOf(subBodySpilt[1]));

			String keepInf = "";
			for (int i = 0; i < keepInfo.length; i++) {
				keepInf += keepInfo[i] + ",";
			}
			keepInf = keepInf.substring(0, keepInf.length() - 1);
			String query = "SELECT ADD_EMAIL AS TO_EMAIL_ID FROM HRMS_EMP_ADDRESS WHERE EMP_ID IN("
					+ keepInf + ") AND ADD_TYPE='P' and  ADD_EMAIL IS NOT NULL";
			Object[][] data = model.getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				String[] informTo = new String[data.length];
				for (int i = 0; i < informTo.length; i++) {
					informTo[i] = String.valueOf(data[i][0]);
				}
				/*
				 * String[][] fromEmail = getDefaultMailIds();
				 * 
				 * MailModel mail_model = new MailModel();
				 * mail_model.initiate(context, session); Object[][] mailBoxData =
				 * mail_model.getServerMailBox(fromEmpId,
				 * String.valueOf(data[0][0])); mail_model.terminate();
				 * 
				 * SendEmail email = new SendEmail();
				 * email.sendMailToKeepInfo(informTo, fromEmail,
				 * subBodySpilt[0], message, mailBoxData);
				 */

				// call Mail Utility for sending email
				final MailUtility mailModel = new MailUtility();
				mailModel.initiate(context, session);
				logger.info("fromEmpId         " + fromEmpId);
				try {
					mailModel.sendMail(informTo, newFromMailId,
							subBodySpilt[0], message, "", fromEmpId, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				mailModel.terminate();

				/*
				 * MailUtility mod = new MailUtility(); mod.initiate(context,
				 * session);
				 * 
				 * mod.sendMail(informTo, mod.getDefaultMailIds(),
				 * subBodySpilt[0], message, "","",true);
				 * 
				 * mod.terminate();
				 */

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void sendApplicationMailToAlternateApprover(
			String alternameApproverCode) {
		try {
			logger.info("subj_body     " + subj_body);
			String[] subBodySpilt = subj_body.split("####");
			SendMailModel model = new SendMailModel();
			model.initiate(context, session);
			String message = model.getMassMessage(String
					.valueOf(subBodySpilt[1]));
			String query = "SELECT ADD_EMAIL AS TO_EMAIL_ID FROM HRMS_EMP_ADDRESS WHERE EMP_ID IN("
					+ alternameApproverCode
					+ ") AND ADD_TYPE='P' and  ADD_EMAIL IS NOT NULL";
			Object[][] data = model.getSqlModel().getSingleResult(query);
			MailModel mail_model = new MailModel();
			mail_model.initiate(context, session);
			Object[][] mailBoxData = mail_model.getServerMailBox(fromEmpId,
					String.valueOf(data[0][0]));
			mail_model.terminate();
			if (data != null && data.length > 0) {
				String[] informTo = new String[data.length];
				for (int i = 0; i < informTo.length; i++) {
					informTo[i] = String.valueOf(data[i][0]);
				}

				// already sending mail to keep info so no need
				/*
				 * String[][] fromEmail = getDefaultMailIds(); SendEmail email =
				 * new SendEmail(); email.sendMailToKeepInfo(informTo,
				 * fromEmail, subBodySpilt[0], message, mailBoxData);
				 */
				MailUtility mod = new MailUtility();
				mod.initiate(context, session);

				mod.sendMail(informTo, mod.getDefaultMailIds(),
						subBodySpilt[0], message, "", "", true);

				mod.terminate();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void sendMailToCCEmailIdsWithAttachment(String[] ccMailIds,
			String[] attachFile) {
		sendMailToCCEmailIdsWithAttachment(ccMailIds, attachFile, false, null,
				null, null, null);
	}

	public void sendMailToCCEmailIdsWithAttachment(String[] ccMailIds,
			String[] attachFile, boolean isCalendar, String startDate,
			String endDate, String calMessage, String calSubject) {
		sendMailToCCEmailIdsWithAttachment(ccMailIds, attachFile, isCalendar,
				startDate, endDate, calMessage, calSubject, null);

	}

	public void sendMailToCCEmailIdsWithAttachment(String[] ccMailIds,
			String[] attachFile, boolean isCalendar, String startDate,
			String endDate, String calMessage, String calSubject,
			String[] bccArray) {
		try {
			logger
					.info("subj_body In sendMailToCCEmailIdsWithAttachment  ==================  "
							+ subj_body);
			/*
			 * for (int i = 0; i < bccArray.length; i++) { logger
			 * .info("bccArray ================== " + bccArray[i]); }
			 */

			String[] subBodySpilt = subj_body.split("####");
			SendMailModel model = new SendMailModel();
			model.initiate(context, session);
			String message = model.getMassMessage(String
					.valueOf(subBodySpilt[1]));

			String[] informTo = new String[ccMailIds.length];
			for (int i = 0; i < informTo.length; i++) {
				informTo[i] = String.valueOf(ccMailIds[i]);
			}
			String[][] fromEmail = getDefaultMailIds();

			final MailUtility mailModel = new MailUtility();
			mailModel.initiate(context, session);

			try {
				mailModel.sendMail(informTo, new String[] { String
						.valueOf(fromEmail[0][0]) }, subBodySpilt[0], message,
						attachFile, fromEmpId, true, isCalendar, startDate,
						endDate, calMessage, calSubject);
				/*
				 * mailModel.sendMail(informTo, fromEmail, subBodySpilt[0],
				 * message, mailBoxData, attachFile,"",false);
				 */
			} catch (Exception e) {

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void sendApplMailWithAttachmentToKeepInf(String[] keepInfo,
			String[] attachfiles) {
		try {
			logger.info("subj_body   ==================  " + subj_body);
			String[] subBodySpilt = subj_body.split("####");
			SendMailModel model = new SendMailModel();
			model.initiate(context, session);
			String message = model.getMassMessage(String
					.valueOf(subBodySpilt[1]));

			String keepInf = "";
			for (int i = 0; i < keepInfo.length; i++) {
				keepInf += keepInfo[i] + ",";
			}
			keepInf = keepInf.substring(0, keepInf.length() - 1);
			String query = "SELECT ADD_EMAIL AS TO_EMAIL_ID FROM HRMS_EMP_ADDRESS WHERE EMP_ID IN("
					+ keepInf + ") AND ADD_TYPE='P' and  ADD_EMAIL IS NOT NULL";
			Object[][] data = model.getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				String[] informTo = new String[data.length];
				for (int i = 0; i < informTo.length; i++) {
					informTo[i] = String.valueOf(data[i][0]);
				}
				String[][] fromEmail = getDefaultMailIds();

				MailModel mail_model = new MailModel();
				mail_model.initiate(context, session);
				Object[][] mailBoxData = mail_model.getServerMailBox(fromEmpId,
						String.valueOf(data[0][0]));
				mail_model.terminate();

				/*
				 * SendEmail mailModel = new SendEmail();
				 * mailModel.initiate(context, session);
				 * mailModel.sendMail(informTo, fromEmail, subBodySpilt[0],
				 * message, mailBoxData, attachFile);
				 */

				final MailUtility mailModel = new MailUtility();
				mailModel.initiate(context, session);
				logger.info("fromEmpId         " + fromEmpId);
				try {
					if (attachfiles != null && attachfiles.length > 0) {
						mailModel.sendMail(informTo, newFromMailId,
								subBodySpilt[0], message, attachfiles,
								fromEmpId, true);
					} else {
						mailModel.sendMail(informTo, newFromMailId,
								subBodySpilt[0], message, "", fromEmpId, true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				/*
				 * email.sendMailToKeepInfo(informTo, fromEmail,
				 * subBodySpilt[0], message, mailBoxData);
				 */

				/*
				 * MailUtility mod = new MailUtility(); mod.initiate(context,
				 * session);
				 * 
				 * mod.sendMail(informTo, mod.getDefaultMailIds(),
				 * subBodySpilt[0], message, "","",true);
				 * 
				 * mod.terminate();
				 */

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	/**
	 * Send mail from scheduler to cc email ids passing connection here
	 * 
	 * @param keepInfo
	 *            Added by Vishwambhar
	 */

	public void sendApplicationMailToKeepInfo(String[] keepInfo,
			Connection dbconn) {
		try {
			logger.info("subj_body   ==================  " + subj_body);
			String[] subBodySpilt = subj_body.split("####");
			SendMailModel model = new SendMailModel();
			// model.initiate(context, session);
			String message = model.getMassMessage(String
					.valueOf(subBodySpilt[1]), dbconn);

			if (keepInfo != null && keepInfo.length > 0) {
				String[] informTo = new String[keepInfo.length];
				for (int i = 0; i < informTo.length; i++) {
					informTo[i] = String.valueOf(keepInfo[i]);
				}
				/*
				 * String[][] fromEmail = getDefaultMailIds();
				 * 
				 * MailModel mail_model = new MailModel();
				 * mail_model.initiate(context, session); Object[][] mailBoxData =
				 * mail_model.getServerMailBox(fromEmpId,
				 * String.valueOf(data[0][0])); mail_model.terminate();
				 * 
				 * SendEmail email = new SendEmail();
				 * email.sendMailToKeepInfo(informTo, fromEmail,
				 * subBodySpilt[0], message, mailBoxData);
				 */

				// call Mail Utility for sending email
				final MailUtility mailModel = new MailUtility();
				// mailModel.initiate(context, session);
				logger.info("fromEmpId         " + fromEmpId);
				try {
					mailModel.sendMail(informTo, newFromMailId,
							subBodySpilt[0], message, "", fromEmpId, true,
							dbconn);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// mailModel.terminate();

				/*
				 * MailUtility mod = new MailUtility(); mod.initiate(context,
				 * session);
				 * 
				 * mod.sendMail(informTo, mod.getDefaultMailIds(),
				 * subBodySpilt[0], message, "","",true);
				 * 
				 * mod.terminate();
				 */

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void sendApplicationMailToKeepInfo(String[] ccMailIds,
			Connection dbconn, boolean delete) {
		try {
			logger.info("subj_body     " + subj_body);

			String[] subBodySpilt = subj_body.split("####");

			SendMailModel model = new SendMailModel();

			// model.initiate(context, session);

			String message = model.getMassMessage(String
					.valueOf(subBodySpilt[1]), dbconn);

			/*
			 * String keepInf = keepInfo;
			 * 
			 * String query = "SELECT ADD_EMAIL AS TO_EMAIL_ID FROM
			 * HRMS_EMP_ADDRESS WHERE EMP_ID IN(" + keepInf + ") AND
			 * ADD_TYPE='P' and ADD_EMAIL IS NOT NULL";
			 * 
			 * Object[][] data = getSqlModel().getSingleResult(query,dbconn);
			 */

			if (ccMailIds != null && ccMailIds.length > 0) {
				String[] informTo = new String[ccMailIds.length];
				for (int i = 0; i < informTo.length; i++) {
					informTo[i] = String.valueOf(ccMailIds[i]);
				}
				String[][] fromEmail = new String[newFromMailId.length][1];
				for (int i = 0; i < fromEmail.length; i++) {
					fromEmail[i][0] = newFromMailId[i];
				}

				MailUtility mod = new MailUtility();
				// mod.initiate(context, session);

				mod.sendMail(informTo, newFromMailId, subBodySpilt[0], message,
						"", fromEmpId, true, dbconn);

				// mod.terminate();
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void sendApplicationMailToKeepInfo(String keepInfo) {
		try {
			logger.info("subj_body     " + subj_body);
			String[] subBodySpilt = subj_body.split("####");
			SendMailModel model = new SendMailModel();
			model.initiate(context, session);
			String message = model.getMassMessage(String
					.valueOf(subBodySpilt[1]));

			String keepInf = keepInfo;
			String query = "SELECT ADD_EMAIL AS TO_EMAIL_ID FROM HRMS_EMP_ADDRESS WHERE EMP_ID IN("
					+ keepInf + ") AND ADD_TYPE='P' and  ADD_EMAIL IS NOT NULL";
			Object[][] data = model.getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				String[] informTo = new String[data.length];
				for (int i = 0; i < informTo.length; i++) {
					informTo[i] = String.valueOf(data[i][0]);
				}
				String[][] fromEmail = new String[newFromMailId.length][1];
				for (int i = 0; i < fromEmail.length; i++) {
					fromEmail[i][0] = newFromMailId[i];
				}

				MailModel mail_model = new MailModel();
				mail_model.initiate(context, session);
				Object[][] mailBoxData = mail_model.getServerMailBox(fromEmpId,
						String.valueOf(data[0][0]));
				mail_model.terminate();

				/*
				 * SendEmail email = new SendEmail();
				 * email.sendMailToKeepInfo(informTo, fromEmail,
				 * subBodySpilt[0], message, mailBoxData);
				 */

				MailUtility mod = new MailUtility();
				mod.initiate(context, session);

				mod.sendMail(informTo, mod.getDefaultMailIds(),
						subBodySpilt[0], message, "", "", true);

				mod.terminate();
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public void sendApplicationMailToKeepInfo(String keepInfo, Connection dbconn) {
		try {
			logger.info("subj_body     " + subj_body);
			String[] subBodySpilt = subj_body.split("####");
			SendMailModel model = new SendMailModel();
			model.initiate(context, session);
			String message = model.getMassMessage(String
					.valueOf(subBodySpilt[1]), dbconn);

			String keepInf = keepInfo;
			
			System.out.println("keepInf---------------"+keepInf);
			String query = "SELECT ADD_EMAIL AS TO_EMAIL_ID FROM HRMS_EMP_ADDRESS WHERE EMP_ID IN("
					+ keepInf + ") AND ADD_TYPE='P' and  ADD_EMAIL IS NOT NULL";
			
			System.out.println("query for keep infio-------------"+query);
			Object[][] data = model.getSqlModel()
					.getSingleResult(query, dbconn);
			if (data != null && data.length > 0) {
				String[] informTo = new String[data.length];
				for (int i = 0; i < informTo.length; i++) {
					informTo[i] = String.valueOf(data[i][0]);
				}
				String[][] fromEmail = new String[newFromMailId.length][1];
				for (int i = 0; i < fromEmail.length; i++) {
					fromEmail[i][0] = newFromMailId[i];
				}

				MailModel mail_model = new MailModel();
				mail_model.initiate(context, session);
				Object[][] mailBoxData = mail_model.getServerMailBox(fromEmpId,
						String.valueOf(data[0][0]), dbconn);
				mail_model.terminate();

				/*
				 * SendEmail email = new SendEmail();
				 * email.sendMailToKeepInfo(informTo, fromEmail,
				 * subBodySpilt[0], message, mailBoxData);
				 */

				MailUtility mod = new MailUtility();
				mod.initiate(context, session);

				mod.sendMail(informTo, newFromMailId, subBodySpilt[0], message,
						"", "", true, dbconn);

				mod.terminate();
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public String[] getNewToMailIds() {
		return newToMailIds;
	}

	public void setNewToMailIds(String[] newToMailIds) {
		this.newToMailIds = newToMailIds;
	}

	 

}