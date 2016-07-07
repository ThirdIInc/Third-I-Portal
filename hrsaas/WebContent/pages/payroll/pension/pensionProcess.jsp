<!-- Developed by @Prakash -->
<!-- Modified by REEBA -->
<!-- Date:30-09-2009  -->
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="PensionProcess" validate="true" id="paraFrm" theme="simple">

<div align="center" id="overlay"
		style="z-index: 3; position: absolute; width: 790px; height: 920px; margin: 0px; left: 0; top: 100; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid :   DXImageTransform .   Microsoft .   alpha(opacity =   15); -moz-opacity: .1; opacity: .1; display: none;">
	</div>
	<div id="confirmationDiv"
	style='position: absolute; z-index: 3; 100 px; height: 150px; visibility: hidden; top: 200px; left: 100px;'></div>
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Pension
					Process</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="flag" />
		<s:hidden name="penProcess.status" />
		<s:hidden name="penProcess.ledgerCode" />
		<s:hidden name="penProcess.monthView" />
		<s:hidden name="filterFlag" />
		<s:hidden name="myPage" />

		<tr>
			<td>
			<table width="100%" border="0">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /> <!--<input
						type="button" class="search" value="    Search "
						onclick="callsF9(500,325,'PensionProcess_f9ShowRecord.action');" />
					<s:if test="flag">
						<input type="button" class="save" theme="simple"
							onclick="return checkSaveOrLock('s');" value="    Save" />

						<input type="button" class="lock" theme="simple"
							onclick="return checkSaveOrLock('l');" value="    Lock" />

						<input type="button" class="token" theme="simple"
							onclick="return dropProcess();" value="    Drop & Process" />
					</s:if> <s:else>

						<input type="button" class="token" theme="simple"
							onclick="return check();" value="Process" />
					</s:else> <s:submit action="PensionProcess_reset" value="    Reset"
						cssClass="reset" />
						--></td>
					<td align="right"><font color="red">*</font>Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>



		<s:if test="%{filterFlag}"></s:if><s:else>
		<tr>
			<td>


			<table width="100%" border="0" class="formbg">
				<tr>
					<td width="10%" nowrap="nowrap"><label class="set"
						id="pension.month" name="pension.month"
						ondblclick="callShowDiv(this);"><%=label.get("pension.month")%></label>
					:<font color="red"> *</font></td>
					<td width="8%"></td>
					<td width="86%"> <s:if test="flag">
						<!--<s:property value="penProcess.monthView" />
						<s:hidden name="penProcess.month" />
					--></s:if> <s:else>
						<s:select name="penProcess.month" theme="simple"
							list="#{'0':'Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
					</s:else></td>
				</tr>
				<tr>
					<td width="10%"><label class="set" id="pension.year"
						name="pension.year" ondblclick="callShowDiv(this);"><%=label.get("pension.year")%></label>
					:<font color="red"> *</font></td>
					<td width="8%"></td>
					<td width="86%"><s:if test="flag">
						<!--<s:textfield size="6" name="penProcess.year" readonly="true"
							maxlength="4" onkeypress="return numbersOnly();" />
					--></s:if> <s:else>
						<s:textfield size="6" name="penProcess.year" maxlength="4"
							onkeypress="return numbersOnly();" />
					</s:else></td>
				</tr>
				<tr>
					<td width="10%" class="formtext"><label class="set"
						id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:<font color="red"> *</font></td>
					<td width="8%"></td>
					<td width="86%"><s:hidden name="penProcess.divCode" /> <s:textfield
						name="penProcess.divName" size="25" theme="simple" readonly="true" />
					<s:if test="flag">

					</s:if> <s:else>
						<img src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="17"
							onclick="javascript:callsF9(500,325,'PensionProcess_f9Div.action');">
					</s:else></td>
				</tr>
			</table>
			</td>
		</tr>
		</s:else>
		
		<!-- ADDED BY REEBA BEGINS -->
		
		<!-- FILTER SECTION -->
		<s:if test="%{filterFlag}">
		
		<tr>
			<td colspan="6">
			<table width="100%" border="0" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<s:hidden name="eArrMonth" />
					<td nowrap="nowrap" width="15%"><label class="set" id="pension.month2"
						name="pension.month" ondblclick="callShowDiv(this);"><%=label.get("pension.month")%></label><font
						color="red"> *</font> :</td>
					<td width="20%">
					<s:property value="penProcess.monthView" />
						<s:hidden name="penProcess.month" />
				
					<td width="15%"><label class="set" id="pension.year2"
						name="pension.year" ondblclick="callShowDiv(this);"><%=label.get("pension.year")%></label><font
						color="red"> *</font> :</td>
					<td width="20%"><s:property value="penProcess.year" /><s:hidden name="penProcess.year" /></td>
					<td width="15%"></td>
					<td width="20%"></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<s:hidden name="penProcess.divCode" />
				<s:hidden name="branchViewId" />
				<tr>
					<td width="15%"><label id="division2" name="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:<font color="red">*</font></td>
					<td width="35%"><s:textfield name="penProcess.divName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" /></td>
					<td width="15%"><label id="branch" name="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td width="35%"  id="ctrlShow"><s:textfield name="branchViewName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="middle" width="18"
						title="Select the branch"
						onclick="callsF9(500,325,'PensionProcess_f9BranchView.action');"></td>
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
						onclick="callsF9(500,325,'PensionProcess_f9DepartmentView.action');"></td>
					<td width="15%"><label id="employee.type" name="employee.type"
						ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
					:</td>
					<td width="35%" id="ctrlShow"><s:textfield name="employeeTypeViewName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="middle" width="18"
						title="Select the type of an employee"
						onclick="callsF9(500,325,'PensionProcess_f9EmployeeTypeView.action');">
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
						onclick="callsF9(500,325,'PensionProcess_f9PayBillView.action');">
					</td>
					<td width="15%"><label id="employee" name="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:</td>
					<td width="35%" id="ctrlShow"><s:textfield name="empViewName"
						readonly="true" size="35" cssStyle="background-color: #F2F2F2;" />
					<img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="middle" width="18"
						title="Select the employee"
						onclick="callsF9(500,325,'PensionProcess_f9employee.action');">
					</td>
				</tr>
				<tr>
					<td width="15%"></td>
					<td width="35%" align="right" id="ctrlShow"><input type="button"
						value="Show Records" class="token" title="Show"
						onclick="return callEditablePension();" /> <input type="button"
						value="Reset" class="reset" title="Reset"
						onclick="return resetFilters();" /></td>
					<td width="15%"></td>
					<td width="35%"></td>
				</tr>
			</table>
			</td>
		</tr>

		</s:if>
		<!-- ADDED BY REEBA ENDS -->
		
		
		
		
		
		
		
		
		
		
		
		
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
						<td>
						<table width="98%" border="0" align="center" cellpadding="2"
							cellspacing="2">
							<tr>
								<td width='5%' class="formth" align="center"><label
									class="set" name="pension.srno" id="pension.srno"
									ondblclick="callShowDiv(this);"><%=label.get("pension.srno")%></label>
								</td>
								<td width='20%' class="formth" align="center"><label
									class="set" name="employee.id" id="employee.id"
									ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label>
								</td>
								<td width='25%' class="formth" align="center"><label
									class="set" name="employee" id="employee"
									ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
								</td>
								<td width='15%' class="formth" align="center"><label
									class="set" name="pension.type" id="pension.type"
									ondblclick="callShowDiv(this);"><%=label.get("pension.type")%></label>
								</td>
								<%
									HashMap salMap = (HashMap) request.getAttribute("salaryMap");

									Object[][] headName = (Object[][]) request
											.getAttribute("creditHead");
								%>

								<%
								if (headName != null && headName.length != 0) {
								%>

								<%
								for (int i = 0; i < headName.length; i++) {
								%>

								<td class="formth" width="2%"><s:hidden name="headCode"
									value="<%=String.valueOf(headName[i][0])%>"></s:hidden> <%=String.valueOf(headName[i][1])%></td>

								<%
								}
								%>
								<%
								}
								%>


								<td width='15%' class="formth" align="center"><label
									class="set" name="pension.arrear" id="pension.arrear"
									ondblclick="callShowDiv(this);"><%=label.get("pension.arrear")%></label>
								</td>
								<td width='15%' class="formth" align="center"><label
									class="set" name="pension.amt" id="pension.amt"
									ondblclick="callShowDiv(this);"><%=label.get("pension.amt")%></label>
								</td>
								<td width='15%' class="formth" align="center"><label
									class="set" name="pension.comm" id="pension.comm"
									ondblclick="callShowDiv(this);"><%=label.get("pension.comm")%></label>
								</td>
								<td width='15%' class="formth" align="center"><label
									class="set" name="pension.recovery" id="pension.recovery"
									ondblclick="callShowDiv(this);"><%=label.get("pension.recovery")%></label>
								</td>
								<td width='15%' class="formth" align="center"><label
									class="set" name="pension.misc" id="pension.misc"
									ondblclick="callShowDiv(this);"><%=label.get("pension.misc")%></label>
								</td>
								<td width='15%' class="formth" align="center"><label
									class="set" name="pension.net" id="pension.net"
									ondblclick="callShowDiv(this);"><%=label.get("pension.net")%></label>
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
											id='<%="empId"+k%>' /><s:property value="empCode" /></td>
										<td width="25%" class="sortableTD"><s:property
											value="empName" /></td>
										<td width="15%" class="sortableTD"><s:property
											value="penType" /></td>

										<%
											String empId = (String) request.getAttribute("empId" + k);
											Object[][] salObj = (Object[][]) salMap.get(empId);
										%>

										<%
										if (salObj != null && salObj.length != 0) {
										%>
										<%
										for (int j = 0; j < salObj.length; j++) {
										%>
										<td width="15%" align="center" class="sortableTD"><input
											type="text" name="<%=k%>_<%=j%>_C"
											onkeypress="return numbersOnly();"
											onkeyup="calPensionAmt(<%=k%>);" id="<%=k%>_<%=j%>_C"
											size="4" style="text-align: right"
											value="<%=String.valueOf(salObj[j][1])%>" /></td>
										<%
										}
										%>
										<%
										} else {
										%>
										<%
										for (int j = 0; j < headName.length; j++) {
										%>
										<td width="15%" align="center" class="sortableTD"><input
											type="text" name="<%=k%>_<%=j%>_C"
											onkeypress="return numbersOnly();"
											onkeyup="calPensionAmt(<%=k%>);" id="<%=k%>_<%=j%>_C"
											size="4" style="text-align: right" value="0" /></td>
										<%
										}
										%>
										<%
										}
										%>
										<input type='hidden' name='creditCount'
											value='<%=salObj.length%>' id='creditCount' />
										<td width="15%" align="center" class="sortableTD"><s:textfield
											name="penArrear" id='<%="penArrear_"+k%>'
											onkeypress="return numbersOnly();" onkeyup="calculate(this);"
											size="4" cssStyle="text-align: right" /></td>
										<td width="15%" align="center" class="sortableTD"><s:textfield
											name="penAmount" id='<%="penAmount_"+k%>'
											onkeyup="calculate(this);" size="4"
											cssStyle="background-color: #F2F2F2; text-align: right;"
											readonly="true" /></td>
										<td width="15%" align="center" class="sortableTD"><s:textfield
											name="commAmount" id='<%="commAmount_"+k%>'
											onkeypress="return numbersOnly();" onkeyup="calculate(this);"
											size="4" cssStyle="text-align: right" /></td>
										<td width="15%" align="center" class="sortableTD"><s:textfield
											name="recAmount" id='<%="recAmount_"+k%>'
											onkeypress="return numbersOnly();" onkeyup="calculate(this);"
											size="4" cssStyle="text-align: right" /></td>
										<td width="15%" align="center" class="sortableTD"><s:textfield
											name="miscAmount" id='<%="miscAmount_"+k%>'
											onkeypress="return numbersOnly();" onkeyup="calculate(this);"
											size="4" cssStyle="text-align: right" /></td>
										<td width="15%" align="center" class="sortableTD"><s:textfield
											name="netAmount" id='<%="netAmount_"+k%>'
											onkeyup="calculate(this);" size="4" readonly="true"
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

	</table>
