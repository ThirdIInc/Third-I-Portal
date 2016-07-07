<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="ProbationSetting" validate="true" id="paraFrm"
	target="main" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Probation
					Setting </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:submit name="save" cssClass="add" action="ProbationSetting_save"
						value="Save" /> <s:submit cssClass="reset" name="reset"
						value="Reset"></s:submit></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="text_head"><strong
								class="forminnerhead">Probation Setting </strong></td>
						</tr>
						<tr>
						<td width="30%" colspan="1">Probation Applicable:</td>
						<td width="70%" colspan="2"><s:checkbox name="probationApp" /></td>
						</tr>
						<tr>
						<td width="30%" colspan="1">Map Probation With Employee Type:</td>
						<td width="70%" colspan="2"><s:textfield name="empType" readonly="true"/><s:hidden name="empTypeCode" /> <img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'ProbationSetting_f9empTypeAction.action');"></td>
						</tr>
						<tr>
						<td width="30%" colspan="1">Default Probation Days:</td>
						<td width="70%" colspan="2"><s:textfield name="probationDays" onkeypress="return numbersOnly();"  maxlength="3"/></td>
						</tr>
						
						<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="sortable">
				<tr>
						<td height="15" class="formhead" nowrap="nowrap"><strong
							class="forminnerhead">Grade Wise Probation Days</strong></td>
					</tr>
				<tr  class="td_bottom_border">
					<td width="10%" valign="top" class="formth">Grade ID</td>
					<td width="60%" valign="top" class="formth">Grade Name</td>
					<td width="30%" valign="top" class="formth">Probation Days</td>
				</tr>
				<%
				int y = 1;
				%>
				<%!int z = 0;%>
				<s:iterator value="gradeList">
					<tr class="sortableTD">
					<td class="sortableTD" width="10%" align="center"><input type="hidden" name="gradeId"
							value='<s:property value="gradeId"/>' id="gradeId<%=y%>" /> <s:property
							value="gradeId" />
						</td>
						<td class="sortableTD" width="80%"><input type="hidden"
							value='<s:property value="gradeName"/>' id="gradeName<%=y%>" /> <s:property
							value="gradeName" /></td>
						<td class="sortableTD" width="10%" align="center"><input type="text" onkeypress="return numbersOnly();"  maxlength="3"
							name="probationGradeDays" id="probationGradeDays<%=y%>" value='<s:property value="probationGradeDays"/>' />
						</td>
					</tr>
					<%
					y++;
					%>
				</s:iterator>
				<%
				z = y;
				%>
			</table>
			</td>
		</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>