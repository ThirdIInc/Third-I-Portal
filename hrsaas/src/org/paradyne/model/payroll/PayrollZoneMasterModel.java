
package org.paradyne.model.payroll;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.bean.payroll.PayrollZoneMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.XMLReaderWriter;

/**
 * @author saipavan voleti
 *
 */
public class PayrollZoneMasterModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PayrollZoneMasterModel.class);

	public String branchupdate(PayrollZoneMaster payzone, String path) {
		System.out.println("nilesh ***************************************************");
		Object [][]update=new Object[1][4];
		update[0][0]=payzone.getBranchEsiZone();
		update[0][1]=payzone.getBranchPtZone();
		update[0][2]=payzone.getBranchPfZone();
		update[0][3]=payzone.getBranchID();
		update[0][4]=payzone.getBranchmetro();
		
		 boolean result = getSqlModel().singleExecute(getQuery(2),update);
		 if(result){
			 xml_salaryZoneBranch(path);
		 return "modified";
		  
	  } else {
		  return "not modified";
		  
	  }
		
		
	}

	public String Emptypedetails(PayrollZoneMaster payzone, String path) {
		
		Object [][]update=new Object[1][4];
		update[0][0]=payzone.getETesiZone();
		update[0][1]=payzone.getETptZone();
		update[0][2]=payzone.getETpfZone();
		update[0][3]=payzone.getEmptypeID();
		
		boolean result = getSqlModel().singleExecute(getQuery(1),update);
		if(result){
			xml_salaryZoneEmpType(path);
			return "modified";
		}
			else{
				return "not modified";
			}
    		
	}

	public void callforempedit(PayrollZoneMaster payzone) {
		String query=" SELECT  TYPE_ID,TYPE_NAME,TYPE_ESI,TYPE_PT,TYPE_PF FROM HRMS_EMP_TYPE where TYPE_ID="+payzone.getEmployeetypehiddencode();
		Object data[][]=getSqlModel().getSingleResult(query);
		
		if (data.length>0) {
			logger.info("within edit....................."+data[0][0]);
			logger.info("within edit....................."+data[0][1]);
			logger.info("within edit....................."+data[0][2]);
			logger.info("within edit....................."+data[0][3]);
			logger.info("within edit....................."+data[0][4]);
			payzone.setEmptypeID(String.valueOf(data[0][0]));
			payzone.setTypeName(String.valueOf(data[0][1]));
			payzone.setETesiZone(String.valueOf(data[0][2]));
			payzone.setETptZone(String.valueOf(data[0][3]));
			payzone.setETpfZone(String.valueOf(data[0][4]));
			logger.info("endeeeeeeeeeeeeeeeeeeeeeee.....................");
		}
		
	}

	public void callforbranchedit(PayrollZoneMaster payzone) {
		String query="SELECT CENTER_ID, CENTER_NAME, CENTER_ESI, CENTER_PT, CENTER_PF FROM  HRMS_CENTER where CENTER_ID="+payzone.getBranchhiddencode();
		Object data[][]=getSqlModel().getSingleResult(query);
		if(data.length>0)
		{
			logger.info("within edit.....................");
			logger.info("within edit....................."+data[0][0]);
			logger.info("within edit....................."+data[0][1]);
			logger.info("within edit....................."+data[0][2]);
			logger.info("within edit....................."+data[0][3]);
			logger.info("within edit....................."+data[0][4]);
		payzone.setBranchID(String.valueOf(data[0][0]));
		payzone.setBranchName(String.valueOf(data[0][1]));
		payzone.setBranchEsiZone(String.valueOf(data[0][2]));
		payzone.setBranchPtZone(String.valueOf(data[0][3]));
		payzone.setBranchPfZone(String.valueOf(data[0][4]));
		logger.info("endeeeeeeeeeeeeeeeeeeeeeee.....................");
		}                                           
		
		
	}
	public void EmpTypeData(PayrollZoneMaster payzone, HttpServletRequest request) {
	
		logger.info("EmpTypeData..................................!");
		
		Object [][] repData = getSqlModel().getSingleResult(getQuery(3));
		logger.info("After query..................EmpTypeData..................................!");
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
	 int pg1=0;
	int PageNo1=1;//----------
	REC_TOTAL = repData.length;
	int no_of_pages=Math.round(REC_TOTAL/20);
	//PageNo = Integer.parseInt(bean.getMyPage());//-----------
	double row = (double)repData.length/20.0;
   
      java.math.BigDecimal value1 = new java.math.BigDecimal(row);
     
      int rowCount1 =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
    
	
	ArrayList<Object> obj=new ArrayList<Object>();
	request.setAttribute("abc", rowCount1);

	//PageNo
	if(String.valueOf(payzone.getMyPage()).equals("null")||String.valueOf(payzone.getMyPage()).equals(null)||String.valueOf(payzone.getMyPage()).equals(" "))
	{
		PageNo1=1;
		From_TOT=0;
		  To_TOT=20;

		  if(To_TOT >repData.length){
			  To_TOT=repData.length;
		  }
			System.out.println("-----------To_TOTAL----null-----"+To_TOT);
			payzone.setMyPage("1");
			/*if(!payzone.getBranchmyPage().equals(""))
				payzone.setBranchmyPage(payzone.getBranchmyPage());*/
	}
	
	
	else{
			
		  pg1=	Integer.parseInt(payzone.getMyPage());
		  PageNo1=pg1;
		
		/*  if(!payzone.getBranchmyPage().equals(""))
				payzone.setBranchmyPage(payzone.getBranchmyPage());*/
		
		  
		  if(pg1 ==1){
			 From_TOT=0;
			 To_TOT=20;
		  }
		  else{
			//  From_TOTAL=To_TOTAL+1;
				 To_TOT=To_TOT*pg1;
				 From_TOT=(To_TOT-20);
		  }
		  if(To_TOT >repData.length){
			  To_TOT=repData.length;
		  }
	  }
	request.setAttribute("xyz", PageNo1);
	  System.out.println("------------from total--------"+From_TOT);
	  System.out.println("----------to total----------"+To_TOT);
	  for(int i=From_TOT;i<To_TOT;i++){
                
		  PayrollZoneMaster    bean1 = new PayrollZoneMaster();
		  
		    bean1.setEmptypeID(String.valueOf(repData[i][0]));
			bean1.setTypeName(String.valueOf(repData[i][1]));
			bean1.setETesiZone(String.valueOf(repData[i][2]));
			bean1.setETptZone(String.valueOf(repData[i][3]));
			//bean1.setETpfZone(String.valueOf(repData[i][4]));
			bean1.setETpfMinAmt(String.valueOf(repData[i][4]));
			
            obj.add(bean1);
	  }
	
	  payzone.setEmptypeList(obj);
	  
	}