</s:form>
<script>
function processFun()
 { 		
 		var month  =document.getElementById("paraFrm_penProcess_month").value;
	 	var year   =document.getElementById("paraFrm_penProcess_year").value;
 		var div   =document.getElementById("paraFrm_penProcess_divCode").value;
 		
 		var monthName  =document.getElementById('pension.month').innerHTML.toLowerCase();
	 	var yearName   =document.getElementById('pension.year').innerHTML.toLowerCase();
 		var divName   =document.getElementById('division').innerHTML.toLowerCase();
 		
 		 if(month =='0'){
	 		alert("Please select "+monthName);
	 		document.getElementById("paraFrm_penProcess_month").focus();
	 		return false;
	 	}
		 if(year ==''){
		 	alert("Please enter "+yearName);
		 	document.getElementById("paraFrm_penProcess_year").focus();
		 	return false;
		}
		
		 if(!checkYear('paraFrm_penProcess_year','pension.year'))
	 		return false;	
		
		 if(div == ""){
	 		alert("Please select the "+divName);
	 		document.getElementById("paraFrm_penProcess_divCode").focus();
	 		return false;
	 	}
	 	 	
	document.getElementById('paraFrm').action='PensionProcess_processPension.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
}

function saveFun(){
 	var ledgerStatus = document.getElementById('paraFrm_penProcess_status').value;
  	var cc = document.getElementById('count').value;
 	if(ledgerStatus == 'PENS_FINAL'){
 	
 		alert('Pension is already locked.');
 		
 	}else{
 		
 		if(cc != 0){
			document.getElementById('paraFrm').action='PensionProcess_savePension.action';
			document.getElementById('paraFrm').submit();
 		}else
 			alert('No employee exists.')
 	}	
}

