package org.paradyne.model.Asset;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Asset.AssetEmployee;
import org.paradyne.bean.leave.LeaveApplication;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;
import org.paradyne.model.ApplicationStudio.ProcessManagerAlertsModel;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.paradyne.model.mypage.MypageProcessManagerAlertsModel;

/**
 * @author Mangesh Jadhav 05-08-2008 AssetEmployeeModel class to write business
 *         logic to save, update, delete and view any asset application, asset
 *         assignment, asset request to other warehouse
 * @modified Ayyappa
 */
public class AssetEmployeeModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.paradyne.model.Asset.AssetEmployeeModel.class);

	String testCode = "";
	Object[][] to_mailIds = new Object[1][1];
	Object[][] from_mailIds = new Object[1][1];
	String mailMsg = "";

	/*
	 * public void showRecord(AssetEmployee assetEmployee, HttpServletRequest
	 * request,int k) { try{
	 * 
	 * String query = "SELECT HRMS_ASST_ASSMT.ASST_CODE,ASST_INCODE
	 * ,ASSET_CATEGORY_NAME
	 * ,TO_CHAR(ASSIGN_DATE,'DD-MM-YYYY'),TO_CHAR(RETURN_DATE,'DD-MM-YYYY')," +"
	 * RETURN_FLAG,HRMS_ASST_ASSMT.ASSMT_CODE" + " FROM HRMS_ASSMT" +" INNER
	 * JOIN HRMS_ASST_ASSMT
	 * ON(HRMS_ASST_ASSMT.ASSMT_CODE=HRMS_ASSMT.ASSMT_CODE)" + " INNER JOIN
	 * HRMS_ASST_MT ON(HRMS_ASST_MT.ASST_CODE=HRMS_ASST_ASSMT.ASST_CODE)" +"
	 * INNER JOIN HRMS_ASSET_CATEGORY
	 * ON(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASST_MT.ASST_HD_CODE) " +"
	 * WHERE HRMS_ASSMT.EMP_CODE="+assetEmployee.getEmpCode()+" AND
	 * RETURN_FLAG='N' ORDER BY ASSIGN_DATE";
	 * 
	 * Object data[][] = getSqlModel().getSingleResult(query); ArrayList<Object>
	 * list=new ArrayList<Object>(); Map checkMap = new HashMap();
	 * 
	 * for(int i=0 ;i <data.length;i++) { AssetEmployee bean1=new
	 * AssetEmployee(); bean1.setAssetCode(String.valueOf(data[i][0]));
	 * bean1.setInvCode(String.valueOf(data[i][1]));
	 * bean1.setAsstHdType(checkNull(String.valueOf(data[i][2])));
	 * bean1.setAssignDate(checkNull(String.valueOf(data[i][3])));
	 * bean1.setReturnDate(checkNull(String.valueOf(data[i][4])));
	 * bean1.setSrNo(String.valueOf(i+1));
	 * bean1.setCheckFlag(String.valueOf(data[i][5]));
	 * if(bean1.getCheckFlag().equals("Y")){ bean1.setChkFlag("true");
	 * checkMap.put(""+i,"Y"); }else{ bean1.setChkFlag("false");
	 * checkMap.put(""+i,"N"); } //checkMap.put(""+i, "Y"); list.add(bean1);
	 * if(k==1){ assetEmployee.setChkCode("true"); }else
	 * assetEmployee.setChkCode("false"); } assetEmployee.setList(list);
	 * request.setAttribute("data",checkMap);
	 * assetEmployee.setTableLength(String.valueOf(assetEmployee.getList().size()));
	 * assetEmployee.setCode(String.valueOf(data[0][6])); }catch (Exception e) { //
	 * TODO: handle exception } }
	 */

	/*
	 * method name : setApproverComments purpose : show the previous approver
	 * comments to the last approver in view details return type : void
	 * parameter : AssetEmployee instance
	 */

	public void setApproverComments(AssetEmployee bean) {
		Object[] applCode = new Object[1];
		applCode[0] = bean.getCode();
		/*
		 * get the approver details from database & sets it in the list
		 * 
		 */
		try {
			Object[][] approverData = getSqlModel().getSingleResult(
					getQuery(11), applCode);
			ArrayList<Object> list = new ArrayList<Object>();
			// logger.info("Before retriving data");
			// logger.info("assetEmployee.getIsApprove() ------
			// "+bean.getIsApprove());
			// logger.info("assetEmployee.getIsSentBack() -----
			// "+bean.getIsSentBack());
			// logger.info("assetEmployee.getCommentFlag() ----
			// "+bean.getCommentFlag());
			// logger.info("after retriving data");
			if (approverData != null && approverData.length > 0) {
				logger.info("Approver comments Data Length ========= "
						+ approverData.length);
				for (int i = 0; i < approverData.length; i++) {
					AssetEmployee bean1 = new AssetEmployee();
					bean1.setApproverName(checkNull(String
							.valueOf(approverData[i][0])));
					bean1.setApproverComment(checkNull(String
							.valueOf(approverData[i][1])));
					bean1.setApprovedDate(checkNull(String
							.valueOf(approverData[i][2])));
					bean1.setApproveStatus(checkNull(String
							.valueOf(approverData[i][3])));
					list.add(bean1);

				}
				bean.setIsApprove("true");
				bean.setCommentFlag("true");
			} // end of if
			// else
			// bean.setCommentFlag("true");
			logger.info("comment flag==" + bean.getCommentFlag());

			bean.setApprList(list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception in setApproverComments()==" + e);
		}
	}

	/*
	 * method name : showRecord purpose : show the asset application details on
	 * the screen. return type : void parameter : AssetEmployee instance,
	 * HttpServletRequest instance
	 */

	public String showRecord(AssetEmployee assetEmployee,
			HttpServletRequest request) {
		
		String isKeepInfoLogin ="N";
		try {
			String statusQuery = "SELECT CASE WHEN ASSET_STATUS='P' AND ASSET_APPL_LEVEL!=1   THEN 'F' ELSE ASSET_STATUS  END, "
					+ " NVL(ASSET_COMMENTS,''),ASSET_APPL_APPROVER,NVL(ASSET_ASSIGN_COMMENTS,''),ASSET_APPL_NO,ASSET_EMP_ID,TO_CHAR(ASSET_APPL_DATE,'DD-MM-YYYY') "
					+ " FROM HRMS_ASSET_APPLICATION WHERE ASSET_APPL_CODE="
					+ assetEmployee.getCode();
			Object status[][] = getSqlModel().getSingleResult(statusQuery);

			assetEmployee.setStatus(String.valueOf(status[0][0]));
			assetEmployee.setHiddenStatus(String.valueOf(status[0][0]));
			assetEmployee.setComments(checkNull(String.valueOf(status[0][1])));
			assetEmployee.setApproverCode(String.valueOf(status[0][2]));
			assetEmployee.setAppl_No(checkNull(String.valueOf(status[0][4])));
			assetEmployee.setEmpCode(String.valueOf(status[0][5]));
			assetEmployee.setAssignDate1(String.valueOf(status[0][6]));
			if (checkNull(String.valueOf(status[0][3])).equals("")
					&& !assetEmployee.getStatus().equals("S")) {
				assetEmployee.setAssignCommentsFlag("false");
			} else {
				assetEmployee.setAssignComments(checkNull(String
						.valueOf(status[0][3])));
				assetEmployee.setAssignCommentsFlag("true");
			}

			String query = "SELECT HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE,ASSET_CATEGORY_NAME, HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE,ASSET_SUBTYPE_NAME,ASSET_APPL_QTY,UNIT_NAME,   "
					+ " ASSET_ASSIGN_QTY,DECODE(ASSET_INVENTORY_TYPE,'S','false','I','true')FROM HRMS_ASSET_APPL_DETAILS "
					+ " LEFT JOIN HRMS_ASSET_CATEGORY ON (HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASSET_APPL_DETAILS.ASSET_CATEGORY_CODE) "
					+ " LEFT JOIN HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_APPL_DETAILS.ASSET_SUBTYPE_CODE) "
					+ " LEFT JOIN HRMS_UNIT_MASTER ON (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_UNIT = HRMS_UNIT_MASTER.UNIT_CODE) "
					+ " WHERE ASSET_APPL_CODE =" + assetEmployee.getCode();

			Object data[][] = getSqlModel().getSingleResult(query);
			ArrayList<Object> list = new ArrayList<Object>();

			/*
			 * sets the applied assets in the list
			 * 
			 */
			for (int i = 0; i < data.length; i++) {
				AssetEmployee bean1 = new AssetEmployee();
				bean1.setAssetCode(String.valueOf(data[i][0]));
				bean1.setAsstHdType(checkNull(String.valueOf(data[i][1])));
				bean1.setSubTypeCodeIterator(checkNull(String
						.valueOf(data[i][2])));
				bean1.setAssetSubTypeIterator(checkNull(String
						.valueOf(data[i][3])));
				bean1.setAssetRequiredIterator(checkNull(String
						.valueOf(data[i][4])));
				bean1
						.setAssetUnitIterator(checkNull(String
								.valueOf(data[i][5])));
				if (checkNull(String.valueOf(data[i][6])).equals("0")
						|| checkNull(String.valueOf(data[i][6])).equals("")) {
					bean1.setPartialAssignIt("false");
				} else {
					bean1.setPartialAssignIt("true");
				}
				bean1.setAssetInvTypeIterator(checkNull(String
						.valueOf(data[i][7])));
				bean1.setSrNo(String.valueOf(i + 1));

				list.add(bean1);

			} // end of for loop
			assetEmployee.setList(list);

			assetEmployee.setTableLength(String.valueOf(assetEmployee.getList()
					.size()));
			String keepInfoempId = "0";
			String sqlKeepinfo = "SELECT ASSET_KEEP_INFORMTO FROM HRMS_ASSET_APPLICATION WHERE ASSET_APPL_CODE  = "
					+ assetEmployee.getCode();
			Object keepInfoId[][] = getSqlModel().getSingleResult(sqlKeepinfo);
			if (keepInfoId != null && keepInfoId.length > 0) {
				keepInfoempId = String.valueOf(keepInfoId[0][0]);
			}
			String keepInfodata = "SELECT "
					+ "HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,EMP_ID "
					+ "FROM HRMS_EMP_OFFC " + "WHERE EMP_ID IN("
					+ keepInfoempId + ")";
			Object keepInfo[][] = getSqlModel().getSingleResult(keepInfodata);
			ArrayList keepInfolst = new ArrayList();
			if (keepInfo != null && keepInfo.length > 0) {

				for (int i = 0; i < keepInfo.length; i++) {
					AssetEmployee asstbean = new AssetEmployee();
					asstbean.setKeepInformedEmpName(String
							.valueOf(keepInfo[i][0]));
					asstbean.setKeepInformedEmpId(String
							.valueOf(keepInfo[i][1]));

					if(assetEmployee.getUserEmpId().equals(asstbean.getKeepInformedEmpId()))
					{
						isKeepInfoLogin ="Y";
					}
					
					keepInfolst.add(asstbean);
				}
			}
			assetEmployee.setKeepInformedList(keepInfolst);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception in showRecord()==" + e);
		}
		
		return isKeepInfoLogin ;
	}

	public void showAssignedRecord(AssetEmployee assetEmployee,
			HttpServletRequest request) {
		try {
			String statusQuery = "SELECT CASE WHEN ASSET_STATUS='P' AND ASSET_APPL_LEVEL!=1   THEN 'F' ELSE ASSET_STATUS  END, "
					+ " NVL(ASSET_COMMENTS,''),ASSET_APPL_APPROVER,NVL(ASSET_ASSIGN_COMMENTS,'') FROM HRMS_ASSET_APPLICATION WHERE ASSET_APPL_CODE="
					+ assetEmployee.getCode();
			Object status[][] = getSqlModel().getSingleResult(statusQuery);

			assetEmployee.setStatus(String.valueOf(status[0][0]));
			assetEmployee.setHiddenStatus(String.valueOf(status[0][0]));
			assetEmployee.setComments(checkNull(String.valueOf(status[0][1])));
			assetEmployee.setApproverCode(String.valueOf(status[0][2]));
			if (checkNull(String.valueOf(status[0][3])).equals("")) {
				assetEmployee.setAssignCommentsFlag("false");
			} else {
				assetEmployee.setAssignComments(checkNull(String
						.valueOf(status[0][3])));
				assetEmployee.setAssignCommentsFlag("true");
			}

			String query = "SELECT HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE,ASSET_CATEGORY_NAME, HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE,ASSET_SUBTYPE_NAME,ASSET_APPL_QTY,UNIT_NAME,   "
					+ " ASSET_ASSIGN_QTY,(ASSET_APPL_QTY-ASSET_ASSIGN_QTY) AS UNASSIGNED,DECODE(ASSET_INVENTORY_TYPE,'S','false','I','true') FROM HRMS_ASSET_APPL_DETAILS "
					+ " LEFT JOIN HRMS_ASSET_CATEGORY ON (HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASSET_APPL_DETAILS.ASSET_CATEGORY_CODE) "
					+ " LEFT JOIN HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_APPL_DETAILS.ASSET_SUBTYPE_CODE) "
					+ " LEFT JOIN HRMS_UNIT_MASTER ON (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_UNIT = HRMS_UNIT_MASTER.UNIT_CODE) "
					+ " WHERE ASSET_APPL_CODE =" + assetEmployee.getCode();

			Object data[][] = getSqlModel().getSingleResult(query);
			ArrayList<Object> assignedlist = new ArrayList<Object>();
			ArrayList<Object> unassignedlist = new ArrayList<Object>();

			/*
			 * sets the applied assets in the list
			 * 
			 */
			for (int i = 0; i < data.length; i++) {
				AssetEmployee beanUnassigned = new AssetEmployee();
				AssetEmployee beanAssigned = new AssetEmployee();
				if (!(checkNull(String.valueOf(data[i][6])).equals("0") || checkNull(
						String.valueOf(data[i][6])).equals(""))) {
					beanAssigned.setAssetCodeAssigned(String
							.valueOf(data[i][0]));
					beanAssigned.setAsstHdTypeAssigned(checkNull(String
							.valueOf(data[i][1])));
					beanAssigned
							.setSubTypeCodeIteratorAssigned(checkNull(String
									.valueOf(data[i][2])));
					beanAssigned
							.setAssetSubTypeIteratorAssigned(checkNull(String
									.valueOf(data[i][3])));
					beanAssigned.setAssetAssignedIterator(checkNull(String
							.valueOf(data[i][6])));
					beanAssigned.setAssetUnitIteratorAssigned(checkNull(String
							.valueOf(data[i][5])));

					assignedlist.add(beanAssigned);
				}
				if (!(checkNull(String.valueOf(data[i][7])).equals("0") || checkNull(
						String.valueOf(data[i][6])).equals(""))) {
					beanUnassigned.setAssetCode(String.valueOf(data[i][0]));
					beanUnassigned.setAsstHdType(checkNull(String
							.valueOf(data[i][1])));
					beanUnassigned.setSubTypeCodeIterator(checkNull(String
							.valueOf(data[i][2])));
					beanUnassigned.setAssetSubTypeIterator(checkNull(String
							.valueOf(data[i][3])));
					beanUnassigned.setAssetRequiredIterator(checkNull(String
							.valueOf(data[i][7])));
					beanUnassigned.setAssetUnitIterator(checkNull(String
							.valueOf(data[i][5])));
					beanUnassigned.setAssetInvTypeIterator(checkNull(String
							.valueOf(data[i][8])));
					unassignedlist.add(beanUnassigned);
				}

				beanUnassigned.setSrNo(String.valueOf(i + 1));

			} // end of for loop
			assetEmployee.setAssignedAssetList(assignedlist);
			assetEmployee.setList(unassignedlist);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception in showRecord()==" + e);
		}
	}

	/*
	 * method name : editItem purpose : set applied asset list after editing
	 * particular asset return type : void parameter : AssetEmployee
	 * instance,String []categoryCode,String []categoryName, String
	 * []subTypeCode,String []subTypeName,String []quantityReq
	 */
	public void editItem(AssetEmployee bean, String[] categoryCode,
			String[] categoryName, String[] subTypeCode, String[] subTypeName,
			String[] quantityReq, String[] assetUnit, String[] partialIt,
			String[] invType) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		try {
			for (int i = 0; i < quantityReq.length; i++) {

				if ((i + 1 != Integer.parseInt(bean.getParaId()))) {
					AssetEmployee bean1 = new AssetEmployee();
					bean1.setSrNo(String.valueOf(i + 1));
					bean1.setAssetCode(categoryCode[i]);
					bean1.setAsstHdType(categoryName[i]);
					bean1.setSubTypeCodeIterator(subTypeCode[i]);
					bean1.setAssetSubTypeIterator(subTypeName[i]);
					bean1.setAssetRequiredIterator(quantityReq[i]);
					bean1.setAssetUnitIterator(assetUnit[i]);
					bean1.setPartialAssignIt(partialIt[i]);
					bean1.setAssetInvTypeIterator(invType[i]);
					tableList.add(bean1);
				} // end of if
				else {
					bean.setSrNo(bean.getParaId());
					bean.setAssetCode(bean.getAsstCode1());
					bean.setAsstHdType(bean.getAsstHdType1());
					bean.setSubTypeCodeIterator(bean.getSubTypeCode());
					bean.setAssetSubTypeIterator(bean.getAssetSubType());
					bean.setAssetRequiredIterator(bean.getAssetRequired());
					bean.setAssetUnitIterator(bean.getAssetUnit());
					bean.setPartialAssignIt("false");
					bean.setAssetInvTypeIterator(bean.getAssetInvType());
					tableList.add(bean);
				} // end of else
			} // end of for loop
			bean.setList(tableList);
			bean.setTableLength(String.valueOf(bean.getList().size()));

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception in editItem()==" + e);
		}
	}

	/*
	 * method name : removeItem purpose : set applied asset list after removing
	 * particular asset return type : void parameter : AssetEmployee
	 * instance,String []categoryCode,String []categoryName, String
	 * []subTypeCode,String []subTypeName,String []quantityReq
	 */
	public void removeItem(AssetEmployee bean, String[] categoryCode,
			String[] categoryName, String[] subTypeCode, String[] subTypeName,
			String[] quantityReq, String[] assetUnit, String[] partialIt,
			String[] invType) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		try {
			for (int i = 0; i < categoryCode.length; i++) {
				if ((i + 1 != Integer.parseInt(bean.getParaId()))) {
					AssetEmployee bean1 = new AssetEmployee();
					bean1.setSrNo(String.valueOf(i + 1));
					bean1.setAssetCode(categoryCode[i]);
					bean1.setAsstHdType(categoryName[i]);
					bean1.setSubTypeCodeIterator(subTypeCode[i]);
					bean1.setAssetSubTypeIterator(subTypeName[i]);
					bean1.setAssetRequiredIterator(quantityReq[i]);
					bean1.setAssetUnitIterator(assetUnit[i]);
					bean1.setPartialAssignIt(partialIt[i]);
					bean1.setAssetInvTypeIterator(invType[i]);
					tableList.add(bean1);
				} // end of if

			} // end of for loop
			bean.setList(tableList);
			bean.setTableLength(String.valueOf(bean.getList().size()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("exception in removeItem()==" + e);
		}
	}

	/*
	 * public String addAsset(AssetEmployee bean,String[] checkFlag,String []
	 * assetCode,String[] assignedDate,String [] returnDate){ Object
	 * chgStat[][]=new Object[1][5]; boolean res=false; String msg=""; Object
	 * maxCode[][]=null; int k=0; if( bean.getCode().equals("")){
	 * maxCode=getSqlModel().getSingleResult("SELECT NVL(MAX(ASSMT_CODE),0)+1
	 * FROM HRMS_ASSMT"); Object [][] data=new Object[1][2];
	 * data[0][0]=String.valueOf(maxCode[0][0]); data[0][1]=bean.getEmpCode();
	 * logger.info("code=="+String.valueOf(maxCode[0][0])+" &
	 * emp="+bean.getEmpCode()); res=getSqlModel().singleExecute(getQuery(1),
	 * data); if(res){
	 * 
	 * k=2; msg="Record saved successfully !"; }else msg="Record can't be saved
	 * !"; } if( ! bean.getCode().equals("")){
	 * getSqlModel().singleExecute("DELETE FROM HRMS_ASST_ASSMT WHERE
	 * ASSMT_CODE="+bean.getCode() +" AND RETURN_FLAG='N'"); k=1;
	 * 
	 * }else bean.setCode(String.valueOf(maxCode[0][0])); if(k==1 ||k==2){
	 * for(int i=0;i<checkFlag.length;i++){
	 * 
	 * chgStat[0][0]=bean.getCode(); chgStat[0][1]=String.valueOf(assetCode[i]);
	 * chgStat[0][2]=String.valueOf(assignedDate[i]);
	 * chgStat[0][3]=String.valueOf(returnDate[i]);
	 * chgStat[0][4]=String.valueOf(checkFlag[i]);
	 * res=getSqlModel().singleExecute(getQuery(2), chgStat);
	 * 
	 * if(res){ Object chgStat1[][]=new Object[1][2];
	 * if(checkFlag[i].equals("Y")) chgStat1[0][0]="U"; else chgStat1[0][0]="A";
	 * chgStat1[0][1]=String.valueOf(assetCode[i]);
	 * res=getSqlModel().singleExecute(getQuery(4), chgStat1); } }} if(k==1 &&
	 * res) msg="Record updated successfully !"; else if(k==1 && !res)
	 * msg="Record cant be updated !";
	 * 
	 * return msg; }
	 */

	/*
	 * method name : save purpose : Add or update the asset application return
	 * type : void parameter : AssetEmployee instance,String []assetCode,String
	 * []subType,String []assetRequired, Object [][] empFlow
	 */

	public String save(AssetEmployee bean, String[] assetCode,
			String[] subType, String[] assetRequired, Object[][] empFlow,
			HttpServletRequest request) {
		Object appDtlObj[][] = new Object[assetCode.length][4];
		boolean res = false;
		String msg = "";
		Object maxCode[][] = null;
		Object[][] to_mailIds = new Object[1][1];
		Object[][] from_mailIds = new Object[1][1];
		int k = 0;
		/*
		 * add the new asset application
		 * 
		 */
		if (bean.getCode().equals("")) {
			maxCode = getSqlModel()
					.getSingleResult(
							"SELECT NVL(MAX(ASSET_APPL_CODE),0)+1 FROM HRMS_ASSET_APPLICATION");
			Object[][] data = new Object[1][8];
			data[0][0] = String.valueOf(maxCode[0][0]);
			data[0][1] = bean.getEmpCode();
			data[0][2] = bean.getAssignDate1();
			logger.info("mpFlow - " + empFlow);
			if (bean.getHiddenStatus().equals("P") || empFlow != null)
				data[0][3] = String.valueOf(empFlow[0][0]);
			else
				data[0][3] = "0";
			logger.info("111111");
			data[0][4] = bean.getHiddenStatus();
			data[0][5] = bean.getComments();
			if (bean.getHiddenStatus().equals("P") || empFlow != null)
				data[0][6] = String.valueOf(empFlow[0][3]);
			else
				data[0][6] = "0";
			/*
			 * if(bean.isGeneralFlag()) data[0][6]= bean.getEmpCode(); else
			 */
			data[0][7] = bean.getUserEmpId();
			// .data[0][4]=assigner;
			logger.info("code==" + String.valueOf(maxCode[0][0]) + " & emp="
					+ bean.getEmpCode());
			res = getSqlModel().singleExecute(getQuery(1), data); // insert
			// data in
			// HRMS_ASSET_APPLICATION
			if (res) {
				bean.setCode(String.valueOf(maxCode[0][0]));
				getSqlModel().singleExecute(getQuery(8), maxCode);
				for (int i = 0; i < assetCode.length; i++) {

					appDtlObj[i][0] = bean.getCode();
					appDtlObj[i][1] = String.valueOf(assetCode[i]).trim();
					appDtlObj[i][2] = String.valueOf(subType[i]).trim();
					logger.info("subtype ===" + appDtlObj[i][2] + "xyz");
					appDtlObj[i][3] = String.valueOf(assetRequired[i]);
					// chgStat[0][4]=String.valueOf(checkFlag[i]);

				}
				res = getSqlModel().singleExecute(getQuery(2), appDtlObj); // insert
				// data
				// in
				// HRMS_ASSET_APPL_DETAILS
				if (res) {
					msg = "Record saved successfully.";

					/*
					 * try { to_mailIds[0][0]=String.valueOf(empFlow[0][0]);
					 * from_mailIds[0][0]=bean.getEmpCode();
					 * 
					 * MailUtility mail=new MailUtility();
					 * mail.initiate(context, session);
					 * logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
					 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
					 * mail.sendMail(to_mailIds, from_mailIds,"Asset", "", "P");
					 * 
					 * mail.terminate(); } catch (Exception e) { // TODO
					 * Auto-generated catch block e.printStackTrace(); }
					 */

					/**
					 * **************** generate the application
					 * number***************
					 */

					ApplCodeTemplateModel model = new ApplCodeTemplateModel();
					model.initiate(context, session);
					String applnCode = model.generateApplicationCode(bean
							.getCode(), "Asset", bean.getEmpCode(), bean
							.getAssignDate1());

					logger
							.info("final appln code in application=="
									+ applnCode);

					bean.setReferenceId(applnCode);

					getSqlModel().singleExecute(
							"UPDATE HRMS_ASSET_APPLICATION SET ASSET_APPL_NO='"
									+ applnCode + "' WHERE ASSET_APPL_CODE="
									+ bean.getCode());
					model.terminate();
					/**
					 * **************** end generate the application
					 * number***************
					 */

				} // end of if

				else
					msg = "Record can't be saved.";

			} // end of if
		} // end of if
		/*
		 * update the selected application
		 * 
		 */
		else {
			Object update[][] = new Object[1][6];
			update[0][0] = bean.getEmpCode();
			if (bean.getHiddenStatus().equals("P") || empFlow != null)
				update[0][1] = String.valueOf(empFlow[0][0]);
			else
				update[0][1] = "0";
			update[0][2] = bean.getHiddenStatus();
			update[0][3] = bean.getComments();
			if (bean.getHiddenStatus().equals("P") || empFlow != null)
				update[0][4] = String.valueOf(empFlow[0][3]);
			else
				update[0][4] = "0";
			update[0][5] = bean.getCode();
			res = getSqlModel().singleExecute(getQuery(5), update); // update
			// the
			// record in
			// HRMS_ASSET_APPLICATION
			if (res) {
				Object[][] appCode = new Object[1][1];
				appCode[0][0] = bean.getCode();
				getSqlModel().singleExecute(getQuery(8), appCode);
				/*
				 * add the asset details in HRMS_ASSET_APPL_DETAILS
				 * 
				 */
				for (int i = 0; i < assetCode.length; i++) {

					appDtlObj[i][0] = bean.getCode();
					appDtlObj[i][1] = String.valueOf(assetCode[i]).trim();
					appDtlObj[i][2] = String.valueOf(subType[i]).trim();
					appDtlObj[i][3] = String.valueOf(assetRequired[i]).trim();
					// chgStat[0][4]=String.valueOf(checkFlag[i]);

				} // end of for loop

				if (res = getSqlModel().singleExecute(getQuery(2), appDtlObj)) {
					msg = "Record updated successfully.";

					if (bean.getReferenceId().trim().equals("")) {
						ApplCodeTemplateModel model = new ApplCodeTemplateModel();
						model.initiate(context, session);
						String applnCode = model.generateApplicationCode(bean
								.getCode(), "Asset", bean.getEmpCode(), bean
								.getAssignDate1());

						logger.info("final appln code in application=="
								+ applnCode);

						bean.setReferenceId(applnCode);

						getSqlModel().singleExecute(
								"UPDATE HRMS_ASSET_APPLICATION SET ASSET_APPL_NO='"
										+ applnCode
										+ "' WHERE ASSET_APPL_CODE="
										+ bean.getCode());
						model.terminate();
					}

				} else {
					msg = "Record can't be updated.";
				}

			} // end of if
		} // end of else

		/*
		 * if(res){ try { to_mailIds[0][0]=String.valueOf(empFlow[0][0]);
		 * from_mailIds[0][0]=bean.getEmpCode();
		 * 
		 * MailUtility mail=new MailUtility(); mail.initiate(context, session);
		 * logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
		 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
		 * mail.sendMail(to_mailIds, from_mailIds,"Asset", "", "P");
		 * 
		 * mail.terminate(); } catch (Exception e) { e.printStackTrace(); } }
		 */
		if (res) {
			updateKeepInfo(request, bean);
		}
		return msg;
	}

	public String saveByApprover(AssetEmployee bean, String[] assetCode,
			String[] subType, String[] assetRequired, String[] partialIt) {

		int dtlObjLength = 0;
		for (int i = 0; i < assetCode.length; i++) {
			if (partialIt[i].equals("false"))
				dtlObjLength++;
		}
		Object appDtlObj[][] = new Object[dtlObjLength][4];
		String msg = "";

		Object[][] appCode = new Object[1][1];
		appCode[0][0] = bean.getCode();
		getSqlModel().singleExecute(getQuery(8), appCode);
		/*
		 * add the asset details in HRMS_ASSET_APPL_DETAILS
		 * 
		 */
		int tempId = 0;
		for (int i = 0; i < assetCode.length; i++) {
			if (partialIt[i].equals("false")) {
				appDtlObj[tempId][0] = bean.getCode();
				appDtlObj[tempId][1] = String.valueOf(assetCode[i]).trim();
				appDtlObj[tempId][2] = String.valueOf(subType[i]).trim();
				appDtlObj[tempId][3] = String.valueOf(assetRequired[i]).trim();
				tempId++;
			}
			// chgStat[0][4]=String.valueOf(checkFlag[i]);

		}
		if (getSqlModel().singleExecute(getQuery(2), appDtlObj)) {
			msg = "Record updated successfully.";

			MailUtility mail = new MailUtility();
			mail.initiate(context, session);

			/*
			 * send mails to all approvers of the application
			 * 
			 */

			Object[][] approverList = getApproverList(bean);
			try {
				from_mailIds[0][0] = bean.getUserEmpId();

				for (int i = 0; i < approverList.length; i++) {
					to_mailIds[0][0] = approverList[i][0];
					logger
							.info("to_mailIds approvers[0][0]"
									+ to_mailIds[0][0]);
					logger.info("from_mailIds[0][0]" + from_mailIds[0][0]);
					mail.sendMail(to_mailIds, from_mailIds, "Asset", bean
							.getEmpName(), "AE");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			/*
			 * send mails to applicant of the application
			 * 
			 */

			try {
				from_mailIds[0][0] = bean.getUserEmpId();
				to_mailIds[0][0] = bean.getEmpCode();
				logger.info("to_mailIds applicant[0][0]" + to_mailIds[0][0]);
				logger.info("from_mailIds[0][0]" + from_mailIds[0][0]);
				mail.sendMail(to_mailIds, from_mailIds, "Asset", bean
						.getEmpName(), "EE");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mail.terminate();
		}

		else
			msg = "Record can't be updated.";
		return msg;
	}

	public boolean saveByAssigner(AssetEmployee bean, HttpServletRequest request) {
		boolean result = false;

		String[] categoryCodeAssigned = request
				.getParameterValues("assetCodeAssigned");
		String[] categoryCode = request.getParameterValues("assetCode");
		String[] subtypeCodeAssigned = request
				.getParameterValues("subTypeCodeIteratorAssigned");
		String[] subtypeCode = request
				.getParameterValues("subTypeCodeIterator");
		String[] assignedQuantity = request
				.getParameterValues("assetAssignedIterator");
		String[] reqQuantity = request
				.getParameterValues("assetRequiredIterator");

		if (categoryCode == null || categoryCode.length == 0) { // if there are
			// no unassigned
			// assets.

			Object appDtlObj[][] = new Object[categoryCodeAssigned.length][5];
			Object[][] appCode = new Object[1][1];
			appCode[0][0] = bean.getCode();

			getSqlModel()
					.singleExecute(
							"DELETE FROM HRMS_ASSET_APPL_DETAILS WHERE ASSET_APPL_CODE=?",
							appCode);
			logger.info("asset appl details deleted ");
			for (int i = 0; i < categoryCodeAssigned.length; i++) {
				appDtlObj[i][0] = bean.getCode();
				appDtlObj[i][1] = String.valueOf(categoryCodeAssigned[i])
						.trim();
				appDtlObj[i][2] = String.valueOf(subtypeCodeAssigned[i]).trim();
				appDtlObj[i][3] = String.valueOf(assignedQuantity[i]).trim();
				appDtlObj[i][4] = String.valueOf(assignedQuantity[i]).trim();
			}
			result = getSqlModel().singleExecute(getQuery(23), appDtlObj);
			if (result) {
				/*
				 * update the application status as the assigned application
				 * 
				 */
				logger.info("assetapplication details added");
				Object[][] assignerComment = new Object[1][1];
				assignerComment[0][0] = bean.getAssignComments();
				String updateQuery = "UPDATE HRMS_ASSET_APPLICATION SET ASSET_STATUS='S',ASSET_ASSIGN_COMMENTS=? WHERE ASSET_APPL_CODE="
						+ bean.getCode();
				result = getSqlModel().singleExecute(updateQuery,
						assignerComment);
			}
		} else { // if unassigned assets are available in the list update the
			// HRMS_ASSET_APPL_DETAILS table

			Vector<Object> unassignedVector = new Vector<Object>();
			Object appDtlObj[][] = null;

			if (categoryCodeAssigned == null
					|| categoryCodeAssigned.length == 0) {

				for (int i = 0; i < subtypeCode.length; i++) {

					Object[][] tempObj = new Object[1][5];

					tempObj[0][0] = bean.getCode();
					tempObj[0][1] = String.valueOf(categoryCode[i]).trim();
					tempObj[0][2] = String.valueOf(subtypeCode[i]).trim();
					tempObj[0][3] = String.valueOf(reqQuantity[i]).trim();
					tempObj[0][4] = "0";

					unassignedVector.add(tempObj);
				}
				appDtlObj = new Object[unassignedVector.size()][5];
				for (int i = 0; i < unassignedVector.size(); i++) {

					appDtlObj[i][0] = String
							.valueOf(((Object[][]) unassignedVector.get(i))[0][0]);
					appDtlObj[i][1] = String
							.valueOf(((Object[][]) unassignedVector.get(i))[0][1]);
					appDtlObj[i][2] = String
							.valueOf(((Object[][]) unassignedVector.get(i))[0][2]);
					appDtlObj[i][3] = String
							.valueOf(((Object[][]) unassignedVector.get(i))[0][3]);
					appDtlObj[i][4] = String
							.valueOf(((Object[][]) unassignedVector.get(i))[0][4]);
				}
			} else {
				String[] updatedQuantity = new String[assignedQuantity.length];
				for (int i = 0; i < updatedQuantity.length; i++) {
					updatedQuantity[i] = assignedQuantity[i];
				}

				for (int i = 0; i < subtypeCode.length; i++) {
					boolean equalFlag = false;
					for (int j = 0; j < categoryCodeAssigned.length; j++) {
						if (subtypeCode[i].equals(subtypeCodeAssigned[j])) {
							updatedQuantity[j] = String.valueOf((Integer
									.parseInt(updatedQuantity[j]) + Integer
									.parseInt(reqQuantity[i])));
							equalFlag = true;
							break;
						}
					}
					if (!equalFlag) {
						Object[][] tempObj = new Object[1][5];

						tempObj[0][0] = bean.getCode();
						tempObj[0][1] = String.valueOf(categoryCode[i]).trim();
						tempObj[0][2] = String.valueOf(subtypeCode[i]).trim();
						tempObj[0][3] = String.valueOf(reqQuantity[i]).trim();
						tempObj[0][4] = "0";

						unassignedVector.add(tempObj);
					}

				}
				logger.info("vector size==" + unassignedVector.size());

				appDtlObj = new Object[categoryCodeAssigned.length
						+ unassignedVector.size()][5];

				for (int i = 0; i < categoryCodeAssigned.length; i++) {

					appDtlObj[i][0] = bean.getCode();
					appDtlObj[i][1] = String.valueOf(categoryCodeAssigned[i])
							.trim();
					appDtlObj[i][2] = String.valueOf(subtypeCodeAssigned[i])
							.trim();
					appDtlObj[i][3] = String.valueOf(updatedQuantity[i]).trim();
					logger.info("updated qty===" + updatedQuantity[i]);

					appDtlObj[i][4] = String.valueOf(assignedQuantity[i])
							.trim();
					logger
							.info("assignedQuantity qty==="
									+ assignedQuantity[i]);
				}
				for (int i = 0; i < unassignedVector.size(); i++) {

					appDtlObj[i + categoryCodeAssigned.length][0] = String
							.valueOf(((Object[][]) unassignedVector.get(i))[0][0]);
					appDtlObj[i + categoryCodeAssigned.length][1] = String
							.valueOf(((Object[][]) unassignedVector.get(i))[0][1]);
					appDtlObj[i + categoryCodeAssigned.length][2] = String
							.valueOf(((Object[][]) unassignedVector.get(i))[0][2]);
					appDtlObj[i + categoryCodeAssigned.length][3] = String
							.valueOf(((Object[][]) unassignedVector.get(i))[0][3]);
					appDtlObj[i + categoryCodeAssigned.length][4] = String
							.valueOf(((Object[][]) unassignedVector.get(i))[0][4]);
				}
			}

			for (int i = 0; i < appDtlObj.length; i++) {
				logger.info("final obj category==" + appDtlObj[i][1]);
				logger.info("final obj subtype==" + appDtlObj[i][2]);
				logger.info("final obj appllied qty==" + appDtlObj[i][3]);
				logger.info("final obj assigned qty==" + appDtlObj[i][4]);
			}
			Object[][] appCode = new Object[1][1];
			appCode[0][0] = bean.getCode();

			getSqlModel()
					.singleExecute(
							"DELETE FROM HRMS_ASSET_APPL_DETAILS WHERE ASSET_APPL_CODE=?",
							appCode);
			result = getSqlModel().singleExecute(getQuery(23), appDtlObj);
		}
		if (result) {

			MailUtility mail = new MailUtility();
			mail.initiate(context, session);

			/*
			 * send mails to all approvers of the application
			 * 
			 */

			Object[][] approverList = getApproverList(bean);
			try {
				from_mailIds[0][0] = bean.getUserEmpId();

				for (int i = 0; i < approverList.length; i++) {
					to_mailIds[0][0] = approverList[i][0];
					logger
							.info("to_mailIds approvers[0][0]"
									+ to_mailIds[0][0]);
					logger.info("from_mailIds[0][0]" + from_mailIds[0][0]);
					mail.sendMail(to_mailIds, from_mailIds, "Asset", bean
							.getEmpName(), "AE");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			/*
			 * send mails to applicant of the application
			 * 
			 
			ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
			processAlerts.initiate(context, session);
			processAlerts.removeProcessAlert(bean.getCode(), "Asset");
			EmailTemplateBody template = new EmailTemplateBody();*/
			try {
				from_mailIds[0][0] = bean.getUserEmpId();
				to_mailIds[0][0] = bean.getEmpCode();
				logger.info("to_mailIds applicant[0][0]" + to_mailIds[0][0]);
				logger.info("from_mailIds[0][0]" + from_mailIds[0][0]);
				mail.sendMail(to_mailIds, from_mailIds, "Asset", bean
						.getEmpName(), "EE");
				/*String sender=bean.getUserEmpId();
				String receiver = bean.getEmpCode();
				String alertlink = "/asset/AssetEmployee_callforedit.action";
				String alertlinkParam = "hiddencode=" + bean.getCode() + "&hiddenStatus=R";
				template.sendProcessManagerAlert(receiver, "Asset", "I",
						bean.getCode(), "", alertlinkParam, alertlink, "Update",
						receiver, "", "", "",sender);*/
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mail.terminate();
		}

		return result;
	}

	/*
	 * public boolean addAsset(AssetEmployee bean){ Object addObj[][]=new
	 * Object[1][1]; boolean result=false; addObj[0][0]=bean.getEmpCode();
	 * result=getSqlModel().singleExecute(getQuery(1),addObj); if(result){
	 * String query = " SELECT MAX(ASSMT_CODE) FROM HRMS_ASSMT "; Object
	 * [][]data=getSqlModel().getSingleResult(query);
	 * bean.setCode(String.valueOf(data[0][0])); Object [][]addObj1=new
	 * Object[1][4]; addObj1[0][0]=bean.getCode();
	 * addObj1[0][1]=bean.getAsstCode1(); addObj1[0][2]=bean.getAssignDate1();
	 * addObj1[0][3]=bean.getReturnDate1();
	 * result=getSqlModel().singleExecute(getQuery(2), addObj1);
	 * 
	 * if (result){ Object[][] code=new Object[1][2];
	 * 
	 * code[0][0]="A"; code[0][1]=String.valueOf(addObj1[0][1]);
	 * result=getSqlModel().singleExecute(getQuery(4), code); } } return result; }
	 */

	/*
	 * method name : setEmpData purpose : Set the employee name & id on the
	 * screen return type : void parameter : AssetEmployee instance
	 */

	public void setEmpData(AssetEmployee bean) {
		String empName = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
				+ " HRMS_EMP_OFFC.EMP_LNAME) FROM HRMS_EMP_OFFC"
				+ " LEFT JOIN HRMS_TITLE  ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
				+ " WHERE EMP_ID=" + bean.getEmpCode();

		/*
		 * String query="SELECT EMP_TOKEN,NVL(HRMS_TITLE.TITLE_NAME,' ')||'
		 * '||(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || " +"
		 * HRMS_EMP_OFFC.EMP_LNAME),EMP_CODE FROM HRMS_ASSMT " +" INNER JOIN
		 * HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ASSMT.EMP_CODE)"
		 */

		Object empData[][] = getSqlModel().getSingleResult(empName);

		bean.setEmpToken(String.valueOf(empData[0][0]));
		bean.setEmpName(String.valueOf(empData[0][1]));
		// bean.setEmpCode(String.valueOf(empData[0][2]));

	}

	/*
	 * method name : showEmpDetails purpose : Set the employee name & id on the
	 * screen return type : void parameter : AssetEmployee instance
	 */
	public void showEmpDetails(AssetEmployee bean) {
		try {
			String empName = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || "
					+ " HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(ASSET_APPL_DATE,'DD-MM-YYYY'),CASE WHEN ASSET_STATUS='P' AND ASSET_APPL_LEVEL!=1   THEN 'F' else ASSET_STATUS  end,"
					+ " ASSET_EMP_ID FROM HRMS_ASSET_APPLICATION"
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_APPLICATION.ASSET_EMP_ID)"
					+ " WHERE ASSET_APPL_CODE=" + bean.getCode();
			/*
			 * set the application details with employee details on the screen
			 * 
			 */
			Object empData[][] = getSqlModel().getSingleResult(empName);
			
			if(empData!=null && empData.length >0)
			{
				bean.setEmpToken(String.valueOf(empData[0][0]));
				bean.setEmpName(String.valueOf(empData[0][1]));
				bean.setAssignDate1(String.valueOf(empData[0][2]));
				bean.setStatus(String.valueOf(empData[0][3]));
				bean.setHiddenStatus(String.valueOf(empData[0][3]));
				bean.setEmpCode(String.valueOf(empData[0][4]));
				bean.setHiddenappCode(bean.getCode());
				// bean.setEmpCode(String.valueOf(empData[0][2]));
				logger.info("status ------------------------------------ "
						+ bean.getStatus());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public boolean modAsset(AssetEmployee bean){ boolean result=false;
	 * Object[]testpara=new Object[2]; testpara[0]=bean.getCode();
	 * testpara[1]=testCode; Object
	 * [][]test=getSqlModel().getSingleResult(getQuery(10), testpara);
	 * if(test.length==0){ Object [][]modObj=new Object[1][4];
	 * modObj[0][0]=bean.getCode(); modObj[0][1]=bean.getAsstCode1();
	 * modObj[0][2]=bean.getAssignDate1(); modObj[0][3]=bean.getReturnDate1();
	 * result= getSqlModel().singleExecute(getQuery(2), modObj); }else { Object
	 * [][]modObj=new Object[1][5]; modObj[0][0]=bean.getAsstCode1();
	 * modObj[0][1]=bean.getAssignDate1(); modObj[0][2]=bean.getReturnDate1();
	 * modObj[0][3]=bean.getCode(); modObj[0][4]=testpara[1]; result=
	 * getSqlModel().singleExecute(getQuery(9), modObj); } if (result){
	 * Object[][] code=new Object[1][2];
	 * 
	 * code[0][0]="A"; code[0][1]=bean.getAsstCode1();
	 * result=getSqlModel().singleExecute(getQuery(4), code); } return result; }
	 */

	/*
	 * public boolean modEmp(AssetEmployee bean) { Object [][]emp=new
	 * Object[1][2]; emp[0][0]=bean.getEmpCode(); emp[0][1]=bean.getCode();
	 * return getSqlModel().singleExecute(getQuery(5), emp); }
	 */

	/*
	 * method name : delRecord purpose : Delete the asset application return
	 * type : void parameter : AssetEmployee instance
	 */
	public boolean delRecord(AssetEmployee bean) {
		boolean result = false;
		Object[][] del = new Object[1][1];
		del[0][0] = bean.getCode();
		result = getSqlModel().singleExecute(getQuery(8), del);

		result = getSqlModel().singleExecute(getQuery(7), del);

		if (result) {
			/*
			 * try { to_mailIds[0][0]=bean.getApproverCode();
			 * from_mailIds[0][0]=bean.getEmpCode();
			 * 
			 * MailUtility mail=new MailUtility(); mail.initiate(context,
			 * session); logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
			 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
			 * mail.sendMail(to_mailIds, from_mailIds,"Asset", "", "D");
			 * 
			 * mail.terminate(); } catch (Exception e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); }
			 */
		}
		return result;
	}

	public boolean cancelRecord(AssetEmployee bean) {
		boolean result = false;
		Object[][] del = new Object[1][2];
		del[0][0] = "C";
		del[0][1] = bean.getCode();
		// result= getSqlModel().singleExecute(getQuery(8), del);
		String query = "DELETE FROM HRMS_ASSET_APPL_DETAILS WHERE ASSET_APPL_CODE = "
				+ bean.getCode();
		result = getSqlModel().singleExecute(query);
		if (result) {
			String delQuery = "DELETE FROM HRMS_ASSET_APPLICATION WHERE ASSET_APPL_CODE = "
					+ bean.getCode();
			result = getSqlModel().singleExecute(delQuery);
			String query1 = "SELECT ASSET_APPL_CODE FROM HRMS_ASSET_PATH WHERE ASSET_APPL_CODE = "
					+ bean.getCode();
			Object[][] obj = getSqlModel().getSingleResult(query1);
			if (obj != null && obj.length > 0) {
				String delPathQuery = "DELETE FROM HRMS_ASSET_PATH WHERE ASSET_APPL_CODE = "
						+ bean.getCode();
				getSqlModel().singleExecute(delPathQuery);
			}
		}
		if (result) {
			/*
			 * try { to_mailIds[0][0]=bean.getApproverCode();
			 * from_mailIds[0][0]=bean.getEmpCode();
			 * 
			 * MailUtility mail=new MailUtility(); mail.initiate(context,
			 * session); logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
			 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
			 * mail.sendMail(to_mailIds, from_mailIds,"Asset", "", "D");
			 * 
			 * mail.terminate(); } catch (Exception e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); }
			 */
		}
		return result;
	}

	/*
	 * public void getRecord(AssetEmployee bean) { Object obj[] =new Object[2];
	 * obj[0] =bean.getCode(); obj[1] =bean.getAssetCode(); Object[][] data =
	 * getSqlModel().getSingleResult(getQuery(6),obj);
	 * 
	 * bean.setAsstCode1(String.valueOf(data[0][0]));
	 * bean.setInvCode1(String.valueOf(data[0][1]));
	 * bean.setAsstHdType1(String.valueOf(data[0][2]));
	 * bean.setAssignDate1(String.valueOf(data[0][3]));
	 * bean.setReturnDate1(String.valueOf(data[0][4]));
	 * testCode=bean.getAssetCode(); }
	 */

	/*
	 * method name : getReport purpose : Show the PDF report for selected
	 * application return type : void parameter : AssetEmployee
	 * instance,HttpServletRequest request,HttpServletResponse response
	 */
	public void getReport(AssetEmployee bean, HttpServletRequest request,
			HttpServletResponse response) {

		String query = "SELECT EMP_TOKEN,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),TO_CHAR(ASSET_APPL_DATE,'DD-MM-YYYY'),ASSET_CATEGORY_NAME,ASSET_SUBTYPE_NAME,ASSET_APPL_QTY, "
				+ " CASE WHEN ASSET_STATUS='P' AND ASSET_APPL_LEVEL!=1   THEN 'Forwarded' WHEN ASSET_STATUS='P' THEN 'Pending' WHEN ASSET_STATUS='A' THEN 'Approved' WHEN ASSET_STATUS='R' THEN 'Rejected' "
				+ " WHEN ASSET_STATUS='S' THEN 'Assigned' WHEN ASSET_STATUS='B' THEN 'SentBack' ELSE '' END,ASSET_COMMENTS,nvl(ASSET_APPL_NO,'') FROM HRMS_ASSET_APPLICATION "
				+ " INNER JOIN  HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_APPLICATION.ASSET_EMP_ID) "
				+ " INNER JOIN  HRMS_ASSET_APPL_DETAILS ON(HRMS_ASSET_APPL_DETAILS.ASSET_APPL_CODE=HRMS_ASSET_APPLICATION.ASSET_APPL_CODE) "
				+ " INNER JOIN HRMS_ASSET_CATEGORY ON(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASSET_APPL_DETAILS.ASSET_CATEGORY_CODE) "
				+ " INNER JOIN HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_APPL_DETAILS.ASSET_SUBTYPE_CODE)"
				+ " WHERE ASSET_APPL_CODE=" + bean.getCode();

		String s = "\n Asset Application Report\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				s);
		Object[][] heading = new Object[1][1];

		int[] cells = { 25 };
		int[] align = { 0 };
		Object applData[][] = getSqlModel().getSingleResult(query);
		Object tab[][] = new Object[applData.length][4];
		for (int i = 0; i < tab.length; i++) {
			tab[i][0] = String.valueOf(i + 1);
			tab[i][1] = applData[i][3];
			tab[i][2] = applData[i][4];
			tab[i][3] = applData[i][5];
		} // end of for loop

		int cellwidth[] = { 15, 30, 30, 15 };
		int alignment[] = { 1, 0, 0, 1 };

		Object data[][] = new Object[3][4];
		data[0][0] = "Application No.";
		data[0][1] = ": " + checkNull(String.valueOf(applData[0][8]));
		data[0][2] = "";
		data[0][3] = "";

		data[1][0] = "Employee Id";
		data[1][1] = ": " + String.valueOf(applData[0][0]);
		data[1][2] = "Employee Name ";
		data[1][3] = ": " + String.valueOf(applData[0][1]);

		data[2][0] = "Application Date";
		data[2][1] = ": " + String.valueOf(applData[0][2]);
		data[2][2] = "Status ";
		data[2][3] = ": " + String.valueOf(applData[0][6]);

		// rg.tableBody(data, cellwidth, alignment);
		String date = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
		Object[][] today = getSqlModel().getSingleResult(date);
		rg.addFormatedText(s, 6, 0, 1, 0);
		int[] bcellWidth = { 20, 30, 20, 30 };
		int[] bcellAlign = { 0, 0, 0, 0 };

		rg.addText("Date: " + today[0][0], 0, 2, 0);
		rg.addFormatedText("", 6, 0, 0, 0);
		try {
			rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);
			// rg.addFormatedText("Applied Assets:", 6, 0, 0, 0);
			heading[0][0] = "Applied Assets :";
			rg.tableBodyBold(heading, cells, align);
			String colnames[] = { "Sr. No", "Asset Type", "Asset Type",
					"Required Quantity" };
			rg.tableBody(colnames, tab, cellwidth, alignment);
			// Object comments[][] = new Object [1][1];
			heading[0][0] = "Comments :";
			rg.tableBodyBold(heading, cells, align);
			rg.addText(checkNull(String.valueOf(applData[0][7])), 0, 0, 0);

			// comments [0][0] = ""+String.valueOf(applData[0][7]);
			// rg.tableBodyNoBorder(comments, new int[]{100}, new int []{0});
		} catch (Exception e) {
			rg.addFormatedText("There is No Record To Display :", 0, 0, 0, 0);
			logger.info("Exception in getReport" + e);
		}

		String commentsQuery = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ " TO_CHAR(ASSET_APPR_DATE,'DD-MM-YYYY'), "
				+ " DECODE(ASSET_PATH_STATUS,'P','Pending','A','Approved','R','Rejected','B','SentBack'), "
				+ " ASSET_APPL_COMMENTS "
				+ " FROM HRMS_ASSET_PATH "
				+ " INNER JOIN HRMS_EMP_OFFC ON ( HRMS_EMP_OFFC.EMP_ID = HRMS_ASSET_PATH.ASSET_APPROVER) "
				+ " WHERE ASSET_APPL_CODE = "
				+ bean.getCode()
				+ " ORDER BY ASSET_PATH_ID ";
		Object obj[][] = getSqlModel().getSingleResult(commentsQuery);
		if (obj != null && obj.length != 0) {
			Object[][] approverComment = new Object[obj.length][5];
			logger.info("Length of ApproverComments Table ---  " + obj.length);
			for (int i = 0; i < obj.length; i++) {
				approverComment[i][0] = i + 1;
				approverComment[i][1] = checkNull(String.valueOf(obj[i][0]));
				approverComment[i][2] = checkNull(String.valueOf(obj[i][1]));
				approverComment[i][3] = checkNull(String.valueOf(obj[i][2]));
				approverComment[i][4] = checkNull(String.valueOf(obj[i][3]));

			}// End of for
			String approverColNames[] = { "Sr. No", "Approver Name",
					"Approved Date", "Status", "Comments" };
			int[] approvercellwidth = { 8, 30, 15, 10, 30 };
			int[] approveralignmnet = { 1, 0, 1, 0, 0 };

			rg.addText("\n", 0, 0, 0);
			heading[0][0] = "Approver Details :";
			rg.tableBodyBold(heading, cells, align);
			rg.tableBody(approverColNames, approverComment, approvercellwidth,
					approveralignmnet);
		}// End of if
		// Assigner Comments
		String assignerComments = " SELECT ASSET_STATUS, nvl(ASSET_ASSIGN_COMMENTS,'')  FROM HRMS_ASSET_APPLICATION WHERE ASSET_APPL_CODE = "
				+ bean.getCode();
		Object assignerCom[][] = getSqlModel()
				.getSingleResult(assignerComments);
		logger
				.info("assigner comments --------"
						+ checkNull(String.valueOf(assignerCom[0][0])).length()
						+ "---");
		logger.info("assigner comments length --------" + assignerCom.length
				+ "---");
		if (assignerCom != null && assignerCom.length != 0) {
			if (assignerCom[0][0].equals("S")) {
				heading[0][0] = " Assigner Comments :";
				rg.tableBodyBold(heading, cells, align);
				rg.addText(checkNull(String.valueOf(assignerCom[0][1])), 0, 0,
						0);
			}
		}

		rg.createReport(response);

	}

	/*
	 * public boolean addItem(AssetEmployee bean, String[] srNo, String[]
	 * asstCode, String[] asstHdType, String[] assignDate, String[] returnDate,
	 * String[] checkFlag,String[] subType, String[] subTypeCode,String[]
	 * assetReq, HttpServletRequest request) {
	 * 
	 * ArrayList<Object> tableList = new ArrayList<Object>(); Map checkMap =
	 * new HashMap(); boolean result=true; if (srNo != null) // check whether
	 * list is empty or not { for (int i = 0; i < srNo.length; i++) {
	 * AssetEmployee bean1 = new AssetEmployee();
	 * 
	 * bean1.setSrNo(srNo[i]); bean1.setAssetCode(asstCode[i]);
	 * //bean1.setInvCode(invCode[i]); bean1.setAsstHdType(asstHdType[i]);
	 * /*bean1.setAssignDate(assignDate[i]); bean1.setReturnDate(returnDate[i]);
	 * bean1.setCheckFlag(checkFlag[i]);
	 * bean1.setSubTypeCodeIterator(subTypeCode[i]);
	 * bean1.setAssetSubTypeIterator(subType[i]);
	 * bean1.setAssetRequiredIterator(assetReq[i]); /*try { //
	 * bean1.setCheckFlag(checkFlag[i]); if (bean1.getCheckFlag().equals("Y")) {
	 * bean1.setChkFlag("true"); checkMap.put("" + i, "Y"); } else {
	 * bean1.setChkFlag("false"); checkMap.put("" + i, "N"); } } catch
	 * (Exception e) { // TODO: handle exception } tableList.add(bean1);
	 * bean.setList(tableList); }
	 * 
	 * /*for (int j = 0; j < srNo.length; j++) { if
	 * (bean.getInvCode1().equals(invCode[j])) { bean.setAsstCode1("");
	 * bean.setInvCode1(""); bean.setAssignDate1(""); bean.setAsstHdType1("");
	 * request.setAttribute("data", checkMap); return false; } }
	 * bean.setSrNo(String.valueOf(tableList.size() + 1));
	 * bean.setAssetCode(bean.getAsstCode1());
	 * //bean.setInvCode(bean.getInvCode1());
	 * bean.setAsstHdType(bean.getAsstHdType1());
	 * 
	 * bean.setSubTypeCodeIterator(bean.getSubTypeCode());
	 * bean.setAssetSubTypeIterator(bean.getAssetSubType());
	 * bean.setAssetRequiredIterator(bean.getAssetRequired());
	 * /*bean.setAssignDate(bean.getAssignDate1()); bean.setReturnDate("");
	 * bean.setCheckFlag("N"); tableList.add(bean);
	 * 
	 * logger.info("tablelist.size()" + tableList.size());
	 * 
	 * logger.info("inside add method" + bean.getList().size());
	 * //logger.info("inside add method" + bean.getCheckFlag()); /*try { if
	 * (bean.getCheckFlag().equals("Y")) { bean.setChkFlag("true");
	 * checkMap.put("" + (tableList.size() - 1), "Y"); } else {
	 * bean.setChkFlag("false"); checkMap.put("" + (tableList.size() - 1), "N"); } }
	 * catch (Exception e) { // TODO: handle exception } } else {
	 * bean.setSrNo(String.valueOf(tableList.size() + 1));
	 * bean.setAssetCode(bean.getAsstCode1());
	 * //bean.setInvCode(bean.getInvCode1());
	 * bean.setAsstHdType(bean.getAsstHdType1());
	 * 
	 * bean.setSubTypeCodeIterator(bean.getSubTypeCode());
	 * bean.setAssetSubTypeIterator(bean.getAssetSubType());
	 * bean.setAssetRequiredIterator(bean.getAssetRequired());
	 * 
	 * /*bean.setAssignDate(bean.getAssignDate1()); bean.setReturnDate("");
	 * bean.setCheckFlag("N"); tableList.add(bean); bean.setList(tableList);
	 * 
	 * logger.info("tablelist.size()" + tableList.size());
	 * 
	 * logger.info("inside add method" + bean.getList().size());
	 * logger.info("inside add method" + bean.getCheckFlag()); /*try { if
	 * (bean.getCheckFlag().equals("Y")) { bean.setChkFlag("true");
	 * checkMap.put("" + (tableList.size() - 1), "Y"); } else {
	 * bean.setChkFlag("false"); checkMap.put("" + (tableList.size() - 1), "N"); } }
	 * catch (Exception e) { // TODO: handle exception } }l
	 * //request.setAttribute("data", checkMap);
	 * bean.setTableLength(String.valueOf(bean.getList().size())); return result ; }
	 */

	/*
	 * add the asset required in the list
	 * 
	 * 
	 */

	/*
	 * method name : addItem purpose : add the asset required in the list return
	 * type : void parameter : AssetEmployee instance,String[] srNo,String[]
	 * asstCode, String[] asstHdType, String[] subType, String[] subTypeCode,
	 * String[] assetReq
	 */
	public void addItem(AssetEmployee bean, String[] srNo, String[] asstCode,
			String[] asstHdType, String[] subType, String[] subTypeCode,
			String[] assetReq, String[] assetUnit, String[] partialIt,
			String[] invType) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		try {

			if (srNo != null && srNo.length > 0) // check whether list is
			// empty or not
			{
				/*
				 * set the asset one by one in the list which were already
				 * present in request
				 * 
				 */
				for (int i = 0; i < assetReq.length; i++) {
					AssetEmployee bean1 = new AssetEmployee();

					bean1.setSrNo(srNo[i]);
					bean1.setAssetCode(asstCode[i]);
					bean1.setAsstHdType(asstHdType[i]);
					bean1.setSubTypeCodeIterator(subTypeCode[i]);
					bean1.setAssetSubTypeIterator(subType[i]);
					bean1.setAssetRequiredIterator(assetReq[i]);
					bean1.setAssetUnitIterator(assetUnit[i]);
					bean1.setPartialAssignIt(partialIt[i]);
					logger.info("setAssetInvTypeIterator in th list=="
							+ invType[i]);
					bean1.setAssetInvTypeIterator(invType[i]);
					tableList.add(bean1);
					bean.setList(tableList);
				} // end of for loop

				bean.setSrNo(String.valueOf(tableList.size() + 1));
				bean.setAssetCode(bean.getAsstCode1());
				bean.setAsstHdType(bean.getAsstHdType1());

				bean.setSubTypeCodeIterator(bean.getSubTypeCode());
				bean.setAssetSubTypeIterator(bean.getAssetSubType());
				bean.setAssetRequiredIterator(bean.getAssetRequired());
				bean.setAssetUnitIterator(bean.getAssetUnit());
				bean.setPartialAssignIt("false");
				bean.setAssetInvTypeIterator(bean.getAssetInvType());
				tableList.add(bean);

				logger.info("tablelist.size()" + tableList.size());

				logger.info("inside add method" + bean.getList().size());

			} // end of if
			/*
			 * add the first asset in the list
			 * 
			 */
			else {
				bean.setSrNo(String.valueOf(tableList.size() + 1));
				bean.setAssetCode(bean.getAsstCode1());
				bean.setAsstHdType(bean.getAsstHdType1());

				bean.setSubTypeCodeIterator(bean.getSubTypeCode());
				bean.setAssetSubTypeIterator(bean.getAssetSubType());
				bean.setAssetRequiredIterator(bean.getAssetRequired());
				bean.setAssetUnitIterator(bean.getAssetUnit());
				bean.setAssetInvTypeIterator(bean.getAssetInvType());
				tableList.add(bean);
				bean.setList(tableList);

				logger.info("tablelist.size()" + tableList.size());

				logger.info("inside add method" + bean.getList().size());
				logger.info("inside add method" + bean.getCheckFlag());
				bean.setTableLength(String.valueOf(bean.getList().size()));
			} // end of else

		} catch (Exception e) {
			bean.setSrNo(String.valueOf(tableList.size() + 1));
			bean.setAssetCode(bean.getAsstCode1());
			bean.setAsstHdType(bean.getAsstHdType1());

			bean.setSubTypeCodeIterator(bean.getSubTypeCode());
			bean.setAssetSubTypeIterator(bean.getAssetSubType());
			bean.setAssetRequiredIterator(bean.getAssetRequired());
			bean.setAssetUnitIterator(bean.getAssetUnit());
			bean.setAssetInvTypeIterator(bean.getAssetInvType());
			tableList.add(bean);
			bean.setList(tableList);

			logger.info("tablelist.size()" + tableList.size());

			logger.info("inside add method" + bean.getList().size());
			logger.info("inside add method" + bean.getCheckFlag());
			bean.setTableLength(String.valueOf(bean.getList().size()));
		}

	}

	/*
	 * method name : showItem purpose : show the asset required in the list
	 * return type : void parameter : AssetEmployee instance,String[]
	 * srNo,String[] asstCode, String[] asstHdType, String[] subType, String[]
	 * subTypeCode, String[] assetReq
	 */
	/*
	 * public void showItem(AssetEmployee bean, String[] srNo, String[]
	 * asstCode, String[] asstHdType, String[] subType, String[]
	 * subTypeCode,String[] assetReq) { ArrayList<Object> tableList = new
	 * ArrayList<Object>();
	 * 
	 * 
	 * if (srNo != null) // check whether list is empty or not { for (int i = 0;
	 * i < srNo.length; i++) { AssetEmployee bean1 = new AssetEmployee();
	 * 
	 * bean1.setSrNo(srNo[i]); bean1.setAssetCode(asstCode[i]);
	 * //bean1.setInvCode(invCode[i]); bean1.setAsstHdType(asstHdType[i]);
	 * 
	 * bean1.setReturnDate(returnDate[i]); bean1.setCheckFlag(checkFlag[i]);
	 * bean1.setSubTypeCodeIterator(subTypeCode[i]);
	 * bean1.setAssetSubTypeIterator(subType[i]);
	 * bean1.setAssetRequiredIterator(assetReq[i]);
	 * 
	 * tableList.add(bean1); bean.setList(tableList); } }
	 * logger.info("tablelength before===="+bean.getTableLength());
	 * bean.setTableLength(String.valueOf(tableList.size()));
	 * logger.info("tablelength after===="+bean.getTableLength()); }
	 */

	/*
	 * method name : showForAssignment purpose : show the application details
	 * for actual assignment to the Employee return type : void parameter :
	 * AssetEmployee instance
	 */

	public void showForAssignment(AssetEmployee bean) {
		try {
			Object[] applCode = new Object[1];
			applCode[0] = bean.getCode();
			
			System.out.println("bean.getCodeeeeeeeeeee()      "+bean.getCode());
			Object[][] result = getSqlModel().getSingleResult(getQuery(12),
					applCode);
			ArrayList<Object> tableList = new ArrayList<Object>();
			ArrayList<Object> otherWarehouseList = new ArrayList<Object>();
			logger.info("emp code==" + bean.getEmpCode());
			String warehouseQuery = "SELECT WAREHOUSE_CODE, WAREHOUSE_NAME FROM HRMS_EMP_OFFC"
					+ " INNER JOIN HRMS_WAREHOUSE_BRANCH ON(HRMS_WAREHOUSE_BRANCH.WAREHOUSE_BRANCH=HRMS_EMP_OFFC.EMP_CENTER)"
					+ " INNER JOIN HRMS_WAREHOUSE_MASTER ON (HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE =HRMS_WAREHOUSE_BRANCH.WAREHOUSE_CODE)"
					+ " WHERE EMP_ID=" + bean.getEmpCode();
			Object warehouseName[][] = getSqlModel().getSingleResult(
					warehouseQuery);
			bean.setEmpWarehouseCode(String.valueOf(warehouseName[0][0]));
			/*
			 * show the applied assets for the particular application
			 */
			for (int i = 0; i < result.length; i++) {
				/*
				 * if the Inventory type is bulk
				 * 
				 */
				if (!checkNull(String.valueOf(result[i][5])).equals("I")) {
					String quantityAvailableQuery = "SELECT  NVL(SUM(HRMS_ASSET_MASTER_DTL.ASSET_AVAILABLE),0) FROM HRMS_ASSET_MASTER_DTL "
							+ "INNER JOIN HRMS_ASSET_MASTER ON(HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE)"
							+ "WHERE ASSET_SUBTYPE_CODE ="
							+ String.valueOf(result[i][2])
							+ " AND ASSET_WAREHOUSE_CODE ="
							+ bean.getEmpWarehouseCode();
					Object quantityAvailable[][] = getSqlModel()
							.getSingleResult(quantityAvailableQuery);

					if (Float.parseFloat(String
							.valueOf(quantityAvailable[0][0])) >= Float
							.parseFloat("" + result[i][4])) {
						AssetEmployee bean1 = new AssetEmployee();
						bean1.setAssetCode(checkNull(String
								.valueOf(result[i][0])));
						bean1.setAsstHdType(checkNull(String
								.valueOf(result[i][1])));
						bean1.setSubTypeCodeIterator(checkNull(String
								.valueOf(result[i][2])));
						bean1.setAssetSubTypeIterator(checkNull(String
								.valueOf(result[i][3])));
						bean1.setAssetRequiredIterator(checkNull(String
								.valueOf(result[i][4])));
						bean1.setReturnFlagIterator(checkNull(String
								.valueOf(result[i][5])));
						bean1.setApplCode(checkNull(String
								.valueOf(result[i][6])));
						bean1.setEmpWarehouse(String
								.valueOf(warehouseName[0][1]));
						logger.info("warehousename=====" + warehouseName[0][0]);
						tableList.add(bean1);
					} // end if
					else if (!String.valueOf(quantityAvailable[0][0]).equals(
							"0")) {

						AssetEmployee bean1 = new AssetEmployee();
						bean1.setAssetCode(checkNull(String
								.valueOf(result[i][0])));
						bean1.setAsstHdType(checkNull(String
								.valueOf(result[i][1])));
						bean1.setSubTypeCodeIterator(checkNull(String
								.valueOf(result[i][2])));
						bean1.setAssetSubTypeIterator(checkNull(String
								.valueOf(result[i][3])));
						bean1.setAssetRequiredIterator(checkNull(("" + (Float
								.parseFloat("" + result[i][4]) - Float
								.parseFloat(String
										.valueOf(quantityAvailable[0][0]))))));
						bean1.setReturnFlagIterator(checkNull(String
								.valueOf(result[i][5])));
						bean1.setApplCode(checkNull(String
								.valueOf(result[i][6])));
						bean1.setEmpWarehouse(String
								.valueOf(warehouseName[0][1]));
						logger.info("warehousename=====" + warehouseName[0][0]);
						otherWarehouseList.add(bean1);

						AssetEmployee bean2 = new AssetEmployee();
						bean2.setAssetCode(checkNull(String
								.valueOf(result[i][0])));
						bean2.setAsstHdType(checkNull(String
								.valueOf(result[i][1])));
						bean2.setSubTypeCodeIterator(checkNull(String
								.valueOf(result[i][2])));
						bean2.setAssetSubTypeIterator(checkNull(String
								.valueOf(result[i][3])));
						bean2.setAssetRequiredIterator(checkNull(("" + Float
								.parseFloat(String
										.valueOf(quantityAvailable[0][0])))));
						bean2.setReturnFlagIterator(checkNull(String
								.valueOf(result[i][5])));
						bean2.setApplCode(checkNull(String
								.valueOf(result[i][6])));
						bean2.setEmpWarehouse(String
								.valueOf(warehouseName[0][1]));
						logger.info("warehousename=====" + warehouseName[0][0]);
						tableList.add(bean2);

					} else if (String.valueOf(quantityAvailable[0][0]).equals(
							"0")) {
						AssetEmployee bean1 = new AssetEmployee();
						bean1.setAssetCode(checkNull(String
								.valueOf(result[i][0])));
						bean1.setAsstHdType(checkNull(String
								.valueOf(result[i][1])));
						bean1.setSubTypeCodeIterator(checkNull(String
								.valueOf(result[i][2])));
						bean1.setAssetSubTypeIterator(checkNull(String
								.valueOf(result[i][3])));
						bean1.setAssetRequiredIterator(checkNull(""
								+ result[i][4]));
						bean1.setReturnFlagIterator(checkNull(String
								.valueOf(result[i][5])));
						bean1.setApplCode(checkNull(String
								.valueOf(result[i][6])));
						bean1.setEmpWarehouse(String
								.valueOf(warehouseName[0][1]));
						logger.info("warehousename=====" + warehouseName[0][0]);
						otherWarehouseList.add(bean1);
					}
				} // end of if
				/*
				 * if the Inventory type is Itemized
				 * 
				 */
				else {

					String quantityAvailableQuery = "SELECT  NVL(SUM(HRMS_ASSET_MASTER_DTL.ASSET_AVAILABLE),0) FROM HRMS_ASSET_MASTER_DTL "
							+ "INNER JOIN HRMS_ASSET_MASTER ON(HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE)"
							+ "WHERE ASSET_SUBTYPE_CODE ="
							+ String.valueOf(result[i][2])
							+ " AND ASSET_WAREHOUSE_CODE ="
							+ bean.getEmpWarehouseCode();
					Object quantityAvailable[][] = getSqlModel()
							.getSingleResult(quantityAvailableQuery);
					try {
						if (Float.parseFloat(String
								.valueOf(quantityAvailable[0][0])) >= Float
								.parseFloat("" + result[i][4])) {

							for (int j = 0; j < Integer.parseInt(String
									.valueOf(result[i][4])); j++) {
								AssetEmployee bean1 = new AssetEmployee();
								bean1.setAssetCode(checkNull(String
										.valueOf(result[i][0])));
								bean1.setAsstHdType(checkNull(String
										.valueOf(result[i][1])));
								bean1.setSubTypeCodeIterator(checkNull(String
										.valueOf(result[i][2])));
								bean1.setAssetSubTypeIterator(checkNull(String
										.valueOf(result[i][3])));
								bean1.setReturnFlagIterator(checkNull(String
										.valueOf(result[i][5])));
								bean1.setAssetRequiredIterator("1");
								bean1.setApplCode(checkNull(String
										.valueOf(result[i][6])));
								bean1.setEmpWarehouse(String
										.valueOf(warehouseName[0][1]));
								tableList.add(bean1);
							} // end of for loop

						} else if (!String.valueOf(quantityAvailable[0][0])
								.equals("0")) {
							for (int j = 0; j < (Integer.parseInt(""
									+ result[i][4]) - Integer.parseInt(String
									.valueOf(quantityAvailable[0][0]))); j++) {
								AssetEmployee bean1 = new AssetEmployee();
								bean1.setAssetCode(checkNull(String
										.valueOf(result[i][0])));
								bean1.setAsstHdType(checkNull(String
										.valueOf(result[i][1])));
								bean1.setSubTypeCodeIterator(checkNull(String
										.valueOf(result[i][2])));
								bean1.setAssetSubTypeIterator(checkNull(String
										.valueOf(result[i][3])));
								bean1.setAssetRequiredIterator("1");
								bean1.setReturnFlagIterator(checkNull(String
										.valueOf(result[i][5])));
								bean1.setApplCode(checkNull(String
										.valueOf(result[i][6])));
								bean1.setEmpWarehouse(String
										.valueOf(warehouseName[0][1]));
								logger.info("warehousename====="
										+ warehouseName[0][0]);
								otherWarehouseList.add(bean1);
							}
							for (int j = 0; j < Integer.parseInt(String
									.valueOf(quantityAvailable[0][0])); j++) {
								AssetEmployee bean1 = new AssetEmployee();
								bean1.setAssetCode(checkNull(String
										.valueOf(result[i][0])));
								bean1.setAsstHdType(checkNull(String
										.valueOf(result[i][1])));
								bean1.setSubTypeCodeIterator(checkNull(String
										.valueOf(result[i][2])));
								bean1.setAssetSubTypeIterator(checkNull(String
										.valueOf(result[i][3])));
								bean1.setAssetRequiredIterator("1");
								bean1.setReturnFlagIterator(checkNull(String
										.valueOf(result[i][5])));
								bean1.setApplCode(checkNull(String
										.valueOf(result[i][6])));
								bean1.setEmpWarehouse(String
										.valueOf(warehouseName[0][1]));
								logger.info("warehousename====="
										+ warehouseName[0][0]);
								tableList.add(bean1);
							}
						} else if (String.valueOf(quantityAvailable[0][0])
								.equals("0")) {
							for (int j = 0; j < (Integer.parseInt(""
									+ result[i][4])); j++) {
								AssetEmployee bean1 = new AssetEmployee();
								bean1.setAssetCode(checkNull(String
										.valueOf(result[i][0])));
								bean1.setAsstHdType(checkNull(String
										.valueOf(result[i][1])));
								bean1.setSubTypeCodeIterator(checkNull(String
										.valueOf(result[i][2])));
								bean1.setAssetSubTypeIterator(checkNull(String
										.valueOf(result[i][3])));
								bean1.setAssetRequiredIterator("1");
								bean1.setReturnFlagIterator(checkNull(String
										.valueOf(result[i][5])));
								bean1.setApplCode(checkNull(String
										.valueOf(result[i][6])));
								bean1.setEmpWarehouse(String
										.valueOf(warehouseName[0][1]));
								logger.info("warehousename====="
										+ warehouseName[0][0]);
								otherWarehouseList.add(bean1);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} // end of else

			} // end of for loop
			bean.setAssignList(tableList);
			bean.setOtherWarehouseList(otherWarehouseList);
			bean.setTableLength(String.valueOf(tableList.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * method name : saveAssignment purpose : assign the asset available to the
	 * Employee return type : void parameter : AssetEmployee instance
	 */

	/**
	 * @param bean
	 * @param inventoryCode
	 * @param quantityAssigned
	 * @param categoryCode
	 * @param subTypeCode
	 * @param masterCode
	 * @param applCode
	 * @param invType
	 * @param assetAssignDate
	 * @param assignQuantity
	 * @param assetItemcode
	 * @return
	 */
	public boolean saveAssignment(AssetEmployee bean, String[] inventoryCode,
			String[] quantityAssigned, String[] categoryCode,
			String[] subTypeCode, String[] masterCode, String[] applCode,
			String[] invType, String[] assetAssignDate,
			String[] assignQuantity, String[] assetItemcode) {
		Object[][] insertAssignment = new Object[1][9];
		boolean result = false;

		for (int i = 0; i < inventoryCode.length; i++) {
			if (!inventoryCode[i].equals("")) {
				insertAssignment[0][0] = bean.getCode();
				insertAssignment[0][1] = categoryCode[i];
				insertAssignment[0][2] = subTypeCode[i];
				insertAssignment[0][3] = inventoryCode[i];
				insertAssignment[0][4] = masterCode[i];

				insertAssignment[0][5] = assetAssignDate[i];

				insertAssignment[0][6] = assignQuantity[i];
				insertAssignment[0][7] = bean.getEmpCode();

				insertAssignment[0][8] = assetItemcode[i];

				result = getSqlModel().singleExecute(getQuery(13),
						insertAssignment); // insert data in
				// HRMS_ASSET_APP_ASSIGNEMENT
				if (result) {
					Object[][] quantityUpdate = new Object[1][4];
					quantityUpdate[0][0] = quantityAssigned[i];
					logger.info(quantityUpdate[0][0]
							+ "===quantityUpdate [0][0]");
					quantityUpdate[0][1] = applCode[i];
					quantityUpdate[0][2] = categoryCode[i];
					quantityUpdate[0][3] = subTypeCode[i];
					getSqlModel().singleExecute(getQuery(18), quantityUpdate); // update
					// the
					// ASSET_ASSIGN_QTY
					// from
					// HRMS_ASSET_APPL_DETAILS
				} // end of if
				/*
				 * update the inventory status in the ASSET_MASTER_DTL to assign
				 * where Inventory type is Itemized Inventory
				 * 
				 */
				if (String.valueOf(invType[i]).equals("I")) {
					Object[][] upadteStatus = new Object[1][3];
					upadteStatus[0][0] = masterCode[i];
					upadteStatus[0][1] = inventoryCode[i];
					upadteStatus[0][2] = getWarehouseForEmp(bean);
					getSqlModel().singleExecute(getQuery(19), upadteStatus);
				} // end of if

				/*
				 * UPDATE THE QUANTITY ASSIGNED & QUANTITY AVAILABLE IN THE
				 * ASSET_MASTER TABLE
				 */
				if (result)
					updateInventory(masterCode[i], inventoryCode[i],
							quantityAssigned[i], bean);

			} // end of if

		} // end of for loop

		/*
		 * send mail to the Asset Applicant
		 * 
		 */
		if (result) {
			try {
				// to_mailIds[0][0] = bean.getEmpCode();
				// from_mailIds[0][0] = bean.getUserEmpId();
				//
				// MailUtility mail = new MailUtility();
				// mail.initiate(context, session);
				// logger.info("to_mailIds[0][0]" + to_mailIds[0][0]);
				// logger.info("from_mailIds[0][0]" + from_mailIds[0][0]);
				// mail.sendMail(to_mailIds, from_mailIds, "AssetAssignment",
				// "",
				// "AS");
				//
				// mail.terminate();
				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);

				String assigner = getAssignCode(bean.getEmpCode());
				template
						.setEmailTemplate("ASSET APPLICATION -ASSIGNER TO APPLICANT");
				MypageProcessManagerAlertsModel processAlerts = new MypageProcessManagerAlertsModel();
				
				processAlerts.initiate(context, session);
				String module = "Asset";
				
				String applnID = bean.getCode();
				processAlerts.removeProcessAlert(String.valueOf(applnID),module);
						
				//String module = "Asset Assignment";
				//String msgType = "A";
				String alertLevel = "1";
				String keepInfo=getKeepInfoIdList(bean.getHiddenappCode());
				System.out
						.println("The applicaion  code :---------------------: = "
								+ bean.getHiddenappCode());
				template.getTemplateQueries();

				EmailTemplateQuery templateQuery5 = template
						.getTemplateQuery(1); // FROM EMAIL
				templateQuery5.setParameter(1, bean.getEmpCode());

				EmailTemplateQuery templateQuery6 = template
						.getTemplateQuery(2); // TO EMAIL
				templateQuery6.setParameter(1, assigner);

				EmailTemplateQuery templateQuery7 = template
						.getTemplateQuery(3);
				templateQuery7.setParameter(1, bean.getHiddenappCode());

				EmailTemplateQuery templateQuery8 = template
						.getTemplateQuery(4);
				templateQuery8.setParameter(1, bean.getHiddenappCode());

				EmailTemplateQuery templateQuery9 = template
						.getTemplateQuery(5);
				templateQuery9.setParameter(1, assigner);

				EmailTemplateQuery templateQuery10 = template
						.getTemplateQuery(6);
				templateQuery10.setParameter(1, bean.getHiddenappCode());

				EmailTemplateQuery templateQuery11 = template
						.getTemplateQuery(7);
				templateQuery11.setParameter(1, bean.getHiddenappCode());
				
				EmailTemplateQuery templateQuery12 = template
				.getTemplateQuery(8);
				templateQuery12.setParameter(1, bean.getHiddenappCode());
				
				EmailTemplateQuery templateQuery13 = template
				.getTemplateQuery(9);
				templateQuery13.setParameter(1, bean.getHiddenappCode());

				template.configMailAlert();
				try {
					template.sendApplicationMailToKeepInfo(keepInfo);
				} catch (Exception e) {
					logger.error(e);
				}
				try {
					//template.sendProcessManagerAlert(assigner, module, msgType,
					//		bean.getHiddenappCode(), alertLevel);
					
					String link = "/asset/AssetEmployee_callforedit.action";
					String linkParam ="hiddencode=" + bean.getCode() + "&hiddenStatus=S";
					template.sendProcessManagerAlert(bean.getEmpCode(), "Asset", "I", bean.getCode(),
								"1", linkParam, link, "Assigned", "",
								"", "", "", assigner);
					template.sendProcessManagerAlert("", "Asset", "I", bean.getCode(),
							"1", linkParam, link, "Assigned", "",
							"", "", assigner, assigner);
					
					template.sendApplicationMail();
				} catch (Exception e) {
					logger.error(e);
				}
				template.clearParameters();
				template.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * 
		 * change application status
		 * 
		 */
		try {
			String applCheckQuery = "SELECT NVL(SUM(ASSET_APPL_QTY) - SUM(ASSET_ASSIGN_QTY),0) FROM HRMS_ASSET_APPL_DETAILS WHERE ASSET_APPL_CODE="
					+ applCode[0];
			Object[][] applCheckObject = getSqlModel().getSingleResult(
					applCheckQuery);
			if (String.valueOf(applCheckObject[0][0]).equals("0")) {
				Object[][] assignerComments = new Object[1][1];
				assignerComments[0][0] = bean.getAssignComments();
				getSqlModel()
						.singleExecute(
								"UPDATE HRMS_ASSET_APPLICATION SET ASSET_STATUS='S', ASSET_ASSIGN_COMMENTS=? WHERE ASSET_APPL_CODE="
										+ applCode[0], assignerComments);

				// ------------------------Process Manager
				// Alert------------------------start
				ProcessManagerAlertsModel processAlerts = new ProcessManagerAlertsModel();
				processAlerts.initiate(context, session);

				String applnID = bean.getCode();
				String module = "Asset";
				//processAlerts.removeProcessAlert(applnID, module);
				// ------------------------Process Manager
				// Alert------------------------end
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in saveAssignment()" + e);
		}

		return result;
	}
	public String getKeepInfoIdList(String applId) {
		String keepInfoId = "0";
		String keepInformedId = " SELECT  ASSET_KEEP_INFORMTO FROM HRMS_ASSET_APPLICATION "
				+ " WHERE ASSET_APPL_CODE=" + applId;

		Object[][] keepInformedObj = getSqlModel().getSingleResult(
				keepInformedId);

		if (keepInformedObj != null && keepInformedObj.length > 0) {
			keepInfoId = String.valueOf(keepInformedObj[0][0]);

		}

		return keepInfoId;
	}

	/*
	 * method name : showList purpose : show the asset required in the list
	 * return type : void parameter : AssetEmployee instance,String []
	 * categoryCode,String [] categoryName,String [] subTypeCode, String []
	 * subTypeName,String [] requiredQuantity,String [] returnFlag
	 */
	public void showList(AssetEmployee bean, String[] categoryCode,
			String[] categoryName, String[] subTypeCode, String[] subTypeName,
			String[] requiredQuantity, String[] returnFlag, String[] partialIt,
			String[] invType) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		/*
		 * sets the assets in the list
		 * 
		 */
		try {
			logger.info("lest length=====" + categoryCode.length);
			for (int i = 0; i < categoryCode.length; i++) {
				AssetEmployee bean1 = new AssetEmployee();
				bean1.setAssetCode(categoryCode[i]);
				bean1.setAsstHdType(categoryName[i]);
				bean1.setAssetSubTypeIterator(subTypeName[i]);
				bean1.setSubTypeCodeIterator(subTypeCode[i]);
				bean1.setAssetRequiredIterator(requiredQuantity[i]);
				bean1.setReturnFlagIterator(returnFlag[i]);
				bean1.setPartialAssignIt(partialIt[i]);
				bean1.setAssetInvType(invType[i]);
				tableList.add(bean1);
			} // end of for loop

			bean.setAssignList(tableList);
			logger.info("AssignList length in model"
					+ bean.getAssignList().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showList1(AssetEmployee bean, String[] categoryCode,
			String[] categoryName, String[] subTypeCode, String[] subTypeName,
			String[] requiredQuantity, String[] assetUnit, String[] partialIt,
			String[] invType) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		/*
		 * sets the assets in the list
		 * 
		 */
		try {
			logger.info("lest length=====" + categoryCode.length);
			for (int i = 0; i < categoryCode.length; i++) {
				AssetEmployee bean1 = new AssetEmployee();
				bean1.setAssetCode(categoryCode[i]);
				bean1.setAsstHdType(categoryName[i]);
				bean1.setAssetSubTypeIterator(subTypeName[i]);
				bean1.setSubTypeCodeIterator(subTypeCode[i]);
				bean1.setAssetRequiredIterator(requiredQuantity[i]);
				bean1.setAssetUnitIterator(assetUnit[i]);
				bean1.setPartialAssignIt(partialIt[i]);
				bean1.setAssetInvTypeIterator(invType[i]);
				tableList.add(bean1);
			} // end of for loop

			bean.setList(tableList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * method name : checkNull purpose : check the string is null or not return
	 * type : String parameter : String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		} // end of else
	}

	/*
	 * get the Warehouse codes to which logged in employee is responsible
	 * 
	 * 
	 * 
	 */

	/*
	 * method name : getWarehouseForEmp purpose : get the applied employee's
	 * warehouse code return type : String parameter : AssetEmployee instance
	 */

	public String getWarehouseForEmp(AssetEmployee bean) {
		String wareHouseReturn = "";

		String warehouseQuery = "SELECT WAREHOUSE_CODE FROM HRMS_WAREHOUSE_BRANCH WHERE WAREHOUSE_BRANCH=(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ bean.getEmpCode() + ")";
		Object[][] warehouseCode = getSqlModel()
				.getSingleResult(warehouseQuery);
		/*
		 * append the warehouse codes in one string
		 */
		try {
			for (int i = 0; i < warehouseCode.length; i++)
				wareHouseReturn += "" + warehouseCode[i][0] + ",";
			wareHouseReturn = wareHouseReturn.substring(0, wareHouseReturn
					.length() - 1);
		} catch (Exception e) {
			wareHouseReturn = "0";
			logger.info("Exception in getWarehouseForEmp()" + e);
		}
		return wareHouseReturn;
	}

	/*
	 * method name : getWarehouseCodes purpose : get the Warehouse code to which
	 * employee who is logged in is responsible return type : String parameter :
	 * AssetEmployee instance
	 */
	public String getWarehouseCodes(AssetEmployee bean) {
		String wareHouseReturn = "";
		String warehouseQuery = "SELECT WAREHOUSE_CODE FROM HRMS_WAREHOUSE_MASTER WHERE WAREHOUSE_RESPONSIBLE_PERSON="
				+ bean.getUserEmpId();

		Object[][] warehouseCode = getSqlModel()
				.getSingleResult(warehouseQuery);
		/*
		 * append the warehouse codes in one string
		 */
		try {
			for (int i = 0; i < warehouseCode.length; i++)
				wareHouseReturn += "" + warehouseCode[i][0] + ",";
			wareHouseReturn = wareHouseReturn.substring(0, wareHouseReturn
					.length() - 1);
		} catch (Exception e) {
			wareHouseReturn = "0";
			logger.info("Exception in getWarehouseCodes()" + e);
		}
		return wareHouseReturn;
	}

	/*
	 * method name : showWarehouseListForRequest purpose : show warehouse names
	 * with assets for requesting the assets return type : void parameter :
	 * AssetEmployee instance
	 */

	public void showWarehouseListForRequest(AssetEmployee bean) {
		String query = "SELECT HRMS_ASSET_MASTER_DTL.ASSET_WAREHOUSE_CODE,WAREHOUSE_NAME,HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE,HRMS_ASSET_MASTER_DTL.ASSET_INVENTORY_CODE, "
				+ " HRMS_ASSET_MASTER_DTL.ASSET_AVAILABLE FROM HRMS_ASSET_MASTER "
				+ " INNER JOIN HRMS_ASSET_MASTER_DTL ON (HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE=HRMS_ASSET_MASTER.ASSET_MASTER_CODE) "
				+ " INNER JOIN HRMS_WAREHOUSE_MASTER ON (HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE=HRMS_ASSET_MASTER_DTL.ASSET_WAREHOUSE_CODE) "
				+ " WHERE ASSET_CATEGORY_CODE="
				+ bean.getReqCategoryCode()
				+ " AND ASSET_SUBTYPE_CODE="
				+ bean.getReqSubTypeCode()
				+ " AND HRMS_ASSET_MASTER_DTL.ASSET_WAREHOUSE_CODE NOT IN ("
				+ bean.getEmpWarehouseCode()
				+ ") AND ASSET_ASSISGN_FLAG='U'"
				+ "AND HRMS_ASSET_MASTER_DTL.ASSET_INVENTORY_CODE not in (select ASSET_REQ_INVENTORY from HRMS_ASSET_WAREHOUSE_REQ where ASSET_IS_CANCELED='N')";

		Object[][] warehouseObj = getSqlModel().getSingleResult(query);

		ArrayList<Object> tableList = new ArrayList<Object>();
		/*
		 * sets the warehouse list with available assets inventories in the list
		 */
		for (int i = 0; i < warehouseObj.length; i++) {
			AssetEmployee bean1 = new AssetEmployee();
			bean1.setWarehouseCode(String.valueOf(warehouseObj[i][0]));
			bean1.setWarehouseName(String.valueOf(warehouseObj[i][1]));
			bean1.setMasterCodeReq(String.valueOf(warehouseObj[i][2]));
			bean1.setInventoryCodeReq(String.valueOf(warehouseObj[i][3]));
			bean1.setAvailableReq(String.valueOf(warehouseObj[i][4]));
			tableList.add(bean1);

		} // end of for loop
		bean.setWarehouseList(tableList);
		if (tableList.size() == 0) {
			bean.setNoData("true");
		} // end of if
		else
			bean.setNoData("false");
	}

	/*
	 * method name : saveRequest purpose : save the request for assets from
	 * other warehouse return type : boolean parameter : AssetEmployee
	 * instance,String []destWarehouse,String [] masterCode,String []
	 * inventory,String [] quantityReq
	 */
	public boolean saveRequest(AssetEmployee bean, String[] destWarehouse,
			String[] masterCode, String[] inventory, String[] quantityReq) {
		Object[][] insertdata = new Object[1][5];

		logger.info("empcode==============================="
				+ bean.getEmpCode());
		logger.info("getEmpWarehouseCode==============================="
				+ bean.getEmpWarehouseCode());
		// Object [][]requestCode=getSqlModel().getSingleResult("SELECT
		// NVL(MAX(ASSET_REQUEST_CODE),0)+1 FROM HRMS_ASSET_WAREHOUSE_REQ");
		boolean result = false;
		/*
		 * Adds the asset request to other warehouse
		 */
		for (int i = 0; i < destWarehouse.length; i++) {
			if (!(quantityReq[i].equals(""))) {
				insertdata[0][0] = destWarehouse[i]; // dest warehouse
				insertdata[0][1] = bean.getEmpWarehouseCode(); // source
				// warehouse
				insertdata[0][2] = masterCode[i];
				insertdata[0][3] = inventory[i];
				insertdata[0][4] = quantityReq[i];
				// insertdata [0][5] = requestCode[0][0];
				result = getSqlModel().singleExecute(getQuery(21), insertdata); // insert
				// the
				// data
				// into
				// HRMS_ASSET_APP_ASSIGNEMENT

				if (result) {
					try {
						to_mailIds[0][0] = getAssignerCode(bean
								.getEmpWarehouseCode());
						from_mailIds[0][0] = getAssignerCode(destWarehouse[i]);

						MailUtility mail = new MailUtility();
						mail.initiate(context, session);
						logger.info("to_mailIds[0][0]" + to_mailIds[0][0]);
						logger.info("from_mailIds[0][0]" + from_mailIds[0][0]);
						mail.sendMail(to_mailIds, from_mailIds,
								"AssetTransfer", "", "RQ");

						mail.terminate();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} // end of if
			} // end of for loop

		}
		return result;

	}

	/*
	 * method name : updateInventory purpose : updates the inventory details
	 * from asset master after assigning assets return type : void parameter :
	 * String masterCode,String inventoryCode,String
	 * quantityAssigned,AssetEmployee instance
	 */
	public void updateInventory(String masterCode, String inventoryCode,
			String quantityAssigned, AssetEmployee bean) {

		Object[][] invCodeUpdate = new Object[1][3];
		invCodeUpdate[0][0] = quantityAssigned;
		invCodeUpdate[0][1] = quantityAssigned;
		invCodeUpdate[0][2] = masterCode;
		logger.info("quantity assignedin master table==" + quantityAssigned);
		getSqlModel().singleExecute(getQuery(15), invCodeUpdate);
		/*
		 * 
		 * UPDATE QUANTITY AVAILABLE IN MASTER_DTL
		 * 
		 */
		Object[][] invCodeDtlUpdate = new Object[1][4];
		invCodeDtlUpdate[0][0] = quantityAssigned;
		invCodeDtlUpdate[0][1] = masterCode;
		invCodeDtlUpdate[0][2] = inventoryCode;
		invCodeDtlUpdate[0][3] = getWarehouseForEmp(bean);
		logger.info("invCodeDtlUpdate[0][0]==" + invCodeDtlUpdate[0][0]);
		logger.info("invCodeDtlUpdate[0][1]==" + invCodeDtlUpdate[0][1]);
		logger.info("invCodeDtlUpdate[0][2]==" + invCodeDtlUpdate[0][2]);
		logger.info("invCodeDtlUpdate[0][3]==" + invCodeDtlUpdate[0][3]);
		getSqlModel().singleExecute(getQuery(20), invCodeDtlUpdate);

		/*
		 * CHECK THE AVAILABLE QUANTITY OF INVENTORY OF TYPE SAME FOR ALL &
		 * ACCORDINGLY CHANGE THE STATUS TO ASSIGN OR UNASSIGNED
		 * 
		 */
		String invAvailableQuery = "SELECT ASSET_INVENTORY_CODE,ASSET_MASTER_CODE FROM HRMS_ASSET_MASTER_DTL WHERE ASSET_AVAILABLE=0 AND ASSET_ASSISGN_FLAG NOT IN('L','D')  AND ASSET_MASTER_CODE ="
				+ masterCode;
		Object[][] invDtlAvailability = getSqlModel().getSingleResult(
				invAvailableQuery);

		for (int j = 0; j < invDtlAvailability.length; j++) {
			String invStatusUpdate = "UPDATE HRMS_ASSET_MASTER_DTL SET ASSET_ASSISGN_FLAG='A' WHERE ASSET_INVENTORY_CODE='"
					+ String.valueOf(invDtlAvailability[j][0])
					+ "' AND ASSET_MASTER_CODE="
					+ String.valueOf(invDtlAvailability[j][1]);
			getSqlModel().singleExecute(invStatusUpdate);
		} // end of for loop

		/*
		 * 
		 * 
		 */
		String query = "SELECT ASSET_AVAILABLE FROM HRMS_ASSET_MASTER WHERE ASSET_MASTER_CODE="
				+ masterCode;
		Object assetAvailable[][] = getSqlModel().getSingleResult(query);
		if (String.valueOf(assetAvailable[0][0]).equals("0")) {
			Object[][] master = new Object[1][1];
			master[0][0] = masterCode;
			getSqlModel().singleExecute(getQuery(16), master); // update
			// inventory
			// status to
			// assigned
			Object[][] upadteStatus = new Object[1][3];
			upadteStatus[0][0] = masterCode;
			upadteStatus[0][1] = inventoryCode;
			upadteStatus[0][2] = getWarehouseForEmp(bean);
			getSqlModel().singleExecute(getQuery(19), upadteStatus);

		} // end of if

	}

	/*
	 * method name : updateInventoryForReq purpose : updates the inventory
	 * details from asset master after saving request return type : void
	 * parameter : String masterCode,String inventoryCode,String
	 * quantityAssigned
	 */
	public void updateInventoryForReq(String masterCode, String inventoryCode,
			String quantityAssigned) {

		/*
		 * 
		 * UPDATE QUANTITY AVAILABLE IN MASTER_DTL
		 * 
		 */
		Object[][] invCodeDtlUpdate = new Object[1][3];
		invCodeDtlUpdate[0][0] = quantityAssigned;
		invCodeDtlUpdate[0][1] = masterCode;
		invCodeDtlUpdate[0][2] = inventoryCode;
		getSqlModel().singleExecute(getQuery(22), invCodeDtlUpdate);

		/*
		 * CHECK THE AVAILABLE QUANTITY OF INVENTORY OF TYPE SAME FOR ALL &
		 * ACCORDINGLY CHANGE THE STATUS TO ASSIGN OR UNASSIGNED
		 * 
		 */
		String invAvailableQuery = "SELECT ASSET_INVENTORY_CODE,ASSET_MASTER_CODE FROM HRMS_ASSET_MASTER_DTL WHERE ASSET_AVAILABLE=0 AND ASSET_MASTER_CODE ="
				+ masterCode;
		Object[][] invDtlAvailability = getSqlModel().getSingleResult(
				invAvailableQuery);

		for (int j = 0; j < invDtlAvailability.length; j++) {
			String invStatusUpdate = "UPDATE HRMS_ASSET_MASTER_DTL SET ASSET_ASSISGN_FLAG='A' WHERE ASSET_INVENTORY_CODE='"
					+ String.valueOf(invDtlAvailability[j][0])
					+ "' AND ASSET_MASTER_CODE="
					+ String.valueOf(invDtlAvailability[j][1]);
			getSqlModel().singleExecute(invStatusUpdate);
		} // end of for loop

	}

	/*
	 * method name : updateWarehouseCode purpose : updates the warehouse code
	 * from HRMS_ASSET_MASTER_DTL return type : void parameter : AssetEmployee
	 * instance,String []inventoryCode,String []masterCode,String
	 * []quantityAssigned,String []sourceWarehouse,String []reqCode
	 */
	public boolean updateWarehouseCode(AssetEmployee bean,
			String[] inventoryCode, String[] masterCode,
			String[] quantityAssigned, String[] sourceWarehouse,
			String[] reqCode, String[] destiWarehouse) {
		String addInvForSourcequery = "INSERT INTO HRMS_ASSET_MASTER_DTL(ASSET_MASTER_CODE,ASSET_INVENTORY_CODE,ASSET_WAREHOUSE_CODE,ASSET_ASSISGN_FLAG,ASSET_QUANTITY,ASSET_AVAILABLE)"
				+ " VALUES(?,?,?,'U',?,?)";
		String updateRequestQuery = "UPDATE HRMS_ASSET_WAREHOUSE_REQ SET ASSET_QUANTITY_ASSIGNED=ASSET_QUANTITY_ASSIGNED+? WHERE ASSET_REQUEST_CODE=?";

		boolean result = false;
		for (int i = 0; i < inventoryCode.length; i++) {

			if (!String.valueOf(quantityAssigned[i]).equals("")) {
				updateInventoryForReq(masterCode[i], inventoryCode[i],
						quantityAssigned[i]); // UPDATE THE QUANTITY & STATUS
				// IN THE DESTINATION WAREHOUSE

				Object[][] addInvForSource = new Object[1][5];
				addInvForSource[0][0] = masterCode[i];
				addInvForSource[0][1] = inventoryCode[i];
				addInvForSource[0][2] = sourceWarehouse[i];
				addInvForSource[0][3] = quantityAssigned[i];
				addInvForSource[0][4] = quantityAssigned[i];
				result = getSqlModel().singleExecute(addInvForSourcequery,
						addInvForSource); // ADD INVENTORY IN THE SOURCE
				// WAREHOUSE

				Object[][] updateRequest = new Object[1][2];
				updateRequest[0][0] = quantityAssigned[i];
				updateRequest[0][0] = reqCode[i];
				result = getSqlModel().singleExecute(updateRequestQuery,
						updateRequest);
				logger.info("result in transfer==" + result);

			} // end of if
		} // end of for loop

		return result;
	}

	/*
	 * method name : getAssignerCode purpose : get the Asset assigner code for
	 * particular warehouse return type : String parameter : String
	 * warehouseCode
	 */
	public String getAssignerCode(String warehouseCode) {
		String assignerCode = "";

		String query = "SELECT WAREHOUSE_RESPONSIBLE_PERSON FROM HRMS_WAREHOUSE_MASTER "
				+ " WHERE WAREHOUSE_CODE=" + warehouseCode;

		Object[][] assignerObj = getSqlModel().getSingleResult(query);

		assignerCode = String.valueOf(assignerObj[0][0]);
		return assignerCode;
	}

	public boolean rejectApplication(AssetEmployee bean) {
		boolean result = false;

		String rejectQuery = "UPDATE HRMS_ASSET_APPLICATION SET ASSET_STATUS = 'R', ASSET_ASSIGN_COMMENTS= '"
				+ checkNull(bean.getAssignComments())
				+ "' WHERE ASSET_APPL_CODE =" + bean.getCode();

		result = getSqlModel().singleExecute(rejectQuery);
		/*
		 * if(result){ try { to_mailIds[0][0]= bean.getEmpCode();
		 * from_mailIds[0][0]=bean.getUserEmpId(); mailMsg = "Reason for
		 * rejection : "+checkNull(bean.getAssignComments()); MailUtility
		 * mail=new MailUtility(); mail.initiate(context, session);
		 * logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
		 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
		 * mail.sendMailWithMsg(to_mailIds, from_mailIds,"AssetAssignment",
		 * mailMsg, "RJ");
		 * 
		 * mail.terminate(); } catch (Exception e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } }
		 */
		return result;
	}

	public boolean sendBackToEmp(AssetEmployee bean) {
		boolean result = false;

		String approverQuery = "SELECT ASSET_APPROVER FROM HRMS_ASSET_PATH WHERE ASSET_APPL_CODE ="
				+ bean.getCode() + " AND ASSET_PATH_ID = 1";
		String sentBackQuery = "UPDATE HRMS_ASSET_APPLICATION SET ASSET_STATUS = 'B',ASSET_APPL_LEVEL=1,ASSET_APPL_APPROVER=("
				+ approverQuery
				+ "),ASSET_ASSIGN_COMMENTS='"
				+ checkNull(bean.getAssignComments())
				+ "'  WHERE ASSET_APPL_CODE =" + bean.getCode();

		result = getSqlModel().singleExecute(sentBackQuery);
		/*
		 * if(result){ try { to_mailIds[0][0]= bean.getEmpCode();
		 * from_mailIds[0][0]=bean.getUserEmpId(); mailMsg = "Reason for sent
		 * back : "+checkNull(bean.getAssignComments()); MailUtility mail=new
		 * MailUtility(); mail.initiate(context, session);
		 * logger.info("to_mailIds[0][0]"+to_mailIds[0][0]);
		 * logger.info("from_mailIds[0][0]"+from_mailIds[0][0]);
		 * mail.sendMailWithMsg(to_mailIds, from_mailIds,"AssetAssignment",
		 * mailMsg, "SB");
		 * 
		 * mail.terminate(); } catch (Exception e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } }
		 */
		return result;
	}

	public void checkAssignment(AssetEmployee bean) {
		String query = "SELECT ASSET_APPL_CODE FROM HRMS_ASSET_APPL_DETAILS WHERE ASSET_ASSIGN_QTY !=0 AND "
				+ " ASSET_APPL_CODE=" + bean.getCode();

		Object[][] assignObject = getSqlModel().getSingleResult(query);
		try {
			logger.info("assignObject===" + assignObject.length);
			if (assignObject != null && assignObject.length > 0) {
				bean.setPartialAssign("true");
			}
		} catch (Exception e) {
			bean.setPartialAssign("false");
		}
	}

	public void showAssignedList(AssetEmployee bean, String[] categoryCode,
			String[] categoryName, String[] subTypeCode, String[] subTypeName,
			String[] assignedQuantity, String[] assetUnit) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		/*
		 * sets the assets in the list
		 * 
		 */
		try {
			for (int i = 0; i < categoryCode.length; i++) {
				AssetEmployee bean1 = new AssetEmployee();
				bean1.setAssetCodeAssigned(categoryCode[i]);
				bean1.setAsstHdTypeAssigned(categoryName[i]);
				bean1.setAssetSubTypeIteratorAssigned(subTypeName[i]);
				bean1.setSubTypeCodeIteratorAssigned(subTypeCode[i]);
				bean1.setAssetAssignedIterator(assignedQuantity[i]);
				bean1.setAssetUnitIteratorAssigned(assetUnit[i]);
				tableList.add(bean1);
			} // end of for loop

			bean.setAssignedAssetList(tableList);
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("" + e);
		}
	}

	public Object[][] getApproverList(AssetEmployee bean) {
		String approverQuery = "SELECT ASSET_APPROVER FROM HRMS_ASSET_PATH WHERE ASSET_PATH_STATUS ='A' AND ASSET_APPL_CODE="
				+ bean.getCode();
		Object[][] approverList = getSqlModel().getSingleResult(approverQuery);

		return approverList;
	}

	public void getAllTypeOfApplications(AssetEmployee bean,
			HttpServletRequest request, String userEmpId) {
		String draftedQuery = " SELECT ASSET_APPL_CODE, HRMS_EMP_OFFC.EMP_TOKEN, "
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME EMP_NAME, "
				+ " TO_CHAR(HRMS_ASSET_APPLICATION.ASSET_APPL_DATE,'DD-MM-YYYY' ), ASSET_STATUS "
				+ " ,ASSET_APPL_NO FROM HRMS_ASSET_APPLICATION "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ASSET_APPLICATION.ASSET_EMP_ID) "
				+ " WHERE HRMS_ASSET_APPLICATION.ASSET_STATUS IN ('D','W') AND HRMS_ASSET_APPLICATION.ASSET_DRAFT_BY ="
				+ bean.getUserEmpId()
				+ " ORDER BY HRMS_ASSET_APPLICATION.ASSET_APPL_DATE DESC, HRMS_ASSET_APPLICATION.ASSET_APPL_CODE DESC ";

		String submittedQuery = " SELECT ASSET_APPL_CODE, HRMS_EMP_OFFC.EMP_TOKEN, "
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME EMP_NAME, "
				+ " TO_CHAR(HRMS_ASSET_APPLICATION.ASSET_APPL_DATE,'DD-MM-YYYY'), ASSET_STATUS "
				+ " ,ASSET_APPL_NO FROM HRMS_ASSET_APPLICATION "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ASSET_APPLICATION.ASSET_EMP_ID) "
				+ " WHERE HRMS_ASSET_APPLICATION.ASSET_STATUS IN ('P') ";
		if (bean.isGeneralFlag())
			submittedQuery += " AND HRMS_ASSET_APPLICATION.ASSET_EMP_ID ="
					+ bean.getUserEmpId();
		submittedQuery += " ORDER BY HRMS_ASSET_APPLICATION.ASSET_APPL_DATE DESC, HRMS_ASSET_APPLICATION.ASSET_APPL_CODE DESC ";

		String returnedQuery = " SELECT ASSET_APPL_CODE, HRMS_EMP_OFFC.EMP_TOKEN, "
				+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME EMP_NAME, "
				+ " TO_CHAR(HRMS_ASSET_APPLICATION.ASSET_APPL_DATE,'DD-MM-YYYY'), ASSET_STATUS "
				+ " ,ASSET_APPL_NO FROM HRMS_ASSET_APPLICATION "
				+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ASSET_APPLICATION.ASSET_EMP_ID) "
				+ " WHERE HRMS_ASSET_APPLICATION.ASSET_STATUS IN ('B') ";
		if (bean.isGeneralFlag())
			returnedQuery += " AND HRMS_ASSET_APPLICATION.ASSET_EMP_ID ="
					+ bean.getUserEmpId();
		else
			returnedQuery += " AND HRMS_ASSET_APPLICATION.ASSET_DRAFT_BY ="
					+ bean.getUserEmpId();
		returnedQuery += " ORDER BY HRMS_ASSET_APPLICATION.ASSET_APPL_DATE DESC, HRMS_ASSET_APPLICATION.ASSET_APPL_CODE DESC ";

		Object[][] draftObj = getSqlModel().getSingleResult(draftedQuery);
		Object[][] submitObj = getSqlModel().getSingleResult(submittedQuery);
		Object[][] returnObj = getSqlModel().getSingleResult(returnedQuery);

		// DraftData
		logger.info("Draft Data length " + draftObj.length);
		logger.info("submit Data length " + submitObj.length);
		logger.info("return Data length " + returnObj.length);
		if (draftObj != null && draftObj.length > 0) {
			ArrayList draftList = new ArrayList();
			int c1 = 0;
			for (int i = 0; i < draftObj.length; i++) {
				AssetEmployee bean1 = new AssetEmployee();
				bean1.setCode(checkNull(String.valueOf(draftObj[i][0])));
				bean1.setEmpToken(checkNull(String.valueOf(draftObj[i][1])));
				bean1.setEmpName(checkNull(String.valueOf(draftObj[i][2])));
				bean1.setAssignDate1(checkNull(String.valueOf(draftObj[i][3])));
				bean1.setStatus(checkNull(String.valueOf(draftObj[i][4])));
				bean1.setAssetId(checkNull(String.valueOf(draftObj[i][5])));
				draftList.add(bean1);
				++c1;
			}// End of for
			logger.info("i = " + c1);
			logger.info("draftList.length " + draftList.size());
			bean.setDraftList(draftList);
		}// End of if
		// Submitted Data
		if (submitObj != null && submitObj.length > 0) {
			ArrayList submitList = new ArrayList();
			int c2 = 0;
			for (int i = 0; i < submitObj.length; i++) {
				AssetEmployee bean1 = new AssetEmployee();
				bean1.setCode(checkNull(String.valueOf(submitObj[i][0])));
				bean1.setEmpToken(checkNull(String.valueOf(submitObj[i][1])));
				bean1.setEmpName(checkNull(String.valueOf(submitObj[i][2])));
				bean1
						.setAssignDate1(checkNull(String
								.valueOf(submitObj[i][3])));
				bean1.setStatus(checkNull(String.valueOf(submitObj[i][4])));
				bean1.setAssetId(checkNull(String.valueOf(submitObj[i][5])));
				submitList.add(bean1);
				++c2;
			}// End of for
			logger.info("i = " + c2);
			bean.setSubmittedList(submitList);
		}// End of if

		// Returned Data
		if (returnObj != null && returnObj.length > 0) {
			ArrayList returnList = new ArrayList();
			int c3 = 0;
			for (int i = 0; i < returnObj.length; i++) {
				AssetEmployee bean1 = new AssetEmployee();
				bean1.setCode(checkNull(String.valueOf(returnObj[i][0])));
				bean1.setEmpToken(checkNull(String.valueOf(returnObj[i][1])));
				bean1.setEmpName(checkNull(String.valueOf(returnObj[i][2])));
				bean1
						.setAssignDate1(checkNull(String
								.valueOf(returnObj[i][3])));
				bean1.setStatus(checkNull(String.valueOf(returnObj[i][4])));
				bean1.setAssetId(checkNull(String.valueOf(returnObj[i][5])));
				returnList.add(bean1);
				++c3;
			}// End of for
			logger.info("i = " + c3);
			bean.setReturnedList(returnList);
		}// End of if
	}// End of function

	public void getApprovedList(AssetEmployee bean, HttpServletRequest request,
			String userEmpId) {
		// TODO Auto-generated method stub

		try {
			String approvedQuery = " SELECT ASSET_APPL_CODE, HRMS_EMP_OFFC.EMP_TOKEN, "
					+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME EMP_NAME, "
					+ " TO_CHAR(HRMS_ASSET_APPLICATION.ASSET_APPL_DATE,'DD-MM-YYYY'), ASSET_STATUS "
					+ " ,ASSET_APPL_NO FROM HRMS_ASSET_APPLICATION "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ASSET_APPLICATION.ASSET_EMP_ID) "
					+ " WHERE HRMS_ASSET_APPLICATION.ASSET_STATUS IN ('A') ";
			if (bean.isGeneralFlag())
				approvedQuery += " AND HRMS_ASSET_APPLICATION.ASSET_EMP_ID ="
						+ bean.getUserEmpId();
			// else
			// approvedQuery += " AND HRMS_ASSET_APPLICATION.ASSET_DRAFT_BY ="+
			// bean.getUserEmpId();
			approvedQuery += " ORDER BY HRMS_ASSET_APPLICATION.ASSET_APPL_DATE DESC, HRMS_ASSET_APPLICATION.ASSET_APPL_CODE DESC ";
			Object[][] approvedObj = getSqlModel().getSingleResult(
					approvedQuery);
			String pageIndex[] = Utility.doPaging(bean.getMyPage(),
					approvedObj.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}// End of if
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("pageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1"))
				bean.setMyPage("1");
			logger.info("approved Data length " + approvedObj.length);
			if (approvedObj != null && approvedObj.length > 0) {
				ArrayList approvedList = new ArrayList();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					AssetEmployee bean1 = new AssetEmployee();
					bean1.setCode(checkNull(String.valueOf(approvedObj[i][0])));
					bean1.setEmpToken(checkNull(String
							.valueOf(approvedObj[i][1])));
					bean1.setEmpName(checkNull(String
							.valueOf(approvedObj[i][2])));
					bean1.setAssignDate1(checkNull(String
							.valueOf(approvedObj[i][3])));
					bean1
							.setStatus(checkNull(String
									.valueOf(approvedObj[i][4])));
					bean1.setAssetId(checkNull(String
							.valueOf(approvedObj[i][5])));
					approvedList.add(bean1);
				}// End of for
				bean.setApprovedList(approvedList);
				logger.info("list length : " + approvedList.size());
				bean.setTotalRecords(String.valueOf(approvedList.size()));
				bean.setListLengthApproved("true");
			}// End of if
			else
				bean.setListLengthApproved("false");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void getRejectedList(AssetEmployee bean, HttpServletRequest request,
			String userEmpId) {
		// TODO Auto-generated method stub
		try {
			String rejectedQuery = " SELECT ASSET_APPL_CODE, HRMS_EMP_OFFC.EMP_TOKEN, "
					+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME EMP_NAME, "
					+ " TO_CHAR(HRMS_ASSET_APPLICATION.ASSET_APPL_DATE,'DD-MM-YYYY'), ASSET_STATUS "
					+ " ,ASSET_APPL_NO FROM HRMS_ASSET_APPLICATION "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ASSET_APPLICATION.ASSET_EMP_ID) "
					+ " WHERE HRMS_ASSET_APPLICATION.ASSET_STATUS IN ('R') ";
			if (bean.isGeneralFlag())
				rejectedQuery += " AND HRMS_ASSET_APPLICATION.ASSET_EMP_ID ="
						+ bean.getUserEmpId();
			// else
			// approvedQuery += " AND HRMS_ASSET_APPLICATION.ASSET_DRAFT_BY ="+
			// bean.getUserEmpId();
			rejectedQuery += " ORDER BY HRMS_ASSET_APPLICATION.ASSET_APPL_DATE DESC, HRMS_ASSET_APPLICATION.ASSET_APPL_CODE DESC ";
			Object[][] rejectedObj = getSqlModel().getSingleResult(
					rejectedQuery);

			String[] pageIndexRejected = Utility.doPaging(bean
					.getMyPageRejected(), rejectedObj.length, 20);
			if (pageIndexRejected == null) {
				pageIndexRejected[0] = "0";
				pageIndexRejected[1] = "20";
				pageIndexRejected[2] = "1";
				pageIndexRejected[3] = "1";
				pageIndexRejected[4] = "";
			}

			request.setAttribute("totalPageRejected", Integer.parseInt(String
					.valueOf(pageIndexRejected[2])));
			request.setAttribute("PageNoRejected", Integer.parseInt(String
					.valueOf(pageIndexRejected[3])));
			if (pageIndexRejected[4].equals("1"))
				bean.setMyPageRejected("1");

			if (rejectedObj != null && rejectedObj.length > 0) {
				ArrayList rejectedList = new ArrayList();
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {
					AssetEmployee bean1 = new AssetEmployee();
					bean1.setCode(checkNull(String.valueOf(rejectedObj[i][0])));
					bean1.setEmpToken(checkNull(String
							.valueOf(rejectedObj[i][1])));
					bean1.setEmpName(checkNull(String
							.valueOf(rejectedObj[i][2])));
					bean1.setAssignDate1(checkNull(String
							.valueOf(rejectedObj[i][3])));
					bean1
							.setStatus(checkNull(String
									.valueOf(rejectedObj[i][4])));

					bean1.setAssetId(checkNull(String
							.valueOf(rejectedObj[i][5])));

					rejectedList.add(bean1);
				}// End of for
				bean.setRejectedList(rejectedList);
				bean.setTotalRecords(String.valueOf(rejectedObj.length));
				bean.setListLengthRejected("true");
			}// End of if
			else
				bean.setListLengthRejected("false");
		} catch (Exception e) {
			logger.info("Exception in rejected application  model " + e);
		}
	}

	public void getAssignedList(AssetEmployee bean, HttpServletRequest request,
			String userEmpId) {
		// TODO Auto-generated method stub
		try {
			String assignedQuery = " SELECT ASSET_APPL_CODE, HRMS_EMP_OFFC.EMP_TOKEN, "
					+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME EMP_NAME, "
					+ " TO_CHAR(HRMS_ASSET_APPLICATION.ASSET_APPL_DATE,'DD-MM-YYYY'), ASSET_STATUS "
					+ " ,ASSET_APPL_NO FROM HRMS_ASSET_APPLICATION "
					+ " INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ASSET_APPLICATION.ASSET_EMP_ID) "
					+ " WHERE HRMS_ASSET_APPLICATION.ASSET_STATUS IN ('S') ";
			if (bean.isGeneralFlag())
				assignedQuery += " AND HRMS_ASSET_APPLICATION.ASSET_EMP_ID ="
						+ bean.getUserEmpId();
			assignedQuery += " ORDER BY HRMS_ASSET_APPLICATION.ASSET_APPL_DATE DESC, HRMS_ASSET_APPLICATION.ASSET_APPL_CODE DESC ";
			Object[][] assignedObj = getSqlModel().getSingleResult(
					assignedQuery);

			String[] pageIndexAssigned = Utility.doPaging(bean
					.getMyPageAssigned(), assignedObj.length, 20);
			if (pageIndexAssigned == null) {
				pageIndexAssigned[0] = "0";
				pageIndexAssigned[1] = "20";
				pageIndexAssigned[2] = "1";
				pageIndexAssigned[3] = "1";
				pageIndexAssigned[4] = "";
			}

			request.setAttribute("totalPageAssigned", Integer.parseInt(String
					.valueOf(pageIndexAssigned[2])));
			request.setAttribute("PageNoAssigned", Integer.parseInt(String
					.valueOf(pageIndexAssigned[3])));
			if (pageIndexAssigned[4].equals("1"))
				bean.setMyPageAssigned("1");

			if (assignedObj != null && assignedObj.length > 0) {
				ArrayList assignedList = new ArrayList();
				for (int i = Integer.parseInt(pageIndexAssigned[0]); i < Integer
						.parseInt(pageIndexAssigned[1]); i++) {
					AssetEmployee bean1 = new AssetEmployee();
					bean1.setCode(checkNull(String.valueOf(assignedObj[i][0])));
					bean1.setEmpToken(checkNull(String
							.valueOf(assignedObj[i][1])));
					bean1.setEmpName(checkNull(String
							.valueOf(assignedObj[i][2])));
					bean1.setAssignDate1(checkNull(String
							.valueOf(assignedObj[i][3])));
					bean1
							.setStatus(checkNull(String
									.valueOf(assignedObj[i][4])));

					bean1.setAssetId(checkNull(String
							.valueOf(assignedObj[i][5])));

					assignedList.add(bean1);
				}// End of for
				bean.setAssignedList(assignedList);
				bean.setTotalRecords(String.valueOf(assignedObj.length));
				bean.setListLengthAssigned("true");
			}// End of if
			else
				bean.setListLengthAssigned("false");
		} catch (Exception e) {
			logger.info("Exception in assigned list  model " + e);
		}
	}

	public void getEmployeeDetails(AssetEmployee assetEmployee) {
		// TODO Auto-generated method stub

		String query = " SELECT EMP_TOKEN, (EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME) EMP_NAME, CENTER_NAME, DEPT_NAME, RANK_NAME, "
				+ " EMP_ID FROM HRMS_EMP_OFFC "
				+ " LEFT JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE = HRMS_EMP_OFFC.EMP_TITLE_CODE ) "
				+ " INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID = HRMS_EMP_OFFC. EMP_CENTER ) "
				+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK ) "
				+ " INNER JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT ) "
				+ " WHERE EMP_ID = " + assetEmployee.getEmpCode();
		Object[][] obj = getSqlModel().getSingleResult(query);

		if (obj != null && obj.length > 0) {
			assetEmployee.setEmpToken(checkNull(String.valueOf(obj[0][0])));
			assetEmployee.setEmpName(checkNull(String.valueOf(obj[0][1])));
			assetEmployee.setBranch(checkNull(String.valueOf(obj[0][2])));
			assetEmployee.setDept(checkNull(String.valueOf(obj[0][3])));
			assetEmployee.setDesig(checkNull(String.valueOf(obj[0][4])));
		}// End of if
	}// End of function

	public boolean withdrawApplication(AssetEmployee assetEmployee) {
		boolean result = false;
		try {
			String checkQuery = "SELECT ASSET_STATUS, ASSET_APPL_LEVEL FROM HRMS_ASSET_APPLICATION "
					+ " WHERE ASSET_STATUS = 'P' AND ASSET_APPL_LEVEL = 1 AND "
					+ " ASSET_APPL_CODE = " + assetEmployee.getCode();
			Object[][] obj = getSqlModel().getSingleResult(checkQuery);
			if (obj.length > 0) {
				String query = " UPDATE HRMS_ASSET_APPLICATION SET ASSET_STATUS='W', ASSET_APPL_LEVEL = 1 "
						+ " WHERE ASSET_APPL_CODE = " + assetEmployee.getCode();
				result = getSqlModel().singleExecute(query);
			}
		} catch (Exception e) {

		}
		return result;
	}

	public void getApproverComment(AssetEmployee assetEmployee) {
		// TODO Auto-generated method stub
		String commentsQuery = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME, "
				+ " TO_CHAR(ASSET_APPR_DATE,'DD-MM-YYYY'), "
				+ " DECODE(ASSET_PATH_STATUS,'P','Pending','A','Approved','R','Rejected','B','SentBack'), "
				+ " ASSET_APPL_COMMENTS "
				+ " FROM HRMS_ASSET_PATH "
				+ " INNER JOIN HRMS_EMP_OFFC ON ( HRMS_EMP_OFFC.EMP_ID = HRMS_ASSET_PATH.ASSET_APPROVER) "
				+ " WHERE ASSET_APPL_CODE = "
				+ assetEmployee.getCode()
				+ " ORDER BY ASSET_PATH_ID ";
		ArrayList list = new ArrayList();
		Object obj[][] = getSqlModel().getSingleResult(commentsQuery);
		if (obj != null && obj.length > 0) {
			for (int i = 0; i < obj.length; i++) {
				AssetEmployee bean1 = new AssetEmployee();
				bean1.setApproverName(checkNull(String.valueOf(obj[i][0])));
				bean1.setApprovedDate(checkNull(String.valueOf(obj[i][1])));
				bean1.setApproveStatus(checkNull(String.valueOf(obj[i][2])));
				bean1.setApproverComment(checkNull(String.valueOf(obj[i][3])));

				list.add(bean1);
			}

			// if(assetEmployee.getStatus().equals("P")){
			assetEmployee.setIsSentBack("true");
			assetEmployee.setCommentFlag("true");
			// }
		}
		assetEmployee.setApprList(list);
	}

	public boolean updateAssetDetails(AssetEmployee bean, String[] assetCode,
			String[] subType, String[] assetRequired) {
		// TODO Auto-generated method stub
		logger.info("Asset Applicaton Code ------- " + bean.getCode());
		Object[][] applnCode = new Object[1][1];
		applnCode[0][0] = bean.getCode();
		Object appDtlObj[][] = new Object[assetCode.length][4];
		getSqlModel().singleExecute(getQuery(8), applnCode);
		for (int i = 0; i < assetCode.length; i++) {
			appDtlObj[i][0] = bean.getCode();
			appDtlObj[i][1] = assetCode[i];
			appDtlObj[i][2] = subType[i];
			appDtlObj[i][3] = assetRequired[i];
		}// End of for
		boolean result = getSqlModel().singleExecute(getQuery(2), appDtlObj);
		return result;
	}// End of function

	/*
	 * method name : getAssignerCode purpose : to get the assigner code for
	 * particular application return type : String parameter : String empCode
	 */
	public String getAssignCode(String empCode) {
		String assignerCode = "";

		String query = "SELECT WAREHOUSE_RESPONSIBLE_PERSON FROM HRMS_WAREHOUSE_BRANCH "
				+ " INNER JOIN HRMS_WAREHOUSE_MASTER ON(HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE=HRMS_WAREHOUSE_BRANCH.WAREHOUSE_CODE) "
				+ " WHERE WAREHOUSE_BRANCH=(SELECT EMP_CENTER FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ empCode + ")";

		Object[][] assignerObj = getSqlModel().getSingleResult(query);

		assignerCode = String.valueOf(assignerObj[0][0]);
		return assignerCode;
	}

	public void displayIteratorValueForKeepInformed(String[] srNo,
			String[] empCode, String[] empName, AssetEmployee assetAppBean) {

		try {
			ArrayList<AssetEmployee> leaveList = new ArrayList<AssetEmployee>();
			if (srNo != null) {
				for (int i = 0; i < srNo.length; i++) {
					AssetEmployee assetApp = new AssetEmployee();
					assetApp.setKeepInformedEmpId(empCode[i]);
					assetApp.setKeepInformedEmpName(empName[i]);
					assetApp.setSerialNo(srNo[i]);// sr no
					leaveList.add(assetApp);
				}
				assetAppBean.setKeepInformedList(leaveList);

			}
		} catch (Exception e) {
			logger
					.error("Exception in displayIteratorValueForKeepInformed----------"
							+ e);
		}

	}

	public void setKeepInformed(String[] srNo, String[] empCode,
			String[] empName, AssetEmployee assetApplication) {

		try {
			AssetEmployee assetApp = new AssetEmployee();
			assetApp.setKeepInformedEmpId(assetApplication.getEmployeeId());
			assetApp.setKeepInformedEmpName(assetApplication.getEmployeeName());
			ArrayList<AssetEmployee> assetList = displayNewValueForKeepInformed(
					srNo, empCode, empName, assetApplication);
			assetApp.setSerialNo(String.valueOf(assetList.size() + 1));// sr no
			assetList.add(assetApp);
			assetApplication.setKeepInformedList(assetList);
		} catch (Exception e) {
			logger.error("Exception in setKeepInformed----------" + e);
		}

	}

	private ArrayList<AssetEmployee> displayNewValueForKeepInformed(
			String[] srNo, String[] empCode, String[] empName,
			AssetEmployee assetApplication2) {
		// TODO Auto-generated method stub

		ArrayList<AssetEmployee> assetList = null;
		try {
			assetList = new ArrayList<AssetEmployee>();
			if (srNo != null) {

				for (int i = 0; i < srNo.length; i++) {
					AssetEmployee assetApp = new AssetEmployee();
					assetApp.setKeepInformedEmpId(empCode[i]);
					assetApp.setKeepInformedEmpName(empName[i]);
					assetApp.setSerialNo(srNo[i]);
					assetList.add(assetApp);

				}

			}
		} catch (Exception e) {
			logger
					.error("Exception in displayNewValueForKeepInformed----------"
							+ e);
		}
		return assetList;
	}

	public void removeKeepInformedData(String[] serialNo, String[] empCode,
			String[] empName, AssetEmployee assetApplication) {
		try {
			ArrayList<Object> tableList = new ArrayList<Object>();
			if (serialNo != null) {
				for (int i = 0; i < serialNo.length; i++) {
					AssetEmployee bean = new AssetEmployee();
					bean.setSrNo(String.valueOf(i + 1));
					bean.setKeepInformedEmpId(empCode[i]);
					bean.setKeepInformedEmpName(empName[i]);
					tableList.add(bean);

				}
				tableList.remove(Integer.parseInt(assetApplication
						.getCheckRemove()) - 1);

			}

			assetApplication.setKeepInformedList(tableList);
		} catch (Exception e) {
			logger.error("Exception in removeKeepInformedData------" + e);
		}

	}

	public void setAssetList(HttpServletRequest request, AssetEmployee bean) {
		String assetCode[] = request.getParameterValues("assetCode");
		String asstHdType[] = request.getParameterValues("asstHdType");
		String assetSubTypeIterator[] = request
				.getParameterValues("assetSubTypeIterator");
		String subTypeCodeIterator[] = request
				.getParameterValues("subTypeCodeIterator");
		String assetRequiredIterator[] = request
				.getParameterValues("assetRequiredIterator");
		String assetUnitIterator[] = request
				.getParameterValues("assetUnitIterator");
		String partialAssignIt[] = request
				.getParameterValues("partialAssignIt");
		String assetInvTypeIterator[] = request
				.getParameterValues("assetInvTypeIterator");
		ArrayList<AssetEmployee> assetList = new ArrayList<AssetEmployee>();
		if (assetCode != null && assetCode.length > 0) {
			for (int i = 0; i < assetCode.length; i++) {
				AssetEmployee assetObj = new AssetEmployee();
				assetObj.setAssetCode(assetCode[i]);
				assetObj.setAsstHdType(asstHdType[i]);
				assetObj.setAssetSubTypeIterator(assetSubTypeIterator[i]);
				assetObj.setSubTypeCodeIterator(subTypeCodeIterator[i]);
				assetObj.setAssetRequiredIterator(assetRequiredIterator[i]);
				assetObj.setAssetUnitIterator(assetUnitIterator[i]);
				assetObj.setPartialAssignIt(partialAssignIt[i]);
				assetObj.setAssetInvTypeIterator(assetInvTypeIterator[i]);
				assetList.add(assetObj);

			}
		}
		bean.setList(assetList);
	}

	public void updateKeepInfo(HttpServletRequest request, AssetEmployee bean) {
		try {
			String str = "";
			String keepInformedEmpId[] = request
					.getParameterValues("keepInformedEmpId");
			if (keepInformedEmpId != null && keepInformedEmpId.length > 0) {
				for (int i = 0; i < keepInformedEmpId.length; i++) {
					if (i == 0) {
						str += keepInformedEmpId[i];
					} else {
						str += "," + keepInformedEmpId[i];
					}

				}

				String query = " update  HRMS_ASSET_APPLICATION "
						+ " set ASSET_KEEP_INFORMTO='" + str + "'"
						+ "where ASSET_APPL_CODE=" + bean.getCode();

				getSqlModel().singleExecute(query);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setKeepInformList(HttpServletRequest request, AssetEmployee bean) {

		String keepInformedEmpId[] = request
				.getParameterValues("keepInformedEmpId");
		String keepInformedEmpName[] = request
				.getParameterValues("keepInformedEmpName");
		ArrayList<AssetEmployee> assetList = new ArrayList<AssetEmployee>();
		if (keepInformedEmpId != null && keepInformedEmpId.length > 0) {
			for (int i = 0; i < keepInformedEmpId.length; i++) {
				AssetEmployee assetObj = new AssetEmployee();
				assetObj.setKeepInformedEmpId(keepInformedEmpId[i]);
				assetObj.setKeepInformedEmpName(keepInformedEmpName[i]);
				assetList.add(assetObj);

			}
		}
		bean.setKeepInformedList(assetList);
	}

	public void setApproverData(AssetEmployee assetEmployee, Object[][] empFlow) {

		try {
			if (empFlow != null && empFlow.length > 0) {
				Object[][] approverDataObj = new Object[empFlow.length][1];
				for (int i = 0; i < empFlow.length; i++) {

					String selectQuery = "  SELECT HRMS_EMP_OFFC.EMP_TOKEN ||'-'||' '||' '||' '|| "
							+ " HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_LNAME ,' '||'('||HRMS_RANK.RANK_NAME||')' "
							+ "  FROM HRMS_EMP_OFFC "
							+ " INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK)"
							+ " WHERE EMP_ID IN(" + empFlow[i][0] + ")";

					Object temObj[][] = getSqlModel().getSingleResult(
							selectQuery);
					approverDataObj[i][0] = String.valueOf(temObj[0][0]);
				}

				if (approverDataObj != null && approverDataObj.length > 0) {
					ArrayList arrList = new ArrayList();
					for (int i = 0; i < approverDataObj.length; i++) {
						AssetEmployee innerbean = new AssetEmployee();
						innerbean.setApproverName(String
								.valueOf(approverDataObj[i][0]));
						String srNo = i + 1 + getOrdinalFor(i + 1)
								+ "-Approver";
						innerbean.setSrNoIterator(srNo);
						arrList.add(innerbean);
					}
					assetEmployee.setApproverList(arrList);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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

}