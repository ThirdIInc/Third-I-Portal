/**
 * 
 */
package org.paradyne.model.settings;

import java.util.ArrayList;

import org.paradyne.bean.settings.Services;
import org.paradyne.lib.ModelBase;

import freemarker.log.Logger;

/**
 * @author prakashs
 *
 */
public class ServicesModel extends ModelBase {
	Logger logger;
	public boolean addList(Services bean){
		System.out.println("inside the save model");
		boolean result=false;
		Object [][]saveObj=new Object[1][1];
		saveObj[0][0]=bean.getSubheadName().trim();
		System.out.println("before inserting");
	result=getSqlModel().singleExecute(getQuery(1), saveObj);
	if(result){
		System.out.println("inside if");
		String query="SELECT NVL(MAX(SERVICES_HDR_CODE),0) FROM HRMS_SERVICES_HDR";
		Object code[][]=getSqlModel().getSingleResult(query);
		System.out.println("code=="+code[0][0]);
		bean.setServiceCode(String.valueOf(code[0][0]));
		Object [][]dtlObj=new Object[1][4];
		dtlObj[0][0]=bean.getServiceCode();
		dtlObj[0][1]=bean.getServiceName().trim();
		dtlObj[0][2]=bean.getServiceLink().trim();
		dtlObj[0][3]="1";
		result=getSqlModel().singleExecute(getQuery(2), dtlObj);
	}
		return result;
	}
	public boolean modList(Services bean){
		boolean result=false;
		Object [][]modObj=new Object[1][2];
		modObj[0][0]=bean.getSubheadName();
		modObj[0][1]=bean.getServiceCode();
		result=getSqlModel().singleExecute(getQuery(3), modObj);
		if(result && bean.getServiceLinkcode().equals("")){
			String dtlCode="SELECT NVL(MAX(SERVICES_DTL_CODE),0)+1 FROM HRMS_SERVICES_DTL WHERE SERVICES_HDR_CODE ="+bean.getServiceCode();
			Object[][] maxDtl=getSqlModel().getSingleResult(dtlCode);
			Object[][] dtlObj=new Object[1][4];
			dtlObj[0][0]=bean.getServiceCode();
			dtlObj[0][1]=bean.getServiceName().trim();
			dtlObj[0][2]=bean.getServiceLink().trim();
			dtlObj[0][3]=maxDtl[0][0];
			result=getSqlModel().singleExecute(getQuery(2), dtlObj);
		}else{
			Object [][]dtlObj=new Object[1][4];
			dtlObj[0][0]=bean.getServiceName().trim();
			dtlObj[0][1]=bean.getServiceLink().trim();
			dtlObj[0][2]=bean.getServiceLinkcode();
			dtlObj[0][3]=bean.getServiceCode();
			result=getSqlModel().singleExecute(getQuery(6), dtlObj);
		}
		return result;
	}
	public void setList(Services bean){
		System.out.println("inside setList");
		ArrayList<Object> list =new ArrayList<Object>();
		Services bean1=new Services();
		bean1.setServiceName(bean.getServiceName());
		bean1.setServiceLink(bean.getServiceLink());
		list.add(bean1);
		bean.setList(list);
		System.out.println("list.size===="+list.size());
	}
	public void showDetails(Services bean){
		System.out.println("Inside the Show model");
		String query="SELECT SERVICES_DTL_NAME,SERVICES_DTL_PATH,SERVICES_DTL_CODE FROM HRMS_SERVICES_DTL WHERE SERVICES_HDR_CODE ="+bean.getServiceCode();
		Object[][] details=getSqlModel().getSingleResult(query);
		ArrayList<Object> linkList =new ArrayList<Object>();
		
		if (details !=null) {
			for (int i = 0; i < details.length; i++) {
				Services bean1 = new Services();
				bean1.setServiceName(String.valueOf(details[i][0]));
				bean1.setServiceLink(String.valueOf(details[i][1]));
				bean1.setServiceLinkCode(String.valueOf(details[i][2]));
				bean1.setSrNo(String.valueOf(i + 1));
				System.out.println("list.size====---------------------"
						+ bean1.getServiceLinkCode());
				linkList.add(bean1);
			}
		}
		bean.setList(linkList);
		/*bean.setChkfield("saved");*/

	}
	public void edit(Services bean){
		String query="SELECT SERVICES_DTL_NAME,SERVICES_DTL_PATH,SERVICES_DTL_CODE FROM HRMS_SERVICES_DTL WHERE SERVICES_HDR_CODE ="+bean.getServiceCode()+
		" AND SERVICES_DTL_CODE= "+bean.getServiceLinkcode();
		Object[][]editData=getSqlModel().getSingleResult(query);
		bean.setServiceName(String.valueOf(editData[0][0]));
		bean.setServiceLink(String.valueOf(editData[0][1]));
		bean.setServiceLinkcode(String.valueOf(editData[0][2]));
	}
	public boolean deleteDtl(Services bean){
		System.out.println("inside deleteDtl model");
		String queryDtl="delete from hrms_services_dtl WHERE SERVICES_HDR_CODE ="+bean.getServiceCode()+
		" AND SERVICES_DTL_CODE= "+bean.getServiceLinkcode();
		return getSqlModel().singleExecute(queryDtl);
	}
	public boolean delete(Services bean){
		boolean result=false;
		Object[][]delObj=new Object[1][1];
		delObj[0][0]=bean.getServiceCode();
		result= getSqlModel().singleExecute(getQuery(7),delObj);
		if(result)
			return getSqlModel().singleExecute(getQuery(8),delObj);
		else return result;
	}
	public boolean save(Services bean){
	Object [][] data=new Object[1][1];
	data[0][0]=bean.getSubheadName();
	
	return getSqlModel().singleExecute(getQuery(1), data);
	}
	public boolean mod(Services bean){
		Object [][] data=new Object[1][2];
		data[0][0]=bean.getSubheadName();
		data[0][1]=bean.getServiceCode();
		return getSqlModel().singleExecute(getQuery(3), data);
		}
	
}
