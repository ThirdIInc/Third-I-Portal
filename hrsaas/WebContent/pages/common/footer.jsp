
		<table width="99%" border="0" cellspacing="0" cellpadding="0" style="margin: 1px;">
		<tr>
					<td height="20px" bgcolor="#7d5da7" class="border1" width="100%">
					<table width="100%" border="0" align="center">
						<tr>
							
							<td>
				<div align="right"><script>
			function callWindowPage(){
			 window.open('../pages/policies/privacy.html','wind','width=550,height=275,scrollbars=no,resizable=yes,menubar=no,top=200,left=100,location=0');	 
			}
			
			function callTermsAndCondition(){
				window.open('../pages/policies/Terms & Conditions.html','wind','width=800,height=600,scrollbars=yes,resizable=yes,menubar=no,top=0,left=0,location=0')
			}
		 	
			</script>
			</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
		</table>
<table width="99%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		
		<td width="40%" align="right" valign="top">

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			bgcolor="white">
			<tbody>
				<tr>
					<td colspan="1" align="right" nowrap="nowrap" valign="top" class="link"><strong>Server
					Response Time:&nbsp;</strong> <%
 	String resTime = (String) request.getAttribute("respTime");
 	if (resTime == null) {
 		resTime = "0.231";
 	}
 %> <b><%=resTime%></b> seconds<br />
					<strong>Last Account Activity:&nbsp;</strong><%=session.getAttribute("lastAcctActivity")%></td>
				</tr>
			</tbody>
		</table>
		</td>
	</tr>
</table>



