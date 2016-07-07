<%@ taglib prefix="s" uri="/struts-tags" %>
<s:form  action="PayCoveringLetter" theme="simple" id="paraFrm" target="main" >
	<table width="100%">
	
	
<tr>
  			<td width="100%" colspan="4" class="pageHeader" align="center" >Payment Statement Covering Letter</td>
</tr>


<tr><td >&nbsp;</td></tr>

<tr>
<td align="center">Month<font color="red">*</font> :</td>
   
	<td ><s:select name="month" list="#{'':'-------Select----------','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"  />
	
</td>
</tr>


<tr>
<td align="center">Year<font color="red">*</font> :</td>
   
	<td><s:textfield name="year" maxlength="4" onkeypress="onlyNumber();"  />
	
</td>
</tr>

<tr>
<td align="center">Cheque No. :</td>
    
	<td><s:textfield name="chq" maxlength="10" onkeypress="onlyNumber();"/>
	
</td>
</tr>

<tr>
<td align="center">Cheque Date :</td>
    <td>
      <s:textfield name="chqDate"  size="20" maxlength="30" theme="simple" />
        <s:a href="javascript:NewCal('paraFrm_chqDate','DDMMYYYY');">
		<img src="../pages/images/cal.gif"  class="imageIcon" border="0"  align="absmiddle"/></s:a></td>
	
	
	

</tr>


<tr>
<td align="center">Division<font color="red">*</font> :</td>
    
	<td><s:hidden name="divId" /><s:textfield theme="simple" readonly="true" size="50" name="divName" />
	<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'PayCoveringLetter_f9div.action');">	
</td>
</tr>




<tr><td >&nbsp;</td></tr>
<tr><td >&nbsp;</td></tr>

<tr>
								<td colspan="2" align="center"><input type="button" class="pagebutton" value=' Submit ' onclick="callReport()" /></td>
</tr>
		
</table>
</s:form>
<script>
function callReport(){

				var chDate=document.getElementById('paraFrm_chqDate').value;

				if(document.getElementById('paraFrm_month').value=="") {
				alert("Please select Month");
				return false;
				
				}
				if(document.getElementById('paraFrm_year').value=="") {
				alert("Please enter the Year");
				return false;
				
				}
				
				
				
				 if(!(chDate=="")) {
				  var fld=['paraFrm_chqDate'];
		 			 var lbl=['Cheque Date'];
	 			  var chkDb=validateDate(fld,lbl);
	 				  if(!chkDb) {
		   	   			 return false;
		 			}
	 			 }
	 			 
	 			 if(document.getElementById('paraFrm_divName').value==""){
				
				alert("Please select the Division");
				return false;
				
				}
				
						
				document.getElementById('paraFrm').target="_blank";
				document.getElementById('paraFrm').action="PayCoveringLetter_report.action";	
				document.getElementById('paraFrm').submit();	
				document.getElementById('paraFrm').target="main";
				
				
}


function onlyNumber() {
	if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;	
}

</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
