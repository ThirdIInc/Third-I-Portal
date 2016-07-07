<!-- @author: REEBA JOSEPH 22 NOVEMBER 2010 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<%@ include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>
<s:form action="LTCCalc" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<div id="msgDiv" style='position: absolute; z-index: 3; background-color: red; 100 px; height: 50px; visibility: hidden; top: 50px; left: 250px;'></div>
	<div id="confirmationDiv" style='position: absolute; z-index: 3; 100 px; height: 150px; visibility: hidden; top: 200px; left: 150px;'></div>
	<div align="center" id="overlay" style="z-index: 3; position: absolute; width: 1000px; height: 500px; margin: 0px; left: 0; top: 0; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid : DXImageTransform . Microsoft . alpha(opacity = 15); -moz-opacity: .1; opacity: .1;"></div>

	<div id="progressBar" style="z-index: 3; position: absolute; width: 770px;">
	<table width="100%">
		<tr>
			<td height="200"></td>
		</tr>
		<tr>
			<td align="center"><img src="../pages/images/ajax-loader.gif">
			</td>
		</tr>
		<tr>
			<td align="center"><span
				style="color: red; font-size: 16px; font-weight: bold; z-index: 1000px;">Processing...</span>
			</td>
		</tr>
		<tr>
			<td align="center"><span
				style="color: red; font-size: 16px; font-weight: bold; z-index: 1000px;">Please
			do not close the browser and do not click anywhere</span></td>
		</tr>
	</table>
	</div>

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">LTC
					Calculation</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>

		<s:if test="editFlag" ></s:if><s:else>
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="100%"><b>Process LTC</b>
					<table width="100%">
						<tr>
							<td width="300" align="right"><label id="month1"
								name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label>
							:<font color="red">*</font></td>
							<td width="50">
							<s:select name="month" headerKey="0"
								headerValue="--Select--" title="Select a month"
								list="#{'1':'January', '2':'Febuary', '3':'March', '4':'April', '5':'May', '6':'June', '7':'July', 
										'8':'August', '9':'September', '10':'October', '11':'November', '12':'December'}" />
							</td>
							<td width="60" align="right"><label id="year1" name="year"
								ondblclick="callShowDiv(this);"><%=label.get("year")%></label> :<font
								color="red">*</font></td>
							<td colspan="1"><s:textfield name="year" size="5"
								maxlength="4" cssStyle="text-align: right"
								title="Enter the year" onkeypress="return numbersOnly(event);"
								onblur="return checkYear('paraFrm_year', 'year');" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td width="300" align="right">
								<label id="division1" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :
								<font color="red">*</font>
							</td>
							<td width="20"><s:textfield name="divisionName"
								readonly="true" size="35" cssStyle="background-color: #F2F2F2;" /></td>
							<td><img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="middle" width="18"
								title="Select the division" id="ctrlHide"
								onclick="callsF9(500,325,'LTCCalc_f9Division.action');"></td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</s:else>
		<s:hidden name="monthCode" /><s:hidden name="status" />
		<s:hidden name="divisionId" /><s:hidden name="ltcCode"/>
		
		<s:if test="editFlag" >
		
		<tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					
					<td nowrap="nowrap" width="15%"><label id="month2"
								name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font
						color="red"> *</font> :</td>
					<td width="20%">
					<s:property value="month" /><s:hidden name="month" />
					<td width="15%"><label id="year2" name="year"
								ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font
						color="red"> *</font> :</td>
					<td width="20%"><s:property value="year" /><s:hidden name="year" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<s:hidden name="divCode" />
				<s:hidden name="branchViewId" />
				<tr>
					<td width="15%"><label id="division2" name="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:<font color="red">*</font></td>
					<td width="35%"><s:textfield name="divisionName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" /></td>
					<td width="15%"><label id="branch" name="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td width="35%"  id="ctrlShow"><s:textfield name="branchViewName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="middle" width="18"
						title="Select the branch"
						onclick="callsF9(500,325,'LTCCalc_f9BranchView.action');"></td>
				</tr>
				<s:hidden name="departmentViewId" />
				<s:hidden name="employeeTypeViewId" />
				<tr>
					<td width="15%"><label id="department" name="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td width="35%" id="ctrlShow"><s:textfield name="departmentViewName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="middle" width="18"
						title="Select the department"
						onclick="callsF9(500,325,'LTCCalc_f9DepartmentView.action');"></td>
					<td width="15%"><label id="employee.type" name="employee.type"
						ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
					:</td>
					<td width="35%" id="ctrlShow"><s:textfield name="employeeTypeViewName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="middle" width="18"
						title="Select the type of an employee"
						onclick="callsF9(500,325,'LTCCalc_f9EmployeeTypeView.action');">
					</td>
				</tr>
				<s:hidden name="payBillViewId" />
				<s:hidden name="empViewId" />
				<s:hidden name="empStatusView" />
				<s:hidden name="empTokenView" />
				<tr>
					<td width="15%"><label id="pay.bill" name="pay.bill"
						ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
					:</td>
					<td width="35%" id="ctrlShow"><s:textfield name="payBillViewName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="middle" width="18"
						title="Select the pay bill group"
						onclick="callsF9(500,325,'LTCCalc_f9PayBillView.action');">
					</td>
					<td width="15%"><label id="employee" name="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td width="35%" id="ctrlShow"><s:textfield name="empViewName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="middle" width="18"
						title="Select the employee"
						onclick="callsF9(500,325,'LTCCalc_f9employeeView.action');">
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="4" align="center" id="ctrlShow"><input type="button"
						value="Show Records" class="token" title="Show"
						onclick="return callEditableLTC();" /> <input type="button"
						value="Reset Records" class="reset" title="Reset"
						onclick="return callReset();" /></td>
				</tr>
			</table>
			</td>
		</tr>

		</s:if>
		
		
		<%
			int totalPage = 0;
			int pageNo = 0;
		%>
		<s:if test="flag">
			<tr>
				<td id="ctrlShow" width="100%" align="right"><b>Page:</b> <%
 		try {
		 		totalPage = (Integer) request.getAttribute("totalPage");
		 		pageNo = (Integer) request.getAttribute("pageNo");
		
		 	} catch (Exception e) {
		
		 	}
		 %> <input type="hidden" name="totalPage" id="totalPage"
					value="<%=totalPage%>"> <a href="#"
					onclick="callPage('1', 'F');"> <img title="First Page"
					src="../pages/common/img/first.gif" width="10" height="10"
					class="iconImage" /> </a>&nbsp; <a href="#"
					onclick="callPage('P', 'P');"> <img title="Previous Page"
					src="../pages/common/img/previous.gif" width="10" height="10"
					class="iconImage" /> </a> <input type="text" name="pageNoField"
					id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
					onkeypress="callPageText(event);return numbersOnly();" /> of <%=totalPage%>
				<a href="#" onclick="callPage('N', 'N')"> <img
					title="Next Page" src="../pages/common/img/next.gif"
					class="iconImage" /> </a>&nbsp; <a href="#"
					onclick="callPage('<%=totalPage%>', 'L');"> <img
					title="Last Page" src="../pages/common/img/last.gif" width="10"
					height="10" class="iconImage" /> </a></td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td><s:hidden name="myGridPage" />
						<table width="98%" border="0" align="center" cellpadding="2"
							cellspacing="2">
							<tr>
								<td width='5%' class="formth" align="center"><label
									class="set" name="serial.no" id="serial.no3"
									ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
								</td>
								<td width='20%' class="formth" align="center"><label
									class="set" name="employee.id" id="employee.id3"
									ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label>
								</td>
								<td width='25%' class="formth" align="center"><label
									class="set" name="employee" id="employee3"
									ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
								</td>

								<td width='15%' class="formth" align="center"><label
									class="set" name="attn.days" id="attn.days"
									ondblclick="callShowDiv(this);"><%=label.get("attn.days")%></label>
								</td>
								<td width='15%' class="formth" align="center"><label
									class="set" name="trvl.days" id="trvl.days"
									ondblclick="callShowDiv(this);"><%=label.get("trvl.days")%></label>
								</td>
								<td width='15%' class="formth" align="center"><label
									class="set" name="ltc.days" id="ltc.days"
									ondblclick="callShowDiv(this);"><%=label.get("ltc.days")%></label>
								</td>
								<td width='25%' class="formth" align="center"><label
									class="set" name="ltc.amount" id="ltc.amount"
									ondblclick="callShowDiv(this);"><%=label.get("ltc.amount")%></label>
								</td>

							</tr>
							<%
								int i = 1;
								int k = 0;
								int cn = 0;
							%>
							<s:if test="noData">
								<tr>
									<td width="100%" colspan="14" align="center"><font
										color="red">No data to display</font></td>
								</tr>
							</s:if>
							<s:else>
								<s:iterator value="empList">
									<%
									k = i;
									%>

									<tr>
										<td width="5%" align="center" class="sortableTD">
										<%
										++cn;
										%><%=(10 * (pageNo - 1)) + k%></td>
										<td width="20%" class="sortableTD"><s:hidden name="empId"
											id='<%="empId"+k%>' /><s:property value="empToken" /></td>
										<td width="25%" class="sortableTD"><s:property
											value="empName" /></td>

										
										<td width="15%" align="center" class="sortableTD"><s:textfield
											name="attnDays" id='<%="attnDays_"+k%>'
											onkeypress="return numbersOnly();" 
											size="4" cssStyle="text-align: right" /></td>
										<td width="15%" align="center" class="sortableTD"><s:textfield
											name="trvlDays" id='<%="trvlDays_"+k%>'
											 size="4" onkeypress="return numbersOnly();" /></td>
										<td width="15%" align="center" class="sortableTD"><s:textfield
											name="ltcDays" id='<%="ltcDays_"+k%>'
											onkeypress="return numbersOnly();" 
											size="4" cssStyle="text-align: right" /></td>
										<td width="25%" align="center" class="sortableTD"><s:textfield
											name="ltcAmount" id='<%="ltcAmount_"+k%>'
											 size="4" onkeypress="return numbersWithDot();" 
											cssStyle="background-color: #F2F2F2; text-align: right;" /></td>

									</tr>
									<%
										k++;
										i++;
									%>
								</s:iterator>
								<%
								i = 1;
								%>
							</s:else>
							<input type='hidden' name='count' id='count' value='<%=k%>' />
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
							
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

	</table>
