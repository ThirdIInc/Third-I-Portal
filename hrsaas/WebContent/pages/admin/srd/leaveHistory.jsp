<%@ taglib uri ="/struts-tags" prefix = "s" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action = "LeaveHistory" target="main" theme="simple" validate ="true" id ="paraFrm">
<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/images/recruitment/lines.gif" class="txt"><img
				src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
		</tr>
		<tr>
          <td colspan="3" width="100%">
              <%@ include file="hrmHeader.jsp" %>
              
          
					</td>
        </tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		<tr>
			<td valign="bottom" class="txt"><strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Leave History 
			 </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr><s:if
								test="%{leaveHistory.generalFlag}"></s:if> <s:else>
								 <s:if test="%{leaveHistory.viewFlag}">
					<td  width="78%"><input type="button" class="search"
							onclick="javascript:callsF9(500,325,'LeaveHistory_f9actionEmployeeId.action');"
							value="    Search " /></td>
							</s:if>
							</s:else>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="text_head"><strong
								class="forminnerhead">Leave History </strong></td>
						</tr>
						<tr>
							<td width="20%" colspan="1">Employee
							:<font color="red">*</font></td>
							<td width="80%" colspan="3"><s:textfield size="15"
								theme="simple" name="leaveHistory.tokenNo" readonly="true" /> <s:textfield
								size="84" theme="simple" name="leaveHistory.empName"
								readonly="true" /> <s:hidden name="leaveHistory.empId" /> 
							</td>

						</tr>

						<tr>

							<td width="15%">Branch :</td>
							<td width="30%"><s:textfield size="25" theme="simple"
								name="leaveHistory.center" readonly="true" /></td>

							<td width="15%">Designation :</td>
							<td width="40%" align="center"><s:textfield size="30" theme="simple"
								name="leaveHistory.rank" readonly="true" /></td>
						</tr>
						</table>
					</td>
				</tr>


			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="text_head"><strong
								class="forminnerhead">Leave Particulars </strong></td>
						</tr>
						<tr>
							<td width="20%">Type of Leave :<font color="red">*</font> </td>
							<td width="30%"><s:textfield size="25" theme="simple"
								name="leaveHistory.leaveType" readonly="true" /> <s:hidden
								theme="simple" name="leaveHistory.leaveTypeId" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								width="16" align="absmiddle" class="iconImage"
								onclick="javascript:callsF9(500,325,'LeaveHistory_f9actionLeaveType.action');">
							</td>
							<td width="20%">No of Days of Leave 
							:<font color="red">*</font></td>
							<td width="40%"><s:textfield size="30" theme="simple"
								name="leaveHistory.leaveDays" /></td>
						</tr>


						<tr>
							<td width="15%">From Date :<font color="red">*</font> </td>
							<td width="30%"><s:textfield size="12" theme="simple"
								name="leaveHistory.fromDate" /> <s:a
								href="javascript:NewCal('paraFrm_leaveHistory_fromDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16"
									class="iconImage">
							</s:a></td>
							<td width="15%">To Date :<font color="red">*</font></td>
							<td width="40%"><s:textfield size="12" theme="simple"
								name="leaveHistory.toDate" /> <s:a
								href="javascript:NewCal('paraFrm_leaveHistory_toDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif" 
				width="5" height="4" /></td>
		</tr>
		<tr>
			<td width="100%" colspan="4"><s:if
				test="%{leaveHistory.insertFlag}">
				<s:submit cssClass="add" action="LeaveHistory_save" theme="simple"
					value="    Insert" onclick="return callAdd();"  />
		</s:if><s:else></s:else> <s:if test="%{leaveHistory.updateFlag}">
				<s:submit cssClass="edit" action="LeaveHistory_save" theme="simple"
					value="    Update" onclick="return callUpdate();"  />
		</s:if><s:else></s:else> <s:if test="%{leaveHistory.generalFlag}"></s:if> <s:else>
				<s:submit cssClass="reset" action="LeaveHistory_reset"
					theme="simple" value="    Reset "  />
		</s:else></td>
		</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif" 
				width="5" height="4" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1">
						<tr>
							<td width="30%" class="formth">Type of Leave</td>
							<td width="10%" class="formth">No of Days of Leave</td>
							<td width="15%" class="formth">From Date</td>
							<td width="15%" class="formth">To Date</td>
							<td width="30%" class="formth"></td>
						</tr>
						<s:iterator value="leaveList">
							<tr>
								<td width="30%" class="border2"><s:property
									value="leaveType" /></td>
								<td width="10%" class="border2"><s:property
									value="leaveDays" /></td>
								<td width="15%" class="border2"><s:property
									value="fromDate" /></td>
								<td width="25%" class="border2"><s:property value="toDate" /></td>
								<td width="30%" class="border2"><s:if
									test="%{leaveHistory.updateFlag}">
									<input type="button" class="edit"
										onclick="callForEdit('<s:property value="leaveId"/>')"
										value="    Edit" />
								</s:if> <s:if test="%{leaveHistory.deleteFlag}">
									<input type="button" class="delete"
										onclick="callDelete('<s:property value="leaveId"/>')"
										value="    Delete" />
								</s:if></td>
							</tr>
						</s:iterator>
					</table>
					<s:hidden name="leaveHistory.paracode" /> <s:hidden
						name="leaveHistory.leaveId" /> 
						</tr>
			</table>
			</td>
		</tr>
	</table>
	<br />


	<label></label>

