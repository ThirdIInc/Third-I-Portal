package org.paradyne.model.recruitment;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.MySubmittedResumeBean;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
/**
 * @author Manish Sakpal
 * Created on 22/09/2011
 */
public class MySubmittedResumeModel extends ModelBase{

	public void getSubmittedResumeList(MySubmittedResumeBean myResumeSubmitBean, HttpServletRequest request) {
		try {
			String sql = " SELECT REQS_NAME, HRMS_RANK.RANK_NAME, CAND_FIRST_NAME ||'  '||CAND_LAST_NAME, CAND_RESUME,  "
					+" CAND_CODE, CAND_POSTRESUME_REQUISITIONID FROM HRMS_REC_CAND_DATABANK "  
					+" INNER JOIN HRMS_REC_REQS_HDR ON (HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_CAND_DATABANK.CAND_POSTRESUME_REQUISITIONID) "
					+" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID = HRMS_REC_REQS_HDR.REQS_POSITION) "
					+" WHERE CAND_REF_TYPE = 'C' AND CAND_POSTRESUME_EMPID = "+myResumeSubmitBean.getUserEmpId();
					
			Object[][] data = getSqlModel().getSingleResult(sql);
			String[] pageIndex = Utility.doPaging(myResumeSubmitBean.getMyPage(),
					data.length, 20);
			if (pageIndex == null) {
				pageIndex[0] = "0";
				pageIndex[1] = "20";
				pageIndex[2] = "1";
				pageIndex[3] = "1";
				pageIndex[4] = "";
			}
			request.setAttribute("totalPage", Integer.parseInt(String
					.valueOf(pageIndex[2])));
			request.setAttribute("PageNo", Integer.parseInt(String
					.valueOf(pageIndex[3])));
			if (pageIndex[4].equals("1")) {
				myResumeSubmitBean.setMyPage("1");
			}
				
			if (data != null && data.length > 0) {
				ArrayList<MySubmittedResumeBean> resumeList = new ArrayList<MySubmittedResumeBean>();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
					MySubmittedResumeBean bean1 = new MySubmittedResumeBean();
					bean1.setItrRequisitionName(checkNull(String.valueOf(data[i][0])));
					bean1.setItrPosition(checkNull(String.valueOf(data[i][1])));
					bean1.setItrCandidateName(checkNull(String.valueOf(data[i][2])));
					bean1.setItrResumeName(checkNull(String.valueOf(data[i][3])));
					bean1.setItrCandidateCode(checkNull(String.valueOf(data[i][4])));
					bean1.setItrRequisitionCode(checkNull(String.valueOf(data[i][5]))); 
					resumeList.add(bean1);
				}
				myResumeSubmitBean.setSubmittedResumeList(resumeList);
				myResumeSubmitBean.setTotalRecods(String.valueOf(data.length));
			} else {
				myResumeSubmitBean.setNoData("true");
				myResumeSubmitBean.setTotalRecods("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	} 

}
