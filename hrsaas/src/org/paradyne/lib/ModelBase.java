/**
 * 		@ ModelBase.java 
 * 		@ author : Sachin Hegde
 * 		
 * 		@ purpose : TO provide a common plateform to all DAO Objects* 
 * 
 */
package org.paradyne.lib;

import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class ModelBase {
	
	protected ServletContext context;
	protected HttpSession session ;
	/*private Connection modelConn;
	private ConnectionModel connModel;*/
	/**
	 * 
	 * @return: Connection from connection pool
	 * 
	 */	
/*	public Connection getConnection() {
		connModel = new ConnectionModel();
		modelConn = connModel.getConnection(context);
		return modelConn;	
	}*/
	
/*	public void setConnection(Connection conn) {
		this.modelConn=conn;
	}*/
	/**
	 * 
	 * @return: the SQL MODEL with the connection for execution of 
	 * 	queries in the DAO Obejcts.
	 */
	public SqlModel getSqlModel() {
		//connModel = new ConnectionModel();
		//modelConn = connModel.getConnection(context);
		return new SqlModel(context,session); // return a sql Model
	}
	
	public SqlModel getSqlModel(String connName) {
		//connModel = new ConnectionModel();
		//modelConn = connModel.getConnection(context);
		return new SqlModel(context,session,connName); // return a sql Model
	}
	
	public LmsSqlModel getLMSSqlModel() {
		return new LmsSqlModel(context,session); // return a sql Model
	}
	
	public SqlModel getSqlModel(Connection connection) {
		return new SqlModel(connection);
	}
	
	/**
	 * 
	 * 	@param UserId
	 * 	@return : boolean flag which gives the status of audit
	 *  @purpose : to provide an implementaion of database audit 	 
	 */
	public boolean executeAudit(String UserId) {
		return true; 
	}
	
	public boolean initiate(ServletContext context,HttpSession session) {
		this.context = context;
		this.session = session ;
		return true;
	}
	
	/**
	 * 
	 * @return : booelan flag stating the connection status. 
	 */
	public boolean terminate() {
/*		try{
			connModel.freeConnection(modelConn);
		}catch(Exception e){	
		}*/
		return true;	
	}
	
	/**
	 * @param id : The id of the Sql query
	 * @return : the sql query
	 * @purpose : to find the Sql query file and provide query string 
	 * 
	 */
	public String getQuery(int id) {
		try {					
			String className = this.getClass().getName();
			className=className.replace(".model.",".sql.");			
			SqlBase base = (SqlBase)Class.forName(className+"Sql").newInstance();			 
			return base.getQuery(id);
		} catch(Exception e) {
			return "Framework could not find Query. Please check the query path and query Number";
		}
	}

	public ServletContext getServletContext() {
		return context;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}
}