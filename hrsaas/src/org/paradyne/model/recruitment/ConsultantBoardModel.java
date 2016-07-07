/**
 * 
 */
package org.paradyne.model.recruitment;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.Recruitment.ConsultantBoard;
import org.paradyne.bean.Recruitment.PartnerJobBoard;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;

/**
 * @author AA1385
 *
 */
public class ConsultantBoardModel extends ModelBase {

	
	public void callPartnerJobBorad(ConsultantBoard bean,HttpServletRequest request)
	{
		String status = request.getParameter("status");
		if(status!=null && status.equals("C"))
		{
			bean.setIsClose("true");
		}
		/**
		 * String sql =" SELECT REQS_NAME,HRMS_RANK.RANK_NAME,NVL(LOCATION_NAME,''),(NVL(PUB_ASG_VAC,0) - NVL(PUB_CLOSE_VACAN,0))," 
			    +" PUB_ASG_VAC,HRMS_REC_REQS_HDR.REQS_CODE ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,REQS_HIRING_MANAGER	FROM HRMS_REC_VACPUB_CONSDTL " 
				+" INNER JOIN HRMS_REC_VACPUB_HDR ON(HRMS_REC_VACPUB_HDR.PUB_CODE=HRMS_REC_VACPUB_CONSDTL.PUB_CODE ) "
				+" LEFT JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE ) "
				+" LEFT JOIN HRMS_REC_VACPUB_RECDTL ON(HRMS_REC_VACPUB_RECDTL.PUB_CODE =HRMS_REC_VACPUB_HDR.PUB_CODE )  "
				+" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =HRMS_REC_REQS_HDR.REQS_POSITION) "
				+" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID =HRMS_REC_REQS_HDR.REQS_BRANCH) "
				+" LEFT JOIN HRMS_LOCATION ON ((HRMS_LOCATION.LOCATION_CODE =HRMS_REC_REQS_HDR.REQS_BRANCH )) "
				+"  LEFT JOIN HRMS_EMP_OFFC ON (EMP_ID = REQS_HIRING_MANAGER) "
				+" WHERE PUB_CONS_ID ="+bean.getUserEmpId()+" AND PUB_VAC_STATUS !='C'" ;
				changes in assigned vaccany 
		 */
	String sql =" SELECT REQS_NAME,HRMS_RANK.RANK_NAME,NVL(CENTER_NAME,' '), (NVL(HRMS_REC_VACPUB_CONSDTL.PUB_ASG_VAC,0) - NVL(HRMS_REC_VACPUB_CONSDTL.PUB_CLOSE_VAC,0)), " 
				+" HRMS_REC_VACPUB_CONSDTL.PUB_ASG_VAC, HRMS_REC_REQS_HDR.REQS_CODE ,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME ,REQS_HIRING_MANAGER	FROM HRMS_REC_VACPUB_CONSDTL " 
				+" INNER JOIN HRMS_REC_VACPUB_HDR ON(HRMS_REC_VACPUB_HDR.PUB_CODE=HRMS_REC_VACPUB_CONSDTL.PUB_CODE ) "
				+" LEFT JOIN HRMS_REC_REQS_HDR ON(HRMS_REC_REQS_HDR.REQS_CODE = HRMS_REC_VACPUB_HDR.PUB_REQS_CODE ) "
				+" LEFT JOIN HRMS_REC_VACPUB_RECDTL ON(HRMS_REC_VACPUB_RECDTL.PUB_CODE =HRMS_REC_VACPUB_HDR.PUB_CODE )  "
				+" INNER JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =HRMS_REC_REQS_HDR.REQS_POSITION) "
				+" INNER JOIN HRMS_CENTER ON (HRMS_CENTER.CENTER_ID =HRMS_REC_REQS_HDR.REQS_BRANCH) " 
				+"  LEFT JOIN HRMS_EMP_OFFC ON (EMP_ID = REQS_HIRING_MANAGER) "
				+" WHERE PUB_CONS_ID ="+bean.getUserEmpId()+" AND PUB_VAC_STATUS !='C'" ;
			 Object [][] data = getSqlModel().getSingleResult(sql);
		
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),data.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			bean.setMyPage("1");
		
		if(data != null && data.length >0)
		{  
			ArrayList  jobList = new ArrayList();
			for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
				PartnerJobBoard bean1 = new PartnerJobBoard();
				bean1.setItReqName(String.valueOf(data[i][0]));
				bean1.setItPosition(String.valueOf(data[i][1]));				
				bean1.setItLocation(checkNull(String.valueOf(data[i][2])));
				bean1.setItNoOfOpenVac(String.valueOf(data[i][3])); 
				bean1.setItTotalNoPostVac(String.valueOf(data[i][4]));  // its hard coded do after
				bean1.setItReqCode(String.valueOf(data[i][5])); 
				bean1.setItHiringManager(String.valueOf(data[i][6])); 
				bean1.setItHiringManagerId(String.valueOf(data[i][7])); 
				jobList.add(bean1);
			}
			bean.setPartnerJobList(jobList);
		} else{
			bean.setNoData("true");
		}
	}
  
	public String checkNull(String result) {
		 
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	} // end of checkNull
	
}
