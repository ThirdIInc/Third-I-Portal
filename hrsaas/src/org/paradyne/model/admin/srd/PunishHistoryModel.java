package org.paradyne.model.admin.srd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.LMS.RulesAndPolicyMasterBean;
import org.paradyne.bean.admin.srd.EmployeeCheckList;
import org.paradyne.bean.admin.srd.ProfReferences;
import org.paradyne.bean.admin.srd.PunishHistory;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;

/**
 * @modified by priyanka kumbhar
 * 
 * 
 */
public class PunishHistoryModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PunishHistoryModel.class);
	
	AuditTrail trial = null;
	
	/**
	 * following function is called to delete the row from the list.
	 * @param bean
	 * @param susId
	 * @param susType
	 * @param auth
	 * @param effDate
	 * @param period
	 * @param doc
	 * @param status
	 * @param desc
	 */
public boolean delPunishment(PunishHistory bean){
		Object addObj[][] = new Object[1][1];		
		addObj[0][0] = bean.getParacode();
		boolean result= getSqlModel().singleExecute(getQuery(5), addObj);
		if(result){
			return true;
		}
		else{
			return false;
		}	
	}	

	/**
	 * Method to save the punishment details of an employee.
	 * 
	 * @param punishHistory
	 * @return String
	 */
public String savePunish(PunishHistory punishHistory,String[] doc) {
	
	
	Object addobj[][] = new Object[1][9];
	addobj[0][0] = punishHistory.getEmpId();	
	addobj[0][1] = punishHistory.getDispActionId();
	addobj[0][2] = punishHistory.getAuthority();
	addobj[0][3] = punishHistory.getEffFromDate();
	addobj[0][4] = punishHistory.getPeriod();	
    String strValue="";
	
	if(doc!=null && doc.length>0){
		for (int i = 0; i < doc.length; i++) {
			strValue+=""+doc[i]+",";
		}
	}
	System.out.println("strValue--------->"+strValue);
	addobj[0][5] = strValue;
	
	if(punishHistory.getPunishStatus().equals("A")){
		addobj[0][6]="A";
	}else{
		addobj[0][6]= "I";
	}
	addobj[0][7] = punishHistory.getDescription();	
	
	addobj[0][8] = punishHistory.getEffToDate();
	
	boolean res= getSqlModel().singleExecute(getQuery(1), addobj);
	
	if(res){
		return "Record Saved Successfully";
	}
	else
	{
		return "Record Cannot Be Saved";
	}
}

