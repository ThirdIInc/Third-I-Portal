<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="PayrollSettings" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Payroll
					Settings </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><s:submit cssClass="save"
						action="PayrollSettings_saveApplicationSetting" theme="simple"
						onclick="return myapplication();" value=" Save" /></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="5">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Enable Processing </strong></td>
				</tr>
				<s:hidden name="salDurJoinFlag" id="salDurJoinFlag" />
				<s:hidden name="salDurLeaveFlag" id="salDurLeaveFlag" />
				<tr>
					<td width="25%"><label name="enable.divwise"
						id="enable.divwise" ondblclick="callShowDiv(this);"><%=label.get("enable.divwise")%></label>
					<font color="red">*</font> :</td>
					<td width="25%"><input type="checkbox" name="divFlag"
						disabled="true" id="divFlag" checked="checked" /></td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
				<tr>
					<td width="25%"><label name="enable.brnwise"
						id="enable.brnwise" ondblclick="callShowDiv(this);"><%=label.get("enable.brnwise")%></label>
					:</td>
					<td width="25%"><s:checkbox name="brnFlag" id="brnFlag" /></td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
				<tr>
					<td width="25%"><label name="enable.deptwise"
						id="enable.deptwise" ondblclick="callShowDiv(this);"><%=label.get("enable.deptwise")%></label>
					:</td>
					<td width="25%"><s:checkbox name="deptFlag" id="deptFlag" /></td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
				<tr>
					<td width="25%"><label name="enable.emptypewise"
						id="enable.emptypewise" ondblclick="callShowDiv(this);"><%=label.get("enable.emptypewise")%></label>
					:</td>
					<td width="25%"><s:checkbox name="emptypeFlag"
						id="emptypeFlag" /></td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
				<tr>
					<td width="25%"><label name="enable.paybillwise"
						id="enable.paybillwise" ondblclick="callShowDiv(this);"><%=label.get("enable.paybillwise")%></label>
					:</td>
					<td width="25%"><s:checkbox name="paybillFlag"
						id="paybillFlag" /></td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td colspan="5">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Enables Work Flow </strong></td>
				</tr>
				<tr>
					<td width="25%"><label name="enable.recovery"
						id="enable.recovery" ondblclick="callShowDiv(this);"><%=label.get("enable.recovery")%></label>
					:</td>
					<td width="25%"><s:checkbox name="recoveryFlag"
						id="recoveryFlag" /></td>
					<!--<td colspan="1" width="25%"><label name="recovery.debitCode"
						id="recovery.debitCode" ondblclick="callShowDiv(this);"><%=label.get("recovery.debitCode")%></label>
					:</td>
					<td colspan="1" width="25%" nowrap="nowrap"><s:textfield
						name="recDebitName" readonly="true" size="25" /><s:hidden
						name="recDebitCode" /> <img src="../pages/images/search2.gif"
						height="16" align="absmiddle" width="16" theme="simple"
						onclick="javascript:callsF9(500,325,'PayrollSettings_f9RecDebit.action');"
						id="ctrlHide"></td>
				-->
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>

				<!-- commented by Mangesh <tr>
					<td colspan="1" width="25%"><label name="enable.professionalTax" id="enable.professionalTax" ondblclick="callShowDiv(this);"><%= label.get("enable.professionalTax")%></label> :</td>
					<td colspan="3" width="75%"><s:checkbox name="professionalTaxFlag"
						id="professionalTaxFlag" /></td>
				</tr> -->

				<tr>
					<td width="25%"><label name="enable.suspWrkFlw"
						id="enable.suspWrkFlw" ondblclick="callShowDiv(this);"><%=label.get("enable.suspWrkFlw")%></label>
					:</td>
					<td width="25%"><s:checkbox name="suspWorkFlag"
						id="suspWorkFlag" /></td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>

				<tr>
					<td width="25%"><label name="enable.taxWorkflow"
						id="enable.taxWorkflow" ondblclick="callShowDiv(this);"><%=label.get("enable.taxWorkflow")%></label>
					:</td>
					<td width="25%"><s:checkbox name="taxWorkFlag"
						id="taxWorkFlag" /></td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
				<tr>
					<td width="25%"><label name="enable.extrawork"
						id="enable.extrawork" ondblclick="callShowDiv(this);"><%=label.get("enable.extrawork")%></label>
					:</td>
					<td width="25%"><s:checkbox name="extraWorkFlag"
						id="extraWorkFlag" /></td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="5">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Chose Provident Fund Type
					Applicable for the Organization </strong></td>
				</tr>
				<tr>
					<td width="25%"><label name="pf.epf" id="pf.epf"
						ondblclick="callShowDiv(this);"><%=label.get("pf.epf")%></label> :</td>
					<td width="25%"><s:checkbox theme="simple" name="sEPFflag" /></td>
					<td width="25%"><label name="pf.gpf" id="pf.gpf"
						ondblclick="callShowDiv(this);"><%=label.get("pf.gpf")%></label>:</td>
					<td width="25%"><s:checkbox theme="simple" name="sGPFflag" /></td>
				</tr>

				<tr>
					<td width="25%"><label name="pf.vpf" id="pf.vpf"
						ondblclick="callShowDiv(this);"><%=label.get("pf.vpf")%></label> :</td>
					<td width="25%"><s:checkbox theme="simple" name="sVPFflag" /></td>
					<td width="25%"><label name="pf.pftrust" id="pf.pftrust"
						ondblclick="callShowDiv(this);"><%=label.get("pf.pftrust")%></label>:</td>
					<td width="25%"><s:checkbox theme="simple" name="sPFTflag" /></td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td colspan="5">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Calculation Settings </strong></td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="joinmnthpaycal"
						id="joinmnthpaycal" ondblclick="callShowDiv(this);"><%=label.get("joinmnthpaycal")%></label>
					:</td>
					<td width="75%" colspan="3"><input type="radio" name="joinPay"
						id="joinPay" value="Y" />30 Days a Month <input type="radio"
						name="joinPay" id="joinPay1" value="N" />Max Days in Month</td>
				</tr>

				<tr>
					<td colspan="1" width="25%"><label name="sepmnthpaycal"
						id="sepmnthpaycal" ondblclick="callShowDiv(this);"><%=label.get("sepmnthpaycal")%></label>
					:</td>
					<td width="75%" colspan="3"><input type="radio" name="sepPay"
						id="sepPay" value="Y" />30 Days a Month <input type="radio"
						name="sepPay" id="sepPay1" value="N" />Max Days in Month</td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="recperpage"
						id="recperpage" ondblclick="callShowDiv(this);"><%=label.get("recperpage")%></label>
					:</td>
					<td width="75%" colspan="3"><s:textfield name="recordsPerPage"
						size="10" maxlength="4" onkeypress="return numbersOnly();" /></td>
				</tr>
			</table>
		</td>
	</tr>
		<tr>
			<td colspan="5">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Rounding options </strong></td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="creditRound"
						id="creditRound" ondblclick="callShowDiv(this);"><%=label.get("creditRound")%></label>
					:</td>
					<td width="75%" colspan="3"><s:select
						list="#{'0':'No Round','1':'Round','2':'Floor','3':'Ceil','4':'Round To Next 10'}"
						name="creditRound"></s:select></td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="totalCreditRound"
						id="totalCreditRound" ondblclick="callShowDiv(this);"><%=label.get("totalCreditRound")%></label>
					:</td>
					<td width="75%" colspan="3"><s:select
						list="#{'0':'No Round','1':'Round','2':'Floor','3':'Ceil','4':'Round To Next 10'}"
						name="totalCreditRound"></s:select></td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="totalDebitRound"
						id="totalDebitRound" ondblclick="callShowDiv(this);"><%=label.get("totalDebitRound")%></label>
					:</td>
					<td width="75%" colspan="3"><s:select
						list="#{'0':'No Round','1':'Round','2':'Floor','3':'Ceil','4':'Round To Next 10'}"
						name="totalDebitRound"></s:select></td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="netPayRound"
						id="netPayRound" ondblclick="callShowDiv(this);"><%=label.get("netPayRound")%></label>
					:</td>
					<td width="75%" colspan="3"><s:select
						list="#{'0':'No Round','1':'Round','2':'Floor','3':'Ceil','4':'Round To Next 10'}"
						name="netPayRound"></s:select></td>
				</tr>
			</table>
		</td>
	</tr>

		<tr>
			<td colspan="3" align="left"><s:submit cssClass="save"
				action="PayrollSettings_saveApplicationSetting" theme="simple"
				onclick="return myapplication();" value=" Save" /></td>
		</tr>
	</table>
