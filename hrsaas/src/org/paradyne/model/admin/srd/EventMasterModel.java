package org.paradyne.model.admin.srd;

import java.util.ArrayList;

import org.paradyne.bean.admin.srd.EventMaster;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

public class EventMasterModel extends ModelBase {

	public void setImageList(String[] srNo, String[] imageName,
			String[] imagePath, EventMaster eventmaster, String[] uploadCategoryItt) {
		try {
			ArrayList<EventMaster> list = new ArrayList<EventMaster>();
			if (srNo != null && srNo.length > 0) {
				for (int j = 0; j < srNo.length; j++) {
					EventMaster bean = new EventMaster();
					bean.setUploadImageItt(imageName[j]);
					bean.setUploadImagePathItt(imagePath[j]);
					bean.setUploadCategoryItt(uploadCategoryItt[j]);
					list.add(bean);
				}
			}
			EventMaster bean = new EventMaster();
			bean.setUploadImageItt(eventmaster.getUserUploadFileName());
			bean.setUploadImagePathItt(eventmaster.getUploadFileName());
			bean.setUploadCategoryItt(eventmaster.getUploadCategory());
			list.add(bean);
			eventmaster.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean save(EventMaster eventmaster, String[] image,
			String[] imagePath, String[] uploadCategoryItt) {
		boolean result = false;
		try {
			Object addObj[][] = new Object[1][7];
			addObj[0][0] = checkNull(eventmaster.getEventName().trim());
			addObj[0][1] = checkNull(eventmaster.getEventDesc().trim());
			addObj[0][2] = checkNull(eventmaster.getEventFromYear().trim());
			addObj[0][3] = checkNull(eventmaster.getEventDate().trim());
			addObj[0][4] = checkNull(eventmaster.getEventLocation().trim());
			addObj[0][5]= checkNull(String.valueOf(eventmaster.getEventTypeCode()));
			addObj[0][6]= checkNull(String.valueOf(eventmaster.getEventDivCode()));

			String insertQuery = " INSERT INTO  HRMS_EVENT_MASTER(EVENT_CODE,"
								+ " EVENT_NAME, EVENT_DESCRIPTION ,EVENT_YEAR, "
								+ " EVENT_DATE, EVENT_LOCATION, EVENT_TYPE_CODE,EVENT_DIVISION) "
								+ " VALUES((SELECT NVL(MAX(EVENT_CODE),0)+1 FROM  HRMS_EVENT_MASTER),"
								+ " ?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?) ";
			result = getSqlModel().singleExecute(insertQuery, addObj);
			String query = " SELECT NVL(MAX(EVENT_CODE),0) FROM  HRMS_EVENT_MASTER ";
			Object eventCodeObj[][] = getSqlModel().getSingleResult(query);

			if (eventCodeObj != null && eventCodeObj.length > 0) {
				eventmaster.setHiddenAutoCode(String
						.valueOf(eventCodeObj[0][0]));
			}
			if (result) {
				if (image != null && image.length > 0) {
					Object addDtlObj[][] = new Object[image.length][4];
					for (int i = 0; i < addDtlObj.length; i++) {
						addDtlObj[i][0] = eventmaster.getHiddenAutoCode();
						addDtlObj[i][1] = image[i];
						addDtlObj[i][2] = imagePath[i];
						addDtlObj[i][3] = uploadCategoryItt[i];
					}
					String dtlQuery = " INSERT INTO HRMS_EVENT_MASTER_DTL(EVENT_CODE, EVENT_IMGNAME, EVENT_IMGPATH ,EVENT_UPLOAD_CATEGORY) "
							+ " VALUES(?,?,?,?) ";
					result = getSqlModel().singleExecute(dtlQuery, addDtlObj);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String checkNull(String result) {
		if (result.equals("") || result.equals("null")) {
			result = "";
		}
		return result;
	}

	public boolean update(EventMaster eventmaster, String[] image,
			String[] imagePath, String[] uploadCategoryItt) {
		boolean result = false;
		try {

			String updateQuery = " UPDATE  HRMS_EVENT_MASTER SET EVENT_NAME=?, EVENT_DESCRIPTION =?, "
					+ " EVENT_YEAR =? , EVENT_DATE =TO_DATE(?,'DD-MM-YYYY'), EVENT_LOCATION=?, EVENT_TYPE_CODE=?, " 
					+ " EVENT_DIVISION =? WHERE  EVENT_CODE="
					+ eventmaster.getHiddenAutoCode().trim();
			Object updateObj[][] = new Object[1][7];
			updateObj[0][0] = checkNull(eventmaster.getEventName().trim());
			updateObj[0][1] = checkNull(eventmaster.getEventDesc().trim());
			updateObj[0][2] = checkNull(eventmaster.getEventFromYear().trim());
			updateObj[0][3] = checkNull(eventmaster.getEventDate().trim());
			updateObj[0][4] = checkNull(eventmaster.getEventLocation().trim());
			updateObj[0][5]= checkNull(eventmaster.getEventTypeCode());
			updateObj[0][6]= checkNull(String.valueOf(eventmaster.getEventDivCode()));
			result = getSqlModel().singleExecute(updateQuery, updateObj);
			String delQuery = " DELETE FROM HRMS_EVENT_MASTER_DTL WHERE EVENT_CODE="
					+ eventmaster.getHiddenAutoCode().trim();
			result = getSqlModel().singleExecute(delQuery);

			if (result) {
				if (image != null && image.length > 0) {
					Object addDtlObj[][] = new Object[image.length][4];
					for (int i = 0; i < addDtlObj.length; i++) {
						addDtlObj[i][0] = eventmaster.getHiddenAutoCode();
						addDtlObj[i][1] = image[i];
						addDtlObj[i][2] = imagePath[i];
						addDtlObj[i][3] = uploadCategoryItt[i];
					}
					String dtlQuery = " INSERT INTO HRMS_EVENT_MASTER_DTL(EVENT_CODE, EVENT_IMGNAME, EVENT_IMGPATH ,EVENT_UPLOAD_CATEGORY) "
							+ " VALUES(?,?,?,?) ";
					result = getSqlModel().singleExecute(dtlQuery, addDtlObj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void displayData(EventMaster eventmaster) {
		try {
			String str="";
			String getName="";
			String code="";
			String selectQuery = " SELECT HRMS_EVENT_MASTER.EVENT_NAME, EVENT_DESCRIPTION,"  
								+ " EVENT_YEAR, TO_CHAR(EVENT_DATE,'DD-MM-YYYY'), EVENT_LOCATION ,"
								+ " EVENT_TYPE_CODE, HRMS_PORTAL_EVENT.EVENT_NAME, EVENT_DIVISION "
								+ " FROM HRMS_EVENT_MASTER" 
								+ " LEFT JOIN HRMS_PORTAL_EVENT ON(HRMS_EVENT_MASTER.EVENT_TYPE_CODE = HRMS_PORTAL_EVENT.EVENT_CODE)"
								+ " WHERE HRMS_EVENT_MASTER.EVENT_CODE="
					+ eventmaster.getHiddenAutoCode();
			Object selectData[][] = getSqlModel().getSingleResult(selectQuery);

			if (selectData != null && selectData.length > 0) {				
				eventmaster.setEventName(checkNull(String
						.valueOf((selectData[0][0]))));
				eventmaster.setEventDesc(checkNull(String
						.valueOf((selectData[0][1]))));
				eventmaster.setEventFromYear(checkNull(String
						.valueOf((selectData[0][2]))));
				eventmaster.setEventDate(checkNull(String
						.valueOf((selectData[0][3]))));
				eventmaster.setEventLocation(checkNull(String
						.valueOf((selectData[0][4]))));
				eventmaster.setEventTypeCode(checkNull(String.valueOf(selectData[0][5])));
				eventmaster.setEventType(checkNull(String.valueOf(selectData[0][6])));
				eventmaster.setEventDivCode(checkNull(String.valueOf(selectData[0][7])));
			}
			if(!(selectData[0][7]==null || String.valueOf(selectData[0][7]).equals("") || String.valueOf(selectData[0][7]).equals("null"))){
				String divQuery="SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION WHERE DIV_ID IN("+String.valueOf(selectData[0][7])+")";
				Object [][]obj= getSqlModel().getSingleResult(divQuery);
				code= String.valueOf(selectData[0][7]);
				getName=Utility.getNameByKey(obj, code);						
				eventmaster.setEventDivision(getName);
			}	
			String dtlQuery = " SELECT EVENT_IMGNAME, EVENT_IMGPATH ,EVENT_UPLOAD_CATEGORY ,EVENT_CODE FROM HRMS_EVENT_MASTER_DTL "
					+ " WHERE EVENT_CODE=" + eventmaster.getHiddenAutoCode();
			Object selectDtlData[][] = getSqlModel().getSingleResult(dtlQuery);
			if (selectDtlData != null && selectDtlData.length > 0) {

				ArrayList<EventMaster> list = new ArrayList<EventMaster>();
				if (selectDtlData != null && selectDtlData.length > 0) {
					for (int j = 0; j < selectDtlData.length; j++) {
						EventMaster bean = new EventMaster();
						bean.setUploadImageItt(checkNull(String
								.valueOf((selectDtlData[j][0]))));
						bean.setUploadImagePathItt(checkNull(String
								.valueOf((selectDtlData[j][1]))));
						bean.setUploadCategoryItt(checkNull(String
								.valueOf((selectDtlData[j][2]))));
						list.add(bean);
					}
				}
				eventmaster.setList(list);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean deleteRecords(EventMaster eventmaster, String[] deleteCode) {
		boolean deleteResult =false;
		String imageDel ="0";
		if(deleteCode.length >0){
			for(int i=0;i < deleteCode.length;i++){
				if(deleteCode[i]!= null && ! deleteCode[i].equals("")){
					if (i == deleteCode.length){
						imageDel = String.valueOf(deleteCode[i]);
					}
					else {
						imageDel += "','" + String.valueOf(deleteCode[i]);
					}
				}
			}
			String query=" DELETE FROM HRMS_EVENT_MASTER_DTL WHERE EVENT_IMGNAME IN('"+ imageDel +
							"') AND EVENT_CODE="+eventmaster.getHiddenAutoCode();
			deleteResult =getSqlModel().singleExecute(query);
		}
		return deleteResult;
	}
}
