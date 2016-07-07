<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="BonusParaMaster" id="paraFrm" validate="true" theme="simple">
	<table class="formbg" width="100%">
		<tr>	
		<td width="100%" colspan="4">		
			<table width="100%" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Bonus Parameter </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/common/css/default/images/help.gif" width="16"
						height="16" /></div>
					</td>
				</tr>
			</table>			
		</td>
		</tr>
		<tr>
			<td width="100%" colspan="4"></td>
		</tr>
		
		<tr>
			<td colspan="4" width="100%">
			<table width="100%" border="0" class="formbg" cellpadding="0" cellspacing="0">
		<tr>

			<td width="20%" colspan="1">Select Bonus Code : </td>

			<td width="80%" colspan="3"><s:hidden 
				theme="simple" name="bonusParaMaster.bonusCode" /> 
				<img src="../pages/images/search2.gif" class="iconImage" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,325,'BonusParaMaster_f9action.action');">
			</td>
		</tr>
		<tr>
		
			<td width="20%">Bonus Period From<font color="red">*</font>:</td>
			<td width="30%"><s:textfield label="%{getText('bonPrdFrom')}" id="date" 
				theme="simple" name="bonusParaMaster.bonPrdFrom" />
				<s:a href="javascript:NewCal('bonusParaMaster.bonPrdFrom','DDMMYYYY');">
				<img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="16" align="absmiddle" width="16" ></s:a></td>
		
		
			<td width="20%">Bonus Period To<font color="red">*</font>:</td>
			<td width="30%" nowrap="nowrap"><s:textfield label="%{getText('bonPrdTo')}" id="date1" 
				theme="simple" name="bonusParaMaster.bonPrdTo" />
				<s:a href="javascript:NewCal('bonusParaMaster.bonPrdTo','DDMMYYYY');"><img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a></td>
		</tr>
		
		<tr>
		
			<td width="25%">Bonus Type<font color="red">*</font>:</td>
			<td width="30%"><s:textfield label="%{getText('bonusType')}" maxlength="20"
				theme="simple" name="bonusParaMaster.bonusType" /></td>

			<td width="25%">Bonus Days Declared<font color="red">*</font>:</td>
			<td width="30%"><s:textfield label="%{getText('bonDaysDec')}" onkeypress="return numbersonly(this);" maxlength="3"
				theme="simple" name="bonusParaMaster.bonDaysDec" /></td>
		</tr>
		
		<tr>
		
			<td width="20%"> Employee Type<font color="red">*</font>:</td>
		
			<td width="30%"><s:textfield name="bonusParaMaster.bonEmpType"  id="et"   readonly="true" theme="simple"   maxlength="20" >
			 </s:textfield>
				<img src="../pages/images/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'BonusParaMaster_f9type.action');">	
				<s:hidden name="bonusParaMaster.typeCode" />
				</td>
		
			<td width="20%">Bonus Formula<font color="red">*</font>:</td>
			<td width="30%"><s:textfield label="%{getText('bonFormula')}" 
				theme="simple" name="bonusParaMaster.bonFormula" />
				
		
		</tr>
		<tr>
			<td  colspan="4">&nbsp;</td>
		</tr>
      <tr>
       
          <td align="center" colspan="4">
  		
  		<s:submit cssClass="pagebutton" action="BonusParaMaster_save"
				theme="simple" value="   Save   " onclick="return callsave()"/>&nbsp; <s:submit cssClass="pagebutton"
				action="BonusParaMaster_reset" theme="simple" value="  Reset  " />&nbsp;
			<s:submit cssClass="pagebutton" action="BonusParaMaster_delete" theme="simple"
				value="  Delete  " onclick="return callDel();"/>&nbsp;  <input type="button" class="pagebutton"
				onclick="callReport('BonusParaMaster_report.action')" value="  Report " />	
  		
  		 </td>
      </tr>
     </table></td></tr></table></s:form>
     
     <script>
  	function callReport(name){
  	
  	document.getElementById('paraFrm').target="_blank";
  	document.getElementById('paraFrm').action=name;
  	document.getElementById('paraFrm').submit();
  	document.getElementById('paraFrm').target="main";	
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
  	
  	function callDel() {
  			if(document.getElementById('bonusParaMaster.bonusCode').value=="") {
  			alert("Please Select Bonus Code.");
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
		
		function callsave() 
	{
  			var bonus = document.getElementById('bonusParaMaster.bonusType').value;
  			
  			
  			if( document.getElementById('bonusParaMaster.bonPrdFrom').value=="")
  			{
  				alert("Please Enter Bonus Period From. ");
  				return false;
  			}
  			
  			if( document.getElementById('bonusParaMaster.bonPrdTo').value=="")
  			{
  				alert("Please Enter Bonus Period To. ");
  				return false;
  			}
  			
  			if( document.getElementById('bonusParaMaster.bonusType').value=="")
  			{
  				alert("Please Enter Bonus Type. ");
  				return false;
  			}
  			
  			if( document.getElementById('bonusParaMaster.bonDaysDec').value=="")
  			{
  				alert("Please Enter Bonus Days Declared. ");
  				return false;
  			}
  			
  			
  			if( document.getElementById('bonusParaMaster.bonEmpType').value=="")
  			{
  				alert("Please Enter Employee Type. ");
  				return false;
  			}
  			
  			if( document.getElementById('bonusParaMaster.bonFormula').value=="")
  			{
  				alert("Please Enter Bonus Formula. ");
  				return false;
  			}
  			
  			if(!(bonus==""))
					{
		
						var iChars = "`~!@#$%^&*()+=[]\\\';,/{}|\"<>?0123456789";
		  			for (var i = 0; i < bonus.length; i++)
		  			 {		  	
			  		if (iChars.indexOf(bonus.charAt(i)) != -1)
			  	 	{
				  	alert ("Please Enter Only Characters in Bonus Type Field !");
				  	return false;
  					}
  					}
				}
				
				
				var bonusFrm=document.getElementById('bonusParaMaster.bonPrdFrom').value;
				var bonusTo=document.getElementById('bonusParaMaster.bonPrdTo').value;
				
				bonusFrm = bonusFrm.split("-"); 
				frmDate = new Date(bonusFrm[2],bonusFrm[1]-1,bonusFrm[0]); 

				bonusTo = bonusTo.split("-"); 
				toDate = new Date(bonusTo[2],bonusTo[1]-1,bonusTo[0]); 
				
				if(frmDate > toDate)
				{
				alert("Please Enter Valid Date ");
				return false;
				}
				
				return true;
  			  			
  	  	}
  	
  	 
  	</script>
		
		
	<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
		
		