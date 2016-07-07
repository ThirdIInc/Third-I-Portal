package org.paradyne.model.admin.srd;
import org.paradyne.lib.BeanBase;
 import org.paradyne.lib.ModelBase;
/*
 * Pradeep Kumar Sahoo
 * 
 */
import org.paradyne.bean.admin.srd.InitialAppointment;

public class InitialAppointmentModel extends ModelBase {  static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(ModelBase.class); 
	
	org.paradyne.bean.admin.srd.InitialAppointment initialAppt=null;
		
	
	public boolean addAppt(InitialAppointment bean){
		Object[][] addObj =new Object[1][17];
		
		addObj[0][0]= bean.getEmpId();//Employee Id
		addObj[0][1]=bean.getInitCentCode();//Initial Center Code 
		addObj[0][2]=bean.getInitRankCode();//Initial Rank Code
		addObj[0][3]=bean.getInitTradeCode();//Initial Trade Code
		addObj[0][4]=bean.getRtType();//Rt Type
		//addObj[0][5]=bean.getDceDate();
		logger.info("1"+addObj[0][0]);
		addObj[0][5]= bean.getDesgCode();//Initial Desig Code
		logger.info("2"+addObj[0][1]);
		addObj[0][6]= bean.getDtOfJnDockyard();//Date of join Dockyard
		logger.info("3"+addObj[0][2]);
		addObj[0][7]= bean.getDtOfJnServ();//Date of join service
		logger.info("4"+addObj[0][3]);
		addObj[0][8]= bean.getInitialJnDate();//Initial Joining Date
		logger.info("5"+addObj[0][4]);
		addObj[0][9]= bean.getSuperAnnDt();//Annuation Date
		addObj[0][10]= bean.getPayCode();//Pay Code
		addObj[0][11]= bean.getDceList();//DCE Letter
		addObj[0][12]=bean.getDceDate();//DCE Date
		
		addObj[0][13]= bean.getDoctName();//Doctor Name
		addObj[0][14]= bean.getDtOfExm();//Date of medical exam
		addObj[0][15]= bean.getHspName();//Hospital Name
		addObj[0][16]= bean.getRemarks();//Remarks
		
		
		boolean result=getSqlModel().singleExecute(getQuery(1),addObj);	
	//	if(result) {
			
	//		return "Record saved Successfully";
	//	} else {
	//		return "Record cnnot be added.";
	//	}
		return result;
		
	}
	
