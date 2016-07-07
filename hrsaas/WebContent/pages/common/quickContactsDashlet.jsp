<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>
<s:form action="HomePage" id="paraFrm" name="HomeForm" theme="simple">
	<%
		Object[][] categoryObj = null;
		try {

			categoryObj = (Object[][]) request.getAttribute("contactObj");
			System.out.println("categoryObj.length " + categoryObj.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" bordercolor="red">
		<tr>
			<td valign="bottom" class="txt" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="pageHeader">
				<tr>
					<td width="4%"><strong class="text_head"><img
						src="../pages/mypage/images/icons/quickContactHeader.png"
						width="25" height="25" /></strong></td>
					<td width="96%"
						style="padding-left: 5px; border-bottom: 1px solid #CCC; font-size: 9pt; font-weight: bold; padding-bottom: 2px;">
					Quick Contacts</td>
					<td width="3%" valign="top" class="txt">
					<div align="right"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="1" align="right" cellpadding="0"
				bordercolor="green" cellspacing="0">
				<%
						if (categoryObj != null && categoryObj.length > 0) {
						String subcat = String.valueOf(categoryObj[0][0]);
				%>
				<tr>
					<td valign="top" colspan="3"
						style="padding-left: 5px; border-bottom: 1px solid #CCC; font-size: 10pt; font-weight: bold; padding-bottom: 2px;">

					<%=String.valueOf(categoryObj[0][0])%></td>
				</tr>
				<%
				for (int i = 0; i < categoryObj.length; i++) {
				%>
				<%
				if (subcat.equals(String.valueOf(categoryObj[i][0]))) {
				%>
				<tr>
					<td valign="top" style="padding-left: 5px;"><%=Utility.checkNull(String
										.valueOf(categoryObj[i][1]))%>
					</td>
					<td valign="top" style="padding-left: 5px;"><%=Utility.checkNull(String
										.valueOf(categoryObj[i][2]))%>
					</td>
					<td valign="top" style="padding-left: 5px;"><%=Utility.checkNull(String
										.valueOf(categoryObj[i][3]))%>
					</td>
				</tr>
				<%
						} else {
						subcat = String.valueOf(categoryObj[i][0]);
				%>
				<tr>
					<td valign="top" colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td valign="top" colspan="3" nowrap="nowrap"
						style="padding-left: 5px; border-bottom: 1px solid #CCC; font-size: 10pt; font-weight: bold; padding-bottom: 2px;">
					<!--  <img align="absmiddle"
			src="../pages/common/css/default/images/dot.gif" /> --> <%=Utility.checkNull(String
										.valueOf(categoryObj[i][0]))%></td>
				</tr>
				<tr>
					<td valign="top" style="padding-left: 5px;"><%=Utility.checkNull(String
										.valueOf(categoryObj[i][1]))%>
					</td>
					<td valign="top" style="padding-left: 5px;"><%=Utility.checkNull(String
										.valueOf(categoryObj[i][2]))%>
					</td>
					<td valign="top" style="padding-left: 5px;"><%=Utility.checkNull(String
										.valueOf(categoryObj[i][3]))%>
					</td>
					<%
					}
					%>
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

