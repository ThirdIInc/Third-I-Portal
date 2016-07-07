/**
 * 
 */
package org.paradyne.model.TravelManagement.Master;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.TravelManagement.Master.TravelAuthorities;
import org.paradyne.lib.ModelBase;

/**
 * @author aa0651
 *
 */
public class TravelAuthoritiesModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	
	
	public boolean addTravelAuth(TravelAuthorities bean, String[] schEmpId, String[] aprEmpId, String[] accEmpId) {
		// TODO Auto-generated method stub
	
		Object[][] add=null;
		Object[][] addObj=null;
		boolean result=false;
		boolean res=false;		
		try{
			Object maxCodeObj[][] = getSqlModel().
					getSingleResult("SELECT NVL(MAX(MANG_AUTH_ID),0) FROM HRMS_TMS_MANG_AUTH");
			int maxCode = Integer.parseInt(""+maxCodeObj[0][0]);
			
	
			Object maxCodeObjDtl[][] = getSqlModel().
			getSingleResult("SELECT NVL(MAX(MANG_ALTER_AUTH_ID),0) FROM HRMS_TMS_MANG_ALTER_AUTH");
			int maxCodeDtl = Integer.parseInt(""+maxCodeObjDtl[0][0]);
			
			
			if(!bean.getBranchId().equals(""))
			{
				
				add = new Object[1][8];
				add[0][0]=++maxCode;
				add[0][1]=bean.getBranchId().trim();
				add[0][2]=bean.getHidSchCode();	
				add[0][3]=bean.getHidApprover();
				add[0][4]=bean.getHidAccName();
				add[0][5]=bean.getDescription();
				add[0][6]=bean.getStatus();
				add[0][7]=bean.getHidBranchFlag();
				
				
				result=getSqlModel().singleExecute(getQuery(1),add);
				if(result){
					String maxQuery="SELECT MAX(MANG_AUTH_ID),MAX(MANG_AUTH_DEFAULT_FLAG) FROM HRMS_TMS_MANG_AUTH";
					Object [][]data=getSqlModel().getSingleResult(maxQuery);
					bean.setTravelAuth(String.valueOf(data[0][0]));
					//logger.info(">>>>>>>>>>>>>vvvvvvvvv"+String.valueOf(data[0][1]));
					if(bean.getHidBranchFlag().equals("Y")){
						logger.info("++++++++++===========Inside if-------------");
						String upQuery="UPDATE HRMS_TMS_MANG_AUTH SET MANG_AUTH_DEFAULT_FLAG='N' WHERE MANG_AUTH_ID != "+String.valueOf(data[0][0]);
						getSqlModel().singleExecute(upQuery);
						
					}
					
				
					int arrLength = 0;
					if(schEmpId != null)
						arrLength+=schEmpId.length;
					if(aprEmpId != null)
						arrLength+=aprEmpId.length;
					if(accEmpId != null)
						arrLength+=accEmpId.length;
					if(arrLength > 0)
					{
					addObj = new Object[arrLength][9];
					int z = 0;
					if(schEmpId != null)
					for (int i = 0; i < schEmpId.length; i++) {
						addObj[z][0]=++maxCodeDtl;						
						addObj[z][1] = bean.getBranchId();
						addObj[z][2]=schEmpId[i];	
						addObj[z][3]="";
						addObj[z][4]="";
						addObj[z][5]=bean.getHidSchCode();	
						addObj[z][6]="";
						addObj[z][7]="";
						addObj[z][8]=bean.getTravelAuth();
						z++;
					}
					if(aprEmpId != null)
					for (int i = 0; i < aprEmpId.length; i++) {
						addObj[z][0]=++maxCodeDtl;
						addObj[z][1] = bean.getBranchId();
						addObj[z][2]="";	
						addObj[z][3]=aprEmpId[i];
						addObj[z][4]="";
						addObj[z][5]="";	
						addObj[z][6]=bean.getHidApprover();
						addObj[z][7]="";
						addObj[z][8]=bean.getTravelAuth();
						z++;
					}
					if(accEmpId != null)
					for (int i = 0; i < accEmpId.length; i++) {
						addObj[z][0]=++maxCodeDtl;
						addObj[z][1] = bean.getBranchId();
						addObj[z][2]="";	
						addObj[z][3]="";
						addObj[z][4]=accEmpId[i];
						addObj[z][5]="";	
						addObj[z][6]="";
						addObj[z][7]=bean.getHidAccName();
						addObj[z][8]=bean.getTravelAuth();
						z++;
					}
					result=getSqlModel().singleExecute(getQuery(4),addObj);
					
					
					}
				}else{
					return result;
				}
				
			}
			/*else
			{
				Object[][] branchObj = getSqlModel().getSingleResult("SELECT CENTER_ID FROM HRMS_CENTER");
				int arrLength = 0;
				if(schEmpId != null)
					arrLength+=schEmpId.length;
				if(aprEmpId != null)
					arrLength+=aprEmpId.length;
				if(accEmpId != null)
					arrLength+=accEmpId.length;
				addObj = new Object[arrLength*branchObj.length][8];		
				//add=new Object[branchObj.length][7];				
				int z = 0; 
				for (int j = 0; j < branchObj.length; j++) {
					add[k][0]=++maxCode;
					add[k][1]=branchObj[j][0];
					add[k][2]=bean.getHidSchCode();	
					add[k][3]=bean.getHidApprover();
					add[k][4]=bean.getHidAccName();
					add[k][5]=bean.getDescription();
					add[k][6]=bean.getStatus();
					boolean dupFlag=false;
					try{ 
					
					String SQL = " INSERT INTO HRMS_TMS_MANG_AUTH(MANG_AUTH_ID,MANG_AUTH_BRANCH,MANG_AUTH_SCHEDULER,MANG_AUTH_SCH_APPROVER"
						+ " ,MANG_AUTH_ACCOUNT_PER,MANG_AUTH_DESC,MANG_AUTH_STATUS	) " 
						+ " VALUES((SELECT  NVL(MAX(MANG_AUTH_ID),0)+1 FROM HRMS_TMS_MANG_AUTH),"+branchObj[j][0]+","+bean.getHidSchCode()+"," +
						""+bean.getHidApprover()+","+bean.getHidAccName()+",'"+bean.getDescription()+"','"+bean.getStatus()+"')";
					dupFlag =getSqlModel().singleExecute(SQL); 
					 	
					}catch (Exception e) {
						System.out.println("dupilcate found ");
						e.printStackTrace();
					}
					if(dupFlag)
					{
						if(schEmpId != null)
						for (int i = 0; i < schEmpId.length; i++) {
							addObj[z][0]=++maxCodeDtl;						
							addObj[z][1] = branchObj[j][0];
							addObj[z][2]=schEmpId[i];	
							addObj[z][3]="";
							addObj[z][4]="";
							addObj[z][5]=bean.getHidSchCode();	
							addObj[z][6]="";
							addObj[z][7]="";
							z++;
						}
						if(aprEmpId != null)
						for (int i = 0; i < aprEmpId.length; i++) {
							addObj[z][0]=++maxCodeDtl;
							addObj[z][1] = branchObj[j][0];
							addObj[z][2]="";	
							addObj[z][3]=aprEmpId[i];
							addObj[z][4]="";
							addObj[z][5]="";	
							addObj[z][6]=bean.getHidApprover();
							addObj[z][7]="";
							z++;
						}
						if(accEmpId != null)
						for (int i = 0; i < accEmpId.length; i++) {
							addObj[z][0]=++maxCodeDtl;
							addObj[z][1] = branchObj[j][0];
							addObj[z][2]="";	
							addObj[z][3]="";
							addObj[z][4]=accEmpId[i];
							addObj[z][5]="";	
							addObj[z][6]="";
							addObj[z][7]=bean.getHidAccName();
							z++;
						}
					}
				}
				
				result=getSqlModel().singleExecute(getQuery(4),addObj);
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}		
		if(result){
			String query = " SELECT MAX(MANG_AUTH_ID) FROM HRMS_TMS_MANG_AUTH" ;
			
			Object[][] data = getSqlModel().getSingleResult(query);
			bean.setTravelAuth(String.valueOf((data[0][0])));
			
			
			String queryDtl = " SELECT MAX(MANG_AUTH_APP_ID) FROM HRMS_TMS_MANG_ALTER_AUTH" ;			
			Object[][] dataDtl = getSqlModel().getSingleResult(query);
			bean.setHidDtlCode(String.valueOf((dataDtl[0][0])));
			
			
			System.out.println("data[0][0]---->"+data[0][0]);
			
				String query1 = "SELECT HRMS_CENTER.CENTER_NAME,"
				+ " e1.EMP_FNAME || ' ' || e1.EMP_MNAME || ' ' || e1.EMP_LNAME,"
				+ " e2.EMP_FNAME || ' ' || e2.EMP_MNAME || ' ' || e2.EMP_LNAME,"  
				+ " e3.EMP_FNAME || ' ' || e3.EMP_MNAME || ' ' || e3.EMP_LNAME," 
				+ " NVL(MANG_AUTH_DESC,' '),CASE WHEN MANG_AUTH_STATUS='A' THEN 'Active' ELSE 'Deactive' END,MANG_AUTH_ID,"
				+ " MANG_AUTH_BRANCH,MANG_AUTH_SCHEDULER,MANG_AUTH_SCH_APPROVER,MANG_AUTH_ACCOUNT_PER"   
				+ " FROM HRMS_TMS_MANG_AUTH"
				+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_BRANCH)"
				+ " LEFT JOIN HRMS_EMP_OFFC e1 ON(e1.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_SCHEDULER)"
				+ " LEFT JOIN HRMS_EMP_OFFC e2 ON(e2.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_SCH_APPROVER)"
				+ " LEFT JOIN HRMS_EMP_OFFC e3 ON(e3.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_ACCOUNT_PER)"
				+ " WHERE MANG_AUTH_ID="+String.valueOf(data[0][0]);
				
			
			/*String query1 = " SELECT ROOM_TYPE_NAME	,CASE WHEN ROOM_TYPE_STATUS	='A' THEN 'Active' ELSE 'Deactive' END,NVL(ROOM_TYPE_DESC,' '),ROOM_TYPE_ID FROM "
						   +" HRMS_TMS_ROOM_TYPE WHERE ROOM_TYPE_ID="+String.valueOf(data[0][0]);*/
			
			logger.info("query1 in addDesignation---->"+query1);
			
			Object[][] Data = getSqlModel().getSingleResult(query1);
			
			bean.setBranchName(checkNull(String.valueOf(Data[0][0])).trim());				
			bean.setSchName(checkNull(String.valueOf(Data[0][1])).trim());			
			bean.setApproverName(checkNull(String.valueOf(Data[0][2])).trim());			
			bean.setAccName(checkNull(String.valueOf(Data[0][3])).trim());
			bean.setDescription(checkNull(String.valueOf(Data[0][4])).trim());
			bean.setStatus(checkNull(String.valueOf(Data[0][5])).trim());
				
	}
		
		return result;
		
	}
	
	public boolean modTravelAuth(TravelAuthorities bean,HttpServletRequest request) {
		// TODO Auto-generated method stub
		String delDtlSch="DELETE FROM HRMS_TMS_MANG_ALTER_AUTH WHERE MANG_ALTER_AUTH_BRANCH="+bean.getBranchId()
		+ " AND MANG_AUTH_SCHDULER_EMPID="+bean.getHidSchCode();
		getSqlModel().singleExecute(delDtlSch);
		
		String[] empId = request.getParameterValues("itEmpId");
		String[] empName = request.getParameterValues("itEmpName");
		String[] empToken = request.getParameterValues("empToken");
		String[] empCheck = request.getParameterValues("empCheck");
		
		addSchedular(bean,empId,empToken,empName,empCheck);
		
		// TODO Auto-generated method stub
		
		
		String delDtlApr="DELETE FROM HRMS_TMS_MANG_ALTER_AUTH WHERE MANG_ALTER_AUTH_BRANCH="+bean.getBranchId()
		+ " AND MANG_AUTH_SCHDULER_EMPID="+bean.getHidApprover();
		getSqlModel().singleExecute(delDtlApr);
		
		
		String delDtlAcc="DELETE FROM HRMS_TMS_MANG_ALTER_AUTH WHERE MANG_ALTER_AUTH_BRANCH="+bean.getBranchId()
		+ " AND MANG_AUTH_SCHDULER_EMPID="+bean.getHidAccName();
		getSqlModel().singleExecute(delDtlAcc);
		
		
		String delHdr="UPDATE HRMS_TMS_MANG_AUTH SET WHERE MANG_AUTH_ID="+bean.getTravelAuth();
		getSqlModel().singleExecute(delHdr);
		Object mod[][] = new Object[1][6];		
		mod[0][0]=bean.getBranchId().trim();			
		mod[0][1]=bean.getHidSchCode();		
		mod[0][2]=bean.getHidApprover();
		mod[0][3]=bean.getHidAccName();
		mod[0][4]=bean.getDescription();
		mod[0][5]=bean.getStatus();
		mod[0][6]=bean.getTravelAuth();	
		boolean res= getSqlModel().singleExecute(getQuery(2), mod);
		return false;
	}
	
	
	public boolean checkDuplicate(TravelAuthorities bean) {
		boolean result = false;
		String query = "SELECT * FROM  HRMS_TMS_MANG_AUTH WHERE MANG_AUTH_BRANCH !="+bean.getBranchId();
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;
	}
	
	public void reqData(TravelAuthorities bean,HttpServletRequest request){
		logger.info("---------Inside reqData---------");
		try{
		
		String query = "SELECT HRMS_CENTER.CENTER_NAME,"
			+ " e1.EMP_FNAME || ' ' || e1.EMP_MNAME || ' ' || e1.EMP_LNAME,"
			+ " e2.EMP_FNAME || ' ' || e2.EMP_MNAME || ' ' || e2.EMP_LNAME,"  
			+ " e3.EMP_FNAME || ' ' || e3.EMP_MNAME || ' ' || e3.EMP_LNAME," 
			+ " CASE WHEN MANG_AUTH_STATUS='A' THEN 'Active' ELSE 'Deactive' END,MANG_AUTH_ID,MANG_AUTH_DEFAULT_FLAG,NVL(MANG_AUTH_DESC,' '),"
			+ " MANG_AUTH_BRANCH,MANG_AUTH_SCHEDULER,MANG_AUTH_SCH_APPROVER,MANG_AUTH_ACCOUNT_PER"   
			+ " FROM HRMS_TMS_MANG_AUTH"
			+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_BRANCH)"
			+ " LEFT JOIN HRMS_EMP_OFFC e1 ON(e1.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_SCHEDULER)"
			+ " LEFT JOIN HRMS_EMP_OFFC e2 ON(e2.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_SCH_APPROVER)"
			+ " LEFT JOIN HRMS_EMP_OFFC e3 ON(e3.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_ACCOUNT_PER)"
			+ " WHERE EMP_STATUS ='S'  ORDER BY MANG_AUTH_DEFAULT_FLAG DESC	";

		Object[][] data = getSqlModel().getSingleResult(query);
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
		int pg1=0;
		int PageNo1=1;//----------
		REC_TOTAL = data.length;
		int no_of_pages=Math.round(REC_TOTAL/20);
		double row = (double)data.length/20.0;
   
      java.math.BigDecimal value1 = new java.math.BigDecimal(row);
     
      int rowCount1 =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
    
	
	ArrayList<Object> obj=new ArrayList<Object>();
	System.out.println("val of riwC"+rowCount1);
	request.setAttribute("abc", rowCount1);

	//PageNo
	if(String.valueOf(bean.getMyPage()).equals("null")||String.valueOf(bean.getMyPage()).equals(null)||String.valueOf(bean.getMyPage()).equals(" "))
	{
		PageNo1=1;
		From_TOT=0;
		  To_TOT=20;

		  if(To_TOT >data.length){
			  To_TOT=data.length;
		  }
			
			bean.setMyPage("1");
	}
	
	
	else{
			
		  pg1=	Integer.parseInt(bean.getMyPage());
		  PageNo1=pg1;
		  
		  if(pg1 ==1){
			 From_TOT=0;
			 To_TOT=20;
		  }
		  else{
			//  From_TOTAL=To_TOTAL+1;
				 To_TOT=To_TOT*pg1;
				 From_TOT=(To_TOT-20);
		  }
		  if(To_TOT >data.length){
			  To_TOT=data.length;
		  }
	  }
	request.setAttribute("xyz", PageNo1);
	  
	  for(int i=From_TOT;i<To_TOT;i++){
                 //setting 
		  TravelAuthorities  bean1 = new TravelAuthorities();
			
		bean1.setBranchName(String.valueOf(data[i][0]).trim());
		bean1.setSchName(String.valueOf(data[i][1]));
		bean1.setApproverName(String.valueOf(data[i][2]));		
		bean1.setAccName(String.valueOf(data[i][3]));
		bean1.setStatus(String.valueOf(data[i][4]));
		bean1.setTravelAuth(String.valueOf(data[i][5]));
		logger.info("-------String.valueOf(data[i][6])----------"+String.valueOf(data[i][6]));
		if(String.valueOf(data[i][6]).equals("Y")){
		bean1.setHidBranchFlag("Yes");
		}else{
			bean1.setHidBranchFlag("No");
		}
		obj.add(bean1);
	  }
	
	
		
	  bean.setTypeList(obj);
	}catch(Exception e){
		e.printStackTrace();
	}
	
	}	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	
	
	
	/**
	 * following function is called when  a record is selected from search window to set the records.
	 * @param bean
	 */
	public void getTravelAuth(TravelAuthorities bean) {
		// TODO Auto-generated method stub
		try{
			
			String query = "SELECT HRMS_CENTER.CENTER_NAME,"
				+ " e1.EMP_FNAME || ' ' || e1.EMP_MNAME || ' ' || e1.EMP_LNAME,"
				+ " e2.EMP_FNAME || ' ' || e2.EMP_MNAME || ' ' || e2.EMP_LNAME,"  
				+ " e3.EMP_FNAME || ' ' || e3.EMP_MNAME || ' ' || e3.EMP_LNAME," 
				+ " NVL(MANG_AUTH_DESC,' '),CASE WHEN MANG_AUTH_STATUS='A' THEN 'Active' ELSE 'Deactive' END,MANG_AUTH_DEFAULT_FLAG,MANG_AUTH_ID,"
				+ " MANG_AUTH_BRANCH,MANG_AUTH_SCHEDULER,MANG_AUTH_SCH_APPROVER,MANG_AUTH_ACCOUNT_PER"   
				+ " FROM HRMS_TMS_MANG_AUTH"
				+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_BRANCH)"
				+ " LEFT JOIN HRMS_EMP_OFFC e1 ON(e1.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_SCHEDULER)"
				+ " LEFT JOIN HRMS_EMP_OFFC e2 ON(e2.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_SCH_APPROVER)"
				+ " LEFT JOIN HRMS_EMP_OFFC e3 ON(e3.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_ACCOUNT_PER)"
				+" WHERE MANG_AUTH_ID="+bean.getTravelAuth();
				
				
			/*String query = " SELECT ROOM_TYPE_NAME,NVL(ROOM_TYPE_DESC,' '),CASE WHEN ROOM_TYPE_STATUS='A' THEN 'Active' ELSE 'Deactive' END,ROOM_TYPE_ID  FROM HRMS_TMS_ROOM_TYPE  "
				+" WHERE ROOM_TYPE_ID="+bean.getTypeId();*/

		Object[][] data = getSqlModel().getSingleResult(query);
		
		if(data!=null && data.length>0){
			
				bean.setBranchName(checkNull(String.valueOf(data[0][0]).trim()));				
				bean.setSchName(checkNull(String.valueOf(data[0][1])));				
				bean.setApproverName(checkNull(String.valueOf(data[0][2])));
				bean.setAccName(checkNull(String.valueOf(data[0][3])));
				bean.setDescription(checkNull(String.valueOf(data[0][4])));
				bean.setStatus(checkNull(String.valueOf(data[0][5])));
				bean.setAppFlag(""+(data[0][6].equals("Y")?"checked":""));
				bean.setHidBranchFlag(checkNull(String.valueOf(data[0][6])));
				bean.setTravelAuth(checkNull(String.valueOf(data[0][7])));				
				bean.setBranchId(checkNull(String.valueOf(data[0][8])));
				bean.setHidSchCode(checkNull(String.valueOf(data[0][9])));
				bean.setHidApprover(checkNull(String.valueOf(data[0][10])));
				bean.setHidAccName(checkNull(String.valueOf(data[0][11])));
				logger.info("------getTravelAuthEdit---"+bean.getBranchId());
				logger.info("------getTravelAuthEdit---"+bean.getHidSchCode());
				logger.info("------getTravelAuthEdit---"+bean.getHidApprover());
				logger.info("------getTravelAuthEdit---"+bean.getHidAccName());
				
		     }
		 }catch(Exception e){
			e.printStackTrace();
	   }
		
	}
	public void getTravelAuthEdit(TravelAuthorities bean) {
		// TODO Auto-generated method stub
		try{
			
			String query = "SELECT HRMS_CENTER.CENTER_NAME,"
				+ " e1.EMP_FNAME || ' ' || e1.EMP_MNAME || ' ' || e1.EMP_LNAME,"
				+ " e2.EMP_FNAME || ' ' || e2.EMP_MNAME || ' ' || e2.EMP_LNAME,"  
				+ " e3.EMP_FNAME || ' ' || e3.EMP_MNAME || ' ' || e3.EMP_LNAME," 
				+ " NVL(MANG_AUTH_DESC,' '),MANG_AUTH_STATUS,MANG_AUTH_DEFAULT_FLAG,MANG_AUTH_ID,"
				+ " MANG_AUTH_BRANCH,MANG_AUTH_SCHEDULER,MANG_AUTH_SCH_APPROVER,MANG_AUTH_ACCOUNT_PER"   
				+ " FROM HRMS_TMS_MANG_AUTH"
				+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_BRANCH)"
				+ " LEFT JOIN HRMS_EMP_OFFC e1 ON(e1.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_SCHEDULER)"
				+ " LEFT JOIN HRMS_EMP_OFFC e2 ON(e2.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_SCH_APPROVER)"
				+ " LEFT JOIN HRMS_EMP_OFFC e3 ON(e3.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_ACCOUNT_PER)"
				+" WHERE MANG_AUTH_ID="+bean.getTravelAuth();
				
				
			/*String query = " SELECT ROOM_TYPE_NAME,NVL(ROOM_TYPE_DESC,' '),CASE WHEN ROOM_TYPE_STATUS='A' THEN 'Active' ELSE 'Deactive' END,ROOM_TYPE_ID  FROM HRMS_TMS_ROOM_TYPE  "
				+" WHERE ROOM_TYPE_ID="+bean.getTypeId();*/

		Object[][] data = getSqlModel().getSingleResult(query);
		
		if(data!=null && data.length>0){
			
				bean.setBranchName(checkNull(String.valueOf(data[0][0]).trim()));				
				bean.setSchName(checkNull(String.valueOf(data[0][1])));				
				bean.setApproverName(checkNull(String.valueOf(data[0][2])));
				bean.setAccName(checkNull(String.valueOf(data[0][3])));
				bean.setDescription(checkNull(String.valueOf(data[0][4])));
				bean.setStatus(checkNull(String.valueOf(data[0][5])));
				bean.setAppFlag(""+(data[0][6].equals("Y")?"checked":""));
				bean.setHidBranchFlag(checkNull(String.valueOf(data[0][6])));
				bean.setTravelAuth(checkNull(String.valueOf(data[0][7])));
				bean.setBranchId(checkNull(String.valueOf(data[0][8])));
				bean.setHidSchCode(checkNull(String.valueOf(data[0][9])));
				bean.setHidApprover(checkNull(String.valueOf(data[0][10])));
				bean.setHidAccName(checkNull(String.valueOf(data[0][11])));
				logger.info("------getTravelAuthEdit---"+bean.getBranchId());
				logger.info("------getTravelAuthEdit---"+bean.getHidSchCode());
				logger.info("------getTravelAuthEdit---"+bean.getHidApprover());
				logger.info("------getTravelAuthEdit---"+bean.getHidAccName());
				
				
				
		     }
		 }catch(Exception e){
			e.printStackTrace();
	   }
		
	}

	public boolean deleteTravelAuth(TravelAuthorities bean) {
		// TODO Auto-generated method stub
		String delDtlSch="DELETE FROM HRMS_TMS_MANG_ALTER_AUTH WHERE MANG_ALTER_AUTH_BRANCH="+bean.getBranchId()
		+ " AND MANG_AUTH_SCHDULER_EMPID="+bean.getHidSchCode();
		getSqlModel().singleExecute(delDtlSch);
		
		String delDtlApr="DELETE FROM HRMS_TMS_MANG_ALTER_AUTH WHERE MANG_ALTER_AUTH_BRANCH="+bean.getBranchId()
		+ " AND MANG_AUTH_SCHDULE_APPR_EMPID="+bean.getHidApprover();
		getSqlModel().singleExecute(delDtlApr);
		
		
		String delDtlAcc="DELETE FROM HRMS_TMS_MANG_ALTER_AUTH WHERE MANG_ALTER_AUTH_BRANCH="+bean.getBranchId()
		+ " AND MANG_AUTH_ACCOUNT_PER_EMPID="+bean.getHidAccName();
		getSqlModel().singleExecute(delDtlAcc);
		
		Object del[][] = new Object[1][1];		
		del[0][0] = bean.getTravelAuth();				
		return getSqlModel().singleExecute(getQuery(3), del);
	}

	
	
	public void getTravelAuthOnDoubleClick(TravelAuthorities bean){
		logger.info("---getTravelAuthOnDoubleClick----------");
		try{
			
			
			String query = "SELECT HRMS_CENTER.CENTER_NAME,"
				+ " e1.EMP_FNAME || ' ' || e1.EMP_MNAME || ' ' || e1.EMP_LNAME,"
				+ " e2.EMP_FNAME || ' ' || e2.EMP_MNAME || ' ' || e2.EMP_LNAME,"  
				+ " e3.EMP_FNAME || ' ' || e3.EMP_MNAME || ' ' || e3.EMP_LNAME," 
				+ " NVL(MANG_AUTH_DESC,' '),MANG_AUTH_STATUS,MANG_AUTH_ID,"
				+ " MANG_AUTH_BRANCH,MANG_AUTH_SCHEDULER,MANG_AUTH_SCH_APPROVER,MANG_AUTH_ACCOUNT_PER,MANG_AUTH_DEFAULT_FLAG"   
				+ " FROM HRMS_TMS_MANG_AUTH"
				+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_BRANCH)"
				+ " LEFT JOIN HRMS_EMP_OFFC e1 ON(e1.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_SCHEDULER)"
				+ " LEFT JOIN HRMS_EMP_OFFC e2 ON(e2.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_SCH_APPROVER)"
				+ " LEFT JOIN HRMS_EMP_OFFC e3 ON(e3.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_ACCOUNT_PER)"
				+" WHERE MANG_AUTH_ID="+bean.getHiddencode();
		
		/*String query = " SELECT NVL(ROOM_TYPE_NAME,' '),CASE WHEN ROOM_TYPE_STATUS='A' THEN 'Active' ELSE 'Deactive' END,NVL(ROOM_TYPE_DESC,' '),ROOM_TYPE_ID  FROM HRMS_TMS_ROOM_TYPE"
			+" WHERE ROOM_TYPE_ID="+bean.getHiddencode();*/

	Object[][] data = getSqlModel().getSingleResult(query);
	
	if(data!=null && data.length>0){
		
		bean.setBranchName(checkNull(String.valueOf(data[0][0]).trim()));				
		bean.setSchName(checkNull(String.valueOf(data[0][1])));
		bean.setApproverName(checkNull(String.valueOf(data[0][2])));		
		bean.setAccName(checkNull(String.valueOf(data[0][3])));
		bean.setDescription(checkNull(String.valueOf(data[0][4])));
		bean.setStatus(checkNull(String.valueOf(data[0][5])));
		bean.setTravelAuth(checkNull(String.valueOf(data[0][6])));
		bean.setBranchId(checkNull(String.valueOf(data[0][7])));
		bean.setHidSchCode(checkNull(String.valueOf(data[0][8])));
		bean.setHidApprover(checkNull(String.valueOf(data[0][9])));
		bean.setHidAccName(checkNull(String.valueOf(data[0][10])));
		bean.setAppFlag(""+(data[0][11].equals("Y")?"checked":""));
		bean.setHidBranchFlag(checkNull(String.valueOf(data[0][11])));
		logger.info("============branch flag------------"+String.valueOf(data[0][11]));
		/*if(String.valueOf(data[0][11]).equals("Y")){
			bean.setAppFlag("true");
		}
		//bean.setHidBranchFlag(checkNull(String.valueOf(data[0][11])));
		// bean1.setGrCheck(""+(result[i][4].equals("Y")?"checked":""));
		// bean1.setHidCheck(""+(result[i][4]));
*/	     }
	 }catch(Exception e){
		e.printStackTrace();
   }
	 
}	 
	
	public boolean delChkdRec(TravelAuthorities bean,String[] code){
		
		
		int count=0;
		boolean result=false;
		for(int i=0;i<code.length;i++){
			if(!code[i].equals("")){
				
				Object [][] delete = new Object [1][1];
				delete [0][0] =code[i] ;
				result=getSqlModel().singleExecute(getQuery(3), delete);
				if(!result){
					count++;
				}
				}
	  	}
		
		
		if(count!=0){
			result=false;
			return result;
		}else {
			result=true;
			return result;
		}
	}

	public void getDesc(TravelAuthorities travel) {
		// TODO Auto-generated method stub
		String query="SELECT NVL(MANG_AUTH_DESC,' ') FROM HRMS_TMS_MANG_AUTH WHERE MANG_AUTH_ID="+travel.getTravelAuth();
		Object[][] data = getSqlModel().getSingleResult(query);
		travel.setDescription(String.valueOf(data[0][0]));
	}

	public void addSchedular(TravelAuthorities travel,String[] empId, String[] empToken, String[]empName, String[] check) {
		// TODO Auto-generated method stub
		String addQurey=" SELECT EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC"
					  + " WHERE EMP_ID = "+travel.getAltSchCode();
		Object[][] addData = getSqlModel().getSingleResult(addQurey);
		ArrayList list = new ArrayList();
		if(empId != null && empId.length > 0)
		{
			for(int i=0;i<empId.length;i++)
			{
				TravelAuthorities bean = new TravelAuthorities();
				bean.setItEmpId(empId[i]);
				bean.setEmpToken(empToken[i]);
				bean.setItEmpName(empName[i]);
				list.add(bean);
			}
		}
		TravelAuthorities bean = new TravelAuthorities();
		bean.setEmpToken(""+addData[0][0]);
		bean.setItEmpName(""+addData[0][1]);
		bean.setItEmpId(""+addData[0][2]);
		list.add(bean);
		travel.setSchlist(list);

	}

	public boolean delDtl(TravelAuthorities travel, String[] code, String[] empToken, String[] itEmpName, String[] itEmpId) {
		// TODO Auto-generated method stub
		try{
			ArrayList<Object> list=new ArrayList<Object>();
			
			
			if(empToken!=null)
			{   
				for(int i=0;i<empToken.length;i++){
					TravelAuthorities bean=new TravelAuthorities();
					if((String.valueOf(code[i]).equals(""))){
						bean.setEmpToken(empToken[i]);
						bean.setItEmpName(itEmpName[i]);
						bean.setItEmpId(itEmpId[i]);
						list.add(bean);
						
					}
					
					}
			}
			travel.setSchlist(list);
			}
			
		
			catch (Exception e) {
				e.printStackTrace();
				
				// TODO: handle exception
			}
			return true;
	}

	public void addApprover(TravelAuthorities travel, String[] empId,
			String[] empToken, String[] empName, String[] empCheck) {
		// TODO Auto-generated method stub
		String addQurey=" SELECT EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC"
			  + " WHERE EMP_ID = "+travel.getAltAppCode();
		Object[][] addData = getSqlModel().getSingleResult(addQurey);
		ArrayList list = new ArrayList();
		if(empId != null && empId.length > 0)
		{
			for(int i=0;i<empId.length;i++)
			{
				TravelAuthorities bean = new TravelAuthorities();
				bean.setItAprId(empId[i]);
				bean.setAprToken(empToken[i]);
				bean.setItAprName(empName[i]);
				list.add(bean);
				}
		}
		TravelAuthorities bean = new TravelAuthorities();
			bean.setAprToken(""+addData[0][0]);
			bean.setItAprName(""+addData[0][1]);
			bean.setItAprId(""+addData[0][2]);
			list.add(bean);
			travel.setAprlist(list);
		
		
	}

	public boolean delApr(TravelAuthorities travel, String[] code,
			String[] empToken, String[] itEmpName, String[] itAprId) {
		// TODO Auto-generated method stub
		logger.info("------delApr--------");
		try{
			ArrayList<Object> list=new ArrayList<Object>();
			
			
			if(empToken!=null)
			{   
				for(int i=0;i<empToken.length;i++){
					TravelAuthorities bean=new TravelAuthorities();
					if((String.valueOf(code[i]).equals(""))){
						bean.setAprToken(empToken[i]);
						bean.setItAprName(itEmpName[i]);
						bean.setItAprId(itAprId[i]);
						list.add(bean);
						
					}
					
					}
			}
			travel.setAprlist(list);
			}
			
		
			catch (Exception e) {
				e.printStackTrace();
				
				// TODO: handle exception
			}
			return true;
	}

	public void addAccountant(TravelAuthorities travel, String[] empId,
			String[] empToken, String[] empName, String[] empCheck) {
		// TODO Auto-generated method stub
		
		String addQurey=" SELECT EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,EMP_ID FROM HRMS_EMP_OFFC"
			  + " WHERE EMP_ID = "+travel.getAltAccCode();
		Object[][] addData = getSqlModel().getSingleResult(addQurey);
		ArrayList list = new ArrayList();
		if(empId != null && empId.length > 0)
		{
			for(int i=0;i<empId.length;i++)
			{
				TravelAuthorities bean = new TravelAuthorities();
				bean.setItAccId(empId[i]);
				bean.setAccToken(empToken[i]);
				bean.setItAccName(empName[i]);
				list.add(bean);
				}
		}
		TravelAuthorities bean = new TravelAuthorities();
			bean.setAccToken(""+addData[0][0]);
			bean.setItAccName(""+addData[0][1]);
			bean.setItAccId(""+addData[0][2]);
			list.add(bean);
			travel.setAcclist(list);
		
	}

	public boolean delAcc(TravelAuthorities travel, String[] code,
			String[] empToken, String[] itEmpName, String[] itAccId) {
		// TODO Auto-generated method stub
		logger.info("----------Inside delAcc---------");
		try{
			ArrayList<Object> list=new ArrayList<Object>();
			
			logger.info("----------Inside delAcc---------"+empToken.length);
			if(empToken!=null)
			{   
				for(int i=0;i<empToken.length;i++){
					TravelAuthorities bean=new TravelAuthorities();
					if((String.valueOf(code[i]).equals(""))){
						bean.setAccToken(empToken[i]);
						bean.setItAccName(itEmpName[i]);
						bean.setItAccId(itAccId[i]);
						list.add(bean);
						
					}
					
					}
			}
			travel.setAcclist(list);
			}
			
		
			catch (Exception e) {
				e.printStackTrace();
				
				// TODO: handle exception
			}
			return true;
	}

