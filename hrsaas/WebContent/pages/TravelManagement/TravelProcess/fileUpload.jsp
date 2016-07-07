<%@ taglib prefix="s" uri="/struts-tags" %>
<s:form id="paraFrm" action="TrvavelMonitor" method="POST" enctype="multipart/form-data">
<s:head value="File Upload"/>
<h1>File Upload</h1>
<table>
	<tr>
		<td>
			<s:file name="myFile" accept="text/*" label="file" id="myFile"></s:file>
		</td>
		<td>
			<input type="button" value="upload" onclick="uploadFile();">
		</td>
	</tr>
</table>
</s:form>
<script>
	function uploadFile()
	{
		document.getElementById('paraFrm').action='<%=request.getContextPath()%>/TMS/TravelMonitor_uploadTicket.action';
		document.getElementById('paraFrm').submit();
	}
</script>