</s:form>
<script >
function callAdd(){

	var empId = document.getElementById('paraFrm_leaveHistory_empName').value;
	var leaveType = document.getElementById('paraFrm_leaveHistory_leaveType').value;
	var leaveDays = document.getElementById('paraFrm_leaveHistory_leaveDays').value;
	var fromDate = document.getElementById('paraFrm_leaveHistory_fromDate').value;
	var toDate = document.getElementById('paraFrm_leaveHistory_toDate').value;
	
	if(!( document.getElementById('paraFrm_leaveHistory_leaveId').value=="")) {
	alert("You can't Insert.Please Update");
	return false;
	
	}
	
	
	if(empId==""){
			alert ("Please select the Employee");
			return false;
		}
		if(leaveType==""){
			alert ("Please select the LeaveType");
			return false;
		}
	if(leaveDays==""){
			alert ("Please enter Leave Days");
			return false;
		}
	if(!(leaveDays=="")){
			var iChars = "0123456789";
		  		for (var i = 0; i < leaveDays.length; i++) {			
			  	if (!(iChars.indexOf(leaveDays.charAt(i)) != -1)) {
				  	alert ("Leave Days should be number !");
				  	return false;
  					}
  				}
		}	
		
	if(fromDate==""){
			alert ("Please enter From Date");
			return false;
		}
	var checkFromDate = validateDate(fromDate);
	
	if(!checkFromDate){
		alert (" From Date is not a valid date");
		return false;
	}		
		
	if(toDate==""){
			alert ("Please enter To Date");
			return false;
	 }
	 var checkToDate = validateDate(toDate);
	
	if(!checkToDate){
		alert (" To Date is not a valid date");
		return false;
	}			
	var checkDate = datedifference();	
	if (!checkDate){
		alert("To Date must be greater than From Date");
		return false;
	}	
			
	return true ;
}




function callUpdate(){

	var empId = document.getElementById('paraFrm_leaveHistory_empName').value;
	var leaveType = document.getElementById('paraFrm_leaveHistory_leaveType').value;
	var leaveDays = document.getElementById('paraFrm_leaveHistory_leaveDays').value;
	var fromDate = document.getElementById('paraFrm_leaveHistory_fromDate').value;
	var toDate = document.getElementById('paraFrm_leaveHistory_toDate').value;
		if(document.getElementById('paraFrm_leaveHistory_leaveId').value=="") {
		alert("You can't Update.Please Insert");
		return false;
	
		}
	
	if(empId==""){
			alert ("Please select the Employee");
			return false;
		}
		if(leaveType==""){
			alert ("Please select the LeaveType");
			return false;
		}
	if(leaveDays==""){
			alert ("Please enter Leave Days");
			return false;
		}
	if(!(leaveDays=="")){
			var iChars = "0123456789";
		  		for (var i = 0; i < leaveDays.length; i++) {			
			  	if (!(iChars.indexOf(leaveDays.charAt(i)) != -1)) {
				  	alert ("Leave Days should be number !");
				  	return false;
  					}
  				}
		}	
		
	if(fromDate==""){
			alert ("Please enter From Date");
			return false;
		}
	var checkFromDate = validateDate(fromDate);
	
	if(!checkFromDate){
		alert (" From Date is not a valid date");
		return false;
	}		
		
	if(toDate==""){
			alert ("Please enter To Date");
			return false;
	 }
	 var checkToDate = validateDate(toDate);
	
	if(!checkToDate){
		alert (" To Date is not a valid date");
		return false;
	}			
	var checkDate = datedifference();	
	if (!checkDate){
		alert("To Date must be greater than From Date");
		return false;
	}	
			
	return true ;
}

function callForEdit(id){
	
	   	document.getElementById("paraFrm").action="LeaveHistory_edit.action";
	    document.getElementById('paraFrm_leaveHistory_paracode').value=id;
	    document.getElementById("paraFrm").submit();
   }
function callDelete(id){

		var r=confirm("Are you sure to delete this record?")
		if(r==false){
			return false;
		}else{

	   	document.getElementById("paraFrm").action="LeaveHistory_delete.action";
	   	document.getElementById('paraFrm_leaveHistory_paracode').value=id;
	    document.getElementById("paraFrm").submit();
	    }
   }
   
 function validateDate(fld) {
    var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
        	
    if (!((fld.match(RegExPattern)) && (fld!=''))){
        
        return false;
    
    }
    return true;
   }
    
 function datedifference() { 
	var fromDate = document.getElementById('paraFrm_leaveHistory_fromDate').value;
	var toDate = document.getElementById('paraFrm_leaveHistory_toDate').value;

	fromDate = fromDate.split("-"); 
	fromTime = new Date(fromDate[2],fromDate[1]-1,fromDate[0]); 

	toDate = toDate.split("-"); 
	toTime = new Date(toDate[2],toDate[1]-1,toDate[0]); 

	if(toTime >= fromTime) 
	{ 
		return true;
		
	}else{
	return false;
	}
	
}
</script>