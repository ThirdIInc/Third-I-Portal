<%@ taglib uri ="/struts-tags" prefix = "s" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action = "AcpHistory" target="main" theme="simple" validate ="true" id ="paraFrm">
<table class ="bodyTable" width="100%" colspan ="4" >
	<tr>
		<td class ="pageHeader" colspan="4" ><center>ACP/Placement History</center></td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	
	<tr>
		<td width = "15%" colspan="1" >Employee<font color="red">*</font> :</td>
		<td width = "85%" colspan="3" >
			<s:if test="%{acpHistory.generalFlag}"></s:if>
			<s:else>
			<img src ="../pages/images/search.gif" height="16" width="16"align="absmiddle" onclick="javascript:callsF9(500,325,'AcpHistory_f9actionEmployeeId.action');">
			</s:else>
			<s:textfield size="15" theme="simple" name ="acpHistory.tokenNo" readonly="true" />
			<s:textfield size="85" theme="simple" name ="acpHistory.empName" readonly="true"  />
			
		</td>
		
	</tr>
	
	
	<tr>
	
		<td width = "15%">Center : </td>
		<td width = "30%">
			<s:textfield size = "30" theme="simple" name ="acpHistory.center" readonly="true" />
		</td>
		
		<td width = "15%">Rank :</td>
		<td width = "40%">
			<s:textfield size = "30" theme="simple" name ="acpHistory.rank" readonly="true" />
			
		</td>
	</tr>
	<tr>
	
		<td width ="100%" colspan="4">&nbsp; </td>
		
	</tr>
	
	<tr>
	
		<td colspan="4" class="sectionhead">ACP/Placement Particulars</td>
		
	</tr>
	
	<tr>
	
		<td width ="100%" colspan="4">&nbsp; </td>
		
	</tr>
	
	<tr>
		<td width = "15%" >Post<font color="red">*</font> :</td>
		<td width = "30%">
			<s:textfield size = "25" theme="simple" name ="acpHistory.post" readonly="true" />
			<s:hidden theme="simple" name ="acpHistory.postId" />
		    	<img src ="../pages/images/search.gif" height="16" width="16"align="absmiddle" onclick="javascript:callsF9(500,325,'AcpHistory_f9actionPost.action');">
		</td>
		<td width = "15%" >Trade<font color="red">*</font> :</td>
		<td width = "40%">
			<s:textfield size = "25" theme="simple" name ="acpHistory.trade" readonly="true" />
			<s:hidden theme="simple" name ="acpHistory.tradeId" />
				<img src ="../pages/images/search.gif" height="16" width="16"align="absmiddle" onclick="javascript:callsF9(500,325,'AcpHistory_f9actionTrade.action');">
		</td>
	</tr>
	
	<tr>
		<td width = "15%" >Placement Date<font color="red">*</font> :</td>
		<td width = "30%">
			<s:textfield size = "25" theme="simple" name ="acpHistory.fromDate"  />
			<s:a href="javascript:NewCal('acpHistory.fromDate','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a>	
		</td>
		<td  width = "15%">Pay Scale<font color="red">*</font> :</td>
		<td width = "40%">
			<s:textfield size = "25" theme="simple" name ="acpHistory.payScale" readonly="true" />
			
			<img src ="../pages/images/search.gif" height="16" width="16"align="absmiddle" onclick="javascript:callsF9(500,325,'AcpHistory_f9actionPayScale.action');">
		</td>
		
	</tr>
	
	<tr>
		
		<td width = "15%" >DCE list No.<font color="red">*</font> :</td>
		<td width = "30%" >
			<s:textfield size = "30" theme="simple" name ="acpHistory.dceListNo"  />
		</td>
	
		<td width = "15%" >DCE Date<font color="red">*</font> :</td>
		<td width = "40%">
			<s:textfield size = "25" theme="simple" name ="acpHistory.dceListDate" />
			<s:a href="javascript:NewCal('acpHistory.dceListDate','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a>	
		</td>
	
	</tr>
	
	<tr>
		
		<td width = "15%" >DCE Serial No.<font color="red">*</font> :</td>
		<td width = "40%">
			<s:textfield size = "30" theme="simple" name ="acpHistory.serialNo"  />
		</td>
	
	</tr>
	<tr><td>&nbsp;</td></tr>
	
	
	<tr >
		<td width="100%" align="center" colspan="4">
		<s:if test="%{acpHistory.insertFlag}">
		<s:submit cssClass="pagebutton" action = "AcpHistory_save" theme="simple" value=" Insert "  onclick="return callAdd();"   />&nbsp;
		</s:if><s:else></s:else>
		<s:if test="%{acpHistory.updateFlag}">
		<s:submit cssClass="pagebutton" action = "AcpHistory_save" theme="simple" value=" Update "  onclick="return callUpdate();"   />&nbsp;
		</s:if><s:else></s:else>
		
		
		<s:if test="%{acpHistory.generalFlag}"></s:if>
		<s:else>
		<s:submit cssClass="pagebutton" action = "AcpHistory_reset" theme="simple" value=" Reset "  />&nbsp;
		</s:else>
		</td>
	</tr>

