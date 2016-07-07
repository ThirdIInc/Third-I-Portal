 <%@ taglib prefix="s" uri="/struts-tags"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<%
	Object[][] downloadList =null;
			try {

				downloadList= (Object[][]) request
			.getAttribute("downloadList");
	%>
	<%
				if (downloadList != null && downloadList.length > 0) {
				for (int i = 0; ((i < downloadList.length) && i < 5); i++) {
	%>

	<tr>
		<td height="20" width="3%"><img align="absmiddle"
			src="../pages/common/css/default/images/dot.gif" /> 
			</td>
			<td height="20" width="97%">
			<a
			href="javascript:void(0);" class="contlink"
			onclick="callMore('<%=request.getContextPath()%>/portal/EmployeePortal_showQuickDownload.action?downloadCode=<%=downloadList[i][0]%>');">
		<%=downloadList[i][0]%> </a></td>
	</tr>

	<%
			}
			}
			if (downloadList != null && downloadList.length > 0) {
				for (int i = 0; i < 5 - downloadList.length; i++) {
	%>

	<tr>
		<td height="20" width="3%"></td>
		<td height="20" width="97%"></td>
	</tr>
	<%
			}
			}

		} catch (Exception e) {
				e.printStackTrace();
				
		}
	%>
	<%if (downloadList != null && downloadList.length > 5) {%>
	<tr>
		<td>&nbsp;</td>
		<td height="30" valign="bottom"><label>
		<div align="right"><img
			onclick="callMore('<%=request.getContextPath()%>/portal/EmployeePortal_showMoreInfo.action?dashletCode=4');"
			src="../pages/common/css/default/images/more.gif" width="42"
			height="13" border="1" style="border-color: #CCCCCC; cursor: pointer;" /></div>
		</label></td>
	</tr>

<%} %>
	
	 

</table>






<script>

function setHolidayVal(id)
{
		document.getElementById('typeFeild').value=id;
}

	function callMore(id)
{
	 
	// window.open(id,'_blank','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=100,left=100');
	
	if( document.getElementById('typeFeild').value=='Holiday'){
		document.getElementById('paraFrm').action='<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_holidays.action';
	}else{
		document.getElementById('paraFrm').action=id;
	}
		document.getElementById('paraFrm').submit();	
	 
}
</script>