function lockFun(){
 	var ledgerStatus = document.getElementById('paraFrm_penProcess_status').value;
  	var cc = document.getElementById('count').value;
 	if(ledgerStatus == 'PENS_FINAL'){
 	
 		alert('Pension is already locked.');
 		
 	}else{
 		if(cc != 0){
 			if(ledgerStatus == ''){
 				alert('Please save the pension first.');
 				return false;
			}
 			con=confirm('Are you sure!\nYou want to lock the pension?');
		 	if(con){
				document.getElementById('paraFrm').action="PensionProcess_lockPension.action";
				document.getElementById('paraFrm').submit();
			}
	 		
 		}else
 			alert('No employee exists.')
 	}	
}

function dropandprocessFun(){
	var ledgerStatus = document.getElementById('paraFrm_penProcess_status').value;
  	var cc = document.getElementById('count').value;
 	if(ledgerStatus == 'PENS_FINAL'){
 	
 		alert('Pension is already locked.');
 		
 	}else{
 		
 		if(cc != 0){
	 			con=confirm('Do you really want to re-Process the pension?');
				 	if(con){
					document.getElementById('paraFrm').action="PensionProcess_reProcessPension.action";
					document.getElementById('paraFrm').submit();
					}
	 		
 		}else
 			alert('No employee exists.')
 	}

}