</table>
		<table class="bodyTable" width="100%">
			<tr>
				<td width="100%" colspan="7">&nbsp;</td>
			</tr>
			<tr>
			    <td width="15%" class="headercell">Post</td>
			    <td width="15%" class="headercell">Trade</td>
			    <td width="15%" class="headercell">Placement Date</td>
			    <td width="20%" class="headercell">Pay Scale</td>
			    <td width="8%" class="headercell">DCE List No.</td>
			    <td width="8%" class="headercell">DCE Serial No.</td>
			     <td width="30%" class="headercell"></td>
			  </tr>
			  <s:iterator value="acpList" >   
			  <tr>
				   	<td width="15%" class="bodyCell"><s:property value="post"/></td>
				    <td width="15%" class="bodyCell"><s:property value="trade"/></td>
				    <td width="15%" class="bodyCell"><s:property value="fromDate"/></td>
				    <td width="20%" class="bodyCell"><s:property value="payScale"/></td>
				    <td width="8%" class="bodyCell"><s:property value="dceListNo"/></td>
				    <td width="8%" class="bodyCell"><s:property value="serialNo"/></td>
				    <td width="30%" class="bodyCell">
				    
				    <s:if test="%{acpHistory.updateFlag}">
				    	 <input type="button" class="pagebutton" onclick="callForEdit('<s:property value="acpId"/>')"  value="Edit"/>
				    </s:if>
				    <s:else></s:else>
				    <s:if test="%{acpHistory.deleteFlag}">
				    <input type="button" class="pagebutton" onclick="callDelete('<s:property value="acpId"/>')"  value="Delete"/>
				    </s:if>
				    <s:else></s:else>
				    </td> 
			    </tr>
			  </s:iterator>
</table>
	
	<s:hidden name="acpHistory.paracode" />
	<s:hidden  name ="acpHistory.empId"  />
	<s:hidden  name ="acpHistory.acpId" />
	<s:hidden  name ="acpHistory.payScaleId" />
	
</s:form> 
<script >

