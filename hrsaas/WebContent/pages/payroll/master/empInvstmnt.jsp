<%@ taglib prefix="s" uri="/struts-tags" %>
 
 <s:form action="EmpInvstmnt" id="paraFrm" validate="true" theme="simple">

<table width="100%" border="0">
<tr>
 <td colspan="3"  class="pageHeader">Employee Investment </td>
</tr>
<tr>
    <td width="5%" align="right"><font color="red">*</font></td>
	<td width="10%" >From Year :</td> 
	<td width="25%">
		 <s:textfield name="empInv.frmYear" id="frmYear" theme="simple"   maxlength="4" size="5"/>
	</td>
</tr>

<tr>
 <td width="5%" align="right"><font color="red">*</font></td>
 <td width="10%">To Year :</td>
 <td width="25%"> 
   <s:textfield name="empInv.toYear" id="toYear" theme="simple"   maxlength="4" size="5"/>
 </td>
</tr>

<tr>
    <td width="5%">&nbsp;</td>
	<td width="10%" >Sort by Employee Type :</td>
		<s:hidden name="empInv.typeCode" value="1" />
	<td width="25%">
		<s:textfield name="empInv.empType" theme="simple"  maxlength="50" size="30" readonly="true"/>
		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'EmpInvstmnt_f9type.action');">	
	</td>
</tr>

<tr>
    <td width="5%">&nbsp;</td>
	<td width="10%" > Sort by center No : </td>
	<td width="25%"><s:textfield name="empInv.centerNo" theme="simple" maxlength="50" size="30" value="9" readonly="true" />
		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'EmpInvstmnt_f9center.action');">	
	</td>
</tr>

<tr>
    <td width="5%">&nbsp;</td>
	<td width="10%" > Paybill Group : </td>
	<td width="25%" >
    	<s:textfield name="empInv.payBill" theme="simple" maxlength="50" size="30" readonly="true" />
		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'EmpInvstmnt_f9payBill.action');">	
	</td>
</tr>

<tr>
    <td width="5%">&nbsp;</td>
	<td width="10%" > Report Type : </td>
	<td width="25%">
		<s:select  name="empInv.rptTyp"  list="#{'Pdf':'PDF','Xls':'XLS'}"  theme="simple"/>		
	</td>
</tr>

<tr>
  <td width="5%">&nbsp;</td>
  <td width="10%">&nbsp;</td>
  <td width="25%">
	   <s:submit cssClass="pagebutton"  theme="simple"  value='Report' action="EmpInvstmnt_report" onclick="return validate()"  />
  </td>
</tr>
 
</table>

</s:form>

<script >
function isNumber(no){
 	l=no.length;
 	for(i=0;i<l;i++){
 	 	c=no.charAt(i);
 	 	if(!(c>=0 || c<=9)|| c==' ' || c=='.'){return false;}
 	}
 	return true;
}
function validate(){
 if(document.getElementById("frmYear").value==""){
  alert('Please enter from year.');
  document.getElementById("frmYear").focus();
  return false;  
 }if(document.getElementById("toYear").value==""){
  alert('Please enter to year.');
  document.getElementById("toYear").focus();
  return false;  
 }
 if(!isNumber(document.getElementById("frmYear").value) || document.getElementById("frmYear").value.length<4){
  alert('Please enter valid from year.');
  document.getElementById("frmYear").focus();
  return false;  
 }
  if(!isNumber(document.getElementById("toYear").value) || document.getElementById("toYear").value.length<4){
   alert('Please enter valid to year.');
   document.getElementById("toYear").focus();
   return false;  
 }
 return true;
}
 function call(){    
  	//alert(document.getElementById('otAcqRoll.centerNo').value);
  	//document.getElementById("paraFrm").action="LoanInstallment_edit.action";
  	//document.getElementById('othrs.centerNo').value=document.getElementById('othrs.centerNo').value;
  	}
</script>