function calPensionAmt(rowId){
 	
 	var pensionCreditAmt=0;
 	var creditCount=document.getElementById("creditCount").value;
 	 		
 	for(var i=0;i<eval(creditCount);i++){
 		
 		var amt = document.getElementById(rowId+"_"+i+"_C").value;
 		
 		if(amt ==''){
 			amt = 0;
 			document.getElementById(rowId+"_"+i+"_C").value=0;
 		}
 		
 		pensionCreditAmt=eval(pensionCreditAmt)+eval((amt*100)/100);
 	}
 	document.getElementById('penAmount_'+rowId).value=Math.ceil(eval(pensionCreditAmt));
 	
 	calNetPension(rowId); 	
 }
 function calNetPension(rowId){
 	 	
 	var netPensionAmt=0;
 	var pensionCreditAmt=0;
 	var pensionDebitAmt=0;
 	
 	var pensionAmt=document.getElementById("penAmount_"+rowId).value;
 	//alert(pensionAmt);
 	var commtAmt=document.getElementById("commAmount_"+rowId).value;
 	if(commtAmt ==''){
 			commtAmt = 0;
 			document.getElementById("commAmount_"+rowId).value=0;
 	}
 	//alert(commtAmt);
 	var arrearAmt=document.getElementById("penArrear_"+rowId).value;
 	if(arrearAmt ==''){
 			arrearAmt = 0;
 			document.getElementById("penArrear_"+rowId).value=0;
 	} 	
 	//alert(arrearAmt);	
 	var recAmt=document.getElementById("recAmount_"+rowId).value;
 	if(recAmt ==''){
 			recAmt = 0;
 			document.getElementById("recAmount_"+rowId).value=0;
 	}
 	//alert(recAmt);
 	var miscRecAmt=document.getElementById("miscAmount_"+rowId).value;
 	if(miscRecAmt ==''){
 			miscRecAmt = 0;
 			document.getElementById("miscAmount_"+rowId).value=0;
 	}
 	//alert(miscRecAmt);
 	pensionDebitAmt=eval(commtAmt)+eval(recAmt)+eval(miscRecAmt);
 	//alert(pensionDebitAmt);
 	netPensionAmt = eval((pensionAmt*100)/100) + eval((arrearAmt*100)/100) - eval((pensionDebitAmt*100)/100);
 	//alert(netPensionAmt);	
 	if(eval(netPensionAmt) < 0)
 		netPensionAmt = 0;
 	document.getElementById("penAmount_"+rowId).value=	eval((pensionAmt*100)/100) + eval((arrearAmt*100)/100)
 	document.getElementById('netAmount_'+rowId).value=eval(netPensionAmt);
}   
function calculate(obj){
     //alert('in calculate');
     var temp = obj.id;
    // alert('object----------'+temp);
     var rowId = temp.split('_');
    // alert('rowId'+rowId[1]);
    calPensionAmt(rowId[1]);
   	 
}

