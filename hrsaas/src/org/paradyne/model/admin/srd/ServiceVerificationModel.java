/**
 * 
 */
package org.paradyne.model.admin.srd;

import java.util.ArrayList;

import org.paradyne.bean.admin.srd.AwardDetails;
import org.paradyne.bean.admin.srd.ServiceVerification;
 import org.paradyne.lib.ModelBase;

/**
 * @author lakkichand
 *
 */
public class ServiceVerificationModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	
	
	
	public boolean addService(ServiceVerification bean){
		Object addObj[][]=new Object[1][11];
		
		addObj[0][0]=bean.getEmpCode();
		logger.info("********1:" +String.valueOf( bean.getEmpCode()));
		addObj[0][1]=bean.getFromDate();
		addObj[0][2]=bean.getToDate();
		addObj[0][3]=bean.getQualSerYear();
		addObj[0][4]=bean.getQualSerMonth();
		addObj[0][5]=bean.getQualSerDays();
		addObj[0][6]=bean.getAttestOfficer();
		addObj[0][7]=bean.getVerifOfficer();
		addObj[0][8]=bean.getPay();
		addObj[0][9]=bean.getPayID();
		addObj[0][10]=bean.getRemark();
		
		return getSqlModel().singleExecute(getQuery(1),addObj);
		
	}
	
	public boolean updateService(ServiceVerification bean){
		Object addObj[][]=new Object[1][12];
		
		addObj[0][0]=bean.getEmpCode();
		addObj[0][1]=bean.getFromDate();
		addObj[0][2]=bean.getToDate();
		addObj[0][3]=bean.getQualSerYear();
		addObj[0][4]=bean.getQualSerMonth();
		addObj[0][5]=bean.getQualSerDays();
		addObj[0][6]=bean.getAttestOfficer();
		addObj[0][7]=bean.getVerifOfficer();
		addObj[0][8]=bean.getPay();
		addObj[0][9]=bean.getPayID();
		System.out.println("Value of pay id"+addObj[0][9]);
		addObj[0][10]=bean.getRemark();
		addObj[0][11]=bean.getServiceID();
		
		return getSqlModel().singleExecute(getQuery(2),addObj);
		
	}
	
	public void getServiceRecord(ServiceVerification bean){
		
		Object [] addObj = new Object [1];
		addObj[0]=bean.getEmpCode();
		
		logger.info("********1:"+bean.getEmpCode());
		Object[][] data = getSqlModel().getSingleResult(getQuery(3),addObj);
		logger.info("********1:"+data.length);
		ArrayList<Object> serList=new ArrayList<Object>();
		
		for(int i=0;i<data.length;i++){
			ServiceVerification bean1=new ServiceVerification();
			
			bean1.setServiceID(checkNull(String.valueOf(data[i][0])));
			logger.info("********2:"+String.valueOf(data[0][1]));
			bean1.setEmpCode(checkNull(String.valueOf(data[i][1])));
			bean1.setFromDate(checkNull(String.valueOf(data[i][2])));
			bean1.setToDate(checkNull(String.valueOf(data[i][3])));
			bean1.setQualSerYear(checkNull(String.valueOf(data[i][4])));
			bean1.setQualSerMonth(checkNull(String.valueOf(data[i][5])));
			bean1.setQualSerDays(checkNull(String.valueOf(data[i][6])));
			
			/*bean1.setAttestOfficer(String.valueOf(data[i][7]));
			bean1.setVerifOfficer(String.valueOf(data[i][8]));*/
			bean1.setPay(checkNull(String.valueOf(data[i][7])));
			bean1.setPayScale(checkNull(String.valueOf(data[i][8])));
			logger.info("********1:"+String.valueOf(data[i][7]));
			logger.info("********1:"+String.valueOf(data[i][8]));
			/*bean1.setRemark(String.valueOf(data[i][11]));*/
			
			serList.add(bean1);
			logger.info("********3:"+serList.listIterator());
			
		}
		bean.setServiceList(serList);
		
	}
	
	public  void getRecord(ServiceVerification bean){
		
		Object addObj[]=new Object[2];
		addObj[0]=bean.getParaID();
		addObj[1]=bean.getEmpCode();
		System.out.println("service id"+addObj[0]);
		System.out.println("emp id"+addObj[1]);
		Object[][] data = getSqlModel().getSingleResult(getQuery(4),addObj);
		
		
			bean.setServiceID(checkNull(String.valueOf(data[0][0])));
			logger.info("********2:"+String.valueOf(data[0][1]));
			bean.setEmpCode(checkNull(String.valueOf(data[0][1])));
			bean.setFromDate(checkNull(String.valueOf(data[0][2])));
			bean.setToDate(checkNull(String.valueOf(data[0][3])));
			bean.setQualSerYear(checkNull(String.valueOf(data[0][4])));
			bean.setQualSerMonth(checkNull(String.valueOf(data[0][5])));
			bean.setQualSerDays(checkNull(String.valueOf(data[0][6])));
			bean.setAttestOfficer(checkNull(String.valueOf(data[0][7])));
			bean.setVerifOfficer(checkNull(String.valueOf(data[0][8])));
			bean.setPay(checkNull(String.valueOf(data[0][9])));
			
			bean.setPayScale(checkNull(String.valueOf(data[0][10])));
			bean.setRemark(checkNull(String.valueOf(data[0][11])));
			bean.setPayID(checkNull(String.valueOf(data[0][12])));
			System.out.println("Value of service pay id"+String.valueOf(data[0][12]));
		
		
	}
	
	public boolean delServiceRecord(ServiceVerification bean){
		Object addObj[][]=new Object[1][1];
		
		addObj[0][0]=bean.getParaID();
		return getSqlModel().singleExecute(getQuery(5),addObj);
		
	}

	public String checkNull(String result){
		if(result ==null ||result.equals("null")||result.equals("")){
			return "";
		}
		else{
			return result;
		}
		}
	
	
	
	public void  getEmployeeDetails(String EmpCode, ServiceVerification servVerification)
	{
	Object[] beanObj = new Object[1];
	beanObj[0] =EmpCode ;

     


	String query =" SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN ,"
		+ "	TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||'  '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
		+ "	HRMS_RANK.RANK_NAME,EMP_CENTER||'-'||HRMS_CENTER.CENTER_NAME ,PAYSC_NAME,CREDIT_AMT"
		+ "	FROM HRMS_EMP_OFFC "
		+ "	INNER JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK " 
		+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
		+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
		+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)"
		+" LEFT JOIN HRMS_EMP_CREDIT ON(HRMS_EMP_CREDIT.EMP_ID=HRMS_EMP_OFFC.EMP_ID AND CREDIT_CODE=1) "
		+" LEFT JOIN HRMS_PAYSCALE ON(HRMS_PAYSCALE.PAYSC_ID=HRMS_SALARY_MISC.SAL_PAYSCALE)"
		+"  WHERE HRMS_EMP_OFFC.EMP_ID = ?";
	Object[][] values = getSqlModel().getSingleResult(query, beanObj);
	logger.info("addApplication:-------------------"+values.length);
	logger.info("addApplication:-------------------"+String.valueOf(beanObj[0]));

	servVerification.setEmpCode(checkNull(String.valueOf(values[0][0])));
	servVerification.setEmpToken(checkNull(String.valueOf(values[0][1])));
	servVerification.setEmpName(checkNull(String.valueOf(values[0][2])));
	servVerification.setRank(checkNull(String.valueOf(values[0][3])));
	servVerification.setCenter(checkNull(String.valueOf(values[0][4])));
	//servVerification.setPayScale(checkNull(String.valueOf(values[0][5])));
	//servVerification.setPay(checkNull(String.valueOf(values[0][6])));
	
	getServiceRecord(servVerification);

}
	

}
