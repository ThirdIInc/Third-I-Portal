<!--Bhushan Dasare--><!--Feb 11, 2009-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	Object[][] processAppraisalAlertsObj = (Object[][])request.getAttribute("processAppraisalAlertsObj");
	int cnt = 1;
	
	if(processAppraisalAlertsObj != null) {
%>	<table width="100%">
		<tr>
			<td class="formth">Sr. No.</td>
			<td class="formth">Message</td>
			<td class="formth">View Appraisal</td>
		</tr>
<%		for(int i = 0; i < processAppraisalAlertsObj.length; i++) {
%>		<tr id="alert<%=i%>">
			<td class="sortabletd"><%=cnt++%></td>
			<td class="sortabletd"><%=String.valueOf(processAppraisalAlertsObj[i][3])%></td>
			<td class="sortabletd" align="center">
<%				if(String.valueOf(processAppraisalAlertsObj[i][6]).equals("Alert")) {
					String apprId = String.valueOf(processAppraisalAlertsObj[i][7]);
					String empId = String.valueOf(processAppraisalAlertsObj[i][10]);
					String phaseCode = String.valueOf(processAppraisalAlertsObj[i][9]);
					String action = request.getContextPath() + "/pas/ApprFormGeneralInfo_retrieveAppraisalDetailsDashlet.action?apprId=" + apprId + 
							"&empId=" + empId + "&phaseCode=" + phaseCode;
%>				<input type="button" value="  View  " class="token" onclick="viewApplication('<%=action%>');" />
<%				} else {
%>					&nbsp;
<%				}
%>			</td>
		</tr>
<%		}
%>	</table>
<%	}
%>