public void BranchData(PayrollZoneMaster payzone, HttpServletRequest request) {
		
System.out.println("BranchData Query..!!"+getQuery(4));
		Object [][] repData = getSqlModel().getSingleResult(getQuery(4));
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
	 int pg1=0;
	int PageNo1=1;//----------
	REC_TOTAL = repData.length;
	int no_of_pages=Math.round(REC_TOTAL/20);
	//PageNo = Integer.parseInt(bean.getMyPage());//-----------
	double row = (double)repData.length/20.0;
   
      java.math.BigDecimal value1 = new java.math.BigDecimal(row);
     
      int rowCount1 =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
    
	
	ArrayList<Object> obj=new ArrayList<Object>();
	request.setAttribute("abc1", rowCount1);

	//PageNo
	if(String.valueOf(payzone.getBranchmyPage()).equals("null")||String.valueOf(payzone.getBranchmyPage()).equals(null)||String.valueOf(payzone.getBranchmyPage()).equals(" "))
	{
		PageNo1=1;
		From_TOT=0;
		  To_TOT=20;

		  if(To_TOT >repData.length){
			  To_TOT=repData.length;
		  }
			System.out.println("-----------To_TOTAL----null-----"+To_TOT);
			payzone.setBranchmyPage("1");
		/*	if(!payzone.getMyPage().equals(""))
			payzone.setMyPage(payzone.getMyPage());*/
	}
	
	
	else{
			
		  pg1=Integer.parseInt(payzone.getBranchmyPage());
			/*if(!payzone.getMyPage().equals(""))
		    payzone.setMyPage(payzone.getMyPage());*/
		  logger.info("your at else block....!!"+pg1);
		  PageNo1=pg1;
		  
		  if(pg1==1){
			 From_TOT=0;
			 To_TOT=20;
		  }
		  else{
			//  From_TOTAL=To_TOTAL+1;
				 To_TOT=To_TOT*pg1;
				 From_TOT=(To_TOT-20);
		  }
		  if(To_TOT >repData.length){
			  To_TOT=repData.length;
		  }
	  }
	request.setAttribute("xyz1", PageNo1);
	  System.out.println("------------from total--------"+From_TOT);
	  System.out.println("----------to total----------"+To_TOT);
	  for(int i=From_TOT;i<To_TOT;i++){
                
		  PayrollZoneMaster    bean1 = new PayrollZoneMaster();
		  bean1.setBranchID(String.valueOf(repData[i][0]));
			bean1.setBranchName(String.valueOf(repData[i][1]));
			bean1.setBranchEsiZone(String.valueOf(repData[i][2]));
			bean1.setBranchPtZone(String.valueOf(repData[i][3]));
			//bean1.setBranchPfZone(String.valueOf(repData[i][4]));
			bean1.setPtaxcitycode(String.valueOf(repData[i][4]));
			bean1.setPtaxcityname(checkNull(String.valueOf(repData[i][5])));
			/**Added by Nilesh for ESI Code start*/
			bean1.setEsiCode(checkNull(String.valueOf(repData[i][6])));			
			/**Added by Nilesh for ESI Code end*/
			bean1.setBranchmetro(checkNull(String.valueOf(repData[i][7])));
			request.setAttribute("Ptaxcityname"+(i-From_TOT), checkNull(String.valueOf(repData[i][5])));
			request.setAttribute("Ptaxcitycode"+(i-From_TOT), checkNull(String.valueOf(repData[i][4])));
			logger.info("RepData...!!! "+String.valueOf(repData[i][6])+"    dsfsdafsda"+String.valueOf(repData[i][4]));
			
            obj.add(bean1);
	  }
	
	
		
	  payzone.setBranchList(obj);
		
	}

