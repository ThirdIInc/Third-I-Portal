<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="SettingMaster" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" cellpadding="2" cellspacing="2"
		class="formbg">
		<tr>
			<td colspan="3" width="100%"><%@ include
				file="hrConfigHeader.jsp"%></td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Quick
					Information Settings </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><s:submit cssClass="save" theme="simple"
						action="SettingMaster_saveGeneral" value=" Save" /></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td class="formth" width="90%" colspan="1"><label name="link.name" id="link.name" onclick="callShowDiv(this);"><%=label.get("link.name") %></label></td>
					<td class="formth" width="10%" colspan="1"><label name="active" id="active" onclick="callShowDiv(this);"><%=label.get("active") %></label></td>

				</tr>
				<%
				int m = 1;
				%>
				<s:iterator value="list_gsLink">
					<tr>
						<td class="td_bottom_border" width="90%" colspan="1"><s:hidden
							name="linkCode_Gs" /> <s:property value="linkName_Gs" /></td>
						<td class="td_bottom_border" align="center" width="10%" colspan="1"><s:if
							test="%{Check_G}">
							<input type="checkbox" name="Check_G"
								onclick="callChk(<%=m%>,'g');" checked="checked" />
							<input type="hidden" name="chkGlink" id='<%="g"+m%>'
								value="<%="Y"%>" />
						</s:if><s:else>
							<input type="checkbox" name="Check_G"
								onclick="callChk(<%=m%>,'g');" />

							<input type="hidden" name="chkGlink" id='<%="g"+m%>'
								value="<%="N"%>" />
						</s:else></td>

					</tr>
					<%
					m++;
					%>
				</s:iterator>
			</table>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><s:submit cssClass="save" theme="simple"
						action="SettingMaster_saveGeneral" value=" Save" /></td>
				</tr>
			</table>
			</td>
		</tr>
		</table>
</s:form>
<script>
	function callChk(id,chkName)
	{	
	  	var idName = chkName+id;
	  	
		if(document.getElementById(idName).value=='Y')
	 		document.getElementById(idName).value='N';
		else if(document.getElementById(idName).value=='N')
	 		document.getElementById(idName).value='Y';
	}
</script>