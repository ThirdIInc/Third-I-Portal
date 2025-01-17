package org.paradyne.sql.CR;

import org.paradyne.lib.SqlBase;

public class ManageClientDashBoardModelSql extends SqlBase {
	
	public String getQuery(int id){
		switch(id){
		
		case 1:
			return "select dashboard_id,dashboard_Caption,is_AccountApplicable,is_active from cr_dashboard";
		case 2:
			return "select NVL(MAX(cemcm.auto_id),0)+1 from cr_emp_client_mapp cemcm";
		case 3:
			return "insert into CR_EMP_CLIENT_MAPP(auto_id, user_id,user_type,Dashboard_id,account_code) values(?,?,?,?,?)";
		case 4:
			return " select heo.EMP_TOKEN,heo.EMP_FNAME||' '||heo.EMP_MNAME||' '||heo.EMP_LNAME,heo.emp_id  " +
				   " from hrms_emp_offc heo,CR_EMP_CLIENT_MAPP cecm " +
				   " where cecm.user_id=heo.emp_id  " +
				   " and cecm.user_type='I'" +
				   " and cecm.dashboard_id=? " +
				   " and nvl(cecm.account_code,0)=?";
		case 5:
			return "delete CR_EMP_CLIENT_MAPP where user_id=? " +
					" and CR_EMP_CLIENT_MAPP.user_type='I' " +
					 " and nvl(CR_EMP_CLIENT_MAPP.account_code,0)=? " +
					   " and CR_EMP_CLIENT_MAPP.dashBoard_ID=? ";
		case 6:	
			return  " select  CR_CLIENT_USERS.CRUSER_CODE,first_name||' '||last_name " +
					" FROM CR_CLIENT_USERS,cr_emp_client_mapp " +
					" where CR_CLIENT_USERS.IS_ACTIVE='Y' " +
					" and cr_emp_client_mapp.user_id=CR_CLIENT_USERS.CRUSER_CODE " +
					" and cr_emp_client_mapp.user_type='E' " +
					" and cr_emp_client_mapp.dashboard_id=? " +
					" and nvl(cr_emp_client_mapp.account_code,0)=?" ;
		case 7:
			return "delete CR_EMP_CLIENT_MAPP where user_id=?" +
				   " and CR_EMP_CLIENT_MAPP.user_type='E' " +
				   " and nvl(CR_EMP_CLIENT_MAPP.account_code,0)=? " +
				   " and CR_EMP_CLIENT_MAPP.dashBoard_ID=? ";
			
		case 8:
			return "select NVL(MAX(cda.auto_id),0)+1 from CR_DASHBOARD_ACCOUNT cda";
		case 9: 
			return "insert into CR_DASHBOARD_ACCOUNT(auto_id,dashboard_id,account_code) values(?,?,?)";	
			
		case 10: 
			return " select ccm.account_id, ccm.account_name , ccm.account_code " +
					" from CR_CLIENT_MASTER ccm, CR_DASHBOARD_ACCOUNT cda " +
					" where ccm.account_code=cda.account_code and cda.dashboard_id=? " +
					" and NVL((rtrim(ltrim(ccm.account_id)))+(rtrim(ltrim(ccm.account_name))),0) like ? " +
					" ORDER BY case when ccm.PARENT_FLAG='Y' then ccm.account_code*10000 else parent_code*10000+ccm.account_code end ";
		case 11:
			return "delete CR_DASHBOARD_ACCOUNT where account_code=?";
			
		case 12:	
			 return " select ccm.account_id, ccm.account_name , ccm.account_code " +
					" from CR_CLIENT_MASTER ccm, CR_DASHBOARD_ACCOUNT cda " +
					" where ccm.account_code=cda.account_code and cda.dashboard_id=? " +
					" ORDER BY case when ccm.PARENT_FLAG='Y' then ccm.account_code*10000 else parent_code*10000+ccm.account_code end ";
		
		case 13:
			return /*" SELECT DISTINCT A.PARAMETER_NAME FROM CR_COMPONENT_PARAMETER A " +
					" INNER JOIN CR_REPORT_CONFIG B ON B.COMPONENT_ID = A.COMPONENT_ID " +
					" INNER JOIN CR_DASHBOARD_REPORTS C ON B.REPORT_ID = C.REPORT_ID " +
					" WHERE C.DASHBOARD_ID= ? AND isnull(A.IS_HIDEINCONFIG,0) !=1   " +
					" UNION " +
					" SELECT DISTINCT D.PARAMETER_NAME FROM CR_COMPONENT_PARAMETER D " +
					" INNER JOIN CR_DASHBOARD_CONFIG E ON D.COMPONENT_ID = E.COMPONENT_ID " +
					" WHERE E.DASHBOARD_ID= ? AND isnull(D.IS_HIDEINCONFIG,0) !=1  " +
					"   UNION "+
					" SELECT DISTINCT D.PARAMETER_NAME FROM CR_COMPONENT_PARAMETER D " +
					" INNER JOIN CR_COMPONENT_MASTER C ON D.COMPONENT_ID = C.DRILLDOWN_COMPONENTS "+
					 " INNER JOIN CR_DASHBOARD_CONFIG E ON C.COMPONENT_ID = E.COMPONENT_ID "+
					 " WHERE E.DASHBOARD_ID= ? AND ISNULL(D.IS_HIDEINCONFIG,0) !=1 ";*/
					" SELECT DISTINCT A.PARAMETER_NAME FROM CR_COMPONENT_PARAMETER A  	" +
					" INNER JOIN CR_REPORT_CONFIG B ON B.COMPONENT_ID = A.COMPONENT_ID  " +
					" INNER JOIN CR_DASHBOARD_REPORTS C ON B.REPORT_ID = C.REPORT_ID  " +
					" WHERE C.DASHBOARD_ID= ? AND NVL(A.IS_HIDEINCONFIG,0) !=1    " +
					" UNION  " +
					" SELECT DISTINCT D.PARAMETER_NAME FROM CR_COMPONENT_PARAMETER D  	" +
					" INNER JOIN CR_DASHBOARD_CONFIG E ON D.COMPONENT_ID = E.COMPONENT_ID  	" +
					" WHERE E.DASHBOARD_ID= ? AND NVL(D.IS_HIDEINCONFIG,0) !=1   	" +
					" UNION  SELECT DISTINCT D.PARAMETER_NAME FROM CR_COMPONENT_PARAMETER D  " +
					" INNER JOIN CR_COMPONENT_drilldown C ON D.COMPONENT_ID = C.drilldown_component_id " +
					" INNER JOIN CR_DASHBOARD_CONFIG E ON C.COMPONENT_ID = E.COMPONENT_ID  " +
					" WHERE E.DASHBOARD_ID= ? AND NVL(D.IS_HIDEINCONFIG,0) !=1 ";
		
		case 14:
			return " SELECT A.REPORT_NAME,A.REPORT_ID FROM CR_REPORT_MASTER A" +
					" INNER JOIN CR_DASHBOARD_REPORTS B ON A.REPORT_ID = B.REPORT_ID " +
					" WHERE B.DASHBOARD_ID= ? ORDER BY B.SEQ";
			
		case 15:
			return "select  ccm.component_caption || '-' || " +
					" case when cdc.component_type ='G' then 'Graph' " +
					" else 'Data' end  , ccm.component_id , cdc.autoid " +
					" from cr_dashboard_config cdc, cr_component_master ccm  " +
					" where cdc.component_id=ccm.component_id  and cdc.dashboard_id= ?" +
					" order by matrix_row,matrix_col";
		/*
		 * For DashBoard Configuration
		 */	
		case 16:
			return "SELECT NVL(MAX(AUTO_ID),0)+1 FROM CR_USER_FILTER";
			
		case 17: 
			return  " INSERT INTO CR_USER_FILTER(AUTO_ID,"
				+ "DASHBOARD_ID,USER_ID,USER_TYPE,PARAMETER_NAME,DEFAULT_VALUE ) "
				+ " VALUES(?,?,?,?,?,?)"; 
			
		case 18:
			return "SELECT NVL(MAX(AUTO_ID),0)+1 FROM CR_USER_REPORT";
		
		case 19:
			return " INSERT INTO CR_USER_REPORT(AUTO_ID,DASHBOARD_ID,USER_ID," +
					"USER_TYPE,REPORT_ID) VALUES (?,?,?,?,?)";
				
		case 20:
			return "SELECT NVL(MAX(AUTO_ID),0)+1 FROM CR_USER_GRAPH";
			
		case 21:
			return "INSERT INTO CR_USER_GRAPH(AUTO_ID,DASHBOARD_ID,USER_ID,USER_TYPE,DASHBOARD_CONFIG_AUTOID)" +
					" VALUES " +
					"(?,?,?,?,?)";
			
		case 22:
			return "SELECT PARAMETER_NAME,DEFAULT_VALUE FROM CR_USER_FILTER WHERE DASHBOARD_ID=? AND USER_ID=? AND USER_TYPE=?";
			
		case 23:
			return "SELECT REPORT_ID FROM CR_USER_REPORT WHERE DASHBOARD_ID=? AND USER_ID=? AND USER_TYPE=?";
		
		case 24:
			return "SELECT DASHBOARD_CONFIG_AUTOID FROM CR_USER_GRAPH WHERE DASHBOARD_ID=? AND USER_ID=? AND USER_TYPE=?";
			
		case 25:
			return "DELETE FROM CR_USER_FILTER WHERE DASHBOARD_ID=? AND USER_ID=? AND USER_TYPE=?";
			
		case 26:
			return "DELETE FROM CR_USER_REPORT WHERE DASHBOARD_ID=? AND USER_ID=? AND USER_TYPE=?";
		
		case 27:
			return "DELETE FROM CR_USER_GRAPH WHERE DASHBOARD_ID=? AND USER_ID=? AND USER_TYPE=?";
			
				// for user Documents
		case 28:
			return "select File_name,autoid from  cr_dashboard_document where dashboard_id=?";
			
		case 29:
			return "  select document_autoid  from cr_user_document cud,cr_dashboard_document cdd" +
					" where cud.user_id= ? " +
					" and cud.user_type=? " +
					" and cdd.dashboard_id=? " +
					" and cdd.autoid=cud.document_autoid " +
					" order by auto_id";
			
		case 30:
			return " delete from cr_user_document       " +
					" where " +
					"(select  dashboard_id "+
					" from cr_dashboard_document "+
					" where document_autoid=autoid)=?" +
					" and USER_ID=?        " +
					" and user_type=? " ;
					
		case 31:
			return "SELECT NVL(MAX(AUTO_ID),0)+1 FROM cr_user_document";
			
		case 32:
			return "insert into cr_user_document(auto_id,user_id,user_type,document_autoid)values(?,?,?,?)";
			
		case 33:
			return " select rm.report_name,rm.report_id ,cd.dashboard_Caption,cd.menu_image " +
					" from cr_report_master rm, cr_dashboard_reports rd , cr_dashboard cd ,cr_user_report cur " +
					" where rm.report_id=rd.report_id " +
					" and cd.dashboard_id=rd.dashboard_id " +
					" and cur.dashboard_id=rd.dashboard_id " +
					" and rd.report_id = cur.report_id " +
					" and rd.dashboard_id=? " +
					" and cur.user_id=? " +
					" order by rd.seq";
			
			
		
		default:
			return "Framework could not the query number specified";
				
		}
	}

}
