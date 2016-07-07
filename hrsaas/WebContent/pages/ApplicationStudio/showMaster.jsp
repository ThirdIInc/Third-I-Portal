<!--Bhushan Dasare--><!--May 29, 2009-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%!String masterAction = "", f9Action = "";%>
<%
	masterAction = request.getParameter("masterAction");
	f9Action = request.getParameter("f9Action");
%>

<script>
	window.onload = setFrameSize;
	
	function setFrameSize() {
		if(navigator.appName == 'Netscape') {
			document.getElementById('tdMaster').height = '370';
			document.getElementById('frmMaster').height = '370';
		} else {
			document.getElementById('tdMaster').height = '350';
			document.getElementById('frmMaster').height = '350';
		}
	}
	
	function callBack(f9Action) {
		document.getElementById('paraFrm').action = '<%=f9Action%>';
		document.getElementById('paraFrm').submit();
	}
</script>

<s:form id="paraFrm">
	<table width="100%" class="formbg">
		<tr valign="top">
			<td>
				<input type="button" value="   Back" class="back" title="Back to list" onclick="callBack();" />
			</td>
			<td align="right">
				<input type="button" value="Close" class="cancel" onclick="javascript:window.close();" />
			</td>
		</tr>
	</table>
	<table width="100%" class="formbg">
		<tr valign="top">
			<td id="tdMaster" height="350">
				<iframe id="frmMaster" src="<%=masterAction%>" height="350" width="100%"></iframe>
			</td>
		</tr>
	</table>
	<table width="100%" class="formbg">
		<tr valign="top">
			<td>
				<input type="button" align="top" value="   Back" class="back" title="Back to list" onclick="callBack();" />
			</td>
			<td align="right">
				<input type="button" value="Close" class="cancel" onclick="javascript:window.close();" />
			</td>
		</tr>
	</table>
</s:form>