public void getAllList(TravelAuthorities travel, HttpServletRequest request)
{
	// for check box
	
	if(travel.getHidBranchFlag().equals("Y"))
	{
		travel.setAppFlag("checked");
	}else
	{
		travel.setAppFlag("false");
	}
	
	
	// for Scheduler
	String[] empId = request.getParameterValues("itEmpId");
	String[] empName = request.getParameterValues("itEmpName");
	String[] empToken = request.getParameterValues("empToken");
	ArrayList list = new ArrayList();
	if(empId != null && empId.length > 0)
	{
		for(int i=0;i<empId.length;i++)
		{
			TravelAuthorities bean = new TravelAuthorities();
			bean.setItEmpId(empId[i]);
			bean.setEmpToken(empToken[i]);
			bean.setItEmpName(empName[i]);
			list.add(bean);
		}
		travel.setSchlist(list);
		System.out.println("sch list======="+list.size());
	}
	
	// for schedule approver 
	
	String[] aprempId = request.getParameterValues("itAprId");
	String[] aprempName = request.getParameterValues("itAprName");
	String[] aprempToken = request.getParameterValues("aprToken");
  
	ArrayList aprlist = new ArrayList();
	if(aprempId != null && aprempId.length > 0)
	{
		for(int i=0;i<aprempId.length;i++)
		{
			TravelAuthorities bean = new TravelAuthorities();
			bean.setItAprId(aprempId[i]);
			bean.setAprToken(aprempToken[i]);
			bean.setItAprName(aprempName[i]);
			aprlist.add(bean);
			}
		System.out.println("sch aprlist======="+aprlist.size());
		travel.setAprlist(aprlist);
	}
	
	// for   account 
	
	String[] accempId = request.getParameterValues("itAccId");
	String[] accempName = request.getParameterValues("itAccName");
	String[] accempToken = request.getParameterValues("accToken");
	
	ArrayList acclist = new ArrayList();
	if(accempId != null && accempId.length > 0)
	{
		for(int i=0;i<accempId.length;i++)
		{
			TravelAuthorities bean = new TravelAuthorities();
			bean.setItAccId(accempId[i]);
			bean.setAccToken(accempToken[i]);
			bean.setItAccName(accempName[i]);
			acclist.add(bean);
			}
		System.out.println("sch acclist======="+acclist.size());
		travel.setAcclist(acclist);
	}
	
   }// end of method


