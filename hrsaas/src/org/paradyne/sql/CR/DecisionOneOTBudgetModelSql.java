/**
 * 
 */
package org.paradyne.sql.CR;

import org.paradyne.lib.SqlBase;

/**
 * @author vijay.gaikwad
 *
 */
public class DecisionOneOTBudgetModelSql extends SqlBase {

public String getQuery(int id){
		
		switch(id){
		
		case 1: 
			return "select totalbudget,no_of_days " +
					"from cr_otbudget " +
					"where monthyear=?";
		case 2:
			return "insert into cr_otmonthbudget(auto_id,monthyear,region_id,person_type,reporting_id,person_id," +
					"budget_percent,budget_amount,budget_daily,budget_weekly,person_name,reporting_name)values(?,?,?,?,?,?,?,?,?,?,?,?)";
		case 3:
			return "update cr_otmonthbudget   set auto_id=?, monthyear=?,  region_id=?, person_type=?, reporting_id=?,   person_id=?, budget_percent=?, budget_amount=?,   budget_daily=?, budget_weekly=?, person_name=?,reporting_name=?  where auto_id=?";
		
		case 4:
			return " select com.budget_amount,co.no_of_days " +
					"	from cr_otbudget co,cr_otmonthbudget com  					" +
					"  where co.monthyear=com.monthyear           " +
					" and com.person_id=?           " +
					"and co.monthyear=?           " +
					"and com.region_id=?";
			
			
			
		default:
			return "Framework could not the query number specified";	
		
		}
}
	
}
