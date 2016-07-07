
package org.paradyne.model.TravelManagement.TravelProcess;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.LinkedMap;
import org.paradyne.bean.TravelManagement.TravelProcess.TravelMonitoring;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.templates.EmailTemplateBody;
import org.paradyne.lib.templates.EmailTemplateQuery;

/**
 * @author Pankaj_Jain, modified by Krishna
 * 
 */
public class TravelMonitoringModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TravelMonitoringModel.class);

	public void showMonitoringRecords(TravelMonitoring monitorBean,
			HttpServletRequest request) {
		try {
			Object dataObj[][] = null;
			try {
				String dataQry = " SELECT DISTINCT TMS_APP_EMPDTL.APPL_CODE,TMS_APP_EMPDTL.APPL_ID,E1.EMP_FNAME || ' '||E1.EMP_LNAME AS INITIATOR, "
						+ " E2.EMP_FNAME || ' '||E2.EMP_LNAME AS EMPLOYEE,TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),TO_CHAR(APPL_DATE,'DD-MM-YYYY'), "
						+ " MTR_TVL_AVA_STATUS AS TVL_STAT,MTR_ACC_AVSTATUS AS ACC_STAT,MTR_LOCAL_AVSTATUS AS LOC_STAT,APPL_INITIATOR,APPL_EMP_CODE, "
						+ " 'APL',TMS_MONITOR_ID,TOUR_TRAVEL_REQ_NAME,'','','',DECODE(APPL_EMP_TRAVEL_APPLSTATUS,'H','true','false'),'E',APPL_TVL_CANCEL_STATUS, APPL_ACC_CANCEL_STATUS, APPL_LOC_CANCEL_STATUS FROM TMS_APP_EMPDTL "
						+ " INNER JOIN TMS_MONITORING ON(TMS_MONITORING.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE AND TMS_MONITORING.APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " INNER JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID = TMS_APPLICATION.APPL_INITIATOR) "
						+ " INNER JOIN HRMS_EMP_OFFC E2 ON(E2.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
						+ " LEFT JOIN TMS_APP_JOURNEY_DTL ON(TMS_APP_JOURNEY_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND TMS_APP_JOURNEY_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
						+ " INNER JOIN TMS_APP_TOUR_DTL ON (TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
						+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_CODE = TMS_APP_EMPDTL.APPL_CODE AND TMS_SCH_DESK.DESK_APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " WHERE TMS_SCH_DESK.DESK_STATUS  IN ('A','PC','CC','CP') AND TMS_APP_EMPDTL.APPL_EMP_CODE = "
						+ monitorBean.getUserEmpId()
						+ " UNION "
						+ " SELECT DISTINCT TMS_APP_EMPDTL.APPL_CODE,TMS_APP_EMPDTL.APPL_ID,E1.EMP_FNAME || ' '||E1.EMP_LNAME AS INITIATOR, "
						+ " E2.EMP_FNAME || ' '||E2.EMP_LNAME AS EMPLOYEE,TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),TO_CHAR(APPL_DATE,'DD-MM-YYYY'), "
						+ " MTR_TVL_AVA_STATUS AS TVL_STAT,MTR_ACC_AVSTATUS AS ACC_STAT,MTR_LOCAL_AVSTATUS AS LOC_STAT,APPL_INITIATOR,APPL_EMP_CODE, "
						+ " 'APL',TMS_MONITOR_ID,TOUR_TRAVEL_REQ_NAME,'','','',DECODE(APPL_EMP_TRAVEL_APPLSTATUS,'H','true','false'),'E',APPL_TVL_CANCEL_STATUS, APPL_ACC_CANCEL_STATUS, APPL_LOC_CANCEL_STATUS FROM TMS_APP_EMPDTL "
						+ " INNER JOIN TMS_MONITORING ON(TMS_MONITORING.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE AND TMS_MONITORING.APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " INNER JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID = TMS_APPLICATION.APPL_INITIATOR) "
						+ " INNER JOIN HRMS_EMP_OFFC E2 ON(E2.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
						+ " LEFT JOIN TMS_APP_JOURNEY_DTL ON(TMS_APP_JOURNEY_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND TMS_APP_JOURNEY_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
						+ " INNER JOIN TMS_APP_TOUR_DTL ON (TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
						+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_CODE = TMS_APP_EMPDTL.APPL_CODE AND TMS_SCH_DESK.DESK_APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " WHERE TMS_SCH_DESK.DESK_STATUS  IN ('A','PC','CC','CP') AND TMS_APPLICATION.APPL_INITIATOR = "
						+ monitorBean.getUserEmpId()
						+ " UNION "
						+ " SELECT DISTINCT TMS_APP_EMPDTL.APPL_CODE,TMS_APP_EMPDTL.APPL_ID,E1.EMP_FNAME || ' '||E1.EMP_LNAME AS INITIATOR, "
						+ " E2.EMP_FNAME || ' '||E2.EMP_LNAME AS EMPLOYEE,TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),TO_CHAR(APPL_DATE,'DD-MM-YYYY'), "
						+ " MTR_TVL_AVA_STATUS AS TVL_STAT,MTR_ACC_AVSTATUS AS ACC_STAT,MTR_LOCAL_AVSTATUS AS LOC_STAT,APPL_INITIATOR,APPL_EMP_CODE,'SUB',TMS_MONITOR_ID,TOUR_TRAVEL_REQ_NAME,SCHDTL_TRAVEL_ASSIGN,SCHDTL_LODGE_ASSIGN,SCHDTL_LOCAL_ASSIGN,DECODE(APPL_EMP_TRAVEL_APPLSTATUS,'H','true','false'),'E',APPL_TVL_CANCEL_STATUS, APPL_ACC_CANCEL_STATUS, APPL_LOC_CANCEL_STATUS FROM TMS_APP_EMPDTL "
						+ " INNER JOIN TMS_MONITORING ON(TMS_MONITORING.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE AND TMS_MONITORING.APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_CODE = TMS_MONITORING.APPL_CODE AND TMS_SCH_DESK.DESK_APPL_ID = TMS_MONITORING.APPL_ID) "
						+ " INNER JOIN TMS_SCH_DTL ON(TMS_SCH_DTL.SCH_DESK_ID = TMS_SCH_DESK.DESK_ID) "
						+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " INNER JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID = TMS_APPLICATION.APPL_INITIATOR) "
						+ " INNER JOIN HRMS_EMP_OFFC E2 ON(E2.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
						+ " LEFT JOIN TMS_APP_JOURNEY_DTL ON(TMS_APP_JOURNEY_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND TMS_APP_JOURNEY_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
						+ " INNER JOIN TMS_APP_TOUR_DTL ON (TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
						+ " WHERE TMS_SCH_DESK.DESK_STATUS  IN ('A','PC','CC','CP') AND TMS_SCH_DTL.SCHDTL_SUBSCHLAR_ECODE = "
						+ monitorBean.getUserEmpId()
						+ " AND TMS_SCH_DTL.SCHDTL_SUBSCHLAR_ECODE != TMS_SCH_DESK.DESK_SCH_ECODE "
						+ " AND ((SCHDTL_TRAVEL_ASSIGN='Y' AND DESK_TRAVEL_ASSIGN='Y') OR (SCHDTL_LOCAL_ASSIGN='Y' AND DESK_LOCAL_ASSIGN='Y') OR (SCHDTL_LODGE_ASSIGN='Y' AND DESK_LODGE_ASSIGN='Y'))"
						+ " UNION "
						+ " SELECT DISTINCT TMS_APP_EMPDTL.APPL_CODE,TMS_APP_EMPDTL.APPL_ID,E1.EMP_FNAME || ' '||E1.EMP_LNAME AS INITIATOR, "
						+ " E2.EMP_FNAME || ' '||E2.EMP_LNAME AS EMPLOYEE,TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),TO_CHAR(APPL_DATE,'DD-MM-YYYY'), "
						+ " MTR_TVL_AVA_STATUS AS TVL_STAT,MTR_ACC_AVSTATUS AS ACC_STAT,MTR_LOCAL_AVSTATUS AS LOC_STAT,APPL_INITIATOR,APPL_EMP_CODE,'APR',TMS_MONITOR_ID,TOUR_TRAVEL_REQ_NAME,'','','',DECODE(APPL_EMP_TRAVEL_APPLSTATUS,'H','true','false'),'E',APPL_TVL_CANCEL_STATUS, APPL_ACC_CANCEL_STATUS, APPL_LOC_CANCEL_STATUS FROM TMS_APP_EMPDTL "
						+ " INNER JOIN TMS_MONITORING ON(TMS_MONITORING.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE AND TMS_MONITORING.APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " INNER JOIN TMS_APP_APPROVAL_DTL ON(TMS_APP_APPROVAL_DTL.APPL_CODE = TMS_MONITORING.APPL_CODE AND TMS_MONITORING.APPL_ID = TMS_APP_APPROVAL_DTL.APPL_ID) "
						+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " INNER JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID = TMS_APPLICATION.APPL_INITIATOR) "
						+ " INNER JOIN HRMS_EMP_OFFC E2 ON(E2.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
						+ " LEFT JOIN TMS_APP_JOURNEY_DTL ON(TMS_APP_JOURNEY_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND TMS_APP_JOURNEY_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
						+ " INNER JOIN TMS_APP_TOUR_DTL ON (TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
						+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
						+ " WHERE TMS_SCH_DESK.DESK_STATUS  IN ('A','PC','CC','CP') AND TMS_APP_APPROVAL_DTL.APPR_APPROVER_ID = "
						+ monitorBean.getUserEmpId()
						+ " UNION "
						+ " SELECT DISTINCT TMS_APP_EMPDTL.APPL_CODE,TMS_APP_EMPDTL.APPL_ID,E1.EMP_FNAME || ' '||E1.EMP_LNAME AS INITIATOR, "
						+ " E2.EMP_FNAME || ' '||E2.EMP_LNAME AS EMPLOYEE,TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),TO_CHAR(APPL_DATE,'DD-MM-YYYY'), "
						+ " MTR_TVL_AVA_STATUS AS TVL_STAT,MTR_ACC_AVSTATUS AS ACC_STAT,MTR_LOCAL_AVSTATUS AS LOC_STAT,APPL_INITIATOR,APPL_EMP_CODE,'SCH',TMS_MONITOR_ID,TOUR_TRAVEL_REQ_NAME,'','','',DECODE(APPL_EMP_TRAVEL_APPLSTATUS,'H','true','false'),'E',APPL_TVL_CANCEL_STATUS, APPL_ACC_CANCEL_STATUS, APPL_LOC_CANCEL_STATUS FROM TMS_APP_EMPDTL "
						+ " INNER JOIN TMS_MONITORING ON(TMS_MONITORING.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE AND TMS_MONITORING.APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_CODE = TMS_APP_EMPDTL.APPL_CODE AND TMS_SCH_DESK.DESK_APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_EMPDTL.APPL_ID) "
						+ " INNER JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID = TMS_APPLICATION.APPL_INITIATOR) "
						+ " INNER JOIN HRMS_EMP_OFFC E2 ON(E2.EMP_ID = TMS_APP_EMPDTL.APPL_EMP_CODE) "
						+ " LEFT JOIN TMS_APP_JOURNEY_DTL ON(TMS_APP_JOURNEY_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND TMS_APP_JOURNEY_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
						+ " INNER JOIN TMS_APP_TOUR_DTL ON (TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_EMPDTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_EMPDTL.APPL_CODE) "
						+ " WHERE TMS_SCH_DESK.DESK_STATUS  IN ('A','PC','CC','CP') AND TMS_SCH_DESK.DESK_SCH_ECODE = "
						+ monitorBean.getUserEmpId()
						+ " UNION "
						+ " SELECT DISTINCT TMS_APP_GUEST_DTL.APPL_CODE,TMS_APP_GUEST_DTL.APPL_ID,E1.EMP_FNAME || ' '||E1.EMP_LNAME AS INITIATOR, GUEST_NAME ,TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),TO_CHAR(APPL_DATE,'DD-MM-YYYY'),  MTR_TVL_AVA_STATUS AS TVL_STAT,MTR_ACC_AVSTATUS AS ACC_STAT,MTR_LOCAL_AVSTATUS AS LOC_STAT,APPL_INITIATOR,0,'APL',TMS_MONITOR_ID,TOUR_TRAVEL_REQ_NAME,'','','',DECODE(GUEST_TRAVEL_APPLSTATUS,'H','true','false'),'G',APPL_TVL_CANCEL_STATUS, APPL_ACC_CANCEL_STATUS, APPL_LOC_CANCEL_STATUS FROM TMS_APP_GUEST_DTL "
						+ " INNER JOIN TMS_MONITORING ON(TMS_MONITORING.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE AND TMS_MONITORING.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID) "
						+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE AND TMS_SCH_DESK.DESK_APPL_ID = TMS_APP_GUEST_DTL.APPL_ID) "
						+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID) "
						+ " INNER JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID = TMS_APPLICATION.APPL_INITIATOR) "
						+ " LEFT JOIN TMS_APP_JOURNEY_DTL ON(TMS_APP_JOURNEY_DTL.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_JOURNEY_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
						+ " INNER JOIN TMS_APP_TOUR_DTL ON (TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
						+ " WHERE TMS_SCH_DESK.DESK_STATUS  IN ('A','PC','CC','CP') AND TMS_APPLICATION.APPL_INITIATOR = "
						+ monitorBean.getUserEmpId()
						+ " UNION "
						+ " SELECT DISTINCT TMS_APP_GUEST_DTL.APPL_CODE,TMS_APP_GUEST_DTL.APPL_ID,E1.EMP_FNAME || ' '||E1.EMP_LNAME AS INITIATOR, GUEST_NAME ,TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),TO_CHAR(APPL_DATE,'DD-MM-YYYY'),  MTR_TVL_AVA_STATUS AS TVL_STAT,MTR_ACC_AVSTATUS AS ACC_STAT,MTR_LOCAL_AVSTATUS AS LOC_STAT,APPL_INITIATOR,0,'APR',TMS_MONITOR_ID,TOUR_TRAVEL_REQ_NAME,'','','',DECODE(GUEST_TRAVEL_APPLSTATUS,'H','true','false'),'G',APPL_TVL_CANCEL_STATUS, APPL_ACC_CANCEL_STATUS, APPL_LOC_CANCEL_STATUS FROM TMS_APP_GUEST_DTL "
						+ " INNER JOIN TMS_MONITORING ON(TMS_MONITORING.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE AND TMS_MONITORING.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID) "
						+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE AND TMS_SCH_DESK.DESK_APPL_ID = TMS_APP_GUEST_DTL.APPL_ID) "
						+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID) "
						+ " INNER JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID = TMS_APPLICATION.APPL_INITIATOR) "
						+ " LEFT JOIN TMS_APP_JOURNEY_DTL ON(TMS_APP_JOURNEY_DTL.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_JOURNEY_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
						+ " INNER JOIN TMS_APP_TOUR_DTL ON (TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
						+ " INNER JOIN TMS_APP_APPROVAL_DTL ON(TMS_APP_APPROVAL_DTL.APPL_CODE = TMS_MONITORING.APPL_CODE AND TMS_APP_APPROVAL_DTL.APPL_ID = TMS_MONITORING.APPL_ID) "
						+ " WHERE TMS_SCH_DESK.DESK_STATUS  IN ('A','PC','CC','CP') AND TMS_APP_APPROVAL_DTL.APPR_APPROVER_ID = "
						+ monitorBean.getUserEmpId()
						+ " UNION "
						+ " SELECT DISTINCT TMS_APP_GUEST_DTL.APPL_CODE,TMS_APP_GUEST_DTL.APPL_ID,E1.EMP_FNAME || ' '||E1.EMP_LNAME AS INITIATOR, GUEST_NAME ,TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),TO_CHAR(APPL_DATE,'DD-MM-YYYY'),  MTR_TVL_AVA_STATUS AS TVL_STAT,MTR_ACC_AVSTATUS AS ACC_STAT,MTR_LOCAL_AVSTATUS AS LOC_STAT,APPL_INITIATOR,0,'SUB',TMS_MONITOR_ID,TOUR_TRAVEL_REQ_NAME,SCHDTL_TRAVEL_ASSIGN,SCHDTL_LODGE_ASSIGN,SCHDTL_LOCAL_ASSIGN,DECODE(GUEST_TRAVEL_APPLSTATUS,'H','true','false'),'G',APPL_TVL_CANCEL_STATUS, APPL_ACC_CANCEL_STATUS, APPL_LOC_CANCEL_STATUS FROM TMS_APP_GUEST_DTL "
						+ " INNER JOIN TMS_MONITORING ON(TMS_MONITORING.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE AND TMS_MONITORING.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID) "
						+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE AND TMS_SCH_DESK.DESK_APPL_ID = TMS_APP_GUEST_DTL.APPL_ID) "
						+ " INNER JOIN TMS_SCH_DTL ON(TMS_SCH_DTL.SCH_DESK_ID = TMS_SCH_DESK.DESK_ID) "
						+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID) "
						+ " INNER JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID = TMS_APPLICATION.APPL_INITIATOR) "
						+ " LEFT JOIN TMS_APP_JOURNEY_DTL ON(TMS_APP_JOURNEY_DTL.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_JOURNEY_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
						+ " INNER JOIN TMS_APP_TOUR_DTL ON (TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
						+ " WHERE TMS_SCH_DESK.DESK_STATUS  IN ('A','PC','CC','CP') AND TMS_SCH_DTL.SCHDTL_SUBSCHLAR_ECODE = "
						+ monitorBean.getUserEmpId()
						+ " AND TMS_SCH_DTL.SCHDTL_SUBSCHLAR_ECODE != TMS_SCH_DESK.DESK_SCH_ECODE "
						+ " AND ((SCHDTL_TRAVEL_ASSIGN='Y' AND DESK_TRAVEL_ASSIGN='Y') OR (SCHDTL_LOCAL_ASSIGN='Y' AND DESK_LOCAL_ASSIGN='Y') OR (SCHDTL_LODGE_ASSIGN='Y' AND DESK_LODGE_ASSIGN='Y'))"
						+ " UNION "
						+ " SELECT DISTINCT TMS_APP_GUEST_DTL.APPL_CODE,TMS_APP_GUEST_DTL.APPL_ID,E1.EMP_FNAME || ' '||E1.EMP_LNAME AS INITIATOR, GUEST_NAME ,TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),TO_CHAR(APPL_DATE,'DD-MM-YYYY'),  MTR_TVL_AVA_STATUS AS TVL_STAT,MTR_ACC_AVSTATUS AS ACC_STAT,MTR_LOCAL_AVSTATUS AS LOC_STAT,APPL_INITIATOR,0,'SCH',TMS_MONITOR_ID,TOUR_TRAVEL_REQ_NAME,'','','',DECODE(GUEST_TRAVEL_APPLSTATUS,'H','true','false'),'G',APPL_TVL_CANCEL_STATUS, APPL_ACC_CANCEL_STATUS, APPL_LOC_CANCEL_STATUS FROM TMS_APP_GUEST_DTL "
						+ " INNER JOIN TMS_MONITORING ON(TMS_MONITORING.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE AND TMS_MONITORING.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID) "
						+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE AND TMS_SCH_DESK.DESK_APPL_ID = TMS_APP_GUEST_DTL.APPL_ID) "
						+ " INNER JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID) "
						+ " INNER JOIN HRMS_EMP_OFFC E1 ON(E1.EMP_ID = TMS_APPLICATION.APPL_INITIATOR) "
						+ " LEFT JOIN TMS_APP_JOURNEY_DTL ON(TMS_APP_JOURNEY_DTL.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_JOURNEY_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
						+ " INNER JOIN TMS_APP_TOUR_DTL ON (TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_GUEST_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_GUEST_DTL.APPL_CODE) "
						+ " INNER JOIN TMS_SCH_DTL ON(TMS_SCH_DTL.SCH_DESK_ID = TMS_SCH_DESK.DESK_ID) "
						+ " WHERE TMS_SCH_DESK.DESK_STATUS  IN ('A','PC','CC','CP') AND TMS_SCH_DESK.DESK_SCH_ECODE = "
						+ monitorBean.getUserEmpId()
						+ " ORDER BY 2 DESC,1 DESC";

				logger.info("MONITOR QUERY----" + dataQry);

				dataObj = getSqlModel().getSingleResult(dataQry);
				dataObj = Utility.checkNullObjArr(dataObj);
			} catch (Exception e) {
				logger.error("Exception in executing query " + e);
				return;
			}
			if (dataObj == null || dataObj.length == 0)
				return;
			int currentPage = 1;
			int fromRec = 0;
			int toRec = 10;
			if (!monitorBean.getCurrentPage().equals("")) {
				currentPage = Integer.parseInt(monitorBean.getCurrentPage());
				fromRec = (currentPage - 1) * 10;
				toRec = fromRec + 10;
			} else {
				monitorBean.setCurrentPage("1");
			}
			request.setAttribute("totalPages",
					(dataObj.length % 10 == 0) ? (dataObj.length / 10)
							: (dataObj.length / 10) + 1);
			request.setAttribute("currentPage", currentPage);
			try {
				if (dataObj != null && dataObj.length > 0) {
					String applId = "";
					String applCode = "";
					for (int i = 0; i < dataObj.length; i++) {
						applCode += dataObj[i][0] + ",";
						applId += dataObj[i][1] + ",";
					}
					applId = applId.substring(0, applId.length() - 1);
					applCode = applCode.substring(0, applCode.length() - 1);
					String actorNameQry = " SELECT EMP_FNAME,DESK_APPL_ID,DESK_APPL_CODE,'T' FROM TMS_SCH_DESK "
							+ " INNER JOIN TMS_SCH_DTL ON (TMS_SCH_DTL.SCH_DESK_ID = TMS_SCH_DESK.DESK_ID) "
							+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = TMS_SCH_DTL.SCHDTL_SUBSCHLAR_ECODE ) "
							+ " WHERE DESK_APPL_CODE IN ("
							+ applCode
							+ ") AND DESK_APPL_ID IN ("
							+ applId
							+ ") AND DESK_STATUS IN ('A','PC','CC','CP') "
							+ " AND SCHDTL_TRAVEL_ASSIGN='Y' "
							+ " UNION "
							+ " SELECT EMP_FNAME,DESK_APPL_ID,DESK_APPL_CODE,'A' FROM TMS_SCH_DESK "
							+ " INNER JOIN TMS_SCH_DTL ON (TMS_SCH_DTL.SCH_DESK_ID = TMS_SCH_DESK.DESK_ID) "
							+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = TMS_SCH_DTL.SCHDTL_SUBSCHLAR_ECODE ) "
							+ " WHERE DESK_APPL_CODE IN ("
							+ applCode
							+ ") AND DESK_APPL_ID IN ("
							+ applId
							+ ") AND DESK_STATUS IN ('A','PC','CC','CP') "
							+ " AND SCHDTL_LODGE_ASSIGN='Y' "
							+ " UNION "
							+ " SELECT EMP_FNAME,DESK_APPL_ID,DESK_APPL_CODE,'L' FROM TMS_SCH_DESK "
							+ " INNER JOIN TMS_SCH_DTL ON (TMS_SCH_DTL.SCH_DESK_ID = TMS_SCH_DESK.DESK_ID) "
							+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = TMS_SCH_DTL.SCHDTL_SUBSCHLAR_ECODE ) "
							+ " WHERE DESK_APPL_CODE IN ("
							+ applCode
							+ ") AND DESK_APPL_ID IN ("
							+ applId
							+ ") AND DESK_STATUS IN ('A','PC','CC','CP') "
							+ " AND SCHDTL_LOCAL_ASSIGN='Y'"
							+ " ORDER BY 2 DESC,3 DESC, 4 DESC";

					Object[][] actorObj = getSqlModel().getSingleResult(
							actorNameQry);

					String apprNameQry = " SELECT HRMS_EMP_OFFC.EMP_FNAME,TMS_APP_APPROVAL_DTL.APPL_CODE,TMS_APP_APPROVAL_DTL.APPL_ID "
							+ " FROM TMS_APP_APPROVAL_DTL "
							+ " INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = TMS_APP_APPROVAL_DTL.APPR_APPROVER_ID "
							+ " WHERE APPL_CODE IN("
							+ applCode
							+ ") AND APPL_ID IN("
							+ applId
							+ ") AND TMS_APP_APPROVAL_DTL.APPR_LEVEL=1 "
							+ " ORDER BY APPL_ID DESC, APPL_CODE DESC";
					Object[][] apprObj = getSqlModel().getSingleResult(
							apprNameQry);

					ArrayList<Object> list = new ArrayList<Object>();
					dataObj = Utility.checkNullObjArr(dataObj);
					String[][] displayName = new String[1][3];
					int z = 0;
					try {
						while (!String.valueOf(actorObj[z][2]).equals(
								String.valueOf(dataObj[fromRec][0]))
								|| !String.valueOf(actorObj[z][1]).equals(
										String.valueOf(dataObj[fromRec][1])))
							z++;
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
					for (int i = fromRec; i < toRec && i < dataObj.length; i++) {
						TravelMonitoring bean = new TravelMonitoring();
						bean.setSrNo(i + 1);
						bean.setEmpApplId(Integer.parseInt(String
								.valueOf(dataObj[i][0])));
						bean.setApplicationId(Integer.parseInt(String
								.valueOf(dataObj[i][1])));
						bean.setInitiator(String.valueOf(dataObj[i][2]));
						bean.setApplicant(String.valueOf(dataObj[i][3]));
						bean.setJourneyDate(String.valueOf(dataObj[i][4]));
						bean.setApplicationDate(String.valueOf(dataObj[i][5]));
						bean.setIniEmpId(Integer.parseInt(String
								.valueOf(dataObj[i][9])));
						bean.setEmpId(""
								+ Integer.parseInt(String
										.valueOf(dataObj[i][10])));
						bean.setUserType(String.valueOf(dataObj[i][11]));
						bean.setMonitorId(String.valueOf(dataObj[i][12]));
						bean.setTravelReqId("TRVL_"
								+ String.valueOf(dataObj[i][1]) + "_"
								+ String.valueOf(dataObj[i][0]));
						if (String.valueOf(dataObj[i][17]).equals("true")) {
							if (!String.valueOf(dataObj[i][6]).trim().equals(
									"N"))
								dataObj[i][6] = "H";
							if (!String.valueOf(dataObj[i][7]).trim().equals(
									"N"))
								dataObj[i][7] = "H";
							if (!String.valueOf(dataObj[i][8]).trim().equals(
									"N"))
								dataObj[i][8] = "H";
						}

						if (!String.valueOf(dataObj[i][19]).equals("N")
								&& !String.valueOf(dataObj[i][19]).equals("CP")
								&& !String.valueOf(dataObj[i][19]).equals("CC"))
							dataObj[i][6] = String.valueOf(dataObj[i][19]);

						if (!String.valueOf(dataObj[i][20]).equals("N")
								&& !String.valueOf(dataObj[i][20]).equals("CP")
								&& !String.valueOf(dataObj[i][20]).equals("CC"))
							dataObj[i][7] = String.valueOf(dataObj[i][20]);

						if (!String.valueOf(dataObj[i][21]).equals("N")
								&& !String.valueOf(dataObj[i][21]).equals("CP")
								&& !String.valueOf(dataObj[i][21]).equals("CC"))
							dataObj[i][8] = String.valueOf(dataObj[i][21]);

						displayName[0][1] = bean.getApplicant();
						if (String.valueOf(dataObj[i][18]).equals("G"))
							displayName[0][1] = bean.getInitiator();
						if (apprObj != null && i < apprObj.length)
							displayName[0][2] = String.valueOf(apprObj[i][0]);

						try {
							if (z < actorObj.length) {
								if (z < actorObj.length
										&& String.valueOf(actorObj[z][3])
												.equals("T")) {
									if (String.valueOf(actorObj[z][2]).equals(
											String.valueOf(dataObj[i][0]))
											&& String
													.valueOf(actorObj[z][1])
													.equals(
															String
																	.valueOf(dataObj[i][1]))) {
										displayName[0][0] = String
												.valueOf(actorObj[z][0]);
										z++;
									}
								}
								if (!String.valueOf(dataObj[i][19])
										.equals("CC")
										|| String.valueOf(dataObj[i][6])
												.equals("CD"))
									bean.setTvlStatus(getStatus(String
											.valueOf(dataObj[i][6]),
											displayName));
								else
									bean.setTvlStatus(getStatus(String
											.valueOf(dataObj[i][19]),
											displayName));

								if (z < actorObj.length
										&& String.valueOf(actorObj[z][3])
												.equals("L")) {
									if (String.valueOf(actorObj[z][2]).equals(
											String.valueOf(dataObj[i][0]))
											&& String
													.valueOf(actorObj[z][1])
													.equals(
															String
																	.valueOf(dataObj[i][1]))) {
										displayName[0][0] = String
												.valueOf(actorObj[z][0]);
										z++;
									}
								}

								if (!String.valueOf(dataObj[i][21])
										.equals("CC")
										|| String.valueOf(dataObj[i][8])
												.equals("CD"))
									bean.setLocStatus(getStatus(String
											.valueOf(dataObj[i][8]),
											displayName));
								else
									bean.setLocStatus(getStatus(String
											.valueOf(dataObj[i][21]),
											displayName));

								if (z < actorObj.length
										&& String.valueOf(actorObj[z][3])
												.equals("A")) {
									if (String.valueOf(actorObj[z][2]).equals(
											String.valueOf(dataObj[i][0]))
											&& String
													.valueOf(actorObj[z][1])
													.equals(
															String
																	.valueOf(dataObj[i][1]))) {
										displayName[0][0] = String
												.valueOf(actorObj[z][0]);
										z++;
									}
								}

								if (!String.valueOf(dataObj[i][20])
										.equals("CC")
										|| String.valueOf(dataObj[i][7])
												.equals("CD"))
									bean.setAccStatus(getStatus(String
											.valueOf(dataObj[i][7]),
											displayName));
								else
									bean.setAccStatus(getStatus(String
											.valueOf(dataObj[i][20]),
											displayName));
							}
						} catch (RuntimeException e) {
							e.printStackTrace();
						}

						bean.setOnHoldFlag(String.valueOf(dataObj[i][17]));

						if (String.valueOf(dataObj[i][6]).trim().equals("N")
								&& String.valueOf(dataObj[i][7]).trim().equals(
										"N")
								&& String.valueOf(dataObj[i][8]).trim().equals(
										"N")) {
							bean.setButtonAction("");
							bean.setButtonLabel("");
						} else if ((String.valueOf(dataObj[i][11])
								.equals("SCH") || String
								.valueOf(dataObj[i][11]).equals("SUB"))
								&& (String.valueOf(dataObj[i][6]).equals("CE")
										|| String.valueOf(dataObj[i][6])
												.equals("CN")
										|| String.valueOf(dataObj[i][6]).trim()
												.equals("N")
										|| String.valueOf(dataObj[i][6]).trim()
												.equals("CC")
										|| String.valueOf(dataObj[i][6]).trim()
												.equals("FI") || String
										.valueOf(dataObj[i][6]).trim().equals(
												"CD"))
								&& (String.valueOf(dataObj[i][7]).equals("CE")
										|| String.valueOf(dataObj[i][7])
												.equals("CN")
										|| String.valueOf(dataObj[i][7]).trim()
												.equals("N")
										|| String.valueOf(dataObj[i][7]).trim()
												.equals("CC")
										|| String.valueOf(dataObj[i][7]).trim()
												.equals("FI") || String
										.valueOf(dataObj[i][7]).trim().equals(
												"CD"))
								&& (String.valueOf(dataObj[i][8]).equals("CE")
										|| String.valueOf(dataObj[i][8])
												.equals("CN")
										|| String.valueOf(dataObj[i][8]).trim()
												.equals("N")
										|| String.valueOf(dataObj[i][8]).trim()
												.equals("CC")
										|| String.valueOf(dataObj[i][8]).trim()
												.equals("FI") || String
										.valueOf(dataObj[i][8]).trim().equals(
												"CD"))) {
							if (String.valueOf(dataObj[i][11]).equals("SUB")) {

								if ((String.valueOf(dataObj[i][6]).equals("CE") && String
										.valueOf(dataObj[i][14]).equals("Y"))
										|| (String.valueOf(dataObj[i][7])
												.equals("CE") && String
												.valueOf(dataObj[i][15])
												.equals("Y"))
										|| (String.valueOf(dataObj[i][8])
												.equals("CE") && String
												.valueOf(dataObj[i][16])
												.equals("Y"))) {
									bean.setButtonAction("startBooking");
									bean.setButtonLabel("Start Booking");
								}

								else if (!(String.valueOf(dataObj[i][6])
										.equals("CE")
										|| String.valueOf(dataObj[i][7])
												.equals("CE") || String
										.valueOf(dataObj[i][8]).equals("CE"))
										&& (((String.valueOf(dataObj[i][6])
												.equals("FI")
												|| String
														.valueOf(dataObj[i][6])
														.equals("CN")
												|| String
														.valueOf(dataObj[i][6])
														.trim().equals("N") || String
												.valueOf(dataObj[i][6]).trim()
												.equals("CC")) && String
												.valueOf(dataObj[i][14])
												.equals("Y"))
												|| ((String.valueOf(
														dataObj[i][7]).equals(
														"FI")
														|| String.valueOf(
																dataObj[i][7])
																.equals("CN")
														|| String.valueOf(
																dataObj[i][7])
																.trim().equals(
																		"N") || String
														.valueOf(dataObj[i][7])
														.trim().equals("CC")) && String
														.valueOf(dataObj[i][15])
														.equals("Y")) || ((String
												.valueOf(dataObj[i][8]).equals(
														"FI")
												|| String
														.valueOf(dataObj[i][8])
														.equals("CN")
												|| String
														.valueOf(dataObj[i][8])
														.trim().equals("N") || String
												.valueOf(dataObj[i][8]).trim()
												.equals("CC")) && String
												.valueOf(dataObj[i][16])
												.equals("Y")))) {
									bean.setButtonAction("startCancellation");
									bean.setButtonLabel("Start Cancellation");
								}

								else {
									bean.setButtonAction("");
									bean.setButtonLabel("");
								}

								logger.info("button Action----"
										+ bean.getButtonAction());
								logger.info("button Label----"
										+ bean.getButtonLabel());
							}

							else {
								if ((String.valueOf(dataObj[i][6]).equals("CC")
										|| String.valueOf(dataObj[i][7])
												.equals("CC") || String
										.valueOf(dataObj[i][8]).equals("CC"))
										&& (String.valueOf(dataObj[i][6])
												.equals("FI")
												|| String
														.valueOf(dataObj[i][6])
														.trim().equals("N")
												|| String
														.valueOf(dataObj[i][6])
														.equals("CC") || String
												.valueOf(dataObj[i][6]).equals(
														"CD"))
										&& (String.valueOf(dataObj[i][7])
												.equals("FI")
												|| String
														.valueOf(dataObj[i][7])
														.trim().equals("N")
												|| String
														.valueOf(dataObj[i][7])
														.equals("CC") || String
												.valueOf(dataObj[i][7]).equals(
														"CD"))
										&& (String.valueOf(dataObj[i][8])
												.equals("FI")
												|| String
														.valueOf(dataObj[i][8])
														.trim().equals("N")
												|| String
														.valueOf(dataObj[i][8])
														.equals("CC") || String
												.valueOf(dataObj[i][8]).equals(
														"CD"))) {
									bean.setButtonAction("startCancellation");
									bean.setButtonLabel("Start Cancellation");
								} else if (!(String.valueOf(dataObj[i][6])
										.equals("PC")
										|| String.valueOf(dataObj[i][7])
												.equals("PC") || String
										.valueOf(dataObj[i][8]).equals("PC"))
										&& (String.valueOf(dataObj[i][6])
												.equals("CE")
												|| String
														.valueOf(dataObj[i][7])
														.equals("CE") || String
												.valueOf(dataObj[i][8]).equals(
														"CE"))) {
									bean.setButtonAction("startBooking");
									bean.setButtonLabel("Start Booking");
								} else {
									bean.setButtonAction("");
									bean.setButtonLabel("");
								}
							}
						} else {
							bean.setButtonAction("");
							bean.setButtonLabel("");
						}
						list.add(bean);
					}
					monitorBean.setMonitorList(list);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Exception while setting iterator : " + e);
			}

		} catch (Exception e) {
			logger.error("Exception in showMonitoringRecords() " + e);
			e.printStackTrace();
		} finally {
			System.gc();
		}
	}

	public void getTravelDetails(HttpServletRequest request,
			TravelMonitoring bean) {
		try {
			setEmpInfo(request, bean);
			if (request.getParameter("userType").equals("SUB"))
				setStatus(request, bean, "SCHDTL_TRAVEL_ASSIGN");
			else
				bean.setViewFlag("true");
			Object[][] journeyObj = null;
			Object[][] optionsObj = null;
			try {
				String journeyQry = " SELECT DISTINCT JOURNEY_FROM,JOURNEY_TO,APPL_CODE,JOURNEY_CODE,TMS_MONITOR_ID,DECODE(MTR_TVL_AVA_STATUS,'RE','Y ',MTR_TVL_AVA_STATUS),TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),"
						+ " TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),JOURNEY_TIME,TO_CHAR(JOURNEY_DATE,'DD-MM-YYYY'),JOURNEY_MODECLASS,JOURNEY_STATUS"
						+ " FROM TMS_MONITORING "
						+ " INNER JOIN TMS_APP_JOURNEY_DTL ON(TMS_MONITORING.APPL_ID = TMS_APP_JOURNEY_DTL.APPL_ID AND TMS_MONITORING.APPL_CODE = TMS_APP_JOURNEY_DTL.APPL_CODE) "
						+ " INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_JOURNEY_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_JOURNEY_DTL.APPL_CODE)"
						+ " WHERE TMS_MONITORING.APPL_CODE="
						+ request.getParameter("empApplId")
						+ " AND TMS_MONITORING.APPL_ID = "
						+ request.getParameter("applicationId")
						+ " ORDER BY JOURNEY_CODE";
				journeyObj = getSqlModel().getSingleResult(journeyQry);
			} catch (Exception e) {
				logger.error("Exception while executing query : " + e);
				e.printStackTrace();
				return;
			}
			boolean flag = true;
			if (journeyObj == null || journeyObj.length < 1)
				return;
			journeyObj = Utility.checkNullObjArr(journeyObj);
			String userType = request.getParameter("userType");
			bean.setUserType(request.getParameter("userType"));
			try {
				String jourCode = "";
				for (int i = 0; i < journeyObj.length; i++)
					jourCode += String.valueOf(journeyObj[i][3]) + ",";
				if (jourCode.length() > 1) {
					jourCode = jourCode.substring(0, jourCode.length() - 1);
					String optionsQry = " SELECT * FROM (SELECT DISTINCT MONITOR_ID,TVLNG_JOURN_CODE,TVLNG_DTL_CODE,TVLNG_SOURCE,TVLNG_DESTINATION,TO_CHAR(TVLNG_DATE,'DD-MM-YYYY'),TVLNG_TIME,TVLNG_MODE, "
							+ " TVLNG_TCK_NO,TVLNG_COST,TVLNG_SEL_FLAG,TVLNG_GROUP_CODE,TVLNG_ID_NO,TVLNG_ORDER,TVLNG_REJ_FLAG,TVLNG_REMARKS FROM TMS_SUGG_TRAVELLING "
							+ " INNER JOIN TMS_MONITORING ON (TMS_MONITORING.TMS_MONITOR_ID = TMS_SUGG_TRAVELLING.MONITOR_ID) "
							+ " WHERE TVLNG_JOURN_CODE IN("
							+ jourCode
							+ ") AND MONITOR_ID = "
							+ request.getParameter("monitorId")
							+ " AND APPL_ID = "
							+ request.getParameter("applicationId")
							+ ") ORDER BY TVLNG_JOURN_CODE,TVLNG_GROUP_CODE,TVLNG_ORDER ";
					optionsObj = getSqlModel().getSingleResult(optionsQry);
				}
				if (optionsObj != null && optionsObj.length > 0) {
					Utility.checkNullObjArr(optionsObj);
					bean.setSavedFlag("true");
				}
			} catch (Exception e) {
				logger.error("Exception while getting options : " + e);
				e.printStackTrace();
			}
			String commentsQry = "SELECT MTR_TRVL_SCH_COMM, MTR_TRVL_APP_COMM,MTR_TRVL_APR_COMM FROM TMS_MONITORING "
					+ " WHERE TMS_MONITOR_ID = " + journeyObj[0][4];
			Object[][] commentsObj = getSqlModel().getSingleResult(commentsQry);
			LinkedMap hMap = getTravelModeClass();
			bean.setMonitorId(String.valueOf(journeyObj[0][4]));
			bean.setTvlStatus(String.valueOf(journeyObj[0][5]));
			bean.setTourStartDate(String.valueOf(journeyObj[0][6]));
			bean.setTourEndDate(String.valueOf(journeyObj[0][7]));
			if ((request.getParameter("userType").equals("SUB") || request
					.getParameter("userType").equals("SCH"))
					&& bean.getTvlStatus().trim().equals("Y")
					&& bean.getViewFlag().equals("true"))
				flag = false;
			if (request.getParameter("tvlStatus").equalsIgnoreCase("Hold")) {
				bean.setTvlStatus("H");
				flag = true;
			} else if (request.getParameter("tvlStatus").equalsIgnoreCase(
					"Pending For Cancellation")) {
				bean.setTvlStatus("PC");
				flag = true;
			} else if (request.getParameter("tvlStatus").equalsIgnoreCase(
					"Cancel")) {
				bean.setTvlStatus("CC");
				flag = true;
			}

			bean.setReadOnlyFlag(flag);
			if (commentsObj != null && commentsObj.length > 0) {
				commentsObj = Utility.checkNullObjArr(commentsObj);
				bean.setSchComments(String.valueOf(commentsObj[0][0]));
				bean.setAplComments(String.valueOf(commentsObj[0][1]));
				bean.setAprComments(String.valueOf(commentsObj[0][2]));
			}
			try {
				ArrayList jourList = new ArrayList();
				int opCount = 0;
				for (int i = 0; i < journeyObj.length; i++) {
					if (optionsObj == null || optionsObj.length == 0) {
						optionsObj = new Object[0][0];
						Utility.checkNullObjArr(optionsObj);
					}
					TravelMonitoring journBean = new TravelMonitoring();
					ArrayList optList = new ArrayList();
					journBean.setFromCity(String.valueOf(journeyObj[i][0]));
					journBean.setToCity(String.valueOf(journeyObj[i][1]));
					journBean.setUserType(request.getParameter("userType"));
					journBean.setReadOnlyFlag(flag);
					if (String.valueOf(journeyObj[i][11]).equals("CC")
							|| String.valueOf(journeyObj[i][11]).equals("PC"))
						journBean.setReadOnlyFlag(true);
					boolean addFlag = false;
					for (; opCount < optionsObj.length;) {
						String grpCode = String
								.valueOf(optionsObj[opCount][11]);
						if (!String.valueOf(optionsObj[opCount][1]).equals(
								String.valueOf(journeyObj[i][3]))) {
							if (optionsObj.length > 1)
								break;
						}
						addFlag = true;
						TravelMonitoring optBean = new TravelMonitoring();
						optBean.setUserType(request.getParameter("userType"));
						optBean.setReadOnlyFlag(flag);
						optBean.setJourCancelFlag(String
								.valueOf(journeyObj[i][11]));
						ArrayList pathList = new ArrayList();
						while (opCount < optionsObj.length
								&& grpCode.equals(String
										.valueOf(optionsObj[opCount][11]))
								&& String
										.valueOf(optionsObj[opCount][1])
										.equals(
												String
														.valueOf(journeyObj[i][3]))) {
							TravelMonitoring pathBean = new TravelMonitoring();
							pathBean.setMonitorId(String
									.valueOf(optionsObj[opCount][0]));
							pathBean.setJourCode(String
									.valueOf(journeyObj[i][3]));
							pathBean.setTrvlDtlId(String
									.valueOf(optionsObj[opCount][2]));
							pathBean.setSource(String
									.valueOf(optionsObj[opCount][3]));
							pathBean.setDest(String
									.valueOf(optionsObj[opCount][4]));
							pathBean.setTravelDate(String
									.valueOf(optionsObj[opCount][5]));
							pathBean.setTravelTime(String
									.valueOf(optionsObj[opCount][6]));
							pathBean.setTravelModeClass(String
									.valueOf(optionsObj[opCount][7]));
							pathBean.setTicketNumber(String
									.valueOf(optionsObj[opCount][8]));
							pathBean.setCost(String
									.valueOf(optionsObj[opCount][9]));
							pathBean.setSelFlag(String
									.valueOf(optionsObj[opCount][10]));
							pathBean.setBusTrnFlgNo(String
									.valueOf(optionsObj[opCount][12]));
							pathBean.setRejFlag(String
									.valueOf(optionsObj[opCount][14]));
							pathBean.setRemarks(String
									.valueOf(optionsObj[opCount][15]));
							pathBean.setReadOnlyFlag(flag);
							pathBean.setUserType(userType);
							pathBean.setModeClassMap(hMap);
							pathBean.setViolateFlag("N");
							pathBean.setJourCancelFlag(String
									.valueOf(journeyObj[i][11]));
							if (pathBean.getJourCancelFlag().equals("CC")
									|| pathBean.getJourCancelFlag()
											.equals("PC")) {
								pathBean.setReadOnlyFlag(true);
								optBean.setReadOnlyFlag(true);
							}
							if (bean.getTvlStatus().equals("CO")
									&& pathBean.getSelFlag().equals("Y")
									&& !isPolicyViolate("" + bean.getEmpId(),
											pathBean.getTravelModeClass(),
											pathBean.getCost()).equals(""))
								pathBean.setViolateFlag("Y");
							opCount++;
							pathList.add(pathBean);
						}
						optList.add(optBean);
						optBean.setPath(pathList);
					}
					if (!addFlag) {
						TravelMonitoring pathBean = new TravelMonitoring();
						pathBean.setReadOnlyFlag(flag);
						pathBean.setUserType(userType);
						pathBean.setModeClassMap(hMap);
						pathBean.setJourCode(String.valueOf(journeyObj[i][3]));
						TravelMonitoring optBean = new TravelMonitoring();
						optBean.setUserType(userType);
						optBean.setJourCancelFlag(String
								.valueOf(journeyObj[i][11]));
						pathBean.setSource(String.valueOf(journeyObj[i][0]));
						pathBean.setDest(String.valueOf(journeyObj[i][1]));
						pathBean
								.setTravelDate(String.valueOf(journeyObj[i][9]));
						pathBean
								.setTravelTime(String.valueOf(journeyObj[i][8]));
						pathBean.setTravelModeClass(String
								.valueOf(journeyObj[i][10]));
						pathBean.setJourCancelFlag(String
								.valueOf(journeyObj[i][11]));
						if (pathBean.getJourCancelFlag().equals("CC")
								|| pathBean.getJourCancelFlag().equals("PC")) {
							pathBean.setReadOnlyFlag(true);
							optBean.setReadOnlyFlag(true);
						}
						ArrayList pathList = new ArrayList();
						pathList.add(pathBean);
						optList.add(optBean);
						optBean.setPath(pathList);
					}
					jourList.add(journBean);
					journBean.setOptions(optList);
				}
				bean.setMain(jourList);
			} catch (Exception e) {
				logger.error("Error while setting Iterator : " + e);
				e.printStackTrace();
			}
		} catch (Exception e) {
			logger.error("Exception in getTravelDetails() : " + e);
			e.printStackTrace();
		}
	}

	public void getAccomDetails(HttpServletRequest request,
			TravelMonitoring bean) {
		setEmpInfo(request, bean);
		if (request.getParameter("userType").equals("SUB"))
			setStatus(request, bean, "SCHDTL_LODGE_ASSIGN");
		else
			bean.setViewFlag("true");
		Object[][] accomObj = null;
		Object[][] optionsObj = null;
		try {
			String journeyQry = " SELECT DISTINCT LODGE_CITY,APPL_CODE,LODGE_CODE,TMS_MONITOR_ID,DECODE(MTR_ACC_AVSTATUS,'RE','Y ',MTR_ACC_AVSTATUS), "
					+ " TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'), "
					+ " TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'),TO_CHAR(LODGE_FROMDATE,'DD-MM-YYYY') ,LODGE_FROMTIME, "
					+ " TO_CHAR(LODGE_TODATE,'DD-MM-YYYY'),LODGE_TOTIME,LODGE_HOTELTYPE,HOTEL_TYPE_NAME,LODGE_ROOMTYPE,ROOM_TYPE_NAME, "
					+ " LODGE_PRE_LOCATION,LODGE_STATUS	 "
					+ " FROM TMS_MONITORING "
					+ " INNER JOIN TMS_APP_LODGE_DTL ON(TMS_MONITORING.APPL_ID = TMS_APP_LODGE_DTL.APPL_ID AND TMS_MONITORING.APPL_CODE = TMS_APP_LODGE_DTL.APPL_CODE) "
					+ " INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_LODGE_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_LODGE_DTL.APPL_CODE) "
					+ " INNER JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID = TMS_APP_LODGE_DTL.LODGE_HOTELTYPE) "
					+ " INNER JOIN HRMS_TMS_ROOM_TYPE ON(HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID = TMS_APP_LODGE_DTL.LODGE_ROOMTYPE) "
					+ " WHERE TMS_MONITORING.APPL_CODE = "
					+ request.getParameter("empApplId")
					+ " AND TMS_MONITORING.APPL_ID = "
					+ request.getParameter("applicationId")
					+ " ORDER BY LODGE_CODE";
			accomObj = getSqlModel().getSingleResult(journeyQry);
		} catch (Exception e) {
			logger.error("Exception while executing query : " + e);
			e.printStackTrace();
			return;
		}
		boolean flag = true;
		if (accomObj == null || accomObj.length < 1)
			return;
		accomObj = Utility.checkNullObjArr(accomObj);
		String userType = request.getParameter("userType");
		bean.setUserType(request.getParameter("userType"));
		try {
			String lodgeCode = "";
			for (int i = 0; i < accomObj.length; i++)
				lodgeCode += String.valueOf(accomObj[i][2]) + ",";
			if (lodgeCode.length() > 1) {
				lodgeCode = lodgeCode.substring(0, lodgeCode.length() - 1);
				String optionsQry = " SELECT * FROM (SELECT DISTINCT MONITOR_ID,ACCOM_LODGE_CODE,ACCOM_LODGEDTL_CODE,ACCOM_CITY,TO_CHAR(ACCOM_FROMDATE,'DD-MM-YYYY'), "
						+ " TO_CHAR(ACCOM_TODATE,'DD-MM-YYYY'),ACCOM_CHECKIN,ACCOM_CHECKOUT,ACCOM_HOTELTYPE,HOTEL_TYPE_NAME, "
						+ " ACCOM_ROOMTYPE,ROOM_TYPE_NAME,ACCOM_ADDRESS, "
						+ " ACCOM_BOOKING_AMT,ACCOM_SEL_FLAG,ACCOM_GROUP_CODE,ACCOM_ORDER,ACCOM_REJ_FLAG "
						+ " FROM TMS_SUGG_ACCOM "
						+ " INNER JOIN TMS_MONITORING ON (TMS_MONITORING.TMS_MONITOR_ID = TMS_SUGG_ACCOM.MONITOR_ID) "
						+ " INNER JOIN HRMS_TMS_HOTEL_TYPE ON(TMS_SUGG_ACCOM.ACCOM_HOTELTYPE = HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID) "
						+ " INNER JOIN HRMS_TMS_ROOM_TYPE ON(TMS_SUGG_ACCOM.ACCOM_ROOMTYPE = HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID) "
						+ " WHERE ACCOM_LODGE_CODE IN("
						+ lodgeCode
						+ ") AND MONITOR_ID = "
						+ request.getParameter("monitorId")
						+ " AND APPL_ID = "
						+ request.getParameter("applicationId")
						+ ") ORDER BY ACCOM_LODGE_CODE,ACCOM_GROUP_CODE,ACCOM_ORDER ";
				optionsObj = getSqlModel().getSingleResult(optionsQry);
			}
			if (optionsObj != null && optionsObj.length > 0) {
				Utility.checkNullObjArr(optionsObj);
				bean.setSavedFlag("true");
			}
		} catch (Exception e) {
			logger.error("Exception while getting options : " + e);
			e.printStackTrace();
		}
		String commentsQry = "SELECT MTR_ACCOM_SCH_COMM, MTR_ACCOM_APP_COMM,MTR_ACCOM_APR_COMM FROM TMS_MONITORING "
				+ " WHERE TMS_MONITOR_ID = " + accomObj[0][3];
		Object[][] commentsObj = getSqlModel().getSingleResult(commentsQry);
		bean.setMonitorId(String.valueOf(accomObj[0][3]));
		bean.setAccStatus(String.valueOf(accomObj[0][4]));
		if (request.getParameter("accStatus").equalsIgnoreCase("Hold"))
			bean.setAccStatus("H");
		bean.setTourStartDate(String.valueOf(accomObj[0][5]));
		bean.setTourEndDate(String.valueOf(accomObj[0][6]));
		if ((request.getParameter("userType").equals("SUB") || request
				.getParameter("userType").equals("SCH"))
				&& bean.getAccStatus().trim().equals("Y")
				&& bean.getViewFlag().equals("true"))
			flag = false;
		if (request.getParameter("accStatus").equalsIgnoreCase("Hold")) {
			bean.setAccStatus("H");
			flag = true;
		} else if (request.getParameter("accStatus").equalsIgnoreCase(
				"Pending For Cancellation")) {
			bean.setAccStatus("PC");
			flag = true;
		} else if (request.getParameter("accStatus").equalsIgnoreCase("Cancel")) {
			bean.setAccStatus("CC");
			flag = true;
		}
		bean.setReadOnlyFlag(flag);

		if (commentsObj != null && commentsObj.length > 0) {
			commentsObj = Utility.checkNullObjArr(commentsObj);
			bean.setSchComments(String.valueOf(commentsObj[0][0]));
			bean.setAplComments(String.valueOf(commentsObj[0][1]));
			bean.setAprComments(String.valueOf(commentsObj[0][2]));
		}
		try {
			ArrayList jourList = new ArrayList();
			int opCount = 0;
			for (int i = 0; i < accomObj.length; i++) {
				if (optionsObj == null || optionsObj.length == 0) {
					optionsObj = new Object[0][0];
					Utility.checkNullObjArr(optionsObj);
				}
				TravelMonitoring journBean = new TravelMonitoring();
				ArrayList optList = new ArrayList();
				journBean.setFromCity(String.valueOf(accomObj[i][0]));
				journBean.setUserType(request.getParameter("userType"));
				journBean.setReadOnlyFlag(flag);
				if (String.valueOf(accomObj[i][16]).equals("CC")
						|| String.valueOf(accomObj[i][16]).equals("PC"))
					journBean.setReadOnlyFlag(true);
				boolean addFlag = false;
				for (; opCount < optionsObj.length;) {
					String grpCode = String.valueOf(optionsObj[opCount][15]);
					if (!String.valueOf(optionsObj[opCount][1]).equals(
							String.valueOf(accomObj[i][2]))) {
						if (optionsObj.length > 1)
							break;
					}
					addFlag = true;
					TravelMonitoring optBean = new TravelMonitoring();
					optBean.setUserType(request.getParameter("userType"));
					optBean.setLodgCancelFlag(String.valueOf(accomObj[i][16]));
					optBean.setReadOnlyFlag(flag);
					ArrayList pathList = new ArrayList();
					while (opCount < optionsObj.length
							&& grpCode.equals(String
									.valueOf(optionsObj[opCount][15]))
							&& String.valueOf(optionsObj[opCount][1]).equals(
									String.valueOf(accomObj[i][2]))) {
						TravelMonitoring pathBean = new TravelMonitoring();
						pathBean.setMonitorId(String
								.valueOf(optionsObj[opCount][0]));
						pathBean.setLodgeCode(String.valueOf(accomObj[i][2]));
						pathBean.setLodgeDtlId(String
								.valueOf(optionsObj[opCount][2]));
						pathBean.setSource(String
								.valueOf(optionsObj[opCount][3]));
						pathBean.setAccomFromDate(String
								.valueOf(optionsObj[opCount][4]));
						pathBean.setAccomToDate(String
								.valueOf(optionsObj[opCount][5]));
						pathBean.setAccomFromTime(String
								.valueOf(optionsObj[opCount][6]));
						pathBean.setAccomToTime(String
								.valueOf(optionsObj[opCount][7]));
						pathBean.setHotelTypeCode(String
								.valueOf(optionsObj[opCount][8]));
						pathBean.setHotelTypeName(String
								.valueOf(optionsObj[opCount][9]));
						pathBean.setRoomTypeCode(String
								.valueOf(optionsObj[opCount][10]));
						pathBean.setRoomTypeName(String
								.valueOf(optionsObj[opCount][11]));
						pathBean.setAccomAddress(String
								.valueOf(optionsObj[opCount][12]));
						pathBean.setAccomBookingAmt(String
								.valueOf(optionsObj[opCount][13]));
						pathBean.setSelFlag(String
								.valueOf(optionsObj[opCount][14]));
						pathBean.setLodgCancelFlag(String
								.valueOf(accomObj[i][16]));
						if (bean.getAccStatus().equals("CO")
								&& pathBean.getSelFlag().equals("Y")
								&& !isPolicyViolateAccom("" + bean.getEmpId(),
										pathBean.getRoomTypeCode()).equals(""))
							pathBean.setViolateFlag("Y");
						pathBean.setRejFlag(String
								.valueOf(optionsObj[opCount][17]));
						pathBean.setReadOnlyFlag(flag);
						pathBean.setUserType(userType);
						optBean.setLodgCancelFlag(String
								.valueOf(accomObj[i][16]));
						if (pathBean.getLodgCancelFlag().equals("CC")
								|| pathBean.getLodgCancelFlag().equals("PC")) {
							pathBean.setReadOnlyFlag(true);
							optBean.setReadOnlyFlag(true);
						}
						opCount++;
						pathList.add(pathBean);
					}
					optList.add(optBean);
					optBean.setPath(pathList);
				}
				if (!addFlag) {
					TravelMonitoring pathBean = new TravelMonitoring();
					pathBean.setReadOnlyFlag(flag);
					pathBean.setUserType(userType);
					pathBean.setLodgeCode(String.valueOf(accomObj[i][2]));
					TravelMonitoring optBean = new TravelMonitoring();
					optBean.setUserType(userType);
					optBean.setLodgCancelFlag(String.valueOf(accomObj[i][16]));
					pathBean.setSource(String.valueOf(accomObj[i][0]));
					pathBean.setAccomFromDate(String.valueOf(accomObj[i][7]));
					pathBean.setAccomToDate(String.valueOf(accomObj[i][9]));
					pathBean.setAccomFromTime(String.valueOf(accomObj[i][8]));
					pathBean.setAccomToTime(String.valueOf(accomObj[i][10]));
					pathBean.setHotelTypeCode(String.valueOf(accomObj[i][11]));
					pathBean.setHotelTypeName(String.valueOf(accomObj[i][12]));
					pathBean.setRoomTypeCode(String.valueOf(accomObj[i][13]));
					pathBean.setRoomTypeName(String.valueOf(accomObj[i][14]));
					pathBean.setAccomAddress(String.valueOf(accomObj[i][15]));
					pathBean.setLodgCancelFlag(String.valueOf(accomObj[i][16]));
					if (pathBean.getLodgCancelFlag().equals("CC")
							|| pathBean.getLodgCancelFlag().equals("PC")) {
						pathBean.setReadOnlyFlag(true);
						optBean.setReadOnlyFlag(true);
					}
					ArrayList pathList = new ArrayList();
					pathList.add(pathBean);
					optList.add(optBean);
					optBean.setPath(pathList);
				}
				jourList.add(journBean);
				journBean.setOptions(optList);
			}
			bean.setMain(jourList);
		} catch (Exception e) {
			logger.error("Error while setting Iterator : " + e);
			e.printStackTrace();
		}
	}

	public void getLocConvDetails(HttpServletRequest request,
			TravelMonitoring bean) {
		try {
			setEmpInfo(request, bean);
			if (request.getParameter("userType").equals("SUB"))
				setStatus(request, bean, "SCHDTL_LOCAL_ASSIGN");
			else
				bean.setViewFlag("true");
			Object[][] locConvObj = null;
			Object[][] optionsObj = null;

			try {
				String locConQry = " SELECT DISTINCT CONV_CITY,APPL_CODE,CONV_CODE,TMS_MONITOR_ID,DECODE(MTR_LOCAL_AVSTATUS,'RE','Y ',MTR_LOCAL_AVSTATUS),TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY'),TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY'), "
						+ " TO_CHAR(CONV_FROMDATE,'DD-MM-YYYY'),CONV_FROMTIME,CONV_TOTIME,CONV_MEDIUM,CONV_STATUS FROM TMS_MONITORING "
						+ " INNER JOIN TMS_APP_CONV_DTL ON(TMS_MONITORING.APPL_ID = TMS_APP_CONV_DTL.APPL_ID AND TMS_MONITORING.APPL_CODE = TMS_APP_CONV_DTL.APPL_CODE) "
						+ " INNER JOIN TMS_APP_TOUR_DTL ON(TMS_APP_TOUR_DTL.APPL_ID = TMS_APP_CONV_DTL.APPL_ID AND TMS_APP_TOUR_DTL.APPL_CODE = TMS_APP_CONV_DTL.APPL_CODE)"
						+ " WHERE TMS_MONITORING.APPL_CODE="
						+ request.getParameter("empApplId")
						+ " AND TMS_MONITORING.APPL_ID = "
						+ request.getParameter("applicationId")
						+ " ORDER BY CONV_CODE";

				locConvObj = getSqlModel().getSingleResult(locConQry);
			} catch (Exception e) {
				logger.error("Exception while executing query : " + e);
				e.printStackTrace();
				return;
			}

			boolean flag = true;
			if (locConvObj == null || locConvObj.length < 1)
				return;
			locConvObj = Utility.checkNullObjArr(locConvObj);

			String userType = request.getParameter("userType");
			bean.setUserType(request.getParameter("userType"));

			try {
				String locConCode = "";
				for (int i = 0; i < locConvObj.length; i++)
					locConCode += String.valueOf(locConvObj[i][2]) + ",";
				if (locConCode.length() > 1) {
					locConCode = locConCode.substring(0,
							locConCode.length() - 1);

					String optionsQry = "SELECT * FROM (SELECT DISTINCT MONITOR_ID,LOCCONV_CONV_CODE,LOCCONV_DTL_CODE,LOCCONV_SOURCE, TO_CHAR(LOCCONV_DATE,'DD-MM-YYYY'), LOCCONV_TRAVELMODE,"
							+ " LOCCONV_TRAVELMODE_NO, LOCCONV_CONTACTPERSON, LOCCONV_CONTACTNO, LOCCONV_PICKUPPERSON, LOCCONV_FROM_TIME,LOCCONV_TO_TIME, LOCCONV_PICKUPPLACE, LOCCONV_TARIFFCOST, LOCCONV_SEL_FLAG, LOCCONV_GROUP_CODE,LOCCONV_ORDER"
							+ " FROM TMS_SUGG_LOC_CONV "
							+ " INNER JOIN TMS_MONITORING ON (TMS_MONITORING.TMS_MONITOR_ID = TMS_SUGG_LOC_CONV.MONITOR_ID) "
							+ " WHERE LOCCONV_CONV_CODE IN("
							+ locConCode
							+ ") AND MONITOR_ID = "
							+ request.getParameter("monitorId")
							+ " AND APPL_ID = "
							+ request.getParameter("applicationId")
							+ ") ORDER BY LOCCONV_CONV_CODE,LOCCONV_GROUP_CODE,LOCCONV_ORDER ";
					optionsObj = getSqlModel().getSingleResult(optionsQry);

				}
				if (optionsObj != null && optionsObj.length > 0) {
					Utility.checkNullObjArr(optionsObj);
					bean.setSavedFlag("true");
				}
			} catch (Exception e) {
				logger.error("Exception while getting options : " + e);
				e.printStackTrace();
			}

			String commentsQry = "SELECT MTR_LOCAL_SCH_COMM, MTR_LOCAL_APP_COMM,MTR_LOCAL_APR_COMM FROM TMS_MONITORING "
					+ " WHERE TMS_MONITOR_ID = " + locConvObj[0][3];
			Object[][] commentsObj = getSqlModel().getSingleResult(commentsQry);
			LinkedMap hMap = getTravelModeClass();

			bean.setMonitorId(String.valueOf(locConvObj[0][3]));
			bean.setLocConStatus(String.valueOf(locConvObj[0][4]));
			bean.setTourStartDate(String.valueOf(locConvObj[0][5]));
			bean.setTourEndDate(String.valueOf(locConvObj[0][6]));
			if ((request.getParameter("userType").equals("SUB") || request
					.getParameter("userType").equals("SCH"))
					&& bean.getLocConStatus().trim().equals("Y")
					&& bean.getViewFlag().equals("true"))
				flag = false;
			if (request.getParameter("locStatus").equalsIgnoreCase("Hold")) {
				bean.setLocConStatus("H");
				flag = true;
			} else if (request.getParameter("locStatus").equalsIgnoreCase(
					"Pending For Cancellation")) {
				bean.setLocConStatus("PC");
				flag = true;
			} else if (request.getParameter("locStatus").equalsIgnoreCase(
					"Cancel")) {
				bean.setLocConStatus("CC");
				flag = true;
			}
			bean.setReadOnlyFlag(flag);

			if (commentsObj != null && commentsObj.length > 0) {
				commentsObj = Utility.checkNullObjArr(commentsObj);
				bean.setSchComments(String.valueOf(commentsObj[0][0]));
				bean.setAplComments(String.valueOf(commentsObj[0][1]));
			}
			try {
				ArrayList locConList = new ArrayList();
				int opCount = 0;
				for (int i = 0; i < locConvObj.length; i++) {
					if (optionsObj == null || optionsObj.length == 0) {
						optionsObj = new Object[0][0];
						Utility.checkNullObjArr(optionsObj);
					}
					TravelMonitoring locConBean = new TravelMonitoring();
					ArrayList optList = new ArrayList();
					locConBean.setLocConCity(String.valueOf(locConvObj[i][0]));
					locConBean.setUserType(request.getParameter("userType"));
					locConBean.setReadOnlyFlag(flag);
					if (String.valueOf(locConvObj[i][11]).equals("CC")
							|| String.valueOf(locConvObj[i][11]).equals("PC"))
						locConBean.setReadOnlyFlag(true);
					boolean addFlag = false;
					for (; opCount < optionsObj.length;) {
						String grpCode = String
								.valueOf(optionsObj[opCount][15]);
						if (!String.valueOf(optionsObj[opCount][1]).equals(
								String.valueOf(locConvObj[i][2]))) {
							if (optionsObj.length > 1)
								break;
						}
						addFlag = true;
						TravelMonitoring optBean = new TravelMonitoring();
						optBean.setUserType(request.getParameter("userType"));
						optBean.setLocCancelFlag(String
								.valueOf(locConvObj[i][11]));
						optBean.setReadOnlyFlag(flag);
						ArrayList pathList = new ArrayList();

						while (opCount < optionsObj.length
								&& grpCode.equals(String
										.valueOf(optionsObj[opCount][15]))
								&& String
										.valueOf(optionsObj[opCount][1])
										.equals(
												String
														.valueOf(locConvObj[i][2]))) {
							TravelMonitoring pathBean = new TravelMonitoring();

							pathBean.setMonitorId(String
									.valueOf(optionsObj[opCount][0]));
							pathBean.setLocConvCode(String
									.valueOf(locConvObj[i][2]));
							pathBean.setLocDtlId(String
									.valueOf(optionsObj[opCount][2]));
							pathBean
									.setSource(String.valueOf(optionsObj[i][3]));
							pathBean.setTravelDate(String
									.valueOf(optionsObj[opCount][4]));
							pathBean.setTravelModeClass(String
									.valueOf(optionsObj[opCount][5]));
							pathBean.setLocConVehNo(String
									.valueOf(optionsObj[opCount][6]));
							pathBean.setLocConConPer(String
									.valueOf(optionsObj[opCount][7]));
							pathBean.setLocConConNo(String
									.valueOf(optionsObj[opCount][8]));
							pathBean.setLocConPicPer(String
									.valueOf(optionsObj[opCount][9]));
							pathBean.setLocConFrmTime(String
									.valueOf(optionsObj[opCount][10]));
							pathBean.setLocConToTime(String
									.valueOf(optionsObj[opCount][11]));
							pathBean.setLocConPicPlace(String
									.valueOf(optionsObj[opCount][12]));
							pathBean.setLocConToriffCost(String
									.valueOf(optionsObj[opCount][13]));
							pathBean.setSelFlag(String
									.valueOf(optionsObj[opCount][14]));
							pathBean.setReadOnlyFlag(flag);
							pathBean.setUserType(userType);
							pathBean.setModeClassMap(hMap);
							pathBean.setLocCancelFlag(String
									.valueOf(locConvObj[i][11]));
							if (pathBean.getLocCancelFlag().equals("CC")
									|| pathBean.getLocCancelFlag().equals("PC")) {
								pathBean.setReadOnlyFlag(true);
								optBean.setReadOnlyFlag(true);
							}
							opCount++;
							pathList.add(pathBean);
						}
						optBean.setLocCancelFlag(String
								.valueOf(locConvObj[i][11]));
						optList.add(optBean);
						optBean.setPath(pathList);
					}
					if (!addFlag) {
						TravelMonitoring pathBean = new TravelMonitoring();
						pathBean.setReadOnlyFlag(flag);
						pathBean.setUserType(userType);
						pathBean.setLocConvCode(String
								.valueOf(locConvObj[i][2]));
						pathBean.setSource(String.valueOf(locConvObj[i][0]));
						pathBean
								.setTravelDate(String.valueOf(locConvObj[i][7]));
						pathBean.setTravelModeClass(String
								.valueOf(locConvObj[i][10]));
						pathBean.setLocConFrmTime(String
								.valueOf(locConvObj[i][8]));
						pathBean.setLocConToTime(String
								.valueOf(locConvObj[i][9]));
						pathBean.setLocCancelFlag(String
								.valueOf(locConvObj[i][11]));
						TravelMonitoring optBean = new TravelMonitoring();
						optBean.setUserType(userType);
						optBean.setLocCancelFlag(String
								.valueOf(locConvObj[i][11]));
						if (pathBean.getLocCancelFlag().equals("CC")
								|| pathBean.getLocCancelFlag().equals("PC")) {
							pathBean.setReadOnlyFlag(true);
							optBean.setReadOnlyFlag(true);
						}
						ArrayList pathList = new ArrayList();
						pathList.add(pathBean);
						optList.add(optBean);
						optBean.setPath(pathList);
					}
					locConList.add(locConBean);
					locConBean.setOptions(optList);
				}
				bean.setMain(locConList);
			} catch (Exception e) {
				logger.error("Error while setting Iterator : " + e);
				e.printStackTrace();
			}
		} catch (Exception e) {
			logger.error("Exception in getTravelDetails() : " + e);
			e.printStackTrace();
		}
	}

	public String[] setEmpInfo(HttpServletRequest request, TravelMonitoring bean) {
		String[] empInfo = null;
		try {
			empInfo = new String[12];
			String empInfoQry = "";
			if (Integer.parseInt(request.getParameter("empId")) != 0) {
				empInfoQry = " SELECT DISTINCT EMP_TOKEN,EMP_FNAME || ' '|| EMP_MNAME || ' ' ||EMP_LNAME,POLICY_NAME,CADRE_NAME,POLICY_ID,APPL_EMP_CONTACT,EMP_CADRE "
						+ " FROM HRMS_EMP_OFFC "
						+ " LEFT JOIN TMS_POLICY_GRADE_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID = EMP_CADRE) "
						+ " LEFT JOIN TMS_TRAVEL_POLICY ON(TMS_TRAVEL_POLICY.POLICY_ID = TMS_POLICY_GRADE_DTL.POLICY_ID) "
						+ " LEFT JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID = HRMS_EMP_OFFC.EMP_CADRE) "
						+ " LEFT JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_EMP_CODE = HRMS_EMP_OFFC.EMP_ID) "
						+ " WHERE EMP_ID = "
						+ request.getParameter("empId")
						+ " AND APPL_CODE = "
						+ request.getParameter("empApplId");
				setEmpDtls(bean, request);
			} else {
				empInfoQry = "  SELECT DISTINCT '',TMS_APP_GUEST_DTL.GUEST_NAME,'','','',GUEST_CONTACT,'' "
						+ " FROM TMS_APP_GUEST_DTL WHERE APPL_CODE = "
						+ request.getParameter("empApplId");
				setGuestDtls(bean, request);
			}
			Object[][] empObj = getSqlModel().getSingleResult(empInfoQry);
			if (empObj != null && empObj.length > 0) {

				empObj = Utility.checkNullObjArr(empObj);
				bean.setAppToken(String.valueOf(empObj[0][0]));
				bean.setApplicant(String.valueOf(empObj[0][1]));
				bean.setPolicyName(String.valueOf(empObj[0][2]));
				bean.setGradeName(String.valueOf(empObj[0][3]));
				bean.setPolicyId(String.valueOf(empObj[0][4]));
				bean.setContactNo(String.valueOf(empObj[0][5]));
				bean.setGradeId(String.valueOf(empObj[0][6]));
				bean.setEmpId(""
						+ Integer.parseInt(request.getParameter("empId")));
				bean.setEmpApplId(Integer.parseInt(request
						.getParameter("empApplId")));
				bean.setApplicationId(Integer.parseInt(request
						.getParameter("applicationId")));
				bean.setUserType(request.getParameter("userType")); // SCH
				bean.setIniEmpId(Integer.parseInt(request
						.getParameter("iniEmpId")));
				bean.setApplicationDate(request.getParameter("applDate"));
			}
		} catch (Exception e) {
			logger.error("Exception in setEmpInfo() : " + e);
		}

		return empInfo;
	}

	public void setStatus(HttpServletRequest request, TravelMonitoring bean,
			String field) {
		try {
			String selQry = " SELECT DISTINCT DECODE("
					+ field
					+ ",'Y','true','false') FROM TMS_SCH_DTL "
					+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_ID = TMS_SCH_DTL.SCH_DESK_ID) "
					+ " WHERE DESK_APPL_CODE = "
					+ request.getParameter("empApplId")
					+ " AND SCHDTL_SUBSCHLAR_ECODE = " + bean.getUserEmpId()
					+ " AND DESK_APPL_ID = "
					+ request.getParameter("applicationId");
			Object[][] selObj = getSqlModel().getSingleResult(selQry);
			if (selObj != null && selObj.length > 0)
				bean.setViewFlag(String.valueOf(selObj[0][0]));
			else
				bean.setViewFlag("true");
		} catch (Exception e) {
			logger.error("Exception : " + e);
		}
	}

	public void addRowToOption_Travel(TravelMonitoring monitorBean,
			HttpServletRequest request) {
		String pathName = request.getParameter("pathName");
		int maxMainCount = Integer.parseInt(request
				.getParameter("maxMainCount"));
		int maxOptCount = Integer.parseInt(request.getParameter("maxOptCount"));
		String userType = request.getParameter("userType");
		LinkedMap hMap = getTravelModeClass();
		boolean readOnly = Boolean
				.valueOf(request.getParameter("readOnlyFlag"));
		monitorBean.setUserType(userType);
		monitorBean.setReadOnlyFlag(readOnly);
		String[] temp = pathName.split("_");
		int mainCount = Integer.parseInt(temp[0]);
		int optCount = Integer.parseInt(temp[1]);
		ArrayList jourList = new ArrayList();
		String[][] dataObj = null;
		for (int i = 0; i < maxMainCount; i++) {
			TravelMonitoring journBean = new TravelMonitoring();
			ArrayList optList = new ArrayList();
			journBean.setFromCity(request.getParameter("fromCity_" + (i + 1)));
			journBean.setToCity(request.getParameter("toCity_" + (i + 1)));
			journBean.setUserType(userType);
			journBean.setReadOnlyFlag(Boolean.valueOf(request
					.getParameter("readOnlyFlag" + (i + 1))));
			for (int j = 0; j < maxOptCount; j++) {
				TravelMonitoring optBean = new TravelMonitoring();
				optBean.setUserType(userType);
				optBean.setReadOnlyFlag(readOnly);
				ArrayList pathList = new ArrayList();
				dataObj = getDataFromView_Travel(request, i, j);
				if (dataObj == null || dataObj.length <= 0)
					break;
				optBean.setJourCancelFlag(request.getParameter("jourCancelFlag"
						+ (i + 1) + "_" + (j + 1)));
				for (int k = 0; k < dataObj.length + 1; k++) {
					TravelMonitoring pathBean = new TravelMonitoring();
					if (k < dataObj.length) {
						pathBean.setSource(dataObj[k][0]);
						pathBean.setDest(dataObj[k][1]);
						pathBean.setTravelDate(dataObj[k][2]);
						pathBean.setTravelTime(dataObj[k][3]);
						pathBean.setTravelModeClass(dataObj[k][4]);
						pathBean.setBusTrnFlgNo(dataObj[k][5]);
						pathBean.setTicketNumber(dataObj[k][6]);
						pathBean.setCost(dataObj[k][7]);
						pathBean.setMonitorId(dataObj[k][8]);
						pathBean.setJourCode(dataObj[k][9]);
						pathBean.setTrvlDtlId(dataObj[k][10]);
						pathBean.setSelFlag(dataObj[k][11]);
						pathBean.setRemarks(dataObj[k][12]);
						pathBean.setReadOnlyFlag(readOnly);
						pathBean.setUserType(userType);
						pathBean.setModeClassMap(hMap);
						pathBean.setJourCancelFlag(optBean.getJourCancelFlag());
						pathList.add(pathBean);
					} else if ((i + 1) == mainCount && (j + 1) == optCount) {
						pathBean.setModeClassMap(hMap);
						pathBean.setReadOnlyFlag(readOnly);
						pathBean.setSource(dataObj[0][0]);
						pathBean.setDest(dataObj[0][1]);
						pathBean.setTravelDate(dataObj[0][2]);
						pathBean.setTravelTime(dataObj[0][3]);
						pathBean.setTravelModeClass(dataObj[0][4]);
						pathBean.setUserType(userType);
						pathList.add(pathBean);
					}
				}
				optList.add(optBean);
				optBean.setPath(pathList);

			}
			jourList.add(journBean);
			journBean.setOptions(optList);
		}
		monitorBean.setMain(jourList);
	}

	public void addOption_Travel(TravelMonitoring monitorBean,
			HttpServletRequest request) {
		String pathName = request.getParameter("pathName");
		int maxMainCount = Integer.parseInt(request
				.getParameter("maxMainCount"));
		int maxOptCount = Integer.parseInt(request.getParameter("maxOptCount"));
		String userType = request.getParameter("userType");
		boolean readOnly = Boolean
				.valueOf(request.getParameter("readOnlyFlag"));
		LinkedMap hMap = getTravelModeClass();
		monitorBean.setUserType(userType);
		monitorBean.setReadOnlyFlag(readOnly);
		String[] temp = pathName.split("_");
		int mainCount = Integer.parseInt(temp[0]);
		ArrayList jourList = new ArrayList();
		String[][] dataObj = null;
		String[][] cloneObj = null;
		for (int i = 0; i < maxMainCount; i++) {
			TravelMonitoring journBean = new TravelMonitoring();
			ArrayList optList = new ArrayList();
			journBean.setFromCity(request.getParameter("fromCity_" + (i + 1)));
			journBean.setToCity(request.getParameter("toCity_" + (i + 1)));
			journBean.setUserType(userType);
			journBean.setReadOnlyFlag(Boolean.valueOf(request
					.getParameter("readOnlyFlag" + (i + 1))));
			for (int j = 0; j < maxOptCount; j++) {
				TravelMonitoring optBean = new TravelMonitoring();
				optBean.setUserType(userType);
				optBean.setReadOnlyFlag(readOnly);
				ArrayList pathList = new ArrayList();

				dataObj = getDataFromView_Travel(request, i, j);
				if (dataObj == null || dataObj.length <= 0)
					break;
				cloneObj = dataObj.clone();
				optBean.setJourCancelFlag(request.getParameter("jourCancelFlag"
						+ (i + 1) + "_" + (j + 1)));
				for (int k = 0; k < dataObj.length + 1; k++) {
					TravelMonitoring pathBean = new TravelMonitoring();
					if (k < dataObj.length) {
						pathBean.setSource(dataObj[k][0]);
						pathBean.setDest(dataObj[k][1]);
						pathBean.setTravelDate(dataObj[k][2]);
						pathBean.setTravelTime(dataObj[k][3]);
						pathBean.setTravelModeClass(dataObj[k][4]);
						pathBean.setBusTrnFlgNo(dataObj[k][5]);
						pathBean.setTicketNumber(dataObj[k][6]);
						pathBean.setCost(dataObj[k][7]);
						pathBean.setMonitorId(dataObj[k][8]);
						pathBean.setJourCode(dataObj[k][9]);
						pathBean.setTrvlDtlId(dataObj[k][10]);
						pathBean.setSelFlag(dataObj[k][11]);
						pathBean.setRemarks(dataObj[k][12]);
						pathBean.setReadOnlyFlag(readOnly);
						pathBean.setUserType(userType);
						pathBean.setModeClassMap(hMap);
						pathBean.setJourCancelFlag(optBean.getJourCancelFlag());
						pathList.add(pathBean);
					}
				}
				optBean.setSrNo(1);
				optList.add(optBean);
				optBean.setPath(pathList);

			}
			if ((i + 1) == mainCount) {
				TravelMonitoring pathBean = new TravelMonitoring();
				pathBean.setReadOnlyFlag(readOnly);
				pathBean.setUserType(userType);
				pathBean.setModeClassMap(hMap);
				if (cloneObj != null) {
					pathBean.setSource(cloneObj[0][0]);
					pathBean.setDest(cloneObj[0][1]);
					pathBean.setTravelDate(cloneObj[0][2]);
					pathBean.setTravelTime(cloneObj[0][3]);
					pathBean.setTravelModeClass(cloneObj[0][4]);
				}
				TravelMonitoring optBean = new TravelMonitoring();
				optBean.setUserType(userType);
				ArrayList pathList = new ArrayList();
				pathList.add(pathBean);
				optList.add(optBean);
				optBean.setPath(pathList);
			}
			journBean.setSrNo(1);
			jourList.add(journBean);
			journBean.setOptions(optList);
		}
		monitorBean.setMain(jourList);
	}

	public void removeOption_Travel(TravelMonitoring bean,
			HttpServletRequest request) {
		String pathName = request.getParameter("pathName");
		int maxMainCount = Integer.parseInt(request
				.getParameter("maxMainCount"));
		int maxOptCount = Integer.parseInt(request.getParameter("maxOptCount"));
		String[] temp = pathName.split("_");
		String userType = request.getParameter("userType");
		boolean readOnly = Boolean
				.valueOf(request.getParameter("readOnlyFlag"));
		LinkedMap hMap = getTravelModeClass();
		bean.setUserType(userType);
		bean.setReadOnlyFlag(readOnly);
		int mainCount = Integer.parseInt(temp[0]);
		int optCount = Integer.parseInt(temp[1]);
		ArrayList jourList = new ArrayList();
		for (int i = 0; i < maxMainCount; i++) {
			TravelMonitoring journBean = new TravelMonitoring();
			ArrayList optList = new ArrayList();
			journBean.setFromCity(request.getParameter("fromCity_" + (i + 1)));
			journBean.setToCity(request.getParameter("toCity_" + (i + 1)));
			journBean.setUserType(userType);
			journBean.setReadOnlyFlag(Boolean.valueOf(request
					.getParameter("readOnlyFlag" + (i + 1))));
			for (int j = 0; j < maxOptCount; j++) {
				if ((i + 1) == mainCount && (j + 1) == optCount)
					continue;
				TravelMonitoring optBean = new TravelMonitoring();
				optBean.setUserType(userType);
				optBean.setReadOnlyFlag(readOnly);
				ArrayList pathList = new ArrayList();
				String[][] dataObj = getDataFromView_Travel(request, i, j);
				if (dataObj == null || dataObj.length <= 0)
					break;
				optBean.setJourCancelFlag(request.getParameter("jourCancelFlag"
						+ (i + 1) + "_" + (j + 1)));
				for (int k = 0; k < dataObj.length + 1; k++) {
					TravelMonitoring pathBean = new TravelMonitoring();
					if (k < dataObj.length) {
						pathBean.setSource(dataObj[k][0]);
						pathBean.setDest(dataObj[k][1]);
						pathBean.setTravelDate(dataObj[k][2]);
						pathBean.setTravelTime(dataObj[k][3]);
						pathBean.setTravelModeClass(dataObj[k][4]);
						pathBean.setBusTrnFlgNo(dataObj[k][5]);
						pathBean.setTicketNumber(dataObj[k][6]);
						pathBean.setCost(dataObj[k][7]);
						pathBean.setMonitorId(dataObj[k][8]);
						pathBean.setJourCode(dataObj[k][9]);
						pathBean.setTrvlDtlId(dataObj[k][10]);
						pathBean.setSelFlag(dataObj[k][11]);
						pathBean.setRemarks(dataObj[k][12]);
						pathBean.setReadOnlyFlag(readOnly);
						pathBean.setUserType(userType);
						pathBean.setModeClassMap(hMap);
						pathBean.setJourCancelFlag(optBean.getJourCancelFlag());
						pathList.add(pathBean);
					}
				}
				optList.add(optBean);
				optBean.setPath(pathList);

			}
			jourList.add(journBean);
			journBean.setOptions(optList);
		}
		bean.setMain(jourList);
	}

	public void removeOptionFromRow_Travel(TravelMonitoring bean,
			HttpServletRequest request) {
		String pathName = request.getParameter("pathName");
		int maxMainCount = Integer.parseInt(request
				.getParameter("maxMainCount"));
		int maxOptCount = Integer.parseInt(request.getParameter("maxOptCount"));
		String userType = request.getParameter("userType");
		boolean readOnly = Boolean
				.valueOf(request.getParameter("readOnlyFlag"));
		LinkedMap hMap = getTravelModeClass();
		bean.setUserType(userType);
		bean.setReadOnlyFlag(readOnly);
		String[] temp = pathName.split("_");
		int mainCount = Integer.parseInt(temp[0]);
		int optCount = Integer.parseInt(temp[1]);
		int rowCount = Integer.parseInt(temp[2]);
		ArrayList jourList = new ArrayList();
		for (int i = 0; i < maxMainCount; i++) {
			TravelMonitoring journBean = new TravelMonitoring();
			ArrayList optList = new ArrayList();
			journBean.setFromCity(request.getParameter("fromCity_" + (i + 1)));
			journBean.setToCity(request.getParameter("toCity_" + (i + 1)));
			journBean.setUserType(userType);
			journBean.setReadOnlyFlag(Boolean.valueOf(request
					.getParameter("readOnlyFlag" + (i + 1))));
			for (int j = 0; j < maxOptCount; j++) {
				TravelMonitoring optBean = new TravelMonitoring();
				optBean.setUserType(userType);
				optBean.setReadOnlyFlag(readOnly);
				ArrayList pathList = new ArrayList();
				String[][] dataObj = getDataFromView_Travel(request, i, j);
				if (dataObj == null || dataObj.length <= 0)
					break;
				optBean.setJourCancelFlag(request.getParameter("jourCancelFlag"
						+ (i + 1) + "_" + (j + 1)));
				boolean oneRowFlag = false;
				for (int k = 0; k < dataObj.length; k++) {
					if ((i + 1) == mainCount && (j + 1) == optCount
							&& (k + 1) == rowCount) {
						if (dataObj.length == 1)
							oneRowFlag = true;
						continue;
					}
					TravelMonitoring pathBean = new TravelMonitoring();
					pathBean.setSource(dataObj[k][0]);
					pathBean.setDest(dataObj[k][1]);
					pathBean.setTravelDate(dataObj[k][2]);
					pathBean.setTravelTime(dataObj[k][3]);
					pathBean.setTravelModeClass(dataObj[k][4]);
					pathBean.setBusTrnFlgNo(dataObj[k][5]);
					pathBean.setTicketNumber(dataObj[k][6]);
					pathBean.setCost(dataObj[k][7]);
					pathBean.setMonitorId(dataObj[k][8]);
					pathBean.setJourCode(dataObj[k][9]);
					pathBean.setTrvlDtlId(dataObj[k][10]);
					pathBean.setSelFlag(dataObj[k][11]);
					pathBean.setRemarks(dataObj[k][12]);
					pathBean.setReadOnlyFlag(readOnly);
					pathBean.setUserType(userType);
					pathBean.setModeClassMap(hMap);
					pathBean.setJourCancelFlag(optBean.getJourCancelFlag());
					pathList.add(pathBean);
				}
				if (!oneRowFlag) {
					optList.add(optBean);
					optBean.setPath(pathList);
				}
			}
			jourList.add(journBean);
			journBean.setOptions(optList);
		}
		bean.setMain(jourList);
	}

	public String[][] getDataFromView_Travel(HttpServletRequest request, int i,
			int j) {
		String[] srcCity = request.getParameterValues("source_" + (i + 1) + "_"
				+ (j + 1));
		String[] destCity = request.getParameterValues("dest_" + (i + 1) + "_"
				+ (j + 1));
		String[] date = request.getParameterValues("travelDate_" + (i + 1)
				+ "_" + (j + 1));
		String[] time = request.getParameterValues("travelTime_" + (i + 1)
				+ "_" + (j + 1));
		String[] mode = request.getParameterValues("mode_" + (i + 1) + "_"
				+ (j + 1));
		String[] modeId = request.getParameterValues("modeId_" + (i + 1) + "_"
				+ (j + 1));
		String[] ticktNo = request.getParameterValues("ticketNumber_" + (i + 1)
				+ "_" + (j + 1));
		String[] cost = request.getParameterValues("cost_" + (i + 1) + "_"
				+ (j + 1));
		String[] monitorId = request.getParameterValues("monitorId_" + (i + 1)
				+ "_" + (j + 1));
		String[] jourCode = request.getParameterValues("jourCode_" + (i + 1)
				+ "_" + (j + 1));
		String[] trvlDtlId = request.getParameterValues("trvlDtlId_" + (i + 1)
				+ "_" + (j + 1));
		String[] selFlag = request.getParameterValues("selFlag_" + (i + 1)
				+ "_" + (j + 1));
		String[] remarks = request.getParameterValues("remarks_" + (i + 1)
				+ "_" + (j + 1));
		String[][] dataObj = null;
		if (srcCity != null && srcCity.length > 0) {
			dataObj = new String[srcCity.length][13];
			for (int k = 0; k < dataObj.length; k++) {
				dataObj[k][0] = srcCity[k];
				dataObj[k][1] = destCity[k];
				dataObj[k][2] = date[k];
				dataObj[k][3] = time[k];
				dataObj[k][4] = mode[k];
				dataObj[k][5] = modeId[k];
				dataObj[k][6] = ticktNo[k];
				dataObj[k][7] = cost[k];
				dataObj[k][8] = monitorId[k];
				dataObj[k][9] = jourCode[k];
				dataObj[k][10] = trvlDtlId[k];
				dataObj[k][11] = selFlag[k];
				dataObj[k][12] = remarks[k];
			}
		}
		return dataObj;
	}

	public void save_Travel(TravelMonitoring bean, HttpServletRequest request) {
		try {
			String applCode = request.getParameter("empApplId");
			int totCount = 0;
			String[] monitorId = null;
			String insrtMonId = request.getParameter("monitorId");
			String updateQry = " UPDATE TMS_MONITORING SET MTR_TRVL_SCH_COMM = '"
					+ bean.getSchComments()
					+ "', MTR_TRVL_APP_COMM = '"
					+ bean.getAplComments()
					+ "'"
					+ " WHERE TMS_MONITOR_ID = "
					+ insrtMonId;
			getSqlModel().singleExecute(updateQry);
			if (request.getParameter("userType").equals("APL")
					|| request.getParameter("userType").equals("APR"))
				return;
			Object[][] saveObj = new Object[Integer.parseInt(request
					.getParameter("totCount"))][13];
			String maxDtlCodeQry = "SELECT NVL(MAX(TVLNG_DTL_CODE),0)+1 FROM TMS_SUGG_TRAVELLING";
			String[] opCount = request.getParameterValues("radioHidden");
			Object[][] maxCodeObj = getSqlModel()
					.getSingleResult(maxDtlCodeQry);
			int maxDtlCode = Integer.parseInt(String.valueOf(maxCodeObj[0][0]));
			int jrnCount = Integer.parseInt(request.getParameter("jrnCount"));
			for (int i = 0; i < jrnCount; i++) {
				int optionCount = Integer.parseInt(opCount[i]);
				int groupCode = 1;
				for (int j = 0; j < optionCount; j++) {
					String[] srcCity = request.getParameterValues("source_"
							+ (i + 1) + "_" + (j + 1));
					String[] destCity = request.getParameterValues("dest_"
							+ (i + 1) + "_" + (j + 1));
					String[] date = request.getParameterValues("travelDate_"
							+ (i + 1) + "_" + (j + 1));
					String[] time = request.getParameterValues("travelTime_"
							+ (i + 1) + "_" + (j + 1));
					String[] mode = request.getParameterValues("mode_"
							+ (i + 1) + "_" + (j + 1));
					String[] cost = request.getParameterValues("cost_"
							+ (i + 1) + "_" + (j + 1));
					monitorId = request.getParameterValues("monitorId_"
							+ (i + 1) + "_" + (j + 1));
					String[] jourCode = request.getParameterValues("jourCode_"
							+ (i + 1) + "_" + (j + 1));
					String[] trvlDtlId = request
							.getParameterValues("trvlDtlId_" + (i + 1) + "_"
									+ (j + 1));
					String[] rejFlag = request.getParameterValues("rejFlag_"
							+ (i + 1) + "_" + (j + 1));
					String[] remarks = request.getParameterValues("remarks_"
							+ (i + 1) + "_" + (j + 1));
					if (srcCity != null && srcCity.length > 0) {
						int order = 1;
						for (int k = 0; k < srcCity.length; k++) {
							saveObj[totCount][0] = insrtMonId;
							saveObj[totCount][1] = groupCode;
							saveObj[totCount][2] = jourCode[k];
							if (!trvlDtlId[k].equals(""))
								saveObj[totCount][3] = trvlDtlId[k];
							else
								saveObj[totCount][3] = maxDtlCode++;
							saveObj[totCount][4] = srcCity[k].trim();
							saveObj[totCount][5] = destCity[k].trim();
							saveObj[totCount][6] = date[k].trim();
							saveObj[totCount][7] = time[k].trim();
							saveObj[totCount][8] = mode[k].trim();
							saveObj[totCount][9] = order++;
							saveObj[totCount][10] = rejFlag[k];
							saveObj[totCount][11] = cost[k];
							saveObj[totCount][12] = remarks[k];
							totCount++;
						}
						groupCode++;
					}
				}
			}

			getSqlModel().singleExecute(
					"DELETE FROM TMS_SUGG_TRAVELLING WHERE MONITOR_ID = "
							+ insrtMonId);
			String insertQry = " INSERT INTO TMS_SUGG_TRAVELLING (MONITOR_ID,TVLNG_GROUP_CODE,TVLNG_JOURN_CODE,TVLNG_DTL_CODE,"
					+ " TVLNG_SOURCE,TVLNG_DESTINATION,TVLNG_DATE,TVLNG_TIME,TVLNG_MODE,TVLNG_ORDER,TVLNG_REJ_FLAG,TVLNG_COST,TVLNG_REMARKS)"
					+ " VALUES(?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?)";
			getSqlModel().singleExecute(insertQry, saveObj);

			try {
				updateCommentsTrail(bean.getUserEmpId(), request
						.getParameter("applicationId"), applCode, request
						.getParameter("userType"), bean.getSchComments(), bean
						.getTvlStatus());
			} catch (Exception e) {
				// TODO: handle exception
			}
			updateQry = " UPDATE TMS_MONITORING SET MTR_TVL_AVA_STATUS = 'SO'"
					+ " WHERE TMS_MONITOR_ID = "
					+ request.getParameter("monitorId");
			getSqlModel().singleExecute(updateQry);

			try {
				String empAppQry = "SELECT APPL_FOR_FLAG FROM TMS_APPLICATION WHERE APPL_ID = "
						+ request.getParameter("applicationId");
				Object[][] appType = getSqlModel().getSingleResult(empAppQry);

				Object[][] tempData = checkMailEvent(58);
				if (tempData == null || tempData.length == 0)
					return;
				if (String.valueOf(tempData[0][0]).equals("Y")) {
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template.setEmailTemplate(String.valueOf(tempData[0][3]));
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);
					templateQuery1.setParameter(1, bean.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);
					templateQuery2.setParameter(1, String.valueOf(bean
							.getIniEmpId()));

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, "Journey");
					templateQuery3.setParameter(2, String.valueOf(bean
							.getApplicationId()));
					templateQuery3.setParameter(3, String.valueOf(bean
							.getEmpApplId()));
					templateQuery3.setParameter(4, "Journey");
					templateQuery3.setParameter(5, String.valueOf(bean
							.getApplicationId()));
					templateQuery3.setParameter(6, String.valueOf(bean
							.getEmpApplId()));
					template.configMailAlert();
					if (!String.valueOf(appType[0][0]).equals("G")
							&& !String.valueOf(bean.getIniEmpId()).equals(
									bean.getEmpId())) {
						template.sendApplicationMailToKeepInfo(String
								.valueOf(bean.getEmpId()));
					}

					template.sendApplicationMail();
					template.clearParameters();
					template.terminate();
				}
			} catch (Exception e) {
				logger
						.error("Error while sending mail after saving Travel Options : "
								+ e);
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void accept_Travel(TravelMonitoring bean, HttpServletRequest request) {
		try {
			String status = "CE";
			if (request.getParameter("policyViolate").equals("Y")) {
				status = "CO";
			}
			String updateQry = " UPDATE TMS_SUGG_TRAVELLING SET TVLNG_SEL_FLAG = 'N' "
					+ " WHERE MONITOR_ID = "
					+ request.getParameter("monitorId");
			getSqlModel().singleExecute(updateQry);

			String dtlId = request.getParameter("dtlId");

			try {
				if (",".equals(dtlId.charAt(dtlId.length() - 1)))
					dtlId = dtlId.substring(0, dtlId.length() - 2);
			} catch (Exception e) {
				// TODO: handle exception
			}
			updateQry = " UPDATE TMS_SUGG_TRAVELLING SET TVLNG_SEL_FLAG = 'Y' "
					+ " WHERE TVLNG_DTL_CODE IN (" + dtlId + ")";
			getSqlModel().singleExecute(updateQry);

			updateQry = " UPDATE TMS_MONITORING SET MTR_TRVL_APP_COMM = '"
					+ bean.getAplComments() + "'," + " MTR_TVL_AVA_STATUS = '"
					+ status + "'" + " WHERE TMS_MONITOR_ID = "
					+ request.getParameter("monitorId");

			getSqlModel().singleExecute(updateQry);

			try {
				updateCommentsTrail(bean.getUserEmpId(), request
						.getParameter("applicationId"), request
						.getParameter("empApplId"), request
						.getParameter("userType"), bean.getAplComments(), bean
						.getTvlStatus());
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				if (!status.equals("CO")) {
					Object[][] tempData = checkMailEvent(60);
					if (tempData == null || tempData.length == 0)
						return;
					if (String.valueOf(tempData[0][0]).equals("Y")) {

						String schEmpCode = " SELECT SCHDTL_SUBSCHLAR_ECODE,DESK_SCH_ECODE FROM TMS_SCH_DTL "
								+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_ID = TMS_SCH_DTL.SCH_DESK_ID) "
								+ " WHERE DESK_APPL_ID = "
								+ request.getParameter("applicationId")
								+ " AND DESK_APPL_CODE = "
								+ request.getParameter("empApplId")
								+ " AND  (TMS_SCH_DESK.DESK_TRAVEL_ASSIGN = 'Y' AND  TMS_SCH_DTL.SCHDTL_TRAVEL_ASSIGN = 'Y') ";
						Object[][] schEmpIdObj = getSqlModel().getSingleResult(
								schEmpCode);

						if (schEmpIdObj == null || schEmpIdObj.length == 0)
							return;

						EmailTemplateBody template = new EmailTemplateBody();
						template.initiate(context, session);
						template.setEmailTemplate(String
								.valueOf(tempData[0][3]));
						template.getTemplateQueries();

						EmailTemplateQuery templateQuery1 = template
								.getTemplateQuery(1);
						templateQuery1.setParameter(1, String.valueOf(bean
								.getIniEmpId()));

						EmailTemplateQuery templateQuery2 = template
								.getTemplateQuery(2);
						templateQuery2.setParameter(1, String
								.valueOf(schEmpIdObj[0][1]));

						EmailTemplateQuery templateQuery3 = template
								.getTemplateQuery(3);
						templateQuery3.setParameter(1, String.valueOf(bean
								.getApplicationId()));
						templateQuery3.setParameter(2, String.valueOf(bean
								.getEmpApplId()));
						templateQuery3.setParameter(3, String.valueOf(bean
								.getApplicationId()));
						templateQuery3.setParameter(4, String.valueOf(bean
								.getEmpApplId()));
						template.configMailAlert();
						if (!String.valueOf(schEmpIdObj[0][0]).equals(
								String.valueOf(schEmpIdObj[0][1]))) {
							template.sendApplicationMailToKeepInfo(String
									.valueOf(schEmpIdObj[0][0]));
						}
						template.sendApplicationMail();
						template.clearParameters();
						template.terminate();
					}
				} else {
					Object[][] tempData = checkMailEvent(70);
					if (tempData == null || tempData.length == 0)
						return;
					if (String.valueOf(tempData[0][0]).equals("Y")) {

						String apprCode = " SELECT APPR_APPROVER_ID FROM TMS_APP_APPROVAL_DTL "
								+ " WHERE APPL_CODE = "
								+ request.getParameter("empApplId")
								+ " AND APPL_ID = "
								+ request.getParameter("applicationId")
								+ " AND APPR_LEVEL = 1";
						Object[][] apprObj = getSqlModel().getSingleResult(
								apprCode);

						EmailTemplateBody template = new EmailTemplateBody();
						template.initiate(context, session);
						template.setEmailTemplate(String
								.valueOf(tempData[0][3]));
						template.getTemplateQueries();

						EmailTemplateQuery templateQuery2 = template
								.getTemplateQuery(2);
						templateQuery2.setParameter(1, String
								.valueOf(apprObj[0][0]));

						EmailTemplateQuery templateQuery3 = template
								.getTemplateQuery(3);
						templateQuery3.setParameter(1, String.valueOf(bean
								.getEmpApplId()));
						templateQuery3.setParameter(2, String.valueOf(bean
								.getApplicationId()));
						templateQuery3.setParameter(3, String.valueOf(bean
								.getEmpApplId()));
						templateQuery3.setParameter(4, String.valueOf(bean
								.getApplicationId()));

						EmailTemplateQuery templateQuery4 = template
								.getTemplateQuery(4);
						templateQuery4.setParameter(1, String.valueOf(bean
								.getEmpApplId()));
						templateQuery4.setParameter(2, String.valueOf(bean
								.getApplicationId()));
						templateQuery4.setParameter(3, String.valueOf(bean
								.getEmpApplId()));
						templateQuery4.setParameter(4, String.valueOf(bean
								.getApplicationId()));

						EmailTemplateQuery templateQuery5 = template
								.getTemplateQuery(5);
						templateQuery5.setParameter(1, String.valueOf(bean
								.getApplicationId()));
						templateQuery5.setParameter(2, String.valueOf(bean
								.getEmpApplId()));

						String applicationType = "TravelMonitorApprove";
						// Add approval link -pass parameters to the link
						String[] link_param = new String[2];
						String[] link_label = new String[2];

						link_param[0] = applicationType + "#" + "T" + "#"
								+ "..." + "#" + bean.getMonitorId() + "#" + "#"
								+ String.valueOf(apprObj[0][0]) + "#"
								+ bean.getApplicationId() + "#"
								+ bean.getEmpApplId() + "#" + "CE";

						link_param[1] = applicationType + "#" + "T" + "#"
								+ "..." + "#" + bean.getMonitorId() + "#" + "#"
								+ String.valueOf(apprObj[0][0]) + "#"
								+ bean.getApplicationId() + "#"
								+ bean.getEmpApplId() + "#" + "SO";

						link_label[0] = "Approve";
						link_label[1] = "Reject";

						template.configMailAlert();
						template.addOnlineLink(request, link_param, link_label);
						template.sendApplicationMail();
						template.clearParameters();
						template.terminate();
					}

				}
			} catch (Exception e) {
				logger
						.error("Error while sending mail after accepting Travel Options : "
								+ e);
				e.printStackTrace();
			}

			try {
				startBookingMail(bean);
			} catch (Exception e) {
				logger.error("Error in start booking mail");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void decline_Travel(TravelMonitoring bean, HttpServletRequest request) {
		try {
			String updateQry = " UPDATE TMS_SUGG_TRAVELLING SET TVLNG_SEL_FLAG = 'N' ";
			boolean flag = false;
			if (!request.getParameter("dtlId").equals("")) {
				updateQry += ",TVLNG_REJ_FLAG = 'Y'";
			}
			updateQry += " WHERE MONITOR_ID = "
					+ request.getParameter("monitorId");
			if (!request.getParameter("dtlId").equals("")) {
				updateQry += " AND TVLNG_DTL_CODE IN ("
						+ request.getParameter("dtlId") + ")";
			}
			getSqlModel().singleExecute(updateQry);
			updateQry = " UPDATE TMS_MONITORING SET MTR_TRVL_APP_COMM = '"
					+ bean.getAplComments() + "' ,MTR_TRVL_APR_COMM = '"
					+ bean.getAprComments() + "',";
			if (!request.getParameter("dtlId").equals("")) {
				updateQry += " MTR_TVL_AVA_STATUS = 'SO'";
			} else {
				updateQry += " MTR_TVL_AVA_STATUS = 'RE'";
				flag = true;
			}

			updateQry += " WHERE TMS_MONITOR_ID = "
					+ request.getParameter("monitorId");
			getSqlModel().singleExecute(updateQry);

			if (flag) {
				try {
					updateCommentsTrail(bean.getUserEmpId(), request
							.getParameter("applicationId"), request
							.getParameter("empApplId"), request
							.getParameter("userType"), bean.getAplComments(),
							bean.getTvlStatus());
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				try {
					updateCommentsTrail(bean.getUserEmpId(), request
							.getParameter("applicationId"), request
							.getParameter("empApplId"), request
							.getParameter("userType"), bean.getAprComments(),
							bean.getTvlStatus());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			if (flag) {
				try {
					Object[][] tempData = checkMailEvent(59);
					if (tempData == null || tempData.length == 0)
						return;
					if (String.valueOf(tempData[0][0]).equals("Y")) {

						String schEmpCode = " SELECT SCHDTL_SUBSCHLAR_ECODE,DESK_SCH_ECODE FROM TMS_SCH_DTL "
								+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_ID = TMS_SCH_DTL.SCH_DESK_ID) "
								+ " WHERE DESK_APPL_ID = "
								+ request.getParameter("applicationId")
								+ " AND DESK_APPL_CODE = "
								+ request.getParameter("empApplId")
								+ " AND  (TMS_SCH_DESK.DESK_LODGE_ASSIGN = 'Y' AND  TMS_SCH_DTL.SCHDTL_LODGE_ASSIGN = 'Y') ";
						Object[][] schEmpIdObj = getSqlModel().getSingleResult(
								schEmpCode);

						if (schEmpIdObj == null || schEmpIdObj.length == 0)
							return;

						EmailTemplateBody template = new EmailTemplateBody();
						template.initiate(context, session);
						template.setEmailTemplate(String
								.valueOf(tempData[0][3]));
						template.getTemplateQueries();

						EmailTemplateQuery templateQuery1 = template
								.getTemplateQuery(1);
						templateQuery1.setParameter(1, String.valueOf(bean
								.getIniEmpId()));

						EmailTemplateQuery templateQuery2 = template
								.getTemplateQuery(2);
						templateQuery2.setParameter(1, String
								.valueOf(schEmpIdObj[0][1]));

						EmailTemplateQuery templateQuery3 = template
								.getTemplateQuery(3);
						templateQuery3.setParameter(1, String.valueOf(bean
								.getApplicationId()));
						templateQuery3.setParameter(2, String.valueOf(bean
								.getEmpApplId()));
						templateQuery3.setParameter(3, String.valueOf(bean
								.getApplicationId()));
						templateQuery3.setParameter(4, String.valueOf(bean
								.getEmpApplId()));
						template.configMailAlert();
						if (!String.valueOf(schEmpIdObj[0][0]).equals(
								String.valueOf(schEmpIdObj[0][1]))) {
							template.sendApplicationMailToKeepInfo(String
									.valueOf(schEmpIdObj[0][0]));
						}
						template.sendApplicationMail();
						template.clearParameters();
						template.terminate();
					}
				} catch (Exception e) {
					logger
							.error("Error while sending mail after accepting accom Options : "
									+ e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LinkedMap getTravelModeClass() {
		LinkedMap hMap = new LinkedMap();
		String modeQry = " SELECT CLASS_ID,JOURNEY_NAME||'-'||CLASS_NAME FROM "
				+ " HRMS_TMS_JOURNEY_MODE "
				+ " INNER JOIN HRMS_TMS_JOURNEY_CLASS ON(HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID=HRMS_TMS_JOURNEY_MODE.JOURNEY_ID)"
				+ " ORDER BY JOURNEY_LEVEL,CLASS_LEVEL";
		Object[][] modeObj = getSqlModel().getSingleResult(modeQry);

		if (modeObj != null && modeObj.length > 0)
			for (int i = 0; i < modeObj.length; i++)
				hMap.put(String.valueOf(modeObj[i][0]), String
						.valueOf(modeObj[i][1]));
		return hMap;
	}

	public String isPolicyViolate(String empId, String classId, String cost) {
		try {
			String classQry = " SELECT CLASS_LEVEL,JOURNEY_NAME||'-'||CLASS_NAME,(SELECT MIN(JOURNEY_LEVEL) FROM HRMS_TMS_JOURNEY_CLASS "
					+ " INNER JOIN HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID = HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID) "
					+ " WHERE CLASS_ID IN("
					+ classId
					+ ")) AS JOURNEY_LEVEL FROM HRMS_TMS_JOURNEY_CLASS "
					+ " INNER JOIN HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID = HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID) "
					+ " WHERE CLASS_LEVEL = (SELECT MIN(CLASS_LEVEL) FROM HRMS_TMS_JOURNEY_CLASS WHERE CLASS_ID IN("
					+ classId + ")) ";
			Object[][] classSelObj = getSqlModel().getSingleResult(classQry);

			if (classSelObj == null || classSelObj.length == 0)
				return "";
			int classSel = Integer.parseInt(String.valueOf(classSelObj[0][0]));
			int jrnLevel = Integer.parseInt(String.valueOf(classSelObj[0][2]));
			String className = String.valueOf(classSelObj[0][1]);
			className = "" + classSelObj[0][1];
			classQry = " SELECT DISTINCT CLASS_LEVEL,JOURNEY_NAME||'-'||CLASS_NAME,JOURNEY_LEVEL,TMS_TRAVEL_POLICY.POLICY_ID FROM TMS_POLICY_GRADE_DTL "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_CADRE = TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID) "
					+ " INNER JOIN TMS_POLICY_MAP_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_ID = TMS_POLICY_MAP_DTL.POLICY_ID) "
					+ " INNER JOIN HRMS_TMS_JOURNEY_CLASS ON(HRMS_TMS_JOURNEY_CLASS.CLASS_ID = TMS_POLICY_MAP_DTL.POLICY_MAP_TRAVEL) "
					+ " INNER JOIN HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID = HRMS_TMS_JOURNEY_MODE.JOURNEY_ID) "
					+ " INNER JOIN TMS_TRAVEL_POLICY ON(TMS_TRAVEL_POLICY.POLICY_ID = TMS_POLICY_MAP_DTL.POLICY_ID)"
					+ " WHERE EMP_ID="
					+ empId
					+ " AND TMS_TRAVEL_POLICY.POLICY_STATUS='A' ORDER BY CLASS_LEVEL";
			classSelObj = getSqlModel().getSingleResult(classQry);

			if (classSelObj == null || classSelObj.length == 0)
				return "";
			className = "Eligible for class travelling is " + classSelObj[0][1]
					+ " and below. You have chosen " + className
					+ ". \n\n Do you want to continue? ";
			if (jrnLevel < Integer.parseInt(String.valueOf(classSelObj[0][2])))
				return "" + className;
			else if (jrnLevel == Integer.parseInt(String
					.valueOf(classSelObj[0][2]))
					&& classSel < Integer.parseInt(String
							.valueOf(classSelObj[0][0])))
				return "" + className;

			String chkCost = " SELECT POLICY_EXP_ENT_AMT FROM TMS_POLICY_EXPENSE_DTL "
					+ " INNER JOIN TMS_POLICY_MAP_DTL ON (TMS_POLICY_MAP_DTL.POLICY_MAP_TRAVEL_MCODE = TMS_POLICY_EXPENSE_DTL.POLICY_EXP_CAT_ID "
					+ " AND TMS_POLICY_EXPENSE_DTL.POLICY_ID = "
					+ classSelObj[0][3]
					+ " AND POLICY_EXP_NO_LIMIT = 'N' "
					+ " AND TMS_POLICY_MAP_DTL.POLICY_ID = TMS_POLICY_EXPENSE_DTL.POLICY_ID) ";
			Object[][] costObj = getSqlModel().getSingleResult(chkCost);

			if (costObj != null && costObj.length > 0)
				if (Integer.parseInt(cost) > Integer.parseInt(String
						.valueOf(costObj[0][0])))
					return " Eligible fare for travelling is " + costObj[0][0]
							+ " and you have chosen the option having fare "
							+ cost + ".\n\n Do you want to continue?";
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String getStatus(String status, String[][] displayName) {
		status = status.trim();
		if (status.equals("Y"))
			return "Pending for " + displayName[0][0];
		else if (status.equals("CO") || status.equals("PA"))
			return "Pending for " + displayName[0][2];
		else if (status.equals("SO") || status.equals("AC"))
			return "Pending for " + displayName[0][1];
		else if (status.equals("DE"))
			return "Declined";
		else if (status.equals("N"))
			return "Not Required";
		else if (status.equals("R"))
			return "Rejected";
		else if (status.equals("FI"))
			return "Booked";
		else if (status.equals("RE"))
			return "RE : Pending for " + displayName[0][0];
		else if (status.equals("H"))
			return "Hold";
		else if (status.equals("PC") || status.equals("FC"))
			return "Pending for Cancellation";
		else if (status.equals("CC"))
			return "Cancel";
		else if (status.equals("CD"))
			return "Cancelled";
		else
			return "Confirmed";
	}

	public boolean updateStatus(HttpServletRequest request, String status) {
		String monitorId = request.getParameter("monitorId");
		String getStatusQry = "SELECT MTR_TVL_AVA_STATUS, MTR_ACC_AVSTATUS, MTR_LOCAL_AVSTATUS FROM TMS_MONITORING WHERE TMS_MONITOR_ID = "
				+ monitorId;
		Object[][] statusObj = getSqlModel().getSingleResult(getStatusQry);
		if (statusObj != null && statusObj.length > 0) {
			for (int i = 0; i < statusObj[0].length; i++)
				if (!(String.valueOf(statusObj[0][i]).trim().equals("N") || String
						.valueOf(statusObj[0][i]).trim().equals("CN")))
					statusObj[0][i] = status;
		} else {
			statusObj = new Object[1][3];
			for (int i = 0; i < statusObj[0].length; i++)
				statusObj[0][i] = status;
		}
		return getSqlModel().singleExecute(
				" UPDATE TMS_MONITORING SET MTR_TVL_AVA_STATUS = " + "'"
						+ statusObj[0][0] + "'" + " MTR_ACC_AVSTATUS = '"
						+ statusObj[0][1] + "', MTR_LOCAL_AVSTATUS='"
						+ statusObj[0][2] + "' WHERE TMS_MONITOR_ID = "
						+ monitorId);
	}

	public boolean approve(String type, String monitorId,
			TravelMonitoring bean, HttpServletRequest request) {
		String updateQry = "UPDATE TMS_MONITORING SET";
		String status = "";
		if (type.equals("T")) {
			status = bean.getTvlStatus();
			updateQry += " MTR_TVL_AVA_STATUS = 'CE', MTR_TRVL_APR_COMM = '"
					+ bean.getAprComments() + "'";
		} else {
			status = bean.getAccStatus();
			updateQry += " MTR_ACC_AVSTATUS = 'CE', MTR_ACCOM_APR_COMM = '"
					+ bean.getAprComments() + "'";
		}
		updateQry += " WHERE TMS_MONITOR_ID = " + monitorId;
		getSqlModel().singleExecute(updateQry);

		try {
			updateCommentsTrail(bean.getUserEmpId(), request
					.getParameter("applicationId"), request
					.getParameter("empApplId"), request
					.getParameter("userType"), bean.getAprComments(), status);
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			startBookingMail(bean);
		} catch (Exception e) {
		}

		return true;

	}

	public boolean onlineApproveReject(HttpServletRequest request, String type,
			String comments, String monitorId, String userEmpId, String applId,
			String applCode, String status) {
		String updateQry = "UPDATE TMS_MONITORING SET";
		if (type.equals("T")) {
			updateQry += " MTR_TVL_AVA_STATUS = '" + status
					+ "', MTR_TRVL_APR_COMM = '" + comments + "'";
		} else {
			updateQry += " MTR_ACC_AVSTATUS = '" + status
					+ "', MTR_ACCOM_APR_COMM = '" + comments + "'";
		}
		updateQry += " WHERE TMS_MONITOR_ID = " + monitorId;
		getSqlModel().singleExecute(updateQry);

		try {
			updateCommentsTrail(userEmpId, applId, applCode, "APPR", comments,
					status);
		} catch (Exception e) {
			// TODO: handle exception
		}
		TravelMonitoring bean = new TravelMonitoring();
		bean.setApplicationId(Integer.parseInt(applId));
		bean.setEmpApplId(Integer.parseInt(applCode));
		try {
			startBookingMail(bean);
		} catch (Exception e) {
			logger.error("Error in start booking mail");
		}

		return true;

	}

	public void addRowToOption_Accom(TravelMonitoring monitorBean,
			HttpServletRequest request) {
		String pathName = request.getParameter("pathName");
		int maxMainCount = Integer.parseInt(request
				.getParameter("maxMainCount"));
		int maxOptCount = Integer.parseInt(request.getParameter("maxOptCount"));
		String userType = request.getParameter("userType");
		LinkedMap hMap = getTravelModeClass();
		boolean readOnly = Boolean
				.valueOf(request.getParameter("readOnlyFlag"));
		monitorBean.setUserType(userType);
		monitorBean.setReadOnlyFlag(readOnly);
		String[] temp = pathName.split("_");
		int mainCount = Integer.parseInt(temp[0]);
		int optCount = Integer.parseInt(temp[1]);
		ArrayList jourList = new ArrayList();
		for (int i = 0; i < maxMainCount; i++) {
			TravelMonitoring journBean = new TravelMonitoring();
			ArrayList optList = new ArrayList();
			journBean.setFromCity(request.getParameter("fromCity_" + (i + 1)));
			journBean.setToCity(request.getParameter("toCity_" + (i + 1)));
			journBean.setUserType(userType);
			journBean.setReadOnlyFlag(Boolean.valueOf(request
					.getParameter("readOnlyFlag" + (i + 1))));
			for (int j = 0; j < maxOptCount; j++) {
				TravelMonitoring optBean = new TravelMonitoring();
				optBean.setUserType(userType);
				optBean.setReadOnlyFlag(readOnly);
				ArrayList pathList = new ArrayList();
				String[][] dataObj = getDataFromView_Accom(request, i, j);
				if (dataObj == null || dataObj.length <= 0)
					break;
				optBean.setLodgCancelFlag(request.getParameter("lodgCancelFlag"
						+ (i + 1) + "_" + (j + 1)));
				for (int k = 0; k < dataObj.length + 1; k++) {
					TravelMonitoring pathBean = new TravelMonitoring();
					if (k < dataObj.length) {
						pathBean.setLodgeCode(String.valueOf(dataObj[k][0]));
						pathBean.setSource(String.valueOf(dataObj[k][1]));
						pathBean
								.setAccomFromDate(String.valueOf(dataObj[k][2]));
						pathBean.setAccomToDate(String.valueOf(dataObj[k][3]));
						pathBean
								.setAccomFromTime(String.valueOf(dataObj[k][4]));
						pathBean.setAccomToTime(String.valueOf(dataObj[k][5]));
						pathBean
								.setHotelTypeCode(String.valueOf(dataObj[k][6]));
						pathBean
								.setHotelTypeName(String.valueOf(dataObj[k][7]));
						pathBean.setRoomTypeCode(String.valueOf(dataObj[k][8]));
						pathBean.setRoomTypeName(String.valueOf(dataObj[k][9]));
						pathBean
								.setAccomAddress(String.valueOf(dataObj[k][10]));
						pathBean.setSelFlag(String.valueOf(dataObj[k][11]));
						pathBean.setAccomBookingAmt(String
								.valueOf(dataObj[k][12]));
						pathBean.setLodgCancelFlag(optBean.getLodgCancelFlag());
						pathBean.setReadOnlyFlag(readOnly);
						pathBean.setUserType(userType);
						pathList.add(pathBean);
					} else if ((i + 1) == mainCount && (j + 1) == optCount) {
						pathBean.setModeClassMap(hMap);
						pathBean.setReadOnlyFlag(readOnly);
						pathBean.setUserType(userType);
						pathBean.setSource(String.valueOf(dataObj[0][1]));
						pathBean
								.setAccomFromDate(String.valueOf(dataObj[0][2]));
						pathBean.setAccomToDate(String.valueOf(dataObj[0][3]));
						pathBean
								.setAccomFromTime(String.valueOf(dataObj[0][4]));
						pathBean.setAccomToTime(String.valueOf(dataObj[0][5]));
						pathBean
								.setHotelTypeCode(String.valueOf(dataObj[0][6]));
						pathBean
								.setHotelTypeName(String.valueOf(dataObj[0][7]));
						pathBean.setRoomTypeCode(String.valueOf(dataObj[0][8]));
						pathBean.setRoomTypeName(String.valueOf(dataObj[0][9]));
						pathBean
								.setAccomAddress(String.valueOf(dataObj[0][10]));
						pathList.add(pathBean);
					}
				}
				optList.add(optBean);
				optBean.setPath(pathList);

			}
			jourList.add(journBean);
			journBean.setOptions(optList);
		}
		monitorBean.setMain(jourList);
	}

	public void addOption_Accom(TravelMonitoring monitorBean,
			HttpServletRequest request) {
		String pathName = request.getParameter("pathName");
		int maxMainCount = Integer.parseInt(request
				.getParameter("maxMainCount"));
		int maxOptCount = Integer.parseInt(request.getParameter("maxOptCount"));
		String userType = request.getParameter("userType");
		boolean readOnly = Boolean
				.valueOf(request.getParameter("readOnlyFlag"));
		LinkedMap hMap = getTravelModeClass();
		monitorBean.setUserType(userType);
		monitorBean.setReadOnlyFlag(readOnly);
		String[] temp = pathName.split("_");
		int mainCount = Integer.parseInt(temp[0]);
		ArrayList jourList = new ArrayList();
		String[][] dataObj = null;
		String[][] cloneObj = null;
		for (int i = 0; i < maxMainCount; i++) {
			TravelMonitoring journBean = new TravelMonitoring();
			ArrayList optList = new ArrayList();
			journBean.setFromCity(request.getParameter("fromCity_" + (i + 1)));
			journBean.setToCity(request.getParameter("toCity_" + (i + 1)));
			journBean.setUserType(userType);
			journBean.setReadOnlyFlag(Boolean.valueOf(request
					.getParameter("readOnlyFlag" + (i + 1))));
			for (int j = 0; j < maxOptCount; j++) {
				TravelMonitoring optBean = new TravelMonitoring();
				optBean.setUserType(userType);
				optBean.setReadOnlyFlag(readOnly);
				ArrayList pathList = new ArrayList();

				dataObj = getDataFromView_Accom(request, i, j);
				if (dataObj == null || dataObj.length <= 0)
					break;
				optBean.setLodgCancelFlag(request.getParameter("lodgCancelFlag"
						+ (i + 1) + "_" + (j + 1)));
				cloneObj = dataObj.clone();
				for (int k = 0; k < dataObj.length + 1; k++) {
					TravelMonitoring pathBean = new TravelMonitoring();
					if (k < dataObj.length) {
						pathBean.setLodgeCode(String.valueOf(dataObj[k][0]));
						pathBean.setSource(String.valueOf(dataObj[k][1]));
						pathBean
								.setAccomFromDate(String.valueOf(dataObj[k][2]));
						pathBean.setAccomToDate(String.valueOf(dataObj[k][3]));
						pathBean
								.setAccomFromTime(String.valueOf(dataObj[k][4]));
						pathBean.setAccomToTime(String.valueOf(dataObj[k][5]));
						pathBean
								.setHotelTypeCode(String.valueOf(dataObj[k][6]));
						pathBean
								.setHotelTypeName(String.valueOf(dataObj[k][7]));
						pathBean.setRoomTypeCode(String.valueOf(dataObj[k][8]));
						pathBean.setRoomTypeName(String.valueOf(dataObj[k][9]));
						pathBean
								.setAccomAddress(String.valueOf(dataObj[k][10]));
						pathBean.setSelFlag(String.valueOf(dataObj[k][11]));
						pathBean.setAccomBookingAmt(String
								.valueOf(dataObj[k][12]));
						pathBean.setReadOnlyFlag(readOnly);
						pathBean.setLodgCancelFlag(optBean.getLodgCancelFlag());
						pathBean.setUserType(userType);
						pathList.add(pathBean);
					}
				}
				optBean.setSrNo(1);
				optList.add(optBean);
				optBean.setPath(pathList);

			}
			if ((i + 1) == mainCount) {
				TravelMonitoring pathBean = new TravelMonitoring();
				pathBean.setReadOnlyFlag(readOnly);
				pathBean.setUserType(userType);
				pathBean.setModeClassMap(hMap);
				if (cloneObj != null && cloneObj.length > 0) {
					pathBean.setSource(String.valueOf(cloneObj[0][1]));
					pathBean.setAccomFromDate(String.valueOf(cloneObj[0][2]));
					pathBean.setAccomToDate(String.valueOf(cloneObj[0][3]));
					pathBean.setAccomFromTime(String.valueOf(cloneObj[0][4]));
					pathBean.setAccomToTime(String.valueOf(cloneObj[0][5]));
					pathBean.setHotelTypeCode(String.valueOf(cloneObj[0][6]));
					pathBean.setHotelTypeName(String.valueOf(cloneObj[0][7]));
					pathBean.setRoomTypeCode(String.valueOf(cloneObj[0][8]));
					pathBean.setRoomTypeName(String.valueOf(cloneObj[0][9]));
					pathBean.setAccomAddress(String.valueOf(cloneObj[0][10]));
				}
				TravelMonitoring optBean = new TravelMonitoring();
				optBean.setUserType(userType);
				ArrayList pathList = new ArrayList();
				pathList.add(pathBean);
				optList.add(optBean);
				optBean.setPath(pathList);
			}
			journBean.setSrNo(1);
			jourList.add(journBean);
			journBean.setOptions(optList);
		}
		monitorBean.setMain(jourList);
	}

	public void removeOption_Accom(TravelMonitoring bean,
			HttpServletRequest request) {
		String pathName = request.getParameter("pathName");
		int maxMainCount = Integer.parseInt(request
				.getParameter("maxMainCount"));
		int maxOptCount = Integer.parseInt(request.getParameter("maxOptCount"));
		String[] temp = pathName.split("_");
		String userType = request.getParameter("userType");
		boolean readOnly = Boolean
				.valueOf(request.getParameter("readOnlyFlag"));
		LinkedMap hMap = getTravelModeClass();
		bean.setUserType(userType);
		bean.setReadOnlyFlag(readOnly);
		int mainCount = Integer.parseInt(temp[0]);
		int optCount = Integer.parseInt(temp[1]);
		ArrayList jourList = new ArrayList();
		for (int i = 0; i < maxMainCount; i++) {
			TravelMonitoring journBean = new TravelMonitoring();
			ArrayList optList = new ArrayList();
			journBean.setFromCity(request.getParameter("fromCity_" + (i + 1)));
			journBean.setToCity(request.getParameter("toCity_" + (i + 1)));
			journBean.setUserType(userType);
			journBean.setReadOnlyFlag(Boolean.valueOf(request
					.getParameter("readOnlyFlag" + (i + 1))));
			for (int j = 0; j < maxOptCount; j++) {
				if ((i + 1) == mainCount && (j + 1) == optCount)
					continue;
				TravelMonitoring optBean = new TravelMonitoring();
				optBean.setUserType(userType);
				optBean.setReadOnlyFlag(readOnly);
				ArrayList pathList = new ArrayList();
				String[][] dataObj = getDataFromView_Accom(request, i, j);
				if (dataObj == null || dataObj.length <= 0)
					break;
				optBean.setLodgCancelFlag(request.getParameter("lodgCancelFlag"
						+ (i + 1) + "_" + (j + 1)));
				for (int k = 0; k < dataObj.length + 1; k++) {
					TravelMonitoring pathBean = new TravelMonitoring();
					if (k < dataObj.length) {
						pathBean.setLodgeCode(String.valueOf(dataObj[k][0]));
						pathBean.setSource(String.valueOf(dataObj[k][1]));
						pathBean
								.setAccomFromDate(String.valueOf(dataObj[k][2]));
						pathBean.setAccomToDate(String.valueOf(dataObj[k][3]));
						pathBean
								.setAccomFromTime(String.valueOf(dataObj[k][4]));
						pathBean.setAccomToTime(String.valueOf(dataObj[k][5]));
						pathBean
								.setHotelTypeCode(String.valueOf(dataObj[k][6]));
						pathBean
								.setHotelTypeName(String.valueOf(dataObj[k][7]));
						pathBean.setRoomTypeCode(String.valueOf(dataObj[k][8]));
						pathBean.setRoomTypeName(String.valueOf(dataObj[k][9]));
						pathBean
								.setAccomAddress(String.valueOf(dataObj[k][10]));
						pathBean.setSelFlag(String.valueOf(dataObj[k][11]));
						pathBean.setAccomBookingAmt(String
								.valueOf(dataObj[k][12]));
						pathBean.setLodgCancelFlag(optBean.getLodgCancelFlag());
						pathBean.setReadOnlyFlag(readOnly);
						pathBean.setUserType(userType);
						pathList.add(pathBean);
					}
				}
				optList.add(optBean);
				optBean.setPath(pathList);

			}
			jourList.add(journBean);
			journBean.setOptions(optList);
		}
		bean.setMain(jourList);
	}

	public void removeOptionFromRow_Accom(TravelMonitoring bean,
			HttpServletRequest request) {
		String pathName = request.getParameter("pathName");
		int maxMainCount = Integer.parseInt(request
				.getParameter("maxMainCount"));
		int maxOptCount = Integer.parseInt(request.getParameter("maxOptCount"));
		String userType = request.getParameter("userType");
		boolean readOnly = Boolean
				.valueOf(request.getParameter("readOnlyFlag"));
		LinkedMap hMap = getTravelModeClass();
		bean.setUserType(userType);
		bean.setReadOnlyFlag(readOnly);
		String[] temp = pathName.split("_");
		int mainCount = Integer.parseInt(temp[0]);
		int optCount = Integer.parseInt(temp[1]);
		int rowCount = Integer.parseInt(temp[2]);
		ArrayList jourList = new ArrayList();
		for (int i = 0; i < maxMainCount; i++) {
			TravelMonitoring journBean = new TravelMonitoring();
			ArrayList optList = new ArrayList();
			journBean.setFromCity(request.getParameter("fromCity_" + (i + 1)));
			journBean.setToCity(request.getParameter("toCity_" + (i + 1)));
			journBean.setUserType(userType);
			journBean.setReadOnlyFlag(Boolean.valueOf(request
					.getParameter("readOnlyFlag" + (i + 1))));
			for (int j = 0; j < maxOptCount; j++) {
				TravelMonitoring optBean = new TravelMonitoring();
				optBean.setUserType(userType);
				optBean.setReadOnlyFlag(readOnly);
				ArrayList pathList = new ArrayList();
				String[][] dataObj = getDataFromView_Accom(request, i, j);
				if (dataObj == null || dataObj.length <= 0)
					break;
				optBean.setLodgCancelFlag(request.getParameter("lodgCancelFlag"
						+ (i + 1) + "_" + (j + 1)));
				boolean oneRowFlag = false;
				for (int k = 0; k < dataObj.length; k++) {
					if ((i + 1) == mainCount && (j + 1) == optCount
							&& (k + 1) == rowCount) {
						if (dataObj.length == 1)
							oneRowFlag = true;
						continue;
					}
					TravelMonitoring pathBean = new TravelMonitoring();
					pathBean.setLodgeCode(String.valueOf(dataObj[k][0]));
					pathBean.setSource(String.valueOf(dataObj[k][1]));
					pathBean.setAccomFromDate(String.valueOf(dataObj[k][2]));
					pathBean.setAccomToDate(String.valueOf(dataObj[k][3]));
					pathBean.setAccomFromTime(String.valueOf(dataObj[k][4]));
					pathBean.setAccomToTime(String.valueOf(dataObj[k][5]));
					pathBean.setHotelTypeCode(String.valueOf(dataObj[k][6]));
					pathBean.setHotelTypeName(String.valueOf(dataObj[k][7]));
					pathBean.setRoomTypeCode(String.valueOf(dataObj[k][8]));
					pathBean.setRoomTypeName(String.valueOf(dataObj[k][9]));
					pathBean.setAccomAddress(String.valueOf(dataObj[k][10]));
					pathBean.setSelFlag(String.valueOf(dataObj[k][11]));
					pathBean.setAccomBookingAmt(String.valueOf(dataObj[k][12]));
					pathBean.setReadOnlyFlag(readOnly);
					pathBean.setLodgCancelFlag(optBean.getLodgCancelFlag());
					pathBean.setUserType(userType);
					pathList.add(pathBean);
				}
				if (!oneRowFlag) {
					optList.add(optBean);
					optBean.setPath(pathList);
				}
			}
			jourList.add(journBean);
			journBean.setOptions(optList);
		}
		bean.setMain(jourList);
	}

	public String[][] getDataFromView_Accom(HttpServletRequest request, int i,
			int j) {
		String[] city = request.getParameterValues("source_" + (i + 1) + "_"
				+ (j + 1));
		String[] lodgeCode = request.getParameterValues("lodgeCode_" + (i + 1)
				+ "_" + (j + 1));
		String[] fromDate = request.getParameterValues("accomFromDate_"
				+ (i + 1) + "_" + (j + 1));
		String[] fromTime = request.getParameterValues("accomFromTime_"
				+ (i + 1) + "_" + (j + 1));
		String[] toDate = request.getParameterValues("accomToDate_" + (i + 1)
				+ "_" + (j + 1));
		String[] toTime = request.getParameterValues("accomToTime_" + (i + 1)
				+ "_" + (j + 1));
		String[] hotelCode = request.getParameterValues("hotelTypeCode_"
				+ (i + 1) + "_" + (j + 1));
		String[] hotelName = request.getParameterValues("hotelTypeName_"
				+ (i + 1) + "_" + (j + 1));
		String[] roomCode = request.getParameterValues("roomTypeCode_"
				+ (i + 1) + "_" + (j + 1));
		String[] roomName = request.getParameterValues("roomTypeName_"
				+ (i + 1) + "_" + (j + 1));
		String[] address = request.getParameterValues("accomAddress_" + (i + 1)
				+ "_" + (j + 1));
		String[] lodgeDtlId = request.getParameterValues("lodgeDtlId_"
				+ (i + 1) + "_" + (j + 1));
		String[] selFlag = request.getParameterValues("selFlag_" + (i + 1)
				+ "_" + (j + 1));
		String[] amount = request.getParameterValues("accomBookingAmt_"
				+ (i + 1) + "_" + (j + 1));
		String[][] dataObj = null;
		if (city != null && city.length > 0) {
			dataObj = new String[city.length][14];
			for (int k = 0; k < dataObj.length; k++) {
				dataObj[k][0] = lodgeCode[k];
				dataObj[k][1] = city[k];
				dataObj[k][2] = fromDate[k];
				dataObj[k][3] = toDate[k];
				dataObj[k][4] = fromTime[k];
				dataObj[k][5] = toTime[k];
				dataObj[k][6] = hotelCode[k];
				dataObj[k][7] = hotelName[k];
				dataObj[k][8] = roomCode[k];
				dataObj[k][9] = roomName[k];
				dataObj[k][10] = address[k];
				dataObj[k][11] = lodgeDtlId[k];
				dataObj[k][12] = selFlag[k];
				dataObj[k][12] = amount[k];
			}
		}
		return dataObj;
	}

	public void save_Accom(TravelMonitoring bean, HttpServletRequest request) {
		try {
			String applId = request.getParameter("empApplId");
			int totCount = 0;
			String[] monitorId = null;
			String insrtMonId = request.getParameter("monitorId");
			String updateQry = " UPDATE TMS_MONITORING SET MTR_ACCOM_SCH_COMM = '"
					+ bean.getSchComments()
					+ "', MTR_ACCOM_APP_COMM = '"
					+ bean.getAplComments()
					+ "'"
					+ " WHERE TMS_MONITOR_ID = "
					+ insrtMonId;
			getSqlModel().singleExecute(updateQry);
			if (request.getParameter("userType").equals("APL")
					|| request.getParameter("userType").equals("APR"))
				return;
			Object[][] saveObj = new Object[Integer.parseInt(request
					.getParameter("totCount"))][14];
			String maxDtlCodeQry = "SELECT NVL(MAX(ACCOM_LODGEDTL_CODE),0)+1 FROM TMS_SUGG_ACCOM";
			String[] opCount = request.getParameterValues("radioHidden");
			Object[][] maxCodeObj = getSqlModel()
					.getSingleResult(maxDtlCodeQry);
			int maxDtlCode = Integer.parseInt(String.valueOf(maxCodeObj[0][0]));
			int lodgeCount = Integer.parseInt(request
					.getParameter("lodgeCount"));
			for (int i = 0; i < lodgeCount; i++) {
				int optionCount = Integer.parseInt(opCount[i]);
				int groupCode = 1;
				for (int j = 0; j < optionCount; j++) {
					String[] city = request.getParameterValues("source_"
							+ (i + 1) + "_" + (j + 1));
					String[] fromDate = request
							.getParameterValues("accomFromDate_" + (i + 1)
									+ "_" + (j + 1));
					String[] fromTime = request
							.getParameterValues("accomFromTime_" + (i + 1)
									+ "_" + (j + 1));
					String[] toDate = request.getParameterValues("accomToDate_"
							+ (i + 1) + "_" + (j + 1));
					String[] toTime = request.getParameterValues("accomToTime_"
							+ (i + 1) + "_" + (j + 1));
					String[] hotelCode = request
							.getParameterValues("hotelTypeCode_" + (i + 1)
									+ "_" + (j + 1));
					String[] roomCode = request
							.getParameterValues("roomTypeCode_" + (i + 1) + "_"
									+ (j + 1));
					String[] address = request
							.getParameterValues("accomAddress_" + (i + 1) + "_"
									+ (j + 1));
					String[] lodgeDtlId = request
							.getParameterValues("lodgeDtlId_" + (i + 1) + "_"
									+ (j + 1));
					String[] rejFlag = request.getParameterValues("rejFlag_"
							+ (i + 1) + "_" + (j + 1));
					String[] lodgeCode = request
							.getParameterValues("lodgeCode_" + (i + 1) + "_"
									+ (j + 1));
					if (city != null && city.length > 0) {
						int order = 1;
						for (int k = 0; k < city.length; k++) {
							saveObj[totCount][0] = insrtMonId;
							saveObj[totCount][1] = groupCode;
							saveObj[totCount][2] = lodgeCode[k];
							if (!lodgeDtlId[k].equals(""))
								saveObj[totCount][3] = lodgeDtlId[k];
							else
								saveObj[totCount][3] = maxDtlCode++;
							saveObj[totCount][4] = city[k].trim();
							saveObj[totCount][5] = fromDate[k].trim();
							saveObj[totCount][6] = fromTime[k].trim();
							saveObj[totCount][7] = toDate[k].trim();
							saveObj[totCount][8] = toTime[k].trim();
							saveObj[totCount][9] = hotelCode[k].trim();
							saveObj[totCount][10] = roomCode[k].trim();
							saveObj[totCount][11] = address[k].trim();
							saveObj[totCount][12] = order++;
							saveObj[totCount][13] = rejFlag[k];
							totCount++;
						}
						groupCode++;
					}
				}
			}

			getSqlModel().singleExecute(
					"DELETE FROM TMS_SUGG_ACCOM WHERE MONITOR_ID = "
							+ insrtMonId);
			String insertQry = " INSERT INTO TMS_SUGG_ACCOM(MONITOR_ID,ACCOM_GROUP_CODE,ACCOM_LODGE_CODE,ACCOM_LODGEDTL_CODE,"
					+ " ACCOM_CITY,ACCOM_FROMDATE,ACCOM_CHECKIN,ACCOM_TODATE,ACCOM_CHECKOUT,ACCOM_HOTELTYPE,ACCOM_ROOMTYPE,"
					+ " ACCOM_ADDRESS,ACCOM_ORDER,ACCOM_REJ_FLAG)"
					+ " VALUES(?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?)";
			getSqlModel().singleExecute(insertQry, saveObj);

			updateQry = " UPDATE TMS_MONITORING SET MTR_ACC_AVSTATUS = 'SO'"
					+ " WHERE TMS_MONITOR_ID = "
					+ request.getParameter("monitorId");

			getSqlModel().singleExecute(updateQry);

			try {
				updateCommentsTrail(bean.getUserEmpId(), request
						.getParameter("applicationId"), request
						.getParameter("empApplId"), request
						.getParameter("userType"), bean.getSchComments(), bean
						.getAccStatus());
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				String empAppQry = "SELECT APPL_FOR_FLAG FROM TMS_APPLICATION WHERE APPL_ID = "
						+ request.getParameter("applicationId");
				Object[][] appType = getSqlModel().getSingleResult(empAppQry);

				Object[][] tempData = checkMailEvent(58);
				if (tempData == null || tempData.length == 0)
					return;
				if (String.valueOf(tempData[0][0]).equals("Y")) {
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template.setEmailTemplate(String.valueOf(tempData[0][3]));
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);
					templateQuery1.setParameter(1, bean.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);
					templateQuery2.setParameter(1, String.valueOf(bean
							.getIniEmpId()));

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, "Accommodation");
					templateQuery3.setParameter(2, String.valueOf(bean
							.getApplicationId()));
					templateQuery3.setParameter(3, String.valueOf(bean
							.getEmpApplId()));
					templateQuery3.setParameter(4, "Accommodation");
					templateQuery3.setParameter(5, String.valueOf(bean
							.getApplicationId()));
					templateQuery3.setParameter(6, String.valueOf(bean
							.getEmpApplId()));
					template.configMailAlert();
					if (!String.valueOf(appType[0][0]).equals("G")
							&& !String.valueOf(bean.getIniEmpId()).equals(
									bean.getEmpId())) {
						template.sendApplicationMailToKeepInfo(String
								.valueOf(bean.getEmpId()));
					}

					template.sendApplicationMail();
					template.clearParameters();
					template.terminate();
				}
			} catch (Exception e) {
				logger
						.error("Error while sending mail after saving Accom Options : "
								+ e);
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void accept_Accom(TravelMonitoring bean, HttpServletRequest request) {
		try {
			String status = "CE";
			if (request.getParameter("policyViolate").equals("Y")) {
				status = "CO";
			}
			String updateQry = " UPDATE TMS_SUGG_ACCOM SET ACCOM_SEL_FLAG = 'N' "
					+ " WHERE MONITOR_ID = "
					+ request.getParameter("monitorId");
			getSqlModel().singleExecute(updateQry);

			updateQry = " UPDATE TMS_SUGG_ACCOM SET ACCOM_SEL_FLAG = 'Y' "
					+ " WHERE ACCOM_LODGEDTL_CODE IN ("
					+ request.getParameter("dtlId") + ")";
			getSqlModel().singleExecute(updateQry);

			updateQry = " UPDATE TMS_MONITORING SET MTR_ACCOM_APP_COMM = '"
					+ bean.getAplComments() + "'," + " MTR_ACC_AVSTATUS = '"
					+ status + "'" + " WHERE TMS_MONITOR_ID = "
					+ request.getParameter("monitorId");

			getSqlModel().singleExecute(updateQry);

			try {
				updateCommentsTrail(bean.getUserEmpId(), request
						.getParameter("applicationId"), request
						.getParameter("empApplId"), request
						.getParameter("userType"), bean.getAplComments(), bean
						.getAccStatus());
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				if (!status.equals("CO")) {
					Object[][] tempData = checkMailEvent(60);
					if (tempData == null || tempData.length == 0)
						return;
					if (String.valueOf(tempData[0][0]).equals("Y")) {

						String schEmpCode = " SELECT SCHDTL_SUBSCHLAR_ECODE,DESK_SCH_ECODE FROM TMS_SCH_DTL "
								+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_ID = TMS_SCH_DTL.SCH_DESK_ID) "
								+ " WHERE DESK_APPL_ID = "
								+ request.getParameter("applicationId")
								+ " AND DESK_APPL_CODE = "
								+ request.getParameter("empApplId")
								+ " AND  (TMS_SCH_DESK.DESK_LODGE_ASSIGN = 'Y' AND  TMS_SCH_DTL.SCHDTL_LODGE_ASSIGN = 'Y') ";
						Object[][] schEmpIdObj = getSqlModel().getSingleResult(
								schEmpCode);

						if (schEmpIdObj == null || schEmpIdObj.length == 0)
							return;

						EmailTemplateBody template = new EmailTemplateBody();
						template.initiate(context, session);
						template.setEmailTemplate(String
								.valueOf(tempData[0][3]));
						template.getTemplateQueries();

						EmailTemplateQuery templateQuery1 = template
								.getTemplateQuery(1);
						templateQuery1.setParameter(1, String.valueOf(bean
								.getIniEmpId()));

						EmailTemplateQuery templateQuery2 = template
								.getTemplateQuery(2);
						templateQuery2.setParameter(1, String
								.valueOf(schEmpIdObj[0][1]));

						EmailTemplateQuery templateQuery3 = template
								.getTemplateQuery(3);
						templateQuery3.setParameter(1, String.valueOf(bean
								.getApplicationId()));
						templateQuery3.setParameter(2, String.valueOf(bean
								.getEmpApplId()));
						templateQuery3.setParameter(3, String.valueOf(bean
								.getApplicationId()));
						templateQuery3.setParameter(4, String.valueOf(bean
								.getEmpApplId()));

						template.configMailAlert();
						if (!String.valueOf(schEmpIdObj[0][0]).equals(
								String.valueOf(schEmpIdObj[0][1]))) {
							template.sendApplicationMailToKeepInfo(String
									.valueOf(schEmpIdObj[0][0]));
						}
						template.sendApplicationMail();
						template.clearParameters();
						template.terminate();

					}
				} else {
					Object[][] tempData = checkMailEvent(72);
					if (tempData == null || tempData.length == 0)
						return;
					if (String.valueOf(tempData[0][0]).equals("Y")) {

						String apprCode = " SELECT APPR_APPROVER_ID FROM TMS_APP_APPROVAL_DTL "
								+ " WHERE APPL_CODE = "
								+ request.getParameter("empApplId")
								+ " AND APPL_ID = "
								+ request.getParameter("applicationId")
								+ " AND APPR_LEVEL = 1";
						Object[][] apprObj = getSqlModel().getSingleResult(
								apprCode);

						if (apprObj == null || apprObj.length == 0)
							return;

						EmailTemplateBody template = new EmailTemplateBody();
						template.initiate(context, session);
						template.setEmailTemplate(String
								.valueOf(tempData[0][3]));
						template.getTemplateQueries();

						EmailTemplateQuery templateQuery2 = template
								.getTemplateQuery(2);
						templateQuery2.setParameter(1, String
								.valueOf(apprObj[0][0]));

						EmailTemplateQuery templateQuery3 = template
								.getTemplateQuery(3);
						templateQuery3.setParameter(1, String.valueOf(bean
								.getEmpApplId()));
						templateQuery3.setParameter(2, String.valueOf(bean
								.getApplicationId()));
						templateQuery3.setParameter(3, String.valueOf(bean
								.getEmpApplId()));
						templateQuery3.setParameter(4, String.valueOf(bean
								.getApplicationId()));

						EmailTemplateQuery templateQuery4 = template
								.getTemplateQuery(4);
						templateQuery4.setParameter(1, String.valueOf(bean
								.getEmpApplId()));
						templateQuery4.setParameter(2, String.valueOf(bean
								.getApplicationId()));
						templateQuery4.setParameter(3, String.valueOf(bean
								.getEmpApplId()));
						templateQuery4.setParameter(4, String.valueOf(bean
								.getApplicationId()));

						template.configMailAlert();

						String applicationType = "TravelMonitorApprove";
						// Add approval link -pass parameters to the link
						String[] link_param = new String[2];
						String[] link_label = new String[2];

						link_param[0] = applicationType + "#" + "A" + "#"
								+ "..." + "#" + bean.getMonitorId() + "#" + "#"
								+ String.valueOf(apprObj[0][0]) + "#"
								+ bean.getApplicationId() + "#"
								+ bean.getEmpApplId() + "#" + "CE";

						link_param[1] = applicationType + "#" + "A" + "#"
								+ "..." + "#" + bean.getMonitorId() + "#" + "#"
								+ String.valueOf(apprObj[0][0]) + "#"
								+ bean.getApplicationId() + "#"
								+ bean.getEmpApplId() + "#" + "SO";

						link_label[0] = "Approve";
						link_label[1] = "Reject";

						template.sendApplicationMail();
						template.clearParameters();
						template.terminate();
					}

				}
			} catch (Exception e) {
				logger
						.error("Error while sending mail after accepting accom Options : "
								+ e);
				e.printStackTrace();
			}

			try {
				startBookingMail(bean);
			} catch (Exception e) {
				logger.error("Error in start booking mail");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void decline_Accom(TravelMonitoring bean, HttpServletRequest request) {
		String updateQry = " UPDATE TMS_SUGG_ACCOM SET ACCOM_SEL_FLAG = 'N' ";
		boolean flag = false;
		if (!request.getParameter("dtlId").equals("")) {
			updateQry += ",ACCOM_REJ_FLAG = 'Y'";
		}
		updateQry += " WHERE MONITOR_ID = " + request.getParameter("monitorId");

		if (!request.getParameter("dtlId").equals("")) {
			updateQry += " AND ACCOM_LODGEDTL_CODE IN ("
					+ request.getParameter("dtlId") + ")";
		}
		getSqlModel().singleExecute(updateQry);

		updateQry = " UPDATE TMS_MONITORING SET MTR_ACCOM_APP_COMM = '"
				+ bean.getAplComments() + "',MTR_ACCOM_APR_COMM = '"
				+ bean.getAprComments() + "',";
		if (!request.getParameter("dtlId").equals("")) {
			updateQry += " MTR_ACC_AVSTATUS = 'SO'";

		} else {
			updateQry += " MTR_ACC_AVSTATUS = 'RE'";
			flag = true;
		}
		updateQry += " WHERE TMS_MONITOR_ID = "
				+ request.getParameter("monitorId");
		getSqlModel().singleExecute(updateQry);

		if (flag) {
			try {
				updateCommentsTrail(bean.getUserEmpId(), request
						.getParameter("applicationId"), request
						.getParameter("empApplId"), request
						.getParameter("userType"), bean.getAplComments(), bean
						.getAccStatus());
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			try {
				updateCommentsTrail(bean.getUserEmpId(), request
						.getParameter("applicationId"), request
						.getParameter("empApplId"), request
						.getParameter("userType"), bean.getAprComments(), bean
						.getAccStatus());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		if (flag) {
			try {
				Object[][] tempData = checkMailEvent(59);
				if (tempData == null || tempData.length == 0)
					return;
				if (String.valueOf(tempData[0][0]).equals("Y")) {

					String schEmpCode = " SELECT SCHDTL_SUBSCHLAR_ECODE,DESK_SCH_ECODE FROM TMS_SCH_DTL "
							+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_ID = TMS_SCH_DTL.SCH_DESK_ID) "
							+ " WHERE DESK_APPL_ID = "
							+ request.getParameter("applicationId")
							+ " AND DESK_APPL_CODE = "
							+ request.getParameter("empApplId")
							+ " AND  (TMS_SCH_DESK.DESK_LODGE_ASSIGN = 'Y' AND  TMS_SCH_DTL.SCHDTL_LODGE_ASSIGN = 'Y') ";
					Object[][] schEmpIdObj = getSqlModel().getSingleResult(
							schEmpCode);

					if (schEmpIdObj == null || schEmpIdObj.length == 0)
						return;

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template.setEmailTemplate(String.valueOf(tempData[0][3]));
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);
					templateQuery1.setParameter(1, String.valueOf(bean
							.getIniEmpId()));

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);
					templateQuery2.setParameter(1, String
							.valueOf(schEmpIdObj[0][1]));

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, String.valueOf(bean
							.getApplicationId()));
					templateQuery3.setParameter(2, String.valueOf(bean
							.getEmpApplId()));
					templateQuery3.setParameter(3, String.valueOf(bean
							.getApplicationId()));
					templateQuery3.setParameter(4, String.valueOf(bean
							.getEmpApplId()));
					template.configMailAlert();
					if (!String.valueOf(schEmpIdObj[0][0]).equals(
							String.valueOf(schEmpIdObj[0][1]))) {
						template.sendApplicationMailToKeepInfo(String
								.valueOf(schEmpIdObj[0][0]));
					}
					template.sendApplicationMail();
					template.clearParameters();
					template.terminate();
				}
			} catch (Exception e) {
				logger
						.error("Error while sending mail after accepting accom Options : "
								+ e);
				e.printStackTrace();
			}

			try {
				startBookingMail(bean);
			} catch (Exception e) {
				logger.error("Error in start booking mail");
			}
		}
	}

	public String isPolicyViolateAccom(String empId, String classId) {
		try {
			String classQry = " SELECT ROOM_LEVEL,HOTEL_TYPE_NAME||'-'||ROOM_TYPE_NAME,(SELECT MIN(HOTEL_LEVEL) FROM HRMS_TMS_ROOM_TYPE "
					+ " INNER JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID = HRMS_TMS_ROOM_TYPE.ROOM_HOTEL_TYPE) "
					+ " WHERE ROOM_TYPE_ID IN("
					+ classId
					+ "))AS HOTEL_LEVEL FROM HRMS_TMS_ROOM_TYPE "
					+ " INNER JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID = HRMS_TMS_ROOM_TYPE.ROOM_HOTEL_TYPE) "
					+ " WHERE ROOM_LEVEL = (SELECT MIN(ROOM_LEVEL) FROM HRMS_TMS_ROOM_TYPE WHERE ROOM_TYPE_ID IN("
					+ classId + ")) ";

			Object[][] classSelObj = getSqlModel().getSingleResult(classQry);
			if (classSelObj == null || classSelObj.length == 0)
				return "";
			int classSel = Integer.parseInt(String.valueOf(classSelObj[0][0]));
			int hotelLevel = Integer
					.parseInt(String.valueOf(classSelObj[0][2]));

			String className = "" + classSelObj[0][1];

			classQry = " SELECT DISTINCT ROOM_LEVEL,HOTEL_TYPE_NAME||'-'||ROOM_TYPE_NAME,HOTEL_LEVEL,TMS_TRAVEL_POLICY.POLICY_ID FROM TMS_POLICY_GRADE_DTL "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_CADRE = TMS_POLICY_GRADE_DTL.POLICY_GRAD_ID) "
					+ " INNER JOIN TMS_POLICY_MAP_DTL ON(TMS_POLICY_GRADE_DTL.POLICY_ID = TMS_POLICY_MAP_DTL.POLICY_ID) "
					+ " INNER JOIN HRMS_TMS_ROOM_TYPE ON(HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID = TMS_POLICY_MAP_DTL.POLICY_MAP_LODGE) "
					+ " INNER JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID = HRMS_TMS_ROOM_TYPE.ROOM_HOTEL_TYPE) "
					+ " INNER JOIN TMS_TRAVEL_POLICY ON(TMS_TRAVEL_POLICY.POLICY_ID = TMS_POLICY_MAP_DTL.POLICY_ID) "
					+ " WHERE EMP_ID="
					+ empId
					+ " AND TMS_TRAVEL_POLICY.POLICY_STATUS='A' ORDER BY ROOM_LEVEL ";
			classSelObj = getSqlModel().getSingleResult(classQry);
			if (classSelObj == null || classSelObj.length == 0)
				return "";
			className = "Eligible for class accommodation is "
					+ classSelObj[0][1] + " and below. You have chosen "
					+ className + ". \n\n Do you want to continue? ";
			if (hotelLevel < Integer
					.parseInt(String.valueOf(classSelObj[0][2])))
				return "" + className;
			else if (hotelLevel == Integer.parseInt(String
					.valueOf(classSelObj[0][2]))
					&& classSel < Integer.parseInt(String
							.valueOf(classSelObj[0][0])))
				return "" + className;
			return "";

		} catch (Exception e) {
			return "";
		}
	}

	public void callDtl(TravelMonitoring monitorBean, HttpServletRequest request) {
		String statQry = " SELECT MTR_TVL_AVA_STATUS, MTR_ACC_AVSTATUS, MTR_LOCAL_AVSTATUS "
				+ " FROM TMS_MONITORING WHERE APPL_CODE = "
				+ request.getParameter("empApplId")
				+ " AND APPL_ID = "
				+ request.getParameter("applicationId");
		Object[][] statObj = getSqlModel().getSingleResult(statQry);
		request.setAttribute("statObj", statObj);
		statQry = " SELECT APPL_TVL_CANCEL_STATUS, APPL_ACC_CANCEL_STATUS, APPL_LOC_CANCEL_STATUS "
				+ " FROM TMS_APP_EMPDTL "
				+ " WHERE APPL_CODE = "
				+ request.getParameter("empApplId")
				+ " AND APPL_ID  = "
				+ request.getParameter("applicationId");
		Object[][] applStatusObj = getSqlModel().getSingleResult(statQry);
		if (applStatusObj == null || applStatusObj.length == 0) {
			statQry = " SELECT APPL_TVL_CANCEL_STATUS, APPL_ACC_CANCEL_STATUS, APPL_LOC_CANCEL_STATUS "
					+ " FROM TMS_APP_GUEST_DTL "
					+ " WHERE APPL_CODE = "
					+ request.getParameter("empApplId")
					+ " AND APPL_ID  = "
					+ request.getParameter("applicationId");
			applStatusObj = getSqlModel().getSingleResult(statQry);
		}

		logger.info("KRISH--------------" + request.getParameter("userType"));
		monitorBean.setUserType(request.getParameter("userType"));

		if (request.getParameter("userType").equals("SUB")) {
			String getStatusQry = " SELECT SCHDTL_TRAVEL_ASSIGN,SCHDTL_LODGE_ASSIGN,SCHDTL_LOCAL_ASSIGN "
					+ " FROM TMS_SCH_DESK "
					+ " INNER JOIN TMS_SCH_DTL ON(TMS_SCH_DTL.SCH_DESK_ID = TMS_SCH_DESK.DESK_ID) "
					+ " WHERE TMS_SCH_DESK.DESK_APPL_CODE = "
					+ request.getParameter("empApplId")
					+ " and TMS_SCH_DESK.DESK_APPL_ID  = "
					+ request.getParameter("applicationId")
					+ " AND SCHDTL_SUBSCHLAR_ECODE = "
					+ monitorBean.getUserEmpId();
			Object[][] statusObj = getSqlModel().getSingleResult(getStatusQry);
			if (statusObj != null && statusObj.length > 0) {
				if (String.valueOf(statusObj[0][0]).equals("Y"))
					statusObj[0][0] = "Y ";
				if (String.valueOf(statusObj[0][1]).equals("Y"))
					statusObj[0][1] = "Y ";
				if (String.valueOf(statusObj[0][2]).equals("Y"))
					statusObj[0][2] = "Y ";

				monitorBean.setTvlStatus(String.valueOf(statusObj[0][0]));
				if (monitorBean.getTvlStatus().trim().equals("Y")
						&& String.valueOf(statObj[0][0]).equals("CE"))
					monitorBean.setTvlStatus("SB");
				else if (monitorBean.getTvlStatus().trim().equals("Y")
						&& (String.valueOf(applStatusObj[0][0]).equals("CC") || String
								.valueOf(applStatusObj[0][0]).equals("CP"))
						&& (String.valueOf(statObj[0][0]).equals("FI") || String
								.valueOf(statObj[0][0]).equals("CC")))
					monitorBean.setTvlStatus("SC");
				monitorBean.setAccStatus(String.valueOf(statusObj[0][1]));
				if (monitorBean.getAccStatus().trim().equals("Y")
						&& String.valueOf(statObj[0][1]).equals("CE"))
					monitorBean.setAccStatus("SB");
				else if (monitorBean.getAccStatus().trim().equals("Y")
						&& (String.valueOf(applStatusObj[0][1]).equals("CC") || String
								.valueOf(applStatusObj[0][1]).equals("CP"))
						&& (String.valueOf(statObj[0][1]).equals("FI") || String
								.valueOf(statObj[0][1]).equals("CC")))
					monitorBean.setAccStatus("SC");
				monitorBean.setLocConStatus(String.valueOf(statusObj[0][2]));
				if (monitorBean.getLocConStatus().trim().equals("Y")
						&& String.valueOf(statObj[0][2]).equals("CE"))
					monitorBean.setLocConStatus("SB");
				else if (monitorBean.getLocConStatus().trim().equals("Y")
						&& (String.valueOf(applStatusObj[0][2]).equals("CC") || String
								.valueOf(applStatusObj[0][2]).equals("CP"))
						&& (String.valueOf(statObj[0][2]).equals("FI") || String
								.valueOf(statObj[0][2]).equals("CC")))
					monitorBean.setLocConStatus("SC");
			} else {
				monitorBean.setTvlStatus("");
				monitorBean.setAccStatus("");
				monitorBean.setLocConStatus("");
			}
		} else {
			if ((String.valueOf(statObj[0][0]).equals("FI")
					|| String.valueOf(statObj[0][0]).equals("CD") || String
					.valueOf(statObj[0][0]).equals("CC"))
					&& (String.valueOf(applStatusObj[0][0]).equals("CC") || String
							.valueOf(applStatusObj[0][0]).equals("CP")))
				monitorBean.setTvlStatus("SC");
			else
				monitorBean.setTvlStatus("SB");
			if ((String.valueOf(statObj[0][1]).equals("FI")
					|| String.valueOf(statObj[0][1]).equals("CD") || String
					.valueOf(statObj[0][1]).equals("CC"))
					&& (String.valueOf(applStatusObj[0][1]).equals("CC") || String
							.valueOf(applStatusObj[0][1]).equals("CP")))
				monitorBean.setAccStatus("SC");
			else
				monitorBean.setAccStatus("SB");
			if ((String.valueOf(statObj[0][2]).equals("FI")
					|| String.valueOf(statObj[0][2]).equals("CD") || String
					.valueOf(statObj[0][2]).equals("CC"))
					&& (String.valueOf(applStatusObj[0][2]).equals("CC") || String
							.valueOf(applStatusObj[0][2]).equals("CP")))
				monitorBean.setLocConStatus("SC");
			else
				monitorBean.setLocConStatus("SB");
		}

		// CANCELLATION COMMENTS IN CANCELLED APPLICATION IN CANCELLED LIST
		String reqFrmDesk = "" + request.getParameter("reqFrmDesk");
		String sql = "";
		if (reqFrmDesk == null || reqFrmDesk.equals("null")) {
			sql = "SELECT COMMENTS_DESC FROM TMS_COMMENTS_TRAIL WHERE COMMENTS_APP_ID="
					+ request.getParameter("applicationId")
					+ " AND COMMENTS_APP_CODE="
					+ request.getParameter("empApplId")
					+ "  AND COMMENTS_LEVEL='CNCL' AND COMMENTS_ID=(SELECT MAX(COMMENTS_ID) FROM TMS_COMMENTS_TRAIL WHERE COMMENTS_APP_ID="
					+ request.getParameter("applicationId")
					+ " AND COMMENTS_APP_CODE="
					+ request.getParameter("empApplId") + ")";// AND
																// COMMENTS_APP_STATE='APPL'
		} else {// DESK
			sql = "SELECT COMMENTS_DESC FROM TMS_COMMENTS_TRAIL WHERE COMMENTS_APP_ID="
					+ request.getParameter("applicationId")
					+ " AND COMMENTS_APP_CODE="
					+ request.getParameter("empApplId")
					+ "  AND COMMENTS_LEVEL='CNCL' AND COMMENTS_ID=(SELECT MAX(COMMENTS_ID) FROM TMS_COMMENTS_TRAIL WHERE COMMENTS_APP_ID="
					+ request.getParameter("applicationId")
					+ " AND COMMENTS_APP_CODE="
					+ request.getParameter("empApplId") + ")";// AND
																// COMMENTS_APP_STATE='DESK'
		}
		Object data[][] = getSqlModel().getSingleResult(sql);
		if (data != null && data.length > 0) {
			monitorBean.setCancelComm(String.valueOf(data[0][0]));
		}
		// /////////////////////////////////////////////////////////

		setEmpInfo(request, monitorBean);
		setTourDtls(monitorBean, request);
		if (monitorBean.getTvlStatus().equals("SB")
				|| monitorBean.getTvlStatus().equals("SC"))
			setJourDtls(monitorBean, request);
		if (monitorBean.getLocConStatus().equals("SB")
				|| monitorBean.getLocConStatus().equals("SC"))
			setLocConDtls(monitorBean, request);
		if (monitorBean.getAccStatus().equals("SB")
				|| monitorBean.getAccStatus().equals("SC"))
			setLodgDtls(monitorBean, request);

	}

	public void setEmpDtls(TravelMonitoring bean, HttpServletRequest request) {
		String dataQUery = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME,CENTER_NAME,DEPT_NAME,RANK_NAME,"
				+ "	TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APP_DATE ,"
				+ "	CASE   WHEN APPL_EMP_TRAVEL_APPLSTATUS='P' THEN 'Pending'"
				+ "			     WHEN APPL_EMP_TRAVEL_APPLSTATUS='A' THEN 'Approved'  "
				+ "				 WHEN APPL_EMP_TRAVEL_APPLSTATUS='R' THEN 'Rejected'  "
				+ "				 WHEN APPL_EMP_TRAVEL_APPLSTATUS='B' THEN 'Returned'"
				+ "			     ELSE  ''  END CASE,"
				+ "	NVL(CADRE_NAME,' '),"
				+ "	NVL(APPL_EMP_AGE,'') AS Age,APPL_EMP_CONTACT"
				+ " ,NVL(APPL_EMP_ADVANCE_AMT,0),DECODE(APPL_EMP_PAY_MODE,'C','Cash','S','Salary','Q','Cheque','T','Transfer') AS PAY_MODE,"
				+ "	 TO_CHAR(APPL_EMP_EXP_SETT_DATE,'DD-MM-YYYY') AS SET_DATE,NVL(APPL_EMP_ID_PROOF,''),NVL(APPL_EMP_ID_NUMBER,''),"
				+ "	 NVL(APPL_EMP_ID_COMMENTS,''),CADRE_ID "
				+ "	FROM TMS_APP_EMPDTL"
				+ "	LEFT JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_APP_EMPDTL.APPL_ID) "
				+ "	LEFT JOIN HRMS_EMP_OFFC ON(TMS_APP_EMPDTL.APPL_EMP_CODE=HRMS_EMP_OFFC.EMP_ID)"
				+ "	LEFT JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ "	LEFT JOIN HRMS_DEPT ON (HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC. EMP_DEPT )"
				+ "	LEFT  JOIN HRMS_CADRE ON(HRMS_CADRE.CADRE_ID=HRMS_EMP_OFFC.EMP_CADRE)"
				+ "	LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_EMP_OFFC. EMP_RANK )"
				+ "	WHERE TMS_APP_EMPDTL.APPL_ID="
				+ request.getParameter("applicationId")
				// + bean.getTmsTrvlId()
				// + "AND APPL_CODE=" + bean.getTmsTrvlIndiId();
				+ "AND APPL_CODE=" + request.getParameter("empApplId");
		Object[][] data = getSqlModel().getSingleResult(dataQUery);

		if (data != null && data.length > 0) {

			bean.setTrvlAdvAmt(checkNull(String.valueOf(data[0][10])));
			bean.setTrvlPrefPayMode(checkNull(String.valueOf(data[0][11])));
			bean.setTrvlExpSettleDate(checkNull(String.valueOf(data[0][12])));
			bean.setIdProof(checkNull(String.valueOf(data[0][13])));
			bean.setIdNumber(checkNull(String.valueOf(data[0][14])));
			bean.setIdCmts(checkNull(String.valueOf(data[0][15])));
			bean.setBrnchName(checkNull(String.valueOf(data[0][2])));
			bean.setDeptName(checkNull(String.valueOf(data[0][3])));
			bean.setDesgn(checkNull(String.valueOf(data[0][4])));
			bean.setApplDate(checkNull(String.valueOf(data[0][5])));
			bean.setGrade(checkNull(String.valueOf(data[0][7])));
			bean.setGradeId(checkNull(String.valueOf(data[0][16])));
			bean.setAge(checkNull(String.valueOf(data[0][8])));
			bean.setContactNo(checkNull(String.valueOf(data[0][9])));

			System.out.println("APPDATE::String.valueOf(data[0][5])::::"
					+ String.valueOf(data[0][5]));

		} /*
			 * else { bean.setSelfTrvlFlag(false); }
			 */

	}

	private void setGuestDtls(TravelMonitoring bean, HttpServletRequest request) {
		String dataQUery = "SELECT EMP_TOKEN ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS INI_NAME,GUEST_NAME,TO_CHAR(APPL_DATE,'DD-MM-YYYY') AS APP_DATE ,"
				+ " CASE   WHEN GUEST_TRAVEL_APPLSTATUS='P' THEN 'Pending'"
				+ "        WHEN GUEST_TRAVEL_APPLSTATUS='A' THEN 'Approved'"
				+ " 	   WHEN GUEST_TRAVEL_APPLSTATUS='R' THEN 'Rejected'  "
				+ " 	   WHEN GUEST_TRAVEL_APPLSTATUS='B' THEN 'Returned'"
				+ " 	   ELSE  ''  END AS STATUS,GUEST_AGE,GUEST_CONTACT"

				+ " ,NVL(GUEST_ADVANCE_AMT,0), DECODE(GUEST_PAY_MODE,'C','Cash','S','Salary','Q','Cheque','T','Transfer') AS PAY_MODE,"
				+ " TO_CHAR(GUEST_EXPECTED_SET_DATE,'DD-MM-YYYY') AS SET_DATE,"
				+ " NVL(GUEST_ID_PROOF,''),NVL(GUEST_ID_NUMBER,''),NVL(GUEST_ID_COMMENTS,'')"
				+ " FROM TMS_APP_GUEST_DTL"
				+ " LEFT JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_APP_GUEST_DTL.APPL_ID)"
				+ " INNER JOIN   HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=TMS_APPLICATION.APPL_INITIATOR)"
				+ "	WHERE TMS_APP_GUEST_DTL.APPL_ID="
				+ request.getParameter("applicationId")
				+ "AND APPL_CODE="
				+ request.getParameter("empApplId");
		Object[][] data = getSqlModel().getSingleResult(dataQUery);

		if (data != null && data.length > 0) {
			bean.setTrvlAdvAmt(checkNull(String.valueOf(data[0][7])));
			bean.setTrvlPrefPayMode(checkNull(String.valueOf(data[0][8])));
			bean.setTrvlExpSettleDate(checkNull(String.valueOf(data[0][9])));
			bean.setIdProof(checkNull(String.valueOf(data[0][10])));
			bean.setIdNumber(checkNull(String.valueOf(data[0][11])));
			bean.setIdCmts(checkNull(String.valueOf(data[0][12])));
			bean.setApplDate(checkNull(String.valueOf(data[0][3])));
			bean.setAge(checkNull(String.valueOf(data[0][5])));
			bean.setContactNo(checkNull(String.valueOf(data[0][6])));

		} else {
			bean.setGuestTrvlFlag(false);
		}
	}

	public void setTourDtls(TravelMonitoring bean, HttpServletRequest request) {
		logger.info("inside book dtl-------");
		String dataQUery = "SELECT DISTINCT DECODE(APPL_FOR_FLAG,'S','Self','G','Guest','O','Others') AS TRVL_FOR,NVL(TOUR_TRAVEL_REQ_NAME,''),PURPOSE_NAME ,"
				+ " DECODE(TOUR_ACCOM_ARR_DONE,'S','Self','Company') AS ACCOM,DECODE(TOUR_TRAVEL_ARR_DONE,'S','Self','Company') AS TRAVEL,"
				+ " DECODE(TOUR_CONV_ARR_DONE,'S','Self','Company') AS LOC_CONV,NVL(LOCATION_TYPE_NAME,''),"
				+ " TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS STRT_DATE,TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') AS END_DATE,APPL_TRVL_ID"
				+ " FROM TMS_APP_TOUR_DTL"
				+ " LEFT JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_APP_TOUR_DTL.APPL_ID)"
				+ " INNER JOIN TMS_APP_EMPDTL ON(TMS_APP_EMPDTL.APPL_ID=TMS_APP_TOUR_DTL.APPL_ID AND TMS_APP_EMPDTL.APPL_CODE=TMS_APP_TOUR_DTL.APPL_CODE) "
				+ " LEFT JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE)"
				+ " LEFT JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE)"
				+ "	WHERE TMS_APP_TOUR_DTL.APPL_ID="
				+ request.getParameter("applicationId")
				+ " AND TMS_APP_TOUR_DTL.APPL_CODE="
				+ request.getParameter("empApplId")
				+ " UNION "
				+ " SELECT DISTINCT DECODE(APPL_FOR_FLAG,'S','Self','G','Guest','O','Others') AS TRVL_FOR,NVL(TOUR_TRAVEL_REQ_NAME,''),PURPOSE_NAME ,"
				+ " DECODE(TOUR_ACCOM_ARR_DONE,'S','Self','Company') AS ACCOM,DECODE(TOUR_TRAVEL_ARR_DONE,'S','Self','Company') AS TRAVEL,"
				+ " DECODE(TOUR_CONV_ARR_DONE,'S','Self','Company') AS LOC_CONV,NVL(LOCATION_TYPE_NAME,''),"
				+ " TO_CHAR(TOUR_TRAVEL_STARTDT,'DD-MM-YYYY') AS STRT_DATE,TO_CHAR(TOUR_TRAVEL_ENDDT,'DD-MM-YYYY') AS END_DATE,APPL_TRVL_ID"
				+ " FROM TMS_APP_TOUR_DTL"
				+ " LEFT JOIN TMS_APPLICATION ON(TMS_APPLICATION.APPL_ID=TMS_APP_TOUR_DTL.APPL_ID)"
				+ " LEFT JOIN TMS_APP_GUEST_DTL ON(TMS_APP_GUEST_DTL.APPL_ID=TMS_APP_TOUR_DTL.APPL_ID AND TMS_APP_GUEST_DTL.APPL_CODE=TMS_APP_TOUR_DTL.APPL_CODE) "
				+ " LEFT JOIN HRMS_TMS_PURPOSE ON(HRMS_TMS_PURPOSE.PURPOSE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_PURPOSE)"
				+ " LEFT JOIN HRMS_TMS_LOCATION_TYPE ON(HRMS_TMS_LOCATION_TYPE.LOCATION_TYPE_ID=TMS_APP_TOUR_DTL.TOUR_TRAVEL_TYPE)"
				+ "	WHERE TMS_APP_TOUR_DTL.APPL_ID="
				+ request.getParameter("applicationId")
				+ " AND TMS_APP_TOUR_DTL.APPL_CODE="
				+ request.getParameter("empApplId");

		Object[][] data = getSqlModel().getSingleResult(dataQUery);

		if (data != null && data.length > 0) {

			bean.setTrvlAppFor(checkNull(String.valueOf(data[0][0])));
			bean.setTrvlReqName(checkNull(String.valueOf(data[0][1])));
			bean.setTrvlPurpose(checkNull(String.valueOf(data[0][2])));
			bean.setTrvlAccom(checkNull(String.valueOf(data[0][3])));
			bean.setTrvlArrngmt(checkNull(String.valueOf(data[0][4])));
			bean.setTrvlLocCon(checkNull(String.valueOf(data[0][5])));
			bean.setTrvlType(checkNull(String.valueOf(data[0][6])));
			bean.setTourStrtDate(checkNull(String.valueOf(data[0][7])));
			bean.setTourEndDate(checkNull(String.valueOf(data[0][8])));
			bean.setTravelId(checkNull(String.valueOf(data[0][9])));

		}
	}

	public void setJourDtls(TravelMonitoring bean, HttpServletRequest request) {
		logger.info("setting setJourDtls details....");
		String query = "SELECT DISTINCT NVL(TMS_SUGG_TRAVELLING.TVLNG_SOURCE,''),NVL(TMS_SUGG_TRAVELLING.TVLNG_DESTINATION,''),"
				+ " HRMS_TMS_JOURNEY_MODE.JOURNEY_NAME||'-'|| HRMS_TMS_JOURNEY_CLASS.CLASS_NAME ,TO_CHAR(TMS_SUGG_TRAVELLING.TVLNG_DATE,'DD-MM-YYYY') AS JOUR_DATE,"
				+ " TMS_SUGG_TRAVELLING.TVLNG_TIME ,TMS_SUGG_TRAVELLING.TVLNG_JOURN_CODE,TVLNG_ID_NO,TVLNG_TCK_NO, TVLNG_COST,  TVLNG_ORDER,TMS_SUGG_TRAVELLING.TVLNG_DTL_CODE,TVLNG_DETAILS,TVLNG_GROUP_CODE,TMS_APP_JOURNEY_DTL.JOURNEY_STATUS"
				+ " ,TVLNG_CANCEL_CHARGE,TVLNG_REFUND_AMT,TVLNG_COMMENTS,JOURNEY_STATUS,JOURNEY_UPLOAD_FILE"
				+ " FROM TMS_SUGG_TRAVELLING"
				+ " INNER JOIN TMS_APP_JOURNEY_DTL ON(TMS_APP_JOURNEY_DTL.JOURNEY_CODE = TMS_SUGG_TRAVELLING.TVLNG_JOURN_CODE ) "
				+ " INNER JOIN HRMS_TMS_JOURNEY_CLASS ON(HRMS_TMS_JOURNEY_CLASS.CLASS_ID=TMS_SUGG_TRAVELLING.TVLNG_MODE) "
				+ " INNER JOIN HRMS_TMS_JOURNEY_MODE ON(HRMS_TMS_JOURNEY_MODE.JOURNEY_ID = HRMS_TMS_JOURNEY_CLASS.CLASS_JOURNEY_ID)"
				+ " INNER JOIN TMS_MONITORING ON(TMS_MONITORING.TMS_MONITOR_ID=TMS_SUGG_TRAVELLING.MONITOR_ID)"
				+ " WHERE  TMS_MONITORING.APPL_CODE="
				+ request.getParameter("empApplId")
				+ " AND TMS_APP_JOURNEY_DTL.APPL_ID ="
				+ request.getParameter("applicationId")
				+ " AND (MTR_TVL_AVA_STATUS='CE' OR MTR_TVL_AVA_STATUS='FI' OR MTR_TVL_AVA_STATUS='CD' OR MTR_TVL_AVA_STATUS='CC') "
				+ " AND TVLNG_SEL_FLAG = 'Y' AND TMS_APP_JOURNEY_DTL.JOURNEY_STATUS IN('N','CC')"
				+ " ORDER BY TMS_SUGG_TRAVELLING.TVLNG_JOURN_CODE,TVLNG_GROUP_CODE,TVLNG_ORDER ";
		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {

			ArrayList trvlList = new ArrayList();

			for (int i = 0; i < data.length; i++) {
				TravelMonitoring bean1 = new TravelMonitoring();
				bean1.setJourFrm(checkNull(String.valueOf(data[i][0])));
				bean1.setJourTo(checkNull(String.valueOf(data[i][1])));
				bean1.setJourModeCls(checkNull(String.valueOf(data[i][2])));
				bean1.setJourDate(checkNull(String.valueOf(data[i][3])));
				bean1.setJourTime(checkNull(String.valueOf(data[i][4])));
				bean1.setJourClsId(checkNull(String.valueOf(data[i][5])));
				bean1.setJourNo(checkNull(String.valueOf(data[i][6])));
				bean1.setTicketNo(checkNull(String.valueOf(data[i][7])));
				bean1.setJourCost(checkNull(String.valueOf(data[i][8])));
				bean1.setJournDtlId(checkNull(String.valueOf(data[i][10])));
				bean1.setJourDetails(checkNull(String.valueOf(data[i][11])));
				bean1.setJourCancelFlag(checkNull(String.valueOf(data[i][13])));
				bean1
						.setCancelChargeTvl(checkNull(String
								.valueOf(data[i][14])));
				bean1.setRefundAmtTvl(checkNull(String.valueOf(data[i][15])));
				bean1.setCommentsTvl(checkNull(String.valueOf(data[i][16])));
				bean1.setUploadFileTvl(checkNull(String.valueOf(data[i][18])));
				trvlList.add(bean1);
			} // end of for loop
			bean.setTravelJourDtl(trvlList);
			bean.setJourDtlFlag(true);

		} else {
			bean.setTvlStatus("NA");
		}
	}

	public void setLocConDtls(TravelMonitoring bean, HttpServletRequest request) {
		logger.info("setting localconveyance details....");
		String query = "SELECT DISTINCT LOCCONV_SOURCE,LOCCONV_PICKUPPLACE,"
				+ " LOCCONV_TRAVELMODE,TO_CHAR(LOCCONV_DATE,'DD-MM-YYYY'),"
				+ " LOCCONV_FROM_TIME,LOCCONV_TO_TIME,LOCCONV_CONV_CODE,"
				+ " LOCCONV_TARIFFCOST,LOCCONV_DTL_CODE,LOCCONV_ORDER,LOCCONV_TRAVELMODE, LOCCONV_DETAILS,LOCCONV_GROUP_CODE,TMS_APP_CONV_DTL.CONV_STATUS"
				+ " ,LOCCONV_CANCEL_CHARGE,LOCCONV_REFUND_AMT,LOCCONV_COMMENTS,CONV_STATUS,CONV_UPLOAD_FILE"
				+ " FROM TMS_SUGG_LOC_CONV"
				+ " INNER JOIN TMS_APP_CONV_DTL ON(TMS_APP_CONV_DTL.CONV_CODE = TMS_SUGG_LOC_CONV.LOCCONV_CONV_CODE) "
				+ " INNER JOIN TMS_MONITORING ON(TMS_MONITORING.TMS_MONITOR_ID=TMS_SUGG_LOC_CONV.MONITOR_ID)"
				+ " WHERE TMS_MONITORING.APPL_CODE="
				+ request.getParameter("empApplId")
				+ " AND TMS_APP_CONV_DTL.APPL_ID="
				+ request.getParameter("applicationId")
				+ " AND (MTR_LOCAL_AVSTATUS='CE' OR MTR_LOCAL_AVSTATUS='FI' OR MTR_LOCAL_AVSTATUS='CD' OR MTR_LOCAL_AVSTATUS='CC') "
				+ " AND LOCCONV_SEL_FLAG = 'Y' AND TMS_APP_CONV_DTL.CONV_STATUS IN('N','CC')"
				+ " ORDER BY TMS_SUGG_LOC_CONV.LOCCONV_CONV_CODE,LOCCONV_GROUP_CODE,LOCCONV_ORDER ";
		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {
			ArrayList trvlList = new ArrayList();

			for (int i = 0; i < data.length; i++) {
				TravelMonitoring bean1 = new TravelMonitoring();
				bean1.setLocConCity(checkNull(String.valueOf(data[i][0])));
				bean1.setLocConSource(checkNull(String.valueOf(data[i][1])));
				bean1.setLocConMode(checkNull(String.valueOf(data[i][2])));
				bean1.setLocConDate(checkNull(String.valueOf(data[i][3])));
				bean1.setLocConFrmTime(checkNull(String.valueOf(data[i][4])));
				bean1.setLocConToTime(checkNull(String.valueOf(data[i][5])));
				bean1.setLocConId(checkNull(String.valueOf(data[i][6])));
				bean1.setLocConCost(checkNull(String.valueOf(data[i][7])));
				bean1.setLocConDtlId(checkNull(String.valueOf(data[i][8])));
				bean1.setLocDetails(checkNull(String.valueOf(data[i][11])));
				bean1.setLocCancelFlag(checkNull(String.valueOf(data[i][13])));
				bean1
						.setCancelChargeLoc(checkNull(String
								.valueOf(data[i][14])));
				bean1.setRefundAmtLoc(checkNull(String.valueOf(data[i][15])));
				bean1.setCommentsLoc(checkNull(String.valueOf(data[i][16])));
				bean1.setUploadFileLoc(checkNull(String.valueOf(data[i][18])));
				trvlList.add(bean1);
			} // end of for loop
			bean.setTravelLocConDtl(trvlList);
			bean.setLocConDtlFlag(true);

		} else {
			bean.setLocConStatus("NA");
		}

	}

	public void setLodgDtls(TravelMonitoring bean, HttpServletRequest request) {
		logger.info("setting lodge details......");
		String query = "SELECT DISTINCT  HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_NAME,HRMS_TMS_ROOM_TYPE.ROOM_TYPE_NAME,ACCOM_CITY, "
				+ "  NVL(ACCOM_ADDRESS,''),TO_CHAR(ACCOM_FROMDATE,'DD-MM-YYYY') AS FROM_DATE ,ACCOM_CHECKIN,  TO_CHAR(ACCOM_TODATE,'DD-MM-YYYY')"
				+ " AS TO_DATE,ACCOM_CHECKOUT ,ACCOM_LODGE_CODE,ACCOM_BOOKING_AMT,ACCOM_LODGEDTL_CODE,ACCOM_HOTELTYPE,ACCOM_ROOMTYPE,ACCOM_DETAILS,ACCOM_GROUP_CODE,ACCOM_ORDER,TMS_APP_LODGE_DTL.LODGE_STATUS  "
				+ " ,ACCOM_CANCEL_CHARGE,ACCOM_REFUND_AMT,ACCOM_COMMENTS,LODGE_STATUS,LODGE_UPLOAD_FILE"
				+ " FROM TMS_SUGG_ACCOM "
				+ " INNER JOIN HRMS_TMS_HOTEL_TYPE ON(HRMS_TMS_HOTEL_TYPE.HOTEL_TYPE_ID=TMS_SUGG_ACCOM.ACCOM_HOTELTYPE)"
				+ " INNER JOIN HRMS_TMS_ROOM_TYPE ON(HRMS_TMS_ROOM_TYPE.ROOM_TYPE_ID=TMS_SUGG_ACCOM.ACCOM_ROOMTYPE) "
				+ " INNER JOIN TMS_APP_LODGE_DTL ON (TMS_SUGG_ACCOM.ACCOM_LODGE_CODE = TMS_APP_LODGE_DTL.LODGE_CODE )"
				+ " INNER JOIN TMS_MONITORING ON(TMS_MONITORING.TMS_MONITOR_ID=TMS_SUGG_ACCOM.MONITOR_ID)"
				+ " WHERE TMS_MONITORING.APPL_CODE="
				+ request.getParameter("empApplId")
				+ " AND TMS_APP_LODGE_DTL.APPL_ID ="
				+ request.getParameter("applicationId")
				+ " AND (MTR_ACC_AVSTATUS='CE' OR MTR_ACC_AVSTATUS='FI' OR MTR_ACC_AVSTATUS='CD' OR MTR_ACC_AVSTATUS='CC') "
				+ " AND ACCOM_SEL_FLAG = 'Y' AND TMS_APP_LODGE_DTL.LODGE_STATUS IN('N','CC')"
				+ " ORDER BY TMS_SUGG_ACCOM.ACCOM_LODGE_CODE,ACCOM_GROUP_CODE,ACCOM_ORDER ";

		Object[][] data = getSqlModel().getSingleResult(query);

		if (data != null && data.length > 0) {

			ArrayList trvlList = new ArrayList();

			for (int i = 0; i < data.length; i++) {
				TravelMonitoring bean1 = new TravelMonitoring();
				bean1.setLodgHotel(checkNull(String.valueOf(data[i][0])));
				bean1.setLodgRoom(checkNull(String.valueOf(data[i][1])));
				bean1.setLodgCity(checkNull(String.valueOf(data[i][2])));
				bean1.setLodgPreLoc(checkNull(String.valueOf(data[i][3])));
				bean1.setLodgFrmDate(checkNull(String.valueOf(data[i][4])));
				bean1.setLodgFrmTime(checkNull(String.valueOf(data[i][5])));
				bean1.setLodgToDate(checkNull(String.valueOf(data[i][6])));
				bean1.setLodgToTime(checkNull(String.valueOf(data[i][7])));
				bean1.setLodgId(checkNull(String.valueOf(data[i][8])));
				bean1.setLodgBookAmt(checkNull(String.valueOf(data[i][9])));
				bean1.setLodgDtlId(checkNull(String.valueOf(data[i][10])));
				bean1.setAccDetails(checkNull(String.valueOf(data[i][13])));
				bean1.setLodgCancelFlag(checkNull(String.valueOf(data[i][16])));
				bean1
						.setCancelChargeAcc(checkNull(String
								.valueOf(data[i][17])));
				bean1.setRefundAmtAcc(checkNull(String.valueOf(data[i][18])));
				bean1.setCommentsAcc(checkNull(String.valueOf(data[i][19])));
				bean1.setUploadFileAcc(checkNull(String.valueOf(data[i][21])));
				trvlList.add(bean1);
			} // end of for loop
			bean.setTravelLodgDtl(trvlList);
			bean.setLodgDtlFlag(true);

		} else {
			bean.setAccStatus("NA");
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

	public boolean book(TravelMonitoring monitorBean,
			HttpServletRequest request, String[] jourNo, String[] ticketNo,
			String[] jourCost, String[] locConCost, String[] lodgBookAmt,
			String[] locConId, String[] lodgId, String[] jourClsId,
			String[] jourDate, String[] jourTime, String[] journDtlId,
			String[] lodgDtlId, String[] locConDtlId, String[] jourDetails,
			String[] lodgDetails, String[] locDetails,
			String[] lodgCancelCharge, String[] lodgRefundAmt,
			String[] lodgComments, String[] jourCancelCharge,
			String[] jourRefundAmt, String[] jourComments,
			String[] locCancelCharge, String[] locRefundAmt,
			String[] locComments, String[] jourFileName, String[] lodgFileName,
			String[] locFileName) {
		boolean result = false;
		String updateFileName = "";
		Object[][] jourObj = null;
		Object[][] locConvObj = null;
		Object[][] lodgeObj = null;
		Object[][] fileName = null;
		int j = 0, k = 0, m = 0;
		if (journDtlId != null && journDtlId.length > 0) {
			jourObj = new Object[journDtlId.length][8];
			fileName = new Object[journDtlId.length][2];
			for (int i = 0; i < journDtlId.length; i++) {
				try {
					jourObj[j][0] = jourNo[i];
					jourObj[j][1] = ticketNo[i];
					jourObj[j][2] = jourCost[i];
					jourObj[j][3] = jourDetails[i];
					jourObj[j][4] = "";
					jourObj[j][5] = "";
					jourObj[j][6] = "";
					fileName[j][0] = jourFileName[i];
					fileName[j][1] = jourClsId[i];
					if (jourComments != null && jourComments.length > 0
							&& i < jourComments.length) {
						jourObj[j][4] = jourCancelCharge[i];
						jourObj[j][5] = jourRefundAmt[i];
						jourObj[j][6] = jourComments[i];
					}
					jourObj[j][7] = journDtlId[i];
					j++;
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
			result = getSqlModel().singleExecute(getQuery(1), jourObj);
			try {
				updateFileName = " UPDATE TMS_APP_JOURNEY_DTL SET JOURNEY_UPLOAD_FILE = ? "
						+ " WHERE JOURNEY_CODE = ?";
				getSqlModel().singleExecute(updateFileName, fileName);
			} catch (Exception e) {
				logger.error("Exception in updateFileName : " + e);
			}
		}// end of journey if

		if (locConDtlId != null && locConDtlId.length > 0) {
			logger.info("LOCAL>>>>>>");
			locConvObj = new Object[locConDtlId.length][6];
			fileName = new Object[locConDtlId.length][2];
			for (int i = 0; i < locConDtlId.length; i++) {
				try {
					locConvObj[k][0] = locConCost[i];
					locConvObj[k][1] = locDetails[i];
					locConvObj[k][2] = "";
					locConvObj[k][3] = "";
					locConvObj[k][4] = "";
					fileName[k][0] = locFileName[i];
					fileName[k][1] = locConId[i];
					if (locComments != null && locComments.length > 0
							&& i < locComments.length) {
						locConvObj[k][2] = locCancelCharge[i];
						locConvObj[k][3] = locRefundAmt[i];
						locConvObj[k][4] = locComments[i];
					}
					locConvObj[k][5] = locConDtlId[i];
					k++;
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
			result = getSqlModel().singleExecute(getQuery(2), locConvObj);
			try {
				logger.info("uploading the file--local");
				updateFileName = " UPDATE TMS_APP_CONV_DTL SET CONV_UPLOAD_FILE = ? "
						+ " WHERE CONV_CODE = ?";
				getSqlModel().singleExecute(updateFileName, fileName);
			} catch (Exception e) {
				logger.error("Exception in updateFileName : " + e);
			}
		}// end of local conveyance if

		if (lodgDtlId != null && lodgDtlId.length > 0) {
			lodgeObj = new Object[lodgDtlId.length][6];
			fileName = new Object[lodgDtlId.length][2];
			for (int i = 0; i < lodgDtlId.length; i++) {
				try {
					lodgeObj[m][0] = lodgBookAmt[i];
					lodgeObj[m][1] = lodgDetails[i];
					lodgeObj[m][2] = "";
					lodgeObj[m][3] = "";
					lodgeObj[m][4] = "";
					fileName[m][0] = lodgFileName[i];
					fileName[m][1] = lodgId[i];
					if (lodgComments != null && lodgComments.length > 0
							&& i < lodgComments.length) {
						lodgeObj[m][2] = lodgCancelCharge[i];
						lodgeObj[m][3] = lodgRefundAmt[i];
						lodgeObj[m][4] = lodgComments[i];
					}
					lodgeObj[m][5] = lodgDtlId[i];
					m++;
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
			result = getSqlModel().singleExecute(getQuery(3), lodgeObj);
			try {
				updateFileName = " UPDATE TMS_APP_LODGE_DTL SET LODGE_UPLOAD_FILE = ? "
						+ " WHERE LODGE_CODE = ?";
				getSqlModel().singleExecute(updateFileName, fileName);
			} catch (Exception e) {
				logger.error("Exception in updateFileName : " + e);
			}
		}// end of lodging if

		String updateQry = "UPDATE TMS_MONITORING SET APPL_ID = "
				+ request.getParameter("applicationId");
		if (jourObj != null && jourObj.length > 0)
			if (request.getParameter("tvlStatus").equals("SB")
					|| request.getParameter("tvlStatus").equals("NA"))
				updateQry += ",MTR_TVL_AVA_STATUS = 'FI',MTR_TVL_BOOKING_DATE = SYSDATE";
			else if (request.getParameter("tvlStatus").equals("SC"))
				updateQry += ",MTR_TVL_AVA_STATUS = 'CD',MTR_TVL_BOOKING_DATE = SYSDATE";

		if (lodgeObj != null && lodgeObj.length > 0)
			if (request.getParameter("accStatus").equals("SB")
					|| request.getParameter("accStatus").equals("NA"))
				updateQry += ",MTR_ACC_AVSTATUS= 'FI', MTR_ACC_BOOKING_DATE = SYSDATE";
			else if (request.getParameter("accStatus").equals("SC"))
				updateQry += ",MTR_ACC_AVSTATUS = 'CD',MTR_ACC_BOOKING_DATE = SYSDATE";

		if (locConvObj != null && locConvObj.length > 0)
			if (request.getParameter("locConStatus").equals("SB")
					|| request.getParameter("locConStatus").equals("NA"))
				updateQry += ",MTR_LOCAL_AVSTATUS='FI', MTR_LOC_BOOKING_DATE = SYSDATE";
			else if (request.getParameter("locConStatus").equals("SC"))
				updateQry += ",MTR_LOCAL_AVSTATUS='CD', MTR_LOC_BOOKING_DATE = SYSDATE";

		updateQry += " WHERE APPL_CODE = " + request.getParameter("empApplId")
				+ " AND APPL_ID = " + request.getParameter("applicationId");
		getSqlModel().singleExecute(updateQry);
		result = checkRecordFinalize(request);

		if (result)
			sendMailToAppl(monitorBean, jourFileName, lodgFileName, locFileName);

		return result;
	}

	public void addRowToOption_LocCon(TravelMonitoring monitorBean,
			HttpServletRequest request) {
		String pathName = request.getParameter("pathName");
		int maxMainCount = Integer.parseInt(request
				.getParameter("maxMainCount"));
		int maxOptCount = Integer.parseInt(request.getParameter("maxOptCount"));
		String userType = request.getParameter("userType");
		LinkedMap hMap = getTravelModeClass();
		boolean readOnly = Boolean
				.valueOf(request.getParameter("readOnlyFlag"));
		monitorBean.setUserType(userType);
		monitorBean.setReadOnlyFlag(readOnly);
		String[] temp = pathName.split("_");
		int mainCount = Integer.parseInt(temp[0]);
		int optCount = Integer.parseInt(temp[1]);
		ArrayList locConList = new ArrayList();
		for (int i = 0; i < maxMainCount; i++) {
			TravelMonitoring locConBean = new TravelMonitoring();
			ArrayList optList = new ArrayList();
			locConBean.setLocConCity(request.getParameter("locConCity_"
					+ (i + 1)));
			locConBean.setUserType(userType);
			locConBean.setReadOnlyFlag(Boolean.valueOf(request
					.getParameter("readOnlyFlag" + (i + 1))));
			for (int j = 0; j < maxOptCount; j++) {
				TravelMonitoring optBean = new TravelMonitoring();
				optBean.setUserType(userType);
				optBean.setReadOnlyFlag(readOnly);
				ArrayList pathList = new ArrayList();

				String[][] dataObj = getDataFromView_LocCon(request, i, j);

				if (dataObj == null || dataObj.length <= 0)
					break;
				optBean.setLocCancelFlag(request.getParameter("locCancelFlag"
						+ (i + 1) + "_" + (j + 1)));
				for (int k = 0; k < dataObj.length + 1; k++) {
					TravelMonitoring pathBean = new TravelMonitoring();

					if (k < dataObj.length) {
						pathBean.setSource(dataObj[k][0]);
						pathBean.setTravelDate(dataObj[k][1]);
						pathBean.setTravelModeClass(dataObj[k][2]);
						pathBean.setLocConVehNo(dataObj[k][3]);
						pathBean.setLocConConPer(dataObj[k][4]);
						pathBean.setLocConConNo(dataObj[k][5]);
						pathBean.setLocConPicPer(dataObj[k][6]);
						pathBean.setLocConFrmTime(dataObj[k][7]);
						pathBean.setLocConToTime(dataObj[k][8]);
						pathBean.setLocConPicPlace(dataObj[k][9]);
						pathBean.setLocConToriffCost(dataObj[k][10]);
						pathBean.setMonitorId(dataObj[k][11]);
						pathBean.setLocConvCode(dataObj[k][12]);
						pathBean.setLocDtlId(dataObj[k][13]);
						pathBean.setSelFlag(dataObj[k][14]);
						pathBean.setReadOnlyFlag(readOnly);
						pathBean.setUserType(userType);
						pathBean.setModeClassMap(hMap);
						pathBean.setLocCancelFlag(optBean.getLocCancelFlag());
						pathList.add(pathBean);

					} else if ((i + 1) == mainCount && (j + 1) == optCount) {
						pathBean.setModeClassMap(hMap);
						pathBean.setReadOnlyFlag(readOnly);
						pathBean.setUserType(userType);
						pathBean.setSource(dataObj[0][0]);
						pathBean.setTravelDate(dataObj[0][1]);
						pathBean.setTravelModeClass(dataObj[0][2]);
						pathBean.setLocConPicPlace(dataObj[0][9]);
						pathBean.setLocConFrmTime(dataObj[0][7]);
						pathBean.setLocConToTime(dataObj[0][8]);
						pathList.add(pathBean);
					}
				}
				optList.add(optBean);
				optBean.setPath(pathList);

			}
			locConList.add(locConBean);
			locConBean.setOptions(optList);
		}
		monitorBean.setMain(locConList);
	}

	public void removeOptionFromRow_LocCon(TravelMonitoring bean,
			HttpServletRequest request) {
		String pathName = request.getParameter("pathName");
		int maxMainCount = Integer.parseInt(request
				.getParameter("maxMainCount"));
		int maxOptCount = Integer.parseInt(request.getParameter("maxOptCount"));
		String userType = request.getParameter("userType");
		boolean readOnly = Boolean
				.valueOf(request.getParameter("readOnlyFlag"));
		LinkedMap hMap = getTravelModeClass();
		bean.setUserType(userType);
		bean.setReadOnlyFlag(readOnly);
		String[] temp = pathName.split("_");
		int mainCount = Integer.parseInt(temp[0]);
		int optCount = Integer.parseInt(temp[1]);
		int rowCount = Integer.parseInt(temp[2]);

		ArrayList locConList = new ArrayList();
		for (int i = 0; i < maxMainCount; i++) {
			TravelMonitoring locConBean = new TravelMonitoring();
			ArrayList optList = new ArrayList();
			locConBean.setLocConCity(request.getParameter("locConCity_"
					+ (i + 1)));
			locConBean.setUserType(userType);
			locConBean.setReadOnlyFlag(Boolean.valueOf(request
					.getParameter("readOnlyFlag" + (i + 1))));

			for (int j = 0; j < maxOptCount; j++) {
				TravelMonitoring optBean = new TravelMonitoring();
				optBean.setUserType(userType);
				optBean.setReadOnlyFlag(readOnly);
				ArrayList pathList = new ArrayList();
				String[][] dataObj = getDataFromView_LocCon(request, i, j);
				if (dataObj == null || dataObj.length <= 0)
					break;
				optBean.setLocCancelFlag(request.getParameter("locCancelFlag"
						+ (i + 1) + "_" + (j + 1)));
				boolean oneRowFlag = false;
				for (int k = 0; k < dataObj.length; k++) {
					if ((i + 1) == mainCount && (j + 1) == optCount
							&& (k + 1) == rowCount) {
						if (dataObj.length == 1)
							oneRowFlag = true;
						continue;
					}
					TravelMonitoring pathBean = new TravelMonitoring();

					pathBean.setSource(dataObj[k][0]);
					pathBean.setTravelDate(dataObj[k][1]);
					pathBean.setTravelModeClass(dataObj[k][2]);
					pathBean.setLocConVehNo(dataObj[k][3]);
					pathBean.setLocConConPer(dataObj[k][4]);
					pathBean.setLocConConNo(dataObj[k][5]);
					pathBean.setLocConPicPer(dataObj[k][6]);
					pathBean.setLocConFrmTime(dataObj[k][7]);
					pathBean.setLocConToTime(dataObj[k][8]);
					pathBean.setLocConPicPlace(dataObj[k][9]);
					pathBean.setLocConToriffCost(dataObj[k][10]);
					pathBean.setMonitorId(dataObj[k][11]);
					pathBean.setLocConvCode(dataObj[k][12]);
					pathBean.setLocDtlId(dataObj[k][13]);
					pathBean.setSelFlag(dataObj[k][14]);
					pathBean.setReadOnlyFlag(readOnly);
					pathBean.setUserType(userType);
					pathBean.setModeClassMap(hMap);
					pathBean.setLocCancelFlag(optBean.getLocCancelFlag());
					pathList.add(pathBean);
				}
				if (!oneRowFlag) {
					optList.add(optBean);
					optBean.setPath(pathList);
				}
			}
			locConList.add(locConBean);
			locConBean.setOptions(optList);
		}
		bean.setMain(locConList);
	}

	public String[][] getDataFromView_LocCon(HttpServletRequest request, int i,
			int j) {
		String[] srcCity = request.getParameterValues("source_" + (i + 1) + "_"
				+ (j + 1));
		String[] date = request.getParameterValues("travelDate_" + (i + 1)
				+ "_" + (j + 1));
		String[] mode = request.getParameterValues("mode_" + (i + 1) + "_"
				+ (j + 1));
		String[] vehNum = request.getParameterValues("locConVehNo_" + (i + 1)
				+ "_" + (j + 1));
		String[] conPerson = request.getParameterValues("locConConPer_"
				+ (i + 1) + "_" + (j + 1));
		String[] contactNum = request.getParameterValues("locConConNo_"
				+ (i + 1) + "_" + (j + 1));
		String[] picPerson = request.getParameterValues("locConPicPer_"
				+ (i + 1) + "_" + (j + 1));
		String[] fromTime = request.getParameterValues("locConFrmTime_"
				+ (i + 1) + "_" + (j + 1));
		String[] toTime = request.getParameterValues("locConToTime_" + (i + 1)
				+ "_" + (j + 1));
		String[] picPlace = request.getParameterValues("locConPicPlace_"
				+ (i + 1) + "_" + (j + 1));
		String[] tariffCost = request.getParameterValues("locConToriffCost_"
				+ (i + 1) + "_" + (j + 1));
		String[] monitorId = request.getParameterValues("monitorId_" + (i + 1)
				+ "_" + (j + 1));
		String[] locConvCode = request.getParameterValues("locConvCode_"
				+ (i + 1) + "_" + (j + 1));
		String[] locDtlId = request.getParameterValues("locDtlId_" + (i + 1)
				+ "_" + (j + 1));
		String[] selFlag = request.getParameterValues("selFlag_" + (i + 1)
				+ "_" + (j + 1));
		String[][] dataObj = null;

		if (srcCity != null && srcCity.length > 0) {
			dataObj = new String[srcCity.length][15];
			for (int k = 0; k < dataObj.length; k++) {
				dataObj[k][0] = srcCity[k];
				dataObj[k][1] = date[k];
				dataObj[k][2] = mode[k];
				dataObj[k][3] = vehNum[k];
				dataObj[k][4] = conPerson[k];
				dataObj[k][5] = contactNum[k];
				dataObj[k][6] = picPerson[k];
				dataObj[k][7] = fromTime[k];
				dataObj[k][8] = toTime[k];
				dataObj[k][9] = picPlace[k];
				dataObj[k][10] = tariffCost[k];
				dataObj[k][11] = monitorId[k];
				dataObj[k][12] = locConvCode[k];
				dataObj[k][13] = locDtlId[k];
				dataObj[k][14] = selFlag[k];

			}
		}
		return dataObj;
	}

	public void addOption_LocCon(TravelMonitoring monitorBean,
			HttpServletRequest request) {
		String pathName = request.getParameter("pathName");
		int maxMainCount = Integer.parseInt(request
				.getParameter("maxMainCount"));
		int maxOptCount = Integer.parseInt(request.getParameter("maxOptCount"));
		String userType = request.getParameter("userType");
		boolean readOnly = Boolean
				.valueOf(request.getParameter("readOnlyFlag"));
		LinkedMap hMap = getTravelModeClass();
		monitorBean.setUserType(userType);
		monitorBean.setReadOnlyFlag(readOnly);
		String[] temp = pathName.split("_");
		int mainCount = Integer.parseInt(temp[0]);
		ArrayList locConList = new ArrayList();
		String[][] dataObj = null;
		String[][] cloneObj = null;
		for (int i = 0; i < maxMainCount; i++) {
			TravelMonitoring locConBean = new TravelMonitoring();
			ArrayList optList = new ArrayList();
			locConBean.setLocConCity(request.getParameter("locConCity_"
					+ (i + 1)));
			locConBean.setUserType(userType);
			locConBean.setReadOnlyFlag(Boolean.valueOf(request
					.getParameter("readOnlyFlag" + (i + 1))));
			for (int j = 0; j < maxOptCount; j++) {
				TravelMonitoring optBean = new TravelMonitoring();
				optBean.setUserType(userType);
				optBean.setReadOnlyFlag(readOnly);
				ArrayList pathList = new ArrayList();

				dataObj = getDataFromView_LocCon(request, i, j);
				if (dataObj == null || dataObj.length <= 0)
					break;
				optBean.setLocCancelFlag(request.getParameter("locCancelFlag"
						+ (i + 1) + "_" + (j + 1)));
				cloneObj = dataObj.clone();
				for (int k = 0; k < dataObj.length + 1; k++) {
					TravelMonitoring pathBean = new TravelMonitoring();
					if (k < dataObj.length) {
						pathBean.setSource(dataObj[k][0]);
						pathBean.setTravelDate(dataObj[k][1]);
						pathBean.setTravelModeClass(dataObj[k][2]);
						pathBean.setLocConVehNo(dataObj[k][3]);
						pathBean.setLocConConPer(dataObj[k][4]);
						pathBean.setLocConConNo(dataObj[k][5]);
						pathBean.setLocConPicPer(dataObj[k][6]);
						pathBean.setLocConFrmTime(dataObj[k][7]);
						pathBean.setLocConToTime(dataObj[k][8]);
						pathBean.setLocConPicPlace(dataObj[k][9]);
						pathBean.setLocConToriffCost(dataObj[k][10]);
						pathBean.setMonitorId(dataObj[k][11]);
						pathBean.setLocConvCode(dataObj[k][12]);
						pathBean.setLocDtlId(dataObj[k][13]);
						pathBean.setSelFlag(dataObj[k][14]);
						pathBean.setReadOnlyFlag(readOnly);
						pathBean.setUserType(userType);
						pathBean.setModeClassMap(hMap);
						pathBean.setLocCancelFlag(optBean.getLocCancelFlag());
						pathList.add(pathBean);
					}
				}
				optBean.setSrNo(1);
				optList.add(optBean);
				optBean.setPath(pathList);

			}
			if ((i + 1) == mainCount) {
				TravelMonitoring pathBean = new TravelMonitoring();
				pathBean.setReadOnlyFlag(readOnly);
				pathBean.setUserType(userType);
				pathBean.setModeClassMap(hMap);
				if (cloneObj != null) {
					pathBean.setSource(cloneObj[0][0]);
					pathBean.setTravelDate(cloneObj[0][1]);
					pathBean.setTravelModeClass(cloneObj[0][2]);
					pathBean.setLocConPicPlace(cloneObj[0][9]);
					pathBean.setLocConFrmTime(cloneObj[0][7]);
					pathBean.setLocConToTime(cloneObj[0][8]);
				}
				TravelMonitoring optBean = new TravelMonitoring();
				optBean.setUserType(userType);
				ArrayList pathList = new ArrayList();
				pathList.add(pathBean);
				optList.add(optBean);
				optBean.setPath(pathList);
			}
			locConBean.setSrNo(1);
			locConList.add(locConBean);
			locConBean.setOptions(optList);
		}
		monitorBean.setMain(locConList);
	}

	public void removeOption_LocCon(TravelMonitoring bean,
			HttpServletRequest request) {
		String pathName = request.getParameter("pathName");
		int maxMainCount = Integer.parseInt(request
				.getParameter("maxMainCount"));
		int maxOptCount = Integer.parseInt(request.getParameter("maxOptCount"));
		String[] temp = pathName.split("_");
		String userType = request.getParameter("userType");
		boolean readOnly = Boolean
				.valueOf(request.getParameter("readOnlyFlag"));
		LinkedMap hMap = getTravelModeClass();
		bean.setUserType(userType);
		bean.setReadOnlyFlag(readOnly);
		int mainCount = Integer.parseInt(temp[0]);
		int optCount = Integer.parseInt(temp[1]);
		ArrayList locConList = new ArrayList();
		for (int i = 0; i < maxMainCount; i++) {
			TravelMonitoring locConBean = new TravelMonitoring();
			ArrayList optList = new ArrayList();
			locConBean.setLocConCity(request.getParameter("locConCity_"
					+ (i + 1)));
			locConBean.setUserType(userType);
			locConBean.setReadOnlyFlag(Boolean.valueOf(request
					.getParameter("readOnlyFlag" + (i + 1))));
			for (int j = 0; j < maxOptCount; j++) {
				if ((i + 1) == mainCount && (j + 1) == optCount)
					continue;
				TravelMonitoring optBean = new TravelMonitoring();
				optBean.setUserType(userType);
				optBean.setReadOnlyFlag(readOnly);
				ArrayList pathList = new ArrayList();
				String[][] dataObj = getDataFromView_LocCon(request, i, j);
				if (dataObj == null || dataObj.length <= 0)
					break;
				optBean.setLocCancelFlag(request.getParameter("locCancelFlag"
						+ (i + 1) + "_" + (j + 1)));
				for (int k = 0; k < dataObj.length + 1; k++) {
					TravelMonitoring pathBean = new TravelMonitoring();
					if (k < dataObj.length) {
						pathBean.setSource(dataObj[k][0]);
						pathBean.setTravelDate(dataObj[k][1]);
						pathBean.setTravelModeClass(dataObj[k][2]);
						pathBean.setLocConVehNo(dataObj[k][3]);
						pathBean.setLocConConPer(dataObj[k][4]);
						pathBean.setLocConConNo(dataObj[k][5]);
						pathBean.setLocConPicPer(dataObj[k][6]);
						pathBean.setLocConFrmTime(dataObj[k][7]);
						pathBean.setLocConToTime(dataObj[k][8]);
						pathBean.setLocConPicPlace(dataObj[k][9]);
						pathBean.setLocConToriffCost(dataObj[k][10]);
						pathBean.setMonitorId(dataObj[k][11]);
						pathBean.setLocConvCode(dataObj[k][12]);
						pathBean.setLocDtlId(dataObj[k][13]);
						pathBean.setSelFlag(dataObj[k][14]);
						pathBean.setReadOnlyFlag(readOnly);
						pathBean.setUserType(userType);
						pathBean.setModeClassMap(hMap);
						pathBean.setLocCancelFlag(optBean.getLocCancelFlag());
						pathList.add(pathBean);
					}
				}
				optList.add(optBean);
				optBean.setPath(pathList);

			}
			locConList.add(locConBean);
			locConBean.setOptions(optList);
		}
		bean.setMain(locConList);
	}

	public void save_LocCon(TravelMonitoring bean, HttpServletRequest request) {

		try {
			String applId = request.getParameter("empApplId");
			int totCount = 0;
			String[] monitorId = null;
			String insrtMonId = request.getParameter("monitorId");
			String updateQry = " UPDATE TMS_MONITORING SET MTR_LOCAL_SCH_COMM = '"
					+ bean.getSchComments()
					+ "', MTR_LOCAL_APP_COMM = '"
					+ bean.getAplComments()
					+ "'"
					+ " WHERE TMS_MONITOR_ID = "
					+ insrtMonId;

			getSqlModel().singleExecute(updateQry);

			if (request.getParameter("userType").equals("APL")
					|| request.getParameter("userType").equals("APR"))
				return;
			Object[][] saveObj = new Object[Integer.parseInt(request
					.getParameter("totCount"))][16];

			String maxDtlCodeQry = "SELECT NVL(MAX(LOCCONV_DTL_CODE),0)+1 FROM TMS_SUGG_LOC_CONV";
			String[] opCount = request.getParameterValues("radioHidden");

			Object[][] maxCodeObj = getSqlModel()
					.getSingleResult(maxDtlCodeQry);

			int maxDtlCode = Integer.parseInt(String.valueOf(maxCodeObj[0][0]));
			int locConCnt = Integer.parseInt(request.getParameter("locConCnt"));

			for (int i = 0; i < locConCnt; i++) {
				int optionCount = Integer.parseInt(opCount[i]);
				int groupCode = 1;

				for (int j = 0; j < optionCount; j++) {

					String[] srcCity = request.getParameterValues("source_"
							+ (i + 1) + "_" + (j + 1));
					String[] date = request.getParameterValues("travelDate_"
							+ (i + 1) + "_" + (j + 1));
					String[] mode = request.getParameterValues("mode_"
							+ (i + 1) + "_" + (j + 1));
					String[] vehNum = request.getParameterValues("locConVehNo_"
							+ (i + 1) + "_" + (j + 1));
					String[] conPerson = request
							.getParameterValues("locConConPer_" + (i + 1) + "_"
									+ (j + 1));
					String[] contactNum = request
							.getParameterValues("locConConNo_" + (i + 1) + "_"
									+ (j + 1));
					String[] picPerson = request
							.getParameterValues("locConPicPer_" + (i + 1) + "_"
									+ (j + 1));
					String[] fromTime = request
							.getParameterValues("locConFrmTime_" + (i + 1)
									+ "_" + (j + 1));
					String[] toTime = request
							.getParameterValues("locConToTime_" + (i + 1) + "_"
									+ (j + 1));
					String[] picPlace = request
							.getParameterValues("locConPicPlace_" + (i + 1)
									+ "_" + (j + 1));
					String[] tariffCost = request
							.getParameterValues("locConToriffCost_" + (i + 1)
									+ "_" + (j + 1));
					monitorId = request.getParameterValues("monitorId_"
							+ (i + 1) + "_" + (j + 1));
					String[] locConCode = request
							.getParameterValues("locConvCode_" + (i + 1) + "_"
									+ (j + 1));
					String[] locDtlId = request.getParameterValues("locDtlId_"
							+ (i + 1) + "_" + (j + 1));
					String[] selFlag = request.getParameterValues("selFlag_"
							+ (i + 1) + "_" + (j + 1));

					if (srcCity != null && srcCity.length > 0) {
						int order = 1;
						for (int k = 0; k < srcCity.length; k++) {

							saveObj[totCount][0] = insrtMonId;
							saveObj[totCount][1] = groupCode;
							saveObj[totCount][2] = locConCode[k];
							if (!locDtlId[k].equals(""))
								saveObj[totCount][3] = locDtlId[k];
							else
								saveObj[totCount][3] = maxDtlCode++;
							saveObj[totCount][4] = srcCity[k].trim();
							saveObj[totCount][5] = date[k].trim();
							saveObj[totCount][6] = mode[k].trim();
							saveObj[totCount][7] = vehNum[k].trim();
							saveObj[totCount][8] = conPerson[k].trim();
							saveObj[totCount][9] = contactNum[k].trim();
							saveObj[totCount][10] = picPerson[k].trim();
							saveObj[totCount][11] = fromTime[k].trim();
							saveObj[totCount][12] = toTime[k].trim();
							saveObj[totCount][13] = picPlace[k].trim();
							saveObj[totCount][14] = tariffCost[k].trim();
							saveObj[totCount][15] = order++;
							totCount++;
						}
						groupCode++;
					}
				}
			}

			getSqlModel().singleExecute(
					"DELETE FROM TMS_SUGG_LOC_CONV WHERE MONITOR_ID = "
							+ insrtMonId);

			String insertQry = "INSERT INTO TMS_SUGG_LOC_CONV ("
					+ "  MONITOR_ID,LOCCONV_GROUP_CODE,LOCCONV_CONV_CODE,LOCCONV_DTL_CODE, LOCCONV_SOURCE,"
					+ "  LOCCONV_DATE, LOCCONV_TRAVELMODE, LOCCONV_TRAVELMODE_NO, "
					+ "  LOCCONV_CONTACTPERSON, LOCCONV_CONTACTNO, LOCCONV_PICKUPPERSON,"
					+ "  LOCCONV_FROM_TIME,LOCCONV_TO_TIME, LOCCONV_PICKUPPLACE, LOCCONV_TARIFFCOST, "
					+ "  LOCCONV_ORDER) "
					+ " VALUES(?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?,?,?,?,?,?,?,?,?)";
			getSqlModel().singleExecute(insertQry, saveObj);
			updateQry = " UPDATE TMS_MONITORING SET MTR_LOCAL_AVSTATUS = 'SO'"
					+ " WHERE TMS_MONITOR_ID = "
					+ request.getParameter("monitorId");

			getSqlModel().singleExecute(updateQry);

			try {
				updateCommentsTrail(bean.getUserEmpId(), request
						.getParameter("applicationId"), request
						.getParameter("empApplId"), request
						.getParameter("userType"), bean.getSchComments(), bean
						.getLocConStatus());
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				String empAppQry = "SELECT APPL_FOR_FLAG FROM TMS_APPLICATION WHERE APPL_ID = "
						+ request.getParameter("applicationId");
				Object[][] appType = getSqlModel().getSingleResult(empAppQry);

				Object[][] tempData = checkMailEvent(58);
				if (tempData == null || tempData.length == 0)
					return;
				if (String.valueOf(tempData[0][0]).equals("Y")) {
					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template.setEmailTemplate(String.valueOf(tempData[0][3]));
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);
					templateQuery1.setParameter(1, bean.getUserEmpId());

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);
					templateQuery2.setParameter(1, String.valueOf(bean
							.getIniEmpId()));

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, "Conveyance");
					templateQuery3.setParameter(2, String.valueOf(bean
							.getApplicationId()));
					templateQuery3.setParameter(3, String.valueOf(bean
							.getEmpApplId()));
					templateQuery3.setParameter(4, "Conveyance");
					templateQuery3.setParameter(5, String.valueOf(bean
							.getApplicationId()));
					templateQuery3.setParameter(6, String.valueOf(bean
							.getEmpApplId()));

					template.configMailAlert();
					if (!String.valueOf(appType[0][0]).equals("G")
							&& !String.valueOf(bean.getIniEmpId()).equals(
									bean.getEmpId())) {
						template.sendApplicationMailToKeepInfo(String
								.valueOf(bean.getEmpId()));
					}

					template.sendApplicationMail();
					template.clearParameters();
					template.terminate();
				}
			} catch (Exception e) {
				logger
						.error("Error while sending mail after saving loc con Options : "
								+ e);
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void accept_LocCon(TravelMonitoring bean, HttpServletRequest request) {
		try {
			String status = "CE";
			String updateQry = " UPDATE TMS_SUGG_LOC_CONV SET LOCCONV_SEL_FLAG = 'N' "
					+ " WHERE MONITOR_ID = "
					+ request.getParameter("monitorId");
			getSqlModel().singleExecute(updateQry);

			updateQry = " UPDATE TMS_SUGG_LOC_CONV SET LOCCONV_SEL_FLAG = 'Y' "
					+ " WHERE LOCCONV_DTL_CODE IN ("
					+ request.getParameter("dtlId") + ")";
			getSqlModel().singleExecute(updateQry);

			updateQry = " UPDATE TMS_MONITORING SET MTR_LOCAL_APP_COMM = '"
					+ bean.getAplComments() + "'," + " MTR_LOCAL_AVSTATUS = '"
					+ status + "'" + " WHERE TMS_MONITOR_ID = "
					+ request.getParameter("monitorId");

			getSqlModel().singleExecute(updateQry);

			try {
				updateCommentsTrail(bean.getUserEmpId(), request
						.getParameter("applicationId"), request
						.getParameter("empApplId"), request
						.getParameter("userType"), bean.getAplComments(), bean
						.getLocConStatus());
			} catch (Exception e) {
				// TODO: handle exception
			}

			try {
				Object[][] tempData = checkMailEvent(60);
				if (tempData == null || tempData.length == 0)
					return;
				if (String.valueOf(tempData[0][0]).equals("Y")) {

					String schEmpCode = " SELECT SCHDTL_SUBSCHLAR_ECODE,DESK_SCH_ECODE FROM TMS_SCH_DTL "
							+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_ID = TMS_SCH_DTL.SCH_DESK_ID) "
							+ " WHERE DESK_APPL_ID = "
							+ request.getParameter("applicationId")
							+ " AND DESK_APPL_CODE = "
							+ request.getParameter("empApplId")
							+ " AND  (TMS_SCH_DESK.DESK_LOCAL_ASSIGN = 'Y' AND  TMS_SCH_DTL.SCHDTL_LOCAL_ASSIGN = 'Y') ";
					Object[][] schEmpIdObj = getSqlModel().getSingleResult(
							schEmpCode);

					if (schEmpIdObj == null || schEmpIdObj.length == 0)
						return;

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template.setEmailTemplate(String.valueOf(tempData[0][3]));
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);
					templateQuery1.setParameter(1, String.valueOf(bean
							.getIniEmpId()));

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);
					templateQuery2.setParameter(1, String
							.valueOf(schEmpIdObj[0][1]));

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, String.valueOf(bean
							.getApplicationId()));
					templateQuery3.setParameter(2, String.valueOf(bean
							.getEmpApplId()));
					templateQuery3.setParameter(3, String.valueOf(bean
							.getApplicationId()));
					templateQuery3.setParameter(4, String.valueOf(bean
							.getEmpApplId()));
					template.configMailAlert();
					if (!String.valueOf(schEmpIdObj[0][0]).equals(
							String.valueOf(schEmpIdObj[0][1]))) {
						template.sendApplicationMailToKeepInfo(String
								.valueOf(schEmpIdObj[0][0]));
					}
					template.sendApplicationMail();
					template.clearParameters();
					template.terminate();
				}

			} catch (Exception e) {
				logger
						.error("Error while sending mail after local Travel Options : "
								+ e);
			}

			try {
				startBookingMail(bean);
			} catch (Exception e) {
				logger.error("Error in start booking mail");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void decline_LocCon(TravelMonitoring bean, HttpServletRequest request) {
		logger.info("Declining the local-----");
		String updateQry = " UPDATE TMS_SUGG_LOC_CONV SET LOCCONV_SEL_FLAG = 'N' ";
		boolean flag = false;
		if (!request.getParameter("dtlId").equals("")) {
			updateQry += ",LOCCONV_REJ_FLAG = 'Y'";
		}
		updateQry += " WHERE MONITOR_ID = " + request.getParameter("monitorId");

		if (!request.getParameter("dtlId").equals("")) {
			updateQry += " AND LOCCONV_DTL_CODE IN ("
					+ request.getParameter("dtlId") + ")";
		}
		getSqlModel().singleExecute(updateQry);

		updateQry = " UPDATE TMS_MONITORING SET MTR_LOCAL_APP_COMM = '"
				+ bean.getAplComments() + "' ,MTR_LOCAL_APR_COMM = '"
				+ bean.getAprComments() + "',";
		if (!request.getParameter("dtlId").equals("")) {
			updateQry += " MTR_LOCAL_AVSTATUS = 'SO'";
		} else {
			updateQry += " MTR_LOCAL_AVSTATUS = 'RE'";
			flag = true;
		}

		if (flag) {
			try {
				updateCommentsTrail(bean.getUserEmpId(), request
						.getParameter("applicationId"), request
						.getParameter("empApplId"), request
						.getParameter("userType"), bean.getAplComments(), bean
						.getLocConStatus());
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			try {
				updateCommentsTrail(bean.getUserEmpId(), request
						.getParameter("applicationId"), request
						.getParameter("empApplId"), request
						.getParameter("userType"), bean.getAprComments(), bean
						.getLocConStatus());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		updateQry += " WHERE TMS_MONITOR_ID = "
				+ request.getParameter("monitorId");
		getSqlModel().singleExecute(updateQry);

		if (flag) {
			try {
				Object[][] tempData = checkMailEvent(59);
				if (tempData == null || tempData.length == 0)
					return;
				if (String.valueOf(tempData[0][0]).equals("Y")) {

					String schEmpCode = " SELECT SCHDTL_SUBSCHLAR_ECODE,DESK_SCH_ECODE FROM TMS_SCH_DTL "
							+ " INNER JOIN TMS_SCH_DESK ON(TMS_SCH_DESK.DESK_ID = TMS_SCH_DTL.SCH_DESK_ID) "
							+ " WHERE DESK_APPL_ID = "
							+ request.getParameter("applicationId")
							+ " AND DESK_APPL_CODE = "
							+ request.getParameter("empApplId")
							+ " AND  (TMS_SCH_DESK.DESK_LODGE_ASSIGN = 'Y' AND  TMS_SCH_DTL.SCHDTL_LODGE_ASSIGN = 'Y') ";
					Object[][] schEmpIdObj = getSqlModel().getSingleResult(
							schEmpCode);

					if (schEmpIdObj == null || schEmpIdObj.length == 0)
						return;

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template.setEmailTemplate(String.valueOf(tempData[0][3]));
					template.getTemplateQueries();

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);
					templateQuery1.setParameter(1, String.valueOf(bean
							.getIniEmpId()));

					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);
					templateQuery2.setParameter(1, String
							.valueOf(schEmpIdObj[0][1]));

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, String.valueOf(bean
							.getApplicationId()));
					templateQuery3.setParameter(2, String.valueOf(bean
							.getEmpApplId()));
					templateQuery3.setParameter(3, String.valueOf(bean
							.getApplicationId()));
					templateQuery3.setParameter(4, String.valueOf(bean
							.getEmpApplId()));
					template.configMailAlert();
					if (!String.valueOf(schEmpIdObj[0][0]).equals(
							String.valueOf(schEmpIdObj[0][1]))) {
						template.sendApplicationMailToKeepInfo(String
								.valueOf(schEmpIdObj[0][0]));
					}
					template.sendApplicationMail();
					template.clearParameters();
					template.terminate();
				}
			} catch (Exception e) {
				logger
						.error("Error while sending mail after accepting accom Options : "
								+ e);
			}
		}

	}

	public void cancel(HttpServletRequest request, TravelMonitoring bean) {
		try {
			String monitorId = request.getParameter("monitorId");
			String type = request.getParameter("type");
			String updateQry = "UPDATE TMS_MONITORING SET ";
			String status = "";
			if (type.equals("T")) {
				updateQry += "MTR_TVL_AVA_STATUS";
				status = bean.getTvlStatus();
			} else if (type.equals("L")) {
				updateQry += "MTR_LOCAL_AVSTATUS";
				status = bean.getLocConStatus();
			} else if (type.equals("A")) {
				updateQry += "MTR_ACC_AVSTATUS";
				status = bean.getAccStatus();
			}
			updateQry += " = 'CN' WHERE 	TMS_MONITOR_ID = " + monitorId;
			getSqlModel().singleExecute(updateQry);

			try {
				updateCommentsTrail(bean.getUserEmpId(), request
						.getParameter("applicationId"), request
						.getParameter("empApplId"), request
						.getParameter("userType"), bean.getAplComments(),
						status);
			} catch (Exception e) {
				// TODO: handle exception
			}

			checkRecordFinalize(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean checkRecordFinalize(HttpServletRequest request) {
		String query = "SELECT MTR_TVL_AVA_STATUS,MTR_ACC_AVSTATUS,MTR_LOCAL_AVSTATUS FROM TMS_MONITORING WHERE APPL_CODE = "
				+ request.getParameter("empApplId")
				+ " AND APPL_ID = "
				+ request.getParameter("applicationId");
		Object[][] selObj = getSqlModel().getSingleResult(query);
		boolean flag = true;
		if (selObj != null && selObj.length > 0) {
			if ((String.valueOf(selObj[0][0]).equals("FI")
					|| String.valueOf(selObj[0][0]).equals("CN")
					|| String.valueOf(selObj[0][0]).trim().equals("N")
					//|| String.valueOf(selObj[0][0]).trim().equals("CC") 
					|| String
					.valueOf(selObj[0][0]).trim().equals("CD"))
					&& (String.valueOf(selObj[0][1]).equals("FI")
							|| String.valueOf(selObj[0][1]).equals("CN")
							|| String.valueOf(selObj[0][1]).trim().equals("N")
							//|| String.valueOf(selObj[0][1]).trim().equals("CC")
							|| String
							.valueOf(selObj[0][1]).trim().equals("CD"))
					&& (String.valueOf(selObj[0][2]).equals("FI")
							|| String.valueOf(selObj[0][2]).equals("CN")
							|| String.valueOf(selObj[0][2]).trim().equals("N")
							//|| String.valueOf(selObj[0][2]).trim().equals("CC") 
							|| String.valueOf(selObj[0][2]).trim().equals("CD"))) {
				try {
					if (!(request.getParameter("tvlStatus").equals("SB")
							|| request.getParameter("accStatus").equals("SB") || request
							.getParameter("locConStatus").equals("SB"))) {
						flag = false;
					}
				} catch (Exception e) {
					logger
							.error("Exception caught for travel/accom/local status : "
									+ e);
					flag = true;
				}
				if (flag) {
					query = "UPDATE TMS_SCH_DESK SET DESK_STATUS = 'S' WHERE DESK_APPL_CODE = "
							+ request.getParameter("empApplId")
							+ " AND DESK_APPL_ID = "
							+ request.getParameter("applicationId");
					getSqlModel().singleExecute(query);
					query = "UPDATE TMS_APP_EMPDTL SET APPL_EMP_TRAVEL_APPLSTATUS = 'S' WHERE APPL_CODE = "
							+ request.getParameter("empApplId")
							+ " AND APPL_ID = "
							+ request.getParameter("applicationId")
							+ " AND APPL_EMP_TRAVEL_APPLSTATUS !='FT' AND APPL_EMP_TRAVEL_APPLSTATUS !='FS'";
					getSqlModel().singleExecute(query);
					query = "UPDATE TMS_APP_GUEST_DTL SET GUEST_TRAVEL_APPLSTATUS = 'S' WHERE APPL_CODE = "
							+ request.getParameter("empApplId")
							+ " AND APPL_ID = "
							+ request.getParameter("applicationId")
							+ " AND GUEST_TRAVEL_APPLSTATUS !='FT' AND GUEST_TRAVEL_APPLSTATUS !='FS'";
					getSqlModel().singleExecute(query);
				} else {
					if ((String.valueOf(selObj[0][0]).equals("CN")
							|| String.valueOf(selObj[0][0]).trim().equals("N")
							//|| String.valueOf(selObj[0][0]).trim().equals("CC")
							|| String
							.valueOf(selObj[0][0]).trim().equals("CD"))
							&& (String.valueOf(selObj[0][1]).equals("CN")
									|| String.valueOf(selObj[0][1]).trim()
											.equals("N")||
									//|| String.valueOf(selObj[0][1]).trim()
											//.equals("CC") || 
											String.valueOf(
									selObj[0][1]).trim().equals("CD"))
							&& (String.valueOf(selObj[0][2]).equals("CN")
									|| String.valueOf(selObj[0][2]).trim()
											.equals("N")||
									//|| String.valueOf(selObj[0][2]).trim()
											//.equals("CC") || 
											String.valueOf(
									selObj[0][2]).trim().equals("CD"))) {
						query = "UPDATE TMS_SCH_DESK SET DESK_STATUS = 'NC' WHERE DESK_APPL_CODE = "
								+ request.getParameter("empApplId")
								+ " AND DESK_APPL_ID = "
								+ request.getParameter("applicationId");
						getSqlModel().singleExecute(query);
					}
				}
			}
		}
		return flag;
	}

	public Object[][] checkMailEvent(int eventCode) {
		try {
			String tempSql = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS "
					+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
					+ " WHERE EVENT_CODE=" + eventCode;
			Object[][] tempData = getSqlModel().getSingleResult(tempSql);
			return tempData;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean isStartBookingEnabled(String applCode, String applId) {
		try {
			String statQry = " SELECT TMS_APP_EMPDTL.APPL_TVL_CANCEL_STATUS,TMS_APP_EMPDTL.APPL_ACC_CANCEL_STATUS, TMS_APP_EMPDTL.APPL_LOC_CANCEL_STATUS,TMS_MONITORING.MTR_TVL_AVA_STATUS,TMS_MONITORING.MTR_ACC_AVSTATUS, "
					+ " TMS_MONITORING.MTR_LOCAL_AVSTATUS "
					+ " FROM TMS_APP_EMPDTL "
					+ " INNER JOIN TMS_MONITORING ON (TMS_APP_EMPDTL.APPL_ID=TMS_MONITORING.APPL_ID AND TMS_APP_EMPDTL.APPL_CODE=TMS_MONITORING.APPL_CODE) "
					+ " WHERE TMS_APP_EMPDTL.APPL_CODE="
					+ applCode
					+ " AND TMS_APP_EMPDTL.APPL_ID="
					+ applId
					+ " UNION "
					+ " SELECT TMS_APP_GUEST_DTL.APPL_TVL_CANCEL_STATUS,TMS_APP_GUEST_DTL.APPL_ACC_CANCEL_STATUS, TMS_APP_GUEST_DTL.APPL_LOC_CANCEL_STATUS,TMS_MONITORING.MTR_TVL_AVA_STATUS,TMS_MONITORING.MTR_ACC_AVSTATUS, "
					+ " TMS_MONITORING.MTR_LOCAL_AVSTATUS "
					+ " FROM TMS_APP_GUEST_DTL "
					+ " INNER JOIN TMS_MONITORING ON (TMS_APP_GUEST_DTL.APPL_ID=TMS_MONITORING.APPL_ID AND TMS_APP_GUEST_DTL.APPL_CODE=TMS_MONITORING.APPL_CODE) "
					+ " WHERE TMS_APP_GUEST_DTL.APPL_CODE="
					+ applCode
					+ " AND TMS_APP_GUEST_DTL.APPL_ID=" + applId;
			Object[][] statObj = getSqlModel().getSingleResult(statQry);

			if (String.valueOf(statObj[0][0]).equals("PC")
					|| String.valueOf(statObj[0][1]).equals("PC")
					|| String.valueOf(statObj[0][2]).equals("PC"))
				return false;

			if ((String.valueOf(statObj[0][3]).equals("CE")
					|| String.valueOf(statObj[0][3]).equals("CN")
					|| String.valueOf(statObj[0][3]).trim().equals("N") || String
					.valueOf(statObj[0][3]).trim().equals("CC"))
					&& (String.valueOf(statObj[0][4]).equals("CE")
							|| String.valueOf(statObj[0][4]).equals("CN")
							|| String.valueOf(statObj[0][4]).trim().equals("N") || String
							.valueOf(statObj[0][4]).trim().equals("CC"))
					&& (String.valueOf(statObj[0][5]).equals("CE")
							|| String.valueOf(statObj[0][5]).equals("CN")
							|| String.valueOf(statObj[0][5]).trim().equals("N") || String
							.valueOf(statObj[0][5]).trim().equals("CC")))
				return true;

			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public void sendMailToAppl(TravelMonitoring bean, String[] jourFileName,
			String[] lodgFileName, String[] locFileName) {
		try {
			String tempSql = "SELECT EVENT_OPTION_FLAG, EVENT_TEMPLATE_CODE,EVENT_CODE,NVL(TEMPLATE_NAME,' ') FROM  HRMS_MAIL_EVENTS "
					+ " INNER JOIN HRMS_EMAILTEMPLATE_HDR ON(EVENT_TEMPLATE_CODE= TEMPLATE_ID) "
					+ " WHERE EVENT_CODE=71";
			Object[][] tempData = getSqlModel().getSingleResult(tempSql);
			logger.info("Inside sendMail2Appl----");

			String poolName = String.valueOf(session
					.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			if (tempData != null && tempData.length > 0) {

				if (String.valueOf(tempData[0][0]).equals("Y")) {
					logger.info("Inside sendMail2Appl--- mail-");

					EmailTemplateBody template = new EmailTemplateBody();
					template.initiate(context, session);
					template.setEmailTemplate(String.valueOf(tempData[0][3]));
					template.getTemplateQueries();

					ResourceBundle boundle = ResourceBundle
							.getBundle("globalMessages");
					String path = boundle.getString("data_path") + "/TMS/"
							+ poolName + "/Tickets/";
					int attachLen = 0;
					if (jourFileName != null && jourFileName.length > 0) {
						attachLen = attachLen + jourFileName.length;
					}

					if (locFileName != null && locFileName.length > 0) {
						attachLen = attachLen + locFileName.length;
					}

					if (lodgFileName != null && lodgFileName.length > 0) {
						attachLen = attachLen + lodgFileName.length;
					}

					String[] attachPath = new String[attachLen];
					int counter = 0;
					if (jourFileName != null && jourFileName.length > 0) {
						logger.info("jourFileName.length : "
								+ jourFileName.length);
						for (int i = 0; i < jourFileName.length; i++) {
							if (jourFileName[i] != null
									&& !jourFileName[i].equals("null")
									&& !jourFileName[i].equals("")) {
								attachPath[counter] = path + jourFileName[i];
								counter++;
							}
						}
					} else {
						logger.info("in else jour");
					}
					if (locFileName != null && locFileName.length > 0) {
						logger.info("locFileName.length : "
								+ locFileName.length);
						for (int i = 0; i < locFileName.length; i++) {
							if (locFileName[i] != null
									&& !locFileName[i].equals("null")
									&& !locFileName[i].equals("")) {
								attachPath[counter] = path + locFileName[i];
								counter++;
							}
						}
					} else {
						logger.info("in else loc");
					}
					if (lodgFileName != null && lodgFileName.length > 0) {
						for (int i = 0; i < lodgFileName.length; i++) {
							if (lodgFileName[i] != null
									&& !lodgFileName[i].equals("null")
									&& !lodgFileName[i].equals("")) {
								attachPath[counter] = path + lodgFileName[i];
								counter++;
							}
						}
					} else {
						logger.info("in else lodg");
					}
					// for getting server path where configuration files are
					// saved.													

					String schQry = "SELECT  TMS_SCH_DESK.DESK_SCH_ECODE FROM TMS_SCH_DESK "
							+ " WHERE TMS_SCH_DESK.DESK_APPL_ID="
							+ bean.getApplicationId()
							+ " AND "
							+ " TMS_SCH_DESK.DESK_APPL_CODE="
							+ bean.getEmpApplId();
					Object[][] schObj = getSqlModel().getSingleResult(schQry);

					EmailTemplateQuery templateQuery1 = template
							.getTemplateQuery(1);
					templateQuery1
							.setParameter(1, String.valueOf(schObj[0][0]));

					logger.info("Ini emp Id : " + bean.getIniEmpId());
					EmailTemplateQuery templateQuery2 = template
							.getTemplateQuery(2);
					templateQuery2.setParameter(1, String.valueOf(bean
							.getIniEmpId()));

					EmailTemplateQuery templateQuery3 = template
							.getTemplateQuery(3);
					templateQuery3.setParameter(1, String.valueOf(bean
							.getIniEmpId()));

					EmailTemplateQuery templateQuery4 = template
							.getTemplateQuery(4);
					templateQuery4.setParameter(1, String.valueOf(bean
							.getApplicationId()));
					templateQuery4.setParameter(2, String.valueOf(bean
							.getEmpApplId()));
					templateQuery4.setParameter(3, String.valueOf(bean
							.getApplicationId()));
					templateQuery4.setParameter(4, String.valueOf(bean
							.getEmpApplId()));

					// pass appId and AppCode

					EmailTemplateQuery templateQuery5 = template
							.getTemplateQuery(5);
					templateQuery5.setParameter(1, String.valueOf(bean
							.getApplicationId()));
					templateQuery5.setParameter(2, String.valueOf(bean
							.getEmpApplId()));
					templateQuery5.setParameter(3, String.valueOf(bean
							.getApplicationId()));
					templateQuery5.setParameter(4, String.valueOf(bean
							.getEmpApplId()));

					EmailTemplateQuery templateQuery6 = template
							.getTemplateQuery(6);
					templateQuery6.setParameter(1, String.valueOf(bean
							.getApplicationId()));
					templateQuery6.setParameter(2, String.valueOf(bean
							.getEmpApplId()));

					EmailTemplateQuery templateQuery7 = template
							.getTemplateQuery(7);
					templateQuery7.setParameter(1, String.valueOf(bean
							.getApplicationId()));
					templateQuery7.setParameter(2, String.valueOf(bean
							.getEmpApplId()));

					EmailTemplateQuery templateQuery8 = template
							.getTemplateQuery(8);
					templateQuery8.setParameter(1, String.valueOf(bean
							.getApplicationId()));
					templateQuery8.setParameter(2, String.valueOf(bean
							.getEmpApplId()));

					template.configMailAlert();
					template.sendApplMailWithAttachment(attachPath);

					if (!bean.getEmpId().equals(
							String.valueOf(bean.getIniEmpId()))
							&& !bean.getEmpId().equals("0")) {
						templateQuery2.setParameter(1, String.valueOf(bean
								.getEmpId()));
						template.configMailAlert();
						template.sendApplMailWithAttachment(attachPath);
					}
					template.clearParameters();
					template.terminate();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateCommentsTrail(String empId, String applId,
			String applCode, String userType, String comments, String applStatus) {
		try {
			String statusQry = "SELECT ";
			Object[][] statObj = getSqlModel().getSingleResult(statusQry);

			String insertQry = " INSERT INTO TMS_COMMENTS_TRAIL (COMMENTS_ID,COMMENTS_APP_ID,COMMENTS_APP_CODE,COMMENTS_APP_STATE,COMMENTS_EMPID,COMMENTS_DESC,COMMENTS_DATE,COMMENTS_LEVEL) "
					+ " VALUES((SELECT NVL(MAX(COMMENTS_ID),0)+1 FROM TMS_COMMENTS_TRAIL ),"
					+ applId
					+ ","
					+ applCode
					+ ",'"
					+ userType
					+ "',"
					+ empId
					+ ",'" + comments + "',SYSDATE,'" + applStatus + "') ";
			getSqlModel().singleExecute(insertQry);
		} catch (Exception e) {
			logger.error("Exception while inserting comments : " + e);
		}
	}

	public String getSysMailId() {
		String mailIdQry = "SELECT SYSMAIL_EMAIL_ID FROM HRMS_SETTINGS_SYSMAILID";
		Object[][] mailIdObj = getSqlModel().getSingleResult(mailIdQry);
		if (mailIdObj != null && mailIdObj.length > 0)
			return String.valueOf(mailIdObj[0][0]);
		return "";
	}

	public void startBookingMail(TravelMonitoring bean) {
		try {
			if (!isStartBookingEnabled(String.valueOf(bean.getEmpApplId()),
					String.valueOf(bean.getApplicationId())))
				return;
			Object[][] tempData = checkMailEvent(61);
			if (tempData == null || tempData.length == 0)
				return;
			if (String.valueOf(tempData[0][0]).equals("Y")) {

				String schEmpCode = " SELECT DESK_SCH_ECODE AS SCH, NVL(D1.SCHDTL_SUBSCHLAR_ECODE,DESK_SCH_ECODE) AS TVLSCH ,NVL(D2.SCHDTL_SUBSCHLAR_ECODE,DESK_SCH_ECODE) AS ACCSCH, "
						+ " NVL(D3.SCHDTL_SUBSCHLAR_ECODE,DESK_SCH_ECODE) AS LOCSCH "
						+ " FROM TMS_SCH_DESK "
						+ "LEFT JOIN TMS_SCH_DTL D1 ON(TMS_SCH_DESK .DESK_ID = D1.SCH_DESK_ID AND "
						+ " TMS_SCH_DESK .DESK_TRAVEL_ASSIGN = 'Y' AND  D1.SCHDTL_TRAVEL_ASSIGN = 'Y') "
						+ " LEFT JOIN TMS_SCH_DTL D2 ON(TMS_SCH_DESK .DESK_ID = D2.SCH_DESK_ID AND "
						+ " TMS_SCH_DESK .DESK_LODGE_ASSIGN = 'Y' AND  D2.SCHDTL_LODGE_ASSIGN = 'Y') "
						+ " LEFT JOIN TMS_SCH_DTL D3 ON(TMS_SCH_DESK .DESK_ID = D3.SCH_DESK_ID AND "
						+ " TMS_SCH_DESK .DESK_LOCAL_ASSIGN = 'Y' AND  D3.SCHDTL_LOCAL_ASSIGN = 'Y') "
						+ " WHERE DESK_APPL_ID = "
						+ bean.getApplicationId()
						+ " AND DESK_APPL_CODE = "
						+ bean.getApplicationId()
						+ " ";
				Object[][] schEmpIdObj = getSqlModel().getSingleResult(
						schEmpCode);

				if (schEmpIdObj == null || schEmpIdObj.length == 0)
					return;

				EmailTemplateBody template = new EmailTemplateBody();
				template.initiate(context, session);
				template.setEmailTemplate(String.valueOf(tempData[0][3]));
				template.getTemplateQueries();

				EmailTemplateQuery templateQuery2 = template
						.getTemplateQuery(2);
				templateQuery2.setParameter(1, String
						.valueOf(schEmpIdObj[0][1]));

				EmailTemplateQuery templateQuery3 = template
						.getTemplateQuery(3);
				templateQuery3.setParameter(1, String.valueOf(bean
						.getApplicationId()));
				templateQuery3.setParameter(2, String.valueOf(bean
						.getEmpApplId()));
				templateQuery3.setParameter(3, String.valueOf(bean
						.getApplicationId()));
				templateQuery3.setParameter(4, String.valueOf(bean
						.getEmpApplId()));

				template.configMailAlert();

				if (!String.valueOf(schEmpIdObj[0][0]).equals(
						String.valueOf(schEmpIdObj[0][1]))) {
					template.sendApplicationMailToKeepInfo(String
							.valueOf(schEmpIdObj[0][1]));
				}
				if (!String.valueOf(schEmpIdObj[0][0]).equals(
						String.valueOf(schEmpIdObj[0][2]))) {
					template.sendApplicationMailToKeepInfo(String
							.valueOf(schEmpIdObj[0][2]));
				}
				if (!String.valueOf(schEmpIdObj[0][0]).equals(
						String.valueOf(schEmpIdObj[0][3]))) {
					template.sendApplicationMailToKeepInfo(String
							.valueOf(schEmpIdObj[0][3]));
				}

				template.sendApplicationMail();
				template.clearParameters();
				template.terminate();
			}
		} catch (Exception e) {
			logger.error("Error while sending mail for start booking : " + e);
		}
	}
}
