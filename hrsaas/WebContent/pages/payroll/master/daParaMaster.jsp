<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="DaParaMaster" id="paraFrm" validate="true" target="main"
	theme="simple">
	<table width="100%">
		<tr>
			<td colspan="4" class="pageHeader" align="center">DA Parameter</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td width="25%" colspan="1">&nbsp;</td>
			<td width="20%" colspan="1">Select DA Code:</td>

			<td colspan="2" width="55%"><s:hidden theme="simple"
				name="daParaMaster.daCode" /> <img src="../pages/images/search.gif" class="iconImage"
				height="18" align="absmiddle" width="18"
				onclick="javascript:callsF9(500,325,'DaParaMaster_f9action.action');">
			</td>
		</tr>

		<tr>
			<td width="25%" colspan="1">&nbsp;</td>
			<td width="20%" colspan="1">DA Rate :<font color="red">*</font></td>

			<td colspan="2" width="55%"><s:textfield
				label="%{getText('daRate')}" maxlength="3"
				onkeypress="return numbersonly(this);" theme="simple"
				name="daParaMaster.daRate" /></td>
		</tr>

		<tr>
			<td width="25%" colspan="1">&nbsp;</td>
			<td width="20%" colspan="1">DA Effect Date :<font color="red">*</font>
			</td>

			<td colspan="2" width="55%"><s:textfield
				label="%{getText('daEffDate')}" theme="simple"
				name="daParaMaster.daEffDate" /> <s:a
				href="javascript:NewCal('daParaMaster.daEffDate','DDMMYYYY');">
				<img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"
					width="16">
			</s:a></td>
		</tr>

		<tr>
			<td>&nbsp;</td>
		</tr>



		<tr>

			<td align="center" colspan="4"><s:submit cssClass="pagebutton"
				action="DaParaMaster_save" theme="simple" value="   Save   "
				onclick="return call();" />&nbsp; <s:submit cssClass="pagebutton"
				action="DaParaMaster_reset" theme="simple" value="  Reset  " />&nbsp;
			<!--<s:submit cssClass="pagebutton" action="DaParaMaster_delete" theme="simple"
				value="  Delete  " onclick="return callDel();"/>&nbsp;  --><input
				type="button" onclick="callReport('DaParaMaster_report.action')"
				value="  Report " /></td>
		</tr>
	</table>
</s:form>
<script>
  	function callReport(name){
  	
  	document.getElementById('paraFrm').target="_blank";
  	document.getElementById('paraFrm').action=name;
  	document.getElementById('paraFrm').submit();
  	document.getElementById('paraFrm').target="main";	
  	}
  	
  	function call() 
	{
			var effdate=document.getElementById('daParaMaster.daEffDate').value;
  			if(document.getElementById('daParaMaster.daRate').value=="") 
  			{
  			alert("Please Enter DA Rate.");
  			return false;
  			}
  			else
  			if(document.getElementById('daParaMaster.daRate').value>=100){
  			alert("Please Enter DA Rate below 100");
  			return false;
  			}
  			 
  			
  			if(document.getElementById('daParaMaster.daEffDate').value=="") 
  			{
  			alert("Please Select DA Effective Date.");
  			return false;
  			}
  			else
  				var checkToDate = validateDate(effdate);
  				if(!checkToDate){
				alert (" Please enter valid DA Effective Date !\nDate should be DD-MM-YYYY format!");
				return false;
			}
  			
  			
  			return true;
  	}
  	
  	function callDel() 
	{
  			if(document.getElementById('daParaMaster.daCode').value=="") 
  			{
  			alert("Please Select DA.");
  			return false;
  			} 
  			else {
  			var conf=confirm("Are you sure to delete this record?");
  			if(conf) {
  				return true;
  			}
  			else
  			{
  				return false;
  			}
  			}
  	}
  	
  	function numbersonly(myfield)
	{
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else
			return true;
		
		keychar = String.fromCharCode(key);	
		if ((("0123456789.").indexOf(keychar) > -1))
			return true;	
		else {
			myfield.focus();
			return false;
		}
	}
	
	function validateDate(fld) {
    	var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[-](0?[13578]|1[02])[-]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[-](0?[13456789]|1[012])[-]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[-]0?2[-]((1[6-9]|[2-9]\d)?\d{2}))|(29[-]0?2[-]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
    	if (!((fld.match(RegExPattern)) && (fld!=''))){
        return false;
    	}
    return true;
   	}
  			
  
  	</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
