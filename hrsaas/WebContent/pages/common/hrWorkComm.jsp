
<%
				try{
				String[][] bulletinList = (String[][]) request
				.getAttribute("bulletinList");
		%>

<table width="100%">
	<%
			if(bulletinList!=null && bulletinList.length >0)
			{
			for (int i = 0; i < bulletinList.length; i++) {
			%>

	<tr>
		<td width="4%">
		<div align="center"><img
			src="../pages/common/css/default/images/dot.gif" width="6" height="6" /></div>
		</td>
		<td width="96%" height="22"><a href="javascript:void(0);"
			class="dashlink" onclick="show('<%=bulletinList[i][2] %>');"><%=bulletinList[i][1]%></a></td>
	</tr>

	<%}}}catch(Exception e)
			{
				
			}
			%>

</table>