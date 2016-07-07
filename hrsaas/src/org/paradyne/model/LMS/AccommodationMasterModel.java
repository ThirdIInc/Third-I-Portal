package org.paradyne.model.LMS;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.LMS.AccommodationMasterBean;
import org.paradyne.bean.LMS.HouseMasterBean;
import org.paradyne.lib.ModelBase;

public class AccommodationMasterModel extends ModelBase{
	
	
	public boolean delete(AccommodationMasterBean bean, HttpServletRequest request) {
		boolean result = false;

		String hId = bean.getAccommodationId();

		String delQuery = "DELETE FROM LMS_ACCOMMODATION WHERE ACCOM_ID="+hId;
		result = getSqlModel().singleExecute(delQuery);
		getList(bean, request);
		return result;
	}
	
	public void getList(AccommodationMasterBean bean, HttpServletRequest request) {
		Object[][] selData = null;

		try {
			String selQuery = "select ACCOM_ID,TO_CHAR(EMP_TOKEN ||'-' ||EMP_FNAME||' '||EMP_MNAME||' '||'  '||EMP_LNAME),LMS_HOUSE.HOUSE_NO,"
				+" TO_CHAR(LMS_COLONY.COLONY_NAME),LMS_ACCOMMODATION.EMP_ID,ACCOM_COLONY_ID, "
				+" ACCOM_TYPE, ACCOM_RENT_AMT, ACCOM_FOR,ACCOM_DEPOSIT_AMT, ACCOM_VALIDITY "
				+" from LMS_ACCOMMODATION "
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=LMS_ACCOMMODATION.EMP_ID) "
				+" LEFT JOIN LMS_COLONY ON(LMS_COLONY.COLONY_ID=LMS_ACCOMMODATION.ACCOM_COLONY_ID) "
				+"  LEFT JOIN LMS_HOUSE ON(LMS_HOUSE.LMS_HOUSE_ID=LMS_ACCOMMODATION.ACCOM_HOUSE_ID) ";
			
			selData = getSqlModel().getSingleResult(selQuery);

			// System.out.println("=========== After Query ===========");
			selData = getSqlModel().getSingleResult(selQuery);
		} catch (Exception e) {

		}
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(bean.getMyPage(), selData.length, 20);
		if (pageIndex == null) {
			pageIndex[0] = "0";
			pageIndex[1] = "20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}

		try {
			System.out.println("pageIndex  of 2============    "
					+ Integer.parseInt(String.valueOf(pageIndex[2])));
		} catch (Exception e) {
			// TODO: handle exception
		}
		request.setAttribute("totalPage", Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1"))
			bean.setMyPage("1");
		if (selData == null) {

		}

		ArrayList<Object> list = new ArrayList<Object>();
		if (selData != null && selData.length > 0) {

			for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
					.parseInt(String.valueOf(pageIndex[1])); i++) {
				AccommodationMasterBean subBean = new AccommodationMasterBean();

				subBean.setAccommodationID(checkNull(String.valueOf(selData[i][0])));
				subBean.setIttEmployeeName(checkNull(String.valueOf(selData[i][1])));
				subBean.setIttHouseNo(checkNull(String.valueOf(selData[i][2])));
				subBean.setIttColonyName(checkNull(String.valueOf(selData[i][3])));
				subBean.setIttSrN0(String.valueOf(i + 1));
				list.add(subBean);
			}
		}
		bean.setAccommodationMasterList(list);
	}
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	
	
	
	public boolean saveCheckOutData(AccommodationMasterBean bean) {

		boolean result = false;
		String hId = bean.getAccommodationId();
			Object insObj[][] = new Object[1][4];

			
			
			if(bean.getCheckOutDate()!=null){
				insObj[0][0] = bean.getCheckOutDate();
			}
			
			if(bean.getReasonCheckOut()!=null){
				insObj[0][1] = bean.getReasonCheckOut();
			}
			if(bean.getRemarkCheckOut()!=null){
				insObj[0][2] = bean.getRemarkCheckOut();
			}
			
			insObj[0][3] = hId ;

			String updateQuery = "UPDATE LMS_ACCOMMODATION set CHECKOUT_DATE=TO_DATE(?,'DD-MM-YYYY'), CHECKOUT_REASON=?, CHECKOUT_REMARKS=? "
				+" where ACCOM_ID=?";
			result = getSqlModel().singleExecute(updateQuery, insObj);
		
		return result;


	}
	
