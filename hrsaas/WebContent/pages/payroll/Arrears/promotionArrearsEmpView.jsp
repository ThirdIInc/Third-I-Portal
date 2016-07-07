<%--Pankaj_Jain--%>
<%--July 1, 2008--%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>

 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/arrearsAjax.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/superTables.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/syncscroll.js"></script>
<div id="msgDiv"
	style='position: absolute; z-index: 3; background-color: red; 100 px; height: 50px; visibility: hidden; top: 70px; left: 250px;'></div>

<%!int k = 1, c, d;%>
<%
int count = 0;
%>
<s:form action="PromotionArrears" name="PromotionArrears" id="paraFrm"
	theme="simple">
	<s:hidden name="arrCode" />
	<s:hidden name="proccessDate" />   
	<s:hidden name="onholdFlag" />
	<s:hidden name="status" />
	<s:hidden name="savedFlag" />
	<s:hidden name="payinSalFlag" />
	<s:hidden name="deductTaxFlag" />
<s:hidden name="suppressCreditFlag"/>
<s:hidden name="suppressDebitCode"/>
<s:hidden name="suppressDebitName"/>

	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Promotion Arrears
					  </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1">
				<tr>
					<td width="78%">
					
					<s:if test="status!='Lock'">
					<input type="button" class="save" name="" value="ReCalculate" onclick="callEmpReProcess()">
					<input type="button" class="save" name="" value="Save" onclick="callSave()">
					<s:if test="onholdFlag=='true'">
					<input type="button" class="token" name="" value="RemoveOnHold" onclick="callRemoveOnHold()">
					<input type="button" class="token" name="" value="OnHold" onclick="callOnHold()">
					</s:if>
					<s:else>					
					</s:else>
					
					<input type="button" class="remove" name="" value="Remove" onclick="callRemove()">
					</s:if>
					
					<input type="button" class="back" name="" value="Back" onclick="back()">
						
					</td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="100%" colspan="4"><strong>Select Filters</strong></td>

				</tr>
				
				
				<s:hidden name="divCode" />
				<s:hidden name="brnCode" />			
				<s:hidden name="monthName" />
				<s:hidden name="eArrYear" />
				<s:hidden name="arrToMonth" />
				<s:hidden name="arrToYear" />			
				<s:hidden name="arrPaidMonth" />
				<s:hidden name="arrPaidYear" />
				<s:hidden name="payinSalCheckBox" />
				<s:hidden name="deductTaxCheckBox" />
			
				
				
				
				
			</table>
			</td>
		</tr>
		<s:if test="savedFlag">
		<tr>
			<td width="100%">
			<table width="100%" cellpadding="1"  cellspacing="2" class="formbg">
			
			<tr>
					<td width="20%" ><label id="record.count" name="record.count"
						ondblclick="callShowDiv(this);"><%=label.get("record.count")%></label>
					:</td>
					<td width="30%" id="ctrlShow"><s:textfield  name="totalEmpCount"  readonly="true" size="25" theme="simple" /></td>
					<td width="20%"><label id="total.netAmount" name="total.netAmount"
						ondblclick="callShowDiv(this);"><%=label.get("total.netAmount")%></label>
					:</td>
					
					<td width="30%" id="ctrlShow"><s:textfield id="totalNetAmount" name="totalNetAmount" 
						readonly="true" size="25" cssStyle="background-color: #F2F2F2;border :none " /></td>
					
				</tr>
			</table>
			</td>
			</tr>
		<tr>
			<td width="100%">
			<table width="100%" cellpadding="1" cellspacing="2" class="formbg">

				<tr>
					<td width="100%" colspan="4"><strong><label id="add.employee" name="add.employee"
						ondblclick="callShowDiv(this);"><%=label.get("add.employee")%></label></strong></td>

				</tr>
				<tr>
					<td width="20%">Select <label id="employee" name="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
					<td width="35%"><s:hidden name="empProcessId"/><s:textfield
						name="empProcessToken" readonly="true" size="10" theme="simple" /><s:textfield
						name="empProcessName" readonly="true" size="25" theme="simple" /></td>
					<td width="20%"><img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callsF9(500,325,'PromotionArrears_f9Branch.action');">
							<input type="button" value="Process Employee" />
					</td>

				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" cellpadding="1" cellspacing="2" class="formbg">

				<tr>
					<td width="100%" colspan="4"><strong><label id="update.employee" name="update.employee"
						ondblclick="callShowDiv(this);"><%=label.get("update.employee")%></label></strong></td>

				</tr>
				<tr>
					<td width="20%">Select <label id="employee" name="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
					<td width="35%"><s:hidden name="empUpdateId"/><s:textfield
						name="empUpdateToken" readonly="true" size="10" theme="simple" /><s:textfield
						name="empUpdateName" readonly="true" size="25" theme="simple" /></td>
					<td width="20%"><img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callsF9(500,325,'PromotionArrears_f9Branch.action');">
							<input type="button" value="View Records" />
					</td>

				</tr>
			</table>
			</td>
		</tr>
		</s:if>
		<s:hidden name="hdPage" id="hdPage" value="%{hdPage}" />
	
	
	<!-- ADD MISSING EMPLOYEE -->
	<s:if test="arrCode!=''">
	
	
	
	
	
	
	<!-- VIEW EMPLOYEE  DETAILS -->
	
	
	
	
	<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="100%" colspan="4"><strong>Employee </strong></td>

				</tr>				
				<tr>
					
					<td width="20%" colspan="1"><label class="set" id="select.employee"
						name="select.employee" ondblclick="callShowDiv(this);"><%=label.get("select.employee")%></label> :<font
						color="red"> *</font> </td>
					<td width="70%" colspan="3" nowrap="nowrap">
					<s:hidden name="editEmpCode"/> 
					<s:textfield name="editEmpToken" readonly="true" size="10" cssStyle="background-color: #F2F2F2"/> 
					 <s:textfield name="editEmpName" readonly="true" size="50" cssStyle="background-color: #F2F2F2"/> 
						<!--<img src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callsF9(500,325,'PromotionArrears_f9emp.action');">
					--></td>					
					
					<!--<<td width="30%" colspan="1" >
					<input type="button" class="token" name="" value="View Details" onclick="callViewDetails()">
					</td>
					-->
				</tr>
			</table>
			</td>
		</tr>
		
		
	</s:if>
	
	<s:if test="arrList">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg" id="salaryTable2">
				<tr>
					<td>
						<div id="scrollDiv1" class="scrollf9" style="width: 760px;overflow-x: yes;height: 100%; margin: 0;padding: 0;overflow: auto;">	
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg" id="salaryTable">
				<tr>
					<td class="formth">Month</td>
				<td class="formth">Year</td>
				<td><input class="token" readonly="readonly"
					style="text-align: center; border: none; margin: 1px" type="hidden"
					value="Sal Days" /></td>
				<td class="formth"> Arr Days</td>
					
				
						<s:iterator value="crHDList">
						<td class="formth" align="center" nowrap="nowrap">
						<s:property value="creditName" /><s:hidden
							name="creditCode" /></td>
					</s:iterator>
					<td class="formth">Total Credit</td>
					<s:iterator value="drHDList">
						<td class="formth" align="center" nowrap="nowrap"><s:hidden
							name="debitCode" /><s:property value="debitName" /></td>
					</s:iterator>
					<td class="formth">Total Debit</td>
					<td class="formth">Net Pay</td>
					<td><input class="classCheck" type="checkbox" name="chkEmpAll"
						id="chkEmpAll" onclick="callChkAll();" size="2" /></td>
					
						
					
				

				</tr>				
				
				<%
					int CR = 1;
					int DR = 1;
					
				%>
				
				<s:iterator value="arrList">
				<%
					int i = 1;
					int j = 0;
					int col;
				%>
				
					<tr>
						
						<td><input name="month" id="month<%=k %>" readonly="readonly"
							style="background-color: #F2F2F2" type="text" size="4"
							value='<s:property value="month" />' /> <s:hidden name="hMonth"
							id='<%="hMonth"+k%>' />
							<s:hidden name="listEmpCode"/>
							<s:hidden name="hPromCode" />
							</td>
						<td><input name="year" id="year<%=k %>" readonly="readonly"
							style="background-color: #F2F2F2" type="text" size="4"
							value='<s:property value="year" />' /></td>
						<td><input name="salDays" id="salDays<%=k %>"
							readonly="readonly" style="background-color: #F2F2F2"
							type="hidden" value='<s:property value="salDays" />' /></td>
						<td><input name="arrDays" id="arrDays<%=k %>"
							readonly="readonly" style="background-color: #F2F2F2" type="text"
							size="8" value='<s:property value="arrDays" />' /></td>
							
						
									<s:iterator value="crValList">
									<td><input type="text" name="CR<%=CR %>" size="6"
										style="text-align: right" id="<%= k+"c"+i %>" maxlength="10"
										value="<s:property value='creditVal' />" onkeyup="sum(<%=k %>)"
										onkeypress="return numbersOnly();" />
										</td>
											<%
												i++;
												j++;
											%>
										</s:iterator>
										
									<td>
									<input type="text" name="totCredit" size="8"
										style="background-color: #F2F2F2; text-align: righ" id="<%= k+"c"+i %>"
										value="<s:property value='totalCr' />" readonly="readonly" />
									</td>
									<s:iterator value="drValList">
										<%
										i++;
										%>
										
										<td><input type="text" name="DR<%=DR %>" size="6"
											style="text-align: right" id="<%= k+"c"+i %>" maxlength="10"
											value="<s:property value='debitVal' />" onkeyup="sum(<%=k %>)"
											onkeypress="return numbersOnly();" /></td>
									</s:iterator>
									<%
										col = i;
										col++;
									%>
									<td><input type="text" name="totDebit" size="8"
										style="background-color: #F2F2F2; text-align: righ" id="<%= k+"c"+col %>"
										value="<s:property value='totalDr' />" readonly="readonly" /></td>
									<%
									col++;
									%>
									<td><input type="text" name="totAmtPay" size="8"
										style="background-color: #F2F2F2; text-align: righ" id="<%= k+"c"+col %>"
										value="<s:property value='amtPay' />" readonly="readonly" /></td>
									<td>
									<input type="hidden"   name="hcheck" id='<%="hcheck"+CR %>' >
									<input class="classCheck" type="checkbox" size="2"
										name="chkEmp" id='<%="chkEmp"+CR %>'
										value="<s:property value='eId' />&<s:property value='hMonth' />&<s:property value='year' />&<s:property value='promotionCode' />&<s:property value='<%=""+CR %>' />"
										onclick="callChkEmp('<%=CR %>');" />
									</td>						
						
					</tr>
				
				<%
									k++;
									c = i;
									d = j;
									CR++;
									DR++;
								%>
			</s:iterator>
				
				
				
				
				
			</table>
			
			</div>
		</td><!--
					
	
			<td valign="top">
					<%
					int CR1 = 1;
				%>
				<div>
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2" class="formbg" id="salaryTable">
						<tr>
						<td><input class="classCheck" type="checkbox" name="chkEmpAll"
								id="chkEmpAll" onclick="callChkAll();" size="2" /></td>
						</tr>
						<s:iterator value="arrList">
						<tr> 
						<td>
											<input type="hidden"   name="hcheck" id='<%="hcheck"+CR1 %>' >
											<input class="classCheck" type="checkbox" size="2"
												name="chkEmp" id='<%="chkEmp"+CR1 %>'										
												onclick="callChkEmp('<%=CR1 %>');" />
											</td>
						</tr>
						<% CR1++;%>
						</s:iterator>
					</table>	
				</div>			
			</td>
		
				--></tr>
			</table>
			</td>
			
		</tr>	
	
		
	</s:if>
	
	<!-- END: -->
	
	<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1">
				<tr>
					<td width="78%">
					
					<s:if test="status!='Lock'">
					<input type="button" class="save" name="" value="ReCalculate" onclick="callEmpReProcess()">
					<input type="button" class="save" name="" value="Save" onclick="callSave()">
					<s:if test="onholdFlag=='true'">
					<input type="button" class="token" name="" value="RemoveOnHold" onclick="callRemoveOnHold()">
					<input type="button" class="token" name="" value="OnHold" onclick="callOnHold()">
					</s:if>
					<s:else>					
					</s:else>
					
					<input type="button" class="remove" name="" value="Remove" onclick="callRemove()">
					</s:if>
					
					<input type="button" class="back" name="" value="Back" onclick="back()">
					
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	//callOnLoad();
	function callOnLoad()
	{
		if(document.getElementById('paraFrm_payinSalFlag').value == "true")
			document.getElementById('paraFrm_payinSalCheckBox').checked=true
		else
			document.getElementById('paraFrm_payinSalCheckBox').checked = false;
		if(document.getElementById('paraFrm_deductTaxFlag').value == "true")
			document.getElementById('paraFrm_deductTaxCheckBox').checked=true
		else
			document.getElementById('paraFrm_deductTaxCheckBox').checked = false;	
			
		if(document.getElementById('paraFrm_eArrYear').value=='')
			setCurrentYear('paraFrm_eArrYear');
		if(document.getElementById('paraFrm_paidYear').value=='')
			setCurrentYear('paraFrm_paidYear');
		gridScroll();
	}
	
	/**
	METHOD TO CHECK VALIDATION
	**/
	function checkValidation(){
	var fromMonth=document.getElementById('paraFrm_monthName').value;
	var fromYear=document.getElementById('paraFrm_eArrYear').value;
	var toMonth=document.getElementById('paraFrm_arrToMonth').value;
	var toYear=document.getElementById('paraFrm_arrToYear').value;
	var paidMonth=document.getElementById('paraFrm_arrPaidMonth').value;
	var paidYear=document.getElementById('paraFrm_arrPaidYear').value;
	var divCode=document.getElementById('paraFrm_divCode').value;
	
	if(fromMonth==''){
	alert("Please select "+document.getElementById('calculateFrom').innerHTML.toLowerCase()+" month");
	return false;
	}
	if(fromYear==''){
	alert("Please enter "+document.getElementById('calculateFrom').innerHTML.toLowerCase()+" year");
	return false;
	}
	
	if(toMonth==''){
	alert("Please select "+document.getElementById('calculateTo').innerHTML.toLowerCase()+" month");
	return false;
	}
	if(toYear==''){
	alert("Please enter "+document.getElementById('calculateTo').innerHTML.toLowerCase()+" year");
	return false;
	}
	
	if(paidMonth==''){
	alert("Please select "+document.getElementById('paid.month').innerHTML.toLowerCase()+" ");
	return false;
	}
	if(paidYear==''){
	alert("Please enter "+document.getElementById('paid.year').innerHTML.toLowerCase()+" ");
	return false;
	}
	
	if(divCode==''){
	alert("Please select division");
	return false;
	}
	
	return true;
	}
	
	function processFun(){
		if(!checkValidation()){
			return false;
		}
		var con=confirm('Do you realy want to process the promotion arrears');
		if(con){	
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="PromotionArrears_processPromotion.action";
				document.getElementById('paraFrm').submit();
		}
		else{
		return false;
		}
	}
	function reprocessFun(){
		if(!checkValidation()){
			return false;
		}
		var con=confirm('Do you realy want to reprocess the promotion arrears');
		if(con){	
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="PromotionArrears_reprocessPromotion.action";
				document.getElementById('paraFrm').submit();
		}
		else{
		return false;
		}
	}
	
	
	function callViewDetails(){
				if(document.getElementById('paraFrm_editEmpCode').value==''){
				alert('Please select employee');
				document.getElementById('paraFrm_editEmpName').focus();
				return false;
				}
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="PromotionArrears_viewEmpDetails.action";
				document.getElementById('paraFrm').submit();
	}
	function callProcess(){
				if(document.getElementById('paraFrm_addEmpCode').value==''){
				alert('Please select employee');
				document.getElementById('paraFrm_addEmpName').focus();
				return false;
				}
				var con=confirm('Do you realy want to process the promotion arrears');
		if(con){
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="PromotionArrears_addPromEmployee.action";
				document.getElementById('paraFrm').submit();
				}
		else{
		return false;
		}
	}
	
		 function callSave(){	
		 			var table = document.getElementById('salaryTable'); 
					var rowCount = table.rows.length; 
		 			var count=0;
				for(var i=1;i<rowCount;i++){
					var creditValue=document.getElementById('hcheck'+i).value;
					if(creditValue=='Y'){
						count++;
					}										
				}
		 			if(count=='0'){
		 			alert('Please select atleast one record');
		 			return false;
		 			}
					var con=confirm('Do you realy want to save the promotion arrears');
			if(con){
					document.getElementById('paraFrm').target = "_self";
					document.getElementById('paraFrm').action="PromotionArrears_savePromEmployee.action";
					document.getElementById('paraFrm').submit();
					}
			else{
			return false;
			}
		}
	 
	  function callEmpReProcess(){				
					var con=confirm('Do you realy want to ReCalculate ?');
			if(con){
					document.getElementById('paraFrm').target = "_self";
					document.getElementById('paraFrm').action="PromotionArrears_reCalculateEmpPromotion.action";
					document.getElementById('paraFrm').submit();
					}
			else{
			return false;
			}
		}
	 
	 
	  function callOnHold(){				
					var con=confirm('Do you realy want to onhold the promotion record');
			if(con){
					document.getElementById('paraFrm').target = "_self";
					document.getElementById('paraFrm').action="PromotionArrears_onholdPromEmployee.action";
					document.getElementById('paraFrm').submit();
					}
			else{
			return false;
			}
		}
		 function callRemoveOnHold(){				
					var con=confirm('Do you realy want to remove onhold ?');
			if(con){
					document.getElementById('paraFrm').target = "_self";
					document.getElementById('paraFrm').action="PromotionArrears_onholdPromEmployee.action";
					document.getElementById('paraFrm').submit();
					}
			else{
			return false;
			}
		}
		
		
		
	  function callRemove(){		
	  var table = document.getElementById('salaryTable'); 
					var rowCount = table.rows.length; 
		 			var count=0;
				for(var i=1;i<rowCount;i++){
					var creditValue=document.getElementById('hcheck'+i).value;
					if(creditValue=='Y'){
						count++;
					}										
				}
		 			if(count=='0'){
		 			alert('Please select atleast one record');
		 			return false;
		 			}	
				var con=confirm('Do you realy want to remove the promotion arrears');
		if(con){
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="PromotionArrears_removePromEmployee.action";
				document.getElementById('paraFrm').submit();
				}
		else{
		return false;
		}
	}
	
	function back(){			
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="PromotionArrears_callForEdit.action";
				document.getElementById('paraFrm').submit();		
	}
	
	function bankstatementFun(){
		//paraFrm_linkSource
			var arrearsCode=document.getElementById('paraFrm_arrCode').value;	
			alert(arrearsCode);		
			var linkSource11='PromotionArrears_callForEdit.action?arrCode='+arrearsCode;
			var month=document.getElementById('paraFrm_arrPaidMonth').value;
			var year=document.getElementById('paraFrm_arrPaidYear').value;			
			var divName=document.getElementById('paraFrm_divName').value;
			var monthText='1';//document.getElementById('paraFrm_monthView').value;
			var divCode=document.getElementById('paraFrm_divCode').value
			document.getElementById('paraFrm').action='SalaryStatementBank_viewSalaryStatementLink.action?earningType=P&earningTypeDisplay=S&hiddenMonth='+month+'&earningYear='+year+'&earningCode='+
			arrearsCode+'&divisionName11='+divName+'&earningMonth='+monthText+'&linkSource='+linkSource11+'&divisionCode='+divCode;
			document.getElementById('paraFrm').submit();
			//document.getElementById('paraFrm_linkSource').value='SalaryProcess_callForEdit.action?linkSourceCode='+ledgerCodeValue;
	}
	
	
	
	function gridScroll()
	{
		myST = superTable("thetable", { fixedCols : 6,rightCols:2,viewCols:4});
	}
	function callMonth()
	{
		document.getElementById('paraFrm_eArrMonth').value = document.getElementById('paraFrm_monthName').selectedIndex;
	}
	
	function callPaidMonth()
	{
		document.getElementById('paraFrm_paidArrMonth').value = document.getElementById('paraFrm_paidMonthName').selectedIndex;
	}

	function validateProcess()
	{
		if(document.getElementById('paraFrm_monthName').selectedIndex == 0)
		{
			alert("Please select "+document.getElementById('reflecting.month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_monthName').focus();
			return false;
		}
		if(document.getElementById('paraFrm_eArrYear').value == "")
		{
			alert("Please enter "+document.getElementById('reflecting.year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_eArrYear').focus();
			return false;
		}
		
			if(document.getElementById('paraFrm_paidMonthName').value==''){
				alert("Please select "+document.getElementById('paid.month').innerHTML.toLowerCase());
				return false;
			}
			if(document.getElementById('paraFrm_paidYear').value==''){
				alert("Please enter "+document.getElementById('paid.year').innerHTML.toLowerCase());
				return false;
			}
			
		if(document.getElementById('paraFrm_divCode').value == "")
		{
			alert("Please select the "+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		//if(document.getElementById('paraFrm_brnCode').value == "")
		//{
		//	alert("Please select "+document.getElementById('branch').innerHTML.toLowerCase());
		//	return false;
		//}
		document.getElementById('paraFrm_monthRef').value = document.getElementById('paraFrm_payinSalFlag').checked;
		return true;
	}
	
	function sum(rowNo)
	{
		var i = <%=c%>
		var j = <%=d%>
		var totalCr = 0;
		var totalDr = 0;
		for(count=1;count<=i;count++)
		{
			if(count<=j)
			{
				if(document.getElementById(rowNo+'c'+count).value!="")
				{
					if((document.getElementById(rowNo+'c'+count).value).indexOf('-') > -1)
					{
							var temp = new Array(2); 
							temp = document.getElementById(rowNo+'c'+count).value.split('-');
							document.getElementById(rowNo+'c'+count).value = "-"+temp[1];
					}
					totalCr+=Math.round(document.getElementById(rowNo+'c'+count).value);
				}
				else
					document.getElementById(rowNo+'c'+count).value=0;
			}
			else if(count>j+1)
			{
				if(document.getElementById(rowNo+'c'+count).value!="")
				{
					if((document.getElementById(rowNo+'c'+count).value).indexOf('-') > -1)
					{
							var temp = new Array(2); 
							temp = document.getElementById(rowNo+'c'+count).value.split('-');
							document.getElementById(rowNo+'c'+count).value = "-"+temp[1];
					}
					totalDr+=Math.round(document.getElementById(rowNo+'c'+count).value);
				}
				else
					document.getElementById(rowNo+'c'+count).value=0;
			}
		}
		document.getElementById(rowNo+'c'+(j+1)).value = totalCr;
		document.getElementById(rowNo+'c'+count).value = totalDr;
		count++;
		document.getElementById(rowNo+'c'+count).value = totalCr-totalDr;
	}
	
	function callPage(id,totalPage)
	{
		var con;
		if(id=='P'){
		var p=document.getElementById('hdPage').value;
		id=eval(p)-1;
		}
		
		if(id=='N'){
		var p=document.getElementById('hdPage').value;
		id=eval(p)+1;
		}
		
		document.getElementById('hdPage').value=id;
		document.getElementById('paraFrm').action="PromotionArrears_calArrears.action";
		document.getElementById('paraFrm').submit();
	}
	
	function lockArrears(type)
	{
		if(type == "lock")
		{
			if(document.getElementById('paraFrm_arrCode').value == "")
			{
				alert("Please select the record to lock");
				return false;
			}
				var conf=confirm("Do you really want to lock promotion arrears ?");
			if(conf)
			{
				retrieveLockURL("PromotionArrears_lockArrears.action?type="+type,"PromotionArrears");
				document.getElementById('lock').style.display = 'none';
				document.getElementById('delete').style.display = 'none';
				document.getElementById('unlock').style.display = '';
			}
			else
				return false;
		}
		else
		{
			if(document.getElementById('paraFrm_arrCode').value == "")
			{
				alert("Please select the record to unlock");
				return false;
			}
			if(document.getElementById('paraFrm_status').value == "Pending")
			{
				alert("Arrears already unlocked");
				return false;
			}
			doAuthorisation('3', 'Arrears', 'U');
		}
	}
	function doUnlock() {
			var type='unlock';
	 		retrieveLockURL("PromotionArrears_lockArrears.action?type="+type,"PromotionArrears");
				document.getElementById('unlock').style.display = 'none';
				document.getElementById('lock').style.display = '';
				document.getElementById('delete').style.display = '';
	  
	}
	function saveValidate()
	{
		if(!validateProcess()){
			return false;
		}
		if(document.getElementById('paraFrm_status').value == "Locked")
		{
			alert("Arrears is locked so cannot update the record");
			return false;
		}
		var rowCount  = <%= count%>
		if(rowCount <= 1)
		{
			alert("No records found to save. Please either select a record or proccess the arrears");
			return false;
		}
		return true;
	}
	
	function callChkAll()
	{
		
			
		var table = document.getElementById('salaryTable'); 
					var rowCount = table.rows.length; 
		 			var count=0;
				for(var i=1;i<rowCount;i++){
				if(document.getElementById('chkEmpAll').checked){
					document.getElementById('hcheck'+i).value='Y';
					document.getElementById('chkEmp'+i).checked=true;					
					}	
					else{
					document.getElementById('hcheck'+i).value='N';	
					document.getElementById('chkEmp'+i).checked=false;		
					}														
				}	
			
	}
	
	function callChkEmp(id)
	{
	//hcheck
	document.getElementById('hcheck'+id).value='N';
	if(document.getElementById('chkEmp'+id).checked == true){
		document.getElementById('hcheck'+id).value='Y';
	}	
	
	var table = document.getElementById('salaryTable'); 
					var rowCount = table.rows.length; 
					
		 			var count=0;
				for(var i=1;i<rowCount;i++){
				if(document.getElementById('hcheck'+i).value=='Y'){
					count++;
					}																			
				}
				rowCount=rowCount-1;
				document.getElementById('chkEmpAll').checked=false;
				if(rowCount==count){
				document.getElementById('chkEmpAll').checked=true;
				}
	}
	

	
	
	
	function callRecalculate()
	{
		var rowCount = <%= count%>
		if(rowCount == 0)
		{
			alert("Please select the arrear record to recalculate");
			return false;
		}
		if(document.getElementById('paraFrm_status').value == "Locked")
		{
			alert("Arrears are locked so cannot recalculate");
			for(i = 1; i < rowCount; i++)
				document.getElementById('chkEmp'+i).checked = false;
			document.getElementById('chkEmpAll').checked = false;
			return false;
		}
		var count=0;
		rowCount = <%=count%>;
		var k = <%=c%>
		for(i = 1; i < rowCount; i++)
		{
			if(document.getElementById('chkEmp'+i).checked == true)
			{
				document.getElementById('recalEmpId'+i).value = "Y";
				count++;
			}
		}
		if(count==0)
		{
			alert("Please select atleast one record");
			return false;
		}
		retrieveURLArrearsRecal('PromotionArrears_recalculate.action?','paraFrm');
		for(i = 1; i < rowCount; i++)
			document.getElementById('chkEmp'+i).checked = false;
		document.getElementById('chkEmpAll').checked = false;
	}
	function deleteArrears(){
		if(document.getElementById('paraFrm_arrCode').value==""){
			alert("Please select arrears to delete");
			return false;
		}else if(document.getElementById('paraFrm_status').value == "Locked")
		{
			alert("Arrears are locked so cannot delete");
			return false;
		}
		else{
		var conf=confirm("Do you really want to delete arrears?");
  			if(!conf) 
  			{
  				return false;
  			}
			document.getElementById('paraFrm').action="PromotionArrears_delete.action";
			document.getElementById('paraFrm').submit();
		}
	}
	
	function callDelete(){
	try{
		var arrearCode = document.getElementById('paraFrm_arrCode').value;
		if(arrearCode == ""){
			alert("Please save the record first");
			return false;
		}
		var rowCount = <%= count%>
		if(rowCount == 0)
		{
			alert("Please select the arrear record to delete");
			return false;
		}
		if(document.getElementById('paraFrm_status').value == "Locked")
		{
			alert("Arrears are locked so cannot delete");
			for(i = 1; i < rowCount; i++)
				document.getElementById('chkEmp'+i).checked = false;
			document.getElementById('chkEmpAll').checked = false;
			return false;
		}
		
		var count=0;
		rowCount = <%=count%>;
		var k = <%=c%>
		for(i = 1; i < rowCount; i++)
		{
			if(document.getElementById('chkEmp'+i).checked == true)
			{
				document.getElementById('deleteEmpId'+i).value = "Y";
				count++;
			}
		}
		if(count==0)
		{
			alert("Please select atleast one record");
			return false;
		}
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action="PromotionArrears_deleteEmployee.action";
			document.getElementById('paraFrm').submit();
		}
		
		}catch(e){
			//alert(e);
		}
	
	}
	
	

	

</script>
