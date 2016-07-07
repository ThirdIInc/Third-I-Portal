package org.paradyne.model.CR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.CR.DataReconcile;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author vivek.wadhwani
 * @Date Jan 28, 2013 : 01:15:00 PM
 */
public class DataReconcileModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	DataReconcile tm = null;

	/**
	 * @purpose: to check null value
	 * 
	 * @param String
	 *            result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}

	/**
	 * @purpose: Pagination dynamically
	 * @param bean
	 * @param request
	 * @return
	 */
	public boolean hasData(DataReconcile bean, HttpServletRequest request,
			String source) {
		boolean result = false;

		String[] filterTables = request.getParameterValues("filterTables"); // getting
																			// filter
																			// tables
		String[] conditions = request.getParameterValues("conditions");// getting
																		// Conditions
		String[] inputCondition = request.getParameterValues("inputCondition");// getting
																				// input
																				// condition
																				// in
																				// filters
																				// entered
																				// by
																				// user

		if (filterTables != null) {
			String queryFilter = "SELECT COLUMN_ID, COLUMN_NAME FROM cr_datasource_columns WHERE SOURCE_ID = "
					+ source;

			Object[][] dataFilter = getSqlModel("POOL_D1").getSingleResult(
					queryFilter);
			ArrayList<DataReconcile> filterPageList = new ArrayList<DataReconcile>();
			ArrayList<Object> ListFilter = new ArrayList<Object>();

			for (int j = 0; j < dataFilter.length; j++) {

				DataReconcile bean2 = new DataReconcile();
				bean2
						.setColumnId(checkNull((String
								.valueOf(dataFilter[j][0]))));// ColumnId
				bean2.setColumnName(checkNull(
						(String.valueOf(dataFilter[j][1]))).toUpperCase());// ColumnName
				ListFilter.add(bean2);

			} // end of for
			for (int i = 0; i < filterTables.length; i++) {
				DataReconcile filterBean = new DataReconcile();

				filterBean.setFilterList(ListFilter);
				// bean.setFilterList(ListFilter);
				filterBean.setFilterTables(filterTables[i]);
				filterBean.setConditions(conditions[i]);
				filterBean.setInputCondition(inputCondition[i]);
				filterPageList.add(filterBean);
			}
			bean.setFilterPageList(filterPageList);
		}

		String sourceName = request.getParameter("sourceName"); // getting table
																// name/data
																// source name
		String query = bean.getQueryParameter(); // getting columns that were
													// selected for updating
		String noOfColumns = bean.getNoOfColumns(); // getting number of columns
													// to display
		int primaryFlagColumnNo = bean.getPrimaryFlagColumnNo();// get
																// primaryColumn
																// Number
		String primaryFlagQuery = bean.getPrimaryQuery(); // getting columns
															// that are
															// compulsory to be
															// displayed
		String displayData = "";
		String filterQuery = bean.getFilterQuery();
		System.out.println("FilterQuery: " + filterQuery);

		String[] tableFlag = request.getParameterValues("tableFlagHidden");
		ArrayList<DataReconcile> checkBoxList = new ArrayList<DataReconcile>();
		int k = 0;

		for (int i = 0; i < tableFlag.length; i++) {// getting checked check box
													// values in an array

			DataReconcile demoBean = new DataReconcile();

			if (tableFlag[i].equals("Y")) {
				// if check box checked

				demoBean.setCheckBox("Y");
				checkBoxList.add(demoBean);
			}// end of if

			else {
				demoBean.setCheckBox("N");
				checkBoxList.add(demoBean);
			}
			k++;
		}// end of for
		bean.setCheckBoxList(checkBoxList);

		if (!query.equals("")) {
			// if query present then append ","
			query = " , " + query;
		}// end of if

		if (filterQuery != null && !filterQuery.equals("null")
				&& !filterQuery.equals("")) {// if filter is present
			displayData = "SELECT " + primaryFlagQuery + " " + query
					+ " FROM " + "\""+sourceName+"\"" + " WHERE " + filterQuery
					+ " ;";
		}// end of if
		else {
			displayData = "SELECT " + primaryFlagQuery + " " + query
					+ " FROM " + "\""+sourceName+"\"" + " ;";
		}// end of else
		String paginationQuery = bean.getPaginationQuery(); // getting
															// pagination query
		Object[] filterValue = paginationQuery.split(",");
		Object[][] clientUserObj = null;
		if (filterQuery != null && !filterQuery.equals("null")
				&& !filterQuery.equals("")) {// if filter is present

			if (!paginationQuery.equals("")) {
				// if pagination query is present
				clientUserObj = getSqlModel("POOL_D1").getSingleResultWithCol(
						displayData, filterValue);
				request.setAttribute("data", clientUserObj);
			}// end of if
			else {
				clientUserObj = getSqlModel("POOL_D1").getSingleResultWithCol(
						displayData);
				request.setAttribute("data", clientUserObj);
			}// end of else
		}// end of outer if
		else {
			clientUserObj = getSqlModel("POOL_D1").getSingleResultWithCol(
					displayData);
			request.setAttribute("data", clientUserObj);

		}// end of outer else
		String parse = String.valueOf(noOfColumns);
		request.setAttribute("primaryFlagColumnNo", primaryFlagColumnNo);
		request.setAttribute("noOfColumns", parse);
		if (clientUserObj != null && clientUserObj.length > 0) {
			// if clientUserObj is not null
			bean.setModeLength("true");
			bean.setTotalRecords(String.valueOf(clientUserObj.length));
			String[] pageIndex = Utility.doPaging(bean.getMyPageInProcess(),
					clientUserObj.length, 20);

			if (pageIndex == null) {
				// if pageindex is not null
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}// end of inner if

			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNumber", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			request.setAttribute("data", clientUserObj);
			request.setAttribute("pageIndex[0]", pageIndex[0]);
			request.setAttribute("pageIndex[1]", pageIndex[1]);
			if (pageIndex[4].equals("1")) {
				bean.setMyPageInProcess("1");
			}// end of inner if
			result = true;
		}// end of outer if

		return result;
	}

	/**
	 * @purpose: Update data in database
	 * @param bean
	 * @param request
	 * @return boolean- result
	 */
	public boolean updateData(DataReconcile bean, HttpServletRequest request,
			String source) {
		boolean result = false;

		try {

			String[] filterTables = request.getParameterValues("filterTables"); // getting
																				// filter
																				// tables
			String[] conditions = request.getParameterValues("conditions");// getting
																			// Conditions
			String[] inputCondition = request
					.getParameterValues("inputCondition");// getting input
															// condition in
															// filters entered
															// by user

			if (filterTables != null) {
				String queryFilter = "SELECT COLUMN_ID, COLUMN_NAME FROM cr_datasource_columns WHERE SOURCE_ID = "
						+ source;

				Object[][] dataFilter = getSqlModel("POOL_D1").getSingleResult(
						queryFilter);
				ArrayList<DataReconcile> filterPageList = new ArrayList<DataReconcile>();
				ArrayList<Object> ListFilter = new ArrayList<Object>();

				for (int j = 0; j < dataFilter.length; j++) {

					DataReconcile bean2 = new DataReconcile();
					bean2.setColumnId(checkNull((String
							.valueOf(dataFilter[j][0]))));// ColumnId
					bean2.setColumnName(checkNull(
							(String.valueOf(dataFilter[j][1]))).toUpperCase());// ColumnName
					ListFilter.add(bean2);

				} // end of for
				for (int i = 0; i < filterTables.length; i++) {
					DataReconcile filterBean = new DataReconcile();

					filterBean.setFilterList(ListFilter);
					// bean.setFilterList(ListFilter);
					filterBean.setFilterTables(filterTables[i]);
					filterBean.setConditions(conditions[i]);
					filterBean.setInputCondition(inputCondition[i]);
					filterPageList.add(filterBean);
				}
				bean.setFilterPageList(filterPageList);

			}

			String sourceName = bean.getSourceName(); // getting table
														// name/data source name

			String query = bean.getQueryParameter();// getting query i.e. tables
													// that are to be updated
			String[] array = query.split(",");

			String primaryQuery = bean.getPrimaryQuery();// getting primary
															// tables of the
															// table that are
															// having unique
															// data
			String[] primaryArray = primaryQuery.split(",");
			HashMap updatePrimaryMap = new HashMap();
			for (int i = 0; i < primaryArray.length; i++) {
				String primaryArrays = primaryArray[i].substring(1, primaryArray[i].length()-1);
				String[] primaryArray_new = request
						.getParameterValues(primaryArrays);
				updatePrimaryMap.put(primaryArray[i], primaryArray_new);

			}// end of for loop

			HashMap updateMap = new HashMap();
			for (int i = 0; i < array.length; i++) {
				String arrays = array[i].substring(1, array[i].length()-1);
				String[] abc = request.getParameterValues(arrays);
				updateMap.put(array[i], abc);

			}// end of for loop
			String[] temp = null;

			temp = (String[]) updateMap.get(array[0]);

			Object[][] updateObj = new Object[temp.length][updateMap.size()
					+ updatePrimaryMap.size()];
			String updateQuery = " UPDATE " + sourceName + "  SET ";
			for (int i = 0; i < array.length; i++) {
				updateQuery += " " + array[i] + " = ?,";
				String[] updateStr = (String[]) updateMap.get(array[i]);
				for (int j = 0; j < updateObj.length; j++) {
					updateObj[j][i] = updateStr[j];
				}// end of inner for
			}// end of outer for
			updateQuery = updateQuery.substring(0, updateQuery.length() - 1);
			updateQuery += " WHERE ";
			for (int i = 0; i < primaryArray.length; i++) {
				updateQuery += " " + primaryArray[i] + " = ? AND";
				String[] updateStr = (String[]) updatePrimaryMap
						.get(primaryArray[i]);
				for (int j = 0; j < updateObj.length; j++) {
					updateObj[j][array.length + i] = updateStr[j];
				}// end of inner for
			}// end of outer for
			updateQuery = updateQuery.substring(0, updateQuery.length() - 3); // final
																				// update
																				// query

			System.out.println("updateQuery  " + updateQuery);
			for (int i = 0; i < updateObj.length; i++) {
				for (int j = 0; j < updateObj[0].length; j++) {
					System.out.println("updateObj[" + i + "]" + "[" + j + "]"
							+ String.valueOf(updateObj[i][j]));
				}// end of inner for

			}// end of outer for

			result = getSqlModel("POOL_D1").singleExecute(updateQuery,
					updateObj);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}

	/**
	 * @purpose: Display data from selected columns
	 * @param bean
	 * @param request
	 * @return boolean result
	 */
	public boolean getDataReconciliation(DataReconcile bean,
			HttpServletRequest request, String source) {
		boolean result = false;
		try {

			String[] filterTables = request.getParameterValues("filterTables"); // getting
																				// filter
																				// tables
			String[] conditions = request.getParameterValues("conditions");// getting
																			// Conditions
			String[] inputCondition = request
					.getParameterValues("inputCondition");// getting input
															// condition in
															// filters entered
															// by user
			bean.setMyPageInProcess("1");
			if (filterTables != null) {
				String queryFilter = "SELECT COLUMN_ID, COLUMN_NAME FROM cr_datasource_columns WHERE SOURCE_ID = "
						+ source;
				Object[][] dataFilter = getSqlModel("POOL_D1").getSingleResult(
						queryFilter);
				ArrayList<DataReconcile> filterPageList = new ArrayList<DataReconcile>();
				ArrayList<Object> ListFilter = new ArrayList<Object>();

				for (int j = 0; j < dataFilter.length; j++) {

					DataReconcile bean2 = new DataReconcile();
					bean2.setColumnId(checkNull((String
							.valueOf(dataFilter[j][0]))));// ColumnId
					bean2.setColumnName(checkNull(
							(String.valueOf(dataFilter[j][1]))).toUpperCase());// ColumnName
					ListFilter.add(bean2);

				} // end of for

				for (int i = 0; i < filterTables.length; i++) {
					DataReconcile filterBean = new DataReconcile();

					filterBean.setFilterList(ListFilter);
					// bean.setFilterList(ListFilter);
					filterBean.setFilterTables(filterTables[i]);
					filterBean.setConditions(conditions[i]);
					filterBean.setInputCondition(inputCondition[i]);
					filterPageList.add(filterBean);
				}
				bean.setFilterPageList(filterPageList);

				String filterQuery = "";

				Object[] filterValues = new Object[filterTables.length];

				for (int i = 0; i < filterTables.length; i++) {

					if (conditions[i].equals("IS NULL")
							|| conditions[i].equals("IS NOT NULL")) {
						// for checking IS NULL and IS NOT NULL condition
						filterQuery += "\"" + filterTables[i] + "\""
								+ conditions[i] + " AND";

					}// end of if

					if (inputCondition[i] != null && inputCondition[i] != ""
							&& inputCondition[i] != "null") {

						if (conditions[i].equals("LIKE ")
								|| conditions[i].equals("NOT LIKE ")) {
							// for checking LIKE and NOT LIKE condition
							filterQuery += "\"" + filterTables[i] + "\""
									+ conditions[i] + " ? AND";
							filterValues[i] = inputCondition[i] + "%";

						}// end of if

						else if (conditions[i].equals("=")
								|| conditions[i].equals("!=")
								|| conditions[i].equals("<")
								|| conditions[i].equals(">")) {
							filterQuery += "\"" + filterTables[i] + "\""
									+ conditions[i] + " ? AND";
							filterValues[i] = inputCondition[i];
						}// end of else
					} // end of outer if

				} // end of for loop

				Vector filter = new Vector();

				for (int i = 0; i < filterValues.length; i++) {
					if (filterValues[i] != null && filterValues[i] != "") {// if
																			// filterValue
																			// is
																			// present

						filter.add(filterValues[i]);

					}// end of if
				}// end of for

				Object[] filterValue = new Object[filter.size()];

				for (int i = 0; i < filter.size(); i++) {
					filterValue[i] = filter.get(i);
				}// end of for

				if (filterQuery != null && !filterQuery.equals("null")
						&& !filterQuery.equals("")) {
					// if filterQUery is present
					filterQuery = filterQuery.substring(0,
							filterQuery.length() - 3);
				}// end of if
				System.out.println("Filter Query: " + filterQuery);

				String[] columnName = request.getParameterValues("columnName");

				int j = 0;
				Vector vector = new Vector();
				String[] primaryFlag = request
						.getParameterValues("primaryFlag");

				for (int i = 0; i < primaryFlag.length; i++) {
					if (primaryFlag[i].equals("Y")) {// getting primaryFlag
														// i.e. unique columns
						String str= String.valueOf(columnName[i]);
						vector.add("\""+str+"\"");
					}// end of if
				}// end of for
				String primaryFlagQuery = "";

				for (int i = 0; i < vector.size(); i++) {
					primaryFlagQuery += vector.get(i) + ",";
				}// end of for
				primaryFlagQuery = primaryFlagQuery.substring(0,
						primaryFlagQuery.length() - 1);

				String[] primaryChecked = primaryFlagQuery.split(",");

				String[] tableFlag = request
						.getParameterValues("tableFlagHidden");
				request.setAttribute("tableFlag", tableFlag);
				ArrayList<DataReconcile> checkBoxList = new ArrayList<DataReconcile>();
				int k = 0;
				Vector vectField = new Vector();

				for (int i = 0; i < tableFlag.length; i++) {// getting checked
															// check box values
															// in an array

					DataReconcile demoBean = new DataReconcile();

					if (tableFlag[i].equals("Y") && !primaryFlag[i].equals("Y")) {
						// if check box checked

						demoBean.setCheckBox("Y");
						String str= String.valueOf(columnName[i]);
						vectField.add("\""+str+"\"");
						j++;
						checkBoxList.add(demoBean);
					}// end of if

					else {
						demoBean.setCheckBox("N");
						checkBoxList.add(demoBean);
					}
					k++;
				}// end of for
				bean.setCheckBoxList(checkBoxList);

				String query = "";

				for (int i = 0; i < vectField.size(); i++) {
					query += vectField.get(i) + ",";
				}// end of for

				if (!query.equals("")) {
					// if query is not null
					query = query.substring(0, query.length() - 1);
				}// end of if
				bean.setQueryParameter(query); // setting columns that were
												// selected for updating

				if (!query.equals("")) {
					query = " , " + query;
				}// end of if

				String sourceName = request.getParameter("sourceName");
				String displayData;

				if (filterQuery != null && !filterQuery.equals("null")
						&& !filterQuery.equals("")) {
					displayData = "SELECT " + primaryFlagQuery + " " + query
							+ "  FROM " + "\""+sourceName+"\"" + " WHERE "
							+ filterQuery + ";";
					bean.setFilterQuery(filterQuery);
				}// end of if
				else {
					displayData = "SELECT " + primaryFlagQuery + " " + query
							+ "  FROM " + "\""+sourceName+"\"" + ";";
					bean.setFilterQuery(filterQuery);
				}// end of else

				bean.setPrimaryQuery(primaryFlagQuery); // setting columns that
														// are compulsory to be
														// displayed

				String paginationQuery = "";

				if (filterQuery != null && !filterQuery.equals("null")
						&& !filterQuery.equals("")) {

					for (int i = 0; i < filterValue.length; i++) {
						if (filterValue[i] != null && filterValue[i] != ""
								&& filterValue[i] != "null") {
							paginationQuery += filterValue[i] + ",";

						}// end of inner if
					}// end of for
					if (!paginationQuery.equals("")) {
						paginationQuery = paginationQuery.substring(0,
								paginationQuery.length() - 1);
					}// end of inner if
				}// end of outer if

				bean.setPaginationQuery(paginationQuery);

				Object[][] clientUserObj = null;
				if (filterQuery != null && !filterQuery.equals("null")
						&& !filterQuery.equals("")) {
					if (!paginationQuery.equals("")) {
						clientUserObj = getSqlModel("POOL_D1")
								.getSingleResultWithCol(displayData,
										filterValue);

						request.setAttribute("data", clientUserObj);// setting
																	// attribute
																	// to get
																	// object in
																	// jsp
					}// end of if
					else {
						clientUserObj = getSqlModel("POOL_D1")
								.getSingleResultWithCol(displayData);

						request.setAttribute("data", clientUserObj);// setting
																	// attribute
																	// to get
																	// object in
																	// jsp

					}// end of else
				} // end of outer if
				else {
					clientUserObj = getSqlModel("POOL_D1")
							.getSingleResultWithCol(displayData);

					request.setAttribute("data", clientUserObj);
				}// end of else

				int noOfColumns = vectField.size() + vector.size();

				String parse = String.valueOf(noOfColumns);

				bean.setNoOfColumns(parse); // setting number of columns to
											// display
				int length = 0;
				request.setAttribute("noOfColumns", parse); // setting attribute
															// to get
															// noOfColumns in
															// jsp
				bean.setPrimaryFlagColumnNo(vector.size());
				request.setAttribute("primaryFlagColumnNo", vector.size());

				if (clientUserObj != null && clientUserObj.length > 0) {
					bean.setModeLength("true");
					bean.setTotalRecords(String.valueOf(clientUserObj.length));
					String[] pageIndex = Utility.doPaging(bean
							.getMyPageInProcess(), clientUserObj.length, 20);

					if (pageIndex == null) {
						// if pageIndex is null
						pageIndex[0] = "0";
						pageIndex[1] = "20";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					}// end of inner if

					request.setAttribute("totalPage", Integer.parseInt(String
							.valueOf(pageIndex[2])));
					request.setAttribute("pageNumber", Integer.parseInt(String
							.valueOf(pageIndex[3])));

					request.setAttribute("pageIndex[0]", pageIndex[0]);
					request.setAttribute("pageIndex[1]", pageIndex[1]);
					if (pageIndex[4].equals("1")) {
						bean.setMyPageInProcess("1");
					}// end of inner if
					length = clientUserObj.length;
					bean.setTotalRecords(String.valueOf(length));
				}
			} else {
				String[] columnNames = request.getParameterValues("columnName");

				int js = 0;
				Vector vectors = new Vector();
				String[] primaryFlags = request
						.getParameterValues("primaryFlag");

				for (int i = 0; i < primaryFlags.length; i++) {
					if (primaryFlags[i].equals("Y")) {// getting primaryFlag
														// i.e. unique columns
						vectors.add("\""+columnNames[i]+"\"");
					}// end of if
				}// end of for
				String primaryFlagQuerys = "";

				for (int i = 0; i < vectors.size(); i++) {
					primaryFlagQuerys += vectors.get(i) + ",";
				}// end of for
				primaryFlagQuerys = primaryFlagQuerys.substring(0,
						primaryFlagQuerys.length() - 1);

				String[] primaryCheckeds = primaryFlagQuerys.split(",");

				String[] tableFlags = request
						.getParameterValues("tableFlagHidden");
				request.setAttribute("tableFlag", tableFlags);
				ArrayList<DataReconcile> checkBoxLists = new ArrayList<DataReconcile>();
				int ks = 0;
				Vector vectFields = new Vector();

				for (int i = 0; i < tableFlags.length; i++) {// getting
																// checked check
																// box values in
																// an array

					DataReconcile demoBean = new DataReconcile();

					if (tableFlags[i].equals("Y")
							&& !primaryFlags[i].equals("Y")) {
						// if check box checked

						demoBean.setCheckBox("Y");
						vectFields.add("\""+columnNames[i]+"\"");
						js++;
						checkBoxLists.add(demoBean);
					}// end of if

					else {
						demoBean.setCheckBox("N");
						checkBoxLists.add(demoBean);
					}
					ks++;
				} // end of for
				bean.setCheckBoxList(checkBoxLists);

				String querys = "";

				for (int i = 0; i < vectFields.size(); i++) {
					querys += vectFields.get(i) + ",";
				}// end of for

				if (!querys.equals("")) {
					// if query is not null
					querys = querys.substring(0, querys.length() - 1);
				}// end of if
				bean.setQueryParameter(querys); // setting columns that were
												// selected for updating

				if (!querys.equals("")) {
					querys = " , " + querys;
				}// end of if

				String sourceNames = request.getParameter("sourceName");
				String displayDatas;
				bean.setFilterQuery("");
				displayDatas = "SELECT " + primaryFlagQuerys + " " + querys
						+ "  FROM " + "\""+sourceNames+"\"" + ";";

				bean.setPrimaryQuery(primaryFlagQuerys); // setting columns
															// that are
															// compulsory to be
															// displayed
				Object[][] clientUserObjs = null;

				clientUserObjs = getSqlModel("POOL_D1").getSingleResultWithCol(
						displayDatas);

				request.setAttribute("data", clientUserObjs);

				int noOfColumnss = vectFields.size() + vectors.size();

				String parses = String.valueOf(noOfColumnss);

				bean.setNoOfColumns(parses); // setting number of columns to
												// display
				int lengths = 0;
				request.setAttribute("noOfColumns", parses); // setting
																// attribute to
																// get
																// noOfColumns
																// in jsp
				bean.setPrimaryFlagColumnNo(vectors.size());
				request.setAttribute("primaryFlagColumnNo", vectors.size());

				if (clientUserObjs != null && clientUserObjs.length > 0) {
					bean.setModeLength("true");
					bean.setTotalRecords(String.valueOf(clientUserObjs.length));
					String[] pageIndex = Utility.doPaging(bean
							.getMyPageInProcess(), clientUserObjs.length, 20);

					if (pageIndex == null) {
						// if pageIndex is null
						pageIndex[0] = "0";
						pageIndex[1] = "20";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					}// end of inner if

					request.setAttribute("totalPage", Integer.parseInt(String
							.valueOf(pageIndex[2])));
					request.setAttribute("pageNumber", Integer.parseInt(String
							.valueOf(pageIndex[3])));

					request.setAttribute("pageIndex[0]", pageIndex[0]);
					request.setAttribute("pageIndex[1]", pageIndex[1]);
					if (pageIndex[4].equals("1")) {
						bean.setMyPageInProcess("1");
					}// end of inner if
					lengths = clientUserObjs.length;
					bean.setTotalRecords(String.valueOf(lengths));

				}

			}
			result = true;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}

	/**
	 * @purpose: Display columns from data source
	 * @param bean
	 * @param request
	 * @param source
	 */
	public void displayColumns(DataReconcile bean, HttpServletRequest request,
			String source) {

		String query = "SELECT COLUMN_ID, COLUMN_NAME, PRIMARY_FLAG FROM cr_datasource_columns WHERE SOURCE_ID = "
				+ source;

		Object[][] data = getSqlModel("POOL_D1").getSingleResult(query);
		request.setAttribute("dataColumn", data);
		if (data != null && data.length > 0) {

			ArrayList<DataReconcile> List = new ArrayList<DataReconcile>();

			for (int i = 0; i < data.length; i++) {

				DataReconcile bean1 = new DataReconcile();
				if(!(String.valueOf(data[i][2])).equals("D")){
				bean1.setColumnId(checkNull((String.valueOf(data[i][0])))); // ColumnId
				bean1.setColumnName(checkNull((String.valueOf(data[i][1])))
						.toUpperCase()); // ColumnName
				bean1.setPrimaryFlag(checkNull((String.valueOf(data[i][2])))); // PrimaryFlag
				List.add(bean1);
				}
				

			} // end of for

			bean.setColumnList(List);
			request.setAttribute("ColumnList", List.size());

			/**
			 * FOr displaying tables in Filter table
			 * 
			 */

			/*
			 * String queryFilter = "SELECT COLUMN_ID, COLUMN_NAME FROM
			 * CR.dbo.cr_datasource_columns WHERE SOURCE_ID = " + source;
			 * 
			 * Object[][] dataFilter = getSqlModel("POOL_D1").getSingleResult(
			 * queryFilter);
			 */
			ArrayList<Object> ListFilter = new ArrayList<Object>();
			for (int i = 0; i < data.length; i++) {

				DataReconcile bean2 = new DataReconcile();
				bean2.setColumnId(checkNull((String.valueOf(data[i][0]))));// ColumnId
				bean2.setColumnName(checkNull((String.valueOf(data[i][1])))
						.toUpperCase());// ColumnName
				ListFilter.add(bean2);

			} // end of for

			bean.setFilterList(ListFilter);

		} // end of if

	}

	/**
	 * @purpose: Display columns from data source
	 * @param bean
	 * @param request
	 * @param source
	 */
	public void displayColumnsWithData(DataReconcile bean,
			HttpServletRequest request, String source) {

		String query = "SELECT COLUMN_ID, COLUMN_NAME, PRIMARY_FLAG FROM cr_datasource_columns WHERE SOURCE_ID = "
				+ source;

		Object[][] data = getSqlModel("POOL_D1").getSingleResult(query);
		request.setAttribute("dataColumn", data);
		if (data != null && data.length > 0) {

			ArrayList<DataReconcile> List = new ArrayList<DataReconcile>();

			for (int i = 0; i < data.length; i++) {

				DataReconcile bean1 = new DataReconcile();
				bean1.setColumnId(checkNull((String.valueOf(data[i][0])))); // ColumnId
				bean1.setColumnName(checkNull((String.valueOf(data[i][1])))
						.toUpperCase()); // ColumnName
				bean1.setPrimaryFlag(checkNull((String.valueOf(data[i][2])))); // PrimaryFlag

				List.add(bean1);

			} // end of for

			bean.setColumnList(List);
			request.setAttribute("ColumnList", List.size());

			/**
			 * FOr displaying tables in Filter table
			 * 
			 */

			String queryFilter = "SELECT COLUMN_ID, COLUMN_NAME FROM cr_datasource_columns WHERE SOURCE_ID = "
					+ source;

			Object[][] dataFilter = getSqlModel("POOL_D1").getSingleResult(
					queryFilter);

			ArrayList<Object> ListFilter = new ArrayList<Object>();
			for (int i = 0; i < dataFilter.length; i++) {

				DataReconcile bean2 = new DataReconcile();
				bean2.setColumnId(checkNull((String.valueOf(data[i][0]))));// ColumnId
				bean2.setColumnName(checkNull((String.valueOf(data[i][1])))
						.toUpperCase());// ColumnName

				ListFilter.add(bean2);

			} //end of for

			bean.setFilterSetList(ListFilter);

		} //end of if

	}

}
