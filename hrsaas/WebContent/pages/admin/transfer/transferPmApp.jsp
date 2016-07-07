
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.HashMap"%>
<s:form action="TransferPmApproval" method="post" id="paraFrm" validate="true" theme="simple">
		
  		<table class="bodyTable" width="100%">
  	<tr>
  			<td width="100%" colspan="6" class="pageHeader" align="center" >Transfer PM Approval</td>
  	</tr>
  	<% int i=0;HashMap afdata=(HashMap)request.getAttribute("data"); %>
  		<tr>
  		
  		<td width="100%" colspan="6" align="left" valign="middle" class="buttontr"><s:if test="tpma.viewFlag"><input type="button" class="pagebutton"    value="Generate DCE List"  onclick="callReport();"/></s:if>&nbsp;&nbsp;
		
		<s:if test="tpma.insertFlag">	
		<s:if test="tpma.updateFlag">
		<s:submit cssClass="pagebutton" action="TransferPmApproval_saveForApprove" value=" Save " onclick="return callSave()" /></s:if></s:if></td>	
  	</tr>
  	<tr>
    	<td width="100%" colspan="6">&nbsp;</td>
  	</tr>
  	 	  	
 
  	<tr>
    	<td width="100%" colspan="6">&nbsp;</td>
  	</tr>
  
  	 	
  	
  	</table>
  	<table class="bodyTable" width="100%">
  	<tr>
    	<td width="100%" colspan="6">&nbsp;</td>
  	</tr>
  	<tr>  			
  		<td class="headercell" width="15%">Token No.</td>
		<td class="headercell" width="40%">Employee Name</td>
		<td class="headercell" width="15%">Application Date</td>
		<td class="headercell" width="15%">Check</td>
		<td class="headercell" width="15%">Details</td>
	</tr>	
	
	<s:iterator value="transferList" status="stat"> 	
	<%String audFlag=(String)afdata.get(""+i); %>
		     	<%! int p=0 ;%>   
	 <tr>	 
	      <td class="bodyCell" width="15%"><s:property value="empToken"/></td>
	      <td class="bodyCell" width="40%"><s:property value="empName"/></td>
	      <td class="bodyCell" width="15%"><s:property value="appDate"/></td>
	      <td class="bodyCell" width="15%"><input type="checkbox" class="checkbox"  name="leaveChk" value="<%=audFlag.equals("Y")?"checked":""%>" onclick="callChk(<%=i%>)"   />
	   	  <input type="hidden" name="pmChkFlag" id="<%=i%>" value="<%=audFlag.equals("A")?"N":audFlag%>" />
	      
	           
	       <td class="bodyCell" width="35%">
		<a href="<s:url action="TransferApplication_callByTransferPmApprove"><s:param name="trfId" value="trfId"/><s:param name="empId" value="empId"/></s:url>">View Details</a></td>
	    <s:hidden name="trfId"/>
	  </tr>
	          
	     <%p++;i++; %>    
	</s:iterator>
	
		</table>
	<table width="90%">
	<tr>
    	<td width="100%" colspan="6">&nbsp;</td>
  	</tr>
  	<tr>
    	<td width="15%">DCE LIST No<font color="red">*</font> :</td>
    	<td width="25%"><s:textfield name="dceNo" /></td>
    	<td width="15%">DCE List Date<font color="red">*</font> :</td>
    	<td width="25%"><s:textfield name="dceDate"/><s:a
				href="javascript:NewCal('dceDate','DDMMYYYY');">
				<img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"
					width="16"></s:a></td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="6">&nbsp;</td>
  	</tr>
  	
  

  <s:hidden name="checkboxSel"/>
  	
 	</table>
  	</s:form>
<script>


  function validateDate(fld) {
    var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
        	
    if (!((fld.match(RegExPattern)) && (fld!=''))){
        
        return false;
    
    }
    return true;
   }
  
	function callSave() {
	var dceno=document.getElementById('dceNo').value;
	var date=document.getElementById('dceDate').value;
	
	if(dceno=="") {
	alert("Please enter the DCE LIST No.");
	return false;
	}
	
	if(date=="") {
	alert("Please enter the DCE List Date.");
	return false;
	
	}
	
	if(date!="" ){
			var checkDate = validateDate(date);
			 if(!checkDate ) {
		    alert("DCE LIST Date is not a valid Date.\nDate should be in 'DD-MM-YYYY' format");
		    return false;
	 	   }
		}
	
	
	if (document.getElementById('checkboxSel').value !="C")
		{
		alert('Please Select a CheckBox');
		return false;
		}
	return true;	
	}	  
  
	function callChk(id){

 	if(document.getElementById(id).value=='Y'){
  		document.getElementById(id).value='N';
  		 document.getElementById('checkboxSel').value='';
 		}else  if(document.getElementById(id).value=='N'){
  		document.getElementById(id).value='Y';
  		 document.getElementById('checkboxSel').value='C';
 		} 
	}
	
	
	
	
   
   
   
   function callReport(){
  
	var dceno=document.getElementById('dceNo').value;
	
	var date=document.getElementById('dceDate').value;
	
	if (document.getElementById('checkboxSel').value !="C")
		{
		alert('Please Select a CheckBox');
		return false;
		}
		
	if(dceno=="") {
	alert("Please enter the DCE LIST No.");
	return false;
	}
	
	if(date=="") {
	alert("Please enter the DCE List Date.");
	return false;
	}
	
	if(date!=""){
			var checkDate = validateDate(date);
		
			 if(!checkDate) {
		   	 alert("DCE LIST Date is not a valid Date.\nDate should be in 'DD-MM-YYYY' format");
		     return false;
		   }
			
		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action='TransferPmApproval_dcelist.action';	
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target=main;
		return true; 
		   
		} 
	   }
   
   
   
 
   
	


  </script>
  <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>