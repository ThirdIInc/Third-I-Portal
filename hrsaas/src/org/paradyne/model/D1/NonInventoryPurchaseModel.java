/**
 * 
 */
package org.paradyne.model.D1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.D1.NonInventoryPurchaseBean;
import org.paradyne.bean.leave.Regularization;

import org.paradyne.lib.ModelBase;
import org.paradyne.model.common.ApplCodeTemplateModel;

/**
 * @author ganesh
 * 
 */
public class NonInventoryPurchaseModel extends ModelBase {

	public boolean draft(NonInventoryPurchaseBean bean,String status,String[]ittselectTypeCode,String []ittnoOfLots,String[] aaprCode, HttpServletRequest request) {
		
		
		
		Object[][] data = new Object[1][58];
		data[0][0] = bean.getEmployeeCode();
		data[0][1] = bean.getExtension();

		// START:Shiping Infomation
		data[0][2] = bean.getAddress();
		data[0][3] = bean.getCityName();
		data[0][4] = bean.getZip();
		data[0][5] = bean.getAttn();
		// END:Shiping Infomation

		// START:Imprint Infomation
		data[0][6] = bean.getImprintId();
		data[0][7] = bean.getImprintName();
		data[0][8] = bean.getCompanyName();
		data[0][9] = bean.getTitle();
		data[0][10] = bean.getCompanyAddress();
		data[0][11] = bean.getCompCityName();
		// data[0][12]=bean.getCompanyState();
		data[0][12] = bean.getCompanyZip();
		data[0][13] = bean.getCompPhoneNumber();
		data[0][14] = bean.getCompFaxNumber();
		data[0][15] = bean.getCompOtherNumber();
		data[0][16] = bean.getCompDescription();
		data[0][17] = bean.getInternet();
		// END:Imprint Infomation

		// Description # of Lots Description # of Lots
	/*	data[0][18] = bean.getBusinessCardsRegular();
		data[0][19] = bean.getEnvelope10x13();
		data[0][20] = bean.getBusinessCardsCne();
		data[0][21] = bean.getLetterheadRegular();
		data[0][22] = bean.getBusinessCardsLogo();
		data[0][23] = bean.getLetterheadManager();
		data[0][24] = bean.getEnvelopeRegular();
		data[0][25] = bean.getMemoPads();
		data[0][26] = bean.getEnvelopeWindow();
		data[0][27] = bean.getMemoLoose();
		data[0][28] = bean.getEnvelope12Window();
		data[0][29] = bean.getLabelFrom();
		data[0][30] = bean.getEnvelope9x12();
		data[0][31] = bean.getLabelFromTo();
		data[0][32] = bean.getLetterheadLogo();
		data[0][33] = bean.getMemoManager();*/
		// Description # of Lots Description # of Lots

		// Additional Infomation
		data[0][18] = bean.getAdditionalName();
		data[0][19] = bean.getAdditionalCompanyName();
		data[0][20] = bean.getAdditionalTitle();
		data[0][21] = bean.getAdditionalAddress();
		data[0][22] = bean.getAdditionalCity();
		// data[0][0]=bean.getAdditionalState();
		data[0][23] = bean.getAdditionalZip();
		data[0][24] = bean.getAdditionalPhoneNumber();
		data[0][25] = bean.getAdditionalFax();
		data[0][26] = bean.getAdditionalOtherNumber();
		data[0][27] = bean.getAdditionalDesc();
		data[0][28] = bean.getAdditionalInternet();
		String workerCode = bean.getNonInventoryCode();
		
		String approverCode="";
		if(aaprCode !=null && aaprCode.length>0 && bean.getChangeApproverCode().equals("")){
			approverCode=aaprCode[0];
		}
		
		if (bean.getNonInventoryCode().equals("")) {
			String selQuery = "SELECT NVL(MAX(NON_PURCHASE_ID),0)+1 FROM HRMS_D1_NON_INVENTORY_PURCHASE ";
			Object[][] selData = getSqlModel().getSingleResult(selQuery);
			workerCode = String.valueOf(selData[0][0]);
			bean.setNonInventoryCode(workerCode);
			
			/**
			 * SET TRACKING NO
			 */String qq="SELECT NVL(MAX(NON_PURCHASE_ID),0)+1||'-'||TO_CHAR(SYSDATE,'MM/DD/YYYY'),NVL(MAX(NON_PURCHASE_ID),0)+1	,TO_CHAR(SYSDATE,'DD-MM-YYYY')	 FROM HRMS_D1_NON_INVENTORY_PURCHASE	";
				Object[][]obj=getSqlModel().getSingleResult(qq);
				if(obj !=null && obj.length>0){			
				try {
					ApplCodeTemplateModel model = new ApplCodeTemplateModel();
					model.initiate(context, session);
					String applnCode = model.generateApplicationCode(bean.getNonInventoryCode(), "D1-NONINV", bean.getEmployeeCode(),String.valueOf(obj[0][2]));
					bean.setFileheaderName(checkNull(applnCode));
					model.terminate();
				} catch (Exception e) {
					// TODO: handle exception
				}
				}	
			
			
		}
		
		/**
		 * ADDITION MORE DATA
		 */
		data[0][29] = bean.getShipTo();
		data[0][30] = bean.getASLMPS();
		data[0][31] = bean.getHasWrittenQuote();
		data[0][32]=bean.getStationary().equals("true")?"Y":"N";
		data[0][33] = bean.getHardwareSoftware().equals("true")?"Y":"N";
		//PROJECT INFORMATION
		data[0][34] = bean.getProjectDesc();
		data[0][35] = bean.getBusinessObj();
		data[0][36] = bean.getRecommendations();
		data[0][37] = bean.getBusinessBenefits();
		data[0][38] = bean.getResposiblePersonCode();
		data[0][39] = bean.getAssetInformation();
		data[0][40] = bean.getAccount();
		data[0][41] = bean.getComment();
		data[0][42] = bean.getShipVia();
		data[0][43] = bean.getCear();
		//PRODUCT INFORMATION
		
		data[0][44] = bean.getStateName();
		data[0][45] = bean.getCountry();
		data[0][46] = bean.getCompanyState();
		data[0][47] = bean.getAdditionalState();
		
		data[0][48] = bean.getVendorName();
		data[0][49] = bean.getVendorPhoneNo();
		data[0][50] = bean.getVendorEmailId();
		data[0][51] = bean.getVendorShipVia();
		
		data[0][52] = bean.getPreparerPhone();
		data[0][53] = bean.getPreparerFax();
		data[0][54] = bean.getAsiVendorID();
		data[0][55] = bean.getDepartment();
		data[0][56]=bean.getUploadFileName();
		data[0][57]=bean.getShippingPhoneNumber();
		
		String insQuery = "INSERT INTO HRMS_D1_NON_INVENTORY_PURCHASE (NON_PURCHASE_ID, NON_INVNTY_APPL_DATE, NON_INVNTY_EMP_ID, NON_INVNTY_EMP_EXT, SHIPPING_ADDRESS, SHIPPING_CITY, SHIPPING_PIN_CODE, SHIPPING_ATTN, IMPRINT_TYPE, IMPRINT_NAME, IMPRINT_COMPANY_NAME, IMPRINT_TITLE, IMPRINT_ADDR, IMPRINT_CITY, IMPRINT_PIN_CODE, IMPRINT_PHONE_NUMBER, IMPRINT_FAX_NUMBER, IMPRINT_OTHER_NUMBER, IMPRINT_DESCRIPTION, IMPRINT_INTERNET, " +
				//"  BUSINESS_CARDS_REGULAR, ENVELOPE_10_13, BUSINESS_CARDS_CNE_LOGO, LETTRHD_REGULAR_CO_ADDRESS, BUSINESS_CARDS_25_YEAR_LOGO, LETTRHD_MANAGER_AND_ABOVE, ENVELOPE_10_REGULAR, MEMO_PADS_4_6, ENVELOPE_10_WINDOW, MEMO_LOOSE_4_6, ENVELOPE_12_WINDOW, LABELS_FROM_ONLY_6_3, ENVELOPE_9_12, LABELS_FROM_TO_ONLY_6_3, LETTRHD_LOGO_ONLY, MEMO_MANAGER_ABOVE_5_7, " +
				"   ADDITIONAL_NAME, ADDITIONAL_COMPANY_NAME, ADDITIONAL_TITLE, ADDITIONAL_ADDRESS, ADDITIONAL_CITY, ADDITIONAL_PIN_CODE, ADDITIONAL_PHONE_NUMBER, ADDITIONAL_FAX_NUMBER, ADDITIONAL_OTHER_NUMBER, ADDITIONAL_DESCRIPTION, ADDITIONAL_INTERNET,NON_INVNTY_STATUS,CHANGE_MANG_CODE,APPROVER_CODE" +
				"  ,SHIP_TO, ASL_MPS, HAS_WRITTEN_QUOTE, REQUEST_TYPE_STATIONARY,REQUEST_TYPE_HARDWARE, PROJECT_DESC, BUSINESS_OBJ, RECOMMENDATION, BUSINESS_BENEFITS, RESPONSIBLE_PERSON, ASSET_INFORMATION, ACCOUNT, COMMENTS, SHIP_VIA, CEAR,TRACKING_NO,COMPLETED_BY,COMPLETED_ON ,SHIPPING_STATE,SHIPPING_COUNTRY,IMPRINT_STATE,ADDITIONAL_STATE,SUGG_VENDOR_NAME, SUGG_VENDOR_PHONE_NO, SUGG_VENDOR_EMAIL_ID, SUGG_VENDOR_SHIP_VIA,NON_INVNTY_EMP_PHONE,NON_INVNTY_EMP_FAX,ASI_VENDOR_ID,NON_EMP_DEPT,NON_INVNTY_FILE_UPLOAD,NON_INVNTY_SHIPPING_PHONE ) "
				+ "	VALUES("
				+ bean.getNonInventoryCode()
				+ ",SYSDATE,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'"+status+"','"+bean.getChangeApproverCode()+"','"+approverCode+"',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'"+bean.getFileheaderName()+"','"+bean.getUserEmpId()+"',SYSDATE,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		String selQuery="DELETE FROM HRMS_D1_NON_INVENTORY_PURCHASE WHERE NON_PURCHASE_ID="+workerCode;
		String selSubQuery="DELETE FROM HRMS_D1_NON_INVENT_BUSINESS WHERE NON_PURCHASE_ID="+workerCode;
		String insSubQuery="INSERT INTO HRMS_D1_NON_INVENT_BUSINESS(NON_INVENT_ID,NON_PURCHASE_ID,BUSINESS_ID,BUSINESS_NO_LOTS)" +
				"     		VALUES((SELECT NVL(MAX(NON_INVENT_ID),0)+1 FROM HRMS_D1_NON_INVENT_BUSINESS),?,?,?) ";
		Object[][]obj=null;
		if(ittselectTypeCode !=null && ittselectTypeCode.length>0){
			obj=new Object[ittselectTypeCode.length][3];
			for (int i = 0; i < obj.length; i++) {
				obj[i][0]=workerCode;
				obj[i][1]=ittselectTypeCode[i];
				obj[i][2]=ittnoOfLots[i];
			}
		}
		/**
		 * INSERT INTO PRODUCT INFO
		 */
		String delProduct="DELETE FROM HRMS_D1_NON_INVENT_PRODUCT_INF WHERE NON_PURCHASE_ID="+workerCode;
		String insProduct="INSERT INTO HRMS_D1_NON_INVENT_PRODUCT_INF(PRODUCT_QTY, PRODUCT_UNIT, PRODUCT_PRIZE, PRODUCT_MANUFACTURE, PRODUCT_MRP_PART, PRODUCT_VENDOR_PART, PRODUCT_DESCRIPTION, PRODUCT_CODE,NON_PURCHASE_ID)"
							+"  VALUES(?,?,?,?,?,?,?,?,?) ";
		String ittqty[]=request.getParameterValues("ittqty");
		String ittunit[]=request.getParameterValues("ittunit");
		String ittprice[]=request.getParameterValues("ittprice");
		String ittmanufacture[]=request.getParameterValues("ittmanufacture");
		String ittmrfPart[]=request.getParameterValues("ittmrfPart");
		String ittvendorPart[]=request.getParameterValues("ittvendorPart");
		String ittdesc[]=request.getParameterValues("ittdesc");
		Object[][]objProduct=null;;
		if(ittqty !=null && ittqty.length>0){
			objProduct=new Object[ittqty.length][9];
			for (int i = 0; i < ittqty.length; i++) {
				objProduct[i][0]=ittqty[i];
				objProduct[i][1]=ittunit[i];
				objProduct[i][2]=ittprice[i];
				objProduct[i][3]=ittmanufacture[i];
				objProduct[i][4]=ittmrfPart[i];
				objProduct[i][5]=ittvendorPart[i];
				objProduct[i][6]=ittdesc[i];
				objProduct[i][7]=(i+1);
				objProduct[i][8]=workerCode;
			}
		}
		
		boolean result=getSqlModel().singleExecute(selQuery);
		if(result){
			 getSqlModel().singleExecute(selSubQuery);
			 getSqlModel().singleExecute(delProduct);
			 if(obj !=null && obj.length>0){
			 getSqlModel().singleExecute(insSubQuery, obj);
			 } 
			 	getSqlModel().singleExecute(insQuery, data);
			 if(ittqty !=null && ittqty.length>0){
				 getSqlModel().singleExecute(insProduct, objProduct);
			 }
		}
		return result;
	}

