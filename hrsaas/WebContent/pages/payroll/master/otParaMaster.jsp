<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="OtParaMaster" id="paraFrm" validate="true" target="main" theme="simple">
	<table  width="100%" class="formbg"> 
  <tr>
    <td colspan="4">
	        				<table width="100%"  cellpadding="0" cellspacing="0" class="formbg">
							     <tr>
								     <td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
								     <td width="93%" class="txt"><strong class="text_head"> OT Parameter </strong></td>
								     <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/common/css/default/images/help.gif" width="16" height="16" /></div></td>
							     </tr>
							</table>
     </tr>
      <tr> 
       <td colspan="4" width="100%">  	
     	<table class="formbg" width="100%" > 
 
		<tr align="left">
			<td  align="left">Select OT Id :</td>
			<s:hidden name="otParaMaster.otId" />
			<td >
				<img src="../pages/images/search2.gif" class="iconImage" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,400,'OtParaMaster_f9action.action');">
			</td>
			
				<td  >Employee Type <font color="red">*</font> : </td>
			<s:hidden name="otParaMaster.typeCode" />
			<td><s:textfield name="otParaMaster.typeName"  id="et"   readonly="true" theme="simple"   maxlength="20" size="22"> </s:textfield>
				<img src="../pages/images/search2.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'OtParaMaster_f9type.action');">	
				</td>
		</tr>
	
		<tr >
		
		 
			<td   >Formula of Single OT <font color="red">*</font> :</td>
			<td   ><s:textfield label="%{getText('normalCalSingle')}"
				theme="simple" name="otParaMaster.normalCalSingle"  size="22"  /></td>
		
		<td  >Formula of Double OT <font color="red">*</font> :</td>
			<td  ><s:textfield label="%{getText('normalCalDouble')}"
				theme="simple" name="otParaMaster.normalCalDouble"  size="22" /></td>
		 
		
		</tr>
		
		 
		
		 
		<tr>
			<td  >Holiday Calculation Single :</td>
			<td ><s:textfield label="%{getText('holiCalSingle')}"
				theme="simple" name="otParaMaster.holiCalSingle" maxlength="20" size="22"/></td>
		
		
			<td  >Holiday Calculation Double :</td>
			<td  ><s:textfield label="%{getText('holiCalDouble')}"
				theme="simple" name="otParaMaster.holiCalDouble" maxlength="20" size="22" /></td>
		</tr>
		<tr>
			<td  >&nbsp;</td>
		</tr>
		 
      <tr>
       
          <td  align="center" colspan="4">
            <s:if test="flag">		
  		<s:submit cssClass="pagebutton" action="OtParaMaster_update"
				theme="simple" value="   Save   " onclick="return callsave()"/>
				&nbsp; 
				<s:submit cssClass="pagebutton"
				action="OtParaMaster_reset" theme="simple" value="  Reset  " /> &nbsp;
			   <s:submit cssClass="pagebutton" action="OtParaMaster_delete" theme="simple"
				value="  Delete  " onclick="return callDel();"/>&nbsp;  <input type="button" class="pagebutton"
				onclick="callReport('OtParaMaster_report.action')" value="  Report " />
		</s:if>	
		<s:else>
		<s:submit cssClass="pagebutton" action="OtParaMaster_save"
				theme="simple" value="   Save   " onclick="return callsave()"/>&nbsp; 
				
				<s:submit cssClass="pagebutton"
				action="OtParaMaster_reset" theme="simple" value="  Reset  " /> &nbsp;
			   <s:submit cssClass="pagebutton" action="OtParaMaster_delete" theme="simple"
				value="  Delete  " onclick="return callDel();"/>&nbsp;  <input type="button" class="pagebutton"
				onclick="callReport('OtParaMaster_report.action')" value="  Report " />
		</s:else>	 
  		
  		 </td>
      </tr>
     </table>
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
  	
  	function callsave() 
	{
  			if(document.getElementById('otParaMaster.typeName').value=="") 
  			{
  				alert("Please Select Employee Type.");
  				return false;
  			} 
  			else if( document.getElementById('otParaMaster.normalCalSingle').value=="")
  			{
  				alert("Please Enter Normal Calculation Single. ");
  				return false;
  			}
  			else if(document.getElementById('otParaMaster.normalCalDouble').value=="")
  			{
  				alert("Please Enter Normal Calculation Double. ");
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
	
	function callDel() {
  			if(document.getElementById('otParaMaster.typeCode').value=="") {
  			alert("Please Select OT.");
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
  	</script>