<%@ taglib uri ="/struts-tags" prefix = "s" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action = "DqeHistory" target="main" theme="simple" validate ="true" id ="paraFrm">
<table class ="bodyTable" width="100%" colspan ="4" >
	<tr>
		<td class ="pageHeader" colspan="4" ><center>Departmental Qualification Examination History </center></td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	
	<tr>
		<td width = "15%" colspan="1" >Employee <font color="red">*</font> :</td>
		<td width = "85%" colspan="3" >
		<s:if test="%{dqeHistory.generalFlag}"></s:if>
			<s:else>
			<img src ="../pages/images/search.gif" height="16" width="16"align="absmiddle" onclick="javascript:callsF9(500,325,'DqeHistory_f9actionEmployeeId.action');">
		</s:else>
			<s:textfield size="15" theme="simple" name ="dqeHistory.tokenNo" readonly="true" />
			<s:textfield size="70" theme="simple" name ="dqeHistory.empName" readonly="true"  />
			
		</td>
		
	</tr>
	
	<tr>
		<td width = "15%">Center :</td>
		<td width = "30%">
			<s:textfield size = "30" theme="simple" name ="dqeHistory.center" readonly="true" />
		</td>
		
		<td width = "15%">Rank :</td>
		<td width = "40%">
			<s:textfield size = "30" theme="simple" name ="dqeHistory.rank" readonly="true" />
	</tr>
	<tr>
	
		<td width ="100%" colspan="4">&nbsp; </td>
		
	</tr>
	<tr>
	
		<td colspan="4" class="sectionhead">Departmental Particulars</td>
		
	</tr>
	<tr>
	
		<td width ="100%" colspan="4">&nbsp; </td>
		
	</tr>
	<tr>
		<td width = "15%" >Post<font color="red">*</font> :</td>
		<td width = "30%">
			<s:textfield size = "25" theme="simple" name ="dqeHistory.post" readonly="true" />
			   	<img src ="../pages/images/search.gif" height="16" width="16"align="absmiddle" onclick="javascript:callsF9(500,325,'DqeHistory_f9actionPost.action');">
		</td>
		<td  width = "15%">Trade <font color="red">*</font> :</td>
		<td width = "40%">
			<s:textfield size = "25" theme="simple" name ="dqeHistory.trade" readonly="true" />
				<img src ="../pages/images/search.gif" height="16" width="16"align="absmiddle" onclick="javascript:callsF9(500,325,'DqeHistory_f9actionTrade.action');">
		</td>
	</tr>
	
	<tr>
		<td width = "15%" >Date <font color="red">*</font> :</td>
		<td width = "30%">
			<s:textfield size = "25" theme="simple" name ="dqeHistory.date" />
			<s:a href="javascript:NewCal('dqeHistory.date','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a>	
		</td>
		<td width = "15%" >DCE List No. <font color="red">*</font> :</td>
		<td width = "40%">
			<s:textfield size = "30" theme="simple" name ="dqeHistory.dcList"   />
			
		</td>
		
	</tr>
	
	<tr>
		<td  width = "15%">DCE Serial No. <font color="red">*</font> :</td>
		<td width = "30%">
			<s:textfield size = "30" theme="simple" name ="dqeHistory.serialNo"  />
		</td>
		<td  width = "15%">DCE List Date <font color="red">*</font> :</td>
		<td width = "40%">
			<s:textfield size = "25" theme="simple" name ="dqeHistory.dcListDate" />
			<s:a href="javascript:NewCal('dqeHistory.dcListDate','DDMMYYYY');"><img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a>	
		</td>
	
	</tr>
	<tr><td>&nbsp;</td></tr>
	
	
	<tr  >
		<td colspan="4" align="center" width="100%">
	
		<s:if test="%{dqeHistory.insertFlag}">
		<s:submit cssClass="pagebutton" action = "DqeHistory_save" theme="simple" value=" Insert " onclick="return callAdd();"  />&nbsp;
		</s:if><s:else></s:else>
		
		<s:if test="%{dqeHistory.updateFlag}">
		<s:submit cssClass="pagebutton" action = "DqeHistory_save" theme="simple" value=" Update " onclick="return callUpdate();"  />&nbsp;
		</s:if><s:else></s:else>
		
		
		<s:if test="%{dqeHistory.generalFlag}"></s:if>
		<s:else>
		<s:submit cssClass="pagebutton" action = "DqeHistory_reset" theme="simple" value=" Reset "  />&nbsp;
		</s:else>
		</td>
	</tr>