function callPageText(id){
 			 
	   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;		
		 	totalPage =document.getElementById('totalPage').value;		
			document.getElementById('paraFrm_myPage').value=pageNo;
	      
	        if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
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
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
           if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
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
		
		document.getElementById('paraFrm_myPage').value=id;
		displayConfirmDiv();
		document.getElementById('confirmationDiv').style.display='block';
		 
	}
	
	
	function enableBlockDiv(){
	  		
	  		try{
		  		
				document.getElementById("overlay").style.visibility = "visible";
				document.getElementById("overlay").style.display = "block";
			}
			catch(e){
			}
	  }
	  
	  
	  function disableBlockDiv(){
	  		
	  		try{
		  		
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
			}
			catch(e){
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
			}
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
	document.getElementById('paraFrm').action="PensionProcess_savePension.action";
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
	document.getElementById('paraFrm').action="PensionProcess_callPaging.action";
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

//ADDED BY REEBA
	function callEditablePension(){
	
		document.getElementById('paraFrm').action='PensionProcess_getEmployeeList.action';
		document.getElementById('paraFrm').submit();
	}
	
	function backtolistFun(){
	
		document.getElementById('paraFrm').action='PensionProcess_input.action';
		document.getElementById('paraFrm').submit();
	}
	
	function backFun(){
	
		document.getElementById('paraFrm').action='PensionProcess_showFilters.action';
		document.getElementById('paraFrm').submit();
	}
	
	function resetFun(){
	
		document.getElementById('paraFrm').action='PensionProcess_reset.action';
		document.getElementById('paraFrm').submit();
	}
	
	function resetFilters(){
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
		document.getElementById("paraFrm_empStatusView").value = "";
		document.getElementById("paraFrm_empTokenView").value = "";
	}
 
</script>
