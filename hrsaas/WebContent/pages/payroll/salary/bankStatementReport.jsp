<%@ taglib prefix="s" uri="/struts-tags" %>
 
 <s:form action="BankStatement" id="paraFrm" validate="true" theme="simple" >

<table width="100%" border="0">
<tr>
 <td colspan="3"  class="pageHeader">Bank Statement </td>
</tr>
<tr>
    <td width="15%" align="right">&nbsp;</td>
	<td width="10%" >Month <font color="red">*</font>:</td> 
	<td width="25%">
		 <s:select  name="month" id="month"  list="#{'0':'Select','1':'JAN','2':'FEB','3':'MAR','4':'APR','5':'MAY','6':'JUN','7':'JUL','8':'AUG','9':'SEP','10':'OCT','11':'NOV','12':'DEC'}"  theme="simple"/>
	</td>
</tr>

<tr>
 <td width="15%" align="right">&nbsp;</td>
 <td width="10%">Year <font color="red">*</font>:</td>
 <td width="25%"> 
   <s:textfield name="year" id="year" theme="simple"   maxlength="4" size="5"/>
 </td>
</tr>

<tr>
    <td width="15%">&nbsp;</td>
	<td width="10%" >Select Bank :</td>
		<s:hidden name="bankCode"/>
	<td width="25%">
		<s:textfield name="bank" theme="simple"  maxlength="50" size="30" readonly="true"/>
		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'BankStatement_f9bank.action');">	
	</td>
</tr>

<tr>
    <td width="15%">&nbsp;</td>
	<td width="10%" >Select Pay Bill : </td>
	<td width="25%"><s:textfield name="payBill" theme="simple" maxlength="50" size="30"  readonly="true" />
		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'BankStatement_f9payBill.action');">	
	</td>
</tr>

<tr>
  <td width="15%">&nbsp;</td>
  <td width="10%">&nbsp;</td>
  <td width="25%">
	   <input type="button" class="pagebutton" value="  Report  " onclick="return validate();">
	   <input type="button" class="pagebutton" value="  Reset  " onclick="reset()">
  </td>
</tr>
 
</table>

</s:form>

<script >
function reset(){
	document.getElementById("month").value="0";
	document.getElementById("year").value="";
	document.getElementById("bank").value="";
	document.getElementById("bankCode").value="";
	document.getElementById("payBill").value="";
	
}

function isNumber(no){
 	l=no.length;
 	for(i=0;i<l;i++){
 	 	c=no.charAt(i);
 	 	if(!(c>=0 || c<=9)|| c==' ' || c=='.'){return false;}
 	}
 	return true;
}
function validate(){
 if(document.getElementById("month").value=="0"){
  alert('Please select month.');
  document.getElementById("month").focus();
  return false;  
 }if(document.getElementById("year").value==""){
  alert('Please enter year.');
  document.getElementById("year").focus();
  return false;  
 }if(!isNumber(document.getElementById("year").value)|| document.getElementById("year").value.length<4){
  alert('Invalid year entered.');
  document.getElementById("year").focus();
  return false;  
 }
 callReport();
}
 
function callReport(){		
		//	document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action="BankStatement_report.action";
			document.getElementById('paraFrm').submit();
		//	document.getElementById('paraFrm').target="main";
		//}else{	
		//	document.getElementById('paraFrm').submit();		
		//}						
}
</script>