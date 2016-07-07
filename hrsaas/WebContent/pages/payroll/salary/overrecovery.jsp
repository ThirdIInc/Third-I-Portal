
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="overRecovery" id="paraFrm" validate="true" theme="simple">
	<table width="100%">
		<tr>
			<td width="100%" colspan="4" class="pageHeader">
			<p align="center">Over Recovery
			</td>
			<s:hidden name="recoverCode" />
		</tr>
		<tr>
			<td width="25%" colspan="1">Select Application :</td>
			<td width="75%" colspan="3"><img
				src="../pages/images/search.gif" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(700,325,'overRecovery_f9code.action');"></td>
		</tr>
		<tr>
			<td width="25%" colspan="1">Select Employee <font color="red">*</font>:</td>

			<td width="75%" colspan="3"><s:hidden name="empid" /> <img
				src="../pages/images/search.gif" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(600,325,'overRecovery_f9action.action');" />
			<s:textfield theme="simple" name="emp_token" readonly="true"
				size="10" /> <s:textfield theme="simple" name="empnm" size="70"
				readonly="true" /></td>
		</tr>


		<tr>
			<td width="20%">Center:</td>
			<td width="30%"><s:textfield theme="simple" readonly="true"
				name="center" size="30" /></td>
			<td width="20%">Rank:</td>
			<td width="30%"><s:textfield theme="simple" name="rank"
				size="30" readonly="true" /></td>
		</tr>
<tr>
			<td width="100%" colspan="4" >&nbsp;
			</td></tr>
		<tr>
			<td width="25%">Over Recovery Of Month <font color="red">*</font>:</td>
			<td width="15%"><s:select name="Recovermonth" 
				list="#{'0':'Select','1':'JAN','2':'FEB','3':'MAR','4':'APR','5':'MAY','6':'JUN','7':'JUL','8':'AUG','9':'SEP','10':'OCT','11':'NOV','12':'DEC'}" />
			</td>
			<td width="20%">Over Recovery Of Year<font color="red">*</font>:</td>
			<td width="30%"><s:textfield name="Recoveryear" maxlength="4" onkeypress="return numbersonly(this);" /></td>
		</tr>
		<tr>
			<td width="20%">Over Recovery Amount<font color="red">*</font>:</td>
			<td width="30%"><s:textfield name="Recoveramount" onkeyup="payAmt()" maxlength="8" onkeypress="return numbersonly(this);"/></td>
			<td width="20%">Under Debit Head</td>
			<td width="30%">
			<s:hidden  name="debitcode" />
			<s:textfield name="debitHead" maxlength="15" /> <img
				src="../pages/images/search.gif" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,325,'overRecovery_Debitaction.action');" />
			</td>

		</tr>
		
		
		<tr>
			<td width="100%" colspan="4" >&nbsp;
			</td></tr>
		
    <tr>
    <td width="20%">Pay in Month<font color="red">*</font>:</td>
        <td width="15%"><s:select name="Paymonth" list="#{'0':'Select','1':'JAN','2':'FEB','3':'MAR','4':'APR','5':'MAY','6':'JUN','7':'JUL','8':'AUG','9':'SEP','10':'OCT','11':'NOV','12':'DEC'}"  />
        </td>
            <td width="20%">Pay in Year<font color="red">*</font>:</td>
                <td width="30%"> <s:textfield name="Payyear" maxlength="4" onkeypress="return numbersonly(this);"/></td>
    </tr>
  		<tr>
    <td width="20%" >Pay Amount<font color="red">*</font>:</td>
        <td width="30%"><s:textfield name="Payamount" maxlength="8" readonly="true"/></td>
            <td width="20%"></td>
                <td width="30%"> </td>
    </tr>
    
    
    
    <tr>
    <td width="20%" colspan="1">Remarks:</td>
               <td width="30%" colspan="3"><s:textarea name="Remarks" rows="4" cols="30" /></td>
            
    </tr>
    <tr>
			<td width="100%" colspan="4" >&nbsp;
			</td></tr>
    <tr>				
						<td colspan="4" align="center">
						<s:if test="change">
						<s:submit cssClass="pagebutton" theme="simple"
							action="overRecovery_save" value="  Save  " onclick=" return callAdd();"
							 />
							 <input type="button" class="pagebutton" theme="simple"
							 value="  Delete  " onclick="calldel('overRecovery_delete.action')"
							 /></s:if>
							
							 <s:submit cssClass="pagebutton" theme="simple"
							action="overRecovery_reset" value="  Reset  "
							 /></td>
							 
				</tr>
				
				<tr>
			<td width="100%" colspan="4" ><hr>
			</td></tr>
			<tr>
			<td width="100%" colspan="4" align="center" >Generate Report
			</td></tr>
			<tr>
    <td width="20%" align="right"> Month<font color="red">*</font>:</td>
        <td width="15%"><s:select name="Repmonth" theme="simple" list="#{'0':'Select','1':'JAN','2':'FEB','3':'MAR','4':'APR','5':'MAY','6':'JUN','7':'JUL','8':'AUG','9':'SEP','10':'OCT','11':'NOV','12':'DEC'}"  />
        </td>
            <td width="20%" align="right">Year<font color="red">*</font>:</td>
                <td width="30%"> <s:textfield name="Repyear" maxlength="4" theme="simple"  onkeypress="return numbersonly(this);"/></td>
    </tr>
    <tr>
			<td width="100%" colspan="4" align="center">
			<input type="button" class="pagebutton" 
							 value="View Report" onclick=" return call('overRecovery_report.action');"
							 />
			</td></tr>
				
    
	</table>
</s:form>
<script>
callReportAdd()
{

}
function payAmt()
{
document.getElementById('Payamount').value=document.getElementById('Recoveramount').value;
}
     function call(name) {
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action=name;	
			document.getElementById('paraFrm').submit();	
			document.getElementById('paraFrm').target="main";						
	}
     function callAdd(){
    // empid;Recovermonth;Recoveryear;Recoveramount;Paymonth;Payyear;Payamount
	var empid = document.getElementById('empid').value;
	var recrmon = document.getElementById('Recovermonth').value;
	var recryr = document.getElementById('Recoveryear').value;
	var recramt = document.getElementById('Recoveramount').value;
	var paymon = document.getElementById('Paymonth').value;
	var payyr = document.getElementById('Payyear').value;
	var payamt = document.getElementById('Payamount').value;
		
		
	if((empid==""))
	{
	alert("Please Select an Employee")
	return false;
	}
	if((recrmon==0))
	{
	alert("Please Select Over Recovery of Month")
	return false;
	}
	if((recryr==""))
	{
	alert("Please Select Over Recovery of year")
	return false;
	}
	if((recramt==""))
	{
	alert("Please Select Over Recovery of amount")
	return false;
	}
	if((paymon==0))
	{
	alert("Please Select Pay in Month")
	return false;
	}
	if((payyr==""))
	{
	alert("Please Select Pay in Year")
	return false;
	}
	if((payamt==""))
	{
	alert("Please Select Pay in Amount")
	return false;
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
		if ((("0123456789").indexOf(keychar) > -1))
			return true;	
		else {
			myfield.focus();
			return false;
		}
	}
	
	function calldel(name) {
  			if(document.getElementById('recoverCode').value=="") {
  			alert("Please Select Application  ");
  			return false;
  			} else {
  			var conf=confirm("Are you sure to delete this record? ");
  			if(conf) {
  			document.getElementById('paraFrm').target="main";
			document.getElementById('paraFrm').action=name;	
			document.getElementById('paraFrm').submit();	
  			
  			}
  			}
  			}
  	
  	</script>
