

<table width="100%">



	<%
			try {

			String[][] knowList = (String[][]) request
			.getAttribute("knowList");
	%>
	<%
				if (knowList != null && knowList.length > 0) {
				for (int i = 0; ((i < knowList.length) && i < 5); i++) {
	%>

	<tr>
		<td height="20" width="3%"><img align="absmiddle"
			src="../pages/common/css/default/images/dot.gif" /> 
			</td>
			
			<td height="20" width="97%">
			<a
			href="javascript:void(0);" class="contlink"
			onclick="show('<%=knowList[i][1] %>');"><%=knowList[i][0]%></a></td>
	</tr>

	<%
			}
			}
			if (knowList != null && knowList.length > 0) {
				for (int i = 0; i < 5 - knowList.length; i++) {
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





</table>