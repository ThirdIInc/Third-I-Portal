<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="TravelMonitor" id="paraFrm" theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Monitoring</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%" align="right" colspan="4"><%!int currentPage = 0, totPages = 0, m;%>
					<%
 		try {
 		currentPage = Integer.valueOf(String.valueOf(request
 		.getAttribute("currentPage")));
 		totPages = Integer.valueOf(String.valueOf(request
 		.getAttribute("totalPages")));
 		System.out.println("totPages");
 	} catch (Exception e) {
 		totPages=0;
 		currentPage=0;
 	}
 %>
 	<%
 		if(totPages == 0)
 		{
 	%> 
 		&nbsp;
 	<%
 		}
 	%>
 <%
 		if (totPages > 0)
 		if (currentPage != 1) {
 %> <a href="#" onclick="callPage('1');"> <img
						src="../pages/common/img/first.gif" width="10" height="10"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('<%=currentPage-1%>')"> <img
						src="../pages/common/img/previous.gif" width="10" height="10"
						class="iconImage" /> </a> <%
 	}
 	if (totPages <= 5) {
 		for (int z = 1; z <= totPages; z++) {
 %> &nbsp;<a href="#" onclick="callPage('<%=z %>');"> <%
 if (currentPage == z) {
 %> <b><u><%=z%></u></b> <%
					} else {
					%> <%=z%> </a> <%
 			}
 			m = z;
 		}
 	} else {
 		if (currentPage == totPages - 1 || currentPage == totPages) {
 			for (int z = currentPage - 2; z <= totPages; z++) {
 %> &nbsp;<a href="#" onclick="callPage('<%=z %>');"> <%
 if (currentPage == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> <%=z%> </a> <%
 		}
 		m = z;
 			}
 		} else if (currentPage <= 3) {
 			for (int z = 1; z <= 5; z++) {
 %> &nbsp;<a href="#" onclick="callPage('<%=z %>');"> <%
 if (currentPage == z) {
 %> <b><u><%=z%></u></b> <%
					} else {
					%> <%=z%></a> <%
 		}
 		m = z;
 			}
 		} else if (currentPage > 3) {
 			for (int z = currentPage - 2; z <= currentPage + 2; z++) {
 %> &nbsp;<a href="#" onclick="callPage('<%=z %>');"> <%
 if (currentPage == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> <%=z%></a> <%
 			}

 			}

 		}
 	}
 	if (currentPage != totPages) {
 		if (totPages > 0) {
 			if (totPages > m) {
 %> ...<%=totPages%> <%
 }
 %> <a href="#" onclick="callPage('<%=currentPage+1 %>')"> <img
						src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; <a
						href="#" onclick="callPage('<%=totPages%>');"> <img
						src="../pages/common/img/last.gif" width="10" height="10"
						class="iconImage" /> </a> <%
 	}
 	}
 %>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="3%" class="formth"><label name="sr.no" id="sr.no"
						ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
					<td width="8%" class="formth"><label name="travel.id"
						id="travel.id" ondblclick="callShowDiv(this)"><%=label.get("travel.id")%></label></td>
					<td width="12%" class="formth"><label name="initiator.name"
						id="initiator.name" ondblclick="callShowDiv(this);"><%=label.get("initiator.name")%></label></td>
					<td width="12%" class="formth"><label name="applicant.name"
						id="applicant.name" ondblclick="callShowDiv(this);"><%=label.get("applicant.name")%></label></td>
					<td width="10%" class="formth"><label name="application.date"
						id="application.date" ondblclick="callShowDiv(this);"><%=label.get("application.date")%></label></td>
					<td width="10%" class="formth"><label name="journey.date"
						id="journey.date" ondblclick="callShowDiv(this);"><%=label.get("journey.date")%></label></td>
					<td width="11%" class="formth"><label name="travel.avail"
						id="travel.avail" ondblclick="callShowDiv(this);"><%=label.get("travel.avail")%></label></td>
					<td width="11%" class="formth"><label name="acco.avail"
						id="accom.avail" ondblclick="callShowDiv(this);"><%=label.get("accom.avail")%></label></td>
					<td width="11%" class="formth"><label name="local.avail"
						id="local.avail" ondblclick="callShowDiv(this);"><%=label.get("local.avail")%></label></td>
					<td width="10%" class="formth"><label name="book.details"
						id="book.details" ondblclick="callShowDiv(this);"><%=label.get("book.details")%></label></td>
					<s:hidden name="currentPage" id="currentPage" />
				</tr>
				<%int i=1; %>
				<s:iterator value="monitorList">
					<tr id='tr<s:property value="srNo"/>' >
						<input type="hidden" name="empId"
							id='empId<s:property value="srNo"/>'
							value="<s:property value="empId"/>">
						<input type="hidden" name="applicationId"
							id='applicationId<s:property value="srNo"/>'
							value="<s:property value="applicationId"/>">
						<input type="hidden" name="empApplId"
							id='empApplId<s:property value="srNo"/>'
							value="<s:property value="empApplId"/>">
						<input type="hidden" name="iniEmpId"
							id='iniEmpId<s:property value="srNo"/>'
							value="<s:property value="iniEmpId"/>">
						<input type="hidden" name="userType"
							id='userType<s:property value="srNo"/>'
							value="<s:property value="userType"/>">
						<input type="hidden" name="applicationDate"
							id='applicationDate<s:property value="srNo"/>'
							value="<s:property value="applicationDate"/>">
						<input type="hidden" name="monId"
							id='monitorId<s:property value="srNo"/>'
							value="<s:property value="monitorId"/>">
						<td width="3%" class="sortabletd" align="center"><s:property
							value="srNo" /></td>
						<td width="8%" class="sortabletd"><s:property
							value="travelReqId" /></td>
						<td width="12%" class="sortabletd"><s:property
							value="initiator" /></td>
						<td width="12%" class="sortabletd"><s:property
							value="applicant" /></td>
						<td width="10%" class="sortabletd"><s:property
							value="applicationDate" /></td>
						<td width="10%" class="sortabletd"><s:property
							value="journeyDate" /></td>
						<td width="11%" class="sortabletd" align="center"><s:property
							value="tvlStatus" /><input type="hidden" name="tvlStatus" value='<s:property value="tvlStatus" />'
							id='tvlStatus<s:property value="srNo"/>' /><br>
						<s:if
							test="tvlStatus != 'Not Required'">
							<input type="button" value="Details" class="token" 
								onclick="showDetails('TravelMonitor_showTravelDetails.action?','<s:property value="srNo"/>');">
						</s:if></td>
						<td width="11%" class="sortabletd" align="center"><s:property
							value="accStatus" /><input type="hidden" name="accStatus" value='<s:property value="accStatus" />'
							id='accStatus<s:property value="srNo"/>' /><br>
						<s:if
							test="accStatus != 'Not Required'">
								<input type="button" value="Details" class="token"
								onclick="showDetails('TravelMonitor_showAccommodationDetails.action?','<s:property value="srNo"/>');">
						</s:if></td>
						<td width="11%" class="sortabletd" align="center"><s:property
							value="locStatus" /><input type="hidden" name="locStatus" value='<s:property value="locStatus" />'
							id='locStatus<s:property value="srNo"/>' /><br>
						<s:if
							test="locStatus != 'Not Required'">
								<input type="button" value="Details" class="token" 
									onclick="showDetails('TravelMonitor_showLocalConveyanceDetails.action?','<s:property value="srNo"/>');">
						</s:if></td>
						<td width="10%" class="sortabletd" align="center">&nbsp; <s:if
							test="buttonLabel != ''">
							<input type="button" name="buttonLabel"
								id='optionButton<s:property value="srNo"/>' theme="simple"
								onclick="callAction('<s:property value="%{monitorId}" />','<s:property value="%{buttonAction}" />','<s:property value="srNo"/>')"
								class="token" value='<s:property value="buttonLabel"/>'>
						</s:if></td>
					</tr>
					<s:if test="onHoldFlag">
						<script>
							document.getElementById('tr<%=i%>').style.background='#E6E4DB';
						</script>
					</s:if>
					<%i++;%>
				</s:iterator>
				<%i=1;%>
				<%if(currentPage == 0) 
					{
						%>
					<tr>
						<td colspan="10" align="center">
							<font color="red"><strong>No records to view</strong></font>
						</td>
					</tr>
						<%
					}
						%>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>

