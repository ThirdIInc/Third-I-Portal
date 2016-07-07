<%@ taglib prefix="s" uri="/struts-tags" %>

<s:form action="DebitHeadMaster" id="paraFrm" validate="true" target="_top">
<table class="tableBody" width="100%">
	<tr>
		<td class="pageHeader" colspan="4" ><center>Debit</center></td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr><td class= "COMMONLABEL" >Debit Code</td>
	<td colspan="3">
		 		
  		<s:textfield label="%{getText('debCode')}"  theme="simple"  name="debMaster.debitCode"/> 
		<s:if test="debMaster.viewFlag">
		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:callsF9(500,325,'DebitHeadMaster_f9action.action');">
		</s:if></td>
		</tr>
		<tr><td class= "COMMONLABEL" >Debit Name</td>
		<td colspan="3">
  		<s:textfield label="%{getText('debName')}"   theme="simple"  name="debMaster.debitName"/>
  		</td>
  		</tr>
  		<tr>
  		<tr><td class= "COMMONLABEL" >Debit Abbr</td>
  		<td colspan="3">
  		<s:textfield label="%{getText('debAbbr')}"   theme="simple" maxlength="3"  name="debMaster.debitAbbr"/>
  		</td>
  		</tr>
  		<tr>
  		<td class= "COMMONLABEL" >Authority (CE Order/DCE List)</td>
  		<td colspan="3">
  		<s:hidden label="%{getText('auth')}"    theme="simple" name="debMaster.authority"/>
  		</td>
  		</tr>
  		<tr>
  		<td class= "COMMONLABEL" >Authority Date</td>  		
  		<td colspan="4">
  		<s:hidden label="%{getText('authDate')}" theme="simple" name="debMaster.authorDate"/>
  		<img src="../pages/images/cal.gif"  class="iconImage" height="16" 	width="16" />				
  		</td>
  		</tr>
  		<tr><td>&nbsp;</td></tr>
  		
  		<tr align="center">
  		<td colspan="6">
  		<s:if test="debMaster.insertFlag">
							<s:if test="debMaster.updateFlag">
  		<s:submit cssClass="pagebutton" action="DebitHeadMaster_save" theme="simple"  value="   Save   " />&nbsp;</s:if></s:if>
  		<s:submit cssClass="pagebutton" action="DebitHeadMaster_reset" theme="simple"   value="  Reset  " />&nbsp;
  		<s:if test="debMaster.deleteFlag">
			<s:submit cssClass="pagebutton" action="DebitHeadMaster_delete" theme="simple"   value="  Delete  " />&nbsp;</s:if>  		
  		<s:if test="debMaster.viewFlag"><input type="button" class="pagebutton"  onclick="callReport('DebitHeadMaster_report.action')" value="  Report " />	
		
		</s:if></td>
		</tr>

</table>
  	</s:form>
  	
  	<script>
  	function callReport(name) {
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action=name;	
			document.getElementById('paraFrm').submit();	
			document.getElementById('paraFrm').target="main";						
	}
	
  	</script>
  	

  
