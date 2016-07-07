<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="AnniversaryMailSettings" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="100%" class="txt"><strong class="text_head">Anniversary
					Mail Settings </strong></td>
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
					<td colspan="4"><s:submit cssClass="save"
						action="AnniversaryMailSettings_save" theme="simple" value=" Save"
						onclick="return callSave(); " /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="25%">
						<label name="enable.autoannivmails" id="enable.autoannivmails" 
						 ondblclick="callShowDiv(this);"><%=label.get("enable.autoannivmails")%></label>
						:
					</td>
					<td width="10%">
						<s:checkbox name="autoFlag" id="autoFlag" />
					</td>
					<td width="25%">
						<label name="mailSentOn" id="mailSentOn" 
						 ondblclick="callShowDiv(this);"><%=label.get("mailSentOn")%></label>
						:
					</td>
					<td width="40%">
						<s:textfield name="mailSentOnTiming" onkeypress="return numbersWithColon();"/>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">

				<tr>
					<td class="formtext"><b>Send Mail To :</b></td>
				</tr>

				<tr>
					<td class="formtext" width="25%"><label name="ind.department" id="ind.department" ondblclick="callShowDiv(this);"><%=label.get("ind.department") %></label> :</td>
					<td nowrap="nowrap" width="10%"><s:checkbox name="indDptFlag"
						id="indDptFlag" onclick="toggle('DptFlag','0');"/></td>
					<td width="20%">
					<label name="all.department" id="all.department" ondblclick="callShowDiv(this);"><%=label.get("all.department") %></label> :</td>
					<td width="40%"><s:checkbox
						name="allDptFlag" id="allDptFlag" onclick="toggle('DptFlag','1');"/></td>
				</tr>

				<tr>
					<td width="25%"><label name="ind.branch" id="ind.branch" ondblclick="callShowDiv(this);"><%=label.get("ind.branch") %></label> :</td>
					<td width="10%"><s:checkbox name="indBrnFlag"
						id="indBrnFlag" onclick="toggle('BrnFlag','0');"/></td>
					<td width="20%">
					<label name="all.branch" id="all.branch" ondblclick="callShowDiv(this);"><%=label.get("all.branch") %></label> :</td>
					<td width="40%" height="22" colspan="1"><s:checkbox
						name="allBrnFlag" id="allBrnFlag" onclick="toggle('BrnFlag','1');"/></td>
				</tr>

				<tr>
					<td width="25%"><label name="ind.designation" id="ind.designation" ondblclick="callShowDiv(this);"><%=label.get("ind.designation") %></label> :</td>
					<td width="10%"><s:checkbox name="indDesgFlag"
						id="indDesgFlag" onclick="toggle('DesgFlag','0');"/></td>
					<td width="20%">
					<label name="all.designation" id="all.designation" ondblclick="callShowDiv(this);"><%=label.get("all.designation") %></label> :</td>
					<td width="40%" height="22" colspan="1"><s:checkbox
						name="allDesgFlag" id="allDesgFlag" onclick="toggle('DesgFlag','1');"/></td>
				</tr>

				<tr>
					<td width="25%"><label name="ind.division" id="ind.division" ondblclick="callShowDiv(this);"><%=label.get("ind.division") %></label> :</td>
					<td width="10%"><s:checkbox name="indDivFlag"
						id="indDivFlag" onclick="toggle('DivFlag','0');"/></td>
					<td width="20%">
					<label name="all.division" id="all.division" ondblclick="callShowDiv(this);"><%=label.get("all.division") %></label> :</td>
					<td width="40%"><s:checkbox
						name="allDivFlag" id="allDivFlag" onclick="toggle('DivFlag','1');"/></td>
				</tr>
				
				<tr>
					<td width="25%"><label name="individual.employee" id="individual.employee"
						ondblclick="callShowDiv(this);"><%=label.get("individual.employee")%></label>
					:</td>
					<td width="10%"><s:checkbox name="allEmpFlag" id="allEmpFlag"
						onclick="toggle('EmpFlag','2');" /></td>
					<!--  Updated by Anantha lakshmi -->
					<td width="25%"><label name="individual.groupId" id="individual.groupId"
						ondblclick="callShowDiv(this);"><%=label.get("individual.groupId")%></label>
					:</td>
					<td width="10%"><s:checkbox name="groupId" id="groupId"
						onclick="unCheckAll();" /></td>
				</tr>
				
					<tr>
					<td></td>
					<td></td>
					<td></td>
					<td width="10%">
						<s:textarea  rows="4" cols="50" name="groupMailId" id="mailId" />
					</td>	
				</tr>
				
				<tr>
					<td colspan="4">
					<table>
					<tr>
					<td width="15%"><label name="template" id="template" ondblclick="callShowDiv(this);"><%=label.get("template") %></label> :</td>
					<td colspan="2" width="40%"><s:textfield size="40"
						name="tempName" readonly="true" /> <s:hidden name="tempCode" />
					<img src="../pages/common/css/default/images/search2.gif"
						width="16" height="15"
						onclick="javascript:callsF9(500,325,'AnniversaryMailSettings_f9mailTemplate.action');">&nbsp;&nbsp;&nbsp;
					<input type="button" name="preview" class="token" value="Preview" onclick="previewTemplate();"></td>
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


