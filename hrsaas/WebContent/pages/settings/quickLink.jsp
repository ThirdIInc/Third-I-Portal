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
					Link </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td width="78%"><s:submit cssClass="save"
						theme="simple" action="SettingMaster_saveLink" value=" Save" /></td>
					<td width="22%"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td class="formth" width="90%" colspan="3"><label name="link.name" id="link.name" ondblclick="callShowDiv(this);"><%= label.get("link.name")%></label></td>
					<td class="formth" width="10%" colspan="1"><label name="active" id="active" ondblclick="callShowDiv(this);"><%= label.get("active")%></label></td>
				</tr>
			
		<%
		int k = 0;
		%>
		<s:iterator value="list_QlLink">
					<tr>
						<td class="td_bottom_border" width="90%" colspan="3"><s:hidden
							name="linkCode_Ql" /> <s:property value="linkName_Ql" /></td>
						<td class="td_bottom_border" width="10%" colspan="1"><s:if
							test="%{Check_Q}">
							<input type="checkbox" name="Check_Q"
								onclick="callChk(<%=k%>,'q')" checked="checked" />
							<input type="hidden" name="chkQlink" id='<%="q"+k%>'
								value="<%="Y"%>" />
						</s:if> <s:else>
							<input type="checkbox" name="Check_Q"
								onclick="callChk(<%=k%>,'q')" />
							<input type="hidden" name="chkQlink" id='<%="q"+k%>'
								value="<%="N"%>" />
						</s:else></td>

					</tr>
					<%
					k++;
					%>
				</s:iterator>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td><s:submit cssClass="save"
						theme="simple" action="SettingMaster_saveLink" value=" Save" /></td>
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