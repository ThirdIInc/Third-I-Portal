<%@ taglib prefix="s" uri="/struts-tags" %>
 
 <s:form action="NdaAcqRoll" id="paraFrm" onsubmit="javascript:call()"  >

<table width="100%" border="0">
<tr>
 <td colspan="3"  class="pageHeader">Night Duty Allowance Acquaintance Roll</td>
</tr>
<tr>
    <td width="20%" align="right">&nbsp;</td>
	<td width="30%">From Month <font color="red">*</font>:</td> 
	<td>
		 <s:select  name="month1" id="month1"  list="#{'0':'Select','1':'JAN','2':'FEB','3':'MAR','4':'APR','5':'MAY','6':'JUN','7':'JUL','8':'AUG','9':'SEP','10':'OCT','11':'NOV','12':'DEC'}"  theme="simple"/>
	</td>
</tr>

<tr>
 <td width="20%" align="right">&nbsp;</td>
 <td width="30%">To Month <font color="red">*</font>:</td>
 <td> 
   <s:select  name="month2" id="month2" list="#{'0':'Select','1':'JAN','2':'FEB','3':'MAR','4':'APR','5':'MAY','6':'JUN','7':'JUL','8':'AUG','9':'SEP','10':'OCT','11':'NOV','12':'DEC'}"  theme="simple"/>
 </td>
</tr>


<tr>
    <td width="20%" align="right">&nbsp;</td>
	<td width="30%">Year <font color="red">*</font>:</td> 
	<td><s:textfield name="year" id="year" theme="simple" maxlength="4" size="20"/>  </td>
</tr>

<tr>
    <td width="20%">&nbsp;</td>
	<td width="30%" >Select Employee Type :</td>
		<s:hidden name="typeCode" />
	<td>
		<s:textfield name="empType" theme="simple"  maxlength="20" size="20" readonly="true" />
		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'NdaAcqRoll_f9type.action');">	
	</td>
</tr>



<tr>
    <td width="20%">&nbsp;</td>
	<td width="30%" >Select Pay Bill : </td>
	<td>
	<s:textfield name="payBilGrp"  theme="simple" maxlength="20" size="20" readonly="false"  readonly="true" ></s:textfield>
	  <img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'NdaAcqRoll_f9payBill.action');">	
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
  <td colspan="2">&nbsp;</td>
  <td>
	   <input type="reset"  value="Reset" >	  
	   <input type="button" class="pagebutton"  value="Report" onclick="setAction()">
  </td>
</tr>
 
</table>

</s:form>

<script >
function isNumber(no){
 	l=no.length;
 	for(i=0;i<l;i++){
 	 	c=no.charAt(i);
 	 	if(!(c>=0 || c<=9)|| c==' '){return false;}
 	}
 	return true;
}
function setAction(){
	if(validate()){				
				callReport();				
	}
}

function validate(){
	if(document.getElementById('paraFrm').month1.value=='0'){
		alert('Please select from month');
		document.getElementById('paraFrm').month1.focus();
		return false;
	}
	else if(document.getElementById('paraFrm').month2.value=='0'){
		alert('Please select to month');
		document.getElementById('paraFrm').month2.focus();
		return false;
	}else if(document.getElementById('paraFrm').year.value==""){
		alert('Please enter year');
		document.getElementById('paraFrm').year.focus();
		return false;
	}else if(!isNumber(document.getElementById("paraFrm").year.value)){
  			alert('Invalid Year entered');
  			document.getElementById("paraFrm").year.focus();
  			return false;
  	}else if(document.getElementById("paraFrm").year.value.length<4){
  			alert('Invalid Year entered');
  			document.getElementById("paraFrm").year.focus();
  			return false;
  	} 
	return true;
}

 function call(){    

  	if(document.getElementById("month1").value=='0'){
		  	alert('Please select from month');
		  	document.getElementById("month1").focus();
		  	return false;
  	}
  	if(document.getElementById("month2").value=='0'){
		  	alert('Please select to month');
		  	return false;
  	}
	if(document.getElementById("year").value==''){
		  	alert('Please select to month');
		  	return false;
  	}
}  	
function callReport(){
		//if(document.getElementById("paraFrm").rptTyp.value=="Pdf"){
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action="NdaAcqRoll_report.action";	
			document.getElementById('paraFrm').submit();	
			document.getElementById('paraFrm').target="main";					
		/*}else{	
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action="NdaAcqRoll_report.action";
			document.getElementById('paraFrm').target="main";
			document.getElementById('paraFrm').submit();		
		}	*/			
}
</script>