
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="LTCConfig" method="post" id="paraFrm" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">LTC
					Configuration </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>

			<td width="100%" colspan="3"><s:submit action="LTCConfig_save"
				cssClass="save" value="  Save" onclick="return validate();" /> <s:submit
				name="" value="  Reset" action="LTCConfig_reset" cssClass="reset"></s:submit></td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="1" width="20%"><label name="ltc.ltcHead"
						id="credit.name" ondblclick="callShowDiv(this);"><%=label.get("ltc.ltcHead")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:hidden name="creditCode"
						value="%{creditCode}" theme="simple" /> <s:textfield
						name="creditName" theme="simple" size="30" readonly="true" /> <img
						id="ctrlHide" src="../pages/images/recruitment/search2.gif"
						height="18" class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'LTCConfig_f9credit.action');">

					</td>

				</tr>

				<tr>
					<td colspan="1" width="35%"><label class="set" id=""
						name="ltc.attnDays" ondblclick="callShowDiv(this);"><%=label.get("ltc.attnDays")%></label>
					:</td>
					<td colspan="3" width="75%"><s:checkbox name="attnDays"
						value="attnDays" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label class="set" id=""
						name="ltc.leaveDays" ondblclick="callShowDiv(this);"><%=label.get("ltc.leaveDays")%></label>
					:</td>
					<td colspan="3" width="75%"><s:checkbox name="leaveDays"
						value="leaveDays" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label class="set" id=""
						name="ltc.trvlDays" ondblclick="callShowDiv(this);"><%=label.get("ltc.trvlDays")%></label>
					:</td>
					<td width="75%" colspan="3"><s:checkbox name="trvlDays"
						value="trvlDays" /></td>
				</tr>

			</table>
			</td>
		</tr>


		<tr>

			<td width="100%" colspan="3"><s:submit action="LTCConfig_save"
				cssClass="save" value="  Save" onclick="return validate();" /> <s:submit
				name="" value="  Reset" action="LTCConfig_reset" cssClass="reset"></s:submit></td>

		</tr>
	</table>


</s:form>


<script>


	function validate()
	{
	   var creditName = document.getElementById('paraFrm_creditCode').value;
	 
	 if(creditName=="")
					 {
					 	alert("Please select LTC Head");
					 	return false;
					 }
	  
	 return true;
	
	}
	
	

</script>


