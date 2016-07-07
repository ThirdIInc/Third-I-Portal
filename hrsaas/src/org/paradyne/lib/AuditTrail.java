package org.paradyne.lib;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Vishwambhar
 * 
 */
public class AuditTrail extends ModelBase {

	String tableName;
	String effectUser;
	String[] fldNames;
	String[] parameters;
	String[] parameterValues;
	Object[][] oldVal;
	Object[][] newVal;
	String userCode;
	String comments = "";

	public AuditTrail(String userCode) {
		try {
			this.userCode = userCode;
		} catch (Exception e) {
			System.out.println("Exception in AuditTrail().." + e);

		}
	}

	public void setParameters(String tableName, String[] parameters,
			String parameterValues[], String effectUser) {
		try {
			this.tableName = tableName;
			this.effectUser = effectUser;
			this.parameters = parameters;
			this.parameterValues = parameterValues;
			this.fldNames = getFieldNames(tableName);
		} catch (Exception e) {
			System.out.println("Exception in setParameters().." + e);

		}
	}

	public String[] getFieldNames(String tableName) {
		String[] fldNames = null;
		try {
			String query = "";
			/*
			 * if(model.getOracleFlag()) { query = " SELECT COLUMN_NAME FROM
			 * USER_TAB_COLUMNS WHERE TABLE_NAME = '"+tableName+"' "; }else{
			 * query = " DESC "+tableName; }
			 */
			query = " SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '"
					+ tableName + "' ";
			Object[][] obj = getSqlModel().getSingleResult(query,
					new Object[] {});
			if (null != obj && obj.length > 0) {
				fldNames = new String[obj.length];
				for (int i = 0; i < obj.length; i++) {
					fldNames[i] = String.valueOf(obj[i][0]);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in getFieldNames().." + e);
		}
		return fldNames;
	}

	public String createSelectQuery() {
		String query = "";
		try {
			query += " SELECT ";
			for (int i = 0; i < fldNames.length; i++) {
				query += tableName + "." + fldNames[i] + ",";
			}
			query = query.substring(0, query.length() - 1);
			query += " FROM " + tableName + " WHERE ";
			for (int i = 0; i < parameters.length; i++) {
				query += parameters[i] + "=" + parameterValues[i] + " AND ";
			}
			query = query.substring(0, query.length() - 4);

		} catch (Exception e) {
			System.out.println("Exception in createSelectQuery().." + e);

		}

		return query;
	}

	/**
	 * CREATE INSERT QUERY FOR AUDIT
	 * 
	 */
	public String createInsertQuery(HttpServletRequest request) {
		String query = "";

		String ipAddress = request.getRemoteAddr();
		String ipAddress1 = ipAddress.replace(".", "#");
		String[] strSpiltIpAdd = ipAddress1.split("#");

		try {
			query += " INSERT INTO HRMS_AUDIT_TRAIL ";
			query += " (TRAIL_TABLENAME,TRAIL_FIELDNAME,TRAIL_OLDVAL,TRAIL_NEWVAL,TRAIL_DATE,TRAIL_ACTIONTYPE,TRAIL_USERCODE,TRAIL_CHANGE_EMPID,TRAIL_IP_ADDR1, TRAIL_IP_ADDR2, TRAIL_IP_ADDR3, TRAIL_IP_ADDR4,TRAIL_COMMENTS) ";
			query += " VALUES (?,?,?,?,TO_DATE(TO_CHAR(SYSDATE,'DD-MM-YYYY'),'DD-MM-YYYY'),?,?,?,'"
					+ strSpiltIpAdd[0]
					+ "','"
					+ strSpiltIpAdd[1]
					+ "','"
					+ strSpiltIpAdd[2]
					+ "','"
					+ strSpiltIpAdd[3]
					+ "','"
					+ getComments() + "' ) ";

			/*
			 * if(model.getOracleFlag()) { query += " INSERT INTO
			 * CART_AUDIT_TRAIL "; query += "
			 * (TRAIL_CODE,TRAIL_TABLENAME,TRAIL_FIELDNAME,TRAIL_OLDVAL,TRAIL_NEWVAL,TRAIL_DATE,TRAIL_ACTIONTYPE,TRAIL_USERCODE) ";
			 * query += " VALUES (SEQ_TRAIL.NEXTVAL,?,?,?,?,SYSDATE,?,?) "; }
			 * else { query += " INSERT INTO CART_AUDIT_TRAIL "; query += "
			 * (TRAIL_TABLENAME,TRAIL_FIELDNAME,TRAIL_OLDVAL,TRAIL_NEWVAL,TRAIL_DATE,TRAIL_ACTIONTYPE,TRIAL_USERCODE) ";
			 * query += " VALUES (?,?,?,?,NOW(),?,?) "; }
			 */
		} catch (Exception e) {
			System.out.println("Exception in createInsertQuery().." + e);

		}
		return query;
	}

	/**
	 * TO BE CALLED JUST BEFORE THE MODIFICATION
	 */
	public void beginTrail() {
		oldVal = getOldValues();
	}

	/**
	 * TO BE CALLED JUST AFTER MODIFICATION
	 * 
	 */
	public void executeMODTrail(HttpServletRequest request) {

		newVal = getNewValues();
		Object[][] insertObj = getFinalModObject(oldVal, newVal);

		try {
			boolean res = getSqlModel().singleExecute(
					createInsertQuery(request), insertObj);
			System.out.println("result.........." + res);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			executeADDTrail(request);

		}

	}

	public void executeDELADDTrail(HttpServletRequest request) {
		// TODO Auto-generated method stub
		newVal = getNewValues();
		// oldVal=getOldValues();
		Object[][] insertObj = null;
		try {
			if (oldVal != null && oldVal.length > 0) {
				insertObj = getFinalModObject(oldVal, newVal);
				System.out.println("old....new");
			} else {
				insertObj = getFinalAddObject(getNewValues());
				System.out.println("(blank)....new");
			}
			for (int i = 0; i < insertObj.length; i++) {
				for (int j = 0; j < insertObj[0].length; j++) {
					System.out.println("insertObj::::::::" + insertObj[i][j]);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			getSqlModel().singleExecute(createInsertQuery(request), insertObj);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * TO BE CALLED JUST AFTER ADDITION
	 * 
	 */
	public void executeADDTrail(HttpServletRequest request) {
		Object[][] insertObj = getFinalAddObject(getNewValues());
		for (int i = 0; i < insertObj.length; i++) {
			for (int j = 0; j < insertObj[0].length; j++) {
				System.out.println("insertObj::::::::" + insertObj[i][j]);
			}

		}
		getSqlModel().singleExecute(createInsertQuery(request), insertObj);
	}

	/**
	 * TO BE CALLED JUST AFTER DELETION
	 * 
	 */
	public void executeDELTrail(HttpServletRequest request) {
		try {
			Object[][] insertObj = getFinalDelObject(getNewValues());
			for (int i = 0; i < insertObj.length; i++) {
				for (int j = 0; j < insertObj[0].length; j++) {
					System.out.println("insertObj::::::::" + insertObj[i][j]);
				}

			}

			getSqlModel().singleExecute(createInsertQuery(request), insertObj);
		} catch (Exception e) {
			System.out.println("Exception in executeDELTrail().." + e);
		}
	}

	public Object[][] getOldValues() {
		try {
			Object[][] obj = getSqlModel().getSingleResult(createSelectQuery(),
					new Object[] {});
			return obj;
		} catch (Exception e) {
			System.out.println("error in old values" + e);
		}
		return new Object[0][0];
	}

	public Object[][] getNewValues() {

		Object[][] obj = getSqlModel().getSingleResult(createSelectQuery(),
				new Object[] {});
		System.out.println("obj           :" + obj.length);
		return obj;
	}

	/**
	 * CREATE FINAL MOD OBJECT
	 */
	public Object[][] getFinalModObject(Object[][] obj1, Object[][] obj2) {
		Object[][] finalObj = null;
		try {
			int k = 0;
			Vector mainV = new Vector();
			for (int i = 0; i < obj1.length; i++) {
				for (int j = 0; j < obj1[0].length; j++) {
					if (!String.valueOf(obj1[i][j]).equals(
							String.valueOf(obj2[i][j]))) {
						Vector innerV = new Vector();
						innerV.add(0, fldNames[j]);
						innerV.add(1, obj1[i][j]);
						innerV.add(2, obj2[i][j]);
						mainV.add(innerV);
						k++;
					}
				}
			}

			finalObj = new Object[k][7];
			for (int i = 0; i < k; i++) {
				Vector v = (Vector) mainV.get(i);
				finalObj[i][0] = tableName;
				finalObj[i][1] = v.get(0);
				finalObj[i][2] = v.get(1);
				finalObj[i][3] = v.get(2);
				finalObj[i][4] = "MOD";
				finalObj[i][5] = userCode;
				finalObj[i][6] = effectUser;
			}
		} catch (Exception e) {
			System.out.println("Exception in getFinalModObject().." + e);

		}
		return finalObj;
	}

	/**
	 * CREATE FINAL ADD OBJECT
	 * 
	 */
	public Object[][] getFinalAddObject(Object[][] obj) {
		Object[][] returnObj = null;
		try {

			int counter = 0;
			Vector mainV = new Vector();
			for (int i = 0; i < obj.length; i++) {
				for (int j = 0; j < obj[0].length; j++) {
					if (String.valueOf(obj[i][j]) != null
							&& !String.valueOf(obj[i][j]).equals("")
							&& String.valueOf(obj[i][j]) != "null") {
						Vector innerV = new Vector();
						innerV.add(0, fldNames[j]);
						innerV.add(1, obj[i][j]);
						mainV.add(innerV);

						counter++;
					}
				}
			}
			returnObj = new Object[counter][7];
			for (int i = 0; i < counter; i++) {
				Vector v = (Vector) mainV.get(i);
				returnObj[i][0] = tableName;
				returnObj[i][1] = v.get(0);
				returnObj[i][2] = "";
				returnObj[i][3] = v.get(1);
				returnObj[i][4] = "ADD";
				returnObj[i][5] = userCode;
				returnObj[i][6] = effectUser;
			}
		} catch (Exception e) {
			System.out.println("Exception in getFinalAddObject().." + e);
		}
		return returnObj;
	}

	/**
	 * CREATE FINAL DEL OBJECT
	 * 
	 */
	public Object[][] getFinalDelObject(Object[][] obj1) {
		Object[][] returnObj = null;
		try {

			// returnObj = new Object[fldNames.length*obj1.length][7];
			int counter = 0;
			Vector mainV = new Vector();
			for (int i = 0; i < obj1.length; i++) {
				for (int j = 0; j < obj1[0].length; j++) {
					if (String.valueOf(obj1[i][j]) != null
							&& !String.valueOf(obj1[i][j]).equals("")
							&& String.valueOf(obj1[i][j]) != "null") {
						Vector innerV = new Vector();
						innerV.add(0, fldNames[j]);
						innerV.add(1, obj1[i][j]);
						mainV.add(innerV);

						counter++;
					}

				}

			}
			returnObj = new Object[counter][7];
			for (int i = 0; i < counter; i++) {
				Vector v = (Vector) mainV.get(i);
				returnObj[i][0] = tableName;
				returnObj[i][1] = v.get(0);
				returnObj[i][2] = v.get(1);
				returnObj[i][3] = "";
				returnObj[i][4] = "DEL";
				returnObj[i][5] = userCode;
				returnObj[i][6] = effectUser;
			}
		} catch (Exception e) {
			System.out.println("Exception in getFinalDelObject().." + e);
		}
		return returnObj;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