public String checkNull(String result) { // check for null

	if (result == null || result.equals("null")) {
		return "";
	} else {
		return result;
	}
}

public String branchupdating(PayrollZoneMaster payzone, Object[] branchid,
		Object[] branchEsi, Object[] branchPt, String path,Object[] Ptcity,Object[] branchMetro,Object[] esiCode) {
	//ESI Code related filed Added by Nilesh 24th June 11
	
	boolean flag=false;
	if(String.valueOf(branchid).length()>0)
	{
		Object result[][]=new Object[branchid.length][6]; 
		
		for(int i=0;i<branchid.length;i++)
		{
			logger.info("Metro...!"+branchMetro[i]);
			logger.info("ESI CODE...!"+esiCode[i]);
			if(String.valueOf(branchid[i])!=""||String.valueOf(branchid[i])!=null)
			{
				logger.info("values...!"+branchEsi[i]+"pt:"+branchPt[i]+"id:"+branchid[i]);
				logger.info("city code...!"+String.valueOf(Ptcity[i]));
				result[i][0]=branchEsi[i];
				result[i][1]=branchPt[i];
				//result[i][2]=branchPf[i];
				result[i][2]=checkNull(String.valueOf(Ptcity[i]));
				result[i][3]=branchMetro[i];
				result[i][4]=esiCode[i];
				result[i][5]=branchid[i];
				try {
					flag = getSqlModel().singleExecute(getQuery(2), result);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
			
		}
	
		
		
	}
	if(flag){
		 xml_salaryZoneBranch(path);
	 return "modified";
	  
} else {
	  return "not modified";
	  
}
	
}




public String EmployeeTypeupdating(PayrollZoneMaster payzone, Object[] empid,
		Object[] empEsi, Object[] empPt, String path,Object [] empPfMinAmt) {
	// TODO Auto-generated method stub
	boolean result=false;
	if(String.valueOf(empid).length()>0)
	{
		Object result1[][]=new Object[empid.length][4]; 
		for(int i=0;i<empid.length;i++)
		{
			if(String.valueOf(empid[i])!=""||String.valueOf(empid[i])!=null)
			{
				result1[i][0]=empEsi[i];
				result1[i][1]=empPt[i];
				//result1[i][2]=empPf[i];
				result1[i][2]=empPfMinAmt[i];
				result1[i][3]=empid[i];
				result =  getSqlModel().singleExecute(getQuery(1),result1);
			}
			
		}
		
	}
	
		
		
	if(result){
		xml_salaryZoneEmpType(path);
	 return "modified";
	  
 } else {
	  return "not modified";
	  
 }
	
	
}


	/** WRITING IN XML **/
	/** @author reebaj **/
	public void xml_salaryZoneBranch(String path) {
		
		if (!(path == null || path.equals("") || path.equals(null)))
			path = "/" + path;
		
		try {
			new XMLReaderWriter().write(buildSal_branch("PAYROLL", "SALARY ZONE_BRANCH"),
					path);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void xml_salaryZoneEmpType(String path) {
		
		if (!(path == null || path.equals("") || path.equals(null)))
			path = "/" + path;
		
		try {
			new XMLReaderWriter().write(buildSal_emptype("PAYROLL", "SALARY ZONE_EMPTYPE"),
					path);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public Document buildSal_branch(String head, String subhead) {
		String query1 = " SELECT CENTER_ID, CENTER_ESI, CENTER_PT, CENTER_PF,CENTER_PTAX_STATE,CENTER_ISMETRO "
						+ " FROM HRMS_CENTER ";
		Object data[][] = getSqlModel().getSingleResult(query1);
		Document document = DocumentHelper.createDocument();
		logger.info("data.length"+data.length);
		Element header;
		Element element;
		Element root = document.addElement(head);
		if(data != null && data.length > 0){
			//header = root.addElement("BRANCH").addAttribute("name", subhead);
			for (int i = 0; i < data.length; i++) {
				header = root.addElement("BRANCH").addAttribute("code",
					String.valueOf(data[i][0])).addAttribute("esi",
					String.valueOf(data[i][1])).addAttribute("pt",
					String.valueOf(data[i][2])).addAttribute("pf",
					String.valueOf(data[i][3])).addAttribute("Ptaxcitycode",
							String.valueOf(data[i][4])).addAttribute("metro",
									String.valueOf(data[i][5]));
			}
		}else {
			logger.info("NO SALARY ZONE CONFIG(BRANCH)");
				//header = root.addElement("BRANCH").addAttribute("name", subhead);
				header = root.addElement("BRANCH").addAttribute("code",
					String.valueOf("code")).addAttribute("esi",
					String.valueOf("esi")).addAttribute("pt",
					String.valueOf("pt")).addAttribute("pf",
					String.valueOf("pf")).addAttribute("Ptaxcitycode",
							String.valueOf("Ptaxcitycode")).addAttribute("metro",
									String.valueOf("metro"));
				
		}
		return document;
	}
	
	public Document buildSal_emptype(String head, String subhead) {
		String query1 = " SELECT TYPE_ID, TYPE_ESI, TYPE_PT, TYPE_PF,TYPE_PF_MIN_AMT "
						+ " FROM HRMS_EMP_TYPE ";
		Object data[][] = getSqlModel().getSingleResult(query1);
		Document document = DocumentHelper.createDocument();
		logger.info("data.length"+data.length);
		Element header;
		Element element;
		Element root = document.addElement(head);
		if(data != null && data.length > 0){
			//header = root.addElement("EMPTYPE").addAttribute("name", subhead);
			for (int i = 0; i < data.length; i++) {
				header = root.addElement("EMPTYPE").addAttribute("code",
					String.valueOf(data[i][0])).addAttribute("esi",
					String.valueOf(data[i][1])).addAttribute("pt",
					String.valueOf(data[i][2])).addAttribute("pf",
					String.valueOf(data[i][3])).addAttribute("pfminamt",
					String.valueOf(data[i][4]));
			}
		}else {
			logger.info("NO SALARY ZONE CONFIG(EMPTYPE)");
				//header = root.addElement("EMPTYPE").addAttribute("name", subhead);
				header = root.addElement("EMPTYPE").addAttribute("code",
					String.valueOf("code")).addAttribute("esi",
					String.valueOf("esi")).addAttribute("pt",
					String.valueOf("pt")).addAttribute("pf",
					String.valueOf("pf")).addAttribute("pfminamt",
					String.valueOf("pfminamt"));
				
		}
		return document;
	}
	
	public void getEmpExceptionData(PayrollZoneMaster bean,HttpServletRequest request){
	
		String empQuery="SELECT EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME,CENTER_NAME,HRMS_SALARY_MISC.EMP_ID FROM HRMS_SALARY_MISC "
			+" LEFT JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
			+" LEFT JOIN HRMS_CENTER ON(CENTER_ID=EMP_CENTER) "
			+" WHERE SAL_PTAX_FLAG = 'N' ORDER BY UPPER(NAME)";

		Object [][] empData = getSqlModel().getSingleResult(empQuery);
		
		int REC_TOTAL = 0;
		int To_TOT = 20;
		int From_TOT = 0;
		int pg1 = 0;
		int PageNo1 = 1;// ----------
		REC_TOTAL = empData.length;
		int no_of_pages = Math.round(REC_TOTAL / 20);
		// PageNo = Integer.parseInt(bean.getMyPage());//-----------
		double row = (double) empData.length / 20.0;

		java.math.BigDecimal value1 = new java.math.BigDecimal(row);
     
      int rowCount1 =Integer.parseInt(value1.setScale(0,java.math.BigDecimal.ROUND_UP).toString());
    
	
	ArrayList<Object> obj=new ArrayList<Object>();
	request.setAttribute("rowCountEmp", rowCount1);

	//PageNo
	if(String.valueOf(bean.getMyPageEmp()).equals("null")||String.valueOf(bean.getMyPageEmp()).equals(null)||String.valueOf(bean.getMyPageEmp()).equals(""))
	{
		PageNo1=1;
		From_TOT=0;
		  To_TOT=20;

		  if(To_TOT >empData.length){
			  To_TOT=empData.length;
		  }
			System.out.println("-----------To_TOTAL----null-----"+To_TOT);
			bean.setMyPage("1");
			/*if(!payzone.getBranchmyPage().equals(""))
				payzone.setBranchmyPage(payzone.getBranchmyPage());*/
	}
	
	
	else{
			
		  pg1=	Integer.parseInt(bean.getMyPageEmp());
		  PageNo1=pg1;
		
		/*  if(!payzone.getBranchmyPage().equals(""))
				payzone.setBranchmyPage(payzone.getBranchmyPage());*/
		
		  
		  if(pg1 ==1){
			 From_TOT=0;
			 To_TOT=20;
		  }
		  else{
			//  From_TOTAL=To_TOTAL+1;
				 To_TOT=To_TOT*pg1;
				 From_TOT=(To_TOT-20);
		  }
		  if(To_TOT >empData.length){
			  To_TOT=empData.length;
		  }
	  }
	request.setAttribute("pageNoEmp", PageNo1);
	  System.out.println("------------from total--------"+From_TOT);
	  System.out.println("----------to total----------"+To_TOT);
		
		
		ArrayList tableList= new ArrayList();
		
		
		
		if(empData !=null || empData.length >0){
			 for(int i = From_TOT ; i<To_TOT ;i++){
				PayrollZoneMaster beanList = new PayrollZoneMaster();
				beanList.setEmpTokenList(checkNull(String.valueOf(empData[i][0])));
				beanList.setEmpNameList(checkNull(String.valueOf(empData[i][1])));
				beanList.setBranchNameList(checkNull(String.valueOf(empData[i][2])));
				beanList.setEmpCodeList(checkNull(String.valueOf(empData[i][3])));
				tableList.add(beanList);
			}
			bean.setNoExcepData("false");
		}else{
			bean.setNoExcepData("true");
		}
		if(tableList.size()<=0)
			bean.setNoExcepData("true");
		logger.info("NoExcepData=="+bean.getNoExcepData());
		bean.setEmpExcpList(tableList);
		
	}
	
	public boolean checkSalMiscData(PayrollZoneMaster bean){
		String query="SELECT EMP_ID FROM HRMS_SALARY_MISC WHERE EMP_ID="+bean.getEmpCode();
		Object empObj [][]=getSqlModel().getSingleResult(query);
		boolean isDataAvailable =false;
		if(empObj !=null || empObj.length>0){
			isDataAvailable = true;
		}else
			isDataAvailable= false;
		return isDataAvailable;
	}
	public boolean addEmployeeToException(PayrollZoneMaster bean,boolean isDataAvailable){
		if(isDataAvailable){
			String updateQuery = "UPDATE HRMS_SALARY_MISC SET SAL_PTAX_FLAG ='N' WHERE EMP_ID="+bean.getEmpCode();
			return getSqlModel().singleExecute(updateQuery);
		}else{
			String insertQuery = "INSERT INTO HRMS_SALARY_MISC(EMP_ID,SAL_EPF_FLAG,SAL_PTAX_FLAG) VALUES("+bean.getEmpCode()+",'Y','N')";
			return getSqlModel().singleExecute(insertQuery);
		}
	}
	
	public boolean removeEmployee(String empId){
		
		String updateQuery = "UPDATE HRMS_SALARY_MISC SET SAL_PTAX_FLAG ='Y' WHERE EMP_ID IN ("+empId+")";
		return getSqlModel().singleExecute(updateQuery);
	}
   
}
