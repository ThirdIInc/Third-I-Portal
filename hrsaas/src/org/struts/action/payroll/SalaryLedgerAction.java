package org.struts.action.payroll;

import org.paradyne.bean.payroll.SalaryLedger;

import org.paradyne.model.payroll.SalaryLedgerModel;
//Author:Pradeep Kumar Sahoo
//Date:02-08-2007
public class SalaryLedgerAction extends org.struts.lib.ParaActionSupport {
	
	SalaryLedger sl =null;
	
	 public Object getModel() {
		// TODO Auto-generated method stub
		return sl;
	}

	public SalaryLedger getSl() {
		return sl;
	}

	public void setSl(SalaryLedger sl) {
		this.sl = sl;
	}
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	
	public void prepare_local() throws Exception {
		sl=new SalaryLedger();
		sl.setMenuCode(271);
	}
	
	public String reset() {
		sl.setEmpType("");
		sl.setMonth("");
		sl.setYear("");
		sl.setPayGrp("");
		sl.setStatus("");
		
		return "success";
	}
	
	public String go() {
		SalaryLedgerModel model=new SalaryLedgerModel();
		model.initiate(context,session);
		String sql="SELECT HRMS_EMP_OFFC.EMP_TYPE,HRMS_EMP_OFFC.EMP_PAYBILL,HRMS_PAYBILL.PAYBILL_GROUP,HRMS_EMP_TYPE.TYPE_NAME"
			+" FROM HRMS_EMP_OFFC "
			
			+" INNER JOIN HRMS_PAYBILL ON(HRMS_PAYBILL.PAYBILL_ID=HRMS_EMP_OFFC.EMP_PAYBILL)"
			+" INNER JOIN HRMS_EMP_TYPE ON(HRMS_EMP_TYPE.TYPE_ID=HRMS_EMP_OFFC.EMP_TYPE)";
		//	+" WHERE HRMS_EMP_OFFC.EMP_PAYBILL IS NOT NULL AND HRMS_EMP_TYPE.TYPE_ID IN(1,2,3,5) ";
		
		sql +=getprofileQuery(sl);
		if ( sl.getUserProfilePaybill() !=null && sl.getUserProfilePaybill().length() > 0) {
			if ( sl.getUserProfileEmpType().length() > 0 || sl.getUserProfileCenters().length() > 0) {
				sql += " AND ";
			}else{
				sql += " WHERE ";
			}
			sql += " HRMS_EMP_OFFC.EMP_PAYBILL IN ("
				+ sl.getUserProfilePaybill() + ")";
		}
		sql	+=" GROUP BY HRMS_EMP_OFFC.EMP_PAYBILL,HRMS_EMP_OFFC.EMP_TYPE,HRMS_PAYBILL.PAYBILL_GROUP ,HRMS_EMP_TYPE.TYPE_NAME "
			+" ORDER BY HRMS_EMP_OFFC.EMP_TYPE  ";
 
		model.getSalDetails(sl,sql);
		model.terminate();
		
		return "success";
		
		
	}
	

   }
