<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="PasswordSettings" validate="true" id="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">Password
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
						action="PasswordSettings_savePasswordSetting" theme="simple"
						value=" Save" onclick="return saveValidation();" /></td>
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
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td><strong><label name="passexpiryset" id="passexpiryset" ondblclick="callShowDiv(this);"><%= label.get("passexpiryset")%></label> </strong></td>
				</tr>
				<tr>
					<td colspan="1" width="33%"><label name="pass.expapp" id="pass.expapp" ondblclick="callShowDiv(this);"><%= label.get("pass.expapp")%></label>:</td>
					<td colspan="3" width="67%"><s:checkbox name="expFlag"
						id="expFlag" onclick="ExpFlag()" /></td>
				</tr>
				<tr>
					<td colspan="1" width="33%"><label name="pass.expper" id="pass.expper" ondblclick="callShowDiv(this);"><%= label.get("pass.expper")%></label> :</td>
					<td width="67%" colspan="1"><s:textfield name="expPeriodicity"
						maxlength="4" onkeypress="return numbersOnly();" size="3" /></td>
				</tr>
			</table>
		</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td><strong><label name="passreuse" id="passreuse" ondblclick="callShowDiv(this);"><%= label.get("passreuse")%></label> </strong></td>
				</tr>
				<tr>
					<td colspan="1" width="33%"><label name="pass.reuse" id="pass.reuse" ondblclick="callShowDiv(this);"><%= label.get("pass.reuse")%></label> :</td>
					<td colspan="3" width="67%"><s:checkbox name="reuseFlag"
						id="reuseFlag" onclick="ReuseFlag()" /></td>
				</tr>
				<tr>
					<td colspan="1" width="33%"><label name="pass.reusecount" id="pass.reusecount" ondblclick="callShowDiv(this);"><%= label.get("pass.reusecount")%></label> :</td>
					<td colspan="3" width="67%"><s:textfield name="reusePassPed"
						maxlength="4" onkeypress="return numbersOnly();" size="3" />
					&nbsp;&nbsp; Password(s)</td>
				</tr>
			</table>
		</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td><strong><label name="acclockset" id="acclockset" ondblclick="callShowDiv(this);"><%= label.get("acclockset")%></label> </strong> </strong></td>
				</tr>
				<tr>
					<td colspan="1" width="33%"><label name="pass.lockapp" id="pass.lockapp" ondblclick="callShowDiv(this);"><%= label.get("pass.lockapp")%></label></td>
					<td colspan="3" width="67%"><s:checkbox name="lockFlag"
						id="lockFlag" onclick="LockFlag()" /></td>
				</tr>
				<tr>
					<td colspan="1" width="33%"><label name="pass.lockattmpt" id="pass.lockattmpt" ondblclick="callShowDiv(this);"><%= label.get("pass.lockattmpt")%></label> :</td>
					<td colspan="3" width="67%"><s:textfield name="lockAttmpt"
						maxlength="4" onkeypress="return numbersOnly();" size="3" /></td>
				</tr>
				<tr>
					<td colspan="1" width="33%"><label name="pass.lockhrs" id="pass.lockhrs" ondblclick="callShowDiv(this);"><%= label.get("pass.lockhrs")%></label> :</td>
					<td colspan="3" width="67%"><s:textfield name="lockPrd"
						maxlength="4" onkeypress="return numbersOnly();" size="3" /></td>
				</tr>
			</table>
		</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td><strong><label name="passcharacteristics" id="passcharacteristics" ondblclick="callShowDiv(this);"><%= label.get("passcharacteristics")%></label></strong></td>
				</tr>
				<tr>
					<td colspan="1" width="30%"><label name="pass.incalpha" id="pass.incalpha" ondblclick="callShowDiv(this);"><%= label.get("pass.incalpha")%></label> :</td>
					<td colspan="3" width="67%"><s:checkbox name="includeAlpha" /></td>
				</tr>
				<tr>
					<td colspan="1" width="30%"><label name="pass.incchar" id="pass.incchar" ondblclick="callShowDiv(this);"><%= label.get("pass.incchar")%></label> :</td>
					<td colspan="3" width="67%"><s:checkbox name="includeSpChar" /></td>
				</tr>
				<tr>
					<td colspan="1" width="30%"><label name="pass.incnum" id="pass.incnum" ondblclick="callShowDiv(this);"><%= label.get("pass.incnum")%></label> :</td>
					<td colspan="3" width="67%"><s:checkbox name="includeNum" /></td>
				</tr>
				<tr>
					<td colspan="1" width="33%"><label name="pass.incupcase" id="pass.incupcase" ondblclick="callShowDiv(this);"><%= label.get("pass.incupcase")%></label> :</td>
					<td colspan="3" width="67%"><s:checkbox name="includeUpCase" /></td>
				</tr>

				<tr>
					<td colspan="1" width="30%"><label name="pass.minlength" id="pass.minlength" ondblclick="callShowDiv(this);"><%= label.get("pass.minlength")%></label> :</td>
					<td colspan="3" width="67%"><s:textfield name="minPwdLen"
						size="3" maxlength="2" /></td>
				</tr>

				<tr>
					<td colspan="1" width="30%"><label name="pass.maxlength" id="pass.maxlength" ondblclick="callShowDiv(this);"><%= label.get("pass.maxlength")%></label> :</td>
					<td colspan="3" width="67%"><s:textfield name="maxPwdLen"
						size="3" maxlength="2" /></td>
				</tr>
			</table>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td><strong><label name="secureauthenti" id="secureauthenti" ondblclick="callShowDiv(this);"><%= label.get("secureauthenti")%></label></strong></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<!--<tr>
					<td colspan="1" width="33%"><label name="virkey.enable" id="virkey.enable" ondblclick="callShowDiv(this);"><%= label.get("virkey.enable")%></label> :</td>
					<td colspan="3" width="67%"><s:checkbox name="virtualKey" /></td>
				</tr>
				<tr>
					<td colspan="1" width="33%"><label name="virkey.enforce" id="virkey.enforce" ondblclick="callShowDiv(this);"><%= label.get("virkey.enforce")%></label> :</td>
					<td colspan="3" width="67%"><s:checkbox name="enforceKey" /></td>
				</tr>

				--><tr>
					<td colspan="1" width="33%"><label name="enable.imgtxt" id="enable.imgtxt" ondblclick="callShowDiv(this);"><%= label.get("enable.imgtxt")%></label>:</td>
					<td colspan="3" width="67%"><s:checkbox name="enableTxtImg" /></td>
				</tr>
					<tr>
					<td colspan="1" width="33%"><label name="firstlogin" id="firstlogin" ondblclick="callShowDiv(this);"><%= label.get("firstlogin")%></label>:</td>
					<td colspan="3" width="67%"><s:checkbox name="secureQue" /></td>
				</tr>
				
				<tr>
					<td colspan="1" width="33%"><label name="forgotQue" id="forgotQue" ondblclick="callShowDiv(this);"><%= label.get("forgotQue")%></label>:</td>
					<td colspan="3" width="67%"><s:checkbox name="forgotPassQuestion" /></td>
				</tr>
				
			</table>
		<tr>
			<td><s:submit cssClass="save"
				action="PasswordSettings_savePasswordSetting" theme="simple"
				value=" Save" onclick="return saveValidation();" /></td>
		</tr>
	</table>