function showDetails(actionName, rowId)
{
	try {
		var empId = document.getElementById('empId'+rowId).value;
		var empApplId = document.getElementById('empApplId'+rowId).value;
		var applicationId = document.getElementById('applicationId'+rowId).value;
		var userType = document.getElementById('userType'+rowId).value; 
		var iniEmpId = document.getElementById('iniEmpId'+rowId).value;
		var applDate = document.getElementById('applicationDate'+rowId).value;
		var monitorId = document.getElementById('monitorId'+rowId).value; 
		var tvlStatus= document.getElementById('tvlStatus'+rowId).value;
		var accStatus= document.getElementById('accStatus'+rowId).value;
		var locStatus= document.getElementById('locStatus'+rowId).value;
		actionName+='empId='+empId+'&empApplId='+empApplId+'&applicationId='+applicationId+'&userType='+userType+'&iniEmpId='+iniEmpId+'&applDate='+applDate+'&monitorId='+monitorId+'&currentPage='+document.getElementById('currentPage').value+'&tvlStatus='+tvlStatus+'&accStatus='+accStatus+'&locStatus='+locStatus;
		window.open(actionName,'dtlWin','top=100,left=50,width=950,height=500,scrollbars=yes,resizable=yes');
	}catch(e)
	{
		alert(e);
	}
}

function callAction(monId,action,rowId)
{
	var empId = document.getElementById('empId'+rowId).value;
	var empApplId = document.getElementById('empApplId'+rowId).value;
	var applicationId = document.getElementById('applicationId'+rowId).value;
	var userType = document.getElementById('userType'+rowId).value; 
	var iniEmpId = document.getElementById('iniEmpId'+rowId).value;
	var applDate = document.getElementById('applicationDate'+rowId).value;
	var monitorId = document.getElementById('monitorId'+rowId).value; 
	var tvlStatus= document.getElementById('tvlStatus'+rowId).value;
	var accStatus= document.getElementById('accStatus'+rowId).value;
	var locStatus= document.getElementById('locStatus'+rowId).value;
	window.open('TravelMonitor_startBooking.action?empId='+empId+'&empApplId='+empApplId+'&applicationId='+applicationId+'&userType='+userType+'&iniEmpId='+iniEmpId+'&applDate='+applDate+'&monitorId='+monitorId+'&currentPage='+document.getElementById('currentPage').value+'&tvlStatus='+tvlStatus+'&accStatus='+accStatus+'&locStatus='+locStatus,'strWin','top=100,left=10,width=900,height=500,scrollbars=yes,resizable=yes');
}

function callPage(pageNo)
{
	if(pageNo == <%=currentPage%>)
		return false;
	document.getElementById('currentPage').value=pageNo;
	document.getElementById('paraFrm').action='TravelMonitor_getMonitoringRecords.action';
	document.getElementById('paraFrm').submit();
}

</script>

