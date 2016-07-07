/**
 * 
 */
package org.struts.action.vas;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Vector;

import org.paradyne.bean.vas.VASHome;
import org.paradyne.model.vas.VASHomeModel;
import org.paradyne.model.vas.VASServiceLoginModel;
import org.struts.lib.ParaActionSupport;

/**
 * @author Lakkichand
 *
 */
public class VASHomeAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	
	VASHome vasHome;
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		vasHome=new VASHome();
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return vasHome;
	}
	public String input() throws Exception{
		
		
		ResourceBundle bundle = ResourceBundle.getBundle("org.paradyne.lib.ConnectionModel");
		/*Enumeration list = bundle.getKeys();
		int count = 0;
		Vector vect = new Vector();
		while (list.hasMoreElements()) {
			// System.out.println("Counter-----"+count+"-------"+list.nextElement());
			String client = bundle.getString((String) list.nextElement());
			vect.add(client);
		}
		System.out.println("vect.size()--------------" + vect.size());*/
		String loginPool=(String) session.getAttribute("session_pool");
		
			System.out.println("client--------------" + bundle.getString(loginPool));
			VASHomeModel model =new VASHomeModel();
			model.initiate(context, session);
			String clientQuery="SELECT CLIENT_CODE FROM HRSAAS_SHARED.VAS_CLIENT_MASTER WHERE CLIENT_POOL='"+bundle.getString(loginPool)+"'";
			Object[][]cData=model.getSqlModel().getSingleResult(clientQuery);
			if(cData !=null && cData.length>0){
				String clientCode=String.valueOf(cData[0][0]);
				vasHome.setClientCode(clientCode);
			String headerQuery="SELECT VAS_SERVICES.SERVICE_CODE,SERVICE_LOGO FROM HRSAAS_SHARED.VAS_SERVICES" +
								" INNER JOIN HRSAAS_SHARED.VAS_CLIENT_PROFILE ON(VAS_CLIENT_PROFILE.SERVICE_CODE=VAS_SERVICES .SERVICE_CODE AND CLIENT_CODE="+clientCode+" AND ISACCESSIBLE='Y') "+
								" WHERE SERVICE_PARENT_CODE=0  ORDER BY VAS_SERVICES.SERVICE_CODE ASC";
			Object[][]headerData=model.getSqlModel().getSingleResult(headerQuery);
			if(headerData !=null && headerData.length>0){				
				int totalRow=0;
				int noOfColumn=4;
				int sub=headerData.length%noOfColumn>0?1:0;
				totalRow=headerData.length<=noOfColumn?1:(headerData.length/noOfColumn+(sub));
				request.setAttribute("totalRow", totalRow);
				request.setAttribute("headerData", headerData);
				
				String query="SELECT HRSAAS_SHARED.VAS_SERVICES.SERVICE_CODE, SERVICE_NAME, SERVICE_PARENT_CODE, SERVICE_COMMENTS, SERVICE_LOGO, " 
							+"	SERVICE_LINK, SERVICE_TOOLTIP, SERVICE_SINGLE_SIGN_ISAVAILABL " 
							+"	FROM HRSAAS_SHARED.VAS_SERVICES " +
							" INNER JOIN HRSAAS_SHARED.VAS_CLIENT_PROFILE ON(VAS_CLIENT_PROFILE.SERVICE_CODE=VAS_SERVICES .SERVICE_CODE AND CLIENT_CODE="+clientCode+")  "+
							"WHERE SERVICE_PARENT_CODE IN( "
							+"	SELECT SERVICE_CODE FROM HRSAAS_SHARED.VAS_SERVICES WHERE SERVICE_PARENT_CODE=0) AND ISACCESSIBLE='Y'"
							+"	ORDER BY VAS_SERVICES.SERVICE_CODE ASC";
				Object[][]dtlData=model.getSqlModel().getSingleResult(query);
				request.setAttribute("dtlData", dtlData);
			}
	}
			model.terminate();		
		return SUCCESS;
		
	}

	
	public void saveClientLogs() throws Exception{
		VASHomeModel model=new VASHomeModel();
		model.initiate(context, session);
		String serviceCode=vasHome.getServiceCode();//request.getParameter("serviceCode");
		String clientCode=vasHome.getClientCode();
		String employeeCode=vasHome.getUserEmpId();
		System.out.println("service code :   "+serviceCode);
		System.out.println("client code :   "+clientCode);
		System.out.println("employee code :   "+employeeCode);
		model.saveClientLogs(serviceCode,clientCode,employeeCode);
		model.terminate();		
	}
	
	
}
