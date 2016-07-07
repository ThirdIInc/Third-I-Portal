<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="CreditMaster" id="paraFrm" validate="true"
	theme="simple">
	<table width="100%" align="center">
		<tr>
			<td colspan="8" class="pageHeader">Credit</td>

		</tr>
<tr align="center">
			<td colspan="4" width="100%" align="left" valign="middle" class="buttontr">
			<s:if test ="creditMaster.insertFlag">
			 <s:if test="creditMaster.updateFlag">
				<s:submit cssClass="pagebutton" action="CreditMaster_save" theme="simple"
					onclick=" return callAdd()" value="   Save   " /> </s:if> </s:if> &nbsp;
			 <s:submit cssClass="pagebutton" action="CreditMaster_reset" theme="simple"
				value="  Reset  " /> <s:if test="creditMaster.deleteFlag">&nbsp;
				<s:submit cssClass="pagebutton" action="CreditMaster_delete" theme="simple"
					onclick="return del()" value="  Delete  " />&nbsp;
			</s:if>
			 <s:if test ="creditMaster.viewFlag">
			
			<input type="button" class="pagebutton" onclick="call('CreditMaster_report.action')" 
				theme="simple" value="  Report " />    </s:if></td>

		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td width="20%" colspan="1">Select Credit :</td>
			<td colspan="2" width="55%"><s:hidden
				name="creditMaster.CreditCode" /> 
				 <s:if test ="creditMaster.viewFlag">
				
				<img
				src="../pages/images/search.gif" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,400,'CreditMaster_f9action.action');">  </s:if>
			</td>

		</tr>
		<tr>
			<td width="20%" colspan="1">Credit Name<font color="red">*</font>:</td>
			<td colspan="2" width="55%"><s:textfield
				label="%{getText('creditName')}" size="50%" maxlength="100" theme="simple"
				name="creditMaster.CreditName" onkeypress="return allCharacters();" /></td>


		</tr>
		<tr>
			<td width="20%" colspan="1">Credit Abbreviation<font color="red">*</font>:</td>
			<td colspan="2" width="55%"><s:textfield maxlength="10"
				label="%{getText('creditAbbr')}" theme="simple"
				name="creditMaster.CreditAbbr" onkeypress="return allCharacters();"/></td>

		</tr>
		
		<tr>
			<td width="20%" colspan="1">Credit Type:</td>
			<td colspan="2" width="55%"><s:select name="CreditType"
				theme="simple" headerKey="0" headerValue="--Select--"
				list="#{'1':'Fixed','2':'Formula','3':'Slab'}" /></td>


		</tr>
		
		
		<tr>
			<td width="20%" colspan="1">Credit Pay Flag:</td>
			<td colspan="2" width="55%"><s:select name="Creditpayflag"
				theme="simple" headerKey=" " headerValue="--Select--"
				list="#{'Y':'YES','N':'NO'}" /></td>


		</tr>
		
		<tr>
			<td width="20%" colspan="1">Applicable Arrears:</td>
			<td colspan="2" width="55%"><s:select name="appArrears"
				theme="simple" headerKey=" " headerValue="--Select--"
				list="#{'Y':'YES','N':'NO'}" /></td>


		</tr>
		
		<tr>
			<td width="20%" colspan="1">Credit Taxable:</td>
			<td colspan="2" width="55%"><s:select name="taxable"
				theme="simple" headerKey=" " headerValue="--Select--"
				list="#{'Y':'YES','N':'NO'}" /></td>


		</tr>
		
		
		
		
			<tr>
			<td width="20%" colspan="1">Credit Formula:</td>
			<td colspan="2" width="55%"><s:textarea   name="creditFor" rows="3" cols="40"/></td>


		</tr>
		
		
		<!--<tr>
			<td width="25%" colspan="1">&nbsp;</td>
			<td width="20%" colspan="1">Credit Type:</td>
			<td colspan="2" width="55%"><s:select name="CreditType"
				theme="simple" headerKey="0" headerValue="--Select--"
				list="#{'1':'Fixed','2':'Formula','3':'Slab'}" /></td>


		</tr>
		
		<tr>
			<td width="25%" colspan="1">&nbsp;</td>
			<td width="20%" colspan="1">Credit Type:</td>
			<td colspan="2" width="55%"><s:select name="CreditType"
				theme="simple" headerKey="0" headerValue="--Select--"
				list="#{'1':'Fixed','2':'Formula','3':'Slab'}" /></td>


		</tr>
		<tr>
			<td width="25%" colspan="1">&nbsp;</td>
			<td width="20%" colspan="1">Credit Maxcapacity:</td>
			<td colspan="2" width="55%"><s:textfield maxlength="25"
				label="%{getText('creditmaxcap')}"
				onkeypress="return numbersonly(this);" theme="simple"
				name="creditMaster.Creditmaxcap" /></td>

		</tr>
		<tr>
			<td width="25%" colspan="1">&nbsp;</td>
			<td width="20%" colspan="1">Credit MinFloor:</td>
			<td colspan="2" width="55%"><s:textfield maxlength="25"
				label="%{getText('creditminfloor')}"
				onkeypress="return numbersonly(this);" theme="simple"
				name="creditMaster.Creditminimumfloor" /></td>


		</tr>

		<tr>
			<td width="25%" colspan="1">&nbsp;</td>
			<td width="20%" colspan="1">Credit Pay Flag:</td>
			<td colspan="2" width="55%"><s:select name="Creditpayflag"
				theme="simple" headerKey="S" headerValue="--Select--"
				list="#{'Y':'YES','N':'NO'}" /></td>


		</tr>-->


				
			
				<!--headerKey="0" headerValue="--Select--" theme="simple"
				list="#{'1':'Nearest Amount', '2':'Higher Amount if the fraction is more than 50 basis point',
        '3':'Lower Amount if the fraction is less than or equal to 50 basis point','4':'Nearest Tens',
        '5':'Nearest Hundreds','6':' Nearest Thousands','7':'Nearest Ten Thousands','8':'No Rounding off'}" />
        -->
       
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>

		

	</table>

