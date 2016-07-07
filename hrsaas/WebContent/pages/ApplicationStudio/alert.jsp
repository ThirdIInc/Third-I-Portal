<!--Bhushan Dasare--><!--May 27, 2009-->

<link rel="stylesheet" type="text/css" title="default-theme" href="../pages/common/css/commonCSS.jsp" />

<%	String module = (String)request.getAttribute("module");
	String message = (String)request.getAttribute("message");
%>

<head><title>Message - <%=module%></title></head>
<body onload="openAlert();" onunload="clearAlert(false);">
	<table class="formbg" width="100%" id="t" height="345">
		<tr valign="top"><td><%=message%></td></tr><tr><td>&nbsp;</td></tr>
		<tr valign="bottom">
			<td align="center">
				<input type="button" id="btnOk" class="token" value="  OK  " onclick="clearAlert(true);" />
			</td>
		</tr>
	</table>
</body>

<script type="text/javascript">
	function clearAlert(isOKPressed) {
		opener.document.getElementById('alertClose').value = 'Y';
		
		var newAlertID = opener.document.getElementById('newAlertID').value;
		opener.document.getElementById('alert' + newAlertID).className = 'tableCell1';
		
		if(isOKPressed == true) {
			window.close();
		}
	}
	
	function openAlert() {
		opener.document.getElementById('alertClose').value = 'N';
	
		var oldAlertID = opener.document.getElementById('oldAlertID').value;
		if(oldAlertID != '') {
			opener.document.getElementById('alert' + oldAlertID).className = 'tableCell1';
		}
		
		var newAlertID = opener.document.getElementById('newAlertID').value;
		opener.document.getElementById('alert' + newAlertID).className = 'onOverCell';
	}
</script>