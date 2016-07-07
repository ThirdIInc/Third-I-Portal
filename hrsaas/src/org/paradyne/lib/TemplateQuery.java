/**
 * 
 */
package org.paradyne.lib;

import java.util.*;

import org.nfunk.jep.function.Str;
import org.paradyne.model.ApplicationStudio.EmailTemplateModel;

/**
 * @author aa0431
 * 
 */
public class TemplateQuery extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(TemplateQuery.class);
	private int queryId;
	private String queryString = "";
	private HashMap parameters = new HashMap();

	public TemplateQuery(int queryId) {
		setQueryId(queryId);
		System.out.println("queryId........" + queryId);
		
	}

	public void setParameter(int parameterOrder, String parameter) {
		parameters.put(parameterOrder, parameter);
	}

	public HashMap getParameters() {
		return parameters;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public int getQueryId() {
		return queryId;
	}

	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}

	public Object[][] execute(String templateID,String sal) {
		// execute query
		String query = "SELECT  QUERY_STRING,QUERY_TYPE,QUERY_COLUMN_WIDTH "
				+ " FROM HRMS_LETTERTEMPLATE_DTL "

				+ " WHERE HRMS_LETTERTEMPLATE_DTL.TEMPLATE_ID = " + templateID
				+ " AND HRMS_LETTERTEMPLATE_DTL.QUERY_ID = " + getQueryId();
		Object[][] data=null;
		try {
			data = getSqlModel().getSingleResult(query);
		} catch (Exception e) {
			logger.info("Set Parameters are not valid. OR .Missing Parameters");
			// TODO: handle exception
		}
		
		/*System.out.println("getQueryId......." + getQueryId());
		System.out.println("getQueryString......." + String.valueOf(data[0][0]));
		System.out.println("parameters.size()" + parameters.size());*/
		
		
		
		Object[] paramObj=new Object[parameters.size()];
		for (int i = 0; i < parameters.size(); i++) {
			paramObj[i]=parameters.get(i+1);
			System.out.println("parameters....." + parameters.get(i + 1));
		}
		Object[][] queryData =null;
		if(sal.equals("S")){
			queryData = getSqlModel().getSingleResult(String.valueOf(data[0][0]),paramObj);
		}
		else if(sal.equals("D")){
			queryData = getSqlModel().getSingleResultWithCol(String.valueOf(data[0][0]),paramObj);
		}
		else{
			queryData = getSqlModel().getSingleResultWithCol(String.valueOf(data[0][0]),paramObj);
		}
		return queryData;
	}
}