</s:form>
<script>

function del(){
	    if(document.getElementById("paraFrm_creditMaster_CreditName").value==""){
    		 alert('Please Select Credit ');
		     return false;
    	}else{
    		var conf=confirm("Are you sure to delete this record? ");
  			if(conf) {
  			 document.getElementById('paraFrm').submit();
  			 return true;
  			}
   		} 
   return false;
}
function callAdd(){
	var crname = document.getElementById('paraFrm_creditMaster_CreditName').value;
	var crabbr = document.getElementById('paraFrm_creditMaster_CreditAbbr').value;
	
	if((crname==""))
	{
	alert("Please Enter Credit Name")
	
					document.getElementById('paraFrm_creditMaster_CreditName').focus();
	return false;
	}
	 if(!(crname==""))
					{  
					 
					var count =0;
					var iChars =" ";
		  			for (var i = 0; i < crname.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(crname.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
  					   }
  					}
  					if(count==crname.length){
  					alert ("Blank Spaces Not Allowed");
  					document.getElementById('paraFrm_creditMaster_CreditName').value="";
					document.getElementById('paraFrm_creditMaster_CreditName').focus();
  					return false;	
  					}
				}
				
				
	
				
			
	
	
	if((crabbr==""))
	{
	alert("Please Enter Credit Abbreviation")
						document.getElementById('paraFrm_creditMaster_CreditAbbr').focus();
	return false;
	}
	
			
			
			if(!(crabbr==""))
					{ 
					 
					var count =0;
					var iChars =" ";
		  			for (var i = 0; i < crabbr.length; i++)
		  			 {		
		  			 
			  		if (iChars.indexOf(crabbr.charAt(i))!= -1)
			  	 	{
			  	 	count=count+1; 
				  	
  					   }
  					}
  					if(count==crabbr.length){
  					alert ("Blank Spaces Not Allowed");
  					document.getElementById('paraFrm_creditMaster_CreditAbbr').value="";
					document.getElementById('paraFrm_creditMaster_CreditAbbr').focus();
  					return false;	
  					}
				}
			
			
			
			
			
			

}
  	function call(name) {
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
  	</script>