function callAdd(){
	var empId = document.getElementById('acpHistory.empName').value;
	var post = document.getElementById('acpHistory.post').value;
	var trade = document.getElementById('acpHistory.trade').value;
	var fromDate = document.getElementById('acpHistory.fromDate').value;
	var payScale = document.getElementById('acpHistory.payScale').value;
	var serialNo = document.getElementById('acpHistory.serialNo').value;
	var dceListNo = document.getElementById('acpHistory.dceListNo').value;
	var dceListDate = document.getElementById('acpHistory.dceListDate').value;
	if(!(document.getElementById('acpHistory.acpId').value=="")) {
	alert("You can't Insert.Please Update");
	return false;
	
	}
	if(empId==""){
			alert ("Please select the Employee");  
			return false;
		}
		
	if(post==""){
			alert ("Please select the Post");
			return false;
		}
	if(trade==""){
			alert ("Please select the Trade");
			return false;
		}
	if(fromDate==""){
			alert ("Please enter the Placement Date");
			return false;
		}
	var checkDate = validateDate(fromDate);
	
	if(!checkDate){
		alert (" Placement Date is not a valid date");
		return false;
	}	
	if(payScale==""){
			alert ("Please select the Pay Scale");
			return false;
		}
		
	if(dceListNo==""){
			alert ("Please enter DCE List No.");
			return false;
		}
		
	if(dceListDate==""){
			alert ("Please enter DCE Date");
			return false;
		}
	var checkDate1 = validateDate(dceListDate);
	
	if(!checkDate1){
		alert ("DCE List Date is not a valid date");
		return false;
	}					
	
	if(serialNo==""){
			alert ("Please enter DCE Serial No.");
			return false;
		}
	if(!(serialNo=="")){
			var iChars = "0123456789.";
		  		for (var i = 0; i < serialNo.length; i++) {			
			  	if (!(iChars.indexOf(serialNo.charAt(i)) != -1)) {
				  	alert ("DCE Serial No. should be number !");
				  	return false;
  					}
  				}
		}									
			
	return true ;
}



function callUpdate(){
	var empId = document.getElementById('acpHistory.empName').value;
	var post = document.getElementById('acpHistory.post').value;
	var trade = document.getElementById('acpHistory.trade').value;
	var fromDate = document.getElementById('acpHistory.fromDate').value;
	var payScale = document.getElementById('acpHistory.payScale').value;
	var serialNo = document.getElementById('acpHistory.serialNo').value;
	var dceListNo = document.getElementById('acpHistory.dceListNo').value;
	var dceListDate = document.getElementById('acpHistory.dceListDate').value;
	if(document.getElementById('acpHistory.acpId').value=="") {
	alert("You can't Update.Please Insert");
	return false;
	
	}
	if(empId==""){
			alert ("Please select the Employee");  
			return false;
		}
		
	if(post==""){
			alert ("Please select the Post");
			return false;
		}
	if(trade==""){
			alert ("Please select the Trade");
			return false;
		}
	if(fromDate==""){
			alert ("Please enter the Placement Date");
			return false;
		}
	var checkDate = validateDate(fromDate);
	
	if(!checkDate){
		alert (" Placement Date is not a valid date");
		return false;
	}	
	if(payScale==""){
			alert ("Please select the Pay Scale");
			return false;
		}
		
	if(dceListNo==""){
			alert ("Please enter DCE List No.");
			return false;
		}
		
	if(dceListDate==""){
			alert ("Please enter DCE Date");
			return false;
		}
	var checkDate1 = validateDate(dceListDate);
	
	if(!checkDate1){
		alert ("DCE List Date is not a valid date");
		return false;
	}					
	
	if(serialNo==""){
			alert ("Please enter DCE Serial No.");
			return false;
		}
	if(!(serialNo=="")){
			var iChars = "0123456789.";
		  		for (var i = 0; i < serialNo.length; i++) {			
			  	if (!(iChars.indexOf(serialNo.charAt(i)) != -1)) {
				  	alert ("DCE Serial No. should be number !");
				  	return false;
  					}
  				}
		}									
			
	return true ;
}




function callForEdit(id){
		
	   	document.getElementById("paraFrm").action="AcpHistory_edit.action";
	    document.getElementById('acpHistory.paracode').value=id;
	    document.getElementById("paraFrm").submit();
   }
function callDelete(id){

	var r=confirm("Are you sure to delete this record?")
		if(r==false){
			return false;
		}else{


	   	document.getElementById("paraFrm").action="AcpHistory_delete.action";
	   	document.getElementById('acpHistory.paracode').value=id;
	    document.getElementById("paraFrm").submit();
	    }
   }
   
   function numericsonly(myfield)
{
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}




   function validateDate(fld) {
    var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
        	
    if (!((fld.match(RegExPattern)) && (fld!=''))){
        
        return false;
    
    }
    return true;
   }
</script>