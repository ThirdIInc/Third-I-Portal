
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<link rel="stylesheet" type="text/css" title="default-theme"
	href="<%=request.getContextPath()%>/pages/common/css/commonCSS.jsp" />
<s:form action="EventDataAction" id="paraFrm" theme="simple"
	name="paraFrm">
	<table width="910" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="23%">&nbsp;</td>
			<td width="77%">&nbsp;</td>
		</tr>
		<tr>
			<td valign="top"><%@include file="../portal/leftglofest.jsp"%>
			</td>
			<td valign="top">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><img src="/pims/pages/portal/images/glofestbanner.gif"
						width="675" height="97" /></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td height="5"></td>
				</tr>

				<%
						Object[][] yearObj = null;
						yearObj = (Object[][]) request.getAttribute("yearObj");
					%>
				<tr>
					<td>
					<ul class="red">


						<%
								if (yearObj != null && yearObj.length > 0) {
								for (int i = 0; i < yearObj.length; i++) {
						%>
						<li><a href="glofest.html" title="home" class="current"><span><%=yearObj[i][0] + "-" + yearObj[i][1]%></span></a></li>

						<%
							}
							}
						%>

					</ul>
					</td>
				</tr>

				<tr>
					<td height="4px"></td>
				</tr>
				<tr>
					<td height="30" class="header1">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><%=yearObj != null && yearObj.length > 0 ? yearObj[0][2]:""%></td>
							<td>
							<table width="47%" border="0" align="right" cellpadding="1"
								cellspacing="1">
								<tr>
									<td width="19%"><a href="#" class="orange"><img
										src="/pims/pages/portal/images/back_icon.gif" width="16" height="16"
										border="0" /></a></td>
									<td width="81%"><a href="eventlist.html" class="orange"><strong>Back
									to List</strong></a></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td><a href="#" onclick="callAction('<%=request.getContextPath()%>/portal/EventData_getGlofestData.action');" class="orange"><strong>Image
					Gallery</strong></a> &nbsp;&nbsp;<span class="orange"><strong>|</strong></span>
					&nbsp;&nbsp;<a href="#" onclick="callAction('<%=request.getContextPath()%>/portal/EventData_aboutEvent.action');" class="header">About
					Event </a> &nbsp;&nbsp;<span class="orange"><strong>|</strong></span>
					&nbsp;&nbsp;<a href="#" onclick="callAction('<%=request.getContextPath()%>/portal/EventData_aboutVideo.action');" class="header">Videos</a>
					&nbsp;&nbsp;<span class="orange"><strong>|</strong></span>
					&nbsp;&nbsp;<a href="presnettaion.html" class="header">Content</a></td>
				</tr>
				<tr>
					<td class="border" height="4px"></td>
				</tr>
				<tr>
					<td valign="top"><iframe
						src="<%=request.getContextPath()%>/portal/EventData_getGlofestImages.action"
						width="640px" height="500px" frameborder="0" scrolling="no"></iframe></td>
				</tr>


			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>

function callAction(action)
{ 
	   alert(action);
	 	document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').submit();
	 
}

</script>

