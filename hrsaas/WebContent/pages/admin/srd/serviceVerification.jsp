<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="ServiceVerification" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="bodyTable" width="100%" >
		<tr>
          <td colspan="4" width="100%">
              <%@ include file="hrmHeader.jsp" %>
              
          
					</td>
        </tr>
		<tr>
			<td width="100%" colspan="4" class="pageHeader" align="center">Service
			Verification</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>

		<tr>
			<td width="25%" colspan="1">Employee :<font color="red">*</font></td>
			<s:hidden name="servVerification.serviceID" />
			<s:hidden name="servVerification.empCode" />
			<td width="75%" colspan="3"><s:if
				test="%{servVerification.generalFlag}">
			</s:if> <s:else>
				<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
					width="18"
					onclick="javascript:callsF9(500,325,'ServiceVerification_f9empaction.action');">
			</s:else> <s:textfield name="servVerification.empToken" theme="simple"
				id="empToken" size="10" readonly="true" /><s:textfield size="81"
				readonly="true" name="servVerification.empName" /></td>

		</tr>

		<!--<tr>
	  		<td width="25%"   colspan="1" >Select Employee : </td>
	  		<td width="75%">
	  		<s:hidden name="servVerification.serviceID" />
	  		<s:hidden name="servVerification.empID" />
	  		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
	  		width="18" onclick="javascript:callsF9(500,325,'ServiceVerification_f9empaction.action');"></td>
	</tr>
	<tr>
	  		<td width="25%"  colspan="1" >Employee Name : </td>
	  		<td width="75%" colspan="3"><s:textfield name="servVerification.empName" size="66"/>
	  		</td>
	</tr>
	
	
	-->

		<tr>
			<td width="25%" colspan="1">Center:</td>
			<td width="25%" colspan="1"><s:textfield size="30"
				readonly="true" name="servVerification.center" /></td>
			<td width="25%" colspan="1">Rank:</td>
			<td width="25%" colspan="1"><s:textfield size="30"
				readonly="true" name="servVerification.rank" /></td>

		</tr>
		
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
		<tr>
		<td colspan="4" class="sectionhead">Verification Particulars</td>
		</tr>
		
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
		

		<tr>
			<td width="25%" colspan="1">From Date
			:<font color="red">*</font></td>
			<td width="25%"><s:textfield size="25"
				name="servVerification.fromDate"  onkeypress="return numbersonly(this);"/><s:a
				href="javascript:NewCal('servVerification.fromDate','DDMMYYYY');">
				<img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"
					width="16" />
			</s:a></td>
			<td width="25%">To Date :<font color="red">*</font></td>
			<td width="25%"><s:textfield size="25"
				name="servVerification.toDate" onkeypress="return numbersonly(this);"/><s:a
				href="javascript:NewCal('servVerification.toDate','DDMMYYYY');"
				onclick="return callCheck();">
				<img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"
					width="16" />
			</s:a></td>
		</tr>

		<tr>
			<td width="25%" colspan="1">Qualifying Service :</td>
			<td width="75%" colspan="3"><s:textfield size="5.8"
				name="servVerification.qualSerYear" maxlength="2" onkeypress="return numbersonly(this);"/> <s:textfield size="3.8"
				name="servVerification.qualSerMonth" maxlength="2" onkeypress="return numbersonly(this);"/> <s:textfield
				size="3.8" name="servVerification.qualSerDays" maxlength="2" onkeypress="return numbersonly(this);"/></td>
		</tr>
		<tr>
			<td width="25%" colspan="1">&nbsp;</td>
			<td width="75%" colspan="3">(Years) (Months) (Days)</td>


		</tr>
		<tr>
			<td width="25%" colspan="1">Pay:</td>
			<td width="25%" colspan="1"><s:textfield size="25"
				name="servVerification.pay" /></td>
			<td width="25%" colspan="1">Pay Scale:</td>
			<td width="25%" colspan="1"><s:hidden
				name="servVerification.payID" theme="simple" /><s:textfield
				size="25" name="servVerification.payScale" theme="simple"
				readonly="true" /><img src="../pages/images/search.gif" class="iconImage" height="18"
				align="absmiddle" width="18"
				onclick="javascript:callsF9(500,325,'ServiceVerification_f9PayScaleaction.action');" /></td>
		</tr>




		<tr>
			<td width="25%" colspan="1">Auditor Details:</td>
			<td width="25%" colspan="1"><s:textfield size="25"
				name="servVerification.attestOfficer" theme="simple" onkeypress="return alphabetsonly(this)" /></td>
			<td width="25%" colspan="1">Verifying Officer:</td>
			<td width="25%" colspan="1"><s:textfield size="25"
				name="servVerification.verifOfficer" theme="simple" onkeypress="return alphabetsonly(this)" /></td>
		</tr>

		<tr>
			<td width="25%" colspan="1">Auditor Remarks:</td>
			<td width="85%" colspan="3"><s:textarea
				name="servVerification.remark" cols="70" rows="3" /></td>

		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>

		<tr align="center">

			<td colspan="4">
			<s:if test="%{servVerification.insertFlag}">
			<s:submit cssClass="pagebutton" action="ServiceVerification_save"
				theme="simple" value="   Insert   " onclick="return formValidate()" />
				</s:if><s:else></s:else>
				<s:if test="%{servVerification.updateFlag}">
				<s:submit cssClass="pagebutton" action="ServiceVerification_save"
				theme="simple" value="   Update   " onclick="return updateFormValidate()" />
				</s:if><s:else></s:else>
			<s:if test="%{servVerification.generalFlag}">
			</s:if> <s:else>
				<s:submit cssClass="pagebutton" action="ServiceVerification_reset" theme="simple"
					value=" Reset  " />
			</s:else></td>

		</tr>




	</table>

	<table class="bodyTable" width="100%">
		<tr>
			<td width="100%" colspan="6">&nbsp;</td>
		</tr>
		<tr>
			<td width="10%" rowspan="2" height="36" class="headercell">From
			Date</td>
			<td width="10%" rowspan="2" height="36" class="headercell">To
			Date</td>
			<td width="21%" colspan="3" height="19" class="headercell" nowrap="nowrap">
			<p align="center">Qualifying Service
			</td>
			<!--<td width="10%" rowspan="2" height="36" class="headercell">Attesting
			Officer</td>
			<td width="10%" rowspan="2" height="36" class="headercell">Verifying
			Officer</td>
			-->
			<td width="20%" rowspan="2" height="36" class="headercell">Pay</td>
			<td width="20%" rowspan="2" height="36" class="headercell">Pay
			Scale</td>

			<!--<td width="10%" rowspan="2" height="36" class="headercell">Remark</td>
			-->
			<td width="9%" rowspan="2" height="36" class="headercell"></td>

		</tr>
		<tr>
			<td width="7%" height="17" class="headercell" nowrap="nowrap">Years</td>
			<td width="7%" height="17" class="headercell" nowrap="nowrap">Months</td>
			<td width="7%" height="17" class="headercell" nowrap="nowrap">Days</td>
		</tr>
		<s:iterator value="serviceList" status="stat">
			<tr>
				<td width="10%" class="bodyCell"><s:property value="fromDate" /></td>
				<td width="10%" class="bodyCell"><s:property value="toDate" /></td>
				<td width="7%" class="bodyCell"><s:property value="qualSerYear" /></td>
				<td width="7%" class="bodyCell"><s:property
					value="qualSerMonth" /></td>
				<td width="7%" class="bodyCell"><s:property value="qualSerDays" /></td>

				<td width="20%" class="bodyCell"><s:property value="pay" /></td>
				<td width="20%" class="bodyCell"><s:property value="payScale" /></td>



				<td width="14%" class="bodyCell"><s:if
					test="%{servVerification.updateFlag}">
					<input type="button" class="pagebutton"
						onclick="callForEdit('<s:property value="serviceID"/>')"
						value=" Edit " />
				</s:if> <s:if test="%{servVerification.deleteFlag}">
					<input type="button" class="pagebutton"
						onclick="callDelete('<s:property value="serviceID"/>')"
						value="Delete" />
				</s:if></td>

			</tr>
		</s:iterator>
	</table>
	<s:hidden name="servVerification.paraID" />
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>
 
    function callForEdit(id){
	   	document.getElementById("paraFrm").action="ServiceVerification_edit.action";
	  	document.getElementById('servVerification.paraID').value=id;
	    document.getElementById("paraFrm").submit();
   }
   function callDelete(id){
   
   		var conf=confirm("Are you sure to delete this record?");
   		if(conf) {
   	   	document.getElementById("paraFrm").action="ServiceVerification_delete.action";
	   	document.getElementById('servVerification.paraID').value=id;
	  	document.getElementById("paraFrm").submit();
   } else {
   		return false;
   }
   }
   
   
   function updateFormValidate(){
  		var empName =document.getElementById('servVerification.empName').value;
  		var fromDate =document.getElementById('servVerification.fromDate').value;
  		var toDate =document.getElementById('servVerification.toDate').value;
  		var days =document.getElementById('servVerification.qualSerDays').value;
		var months=document.getElementById('servVerification.qualSerMonth').value; 	
		
		var year=document.getElementById('servVerification.qualSerYear').value; 
		  		
  		if(empName == ""){
  			alert("Please Select Employee");
  			return false;
  		}
  		
  		
  		if(fromDate == ""){
  			alert("Please Enter From Date ");
  			return false;
  		}
  	var checkFromDate = validateDate(fromDate);
	
	if(!checkFromDate){
		alert (" Please enter valid From Date !\nDate should be DD-MM-YYYY format");
		return false;
	}
  		
  		if(toDate == ""){
  			alert("Please Enter To Date");
  			return false;
  		}
  		
  		var checkToDate = validateDate(toDate);
	
	if(!checkToDate){
		alert (" Please enter valid To Date !");
		return false;
	}
	
	var checkDate = datedifference();	
	if (!checkDate){
		alert("To Date must be greater than From Date");
		return false;
	}
  		
  		if(months >12){
  			alert("Please  Enter valid months ");
  			return false;
  		}	
  		if(days >30){
  			alert("Please Enter valid days ");
  			return false;
  		}	
  		
  		
  		
  		if(!(year=="")){
			var iChars = "0123456789";
		  		for (var i = 0; i < year.length; i++) {			
			  	if (!(iChars.indexOf(year.charAt(i)) != -1)) {
				  	alert ("Years should be numeric value.");
				  	return false;
  					}
  				}
		}					
  		
  		
  		return true ;
  		
  	}
   
   
   
   
   
   
   
   function formValidate(){
  		var empName =document.getElementById('servVerification.empName').value;
  		var fromDate =document.getElementById('servVerification.fromDate').value;
  		var toDate =document.getElementById('servVerification.toDate').value;
  		var days =document.getElementById('servVerification.qualSerDays').value;
		var months=document.getElementById('servVerification.qualSerMonth').value; 	
		var paraID=document.getElementById('servVerification.serviceID').value; 
		var year=document.getElementById('servVerification.qualSerYear').value; 
	
		
		if(!(paraID == "")){
  			alert("You can't Insert Please Update !");
  			return false;
  		}
			
		
  		
  		if(empName == ""){
  			alert("Please Select Employee !");
  			return false;
  		}
  		
  		
  		if(fromDate == ""){
  			alert("Please Enter From Date ");
  			return false;
  		}
  	var checkFromDate = validateDate(fromDate);
	
	if(!checkFromDate){
		alert (" Please enter valid From Date !\nDate should be DD-MM-YYYY format");
		return false;
	}
  		
  		if(toDate == ""){
  			alert("Please Enter To Date");
  			return false;
  		}
  		
  		var checkToDate = validateDate(toDate);
	
	if(!checkToDate){
		alert (" Please enter valid To Date !");
		return false;
	}
	
	var checkDate = datedifference();	
	if (!checkDate){
		alert("To Date must be greater than From Date");
		return false;
	}
  		
  		if(months >12){
  			alert("Please  Enter valid months ");
  			return false;
  		}	
  		if(days >30){
  			alert("Please Enter valid days ");
  			return false;
  		}	
  		
  		
  		
  		if(!(year=="")){
			var iChars = "0123456789";
		  		for (var i = 0; i < year.length; i++) {			
			  	if (!(iChars.indexOf(year.charAt(i)) != -1)) {
				  	alert ("Year should be numeric value.");
				  	return false;
  					}
  				}
		}					
  		
  		
  		return true ;
  		
  	}
  	
  	  function validateDate(fld) {
    var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[-](0?[13578]|1[02])[-]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[-](0?[13456789]|1[012])[-]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[-]0?2[-]((1[6-9]|[2-9]\d)?\d{2}))|(29[-]0?2[-]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
        	
    if (!((fld.match(RegExPattern)) && (fld!=''))){
        
        return false;
    
    }
    return true;
   }
   
   function datedifference() { 
	var fromDate = document.getElementById('servVerification.fromDate').value;
	var toDate = document.getElementById('servVerification.toDate').value;

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

		function formVal(){
		var empName =document.getElementById('servVerification.empName').value;
  		var fromDate =document.getElementById('servVerification.fromDate').value;
  		var toDate =document.getElementById('servVerification.toDate').value;
  		var days =document.getElementById('servVerification.qualSerDays').value;
		var months=document.getElementById('servVerification.qualSerMonth').value; 	
		var paraID=document.getElementById('servVerification.serviceID').value; 	
		if(paraID == ""){
  			alert("You can't Update  Please Insert ");
  			return false;
  		}
  		
  		if(empName == ""){
  			alert("Please Select Employee !");
  			return false;
  		}
  		
  		
  		if(fromDate == ""){
  			alert("Please Enter From Date ");
  			return false;
  		}
  	var checkFromDate = validateDate(fromDate);
	
	if(!checkFromDate){
		alert (" Please enter valid From Date !\nDate should be DD-MM-YYYY format");
		return false;
	}
  		
  		if(toDate == ""){
  			alert("Please Enter To Date");
  			return false;
  		}
  		
  		var checkToDate = validateDate(toDate);
	
	if(!checkToDate){
		alert (" Please enter valid To Date !");
		return false;
	}
	
	var checkDate = datedifference();	
	if (!checkDate){
		alert("To Date must be greater than From Date");
		return false;
	}
  		
  		if(months >12){
  			alert("Please  Enter valid months ");
  			return false;
  		}	
  		if(days >30){
  			alert("Please Enter valid days ");
  			return false;
  		}	
  		
  		return true ;
  		
		
		}
		
		
		
		
		
	function alphabetsonly(myfield)
{
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz .").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}

function numbersonly(myfield)
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
   
   </script>
