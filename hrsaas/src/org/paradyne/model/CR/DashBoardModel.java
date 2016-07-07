package org.paradyne.model.CR;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.paradyne.bean.CR.DashBoard;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class DashBoardModel extends ModelBase {
	static Logger logger = Logger.getLogger(DashBoardModel.class);
	DashBoard dashBoard;

	
 /*
  * for getting report list on dashBoard
  */
	public Object[][] getTableMenuList(DashBoard dashBean) {
		ArrayList MenuList = new ArrayList();
		Object[][] object = (Object[][]) null;
		try {
			//Getting Configure DashBoard Filter
			Object[] configParameters=new Object[3];
			configParameters[0]=String.valueOf(dashBean.getDashBoardID());
			configParameters[1]=String.valueOf(dashBean.getUserID());
			if(String.valueOf(dashBean.getUserType()).equals("CRM")){
			configParameters[2]="I";
			}
			else if(String.valueOf(dashBean.getUserType()).equals("HRM")){
				configParameters[2]="P";	
			}
			else{
				configParameters[2]="E";	
			}
			object = getSqlModel().getSingleResult(getQuery(2),configParameters);
			int objectlen=object.length;
			for (int i = 0; i < objectlen; i++) {
				DashBoard beanItt = new DashBoard();
				beanItt.setReportName(String.valueOf(object[i][0]).trim());
				MenuList.add(beanItt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		dashBean.setMenuList(MenuList);
		return object;
	}
	
	
	/**
	 * This funtion used for getUsers Document List
	 * @param bean
	 * @return
	 */
	public void getDocumentFileList(DashBoard bean){
		
		try{
			Object[] docPara=new Object[3];//passing parameter to query
			docPara[0]=String.valueOf(bean.getUserID());
			if(String.valueOf(bean.getUserType()).equals("CRM")){
				docPara[1]="I";
				}
			else if(String.valueOf(bean.getUserType()).equals("HRM")){
				docPara[2]="P";	
			}	
			else{
					docPara[1]="E";	
				}
			
			docPara[2]=String.valueOf(bean.getDashBoardID());
			Object[][] documentsObj=getSqlModel().getSingleResult(getQuery(11),docPara);
			if(documentsObj!=null&& documentsObj.length>0){
				ArrayList docList=new ArrayList();
				int documentObjlen=documentsObj.length;
				for(int i=0; i<documentObjlen;i++){//set document list
					DashBoard bean1=new DashBoard();
					bean1.setDocumentFile(String.valueOf(documentsObj[i][0]));
					bean1.setDocumentFileloc(String.valueOf(documentsObj[i][1]));
					docList.add(bean1);
				}
				bean.setDocumentList(docList);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Returning 2d object of Component Table
	 */
	public Object[][] getTableCellData(String query) {
		Object[][] Queryobj=null;
		try{
		query = query.replace("'<pdate>'"," to_char(sysdate-7,'DD-MON-RRRR')");
		
		Queryobj= getSqlModel().getSingleResultWithCol(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Queryobj;
	}

	
	/*
	 * Returning 2d Object Of Component Table for CRM Account
	 */
	public Object[][] getTableCellData_account(String query, String accountCode,
			String accountId) {
		query = query.replace("<pdate>",
				" to_char(sysdate-7,'DD-MON-RRRR') ");
		query = query.replaceAll("<ACCOUNT_CODE>", accountCode);
		query = query.replaceAll("<ACCOUNT_ID>", accountId);

		Object[][] Queryobj = getSqlModel().getSingleResultWithCol(
				query);

		return Queryobj;
	}
	
	/*
	 * returning vector of dashBoard Tables and setRequest Parameter of charts
	 */ 
	public Object[][] gettabfrmQuery(HttpServletRequest request, DashBoard bean) {
		/*
		 * For Replacing Configure Parameters by Manage Client DashBoard
		 */
		Object[] configpara=new Object[3];//passing parameter to query
		try{
			configpara[0]=String.valueOf(bean.getDashBoardID());  // dashboard id
			configpara[1]=String.valueOf(bean.getUserID());  // user id 
			
			if(String.valueOf(bean.getUserType()).equals("CRM")){
				configpara[2]="I";  // Internal peoplepower user
			}
			else if(String.valueOf(bean.getUserType()).equals("HRM")){
				configpara[2]="P";	
			}	
			else{
				configpara[2]="E";  // external peoplepower user -- client table
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		/*
		 * to get all component information
		 */  
		Object[][] obj = getSqlModel().getSingleResult(getQuery(5),configpara);// result containt all Dashboard related information 

		
		//HashMap map = new HashMap();
		LinkedHashMap comMap=new LinkedHashMap();
		int objlen=obj.length;
		Vector extComps = new Vector();
		Vector xValues=new Vector();
		Vector yValues=new Vector();
		for (int i = 0; objlen > i; i++) {
			/*
			 *  to replace default date 
			 */
			String query = String.valueOf(obj[i][2]).replace("'<pdate>'"," to_char(sysdate-7,'DD-MON-RRRR') ");
			if(bean.getDashMonth().equals("") && bean.getDashYear().equals("")){
				Calendar now = Calendar.getInstance();
				if(now.get(Calendar.MONTH)<9){
					//System.out.println("Month - "+Calendar.MONTH);
				bean.setDashMonth("0"+Calendar.MONTH);}
				
				else{
					bean.setDashMonth(Calendar.MONTH+"");	
				}
				System.out.println("Date of Month is - -"+bean.getDashMonth());
				bean.setDashYear(String.valueOf(now.get(Calendar.YEAR)));
			}
			
			
			query=query.replaceAll("<PMONTH>", bean.getDashMonth());
			query=query.replaceAll("<PYEAR>", bean.getDashYear());
			/*
			 * For Replacing Configure Parameters by Manage Client DashBoard
			 */
			String replacedParameterString=ReplacingQueryParameters(query,bean);// called function for replaced configured parameters
			
			obj[i][2]=replacedParameterString;
			
			Object[][] replacedFiltetedObject=(Object[][])null;  // to display data

			/*
			 *  to get the data from PP database, as the component is Internal
			 */	
			if(String.valueOf(obj[i][12]).equals("I")) {  // to check whether the component data is internal 
				
				if(!String.valueOf(obj[i][15]).equals("null") &&  !String.valueOf(obj[i][16]).equals("null")){
					/*
					 *  to configure x and y axis columns
					 */
					replacedFiltetedObject=filterColums(replacedParameterString,obj, String.valueOf(obj[i][15]), String.valueOf(obj[i][16])) ;}
				else{
					/*
					 * To show entire query data
					 */
					replacedFiltetedObject=getTableCellData(replacedParameterString);
				}
				
				// Puting Data and Graph Parameter in Map
				if(String.valueOf(obj[i][1]).equals("D")) {
					comMap.put(String.valueOf(obj[i][0])+"-"+String.valueOf(obj[i][1])+"-"+String.valueOf(obj[i][4]), replacedFiltetedObject);
				} else {				
					String multiComp=getStringMultiComp(String.valueOf(obj[i][0]));
					comMap.put(String.valueOf(obj[i][0])+"-"+String.valueOf(obj[i][1])+"-"+String.valueOf(obj[i][4]), getChartInfo("",replacedFiltetedObject, String.valueOf(obj[i][4]), multiComp,"", String.valueOf(obj[i][0])));
				}	
				
			} else {
				/**
				 *  to get all the external components
				 */
				extComps.add(i);
				xValues.add(String.valueOf(obj[i][15]));
				yValues.add(String.valueOf(obj[i][16]));
				obj[i][14]=getStringMultiComp(String.valueOf(obj[i][0]));
			}
		
		}
		/*
		 *  to get the data through external webservice
		 */
		if(extComps.size() > 0) {
			//14 dirldown component
			//0 component
			
			comMap = getDataFromWebservice(comMap, bean, extComps, obj,"false",xValues,yValues,0);
		}		
		request.setAttribute("comMap",comMap);
		return obj;
	}
	
	public LinkedHashMap getDataFromWebservice(LinkedHashMap comMap, DashBoard bean, Vector extComps, 
			Object[][] obj, String isComponentData,Vector xValue, Vector yValue,int cont) {
		//LinkedHashMap compMap = comMap;
		Vector componentSequence=new Vector();
		/**
			 * Code to get data from Webservice
			 */
			/*
			 * grouping
			 */
				Object[][] groupObj = obj;
				String groupStrQuery="";
				if(isComponentData.equals("true")){
				    LinkedHashMap componentMap=(LinkedHashMap) comMap.get(String.valueOf(cont));
					String graphType=(String)componentMap.get("graphType");
					String nextCompID=(String)componentMap.get("nextComponentId");
					String graphParam=(String)componentMap.get("graphParam");
					String currentCompId=(String)componentMap.get("currentComponentId"); 
					String compQuery=(String)componentMap.get("compQuery"); 
				    groupObj[0][2]=String.valueOf(compQuery);
				    }
				 LinkedHashMap<String, Vector> groupMap= new LinkedHashMap<String, Vector>();
				 LinkedHashMap<String, Vector> urlcompoMap=new LinkedHashMap<String, Vector>();
				 List grupList=new ArrayList<String>();
				 for(int i=0; groupObj.length>i;i++){
					String key="";
					if(isComponentData.equals("true")){
						key=String.valueOf(groupObj[i][12]);}
					else{
						key=String.valueOf(groupObj[i][13]);
					}
					
					if(groupMap.containsKey(key))
					{
						Vector innerVector=groupMap.get(key);
						innerVector.add(groupObj[i]);
						groupMap.put(key, innerVector);
						Vector seqid=new Vector();
						seqid=urlcompoMap.get(key);
						seqid.add(i);
						urlcompoMap.put(key, seqid);
					}
					else
					{
						Vector innerVector=new Vector();
						innerVector.add(groupObj[i]);
						groupMap.put(key,innerVector);
						Vector seqid=new Vector();
						seqid.add(i);
						urlcompoMap.put(key, seqid);
					}
				 }
					
				Set keySet=null;
				Iterator ittKeyList=null;
				keySet=groupMap.keySet();
				ittKeyList=keySet.iterator();
				List<List<String>> compoList=new ArrayList<List<String>>();
				
				while (ittKeyList.hasNext())
				{
					String key=(String)ittKeyList.next();
					System.out.println("GROUP NAME......." +key);
				    Vector vect=(Vector)groupMap.get(key);
				    Vector compSeqVect=urlcompoMap.get(key);
				    int seq=0;
				    String xmlRequestStr="";
				    if(isComponentData.equals("true")){
				    	xmlRequestStr=getXmlFromComponentQuery(vect,bean, obj,true);// passing xml string
				    }
				    else{
				    	xmlRequestStr=getXmlFromComponentQuery(vect,bean, obj,false);
				    }
					String xmlData = "";
					String response = null;
					try {
						 BufferedReader reader = null;
						   OutputStreamWriter writer = null;
						   HttpURLConnection connection = null;
						   
						String URLString = key;//"http://192.168.25.240/UALDIRECT/DirectLinkXMLData.aspx";//key; // get from database
						System.out.println("xmlRequestStr  "+xmlRequestStr);
						xmlRequestStr = java.net.URLEncoder.encode(xmlRequestStr, "UTF-8");
						  URL url = new URL(URLString);
						    connection = (HttpURLConnection) url.openConnection();
						    connection.setConnectTimeout(180000);
						    connection.setReadTimeout(180000);
						    connection.setDoOutput(true);
						 String charset = "UTF-8";
						connection.setRequestProperty("Accept-Charset", charset);
						connection.setRequestProperty("Content-Type","text/xml");
						OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
						out.write(xmlRequestStr);
						out.close();	
						StringBuilder sb = new StringBuilder();
					    String line = null;
					  
					    try {
					     reader = new BufferedReader(new InputStreamReader(
					       connection.getInputStream()));
					    } catch (IOException e) {
					     InputStream errorIp = connection.getErrorStream();
					     if (errorIp != null) {
					      BufferedReader errorReader = new BufferedReader(
					        new InputStreamReader(errorIp));
					      while ((line = errorReader.readLine()) != null) {
					       sb.append(line);
					      }
					      System.out.println("Got Error Response: " + sb.toString());
					     }     
					     throw e;
					    }

					    while ((line = reader.readLine()) != null) {
					     sb.append(line);
					    }
					    response = sb.toString();
					    System.out.println("responseggg--" + response);
						BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						String decodedString;
						in.close();
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}			
					String xmlDataString = response;//.substring(3, xmlData.length());
					String compId="";
					List graphDataObj=null;// to display graph
					try {
						graphDataObj = getObjectFromXml(xmlDataString);
					} catch (Exception e) {
						// TODO: handle exception
					}
					int graphDataObjSize=graphDataObj.size();
					for(int p = 0; p< graphDataObjSize; p++) { 
						Object[][]compObj= (Object[][])graphDataObj.get(p);	
						
						int i = Integer.parseInt(String.valueOf(compSeqVect.get(p)));
						if(isComponentData.equals("false")){
						if(String.valueOf(obj[i][1]).equals("D")) {
							comMap.put(String.valueOf(obj[i][0])+"-"+String.valueOf(obj[i][1])+"-"+String.valueOf(obj[i][4]), compObj);
						} else {	
							if((!String.valueOf(xValue.get(p)).equals("null"))&& (!String.valueOf(yValue.get(p)).equals("null"))){
								compObj=filterColums("",compObj,String.valueOf(xValue.get(p)),String.valueOf(yValue.get(p)));
							}
							String multiComp=getStringMultiComp(String.valueOf(obj[i][0]));
							comMap.put(String.valueOf(obj[i][0])+"-"+String.valueOf(obj[i][1])+"-"+String.valueOf(obj[i][4]), getChartInfo("",compObj, String.valueOf(obj[i][4]), multiComp,"", String.valueOf(obj[i][0])));
						}
						}
						else{
							 if(isComponentData.equals("true")){
								 LinkedHashMap componentMap=(LinkedHashMap)comMap.get(String.valueOf(cont));
									String graphType=(String)componentMap.get("graphType");
									String nextCompID=(String)componentMap.get("nextComponentId");
									String graphParam=(String)componentMap.get("graphParam");
									String currentCompId=(String)componentMap.get("currentComponentId"); 
									componentMap.put("tableData", compObj);
									if((!String.valueOf(xValue.get(p)).equals("null"))&& (!String.valueOf(yValue.get(p)).equals("null"))){
										compObj=filterColums("",compObj,String.valueOf(xValue.get(p)),String.valueOf(yValue.get(p)));
									}
									componentMap.put("chartData", getChartInfo("",compObj, graphType, nextCompID,graphParam, currentCompId));
							}
						}
					}
				}
			
			/*
			 * end grouping
			 */
		return comMap;
	}
	

	/**
	 * this function is used for CRM Accounts dashboard
	 * @param request
	 * @param bean
	 * @return object
	 */
	public Object[][] gettabfrmQuery_account(HttpServletRequest request,
			DashBoard bean) {
		/*
		 * For Replacing Configure Parameters by Manage Client DashBoard(CRM)
		 */
		Object[] configpara=new Object[3];//passing parameter to query
		try{
			configpara[0]=String.valueOf(bean.getDashBoardID()); // dashboard id
			configpara[1]=String.valueOf(bean.getUserID());// user id 
			if(String.valueOf(bean.getUserType()).equals("CRM")){
				configpara[2]="I"; // Internal peoplepower user
			}
			else{
				configpara[2]="E";// external peoplepower user -- client table
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		/*
		 * to get all component information
		 */
		Object[][] obj = getSqlModel().getSingleResult(getQuery(5),configpara);
		LinkedHashMap map = new LinkedHashMap();
		String replacedQuery="";
		LinkedHashMap comMap=new LinkedHashMap();
		Vector extComps = new Vector();
		Vector xValues=new Vector();
		Vector yValues=new Vector();
		
		//set each object to map according to Graph and data
		int objlen=obj.length;
		for (int i = 0; objlen > i; i++) {
			/*
			 *  to replace default date 
			 */
			String query = String.valueOf(obj[i][2]).replace("'<pdate>'","to_char(sysdate-7,'DD-MON-RRRR')");
			if(bean.getDashMonth().equals("") && bean.getDashYear().equals("")){
				Calendar now = Calendar.getInstance();
				if(now.get(Calendar.MONTH)<9){
					//System.out.println("Month - "+Calendar.MONTH);
				bean.setDashMonth("0"+Calendar.MONTH);}
				
				else{
					bean.setDashMonth(Calendar.MONTH+"");	
				}
				System.out.println("Date of Month is - -"+bean.getDashMonth());
				bean.setDashYear(String.valueOf(now.get(Calendar.YEAR)));
			}
			
			query=query.replaceAll("<PMONTH>", bean.getDashMonth());
			query=query.replaceAll("<PYEAR>", bean.getDashYear());
			/*
			 * For Replacing Configure Parameters by Manage Client DashBoard
			 */
			
			//replacing some manadatory space holder to CRM Component query
			query = query.replaceAll("<ACCOUNT_CODE>", bean.getAccountID());
			query = query.replaceAll("<ACCOUNT_ID>", bean.getAccountName());
			/*
			 * replace query Parameter as manage client Configuration
			 */
			replacedQuery=ReplacingQueryParameters(query,bean);
			/*
			 * For Replacing Configure Parameters by Manage Client DashBoard
			 */
			String repacedParameterString=ReplacingQueryParameters(query,bean);
			obj[i][2]=repacedParameterString;
			
			Object[][] replacedFiltetedObject=(Object[][])null;
			Object xmlObj[][]=(Object[][])null;
			if(String.valueOf(obj[i][12]).equals("I")){

				if(!String.valueOf(obj[i][15]).equals("null") &&  !String.valueOf(obj[i][16]).equals("null")){
				 replacedFiltetedObject=filterColums(repacedParameterString,null, String.valueOf(obj[i][15]), String.valueOf(obj[i][16])) ;
				 }			
				else{
					replacedFiltetedObject=getTableCellData_account(repacedParameterString,  bean.getAccountID(), bean.getAccountName());
				}	
				
				if(String.valueOf(obj[i][1]).equals("D")){
					comMap.put(String.valueOf(obj[i][0])+"-"+String.valueOf(obj[i][1])+"-"+String.valueOf(obj[i][4]), replacedFiltetedObject);}
					else{
						String multiComp=getStringMultiComp(String.valueOf(obj[i][0]));
						comMap.put(String.valueOf(obj[i][0])+"-"+String.valueOf(obj[i][1])+"-"+String.valueOf(obj[i][4]), getChartInfo("",replacedFiltetedObject, String.valueOf(obj[i][4]), multiComp,"", String.valueOf(obj[i][0])));
					}
				}
			else{
				extComps.add(i);
				xValues.add(String.valueOf(obj[i][15]));
				yValues.add(String.valueOf(obj[i][16]));
				obj[i][14]=getStringMultiComp(String.valueOf(obj[i][0]));
			}
			}//end of for
		/*
		 *  to get the data through external webservice
		 */
			if(extComps.size() > 0) {
				comMap = getDataFromWebservice(comMap, bean, extComps, obj,"false",xValues,yValues,0);
			}		
			request.setAttribute("comMap",comMap);
			return obj;
		
	}
	
	
	
	/*
	 * Replace Query String Parameter with Configured Parameter in ManageClientDashBoard
	 */
	public String ReplacingQueryParameters(String query,DashBoard bean){
		String ReplacedQuery="";
		String ReplacedPara="";
			try{Object[] ConfigParameter =new Object[6];
			ConfigParameter[0]=String.valueOf(bean.getDashBoardID());
			ConfigParameter[1]=String.valueOf(bean.getDashBoardID());
			ConfigParameter[2]=String.valueOf(bean.getDashBoardID());
			ConfigParameter[3]=String.valueOf(bean.getDashBoardID());
			ConfigParameter[4]=String.valueOf(bean.getUserID());
			if(String.valueOf(bean.getUserType()).equals("CRM")){
			ConfigParameter[5]="I";
			}
			else if(String.valueOf(bean.getUserType()).equals("HRM")){
			ConfigParameter[5]="P";
			}
			else{
				ConfigParameter[5]="E";
			}
			Object[][] ConfigParameterData=getSqlModel().getSingleResult(getQuery(10),ConfigParameter);
			
			if(ConfigParameterData!=null){
				if(ConfigParameterData.length>0){
				
					int ConfigParameterDatalen=ConfigParameterData.length;
				for(int j=0; ConfigParameterDatalen>j;j++){
					if(query.contains(String.valueOf(ConfigParameterData[j][0]))){//check configured parameter in Query
					//if query parameter contraint multipel parameter seperated by comma its woulde be converted to Sql format
						if(String.valueOf(ConfigParameterData[j][0]).contains(",")){
							String[] splitConfigParameterData=String.valueOf(ConfigParameterData[j][0]).split(",");
							String splitedString="";
					
							int splitConfigParameterDatalen=splitConfigParameterData.length;
							for(int k=0;k<splitConfigParameterDatalen;k++){
								splitedString=splitedString+"''"+splitConfigParameterData[k]+"'',";
							}// end for Loop
							ConfigParameterData[j][0]=splitedString.substring(0, splitedString.length()-1);
						}//end if(String.valueOf(ConfigParameterData[j][0]).contains(","))
						ReplacedPara="<"+String.valueOf(ConfigParameterData[j][0])+">";
						if(String.valueOf(ConfigParameterData[j][1])!="null"){
						ReplacedQuery=query.replaceAll(ReplacedPara, String.valueOf(ConfigParameterData[j][1]));}
						else{
							ReplacedQuery=query.replaceAll(ReplacedPara, "");
						}
						query=ReplacedQuery;
					}//end if(query.contains(String.valueOf(ConfigParameterData[j][0])))
					
					else{
						ReplacedQuery=query;
					}
					}//end For Loop
					}
				else{
					ReplacedQuery=query;// if query does not containt any filter it would be returning string as it is
				}
			}//end if(ConfigParameterData!=null)
			else{
				ReplacedQuery=query;
			}
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return ReplacedQuery;
		}
	
	public Object[][] filterColums(String query,Object[][] comObj,String xValue,String yValue){
		Vector vectorfilterObj=new Vector();
		Object[][] filterObject=(Object[][])null;
		Object[][] tabObject=(Object[][])null;
		
		String[] xColValu=xValue.split(",");
		String[] yColValu=yValue.split(",");

		int noOfCol=(xColValu.length)+(yColValu.length);	
		
		try{
			if(!query.equals("")){
			tabObject=getSqlModel().getSingleResultWithCol(query);
			}
			else{
				tabObject=comObj;
			}
			
			int Count=0;
			int tablen=tabObject.length;
			for(int i=0;i<tablen;i++){
				Count=0;
				for(int j=0; j<tabObject[0].length;j++){
					if( xColValu!=null && yColValu!=null ){						
						for(int k=0;k<yColValu.length;k++){							
							if((xColValu[0].equals(String.valueOf(j+1))&& k==0) || (yColValu[k].equals(String.valueOf(j+1)))){
								vectorfilterObj.add(tabObject[i][j]);
								}							
							}
					}					
				}
			}
			filterObject=Utility.to2DObject(vectorfilterObj, noOfCol);	
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return filterObject;
	}
	
	/**
	 * For Dril Down Specific Filter changes
	 * @param query
	 * @param bean
	 * @param NextDrillDownFilter
	 * @param NextDrillDownFilterValue
	 * @return
	 */
	public String ReplacingQueryParameters(String query,DashBoard bean,String NextDrillDownFilter,String NextDrillDownFilterValue){
		String ReplacedQuery="";
		String ReplacedPara="";
			try{
				Object[] ConfigParameter =new Object[6];//passing parameter to query
			ConfigParameter[0]=String.valueOf(bean.getDashBoardID());
			ConfigParameter[1]=String.valueOf(bean.getDashBoardID());
			ConfigParameter[2]=String.valueOf(bean.getDashBoardID());
			ConfigParameter[3]=String.valueOf(bean.getDashBoardID());
			ConfigParameter[4]=String.valueOf(bean.getUserID());
			if(String.valueOf(bean.getUserType()).equals("CRM")){
			ConfigParameter[5]="I";
			}
			else if(String.valueOf(bean.getUserType()).equals("HRM")){
			ConfigParameter[5]="P";
			}
			else{
				ConfigParameter[5]="E";
			}
			Object[][] ConfigParameterData=getSqlModel().getSingleResult(getQuery(10),ConfigParameter);
			
			if(ConfigParameterData!=null){
				if(ConfigParameterData.length>0){
				int ConfigParameterDatalen= ConfigParameterData.length;
					for(int j=0; ConfigParameterDatalen>j;j++){
					
					if(query.contains(String.valueOf(ConfigParameterData[j][0]))){
					//if query parameter contraint multiper parameter seperated by comma its woulde be converted to Sql format
						if(String.valueOf(ConfigParameterData[j][0]).contains(",")){
							String[] splitConfigParameterData=String.valueOf(ConfigParameterData[j][0]).split(",");
							String splitedString="";
							int splitConfigParameterDatalen=splitConfigParameterData.length;
							for(int k=0;k<splitConfigParameterDatalen;k++){
								splitedString=splitedString+"''"+splitConfigParameterData[k]+"'',";
							}// end for
							ConfigParameterData[j][0]=splitedString.substring(0, splitedString.length()-1);
						}//end if(String.valueOf(ConfigParameterData[j][0]).contains(","))
						ReplacedPara="<"+String.valueOf(ConfigParameterData[j][0])+">";
						
						if(NextDrillDownFilter!=null && NextDrillDownFilterValue!=null && NextDrillDownFilter.contains(String.valueOf(ConfigParameterData[j][0]))){
							ReplacedQuery=query.replaceAll(ReplacedPara, NextDrillDownFilterValue);
						}
						else{
							if(String.valueOf(ConfigParameterData[j][1])!="null"){
								ReplacedQuery=query.replaceAll(ReplacedPara, String.valueOf(ConfigParameterData[j][1]));
							}
						else{
						ReplacedQuery=query.replaceAll(ReplacedPara, "");
						}
						query=ReplacedQuery;
					}
						}
					
					else{
						ReplacedQuery=query;
					}
					}//end Of For Loop
					}
				else{
					ReplacedQuery=query;// if query does not containt any filter it would be returning string as it is
				}
			}// end 	if(ConfigParameterData!=null)
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return ReplacedQuery;
		}
	
	
		
	/**
	 * For identify object contains component name, Component ID, Component Query and Componet Type i.e (G,D)
	 * This function is used for getting 2D Table Object. 
	 * @param bean
	 * @return
	 */
	public Object[][] getTablesDetail(DashBoard bean) {
		Object[] configpara=new Object[3];//passing parameter to query
		try{
			configpara[0]=String.valueOf(bean.getDashBoardID());
			configpara[1]=String.valueOf(bean.getUserID());
			if(String.valueOf(bean.getUserType()).equals("CRM")){
				configpara[2]="I";
			}
			else if(String.valueOf(bean.getUserType()).equals("HRM")){
				configpara[2]="P";
			}
			else{
				configpara[2]="E";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		Object[][] obj = getSqlModel().getSingleResult(getQuery(5),configpara);
		return obj;
	}
	

	/**
	 * This Function is used for make Fusion Chart XML String. 
	 * @param Query
	 * @param displayNum
	 * @param request
	 * @param chartType
	 * @return
	 */
	public String getChartInfo(String Query, int displayNum,
			HttpServletRequest request, String chartType) {
		//String column3DQuery = Query;

		Object[][] column3DMap = getSqlModel().getSingleResultWithCol(Query);//getting 2D Object Result
		String replace = "";
		String column3DCharts = "";
		
		/*
		 * For Pia Graph
		 */
		// for Pia graph required only one row and header row to generete Graph
		int column3DMaplen=column3DMap[0].length;
		if (chartType.trim().equals("Pie2D")) {
			column3DCharts = "<chart enableSmartLabels='0' shownames='0' showValues='1' bgColor='ffffff,ffffff' showBorder='0' >";
			if ((column3DMap != null) && (column3DMap.length > 0)) {
				
				for (int i = 0; i < column3DMaplen; i++) {
					column3DCharts = column3DCharts + "<set label='"
							+ String.valueOf(column3DMap[0][i]) + "' value='"
							+ String.valueOf(column3DMap[1][i]) + "'/>";
				}
			}
			column3DCharts = column3DCharts + "</chart>";
			replace = column3DCharts.replace("null", "");
		} 
		/*
		 * For Line Graph
		 * // for Single Line graph required only one row and header row to generete Graph
		 */
		else if(chartType.trim().equals("Line")){
			column3DCharts="<chart    showValues='0'  divLineAlpha='50' bgColor='ffffff,ffffff' showBorder='0' >";
			if((column3DMap != null) && (column3DMap.length > 0)) {
				for (int i = 0; i < column3DMap[0].length; i++) {
					if(column3DMap.length!=1)
					column3DCharts = column3DCharts + "<set label='"
							+ String.valueOf(column3DMap[0][i]) + "' value='"
							+ String.valueOf(column3DMap[1][i]) + "'/>";
				}
				// for Some animated effect
				column3DCharts=column3DCharts+"<styles><definition><style name='Anim1' type='animation' param='_xscale' start='0' duration='0.6'/>" +
						"<style name='Anim2' type='animation' param='_alpha' start='0' duration='0.6'/>" +
						"<style name='DataShadow' type='Shadow' alpha='40'/></definition>" +
						"<application><apply toObject='DIVLINES' styles='Anim1'/>" +
						"<apply toObject='HGRID' styles='Anim2'/><apply toObject='DATALABELS' styles='DataShadow,Anim2'/>" +
						"</application></styles>";
			}
			column3DCharts = column3DCharts + "</chart>";
			replace = column3DCharts.replace("null", "");
			
		}
		
		
		// For Multiple Line n Multiple Bar chart
		else {
			if (displayNum > 0) {// this check whether hyperlink for drildown show only for expanded component
				column3DCharts = "<chart showvalues='1'  legendAllowDrag='1' rotateNames='1' shownames='1' decimals='0' slantLabels='1' numberPrefix='' labelDisplay='WRAP' showExportDataMenuItem='1' exportEnabled='1' exportAtClient='1' exportHandler='C:\\img' html5ExportHandler='C:\\img'>";
			}
			else if(chartType.trim().equals("MLine")){
				//column3DCharts = "<chart caption='Daily Visits' subcaption='(from 8/6/2006 to 8/12/2006)' lineThickness='1' showValues='0' formatNumberScale='0' anchorRadius='2' divLineAlpha='20' divLineColor='CC3300' divLineIsDashed='1' showAlternateHGridColor='1' alternateHGridAlpha='5' alternateHGridColor='CC3300' shadowAlpha='40' labelStep='2' numvdivlines='5' chartRightMargin='35' bgColor='FFFFFF,CC3300' bgAngle='270' bgAlpha='10,10'>";
				column3DCharts = "<chart showvalues='1'  legendAllowDrag='1' bgColor='ffffff,ffffff' rotateNames='1'  shownames='1' decimals='0' slantLabels='1' numberPrefix='' labelDisplay='WRAP' showExportDataMenuItem='1' exportEnabled='1' exportAtClient='1' showBorder='0' exportHandler='C:\\img' html5ExportHandler='C:\\img'>";
			}
			else if(chartType.trim().equals("MCLine")){
				column3DCharts = "<chart showvalues='1'  legendAllowDrag='1' yAxisValuesPadding='10' bgColor='ffffff,ffffff' rotateNames='1'  shownames='1' decimals='0' slantLabels='1' numberPrefix='' labelDisplay='WRAP' showExportDataMenuItem='1' exportEnabled='1' exportAtClient='1' exportHandler='C:\\img' html5ExportHandler='C:\\img'>";
			}
			else {
				column3DCharts = "<chart  showvalues='0'  legendAllowDrag='1' rotateNames='1' shownames='1' decimals='0' slantLabels='1' numberPrefix='' labelDisplay='WRAP' showExportDataMenuItem='1' exportEnabled='1' exportAtClient='1' exportHandler='C:\\img' html5ExportHandler='C:\\img'>";
			}
			Query = Query.replace("'<pdate>'"," to_char(sysdate-7,'DD-MON-RRRR') ");

			String categories = "<categories>";
			String dataset = "";
			String[] chartStr = new String[column3DMaplen];
			String[] datasetHdr = new String[column3DMap[0].length];
			String LineSetValue="";
			String LineCat="";
			for (int i = 0; i < column3DMap.length; i++) {
				if (i == 0)
					for (int j = 0; j < column3DMaplen; j++) {
						
						datasetHdr[j] = String.valueOf(column3DMap[i][j]);
					}
				else {
					
					for (int j = 0; j < chartStr.length; j++) {
						if (j == 0) {
							if (String.valueOf(column3DMap[i][0]).trim().length() > 15) {
								int tmp336_334 = j;
								String[] tmp336_332 = chartStr;
								tmp336_332[tmp336_334] = (tmp336_332[tmp336_334]+ "<category label='"
										+ String.valueOf(column3DMap[i][0])
												.trim().substring(0, 10) + "'/>");
							} else {
								int tmp392_390 = j;
								String[] tmp392_388 = chartStr;
								tmp392_388[tmp392_390] = (tmp392_388[tmp392_390]
										+ "<category label='"
										+ String.valueOf(column3DMap[i][0])
												.trim() + "'/>");
							}
						} else {
							int tmp442_440 = j;
							String[] tmp442_438 = chartStr;
							tmp442_438[tmp442_440] = (tmp442_438[tmp442_440]
									+ "<set value='"
									+ String.valueOf(column3DMap[i][j]).trim() + "'/>");
							LineSetValue=(tmp442_438[tmp442_440]
									+ "<set value='"
									+ String.valueOf(column3DMap[i][j]).trim() + "'/>");
						}
					}
				}

			}

			int chartStrlen=chartStr.length;
			for (int i = 0; i < chartStrlen; i++) {
			
				if (i == 0) {
					column3DCharts = column3DCharts + "<categories>"
							+ chartStr[i] + "</categories>";
					LineCat=LineCat+"<categories>";
					
				} else {
					if(i+1== chartStrlen){
					column3DCharts = column3DCharts + "<dataset seriesName='"
							+ datasetHdr[i] + "'showValues='0' parentYAxis='S' >" + chartStr[i] + "</dataset>";}
					else{
						column3DCharts = column3DCharts + "<dataset seriesName='"
						+ datasetHdr[i] + "'showValues='0'>" + chartStr[i] + "</dataset>";
					}
				}
				if(i!=0){
					LineSetValue="<dataset seriesName='"
						+ datasetHdr[i] + "'  renderAs='Line' showValues='0'>" + chartStr[i] + "</dataset>";
				}
			}
			
			column3DCharts = column3DCharts+"</chart>";
			

			replace = column3DCharts.replace("null", "");
			
		}
		return replace;
	}

	
	/**
	 * This function is used for make fusion chart String .this function is used for Drill down Graph
	 * @param query
	 * @param chartType
	 * @param nextComponentId
	 * @param graphParameters
	 * @param currentComponentId
	 * @param request
	 * @return
	 */
	public String getChartInfo(String query,Object[][] compObj, String chartType,
			String nextComponentId, String graphParameters,
			String currentComponentId) {
		Object[][] column3DMap =(Object[][])null;
		if(compObj!=null && compObj.length>0){
			column3DMap=compObj;
		}
		else{
		column3DMap = getSqlModel().getSingleResultWithCol(
				query);//getting 2D Object from Given Query
		}
		String replace = "";
		String column3DCharts = "";
		int column3DMaplen=column3DMap[0].length;
		if (chartType.trim().equals("Pie2D")) {
			column3DCharts = "<chart enableSmartLabels='0' shownames='0' showValues='1' bgColor='ffffff,ffffff' showBorder='0' >";
			if ((column3DMap != null) && (column3DMap.length > 0)) {
				
				for (int i = 0; i < column3DMaplen; i++) {
				try{	
					String graphLink = "' link=\'JavaScript:getGraph(\""
						+ currentComponentId
						+ "\",\""
						+ nextComponentId
						+ "\",\""
						+ graphParameters
						+ "\",\""
						+ String.valueOf(column3DMap[0][i]).trim()
						+ "\",\""+0+"\")\' ";
					
					column3DCharts = column3DCharts + "<set label='"
							+ String.valueOf(column3DMap[0][i]) + "' value='"
							+ String.valueOf(column3DMap[1][i]) +""+graphLink+ " />";
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				}
				
			}
			column3DCharts = column3DCharts + "</chart>";
			replace = column3DCharts.replace("null", "");
		} else if(chartType.trim().equals("USGraph")) {
			column3DCharts=getUSGraphString(column3DMap,currentComponentId,nextComponentId,graphParameters);
		}
		else{

			column3DCharts = "<chart showvalues='1' legendAllowDrag='1' rotateNames='1' shownames='1' decimals='0' slantLabels='1' numberPrefix='' labelDisplay='WRAP' showExportDataMenuItem='1' exportEnabled='1' exportAtClient='1' showBorder='0' bgColor='ffffff,ffffff' exportHandler='C:\\img' html5ExportHandler='C:\\img'>";

			String categories = "<categories>";
			String dataset = "";
			String[] chartStr = new String[column3DMaplen];
			String[] datasetHdr = new String[column3DMaplen];

			for (int i = 0; i < column3DMap.length; i++) {
				if (i == 0)
					for (int j = 0; j < column3DMaplen; j++) {
						System.out.println("--------header----"
								+ String.valueOf(column3DMap[i][j]));
						datasetHdr[j] = String.valueOf(column3DMap[i][j]);
					}
				else {
					for (int j = 0; j < chartStr.length; j++) {
						if (j == 0) {
							if (String.valueOf(column3DMap[i][0]).trim()
									.length() > 15) {
								int tmp336_334 = j;
								String[] tmp336_332 = chartStr;
								tmp336_332[tmp336_334] = (tmp336_332[tmp336_334]
										+ "<category label='"
										+ String.valueOf(column3DMap[i][0])
												.trim().substring(0, 10) + "'/>");
							} else {
								int tmp392_390 = j;
								String[] tmp392_388 = chartStr;
								tmp392_388[tmp392_390] = (tmp392_388[tmp392_390]
										+ "<category label='"
										+ String.valueOf(column3DMap[i][0])
												.trim() + "'/>");
							}
						} else {
							int tmp442_440 = j;
							String[] tmp442_438 = chartStr;
							//hyperLink to next drill down Grpah
							String graphLink = "' link=\'JavaScript:getGraph(\""
									+ currentComponentId
									+ "\",\""
									+ nextComponentId
									+ "\",\""
									+ graphParameters
									+ "\",\""
									+ String.valueOf(column3DMap[i][0]).trim()
									+ "\",\""+0+"\")\' />";

							tmp442_438[tmp442_440] = (tmp442_438[tmp442_440]
									+ "<set value='"
									+ String.valueOf(column3DMap[i][j]).trim() + graphLink);
						}
					}
				}
			}

			for (int i = 0; i < chartStr.length; i++) {
				// System.out.println("Table Header----" + datasetHdr[i]);
				if (i == 0) {
					column3DCharts = column3DCharts + "<categories>"
							+ chartStr[i] + "</categories>";
				} else {
					if(i+1==chartStr.length){
					column3DCharts = column3DCharts + "<dataset seriesName='"
							+ datasetHdr[i] + "'parentYAxis='S' >" + chartStr[i] + "</dataset>";}
					else{
						column3DCharts = column3DCharts + "<dataset seriesName='"
						+ datasetHdr[i] + "' >" + chartStr[i] + "</dataset>";
					}
				}
			}
			String canvasEffect="<styles>          " +
					" <definition>             " +
					" <style type='font' name='CaptionFont' color='666666' size='15' />  " +
					" <style type='font' name='SubCaptionFont' bold='0' /> " +
					" </definition> " +
					" <application> " +
					" <apply toObject='caption' styles='CaptionFont' /> " +
					" <apply toObject='SubCaption' styles='SubCaptionFont' /> " +
					"  </application>     " +
					"  </styles>";
			column3DCharts = column3DCharts + canvasEffect+"</chart>";
			System.out.println("Chart String-----------" + column3DCharts);

			
		}
		/*
		 * if(nextGraph!=null){ request.setAttribute("graph1", replace); }
		 * 
		 */
		replace = column3DCharts.replace("null", "");
		System.out.println("column3DCharts:::::::" + replace.trim());
		return replace;
	}
	/*
	 *for Getting Report List of DashBoard 
	 */
	/**
	 * This function is used for getting reports Attributes and set it defaults values
	 * @param bean
	 * @return
	 */
	public Object[][] getMenuParameter(DashBoard bean) {
		Object[][] parameter = (Object[][]) null;
		Object[] configpara=new Object[4];

		
		
		try{
			
			configpara[0]=String.valueOf(bean.getUserID());//passing paramter to query
			
			if(String.valueOf(bean.getUserType()).equals("CRM")){
				configpara[1]="I";
			}
			else if(String.valueOf(bean.getUserType()).equals("HRM")){
				configpara[1]="P";
			}
			else{
				configpara[1]="E";
			}
			configpara[2]=String.valueOf(bean.getDashBoardID());
			configpara[3]=String.valueOf(bean.getReportID());
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		parameter = getSqlModel().getSingleResult(getQuery(7),
				configpara);
	int parameterlen=parameter.length;
	//assigning some defoult value to Fileds
	for (int i = 0; i < parameterlen; i++) {
			if (String.valueOf(parameter[i][2]).equals("query")) {
				String listMap = "";
				Object[][] optionObj = getSqlModel().getSingleResult(
						String.valueOf(parameter[i][4]));
				int optionObjlen=optionObj.length;
				for (int j = 0; j < optionObjlen; j++) {
					listMap = listMap + String.valueOf(optionObj[j][0]) + "@"
							+ String.valueOf(optionObj[j][1]) + "~";
				}
				listMap = listMap.substring(0, listMap.length() - 1);

				parameter[i][3] = listMap;
			} else if ((String.valueOf(parameter[i][2]).equals("date"))
					&& (String.valueOf(parameter[i][3]).equals("<sysdate>"))) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				parameter[i][3] = sdf.format(Long
						.valueOf(new Date().getTime() - 86400000L));
			} else if ((String.valueOf(parameter[i][2]).equals("text"))	&& ((parameter[i][3] == null) || (String.valueOf(parameter[i][3]).equals("null")))) {
				
				if(parameter[i][8]!=null&&!String.valueOf(parameter[i][8]).equals("")){
					parameter[i][3]=String.valueOf(parameter[i][8]);
				}
				else{
				parameter[i][3] = "";}
				
			} else if ((String.valueOf(parameter[i][2]).equals("number"))
					&& ((parameter[i][3] == null) || (String
							.valueOf(parameter[i][3]).equals("null")))) {
				parameter[i][3] = Integer.valueOf(0);
			}
			
		}
		return parameter;
	}




	// For DrilDown Component purpose 
	
	public void getResultbyComponentId(String preCompnentId, String CompnentID,
			String graphParameter, String nextParam, HttpServletRequest request, String accountCode,String accountId,DashBoard bean,String autoID) {
		try {
			System.out.println("Componet ID is :" + CompnentID);
			System.out.println("graphParameter :" + graphParameter);
			System.out.println("nextParam :" + nextParam);
			System.out.println("AutoID :" + autoID);
			Object[][] componentData = (Object[][]) null;
			Object[][] tableData = (Object[][]) null;
			List chartQyery = new ArrayList();
			String multiComponent[]=CompnentID.split(",");
			LinkedHashMap mainMap = new LinkedHashMap();

			/*
			 * One Component Block
			 */
			Vector multiComponetVec=new Vector();
			
			{
				for(int cont=0; cont<multiComponent.length; cont++){
				componentData = getSqlModel().getSingleResult(
						getQuery(4), new Object[] { autoID,multiComponent[cont] });
				String query = String.valueOf(componentData[0][2]);
				String graphName = String.valueOf(componentData[0][3]);
				String graphType = String.valueOf(componentData[0][4]);
				String nextComponentId = String.valueOf(componentData[0][5]);
				String isGraph = String.valueOf(componentData[0][6]);
				String isTable = String.valueOf(componentData[0][7]);
				String xColsValue=String.valueOf(componentData[0][9]);
				String yColsValue=String.valueOf(componentData[0][10]);
				String isDataInternal=String.valueOf(componentData[0][11]);
				String componentNo=String.valueOf(componentData[0][0]);

				/*
				 * to get the column name clicked in previous graph
				 */
				String defaultParameter = "";
				if (!(preCompnentId == null || preCompnentId.equals("null") || preCompnentId
						.equals(""))) {
					System.out.println("in : ");
					Object[][] preCompData = getSqlModel()
							.getSingleResult(getQuery(4),
									new Object[] { autoID,preCompnentId });
					defaultParameter = String.valueOf(preCompData[0][8]).trim();
				}
				System.out.println("defaultParameter : " + defaultParameter);
				String graphParam = graphParameter;
				/*
				 * Find the components of the query and replace parameters.
				 */
				Object[][] componentParameters = getSqlModel()
						.getSingleResult(getQuery(8),
								new Object[] { multiComponent[cont] });
				System.out.println("component length--  "
						+ componentParameters.length);
				/*
				 * Replacing Parameter of String to default String
				 */
				
				System.out.println("query before : " + query);
				/*
				 * to replace accountId and accountName for crm purpose
				 * 
				 */
				query = query.replaceAll("<ACCOUNT_CODE>", accountCode);
				query = query.replaceAll("<ACCOUNT_ID>", accountId);
				query=query.replaceAll("<PMONTH>", bean.getDashMonth());
				query=query.replaceAll("<PYEAR>", bean.getDashYear());
				
				int componentParameterslen=componentParameters.length;
				if (componentParameterslen > 0) {

					/*
					 * To replace the parameter which is clicked in the graph
					 */
				
					for (int i = 0; i < componentParameterslen; i++) {
						System.out.println("componentParameters-- "
								+ String.valueOf(componentParameters[i][0]));

						if (String.valueOf(componentParameters[i][0]).trim()
								.equals(defaultParameter.trim())) {
							graphParam = graphParam + "1111"
									+ String.valueOf(componentParameters[i][0])
									+ "2222" + nextParam;
						}
					}
					System.out.println("graphParameter--  " + graphParam);
					/*
					 * Build the query by replacing dynamic parameters
					 * 
					 */
					String[] completeParam = graphParam.split("1111");
					int completeParamlen=completeParam.length;
					for (int i = 1; i < completeParamlen; i++) {
						String[] params = completeParam[i].split("2222");
						if (params.length > 0) {
							System.out
									.println("parameters replaced :"
											+ params[0] + " : with value :"
											+ params[1]);
							query = query.replaceAll("<" + params[0] + ">",
									params[1]);
						}
					}
					/*
					 * To Replace Configure Parameter
					 */
					
					query=ReplacingQueryParameters(query, bean,String.valueOf(defaultParameter).trim(),String.valueOf(nextParam).trim());
					/*
					 * To replace remaining parameter for which values were not
					 * available
					 */
					//int componentParameterslen=componentParameters.length;
					for (int i = 0; i < componentParameterslen; i++) {
						// logic to remove duplicate parameters to be added
						// here.
						if (!String.valueOf(componentParameters[i][0]).equals(
								defaultParameter)) {
							query = query.replaceAll("<"
									+ String.valueOf(componentParameters[i][0])
									+ ">", "");
						}
					}
				}
				
				System.out.println("query after : " + query);
				request.setAttribute("GraphPara", graphParam);
				LinkedHashMap componentMap = new LinkedHashMap();
				/*
				 * Verify if the component is graph or data. set the request
				 * parameter accordingly.
				 */
				Vector xValues=new Vector();
				Vector yValues=new Vector();
				
				if(isDataInternal.equals("I")){
					
					/*
					 * For table Data Filter Column this object is used for both table data and graph 
					 */
					
					
					
				componentMap.put("isDataInternal", isDataInternal);
				if (isGraph.equals("Y")) {
					componentMap.put("isChart", "Y");
					/*
					 * send the Chart data back to JSP through request.
					 */
					nextComponentId=getStringMultiComp(multiComponent[cont]);
					
					System.out.println("NextCompID by Comma"+nextComponentId);
					Object[][] tabData=null;
					if(!xColsValue.equals("null")&&(!yColsValue.equals("null"))){
						tabData=filterColums(query,null ,xColsValue, yColsValue);
					}
					String chartData = getChartInfo(query, // query
							tabData,	
							graphType, // graph type
							nextComponentId, // next component id - one or
							// many
							graphParam, // graph parameters
							multiComponent[cont]//Current Component ID
							);

					chartQyery.add(chartData); // to be removed.
					request.setAttribute("Chartquery", chartQyery); // to be
					componentMap.put("chartData", chartData);
				} else {
					/*
					 * The component is only data
					 */
					componentMap.put("isChart", "N");
				}
				if (isTable.equals("Y")) {
					if((!xColsValue.equals("null"))&&(!yColsValue.equals("null"))){
//						tableData=filterColums(query, xColsValue, yColsValue);
						xValues.add(xColsValue);
						yValues.add(yColsValue);
					}
					Object[][] tabData=getSqlModel().getSingleResultWithCol(query);
					request.setAttribute("tableHeader", graphName);// to be	// removed.
					componentMap.put("isData", "Y");
					componentMap.put("tableData", tabData);
					componentMap.put("nextComponentId", nextComponentId);
					componentMap.put("graphParam", graphParam);
					componentMap.put("graphName", graphName);
					componentMap.put("isTable", "Y");
					//componentMap.put("currentComponentId", preCompnentId);//multiComponent[cont]
					componentMap.put("currentComponentId", multiComponent[cont]);
					componentMap.put("componentId",CompnentID);
					componentMap.put("graphType",graphType.trim());
				} else {
					componentMap.put("isTable", "N");
					componentMap.put("isData", "Y");
					componentMap.put("tableData", tableData);
					componentMap.put("nextComponentId", nextComponentId);
					componentMap.put("graphParam", graphParam);
					componentMap.put("graphName", graphName);
					//componentMap.put("isTable", "Y");
					//componentMap.put("currentComponentId", preCompnentId);//multiComponent[cont]
					componentMap.put("currentComponentId", multiComponent[cont]);
					componentMap.put("componentId",CompnentID);
					componentMap.put("graphType",graphType.trim());
				}
				mainMap.put(String.valueOf(cont), componentMap); // Add all the charts here.
				}//end of isDataInternal if
				else{//for isDataExternal 
					componentData[0][2]=query;
					componentMap.put("isDataInternal", isDataInternal);
					if((!xColsValue.equals("null"))&&(!yColsValue.equals("null"))){
//						tableData=filterColums(query, xColsValue, yColsValue);
						xValues.add(xColsValue);
						yValues.add(yColsValue);
					}
					else{
						String value="null";
						xValues.add(value);
						yValues.add(value);
					}
					
					
					Vector vec=new Vector();
					vec.add(0);
					if (isGraph.equals("Y")) {
						componentMap.put("isChart", "Y");
						/*
						 * send the Chart data back to JSP through request.
						 */
						nextComponentId=getStringMultiComp(multiComponent[cont]);
						
						System.out.println("NextCompID by Comma"+nextComponentId);
						Object [][] compDATA=(Object[][])null;
						

					} else {
						/*
						 * The component is only data
						 */
						componentMap.put("isChart", "N");
					}
					
					if (isTable.equals("Y")) {
						if((!xColsValue.equals("null"))&&(!yColsValue.equals("null"))){
//							tableData=filterColums(query, xColsValue, yColsValue);
							xValues.add(xColsValue);
							yValues.add(yColsValue);
						}
						else{
						tableData = getSqlModel().getSingleResultWithCol(
								query); }// to be removed.}
						request.setAttribute("tableData", tableData);// to be
																		// removed.
						request.setAttribute("tableHeader", graphName);// to be
																		// removed.
						componentMap.put("isData", "Y");
						componentMap.put("nextComponentId", nextComponentId);
						componentMap.put("graphParam", graphParam);
						componentMap.put("graphName", graphName);
						componentMap.put("isTable", "Y");
						//componentMap.put("currentComponentId", preCompnentId);//multiComponent[cont]
						componentMap.put("currentComponentId", multiComponent[cont]);
						componentMap.put("componentId",CompnentID);
						componentMap.put("graphType",graphType.trim());
						componentMap.put("compQuery", query);
					} else {
						componentMap.put("isTable", "N");
						componentMap.put("isData", "Y");
						componentMap.put("nextComponentId", nextComponentId);
						componentMap.put("graphParam", graphParam);
						componentMap.put("graphName", graphName);
						//componentMap.put("isTable", "Y");
						//componentMap.put("currentComponentId", preCompnentId);//multiComponent[cont]
						componentMap.put("currentComponentId", multiComponent[cont]);
						componentMap.put("componentId",CompnentID);
						componentMap.put("graphType",graphType.trim());
						componentMap.put("compQuery", query);
					}
					
					
					mainMap.put(String.valueOf(cont), componentMap);
					
					
					mainMap=getDataFromWebservice(mainMap, bean,vec , componentData,"true",xValues,yValues,cont);
					
				}
				} // End MultiComponent For Loop
				request.setAttribute("mainMap", mainMap);// added Vijay mainMap(HashMap)
				
				
				
			}// component block end
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
		
		public String getFilterDrilDownQuery(String currentComponentId, 
				String graphParam,String nextParam,  DashBoard bean ){
			String query="";
			try {
				Object[][] componentParameters = getSqlModel().getSingleResult(
						getQuery(8), new Object[] { currentComponentId });
				String querystr = " select ccm.COMPONENT_QUERY,ccm.DEFAULT_PARAMETER  from cr_component_master ccm where  ccm.component_id = "+currentComponentId;
						
				Object[][] componentQuery = getSqlModel().getSingleResult(
						querystr);
				String defaultParameter = String.valueOf(componentQuery[0][1]).trim();
				query = String.valueOf(componentQuery[0][0]);
				query = query.replaceAll("<ACCOUNT_CODE>", bean.getAccountID());
				query = query.replaceAll("<ACCOUNT_ID>", bean.getAccountName());
				query=query.replaceAll("<PMONTH>", bean.getDashMonth());
				query=query.replaceAll("<PYEAR>", bean.getDashYear());
				
				int componentParameterslen = componentParameters.length;
				/*
				 * To replace the parameter which is clicked in the graph
				 */
				if(graphParam!=null && !graphParam.equals("")){
					String[] graphparaSplit=graphParam.split("&");
					//replace selected parameter
					for(int i=0; i<componentParameterslen;i++){
						for(int j=0;j<graphparaSplit.length-1;j++){
							String graphParaSplit1[]=null;
							if(!graphparaSplit[j+1].equals("")){
						 graphParaSplit1=String.valueOf(graphparaSplit[j+1]).split("=");}
						String queryPara1=String.valueOf(graphParaSplit1[0]);
						String queryPara2=new String(componentParameters[i][0].toString());
						System.out.println("queryPara1 - "+queryPara1+"queryPara2 - "+queryPara2);	
						if(queryPara1.equals(queryPara2)){
							query=query.replace("<"+String.valueOf(componentParameters[i][0])+">", graphParaSplit1[1]);
						}
						
						}
						
					}
					//replace remaining parameter
					for(int i=0;i<componentParameterslen;i++){
						query=query.replace("<"+String.valueOf(componentParameters[i][0])+">", "");
					}
				
					/*if (componentParameterslen > 0) {
					
					
					
					for (int i = 0; i < componentParameterslen; i++) {
						System.out.println("componentParameters-- "
								+ String.valueOf(componentParameters[i][0]));
						
						if (String.valueOf(componentParameters[i][0]).trim()
								.equals(defaultParameter.trim())) {
							graphParam = graphParam + "1111"
									+ String.valueOf(componentParameters[i][0])
									+ "2222" + nextParam;
						}
					}
					System.out.println("graphParameter--  " + graphParam);
					
					 * Build the query by replacing dynamic parameters
					 * 
					 
					String[] completeParam = graphParam.split("1111");
					int completeParamlen = completeParam.length;
					
					
					for (int i = 1; i < completeParamlen; i++) {
						String[] params = completeParam[i].split("2222");
						if (params.length > 0) {
							System.out
									.println("parameters replaced :"
											+ params[0] + " : with value :"
											+ params[1]);
							query = query.replaceAll("<" + params[0] + ">",
									params[1]);
						}
					}
					
					 * To Replace Configure Parameter
					 

					query = ReplacingQueryParameters(query, bean, String
							.valueOf(defaultParameter).trim(), String.valueOf(
							nextParam).trim());
					
					 * To replace remaining parameter for which values were not
					 * available
					 
					//int componentParameterslen=componentParameters.length;
					for (int i = 0; i < componentParameterslen; i++) {
						// logic to remove duplicate parameters to be added
						// here.
						if (!String.valueOf(componentParameters[i][0]).equals(
								defaultParameter)) {
							query = query.replaceAll("<"
									+ String.valueOf(componentParameters[i][0])
									+ ">", "");
						}
					}

				}*/
				}
				else{
					
					
					for (int i = 0; i < componentParameterslen; i++) {
						// logic to remove duplicate parameters to be added
						// here.
						
							query = query.replaceAll("<"
									+ String.valueOf(componentParameters[i][0])
									+ ">", "");
						
					}
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return query;
		}
	
	
	
	
	
	/**
	 * used to getting table and data object of component CRM
	 * @param CompnentID
	 * @param request
	 * @param dashboardID 
	 * @param accountCode
	 * @param accountId
	 */
	public void getResultbyComponentId_account(String CompnentID,
			HttpServletRequest request, String dashboardID, String accountCode,
			String accountId) {
		System.out.println("Componet ID is" + CompnentID);
		Object[][] parameter = (Object[][]) null;
		parameter = getSqlModel().getSingleResult(getQuery(4),
				new Object[] { CompnentID, dashboardID });
		Object[][] tableData = getTableCellData_account(
				(String) parameter[0][0], accountCode, accountId);
		request.setAttribute("tableHeader", String.valueOf(parameter[0][1]));
		String QueryForChart = (String) parameter[0][0];
		QueryForChart = QueryForChart.replace("<pdate>",
				" to_char(sysdate-7,'DD-MON-RRRR')");
		QueryForChart = QueryForChart.replace("<ACCOUNT_CODE>", accountCode);
		QueryForChart = QueryForChart.replace("<ACCOUNT_ID>", accountId);
		String Chartquery = getChartInfo(QueryForChart, 1, request, String
				.valueOf(parameter[0][2]));
		request.setAttribute("tableData", tableData);
		request.setAttribute("Chartquery", Chartquery);
		request.setAttribute("charType", String.valueOf(parameter[0][2]));
	}
	/*
	 * saveUrlToFile is used for attachment to Email
	 */
	public void saveUrlToFile(File saveFile, String location) {
		System.out.println("URL Link"+location);
		OutputStream outStream = null;
		URLConnection uCon = null;
		InputStream is = null;
		System.out.println("URL Save to File" + location);
		int size = 1024;
		try {
			int ByteWritten = 0;
			URL Url = new URL(location);
			outStream = new BufferedOutputStream(new FileOutputStream(saveFile));
			uCon = Url.openConnection();
			is = Url.openStream();
			byte[] buf = new byte[1024];
			int ByteRead;
			while ((ByteRead = is.read(buf)) != -1) {
				// int ByteRead;
				outStream.write(buf, 0, ByteRead);
				ByteWritten += ByteRead;
			}
			/*System.out.println("Downloaded Successfully.");
			System.out.println("File name:\"" + saveFile + "\"\nNo ofbytes :"
					+ ByteWritten);*/
		} catch (Exception e) {
			e.printStackTrace();
			try {
				is.close();
				outStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				is.close();
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * Attach File to email for xml
	 */
	public void saveXmlURLtoFile(File saveFile, String location, String reportXml){
		try {
			reportXml = java.net.URLEncoder.encode(reportXml, "UTF-8");
			OutputStream outStream = null;
			InputStream is = null;
			int size = 1024;
			try {
				int ByteWritten = 0;
				URL Url = new URL(location);
				outStream = new BufferedOutputStream(new FileOutputStream(saveFile));
				URLConnection connection = Url.openConnection();
				connection.setDoOutput(true);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(reportXml);
				out.close();
				
				is = connection.getInputStream();
				byte[] buf = new byte[1024];
				int ByteRead;
				while ((ByteRead = is.read(buf)) != -1) {
					// int ByteRead;
					outStream.write(buf, 0, ByteRead);
					ByteWritten += ByteRead;
				}
				/*System.out.println("Downloaded Successfully.");
				System.out.println("File name:\"" + saveFile + "\"\nNo ofbytes :"
						+ ByteWritten);*/
			} catch (Exception e) {
				e.printStackTrace();
				try {
					is.close();
					outStream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} finally {
				try {
					is.close();
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}		
		
		
		
		
	}
	
	public void setData(DashBoard dasBoard, HashMap<String, String> jobDetails) {
		try {
			if (jobDetails.size() > 0) {
				dasBoard.setJobDuration((String) jobDetails.get("DURATION"));
				dasBoard
						.setJobDayOfWeek((String) jobDetails.get("DAY_OF_WEEK"));
				dasBoard.setJobDayOfMonth((String) jobDetails
						.get("DAY_OF_MONTH"));
				dasBoard.setJobStartTime((String) jobDetails.get("START_TIME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * for getting report list on CRM dashBoard 
	 * @param dashBean
	 * @return
	 */
	public Object[][] getTableMenuList_Account(DashBoard dashBean) {
		ArrayList MenuList = new ArrayList();

		Object[][] object = (Object[][]) null;
		try {
			Object[] configParameters=new Object[3];
			configParameters[0]=String.valueOf(dashBean.getDashBoardID()); // DashBoard ID
			configParameters[1]=String.valueOf(dashBean.getUserID()); // User ID
			if(String.valueOf(dashBean.getUserType()).equals("CRM")){
			configParameters[2]="I"; // Internal peoplepower user
			}
			else if(String.valueOf(dashBean.getUserType()).equals("HRM")){
			configParameters[2]="P"; // Internal peoplepower user
			}
			else{
				configParameters[2]="E"; // external peoplepower user -- client table
			}
			
			object = getSqlModel().getSingleResult(getQuery(2),
					configParameters);

			int objectlen=object.length;
			for (int i = 0; i < objectlen; i++) {
				DashBoard beanItt = new DashBoard();
				beanItt.setReportName(String.valueOf(object[i][0])); // Report Name
				MenuList.add(beanItt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		dashBean.setMenuList(MenuList);
		return object;
	}

	public Object[][] getTableAttribute_Account(String dashboardID) {
		Object[][] obj = (Object[][]) null;
		try {
			obj = getSqlModel().getSingleResult(getQuery(1),
					new Object[] { dashboardID });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public Object[][] getTablesDetail_Account(DashBoard bean) {
		Object[] configpara=new Object[3];
		try{
			configpara[0]=String.valueOf(bean.getDashBoardID());
			configpara[1]=String.valueOf(bean.getUserID());
			if(String.valueOf(bean.getUserType()).equals("CRM")){
				configpara[2]="I";
			}
			else if(String.valueOf(bean.getUserType()).equals("HRM")){
				configpara[2]="P";
			}
			else{
				configpara[2]="E";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		Object[][] obj = getSqlModel().getSingleResult(getQuery(5),
				configpara);
		return obj;
	}
								//accountCode=accountID			//accountId=accountname
	
/*
 * For Getting Colspan
 */
	public HashMap getMatrixElements_account(DashBoard bean) {
		String userType="";
		if(bean.getUserType().equals("CRM")){
			userType="I";
		}
		else if(bean.getUserType().equals("HRM")){
			userType="P";
		}
		else{
			userType="E";
		}
		String query = "select convert(nvarchar,cdf.matrix_row)+'-'+convert(nvarchar,cdf.matrix_col)," +
		" cug.dashboard_config_autoid as rowkey,  cdf.rowspan, cdf.colspan  " +
		" from cr_dashboard_config cdf left join cr_user_graph cug on cdf.autoid=cug.dashboard_config_autoid " +
		" and USER_ID= "+bean.getUserID()+
		" and user_type= '"+userType+"'"+
		" where cdf.dashboard_id="+ bean.getDashBoardID();

		HashMap Mapobj = getSqlModel().getSingleResultMap(query, 0, 0);

		return Mapobj;
	}
	
	/**
	 * For mainting each action by users 
	 * @param logColoum
	 */
	public void DashBoardLogModel(Object[][] logColoum){
		try{
			if(logColoum!=null){
			String query="select NVL(MAX(cal.autoid),0)+1 from cr_access_log cal"; 	
			Object[][] maxIDObj=getSqlModel().getSingleResult(query);	
			int maxID=Integer.parseInt(String.valueOf(maxIDObj[0][0]));
			logColoum[0][0]=maxID;
			Boolean result= getSqlModel().singleExecute(getQuery(9),logColoum);}
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	/*
	 * This function is used in getting configurated parameters for replacing in required configuration 
	 */
	
	public Object[][] getConfigurePara(DashBoard bean){
		Object[][] parameters=null;
		
			try{Object[] ConfigParameter =new Object[6];
			ConfigParameter[0]=String.valueOf(bean.getDashBoardID());
			ConfigParameter[1]=String.valueOf(bean.getDashBoardID());
			ConfigParameter[2]=String.valueOf(bean.getDashBoardID());
			ConfigParameter[3]=String.valueOf(bean.getDashBoardID());
			ConfigParameter[4]=String.valueOf(bean.getUserID());
			if(String.valueOf(bean.getUserType()).equals("CRM")){
			ConfigParameter[5]="I";
			}
			else if(String.valueOf(bean.getUserType()).equals("HRM")){
			ConfigParameter[5]="P";
			}
			else{
				ConfigParameter[5]="E";
			}
			parameters=getSqlModel().getSingleResult(getQuery(10),ConfigParameter);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	return parameters;
	
	}
	
	/*
	 * converting XML Data to 2D Object and vise versa
	 */
	
	
	public String getXmlFromComponentQuery(Vector compIds, DashBoard bean, Object[][] obj, boolean isComponentData){
		
		
		
		String xmlStr="  <d1-Graph>	" +
					"<auth>" +
					"<user>" +"UserName"+
					"</user>" +
					"<password>" +"password"+
					"</password>" +
					"</auth>";
						
					for(int k=0; k < compIds.size(); k ++) {
						Object vecObj[]=(Object[])compIds.get(k);
						String query = String.valueOf(vecObj[2]);
						//for replacing default parameters with default value
						query=ReplacingQueryParameters(query, bean)	;				
						query=query.replaceAll("<", "&lt;");
						query=query.replaceAll(">", "&gt;");
						
						xmlStr+="<component>" +
						"<componentid>" + String.valueOf(vecObj[0]) +//componentId+
						"</componentid>" +
						"<query>" + query + // to add the queryString
						"</query>" ;
						if(isComponentData){
							xmlStr+="<DBName>"+String.valueOf(vecObj[13])+"</DBName>";
						}
						else{
							xmlStr+="<DBName>"+String.valueOf(vecObj[17])+"</DBName>";
						}
						xmlStr+="</component>"; 
						Object[][] configPara=getConfigurePara(bean);
						xmlStr=xmlStr+"<parameters>";
						String parameterString="";
						for(int i=0;i<configPara.length;i++){
							
							parameterString=parameterString+"<param><name>"
							
							+Utility.checkNull(String.valueOf(configPara[i][0]))+"</name>" ;
							if(String.valueOf(configPara[i][0]).equals("ACCOUNT_CODE")){		
								parameterString=parameterString+"<value>"
							
							+Utility.checkNull(String.valueOf(bean.getAccountID()))+"</value>";}
							else if(String.valueOf(configPara[i][0]).equals("ACCOUNT_ID")){		
								parameterString=parameterString+"<value>"
							
							+Utility.checkNull(String.valueOf(bean.getAccountName()))+"</value>";}
							else{
								parameterString=parameterString+"<value>"
								+Utility.checkNull(String.valueOf(configPara[i][1]))+"</value>";
							}
							
							
							parameterString=parameterString+"</param>";
							}
						xmlStr=xmlStr+parameterString+
						"</parameters>";
						
					}
					xmlStr=xmlStr+" </d1-Graph> ";
		return xmlStr;
	}
	/*
	 * converting XML Data to 2D Object and vise versa
	 */
	
	
	public String getXmlFromComponentDrilDownQuery(Vector compIds, DashBoard bean, Object[][] obj,String ComponentID){
		
		
		
		String xmlStr="  <d1-Graph>	" +
					"<auth>" +
					"<user>" +"UserName"+
					"</user>" +
					"<password>" +"password"+
					"</password>" +
					"</auth>";
						
					for(int k=0; k < compIds.size(); k ++) {
						
						String query = (String.valueOf(obj[Integer.parseInt(String.valueOf(compIds.get(k)))][2])) ;
											
						query=query.replaceAll("<", "&lt;");
						query=query.replaceAll(">", "&gt;");
						
						xmlStr+="<component>" +
						"<componentid>" + obj[Integer.parseInt(String.valueOf(compIds.get(k)))][0] +//componentId+
						"</componentid>" +
						"<query>" + query + // to add the queryString
						"</query>"+
						"</component>"; 
						Object[][] configPara=getConfigurePara(bean);
						xmlStr=xmlStr+"<parameters>";
						String parameterString="";
						for(int i=0;i<configPara.length;i++){
							
							parameterString=parameterString+"<param><name>"+configPara[i][0]+"</name>" +
									"<value>"+configPara[i][1]+"</value></param>";
							}
						xmlStr=xmlStr+parameterString+
						"</parameters>";
						
					}
					xmlStr=xmlStr+" </d1-Graph> ";
		return xmlStr;
	}
	/**
	 * This function is used for returning 2D Object form giving xml String
	 * @param xmlString
	 * @return 2DObject
	 * @throws DocumentException
	 */
	public List getObjectFromXml(String xmlString) throws DocumentException{
		 ArrayList<Object[][]> listObj=new ArrayList<Object[][]>();
		try {
					String str=xmlString;
				//	SyndFeedInput input = new SyndFeedInput();
				//	SyndFeed feed = input.build(new XmlReader(str));
		SAXReader reader = new SAXReader();
		reader.setIgnoreComments(false);
		reader.setIncludeExternalDTDDeclarations(false);
		reader.setIncludeInternalDTDDeclarations(true);
		//str=StringEscapeUtils.escapeXml(str);
		// Get the xml document object by sax reader.
		//System.out.println("str.... after escapeXml"+str);
		Document document = reader.read(new StringReader(str));
		
	 // Define the xpath
		String xpathExpression = "//componentdata";
		// Get the list of nodes on given xPath
	    List<Node> nodes = document.selectNodes(xpathExpression);
	    // Read all the node inside xpath nodes and print the value of each
	  
	  //  List headerlist=new ArrayList();
		int coloumCount=0;
		int colCount=0;
		for (Node node : nodes) {//start 1st for loop
			List<Node> componentNodes = node.selectNodes("component");
	    	coloumCount=componentNodes.size();
	    	 
	    	for (Node headNode : componentNodes) {//start 2nd for loop
	    		Vector vectObj=new Vector();
			   List<Node> componentIdNodes = headNode.selectNodes("componentid");
		    	coloumCount=componentIdNodes.size();
		    	
		    	List<Node> tableNodes = headNode.selectNodes("table");
		    	for (Node tableNode : tableNodes) {//start 3rd for loop
		    		List<Node> tableHeaderNodes = tableNode.selectNodes("header");
						   
						    for (Node colNode : tableHeaderNodes) {//start 4th for loop
						    	List<Node> headNodes = colNode.selectNodes("head");
						    	coloumCount=headNodes.size();
						    	 colCount=0;
						    	for (Node headNode1 : headNodes) {//start 5th for loop
						    		  String headerValue =headNode1.getStringValue();
						    		  vectObj.add(headerValue);
						    		  colCount++;
						    	  }//end 5th for loop
						    	
						    }//end 4th for loop
						    
						    List<Node> tableRowNodes = tableNode.selectNodes("row");
						    int rowCount=0;
						    for (Node colsNode : tableRowNodes) {
								  /// System.out.println("---"+rowCount);
								   List<Node>  colNodes =  colsNode.selectNodes("col");
								   
								   for (Node colNode : colNodes) {
									   String value=" "+colNode.getStringValue();
									///   System.out.println("  value "+value );
									   vectObj.add(value);
									
								   }//end col for loop
							        rowCount++; 
							    }//end for 
		    	}//end 3rd for loop
		    	
		    	 Object[][] finalObject=Utility.to2DObject(vectObj, colCount);
				 listObj.add(finalObject);					
	    	 }//end 2nd for loop	    	
        }//end 1st for loop				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}				
		return listObj;
	}
	 public Object[][] getReportxmlParameter(String reportID){
		 Object[][] sheetinfo=getSqlModel().getSingleResult(getQuery(12),new Object[]{reportID});
		 return sheetinfo;
	 }
	 public Object[][] getComponentxmlParameter(String componentID){
		 Object[][] sheetinfo=(Object[][])null;
			 String query=" select 1, component_caption, 'A1', "+ 
					 " ccm.component_id,'G',ccm.component_query, "+
					 " null,ccm.graph_type, ccm.component_mode, ccm.component_caption "+ 
					 " from cr_component_master ccm where  ccm.component_id = "+componentID;
			 
			 sheetinfo=getSqlModel().getSingleResult(query);
		
		 return sheetinfo;
	 }
	 
	 
	 
	public  String getStringMultiComp(String componentId){
		 String mutiCompQuery="select drillDown_component_id from CR_COMPONENT_drilldown " +
		 		"where component_id="+componentId;
			Object[][] multiCompObj = getSqlModel().getSingleResult(mutiCompQuery);
			String multiComp="";
			if(multiCompObj!=null && multiCompObj.length>0){
				for(int l=0; multiCompObj.length>l; l++){
					for(int m=0;multiCompObj[0].length>m;m++){
						if(multiCompObj.length==1){
							multiComp=multiComp+String.valueOf(multiCompObj[l][m]);
						}
						else{
							multiComp=multiComp+String.valueOf(multiCompObj[l][m])+",";
						}
					}
				}
			}
		 return multiComp;
	 }


	public String getDashboardDetails(String dashboardID) {
		// TODO Auto-generated method stub
		Object[][] DashDetailObj=null;
		try{
			String query="select cd.dashboard_caption from cr_dashboard cd "+
						" where cd.dashboard_id= "+dashboardID;
			DashDetailObj=getSqlModel().getSingleResult(query);
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return String.valueOf(DashDetailObj[0][0]);
	}
	
	public String getUSGraphString(Object[][] GraphObject,String CurrentComponentID,String nextComponetID,String graphParameter){
	
		/*
		 * getting miniMax values from mssql database
		 */
		
		
		String minGreenValue = "";
		String maxGreenValue = "";
		String minYelloValue= "";
		String maxYelloValue= "";
		String minRedValue= "";
		String maxRedValue= "";
		try {
			minGreenValue = String.valueOf(GraphObject[1][6]);
			maxGreenValue = String.valueOf(GraphObject[1][7]);
			minYelloValue = String.valueOf(GraphObject[1][8]);
			maxYelloValue = String.valueOf(GraphObject[1][9]);
			minRedValue = String.valueOf(GraphObject[1][10]);
			maxRedValue = String.valueOf(GraphObject[1][11]);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String USString="<map borderColor='000000' connectorColor='000000' shownames='1' showBorder='0' fillAlpha='70' hoverColor='FFFFFF' showBevel='0'> " +
			"	<colorRange> 	" +
			"	<color minValue='"+minGreenValue+"' maxValue='"+maxGreenValue+"' displayValue='Closed Call "+minGreenValue+" - "+maxGreenValue+"' color='#95DB8A' /> " +
			"	<color minValue='"+minYelloValue+"' maxValue='"+maxYelloValue+"' displayValue='Closed Call "+minYelloValue+" - "+maxYelloValue+"' color='#DFE667' /> " +
			"   <color minValue='"+minRedValue+"' maxValue='"+maxRedValue+"' displayValue='Closed Call "+minRedValue+" - "+maxRedValue+"' color='#DB8A8E' /> " +
			"	</colorRange>  " +
			"	<data> 		" ;
			
		
		/*String USString="<map borderColor='000000' connectorColor='000000' shownames='1' showBorder='0' fillAlpha='70' hoverColor='FFFFFF' showBevel='0'> " +
		"	<colorRange> 	" +
		"	<color minValue='"+minimaxValueMap.get("minRed")+"' maxValue='"+minimaxValueMap.get("maxRed")+"' displayValue='Closed Call "+minimaxValueMap.get("minRed")+" - "+minimaxValueMap.get("maxRed")+"' color='#e5a47a' /> " +
		"	<color minValue='"+minimaxValueMap.get("minBlue")+"' maxValue='"+minimaxValueMap.get("maxBlue")+"' displayValue='Closed Call "+minimaxValueMap.get("minRed")+" - "+minimaxValueMap.get("maxRed")+"' color='#a9c45b' /> " +
		"   <color minValue='"+minimaxValueMap.get("minWhite")+"' maxValue='"+minimaxValueMap.get("maxWhite")+"' displayValue='Closed Call "+minimaxValueMap.get("minWhite")+" - "+minimaxValueMap.get("maxWhite")+"' color='#d5a922' /> " +
		"	</colorRange>  " +
		"	<data> 		" ;*/
			for(int i=0;i<GraphObject.length;i++ ){
				if(i!=0){
				USString+="<entity id='"+String.valueOf(GraphObject[i][0]).trim()+
				"' value='" +
				""+String.valueOf(GraphObject[i][1]).trim()+
			//	"' link='JavaScript:getMap()'/>";
				"' link=\'JavaScript:getGraph(\""
				+ CurrentComponentID
				+ "\",\""
				+ nextComponetID
				+ "\",\""
				+ graphParameter
				+ "\",\""
				+ String.valueOf(GraphObject[i][0]).trim()
				+ "\",\""+0+"\")\'/> ";
				}
			}
			
	USString+=	
			"	</data> 	" +
			"	<styles> 	" +
			"	<definition> 		" +
			"	<style type='animation' name='animX' param='_xscale' start='0' duration='1' /> 	" +
			"	<style type='animation' name='animY' param='_yscale' start='0' duration='1' /> 	" +
			"	</definition>	 " +
			"		<application> 		" +
			"	<apply toObject='PLOT' styles='animX,animY' /> 	" +
			"	</application> 	</styles> </map>";
	System.out.println("us Data String  = = "+USString);
	return USString;
	}


	private HashMap getPerformaceMinMaxValues() {
		
		return null;
	}


	public void getSenerioList(DashBoard dashBean) {
		Object senerioObj[][]=getSqlModel().getSingleResult(getQuery(13),new Object[]{dashBean.getDashBoardID()});
		LinkedHashMap<String, String > SenerioHashMap=new LinkedHashMap<String, String>();
		for (int i = 0; i < senerioObj.length; i++) {
			SenerioHashMap.put(String.valueOf(senerioObj[i][0]), String.valueOf(senerioObj[i][1]));
		}
		dashBean.setSenerioMap(SenerioHashMap);
	}


	public void getscenerioInfo(String scenerioId, DashBoard dashBean) {
		Object[] param=new Object[4];
		
		param[0]=scenerioId;
		param[1]=dashBean.getDashBoardID();
		param[2]=dashBean.getUserID();
		if(String.valueOf(dashBean.getUserType()).equals("CRM")){
			param[3]="I";  // Internal peoplepower user
		} 
		else if(String.valueOf(dashBean.getUserType()).equals("HRM")){
			param[3]="P";  // Internal peoplepower user
		}
		else{
			param[3]="E";  // external peoplepower user -- client table
		}
		
		
		Object[][] scenerioObj=getSqlModel().getSingleResult(getQuery(14),param);
		
		if(null!=scenerioObj && scenerioObj.length>0){
		dashBean.setScenarioId(String.valueOf(scenerioObj[0][0]));
		dashBean.setScenario_name(String.valueOf(scenerioObj[0][1]));
		dashBean.setCompoNo(String.valueOf(scenerioObj[0][2]));
		dashBean.setComponentName(String.valueOf(scenerioObj[0][3]));
		dashBean.setDataEnable(String.valueOf(scenerioObj[0][4]));
		dashBean.setGraphEnable(String.valueOf(scenerioObj[0][5]));
		dashBean.setGraphType(String.valueOf(scenerioObj[0][6]));
		//setX and  setY value
		dashBean.setDashAutoId(String.valueOf(scenerioObj[0][9]));
		
		}
	}


	
	
	
	
}
