package org.paradyne.model.event;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import org.paradyne.bean.event.CeoConfiguration;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class CeoConfigurationModel extends ModelBase {

	public void addEmployee(String[] srNo, String[] employeeId,
			String[] employeeName, String[] employeeToken,
			CeoConfiguration bean, String[] ceoType, String[] ceoTypeValueItt) {	
		try {
			LinkedList<CeoConfiguration> list = new LinkedList<CeoConfiguration>();
			if (srNo != null && srNo.length > 0) {
				for (int i = 0; i < srNo.length; i++) {
					CeoConfiguration innerbean = new CeoConfiguration();
					innerbean.setEmpCodeItt(employeeId[i]);
					innerbean.setEmpNameItt(employeeName[i]);
					innerbean.setEmpTokenItt(employeeToken[i]);
					innerbean.setCeoTypeItt(ceoType[i]);
					if (innerbean.getCeoTypeItt().equals("C")) {
						bean.setShowCeo("false");
					}
					innerbean.setCeoTypeValueItt(ceoTypeValueItt[i]);
					list.add(innerbean);
				}
			}
			CeoConfiguration localbean = new CeoConfiguration();
			localbean.setEmpCodeItt(bean.getEmpCode().trim());
			localbean.setEmpNameItt(bean.getEmpName().trim());
			localbean.setEmpTokenItt(bean.getEmpToken().trim());
			localbean.setCeoTypeItt(bean.getCeoType().trim());
			String str = "";
			if (localbean.getCeoTypeItt().equals("M")) {
				str = "Message Administrator";
			} else {
				str = "CEO";
				bean.setShowCeo("false");
			}
			localbean.setCeoTypeValueItt(str);
			list.add(localbean);
			//bean.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean displayList(CeoConfiguration bean) {
		boolean flag = false;
		String code="";
		String getName="";
		String query = " SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
				+ " HRMS_CEO_CONFIG .EMP_ID,"
				+ " DECODE(ISCEOORMSGADMIN,'M','Message Administrator','C','CEO'),CEO_DIVISION "
				+ " FROM HRMS_CEO_CONFIG "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID =HRMS_CEO_CONFIG.EMP_ID)";

		Object selectDataObj[][] = getSqlModel().getSingleResult(query);

		if (selectDataObj != null && selectDataObj.length > 0) {
			LinkedHashSet<CeoConfiguration> list = new LinkedHashSet<CeoConfiguration>();

			for (int i = 0; i < selectDataObj.length; i++) {
				CeoConfiguration innerbean = new CeoConfiguration();
				innerbean.setEmpTokenItt(String.valueOf(selectDataObj[i][0]));
				innerbean.setEmpNameItt(String.valueOf(selectDataObj[i][1]));
				innerbean.setEmpCodeItt(String.valueOf(selectDataObj[i][2]));
				innerbean.setCeoTypeItt(String.valueOf(selectDataObj[i][3]));				
				if (innerbean.getCeoTypeItt().equals("CEO")) {
					bean.setShowCeo("false");
				}
				else{
					bean.setShowCeo("true");
				}
				innerbean.setCeoDivCode(String.valueOf(selectDataObj[i][4]));
				if(!(selectDataObj[i][4]==null || String.valueOf(selectDataObj[i][4]).equals("") || String.valueOf(selectDataObj[i][4]).equals("null"))){
					String divQuery="SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID IN("+String.valueOf(selectDataObj[i][4])+")";
					Object [][]obj= getSqlModel().getSingleResult(divQuery);
					code= String.valueOf(selectDataObj[i][4]);
					getName=Utility.getNameByKey(obj, code);						
					innerbean.setCeoDivName(getName);
				}				
				innerbean.setCeoTypeValueItt(String
						.valueOf(selectDataObj[i][3]));
				list.add(innerbean);
				flag = true;
			}
			bean.setList(list);
		}
		else{
			bean.setList(null);
		}
		return flag;
	}

	public boolean addEmployee(CeoConfiguration bean) {
		boolean result = false;
		Object [][] addObj= new Object[1][3];
		addObj[0][0]=bean.getEmpCode().trim();
		addObj[0][1]= bean.getCeoType().trim();
		addObj[0][2]=bean.getCeoDivCode();
		try {
			String insertQuery = " INSERT INTO HRMS_CEO_CONFIG(EMP_ID,ISCEOORMSGADMIN,CEO_DIVISION)"
								+ " VALUES(?,?,?)";
			result = getSqlModel().singleExecute(insertQuery,addObj);
			if (bean.getCeoTypeItt().equals("C")) {
				bean.setShowCeo("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean delete(CeoConfiguration bean) {

		boolean result = false;
		try {
			String delQuery = " DELETE FROM HRMS_CEO_CONFIG WHERE EMP_ID="
					+ bean.getHiddenDelete();
			result = getSqlModel().singleExecute(delQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
