<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="SuggestionAppr" validate="true" id="paraFrm"
	theme="simple">
	<table class="bodyTable" width="100%">
		<tr>
			<td width="100%" colspan="4" class="pageHeader" align="center">Suggestion Approval Form</td>
		</tr>
		
		<tr><td>&nbsp;</td></tr>
		
		<tr align="left">
		
			<td>Suggestion Status :
			<s:select name="suggAppr.suggStatus" headerKey="" headerValue="Select"list="#{'P':'Pending','A':'Approved','R':'Rejected'}" onchange="return check();"/></td>
			</tr>
		     <tr><td>&nbsp;</td></tr>
		<tr>
			<td width="100%" colspan="6" class="sectionHead">Suggestion Details<s:hidden name="suggDetail"/></td>
		</tr>
		</table> 
	      <table class="bodyTable" width="100%">
  		
  		<%! int i = 0 ; %>
		<% int k = 0 ; %>
  		<tr>
  			<td class="headercell" width="10%">Sr No.</td>  			
  			<td class="headercell" width="75%">Suggestion Subject</td>
			<td class="headercell" width="15%">Status</td>
		 </tr>
		 <s:iterator value="list">
	<tr>
	<td width="10%" class="bodycell"><s:property value = "SrNo"/><s:hidden name="suggestionCode"></s:hidden></td>
	<td width="60%"  class="bodycell"><s:property value="suggestionSub"/></td>
	            
	
	<td width="30%" class="bodycell"> <s:select name="checkStatus" id="<%="check"+k %>"
					cssStyle="width:110" theme="simple" list="#{'P':'Pending','A':'Approved','R':'Rejected'}"  />
				
		</td>				
	</tr>
	<%k++; %>
	</s:iterator>
	<%i=k; %>
	<tr><td>&nbsp;</td></tr>
	<tr align="center">
			
			<td width="50%" colspan="3">
				<div id="saveButton" >  
					<s:submit action="SuggestionAppr_save"  cssClass="pagebutton" value="  Save  " id="save" onclick="return saveCheck();"></s:submit>
				</div>
			</td>
	</tr>
	
	       </table>
		</s:form>
<script >
		callOnload();
	function check()
	{ 
		
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action="SuggestionAppr_showData.action";	
		document.getElementById('paraFrm').submit();
		callOnload();
	}
	function callOnload(){
	var check =<%=i%>;
		if(document.getElementById("paraFrm_suggAppr_suggStatus").value=='A' || document.getElementById("paraFrm_suggAppr_suggStatus").value=='R')
		{
			for(var k = 1;k < check ;k++ ){
			document.getElementById("check"+k).disabled=true;	
			}
			document.getElementById("save").disabled=true;
			document.getElementById('saveButton').style.display = 'none';	
		}
		
}
	function saveCheck()
	{
	var count =<%=i%>;
		if(count > 0)
		{
			for(var k = 0;k < count ;k++ )
			{
				var chk=document.getElementById("check"+k).value;	
				if(chk =='A' || chk =='R')
					return true;
			}
		}
		else
		{
			alert("No Records Found to Save");
			return false;
		}
		alert('Status has not been changed!');
		return false;
	}
</script>
