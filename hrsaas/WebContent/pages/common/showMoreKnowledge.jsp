<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>
<s:form action="HomePage" id="paraFrm" name="HomeForm" theme="simple">
	<%
		Object[][] knowObj = null;

		try {

			knowObj = (Object[][]) request.getAttribute("knowObj");
			System.out.println("knowObj.length " + knowObj.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="bottom" class="txt" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td style="padding: 3px;"><strong class="text_head"><img
						src="../pages/mypage/images/icons/documents.png" align="absmiddle" /></strong></td>
					<td width="100%" class="txt"><strong class="text_head"><%=request.getAttribute("knowCategoryName")%></strong><s:hidden
						name="flagHrs" /></td>

				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<%
						if (knowObj != null && knowObj.length > 0) {
							String subcat = String.valueOf(knowObj[0][1]);
				%>
				<tr>
					<td width="25%" valign="top"
						style="padding-left: 5px; border-bottom: 1px solid #CCC; font-size: 10pt; font-weight: bold; padding-bottom: 2px;">
					<img src="../pages/mypage/images/icons/folder.png"
						align="absmiddle" /> <%=String.valueOf(knowObj[0][1])%></td>
				</tr>
				<%  			
						for (int i = 0; i < knowObj.length; i++) {
				%>
				<%if(subcat.equals(String.valueOf(knowObj[i][1]))) {%>
				<tr>
					<td width="25%" valign="top" style="padding-left: 5px;">
					<%
					 
					if(i==knowObj.length-1) {%> <img
						src="../pages/mypage/images/icons/joinbottom.gif"
						align="absmiddle" /> <%}else{%> <img
						src="../pages/mypage/images/icons/join.gif" align="absmiddle" />
					<%} %> <img src="../pages/mypage/images/icons/file.png"
						align="absmiddle" /> <a href="javascript:void(0);"
						class="contlink" onclick="show('<%=knowObj[i][2] %>');"> <%=String.valueOf(knowObj[i][1])%></a>
					</td>
				</tr>
				<%} else{
						subcat = String.valueOf(knowObj[i][1]);
					%>

				<tr>
					<td width="25%" valign="top"
						style="padding-left: 5px; border-bottom: 1px solid #CCC; font-size: 10pt; font-weight: bold; padding-bottom: 2px;">
					<img src="../pages/mypage/images/icons/folder.png"
						align="absmiddle" /> <%=String.valueOf(knowObj[i][1])%></td>
				</tr>
				<tr>
					<td width="25%" valign="top" style="padding-left: 5px;"><img
						src="../pages/mypage/images/icons/join.gif" align="absmiddle" />

					<img src="../pages/mypage/images/icons/file.png" align="absmiddle" />
					<a href="javascript:void(0);" class="contlink"
						onclick="show('<%=knowObj[i][2] %>');"> <%=String.valueOf(knowObj[i][1])%></a>
					<%} %>
					</td>
				</tr>
				<%
					}
					}
				%>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>

 function show(id){		 
		var wind = window.open(id,'_blank','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
 }

</script>