	public boolean save(AccommodationMasterBean bean) {

		boolean result = false;

			Object insObj[][] = new Object[1][8];

			insObj[0][0] = bean.getEmpID();
			/*if(bean.getFreeAccommodations()!=null){
				insObj[0][1] = bean.getFreeAccommodations();
			}else if(bean.getHraDeduction()!=null){
				insObj[0][1] = bean.getHraDeduction();
			}else if(bean.getRentRec()!=null){
				insObj[0][1] = bean.getRentRec();
			}*/
			
			if(bean.getRadioValue().equals("F")){
				insObj[0][1] ="F";
			}else if(bean.getRadioValue().equals("H")){
				insObj[0][1] ="H";
			}else if(bean.getRadioValue().equals("R")){
				insObj[0][1] ="R";
			}
			
			if(bean.getRadioValue().equals("R")){
				insObj[0][2] = bean.getRentAmount();
			}else{
				insObj[0][2] = 0;
			}
			
			if(bean.getRadio1Value().equals("I")){
				insObj[0][3] ="I";
			}else if(bean.getRadio1Value().equals("F")){
				insObj[0][3] ="F";
			}
			
			if(bean.getColonyID()!=null){
				insObj[0][4] = bean.getColonyID();
			}else{
				insObj[0][4] = 0;
			}
			
			if(bean.getHouseID()!=null){
				insObj[0][5] = bean.getHouseID();
			}
			
			
			if(bean.getAmount()!=null){
				insObj[0][6] = bean.getAmount();
			}else{
				insObj[0][6] = 0;
			}
			if(bean.getValidityDate()!=null){
				insObj[0][7] = bean.getValidityDate();
			}			
			
			/*if(bean.getCheckOutDate()!=null){
				insObj[0][8] = bean.getCheckOutDate();
			}
			
			if(bean.getReasonCheckOut()!=null){
				insObj[0][9] = bean.getReasonCheckOut();
			}
			if(bean.getRemarkCheckOut()!=null){
				insObj[0][10] = bean.getRemarkCheckOut();
			}*/
			
			String houseForCheck=bean.getHouseID();
			
			
			String countFamily="";
			String countIndividual="";
			Object[][] data=null;
			
			String houseCheckQuery="Select NO_OF_FAMILY, NO_OF_INDIVIDUAL,LMS_HOUSE_ID, HOUSE_NO, COLONY_ID, HOUSE_ADDRESS from LMS_HOUSE where LMS_HOUSE_ID="+bean.getHouseID();
			data=getSqlModel().getSingleResult(houseCheckQuery);
			if(data !=null && data.length>0){
				countFamily=String.valueOf(data[0][0]);
				countIndividual=String.valueOf(data[0][1]);
			}
			
			int countFamilyAcc=0;
			int countIndividualAcc=0;
			Object[][] dataFamilyAcc=null;
			Object[][] dataIndividualAcc=null;
			
			if(bean.getRadio1Value().equals("F")){
			String houseCheckAccommodationFamilyQuery="select * from lms_accommodation where CHECKOUT_DATE is null and ACCOM_FOR='"+ bean.getRadio1Value()+"' and ACCOM_HOUSE_ID="+bean.getHouseID();
			dataFamilyAcc=getSqlModel().getSingleResult(houseCheckAccommodationFamilyQuery);
			if(dataFamilyAcc !=null && dataFamilyAcc.length>0){
				for(int i=0;i<dataFamilyAcc.length;i++ ){
					countFamilyAcc=countFamilyAcc+1;
					//countIndividual=countIndividual+1;
				}
			}
			
			//if(countIndividualAcc !=Integer.parseInt(countIndividual)){
			if(Integer.parseInt(countFamily)>countFamilyAcc){
				
				String insQuery = "INSERT INTO lms_accommodation ( ACCOM_ID,EMP_ID,ACCOM_TYPE,ACCOM_RENT_AMT,ACCOM_FOR,ACCOM_COLONY_ID,ACCOM_HOUSE_ID,ACCOM_DEPOSIT_AMT,ACCOM_VALIDITY) " 
						+" VALUES((SELECT NVL(MAX(ACCOM_ID),0)+1 FROM lms_accommodation) ,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'))";
				result = getSqlModel().singleExecute(insQuery, insObj);
				result=true;
				}else{
					result=false;
				}
			
			}else if(bean.getRadio1Value().equals("I")){
			String houseCheckAccommodationindividualQuery="select * from lms_accommodation where CHECKOUT_DATE is null and ACCOM_FOR='"+ bean.getRadio1Value()+"' and ACCOM_HOUSE_ID="+bean.getHouseID();
			dataIndividualAcc=getSqlModel().getSingleResult(houseCheckAccommodationindividualQuery);
			if(dataIndividualAcc !=null && dataIndividualAcc.length > 0){
				for(int i=0;i<dataIndividualAcc.length;i++ ){
					//countFamily=countFamily+1;
					countIndividualAcc=countIndividualAcc+1;
				}
			  }
				
				//if(Integer.parseInt(countFamily)!=countFamilyAcc){
			if(countIndividualAcc < Integer.parseInt(countIndividual)){
				String insQuery = "INSERT INTO lms_accommodation ( ACCOM_ID,EMP_ID,ACCOM_TYPE,ACCOM_RENT_AMT,ACCOM_FOR,ACCOM_COLONY_ID,ACCOM_HOUSE_ID,ACCOM_DEPOSIT_AMT,ACCOM_VALIDITY) " 
						+" VALUES((SELECT NVL(MAX(ACCOM_ID),0)+1 FROM lms_accommodation) ,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'))";
				result = getSqlModel().singleExecute(insQuery, insObj);
				result=true;
				}else{
					result=false;
				}
			}
			
			
		
			
			
			return result;

	}
	
