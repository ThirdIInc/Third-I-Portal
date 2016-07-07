<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function updateDetails(){
	var dashBaordName=document.getElementById("dashBoardName").value;
	var isActive=document.getElementById('isActive').checked
	document.getElementById("paraFrm").action="ManageClientDashBoard_saveDashBoardDetails.action?dashBaordName="+dashBaordName+"&isActive="+isActive
	document.getElementById('paraFrm').target = "_self";
	document.getElementById("paraFrm").submit();
}


</script>
<s:form action="ManageClientDashBoard" id="paraFrm" validate="true"
		theme="simple" target="main">
		<s:hidden name="dashBoardID"/>
		<table width="100%" border="0" align="right" class="formbg">
	
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Update DashBoard	Master List</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right">
					<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
					
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" class="formbg">
		
		<tr>
		<td>
		<input type="button" value="Update" Class="Update" onclick="updateDetails('<s:property value="dashBoardName"/>','<s:property value="isActive"/>')">
		</td>
		</tr>
		
		<tr>
		<td width="20%">
		Dash Board Name			
		</td>
		<td width="80%">
		<s:textfield  theme="simple" id="dashBoardName" name="dashBoardName" size="25" readonly="false" value="%{dashBoardName}"cssStyle="background-color: #F2F2F2;">
		</s:textfield>
		</td>
		</tr>
		<tr>
		<td>
		Is Active
		</td>
		<td>
		
		<s:if test="isActive=='YY'">
		<s:checkbox name="isActive" id="isActive" value="true"></s:checkbox>
		<s:hidden name="isActive" id="isEnable"></s:hidden>
		</s:if>
		<s:elseif test="isActive=='NN'">
		<s:checkbox name="isActive" id="isActive" value="false"></s:checkbox>
		<s:hidden name="isActive" id="isDisable"></s:hidden>
		</s:elseif>
		</td>
		</tr>
		</table>
		</td>
		</tr>
		<tr>
		<td>
		<input type="button" value="Update" Class="Update" onclick="updateDetails('<s:property value="dashBoardName"/>','<s:property value="isActive"/>')">
		</td>
		</tr>
		</table>
		
</s:form>		
		



</body>
</html>