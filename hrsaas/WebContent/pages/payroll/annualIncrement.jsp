<!--Mangesh Jadhav--><!--October 12, 2010-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp" %>
<%@ include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>

<s:form action="AnnualIncrement" name="AnnualIncrement" validate="true" id="paraFrm" target="main" theme="simple">
	<div id="msgDiv" style='position: absolute; z-index: 3; background-color: red; 100 px; height: 50px; visibility: hidden; top: 50px; left: 250px;'></div>
<div id="confirmationDiv" style='position: absolute; z-index: 3; 100 px; height: 150px; visibility: hidden; top: 200px; left: 150px;'></div>
<div align="center" id="overlay" style="z-index: 3; position: absolute; width: 776px; height: 450px; margin: 0px; left: 0; top: 0; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid : DXImageTransform . Microsoft . alpha(opacity = 15); -moz-opacity: .1; opacity: .1;"></div>

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
	<table width="100%" class="formbg" align="right">
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">Annual Increment Process</strong></td>
						<td width="4%" valign="middle" align="right" class="txt">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<s:if test="showFlag">
								<input type="button" value="Drop & Process" class="token" 
								title="Delete & Process" onclick="return reProcessIncrement()" />
								<input type="button" value="Save" class="save" 
								title="Save Increment" onclick="return saveIncrement()" />									
							</s:if><s:else>
							<input type="button" value="Process" class="token" title="Process" 
							onclick="return processFun();" /></s:else>
							<input type="button" value="Back" class="back" title="Return to main page" 
							onclick="return backFun();" />
							<input type="button" value="Lock" class="lock" title="Lock" 
							onclick="return lockFun();" />
							
						</td>
						<td align="right"><font color="red">*</font> Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr><s:hidden name="incrementCode" />
						<s:hidden name="incrementStatus" />
						<s:hidden name="monthView"/>
						<s:hidden name="extraWorkFlag"/>
						<td width="100%"><b>Process Increment</b>
							<table width="100%">
								<tr>
									<td width="300" align="right">
										<label id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label> :<font color="red">*</font>
									</td>
									<td width="50">
										<s:if test="showFlag"><s:property value="monthView"/><s:hidden name="month"/></s:if><s:else><s:select name="month" headerKey="0" headerValue="--Select--" title="Select a month"
										list="#{'1':'January', '2':'Febuary', '3':'March', '4':'April', '5':'May', '6':'June', '7':'July', 
										'8':'August', '9':'September', '10':'October', '11':'November', '12':'December'}" /></s:else>
									</td>
									<td width="60" align="right">
										<label id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> :<font color="red">*</font>
									</td>
									<td colspan="1">
									<s:if test="showFlag"><s:property value="year"/><s:hidden name="year"/></s:if>
										<s:else><s:textfield name="year" size="5" maxlength="4" cssStyle="text-align: right" title="Enter the year"
										onkeypress="return numbersOnly(event);" onblur="return checkYear('paraFrm_year', 'year');" /></s:else>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="100%">
							<table width="100%">
								<s:hidden name="divisionId" />
								
									<tr>
										<td width="300" align="right">
											<label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<font color="red">*</font>
										</td>
										<s:if test="showFlag">
										<td colspan="3">
											<s:property value="divisionName" /><s:hidden name ="divisionName"/>
										</td>
										</s:if>
										<s:else>
										<td width="20" colspan="3">
										<s:textfield name="divisionName" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the division" 
											onclick="callsF9(500,325,'AnnualIncrement_f9Division.action');">
										</td>
										</s:else>
										
									</tr>
								<s:hidden name="branchId" />
									<tr>
										<td align="right">
											<label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :
										</td>
										
										<s:if test="showFlag"><td width="20%">
											<s:property value="branchName"/><s:hidden name="branchName"></s:hidden>
											
										</td>
										<td width="300" align="right">
											<label id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :
										</td>
										<td width="20%">
										<s:property value="departmentName"/><s:hidden name="departmentName"></s:hidden>
										</td>
										</s:if>
										<s:else><td width="20%">
											<s:textfield name="branchName" readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
											
										</td>
										<td>
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the branch" 
											onclick="callsF9(500,325,'AnnualIncrement_f9Branch.action');">
										</td>
										
										<td width="300" align="right">
											<label id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :
										</td>
										<td width="20">
											<s:textfield name="departmentName" readonly="true" size="35" 
											cssStyle="background-color: #F2F2F2;" />
										</td>
										<td>
											<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
											align="middle" width="18" title="Select the department" 
											onclick="callsF9(500,325,'AnnualIncrement_f9Department.action');">
										</td>
										</s:else>
									</tr>
								
								<s:hidden name="departmentId" />
								
								
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr><s:hidden name="showFlag" />
		<s:if test="showFlag">
		<tr>
			<td width="100%">
				<s:hidden name="hdPage" id="hdPage" />
				<s:hidden name="fromTotRec" id="fromTotRec" />
				<s:hidden name="toTotRec" id="toTotRec" />
				<s:hidden name="hdProcess" id="hdProcess" value="%{hdProcess}" />
					<table width="100%" class="formbg">
						<tr>
							<td colspan="4">
							<%
								Object[][] rows = (Object[][]) request.getAttribute("rows");
										Object[][] c = (Object[][]) request.getAttribute("creditLength");
										int totalPage = (Integer) request.getAttribute("totalPage");
										int pageNo = (Integer) request.getAttribute("PageNo");
										int index = (Integer) request.getAttribute("index");
							%>
							<table width="98%">
								<tr>
									<td width="50%" align="center">
									<%
										if (pageNo != 1) {
									%> <a href="#" onclick="callPage('1');"> <img src="../pages/common/img/first.gif" width="10" height="10"
												class="iconImage" /> </a>&nbsp; <a href="#" onclick="callPage('P')">
										<img src="../pages/common/img/previous.gif" width="10" height="10"	class="iconImage" /> </a> <%
			 						}
								 	if (totalPage <= 5) {
								 				if (totalPage == 1) {
								 					%> <b><u><%=totalPage%></u></b> <%
								 				} else {
								 					for (int z = 1; z <= totalPage; z++) {
								 						if (pageNo == z) {
															 %> <b><u><%=z%></u></b> <%
								 						} else {
															 %> <a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
								 						}
								 					}
								 				}
								 	} else {
								 				if (pageNo == totalPage - 1 || pageNo == totalPage) {
								 					for (int z = pageNo - 2; z <= totalPage; z++) {
								 						if (pageNo == z) {
								 							%> <b><u><%=z%></u></b> <%
								 						} else {
															 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
								 						}
								 					}
								 				} else if (pageNo <= 3) {
								 					for (int z = 1; z <= 5; z++) {
								 						if (pageNo == z) {
															 %> <b><u><%=z%></u></b> <%
								 						} else {
								 							 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
								 						}
								 					}
								 				} else if (pageNo > 3) {
								 					for (int z = pageNo - 2; z <= pageNo + 2; z++) {
								 						if (pageNo == z) {
								 							%> <b><u><%=z%></u></b> <%
								 						} else {
								 							%> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
								 						}
								 					}
								 				}
								 			}
								 			if (!(pageNo == totalPage)) {
								 				if (totalPage == 0) {
								 				} else {
													 %> ....<%=totalPage%> <a href="#" onclick="callPage('N')"> <img src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; 
													 <a	href="#" onclick="callPage('<%=totalPage%>');"> <img src="../pages/common/img/last.gif" width="10" height="10" class="iconImage" /> </a> <%
								 				}
								 			}
								 		%>
									 	</td>
			
										<td align="right">
												<!--  <input type="button" class="token" onclick="callOnhold()" value="OnHold" /> 
												<input type="button" class="token" onclick="removeOnHold()" value="Remove OnHold" />
												<input type="button" class="edit" onclick="checkRecordRecal();"	value="   Recalculate" />-->
										</td>
								</tr>
							</table>
							<table id="thetable" width="100%">
								<tr>
									<td width="10%"><input class="tokenPay" readonly="readonly"	style="text-align: center; border: none; margin: 1px" type="text" size="10" value="Employee Id" /></td>
									<td width="15%"><input class="tokenPay" readonly="readonly"	style="text-align: center; border: none; margin: 1px;" type="text" size="24" value="Employee Name" /></td>
									<td width="10%"><input class="tokenPay" readonly="readonly"	style="text-align: center; border: none; margin: 1px;" type="text" size="10" value="Old Basic" /></td>
									<s:iterator value="creditHeader">
										<td><input class="tokenPay" type="text" size="4" readonly="readonly" style="text-align: center; border: none; margin: 1px" value="<s:property value="creditName" />" /></td>
									</s:iterator>
									<td width="10%"><input class="tokenPay" type="text" size="10" style="text-align: center; border: none; margin: 1px" value="Total Credit" /></td>
									<!--<td>  <input class="classCheck" type="checkbox" name="selectId" id="selectId" onclick="return selectAll();" /> </td>-->
								</tr>
								<%
									try {
											int colVal = rows[0].length - 4;
											int i = 0;
											for (int k = 0; k < index; k++) {
																
															//System.out.println("salary days"+ rows[k][rows[0].length - 3] + "------k="+ k);
															//System.out.println("attendance days"+ rows[k][rows[0].length - 1] + "------k="+ k);
											%>
											
													<tr>
														<td width="10%">
															<input type="hidden" name="emp_id" value="<%=rows[k][0] %>"	id="<%=rows[k][0]%>"> 
															<input style="background-color: #C0EDFE" type="text" readonly="readonly" size="8" name="tokenNo" value="<%=rows[k][1] %>"	id='<%=rows[k][0]+"empToken"%>'></td>
														<td width="15%"><input style="background-color: #C0EDFE" type="text" size="24" readonly="readonly" name="empName" value="<%=rows[k][2] %>" id='<%=rows[k][0]+"empName"%>'></td>
														<td width="8%" ><input  style="background-color: #C0EDFE" type="text" size="4" readonly="readonly" name="oldBasic" value="<%=rows[k][3] %>" id='<%=rows[k][0]+"oldBasic"%>'></td>
														<%
															i = 4;
														%>
														<s:iterator value="creditHeader">
															<td width="8%" ><input style="text-align: right" type="text" size="4" name="<%=k%>" value="<%=String.valueOf(rows[k][i]) %>"
																id="<%=rows[k][0]+"c"+i%>" onkeypress="return numbersWithDot();" onkeyup="sumCredits(<%=c.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>)">
															</td>
														<%
															i++;
														%>
														</s:iterator>
														<td width="10%"><input style="text-align: right"  style="background-color: #C0EDFE" type="text" size="6" readonly="readonly" id="<%=rows[k][0]+"c"+i%>"	name="totalCredit<%=k%>" value="<%=rows[k][i] %>"></td>
														<%
															int dStart = i;
														%>
													</tr>
												
											
												<script>
													eCode[<%=k%>] = "<%=String.valueOf(rows[k][0])%>";
												</script>
								<%
												}
										} catch (Exception e) {
			
										}
								%>
							</table>
							</td>
						</tr> 
					</table>
					
			</td>
		</tr>
	</s:if>
		
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
							<td>
							<s:if test="showFlag">
								<input type="button" value="Drop & Process" class="token" 
								title="Delete & Process" onclick="return reProcessIncrement()" />
								<input type="button" value="Save" class="save" 
								title="Save Increment" onclick="return saveIncrement()" />									
							</s:if><s:else>
							<input type="button" value="Process" class="token" title="Process" 
							onclick="return processFun();" /></s:else>
							<input type="button" value="Back" class="back" title="Return to main page" 
							onclick="return backFun();" />
							
						</td>
						<td align="right"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