	public boolean update(AccommodationMasterBean bean) {

		boolean result = false;
		 String hId = bean.getAccommodationId();
			Object insObj[][] = new Object[1][9];

			insObj[0][0] = bean.getEmpID();
					
			if(bean.getRadioValue().equals("F")){
				insObj[0][1] ="F";
			}else if(bean.getRadioValue().equals("H")){
				insObj[0][1] ="H";
			}else if(bean.getRadioValue().equals("R")){
				insObj[0][1] ="R";
			}
			if(bean.getRentRec()!=null){
				insObj[0][2] = bean.getRentAmount();
			}else{
				insObj[0][2] = 0;
			}
			
			if(bean.getRadio1Value().equals("I")){
				insObj[0][3] ="I";
			}else if(bean.getRadio1Value().equals("F")){
				insObj[0][3] ="F";
			}
			
			if(bean.getColonyID()!=null){
				insObj[0][4] = bean.getColonyID();
			}else{
				insObj[0][4] = 0;
			}
			
			if(bean.getHouseID()!=null){
				insObj[0][5] = bean.getHouseID();
			}
			
			
			if(bean.getAmount()!=null){
				insObj[0][6] = bean.getAmount();
			}else{
				insObj[0][6] = 0;
			}
			if(bean.getValidityDate()!=null){
				insObj[0][7] = bean.getValidityDate();
			}			
			
				
			insObj[0][8] = hId ;
			
			
			//Check for Type availability Individual or Family 
			String famChk="";
			Object[][] dataFamChk=null;
			String famCheck="select ACCOM_FOR from lms_accommodation where ACCOM_ID="+ hId;
			dataFamChk=getSqlModel().getSingleResult(famCheck);
			if(dataFamChk !=null && dataFamChk.length>0){
				famChk=String.valueOf(dataFamChk[0][0]);
			}
			
			
			if(famChk.equals(bean.getRadio1Value())){
				String updateQuery = "UPDATE LMS_ACCOMMODATION set EMP_ID=?,ACCOM_TYPE=?,ACCOM_RENT_AMT=?,ACCOM_FOR=?, "
					+" ACCOM_COLONY_ID=?,ACCOM_HOUSE_ID=?,ACCOM_DEPOSIT_AMT=?,ACCOM_VALIDITY=TO_DATE(?,'DD-MM-YYYY') "
					//+" CHECKOUT_DATE=TO_DATE(?,'DD-MM-YYYY'), CHECKOUT_REASON=?, CHECKOUT_REMARKS=? "
					+" where ACCOM_ID=?";
				result = getSqlModel().singleExecute(updateQuery, insObj);
			}else{
				
			String countFamily="";
			String countIndividual="";
			Object[][] data=null;
			
			String houseCheckQuery="Select NO_OF_FAMILY, NO_OF_INDIVIDUAL,LMS_HOUSE_ID, HOUSE_NO, COLONY_ID, HOUSE_ADDRESS from LMS_HOUSE where LMS_HOUSE_ID="+bean.getHouseID();
			data=getSqlModel().getSingleResult(houseCheckQuery);
			if(data !=null && data.length>0){
				countFamily=String.valueOf(data[0][0]);
				countIndividual=String.valueOf(data[0][1]);
			}
			
			int countFamilyAcc=0;
			int countIndividualAcc=0;
			Object[][] dataFamilyAcc=null;
			Object[][] dataIndividualAcc=null;
			
			if(bean.getRadio1Value().equals("F")){
			String houseCheckAccommodationFamilyQuery="select * from lms_accommodation where CHECKOUT_DATE is null and ACCOM_FOR='"+ bean.getRadio1Value()+"' and ACCOM_HOUSE_ID="+bean.getHouseID();
			dataFamilyAcc=getSqlModel().getSingleResult(houseCheckAccommodationFamilyQuery);
			if(dataFamilyAcc !=null && dataFamilyAcc.length>0){
				for(int i=0;i<dataFamilyAcc.length;i++ ){
					countFamilyAcc=countFamilyAcc+1;
					//countIndividual=countIndividual+1;
				}
			}
			
			//if(countIndividualAcc !=Integer.parseInt(countIndividual)){
			if(Integer.parseInt(countFamily)>countFamilyAcc){
				
				String updateQuery = "UPDATE LMS_ACCOMMODATION set EMP_ID=?,ACCOM_TYPE=?,ACCOM_RENT_AMT=?,ACCOM_FOR=?, "
					+" ACCOM_COLONY_ID=?,ACCOM_HOUSE_ID=?,ACCOM_DEPOSIT_AMT=?,ACCOM_VALIDITY=TO_DATE(?,'DD-MM-YYYY') "
					//+" CHECKOUT_DATE=TO_DATE(?,'DD-MM-YYYY'), CHECKOUT_REASON=?, CHECKOUT_REMARKS=? "
					+" where ACCOM_ID=?";
				result = getSqlModel().singleExecute(updateQuery, insObj);
				result=true;
				}else{
					result=false;
				}
			
			}else if(bean.getRadio1Value().equals("I")){
			String houseCheckAccommodationindividualQuery="select * from lms_accommodation where CHECKOUT_DATE is null and ACCOM_FOR='"+ bean.getRadio1Value()+"' and ACCOM_HOUSE_ID="+bean.getHouseID();
			dataIndividualAcc=getSqlModel().getSingleResult(houseCheckAccommodationindividualQuery);
			if(dataIndividualAcc !=null && dataIndividualAcc.length > 0){
				for(int i=0;i<dataIndividualAcc.length;i++ ){
					//countFamily=countFamily+1;
					countIndividualAcc=countIndividualAcc+1;
				}
			  }
				
				//if(Integer.parseInt(countFamily)!=countFamilyAcc){
			if(countIndividualAcc < Integer.parseInt(countIndividual)){
				String updateQuery = "UPDATE LMS_ACCOMMODATION set EMP_ID=?,ACCOM_TYPE=?,ACCOM_RENT_AMT=?,ACCOM_FOR=?, "
					+" ACCOM_COLONY_ID=?,ACCOM_HOUSE_ID=?,ACCOM_DEPOSIT_AMT=?,ACCOM_VALIDITY=TO_DATE(?,'DD-MM-YYYY') "
					//+" CHECKOUT_DATE=TO_DATE(?,'DD-MM-YYYY'), CHECKOUT_REASON=?, CHECKOUT_REMARKS=? "
					+" where ACCOM_ID=?";
				result = getSqlModel().singleExecute(updateQuery, insObj);
				result=true;
				}else{
					result=false;
				}
			}
			
			
			}
		
		return result;

	}
	