</s:form>
<script>
	callOnLoad();
	function myapplication()
	{
	  var date=document.getElementById('paraFrm_startDate').value;
	  var recPerPg=document.getElementById('paraFrm_recordsPerPage').value;
	 
	  if(date > 0 && date < 32)
	  {
	  	if(recPerPg > 0 && recPerPg <= 100)
	    	return true;
	    else
	  	{
			 alert(document.getElementById('recperpage').innerHTML.toLowerCase()+' should be in between 1 and 100');
			 document.getElementById('paraFrm_recordsPerPage').value="";
			 document.getElementById('paraFrm_recordsPerPage').focus();
			 return false;
	    }
	   
	  return true;
	  } 
	  else
	  {
	 		alert('Please enter start date properly');
	 		document.getElementById('paraFrm_startDate').value="";
	 		document.getElementById('paraFrm_startDate').focus();
			return false;
	  }
	}
	
	function callOnLoad()
	{
		if(document.getElementById('salDurJoinFlag').value == "true")
			document.getElementById('joinPay').checked = true;
		else 
			document.getElementById('joinPay1').checked = true;
		if(document.getElementById('salDurLeaveFlag').value == "true")
			document.getElementById('sepPay').checked = true;
		else
			document.getElementById('sepPay1').checked = true;
	}
</script>