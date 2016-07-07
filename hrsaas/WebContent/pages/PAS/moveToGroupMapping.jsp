<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="SectionMapping" theme="simple" method="post"
	name="paraFrm" id="paraFrm">
	<s:hidden name="check" value="%{check}" />
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Section
					Mapping</strong></td>
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
			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td height="3" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td width="25%" colspan="1" height="20" class="formtext"><label
								name="move.group" class="set" id="move.group"
								ondblclick="callShowDiv(this);"><%=label.get("move.group")%></label>
							<font color="red">*</font> :</td>
							<td width="25%" colspan="1" height="20" nowrap="nowrap"><s:select
								name="moveGroupName" headerKey="" headerValue="--Select--"
								list="%{hashmapGroup}" cssStyle="width:153" /></td>
							<td width="25%" colspan="1" height="20"><input type="button"
								value=" Move" class="token" onclick="return callMove();" /></td>
							<td width="25%" colspan="1" height="20"></td>
							<s:hidden name="apprId" />
							<s:hidden name="moveEmpId" />
						</tr>
						<tr>

							<td width="25%" colspan="4" height="20" align="center"
								class="formtext">
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
		function callMove(){
			if(document.getElementById("paraFrm_moveGroupName").value==""){
			alert("Please select group name to move.");
			return false;
			}
			else{
			//document.getElementById("paraFrm").action="AppraiserConfig_moveToGroup.action"; 
			//document.getElementById("paraFrm").submit();
			
			var empId= document.getElementById("paraFrm_moveEmpId").value;
			var moveGroupName= document.getElementById("paraFrm_moveGroupName").value;
			opener.document.getElementById('paraFrm').target="main";
			opener.document.getElementById('paraFrm').action="SectionMapping_moveToGroup.action?empId="+empId+"&moveGroupName="+moveGroupName;
			opener.document.getElementById('paraFrm').submit();
			window.close();
			
		}
		window.close();
		}
</script>
