<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<head>
<style type="text/css">
.messageClass {
	font-family:Arial, Helvetica, sans-serif; 
	font-size:12px; 
	color:#FF0000;
	text-decoration: none;
	font-weight:bold;
}
</style></head>
<s:form action="EmailPayrollReport" validate="true" id="paraFrm" theme="simple">
<%
	String message = String.valueOf(request.getAttribute("unauthorizedMessage")); 
%>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
	<tr>
		<td colspan="4" width="100%">
			<table width="100%" border="0">
				<tr>
				    <td bgcolor="#EAF2FB">
				    <table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" height="400px">
				      <tr>
				        <td width="20%" align="center"><img src="../pages/common/img/alert.jpg" width="57" height="49" /></td>
				        <td width="80%" >
				        	<div id="messageDiv" align="center" class="messageClass"></span>
				        	</div></td>
				      </tr>
				    </table>
				    </td>
				  </tr>
			</table>
		</td>
	</tr>
	<s:hidden name="authMsg" value="<%=message%>"/>;
</table>
</s:form>
<script>
	document.getElementById('messageDiv').innerHTML = document.getElementById("paraFrm_authMsg").value;
</script>