function callOnload(){
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
				document.getElementById("progressBar").style.visibility = "hidden";
				document.getElementById("progressBar").style.display = "none";
}
callOnload();

function backFun(){
			document.getElementById('paraFrm').action="AnnualIncrement_input.action";
			document.getElementById('paraFrm').submit();
}
function callPage(id,totalPage){
try{
	var con;
	var process=document.getElementById('hdProcess').value
 	var incrementStatus = document.getElementById("paraFrm_incrementStatus").value;
	if(id=='P'){
		var p=document.getElementById('hdPage').value;
		id=eval(p)-1;
	}
	if(id=='N'){
		var p=document.getElementById('hdPage').value;
		id=eval(p)+1;
	}
	document.getElementById('hdPage').value=id;
	if(process=="Pr"){
		if(incrementStatus=="SAL_FINAL" ||incrementStatus=="ATTN_UNLOCK"){
			document.getElementById('paraFrm').action="SalaryProcess_getEditableSalary.action";
			document.getElementById('paraFrm').submit();
		}else{
	 		//con=confirm("Do you want to save the page and proceed to the next page");
	 		document.getElementById("confirmationDiv").style.visibility = 'visible';
		 	document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=100 border=0 class=formbg>'
		 		+'<tr><td class="txt"><b><center>Please select anyone of the option given below</td></tr>'
		 		+'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>'
		 		+'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithoutSave();"/>'
		 		+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
		}
	}else{
		if(incrementStatus=="SAL_FINAL" || incrementStatus=="ATTN_UNLOCK"){
			document.getElementById('paraFrm').action="SalaryProcess_getEditableSalary.action";
			document.getElementById('paraFrm').submit();
		}else{
		 	//con=confirm("Do you want to save the page and proceed to the next page");
		 	document.getElementById("confirmationDiv").style.visibility = 'visible';
		 	document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=100 border=0 class=formbg>'
		 		+'<tr><td class="txt"><b><center>Please select anyone of the option given below</td></tr>'
		 		+'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>'
		 		+'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithoutSave();"/>'
		 	+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
		}
	}
	}catch(e){alert(e);}
}
	function processFun() {
	
		if(document.getElementById('paraFrm_month').selectedIndex == 0) {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_month').focus();
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		
				document.getElementById("overlay").style.visibility = "";
				document.getElementById("overlay").style.display = "";
				document.getElementById("progressBar").style.visibility = "";
				document.getElementById("progressBar").style.display = "";
				document.getElementById('paraFrm').action = "AnnualIncrement_processIncrement.action";
				document.getElementById('paraFrm').submit();
				
	}
	function cancel(){
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
}
	function uploadFile(fieldName) 
{
	var path="oo/<%=session.getAttribute("session_pool")%>/pay";
	window.open('../pages/common/uploadFile.jsp?path='+path+'&field='+""+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}
	function callReturntoList() {
		document.getElementById("paraFrm_ledgerCode").value = "";
		document.getElementById("paraFrm_month").value = "";
		document.getElementById("paraFrm_year").value = "";
		document.getElementById("paraFrm_employeeTypeName").value = "";
		document.getElementById("paraFrm_employeeTypeId").value = "";
		document.getElementById("paraFrm_payBillName").value = "";
		document.getElementById("paraFrm_payBillId").value = "";
		document.getElementById("paraFrm_departmentName").value = "";
		document.getElementById("paraFrm_departmentId").value = "";
		document.getElementById("paraFrm_branchName").value = "";
		document.getElementById("paraFrm_branchId").value = "";
		document.getElementById("paraFrm_divisionName").value = "";
		document.getElementById("paraFrm_divisionId").value = "";
		document.getElementById("paraFrm_incrementStatus").value = "";
	}
	
	function downloadTemplate(){
		document.getElementById('paraFrm').action="SalaryProcess_downloadTemplate.action";
		document.getElementById('paraFrm').submit();
	}
	


function saveIncrement(){
		if(document.getElementById('paraFrm_incrementStatus').value=="LOCK"){
			alert("Increment locked already");
			return false;
		}
			document.getElementById('paraFrm').action="AnnualIncrement_saveAfterRecalculate.action";
			document.getElementById('paraFrm').submit();
}

function checkFun(action){
 	 var incrementStatus = document.getElementById("paraFrm_ledgerStatus").value;
		 if(ledgerStatus=="SAL_FINAL"){
			alert("Salary is already Locked");
		}
		else if(ledgerStatus=="ATTN_UNLOCK"){
			alert("Attendance is unlocked");
		}
		else{
		if(action=='Lock'){
			 	con=confirm('Do you really want to lock the salary?');
			 	if(con){
			 	//enableBlockDiv();
				document.getElementById('paraFrm').action="SalaryProcess_lockSalary.action";
				document.getElementById('paraFrm').submit();
				}
			}else if(action=='Tax'){
				document.getElementById('paraFrm').action="SalaryProcess_recalculateTax.action";
				document.getElementById('paraFrm').submit();
			}
			
		}
 }
 
 function proceedWithSave(){
	try{
			document.getElementById("confirmationDiv").style.visibility = 'hidden';
			enableBlockDiv();
			document.getElementById('paraFrm').action="AnnualIncrement_saveAfterRecalculate.action";
			document.getElementById('paraFrm').submit();
		}catch(e){
			alert(e);
		}
}
function proceedWithoutSave(){
	try{
			enableBlockDiv();
			document.getElementById("confirmationDiv").style.visibility = 'hidden';
			document.getElementById('paraFrm').action="AnnualIncrement_callForEdit.action";
			document.getElementById('paraFrm').submit();
		}catch(e){
			alert(e);
		}
}
function enableBlockDiv(){
  		try{
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
			document.getElementById("progressBar").style.visibility = "visible";
			document.getElementById("progressBar").style.display = "block";   
		}catch(e){
		//alert(" enableBlockDiv -- "+e);
		}
}
function disableBlockDiv(){
  		try{
			document.getElementById("overlay").style.visibility = "hidden";
			document.getElementById("overlay").style.display = "none";
			document.getElementById("progressBar").style.visibility = "hidden";
			document.getElementById("progressBar").style.display = "none";   
			}catch(e){
			//alert(" disableBlockDiv -- "+e);
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
				document.getElementById("progressBar").style.visibility = "hidden";
				document.getElementById("progressBar").style.display = "none";  
			}
}

function reProcessIncrement(){
	if(document.getElementById('paraFrm_incrementStatus').value=="LOCK"){
			alert("Increment locked already");
			return false;
		}
		var con=confirm('Do you really want to re-Process the Increment?');
			 	if(!con){
			 	return false;
			 	}
			document.getElementById('paraFrm').action="AnnualIncrement_deleteAndReProcess.action";
			document.getElementById('paraFrm').submit();
}

function lockFun(){
	if(document.getElementById('paraFrm_incrementStatus').value=="LOCK"){
			alert("Increment already locked");
			return false;
		}
	var con=confirm('Locking the Increment will update the employee credits,Do you really want to lock the Increment?');
			 	if(!con){
			 	return false;
			 	}
			document.getElementById('paraFrm').action="AnnualIncrement_lockIncrement.action";
			document.getElementById('paraFrm').submit();
}








</script>