<%@ taglib prefix="s" uri="/struts-tags" %>
 
 <s:form action="NdaHours" id="paraFrm" >

<table width="100%" border="0">
<tr>
 <td colspan="3"  class="pageHeader">Night Duty Allowance Hours </td>
</tr>
<tr>
    <td width="20%" align="right">&nbsp;</td>
	<td width="30%">From Date <font color="red">*</font>:</td> 
	<td>
		 <s:textfield name="frmDate" theme="simple"   maxlength="10" size="20"/>
		 <s:a href="javascript:NewCal('frmDate','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a>
	</td>
</tr>

<tr>
 <td width="20%" align="right">&nbsp;</td>
 <td width="30%">To Date <font color="red">*</font>:</td>
 <td> 
   <s:textfield name="toDate" theme="simple"   maxlength="10" size="20"/>
   <s:a href="javascript:NewCal('toDate','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a>
 </td>
</tr>

<tr>
    <td width="20%" align="right">&nbsp;</td>
	<td width="30" >Select Employee Type :</td>
		<s:hidden name="typeCode"/>
	<td>
		<s:textfield name="empType" theme="simple"  maxlength="20" size="20"  readonly="true"  />
		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'NdaHours_f9type.action');">	
	</td>
</tr>

<tr>
    <td width="20%">&nbsp;</td>
	<td width="30%" >Select Pay Bill : </td>
	<td>
	<s:textfield name="payBill"  theme="simple" maxlength="20" size="20" readonly="false" readonly="true"   ></s:textfield>
	  <img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'NdaHours_f9payBill.action');">	
  </td>
</tr>

<tr>
    <td width="20%">&nbsp;</td>
	<td width="30%" > Report Type : </td>
	<td>
		<s:select  name="rptTyp"  list="#{'Pdf':'PDF','Xls':'XLS'}"  theme="simple"/>		
	</td>
</tr>

<tr>
  <td colspan="2" width="50%">&nbsp;</td>
  <td>
	   <input type="reset" value="Reset" >
	   <input type="button" class="pagebutton"  value="Report" onclick="setAction()" >
  </td>
</tr>
 
</table>

</s:form>

<script >
function isDate(dt){ 
 l=dt.length;
 for(i=0;i<l;i++){
	  c=dt.charAt(i);
	  if((i==2 || i==5) && !(c=="-")){
		return false;
	  }
  }
 return true; 
}
function validate(){ 
  		if(document.getElementById("paraFrm").frmDate.value==""){
  			alert('Please enter from date.');
  			document.getElementById("paraFrm").frmDate.select();
  			return false;
  		}
  		else if(document.getElementById("paraFrm").toDate.value==""){
  			alert('Please enter to date.');
  			document.getElementById("paraFrm").toDate.focus();
  			return false;
  		}else if(!isDate(document.getElementById('paraFrm').frmDate.value)){
			alert('Invalid date entered');
			document.getElementById('paraFrm').frmDate.focus();
			return false;
		}else if(!isDate(document.getElementById('paraFrm').toDate.value)){
			alert('Invalid date entered');
			document.getElementById('paraFrm').toDate.focus();
			return false;
		} 
		return true;
}

function setAction(){
		if(validate()){			
			callReport();
		}
}
function callReport(){
		//if(document.getElementById("paraFrm").rptTyp.value=="Pdf"){
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action="NdaHours_report.action";
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main";
		/*}else{
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action="NdaHours_report.action";
			document.getElementById('paraFrm').target="main";
			document.getElementById('paraFrm').submit();
		}*/				
}

</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js" >
</script>