<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/Ajax.js"></script>


<%
			String accountsAdded = (String) request
			.getAttribute("accountsAdded");

	if (accountsAdded != null && accountsAdded.length() > 0) {
%>

<form name="paraFrm" id="paraFrm" theme="simple">


<table width="100%" border="0" align="right" cellpadding="0"
	cellspacing="0" class="formbg">
	<tr>
		<td colspan="3" valign="bottom"
			background="../pages/images/recruitment/lines.gif" class="txt"><img
			src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
	</tr>
	<tr>
		<td width="100%" colspan="5">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="formbg">
			<tr>
				<td valign="bottom" class="txt"><strong class="text_head"><img
					src="../pages/images/recruitment/review_shared.gif" width="25"
					height="25" /></strong></td>
				<td width="93%" class="txt"><strong class="text_head">Service
				Login</strong></td>
				<td width="3%" valign="top" class="txt">
				<div align="right"><img
					src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>

	<TR>
		<td colspan="5">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="formbg">
			<tr>
				<td class="formth" width="8%" colspan="1">Sr.No</td>
				<td width="18%" class="formth" colspan="1">User Name&nbsp;</td>
				<td width="10%" class="formth" colspan="1">&nbsp;</td>
			</tr>
			<%!int d = 0;%>
			<%
			int z = 1;
			%>

			<s:iterator value="ServloginList">

				<tr>
					<td class="sortableTD"><%=z%></td>
					<td class="sortableTD"><s:property value="servUsrName" />&nbsp;</td>
					<td class="sortableTD"><input type="button" class="token"
						value="Login"
						onclick="callServiceLink('<s:property value="servUsrName" />','<s:property value="servPwd" />')" /></td>
				</tr>
				<%
				z++;
				%>
			</s:iterator>
			<%
			d = z;
			%>

		</table>
		</td>
	</tr>


</table>


<%
} else {
%>

<table width="100%" border="0" align="right" cellpadding="0"
	cellspacing="0" class="formbg">
	<tr>
		<td colspan="3" valign="bottom"
			background="../pages/images/recruitment/lines.gif" class="txt"><img
			src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
	</tr>
	<tr>
		<td width="100%" colspan="5">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="formbg">
			<tr>
				<td valign="bottom" class="txt"><strong class="text_head"><img
					src="../pages/images/recruitment/review_shared.gif" width="25"
					height="25" /></strong></td>
				<td width="93%" class="txt"><strong class="text_head">Service
				Accounts</strong></td>
				<td width="3%" valign="top" class="txt">
				<div align="right"><img
					src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>

		<table>
			<tr>
				<td colspan="4">There is no Service account configured.</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<%
}
%>
</form>

<script>		
function addEmailAccount(){
document.getElementById('paraFrm').target="main";
document.getElementById('paraFrm').action="CongEmailAccount_addAccount.action";
document.getElementById('paraFrm').submit();	
}	
	
function callServiceLink(userName,password)
{ 			
document.getElementById('paraFrm').action="http://www.irctc.co.in/cgi-bin/bv60.dll/irctc/services/login.do?userName="+userName+"&password="+password;
document.getElementById('paraFrm').submit();
}
	
 </script>
