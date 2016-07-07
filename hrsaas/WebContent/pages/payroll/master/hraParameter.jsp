<%@ taglib prefix="s" uri="/struts-tags" %>

<s:form action="HraParameterMaster" id="paraFrm" validate="true" target="_top">
<table class="tableBody" width="100%">
	<tr>
		<td class="pageHeader" colspan="4" ><center>HRA Parameter </center></td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr><td class= "COMMONLABEL" >Class code</td>
	<td colspan="3">
		 		
  		<s:textfield label="%{getText('hracode')}"  theme="simple"  name="hraparameterClass"/> 
		
		</td>
		</tr>
		<tr><td class= "COMMONLABEL" >Class Name</td>
		<td colspan="3">
  		<s:textfield label="%{getText('hraname')}"   theme="simple"  name="hraparameterName"/>
  		</td>
  		</tr>
  		<tr><td class= "COMMONLABEL" >HRA Percentage</td>
  		<td colspan="3">
  		<s:textfield label="%{getText('hrapercentage')}"  theme="simple"  name="hraparameterPercent"/>	
  		</td>
  		</tr>
  		<tr><td class= "COMMONLABEL" >HRA Master Code</td>
  		<td colspan="3">
  		<s:textfield label="%{getText('hracode')}"    theme="simple"  name="hraparameterCode"/>
  		</td>
  		</tr>
  		
  		<tr align="center">
  		<td colspan="6">
  		<s:submit cssClass="pagebutton" action="HraParameterMaster_save" theme="simple"  value="   Save   " />&nbsp;
  		<s:submit cssClass="pagebutton" action="HraParameterMaster_reset" theme="simple"   value="  Reset  " />&nbsp;
  		<s:submit cssClass="pagebutton" action="HraParameterMaster_delete" theme="simple"   value="  Delete  " />&nbsp;  		
  		
		</td>
		</tr>

</table>
  	</s:form>
  	<!--<script>
  	function call(name){
  	
  	document.getElementById('paraFrm').target="_blank";
  	document.getElementById('paraFrm').action=name;
  	document.getElementById('paraFrm').submit();
  	}
  	</script>

  
-->