function unCheckAll(){
	try{
	if(document.getElementById('groupId').checked)
	{
		document.getElementById('indDptFlag').checked=false;
		document.getElementById('allDptFlag').checked=false;
		document.getElementById('indBrnFlag').checked=false;
		document.getElementById('allBrnFlag').checked=false;
		document.getElementById('indDesgFlag').checked=false;
		document.getElementById('allDesgFlag').checked=false;
		document.getElementById('indDivFlag').checked=false;
		document.getElementById('allDivFlag').checked=false;
		document.getElementById('allEmpFlag').checked=false;
		
		document.getElementById('indDptFlag').disabled=true;
		document.getElementById('allDptFlag').disabled =true;
		document.getElementById('indBrnFlag').disabled =true;
		document.getElementById('allBrnFlag').disabled =true;
		document.getElementById('indDesgFlag').disabled =true;
		document.getElementById('allDesgFlag').disabled =true;
		document.getElementById('indDivFlag').disabled =true;
		document.getElementById('allDivFlag').disabled =true;
		document.getElementById('allEmpFlag').disabled=true;
	}
	else{
		document.getElementById('indDptFlag').disabled=false;
		document.getElementById('allDptFlag').disabled=false;
		document.getElementById('indBrnFlag').disabled=false;
		document.getElementById('allBrnFlag').disabled=false;
		document.getElementById('indDesgFlag').disabled=false;
		document.getElementById('allDesgFlag').disabled=false;
		document.getElementById('indDivFlag').disabled=false;
		document.getElementById('allDivFlag').disabled=false;
		document.getElementById('allEmpFlag').disabled=false;
	}
	
}catch(e){alert("e "+e);}	
}	
	
function previewTemplate()
{
	tempCode = document.getElementById('paraFrm_tempCode').value;
	if(tempCode == "")
	{
		alert("Please select "+document.getElementById('template').innerHTML.toLowerCase());
		return false;
	}
	window.open("AnniversaryTemplate_setTemplateDataForPreview.action?tempCode="+tempCode,"","height=550,width=700");
}

function toggle(id,type)
{
	if(type==0)
	{
		if(document.getElementById('ind'+id).checked)
			document.getElementById('all'+id).checked=false;
		else
			document.getElementById('all'+id).checked=true;
	}
	else
	{
		if(document.getElementById('all'+id).checked)
			document.getElementById('ind'+id).checked=false;
		else
			document.getElementById('ind'+id).checked=true;
	}
}


function toggle(id,type)
{
	if(type==0)
	{	document.getElementById('allEmpFlag').checked=false;
		if(document.getElementById('ind'+id).checked)
			document.getElementById('all'+id).checked=false;
		else
			document.getElementById('all'+id).checked=true;
	}
	if(type==2)
	{
		if(document.getElementById('all'+id).checked){
			document.getElementById('indDptFlag').checked=false;
			document.getElementById('allDptFlag').checked=false;
			
			document.getElementById('indBrnFlag').checked=false;
			document.getElementById('allBrnFlag').checked=false;
			
			document.getElementById('indDesgFlag').checked=false;
			document.getElementById('allDesgFlag').checked=false;
			
			document.getElementById('indDivFlag').checked=false;
			document.getElementById('allDivFlag').checked=false;			
			}
		
	}
	else
	{	document.getElementById('allEmpFlag').checked=false;
		if(document.getElementById('all'+id).checked)
			document.getElementById('ind'+id).checked=false;
		else
			document.getElementById('ind'+id).checked=true;
	}
}

unCheckAll();
</script>
