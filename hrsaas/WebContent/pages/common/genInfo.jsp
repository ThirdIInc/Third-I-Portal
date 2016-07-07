<%@ taglib prefix="s" uri="/struts-tags"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<%
			try {

			Object[][] genList = (Object[][]) request
			.getAttribute("genList");
	%>
	<%
				if (genList != null && genList.length > 0) {
				for (int i = 0; ((i < genList.length) && i < 5); i++) {
	%>

	<tr>
		<td height="20" width="3%"><img align="absmiddle"
			src="../pages/common/css/default/images/dot.gif" /></td>
		<td height="20" width="97%"><a href="javascript:void(0);"
			class="contlink"
			onclick="setHolidayVal('<%=genList[i][1]%>');callGeneralLink('<%=request.getContextPath()%>/portal/EmployeePortal_showGeneralDashletList.action?type=<%=genList[i][1]%>');">
		<%=genList[i][1]%> </a></td>
	</tr>

	<%
			}
			}
			if (genList != null && genList.length > 0) {
				for (int i = 0; i < 5 - genList.length; i++) {
	%>
	<tr>
		<td height="20" width="3%"></td>
		<td height="20" width="97%"></td>
	</tr>
	<%
			}
			}

		} catch (Exception e) {

		}
	%>
	<tr>
		<td>&nbsp;</td>
		<td height="22" valign="bottom"><label>
		<div align="right"></div>
		</label></td>
	</tr>
	<s:hidden name="typeFeild" id="typeFeild" />
</table>
<script>

function setHolidayVal(id){
		document.getElementById('typeFeild').value=id;
}

function callGeneralLink(id){	 
	// window.open(id,'_blank','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=100,left=100');
	if( document.getElementById('typeFeild').value=='Holiday'){
		document.getElementById('paraFrm').action='<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_holidays.action';
	}
	else if(document.getElementById('typeFeild').value=='Quick Contacts'){	
	    document.getElementById('paraFrm').action='<%=request.getContextPath()%>/portal/EmployeePortal_getQuickContacts.action';	 
	}
	else{
		document.getElementById('paraFrm').action=id;
	}
	document.getElementById('paraFrm').submit();		 
}
</script>