public void getTravelSch(TravelAuthorities travel) {
	// TODO Auto-generated method stub
	logger.info("--------Inside getTravelSch------------");
	try{
	ArrayList obj=new ArrayList();
	String schQuery="SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,"
		+ " MANG_ALTER_AUTH_SCHEDULER,MANG_ALTER_AUTH_ID"
		+ " FROM HRMS_TMS_MANG_ALTER_AUTH" 
		+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=HRMS_TMS_MANG_ALTER_AUTH.MANG_ALTER_AUTH_BRANCH)" 
		+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_MANG_ALTER_AUTH.MANG_ALTER_AUTH_SCHEDULER)" 
		+ " WHERE MANG_ALTER_AUTH_BRANCH="+travel.getBranchId()
		+ " AND  MANG_AUTH_SCHDULER_EMPID="+travel.getHidSchCode()
		+ " AND MANG_AUTH_APP_ID="+travel.getTravelAuth();
	Object[][] schData = getSqlModel().getSingleResult(schQuery);
	if(schData!=null && schData.length>0){
		 for(int i=0;i<schData.length;i++){
			 TravelAuthorities bean1=new TravelAuthorities();
			logger.info("======empToken)========"+String.valueOf(schData[i][0]));
			logger.info("======Emp Name========"+String.valueOf(schData[i][1]));
			
			logger.info("======empId========"+String.valueOf(schData[i][2]));			
		
			 bean1.setEmpToken(String.valueOf(schData[i][0]));
			 bean1.setItEmpName(String.valueOf(schData[i][1]));
			 bean1.setItEmpId(String.valueOf(schData[i][2]));		 			
				
	obj.add(bean1);
	logger.info("======empToken)========"+bean1.getEmpToken());
	logger.info("======Emp Name========"+bean1.getItEmpName());
	
	logger.info("======empId========"+bean1.getItEmpId());
	}
		 travel.setSchlist(obj);
		 logger.info("----list getSchlist--------"+travel.getSchlist().size());		 
	}
	}catch(Exception e){
	e.printStackTrace();
	}
}