	public boolean modAppt(InitialAppointment bean) {
		
		Object[][] modAppt = new Object[1][17];
		
		modAppt[0][0]= bean.getDesgCode();//Designation
		modAppt[0][1]=bean.getInitCentCode();//Initial Center
		modAppt[0][2]=bean.getInitRankCode();//Initial Rank
		modAppt[0][3]=bean.getInitTradeCode();//Initial Trade
		modAppt[0][4]=bean.getRtType();//Rt Type
		
		modAppt[0][5]= bean.getDtOfJnDockyard();//Date of join Dockyard
		modAppt[0][6]= bean.getDtOfJnServ();//Date of join service
		modAppt[0][7]= bean.getInitialJnDate();//Initial Join Date
		modAppt[0][8]= bean.getSuperAnnDt();//Annuation Date
		modAppt[0][9]= bean.getPayCode();//Pay code
		modAppt[0][10]= bean.getDceList();//DCE Letter
		modAppt[0][11]=bean.getDceDate();//DCE DATE
		
		modAppt[0][12]= bean.getDoctName();//Doct Name
		modAppt[0][13]= bean.getDtOfExm();//Date of Med Exm
		modAppt[0][14]= bean.getHspName();//Hosp Name
		modAppt[0][15]= bean.getRemarks();//Remarks
		modAppt[0][16]= bean.getEmpId();//Emp code
		
		boolean result= getSqlModel().singleExecute(getQuery(2),modAppt);
		//if(result) {
		//	return "Record updated Successfully";
			
		//} else {
		//	return "Record cannot be modified"; 
			
	//	}
		return result;
	
		}
	public boolean deleteAppt(InitialAppointment bean){
		Object delObj [][]= new Object [1][1];
		delObj [0][0]=bean.getEmpId();//Employee Code
		return getSqlModel().singleExecute(getQuery(3),delObj);
	}
	
	
	public InitialAppointment getDesg(InitialAppointment initialAppt) {
		
		Object addObj[] =new Object[1];
		addObj[0]=initialAppt.getDesgCode();
		Object[][] data = getSqlModel().getSingleResult(getQuery(6),addObj);
		
		for(int i=0; i<data.length; i++) {
			logger.info("data.length1:"+data.length);
			
			
			initialAppt.setDesgCode(checkNull(String.valueOf(data[i][0])));
			initialAppt.setInitialDesg(checkNull(String.valueOf(data[i][1])));
		
				
		}
		
		return initialAppt;
	}
	
	
public InitialAppointment getPayScale(InitialAppointment initialAppt) {
		
		Object addObj[] =new Object[1];
		addObj[0]=initialAppt.getPayCode();
		Object[][] data = getSqlModel().getSingleResult(getQuery(7),addObj);
		
		for(int i=0; i<data.length; i++) {
			logger.info("data.length1:"+data.length);
			
			
			initialAppt.setPayCode(checkNull(String.valueOf(data[i][0])));
			initialAppt.setPayScale(checkNull(String.valueOf(data[i][1])));
		
				
		}
		
		return initialAppt;
	}
	
	
	public InitialAppointment  getRecord(InitialAppointment initialAppt) {
		logger.info("getRecord");
		
		Object addObj[] =new Object[1];
		
		addObj[0] =initialAppt.getEmpId();
		logger.info("addObj[0]***:"+addObj[0]);
		Object[][] data = getSqlModel().getSingleResult(getQuery(4),addObj);
		logger.info("data.length:"+data.length);
		for(int i=0; i<data.length; i++) {
			logger.info("data.length1:"+data.length);
			
			initialAppt.setEmpId(checkNull(String.valueOf(data[i][0])));//Employee Code
			initialAppt.setEmployeeToken(checkNull(String.valueOf(data[i][1])));//Token no.
			initialAppt.setEmpName(checkNull(String.valueOf(data[i][2])));//Employee Name
			//initialAppt.setEmpDept(checkNull(String.valueOf(data[i][2])));
			initialAppt.setEmpCent(checkNull(String.valueOf(data[i][3])));//Current Center
			initialAppt.setEmpRank(checkNull(String.valueOf(data[i][4])));
			initialAppt.setInitCentCode(checkNull(String.valueOf(data[i][5])));
			initialAppt.setInitCentName(checkNull(String.valueOf(data[i][6])));
			initialAppt.setInitRankCode(checkNull(String.valueOf(data[i][7])));
			initialAppt.setInitRankName(checkNull(String.valueOf(data[i][8])));
			initialAppt.setInitTradeCode(checkNull(String.valueOf(data[i][9])));
			initialAppt.setInitTradeName(checkNull(String.valueOf(data[i][10])));
			initialAppt.setRtType(checkNull(String.valueOf(data[i][11])));
			initialAppt.setDesgCode(checkNull(String.valueOf(data[i][12])));
			initialAppt.setInitialDesg(checkNull(String.valueOf(data[i][13])));
			initialAppt.setDtOfJnDockyard(checkNull(String.valueOf(data[i][14])));
			initialAppt.setDtOfJnServ(checkNull(String.valueOf(data[i][15])));
			initialAppt.setInitialJnDate(checkNull(String.valueOf(data[i][16])));
			initialAppt.setSuperAnnDt(checkNull(String.valueOf(data[i][17])));
			initialAppt.setPayCode(checkNull(String.valueOf(data[i][18])));
			
			initialAppt.setPayScale(checkNull(String.valueOf(data[i][19])));
			initialAppt.setDceList(checkNull(String.valueOf(data[i][20])));
			initialAppt.setDceDate(checkNull(String.valueOf(data[i][21])));
			
			initialAppt.setDtOfExm(checkNull(String.valueOf(data[i][22])));
			initialAppt.setDoctName(checkNull(String.valueOf(data[i][23])));
			initialAppt.setHspName(checkNull(String.valueOf(data[i][24])));
			initialAppt.setRemarks(checkNull(String.valueOf(data[i][25])));
			
		}
		return initialAppt;
	}
	
	
	public boolean checkID(InitialAppointment bean){
		boolean flag;
		Object addObj []=new Object[1];
		addObj [0]=bean.getEmpId();
		Object [][] data=getSqlModel().getSingleResult(getQuery(5),addObj);
		if(data.length==0){
			flag=false;
		}
		else{
			flag=true;
		}
		return flag;
	}
	
	public String checkNull(String result){
		if(result ==null ||result.equals("null")){
			return "";
		}
		else{
			return result;
		}
	}
	
	public void  getEmployeeDetails(String empId, InitialAppointment initialAppt)
	{
	Object[] beanObj = new Object[1];
	beanObj[0] =empId ;

     


	String query =" SELECT HRMS_EMP_OFFC.EMP_ID,EMP_TOKEN, "
		+ " HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
		+ "	HRMS_CENTER.CENTER_ID||'-'||HRMS_CENTER.CENTER_NAME ,HRMS_RANK.RANK_NAME "
		+ "	FROM HRMS_EMP_OFFC "
		
		+" LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
		//+ "	INNER JOIN HRMS_DEPT ON HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT " 
		+ "	INNER JOIN HRMS_CENTER ON HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC.EMP_CENTER "
		+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)"
		+"  WHERE HRMS_EMP_OFFC.EMP_ID = ?";
	Object[][] values = getSqlModel().getSingleResult(query, beanObj);
	logger.info("addApplication:-------------------"+values.length);
	logger.info("addApplication:-------------------"+String.valueOf(beanObj[0]));

	initialAppt.setEmpId(checkNull(String.valueOf(values[0][0])));
	initialAppt.setEmployeeToken(checkNull(String.valueOf(values[0][1])));
	initialAppt.setEmpName(checkNull(String.valueOf(values[0][2])));
	initialAppt.setEmpCent(checkNull(String.valueOf(values[0][3])));
	initialAppt.setEmpRank(checkNull(String.valueOf(values[0][4])));
	
}
	
	
	
		

  }
