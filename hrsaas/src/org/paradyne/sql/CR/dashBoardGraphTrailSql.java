package org.paradyne.sql.CR;

import org.paradyne.lib.SqlBase;

public class dashBoardGraphTrailSql extends SqlBase {
	
	
public String getQuery(int id){
		
		switch(id){
		// insert in into cr_component_master tables
		case 1:
			return "select ccm.component_query,ccm.component_caption,ccm.graph_type,ccm.drillDown_Components, enable_graph from  cr_component_master ccm where  ccm.component_id = ?";
		
		case 2: return "select parameter_name, is_default from cr_component_parameter where component_id= ? ";	
		
		default:
			return "Framework could not the query number specified";
		}
}
	
}