public void getTravelApr(TravelAuthorities travel) {
	// TODO Auto-generated method stub
	try{
		ArrayList<Object> obj=new ArrayList<Object>();
		String aprQuery="SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,MANG_ALTER_AUTH_SCH_APPROVER,MANG_ALTER_AUTH_ID"
			+ " FROM HRMS_TMS_MANG_ALTER_AUTH "
			+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=HRMS_TMS_MANG_ALTER_AUTH.MANG_ALTER_AUTH_BRANCH)" 
			+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_MANG_ALTER_AUTH.MANG_ALTER_AUTH_SCH_APPROVER)" 
			+ " WHERE MANG_ALTER_AUTH_BRANCH="+travel.getBranchId()
			+ " AND  MANG_AUTH_SCHDULE_APPR_EMPID="+travel.getHidApprover()
			+ " AND MANG_AUTH_APP_ID="+travel.getTravelAuth();
		
		Object[][] aprData = getSqlModel().getSingleResult(aprQuery);
		if(aprData!=null && aprData.length>0){
			 for(int i=0;i<aprData.length;i++){
				 TravelAuthorities bean1=new TravelAuthorities();
			
				 bean1.setAprToken(String.valueOf(aprData[i][0]));
				 bean1.setItAprName(String.valueOf(aprData[i][1]));
				 bean1.setItAprId(String.valueOf(aprData[i][2]));
		obj.add(bean1);
		}
			 travel.setAprlist(obj);
		}
		}catch(Exception e){
		e.printStackTrace();
		}
	
}


