package org.paradyne.model.recruitment;

import org.paradyne.bean.Recruitment.ClosedRequisitionBean;
import org.paradyne.lib.ModelBase;

public class ClosedRequisitionModel extends ModelBase {

	public boolean updateStatus(ClosedRequisitionBean crBean) {
		
		String str;
		Boolean result=false;
		
		
		try {
			str = "update HRMS_REC_REQS_HDR set REQS_CLOSE_DATE = SYSDATE, REQS_STATUS = 'C' where REQS_CODE = "
					+ crBean.getReqCode();
			result = getSqlModel().singleExecute(str);
		} catch (Exception e) {
		}
		return result;
	}
}
