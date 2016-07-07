<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>
 

 
<s:form action="TipsSetting" id="paraFrm" theme="simple" name="paraFrmName">

	<%
		
		Object[][] tipsData = null;


		String selectedStr = "";
		try {
			
			tipsData = (Object[][]) request.getAttribute("tipsData");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>

	<table width="910" border="0" align="left" cellpadding="0"
		bgcolor="white" cellspacing="0">
		<s:hidden name="contentDataFlag" id="contentDataFlag" />
		<input type="hidden" id="categoryCode" value="10" />
	

		<tr>
			<td width="235" valign="top"><%@include file="../portal/leftglofest.jsp"%></td>
			<td width="57%" valign="top">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="100%" colspan="2">&nbsp; </td>

				</tr>
			
				<tr>
					<td><img src="../pages/portal/images/forum.gif" width="675"
						height="97" /></td>
				</tr>
		

				<tr>
					<td height="5"></td>
				</tr>



				<tr>
					<td height="4px"></td>
				</tr>
				<tr id="eventNameId">
					<td height="30" class="eventheader">
						<table>
						<%
								if (tipsData != null && tipsData.length > 0) {

								for (int i = 0; i < tipsData.length; i++) {
						%>

						<tr>
							<td><img src="../pages/portal/images/next_icon.gif"
								width="16" height="16" border="0" /></td>
							<td height="24"><a href=" <%=tipsData[i][1]%>" target="_blank"
								
								class="contlink"> <%=tipsData[i][0]%> </a></td>
						</tr>
						<%
							}
							}
					
						%>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	
</s:form>
 