</s:form>
<script>

	callOnLoad();
	function callOnLoad()
	{
		ExpFlag();
	    ReuseFlag();
	    LockFlag();
	}
		function saveValidation()
		{
		if(document.getElementById('expFlag').checked==true)
		{
			if(document.getElementById('paraFrm_expPeriodicity').value=="")
			{
				alert('Please Enter Expiry Periodicity ');
				return false;
			}
			if(document.getElementById('paraFrm_expPeriodicity').value=='0')
				{
				alert('Please Enter Expiry Periodicity Greater than zero ');
				return false;
				}
		}
	
		if(document.getElementById('reuseFlag').checked==true)
		{
			if(document.getElementById('paraFrm_reusePassPed').value=="")
			{
				alert('Please Enter Reuse Password Periodicity  ');
				return false;
			}
			if(document.getElementById('paraFrm_reusePassPed').value=='1')
				{
				alert('Please Enter Reuse Password Periodicity Greater than one ');
				return false;
				}
		}
	
		if(document.getElementById('lockFlag').checked==true)
		{
		if(document.getElementById('paraFrm_lockAttmpt').value=="")
			{
				alert('Please Enter Lock Attempts   ');
				return false;
			}
			if(document.getElementById('paraFrm_lockPrd').value=="")
			{
				alert('Please Enter Lock Periodicoty   ');
				return false;
			}
			
		}
		var minPassValue = document.getElementById('paraFrm_minPwdLen').value;
		var maxPassValue = document.getElementById('paraFrm_maxPwdLen').value;
		if(maxPassValue != "" & minPassValue =="")
		{
			alert("Please enter minimum length of password");
			document.getElementById('paraFrm_minPwdLen').focus();
			return false;
		}
		if(minPassValue != "" & eval(minPassValue) < 4)
		{
			alert("Minimum Password length should be greater than or equal to 4");
			document.getElementById('paraFrm_minPwdLen').focus();
			return false;
		}
		if(minPassValue != "" & maxPassValue =="")
		{
			alert("Please enter maximum length of password");
			document.getElementById('paraFrm_minPwdLen').focus();
			return false;
		}
		if(maxPassValue != "" & minPassValue != "" & eval(minPassValue) > eval(maxPassValue))
		{
			alert("Maximum Password length cannot be less than minimum password length");
			document.getElementById('paraFrm_maxPwdLen').focus();
			return false;
		}
		return true;
	}
	
	function ExpFlag()
	{
		if(document.getElementById('expFlag').checked==true)
		{
	
			document.getElementById('paraFrm_expPeriodicity').disabled=false;
		}
		else
		{
		
			document.getElementById('paraFrm_expPeriodicity').value="";
			document.getElementById('paraFrm_expPeriodicity').disabled=true;
		}
	}
	function ReuseFlag()
	{
		if(document.getElementById('reuseFlag').checked==true)
			document.getElementById('paraFrm_reusePassPed').disabled=false;
	
		else
		{
			document.getElementById('paraFrm_reusePassPed').value="";
			document.getElementById('paraFrm_reusePassPed').disabled=true;
		}
	}
	
	function LockFlag()
	{
		if(document.getElementById('lockFlag').checked==true)
		{
			document.getElementById('paraFrm_lockPrd').disabled=false;
			document.getElementById('paraFrm_lockAttmpt').disabled=false;
		}
		else
		{
			document.getElementById('paraFrm_lockPrd').value="";
			document.getElementById('paraFrm_lockAttmpt').value="";
			document.getElementById('paraFrm_lockPrd').disabled=true;
			document.getElementById('paraFrm_lockAttmpt').disabled=true;
		}
	}
</script>