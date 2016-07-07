<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>Success: Upload User Image</title>
</head>
<body><s:textfield name="userImageFileName" id="userImageFileName"></s:textfield>
<% 	String field=request.getParameter("field");%>

<s:hidden name="fileUploadField" value="<%=field%>"/>


</body>
<script>
var fieldName=document.getElementById('fileUploadField').value;
var userImageFileName=document.getElementById("userImageFileName").value;
window.opener.document.getElementById('paraFrm').target="main";
window.opener.document.getElementById('paraFrm').action="OfficialDetail_update.action?userImageFileName="+userImageFileName;
window.opener.document.getElementById('paraFrm').submit();
window.close();
</script>
</html>