	public void dblClickItt(AccommodationMasterBean bean,HttpServletRequest request) {

		getDetails(bean,request);
	}
	
	public void getDetails(AccommodationMasterBean bean,HttpServletRequest request) {
		try {
			String hId = bean.getAccommodationId();
			String selQuery = " select ACCOM_ID, LMS_ACCOMMODATION.EMP_ID, ACCOM_TYPE, ACCOM_RENT_AMT, ACCOM_FOR, ACCOM_COLONY_ID, h1.HOUSE_NO, ACCOM_DEPOSIT_AMT, TO_CHAR(ACCOM_VALIDITY,'DD-MM-YYYY')"
				+" ,HRMS_RANK.RANK_NAME,HRMS_CENTER.CENTER_NAME,LMS_COLONY.COLONY_NAME,LMS_HOUSE.HOUSE_ADDRESS,TO_CHAR(EMP_FNAME||' '||EMP_MNAME||' '||'  '||EMP_LNAME) ,"
				+" TO_CHAR(CHECKOUT_DATE,'DD-MM-YYYY'), CHECKOUT_REASON, CHECKOUT_REMARKS,HRMS_EMP_OFFC.EMP_TOKEN,h1.LMS_HOUSE_ID "
				+" from LMS_ACCOMMODATION  "
				+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=LMS_ACCOMMODATION.EMP_ID)  "
				+" LEFT JOIN LMS_COLONY ON(LMS_COLONY.COLONY_ID=LMS_ACCOMMODATION.ACCOM_COLONY_ID)  "
				+" LEFT JOIN HRMS_RANK ON(HRMS_RANK.RANK_ID=HRMS_EMP_OFFC.EMP_RANK)  "  
				+" LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER) "
				+" LEFT JOIN LMS_HOUSE ON(LMS_HOUSE.COLONY_ID=LMS_ACCOMMODATION.ACCOM_COLONY_ID) "
				+" LEFT JOIN LMS_HOUSE h1 ON(h1.LMS_HOUSE_ID=LMS_ACCOMMODATION.ACCOM_HOUSE_ID) "
				+" where   ACCOM_ID= "+hId ;
			Object[][] data = getSqlModel().getSingleResult(selQuery);
			bean.setAccommodationId(checkNull(String.valueOf(data[0][0])));
			request.setAttribute("accId", bean.getAccommodationId());
			bean.setEmpID(checkNull(String.valueOf(data[0][1])));
			if((String.valueOf(data[0][2])).equals("R")){
				bean.setRadioValue(checkNull(String.valueOf(data[0][2])));
				request.setAttribute("valueRadioStatus", bean.getRadioValue());
			}
			if((String.valueOf(data[0][2])).equals("F")){
				bean.setRadioValue(checkNull(String.valueOf(data[0][2])));
				request.setAttribute("valueRadioStatus", bean.getRadioValue());
			}
			if((String.valueOf(data[0][2])).equals("H")){
				bean.setRadioValue(checkNull(String.valueOf(data[0][2])));
				request.setAttribute("valueRadioStatus", bean.getRadioValue());
			}
			
			if((String.valueOf(data[0][2])).equals("R")){
				bean.setRentAmount(checkNull(String.valueOf(data[0][3])));
			}else{
				bean.setRentAmount("");
			}
			
			if((String.valueOf(data[0][4])).equals("I")){
				//bean.setIndividual(checkNull(String.valueOf(data[0][4])));
				bean.setRadio1Value(checkNull(String.valueOf(data[0][4])));
				request.setAttribute("indFamRadioStatus", bean.getRadio1Value());
			}
			
			if((String.valueOf(data[0][4])).equals("F")){
				//bean.setIndividual(checkNull(String.valueOf(data[0][4])));
				bean.setRadio1Value(checkNull(String.valueOf(data[0][4])));
				request.setAttribute("indFamRadioStatus",bean.getRadio1Value());
			}
			
			bean.setColonyID(checkNull(String.valueOf(data[0][5])));
			bean.setHouseNo(checkNull(String.valueOf(data[0][6])));
			bean.setAmount(checkNull(String.valueOf(data[0][7])));
			bean.setValidityDate(checkNull(String.valueOf(data[0][8])));
			
			bean.setDesignationName(checkNull(String.valueOf(data[0][9])));
			bean.setBranchName(checkNull(String.valueOf(data[0][10])));
			bean.setColonyName(checkNull(String.valueOf(data[0][11])));
			bean.setHouseAddr(checkNull(String.valueOf(data[0][12])));
			bean.setEmpName(checkNull(String.valueOf(data[0][13])));
			
			bean.setCheckOutDate(checkNull(String.valueOf(data[0][14])));
			bean.setReasonCheckOut(checkNull(String.valueOf(data[0][15])));
			bean.setRemarkCheckOut(checkNull(String.valueOf(data[0][16])));
			bean.setEmpToken(checkNull(String.valueOf(data[0][17])));
			bean.setHouseID(String.valueOf(data[0][18]));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
