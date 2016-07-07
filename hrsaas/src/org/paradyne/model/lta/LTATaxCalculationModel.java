/**
 * 
 */
package org.paradyne.model.lta;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.lta.LTACalculation;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author aa0554
 *
 */
public class LTATaxCalculationModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.paradyne.model.lta.LTATaxCalculationModel.class);
	
	public void showLtaEmpList(LTACalculation bean, HttpServletRequest request){
		String empQuery="SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME,HRMS_LTA_TAXCALC.EMP_ID, "
				+" LTA_BLOCK_FROM,LTA_BLOCK_TO, LTA_YEAROFVISIT, LTA_CLAIM_AMOUNT,DECODE(NVL(LTA_EXEMPTED,'N'),'Y','YES','N','NO'),"
				+" LTA_ID FROM HRMS_LTA_TAXCALC "
				+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LTA_TAXCALC.EMP_ID)";
		
		Object [][]empData = getSqlModel().getSingleResult(empQuery);
		
		try{
			String[] pageIndex = Utility.doPaging(bean.getMyPageEmpConf(),empData.length, 20);	
			if(pageIndex==null){
				pageIndex[0] = "0";
				pageIndex[1] ="20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			
			request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
			request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
			if(pageIndex[4].equals("1"))
				bean.setMyPageEmpConf("1");
			for (int i = 0; i < pageIndex.length; i++) {
			logger.info("pageIndex["+i+"]===="+pageIndex[i]);
			}
			ArrayList tableList = new ArrayList();
			if(empData != null && empData.length !=0){
				for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {
					LTACalculation listBean=new LTACalculation();
				listBean.setEmpTokenList(checkNull(String.valueOf(empData[i][0])));
				listBean.setEmpNameList(checkNull(String.valueOf(empData[i][1])));
				listBean.setEmpCodeList(checkNull(String.valueOf(empData[i][2])));
				listBean.setBlockYearFromList(checkNull(String.valueOf(empData[i][3])));
				listBean.setBlockYearToList(checkNull(String.valueOf(empData[i][4])));
				listBean.setYearOfVisitList(checkNull(String.valueOf(empData[i][5])));
				listBean.setClaimAmtList(checkNull(String.valueOf(empData[i][6])));
				listBean.setIsTaxExemptedList(checkNull(String.valueOf(empData[i][7])));
				listBean.setLtaIdList(checkNull(String.valueOf(empData[i][8])));
				tableList.add(listBean);
			}
			bean.setNoData("false");
			bean.setApplEmpList(tableList);
			
		}else{
			bean.setNoData("true");
			
		}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void showLtaRecord(LTACalculation bean){

		String ltaQuery="SELECT EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) EMP_NAME,HRMS_LTA_TAXCALC.EMP_ID, "
				+" LTA_BLOCK_FROM,LTA_BLOCK_TO, LTA_YEAROFVISIT, LTA_CLAIM_AMOUNT,NVL(LTA_EXEMPTED,'N'),"
				+" TO_CHAR(LTA_CLAIM_DATE,'DD-MM-YYYY'), LTA_TYPE, LTA_EXEMPT_IN_YEAR,'-'||(LTA_EXEMPT_IN_YEAR+1),NVL(LTA_EXEMPT_AMOUNT,0) FROM HRMS_LTA_TAXCALC "
				+" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LTA_TAXCALC.EMP_ID)"
				+" WHERE LTA_ID="+bean.getLtaId();
		
		Object [][]ltaData = getSqlModel().getSingleResult(ltaQuery);
		
		try{
			if(ltaData != null && ltaData.length > 0){
				bean.setEmpToken(checkNull(String.valueOf(ltaData[0][0])));
				bean.setEmpName(checkNull(String.valueOf(ltaData[0][1])));
				bean.setEmpCode(checkNull(String.valueOf(ltaData[0][2])));
				bean.setBlockYearFrom(checkNull(String.valueOf(ltaData[0][3])));
				bean.setBlockYearTo(checkNull(String.valueOf(ltaData[0][4])));
				bean.setYearOfVisit(checkNull(String.valueOf(ltaData[0][5])));
				bean.setClaimAmt(checkNull(String.valueOf(ltaData[0][6])));
				bean.setIsTaxExempted(checkNull(String.valueOf(ltaData[0][7])));
				bean.setClaimDate(checkNull(String.valueOf(ltaData[0][8])));
				bean.setClaimType(checkNull(String.valueOf(ltaData[0][9])));
				bean.setYearOfExemption(checkNull(String.valueOf(ltaData[0][10])));
				bean.setYearOfExemptionTo(checkNull(String.valueOf(ltaData[0][11])));
				bean.setExemptedAmt(checkNull(String.valueOf(ltaData[0][12])));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public boolean delete(LTACalculation bean){

		String delQuery="DELETE FROM HRMS_LTA_TAXCALC WHERE LTA_ID="+bean.getLtaId();
		
		boolean result=false;
		
		try{
			result=getSqlModel().singleExecute(delQuery);
						
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	
	}
	public boolean save(LTACalculation bean){
		boolean result=false;
		if(bean.getLtaId().equals("")){
			String insertQuery="INSERT INTO HRMS_LTA_TAXCALC(LTA_ID,EMP_ID, LTA_BLOCK_FROM, LTA_BLOCK_TO, LTA_YEAROFVISIT, LTA_CLAIM_DATE, LTA_CLAIM_AMOUNT, LTA_TYPE, " 
					+" LTA_EXEMPTED, LTA_EXEMPT_IN_YEAR,LTA_EXEMPT_AMOUNT) VALUES((SELECT MAX(NVL(LTA_ID,0))+1 FROM HRMS_LTA_TAXCALC),?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?)";
			
			Object addObj[][]=new Object[1][10];
			
			addObj[0][0]=bean.getEmpCode();
			addObj[0][1]=bean.getBlockYearFrom();
			addObj[0][2]=bean.getBlockYearTo();
			addObj[0][3]=bean.getYearOfVisit();
			addObj[0][4]=bean.getClaimDate();
			addObj[0][5]=bean.getClaimAmt();
			addObj[0][6]=bean.getClaimType();
			addObj[0][7]=bean.getIsTaxExempted();
			addObj[0][8]=bean.getYearOfExemption();
			addObj[0][9]=bean.getExemptedAmt();
			
			result =getSqlModel().singleExecute(insertQuery,addObj);
			if(result){
				Object ltaId[][]=getSqlModel().getSingleResult("SELECT MAX(NVL(LTA_ID,0)) FROM HRMS_LTA_TAXCALC");
				
				bean.setLtaId(checkNull(String.valueOf(ltaId[0][0])));
			}
		}else{
			
		String insertQuery="UPDATE HRMS_LTA_TAXCALC SET EMP_ID=?, LTA_BLOCK_FROM=?, LTA_BLOCK_TO=?, LTA_YEAROFVISIT=?, LTA_CLAIM_DATE=TO_DATE(?,'DD-MM-YYYY'), LTA_CLAIM_AMOUNT=?, LTA_TYPE=?, " 
				+" LTA_EXEMPTED=?, LTA_EXEMPT_IN_YEAR=?,LTA_EXEMPT_AMOUNT=? WHERE LTA_ID=?";
		
		Object updateObj[][]=new Object[1][11];
		
		updateObj[0][0]=bean.getEmpCode();
		updateObj[0][1]=bean.getBlockYearFrom();
		updateObj[0][2]=bean.getBlockYearTo();
		updateObj[0][3]=bean.getYearOfVisit();
		updateObj[0][4]=bean.getClaimDate();
		updateObj[0][5]=bean.getClaimAmt();
		updateObj[0][6]=bean.getClaimType();
		updateObj[0][7]=bean.getIsTaxExempted();
		updateObj[0][8]=bean.getYearOfExemption();
		updateObj[0][9]=bean.getExemptedAmt();
		updateObj[0][10]=bean.getLtaId();
		for (int i = 0; i < updateObj[0].length; i++) {
			logger.info("updateObj[0]["+i+"]=="+updateObj[0][i]);
		}
		result =getSqlModel().singleExecute(insertQuery,updateObj);
	}
		return result;
	}
	
	public String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "";
		}
		else{
			return result;
		}
	}
	
	
	public String checkYearValidation(LTACalculation bean){
		
		String result="true";
		String query="SELECT COUNT(*) FROM HRMS_LTA_TAXCALC WHERE  EMP_ID="+bean.getEmpCode()+" AND LTA_BLOCK_FROM="+bean.getBlockYearFrom()+" AND LTA_BLOCK_TO="+bean.getBlockYearTo()+" AND LTA_TYPE='"+bean.getClaimType()+"'";
		
		if(!bean.getLtaId().equals("")){
			query +=" AND LTA_ID !="+bean.getLtaId();
		}
		Object [][] ltaCountObj = getSqlModel().getSingleResult(query);
		int ltaCount = Integer.parseInt(String.valueOf(ltaCountObj[0][0]));
		
		if(bean.getClaimType().equals("S")){
			if(ltaCount>1)
				result="Only two LTA Application allowed for this block year and 'Self' claim type.";
		}else if(bean.getClaimType().equals("F")){
			if(ltaCount>1)
				result="Only two LTA Application allowed for this block year and 'Family' claim type.";
		}else if(bean.getClaimType().equals("B")){
			if(ltaCount>0)
				result="Only one LTA Application allowed for this block year and claim type as 'Both'.";
		}else{
			result="true";
		}
		
		return result;
	}
	
	public String checkExemptYearValidation(LTACalculation bean,String result){
		
		if(!bean.getYearOfExemption().equals("")){
		String query="SELECT LTA_EXEMPT_IN_YEAR FROM HRMS_LTA_TAXCALC WHERE  EMP_ID="+bean.getEmpCode()+" AND LTA_EXEMPT_IN_YEAR IN " 
				+"("+bean.getYearOfExemption()+","+(Integer.parseInt(bean.getYearOfExemption())+1)+","+(Integer.parseInt(bean.getYearOfExemption())-1)+")";
		
		if(!bean.getLtaId().equals("")){
			query +=" AND LTA_ID !="+bean.getLtaId();
		}
		Object [][]ltaObj=getSqlModel().getSingleResult(query);
		try {
			if (ltaObj != null || ltaObj.length > 0) {
				result = "LTA exemption is already applied for the year "
						+ String.valueOf(ltaObj[0][0])
						+ ". You can not exempt LTA for consecutive financial year.";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		}
		return result;
	}
	
		


}