public void getTravelAcc(TravelAuthorities travel) {
	// TODO Auto-generated method stub
	try{
	ArrayList<Object> obj=new ArrayList<Object>();
	String accQuery="SELECT HRMS_EMP_OFFC.EMP_TOKEN, HRMS_EMP_OFFC.EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME, MANG_ALTER_AUTH_ACCOUNT_PER ,MANG_ALTER_AUTH_ID"
		+ " FROM HRMS_TMS_MANG_ALTER_AUTH " 
		+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=HRMS_TMS_MANG_ALTER_AUTH.MANG_ALTER_AUTH_BRANCH) "
		+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_TMS_MANG_ALTER_AUTH.MANG_ALTER_AUTH_ACCOUNT_PER) "
		+ " WHERE MANG_ALTER_AUTH_BRANCH="+travel.getBranchId()
		+ " AND  MANG_AUTH_ACCOUNT_PER_EMPID="+travel.getHidAccName()
		+ " AND MANG_AUTH_APP_ID="+travel.getTravelAuth();
	Object[][] accData = getSqlModel().getSingleResult(accQuery);
	if(accData!=null && accData.length>0){
		 for(int i=0;i<accData.length;i++){
			 TravelAuthorities bean1=new TravelAuthorities();
		
			 bean1.setAccToken(String.valueOf(accData[i][0]));
			 bean1.setItAccName(String.valueOf(accData[i][1]));
			 bean1.setItAccId(String.valueOf(accData[i][2]));
	obj.add(bean1);
	}
		 travel.setAcclist(obj);
	}
	}catch(Exception e){
	e.printStackTrace();
	}
	
}


