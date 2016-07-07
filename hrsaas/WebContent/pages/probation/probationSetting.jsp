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
						value="Save" />  </td>
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
						<td width="30%" colspan="1"><label  name="probationApp" id="probationApp" ondblclick="callShowDiv(this);"><%=label.get("probationApp")%></label>:</td>
						<td width="70%" colspan="2"><s:checkbox name="probationApp" /></td>
						</tr>
						<tr>
						<td width="30%" colspan="1"><label  name="mapprobempType" id="mapprobempType" ondblclick="callShowDiv(this);"><%=label.get("mapprobempType")%></label>:</td>
						<td width="70%" colspan="2"><s:textfield name="empType" readonly="true"/><s:hidden name="empTypeCode" /> <img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'ProbationSetting_f9empTypeAction.action');"></td>
						</tr>
						<tr>
						<td width="30%" colspan="1"><label  name="defaultProbDays" id="defaultProbDays" ondblclick="callShowDiv(this);"><%=label.get("defaultProbDays")%></label>:</td>
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
					<td width="10%" valign="top" class="formth"><label  name="gradeId" id="gradeId" ondblclick="callShowDiv(this);"><%=label.get("gradeId")%></label></td>
					<td width="70%" valign="top" class="formth"><label  name="gradeName" id="gradeName" ondblclick="callShowDiv(this);"><%=label.get("gradeName")%></label></td>
					<td width="20%" valign="top" class="formth" height="22"><label  name="probDays" id="probDays" ondblclick="callShowDiv(this);"><%=label.get("probDays")%></label></td>
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
						<td class="sortableTD" width="70%"><input type="hidden"
							value='<s:property value="gradeName"/>' id="gradeName<%=y%>" /> <s:property
							value="gradeName" /></td>
						<td class="sortableTD" width="20%" align="center" height="22"><input type="text"  style="text-align: right;" onkeypress="return numbersOnly();"  maxlength="3" size="6"
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