public void getPunishDet(String empId,PunishHistory punishHistory){
		

		try{
		Object[] empcode = new Object[1];
		empcode[0] = punishHistory.getEmpId(); 
		Object[][] data = getSqlModel().getSingleResult(getQuery(2), empcode);
		ArrayList<Object> list1 = new ArrayList<Object>();
		if (data.length > 0 && data!=null) {
			punishHistory.setItrFlag(true);
			for (int i = 0; i < data.length; i++) {
				PunishHistory bean = new PunishHistory();
				bean.setHSuspensionType(String.valueOf(data[i][0]));
				bean.setHAuth(String.valueOf(data[i][1]));
				bean.setHPeriod(checkNull(String.valueOf(data[i][6])));
				bean.setHEffDate(checkNull(String.valueOf(data[i][3])));
				String result=String.valueOf(data[i][11]);
				String[] abc=result.split(",");
				String value="";
				for (int j = 0; j < abc.length; j++) {
					value+="\n"+abc[j];
					System.out.println("abc["+ j+"]----------------->"+abc[j]);
				}
				
				
				String temp=value.trim();
				 
				bean.setHView(value);
									
				String[] splitDoc = temp.split("\n");
				ArrayList<PunishHistory> proofList = new ArrayList<PunishHistory>();
				if (splitDoc[0] != null && !splitDoc[0].equals("")) {
					
					for (int j = 0; j < splitDoc.length; j++) {
						PunishHistory beanList = new PunishHistory();
						beanList.setHView(splitDoc[j]); 
						proofList.add(beanList);
					}
					bean.setImageList(proofList);
				} else {
					bean.setImageList(proofList);
				}
		
				if(String.valueOf(data[i][12]).equals("A")){
					bean.setStatus("Active");
				}else{
					bean.setStatus("In Active");
				}
				bean.setListpunishid(String.valueOf(data[i][8]));
				System.out.println("........------>>"+String.valueOf(data[i][8]));

				bean.setHSuspensionId(String.valueOf(data[i][13]));
				bean.setHEffToDate(checkNull(String.valueOf(data[i][14])));
				bean.setHDesc(checkNull(String.valueOf(data[i][9])));
				bean.setListOffncDesc(checkNull(String.valueOf(data[i][15])));
				if(bean.getListOffncDesc().length() > 10){
					bean.setAbbrPunishHistory(bean.getListOffncDesc().substring(0, 9)+"....");
				}else{
					bean.setAbbrPunishHistory(bean.getListOffncDesc());
				}
				
				list1.add(bean);
			}// end of for loop
			punishHistory.setPunishList(list1);
			punishHistory.setNoData("false");
		}
		else
		{
			punishHistory.setNoData("true");
			list1.add(null);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Method to display the records for selected employee.
	 * 
	 * @param punishHistory
	 */
	
	public void getPunishDetails(PunishHistory punishHistory) {
		try{
		Object[] empcode = new Object[1];
		empcode[0] = punishHistory.getEmpId();
		Object[][] data = getSqlModel().getSingleResult(getQuery(2), empcode);
		ArrayList<Object> list1 = new ArrayList<Object>();
		if (data.length > 0 && data!=null) {
			for (int i = 0; i < data.length; i++) {
				PunishHistory bean = new PunishHistory();
				bean.setHSuspensionType(String.valueOf(data[i][0]));
				bean.setHAuth(String.valueOf(data[i][1]));
				bean.setHPeriod(checkNull(String.valueOf(data[i][6])));
				bean.setHEffDate(checkNull(String.valueOf(data[i][3])));
				bean.setListpunishid(checkNull(String.valueOf(data[i][7])));
				bean.setPunishId(checkNull(String.valueOf(data[i][8])));
				String result=String.valueOf(data[i][11]);
				String[] abc=result.split(",");
				String value="";
				for (int j = 0; j < abc.length; j++) {
					value+="\n" +abc[j];
					System.out.println("abc["+ j+"]----------------->"+abc[j]);
				}
				String temp=value.trim();
				 
				bean.setHView(value);
									
				String[] splitDoc = temp.split("\n");
				ArrayList<PunishHistory> proofList = new ArrayList<PunishHistory>();
				if (splitDoc[0] != null && !splitDoc[0].equals("")) {
					
					for (int j = 0; j < splitDoc.length; j++) {
						PunishHistory beanList = new PunishHistory();
						beanList.setHView(splitDoc[j]); 
						proofList.add(beanList);
					}
					bean.setImageList(proofList);
				} else {
					bean.setImageList(proofList);
				}
				
				if(String.valueOf(data[i][12]).equals("A")){
					bean.setStatus("Active");
				}else{
					bean.setStatus("In Active");
				}
			
				bean.setHSuspensionId(String.valueOf(data[i][13]));
				bean.setHEffToDate(checkNull(String.valueOf(data[i][14])));
				bean.setHDesc(checkNull(String.valueOf(data[i][9])));
				bean.setListOffncDesc(checkNull(String.valueOf(data[i][15])));
				
				if(bean.getListOffncDesc().length() > 10){
					bean.setAbbrPunishHistory(bean.getListOffncDesc().substring(0, 9)+".....");
				}else{
					bean.setAbbrPunishHistory(bean.getListOffncDesc());
				}
				list1.add(bean);
			}// end of for loop
			punishHistory.setNoData("false");
			punishHistory.setPunishList(list1);
		}else
		{
			punishHistory.setNoData("true");
			punishHistory.setPunishList(null);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Method to select the particular record which is going to be modify.
	 * 
	 * @param punishHistory
	 */
	public void getOneRecord(PunishHistory punishHistory) {

		Object[] punishId = new Object[1];
		punishId[0] = punishHistory.getParacode();
		Object[][] data = getSqlModel().getSingleResult(getQuery(3), punishId);
		punishHistory.setDispAction(checkNull(String.valueOf(data[0][0])));
		punishHistory.setAuthority(checkNull(String.valueOf(data[0][1])));
		punishHistory.setLetterNo(checkNull(String.valueOf(data[0][2])));
		punishHistory.setCeoOrder(checkNull(String.valueOf(data[0][3])));
		punishHistory.setPeriod(checkNull(String.valueOf(data[0][4])));
		punishHistory.setEffFromDate(checkNull(String.valueOf(data[0][5])));
		punishHistory.setEmpId(checkNull(String.valueOf(data[0][6])));
		punishHistory.setDispActionId(checkNull(String.valueOf(data[0][7])));
		if (String.valueOf(data[0][8]).equals("null") || (data[0][8] == null)
				|| (data[0][8].equals(""))) {
			punishHistory.setDescription("");
		}// end of if
		else {
			punishHistory.setDescription(String.valueOf(data[0][8]));
		}// end of else
		punishHistory.setSerialNo(String.valueOf(data[0][9]));
		punishHistory.setUploadFileNameTxt(checkNull(String.valueOf(data[0][10])));
		punishHistory.setEffToDate(checkNull(String.valueOf(data[0][11])));
		punishHistory.setPunishStatus(checkNull(String.valueOf(data[0][12])));
		punishHistory.setPunishId(punishHistory.getParacode());

	}

	/**
	 * Method to replace the null with a space.
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {

		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/**
	 * Method to modify the particular record of an employee.
	 * 
	 * @param punishHistory
	 * @return String
	 */

	
public String modPunish(PunishHistory punishHistory,String[] doc, HttpServletRequest request) {
	String str="";
	try{	
		Object [][] modObj = new Object[1][10];// to get  the data for updating record
		modObj[0][0] = punishHistory.getDispActionId();
		modObj[0][1] = punishHistory.getAuthority();
		modObj[0][2] = punishHistory.getEffFromDate();
		modObj[0][3] = punishHistory.getPeriod();
		modObj[0][4] = punishHistory.getDescription();
		String strValue="";
		if(doc!=null && doc.length>0){
			for (int i = 0; i < doc.length; i++) {
				strValue+=""+doc[i]+",";
			}
		}
		System.out.println("strValue--------->"+strValue);
		modObj[0][5] = strValue;
		if(punishHistory.getPunishStatus().equals("A")){
			modObj[0][6]="A";
		}else{
			modObj[0][6]= "I";
		}
		modObj[0][7] = punishHistory.getEffToDate();
		
		modObj[0][8]=punishHistory.getEmpId();
		modObj[0][9] = punishHistory.getParacode();	
		
		boolean result = getSqlModel().singleExecute(getQuery(4), modObj);
		if(result)
		{
	     str="Record Updated Successfully";
		}
		else
		{
	     str="Record cannot be Updated";
		}
		
	}catch(Exception e){
		logger.error("Exception in modAwdDtl-----------" + e);
	}
	return str;

	}

	/**
	 * Method to delete the particular record of an employee.
	 * 
	 * @param bean
	 * @return String
	 */

	public boolean chkEmp(PunishHistory punishHistory){
		String query="SELECT EMP_ID FROM HRMS_PUNISH WHERE EMP_ID="+punishHistory.getEmpId();
		 Object [][]data=getSqlModel().getSingleResult(query);
			
		 if(data!=null && data.length>0){
			return true;
		 }else
		 {
			return false;  
		 }
		
		
		
	}

	
	public void displayIteratorValueForUploadProof(String[] srNo,
			String[] proofName, PunishHistory punishHistory) {
		try {
			ArrayList<PunishHistory> proofList = new ArrayList<PunishHistory>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					PunishHistory beanList = new PunishHistory();
					beanList.setProofName(proofName[i]);
					// tmsclaimapp.setProofFileName(proofFileName[i]);
					proofList.add(beanList);
				}
				punishHistory.setProofList(proofList);
			}
		} catch (Exception e) {
			logger
					.error("Exception in displayIteratorValueForUploadProof------"
							+ e);
		}

		
	}

	
	public void setProofList(String[] srNo, String[] proofName,
			PunishHistory punishHistory) {
		try {
			PunishHistory beanProof = new PunishHistory();
			beanProof.setProofName(punishHistory.getUploadLocFileName());

			ArrayList<PunishHistory> proofList = displayNewValueProofList(
					srNo, proofName, punishHistory);
			beanProof.setProofSrNo(String.valueOf(proofList.size() + 1));// sr no
			proofList.add(beanProof);
			punishHistory.setProofList(proofList);
		} catch (Exception e) {
			logger.error("Exception in setProofList------" + e);
		}
		
	}
	
	private ArrayList<PunishHistory> displayNewValueProofList(
			String[] srNo, String[] proofName, PunishHistory punishHistory) {
		ArrayList<PunishHistory> proofList = null;
		try {
			proofList = new ArrayList<PunishHistory>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					PunishHistory claimApp = new PunishHistory();
					claimApp.setProofName(proofName[i]);
					claimApp.setProofSrNo(srNo[i]);
					proofList.add(claimApp);
				}

			}
		} catch (Exception e) {
			logger.error("Exception in displayNewValueProofList------" + e);
		}
		return proofList;
	}

	public void getImage(PunishHistory punishHistory) {
	
		      try {
		    	  String query="select EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="+punishHistory.getEmpId();
		  		
		  		Object[][] myPics = getSqlModel().getSingleResult(query);
		  			
		  		punishHistory.setProfileUploadFileName( String.valueOf(myPics[0][0]));	
		  		punishHistory.setProfileEmpName( String.valueOf(myPics[0][1])+" "+String.valueOf(myPics[0][2])+" "+String.valueOf(myPics[0][3]));
		  		
		  		System.out.println("persDetail.setUploadFileName( String.valueOf(myPics[0][0]))----"+punishHistory.getProfileUploadFileName());
		  			
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		
	}

}
