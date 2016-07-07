package org.paradyne.model.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import oracle.jdbc.OracleResultSet;
import oracle.sql.CLOB;
import org.paradyne.bean.common.TemplateMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

public class TemplateModel1 extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	// for writing
	private String inputTextFileName = null;
	private File inputTextFile = null;

	// for reading
	private String outputTextFileName = null;
	private File outputTextFile = null;

	private java.sql.Connection dbConn = null;
	org.paradyne.lib.ConnectionModel connModel = null;
	private String dirName = "";
	HttpSession session = null;
	ServletContext context = null;

	// initialize the parameters in constructor
	/*
	 * public TemplateModel() {
	 * 
	 * inputTextFileName = "c:/work.doc"; inputTextFile = new
	 * File(inputTextFileName);
	 * 
	 * try { if (!inputTextFile.exists()) { throw new IOException("File not
	 * found. " + inputTextFileName); } } catch (Exception e) { // TODO: handle
	 * exception }
	 * 
	 * outputTextFileName = inputTextFileName + ".Streams.out"; }
	 */
	/**
	 * method to save the Template
	 * 
	 * @param tempBean
	 * @return boolean
	 */
	public boolean save(TemplateMaster tempBean) {

		boolean result;
		logger.info("QID" + tempBean.getQId().length());
		logger.info("length" + tempBean.getQId().length());
		if (!checkDuplicate(tempBean)) {
			// create a file and save the content in that file
			createFile(tempBean);
			if (tempBean.getQId().length() > 0) {
				String quString = " INSERT INTO HRMS_TEMPLATE (TEMPLATE_ID,TEMPLATE_DATE,TEMPLATE_CREATED_BY,QUERY_ID,TEMPLATE_NAME) "
						+ " VALUES ((SELECT NVL(MAX(TEMPLATE_ID),0)+1 from HRMS_TEMPLATE) ,SYSDATE,"
						+ tempBean.getUserEmpId()
						+ ","
						+ tempBean.getQId()
						+ ",'" + tempBean.getTempName() + "')";
				logger.info(" krisssssssss........empId============"
						+ tempBean.getUserEmpId() + "QUERY ID=="
						+ tempBean.getQId());
				result = getSqlModel().singleExecute(quString);
				logger.info(quString);

				// save the file in database as blob
				// calling method
				// get the tempId from the table based on Template Name
				String TemplateId = getTemplateIdOnName(tempBean);

				if (result) {
					// writeClobFile(tempBean, TemplateId);
					try {
						writeCLOBStream(tempBean, TemplateId);
					} catch (Exception e) {
						logger.info("In catch block");
					}

				} else {
					return result;
				}

				return result;

			}

			else {
				logger.info("in else case ====means no query Id is there");

				String quString1 = " INSERT INTO HRMS_TEMPLATE (TEMPLATE_ID,TEMPLATE_DATE,TEMPLATE_CREATED_BY,TEMPLATE_NAME) "
						+ " VALUES ((SELECT NVL(MAX(TEMPLATE_ID),0)+1 from HRMS_TEMPLATE) ,"
						+ "SYSDATE,"
						+ tempBean.getUserEmpId()
						+ ",'"
						+ tempBean.getTempName() + "')";
				logger.info(" krisssssssss........empId============"
						+ tempBean.getUserEmpId() + "QUERY ID=="
						+ tempBean.getQId());
				result = getSqlModel().singleExecute(quString1);
				logger.info(quString1);

				String TemplateId1 = getTemplateIdOnName(tempBean);

				if (result) {
					// writeClobFile(tempBean, TemplateId1);
					try {
						writeCLOBStream(tempBean, TemplateId1);
					} catch (Exception e) {
						logger.info("In catch block");
					}

				} else {
					return result;
				}

				return result;

			}
		} else {
			return false;
		}// end of else

	}

	/**
	 * method to update the Template
	 * 
	 * @param tempBean
	 * @return boolean
	 */
	public boolean update(TemplateMaster tempBean) {

		// check the query id
		boolean result;
		logger.info("QID" + tempBean.getQId().length());
		if (!checkDuplicateMod(tempBean)) {

			// create a file and save the content in that file
			createFile(tempBean);

			if (tempBean.getQId().length() > 0) {
				createFile(tempBean);
				String quString = " UPDATE HRMS_TEMPLATE SET TEMPLATE_DATE=SYSDATE ,TEMPLATE_CREATED_BY='"
						+ tempBean.getUserEmpId()
						+ "',QUERY_ID="
						+ tempBean.getQId()
						+ ",TEMPLATE_NAME='"
						+ tempBean.getTempName()
						+ "'  WHERE  TEMPLATE_ID="
						+ tempBean.getTempId();
				result = getSqlModel().singleExecute(quString);
				// here update the template Content

				String TemplateId2 = tempBean.getTempId();

				if (result) {
					// first delete the content from the table then insert
					deleteClobFile(TemplateId2);

					try {
						writeCLOBStream(tempBean, TemplateId2);
					} catch (Exception e) {
						logger.info("In catch block");
					}

					// writeClobFile(tempBean, TemplateId2);
				} else {
					return result;
				}

			} else {
				// no query id
				// here no need to update the query Id
				String quString1 = " UPDATE HRMS_TEMPLATE SET TEMPLATE_DATE=SYSDATE ,TEMPLATE_CREATED_BY='"
						+ tempBean.getUserEmpId()
						+ "',TEMPLATE_NAME='"
						+ tempBean.getTempName()
						+ "'  WHERE  TEMPLATE_ID="
						+ tempBean.getTempId();

				result = getSqlModel().singleExecute(quString1);

				String TemplateId3 = tempBean.getTempId();

				if (result) {
					result = deleteClobFile(TemplateId3);
					if (result) {
						try {
							writeCLOBStream(tempBean, TemplateId3);
						} catch (Exception e) {
							logger.info("In catch block");
						}

						// writeClobFile(tempBean, TemplateId3);
					} else {
						return result;
					}

				} else {
					return result;
				}
			}
			return result;
		} else {
			return false;
		}// end of else

	}

	/**
	 * method to delete the Template
	 * 
	 * @param tempBean
	 * @return boolean
	 */

	public boolean delete(TemplateMaster tempBean) {
		String quString = " DELETE FROM  HRMS_TEMPLATE WHERE  TEMPLATE_ID="
				+ tempBean.getTempId();
		boolean result = getSqlModel().singleExecute(quString);
		if (result) {
			if (deleteClobFile(tempBean.getTempId())) {
				return true;
			} else {
				return false;
			}
		} else {

		}

		return result;
	}

	/**
	 * method to generate the report for template
	 * 
	 * @param tempBean
	 * @param templateContent
	 * @param request
	 * @param response
	 */
	public void report(TemplateMaster tempBean, String templateContent,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("generating report");
		String name = "Template";
		String type = "Txt";
		String title = "Template letter";
		String finalData = templateContent;
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(type,
				title);
		try {

			byte[] buf = finalData.getBytes();

			response.setContentType("application/msword");
			response.getOutputStream().write(buf);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * method to get the parameter labels in jsp dynamically depends on the
	 * 
	 * @param tempBean
	 */

	public void iterate(TemplateMaster tempBean) {
		logger.info("In iterate method of model");
		logger.info("Query Id ------------" + tempBean.getQId());
		String query = "SELECT QUERY_PARA_LABEL FROM HRMS_TEMPLATE_QUERYDTL  WHERE QUERY_PARA_TYPE='F' AND QUERY_ID= "
				+ tempBean.getQId() + " ORDER BY QUERY_PARA_ORDER";
		Object queryData[][] = getSqlModel().getSingleResult(query);

		ArrayList<Object> propList = new ArrayList<Object>();
		logger.info("Content  ----------" + tempBean.getTempContent());
		for (int i = 0; i < queryData.length; i++) {
			TemplateMaster bean = new TemplateMaster();
			bean.setField(String.valueOf(queryData[i][0]));
			propList.add(bean);
		}
		tempBean.setIterate(propList);

	}

	/**
	 * method to set the jsp fields with the data by setting the bean variables
	 * 
	 * @param tempBean
	 */

	public void setData(TemplateMaster tempBean) {
		logger.info("setting Data----------");

		try {
			String que = "SELECT  QUERY_ID FROM HRMS_TEMPLATE WHERE TEMPLATE_ID="
					+ tempBean.getTempId() + " AND QUERY_ID > 0";
			Object queData[][] = getSqlModel().getSingleResult(que);

			if (queData.length > 0 && queData != null) {
				logger.info("setting Query Related -----------");
				String query1 = "SELECT HRMS_TEMPLATE.QUERY_ID,QUERY_NAME FROM HRMS_TEMPLATE    "
						+ "LEFT JOIN HRMS_TEMPLATE_QUERYHDR H1 ON(HRMS_TEMPLATE.QUERY_ID= H1.QUERY_ID) WHERE TEMPLATE_ID="
						+ tempBean.getTempId();
				Object queryData1[][] = getSqlModel().getSingleResult(query1);
				logger.info("Query--------" + query1);
				// tempBean.setTempContent(checkNull(String.valueOf(queryData1[0][0])));

				tempBean.setQId(checkNull(String.valueOf(queryData1[0][0])));
				tempBean.setQName(checkNull(String.valueOf(queryData1[0][1])));

				// get the template content from the database and as file and
				// read that file and create a string and set that string to
				// template content

				String tempData = readCLOBToFileStream(tempBean);
				tempBean.setHtmlcode(tempData);
				logger.info("KRISHNA================" + tempBean.getHtmlcode());
				iterate(tempBean);

			} else {

				// get the template content from the database and as file and
				// read that file and create a string and set that string to
				// template content

				String tempData1 = readCLOBToFileStream(tempBean);
				tempBean.setHtmlcode(tempData1);
				/*
				 * logger.info("setting Related -----------"); String query2 =
				 * "SELECT TEMPLATE_CONTENT FROM HRMS_TEMPLATE WHERE
				 * TEMPLATE_ID=" + tempBean.getTempId(); Object queryData2[][] =
				 * getSqlModel().getSingleResult(query2);
				 * logger.info("Query--------" + query2);
				 * tempBean.setTempContent(checkNull(String
				 * .valueOf(queryData2[0][0])));
				 */
			}
		}

		catch (Exception e) {

			// get the template content from the database and as file and
			// read that file and create a string and set that string to
			// template content

			try {
				String tempData2 = readCLOBToFileStream(tempBean);
				tempBean.setHtmlcode(tempData2);

			} catch (Exception f) {
				// TODO: handle exception
			}

			/*
			 * logger.info("setting Related -----------"); String query2 =
			 * "SELECT TEMPLATE_CONTENT FROM HRMS_TEMPLATE WHERE TEMPLATE_ID=" +
			 * tempBean.getTempId(); Object queryData2[][] =
			 * getSqlModel().getSingleResult(query2);
			 * logger.info("Query--------" + query2); tempBean
			 * .setTempContent(checkNull(String.valueOf(queryData2[0][0])));
			 */

		}

	}

	/**
	 * method to genarate the report
	 * 
	 * @param tempBean
	 * @param response
	 * @param request
	 * @return boolean
	 */
	public boolean reportGenerate(TemplateMaster tempBean,
			HttpServletResponse response, HttpServletRequest request) {
		logger.info("I am in ReportGenerated of Model");

		// get the Template content
		String tempContent = "";
		logger.info("Template Content" + tempContent);

		// read the clob data from the database

		// get the Template content

		logger.info("Template Content" + tempContent);

		try {
			tempContent = checkNull(String
					.valueOf(readCLOBToFileStream(tempBean)));
			logger.info("Template Content" + tempContent);
		} catch (Exception e) {
			// TODO: handle exception
		}
		/*
		 * String contQuery = "SELECT TEMPLATE_CONTENT FROM HRMS_TEMPLATE WHERE
		 * TEMPLATE_ID=" + tempBean.getTempId(); Object[][] content =
		 * getSqlModel().getSingleResult(contQuery);
		 * 
		 * tempContent = checkNull(String.valueOf(content[0][0]));
		 */

		// cpmpare the content for query labels
		logger.info("Template content---" + tempContent);
		logger.info("calling compare method-------------");

		// get the order of the labels
		// check for Query Id

		if (tempBean.getQueryId().length() > 0) {
			HashMap map = getLabels(tempContent, tempBean);
			logger.info("MAP VALUES" + map);
			// got the map contains the label and order <paralabel,order> then

			String dataQuery = "SELECT QUERY_STRING FROM HRMS_TEMPLATE_QUERYHDR WHERE QUERY_ID="
					+ tempBean.getQueryId();
			Object[][] data = getSqlModel().getSingleResult(dataQuery);
			String queryStr = checkNull(String.valueOf(data[0][0]));
			logger.info("dataQuery----" + queryStr);
			// replace the ?s with parameters according to their order
			logger.info("Index of ?============" + queryStr.indexOf("?"));

			String paraVal[] = (String[]) request
					.getParameterValues("paraValue");
			logger.info("krish -----length of the paraVal ===="
					+ paraVal.length);
			logger.info("Param value---------" + paraVal[0]);
			// replace the '?' with '@'
			String newDataQuery = queryStr.replace("?", "@");
			String my = "";

			for (int i = 0; i < paraVal.length; i++) {
				my = newDataQuery.replaceFirst("@", paraVal[i]);
				newDataQuery = my;
			}

			logger.info("actual query string-=======" + my);
			logger.info("Map values" + map);

			// execute the actual Query string

			Object[][] actData = getSqlModel().getSingleResult(my);
			try {
				if (actData.length > 0 && actData != null) {
					Set ref = map.keySet();
					Iterator it = ref.iterator();
					String labelName = "";
					String newLabel = "";
					String actContent = "";
					String order = "";
					logger.info("1111111111");
					while (it.hasNext()) {
						logger.info("222222222");
						labelName = checkNull(String.valueOf(it.next()));
						logger.info("labelName=================" + labelName);
						order = checkNull(String.valueOf(map.get(labelName)));
						logger.info("order=================" + order);
						newLabel = checkNull(String.valueOf(actData[0][Integer
								.parseInt(order) - 1]));
						logger.info("newLabel=================" + newLabel);
						// now replace the template content with the label name
						// values
						actContent = tempContent.replace("&lt;#" + labelName
								+ "#&gt;", newLabel);
						tempContent = actContent;
						// tempContent= tempContent.replaceAll("gt;","");
						logger.info("newLabel=================" + actContent);

					}
					logger.info("ACTUAL CONTENT=====" + tempContent);

				} else {
					String reportCon = "No content available";
					// report(tempBean, reportCon, request, response);
					reportGen(tempBean, reportCon, response, request);
					return true;
				}

			} catch (Exception e) {
				// TODO: handle exception
				String reportContent = "No content available";
				// report(tempBean, reportContent, request, response);

				reportGen(tempBean, reportContent, response, request);
				return true;
			}

		}

		else {
			// no need to check label order etc....
		}

		// call report method to generate report by passing the Template Content
		// and Template Bean
		// report(tempBean, tempContent, request, response);
		reportGen(tempBean, tempContent, response, request);
		return true;
	}

	/**
	 * method to get the dynamic parameters depends on the query Id and
	 * parameter type and to display the parameters dynamically in the jsp.
	 * 
	 * @param bean
	 */

	public void getQueryResult(TemplateMaster bean) {

		try {
			// for checking Query Id
			String queryId = "SELECT QUERY_ID FROM HRMS_TEMPLATE WHERE TEMPLATE_ID="
					+ bean.getTempId() + "AND QUERY_ID>0";
			Object[][] qId = getSqlModel().getSingleResult(queryId);

			if (qId.length > 0 && qId != null) {
				// id is there
				bean.setQueryId(checkNull(String.valueOf(qId[0][0])));

				String query = "SELECT QUERY_PARA_ORDER,QUERY_PARA_LABEL FROM HRMS_TEMPLATE_QUERYDTL WHERE  QUERY_PARA_TYPE='P' AND QUERY_ID="
						+ bean.getQueryId() + "ORDER BY QUERY_PARA_ORDER";
				Object[][] queryDat = getSqlModel().getSingleResult(query);
				ArrayList<Object> list = new ArrayList<Object>();

				for (int i = 0; i < queryDat.length; i++) {
					TemplateMaster bean1 = new TemplateMaster();
					bean1
							.setParaName(checkNull(String
									.valueOf(queryDat[i][1])));

					list.add(bean1);

				}

				bean.setParaList(list);
				logger.info("list size============" + list.size());
				logger.info("ParamName====" + bean.getParaName());

			}

			else {
				// Query Id is not there
				String query = "";

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// comparing the string

	public HashMap getLabels(String tempContent, TemplateMaster bean) {
		// ArrayList list=new ArrayList();

		logger.info("I am in getting labels");
		logger.info("Template string" + tempContent);
		HashMap map = new HashMap(100);
		int i = 0;
		while (true) {
			int found = tempContent.indexOf("&lt;#", i);
			if (found == -1)
				break;
			int start = found + 5; // start of actual name
			int end = tempContent.indexOf("#&gt;", start);
			logger.info("got labels======" + tempContent.substring(start, end));
			map.put(tempContent.substring(start, end), 0);
			i = end + 5; // advance i to start the next iteration
		}

		String orderQr = "SELECT QUERY_PARA_LABEL,QUERY_PARA_ORDER  FROM HRMS_TEMPLATE_QUERYDTL WHERE QUERY_PARA_TYPE='F' AND QUERY_ID="
				+ bean.getQueryId();
		Object[][] orderDat = getSqlModel().getSingleResult(orderQr);

		if (orderDat.length > 0 && orderDat != null) {

			for (int j = 0; j < orderDat.length; j++) {
				if (map.containsKey(String.valueOf(orderDat[j][0]))) {
					map.put(String.valueOf(orderDat[j][0]), String
							.valueOf(orderDat[j][1]));
				}

			}
			logger.info("map values------------======" + map);

		}

		return map;
	}

	// method to get the key

	public static Object getKeyFromValue(HashMap hm, Object value) {
		Set ref = hm.keySet();
		Iterator it = ref.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			if (o.equals(value)) {
				return o;
			}
		}
		return null;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public boolean reportGen(TemplateMaster tempBean, String tempContent,
			HttpServletResponse response, HttpServletRequest request) {

		logger.info("i am in rep Gene");
		String type = "Txt";
		String title = "Template letter";

		try {
			String finaldata = "<html>" + String.valueOf(tempContent)
					+ "</html>";
			org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
					type, title);

			byte[] buf = finaldata.getBytes();

			response.setContentType("application/msword");
			response.getOutputStream().write(buf);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	/*
	 * public void writeClobFile(TemplateMaster tempBean, String TemplateId) { //
	 * save the file in database // get the connection
	 * 
	 * 
	 * try { writeCLOBStream(tempBean, TemplateId); } catch (Exception e) {
	 * logger.info("In catch block"); }
	 *  }
	 */
	
	
	
	//=======================================================================================
	public void writeCLOBStream(TemplateMaster tempBean, String TemplateId)
	throws IOException, SQLException {
		
		
		
		
		
		
	}
	//=======================================================================================
	
	
	
	
	/**
	 * Method used to write text data contained in a file to an Oracle CLOB
	 * column. The method used to write the data to the CLOB uses Streams. This
	 * is one of two types of methods used to write text data to a CLOB column.
	 * The other method uses the putChars() method.
	 * 
	 * @throws java.io.IOException
	 * @throws java.sql.SQLException
	 */
	public void writeCLOBStream1(TemplateMaster tempBean, String TemplateId)
			throws IOException, SQLException {
		logger.info("in writeCLOBStream");

		// inputTextFileName = getFileName();

		inputTextFileName = getDirName() + "//" + tempBean.getTempName()
				+ ".doc";
		inputTextFile = new File(inputTextFileName);
		logger.info("INPUT FILE NAME" + inputTextFileName);
		FileInputStream inputFileInputStream = null;
		OutputStream clobOutputStream = null;
		String sqlText = null;
		Statement stmt = null;
		ResultSet rset = null;
		CLOB xmlDocument = null;
		int bufferSize;
		byte[] byteBuffer;
		int bytesRead = 0;
		int bytesWritten = 0;
		int totBytesRead = 0;
		int totBytesWritten = 0;
		dbConn = getSqlModel().connection();
		try {

			stmt = dbConn.createStatement();
			logger.info("Input file Name" + inputTextFileName);
			inputTextFile = new File(inputTextFileName);
			inputFileInputStream = new FileInputStream(inputTextFile);

			sqlText = "UPDATE HRMS_TEMPLATE SET TEMPLATE_DOC_NAME='"
					+ inputTextFile.getName()
					+ "',TEMPLATE_DOCUMENT=EMPTY_CLOB()WHERE TEMPLATE_ID = "
					+ TemplateId;

			/*
			 * sqlText = "INSERT INTO HRMS_TEMPLATE (TEMPLATE_DOC_NAME,
			 * TEMPLATE_DOCUMENT) " + " VALUES("+"'" + inputTextFile.getName() +
			 * "', EMPTY_CLOB()) WHERE TEMPLATE_ID = " + TemplateId;
			 */
			stmt.executeUpdate(sqlText);

			sqlText = "SELECT TEMPLATE_DOCUMENT FROM   HRMS_TEMPLATE "
					+ "WHERE  TEMPLATE_ID = " + TemplateId + "FOR UPDATE";
			rset = stmt.executeQuery(sqlText);
			rset.next();
			xmlDocument = ((OracleResultSet) rset).getCLOB("TEMPLATE_DOCUMENT");

			bufferSize = xmlDocument.getBufferSize();

			// Notice that we are using an array of bytes as opposed to an array
			// of characters. This is required since we will be streaming the
			// content (to either a CLOB or BLOB) as a stream of bytes using
			// using an OutputStream Object. This requires that a byte array to
			// be used to temporarily store the contents that will be sent to
			// the LOB. Note that they use of the byte array can be used even
			// when reading contents from an ASCII text file that will be sent
			// to a CLOB.
			byteBuffer = new byte[bufferSize];

			clobOutputStream = xmlDocument.getAsciiOutputStream();

			while ((bytesRead = inputFileInputStream.read(byteBuffer)) != -1) {

				// After reading a buffer from the text file, write the contents
				// of the buffer to the output stream using the write()
				// method.
				clobOutputStream.write(byteBuffer, 0, bytesRead);

				totBytesRead += bytesRead;
				totBytesWritten += bytesRead;

			}

			// Keep in mind that we still have the stream open. Once the stream
			// gets open, you cannot perform any other database operations
			// until that stream has been closed. This even includes a COMMIT
			// statement. It is possible to loose data from the stream if this
			// rule is not followed. If you were to attempt to put the COMMIT in
			// place before closing the stream, Oracle will raise an
			// "ORA-22990: LOB locators cannot span transactions" error.

			inputFileInputStream.close();
			clobOutputStream.close();

			rset.close();
			stmt.close();
			dbConn.commit();
			System.out
					.println("==========================================================\n"
							+ "  OUTPUT STREAMS METHOD\n"
							+ "==========================================================\n"
							+ "Wrote file "
							+ inputTextFile.getName()
							+ " to CLOB column.\n"
							+ totBytesRead
							+ " bytes read.\n"
							+ totBytesWritten
							+ " bytes written.\n");

		} catch (IOException e) {
			System.out
					.println("Caught I/O Exception: (Write CLOB value - Stream Method).");
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			System.out
					.println("Caught SQL Exception: (Write CLOB value - Stream Method).");
			System.out.println("SQL:\n" + sqlText);
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * Method used to write the contents (data) from an Oracle CLOB column to an
	 * O/S file. This method uses one of two ways to get data from the CLOB
	 * column - namely using Streams. The other way to read data from an Oracle
	 * CLOB column is to use getChars() method.
	 * 
	 * @throws java.io.IOException
	 * @throws java.sql.SQLException
	 */

	public String readCLOBToFileStream(TemplateMaster tempBean)
			throws IOException, SQLException {

		logger.info("IN CLOB+++++++++++++++++++++++++++");
		String sqlText = "SELECT TEMPLATE_DOCUMENT FROM  HRMS_TEMPLATE WHERE  TEMPLATE_ID ="+tempBean.getTempId();
		Object[][] data = getSqlModel().getSingleResult(sqlText);

		logger.info("STRING +++++++++++++++++++++++++++++MESSAGE+++++++++===="
				+ String.valueOf(data[0][0]));

		return String.valueOf(data[0][0]);
	}

	
	
	
	
	public String readCLOBToFileStream1(TemplateMaster tempBean)
			throws IOException, SQLException {

		logger.info("I am in readCLOBToFileStream");
		String dirPath = getDirName();
		logger.info("DIRECTORY STRUCTURE=============" + dirName);

		outputTextFileName = tempBean.getTempName() + ".Streams.out";
		logger.info("outputTextFileName==========" + outputTextFileName);

		FileOutputStream outputFileOutputStream = null;
		InputStream clobInputStream = null;
		String sqlText = null;
		Statement stmt = null;
		ResultSet rset = null;
		CLOB xmlDocument = null;
		int chunkSize;
		byte[] textBuffer;
		int bytesRead = 0;
		int bytesWritten = 0;
		int totBytesRead = 0;
		int totBytesWritten = 0;
		String result = "";

		try {
			dbConn = getSqlModel().connection();
			//dbConn.setAutoCommit(false);
			File dir = new File(dirPath);
			dir.mkdirs();
			outputTextFile = new File(dir, outputTextFileName);
			if (!outputTextFile.exists()) {
				outputTextFile.createNewFile();
			}

			logger.info("outputTextFileName=======" + outputTextFileName);

			outputFileOutputStream = new FileOutputStream(outputTextFile);
			stmt = dbConn.createStatement();
			sqlText = "SELECT TEMPLATE_DOCUMENT FROM  HRMS_TEMPLATE WHERE  TEMPLATE_ID ="
					+ tempBean.getTempId() + "FOR UPDATE";
			rset = stmt.executeQuery(sqlText);
			rset.next();
			xmlDocument = ((OracleResultSet) rset).getCLOB("TEMPLATE_DOCUMENT");

			// Will use a Java InputStream object to read data from a CLOB (can
			// also be used for a BLOB) object. In this example, we will use an
			// InputStream to read ASCII characters from a CLOB.
			clobInputStream = xmlDocument.getAsciiStream();

			chunkSize = xmlDocument.getChunkSize();
			textBuffer = new byte[chunkSize];

			while ((bytesRead = clobInputStream.read(textBuffer)) != -1) {

				// Loop through while reading a chunk of data from the CLOB
				// column using an InputStream. This data will be stored
				// in a temporary buffer that will be written to disk.
				outputFileOutputStream.write(textBuffer, 0, bytesRead);

				totBytesRead += bytesRead;
				totBytesWritten += bytesRead;

			}

			outputFileOutputStream.close();
			clobInputStream.close();

			rset.close();
			stmt.close();
			dbConn.commit();
			// ======================================================================

			logger.info("path of the file " + outputTextFile);
			FileReader fr = new FileReader(outputTextFile);

			BufferedReader br = new BufferedReader(fr);
			String buffer;
			while ((buffer = br.readLine()) != null) {
				result = result + buffer;
			}

			logger.info("Reading aaaaaaaaaaaaa======= result========--"
					+ result);

			System.out
					.println("==========================================================\n"
							+ "  INPUT STREAMS METHOD\n"
							+ "==========================================================\n"
							+ "Wrote CLOB column data to file "
							+ outputTextFile.getName()
							+ ".\n"
							+ totBytesRead
							+ " characters read.\n"
							+ totBytesWritten
							+ " characters written.\n");

		} catch (IOException e) {
			System.out
					.println("Caught I/O Exception: (Write CLOB value to file - Streams Method).");
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			System.out
					.println("Caught SQL Exception: (Write CLOB value to file - Streams Method).");
			System.out.println("SQL:\n" + sqlText);
			e.printStackTrace();
			throw e;
		}

		/*  finally {
			  logger.info("IN FINNALLY-------------"+ result);
			connModel.freeConnection(dbConn);
			
			
		}*/

		return result;

	}

	/**
	 * @return the dirName
	 */
	public String getDirName() {
		return dirName;
	}

	/**
	 * @param dirName
	 *            the dirName to set
	 */
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	/*
	 * method name : checkDuplicate purpose : for checking duplicate entry of
	 * record during insertion return type : boolean parameter : bean
	 */

	public boolean checkDuplicate(TemplateMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_TEMPLATE WHERE UPPER(TEMPLATE_NAME) LIKE '"
				+ bean.getTempName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/*
	 * method name : checkDuplicateMod purpose : for checking duplicate entry of
	 * record during modification return type : boolean parameter : bean
	 */

	public boolean checkDuplicateMod(TemplateMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_TEMPLATE WHERE UPPER(TEMPLATE_NAME) LIKE '"
				+ bean.getTempName().trim().toUpperCase()
				+ "' AND TEMPLATE_ID not in(" + bean.getTempId() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/**
	 * method to get the Template Id based on the Template Name
	 * 
	 * @param tempBean
	 * @return string
	 */
	public String getTemplateIdOnName(TemplateMaster tempBean)

	{
		String tempIdQuery = "SELECT TEMPLATE_ID FROM HRMS_TEMPLATE WHERE TEMPLATE_NAME='"
				+ tempBean.getTempName() + "'";
		Object[][] tempIdData = getSqlModel().getSingleResult(tempIdQuery);
		String tempId = "";
		if (tempIdData != null && tempIdData.length > 0) {
			tempId = String.valueOf(tempIdData[0][0]);
		}// end of if
		return tempId;
	}

	public boolean deleteClobFile(String TempId) {
		String query = "DELETE FROM HRMS_TEST WHERE ID=" + TempId;
		return getSqlModel().singleExecute(query);
	}

	public void createFile(TemplateMaster tempBean) {

		String path = getDirName();
		logger.info("Path of the file" + path);
		String fileName = tempBean.getTempName() + ".doc";
		setDirName(path);
		PrintWriter output;
		try {
			output = null;
			// create the directory
			File dir = new File(path);
			dir.mkdirs();
			File file = new File(dir, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			output = new PrintWriter(new FileWriter(file));
			output.write(tempBean.getHtmlcode());
			output.println();
			output.close();
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Exception" + e);
		}

	}

}
