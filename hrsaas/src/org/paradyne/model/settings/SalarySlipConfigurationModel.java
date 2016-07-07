package org.paradyne.model.settings;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.settings.SalarySlipConfiguration;
import org.paradyne.lib.ModelBase;
import org.struts.action.common.LoginAction;

public class SalarySlipConfigurationModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(SalarySlipConfigurationModel.class);

	public boolean saveSettings(SalarySlipConfiguration salarySlipBean) {
		boolean result = false;
		
		try {
			Object[][] salarySlipObj = new Object[1][19];
			
			if (salarySlipBean.getBranchChk().equals("true")) {
				salarySlipObj[0][0] = "Y";
			}else{
				salarySlipObj[0][0] = "N";
			}
			if (salarySlipBean.getDepartmentChk().equals("true")) {
				salarySlipObj[0][1] = "Y";
			}else{
				salarySlipObj[0][1] = "N";
			}
			if (salarySlipBean.getDesignationChk().equals("true")) {
				salarySlipObj[0][2] = "Y";
			}else{
				salarySlipObj[0][2] = "N";
			}
			if (salarySlipBean.getEmpTypeChk().equals("true")) {
				salarySlipObj[0][3] = "Y";
			}else{
				salarySlipObj[0][3] = "N";
			}
			if (salarySlipBean.getSalaryGradeChk().equals("true")) {
				salarySlipObj[0][4] = "Y";
			}else{
				salarySlipObj[0][4] = "N";
			}
			if (salarySlipBean.getBankIdChk().equals("true")) {
				salarySlipObj[0][5] = "Y";
			}else{
				salarySlipObj[0][5] = "N";
			}
			if (salarySlipBean.getAccountNoChk().equals("true")) {
				salarySlipObj[0][6] = "Y";
			}else{
				salarySlipObj[0][6] = "N";
			}
			if (salarySlipBean.getAttendanceDetailChk().equals("true")) {
				salarySlipObj[0][7] = "Y";
			}else{
				salarySlipObj[0][7] = "N";
			}
			if (salarySlipBean.getLeaveDetailChk().equals("true")) {
				salarySlipObj[0][8] = "Y";
			}else{
				salarySlipObj[0][8] = "N";
			}
			if (salarySlipBean.getSalHeader().equals("Company")) {
				salarySlipObj[0][9] = "C";
			}else{
				salarySlipObj[0][9] = "D";
			}
			if (salarySlipBean.getShowLogoChk().equals("true")) {
				salarySlipObj[0][10] = "Y";
			}else{
				salarySlipObj[0][10] = "N";
			}
			if (salarySlipBean.getGradeChk().equals("true")) {
				salarySlipObj[0][11] = "Y";
			}else{
				salarySlipObj[0][11] = "N";
			}
			// roleChk field added by abhijit
			if (salarySlipBean.getRoleChk().equals("true")) {
				salarySlipObj[0][12] = "Y";
			}else{
				salarySlipObj[0][12] = "N";
			}
			//ended by abhijit
			if (salarySlipBean.getPayBill().equals("true")) {
				salarySlipObj[0][13] = "Y";
			}else{
				salarySlipObj[0][13] = "N";
			}
			if (salarySlipBean.getTrade().equals("true")) {
				salarySlipObj[0][14] = "Y";
			}else{
				salarySlipObj[0][14] = "N";
			}
			
			if (salarySlipBean.getSignature().equals("true")) {
				salarySlipObj[0][15] = "Y";
			}else{
				salarySlipObj[0][15] = "N";
			}
			
			//added 10/01/2012
			
			if (salarySlipBean.getAprilToDateSalaryChk().equals("true")) {
				salarySlipObj[0][16] = "Y";
			}else{
				salarySlipObj[0][16] = "N";
			}
			
			if (salarySlipBean.getRecoveryDays().equals("true")) {
				salarySlipObj[0][17] = "Y";
			}else{
				salarySlipObj[0][17] = "N";
			}
			
			if (salarySlipBean.getNetPayInWords().equals("true")) {
				salarySlipObj[0][18] = "Y";
			}else{
				salarySlipObj[0][18] = "N";
			}
			
			Object[][] salaryDataObj = getSqlModel().getSingleResult(getQuery(3));
			
			if(salaryDataObj != null && salaryDataObj.length >0){
				result = getSqlModel().singleExecute(getQuery(2), salarySlipObj);
			}else{
				result = getSqlModel().singleExecute(getQuery(1), salarySlipObj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getSalaryConfigurationSettings(SalarySlipConfiguration salarySlipBean, HttpServletRequest request) {
		try {
			Object[][] salaryDataObj = getSqlModel().getSingleResult(getQuery(3));
			if(salaryDataObj != null && salaryDataObj.length >0){
				
				
				if (String.valueOf(salaryDataObj[0][0]).equals("Y")) {
					salarySlipBean.setBranchChk("true");
				} 
				if (String.valueOf(salaryDataObj[0][1]).equals("Y")) {
					salarySlipBean.setDepartmentChk("true");
				} 
				if (String.valueOf(salaryDataObj[0][2]).equals("Y")) {
					salarySlipBean.setDesignationChk("true");
				} 
				if (String.valueOf(salaryDataObj[0][3]).equals("Y")) {
					salarySlipBean.setEmpTypeChk("true");
				} 
				if (String.valueOf(salaryDataObj[0][4]).equals("Y")) {
					salarySlipBean.setSalaryGradeChk("true");
				} 
				if (String.valueOf(salaryDataObj[0][5]).equals("Y")) {
					salarySlipBean.setBankIdChk("true");
				} 
				if (String.valueOf(salaryDataObj[0][6]).equals("Y")) {
					salarySlipBean.setAccountNoChk("true");
				} 
				if (String.valueOf(salaryDataObj[0][7]).equals("Y")) {
					salarySlipBean.setAttendanceDetailChk("true");
				} 
				if (String.valueOf(salaryDataObj[0][8]).equals("Y")) {
					salarySlipBean.setLeaveDetailChk("true");
				} 
				if (String.valueOf(salaryDataObj[0][9]).equals("C")) {
					salarySlipBean.setSalHeader("Company");
				} 
				else{
					salarySlipBean.setSalHeader("Division");
				}
				if (String.valueOf(salaryDataObj[0][10]).equals("Y")) {
					salarySlipBean.setShowLogoChk("true");
				} 
				if (String.valueOf(salaryDataObj[0][11]).equals("Y")) {
					salarySlipBean.setGradeChk("true");
				}
				//Added roleChk field by abhijit
				if (String.valueOf(salaryDataObj[0][12]).equals("Y")) {
					salarySlipBean.setRoleChk("true");
				}
				
				//Ended by abhijit
				if (String.valueOf(salaryDataObj[0][13]).equals("Y")) {
					salarySlipBean.setPayBill("true");
				}
				if (String.valueOf(salaryDataObj[0][14]).equals("Y")) {
					salarySlipBean.setTrade("true");
				}
				//added on 10/01/2012
				
				if (String.valueOf(salaryDataObj[0][15]).equals("Y")) {
					salarySlipBean.setSignature("true");
				}
				
				if (String.valueOf(salaryDataObj[0][16]).equals("Y")) {
					salarySlipBean.setAprilToDateSalaryChk("true");
				}
				
				if (String.valueOf(salaryDataObj[0][17]).equals("Y")) {
					salarySlipBean.setRecoveryDays("true");
				}
				if (String.valueOf(salaryDataObj[0][18]).equals("Y")) {
					salarySlipBean.setNetPayInWords("true");
				}
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		request.setAttribute("headerStatus", salarySlipBean.getSalHeader());
	}


}