	public boolean delete(NonInventoryPurchaseBean bean) {
		boolean result=false;
			Object[][]data=new Object[1][1];
			data[0][0]=bean.getNonInventoryCode();
			String delQuery="DELETE FROM HRMS_D1_NON_INVENTORY_PURCHASE WHERE NON_PURCHASE_ID=?";
			result= getSqlModel().singleExecute(delQuery,data);
			if(result){
				delQuery="DELETE FROM HRMS_D1_NON_INVENTORY_PATH WHERE NON_INVENTORY_ID=?";
				result= getSqlModel().singleExecute(delQuery,data);
			}
	 return result;
	}

	public void input(NonInventoryPurchaseBean bean, HttpServletRequest request) {
		
		String query="SELECT TRACKING_NO, EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME,TO_CHAR(NON_INVNTY_APPL_DATE,'DD-MM-YYYY') "
					+"	,HRMS_D1_NON_INVENTORY_PURCHASE.NON_INVNTY_EMP_ID,NON_PURCHASE_ID FROM HRMS_D1_NON_INVENTORY_PURCHASE "
					+"	 INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_D1_NON_INVENTORY_PURCHASE.NON_INVNTY_EMP_ID) ";
		if (1==1) {
			query += " WHERE HRMS_D1_NON_INVENTORY_PURCHASE.NON_INVNTY_EMP_ID=" + bean.getUserEmpId();
		}
		else{
			query += " WHERE 1=1 ";
		}
		
		
		
		String draft=query+" AND NON_INVNTY_STATUS='D' ";
		draft = draft + " ORDER BY HRMS_D1_NON_INVENTORY_PURCHASE.NON_PURCHASE_ID DESC  ";
		
		Object[][]data=getSqlModel().getSingleResult(draft);
		
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(bean.getMyPage(), data.length, 20);
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
		
		/**
		 * DRAFT APPLICATION
		 */
		
		if(data !=null && data.length>0){
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndex[0])); i < Integer
			.parseInt(String.valueOf(pageIndex[1])); i++) {
				NonInventoryPurchaseBean bean1=new NonInventoryPurchaseBean();
				bean1.setIttEmployeeToken(checkNull(checkNull(String.valueOf(data[i][0]))));
				bean1.setIttEmployee(String.valueOf(data[i][1]));
				bean1.setIttApplicationDate(String.valueOf(data[i][2]));
				bean1.setIttnonInventoryCode(String.valueOf(data[i][4]));
				list.add(bean1);
			}
			bean.setListDraft(list);
		}
		
		/**
		 * IN PROGRESS
		 */
		String progress=query+" AND NON_INVNTY_STATUS IN('P','L')  ";
		progress = progress + " ORDER BY HRMS_D1_NON_INVENTORY_PURCHASE.NON_PURCHASE_ID DESC  ";
		Object[][]objProgress=getSqlModel().getSingleResult(progress);
		String[]pageIndexRe=setData(bean,request,objProgress,"totalPageP","pageNoP",bean.getMyPage1(),"1");
		if(objProgress !=null && objProgress.length>0){	
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexRe[0])); i < Integer
			.parseInt(String.valueOf(pageIndexRe[1])); i++) {
				NonInventoryPurchaseBean bean1=new NonInventoryPurchaseBean();
				bean1.setIttEmployeeToken(checkNull(checkNull(String.valueOf(objProgress[i][0]))));
				bean1.setIttEmployee(String.valueOf(objProgress[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objProgress[i][2]));
				//bean1.setIttEmployeeId(String.valueOf(objProgress[i][3]));
				bean1.setIttnonInventoryCode(String.valueOf(objProgress[i][4]));
				list.add(bean1);
			}
			bean.setListInProgress(list);
		}
		
		/**
		 * APPROVED PPLICATION
		 */
		String approve=query+" AND NON_INVNTY_STATUS='A'  ";
		if(bean.getFlag().equals("AA")){
			approve = approve + " ORDER BY HRMS_D1_NON_INVENTORY_PURCHASE.NON_PURCHASE_ID DESC  ";
			Object[][]objApprove=getSqlModel().getSingleResult(approve);
			String[]pageIndexAppr=setData(bean,request,objApprove,"totalPage","pageNo",bean.getMyPage(),"");
		if(objApprove !=null && objApprove.length>0){
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexAppr[0])); i < Integer
			.parseInt(String.valueOf(pageIndexAppr[1])); i++) {
				NonInventoryPurchaseBean bean1=new NonInventoryPurchaseBean();
				bean1.setIttEmployeeToken(checkNull(checkNull(String.valueOf(objApprove[i][0]))));
				bean1.setIttEmployee(String.valueOf(objApprove[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objApprove[i][2]));
				//bean1.setIttEmployeeId(String.valueOf(objApprove[i][3]));
				bean1.setIttnonInventoryCode(String.valueOf(objApprove[i][4]));
				list.add(bean1);
			}
			bean.setListApprove(list);
		}
		}
		
		/**
		 * REJECT APPLICATION
		 */
		String reject=query+" AND NON_INVNTY_STATUS='R'  ";
		
				if(bean.getFlag().equals("RR")){
					reject = reject + " ORDER BY HRMS_D1_NON_INVENTORY_PURCHASE.NON_PURCHASE_ID DESC  ";
					Object[][]objReject=getSqlModel().getSingleResult(reject);
					String[]pageIndexR=setData(bean,request,objReject,"totalPage","pageNo",bean.getMyPage(),"");		
				if(objReject !=null && objReject.length>0){
					ArrayList list =new ArrayList();
					for (int i = Integer.parseInt(String.valueOf(pageIndexR[0])); i < Integer
					.parseInt(String.valueOf(pageIndexR[1])); i++) {
						NonInventoryPurchaseBean bean1=new NonInventoryPurchaseBean();
						bean1.setIttEmployeeToken(checkNull(checkNull(String.valueOf(objReject[i][0]))));
						bean1.setIttEmployee(String.valueOf(objReject[i][1]));
						bean1.setIttApplicationDate(String.valueOf(objReject[i][2]));
						//bean1.setIttEmployeeId(String.valueOf(objReject[i][3]));
						bean1.setIttnonInventoryCode(String.valueOf(objReject[i][4]));
						list.add(bean1);
					}
					bean.setListReject(list);
				}
			}		
				
		/**
		 * SEND BACK APPLICATION
		 */
		String sendBack=query+" AND NON_INVNTY_STATUS='B'  ";
		sendBack = sendBack + " ORDER BY HRMS_D1_NON_INVENTORY_PURCHASE.NON_PURCHASE_ID DESC  ";
		Object[][]objSendBack=getSqlModel().getSingleResult(sendBack);
		if(objSendBack !=null && objSendBack.length>0){			
			String[]pageIndexB=setData(bean,request,objSendBack,"totalPageB","pageNoB",bean.getMyPage2(),"2");	
			ArrayList list =new ArrayList();
			for (int i = Integer.parseInt(String.valueOf(pageIndexB[0])); i < Integer
			.parseInt(String.valueOf(pageIndexB[1])); i++) {
				NonInventoryPurchaseBean bean1=new NonInventoryPurchaseBean();
				bean1.setIttEmployeeToken(checkNull(checkNull(String.valueOf(objSendBack[i][0]))));
				bean1.setIttEmployee(String.valueOf(objSendBack[i][1]));
				bean1.setIttApplicationDate(String.valueOf(objSendBack[i][2]));
				//bean1.setIttEmployeeId(String.valueOf(objSendBack[i][3]));
				bean1.setIttnonInventoryCode(String.valueOf(objSendBack[i][4]));
				list.add(bean1);
			}
			bean.setListSendBack(list);
		}		
	}
	
	
	public String[] setData(NonInventoryPurchaseBean bean, HttpServletRequest request,Object[][]data,String totalPage,String pageNo,String page,String type){
		String[] pageIndex = new org.paradyne.lib.Utility().doPaging(page, data.length, 20);
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
		request.setAttribute(totalPage, Integer.parseInt(String
				.valueOf(pageIndex[2])));
		request.setAttribute(pageNo, Integer.parseInt(String
				.valueOf(pageIndex[3])));
		if (pageIndex[4].equals("1")){
			bean.setMyPage("1");
		if(type.equals("1")){
			bean.setMyPage1("1");
		}
		if(type.equals("2")){
			bean.setMyPage2("1");
		}
		}
		return pageIndex;
	}

	public boolean editApplication(NonInventoryPurchaseBean bean) {
		
		String query="SELECT NON_PURCHASE_ID, TO_CHAR(NON_INVNTY_APPL_DATE,'DD-MM-YYYY'), NON_INVNTY_EMP_ID,"
					+" OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME,NON_INVNTY_EMP_PHONE,NON_INVNTY_EMP_FAX, NON_INVNTY_EMP_EXT," 
					+" SHIPPING_ADDRESS, SHIPPING_CITY, SHIPPING_PIN_CODE, SHIPPING_ATTN, "
					+" IMPRINT_TYPE,HRMS_D1_IMPRINT_TYPE .IMPRINT_NAME,HRMS_D1_NON_INVENTORY_PURCHASE.IMPRINT_NAME, IMPRINT_COMPANY_NAME, IMPRINT_TITLE, IMPRINT_ADDR, IMPRINT_CITY, IMPRINT_PIN_CODE, IMPRINT_PHONE_NUMBER, IMPRINT_FAX_NUMBER, IMPRINT_OTHER_NUMBER, IMPRINT_DESCRIPTION, IMPRINT_INTERNET, "
					//+",BUSINESS_CARDS_REGULAR, ENVELOPE_10_13, BUSINESS_CARDS_CNE_LOGO, LETTRHD_REGULAR_CO_ADDRESS, BUSINESS_CARDS_25_YEAR_LOGO, LETTRHD_MANAGER_AND_ABOVE, ENVELOPE_10_REGULAR, MEMO_PADS_4_6, ENVELOPE_10_WINDOW, MEMO_LOOSE_4_6, ENVELOPE_12_WINDOW, LABELS_FROM_ONLY_6_3, ENVELOPE_9_12, LABELS_FROM_TO_ONLY_6_3, LETTRHD_LOGO_ONLY, MEMO_MANAGER_ABOVE_5_7, " +
					+" ADDITIONAL_NAME, ADDITIONAL_COMPANY_NAME, ADDITIONAL_TITLE, ADDITIONAL_ADDRESS, ADDITIONAL_CITY, ADDITIONAL_PIN_CODE, ADDITIONAL_PHONE_NUMBER, ADDITIONAL_FAX_NUMBER, ADDITIONAL_OTHER_NUMBER, ADDITIONAL_DESCRIPTION, ADDITIONAL_INTERNET, " 
					+" OFFC1.EMP_TOKEN,OFFC1.EMP_FNAME||' '||OFFC1.EMP_MNAME||' '||OFFC1.EMP_LNAME,CHANGE_MANG_CODE, " 
					+" SHIP_TO, ASL_MPS, HAS_WRITTEN_QUOTE, DECODE(REQUEST_TYPE_STATIONARY,'Y','true','false'),DECODE(REQUEST_TYPE_HARDWARE,'Y','true','false'), PROJECT_DESC, BUSINESS_OBJ, RECOMMENDATION, BUSINESS_BENEFITS, OFFC2.EMP_FNAME||' '||OFFC2.EMP_MNAME||' '||OFFC2.EMP_LNAME, ASSET_INFORMATION, " 
					+" ACCOUNT, COMMENTS, SHIP_VIA, CEAR,RESPONSIBLE_PERSON, NON_INVNTY_STATUS ,TRACKING_NO , "
					+" COMPLETED_BY.EMP_TOKEN||'-'||COMPLETED_BY.EMP_FNAME||' '||COMPLETED_BY.EMP_MNAME||' '||COMPLETED_BY.EMP_LNAME, " 
					+" TO_CHAR(COMPLETED_ON,'DD-MM-YYYY HH24:MI'), SHIPPING_STATE, SHIPPING_COUNTRY, IMPRINT_STATE, ADDITIONAL_STATE, " 
					+" SUGG_VENDOR_NAME, SUGG_VENDOR_PHONE_NO, SUGG_VENDOR_EMAIL_ID, SUGG_VENDOR_SHIP_VIA, ASI_VENDOR_ID, NON_EMP_DEPT, "
					+" NON_INVNTY_FILE_UPLOAD, NON_INVNTY_PPO, NON_INVNTY_PPO_NO, NON_INVNTY_SHIPPING_PHONE " 
					+" FROM HRMS_D1_NON_INVENTORY_PURCHASE"
					+" INNER JOIN HRMS_EMP_OFFC OFFC ON(OFFC.EMP_ID=HRMS_D1_NON_INVENTORY_PURCHASE.NON_INVNTY_EMP_ID)"
					+" LEFT JOIN HRMS_EMP_OFFC OFFC1 ON(OFFC1.EMP_ID=HRMS_D1_NON_INVENTORY_PURCHASE.CHANGE_MANG_CODE)"
					+" LEFT JOIN HRMS_EMP_ADDRESS ADD1 ON(ADD1.EMP_ID=OFFC.EMP_ID AND  ADD1.ADD_TYPE='P')"
					+" LEFT JOIN HRMS_EMP_OFFC OFFC2 ON(OFFC2.EMP_ID=HRMS_D1_NON_INVENTORY_PURCHASE.RESPONSIBLE_PERSON) "
					+" LEFT JOIN HRMS_EMP_OFFC COMPLETED_BY ON(COMPLETED_BY.EMP_ID = HRMS_D1_NON_INVENTORY_PURCHASE.COMPLETED_BY)        "	
					+" LEFT JOIN HRMS_D1_IMPRINT_TYPE ON(HRMS_D1_IMPRINT_TYPE .IMPRINT_ID=HRMS_D1_NON_INVENTORY_PURCHASE.IMPRINT_TYPE)"	
					+" WHERE NON_PURCHASE_ID = " + bean.getNonInventoryCode();
		Object[][]data=getSqlModel().getSingleResult(query);
		if(data !=null && data.length>0){
			bean.setNonInventoryCode(checkNull(String.valueOf(data[0][0])));
			//APPLICATION DATE
			bean.setEmployeeCode(checkNull(String.valueOf(data[0][2])));
			bean.setEmployeeToken(checkNull(String.valueOf(data[0][3])));
			bean.setEmployeeName(checkNull(String.valueOf(data[0][4])));
			bean.setPreparerPhone(checkNull(String.valueOf(data[0][5])));
			bean.setPreparerFax(checkNull(String.valueOf(data[0][6])));	 
			bean.setExtension(checkNull(String.valueOf(data[0][7]))); 
			// START:Shiping Infomation
			bean.setAddress(checkNull(String.valueOf(data[0][8])));
			bean.setCityId(checkNull(String.valueOf(data[0][9])));
			//String[]result=getState(String.valueOf(data[0][9]));
			
			bean.setCityName(checkNull(String.valueOf(data[0][9])));//#####
			bean.setStateName(checkNull(String.valueOf(data[0][59])));//#####
			bean.setCountry(checkNull(String.valueOf(data[0][60])));//#####
			
			bean.setZip(checkNull(String.valueOf(data[0][10])));
			bean.setAttn(checkNull(String.valueOf(data[0][11])));
			// END:Shiping Infomation
			// START:Imprint Infomation
			 bean.setImprintId(checkNull(String.valueOf(data[0][12])));
			 bean.setImprintType(checkNull(String.valueOf(data[0][13])));
			bean.setImprintName(checkNull(String.valueOf(data[0][14])));
			 bean.setCompanyName(checkNull(String.valueOf(data[0][15])));
			bean.setTitle(checkNull(String.valueOf(data[0][16])));
			 bean.setCompanyAddress(checkNull(String.valueOf(data[0][17])));
			bean.setCompCityNameCode(checkNull(String.valueOf(data[0][18])));
			//String[]result1=getState(String.valueOf(data[0][18]));
			//if(result1 !=null && result1.length>0){
			bean.setCompCityName(checkNull(String.valueOf(data[0][18])));//#####
			bean.setCompanyState(checkNull(String.valueOf(data[0][61])));//#####
			//}
			 bean.setCompanyZip(checkNull(String.valueOf(data[0][19])));
			bean.setCompPhoneNumber(checkNull(String.valueOf(data[0][20])));
			 bean.setCompFaxNumber(checkNull(String.valueOf(data[0][21])));
			bean.setCompOtherNumber(checkNull(String.valueOf(data[0][22])));
			 bean.setCompDescription(checkNull(String.valueOf(data[0][23])));
			bean.setInternet(checkNull(String.valueOf(data[0][24])));
			// END:Imprint Infomation
			
			
			/*// Description # of Lots Description # of Lots
			 bean.setBusinessCardsRegular(checkNull(String.valueOf(data[0][25])));
			 bean.setEnvelope10x13(checkNull(String.valueOf(data[0][26])));
			 bean.setBusinessCardsCne(checkNull(String.valueOf(data[0][27])));
			 bean.setLetterheadRegular(checkNull(String.valueOf(data[0][28])));
			 bean.setBusinessCardsLogo(checkNull(String.valueOf(data[0][29])));
			 bean.setLetterheadManager(checkNull(String.valueOf(data[0][30])));
			 bean.setEnvelopeRegular(checkNull(String.valueOf(data[0][31])));
			 bean.setMemoPads(checkNull(String.valueOf(data[0][32])));
			bean.setEnvelopeWindow(checkNull(String.valueOf(data[0][33])));
			bean.setMemoLoose(checkNull(String.valueOf(data[0][34])));
			 bean.setEnvelope12Window(checkNull(String.valueOf(data[0][35])));
			 bean.setLabelFrom(checkNull(String.valueOf(data[0][36])));
			 bean.setEnvelope9x12(checkNull(String.valueOf(data[0][37])));
			 bean.setLabelFromTo(checkNull(String.valueOf(data[0][38])));
			 bean.setLetterheadLogo(checkNull(String.valueOf(data[0][39])));
			 bean.setMemoManager(checkNull(String.valueOf(data[0][40])));*/
			// Description # of Lots Description # of Lots
			// Additional Infomation
			 bean.setAdditionalName(checkNull(String.valueOf(data[0][25])));
			 bean.setAdditionalCompanyName(checkNull(String.valueOf(data[0][26])));
			 bean.setAdditionalTitle(checkNull(String.valueOf(data[0][27])));
			 bean.setAdditionalAddress(checkNull(String.valueOf(data[0][28])));
			 bean.setAdditionalCityCode(checkNull(String.valueOf(data[0][29])));
			// String[]result2=getState(String.valueOf(data[0][29]));
				//if(result2 !=null && result2.length>0){
			 bean.setAdditionalCity(checkNull(String.valueOf(data[0][29])));//#####
			 bean.setAdditionalState(checkNull(String.valueOf(data[0][62])));//#####
			//	}
			 bean.setAdditionalZip(checkNull(String.valueOf(data[0][30])));
			 bean.setAdditionalPhoneNumber(checkNull(String.valueOf(data[0][31])));
			 bean.setAdditionalFax(checkNull(String.valueOf(data[0][32])));
			 bean.setAdditionalOtherNumber(checkNull(String.valueOf(data[0][33])));
			 bean.setAdditionalDesc(checkNull(String.valueOf(data[0][34])));
			 bean.setAdditionalInternet(checkNull(String.valueOf(data[0][35])));
			 bean.setApproverToken(checkNull(String.valueOf(data[0][36])));
			 bean.setSelectapproverName(checkNull(String.valueOf(data[0][37])));
			 bean.setChangeApproverCode(checkNull(String.valueOf(data[0][38])));
			 
			 
			  bean.setShipTo(checkNull(String.valueOf(data[0][39])));
			 bean.setASLMPS(checkNull(String.valueOf(data[0][40])));
			 bean.setHasWrittenQuote(checkNull(String.valueOf(data[0][41])));
			 bean.setStationary(checkNull(String.valueOf(data[0][42])));
			 bean.setStationaryHidden(checkNull(String.valueOf(data[0][42])));
			 bean.setHardwareSoftware(checkNull(String.valueOf(data[0][43])));
			 bean.setHardwareSoftwareHidden(checkNull(String.valueOf(data[0][43])));
			//PROJECT INFORMATION
			 bean.setProjectDesc(checkNull(String.valueOf(data[0][44])));
			  bean.setBusinessObj(checkNull(String.valueOf(data[0][45])));
			 bean.setRecommendations(checkNull(String.valueOf(data[0][46])));
			 bean.setBusinessBenefits(checkNull(String.valueOf(data[0][47])));
			 bean.setResposiblePersonName(checkNull(String.valueOf(data[0][48])));
			 bean.setAssetInformation(checkNull(String.valueOf(data[0][49])));
			 bean.setAccount(checkNull(String.valueOf(data[0][50])));
			 bean.setComment(checkNull(String.valueOf(data[0][51])));
			bean.setShipVia(checkNull(String.valueOf(data[0][52])));
			 bean.setCear(checkNull(String.valueOf(data[0][53])));
			 bean.setResposiblePersonCode(checkNull(String.valueOf(data[0][54])));
			 bean.setStatus(checkNull(String.valueOf(data[0][55])));
			 bean.setFileheaderName(checkNull(String.valueOf(data[0][56])));
			 bean.setCompletedBy(checkNull(String.valueOf(data[0][57])));
			 bean.setCompletedOn(checkNull(String.valueOf(data[0][58])));
			 bean.setVendorName(checkNull(String.valueOf(data[0][63])));
			 bean.setVendorPhoneNo(checkNull(String.valueOf(data[0][64])));
			 bean.setVendorEmailId(checkNull(String.valueOf(data[0][65])));
			 bean.setVendorShipVia(checkNull(String.valueOf(data[0][66])));
			 bean.setAsiVendorID(checkNull(String.valueOf(data[0][67])));
			 bean.setDepartment(checkNull(String.valueOf(data[0][68])));
			 bean.setUploadFileName(checkNull(String.valueOf(data[0][69])));
			 bean.setPpo(checkNull(String.valueOf(data[0][70])));
			 bean.setPpoNo(checkNull(String.valueOf(data[0][71])));
			 bean.setShippingPhoneNumber(checkNull(String.valueOf(data[0][72])));
		}
		
		/**
		 * SET BUSINESS 
		 */
		String businessQuery="SELECT HRMS_D1_STATIONARY.STATIONARY_ID,STATIONARY_NAME,BUSINESS_NO_LOTS FROM HRMS_D1_NON_INVENT_BUSINESS INNER JOIN HRMS_D1_STATIONARY ON(HRMS_D1_STATIONARY.STATIONARY_ID=HRMS_D1_NON_INVENT_BUSINESS.BUSINESS_ID) "
							+"	WHERE NON_PURCHASE_ID="+bean.getNonInventoryCode();
		Object[][]obj=getSqlModel().getSingleResult(businessQuery);
		ArrayList list=new ArrayList();
		bean.setSelectList(null);	
		if(obj !=null && obj.length>0){
			for (int i = 0; i < obj.length; i++) {
				NonInventoryPurchaseBean bean2=new NonInventoryPurchaseBean();
				bean2.setIttselectTypeCode(String.valueOf(obj[i][0]));
				bean2.setIttselectTypeName(String.valueOf(obj[i][1]));
				bean2.setIttnoOfLots(String.valueOf(obj[i][2]));			
				list.add(bean2);
			}
			bean.setHiddenValue("");			
			bean.setSelectList(list);
			}
		
		/**
		 * SET PRODUCT INFORMATION
		 */
		String queryProduct="SELECT  PRODUCT_QTY, PRODUCT_UNIT, PRODUCT_PRIZE, PRODUCT_MANUFACTURE, PRODUCT_MRP_PART, PRODUCT_VENDOR_PART, PRODUCT_DESCRIPTION, PRODUCT_CODE, NON_PURCHASE_ID FROM HRMS_D1_NON_INVENT_PRODUCT_INF WHERE NON_PURCHASE_ID="+bean.getNonInventoryCode()+" ORDER BY PRODUCT_CODE";
		Object[][]objProduct=getSqlModel().getSingleResult(queryProduct);
		ArrayList listProduct =new ArrayList();
		bean.setProductList(null);
		if(objProduct !=null && objProduct.length>0){
			for (int i = 0; i < objProduct.length; i++) {
				NonInventoryPurchaseBean bean1=new NonInventoryPurchaseBean();
				bean1.setIttqty(checkNull(String.valueOf(objProduct[i][0])));
				bean1.setIttunit(checkNull(String.valueOf(objProduct[i][1])));
				bean1.setIttprice(checkNull(String.valueOf(objProduct[i][2])));
				bean1.setIttmanufacture(checkNull(String.valueOf(objProduct[i][3])));
				bean1.setIttmrfPart(checkNull(String.valueOf(objProduct[i][4])));
				bean1.setIttvendorPart(checkNull(String.valueOf(objProduct[i][5])));
				bean1.setIttdesc(checkNull(String.valueOf(objProduct[i][6])));							
				listProduct.add(bean1);
			}
		}				
		bean.setProductList(listProduct);
		
		
		
				
		return false;
	}

	
	public void setApproverData(NonInventoryPurchaseBean bean,
			Object[][] empFlow) {

		Object[][] approverDataObj = new Object[empFlow.length][2];
		for (int i = 0; i < empFlow.length; i++) {
			approverDataObj[i][0] = "";
			String selectQuery = "  SELECT NVL(HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
					+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' ') ,' '||'('||HRMS_RANK.RANK_NAME||')',HRMS_EMP_OFFC.EMP_ID "
					+ "  FROM HRMS_EMP_OFFC "
					+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
					+ " WHERE EMP_ID IN(" + empFlow[i][0] + ")";

			Object temObj[][] = getSqlModel().getSingleResult(selectQuery);
			if (temObj != null && temObj.length > 0) {				
				approverDataObj[i][0] = checkNull(String.valueOf(temObj[0][0]));
				approverDataObj[i][1] = checkNull(String.valueOf(temObj[0][2]));
			}
		}
		bean.setApproverList(null);
		if (approverDataObj != null && approverDataObj.length > 0) {
			ArrayList arrList = new ArrayList();
			for (int i = 0; i < approverDataObj.length; i++) {
				Regularization leaveBean = new Regularization();
				leaveBean
						.setApproverName(String.valueOf(approverDataObj[i][0]));
				String srNo = i + 1 + getOrdinalFor(i + 1) + "-Approver";
				leaveBean.setApprSrNo(srNo);
				leaveBean
						.setApproverCode(String.valueOf(approverDataObj[i][1]));
				arrList.add(leaveBean);
			}
			bean.setApproverList(arrList);
			bean.setCheckData(String.valueOf(arrList.size()));
		}
		else{
			bean.setCheckData("0");
		}
	}
	

