
/**
 * @author vijay.gaikwad
 * class ManageClientDashBoardModel
 */

package org.paradyne.model.CR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.CR.AccountMaster;
import org.paradyne.bean.CR.ManageClientDashBoard;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;





public class ManageClientDashBoardModel extends ModelBase {
	
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(ManageClientDashBoardModel.class);
	
	/**
	 * This Function is used for Checking null and abnormal termination 
	 * @param String result
	 * @return
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} //end of if
		else {
			return result;
		}//end of else
	}
	
	
	
	/**
	 * This function used for getting DashBorad list
	 * 
	 * @param bean
	 * @param request
	 */
	public void getDashBoardList(ManageClientDashBoard bean,HttpServletRequest request){
		try{
			Object [][] dashBoardList = getSqlModel().getSingleResult(getQuery(1));
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					dashBoardList.length, 20);
			ArrayList<ManageClientDashBoard> list = new ArrayList<ManageClientDashBoard>();
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");
			bean.setIteratorList(null);
			
			
			if (dashBoardList != null && dashBoardList.length > 0) {
				bean.setListLength(true);
				
				int count = 0;
		
	
		if(dashBoardList!=null){
			for(int i=0; i<dashBoardList.length;i++){
				ManageClientDashBoard beanItt = new ManageClientDashBoard();
				beanItt.setSrno(String.valueOf(i+1));
				beanItt.setDashBoardID(checkNull(String.valueOf(dashBoardList[i][0])));
				beanItt.setDashBoardName(checkNull(String.valueOf(dashBoardList[i][1])));
				beanItt.setIsAccountApplicatble(checkNull(String.valueOf(dashBoardList[i][2])));
				if(String.valueOf(dashBoardList[i][3]).equals("1"))
				{beanItt.setIsActive("Yes");}
				else
				{
					beanItt.setIsActive("No");	
				}
				list.add(beanItt);
			}
			bean.setIteratorList(list);
			}
		}
		}
		catch (Exception e) {
		// TODO: handle exception
		}
	}
	 
	/**
	 * This function is used for getting Account List
	 * 
	 * @param bean
	 * @param request
	 */
	public void getDashBoardAccountList(ManageClientDashBoard bean,HttpServletRequest request){
		try{
			Object [][] dashBoardList = getSqlModel().getSingleResult(getQuery(1));
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					dashBoardList.length, 20);
			ArrayList<ManageClientDashBoard> list = new ArrayList<ManageClientDashBoard>();
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");
			bean.setIteratorList(null);
			
			
			if (dashBoardList != null && dashBoardList.length > 0) {
				bean.setListLength(true);
				
			for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++){
				ManageClientDashBoard beanItt = new ManageClientDashBoard();
				beanItt.setSrno(String.valueOf(i+1));
				beanItt.setDashBoardID(checkNull(String.valueOf(dashBoardList[i][0])));
				beanItt.setDashBoardName(checkNull(String.valueOf(dashBoardList[i][1])));
				beanItt.setIsAccountApplicatble(checkNull(String.valueOf(dashBoardList[i][2])));
				beanItt.setIsActive(checkNull(String.valueOf(dashBoardList[i][3])));
				list.add(beanItt);
			}
			bean.setIteratorList(list);
			
		}
		}
		catch (Exception e) {
		// TODO: handle exception
		}
	}
	
	/**
	 * This function is used for inserting internal as well as Managed Account Internal Empoyee information
	 * 
	 * @param bean
	 * @param employeeID
	 * @param accountID
	 */
	public void saveEmployeeInfo(ManageClientDashBoard bean,String employeeID, String accountID){
		//getting max auto id
		try{
		boolean result=false;
		ArrayList<ManageClientDashBoard> list = new ArrayList<ManageClientDashBoard>();
		Object[][] maxID = getSqlModel().getSingleResult(getQuery(2));
		Object[][] joinedEmployeeList=null;
		String maxAutoID="";
		
		if(maxID!=null){
			maxAutoID=String.valueOf(maxID[0][0]);}
		// attrute to pass insert query
		
		Object addEmp[][]= new Object[1][5];
		addEmp[0][0]=maxAutoID;
		addEmp[0][1]=employeeID;
		addEmp[0][2]="I";
		addEmp[0][3]=bean.getDashBoardID();
		if(accountID!=null&&!accountID.equals("")){
		addEmp[0][4]=accountID;	
		}
		else{
			addEmp[0][4]="";
		}
		// inserting value of client info for DashBoard
		if(accountID==null ||accountID.equals("")){
			accountID="0";
		}
		
		
		result= getSqlModel().singleExecute(getQuery(3),addEmp);
		if(result){
		 bean.setActionMessage("Employee Added Successfully");
		 joinedEmployeeList=getSqlModel().getSingleResult(getQuery(4), new Object[]{bean.getDashBoardID(),accountID});
		 if(joinedEmployeeList!=null){
			 for(int i=0;i<joinedEmployeeList.length;i++){
				 ManageClientDashBoard beanItt = new ManageClientDashBoard();
				 beanItt.setInformToken(checkNull(String.valueOf(joinedEmployeeList[i][0])));
				 beanItt.setInformName(checkNull(String.valueOf(joinedEmployeeList[i][1])));
				 beanItt.setInformCode(checkNull(String.valueOf(joinedEmployeeList[i][2])));
				 list.add(beanItt);
			 }
			 bean.setKeepInformedList(list);
		 }
		}
		else{
			System.out.println("not saved");
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * This function is used for inserting External as well as Managed Account External Empoyee information
	 * 
	 * @param bean
	 * @param accountNo
	 * @param accountID
	 */
	public void saveAccountInfo(ManageClientDashBoard bean,String accountNo,String accountID){
		try{
			boolean result=false;
			ArrayList<ManageClientDashBoard> list = new ArrayList<ManageClientDashBoard>();
			Object[][] maxID = getSqlModel().getSingleResult(getQuery(2));//getting max autoid from sqlserver 
			Object[][] joinedEmployeeList=null;
			String maxAutoID="";
			
			if(maxID!=null){
				maxAutoID=String.valueOf(maxID[0][0]);}
			//attribute for insert Query
			Object addAcc[][]= new Object[1][5];
			addAcc[0][0]=maxAutoID;
			addAcc[0][1]=accountID;
			addAcc[0][2]="E";
			addAcc[0][3]=bean.getDashBoardID();
			if(accountID!=null&&!accountID.equals("")){
				addAcc[0][4]=accountNo;	
				}
				else{
					addAcc[0][4]="";
				}
			if(accountNo==null ||accountNo.equals("")){
				accountNo="0";
			}
			//inserting the records
			result= getSqlModel().singleExecute(getQuery(3),addAcc);
			if(result){
				 bean.setActionMessage("Client Added successfully");
				
				
				 joinedEmployeeList=getSqlModel().getSingleResult(getQuery(6), new Object[]{bean.getDashBoardID(),accountNo});
				 if(joinedEmployeeList!=null){
					 for(int i=0;i<joinedEmployeeList.length;i++){
						 ManageClientDashBoard beanItt = new ManageClientDashBoard();
						 beanItt.setInformToken(checkNull(String.valueOf(joinedEmployeeList[i][0])));
						 beanItt.setInformName(checkNull(String.valueOf(joinedEmployeeList[i][1])));
						 list.add(beanItt);
					 }
					 bean.setKeepInformedList(list);
				 }
			}
			else{
				 bean.setActionMessage("Not Added");
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This function is used for Adding account for corresponding dashBoard
	 * @param bean
	 * @param accountID
	 * @param request
	 */
	public void saveManageAccountInfo(ManageClientDashBoard bean,String accountID,HttpServletRequest request) {
		try{
			boolean result=false;
			ArrayList<ManageClientDashBoard> list = new ArrayList<ManageClientDashBoard>();
			Object[][] maxID = getSqlModel().getSingleResult(getQuery(8));//getting max autoid from sqlserver 
			Object[][] joinedEmployeeList=null;
			String maxAutoID="";
			System.out.println("dashBoard Name - "+bean.getDashBoardName());
			if(maxID!=null){
				maxAutoID=String.valueOf(maxID[0][0]);}
			//attribute for insert Query
			Object addAcc[][]= new Object[1][3];
			addAcc[0][0]=maxAutoID;
			addAcc[0][1]=bean.getDashBoardID();
			addAcc[0][2]=accountID;
			//inserting the records
			result= getSqlModel().singleExecute(getQuery(9),addAcc);
			if(result){
				 bean.setActionMessage("Added Successfully");
				
				list=getManageAccountList(bean.getDashBoardID(), bean, request, bean.getSearchMessagestr());
				/* joinedEmployeeList=getSqlModel().getSingleResult(getQuery(10), new Object[]{bean.getDashBoardID()});
				 if(joinedEmployeeList!=null){
					 for(int i=0;i<joinedEmployeeList.length;i++){
						 ManageClientDashBoard beanItt = new ManageClientDashBoard();
						 beanItt.setInformToken(checkNull(String.valueOf(joinedEmployeeList[i][0])));
						 beanItt.setInformName(checkNull(String.valueOf(joinedEmployeeList[i][1])));
						 beanItt.setInformCode(checkNull(String.valueOf(joinedEmployeeList[i][2])));
						 list.add(beanItt);
					 }*/
					 bean.setKeepInformedList(list);
				// }
			}
			else{
				 bean.setActionMessage("Not Added");
			}
			
		
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		
		// TODO Auto-generated method stub
		
	}
	
	
	/** This function is used to get the basic filter list of parameters, reports and graphs.
	 * @param bean
	 * @param dashBoardID
	 * @param informCode
	 * @param userType
	 */
	public void userFilter(ManageClientDashBoard bean,String dashBoardID, String informCode, String userType,HttpServletRequest request){
		
		try {
			Object[] filterObj = new Object[3];
			filterObj[0]=dashBoardID;
			filterObj[1]=informCode;
			filterObj[2]=userType;
			Object[][] savedFilter = getSqlModel().getSingleResult(getQuery(22), filterObj);
			Object[] parameterObj = new Object[3];
			parameterObj[0]= dashBoardID;
			parameterObj[1]= dashBoardID;
			parameterObj[2]= dashBoardID;
			Object[][] data = getSqlModel().getSingleResult(getQuery(13), parameterObj);
			
			if(data != null && data.length>0){
				
				ArrayList userFilterlist  = new ArrayList();
				
				
				for (int i = 0; i < data.length; i++) {
					ManageClientDashBoard bean1 = new ManageClientDashBoard();
					bean1.setParameterName(checkNull((String.valueOf(data[i][0]))));
					if(savedFilter!=null && savedFilter.length>0){
						for (int j = 0; j < savedFilter.length; j++) {
					if(String.valueOf(data[i][0]).equals(String.valueOf(savedFilter[j][0])))
					{
					bean1.setDefaultValue(checkNull(String.valueOf(savedFilter[j][1])));
							
							}
						}
					}
					userFilterlist.add(bean1);
				}
				bean.setFilterList(userFilterlist);
				bean.setShowParameterFlag("true");
			}
			
			//For bringing back saved filter reports
			Object[] filterReport = new Object[3]; 
			filterReport[0]= dashBoardID;
			filterReport[1]= informCode;
			filterReport[2]= userType;
			
			Object[][] savedReport = getSqlModel().getSingleResult(getQuery(23),filterReport);
			 
			 // For displaying the reports
			Object[] reportObj = new Object[1];
			reportObj[0]= dashBoardID;
			Object[][] report = getSqlModel().getSingleResult(getQuery(14),reportObj);
			 
			 if(report != null && report.length>0){
					ArrayList reportlists  = new ArrayList();
					for (int i = 0; i < report.length; i++) {
						ManageClientDashBoard bean2 = new ManageClientDashBoard();
						bean2.setReportName(checkNull((String.valueOf(report[i][0]))));
						bean2.setReportId(checkNull((String.valueOf(report[i][1].toString()))));
						if(savedReport != null && savedReport.length>0){
							for (int j = 0; j < savedReport.length; j++) {
								if(String.valueOf(report[i][1].toString()).equals(String.valueOf(savedReport[j][0].toString()))){
									bean2.setReportHidden("Y")	;
								}
							}
						}
						reportlists.add(bean2);
					}
					bean.setReportList(reportlists);
					bean.setShowReportFlag("true");
				}
			 
			 //for bringing back saved graphs
			 Object[] filterGraph = new Object[3]; 
			 filterGraph[0]= dashBoardID;
			 filterGraph[1]= informCode;
			 filterGraph[2]= userType;
				
				 Object[][] savedGraph = getSqlModel().getSingleResult(getQuery(24),filterGraph);
				
			 //for displaying the graphs
			 Object[] graphObj = new Object[1];
			 graphObj[0]= dashBoardID;
			 Object[][] graph = getSqlModel().getSingleResult(getQuery(15),graphObj);
			 if(graph != null && graph.length>0){
					
					ArrayList graphlists  = new ArrayList();
					
					for (int i = 0; i < graph.length; i++) {
						ManageClientDashBoard bean3 = new ManageClientDashBoard();
						bean3.setGraphName(checkNull((String.valueOf(graph[i][0]))));
						bean3.setComponentId(checkNull((String.valueOf(graph[i][1].toString()))));
						bean3.setAutoIdForGraph(checkNull((String.valueOf(graph[i][2].toString()))));
						if(savedGraph!=null && savedGraph.length>0)
						{
							for (int j = 0; j < savedGraph.length; j++) {
								if(String.valueOf(graph[i][2].toString()).equals(String.valueOf(savedGraph[j][0].toString())))
								{
									bean3.setGraphHidden("Y");
								}
							}
						}
						
						graphlists.add(bean3);
					}
					bean.setGraphList(graphlists);
					bean.setShowGraphFlag("true");
				}
			 //for bring back saved user documents
			 Object[] saveDocFileParameter=new Object[3];
			 saveDocFileParameter[0]=informCode;
			 saveDocFileParameter[1]=userType;
			 saveDocFileParameter[2]=dashBoardID;
			 Object[][] savedDocFileObj=getSqlModel().getSingleResult(getQuery(29),saveDocFileParameter);
			 
			 // update for User Documents--vijay
			 Object[] docParameters=new Object[1];
			 docParameters[0]=dashBoardID;
			 Object[][] userDocuments=getSqlModel().getSingleResult(getQuery(28),docParameters);
			 if(userDocuments!=null && userDocuments.length>0){
				 ArrayList documentList=new ArrayList();
				 for(int i=0;i<userDocuments.length;i++){
					 ManageClientDashBoard mbean=new ManageClientDashBoard();
					 mbean.setDocumentFileName(checkNull(String.valueOf(userDocuments[i][0])));
					 mbean.setAutoidDoc(checkNull(String.valueOf(userDocuments[i][1])));
					 documentList.add(mbean);
					 if(savedDocFileObj!=null && savedDocFileObj.length>0){
						
						 for(int j=0;j<savedDocFileObj.length;j++){
						 if(String.valueOf(savedDocFileObj[j][0]).equals(String.valueOf(userDocuments[i][1]))){
							 mbean.setDocHidden("Y");
						 }
						 
					 }}
					 
				 }
				 bean.setDocumentList(documentList);
			 }
			 
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * This function is used for removed  configuration of users
	 * @param bean
	 * @param dashBoardID
	 * @param informCode
	 * @param userType
	 * @param request
	 */
	public void deleteFilter(ManageClientDashBoard bean,String dashBoardID, String informCode, String userType,HttpServletRequest request) {
		boolean result =false;
		
		Object[][] delObj = new Object[1][3];
		delObj[0][0]=dashBoardID;
		delObj[0][1]=informCode;
		delObj[0][2]= userType;
		
		result = getSqlModel().singleExecute(getQuery(25),delObj);// For deleting saved parameters
		
		result = getSqlModel().singleExecute(getQuery(26),delObj);// For deleting saved reports
		
		result= getSqlModel().singleExecute(getQuery(27),delObj);// For deleting saved graphs
		
		/*Object[][] delDocObj=new Object[1][3];
		delDocObj[0][0]=informCode;
		delDocObj[0][1]=userType;
		delDocObj[][]*/
		result=getSqlModel().singleExecute(getQuery(30),delObj);
		 if(result){
			 System.out.println("User Document Deleted");
		 }
		 else{
			 System.out.println("User Document not Deleted"); 
		 }
		
		
	}
	
	
	
	/** This function is used for saving filter values
	 * @param bean
	 * @param dashBoardID
	 * @param informCode
	 * @param userType
	 * @param request
	 */
	
	public boolean saveFilter(ManageClientDashBoard bean,String dashBoardID, String informCode, String userType,HttpServletRequest request) {
		boolean result =false;
		try {
			try {
				String[] parameterName = request
						.getParameterValues("parameterName");
				String[] defaultValue = request
						.getParameterValues("defaultValue");
				Object[][] data = new Object[parameterName.length][6];
				Object[][] autoId = getSqlModel().getSingleResult(
						getQuery(16));
				if (autoId != null && autoId.length > 0) {
					int j = 1;

					for (int i = 0; i < data.length; i++) {
						data[i][0] = Integer.parseInt(autoId[0][0].toString())
								+ j;
						data[i][1] = dashBoardID;
						data[i][2] = informCode;
						data[i][3] = userType;
						data[i][4] = parameterName[i];
						data[i][5] = defaultValue[i];
						j++;

					}

					/*for (int i = 0; i < data.length; i++) {
						for (int k = 0; k < data[0].length; k++) {
							System.out.println("data[" + i + "]" + "[" + k
									+ "]" + String.valueOf(data[i][k]));
						}

					}*/
					result = getSqlModel().singleExecute(getQuery(17),
							data);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			result =false;
			try {
				//For REPORTS
				String[] reportName = request.getParameterValues("reportName");
				String[] reportId = request.getParameterValues("reportId");
				String[] reportSelected = request
						.getParameterValues("reportHidden");
				Vector vect = new Vector();
				for (int i = 0; i < reportSelected.length; i++) {
					if (reportSelected[i].equals("Y")) {
						vect.add(reportId[i]);

					}
				}
				Object[][] autoIdForReport = getSqlModel()
						.getSingleResult(getQuery(18));
				Object[][] report = new Object[vect.size()][5];
				int k = 1;
				for (int i = 0; i < vect.size(); i++) {

					report[i][0] = Integer.parseInt(autoIdForReport[0][0]
							.toString())
							+ k;
					report[i][1] = dashBoardID;
					report[i][2] = informCode;
					report[i][3] = userType;
					report[i][4] = vect.get(i);
					k++;

				}
				
				/*for (int i = 0; i < report.length; i++) {
					for (int j = 0; j < report[i].length; j++) {
						logger.info("report[" + i + "][" + j + "]  "
								+ report[i][j]);
					}
				}*/
				
				result = getSqlModel().singleExecute(getQuery(19),
						report);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			result =false;
			
			try {
				//for graphs
				String[] graphName = request.getParameterValues("graphName");
				String[] autoIdForGraph = request
						.getParameterValues("autoIdForGraph");
				String[] graphSelected = request
						.getParameterValues("graphHidden");
				Vector vector = new Vector();
				for (int i = 0; i < graphSelected.length; i++) {
					if (graphSelected[i].equals("Y")) {
						vector.add(autoIdForGraph[i]);

					}
				}
				Object[][] autoIdGraph = getSqlModel()
						.getSingleResult(getQuery(20));
				Object[][] graph = new Object[vector.size()][5];
				int h = 1;
				for (int i = 0; i < vector.size(); i++) {

					graph[i][0] = Integer
							.parseInt(autoIdGraph[0][0].toString())
							+ h;
					graph[i][1] = dashBoardID;
					graph[i][2] = informCode;
					graph[i][3] = userType;
					graph[i][4] = vector.get(i);
					h++;

				}
				
				/*for (int i = 0; i < graph.length; i++) {
					for (int j = 0; j < graph[i].length; j++) {
						logger.info("graph[" + i + "][" + j + "]  "
								+ graph[i][j]);
					}
				}*/
				
				result = getSqlModel().singleExecute(getQuery(21),
						graph);
			} catch (Exception e) {
				// TODO: handle exception
			}
			//user Documents
			result=false;
			try{
				String docFilename[]=request.getParameterValues("documentFileName");
				String autoId[]=request.getParameterValues("autoidDoc");
				String docSelected[]=request.getParameterValues("docHidden");
				if(docFilename!=null&&docFilename.length>0){
				Vector vector = new Vector();
				for (int i = 0; i < docSelected.length; i++) {
					if (docSelected[i].equals("Y")) {
						vector.add(autoId[i]);

					}
				}
				Object[][] autoIdDoc = getSqlModel().getSingleResult(getQuery(31));
				Object[][] docPara = new Object[vector.size()][4];
				
				for (int i = 0; i < vector.size(); i++) {
					docPara[i][0] = Integer.parseInt(autoIdDoc[0][0].toString())+(i+1);
					docPara[i][1] = informCode;//dashBoardID;
					docPara[i][2] =userType;// informCode;
					docPara[i][3] = vector.get(i);
				}
				
				
				for (int i = 0; i < docPara.length; i++) {
					for (int j = 0; j < docPara[i].length; j++) {
						logger.info("docPara[" + i + "][" + j + "]  "
								+ docPara[i][j]);
					}
				}
				
				result= getSqlModel().singleExecute(getQuery(32),
						docPara);
				if(result){
					System.out.println("Document success");
				}
				else
				{
					System.out.println("doc not succcess");
				}
			}
				else{
					result=true;
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	/**
	 * This function is used for getting Internal Employee List for Corresponding DashBoard
	 * @param bean
	 * @param accountID
	 */
	public void getEmployeeList(ManageClientDashBoard bean,String accountID){
		
		if(accountID==null||accountID.equals("")){
			accountID="0";	
		}
	String accountID1="";
	if(bean.getAccountID()==null||bean.getAccountID().equals("")){
		accountID1="0";
	}
	else{
		accountID1=bean.getAccountID();
	}
		
		try{ArrayList<ManageClientDashBoard> list = new ArrayList<ManageClientDashBoard>();
		Object[][] joinedEmployeeList=null;
		joinedEmployeeList=getSqlModel().getSingleResult(getQuery(4), new Object[]{bean.getDashBoardID(),accountID1});
		 if(joinedEmployeeList!=null){
			 for(int i=0;i<joinedEmployeeList.length;i++){
				 ManageClientDashBoard beanItt = new ManageClientDashBoard();
				 beanItt.setInformToken(checkNull(String.valueOf(joinedEmployeeList[i][0])));
				 beanItt.setInformName(checkNull(String.valueOf(joinedEmployeeList[i][1])));
				 beanItt.setInformCode(checkNull(String.valueOf(joinedEmployeeList[i][2])));
				 list.add(beanItt);
			 }
			 bean.setKeepInformedList(list);
		 }
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * This function is used for getting External Employee List for Corresponding DashBoard
	 * @param bean
	 * @param accountID
	 */
	public void getAccountList(ManageClientDashBoard bean,String accountID,HttpServletRequest request){
		try{ArrayList<ManageClientDashBoard> list = new ArrayList<ManageClientDashBoard>();
		Object[][] joinedEmployeeList=null;
		if(bean.getAccountID()==null||bean.getAccountID().equals("")){
			accountID="0";	
		}
		else{
			accountID=bean.getAccountID();
		}
		joinedEmployeeList=getSqlModel().getSingleResult(getQuery(6), new Object[]{bean.getDashBoardID(),accountID});
		
		
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),
				joinedEmployeeList.length, 20);
		ArrayList<ManageClientDashBoard> accountlist = new ArrayList<ManageClientDashBoard>();
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");
		bean.setIteratorList(null);
		
		
		if (joinedEmployeeList != null && joinedEmployeeList.length > 0) {
			bean.setListLength(true);
			
		for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++){
	
				 ManageClientDashBoard beanItt = new ManageClientDashBoard();
				 beanItt.setInformToken(checkNull(String.valueOf(joinedEmployeeList[i][0])));
				 beanItt.setInformName(checkNull(String.valueOf(joinedEmployeeList[i][1])));
				 list.add(beanItt);
			 }
			 bean.setKeepInformedList(list);
	
		
		
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This function used for getting Manage Account For Corresponding DashBoard
	 * @param DashBoardId
	 * @param bean
	 * @param request
	 * @param searchMessagestr
	 * @return ArrayList
	 */
	public ArrayList getManageAccountList(String DashBoardId, ManageClientDashBoard bean,HttpServletRequest request,String searchMessagestr){
		ArrayList<ManageClientDashBoard> list = new ArrayList<ManageClientDashBoard>();
		try{
		Object[][] joinedEmployeeList=null;
		
		if(searchMessagestr==null||searchMessagestr.equals("")){
			searchMessagestr="'%'";
		}
		else{
			searchMessagestr="'%"+searchMessagestr+"%'";
		}
		String query=" select ccm.account_id, ccm.account_name , ccm.account_code,'Y'||ccm.PARENT_FLAG " +
		" from CR_CLIENT_MASTER ccm, CR_DASHBOARD_ACCOUNT cda " +
		" where ccm.account_code=cda.account_code and cda.dashboard_id="+DashBoardId+" " +
		" and ccm.is_active='Y'" +
		" and UPPER(NVL((rtrim(ltrim(ccm.account_id)))||(rtrim(ltrim(ccm.account_name))),0)) like UPPER (ltrim(rtrim("+searchMessagestr+"))) " +
		" ORDER BY case when ccm.PARENT_FLAG='Y' then ccm.account_code*10000 else parent_code*10000+ccm.account_code end ";
		joinedEmployeeList=getSqlModel().getSingleResult(query);
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),
				joinedEmployeeList.length, 20);
		ArrayList<ManageClientDashBoard> manageList = new ArrayList<ManageClientDashBoard>();
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}//end if
		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");
		bean.setIteratorList(null);
		 if(joinedEmployeeList!=null && joinedEmployeeList.length > 0){
			 bean.setListLength(true);
			 
			 for(int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++){
				 ManageClientDashBoard beanItt = new ManageClientDashBoard();
				 beanItt.setSrno(String.valueOf(i+1));
				 beanItt.setInformToken(checkNull(String.valueOf(joinedEmployeeList[i][0])));
				 beanItt.setInformName(checkNull(String.valueOf(joinedEmployeeList[i][1])));
				 beanItt.setInformCode(checkNull(String.valueOf(joinedEmployeeList[i][2])));
				 beanItt.setIsParent(checkNull(String.valueOf(joinedEmployeeList[i][3])));
				 list.add(beanItt);
				
			 }
			// bean.setKeepInformedList(list);
		 	}
		 }
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	/**
	 * This function is used for Removing Employee form Internal Employee list
	 * 
	 * @param employeeID
	 * @param bean
	 */
	public void removeEmployee(String employeeID,ManageClientDashBoard bean){
		boolean result=false;
		System.out.println("employeeID- - "+employeeID);
		try{
			String accountID="";
				if(bean.getAccountID()!=null && !String.valueOf(bean.getAccountID()).equals("")){
					accountID=bean.getAccountID();
				}
				else{
					accountID="0";
				}
			
			Object deletePara[][]=new Object[1][3];
			deletePara[0][0]=employeeID;
			deletePara[0][1]=accountID;
			deletePara[0][2]=bean.getDashBoardID();
			
			result=getSqlModel().singleExecute(getQuery(5),deletePara);
			if(result){
				bean.setActionMessage("Employee Deleted Successfully");
			}
			else{
				bean.setActionMessage("Employee not remove ");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * This function is used for Removing Employee form External Employee list
	 * @param accountID
	 * @param bean
	 */
	public void removeAccount(String accountID,ManageClientDashBoard bean){
		boolean result=false;
		System.out.println("employeeID- - "+accountID);
		try{
			String accountCode="";
				if(bean.getAccountID()!=null && !String.valueOf(bean.getAccountID()).equals("")){
					accountCode=bean.getAccountID();
				}
				else{
					accountCode="0";
				}
			
			
			Object deletePara[][]=new Object[1][3];//passing parameters to query
			deletePara[0][0]=accountID;
			deletePara[0][1]=accountCode;
			deletePara[0][2]=bean.getDashBoardID();
			result=getSqlModel().singleExecute(getQuery(7),deletePara);
			if(result){
				bean.setActionMessage("Client removed successfully");
			}
			else{
				bean.setActionMessage("Client not removed ");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This function is used for removing Accounts form manage Accounts list for Corresponding Dash board	 * 
	 * @param accountID
	 * @param bean
	 */
	public void removeManageAccount(String accountID,ManageClientDashBoard bean){
		boolean result=false;
		
		System.out.println("Account- - "+accountID);
		try{
			
			String query= "SELECT count(*) FROM CR_EMP_CLIENT_MAPP WHERE DASHBOARD_ID= " +	bean.getDashBoardID()+
					"  AND ACCOUNT_CODE= "+accountID;
					 
			Object[][] count=getSqlModel().getSingleResult(query);
			if(Integer.parseInt(String.valueOf(count[0][0]))==0){
				
			
			
			Object deletePara[][]=new Object[1][1];
			deletePara[0][0]=accountID;
			result=getSqlModel().singleExecute(getQuery(11),deletePara);
			if(result){
				bean.setActionMessage("Account removed successfully");
			}
			}
			else{
				bean.setActionMessage("Account can not be Removed. Please Remove Employee/Client Mapping First ");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * This function is used for Updating DashBoard Information
	 * @param dashBoardName
	 * @param isActive
	 * @param bean
	 */
	public void updateDashBoardDetails(String dashBoardName,String isActive,ManageClientDashBoard bean){
		boolean result=false;
	
		if(isActive.equals("true")){
		isActive="1";
	}
	else{
		isActive="0";
	}
		
		String query="update cr_dashboard  set dashboard_Caption='"+dashBoardName+"', is_active="+isActive+"where dashboard_id="+bean.getDashBoardID();
		result=getSqlModel().singleExecute(query);
		if(result){
			bean.setActionMessage("Successfully Updated");
		}
		else{
			bean.setActionMessage("not Updated");
		}
		
	}
	public void getDashBoardReportList(ManageClientDashBoard bean){
		try{
			ArrayList<ManageClientDashBoard> list = new ArrayList<ManageClientDashBoard>();
			String queryParameter[]=new String[2];
			queryParameter[0]=bean.getDashBoardID();
			queryParameter[1]=bean.getUserEmpId();
			Object[][] reportObj=getSqlModel().getSingleResult(getQuery(33),queryParameter);
			if(reportObj!=null && reportObj.length>0){
			for(int i=0; reportObj.length>i;i++){
				ManageClientDashBoard mbean=new ManageClientDashBoard();
				mbean.setDashBoardreportName(String.valueOf(reportObj[i][0]));
				list.add(mbean);
			}
			bean.setDashBoardReportList(list);
		}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}



	public void applyFilters(ManageClientDashBoard bean,
			HttpServletRequest request, String searchMessage,
			String searchCRMMessage) {
		// TODO Auto-generated method stub
		try {
			Object[][] listParentData = null;
			ArrayList<ManageClientDashBoard> list = new ArrayList<ManageClientDashBoard>();
			System.out.println("searchCRMMessage===="+searchCRMMessage);
			String selParentQuery="";
			
				System.out.println("searchCRMMessage==if=="+searchCRMMessage);
				 selParentQuery="SELECT  A.ACCOUNT_CODE,A.ACCOUNT_NAME||' '||DECODE(A.PARENT_FLAG,'Y','(P)','N',' '),A.ACCOUNT_ID, "
					+" DECODE(A.IS_ACTIVE,'Y','Yes','N','No'),TO_CHAR(A.RECORD_CREATEDON,'DD-MM-YYYY HH:MM AM'),TO_CHAR(A.RECORD_MODIFIEDON,'DD-MM-YYYY HH:MM AM') ,'Y'||A.PARENT_FLAG,B.ACCOUNT_CODE "
					+" FROM CR_ClIENT_MASTER A,CR_CLIENT_MASTER B ,CR_ACC_CRM_MAPP C "
					+" WHERE NVL(A.PARENT_CODE,A.ACCOUNT_CODE)=B.ACCOUNT_CODE ";
					
				if (searchCRMMessage != null && !searchCRMMessage.trim().equals("")) {
					selParentQuery += " AND C.ACCOUNT_CODE=B.ACCOUNT_CODE AND C.CRM_CODE IN (('"+ searchCRMMessage.trim() + "'))   ";
				}
				selParentQuery+= " ORDER BY B.ACCOUNT_CODE*1000+(DECODE(A.PARENT_FLAG,'Y',0,A.ACCOUNT_CODE))  ";
			
			
			listParentData = getSqlModel().getSingleResult(selParentQuery);
			
			String[] pageIndex = Utility.doPaging(bean.getMyPage(),
					listParentData.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");
			bean.setIteratorList(null);
			
			
			if (listParentData != null && listParentData.length > 0) {
				bean.setListLength(true);
				
				int count = 0;
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					ManageClientDashBoard beanItt=new ManageClientDashBoard();
					
					beanItt.setInformCode(checkNull(String
							.valueOf(listParentData[i][0])));
					
					beanItt.setInformName(checkNull(String
							.valueOf(listParentData[i][2])));
					
					/*beanItt.setRecordCreatedBy(checkNull(String
							.valueOf(listParentData[i][4])));*/
					beanItt.setInformToken(checkNull(String
							.valueOf(listParentData[i][4])));
					
					beanItt.setIsParent(checkNull(String
							.valueOf(listParentData[i][6])));
					
					list.add(beanItt);
				}
				bean.setIteratorList(list);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