</s:form>

<script>
callOnload();

function callOnload(){
	document.getElementById("overlay").style.visibility = "hidden";
	document.getElementById("overlay").style.display = "none";
	document.getElementById("progressBar").style.visibility = "hidden";
	document.getElementById("progressBar").style.display = "none";
}

function processFun(){
	try{
	if(document.getElementById('paraFrm_month').selectedIndex == 0) {
		alert("Please select the " + document.getElementById('month1').innerHTML.toLowerCase());
		document.getElementById('paraFrm_month').focus();
		return false;
	}
	if(document.getElementById('paraFrm_year').value == "") {
		alert("Please enter the " + document.getElementById('year1').innerHTML.toLowerCase());
		document.getElementById('paraFrm_year').focus();
		return false;
	}
	if(document.getElementById('paraFrm_divisionId').value == "") {
		alert("Please select the " + document.getElementById('division1').innerHTML.toLowerCase());
		return false;
	}
	document.getElementById("overlay").style.visibility = "";
	document.getElementById("overlay").style.display = "";
	document.getElementById("progressBar").style.visibility = "";
	document.getElementById("progressBar").style.display = "";
	
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'LTCCalc_process.action';
	document.getElementById('paraFrm').submit();
	}catch(e){
		//alert(e);
	}
}

function resetFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'LTCCalc_reset.action';
	document.getElementById('paraFrm').submit();
}

function backtolistFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'LTCCalc_input.action';
	document.getElementById('paraFrm').submit();
}

function backFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'LTCCalc_callForEdit.action';
	document.getElementById('paraFrm').submit();
}

function callEditableLTC(){
	document.getElementById('paraFrm').action='LTCCalc_viewRecords.action';
	document.getElementById('paraFrm').submit();
}
	
function callReset(){
	document.getElementById("paraFrm_branchViewId").value = "";
	document.getElementById("paraFrm_branchViewName").value = "";
	document.getElementById("paraFrm_departmentViewId").value = "";
	document.getElementById("paraFrm_departmentViewName").value = "";
	document.getElementById("paraFrm_employeeTypeViewId").value = "";
	document.getElementById("paraFrm_employeeTypeViewName").value = "";
	document.getElementById("paraFrm_payBillViewId").value = "";
	document.getElementById("paraFrm_payBillViewName").value = "";
	document.getElementById("paraFrm_empViewId").value = "";
	document.getElementById("paraFrm_empViewName").value = "";
	document.getElementById("paraFrm_empTokenView").value = "";
	document.getElementById("paraFrm_empStatusView").value = "";
}

function callPageText(id){
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = id.which
	clear();
			
	if(key==13){ 
		pageNo =document.getElementById('pageNoField').value;		
	 	totalPage =document.getElementById('totalPage').value;		
		document.getElementById('paraFrm_myGridPage').value=pageNo;
     
       	if(pageNo==""){
	       	alert("Please Enter Page Number.");
	       	document.getElementById('pageNoField').focus();
			return false;
       	}
   		if(Number(pageNo)<=0){
	     	alert("Page number should be greater than zero.");
	     	document.getElementById('pageNoField').focus();
		 	return false;
    	}
    	if(Number(totalPage)<Number(pageNo)){
     		alert("Page number should not be greater than "+totalPage+".");
     		document.getElementById('pageNoField').focus();
	 		return false;
    	}
   
		displayConfirmDiv();
		document.getElementById('confirmationDiv').style.display='block';
	}
}

