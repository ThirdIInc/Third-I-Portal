<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="CustomerAccountInfo" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="advtName" />
	<s:hidden name="checkUploadProfile" />
	<table class="formbg" width="100%">
		<!--Final Table -->
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Account Information</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<!--Table 3-->
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<!--Table 5-->
						<tr>
							<td height="27" class="tesxt_head"><strong>User Account Details
							</strong></td>
						</tr>
					</table>
					</td>
				</tr>
			
				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="5%" valign="top" class="mypageTh" >Sr.No</td>
						<td width="20%" valign="top" class="mypageTh">Report Name</td>
						<td width="20%" valign="top" class="mypageTh">Group Name</td>						
					</tr>
						
						<%!int i = 0;%>
						<% int k = 1;%>
						<s:if test="reportList">
						<s:iterator value="reportList">
							<tr >
								<td class="sortableTD" nowrap="nowrap" align="center"><%=k++ %></td>
								<td class="sortableTD">&nbsp;
								<s:hidden value="listReportCode" />
								<s:hidden value="listAccountCode" />
								<a
								href="javascript:void(0);"
								onClick="callJobNameDetailsWindow('CustomerAccountInfo_generateReport.action?reportCode=<s:property value='listReportCode'/>&&app_random=<%=java.lang.Math.random()%>',event)"
								class="contlink"
								title="<s:property value="listReportName" />"
								target="main">
								<s:property value="listReportName" />
								</a>			
								<td width="20%"  class="sortableTD"><s:property value="listGroupName" /></td>
								
							</tr>						
							
													
						</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td width="100%" colspan="3" align="center"><font
								color="red"><b>No data to display</b></font></td>
							</tr>
						</s:else>
					
					</table>
					</td>
				</tr>
				
			</table>
			</td>
		</tr>
	</table>
	<!--End of Final Table -->
</s:form>
<script>

					function callJobNameDetailsWindow(action,e) {
 						
			 					callPageDisplay(action,'500','300','false',e);	 
							}

</script>