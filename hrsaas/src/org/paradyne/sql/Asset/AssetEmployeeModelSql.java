/**
 * 
 */
package org.paradyne.sql.Asset;

import org.paradyne.lib.SqlBase;

/**
 * @author mangeshj
 *
 */
public class AssetEmployeeModelSql extends SqlBase {
	public String getQuery(int id){
		switch (id){
		/*case 1: return "INSERT INTO HRMS_ASSMT (ASSMT_CODE,EMP_CODE) VALUES(?,?)";*/
		case 1 : return "INSERT INTO HRMS_ASSET_APPLICATION(ASSET_APPL_CODE,ASSET_EMP_ID,ASSET_APPL_DATE, "
						+" ASSET_APPL_LEVEL,ASSET_APPL_APPROVER,ASSET_STATUS,ASSET_COMMENTS,ASSET_ALTER_APPROVER,ASSET_DRAFT_BY) VALUES(?,?,TO_DATE(?,'DD-MM-YYYY'),1,?,?,?,?,?)";
		
		/*case 2: return "INSERT INTO HRMS_ASST_ASSMT (ASSMT_CODE,ASST_CODE,ASSIGN_DATE,RETURN_DATE,RETURN_FLAG)"
			+" VALUES (?,?,TO_DATE(?,'DD-MM-YYYY'),TO_DATE(?,'DD-MM-YYYY'),?)";*/
		case 2 : return "INSERT INTO HRMS_ASSET_APPL_DETAILS (ASSET_APPL_CODE,ASSET_CATEGORY_CODE,ASSET_SUBTYPE_CODE,ASSET_APPL_QTY,ASSET_ASSIGN_QTY)" 
					+" VALUES (?,?,?,?,0)";
		
		case 3: return "UPDATE HRMS_ASST_ASSMT SET RETURN_FLAG=? WHERE ASST_CODE=? AND ASSMT_CODE=? ";
		
		case 4: return "UPDATE HRMS_ASST_MT SET ASST_STATUS=? WHERE ASST_CODE=? ";
		
		/*case 5 :return "UPDATE HRMS_ASSMT SET EMP_CODE=? WHERE ASSMT_CODE=? ";*/
		
		case 5 : return "UPDATE HRMS_ASSET_APPLICATION SET ASSET_EMP_ID=?,ASSET_APPL_APPROVER=?,ASSET_STATUS=?,ASSET_COMMENTS=?,ASSET_ALTER_APPROVER=? WHERE ASSET_APPL_CODE =?";
		
		case 6 :return "SELECT HRMS_ASST_ASSMT.ASST_CODE,ASST_INCODE ,ASST_TYPE ,TO_CHAR(ASSIGN_DATE,'DD-MM-YYYY'),TO_CHAR(RETURN_DATE,'DD-MM-YYYY')" 
						+ " FROM HRMS_ASST_ASSMT"
						+ " INNER JOIN HRMS_ASST_MT ON(HRMS_ASST_MT.ASST_CODE=HRMS_ASST_ASSMT.ASST_CODE)" 
						+" INNER JOIN HRMS_ASST_TYPE ON(HRMS_ASST_TYPE.ASST_TYPE_CODE=HRMS_ASST_MT.ASST_HD_CODE) " 
						+" WHERE HRMS_ASST_ASSMT.ASSMT_CODE=? AND HRMS_ASST_ASSMT.ASST_CODE= ? ";
		
		case 7 :return "DELETE FROM HRMS_ASSET_APPLICATION WHERE ASSET_APPL_CODE=? ";
		
		/*case 8 :return "DELETE FROM HRMS_ASST_ASSMT WHERE ASSMT_CODE=? ";*/
		case 8 : return "DELETE FROM HRMS_ASSET_APPL_DETAILS WHERE ASSET_APPL_CODE=? AND ASSET_ASSIGN_QTY=0";
		
		case 9: return "UPDATE HRMS_ASST_ASSMT SET ASST_CODE=?,ASSIGN_DATE=TO_DATE(?,'DD-MM-YYYY'),RETURN_DATE=TO_DATE(?,'DD-MM-YYYY') WHERE ASSMT_CODE=? AND ASST_CODE=?";
		
		case 10: return" SELECT * FROM HRMS_ASST_ASSMT WHERE ASSMT_CODE=? AND ASST_CODE=? ";
		
		case 11 : return " SELECT (HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),NVL(ASSET_APPL_COMMENTS,''),TO_CHAR(ASSET_APPR_DATE,'DD-MM-YYYY'), "
						+" DECODE(ASSET_PATH_STATUS,'P','Pending','R','Rejected','A','Approved','B','SentBack')  FROM HRMS_ASSET_PATH "
						+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_PATH.ASSET_APPROVER) "
						+" WHERE ASSET_APPL_CODE =? ORDER BY ASSET_PATH_ID";
		
		case 12 : return "SELECT HRMS_ASSET_APPL_DETAILS.ASSET_CATEGORY_CODE,ASSET_CATEGORY_NAME,ASSET_SUBTYPE_CODE,ASSET_SUBTYPE_NAME,(ASSET_APPL_QTY-ASSET_ASSIGN_QTY), "
						+" ASSET_INVENTORY_TYPE,ASSET_APPL_CODE FROM HRMS_ASSET_APPL_DETAILS "
						+" INNER JOIN HRMS_ASSET_CATEGORY ON(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASSET_APPL_DETAILS.ASSET_CATEGORY_CODE) "
						+" LEFT JOIN HRMS_ASSET_SUBTYPE ON(HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_APPL_DETAILS.ASSET_SUBTYPE_CODE)"
						+" WHERE ASSET_APPL_CODE =? AND (ASSET_APPL_QTY-ASSET_ASSIGN_QTY)>0";
		
		case 13 : return "INSERT INTO HRMS_ASSET_APP_ASSIGNEMENT(ASSET_APPL_CODE,ASSET_CATEGORY_CODE,ASSET_SUBTYPE_CODE, "
						+" ASSET_INVENTORY_CODE,ASSET_MASTER_CODE,ASSET_RETURN_FLAG,ASSET_ASSIGNMENT_DATE,ASSET_ASSIGNED_QUANTITY,ASSET_EMP_ID,ASSET_VENDOR_ID,ASSET_OWNED_BY,ASSET_ITEM_CODE) VALUES (?,?,?,?,?,'N',TO_DATE(?,'DD-MM-YYYY'),?,?,0,'E',?) ";
		
		case 14 : return "UPDATE HRMS_ASSET_MASTER SET ASSET_ASSIGNED = ASSET_ASSIGNED + ?, ASSET_AVAILABLE=ASSET_AVAILABLE - ?,ASSET_STATUS = 'A' WHERE ASSET_MASTER_CODE = ?";
		
		case 15 : return "UPDATE HRMS_ASSET_MASTER SET ASSET_ASSIGNED = ASSET_ASSIGNED + ?, ASSET_AVAILABLE=ASSET_AVAILABLE - ? WHERE ASSET_MASTER_CODE = ?";
		
		case 16 : return "UPDATE HRMS_ASSET_MASTER SET ASSET_STATUS = 'A' WHERE ASSET_MASTER_CODE = ?";
		
		case 17 : return "SELECT WAREHOUSE_RESPONSIBLE_PERSON FROM HRMS_WAREHOUSE_BRANCH "
						+" INNER JOIN HRMS_WAREHOUSE_MASTER ON(HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE=HRMS_WAREHOUSE_BRANCH.WAREHOUSE_CODE) "
						+" WHERE WAREHOUSE_BRANCH = ?";
		
		case 18 : return "UPDATE HRMS_ASSET_APPL_DETAILS SET ASSET_ASSIGN_QTY=ASSET_ASSIGN_QTY + ? WHERE ASSET_APPL_CODE=? AND ASSET_CATEGORY_CODE=? AND ASSET_SUBTYPE_CODE=?" ;
		
		case 19 : return "UPDATE HRMS_ASSET_MASTER_DTL SET ASSET_ASSISGN_FLAG='A' WHERE ASSET_MASTER_CODE =? AND ASSET_INVENTORY_CODE=? AND ASSET_WAREHOUSE_CODE=?";
				
		case 20 : return "UPDATE HRMS_ASSET_MASTER_DTL SET ASSET_AVAILABLE=ASSET_AVAILABLE- ? WHERE ASSET_MASTER_CODE=? AND ASSET_INVENTORY_CODE=? AND ASSET_WAREHOUSE_CODE IN(?)";
		
		case 21 :return "INSERT INTO HRMS_ASSET_WAREHOUSE_REQ(ASSET_DESTI_WAREHOUSE,ASSET_SOURCE_WAREHOUSE,ASSET_REQ_MASTERCODE, "
						+" ASSET_REQ_INVENTORY,ASSET_QUANTITY_REQUIRED,ASSET_REQUEST_CODE,ASSET_REQ_DATE,ASSET_QUANTITY_ASSIGNED) "
						+" VALUES (?,?,?,?,?,(SELECT NVL(MAX(ASSET_REQUEST_CODE),0)+1 FROM HRMS_ASSET_WAREHOUSE_REQ),TO_DATE(SYSDATE,'DD-MM-YYYY'),0)";
		
		case 22 : return "UPDATE HRMS_ASSET_MASTER_DTL SET ASSET_AVAILABLE=ASSET_AVAILABLE- ? WHERE ASSET_MASTER_CODE=? AND ASSET_INVENTORY_CODE=?";
		
		case 23 : return "INSERT INTO HRMS_ASSET_APPL_DETAILS (ASSET_APPL_CODE,ASSET_CATEGORY_CODE,ASSET_SUBTYPE_CODE,ASSET_APPL_QTY,ASSET_ASSIGN_QTY)" 
						+" VALUES (?,?,?,?,?)";
		
		case 24 : return "UPDATE HRMS_ASSET_APPLICATION SET ASSET_STATUS=? WHERE ASSET_APPL_CODE =?";
		
		default : return "Framework could not the query number specified";
		}
	}

}
