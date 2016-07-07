package org.paradyne.sql.CR;

import org.paradyne.lib.SqlBase;

public class DashBoardModelSql extends SqlBase {


public String getQuery(int id){
		
		switch(id){
					
		
			/*
			 * for getting DashBoard Report Menu
			 */
		case 2:
			return  " select rm.report_name,rm.report_id ,cd.dashboard_Caption,cd.menu_image  " +
					" from cr_report_master rm, cr_dashboard_reports rd , cr_dashboard cd ,cr_user_report cur " +
					" where rm.report_id=rd.report_id  " +
					" and cd.dashboard_id=rd.dashboard_id  " +
					" and cur.dashboard_id=rd.dashboard_id " +
					" and rd.report_id = cur.report_id " +
					" and rd.dashboard_id=?  " +
					" and cur.user_id=? " +
					" and cur.user_type=? " +
					" order by rd.seq ";
			
			/*
			 * DrilDown Component
			 */
		case 4:
			return /*" select ccm.component_query,ccm.component_caption, " +
					" isnull(cdc.graph_type,ccm.graph_type) graph_type,    " +
					" ccd.drillDown_component_id, enable_graph,enable_data,default_parameter " +
					" from  cr_component_master ccm  " +
					" left join cr_dashboard_config cdc on  cdc.component_id = ccm.component_id " +
					" and cdc.autoid = ?  " +
					" ,CR_COMPONENT_drilldown ccd  " +
					" where  ccm.component_id = ?  " +
					" and ccd.component_id = ccm.component_id";*/
					
			" select ccm.component_id,'',ccm.component_query,ccm.component_caption,     " +
			" NVL(cdc.graph_type,ccm.graph_type) graph_type,     " +
			" ccm.drillDown_Components, enable_graph,enable_data," +
			" default_parameter,graph_x_columns,graph_y_columns, " +
			" ccm.isexternal ,    " +
			" ccm.componenturl, " +
			" ccm.dbname " +
			" from  cr_component_master ccm      " +
			" left join cr_dashboard_config cdc on  cdc.component_id = ccm.component_id " +
			" and cdc.autoid=?    " +
			" where  ccm.component_id = ?";
			
			/*
			 *  For Getting DashBoard number of DashBoar Component and its capations
			 */
		case 5:
	
			//new skip 1 ,6
			return " select ccm.component_id,  " +
					"  cdc.component_type, 	" +
					"  ccm.component_query, " +
					"  ccm.component_caption,  " +
					"  cdc.graph_type, " +
					"  cdc.autoid ," +
					"  cdc.matrix_row,cdc.matrix_col, 	" +
					"  cdc.colspan, " +
					"  cug.dashboard_config_autoid, " +
					"  d.dashboard_rows, " +
					"  d.dashboard_cols, " +
					"  ccm.isexternal, " +
					"  ccm.componenturl , " +
					"  drillDown_Components , " +
					"  ccm.graph_x_columns,  " +
					"  ccm.graph_y_columns, " +
					"  ccm.dbname " +
					"  from cr_dashboard_config cdc " +
					" left join cr_user_graph cug on cdc.autoid=cug.dashboard_config_autoid, " +
					" cr_component_master ccm, " +
					" cr_dashboard d  		" +
					" where cdc.component_id=ccm.component_id " +
					" and cug.dashboard_id=cdc.dashboard_id  " +
					" and cdc.autoid=cug.dashboard_config_autoid  	" +
					" and cdc.dashboard_id = d.dashboard_id  " +
					" and cdc.dashboard_id= ? 	" +
					" and cug.user_id= ?  " +
					" and cug.user_type= ? " +
					" order by matrix_row,matrix_col";
			
			
			/*
			 *  For Getting Report Parameters
			 */
		case 7:
			return " select distinct vcr.parameter_name,vcr.parameter_caption,vcr.parameter_type," +
					"  vcr.parameter_default,vcr.parameter_query,vcr.report_id,vcr.is_mandatory,vcr.is_hidden , " +
					" cuf.default_value ,cdr.isDataInternal,cdr.dataURL " +
					" from v_cr_report_parameter vcr  left join cr_user_filter cuf  on  vcr.parameter_name=cuf.parameter_name  " +
					"  and cuf.user_id=?  and cuf.user_type=? and cuf.parameter_name=vcr.parameter_name and cuf.dashboard_id=? " +
					" left join cr_dashboard_reports cdr on vcr.report_id = cdr.report_id where vcr.report_id=?";
			
			/*
			 *  For Drill Down Purpose For next parameter of Drill down
			 */
		case 8:	
			return "select parameter_name from cr_component_parameter where component_id= ? ";
			
			/*
			 * For DashBoard Log 
			 */
		case 9:
			return "insert into cr_access_log(autoID,EmpID,ClientID,UserType,IP,Port,dashBoardID,reportID,graphID,isemail) values(?,?,?,?,?,?,?,?,?,?)";
			
			/*
			 * For DashBoard Configuration Parameters
			 */
		case 10:
			return
					" SELECT a.parameter_name,   " +
					" default_value,a.parameter_caption FROM   " +
					" ( SELECT DISTINCT A.PARAMETER_NAME,A.parameter_caption   " +
					" FROM CR_COMPONENT_PARAMETER A   " +
					" INNER JOIN CR_REPORT_CONFIG B   " +
					" ON B.COMPONENT_ID = A.COMPONENT_ID   " +
					" INNER JOIN CR_DASHBOARD_REPORTS C   " +
					" ON B.REPORT_ID      = C.REPORT_ID   " +
					" WHERE C.DASHBOARD_ID= ?   " +
					" UNION   SELECT DISTINCT D.PARAMETER_NAME,D.parameter_caption   FROM CR_COMPONENT_PARAMETER D   " +
					" INNER JOIN CR_DASHBOARD_CONFIG E   ON D.COMPONENT_ID   = E.COMPONENT_ID   WHERE E.DASHBOARD_ID= ?   " +
					" UNION   SELECT DISTINCT D.PARAMETER_NAME,D.parameter_caption   FROM CR_COMPONENT_PARAMETER D   " +
					" INNER JOIN CR_COMPONENT_drilldown C   ON D.COMPONENT_ID = C.drilldown_component_id   " +
					" INNER JOIN CR_DASHBOARD_CONFIG E   ON C.COMPONENT_ID   = E.COMPONENT_ID   WHERE E.DASHBOARD_ID= ?   ) a " +
					" LEFT JOIN   (SELECT cuf.parameter_name,     default_value   FROM cr_user_filter cuf   " +
					" WHERE cuf.dashboard_id=?   AND cuf.user_id       =?   AND cuf.user_type     =?   ) b " +
					" ON a.parameter_name=b.parameter_name";
		case 11:
			return " select file_name ,file_location  " +
					" from cr_dashboard_document cdd ,cr_user_document cud  " +
					" where  cud.user_id=? " +
					" and cud.user_type=? " +
					" and cdd.dashboard_id=? " +
					" and cud.document_autoid=cdd.autoid";
			
		case 12:
			return " select crc.sheet_num, crc.sheet_caption, crc.matrix_location, " +
				   " crc.component_id,crc.component_type,ccm.component_query, " +
				   " crc.column_width,crc.graph_type, ccm.component_mode, " +
				   " ccm.component_caption,ccm.dbname " +
				   " from cr_report_config crc, cr_component_master ccm  " +
				   " where  crc.component_id=ccm.component_id  " +
				   " and report_id=?" +
				   " order by crc.sheet_num,crc.matrix_location";
		case 13:
			return " select cs.scenario_id, cs.scenario_name, cs.component_id ,'', " +
					" ccm.component_caption from cr_scenario cs,cr_component_master ccm " +
					" where cs.component_id = ccm.component_id " +
					" and cs.dashboard_id= ?";
		
		case 14:
			return " select cs.scenario_id, cs.scenario_name, cs.component_id , " +
				   "  ccm.component_caption, ccm.enable_data,ccm.enable_graph, " +
				   "  ccm.graph_type graphtype ,ccm.graph_x_columns,ccm.graph_y_columns ,0 auto_id " +
				   "  from  cr_scenario cs     " +
				   "  inner join cr_component_master ccm  on cs.component_id = ccm.component_id   " +
				   " INNER JOIN CR_USER_SCENARIO CUS ON CUS.SCENARIO_ID=CS.SCENARIO_ID  " +
				   " where " +
				   "  cs.scenario_id=?" +
				   "  and cs.dashboard_id= ?" +
				   "  and cuS.user_id= ?" +
				   " and cuS.user_type= ?";	
			
			
		default:
			return "Framework could not the query number specified";
				
		}

}
}
