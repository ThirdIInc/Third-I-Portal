<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="CcaParaMaster" id="paraFrm" validate="true" target="main">
	<table  width="100%">
		<tr>
			<td width="100%" colspan="4" class="pageHeader">
			<p align="center">CCA Parameter
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>

		<tr>
		<td width="25%" colspan="1" >&nbsp;</td>
			<td >Select CCA Code:</td>
				
			<td colspan="2" width="55%" ><s:hidden
				theme="simple" name="ccaParaMaster.ccaCode" /> 
				<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,400,'CcaParaMaster_f9action.action');">
			</td>
		</tr>

		<tr>
		<td width="25%" colspan="1" >&nbsp;</td>
			<td width="25%" colspan="1" >Equivalent Salary From<font color="red">*</font>:</td>
			<td colspan="2" width="55%"><s:textfield label="%{getText('equiSalFrom')}" 
				theme="simple" name="ccaParaMaster.equiSalFrom" />
				<s:a href="javascript:NewCal('ccaParaMaster.equiSalFrom','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a></td>
		</tr>
		<tr>
			<td width="25%" colspan="1" >&nbsp;</td>
			<td width="20%" colspan="1" >Equivalent  Salary To<font color="red">*</font>:</td>
			<td colspan="2" width="55%"><s:textfield label="%{getText('equiSalTo')}" 
				theme="simple" name="ccaParaMaster.equiSalTo" />
				<s:a href="javascript:NewCal('ccaParaMaster.equiSalTo','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a></td>
		</tr>
		
		<tr>
			<td width="25%" colspan="1" >&nbsp;</td>
			<td width="20%" colspan="1" >CCA Amount<font color="red">*</font>:</td>
			<td colspan="2" width="55%"><s:textfield label="%{getText('ccaAmt')}" maxlength="8" onkeypress="return numbersonly(this);"
				theme="simple" name="ccaParaMaster.ccaAmt" /></td>
		</tr>
		<tr>
			<td  >&nbsp;</td>
		</tr>
		
      <tr>
       
          <td align="center" colspan="4">	
  		
  		<s:submit cssClass="pagebutton" action="CcaParaMaster_save"
				theme="simple" value="   Save   " onclick="return callForSave()"/>&nbsp; <s:submit cssClass="pagebutton"
				action="CcaParaMaster_reset" theme="simple" value="  Reset  " />&nbsp;
			<s:submit cssClass="pagebutton" action="CcaParaMaster_delete" theme="simple"
				value="  Delete  " onclick="return deleteCca();"/>&nbsp;  <input type="button" class="pagebutton"
				onclick="callReport('CcaParaMaster_report.action')" value="  Report " />
  		
  		 </td>
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
  	
  		function deleteCca() 
	{
	
  			if((document.getElementById('ccaParaMaster.ccaCode').value=="")) 
  			{
  			alert("Please Select CCA.");
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
  	
  	
  	function callForSave() 
	{
	
  			if((document.getElementById('ccaParaMaster.equiSalFrom').value=="")) 
  			{
  			alert("Please Select Equivalent Salary From. ");
  			return false;
  			} 
  			else if((document.getElementById('ccaParaMaster.equiSalTo').value=="")) 
  			{
  			alert("Please Select Equivalent Salary To. ");
  			return false;
  			} 
  			else if((document.getElementById('ccaParaMaster.ccaAmt').value=="")) 
  			{
  			alert("Please Enter CCA Amount. ");
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
  	</script>
		<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
		
		
		
		