public String checkNull(String result) {
	if (result == null || result.equals("null")) {
		return "";
	}// end of if
	else {
		return result;
	}// end of else
}
public String getOrdinalFor(int value) {
	int hundredRemainder = value % 100;

	if (hundredRemainder >= 10 && hundredRemainder <= 20) {
		return "th";
	} // end of if

	int tenRemainder = value % 10;

	switch (tenRemainder) {
	case 1:
		return "st";
	case 2:
		return "nd";
	case 3:
		return "rd";
	default:
		return "th";
	} // end of switch
}

public boolean addSelectTYpe(NonInventoryPurchaseBean bean,
		String[] ittselectTypeCode, String[] ittselectTypeName,
		String[] ittnoOfLots,String add) {
	
	ArrayList list =new ArrayList();
	bean.setSelectList(null);
	if(ittselectTypeCode !=null && ittselectTypeCode.length>0){
		for (int i = 0; i < ittselectTypeCode.length; i++) {
			if(add.equals("remove") && !bean.getHiddenValue().equals(String.valueOf(i))){
			NonInventoryPurchaseBean bean1=new NonInventoryPurchaseBean();
			bean1.setIttselectTypeCode(ittselectTypeCode[i]);
			bean1.setIttselectTypeName(ittselectTypeName[i]);
			bean1.setIttnoOfLots(ittnoOfLots[i]);			
			list.add(bean1);
			}
			else if(add.equals("")||add.equals("add")) {
				NonInventoryPurchaseBean bean1=new NonInventoryPurchaseBean();
				bean1.setIttselectTypeCode(ittselectTypeCode[i]);
				bean1.setIttselectTypeName(ittselectTypeName[i]);
				bean1.setIttnoOfLots(ittnoOfLots[i]);			
				list.add(bean1);
			}
		}
	}
	if(add.equals("add")){
	NonInventoryPurchaseBean bean2=new NonInventoryPurchaseBean();
	bean2.setIttselectTypeCode(bean.getSelectTypeCode());
	bean2.setIttselectTypeName(bean.getSelectTypeName());
	bean2.setIttnoOfLots(bean.getNoOfLots());			
	list.add(bean2);
	}
	bean.setHiddenValue("");
	
	bean.setSelectList(list);
	return false;
}
/**
 * 
 * @param cityCode
 * @return
 * result[0]=cityName
 * result[1]=stateName
 * result[2]=CountryName
 */
