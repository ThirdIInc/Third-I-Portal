 <!--Bhushan Dasare--><!--Feb 11, 2009-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<s:hidden id="oldAlertID" /><s:hidden id="newAlertID" /><s:hidden id="alertClose" value="Y" />

<%	
	Object[][] alertsObj = (Object[][])request.getAttribute("alertsObj");
%>
<table width="100%" class="sortable">
	<tr>
		<td class="formth" width="7%"><b>Sr. No.</b></td>
		<td class="formth" width="15%"><b>Application Type</b></td>
		<td class="formth" width="58%"><b>Subject</b></td>
		<td class="formth" width="8%"><b>Date</b></td>
		<td class="formth" width="12%"><b>View Application</b></td>
	</tr>
<%	int approvalCnt = 1;
	if(alertsObj != null && alertsObj.length > 0) {
		for(int j = 0; j < alertsObj.length; j++) {
%>			<tr id="alert<%=j%>" style="cursor: hand;" onmouseover="newRowColor(this);" 
			onmouseout="oldRowColor(this);" ondblclick="showAlert('<%=String.valueOf(alertsObj[j][0])%>', '<%=j%>');">
				<td title="Double Click to show Message" class="sortableTD"><%=approvalCnt++%></td>
				<td title="Double Click to show Message" class="sortableTD"><%=String.valueOf(alertsObj[j][5])%></td>
				<td title="Double Click to show Message" class="sortableTD"><%=String.valueOf(alertsObj[j][1])%></td>
				<td title="Double Click to show Message" class="sortableTD" align="center" nowrap="nowrap"><%=String.valueOf(alertsObj[j][4])%></td>
				<td class="sortableTD" align="center">
<%					if(String.valueOf(alertsObj[j][6]).equals("Alert")) {
						String action = request.getContextPath() + String.valueOf(alertsObj[j][9]);
%>						<input type="button" title="View list of pending applications" value="  View  " class="token" onclick="viewApplication('<%=action%>');" />
<%					} else {
%>						&nbsp;
<%					}
%>				</td>
			</tr>
<%		}
	}
%>
</table>