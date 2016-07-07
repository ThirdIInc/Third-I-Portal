package org.paradyne.model.settings;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.paradyne.lib.ModelBase;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.struts.lib.ParaActionSupport;
import org.paradyne.lib.ModelBase;
import org.omg.CORBA.Request;
import org.paradyne.bean.Asset.AssetSubTypes;
import org.paradyne.bean.settings.AdminConf;
public class AdminConfModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.paradyne.model.settings.AdminConfModel.class);
	
	public void addAdminConfDetails(AdminConf bean,String[] adminNameIt,String[] adminCodeIt,String[] divNameIt,String[] divCodeIt,String[] confIdIt) {
		try {
			ArrayList adminList = new ArrayList();
			if(adminNameIt!=null && adminNameIt.length>0){
				AdminConf adConf=null;
				for (int i = 0; i < adminNameIt.length; i++) {
					adConf = new AdminConf();
					adConf.setAdminConfIdIt(confIdIt[i]);
					adConf.setDivNameIt(divNameIt[i]);
					adConf.setDivCodeIt(divCodeIt[i]);
					adConf.setAdminNameIt(adminNameIt[i]);
					adConf.setAdminCodeIt(adminCodeIt[i]);
					adminList.add(adConf);
				}
				bean.setAdminConfIdIt("");
				bean.setDivNameIt(bean.getDivision());
				bean.setDivCodeIt(bean.getDivCode());
				bean.setAdminNameIt(bean.getEmpName());
				bean.setAdminCodeIt(bean.getEmpId());
			}
			
			if(bean.getSrNoIt().equals("")){
				bean.setDivNameIt(bean.getDivision());
				bean.setDivCodeIt(bean.getDivCode());
				bean.setAdminNameIt(bean.getEmpName());
				bean.setAdminCodeIt(bean.getEmpId());
			}
			adminList.add(bean);
		    bean.setAdminConfList(adminList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public void addAcctConfDetails(AdminConf bean,String[] accAdminNameIt,String[] accDivNameIt,String[] accAdminCodeIt,String[] accDivCodeIt,String[] confIdIt) {
		try {
			ArrayList acctList = new ArrayList();
			if(accAdminNameIt!=null && accAdminNameIt.length>0){
				AdminConf accConf=null;
				for (int i = 0; i < accAdminNameIt.length; i++) {
					accConf = new AdminConf();
					accConf.setAccConfIdIt(confIdIt[i]);
					accConf.setAccDivNameIt(accDivNameIt[i]);
					accConf.setAccDivCodeIt(accDivCodeIt[i]);
					accConf.setAccAdminNameIt(accAdminNameIt[i]);
					accConf.setAccAdminCodeIt(accAdminCodeIt[i]);
					acctList.add(accConf);
				}
				bean.setAccConfIdIt("");
				bean.setAccDivNameIt(bean.getAccDivision());
				bean.setAccDivCodeIt(bean.getAccDivCode());
				bean.setAccAdminNameIt(bean.getAccEmpName());
				bean.setAccAdminCodeIt(bean.getAccEmpId());
				
			}	
			if(bean.getAccSrNoIt().equals("")){
				bean.setAccDivNameIt(bean.getAccDivision());
				bean.setAccDivCodeIt(bean.getAccDivCode());
				bean.setAccAdminNameIt(bean.getAccEmpName());
				bean.setAccAdminCodeIt(bean.getAccEmpId());
			}
			acctList.add(bean);
		    bean.setAccConfList(acctList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addBCardAdminConfDetails(AdminConf bean,String[] bCardAdminNameIt,String[] bCardDivNameIt,
			String[] bCardAdminCodeIt,String[] bCardDivCodeIt,String[] confIdIt) {
		try {
			ArrayList list = new ArrayList();
			AdminConf adConf = null;
			if(bCardAdminNameIt!=null && bCardAdminNameIt.length>0){
				for (int i = 0; i < bCardAdminNameIt.length; i++) {
					adConf = new AdminConf();
					adConf.setBCardConfIdIt(confIdIt[i]);
					adConf.setBCardDivNameIt(bCardDivNameIt[i]);
					adConf.setBCardDivCodeIt(bCardDivCodeIt[i]);
					adConf.setBCardAdminNameIt(bCardAdminNameIt[i]);
					adConf.setBCardAdminCodeIt(bCardAdminCodeIt[i]);
					list.add(adConf);
				}
				bean.setBCardConfIdIt("");
				bean.setBCardDivNameIt(bean.getBCardDivision());
				bean.setBCardDivCodeIt(bean.getBCardDivCode());
				bean.setBCardAdminNameIt(bean.getBCardEmpName());
				bean.setBCardAdminCodeIt(bean.getBCardEmpId());
			}					
			if(bean.getBCardSrNoIt().equals("")){
				bean.setBCardDivNameIt(bean.getBCardDivision());
				bean.setBCardDivCodeIt(bean.getBCardDivCode());
				bean.setBCardAdminNameIt(bean.getBCardEmpName());
				bean.setBCardAdminCodeIt(bean.getBCardEmpId());
			}
			list.add(bean);
		    bean.setAdminBCardConfList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addECardAdminConfDetails(AdminConf bean,String[] eCardAdminNameIt,String[] eCardDivNameIt,
			String[] eCardAdminCodeIt,String[] eCardDivCodeIt,String[] confIdIt) {
		try {
			ArrayList list = new ArrayList();
			if(eCardAdminNameIt!=null && eCardAdminNameIt.length>0){
				AdminConf adConf = null;
				for (int i = 0; i < eCardAdminNameIt.length; i++) {
					adConf = new AdminConf();
					adConf.setECardConfIdIt(confIdIt[i]);
					adConf.setECardDivNameIt(eCardDivNameIt[i]);
					adConf.setECardDivCodeIt(eCardDivCodeIt[i]);
					adConf.setECardAdminNameIt(eCardAdminNameIt[i]);
					adConf.setECardAdminCodeIt(eCardAdminCodeIt[i]);
					list.add(adConf);
				}
				bean.setECardConfIdIt("");
				bean.setECardDivNameIt(bean.getECardDivision());
				bean.setECardDivCodeIt(bean.getECardDivCode());
				bean.setECardAdminNameIt(bean.getECardEmpName());
				bean.setECardAdminCodeIt(bean.getECardEmpId());
			}	
			if(bean.getECardSrNoIt().equals("")){
				
				bean.setECardDivNameIt(bean.getECardDivision());
				bean.setECardDivCodeIt(bean.getECardDivCode());
				bean.setECardAdminNameIt(bean.getECardEmpName());
				bean.setECardAdminCodeIt(bean.getECardEmpId());
			}
			list.add(bean);
		    bean.setAdminECardConfList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean saveConfDtl(AdminConf bean,String[] adminNameIt,String[] divNameIt,
										   String[] accAdminCodeIt,String[] accDivCodeIt,
										   String[] bCardAdminCodeIt,String[] bCardDivCodeIt,
										   String[] eCardAdminCodeIt,String[] eCardDivCodeIt){
		boolean result = false;
		Object[][] flagValues=new Object[1][12];
		flagValues[0][0]=bean.getManagerFlag();
		flagValues[0][1]=String.valueOf(bean.getAdminFlag());
		flagValues[0][2]=bean.getAcctFlag();
		
		flagValues[0][3]=bean.getBCardManagerFlag();
		flagValues[0][4]=bean.getBCardVendorFlag();
		flagValues[0][5]=bean.getBCardVndrName();
		flagValues[0][6]=bean.getBCardVndrEmail();
		flagValues[0][7]=bean.getBCardVndrPhNum();
		
		flagValues[0][8]=bean.getECardVendorFlag();
		flagValues[0][9]=bean.getECardVndrName();
		flagValues[0][10]=bean.getECardVndrEmail();
		flagValues[0][11]=bean.getECardVndrPhNum();
		
		if(flagValues!=null && flagValues.length>0){
			
			String delQuery = " DELETE FROM HRMS_ADMIN_CONF ";
			result = getSqlModel().singleExecute(delQuery);
			
			String saveConfFlagVaues=" INSERT INTO HRMS_ADMIN_CONF(CONF_CASH_MGR_FLAG, CONF_CASH_ADMIN_FLAG, " +
			" CONF_CASH_ACC_FLAG, CONF_BCARD_MGR_FLAG, CONF_BCARD_VNDR_FLAG, " +
			" CONF_BCARD_VNDR_NAME, CONF_BCARD_VNDR_EMAIL, CONF_BCARD_VNDR_PHON, CONF_ECARD_VNDR_FLAG," +
			" CONF_ECARD_VNDR_NAME, CONF_ECARD_VNDR_EMAIL, CONF_ECARD_VNDR_PHON)" +
			" VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			System.out.println("===================");
			result=getSqlModel().singleExecute(saveConfFlagVaues, flagValues);
		}
		
		if(adminNameIt!=null && adminNameIt.length>0){
			String confType="CashAdmin";
			Object[][] adminData= new Object[adminNameIt.length][4];
			String delQuery = " DELETE FROM HRMS_ADMIN_CONF_DTL WHERE CONF_TYPE ='CashAdmin' ";
			result = getSqlModel().singleExecute(delQuery);
			
			String idQuery = "SELECT NVL(MAX(CONF_ID),0)+1 FROM HRMS_ADMIN_CONF_DTL";
			Object cnt[][] = getSqlModel().getSingleResult(idQuery);
			int count=Integer.parseInt(String.valueOf(cnt[0][0]));
			
			for (int i = 0; i < adminNameIt.length; i++) {
				adminData[i][0]=count+i;
				adminData[i][1]=confType;
				adminData[i][2]=String.valueOf(divNameIt[i]);
				adminData[i][3]=String.valueOf(adminNameIt[i]);	
			}
			if(result){
				String insertQry="INSERT INTO HRMS_ADMIN_CONF_DTL(CONF_ID, CONF_TYPE, CONF_DIV, CONF_EMP) VALUES(?,?,?,?)";
				result = getSqlModel().singleExecute(insertQry, adminData);
			}			
		}
		
		if(accAdminCodeIt!=null && accAdminCodeIt.length>0){
			String confType="CashAccount";
			String idQuery = "SELECT NVL(MAX(CONF_ID),0)+1 FROM HRMS_ADMIN_CONF_DTL";
			Object cnt[][] = getSqlModel().getSingleResult(idQuery);
			int count=Integer.parseInt(String.valueOf(cnt[0][0]));
			Object[][] accAdminData= new Object[accAdminCodeIt.length][4];
			for (int i = 0; i < accAdminCodeIt.length; i++) {
				accAdminData[i][0]=count+i;
				accAdminData[i][1]=confType;
				accAdminData[i][2]=String.valueOf(accDivCodeIt[i]);
				accAdminData[i][3]=String.valueOf(accAdminCodeIt[i]);
			}
			String delQuery = " DELETE FROM HRMS_ADMIN_CONF_DTL WHERE CONF_TYPE ='CashAccount' ";
			result = getSqlModel().singleExecute(delQuery);
			if(result){
				String insertQry="INSERT INTO HRMS_ADMIN_CONF_DTL(CONF_ID, CONF_TYPE, CONF_DIV, CONF_EMP) VALUES(?,?,?,?)";
				result=getSqlModel().singleExecute(insertQry, accAdminData);
			}			
			
		}
		
		if(bCardAdminCodeIt!=null && bCardAdminCodeIt.length>0){
			String confType="BcardAdmin";
			String idQuery = "SELECT NVL(MAX(CONF_ID),0)+1 FROM HRMS_ADMIN_CONF_DTL";
			Object cnt[][] = getSqlModel().getSingleResult(idQuery);
			int count=Integer.parseInt(String.valueOf(cnt[0][0]));
			Object[][] bCardAdminData= new Object[bCardAdminCodeIt.length][4];
			for (int i = 0; i < bCardAdminCodeIt.length; i++) {
				bCardAdminData[i][0]=count+i;
				bCardAdminData[i][1]=confType;
				bCardAdminData[i][2]=String.valueOf(bCardDivCodeIt[i]);
				bCardAdminData[i][3]=String.valueOf(bCardAdminCodeIt[i]);
			}
			String delQuery = " DELETE FROM HRMS_ADMIN_CONF_DTL WHERE CONF_TYPE ='BcardAdmin' ";
			result = getSqlModel().singleExecute(delQuery);
			if(result){
				String insertQry="INSERT INTO HRMS_ADMIN_CONF_DTL(CONF_ID, CONF_TYPE, CONF_DIV, CONF_EMP) VALUES(?,?,?,?)";
				result=getSqlModel().singleExecute(insertQry, bCardAdminData);
			}
		}
		if(eCardAdminCodeIt!=null && eCardAdminCodeIt.length>0){
			String confType="EcardAdmin";
			String idQuery = "SELECT NVL(MAX(CONF_ID),0)+1 FROM HRMS_ADMIN_CONF_DTL";
			Object cnt[][] = getSqlModel().getSingleResult(idQuery);
			int count=Integer.parseInt(String.valueOf(cnt[0][0]));
			Object[][] eCardAdminData= new Object[eCardAdminCodeIt.length][4];
			for (int i = 0; i < eCardAdminCodeIt.length; i++) {
				eCardAdminData[i][0]=count+i;
				eCardAdminData[i][1]=confType;
				eCardAdminData[i][2]=String.valueOf(eCardDivCodeIt[i]);
				eCardAdminData[i][3]=String.valueOf(eCardAdminCodeIt[i]);
			}
			String delQuery = " DELETE FROM HRMS_ADMIN_CONF_DTL WHERE CONF_TYPE ='EcardAdmin' ";
			result = getSqlModel().singleExecute(delQuery);
		    if(result){
		    	String insertQry="INSERT INTO HRMS_ADMIN_CONF_DTL(CONF_ID, CONF_TYPE, CONF_DIV, CONF_EMP) VALUES(?,?,?,?)";
		    	result =getSqlModel().singleExecute(insertQry, eCardAdminData);
		    }	
		}
		return result;
	}
	public void  getConfDetails(AdminConf bean){
		String adminQry=" SELECT CONF_DIV, CONF_EMP,CONF_ID FROM  HRMS_ADMIN_CONF_DTL WHERE CONF_TYPE='CashAdmin' ";
		Object adminDataObj[][] = getSqlModel().getSingleResult(adminQry);
		AdminConf adConf=null;
		ArrayList list = new ArrayList();
		ArrayList acList = new ArrayList();
		ArrayList bcardList = new ArrayList();
		ArrayList ecardList = new ArrayList();
		ArrayList vendorList = new ArrayList();
		if(adminDataObj!=null && adminDataObj.length>0){
			for (int i = 0; i < adminDataObj.length; i++) {
				adConf = new AdminConf();
				String qryDivName="SELECT DIV_NAME,DIV_ID  FROM HRMS_DIVISION WHERE DIV_ID="+adminDataObj[i][0];
				Object divName[][] = getSqlModel().getSingleResult(qryDivName);
				
				String qryEmpName="SELECT EMP_FNAME||' ' ||EMP_MNAME|| ' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC  WHERE EMP_ID="+adminDataObj[i][1];
				Object empName[][] = getSqlModel().getSingleResult(qryEmpName);
				String ConfId=String.valueOf(adminDataObj[i][2]);
				if(divName!=null && divName.length>0){
					for (int cnt = 0; cnt < divName.length; cnt++) {
						adConf.setAdminConfIdIt(ConfId);
						adConf.setDivNameIt(String.valueOf(divName[cnt][0]));
						adConf.setDivCodeIt(String.valueOf(divName[cnt][1]));
						adConf.setAdminNameIt(String.valueOf(empName[cnt][0]));
						adConf.setAdminCodeIt(String.valueOf(empName[cnt][1]));
						list.add(adConf);
					}	
				}
			}		
		}
		bean.setAdminConfList(list);
		
		String acctQry="SELECT CONF_DIV, CONF_EMP, CONF_ID FROM  HRMS_ADMIN_CONF_DTL WHERE CONF_TYPE='CashAccount'";
		Object accDataObj[][] = getSqlModel().getSingleResult(acctQry);	
		
		if(accDataObj!=null && accDataObj.length>0){
			for (int i = 0; i < accDataObj.length; i++) {
				adConf = new AdminConf();
				String qryDivName="SELECT DIV_NAME,DIV_ID  FROM HRMS_DIVISION WHERE DIV_ID="+accDataObj[i][0];
				Object divName[][] = getSqlModel().getSingleResult(qryDivName);
				
				String qryEmpName="SELECT EMP_FNAME||' ' ||EMP_MNAME|| ' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC  WHERE EMP_ID="+accDataObj[i][1];
				Object empName[][] = getSqlModel().getSingleResult(qryEmpName);
				String ConfId=String.valueOf(accDataObj[i][2]);
				if(divName!=null && divName.length>0){
					for (int cnt = 0; cnt < divName.length; cnt++) {
						adConf.setAccConfIdIt(ConfId);
						adConf.setAccDivNameIt(String.valueOf(divName[cnt][0]));
						adConf.setAccDivCodeIt(String.valueOf(divName[cnt][1]));
						adConf.setAccAdminNameIt(String.valueOf(empName[cnt][0]));
						adConf.setAccAdminCodeIt(String.valueOf(empName[cnt][1]));
						acList.add(adConf);
					}	
				}
			}		
		}
		bean.setAccConfList(acList);
		
		String bcardQry="SELECT CONF_DIV, CONF_EMP , CONF_ID  FROM  HRMS_ADMIN_CONF_DTL WHERE CONF_TYPE='BcardAdmin'";
		Object bcardDataObj[][] = getSqlModel().getSingleResult(bcardQry);
		
		if(bcardDataObj!=null && bcardDataObj.length>0){
			for (int i = 0; i < bcardDataObj.length; i++) {
				adConf = new AdminConf();
				String qryDivName="SELECT DIV_NAME,DIV_ID  FROM HRMS_DIVISION WHERE DIV_ID="+bcardDataObj[i][0];
				Object divName[][] = getSqlModel().getSingleResult(qryDivName);
				
				String qryEmpName="SELECT EMP_FNAME||' ' ||EMP_MNAME|| ' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC  WHERE EMP_ID="+bcardDataObj[i][1];
				Object empName[][] = getSqlModel().getSingleResult(qryEmpName);
				String ConfId=String.valueOf(bcardDataObj[i][2]);
				if(divName!=null && divName.length>0){
					for (int cnt = 0; cnt < divName.length; cnt++) {
						adConf.setBCardConfIdIt(ConfId);
						adConf.setBCardDivNameIt(String.valueOf(divName[cnt][0]));
						adConf.setBCardDivCodeIt(String.valueOf(divName[cnt][1]));
						adConf.setBCardAdminNameIt(String.valueOf(empName[cnt][0]));
						adConf.setBCardAdminCodeIt(String.valueOf(empName[cnt][1]));
						bcardList.add(adConf);
					}	
				}
			}		
		}
		bean.setAdminBCardConfList(bcardList);
		
		String eCardQry="SELECT CONF_DIV, CONF_EMP , CONF_ID  FROM  HRMS_ADMIN_CONF_DTL WHERE CONF_TYPE='EcardAdmin'";	
		Object eCardDataObj[][] = getSqlModel().getSingleResult(eCardQry);
		if(eCardDataObj!=null && eCardDataObj.length>0){
			for (int i = 0; i < eCardDataObj.length; i++) {
				adConf = new AdminConf();
				String qryDivName="SELECT DIV_NAME,DIV_ID  FROM HRMS_DIVISION WHERE DIV_ID="+eCardDataObj[i][0];
				Object divName[][] = getSqlModel().getSingleResult(qryDivName);
				
				String qryEmpName="SELECT EMP_FNAME||' ' ||EMP_MNAME|| ' '||EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC  WHERE EMP_ID="+eCardDataObj[i][1];
				Object empName[][] = getSqlModel().getSingleResult(qryEmpName);
				String ConfId=String.valueOf(eCardDataObj[i][2]);
				if(divName!=null && divName.length>0){
					for (int cnt = 0; cnt < divName.length; cnt++) {
						adConf.setECardConfIdIt(ConfId);
						adConf.setECardDivNameIt(String.valueOf(divName[cnt][0]));
						adConf.setECardDivCodeIt(String.valueOf(divName[cnt][1]));
						adConf.setECardAdminNameIt(String.valueOf(empName[cnt][0]));
						adConf.setECardAdminCodeIt(String.valueOf(empName[cnt][1]));
						ecardList.add(adConf);
					}	
				}
			}		
		}
		bean.setAdminECardConfList(ecardList);	
		
		
		
		//////Vendor list
		
	String eCardVendor="SELECT CONF_CASH_MGR_FLAG,CONF_CASH_ADMIN_FLAG,CONF_CASH_ACC_FLAG,CONF_BCARD_MGR_FLAG," +
						"CONF_BCARD_VNDR_FLAG,CONF_BCARD_VNDR_NAME,CONF_BCARD_VNDR_EMAIL,CONF_BCARD_VNDR_PHON," +
			           "CONF_ECARD_VNDR_FLAG, CONF_ECARD_VNDR_NAME , CONF_ECARD_VNDR_EMAIL,CONF_ECARD_VNDR_PHON  FROM  HRMS_ADMIN_CONF";	
		Object eCardVendorObj[][] = getSqlModel().getSingleResult(eCardVendor);
		if(eCardVendorObj!=null && eCardVendorObj.length>0){
				adConf = new AdminConf();
				adConf.setManagerFlag(String.valueOf(eCardVendorObj[0][0]));
				adConf.setAdminFlag(String.valueOf(eCardVendorObj[0][1]));
				adConf.setAcctFlag(String.valueOf(eCardVendorObj[0][2]));
				adConf.setBCardManagerFlag(String.valueOf(eCardVendorObj[0][3]));
				adConf.setBCardVendorFlag(String.valueOf(eCardVendorObj[0][4]));
				adConf.setBCardVndrNameIt(checkNull(String.valueOf(eCardVendorObj[0][5])));
				adConf.setBCardVndrEmailIt(checkNull(String.valueOf(eCardVendorObj[0][6])));
				adConf.setBCardVndrPhNumIt(checkNull(String.valueOf(eCardVendorObj[0][7])));
				
				adConf.setECardVendorFlag(String.valueOf(eCardVendorObj[0][8]));
				adConf.setECardVndrNameIt(checkNull(String.valueOf(eCardVendorObj[0][9])));
				adConf.setECardVndrEmailIt(checkNull(String.valueOf(eCardVendorObj[0][10])));
				adConf.setECardVndrPhNumIt(checkNull(String.valueOf(eCardVendorObj[0][11])));
				
				bean.setManagerFlag(adConf.getManagerFlag());
				bean.setAdminFlag(adConf.getAdminFlag());
				bean.setAcctFlag(adConf.getAcctFlag());
				bean.setBCardManagerFlag(adConf.getBCardManagerFlag());
				
				bean.setBCardVendorFlag(adConf.getBCardVendorFlag());
				bean.setBCardVndrName(adConf.getBCardVndrNameIt());
				bean.setBCardVndrEmail(adConf.getBCardVndrEmailIt());
				bean.setBCardVndrPhNum(adConf.getBCardVndrPhNumIt());
				
				bean.setECardVendorFlag(adConf.getECardVendorFlag());
				bean.setECardVndrEmail(adConf.getECardVndrEmailIt());
				bean.setECardVndrName(adConf.getECardVndrNameIt());
				bean.setECardVndrPhNum(adConf.getECardVndrPhNumIt());
				vendorList.add(adConf);
			}else{
				bean.setECardVendorFlag("");
				bean.setECardVndrEmail("");
				bean.setECardVndrName("");
				bean.setECardVndrPhNum("");
				bean.setECardVndrEmail("");
				bean.setECardVndrName("");
				bean.setECardVndrPhNum("");
				vendorList.add(adConf);
			}
			
	
		bean.setECardVendorConfList(vendorList);	
			
	}
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}
	public void setAdminIteratorValues(AdminConf bean,String[] adminNameIt,String[] divNameIt,String[] adminCodeIt,String[] divCodeIt,String[] confId){
		ArrayList adminList = new ArrayList();
		if(adminNameIt!=null && adminNameIt.length>0){
			AdminConf adminConf = null;
			for (int i = 0; i < adminNameIt.length; i++) {
				adminConf = new AdminConf();
				adminConf.setAdminConfIdIt(confId[i]);
				adminConf.setDivNameIt(divNameIt[i]);
				adminConf.setAdminNameIt(adminNameIt[i]);
				adminConf.setDivCodeIt(divCodeIt[i]);
				adminConf.setAdminCodeIt(adminCodeIt[i]);
				adminList.add(adminConf);
			}
			 bean.setAdminConfList(adminList);	
		}
	}
	public void setAcctIteratorValues(AdminConf bean,String[] accAdminNameIt,String[] accDivNameIt,String[] accAdminCodeIt,String[] accDivCodeIt,String[] confId){
		ArrayList accList = new ArrayList();
		AdminConf accConf = null;
		for (int i = 0; i < accAdminNameIt.length; i++) {
			accConf = new AdminConf();
			accConf.setAccConfIdIt(confId[i]);
			accConf.setAccDivNameIt(accDivNameIt[i]);
			accConf.setAccDivCodeIt(accDivCodeIt[i]);
			accConf.setAccAdminNameIt(accAdminNameIt[i]);
			accConf.setAccAdminCodeIt(accAdminCodeIt[i]);
			accList.add(accConf);
		}
		 bean.setAccConfList(accList);	
	}
	public void setBCardIteratorValues(AdminConf bean,String[] bCardAdminNameIt,String[] bCardDivNameIt,
										String[] bCardAdminCodeIt,String[] bCardDivCodeIt,String[] confId){
		ArrayList adminList = new ArrayList();
		if(bCardAdminNameIt!=null && bCardAdminNameIt.length>0){
			AdminConf adminConf = null;
			for (int i = 0; i < bCardAdminNameIt.length; i++) {
				adminConf = new AdminConf();
				adminConf.setBCardConfIdIt(confId[i]);
				adminConf.setBCardDivNameIt(bCardDivNameIt[i]);
				adminConf.setBCardDivCodeIt(bCardDivCodeIt[i]);
				adminConf.setBCardAdminNameIt(bCardAdminNameIt[i]);
				adminConf.setBCardAdminCodeIt(bCardAdminCodeIt[i]);
				adminList.add(adminConf);
			}
			 bean.setAdminBCardConfList(adminList);	
		}
	}
	public void setECardIteratorValues(AdminConf bean,String[] eCardAdminNameIt,String[] eCardDivNameIt,
			String[] eCardAdminCodeIt,String[] eCardDivCodeIt,String[] confId ){
		ArrayList adminList = new ArrayList();
		if(eCardAdminNameIt!=null && eCardAdminNameIt.length>0){
			AdminConf adminConf = null;
			for (int i = 0; i < eCardAdminNameIt.length; i++) {
				adminConf = new AdminConf();
				adminConf.setECardConfIdIt(confId[i]);
				adminConf.setECardDivNameIt(eCardAdminNameIt[i]);
				adminConf.setECardDivCodeIt(eCardAdminCodeIt[i]);
				adminConf.setECardAdminNameIt(eCardDivNameIt[i]);
				adminConf.setECardAdminCodeIt(eCardDivCodeIt[i]);
				adminList.add(adminConf);
			}
			 bean.setAdminECardConfList(adminList);	
		}
	}
	public void setECardVendorIteratorValues(AdminConf bean,String[] eCardVndrNameIt,String[] eCardVndrEmailIt,
			String[] eCardVndrPhNumIt){
		ArrayList vendorList = new ArrayList();
		if(eCardVndrNameIt!=null && eCardVndrNameIt.length>0){
			AdminConf adminConf = null;
			for (int i = 0; i < eCardVndrNameIt.length; i++) {
				adminConf = new AdminConf();
				adminConf.setECardVndrNameIt(eCardVndrNameIt[i]);
				adminConf.setECardVndrEmailIt(eCardVndrEmailIt[i]);
				adminConf.setECardVndrPhNumIt(eCardVndrPhNumIt[i]);
				
				vendorList.add(adminConf);
			}
			 bean.setECardVendorConfList(vendorList);	
		}
	}
	public void deleteBCardConfDtl(AdminConf bean, String[] bCardAdminNameIt,String[] bCardDivNameIt,String[] bCardAdminCodeIt,String[] bCardDivCodeIt, String[] confId,String confID) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if(!confID.equals("")){
			String delQry="DELETE FROM HRMS_ADMIN_CONF_DTL WHERE CONF_ID="+confID;
			getSqlModel().singleExecute(delQry);
		}	
		for (int i = 0; i < bCardAdminNameIt.length; i++) {
			AdminConf bean1 = new AdminConf();
			if (!bean.getParaId().equals(String.valueOf(i + 1))) {
				bean1.setECardConfIdIt(confId[i]);
				bean1.setBCardDivNameIt(bCardDivNameIt[i]);
				bean1.setBCardDivCodeIt(bCardDivCodeIt[i]);
				bean1.setBCardAdminNameIt(bCardAdminNameIt[i]);
				bean1.setBCardAdminCodeIt(bCardAdminCodeIt[i]);
				
				tableList.add(bean1);
			} // end of if
		} // end of for loop
		bean.setAdminBCardConfList(tableList);
	}
	
	
	
	public void deleteECardConfDtl(AdminConf bean, String[] bcardAdminNameIt,String[] bcardDivnameIt,
			String[] bcardAdminCodeIt,String[] bcardDivCodeIt,String[] confId,String confID) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if(!confID.equals("")){
			String delQry="DELETE FROM HRMS_ADMIN_CONF_DTL WHERE CONF_ID="+confID;
			getSqlModel().singleExecute(delQry);
		}	
		for (int i = 0; i < bcardAdminNameIt.length; i++) {
			AdminConf bean1 = new AdminConf();
			if (!bean.getParaId().equals(String.valueOf(i + 1))) {
				bean1.setECardConfIdIt(confId[i]);
				bean1.setECardAdminNameIt(bcardAdminNameIt[i]);
				bean1.setECardAdminCodeIt(bcardAdminCodeIt[i]);
				bean1.setECardDivNameIt(bcardDivnameIt[i]);
				bean1.setECardDivCodeIt(bcardDivCodeIt[i]);
				tableList.add(bean1);
			} // end of if
		} // end of for loop
		bean.setAdminECardConfList(tableList);
	}

	public void deleteAdminConfDtl(AdminConf bean, String[] adminName,String[] divName,
			String[] adminCode,String[] divCode,String[] confId,String confID) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if(!confID.equals("")){
			String delQry="DELETE FROM HRMS_ADMIN_CONF_DTL WHERE CONF_ID="+confID;
			getSqlModel().singleExecute(delQry);
		}	
		
		for (int i = 0; i < adminName.length; i++) {
			AdminConf bean1 = new AdminConf();
			if (!bean.getParaId().equals(String.valueOf(i + 1))) {
				bean1.setAdminConfIdIt(confId[i]);
				bean1.setAdminNameIt(adminName[i]);
				bean1.setAdminCodeIt(adminCode[i]);
				bean1.setDivNameIt(divName[i]);
				bean1.setDivCodeIt(divCode[i]);
				tableList.add(bean1);
			} // end of if
		} // end of for loop
		bean.setAdminConfList(tableList);
	}
	public void deleteAcctConfDetails(AdminConf bean, String[] accAdminNameIt,String[] accDivnameIt,
			String[] accAdminCodeIt,String[] accDivCodeIt,String[] confId,String confID) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if(!confID.equals("")){
			String delQry="DELETE FROM HRMS_ADMIN_CONF_DTL WHERE CONF_ID="+confID;
			getSqlModel().singleExecute(delQry);
		}
		AdminConf bean1 = null;
		for (int i = 0; i < accAdminNameIt.length; i++) {
			bean1 = new AdminConf();
			if (!bean.getParaId().equals(String.valueOf(i + 1))) {
				bean1.setAccConfIdIt(confId[i]);	
				bean1.setAccDivNameIt(accDivnameIt[i]);
				bean1.setAccDivCodeIt(accDivCodeIt[i]);
				bean1.setAccAdminNameIt(accAdminNameIt[i]);
				bean1.setAccAdminCodeIt(accAdminCodeIt[i]);
				tableList.add(bean1);
			} 
		}
		System.out.println("--tableList SIZE---"+tableList.size());
		bean.setAccConfList(tableList);
	}
}


