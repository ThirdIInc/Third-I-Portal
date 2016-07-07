
<%@page import="org.paradyne.lib.Utility"%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="9%" valign="top" style="padding: 3px;">
		<%
				Object[][] awardImages = null;
				try {
					  awardImages = (Object[][]) request.getAttribute("awardPhoto");
					   if (awardImages != null && awardImages.length > 0) {
						 for (int i = 0; i < 1; i++) {
							if (String.valueOf(awardImages[i][0]).equals("nophoto")) {
		%> 
		<img src="../pages/portal/images/empimage.gif" width="80"height="80" /> 
		<%
 			} else {
 		%> 
 		<img src="../pages/images/<%=session.getAttribute("session_pool")%>/award/<%=awardImages[i][0] %>"
			width="80" height="80" /> 
	<%
 			}
 		  }
 		}
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
 %>
		</td>
		<td width="91%" style="padding-left: 5px; text-align: justify;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<%
					if (awardImages != null && awardImages.length > 0) {
			%>
			<tr>
				<td height="18" class="dasheader"><%=Utility.checkNull(String.valueOf(awardImages[0][4]))%></td>
			</tr>
			<tr>
				<td height="18" class="contlink"><%=Utility.checkNull(String.valueOf(awardImages[0][1]))%></td>
			</tr>
			<tr>
				<td height="18" class="contlink"><%=Utility.checkNull(String.valueOf(awardImages[0][3]))%></td>
			</tr>
			<tr>
				<td height="18" class="contlink"><%=Utility.checkNull(String.valueOf(awardImages[0][2]))%></td>
			</tr>
			<tr>
				<td height="18" class="contlink"><%=Utility.checkNull(String.valueOf(awardImages[0][5]))%></td>
			</tr>
			<%
				}
			%>
		</table>
		</td>
	</tr>
	<%if (awardImages != null && awardImages.length > 0){ %>
	<tr>
		<td>&nbsp;</td>
		<td height="40" valign="bottom"><label>
		<div align="right"><img
			onClick="callPageReward('<%=request.getContextPath()%>/portal/EventData_getRewardData.action?categoryCode=6');"
			src="../pages/common/css/default/images/more.gif" width="42"
			height="13" border="1"
			style="border-color: #CCCCCC; cursor: pointer;" /></div>
		</label></td>
	</tr>
	<%} %>
</table>
<script>
	function callPageReward(actionName){ 
 	//alert("in callPageReward------ ----"+actionName);
	try{
		document.getElementById('paraFrm').action = actionName;
		document.getElementById('paraFrm').submit();
	}
	catch(e){
		alert("Error  "+e);
	}
}	
</script>
