<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/common/commonValidations.jsp"%>
<%@page import="java.util.HashMap"%>

<s:form action="ClosedRequisition" validate="true" id="paraFrm"
	validate="true" theme="simple">
<s:hidden name="reqCode" />
<s:hidden name="reqStatus" />
<s:hidden name="reqClosedDate" />
	<table width="100%" align="right" class="formbg">
		<tr>
			<td colspan="2">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2" width="100%">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="formbg">
						<tr>
							<td width="4%" valign="bottom" class="txt"><strong
								class="formhead"><img
								src="../pages/common/css/default/images/review_shared.gif"
								width="25" height="25" /></strong></td>
							<td width="93%" class="txt"><strong class="text_head">Closed Requisition </strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"><img
								src="../pages/images/recruitment/help.gif" width="16"
								height="16" /></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

			<tr>
				<td colspan="2" width="100%">
				<table width="100%" border="0" class="formbg">
							
							<tr>
								<td width="4%" class="txt"><label class="set"
									name="position" id="position" ondblclick="callShowDiv(this);"><%=label.get("req.name")%></label>
								:<font color="red">*</font></td>
								<td width="93%" class="txt"><s:textfield name="reqName"
									size="25" maxlength="30" readonly="true" /> <a href="#"><img
									src="../pages/common/css/default/images/search2.gif" width="16"
									height="15" border="0"
									onclick="javascript:callsF9(500,325,'ClosedRequisition_f9action.action');" /></a></td>
							</tr>
							<tr>
								<td width="4%">&nbsp;</td>
							</tr>
							<tr>
								<td width="4%" align="left">
									<s:submit  value="Change Status" onclick="return validation();" />
								</td>
							</tr>

				</table>
				</td>
			</tr>
	
	</table>

</s:form>

<script>

function validation()
{
	var name = document.getElementById('paraFrm_reqName').value;
	if(name == '')
	{
		alert("Please select Requisition Name");
		return false;
	}
	var conf = confirm("Are you sure to change the Requisition Status?");
	if(conf)
	{
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action="ClosedRequisition_closeStatus.action";
		document.getElementById('paraFrm').submit();
		
		return true;
	}
	else
	{
		return false;
	}
	return true;
}

</script>