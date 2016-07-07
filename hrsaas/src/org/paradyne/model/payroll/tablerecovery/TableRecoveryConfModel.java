package org.paradyne.model.payroll.tablerecovery;

import java.util.ArrayList;
import java.util.HashMap;

import org.paradyne.bean.common.ProfileBean;
import org.paradyne.bean.payroll.tablerecovery.*;
 import org.paradyne.lib.ModelBase;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Venkatesh
 *
 */
public class TableRecoveryConfModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	TableRecoveryConf tabRecConf=null;
	
	public void getRecord(TableRecoveryConf tabRecConf,HttpServletRequest request)
	{
		String query =  " select DEBIT_CODE,DEBIT_Name from HRMS_DEBIT_HEAD order by debit_code";

		Object[][] data = getSqlModel().getSingleResult(query);

		logger.info("data----------lenth-----------------------------=: "+data.length);
		HashMap afdata=new HashMap();

		ArrayList<Object> propList = new ArrayList<Object>();

		for(int i=0; i<data.length; i++) {	
			TableRecoveryConf bean1= new TableRecoveryConf();

			bean1.setDebitCode(String.valueOf(data[i][0]));

			bean1.setDebitAbbr(String.valueOf(data[i][1]));
			afdata.put(""+i,"Y");
			
			propList.add(bean1);
		}
		tabRecConf.setTabRecList(propList);
		request.setAttribute("data",afdata);


	}
	
	public boolean addRec(TableRecoveryConf tabRecConf,String debitCode)
	{
		String SQL="insert into hrms_recovery_conf (EMP_ID,REC_DEBIT_CODE) values("+tabRecConf.getEmpID()+","+debitCode+")" ;
		logger.info("In addrec Model -----------------------------------------------------------query="+SQL);
		logger.info("In addrec Model -----------------------------------------------------------Rank="+tabRecConf.getEmpName());
		boolean result=getSqlModel().singleExecute(SQL);
		return result;
	}
	
	public String[][] getCenterList(TableRecoveryConf tabRecConf){
		Object[][] centObj = getSqlModel().getSingleResult(" SELECT DEBIT_CODE,DEBIT_NAME FROM HRMS_DEBIT_HEAD ORDER BY DEBIT_CODE");
		Object[] profCode = new Object[1];
		profCode[0] = String.valueOf(tabRecConf.getEmpID()); 
		String recovaryStr = "SELECT REC_DEBIT_CODE,EMP_ID FROM HRMS_RECOVERY_CONF WHERE EMP_ID=? order by REC_DEBIT_CODE ";
		Object[][] centProfObj = getSqlModel().getSingleResult(recovaryStr,profCode);
		String[][]  centList ;
		if(centObj.length>0) {											/* Start 1st if-else condition */
		centList= new String[centObj.length][3];
			if(!String.valueOf(tabRecConf.getEmpID()).equals("")){		/* Start 2nd if-else(for profileCode check) condition */
				for (int i=0 ;i < centObj.length ;i++){					/* Start 1st for loop */
					centList[i][0] = String.valueOf(centObj[i][0]);
					centList[i][1] = String.valueOf(centObj[i][1]);
					centList[i][2] = "false";
						for (int j = 0; j < centProfObj.length; j++) {	/* Start 2nd for loop */
							if(String.valueOf(centProfObj[j][0]).equals(centList[i][0])){		/* Start 3rd if-else condition */
								logger.info("centList[i][0]---------------"+centList[i][0]+"---"+i);
								logger.info("centProfObj[j][1]---------------"+centProfObj[j][1]+"--"+j);
								centList[i][2] = "true";
								}									/* End 3rd if-else condition */
							}											/* End 2nd for loop */
					}													/* End 1st for loop */
			}else{
				for (int i=0 ;i < centObj.length ;i++){					
					centList[i][0] = String.valueOf(centObj[i][0]);
					centList[i][1] = String.valueOf(centObj[i][1]);
					centList[i][2] = "false";
					}		
					
			}															/* End 2nd(for profileCode check) if-else condition */
			
		}else{
			return centList = new String[0][0];
		}											/* End 1st if condition */
		return centList;
	}
	
	public void getEmpDet(TableRecoveryConf tabRecConf)
	{
		String query = "	SELECT HRMS_EMP_OFFC.EMP_TOKEN, "
			+ "	HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME," +
			   " nvl(HRMS_RANK.RANK_NAME,''),HRMS_CENTER.CENTER_ID||' - '||HRMS_CENTER.CENTER_NAME,HRMS_EMP_OFFC.EMP_ID "
			+ "	FROM HRMS_EMP_OFFC  "
			+ "	LEFT JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER " +
				" LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK " +
				" LEFT JOIN HRMS_TITLE ON HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE where HRMS_EMP_OFFC.emp_id="+tabRecConf.getEmpID() ;
		
		Object[][] data = getSqlModel().getSingleResult(query);
		
			tabRecConf.setEmpTokenNo(String.valueOf(data[0][0]));
			tabRecConf.setEmpName(String.valueOf(data[0][1]));
			tabRecConf.setDepartment(String.valueOf(data[0][2]));
			tabRecConf.setCenter(String.valueOf(data[0][3]));
		
		
	}

}
	