public String[] getState(String cityCode)  {
	String[] result=new String[3];
		String query = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE , (SELECT LOCATION_NAME FROM HRMS_LOCATION WHERE LOCATION_CODE ="+cityCode+")  FROM HRMS_LOCATION "
				+ " WHERE LOCATION_CODE = (select LOCATION_PARENT_CODE from HRMS_LOCATION where LOCATION_CODE ="
				+cityCode + ") ";
		Object[][] stateCode = getSqlModel().getSingleResult(query);
		if (stateCode.length > 0) {
			result[0]=String.valueOf(stateCode[0][3]);
			result[1]=String.valueOf(stateCode[0][1]);
			//bean.setStateName((String.valueOf(stateCode[0][1])));
			String coutryQuery = "SELECT LOCATION_CODE, LOCATION_NAME, LOCATION_PARENT_CODE FROM HRMS_LOCATION WHERE LOCATION_CODE = "
					+ stateCode[0][2] + " ";
			Object[][] countryName = getSqlModel().getSingleResult(coutryQuery);
			if (countryName.length > 0) {
				//bean.setCountry((String.valueOf(countryName[0][1])));
				result[2]=String.valueOf(countryName[0][1]);
			}// end of nested if
			
		}// end of if
	return result;
}


	/*
	 * 
	 * 
	 * 
	 */

	public String getApproverComments(NonInventoryPurchaseBean bean){
		
		String qq="SELECT EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,NON_INVENTORY_COMMENTS,TO_CHAR(NON_INVENTORY_DATE,'DD-MM-YYYY'),DECODE(NON_INVENTORY_STATUS,'A','Approved','R','Reject','B','Send Back','P','Pending','Z','Pending for Resubmit','L','Forwarded ') FROM HRMS_D1_NON_INVENTORY_PATH " 
					+"	INNER JOIN HRMS_EMP_OFFC ON(HRMS_D1_NON_INVENTORY_PATH.NON_INVENTORY_APPROVER=HRMS_EMP_OFFC.EMP_ID) "
					+"	WHERE HRMS_D1_NON_INVENTORY_PATH .NON_INVENTORY_ID="+bean.getNonInventoryCode()+" ORDER BY NON_INVENTORY_PATH_ID";	
		Object[][]data=getSqlModel().getSingleResult(qq);
		ArrayList list =new ArrayList();
		if(data !=null && data.length>0){
			for (int i = 0; i < data.length; i++) {
				NonInventoryPurchaseBean bean1=new NonInventoryPurchaseBean();
				bean1.setIttApproverName(checkNull(String.valueOf(data[i][0])));
				bean1.setIttComments(checkNull(String.valueOf(data[i][1])));
				bean1.setIttApplicationDate(checkNull(String.valueOf(data[i][2])));
				bean1.setIttStatus(checkNull(String.valueOf(data[i][3])));				
				list.add(bean1);
			}	
			bean.setListComment(list);
		}		
		return "";
	}

	public void setGeneralData(NonInventoryPurchaseBean bean,
			HttpServletRequest request) {
		String query="SELECT OFFC.EMP_TOKEN,OFFC.EMP_FNAME||' '||OFFC.EMP_MNAME||' '||OFFC.EMP_LNAME,NVL(ADD_PH1,' '),"
						+"	NVL(ADD_FAX,' '), OFFC.EMP_ID, ADD_EXTENSION ,DEPT_NAME||' - '||DEPT_ABBR  "
						+"	FROM HRMS_EMP_OFFC OFFC  	LEFT JOIN HRMS_EMP_ADDRESS ADD1 ON(ADD1.EMP_ID=OFFC.EMP_ID AND  ADD1.ADD_TYPE='P') "
						+"	LEFT JOIN HRMS_EMP_OFFC MGR ON(MGR.EMP_ID = OFFC.EMP_REPORTING_OFFICER) "
						+"  LEFT JOIN HRMS_DEPT ON(HRMS_DEPT.DEPT_ID=OFFC.EMP_DEPT)  "
						+" WHERE OFFC.EMP_ID ="+bean.getUserEmpId();
		Object[][]data=getSqlModel().getSingleResult(query);
		//String[] fieldNames = { "employeeToken", "employeeName" ,"preparerPhone","preparerFax","employeeCode"};
		if(data !=null && data.length>0){			
			bean.setEmployeeToken(checkNull(String.valueOf(data[0][0])));
			bean.setEmployeeName(checkNull(String.valueOf(data[0][1])));
			bean.setPreparerPhone(checkNull(String.valueOf(data[0][2])));
			bean.setPreparerFax(checkNull(String.valueOf(data[0][3])));
			bean.setEmployeeCode(checkNull(String.valueOf(data[0][4])));
			bean.setExtension(checkNull(String.valueOf(data[0][5])));
			bean.setDepartment(checkNull(String.valueOf(data[0][6])));
		}
		
			
	}

	public boolean addToList(NonInventoryPurchaseBean bean, String[] ittqty,
			String[] ittunit, String[] ittprice, String[] ittmanufacture,
			String[] ittmrfPart, String[] ittvendorPart, String[] ittcomment,
			String add) {
		
		ArrayList list =new ArrayList();
		bean.setProductList(null);
		if(ittqty !=null && ittqty.length>0){
			for (int i = 0; i < ittqty.length; i++) {
				if(add.equals("remove") && !bean.getHiddenValue().equals(String.valueOf(i))){
				NonInventoryPurchaseBean bean1=new NonInventoryPurchaseBean();
				bean1.setIttqty(ittqty[i]);
				bean1.setIttunit(ittunit[i]);
				bean1.setIttprice(ittprice[i]);
				bean1.setIttmanufacture(ittmanufacture[i]);
				bean1.setIttmrfPart(ittmrfPart[i]);
				bean1.setIttvendorPart(ittvendorPart[i]);
				bean1.setIttdesc(ittcomment[i]);							
				list.add(bean1);
				}
				else if(add.equals("")||add.equals("add")) {
					NonInventoryPurchaseBean bean1=new NonInventoryPurchaseBean();
					bean1.setIttqty(ittqty[i]);
					bean1.setIttunit(ittunit[i]);
					bean1.setIttprice(ittprice[i]);
					bean1.setIttmanufacture(ittmanufacture[i]);
					bean1.setIttmrfPart(ittmrfPart[i]);
					bean1.setIttvendorPart(ittvendorPart[i]);
					bean1.setIttdesc(ittcomment[i]);		
					list.add(bean1);
				}
			}
		}
		if(add.equals("add")){
		NonInventoryPurchaseBean bean2=new NonInventoryPurchaseBean();
		bean2.setIttqty(bean.getQty());
		bean2.setIttunit(bean.getUnit());
		bean2.setIttprice(bean.getPrice());
		bean2.setIttmanufacture(bean.getManufacture());
		bean2.setIttmrfPart(bean.getMrfPart());
		bean2.setIttvendorPart(bean.getVendorPart());
		bean2.setIttdesc(bean.getDesc());			
		list.add(bean2);
		}
		bean.setHiddenValue("");
		
		bean.setProductList(list);
		return false;
}
	private String getTrackingNo(String requestId,String appl) {
		String trackingNo = "";

		if(requestId.length() < 4) {
			int remChars = 4 - requestId.length();

			for(int i = 0; i < remChars; i++) {
				requestId = "0" + requestId;
			}
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		
		String strDay = day < 10 ? "0" + day : String.valueOf(day);
		String strMonth = month < 10 ? "0" + month : String.valueOf(month);
		String strYear = String.valueOf(year);

		trackingNo = appl + strYear + strMonth + strDay + "-" + requestId;
		
		return trackingNo;
	}
	
	public void setCompletedBy(NonInventoryPurchaseBean bean) {
		String query="SELECT EMP_TOKEN||'-'||EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,TO_CHAR(SYSDATE,'DD-MM-YYYY HH24:MI') FROM HRMS_EMP_OFFC WHERE EMP_ID="+bean.getUserEmpId();
		Object[][]obj=getSqlModel().getSingleResult(query);
		if(obj !=null && obj.length>0){
			bean.setCompletedBy(String.valueOf(obj[0][0]));
			bean.setCompletedOn(String.valueOf(obj[0][1]));
		}		
	}
}