function callPage(id,pageImg){
 	pageNo =document.getElementById('pageNoField').value;	
 	totalPage =document.getElementById('totalPage').value;	 
	if(pageNo==""){
	    alert("Please Enter Page Number.");
	    document.getElementById('pageNoField').focus();
	 	return false;
	}
	if(Number(pageNo)<=0){
   		alert("Page number should be greater than zero.");
   		document.getElementById('pageNoField').focus();
		return false;
	}
	if(Number(totalPage)<Number(pageNo)){
	    alert("Page number should not be greater than "+totalPage+".");
	    document.getElementById('pageNoField').focus();
		return false;
    }
	if(pageImg=="F"){
	    if(pageNo=="1"){
		    alert("This is first page.");
		    document.getElementById('pageNoField').focus();
		    return false;
	    } 
    }  
    if(pageImg=="L"){
		if(eval(pageNo)==eval(totalPage)){
			alert("This is last page.");
	        document.getElementById('pageNoField').focus();
	        return false;
		} 
	} 
    if(pageImg=="P"){
		if(pageNo=="1"){
			alert("There is no previous page.");
	        document.getElementById('pageNoField').focus();
	        return false;
		}  
	}  
    if(pageImg=="N"){
		if(Number(pageNo)==Number(totalPage)){
			alert("There is no next page.");
	        document.getElementById('pageNoField').focus();
	        return false;
		}  
	}  
     
	var p=document.getElementById('pageNoField').value;
    if(id=='P'){
		id=eval(p)-1;
	}
	if(id=='N'){
		id=eval(p)+1;
	} 
		
	document.getElementById('paraFrm_myGridPage').value=id;
	displayConfirmDiv();
	document.getElementById('confirmationDiv').style.display='block';
}

function displayConfirmDiv(){
	document.getElementById("confirmationDiv").style.visibility = 'visible';
	document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=100 border=0 class=formbg>'
		+'<tr><td class="txt"><b><center>Please select anyone of the option given below</td></tr>'
		+'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>'
		+'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithoutSave();"/>'
		+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
	document.getElementById("overlay").style.display = "block";
	///JSFX_FloatTopDiv();
}

function proceedWithSave(){
	try{
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById('confirmationDiv').style.display='none';
		document.getElementById("overlay").style.display = "none";
		enableBlockDiv();
		document.getElementById('paraFrm').action="LTCCalc_saveLTC.action";
		document.getElementById('paraFrm').submit();
	}
	catch(e){
		//alert(e);
	}
}
function proceedWithoutSave(){
	try{
		enableBlockDiv();
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById('confirmationDiv').style.display='none';
		document.getElementById("overlay").style.display = "none";
		document.getElementById('paraFrm').action="LTCCalc_callPaging.action";
		document.getElementById('paraFrm').submit();
	}
	catch(e){
		//alert(e);
	}
}
function cancel(){
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
	document.getElementById("overlay").style.display = "none";
}

function enableBlockDiv(){
	try{
		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
	}
	catch(e){
	}
 }

function saveFun(){
 	var status = document.getElementById('paraFrm_status').value;
  	//var cc = document.getElementById('count').value;
 	if(status == 'LTC_FINAL'){
 		alert('LTC is already locked.');
 	}else{
 		//if(cc != 0){
			document.getElementById('paraFrm').action='LTCCalc_saveLTC.action';
			document.getElementById('paraFrm').submit();
 		//}else
 		//	alert('No employee exists.')
 	}	
}

function lockFun(){
 	var status = document.getElementById('paraFrm_status').value;
  	//var cc = document.getElementById('count').value;
 	if(status == 'LTC_FINAL'){
 		alert('LTC is already locked.');
 	}else{
 		//if(cc != 0){
 			if(status == ''){
 				alert('Please save LTC first.');
 				return false;
			}
 			con=confirm('Are you sure!\nYou want to lock the LTC?');
		 	if(con){
				document.getElementById('paraFrm').action="LTCCalc_lockLTC.action";
				document.getElementById('paraFrm').submit();
			}
	 		
 		//}else
 			//alert('No employee exists.')
 	}	
}

function dropandprocessFun(){
	var status = document.getElementById('paraFrm_status').value;
  	//var cc = document.getElementById('count').value;
 	if(status == 'LTC_FINAL'){
 		alert('Pension is already locked.');
 	}else{
 		//if(cc != 0){
	 			con=confirm('Do you really want to re-Process LTC?');
			 	if(con){
					document.getElementById('paraFrm').action="LTCCalc_reProcessLTC.action";
					document.getElementById('paraFrm').submit();
				}
	 		
 		//}else
 			//alert('No employee exists.')
 	}

}

function unlockFun() {
	doAuthorisation('7', 'Salary', 'U');
}

function doUnlock() {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'LTCCalc_unLockLTC.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "main";
}

function reportFun(){
	var monthCode = document.getElementById('paraFrm_monthCode').value;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'LTCReport_generateReport.action?month='+monthCode;
	document.getElementById('paraFrm').submit();
}

</script>