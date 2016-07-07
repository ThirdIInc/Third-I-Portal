<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="org.paradyne.lib.Utility"%>
<%
Object[][]requistionDtlObj =(Object[][])request.getAttribute("reqDtlObj");

%>

<table width="100%" border="0" cellpadding="0" cellspacing="0"
	class="formbg">
	<tr>
		<strong class="formhead"> </strong>
		<td>
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0">


			<tr>
				<strong class="text_head">Job Description : </strong>

			</tr>



			<tr>
				<td width="24%" colspan="1" height="27">Job Name:</td>
				<td width="76%" height="27" colspan="4">
				<%=Utility.checkNull(String.valueOf(requistionDtlObj[0][0]))%>
				</td>
			</tr>

			<tr>
				<td>&nbsp;&nbsp;</td>
			</tr>

			<tr>
				<td width="24%" height="22" class="formtext" colspan="1"
					valign="top">Job Description :</td>
				<td width="76%" valign="top"><%=Utility.checkNull(String.valueOf(requistionDtlObj[0][1]))%></td>
			</tr>

			<tr>
				<td>&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td width="24%" colspan="1" height="27" valign="top">Job
				Roles/Responsibilities :</td>
				<td width="76%" height="27" colspan="4" valign="top"><%=Utility.checkNull(String.valueOf(requistionDtlObj[0][2]))%></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;</td>
			</tr>

			<tr>

				<td width="24%" height="22" class="formtext" valign="top">Special
				Requirement :</td>
				<td width="76%" height="27" valign="top" colspan="4"><%=Utility.checkNull(String.valueOf(requistionDtlObj[0][3]))%></td>


			</tr>

			<tr>
				<td>&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td width="24%" colspan="1" height="27" valign="top">Personal
				Qualities :</td>
				<td width="76%" height="27" colspan="4"><%=Utility.checkNull(String.valueOf(requistionDtlObj[0][4]))%></td>
			</tr>

		</table>
		</td>
	</tr>
</table>