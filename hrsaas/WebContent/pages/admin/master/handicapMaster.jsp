<%@ taglib prefix="s" uri="/struts-tags" %>  


  	
  	<s:form action="HandicapMaster"  validate="true" id="paraFrm" validate="true" theme="simple">
	<table class="bodyTable" width="100%" colspan="3">
	<tr>
  		<td width="100%" colspan="3" class="pageHeader" align="center" >Handicap Master</td>
  	</tr>
  	<tr>
	    <td width="100%" colspan="3">&nbsp;</td>
	</tr>
	
	<tr>
	  		<td width="20%">CATAGORY ID :
	  		<td width="70%" ><s:hidden label="%{getText('catagoryID')}" name="handicapMaster.catagoryID" />  	
	  		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
	  		width="18" onclick="javascript:callsF9(500,325,'HandicapMaster_f9action.action');"></td>
	  		
	  		<td width="10%" ></td>
	  								
	</tr>
	  	
	<tr>	
	  		<td width="20%" >CATAGORY NAME :</td>
	  		<td width="70%"><s:textfield size="30" label="%{getText('catagoryName')}" name="handicapMaster.catagoryName"/></td>
	  		<td width="10%" ></td>
	  		
	</tr>
	
		<td>&nbsp;</td>
	 <tr align="center">			
	  		<td width="100%"  colspan="4">
	  		<s:submit cssClass="pagebutton" action="HandicapMaster_save"  value="Save" />
	  		<s:submit cssClass="pagebutton" action="HandicapMaster_reset"  value="Reset" />
	  		<s:submit cssClass="pagebutton" action="HandicapMaster_delete"  value="Delete" />
	  		<input type="button" class="pagebutton"  onclick="callReport('HandicapMaster_report.action')" value="Report"/>&nbsp;</td>
	 </tr>	
  	</table>
  
		
	
	<script>
  			function callReport(name)
  			{
				document.getElementById('paraFrm').target="_blank";
				document.getElementById('paraFrm').action=name;	
				document.getElementById('paraFrm').submit();							
			}
	
  	</script>
 </s:form>