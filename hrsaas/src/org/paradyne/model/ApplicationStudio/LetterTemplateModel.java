/**
 * 
 */
package org.paradyne.model.ApplicationStudio;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import oracle.jdbc.driver.OracleResultSet;
import oracle.sql.CLOB;

import org.paradyne.bean.ApplicationStudio.LetterTemplate;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;

/**
 * @author Reeba Joseph
 * 
 */
public class LetterTemplateModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LetterTemplateModel.class);

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

	/**
	 * @return the inputTextFileName
	 */
	public String getInputTextFileName() {
		return inputTextFileName;
	}

	/**
	 * @param inputTextFileName
	 *            the inputTextFileName to set
	 */
	public void setInputTextFileName(String inputTextFileName) {
		this.inputTextFileName = inputTextFileName;
	}

	/**
	 * @return the inputTextFile
	 */
	public File getInputTextFile() {
		return inputTextFile;
	}

	/**
	 * @param inputTextFile
	 *            the inputTextFile to set
	 */
	public void setInputTextFile(File inputTextFile) {
		this.inputTextFile = inputTextFile;
	}

	/**
	 * @return the outputTextFileName
	 */
	public String getOutputTextFileName() {
		return outputTextFileName;
	}

	/**
	 * @param outputTextFileName
	 *            the outputTextFileName to set
	 */
	public void setOutputTextFileName(String outputTextFileName) {
		this.outputTextFileName = outputTextFileName;
	}

	/**
	 * @return the outputTextFile
	 */
	public File getOutputTextFile() {
		return outputTextFile;
	}

	/**
	 * @param outputTextFile
	 *            the outputTextFile to set
	 */
	public void setOutputTextFile(File outputTextFile) {
		this.outputTextFile = outputTextFile;
	}

	/**
	 * @return the dbConn
	 */
	public java.sql.Connection getDbConn() {
		return dbConn;
	}

	/**
	 * @param dbConn
	 *            the dbConn to set
	 */
	public void setDbConn(java.sql.Connection dbConn) {
		this.dbConn = dbConn;
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

	public String checkNullZero(String result) {
		if (result == null || result.equals("null")) {
			return "0";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public void addItem(LetterTemplate letterTemplate, String[] srNo,
			String[] queryName, int check, String[] queryType,
			String[] noOfRows, String[] noOfColumn, String[] colsWidth,String[] queryNameItt) {

		// TODO Auto-generated method stub
		ArrayList<Object> tableList = new ArrayList<Object>();
		ArrayList<Object> salaryList = new ArrayList<Object>();
		logger.info("in new add");
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				LetterTemplate bean = new LetterTemplate();
				logger.info("_________________" + queryName[i]);
				bean.setSrNo(String.valueOf(i + 1));
				bean.setQueryName(queryName[i]);
				bean.setQueryType(queryType[i]);
				bean.setNoOfRowsItt(checkNullZero(noOfRows[i]));
				bean.setNoOfColumnItt(checkNullZero(noOfColumn[i]));
				bean.setColsWidthItt(checkNullZero(colsWidth[i]));
				bean.setQueryNameItt(queryNameItt[i]);
				tableList.add(bean);
				/**
				 * CODING FOR NO OF ROW AND NO OF COLUMN
				 */
				logger.info("ueryType[i]_________:::   " + queryType[i]);
				if (queryType[i].equals("Salary")) {
					TreeMap tempMap = new TreeMap();
					LetterTemplate salBean = new LetterTemplate();
					int row = Integer.parseInt(checkNullZero(noOfRows[i]));
					int col = Integer.parseInt(checkNullZero(noOfColumn[i]));
					if (row == 0 || col == 0) {
						tempMap.put("DATA NOT AVAILABLE", "DATA NOT AVAILABLE");
					}

					for (int j = 0; j < row; j++) {
						for (int k = 0; k < col; k++) {
							tempMap.put(bean.getSrNo() + "_ROW" + (j + 1)
									+ "_COL" + (k + 1), bean.getSrNo() + "_ROW"
									+ (j + 1) + "_COL" + (k + 1));
						}
					}
					salBean.setQueryNameItt(queryNameItt[i]);
					salBean.setTemplateOptSalaryMap(tempMap);
					salaryList.add(salBean);

				}
				
				

			}
		}
		if (check == 1) {
			logger.info("==========" + letterTemplate.getQName());
			LetterTemplate bean = new LetterTemplate();
			bean.setSrNo(String.valueOf(tableList.size() + 1));
			bean.setQueryName(letterTemplate.getQName());
			bean.setNoOfRowsItt(checkNullZero(letterTemplate.getNoOfRows()));
			bean.setNoOfColumnItt(checkNullZero(letterTemplate.getNoOfColumn()));
			bean.setColsWidthItt(checkNullZero(letterTemplate.getCols_width()));
			bean.setQueryNameItt(letterTemplate.getQueryNameTab());
			if (letterTemplate.getQType().equals("S")) {
				bean.setQueryType("Salary");
			} else if (letterTemplate.getQType().equals("O")) {
				bean.setQueryType("Other");
			}else if (letterTemplate.getQType().equals("D")) {
				bean.setQueryType("Table Data");
			} 
			else
				bean.setQueryType("");
			tableList.add(bean);

			if (letterTemplate.getQType().equals("S")) {
				logger.info("letterTemplate.getQType()=====*********====="
						+ letterTemplate.getQType());
				TreeMap tempMap = new TreeMap();
				LetterTemplate salBean = new LetterTemplate();
				int row = Integer.parseInt(checkNullZero(letterTemplate
						.getNoOfRows()));
				int col = Integer.parseInt(checkNullZero(letterTemplate
						.getNoOfColumn()));
				if (row == 0 || col == 0) {
					tempMap.put("DATA NOT AVAILABLE", "DATA NOT AVAILABLE");
				}
				for (int j = 0; j < row; j++) {
					for (int k = 0; k < col; k++) {
						tempMap.put(bean.getSrNo() + "_ROW" + (j + 1) + "_COL"
								+ (k + 1), bean.getSrNo() + "_ROW" + (j + 1)
								+ "_COL" + (k + 1));
					}
				}
				salBean.setQueryNameItt(letterTemplate.getQueryNameTab());
				salBean.setTemplateOptSalaryMap(tempMap);
				salaryList.add(salBean);

			}
			
		

		} else if (check == 0) {
			tableList.remove(Integer.parseInt(letterTemplate.getSubcode()) - 1);
		}
		logger.info("length************" + tableList.size());
		if (tableList.size() != 0)
			letterTemplate.setTableLength("1");
		else
			letterTemplate.setTableLength("0");
		letterTemplate.setList(tableList);
		letterTemplate.setSalaryItt(salaryList);

	}

	
	
	
	public void moditem(LetterTemplate letterTemplate, String[] srNo,
			String[] queryName, int check, String[] queryType,
			String[] noOfRows, String[] noOfColumn,String[] colsWidth, String[] queryNameItt) {
		// TODO Auto-generated method stub
		ArrayList<Object> tableList = new ArrayList<Object>();
		ArrayList<Object> salaryList = new ArrayList<Object>();
		logger.info("in  moditem");
		if (srNo != null) {

			for (int i = 0; i < srNo.length; i++) {
				LetterTemplate bean = new LetterTemplate();
				logger.info("hidden edit==" + letterTemplate.getHiddenEdit());

				if (i == Integer.parseInt(letterTemplate.getHiddenEdit()) - 1) {
					logger.info("loop no$$$$$$$$$$$$$$$$$$" + i);
					bean.setSrNo(String.valueOf(i + 1));
					bean.setQueryName(letterTemplate.getQName());
					bean.setNoOfRowsItt(checkNullZero(letterTemplate
							.getNoOfRows()));
					bean.setNoOfColumnItt(checkNullZero(letterTemplate
							.getNoOfColumn()));
					bean.setColsWidthItt(checkNullZero(letterTemplate.getCols_width()));
					bean.setQueryNameItt(letterTemplate.getQueryNameTab());
					if (letterTemplate.getQType().equals("S")) {
						bean.setQueryType("Salary");
					} else if (letterTemplate.getQType().equals("O")) {
						bean.setQueryType("Other");
					}else if(letterTemplate.getQType().equals("D")){ 
						bean.setQueryType("Table Data");
					}else
						bean.setQueryType("");

					if (letterTemplate.getQType().equals("S")) {
						logger
								.info("letterTemplate.getQType()=====*********====="
										+ letterTemplate.getQType());
						TreeMap tempMap = new TreeMap();
						LetterTemplate salBean = new LetterTemplate();
						int row = Integer.parseInt(checkNullZero(letterTemplate
								.getNoOfRows()));
						int col = Integer.parseInt(checkNullZero(letterTemplate
								.getNoOfColumn()));
						if (row == 0 || col == 0) {
							tempMap.put("DATA NOT AVAILABLE",
									"DATA NOT AVAILABLE");
						}
						for (int j = 0; j < row; j++) {
							for (int k = 0; k < col; k++) {
								tempMap.put(bean.getSrNo() + "_ROW" + (j + 1)
										+ "_COL" + (k + 1), bean.getSrNo()
										+ "ROW" + (j + 1) + "_COL" + (k + 1));
							}
						}
						salBean.setQueryNameItt(letterTemplate
								.getQueryNameTab());
						salBean.setTemplateOptSalaryMap(tempMap);
						salaryList.add(salBean);

					}

				} else {
					bean.setSrNo(String.valueOf(i + 1));
					bean.setQueryName(queryName[i]);
					bean.setQueryType(queryType[i]);
					bean.setNoOfRowsItt(checkNullZero(noOfRows[i]));
					bean.setNoOfColumnItt(checkNullZero(noOfColumn[i]));
					bean.setColsWidthItt(checkNullZero(colsWidth[i]));
					bean.setQueryNameItt(queryNameItt[i]);
					if (queryType[i].equals("Salary")) {
						TreeMap tempMap = new TreeMap();
						tempMap.put("SELECT", "SELECT");
						LetterTemplate salBean = new LetterTemplate();
						int row = Integer.parseInt(checkNullZero(noOfRows[i]));
						int col = Integer
								.parseInt(checkNullZero(noOfColumn[i]));
						if (row == 0 || col == 0) {
							tempMap.put("DATA NOT AVAILABLE",
									"DATA NOT AVAILABLE");
						}
						for (int j = 0; j < row; j++) {
							for (int k = 0; k < col; k++) {
								tempMap.put(bean.getSrNo() + "_ROW" + (j + 1)
										+ "_COL" + (k + 1), bean.getSrNo()
										+ "ROW" + (j + 1) + "_COL" + (k + 1));
							}
						}
						salBean.setQueryNameItt(queryNameItt[i]);
						salBean.setTemplateOptSalaryMap(tempMap);
						salaryList.add(salBean);

					}

				}
				tableList.add(bean);
			}
		}
		logger.info("length************" + tableList.size());
		if (tableList.size() != 0)
			letterTemplate.setTableLength("1");
		else
			letterTemplate.setTableLength("0");
		letterTemplate.setList(tableList);
		letterTemplate.setSalaryItt(salaryList);

	}



	public String createTempList(LetterTemplate letterTemplate, String[] srNo,
			String[] queryName, String[] field, String[] queryType,
			String[] noOfRows, String[] noOfColumn,String[] colsWidth) {
		// TODO Auto-generated method stub
		/* To add paramaters to the template. Setting the labels from the query */

		String listQuery = letterTemplate.getQName();
		logger.info("Queries ----:" + listQuery);
		StringTokenizer st = new StringTokenizer(listQuery);

		// get how many tokens inside st object
		System.out.println("tokens count: " + st.countTokens());

		String[] splitedQuery = new String[st.countTokens()];
		// iterate st object to get more tokens from it
		int count = 0;
		while (st.hasMoreElements()) {
			splitedQuery[count] = st.nextElement().toString();
			//System.out.println("token = " + splitedQuery[count]);
			count++;
		}
		ArrayList list = new ArrayList();
		TreeMap tempMap = new TreeMap();
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
		logger.info("Content ----------" + letterTemplate.getTempContent());
		if (field != null) {
			for (int i = 0; i < field.length; i++) {
				LetterTemplate bean = new LetterTemplate();
				bean.setField(String.valueOf(field[i]));
				tempList.add(bean);
				tempMap.put(String.valueOf(field[i]), String.valueOf(field[i]));
				logger.info("String.valueOf(field[i] : "+ String.valueOf(field[i]));
			}
		}
		for (int i = 0; i < fields.length; i++) {
			LetterTemplate bean = new LetterTemplate();
			bean.setField(String.valueOf(fields[i]));
			tempList.add(bean);
			tempMap.put(String.valueOf(fields[i]), String.valueOf(fields[i]));
			logger.info("String.valueOf(field[i] : "
					+ String.valueOf(fields[i]));
		}

		letterTemplate.setIterate(tempList);
		letterTemplate.setTemplateOptMap(tempMap);
		letterTemplate.setTemplateOptFlag("true");

		return "Query Added";

	}

	public boolean delDtl(LetterTemplate letterTemplate, String[] code,
			String[] queryName) {
		// TODO Auto-generated method stub
		try {
			ArrayList<Object> list = new ArrayList<Object>();

			if (queryName != null) {
				for (int i = 0; i < queryName.length; i++) {
					LetterTemplate bean = new LetterTemplate();
					if ((String.valueOf(code[i]).equals(""))) {
						bean.setQueryName(queryName[i]);
						list.add(bean);

					}

				}
			}
			letterTemplate.setList(list);
			// Object[][] dataDTL = getSqlModel().getSingleResult(queryDTL);
			ArrayList beanList = letterTemplate.getList();
			Object[][] dataDTL = new Object[beanList.size()][1];
			int counter = 0;
			for (Iterator iterator = beanList.iterator(); iterator.hasNext();) {
				LetterTemplate name = (LetterTemplate) iterator.next();
				dataDTL[counter][0] = name.getQueryName();

			}
			ArrayList<Object> listQuery = new ArrayList<Object>();
			if (dataDTL != null && dataDTL.length > 0) {
				for (int i = 0; i < dataDTL.length; i++) {

					LetterTemplate bean = new LetterTemplate();
					bean.setQueryName(String.valueOf(dataDTL[i][0]));
					bean.setSrNo(String.valueOf(i + 1));
					listQuery.add(bean);

				}
				letterTemplate.setList(listQuery);
			}
			if (listQuery.size() == 0) {
				letterTemplate.setTableLength("0");
			} else {
				letterTemplate.setTableLength("1");
			}
			// Object listData[][]=new Object[data.length][1];
			ArrayList<String[]> tempList = new ArrayList<String[]>();
			ArrayList<Object> iterList = new ArrayList<Object>();
			TreeMap tempMap = new TreeMap();
			if (dataDTL != null && dataDTL.length > 0) {
				for (int i = 0; i < dataDTL.length; i++) {
					/*
					 * logger.info("Queries-------------------set---" +
					 * String.valueOf(data[i][2])); Object listData[][] =
					 * getSqlModel().getSingleResultWithCol(
					 * String.valueOf(data[i][2]));
					 */

					StringTokenizer st = new StringTokenizer(String
							.valueOf(dataDTL[i][0]));

					// get how many tokens inside st object
					System.out.println("tokens count: " + st.countTokens());

					String[] splitedQuery = new String[st.countTokens()];
					// iterate st object to get more tokens from it
					int count = 0;
					while (st.hasMoreElements()) {
						splitedQuery[count] = st.nextElement().toString();
						System.out.println("token = " + splitedQuery[count]);
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
						System.out.println("list..." + list1.get(k));
						fields[k] = (String) list1.get(k);

					}

					tempList.add(fields);
				}
			}
			for (Iterator iterator = tempList.iterator(); iterator.hasNext();) {
				String[] dataList = (String[]) iterator.next();
				for (int i = 0; i < dataList.length; i++) {
					LetterTemplate bean1 = new LetterTemplate();
					bean1.setField(String.valueOf(dataList[i]));
					iterList.add(bean1);
					tempMap.put(String.valueOf(dataList[i]), String
							.valueOf(dataList[i]));
				}
			}
			
			letterTemplate.setIterate(iterList);
			letterTemplate.setTemplateOptMap(tempMap);
			letterTemplate.setTemplateOptFlag("true");
		}

		catch (Exception e) {
			e.printStackTrace();

			// TODO: handle exception
		}
		return true;
	}

	public boolean save(LetterTemplate letterTemplate, String[] srNo,
			String[] queryName, String[] queryType, String[] noOfRows,
			String[] noOfColumn,String[] colsWidth ,String[] queryNameItt) {
		// TODO Auto-generated method stub
		boolean result = false;

		try {

			boolean res = checkDuplicate(letterTemplate);
			if (!checkDuplicate(letterTemplate)) {

				Object[][] insertdata = new Object[1][3];
				insertdata[0][0] = letterTemplate.getTempName();
				insertdata[0][1] = letterTemplate.getHtmlcode();
				insertdata[0][2] = letterTemplate.getTempType();

				String hdrQuery = " INSERT INTO HRMS_LETTERTEMPLATE_HDR(TEMPLATE_ID,TEMPLATE_DATE,TEMPLATE_NAME,TEMPLATE_BODY,TEMPLATE_TYPE) "
						+ " VALUES ((SELECT NVL(MAX(TEMPLATE_ID),0)+1 from HRMS_LETTERTEMPLATE_HDR) ,"
						+ " SYSDATE,?,?,? )";

				result = getSqlModel().singleExecute(hdrQuery, insertdata);

				if (result) {
					String query = " SELECT MAX(TEMPLATE_ID) FROM HRMS_LETTERTEMPLATE_HDR ";
					Object maxcode[][] = getSqlModel().getSingleResult(query);
					/*try {
						String sqlText = "SELECT TEMPLATE_BODY  FROM HRMS_LETTERTEMPLATE_HDR WHERE  TEMPLATE_ID = "
								+ String.valueOf(maxcode[0][0])
								+ " FOR UPDATE ";
						SqlModel model = getSqlModel();
						Connection conn = model.connection();
						Statement stmt = conn.createStatement();
						ResultSet rset = stmt.executeQuery(sqlText);
						rset.next();
						CLOB xmlDoent = ((OracleResultSet) rset)
								.getCLOB("TEMPLATE_BODY");
						xmlDoent.setString(1, letterTemplate.getHtmlcode());

						conn.commit();
						rset.close();
						stmt.close();
						model.freeConnection(conn);

					} catch (Exception e) {
						logger.error("Error  while inserting the clob" + e);
					}*/

					logger.info(" String.valueOf(maxcode[0][0])"
							+ String.valueOf(maxcode[0][0]));
					String dtlQuery = " INSERT INTO HRMS_LETTERTEMPLATE_DTL (QUERY_ID,TEMPLATE_ID,QUERY_STRING,QUERY_TYPE,QUERY_NAME, QUERY_NOOFROW, QUERY_NOOFCOLUMN,QUERY_COLUMN_WIDTH ) "
							+ " VALUES (?,"
							+ String.valueOf(maxcode[0][0])
							+ ",?,?,?,?,?,?)";

					Object[][] insData = null;
					insData = new Object[queryName.length][7];
					if (queryName != null && queryName.length > 0) {
						for (int i = 0; i < queryName.length; i++) {
							insData[i][0] = i + 1;
							insData[i][1] = String.valueOf(queryName[i]);
							if (queryType[i].equals("Salary")) {
								queryType[i] = "S";
							} else if (queryType[i].equals("Other")) {
								queryType[i] = "O";
							} 
							 else if (queryType[i].equals("Table Data"))
							 {
								 queryType[i] = "D";
							 }
							 
							else
								queryType[i] = "";
							insData[i][2] = String.valueOf(queryType[i]);
							insData[i][3] = String.valueOf(queryNameItt[i]);
							insData[i][4] = String.valueOf(noOfRows[i]);
							insData[i][5] = String.valueOf(noOfColumn[i]);
							insData[i][6] = String.valueOf(colsWidth[i]);
						}
						result = getSqlModel().singleExecute(dtlQuery, insData);
					}

				}

			} else {
				result = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public String getTemplateIdOnName(LetterTemplate letterTemplate)

	{
		String tempId = "";
		try {
			String tempIdQuery = "SELECT TEMPLATE_ID FROM HRMS_LETTERTEMPLATE_HDR WHERE TEMPLATE_NAME='"
					+ letterTemplate.getTempName() + "'";
			Object[][] tempIdData = getSqlModel().getSingleResult(tempIdQuery);
			tempId = "";
			if (tempIdData != null && tempIdData.length > 0) {
				tempId = String.valueOf(tempIdData[0][0]);
			}// end of if
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tempId;
	}

	public boolean update(LetterTemplate letterTemplate, String[] srNo,
			String[] queryName, String[] queryType, String[] noOfRows,
			String[] noOfColumn,String[] colsWidth, String[] queryNameItt) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		System.out.println("$$$$$$$$$$$$$$$$IM IN UPDATE$$$$$$$$$$$$$$$$$$$$$4");
		
		try {
			//boolean flag =checkDuplicateMod(letterTemplate);
			//System.out.println("flag  0"+flag);

			if (true) {
				
				Object[][] updateData = new Object[1][4];
				updateData[0][0] = letterTemplate.getTempName();
				updateData[0][1] = letterTemplate.getHtmlcode();
				updateData[0][2] = letterTemplate.getTempType();
				updateData[0][3] = letterTemplate.getTempCode();

				String hdrQuery = " UPDATE HRMS_LETTERTEMPLATE_HDR SET  TEMPLATE_DATE=SYSDATE , "
						+ " TEMPLATE_NAME = ?,"
						+ " TEMPLATE_BODY = ? ,TEMPLATE_TYPE=? WHERE TEMPLATE_ID =? ";
System.out.println("before update..");
				try {
					result = getSqlModel().singleExecute(hdrQuery, updateData);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				if (result) {
/*
					try {

						String sqlText = "SELECT TEMPLATE_BODY  FROM HRMS_LETTERTEMPLATE_HDR WHERE  TEMPLATE_ID = "
								+ letterTemplate.getTempCode() + " FOR UPDATE ";
						SqlModel model = getSqlModel();
						Connection conn = model.connection();
						Statement stmt = conn.createStatement();
						ResultSet rset = stmt.executeQuery(sqlText);
						rset.next();
						CLOB xmlDoent = ((OracleResultSet) rset)
								.getCLOB("TEMPLATE_BODY");
						xmlDoent.setString(1, letterTemplate.getHtmlcode());

						conn.commit();
						rset.close();
						stmt.close();
						model.freeConnection(conn);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						logger.error("Error  while inserting the clob" + e);
					}*/

					// oracle.sql.CLOB.freeTemporary(newClob);

					String deleteQuery =  "DELETE FROM HRMS_LETTERTEMPLATE_DTL WHERE TEMPLATE_ID =" + letterTemplate.getTempCode();
							

					String dtlQuery = " INSERT INTO HRMS_LETTERTEMPLATE_DTL (QUERY_ID,TEMPLATE_ID,QUERY_STRING,QUERY_TYPE,QUERY_NAME, QUERY_NOOFROW, QUERY_NOOFCOLUMN,QUERY_COLUMN_WIDTH) "
							+ " VALUES (?,"
							+ letterTemplate.getTempCode()
							+ ", ?,?,?,?,?,?)";
					result = getSqlModel().singleExecute(deleteQuery);
					System.out.println("queryName.length  "+queryName.length);
					Object[][] insData = new Object[queryName.length][7];
					for (int i = 0; i < queryName.length; i++) {

						logger.info("Query id======" + String.valueOf(srNo[i]));
						logger.info("Query======="
								+ String.valueOf(queryName[i]));

						insData[i][0] = String.valueOf(srNo[i]);
						insData[i][1] = String.valueOf(queryName[i]);
						if (queryType[i].equals("Salary")) {
							queryType[i] = "S";
						} else if (queryType[i].equals("Other")) {
							queryType[i] = "O";
						} 
						 else if (queryType[i].equals("Table Data"))
						 {
							 queryType[i] = "D";
						 }
						 
						else
							queryType[i] = "";
						insData[i][2] = String.valueOf(queryType[i]);

						insData[i][3] = String.valueOf(queryNameItt[i]);
						insData[i][4] = String.valueOf(noOfRows[i]);
						insData[i][5] = String.valueOf(noOfColumn[i]);
						insData[i][6] = String.valueOf(colsWidth[i]);
					}
					result = getSqlModel().singleExecute(dtlQuery, insData);
				}

			} else {
				result = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * method to set the jsp fields with the data by setting the bean variables
	 * after search
	 * 
	 * @param field
	 * @param queryNameaction
	 * @param srNo
	 * @param tempBean
	 */

	public void setTemplateData(LetterTemplate letterTemplate, String[] srNo,
			String[] queryName, String[] field, String[] queryType) {
		// TODO Auto-generated method stub
		logger.info("setting Data----------");
		boolean salaryStruct = false;
		try {
			String queryHDR = "SELECT HRMS_LETTERTEMPLATE_HDR.TEMPLATE_ID, TEMPLATE_NAME,TEMPLATE_BODY,TEMPLATE_TYPE"
					+ " FROM HRMS_LETTERTEMPLATE_HDR "

					+ " WHERE HRMS_LETTERTEMPLATE_HDR.TEMPLATE_ID = "
					+ letterTemplate.getTempCode();

			Object[][] dataHDR = getSqlModel().getSingleResult(queryHDR);

			String queryDTL = "SELECT QUERY_STRING,DECODE(QUERY_TYPE,'S','Salary','O','Other','D','Table Data'), QUERY_NAME, NVL(QUERY_NOOFROW,0), NVL(QUERY_NOOFCOLUMN,0), NVL(QUERY_COLUMN_WIDTH,' ') "
					+ " FROM HRMS_LETTERTEMPLATE_DTL "
					+ " WHERE HRMS_LETTERTEMPLATE_DTL.TEMPLATE_ID = "
					+ letterTemplate.getTempCode() + " ORDER BY QUERY_ID ";

			if (dataHDR != null && dataHDR.length > 0) {
				letterTemplate.setTempName(checkNull(String
						.valueOf(dataHDR[0][1])));
				letterTemplate.setHtmlcode(checkNull(String
						.valueOf(dataHDR[0][2])));
				letterTemplate.setTempType(checkNull(String
						.valueOf(dataHDR[0][3])));
			}
			Object[][] dataDTL = getSqlModel().getSingleResult(queryDTL);
			ArrayList<Object> list = new ArrayList<Object>();
			ArrayList<Object> salaryList = new ArrayList<Object>();
			if (dataDTL != null && dataDTL.length > 0) {
				for (int i = 0; i < dataDTL.length; i++) {

					System.out.println("String.valueOf(dataDTL[i][1])-------------------------------"+ String.valueOf(dataDTL[i][1]));
					LetterTemplate bean = new LetterTemplate();
					bean.setQueryName(checkNull(String.valueOf(dataDTL[i][0])));
					bean.setSrNo(String.valueOf(i + 1));
					bean.setQueryType(checkNull(String.valueOf(dataDTL[i][1])));
					bean.setNoOfRowsItt(checkNullZero(String.valueOf(dataDTL[i][3])));
					bean.setNoOfColumnItt(checkNullZero(String.valueOf(dataDTL[i][4])));
					bean.setQueryNameItt(String.valueOf(dataDTL[i][2]));
					bean.setColsWidthItt(checkNullZero(String.valueOf(dataDTL[i][5])));

					if (bean.getQueryType().equals("Salary")) {
						TreeMap tempMap = new TreeMap();
						// tempMap.put("SELECT","SELECT");
						LetterTemplate salBean = new LetterTemplate();
						int row = Integer.parseInt(checkNullZero(String
								.valueOf(dataDTL[i][3])));
						int col = Integer.parseInt(checkNullZero(String
								.valueOf(dataDTL[i][4])));
						if (row == 0 || col == 0) {
							tempMap.put("DATA NOT AVAILABLE",
									"DATA NOT AVAILABLE");
						}
						for (int j = 0; j < row; j++) {
							for (int k = 0; k < col; k++) {
								tempMap.put(bean.getSrNo() + "_ROW" + (j + 1)
										+ "_COL" + (k + 1), bean.getSrNo()
										+ "_ROW" + (j + 1) + "_COL" + (k + 1));
							}
						}
						salBean.setQueryNameItt(String.valueOf(dataDTL[i][2]));
						salBean.setTemplateOptSalaryMap(tempMap);
						salaryList.add(salBean);

					}

					if (bean.getQueryType().equals("Salary")) {
						salaryStruct = true;
					}
					list.add(bean);

				}
				letterTemplate.setList(list);
				letterTemplate.setSalaryItt(salaryList);
			}
			if (list.size() == 0) {
				letterTemplate.setTableLength("0");
			} else {
				letterTemplate.setTableLength("1");
			}
			// Object listData[][]=new Object[data.length][1];
			ArrayList<String[]> tempList = new ArrayList<String[]>();
			ArrayList<Object> iterList = new ArrayList<Object>();
			TreeMap tempMap = new TreeMap();
			if (dataDTL != null && dataDTL.length > 0) {
				for (int i = 0; i < dataDTL.length; i++) {
					/*
					 * logger.info("Queries-------------------set---" +
					 * String.valueOf(data[i][2])); Object listData[][] =
					 * getSqlModel().getSingleResultWithCol(
					 * String.valueOf(data[i][2]));
					 */

					StringTokenizer st = new StringTokenizer(String
							.valueOf(dataDTL[i][0]));

					// get how many tokens inside st object
					System.out.println("tokens count: " + st.countTokens());

					String[] splitedQuery = new String[st.countTokens()];
					// iterate st object to get more tokens from it
					int count = 0;
					while (st.hasMoreElements()) {
						splitedQuery[count] = st.nextElement().toString();
						//System.out.println("token = " + splitedQuery[count]);
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
						System.out.println("list..." + list1.get(k));
						fields[k] = (String) list1.get(k);

					}

					tempList.add(fields);
				}
			}
			for (Iterator iterator = tempList.iterator(); iterator.hasNext();) {
				String[] dataList = (String[]) iterator.next();
				for (int i = 0; i < dataList.length; i++) {
					LetterTemplate bean1 = new LetterTemplate();
					bean1.setField(String.valueOf(dataList[i]));
					iterList.add(bean1);

					tempMap.put(String.valueOf(dataList[i]), String
							.valueOf(dataList[i]));
				}

			}
			if (salaryStruct) {
				LetterTemplate bean2 = new LetterTemplate();
				bean2.setField("SALARY TABLE");
				iterList.add(bean2);
				tempMap.put("SALARY TABLE", "SALARY TABLE");
			}
			
			/*
			 edited by Prashant on 6/09/2010 to add data from HRMS_LETTERTEMPLATE_VAR table
			 */
			
			String letterTempVar = "SELECT TEMPLATE_ID, VAR_NAME, VAR_FORMULA, VAR_PRIORITY FROM HRMS_LETTERTEMPLATE_VAR WHERE TEMPLATE_ID = "
				+ letterTemplate.getTempCode();
			
			Object[][] letterTempData = getSqlModel().getSingleResult(letterTempVar);
			TreeMap letterTempMap = new TreeMap();
			
			if(letterTempData!= null && letterTempData.length > 0)
			{
				for( int i=0; i< letterTempData.length; i++){
					letterTempMap.put(String.valueOf(letterTempData[i][1]), String.valueOf(letterTempData[i][1]));
					
				}
				
			}
			letterTemplate.setLetterTempMap(letterTempMap);
			letterTemplate.setIterate(iterList);
			letterTemplate.setTemplateOptMap(tempMap);
			letterTemplate.setTemplateOptFlag("true");
			letterTemplate.setVariableName("");
			letterTemplate.setVariableValue("");
			letterTemplate.setVariablePriority("");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * method to check the duplicate entry of record during insertion
	 * 
	 * @param letterTemplate
	 * @return boolean
	 */
	public boolean checkDuplicate(LetterTemplate letterTemplate) {
		boolean result = false;
		try {
			String query = "SELECT TEMPLATE_ID FROM HRMS_LETTERTEMPLATE_HDR WHERE UPPER(TEMPLATE_NAME) LIKE '%"
					+ letterTemplate.getTempName().trim().toUpperCase() + "%'";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}// end of if
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("........");
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * method to check duplicate entry of record during modification
	 * 
	 * @param letterTemplate
	 * @return boolean
	 */

	public boolean checkDuplicateMod(LetterTemplate letterTemplate) {
		boolean result = false;
		try {
			String query = "SELECT TEMPLATE_ID FROM HRMS_LETTERTEMPLATE_HDR WHERE UPPER(TEMPLATE_NAME) LIKE '%"
					+ letterTemplate.getTempName().trim().toUpperCase()
					+ "%' AND TEMPLATE_ID NOT IN("
					+ letterTemplate.getTempCode() + ")";
			Object[][] data = getSqlModel().getSingleResult(query);
			if (data != null && data.length > 0) {
				result = true;
			}// end of if
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;

	}

	public boolean update(LetterTemplate letterTemplate) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {

			if (true) {
				System.out.println("*******************");
				Object[][] updateData = new Object[1][3];
				updateData[0][0] = letterTemplate.getTempName();
				// System.out.println("m here" + updateData[0][0]);//

				updateData[0][1] = letterTemplate.getTemplateType();
				// System.out.println("m here" + updateData[0][1]);//

				updateData[0][2] = letterTemplate.getTempCode();
				// System.out.println("m here" + updateData[0][2]);//

				String hdrQuery = " UPDATE HRMS_LETTERTEMPLATE_HDR SET  TEMPLATE_DATE=SYSDATE , "
						+ " TEMPLATE_NAME = ?,"
						+ " TEMPLATE_BODY = EMPTY_CLOB() ,TEMPLATE_TYPE=? WHERE TEMPLATE_ID =? ";

				result = getSqlModel().singleExecute(hdrQuery, updateData);

				if (result) {

					try {
						String sqlText = "SELECT TEMPLATE_BODY  FROM HRMS_LETTERTEMPLATE_HDR WHERE  TEMPLATE_ID = "
								+ letterTemplate.getTempCode() + " FOR UPDATE ";
						SqlModel model = getSqlModel();
						Connection conn = model.connection();
						Statement stmt = conn.createStatement();
						ResultSet rset = stmt.executeQuery(sqlText);
						rset.next();
						CLOB xmlDoent = ((OracleResultSet) rset)
								.getCLOB("TEMPLATE_BODY");
						xmlDoent.setString(1, letterTemplate.getHtmlcode());

						conn.commit();
						rset.close();
						stmt.close();
						model.freeConnection(conn);
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public void setTemplateData(LetterTemplate letterTemplate) {
		// TODO Auto-generated method stub
		logger.info("setting Data----------");
		boolean salaryStruct = false;
		try {
			String queryHDR = "SELECT HRMS_LETTERTEMPLATE_HDR.TEMPLATE_ID, TEMPLATE_NAME,TEMPLATE_BODY,TEMPLATE_TYPE"
					+ " FROM HRMS_LETTERTEMPLATE_HDR "

					+ " WHERE HRMS_LETTERTEMPLATE_HDR.TEMPLATE_ID = "
					+ letterTemplate.getTempCode();

			Object[][] dataHDR = getSqlModel().getSingleResult(queryHDR);

			String queryDTL = "SELECT QUERY_STRING,DECODE(QUERY_TYPE,'S','Salary','O','Other',''),QUERY_NAME, NVL(QUERY_NOOFROW,0), NVL(QUERY_NOOFCOLUMN,0) ,QUERY_ID"
					+ " FROM HRMS_LETTERTEMPLATE_DTL "
					+ " WHERE HRMS_LETTERTEMPLATE_DTL.TEMPLATE_ID = "
					+ letterTemplate.getTempCode();

			if (dataHDR != null && dataHDR.length > 0) {
				letterTemplate.setTempName(String.valueOf(dataHDR[0][1]));
				letterTemplate.setHtmlcode(String.valueOf(dataHDR[0][2]));
				letterTemplate.setTemplateType(String.valueOf(dataHDR[0][3]));
			}
			Object[][] dataDTL = getSqlModel().getSingleResult(queryDTL);
			ArrayList<Object> list = new ArrayList<Object>();
			ArrayList<Object> salaryList = new ArrayList<Object>();
			if (dataDTL != null && dataDTL.length > 0) {
				for (int i = 0; i < dataDTL.length; i++) {
					LetterTemplate bean = new LetterTemplate();
					bean.setQType(String.valueOf(dataDTL[i][1]));
					if (bean.getQType().equals("Salary")) {

						TreeMap tempMap = new TreeMap();
						// tempMap.put("SELECT","SELECT");
						LetterTemplate salBean = new LetterTemplate();
						int row = Integer.parseInt(checkNullZero(String
								.valueOf(dataDTL[i][3])));
						int col = Integer.parseInt(checkNullZero(String
								.valueOf(dataDTL[i][4])));
						if (row == 0 || col == 0) {
							tempMap.put("DATA NOT AVAILABLE",
									"DATA NOT AVAILABLE");
						}
						for (int j = 0; j < row; j++) {
							for (int k = 0; k < col; k++) {
								tempMap.put(String
										.valueOf(dataDTL[i][5]) + "_ROW" + (j + 1)
										+ "_COL" + (k + 1),String
										.valueOf(dataDTL[i][5])
										+ "_ROW" + (j + 1) + "_COL" + (k + 1));
							}
						}
						salBean.setQueryNameItt(String.valueOf(dataDTL[i][2]));
						salBean.setTemplateOptSalaryMap(tempMap);
						salaryList.add(salBean);

					
						
						salaryStruct = true;
						
						
					}
					list.add(bean);

				}
				letterTemplate.setList(list);
				letterTemplate.setSalaryItt(salaryList);
			}
			if (list.size() == 0) {
				letterTemplate.setTableLength("0");
			} else {
				letterTemplate.setTableLength("1");
			}
			// Object listData[][]=new Object[data.length][1];
			ArrayList<String[]> tempList = new ArrayList<String[]>();
			ArrayList<Object> iterList = new ArrayList<Object>();
			TreeMap tempMap = new TreeMap();
			if (dataDTL != null && dataDTL.length > 0) {
				for (int i = 0; i < dataDTL.length; i++) {
					/*
					 * logger.info("Queries-------------------set---" +
					 * String.valueOf(data[i][2])); Object listData[][] =
					 * getSqlModel().getSingleResultWithCol(
					 * String.valueOf(data[i][2]));
					 */

					StringTokenizer st = new StringTokenizer(String
							.valueOf(dataDTL[i][0]));

					// get how many tokens inside st object
					System.out.println("tokens count: " + st.countTokens());

					String[] splitedQuery = new String[st.countTokens()];
					// iterate st object to get more tokens from it
					int count = 0;
					while (st.hasMoreElements()) {
						splitedQuery[count] = st.nextElement().toString();
						System.out.println("token = " + splitedQuery[count]);
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
						System.out.println("list..." + list1.get(k));
						fields[k] = (String) list1.get(k);

					}

					tempList.add(fields);
				}
			}
			for (Iterator iterator = tempList.iterator(); iterator.hasNext();) {
				String[] dataList = (String[]) iterator.next();
				for (int i = 0; i < dataList.length; i++) {
					LetterTemplate bean1 = new LetterTemplate();
					bean1.setField(String.valueOf(dataList[i]));
					iterList.add(bean1);

					tempMap.put(String.valueOf(dataList[i]), String
							.valueOf(dataList[i]));
				}

			}
			if (salaryStruct) {
				LetterTemplate bean2 = new LetterTemplate();
				bean2.setField("SALARY TABLE");
				iterList.add(bean2);
				tempMap.put("SALARY TABLE", "SALARY TABLE");
			}
			
			/*
			 edited by Prashant on 6/09/2010 to add data from HRMS_LETTERTEMPLATE_VAR table
			 */
			
			String letterTempVar = "SELECT TEMPLATE_ID, VAR_NAME, VAR_FORMULA, VAR_PRIORITY FROM HRMS_LETTERTEMPLATE_VAR WHERE TEMPLATE_ID = "
				+ letterTemplate.getTempCode();
			
			Object[][] letterTempData = getSqlModel().getSingleResult(letterTempVar);
			TreeMap letterTempMap = new TreeMap();
			
			if(letterTempData!= null && letterTempData.length > 0)
			{
				for( int i=0; i< letterTempData.length; i++){
					letterTempMap.put(String.valueOf(letterTempData[i][1]), String.valueOf(letterTempData[i][1]));
					
				}
				
			}
			letterTemplate.setLetterTempMap(letterTempMap);
			letterTemplate.setIterate(iterList);
			letterTemplate.setTemplateOptMap(tempMap);
			letterTemplate.setTemplateOptFlag("true");
			letterTemplate.setVariableName("");
			letterTemplate.setVariableValue("");
			letterTemplate.setVariablePriority("");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public boolean deleteQuery(String[] srNo, String[] queryName,
			String[] queryType, LetterTemplate letterTemplate,
			String[] noOfRows, String[] noOfColumn,String[] colsWidth, String[] queryNameItt) {
		try {
			// TODO Auto-generated method stub
			ArrayList<Object> tableList = new ArrayList<Object>();
			ArrayList<Object> salaryList = new ArrayList<Object>();
			if (srNo != null) {
				int count = 0;
				for (int i = 0; i < srNo.length; i++) {
					LetterTemplate bean = new LetterTemplate();
					logger.info("_________________" + queryName[i]);
					bean.setSrNo(String.valueOf(i + 1));
					bean.setQueryName(queryName[i]);
					bean.setQueryType(queryType[i]);
					bean.setNoOfRowsItt(checkNullZero(noOfRows[i]));
					bean.setNoOfColumnItt(checkNullZero(noOfColumn[i]));
					bean.setColsWidthItt(checkNullZero(colsWidth[i]));
					bean.setQueryNameItt(queryNameItt[i]);
					tableList.add(bean);
					count++;
					if ((i == Integer.parseInt(letterTemplate.getHiddenEdit()) - 1)) {
						count--;
					}
					if (!(i == Integer.parseInt(letterTemplate.getHiddenEdit()) - 1)) {
						if (queryType[i].equals("Salary")) {
							TreeMap tempMap = new TreeMap();
							LetterTemplate salBean = new LetterTemplate();
							int row = Integer
									.parseInt(checkNullZero(noOfRows[i]));
							int col = Integer
									.parseInt(checkNullZero(noOfColumn[i]));
							if (row == 0 || col == 0) {
								tempMap.put("DATA NOT AVAILABLE",
										"DATA NOT AVAILABLE");
							}
							for (int j = 0; j < row; j++) {
								for (int k = 0; k < col; k++) {
									tempMap.put(count + "_ROW" + (j + 1)
											+ "_COL" + (k + 1), count + "_ROW"
											+ (j + 1) + "_COL" + (k + 1));
								}
							}
							salBean.setQueryNameItt(queryNameItt[i]);
							salBean.setTemplateOptSalaryMap(tempMap);
							salaryList.add(salBean);
						}
					}
					

				}// end of for

				tableList.remove(Integer.parseInt(letterTemplate
						.getHiddenEdit()) - 1);
			}
			letterTemplate.setList(tableList);
			letterTemplate.setSalaryItt(salaryList);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public String createTempListForEdit(LetterTemplate letterTemplate,
			String[] srNo, String[] queryName, String[] field,
			String[] queryType, String[] noOfRows, String[] noOfColumn,String[] colsWidth) {
		// TODO Auto-generated method stub

		try {
			ArrayList<Object> tempList = new ArrayList<Object>();
			TreeMap tempMap = new TreeMap();
			if (queryName != null) {
				for (int i = 0; i < queryName.length; i++) {
					if (i == Integer.parseInt(letterTemplate.getHiddenEdit()) - 1) {
					} else {

						StringTokenizer st = new StringTokenizer(queryName[i]);

						// get how many tokens inside st object
						System.out.println("tokens count: " + st.countTokens());

						String[] splitedQuery = new String[st.countTokens()];
						// iterate st object to get more tokens from it
						int count = 0;
						while (st.hasMoreElements()) {
							splitedQuery[count] = st.nextElement().toString();
							System.out
									.println("token = " + splitedQuery[count]);
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
							System.out.println("list..." + list.get(k));
							fields[k] = (String) list.get(k);

						}

						logger.info("Content ----------"
								+ letterTemplate.getTempContent());
						for (int m = 0; m < fields.length; m++) {
							LetterTemplate bean = new LetterTemplate();
							bean.setField(String.valueOf(fields[m]));
							tempList.add(bean);
							tempMap.put(String.valueOf(fields[m]), String
									.valueOf(fields[m]));
						}

					}

				}

			}
			String listQuery = letterTemplate.getQName();
			logger.info("Queries ----:" + listQuery);
			StringTokenizer st = new StringTokenizer(listQuery);
			// get how many tokens inside st object
			System.out.println("tokens count: " + st.countTokens());
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
				System.out.println("list..." + list.get(i));
				fields[i] = (String) list.get(i);

			}
			for (int i = 0; i < fields.length; i++) {
				LetterTemplate bean = new LetterTemplate();
				bean.setField(String.valueOf(fields[i]));

				tempMap.put(String.valueOf(fields[i]), String
						.valueOf(fields[i]));
				tempList.add(bean);
			}
			letterTemplate.setIterate(tempList);
			letterTemplate.setTemplateOptMap(tempMap);
			letterTemplate.setTemplateOptFlag("true");
			logger.info("Content ---4545-------" + tempList.size());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Query Added";
	}

	public String createTempListForEdit1(LetterTemplate letterTemplate,
			String[] srNo, String[] queryName, String[] field,
			String[] queryType) {
		// TODO Auto-generated method stub
		/* To add paramaters to the template. Setting the labels from the query */

		try {
			String listQuery = letterTemplate.getQName();
			logger.info("Queries ----:" + listQuery);
			StringTokenizer st = new StringTokenizer(listQuery);
			// get how many tokens inside st object
			System.out.println("tokens count: " + st.countTokens());
			String[] splitedQuery = new String[st.countTokens()];
			// iterate st object to get more tokens from it
			int count = 0;
			while (st.hasMoreElements()) {
				splitedQuery[count] = st.nextElement().toString();
				System.out.println("token = " + splitedQuery[count]);
				count++;
			}
			ArrayList list = new ArrayList();
			TreeMap tempMap = new TreeMap();
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
			logger.info("Content -----field.length-----" + field.length);
			if (field != null) {
				for (int i = 0; i < field.length; i++) {
					LetterTemplate bean = new LetterTemplate();
					bean.setField(String.valueOf(field[i]));
					tempList.add(bean);

					tempMap.put(String.valueOf(field[i]), String
							.valueOf(field[i]));
				}
			}
			/*
			 * for (int i = 0; i < fields.length; i++) { LetterTemplate bean =
			 * new LetterTemplate(); bean.setField(String.valueOf(fields[i]));
			 * tempList.add(bean); }
			 */
			letterTemplate.setIterate(tempList);
			letterTemplate.setTemplateOptMap(tempMap);
			letterTemplate.setTemplateOptFlag("true");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "Query Added";

	}

	public void createTempListForDelete(LetterTemplate letterTemplate,
			String[] srNo, String[] queryName, String[] field,
			String[] queryType) {
		try {
			// TODO Auto-generated method stub
			ArrayList<Object> tempList = new ArrayList<Object>();
			TreeMap tempMap = new TreeMap();
			if (queryName != null) {
				for (int i = 0; i < queryName.length; i++) {
					if (i == Integer.parseInt(letterTemplate.getHiddenEdit()) - 1) {

					} else {

						StringTokenizer st = new StringTokenizer(queryName[i]);

						// get how many tokens inside st object
						System.out.println("tokens count: " + st.countTokens());

						String[] splitedQuery = new String[st.countTokens()];
						// iterate st object to get more tokens from it
						int count = 0;
						while (st.hasMoreElements()) {
							splitedQuery[count] = st.nextElement().toString();
							System.out
									.println("token = " + splitedQuery[count]);
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
							System.out.println("list..." + list.get(k));
							fields[k] = (String) list.get(k);

						}

						logger.info("Content ----------"
								+ letterTemplate.getTempContent());
						for (int m = 0; m < fields.length; m++) {
							LetterTemplate bean = new LetterTemplate();
							bean.setField(String.valueOf(fields[m]));
							tempList.add(bean);

							tempMap.put(String.valueOf(fields[m]), String
									.valueOf(fields[m]));
						}

					}

				}

			}
			letterTemplate.setIterate(tempList);
			letterTemplate.setTemplateOptMap(tempMap);
			letterTemplate.setTemplateOptFlag("true");
		} catch (Exception e) {
			// TODO: handle exception
		}
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
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public void getTemplateType(LetterTemplate letterTemplate) {
		// TODO Auto-generated method stub
		String query = "SELECT LETTERTYPE_ID,LETTERTYPE FROM HRMS_LETTERTYPE ORDER BY LETTERTYPE_ID";
		Object[][] data = getSqlModel().getSingleResult(query);
		TreeMap map = new TreeMap();
		if (data != null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				map.put(String.valueOf(data[i][0]), String.valueOf(data[i][1]));
			}
		} else {
			map.put("NO DATA AVAILABLE", "NO DATA AVAILABLE");
		}
		letterTemplate.setTempTypeMap(map);
	}

	public boolean duplicate(LetterTemplate letterTemplate) {
		Boolean result=false;
		if(letterTemplate.getTempCode()!=null && letterTemplate.getTempCode()!="")
		{
		String insertQuery = "INSERT INTO HRMS_LETTERTEMPLATE_HDR "
				+ " (TEMPLATE_ID, TEMPLATE_DATE, TEMPLATE_NAME, TEMPLATE_BODY_NAME, TEMPLATE_BODY, TEMPLATE_TYPE)"
				+ " (  SELECT (SELECT MAX(TEMPLATE_ID)+1 FROM HRMS_LETTERTEMPLATE_HDR) "
				+ "  ,TEMPLATE_DATE, '"+letterTemplate.getDupTempName()+"', TEMPLATE_BODY_NAME, TEMPLATE_BODY, TEMPLATE_TYPE"
				+ " FROM HRMS_LETTERTEMPLATE_HDR WHERE TEMPLATE_ID= "
				+ " "+letterTemplate.getTempCode()+ ")";
				
		
		getSqlModel().singleExecute(insertQuery);

		System.out
				.println("data after insertion ........................................"
						+ insertQuery);
		String dtlInsert = " INSERT INTO HRMS_LETTERTEMPLATE_DTL (TEMPLATE_ID,QUERY_ID, QUERY_STRING, " +
				"   QUERY_TYPE, QUERY_NAME, QUERY_NOOFROW, QUERY_NOOFCOLUMN,QUERY_COLUMN_WIDTH)  " +
				"   (SELECT  (SELECT MAX(TEMPLATE_ID) FROM HRMS_LETTERTEMPLATE_HDR),QUERY_ID," +
				" QUERY_STRING,      QUERY_TYPE, QUERY_NAME, QUERY_NOOFROW ,QUERY_NOOFCOLUMN, " +
				"QUERY_COLUMN_WIDTH       FROM HRMS_LETTERTEMPLATE_DTL WHERE  TEMPLATE_ID= "
				+letterTemplate.getTempCode()+")";
		getSqlModel().singleExecute(dtlInsert);
	   String varQuery="INSERT INTO HRMS_LETTERTEMPLATE_VAR (TEMPLATE_ID , VAR_NAME, VAR_FORMULA, VAR_PRIORITY)" +
	   		"  (SELECT  (SELECT MAX(TEMPLATE_ID) FROM HRMS_LETTERTEMPLATE_HDR),VAR_NAME, VAR_FORMULA, VAR_PRIORITY  FROM HRMS_LETTERTEMPLATE_VAR WHERE TEMPLATE_ID="
		   +letterTemplate.getTempCode()+ ")";

		
		getSqlModel().singleExecute(varQuery);
		result=true;
		}
		else
		{
			result=false;
		}
		System.out.println("m here  for ..........................................");
		return result;
	}
	
	public boolean addTemplateVariable( LetterTemplate letterTemplate ){
		
		boolean result = false;
		
		try {
			Object[][] variableData = new Object[1][3];
			variableData[0][0] = letterTemplate.getVariableName();
			variableData[0][1] = letterTemplate.getVariableValue();
			variableData[0][2] = letterTemplate.getVariablePriority();
			String addVariableQuery = "INSERT INTO HRMS_LETTERTEMPLATE_VAR (TEMPLATE_ID , VAR_NAME, VAR_FORMULA, VAR_PRIORITY) VALUES ("
					+ letterTemplate.getTempCode() + ", ?, ?, ?)";
			result = getSqlModel()
					.singleExecute(addVariableQuery, variableData);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result ;
	}
	
	public boolean deleteTemplateVariable( LetterTemplate letterTemplate ){
		boolean result = false;
		try {
			
			System.out.println("########## VAR_NAME = #############"+letterTemplate.getLetterTempVar());
			String deleteQuery = " DELETE FROM HRMS_LETTERTEMPLATE_VAR WHERE VAR_NAME = '"
									+ letterTemplate.getLetterTempVar()
									+ "' AND TEMPLATE_ID = '"
									+ letterTemplate.getTempCode()
									+ "'";
			
			result = getSqlModel().singleExecute(deleteQuery);
			System.out.println("########## DELETE VARIABLE RESULT "+result);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result ;
		
	}
}