</table>

		<table class="bodyTable" width="100%">
			<tr>
				<td width="100%" colspan="6">&nbsp;</td>
			</tr>
			<tr>
			    <td width="15%" class="headercell">Post</td>
			    <td width="15%" class="headercell">Trade</td>
			    <td width="15%" class="headercell">Date</td>
			    <td width="15%" class="headercell">DCE List Number</td>
			    <td width="15%" class="headercell">DCE List Date</td>
			    <td width="8%" class="headercell">DCE Serial No.</td>
			     <td width="30%" class="headercell"></td>
			  </tr>
			  <s:iterator value="dqeList" >   
			  <tr>
				   	<td width="15%" class="bodyCell"><s:property value="post"/></td>
				    <td width="15%" class="bodyCell"><s:property value="trade"/></td>
				    <td width="15%" class="bodyCell"><s:property value="date"/></td>
				    <td width="15%" class="bodyCell"><s:property value="dcList"/></td>
				    <td width="15%" class="bodyCell"><s:property value="dcListDate"/></td>
				    <td width="8%" class="bodyCell"><s:property value="serialNo"/></td>
				    <td width="30%" class="bodyCell">
				    <s:if test="%{dqeHistory.updateFlag}">
				        <input type="button" class="pagebutton" onclick="callForEdit('<s:property value="dqeId"/>')"  value="Edit"/>
				    </s:if>
				     <s:if test="%{dqeHistory.deleteFlag}">
				        <input type="button" class="pagebutton" onclick="callDelete('<s:property value="dqeId"/>')"  value="Delete"/>
				    </s:if>
				    </td> 
			    </tr>
			  </s:iterator>
		</table>
	<s:hidden name="dqeHistory.paracode" />
	<s:hidden  name ="dqeHistory.empId"  />
	<s:hidden  name ="dqeHistory.dqeId" />
	<s:hidden theme="simple" name ="dqeHistory.postId" />
	<s:hidden theme="simple" name ="dqeHistory.tradeId" />
</s:form> 
<script >




function callAdd(){
	var empId = document.getElementById('dqeHistory.empName').value;
	var post = document.getElementById('dqeHistory.post').value;
	var trade = document.getElementById('dqeHistory.trade').value;
	var date = document.getElementById('dqeHistory.date').value;
	var dcList = document.getElementById('dqeHistory.dcList').value;
	var serialNo = document.getElementById('dqeHistory.serialNo').value;
	var dceDate = document.getElementById('dqeHistory.dcListDate').value;
	
	if(!(document.getElementById('dqeHistory.dqeId').value=="")) {
	alert("You cannot Insert.Please Update");
	return false;
	}
	
	if(empId==""){
			alert ("Please select the Employee");  
			return false;
		}
		
	if(post==""){
			alert ("Please select the Post !");
			return false;
		}
	if(trade==""){
			alert ("Please select the Trade !");
			return false;
		}
	if(date==""){
			alert (" Please enter the Date ");
			return false;
		}
	var checkDate = validateDate(date);
	
	if(!checkDate){
		alert (" Date is not valid");
		return false;
	}
	if(dcList==""){
			alert ("Please enter DCE List No.");
			return false;
		}	
					
	if(serialNo==""){
			alert ("Please enter DCE Serial No.");
			return false;
		}
		
	if(dceDate==""){
			alert ("Please enter DCE List Date");
			return false;
		}
	var checkDate1 = validateDate(dceDate);
	
	if(!checkDate1){
		alert ("DCE List Date is not a valid date");
		return false;
	}						
			
	return true ;
}



function callUpdate(){
	var empId = document.getElementById('dqeHistory.empName').value;
	var post = document.getElementById('dqeHistory.post').value;
	var trade = document.getElementById('dqeHistory.trade').value;
	var date = document.getElementById('dqeHistory.date').value;
	var dcList = document.getElementById('dqeHistory.dcList').value;
	var serialNo = document.getElementById('dqeHistory.serialNo').value;
	var dceDate = document.getElementById('dqeHistory.dcListDate').value;
	
	if(document.getElementById('dqeHistory.dqeId').value=="") {
	alert("You cannot Update.Please Insert");
	return false;
	}
	
	if(empId==""){
			alert ("Please select the Employee");  
			return false;
		}
		
	if(post==""){
			alert ("Please select the Post !");
			return false;
		}
	if(trade==""){
			alert ("Please select the Trade !");
			return false;
		}
	if(date==""){
			alert (" Please enter the Date ");
			return false;
		}
	var checkDate = validateDate(date);
	
	if(!checkDate){
		alert (" Date is not valid");
		return false;
	}
	if(dcList==""){
			alert ("Please enter DCE List No.");
			return false;
		}	
					
	if(serialNo==""){
			alert ("Please enter DCE Serial No.");
			return false;
		}
		
	if(dceDate==""){
			alert ("Please enter DCE List Date");
			return false;
		}
	var checkDate1 = validateDate(dceDate);
	
	if(!checkDate1){
		alert ("DCE List Date is not a valid date");
		return false;
	}						
			
	return true ;
}


function callForEdit(id){
		document.getElementById("paraFrm").action="DqeHistory_edit.action";
	    document.getElementById('dqeHistory.paracode').value=id;
	    document.getElementById("paraFrm").submit();
   }
function callDelete(id){

	var r=confirm("Are you sure to delete this record?")
		if(r==false){
			return false;
		}else{

	   	document.getElementById("paraFrm").action="DqeHistory_delete.action";
	   	document.getElementById('dqeHistory.paracode').value=id;
	    document.getElementById("paraFrm").submit();
	    }
   }
   
   
   function alphanumericsonly(myfield)
{
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
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