public boolean update(TravelAuthorities bean, String[] schEmpId, String[] aprEmpId, String[] accEmpId) {
	// TODO Auto-generated method stub
	
	if(schEmpId != null && schEmpId.length>0){
	
	String delDtlSch="DELETE FROM HRMS_TMS_MANG_ALTER_AUTH WHERE MANG_ALTER_AUTH_BRANCH="+bean.getBranchId()
	+ " AND MANG_AUTH_SCHDULER_EMPID="+bean.getHidSchCode();
	getSqlModel().singleExecute(delDtlSch);
	}
	if(aprEmpId != null && aprEmpId.length>0){
	String delDtlApr="DELETE FROM HRMS_TMS_MANG_ALTER_AUTH WHERE MANG_ALTER_AUTH_BRANCH="+bean.getBranchId()
	+ " AND MANG_AUTH_SCHDULE_APPR_EMPID="+bean.getHidApprover();
	getSqlModel().singleExecute(delDtlApr);
	}
	
	if(accEmpId != null && accEmpId.length>0){
	String delDtlAcc="DELETE FROM HRMS_TMS_MANG_ALTER_AUTH WHERE MANG_ALTER_AUTH_BRANCH="+bean.getBranchId()
	+ " AND MANG_AUTH_ACCOUNT_PER_EMPID="+bean.getHidAccName();
	getSqlModel().singleExecute(delDtlAcc);
	}

	Object[][] add=null;
	Object[][] addObj=null;
	boolean result=false;
	boolean res=false;		
	try{
		Object maxCodeObj[][] = getSqlModel().
				getSingleResult("SELECT NVL(MAX(MANG_AUTH_ID),0) FROM HRMS_TMS_MANG_AUTH");
		int maxCode = Integer.parseInt(""+maxCodeObj[0][0]);
		
		
		Object maxCodeObjDtl[][] = getSqlModel().
		getSingleResult("SELECT NVL(MAX(MANG_ALTER_AUTH_ID),0) FROM HRMS_TMS_MANG_ALTER_AUTH");
		int maxCodeDtl = Integer.parseInt(""+maxCodeObjDtl[0][0]);
		
		
		if(!bean.getBranchId().equals(""))
		{
			
			add = new Object[1][8];
			
			add[0][0]=bean.getBranchId().trim();
			add[0][1]=bean.getHidSchCode();	
			add[0][2]=bean.getHidApprover();
			add[0][3]=bean.getHidAccName();
			add[0][4]=bean.getDescription();
			add[0][5]=bean.getStatus();
			add[0][6]=bean.getHidBranchFlag();
			add[0][7]=bean.getTravelAuth();
			result=getSqlModel().singleExecute(getQuery(2),add);
			if(result){
				logger.info(""+bean.getHidBranchFlag());
				if(bean.getHidBranchFlag().equals("Y")){
					logger.info("======Inside If Update++++++++++++");
				String upQuery="UPDATE HRMS_TMS_MANG_AUTH SET MANG_AUTH_DEFAULT_FLAG='N' WHERE MANG_AUTH_ID != "+bean.getTravelAuth();
				getSqlModel().singleExecute(upQuery);
				}
				int arrLength = 0;
				if(schEmpId != null)
					arrLength+=schEmpId.length;
				if(aprEmpId != null)
					arrLength+=aprEmpId.length;
				if(accEmpId != null)
					arrLength+=accEmpId.length;
				if(arrLength > 0)
				{
				addObj = new Object[arrLength][9];
				//addObj = new Object[schEmpId.length+aprEmpId.length+accEmpId.length][8];
				int z = 0;
				if(schEmpId != null)
				for (int i = 0; i < schEmpId.length; i++) {
					
					System.out.println("In update save========== ");
					
					System.out.println("bean.getBranchId()[i]============ "+bean.getBranchId());
					System.out.println("bean.getHidSchCode()[i]============ "+bean.getHidSchCode());
					System.out.println("bean.getTravelAuth()[i]============ "+bean.getTravelAuth());
					addObj[z][0]=++maxCodeDtl;						
					addObj[z][1] = bean.getBranchId();
					addObj[z][2]=schEmpId[i];	
					System.out.println("schEmpId[i]====xxx======== "+schEmpId[i]);
					addObj[z][3]="";
					addObj[z][4]="";
					addObj[z][5]=bean.getHidSchCode();	
					addObj[z][6]="";
					addObj[z][7]="";
					addObj[z][8]=bean.getTravelAuth();
					
					z++;
				}
				if(aprEmpId != null)
				for (int i = 0; i < aprEmpId.length; i++) {
					addObj[z][0]=++maxCodeDtl;
					addObj[z][1] = bean.getBranchId();
					addObj[z][2]="";	
					addObj[z][3]=aprEmpId[i];
					addObj[z][4]="";
					addObj[z][5]="";	
					addObj[z][6]=bean.getHidApprover();
					addObj[z][7]="";
					addObj[z][8]=bean.getTravelAuth();
					z++;
				}
				if(accEmpId != null)
				for (int i = 0; i < accEmpId.length; i++) {
					addObj[z][0]=++maxCodeDtl;
					addObj[z][1] = bean.getBranchId();
					addObj[z][2]="";	
					addObj[z][3]="";
					addObj[z][4]=accEmpId[i];
					addObj[z][5]="";	
					addObj[z][6]="";
					addObj[z][7]=bean.getHidAccName();
					addObj[z][8]=bean.getTravelAuth();
					z++;
				}
				result=getSqlModel().singleExecute(getQuery(4),addObj);
				}
		
			}else{
				return result;
			}
			
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}		
	if(result){
		String query = " SELECT MAX(MANG_AUTH_ID) FROM HRMS_TMS_MANG_AUTH" ;
		
		Object[][] data = getSqlModel().getSingleResult(query);
		
		System.out.println("data[0][0]---->"+data[0][0]);
		
			String query1 = "SELECT HRMS_CENTER.CENTER_NAME,"
			+ " e1.EMP_FNAME || ' ' || e1.EMP_MNAME || ' ' || e1.EMP_LNAME,"
			+ " e2.EMP_FNAME || ' ' || e2.EMP_MNAME || ' ' || e2.EMP_LNAME,"  
			+ " e3.EMP_FNAME || ' ' || e3.EMP_MNAME || ' ' || e3.EMP_LNAME," 
			+ " NVL(MANG_AUTH_DESC,' '),CASE WHEN MANG_AUTH_STATUS='A' THEN 'Active' ELSE 'Deactive' END,MANG_AUTH_ID,"
			+ " MANG_AUTH_BRANCH,MANG_AUTH_SCHEDULER,MANG_AUTH_SCH_APPROVER,MANG_AUTH_ACCOUNT_PER"   
			+ " FROM HRMS_TMS_MANG_AUTH"
			+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_BRANCH)"
			+ " LEFT JOIN HRMS_EMP_OFFC e1 ON(e1.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_SCHEDULER)"
			+ " LEFT JOIN HRMS_EMP_OFFC e2 ON(e2.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_SCH_APPROVER)"
			+ " LEFT JOIN HRMS_EMP_OFFC e3 ON(e3.EMP_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_ACCOUNT_PER)"
			+ " WHERE MANG_AUTH_ID="+String.valueOf(data[0][0]);
			
		
		/*String query1 = " SELECT ROOM_TYPE_NAME	,CASE WHEN ROOM_TYPE_STATUS	='A' THEN 'Active' ELSE 'Deactive' END,NVL(ROOM_TYPE_DESC,' '),ROOM_TYPE_ID FROM "
					   +" HRMS_TMS_ROOM_TYPE WHERE ROOM_TYPE_ID="+String.valueOf(data[0][0]);*/
		
		logger.info("query1 in addDesignation---->"+query1);
		
		Object[][] Data = getSqlModel().getSingleResult(query1);
		if(Data!=null && Data.length>0){		
		bean.setBranchName(checkNull(String.valueOf(Data[0][0])).trim());				
		bean.setSchName(checkNull(String.valueOf(Data[0][1])).trim());			
		bean.setApproverName(checkNull(String.valueOf(Data[0][2])).trim());			
		bean.setAccName(checkNull(String.valueOf(Data[0][3])).trim());
		bean.setDescription(checkNull(String.valueOf(Data[0][4])).trim());
		bean.setStatus(checkNull(String.valueOf(Data[0][5])).trim());
		}
			
}
	
	return result;
	
}

public void getMsg(TravelAuthorities travel) {
	//boolean result=false;
	// TODO Auto-generated method stub
	String query="SELECT HRMS_CENTER.CENTER_NAME,MANG_AUTH_BRANCH FROM HRMS_TMS_MANG_AUTH"
		+ " LEFT JOIN HRMS_CENTER  ON(HRMS_CENTER.CENTER_ID=HRMS_TMS_MANG_AUTH.MANG_AUTH_BRANCH)"
		+ " WHERE MANG_AUTH_DEFAULT_FLAG='Y'";
	Object [][] queryData=getSqlModel().getSingleResult(query);
	if(queryData!=null && queryData.length>0){
		travel.setMsg(String.valueOf(queryData[0][0]));	
		travel.setMsgFlag(true);
	}
	
}


	

}
