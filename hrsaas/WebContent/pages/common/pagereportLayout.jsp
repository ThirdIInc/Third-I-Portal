<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- Show usage; Used in Header --%>
<tiles:importAttribute name="title" scope="request" />

<link rel="stylesheet" type="text/css" title="default-theme"
	href="../pages/common/css/commonCSS.jsp" />
<table width="100%" height="100%" border="0" cellpadding="0"
	cellspacing="0" style="border-collapse: collapse;"
	bordercolor="#111111">
	<tr>
		<td>
		<s:hidden name="actionMessage" /> 
		
			</td>
	</tr>
	<tr height="100%" WIDTH="100%">
		<td ALIGN="center" valign="top">
		<s:actionerror /> <s:actionmessage />
		<s:fielderror /> <tiles:insertAttribute name="body" />
		</td>

	</tr>
	<tr>
		<td>
		
		</td>
	</tr>
</table>
<script>


function callMessage() {
	var message = document.getElementById("actionMessage").value;
	if(message !="" ){
		alert (message);
		}
}
callMessage();

</script>
