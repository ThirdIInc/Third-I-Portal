
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="DaAcquintanceRoll" id="paraFrm" theme="simple">
	<table  width="100%">
		<tr>
			<td class="pageHeader" colspan="3">
			<center>DA Arrear Report</center>
			</td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>

		<tr>
			<td width="32%" align="right">DA Date :<font color="red">*</font></td>
			<td><s:hidden
				name="daAcquintanceRoll.daRate" /> <s:textfield
				name="daAcquintanceRoll.daDate" theme="simple" id="fld" size="30"
				readonly="true">
			</s:textfield> <img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,325,'DaAcquintanceRoll_f9action.action');">
			</td>
		</tr>
		<tr>
			<td width="32%" align="right"><font color="red"></font>Select
			Employee Type :</td>
			<s:hidden name="daAcquintanceRoll.typeCode" />
			<td><s:textfield name="daAcquintanceRoll.typeName" id="et"
				readonly="true" theme="simple" maxlength="40" size="30">
			</s:textfield> <img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,325,'DaAcquintanceRoll_f9type.action');">
			</td>
		</tr>
		<tr>
			<td width="32%" align="right"><font color="red"></font>Select
			Pay Bill No :</td>

			<td><s:hidden name="daAcquintanceRoll.payBillNo" /> <s:textfield
				name="daAcquintanceRoll.payBillName" id="pb" theme="simple"
				readonly="true" maxlength="40" size="30">
			</s:textfield> <img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,325,'DaAcquintanceRoll_f9payBill.action');">
			</td>
		</tr>

		<tr>
			<td width="30%">&nbsp;</td>
			<td><s:submit cssClass="pagebutton"  onclick="return check()"
				theme="simple" action="DaAcquintanceRoll_report" value="Report" />
				<s:submit cssClass="pagebutton"  onclick="return check()"
				theme="simple" action="DaAcquintanceRoll_bankState" value="Bank Statement" />
				</td>

		</tr>
	</table>
</s:form>

<script>

   
    	 function check() {
    	  var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
  		 
    	  	var fld= document.getElementById("paraFrm").fld.value;
    	  
    	 	var type= document.getElementById("paraFrm").et.value;
			var center= document.getElementById("paraFrm").pb.value;
	    if ((fld.match(RegExPattern)) && (fld!='')) {
    
   			 } else {
       				 alert('Please enter valid date ');
       				  document.getElementById("paraFrm").fld.value=""; 
       			    	 return false;
       				 
   						 } 
   		
   		 if ((fld1.match(RegExPattern)) && (fld1!='')) {
    
   			 } else {
       				 alert('Please enter valid to date ');
       				   document.getElementById("paraFrm").fld1.value="";
       				   return false;
       					 
   						 } 
   		
   	alert(type);
	 
	if(type=="")
	{
	alert("Please Select Employee Type");
	return false;
	}
	if(center=="")
	{
	alert("Please Select Pay Bill No");
	return false;
	}
}
 </script>
