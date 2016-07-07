/**
 * 
 */
package org.paradyne.lib.templates;

import java.sql.Connection;
import java.util.HashMap;
import org.paradyne.lib.ModelBase;

/**
 * @author Reeba_Joseph 01-April-2009
 */
public class EmailTemplateQuery extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmailTemplateQuery.class);
	private int queryId;
	private String queryString = "";
	private String employeeId = "";
	private HashMap parameters = new HashMap();

	public EmailTemplateQuery(int queryId) {
		setQueryId(queryId);
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameter(int parameterOrder, String parameter) {
		parameters.put(parameterOrder, parameter);
	}

	/**
	 * @return the parameters
	 */
	public HashMap getParameters() {
		return parameters;
	}

	/**
	 * @return the queryId
	 */
	public int getQueryId() {
		return queryId;
	}

	/**
	 * @param queryId
	 *            the queryId to set
	 */
	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}

	/**
	 * @return the queryString
	 */
	public String getQueryString() {
		return queryString;
	}

	/**
	 * @param queryString
	 *            the queryString to set
	 */
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	/**
	 * Obtain Query output with column names
	 * 
	 * @param templateID
	 * @param type
	 * @return Object
	 */ 
	public Object[][] execute(String templateID, String type ) {
		// retrieve query from database
		String query = "SELECT  QUERY_STRING,QUERY_TYPE "
				+ " FROM HRMS_EMAILTEMPLATE_DTL "
				+ " WHERE HRMS_EMAILTEMPLATE_DTL.TEMPLATE_ID = " + templateID
				+ " AND HRMS_EMAILTEMPLATE_DTL.QUERY_ID = " + getQueryId();
		Object[][] data = null;
		try {
			data = getSqlModel().getSingleResult(query);
		} // end try block
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Set Parameters are not valid OR Missing Parameters");
		}// end catch block

		Object[] paramObj = new Object[parameters.size()];
		for (int i = 0; i < parameters.size(); i++) {
			paramObj[i] = parameters.get(i + 1);
			if(String.valueOf(data[0][1]).equals("F")){
				setEmployeeId(String.valueOf(parameters.get(i + 1)));
			}
			logger.info("parameters....." + parameters.get(i + 1));
		}// end loop for parameters

		// execute query to obtain result with Column name
		Object[][] queryData = getSqlModel().getSingleResultWithCol(
				String.valueOf(data[0][0]), paramObj);
		return queryData;

	}// end of execute() method
	
	
	
	/**
	 * Obtain Query output with column names
	 * 
	 * @param templateID
	 * @param type
	 * @return Object
	 */ 
	public Object[][] execute(String templateID, String type , Connection dbConn) {
		// retrieve query from database
		String query = "SELECT  QUERY_STRING,QUERY_TYPE "
				+ " FROM HRMS_EMAILTEMPLATE_DTL "
				+ " WHERE HRMS_EMAILTEMPLATE_DTL.TEMPLATE_ID = " + templateID
				+ " AND HRMS_EMAILTEMPLATE_DTL.QUERY_ID = " + getQueryId();
		Object[][] data = null;
		try {
			data = getSqlModel().getSingleResult(query,dbConn);
		} // end try block
		catch (Exception e) {
			// TODO: handle exception
			logger.error("Set Parameters are not valid OR Missing Parameters");
		}// end catch block

		Object[] paramObj = new Object[parameters.size()];
		for (int i = 0; i < parameters.size(); i++) {
			paramObj[i] = parameters.get(i + 1);
			if(String.valueOf(data[0][1]).equals("F")){
				setEmployeeId(String.valueOf(parameters.get(i + 1)));
			}
			logger.info("parameters....." + parameters.get(i + 1));
		}// end loop for parameters

		// execute query to obtain result with Column name
		Object[][] queryData = getSqlModel().getSingleResultWithCol(
				String.valueOf(data[0][0]), paramObj,dbConn);
		return queryData;

	}// end of execute() method


	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
}// end of class
