<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="AlertSettings" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>

		<tr>
			<td valign="bottom" class="txt"><strong class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">Alert Message
			Settings </strong></td>

		</tr>
		<tr>
			<td class="txt"><img src="../pages/images/recruitment/space.gif"
				width="5" height="5" /></td>
			<td class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>
		</tr>
		<tr>
			<td></td>
			<td width="22%">
			<div align="right"><font color="red">*</font> Indicates
			Required</div>
			</td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
		</tr>
	</table>
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="100%" colspan="5">
			<table class="formbg" width="100%">
				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>

				<tr>
					<td class="formth" width="90%" colspan="3">Module Name</td>
					<td class="formth" width="10%" colspan="1">Send Mail</td>

				</tr>
				<%
				int p = 0;
				%>
				<s:iterator value="list_EM">
					<tr>
						<td class="border2" width="85%" colspan="3"><s:hidden
							name="linkCode_EM" /> <s:property value="linkName_EM" /></td>
						<td class="border2" width="15%" colspan="1"><s:if
							test="%{Check_EM}">
							<input type="checkbox" name="Check_EM"
								onclick="callChk(<%=p%>,'em')" checked="checked" />
							<input type="hidden" name="chkemail" id='<%="em"+p%>'
								value="<%="Y"%>" />
						</s:if> <s:else>
							<input type="checkbox" name="Check_EM"
								onclick="callChk(<%=p%>,'em')" />
							<input type="hidden" name="chkemail" id='<%="em"+p%>'
								value="<%="N"%>" />
						</s:else></td>

					</tr>
					<%
					p++;
					%>
				</s:iterator>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td align="center" colspan="4"><s:submit cssClass="add"
						theme="simple" action="AlertSettings_saveEmail" value="    Save" /></td>
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