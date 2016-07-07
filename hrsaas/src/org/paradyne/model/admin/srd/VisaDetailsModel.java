package org.paradyne.model.admin.srd;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.admin.srd.AwardDetails;
import org.paradyne.bean.admin.srd.VisaDetailsBean;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;

public class VisaDetailsModel extends ModelBase {

	AuditTrail trial = null;

	/**
	 * Method to add personal details of the employee
	 * @param bean
	 * @param request
	 * @param address
	 * @param validUpto
	 * @param issueDate
	 * @param issueAuthority
	 * @param issuePlace
	 * @param visaEntry
	 * @param visaType
	 * @param country
	 * @param visaNumber
	 * @return
	 */

	/** To set blank for null value of any field. 
	 * @param result
	 * @return*/
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/** Method to check PersonalDetails of an employee whether it is saved ornot. 
	 * @param bean
	 * @return boolean*/
	public boolean checkID(VisaDetailsBean bean) {
		boolean flag;
		Object addObj[] = new Object[1];
		addObj[0] = bean.getEmpId();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);
		if (data.length == 0) {
			flag = false;
		}// end of if
		else {
			flag = true;
		}// end of else
		return flag;
	}

	public boolean deleteVisaRecord(VisaDetailsBean visaBean) 
	{
		Object addObj[][] = new Object[1][1];		
		addObj[0][0] = visaBean.getParaId();
		boolean result= getSqlModel().singleExecute(getQuery(3), addObj);
		if(result){
			return true;
		}
		else{
			return false;
		}
	}
	
	public VisaDetailsBean getPassportRecord(VisaDetailsBean bean) {
		Object[] addObj = new Object[1];
		addObj[0] = bean.getEmpId();
		Object[][] data = getSqlModel().getSingleResult(getQuery(5), addObj);

		if (data != null && data.length > 0 && !String.valueOf(data[0][0]).equals(null) && !String.valueOf(data[0][0]).equals("null")) {
			// for Passport details
			bean.setPassport(checkNull(String.valueOf(data[0][0])));
			bean.setPassportName(checkNull(String.valueOf(data[0][1])));
			bean.setFatherName(checkNull(String.valueOf(data[0][2])));
			bean.setMotherName(checkNull(String.valueOf(data[0][3])));
			bean.setPassportIssueFrom(checkNull(String.valueOf(data[0][4])));
			bean.setPassportDateOfIssue(checkNull(String.valueOf(data[0][5])));
			bean.setPassportExpDate(checkNull(String.valueOf(data[0][6])));
			bean.setPassportUidNum(checkNull(String.valueOf(data[0][7])));
		}
		else
		{
			bean.setPassport("");
			bean.setPassportName("");
			bean.setFatherName("");
			bean.setMotherName("");
			bean.setPassportIssueFrom("");
			bean.setPassportDateOfIssue("");
			bean.setPassportExpDate("");
			bean.setPassportUidNum("");
		}
		return bean;
	}

	
	public void getVisaRecord(VisaDetailsBean vBean) {
		
		Object[] addObj = new Object[1];
		addObj[0] = vBean.getEmpId();
		Object[][] data = getSqlModel().getSingleResult(getQuery(6), addObj);
		ArrayList<Object> list = new ArrayList<Object>();
		if (data != null && data.length > 0) {	
			for(int i=0;i<data.length;i++){	
				VisaDetailsBean bean = new VisaDetailsBean();
				bean.setVisaNumberItt(checkNull(String.valueOf(data[i][1])));
				bean.setCountryItt(checkNull(String.valueOf(data[i][2])));
				if(bean.getCountryItt().length() > 5){
					bean.setAbbrCountry(bean.getCountryItt().substring(0, 4)+"...");
				}else{
					bean.setAbbrCountry(bean.getCountryItt());
				}
				bean.setVisaTypeItt(checkNull(String.valueOf(data[i][3])));
				bean.setVisaEntryItt(checkNull(String.valueOf(data[i][4])));
				bean.setIssuePlaceItt(checkNull(String.valueOf(data[i][5])));
				if(bean.getIssuePlaceItt().length() > 5){
					bean.setAbbrIssuePlace(bean.getIssuePlaceItt().substring(0, 4)+"...");
				}else{
					bean.setAbbrIssuePlace(bean.getIssuePlaceItt());
				}
				bean.setIssueAuthorityItt(checkNull(String.valueOf(data[i][6])));
				bean.setIssueDateItt(checkNull(String.valueOf(data[i][7])));
				bean.setValidUptoItt(checkNull(String.valueOf(data[i][8])));
				bean.setAddressItt(checkNull(String.valueOf(data[i][9])));
				if(bean.getAddressItt().length()>5)
				{
					bean.setAbbrAddress(bean.getAddressItt().substring(0,4)+"...");
				}
				else
				{
					bean.setAbbrAddress(bean.getAddressItt());
				}
				bean.setVisaCode(checkNull(String.valueOf(data[i][10])));
				list.add(bean);				
	      }
			vBean.setVisaDetailsList(list);
			vBean.setNoData("false");
			
	  }
		else
		{
			vBean.setVisaDetailsList(null);
			vBean.setNoData("true");
		}
  }
	public void getVisaSingleRecord(VisaDetailsBean bean)
	{
		try
		{
		Object[] addObj=new Object[2];
		addObj[0]=bean.getEmpId();
		addObj[1]=bean.getParaId();
		System.out.println("paraId" +addObj[1]);
		Object[][] data=getSqlModel().getSingleResult(getQuery(7),addObj);
			for(int i=0;i<data.length;i++){	
				bean.setVisaNumber(checkNull(String.valueOf(data[i][0])));
				bean.setCountry(checkNull(String.valueOf(data[i][1])));
				bean.setVisaType(checkNull(String.valueOf(data[i][2])));
				bean.setVisaEntry(checkNull(String.valueOf(data[i][3])));
				bean.setIssuePlace(checkNull(String.valueOf(data[i][4])));
				bean.setIssueAuthority(checkNull(String.valueOf(data[i][5])));
				bean.setIssueDate(checkNull(String.valueOf(data[i][6])));
				bean.setValidUpto(checkNull(String.valueOf(data[i][7])));
				bean.setAddress(checkNull(String.valueOf(data[i][8])));
				bean.setEmpId(checkNull(String.valueOf(data[0][9])));
				//bean.setVisaCode(checkNull(String.valueOf(data[0][10])));
				bean.setVisaCode(bean.getParaId());
		
	      }
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}


	public void getImage(VisaDetailsBean visaBean) {
		try {
			String query = " SELECT EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),"
					+ " NVL(EMP_LNAME,' ') FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ visaBean.getEmpId();
			Object[][] myPics = getSqlModel().getSingleResult(query);

			visaBean.setUploadFileName(String.valueOf(myPics[0][0]));
			visaBean.setProfileEmpName(String.valueOf(myPics[0][1]) + " "
					+ String.valueOf(myPics[0][2]) + " "
					+ String.valueOf(myPics[0][3]));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String addVisaDetail(VisaDetailsBean bean) {
		Object addObj[][] = new Object[1][10];

		addObj[0][0] = bean.getEmpId();
		addObj[0][1] = bean.getVisaNumber();
		addObj[0][2] = bean.getCountry();
		addObj[0][3] = bean.getVisaType();
		addObj[0][5] = bean.getIssuePlace().trim();
		addObj[0][6] = bean.getIssueAuthority();
		addObj[0][7] = bean.getIssueDate();
		addObj[0][8] = bean.getValidUpto();
		addObj[0][9] = bean.getAddress().trim();
		if (bean.getVisaEntry().equalsIgnoreCase("Single")) {
			addObj[0][4] = "S";
		} else {
			addObj[0][4] = "M";
	
		}
		
		boolean result = getSqlModel().singleExecute(getQuery(1), addObj);
		if (result) {
			return "Record saved Successfully";

		}// end of if
		else {

			return "Record cannot be added.";
		}// end of else
	}
	
	public boolean updatePassportDetail(VisaDetailsBean bean){
		boolean result=false;
		Object addObj[][]=new Object[1][9];
		addObj[0][0]=bean.getPassport();
		addObj[0][1]=bean.getPassportName();
		addObj[0][2]=bean.getFatherName();
		addObj[0][3]=bean.getMotherName();
		addObj[0][4]=bean.getPassportIssueFrom();
		addObj[0][5]=bean.getPassportDateOfIssue();
		addObj[0][6]=bean.getPassportExpDate();	
		addObj[0][7]=bean.getPassportUidNum();
		addObj[0][8]=bean.getEmpId();
		result = getSqlModel().singleExecute(getQuery(4), addObj);
		return result;
		
	}
	public String modifyVisaDetail(VisaDetailsBean bean)
	{
		String str="";
		try
		{
			Object[][] modObj= new Object[1][11];
			
			modObj[0][0] = bean.getVisaNumber();
			modObj[0][1] = bean.getCountry();
			modObj[0][2] = bean.getVisaType();
			//modObj[0][3] = bean.getVisaEntry();
			modObj[0][4] = bean.getIssuePlace().trim();
			modObj[0][5] = bean.getIssueAuthority();
			modObj[0][6] = bean.getIssueDate();
			modObj[0][7] = bean.getValidUpto();
			modObj[0][8] = bean.getAddress();
			modObj[0][9] = bean.getEmpId();
			modObj[0][10]=bean.getParaId();
			if(bean.getVisaEntry().equals("Single")){
				modObj[0][3]="S";
				
			}else
			{	
				modObj[0][3]= "M";
			}
			boolean result = getSqlModel().singleExecute(getQuery(8), modObj);
			if(result)
			{
			  str="Record Updated Successfully";
			}
			else
			{
			  str="Record cannot be updated";
			}
		}
		catch(Exception e)
		{
		   e.printStackTrace();	
		}
		return str;
		
	}
	
	public boolean isPersonal(VisaDetailsBean visaBean) {
		boolean finalResult = false;
		String query="SELECT * FROM HRMS_EMP_PERS WHERE EMP_ID="+ visaBean.getEmpId();
		Object [][] chkIsPersonal = getSqlModel().getSingleResult(query);
		
		if(chkIsPersonal != null && chkIsPersonal.length >0){		
				finalResult =true;
		}
		return finalResult;
	}

	public boolean insertPassPortDetail(VisaDetailsBean visaBean) {
		boolean returnResult=false;
		Object addObj[][]=new Object[1][9];
		addObj[0][0]=visaBean.getPassport();
		addObj[0][1]=visaBean.getPassportName();
		addObj[0][2]=visaBean.getFatherName();
		addObj[0][3]=visaBean.getMotherName();
		addObj[0][4]=visaBean.getPassportIssueFrom();
		addObj[0][5]=visaBean.getPassportDateOfIssue();
		addObj[0][6]=visaBean.getPassportExpDate();	
		addObj[0][7]=visaBean.getPassportUidNum();
		addObj[0][8]=visaBean.getEmpId();
		returnResult = getSqlModel().singleExecute(getQuery(9), addObj);
		return returnResult;
		
	}

	public boolean getpassportNumber(VisaDetailsBean bean)
	{
		boolean finalResult = false;
		String query="SELECT EMP_PASSPORT FROM HRMS_EMP_PERS WHERE EMP_ID="+ bean.getEmpId();
		Object [][] chkIsPassport = getSqlModel().getSingleResult(query);
		System.out.println("*******************************" +chkIsPassport);
		
		if(bean.getPassport().length()>0 && bean.getPassportDateOfIssue().length()>0 && bean.getPassportExpDate().length()>0){	
			for (int i = 0; i < chkIsPassport.length; i++) {
				if(!chkIsPassport[i][0].equals(null) && !chkIsPassport[i][0].equals("null"))
				{
					finalResult =true;
				}
			}
			
		}
		return finalResult;
	
	
	
	
}
}
